package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import javax.annotation.Nullable;

final class AsyncSettableFuture<V> extends ForwardingListenableFuture<V> {
    private final ListenableFuture<V> dereferenced;
    private final NestedFuture<V> nested;

    public static <V> AsyncSettableFuture<V> create() {
        return new AsyncSettableFuture<>();
    }

    private AsyncSettableFuture() {
        NestedFuture<V> nestedFuture = new NestedFuture<>();
        this.nested = nestedFuture;
        this.dereferenced = Futures.dereference(nestedFuture);
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<V> delegate() {
        return this.dereferenced;
    }

    public boolean setFuture(ListenableFuture<? extends V> listenableFuture) {
        return this.nested.setFuture((ListenableFuture) Preconditions.checkNotNull(listenableFuture));
    }

    public boolean setValue(@Nullable V v) {
        return setFuture(Futures.immediateFuture(v));
    }

    public boolean setException(Throwable th) {
        return setFuture(Futures.immediateFailedFuture(th));
    }

    public boolean isSet() {
        return this.nested.isDone();
    }

    private static final class NestedFuture<V> extends AbstractFuture<ListenableFuture<? extends V>> {
        private NestedFuture() {
        }

        /* access modifiers changed from: package-private */
        public boolean setFuture(ListenableFuture<? extends V> listenableFuture) {
            boolean z = set(listenableFuture);
            if (isCancelled()) {
                listenableFuture.cancel(wasInterrupted());
            }
            return z;
        }
    }
}
