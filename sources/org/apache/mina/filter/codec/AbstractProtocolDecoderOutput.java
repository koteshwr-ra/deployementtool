package org.apache.mina.filter.codec;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class AbstractProtocolDecoderOutput implements ProtocolDecoderOutput {
    private final Queue<Object> messageQueue = new ConcurrentLinkedQueue();

    public Queue<Object> getMessageQueue() {
        return this.messageQueue;
    }

    public void write(Object obj) {
        if (obj != null) {
            this.messageQueue.add(obj);
            return;
        }
        throw new IllegalArgumentException("message");
    }
}
