package org.apache.log4j;

import com.alibaba.android.arouter.utils.Consts;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;
import org.apache.log4j.config.PropertySetter;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.or.RendererMap;
import org.apache.log4j.spi.Configurator;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.OptionHandler;
import org.apache.log4j.spi.RendererSupport;

public class PropertyConfigurator implements Configurator {
    static final String ADDITIVITY_PREFIX = "log4j.additivity.";
    static final String APPENDER_PREFIX = "log4j.appender.";
    static final String CATEGORY_PREFIX = "log4j.category.";
    static final String FACTORY_PREFIX = "log4j.factory";
    private static final String INTERNAL_ROOT_NAME = "root";
    public static final String LOGGER_FACTORY_KEY = "log4j.loggerFactory";
    static final String LOGGER_PREFIX = "log4j.logger.";
    static final String RENDERER_PREFIX = "log4j.renderer.";
    static final String ROOT_CATEGORY_PREFIX = "log4j.rootCategory";
    static final String ROOT_LOGGER_PREFIX = "log4j.rootLogger";
    static final String THRESHOLD_PREFIX = "log4j.threshold";
    static /* synthetic */ Class class$org$apache$log4j$Appender;
    static /* synthetic */ Class class$org$apache$log4j$Layout;
    static /* synthetic */ Class class$org$apache$log4j$spi$LoggerFactory;
    protected LoggerFactory loggerFactory = new DefaultCategoryFactory();
    protected Hashtable registry = new Hashtable(11);

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public void doConfigure(String str, LoggerRepository loggerRepository) {
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            properties.load(fileInputStream);
            fileInputStream.close();
            doConfigure(properties, loggerRepository);
        } catch (IOException e) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Could not read configuration file [");
            stringBuffer.append(str);
            stringBuffer.append("].");
            LogLog.error(stringBuffer.toString(), e);
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Ignoring configuration file [");
            stringBuffer2.append(str);
            stringBuffer2.append("].");
            LogLog.error(stringBuffer2.toString());
        }
    }

    public static void configure(String str) {
        new PropertyConfigurator().doConfigure(str, LogManager.getLoggerRepository());
    }

    public static void configure(URL url) {
        new PropertyConfigurator().doConfigure(url, LogManager.getLoggerRepository());
    }

    public static void configure(Properties properties) {
        new PropertyConfigurator().doConfigure(properties, LogManager.getLoggerRepository());
    }

    public static void configureAndWatch(String str) {
        configureAndWatch(str, 60000);
    }

    public static void configureAndWatch(String str, long j) {
        PropertyWatchdog propertyWatchdog = new PropertyWatchdog(str);
        propertyWatchdog.setDelay(j);
        propertyWatchdog.start();
    }

