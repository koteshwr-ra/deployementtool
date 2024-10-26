package com.ciot.navigation.navigation.task;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface TASK_PRIORITY {
    public static final int ALARM_LEVEL = 4;
    public static final int CHARGE_LEVEL = 5;
    public static final int GUIDE_TASK_LEVEL = 3;
    public static final int OUT_ALARM_TASK_LEVEL = 1;
    public static final int TASK_LEVEL = 0;
    public static final int TRANSPORT_TASK_LEVEL = 2;
}
