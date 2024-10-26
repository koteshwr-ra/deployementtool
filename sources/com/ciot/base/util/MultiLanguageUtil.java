package com.ciot.base.util;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.util.DisplayMetrics;
import android.util.Log;
import java.util.Locale;

public class MultiLanguageUtil {
    private static final String TAG = "MultiLanguageUtil";
    private static MultiLanguageUtil instance;
    private Context mContext;

    public interface LanguageType {
        public static final int LANGUAGE_ARABIC = 6;
        public static final int LANGUAGE_CHINESE_SIMPLIFIED = 0;
        public static final int LANGUAGE_CHINESE_TRADITIONAL = 1;
        public static final int LANGUAGE_EN = 2;
        public static final int LANGUAGE_GERMAN = 7;
        public static final int LANGUAGE_HEBREW = 9;
        public static final int LANGUAGE_INDONESIAN = 8;
        public static final int LANGUAGE_JAPANESE = 4;
        public static final int LANGUAGE_KOREAN = 5;
        public static final int LANGUAGE_THAI = 3;
        public static final int LANGUAGE_TURKISH = 10;
    }

    public static boolean isWavSpeak(int i) {
        return (i == 0 || i == 1 || i == 2) ? false : true;
    }

    public static void init(Context context) {
        if (instance == null) {
            synchronized (MultiLanguageUtil.class) {
                if (instance == null) {
                    instance = new MultiLanguageUtil(context);
                }
            }
        }
    }

    public static MultiLanguageUtil getInstance() {
        MultiLanguageUtil multiLanguageUtil = instance;
        if (multiLanguageUtil != null) {
            return multiLanguageUtil;
        }
        throw new IllegalStateException("You must be init MultiLanguageUtil first");
    }

    private MultiLanguageUtil(Context context) {
        this.mContext = context;
    }

    public void setConfiguration() {
        Locale languageLocale = getLanguageLocale();
        Configuration configuration = this.mContext.getResources().getConfiguration();
        if (Build.VERSION.SDK_INT >= 17) {
            configuration.setLocale(languageLocale);
        } else {
            configuration.locale = languageLocale;
        }
        Resources resources = this.mContext.getResources();
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

    private Locale getLanguageLocale() {
        return getLanguageLocale(getLanguageType());
    }

    public static Locale getLanguageLocale(int i) {
        if (i == 2) {
            return Locale.ENGLISH;
        }
        if (i == 0) {
            return Locale.SIMPLIFIED_CHINESE;
        }
        if (i == 1) {
            return Locale.TRADITIONAL_CHINESE;
        }
        if (i == 3) {
            return new Locale("th", "TH");
        }
        if (i == 4) {
            return Locale.JAPAN;
        }
        if (i == 5) {
            return Locale.KOREA;
        }
        if (i == 6) {
            return new Locale("ara");
        }
        if (i == 7) {
            return Locale.GERMAN;
        }
        if (i == 8) {
            return new Locale("in");
        }
        if (i == 9) {
            return new Locale("he", "rIL");
        }
        if (i == 10) {
            return new Locale("tr");
        }
        getSystemLanguage(getSysLocale());
        Log.e(TAG, "getLanguageLocale" + i + i);
        return Locale.SIMPLIFIED_CHINESE;
    }

    public static String getSystemLanguage(Locale locale) {
        return locale.getLanguage() + "_" + locale.getCountry();
    }

    public static Locale getSysLocale() {
        if (Build.VERSION.SDK_INT >= 24) {
            return LocaleList.getDefault().get(0);
        }
        return Locale.getDefault();
    }

    public void updateLanguage(int i) {
        setLanguageSpValue(i);
        getInstance().setConfiguration();
    }

    private void setLanguageSpValue(int i) {
        AppSpUtil.getInstance().setLanguage(i);
    }

    public int getLanguageType() {
        int language = AppSpUtil.getInstance().getLanguage();
        Log.e(TAG, "getLanguageType" + language);
        return language;
    }

    public static Context attachBaseContext(Context context) {
        if (Build.VERSION.SDK_INT >= 24) {
            return createConfigurationResources(context);
        }
        getInstance().setConfiguration();
        return context;
    }

    private static Context createConfigurationResources(Context context) {
        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(getInstance().getLanguageLocale());
        return context.createConfigurationContext(configuration);
    }

    public static String getCNStrByResId(int i) {
        return getLocaleStringResource(0, i);
    }

    public static String getLocaleStringResource(int i, int i2) {
        Context context = ContextUtil.getContext();
        Locale languageLocale = getLanguageLocale(i);
        String str = "";
        try {
            if (Build.VERSION.SDK_INT >= 17) {
                Configuration configuration = new Configuration(context.getResources().getConfiguration());
                configuration.setLocale(languageLocale);
                return context.createConfigurationContext(configuration).getText(i2).toString();
            }
            Resources resources = context.getResources();
            Configuration configuration2 = resources.getConfiguration();
            Locale locale = configuration2.locale;
            configuration2.locale = languageLocale;
            resources.updateConfiguration(configuration2, (DisplayMetrics) null);
            str = resources.getString(i2);
            configuration2.locale = locale;
            resources.updateConfiguration(configuration2, (DisplayMetrics) null);
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isRtlLanguage() {
        return 6 == getLanguageType();
    }
}
