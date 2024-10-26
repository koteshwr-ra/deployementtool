package org.apache.log4j;

import org.apache.log4j.spi.LoggerFactory;

public class Logger extends Category {
    private static final String FQCN;
    static /* synthetic */ Class class$org$apache$log4j$Logger;

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class cls = class$org$apache$log4j$Logger;
        if (cls == null) {
            cls = class$("org.apache.log4j.Logger");
            class$org$apache$log4j$Logger = cls;
        }
        FQCN = cls.getName();
    }

    protected Logger(String str) {
        super(str);
    }

    public static Logger getLogger(String str) {
        return LogManager.getLogger(str);
    }

    public static Logger getLogger(Class cls) {
        return LogManager.getLogger(cls.getName());
    }

    public static Logger getRootLogger() {
        return LogManager.getRootLogger();
    }

    public static Logger getLogger(String str, LoggerFactory loggerFactory) {
        return LogManager.getLogger(str, loggerFactory);
    }

    public void trace(Object obj) {
        if (!this.repository.isDisabled(5000) && Level.TRACE.isGreaterOrEqual(getEffectiveLevel())) {
            forcedLog(FQCN, Level.TRACE, obj, (Throwable) null);
        }
    }

    public void trace(Object obj, Throwable th) {
        if (!this.repository.isDisabled(5000) && Level.TRACE.isGreaterOrEqual(getEffectiveLevel())) {
            forcedLog(FQCN, Level.TRACE, obj, th);
        }
    }

    public boolean isTraceEnabled() {
        if (this.repository.isDisabled(5000)) {
            return false;
        }
        return Level.TRACE.isGreaterOrEqual(getEffectiveLevel());
    }
}
