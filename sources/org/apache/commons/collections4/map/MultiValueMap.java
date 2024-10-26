package org.apache.commons.collections4.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.FunctorException;
import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.iterators.EmptyIterator;
import org.apache.commons.collections4.iterators.IteratorChain;
import org.apache.commons.collections4.iterators.LazyIteratorChain;
import org.apache.commons.collections4.iterators.TransformIterator;

@Deprecated
public class MultiValueMap<K, V> extends AbstractMapDecorator<K, Object> implements MultiMap<K, V>, Serializable {
    private static final long serialVersionUID = -2214159910087182007L;
    private final Factory<? extends Collection<V>> collectionFactory;
    private transient Collection<V> valuesView;

    public static <K, V> MultiValueMap<K, V> multiValueMap(Map<K, ? super Collection<V>> map) {
        return multiValueMap(map, ArrayList.class);
    }

    public static <K, V, C extends Collection<V>> MultiValueMap<K, V> multiValueMap(Map<K, ? super C> map, Class<C> cls) {
        return new MultiValueMap<>(map, new ReflectionFactory(cls));
    }

    public static <K, V, C extends Collection<V>> MultiValueMap<K, V> multiValueMap(Map<K, ? super C> map, Factory<C> factory) {
        return new MultiValueMap<>(map, factory);
    }

    public MultiValueMap() {
        this(new HashMap(), new ReflectionFactory(ArrayList.class));
    }

    protected <C extends Collection<V>> MultiValueMap(Map<K, ? super C> map, Factory<C> factory) {
        super(map);
        if (factory != null) {
            this.collectionFactory = factory;
            return;
        }
        throw new IllegalArgumentException("The factory must not be null");
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(this.map);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.map = (Map) objectInputStream.readObject();
    }

    public void clear() {
        decorated().clear();
    }

    public boolean removeMapping(Object obj, Object obj2) {
        Collection collection = getCollection(obj);
        if (collection == null || !collection.remove(obj2)) {
            return false;
        }
        if (!collection.isEmpty()) {
            return true;
        }
        remove(obj);
        return true;
    }

    public boolean containsValue(Object obj) {
        Set<Map.Entry> entrySet = decorated().entrySet();
        if (entrySet == null) {
            return false;
        }
        for (Map.Entry value : entrySet) {
            if (((Collection) value.getValue()).contains(obj)) {
                return true;
            }
        }
        return false;
    }

    public Object put(K k, Object obj) {
        Collection collection = getCollection(k);
        boolean z = true;
        if (collection == null) {
            Collection createCollection = createCollection(1);
            createCollection.add(obj);
            if (createCollection.size() > 0) {
                decorated().put(k, createCollection);
            } else {
                z = false;
            }
        } else {
            z = collection.add(obj);
        }
        if (z) {
            return obj;
        }
        return null;
    }

    public void putAll(Map<? extends K, ?> map) {
        if (map instanceof MultiMap) {
            for (Map.Entry entry : ((MultiMap) map).entrySet()) {
                putAll(entry.getKey(), (Collection) entry.getValue());
            }
            return;
        }
        for (Map.Entry next : map.entrySet()) {
            put(next.getKey(), next.getValue());
        }
    }

    public Set<Map.Entry<K, Object>> entrySet() {
        return super.entrySet();
    }

    public Collection<Object> values() {
        Collection<V> collection = this.valuesView;
        if (collection != null) {
            return collection;
        }
        Values values = new Values();
        this.valuesView = values;
        return values;
    }

    public boolean containsValue(Object obj, Object obj2) {
        Collection collection = getCollection(obj);
        if (collection == null) {
            return false;
        }
        return collection.contains(obj2);
    }

    public Collection<V> getCollection(Object obj) {
        return (Collection) decorated().get(obj);
    }

    public int size(Object obj) {
        Collection collection = getCollection(obj);
        if (collection == null) {
            return 0;
        }
        return collection.size();
    }

    public boolean putAll(K k, Collection<V> collection) {
        if (collection == null || collection.size() == 0) {
            return false;
        }
        Collection collection2 = getCollection(k);
        if (collection2 != null) {
            return collection2.addAll(collection);
        }
        Collection createCollection = createCollection(collection.size());
        createCollection.addAll(collection);
        if (createCollection.size() <= 0) {
            return false;
        }
        decorated().put(k, createCollection);
        return true;
    }

    public Iterator<V> iterator(Object obj) {
        if (!containsKey(obj)) {
            return EmptyIterator.emptyIterator();
        }
        return new ValuesIterator(obj);
    }

    public Iterator<Map.Entry<K, V>> iterator() {
        final Iterator it = new ArrayList(keySet()).iterator();
        return new LazyIteratorChain<Map.Entry<K, V>>() {
            /* access modifiers changed from: protected */
            public Iterator<? extends Map.Entry<K, V>> nextIterator(int i) {
                if (!it.hasNext()) {
                    return null;
                }
                final Object next = it.next();
                return new TransformIterator(new ValuesIterator(next), new Transformer<V, Map.Entry<K, V>>() {
                    public Map.Entry<K, V> transform(final V v) {
                        return new Map.Entry<K, V>() {
                            public K getKey() {
                                return next;
                            }

                            public V getValue() {
                                return v;
                            }

                            public V setValue(V v) {
                                throw new UnsupportedOperationException();
                            }
                        };
                    }
                });
            }
        };
    }

    public int totalSize() {
        int i = 0;
        for (Object size : decorated().values()) {
            i += CollectionUtils.size(size);
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public Collection<V> createCollection(int i) {
        return (Collection) this.collectionFactory.create();
    }

    private class Values extends AbstractCollection<V> {
        private Values() {
        }

        public Iterator<V> iterator() {
            IteratorChain iteratorChain = new IteratorChain();
            for (Object valuesIterator : MultiValueMap.this.keySet()) {
                iteratorChain.addIterator(new ValuesIterator(valuesIterator));
            }
            return iteratorChain;
        }

        public int size() {
            return MultiValueMap.this.totalSize();
        }

        public void clear() {
            MultiValueMap.this.clear();
        }
    }

    private class ValuesIterator implements Iterator<V> {
        private final Iterator<V> iterator;
        private final Object key;
        private final Collection<V> values;

        public ValuesIterator(Object obj) {
            this.key = obj;
            Collection<V> collection = MultiValueMap.this.getCollection(obj);
            this.values = collection;
            this.iterator = collection.iterator();
        }

        public void remove() {
            this.iterator.remove();
            if (this.values.isEmpty()) {
                MultiValueMap.this.remove(this.key);
            }
        }

        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        public V next() {
            return this.iterator.next();
        }
    }

    private static class ReflectionFactory<T extends Collection<?>> implements Factory<T>, Serializable {
        private static final long serialVersionUID = 2986114157496788874L;
        private final Class<T> clazz;

        public ReflectionFactory(Class<T> cls) {
            this.clazz = cls;
        }

        public T create() {
            try {
                return (Collection) this.clazz.newInstance();
            } catch (Exception e) {
                throw new FunctorException("Cannot instantiate class: " + this.clazz, e);
            }
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            objectInputStream.defaultReadObject();
            Class<T> cls = this.clazz;
            if (cls != null && !Collection.class.isAssignableFrom(cls)) {
                throw new UnsupportedOperationException();
            }
        }
    }
}
