package org.apache.mina.filter.stream;

import java.io.IOException;
import java.io.InputStream;
import org.apache.mina.core.buffer.IoBuffer;

public class StreamWriteFilter extends AbstractStreamWriteFilter<InputStream> {
    /* access modifiers changed from: protected */
    public IoBuffer getNextBuffer(InputStream inputStream) throws IOException {
        int writeBufferSize = getWriteBufferSize();
        byte[] bArr = new byte[writeBufferSize];
        int i = 0;
        int i2 = 0;
        while (i < writeBufferSize && (i2 = inputStream.read(bArr, i, writeBufferSize - i)) != -1) {
            i += i2;
        }
        if (i2 == -1 && i == 0) {
            return null;
        }
        return IoBuffer.wrap(bArr, 0, i);
    }

    /* access modifiers changed from: protected */
    public Class<InputStream> getMessageClass() {
        return InputStream.class;
    }
}
