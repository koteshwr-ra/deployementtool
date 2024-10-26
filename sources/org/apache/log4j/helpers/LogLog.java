package org.apache.log4j.helpers;

import java.io.PrintStream;

public class LogLog {
    public static final String CONFIG_DEBUG_KEY = "log4j.configDebug";
    public static final String DEBUG_KEY = "log4j.debug";
    private static final String ERR_PREFIX = "log4j:ERROR ";
    private static final String PREFIX = "log4j: ";
    private static final String WARN_PREFIX = "log4j:WARN ";
    protected static boolean debugEnabled = false;
    private static boolean quietMode = false;

    static {
        String systemProperty = OptionConverter.getSystemProperty(DEBUG_KEY, (String) null);
        if (systemProperty == null) {
            systemProperty = OptionConverter.getSystemProperty(CONFIG_DEBUG_KEY, (String) null);
        }
        if (systemProperty != null) {
            debugEnabled = OptionConverter.toBoolean(systemProperty, true);
        }
    }

    public static void setInternalDebugging(boolean z) {
        debugEnabled = z;
    }

    public static void debug(String str) {
        if (debugEnabled && !quietMode) {
            PrintStream printStream = System.out;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(PREFIX);
            stringBuffer.append(str);
            printStream.println(stringBuffer.toString());
        }
    }

    public static void debug(String str, Throwable th) {
        if (debugEnabled && !quietMode) {
            PrintStream printStream = System.out;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(PREFIX);
            stringBuffer.append(str);
            printStream.println(stringBuffer.toString());
            if (th != null) {
                th.printStackTrace(System.out);
            }
        }
    }

    public static void error(String str) {
        if (!quietMode) {
            PrintStream printStream = System.err;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(ERR_PREFIX);
            stringBuffer.append(str);
            printStream.println(stringBuffer.toString());
        }
    }

    public static void error(String str, Throwable th) {
        if (!quietMode) {
            PrintStream printStream = System.err;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(ERR_PREFIX);
            stringBuffer.append(str);
            printStream.println(stringBuffer.toString());
            if (th != null) {
                th.printStackTrace();
            }
        }
    }

    public static void setQuietMode(boolean z) {
        quietMode = z;
    }

    public static void warn(String str) {
        if (!quietMode) {
            PrintStream printStream = System.err;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(WARN_PREFIX);
            stringBuffer.append(str);
            printStream.println(stringBuffer.toString());
        }
    }

    public static void warn(String str, Throwable th) {
        if (!quietMode) {
            PrintStream printStream = System.err;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(WARN_PREFIX);
            stringBuffer.append(str);
            printStream.println(stringBuffer.toString());
            if (th != null) {
                th.printStackTrace();
            }
        }
    }
}
