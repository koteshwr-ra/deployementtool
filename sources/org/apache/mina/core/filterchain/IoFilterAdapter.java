package org.apache.mina.core.filterchain;

import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;

public class IoFilterAdapter implements IoFilter {
    public void destroy() throws Exception {
    }

    public void init() throws Exception {
    }

    public void onPostAdd(IoFilterChain ioFilterChain, String str, IoFilter.NextFilter nextFilter) throws Exception {
    }

    public void onPostRemove(IoFilterChain ioFilterChain, String str, IoFilter.NextFilter nextFilter) throws Exception {
    }

    public void onPreAdd(IoFilterChain ioFilterChain, String str, IoFilter.NextFilter nextFilter) throws Exception {
    }

    public void onPreRemove(IoFilterChain ioFilterChain, String str, IoFilter.NextFilter nextFilter) throws Exception {
    }

    public void sessionCreated(IoFilter.NextFilter nextFilter, IoSession ioSession) throws Exception {
        nextFilter.sessionCreated(ioSession);
    }

    public void sessionOpened(IoFilter.NextFilter nextFilter, IoSession ioSession) throws Exception {
        nextFilter.sessionOpened(ioSession);
    }

    public void sessionClosed(IoFilter.NextFilter nextFilter, IoSession ioSession) throws Exception {
        nextFilter.sessionClosed(ioSession);
    }

    public void sessionIdle(IoFilter.NextFilter nextFilter, IoSession ioSession, IdleStatus idleStatus) throws Exception {
        nextFilter.sessionIdle(ioSession, idleStatus);
    }

    public void exceptionCaught(IoFilter.NextFilter nextFilter, IoSession ioSession, Throwable th) throws Exception {
        nextFilter.exceptionCaught(ioSession, th);
    }

    public void messageReceived(IoFilter.NextFilter nextFilter, IoSession ioSession, Object obj) throws Exception {
        nextFilter.messageReceived(ioSession, obj);
    }

    public void messageSent(IoFilter.NextFilter nextFilter, IoSession ioSession, WriteRequest writeRequest) throws Exception {
        nextFilter.messageSent(ioSession, writeRequest);
    }

    public void filterWrite(IoFilter.NextFilter nextFilter, IoSession ioSession, WriteRequest writeRequest) throws Exception {
        nextFilter.filterWrite(ioSession, writeRequest);
    }

    public void filterClose(IoFilter.NextFilter nextFilter, IoSession ioSession) throws Exception {
        nextFilter.filterClose(ioSession);
    }

    public String toString() {
        return getClass().getSimpleName();
    }
}
