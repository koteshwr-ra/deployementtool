package org.apache.mina.proxy.event;

import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IoSessionEvent {
    private static final Logger logger = LoggerFactory.getLogger(IoSessionEvent.class);
    private final IoFilter.NextFilter nextFilter;
    private final IoSession session;
    private IdleStatus status;
    private final IoSessionEventType type;

    public IoSessionEvent(IoFilter.NextFilter nextFilter2, IoSession ioSession, IoSessionEventType ioSessionEventType) {
        this.nextFilter = nextFilter2;
        this.session = ioSession;
        this.type = ioSessionEventType;
    }

    public IoSessionEvent(IoFilter.NextFilter nextFilter2, IoSession ioSession, IdleStatus idleStatus) {
        this(nextFilter2, ioSession, IoSessionEventType.IDLE);
        this.status = idleStatus;
    }

    public void deliverEvent() {
        logger.debug("Delivering event {}", (Object) this);
        deliverEvent(this.nextFilter, this.session, this.type, this.status);
    }

    /* renamed from: org.apache.mina.proxy.event.IoSessionEvent$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$mina$proxy$event$IoSessionEventType;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                org.apache.mina.proxy.event.IoSessionEventType[] r0 = org.apache.mina.proxy.event.IoSessionEventType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$mina$proxy$event$IoSessionEventType = r0
                org.apache.mina.proxy.event.IoSessionEventType r1 = org.apache.mina.proxy.event.IoSessionEventType.CREATED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$mina$proxy$event$IoSessionEventType     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.mina.proxy.event.IoSessionEventType r1 = org.apache.mina.proxy.event.IoSessionEventType.OPENED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$mina$proxy$event$IoSessionEventType     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.mina.proxy.event.IoSessionEventType r1 = org.apache.mina.proxy.event.IoSessionEventType.IDLE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$mina$proxy$event$IoSessionEventType     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.mina.proxy.event.IoSessionEventType r1 = org.apache.mina.proxy.event.IoSessionEventType.CLOSED     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.mina.proxy.event.IoSessionEvent.AnonymousClass1.<clinit>():void");
        }
    }

    private static void deliverEvent(IoFilter.NextFilter nextFilter2, IoSession ioSession, IoSessionEventType ioSessionEventType, IdleStatus idleStatus) {
        int i = AnonymousClass1.$SwitchMap$org$apache$mina$proxy$event$IoSessionEventType[ioSessionEventType.ordinal()];
        if (i == 1) {
            nextFilter2.sessionCreated(ioSession);
        } else if (i == 2) {
            nextFilter2.sessionOpened(ioSession);
        } else if (i == 3) {
            nextFilter2.sessionIdle(ioSession, idleStatus);
        } else if (i == 4) {
            nextFilter2.sessionClosed(ioSession);
        }
    }

    public String toString() {
        return IoSessionEvent.class.getSimpleName() + '@' + Integer.toHexString(hashCode()) + " - [ " + this.session + ", " + this.type + ']';
    }

    public IdleStatus getStatus() {
        return this.status;
    }

    public IoFilter.NextFilter getNextFilter() {
        return this.nextFilter;
    }

    public IoSession getSession() {
        return this.session;
    }

    public IoSessionEventType getType() {
        return this.type;
    }
}
