package org.apache.log4j.varia;

import java.util.Vector;
import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.LoggingEvent;

public class FallbackErrorHandler implements ErrorHandler {
    Appender backup;
    Vector loggers;
    Appender primary;

    public void activateOptions() {
    }

    public void error(String str) {
    }

    public void setLogger(Logger logger) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("FB: Adding logger [");
        stringBuffer.append(logger.getName());
        stringBuffer.append("].");
        LogLog.debug(stringBuffer.toString());
        if (this.loggers == null) {
            this.loggers = new Vector();
        }
        this.loggers.addElement(logger);
    }

    public void error(String str, Exception exc, int i) {
        error(str, exc, i, (LoggingEvent) null);
    }

    public void error(String str, Exception exc, int i, LoggingEvent loggingEvent) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("FB: The following error reported: ");
        stringBuffer.append(str);
        LogLog.debug(stringBuffer.toString(), exc);
        LogLog.debug("FB: INITIATING FALLBACK PROCEDURE.");
        if (this.loggers != null) {
            for (int i2 = 0; i2 < this.loggers.size(); i2++) {
                Logger logger = (Logger) this.loggers.elementAt(i2);
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("FB: Searching for [");
                stringBuffer2.append(this.primary.getName());
                stringBuffer2.append("] in logger [");
                stringBuffer2.append(logger.getName());
                stringBuffer2.append("].");
                LogLog.debug(stringBuffer2.toString());
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("FB: Replacing [");
                stringBuffer3.append(this.primary.getName());
                stringBuffer3.append("] by [");
                stringBuffer3.append(this.backup.getName());
                stringBuffer3.append("] in logger [");
                stringBuffer3.append(logger.getName());
                stringBuffer3.append("].");
                LogLog.debug(stringBuffer3.toString());
                logger.removeAppender(this.primary);
                StringBuffer stringBuffer4 = new StringBuffer();
                stringBuffer4.append("FB: Adding appender [");
                stringBuffer4.append(this.backup.getName());
                stringBuffer4.append("] to logger ");
                stringBuffer4.append(logger.getName());
                LogLog.debug(stringBuffer4.toString());
                logger.addAppender(this.backup);
            }
        }
    }

    public void setAppender(Appender appender) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("FB: Setting primary appender to [");
        stringBuffer.append(appender.getName());
        stringBuffer.append("].");
        LogLog.debug(stringBuffer.toString());
        this.primary = appender;
    }

    public void setBackupAppender(Appender appender) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("FB: Setting backup appender to [");
        stringBuffer.append(appender.getName());
        stringBuffer.append("].");
        LogLog.debug(stringBuffer.toString());
        this.backup = appender;
    }
}
