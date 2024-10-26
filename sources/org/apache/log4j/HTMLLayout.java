package org.apache.log4j;

import java.util.Date;
import org.apache.log4j.helpers.Transform;
import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;

public class HTMLLayout extends Layout {
    public static final String LOCATION_INFO_OPTION = "LocationInfo";
    public static final String TITLE_OPTION = "Title";
    static String TRACE_PREFIX = "<br>&nbsp;&nbsp;&nbsp;&nbsp;";
    protected final int BUF_SIZE = 256;
    protected final int MAX_CAPACITY = 1024;
    boolean locationInfo = false;
    private StringBuffer sbuf = new StringBuffer(256);
    String title = "Log4J Log Messages";

    public void activateOptions() {
    }

    public String getContentType() {
        return "text/html";
    }

    public boolean ignoresThrowable() {
        return false;
    }

    public void setLocationInfo(boolean z) {
        this.locationInfo = z;
    }

    public boolean getLocationInfo() {
        return this.locationInfo;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getTitle() {
        return this.title;
    }

    public String format(LoggingEvent loggingEvent) {
        if (this.sbuf.capacity() > 1024) {
            this.sbuf = new StringBuffer(256);
        } else {
            this.sbuf.setLength(0);
        }
        StringBuffer stringBuffer = this.sbuf;
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append(Layout.LINE_SEP);
        stringBuffer2.append("<tr>");
        stringBuffer2.append(Layout.LINE_SEP);
        stringBuffer.append(stringBuffer2.toString());
        this.sbuf.append("<td>");
        this.sbuf.append(loggingEvent.timeStamp - LoggingEvent.getStartTime());
        StringBuffer stringBuffer3 = this.sbuf;
        StringBuffer stringBuffer4 = new StringBuffer();
        stringBuffer4.append("</td>");
        stringBuffer4.append(Layout.LINE_SEP);
        stringBuffer3.append(stringBuffer4.toString());
        StringBuffer stringBuffer5 = this.sbuf;
        StringBuffer stringBuffer6 = new StringBuffer();
        stringBuffer6.append("<td title=\"");
        stringBuffer6.append(loggingEvent.getThreadName());
        stringBuffer6.append(" thread\">");
        stringBuffer5.append(stringBuffer6.toString());
        this.sbuf.append(Transform.escapeTags(loggingEvent.getThreadName()));
        StringBuffer stringBuffer7 = this.sbuf;
        StringBuffer stringBuffer8 = new StringBuffer();
        stringBuffer8.append("</td>");
        stringBuffer8.append(Layout.LINE_SEP);
        stringBuffer7.append(stringBuffer8.toString());
        this.sbuf.append("<td title=\"Level\">");
        if (loggingEvent.getLevel().equals(Level.DEBUG)) {
            this.sbuf.append("<font color=\"#339933\">");
            this.sbuf.append(loggingEvent.getLevel());
            this.sbuf.append("</font>");
        } else if (loggingEvent.getLevel().isGreaterOrEqual(Level.WARN)) {
            this.sbuf.append("<font color=\"#993300\"><strong>");
            this.sbuf.append(loggingEvent.getLevel());
            this.sbuf.append("</strong></font>");
        } else {
            this.sbuf.append(loggingEvent.getLevel());
        }
        StringBuffer stringBuffer9 = this.sbuf;
        StringBuffer stringBuffer10 = new StringBuffer();
        stringBuffer10.append("</td>");
        stringBuffer10.append(Layout.LINE_SEP);
        stringBuffer9.append(stringBuffer10.toString());
        StringBuffer stringBuffer11 = this.sbuf;
        StringBuffer stringBuffer12 = new StringBuffer();
        stringBuffer12.append("<td title=\"");
        stringBuffer12.append(loggingEvent.getLoggerName());
        stringBuffer12.append(" category\">");
        stringBuffer11.append(stringBuffer12.toString());
        this.sbuf.append(Transform.escapeTags(loggingEvent.getLoggerName()));
        StringBuffer stringBuffer13 = this.sbuf;
        StringBuffer stringBuffer14 = new StringBuffer();
        stringBuffer14.append("</td>");
        stringBuffer14.append(Layout.LINE_SEP);
        stringBuffer13.append(stringBuffer14.toString());
        if (this.locationInfo) {
            LocationInfo locationInformation = loggingEvent.getLocationInformation();
            this.sbuf.append("<td>");
            this.sbuf.append(Transform.escapeTags(locationInformation.getFileName()));
            this.sbuf.append(':');
            this.sbuf.append(locationInformation.getLineNumber());
            StringBuffer stringBuffer15 = this.sbuf;
            StringBuffer stringBuffer16 = new StringBuffer();
            stringBuffer16.append("</td>");
            stringBuffer16.append(Layout.LINE_SEP);
            stringBuffer15.append(stringBuffer16.toString());
        }
        this.sbuf.append("<td title=\"Message\">");
        this.sbuf.append(Transform.escapeTags(loggingEvent.getRenderedMessage()));
        StringBuffer stringBuffer17 = this.sbuf;
        StringBuffer stringBuffer18 = new StringBuffer();
        stringBuffer18.append("</td>");
        stringBuffer18.append(Layout.LINE_SEP);
        stringBuffer17.append(stringBuffer18.toString());
        StringBuffer stringBuffer19 = this.sbuf;
        StringBuffer stringBuffer20 = new StringBuffer();
        stringBuffer20.append("</tr>");
        stringBuffer20.append(Layout.LINE_SEP);
        stringBuffer19.append(stringBuffer20.toString());
        if (loggingEvent.getNDC() != null) {
            this.sbuf.append("<tr><td bgcolor=\"#EEEEEE\" style=\"font-size : xx-small;\" colspan=\"6\" title=\"Nested Diagnostic Context\">");
            StringBuffer stringBuffer21 = this.sbuf;
            StringBuffer stringBuffer22 = new StringBuffer();
            stringBuffer22.append("NDC: ");
            stringBuffer22.append(Transform.escapeTags(loggingEvent.getNDC()));
            stringBuffer21.append(stringBuffer22.toString());
            StringBuffer stringBuffer23 = this.sbuf;
            StringBuffer stringBuffer24 = new StringBuffer();
            stringBuffer24.append("</td></tr>");
            stringBuffer24.append(Layout.LINE_SEP);
            stringBuffer23.append(stringBuffer24.toString());
        }
        String[] throwableStrRep = loggingEvent.getThrowableStrRep();
        if (throwableStrRep != null) {
            this.sbuf.append("<tr><td bgcolor=\"#993300\" style=\"color:White; font-size : xx-small;\" colspan=\"6\">");
            appendThrowableAsHTML(throwableStrRep, this.sbuf);
            StringBuffer stringBuffer25 = this.sbuf;
            StringBuffer stringBuffer26 = new StringBuffer();
            stringBuffer26.append("</td></tr>");
            stringBuffer26.append(Layout.LINE_SEP);
            stringBuffer25.append(stringBuffer26.toString());
        }
        return this.sbuf.toString();
    }

    /* access modifiers changed from: package-private */
    public void appendThrowableAsHTML(String[] strArr, StringBuffer stringBuffer) {
        int length;
        if (strArr != null && (length = strArr.length) != 0) {
            stringBuffer.append(Transform.escapeTags(strArr[0]));
            stringBuffer.append(Layout.LINE_SEP);
            for (int i = 1; i < length; i++) {
                stringBuffer.append(TRACE_PREFIX);
                stringBuffer.append(Transform.escapeTags(strArr[i]));
                stringBuffer.append(Layout.LINE_SEP);
            }
        }
    }

    public String getHeader() {
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
        stringBuffer2.append(Layout.LINE_SEP);
        stringBuffer.append(stringBuffer2.toString());
        StringBuffer stringBuffer3 = new StringBuffer();
        stringBuffer3.append("<html>");
        stringBuffer3.append(Layout.LINE_SEP);
        stringBuffer.append(stringBuffer3.toString());
        StringBuffer stringBuffer4 = new StringBuffer();
        stringBuffer4.append("<head>");
        stringBuffer4.append(Layout.LINE_SEP);
        stringBuffer.append(stringBuffer4.toString());
        StringBuffer stringBuffer5 = new StringBuffer();
        stringBuffer5.append("<title>");
        stringBuffer5.append(this.title);
        stringBuffer5.append("</title>");
        stringBuffer5.append(Layout.LINE_SEP);
        stringBuffer.append(stringBuffer5.toString());
        StringBuffer stringBuffer6 = new StringBuffer();
        stringBuffer6.append("<style type=\"text/css\">");
        stringBuffer6.append(Layout.LINE_SEP);
        stringBuffer.append(stringBuffer6.toString());
        StringBuffer stringBuffer7 = new StringBuffer();
        stringBuffer7.append("<!--");
        stringBuffer7.append(Layout.LINE_SEP);
        stringBuffer.append(stringBuffer7.toString());
        StringBuffer stringBuffer8 = new StringBuffer();
        stringBuffer8.append("body, table {font-family: arial,sans-serif; font-size: x-small;}");
        stringBuffer8.append(Layout.LINE_SEP);
        stringBuffer.append(stringBuffer8.toString());
        StringBuffer stringBuffer9 = new StringBuffer();
        stringBuffer9.append("th {background: #336699; color: #FFFFFF; text-align: left;}");
        stringBuffer9.append(Layout.LINE_SEP);
        stringBuffer.append(stringBuffer9.toString());
        StringBuffer stringBuffer10 = new StringBuffer();
        stringBuffer10.append("-->");
        stringBuffer10.append(Layout.LINE_SEP);
        stringBuffer.append(stringBuffer10.toString());
        StringBuffer stringBuffer11 = new StringBuffer();
        stringBuffer11.append("</style>");
        stringBuffer11.append(Layout.LINE_SEP);
        stringBuffer.append(stringBuffer11.toString());
        StringBuffer stringBuffer12 = new StringBuffer();
        stringBuffer12.append("</head>");
        stringBuffer12.append(Layout.LINE_SEP);
        stringBuffer.append(stringBuffer12.toString());
        StringBuffer stringBuffer13 = new StringBuffer();
        stringBuffer13.append("<body bgcolor=\"#FFFFFF\" topmargin=\"6\" leftmargin=\"6\">");
        stringBuffer13.append(Layout.LINE_SEP);
        stringBuffer.append(stringBuffer13.toString());
        StringBuffer stringBuffer14 = new StringBuffer();
        stringBuffer14.append("<hr size=\"1\" noshade>");
        stringBuffer14.append(Layout.LINE_SEP);
        stringBuffer.append(stringBuffer14.toString());
        StringBuffer stringBuffer15 = new StringBuffer();
        stringBuffer15.append("Log session start time ");
        stringBuffer15.append(new Date());
        stringBuffer15.append("<br>");
        stringBuffer15.append(Layout.LINE_SEP);
        stringBuffer.append(stringBuffer15.toString());
        StringBuffer stringBuffer16 = new StringBuffer();
        stringBuffer16.append("<br>");
        stringBuffer16.append(Layout.LINE_SEP);
        stringBuffer.append(stringBuffer16.toString());
        StringBuffer stringBuffer17 = new StringBuffer();
        stringBuffer17.append("<table cellspacing=\"0\" cellpadding=\"4\" border=\"1\" bordercolor=\"#224466\" width=\"100%\">");
        stringBuffer17.append(Layout.LINE_SEP);
        stringBuffer.append(stringBuffer17.toString());
        StringBuffer stringBuffer18 = new StringBuffer();
        stringBuffer18.append("<tr>");
        stringBuffer18.append(Layout.LINE_SEP);
        stringBuffer.append(stringBuffer18.toString());
        StringBuffer stringBuffer19 = new StringBuffer();
        stringBuffer19.append("<th>Time</th>");
        stringBuffer19.append(Layout.LINE_SEP);
        stringBuffer.append(stringBuffer19.toString());
        StringBuffer stringBuffer20 = new StringBuffer();
        stringBuffer20.append("<th>Thread</th>");
        stringBuffer20.append(Layout.LINE_SEP);
        stringBuffer.append(stringBuffer20.toString());
        StringBuffer stringBuffer21 = new StringBuffer();
        stringBuffer21.append("<th>Level</th>");
        stringBuffer21.append(Layout.LINE_SEP);
        stringBuffer.append(stringBuffer21.toString());
        StringBuffer stringBuffer22 = new StringBuffer();
        stringBuffer22.append("<th>Category</th>");
        stringBuffer22.append(Layout.LINE_SEP);
        stringBuffer.append(stringBuffer22.toString());
        if (this.locationInfo) {
            StringBuffer stringBuffer23 = new StringBuffer();
            stringBuffer23.append("<th>File:Line</th>");
            stringBuffer23.append(Layout.LINE_SEP);
            stringBuffer.append(stringBuffer23.toString());
        }
        StringBuffer stringBuffer24 = new StringBuffer();
        stringBuffer24.append("<th>Message</th>");
        stringBuffer24.append(Layout.LINE_SEP);
        stringBuffer.append(stringBuffer24.toString());
        StringBuffer stringBuffer25 = new StringBuffer();
        stringBuffer25.append("</tr>");
        stringBuffer25.append(Layout.LINE_SEP);
        stringBuffer.append(stringBuffer25.toString());
        return stringBuffer.toString();
    }

    public String getFooter() {
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("</table>");
        stringBuffer2.append(Layout.LINE_SEP);
        stringBuffer.append(stringBuffer2.toString());
        StringBuffer stringBuffer3 = new StringBuffer();
        stringBuffer3.append("<br>");
        stringBuffer3.append(Layout.LINE_SEP);
        stringBuffer.append(stringBuffer3.toString());
        stringBuffer.append("</body></html>");
        return stringBuffer.toString();
    }
}
