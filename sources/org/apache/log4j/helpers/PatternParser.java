package org.apache.log4j.helpers;

import com.tencent.smtt.sdk.TbsListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;

public class PatternParser {
    static final int CLASS_LOCATION_CONVERTER = 1002;
    private static final int CONVERTER_STATE = 1;
    private static final int DOT_STATE = 3;
    private static final char ESCAPE_CHAR = '%';
    static final int FILE_LOCATION_CONVERTER = 1004;
    static final int FULL_LOCATION_CONVERTER = 1000;
    static final int LEVEL_CONVERTER = 2002;
    static final int LINE_LOCATION_CONVERTER = 1003;
    private static final int LITERAL_STATE = 0;
    private static final int MAX_STATE = 5;
    static final int MESSAGE_CONVERTER = 2004;
    static final int METHOD_LOCATION_CONVERTER = 1001;
    private static final int MINUS_STATE = 2;
    private static final int MIN_STATE = 4;
    static final int NDC_CONVERTER = 2003;
    static final int RELATIVE_TIME_CONVERTER = 2000;
    static final int THREAD_CONVERTER = 2001;
    static /* synthetic */ Class class$java$text$DateFormat;
    protected StringBuffer currentLiteral = new StringBuffer(32);
    protected FormattingInfo formattingInfo = new FormattingInfo();
    PatternConverter head;
    protected int i;
    protected String pattern;
    protected int patternLength;
    int state;
    PatternConverter tail;

    public PatternParser(String str) {
        this.pattern = str;
        this.patternLength = str.length();
        this.state = 0;
    }

    private void addToList(PatternConverter patternConverter) {
        if (this.head == null) {
            this.tail = patternConverter;
            this.head = patternConverter;
            return;
        }
        this.tail.next = patternConverter;
        this.tail = patternConverter;
    }

    /* access modifiers changed from: protected */
    public String extractOption() {
        int indexOf;
        int i2;
        int i3 = this.i;
        if (i3 >= this.patternLength || this.pattern.charAt(i3) != '{' || (indexOf = this.pattern.indexOf(TbsListener.ErrorCode.DOWNLOAD_THROWABLE, this.i)) <= (i2 = this.i)) {
            return null;
        }
        String substring = this.pattern.substring(i2 + 1, indexOf);
        this.i = indexOf + 1;
        return substring;
    }

    /* access modifiers changed from: protected */
    public int extractPrecisionOption() {
        int i2;
        NumberFormatException e;
        String extractOption = extractOption();
        if (extractOption == null) {
            return 0;
        }
        try {
            i2 = Integer.parseInt(extractOption);
            if (i2 <= 0) {
                try {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Precision option (");
                    stringBuffer.append(extractOption);
                    stringBuffer.append(") isn't a positive integer.");
                    LogLog.error(stringBuffer.toString());
                    return 0;
                } catch (NumberFormatException e2) {
                    e = e2;
                }
            }
        } catch (NumberFormatException e3) {
            e = e3;
            i2 = 0;
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Category option \"");
            stringBuffer2.append(extractOption);
            stringBuffer2.append("\" not a decimal integer.");
            LogLog.error(stringBuffer2.toString(), e);
            return i2;
        }
        return i2;
    }

