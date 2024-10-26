package com.ciot.base.base;

import android.app.Activity;
import java.util.Iterator;
import java.util.Stack;

public class AppManager {
    private static Stack<Activity> activityStack;

    private AppManager() {
    }

    private static class SingleHolder {
        /* access modifiers changed from: private */
        public static AppManager instance = new AppManager();

        private SingleHolder() {
        }
    }

    public static AppManager getInstance() {
        return SingleHolder.instance;
    }

    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    public void removeActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
        }
    }

    public boolean isActivity() {
        Stack<Activity> stack = activityStack;
        if (stack != null) {
            return !stack.isEmpty();
        }
        return false;
    }

    public Activity currentActivity() {
        return (Activity) activityStack.lastElement();
    }

    public void finishActivity() {
        finishActivity((Activity) activityStack.lastElement());
    }

    public void finishActivity(Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            activity.finish();
        }
    }

    public void finishActivity(Class<?> cls) {
        Iterator it = activityStack.iterator();
        while (it.hasNext()) {
            Activity activity = (Activity) it.next();
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                return;
            }
        }
    }

    public void finishAllActivity() {
        int size = activityStack.size();
        for (int i = 0; i < size; i++) {
            if (activityStack.get(i) != null) {
                finishActivity((Activity) activityStack.get(i));
            }
        }
        activityStack.clear();
    }

    public Activity getActivity(Class<?> cls) {
        Stack<Activity> stack = activityStack;
        if (stack == null) {
            return null;
        }
        Iterator it = stack.iterator();
        while (it.hasNext()) {
            Activity activity = (Activity) it.next();
            if (activity.getClass().equals(cls)) {
                return activity;
            }
        }
        return null;
    }

    public void AppExit() {
        try {
            finishAllActivity();
        } catch (Exception e) {
            activityStack.clear();
            e.printStackTrace();
        }
    }
}
