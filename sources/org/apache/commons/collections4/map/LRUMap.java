package org.apache.commons.collections4.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import org.apache.commons.collections4.BoundedMap;
import org.apache.commons.collections4.map.AbstractHashedMap;
import org.apache.commons.collections4.map.AbstractLinkedMap;

public class LRUMap<K, V> extends AbstractLinkedMap<K, V> implements BoundedMap<K, V>, Serializable, Cloneable {
    protected static final int DEFAULT_MAX_SIZE = 100;
    private static final long serialVersionUID = -612114643488955218L;
    private transient int maxSize;
    private boolean scanUntilRemovable;

    /* access modifiers changed from: protected */
    public boolean removeLRU(AbstractLinkedMap.LinkEntry<K, V> linkEntry) {
        return true;
    }

    public LRUMap() {
        this(100, 0.75f, false);
    }

    public LRUMap(int i) {
        this(i, 0.75f);
    }

    public LRUMap(int i, int i2) {
        this(i, i2, 0.75f);
    }

    public LRUMap(int i, boolean z) {
        this(i, 0.75f, z);
    }

    public LRUMap(int i, float f) {
        this(i, f, false);
    }

    public LRUMap(int i, int i2, float f) {
        this(i, i2, f, false);
    }

    public LRUMap(int i, float f, boolean z) {
        this(i, i, f, z);
    }

    public LRUMap(int i, int i2, float f, boolean z) {
        super(i2, f);
        if (i < 1) {
            throw new IllegalArgumentException("LRUMap max size must be greater than 0");
        } else if (i2 <= i) {
            this.maxSize = i;
            this.scanUntilRemovable = z;
        } else {
            throw new IllegalArgumentException("LRUMap initial size must not be greather than max size");
        }
    }

    public LRUMap(Map<? extends K, ? extends V> map) {
        this(map, false);
    }

    public LRUMap(Map<? extends K, ? extends V> map, boolean z) {
        this(map.size(), 0.75f, z);
        putAll(map);
    }

    public V get(Object obj) {
        return get(obj, true);
    }

    public V get(Object obj, boolean z) {
        AbstractLinkedMap.LinkEntry entry = getEntry(obj);
        if (entry == null) {
            return null;
        }
        if (z) {
            moveToMRU(entry);
        }
        return entry.getValue();
    }

    /* access modifiers changed from: protected */
    public void moveToMRU(AbstractLinkedMap.LinkEntry<K, V> linkEntry) {
        if (linkEntry.after != this.header) {
            this.modCount++;
            if (linkEntry.before != null) {
                linkEntry.before.after = linkEntry.after;
                linkEntry.after.before = linkEntry.before;
                linkEntry.after = this.header;
                linkEntry.before = this.header.before;
                this.header.before.after = linkEntry;
                this.header.before = linkEntry;
                return;
            }
            throw new IllegalStateException("Entry.before is null. Please check that your keys are immutable, and that you have used synchronization properly. If so, then please report this to dev@commons.apache.org as a bug.");
        } else if (linkEntry == this.header) {
            throw new IllegalStateException("Can't move header to MRU (please report this to dev@commons.apache.org)");
        }
    }

    /* access modifiers changed from: protected */
    public void updateEntry(AbstractHashedMap.HashEntry<K, V> hashEntry, V v) {
        moveToMRU((AbstractLinkedMap.LinkEntry) hashEntry);
        hashEntry.setValue(v);
    }

