package org.apache.mina.core.session;

import org.apache.mina.core.write.WriteRequest;

public class IoEvent implements Runnable {
    private final Object parameter;
    private final IoSession session;
    private final IoEventType type;

    public IoEvent(IoEventType ioEventType, IoSession ioSession, Object obj) {
        if (ioEventType == null) {
            throw new IllegalArgumentException("type");
        } else if (ioSession != null) {
            this.type = ioEventType;
            this.session = ioSession;
            this.parameter = obj;
        } else {
            throw new IllegalArgumentException("session");
        }
    }

    public IoEventType getType() {
        return this.type;
    }

    public IoSession getSession() {
        return this.session;
    }

    public Object getParameter() {
        return this.parameter;
    }

    public void run() {
        fire();
    }

    /* renamed from: org.apache.mina.core.session.IoEvent$1  reason: invalid class name */
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
            throw new UnsupportedOperationException("Method not decompiled: org.apache.mina.core.session.IoEvent.AnonymousClass1.<clinit>():void");
        }
    }

    public void fire() {
        switch (AnonymousClass1.$SwitchMap$org$apache$mina$core$session$IoEventType[getType().ordinal()]) {
            case 1:
                getSession().getFilterChain().fireMessageReceived(getParameter());
                return;
            case 2:
                getSession().getFilterChain().fireMessageSent((WriteRequest) getParameter());
                return;
            case 3:
                getSession().getFilterChain().fireFilterWrite((WriteRequest) getParameter());
                return;
            case 4:
                getSession().getFilterChain().fireFilterClose();
                return;
            case 5:
                getSession().getFilterChain().fireExceptionCaught((Throwable) getParameter());
                return;
            case 6:
                getSession().getFilterChain().fireSessionIdle((IdleStatus) getParameter());
                return;
            case 7:
                getSession().getFilterChain().fireSessionOpened();
                return;
            case 8:
                getSession().getFilterChain().fireSessionCreated();
                return;
            case 9:
                getSession().getFilterChain().fireSessionClosed();
                return;
            default:
                throw new IllegalArgumentException("Unknown event type: " + getType());
        }
    }

    public String toString() {
        if (getParameter() == null) {
            return "[" + getSession() + "] " + getType().name();
        }
        return "[" + getSession() + "] " + getType().name() + ": " + getParameter();
    }
}
