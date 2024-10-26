package org.apache.commons.collections4.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.keyvalue.MultiKey;
import org.apache.commons.collections4.map.AbstractHashedMap;

public class MultiKeyMap<K, V> extends AbstractMapDecorator<MultiKey<? extends K>, V> implements Serializable, Cloneable {
    private static final long serialVersionUID = -1788199231038721040L;

    public static <K, V> MultiKeyMap<K, V> multiKeyMap(AbstractHashedMap<MultiKey<? extends K>, V> abstractHashedMap) {
        if (abstractHashedMap == null) {
            throw new NullPointerException("Map must not be null");
        } else if (abstractHashedMap.size() <= 0) {
            return new MultiKeyMap<>(abstractHashedMap);
        } else {
            throw new IllegalArgumentException("Map must be empty");
        }
    }

    public MultiKeyMap() {
        this(new HashedMap());
    }

    protected MultiKeyMap(AbstractHashedMap<MultiKey<? extends K>, V> abstractHashedMap) {
        super(abstractHashedMap);
        this.map = abstractHashedMap;
    }

    public V get(Object obj, Object obj2) {
        int hash = hash(obj, obj2);
        for (AbstractHashedMap.HashEntry<K, V> hashEntry = decorated().data[decorated().hashIndex(hash, decorated().data.length)]; hashEntry != null; hashEntry = hashEntry.next) {
            if (hashEntry.hashCode == hash && isEqualKey(hashEntry, obj, obj2)) {
                return hashEntry.getValue();
            }
        }
        return null;
    }

    public boolean containsKey(Object obj, Object obj2) {
        int hash = hash(obj, obj2);
        for (AbstractHashedMap.HashEntry<K, V> hashEntry = decorated().data[decorated().hashIndex(hash, decorated().data.length)]; hashEntry != null; hashEntry = hashEntry.next) {
            if (hashEntry.hashCode == hash && isEqualKey(hashEntry, obj, obj2)) {
                return true;
            }
        }
        return false;
    }

    public V put(K k, K k2, V v) {
        int hash = hash(k, k2);
        int hashIndex = decorated().hashIndex(hash, decorated().data.length);
        AbstractHashedMap.HashEntry<K, V> hashEntry = decorated().data[hashIndex];
        while (hashEntry != null) {
            if (hashEntry.hashCode != hash || !isEqualKey(hashEntry, k, k2)) {
                hashEntry = hashEntry.next;
            } else {
                V value = hashEntry.getValue();
                decorated().updateEntry(hashEntry, v);
                return value;
            }
        }
        decorated().addMapping(hashIndex, hash, new MultiKey(k, k2), v);
        return null;
    }

