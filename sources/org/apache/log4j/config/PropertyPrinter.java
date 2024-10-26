package org.apache.log4j.config;

import androidx.exifinterface.media.ExifInterface;
import com.alibaba.android.arouter.utils.Consts;
import com.limpoxe.support.servicemanager.ServiceProvider;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import org.apache.log4j.Appender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.config.PropertyGetter;

public class PropertyPrinter implements PropertyGetter.PropertyCallback {
    protected Hashtable appenderNames;
    protected boolean doCapitalize;
    protected Hashtable layoutNames;
    protected int numAppenders;
    protected PrintWriter out;

    public PropertyPrinter(PrintWriter printWriter) {
        this(printWriter, false);
    }

    public PropertyPrinter(PrintWriter printWriter, boolean z) {
        this.numAppenders = 0;
        this.appenderNames = new Hashtable();
        this.layoutNames = new Hashtable();
        this.out = printWriter;
        this.doCapitalize = z;
        print(printWriter);
        printWriter.flush();
    }

    /* access modifiers changed from: protected */
    public String genAppName() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(ExifInterface.GPS_MEASUREMENT_IN_PROGRESS);
        int i = this.numAppenders;
        this.numAppenders = i + 1;
        stringBuffer.append(i);
        return stringBuffer.toString();
    }

    /* access modifiers changed from: protected */
    public boolean isGenAppName(String str) {
        if (str.length() >= 2 && str.charAt(0) == 'A') {
            int i = 0;
            while (i < str.length()) {
                if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                    i++;
                }
            }
            return true;
        }
        return false;
    }

    public void print(PrintWriter printWriter) {
        printOptions(printWriter, Logger.getRootLogger());
        Enumeration currentLoggers = LogManager.getCurrentLoggers();
        while (currentLoggers.hasMoreElements()) {
            printOptions(printWriter, (Logger) currentLoggers.nextElement());
        }
    }

    /* access modifiers changed from: protected */
    public void printOptions(PrintWriter printWriter, Logger logger) {
        String str;
        String str2;
        Enumeration allAppenders = logger.getAllAppenders();
        Level level = logger.getLevel();
        if (level == null) {
            str = "";
        } else {
            str = level.toString();
        }
        while (allAppenders.hasMoreElements()) {
            Appender appender = (Appender) allAppenders.nextElement();
            String str3 = (String) this.appenderNames.get(appender);
            if (str3 == null) {
                str3 = appender.getName();
                if (str3 == null || isGenAppName(str3)) {
                    str3 = genAppName();
                }
                this.appenderNames.put(appender, str3);
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("log4j.appender.");
                stringBuffer.append(str3);
                printOptions(printWriter, appender, stringBuffer.toString());
                if (appender.getLayout() != null) {
                    Layout layout = appender.getLayout();
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("log4j.appender.");
                    stringBuffer2.append(str3);
                    stringBuffer2.append(".layout");
                    printOptions(printWriter, layout, stringBuffer2.toString());
                }
            }
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append(str);
            stringBuffer3.append(", ");
            stringBuffer3.append(str3);
            str = stringBuffer3.toString();
        }
        if (logger == Logger.getRootLogger()) {
            str2 = "log4j.rootLogger";
        } else {
            StringBuffer stringBuffer4 = new StringBuffer();
            stringBuffer4.append("log4j.logger.");
            stringBuffer4.append(logger.getName());
            str2 = stringBuffer4.toString();
        }
        if (str != "") {
            StringBuffer stringBuffer5 = new StringBuffer();
            stringBuffer5.append(str2);
            stringBuffer5.append("=");
            stringBuffer5.append(str);
            printWriter.println(stringBuffer5.toString());
        }
        if (!logger.getAdditivity() && logger != Logger.getRootLogger()) {
            StringBuffer stringBuffer6 = new StringBuffer();
            stringBuffer6.append("log4j.additivity.");
            stringBuffer6.append(logger.getName());
            stringBuffer6.append("=false");
            printWriter.println(stringBuffer6.toString());
        }
    }

    /* access modifiers changed from: protected */
    public void printOptions(PrintWriter printWriter, Object obj, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        stringBuffer.append("=");
        stringBuffer.append(obj.getClass().getName());
        printWriter.println(stringBuffer.toString());
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append(str);
        stringBuffer2.append(Consts.DOT);
        PropertyGetter.getProperties(obj, this, stringBuffer2.toString());
    }

    public void foundProperty(Object obj, String str, String str2, Object obj2) {
        if (!(obj instanceof Appender) || !ServiceProvider.NAME.equals(str2)) {
            if (this.doCapitalize) {
                str2 = capitalize(str2);
            }
            PrintWriter printWriter = this.out;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str);
            stringBuffer.append(str2);
            stringBuffer.append("=");
            stringBuffer.append(obj2.toString());
            printWriter.println(stringBuffer.toString());
        }
    }

    public static String capitalize(String str) {
        if (!Character.isLowerCase(str.charAt(0))) {
            return str;
        }
        if (str.length() != 1 && !Character.isLowerCase(str.charAt(1))) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(str);
        stringBuffer.setCharAt(0, Character.toUpperCase(str.charAt(0)));
        return stringBuffer.toString();
    }

    public static void main(String[] strArr) {
        new PropertyPrinter(new PrintWriter(System.out));
    }
}
