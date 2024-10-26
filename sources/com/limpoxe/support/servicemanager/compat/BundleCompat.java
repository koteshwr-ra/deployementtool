package com.limpoxe.support.servicemanager.compat;

import android.os.BaseBundle;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.ArrayMap;
import com.limpoxe.support.servicemanager.util.RefIectUtil;
import java.util.Map;

public class BundleCompat {
    public static IBinder getBinder(Bundle bundle, String str) {
        if (Build.VERSION.SDK_INT >= 18) {
            return bundle.getBinder(str);
        }
        return (IBinder) RefIectUtil.invokeMethod(bundle, Bundle.class, "getIBinder", new Class[]{String.class}, new Object[]{str});
    }

    public static void putBinder(Bundle bundle, String str, IBinder iBinder) {
        if (Build.VERSION.SDK_INT >= 18) {
            bundle.putBinder(str, iBinder);
            return;
        }
        RefIectUtil.invokeMethod(bundle, Bundle.class, "putIBinder", new Class[]{String.class, IBinder.class}, new Object[]{str, iBinder});
    }

    public static void putObject(Bundle bundle, String str, Object obj) {
        if (Build.VERSION.SDK_INT < 19) {
            RefIectUtil.invokeMethod(bundle, Bundle.class, "unparcel", (Class[]) null, (Object[]) null);
            ((Map) RefIectUtil.getFieldObject(bundle, Bundle.class, "mMap")).put(str, obj);
        } else if (Build.VERSION.SDK_INT == 19) {
            RefIectUtil.invokeMethod(bundle, Bundle.class, "unparcel", (Class[]) null, (Object[]) null);
            ((ArrayMap) RefIectUtil.getFieldObject(bundle, Bundle.class, "mMap")).put(str, obj);
        } else if (Build.VERSION.SDK_INT > 19) {
            RefIectUtil.invokeMethod(bundle, BaseBundle.class, "unparcel", (Class[]) null, (Object[]) null);
            ((ArrayMap) RefIectUtil.getFieldObject(bundle, BaseBundle.class, "mMap")).put(str, obj);
        }
    }
}
