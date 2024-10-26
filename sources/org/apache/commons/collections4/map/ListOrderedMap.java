package org.apache.commons.collections4.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.ResettableIterator;
import org.apache.commons.collections4.iterators.AbstractUntypedIteratorDecorator;
import org.apache.commons.collections4.keyvalue.AbstractMapEntry;
import org.apache.commons.collections4.list.UnmodifiableList;

public class ListOrderedMap<K, V> extends AbstractMapDecorator<K, V> implements OrderedMap<K, V>, Serializable {
    private static final long serialVersionUID = 2728177751851003750L;
    /* access modifiers changed from: private */
    public final List<K> insertOrder;

    public static <K, V> ListOrderedMap<K, V> listOrderedMap(Map<K, V> map) {
        return new ListOrderedMap<>(map);
    }

    public ListOrderedMap() {
        this(new HashMap());
    }

    protected ListOrderedMap(Map<K, V> map) {
        super(map);
        ArrayList arrayList = new ArrayList();
        this.insertOrder = arrayList;
        arrayList.addAll(decorated().keySet());
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(this.map);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.map = (Map) objectInputStream.readObject();
    }

    public OrderedMapIterator<K, V> mapIterator() {
        return new ListOrderedMapIterator(this);
    }

    public K firstKey() {
        if (size() != 0) {
            return this.insertOrder.get(0);
        }
        throw new NoSuchElementException("Map is empty");
    }

    public K lastKey() {
        if (size() != 0) {
            return this.insertOrder.get(size() - 1);
        }
        throw new NoSuchElementException("Map is empty");
    }

    public K nextKey(Object obj) {
        int indexOf = this.insertOrder.indexOf(obj);
        if (indexOf < 0 || indexOf >= size() - 1) {
            return null;
        }
        return this.insertOrder.get(indexOf + 1);
    }

    public K previousKey(Object obj) {
        int indexOf = this.insertOrder.indexOf(obj);
        if (indexOf > 0) {
            return this.insertOrder.get(indexOf - 1);
        }
        return null;
    }

