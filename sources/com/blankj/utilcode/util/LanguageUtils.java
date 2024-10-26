package com.blankj.utilcode.util;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import java.lang.reflect.Field;
import java.util.Locale;

public class LanguageUtils {
    private static final String KEY_LOCALE = "KEY_LOCALE";
    private static final String VALUE_FOLLOW_SYSTEM = "VALUE_FOLLOW_SYSTEM";

    private LanguageUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void applySystemLanguage() {
        if (!isAppliedSystemLanguage()) {
            applyLanguage(Resources.getSystem().getConfiguration().locale, "", true, false);
        }
    }

    public static void applySystemLanguage(Class<? extends Activity> cls) {
        applyLanguage(Resources.getSystem().getConfiguration().locale, cls, true, true);
    }

    public static void applySystemLanguage(String str) {
        applyLanguage(Resources.getSystem().getConfiguration().locale, str, true, true);
    }

    public static void applyLanguage(Locale locale) {
        if (locale == null) {
            throw new NullPointerException("Argument 'locale' of type Locale (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (!isAppliedLanguage(locale)) {
            applyLanguage(locale, "", false, false);
        }
    }

    public static void applyLanguage(Locale locale, Class<? extends Activity> cls) {
        if (locale != null) {
            applyLanguage(locale, cls, false, true);
            return;
        }
        throw new NullPointerException("Argument 'locale' of type Locale (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void applyLanguage(Locale locale, String str) {
        if (locale != null) {
            applyLanguage(locale, str, false, true);
            return;
        }
        throw new NullPointerException("Argument 'locale' of type Locale (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    private static void applyLanguage(Locale locale, Class<? extends Activity> cls, boolean z, boolean z2) {
        if (locale == null) {
            throw new NullPointerException("Argument 'locale' of type Locale (#0 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (cls == null) {
            applyLanguage(locale, "", z, z2);
        } else {
            applyLanguage(locale, cls.getName(), z, z2);
        }
    }

    private static void applyLanguage(Locale locale, String str, boolean z, boolean z2) {
        if (locale != null) {
            if (z) {
                UtilsBridge.getSpUtils4Utils().put(KEY_LOCALE, VALUE_FOLLOW_SYSTEM);
            } else {
                UtilsBridge.getSpUtils4Utils().put(KEY_LOCALE, locale2String(locale));
            }
            updateLanguage(Utils.getApp(), locale);
            if (z2) {
                Intent intent = new Intent();
                if (TextUtils.isEmpty(str)) {
                    str = UtilsBridge.getLauncherActivity();
                }
                intent.setComponent(new ComponentName(Utils.getApp(), str));
                intent.addFlags(335577088);
                Utils.getApp().startActivity(intent);
                return;
            }
            return;
        }
        throw new NullPointerException("Argument 'locale' of type Locale (#0 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static boolean isAppliedSystemLanguage() {
        return VALUE_FOLLOW_SYSTEM.equals(UtilsBridge.getSpUtils4Utils().getString(KEY_LOCALE));
    }

    public static boolean isAppliedLanguage() {
        return !TextUtils.isEmpty(UtilsBridge.getSpUtils4Utils().getString(KEY_LOCALE));
    }

    public static boolean isAppliedLanguage(Locale locale) {
        Locale string2Locale;
        String string = UtilsBridge.getSpUtils4Utils().getString(KEY_LOCALE);
        if (!TextUtils.isEmpty(string) && !VALUE_FOLLOW_SYSTEM.equals(string) && (string2Locale = string2Locale(string)) != null) {
            return isSameLocale(string2Locale, locale);
        }
        return false;
    }

    public static Locale getCurrentLocale() {
        return Utils.getApp().getResources().getConfiguration().locale;
    }

    static void applyLanguage(Activity activity) {
        if (activity != null) {
            String string = UtilsBridge.getSpUtils4Utils().getString(KEY_LOCALE);
            if (!TextUtils.isEmpty(string)) {
                if (VALUE_FOLLOW_SYSTEM.equals(string)) {
                    Locale locale = Resources.getSystem().getConfiguration().locale;
                    updateLanguage(Utils.getApp(), locale);
                    updateLanguage(activity, locale);
                    return;
                }
                Locale string2Locale = string2Locale(string);
                if (string2Locale != null) {
                    updateLanguage(Utils.getApp(), string2Locale);
                    updateLanguage(activity, string2Locale);
                    return;
                }
                return;
            }
            return;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    private static String locale2String(Locale locale) {
        String language = locale.getLanguage();
        String country = locale.getCountry();
        return language + "$" + country;
    }

    private static Locale string2Locale(String str) {
        String[] split = str.split("\\$");
        if (split.length == 2) {
            return new Locale(split[0], split[1]);
        }
        Log.e("LanguageUtils", "The string of " + str + " is not in the correct format.");
        return null;
    }

    private static void updateLanguage(Context context, Locale locale) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        if (!isSameLocale(configuration.locale, locale)) {
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            if (Build.VERSION.SDK_INT >= 17) {
                configuration.setLocale(locale);
                if (context instanceof Application) {
                    Context createConfigurationContext = context.createConfigurationContext(configuration);
                    try {
                        Field declaredField = ContextWrapper.class.getDeclaredField("mBase");
                        declaredField.setAccessible(true);
                        declaredField.set(context, createConfigurationContext);
                    } catch (Exception unused) {
                    }
                }
            } else {
                configuration.locale = locale;
            }
            resources.updateConfiguration(configuration, displayMetrics);
        }
    }

    private static boolean isSameLocale(Locale locale, Locale locale2) {
        return UtilsBridge.equals(locale2.getLanguage(), locale.getLanguage()) && UtilsBridge.equals(locale2.getCountry(), locale.getCountry());
    }
}
