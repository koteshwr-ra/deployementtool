package org.apache.mina.filter.buffer;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.util.LazyInitializer;

public class IoBufferLazyInitializer extends LazyInitializer<IoBuffer> {
    private int bufferSize;

    public IoBufferLazyInitializer(int i) {
        this.bufferSize = i;
    }

    public IoBuffer init() {
        return IoBuffer.allocate(this.bufferSize);
    }
}
