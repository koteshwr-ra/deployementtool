package org.apache.log4j;

import com.tencent.bugly.Bugly;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import org.apache.log4j.helpers.Loader;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.DefaultRepositorySelector;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.RepositorySelector;
import org.apache.log4j.spi.RootLogger;

public class LogManager {
    public static final String CONFIGURATOR_CLASS_KEY = "log4j.configuratorClass";
    public static final String DEFAULT_CONFIGURATION_FILE = "log4j.properties";
    public static final String DEFAULT_CONFIGURATION_KEY = "log4j.configuration";
    public static final String DEFAULT_INIT_OVERRIDE_KEY = "log4j.defaultInitOverride";
    static final String DEFAULT_XML_CONFIGURATION_FILE = "log4j.xml";
    private static Object guard;
    private static RepositorySelector repositorySelector = new DefaultRepositorySelector(new Hierarchy(new RootLogger(Level.DEBUG)));

    static {
        URL url;
        String systemProperty = OptionConverter.getSystemProperty(DEFAULT_INIT_OVERRIDE_KEY, (String) null);
        if (systemProperty == null || Bugly.SDK_IS_DEV.equalsIgnoreCase(systemProperty)) {
            String systemProperty2 = OptionConverter.getSystemProperty(DEFAULT_CONFIGURATION_KEY, (String) null);
            String systemProperty3 = OptionConverter.getSystemProperty(CONFIGURATOR_CLASS_KEY, (String) null);
            if (systemProperty2 == null) {
                url = Loader.getResource(DEFAULT_XML_CONFIGURATION_FILE);
                if (url == null) {
                    url = Loader.getResource(DEFAULT_CONFIGURATION_FILE);
                }
            } else {
                try {
                    url = new URL(systemProperty2);
                } catch (MalformedURLException unused) {
                    url = Loader.getResource(systemProperty2);
                }
            }
            if (url != null) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Using URL [");
                stringBuffer.append(url);
                stringBuffer.append("] for automatic log4j configuration.");
                LogLog.debug(stringBuffer.toString());
                OptionConverter.selectAndConfigure(url, systemProperty3, getLoggerRepository());
                return;
            }
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Could not find resource: [");
            stringBuffer2.append(systemProperty2);
            stringBuffer2.append("].");
            LogLog.debug(stringBuffer2.toString());
        }
    }

    public static void setRepositorySelector(RepositorySelector repositorySelector2, Object obj) throws IllegalArgumentException {
        Object obj2 = guard;
        if (obj2 != null && obj2 != obj) {
            throw new IllegalArgumentException("Attempted to reset the LoggerFactory without possessing the guard.");
        } else if (repositorySelector2 != null) {
            guard = obj;
            repositorySelector = repositorySelector2;
        } else {
            throw new IllegalArgumentException("RepositorySelector must be non-null.");
        }
    }

    public static LoggerRepository getLoggerRepository() {
        return repositorySelector.getLoggerRepository();
    }

    public static Logger getRootLogger() {
        return repositorySelector.getLoggerRepository().getRootLogger();
    }

    public static Logger getLogger(String str) {
        return repositorySelector.getLoggerRepository().getLogger(str);
    }

    public static Logger getLogger(Class cls) {
        return repositorySelector.getLoggerRepository().getLogger(cls.getName());
    }

    public static Logger getLogger(String str, LoggerFactory loggerFactory) {
        return repositorySelector.getLoggerRepository().getLogger(str, loggerFactory);
    }

    public static Logger exists(String str) {
        return repositorySelector.getLoggerRepository().exists(str);
    }

    public static Enumeration getCurrentLoggers() {
        return repositorySelector.getLoggerRepository().getCurrentLoggers();
    }

    public static void shutdown() {
        repositorySelector.getLoggerRepository().shutdown();
    }

    public static void resetConfiguration() {
        repositorySelector.getLoggerRepository().resetConfiguration();
    }
}
