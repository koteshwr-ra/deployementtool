package com.tencent.smtt.sdk;

import android.os.HandlerThread;

class n extends HandlerThread {
    private static n a;

    public n(String str) {
        super(str);
    }

    public static synchronized n a() {
        n nVar;
        synchronized (n.class) {
            if (a == null) {
                n nVar2 = new n("TbsHandlerThread");
                a = nVar2;
                nVar2.start();
            }
            nVar = a;
        }
        return nVar;
    }
}
