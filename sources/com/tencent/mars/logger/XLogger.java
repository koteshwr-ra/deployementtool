package com.tencent.mars.logger;

import com.tencent.mars.xlog.Log;
import com.tencent.mars.xlog.Xlog;

public final class XLogger {
    private static final String TAG = "DEFAULT";
    private static Printer printer = new LoggerPrinter();

    private XLogger() {
    }

    public static void printer(Printer printer2) {
        printer = (Printer) Utils.checkNotNull(printer2);
    }

    public static void addLogAdapter(LogAdapter logAdapter) {
        printer.addAdapter((LogAdapter) Utils.checkNotNull(logAdapter));
    }

    public static void clearLogAdapters() {
        printer.clearLogAdapters();
    }

    public static void v(String str) {
        printer.v(TAG, str, new Object[0]);
    }

    public static void i(String str) {
        printer.i(TAG, str, new Object[0]);
    }

    public static void d(String str) {
        printer.d(TAG, str, new Object[0]);
    }

    public static void w(String str) {
        printer.w(TAG, str, new Object[0]);
    }

    public static void e(String str) {
        printer.e(TAG, str, new Object[0]);
    }

    public static void wtf(String str) {
        printer.wtf(TAG, str, new Object[0]);
    }

    public static void v(String str, String str2, Object... objArr) {
        printer.v(str, str2, objArr);
    }

    public static void i(String str, String str2, Object... objArr) {
        printer.i(str, str2, objArr);
    }

    public static void d(String str, String str2, Object... objArr) {
        printer.d(str, str2, objArr);
    }

    public static void w(String str, String str2, Object... objArr) {
        printer.w(str, str2, objArr);
    }

    public static void e(String str, String str2, Object... objArr) {
        printer.e(str, (Throwable) null, str2, objArr);
    }

    public static void e(String str, Throwable th, String str2, Object... objArr) {
        printer.e(str, th, str2, objArr);
    }

    public static void wtf(String str, String str2, Object... objArr) {
        printer.wtf(str, str2, objArr);
    }

    public static void json(String str, String str2) {
        printer.json(str, str2);
    }

    public static void xml(String str, String str2) {
        printer.xml(str, str2);
    }

    public static void printErrStackTrace(String str, Throwable th, String str2, Object... objArr) {
        Log.printErrStackTrace(str, th, str2, objArr);
    }

    public static void appenderClose() {
        Log.appenderClose();
    }

    public static void appenderFlush(boolean z) {
        Log.appenderFlush(z);
    }

    public static class Builder {
        private int appednerMode = 0;
        private int cacheDays = 0;
        private String cacheDir = "";
        private int compressLevel = 0;
        private int compressMode = 0;
        private boolean consoleLogOpen = true;
        private int level = 1;
        private String logDir = LoggerConfig.LOG_DIR;
        private long maxAliveTime = LoggerConfig.MAX_ALIVE_TIME;
        private long maxFileSize = 52428800;
        private String namePrefix = LoggerConfig.NAME_PREFIX;
        private String pubKey = LoggerConfig.PUB_KEY;

        public static Builder create() {
            return new Builder();
        }

        public Builder setLevel(int i) {
            this.level = i;
            return this;
        }

        public Builder setAppednerMode(int i) {
            this.appednerMode = i;
            return this;
        }

        public Builder setLogDir(String str) {
            this.logDir = str;
            return this;
        }

        public Builder setNamePrefix(String str) {
            this.namePrefix = str;
            return this;
        }

        public Builder setPubKey(String str) {
            this.pubKey = str;
            return this;
        }

        public Builder setCompressMode(int i) {
            this.compressMode = i;
            return this;
        }

        public Builder setCompressLevel(int i) {
            this.compressLevel = i;
            return this;
        }

        public Builder setCacheDir(String str) {
            this.cacheDir = str;
            return this;
        }

        public Builder setCacheDays(int i) {
            this.cacheDays = i;
            return this;
        }

        public Builder setMaxFileSize(long j) {
            this.maxFileSize = j;
            return this;
        }

        public Builder setMaxAliveTime(long j) {
            this.maxAliveTime = j;
            return this;
        }

        public Builder setConsoleLogOpen(boolean z) {
            this.consoleLogOpen = z;
            return this;
        }

        public Xlog.XLogConfig build() {
            Xlog.XLogConfig xLogConfig = new Xlog.XLogConfig();
            xLogConfig.mode = this.appednerMode;
            xLogConfig.logdir = this.logDir;
            xLogConfig.nameprefix = this.namePrefix;
            xLogConfig.pubkey = this.pubKey;
            xLogConfig.compressmode = this.compressMode;
            xLogConfig.compresslevel = this.compressLevel;
            xLogConfig.cachedir = this.cacheDir;
            xLogConfig.cachedays = this.cacheDays;
            xLogConfig.level = this.level;
            xLogConfig.maxFileSize = this.maxFileSize;
            xLogConfig.maxAliveTime = this.maxAliveTime;
            xLogConfig.consoleLogOpen = this.consoleLogOpen;
            return xLogConfig;
        }
    }
}
