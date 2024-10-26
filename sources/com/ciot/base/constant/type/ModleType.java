package com.ciot.base.constant.type;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface ModleType {
    public static final int DIY = 3;
    public static final int HEALTH = 4;
    public static final int RECORD = 1;
    public static final int VERIFY = 2;
}
