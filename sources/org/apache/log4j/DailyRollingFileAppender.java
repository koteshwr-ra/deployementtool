package org.apache.log4j;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.LoggingEvent;

public class DailyRollingFileAppender extends FileAppender {
    static final int HALF_DAY = 2;
    static final int TOP_OF_DAY = 3;
    static final int TOP_OF_HOUR = 1;
    static final int TOP_OF_MINUTE = 0;
    static final int TOP_OF_MONTH = 5;
    static final int TOP_OF_TROUBLE = -1;
    static final int TOP_OF_WEEK = 4;
    static final TimeZone gmtTimeZone = TimeZone.getTimeZone("GMT");
    int checkPeriod = -1;
    private String datePattern = "'.'yyyy-MM-dd";
    private long nextCheck = (System.currentTimeMillis() - 1);
    Date now = new Date();
    RollingCalendar rc = new RollingCalendar();
    private String scheduledFilename;
    SimpleDateFormat sdf;

    public DailyRollingFileAppender() {
    }

    public DailyRollingFileAppender(Layout layout, String str, String str2) throws IOException {
        super(layout, str, true);
        this.datePattern = str2;
        activateOptions();
    }

    public void setDatePattern(String str) {
        this.datePattern = str;
    }

    public String getDatePattern() {
        return this.datePattern;
    }

    public void activateOptions() {
        super.activateOptions();
        if (this.datePattern == null || this.fileName == null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Either File or DatePattern options are not set for appender [");
            stringBuffer.append(this.name);
            stringBuffer.append("].");
            LogLog.error(stringBuffer.toString());
            return;
        }
        this.now.setTime(System.currentTimeMillis());
        this.sdf = new SimpleDateFormat(this.datePattern);
        int computeCheckPeriod = computeCheckPeriod();
        printPeriodicity(computeCheckPeriod);
        this.rc.setType(computeCheckPeriod);
        File file = new File(this.fileName);
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append(this.fileName);
        stringBuffer2.append(this.sdf.format(new Date(file.lastModified())));
        this.scheduledFilename = stringBuffer2.toString();
    }

    /* access modifiers changed from: package-private */
    public void printPeriodicity(int i) {
        if (i == 0) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Appender [");
            stringBuffer.append(this.name);
            stringBuffer.append("] to be rolled every minute.");
            LogLog.debug(stringBuffer.toString());
        } else if (i == 1) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Appender [");
            stringBuffer2.append(this.name);
            stringBuffer2.append("] to be rolled on top of every hour.");
            LogLog.debug(stringBuffer2.toString());
        } else if (i == 2) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("Appender [");
            stringBuffer3.append(this.name);
            stringBuffer3.append("] to be rolled at midday and midnight.");
            LogLog.debug(stringBuffer3.toString());
        } else if (i == 3) {
            StringBuffer stringBuffer4 = new StringBuffer();
            stringBuffer4.append("Appender [");
            stringBuffer4.append(this.name);
            stringBuffer4.append("] to be rolled at midnight.");
            LogLog.debug(stringBuffer4.toString());
        } else if (i == 4) {
            StringBuffer stringBuffer5 = new StringBuffer();
            stringBuffer5.append("Appender [");
            stringBuffer5.append(this.name);
            stringBuffer5.append("] to be rolled at start of week.");
            LogLog.debug(stringBuffer5.toString());
        } else if (i != 5) {
            StringBuffer stringBuffer6 = new StringBuffer();
            stringBuffer6.append("Unknown periodicity for appender [");
            stringBuffer6.append(this.name);
            stringBuffer6.append("].");
            LogLog.warn(stringBuffer6.toString());
        } else {
            StringBuffer stringBuffer7 = new StringBuffer();
            stringBuffer7.append("Appender [");
            stringBuffer7.append(this.name);
            stringBuffer7.append("] to be rolled at start of every month.");
            LogLog.debug(stringBuffer7.toString());
        }
    }

    /* access modifiers changed from: package-private */
    public int computeCheckPeriod() {
        RollingCalendar rollingCalendar = new RollingCalendar(gmtTimeZone, Locale.ENGLISH);
        Date date = new Date(0);
        if (this.datePattern == null) {
            return -1;
        }
        for (int i = 0; i <= 5; i++) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.datePattern);
            simpleDateFormat.setTimeZone(gmtTimeZone);
            String format = simpleDateFormat.format(date);
            rollingCalendar.setType(i);
            String format2 = simpleDateFormat.format(new Date(rollingCalendar.getNextCheckMillis(date)));
            if (format != null && format2 != null && !format.equals(format2)) {
                return i;
            }
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public void rollOver() throws IOException {
        if (this.datePattern == null) {
            this.errorHandler.error("Missing DatePattern option in rollOver().");
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.fileName);
        stringBuffer.append(this.sdf.format(this.now));
        String stringBuffer2 = stringBuffer.toString();
        if (!this.scheduledFilename.equals(stringBuffer2)) {
            closeFile();
            File file = new File(this.scheduledFilename);
            if (file.exists()) {
                file.delete();
            }
            if (new File(this.fileName).renameTo(file)) {
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append(this.fileName);
                stringBuffer3.append(" -> ");
                stringBuffer3.append(this.scheduledFilename);
                LogLog.debug(stringBuffer3.toString());
            } else {
                StringBuffer stringBuffer4 = new StringBuffer();
                stringBuffer4.append("Failed to rename [");
                stringBuffer4.append(this.fileName);
                stringBuffer4.append("] to [");
                stringBuffer4.append(this.scheduledFilename);
                stringBuffer4.append("].");
                LogLog.error(stringBuffer4.toString());
            }
            try {
                setFile(this.fileName, false, this.bufferedIO, this.bufferSize);
            } catch (IOException unused) {
                ErrorHandler errorHandler = this.errorHandler;
                StringBuffer stringBuffer5 = new StringBuffer();
                stringBuffer5.append("setFile(");
                stringBuffer5.append(this.fileName);
                stringBuffer5.append(", false) call failed.");
                errorHandler.error(stringBuffer5.toString());
            }
            this.scheduledFilename = stringBuffer2;
        }
    }

    /* access modifiers changed from: protected */
    public void subAppend(LoggingEvent loggingEvent) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis >= this.nextCheck) {
            this.now.setTime(currentTimeMillis);
            this.nextCheck = this.rc.getNextCheckMillis(this.now);
            try {
                rollOver();
            } catch (IOException e) {
                LogLog.error("rollOver() failed.", e);
            }
        }
        super.subAppend(loggingEvent);
    }
}