    public V removeMultiKey(Object obj, Object obj2) {
        int hash = hash(obj, obj2);
        int hashIndex = decorated().hashIndex(hash, decorated().data.length);
        AbstractHashedMap.HashEntry<K, V> hashEntry = decorated().data[hashIndex];
        AbstractHashedMap.HashEntry<K, V> hashEntry2 = null;
        while (hashEntry != null) {
            if (hashEntry.hashCode != hash || !isEqualKey(hashEntry, obj, obj2)) {
                hashEntry2 = hashEntry;
                hashEntry = hashEntry.next;
            } else {
                V value = hashEntry.getValue();
                decorated().removeMapping(hashEntry, hashIndex, hashEntry2);
                return value;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public int hash(Object obj, Object obj2) {
        int i = 0;
        if (obj != null) {
            i = 0 ^ obj.hashCode();
        }
        if (obj2 != null) {
            i ^= obj2.hashCode();
        }
        int i2 = i + (~(i << 9));
        int i3 = (i2 >>> 14) ^ i2;
        int i4 = i3 + (i3 << 4);
        return i4 ^ (i4 >>> 10);
    }

    /* access modifiers changed from: protected */
    public boolean isEqualKey(AbstractHashedMap.HashEntry<MultiKey<? extends K>, V> hashEntry, Object obj, Object obj2) {
        MultiKey key = hashEntry.getKey();
        if (key.size() == 2 && (obj == key.getKey(0) || (obj != null && obj.equals(key.getKey(0))))) {
            if (obj2 != key.getKey(1)) {
                return obj2 != null && obj2.equals(key.getKey(1));
            }
            return true;
        }
    }

    public V get(Object obj, Object obj2, Object obj3) {
        int hash = hash(obj, obj2, obj3);
        for (AbstractHashedMap.HashEntry<K, V> hashEntry = decorated().data[decorated().hashIndex(hash, decorated().data.length)]; hashEntry != null; hashEntry = hashEntry.next) {
            if (hashEntry.hashCode == hash && isEqualKey(hashEntry, obj, obj2, obj3)) {
                return hashEntry.getValue();
            }
        }
        return null;
    }

    public boolean containsKey(Object obj, Object obj2, Object obj3) {
        int hash = hash(obj, obj2, obj3);
        for (AbstractHashedMap.HashEntry<K, V> hashEntry = decorated().data[decorated().hashIndex(hash, decorated().data.length)]; hashEntry != null; hashEntry = hashEntry.next) {
            if (hashEntry.hashCode == hash && isEqualKey(hashEntry, obj, obj2, obj3)) {
                return true;
            }
        }
        return false;
    }

    public V put(K k, K k2, K k3, V v) {
        int hash = hash(k, k2, k3);
        int hashIndex = decorated().hashIndex(hash, decorated().data.length);
        AbstractHashedMap.HashEntry<K, V> hashEntry = decorated().data[hashIndex];
        while (hashEntry != null) {
            if (hashEntry.hashCode != hash || !isEqualKey(hashEntry, k, k2, k3)) {
                hashEntry = hashEntry.next;
            } else {
                V value = hashEntry.getValue();
                decorated().updateEntry(hashEntry, v);
                return value;
            }
        }
        decorated().addMapping(hashIndex, hash, new MultiKey(k, k2, k3), v);
        return null;
    }

    public V removeMultiKey(Object obj, Object obj2, Object obj3) {
        int hash = hash(obj, obj2, obj3);
        int hashIndex = decorated().hashIndex(hash, decorated().data.length);
        AbstractHashedMap.HashEntry<K, V> hashEntry = decorated().data[hashIndex];
        AbstractHashedMap.HashEntry<K, V> hashEntry2 = null;
        while (hashEntry != null) {
            if (hashEntry.hashCode != hash || !isEqualKey(hashEntry, obj, obj2, obj3)) {
                hashEntry2 = hashEntry;
                hashEntry = hashEntry.next;
            } else {
                V value = hashEntry.getValue();
                decorated().removeMapping(hashEntry, hashIndex, hashEntry2);
                return value;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public int hash(Object obj, Object obj2, Object obj3) {
        int i = 0;
        if (obj != null) {
            i = 0 ^ obj.hashCode();
        }
        if (obj2 != null) {
            i ^= obj2.hashCode();
        }
        if (obj3 != null) {
            i ^= obj3.hashCode();
        }
        int i2 = i + (~(i << 9));
        int i3 = (i2 >>> 14) ^ i2;
        int i4 = i3 + (i3 << 4);
        return i4 ^ (i4 >>> 10);
    }

    /* access modifiers changed from: protected */
    public boolean isEqualKey(AbstractHashedMap.HashEntry<MultiKey<? extends K>, V> hashEntry, Object obj, Object obj2, Object obj3) {
        MultiKey key = hashEntry.getKey();
        if (key.size() == 3 && ((obj == key.getKey(0) || (obj != null && obj.equals(key.getKey(0)))) && (obj2 == key.getKey(1) || (obj2 != null && obj2.equals(key.getKey(1)))))) {
            if (obj3 != key.getKey(2)) {
                return obj3 != null && obj3.equals(key.getKey(2));
            }
            return true;
        }
    }

    public V get(Object obj, Object obj2, Object obj3, Object obj4) {
        int hash = hash(obj, obj2, obj3, obj4);
        for (AbstractHashedMap.HashEntry<K, V> hashEntry = decorated().data[decorated().hashIndex(hash, decorated().data.length)]; hashEntry != null; hashEntry = hashEntry.next) {
            if (hashEntry.hashCode == hash && isEqualKey(hashEntry, obj, obj2, obj3, obj4)) {
                return hashEntry.getValue();
            }
        }
        return null;
    }

    public boolean containsKey(Object obj, Object obj2, Object obj3, Object obj4) {
        int hash = hash(obj, obj2, obj3, obj4);
        for (AbstractHashedMap.HashEntry<K, V> hashEntry = decorated().data[decorated().hashIndex(hash, decorated().data.length)]; hashEntry != null; hashEntry = hashEntry.next) {
            if (hashEntry.hashCode == hash && isEqualKey(hashEntry, obj, obj2, obj3, obj4)) {
                return true;
            }
        }
        return false;
    }

    public V put(K k, K k2, K k3, K k4, V v) {
        int hash = hash(k, k2, k3, k4);
        int hashIndex = decorated().hashIndex(hash, decorated().data.length);
        AbstractHashedMap.HashEntry<K, V> hashEntry = decorated().data[hashIndex];
        while (hashEntry != null) {
            if (hashEntry.hashCode != hash || !isEqualKey(hashEntry, k, k2, k3, k4)) {
                hashEntry = hashEntry.next;
            } else {
                V value = hashEntry.getValue();
                decorated().updateEntry(hashEntry, v);
                return value;
            }
        }
        decorated().addMapping(hashIndex, hash, new MultiKey(k, k2, k3, k4), v);
        return null;
    }

    public V removeMultiKey(Object obj, Object obj2, Object obj3, Object obj4) {
        int hash = hash(obj, obj2, obj3, obj4);
        int hashIndex = decorated().hashIndex(hash, decorated().data.length);
        AbstractHashedMap.HashEntry<K, V> hashEntry = decorated().data[hashIndex];
        AbstractHashedMap.HashEntry<K, V> hashEntry2 = null;
        while (hashEntry != null) {
            if (hashEntry.hashCode != hash || !isEqualKey(hashEntry, obj, obj2, obj3, obj4)) {
                hashEntry2 = hashEntry;
                hashEntry = hashEntry.next;
            } else {
                V value = hashEntry.getValue();
                decorated().removeMapping(hashEntry, hashIndex, hashEntry2);
                return value;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public int hash(Object obj, Object obj2, Object obj3, Object obj4) {
        int i = 0;
        if (obj != null) {
            i = 0 ^ obj.hashCode();
        }
        if (obj2 != null) {
            i ^= obj2.hashCode();
        }
        if (obj3 != null) {
            i ^= obj3.hashCode();
        }
        if (obj4 != null) {
            i ^= obj4.hashCode();
        }
        int i2 = i + (~(i << 9));
        int i3 = (i2 >>> 14) ^ i2;
        int i4 = i3 + (i3 << 4);
        return i4 ^ (i4 >>> 10);
    }

    /* access modifiers changed from: protected */
    public boolean isEqualKey(AbstractHashedMap.HashEntry<MultiKey<? extends K>, V> hashEntry, Object obj, Object obj2, Object obj3, Object obj4) {
        MultiKey key = hashEntry.getKey();
        if (key.size() == 4 && ((obj == key.getKey(0) || (obj != null && obj.equals(key.getKey(0)))) && ((obj2 == key.getKey(1) || (obj2 != null && obj2.equals(key.getKey(1)))) && (obj3 == key.getKey(2) || (obj3 != null && obj3.equals(key.getKey(2))))))) {
            if (obj4 != key.getKey(3)) {
                return obj4 != null && obj4.equals(key.getKey(3));
            }
            return true;
        }
    }

    public V get(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        int hash = hash(obj, obj2, obj3, obj4, obj5);
        for (AbstractHashedMap.HashEntry<K, V> hashEntry = decorated().data[decorated().hashIndex(hash, decorated().data.length)]; hashEntry != null; hashEntry = hashEntry.next) {
            if (hashEntry.hashCode == hash && isEqualKey(hashEntry, obj, obj2, obj3, obj4, obj5)) {
                return hashEntry.getValue();
            }
        }
        return null;
    }

    public boolean containsKey(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        int hash = hash(obj, obj2, obj3, obj4, obj5);
        for (AbstractHashedMap.HashEntry<K, V> hashEntry = decorated().data[decorated().hashIndex(hash, decorated().data.length)]; hashEntry != null; hashEntry = hashEntry.next) {
            if (hashEntry.hashCode == hash && isEqualKey(hashEntry, obj, obj2, obj3, obj4, obj5)) {
                return true;
            }
        }
        return false;
    }

    public V put(K k, K k2, K k3, K k4, K k5, V v) {
        V v2 = v;
        int hash = hash(k, k2, k3, k4, k5);
        int hashIndex = decorated().hashIndex(hash, decorated().data.length);
        AbstractHashedMap.HashEntry<K, V> hashEntry = decorated().data[hashIndex];
        while (hashEntry != null) {
            if (hashEntry.hashCode != hash || !isEqualKey(hashEntry, k, k2, k3, k4, k5)) {
                hashEntry = hashEntry.next;
            } else {
                V value = hashEntry.getValue();
                decorated().updateEntry(hashEntry, v2);
                return value;
            }
        }
        decorated().addMapping(hashIndex, hash, new MultiKey(k, k2, k3, k4, k5), v2);
        return null;
    }

    public V removeMultiKey(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        int hash = hash(obj, obj2, obj3, obj4, obj5);
        int hashIndex = decorated().hashIndex(hash, decorated().data.length);
        AbstractHashedMap.HashEntry<K, V> hashEntry = decorated().data[hashIndex];
        AbstractHashedMap.HashEntry<K, V> hashEntry2 = null;
        while (hashEntry != null) {
            if (hashEntry.hashCode != hash || !isEqualKey(hashEntry, obj, obj2, obj3, obj4, obj5)) {
                hashEntry2 = hashEntry;
                hashEntry = hashEntry.next;
            } else {
                V value = hashEntry.getValue();
                decorated().removeMapping(hashEntry, hashIndex, hashEntry2);
                return value;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public int hash(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        int i = 0;
        if (obj != null) {
            i = 0 ^ obj.hashCode();
        }
        if (obj2 != null) {
            i ^= obj2.hashCode();
        }
        if (obj3 != null) {
            i ^= obj3.hashCode();
        }
        if (obj4 != null) {
            i ^= obj4.hashCode();
        }
        if (obj5 != null) {
            i ^= obj5.hashCode();
        }
        int i2 = i + (~(i << 9));
        int i3 = (i2 >>> 14) ^ i2;
        int i4 = i3 + (i3 << 4);
        return i4 ^ (i4 >>> 10);
    }

    /* access modifiers changed from: protected */
    public boolean isEqualKey(AbstractHashedMap.HashEntry<MultiKey<? extends K>, V> hashEntry, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        MultiKey key = hashEntry.getKey();
        if (key.size() == 5 && ((obj == key.getKey(0) || (obj != null && obj.equals(key.getKey(0)))) && ((obj2 == key.getKey(1) || (obj2 != null && obj2.equals(key.getKey(1)))) && ((obj3 == key.getKey(2) || (obj3 != null && obj3.equals(key.getKey(2)))) && (obj4 == key.getKey(3) || (obj4 != null && obj4.equals(key.getKey(3)))))))) {
            if (obj5 != key.getKey(4)) {
                return obj5 != null && obj5.equals(key.getKey(4));
            }
            return true;
        }
    }

    public boolean removeAll(Object obj) {
        MapIterator mapIterator = mapIterator();
        boolean z = false;
        while (mapIterator.hasNext()) {
            MultiKey multiKey = (MultiKey) mapIterator.next();
            if (multiKey.size() >= 1) {
                Object key = multiKey.getKey(0);
                if (obj == null) {
                    if (key != null) {
                    }
                } else if (!obj.equals(key)) {
                }
                mapIterator.remove();
                z = true;
            }
        }
        return z;
    }

    public boolean removeAll(Object obj, Object obj2) {
        MapIterator mapIterator = mapIterator();
        boolean z = false;
        while (mapIterator.hasNext()) {
            MultiKey multiKey = (MultiKey) mapIterator.next();
            if (multiKey.size() >= 2) {
                Object key = multiKey.getKey(0);
                if (obj == null) {
                    if (key != null) {
                    }
                } else if (!obj.equals(key)) {
                }
                Object key2 = multiKey.getKey(1);
                if (obj2 == null) {
                    if (key2 != null) {
                    }
                } else if (!obj2.equals(key2)) {
                }
                mapIterator.remove();
                z = true;
            }
        }
        return z;
    }

    public boolean removeAll(Object obj, Object obj2, Object obj3) {
        MapIterator mapIterator = mapIterator();
        boolean z = false;
        while (mapIterator.hasNext()) {
            MultiKey multiKey = (MultiKey) mapIterator.next();
            if (multiKey.size() >= 3) {
                Object key = multiKey.getKey(0);
                if (obj == null) {
                    if (key != null) {
                    }
                } else if (!obj.equals(key)) {
                }
                Object key2 = multiKey.getKey(1);
                if (obj2 == null) {
                    if (key2 != null) {
                    }
                } else if (!obj2.equals(key2)) {
                }
                Object key3 = multiKey.getKey(2);
                if (obj3 == null) {
                    if (key3 != null) {
                    }
                } else if (!obj3.equals(key3)) {
                }
                mapIterator.remove();
                z = true;
            }
        }
        return z;
    }

    public boolean removeAll(Object obj, Object obj2, Object obj3, Object obj4) {
        MapIterator mapIterator = mapIterator();
        boolean z = false;
        while (mapIterator.hasNext()) {
            MultiKey multiKey = (MultiKey) mapIterator.next();
            if (multiKey.size() >= 4) {
                Object key = multiKey.getKey(0);
                if (obj == null) {
                    if (key != null) {
                    }
                } else if (!obj.equals(key)) {
                }
                Object key2 = multiKey.getKey(1);
                if (obj2 == null) {
                    if (key2 != null) {
                    }
                } else if (!obj2.equals(key2)) {
                }
                Object key3 = multiKey.getKey(2);
                if (obj3 == null) {
                    if (key3 != null) {
                    }
                } else if (!obj3.equals(key3)) {
                }
                Object key4 = multiKey.getKey(3);
                if (obj4 == null) {
                    if (key4 != null) {
                    }
                } else if (!obj4.equals(key4)) {
                }
                mapIterator.remove();
                z = true;
            }
        }
        return z;
    }

    /* access modifiers changed from: protected */
    public void checkKey(MultiKey<?> multiKey) {
        if (multiKey == null) {
            throw new NullPointerException("Key must not be null");
        }
    }

    public MultiKeyMap<K, V> clone() {
        try {
            return (MultiKeyMap) super.clone();
        } catch (CloneNotSupportedException unused) {
            throw new InternalError();
        }
    }

    public V put(MultiKey<? extends K> multiKey, V v) {
        checkKey(multiKey);
        return super.put(multiKey, v);
    }

    public void putAll(Map<? extends MultiKey<? extends K>, ? extends V> map) {
        for (MultiKey checkKey : map.keySet()) {
            checkKey(checkKey);
        }
        super.putAll(map);
    }

    public MapIterator<MultiKey<? extends K>, V> mapIterator() {
        return decorated().mapIterator();
    }

    /* access modifiers changed from: protected */
    public AbstractHashedMap<MultiKey<? extends K>, V> decorated() {
        return (AbstractHashedMap) super.decorated();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(this.map);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.map = (Map) objectInputStream.readObject();
    }
}
