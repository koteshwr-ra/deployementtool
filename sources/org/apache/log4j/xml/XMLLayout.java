package org.apache.log4j.xml;

import org.apache.log4j.Layout;
import org.apache.log4j.helpers.Transform;
import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.mina.proxy.handlers.http.HttpProxyConstants;

public class XMLLayout extends Layout {
    private final int DEFAULT_SIZE = 256;
    private final int UPPER_LIMIT = 2048;
    private StringBuffer buf = new StringBuffer(256);
    private boolean locationInfo = false;

    public void activateOptions() {
    }

    public String format(LoggingEvent loggingEvent) {
        if (this.buf.capacity() > 2048) {
            this.buf = new StringBuffer(256);
        } else {
            this.buf.setLength(0);
        }
        this.buf.append("<log4j:event logger=\"");
        this.buf.append(loggingEvent.getLoggerName());
        this.buf.append("\" timestamp=\"");
        this.buf.append(loggingEvent.timeStamp);
        this.buf.append("\" level=\"");
        this.buf.append(loggingEvent.getLevel());
        this.buf.append("\" thread=\"");
        this.buf.append(loggingEvent.getThreadName());
        this.buf.append("\">\r\n");
        this.buf.append("<log4j:message><![CDATA[");
        Transform.appendEscapingCDATA(this.buf, loggingEvent.getRenderedMessage());
        this.buf.append("]]></log4j:message>\r\n");
        String ndc = loggingEvent.getNDC();
        if (ndc != null) {
            this.buf.append("<log4j:NDC><![CDATA[");
            this.buf.append(ndc);
            this.buf.append("]]></log4j:NDC>\r\n");
        }
        String[] throwableStrRep = loggingEvent.getThrowableStrRep();
        if (throwableStrRep != null) {
            this.buf.append("<log4j:throwable><![CDATA[");
            for (String append : throwableStrRep) {
                this.buf.append(append);
                this.buf.append(HttpProxyConstants.CRLF);
            }
            this.buf.append("]]></log4j:throwable>\r\n");
        }
        if (this.locationInfo) {
            LocationInfo locationInformation = loggingEvent.getLocationInformation();
            this.buf.append("<log4j:locationInfo class=\"");
            this.buf.append(Transform.escapeTags(locationInformation.getClassName()));
            this.buf.append("\" method=\"");
            this.buf.append(Transform.escapeTags(locationInformation.getMethodName()));
            this.buf.append("\" file=\"");
            this.buf.append(locationInformation.getFileName());
            this.buf.append("\" line=\"");
            this.buf.append(locationInformation.getLineNumber());
            this.buf.append("\"/>\r\n");
        }
        this.buf.append("</log4j:event>\r\n\r\n");
        return this.buf.toString();
    }

    public boolean getLocationInfo() {
        return this.locationInfo;
    }

    public boolean ignoresThrowable() {
        return false;
    }

    public void setLocationInfo(boolean z) {
        this.locationInfo = z;
    }
}
