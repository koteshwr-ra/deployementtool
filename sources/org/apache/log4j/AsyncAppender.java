package org.apache.log4j;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.helpers.AppenderAttachableImpl;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.AppenderAttachable;
import org.apache.log4j.spi.LoggingEvent;

public class AsyncAppender extends AppenderSkeleton implements AppenderAttachable {
    public static final int DEFAULT_BUFFER_SIZE = 128;
    AppenderAttachableImpl aai;
    private final AppenderAttachableImpl appenders;
    private boolean blocking = true;
    private final List buffer = new ArrayList();
    private int bufferSize = 128;
    private final Map discardMap = new HashMap();
    private final Thread dispatcher;
    private boolean locationInfo = false;

    public boolean requiresLayout() {
        return false;
    }

    public AsyncAppender() {
        AppenderAttachableImpl appenderAttachableImpl = new AppenderAttachableImpl();
        this.appenders = appenderAttachableImpl;
        this.aai = appenderAttachableImpl;
        Thread thread = new Thread(new Dispatcher(this, this.buffer, this.discardMap, this.appenders));
        this.dispatcher = thread;
        thread.setDaemon(true);
        Thread thread2 = this.dispatcher;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Dispatcher-");
        stringBuffer.append(this.dispatcher.getName());
        thread2.setName(stringBuffer.toString());
        this.dispatcher.start();
    }

