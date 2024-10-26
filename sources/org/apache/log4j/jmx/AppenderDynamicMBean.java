package org.apache.log4j.jmx;

import com.alibaba.android.arouter.compiler.utils.Consts;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Hashtable;
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
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.RuntimeOperationsException;
import org.apache.log4j.Appender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.OptionHandler;

public class AppenderDynamicMBean extends AbstractDynamicMBean {
    private static Logger cat;
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class class$org$apache$log4j$Layout;
    static /* synthetic */ Class class$org$apache$log4j$Priority;
    static /* synthetic */ Class class$org$apache$log4j$jmx$AppenderDynamicMBean;
    private Appender appender;
    private Vector dAttributes = new Vector();
    private String dClassName = getClass().getName();
    private MBeanConstructorInfo[] dConstructors = new MBeanConstructorInfo[1];
    private String dDescription = "This MBean acts as a management facade for log4j appenders.";
    private MBeanOperationInfo[] dOperations = new MBeanOperationInfo[2];
    private Hashtable dynamicProps = new Hashtable(5);

    static {
        Class cls = class$org$apache$log4j$jmx$AppenderDynamicMBean;
        if (cls == null) {
            cls = class$("org.apache.log4j.jmx.AppenderDynamicMBean");
            class$org$apache$log4j$jmx$AppenderDynamicMBean = cls;
        }
        cat = Logger.getLogger(cls);
    }

    public AppenderDynamicMBean(Appender appender2) throws IntrospectionException {
        this.appender = appender2;
        buildDynamicMBeanInfo();
    }

