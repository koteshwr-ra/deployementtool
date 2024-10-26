package org.apache.log4j.lf5;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.spi.ThrowableInformation;

public class Log4JLogRecord extends LogRecord {
    public boolean isSevereLevel() {
        return LogLevel.ERROR.equals(getLevel()) || LogLevel.FATAL.equals(getLevel());
    }

    public void setThrownStackTrace(ThrowableInformation throwableInformation) {
        String[] throwableStrRep = throwableInformation.getThrowableStrRep();
        StringBuffer stringBuffer = new StringBuffer();
        for (String append : throwableStrRep) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(append);
            stringBuffer2.append(StringUtils.LF);
            stringBuffer.append(stringBuffer2.toString());
        }
        this._thrownStackTrace = stringBuffer.toString();
    }
}
