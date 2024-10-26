package org.apache.log4j.jmx;

import com.alibaba.android.arouter.compiler.utils.Consts;
import com.limpoxe.support.servicemanager.ServiceProvider;
import java.util.Enumeration;
import java.util.Vector;
import javax.management.Attribute;
import javax.management.AttributeNotFoundException;
import javax.management.InvalidAttributeValueException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanConstructorInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanNotificationInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanParameterInfo;
import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.RuntimeOperationsException;
import org.apache.log4j.Appender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.DateLayout;
import org.apache.log4j.helpers.OptionConverter;

public class LoggerDynamicMBean extends AbstractDynamicMBean implements NotificationListener {
    private static Logger cat;
    static /* synthetic */ Class class$org$apache$log4j$Appender;
    static /* synthetic */ Class class$org$apache$log4j$jmx$LoggerDynamicMBean;
    private Vector dAttributes = new Vector();
    private String dClassName = getClass().getName();
    private MBeanConstructorInfo[] dConstructors = new MBeanConstructorInfo[1];
    private String dDescription = "This MBean acts as a management facade for a org.apache.log4j.Logger instance.";
    private MBeanOperationInfo[] dOperations = new MBeanOperationInfo[1];
    private Logger logger;

    static {
        Class cls = class$org$apache$log4j$jmx$LoggerDynamicMBean;
        if (cls == null) {
            cls = class$("org.apache.log4j.jmx.LoggerDynamicMBean");
            class$org$apache$log4j$jmx$LoggerDynamicMBean = cls;
        }
        cat = Logger.getLogger(cls);
    }

    public LoggerDynamicMBean(Logger logger2) {
        this.logger = logger2;
        buildDynamicMBeanInfo();
    }

