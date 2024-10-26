package com.blankj.utilcode.util;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.blankj.utilcode.util.Utils;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.apache.mina.proxy.handlers.http.ntlm.NTLMConstants;

public final class ActivityUtils {
    private ActivityUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void addActivityLifecycleCallbacks(Activity activity, Utils.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        UtilsBridge.addActivityLifecycleCallbacks(activity, activityLifecycleCallbacks);
    }

    public static void removeActivityLifecycleCallbacks(Activity activity) {
        UtilsBridge.removeActivityLifecycleCallbacks(activity);
    }

    public static void removeActivityLifecycleCallbacks(Activity activity, Utils.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        UtilsBridge.removeActivityLifecycleCallbacks(activity, activityLifecycleCallbacks);
    }

    public static Activity getActivityByContext(Context context) {
        Activity activityByContextInner = getActivityByContextInner(context);
        if (!isActivityAlive(activityByContextInner)) {
            return null;
        }
        return activityByContextInner;
    }

    private static Activity getActivityByContextInner(Context context) {
        if (context == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            Activity activityFromDecorContext = getActivityFromDecorContext(context);
            if (activityFromDecorContext != null) {
                return activityFromDecorContext;
            }
            arrayList.add(context);
            context = ((ContextWrapper) context).getBaseContext();
            if (context != null) {
                if (arrayList.contains(context)) {
                    break;
                }
            } else {
                return null;
            }
        }
        return null;
    }

    private static Activity getActivityFromDecorContext(Context context) {
        if (context != null && context.getClass().getName().equals("com.android.internal.policy.DecorContext")) {
            try {
                Field declaredField = context.getClass().getDeclaredField("mActivityContext");
                declaredField.setAccessible(true);
                return (Activity) ((WeakReference) declaredField.get(context)).get();
            } catch (Exception unused) {
            }
        }
        return null;
    }

