package org.apache.mina.filter.codec.statemachine;

import java.util.ArrayList;
import java.util.List;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DecodingStateMachine implements DecodingState {
    private final ProtocolDecoderOutput childOutput = new ProtocolDecoderOutput() {
        public void flush(IoFilter.NextFilter nextFilter, IoSession ioSession) {
        }

        public void write(Object obj) {
            DecodingStateMachine.this.childProducts.add(obj);
        }
    };
    /* access modifiers changed from: private */
    public final List<Object> childProducts = new ArrayList();
    private DecodingState currentState;
    private boolean initialized;
    private final Logger log = LoggerFactory.getLogger(DecodingStateMachine.class);

    /* access modifiers changed from: protected */
    public abstract void destroy() throws Exception;

    /* access modifiers changed from: protected */
    public abstract DecodingState finishDecode(List<Object> list, ProtocolDecoderOutput protocolDecoderOutput) throws Exception;

    /* access modifiers changed from: protected */
    public abstract DecodingState init() throws Exception;

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0024, code lost:
        return r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:?, code lost:
        r6 = finishDecode(r5.childProducts, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001d, code lost:
        r5.currentState = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001f, code lost:
        if (r3 != null) goto L_0x0024;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0021, code lost:
        cleanup();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.mina.filter.codec.statemachine.DecodingState decode(org.apache.mina.core.buffer.IoBuffer r6, org.apache.mina.filter.codec.ProtocolDecoderOutput r7) throws java.lang.Exception {
        /*
            r5 = this;
            org.apache.mina.filter.codec.statemachine.DecodingState r0 = r5.getCurrentState()
            int r1 = r6.limit()
            int r2 = r6.position()
        L_0x000c:
            if (r2 != r1) goto L_0x000f
            goto L_0x002e
        L_0x000f:
            org.apache.mina.filter.codec.ProtocolDecoderOutput r3 = r5.childOutput     // Catch:{ Exception -> 0x003e }
            org.apache.mina.filter.codec.statemachine.DecodingState r3 = r0.decode(r6, r3)     // Catch:{ Exception -> 0x003e }
            if (r3 != 0) goto L_0x0025
            java.util.List<java.lang.Object> r6 = r5.childProducts     // Catch:{ Exception -> 0x003e, all -> 0x0039 }
            org.apache.mina.filter.codec.statemachine.DecodingState r6 = r5.finishDecode(r6, r7)     // Catch:{ Exception -> 0x003e, all -> 0x0039 }
            r5.currentState = r3
            if (r3 != 0) goto L_0x0024
            r5.cleanup()
        L_0x0024:
            return r6
        L_0x0025:
            int r4 = r6.position()     // Catch:{ Exception -> 0x003e, all -> 0x0039 }
            if (r4 != r2) goto L_0x0036
            if (r0 != r3) goto L_0x0036
            r0 = r3
        L_0x002e:
            r5.currentState = r0
            if (r0 != 0) goto L_0x0035
            r5.cleanup()
        L_0x0035:
            return r5
        L_0x0036:
            r0 = r3
            r2 = r4
            goto L_0x000c
        L_0x0039:
            r6 = move-exception
            r0 = r3
            goto L_0x0041
        L_0x003c:
            r6 = move-exception
            goto L_0x0041
        L_0x003e:
            r6 = move-exception
            r0 = 0
            throw r6     // Catch:{ all -> 0x003c }
        L_0x0041:
            r5.currentState = r0
            if (r0 != 0) goto L_0x0048
            r5.cleanup()
        L_0x0048:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.mina.filter.codec.statemachine.DecodingStateMachine.decode(org.apache.mina.core.buffer.IoBuffer, org.apache.mina.filter.codec.ProtocolDecoderOutput):org.apache.mina.filter.codec.statemachine.DecodingState");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0017, code lost:
        if (r1 == null) goto L_0x0019;
     */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0041  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.mina.filter.codec.statemachine.DecodingState finishDecode(org.apache.mina.filter.codec.ProtocolDecoderOutput r6) throws java.lang.Exception {
        /*
            r5 = this;
            org.apache.mina.filter.codec.statemachine.DecodingState r0 = r5.getCurrentState()
        L_0x0004:
            org.apache.mina.filter.codec.ProtocolDecoderOutput r1 = r5.childOutput     // Catch:{ Exception -> 0x0021, all -> 0x001f }
            org.apache.mina.filter.codec.statemachine.DecodingState r1 = r0.finishDecode(r1)     // Catch:{ Exception -> 0x0021, all -> 0x001f }
            if (r1 != 0) goto L_0x000d
            goto L_0x000f
        L_0x000d:
            if (r0 != r1) goto L_0x001d
        L_0x000f:
            r5.currentState = r1
            java.util.List<java.lang.Object> r0 = r5.childProducts
            org.apache.mina.filter.codec.statemachine.DecodingState r6 = r5.finishDecode(r0, r6)
            if (r1 != 0) goto L_0x0033
        L_0x0019:
            r5.cleanup()
            goto L_0x0033
        L_0x001d:
            r0 = r1
            goto L_0x0004
        L_0x001f:
            r1 = move-exception
            goto L_0x0038
        L_0x0021:
            r0 = move-exception
            r1 = 0
            org.slf4j.Logger r2 = r5.log     // Catch:{ all -> 0x0034 }
            java.lang.String r3 = "Ignoring the exception caused by a closed session."
            r2.debug((java.lang.String) r3, (java.lang.Throwable) r0)     // Catch:{ all -> 0x0034 }
            r5.currentState = r1
            java.util.List<java.lang.Object> r0 = r5.childProducts
            org.apache.mina.filter.codec.statemachine.DecodingState r6 = r5.finishDecode(r0, r6)
            goto L_0x0019
        L_0x0033:
            return r6
        L_0x0034:
            r0 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
        L_0x0038:
            r5.currentState = r0
            java.util.List<java.lang.Object> r2 = r5.childProducts
            r5.finishDecode(r2, r6)
            if (r0 != 0) goto L_0x0044
            r5.cleanup()
        L_0x0044:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.mina.filter.codec.statemachine.DecodingStateMachine.finishDecode(org.apache.mina.filter.codec.ProtocolDecoderOutput):org.apache.mina.filter.codec.statemachine.DecodingState");
    }

    private void cleanup() {
        if (this.initialized) {
            this.initialized = false;
            this.childProducts.clear();
            try {
                destroy();
            } catch (Exception e) {
                this.log.warn("Failed to destroy a decoding state machine.", (Throwable) e);
            }
        } else {
            throw new IllegalStateException();
        }
    }

    private DecodingState getCurrentState() throws Exception {
        DecodingState decodingState = this.currentState;
        if (decodingState != null) {
            return decodingState;
        }
        DecodingState init = init();
        this.initialized = true;
        return init;
    }
}
