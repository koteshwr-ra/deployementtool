package org.apache.mina.core.filterchain;

import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoEvent;
import org.apache.mina.core.session.IoEventType;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IoFilterEvent extends IoEvent {
    static boolean DEBUG;
    static Logger LOGGER;
    private final IoFilter.NextFilter nextFilter;

    static {
        Logger logger = LoggerFactory.getLogger(IoFilterEvent.class);
        LOGGER = logger;
        DEBUG = logger.isDebugEnabled();
    }

    public IoFilterEvent(IoFilter.NextFilter nextFilter2, IoEventType ioEventType, IoSession ioSession, Object obj) {
        super(ioEventType, ioSession, obj);
        if (nextFilter2 != null) {
            this.nextFilter = nextFilter2;
            return;
        }
        throw new IllegalArgumentException("nextFilter must not be null");
    }

    public IoFilter.NextFilter getNextFilter() {
        return this.nextFilter;
    }

    public void fire() {
        IoSession session = getSession();
        IoFilter.NextFilter nextFilter2 = getNextFilter();
        IoEventType type = getType();
        if (DEBUG) {
            LOGGER.debug("Firing a {} event for session {}", (Object) type, (Object) Long.valueOf(session.getId()));
        }
        switch (AnonymousClass1.$SwitchMap$org$apache$mina$core$session$IoEventType[type.ordinal()]) {
            case 1:
                nextFilter2.messageReceived(session, getParameter());
                break;
            case 2:
                nextFilter2.messageSent(session, (WriteRequest) getParameter());
                break;
            case 3:
                nextFilter2.filterWrite(session, (WriteRequest) getParameter());
                break;
            case 4:
                nextFilter2.filterClose(session);
                break;
            case 5:
                nextFilter2.exceptionCaught(session, (Throwable) getParameter());
                break;
            case 6:
                nextFilter2.sessionIdle(session, (IdleStatus) getParameter());
                break;
            case 7:
                nextFilter2.sessionOpened(session);
                break;
            case 8:
                nextFilter2.sessionCreated(session);
                break;
            case 9:
                nextFilter2.sessionClosed(session);
                break;
            default:
                throw new IllegalArgumentException("Unknown event type: " + type);
        }
        if (DEBUG) {
            LOGGER.debug("Event {} has been fired for session {}", (Object) type, (Object) Long.valueOf(session.getId()));
        }
    }

    /* renamed from: org.apache.mina.core.filterchain.IoFilterEvent$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$mina$core$session$IoEventType;

        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|(3:17|18|20)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.apache.mina.core.session.IoEventType[] r0 = org.apache.mina.core.session.IoEventType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$mina$core$session$IoEventType = r0
                org.apache.mina.core.session.IoEventType r1 = org.apache.mina.core.session.IoEventType.MESSAGE_RECEIVED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$mina$core$session$IoEventType     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.mina.core.session.IoEventType r1 = org.apache.mina.core.session.IoEventType.MESSAGE_SENT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$mina$core$session$IoEventType     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.mina.core.session.IoEventType r1 = org.apache.mina.core.session.IoEventType.WRITE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$mina$core$session$IoEventType     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.mina.core.session.IoEventType r1 = org.apache.mina.core.session.IoEventType.CLOSE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$mina$core$session$IoEventType     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.mina.core.session.IoEventType r1 = org.apache.mina.core.session.IoEventType.EXCEPTION_CAUGHT     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$org$apache$mina$core$session$IoEventType     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.apache.mina.core.session.IoEventType r1 = org.apache.mina.core.session.IoEventType.SESSION_IDLE     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$org$apache$mina$core$session$IoEventType     // Catch:{ NoSuchFieldError -> 0x0054 }
                org.apache.mina.core.session.IoEventType r1 = org.apache.mina.core.session.IoEventType.SESSION_OPENED     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$org$apache$mina$core$session$IoEventType     // Catch:{ NoSuchFieldError -> 0x0060 }
                org.apache.mina.core.session.IoEventType r1 = org.apache.mina.core.session.IoEventType.SESSION_CREATED     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$org$apache$mina$core$session$IoEventType     // Catch:{ NoSuchFieldError -> 0x006c }
                org.apache.mina.core.session.IoEventType r1 = org.apache.mina.core.session.IoEventType.SESSION_CLOSED     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.mina.core.filterchain.IoFilterEvent.AnonymousClass1.<clinit>():void");
        }
    }
}
