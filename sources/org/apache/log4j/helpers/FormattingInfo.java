package org.apache.log4j.helpers;

public class FormattingInfo {
    boolean leftAlign = false;
    int max = Integer.MAX_VALUE;
    int min = -1;

    /* access modifiers changed from: package-private */
    public void reset() {
        this.min = -1;
        this.max = Integer.MAX_VALUE;
        this.leftAlign = false;
    }

    /* access modifiers changed from: package-private */
    public void dump() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("min=");
        stringBuffer.append(this.min);
        stringBuffer.append(", max=");
        stringBuffer.append(this.max);
        stringBuffer.append(", leftAlign=");
        stringBuffer.append(this.leftAlign);
        LogLog.debug(stringBuffer.toString());
    }
}
