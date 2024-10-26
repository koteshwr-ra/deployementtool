package org.apache.mina.proxy.handlers.http.digest;

import com.google.common.net.HttpHeaders;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import kotlin.text.Typography;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.proxy.ProxyAuthException;
import org.apache.mina.proxy.handlers.http.AbstractAuthLogicHandler;
import org.apache.mina.proxy.handlers.http.HttpProxyConstants;
import org.apache.mina.proxy.handlers.http.HttpProxyRequest;
import org.apache.mina.proxy.handlers.http.HttpProxyResponse;
import org.apache.mina.proxy.session.ProxyIoSession;
import org.apache.mina.proxy.utils.StringUtilities;
import org.apache.mina.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpDigestAuthLogicHandler extends AbstractAuthLogicHandler {
    private static final Logger logger = LoggerFactory.getLogger(HttpDigestAuthLogicHandler.class);
    private static SecureRandom rnd;
    private HashMap<String, String> directives = null;
    private HttpProxyResponse response;

    static {
        try {
            rnd = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public HttpDigestAuthLogicHandler(ProxyIoSession proxyIoSession) throws ProxyAuthException {
        super(proxyIoSession);
        ((HttpProxyRequest) this.request).checkRequiredProperties(HttpProxyConstants.USER_PROPERTY, HttpProxyConstants.PWD_PROPERTY);
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [java.lang.Throwable, org.apache.mina.proxy.ProxyAuthException] */
    /* JADX WARNING: type inference failed for: r14v4, types: [java.lang.Throwable, org.apache.mina.proxy.ProxyAuthException] */
    /* JADX WARNING: type inference failed for: r0v6, types: [java.lang.Throwable, org.apache.mina.proxy.ProxyAuthException] */
    /* JADX WARNING: type inference failed for: r14v6, types: [java.lang.Throwable, org.apache.mina.proxy.ProxyAuthException] */
    /* JADX WARNING: type inference failed for: r14v7, types: [java.lang.Throwable, org.apache.mina.proxy.ProxyAuthException] */
    public void doHandshake(IoFilter.NextFilter nextFilter) throws ProxyAuthException {
        logger.debug(" doHandshake()");
        if (this.step <= 0 || this.directives != null) {
            HttpProxyRequest httpProxyRequest = (HttpProxyRequest) this.request;
            Map headers = httpProxyRequest.getHeaders() != null ? httpProxyRequest.getHeaders() : new HashMap();
            if (this.step > 0) {
                logger.debug("  sending DIGEST challenge response");
                HashMap hashMap = new HashMap();
                hashMap.put("username", httpProxyRequest.getProperties().get(HttpProxyConstants.USER_PROPERTY));
                StringUtilities.copyDirective(this.directives, (HashMap<String, String>) hashMap, "realm");
                StringUtilities.copyDirective(this.directives, (HashMap<String, String>) hashMap, "uri");
                StringUtilities.copyDirective(this.directives, (HashMap<String, String>) hashMap, "opaque");
                StringUtilities.copyDirective(this.directives, (HashMap<String, String>) hashMap, "nonce");
                String copyDirective = StringUtilities.copyDirective(this.directives, (HashMap<String, String>) hashMap, "algorithm");
                if (copyDirective == null || "md5".equalsIgnoreCase(copyDirective) || "md5-sess".equalsIgnoreCase(copyDirective)) {
                    String str = this.directives.get("qop");
                    if (str != null) {
                        StringTokenizer stringTokenizer = new StringTokenizer(str, ",");
                        String str2 = null;
                        while (stringTokenizer.hasMoreTokens()) {
                            String nextToken = stringTokenizer.nextToken();
                            if ("auth".equalsIgnoreCase(str2)) {
                                break;
                            } else if (Arrays.binarySearch(DigestUtilities.SUPPORTED_QOPS, nextToken) > -1) {
                                str2 = nextToken;
                            }
                        }
                        if (str2 != null) {
                            hashMap.put("qop", str2);
                            byte[] bArr = new byte[8];
                            rnd.nextBytes(bArr);
                            try {
                                hashMap.put("cnonce", new String(Base64.encodeBase64(bArr), this.proxyIoSession.getCharsetName()));
                            } catch (UnsupportedEncodingException e) {
                                throw new ProxyAuthException("Unable to encode cnonce", e);
                            }
                        } else {
                            throw new ProxyAuthException("No supported qop option available");
                        }
                    }
                    hashMap.put("nc", "00000001");
                    hashMap.put("uri", httpProxyRequest.getHttpURI());
                    try {
                        hashMap.put("response", DigestUtilities.computeResponseValue(this.proxyIoSession.getSession(), hashMap, httpProxyRequest.getHttpVerb().toUpperCase(), httpProxyRequest.getProperties().get(HttpProxyConstants.PWD_PROPERTY), this.proxyIoSession.getCharsetName(), this.response.getBody()));
                        StringBuilder sb = new StringBuilder("Digest ");
                        boolean z = false;
                        for (String str3 : hashMap.keySet()) {
                            if (z) {
                                sb.append(", ");
                            } else {
                                z = true;
                            }
                            boolean z2 = !"qop".equals(str3) && !"nc".equals(str3);
                            sb.append(str3);
                            if (z2) {
                                sb.append("=\"");
                                sb.append((String) hashMap.get(str3));
                                sb.append(Typography.quote);
                            } else {
                                sb.append('=');
                                sb.append((String) hashMap.get(str3));
                            }
                        }
                        StringUtilities.addValueToHeader(headers, HttpHeaders.PROXY_AUTHORIZATION, sb.toString(), true);
                    } catch (Exception e2) {
                        throw new ProxyAuthException("Digest response computing failed", e2);
                    }
                } else {
                    throw new ProxyAuthException("Unknown algorithm required by server");
                }
            }
            addKeepAliveHeaders(headers);
            httpProxyRequest.setHeaders(headers);
            writeRequest(nextFilter, httpProxyRequest);
            this.step++;
            return;
        }
        throw new ProxyAuthException("Authentication challenge not received");
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Throwable, org.apache.mina.proxy.ProxyAuthException] */
    /* JADX WARNING: type inference failed for: r5v7, types: [java.lang.Throwable, org.apache.mina.proxy.ProxyAuthException] */
    /* JADX WARNING: type inference failed for: r0v7, types: [java.lang.Throwable, org.apache.mina.proxy.ProxyAuthException] */
    /* JADX WARNING: type inference failed for: r0v12, types: [java.lang.Throwable, org.apache.mina.proxy.ProxyAuthException] */
    public void handleResponse(HttpProxyResponse httpProxyResponse) throws ProxyAuthException {
        this.response = httpProxyResponse;
        if (this.step != 0) {
            throw new ProxyAuthException("Received unexpected response code (" + httpProxyResponse.getStatusLine() + ").");
        } else if (httpProxyResponse.getStatusCode() == 401 || httpProxyResponse.getStatusCode() == 407) {
            String str = null;
            Iterator it = httpProxyResponse.getHeaders().get(HttpHeaders.PROXY_AUTHENTICATE).iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                String str2 = (String) it.next();
                if (str2.startsWith("Digest")) {
                    str = str2;
                    break;
                }
            }
            if (str != null) {
                try {
                    this.directives = StringUtilities.parseDirectives(str.substring(7).getBytes(this.proxyIoSession.getCharsetName()));
                    this.step = 1;
                } catch (Exception e) {
                    throw new ProxyAuthException("Parsing of server digest directives failed", e);
                }
            } else {
                throw new ProxyAuthException("Server doesn't support digest authentication method !");
            }
        } else {
            throw new ProxyAuthException("Received unexpected response code (" + httpProxyResponse.getStatusLine() + ").");
        }
    }
}
