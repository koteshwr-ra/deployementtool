package org.apache.mina.proxy.handlers.http;

import java.util.List;
import java.util.Map;

public class HttpProxyResponse {
    public String body;
    public final Map<String, List<String>> headers;
    public final String httpVersion;
    public final int statusCode;
    public final String statusLine;

    protected HttpProxyResponse(String str, String str2, Map<String, List<String>> map) {
        int i;
        this.httpVersion = str;
        this.statusLine = str2;
        int i2 = 0;
        if (str2.charAt(0) == ' ') {
            i2 = 1;
            i = 4;
        } else {
            i = 3;
        }
        this.statusCode = Integer.parseInt(str2.substring(i2, i));
        this.headers = map;
    }

    public final String getHttpVersion() {
        return this.httpVersion;
    }

    public final int getStatusCode() {
        return this.statusCode;
    }

    public final String getStatusLine() {
        return this.statusLine;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String str) {
        this.body = str;
    }

    public final Map<String, List<String>> getHeaders() {
        return this.headers;
    }
}
