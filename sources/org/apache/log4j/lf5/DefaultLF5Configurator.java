package org.apache.log4j.lf5;

import java.io.IOException;
import java.net.URL;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.Configurator;
import org.apache.log4j.spi.LoggerRepository;

public class DefaultLF5Configurator implements Configurator {
    static /* synthetic */ Class class$org$apache$log4j$lf5$DefaultLF5Configurator;

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    private DefaultLF5Configurator() {
    }

    public static void configure() throws IOException {
        Class cls = class$org$apache$log4j$lf5$DefaultLF5Configurator;
        if (cls == null) {
            cls = class$("org.apache.log4j.lf5.DefaultLF5Configurator");
            class$org$apache$log4j$lf5$DefaultLF5Configurator = cls;
        }
        URL resource = cls.getResource("/org/apache/log4j/lf5/config/defaultconfig.properties");
        if (resource != null) {
            PropertyConfigurator.configure(resource);
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Error: Unable to open the resource");
        stringBuffer.append("/org/apache/log4j/lf5/config/defaultconfig.properties");
        throw new IOException(stringBuffer.toString());
    }

    public void doConfigure(URL url, LoggerRepository loggerRepository) {
        throw new IllegalStateException("This class should NOT be instantiated!");
    }
}
