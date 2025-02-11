package com.google.common.collect;

import com.google.common.collect.ImmutableMapEntry;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.Nullable;

class RegularImmutableBiMap<K, V> extends ImmutableBiMap<K, V> {
    static final double MAX_LOAD_FACTOR = 1.2d;
    /* access modifiers changed from: private */
    public final transient ImmutableMapEntry<K, V>[] entries;
    /* access modifiers changed from: private */
    public final transient int hashCode;
    private transient ImmutableBiMap<V, K> inverse;
    private final transient ImmutableMapEntry<K, V>[] keyTable;
    /* access modifiers changed from: private */
    public final transient int mask;
    /* access modifiers changed from: private */
    public final transient ImmutableMapEntry<K, V>[] valueTable;

    /* access modifiers changed from: package-private */
    public boolean isPartialView() {
        return false;
    }

    RegularImmutableBiMap(ImmutableMapEntry.TerminalEntry<?, ?>... terminalEntryArr) {
        this(terminalEntryArr.length, terminalEntryArr);
    }

    /* JADX WARNING: type inference failed for: r8v4, types: [com.google.common.collect.RegularImmutableBiMap$NonTerminalBiMapEntry] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    RegularImmutableBiMap(int r18, com.google.common.collect.ImmutableMapEntry.TerminalEntry<?, ?>[] r19) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r17.<init>()
            r2 = 4608083138725491507(0x3ff3333333333333, double:1.2)
            int r2 = com.google.common.collect.Hashing.closedTableSize(r1, r2)
            int r3 = r2 + -1
            r0.mask = r3
            com.google.common.collect.ImmutableMapEntry[] r3 = createEntryArray(r2)
            com.google.common.collect.ImmutableMapEntry[] r2 = createEntryArray(r2)
            com.google.common.collect.ImmutableMapEntry[] r4 = createEntryArray(r18)
            r5 = 0
            r6 = 0
        L_0x0022:
            if (r5 >= r1) goto L_0x0099
            r7 = r19[r5]
            java.lang.Object r8 = r7.getKey()
            java.lang.Object r9 = r7.getValue()
            int r10 = r8.hashCode()
            int r11 = r9.hashCode()
            int r12 = com.google.common.collect.Hashing.smear(r10)
            int r13 = r0.mask
            r12 = r12 & r13
            int r13 = com.google.common.collect.Hashing.smear(r11)
            int r14 = r0.mask
            r13 = r13 & r14
            r14 = r3[r12]
            r15 = r14
        L_0x0047:
            if (r15 == 0) goto L_0x0063
            java.lang.Object r1 = r15.getKey()
            boolean r1 = r8.equals(r1)
            r1 = r1 ^ 1
            r16 = r8
            java.lang.String r8 = "key"
            checkNoConflict(r1, r8, r7, r15)
            com.google.common.collect.ImmutableMapEntry r15 = r15.getNextInKeyBucket()
            r1 = r18
            r8 = r16
            goto L_0x0047
        L_0x0063:
            r1 = r2[r13]
            r8 = r1
        L_0x0066:
            if (r8 == 0) goto L_0x0080
            java.lang.Object r15 = r8.getValue()
            boolean r15 = r9.equals(r15)
            r15 = r15 ^ 1
            r16 = r9
            java.lang.String r9 = "value"
            checkNoConflict(r15, r9, r7, r8)
            com.google.common.collect.ImmutableMapEntry r8 = r8.getNextInValueBucket()
            r9 = r16
            goto L_0x0066
        L_0x0080:
            if (r14 != 0) goto L_0x0085
            if (r1 != 0) goto L_0x0085
            goto L_0x008b
        L_0x0085:
            com.google.common.collect.RegularImmutableBiMap$NonTerminalBiMapEntry r8 = new com.google.common.collect.RegularImmutableBiMap$NonTerminalBiMapEntry
            r8.<init>(r7, r14, r1)
            r7 = r8
        L_0x008b:
            r3[r12] = r7
            r2[r13] = r7
            r4[r5] = r7
            r1 = r10 ^ r11
            int r6 = r6 + r1
            int r5 = r5 + 1
            r1 = r18
            goto L_0x0022
        L_0x0099:
            r0.keyTable = r3
            r0.valueTable = r2
            r0.entries = r4
            r0.hashCode = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.RegularImmutableBiMap.<init>(int, com.google.common.collect.ImmutableMapEntry$TerminalEntry[]):void");
    }

    RegularImmutableBiMap(Map.Entry<?, ?>[] entryArr) {
        RegularImmutableBiMap regularImmutableBiMap = this;
        Map.Entry<?, ?>[] entryArr2 = entryArr;
        int length = entryArr2.length;
        int closedTableSize = Hashing.closedTableSize(length, MAX_LOAD_FACTOR);
        regularImmutableBiMap.mask = closedTableSize - 1;
        ImmutableMapEntry<K, V>[] createEntryArray = createEntryArray(closedTableSize);
        ImmutableMapEntry<K, V>[] createEntryArray2 = createEntryArray(closedTableSize);
        ImmutableMapEntry<K, V>[] createEntryArray3 = createEntryArray(length);
        int i = 0;
        int i2 = 0;
        while (i < length) {
            Map.Entry<?, ?> entry = entryArr2[i];
            Object key = entry.getKey();
            Object value = entry.getValue();
            CollectPreconditions.checkEntryNotNull(key, value);
            int hashCode2 = key.hashCode();
            int hashCode3 = value.hashCode();
            int smear = Hashing.smear(hashCode2) & regularImmutableBiMap.mask;
            int smear2 = Hashing.smear(hashCode3) & regularImmutableBiMap.mask;
            ImmutableMapEntry<K, V> immutableMapEntry = createEntryArray[smear];
            ImmutableMapEntry<K, V> immutableMapEntry2 = immutableMapEntry;
            while (immutableMapEntry2 != null) {
                checkNoConflict(!key.equals(immutableMapEntry2.getKey()), "key", entry, immutableMapEntry2);
                immutableMapEntry2 = immutableMapEntry2.getNextInKeyBucket();
                length = length;
            }
            int i3 = length;
            ImmutableMapEntry<K, V> immutableMapEntry3 = createEntryArray2[smear2];
            ImmutableMapEntry<K, V> immutableMapEntry4 = immutableMapEntry3;
            while (immutableMapEntry4 != null) {
                checkNoConflict(!value.equals(immutableMapEntry4.getValue()), "value", entry, immutableMapEntry4);
                immutableMapEntry4 = immutableMapEntry4.getNextInValueBucket();
                i2 = i2;
            }
            int i4 = i2;
            ImmutableMapEntry<K, V> terminalEntry = (immutableMapEntry == null && immutableMapEntry3 == null) ? new ImmutableMapEntry.TerminalEntry<>(key, value) : new NonTerminalBiMapEntry<>(key, value, immutableMapEntry, immutableMapEntry3);
            createEntryArray[smear] = terminalEntry;
            createEntryArray2[smear2] = terminalEntry;
            createEntryArray3[i] = terminalEntry;
            i2 = i4 + (hashCode2 ^ hashCode3);
            i++;
            regularImmutableBiMap = this;
            entryArr2 = entryArr;
            length = i3;
        }
        regularImmutableBiMap.keyTable = createEntryArray;
        regularImmutableBiMap.valueTable = createEntryArray2;
        regularImmutableBiMap.entries = createEntryArray3;
        regularImmutableBiMap.hashCode = i2;
    }

    private static final class NonTerminalBiMapEntry<K, V> extends ImmutableMapEntry<K, V> {
        @Nullable
        private final ImmutableMapEntry<K, V> nextInKeyBucket;
        @Nullable
        private final ImmutableMapEntry<K, V> nextInValueBucket;

        NonTerminalBiMapEntry(K k, V v, @Nullable ImmutableMapEntry<K, V> immutableMapEntry, @Nullable ImmutableMapEntry<K, V> immutableMapEntry2) {
            super(k, v);
            this.nextInKeyBucket = immutableMapEntry;
            this.nextInValueBucket = immutableMapEntry2;
        }

        NonTerminalBiMapEntry(ImmutableMapEntry<K, V> immutableMapEntry, @Nullable ImmutableMapEntry<K, V> immutableMapEntry2, @Nullable ImmutableMapEntry<K, V> immutableMapEntry3) {
            super(immutableMapEntry);
            this.nextInKeyBucket = immutableMapEntry2;
            this.nextInValueBucket = immutableMapEntry3;
        }

        /* access modifiers changed from: package-private */
        @Nullable
        public ImmutableMapEntry<K, V> getNextInKeyBucket() {
            return this.nextInKeyBucket;
        }

