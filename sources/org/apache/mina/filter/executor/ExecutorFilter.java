package org.apache.mina.filter.executor;

import java.util.EnumSet;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.filterchain.IoFilterChain;
import org.apache.mina.core.filterchain.IoFilterEvent;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoEventType;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;

public class ExecutorFilter extends IoFilterAdapter {
    private static final int BASE_THREAD_NUMBER = 0;
    private static IoEventType[] DEFAULT_EVENT_SET = {IoEventType.EXCEPTION_CAUGHT, IoEventType.MESSAGE_RECEIVED, IoEventType.MESSAGE_SENT, IoEventType.SESSION_CLOSED, IoEventType.SESSION_IDLE, IoEventType.SESSION_OPENED};
    private static final long DEFAULT_KEEPALIVE_TIME = 30;
    private static final int DEFAULT_MAX_POOL_SIZE = 16;
    private static final boolean MANAGEABLE_EXECUTOR = true;
    private static final boolean NOT_MANAGEABLE_EXECUTOR = false;
    private EnumSet<IoEventType> eventTypes;
    private Executor executor;
    private boolean manageableExecutor;

    public ExecutorFilter() {
        init(createDefaultExecutor(0, 16, DEFAULT_KEEPALIVE_TIME, TimeUnit.SECONDS, Executors.defaultThreadFactory(), (IoEventQueueHandler) null), true, new IoEventType[0]);
    }

    public ExecutorFilter(int i) {
        init(createDefaultExecutor(0, i, DEFAULT_KEEPALIVE_TIME, TimeUnit.SECONDS, Executors.defaultThreadFactory(), (IoEventQueueHandler) null), true, new IoEventType[0]);
    }

    public ExecutorFilter(int i, int i2) {
        init(createDefaultExecutor(i, i2, DEFAULT_KEEPALIVE_TIME, TimeUnit.SECONDS, Executors.defaultThreadFactory(), (IoEventQueueHandler) null), true, new IoEventType[0]);
    }

    public ExecutorFilter(int i, int i2, long j, TimeUnit timeUnit) {
        init(createDefaultExecutor(i, i2, j, timeUnit, Executors.defaultThreadFactory(), (IoEventQueueHandler) null), true, new IoEventType[0]);
    }

    public ExecutorFilter(int i, int i2, long j, TimeUnit timeUnit, IoEventQueueHandler ioEventQueueHandler) {
        init(createDefaultExecutor(i, i2, j, timeUnit, Executors.defaultThreadFactory(), ioEventQueueHandler), true, new IoEventType[0]);
    }

    public ExecutorFilter(int i, int i2, long j, TimeUnit timeUnit, ThreadFactory threadFactory) {
        init(createDefaultExecutor(i, i2, j, timeUnit, threadFactory, (IoEventQueueHandler) null), true, new IoEventType[0]);
    }

    public ExecutorFilter(int i, int i2, long j, TimeUnit timeUnit, ThreadFactory threadFactory, IoEventQueueHandler ioEventQueueHandler) {
        init(new OrderedThreadPoolExecutor(i, i2, j, timeUnit, threadFactory, ioEventQueueHandler), true, new IoEventType[0]);
    }

    public ExecutorFilter(IoEventType... ioEventTypeArr) {
        init(createDefaultExecutor(0, 16, DEFAULT_KEEPALIVE_TIME, TimeUnit.SECONDS, Executors.defaultThreadFactory(), (IoEventQueueHandler) null), true, ioEventTypeArr);
    }

    public ExecutorFilter(int i, IoEventType... ioEventTypeArr) {
        init(createDefaultExecutor(0, i, DEFAULT_KEEPALIVE_TIME, TimeUnit.SECONDS, Executors.defaultThreadFactory(), (IoEventQueueHandler) null), true, ioEventTypeArr);
    }

    public ExecutorFilter(int i, int i2, IoEventType... ioEventTypeArr) {
        init(createDefaultExecutor(i, i2, DEFAULT_KEEPALIVE_TIME, TimeUnit.SECONDS, Executors.defaultThreadFactory(), (IoEventQueueHandler) null), true, ioEventTypeArr);
    }

    public ExecutorFilter(int i, int i2, long j, TimeUnit timeUnit, IoEventType... ioEventTypeArr) {
        init(createDefaultExecutor(i, i2, j, timeUnit, Executors.defaultThreadFactory(), (IoEventQueueHandler) null), true, ioEventTypeArr);
    }

    public ExecutorFilter(int i, int i2, long j, TimeUnit timeUnit, IoEventQueueHandler ioEventQueueHandler, IoEventType... ioEventTypeArr) {
        init(createDefaultExecutor(i, i2, j, timeUnit, Executors.defaultThreadFactory(), ioEventQueueHandler), true, ioEventTypeArr);
    }

    public ExecutorFilter(int i, int i2, long j, TimeUnit timeUnit, ThreadFactory threadFactory, IoEventType... ioEventTypeArr) {
        init(createDefaultExecutor(i, i2, j, timeUnit, threadFactory, (IoEventQueueHandler) null), true, ioEventTypeArr);
    }

    public ExecutorFilter(int i, int i2, long j, TimeUnit timeUnit, ThreadFactory threadFactory, IoEventQueueHandler ioEventQueueHandler, IoEventType... ioEventTypeArr) {
        init(new OrderedThreadPoolExecutor(i, i2, j, timeUnit, threadFactory, ioEventQueueHandler), true, ioEventTypeArr);
    }

