package org.apache.mina.proxy.handlers.http.ntlm;

import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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

public class HttpNTLMAuthLogicHandler extends AbstractAuthLogicHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpNTLMAuthLogicHandler.class);
    private byte[] challengePacket = null;

    public HttpNTLMAuthLogicHandler(ProxyIoSession proxyIoSession) throws ProxyAuthException {
        super(proxyIoSession);
        ((HttpProxyRequest) this.request).checkRequiredProperties(HttpProxyConstants.USER_PROPERTY, HttpProxyConstants.PWD_PROPERTY, HttpProxyConstants.DOMAIN_PROPERTY, HttpProxyConstants.WORKSTATION_PROPERTY);
    }

    public void doHandshake(IoFilter.NextFilter nextFilter) throws ProxyAuthException {
        LOGGER.debug(" doHandshake()");
        if (this.step <= 0 || this.challengePacket != null) {
            HttpProxyRequest httpProxyRequest = (HttpProxyRequest) this.request;
            Map headers = httpProxyRequest.getHeaders() != null ? httpProxyRequest.getHeaders() : new HashMap();
            String str = httpProxyRequest.getProperties().get(HttpProxyConstants.DOMAIN_PROPERTY);
            String str2 = httpProxyRequest.getProperties().get(HttpProxyConstants.WORKSTATION_PROPERTY);
            if (this.step > 0) {
                LOGGER.debug("  sending NTLM challenge response");
                byte[] createType3Message = NTLMUtilities.createType3Message(httpProxyRequest.getProperties().get(HttpProxyConstants.USER_PROPERTY), httpProxyRequest.getProperties().get(HttpProxyConstants.PWD_PROPERTY), NTLMUtilities.extractChallengeFromType2Message(this.challengePacket), str, str2, Integer.valueOf(NTLMUtilities.extractFlagsFromType2Message(this.challengePacket)), (byte[]) null);
                StringUtilities.addValueToHeader(headers, HttpHeaders.PROXY_AUTHORIZATION, "NTLM " + new String(Base64.encodeBase64(createType3Message)), true);
            } else {
                LOGGER.debug("  sending NTLM negotiation packet");
                byte[] createType1Message = NTLMUtilities.createType1Message(str2, str, (Integer) null, (byte[]) null);
                StringUtilities.addValueToHeader(headers, HttpHeaders.PROXY_AUTHORIZATION, "NTLM " + new String(Base64.encodeBase64(createType1Message)), true);
            }
            addKeepAliveHeaders(headers);
            httpProxyRequest.setHeaders(headers);
            writeRequest(nextFilter, httpProxyRequest);
            this.step++;
            return;
        }
        throw new IllegalStateException("NTLM Challenge packet not received");
    }

    private String getNTLMHeader(HttpProxyResponse httpProxyResponse) {
        for (String str : httpProxyResponse.getHeaders().get(HttpHeaders.PROXY_AUTHENTICATE)) {
            if (str.startsWith("NTLM")) {
                return str;
            }
        }
        return null;
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [java.lang.Throwable, org.apache.mina.proxy.ProxyAuthException] */
    /* JADX WARNING: type inference failed for: r4v5, types: [java.lang.Throwable, org.apache.mina.proxy.ProxyAuthException] */
    /* JADX WARNING: type inference failed for: r0v5, types: [java.lang.Throwable, org.apache.mina.proxy.ProxyAuthException] */
    public void handleResponse(HttpProxyResponse httpProxyResponse) throws ProxyAuthException {
        if (this.step == 0) {
            String nTLMHeader = getNTLMHeader(httpProxyResponse);
            this.step = 1;
            if (nTLMHeader == null || nTLMHeader.length() < 5) {
                return;
            }
        }
        if (this.step == 1) {
            String nTLMHeader2 = getNTLMHeader(httpProxyResponse);
            if (nTLMHeader2 == null || nTLMHeader2.length() < 5) {
                throw new ProxyAuthException("Unexpected error while reading server challenge !");
            }
            try {
                this.challengePacket = Base64.decodeBase64(nTLMHeader2.substring(5).getBytes(this.proxyIoSession.getCharsetName()));
                this.step = 2;
            } catch (IOException e) {
                throw new ProxyAuthException("Unable to decode the base64 encoded NTLM challenge", e);
            }
        } else {
            throw new ProxyAuthException("Received unexpected response code (" + httpProxyResponse.getStatusLine() + ").");
        }
    }
}
