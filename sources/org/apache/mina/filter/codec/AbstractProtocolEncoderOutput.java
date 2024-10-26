package org.apache.mina.filter.codec;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.apache.mina.core.buffer.IoBuffer;

public abstract class AbstractProtocolEncoderOutput implements ProtocolEncoderOutput {
    private boolean buffersOnly = true;
    private final Queue<Object> messageQueue = new ConcurrentLinkedQueue();

    public Queue<Object> getMessageQueue() {
        return this.messageQueue;
    }

    public void write(Object obj) {
        if (obj instanceof IoBuffer) {
            IoBuffer ioBuffer = (IoBuffer) obj;
            if (ioBuffer.hasRemaining()) {
                this.messageQueue.offer(ioBuffer);
                return;
            }
            throw new IllegalArgumentException("buf is empty. Forgot to call flip()?");
        }
        this.messageQueue.offer(obj);
        this.buffersOnly = false;
    }

    public void mergeAll() {
        if (!this.buffersOnly) {
            throw new IllegalStateException("the encoded message list contains a non-buffer.");
        } else if (this.messageQueue.size() >= 2) {
            int i = 0;
            Iterator it = this.messageQueue.iterator();
            while (it.hasNext()) {
                i += ((IoBuffer) it.next()).remaining();
            }
            IoBuffer allocate = IoBuffer.allocate(i);
            while (true) {
                IoBuffer ioBuffer = (IoBuffer) this.messageQueue.poll();
                if (ioBuffer == null) {
                    allocate.flip();
                    this.messageQueue.add(allocate);
                    return;
                }
                allocate.put(ioBuffer);
            }
        }
    }
}
