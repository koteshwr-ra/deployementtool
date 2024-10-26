package com.ciot;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class SocketConstant {
    public static final int CHASSIS_CLIENT = 0;
    public static final int SROS_CLIENT = 1;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ClientType {
    }
}
