package com.ciot.base.util;

import android.content.Context;

public class ContextUtil {
    private static Context sContext;

    public static Context getContext() {
        return sContext;
    }

    public static void setContext(Context context) {
        sContext = context.getApplicationContext();
    }
}
