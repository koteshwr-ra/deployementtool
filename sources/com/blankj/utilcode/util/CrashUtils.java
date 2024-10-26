package com.blankj.utilcode.util;

import android.os.Build;
import java.io.File;
import java.lang.Thread;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class CrashUtils {
    /* access modifiers changed from: private */
    public static final Thread.UncaughtExceptionHandler DEFAULT_UNCAUGHT_EXCEPTION_HANDLER = Thread.getDefaultUncaughtExceptionHandler();
    private static final String FILE_SEP = System.getProperty("file.separator");

    public interface OnCrashListener {
        void onCrash(String str, Throwable th);
    }

    private CrashUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void init() {
        init("");
    }

    public static void init(File file) {
        if (file != null) {
            init(file.getAbsolutePath(), (OnCrashListener) null);
            return;
        }
        throw new NullPointerException("Argument 'crashDir' of type File (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void init(String str) {
        init(str, (OnCrashListener) null);
    }

    public static void init(OnCrashListener onCrashListener) {
        init("", onCrashListener);
    }

    public static void init(File file, OnCrashListener onCrashListener) {
        if (file != null) {
            init(file.getAbsolutePath(), onCrashListener);
            return;
        }
        throw new NullPointerException("Argument 'crashDir' of type File (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void init(String str, OnCrashListener onCrashListener) {
        if (UtilsBridge.isSpace(str)) {
            if (!UtilsBridge.isSDCardEnableByEnvironment() || Utils.getApp().getExternalFilesDir((String) null) == null) {
                str = Utils.getApp().getFilesDir() + FILE_SEP + "crash" + FILE_SEP;
            } else {
                str = Utils.getApp().getExternalFilesDir((String) null) + FILE_SEP + "crash" + FILE_SEP;
            }
        } else if (!str.endsWith(FILE_SEP)) {
            str = str + FILE_SEP;
        }
        Thread.setDefaultUncaughtExceptionHandler(getUncaughtExceptionHandler(str, onCrashListener));
    }

    private static Thread.UncaughtExceptionHandler getUncaughtExceptionHandler(final String str, final OnCrashListener onCrashListener) {
        return new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread thread, Throwable th) {
                if (thread == null) {
                    throw new NullPointerException("Argument 't' of type Thread (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
                } else if (th != null) {
                    String format = new SimpleDateFormat("yyyy_MM_dd-HH_mm_ss").format(new Date());
                    StringBuilder sb = new StringBuilder();
                    sb.append("************* Log Head ****************\nTime Of Crash      : " + format + "\nDevice Manufacturer: " + Build.MANUFACTURER + "\nDevice Model       : " + Build.MODEL + "\nAndroid Version    : " + Build.VERSION.RELEASE + "\nAndroid SDK        : " + Build.VERSION.SDK_INT + "\nApp VersionName    : " + UtilsBridge.getAppVersionName() + "\nApp VersionCode    : " + UtilsBridge.getAppVersionCode() + "\n************* Log Head ****************\n\n");
                    sb.append(UtilsBridge.getFullStackTrace(th));
                    String sb2 = sb.toString();
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(str);
                    sb3.append(format);
                    sb3.append(".txt");
                    UtilsBridge.writeFileFromString(sb3.toString(), sb2, true);
                    OnCrashListener onCrashListener = onCrashListener;
                    if (onCrashListener != null) {
                        onCrashListener.onCrash(sb2, th);
                    }
                    if (CrashUtils.DEFAULT_UNCAUGHT_EXCEPTION_HANDLER != null) {
                        CrashUtils.DEFAULT_UNCAUGHT_EXCEPTION_HANDLER.uncaughtException(thread, th);
                    }
                } else {
                    throw new NullPointerException("Argument 'e' of type Throwable (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
                }
            }
        };
    }
}
