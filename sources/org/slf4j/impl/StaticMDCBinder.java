package org.slf4j.impl;

import org.slf4j.spi.MDCAdapter;

public class StaticMDCBinder {
    public static final StaticMDCBinder SINGLETON = new StaticMDCBinder();
    static /* synthetic */ Class class$org$slf4j$impl$Log4jMDCAdapter;

    private StaticMDCBinder() {
    }

    public MDCAdapter getMDCA() {
        return new Log4jMDCAdapter();
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public String getMDCAdapterClassStr() {
        Class cls = class$org$slf4j$impl$Log4jMDCAdapter;
        if (cls == null) {
            cls = class$("org.slf4j.impl.Log4jMDCAdapter");
            class$org$slf4j$impl$Log4jMDCAdapter = cls;
        }
        return cls.getName();
    }
}
