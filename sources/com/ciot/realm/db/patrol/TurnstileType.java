package com.ciot.realm.db.patrol;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface TurnstileType {
    public static final int PENDULUM_GATE = 1;
    public static final int SPEED_GATE = 2;
}
