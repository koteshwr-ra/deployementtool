package org.apache.log4j.lf5;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;

public abstract class LogRecord implements Serializable {
    protected static long _seqCount;
    protected String _category = "Debug";
    protected LogLevel _level = LogLevel.INFO;
    protected String _location = "";
    protected String _message = "";
    protected long _millis = System.currentTimeMillis();
    protected String _ndc = "";
    protected long _sequenceNumber = getNextId();
    protected String _thread = Thread.currentThread().toString();
    protected Throwable _thrown;
    protected String _thrownStackTrace;

    public abstract boolean isSevereLevel();

    public LogLevel getLevel() {
        return this._level;
    }

    public void setLevel(LogLevel logLevel) {
        this._level = logLevel;
    }

    public boolean hasThrown() {
        String th;
        Throwable thrown = getThrown();
        if (thrown == null || (th = thrown.toString()) == null || th.trim().length() == 0) {
            return false;
        }
        return true;
    }

    public boolean isFatal() {
        return isSevereLevel() || hasThrown();
    }

    public String getCategory() {
        return this._category;
    }

    public void setCategory(String str) {
        this._category = str;
    }

    public String getMessage() {
        return this._message;
    }

    public void setMessage(String str) {
        this._message = str;
    }

    public long getSequenceNumber() {
        return this._sequenceNumber;
    }

    public void setSequenceNumber(long j) {
        this._sequenceNumber = j;
    }

    public long getMillis() {
        return this._millis;
    }

    public void setMillis(long j) {
        this._millis = j;
    }

    public String getThreadDescription() {
        return this._thread;
    }

    public void setThreadDescription(String str) {
        this._thread = str;
    }

    public String getThrownStackTrace() {
        return this._thrownStackTrace;
    }

    public void setThrownStackTrace(String str) {
        this._thrownStackTrace = str;
    }

    public Throwable getThrown() {
        return this._thrown;
    }

    public void setThrown(Throwable th) {
        if (th != null) {
            this._thrown = th;
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            th.printStackTrace(printWriter);
            printWriter.flush();
            this._thrownStackTrace = stringWriter.toString();
            try {
                printWriter.close();
                stringWriter.close();
            } catch (IOException unused) {
            }
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("LogRecord: [");
        stringBuffer2.append(this._level);
        stringBuffer2.append(", ");
        stringBuffer2.append(this._message);
        stringBuffer2.append("]");
        stringBuffer.append(stringBuffer2.toString());
        return stringBuffer.toString();
    }

    public String getNDC() {
        return this._ndc;
    }

    public void setNDC(String str) {
        this._ndc = str;
    }

    public String getLocation() {
        return this._location;
    }

    public void setLocation(String str) {
        this._location = str;
    }

    public static synchronized void resetSequenceNumber() {
        synchronized (LogRecord.class) {
            _seqCount = 0;
        }
    }

    protected static synchronized long getNextId() {
        long j;
        synchronized (LogRecord.class) {
            j = _seqCount + 1;
            _seqCount = j;
        }
        return j;
    }
}
