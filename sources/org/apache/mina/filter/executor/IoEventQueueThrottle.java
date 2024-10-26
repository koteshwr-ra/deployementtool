package org.apache.mina.filter.executor;

import java.util.concurrent.atomic.AtomicInteger;
import org.apache.mina.core.session.IoEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IoEventQueueThrottle implements IoEventQueueHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(IoEventQueueThrottle.class);
    private final AtomicInteger counter;
    private final IoEventSizeEstimator eventSizeEstimator;
    private final Object lock;
    private volatile int threshold;
    private int waiters;

    public boolean accept(Object obj, IoEvent ioEvent) {
        return true;
    }

    public IoEventQueueThrottle() {
        this(new DefaultIoEventSizeEstimator(), 65536);
    }

    public IoEventQueueThrottle(int i) {
        this(new DefaultIoEventSizeEstimator(), i);
    }

    public IoEventQueueThrottle(IoEventSizeEstimator ioEventSizeEstimator, int i) {
        this.lock = new Object();
        this.counter = new AtomicInteger();
        if (ioEventSizeEstimator != null) {
            this.eventSizeEstimator = ioEventSizeEstimator;
            setThreshold(i);
            return;
        }
        throw new IllegalArgumentException("eventSizeEstimator");
    }

    public IoEventSizeEstimator getEventSizeEstimator() {
        return this.eventSizeEstimator;
    }

    public int getThreshold() {
        return this.threshold;
    }

    public int getCounter() {
        return this.counter.get();
    }

    public void setThreshold(int i) {
        if (i > 0) {
            this.threshold = i;
            return;
        }
        throw new IllegalArgumentException("threshold: " + i);
    }

    public void offered(Object obj, IoEvent ioEvent) {
        int addAndGet = this.counter.addAndGet(estimateSize(ioEvent));
        logState();
        if (addAndGet >= this.threshold) {
            block();
        }
    }

    public void polled(Object obj, IoEvent ioEvent) {
        int addAndGet = this.counter.addAndGet(-estimateSize(ioEvent));
        logState();
        if (addAndGet < this.threshold) {
            unblock();
        }
    }

    private int estimateSize(IoEvent ioEvent) {
        int estimateSize = getEventSizeEstimator().estimateSize(ioEvent);
        if (estimateSize >= 0) {
            return estimateSize;
        }
        throw new IllegalStateException(IoEventSizeEstimator.class.getSimpleName() + " returned " + "a negative value (" + estimateSize + "): " + ioEvent);
    }

    private void logState() {
        if (LOGGER.isDebugEnabled()) {
            Logger logger = LOGGER;
            logger.debug(Thread.currentThread().getName() + " state: " + this.counter.get() + " / " + getThreshold());
        }
    }

    /* access modifiers changed from: protected */
    public void block() {
        int i;
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Thread.currentThread().getName() + " blocked: " + this.counter.get() + " >= " + this.threshold);
        }
        synchronized (this.lock) {
            while (this.counter.get() >= this.threshold) {
                this.waiters++;
                try {
                    this.lock.wait();
                    i = this.waiters;
                } catch (InterruptedException unused) {
                    i = this.waiters;
                } catch (Throwable th) {
                    this.waiters--;
                    throw th;
                }
                this.waiters = i - 1;
            }
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Thread.currentThread().getName() + " unblocked: " + this.counter.get() + " < " + this.threshold);
        }
    }

    /* access modifiers changed from: protected */
    public void unblock() {
        synchronized (this.lock) {
            if (this.waiters > 0) {
                this.lock.notify();
            }
        }
    }
}