    public V put(K k, V v) {
        if (decorated().containsKey(k)) {
            return decorated().put(k, v);
        }
        V put = decorated().put(k, v);
        this.insertOrder.add(k);
        return put;
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry next : map.entrySet()) {
            put(next.getKey(), next.getValue());
        }
    }

    public void putAll(int i, Map<? extends K, ? extends V> map) {
        if (i < 0 || i > this.insertOrder.size()) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + this.insertOrder.size());
        }
        for (Map.Entry next : map.entrySet()) {
            boolean containsKey = containsKey(next.getKey());
            put(i, next.getKey(), next.getValue());
            if (containsKey) {
                i = indexOf(next.getKey());
            }
            i++;
        }
    }

    public V remove(Object obj) {
        if (!decorated().containsKey(obj)) {
            return null;
        }
        V remove = decorated().remove(obj);
        this.insertOrder.remove(obj);
        return remove;
    }

    public void clear() {
        decorated().clear();
        this.insertOrder.clear();
    }

    public Set<K> keySet() {
        return new KeySetView(this);
    }

    public List<K> keyList() {
        return UnmodifiableList.unmodifiableList(this.insertOrder);
    }

    public Collection<V> values() {
        return new ValuesView(this);
    }

    public List<V> valueList() {
        return new ValuesView(this);
    }

    public Set<Map.Entry<K, V>> entrySet() {
        return new EntrySetView(this, this.insertOrder);
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        boolean z = true;
        for (Map.Entry entry : entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (z) {
                z = false;
            } else {
                sb.append(", ");
            }
            if (key == this) {
                key = "(this Map)";
            }
            sb.append(key);
            sb.append('=');
            if (value == this) {
                value = "(this Map)";
            }
            sb.append(value);
        }
        sb.append('}');
        return sb.toString();
    }

    public K get(int i) {
        return this.insertOrder.get(i);
    }

    public V getValue(int i) {
        return get(this.insertOrder.get(i));
    }

    public int indexOf(Object obj) {
        return this.insertOrder.indexOf(obj);
    }

    public V setValue(int i, V v) {
        return put(this.insertOrder.get(i), v);
    }

    public V put(int i, K k, V v) {
        if (i < 0 || i > this.insertOrder.size()) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + this.insertOrder.size());
        }
        Map decorated = decorated();
        if (decorated.containsKey(k)) {
            V remove = decorated.remove(k);
            int indexOf = this.insertOrder.indexOf(k);
            this.insertOrder.remove(indexOf);
            if (indexOf < i) {
                i--;
            }
            this.insertOrder.add(i, k);
            decorated.put(k, v);
            return remove;
        }
        this.insertOrder.add(i, k);
        decorated.put(k, v);
        return null;
    }

    public V remove(int i) {
        return remove(get(i));
    }

    public List<K> asList() {
        return keyList();
    }

    static class ValuesView<V> extends AbstractList<V> {
        private final ListOrderedMap<Object, V> parent;

        ValuesView(ListOrderedMap<?, V> listOrderedMap) {
            this.parent = listOrderedMap;
        }

        public int size() {
            return this.parent.size();
        }

        public boolean contains(Object obj) {
            return this.parent.containsValue(obj);
        }

        public void clear() {
            this.parent.clear();
        }

        public Iterator<V> iterator() {
            return new AbstractUntypedIteratorDecorator<Map.Entry<Object, V>, V>(this.parent.entrySet().iterator()) {
                public V next() {
                    return ((Map.Entry) getIterator().next()).getValue();
                }
            };
        }

        public V get(int i) {
            return this.parent.getValue(i);
        }

        public V set(int i, V v) {
            return this.parent.setValue(i, v);
        }

        public V remove(int i) {
            return this.parent.remove(i);
        }
    }

    static class KeySetView<K> extends AbstractSet<K> {
        private final ListOrderedMap<K, Object> parent;

        KeySetView(ListOrderedMap<K, ?> listOrderedMap) {
            this.parent = listOrderedMap;
        }

        public int size() {
            return this.parent.size();
        }

        public boolean contains(Object obj) {
            return this.parent.containsKey(obj);
        }

        public void clear() {
            this.parent.clear();
        }

        public Iterator<K> iterator() {
            return new AbstractUntypedIteratorDecorator<Map.Entry<K, Object>, K>(this.parent.entrySet().iterator()) {
                public K next() {
                    return ((Map.Entry) getIterator().next()).getKey();
                }
            };
        }
    }

    static class EntrySetView<K, V> extends AbstractSet<Map.Entry<K, V>> {
        private Set<Map.Entry<K, V>> entrySet;
        private final List<K> insertOrder;
        private final ListOrderedMap<K, V> parent;

        public EntrySetView(ListOrderedMap<K, V> listOrderedMap, List<K> list) {
            this.parent = listOrderedMap;
            this.insertOrder = list;
        }

        private Set<Map.Entry<K, V>> getEntrySet() {
            if (this.entrySet == null) {
                this.entrySet = this.parent.decorated().entrySet();
            }
            return this.entrySet;
        }

        public int size() {
            return this.parent.size();
        }

        public boolean isEmpty() {
            return this.parent.isEmpty();
        }

        public boolean contains(Object obj) {
            return getEntrySet().contains(obj);
        }

        public boolean containsAll(Collection<?> collection) {
            return getEntrySet().containsAll(collection);
        }

        public boolean remove(Object obj) {
            if (!(obj instanceof Map.Entry) || !getEntrySet().contains(obj)) {
                return false;
            }
            this.parent.remove(((Map.Entry) obj).getKey());
            return true;
        }

        public void clear() {
            this.parent.clear();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            return getEntrySet().equals(obj);
        }

        public int hashCode() {
            return getEntrySet().hashCode();
        }

        public String toString() {
            return getEntrySet().toString();
        }

        public Iterator<Map.Entry<K, V>> iterator() {
            return new ListOrderedIterator(this.parent, this.insertOrder);
        }
    }

    static class ListOrderedIterator<K, V> extends AbstractUntypedIteratorDecorator<K, Map.Entry<K, V>> {
        private K last = null;
        private final ListOrderedMap<K, V> parent;

        ListOrderedIterator(ListOrderedMap<K, V> listOrderedMap, List<K> list) {
            super(list.iterator());
            this.parent = listOrderedMap;
        }

        public Map.Entry<K, V> next() {
            K next = getIterator().next();
            this.last = next;
            return new ListOrderedMapEntry(this.parent, next);
        }

        public void remove() {
            super.remove();
            this.parent.decorated().remove(this.last);
        }
    }

    static class ListOrderedMapEntry<K, V> extends AbstractMapEntry<K, V> {
        private final ListOrderedMap<K, V> parent;

        ListOrderedMapEntry(ListOrderedMap<K, V> listOrderedMap, K k) {
            super(k, null);
            this.parent = listOrderedMap;
        }

        public V getValue() {
            return this.parent.get(getKey());
        }

        public V setValue(V v) {
            return this.parent.decorated().put(getKey(), v);
        }
    }

    static class ListOrderedMapIterator<K, V> implements OrderedMapIterator<K, V>, ResettableIterator<K> {
        private ListIterator<K> iterator;
        private K last = null;
        private final ListOrderedMap<K, V> parent;
        private boolean readable = false;

        ListOrderedMapIterator(ListOrderedMap<K, V> listOrderedMap) {
            this.parent = listOrderedMap;
            this.iterator = listOrderedMap.insertOrder.listIterator();
        }

        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        public K next() {
            K next = this.iterator.next();
            this.last = next;
            this.readable = true;
            return next;
        }

        public boolean hasPrevious() {
            return this.iterator.hasPrevious();
        }

        public K previous() {
            K previous = this.iterator.previous();
            this.last = previous;
            this.readable = true;
            return previous;
        }

        public void remove() {
            if (this.readable) {
                this.iterator.remove();
                this.parent.map.remove(this.last);
                this.readable = false;
                return;
            }
            throw new IllegalStateException("remove() can only be called once after next()");
        }

        public K getKey() {
            if (this.readable) {
                return this.last;
            }
            throw new IllegalStateException("getKey() can only be called after next() and before remove()");
        }

        public V getValue() {
            if (this.readable) {
                return this.parent.get(this.last);
            }
            throw new IllegalStateException("getValue() can only be called after next() and before remove()");
        }

        public V setValue(V v) {
            if (this.readable) {
                return this.parent.map.put(this.last, v);
            }
            throw new IllegalStateException("setValue() can only be called after next() and before remove()");
        }

        public void reset() {
            this.iterator = this.parent.insertOrder.listIterator();
            this.last = null;
            this.readable = false;
        }

        public String toString() {
            if (!this.readable) {
                return "Iterator[]";
            }
            return "Iterator[" + getKey() + "=" + getValue() + "]";
        }
    }
}
