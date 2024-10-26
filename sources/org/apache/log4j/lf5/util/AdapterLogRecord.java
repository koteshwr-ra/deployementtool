package org.apache.log4j.lf5.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import org.apache.log4j.lf5.LogLevel;
import org.apache.log4j.lf5.LogRecord;

public class AdapterLogRecord extends LogRecord {
    private static PrintWriter pw = new PrintWriter(sw);
    private static LogLevel severeLevel;
    private static StringWriter sw = new StringWriter();

    public void setCategory(String str) {
        super.setCategory(str);
        super.setLocation(getLocationInfo(str));
    }

    public boolean isSevereLevel() {
        LogLevel logLevel = severeLevel;
        if (logLevel == null) {
            return false;
        }
        return logLevel.equals(getLevel());
    }

    public static void setSevereLevel(LogLevel logLevel) {
        severeLevel = logLevel;
    }

    public static LogLevel getSevereLevel() {
        return severeLevel;
    }

    /* access modifiers changed from: protected */
    public String getLocationInfo(String str) {
        return parseLine(stackTraceToString(new Throwable()), str);
    }

    /* access modifiers changed from: protected */
    public String stackTraceToString(Throwable th) {
        String stringWriter;
        synchronized (sw) {
            th.printStackTrace(pw);
            stringWriter = sw.toString();
            sw.getBuffer().setLength(0);
        }
        return stringWriter;
    }

    /* access modifiers changed from: protected */
    public String parseLine(String str, String str2) {
        int indexOf = str.indexOf(str2);
        if (indexOf == -1) {
            return null;
        }
        String substring = str.substring(indexOf);
        return substring.substring(0, substring.indexOf(")") + 1);
    }
}
