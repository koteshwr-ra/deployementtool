package org.apache.mina.filter.util;

import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.filterchain.IoFilterChain;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;

public class ReferenceCountingFilter extends IoFilterAdapter {
    private int count = 0;
    private final IoFilter filter;

    public void destroy() throws Exception {
    }

    public void init() throws Exception {
    }

    public ReferenceCountingFilter(IoFilter ioFilter) {
        this.filter = ioFilter;
    }

    public synchronized void onPreAdd(IoFilterChain ioFilterChain, String str, IoFilter.NextFilter nextFilter) throws Exception {
        if (this.count == 0) {
            this.filter.init();
        }
        this.count++;
        this.filter.onPreAdd(ioFilterChain, str, nextFilter);
    }

    public synchronized void onPostRemove(IoFilterChain ioFilterChain, String str, IoFilter.NextFilter nextFilter) throws Exception {
        this.filter.onPostRemove(ioFilterChain, str, nextFilter);
        int i = this.count - 1;
        this.count = i;
        if (i == 0) {
            this.filter.destroy();
        }
    }

    public void exceptionCaught(IoFilter.NextFilter nextFilter, IoSession ioSession, Throwable th) throws Exception {
        this.filter.exceptionCaught(nextFilter, ioSession, th);
    }

    public void filterClose(IoFilter.NextFilter nextFilter, IoSession ioSession) throws Exception {
        this.filter.filterClose(nextFilter, ioSession);
    }

    public void filterWrite(IoFilter.NextFilter nextFilter, IoSession ioSession, WriteRequest writeRequest) throws Exception {
        this.filter.filterWrite(nextFilter, ioSession, writeRequest);
    }

    public void messageReceived(IoFilter.NextFilter nextFilter, IoSession ioSession, Object obj) throws Exception {
        this.filter.messageReceived(nextFilter, ioSession, obj);
    }

    public void messageSent(IoFilter.NextFilter nextFilter, IoSession ioSession, WriteRequest writeRequest) throws Exception {
        this.filter.messageSent(nextFilter, ioSession, writeRequest);
    }

    public void onPostAdd(IoFilterChain ioFilterChain, String str, IoFilter.NextFilter nextFilter) throws Exception {
        this.filter.onPostAdd(ioFilterChain, str, nextFilter);
    }

    public void onPreRemove(IoFilterChain ioFilterChain, String str, IoFilter.NextFilter nextFilter) throws Exception {
        this.filter.onPreRemove(ioFilterChain, str, nextFilter);
    }

    public void sessionClosed(IoFilter.NextFilter nextFilter, IoSession ioSession) throws Exception {
        this.filter.sessionClosed(nextFilter, ioSession);
    }

    public void sessionCreated(IoFilter.NextFilter nextFilter, IoSession ioSession) throws Exception {
        this.filter.sessionCreated(nextFilter, ioSession);
    }

    public void sessionIdle(IoFilter.NextFilter nextFilter, IoSession ioSession, IdleStatus idleStatus) throws Exception {
        this.filter.sessionIdle(nextFilter, ioSession, idleStatus);
    }

    public void sessionOpened(IoFilter.NextFilter nextFilter, IoSession ioSession) throws Exception {
        this.filter.sessionOpened(nextFilter, ioSession);
    }
}