    public ExecutorFilter(Executor executor2) {
        init(executor2, false, new IoEventType[0]);
    }

    public ExecutorFilter(Executor executor2, IoEventType... ioEventTypeArr) {
        init(executor2, false, ioEventTypeArr);
    }

    private Executor createDefaultExecutor(int i, int i2, long j, TimeUnit timeUnit, ThreadFactory threadFactory, IoEventQueueHandler ioEventQueueHandler) {
        return new OrderedThreadPoolExecutor(i, i2, j, timeUnit, threadFactory, ioEventQueueHandler);
    }

    private void initEventTypes(IoEventType... ioEventTypeArr) {
        if (ioEventTypeArr == null || ioEventTypeArr.length == 0) {
            ioEventTypeArr = DEFAULT_EVENT_SET;
        }
        EnumSet<IoEventType> of = EnumSet.of(ioEventTypeArr[0], ioEventTypeArr);
        this.eventTypes = of;
        if (of.contains(IoEventType.SESSION_CREATED)) {
            this.eventTypes = null;
            throw new IllegalArgumentException(IoEventType.SESSION_CREATED + " is not allowed.");
        }
    }

    private void init(Executor executor2, boolean z, IoEventType... ioEventTypeArr) {
        if (executor2 != null) {
            initEventTypes(ioEventTypeArr);
            this.executor = executor2;
            this.manageableExecutor = z;
            return;
        }
        throw new IllegalArgumentException("executor");
    }

    public void destroy() {
        if (this.manageableExecutor) {
            ((ExecutorService) this.executor).shutdown();
        }
    }

    public final Executor getExecutor() {
        return this.executor;
    }

    /* access modifiers changed from: protected */
    public void fireEvent(IoFilterEvent ioFilterEvent) {
        this.executor.execute(ioFilterEvent);
    }

    public void onPreAdd(IoFilterChain ioFilterChain, String str, IoFilter.NextFilter nextFilter) throws Exception {
        if (ioFilterChain.contains((IoFilter) this)) {
            throw new IllegalArgumentException("You can't add the same filter instance more than once.  Create another instance and add it.");
        }
    }

    public final void sessionOpened(IoFilter.NextFilter nextFilter, IoSession ioSession) {
        if (this.eventTypes.contains(IoEventType.SESSION_OPENED)) {
            fireEvent(new IoFilterEvent(nextFilter, IoEventType.SESSION_OPENED, ioSession, (Object) null));
        } else {
            nextFilter.sessionOpened(ioSession);
        }
    }

    public final void sessionClosed(IoFilter.NextFilter nextFilter, IoSession ioSession) {
        if (this.eventTypes.contains(IoEventType.SESSION_CLOSED)) {
            fireEvent(new IoFilterEvent(nextFilter, IoEventType.SESSION_CLOSED, ioSession, (Object) null));
        } else {
            nextFilter.sessionClosed(ioSession);
        }
    }

    public final void sessionIdle(IoFilter.NextFilter nextFilter, IoSession ioSession, IdleStatus idleStatus) {
        if (this.eventTypes.contains(IoEventType.SESSION_IDLE)) {
            fireEvent(new IoFilterEvent(nextFilter, IoEventType.SESSION_IDLE, ioSession, idleStatus));
        } else {
            nextFilter.sessionIdle(ioSession, idleStatus);
        }
    }

    public final void exceptionCaught(IoFilter.NextFilter nextFilter, IoSession ioSession, Throwable th) {
        if (this.eventTypes.contains(IoEventType.EXCEPTION_CAUGHT)) {
            fireEvent(new IoFilterEvent(nextFilter, IoEventType.EXCEPTION_CAUGHT, ioSession, th));
        } else {
            nextFilter.exceptionCaught(ioSession, th);
        }
    }

    public final void messageReceived(IoFilter.NextFilter nextFilter, IoSession ioSession, Object obj) {
        if (this.eventTypes.contains(IoEventType.MESSAGE_RECEIVED)) {
            fireEvent(new IoFilterEvent(nextFilter, IoEventType.MESSAGE_RECEIVED, ioSession, obj));
        } else {
            nextFilter.messageReceived(ioSession, obj);
        }
    }

    public final void messageSent(IoFilter.NextFilter nextFilter, IoSession ioSession, WriteRequest writeRequest) {
        if (this.eventTypes.contains(IoEventType.MESSAGE_SENT)) {
            fireEvent(new IoFilterEvent(nextFilter, IoEventType.MESSAGE_SENT, ioSession, writeRequest));
        } else {
            nextFilter.messageSent(ioSession, writeRequest);
        }
    }

    public final void filterWrite(IoFilter.NextFilter nextFilter, IoSession ioSession, WriteRequest writeRequest) {
        if (this.eventTypes.contains(IoEventType.WRITE)) {
            fireEvent(new IoFilterEvent(nextFilter, IoEventType.WRITE, ioSession, writeRequest));
        } else {
            nextFilter.filterWrite(ioSession, writeRequest);
        }
    }

    public final void filterClose(IoFilter.NextFilter nextFilter, IoSession ioSession) throws Exception {
        if (this.eventTypes.contains(IoEventType.CLOSE)) {
            fireEvent(new IoFilterEvent(nextFilter, IoEventType.CLOSE, ioSession, (Object) null));
        } else {
            nextFilter.filterClose(ioSession);
        }
    }
}
