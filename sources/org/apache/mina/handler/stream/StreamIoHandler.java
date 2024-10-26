package org.apache.mina.handler.stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketTimeoutException;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class StreamIoHandler extends IoHandlerAdapter {
    private static final AttributeKey KEY_IN;
    private static final AttributeKey KEY_OUT;
    private static final Logger LOGGER;
    private int readTimeout;
    private int writeTimeout;

    /* access modifiers changed from: protected */
    public abstract void processStreamIo(IoSession ioSession, InputStream inputStream, OutputStream outputStream);

    static {
        Class<StreamIoHandler> cls = StreamIoHandler.class;
        LOGGER = LoggerFactory.getLogger((Class) cls);
        KEY_IN = new AttributeKey(cls, "in");
        KEY_OUT = new AttributeKey(cls, "out");
    }

    protected StreamIoHandler() {
    }

    public int getReadTimeout() {
        return this.readTimeout;
    }

    public void setReadTimeout(int i) {
        this.readTimeout = i;
    }

    public int getWriteTimeout() {
        return this.writeTimeout;
    }

    public void setWriteTimeout(int i) {
        this.writeTimeout = i;
    }

    public void sessionOpened(IoSession ioSession) {
        ioSession.getConfig().setWriteTimeout(this.writeTimeout);
        ioSession.getConfig().setIdleTime(IdleStatus.READER_IDLE, this.readTimeout);
        IoSessionInputStream ioSessionInputStream = new IoSessionInputStream();
        IoSessionOutputStream ioSessionOutputStream = new IoSessionOutputStream(ioSession);
        ioSession.setAttribute(KEY_IN, ioSessionInputStream);
        ioSession.setAttribute(KEY_OUT, ioSessionOutputStream);
        processStreamIo(ioSession, ioSessionInputStream, ioSessionOutputStream);
    }

    public void sessionClosed(IoSession ioSession) throws Exception {
        InputStream inputStream = (InputStream) ioSession.getAttribute(KEY_IN);
        OutputStream outputStream = (OutputStream) ioSession.getAttribute(KEY_OUT);
        try {
            inputStream.close();
        } finally {
            outputStream.close();
        }
    }

    public void messageReceived(IoSession ioSession, Object obj) {
        ((IoSessionInputStream) ioSession.getAttribute(KEY_IN)).write((IoBuffer) obj);
    }

    public void exceptionCaught(IoSession ioSession, Throwable th) {
        IOException iOException;
        IoSessionInputStream ioSessionInputStream = (IoSessionInputStream) ioSession.getAttribute(KEY_IN);
        if (th instanceof StreamIoException) {
            iOException = (IOException) th.getCause();
        } else {
            iOException = th instanceof IOException ? (IOException) th : null;
        }
        if (iOException == null || ioSessionInputStream == null) {
            LOGGER.warn("Unexpected exception.", th);
            ioSession.close(true);
            return;
        }
        ioSessionInputStream.throwException(iOException);
    }

    public void sessionIdle(IoSession ioSession, IdleStatus idleStatus) {
        if (idleStatus == IdleStatus.READER_IDLE) {
            throw new StreamIoException(new SocketTimeoutException("Read timeout"));
        }
    }

    private static class StreamIoException extends RuntimeException {
        private static final long serialVersionUID = 3976736960742503222L;

        public StreamIoException(IOException iOException) {
            super(iOException);
        }
    }
}
