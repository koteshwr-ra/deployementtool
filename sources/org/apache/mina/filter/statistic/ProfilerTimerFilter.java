package org.apache.mina.filter.statistic;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoEventType;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;

public class ProfilerTimerFilter extends IoFilterAdapter {
    private TimerWorker messageReceivedTimerWorker;
    private TimerWorker messageSentTimerWorker;
    private boolean profileMessageReceived;
    private boolean profileMessageSent;
    private boolean profileSessionClosed;
    private boolean profileSessionCreated;
    private boolean profileSessionIdle;
    private boolean profileSessionOpened;
    private TimerWorker sessionClosedTimerWorker;
    private TimerWorker sessionCreatedTimerWorker;
    private TimerWorker sessionIdleTimerWorker;
    private TimerWorker sessionOpenedTimerWorker;
    private volatile TimeUnit timeUnit;

    public ProfilerTimerFilter() {
        this(TimeUnit.MILLISECONDS, IoEventType.MESSAGE_RECEIVED, IoEventType.MESSAGE_SENT);
    }

    public ProfilerTimerFilter(TimeUnit timeUnit2) {
        this(timeUnit2, IoEventType.MESSAGE_RECEIVED, IoEventType.MESSAGE_SENT);
    }

    public ProfilerTimerFilter(TimeUnit timeUnit2, IoEventType... ioEventTypeArr) {
        this.profileMessageReceived = false;
        this.profileMessageSent = false;
        this.profileSessionCreated = false;
        this.profileSessionOpened = false;
        this.profileSessionIdle = false;
        this.profileSessionClosed = false;
        this.timeUnit = timeUnit2;
        setProfilers(ioEventTypeArr);
    }

    private void setProfilers(IoEventType... ioEventTypeArr) {
        for (IoEventType ordinal : ioEventTypeArr) {
            switch (AnonymousClass1.$SwitchMap$org$apache$mina$core$session$IoEventType[ordinal.ordinal()]) {
                case 1:
                    this.messageReceivedTimerWorker = new TimerWorker();
                    this.profileMessageReceived = true;
                    break;
                case 2:
                    this.messageSentTimerWorker = new TimerWorker();
                    this.profileMessageSent = true;
                    break;
                case 3:
                    this.sessionCreatedTimerWorker = new TimerWorker();
                    this.profileSessionCreated = true;
                    break;
                case 4:
                    this.sessionOpenedTimerWorker = new TimerWorker();
                    this.profileSessionOpened = true;
                    break;
                case 5:
                    this.sessionIdleTimerWorker = new TimerWorker();
                    this.profileSessionIdle = true;
                    break;
                case 6:
                    this.sessionClosedTimerWorker = new TimerWorker();
                    this.profileSessionClosed = true;
                    break;
            }
        }
    }

    public void setTimeUnit(TimeUnit timeUnit2) {
        this.timeUnit = timeUnit2;
    }

