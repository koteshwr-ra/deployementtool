package io.realm.log;

import android.util.Log;
import java.util.Locale;
import javax.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;

public final class RealmLog {
    private static String REALM_JAVA_TAG = "REALM_JAVA";

    private static native void nativeAddLogger(RealmLogger realmLogger);

    private static native void nativeClearLoggers();

    static native void nativeCloseCoreLoggerBridge(long j);

    static native long nativeCreateCoreLoggerBridge(String str);

    private static native int nativeGetLogLevel();

    private static native void nativeLog(int i, String str, @Nullable Throwable th, @Nullable String str2);

    static native void nativeLogToCoreLoggerBridge(long j, int i, String str);

    private static native void nativeRegisterDefaultLogger();

    private static native void nativeRemoveLogger(RealmLogger realmLogger);

    private static native void nativeSetLogLevel(int i);

    public static void add(RealmLogger realmLogger) {
        if (realmLogger != null) {
            nativeAddLogger(realmLogger);
            return;
        }
        throw new IllegalArgumentException("A non-null logger has to be provided");
    }

    public static void setLevel(int i) {
        if (i < 1 || i > 8) {
            throw new IllegalArgumentException("Invalid log level: " + i);
        }
        nativeSetLogLevel(i);
    }

    public static int getLevel() {
        return nativeGetLogLevel();
    }

    public static boolean remove(RealmLogger realmLogger) {
        if (realmLogger != null) {
            nativeRemoveLogger(realmLogger);
            return true;
        }
        throw new IllegalArgumentException("A non-null logger has to be provided");
    }

    public static void clear() {
        nativeClearLoggers();
    }

    public static void registerDefaultLogger() {
        nativeRegisterDefaultLogger();
    }

    public static void trace(Throwable th) {
        trace(th, (String) null, new Object[0]);
    }

    public static void trace(String str, Object... objArr) {
        trace((Throwable) null, str, objArr);
    }

    public static void trace(@Nullable Throwable th, @Nullable String str, Object... objArr) {
        log(2, th, str, objArr);
    }

    public static void debug(@Nullable Throwable th) {
        debug(th, (String) null, new Object[0]);
    }

    public static void debug(String str, Object... objArr) {
        debug((Throwable) null, str, objArr);
    }

    public static void debug(@Nullable Throwable th, @Nullable String str, Object... objArr) {
        log(3, th, str, objArr);
    }

    public static void info(Throwable th) {
        info(th, (String) null, new Object[0]);
    }

    public static void info(String str, Object... objArr) {
        info((Throwable) null, str, objArr);
    }

    public static void info(@Nullable Throwable th, @Nullable String str, Object... objArr) {
        log(4, th, str, objArr);
    }

    public static void warn(Throwable th) {
        warn(th, (String) null, new Object[0]);
    }

    public static void warn(String str, Object... objArr) {
        warn((Throwable) null, str, objArr);
    }

    public static void warn(@Nullable Throwable th, @Nullable String str, Object... objArr) {
        log(5, th, str, objArr);
    }

    public static void error(Throwable th) {
        error(th, (String) null, new Object[0]);
    }

    public static void error(String str, Object... objArr) {
        error((Throwable) null, str, objArr);
    }

    public static void error(@Nullable Throwable th, @Nullable String str, Object... objArr) {
        log(6, th, str, objArr);
    }

    public static void fatal(Throwable th) {
        fatal(th, (String) null, new Object[0]);
    }

    public static void fatal(String str, Object... objArr) {
        fatal((Throwable) null, str, objArr);
    }

    public static void fatal(@Nullable Throwable th, @Nullable String str, Object... objArr) {
        log(7, th, str, objArr);
    }

    private static void log(int i, @Nullable Throwable th, @Nullable String str, @Nullable Object... objArr) {
        if (i >= getLevel()) {
            StringBuilder sb = new StringBuilder();
            if (!(str == null || objArr == null || objArr.length <= 0)) {
                str = String.format(Locale.US, str, objArr);
            }
            if (th != null) {
                sb.append(Log.getStackTraceString(th));
            }
            if (str != null) {
                if (th != null) {
                    sb.append(StringUtils.LF);
                }
                sb.append(str);
            }
            nativeLog(i, REALM_JAVA_TAG, th, sb.toString());
        }
    }
}
