package org.apache.commons.collections4.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.KeyValue;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.iterators.EmptyIterator;
import org.apache.commons.collections4.iterators.EmptyMapIterator;

public class AbstractHashedMap<K, V> extends AbstractMap<K, V> implements IterableMap<K, V> {
    protected static final int DEFAULT_CAPACITY = 16;
    protected static final float DEFAULT_LOAD_FACTOR = 0.75f;
    protected static final int DEFAULT_THRESHOLD = 12;
    protected static final String GETKEY_INVALID = "getKey() can only be called after next() and before remove()";
    protected static final String GETVALUE_INVALID = "getValue() can only be called after next() and before remove()";
    protected static final int MAXIMUM_CAPACITY = 1073741824;
    protected static final String NO_NEXT_ENTRY = "No next() entry in the iteration";
    protected static final String NO_PREVIOUS_ENTRY = "No previous() entry in the iteration";
    protected static final Object NULL = new Object();
    protected static final String REMOVE_INVALID = "remove() can only be called once after next()";
    protected static final String SETVALUE_INVALID = "setValue() can only be called after next() and before remove()";
    transient HashEntry<K, V>[] data;
    transient EntrySet<K, V> entrySet;
    transient KeySet<K> keySet;
    transient float loadFactor;
    transient int modCount;
    transient int size;
    transient int threshold;
    transient Values<V> values;

    /* access modifiers changed from: protected */
    public int calculateNewCapacity(int i) {
        if (i > 1073741824) {
            return 1073741824;
        }
        int i2 = 1;
        while (i2 < i) {
            i2 <<= 1;
        }
        if (i2 > 1073741824) {
            return 1073741824;
        }
        return i2;
    }

    /* access modifiers changed from: protected */
    public int calculateThreshold(int i, float f) {
        return (int) (((float) i) * f);
    }

    /* access modifiers changed from: protected */
    public int hashIndex(int i, int i2) {
        return i & (i2 - 1);
    }

    /* access modifiers changed from: protected */
    public void init() {
    }

    protected AbstractHashedMap() {
    }

    protected AbstractHashedMap(int i, float f, int i2) {
        this.loadFactor = f;
        this.data = new HashEntry[i];
        this.threshold = i2;
        init();
    }

    protected AbstractHashedMap(int i) {
        this(i, DEFAULT_LOAD_FACTOR);
    }

    protected AbstractHashedMap(int i, float f) {
        if (i < 0) {
            throw new IllegalArgumentException("Initial capacity must be a non negative number");
        } else if (f <= 0.0f || Float.isNaN(f)) {
            throw new IllegalArgumentException("Load factor must be greater than 0");
        } else {
            this.loadFactor = f;
            int calculateNewCapacity = calculateNewCapacity(i);
            this.threshold = calculateThreshold(calculateNewCapacity, f);
            this.data = new HashEntry[calculateNewCapacity];
            init();
        }
    }

    protected AbstractHashedMap(Map<? extends K, ? extends V> map) {
        this(Math.max(map.size() * 2, 16), DEFAULT_LOAD_FACTOR);
        _putAll(map);
    }

