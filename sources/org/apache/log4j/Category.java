package org.apache.log4j;

import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.apache.log4j.helpers.AppenderAttachableImpl;
import org.apache.log4j.helpers.NullEnumeration;
import org.apache.log4j.spi.AppenderAttachable;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.LoggingEvent;

public class Category implements AppenderAttachable {
    private static final String FQCN;
    static /* synthetic */ Class class$org$apache$log4j$Category;
    AppenderAttachableImpl aai;
    protected boolean additive = true;
    protected volatile Level level;
    protected String name;
    protected volatile Category parent;
    protected LoggerRepository repository;
    protected ResourceBundle resourceBundle;

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class cls = class$org$apache$log4j$Category;
        if (cls == null) {
            cls = class$("org.apache.log4j.Category");
            class$org$apache$log4j$Category = cls;
        }
        FQCN = cls.getName();
    }

    protected Category(String str) {
        this.name = str;
    }

    public synchronized void addAppender(Appender appender) {
        if (this.aai == null) {
            this.aai = new AppenderAttachableImpl();
        }
        this.aai.addAppender(appender);
        this.repository.fireAddAppenderEvent(this, appender);
    }

    public void assertLog(boolean z, String str) {
        if (!z) {
            error(str);
        }
    }

    public void callAppenders(LoggingEvent loggingEvent) {
        int i = 0;
        Category category = this;
        while (true) {
            if (category == null) {
                break;
            }
            synchronized (category) {
                if (category.aai != null) {
                    i += category.aai.appendLoopOnAppenders(loggingEvent);
                }
                if (!category.additive) {
                }
            }
            category = category.parent;
        }
        if (i == 0) {
            this.repository.emitNoAppenderWarning(this);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void closeNestedAppenders() {
        Enumeration allAppenders = getAllAppenders();
        if (allAppenders != null) {
            while (allAppenders.hasMoreElements()) {
                Appender appender = (Appender) allAppenders.nextElement();
                if (appender instanceof AppenderAttachable) {
                    appender.close();
                }
            }
        }
    }

    public void debug(Object obj) {
        if (!this.repository.isDisabled(10000) && Level.DEBUG.isGreaterOrEqual(getEffectiveLevel())) {
            forcedLog(FQCN, Level.DEBUG, obj, (Throwable) null);
        }
    }

    public void debug(Object obj, Throwable th) {
        if (!this.repository.isDisabled(10000) && Level.DEBUG.isGreaterOrEqual(getEffectiveLevel())) {
            forcedLog(FQCN, Level.DEBUG, obj, th);
        }
    }

    public void error(Object obj) {
        if (!this.repository.isDisabled(Priority.ERROR_INT) && Level.ERROR.isGreaterOrEqual(getEffectiveLevel())) {
            forcedLog(FQCN, Level.ERROR, obj, (Throwable) null);
        }
    }

    public void error(Object obj, Throwable th) {
        if (!this.repository.isDisabled(Priority.ERROR_INT) && Level.ERROR.isGreaterOrEqual(getEffectiveLevel())) {
            forcedLog(FQCN, Level.ERROR, obj, th);
        }
    }

    public static Logger exists(String str) {
        return LogManager.exists(str);
    }

    public void fatal(Object obj) {
        if (!this.repository.isDisabled(Priority.FATAL_INT) && Level.FATAL.isGreaterOrEqual(getEffectiveLevel())) {
            forcedLog(FQCN, Level.FATAL, obj, (Throwable) null);
        }
    }

    public void fatal(Object obj, Throwable th) {
        if (!this.repository.isDisabled(Priority.FATAL_INT) && Level.FATAL.isGreaterOrEqual(getEffectiveLevel())) {
            forcedLog(FQCN, Level.FATAL, obj, th);
        }
    }

    /* access modifiers changed from: protected */
    public void forcedLog(String str, Priority priority, Object obj, Throwable th) {
        callAppenders(new LoggingEvent(str, this, priority, obj, th));
    }

    public boolean getAdditivity() {
        return this.additive;
    }

    public synchronized Enumeration getAllAppenders() {
        if (this.aai == null) {
            return NullEnumeration.getInstance();
        }
        return this.aai.getAllAppenders();
    }

    public synchronized Appender getAppender(String str) {
        if (this.aai != null) {
            if (str != null) {
                return this.aai.getAppender(str);
            }
        }
        return null;
    }

    public Level getEffectiveLevel() {
        for (Category category = this; category != null; category = category.parent) {
            if (category.level != null) {
                return category.level;
            }
        }
        return null;
    }

    public Priority getChainedPriority() {
        for (Category category = this; category != null; category = category.parent) {
            if (category.level != null) {
                return category.level;
            }
        }
        return null;
    }

    public static Enumeration getCurrentCategories() {
        return LogManager.getCurrentLoggers();
    }

    public static LoggerRepository getDefaultHierarchy() {
        return LogManager.getLoggerRepository();
    }

    public LoggerRepository getHierarchy() {
        return this.repository;
    }

    public LoggerRepository getLoggerRepository() {
        return this.repository;
    }

    public static Category getInstance(String str) {
        return LogManager.getLogger(str);
    }

    public static Category getInstance(Class cls) {
        return LogManager.getLogger(cls);
    }

    public final String getName() {
        return this.name;
    }

    public final Category getParent() {
        return this.parent;
    }

    public final Level getLevel() {
        return this.level;
    }

    public final Level getPriority() {
        return this.level;
    }

    public static final Category getRoot() {
        return LogManager.getRootLogger();
    }

    public ResourceBundle getResourceBundle() {
        for (Category category = this; category != null; category = category.parent) {
            ResourceBundle resourceBundle2 = category.resourceBundle;
            if (resourceBundle2 != null) {
                return resourceBundle2;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public String getResourceBundleString(String str) {
        ResourceBundle resourceBundle2 = getResourceBundle();
        if (resourceBundle2 == null) {
            return null;
        }
        try {
            return resourceBundle2.getString(str);
        } catch (MissingResourceException unused) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("No resource is associated with key \"");
            stringBuffer.append(str);
            stringBuffer.append("\".");
            error(stringBuffer.toString());
            return null;
        }
    }

    public void info(Object obj) {
        if (!this.repository.isDisabled(20000) && Level.INFO.isGreaterOrEqual(getEffectiveLevel())) {
            forcedLog(FQCN, Level.INFO, obj, (Throwable) null);
        }
    }

    public void info(Object obj, Throwable th) {
        if (!this.repository.isDisabled(20000) && Level.INFO.isGreaterOrEqual(getEffectiveLevel())) {
            forcedLog(FQCN, Level.INFO, obj, th);
        }
    }

    public boolean isAttached(Appender appender) {
        AppenderAttachableImpl appenderAttachableImpl;
        if (appender == null || (appenderAttachableImpl = this.aai) == null) {
            return false;
        }
        return appenderAttachableImpl.isAttached(appender);
    }

    public boolean isDebugEnabled() {
        if (this.repository.isDisabled(10000)) {
            return false;
        }
        return Level.DEBUG.isGreaterOrEqual(getEffectiveLevel());
    }

    public boolean isEnabledFor(Priority priority) {
        if (this.repository.isDisabled(priority.level)) {
            return false;
        }
        return priority.isGreaterOrEqual(getEffectiveLevel());
    }

    public boolean isInfoEnabled() {
        if (this.repository.isDisabled(20000)) {
            return false;
        }
        return Level.INFO.isGreaterOrEqual(getEffectiveLevel());
    }

    public void l7dlog(Priority priority, String str, Throwable th) {
        if (!this.repository.isDisabled(priority.level) && priority.isGreaterOrEqual(getEffectiveLevel())) {
            String resourceBundleString = getResourceBundleString(str);
            if (resourceBundleString != null) {
                str = resourceBundleString;
            }
            forcedLog(FQCN, priority, str, th);
        }
    }

    public void l7dlog(Priority priority, String str, Object[] objArr, Throwable th) {
        if (!this.repository.isDisabled(priority.level) && priority.isGreaterOrEqual(getEffectiveLevel())) {
            String resourceBundleString = getResourceBundleString(str);
            if (resourceBundleString != null) {
                str = MessageFormat.format(resourceBundleString, objArr);
            }
            forcedLog(FQCN, priority, str, th);
        }
    }

    public void log(Priority priority, Object obj, Throwable th) {
        if (!this.repository.isDisabled(priority.level) && priority.isGreaterOrEqual(getEffectiveLevel())) {
            forcedLog(FQCN, priority, obj, th);
        }
    }

    public void log(Priority priority, Object obj) {
        if (!this.repository.isDisabled(priority.level) && priority.isGreaterOrEqual(getEffectiveLevel())) {
            forcedLog(FQCN, priority, obj, (Throwable) null);
        }
    }

    public void log(String str, Priority priority, Object obj, Throwable th) {
        if (!this.repository.isDisabled(priority.level) && priority.isGreaterOrEqual(getEffectiveLevel())) {
            forcedLog(str, priority, obj, th);
        }
    }

    public synchronized void removeAllAppenders() {
        if (this.aai != null) {
            this.aai.removeAllAppenders();
            this.aai = null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0013, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void removeAppender(org.apache.log4j.Appender r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            if (r2 == 0) goto L_0x0012
            org.apache.log4j.helpers.AppenderAttachableImpl r0 = r1.aai     // Catch:{ all -> 0x000f }
            if (r0 != 0) goto L_0x0008
            goto L_0x0012
        L_0x0008:
            org.apache.log4j.helpers.AppenderAttachableImpl r0 = r1.aai     // Catch:{ all -> 0x000f }
            r0.removeAppender((org.apache.log4j.Appender) r2)     // Catch:{ all -> 0x000f }
            monitor-exit(r1)
            return
        L_0x000f:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        L_0x0012:
            monitor-exit(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.log4j.Category.removeAppender(org.apache.log4j.Appender):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0013, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void removeAppender(java.lang.String r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            if (r2 == 0) goto L_0x0012
            org.apache.log4j.helpers.AppenderAttachableImpl r0 = r1.aai     // Catch:{ all -> 0x000f }
            if (r0 != 0) goto L_0x0008
            goto L_0x0012
        L_0x0008:
            org.apache.log4j.helpers.AppenderAttachableImpl r0 = r1.aai     // Catch:{ all -> 0x000f }
            r0.removeAppender((java.lang.String) r2)     // Catch:{ all -> 0x000f }
            monitor-exit(r1)
            return
        L_0x000f:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        L_0x0012:
            monitor-exit(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.log4j.Category.removeAppender(java.lang.String):void");
    }

    public void setAdditivity(boolean z) {
        this.additive = z;
    }

    /* access modifiers changed from: package-private */
    public final void setHierarchy(LoggerRepository loggerRepository) {
        this.repository = loggerRepository;
    }

    public void setLevel(Level level2) {
        this.level = level2;
    }

    public void setPriority(Priority priority) {
        this.level = (Level) priority;
    }

    public void setResourceBundle(ResourceBundle resourceBundle2) {
        this.resourceBundle = resourceBundle2;
    }

    public static void shutdown() {
        LogManager.shutdown();
    }

    public void warn(Object obj) {
        if (!this.repository.isDisabled(30000) && Level.WARN.isGreaterOrEqual(getEffectiveLevel())) {
            forcedLog(FQCN, Level.WARN, obj, (Throwable) null);
        }
    }

    public void warn(Object obj, Throwable th) {
        if (!this.repository.isDisabled(30000) && Level.WARN.isGreaterOrEqual(getEffectiveLevel())) {
            forcedLog(FQCN, Level.WARN, obj, th);
        }
    }
}
