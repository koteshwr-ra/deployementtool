package com.google.common.base;

import javax.annotation.Nullable;

public class VerifyException extends RuntimeException {
    public VerifyException() {
    }

    public VerifyException(@Nullable String str) {
        super(str);
    }
}