    /* access modifiers changed from: protected */
    public void addMapping(int i, int i2, K k, V v) {
        K k2 = k;
        V v2 = v;
        if (isFull()) {
            AbstractLinkedMap.LinkEntry<K, V> linkEntry = this.header.after;
            boolean z = false;
            if (this.scanUntilRemovable) {
                while (true) {
                    if (linkEntry == this.header || linkEntry == null) {
                        break;
                    } else if (removeLRU(linkEntry)) {
                        z = true;
                        break;
                    } else {
                        linkEntry = linkEntry.after;
                    }
                }
                if (linkEntry == null) {
                    throw new IllegalStateException("Entry.after=null, header.after" + this.header.after + " header.before" + this.header.before + " key=" + k2 + " value=" + v2 + " size=" + this.size + " maxSize=" + this.maxSize + " Please check that your keys are immutable, and that you have used synchronization properly." + " If so, then please report this to dev@commons.apache.org as a bug.");
                }
            } else {
                z = removeLRU(linkEntry);
            }
            AbstractLinkedMap.LinkEntry<K, V> linkEntry2 = linkEntry;
            if (!z) {
                super.addMapping(i, i2, k, v);
            } else if (linkEntry2 != null) {
                reuseMapping(linkEntry2, i, i2, k, v);
            } else {
                throw new IllegalStateException("reuse=null, header.after=" + this.header.after + " header.before" + this.header.before + " key=" + k2 + " value=" + v2 + " size=" + this.size + " maxSize=" + this.maxSize + " Please check that your keys are immutable, and that you have used synchronization properly." + " If so, then please report this to dev@commons.apache.org as a bug.");
            }
        } else {
            super.addMapping(i, i2, k, v);
        }
    }

    /* access modifiers changed from: protected */
    public void reuseMapping(AbstractLinkedMap.LinkEntry<K, V> linkEntry, int i, int i2, K k, V v) {
        AbstractLinkedMap.LinkEntry<K, V> linkEntry2 = linkEntry;
        K k2 = k;
        V v2 = v;
        boolean z = true;
        try {
            int hashIndex = hashIndex(linkEntry2.hashCode, this.data.length);
            AbstractHashedMap.HashEntry<K, V> hashEntry = this.data[hashIndex];
            AbstractHashedMap.HashEntry<K, V> hashEntry2 = null;
            while (hashEntry != linkEntry2 && hashEntry != null) {
                hashEntry2 = hashEntry;
                hashEntry = hashEntry.next;
            }
            if (hashEntry != null) {
                this.modCount++;
                removeEntry(linkEntry2, hashIndex, hashEntry2);
                reuseEntry(linkEntry, i, i2, k, v);
                addEntry(linkEntry, i);
                return;
            }
            throw new IllegalStateException("Entry.next=null, data[removeIndex]=" + this.data[hashIndex] + " previous=" + hashEntry2 + " key=" + k2 + " value=" + v2 + " size=" + this.size + " maxSize=" + this.maxSize + " Please check that your keys are immutable, and that you have used synchronization properly." + " If so, then please report this to dev@commons.apache.org as a bug.");
        } catch (NullPointerException unused) {
            StringBuilder sb = new StringBuilder();
            sb.append("NPE, entry=");
            sb.append(linkEntry2);
            sb.append(" entryIsHeader=");
            if (linkEntry2 != this.header) {
                z = false;
            }
            sb.append(z);
            sb.append(" key=");
            sb.append(k2);
            sb.append(" value=");
            sb.append(v2);
            sb.append(" size=");
            sb.append(this.size);
            sb.append(" maxSize=");
            sb.append(this.maxSize);
            sb.append(" Please check that your keys are immutable, and that you have used synchronization properly.");
            sb.append(" If so, then please report this to dev@commons.apache.org as a bug.");
            throw new IllegalStateException(sb.toString());
        }
    }

    public boolean isFull() {
        return this.size >= this.maxSize;
    }

    public int maxSize() {
        return this.maxSize;
    }

    public boolean isScanUntilRemovable() {
        return this.scanUntilRemovable;
    }

    public LRUMap<K, V> clone() {
        return (LRUMap) super.clone();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        doWriteObject(objectOutputStream);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        doReadObject(objectInputStream);
    }

    /* access modifiers changed from: protected */
    public void doWriteObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(this.maxSize);
        super.doWriteObject(objectOutputStream);
    }

    /* access modifiers changed from: protected */
    public void doReadObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.maxSize = objectInputStream.readInt();
        super.doReadObject(objectInputStream);
    }
}
