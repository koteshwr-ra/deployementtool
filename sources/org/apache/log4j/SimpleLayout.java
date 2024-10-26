package org.apache.log4j;

import org.apache.log4j.spi.LoggingEvent;

public class SimpleLayout extends Layout {
    StringBuffer sbuf = new StringBuffer(128);

    public void activateOptions() {
    }

    public boolean ignoresThrowable() {
        return true;
    }

    public String format(LoggingEvent loggingEvent) {
        this.sbuf.setLength(0);
        this.sbuf.append(loggingEvent.getLevel().toString());
        this.sbuf.append(" - ");
        this.sbuf.append(loggingEvent.getRenderedMessage());
        this.sbuf.append(Layout.LINE_SEP);
        return this.sbuf.toString();
    }
}
