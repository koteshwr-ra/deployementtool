package org.apache.log4j.net;

import java.io.PrintStream;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.helpers.SyslogQuietWriter;
import org.apache.log4j.helpers.SyslogWriter;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.mina.proxy.handlers.http.HttpProxyConstants;

public class SyslogAppender extends AppenderSkeleton {
    protected static final int FACILITY_OI = 1;
    public static final int LOG_AUTH = 32;
    public static final int LOG_AUTHPRIV = 80;
    public static final int LOG_CRON = 72;
    public static final int LOG_DAEMON = 24;
    public static final int LOG_FTP = 88;
    public static final int LOG_KERN = 0;
    public static final int LOG_LOCAL0 = 128;
    public static final int LOG_LOCAL1 = 136;
    public static final int LOG_LOCAL2 = 144;
    public static final int LOG_LOCAL3 = 152;
    public static final int LOG_LOCAL4 = 160;
    public static final int LOG_LOCAL5 = 168;
    public static final int LOG_LOCAL6 = 176;
    public static final int LOG_LOCAL7 = 184;
    public static final int LOG_LPR = 48;
    public static final int LOG_MAIL = 16;
    public static final int LOG_NEWS = 56;
    public static final int LOG_SYSLOG = 40;
    public static final int LOG_USER = 8;
    public static final int LOG_UUCP = 64;
    protected static final int SYSLOG_HOST_OI = 0;
    static final String TAB = "    ";
    boolean facilityPrinting;
    String facilityStr;
    SyslogQuietWriter sqw;
    int syslogFacility;
    String syslogHost;

    public static String getFacilityString(int i) {
        switch (i) {
            case 0:
                return "kern";
            case 8:
                return "user";
            case 16:
                return "mail";
            case 24:
                return "daemon";
            case 32:
                return "auth";
            case 40:
                return "syslog";
            case 48:
                return "lpr";
            case 56:
                return "news";
            case 64:
                return "uucp";
            case 72:
                return "cron";
            case 80:
                return "authpriv";
            case 88:
                return "ftp";
            case 128:
                return "local0";
            case LOG_LOCAL1 /*136*/:
                return "local1";
            case 144:
                return "local2";
            case LOG_LOCAL3 /*152*/:
                return "local3";
            case 160:
                return "local4";
            case 168:
                return "local5";
            case 176:
                return "local6";
            case 184:
                return "local7";
            default:
                return null;
        }
    }

    public void activateOptions() {
    }

    public boolean requiresLayout() {
        return true;
    }

    public SyslogAppender() {
        this.syslogFacility = 8;
        this.facilityPrinting = false;
        initSyslogFacilityStr();
    }

    public SyslogAppender(Layout layout, int i) {
        this.syslogFacility = 8;
        this.facilityPrinting = false;
        this.layout = layout;
        this.syslogFacility = i;
        initSyslogFacilityStr();
    }

    public SyslogAppender(Layout layout, String str, int i) {
        this(layout, i);
        setSyslogHost(str);
    }

    public synchronized void close() {
        this.closed = true;
        this.sqw = null;
    }

