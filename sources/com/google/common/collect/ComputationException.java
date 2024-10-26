package com.google.common.collect;

import javax.annotation.Nullable;

public class ComputationException extends RuntimeException {
    private static final long serialVersionUID = 0;

    public ComputationException(@Nullable Throwable th) {
        super(th);
    }
}
