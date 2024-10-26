package org.apache.log4j.nt;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.TTCCLayout;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

public class NTEventLogAppender extends AppenderSkeleton {
    private static final int DEBUG = Level.DEBUG.toInt();
    private static final int ERROR = Level.ERROR.toInt();
    private static final int FATAL = Level.FATAL.toInt();
    private static final int INFO = Level.INFO.toInt();
    private static final int WARN = Level.WARN.toInt();
    private int _handle;
    private String server;
    private String source;

    private native void deregisterEventSource(int i);

    private native int registerEventSource(String str, String str2);

    private native void reportEvent(int i, String str, int i2);

    public void close() {
    }

    public boolean requiresLayout() {
        return true;
    }

    static {
        System.loadLibrary("NTEventLogAppender");
    }

    public NTEventLogAppender() {
        this((String) null, (String) null, (Layout) null);
    }

    public NTEventLogAppender(String str) {
        this((String) null, str, (Layout) null);
    }

    public NTEventLogAppender(String str, String str2) {
        this(str, str2, (Layout) null);
    }

    public NTEventLogAppender(Layout layout) {
        this((String) null, (String) null, layout);
    }

    public NTEventLogAppender(String str, Layout layout) {
        this((String) null, str, layout);
    }

    public NTEventLogAppender(String str, String str2, Layout layout) {
        this._handle = 0;
        this.source = null;
        this.server = null;
        str2 = str2 == null ? "Log4j" : str2;
        if (layout == null) {
            this.layout = new TTCCLayout();
        } else {
            this.layout = layout;
        }
        try {
            this._handle = registerEventSource(str, str2);
        } catch (Exception e) {
            e.printStackTrace();
            this._handle = 0;
        }
    }

    public void activateOptions() {
        String str = this.source;
        if (str != null) {
            try {
                this._handle = registerEventSource(this.server, str);
            } catch (Exception e) {
                LogLog.error("Could not register event source.", e);
                this._handle = 0;
            }
        }
    }

    public void append(LoggingEvent loggingEvent) {
        String[] throwableStrRep;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.layout.format(loggingEvent));
        if (this.layout.ignoresThrowable() && (throwableStrRep = loggingEvent.getThrowableStrRep()) != null) {
            for (String append : throwableStrRep) {
                stringBuffer.append(append);
            }
        }
        reportEvent(this._handle, stringBuffer.toString(), loggingEvent.getLevel().toInt());
    }

    public void finalize() {
        deregisterEventSource(this._handle);
        this._handle = 0;
    }

    public void setSource(String str) {
        this.source = str.trim();
    }

    public String getSource() {
        return this.source;
    }
}
