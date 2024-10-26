package org.apache.commons.collections4.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.keyvalue.DefaultMapEntry;
import org.apache.commons.collections4.map.AbstractHashedMap;

public abstract class AbstractReferenceMap<K, V> extends AbstractHashedMap<K, V> {
    /* access modifiers changed from: private */
    public ReferenceStrength keyType;
    /* access modifiers changed from: private */
    public boolean purgeValues;
    /* access modifiers changed from: private */
    public transient ReferenceQueue<Object> queue;
    /* access modifiers changed from: private */
    public ReferenceStrength valueType;

    public enum ReferenceStrength {
        HARD(0),
        SOFT(1),
        WEAK(2);
        
        public final int value;

        public static ReferenceStrength resolve(int i) {
            if (i == 0) {
                return HARD;
            }
            if (i == 1) {
                return SOFT;
            }
            if (i == 2) {
                return WEAK;
            }
            throw new IllegalArgumentException();
        }

        private ReferenceStrength(int i) {
            this.value = i;
        }
    }

    protected AbstractReferenceMap() {
    }

    protected AbstractReferenceMap(ReferenceStrength referenceStrength, ReferenceStrength referenceStrength2, int i, float f, boolean z) {
        super(i, f);
        this.keyType = referenceStrength;
        this.valueType = referenceStrength2;
        this.purgeValues = z;
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.queue = new ReferenceQueue<>();
    }

    public int size() {
        purgeBeforeRead();
        return super.size();
    }

    public boolean isEmpty() {
        purgeBeforeRead();
        return super.isEmpty();
    }

    public boolean containsKey(Object obj) {
        purgeBeforeRead();
        AbstractHashedMap.HashEntry entry = getEntry(obj);
        if (entry == null || entry.getValue() == null) {
            return false;
        }
        return true;
    }

    public boolean containsValue(Object obj) {
        purgeBeforeRead();
        if (obj == null) {
            return false;
        }
        return super.containsValue(obj);
    }

    public V get(Object obj) {
        purgeBeforeRead();
        AbstractHashedMap.HashEntry entry = getEntry(obj);
        if (entry == null) {
            return null;
        }
        return entry.getValue();
    }

    public V put(K k, V v) {
        if (k == null) {
            throw new NullPointerException("null keys not allowed");
        } else if (v != null) {
            purgeBeforeWrite();
            return super.put(k, v);
        } else {
            throw new NullPointerException("null values not allowed");
        }
    }

    public V remove(Object obj) {
        if (obj == null) {
            return null;
        }
        purgeBeforeWrite();
        return super.remove(obj);
    }

    public void clear() {
        super.clear();
        do {
        } while (this.queue.poll() != null);
    }

    public MapIterator<K, V> mapIterator() {
        return new ReferenceMapIterator(this);
    }

    public Set<Map.Entry<K, V>> entrySet() {
        if (this.entrySet == null) {
            this.entrySet = new ReferenceEntrySet(this);
        }
        return this.entrySet;
    }

    public Set<K> keySet() {
        if (this.keySet == null) {
            this.keySet = new ReferenceKeySet(this);
        }
        return this.keySet;
    }

    public Collection<V> values() {
        if (this.values == null) {
            this.values = new ReferenceValues(this);
        }
        return this.values;
    }

    /* access modifiers changed from: protected */
    public void purgeBeforeRead() {
        purge();
    }

    /* access modifiers changed from: protected */
    public void purgeBeforeWrite() {
        purge();
    }

    /* access modifiers changed from: protected */
    public void purge() {
        Reference<? extends Object> poll = this.queue.poll();
        while (poll != null) {
            purge(poll);
            poll = this.queue.poll();
        }
    }

    /* access modifiers changed from: protected */
    public void purge(Reference<?> reference) {
        int hashIndex = hashIndex(reference.hashCode(), this.data.length);
        AbstractHashedMap.HashEntry<K, V> hashEntry = null;
        for (AbstractHashedMap.HashEntry<K, V> hashEntry2 = this.data[hashIndex]; hashEntry2 != null; hashEntry2 = hashEntry2.next) {
            if (((ReferenceEntry) hashEntry2).purge(reference)) {
                if (hashEntry == null) {
                    this.data[hashIndex] = hashEntry2.next;
                } else {
                    hashEntry.next = hashEntry2.next;
                }
                this.size--;
                return;
            }
            hashEntry = hashEntry2;
        }
    }

