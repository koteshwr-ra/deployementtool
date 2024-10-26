package org.apache.log4j.config;

import com.alibaba.android.arouter.compiler.utils.Consts;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import org.apache.log4j.helpers.LogLog;

public class PropertyGetter {
    protected static final Object[] NULL_ARG = new Object[0];
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class class$org$apache$log4j$Priority;
    protected Object obj;
    protected PropertyDescriptor[] props;

    public interface PropertyCallback {
        void foundProperty(Object obj, String str, String str2, Object obj2);
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public PropertyGetter(Object obj2) throws IntrospectionException {
        this.props = Introspector.getBeanInfo(obj2.getClass()).getPropertyDescriptors();
        this.obj = obj2;
    }

    public static void getProperties(Object obj2, PropertyCallback propertyCallback, String str) {
        try {
            new PropertyGetter(obj2).getProperties(propertyCallback, str);
        } catch (IntrospectionException e) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Failed to introspect object ");
            stringBuffer.append(obj2);
            LogLog.error(stringBuffer.toString(), e);
        }
    }

    public void getProperties(PropertyCallback propertyCallback, String str) {
        int i = 0;
        while (true) {
            PropertyDescriptor[] propertyDescriptorArr = this.props;
            if (i < propertyDescriptorArr.length) {
                Method readMethod = propertyDescriptorArr[i].getReadMethod();
                if (readMethod != null && isHandledType(readMethod.getReturnType())) {
                    String name = this.props[i].getName();
                    try {
                        Object invoke = readMethod.invoke(this.obj, NULL_ARG);
                        if (invoke != null) {
                            propertyCallback.foundProperty(this.obj, str, name, invoke);
                        }
                    } catch (Exception unused) {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("Failed to get value of property ");
                        stringBuffer.append(name);
                        LogLog.warn(stringBuffer.toString());
                    }
                }
                i++;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean isHandledType(Class cls) {
        Class cls2 = class$java$lang$String;
        if (cls2 == null) {
            cls2 = class$(Consts.STRING);
            class$java$lang$String = cls2;
        }
        if (!cls2.isAssignableFrom(cls) && !Integer.TYPE.isAssignableFrom(cls) && !Long.TYPE.isAssignableFrom(cls) && !Boolean.TYPE.isAssignableFrom(cls)) {
            Class cls3 = class$org$apache$log4j$Priority;
            if (cls3 == null) {
                cls3 = class$("org.apache.log4j.Priority");
                class$org$apache$log4j$Priority = cls3;
            }
            return cls3.isAssignableFrom(cls);
        }
    }
}
