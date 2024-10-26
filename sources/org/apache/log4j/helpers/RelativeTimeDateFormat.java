package org.apache.log4j.helpers;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Date;

public class RelativeTimeDateFormat extends DateFormat {
    protected final long startTime = System.currentTimeMillis();

    public Date parse(String str, ParsePosition parsePosition) {
        return null;
    }

    public StringBuffer format(Date date, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        stringBuffer.append(date.getTime() - this.startTime);
        return stringBuffer;
    }
}
