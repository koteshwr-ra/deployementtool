package org.apache.mina.filter.util;

import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.filterchain.IoFilterEvent;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoEventType;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;

public abstract class CommonEventFilter extends IoFilterAdapter {
    /* access modifiers changed from: protected */
    public abstract void filter(IoFilterEvent ioFilterEvent) throws Exception;

    public final void sessionCreated(IoFilter.NextFilter nextFilter, IoSession ioSession) throws Exception {
        filter(new IoFilterEvent(nextFilter, IoEventType.SESSION_CREATED, ioSession, (Object) null));
    }

    public final void sessionOpened(IoFilter.NextFilter nextFilter, IoSession ioSession) throws Exception {
        filter(new IoFilterEvent(nextFilter, IoEventType.SESSION_OPENED, ioSession, (Object) null));
    }

    public final void sessionClosed(IoFilter.NextFilter nextFilter, IoSession ioSession) throws Exception {
        filter(new IoFilterEvent(nextFilter, IoEventType.SESSION_CLOSED, ioSession, (Object) null));
    }

    public final void sessionIdle(IoFilter.NextFilter nextFilter, IoSession ioSession, IdleStatus idleStatus) throws Exception {
        filter(new IoFilterEvent(nextFilter, IoEventType.SESSION_IDLE, ioSession, idleStatus));
    }

    public final void exceptionCaught(IoFilter.NextFilter nextFilter, IoSession ioSession, Throwable th) throws Exception {
        filter(new IoFilterEvent(nextFilter, IoEventType.EXCEPTION_CAUGHT, ioSession, th));
    }

    public final void messageReceived(IoFilter.NextFilter nextFilter, IoSession ioSession, Object obj) throws Exception {
        filter(new IoFilterEvent(nextFilter, IoEventType.MESSAGE_RECEIVED, ioSession, obj));
    }

    public final void messageSent(IoFilter.NextFilter nextFilter, IoSession ioSession, WriteRequest writeRequest) throws Exception {
        filter(new IoFilterEvent(nextFilter, IoEventType.MESSAGE_SENT, ioSession, writeRequest));
    }

    public final void filterWrite(IoFilter.NextFilter nextFilter, IoSession ioSession, WriteRequest writeRequest) throws Exception {
        filter(new IoFilterEvent(nextFilter, IoEventType.WRITE, ioSession, writeRequest));
    }

    public final void filterClose(IoFilter.NextFilter nextFilter, IoSession ioSession) throws Exception {
        filter(new IoFilterEvent(nextFilter, IoEventType.CLOSE, ioSession, (Object) null));
    }
}
