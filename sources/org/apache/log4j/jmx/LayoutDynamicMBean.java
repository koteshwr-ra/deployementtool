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
import javax.management.ReflectionException;
import javax.management.RuntimeOperationsException;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.OptionHandler;

public class LayoutDynamicMBean extends AbstractDynamicMBean {
    private static Logger cat;
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class class$org$apache$log4j$Level;
    static /* synthetic */ Class class$org$apache$log4j$Priority;
    static /* synthetic */ Class class$org$apache$log4j$jmx$LayoutDynamicMBean;
    private Vector dAttributes = new Vector();
    private String dClassName = getClass().getName();
    private MBeanConstructorInfo[] dConstructors = new MBeanConstructorInfo[1];
    private String dDescription = "This MBean acts as a management facade for log4j layouts.";
    private MBeanOperationInfo[] dOperations = new MBeanOperationInfo[1];
    private Hashtable dynamicProps = new Hashtable(5);
    private Layout layout;

    static {
        Class cls = class$org$apache$log4j$jmx$LayoutDynamicMBean;
        if (cls == null) {
            cls = class$("org.apache.log4j.jmx.LayoutDynamicMBean");
            class$org$apache$log4j$jmx$LayoutDynamicMBean = cls;
        }
        cat = Logger.getLogger(cls);
    }

    public LayoutDynamicMBean(Layout layout2) throws IntrospectionException {
        this.layout = layout2;
        buildDynamicMBeanInfo();
    }

    private void buildDynamicMBeanInfo() throws IntrospectionException {
        int i = 0;
        this.dConstructors[0] = new MBeanConstructorInfo("LayoutDynamicMBean(): Constructs a LayoutDynamicMBean instance", getClass().getConstructors()[0]);
        PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(this.layout.getClass()).getPropertyDescriptors();
        int length = propertyDescriptors.length;
        int i2 = 0;
        while (i2 < length) {
            String name = propertyDescriptors[i2].getName();
            Method readMethod = propertyDescriptors[i2].getReadMethod();
            Method writeMethod = propertyDescriptors[i2].getWriteMethod();
            if (readMethod != null) {
                Class<?> returnType = readMethod.getReturnType();
                if (isSupportedType(returnType)) {
                    Class cls = class$org$apache$log4j$Level;
                    if (cls == null) {
                        cls = class$("org.apache.log4j.Level");
                        class$org$apache$log4j$Level = cls;
                    }
                    String name2 = returnType.isAssignableFrom(cls) ? Consts.STRING : returnType.getName();
                    Vector vector = this.dAttributes;
                    MBeanAttributeInfo mBeanAttributeInfo = r6;
                    MBeanAttributeInfo mBeanAttributeInfo2 = new MBeanAttributeInfo(name, name2, "Dynamic", true, writeMethod != null, false);
                    vector.add(mBeanAttributeInfo);
                    this.dynamicProps.put(name, new MethodUnion(readMethod, writeMethod));
                }
            }
            i2++;
            i = 0;
        }
        this.dOperations[i] = new MBeanOperationInfo("activateOptions", "activateOptions(): add an layout", new MBeanParameterInfo[i], "void", 1);
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
        Class cls3 = class$org$apache$log4j$Level;
        if (cls3 == null) {
            cls3 = class$("org.apache.log4j.Level");
            class$org$apache$log4j$Level = cls3;
        }
        return cls.isAssignableFrom(cls3);
    }

    public Object getAttribute(String str) throws AttributeNotFoundException, MBeanException, ReflectionException {
        if (str != null) {
            MethodUnion methodUnion = (MethodUnion) this.dynamicProps.get(str);
            Logger logger = cat;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("----name=");
            stringBuffer.append(str);
            stringBuffer.append(", mu=");
            stringBuffer.append(methodUnion);
            logger.debug(stringBuffer.toString());
            if (methodUnion == null || methodUnion.readMethod == null) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Cannot find ");
                stringBuffer2.append(str);
                stringBuffer2.append(" attribute in ");
                stringBuffer2.append(this.dClassName);
                throw new AttributeNotFoundException(stringBuffer2.toString());
            }
            try {
                return methodUnion.readMethod.invoke(this.layout, (Object[]) null);
            } catch (Exception unused) {
                return null;
            }
        } else {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Attribute name cannot be null");
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("Cannot invoke a getter of ");
            stringBuffer3.append(this.dClassName);
            stringBuffer3.append(" with null attribute name");
            throw new RuntimeOperationsException(illegalArgumentException, stringBuffer3.toString());
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
        if (!str.equals("activateOptions")) {
            return null;
        }
        Layout layout2 = this.layout;
        if (!(layout2 instanceof OptionHandler)) {
            return null;
        }
        layout2.activateOptions();
        return "Options activated.";
    }

    public void setAttribute(Attribute attribute) throws AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException {
        if (attribute != null) {
            String name = attribute.getName();
            Object value = attribute.getValue();
            if (name != null) {
                MethodUnion methodUnion = (MethodUnion) this.dynamicProps.get(name);
                if (methodUnion == null || methodUnion.writeMethod == null) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Attribute ");
                    stringBuffer.append(name);
                    stringBuffer.append(" not found in ");
                    stringBuffer.append(getClass().getName());
                    throw new AttributeNotFoundException(stringBuffer.toString());
                }
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
                    methodUnion.writeMethod.invoke(this.layout, objArr);
                } catch (Exception e) {
                    cat.error("FIXME", e);
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