    /* access modifiers changed from: protected */
    public AbstractHashedMap.HashEntry<K, V> getEntry(Object obj) {
        if (obj == null) {
            return null;
        }
        return super.getEntry(obj);
    }

    /* access modifiers changed from: protected */
    public int hashEntry(Object obj, Object obj2) {
        int i = 0;
        int hashCode = obj == null ? 0 : obj.hashCode();
        if (obj2 != null) {
            i = obj2.hashCode();
        }
        return hashCode ^ i;
    }

    /* access modifiers changed from: protected */
    public boolean isEqualKey(Object obj, Object obj2) {
        if (this.keyType != ReferenceStrength.HARD) {
            obj2 = ((Reference) obj2).get();
        }
        return obj == obj2 || obj.equals(obj2);
    }

    /* access modifiers changed from: protected */
    public ReferenceEntry<K, V> createEntry(AbstractHashedMap.HashEntry<K, V> hashEntry, int i, K k, V v) {
        return new ReferenceEntry(this, hashEntry, i, k, v);
    }

    /* access modifiers changed from: protected */
    public Iterator<Map.Entry<K, V>> createEntrySetIterator() {
        return new ReferenceEntrySetIterator(this);
    }

    /* access modifiers changed from: protected */
    public Iterator<K> createKeySetIterator() {
        return new ReferenceKeySetIterator(this);
    }

    /* access modifiers changed from: protected */
    public Iterator<V> createValuesIterator() {
        return new ReferenceValuesIterator(this);
    }

    static class ReferenceEntrySet<K, V> extends AbstractHashedMap.EntrySet<K, V> {
        protected ReferenceEntrySet(AbstractHashedMap<K, V> abstractHashedMap) {
            super(abstractHashedMap);
        }

        public Object[] toArray() {
            return toArray(new Object[size()]);
        }

        public <T> T[] toArray(T[] tArr) {
            ArrayList arrayList = new ArrayList(size());
            Iterator it = iterator();
            while (it.hasNext()) {
                arrayList.add(new DefaultMapEntry((Map.Entry) it.next()));
            }
            return arrayList.toArray(tArr);
        }
    }

    static class ReferenceKeySet<K> extends AbstractHashedMap.KeySet<K> {
        protected ReferenceKeySet(AbstractHashedMap<K, ?> abstractHashedMap) {
            super(abstractHashedMap);
        }

        public Object[] toArray() {
            return toArray(new Object[size()]);
        }

        public <T> T[] toArray(T[] tArr) {
            ArrayList arrayList = new ArrayList(size());
            Iterator it = iterator();
            while (it.hasNext()) {
                arrayList.add(it.next());
            }
            return arrayList.toArray(tArr);
        }
    }

    static class ReferenceValues<V> extends AbstractHashedMap.Values<V> {
        protected ReferenceValues(AbstractHashedMap<?, V> abstractHashedMap) {
            super(abstractHashedMap);
        }

        public Object[] toArray() {
            return toArray(new Object[size()]);
        }

        public <T> T[] toArray(T[] tArr) {
            ArrayList arrayList = new ArrayList(size());
            Iterator it = iterator();
            while (it.hasNext()) {
                arrayList.add(it.next());
            }
            return arrayList.toArray(tArr);
        }
    }

    protected static class ReferenceEntry<K, V> extends AbstractHashedMap.HashEntry<K, V> {
        private final AbstractReferenceMap<K, V> parent;

        public ReferenceEntry(AbstractReferenceMap<K, V> abstractReferenceMap, AbstractHashedMap.HashEntry<K, V> hashEntry, int i, K k, V v) {
            super(hashEntry, i, (Object) null, null);
            this.parent = abstractReferenceMap;
            this.key = toReference(abstractReferenceMap.keyType, k, i);
            this.value = toReference(abstractReferenceMap.valueType, v, i);
        }

        public K getKey() {
            return this.parent.keyType == ReferenceStrength.HARD ? this.key : ((Reference) this.key).get();
        }

