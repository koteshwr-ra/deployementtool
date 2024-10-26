package com.tencent.smtt.utils;

import android.text.TextUtils;
import java.lang.reflect.Method;

public class PropertyUtils {
    private static Class a;
    private static Method b;

    static {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            a = cls;
            b = cls.getDeclaredMethod("get", new Class[]{String.class, String.class});
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static String a(String str, String str2) {
        Method method;
        Class cls = a;
        if (cls == null || (method = b) == null) {
            return str2;
        }
        try {
            return (String) method.invoke(cls, new Object[]{str, str2});
        } catch (Throwable th) {
            th.printStackTrace();
            return str2;
        }
    }

    public static String getQuickly(String str, String str2) {
        return TextUtils.isEmpty(str) ? str2 : a(str, str2);
    }
}
