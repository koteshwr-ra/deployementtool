package org.apache.commons.collections4.multimap;

import java.util.Iterator;
import java.util.Map;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.FluentIterable;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.Transformer;

public class TransformedMultiValuedMap<K, V> extends AbstractMultiValuedMapDecorator<K, V> {
    private static final long serialVersionUID = 20150612;
    private final Transformer<? super K, ? extends K> keyTransformer;
    private final Transformer<? super V, ? extends V> valueTransformer;

    public static <K, V> TransformedMultiValuedMap<K, V> transformingMap(MultiValuedMap<K, V> multiValuedMap, Transformer<? super K, ? extends K> transformer, Transformer<? super V, ? extends V> transformer2) {
        return new TransformedMultiValuedMap<>(multiValuedMap, transformer, transformer2);
    }

    public static <K, V> TransformedMultiValuedMap<K, V> transformedMap(MultiValuedMap<K, V> multiValuedMap, Transformer<? super K, ? extends K> transformer, Transformer<? super V, ? extends V> transformer2) {
        TransformedMultiValuedMap<K, V> transformedMultiValuedMap = new TransformedMultiValuedMap<>(multiValuedMap, transformer, transformer2);
        if (!multiValuedMap.isEmpty()) {
            ArrayListValuedHashMap arrayListValuedHashMap = new ArrayListValuedHashMap(multiValuedMap);
            transformedMultiValuedMap.clear();
            transformedMultiValuedMap.putAll((MultiValuedMap<? extends K, ? extends V>) arrayListValuedHashMap);
        }
        return transformedMultiValuedMap;
    }

    protected TransformedMultiValuedMap(MultiValuedMap<K, V> multiValuedMap, Transformer<? super K, ? extends K> transformer, Transformer<? super V, ? extends V> transformer2) {
        super(multiValuedMap);
        this.keyTransformer = transformer;
        this.valueTransformer = transformer2;
    }

    /* access modifiers changed from: protected */
    public K transformKey(K k) {
        Transformer<? super K, ? extends K> transformer = this.keyTransformer;
        if (transformer == null) {
            return k;
        }
        return transformer.transform(k);
    }

    /* access modifiers changed from: protected */
    public V transformValue(V v) {
        Transformer<? super V, ? extends V> transformer = this.valueTransformer;
        if (transformer == null) {
            return v;
        }
        return transformer.transform(v);
    }

    public boolean put(K k, V v) {
        return decorated().put(transformKey(k), transformValue(v));
    }

    public boolean putAll(K k, Iterable<? extends V> iterable) {
        if (iterable != null) {
            Iterator<T> it = FluentIterable.of(iterable).transform(this.valueTransformer).iterator();
            return it.hasNext() && CollectionUtils.addAll(decorated().get(transformKey(k)), it);
        }
        throw new NullPointerException("Values must not be null.");
    }

    public boolean putAll(Map<? extends K, ? extends V> map) {
        if (map != null) {
            boolean z = false;
            for (Map.Entry next : map.entrySet()) {
                z |= put(next.getKey(), next.getValue());
            }
            return z;
        }
        throw new NullPointerException("Map must not be null.");
    }

    public boolean putAll(MultiValuedMap<? extends K, ? extends V> multiValuedMap) {
        if (multiValuedMap != null) {
            boolean z = false;
            for (Map.Entry next : multiValuedMap.entries()) {
                z |= put(next.getKey(), next.getValue());
            }
            return z;
        }
        throw new NullPointerException("Map must not be null.");
    }
}
