package org.apache.mina.filter.keepalive;

import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.filterchain.IoFilterChain;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.DefaultWriteRequest;
import org.apache.mina.core.write.WriteRequest;

public class KeepAliveFilter extends IoFilterAdapter {
    private final AttributeKey IGNORE_READER_IDLE_ONCE;
    private final AttributeKey WAITING_FOR_RESPONSE;
    private volatile boolean forwardEvent;
    private final IdleStatus interestedIdleStatus;
    private final KeepAliveMessageFactory messageFactory;
    private volatile int requestInterval;
    private volatile int requestTimeout;
    private volatile KeepAliveRequestTimeoutHandler requestTimeoutHandler;

    public KeepAliveFilter(KeepAliveMessageFactory keepAliveMessageFactory) {
        this(keepAliveMessageFactory, IdleStatus.READER_IDLE, KeepAliveRequestTimeoutHandler.CLOSE);
    }

    public KeepAliveFilter(KeepAliveMessageFactory keepAliveMessageFactory, IdleStatus idleStatus) {
        this(keepAliveMessageFactory, idleStatus, KeepAliveRequestTimeoutHandler.CLOSE, 60, 30);
    }

    public KeepAliveFilter(KeepAliveMessageFactory keepAliveMessageFactory, KeepAliveRequestTimeoutHandler keepAliveRequestTimeoutHandler) {
        this(keepAliveMessageFactory, IdleStatus.READER_IDLE, keepAliveRequestTimeoutHandler, 60, 30);
    }

    public KeepAliveFilter(KeepAliveMessageFactory keepAliveMessageFactory, IdleStatus idleStatus, KeepAliveRequestTimeoutHandler keepAliveRequestTimeoutHandler) {
        this(keepAliveMessageFactory, idleStatus, keepAliveRequestTimeoutHandler, 60, 30);
    }

    public KeepAliveFilter(KeepAliveMessageFactory keepAliveMessageFactory, IdleStatus idleStatus, KeepAliveRequestTimeoutHandler keepAliveRequestTimeoutHandler, int i, int i2) {
        this.WAITING_FOR_RESPONSE = new AttributeKey(getClass(), "waitingForResponse");
        this.IGNORE_READER_IDLE_ONCE = new AttributeKey(getClass(), "ignoreReaderIdleOnce");
        if (keepAliveMessageFactory == null) {
            throw new IllegalArgumentException("messageFactory");
        } else if (idleStatus == null) {
            throw new IllegalArgumentException("interestedIdleStatus");
        } else if (keepAliveRequestTimeoutHandler != null) {
            this.messageFactory = keepAliveMessageFactory;
            this.interestedIdleStatus = idleStatus;
            this.requestTimeoutHandler = keepAliveRequestTimeoutHandler;
            setRequestInterval(i);
            setRequestTimeout(i2);
        } else {
            throw new IllegalArgumentException("policy");
        }
    }

    public IdleStatus getInterestedIdleStatus() {
        return this.interestedIdleStatus;
    }

    public KeepAliveRequestTimeoutHandler getRequestTimeoutHandler() {
        return this.requestTimeoutHandler;
    }

    public void setRequestTimeoutHandler(KeepAliveRequestTimeoutHandler keepAliveRequestTimeoutHandler) {
        if (keepAliveRequestTimeoutHandler != null) {
            this.requestTimeoutHandler = keepAliveRequestTimeoutHandler;
            return;
        }
        throw new IllegalArgumentException("timeoutHandler");
    }

    public int getRequestInterval() {
        return this.requestInterval;
    }

    public void setRequestInterval(int i) {
        if (i > 0) {
            this.requestInterval = i;
            return;
        }
        throw new IllegalArgumentException("keepAliveRequestInterval must be a positive integer: " + i);
    }

    public int getRequestTimeout() {
        return this.requestTimeout;
    }

    public void setRequestTimeout(int i) {
        if (i > 0) {
            this.requestTimeout = i;
            return;
        }
        throw new IllegalArgumentException("keepAliveRequestTimeout must be a positive integer: " + i);
    }

    public KeepAliveMessageFactory getMessageFactory() {
        return this.messageFactory;
    }

    public boolean isForwardEvent() {
        return this.forwardEvent;
    }

    public void setForwardEvent(boolean z) {
        this.forwardEvent = z;
    }