        public V getValue() {
            return this.parent.valueType == ReferenceStrength.HARD ? this.value : ((Reference) this.value).get();
        }

        public V setValue(V v) {
            V value = getValue();
            if (this.parent.valueType != ReferenceStrength.HARD) {
                ((Reference) this.value).clear();
            }
            this.value = toReference(this.parent.valueType, v, this.hashCode);
            return value;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (key == null || value == null || !this.parent.isEqualKey(key, this.key) || !this.parent.isEqualValue(value, getValue())) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return this.parent.hashEntry(getKey(), getValue());
        }

        /* access modifiers changed from: protected */
        public <T> Object toReference(ReferenceStrength referenceStrength, T t, int i) {
            if (referenceStrength == ReferenceStrength.HARD) {
                return t;
            }
            if (referenceStrength == ReferenceStrength.SOFT) {
                return new SoftRef(i, t, this.parent.queue);
            }
            if (referenceStrength == ReferenceStrength.WEAK) {
                return new WeakRef(i, t, this.parent.queue);
            }
            throw new Error();
        }

        /* access modifiers changed from: package-private */
        public boolean purge(Reference<?> reference) {
            boolean z = true;
            if (!(this.parent.keyType != ReferenceStrength.HARD && this.key == reference) && (this.parent.valueType == ReferenceStrength.HARD || this.value != reference)) {
                z = false;
            }
            if (z) {
                if (this.parent.keyType != ReferenceStrength.HARD) {
                    ((Reference) this.key).clear();
                }
                if (this.parent.valueType != ReferenceStrength.HARD) {
                    ((Reference) this.value).clear();
                } else if (this.parent.purgeValues) {
                    this.value = null;
                }
            }
            return z;
        }

        /* access modifiers changed from: protected */
        public ReferenceEntry<K, V> next() {
            return (ReferenceEntry) this.next;
        }
    }

    static class ReferenceBaseIterator<K, V> {
        K currentKey;
        V currentValue;
        ReferenceEntry<K, V> entry;
        int expectedModCount;
        int index;
        K nextKey;
        V nextValue;
        final AbstractReferenceMap<K, V> parent;
        ReferenceEntry<K, V> previous;

        public ReferenceBaseIterator(AbstractReferenceMap<K, V> abstractReferenceMap) {
            this.parent = abstractReferenceMap;
            this.index = abstractReferenceMap.size() != 0 ? abstractReferenceMap.data.length : 0;
            this.expectedModCount = abstractReferenceMap.modCount;
        }

        public boolean hasNext() {
            checkMod();
            while (nextNull()) {
                ReferenceEntry<K, V> referenceEntry = this.entry;
                int i = this.index;
                while (referenceEntry == null && i > 0) {
                    i--;
                    referenceEntry = (ReferenceEntry) this.parent.data[i];
                }
                this.entry = referenceEntry;
                this.index = i;
                if (referenceEntry == null) {
                    this.currentKey = null;
                    this.currentValue = null;
                    return false;
                }
                this.nextKey = referenceEntry.getKey();
                this.nextValue = referenceEntry.getValue();
                if (nextNull()) {
                    this.entry = this.entry.next();
                }
            }
            return true;
        }

