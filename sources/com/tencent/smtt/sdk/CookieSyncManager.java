package com.tencent.smtt.sdk;

import android.content.Context;
import java.lang.reflect.Field;

@Deprecated
public class CookieSyncManager {
    private static android.webkit.CookieSyncManager a = null;
    private static CookieSyncManager b = null;
    private static boolean c = false;

    private CookieSyncManager(Context context) {
        w a2 = w.a();
        if (a2 != null && a2.b()) {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieSyncManager_createInstance", new Class[]{Context.class}, context);
            c = true;
        }
    }

    public static synchronized CookieSyncManager createInstance(Context context) {
        CookieSyncManager cookieSyncManager;
        synchronized (CookieSyncManager.class) {
            a = android.webkit.CookieSyncManager.createInstance(context);
            if (b == null || !c) {
                b = new CookieSyncManager(context.getApplicationContext());
            }
            cookieSyncManager = b;
        }
        return cookieSyncManager;
    }

    public static synchronized CookieSyncManager getInstance() {
        CookieSyncManager cookieSyncManager;
        synchronized (CookieSyncManager.class) {
            if (b != null) {
                cookieSyncManager = b;
            } else {
                throw new IllegalStateException("CookieSyncManager::createInstance() needs to be called before CookieSyncManager::getInstance()");
            }
        }
        return cookieSyncManager;
    }

    public void startSync() {
        w a2 = w.a();
        if (a2 == null || !a2.b()) {
            a.startSync();
            try {
                Field declaredField = Class.forName("android.webkit.WebSyncManager").getDeclaredField("mSyncThread");
                declaredField.setAccessible(true);
                ((Thread) declaredField.get(a)).setUncaughtExceptionHandler(new g());
            } catch (Exception unused) {
            }
        } else {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieSyncManager_startSync", new Class[0], new Object[0]);
        }
    }

    public void stopSync() {
        w a2 = w.a();
        if (a2 == null || !a2.b()) {
            a.stopSync();
        } else {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieSyncManager_stopSync", new Class[0], new Object[0]);
        }
    }

    public void sync() {
        w a2 = w.a();
        if (a2 == null || !a2.b()) {
            a.sync();
        } else {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieSyncManager_Sync", new Class[0], new Object[0]);
        }
    }
}
