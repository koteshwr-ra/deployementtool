package org.greenrobot.eventbus.android;

import android.util.Log;
import java.util.logging.Level;
import org.apache.commons.lang3.StringUtils;
import org.greenrobot.eventbus.Logger;

public class AndroidLogger implements Logger {
    private static final boolean ANDROID_LOG_AVAILABLE;
    private final String tag;

    static {
        boolean z = false;
        try {
            if (Class.forName("android.util.Log") != null) {
                z = true;
            }
        } catch (ClassNotFoundException unused) {
        }
        ANDROID_LOG_AVAILABLE = z;
    }

    public static boolean isAndroidLogAvailable() {
        return ANDROID_LOG_AVAILABLE;
    }

    public AndroidLogger(String str) {
        this.tag = str;
    }

    public void log(Level level, String str) {
        if (level != Level.OFF) {
            Log.println(mapLevel(level), this.tag, str);
        }
    }

    public void log(Level level, String str, Throwable th) {
        if (level != Level.OFF) {
            int mapLevel = mapLevel(level);
            String str2 = this.tag;
            Log.println(mapLevel, str2, str + StringUtils.LF + Log.getStackTraceString(th));
        }
    }

    private int mapLevel(Level level) {
        int intValue = level.intValue();
        if (intValue < 800) {
            return intValue < 500 ? 2 : 3;
        }
        if (intValue < 900) {
            return 4;
        }
        return intValue < 1000 ? 5 : 6;
    }
}
