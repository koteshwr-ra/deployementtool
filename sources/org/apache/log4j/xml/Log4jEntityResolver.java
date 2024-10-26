package org.apache.log4j.xml;

import java.io.InputStream;
import org.apache.log4j.helpers.LogLog;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

public class Log4jEntityResolver implements EntityResolver {
    public InputSource resolveEntity(String str, String str2) {
        if (!str2.endsWith("log4j.dtd")) {
            return null;
        }
        Class<?> cls = getClass();
        InputStream resourceAsStream = cls.getResourceAsStream("/org/apache/log4j/xml/log4j.dtd");
        if (resourceAsStream != null) {
            return new InputSource(resourceAsStream);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Could not find [log4j.dtd]. Used [");
        stringBuffer.append(cls.getClassLoader());
        stringBuffer.append("] class loader in the search.");
        LogLog.error(stringBuffer.toString());
        return null;
    }
}
