package com.google.common.collect;

public enum BoundType {
    OPEN {
        /* access modifiers changed from: package-private */
        public BoundType flip() {
            return CLOSED;
        }
    },
    CLOSED {
        /* access modifiers changed from: package-private */
        public BoundType flip() {
            return OPEN;
        }
    };

    /* access modifiers changed from: package-private */
    public abstract BoundType flip();

    static BoundType forBoolean(boolean z) {
        return z ? CLOSED : OPEN;
    }
}
