package org.slf4j.impl;

import org.apache.log4j.Level;
import org.slf4j.ILoggerFactory;
import org.slf4j.helpers.Util;
import org.slf4j.spi.LoggerFactoryBinder;

public class StaticLoggerBinder implements LoggerFactoryBinder {
    public static String REQUESTED_API_VERSION = "1.5.8";
    private static final StaticLoggerBinder SINGLETON = new StaticLoggerBinder();
    static /* synthetic */ Class class$org$slf4j$impl$Log4jLoggerFactory;
    private static final String loggerFactoryClassStr;
    private final ILoggerFactory loggerFactory = new Log4jLoggerFactory();

    static {
        Class cls = class$org$slf4j$impl$Log4jLoggerFactory;
        if (cls == null) {
            cls = class$("org.slf4j.impl.Log4jLoggerFactory");
            class$org$slf4j$impl$Log4jLoggerFactory = cls;
        }
        loggerFactoryClassStr = cls.getName();
    }

    public static final StaticLoggerBinder getSingleton() {
        return SINGLETON;
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    private StaticLoggerBinder() {
        try {
            Level level = Level.TRACE;
        } catch (NoSuchFieldError unused) {
            Util.reportFailure("This version of SLF4J requires log4j version 1.2.12 or later. See also http://www.slf4j.org/codes.html#log4j_version");
        }
    }

    public ILoggerFactory getLoggerFactory() {
        return this.loggerFactory;
    }

    public String getLoggerFactoryClassStr() {
        return loggerFactoryClassStr;
    }
}
