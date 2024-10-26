package org.apache.commons.collections4.multiset;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.iterators.AbstractIteratorDecorator;
import org.apache.commons.collections4.multiset.AbstractMultiSet;

public abstract class AbstractMapMultiSet<E> extends AbstractMultiSet<E> {
    /* access modifiers changed from: private */
    public transient Map<E, MutableInteger> map;
    /* access modifiers changed from: private */
    public transient int modCount;
    private transient int size;

    static /* synthetic */ int access$210(AbstractMapMultiSet abstractMapMultiSet) {
        int i = abstractMapMultiSet.size;
        abstractMapMultiSet.size = i - 1;
        return i;
    }

    protected AbstractMapMultiSet() {
    }

    protected AbstractMapMultiSet(Map<E, MutableInteger> map2) {
        this.map = map2;
    }

    /* access modifiers changed from: protected */
    public Map<E, MutableInteger> getMap() {
        return this.map;
    }

    /* access modifiers changed from: protected */
    public void setMap(Map<E, MutableInteger> map2) {
        this.map = map2;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public int getCount(Object obj) {
        MutableInteger mutableInteger = this.map.get(obj);
        if (mutableInteger != null) {
            return mutableInteger.value;
        }
        return 0;
    }

    public boolean contains(Object obj) {
        return this.map.containsKey(obj);
    }

    public Iterator<E> iterator() {
        return new MapBasedMultiSetIterator(this);
    }

    private static class MapBasedMultiSetIterator<E> implements Iterator<E> {
        private boolean canRemove;
        private Map.Entry<E, MutableInteger> current = null;
        private final Iterator<Map.Entry<E, MutableInteger>> entryIterator;
        private int itemCount;
        private final int mods;
        private final AbstractMapMultiSet<E> parent;

        public MapBasedMultiSetIterator(AbstractMapMultiSet<E> abstractMapMultiSet) {
            this.parent = abstractMapMultiSet;
            this.entryIterator = abstractMapMultiSet.map.entrySet().iterator();
            this.mods = abstractMapMultiSet.modCount;
            this.canRemove = false;
        }

        public boolean hasNext() {
            return this.itemCount > 0 || this.entryIterator.hasNext();
        }

        public E next() {
            if (this.parent.modCount == this.mods) {
                if (this.itemCount == 0) {
                    Map.Entry<E, MutableInteger> next = this.entryIterator.next();
                    this.current = next;
                    this.itemCount = next.getValue().value;
                }
                this.canRemove = true;
                this.itemCount--;
                return this.current.getKey();
            }
            throw new ConcurrentModificationException();
        }

        public void remove() {
            if (this.parent.modCount != this.mods) {
                throw new ConcurrentModificationException();
            } else if (this.canRemove) {
                MutableInteger value = this.current.getValue();
                if (value.value > 1) {
                    value.value--;
                } else {
                    this.entryIterator.remove();
                }
                AbstractMapMultiSet.access$210(this.parent);
                this.canRemove = false;
            } else {
                throw new IllegalStateException();
            }
        }
    }

    public int add(E e, int i) {
        if (i >= 0) {
            MutableInteger mutableInteger = this.map.get(e);
            int i2 = mutableInteger != null ? mutableInteger.value : 0;
            if (i > 0) {
                this.modCount++;
                this.size += i;
                if (mutableInteger == null) {
                    this.map.put(e, new MutableInteger(i));
                } else {
                    mutableInteger.value += i;
                }
            }
            return i2;
        }
        throw new IllegalArgumentException("Occurrences must not be negative.");
    }

    public void clear() {
        this.modCount++;
        this.map.clear();
        this.size = 0;
    }

    public int remove(Object obj, int i) {
        if (i >= 0) {
            MutableInteger mutableInteger = this.map.get(obj);
            if (mutableInteger == null) {
                return 0;
            }
            int i2 = mutableInteger.value;
            if (i > 0) {
                this.modCount++;
                if (i < mutableInteger.value) {
                    mutableInteger.value -= i;
                    this.size -= i;
                } else {
                    this.map.remove(obj);
                    this.size -= mutableInteger.value;
                }
            }
            return i2;
        }
        throw new IllegalArgumentException("Occurrences must not be negative.");
    }

    protected static class MutableInteger {
        protected int value;

        MutableInteger(int i) {
            this.value = i;
        }

        public boolean equals(Object obj) {
            if ((obj instanceof MutableInteger) && ((MutableInteger) obj).value == this.value) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return this.value;
        }
    }

    /* access modifiers changed from: protected */
    public Iterator<E> createUniqueSetIterator() {
        return new UniqueSetIterator(getMap().keySet().iterator(), this);
    }

    /* access modifiers changed from: protected */
    public int uniqueElements() {
        return this.map.size();
    }

    /* access modifiers changed from: protected */
    public Iterator<MultiSet.Entry<E>> createEntrySetIterator() {
        return new EntrySetIterator(this.map.entrySet().iterator(), this);
    }

    protected static class UniqueSetIterator<E> extends AbstractIteratorDecorator<E> {
        protected boolean canRemove = false;
        protected E lastElement = null;
        protected final AbstractMapMultiSet<E> parent;

        protected UniqueSetIterator(Iterator<E> it, AbstractMapMultiSet<E> abstractMapMultiSet) {
            super(it);
            this.parent = abstractMapMultiSet;
        }

        public E next() {
            E next = super.next();
            this.lastElement = next;
            this.canRemove = true;
            return next;
        }

        public void remove() {
            if (this.canRemove) {
                int count = this.parent.getCount(this.lastElement);
                super.remove();
                this.parent.remove(this.lastElement, count);
                this.lastElement = null;
                this.canRemove = false;
                return;
            }
            throw new IllegalStateException("Iterator remove() can only be called once after next()");
        }
    }

    protected static class EntrySetIterator<E> implements Iterator<MultiSet.Entry<E>> {
        protected boolean canRemove = false;
        protected final Iterator<Map.Entry<E, MutableInteger>> decorated;
        protected MultiSet.Entry<E> last = null;
        protected final AbstractMapMultiSet<E> parent;

        protected EntrySetIterator(Iterator<Map.Entry<E, MutableInteger>> it, AbstractMapMultiSet<E> abstractMapMultiSet) {
            this.decorated = it;
            this.parent = abstractMapMultiSet;
        }

        public boolean hasNext() {
            return this.decorated.hasNext();
        }

        public MultiSet.Entry<E> next() {
            MultiSetEntry multiSetEntry = new MultiSetEntry(this.decorated.next());
            this.last = multiSetEntry;
            this.canRemove = true;
            return multiSetEntry;
        }

        public void remove() {
            if (this.canRemove) {
                this.decorated.remove();
                this.last = null;
                this.canRemove = false;
                return;
            }
            throw new IllegalStateException("Iterator remove() can only be called once after next()");
        }
    }

    protected static class MultiSetEntry<E> extends AbstractMultiSet.AbstractEntry<E> {
        protected final Map.Entry<E, MutableInteger> parentEntry;

        protected MultiSetEntry(Map.Entry<E, MutableInteger> entry) {
            this.parentEntry = entry;
        }

        public E getElement() {
            return this.parentEntry.getKey();
        }

        public int getCount() {
            return this.parentEntry.getValue().value;
        }
    }

    /* access modifiers changed from: protected */
    public void doWriteObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(this.map.size());
        for (Map.Entry next : this.map.entrySet()) {
            objectOutputStream.writeObject(next.getKey());
            objectOutputStream.writeInt(((MutableInteger) next.getValue()).value);
        }
    }

