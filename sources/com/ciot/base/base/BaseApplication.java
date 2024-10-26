package com.ciot.base.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Process;
import com.blankj.utilcode.util.Utils;
import java.util.List;

public class BaseApplication extends Application {
    private static boolean sDebug;
    private static BaseApplication sInstance;

    public void onCreate() {
        super.onCreate();
        setApplication(this);
        Utils.init(this);
    }

    private void setApplication(BaseApplication baseApplication) {
        sInstance = baseApplication;
        baseApplication.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            public void onActivityPaused(Activity activity) {
            }

            public void onActivityResumed(Activity activity) {
            }

            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            public void onActivityStarted(Activity activity) {
            }

            public void onActivityStopped(Activity activity) {
            }

            public void onActivityCreated(Activity activity, Bundle bundle) {
                AppManager.getInstance().addActivity(activity);
            }

            public void onActivityDestroyed(Activity activity) {
                AppManager.getInstance().removeActivity(activity);
            }
        });
    }

    public static BaseApplication getInstance() {
        BaseApplication baseApplication = sInstance;
        if (baseApplication != null) {
            return baseApplication;
        }
        throw new NullPointerException("please inherit BaseApplication or call setApplication.");
    }

    public void setsDebug(boolean z) {
        sDebug = z;
    }

    public boolean issDebug() {
        return sDebug;
    }

    public static String getCurrentProcessName(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
            if (next.pid == Process.myPid() && next.processName != null) {
                return next.processName;
            }
        }
        return null;
    }
}
