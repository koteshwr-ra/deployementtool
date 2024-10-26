package com.tencent.mars.xlog;

import com.tencent.mars.xlog.Log;

public class Xlog implements Log.LogImp {
    public static final int ASSERT = 5;
    public static final int AppednerModeAsync = 0;
    public static final int AppednerModeSync = 1;
    public static final int COMPRESS_LEVEL1 = 1;
    public static final int COMPRESS_LEVEL2 = 2;
    public static final int COMPRESS_LEVEL3 = 3;
    public static final int COMPRESS_LEVEL4 = 4;
    public static final int COMPRESS_LEVEL5 = 5;
    public static final int COMPRESS_LEVEL6 = 6;
    public static final int COMPRESS_LEVEL7 = 7;
    public static final int COMPRESS_LEVEL8 = 8;
    public static final int COMPRESS_LEVEL9 = 9;
    public static final int DEBUG = 1;
    public static final int ERROR = 4;
    public static final int INFO = 2;
    public static final int VERBOSE = 0;
    public static final int WARN = 3;
    public static final int ZLIB_MODE = 0;
    public static final int ZSTD_MODE = 1;

    public static class XLogConfig {
        public int cachedays = 0;
        public String cachedir;
        public int compresslevel = 0;
        public int compressmode = 0;
        public boolean consoleLogOpen = true;
        public int level = 2;
        public String logdir;
        public long maxAliveTime = 0;
        public long maxFileSize = 0;
        public int mode = 0;
        public String nameprefix;
        public String pubkey = "";
    }

    public static native void appenderOpen(XLogConfig xLogConfig);

    private static String decryptTag(String str) {
        return str;
    }

    public static native void logWrite(XLoggerInfo xLoggerInfo, String str);

    public static native void logWrite2(int i, String str, String str2, String str3, int i2, int i3, long j, long j2, String str4);

    public static native void setAppenderMode(int i);

    public static native void setConsoleLogOpen(boolean z);

    public static native void setErrLogOpen(boolean z);

    public static native void setLogLevel(int i);

    public static native void setMaxAliveTime(long j);

    public static native void setMaxFileSize(long j);

    public native void appenderClose();

    public native void appenderFlush(boolean z);

    public native int getLogLevel();

    static class XLoggerInfo {
        public String filename;
        public String funcname;
        public int level;
        public int line;
        public long maintid;
        public long pid;
        public String tag;
        public long tid;

        XLoggerInfo() {
        }
    }

    public static void open(boolean z, int i, int i2, String str, String str2, String str3, String str4) {
        if (z) {
            System.loadLibrary("c++_shared");
            System.loadLibrary("marsxlog");
        }
        XLogConfig xLogConfig = new XLogConfig();
        xLogConfig.level = i;
        xLogConfig.mode = i2;
        xLogConfig.logdir = str2;
        xLogConfig.nameprefix = str3;
        xLogConfig.pubkey = str4;
        xLogConfig.compressmode = 0;
        xLogConfig.compresslevel = 0;
        xLogConfig.cachedir = str;
        xLogConfig.cachedays = 0;
        appenderOpen(xLogConfig);
    }

    public void logV(String str, String str2, String str3, int i, int i2, long j, long j2, String str4) {
        logWrite2(0, decryptTag(str), str2, str3, i, i2, j, j2, str4);
    }

    public void logD(String str, String str2, String str3, int i, int i2, long j, long j2, String str4) {
        logWrite2(1, decryptTag(str), str2, str3, i, i2, j, j2, str4);
    }

    public void logI(String str, String str2, String str3, int i, int i2, long j, long j2, String str4) {
        logWrite2(2, decryptTag(str), str2, str3, i, i2, j, j2, str4);
    }

    public void logW(String str, String str2, String str3, int i, int i2, long j, long j2, String str4) {
        logWrite2(3, decryptTag(str), str2, str3, i, i2, j, j2, str4);
    }

    public void logE(String str, String str2, String str3, int i, int i2, long j, long j2, String str4) {
        logWrite2(4, decryptTag(str), str2, str3, i, i2, j, j2, str4);
    }

    public void logF(String str, String str2, String str3, int i, int i2, long j, long j2, String str4) {
        logWrite2(5, decryptTag(str), str2, str3, i, i2, j, j2, str4);
    }
}
