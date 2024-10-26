package org.apache.mina.filter.logging;

import com.ciot.base.config.SpeechConstants;
import kotlinx.coroutines.debug.internal.DebugCoroutineInfoImplKt;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingFilter extends IoFilterAdapter {
    private LogLevel exceptionCaughtLevel;
    private final Logger logger;
    private LogLevel messageReceivedLevel;
    private LogLevel messageSentLevel;
    private final String name;
    private LogLevel sessionClosedLevel;
    private LogLevel sessionCreatedLevel;
    private LogLevel sessionIdleLevel;
    private LogLevel sessionOpenedLevel;

    public LoggingFilter() {
        this(LoggingFilter.class.getName());
    }

    public LoggingFilter(Class<?> cls) {
        this(cls.getName());
    }

    public LoggingFilter(String str) {
        this.exceptionCaughtLevel = LogLevel.WARN;
        this.messageSentLevel = LogLevel.INFO;
        this.messageReceivedLevel = LogLevel.INFO;
        this.sessionCreatedLevel = LogLevel.INFO;
        this.sessionOpenedLevel = LogLevel.INFO;
        this.sessionIdleLevel = LogLevel.INFO;
        this.sessionClosedLevel = LogLevel.INFO;
        if (str == null) {
            this.name = LoggingFilter.class.getName();
        } else {
            this.name = str;
        }
        this.logger = LoggerFactory.getLogger(this.name);
    }

    public String getName() {
        return this.name;
    }

    /* renamed from: org.apache.mina.filter.logging.LoggingFilter$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$mina$filter$logging$LogLevel;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.apache.mina.filter.logging.LogLevel[] r0 = org.apache.mina.filter.logging.LogLevel.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$mina$filter$logging$LogLevel = r0
                org.apache.mina.filter.logging.LogLevel r1 = org.apache.mina.filter.logging.LogLevel.TRACE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$mina$filter$logging$LogLevel     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.mina.filter.logging.LogLevel r1 = org.apache.mina.filter.logging.LogLevel.DEBUG     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$mina$filter$logging$LogLevel     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.mina.filter.logging.LogLevel r1 = org.apache.mina.filter.logging.LogLevel.INFO     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$mina$filter$logging$LogLevel     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.mina.filter.logging.LogLevel r1 = org.apache.mina.filter.logging.LogLevel.WARN     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$mina$filter$logging$LogLevel     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.mina.filter.logging.LogLevel r1 = org.apache.mina.filter.logging.LogLevel.ERROR     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.mina.filter.logging.LoggingFilter.AnonymousClass1.<clinit>():void");
        }
    }

    private void log(LogLevel logLevel, String str, Throwable th) {
        int i = AnonymousClass1.$SwitchMap$org$apache$mina$filter$logging$LogLevel[logLevel.ordinal()];
        if (i == 1) {
            this.logger.trace(str, th);
        } else if (i == 2) {
            this.logger.debug(str, th);
        } else if (i == 3) {
            this.logger.info(str, th);
        } else if (i == 4) {
            this.logger.warn(str, th);
        } else if (i == 5) {
            this.logger.error(str, th);
        }
    }

    private void log(LogLevel logLevel, String str, Object obj) {
        int i = AnonymousClass1.$SwitchMap$org$apache$mina$filter$logging$LogLevel[logLevel.ordinal()];
        if (i == 1) {
            this.logger.trace(str, obj);
        } else if (i == 2) {
            this.logger.debug(str, obj);
        } else if (i == 3) {
            this.logger.info(str, obj);
        } else if (i == 4) {
            this.logger.warn(str, obj);
        } else if (i == 5) {
            this.logger.error(str, obj);
        }
    }

    private void log(LogLevel logLevel, String str) {
        int i = AnonymousClass1.$SwitchMap$org$apache$mina$filter$logging$LogLevel[logLevel.ordinal()];
        if (i == 1) {
            this.logger.trace(str);
        } else if (i == 2) {
            this.logger.debug(str);
        } else if (i == 3) {
            this.logger.info(str);
        } else if (i == 4) {
            this.logger.warn(str);
        } else if (i == 5) {
            this.logger.error(str);
        }
    }

    public void exceptionCaught(IoFilter.NextFilter nextFilter, IoSession ioSession, Throwable th) throws Exception {
        log(this.exceptionCaughtLevel, "EXCEPTION :", th);
        nextFilter.exceptionCaught(ioSession, th);
    }

    public void messageReceived(IoFilter.NextFilter nextFilter, IoSession ioSession, Object obj) throws Exception {
        log(this.messageReceivedLevel, "RECEIVED: {}", obj);
        nextFilter.messageReceived(ioSession, obj);
    }

    public void messageSent(IoFilter.NextFilter nextFilter, IoSession ioSession, WriteRequest writeRequest) throws Exception {
        log(this.messageSentLevel, "SENT: {}", writeRequest.getMessage());
        nextFilter.messageSent(ioSession, writeRequest);
    }

    public void sessionCreated(IoFilter.NextFilter nextFilter, IoSession ioSession) throws Exception {
        log(this.sessionCreatedLevel, DebugCoroutineInfoImplKt.CREATED);
        nextFilter.sessionCreated(ioSession);
    }

    public void sessionOpened(IoFilter.NextFilter nextFilter, IoSession ioSession) throws Exception {
        log(this.sessionOpenedLevel, "OPENED");
        nextFilter.sessionOpened(ioSession);
    }

    public void sessionIdle(IoFilter.NextFilter nextFilter, IoSession ioSession, IdleStatus idleStatus) throws Exception {
        log(this.sessionIdleLevel, SpeechConstants.SESSION_STATE_IDLE);
        nextFilter.sessionIdle(ioSession, idleStatus);
    }

    public void sessionClosed(IoFilter.NextFilter nextFilter, IoSession ioSession) throws Exception {
        log(this.sessionClosedLevel, "CLOSED");
        nextFilter.sessionClosed(ioSession);
    }

    public void setExceptionCaughtLogLevel(LogLevel logLevel) {
        this.exceptionCaughtLevel = logLevel;
    }

    public LogLevel getExceptionCaughtLogLevel() {
        return this.exceptionCaughtLevel;
    }

    public void setMessageReceivedLogLevel(LogLevel logLevel) {
        this.messageReceivedLevel = logLevel;
    }

    public LogLevel getMessageReceivedLogLevel() {
        return this.messageReceivedLevel;
    }

    public void setMessageSentLogLevel(LogLevel logLevel) {
        this.messageSentLevel = logLevel;
    }

    public LogLevel getMessageSentLogLevel() {
        return this.messageSentLevel;
    }

    public void setSessionCreatedLogLevel(LogLevel logLevel) {
        this.sessionCreatedLevel = logLevel;
    }

    public LogLevel getSessionCreatedLogLevel() {
        return this.sessionCreatedLevel;
    }

    public void setSessionOpenedLogLevel(LogLevel logLevel) {
        this.sessionOpenedLevel = logLevel;
    }

    public LogLevel getSessionOpenedLogLevel() {
        return this.sessionOpenedLevel;
    }

    public void setSessionIdleLogLevel(LogLevel logLevel) {
        this.sessionIdleLevel = logLevel;
    }

    public LogLevel getSessionIdleLogLevel() {
        return this.sessionIdleLevel;
    }

    public void setSessionClosedLogLevel(LogLevel logLevel) {
        this.sessionClosedLevel = logLevel;
    }

    public LogLevel getSessionClosedLogLevel() {
        return this.sessionClosedLevel;
    }
}