    public static boolean isActivityExists(String str, String str2) {
        if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (str2 != null) {
            Intent intent = new Intent();
            intent.setClassName(str, str2);
            PackageManager packageManager = Utils.getApp().getPackageManager();
            if (packageManager.resolveActivity(intent, 0) == null || intent.resolveActivity(packageManager) == null || packageManager.queryIntentActivities(intent, 0).size() == 0) {
                return false;
            }
            return true;
        } else {
            throw new NullPointerException("Argument 'cls' of type String (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivity(Class<? extends Activity> cls) {
        if (cls != null) {
            Context topActivityOrApp = UtilsBridge.getTopActivityOrApp();
            startActivity(topActivityOrApp, (Bundle) null, topActivityOrApp.getPackageName(), cls.getName(), (Bundle) null);
            return;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void startActivity(Class<? extends Activity> cls, Bundle bundle) {
        if (cls != null) {
            Context topActivityOrApp = UtilsBridge.getTopActivityOrApp();
            startActivity(topActivityOrApp, (Bundle) null, topActivityOrApp.getPackageName(), cls.getName(), bundle);
            return;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void startActivity(Class<? extends Activity> cls, int i, int i2) {
        if (cls != null) {
            Context topActivityOrApp = UtilsBridge.getTopActivityOrApp();
            startActivity(topActivityOrApp, (Bundle) null, topActivityOrApp.getPackageName(), cls.getName(), getOptionsBundle(topActivityOrApp, i, i2));
            if (Build.VERSION.SDK_INT < 16 && (topActivityOrApp instanceof Activity)) {
                ((Activity) topActivityOrApp).overridePendingTransition(i, i2);
                return;
            }
            return;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void startActivity(Activity activity, Class<? extends Activity> cls) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (cls != null) {
            startActivity((Context) activity, (Bundle) null, activity.getPackageName(), cls.getName(), (Bundle) null);
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivity(Activity activity, Class<? extends Activity> cls, Bundle bundle) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (cls != null) {
            startActivity((Context) activity, (Bundle) null, activity.getPackageName(), cls.getName(), bundle);
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivity(Activity activity, Class<? extends Activity> cls, View... viewArr) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (cls != null) {
            startActivity((Context) activity, (Bundle) null, activity.getPackageName(), cls.getName(), getOptionsBundle(activity, viewArr));
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivity(Activity activity, Class<? extends Activity> cls, int i, int i2) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (cls != null) {
            startActivity((Context) activity, (Bundle) null, activity.getPackageName(), cls.getName(), getOptionsBundle((Context) activity, i, i2));
            if (Build.VERSION.SDK_INT < 16) {
                activity.overridePendingTransition(i, i2);
            }
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivity(Bundle bundle, Class<? extends Activity> cls) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (cls != null) {
            Context topActivityOrApp = UtilsBridge.getTopActivityOrApp();
            startActivity(topActivityOrApp, bundle, topActivityOrApp.getPackageName(), cls.getName(), (Bundle) null);
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivity(Bundle bundle, Class<? extends Activity> cls, Bundle bundle2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (cls != null) {
            Context topActivityOrApp = UtilsBridge.getTopActivityOrApp();
            startActivity(topActivityOrApp, bundle, topActivityOrApp.getPackageName(), cls.getName(), bundle2);
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivity(Bundle bundle, Class<? extends Activity> cls, int i, int i2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (cls != null) {
            Context topActivityOrApp = UtilsBridge.getTopActivityOrApp();
            startActivity(topActivityOrApp, bundle, topActivityOrApp.getPackageName(), cls.getName(), getOptionsBundle(topActivityOrApp, i, i2));
            if (Build.VERSION.SDK_INT < 16 && (topActivityOrApp instanceof Activity)) {
                ((Activity) topActivityOrApp).overridePendingTransition(i, i2);
            }
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivity(Bundle bundle, Activity activity, Class<? extends Activity> cls) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (cls != null) {
            startActivity((Context) activity, bundle, activity.getPackageName(), cls.getName(), (Bundle) null);
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#2 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivity(Bundle bundle, Activity activity, Class<? extends Activity> cls, Bundle bundle2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (cls != null) {
            startActivity((Context) activity, bundle, activity.getPackageName(), cls.getName(), bundle2);
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#2 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivity(Bundle bundle, Activity activity, Class<? extends Activity> cls, View... viewArr) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (cls != null) {
            startActivity((Context) activity, bundle, activity.getPackageName(), cls.getName(), getOptionsBundle(activity, viewArr));
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#2 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivity(Bundle bundle, Activity activity, Class<? extends Activity> cls, int i, int i2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (cls != null) {
            startActivity((Context) activity, bundle, activity.getPackageName(), cls.getName(), getOptionsBundle((Context) activity, i, i2));
            if (Build.VERSION.SDK_INT < 16) {
                activity.overridePendingTransition(i, i2);
            }
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#2 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivity(String str, String str2) {
        if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (str2 != null) {
            startActivity(UtilsBridge.getTopActivityOrApp(), (Bundle) null, str, str2, (Bundle) null);
        } else {
            throw new NullPointerException("Argument 'cls' of type String (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivity(String str, String str2, Bundle bundle) {
        if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (str2 != null) {
            startActivity(UtilsBridge.getTopActivityOrApp(), (Bundle) null, str, str2, bundle);
        } else {
            throw new NullPointerException("Argument 'cls' of type String (#1 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivity(String str, String str2, int i, int i2) {
        if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#0 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (str2 != null) {
            Context topActivityOrApp = UtilsBridge.getTopActivityOrApp();
            startActivity(topActivityOrApp, (Bundle) null, str, str2, getOptionsBundle(topActivityOrApp, i, i2));
            if (Build.VERSION.SDK_INT < 16 && (topActivityOrApp instanceof Activity)) {
                ((Activity) topActivityOrApp).overridePendingTransition(i, i2);
            }
        } else {
            throw new NullPointerException("Argument 'cls' of type String (#1 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivity(Activity activity, String str, String str2) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#1 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (str2 != null) {
            startActivity((Context) activity, (Bundle) null, str, str2, (Bundle) null);
        } else {
            throw new NullPointerException("Argument 'cls' of type String (#2 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivity(Activity activity, String str, String str2, Bundle bundle) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#1 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (str2 != null) {
            startActivity((Context) activity, (Bundle) null, str, str2, bundle);
        } else {
            throw new NullPointerException("Argument 'cls' of type String (#2 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivity(Activity activity, String str, String str2, View... viewArr) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#1 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (str2 != null) {
            startActivity((Context) activity, (Bundle) null, str, str2, getOptionsBundle(activity, viewArr));
        } else {
            throw new NullPointerException("Argument 'cls' of type String (#2 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivity(Activity activity, String str, String str2, int i, int i2) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#1 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (str2 != null) {
            startActivity((Context) activity, (Bundle) null, str, str2, getOptionsBundle((Context) activity, i, i2));
            if (Build.VERSION.SDK_INT < 16) {
                activity.overridePendingTransition(i, i2);
            }
        } else {
            throw new NullPointerException("Argument 'cls' of type String (#2 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivity(Bundle bundle, String str, String str2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#1 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (str2 != null) {
            startActivity(UtilsBridge.getTopActivityOrApp(), bundle, str, str2, (Bundle) null);
        } else {
            throw new NullPointerException("Argument 'cls' of type String (#2 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivity(Bundle bundle, String str, String str2, Bundle bundle2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#1 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (str2 != null) {
            startActivity(UtilsBridge.getTopActivityOrApp(), bundle, str, str2, bundle2);
        } else {
            throw new NullPointerException("Argument 'cls' of type String (#2 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivity(Bundle bundle, String str, String str2, int i, int i2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#1 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (str2 != null) {
            Context topActivityOrApp = UtilsBridge.getTopActivityOrApp();
            startActivity(topActivityOrApp, bundle, str, str2, getOptionsBundle(topActivityOrApp, i, i2));
            if (Build.VERSION.SDK_INT < 16 && (topActivityOrApp instanceof Activity)) {
                ((Activity) topActivityOrApp).overridePendingTransition(i, i2);
            }
        } else {
            throw new NullPointerException("Argument 'cls' of type String (#2 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivity(Bundle bundle, Activity activity, String str, String str2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#2 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (str2 != null) {
            startActivity((Context) activity, bundle, str, str2, (Bundle) null);
        } else {
            throw new NullPointerException("Argument 'cls' of type String (#3 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivity(Bundle bundle, Activity activity, String str, String str2, Bundle bundle2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#2 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (str2 != null) {
            startActivity((Context) activity, bundle, str, str2, bundle2);
        } else {
            throw new NullPointerException("Argument 'cls' of type String (#3 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivity(Bundle bundle, Activity activity, String str, String str2, View... viewArr) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#2 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (str2 != null) {
            startActivity((Context) activity, bundle, str, str2, getOptionsBundle(activity, viewArr));
        } else {
            throw new NullPointerException("Argument 'cls' of type String (#3 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivity(Bundle bundle, Activity activity, String str, String str2, int i, int i2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 6, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 6, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#2 out of 6, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (str2 != null) {
            startActivity((Context) activity, bundle, str, str2, getOptionsBundle((Context) activity, i, i2));
            if (Build.VERSION.SDK_INT < 16) {
                activity.overridePendingTransition(i, i2);
            }
        } else {
            throw new NullPointerException("Argument 'cls' of type String (#3 out of 6, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static boolean startActivity(Intent intent) {
        if (intent != null) {
            return startActivity(intent, UtilsBridge.getTopActivityOrApp(), (Bundle) null);
        }
        throw new NullPointerException("Argument 'intent' of type Intent (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static boolean startActivity(Intent intent, Bundle bundle) {
        if (intent != null) {
            return startActivity(intent, UtilsBridge.getTopActivityOrApp(), bundle);
        }
        throw new NullPointerException("Argument 'intent' of type Intent (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static boolean startActivity(Intent intent, int i, int i2) {
        if (intent != null) {
            Context topActivityOrApp = UtilsBridge.getTopActivityOrApp();
            boolean startActivity = startActivity(intent, topActivityOrApp, getOptionsBundle(topActivityOrApp, i, i2));
            if (startActivity && Build.VERSION.SDK_INT < 16 && (topActivityOrApp instanceof Activity)) {
                ((Activity) topActivityOrApp).overridePendingTransition(i, i2);
            }
            return startActivity;
        }
        throw new NullPointerException("Argument 'intent' of type Intent (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void startActivity(Activity activity, Intent intent) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (intent != null) {
            startActivity(intent, (Context) activity, (Bundle) null);
        } else {
            throw new NullPointerException("Argument 'intent' of type Intent (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivity(Activity activity, Intent intent, Bundle bundle) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (intent != null) {
            startActivity(intent, (Context) activity, bundle);
        } else {
            throw new NullPointerException("Argument 'intent' of type Intent (#1 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivity(Activity activity, Intent intent, View... viewArr) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (intent != null) {
            startActivity(intent, (Context) activity, getOptionsBundle(activity, viewArr));
        } else {
            throw new NullPointerException("Argument 'intent' of type Intent (#1 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivity(Activity activity, Intent intent, int i, int i2) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (intent != null) {
            startActivity(intent, (Context) activity, getOptionsBundle((Context) activity, i, i2));
            if (Build.VERSION.SDK_INT < 16) {
                activity.overridePendingTransition(i, i2);
            }
        } else {
            throw new NullPointerException("Argument 'intent' of type Intent (#1 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivityForResult(Activity activity, Class<? extends Activity> cls, int i) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (cls != null) {
            startActivityForResult(activity, (Bundle) null, activity.getPackageName(), cls.getName(), i, (Bundle) null);
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivityForResult(Activity activity, Class<? extends Activity> cls, int i, Bundle bundle) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (cls != null) {
            startActivityForResult(activity, (Bundle) null, activity.getPackageName(), cls.getName(), i, bundle);
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivityForResult(Activity activity, Class<? extends Activity> cls, int i, View... viewArr) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (cls != null) {
            startActivityForResult(activity, (Bundle) null, activity.getPackageName(), cls.getName(), i, getOptionsBundle(activity, viewArr));
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivityForResult(Activity activity, Class<? extends Activity> cls, int i, int i2, int i3) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (cls != null) {
            startActivityForResult(activity, (Bundle) null, activity.getPackageName(), cls.getName(), i, getOptionsBundle((Context) activity, i2, i3));
            if (Build.VERSION.SDK_INT < 16) {
                activity.overridePendingTransition(i2, i3);
            }
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivityForResult(Bundle bundle, Activity activity, Class<? extends Activity> cls, int i) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (cls != null) {
            startActivityForResult(activity, bundle, activity.getPackageName(), cls.getName(), i, (Bundle) null);
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#2 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivityForResult(Bundle bundle, Activity activity, Class<? extends Activity> cls, int i, Bundle bundle2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (cls != null) {
            startActivityForResult(activity, bundle, activity.getPackageName(), cls.getName(), i, bundle2);
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#2 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivityForResult(Bundle bundle, Activity activity, Class<? extends Activity> cls, int i, View... viewArr) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (cls != null) {
            startActivityForResult(activity, bundle, activity.getPackageName(), cls.getName(), i, getOptionsBundle(activity, viewArr));
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#2 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivityForResult(Bundle bundle, Activity activity, Class<? extends Activity> cls, int i, int i2, int i3) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 6, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 6, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (cls != null) {
            startActivityForResult(activity, bundle, activity.getPackageName(), cls.getName(), i, getOptionsBundle((Context) activity, i2, i3));
            if (Build.VERSION.SDK_INT < 16) {
                activity.overridePendingTransition(i2, i3);
            }
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#2 out of 6, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivityForResult(Bundle bundle, Activity activity, String str, String str2, int i) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#2 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (str2 != null) {
            startActivityForResult(activity, bundle, str, str2, i, (Bundle) null);
        } else {
            throw new NullPointerException("Argument 'cls' of type String (#3 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivityForResult(Bundle bundle, Activity activity, String str, String str2, int i, Bundle bundle2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 6, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 6, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#2 out of 6, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (str2 != null) {
            startActivityForResult(activity, bundle, str, str2, i, bundle2);
        } else {
            throw new NullPointerException("Argument 'cls' of type String (#3 out of 6, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivityForResult(Bundle bundle, Activity activity, String str, String str2, int i, View... viewArr) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 6, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 6, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#2 out of 6, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (str2 != null) {
            startActivityForResult(activity, bundle, str, str2, i, getOptionsBundle(activity, viewArr));
        } else {
            throw new NullPointerException("Argument 'cls' of type String (#3 out of 6, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivityForResult(Bundle bundle, Activity activity, String str, String str2, int i, int i2, int i3) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 7, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 7, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#2 out of 7, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (str2 != null) {
            startActivityForResult(activity, bundle, str, str2, i, getOptionsBundle((Context) activity, i2, i3));
            if (Build.VERSION.SDK_INT < 16) {
                activity.overridePendingTransition(i2, i3);
            }
        } else {
            throw new NullPointerException("Argument 'cls' of type String (#3 out of 7, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivityForResult(Activity activity, Intent intent, int i) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (intent != null) {
            startActivityForResult(intent, activity, i, (Bundle) null);
        } else {
            throw new NullPointerException("Argument 'intent' of type Intent (#1 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivityForResult(Activity activity, Intent intent, int i, Bundle bundle) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (intent != null) {
            startActivityForResult(intent, activity, i, bundle);
        } else {
            throw new NullPointerException("Argument 'intent' of type Intent (#1 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivityForResult(Activity activity, Intent intent, int i, View... viewArr) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (intent != null) {
            startActivityForResult(intent, activity, i, getOptionsBundle(activity, viewArr));
        } else {
            throw new NullPointerException("Argument 'intent' of type Intent (#1 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivityForResult(Activity activity, Intent intent, int i, int i2, int i3) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (intent != null) {
            startActivityForResult(intent, activity, i, getOptionsBundle((Context) activity, i2, i3));
            if (Build.VERSION.SDK_INT < 16) {
                activity.overridePendingTransition(i2, i3);
            }
        } else {
            throw new NullPointerException("Argument 'intent' of type Intent (#1 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivityForResult(Fragment fragment, Class<? extends Activity> cls, int i) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (cls != null) {
            startActivityForResult(fragment, (Bundle) null, Utils.getApp().getPackageName(), cls.getName(), i, (Bundle) null);
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivityForResult(Fragment fragment, Class<? extends Activity> cls, int i, Bundle bundle) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#0 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (cls != null) {
            startActivityForResult(fragment, (Bundle) null, Utils.getApp().getPackageName(), cls.getName(), i, bundle);
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivityForResult(Fragment fragment, Class<? extends Activity> cls, int i, View... viewArr) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#0 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (cls != null) {
            startActivityForResult(fragment, (Bundle) null, Utils.getApp().getPackageName(), cls.getName(), i, getOptionsBundle(fragment, viewArr));
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivityForResult(Fragment fragment, Class<? extends Activity> cls, int i, int i2, int i3) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#0 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (cls != null) {
            startActivityForResult(fragment, (Bundle) null, Utils.getApp().getPackageName(), cls.getName(), i, getOptionsBundle(fragment, i2, i3));
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivityForResult(Bundle bundle, Fragment fragment, Class<? extends Activity> cls, int i) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (cls != null) {
            startActivityForResult(fragment, bundle, Utils.getApp().getPackageName(), cls.getName(), i, (Bundle) null);
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#2 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivityForResult(Bundle bundle, Fragment fragment, Class<? extends Activity> cls, int i, Bundle bundle2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (cls != null) {
            startActivityForResult(fragment, bundle, Utils.getApp().getPackageName(), cls.getName(), i, bundle2);
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#2 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivityForResult(Bundle bundle, Fragment fragment, Class<? extends Activity> cls, int i, View... viewArr) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (cls != null) {
            startActivityForResult(fragment, bundle, Utils.getApp().getPackageName(), cls.getName(), i, getOptionsBundle(fragment, viewArr));
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#2 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivityForResult(Bundle bundle, Fragment fragment, Class<? extends Activity> cls, int i, int i2, int i3) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 6, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 6, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (cls != null) {
            startActivityForResult(fragment, bundle, Utils.getApp().getPackageName(), cls.getName(), i, getOptionsBundle(fragment, i2, i3));
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#2 out of 6, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivityForResult(Bundle bundle, Fragment fragment, String str, String str2, int i) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#2 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (str2 != null) {
            startActivityForResult(fragment, bundle, str, str2, i, (Bundle) null);
        } else {
            throw new NullPointerException("Argument 'cls' of type String (#3 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivityForResult(Bundle bundle, Fragment fragment, String str, String str2, int i, Bundle bundle2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 6, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 6, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#2 out of 6, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (str2 != null) {
            startActivityForResult(fragment, bundle, str, str2, i, bundle2);
        } else {
            throw new NullPointerException("Argument 'cls' of type String (#3 out of 6, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivityForResult(Bundle bundle, Fragment fragment, String str, String str2, int i, View... viewArr) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 6, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 6, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#2 out of 6, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (str2 != null) {
            startActivityForResult(fragment, bundle, str, str2, i, getOptionsBundle(fragment, viewArr));
        } else {
            throw new NullPointerException("Argument 'cls' of type String (#3 out of 6, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivityForResult(Bundle bundle, Fragment fragment, String str, String str2, int i, int i2, int i3) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 7, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 7, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#2 out of 7, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (str2 != null) {
            startActivityForResult(fragment, bundle, str, str2, i, getOptionsBundle(fragment, i2, i3));
        } else {
            throw new NullPointerException("Argument 'cls' of type String (#3 out of 7, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivityForResult(Fragment fragment, Intent intent, int i) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (intent != null) {
            startActivityForResult(intent, fragment, i, (Bundle) null);
        } else {
            throw new NullPointerException("Argument 'intent' of type Intent (#1 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivityForResult(Fragment fragment, Intent intent, int i, Bundle bundle) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#0 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (intent != null) {
            startActivityForResult(intent, fragment, i, bundle);
        } else {
            throw new NullPointerException("Argument 'intent' of type Intent (#1 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivityForResult(Fragment fragment, Intent intent, int i, View... viewArr) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#0 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (intent != null) {
            startActivityForResult(intent, fragment, i, getOptionsBundle(fragment, viewArr));
        } else {
            throw new NullPointerException("Argument 'intent' of type Intent (#1 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivityForResult(Fragment fragment, Intent intent, int i, int i2, int i3) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#0 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (intent != null) {
            startActivityForResult(intent, fragment, i, getOptionsBundle(fragment, i2, i3));
        } else {
            throw new NullPointerException("Argument 'intent' of type Intent (#1 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivities(Intent[] intentArr) {
        if (intentArr != null) {
            startActivities(intentArr, UtilsBridge.getTopActivityOrApp(), (Bundle) null);
            return;
        }
        throw new NullPointerException("Argument 'intents' of type Intent[] (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void startActivities(Intent[] intentArr, Bundle bundle) {
        if (intentArr != null) {
            startActivities(intentArr, UtilsBridge.getTopActivityOrApp(), bundle);
            return;
        }
        throw new NullPointerException("Argument 'intents' of type Intent[] (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void startActivities(Intent[] intentArr, int i, int i2) {
        if (intentArr != null) {
            Context topActivityOrApp = UtilsBridge.getTopActivityOrApp();
            startActivities(intentArr, topActivityOrApp, getOptionsBundle(topActivityOrApp, i, i2));
            if (Build.VERSION.SDK_INT < 16 && (topActivityOrApp instanceof Activity)) {
                ((Activity) topActivityOrApp).overridePendingTransition(i, i2);
                return;
            }
            return;
        }
        throw new NullPointerException("Argument 'intents' of type Intent[] (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void startActivities(Activity activity, Intent[] intentArr) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (intentArr != null) {
            startActivities(intentArr, (Context) activity, (Bundle) null);
        } else {
            throw new NullPointerException("Argument 'intents' of type Intent[] (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivities(Activity activity, Intent[] intentArr, Bundle bundle) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (intentArr != null) {
            startActivities(intentArr, (Context) activity, bundle);
        } else {
            throw new NullPointerException("Argument 'intents' of type Intent[] (#1 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startActivities(Activity activity, Intent[] intentArr, int i, int i2) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (intentArr != null) {
            startActivities(intentArr, (Context) activity, getOptionsBundle((Context) activity, i, i2));
            if (Build.VERSION.SDK_INT < 16) {
                activity.overridePendingTransition(i, i2);
            }
        } else {
            throw new NullPointerException("Argument 'intents' of type Intent[] (#1 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void startHomeActivity() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        intent.setFlags(NTLMConstants.FLAG_UNIDENTIFIED_11);
        startActivity(intent);
    }

    public static void startLauncherActivity() {
        startLauncherActivity(Utils.getApp().getPackageName());
    }

    public static void startLauncherActivity(String str) {
        if (str != null) {
            String launcherActivity = getLauncherActivity(str);
            if (!TextUtils.isEmpty(launcherActivity)) {
                startActivity(str, launcherActivity);
                return;
            }
            return;
        }
        throw new NullPointerException("Argument 'pkg' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static List<Activity> getActivityList() {
        return UtilsBridge.getActivityList();
    }

    public static String getLauncherActivity() {
        return getLauncherActivity(Utils.getApp().getPackageName());
    }

    public static String getLauncherActivity(String str) {
        if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (UtilsBridge.isSpace(str)) {
            return "";
        } else {
            Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.setPackage(str);
            List<ResolveInfo> queryIntentActivities = Utils.getApp().getPackageManager().queryIntentActivities(intent, 0);
            if (queryIntentActivities == null || queryIntentActivities.size() == 0) {
                return "";
            }
            return queryIntentActivities.get(0).activityInfo.name;
        }
    }

    public static List<String> getMainActivities() {
        return getMainActivities(Utils.getApp().getPackageName());
    }

    public static List<String> getMainActivities(String str) {
        if (str != null) {
            ArrayList arrayList = new ArrayList();
            Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
            intent.setPackage(str);
            List<ResolveInfo> queryIntentActivities = Utils.getApp().getPackageManager().queryIntentActivities(intent, 0);
            int size = queryIntentActivities.size();
            if (size == 0) {
                return arrayList;
            }
            for (int i = 0; i < size; i++) {
                ResolveInfo resolveInfo = queryIntentActivities.get(i);
                if (resolveInfo.activityInfo.processName.equals(str)) {
                    arrayList.add(resolveInfo.activityInfo.name);
                }
            }
            return arrayList;
        }
        throw new NullPointerException("Argument 'pkg' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static Activity getTopActivity() {
        return UtilsBridge.getTopActivity();
    }

    public static boolean isActivityAlive(Context context) {
        return isActivityAlive(getActivityByContext(context));
    }

    public static boolean isActivityAlive(Activity activity) {
        return activity != null && !activity.isFinishing() && (Build.VERSION.SDK_INT < 17 || !activity.isDestroyed());
    }

    public static boolean isActivityExistsInStack(Activity activity) {
        if (activity != null) {
            for (Activity equals : UtilsBridge.getActivityList()) {
                if (equals.equals(activity)) {
                    return true;
                }
            }
            return false;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static boolean isActivityExistsInStack(Class<? extends Activity> cls) {
        if (cls != null) {
            for (Activity activity : UtilsBridge.getActivityList()) {
                if (activity.getClass().equals(cls)) {
                    return true;
                }
            }
            return false;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void finishActivity(Activity activity) {
        if (activity != null) {
            finishActivity(activity, false);
            return;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void finishActivity(Activity activity, boolean z) {
        if (activity != null) {
            activity.finish();
            if (!z) {
                activity.overridePendingTransition(0, 0);
                return;
            }
            return;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void finishActivity(Activity activity, int i, int i2) {
        if (activity != null) {
            activity.finish();
            activity.overridePendingTransition(i, i2);
            return;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void finishActivity(Class<? extends Activity> cls) {
        if (cls != null) {
            finishActivity(cls, false);
            return;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void finishActivity(Class<? extends Activity> cls, boolean z) {
        if (cls != null) {
            for (Activity next : UtilsBridge.getActivityList()) {
                if (next.getClass().equals(cls)) {
                    next.finish();
                    if (!z) {
                        next.overridePendingTransition(0, 0);
                    }
                }
            }
            return;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void finishActivity(Class<? extends Activity> cls, int i, int i2) {
        if (cls != null) {
            for (Activity next : UtilsBridge.getActivityList()) {
                if (next.getClass().equals(cls)) {
                    next.finish();
                    next.overridePendingTransition(i, i2);
                }
            }
            return;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static boolean finishToActivity(Activity activity, boolean z) {
        if (activity != null) {
            return finishToActivity(activity, z, false);
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static boolean finishToActivity(Activity activity, boolean z, boolean z2) {
        if (activity != null) {
            for (Activity next : UtilsBridge.getActivityList()) {
                if (!next.equals(activity)) {
                    finishActivity(next, z2);
                } else if (!z) {
                    return true;
                } else {
                    finishActivity(next, z2);
                    return true;
                }
            }
            return false;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static boolean finishToActivity(Activity activity, boolean z, int i, int i2) {
        if (activity != null) {
            for (Activity next : UtilsBridge.getActivityList()) {
                if (!next.equals(activity)) {
                    finishActivity(next, i, i2);
                } else if (!z) {
                    return true;
                } else {
                    finishActivity(next, i, i2);
                    return true;
                }
            }
            return false;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static boolean finishToActivity(Class<? extends Activity> cls, boolean z) {
        if (cls != null) {
            return finishToActivity(cls, z, false);
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static boolean finishToActivity(Class<? extends Activity> cls, boolean z, boolean z2) {
        if (cls != null) {
            for (Activity next : UtilsBridge.getActivityList()) {
                if (!next.getClass().equals(cls)) {
                    finishActivity(next, z2);
                } else if (!z) {
                    return true;
                } else {
                    finishActivity(next, z2);
                    return true;
                }
            }
            return false;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static boolean finishToActivity(Class<? extends Activity> cls, boolean z, int i, int i2) {
        if (cls != null) {
            for (Activity next : UtilsBridge.getActivityList()) {
                if (!next.getClass().equals(cls)) {
                    finishActivity(next, i, i2);
                } else if (!z) {
                    return true;
                } else {
                    finishActivity(next, i, i2);
                    return true;
                }
            }
            return false;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void finishOtherActivities(Class<? extends Activity> cls) {
        if (cls != null) {
            finishOtherActivities(cls, false);
            return;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void finishOtherActivities(Class<? extends Activity> cls, boolean z) {
        if (cls != null) {
            for (Activity next : UtilsBridge.getActivityList()) {
                if (!next.getClass().equals(cls)) {
                    finishActivity(next, z);
                }
            }
            return;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void finishOtherActivities(Class<? extends Activity> cls, int i, int i2) {
        if (cls != null) {
            for (Activity next : UtilsBridge.getActivityList()) {
                if (!next.getClass().equals(cls)) {
                    finishActivity(next, i, i2);
                }
            }
            return;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void finishAllActivities() {
        finishAllActivities(false);
    }

    public static void finishAllActivities(boolean z) {
        for (Activity next : UtilsBridge.getActivityList()) {
            next.finish();
            if (!z) {
                next.overridePendingTransition(0, 0);
            }
        }
    }

    public static void finishAllActivities(int i, int i2) {
        for (Activity next : UtilsBridge.getActivityList()) {
            next.finish();
            next.overridePendingTransition(i, i2);
        }
    }

    public static void finishAllActivitiesExceptNewest() {
        finishAllActivitiesExceptNewest(false);
    }

    public static void finishAllActivitiesExceptNewest(boolean z) {
        List<Activity> activityList = UtilsBridge.getActivityList();
        for (int i = 1; i < activityList.size(); i++) {
            finishActivity(activityList.get(i), z);
        }
    }

    public static void finishAllActivitiesExceptNewest(int i, int i2) {
        List<Activity> activityList = UtilsBridge.getActivityList();
        for (int i3 = 1; i3 < activityList.size(); i3++) {
            finishActivity(activityList.get(i3), i, i2);
        }
    }

    public static Drawable getActivityIcon(Activity activity) {
        if (activity != null) {
            return getActivityIcon(activity.getComponentName());
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static Drawable getActivityIcon(Class<? extends Activity> cls) {
        if (cls != null) {
            return getActivityIcon(new ComponentName(Utils.getApp(), cls));
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static Drawable getActivityIcon(ComponentName componentName) {
        if (componentName != null) {
            try {
                return Utils.getApp().getPackageManager().getActivityIcon(componentName);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            throw new NullPointerException("Argument 'activityName' of type ComponentName (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static Drawable getActivityLogo(Activity activity) {
        if (activity != null) {
            return getActivityLogo(activity.getComponentName());
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static Drawable getActivityLogo(Class<? extends Activity> cls) {
        if (cls != null) {
            return getActivityLogo(new ComponentName(Utils.getApp(), cls));
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static Drawable getActivityLogo(ComponentName componentName) {
        if (componentName != null) {
            try {
                return Utils.getApp().getPackageManager().getActivityLogo(componentName);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            throw new NullPointerException("Argument 'activityName' of type ComponentName (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    private static void startActivity(Context context, Bundle bundle, String str, String str2, Bundle bundle2) {
        Intent intent = new Intent();
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.setComponent(new ComponentName(str, str2));
        startActivity(intent, context, bundle2);
    }

    private static boolean startActivity(Intent intent, Context context, Bundle bundle) {
        if (!isIntentAvailable(intent)) {
            Log.e("ActivityUtils", "intent is unavailable");
            return false;
        }
        if (!(context instanceof Activity)) {
            intent.addFlags(NTLMConstants.FLAG_UNIDENTIFIED_11);
        }
        if (bundle == null || Build.VERSION.SDK_INT < 16) {
            context.startActivity(intent);
            return true;
        }
        context.startActivity(intent, bundle);
        return true;
    }

    private static boolean isIntentAvailable(Intent intent) {
        return Utils.getApp().getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
    }

    private static boolean startActivityForResult(Activity activity, Bundle bundle, String str, String str2, int i, Bundle bundle2) {
        Intent intent = new Intent();
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.setComponent(new ComponentName(str, str2));
        return startActivityForResult(intent, activity, i, bundle2);
    }

    private static boolean startActivityForResult(Intent intent, Activity activity, int i, Bundle bundle) {
        if (!isIntentAvailable(intent)) {
            Log.e("ActivityUtils", "intent is unavailable");
            return false;
        } else if (bundle == null || Build.VERSION.SDK_INT < 16) {
            activity.startActivityForResult(intent, i);
            return true;
        } else {
            activity.startActivityForResult(intent, i, bundle);
            return true;
        }
    }

    private static void startActivities(Intent[] intentArr, Context context, Bundle bundle) {
        if (!(context instanceof Activity)) {
            for (Intent addFlags : intentArr) {
                addFlags.addFlags(NTLMConstants.FLAG_UNIDENTIFIED_11);
            }
        }
        if (bundle == null || Build.VERSION.SDK_INT < 16) {
            context.startActivities(intentArr);
        } else {
            context.startActivities(intentArr, bundle);
        }
    }

    private static boolean startActivityForResult(Fragment fragment, Bundle bundle, String str, String str2, int i, Bundle bundle2) {
        Intent intent = new Intent();
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.setComponent(new ComponentName(str, str2));
        return startActivityForResult(intent, fragment, i, bundle2);
    }

    private static boolean startActivityForResult(Intent intent, Fragment fragment, int i, Bundle bundle) {
        if (!isIntentAvailable(intent)) {
            Log.e("ActivityUtils", "intent is unavailable");
            return false;
        } else if (fragment.getActivity() == null) {
            Log.e("ActivityUtils", "Fragment " + fragment + " not attached to Activity");
            return false;
        } else if (bundle == null || Build.VERSION.SDK_INT < 16) {
            fragment.startActivityForResult(intent, i);
            return true;
        } else {
            fragment.startActivityForResult(intent, i, bundle);
            return true;
        }
    }

    private static Bundle getOptionsBundle(Fragment fragment, int i, int i2) {
        FragmentActivity activity = fragment.getActivity();
        if (activity == null) {
            return null;
        }
        return ActivityOptionsCompat.makeCustomAnimation(activity, i, i2).toBundle();
    }

    private static Bundle getOptionsBundle(Context context, int i, int i2) {
        return ActivityOptionsCompat.makeCustomAnimation(context, i, i2).toBundle();
    }

    private static Bundle getOptionsBundle(Fragment fragment, View[] viewArr) {
        FragmentActivity activity = fragment.getActivity();
        if (activity == null) {
            return null;
        }
        return getOptionsBundle((Activity) activity, viewArr);
    }

    private static Bundle getOptionsBundle(Activity activity, View[] viewArr) {
        int length;
        if (Build.VERSION.SDK_INT < 21 || viewArr == null || (length = viewArr.length) <= 0) {
            return null;
        }
        Pair[] pairArr = new Pair[length];
        for (int i = 0; i < length; i++) {
            pairArr[i] = Pair.create(viewArr[i], viewArr[i].getTransitionName());
        }
        return ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pairArr).toBundle();
    }
}
