package org.apache.mina.proxy.event;

import java.util.LinkedList;
import java.util.Queue;
import org.apache.mina.proxy.handlers.socks.SocksProxyRequest;
import org.apache.mina.proxy.session.ProxyIoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IoSessionEventQueue {
    private static final Logger logger = LoggerFactory.getLogger(IoSessionEventQueue.class);
    private ProxyIoSession proxyIoSession;
    private Queue<IoSessionEvent> sessionEventsQueue = new LinkedList();

    public IoSessionEventQueue(ProxyIoSession proxyIoSession2) {
        this.proxyIoSession = proxyIoSession2;
    }

    private void discardSessionQueueEvents() {
        synchronized (this.sessionEventsQueue) {
            this.sessionEventsQueue.clear();
            logger.debug("Event queue CLEARED");
        }
    }

    public void enqueueEventIfNecessary(IoSessionEvent ioSessionEvent) {
        logger.debug("??? >> Enqueue {}", (Object) ioSessionEvent);
        if (this.proxyIoSession.getRequest() instanceof SocksProxyRequest) {
            ioSessionEvent.deliverEvent();
        } else if (this.proxyIoSession.getHandler().isHandshakeComplete()) {
            ioSessionEvent.deliverEvent();
        } else if (ioSessionEvent.getType() == IoSessionEventType.CLOSED) {
            if (this.proxyIoSession.isAuthenticationFailed()) {
                this.proxyIoSession.getConnector().cancelConnectFuture();
                discardSessionQueueEvents();
                ioSessionEvent.deliverEvent();
                return;
            }
            discardSessionQueueEvents();
        } else if (ioSessionEvent.getType() == IoSessionEventType.OPENED) {
            enqueueSessionEvent(ioSessionEvent);
            ioSessionEvent.deliverEvent();
        } else {
            enqueueSessionEvent(ioSessionEvent);
        }
    }

    public void flushPendingSessionEvents() throws Exception {
        synchronized (this.sessionEventsQueue) {
            while (true) {
                IoSessionEvent poll = this.sessionEventsQueue.poll();
                if (poll != null) {
                    logger.debug(" Flushing buffered event: {}", (Object) poll);
                    poll.deliverEvent();
                }
            }
        }
    }

    private void enqueueSessionEvent(IoSessionEvent ioSessionEvent) {
        synchronized (this.sessionEventsQueue) {
            logger.debug("Enqueuing event: {}", (Object) ioSessionEvent);
            this.sessionEventsQueue.offer(ioSessionEvent);
        }
    }
}
