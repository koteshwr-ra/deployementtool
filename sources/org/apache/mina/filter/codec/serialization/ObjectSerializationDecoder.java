package org.apache.mina.filter.codec.serialization;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class ObjectSerializationDecoder extends CumulativeProtocolDecoder {
    private final ClassLoader classLoader;
    private int maxObjectSize;

    public ObjectSerializationDecoder() {
        this(Thread.currentThread().getContextClassLoader());
    }

    public ObjectSerializationDecoder(ClassLoader classLoader2) {
        this.maxObjectSize = 1048576;
        if (classLoader2 != null) {
            this.classLoader = classLoader2;
            return;
        }
        throw new IllegalArgumentException("classLoader");
    }

    public int getMaxObjectSize() {
        return this.maxObjectSize;
    }

    public void setMaxObjectSize(int i) {
        if (i > 0) {
            this.maxObjectSize = i;
            return;
        }
        throw new IllegalArgumentException("maxObjectSize: " + i);
    }

    /* access modifiers changed from: protected */
    public boolean doDecode(IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        if (!ioBuffer.prefixedDataAvailable(4, this.maxObjectSize)) {
            return false;
        }
        protocolDecoderOutput.write(ioBuffer.getObject(this.classLoader));
        return true;
    }
}