    public void doConfigure(Properties properties, LoggerRepository loggerRepository) {
        String property = properties.getProperty(LogLog.DEBUG_KEY);
        if (property == null && (property = properties.getProperty(LogLog.CONFIG_DEBUG_KEY)) != null) {
            LogLog.warn("[log4j.configDebug] is deprecated. Use [log4j.debug] instead.");
        }
        if (property != null) {
            LogLog.setInternalDebugging(OptionConverter.toBoolean(property, true));
        }
        String findAndSubst = OptionConverter.findAndSubst(THRESHOLD_PREFIX, properties);
        if (findAndSubst != null) {
            loggerRepository.setThreshold(OptionConverter.toLevel(findAndSubst, Level.ALL));
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Hierarchy threshold set to [");
            stringBuffer.append(loggerRepository.getThreshold());
            stringBuffer.append("].");
            LogLog.debug(stringBuffer.toString());
        }
        configureRootCategory(properties, loggerRepository);
        configureLoggerFactory(properties);
        parseCatsAndRenderers(properties, loggerRepository);
        LogLog.debug("Finished configuring.");
        this.registry.clear();
    }

    public void doConfigure(URL url, LoggerRepository loggerRepository) {
        Properties properties = new Properties();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Reading configuration from URL ");
        stringBuffer.append(url);
        LogLog.debug(stringBuffer.toString());
        try {
            properties.load(url.openStream());
            doConfigure(properties, loggerRepository);
        } catch (IOException e) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Could not read configuration file from URL [");
            stringBuffer2.append(url);
            stringBuffer2.append("].");
            LogLog.error(stringBuffer2.toString(), e);
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("Ignoring configuration file [");
            stringBuffer3.append(url);
            stringBuffer3.append("].");
            LogLog.error(stringBuffer3.toString());
        }
    }

    /* access modifiers changed from: protected */
    public void configureLoggerFactory(Properties properties) {
        String findAndSubst = OptionConverter.findAndSubst(LOGGER_FACTORY_KEY, properties);
        if (findAndSubst != null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Setting category factory to [");
            stringBuffer.append(findAndSubst);
            stringBuffer.append("].");
            LogLog.debug(stringBuffer.toString());
            Class cls = class$org$apache$log4j$spi$LoggerFactory;
            if (cls == null) {
                cls = class$("org.apache.log4j.spi.LoggerFactory");
                class$org$apache$log4j$spi$LoggerFactory = cls;
            }
            LoggerFactory loggerFactory2 = (LoggerFactory) OptionConverter.instantiateByClassName(findAndSubst, cls, this.loggerFactory);
            this.loggerFactory = loggerFactory2;
            PropertySetter.setProperties(loggerFactory2, properties, "log4j.factory.");
        }
    }

    /* access modifiers changed from: package-private */
    public void configureRootCategory(Properties properties, LoggerRepository loggerRepository) {
        String str = ROOT_LOGGER_PREFIX;
        String findAndSubst = OptionConverter.findAndSubst(ROOT_LOGGER_PREFIX, properties);
        if (findAndSubst == null) {
            findAndSubst = OptionConverter.findAndSubst(ROOT_CATEGORY_PREFIX, properties);
            str = ROOT_CATEGORY_PREFIX;
        }
        String str2 = str;
        String str3 = findAndSubst;
        if (str3 == null) {
            LogLog.debug("Could not find root logger information. Is this OK?");
            return;
        }
        Logger rootLogger = loggerRepository.getRootLogger();
        synchronized (rootLogger) {
            parseCategory(properties, rootLogger, str2, INTERNAL_ROOT_NAME, str3);
        }
    }

    /* access modifiers changed from: protected */
    public void parseCatsAndRenderers(Properties properties, LoggerRepository loggerRepository) {
        Enumeration<?> propertyNames = properties.propertyNames();
        while (propertyNames.hasMoreElements()) {
            String str = (String) propertyNames.nextElement();
            if (str.startsWith(CATEGORY_PREFIX) || str.startsWith(LOGGER_PREFIX)) {
                String str2 = null;
                if (str.startsWith(CATEGORY_PREFIX)) {
                    str2 = str.substring(15);
                } else if (str.startsWith(LOGGER_PREFIX)) {
                    str2 = str.substring(13);
                }
                String findAndSubst = OptionConverter.findAndSubst(str, properties);
                Logger logger = loggerRepository.getLogger(str2, this.loggerFactory);
                synchronized (logger) {
                    parseCategory(properties, logger, str, str2, findAndSubst);
                    parseAdditivityForLogger(properties, logger, str2);
                }
            } else if (str.startsWith(RENDERER_PREFIX)) {
                String substring = str.substring(15);
                String findAndSubst2 = OptionConverter.findAndSubst(str, properties);
                if (loggerRepository instanceof RendererSupport) {
                    RendererMap.addRenderer((RendererSupport) loggerRepository, substring, findAndSubst2);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void parseAdditivityForLogger(Properties properties, Logger logger, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(ADDITIVITY_PREFIX);
        stringBuffer.append(str);
        String findAndSubst = OptionConverter.findAndSubst(stringBuffer.toString(), properties);
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("Handling log4j.additivity.");
        stringBuffer2.append(str);
        stringBuffer2.append("=[");
        stringBuffer2.append(findAndSubst);
        stringBuffer2.append("]");
        LogLog.debug(stringBuffer2.toString());
        if (findAndSubst != null && !findAndSubst.equals("")) {
            boolean z = OptionConverter.toBoolean(findAndSubst, true);
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("Setting additivity for \"");
            stringBuffer3.append(str);
            stringBuffer3.append("\" to ");
            stringBuffer3.append(z);
            LogLog.debug(stringBuffer3.toString());
            logger.setAdditivity(z);
        }
    }

    /* access modifiers changed from: package-private */
    public void parseCategory(Properties properties, Logger logger, String str, String str2, String str3) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Parsing for [");
        stringBuffer.append(str2);
        stringBuffer.append("] with value=[");
        stringBuffer.append(str3);
        stringBuffer.append("].");
        LogLog.debug(stringBuffer.toString());
        StringTokenizer stringTokenizer = new StringTokenizer(str3, ",");
        if (!str3.startsWith(",") && !str3.equals("")) {
            if (stringTokenizer.hasMoreTokens()) {
                String nextToken = stringTokenizer.nextToken();
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Level token is [");
                stringBuffer2.append(nextToken);
                stringBuffer2.append("].");
                LogLog.debug(stringBuffer2.toString());
                if (!Configurator.INHERITED.equalsIgnoreCase(nextToken) && !Configurator.NULL.equalsIgnoreCase(nextToken)) {
                    logger.setLevel(OptionConverter.toLevel(nextToken, Level.DEBUG));
                } else if (str2.equals(INTERNAL_ROOT_NAME)) {
                    LogLog.warn("The root logger cannot be set to null.");
                } else {
                    logger.setLevel((Level) null);
                }
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("Category ");
                stringBuffer3.append(str2);
                stringBuffer3.append(" set to ");
                stringBuffer3.append(logger.getLevel());
                LogLog.debug(stringBuffer3.toString());
            } else {
                return;
            }
        }
        logger.removeAllAppenders();
        while (stringTokenizer.hasMoreTokens()) {
            String trim = stringTokenizer.nextToken().trim();
            if (trim != null && !trim.equals(",")) {
                StringBuffer stringBuffer4 = new StringBuffer();
                stringBuffer4.append("Parsing appender named \"");
                stringBuffer4.append(trim);
                stringBuffer4.append("\".");
                LogLog.debug(stringBuffer4.toString());
                Appender parseAppender = parseAppender(properties, trim);
                if (parseAppender != null) {
                    logger.addAppender(parseAppender);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Appender parseAppender(Properties properties, String str) {
        Appender registryGet = registryGet(str);
        if (registryGet != null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Appender \"");
            stringBuffer.append(str);
            stringBuffer.append("\" was already parsed.");
            LogLog.debug(stringBuffer.toString());
            return registryGet;
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append(APPENDER_PREFIX);
        stringBuffer2.append(str);
        String stringBuffer3 = stringBuffer2.toString();
        StringBuffer stringBuffer4 = new StringBuffer();
        stringBuffer4.append(stringBuffer3);
        stringBuffer4.append(".layout");
        String stringBuffer5 = stringBuffer4.toString();
        Class cls = class$org$apache$log4j$Appender;
        if (cls == null) {
            cls = class$("org.apache.log4j.Appender");
            class$org$apache$log4j$Appender = cls;
        }
        Appender appender = (Appender) OptionConverter.instantiateByKey(properties, stringBuffer3, cls, (Object) null);
        if (appender == null) {
            StringBuffer stringBuffer6 = new StringBuffer();
            stringBuffer6.append("Could not instantiate appender named \"");
            stringBuffer6.append(str);
            stringBuffer6.append("\".");
            LogLog.error(stringBuffer6.toString());
            return null;
        }
        appender.setName(str);
        if (appender instanceof OptionHandler) {
            if (appender.requiresLayout()) {
                Class cls2 = class$org$apache$log4j$Layout;
                if (cls2 == null) {
                    cls2 = class$("org.apache.log4j.Layout");
                    class$org$apache$log4j$Layout = cls2;
                }
                Layout layout = (Layout) OptionConverter.instantiateByKey(properties, stringBuffer5, cls2, (Object) null);
                if (layout != null) {
                    appender.setLayout(layout);
                    StringBuffer stringBuffer7 = new StringBuffer();
                    stringBuffer7.append("Parsing layout options for \"");
                    stringBuffer7.append(str);
                    stringBuffer7.append("\".");
                    LogLog.debug(stringBuffer7.toString());
                    StringBuffer stringBuffer8 = new StringBuffer();
                    stringBuffer8.append(stringBuffer5);
                    stringBuffer8.append(Consts.DOT);
                    PropertySetter.setProperties(layout, properties, stringBuffer8.toString());
                    StringBuffer stringBuffer9 = new StringBuffer();
                    stringBuffer9.append("End of parsing for \"");
                    stringBuffer9.append(str);
                    stringBuffer9.append("\".");
                    LogLog.debug(stringBuffer9.toString());
                }
            }
            StringBuffer stringBuffer10 = new StringBuffer();
            stringBuffer10.append(stringBuffer3);
            stringBuffer10.append(Consts.DOT);
            PropertySetter.setProperties(appender, properties, stringBuffer10.toString());
            StringBuffer stringBuffer11 = new StringBuffer();
            stringBuffer11.append("Parsed \"");
            stringBuffer11.append(str);
            stringBuffer11.append("\" options.");
            LogLog.debug(stringBuffer11.toString());
        }
        registryPut(appender);
        return appender;
    }

    /* access modifiers changed from: package-private */
    public void registryPut(Appender appender) {
        this.registry.put(appender.getName(), appender);
    }

    /* access modifiers changed from: package-private */
    public Appender registryGet(String str) {
        return (Appender) this.registry.get(str);
    }
}