    public void profile(IoEventType ioEventType) {
        switch (AnonymousClass1.$SwitchMap$org$apache$mina$core$session$IoEventType[ioEventType.ordinal()]) {
            case 1:
                this.profileMessageReceived = true;
                if (this.messageReceivedTimerWorker == null) {
                    this.messageReceivedTimerWorker = new TimerWorker();
                    return;
                }
                return;
            case 2:
                this.profileMessageSent = true;
                if (this.messageSentTimerWorker == null) {
                    this.messageSentTimerWorker = new TimerWorker();
                    return;
                }
                return;
            case 3:
                this.profileSessionCreated = true;
                if (this.sessionCreatedTimerWorker == null) {
                    this.sessionCreatedTimerWorker = new TimerWorker();
                    return;
                }
                return;
            case 4:
                this.profileSessionOpened = true;
                if (this.sessionOpenedTimerWorker == null) {
                    this.sessionOpenedTimerWorker = new TimerWorker();
                    return;
                }
                return;
            case 5:
                this.profileSessionIdle = true;
                if (this.sessionIdleTimerWorker == null) {
                    this.sessionIdleTimerWorker = new TimerWorker();
                    return;
                }
                return;
            case 6:
                this.profileSessionClosed = true;
                if (this.sessionClosedTimerWorker == null) {
                    this.sessionClosedTimerWorker = new TimerWorker();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void stopProfile(IoEventType ioEventType) {
        switch (AnonymousClass1.$SwitchMap$org$apache$mina$core$session$IoEventType[ioEventType.ordinal()]) {
            case 1:
                this.profileMessageReceived = false;
                return;
            case 2:
                this.profileMessageSent = false;
                return;
            case 3:
                this.profileSessionCreated = false;
                return;
            case 4:
                this.profileSessionOpened = false;
                return;
            case 5:
                this.profileSessionIdle = false;
                return;
            case 6:
                this.profileSessionClosed = false;
                return;
            default:
                return;
        }
    }

    public Set<IoEventType> getEventsToProfile() {
        HashSet hashSet = new HashSet();
        if (this.profileMessageReceived) {
            hashSet.add(IoEventType.MESSAGE_RECEIVED);
        }
        if (this.profileMessageSent) {
            hashSet.add(IoEventType.MESSAGE_SENT);
        }
        if (this.profileSessionCreated) {
            hashSet.add(IoEventType.SESSION_CREATED);
        }
        if (this.profileSessionOpened) {
            hashSet.add(IoEventType.SESSION_OPENED);
        }
        if (this.profileSessionIdle) {
            hashSet.add(IoEventType.SESSION_IDLE);
        }
        if (this.profileSessionClosed) {
            hashSet.add(IoEventType.SESSION_CLOSED);
        }
        return hashSet;
    }

    public void setEventsToProfile(IoEventType... ioEventTypeArr) {
        setProfilers(ioEventTypeArr);
    }

    public void messageReceived(IoFilter.NextFilter nextFilter, IoSession ioSession, Object obj) throws Exception {
        if (this.profileMessageReceived) {
            long timeNow = timeNow();
            nextFilter.messageReceived(ioSession, obj);
            this.messageReceivedTimerWorker.addNewDuration(timeNow() - timeNow);
            return;
        }
        nextFilter.messageReceived(ioSession, obj);
    }

    public void messageSent(IoFilter.NextFilter nextFilter, IoSession ioSession, WriteRequest writeRequest) throws Exception {
        if (this.profileMessageSent) {
            long timeNow = timeNow();
            nextFilter.messageSent(ioSession, writeRequest);
            this.messageSentTimerWorker.addNewDuration(timeNow() - timeNow);
            return;
        }
        nextFilter.messageSent(ioSession, writeRequest);
    }

    public void sessionCreated(IoFilter.NextFilter nextFilter, IoSession ioSession) throws Exception {
        if (this.profileSessionCreated) {
            long timeNow = timeNow();
            nextFilter.sessionCreated(ioSession);
            this.sessionCreatedTimerWorker.addNewDuration(timeNow() - timeNow);
            return;
        }
        nextFilter.sessionCreated(ioSession);
    }

    public void sessionOpened(IoFilter.NextFilter nextFilter, IoSession ioSession) throws Exception {
        if (this.profileSessionOpened) {
            long timeNow = timeNow();
            nextFilter.sessionOpened(ioSession);
            this.sessionOpenedTimerWorker.addNewDuration(timeNow() - timeNow);
            return;
        }
        nextFilter.sessionOpened(ioSession);
    }

    public void sessionIdle(IoFilter.NextFilter nextFilter, IoSession ioSession, IdleStatus idleStatus) throws Exception {
        if (this.profileSessionIdle) {
            long timeNow = timeNow();
            nextFilter.sessionIdle(ioSession, idleStatus);
            this.sessionIdleTimerWorker.addNewDuration(timeNow() - timeNow);
            return;
        }
        nextFilter.sessionIdle(ioSession, idleStatus);
    }

    public void sessionClosed(IoFilter.NextFilter nextFilter, IoSession ioSession) throws Exception {
        if (this.profileSessionClosed) {
            long timeNow = timeNow();
            nextFilter.sessionClosed(ioSession);
            this.sessionClosedTimerWorker.addNewDuration(timeNow() - timeNow);
            return;
        }
        nextFilter.sessionClosed(ioSession);
    }

    public double getAverageTime(IoEventType ioEventType) {
        switch (AnonymousClass1.$SwitchMap$org$apache$mina$core$session$IoEventType[ioEventType.ordinal()]) {
            case 1:
                if (this.profileMessageReceived) {
                    return this.messageReceivedTimerWorker.getAverage();
                }
                break;
            case 2:
                if (this.profileMessageSent) {
                    return this.messageSentTimerWorker.getAverage();
                }
                break;
            case 3:
                if (this.profileSessionCreated) {
                    return this.sessionCreatedTimerWorker.getAverage();
                }
                break;
            case 4:
                if (this.profileSessionOpened) {
                    return this.sessionOpenedTimerWorker.getAverage();
                }
                break;
            case 5:
                if (this.profileSessionIdle) {
                    return this.sessionIdleTimerWorker.getAverage();
                }
                break;
            case 6:
                if (this.profileSessionClosed) {
                    return this.sessionClosedTimerWorker.getAverage();
                }
                break;
        }
        throw new IllegalArgumentException("You are not monitoring this event.  Please add this event first.");
    }

    public long getTotalCalls(IoEventType ioEventType) {
        switch (AnonymousClass1.$SwitchMap$org$apache$mina$core$session$IoEventType[ioEventType.ordinal()]) {
            case 1:
                if (this.profileMessageReceived) {
                    return this.messageReceivedTimerWorker.getCallsNumber();
                }
                break;
            case 2:
                if (this.profileMessageSent) {
                    return this.messageSentTimerWorker.getCallsNumber();
                }
                break;
            case 3:
                if (this.profileSessionCreated) {
                    return this.sessionCreatedTimerWorker.getCallsNumber();
                }
                break;
            case 4:
                if (this.profileSessionOpened) {
                    return this.sessionOpenedTimerWorker.getCallsNumber();
                }
                break;
            case 5:
                if (this.profileSessionIdle) {
                    return this.sessionIdleTimerWorker.getCallsNumber();
                }
                break;
            case 6:
                if (this.profileSessionClosed) {
                    return this.sessionClosedTimerWorker.getCallsNumber();
                }
                break;
        }
        throw new IllegalArgumentException("You are not monitoring this event.  Please add this event first.");
    }

    public long getTotalTime(IoEventType ioEventType) {
        switch (AnonymousClass1.$SwitchMap$org$apache$mina$core$session$IoEventType[ioEventType.ordinal()]) {
            case 1:
                if (this.profileMessageReceived) {
                    return this.messageReceivedTimerWorker.getTotal();
                }
                break;
            case 2:
                if (this.profileMessageSent) {
                    return this.messageSentTimerWorker.getTotal();
                }
                break;
            case 3:
                if (this.profileSessionCreated) {
                    return this.sessionCreatedTimerWorker.getTotal();
                }
                break;
            case 4:
                if (this.profileSessionOpened) {
                    return this.sessionOpenedTimerWorker.getTotal();
                }
                break;
            case 5:
                if (this.profileSessionIdle) {
                    return this.sessionIdleTimerWorker.getTotal();
                }
                break;
            case 6:
                if (this.profileSessionClosed) {
                    return this.sessionClosedTimerWorker.getTotal();
                }
                break;
        }
        throw new IllegalArgumentException("You are not monitoring this event.  Please add this event first.");
    }

    public long getMinimumTime(IoEventType ioEventType) {
        switch (AnonymousClass1.$SwitchMap$org$apache$mina$core$session$IoEventType[ioEventType.ordinal()]) {
            case 1:
                if (this.profileMessageReceived) {
                    return this.messageReceivedTimerWorker.getMinimum();
                }
                break;
            case 2:
                if (this.profileMessageSent) {
                    return this.messageSentTimerWorker.getMinimum();
                }
                break;
            case 3:
                if (this.profileSessionCreated) {
                    return this.sessionCreatedTimerWorker.getMinimum();
                }
                break;
            case 4:
                if (this.profileSessionOpened) {
                    return this.sessionOpenedTimerWorker.getMinimum();
                }
                break;
            case 5:
                if (this.profileSessionIdle) {
                    return this.sessionIdleTimerWorker.getMinimum();
                }
                break;
            case 6:
                if (this.profileSessionClosed) {
                    return this.sessionClosedTimerWorker.getMinimum();
                }
                break;
        }
        throw new IllegalArgumentException("You are not monitoring this event.  Please add this event first.");
    }

    public long getMaximumTime(IoEventType ioEventType) {
        switch (AnonymousClass1.$SwitchMap$org$apache$mina$core$session$IoEventType[ioEventType.ordinal()]) {
            case 1:
                if (this.profileMessageReceived) {
                    return this.messageReceivedTimerWorker.getMaximum();
                }
                break;
            case 2:
                if (this.profileMessageSent) {
                    return this.messageSentTimerWorker.getMaximum();
                }
                break;
            case 3:
                if (this.profileSessionCreated) {
                    return this.sessionCreatedTimerWorker.getMaximum();
                }
                break;
            case 4:
                if (this.profileSessionOpened) {
                    return this.sessionOpenedTimerWorker.getMaximum();
                }
                break;
            case 5:
                if (this.profileSessionIdle) {
                    return this.sessionIdleTimerWorker.getMaximum();
                }
                break;
            case 6:
                if (this.profileSessionClosed) {
                    return this.sessionClosedTimerWorker.getMaximum();
                }
                break;
        }
        throw new IllegalArgumentException("You are not monitoring this event.  Please add this event first.");
    }

    private class TimerWorker {
        private final AtomicLong callsNumber = new AtomicLong();
        private final Object lock = new Object();
        private final AtomicLong maximum = new AtomicLong();
        private final AtomicLong minimum = new AtomicLong();
        private final AtomicLong total = new AtomicLong();

        public TimerWorker() {
        }

        public void addNewDuration(long j) {
            this.callsNumber.incrementAndGet();
            this.total.addAndGet(j);
            synchronized (this.lock) {
                if (j < this.minimum.longValue()) {
                    this.minimum.set(j);
                }
                if (j > this.maximum.longValue()) {
                    this.maximum.set(j);
                }
            }
        }

        public double getAverage() {
            double longValue;
            synchronized (this.lock) {
                longValue = (double) (this.total.longValue() / this.callsNumber.longValue());
            }
            return longValue;
        }

        public long getCallsNumber() {
            return this.callsNumber.longValue();
        }

        public long getTotal() {
            return this.total.longValue();
        }

        public long getMinimum() {
            return this.minimum.longValue();
        }

        public long getMaximum() {
            return this.maximum.longValue();
        }
    }

    /* renamed from: org.apache.mina.filter.statistic.ProfilerTimerFilter$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$util$concurrent$TimeUnit;
        static final /* synthetic */ int[] $SwitchMap$org$apache$mina$core$session$IoEventType;

        /* JADX WARNING: Can't wrap try/catch for region: R(21:0|(2:1|2)|3|(2:5|6)|7|9|10|11|13|14|15|16|17|18|19|20|21|22|23|24|26) */
        /* JADX WARNING: Can't wrap try/catch for region: R(22:0|1|2|3|(2:5|6)|7|9|10|11|13|14|15|16|17|18|19|20|21|22|23|24|26) */
        /* JADX WARNING: Can't wrap try/catch for region: R(23:0|1|2|3|5|6|7|9|10|11|13|14|15|16|17|18|19|20|21|22|23|24|26) */
        /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0039 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0043 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x004d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0058 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0063 */
        static {
            /*
                java.util.concurrent.TimeUnit[] r0 = java.util.concurrent.TimeUnit.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$java$util$concurrent$TimeUnit = r0
                r1 = 1
                java.util.concurrent.TimeUnit r2 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$java$util$concurrent$TimeUnit     // Catch:{ NoSuchFieldError -> 0x001d }
                java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.MICROSECONDS     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$java$util$concurrent$TimeUnit     // Catch:{ NoSuchFieldError -> 0x0028 }
                java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.NANOSECONDS     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                org.apache.mina.core.session.IoEventType[] r3 = org.apache.mina.core.session.IoEventType.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$org$apache$mina$core$session$IoEventType = r3
                org.apache.mina.core.session.IoEventType r4 = org.apache.mina.core.session.IoEventType.MESSAGE_RECEIVED     // Catch:{ NoSuchFieldError -> 0x0039 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0039 }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0039 }
            L_0x0039:
                int[] r1 = $SwitchMap$org$apache$mina$core$session$IoEventType     // Catch:{ NoSuchFieldError -> 0x0043 }
                org.apache.mina.core.session.IoEventType r3 = org.apache.mina.core.session.IoEventType.MESSAGE_SENT     // Catch:{ NoSuchFieldError -> 0x0043 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0043 }
                r1[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0043 }
            L_0x0043:
                int[] r0 = $SwitchMap$org$apache$mina$core$session$IoEventType     // Catch:{ NoSuchFieldError -> 0x004d }
                org.apache.mina.core.session.IoEventType r1 = org.apache.mina.core.session.IoEventType.SESSION_CREATED     // Catch:{ NoSuchFieldError -> 0x004d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004d }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004d }
            L_0x004d:
                int[] r0 = $SwitchMap$org$apache$mina$core$session$IoEventType     // Catch:{ NoSuchFieldError -> 0x0058 }
                org.apache.mina.core.session.IoEventType r1 = org.apache.mina.core.session.IoEventType.SESSION_OPENED     // Catch:{ NoSuchFieldError -> 0x0058 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0058 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0058 }
            L_0x0058:
                int[] r0 = $SwitchMap$org$apache$mina$core$session$IoEventType     // Catch:{ NoSuchFieldError -> 0x0063 }
                org.apache.mina.core.session.IoEventType r1 = org.apache.mina.core.session.IoEventType.SESSION_IDLE     // Catch:{ NoSuchFieldError -> 0x0063 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0063 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0063 }
            L_0x0063:
                int[] r0 = $SwitchMap$org$apache$mina$core$session$IoEventType     // Catch:{ NoSuchFieldError -> 0x006e }
                org.apache.mina.core.session.IoEventType r1 = org.apache.mina.core.session.IoEventType.SESSION_CLOSED     // Catch:{ NoSuchFieldError -> 0x006e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006e }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006e }
            L_0x006e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.mina.filter.statistic.ProfilerTimerFilter.AnonymousClass1.<clinit>():void");
        }
    }

    private long timeNow() {
        int i = AnonymousClass1.$SwitchMap$java$util$concurrent$TimeUnit[this.timeUnit.ordinal()];
        if (i == 1) {
            return System.currentTimeMillis() / 1000;
        }
        if (i == 2) {
            return System.nanoTime() / 1000;
        }
        if (i != 3) {
            return System.currentTimeMillis();
        }
        return System.nanoTime();
    }
}
