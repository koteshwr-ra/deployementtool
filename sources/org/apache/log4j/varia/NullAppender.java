package org.apache.log4j.varia;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

public class NullAppender extends AppenderSkeleton {
    private static NullAppender instance = new NullAppender();

    public void activateOptions() {
    }

    /* access modifiers changed from: protected */
    public void append(LoggingEvent loggingEvent) {
    }

    public void close() {
    }

    public void doAppend(LoggingEvent loggingEvent) {
    }

    public boolean requiresLayout() {
        return false;
    }

    public NullAppender getInstance() {
        return instance;
    }
}
