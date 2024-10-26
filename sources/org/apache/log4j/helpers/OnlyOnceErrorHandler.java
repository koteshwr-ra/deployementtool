package org.apache.log4j.helpers;

import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.LoggingEvent;

public class OnlyOnceErrorHandler implements ErrorHandler {
    final String ERROR_PREFIX = "log4j error: ";
    final String WARN_PREFIX = "log4j warning: ";
    boolean firstTime = true;

    public void activateOptions() {
    }

    public void setAppender(Appender appender) {
    }

    public void setBackupAppender(Appender appender) {
    }

    public void setLogger(Logger logger) {
    }

    public void error(String str, Exception exc, int i) {
        error(str, exc, i, (LoggingEvent) null);
    }

    public void error(String str, Exception exc, int i, LoggingEvent loggingEvent) {
        if (this.firstTime) {
            LogLog.error(str, exc);
            this.firstTime = false;
        }
    }

    public void error(String str) {
        if (this.firstTime) {
            LogLog.error(str);
            this.firstTime = false;
        }
    }
}
