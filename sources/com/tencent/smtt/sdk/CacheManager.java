package com.tencent.smtt.sdk;

import com.tencent.smtt.utils.i;
import java.io.File;
import java.io.InputStream;
import java.util.Map;

@Deprecated
public final class CacheManager {
    @Deprecated
    public static boolean cacheDisabled() {
        w a = w.a();
        if (a != null && a.b()) {
            return ((Boolean) a.c().c()).booleanValue();
        }
        Object a2 = i.a("android.webkit.CacheManager", "cacheDisabled");
        if (a2 == null) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    public static InputStream getCacheFile(String str, boolean z) {
        w a = w.a();
        if (a == null || !a.b()) {
            return null;
        }
        return a.c().a(str, z);
    }

    public static Object getCacheFile(String str, Map<String, String> map) {
        w a = w.a();
        if (a != null && a.b()) {
            return a.c().g();
        }
        try {
            return i.a(Class.forName("android.webkit.CacheManager"), "getCacheFile", (Class<?>[]) new Class[]{String.class, Map.class}, str, map);
        } catch (Exception unused) {
            return null;
        }
    }

    @Deprecated
    public static File getCacheFileBaseDir() {
        w a = w.a();
        return (File) ((a == null || !a.b()) ? i.a("android.webkit.CacheManager", "getCacheFileBaseDir") : a.c().g());
    }
}
