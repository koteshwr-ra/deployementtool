package org.apache.commons.collections4.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import org.apache.commons.collections4.iterators.UnmodifiableIterator;
import org.apache.commons.collections4.iterators.UnmodifiableListIterator;
import org.apache.commons.collections4.list.UnmodifiableList;
import org.apache.commons.collections4.map.AbstractLinkedMap;

public class LinkedMap<K, V> extends AbstractLinkedMap<K, V> implements Serializable, Cloneable {
    private static final long serialVersionUID = 9077234323521161066L;

    public LinkedMap() {
        super(16, 0.75f, 12);
    }

    public LinkedMap(int i) {
        super(i);
    }

    public LinkedMap(int i, float f) {
        super(i, f);
    }

    public LinkedMap(Map<? extends K, ? extends V> map) {
        super(map);
    }

    public LinkedMap<K, V> clone() {
        return (LinkedMap) super.clone();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        doWriteObject(objectOutputStream);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        doReadObject(objectInputStream);
    }

    public K get(int i) {
        return getEntry(i).getKey();
    }

    public V getValue(int i) {
        return getEntry(i).getValue();
    }

    public int indexOf(Object obj) {
        Object convertKey = convertKey(obj);
        AbstractLinkedMap.LinkEntry<K, V> linkEntry = this.header.after;
        int i = 0;
        while (linkEntry != this.header) {
            if (isEqualKey(convertKey, linkEntry.key)) {
                return i;
            }
            linkEntry = linkEntry.after;
            i++;
        }
        return -1;
    }

    public V remove(int i) {
        return remove(get(i));
    }

    public List<K> asList() {
        return new LinkedMapList(this);
    }

    static class LinkedMapList<K> extends AbstractList<K> {
        private final LinkedMap<K, ?> parent;

        LinkedMapList(LinkedMap<K, ?> linkedMap) {
            this.parent = linkedMap;
        }

        public int size() {
            return this.parent.size();
        }

        public K get(int i) {
            return this.parent.get(i);
        }

        public boolean contains(Object obj) {
            return this.parent.containsKey(obj);
        }

        public int indexOf(Object obj) {
            return this.parent.indexOf(obj);
        }

        public int lastIndexOf(Object obj) {
            return this.parent.indexOf(obj);
        }

        public boolean containsAll(Collection<?> collection) {
            return this.parent.keySet().containsAll(collection);
        }

        public K remove(int i) {
            throw new UnsupportedOperationException();
        }

        public boolean remove(Object obj) {
            throw new UnsupportedOperationException();
        }

        public boolean removeAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        public boolean retainAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        public void clear() {
            throw new UnsupportedOperationException();
        }

        public Object[] toArray() {
            return this.parent.keySet().toArray();
        }

        public <T> T[] toArray(T[] tArr) {
            return this.parent.keySet().toArray(tArr);
        }

        public Iterator<K> iterator() {
            return UnmodifiableIterator.unmodifiableIterator(this.parent.keySet().iterator());
        }

        public ListIterator<K> listIterator() {
            return UnmodifiableListIterator.umodifiableListIterator(super.listIterator());
        }

        public ListIterator<K> listIterator(int i) {
            return UnmodifiableListIterator.umodifiableListIterator(super.listIterator(i));
        }

        public List<K> subList(int i, int i2) {
            return UnmodifiableList.unmodifiableList(super.subList(i, i2));
        }
    }
}
