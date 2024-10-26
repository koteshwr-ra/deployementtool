package org.apache.log4j.helpers;

import com.alibaba.android.arouter.utils.Consts;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

public class Loader {
    static final String TSTR = "Caught Exception while in Loader.getResource. This may be innocuous.";
    static /* synthetic */ Class class$java$lang$Thread = null;
    static /* synthetic */ Class class$org$apache$log4j$helpers$Loader = null;
    private static boolean ignoreTCL = false;
    private static boolean java1 = false;

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        int indexOf;
        String systemProperty = OptionConverter.getSystemProperty("java.version", (String) null);
        if (!(systemProperty == null || (indexOf = systemProperty.indexOf(46)) == -1 || systemProperty.charAt(indexOf + 1) == '1')) {
        }
        String systemProperty2 = OptionConverter.getSystemProperty("log4j.ignoreTCL", (String) null);
        if (systemProperty2 != null) {
            ignoreTCL = OptionConverter.toBoolean(systemProperty2, true);
        }
    }

    public static URL getResource(String str, Class cls) {
        return getResource(str);
    }

    public static URL getResource(String str) {
        Class cls;
        ClassLoader tcl;
        try {
            if (!java1 && (tcl = getTCL()) != null) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Trying to find [");
                stringBuffer.append(str);
                stringBuffer.append("] using context classloader ");
                stringBuffer.append(tcl);
                stringBuffer.append(Consts.DOT);
                LogLog.debug(stringBuffer.toString());
                URL resource = tcl.getResource(str);
                if (resource != null) {
                    return resource;
                }
            }
            if (class$org$apache$log4j$helpers$Loader == null) {
                cls = class$("org.apache.log4j.helpers.Loader");
                class$org$apache$log4j$helpers$Loader = cls;
            } else {
                cls = class$org$apache$log4j$helpers$Loader;
            }
            ClassLoader classLoader = cls.getClassLoader();
            if (classLoader != null) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Trying to find [");
                stringBuffer2.append(str);
                stringBuffer2.append("] using ");
                stringBuffer2.append(classLoader);
                stringBuffer2.append(" class loader.");
                LogLog.debug(stringBuffer2.toString());
                URL resource2 = classLoader.getResource(str);
                if (resource2 != null) {
                    return resource2;
                }
            }
        } catch (Throwable th) {
            LogLog.warn(TSTR, th);
        }
        StringBuffer stringBuffer3 = new StringBuffer();
        stringBuffer3.append("Trying to find [");
        stringBuffer3.append(str);
        stringBuffer3.append("] using ClassLoader.getSystemResource().");
        LogLog.debug(stringBuffer3.toString());
        return ClassLoader.getSystemResource(str);
    }

    public static boolean isJava1() {
        return java1;
    }

    private static ClassLoader getTCL() throws IllegalAccessException, InvocationTargetException {
        Class cls;
        try {
            if (class$java$lang$Thread == null) {
                cls = class$("java.lang.Thread");
                class$java$lang$Thread = cls;
            } else {
                cls = class$java$lang$Thread;
            }
            return (ClassLoader) cls.getMethod("getContextClassLoader", (Class[]) null).invoke(Thread.currentThread(), (Object[]) null);
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    public static Class loadClass(String str) throws ClassNotFoundException {
        if (java1 || ignoreTCL) {
            return Class.forName(str);
        }
        try {
            return getTCL().loadClass(str);
        } catch (Throwable unused) {
            return Class.forName(str);
        }
    }
}
