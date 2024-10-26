package org.apache.log4j.jmx;

import com.alibaba.android.arouter.compiler.utils.Consts;
import com.limpoxe.support.servicemanager.ServiceProvider;
import java.util.Vector;
import javax.management.Attribute;
import javax.management.AttributeNotFoundException;
import javax.management.InvalidAttributeValueException;
import javax.management.ListenerNotFoundException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanConstructorInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanNotificationInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanParameterInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcaster;
import javax.management.NotificationBroadcasterSupport;
import javax.management.NotificationFilter;
import javax.management.NotificationFilterSupport;
import javax.management.NotificationListener;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.RuntimeOperationsException;
import org.apache.log4j.Appender;
import org.apache.log4j.Category;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.HierarchyEventListener;
import org.apache.log4j.spi.LoggerRepository;

public class HierarchyDynamicMBean extends AbstractDynamicMBean implements HierarchyEventListener, NotificationBroadcaster {
    static final String ADD_APPENDER = "addAppender.";
    static final String THRESHOLD = "threshold";
    static /* synthetic */ Class class$org$apache$log4j$jmx$HierarchyDynamicMBean;
    private static Logger log;
    private String dClassName = getClass().getName();
    private MBeanConstructorInfo[] dConstructors = new MBeanConstructorInfo[1];
    private String dDescription = "This MBean acts as a management facade for org.apache.log4j.Hierarchy.";
    private MBeanOperationInfo[] dOperations = new MBeanOperationInfo[1];
    private LoggerRepository hierarchy = LogManager.getLoggerRepository();
    private NotificationBroadcasterSupport nbs = new NotificationBroadcasterSupport();
    private Vector vAttributes = new Vector();

    static {
        Class cls = class$org$apache$log4j$jmx$HierarchyDynamicMBean;
        if (cls == null) {
            cls = class$("org.apache.log4j.jmx.HierarchyDynamicMBean");
            class$org$apache$log4j$jmx$HierarchyDynamicMBean = cls;
        }
        log = Logger.getLogger(cls);
    }

    public HierarchyDynamicMBean() {
        buildDynamicMBeanInfo();
    }