    private void buildDynamicMBeanInfo() {
        this.dConstructors[0] = new MBeanConstructorInfo("HierarchyDynamicMBean(): Constructs a HierarchyDynamicMBean instance", getClass().getConstructors()[0]);
        this.dAttributes.add(new MBeanAttributeInfo(ServiceProvider.NAME, Consts.STRING, "The name of this Logger.", true, false, false));
        this.dAttributes.add(new MBeanAttributeInfo("priority", Consts.STRING, "The priority of this logger.", true, true, false));
        this.dOperations[0] = new MBeanOperationInfo("addAppender", "addAppender(): add an appender", new MBeanParameterInfo[]{new MBeanParameterInfo("class name", Consts.STRING, "add an appender to this logger"), new MBeanParameterInfo("appender name", Consts.STRING, "name of the appender")}, "void", 1);
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    /* access modifiers changed from: package-private */
    public void addAppender(String str, String str2) {
        Logger logger2 = cat;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("addAppender called with ");
        stringBuffer.append(str);
        stringBuffer.append(", ");
        stringBuffer.append(str2);
        logger2.debug(stringBuffer.toString());
        Class cls = class$org$apache$log4j$Appender;
        if (cls == null) {
            cls = class$("org.apache.log4j.Appender");
            class$org$apache$log4j$Appender = cls;
        }
        Appender appender = (Appender) OptionConverter.instantiateByClassName(str, cls, (Object) null);
        appender.setName(str2);
        this.logger.addAppender(appender);
    }

    /* access modifiers changed from: package-private */
    public void appenderMBeanRegistration() {
        Enumeration allAppenders = this.logger.getAllAppenders();
        while (allAppenders.hasMoreElements()) {
            registerAppenderMBean((Appender) allAppenders.nextElement());
        }
    }

    public Object getAttribute(String str) throws AttributeNotFoundException, MBeanException, ReflectionException {
        if (str == null) {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Attribute name cannot be null");
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Cannot invoke a getter of ");
            stringBuffer.append(this.dClassName);
            stringBuffer.append(" with null attribute name");
            throw new RuntimeOperationsException(illegalArgumentException, stringBuffer.toString());
        } else if (str.equals(ServiceProvider.NAME)) {
            return this.logger.getName();
        } else {
            if (str.equals("priority")) {
                Level level = this.logger.getLevel();
                if (level == null) {
                    return null;
                }
                return level.toString();
            }
            if (str.startsWith("appender=")) {
                try {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("log4j:");
                    stringBuffer2.append(str);
                    return new ObjectName(stringBuffer2.toString());
                } catch (Exception unused) {
                    Logger logger2 = cat;
                    StringBuffer stringBuffer3 = new StringBuffer();
                    stringBuffer3.append("Could not create ObjectName");
                    stringBuffer3.append(str);
                    logger2.error(stringBuffer3.toString());
                }
            }
            StringBuffer stringBuffer4 = new StringBuffer();
            stringBuffer4.append("Cannot find ");
            stringBuffer4.append(str);
            stringBuffer4.append(" attribute in ");
            stringBuffer4.append(this.dClassName);
            throw new AttributeNotFoundException(stringBuffer4.toString());
        }
    }

    /* access modifiers changed from: protected */
    public Logger getLogger() {
        return this.logger;
    }

    public MBeanInfo getMBeanInfo() {
        MBeanAttributeInfo[] mBeanAttributeInfoArr = new MBeanAttributeInfo[this.dAttributes.size()];
        this.dAttributes.toArray(mBeanAttributeInfoArr);
        return new MBeanInfo(this.dClassName, this.dDescription, mBeanAttributeInfoArr, this.dConstructors, this.dOperations, new MBeanNotificationInfo[0]);
    }

    public void handleNotification(Notification notification, Object obj) {
        Logger logger2 = cat;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Received notification: ");
        stringBuffer.append(notification.getType());
        logger2.debug(stringBuffer.toString());
        registerAppenderMBean((Appender) notification.getUserData());
    }

    public Object invoke(String str, Object[] objArr, String[] strArr) throws MBeanException, ReflectionException {
        if (!str.equals("addAppender")) {
            return null;
        }
        addAppender(objArr[0], objArr[1]);
        return "Hello world.";
    }

    public void postRegister(Boolean bool) {
        appenderMBeanRegistration();
    }

    /* access modifiers changed from: package-private */
    public void registerAppenderMBean(Appender appender) {
        String name = appender.getName();
        Logger logger2 = cat;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Adding AppenderMBean for appender named ");
        stringBuffer.append(name);
        logger2.debug(stringBuffer.toString());
        try {
            this.server.registerMBean(new AppenderDynamicMBean(appender), new ObjectName("log4j", "appender", name));
            Vector vector = this.dAttributes;
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("appender=");
            stringBuffer2.append(name);
            String stringBuffer3 = stringBuffer2.toString();
            StringBuffer stringBuffer4 = new StringBuffer();
            stringBuffer4.append("The ");
            stringBuffer4.append(name);
            stringBuffer4.append(" appender.");
            vector.add(new MBeanAttributeInfo(stringBuffer3, "javax.management.ObjectName", stringBuffer4.toString(), true, true, false));
        } catch (Exception e) {
            Logger logger3 = cat;
            StringBuffer stringBuffer5 = new StringBuffer();
            stringBuffer5.append("Could not add appenderMBean for [");
            stringBuffer5.append(name);
            stringBuffer5.append("].");
            logger3.error(stringBuffer5.toString(), e);
        }
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
            } else if (!name.equals("priority")) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Attribute ");
                stringBuffer2.append(name);
                stringBuffer2.append(" not found in ");
                stringBuffer2.append(getClass().getName());
                throw new AttributeNotFoundException(stringBuffer2.toString());
            } else if (value instanceof String) {
                String str = (String) value;
                this.logger.setLevel(str.equalsIgnoreCase(DateLayout.NULL_DATE_FORMAT) ? null : OptionConverter.toLevel(str, this.logger.getLevel()));
            }
        } else {
            IllegalArgumentException illegalArgumentException2 = new IllegalArgumentException("Attribute cannot be null");
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("Cannot invoke a setter of ");
            stringBuffer3.append(this.dClassName);
            stringBuffer3.append(" with null attribute");
            throw new RuntimeOperationsException(illegalArgumentException2, stringBuffer3.toString());
        }
    }
}
