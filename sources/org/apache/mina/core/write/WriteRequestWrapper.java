package org.apache.mina.core.write;

import java.net.SocketAddress;
import org.apache.mina.core.future.WriteFuture;

public class WriteRequestWrapper implements WriteRequest {
    private final WriteRequest parentRequest;

    public boolean isEncoded() {
        return false;
    }

    public WriteRequestWrapper(WriteRequest writeRequest) {
        if (writeRequest != null) {
            this.parentRequest = writeRequest;
            return;
        }
        throw new IllegalArgumentException("parentRequest");
    }

    public SocketAddress getDestination() {
        return this.parentRequest.getDestination();
    }

    public WriteFuture getFuture() {
        return this.parentRequest.getFuture();
    }

    public Object getMessage() {
        return this.parentRequest.getMessage();
    }

    public WriteRequest getOriginalRequest() {
        return this.parentRequest.getOriginalRequest();
    }

    public WriteRequest getParentRequest() {
        return this.parentRequest;
    }

    public String toString() {
        return "WR Wrapper" + this.parentRequest.toString();
    }
}
