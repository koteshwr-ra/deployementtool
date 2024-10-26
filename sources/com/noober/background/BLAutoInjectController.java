package com.noober.background;

public class BLAutoInjectController {
    private static boolean enableAutoInject = true;

    public static boolean isEnableAutoInject() {
        return enableAutoInject;
    }

    public static void setEnableAutoInject(boolean z) {
        enableAutoInject = z;
    }
}
