package org.apache.commons.collections4.bag;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import okhttp3.HttpUrl;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.set.UnmodifiableSet;

public abstract class AbstractMapBag<E> implements Bag<E> {
    /* access modifiers changed from: private */
    public transient Map<E, MutableInteger> map;
    /* access modifiers changed from: private */
    public transient int modCount;
    private int size;
    private transient Set<E> uniqueSet;

    static /* synthetic */ int access$210(AbstractMapBag abstractMapBag) {
        int i = abstractMapBag.size;
        abstractMapBag.size = i - 1;
        return i;
    }

    protected AbstractMapBag() {
    }

    protected AbstractMapBag(Map<E, MutableInteger> map2) {
        this.map = map2;
    }

    /* access modifiers changed from: protected */
    public Map<E, MutableInteger> getMap() {
        return this.map;
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

    public boolean containsAll(Collection<?> collection) {
        if (collection instanceof Bag) {
            return containsAll((Bag<?>) (Bag) collection);
        }
        return containsAll((Bag<?>) new HashBag(collection));
    }

    /* access modifiers changed from: package-private */
    public boolean containsAll(Bag<?> bag) {
        for (Object next : bag.uniqueSet()) {
            if (getCount(next) < bag.getCount(next)) {
                return false;
            }
        }
        return true;
    }

    public Iterator<E> iterator() {
        return new BagIterator(this);
    }

    static class BagIterator<E> implements Iterator<E> {
        private boolean canRemove;
        private Map.Entry<E, MutableInteger> current = null;
        private final Iterator<Map.Entry<E, MutableInteger>> entryIterator;
        private int itemCount;
        private final int mods;
        private final AbstractMapBag<E> parent;

        public BagIterator(AbstractMapBag<E> abstractMapBag) {
            this.parent = abstractMapBag;
            this.entryIterator = abstractMapBag.map.entrySet().iterator();
            this.mods = abstractMapBag.modCount;
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
                AbstractMapBag.access$210(this.parent);
                this.canRemove = false;
            } else {
                throw new IllegalStateException();
            }
        }
    }

    public boolean add(E e) {
        return add(e, 1);
    }

    public boolean add(E e, int i) {
        this.modCount++;
        if (i > 0) {
            MutableInteger mutableInteger = this.map.get(e);
            this.size += i;
            if (mutableInteger == null) {
                this.map.put(e, new MutableInteger(i));
                return true;
            }
            mutableInteger.value += i;
        }
        return false;
    }

    public boolean addAll(Collection<? extends E> collection) {
        Iterator<? extends E> it = collection.iterator();
        while (true) {
            boolean z = false;
            while (true) {
                if (!it.hasNext()) {
                    return z;
                }
                boolean add = add(it.next());
                if (z || add) {
                    z = true;
                }
            }
        }
    }

    public void clear() {
        this.modCount++;
        this.map.clear();
        this.size = 0;
    }

    public boolean remove(Object obj) {
        MutableInteger mutableInteger = this.map.get(obj);
        if (mutableInteger == null) {
            return false;
        }
        this.modCount++;
        this.map.remove(obj);
        this.size -= mutableInteger.value;
        return true;
    }

    public boolean remove(Object obj, int i) {
        MutableInteger mutableInteger = this.map.get(obj);
        if (mutableInteger == null || i <= 0) {
            return false;
        }
        this.modCount++;
        if (i < mutableInteger.value) {
            mutableInteger.value -= i;
            this.size -= i;
        } else {
            this.map.remove(obj);
            this.size -= mutableInteger.value;
        }
        return true;
    }

    public boolean removeAll(Collection<?> collection) {
        if (collection == null) {
            return false;
        }
        Iterator<?> it = collection.iterator();
        while (true) {
            boolean z = false;
            while (true) {
                if (!it.hasNext()) {
                    return z;
                }
                boolean remove = remove(it.next(), 1);
                if (z || remove) {
                    z = true;
                }
            }
        }
    }

    public boolean retainAll(Collection<?> collection) {
        if (collection instanceof Bag) {
            return retainAll((Bag<?>) (Bag) collection);
        }
        return retainAll((Bag<?>) new HashBag(collection));
    }

    /* access modifiers changed from: package-private */
    public boolean retainAll(Bag<?> bag) {
        HashBag hashBag = new HashBag();
        for (Object next : uniqueSet()) {
            int count = getCount(next);
            int count2 = bag.getCount(next);
            if (1 > count2 || count2 > count) {
                hashBag.add(next, count);
            } else {
                hashBag.add(next, count - count2);
            }
        }
        if (!hashBag.isEmpty()) {
            return removeAll(hashBag);
        }
        return false;
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

    public Object[] toArray() {
        Object[] objArr = new Object[size()];
        int i = 0;
        for (E next : this.map.keySet()) {
            int count = getCount(next);
            while (count > 0) {
                objArr[i] = next;
                count--;
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
        for (E next : this.map.keySet()) {
            int count = getCount(next);
            while (count > 0) {
                tArr[i] = next;
                count--;
                i++;
            }
        }
        while (i < tArr.length) {
            tArr[i] = null;
            i++;
        }
        return tArr;
    }

    public Set<E> uniqueSet() {
        if (this.uniqueSet == null) {
            this.uniqueSet = UnmodifiableSet.unmodifiableSet(this.map.keySet());
        }
        return this.uniqueSet;
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
    public void doReadObject(Map<E, MutableInteger> map2, ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.map = map2;
        int readInt = objectInputStream.readInt();
        for (int i = 0; i < readInt; i++) {
            Object readObject = objectInputStream.readObject();
            int readInt2 = objectInputStream.readInt();
            map2.put(readObject, new MutableInteger(readInt2));
            this.size += readInt2;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Bag)) {
            return false;
        }
        Bag bag = (Bag) obj;
        if (bag.size() != size()) {
            return false;
        }
        for (E next : this.map.keySet()) {
            if (bag.getCount(next) != getCount(next)) {
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

    public String toString() {
        if (size() == 0) {
            return HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        Iterator it = uniqueSet().iterator();
        while (it.hasNext()) {
            Object next = it.next();
            sb.append(getCount(next));
            sb.append(':');
            sb.append(next);
            if (it.hasNext()) {
                sb.append(',');
            }
        }
        sb.append(']');
        return sb.toString();
    }
}
