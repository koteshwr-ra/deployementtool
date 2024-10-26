package org.apache.mina.filter.buffer;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.DefaultWriteRequest;
import org.apache.mina.core.write.WriteRequest;
import org.apache.mina.util.LazyInitializedCacheMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class BufferedWriteFilter extends IoFilterAdapter {
    public static final int DEFAULT_BUFFER_SIZE = 8192;
    private int bufferSize;
    private final LazyInitializedCacheMap<IoSession, IoBuffer> buffersMap;
    private final Logger logger;

    public BufferedWriteFilter() {
        this(8192, (LazyInitializedCacheMap<IoSession, IoBuffer>) null);
    }

    public BufferedWriteFilter(int i) {
        this(i, (LazyInitializedCacheMap<IoSession, IoBuffer>) null);
    }

    public BufferedWriteFilter(int i, LazyInitializedCacheMap<IoSession, IoBuffer> lazyInitializedCacheMap) {
        this.logger = LoggerFactory.getLogger(BufferedWriteFilter.class);
        this.bufferSize = 8192;
        this.bufferSize = i;
        if (lazyInitializedCacheMap == null) {
            this.buffersMap = new LazyInitializedCacheMap<>();
        } else {
            this.buffersMap = lazyInitializedCacheMap;
        }
    }

    public int getBufferSize() {
        return this.bufferSize;
    }

    public void setBufferSize(int i) {
        this.bufferSize = i;
    }

    public void filterWrite(IoFilter.NextFilter nextFilter, IoSession ioSession, WriteRequest writeRequest) throws Exception {
        Object message = writeRequest.getMessage();
        if (message instanceof IoBuffer) {
            write(ioSession, (IoBuffer) message);
            return;
        }
        throw new IllegalArgumentException("This filter should only buffer IoBuffer objects");
    }

    private void write(IoSession ioSession, IoBuffer ioBuffer) {
        write(ioSession, ioBuffer, this.buffersMap.putIfAbsent(ioSession, new IoBufferLazyInitializer(this.bufferSize)));
    }

    private void write(IoSession ioSession, IoBuffer ioBuffer, IoBuffer ioBuffer2) {
        try {
            int remaining = ioBuffer.remaining();
            if (remaining >= ioBuffer2.capacity()) {
                IoFilter.NextFilter nextFilter = ioSession.getFilterChain().getNextFilter((IoFilter) this);
                internalFlush(nextFilter, ioSession, ioBuffer2);
                nextFilter.filterWrite(ioSession, new DefaultWriteRequest(ioBuffer));
                return;
            }
            if (remaining > ioBuffer2.limit() - ioBuffer2.position()) {
                internalFlush(ioSession.getFilterChain().getNextFilter((IoFilter) this), ioSession, ioBuffer2);
            }
            synchronized (ioBuffer2) {
                ioBuffer2.put(ioBuffer);
            }
        } catch (Throwable th) {
            ioSession.getFilterChain().fireExceptionCaught(th);
        }
    }

    private void internalFlush(IoFilter.NextFilter nextFilter, IoSession ioSession, IoBuffer ioBuffer) throws Exception {
        IoBuffer duplicate;
        synchronized (ioBuffer) {
            ioBuffer.flip();
            duplicate = ioBuffer.duplicate();
            ioBuffer.clear();
        }
        this.logger.debug("Flushing buffer: {}", (Object) duplicate);
        nextFilter.filterWrite(ioSession, new DefaultWriteRequest(duplicate));
    }

    public void flush(IoSession ioSession) {
        try {
            internalFlush(ioSession.getFilterChain().getNextFilter((IoFilter) this), ioSession, this.buffersMap.get(ioSession));
        } catch (Throwable th) {
            ioSession.getFilterChain().fireExceptionCaught(th);
        }
    }

    private void free(IoSession ioSession) {
        IoBuffer remove = this.buffersMap.remove(ioSession);
        if (remove != null) {
            remove.free();
        }
    }

    public void exceptionCaught(IoFilter.NextFilter nextFilter, IoSession ioSession, Throwable th) throws Exception {
        free(ioSession);
        nextFilter.exceptionCaught(ioSession, th);
    }

    public void sessionClosed(IoFilter.NextFilter nextFilter, IoSession ioSession) throws Exception {
        free(ioSession);
        nextFilter.sessionClosed(ioSession);
    }
}
