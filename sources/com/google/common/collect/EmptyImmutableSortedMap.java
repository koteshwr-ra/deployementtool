package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.Comparator;
import java.util.Map;
import javax.annotation.Nullable;

final class EmptyImmutableSortedMap<K, V> extends ImmutableSortedMap<K, V> {
    private final transient ImmutableSortedSet<K> keySet;

    public V get(@Nullable Object obj) {
        return null;
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

    public String toString() {
        return "{}";
    }

    EmptyImmutableSortedMap(Comparator<? super K> comparator) {
        this.keySet = ImmutableSortedSet.emptySet(comparator);
    }

    EmptyImmutableSortedMap(Comparator<? super K> comparator, ImmutableSortedMap<K, V> immutableSortedMap) {
        super(immutableSortedMap);
        this.keySet = ImmutableSortedSet.emptySet(comparator);
    }

    public ImmutableSortedSet<K> keySet() {
        return this.keySet;
    }

    public ImmutableCollection<V> values() {
        return ImmutableList.of();
    }

    public ImmutableSet<Map.Entry<K, V>> entrySet() {
        return ImmutableSet.of();
    }

    /* access modifiers changed from: package-private */
    public ImmutableSet<Map.Entry<K, V>> createEntrySet() {
        throw new AssertionError("should never be called");
    }

    public ImmutableSetMultimap<K, V> asMultimap() {
        return ImmutableSetMultimap.of();
    }

    public ImmutableSortedMap<K, V> headMap(K k, boolean z) {
        Preconditions.checkNotNull(k);
        return this;
    }

    public ImmutableSortedMap<K, V> tailMap(K k, boolean z) {
        Preconditions.checkNotNull(k);
        return this;
    }

    /* access modifiers changed from: package-private */
    public ImmutableSortedMap<K, V> createDescendingMap() {
        return new EmptyImmutableSortedMap(Ordering.from(comparator()).reverse(), this);
    }
}
