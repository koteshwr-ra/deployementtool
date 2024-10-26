package com.ciot.base.util;

import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class LogPlus {
    private static int sCurrentLogLevel = 3;
    private static String sPrefix;

    @Retention(RetentionPolicy.SOURCE)
    public @interface LogLevel {
    }

    public static void init(String str, int i) {
        if (str != null) {
            String trim = str.trim();
            if (trim.length() > 0) {
                sPrefix = trim;
            }
        }
        sCurrentLogLevel = i;
    }

    public static void v(String str, String str2, Throwable th) {
        log(2, str, str2, th);
    }

    public static void v(String str, String str2) {
        log(2, str, str2, (Throwable) null);
    }

    public static void v(String str, Throwable th) {
        log(2, (String) null, str, th);
    }

    public static void v(String str) {
        log(2, (String) null, str, (Throwable) null);
    }

    public static void d(String str, String str2, Throwable th) {
        log(3, str, str2, th);
    }

    public static void d(String str, String str2) {
        log(3, str, str2, (Throwable) null);
    }

    public static void d(String str, Throwable th) {
        log(3, (String) null, str, th);
    }

    public static void d(String str) {
        log(3, (String) null, str, (Throwable) null);
    }

    public static void i(String str, String str2, Throwable th) {
        log(4, str, str2, th);
    }

    public static void i(String str, String str2) {
        log(4, str, str2, (Throwable) null);
    }

    public static void i(String str, Throwable th) {
        log(4, (String) null, str, th);
    }

    public static void i(String str) {
        log(4, (String) null, str, (Throwable) null);
    }

    public static void w(String str, String str2, Throwable th) {
        log(5, str, str2, th);
    }

    public static void w(String str, String str2) {
        log(5, str, str2, (Throwable) null);
    }

    public static void w(String str, Throwable th) {
        log(5, (String) null, str, th);
    }

    public static void w(String str) {
        log(5, (String) null, str, (Throwable) null);
    }

    public static void e(String str, String str2, Throwable th) {
        log(6, str, str2, th);
    }

    public static void e(String str, String str2) {
        log(6, str, str2, (Throwable) null);
    }

    public static void e(String str, Throwable th) {
        log(6, (String) null, str, th);
    }

    public static void e(String str) {
        log(6, (String) null, str, (Throwable) null);
    }

    private static void log(int i, String str, String str2, Throwable th) {
        if (i >= sCurrentLogLevel) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[4];
            String fileName = stackTraceElement.getFileName();
            int lineNumber = stackTraceElement.getLineNumber();
            String methodName = stackTraceElement.getMethodName();
            StringBuilder sb = new StringBuilder();
            sb.append(methodName);
            sb.append('(');
            sb.append(fileName);
            sb.append(':');
            sb.append(lineNumber);
            sb.append(')');
            sb.append(str2);
            String sb2 = sb.toString();
            sb.delete(0, sb.length());
            String str3 = sPrefix;
            if (str3 != null) {
                sb.append(str3);
                sb.append('_');
            }
            if (str == null || str.length() == 0 || str.trim().length() == 0) {
                String className = stackTraceElement.getClassName();
                int lastIndexOf = className.lastIndexOf(46) + 1;
                if (i < 5) {
                    sb.append(className, lastIndexOf, className.length());
                } else {
                    sb.append("NAVIGATION_TAG");
                }
            } else {
                sb.append(str);
            }
            String sb3 = sb.toString();
            if (th == null) {
                if (i == 2) {
                    Log.v(sb3, sb2);
                } else if (i == 3) {
                    Log.d(sb3, sb2);
                } else if (i == 4) {
                    Log.i(sb3, sb2);
                } else if (i == 5) {
                    Log.w(sb3, sb2);
                } else if (i == 6) {
                    Log.e(sb3, sb2);
                }
            } else if (i == 2) {
                Log.v(sb3, sb2, th);
            } else if (i == 3) {
                Log.d(sb3, sb2, th);
            } else if (i == 4) {
                Log.i(sb3, sb2, th);
            } else if (i == 5) {
                Log.w(sb3, sb2, th);
            } else if (i == 6) {
                Log.e(sb3, sb2, th);
            }
            MyLogUtils.print2File(sb3, sb2);
        }
    }
}