    private void buildDynamicMBeanInfo() {
        this.dConstructors[0] = new MBeanConstructorInfo("HierarchyDynamicMBean(): Constructs a HierarchyDynamicMBean instance", getClass().getConstructors()[0]);
        this.vAttributes.add(new MBeanAttributeInfo(THRESHOLD, Consts.STRING, "The \"threshold\" state of the hiearchy.", true, true, false));
        this.dOperations[0] = new MBeanOperationInfo("addLoggerMBean", "addLoggerMBean(): add a loggerMBean", new MBeanParameterInfo[]{new MBeanParameterInfo(ServiceProvider.NAME, Consts.STRING, "Create a logger MBean")}, "javax.management.ObjectName", 1);
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public void addAppenderEvent(Category category, Appender appender) {
        Logger logger = log;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("addAppenderEvent called: logger=");
        stringBuffer.append(category.getName());
        stringBuffer.append(", appender=");
        stringBuffer.append(appender.getName());
        logger.debug(stringBuffer.toString());
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append(ADD_APPENDER);
        stringBuffer2.append(category.getName());
        Notification notification = new Notification(stringBuffer2.toString(), this, 0);
        notification.setUserData(appender);
        log.debug("sending notification.");
        this.nbs.sendNotification(notification);
    }

    public ObjectName addLoggerMBean(String str) {
        Logger exists = LogManager.exists(str);
        if (exists != null) {
            return addLoggerMBean(exists);
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public ObjectName addLoggerMBean(Logger logger) {
        String name = logger.getName();
        ObjectName objectName = null;
        try {
            LoggerDynamicMBean loggerDynamicMBean = new LoggerDynamicMBean(logger);
            ObjectName objectName2 = new ObjectName("log4j", "logger", name);
            try {
                this.server.registerMBean(loggerDynamicMBean, objectName2);
                NotificationFilterSupport notificationFilterSupport = new NotificationFilterSupport();
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(ADD_APPENDER);
                stringBuffer.append(logger.getName());
                notificationFilterSupport.enableType(stringBuffer.toString());
                Logger logger2 = log;
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("---Adding logger [");
                stringBuffer2.append(name);
                stringBuffer2.append("] as listener.");
                logger2.debug(stringBuffer2.toString());
                this.nbs.addNotificationListener(loggerDynamicMBean, notificationFilterSupport, (Object) null);
                Vector vector = this.vAttributes;
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("logger=");
                stringBuffer3.append(name);
                String stringBuffer4 = stringBuffer3.toString();
                StringBuffer stringBuffer5 = new StringBuffer();
                stringBuffer5.append("The ");
                stringBuffer5.append(name);
                stringBuffer5.append(" logger.");
                vector.add(new MBeanAttributeInfo(stringBuffer4, "javax.management.ObjectName", stringBuffer5.toString(), true, true, false));
                return objectName2;
            } catch (Exception e) {
                e = e;
                objectName = objectName2;
            }
        } catch (Exception e2) {
            e = e2;
            Logger logger3 = log;
            StringBuffer stringBuffer6 = new StringBuffer();
            stringBuffer6.append("Could not add loggerMBean for [");
            stringBuffer6.append(name);
            stringBuffer6.append("].");
            logger3.error(stringBuffer6.toString(), e);
            return objectName;
        }
    }

    public void addNotificationListener(NotificationListener notificationListener, NotificationFilter notificationFilter, Object obj) {
        this.nbs.addNotificationListener(notificationListener, notificationFilter, obj);
    }

    public Object getAttribute(String str) throws AttributeNotFoundException, MBeanException, ReflectionException {
        String str2;
        if (str != null) {
            Logger logger = log;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Called getAttribute with [");
            stringBuffer.append(str);
            stringBuffer.append("].");
            logger.debug(stringBuffer.toString());
            if (str.equals(THRESHOLD)) {
                return this.hierarchy.getThreshold();
            }
            if (str.startsWith("logger")) {
                int indexOf = str.indexOf("%3D");
                if (indexOf > 0) {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append(str.substring(0, indexOf));
                    stringBuffer2.append('=');
                    stringBuffer2.append(str.substring(indexOf + 3));
                    str2 = stringBuffer2.toString();
                } else {
                    str2 = str;
                }
                try {
                    StringBuffer stringBuffer3 = new StringBuffer();
                    stringBuffer3.append("log4j:");
                    stringBuffer3.append(str2);
                    return new ObjectName(stringBuffer3.toString());
                } catch (Exception unused) {
                    Logger logger2 = log;
                    StringBuffer stringBuffer4 = new StringBuffer();
                    stringBuffer4.append("Could not create ObjectName");
                    stringBuffer4.append(str2);
                    logger2.error(stringBuffer4.toString());
                }
            }
            StringBuffer stringBuffer5 = new StringBuffer();
            stringBuffer5.append("Cannot find ");
            stringBuffer5.append(str);
            stringBuffer5.append(" attribute in ");
            stringBuffer5.append(this.dClassName);
            throw new AttributeNotFoundException(stringBuffer5.toString());
        }
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Attribute name cannot be null");
        StringBuffer stringBuffer6 = new StringBuffer();
        stringBuffer6.append("Cannot invoke a getter of ");
        stringBuffer6.append(this.dClassName);
        stringBuffer6.append(" with null attribute name");
        throw new RuntimeOperationsException(illegalArgumentException, stringBuffer6.toString());
    }

    /* access modifiers changed from: protected */
    public Logger getLogger() {
        return log;
    }

    public MBeanInfo getMBeanInfo() {
        MBeanAttributeInfo[] mBeanAttributeInfoArr = new MBeanAttributeInfo[this.vAttributes.size()];
        this.vAttributes.toArray(mBeanAttributeInfoArr);
        return new MBeanInfo(this.dClassName, this.dDescription, mBeanAttributeInfoArr, this.dConstructors, this.dOperations, new MBeanNotificationInfo[0]);
    }

    public MBeanNotificationInfo[] getNotificationInfo() {
        return this.nbs.getNotificationInfo();
    }

    public Object invoke(String str, Object[] objArr, String[] strArr) throws MBeanException, ReflectionException {
        if (str == null) {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Operation name cannot be null");
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Cannot invoke a null operation in ");
            stringBuffer.append(this.dClassName);
            throw new RuntimeOperationsException(illegalArgumentException, stringBuffer.toString());
        } else if (str.equals("addLoggerMBean")) {
            return addLoggerMBean(objArr[0]);
        } else {
            NoSuchMethodException noSuchMethodException = new NoSuchMethodException(str);
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Cannot find the operation ");
            stringBuffer2.append(str);
            stringBuffer2.append(" in ");
            stringBuffer2.append(this.dClassName);
            throw new ReflectionException(noSuchMethodException, stringBuffer2.toString());
        }
    }

    public void postRegister(Boolean bool) {
        log.debug("postRegister is called.");
        this.hierarchy.addHierarchyEventListener(this);
        addLoggerMBean(this.hierarchy.getRootLogger());
    }

    public void removeAppenderEvent(Category category, Appender appender) {
        Logger logger = log;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("removeAppenderCalled: logger=");
        stringBuffer.append(category.getName());
        stringBuffer.append(", appender=");
        stringBuffer.append(appender.getName());
        logger.debug(stringBuffer.toString());
    }

    public void removeNotificationListener(NotificationListener notificationListener) throws ListenerNotFoundException {
        this.nbs.removeNotificationListener(notificationListener);
    }

    public void setAttribute(Attribute attribute) throws AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException {
        if (attribute != null) {
            String name = attribute.getName();
            Object value = attribute.getValue();
            if (name == null) {
                IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Attribute name cannot be null");
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Cannot invoke the setter of ");
                stringBuffer.append(this.dClassName);
                stringBuffer.append(" with null attribute name");
                throw new RuntimeOperationsException(illegalArgumentException, stringBuffer.toString());
            } else if (name.equals(THRESHOLD)) {
                this.hierarchy.setThreshold(OptionConverter.toLevel((String) value, this.hierarchy.getThreshold()));
            }
        } else {
            IllegalArgumentException illegalArgumentException2 = new IllegalArgumentException("Attribute cannot be null");
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Cannot invoke a setter of ");
            stringBuffer2.append(this.dClassName);
            stringBuffer2.append(" with null attribute");
            throw new RuntimeOperationsException(illegalArgumentException2, stringBuffer2.toString());
        }
    }
}