    public PatternConverter parse() {
        this.i = 0;
        while (true) {
            int i2 = this.i;
            if (i2 >= this.patternLength) {
                break;
            }
            String str = this.pattern;
            this.i = i2 + 1;
            char charAt = str.charAt(i2);
            int i3 = this.state;
            if (i3 == 0) {
                int i4 = this.i;
                if (i4 == this.patternLength) {
                    this.currentLiteral.append(charAt);
                } else if (charAt == '%') {
                    char charAt2 = this.pattern.charAt(i4);
                    if (charAt2 == '%') {
                        this.currentLiteral.append(charAt);
                        this.i++;
                    } else if (charAt2 != 'n') {
                        if (this.currentLiteral.length() != 0) {
                            addToList(new LiteralPatternConverter(this.currentLiteral.toString()));
                        }
                        this.currentLiteral.setLength(0);
                        this.currentLiteral.append(charAt);
                        this.state = 1;
                        this.formattingInfo.reset();
                    } else {
                        this.currentLiteral.append(Layout.LINE_SEP);
                        this.i++;
                    }
                } else {
                    this.currentLiteral.append(charAt);
                }
            } else if (i3 == 1) {
                this.currentLiteral.append(charAt);
                if (charAt == '-') {
                    this.formattingInfo.leftAlign = true;
                } else if (charAt == '.') {
                    this.state = 3;
                } else if (charAt < '0' || charAt > '9') {
                    finalizeConverter(charAt);
                } else {
                    this.formattingInfo.min = charAt - '0';
                    this.state = 4;
                }
            } else if (i3 == 3) {
                this.currentLiteral.append(charAt);
                if (charAt < '0' || charAt > '9') {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Error occured in position ");
                    stringBuffer.append(this.i);
                    stringBuffer.append(".\n Was expecting digit, instead got char \"");
                    stringBuffer.append(charAt);
                    stringBuffer.append("\".");
                    LogLog.error(stringBuffer.toString());
                    this.state = 0;
                } else {
                    this.formattingInfo.max = charAt - '0';
                    this.state = 5;
                }
            } else if (i3 == 4) {
                this.currentLiteral.append(charAt);
                if (charAt >= '0' && charAt <= '9') {
                    FormattingInfo formattingInfo2 = this.formattingInfo;
                    formattingInfo2.min = (formattingInfo2.min * 10) + (charAt - '0');
                } else if (charAt == '.') {
                    this.state = 3;
                } else {
                    finalizeConverter(charAt);
                }
            } else if (i3 == 5) {
                this.currentLiteral.append(charAt);
                if (charAt < '0' || charAt > '9') {
                    finalizeConverter(charAt);
                    this.state = 0;
                } else {
                    FormattingInfo formattingInfo3 = this.formattingInfo;
                    formattingInfo3.max = (formattingInfo3.max * 10) + (charAt - '0');
                }
            }
        }
        if (this.currentLiteral.length() != 0) {
            addToList(new LiteralPatternConverter(this.currentLiteral.toString()));
        }
        return this.head;
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    public void finalizeConverter(char c) {
        PatternConverter patternConverter;
        PatternConverter patternConverter2;
        DateFormat dateFormat;
        if (c == 'C') {
            patternConverter = new ClassNamePatternConverter(this, this.formattingInfo, extractPrecisionOption());
            this.currentLiteral.setLength(0);
        } else if (c != 'F') {
            if (c == 'X') {
                patternConverter2 = new MDCPatternConverter(this.formattingInfo, extractOption());
                this.currentLiteral.setLength(0);
            } else if (c == 'p') {
                patternConverter = new BasicPatternConverter(this.formattingInfo, 2002);
                this.currentLiteral.setLength(0);
            } else if (c == 'r') {
                patternConverter = new BasicPatternConverter(this.formattingInfo, RELATIVE_TIME_CONVERTER);
                this.currentLiteral.setLength(0);
            } else if (c == 't') {
                patternConverter = new BasicPatternConverter(this.formattingInfo, 2001);
                this.currentLiteral.setLength(0);
            } else if (c == 'x') {
                patternConverter = new BasicPatternConverter(this.formattingInfo, 2003);
                this.currentLiteral.setLength(0);
            } else if (c == 'L') {
                patternConverter = new LocationPatternConverter(this, this.formattingInfo, 1003);
                this.currentLiteral.setLength(0);
            } else if (c == 'M') {
                patternConverter = new LocationPatternConverter(this, this.formattingInfo, 1001);
                this.currentLiteral.setLength(0);
            } else if (c == 'c') {
                patternConverter = new CategoryPatternConverter(this, this.formattingInfo, extractPrecisionOption());
                this.currentLiteral.setLength(0);
            } else if (c == 'd') {
                String extractOption = extractOption();
                if (extractOption == null) {
                    extractOption = AbsoluteTimeDateFormat.ISO8601_DATE_FORMAT;
                }
                if (extractOption.equalsIgnoreCase(AbsoluteTimeDateFormat.ISO8601_DATE_FORMAT)) {
                    dateFormat = new ISO8601DateFormat();
                } else if (extractOption.equalsIgnoreCase(AbsoluteTimeDateFormat.ABS_TIME_DATE_FORMAT)) {
                    dateFormat = new AbsoluteTimeDateFormat();
                } else if (extractOption.equalsIgnoreCase(AbsoluteTimeDateFormat.DATE_AND_TIME_DATE_FORMAT)) {
                    dateFormat = new DateTimeDateFormat();
                } else {
                    try {
                        dateFormat = new SimpleDateFormat(extractOption);
                    } catch (IllegalArgumentException e) {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("Could not instantiate SimpleDateFormat with ");
                        stringBuffer.append(extractOption);
                        LogLog.error(stringBuffer.toString(), e);
                        Class cls = class$java$text$DateFormat;
                        if (cls == null) {
                            cls = class$("java.text.DateFormat");
                            class$java$text$DateFormat = cls;
                        }
                        dateFormat = (DateFormat) OptionConverter.instantiateByClassName("org.apache.log4j.helpers.ISO8601DateFormat", cls, (Object) null);
                    }
                }
                patternConverter2 = new DatePatternConverter(this.formattingInfo, dateFormat);
                this.currentLiteral.setLength(0);
            } else if (c == 'l') {
                patternConverter = new LocationPatternConverter(this, this.formattingInfo, 1000);
                this.currentLiteral.setLength(0);
            } else if (c != 'm') {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Unexpected char [");
                stringBuffer2.append(c);
                stringBuffer2.append("] at position ");
                stringBuffer2.append(this.i);
                stringBuffer2.append(" in conversion patterrn.");
                LogLog.error(stringBuffer2.toString());
                patternConverter = new LiteralPatternConverter(this.currentLiteral.toString());
                this.currentLiteral.setLength(0);
            } else {
                patternConverter = new BasicPatternConverter(this.formattingInfo, 2004);
                this.currentLiteral.setLength(0);
            }
            patternConverter = patternConverter2;
        } else {
            patternConverter = new LocationPatternConverter(this, this.formattingInfo, 1004);
            this.currentLiteral.setLength(0);
        }
        addConverter(patternConverter);
    }

    /* access modifiers changed from: protected */
    public void addConverter(PatternConverter patternConverter) {
        this.currentLiteral.setLength(0);
        addToList(patternConverter);
        this.state = 0;
        this.formattingInfo.reset();
    }

    private static class BasicPatternConverter extends PatternConverter {
        int type;

        BasicPatternConverter(FormattingInfo formattingInfo, int i) {
            super(formattingInfo);
            this.type = i;
        }

        public String convert(LoggingEvent loggingEvent) {
            switch (this.type) {
                case PatternParser.RELATIVE_TIME_CONVERTER /*2000*/:
                    return Long.toString(loggingEvent.timeStamp - LoggingEvent.getStartTime());
                case 2001:
                    return loggingEvent.getThreadName();
                case 2002:
                    return loggingEvent.getLevel().toString();
                case 2003:
                    return loggingEvent.getNDC();
                case 2004:
                    return loggingEvent.getRenderedMessage();
                default:
                    return null;
            }
        }
    }

    private static class LiteralPatternConverter extends PatternConverter {
        private String literal;

        LiteralPatternConverter(String str) {
            this.literal = str;
        }

        public final void format(StringBuffer stringBuffer, LoggingEvent loggingEvent) {
            stringBuffer.append(this.literal);
        }

        public String convert(LoggingEvent loggingEvent) {
            return this.literal;
        }
    }

    private static class DatePatternConverter extends PatternConverter {
        private Date date = new Date();
        private DateFormat df;

        DatePatternConverter(FormattingInfo formattingInfo, DateFormat dateFormat) {
            super(formattingInfo);
            this.df = dateFormat;
        }

        public String convert(LoggingEvent loggingEvent) {
            this.date.setTime(loggingEvent.timeStamp);
            try {
                return this.df.format(this.date);
            } catch (Exception e) {
                LogLog.error("Error occured while converting date.", e);
                return null;
            }
        }
    }

    private static class MDCPatternConverter extends PatternConverter {
        private String key;

        MDCPatternConverter(FormattingInfo formattingInfo, String str) {
            super(formattingInfo);
            this.key = str;
        }

        public String convert(LoggingEvent loggingEvent) {
            Object mdc = loggingEvent.getMDC(this.key);
            if (mdc == null) {
                return null;
            }
            return mdc.toString();
        }
    }

    private class LocationPatternConverter extends PatternConverter {
        private final /* synthetic */ PatternParser this$0;
        int type;

        LocationPatternConverter(PatternParser patternParser, FormattingInfo formattingInfo, int i) {
            super(formattingInfo);
            this.this$0 = patternParser;
            this.type = i;
        }

        public String convert(LoggingEvent loggingEvent) {
            LocationInfo locationInformation = loggingEvent.getLocationInformation();
            switch (this.type) {
                case 1000:
                    return locationInformation.fullInfo;
                case 1001:
                    return locationInformation.getMethodName();
                case 1003:
                    return locationInformation.getLineNumber();
                case 1004:
                    return locationInformation.getFileName();
                default:
                    return null;
            }
        }
    }

    private static abstract class NamedPatternConverter extends PatternConverter {
        int precision;

        /* access modifiers changed from: package-private */
        public abstract String getFullyQualifiedName(LoggingEvent loggingEvent);

        NamedPatternConverter(FormattingInfo formattingInfo, int i) {
            super(formattingInfo);
            this.precision = i;
        }

        public String convert(LoggingEvent loggingEvent) {
            String fullyQualifiedName = getFullyQualifiedName(loggingEvent);
            if (this.precision <= 0) {
                return fullyQualifiedName;
            }
            int length = fullyQualifiedName.length();
            int i = length - 1;
            for (int i2 = this.precision; i2 > 0; i2--) {
                i = fullyQualifiedName.lastIndexOf(46, i - 1);
                if (i == -1) {
                    return fullyQualifiedName;
                }
            }
            return fullyQualifiedName.substring(i + 1, length);
        }
    }

    private class ClassNamePatternConverter extends NamedPatternConverter {
        private final /* synthetic */ PatternParser this$0;

        ClassNamePatternConverter(PatternParser patternParser, FormattingInfo formattingInfo, int i) {
            super(formattingInfo, i);
            this.this$0 = patternParser;
        }

        /* access modifiers changed from: package-private */
        public String getFullyQualifiedName(LoggingEvent loggingEvent) {
            return loggingEvent.getLocationInformation().getClassName();
        }
    }

    private class CategoryPatternConverter extends NamedPatternConverter {
        private final /* synthetic */ PatternParser this$0;

        CategoryPatternConverter(PatternParser patternParser, FormattingInfo formattingInfo, int i) {
            super(formattingInfo, i);
            this.this$0 = patternParser;
        }

        /* access modifiers changed from: package-private */
        public String getFullyQualifiedName(LoggingEvent loggingEvent) {
            return loggingEvent.getLoggerName();
        }
    }
}
