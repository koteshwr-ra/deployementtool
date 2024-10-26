package org.apache.mina.filter.firewall;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionThrottleFilter extends IoFilterAdapter {
    private static final long DEFAULT_TIME = 1000;
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionThrottleFilter.class);
    private long allowedInterval;
    private final Map<String, Long> clients;

    public ConnectionThrottleFilter() {
        this(1000);
    }

    public ConnectionThrottleFilter(long j) {
        this.allowedInterval = j;
        this.clients = Collections.synchronizedMap(new HashMap());
    }

    public void setAllowedInterval(long j) {
        this.allowedInterval = j;
    }

    /* access modifiers changed from: protected */
    public boolean isConnectionOk(IoSession ioSession) {
        SocketAddress remoteAddress = ioSession.getRemoteAddress();
        if (!(remoteAddress instanceof InetSocketAddress)) {
            return false;
        }
        InetSocketAddress inetSocketAddress = (InetSocketAddress) remoteAddress;
        long currentTimeMillis = System.currentTimeMillis();
        if (this.clients.containsKey(inetSocketAddress.getAddress().getHostAddress())) {
            LOGGER.debug("This is not a new client");
            this.clients.put(inetSocketAddress.getAddress().getHostAddress(), Long.valueOf(currentTimeMillis));
            if (currentTimeMillis - this.clients.get(inetSocketAddress.getAddress().getHostAddress()).longValue() >= this.allowedInterval) {
                return true;
            }
            LOGGER.warn("Session connection interval too short");
            return false;
        }
        this.clients.put(inetSocketAddress.getAddress().getHostAddress(), Long.valueOf(currentTimeMillis));
        return true;
    }

    public void sessionCreated(IoFilter.NextFilter nextFilter, IoSession ioSession) throws Exception {
        if (!isConnectionOk(ioSession)) {
            LOGGER.warn("Connections coming in too fast; closing.");
            ioSession.close(true);
        }
        nextFilter.sessionCreated(ioSession);
    }
}
