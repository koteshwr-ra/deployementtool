package org.apache.commons.collections4.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.Predicate;

public class PredicatedMap<K, V> extends AbstractInputCheckedMapDecorator<K, V> implements Serializable {
    private static final long serialVersionUID = 7412622456128415156L;
    protected final Predicate<? super K> keyPredicate;
    protected final Predicate<? super V> valuePredicate;

    public /* bridge */ /* synthetic */ Set entrySet() {
        return super.entrySet();
    }

    public static <K, V> PredicatedMap<K, V> predicatedMap(Map<K, V> map, Predicate<? super K> predicate, Predicate<? super V> predicate2) {
        return new PredicatedMap<>(map, predicate, predicate2);
    }

    protected PredicatedMap(Map<K, V> map, Predicate<? super K> predicate, Predicate<? super V> predicate2) {
        super(map);
        this.keyPredicate = predicate;
        this.valuePredicate = predicate2;
        for (Map.Entry next : map.entrySet()) {
            validate(next.getKey(), next.getValue());
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(this.map);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.map = (Map) objectInputStream.readObject();
    }

    /* access modifiers changed from: protected */
    public void validate(K k, V v) {
        Predicate<? super K> predicate = this.keyPredicate;
        if (predicate == null || predicate.evaluate(k)) {
            Predicate<? super V> predicate2 = this.valuePredicate;
            if (predicate2 != null && !predicate2.evaluate(v)) {
                throw new IllegalArgumentException("Cannot add value - Predicate rejected it");
            }
            return;
        }
        throw new IllegalArgumentException("Cannot add key - Predicate rejected it");
    }

    /* access modifiers changed from: protected */
    public V checkSetValue(V v) {
        if (this.valuePredicate.evaluate(v)) {
            return v;
        }
        throw new IllegalArgumentException("Cannot set value - Predicate rejected it");
    }

    /* access modifiers changed from: protected */
    public boolean isSetValueChecking() {
        return this.valuePredicate != null;
    }

    public V put(K k, V v) {
        validate(k, v);
        return this.map.put(k, v);
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry next : map.entrySet()) {
            validate(next.getKey(), next.getValue());
        }
        super.putAll(map);
    }
}
