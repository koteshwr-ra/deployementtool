package xcrash;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import java.util.Iterator;
import java.util.LinkedList;

class ActivityMonitor {
    private static final int MAX_ACTIVITY_NUM = 100;
    private static final ActivityMonitor instance = new ActivityMonitor();
    /* access modifiers changed from: private */
    public LinkedList<Activity> activities = null;
    /* access modifiers changed from: private */
    public boolean isAppForeground = false;

    private ActivityMonitor() {
    }

    static ActivityMonitor getInstance() {
        return instance;
    }

    /* access modifiers changed from: package-private */
    public void initialize(Application application) {
        this.activities = new LinkedList<>();
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            private int activityReferences = 0;
            private boolean isActivityChangingConfigurations = false;

            public void onActivityPaused(Activity activity) {
            }

            public void onActivityResumed(Activity activity) {
            }

            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            public void onActivityCreated(Activity activity, Bundle bundle) {
                ActivityMonitor.this.activities.addFirst(activity);
                if (ActivityMonitor.this.activities.size() > 100) {
                    ActivityMonitor.this.activities.removeLast();
                }
            }

            public void onActivityStarted(Activity activity) {
                int i = this.activityReferences + 1;
                this.activityReferences = i;
                if (i == 1 && !this.isActivityChangingConfigurations) {
                    boolean unused = ActivityMonitor.this.isAppForeground = true;
                }
            }

            public void onActivityStopped(Activity activity) {
                boolean isChangingConfigurations = activity.isChangingConfigurations();
                this.isActivityChangingConfigurations = isChangingConfigurations;
                int i = this.activityReferences - 1;
                this.activityReferences = i;
                if (i == 0 && !isChangingConfigurations) {
                    boolean unused = ActivityMonitor.this.isAppForeground = false;
                }
            }

            public void onActivityDestroyed(Activity activity) {
                ActivityMonitor.this.activities.remove(activity);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void finishAllActivities() {
        LinkedList<Activity> linkedList = this.activities;
        if (linkedList != null) {
            Iterator it = linkedList.iterator();
            while (it.hasNext()) {
                ((Activity) it.next()).finish();
            }
            this.activities.clear();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isApplicationForeground() {
        return this.isAppForeground;
    }
}
