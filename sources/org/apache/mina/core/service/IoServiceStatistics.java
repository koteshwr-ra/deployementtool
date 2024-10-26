package org.apache.mina.core.service;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class IoServiceStatistics {
    private double largestReadBytesThroughput;
    private double largestReadMessagesThroughput;
    private double largestWrittenBytesThroughput;
    private double largestWrittenMessagesThroughput;
    private long lastReadBytes;
    private long lastReadMessages;
    private long lastReadTime;
    private long lastThroughputCalculationTime;
    private long lastWriteTime;
    private long lastWrittenBytes;
    private long lastWrittenMessages;
    private final AtomicLong readBytes = new AtomicLong();
    private double readBytesThroughput;
    private final AtomicLong readMessages = new AtomicLong();
    private double readMessagesThroughput;
    private final AtomicInteger scheduledWriteBytes = new AtomicInteger();
    private final AtomicInteger scheduledWriteMessages = new AtomicInteger();
    private AbstractIoService service;
    private int throughputCalculationInterval = 3;
    private final Object throughputCalculationLock = new Object();
    private final AtomicLong writtenBytes = new AtomicLong();
    private double writtenBytesThroughput;
    private final AtomicLong writtenMessages = new AtomicLong();
    private double writtenMessagesThroughput;

    public IoServiceStatistics(AbstractIoService abstractIoService) {
        this.service = abstractIoService;
    }

    public final int getLargestManagedSessionCount() {
        return this.service.getListeners().getLargestManagedSessionCount();
    }

    public final long getCumulativeManagedSessionCount() {
        return this.service.getListeners().getCumulativeManagedSessionCount();
    }

    public final long getLastIoTime() {
        return Math.max(this.lastReadTime, this.lastWriteTime);
    }

    public final long getLastReadTime() {
        return this.lastReadTime;
    }

    public final long getLastWriteTime() {
        return this.lastWriteTime;
    }

    public final long getReadBytes() {
        return this.readBytes.get();
    }

    public final long getWrittenBytes() {
        return this.writtenBytes.get();
    }

    public final long getReadMessages() {
        return this.readMessages.get();
    }

    public final long getWrittenMessages() {
        return this.writtenMessages.get();
    }

    public final double getReadBytesThroughput() {
        resetThroughput();
        return this.readBytesThroughput;
    }

    public final double getWrittenBytesThroughput() {
        resetThroughput();
        return this.writtenBytesThroughput;
    }

    public final double getReadMessagesThroughput() {
        resetThroughput();
        return this.readMessagesThroughput;
    }

    public final double getWrittenMessagesThroughput() {
        resetThroughput();
        return this.writtenMessagesThroughput;
    }

    public final double getLargestReadBytesThroughput() {
        return this.largestReadBytesThroughput;
    }

    public final double getLargestWrittenBytesThroughput() {
        return this.largestWrittenBytesThroughput;
    }

    public final double getLargestReadMessagesThroughput() {
        return this.largestReadMessagesThroughput;
    }

    public final double getLargestWrittenMessagesThroughput() {
        return this.largestWrittenMessagesThroughput;
    }

    public final int getThroughputCalculationInterval() {
        return this.throughputCalculationInterval;
    }

    public final long getThroughputCalculationIntervalInMillis() {
        return ((long) this.throughputCalculationInterval) * 1000;
    }

    public final void setThroughputCalculationInterval(int i) {
        if (i >= 0) {
            this.throughputCalculationInterval = i;
            return;
        }
        throw new IllegalArgumentException("throughputCalculationInterval: " + i);
    }

    /* access modifiers changed from: protected */
    public final void setLastReadTime(long j) {
        this.lastReadTime = j;
    }

    /* access modifiers changed from: protected */
    public final void setLastWriteTime(long j) {
        this.lastWriteTime = j;
    }

    private void resetThroughput() {
        if (this.service.getManagedSessionCount() == 0) {
            this.readBytesThroughput = 0.0d;
            this.writtenBytesThroughput = 0.0d;
            this.readMessagesThroughput = 0.0d;
            this.writtenMessagesThroughput = 0.0d;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00a2, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateThroughput(long r20) {
        /*
            r19 = this;
            r1 = r19
            r2 = r20
            java.lang.Object r4 = r1.throughputCalculationLock
            monitor-enter(r4)
            long r5 = r1.lastThroughputCalculationTime     // Catch:{ all -> 0x00a3 }
            long r5 = r2 - r5
            int r0 = (int) r5     // Catch:{ all -> 0x00a3 }
            long r5 = r19.getThroughputCalculationIntervalInMillis()     // Catch:{ all -> 0x00a3 }
            r7 = 0
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 == 0) goto L_0x00a1
            long r7 = (long) r0     // Catch:{ all -> 0x00a3 }
            int r9 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r9 >= 0) goto L_0x001d
            goto L_0x00a1
        L_0x001d:
            java.util.concurrent.atomic.AtomicLong r5 = r1.readBytes     // Catch:{ all -> 0x00a3 }
            long r5 = r5.get()     // Catch:{ all -> 0x00a3 }
            java.util.concurrent.atomic.AtomicLong r7 = r1.writtenBytes     // Catch:{ all -> 0x00a3 }
            long r7 = r7.get()     // Catch:{ all -> 0x00a3 }
            java.util.concurrent.atomic.AtomicLong r9 = r1.readMessages     // Catch:{ all -> 0x00a3 }
            long r9 = r9.get()     // Catch:{ all -> 0x00a3 }
            java.util.concurrent.atomic.AtomicLong r11 = r1.writtenMessages     // Catch:{ all -> 0x00a3 }
            long r11 = r11.get()     // Catch:{ all -> 0x00a3 }
            long r13 = r1.lastReadBytes     // Catch:{ all -> 0x00a3 }
            long r13 = r5 - r13
            double r13 = (double) r13     // Catch:{ all -> 0x00a3 }
            r15 = 4652007308841189376(0x408f400000000000, double:1000.0)
            double r13 = r13 * r15
            double r2 = (double) r0     // Catch:{ all -> 0x00a3 }
            double r13 = r13 / r2
            r1.readBytesThroughput = r13     // Catch:{ all -> 0x00a3 }
            r17 = r5
            long r5 = r1.lastWrittenBytes     // Catch:{ all -> 0x00a3 }
            long r5 = r7 - r5
            double r5 = (double) r5     // Catch:{ all -> 0x00a3 }
            double r5 = r5 * r15
            double r5 = r5 / r2
            r1.writtenBytesThroughput = r5     // Catch:{ all -> 0x00a3 }
            long r5 = r1.lastReadMessages     // Catch:{ all -> 0x00a3 }
            long r5 = r9 - r5
            double r5 = (double) r5     // Catch:{ all -> 0x00a3 }
            double r5 = r5 * r15
            double r5 = r5 / r2
            r1.readMessagesThroughput = r5     // Catch:{ all -> 0x00a3 }
            long r5 = r1.lastWrittenMessages     // Catch:{ all -> 0x00a3 }
            long r5 = r11 - r5
            double r5 = (double) r5     // Catch:{ all -> 0x00a3 }
            double r5 = r5 * r15
            double r5 = r5 / r2
            r1.writtenMessagesThroughput = r5     // Catch:{ all -> 0x00a3 }
            double r2 = r1.largestReadBytesThroughput     // Catch:{ all -> 0x00a3 }
            int r0 = (r13 > r2 ? 1 : (r13 == r2 ? 0 : -1))
            if (r0 <= 0) goto L_0x006d
            r1.largestReadBytesThroughput = r13     // Catch:{ all -> 0x00a3 }
        L_0x006d:
            double r2 = r1.writtenBytesThroughput     // Catch:{ all -> 0x00a3 }
            double r5 = r1.largestWrittenBytesThroughput     // Catch:{ all -> 0x00a3 }
            int r0 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r0 <= 0) goto L_0x0079
            double r2 = r1.writtenBytesThroughput     // Catch:{ all -> 0x00a3 }
            r1.largestWrittenBytesThroughput = r2     // Catch:{ all -> 0x00a3 }
        L_0x0079:
            double r2 = r1.readMessagesThroughput     // Catch:{ all -> 0x00a3 }
            double r5 = r1.largestReadMessagesThroughput     // Catch:{ all -> 0x00a3 }
            int r0 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r0 <= 0) goto L_0x0085
            double r2 = r1.readMessagesThroughput     // Catch:{ all -> 0x00a3 }
            r1.largestReadMessagesThroughput = r2     // Catch:{ all -> 0x00a3 }
        L_0x0085:
            double r2 = r1.writtenMessagesThroughput     // Catch:{ all -> 0x00a3 }
            double r5 = r1.largestWrittenMessagesThroughput     // Catch:{ all -> 0x00a3 }
            int r0 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r0 <= 0) goto L_0x0091
            double r2 = r1.writtenMessagesThroughput     // Catch:{ all -> 0x00a3 }
            r1.largestWrittenMessagesThroughput = r2     // Catch:{ all -> 0x00a3 }
        L_0x0091:
            r2 = r17
            r1.lastReadBytes = r2     // Catch:{ all -> 0x00a3 }
            r1.lastWrittenBytes = r7     // Catch:{ all -> 0x00a3 }
            r1.lastReadMessages = r9     // Catch:{ all -> 0x00a3 }
            r1.lastWrittenMessages = r11     // Catch:{ all -> 0x00a3 }
            r2 = r20
            r1.lastThroughputCalculationTime = r2     // Catch:{ all -> 0x00a3 }
            monitor-exit(r4)     // Catch:{ all -> 0x00a3 }
            return
        L_0x00a1:
            monitor-exit(r4)     // Catch:{ all -> 0x00a3 }
            return
        L_0x00a3:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x00a3 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.mina.core.service.IoServiceStatistics.updateThroughput(long):void");
    }

    public final void increaseReadBytes(long j, long j2) {
        this.readBytes.addAndGet(j);
        this.lastReadTime = j2;
    }

    public final void increaseReadMessages(long j) {
        this.readMessages.incrementAndGet();
        this.lastReadTime = j;
    }

    public final void increaseWrittenBytes(int i, long j) {
        this.writtenBytes.addAndGet((long) i);
        this.lastWriteTime = j;
    }

    public final void increaseWrittenMessages(long j) {
        this.writtenMessages.incrementAndGet();
        this.lastWriteTime = j;
    }

    public final int getScheduledWriteBytes() {
        return this.scheduledWriteBytes.get();
    }

    public final void increaseScheduledWriteBytes(int i) {
        this.scheduledWriteBytes.addAndGet(i);
    }

    public final int getScheduledWriteMessages() {
        return this.scheduledWriteMessages.get();
    }

    public final void increaseScheduledWriteMessages() {
        this.scheduledWriteMessages.incrementAndGet();
    }

    public final void decreaseScheduledWriteMessages() {
        this.scheduledWriteMessages.decrementAndGet();
    }

    /* access modifiers changed from: protected */
    public void setLastThroughputCalculationTime(long j) {
        this.lastThroughputCalculationTime = j;
    }
}
