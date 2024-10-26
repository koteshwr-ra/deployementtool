package org.apache.log4j.helpers;

import org.apache.log4j.spi.LoggingEvent;

public class BoundedFIFO {
    LoggingEvent[] buf;
    int first = 0;
    int maxSize;
    int next = 0;
    int numElements = 0;

    /* access modifiers changed from: package-private */
    public int min(int i, int i2) {
        return i < i2 ? i : i2;
    }

    public BoundedFIFO(int i) {
        if (i >= 1) {
            this.maxSize = i;
            this.buf = new LoggingEvent[i];
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("The maxSize argument (");
        stringBuffer.append(i);
        stringBuffer.append(") is not a positive integer.");
        throw new IllegalArgumentException(stringBuffer.toString());
    }

    public LoggingEvent get() {
        if (this.numElements == 0) {
            return null;
        }
        LoggingEvent[] loggingEventArr = this.buf;
        int i = this.first;
        LoggingEvent loggingEvent = loggingEventArr[i];
        loggingEventArr[i] = null;
        int i2 = i + 1;
        this.first = i2;
        if (i2 == this.maxSize) {
            this.first = 0;
        }
        this.numElements--;
        return loggingEvent;
    }

    public void put(LoggingEvent loggingEvent) {
        int i = this.numElements;
        int i2 = this.maxSize;
        if (i != i2) {
            LoggingEvent[] loggingEventArr = this.buf;
            int i3 = this.next;
            loggingEventArr[i3] = loggingEvent;
            int i4 = i3 + 1;
            this.next = i4;
            if (i4 == i2) {
                this.next = 0;
            }
            this.numElements++;
        }
    }

    public int getMaxSize() {
        return this.maxSize;
    }

    public boolean isFull() {
        return this.numElements == this.maxSize;
    }

    public int length() {
        return this.numElements;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0046, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void resize(int r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            int r0 = r5.maxSize     // Catch:{ all -> 0x0047 }
            if (r6 != r0) goto L_0x0007
            monitor-exit(r5)
            return
        L_0x0007:
            org.apache.log4j.spi.LoggingEvent[] r0 = new org.apache.log4j.spi.LoggingEvent[r6]     // Catch:{ all -> 0x0047 }
            int r1 = r5.maxSize     // Catch:{ all -> 0x0047 }
            int r2 = r5.first     // Catch:{ all -> 0x0047 }
            int r1 = r1 - r2
            int r1 = r5.min(r1, r6)     // Catch:{ all -> 0x0047 }
            int r2 = r5.numElements     // Catch:{ all -> 0x0047 }
            int r1 = r5.min(r1, r2)     // Catch:{ all -> 0x0047 }
            org.apache.log4j.spi.LoggingEvent[] r2 = r5.buf     // Catch:{ all -> 0x0047 }
            int r3 = r5.first     // Catch:{ all -> 0x0047 }
            r4 = 0
            java.lang.System.arraycopy(r2, r3, r0, r4, r1)     // Catch:{ all -> 0x0047 }
            int r2 = r5.numElements     // Catch:{ all -> 0x0047 }
            if (r1 >= r2) goto L_0x0035
            if (r1 >= r6) goto L_0x0035
            int r2 = r5.numElements     // Catch:{ all -> 0x0047 }
            int r2 = r2 - r1
            int r3 = r6 - r1
            int r2 = r5.min(r2, r3)     // Catch:{ all -> 0x0047 }
            org.apache.log4j.spi.LoggingEvent[] r3 = r5.buf     // Catch:{ all -> 0x0047 }
            java.lang.System.arraycopy(r3, r4, r0, r1, r2)     // Catch:{ all -> 0x0047 }
            goto L_0x0036
        L_0x0035:
            r2 = 0
        L_0x0036:
            r5.buf = r0     // Catch:{ all -> 0x0047 }
            r5.maxSize = r6     // Catch:{ all -> 0x0047 }
            r5.first = r4     // Catch:{ all -> 0x0047 }
            int r1 = r1 + r2
            r5.numElements = r1     // Catch:{ all -> 0x0047 }
            r5.next = r1     // Catch:{ all -> 0x0047 }
            if (r1 != r6) goto L_0x0045
            r5.next = r4     // Catch:{ all -> 0x0047 }
        L_0x0045:
            monitor-exit(r5)
            return
        L_0x0047:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.log4j.helpers.BoundedFIFO.resize(int):void");
    }

    public boolean wasEmpty() {
        return this.numElements == 1;
    }

    public boolean wasFull() {
        return this.numElements + 1 == this.maxSize;
    }
}
