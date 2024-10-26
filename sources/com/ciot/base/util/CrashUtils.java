package com.ciot.base.util;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import com.ciot.base.constant.FileConstant;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class CrashUtils implements Thread.UncaughtExceptionHandler {
    private static volatile CrashUtils mInstance;
    private String crashDir;
    private Thread.UncaughtExceptionHandler mHandler;
    private boolean mInitialized;
    private int versionCode;
    private String versionName;

    private CrashUtils() {
    }

    public static CrashUtils getInstance() {
        if (mInstance == null) {
            synchronized (CrashUtils.class) {
                if (mInstance == null) {
                    mInstance = new CrashUtils();
                }
            }
        }
        return mInstance;
    }

    public boolean init() {
        if (this.mInitialized) {
            return true;
        }
        if ("mounted".equals(Environment.getExternalStorageState())) {
            this.crashDir = FileConstant.APP_PATH + File.separator + new SimpleDateFormat("MM-dd", Locale.getDefault()).format(new Date()) + File.separator;
        } else {
            this.crashDir = ContextUtil.getContext().getCacheDir() + File.separator + "log" + File.separator;
        }
        try {
            PackageInfo packageInfo = ContextUtil.getContext().getPackageManager().getPackageInfo(ContextUtil.getContext().getPackageName(), 0);
            this.versionName = packageInfo.versionName;
            this.versionCode = packageInfo.versionCode;
            this.mHandler = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(this);
            this.mInitialized = true;
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void uncaughtException(Thread thread, final Throwable th) {
        final String str = this.crashDir + new SimpleDateFormat("yyMMdd HH-mm-ss", Locale.getDefault()).format(new Date()) + "crash.txt";
        if (createOrExistsFile(str)) {
            new Thread(new Runnable() {
                public void run() {
                    PrintWriter printWriter;
                    IOException e;
                    try {
                        printWriter = new PrintWriter(new FileWriter(str, false));
                        try {
                            printWriter.write(CrashUtils.this.getCrashHead());
                            th.printStackTrace(printWriter);
                            for (Throwable cause = th.getCause(); cause != null; cause = cause.getCause()) {
                                cause.printStackTrace(printWriter);
                            }
                            CloseUtils.closeIO(printWriter);
                        } catch (IOException e2) {
                            e = e2;
                            try {
                                e.printStackTrace();
                                CloseUtils.closeIO(printWriter);
                            } catch (Throwable th) {
                                th = th;
                                CloseUtils.closeIO(printWriter);
                                throw th;
                            }
                        }
                    } catch (IOException e3) {
                        IOException iOException = e3;
                        printWriter = null;
                        e = iOException;
                        e.printStackTrace();
                        CloseUtils.closeIO(printWriter);
                    } catch (Throwable th2) {
                        Throwable th3 = th2;
                        printWriter = null;
                        th = th3;
                        CloseUtils.closeIO(printWriter);
                        throw th;
                    }
                }
            }).start();
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.mHandler;
            if (uncaughtExceptionHandler != null) {
                uncaughtExceptionHandler.uncaughtException(thread, th);
            }
        }
    }

    /* access modifiers changed from: private */
    public String getCrashHead() {
        return "\n************* Crash Log Head ****************\nDevice Manufacturer: " + Build.MANUFACTURER + "\nDevice Model       : " + Build.MODEL + "\nAndroid Version    : " + Build.VERSION.RELEASE + "\nAndroid SDK        : " + Build.VERSION.SDK_INT + "\nApp VersionName    : " + this.versionName + "\nApp VersionCode    : " + this.versionCode + "\n************* Crash Log Head ****************\n\n";
    }

    private static boolean createOrExistsFile(String str) {
        File file = new File(str);
        if (file.exists()) {
            return file.isFile();
        }
        if (!createOrExistsDir(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean createOrExistsDir(File file) {
        return file != null && (!file.exists() ? file.mkdirs() : file.isDirectory());
    }
}
