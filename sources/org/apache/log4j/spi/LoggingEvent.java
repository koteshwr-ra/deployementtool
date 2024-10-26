package org.apache.log4j.spi;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Hashtable;
import org.apache.log4j.Category;
import org.apache.log4j.Level;
import org.apache.log4j.MDC;
import org.apache.log4j.NDC;
import org.apache.log4j.Priority;
import org.apache.log4j.helpers.Loader;
import org.apache.log4j.helpers.LogLog;

public class LoggingEvent implements Serializable {
    static final Integer[] PARAM_ARRAY = new Integer[1];
    static final String TO_LEVEL = "toLevel";
    static final Class[] TO_LEVEL_PARAMS = {Integer.TYPE};
    static /* synthetic */ Class class$org$apache$log4j$Level = null;
    static final Hashtable methodCache = new Hashtable(3);
    static final long serialVersionUID = -868428216207166145L;
    private static long startTime = System.currentTimeMillis();
    public final String categoryName;
    public final transient String fqnOfCategoryClass;
    public transient Priority level;
    private LocationInfo locationInfo;
    private transient Category logger;
    private Hashtable mdcCopy;
    private boolean mdcCopyLookupRequired = true;
    private transient Object message;
    private String ndc;
    private boolean ndcLookupRequired = true;
    private String renderedMessage;
    private String threadName;
    private ThrowableInformation throwableInfo;
    public final long timeStamp;

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public LoggingEvent(String str, Category category, Priority priority, Object obj, Throwable th) {
        this.fqnOfCategoryClass = str;
        this.logger = category;
        this.categoryName = category.getName();
        this.level = priority;
        this.message = obj;
        if (th != null) {
            this.throwableInfo = new ThrowableInformation(th);
        }
        this.timeStamp = System.currentTimeMillis();
    }

    public LoggingEvent(String str, Category category, long j, Priority priority, Object obj, Throwable th) {
        this.fqnOfCategoryClass = str;
        this.logger = category;
        this.categoryName = category.getName();
        this.level = priority;
        this.message = obj;
        if (th != null) {
            this.throwableInfo = new ThrowableInformation(th);
        }
        this.timeStamp = j;
    }

    public LocationInfo getLocationInformation() {
        if (this.locationInfo == null) {
            this.locationInfo = new LocationInfo(new Throwable(), this.fqnOfCategoryClass);
        }
        return this.locationInfo;
    }

    public Level getLevel() {
        return (Level) this.level;
    }

    public String getLoggerName() {
        return this.categoryName;
    }

    public Object getMessage() {
        Object obj = this.message;
        if (obj != null) {
            return obj;
        }
        return getRenderedMessage();
    }

    public String getNDC() {
        if (this.ndcLookupRequired) {
            this.ndcLookupRequired = false;
            this.ndc = NDC.get();
        }
        return this.ndc;
    }

    public Object getMDC(String str) {
        Object obj;
        Hashtable hashtable = this.mdcCopy;
        if (hashtable == null || (obj = hashtable.get(str)) == null) {
            return MDC.get(str);
        }
        return obj;
    }

    public void getMDCCopy() {
        if (this.mdcCopyLookupRequired) {
            this.mdcCopyLookupRequired = false;
            Hashtable context = MDC.getContext();
            if (context != null) {
                this.mdcCopy = (Hashtable) context.clone();
            }
        }
    }

    public String getRenderedMessage() {
        Object obj;
        if (this.renderedMessage == null && (obj = this.message) != null) {
            if (obj instanceof String) {
                this.renderedMessage = (String) obj;
            } else {
                LoggerRepository loggerRepository = this.logger.getLoggerRepository();
                if (loggerRepository instanceof RendererSupport) {
                    this.renderedMessage = ((RendererSupport) loggerRepository).getRendererMap().findAndRender(this.message);
                } else {
                    this.renderedMessage = this.message.toString();
                }
            }
        }
        return this.renderedMessage;
    }

    public static long getStartTime() {
        return startTime;
    }

    public String getThreadName() {
        if (this.threadName == null) {
            this.threadName = Thread.currentThread().getName();
        }
        return this.threadName;
    }

    public ThrowableInformation getThrowableInformation() {
        return this.throwableInfo;
    }

    public String[] getThrowableStrRep() {
        ThrowableInformation throwableInformation = this.throwableInfo;
        if (throwableInformation == null) {
            return null;
        }
        return throwableInformation.getThrowableStrRep();
    }

    private void readLevel(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        int readInt = objectInputStream.readInt();
        try {
            String str = (String) objectInputStream.readObject();
            if (str == null) {
                this.level = Level.toLevel(readInt);
                return;
            }
            Method method = (Method) methodCache.get(str);
            if (method == null) {
                method = Loader.loadClass(str).getDeclaredMethod(TO_LEVEL, TO_LEVEL_PARAMS);
                methodCache.put(str, method);
            }
            PARAM_ARRAY[0] = new Integer(readInt);
            this.level = (Level) method.invoke((Object) null, PARAM_ARRAY);
        } catch (Exception e) {
            LogLog.warn("Level deserialization failed, reverting to default.", e);
            this.level = Level.toLevel(readInt);
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        readLevel(objectInputStream);
        if (this.locationInfo == null) {
            this.locationInfo = new LocationInfo((Throwable) null, (String) null);
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        getThreadName();
        getRenderedMessage();
        getNDC();
        getMDCCopy();
        getThrowableStrRep();
        objectOutputStream.defaultWriteObject();
        writeLevel(objectOutputStream);
    }

    private void writeLevel(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(this.level.toInt());
        Class<?> cls = this.level.getClass();
        Class<?> cls2 = class$org$apache$log4j$Level;
        if (cls2 == null) {
            cls2 = class$("org.apache.log4j.Level");
            class$org$apache$log4j$Level = cls2;
        }
        if (cls == cls2) {
            objectOutputStream.writeObject((Object) null);
        } else {
            objectOutputStream.writeObject(cls.getName());
        }
    }
}