        /* access modifiers changed from: package-private */
        @Nullable
        public ImmutableMapEntry<K, V> getNextInValueBucket() {
            return this.nextInValueBucket;
        }
    }

    private static <K, V> ImmutableMapEntry<K, V>[] createEntryArray(int i) {
        return new ImmutableMapEntry[i];
    }

    @Nullable
    public V get(@Nullable Object obj) {
        if (obj == null) {
            return null;
        }
        for (ImmutableMapEntry<K, V> immutableMapEntry = this.keyTable[Hashing.smear(obj.hashCode()) & this.mask]; immutableMapEntry != null; immutableMapEntry = immutableMapEntry.getNextInKeyBucket()) {
            if (obj.equals(immutableMapEntry.getKey())) {
                return immutableMapEntry.getValue();
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public ImmutableSet<Map.Entry<K, V>> createEntrySet() {
        return new ImmutableMapEntrySet<K, V>() {
            /* access modifiers changed from: package-private */
            public boolean isHashCodeFast() {
                return true;
            }

            /* access modifiers changed from: package-private */
            public ImmutableMap<K, V> map() {
                return RegularImmutableBiMap.this;
            }

            public UnmodifiableIterator<Map.Entry<K, V>> iterator() {
                return asList().iterator();
            }

            /* access modifiers changed from: package-private */
            public ImmutableList<Map.Entry<K, V>> createAsList() {
                return new RegularImmutableAsList(this, (Object[]) RegularImmutableBiMap.this.entries);
            }

            public int hashCode() {
                return RegularImmutableBiMap.this.hashCode;
            }
        };
    }

    public int size() {
        return this.entries.length;
    }

    public ImmutableBiMap<V, K> inverse() {
        ImmutableBiMap<V, K> immutableBiMap = this.inverse;
        if (immutableBiMap != null) {
            return immutableBiMap;
        }
        Inverse inverse2 = new Inverse();
        this.inverse = inverse2;
        return inverse2;
    }

    private final class Inverse extends ImmutableBiMap<V, K> {
        /* access modifiers changed from: package-private */
        public boolean isPartialView() {
            return false;
        }

        private Inverse() {
        }

        public int size() {
            return inverse().size();
        }

        public ImmutableBiMap<K, V> inverse() {
            return RegularImmutableBiMap.this;
        }

        public K get(@Nullable Object obj) {
            if (obj == null) {
                return null;
            }
            for (ImmutableMapEntry immutableMapEntry = RegularImmutableBiMap.this.valueTable[Hashing.smear(obj.hashCode()) & RegularImmutableBiMap.this.mask]; immutableMapEntry != null; immutableMapEntry = immutableMapEntry.getNextInValueBucket()) {
                if (obj.equals(immutableMapEntry.getValue())) {
                    return immutableMapEntry.getKey();
                }
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        public ImmutableSet<Map.Entry<V, K>> createEntrySet() {
            return new InverseEntrySet();
        }

        final class InverseEntrySet extends ImmutableMapEntrySet<V, K> {
            /* access modifiers changed from: package-private */
            public boolean isHashCodeFast() {
                return true;
            }

            InverseEntrySet() {
            }

            /* access modifiers changed from: package-private */
            public ImmutableMap<V, K> map() {
                return Inverse.this;
            }

            public int hashCode() {
                return RegularImmutableBiMap.this.hashCode;
            }

            public UnmodifiableIterator<Map.Entry<V, K>> iterator() {
                return asList().iterator();
            }

            /* access modifiers changed from: package-private */
            public ImmutableList<Map.Entry<V, K>> createAsList() {
                return new ImmutableAsList<Map.Entry<V, K>>() {
                    public Map.Entry<V, K> get(int i) {
                        ImmutableMapEntry immutableMapEntry = RegularImmutableBiMap.this.entries[i];
                        return Maps.immutableEntry(immutableMapEntry.getValue(), immutableMapEntry.getKey());
                    }

                    /* access modifiers changed from: package-private */
                    public ImmutableCollection<Map.Entry<V, K>> delegateCollection() {
                        return InverseEntrySet.this;
                    }
                };
            }
        }

        /* access modifiers changed from: package-private */
        public Object writeReplace() {
            return new InverseSerializedForm(RegularImmutableBiMap.this);
        }
    }

    private static class InverseSerializedForm<K, V> implements Serializable {
        private static final long serialVersionUID = 1;
        private final ImmutableBiMap<K, V> forward;

        InverseSerializedForm(ImmutableBiMap<K, V> immutableBiMap) {
            this.forward = immutableBiMap;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return this.forward.inverse();
        }
    }
}
