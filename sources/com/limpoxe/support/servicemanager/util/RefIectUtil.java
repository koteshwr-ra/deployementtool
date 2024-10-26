package com.limpoxe.support.servicemanager.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RefIectUtil {
    public static Object invokeMethod(Object obj, Class cls, String str, Class[] clsArr, Object[] objArr) {
        try {
            Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
            if (!declaredMethod.isAccessible()) {
                declaredMethod.setAccessible(true);
            }
            return declaredMethod.invoke(obj, objArr);
        } catch (SecurityException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            return null;
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
            return null;
        } catch (NoSuchMethodException e4) {
            e4.printStackTrace();
            return null;
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
            return null;
        }
    }

    public static Object getFieldObject(Object obj, Class cls, String str) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            if (!declaredField.isAccessible()) {
                declaredField.setAccessible(true);
            }
            return declaredField.get(obj);
        } catch (SecurityException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchFieldException e2) {
            e2.printStackTrace();
            return null;
        } catch (IllegalArgumentException e3) {
            e3.printStackTrace();
            return null;
        } catch (IllegalAccessException e4) {
            e4.printStackTrace();
            return null;
        }
    }
}
