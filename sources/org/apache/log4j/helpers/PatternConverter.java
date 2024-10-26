package org.apache.log4j.helpers;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.spi.LoggingEvent;

public abstract class PatternConverter {
    static String[] SPACES = {StringUtils.SPACE, "  ", "    ", "        ", "                ", "                                "};
    boolean leftAlign = false;
    int max = Integer.MAX_VALUE;
    int min = -1;
    public PatternConverter next;

    /* access modifiers changed from: protected */
    public abstract String convert(LoggingEvent loggingEvent);

    protected PatternConverter() {
    }

    protected PatternConverter(FormattingInfo formattingInfo) {
        this.min = formattingInfo.min;
        this.max = formattingInfo.max;
        this.leftAlign = formattingInfo.leftAlign;
    }

    public void format(StringBuffer stringBuffer, LoggingEvent loggingEvent) {
        String convert = convert(loggingEvent);
        if (convert == null) {
            int i = this.min;
            if (i > 0) {
                spacePad(stringBuffer, i);
                return;
            }
            return;
        }
        int length = convert.length();
        int i2 = this.max;
        if (length > i2) {
            stringBuffer.append(convert.substring(length - i2));
            return;
        }
        int i3 = this.min;
        if (length >= i3) {
            stringBuffer.append(convert);
        } else if (this.leftAlign) {
            stringBuffer.append(convert);
            spacePad(stringBuffer, this.min - length);
        } else {
            spacePad(stringBuffer, i3 - length);
            stringBuffer.append(convert);
        }
    }

    public void spacePad(StringBuffer stringBuffer, int i) {
        while (i >= 32) {
            stringBuffer.append(SPACES[5]);
            i -= 32;
        }
        for (int i2 = 4; i2 >= 0; i2--) {
            if (((1 << i2) & i) != 0) {
                stringBuffer.append(SPACES[i2]);
            }
        }
    }
}
