package org.apache.log4j.helpers;

import java.io.Writer;
import org.apache.log4j.spi.ErrorHandler;

public class SyslogQuietWriter extends QuietWriter {
    int level;
    int syslogFacility;

    public SyslogQuietWriter(Writer writer, int i, ErrorHandler errorHandler) {
        super(writer, errorHandler);
        this.syslogFacility = i;
    }

    public void setLevel(int i) {
        this.level = i;
    }

    public void setSyslogFacility(int i) {
        this.syslogFacility = i;
    }

    public void write(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<");
        stringBuffer.append(this.syslogFacility | this.level);
        stringBuffer.append(">");
        stringBuffer.append(str);
        super.write(stringBuffer.toString());
    }
}
