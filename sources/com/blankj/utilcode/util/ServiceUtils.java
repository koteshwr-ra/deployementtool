package com.blankj.utilcode.util;

import android.app.ActivityManager;
import android.content.Intent;
import android.content.ServiceConnection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class ServiceUtils {
    private ServiceUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static Set getAllRunningServices() {
        List<ActivityManager.RunningServiceInfo> runningServices = ((ActivityManager) Utils.getApp().getSystemService("activity")).getRunningServices(Integer.MAX_VALUE);
        HashSet hashSet = new HashSet();
        if (runningServices == null || runningServices.size() == 0) {
            return null;
        }
        for (ActivityManager.RunningServiceInfo runningServiceInfo : runningServices) {
            hashSet.add(runningServiceInfo.service.getClassName());
        }
        return hashSet;
    }

    public static void startService(String str) {
        if (str != null) {
            try {
                startService(Class.forName(str));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            throw new NullPointerException("Argument 'className' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startService(Class<?> cls) {
        if (cls != null) {
            Utils.getApp().startService(new Intent(Utils.getApp(), cls));
            return;
        }
        throw new NullPointerException("Argument 'cls' of type Class<?> (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static boolean stopService(String str) {
        if (str != null) {
            try {
                return stopService(Class.forName(str));
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            throw new NullPointerException("Argument 'className' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static boolean stopService(Class<?> cls) {
        if (cls != null) {
            return Utils.getApp().stopService(new Intent(Utils.getApp(), cls));
        }
        throw new NullPointerException("Argument 'cls' of type Class<?> (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void bindService(String str, ServiceConnection serviceConnection, int i) {
        if (str == null) {
            throw new NullPointerException("Argument 'className' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (serviceConnection != null) {
            try {
                bindService(Class.forName(str), serviceConnection, i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            throw new NullPointerException("Argument 'conn' of type ServiceConnection (#1 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void bindService(Class<?> cls, ServiceConnection serviceConnection, int i) {
        if (cls == null) {
            throw new NullPointerException("Argument 'cls' of type Class<?> (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (serviceConnection != null) {
            Utils.getApp().bindService(new Intent(Utils.getApp(), cls), serviceConnection, i);
        } else {
            throw new NullPointerException("Argument 'conn' of type ServiceConnection (#1 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void unbindService(ServiceConnection serviceConnection) {
        if (serviceConnection != null) {
            Utils.getApp().unbindService(serviceConnection);
            return;
        }
        throw new NullPointerException("Argument 'conn' of type ServiceConnection (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static boolean isServiceRunning(Class<?> cls) {
        if (cls != null) {
            return isServiceRunning(cls.getName());
        }
        throw new NullPointerException("Argument 'cls' of type Class<?> (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static boolean isServiceRunning(String str) {
        if (str != null) {
            List<ActivityManager.RunningServiceInfo> runningServices = ((ActivityManager) Utils.getApp().getSystemService("activity")).getRunningServices(Integer.MAX_VALUE);
            if (!(runningServices == null || runningServices.size() == 0)) {
                for (ActivityManager.RunningServiceInfo runningServiceInfo : runningServices) {
                    if (str.equals(runningServiceInfo.service.getClassName())) {
                        return true;
                    }
                }
            }
            return false;
        }
        throw new NullPointerException("Argument 'className' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }
}