    public void onPreAdd(IoFilterChain ioFilterChain, String str, IoFilter.NextFilter nextFilter) throws Exception {
        if (ioFilterChain.contains((IoFilter) this)) {
            throw new IllegalArgumentException("You can't add the same filter instance more than once. Create another instance and add it.");
        }
    }

    public void onPostAdd(IoFilterChain ioFilterChain, String str, IoFilter.NextFilter nextFilter) throws Exception {
        resetStatus(ioFilterChain.getSession());
    }

    public void onPostRemove(IoFilterChain ioFilterChain, String str, IoFilter.NextFilter nextFilter) throws Exception {
        resetStatus(ioFilterChain.getSession());
    }

    public void messageReceived(IoFilter.NextFilter nextFilter, IoSession ioSession, Object obj) throws Exception {
        Object response;
        try {
            if (this.messageFactory.isRequest(ioSession, obj) && (response = this.messageFactory.getResponse(ioSession, obj)) != null) {
                nextFilter.filterWrite(ioSession, new DefaultWriteRequest(response));
            }
            if (this.messageFactory.isResponse(ioSession, obj)) {
                resetStatus(ioSession);
            }
        } finally {
            if (!isKeepAliveMessage(ioSession, obj)) {
                nextFilter.messageReceived(ioSession, obj);
            }
        }
    }

    public void messageSent(IoFilter.NextFilter nextFilter, IoSession ioSession, WriteRequest writeRequest) throws Exception {
        if (!isKeepAliveMessage(ioSession, writeRequest.getMessage())) {
            nextFilter.messageSent(ioSession, writeRequest);
        }
    }

    public void sessionIdle(IoFilter.NextFilter nextFilter, IoSession ioSession, IdleStatus idleStatus) throws Exception {
        if (idleStatus == this.interestedIdleStatus) {
            if (!ioSession.containsAttribute(this.WAITING_FOR_RESPONSE)) {
                Object request = this.messageFactory.getRequest(ioSession);
                if (request != null) {
                    nextFilter.filterWrite(ioSession, new DefaultWriteRequest(request));
                    if (getRequestTimeoutHandler() != KeepAliveRequestTimeoutHandler.DEAF_SPEAKER) {
                        markStatus(ioSession);
                        if (this.interestedIdleStatus == IdleStatus.BOTH_IDLE) {
                            ioSession.setAttribute(this.IGNORE_READER_IDLE_ONCE);
                        }
                    } else {
                        resetStatus(ioSession);
                    }
                }
            } else {
                handlePingTimeout(ioSession);
            }
        } else if (idleStatus == IdleStatus.READER_IDLE && ioSession.removeAttribute(this.IGNORE_READER_IDLE_ONCE) == null && ioSession.containsAttribute(this.WAITING_FOR_RESPONSE)) {
            handlePingTimeout(ioSession);
        }
        if (this.forwardEvent) {
            nextFilter.sessionIdle(ioSession, idleStatus);
        }
    }

    private void handlePingTimeout(IoSession ioSession) throws Exception {
        resetStatus(ioSession);
        KeepAliveRequestTimeoutHandler requestTimeoutHandler2 = getRequestTimeoutHandler();
        if (requestTimeoutHandler2 != KeepAliveRequestTimeoutHandler.DEAF_SPEAKER) {
            requestTimeoutHandler2.keepAliveRequestTimedOut(this, ioSession);
        }
    }

    private void markStatus(IoSession ioSession) {
        ioSession.getConfig().setIdleTime(this.interestedIdleStatus, 0);
        ioSession.getConfig().setReaderIdleTime(getRequestTimeout());
        ioSession.setAttribute(this.WAITING_FOR_RESPONSE);
    }

    private void resetStatus(IoSession ioSession) {
        ioSession.getConfig().setReaderIdleTime(0);
        ioSession.getConfig().setWriterIdleTime(0);
        ioSession.getConfig().setIdleTime(this.interestedIdleStatus, getRequestInterval());
        ioSession.removeAttribute(this.WAITING_FOR_RESPONSE);
    }

    private boolean isKeepAliveMessage(IoSession ioSession, Object obj) {
        return this.messageFactory.isRequest(ioSession, obj) || this.messageFactory.isResponse(ioSession, obj);
    }
}
