package org.slf4j.impl;

import java.io.Serializable;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.slf4j.Marker;
import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.helpers.MessageFormatter;
import org.slf4j.spi.LocationAwareLogger;

public final class Log4jLoggerAdapter extends MarkerIgnoringBase implements LocationAwareLogger, Serializable {
    static final String FQCN;
    static /* synthetic */ Class class$org$slf4j$impl$Log4jLoggerAdapter = null;
    private static final long serialVersionUID = 6182834493563598289L;
    final transient Logger logger;
    final boolean traceCapable = isTraceCapable();

    static {
        Class cls = class$org$slf4j$impl$Log4jLoggerAdapter;
        if (cls == null) {
            cls = class$("org.slf4j.impl.Log4jLoggerAdapter");
            class$org$slf4j$impl$Log4jLoggerAdapter = cls;
        }
        FQCN = cls.getName();
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    Log4jLoggerAdapter(Logger logger2) {
        this.logger = logger2;
        this.name = logger2.getName();
    }

    private boolean isTraceCapable() {
        try {
            this.logger.isTraceEnabled();
            return true;
        } catch (NoSuchMethodError unused) {
            return false;
        }
    }

    public boolean isTraceEnabled() {
        if (this.traceCapable) {
            return this.logger.isTraceEnabled();
        }
        return this.logger.isDebugEnabled();
    }

    public void trace(String str) {
        this.logger.log(FQCN, this.traceCapable ? Level.TRACE : Level.DEBUG, str, (Throwable) null);
    }

    public void trace(String str, Object obj) {
        if (isTraceEnabled()) {
            this.logger.log(FQCN, this.traceCapable ? Level.TRACE : Level.DEBUG, MessageFormatter.format(str, obj), (Throwable) null);
        }
    }

    public void trace(String str, Object obj, Object obj2) {
        if (isTraceEnabled()) {
            this.logger.log(FQCN, this.traceCapable ? Level.TRACE : Level.DEBUG, MessageFormatter.format(str, obj, obj2), (Throwable) null);
        }
    }

    public void trace(String str, Object[] objArr) {
        if (isTraceEnabled()) {
            this.logger.log(FQCN, this.traceCapable ? Level.TRACE : Level.DEBUG, MessageFormatter.arrayFormat(str, objArr), (Throwable) null);
        }
    }

    public void trace(String str, Throwable th) {
        this.logger.log(FQCN, this.traceCapable ? Level.TRACE : Level.DEBUG, str, th);
    }

    public boolean isDebugEnabled() {
        return this.logger.isDebugEnabled();
    }

    public void debug(String str) {
        this.logger.log(FQCN, Level.DEBUG, str, (Throwable) null);
    }

    public void debug(String str, Object obj) {
        if (this.logger.isDebugEnabled()) {
            this.logger.log(FQCN, Level.DEBUG, MessageFormatter.format(str, obj), (Throwable) null);
        }
    }

    public void debug(String str, Object obj, Object obj2) {
        if (this.logger.isDebugEnabled()) {
            this.logger.log(FQCN, Level.DEBUG, MessageFormatter.format(str, obj, obj2), (Throwable) null);
        }
    }

    public void debug(String str, Object[] objArr) {
        if (this.logger.isDebugEnabled()) {
            this.logger.log(FQCN, Level.DEBUG, MessageFormatter.arrayFormat(str, objArr), (Throwable) null);
        }
    }

    public void debug(String str, Throwable th) {
        this.logger.log(FQCN, Level.DEBUG, str, th);
    }

    public boolean isInfoEnabled() {
        return this.logger.isInfoEnabled();
    }

    public void info(String str) {
        this.logger.log(FQCN, Level.INFO, str, (Throwable) null);
    }

    public void info(String str, Object obj) {
        if (this.logger.isInfoEnabled()) {
            this.logger.log(FQCN, Level.INFO, MessageFormatter.format(str, obj), (Throwable) null);
        }
    }

    public void info(String str, Object obj, Object obj2) {
        if (this.logger.isInfoEnabled()) {
            this.logger.log(FQCN, Level.INFO, MessageFormatter.format(str, obj, obj2), (Throwable) null);
        }
    }

    public void info(String str, Object[] objArr) {
        if (this.logger.isInfoEnabled()) {
            this.logger.log(FQCN, Level.INFO, MessageFormatter.arrayFormat(str, objArr), (Throwable) null);
        }
    }

    public void info(String str, Throwable th) {
        this.logger.log(FQCN, Level.INFO, str, th);
    }

    public boolean isWarnEnabled() {
        return this.logger.isEnabledFor(Level.WARN);
    }

    public void warn(String str) {
        this.logger.log(FQCN, Level.WARN, str, (Throwable) null);
    }

    public void warn(String str, Object obj) {
        if (this.logger.isEnabledFor(Level.WARN)) {
            this.logger.log(FQCN, Level.WARN, MessageFormatter.format(str, obj), (Throwable) null);
        }
    }

    public void warn(String str, Object obj, Object obj2) {
        if (this.logger.isEnabledFor(Level.WARN)) {
            this.logger.log(FQCN, Level.WARN, MessageFormatter.format(str, obj, obj2), (Throwable) null);
        }
    }

    public void warn(String str, Object[] objArr) {
        if (this.logger.isEnabledFor(Level.WARN)) {
            this.logger.log(FQCN, Level.WARN, MessageFormatter.arrayFormat(str, objArr), (Throwable) null);
        }
    }

    public void warn(String str, Throwable th) {
        this.logger.log(FQCN, Level.WARN, str, th);
    }

    public boolean isErrorEnabled() {
        return this.logger.isEnabledFor(Level.ERROR);
    }

    public void error(String str) {
        this.logger.log(FQCN, Level.ERROR, str, (Throwable) null);
    }

    public void error(String str, Object obj) {
        if (this.logger.isEnabledFor(Level.ERROR)) {
            this.logger.log(FQCN, Level.ERROR, MessageFormatter.format(str, obj), (Throwable) null);
        }
    }

    public void error(String str, Object obj, Object obj2) {
        if (this.logger.isEnabledFor(Level.ERROR)) {
            this.logger.log(FQCN, Level.ERROR, MessageFormatter.format(str, obj, obj2), (Throwable) null);
        }
    }

    public void error(String str, Object[] objArr) {
        if (this.logger.isEnabledFor(Level.ERROR)) {
            this.logger.log(FQCN, Level.ERROR, MessageFormatter.arrayFormat(str, objArr), (Throwable) null);
        }
    }

    public void error(String str, Throwable th) {
        this.logger.log(FQCN, Level.ERROR, str, th);
    }

    public void log(Marker marker, String str, int i, String str2, Throwable th) {
        Level level;
        if (i == 0) {
            level = this.traceCapable ? Level.TRACE : Level.DEBUG;
        } else if (i == 10) {
            level = Level.DEBUG;
        } else if (i == 20) {
            level = Level.INFO;
        } else if (i == 30) {
            level = Level.WARN;
        } else if (i == 40) {
            level = Level.ERROR;
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Level number ");
            stringBuffer.append(i);
            stringBuffer.append(" is not recognized.");
            throw new IllegalStateException(stringBuffer.toString());
        }
        this.logger.log(str, level, str2, th);
    }
}
