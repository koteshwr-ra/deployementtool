package com.google.common.collect;

import java.util.Collection;
import java.util.Set;
import javax.annotation.Nullable;
import okhttp3.HttpUrl;

final class EmptyImmutableSet extends ImmutableSet<Object> {
    static final EmptyImmutableSet INSTANCE = new EmptyImmutableSet();
    private static final long serialVersionUID = 0;

    public boolean contains(@Nullable Object obj) {
        return false;
    }

    /* access modifiers changed from: package-private */
    public int copyIntoArray(Object[] objArr, int i) {
        return i;
    }

    public final int hashCode() {
        return 0;
    }

    public boolean isEmpty() {
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean isHashCodeFast() {
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
        return HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
    }

    private EmptyImmutableSet() {
    }

    public boolean containsAll(Collection<?> collection) {
        return collection.isEmpty();
    }

    public UnmodifiableIterator<Object> iterator() {
        return Iterators.emptyIterator();
    }

    public ImmutableList<Object> asList() {
        return ImmutableList.of();
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Set) {
            return ((Set) obj).isEmpty();
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public Object readResolve() {
        return INSTANCE;
    }
}
