package com.ciot.diagnosis.util;

import android.content.Context;

public class MyContextUtil {
    private static volatile MyContextUtil instance;
    private Context context;

    public static MyContextUtil getInstance() {
        if (instance == null) {
            instance = new MyContextUtil();
        }
        return instance;
    }

    private MyContextUtil() {
    }

    public Context getContext() {
        return this.context;
    }

    public void setContext(Context context2) {
        this.context = context2;
    }
}
