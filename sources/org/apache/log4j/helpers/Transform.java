package org.apache.log4j.helpers;

public class Transform {
    private static final String CDATA_EMBEDED_END = "]]>]]&gt;<![CDATA[";
    private static final String CDATA_END = "]]>";
    private static final int CDATA_END_LEN = 3;
    private static final String CDATA_PSEUDO_END = "]]&gt;";
    private static final String CDATA_START = "<![CDATA[";

    public static String escapeTags(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(str.length() + 6);
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt == '<') {
                stringBuffer.append("&lt;");
            } else if (charAt == '>') {
                stringBuffer.append("&gt;");
            } else {
                stringBuffer.append(charAt);
            }
        }
        return stringBuffer.toString();
    }

    public static void appendEscapingCDATA(StringBuffer stringBuffer, String str) {
        if (str == null) {
            stringBuffer.append("");
            return;
        }
        int indexOf = str.indexOf(CDATA_END);
        if (indexOf < 0) {
            stringBuffer.append(str);
            return;
        }
        int i = 0;
        while (indexOf > -1) {
            stringBuffer.append(str.substring(i, indexOf));
            stringBuffer.append(CDATA_EMBEDED_END);
            i = CDATA_END_LEN + indexOf;
            if (i < str.length()) {
                indexOf = str.indexOf(CDATA_END, i);
            } else {
                return;
            }
        }
        stringBuffer.append(str.substring(i));
    }
}