    public V get(Object obj) {
        Object convertKey = convertKey(obj);
        int hash = hash(convertKey);
        HashEntry<K, V>[] hashEntryArr = this.data;
        for (HashEntry<K, V> hashEntry = hashEntryArr[hashIndex(hash, hashEntryArr.length)]; hashEntry != null; hashEntry = hashEntry.next) {
            if (hashEntry.hashCode == hash && isEqualKey(convertKey, hashEntry.key)) {
                return hashEntry.getValue();
            }
        }
        return null;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean containsKey(Object obj) {
        Object convertKey = convertKey(obj);
        int hash = hash(convertKey);
        HashEntry<K, V>[] hashEntryArr = this.data;
        for (HashEntry<K, V> hashEntry = hashEntryArr[hashIndex(hash, hashEntryArr.length)]; hashEntry != null; hashEntry = hashEntry.next) {
            if (hashEntry.hashCode == hash && isEqualKey(convertKey, hashEntry.key)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsValue(Object obj) {
        if (obj == null) {
            for (HashEntry<K, V> hashEntry : this.data) {
                while (hashEntry != null) {
                    if (hashEntry.getValue() == null) {
                        return true;
                    }
                    hashEntry = hashEntry.next;
                }
            }
        } else {
            for (HashEntry<K, V> hashEntry2 : this.data) {
                while (hashEntry2 != null) {
                    if (isEqualValue(obj, hashEntry2.getValue())) {
                        return true;
                    }
                    hashEntry2 = hashEntry2.next;
                }
            }
        }
        return false;
    }

    public V put(K k, V v) {
        Object convertKey = convertKey(k);
        int hash = hash(convertKey);
        int hashIndex = hashIndex(hash, this.data.length);
        HashEntry<K, V> hashEntry = this.data[hashIndex];
        while (hashEntry != null) {
            if (hashEntry.hashCode != hash || !isEqualKey(convertKey, hashEntry.key)) {
                hashEntry = hashEntry.next;
            } else {
                V value = hashEntry.getValue();
                updateEntry(hashEntry, v);
                return value;
            }
        }
        addMapping(hashIndex, hash, k, v);
        return null;
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        _putAll(map);
    }

    private void _putAll(Map<? extends K, ? extends V> map) {
        int size2 = map.size();
        if (size2 != 0) {
            ensureCapacity(calculateNewCapacity((int) ((((float) (this.size + size2)) / this.loadFactor) + 1.0f)));
            for (Map.Entry next : map.entrySet()) {
                put(next.getKey(), next.getValue());
            }
        }
    }

    public V remove(Object obj) {
        Object convertKey = convertKey(obj);
        int hash = hash(convertKey);
        int hashIndex = hashIndex(hash, this.data.length);
        HashEntry<K, V> hashEntry = this.data[hashIndex];
        HashEntry<K, V> hashEntry2 = null;
        while (hashEntry != null) {
            if (hashEntry.hashCode != hash || !isEqualKey(convertKey, hashEntry.key)) {
                hashEntry2 = hashEntry;
                hashEntry = hashEntry.next;
            } else {
                V value = hashEntry.getValue();
                removeMapping(hashEntry, hashIndex, hashEntry2);
                return value;
            }
        }
        return null;
    }

    public void clear() {
        this.modCount++;
        HashEntry<K, V>[] hashEntryArr = this.data;
        for (int length = hashEntryArr.length - 1; length >= 0; length--) {
            hashEntryArr[length] = null;
        }
        this.size = 0;
    }

    /* access modifiers changed from: protected */
    public Object convertKey(Object obj) {
        return obj == null ? NULL : obj;
    }

    /* access modifiers changed from: protected */
    public int hash(Object obj) {
        int hashCode = obj.hashCode();
        int i = hashCode + (~(hashCode << 9));
        int i2 = i ^ (i >>> 14);
        int i3 = i2 + (i2 << 4);
        return i3 ^ (i3 >>> 10);
    }

    /* access modifiers changed from: protected */
    public boolean isEqualKey(Object obj, Object obj2) {
        return obj == obj2 || obj.equals(obj2);
    }

    /* access modifiers changed from: protected */
    public boolean isEqualValue(Object obj, Object obj2) {
        return obj == obj2 || obj.equals(obj2);
    }

    /* access modifiers changed from: protected */
    public HashEntry<K, V> getEntry(Object obj) {
        Object convertKey = convertKey(obj);
        int hash = hash(convertKey);
        HashEntry<K, V>[] hashEntryArr = this.data;
        for (HashEntry<K, V> hashEntry = hashEntryArr[hashIndex(hash, hashEntryArr.length)]; hashEntry != null; hashEntry = hashEntry.next) {
            if (hashEntry.hashCode == hash && isEqualKey(convertKey, hashEntry.key)) {
                return hashEntry;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void updateEntry(HashEntry<K, V> hashEntry, V v) {
        hashEntry.setValue(v);
    }

    /* access modifiers changed from: protected */
    public void reuseEntry(HashEntry<K, V> hashEntry, int i, int i2, K k, V v) {
        hashEntry.next = this.data[i];
        hashEntry.hashCode = i2;
        hashEntry.key = k;
        hashEntry.value = v;
    }

    /* access modifiers changed from: protected */
    public void addMapping(int i, int i2, K k, V v) {
        this.modCount++;
        addEntry(createEntry(this.data[i], i2, k, v), i);
        this.size++;
        checkCapacity();
    }

    /* access modifiers changed from: protected */
    public HashEntry<K, V> createEntry(HashEntry<K, V> hashEntry, int i, K k, V v) {
        return new HashEntry<>(hashEntry, i, convertKey(k), v);
    }

    /* access modifiers changed from: protected */
    public void addEntry(HashEntry<K, V> hashEntry, int i) {
        this.data[i] = hashEntry;
    }

    /* access modifiers changed from: protected */
    public void removeMapping(HashEntry<K, V> hashEntry, int i, HashEntry<K, V> hashEntry2) {
        this.modCount++;
        removeEntry(hashEntry, i, hashEntry2);
        this.size--;
        destroyEntry(hashEntry);
    }

    /* access modifiers changed from: protected */
    public void removeEntry(HashEntry<K, V> hashEntry, int i, HashEntry<K, V> hashEntry2) {
        if (hashEntry2 == null) {
            this.data[i] = hashEntry.next;
        } else {
            hashEntry2.next = hashEntry.next;
        }
    }

    /* access modifiers changed from: protected */
    public void destroyEntry(HashEntry<K, V> hashEntry) {
        hashEntry.next = null;
        hashEntry.key = null;
        hashEntry.value = null;
    }

    /* access modifiers changed from: protected */
    public void checkCapacity() {
        int length;
        if (this.size >= this.threshold && (length = this.data.length * 2) <= 1073741824) {
            ensureCapacity(length);
        }
    }

    /* access modifiers changed from: protected */
    public void ensureCapacity(int i) {
        HashEntry<K, V>[] hashEntryArr = this.data;
        int length = hashEntryArr.length;
        if (i > length) {
            if (this.size == 0) {
                this.threshold = calculateThreshold(i, this.loadFactor);
                this.data = new HashEntry[i];
                return;
            }
            HashEntry<K, V>[] hashEntryArr2 = new HashEntry[i];
            this.modCount++;
            for (int i2 = length - 1; i2 >= 0; i2--) {
                HashEntry<K, V> hashEntry = hashEntryArr[i2];
                if (hashEntry != null) {
                    hashEntryArr[i2] = null;
                    while (true) {
                        HashEntry<K, V> hashEntry2 = hashEntry.next;
                        int hashIndex = hashIndex(hashEntry.hashCode, i);
                        hashEntry.next = hashEntryArr2[hashIndex];
                        hashEntryArr2[hashIndex] = hashEntry;
                        if (hashEntry2 == null) {
                            break;
                        }
                        hashEntry = hashEntry2;
                    }
                }
            }
            this.threshold = calculateThreshold(i, this.loadFactor);
            this.data = hashEntryArr2;
        }
    }

    /* access modifiers changed from: protected */
    public HashEntry<K, V> entryNext(HashEntry<K, V> hashEntry) {
        return hashEntry.next;
    }

    /* access modifiers changed from: protected */
    public int entryHashCode(HashEntry<K, V> hashEntry) {
        return hashEntry.hashCode;
    }

    /* access modifiers changed from: protected */
    public K entryKey(HashEntry<K, V> hashEntry) {
        return hashEntry.getKey();
    }

    /* access modifiers changed from: protected */
    public V entryValue(HashEntry<K, V> hashEntry) {
        return hashEntry.getValue();
    }

    public MapIterator<K, V> mapIterator() {
        if (this.size == 0) {
            return EmptyMapIterator.emptyMapIterator();
        }
        return new HashMapIterator(this);
    }

    protected static class HashMapIterator<K, V> extends HashIterator<K, V> implements MapIterator<K, V> {
        protected HashMapIterator(AbstractHashedMap<K, V> abstractHashedMap) {
            super(abstractHashedMap);
        }

        public K next() {
            return super.nextEntry().getKey();
        }

        public K getKey() {
            HashEntry currentEntry = currentEntry();
            if (currentEntry != null) {
                return currentEntry.getKey();
            }
            throw new IllegalStateException(AbstractHashedMap.GETKEY_INVALID);
        }

        public V getValue() {
            HashEntry currentEntry = currentEntry();
            if (currentEntry != null) {
                return currentEntry.getValue();
            }
            throw new IllegalStateException(AbstractHashedMap.GETVALUE_INVALID);
        }

        public V setValue(V v) {
            HashEntry currentEntry = currentEntry();
            if (currentEntry != null) {
                return currentEntry.setValue(v);
            }
            throw new IllegalStateException(AbstractHashedMap.SETVALUE_INVALID);
        }
    }

    public Set<Map.Entry<K, V>> entrySet() {
        if (this.entrySet == null) {
            this.entrySet = new EntrySet<>(this);
        }
        return this.entrySet;
    }

    /* access modifiers changed from: protected */
    public Iterator<Map.Entry<K, V>> createEntrySetIterator() {
        if (size() == 0) {
            return EmptyIterator.emptyIterator();
        }
        return new EntrySetIterator(this);
    }

    protected static class EntrySet<K, V> extends AbstractSet<Map.Entry<K, V>> {
        private final AbstractHashedMap<K, V> parent;

        protected EntrySet(AbstractHashedMap<K, V> abstractHashedMap) {
            this.parent = abstractHashedMap;
        }

        public int size() {
            return this.parent.size();
        }

        public void clear() {
            this.parent.clear();
        }

        public boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            HashEntry<K, V> entry2 = this.parent.getEntry(entry.getKey());
            if (entry2 == null || !entry2.equals(entry)) {
                return false;
            }
            return true;
        }

        public boolean remove(Object obj) {
            if (!(obj instanceof Map.Entry) || !contains(obj)) {
                return false;
            }
            this.parent.remove(((Map.Entry) obj).getKey());
            return true;
        }

        public Iterator<Map.Entry<K, V>> iterator() {
            return this.parent.createEntrySetIterator();
        }
    }

    protected static class EntrySetIterator<K, V> extends HashIterator<K, V> implements Iterator<Map.Entry<K, V>> {
        protected EntrySetIterator(AbstractHashedMap<K, V> abstractHashedMap) {
            super(abstractHashedMap);
        }

        public Map.Entry<K, V> next() {
            return super.nextEntry();
        }
    }

    public Set<K> keySet() {
        if (this.keySet == null) {
            this.keySet = new KeySet<>(this);
        }
        return this.keySet;
    }

    /* access modifiers changed from: protected */
    public Iterator<K> createKeySetIterator() {
        if (size() == 0) {
            return EmptyIterator.emptyIterator();
        }
        return new KeySetIterator(this);
    }

    protected static class KeySet<K> extends AbstractSet<K> {
        private final AbstractHashedMap<K, ?> parent;

        protected KeySet(AbstractHashedMap<K, ?> abstractHashedMap) {
            this.parent = abstractHashedMap;
        }

        public int size() {
            return this.parent.size();
        }

        public void clear() {
            this.parent.clear();
        }

        public boolean contains(Object obj) {
            return this.parent.containsKey(obj);
        }

        public boolean remove(Object obj) {
            boolean containsKey = this.parent.containsKey(obj);
            this.parent.remove(obj);
            return containsKey;
        }

        public Iterator<K> iterator() {
            return this.parent.createKeySetIterator();
        }
    }

    protected static class KeySetIterator<K> extends HashIterator<K, Object> implements Iterator<K> {
        protected KeySetIterator(AbstractHashedMap<K, ?> abstractHashedMap) {
            super(abstractHashedMap);
        }

        public K next() {
            return super.nextEntry().getKey();
        }
    }

    public Collection<V> values() {
        if (this.values == null) {
            this.values = new Values<>(this);
        }
        return this.values;
    }

    /* access modifiers changed from: protected */
    public Iterator<V> createValuesIterator() {
        if (size() == 0) {
            return EmptyIterator.emptyIterator();
        }
        return new ValuesIterator(this);
    }

    protected static class Values<V> extends AbstractCollection<V> {
        private final AbstractHashedMap<?, V> parent;

        protected Values(AbstractHashedMap<?, V> abstractHashedMap) {
            this.parent = abstractHashedMap;
        }

        public int size() {
            return this.parent.size();
        }

        public void clear() {
            this.parent.clear();
        }

        public boolean contains(Object obj) {
            return this.parent.containsValue(obj);
        }

        public Iterator<V> iterator() {
            return this.parent.createValuesIterator();
        }
    }

    protected static class ValuesIterator<V> extends HashIterator<Object, V> implements Iterator<V> {
        protected ValuesIterator(AbstractHashedMap<?, V> abstractHashedMap) {
            super(abstractHashedMap);
        }

        public V next() {
            return super.nextEntry().getValue();
        }
    }

    protected static class HashEntry<K, V> implements Map.Entry<K, V>, KeyValue<K, V> {
        protected int hashCode;
        protected Object key;
        protected HashEntry<K, V> next;
        protected Object value;

        protected HashEntry(HashEntry<K, V> hashEntry, int i, Object obj, V v) {
            this.next = hashEntry;
            this.hashCode = i;
            this.key = obj;
            this.value = v;
        }

        public K getKey() {
            if (this.key == AbstractHashedMap.NULL) {
                return null;
            }
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

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            if (getKey() != null ? getKey().equals(entry.getKey()) : entry.getKey() == null) {
                if (getValue() == null) {
                    if (entry.getValue() == null) {
                        return true;
                    }
                } else if (getValue().equals(entry.getValue())) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            int i = 0;
            int hashCode2 = getKey() == null ? 0 : getKey().hashCode();
            if (getValue() != null) {
                i = getValue().hashCode();
            }
            return hashCode2 ^ i;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(getKey());
            sb.append('=');
            sb.append(getValue());
            return sb.toString();
        }
    }

    protected static abstract class HashIterator<K, V> {
        private int expectedModCount;
        private int hashIndex;
        private HashEntry<K, V> last;
        private HashEntry<K, V> next;
        private final AbstractHashedMap<K, V> parent;

        protected HashIterator(AbstractHashedMap<K, V> abstractHashedMap) {
            this.parent = abstractHashedMap;
            HashEntry<K, V>[] hashEntryArr = abstractHashedMap.data;
            int length = hashEntryArr.length;
            HashEntry<K, V> hashEntry = null;
            while (length > 0 && hashEntry == null) {
                length--;
                hashEntry = hashEntryArr[length];
            }
            this.next = hashEntry;
            this.hashIndex = length;
            this.expectedModCount = abstractHashedMap.modCount;
        }

        public boolean hasNext() {
            return this.next != null;
        }

        /* access modifiers changed from: protected */
        public HashEntry<K, V> nextEntry() {
            if (this.parent.modCount == this.expectedModCount) {
                HashEntry<K, V> hashEntry = this.next;
                if (hashEntry != null) {
                    HashEntry<K, V>[] hashEntryArr = this.parent.data;
                    int i = this.hashIndex;
                    HashEntry<K, V> hashEntry2 = hashEntry.next;
                    while (hashEntry2 == null && i > 0) {
                        i--;
                        hashEntry2 = hashEntryArr[i];
                    }
                    this.next = hashEntry2;
                    this.hashIndex = i;
                    this.last = hashEntry;
                    return hashEntry;
                }
                throw new NoSuchElementException(AbstractHashedMap.NO_NEXT_ENTRY);
            }
            throw new ConcurrentModificationException();
        }

        /* access modifiers changed from: protected */
        public HashEntry<K, V> currentEntry() {
            return this.last;
        }

        public void remove() {
            if (this.last == null) {
                throw new IllegalStateException(AbstractHashedMap.REMOVE_INVALID);
            } else if (this.parent.modCount == this.expectedModCount) {
                this.parent.remove(this.last.getKey());
                this.last = null;
                this.expectedModCount = this.parent.modCount;
            } else {
                throw new ConcurrentModificationException();
            }
        }

        public String toString() {
            if (this.last == null) {
                return "Iterator[]";
            }
            return "Iterator[" + this.last.getKey() + "=" + this.last.getValue() + "]";
        }
    }

    /* access modifiers changed from: protected */
    public void doWriteObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeFloat(this.loadFactor);
        objectOutputStream.writeInt(this.data.length);
        objectOutputStream.writeInt(this.size);
        MapIterator mapIterator = mapIterator();
        while (mapIterator.hasNext()) {
            objectOutputStream.writeObject(mapIterator.next());
            objectOutputStream.writeObject(mapIterator.getValue());
        }
    }

    /* access modifiers changed from: protected */
    public void doReadObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.loadFactor = objectInputStream.readFloat();
        int readInt = objectInputStream.readInt();
        int readInt2 = objectInputStream.readInt();
        init();
        this.threshold = calculateThreshold(readInt, this.loadFactor);
        this.data = new HashEntry[readInt];
        for (int i = 0; i < readInt2; i++) {
            put(objectInputStream.readObject(), objectInputStream.readObject());
        }
    }

    /* access modifiers changed from: protected */
    public AbstractHashedMap<K, V> clone() {
        try {
            AbstractHashedMap<K, V> abstractHashedMap = (AbstractHashedMap) super.clone();
            abstractHashedMap.data = new HashEntry[this.data.length];
            abstractHashedMap.entrySet = null;
            abstractHashedMap.keySet = null;
            abstractHashedMap.values = null;
            abstractHashedMap.modCount = 0;
            abstractHashedMap.size = 0;
            abstractHashedMap.init();
            abstractHashedMap.putAll(this);
            return abstractHashedMap;
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
        if (map.size() != size()) {
            return false;
        }
        MapIterator mapIterator = mapIterator();
        while (mapIterator.hasNext()) {
            try {
                Object next = mapIterator.next();
                Object value = mapIterator.getValue();
                if (value == null) {
                    if (map.get(next) != null || !map.containsKey(next)) {
                        return false;
                    }
                } else if (!value.equals(map.get(next))) {
                    return false;
                }
            } catch (ClassCastException | NullPointerException unused) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        Iterator createEntrySetIterator = createEntrySetIterator();
        int i = 0;
        while (createEntrySetIterator.hasNext()) {
            i += ((Map.Entry) createEntrySetIterator.next()).hashCode();
        }
        return i;
    }

    public String toString() {
        if (size() == 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(size() * 32);
        sb.append('{');
        MapIterator mapIterator = mapIterator();
        boolean hasNext = mapIterator.hasNext();
        while (hasNext) {
            Object next = mapIterator.next();
            Object value = mapIterator.getValue();
            if (next == this) {
                next = "(this Map)";
            }
            sb.append(next);
            sb.append('=');
            if (value == this) {
                value = "(this Map)";
            }
            sb.append(value);
            hasNext = mapIterator.hasNext();
            if (hasNext) {
                sb.append(',');
                sb.append(' ');
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
