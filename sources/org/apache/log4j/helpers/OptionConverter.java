package org.apache.log4j.helpers;

import android.support.v4.media.session.PlaybackStateCompat;
import com.alibaba.android.arouter.compiler.utils.Consts;
import com.tencent.bugly.Bugly;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Properties;
import kotlin.text.Typography;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.log4j.Level;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.Configurator;
import org.apache.log4j.spi.LoggerRepository;

public class OptionConverter {
    static String DELIM_START = "${";
    static int DELIM_START_LEN = 2;
    static char DELIM_STOP = '}';
    static int DELIM_STOP_LEN = 1;
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class class$org$apache$log4j$Level;
    static /* synthetic */ Class class$org$apache$log4j$spi$Configurator;

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    private OptionConverter() {
    }

    public static String[] concatanateArrays(String[] strArr, String[] strArr2) {
        String[] strArr3 = new String[(strArr.length + strArr2.length)];
        System.arraycopy(strArr, 0, strArr3, 0, strArr.length);
        System.arraycopy(strArr2, 0, strArr3, strArr.length, strArr2.length);
        return strArr3;
    }

    public static String convertSpecialChars(String str) {
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer(length);
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            char charAt = str.charAt(i);
            if (charAt == '\\') {
                int i3 = i2 + 1;
                char charAt2 = str.charAt(i2);
                if (charAt2 == 'n') {
                    i2 = i3;
                    charAt = 10;
                } else if (charAt2 == 'r') {
                    i2 = i3;
                    charAt = CharUtils.CR;
                } else if (charAt2 == 't') {
                    i2 = i3;
                    charAt = 9;
                } else if (charAt2 == 'f') {
                    i2 = i3;
                    charAt = 12;
                } else if (charAt2 == 8) {
                    i2 = i3;
                    charAt = 8;
                } else if (charAt2 == '\"') {
                    i2 = i3;
                    charAt = Typography.quote;
                } else if (charAt2 == '\'') {
                    i2 = i3;
                    charAt = '\'';
                } else if (charAt2 == '\\') {
                    i2 = i3;
                    charAt = '\\';
                } else {
                    char c = charAt2;
                    i2 = i3;
                    charAt = c;
                }
            }
            stringBuffer.append(charAt);
            i = i2;
        }
        return stringBuffer.toString();
    }

    public static String getSystemProperty(String str, String str2) {
        try {
            return System.getProperty(str, str2);
        } catch (Throwable unused) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Was not allowed to read system property \"");
            stringBuffer.append(str);
            stringBuffer.append("\".");
            LogLog.debug(stringBuffer.toString());
            return str2;
        }
    }

    public static Object instantiateByKey(Properties properties, String str, Class cls, Object obj) {
        String findAndSubst = findAndSubst(str, properties);
        if (findAndSubst != null) {
            return instantiateByClassName(findAndSubst.trim(), cls, obj);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Could not find value for key ");
        stringBuffer.append(str);
        LogLog.error(stringBuffer.toString());
        return obj;
    }

    public static boolean toBoolean(String str, boolean z) {
        if (str == null) {
            return z;
        }
        String trim = str.trim();
        if ("true".equalsIgnoreCase(trim)) {
            return true;
        }
        if (Bugly.SDK_IS_DEV.equalsIgnoreCase(trim)) {
            return false;
        }
        return z;
    }

    public static int toInt(String str, int i) {
        if (str != null) {
            String trim = str.trim();
            try {
                return Integer.valueOf(trim).intValue();
            } catch (NumberFormatException e) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("[");
                stringBuffer.append(trim);
                stringBuffer.append("] is not in proper int form.");
                LogLog.error(stringBuffer.toString());
                e.printStackTrace();
            }
        }
        return i;
    }

    public static Level toLevel(String str, Level level) {
        Class cls;
        Class cls2;
        if (str == null) {
            return level;
        }
        String trim = str.trim();
        int indexOf = trim.indexOf(35);
        if (indexOf != -1) {
            String substring = trim.substring(indexOf + 1);
            String substring2 = trim.substring(0, indexOf);
            if (DateLayout.NULL_DATE_FORMAT.equalsIgnoreCase(substring2)) {
                return null;
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("toLevel:class=[");
            stringBuffer.append(substring);
            stringBuffer.append("]");
            stringBuffer.append(":pri=[");
            stringBuffer.append(substring2);
            stringBuffer.append("]");
            LogLog.debug(stringBuffer.toString());
            try {
                Class loadClass = Loader.loadClass(substring);
                Class[] clsArr = new Class[2];
                if (class$java$lang$String == null) {
                    cls = class$(Consts.STRING);
                    class$java$lang$String = cls;
                } else {
                    cls = class$java$lang$String;
                }
                clsArr[0] = cls;
                if (class$org$apache$log4j$Level == null) {
                    cls2 = class$("org.apache.log4j.Level");
                    class$org$apache$log4j$Level = cls2;
                } else {
                    cls2 = class$org$apache$log4j$Level;
                }
                clsArr[1] = cls2;
                return (Level) loadClass.getMethod("toLevel", clsArr).invoke((Object) null, new Object[]{substring2, level});
            } catch (ClassNotFoundException unused) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("custom level class [");
                stringBuffer2.append(substring);
                stringBuffer2.append("] not found.");
                LogLog.warn(stringBuffer2.toString());
                return level;
            } catch (NoSuchMethodException e) {
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("custom level class [");
                stringBuffer3.append(substring);
                stringBuffer3.append("]");
                stringBuffer3.append(" does not have a constructor which takes one string parameter");
                LogLog.warn(stringBuffer3.toString(), e);
                return level;
            } catch (InvocationTargetException e2) {
                StringBuffer stringBuffer4 = new StringBuffer();
                stringBuffer4.append("custom level class [");
                stringBuffer4.append(substring);
                stringBuffer4.append("]");
                stringBuffer4.append(" could not be instantiated");
                LogLog.warn(stringBuffer4.toString(), e2);
                return level;
            } catch (ClassCastException e3) {
                StringBuffer stringBuffer5 = new StringBuffer();
                stringBuffer5.append("class [");
                stringBuffer5.append(substring);
                stringBuffer5.append("] is not a subclass of org.apache.log4j.Level");
                LogLog.warn(stringBuffer5.toString(), e3);
                return level;
            } catch (IllegalAccessException e4) {
                StringBuffer stringBuffer6 = new StringBuffer();
                stringBuffer6.append("class [");
                stringBuffer6.append(substring);
                stringBuffer6.append("] cannot be instantiated due to access restrictions");
                LogLog.warn(stringBuffer6.toString(), e4);
                return level;
            } catch (Exception e5) {
                StringBuffer stringBuffer7 = new StringBuffer();
                stringBuffer7.append("class [");
                stringBuffer7.append(substring);
                stringBuffer7.append("], level [");
                stringBuffer7.append(substring2);
                stringBuffer7.append("] conversion failed.");
                LogLog.warn(stringBuffer7.toString(), e5);
                return level;
            }
        } else if (DateLayout.NULL_DATE_FORMAT.equalsIgnoreCase(trim)) {
            return null;
        } else {
            return Level.toLevel(trim, level);
        }
    }

    public static long toFileSize(String str, long j) {
        if (str == null) {
            return j;
        }
        String upperCase = str.trim().toUpperCase();
        long j2 = 1;
        int indexOf = upperCase.indexOf("KB");
        if (indexOf != -1) {
            j2 = PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
            upperCase = upperCase.substring(0, indexOf);
        } else {
            int indexOf2 = upperCase.indexOf("MB");
            if (indexOf2 != -1) {
                j2 = PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
                upperCase = upperCase.substring(0, indexOf2);
            } else {
                int indexOf3 = upperCase.indexOf("GB");
                if (indexOf3 != -1) {
                    j2 = 1073741824;
                    upperCase = upperCase.substring(0, indexOf3);
                }
            }
        }
        if (upperCase != null) {
            try {
                return Long.valueOf(upperCase).longValue() * j2;
            } catch (NumberFormatException e) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("[");
                stringBuffer.append(upperCase);
                stringBuffer.append("] is not in proper int form.");
                LogLog.error(stringBuffer.toString());
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("[");
                stringBuffer2.append(str);
                stringBuffer2.append("] not in expected format.");
                LogLog.error(stringBuffer2.toString(), e);
            }
        }
        return j;
    }

    public static String findAndSubst(String str, Properties properties) {
        String property = properties.getProperty(str);
        if (property == null) {
            return null;
        }
        try {
            return substVars(property, properties);
        } catch (IllegalArgumentException e) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Bad option value [");
            stringBuffer.append(property);
            stringBuffer.append("].");
            LogLog.error(stringBuffer.toString(), e);
            return property;
        }
    }

    public static Object instantiateByClassName(String str, Class cls, Object obj) {
        if (str != null) {
            try {
                Class loadClass = Loader.loadClass(str);
                if (cls.isAssignableFrom(loadClass)) {
                    return loadClass.newInstance();
                }
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("A \"");
                stringBuffer.append(str);
                stringBuffer.append("\" object is not assignable to a \"");
                stringBuffer.append(cls.getName());
                stringBuffer.append("\" variable.");
                LogLog.error(stringBuffer.toString());
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("The class \"");
                stringBuffer2.append(cls.getName());
                stringBuffer2.append("\" was loaded by ");
                LogLog.error(stringBuffer2.toString());
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("[");
                stringBuffer3.append(cls.getClassLoader());
                stringBuffer3.append("] whereas object of type ");
                LogLog.error(stringBuffer3.toString());
                StringBuffer stringBuffer4 = new StringBuffer();
                stringBuffer4.append("\"");
                stringBuffer4.append(loadClass.getName());
                stringBuffer4.append("\" was loaded by [");
                stringBuffer4.append(loadClass.getClassLoader());
                stringBuffer4.append("].");
                LogLog.error(stringBuffer4.toString());
                return obj;
            } catch (Exception e) {
                StringBuffer stringBuffer5 = new StringBuffer();
                stringBuffer5.append("Could not instantiate class [");
                stringBuffer5.append(str);
                stringBuffer5.append("].");
                LogLog.error(stringBuffer5.toString(), e);
            }
        }
        return obj;
    }

    public static String substVars(String str, Properties properties) throws IllegalArgumentException {
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        while (true) {
            int indexOf = str.indexOf(DELIM_START, i);
            if (indexOf != -1) {
                stringBuffer.append(str.substring(i, indexOf));
                int indexOf2 = str.indexOf(DELIM_STOP, indexOf);
                if (indexOf2 != -1) {
                    String substring = str.substring(indexOf + DELIM_START_LEN, indexOf2);
                    String systemProperty = getSystemProperty(substring, (String) null);
                    if (systemProperty == null && properties != null) {
                        systemProperty = properties.getProperty(substring);
                    }
                    if (systemProperty != null) {
                        stringBuffer.append(substVars(systemProperty, properties));
                    }
                    i = indexOf2 + DELIM_STOP_LEN;
                } else {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append(Typography.quote);
                    stringBuffer2.append(str);
                    stringBuffer2.append("\" has no closing brace. Opening brace at position ");
                    stringBuffer2.append(indexOf);
                    stringBuffer2.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
                    throw new IllegalArgumentException(stringBuffer2.toString());
                }
            } else if (i == 0) {
                return str;
            } else {
                stringBuffer.append(str.substring(i, str.length()));
                return stringBuffer.toString();
            }
        }
    }

    public static void selectAndConfigure(URL url, String str, LoggerRepository loggerRepository) {
        Configurator configurator;
        String file = url.getFile();
        if (str == null && file != null && file.endsWith(".xml")) {
            str = "org.apache.log4j.xml.DOMConfigurator";
        }
        if (str != null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Preferred configurator class: ");
            stringBuffer.append(str);
            LogLog.debug(stringBuffer.toString());
            Class cls = class$org$apache$log4j$spi$Configurator;
            if (cls == null) {
                cls = class$("org.apache.log4j.spi.Configurator");
                class$org$apache$log4j$spi$Configurator = cls;
            }
            configurator = (Configurator) instantiateByClassName(str, cls, (Object) null);
            if (configurator == null) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Could not instantiate configurator [");
                stringBuffer2.append(str);
                stringBuffer2.append("].");
                LogLog.error(stringBuffer2.toString());
                return;
            }
        } else {
            configurator = new PropertyConfigurator();
        }
        configurator.doConfigure(url, loggerRepository);
    }
}
