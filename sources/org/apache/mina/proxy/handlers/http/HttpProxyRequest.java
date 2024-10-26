package org.apache.mina.proxy.handlers.http;

import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import org.apache.mina.proxy.ProxyAuthException;
import org.apache.mina.proxy.handlers.ProxyRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpProxyRequest extends ProxyRequest {
    private static final Logger logger = LoggerFactory.getLogger(HttpProxyRequest.class);
    private Map<String, List<String>> headers;
    private String host;
    public final String httpURI;
    public final String httpVerb;
    private String httpVersion;
    private transient Map<String, String> properties;

    public HttpProxyRequest(InetSocketAddress inetSocketAddress) {
        this(inetSocketAddress, HttpProxyConstants.HTTP_1_0, (Map<String, List<String>>) null);
    }

    public HttpProxyRequest(InetSocketAddress inetSocketAddress, String str) {
        this(inetSocketAddress, str, (Map<String, List<String>>) null);
    }

    public HttpProxyRequest(InetSocketAddress inetSocketAddress, String str, Map<String, List<String>> map) {
        this.httpVerb = HttpProxyConstants.CONNECT;
        if (!inetSocketAddress.isUnresolved()) {
            this.httpURI = inetSocketAddress.getHostName() + ":" + inetSocketAddress.getPort();
        } else {
            this.httpURI = inetSocketAddress.getAddress().getHostAddress() + ":" + inetSocketAddress.getPort();
        }
        this.httpVersion = str;
        this.headers = map;
    }

    public HttpProxyRequest(String str) {
        this(HttpProxyConstants.GET, str, HttpProxyConstants.HTTP_1_0, (Map<String, List<String>>) null);
    }

    public HttpProxyRequest(String str, String str2) {
        this(HttpProxyConstants.GET, str, str2, (Map<String, List<String>>) null);
    }

    public HttpProxyRequest(String str, String str2, String str3) {
        this(str, str2, str3, (Map<String, List<String>>) null);
    }

    public HttpProxyRequest(String str, String str2, String str3, Map<String, List<String>> map) {
        this.httpVerb = str;
        this.httpURI = str2;
        this.httpVersion = str3;
        this.headers = map;
    }

    public final String getHttpVerb() {
        return this.httpVerb;
    }

    public String getHttpVersion() {
        return this.httpVersion;
    }

    public void setHttpVersion(String str) {
        this.httpVersion = str;
    }

    public final synchronized String getHost() {
        if (this.host == null) {
            if (getEndpointAddress() != null && !getEndpointAddress().isUnresolved()) {
                this.host = getEndpointAddress().getHostName();
            }
            if (this.host == null && this.httpURI != null) {
                try {
                    this.host = new URL(this.httpURI).getHost();
                } catch (MalformedURLException e) {
                    logger.debug("Malformed URL", (Throwable) e);
                }
            }
        }
        return this.host;
    }

    public final String getHttpURI() {
        return this.httpURI;
    }

    public final Map<String, List<String>> getHeaders() {
        return this.headers;
    }

    public final void setHeaders(Map<String, List<String>> map) {
        this.headers = map;
    }

    public Map<String, String> getProperties() {
        return this.properties;
    }

    public void setProperties(Map<String, String> map) {
        this.properties = map;
    }

    /* JADX WARNING: type inference failed for: r6v3, types: [java.lang.Throwable, org.apache.mina.proxy.ProxyAuthException] */
    public void checkRequiredProperties(String... strArr) throws ProxyAuthException {
        StringBuilder sb = new StringBuilder();
        for (String str : strArr) {
            if (this.properties.get(str) == null) {
                sb.append(str);
                sb.append(' ');
            }
        }
        if (sb.length() > 0) {
            sb.append("property(ies) missing in request");
            throw new ProxyAuthException(sb.toString());
        }
    }

    public String toHttpString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpVerb());
        sb.append(' ');
        sb.append(getHttpURI());
        sb.append(' ');
        sb.append(getHttpVersion());
        sb.append(HttpProxyConstants.CRLF);
        if (getHeaders() != null) {
            boolean z = false;
            for (Map.Entry next : getHeaders().entrySet()) {
                if (!z) {
                    z = ((String) next.getKey()).equalsIgnoreCase("host");
                }
                for (String append : (List) next.getValue()) {
                    sb.append((String) next.getKey());
                    sb.append(": ");
                    sb.append(append);
                    sb.append(HttpProxyConstants.CRLF);
                }
            }
            if (!z && getHttpVersion() == HttpProxyConstants.HTTP_1_1) {
                sb.append("Host: ");
                sb.append(getHost());
                sb.append(HttpProxyConstants.CRLF);
            }
        }
        sb.append(HttpProxyConstants.CRLF);
        return sb.toString();
    }
}
