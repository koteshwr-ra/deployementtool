package org.apache.log4j;

import java.util.TimeZone;
import org.apache.log4j.helpers.DateLayout;
import org.apache.log4j.spi.LoggingEvent;

public class TTCCLayout extends DateLayout {
    protected final StringBuffer buf = new StringBuffer(256);
    private boolean categoryPrefixing = true;
    private boolean contextPrinting = true;
    private boolean threadPrinting = true;

    public boolean ignoresThrowable() {
        return true;
    }

    public TTCCLayout() {
        setDateFormat(DateLayout.RELATIVE_TIME_DATE_FORMAT, (TimeZone) null);
    }

    public TTCCLayout(String str) {
        setDateFormat(str);
    }

    public void setThreadPrinting(boolean z) {
        this.threadPrinting = z;
    }

    public boolean getThreadPrinting() {
        return this.threadPrinting;
    }

    public void setCategoryPrefixing(boolean z) {
        this.categoryPrefixing = z;
    }

    public boolean getCategoryPrefixing() {
        return this.categoryPrefixing;
    }

    public void setContextPrinting(boolean z) {
        this.contextPrinting = z;
    }

    public boolean getContextPrinting() {
        return this.contextPrinting;
    }

    public String format(LoggingEvent loggingEvent) {
        String ndc;
        this.buf.setLength(0);
        dateFormat(this.buf, loggingEvent);
        if (this.threadPrinting) {
            this.buf.append('[');
            this.buf.append(loggingEvent.getThreadName());
            this.buf.append("] ");
        }
        this.buf.append(loggingEvent.getLevel().toString());
        this.buf.append(' ');
        if (this.categoryPrefixing) {
            this.buf.append(loggingEvent.getLoggerName());
            this.buf.append(' ');
        }
        if (this.contextPrinting && (ndc = loggingEvent.getNDC()) != null) {
            this.buf.append(ndc);
            this.buf.append(' ');
        }
        this.buf.append("- ");
        this.buf.append(loggingEvent.getRenderedMessage());
        this.buf.append(Layout.LINE_SEP);
        return this.buf.toString();
    }
}
