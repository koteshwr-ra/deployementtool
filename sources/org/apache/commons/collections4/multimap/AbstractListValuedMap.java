package org.apache.commons.collections4.multimap;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.ListValuedMap;

public abstract class AbstractListValuedMap<K, V> extends AbstractMultiValuedMap<K, V> implements ListValuedMap<K, V> {
    /* access modifiers changed from: protected */
    public abstract List<V> createCollection();

    protected AbstractListValuedMap() {
    }

    protected AbstractListValuedMap(Map<K, ? extends List<V>> map) {
        super(map);
    }

    /* access modifiers changed from: protected */
    public Map<K, List<V>> getMap() {
        return super.getMap();
    }

    public List<V> get(K k) {
        return wrappedCollection((Object) k);
    }

    /* access modifiers changed from: package-private */
    public List<V> wrappedCollection(K k) {
        return new WrappedList(k);
    }

    public List<V> remove(Object obj) {
        return ListUtils.emptyIfNull((List) getMap().remove(obj));
    }

    private class WrappedList extends AbstractMultiValuedMap<K, V>.WrappedCollection implements List<V> {
        public WrappedList(K k) {
            super(k);
        }

        /* access modifiers changed from: protected */
        public List<V> getMapping() {
            return (List) AbstractListValuedMap.this.getMap().get(this.key);
        }

        public void add(int i, V v) {
            List mapping = getMapping();
            if (mapping == null) {
                mapping = AbstractListValuedMap.this.createCollection();
                AbstractListValuedMap.this.getMap().put(this.key, mapping);
            }
            mapping.add(i, v);
        }

        public boolean addAll(int i, Collection<? extends V> collection) {
            List mapping = getMapping();
            if (mapping != null) {
                return mapping.addAll(i, collection);
            }
            List createCollection = AbstractListValuedMap.this.createCollection();
            boolean addAll = createCollection.addAll(i, collection);
            if (addAll) {
                AbstractListValuedMap.this.getMap().put(this.key, createCollection);
            }
            return addAll;
        }

        public V get(int i) {
            return ListUtils.emptyIfNull(getMapping()).get(i);
        }

        public int indexOf(Object obj) {
            return ListUtils.emptyIfNull(getMapping()).indexOf(obj);
        }

        public int lastIndexOf(Object obj) {
            return ListUtils.emptyIfNull(getMapping()).lastIndexOf(obj);
        }

        public ListIterator<V> listIterator() {
            return new ValuesListIterator(this.key);
        }

        public ListIterator<V> listIterator(int i) {
            return new ValuesListIterator(this.key, i);
        }

        public V remove(int i) {
            List emptyIfNull = ListUtils.emptyIfNull(getMapping());
            V remove = emptyIfNull.remove(i);
            if (emptyIfNull.isEmpty()) {
                AbstractListValuedMap.this.remove(this.key);
            }
            return remove;
        }

        public V set(int i, V v) {
            return ListUtils.emptyIfNull(getMapping()).set(i, v);
        }

        public List<V> subList(int i, int i2) {
            return ListUtils.emptyIfNull(getMapping()).subList(i, i2);
        }

        public boolean equals(Object obj) {
            List mapping = getMapping();
            if (mapping == null) {
                return Collections.emptyList().equals(obj);
            }
            if (!(obj instanceof List)) {
                return false;
            }
            return ListUtils.isEqualList(mapping, (List) obj);
        }

        public int hashCode() {
            return ListUtils.hashCodeForList(getMapping());
        }
    }

    private class ValuesListIterator implements ListIterator<V> {
        private ListIterator<V> iterator;
        private final K key;
        private List<V> values;

        public ValuesListIterator(K k) {
            this.key = k;
            List<V> emptyIfNull = ListUtils.emptyIfNull((List) AbstractListValuedMap.this.getMap().get(k));
            this.values = emptyIfNull;
            this.iterator = emptyIfNull.listIterator();
        }

        public ValuesListIterator(K k, int i) {
            this.key = k;
            List<V> emptyIfNull = ListUtils.emptyIfNull((List) AbstractListValuedMap.this.getMap().get(k));
            this.values = emptyIfNull;
            this.iterator = emptyIfNull.listIterator(i);
        }

        public void add(V v) {
            if (AbstractListValuedMap.this.getMap().get(this.key) == null) {
                List<V> createCollection = AbstractListValuedMap.this.createCollection();
                AbstractListValuedMap.this.getMap().put(this.key, createCollection);
                this.values = createCollection;
                this.iterator = createCollection.listIterator();
            }
            this.iterator.add(v);
        }

        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        public boolean hasPrevious() {
            return this.iterator.hasPrevious();
        }

        public V next() {
            return this.iterator.next();
        }

        public int nextIndex() {
            return this.iterator.nextIndex();
        }

        public V previous() {
            return this.iterator.previous();
        }

        public int previousIndex() {
            return this.iterator.previousIndex();
        }

        public void remove() {
            this.iterator.remove();
            if (this.values.isEmpty()) {
                AbstractListValuedMap.this.getMap().remove(this.key);
            }
        }

        public void set(V v) {
            this.iterator.set(v);
        }
    }
}
