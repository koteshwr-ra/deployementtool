package org.apache.commons.collections4.splitmap;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import org.apache.commons.collections4.Put;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.map.LinkedMap;

public class TransformedSplitMap<J, K, U, V> extends AbstractIterableGetMapDecorator<K, V> implements Put<J, U>, Serializable {
    private static final long serialVersionUID = 5966875321133456994L;
    private final Transformer<? super J, ? extends K> keyTransformer;
    private final Transformer<? super U, ? extends V> valueTransformer;

    public static <J, K, U, V> TransformedSplitMap<J, K, U, V> transformingMap(Map<K, V> map, Transformer<? super J, ? extends K> transformer, Transformer<? super U, ? extends V> transformer2) {
        return new TransformedSplitMap<>(map, transformer, transformer2);
    }

    protected TransformedSplitMap(Map<K, V> map, Transformer<? super J, ? extends K> transformer, Transformer<? super U, ? extends V> transformer2) {
        super(map);
        if (transformer != null) {
            this.keyTransformer = transformer;
            if (transformer2 != null) {
                this.valueTransformer = transformer2;
                return;
            }
            throw new NullPointerException("ValueTransformer must not be null.");
        }
        throw new NullPointerException("KeyTransformer must not be null.");
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(decorated());
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.map = (Map) objectInputStream.readObject();
    }

    /* access modifiers changed from: protected */
    public K transformKey(J j) {
        return this.keyTransformer.transform(j);
    }

    /* access modifiers changed from: protected */
    public V transformValue(U u) {
        return this.valueTransformer.transform(u);
    }

    /* access modifiers changed from: protected */
    public Map<K, V> transformMap(Map<? extends J, ? extends U> map) {
        if (map.isEmpty()) {
            return map;
        }
        LinkedMap linkedMap = new LinkedMap(map.size());
        for (Map.Entry next : map.entrySet()) {
            linkedMap.put(transformKey(next.getKey()), transformValue(next.getValue()));
        }
        return linkedMap;
    }

    /* access modifiers changed from: protected */
    public V checkSetValue(U u) {
        return this.valueTransformer.transform(u);
    }

    public V put(J j, U u) {
        return decorated().put(transformKey(j), transformValue(u));
    }

    public void putAll(Map<? extends J, ? extends U> map) {
        decorated().putAll(transformMap(map));
    }

    public void clear() {
        decorated().clear();
    }
}