        private void checkMod() {
            if (this.parent.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        private boolean nextNull() {
            return this.nextKey == null || this.nextValue == null;
        }

        /* access modifiers changed from: protected */
        public ReferenceEntry<K, V> nextEntry() {
            checkMod();
            if (!nextNull() || hasNext()) {
                ReferenceEntry<K, V> referenceEntry = this.entry;
                this.previous = referenceEntry;
                this.entry = referenceEntry.next();
                this.currentKey = this.nextKey;
                this.currentValue = this.nextValue;
                this.nextKey = null;
                this.nextValue = null;
                return this.previous;
            }
            throw new NoSuchElementException();
        }

        /* access modifiers changed from: protected */
        public ReferenceEntry<K, V> currentEntry() {
            checkMod();
            return this.previous;
        }

        public void remove() {
            checkMod();
            if (this.previous != null) {
                this.parent.remove(this.currentKey);
                this.previous = null;
                this.currentKey = null;
                this.currentValue = null;
                this.expectedModCount = this.parent.modCount;
                return;
            }
            throw new IllegalStateException();
        }
    }

    static class ReferenceEntrySetIterator<K, V> extends ReferenceBaseIterator<K, V> implements Iterator<Map.Entry<K, V>> {
        public ReferenceEntrySetIterator(AbstractReferenceMap<K, V> abstractReferenceMap) {
            super(abstractReferenceMap);
        }

        public Map.Entry<K, V> next() {
            return nextEntry();
        }
    }

    static class ReferenceKeySetIterator<K> extends ReferenceBaseIterator<K, Object> implements Iterator<K> {
        ReferenceKeySetIterator(AbstractReferenceMap<K, ?> abstractReferenceMap) {
            super(abstractReferenceMap);
        }

        public K next() {
            return nextEntry().getKey();
        }
    }

    static class ReferenceValuesIterator<V> extends ReferenceBaseIterator<Object, V> implements Iterator<V> {
        ReferenceValuesIterator(AbstractReferenceMap<?, V> abstractReferenceMap) {
            super(abstractReferenceMap);
        }

        public V next() {
            return nextEntry().getValue();
        }
    }

    static class ReferenceMapIterator<K, V> extends ReferenceBaseIterator<K, V> implements MapIterator<K, V> {
        protected ReferenceMapIterator(AbstractReferenceMap<K, V> abstractReferenceMap) {
            super(abstractReferenceMap);
        }

        public K next() {
            return nextEntry().getKey();
        }

        public K getKey() {
            ReferenceEntry currentEntry = currentEntry();
            if (currentEntry != null) {
                return currentEntry.getKey();
            }
            throw new IllegalStateException("getKey() can only be called after next() and before remove()");
        }

        public V getValue() {
            ReferenceEntry currentEntry = currentEntry();
            if (currentEntry != null) {
                return currentEntry.getValue();
            }
            throw new IllegalStateException("getValue() can only be called after next() and before remove()");
        }

        public V setValue(V v) {
            ReferenceEntry currentEntry = currentEntry();
            if (currentEntry != null) {
                return currentEntry.setValue(v);
            }
            throw new IllegalStateException("setValue() can only be called after next() and before remove()");
        }
    }

    static class SoftRef<T> extends SoftReference<T> {
        private final int hash;

        public SoftRef(int i, T t, ReferenceQueue<? super T> referenceQueue) {
            super(t, referenceQueue);
            this.hash = i;
        }

        public int hashCode() {
            return this.hash;
        }
    }

    static class WeakRef<T> extends WeakReference<T> {
        private final int hash;

        public WeakRef(int i, T t, ReferenceQueue<? super T> referenceQueue) {
            super(t, referenceQueue);
            this.hash = i;
        }

        public int hashCode() {
            return this.hash;
        }
    }

    /* access modifiers changed from: protected */
    public void doWriteObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(this.keyType.value);
        objectOutputStream.writeInt(this.valueType.value);
        objectOutputStream.writeBoolean(this.purgeValues);
        objectOutputStream.writeFloat(this.loadFactor);
        objectOutputStream.writeInt(this.data.length);
        MapIterator mapIterator = mapIterator();
        while (mapIterator.hasNext()) {
            objectOutputStream.writeObject(mapIterator.next());
            objectOutputStream.writeObject(mapIterator.getValue());
        }
        objectOutputStream.writeObject((Object) null);
    }

    /* access modifiers changed from: protected */
    public void doReadObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.keyType = ReferenceStrength.resolve(objectInputStream.readInt());
        this.valueType = ReferenceStrength.resolve(objectInputStream.readInt());
        this.purgeValues = objectInputStream.readBoolean();
        this.loadFactor = objectInputStream.readFloat();
        int readInt = objectInputStream.readInt();
        init();
        this.data = new AbstractHashedMap.HashEntry[readInt];
        while (true) {
            Object readObject = objectInputStream.readObject();
            if (readObject == null) {
                this.threshold = calculateThreshold(this.data.length, this.loadFactor);
                return;
            }
            put(readObject, objectInputStream.readObject());
        }
    }

    /* access modifiers changed from: protected */
    public boolean isKeyType(ReferenceStrength referenceStrength) {
        return this.keyType == referenceStrength;
    }
}
