package org.apache.mina.proxy.handlers.http;

import com.google.common.base.Ascii;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.session.IoSessionInitializer;
import org.apache.mina.proxy.AbstractProxyLogicHandler;
import org.apache.mina.proxy.ProxyAuthException;
import org.apache.mina.proxy.session.ProxyIoSession;
import org.apache.mina.proxy.utils.StringUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractHttpLogicHandler extends AbstractProxyLogicHandler {
    private static final byte[] CRLF_DELIMITER = {Ascii.CR, 10};
    private static final String DECODER;
    private static final byte[] HTTP_DELIMITER = {Ascii.CR, 10, Ascii.CR, 10};
    /* access modifiers changed from: private */
    public static final Logger LOGGER;
    private int contentLength = -1;
    private int entityBodyLimitPosition;
    private int entityBodyStartPosition;
    private boolean hasChunkedData;
    private HttpProxyResponse parsedResponse = null;
    private IoBuffer responseData = null;
    private boolean waitingChunkedData;
    private boolean waitingFooters;

    public abstract void handleResponse(HttpProxyResponse httpProxyResponse) throws ProxyAuthException;

    static {
        Class<AbstractHttpLogicHandler> cls = AbstractHttpLogicHandler.class;
        LOGGER = LoggerFactory.getLogger((Class) cls);
        DECODER = cls.getName() + ".Decoder";
    }

    public AbstractHttpLogicHandler(ProxyIoSession proxyIoSession) {
        super(proxyIoSession);
    }

    /* JADX WARNING: type inference failed for: r11v2, types: [java.lang.Throwable, org.apache.mina.proxy.ProxyAuthException] */
    /* JADX WARNING: type inference failed for: r10v3, types: [java.lang.Throwable, org.apache.mina.proxy.ProxyAuthException] */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0227, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void messageReceived(org.apache.mina.core.filterchain.IoFilter.NextFilter r10, org.apache.mina.core.buffer.IoBuffer r11) throws org.apache.mina.proxy.ProxyAuthException {
        /*
            r9 = this;
            monitor-enter(r9)
            org.slf4j.Logger r0 = LOGGER     // Catch:{ all -> 0x0238 }
            java.lang.String r1 = " messageReceived()"
            r0.debug(r1)     // Catch:{ all -> 0x0238 }
            org.apache.mina.core.session.IoSession r0 = r9.getSession()     // Catch:{ all -> 0x0238 }
            java.lang.String r1 = DECODER     // Catch:{ all -> 0x0238 }
            java.lang.Object r0 = r0.getAttribute(r1)     // Catch:{ all -> 0x0238 }
            org.apache.mina.proxy.utils.IoBufferDecoder r0 = (org.apache.mina.proxy.utils.IoBufferDecoder) r0     // Catch:{ all -> 0x0238 }
            if (r0 != 0) goto L_0x0026
            org.apache.mina.proxy.utils.IoBufferDecoder r0 = new org.apache.mina.proxy.utils.IoBufferDecoder     // Catch:{ all -> 0x0238 }
            byte[] r1 = HTTP_DELIMITER     // Catch:{ all -> 0x0238 }
            r0.<init>((byte[]) r1)     // Catch:{ all -> 0x0238 }
            org.apache.mina.core.session.IoSession r1 = r9.getSession()     // Catch:{ all -> 0x0238 }
            java.lang.String r2 = DECODER     // Catch:{ all -> 0x0238 }
            r1.setAttribute(r2, r0)     // Catch:{ all -> 0x0238 }
        L_0x0026:
            org.apache.mina.proxy.handlers.http.HttpProxyResponse r1 = r9.parsedResponse     // Catch:{ Exception -> 0x0228 }
            r2 = 1
            r3 = 0
            if (r1 != 0) goto L_0x00af
            org.apache.mina.core.buffer.IoBuffer r1 = r0.decodeFully(r11)     // Catch:{ Exception -> 0x0228 }
            r9.responseData = r1     // Catch:{ Exception -> 0x0228 }
            if (r1 != 0) goto L_0x0036
            monitor-exit(r9)
            return
        L_0x0036:
            org.apache.mina.proxy.session.ProxyIoSession r4 = r9.getProxyIoSession()     // Catch:{ Exception -> 0x0228 }
            java.nio.charset.Charset r4 = r4.getCharset()     // Catch:{ Exception -> 0x0228 }
            java.nio.charset.CharsetDecoder r4 = r4.newDecoder()     // Catch:{ Exception -> 0x0228 }
            java.lang.String r1 = r1.getString(r4)     // Catch:{ Exception -> 0x0228 }
            org.apache.mina.core.buffer.IoBuffer r4 = r9.responseData     // Catch:{ Exception -> 0x0228 }
            int r4 = r4.position()     // Catch:{ Exception -> 0x0228 }
            r9.entityBodyStartPosition = r4     // Catch:{ Exception -> 0x0228 }
            org.slf4j.Logger r4 = LOGGER     // Catch:{ Exception -> 0x0228 }
            java.lang.String r5 = "  response header received:\n{}"
            java.lang.String r6 = "\r"
            java.lang.String r7 = "\\r"
            java.lang.String r6 = r1.replace(r6, r7)     // Catch:{ Exception -> 0x0228 }
            java.lang.String r7 = "\n"
            java.lang.String r8 = "\\n\n"
            java.lang.String r6 = r6.replace(r7, r8)     // Catch:{ Exception -> 0x0228 }
            r4.debug((java.lang.String) r5, (java.lang.Object) r6)     // Catch:{ Exception -> 0x0228 }
            org.apache.mina.proxy.handlers.http.HttpProxyResponse r1 = r9.decodeResponse(r1)     // Catch:{ Exception -> 0x0228 }
            r9.parsedResponse = r1     // Catch:{ Exception -> 0x0228 }
            int r1 = r1.getStatusCode()     // Catch:{ Exception -> 0x0228 }
            r4 = 200(0xc8, float:2.8E-43)
            if (r1 == r4) goto L_0x00a7
            org.apache.mina.proxy.handlers.http.HttpProxyResponse r1 = r9.parsedResponse     // Catch:{ Exception -> 0x0228 }
            int r1 = r1.getStatusCode()     // Catch:{ Exception -> 0x0228 }
            r4 = 300(0x12c, float:4.2E-43)
            if (r1 < r4) goto L_0x0088
            org.apache.mina.proxy.handlers.http.HttpProxyResponse r1 = r9.parsedResponse     // Catch:{ Exception -> 0x0228 }
            int r1 = r1.getStatusCode()     // Catch:{ Exception -> 0x0228 }
            r4 = 307(0x133, float:4.3E-43)
            if (r1 > r4) goto L_0x0088
            goto L_0x00a7
        L_0x0088:
            org.apache.mina.proxy.handlers.http.HttpProxyResponse r1 = r9.parsedResponse     // Catch:{ Exception -> 0x0228 }
            java.util.Map r1 = r1.getHeaders()     // Catch:{ Exception -> 0x0228 }
            java.lang.String r4 = "Content-Length"
            java.lang.String r1 = org.apache.mina.proxy.utils.StringUtilities.getSingleValuedHeader(r1, r4)     // Catch:{ Exception -> 0x0228 }
            if (r1 != 0) goto L_0x0099
            r9.contentLength = r3     // Catch:{ Exception -> 0x0228 }
            goto L_0x00af
        L_0x0099:
            java.lang.String r1 = r1.trim()     // Catch:{ Exception -> 0x0228 }
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ Exception -> 0x0228 }
            r9.contentLength = r1     // Catch:{ Exception -> 0x0228 }
            r0.setContentLength(r1, r2)     // Catch:{ Exception -> 0x0228 }
            goto L_0x00af
        L_0x00a7:
            r11.position(r3)     // Catch:{ Exception -> 0x0228 }
            r9.setHandshakeComplete()     // Catch:{ Exception -> 0x0228 }
            monitor-exit(r9)
            return
        L_0x00af:
            boolean r1 = r9.hasChunkedData     // Catch:{ Exception -> 0x0228 }
            if (r1 != 0) goto L_0x00ea
            int r1 = r9.contentLength     // Catch:{ Exception -> 0x0228 }
            if (r1 <= 0) goto L_0x00cb
            org.apache.mina.core.buffer.IoBuffer r1 = r0.decodeFully(r11)     // Catch:{ Exception -> 0x0228 }
            if (r1 != 0) goto L_0x00bf
            monitor-exit(r9)
            return
        L_0x00bf:
            org.apache.mina.core.buffer.IoBuffer r4 = r9.responseData     // Catch:{ Exception -> 0x0228 }
            r4.setAutoExpand(r2)     // Catch:{ Exception -> 0x0228 }
            org.apache.mina.core.buffer.IoBuffer r4 = r9.responseData     // Catch:{ Exception -> 0x0228 }
            r4.put((org.apache.mina.core.buffer.IoBuffer) r1)     // Catch:{ Exception -> 0x0228 }
            r9.contentLength = r3     // Catch:{ Exception -> 0x0228 }
        L_0x00cb:
            java.lang.String r1 = "chunked"
            org.apache.mina.proxy.handlers.http.HttpProxyResponse r4 = r9.parsedResponse     // Catch:{ Exception -> 0x0228 }
            java.util.Map r4 = r4.getHeaders()     // Catch:{ Exception -> 0x0228 }
            java.lang.String r5 = "Transfer-Encoding"
            java.lang.String r4 = org.apache.mina.proxy.utils.StringUtilities.getSingleValuedHeader(r4, r5)     // Catch:{ Exception -> 0x0228 }
            boolean r1 = r1.equalsIgnoreCase(r4)     // Catch:{ Exception -> 0x0228 }
            if (r1 == 0) goto L_0x00ea
            org.slf4j.Logger r1 = LOGGER     // Catch:{ Exception -> 0x0228 }
            java.lang.String r4 = "Retrieving additional http response chunks"
            r1.debug(r4)     // Catch:{ Exception -> 0x0228 }
            r9.hasChunkedData = r2     // Catch:{ Exception -> 0x0228 }
            r9.waitingChunkedData = r2     // Catch:{ Exception -> 0x0228 }
        L_0x00ea:
            boolean r1 = r9.hasChunkedData     // Catch:{ Exception -> 0x0228 }
            if (r1 == 0) goto L_0x01c1
        L_0x00ee:
            boolean r1 = r9.waitingChunkedData     // Catch:{ Exception -> 0x0228 }
            r4 = 2
            if (r1 == 0) goto L_0x0177
            int r1 = r9.contentLength     // Catch:{ Exception -> 0x0228 }
            if (r1 != 0) goto L_0x014e
            byte[] r1 = CRLF_DELIMITER     // Catch:{ Exception -> 0x0228 }
            r0.setDelimiter(r1, r3)     // Catch:{ Exception -> 0x0228 }
            org.apache.mina.core.buffer.IoBuffer r1 = r0.decodeFully(r11)     // Catch:{ Exception -> 0x0228 }
            if (r1 != 0) goto L_0x0104
            monitor-exit(r9)
            return
        L_0x0104:
            org.apache.mina.proxy.session.ProxyIoSession r5 = r9.getProxyIoSession()     // Catch:{ Exception -> 0x0228 }
            java.nio.charset.Charset r5 = r5.getCharset()     // Catch:{ Exception -> 0x0228 }
            java.nio.charset.CharsetDecoder r5 = r5.newDecoder()     // Catch:{ Exception -> 0x0228 }
            java.lang.String r1 = r1.getString(r5)     // Catch:{ Exception -> 0x0228 }
            r5 = 59
            int r5 = r1.indexOf(r5)     // Catch:{ Exception -> 0x0228 }
            if (r5 < 0) goto L_0x0121
            java.lang.String r1 = r1.substring(r3, r5)     // Catch:{ Exception -> 0x0228 }
            goto L_0x012a
        L_0x0121:
            int r5 = r1.length()     // Catch:{ Exception -> 0x0228 }
            int r5 = r5 - r4
            java.lang.String r1 = r1.substring(r3, r5)     // Catch:{ Exception -> 0x0228 }
        L_0x012a:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0228 }
            r5.<init>()     // Catch:{ Exception -> 0x0228 }
            java.lang.String r6 = "0x"
            r5.append(r6)     // Catch:{ Exception -> 0x0228 }
            r5.append(r1)     // Catch:{ Exception -> 0x0228 }
            java.lang.String r1 = r5.toString()     // Catch:{ Exception -> 0x0228 }
            java.lang.Integer r1 = java.lang.Integer.decode(r1)     // Catch:{ Exception -> 0x0228 }
            int r1 = r1.intValue()     // Catch:{ Exception -> 0x0228 }
            r9.contentLength = r1     // Catch:{ Exception -> 0x0228 }
            if (r1 <= 0) goto L_0x014e
            int r1 = r1 + 2
            r9.contentLength = r1     // Catch:{ Exception -> 0x0228 }
            r0.setContentLength(r1, r2)     // Catch:{ Exception -> 0x0228 }
        L_0x014e:
            int r1 = r9.contentLength     // Catch:{ Exception -> 0x0228 }
            if (r1 != 0) goto L_0x015f
            r9.waitingChunkedData = r3     // Catch:{ Exception -> 0x0228 }
            r9.waitingFooters = r2     // Catch:{ Exception -> 0x0228 }
            org.apache.mina.core.buffer.IoBuffer r1 = r9.responseData     // Catch:{ Exception -> 0x0228 }
            int r1 = r1.position()     // Catch:{ Exception -> 0x0228 }
            r9.entityBodyLimitPosition = r1     // Catch:{ Exception -> 0x0228 }
            goto L_0x0177
        L_0x015f:
            org.apache.mina.core.buffer.IoBuffer r1 = r0.decodeFully(r11)     // Catch:{ Exception -> 0x0228 }
            if (r1 != 0) goto L_0x0167
            monitor-exit(r9)
            return
        L_0x0167:
            r9.contentLength = r3     // Catch:{ Exception -> 0x0228 }
            org.apache.mina.core.buffer.IoBuffer r4 = r9.responseData     // Catch:{ Exception -> 0x0228 }
            r4.put((org.apache.mina.core.buffer.IoBuffer) r1)     // Catch:{ Exception -> 0x0228 }
            int r1 = r11.position()     // Catch:{ Exception -> 0x0228 }
            r11.position(r1)     // Catch:{ Exception -> 0x0228 }
            goto L_0x00ee
        L_0x0177:
            boolean r1 = r9.waitingFooters     // Catch:{ Exception -> 0x0228 }
            if (r1 == 0) goto L_0x01c1
            byte[] r1 = CRLF_DELIMITER     // Catch:{ Exception -> 0x0228 }
            r0.setDelimiter(r1, r3)     // Catch:{ Exception -> 0x0228 }
            org.apache.mina.core.buffer.IoBuffer r1 = r0.decodeFully(r11)     // Catch:{ Exception -> 0x0228 }
            if (r1 != 0) goto L_0x0188
            monitor-exit(r9)
            return
        L_0x0188:
            int r5 = r1.remaining()     // Catch:{ Exception -> 0x0228 }
            if (r5 != r4) goto L_0x0191
            r9.waitingFooters = r3     // Catch:{ Exception -> 0x0228 }
            goto L_0x01c1
        L_0x0191:
            org.apache.mina.proxy.session.ProxyIoSession r5 = r9.getProxyIoSession()     // Catch:{ Exception -> 0x0228 }
            java.nio.charset.Charset r5 = r5.getCharset()     // Catch:{ Exception -> 0x0228 }
            java.nio.charset.CharsetDecoder r5 = r5.newDecoder()     // Catch:{ Exception -> 0x0228 }
            java.lang.String r5 = r1.getString(r5)     // Catch:{ Exception -> 0x0228 }
            java.lang.String r6 = ":\\s?"
            java.lang.String[] r5 = r5.split(r6, r4)     // Catch:{ Exception -> 0x0228 }
            org.apache.mina.proxy.handlers.http.HttpProxyResponse r6 = r9.parsedResponse     // Catch:{ Exception -> 0x0228 }
            java.util.Map r6 = r6.getHeaders()     // Catch:{ Exception -> 0x0228 }
            r7 = r5[r3]     // Catch:{ Exception -> 0x0228 }
            r5 = r5[r2]     // Catch:{ Exception -> 0x0228 }
            org.apache.mina.proxy.utils.StringUtilities.addValueToHeader(r6, r7, r5, r3)     // Catch:{ Exception -> 0x0228 }
            org.apache.mina.core.buffer.IoBuffer r5 = r9.responseData     // Catch:{ Exception -> 0x0228 }
            r5.put((org.apache.mina.core.buffer.IoBuffer) r1)     // Catch:{ Exception -> 0x0228 }
            org.apache.mina.core.buffer.IoBuffer r1 = r9.responseData     // Catch:{ Exception -> 0x0228 }
            byte[] r5 = CRLF_DELIMITER     // Catch:{ Exception -> 0x0228 }
            r1.put((byte[]) r5)     // Catch:{ Exception -> 0x0228 }
            goto L_0x0177
        L_0x01c1:
            org.apache.mina.core.buffer.IoBuffer r11 = r9.responseData     // Catch:{ Exception -> 0x0228 }
            r11.flip()     // Catch:{ Exception -> 0x0228 }
            org.slf4j.Logger r11 = LOGGER     // Catch:{ Exception -> 0x0228 }
            java.lang.String r1 = "  end of response received:\n{}"
            org.apache.mina.core.buffer.IoBuffer r4 = r9.responseData     // Catch:{ Exception -> 0x0228 }
            org.apache.mina.proxy.session.ProxyIoSession r5 = r9.getProxyIoSession()     // Catch:{ Exception -> 0x0228 }
            java.nio.charset.Charset r5 = r5.getCharset()     // Catch:{ Exception -> 0x0228 }
            java.nio.charset.CharsetDecoder r5 = r5.newDecoder()     // Catch:{ Exception -> 0x0228 }
            java.lang.String r4 = r4.getString(r5)     // Catch:{ Exception -> 0x0228 }
            r11.debug((java.lang.String) r1, (java.lang.Object) r4)     // Catch:{ Exception -> 0x0228 }
            org.apache.mina.core.buffer.IoBuffer r11 = r9.responseData     // Catch:{ Exception -> 0x0228 }
            int r1 = r9.entityBodyStartPosition     // Catch:{ Exception -> 0x0228 }
            r11.position(r1)     // Catch:{ Exception -> 0x0228 }
            org.apache.mina.core.buffer.IoBuffer r11 = r9.responseData     // Catch:{ Exception -> 0x0228 }
            int r1 = r9.entityBodyLimitPosition     // Catch:{ Exception -> 0x0228 }
            r11.limit(r1)     // Catch:{ Exception -> 0x0228 }
            org.apache.mina.proxy.handlers.http.HttpProxyResponse r11 = r9.parsedResponse     // Catch:{ Exception -> 0x0228 }
            org.apache.mina.core.buffer.IoBuffer r1 = r9.responseData     // Catch:{ Exception -> 0x0228 }
            org.apache.mina.proxy.session.ProxyIoSession r4 = r9.getProxyIoSession()     // Catch:{ Exception -> 0x0228 }
            java.nio.charset.Charset r4 = r4.getCharset()     // Catch:{ Exception -> 0x0228 }
            java.nio.charset.CharsetDecoder r4 = r4.newDecoder()     // Catch:{ Exception -> 0x0228 }
            java.lang.String r1 = r1.getString(r4)     // Catch:{ Exception -> 0x0228 }
            r11.setBody(r1)     // Catch:{ Exception -> 0x0228 }
            org.apache.mina.core.buffer.IoBuffer r11 = r9.responseData     // Catch:{ Exception -> 0x0228 }
            r11.free()     // Catch:{ Exception -> 0x0228 }
            r11 = 0
            r9.responseData = r11     // Catch:{ Exception -> 0x0228 }
            org.apache.mina.proxy.handlers.http.HttpProxyResponse r1 = r9.parsedResponse     // Catch:{ Exception -> 0x0228 }
            r9.handleResponse(r1)     // Catch:{ Exception -> 0x0228 }
            r9.parsedResponse = r11     // Catch:{ Exception -> 0x0228 }
            r9.hasChunkedData = r3     // Catch:{ Exception -> 0x0228 }
            r11 = -1
            r9.contentLength = r11     // Catch:{ Exception -> 0x0228 }
            byte[] r11 = HTTP_DELIMITER     // Catch:{ Exception -> 0x0228 }
            r0.setDelimiter(r11, r2)     // Catch:{ Exception -> 0x0228 }
            boolean r11 = r9.isHandshakeComplete()     // Catch:{ Exception -> 0x0228 }
            if (r11 != 0) goto L_0x0226
            r9.doHandshake(r10)     // Catch:{ Exception -> 0x0228 }
        L_0x0226:
            monitor-exit(r9)
            return
        L_0x0228:
            r10 = move-exception
            boolean r11 = r10 instanceof org.apache.mina.proxy.ProxyAuthException     // Catch:{ all -> 0x0238 }
            if (r11 == 0) goto L_0x0230
            org.apache.mina.proxy.ProxyAuthException r10 = (org.apache.mina.proxy.ProxyAuthException) r10     // Catch:{ all -> 0x0238 }
            throw r10     // Catch:{ all -> 0x0238 }
        L_0x0230:
            org.apache.mina.proxy.ProxyAuthException r11 = new org.apache.mina.proxy.ProxyAuthException     // Catch:{ all -> 0x0238 }
            java.lang.String r0 = "Handshake failed"
            r11.<init>(r0, r10)     // Catch:{ all -> 0x0238 }
            throw r11     // Catch:{ all -> 0x0238 }
        L_0x0238:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.mina.proxy.handlers.http.AbstractHttpLogicHandler.messageReceived(org.apache.mina.core.filterchain.IoFilter$NextFilter, org.apache.mina.core.buffer.IoBuffer):void");
    }

    public void writeRequest(IoFilter.NextFilter nextFilter, HttpProxyRequest httpProxyRequest) {
        if (getProxyIoSession().isReconnectionNeeded()) {
            reconnect(nextFilter, httpProxyRequest);
        } else {
            writeRequest0(nextFilter, httpProxyRequest);
        }
    }

    /* access modifiers changed from: private */
    public void writeRequest0(IoFilter.NextFilter nextFilter, HttpProxyRequest httpProxyRequest) {
        try {
            String httpString = httpProxyRequest.toHttpString();
            IoBuffer wrap = IoBuffer.wrap(httpString.getBytes(getProxyIoSession().getCharsetName()));
            LOGGER.debug("   write:\n{}", (Object) httpString.replace(StringUtils.CR, "\\r").replace(StringUtils.LF, "\\n\n"));
            writeData(nextFilter, wrap);
        } catch (UnsupportedEncodingException e) {
            closeSession("Unable to send HTTP request: ", e);
        }
    }

    private void reconnect(final IoFilter.NextFilter nextFilter, final HttpProxyRequest httpProxyRequest) {
        LOGGER.debug("Reconnecting to proxy ...");
        final ProxyIoSession proxyIoSession = getProxyIoSession();
        proxyIoSession.getConnector().connect((IoSessionInitializer<? extends ConnectFuture>) new IoSessionInitializer<ConnectFuture>() {
            public void initializeSession(IoSession ioSession, ConnectFuture connectFuture) {
                AbstractHttpLogicHandler.LOGGER.debug("Initializing new session: {}", (Object) ioSession);
                ioSession.setAttribute(ProxyIoSession.PROXY_SESSION, proxyIoSession);
                proxyIoSession.setSession(ioSession);
                AbstractHttpLogicHandler.LOGGER.debug("  setting up proxyIoSession: {}", (Object) proxyIoSession);
                connectFuture.addListener(new IoFutureListener<ConnectFuture>() {
                    public void operationComplete(ConnectFuture connectFuture) {
                        proxyIoSession.setReconnectionNeeded(false);
                        AbstractHttpLogicHandler.this.writeRequest0(nextFilter, httpProxyRequest);
                    }
                });
            }
        });
    }

    /* access modifiers changed from: protected */
    public HttpProxyResponse decodeResponse(String str) throws Exception {
        LOGGER.debug("  parseResponse()");
        String[] split = str.split(HttpProxyConstants.CRLF);
        String[] split2 = split[0].trim().split(StringUtils.SPACE, 2);
        if (split2.length < 2) {
            throw new Exception("Invalid response status line (" + split2 + "). Response: " + str);
        } else if (!split2[1].matches("^\\d\\d\\d")) {
            HashMap hashMap = new HashMap();
            for (int i = 1; i < split.length; i++) {
                String[] split3 = split[i].split(":\\s?", 2);
                StringUtilities.addValueToHeader(hashMap, split3[0], split3[1], false);
            }
            return new HttpProxyResponse(split2[0], split2[1], hashMap);
        } else {
            throw new Exception("Invalid response code (" + split2[1] + "). Response: " + str);
        }
    }
}
