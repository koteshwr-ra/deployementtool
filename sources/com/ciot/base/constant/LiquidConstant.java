package com.ciot.base.constant;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class LiquidConstant {
    public static final int LIQUID_LEVEL_HIGH = 3;
    public static final int LIQUID_LEVEL_LOW = 2;
    public static final int LIQUID_LEVEL_NORMAL = 1;
    public static final int LIQUID_LEVEL_OVERFLOW = 4;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Level {
    }
}
