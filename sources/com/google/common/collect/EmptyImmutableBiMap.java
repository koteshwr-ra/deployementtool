package com.google.common.collect;

import java.util.Map;
import javax.annotation.Nullable;

final class EmptyImmutableBiMap extends ImmutableBiMap<Object, Object> {
    static final EmptyImmutableBiMap INSTANCE = new EmptyImmutableBiMap();

    public Object get(@Nullable Object obj) {
        return null;
    }

    public ImmutableBiMap<Object, Object> inverse() {
        return this;
    }

    public boolean isEmpty() {
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean isPartialView() {
        return false;
    }

    public int size() {
        return 0;
    }

    private EmptyImmutableBiMap() {
    }

    public ImmutableSet<Map.Entry<Object, Object>> entrySet() {
        return ImmutableSet.of();
    }

    /* access modifiers changed from: package-private */
    public ImmutableSet<Map.Entry<Object, Object>> createEntrySet() {
        throw new AssertionError("should never be called");
    }

    public ImmutableSetMultimap<Object, Object> asMultimap() {
        return ImmutableSetMultimap.of();
    }

    public ImmutableSet<Object> keySet() {
        return ImmutableSet.of();
    }

    /* access modifiers changed from: package-private */
    public Object readResolve() {
        return INSTANCE;
    }
}
