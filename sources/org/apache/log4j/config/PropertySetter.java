package org.apache.log4j.config;

import com.alibaba.android.arouter.utils.Consts;
import com.tencent.bugly.Bugly;
import java.beans.FeatureDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Properties;
import org.apache.log4j.Appender;
import org.apache.log4j.Level;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.OptionHandler;

public class PropertySetter {
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class class$org$apache$log4j$Priority;
    protected Object obj;
    protected PropertyDescriptor[] props;

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public PropertySetter(Object obj2) {
        this.obj = obj2;
    }

    /* access modifiers changed from: protected */
    public void introspect() {
        try {
            this.props = Introspector.getBeanInfo(this.obj.getClass()).getPropertyDescriptors();
        } catch (IntrospectionException e) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Failed to introspect ");
            stringBuffer.append(this.obj);
            stringBuffer.append(": ");
            stringBuffer.append(e.getMessage());
            LogLog.error(stringBuffer.toString());
            this.props = new PropertyDescriptor[0];
        }
    }

    public static void setProperties(Object obj2, Properties properties, String str) {
        new PropertySetter(obj2).setProperties(properties, str);
    }

    public void setProperties(Properties properties, String str) {
        int length = str.length();
        Enumeration<?> propertyNames = properties.propertyNames();
        while (propertyNames.hasMoreElements()) {
            String str2 = (String) propertyNames.nextElement();
            if (str2.startsWith(str) && str2.indexOf(46, length + 1) <= 0) {
                String findAndSubst = OptionConverter.findAndSubst(str2, properties);
                String substring = str2.substring(length);
                if (!"layout".equals(substring) || !(this.obj instanceof Appender)) {
                    setProperty(substring, findAndSubst);
                }
            }
        }
        activate();
    }

    public void setProperty(String str, String str2) {
        if (str2 != null) {
            String decapitalize = Introspector.decapitalize(str);
            PropertyDescriptor propertyDescriptor = getPropertyDescriptor(decapitalize);
            if (propertyDescriptor == null) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("No such property [");
                stringBuffer.append(decapitalize);
                stringBuffer.append("] in ");
                stringBuffer.append(this.obj.getClass().getName());
                stringBuffer.append(Consts.DOT);
                LogLog.warn(stringBuffer.toString());
                return;
            }
            try {
                setProperty(propertyDescriptor, decapitalize, str2);
            } catch (PropertySetterException e) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Failed to set property [");
                stringBuffer2.append(decapitalize);
                stringBuffer2.append("] to value \"");
                stringBuffer2.append(str2);
                stringBuffer2.append("\". ");
                LogLog.warn(stringBuffer2.toString(), e.rootCause);
            }
        }
    }

    public void setProperty(PropertyDescriptor propertyDescriptor, String str, String str2) throws PropertySetterException {
        Method writeMethod = propertyDescriptor.getWriteMethod();
        if (writeMethod != null) {
            Class[] parameterTypes = writeMethod.getParameterTypes();
            if (parameterTypes.length == 1) {
                try {
                    Object convertArg = convertArg(str2, parameterTypes[0]);
                    if (convertArg != null) {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("Setting property [");
                        stringBuffer.append(str);
                        stringBuffer.append("] to [");
                        stringBuffer.append(convertArg);
                        stringBuffer.append("].");
                        LogLog.debug(stringBuffer.toString());
                        try {
                            writeMethod.invoke(this.obj, new Object[]{convertArg});
                        } catch (Exception e) {
                            throw new PropertySetterException((Throwable) e);
                        }
                    } else {
                        StringBuffer stringBuffer2 = new StringBuffer();
                        stringBuffer2.append("Conversion to type [");
                        stringBuffer2.append(parameterTypes[0]);
                        stringBuffer2.append("] failed.");
                        throw new PropertySetterException(stringBuffer2.toString());
                    }
                } catch (Throwable th) {
                    StringBuffer stringBuffer3 = new StringBuffer();
                    stringBuffer3.append("Conversion to type [");
                    stringBuffer3.append(parameterTypes[0]);
                    stringBuffer3.append("] failed. Reason: ");
                    stringBuffer3.append(th);
                    throw new PropertySetterException(stringBuffer3.toString());
                }
            } else {
                throw new PropertySetterException("#params for setter != 1");
            }
        } else {
            StringBuffer stringBuffer4 = new StringBuffer();
            stringBuffer4.append("No setter for property [");
            stringBuffer4.append(str);
            stringBuffer4.append("].");
            throw new PropertySetterException(stringBuffer4.toString());
        }
    }

    /* access modifiers changed from: protected */
    public Object convertArg(String str, Class cls) {
        if (str == null) {
            return null;
        }
        String trim = str.trim();
        Class cls2 = class$java$lang$String;
        if (cls2 == null) {
            cls2 = class$(com.alibaba.android.arouter.compiler.utils.Consts.STRING);
            class$java$lang$String = cls2;
        }
        if (cls2.isAssignableFrom(cls)) {
            return str;
        }
        if (Integer.TYPE.isAssignableFrom(cls)) {
            return new Integer(trim);
        }
        if (Long.TYPE.isAssignableFrom(cls)) {
            return new Long(trim);
        }
        if (!Boolean.TYPE.isAssignableFrom(cls)) {
            Class cls3 = class$org$apache$log4j$Priority;
            if (cls3 == null) {
                cls3 = class$("org.apache.log4j.Priority");
                class$org$apache$log4j$Priority = cls3;
            }
            if (cls3.isAssignableFrom(cls)) {
                return OptionConverter.toLevel(trim, Level.DEBUG);
            }
        } else if ("true".equalsIgnoreCase(trim)) {
            return Boolean.TRUE;
        } else {
            if (Bugly.SDK_IS_DEV.equalsIgnoreCase(trim)) {
                return Boolean.FALSE;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public PropertyDescriptor getPropertyDescriptor(String str) {
        if (this.props == null) {
            introspect();
        }
        int i = 0;
        while (true) {
            FeatureDescriptor[] featureDescriptorArr = this.props;
            if (i >= featureDescriptorArr.length) {
                return null;
            }
            if (str.equals(featureDescriptorArr[i].getName())) {
                return this.props[i];
            }
            i++;
        }
    }

    public void activate() {
        Object obj2 = this.obj;
        if (obj2 instanceof OptionHandler) {
            ((OptionHandler) obj2).activateOptions();
        }
    }
}
