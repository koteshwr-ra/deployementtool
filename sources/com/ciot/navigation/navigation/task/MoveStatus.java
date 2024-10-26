package com.ciot.navigation.navigation.task;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface MoveStatus {
    public static final int CANCELED = 4;
    public static final int FAILED = 3;
    public static final int OTHER = 0;
    public static final int RUNNING = 2;
    public static final int SUCCEEDED = 1;
}
