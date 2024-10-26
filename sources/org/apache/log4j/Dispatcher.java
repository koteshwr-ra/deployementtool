package org.apache.log4j;

import org.apache.log4j.helpers.AppenderAttachableImpl;
import org.apache.log4j.helpers.BoundedFIFO;

class Dispatcher extends Thread {
    private AppenderAttachableImpl aai;
    private BoundedFIFO bf;
    AsyncAppender container;
    private boolean interrupted = false;

    Dispatcher(BoundedFIFO boundedFIFO, AsyncAppender asyncAppender) {
        this.bf = boundedFIFO;
        this.container = asyncAppender;
        this.aai = asyncAppender.aai;
        setDaemon(true);
        setPriority(1);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Dispatcher-");
        stringBuffer.append(getName());
        setName(stringBuffer.toString());
    }

    /* access modifiers changed from: package-private */
    public void close() {
        synchronized (this.bf) {
            this.interrupted = true;
            if (this.bf.length() == 0) {
                this.bf.notify();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0032, code lost:
        r0 = r3.container.aai;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0036, code lost:
        monitor-enter(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0039, code lost:
        if (r3.aai == null) goto L_0x0042;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x003b, code lost:
        if (r1 == null) goto L_0x0042;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x003d, code lost:
        r3.aai.appendLoopOnAppenders(r1);
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0017 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r3 = this;
        L_0x0000:
            org.apache.log4j.helpers.BoundedFIFO r0 = r3.bf
            monitor-enter(r0)
            org.apache.log4j.helpers.BoundedFIFO r1 = r3.bf     // Catch:{ all -> 0x0047 }
            int r1 = r1.length()     // Catch:{ all -> 0x0047 }
            if (r1 != 0) goto L_0x001e
            boolean r1 = r3.interrupted     // Catch:{ all -> 0x0047 }
            if (r1 == 0) goto L_0x0011
            monitor-exit(r0)     // Catch:{ all -> 0x0047 }
            goto L_0x0018
        L_0x0011:
            org.apache.log4j.helpers.BoundedFIFO r1 = r3.bf     // Catch:{ InterruptedException -> 0x0017 }
            r1.wait()     // Catch:{ InterruptedException -> 0x0017 }
            goto L_0x001e
        L_0x0017:
            monitor-exit(r0)     // Catch:{ all -> 0x0047 }
        L_0x0018:
            org.apache.log4j.helpers.AppenderAttachableImpl r0 = r3.aai
            r0.removeAllAppenders()
            return
        L_0x001e:
            org.apache.log4j.helpers.BoundedFIFO r1 = r3.bf     // Catch:{ all -> 0x0047 }
            org.apache.log4j.spi.LoggingEvent r1 = r1.get()     // Catch:{ all -> 0x0047 }
            org.apache.log4j.helpers.BoundedFIFO r2 = r3.bf     // Catch:{ all -> 0x0047 }
            boolean r2 = r2.wasFull()     // Catch:{ all -> 0x0047 }
            if (r2 == 0) goto L_0x0031
            org.apache.log4j.helpers.BoundedFIFO r2 = r3.bf     // Catch:{ all -> 0x0047 }
            r2.notify()     // Catch:{ all -> 0x0047 }
        L_0x0031:
            monitor-exit(r0)     // Catch:{ all -> 0x0047 }
            org.apache.log4j.AsyncAppender r0 = r3.container
            org.apache.log4j.helpers.AppenderAttachableImpl r0 = r0.aai
            monitor-enter(r0)
            org.apache.log4j.helpers.AppenderAttachableImpl r2 = r3.aai     // Catch:{ all -> 0x0044 }
            if (r2 == 0) goto L_0x0042
            if (r1 == 0) goto L_0x0042
            org.apache.log4j.helpers.AppenderAttachableImpl r2 = r3.aai     // Catch:{ all -> 0x0044 }
            r2.appendLoopOnAppenders(r1)     // Catch:{ all -> 0x0044 }
        L_0x0042:
            monitor-exit(r0)     // Catch:{ all -> 0x0044 }
            goto L_0x0000
        L_0x0044:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        L_0x0047:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.log4j.Dispatcher.run():void");
    }
}
