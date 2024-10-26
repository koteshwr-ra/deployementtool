package org.apache.commons.collections4.bidimap;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.OrderedBidiMap;
import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.ResettableIterator;
import org.apache.commons.collections4.SortedBidiMap;
import org.apache.commons.collections4.map.AbstractSortedMapDecorator;

public class DualTreeBidiMap<K, V> extends AbstractDualBidiMap<K, V> implements SortedBidiMap<K, V>, Serializable {
    private static final long serialVersionUID = 721969328361809L;
    private final Comparator<? super K> comparator;
    private final Comparator<? super V> valueComparator;

    public DualTreeBidiMap() {
        super(new TreeMap(), new TreeMap());
        this.comparator = null;
        this.valueComparator = null;
    }

    public DualTreeBidiMap(Map<? extends K, ? extends V> map) {
        super(new TreeMap(), new TreeMap());
        putAll(map);
        this.comparator = null;
        this.valueComparator = null;
    }

    public DualTreeBidiMap(Comparator<? super K> comparator2, Comparator<? super V> comparator3) {
        super(new TreeMap(comparator2), new TreeMap(comparator3));
        this.comparator = comparator2;
        this.valueComparator = comparator3;
    }

    protected DualTreeBidiMap(Map<K, V> map, Map<V, K> map2, BidiMap<V, K> bidiMap) {
        super(map, map2, bidiMap);
        this.comparator = ((SortedMap) map).comparator();
        this.valueComparator = ((SortedMap) map2).comparator();
    }

    /* access modifiers changed from: protected */
    public DualTreeBidiMap<V, K> createBidiMap(Map<V, K> map, Map<K, V> map2, BidiMap<K, V> bidiMap) {
        return new DualTreeBidiMap<>(map, map2, bidiMap);
    }

    public Comparator<? super K> comparator() {
        return ((SortedMap) this.normalMap).comparator();
    }

    public Comparator<? super V> valueComparator() {
        return ((SortedMap) this.reverseMap).comparator();
    }

    public K firstKey() {
        return ((SortedMap) this.normalMap).firstKey();
    }

    public K lastKey() {
        return ((SortedMap) this.normalMap).lastKey();
    }

    public K nextKey(K k) {
        if (isEmpty()) {
            return null;
        }
        if (this.normalMap instanceof OrderedMap) {
            return ((OrderedMap) this.normalMap).nextKey(k);
        }
        Iterator it = ((SortedMap) this.normalMap).tailMap(k).keySet().iterator();
        it.next();
        if (it.hasNext()) {
            return it.next();
        }
        return null;
    }

    public K previousKey(K k) {
        if (isEmpty()) {
            return null;
        }
        if (this.normalMap instanceof OrderedMap) {
            return ((OrderedMap) this.normalMap).previousKey(k);
        }
        SortedMap headMap = ((SortedMap) this.normalMap).headMap(k);
        if (headMap.isEmpty()) {
            return null;
        }
        return headMap.lastKey();
    }

    public OrderedMapIterator<K, V> mapIterator() {
        return new BidiOrderedMapIterator(this);
    }

    public SortedBidiMap<V, K> inverseSortedBidiMap() {
        return inverseBidiMap();
    }

    public OrderedBidiMap<V, K> inverseOrderedBidiMap() {
        return inverseBidiMap();
    }

    public SortedMap<K, V> headMap(K k) {
        return new ViewMap(this, ((SortedMap) this.normalMap).headMap(k));
    }

    public SortedMap<K, V> tailMap(K k) {
        return new ViewMap(this, ((SortedMap) this.normalMap).tailMap(k));
    }

    public SortedMap<K, V> subMap(K k, K k2) {
        return new ViewMap(this, ((SortedMap) this.normalMap).subMap(k, k2));
    }

    public SortedBidiMap<V, K> inverseBidiMap() {
        return (SortedBidiMap) super.inverseBidiMap();
    }

    protected static class ViewMap<K, V> extends AbstractSortedMapDecorator<K, V> {
        protected ViewMap(DualTreeBidiMap<K, V> dualTreeBidiMap, SortedMap<K, V> sortedMap) {
            super(new DualTreeBidiMap(sortedMap, dualTreeBidiMap.reverseMap, dualTreeBidiMap.inverseBidiMap));
        }

        public boolean containsValue(Object obj) {
            return decorated().normalMap.containsValue(obj);
        }

        public void clear() {
            Iterator it = keySet().iterator();
            while (it.hasNext()) {
                it.next();
                it.remove();
            }
        }

        public SortedMap<K, V> headMap(K k) {
            return new ViewMap(decorated(), super.headMap(k));
        }

        public SortedMap<K, V> tailMap(K k) {
            return new ViewMap(decorated(), super.tailMap(k));
        }

        public SortedMap<K, V> subMap(K k, K k2) {
            return new ViewMap(decorated(), super.subMap(k, k2));
        }

        /* access modifiers changed from: protected */
        public DualTreeBidiMap<K, V> decorated() {
            return (DualTreeBidiMap) super.decorated();
        }

        public K previousKey(K k) {
            return decorated().previousKey(k);
        }

        public K nextKey(K k) {
            return decorated().nextKey(k);
        }
    }

    protected static class BidiOrderedMapIterator<K, V> implements OrderedMapIterator<K, V>, ResettableIterator<K> {
        private ListIterator<Map.Entry<K, V>> iterator;
        private Map.Entry<K, V> last = null;
        private final AbstractDualBidiMap<K, V> parent;

        protected BidiOrderedMapIterator(AbstractDualBidiMap<K, V> abstractDualBidiMap) {
            this.parent = abstractDualBidiMap;
            this.iterator = new ArrayList(abstractDualBidiMap.entrySet()).listIterator();
        }

        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        public K next() {
            Map.Entry<K, V> next = this.iterator.next();
            this.last = next;
            return next.getKey();
        }

        public boolean hasPrevious() {
            return this.iterator.hasPrevious();
        }

        public K previous() {
            Map.Entry<K, V> previous = this.iterator.previous();
            this.last = previous;
            return previous.getKey();
        }

        public void remove() {
            this.iterator.remove();
            this.parent.remove(this.last.getKey());
            this.last = null;
        }

        public K getKey() {
            Map.Entry<K, V> entry = this.last;
            if (entry != null) {
                return entry.getKey();
            }
            throw new IllegalStateException("Iterator getKey() can only be called after next() and before remove()");
        }

        public V getValue() {
            Map.Entry<K, V> entry = this.last;
            if (entry != null) {
                return entry.getValue();
            }
            throw new IllegalStateException("Iterator getValue() can only be called after next() and before remove()");
        }

        public V setValue(V v) {
            if (this.last == null) {
                throw new IllegalStateException("Iterator setValue() can only be called after next() and before remove()");
            } else if (!this.parent.reverseMap.containsKey(v) || this.parent.reverseMap.get(v) == this.last.getKey()) {
                V put = this.parent.put(this.last.getKey(), v);
                this.last.setValue(v);
                return put;
            } else {
                throw new IllegalArgumentException("Cannot use setValue() when the object being set is already in the map");
            }
        }

        public void reset() {
            this.iterator = new ArrayList(this.parent.entrySet()).listIterator();
            this.last = null;
        }

        public String toString() {
            if (this.last == null) {
                return "MapIterator[]";
            }
            return "MapIterator[" + getKey() + "=" + getValue() + "]";
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(this.normalMap);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.normalMap = new TreeMap(this.comparator);
        this.reverseMap = new TreeMap(this.valueComparator);
        putAll((Map) objectInputStream.readObject());
    }
}