    public void addAppender(Appender appender) {
        synchronized (this.appenders) {
            this.appenders.addAppender(appender);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        java.lang.Thread.currentThread().interrupt();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x005a, code lost:
        continue;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:26:0x0053 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void append(org.apache.log4j.spi.LoggingEvent r5) {
        /*
            r4 = this;
            java.lang.Thread r0 = r4.dispatcher
            if (r0 == 0) goto L_0x007d
            boolean r0 = r0.isAlive()
            if (r0 == 0) goto L_0x007d
            int r0 = r4.bufferSize
            if (r0 > 0) goto L_0x000f
            goto L_0x007d
        L_0x000f:
            r5.getNDC()
            r5.getThreadName()
            r5.getMDCCopy()
            boolean r0 = r4.locationInfo
            if (r0 == 0) goto L_0x001f
            r5.getLocationInformation()
        L_0x001f:
            java.util.List r0 = r4.buffer
            monitor-enter(r0)
        L_0x0022:
            java.util.List r1 = r4.buffer     // Catch:{ all -> 0x007a }
            int r1 = r1.size()     // Catch:{ all -> 0x007a }
            int r2 = r4.bufferSize     // Catch:{ all -> 0x007a }
            if (r1 >= r2) goto L_0x0039
            java.util.List r2 = r4.buffer     // Catch:{ all -> 0x007a }
            r2.add(r5)     // Catch:{ all -> 0x007a }
            if (r1 != 0) goto L_0x0078
            java.util.List r5 = r4.buffer     // Catch:{ all -> 0x007a }
            r5.notifyAll()     // Catch:{ all -> 0x007a }
            goto L_0x0078
        L_0x0039:
            r1 = 1
            boolean r2 = r4.blocking     // Catch:{ all -> 0x007a }
            if (r2 == 0) goto L_0x005a
            boolean r2 = java.lang.Thread.interrupted()     // Catch:{ all -> 0x007a }
            if (r2 != 0) goto L_0x005a
            java.lang.Thread r2 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x007a }
            java.lang.Thread r3 = r4.dispatcher     // Catch:{ all -> 0x007a }
            if (r2 == r3) goto L_0x005a
            java.util.List r2 = r4.buffer     // Catch:{ InterruptedException -> 0x0053 }
            r2.wait()     // Catch:{ InterruptedException -> 0x0053 }
            r1 = 0
            goto L_0x005a
        L_0x0053:
            java.lang.Thread r2 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x007a }
            r2.interrupt()     // Catch:{ all -> 0x007a }
        L_0x005a:
            if (r1 == 0) goto L_0x0022
            java.lang.String r1 = r5.getLoggerName()     // Catch:{ all -> 0x007a }
            java.util.Map r2 = r4.discardMap     // Catch:{ all -> 0x007a }
            java.lang.Object r2 = r2.get(r1)     // Catch:{ all -> 0x007a }
            org.apache.log4j.AsyncAppender$DiscardSummary r2 = (org.apache.log4j.AsyncAppender.DiscardSummary) r2     // Catch:{ all -> 0x007a }
            if (r2 != 0) goto L_0x0075
            org.apache.log4j.AsyncAppender$DiscardSummary r2 = new org.apache.log4j.AsyncAppender$DiscardSummary     // Catch:{ all -> 0x007a }
            r2.<init>(r5)     // Catch:{ all -> 0x007a }
            java.util.Map r5 = r4.discardMap     // Catch:{ all -> 0x007a }
            r5.put(r1, r2)     // Catch:{ all -> 0x007a }
            goto L_0x0078
        L_0x0075:
            r2.add(r5)     // Catch:{ all -> 0x007a }
        L_0x0078:
            monitor-exit(r0)     // Catch:{ all -> 0x007a }
            return
        L_0x007a:
            r5 = move-exception
            monitor-exit(r0)
            throw r5
        L_0x007d:
            org.apache.log4j.helpers.AppenderAttachableImpl r0 = r4.appenders
            monitor-enter(r0)
            org.apache.log4j.helpers.AppenderAttachableImpl r1 = r4.appenders     // Catch:{ all -> 0x0087 }
            r1.appendLoopOnAppenders(r5)     // Catch:{ all -> 0x0087 }
            monitor-exit(r0)     // Catch:{ all -> 0x0087 }
            return
        L_0x0087:
            r5 = move-exception
            monitor-exit(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.log4j.AsyncAppender.append(org.apache.log4j.spi.LoggingEvent):void");
    }

    public void close() {
        synchronized (this.buffer) {
            this.closed = true;
            this.buffer.notifyAll();
        }
        try {
            this.dispatcher.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LogLog.error("Got an InterruptedException while waiting for the dispatcher to finish.", e);
        }
        synchronized (this.appenders) {
            Enumeration allAppenders = this.appenders.getAllAppenders();
            if (allAppenders != null) {
                while (allAppenders.hasMoreElements()) {
                    Object nextElement = allAppenders.nextElement();
                    if (nextElement instanceof Appender) {
                        ((Appender) nextElement).close();
                    }
                }
            }
        }
    }

    public Enumeration getAllAppenders() {
        Enumeration allAppenders;
        synchronized (this.appenders) {
            allAppenders = this.appenders.getAllAppenders();
        }
        return allAppenders;
    }

    public Appender getAppender(String str) {
        Appender appender;
        synchronized (this.appenders) {
            appender = this.appenders.getAppender(str);
        }
        return appender;
    }

    public boolean getLocationInfo() {
        return this.locationInfo;
    }

    public boolean isAttached(Appender appender) {
        boolean isAttached;
        synchronized (this.appenders) {
            isAttached = this.appenders.isAttached(appender);
        }
        return isAttached;
    }

    public void removeAllAppenders() {
        synchronized (this.appenders) {
            this.appenders.removeAllAppenders();
        }
    }

    public void removeAppender(Appender appender) {
        synchronized (this.appenders) {
            this.appenders.removeAppender(appender);
        }
    }

    public void removeAppender(String str) {
        synchronized (this.appenders) {
            this.appenders.removeAppender(str);
        }
    }

    public void setLocationInfo(boolean z) {
        this.locationInfo = z;
    }

    public void setBufferSize(int i) {
        if (i >= 0) {
            synchronized (this.buffer) {
                if (i < 1) {
                    i = 1;
                }
                this.bufferSize = i;
                this.buffer.notifyAll();
            }
            return;
        }
        throw new NegativeArraySizeException("size");
    }

    public int getBufferSize() {
        return this.bufferSize;
    }

    public void setBlocking(boolean z) {
        synchronized (this.buffer) {
            this.blocking = z;
            this.buffer.notifyAll();
        }
    }

    public boolean getBlocking() {
        return this.blocking;
    }

    private static final class DiscardSummary {
        private int count = 1;
        private LoggingEvent maxEvent;

        public DiscardSummary(LoggingEvent loggingEvent) {
            this.maxEvent = loggingEvent;
        }

        public void add(LoggingEvent loggingEvent) {
            if (loggingEvent.getLevel().toInt() > this.maxEvent.getLevel().toInt()) {
                this.maxEvent = loggingEvent;
            }
            this.count++;
        }

        public LoggingEvent createEvent() {
            return new LoggingEvent((String) null, Logger.getLogger(this.maxEvent.getLoggerName()), this.maxEvent.getLevel(), MessageFormat.format("Discarded {0} messages due to full event buffer including: {1}", new Object[]{new Integer(this.count), this.maxEvent.getMessage()}), (Throwable) null);
        }
    }

    private static class Dispatcher implements Runnable {
        private final AppenderAttachableImpl appenders;
        private final List buffer;
        private final Map discardMap;
        private final AsyncAppender parent;

        public Dispatcher(AsyncAppender asyncAppender, List list, Map map, AppenderAttachableImpl appenderAttachableImpl) {
            this.parent = asyncAppender;
            this.buffer = list;
            this.appenders = appenderAttachableImpl;
            this.discardMap = map;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:40:0x0089, code lost:
            java.lang.Thread.currentThread().interrupt();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:49:?, code lost:
            return;
         */
        /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r8 = this;
                r0 = 1
                r1 = 1
            L_0x0002:
                if (r1 != 0) goto L_0x0006
                goto L_0x0090
            L_0x0006:
                r1 = 0
                java.util.List r2 = r8.buffer     // Catch:{ InterruptedException -> 0x0089 }
                monitor-enter(r2)     // Catch:{ InterruptedException -> 0x0089 }
                java.util.List r3 = r8.buffer     // Catch:{ all -> 0x0086 }
                int r3 = r3.size()     // Catch:{ all -> 0x0086 }
                org.apache.log4j.AsyncAppender r4 = r8.parent     // Catch:{ all -> 0x0086 }
                boolean r4 = r4.closed     // Catch:{ all -> 0x0086 }
            L_0x0014:
                r4 = r4 ^ r0
                if (r3 != 0) goto L_0x002a
                if (r4 != 0) goto L_0x001a
                goto L_0x002a
            L_0x001a:
                java.util.List r3 = r8.buffer     // Catch:{ all -> 0x0086 }
                r3.wait()     // Catch:{ all -> 0x0086 }
                java.util.List r3 = r8.buffer     // Catch:{ all -> 0x0086 }
                int r3 = r3.size()     // Catch:{ all -> 0x0086 }
                org.apache.log4j.AsyncAppender r4 = r8.parent     // Catch:{ all -> 0x0086 }
                boolean r4 = r4.closed     // Catch:{ all -> 0x0086 }
                goto L_0x0014
            L_0x002a:
                if (r3 <= 0) goto L_0x006a
                java.util.Map r1 = r8.discardMap     // Catch:{ all -> 0x0086 }
                int r1 = r1.size()     // Catch:{ all -> 0x0086 }
                int r1 = r1 + r3
                org.apache.log4j.spi.LoggingEvent[] r1 = new org.apache.log4j.spi.LoggingEvent[r1]     // Catch:{ all -> 0x0086 }
                java.util.List r5 = r8.buffer     // Catch:{ all -> 0x0086 }
                r5.toArray(r1)     // Catch:{ all -> 0x0086 }
                java.util.Map r5 = r8.discardMap     // Catch:{ all -> 0x0086 }
                java.util.Collection r5 = r5.values()     // Catch:{ all -> 0x0086 }
                java.util.Iterator r5 = r5.iterator()     // Catch:{ all -> 0x0086 }
            L_0x0044:
                boolean r6 = r5.hasNext()     // Catch:{ all -> 0x0086 }
                if (r6 != 0) goto L_0x005a
                java.util.List r3 = r8.buffer     // Catch:{ all -> 0x0086 }
                r3.clear()     // Catch:{ all -> 0x0086 }
                java.util.Map r3 = r8.discardMap     // Catch:{ all -> 0x0086 }
                r3.clear()     // Catch:{ all -> 0x0086 }
                java.util.List r3 = r8.buffer     // Catch:{ all -> 0x0086 }
                r3.notifyAll()     // Catch:{ all -> 0x0086 }
                goto L_0x006a
            L_0x005a:
                int r6 = r3 + 1
                java.lang.Object r7 = r5.next()     // Catch:{ all -> 0x0086 }
                org.apache.log4j.AsyncAppender$DiscardSummary r7 = (org.apache.log4j.AsyncAppender.DiscardSummary) r7     // Catch:{ all -> 0x0086 }
                org.apache.log4j.spi.LoggingEvent r7 = r7.createEvent()     // Catch:{ all -> 0x0086 }
                r1[r3] = r7     // Catch:{ all -> 0x0086 }
                r3 = r6
                goto L_0x0044
            L_0x006a:
                monitor-exit(r2)     // Catch:{ all -> 0x0086 }
                if (r1 == 0) goto L_0x0083
                r2 = 0
            L_0x006e:
                int r3 = r1.length     // Catch:{ InterruptedException -> 0x0089 }
                if (r2 < r3) goto L_0x0072
                goto L_0x0083
            L_0x0072:
                org.apache.log4j.helpers.AppenderAttachableImpl r3 = r8.appenders     // Catch:{ InterruptedException -> 0x0089 }
                monitor-enter(r3)     // Catch:{ InterruptedException -> 0x0089 }
                org.apache.log4j.helpers.AppenderAttachableImpl r5 = r8.appenders     // Catch:{ all -> 0x0080 }
                r6 = r1[r2]     // Catch:{ all -> 0x0080 }
                r5.appendLoopOnAppenders(r6)     // Catch:{ all -> 0x0080 }
                monitor-exit(r3)     // Catch:{ all -> 0x0080 }
                int r2 = r2 + 1
                goto L_0x006e
            L_0x0080:
                r0 = move-exception
                monitor-exit(r3)     // Catch:{ InterruptedException -> 0x0089 }
                throw r0     // Catch:{ InterruptedException -> 0x0089 }
            L_0x0083:
                r1 = r4
                goto L_0x0002
            L_0x0086:
                r0 = move-exception
                monitor-exit(r2)     // Catch:{ InterruptedException -> 0x0089 }
                throw r0     // Catch:{ InterruptedException -> 0x0089 }
            L_0x0089:
                java.lang.Thread r0 = java.lang.Thread.currentThread()
                r0.interrupt()
            L_0x0090:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.log4j.AsyncAppender.Dispatcher.run():void");
        }
    }
}
