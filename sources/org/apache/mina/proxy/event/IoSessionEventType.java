package org.apache.mina.proxy.event;

public enum IoSessionEventType {
    CREATED(1),
    OPENED(2),
    IDLE(3),
    CLOSED(4);
    
    private final int id;

    private IoSessionEventType(int i) {
        this.id = i;
    }

    public int getId() {
        return this.id;
    }

    /* renamed from: org.apache.mina.proxy.event.IoSessionEventType$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$mina$proxy$event$IoSessionEventType = null;

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
            throw new UnsupportedOperationException("Method not decompiled: org.apache.mina.proxy.event.IoSessionEventType.AnonymousClass1.<clinit>():void");
        }
    }

    public String toString() {
        int i = AnonymousClass1.$SwitchMap$org$apache$mina$proxy$event$IoSessionEventType[ordinal()];
        if (i == 1) {
            return "- CREATED event -";
        }
        if (i == 2) {
            return "- OPENED event -";
        }
        if (i == 3) {
            return "- IDLE event -";
        }
        if (i == 4) {
            return "- CLOSED event -";
        }
        return "- Event Id=" + this.id + " -";
    }
}
