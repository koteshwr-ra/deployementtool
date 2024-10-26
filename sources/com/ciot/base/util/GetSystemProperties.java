package com.ciot.base.util;

import java.lang.reflect.Method;

public class GetSystemProperties {
    private static Class<?> mClassType;
    private static Method mGetMethod;
    private static Method mSetMethod;

    public static String get(String str) {
        init();
        try {
            return (String) mGetMethod.invoke(mClassType, new Object[]{str});
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String set(String str, String str2) {
        init();
        try {
            mSetMethod.invoke(mClassType, new Object[]{str, str2});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str2;
    }

    private static void init() {
        try {
            if (mClassType == null) {
                Class<?> cls = Class.forName("android.os.SystemProperties");
                mClassType = cls;
                mGetMethod = cls.getMethod("get", new Class[]{String.class});
                mSetMethod = mClassType.getMethod("set", new Class[]{String.class, String.class});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
