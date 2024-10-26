package org.apache.mina.filter.codec.textline;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import org.apache.commons.lang3.StringUtils;
import org.apache.mina.proxy.handlers.http.HttpProxyConstants;

public class LineDelimiter {
    public static final LineDelimiter AUTO = new LineDelimiter("");
    public static final LineDelimiter CRLF = new LineDelimiter(HttpProxyConstants.CRLF);
    public static final LineDelimiter DEFAULT;
    public static final LineDelimiter MAC = new LineDelimiter(StringUtils.CR);
    public static final LineDelimiter NUL = new LineDelimiter("\u0000");
    public static final LineDelimiter UNIX = new LineDelimiter(StringUtils.LF);
    public static final LineDelimiter WINDOWS = CRLF;
    private final String value;

    static {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        new PrintWriter(byteArrayOutputStream, true).println();
        DEFAULT = new LineDelimiter(new String(byteArrayOutputStream.toByteArray()));
    }

    public LineDelimiter(String str) {
        if (str != null) {
            this.value = str;
            return;
        }
        throw new IllegalArgumentException(RequestParameters.DELIMITER);
    }

    public String getValue() {
        return this.value;
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LineDelimiter)) {
            return false;
        }
        return this.value.equals(((LineDelimiter) obj).value);
    }

    public String toString() {
        if (this.value.length() == 0) {
            return "delimiter: auto";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("delimiter:");
        for (int i = 0; i < this.value.length(); i++) {
            sb.append(" 0x");
            sb.append(Integer.toHexString(this.value.charAt(i)));
        }
        return sb.toString();
    }
}
