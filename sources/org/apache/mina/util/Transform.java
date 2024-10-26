package org.apache.mina.util;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;

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
        if (str.indexOf(34) == -1 && str.indexOf(38) == -1 && str.indexOf(60) == -1 && str.indexOf(62) == -1) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str.length() + 6);
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt > '>') {
                sb.append(charAt);
            } else if (charAt == '<') {
                sb.append("&lt;");
            } else if (charAt == '>') {
                sb.append("&gt;");
            } else if (charAt == '&') {
                sb.append("&amp;");
            } else if (charAt == '\"') {
                sb.append("&quot;");
            } else {
                sb.append(charAt);
            }
        }
        return sb.toString();
    }

    public static void appendEscapingCDATA(StringBuffer stringBuffer, String str) {
        if (str != null) {
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

    public static String[] getThrowableStrRep(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        printWriter.flush();
        LineNumberReader lineNumberReader = new LineNumberReader(new StringReader(stringWriter.toString()));
        ArrayList arrayList = new ArrayList();
        try {
            for (String readLine = lineNumberReader.readLine(); readLine != null; readLine = lineNumberReader.readLine()) {
                arrayList.add(readLine);
            }
        } catch (IOException e) {
            arrayList.add(e.toString());
        }
        String[] strArr = new String[arrayList.size()];
        arrayList.toArray(strArr);
        return strArr;
    }
}