    private void buildDynamicMBeanInfo() throws IntrospectionException {
        int i = 0;
        this.dConstructors[0] = new MBeanConstructorInfo("AppenderDynamicMBean(): Constructs a AppenderDynamicMBean instance", getClass().getConstructors()[0]);
        PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(this.appender.getClass()).getPropertyDescriptors();
        int length = propertyDescriptors.length;
        int i2 = 0;
        while (true) {
            String str = Consts.STRING;
            boolean z = true;
            if (i2 >= length) {
                this.dOperations[i] = new MBeanOperationInfo("activateOptions", "activateOptions(): add an appender", new MBeanParameterInfo[i], "void", 1);
                MBeanParameterInfo[] mBeanParameterInfoArr = new MBeanParameterInfo[1];
                mBeanParameterInfoArr[i] = new MBeanParameterInfo("layout class", str, "layout class");
                this.dOperations[1] = new MBeanOperationInfo("setLayout", "setLayout(): add a layout", mBeanParameterInfoArr, "void", 1);
                return;
            }
            String name = propertyDescriptors[i2].getName();
            Method readMethod = propertyDescriptors[i2].getReadMethod();
            Method writeMethod = propertyDescriptors[i2].getWriteMethod();
            if (readMethod != null) {
                Class<?> returnType = readMethod.getReturnType();
                if (isSupportedType(returnType)) {
                    Class cls = class$org$apache$log4j$Priority;
                    if (cls == null) {
                        cls = class$("org.apache.log4j.Priority");
                        class$org$apache$log4j$Priority = cls;
                    }
                    if (!returnType.isAssignableFrom(cls)) {
                        str = returnType.getName();
                    }
                    String str2 = str;
                    Vector vector = this.dAttributes;
                    if (writeMethod == null) {
                        z = false;
                    }
                    MBeanAttributeInfo mBeanAttributeInfo = r8;
                    MBeanAttributeInfo mBeanAttributeInfo2 = new MBeanAttributeInfo(name, str2, "Dynamic", true, z, false);
                    vector.add(mBeanAttributeInfo);
                    this.dynamicProps.put(name, new MethodUnion(readMethod, writeMethod));
                }
            }
            i2++;
            i = 0;
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    private boolean isSupportedType(Class cls) {
        if (cls.isPrimitive()) {
            return true;
        }
        Class cls2 = class$java$lang$String;
        if (cls2 == null) {
            cls2 = class$(Consts.STRING);
            class$java$lang$String = cls2;
        }
        if (cls == cls2) {
            return true;
        }
        Class cls3 = class$org$apache$log4j$Priority;
        if (cls3 == null) {
            cls3 = class$("org.apache.log4j.Priority");
            class$org$apache$log4j$Priority = cls3;
        }
        return cls.isAssignableFrom(cls3);
    }

    public Object getAttribute(String str) throws AttributeNotFoundException, MBeanException, ReflectionException {
        if (str != null) {
            Logger logger = cat;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("getAttribute called with [");
            stringBuffer.append(str);
            stringBuffer.append("].");
            logger.debug(stringBuffer.toString());
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("appender=");
            stringBuffer2.append(this.appender.getName());
            stringBuffer2.append(",layout");
            if (str.startsWith(stringBuffer2.toString())) {
                try {
                    StringBuffer stringBuffer3 = new StringBuffer();
                    stringBuffer3.append("log4j:");
                    stringBuffer3.append(str);
                    return new ObjectName(stringBuffer3.toString());
                } catch (Exception e) {
                    cat.error("attributeName", e);
                }
            }
            MethodUnion methodUnion = (MethodUnion) this.dynamicProps.get(str);
            if (methodUnion == null || methodUnion.readMethod == null) {
                StringBuffer stringBuffer4 = new StringBuffer();
                stringBuffer4.append("Cannot find ");
                stringBuffer4.append(str);
                stringBuffer4.append(" attribute in ");
                stringBuffer4.append(this.dClassName);
                throw new AttributeNotFoundException(stringBuffer4.toString());
            }
            try {
                return methodUnion.readMethod.invoke(this.appender, (Object[]) null);
            } catch (Exception unused) {
                return null;
            }
        } else {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Attribute name cannot be null");
            StringBuffer stringBuffer5 = new StringBuffer();
            stringBuffer5.append("Cannot invoke a getter of ");
            stringBuffer5.append(this.dClassName);
            stringBuffer5.append(" with null attribute name");
            throw new RuntimeOperationsException(illegalArgumentException, stringBuffer5.toString());
        }
    }

    /* access modifiers changed from: protected */
    public Logger getLogger() {
        return cat;
    }

    public MBeanInfo getMBeanInfo() {
        cat.debug("getMBeanInfo called.");
        MBeanAttributeInfo[] mBeanAttributeInfoArr = new MBeanAttributeInfo[this.dAttributes.size()];
        this.dAttributes.toArray(mBeanAttributeInfoArr);
        return new MBeanInfo(this.dClassName, this.dDescription, mBeanAttributeInfoArr, this.dConstructors, this.dOperations, new MBeanNotificationInfo[0]);
    }

    public Object invoke(String str, Object[] objArr, String[] strArr) throws MBeanException, ReflectionException {
        if (str.equals("activateOptions")) {
            Appender appender2 = this.appender;
            if (appender2 instanceof OptionHandler) {
                ((OptionHandler) appender2).activateOptions();
                return "Options activated.";
            }
        }
        if (str.equals("setLayout")) {
            String str2 = objArr[0];
            Class cls = class$org$apache$log4j$Layout;
            if (cls == null) {
                cls = class$("org.apache.log4j.Layout");
                class$org$apache$log4j$Layout = cls;
            }
            Layout layout = (Layout) OptionConverter.instantiateByClassName(str2, cls, (Object) null);
            this.appender.setLayout(layout);
            registerLayoutMBean(layout);
        }
        return null;
    }

    public ObjectName preRegister(MBeanServer mBeanServer, ObjectName objectName) {
        Logger logger = cat;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("preRegister called. Server=");
        stringBuffer.append(mBeanServer);
        stringBuffer.append(", name=");
        stringBuffer.append(objectName);
        logger.debug(stringBuffer.toString());
        this.server = mBeanServer;
        registerLayoutMBean(this.appender.getLayout());
        return objectName;
    }

    /* access modifiers changed from: package-private */
    public void registerLayoutMBean(Layout layout) {
        if (layout != null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(this.appender.getName());
            stringBuffer.append(",layout=");
            stringBuffer.append(layout.getClass().getName());
            String stringBuffer2 = stringBuffer.toString();
            Logger logger = cat;
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("Adding LayoutMBean:");
            stringBuffer3.append(stringBuffer2);
            logger.debug(stringBuffer3.toString());
            try {
                LayoutDynamicMBean layoutDynamicMBean = new LayoutDynamicMBean(layout);
                StringBuffer stringBuffer4 = new StringBuffer();
                stringBuffer4.append("log4j:appender=");
                stringBuffer4.append(stringBuffer2);
                this.server.registerMBean(layoutDynamicMBean, new ObjectName(stringBuffer4.toString()));
                Vector vector = this.dAttributes;
                StringBuffer stringBuffer5 = new StringBuffer();
                stringBuffer5.append("appender=");
                stringBuffer5.append(stringBuffer2);
                String stringBuffer6 = stringBuffer5.toString();
                StringBuffer stringBuffer7 = new StringBuffer();
                stringBuffer7.append("The ");
                stringBuffer7.append(stringBuffer2);
                stringBuffer7.append(" layout.");
                vector.add(new MBeanAttributeInfo(stringBuffer6, "javax.management.ObjectName", stringBuffer7.toString(), true, true, false));
            } catch (Exception e) {
                Logger logger2 = cat;
                StringBuffer stringBuffer8 = new StringBuffer();
                stringBuffer8.append("Could not add DynamicLayoutMBean for [");
                stringBuffer8.append(stringBuffer2);
                stringBuffer8.append("].");
                logger2.error(stringBuffer8.toString(), e);
            }
        }
    }

    public void setAttribute(Attribute attribute) throws AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException {
        if (attribute != null) {
            String name = attribute.getName();
            Object value = attribute.getValue();
            if (name != null) {
                MethodUnion methodUnion = (MethodUnion) this.dynamicProps.get(name);
                if (methodUnion != null && methodUnion.writeMethod != null) {
                    Object[] objArr = new Object[1];
                    Class cls = methodUnion.writeMethod.getParameterTypes()[0];
                    Class cls2 = class$org$apache$log4j$Priority;
                    if (cls2 == null) {
                        cls2 = class$("org.apache.log4j.Priority");
                        class$org$apache$log4j$Priority = cls2;
                    }
                    if (cls == cls2) {
                        value = OptionConverter.toLevel((String) value, (Level) getAttribute(name));
                    }
                    objArr[0] = value;
                    try {
                        methodUnion.writeMethod.invoke(this.appender, objArr);
                    } catch (Exception e) {
                        cat.error("FIXME", e);
                    }
                } else if (!name.endsWith(".layout")) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Attribute ");
                    stringBuffer.append(name);
                    stringBuffer.append(" not found in ");
                    stringBuffer.append(getClass().getName());
                    throw new AttributeNotFoundException(stringBuffer.toString());
                }
            } else {
                IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Attribute name cannot be null");
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Cannot invoke the setter of ");
                stringBuffer2.append(this.dClassName);
                stringBuffer2.append(" with null attribute name");
                throw new RuntimeOperationsException(illegalArgumentException, stringBuffer2.toString());
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
