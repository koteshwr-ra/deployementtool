package org.apache.commons.collections4.map;

import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.apache.commons.collections4.BoundedMap;
import org.apache.commons.collections4.KeyValue;
import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.ResettableIterator;
import org.apache.commons.collections4.iterators.SingletonIterator;
import org.apache.commons.collections4.keyvalue.TiedMapEntry;

public class SingletonMap<K, V> implements OrderedMap<K, V>, BoundedMap<K, V>, KeyValue<K, V>, Serializable, Cloneable {
    private static final long serialVersionUID = -8931271118676803261L;
    private final K key;
    private V value;

    public boolean isEmpty() {
        return false;
    }

    public boolean isFull() {
        return true;
    }

    public int maxSize() {
        return 1;
    }

    public K nextKey(K k) {
        return null;
    }

    public K previousKey(K k) {
        return null;
    }

    public int size() {
        return 1;
    }

    public SingletonMap() {
        this.key = null;
    }

    public SingletonMap(K k, V v) {
        this.key = k;
        this.value = v;
    }

    public SingletonMap(KeyValue<K, V> keyValue) {
        this.key = keyValue.getKey();
        this.value = keyValue.getValue();
    }

    public SingletonMap(Map.Entry<? extends K, ? extends V> entry) {
        this.key = entry.getKey();
        this.value = entry.getValue();
    }

    public SingletonMap(Map<? extends K, ? extends V> map) {
        if (map.size() == 1) {
            Map.Entry next = map.entrySet().iterator().next();
            this.key = next.getKey();
            this.value = next.getValue();
            return;
        }
        throw new IllegalArgumentException("The map size must be 1");
    }

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }

    public V setValue(V v) {
        V v2 = this.value;
        this.value = v;
        return v2;
    }

    public V get(Object obj) {
        if (isEqualKey(obj)) {
            return this.value;
        }
        return null;
    }

    public boolean containsKey(Object obj) {
        return isEqualKey(obj);
    }

    public boolean containsValue(Object obj) {
        return isEqualValue(obj);
    }

    public V put(K k, V v) {
        if (isEqualKey(k)) {
            return setValue(v);
        }
        throw new IllegalArgumentException("Cannot put new key/value pair - Map is fixed size singleton");
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        int size = map.size();
        if (size == 0) {
            return;
        }
        if (size == 1) {
            Map.Entry next = map.entrySet().iterator().next();
            put(next.getKey(), next.getValue());
            return;
        }
        throw new IllegalArgumentException("The map size must be 0 or 1");
    }

    public V remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        throw new UnsupportedOperationException();
    }

    public Set<Map.Entry<K, V>> entrySet() {
        return Collections.singleton(new TiedMapEntry(this, getKey()));
    }

    public Set<K> keySet() {
        return Collections.singleton(this.key);
    }

    public Collection<V> values() {
        return new SingletonValues(this);
    }

    public OrderedMapIterator<K, V> mapIterator() {
        return new SingletonMapIterator(this);
    }

    public K firstKey() {
        return getKey();
    }

    public K lastKey() {
        return getKey();
    }

    /* access modifiers changed from: protected */
    public boolean isEqualKey(Object obj) {
        if (obj == null) {
            return getKey() == null;
        }
        return obj.equals(getKey());
    }

    /* access modifiers changed from: protected */
    public boolean isEqualValue(Object obj) {
        if (obj == null) {
            return getValue() == null;
        }
        return obj.equals(getValue());
    }

    static class SingletonMapIterator<K, V> implements OrderedMapIterator<K, V>, ResettableIterator<K> {
        private boolean canGetSet = false;
        private boolean hasNext = true;
        private final SingletonMap<K, V> parent;

        SingletonMapIterator(SingletonMap<K, V> singletonMap) {
            this.parent = singletonMap;
        }

        public boolean hasNext() {
            return this.hasNext;
        }

        public K next() {
            if (this.hasNext) {
                this.hasNext = false;
                this.canGetSet = true;
                return this.parent.getKey();
            }
            throw new NoSuchElementException("No next() entry in the iteration");
        }

        public boolean hasPrevious() {
            return !this.hasNext;
        }

        public K previous() {
            if (!this.hasNext) {
                this.hasNext = true;
                return this.parent.getKey();
            }
            throw new NoSuchElementException("No previous() entry in the iteration");
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public K getKey() {
            if (this.canGetSet) {
                return this.parent.getKey();
            }
            throw new IllegalStateException("getKey() can only be called after next() and before remove()");
        }

        public V getValue() {
            if (this.canGetSet) {
                return this.parent.getValue();
            }
            throw new IllegalStateException("getValue() can only be called after next() and before remove()");
        }

        public V setValue(V v) {
            if (this.canGetSet) {
                return this.parent.setValue(v);
            }
            throw new IllegalStateException("setValue() can only be called after next() and before remove()");
        }

        public void reset() {
            this.hasNext = true;
        }

        public String toString() {
            if (this.hasNext) {
                return "Iterator[]";
            }
            return "Iterator[" + getKey() + "=" + getValue() + "]";
        }
    }

    static class SingletonValues<V> extends AbstractSet<V> implements Serializable {
        private static final long serialVersionUID = -3689524741863047872L;
        private final SingletonMap<?, V> parent;

        public boolean isEmpty() {
            return false;
        }

        public int size() {
            return 1;
        }

        SingletonValues(SingletonMap<?, V> singletonMap) {
            this.parent = singletonMap;
        }

        public boolean contains(Object obj) {
            return this.parent.containsValue(obj);
        }

        public void clear() {
            throw new UnsupportedOperationException();
        }

        public Iterator<V> iterator() {
            return new SingletonIterator(this.parent.getValue(), false);
        }
    }

    public SingletonMap<K, V> clone() {
        try {
            return (SingletonMap) super.clone();
        } catch (CloneNotSupportedException unused) {
            throw new InternalError();
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        if (map.size() != 1) {
            return false;
        }
        Map.Entry entry = (Map.Entry) map.entrySet().iterator().next();
        if (!isEqualKey(entry.getKey()) || !isEqualValue(entry.getValue())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = getKey() == null ? 0 : getKey().hashCode();
        if (getValue() != null) {
            i = getValue().hashCode();
        }
        return hashCode ^ i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append('{');
        Object obj = "(this Map)";
        sb.append(getKey() == this ? obj : getKey());
        sb.append('=');
        if (getValue() != this) {
            obj = getValue();
        }
        sb.append(obj);
        sb.append('}');
        return sb.toString();
    }
}
