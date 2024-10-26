package org.apache.commons.collections4.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.ResettableIterator;
import org.apache.commons.collections4.iterators.EmptyIterator;
import org.apache.commons.collections4.iterators.EmptyMapIterator;

public class Flat3Map<K, V> implements IterableMap<K, V>, Serializable, Cloneable {
    private static final long serialVersionUID = -6701087419741928296L;
    /* access modifiers changed from: private */
    public transient AbstractHashedMap<K, V> delegateMap;
    private transient int hash1;
    private transient int hash2;
    private transient int hash3;
    /* access modifiers changed from: private */
    public transient K key1;
    /* access modifiers changed from: private */
    public transient K key2;
    /* access modifiers changed from: private */
    public transient K key3;
    /* access modifiers changed from: private */
    public transient int size;
    /* access modifiers changed from: private */
    public transient V value1;
    /* access modifiers changed from: private */
    public transient V value2;
    /* access modifiers changed from: private */
    public transient V value3;

    public Flat3Map() {
    }

    public Flat3Map(Map<? extends K, ? extends V> map) {
        putAll(map);
    }

    public V get(Object obj) {
        AbstractHashedMap<K, V> abstractHashedMap = this.delegateMap;
        if (abstractHashedMap != null) {
            return abstractHashedMap.get(obj);
        }
        if (obj == null) {
            int i = this.size;
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return null;
                    }
                    if (this.key3 == null) {
                        return this.value3;
                    }
                }
                if (this.key2 == null) {
                    return this.value2;
                }
            }
            if (this.key1 == null) {
                return this.value1;
            }
            return null;
        } else if (this.size <= 0) {
            return null;
        } else {
            int hashCode = obj.hashCode();
            int i2 = this.size;
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        return null;
                    }
                    if (this.hash3 == hashCode && obj.equals(this.key3)) {
                        return this.value3;
                    }
                }
                if (this.hash2 == hashCode && obj.equals(this.key2)) {
                    return this.value2;
                }
            }
            if (this.hash1 != hashCode || !obj.equals(this.key1)) {
                return null;
            }
            return this.value1;
        }
    }

    public int size() {
        AbstractHashedMap<K, V> abstractHashedMap = this.delegateMap;
        if (abstractHashedMap != null) {
            return abstractHashedMap.size();
        }
        return this.size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean containsKey(Object obj) {
        AbstractHashedMap<K, V> abstractHashedMap = this.delegateMap;
        if (abstractHashedMap != null) {
            return abstractHashedMap.containsKey(obj);
        }
        if (obj == null) {
            int i = this.size;
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return false;
                    }
                    if (this.key3 == null) {
                        return true;
                    }
                }
                if (this.key2 == null) {
                    return true;
                }
            }
            if (this.key1 == null) {
                return true;
            }
            return false;
        } else if (this.size <= 0) {
            return false;
        } else {
            int hashCode = obj.hashCode();
            int i2 = this.size;
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        return false;
                    }
                    if (this.hash3 == hashCode && obj.equals(this.key3)) {
                        return true;
                    }
                }
                if (this.hash2 == hashCode && obj.equals(this.key2)) {
                    return true;
                }
            }
            if (this.hash1 != hashCode || !obj.equals(this.key1)) {
                return false;
            }
            return true;
        }
    }

    public boolean containsValue(Object obj) {
        AbstractHashedMap<K, V> abstractHashedMap = this.delegateMap;
        if (abstractHashedMap != null) {
            return abstractHashedMap.containsValue(obj);
        }
        if (obj == null) {
            int i = this.size;
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return false;
                    }
                    if (this.value3 == null) {
                        return true;
                    }
                }
                if (this.value2 == null) {
                    return true;
                }
            }
            if (this.value1 == null) {
                return true;
            }
            return false;
        }
        int i2 = this.size;
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    return false;
                }
                if (obj.equals(this.value3)) {
                    return true;
                }
            }
            if (obj.equals(this.value2)) {
                return true;
            }
        }
        if (obj.equals(this.value1)) {
            return true;
        }
        return false;
    }

    public V put(K k, V v) {
        AbstractHashedMap<K, V> abstractHashedMap = this.delegateMap;
        if (abstractHashedMap != null) {
            return abstractHashedMap.put(k, v);
        }
        if (k == null) {
            int i = this.size;
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        if (this.key3 == null) {
                            V v2 = this.value3;
                            this.value3 = v;
                            return v2;
                        }
                    }
                }
                if (this.key2 == null) {
                    V v3 = this.value2;
                    this.value2 = v;
                    return v3;
                }
            }
            if (this.key1 == null) {
                V v4 = this.value1;
                this.value1 = v;
                return v4;
            }
        } else if (this.size > 0) {
            int hashCode = k.hashCode();
            int i2 = this.size;
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 == 3) {
                        if (this.hash3 == hashCode && k.equals(this.key3)) {
                            V v5 = this.value3;
                            this.value3 = v;
                            return v5;
                        }
                    }
                }
                if (this.hash2 == hashCode && k.equals(this.key2)) {
                    V v6 = this.value2;
                    this.value2 = v;
                    return v6;
                }
            }
            if (this.hash1 == hashCode && k.equals(this.key1)) {
                V v7 = this.value1;
                this.value1 = v;
                return v7;
            }
        }
        int i3 = this.size;
        int i4 = 0;
        if (i3 == 0) {
            if (k != null) {
                i4 = k.hashCode();
            }
            this.hash1 = i4;
            this.key1 = k;
            this.value1 = v;
        } else if (i3 == 1) {
            if (k != null) {
                i4 = k.hashCode();
            }
            this.hash2 = i4;
            this.key2 = k;
            this.value2 = v;
        } else if (i3 != 2) {
            convertToMap();
            this.delegateMap.put(k, v);
            return null;
        } else {
            if (k != null) {
                i4 = k.hashCode();
            }
            this.hash3 = i4;
            this.key3 = k;
            this.value3 = v;
        }
        this.size++;
        return null;
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        int size2 = map.size();
        if (size2 != 0) {
            AbstractHashedMap<K, V> abstractHashedMap = this.delegateMap;
            if (abstractHashedMap != null) {
                abstractHashedMap.putAll(map);
            } else if (size2 < 4) {
                for (Map.Entry next : map.entrySet()) {
                    put(next.getKey(), next.getValue());
                }
            } else {
                convertToMap();
                this.delegateMap.putAll(map);
            }
        }
    }

    private void convertToMap() {
        AbstractHashedMap<K, V> createDelegateMap = createDelegateMap();
        this.delegateMap = createDelegateMap;
        int i = this.size;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        createDelegateMap.put(this.key3, this.value3);
                    } else {
                        throw new IllegalStateException("Invalid map index: " + this.size);
                    }
                }
                this.delegateMap.put(this.key2, this.value2);
            }
            this.delegateMap.put(this.key1, this.value1);
        }
        this.size = 0;
        this.hash3 = 0;
        this.hash2 = 0;
        this.hash1 = 0;
        this.key3 = null;
        this.key2 = null;
        this.key1 = null;
        this.value3 = null;
        this.value2 = null;
        this.value1 = null;
    }

    /* access modifiers changed from: protected */
    public AbstractHashedMap<K, V> createDelegateMap() {
        return new HashedMap();
    }

    public V remove(Object obj) {
        AbstractHashedMap<K, V> abstractHashedMap = this.delegateMap;
        if (abstractHashedMap != null) {
            return abstractHashedMap.remove(obj);
        }
        int i = this.size;
        if (i == 0) {
            return null;
        }
        if (obj == null) {
            if (i != 1) {
                if (i == 2) {
                    K k = this.key2;
                    if (k == null) {
                        V v = this.value2;
                        this.hash2 = 0;
                        this.key2 = null;
                        this.value2 = null;
                        this.size = 1;
                        return v;
                    } else if (this.key1 != null) {
                        return null;
                    } else {
                        V v2 = this.value1;
                        this.hash1 = this.hash2;
                        this.key1 = k;
                        this.value1 = this.value2;
                        this.hash2 = 0;
                        this.key2 = null;
                        this.value2 = null;
                        this.size = 1;
                        return v2;
                    }
                } else if (i == 3) {
                    K k2 = this.key3;
                    if (k2 == null) {
                        V v3 = this.value3;
                        this.hash3 = 0;
                        this.key3 = null;
                        this.value3 = null;
                        this.size = 2;
                        return v3;
                    } else if (this.key2 == null) {
                        V v4 = this.value2;
                        this.hash2 = this.hash3;
                        this.key2 = k2;
                        this.value2 = this.value3;
                        this.hash3 = 0;
                        this.key3 = null;
                        this.value3 = null;
                        this.size = 2;
                        return v4;
                    } else if (this.key1 != null) {
                        return null;
                    } else {
                        V v5 = this.value1;
                        this.hash1 = this.hash3;
                        this.key1 = k2;
                        this.value1 = this.value3;
                        this.hash3 = 0;
                        this.key3 = null;
                        this.value3 = null;
                        this.size = 2;
                        return v5;
                    }
                }
            } else if (this.key1 == null) {
                V v6 = this.value1;
                this.hash1 = 0;
                this.key1 = null;
                this.value1 = null;
                this.size = 0;
                return v6;
            }
        } else if (i > 0) {
            int hashCode = obj.hashCode();
            int i2 = this.size;
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 == 3) {
                        if (this.hash3 == hashCode && obj.equals(this.key3)) {
                            V v7 = this.value3;
                            this.hash3 = 0;
                            this.key3 = null;
                            this.value3 = null;
                            this.size = 2;
                            return v7;
                        } else if (this.hash2 == hashCode && obj.equals(this.key2)) {
                            V v8 = this.value2;
                            this.hash2 = this.hash3;
                            this.key2 = this.key3;
                            this.value2 = this.value3;
                            this.hash3 = 0;
                            this.key3 = null;
                            this.value3 = null;
                            this.size = 2;
                            return v8;
                        } else if (this.hash1 != hashCode || !obj.equals(this.key1)) {
                            return null;
                        } else {
                            V v9 = this.value1;
                            this.hash1 = this.hash3;
                            this.key1 = this.key3;
                            this.value1 = this.value3;
                            this.hash3 = 0;
                            this.key3 = null;
                            this.value3 = null;
                            this.size = 2;
                            return v9;
                        }
                    }
                } else if (this.hash2 == hashCode && obj.equals(this.key2)) {
                    V v10 = this.value2;
                    this.hash2 = 0;
                    this.key2 = null;
                    this.value2 = null;
                    this.size = 1;
                    return v10;
                } else if (this.hash1 != hashCode || !obj.equals(this.key1)) {
                    return null;
                } else {
                    V v11 = this.value1;
                    this.hash1 = this.hash2;
                    this.key1 = this.key2;
                    this.value1 = this.value2;
                    this.hash2 = 0;
                    this.key2 = null;
                    this.value2 = null;
                    this.size = 1;
                    return v11;
                }
            } else if (this.hash1 == hashCode && obj.equals(this.key1)) {
                V v12 = this.value1;
                this.hash1 = 0;
                this.key1 = null;
                this.value1 = null;
                this.size = 0;
                return v12;
            }
        }
        return null;
    }

    public void clear() {
        AbstractHashedMap<K, V> abstractHashedMap = this.delegateMap;
        if (abstractHashedMap != null) {
            abstractHashedMap.clear();
            this.delegateMap = null;
            return;
        }
        this.size = 0;
        this.hash3 = 0;
        this.hash2 = 0;
        this.hash1 = 0;
        this.key3 = null;
        this.key2 = null;
        this.key1 = null;
        this.value3 = null;
        this.value2 = null;
        this.value1 = null;
    }

    public MapIterator<K, V> mapIterator() {
        AbstractHashedMap<K, V> abstractHashedMap = this.delegateMap;
        if (abstractHashedMap != null) {
            return abstractHashedMap.mapIterator();
        }
        if (this.size == 0) {
            return EmptyMapIterator.emptyMapIterator();
        }
        return new FlatMapIterator(this);
    }

    static class FlatMapIterator<K, V> implements MapIterator<K, V>, ResettableIterator<K> {
        private boolean canRemove = false;
        private int nextIndex = 0;
        private final Flat3Map<K, V> parent;

        FlatMapIterator(Flat3Map<K, V> flat3Map) {
            this.parent = flat3Map;
        }

        public boolean hasNext() {
            return this.nextIndex < this.parent.size;
        }

        public K next() {
            if (hasNext()) {
                this.canRemove = true;
                this.nextIndex++;
                return getKey();
            }
            throw new NoSuchElementException("No next() entry in the iteration");
        }

        public void remove() {
            if (this.canRemove) {
                this.parent.remove(getKey());
                this.nextIndex--;
                this.canRemove = false;
                return;
            }
            throw new IllegalStateException("remove() can only be called once after next()");
        }

        public K getKey() {
            if (this.canRemove) {
                int i = this.nextIndex;
                if (i == 1) {
                    return this.parent.key1;
                }
                if (i == 2) {
                    return this.parent.key2;
                }
                if (i == 3) {
                    return this.parent.key3;
                }
                throw new IllegalStateException("Invalid map index: " + this.nextIndex);
            }
            throw new IllegalStateException("getKey() can only be called after next() and before remove()");
        }

        public V getValue() {
            if (this.canRemove) {
                int i = this.nextIndex;
                if (i == 1) {
                    return this.parent.value1;
                }
                if (i == 2) {
                    return this.parent.value2;
                }
                if (i == 3) {
                    return this.parent.value3;
                }
                throw new IllegalStateException("Invalid map index: " + this.nextIndex);
            }
            throw new IllegalStateException("getValue() can only be called after next() and before remove()");
        }

        public V setValue(V v) {
            if (this.canRemove) {
                V value = getValue();
                int i = this.nextIndex;
                if (i == 1) {
                    Object unused = this.parent.value1 = v;
                } else if (i == 2) {
                    Object unused2 = this.parent.value2 = v;
                } else if (i == 3) {
                    Object unused3 = this.parent.value3 = v;
                } else {
                    throw new IllegalStateException("Invalid map index: " + this.nextIndex);
                }
                return value;
            }
            throw new IllegalStateException("setValue() can only be called after next() and before remove()");
        }

        public void reset() {
            this.nextIndex = 0;
            this.canRemove = false;
        }

        public String toString() {
            if (!this.canRemove) {
                return "Iterator[]";
            }
            return "Iterator[" + getKey() + "=" + getValue() + "]";
        }
    }

    public Set<Map.Entry<K, V>> entrySet() {
        AbstractHashedMap<K, V> abstractHashedMap = this.delegateMap;
        if (abstractHashedMap != null) {
            return abstractHashedMap.entrySet();
        }
        return new EntrySet(this);
    }

    static class EntrySet<K, V> extends AbstractSet<Map.Entry<K, V>> {
        private final Flat3Map<K, V> parent;

        EntrySet(Flat3Map<K, V> flat3Map) {
            this.parent = flat3Map;
        }

        public int size() {
            return this.parent.size();
        }

        public void clear() {
            this.parent.clear();
        }

        public boolean remove(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Object key = ((Map.Entry) obj).getKey();
            boolean containsKey = this.parent.containsKey(key);
            this.parent.remove(key);
            return containsKey;
        }

        public Iterator<Map.Entry<K, V>> iterator() {
            if (this.parent.delegateMap != null) {
                return this.parent.delegateMap.entrySet().iterator();
            }
            if (this.parent.size() == 0) {
                return EmptyIterator.emptyIterator();
            }
            return new EntrySetIterator(this.parent);
        }
    }

    static class FlatMapEntry<K, V> implements Map.Entry<K, V> {
        private final int index;
        private final Flat3Map<K, V> parent;
        private volatile boolean removed = false;

        public FlatMapEntry(Flat3Map<K, V> flat3Map, int i) {
            this.parent = flat3Map;
            this.index = i;
        }

        /* access modifiers changed from: package-private */
        public void setRemoved(boolean z) {
            this.removed = z;
        }

        public K getKey() {
            if (!this.removed) {
                int i = this.index;
                if (i == 1) {
                    return this.parent.key1;
                }
                if (i == 2) {
                    return this.parent.key2;
                }
                if (i == 3) {
                    return this.parent.key3;
                }
                throw new IllegalStateException("Invalid map index: " + this.index);
            }
            throw new IllegalStateException("getKey() can only be called after next() and before remove()");
        }

        public V getValue() {
            if (!this.removed) {
                int i = this.index;
                if (i == 1) {
                    return this.parent.value1;
                }
                if (i == 2) {
                    return this.parent.value2;
                }
                if (i == 3) {
                    return this.parent.value3;
                }
                throw new IllegalStateException("Invalid map index: " + this.index);
            }
            throw new IllegalStateException("getValue() can only be called after next() and before remove()");
        }

        public V setValue(V v) {
            if (!this.removed) {
                V value = getValue();
                int i = this.index;
                if (i == 1) {
                    Object unused = this.parent.value1 = v;
                } else if (i == 2) {
                    Object unused2 = this.parent.value2 = v;
                } else if (i == 3) {
                    Object unused3 = this.parent.value3 = v;
                } else {
                    throw new IllegalStateException("Invalid map index: " + this.index);
                }
                return value;
            }
            throw new IllegalStateException("setValue() can only be called after next() and before remove()");
        }

        /* JADX WARNING: Removed duplicated region for block: B:17:0x0037 A[ORIG_RETURN, RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean equals(java.lang.Object r5) {
            /*
                r4 = this;
                boolean r0 = r4.removed
                r1 = 0
                if (r0 == 0) goto L_0x0006
                return r1
            L_0x0006:
                boolean r0 = r5 instanceof java.util.Map.Entry
                if (r0 != 0) goto L_0x000b
                return r1
            L_0x000b:
                java.util.Map$Entry r5 = (java.util.Map.Entry) r5
                java.lang.Object r0 = r4.getKey()
                java.lang.Object r2 = r4.getValue()
                if (r0 != 0) goto L_0x001e
                java.lang.Object r0 = r5.getKey()
                if (r0 != 0) goto L_0x0038
                goto L_0x0028
            L_0x001e:
                java.lang.Object r3 = r5.getKey()
                boolean r0 = r0.equals(r3)
                if (r0 == 0) goto L_0x0038
            L_0x0028:
                java.lang.Object r5 = r5.getValue()
                if (r2 != 0) goto L_0x0031
                if (r5 != 0) goto L_0x0038
                goto L_0x0037
            L_0x0031:
                boolean r5 = r2.equals(r5)
                if (r5 == 0) goto L_0x0038
            L_0x0037:
                r1 = 1
            L_0x0038:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.collections4.map.Flat3Map.FlatMapEntry.equals(java.lang.Object):boolean");
        }

        public int hashCode() {
            int i;
            int i2 = 0;
            if (this.removed) {
                return 0;
            }
            Object key = getKey();
            Object value = getValue();
            if (key == null) {
                i = 0;
            } else {
                i = key.hashCode();
            }
            if (value != null) {
                i2 = value.hashCode();
            }
            return i ^ i2;
        }

        public String toString() {
            if (this.removed) {
                return "";
            }
            return getKey() + "=" + getValue();
        }
    }

    static abstract class EntryIterator<K, V> {
        private FlatMapEntry<K, V> currentEntry = null;
        private int nextIndex = 0;
        private final Flat3Map<K, V> parent;

        public EntryIterator(Flat3Map<K, V> flat3Map) {
            this.parent = flat3Map;
        }

        public boolean hasNext() {
            return this.nextIndex < this.parent.size;
        }

        public Map.Entry<K, V> nextEntry() {
            if (hasNext()) {
                Flat3Map<K, V> flat3Map = this.parent;
                int i = this.nextIndex + 1;
                this.nextIndex = i;
                FlatMapEntry<K, V> flatMapEntry = new FlatMapEntry<>(flat3Map, i);
                this.currentEntry = flatMapEntry;
                return flatMapEntry;
            }
            throw new NoSuchElementException("No next() entry in the iteration");
        }

        public void remove() {
            FlatMapEntry<K, V> flatMapEntry = this.currentEntry;
            if (flatMapEntry != null) {
                flatMapEntry.setRemoved(true);
                this.parent.remove(this.currentEntry.getKey());
                this.nextIndex--;
                this.currentEntry = null;
                return;
            }
            throw new IllegalStateException("remove() can only be called once after next()");
        }
    }

    static class EntrySetIterator<K, V> extends EntryIterator<K, V> implements Iterator<Map.Entry<K, V>> {
        EntrySetIterator(Flat3Map<K, V> flat3Map) {
            super(flat3Map);
        }

        public Map.Entry<K, V> next() {
            return nextEntry();
        }
    }

    public Set<K> keySet() {
        AbstractHashedMap<K, V> abstractHashedMap = this.delegateMap;
        if (abstractHashedMap != null) {
            return abstractHashedMap.keySet();
        }
        return new KeySet(this);
    }

    static class KeySet<K> extends AbstractSet<K> {
        private final Flat3Map<K, ?> parent;

        KeySet(Flat3Map<K, ?> flat3Map) {
            this.parent = flat3Map;
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
            if (this.parent.delegateMap != null) {
                return this.parent.delegateMap.keySet().iterator();
            }
            if (this.parent.size() == 0) {
                return EmptyIterator.emptyIterator();
            }
            return new KeySetIterator(this.parent);
        }
    }

    static class KeySetIterator<K> extends EntryIterator<K, Object> implements Iterator<K> {
        KeySetIterator(Flat3Map<K, ?> flat3Map) {
            super(flat3Map);
        }

        public K next() {
            return nextEntry().getKey();
        }
    }

    public Collection<V> values() {
        AbstractHashedMap<K, V> abstractHashedMap = this.delegateMap;
        if (abstractHashedMap != null) {
            return abstractHashedMap.values();
        }
        return new Values(this);
    }

    static class Values<V> extends AbstractCollection<V> {
        private final Flat3Map<?, V> parent;

        Values(Flat3Map<?, V> flat3Map) {
            this.parent = flat3Map;
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
            if (this.parent.delegateMap != null) {
                return this.parent.delegateMap.values().iterator();
            }
            if (this.parent.size() == 0) {
                return EmptyIterator.emptyIterator();
            }
            return new ValuesIterator(this.parent);
        }
    }

    static class ValuesIterator<V> extends EntryIterator<Object, V> implements Iterator<V> {
        ValuesIterator(Flat3Map<?, V> flat3Map) {
            super(flat3Map);
        }

        public V next() {
            return nextEntry().getValue();
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(size());
        MapIterator mapIterator = mapIterator();
        while (mapIterator.hasNext()) {
            objectOutputStream.writeObject(mapIterator.next());
            objectOutputStream.writeObject(mapIterator.getValue());
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        int readInt = objectInputStream.readInt();
        if (readInt > 3) {
            this.delegateMap = createDelegateMap();
        }
        while (readInt > 0) {
            put(objectInputStream.readObject(), objectInputStream.readObject());
            readInt--;
        }
    }

    public Flat3Map<K, V> clone() {
        try {
            Flat3Map<K, V> flat3Map = (Flat3Map) super.clone();
            if (flat3Map.delegateMap != null) {
                flat3Map.delegateMap = flat3Map.delegateMap.clone();
            }
            return flat3Map;
        } catch (CloneNotSupportedException unused) {
            throw new InternalError();
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        AbstractHashedMap<K, V> abstractHashedMap = this.delegateMap;
        if (abstractHashedMap != null) {
            return abstractHashedMap.equals(obj);
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        if (this.size != map.size()) {
            return false;
        }
        int i = this.size;
        if (i > 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        if (!map.containsKey(this.key3)) {
                            return false;
                        }
                        Object obj2 = map.get(this.key3);
                        V v = this.value3;
                        if (v != null ? !v.equals(obj2) : obj2 != null) {
                            return false;
                        }
                    }
                }
                if (!map.containsKey(this.key2)) {
                    return false;
                }
                Object obj3 = map.get(this.key2);
                V v2 = this.value2;
                if (v2 != null ? !v2.equals(obj3) : obj3 != null) {
                    return false;
                }
            }
            if (!map.containsKey(this.key1)) {
                return false;
            }
            Object obj4 = map.get(this.key1);
            V v3 = this.value1;
            if (v3 != null ? v3.equals(obj4) : obj4 == null) {
                return true;
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i;
        int i2;
        AbstractHashedMap<K, V> abstractHashedMap = this.delegateMap;
        if (abstractHashedMap != null) {
            return abstractHashedMap.hashCode();
        }
        int i3 = this.size;
        int i4 = 0;
        if (i3 == 0) {
            return 0;
        }
        if (i3 != 1) {
            if (i3 == 2) {
                i2 = 0;
            } else if (i3 == 3) {
                int i5 = this.hash3;
                V v = this.value3;
                i2 = (i5 ^ (v == null ? 0 : v.hashCode())) + 0;
            } else {
                throw new IllegalStateException("Invalid map index: " + this.size);
            }
            int i6 = this.hash2;
            V v2 = this.value2;
            i = i2 + (i6 ^ (v2 == null ? 0 : v2.hashCode()));
        } else {
            i = 0;
        }
        int i7 = this.hash1;
        V v3 = this.value1;
        if (v3 != null) {
            i4 = v3.hashCode();
        }
        return (i4 ^ i7) + i;
    }

    public String toString() {
        AbstractHashedMap<K, V> abstractHashedMap = this.delegateMap;
        if (abstractHashedMap != null) {
            return abstractHashedMap.toString();
        }
        if (this.size == 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(128);
        sb.append('{');
        int i = this.size;
        V v = "(this Map)";
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    K k = this.key3;
                    if (k == this) {
                        k = v;
                    }
                    sb.append(k);
                    sb.append('=');
                    V v2 = this.value3;
                    if (v2 == this) {
                        v2 = v;
                    }
                    sb.append(v2);
                    sb.append(',');
                } else {
                    throw new IllegalStateException("Invalid map index: " + this.size);
                }
            }
            K k2 = this.key2;
            if (k2 == this) {
                k2 = v;
            }
            sb.append(k2);
            sb.append('=');
            V v3 = this.value2;
            if (v3 == this) {
                v3 = v;
            }
            sb.append(v3);
            sb.append(',');
        }
        K k3 = this.key1;
        if (k3 == this) {
            k3 = v;
        }
        sb.append(k3);
        sb.append('=');
        V v4 = this.value1;
        if (v4 != this) {
            v = v4;
        }
        sb.append(v);
        sb.append('}');
        return sb.toString();
    }
}
