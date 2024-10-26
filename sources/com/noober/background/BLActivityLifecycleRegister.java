package com.noober.background;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

public class BLActivityLifecycleRegister implements Application.ActivityLifecycleCallbacks {
    public void onActivityDestroyed(Activity activity) {
    }

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
        BackgroundLibrary.inject(activity);
    }
}
