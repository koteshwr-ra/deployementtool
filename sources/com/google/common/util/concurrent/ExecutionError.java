package com.google.common.util.concurrent;

import javax.annotation.Nullable;

public class ExecutionError extends Error {
    private static final long serialVersionUID = 0;

    protected ExecutionError() {
    }

    protected ExecutionError(@Nullable String str) {
        super(str);
    }

    public ExecutionError(@Nullable String str, @Nullable Error error) {
        super(str, error);
    }

    public ExecutionError(@Nullable Error error) {
        super(error);
    }
}
