package org.apache.mina.core.service;

import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import org.apache.mina.core.session.AbstractIoSession;
import org.apache.mina.core.session.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleIoProcessorPool<S extends AbstractIoSession> implements IoProcessor<S> {
    private static final int DEFAULT_SIZE = (Runtime.getRuntime().availableProcessors() + 1);
    private static final Logger LOGGER;
    private static final AttributeKey PROCESSOR;
    private final boolean createdExecutor;
    private final Object disposalLock;
    private volatile boolean disposed;
    private volatile boolean disposing;
    private final Executor executor;
    private final IoProcessor<S>[] pool;

    static {
        Class<SimpleIoProcessorPool> cls = SimpleIoProcessorPool.class;
        LOGGER = LoggerFactory.getLogger((Class) cls);
        PROCESSOR = new AttributeKey(cls, "processor");
    }

    public SimpleIoProcessorPool(Class<? extends IoProcessor<S>> cls) {
        this(cls, (Executor) null, DEFAULT_SIZE);
    }

    public SimpleIoProcessorPool(Class<? extends IoProcessor<S>> cls, int i) {
        this(cls, (Executor) null, i);
    }

    public SimpleIoProcessorPool(Class<? extends IoProcessor<S>> cls, Executor executor2) {
        this(cls, executor2, DEFAULT_SIZE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0042, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0045, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0047, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r6 = "Failed to create a new instance of " + r6.getName() + ":" + r7.getMessage();
        LOGGER.error(r6, (java.lang.Throwable) r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00a3, code lost:
        throw new org.apache.mina.core.RuntimeIoException(r6, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00a4, code lost:
        LOGGER.error("Cannot create an IoProcessor :{}", (java.lang.Object) r6.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00af, code lost:
        throw r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0118, code lost:
        dispose();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x011b, code lost:
        throw r6;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0049 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0064 */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0045 A[ExcHandler: Exception (r7v16 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:17:0x0049] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0047 A[ExcHandler: RuntimeException (r6v15 'e' java.lang.RuntimeException A[CUSTOM_DECLARE]), Splitter:B:12:0x0027] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00b3 A[Catch:{ NoSuchMethodException -> 0x0076, RuntimeException -> 0x0047, Exception -> 0x0045, all -> 0x0042 }] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00dc A[SYNTHETIC, Splitter:B:43:0x00dc] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SimpleIoProcessorPool(java.lang.Class<? extends org.apache.mina.core.service.IoProcessor<S>> r6, java.util.concurrent.Executor r7, int r8) {
        /*
            r5 = this;
            r5.<init>()
            java.lang.Object r0 = new java.lang.Object
            r0.<init>()
            r5.disposalLock = r0
            if (r6 == 0) goto L_0x0138
            if (r8 <= 0) goto L_0x011c
            r0 = 1
            r1 = 0
            if (r7 != 0) goto L_0x0014
            r2 = 1
            goto L_0x0015
        L_0x0014:
            r2 = 0
        L_0x0015:
            r5.createdExecutor = r2
            if (r2 == 0) goto L_0x0020
            java.util.concurrent.ExecutorService r7 = java.util.concurrent.Executors.newCachedThreadPool()
            r5.executor = r7
            goto L_0x0022
        L_0x0020:
            r5.executor = r7
        L_0x0022:
            org.apache.mina.core.service.IoProcessor[] r7 = new org.apache.mina.core.service.IoProcessor[r8]
            r5.pool = r7
            r7 = 0
            java.lang.Class[] r8 = new java.lang.Class[r0]     // Catch:{ NoSuchMethodException -> 0x0049, RuntimeException -> 0x0047, Exception -> 0x0045 }
            java.lang.Class<java.util.concurrent.ExecutorService> r2 = java.util.concurrent.ExecutorService.class
            r8[r1] = r2     // Catch:{ NoSuchMethodException -> 0x0049, RuntimeException -> 0x0047, Exception -> 0x0045 }
            java.lang.reflect.Constructor r7 = r6.getConstructor(r8)     // Catch:{ NoSuchMethodException -> 0x0049, RuntimeException -> 0x0047, Exception -> 0x0045 }
            org.apache.mina.core.service.IoProcessor<S>[] r8 = r5.pool     // Catch:{ NoSuchMethodException -> 0x0049, RuntimeException -> 0x0047, Exception -> 0x0045 }
            java.lang.Object[] r2 = new java.lang.Object[r0]     // Catch:{ NoSuchMethodException -> 0x0049, RuntimeException -> 0x0047, Exception -> 0x0045 }
            java.util.concurrent.Executor r3 = r5.executor     // Catch:{ NoSuchMethodException -> 0x0049, RuntimeException -> 0x0047, Exception -> 0x0045 }
            r2[r1] = r3     // Catch:{ NoSuchMethodException -> 0x0049, RuntimeException -> 0x0047, Exception -> 0x0045 }
            java.lang.Object r2 = r7.newInstance(r2)     // Catch:{ NoSuchMethodException -> 0x0049, RuntimeException -> 0x0047, Exception -> 0x0045 }
            org.apache.mina.core.service.IoProcessor r2 = (org.apache.mina.core.service.IoProcessor) r2     // Catch:{ NoSuchMethodException -> 0x0049, RuntimeException -> 0x0047, Exception -> 0x0045 }
            r8[r1] = r2     // Catch:{ NoSuchMethodException -> 0x0049, RuntimeException -> 0x0047, Exception -> 0x0045 }
            goto L_0x00b0
        L_0x0042:
            r6 = move-exception
            goto L_0x0118
        L_0x0045:
            r7 = move-exception
            goto L_0x0078
        L_0x0047:
            r6 = move-exception
            goto L_0x00a4
        L_0x0049:
            java.lang.Class[] r8 = new java.lang.Class[r0]     // Catch:{ NoSuchMethodException -> 0x0064, RuntimeException -> 0x0047, Exception -> 0x0045 }
            java.lang.Class<java.util.concurrent.Executor> r2 = java.util.concurrent.Executor.class
            r8[r1] = r2     // Catch:{ NoSuchMethodException -> 0x0064, RuntimeException -> 0x0047, Exception -> 0x0045 }
            java.lang.reflect.Constructor r7 = r6.getConstructor(r8)     // Catch:{ NoSuchMethodException -> 0x0064, RuntimeException -> 0x0047, Exception -> 0x0045 }
            org.apache.mina.core.service.IoProcessor<S>[] r8 = r5.pool     // Catch:{ NoSuchMethodException -> 0x0064, RuntimeException -> 0x0047, Exception -> 0x0045 }
            java.lang.Object[] r2 = new java.lang.Object[r0]     // Catch:{ NoSuchMethodException -> 0x0064, RuntimeException -> 0x0047, Exception -> 0x0045 }
            java.util.concurrent.Executor r3 = r5.executor     // Catch:{ NoSuchMethodException -> 0x0064, RuntimeException -> 0x0047, Exception -> 0x0045 }
            r2[r1] = r3     // Catch:{ NoSuchMethodException -> 0x0064, RuntimeException -> 0x0047, Exception -> 0x0045 }
            java.lang.Object r2 = r7.newInstance(r2)     // Catch:{ NoSuchMethodException -> 0x0064, RuntimeException -> 0x0047, Exception -> 0x0045 }
            org.apache.mina.core.service.IoProcessor r2 = (org.apache.mina.core.service.IoProcessor) r2     // Catch:{ NoSuchMethodException -> 0x0064, RuntimeException -> 0x0047, Exception -> 0x0045 }
            r8[r1] = r2     // Catch:{ NoSuchMethodException -> 0x0064, RuntimeException -> 0x0047, Exception -> 0x0045 }
            goto L_0x00b0
        L_0x0064:
            java.lang.Class[] r8 = new java.lang.Class[r1]     // Catch:{ NoSuchMethodException -> 0x00b0, RuntimeException -> 0x0047, Exception -> 0x0045 }
            java.lang.reflect.Constructor r7 = r6.getConstructor(r8)     // Catch:{ NoSuchMethodException -> 0x00b0, RuntimeException -> 0x0047, Exception -> 0x0045 }
            org.apache.mina.core.service.IoProcessor<S>[] r8 = r5.pool     // Catch:{ NoSuchMethodException -> 0x0076, RuntimeException -> 0x0047, Exception -> 0x0045 }
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch:{ NoSuchMethodException -> 0x0076, RuntimeException -> 0x0047, Exception -> 0x0045 }
            java.lang.Object r2 = r7.newInstance(r2)     // Catch:{ NoSuchMethodException -> 0x0076, RuntimeException -> 0x0047, Exception -> 0x0045 }
            org.apache.mina.core.service.IoProcessor r2 = (org.apache.mina.core.service.IoProcessor) r2     // Catch:{ NoSuchMethodException -> 0x0076, RuntimeException -> 0x0047, Exception -> 0x0045 }
            r8[r1] = r2     // Catch:{ NoSuchMethodException -> 0x0076, RuntimeException -> 0x0047, Exception -> 0x0045 }
        L_0x0076:
            r8 = 0
            goto L_0x00b1
        L_0x0078:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x0042 }
            r8.<init>()     // Catch:{ all -> 0x0042 }
            java.lang.String r0 = "Failed to create a new instance of "
            r8.append(r0)     // Catch:{ all -> 0x0042 }
            java.lang.String r6 = r6.getName()     // Catch:{ all -> 0x0042 }
            r8.append(r6)     // Catch:{ all -> 0x0042 }
            java.lang.String r6 = ":"
            r8.append(r6)     // Catch:{ all -> 0x0042 }
            java.lang.String r6 = r7.getMessage()     // Catch:{ all -> 0x0042 }
            r8.append(r6)     // Catch:{ all -> 0x0042 }
            java.lang.String r6 = r8.toString()     // Catch:{ all -> 0x0042 }
            org.slf4j.Logger r8 = LOGGER     // Catch:{ all -> 0x0042 }
            r8.error((java.lang.String) r6, (java.lang.Throwable) r7)     // Catch:{ all -> 0x0042 }
            org.apache.mina.core.RuntimeIoException r8 = new org.apache.mina.core.RuntimeIoException     // Catch:{ all -> 0x0042 }
            r8.<init>(r6, r7)     // Catch:{ all -> 0x0042 }
            throw r8     // Catch:{ all -> 0x0042 }
        L_0x00a4:
            org.slf4j.Logger r7 = LOGGER     // Catch:{ all -> 0x0042 }
            java.lang.String r8 = "Cannot create an IoProcessor :{}"
            java.lang.String r0 = r6.getMessage()     // Catch:{ all -> 0x0042 }
            r7.error((java.lang.String) r8, (java.lang.Object) r0)     // Catch:{ all -> 0x0042 }
            throw r6     // Catch:{ all -> 0x0042 }
        L_0x00b0:
            r8 = 1
        L_0x00b1:
            if (r7 == 0) goto L_0x00dc
            r6 = 1
        L_0x00b4:
            org.apache.mina.core.service.IoProcessor<S>[] r2 = r5.pool     // Catch:{ all -> 0x0042 }
            int r2 = r2.length     // Catch:{ all -> 0x0042 }
            if (r6 >= r2) goto L_0x00db
            if (r8 == 0) goto L_0x00cc
            org.apache.mina.core.service.IoProcessor<S>[] r2 = r5.pool     // Catch:{ Exception -> 0x00d8 }
            java.lang.Object[] r3 = new java.lang.Object[r0]     // Catch:{ Exception -> 0x00d8 }
            java.util.concurrent.Executor r4 = r5.executor     // Catch:{ Exception -> 0x00d8 }
            r3[r1] = r4     // Catch:{ Exception -> 0x00d8 }
            java.lang.Object r3 = r7.newInstance(r3)     // Catch:{ Exception -> 0x00d8 }
            org.apache.mina.core.service.IoProcessor r3 = (org.apache.mina.core.service.IoProcessor) r3     // Catch:{ Exception -> 0x00d8 }
            r2[r6] = r3     // Catch:{ Exception -> 0x00d8 }
            goto L_0x00d8
        L_0x00cc:
            org.apache.mina.core.service.IoProcessor<S>[] r2 = r5.pool     // Catch:{ Exception -> 0x00d8 }
            java.lang.Object[] r3 = new java.lang.Object[r1]     // Catch:{ Exception -> 0x00d8 }
            java.lang.Object r3 = r7.newInstance(r3)     // Catch:{ Exception -> 0x00d8 }
            org.apache.mina.core.service.IoProcessor r3 = (org.apache.mina.core.service.IoProcessor) r3     // Catch:{ Exception -> 0x00d8 }
            r2[r6] = r3     // Catch:{ Exception -> 0x00d8 }
        L_0x00d8:
            int r6 = r6 + 1
            goto L_0x00b4
        L_0x00db:
            return
        L_0x00dc:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x0042 }
            r7.<init>()     // Catch:{ all -> 0x0042 }
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x0042 }
            r7.append(r6)     // Catch:{ all -> 0x0042 }
            java.lang.String r6 = " must have a public constructor with one "
            r7.append(r6)     // Catch:{ all -> 0x0042 }
            java.lang.Class<java.util.concurrent.ExecutorService> r6 = java.util.concurrent.ExecutorService.class
            java.lang.String r6 = r6.getSimpleName()     // Catch:{ all -> 0x0042 }
            r7.append(r6)     // Catch:{ all -> 0x0042 }
            java.lang.String r6 = " parameter, a public constructor with one "
            r7.append(r6)     // Catch:{ all -> 0x0042 }
            java.lang.Class<java.util.concurrent.Executor> r6 = java.util.concurrent.Executor.class
            java.lang.String r6 = r6.getSimpleName()     // Catch:{ all -> 0x0042 }
            r7.append(r6)     // Catch:{ all -> 0x0042 }
            java.lang.String r6 = " parameter or a public default constructor."
            r7.append(r6)     // Catch:{ all -> 0x0042 }
            java.lang.String r6 = r7.toString()     // Catch:{ all -> 0x0042 }
            org.slf4j.Logger r7 = LOGGER     // Catch:{ all -> 0x0042 }
            r7.error(r6)     // Catch:{ all -> 0x0042 }
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x0042 }
            r7.<init>(r6)     // Catch:{ all -> 0x0042 }
            throw r7     // Catch:{ all -> 0x0042 }
        L_0x0118:
            r5.dispose()
            throw r6
        L_0x011c:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r0 = "size: "
            r7.append(r0)
            r7.append(r8)
            java.lang.String r8 = " (expected: positive integer)"
            r7.append(r8)
            java.lang.String r7 = r7.toString()
            r6.<init>(r7)
            throw r6
        L_0x0138:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r7 = "processorType"
            r6.<init>(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.mina.core.service.SimpleIoProcessorPool.<init>(java.lang.Class, java.util.concurrent.Executor, int):void");
    }

    public final void add(S s) {
        getProcessor(s).add(s);
    }

    public final void flush(S s) {
        getProcessor(s).flush(s);
    }

    public final void remove(S s) {
        getProcessor(s).remove(s);
    }

    public final void updateTrafficControl(S s) {
        getProcessor(s).updateTrafficControl(s);
    }

    public boolean isDisposed() {
        return this.disposed;
    }

    public boolean isDisposing() {
        return this.disposing;
    }

    public final void dispose() {
        if (!this.disposed) {
            synchronized (this.disposalLock) {
                if (!this.disposing) {
                    this.disposing = true;
                    for (IoProcessor<S> ioProcessor : this.pool) {
                        if (ioProcessor != null) {
                            if (ioProcessor.isDisposing()) {
                                continue;
                            } else {
                                try {
                                    ioProcessor.dispose();
                                } catch (Exception e) {
                                    LOGGER.warn("Failed to dispose the {} IoProcessor.", (Object) ioProcessor.getClass().getSimpleName(), (Object) e);
                                }
                            }
                        }
                    }
                    if (this.createdExecutor) {
                        ((ExecutorService) this.executor).shutdown();
                    }
                }
                Arrays.fill(this.pool, (Object) null);
                this.disposed = true;
            }
        }
    }

    private IoProcessor<S> getProcessor(S s) {
        IoProcessor<S> ioProcessor = (IoProcessor) s.getAttribute(PROCESSOR);
        if (ioProcessor == null) {
            if (this.disposed || this.disposing) {
                throw new IllegalStateException("A disposed processor cannot be accessed.");
            }
            ioProcessor = this.pool[Math.abs((int) s.getId()) % this.pool.length];
            if (ioProcessor != null) {
                s.setAttributeIfAbsent(PROCESSOR, ioProcessor);
            } else {
                throw new IllegalStateException("A disposed processor cannot be accessed.");
            }
        }
        return ioProcessor;
    }
}
