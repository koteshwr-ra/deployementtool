package org.apache.mina.core.buffer;

public class BufferDataException extends RuntimeException {
    private static final long serialVersionUID = -4138189188602563502L;

    public BufferDataException() {
    }

    public BufferDataException(String str) {
        super(str);
    }

    public BufferDataException(String str, Throwable th) {
        super(str, th);
    }

    public BufferDataException(Throwable th) {
        super(th);
    }
}
