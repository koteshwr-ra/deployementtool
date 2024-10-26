package org.greenrobot.eventbus;

import android.os.Looper;
import java.io.PrintStream;
import java.util.logging.Level;
import org.greenrobot.eventbus.android.AndroidLogger;

public interface Logger {
    void log(Level level, String str);

    void log(Level level, String str, Throwable th);

    public static class JavaLogger implements Logger {
        protected final java.util.logging.Logger logger;

        public JavaLogger(String str) {
            this.logger = java.util.logging.Logger.getLogger(str);
        }

        public void log(Level level, String str) {
            this.logger.log(level, str);
        }

        public void log(Level level, String str, Throwable th) {
            this.logger.log(level, str, th);
        }
    }

    public static class SystemOutLogger implements Logger {
        public void log(Level level, String str) {
            PrintStream printStream = System.out;
            printStream.println("[" + level + "] " + str);
        }

        public void log(Level level, String str, Throwable th) {
            PrintStream printStream = System.out;
            printStream.println("[" + level + "] " + str);
            th.printStackTrace(System.out);
        }
    }

    public static class Default {
        public static Logger get() {
            if (!AndroidLogger.isAndroidLogAvailable() || getAndroidMainLooperOrNull() == null) {
                return new SystemOutLogger();
            }
            return new AndroidLogger("EventBus");
        }

        static Object getAndroidMainLooperOrNull() {
            try {
                return Looper.getMainLooper();
            } catch (RuntimeException unused) {
                return null;
            }
        }
    }
}