    /* access modifiers changed from: protected */
    public void doReadObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        int readInt = objectInputStream.readInt();
        for (int i = 0; i < readInt; i++) {
            Object readObject = objectInputStream.readObject();
            int readInt2 = objectInputStream.readInt();
            this.map.put(readObject, new MutableInteger(readInt2));
            this.size += readInt2;
        }
    }

    public Object[] toArray() {
        Object[] objArr = new Object[size()];
        int i = 0;
        for (Map.Entry next : this.map.entrySet()) {
            Object key = next.getKey();
            int i2 = ((MutableInteger) next.getValue()).value;
            while (i2 > 0) {
                objArr[i] = key;
                i2--;
                i++;
            }
        }
        return objArr;
    }

    public <T> T[] toArray(T[] tArr) {
        int size2 = size();
        if (tArr.length < size2) {
            tArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), size2);
        }
        int i = 0;
        for (Map.Entry next : this.map.entrySet()) {
            T key = next.getKey();
            int i2 = ((MutableInteger) next.getValue()).value;
            while (i2 > 0) {
                tArr[i] = key;
                i2--;
                i++;
            }
        }
        while (i < tArr.length) {
            tArr[i] = null;
            i++;
        }
        return tArr;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MultiSet)) {
            return false;
        }
        MultiSet multiSet = (MultiSet) obj;
        if (multiSet.size() != size()) {
            return false;
        }
        for (E next : this.map.keySet()) {
            if (multiSet.getCount(next) != getCount(next)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i;
        int i2 = 0;
        for (Map.Entry next : this.map.entrySet()) {
            Object key = next.getKey();
            MutableInteger mutableInteger = (MutableInteger) next.getValue();
            if (key == null) {
                i = 0;
            } else {
                i = key.hashCode();
            }
            i2 += mutableInteger.value ^ i;
        }
        return i2;
    }
}
