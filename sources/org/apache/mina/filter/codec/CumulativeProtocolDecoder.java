package org.apache.mina.filter.codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IoSession;

public abstract class CumulativeProtocolDecoder extends ProtocolDecoderAdapter {
    private final AttributeKey BUFFER = new AttributeKey(getClass(), "buffer");

    /* access modifiers changed from: protected */
    public abstract boolean doDecode(IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception;

    protected CumulativeProtocolDecoder() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:2:0x000a A[LOOP:0: B:2:0x000a->B:5:0x0014, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void decode(org.apache.mina.core.session.IoSession r5, org.apache.mina.core.buffer.IoBuffer r6, org.apache.mina.filter.codec.ProtocolDecoderOutput r7) throws java.lang.Exception {
        /*
            r4 = this;
            org.apache.mina.core.service.TransportMetadata r0 = r5.getTransportMetadata()
            boolean r0 = r0.hasFragmentation()
            if (r0 != 0) goto L_0x0017
        L_0x000a:
            boolean r0 = r6.hasRemaining()
            if (r0 == 0) goto L_0x0016
            boolean r0 = r4.doDecode(r5, r6, r7)
            if (r0 != 0) goto L_0x000a
        L_0x0016:
            return
        L_0x0017:
            org.apache.mina.core.session.AttributeKey r0 = r4.BUFFER
            java.lang.Object r0 = r5.getAttribute(r0)
            org.apache.mina.core.buffer.IoBuffer r0 = (org.apache.mina.core.buffer.IoBuffer) r0
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x0061
            boolean r3 = r0.isAutoExpand()
            if (r3 == 0) goto L_0x002f
            r0.put((org.apache.mina.core.buffer.IoBuffer) r6)     // Catch:{ IllegalStateException | IndexOutOfBoundsException -> 0x002e }
            r1 = 1
            goto L_0x002f
        L_0x002e:
        L_0x002f:
            if (r1 == 0) goto L_0x0036
            r0.flip()
            r6 = r0
            goto L_0x0060
        L_0x0036:
            r0.flip()
            int r1 = r0.remaining()
            int r3 = r6.remaining()
            int r1 = r1 + r3
            org.apache.mina.core.buffer.IoBuffer r1 = org.apache.mina.core.buffer.IoBuffer.allocate(r1)
            org.apache.mina.core.buffer.IoBuffer r1 = r1.setAutoExpand(r2)
            java.nio.ByteOrder r3 = r0.order()
            r1.order(r3)
            r1.put((org.apache.mina.core.buffer.IoBuffer) r0)
            r1.put((org.apache.mina.core.buffer.IoBuffer) r6)
            r1.flip()
            org.apache.mina.core.session.AttributeKey r6 = r4.BUFFER
            r5.setAttribute(r6, r1)
            r6 = r1
        L_0x0060:
            r1 = 1
        L_0x0061:
            int r0 = r6.position()
            boolean r2 = r4.doDecode(r5, r6, r7)
            if (r2 == 0) goto L_0x0080
            int r2 = r6.position()
            if (r2 == r0) goto L_0x0078
            boolean r0 = r6.hasRemaining()
            if (r0 != 0) goto L_0x0061
            goto L_0x0080
        L_0x0078:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "doDecode() can't return true when buffer is not consumed."
            r5.<init>(r6)
            throw r5
        L_0x0080:
            boolean r7 = r6.hasRemaining()
            if (r7 == 0) goto L_0x0096
            if (r1 == 0) goto L_0x0092
            boolean r7 = r6.isAutoExpand()
            if (r7 == 0) goto L_0x0092
            r6.compact()
            goto L_0x009b
        L_0x0092:
            r4.storeRemainingInSession(r6, r5)
            goto L_0x009b
        L_0x0096:
            if (r1 == 0) goto L_0x009b
            r4.removeSessionBuffer(r5)
        L_0x009b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.mina.filter.codec.CumulativeProtocolDecoder.decode(org.apache.mina.core.session.IoSession, org.apache.mina.core.buffer.IoBuffer, org.apache.mina.filter.codec.ProtocolDecoderOutput):void");
    }

    public void dispose(IoSession ioSession) throws Exception {
        removeSessionBuffer(ioSession);
    }

    private void removeSessionBuffer(IoSession ioSession) {
        ioSession.removeAttribute(this.BUFFER);
    }

    private void storeRemainingInSession(IoBuffer ioBuffer, IoSession ioSession) {
        IoBuffer autoExpand = IoBuffer.allocate(ioBuffer.capacity()).setAutoExpand(true);
        autoExpand.order(ioBuffer.order());
        autoExpand.put(ioBuffer);
        ioSession.setAttribute(this.BUFFER, autoExpand);
    }
}