    private void initSyslogFacilityStr() {
        String facilityString = getFacilityString(this.syslogFacility);
        this.facilityStr = facilityString;
        if (facilityString == null) {
            PrintStream printStream = System.err;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("\"");
            stringBuffer.append(this.syslogFacility);
            stringBuffer.append("\" is an unknown syslog facility. Defaulting to \"USER\".");
            printStream.println(stringBuffer.toString());
            this.syslogFacility = 8;
            this.facilityStr = "user:";
            return;
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append(this.facilityStr);
        stringBuffer2.append(":");
        this.facilityStr = stringBuffer2.toString();
    }

    public static int getFacility(String str) {
        if (str != null) {
            str = str.trim();
        }
        if ("KERN".equalsIgnoreCase(str)) {
            return 0;
        }
        if (HttpProxyConstants.USER_PROPERTY.equalsIgnoreCase(str)) {
            return 8;
        }
        if ("MAIL".equalsIgnoreCase(str)) {
            return 16;
        }
        if ("DAEMON".equalsIgnoreCase(str)) {
            return 24;
        }
        if ("AUTH".equalsIgnoreCase(str)) {
            return 32;
        }
        if ("SYSLOG".equalsIgnoreCase(str)) {
            return 40;
        }
        if ("LPR".equalsIgnoreCase(str)) {
            return 48;
        }
        if ("NEWS".equalsIgnoreCase(str)) {
            return 56;
        }
        if ("UUCP".equalsIgnoreCase(str)) {
            return 64;
        }
        if ("CRON".equalsIgnoreCase(str)) {
            return 72;
        }
        if ("AUTHPRIV".equalsIgnoreCase(str)) {
            return 80;
        }
        if ("FTP".equalsIgnoreCase(str)) {
            return 88;
        }
        if ("LOCAL0".equalsIgnoreCase(str)) {
            return 128;
        }
        if ("LOCAL1".equalsIgnoreCase(str)) {
            return LOG_LOCAL1;
        }
        if ("LOCAL2".equalsIgnoreCase(str)) {
            return 144;
        }
        if ("LOCAL3".equalsIgnoreCase(str)) {
            return LOG_LOCAL3;
        }
        if ("LOCAL4".equalsIgnoreCase(str)) {
            return 160;
        }
        if ("LOCAL5".equalsIgnoreCase(str)) {
            return 168;
        }
        if ("LOCAL6".equalsIgnoreCase(str)) {
            return 176;
        }
        return "LOCAL7".equalsIgnoreCase(str) ? 184 : -1;
    }

    public void append(LoggingEvent loggingEvent) {
        String[] throwableStrRep;
        int length;
        if (isAsSevereAsThreshold(loggingEvent.getLevel())) {
            if (this.sqw == null) {
                ErrorHandler errorHandler = this.errorHandler;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("No syslog host is set for SyslogAppedender named \"");
                stringBuffer.append(this.name);
                stringBuffer.append("\".");
                errorHandler.error(stringBuffer.toString());
                return;
            }
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(this.facilityPrinting ? this.facilityStr : "");
            stringBuffer2.append(this.layout.format(loggingEvent));
            String stringBuffer3 = stringBuffer2.toString();
            this.sqw.setLevel(loggingEvent.getLevel().getSyslogEquivalent());
            this.sqw.write(stringBuffer3);
            if (this.layout.ignoresThrowable() && (throwableStrRep = loggingEvent.getThrowableStrRep()) != null && (length = throwableStrRep.length) > 0) {
                this.sqw.write(throwableStrRep[0]);
                for (int i = 1; i < length; i++) {
                    SyslogQuietWriter syslogQuietWriter = this.sqw;
                    StringBuffer stringBuffer4 = new StringBuffer();
                    stringBuffer4.append(TAB);
                    stringBuffer4.append(throwableStrRep[i].substring(1));
                    syslogQuietWriter.write(stringBuffer4.toString());
                }
            }
        }
    }

    public void setSyslogHost(String str) {
        this.sqw = new SyslogQuietWriter(new SyslogWriter(str), this.syslogFacility, this.errorHandler);
        this.syslogHost = str;
    }

    public String getSyslogHost() {
        return this.syslogHost;
    }

    public void setFacility(String str) {
        if (str != null) {
            int facility = getFacility(str);
            this.syslogFacility = facility;
            if (facility == -1) {
                PrintStream printStream = System.err;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("[");
                stringBuffer.append(str);
                stringBuffer.append("] is an unknown syslog facility. Defaulting to [USER].");
                printStream.println(stringBuffer.toString());
                this.syslogFacility = 8;
            }
            initSyslogFacilityStr();
            SyslogQuietWriter syslogQuietWriter = this.sqw;
            if (syslogQuietWriter != null) {
                syslogQuietWriter.setSyslogFacility(this.syslogFacility);
            }
        }
    }

    public String getFacility() {
        return getFacilityString(this.syslogFacility);
    }

    public void setFacilityPrinting(boolean z) {
        this.facilityPrinting = z;
    }

    public boolean getFacilityPrinting() {
        return this.facilityPrinting;
    }
}
