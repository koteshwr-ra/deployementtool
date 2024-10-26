package org.apache.mina.filter.codec.statemachine;

import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public abstract class CrLfDecodingState implements DecodingState {
    private static final byte CR = 13;
    private static final byte LF = 10;
    private boolean hasCR;

    /* access modifiers changed from: protected */
    public abstract DecodingState finishDecode(boolean z, ProtocolDecoderOutput protocolDecoderOutput) throws Exception;

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0028, code lost:
        r6 = true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.mina.filter.codec.statemachine.DecodingState decode(org.apache.mina.core.buffer.IoBuffer r6, org.apache.mina.filter.codec.ProtocolDecoderOutput r7) throws java.lang.Exception {
        /*
            r5 = this;
        L_0x0000:
            boolean r0 = r6.hasRemaining()
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x0043
            byte r0 = r6.get()
            boolean r3 = r5.hasCR
            r4 = 10
            if (r3 != 0) goto L_0x0026
            r3 = 13
            if (r0 != r3) goto L_0x0019
            r5.hasCR = r2
            goto L_0x0000
        L_0x0019:
            if (r0 != r4) goto L_0x001c
            goto L_0x0028
        L_0x001c:
            int r0 = r6.position()
            int r0 = r0 - r2
            r6.position(r0)
            r6 = 0
            goto L_0x0045
        L_0x0026:
            if (r0 != r4) goto L_0x002a
        L_0x0028:
            r6 = 1
            goto L_0x0045
        L_0x002a:
            org.apache.mina.filter.codec.ProtocolDecoderException r6 = new org.apache.mina.filter.codec.ProtocolDecoderException
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r1 = "Expected LF after CR but was: "
            r7.append(r1)
            r0 = r0 & 255(0xff, float:3.57E-43)
            r7.append(r0)
            java.lang.String r7 = r7.toString()
            r6.<init>((java.lang.String) r7)
            throw r6
        L_0x0043:
            r6 = 0
            r2 = 0
        L_0x0045:
            if (r2 == 0) goto L_0x004e
            r5.hasCR = r1
            org.apache.mina.filter.codec.statemachine.DecodingState r6 = r5.finishDecode(r6, r7)
            return r6
        L_0x004e:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.mina.filter.codec.statemachine.CrLfDecodingState.decode(org.apache.mina.core.buffer.IoBuffer, org.apache.mina.filter.codec.ProtocolDecoderOutput):org.apache.mina.filter.codec.statemachine.DecodingState");
    }

    public DecodingState finishDecode(ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        return finishDecode(false, protocolDecoderOutput);
    }
}
