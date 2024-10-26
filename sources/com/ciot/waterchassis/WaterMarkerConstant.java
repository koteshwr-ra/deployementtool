package com.ciot.waterchassis;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class WaterMarkerConstant {
    public static final int CALL_LIFT = 3;
    public static final int CHARGING_PILE = 11;
    public static final int NORMAL = 0;
    public static final int TAKE_LIFT = 4;

    @Retention(RetentionPolicy.SOURCE)
    public @interface MarkerType {
    }
}
