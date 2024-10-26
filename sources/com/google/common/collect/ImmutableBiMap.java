package com.google.common.collect;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMapEntry;
import java.util.Map;

public abstract class ImmutableBiMap<K, V> extends ImmutableMap<K, V> implements BiMap<K, V> {
    private static final Map.Entry<?, ?>[] EMPTY_ENTRY_ARRAY = new Map.Entry[0];

    public abstract ImmutableBiMap<V, K> inverse();

    public static <K, V> ImmutableBiMap<K, V> of() {
        return EmptyImmutableBiMap.INSTANCE;
    }

    public static <K, V> ImmutableBiMap<K, V> of(K k, V v) {
        return new SingletonImmutableBiMap(k, v);
    }

    public static <K, V> ImmutableBiMap<K, V> of(K k, V v, K k2, V v2) {
        return new RegularImmutableBiMap((ImmutableMapEntry.TerminalEntry<?, ?>[]) new ImmutableMapEntry.TerminalEntry[]{entryOf(k, v), entryOf(k2, v2)});
    }

    public static <K, V> ImmutableBiMap<K, V> of(K k, V v, K k2, V v2, K k3, V v3) {
        return new RegularImmutableBiMap((ImmutableMapEntry.TerminalEntry<?, ?>[]) new ImmutableMapEntry.TerminalEntry[]{entryOf(k, v), entryOf(k2, v2), entryOf(k3, v3)});
    }

    public static <K, V> ImmutableBiMap<K, V> of(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4) {
        return new RegularImmutableBiMap((ImmutableMapEntry.TerminalEntry<?, ?>[]) new ImmutableMapEntry.TerminalEntry[]{entryOf(k, v), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4)});
    }

    public static <K, V> ImmutableBiMap<K, V> of(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        return new RegularImmutableBiMap((ImmutableMapEntry.TerminalEntry<?, ?>[]) new ImmutableMapEntry.TerminalEntry[]{entryOf(k, v), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4), entryOf(k5, v5)});
    }

    public static <K, V> Builder<K, V> builder() {
        return new Builder<>();
    }

    public static final class Builder<K, V> extends ImmutableMap.Builder<K, V> {
        public Builder<K, V> put(K k, V v) {
            super.put(k, v);
            return this;
        }

        public Builder<K, V> putAll(Map<? extends K, ? extends V> map) {
            super.putAll(map);
            return this;
        }

        public ImmutableBiMap<K, V> build() {
            int i = this.size;
            if (i == 0) {
                return ImmutableBiMap.of();
            }
            if (i != 1) {
                return new RegularImmutableBiMap(this.size, this.entries);
            }
            return ImmutableBiMap.of(this.entries[0].getKey(), this.entries[0].getValue());
        }
    }

    public static <K, V> ImmutableBiMap<K, V> copyOf(Map<? extends K, ? extends V> map) {
        if (map instanceof ImmutableBiMap) {
            ImmutableBiMap<K, V> immutableBiMap = (ImmutableBiMap) map;
            if (!immutableBiMap.isPartialView()) {
                return immutableBiMap;
            }
        }
        Map.Entry[] entryArr = (Map.Entry[]) map.entrySet().toArray(EMPTY_ENTRY_ARRAY);
        int length = entryArr.length;
        if (length == 0) {
            return of();
        }
        if (length != 1) {
            return new RegularImmutableBiMap((Map.Entry<?, ?>[]) entryArr);
        }
        Map.Entry entry = entryArr[0];
        return of(entry.getKey(), entry.getValue());
    }

    ImmutableBiMap() {
    }

    public ImmutableSet<V> values() {
        return inverse().keySet();
    }

    @Deprecated
    public V forcePut(K k, V v) {
        throw new UnsupportedOperationException();
    }

    private static class SerializedForm extends ImmutableMap.SerializedForm {
        private static final long serialVersionUID = 0;

        SerializedForm(ImmutableBiMap<?, ?> immutableBiMap) {
            super(immutableBiMap);
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return createMap(new Builder());
        }
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new SerializedForm(this);
    }
}
