package org.apache.log4j.xml;

import com.alibaba.android.arouter.compiler.utils.Consts;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.util.Hashtable;
import java.util.Properties;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import org.apache.log4j.Appender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.config.PropertySetter;
import org.apache.log4j.helpers.Loader;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.or.RendererMap;
import org.apache.log4j.spi.AppenderAttachable;
import org.apache.log4j.spi.Configurator;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.RendererSupport;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class DOMConfigurator implements Configurator {
    static final String ADDITIVITY_ATTR = "additivity";
    static final String APPENDER_REF_TAG = "appender-ref";
    static final String APPENDER_TAG = "appender";
    static final String CATEGORY = "category";
    static final String CATEGORY_FACTORY_TAG = "categoryFactory";
    static final String CLASS_ATTR = "class";
    static final String CONFIGURATION_TAG = "log4j:configuration";
    static final String CONFIG_DEBUG_ATTR = "configDebug";
    static final String EMPTY_STR = "";
    static final String ERROR_HANDLER_TAG = "errorHandler";
    static final String FILTER_TAG = "filter";
    static final String INTERNAL_DEBUG_ATTR = "debug";
    static final String LAYOUT_TAG = "layout";
    static final String LEVEL_TAG = "level";
    static final String LOGGER = "logger";
    static final String LOGGER_REF = "logger-ref";
    static final String NAME_ATTR = "name";
    static final String OLD_CONFIGURATION_TAG = "configuration";
    static final Class[] ONE_STRING_PARAM;
    static final String PARAM_TAG = "param";
    static final String PRIORITY_TAG = "priority";
    static final String REF_ATTR = "ref";
    static final String RENDERED_CLASS_ATTR = "renderedClass";
    static final String RENDERER_TAG = "renderer";
    static final String RENDERING_CLASS_ATTR = "renderingClass";
    static final String ROOT_REF = "root-ref";
    static final String ROOT_TAG = "root";
    static final String THRESHOLD_ATTR = "threshold";
    static final String VALUE_ATTR = "value";
    static /* synthetic */ Class class$java$lang$String = null;
    static /* synthetic */ Class class$org$apache$log4j$spi$ErrorHandler = null;
    static /* synthetic */ Class class$org$apache$log4j$spi$Filter = null;
    static /* synthetic */ Class class$org$apache$log4j$spi$LoggerFactory = null;
    static final String dbfKey = "javax.xml.parsers.DocumentBuilderFactory";
    Hashtable appenderBag = new Hashtable();
    Properties props;
    LoggerRepository repository;

    private interface ParseAction {
        Document parse(DocumentBuilder documentBuilder) throws SAXException, IOException;
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class[] clsArr = new Class[1];
        Class cls = class$java$lang$String;
        if (cls == null) {
            cls = class$(Consts.STRING);
            class$java$lang$String = cls;
        }
        clsArr[0] = cls;
        ONE_STRING_PARAM = clsArr;
    }

    /* access modifiers changed from: protected */
    public Appender findAppenderByName(Document document, String str) {
        Element element;
        Appender appender = (Appender) this.appenderBag.get(str);
        if (appender != null) {
            return appender;
        }
        NodeList elementsByTagName = document.getElementsByTagName(APPENDER_TAG);
        int i = 0;
        while (true) {
            if (i >= elementsByTagName.getLength()) {
                element = null;
                break;
            }
            Node item = elementsByTagName.item(i);
            if (str.equals(item.getAttributes().getNamedItem("name").getNodeValue())) {
                element = (Element) item;
                break;
            }
            i++;
        }
        if (element == null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("No appender named [");
            stringBuffer.append(str);
            stringBuffer.append("] could be found.");
            LogLog.error(stringBuffer.toString());
            return null;
        }
        Appender parseAppender = parseAppender(element);
        this.appenderBag.put(str, parseAppender);
        return parseAppender;
    }

    /* access modifiers changed from: protected */
    public Appender findAppenderByReference(Element element) {
        return findAppenderByName(element.getOwnerDocument(), subst(element.getAttribute(REF_ATTR)));
    }

    /* access modifiers changed from: protected */
    public Appender parseAppender(Element element) {
        String subst = subst(element.getAttribute(CLASS_ATTR));
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Class name: [");
        stringBuffer.append(subst);
        stringBuffer.append(']');
        LogLog.debug(stringBuffer.toString());
        try {
            Appender appender = (Appender) Loader.loadClass(subst).newInstance();
            PropertySetter propertySetter = new PropertySetter(appender);
            appender.setName(subst(element.getAttribute("name")));
            NodeList childNodes = element.getChildNodes();
            int length = childNodes.getLength();
            for (int i = 0; i < length; i++) {
                Node item = childNodes.item(i);
                if (item.getNodeType() == 1) {
                    Element element2 = (Element) item;
                    if (element2.getTagName().equals(PARAM_TAG)) {
                        setParameter(element2, propertySetter);
                    } else if (element2.getTagName().equals(LAYOUT_TAG)) {
                        appender.setLayout(parseLayout(element2));
                    } else if (element2.getTagName().equals(FILTER_TAG)) {
                        parseFilters(element2, appender);
                    } else if (element2.getTagName().equals(ERROR_HANDLER_TAG)) {
                        parseErrorHandler(element2, appender);
                    } else if (element2.getTagName().equals(APPENDER_REF_TAG)) {
                        String subst2 = subst(element2.getAttribute(REF_ATTR));
                        if (appender instanceof AppenderAttachable) {
                            StringBuffer stringBuffer2 = new StringBuffer();
                            stringBuffer2.append("Attaching appender named [");
                            stringBuffer2.append(subst2);
                            stringBuffer2.append("] to appender named [");
                            stringBuffer2.append(appender.getName());
                            stringBuffer2.append("].");
                            LogLog.debug(stringBuffer2.toString());
                            ((AppenderAttachable) appender).addAppender(findAppenderByReference(element2));
                        } else {
                            StringBuffer stringBuffer3 = new StringBuffer();
                            stringBuffer3.append("Requesting attachment of appender named [");
                            stringBuffer3.append(subst2);
                            stringBuffer3.append("] to appender named [");
                            stringBuffer3.append(appender.getName());
                            stringBuffer3.append("] which does not implement org.apache.log4j.spi.AppenderAttachable.");
                            LogLog.error(stringBuffer3.toString());
                        }
                    }
                }
            }
            propertySetter.activate();
            return appender;
        } catch (Exception e) {
            LogLog.error("Could not create an Appender. Reported error follows.", e);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void parseErrorHandler(Element element, Appender appender) {
        String subst = subst(element.getAttribute(CLASS_ATTR));
        Class cls = class$org$apache$log4j$spi$ErrorHandler;
        if (cls == null) {
            cls = class$("org.apache.log4j.spi.ErrorHandler");
            class$org$apache$log4j$spi$ErrorHandler = cls;
        }
        ErrorHandler errorHandler = (ErrorHandler) OptionConverter.instantiateByClassName(subst, cls, (Object) null);
        if (errorHandler != null) {
            errorHandler.setAppender(appender);
            PropertySetter propertySetter = new PropertySetter(errorHandler);
            NodeList childNodes = element.getChildNodes();
            int length = childNodes.getLength();
            for (int i = 0; i < length; i++) {
                Node item = childNodes.item(i);
                if (item.getNodeType() == 1) {
                    Element element2 = (Element) item;
                    String tagName = element2.getTagName();
                    if (tagName.equals(PARAM_TAG)) {
                        setParameter(element2, propertySetter);
                    } else if (tagName.equals(APPENDER_REF_TAG)) {
                        errorHandler.setBackupAppender(findAppenderByReference(element2));
                    } else if (tagName.equals(LOGGER_REF)) {
                        errorHandler.setLogger(this.repository.getLogger(element2.getAttribute(REF_ATTR)));
                    } else if (tagName.equals(ROOT_REF)) {
                        errorHandler.setLogger(this.repository.getRootLogger());
                    }
                }
            }
            propertySetter.activate();
            appender.setErrorHandler(errorHandler);
        }
    }

    /* access modifiers changed from: protected */
    public void parseFilters(Element element, Appender appender) {
        String subst = subst(element.getAttribute(CLASS_ATTR));
        Class cls = class$org$apache$log4j$spi$Filter;
        if (cls == null) {
            cls = class$("org.apache.log4j.spi.Filter");
            class$org$apache$log4j$spi$Filter = cls;
        }
        Filter filter = (Filter) OptionConverter.instantiateByClassName(subst, cls, (Object) null);
        if (filter != null) {
            PropertySetter propertySetter = new PropertySetter(filter);
            NodeList childNodes = element.getChildNodes();
            int length = childNodes.getLength();
            for (int i = 0; i < length; i++) {
                Node item = childNodes.item(i);
                if (item.getNodeType() == 1) {
                    Element element2 = (Element) item;
                    if (element2.getTagName().equals(PARAM_TAG)) {
                        setParameter(element2, propertySetter);
                    }
                }
            }
            propertySetter.activate();
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Adding filter of type [");
            stringBuffer.append(filter.getClass());
            stringBuffer.append("] to appender named [");
            stringBuffer.append(appender.getName());
            stringBuffer.append("].");
            LogLog.debug(stringBuffer.toString());
            appender.addFilter(filter);
        }
    }

    /* access modifiers changed from: protected */
    public void parseCategory(Element element) {
        Logger logger;
        String subst = subst(element.getAttribute("name"));
        String subst2 = subst(element.getAttribute(CLASS_ATTR));
        if ("".equals(subst2)) {
            LogLog.debug("Retreiving an instance of org.apache.log4j.Logger.");
            logger = this.repository.getLogger(subst);
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Desired logger sub-class: [");
            stringBuffer.append(subst2);
            stringBuffer.append(']');
            LogLog.debug(stringBuffer.toString());
            try {
                logger = (Logger) Loader.loadClass(subst2).getMethod("getLogger", ONE_STRING_PARAM).invoke((Object) null, new Object[]{subst});
            } catch (Exception e) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Could not retrieve category [");
                stringBuffer2.append(subst);
                stringBuffer2.append("]. Reported error follows.");
                LogLog.error(stringBuffer2.toString(), e);
                return;
            }
        }
        synchronized (logger) {
            boolean z = OptionConverter.toBoolean(subst(element.getAttribute(ADDITIVITY_ATTR)), true);
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("Setting [");
            stringBuffer3.append(logger.getName());
            stringBuffer3.append("] additivity to [");
            stringBuffer3.append(z);
            stringBuffer3.append("].");
            LogLog.debug(stringBuffer3.toString());
            logger.setAdditivity(z);
            parseChildrenOfLoggerElement(element, logger, false);
        }
    }

    /* access modifiers changed from: protected */
    public void parseCategoryFactory(Element element) {
        String subst = subst(element.getAttribute(CLASS_ATTR));
        if ("".equals(subst)) {
            LogLog.error("Category Factory tag class attribute not found.");
            LogLog.debug("No Category Factory configured.");
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Desired category factory: [");
        stringBuffer.append(subst);
        stringBuffer.append(']');
        LogLog.debug(stringBuffer.toString());
        Class cls = class$org$apache$log4j$spi$LoggerFactory;
        if (cls == null) {
            cls = class$("org.apache.log4j.spi.LoggerFactory");
            class$org$apache$log4j$spi$LoggerFactory = cls;
        }
        PropertySetter propertySetter = new PropertySetter(OptionConverter.instantiateByClassName(subst, cls, (Object) null));
        NodeList childNodes = element.getChildNodes();
        int length = childNodes.getLength();
        for (int i = 0; i < length; i++) {
            Node item = childNodes.item(i);
            if (item.getNodeType() == 1) {
                Element element2 = (Element) item;
                if (element2.getTagName().equals(PARAM_TAG)) {
                    setParameter(element2, propertySetter);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void parseRoot(Element element) {
        Logger rootLogger = this.repository.getRootLogger();
        synchronized (rootLogger) {
            parseChildrenOfLoggerElement(element, rootLogger, true);
        }
    }

    /* access modifiers changed from: protected */
    public void parseChildrenOfLoggerElement(Element element, Logger logger, boolean z) {
        PropertySetter propertySetter = new PropertySetter(logger);
        logger.removeAllAppenders();
        NodeList childNodes = element.getChildNodes();
        int length = childNodes.getLength();
        for (int i = 0; i < length; i++) {
            Node item = childNodes.item(i);
            if (item.getNodeType() == 1) {
                Element element2 = (Element) item;
                String tagName = element2.getTagName();
                if (tagName.equals(APPENDER_REF_TAG)) {
                    Appender findAppenderByReference = findAppenderByReference(element2);
                    String subst = subst(element2.getAttribute(REF_ATTR));
                    if (findAppenderByReference != null) {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("Adding appender named [");
                        stringBuffer.append(subst);
                        stringBuffer.append("] to category [");
                        stringBuffer.append(logger.getName());
                        stringBuffer.append("].");
                        LogLog.debug(stringBuffer.toString());
                    } else {
                        StringBuffer stringBuffer2 = new StringBuffer();
                        stringBuffer2.append("Appender named [");
                        stringBuffer2.append(subst);
                        stringBuffer2.append("] not found.");
                        LogLog.debug(stringBuffer2.toString());
                    }
                    logger.addAppender(findAppenderByReference);
                } else if (tagName.equals(LEVEL_TAG)) {
                    parseLevel(element2, logger, z);
                } else if (tagName.equals(PRIORITY_TAG)) {
                    parseLevel(element2, logger, z);
                } else if (tagName.equals(PARAM_TAG)) {
                    setParameter(element2, propertySetter);
                }
            }
        }
        propertySetter.activate();
    }

    /* access modifiers changed from: protected */
    public Layout parseLayout(Element element) {
        String subst = subst(element.getAttribute(CLASS_ATTR));
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Parsing layout of class: \"");
        stringBuffer.append(subst);
        stringBuffer.append("\"");
        LogLog.debug(stringBuffer.toString());
        try {
            Layout layout = (Layout) Loader.loadClass(subst).newInstance();
            PropertySetter propertySetter = new PropertySetter(layout);
            NodeList childNodes = element.getChildNodes();
            int length = childNodes.getLength();
            for (int i = 0; i < length; i++) {
                Node item = childNodes.item(i);
                if (item.getNodeType() == 1) {
                    Element element2 = (Element) item;
                    if (element2.getTagName().equals(PARAM_TAG)) {
                        setParameter(element2, propertySetter);
                    }
                }
            }
            propertySetter.activate();
            return layout;
        } catch (Exception e) {
            LogLog.error("Could not create the Layout. Reported error follows.", e);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void parseRenderer(Element element) {
        String subst = subst(element.getAttribute(RENDERING_CLASS_ATTR));
        String subst2 = subst(element.getAttribute(RENDERED_CLASS_ATTR));
        LoggerRepository loggerRepository = this.repository;
        if (loggerRepository instanceof RendererSupport) {
            RendererMap.addRenderer((RendererSupport) loggerRepository, subst2, subst);
        }
    }

    /* access modifiers changed from: protected */
    public void parseLevel(Element element, Logger logger, boolean z) {
        String name = logger.getName();
        if (z) {
            name = ROOT_TAG;
        }
        String subst = subst(element.getAttribute(VALUE_ATTR));
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Level value for ");
        stringBuffer.append(name);
        stringBuffer.append(" is  [");
        stringBuffer.append(subst);
        stringBuffer.append("].");
        LogLog.debug(stringBuffer.toString());
        if (!Configurator.INHERITED.equalsIgnoreCase(subst) && !Configurator.NULL.equalsIgnoreCase(subst)) {
            String subst2 = subst(element.getAttribute(CLASS_ATTR));
            if ("".equals(subst2)) {
                logger.setLevel(OptionConverter.toLevel(subst, Level.DEBUG));
            } else {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Desired Level sub-class: [");
                stringBuffer2.append(subst2);
                stringBuffer2.append(']');
                LogLog.debug(stringBuffer2.toString());
                try {
                    logger.setLevel((Level) Loader.loadClass(subst2).getMethod("toLevel", ONE_STRING_PARAM).invoke((Object) null, new Object[]{subst}));
                } catch (Exception e) {
                    StringBuffer stringBuffer3 = new StringBuffer();
                    stringBuffer3.append("Could not create level [");
                    stringBuffer3.append(subst);
                    stringBuffer3.append("]. Reported error follows.");
                    LogLog.error(stringBuffer3.toString(), e);
                    return;
                }
            }
        } else if (z) {
            LogLog.error("Root level cannot be inherited. Ignoring directive.");
        } else {
            logger.setLevel((Level) null);
        }
        StringBuffer stringBuffer4 = new StringBuffer();
        stringBuffer4.append(name);
        stringBuffer4.append(" level set to ");
        stringBuffer4.append(logger.getLevel());
        LogLog.debug(stringBuffer4.toString());
    }

    /* access modifiers changed from: protected */
    public void setParameter(Element element, PropertySetter propertySetter) {
        propertySetter.setProperty(subst(element.getAttribute("name")), subst(OptionConverter.convertSpecialChars(element.getAttribute(VALUE_ATTR))));
    }

    public static void configure(Element element) {
        new DOMConfigurator().doConfigure(element, LogManager.getLoggerRepository());
    }

    public static void configureAndWatch(String str) {
        configureAndWatch(str, 60000);
    }

    public static void configureAndWatch(String str, long j) {
        XMLWatchdog xMLWatchdog = new XMLWatchdog(str);
        xMLWatchdog.setDelay(j);
        xMLWatchdog.start();
    }

    public void doConfigure(final String str, LoggerRepository loggerRepository) {
        doConfigure((ParseAction) new ParseAction() {
            public Document parse(DocumentBuilder documentBuilder) throws SAXException, IOException {
                return documentBuilder.parse(new File(str));
            }

            public String toString() {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("file [");
                stringBuffer.append(str);
                stringBuffer.append("]");
                return stringBuffer.toString();
            }
        }, loggerRepository);
    }

    public void doConfigure(final URL url, LoggerRepository loggerRepository) {
        doConfigure((ParseAction) new ParseAction() {
            public Document parse(DocumentBuilder documentBuilder) throws SAXException, IOException {
                return documentBuilder.parse(url.toString());
            }

            public String toString() {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("url [");
                stringBuffer.append(url.toString());
                stringBuffer.append("]");
                return stringBuffer.toString();
            }
        }, loggerRepository);
    }

    public void doConfigure(final InputStream inputStream, LoggerRepository loggerRepository) throws FactoryConfigurationError {
        doConfigure((ParseAction) new ParseAction() {
            public Document parse(DocumentBuilder documentBuilder) throws SAXException, IOException {
                InputSource inputSource = new InputSource(inputStream);
                inputSource.setSystemId("dummy://log4j.dtd");
                return documentBuilder.parse(inputSource);
            }

            public String toString() {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("input stream [");
                stringBuffer.append(inputStream.toString());
                stringBuffer.append("]");
                return stringBuffer.toString();
            }
        }, loggerRepository);
    }

    public void doConfigure(final Reader reader, LoggerRepository loggerRepository) throws FactoryConfigurationError {
        doConfigure((ParseAction) new ParseAction() {
            public Document parse(DocumentBuilder documentBuilder) throws SAXException, IOException {
                InputSource inputSource = new InputSource(reader);
                inputSource.setSystemId("dummy://log4j.dtd");
                return documentBuilder.parse(inputSource);
            }

            public String toString() {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("reader [");
                stringBuffer.append(reader.toString());
                stringBuffer.append("]");
                return stringBuffer.toString();
            }
        }, loggerRepository);
    }

    /* access modifiers changed from: protected */
    public void doConfigure(final InputSource inputSource, LoggerRepository loggerRepository) throws FactoryConfigurationError {
        if (inputSource.getSystemId() == null) {
            inputSource.setSystemId("dummy://log4j.dtd");
        }
        doConfigure((ParseAction) new ParseAction() {
            public Document parse(DocumentBuilder documentBuilder) throws SAXException, IOException {
                return documentBuilder.parse(inputSource);
            }

            public String toString() {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("input source [");
                stringBuffer.append(inputSource.toString());
                stringBuffer.append("]");
                return stringBuffer.toString();
            }
        }, loggerRepository);
    }

    private final void doConfigure(ParseAction parseAction, LoggerRepository loggerRepository) throws FactoryConfigurationError {
        this.repository = loggerRepository;
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("System property is :");
            stringBuffer.append(OptionConverter.getSystemProperty(dbfKey, (String) null));
            LogLog.debug(stringBuffer.toString());
            DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
            LogLog.debug("Standard DocumentBuilderFactory search succeded.");
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("DocumentBuilderFactory is: ");
            stringBuffer2.append(newInstance.getClass().getName());
            LogLog.debug(stringBuffer2.toString());
            try {
                newInstance.setValidating(true);
                DocumentBuilder newDocumentBuilder = newInstance.newDocumentBuilder();
                newDocumentBuilder.setErrorHandler(new SAXErrorHandler());
                newDocumentBuilder.setEntityResolver(new Log4jEntityResolver());
                parse(parseAction.parse(newDocumentBuilder).getDocumentElement());
            } catch (Exception e) {
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("Could not parse ");
                stringBuffer3.append(parseAction.toString());
                stringBuffer3.append(com.alibaba.android.arouter.utils.Consts.DOT);
                LogLog.error(stringBuffer3.toString(), e);
            }
        } catch (FactoryConfigurationError e2) {
            LogLog.debug("Could not instantiate a DocumentBuilderFactory.", e2.getException());
            throw e2;
        }
    }

    public void doConfigure(Element element, LoggerRepository loggerRepository) {
        this.repository = loggerRepository;
        parse(element);
    }

    public static void configure(String str) throws FactoryConfigurationError {
        new DOMConfigurator().doConfigure(str, LogManager.getLoggerRepository());
    }

    public static void configure(URL url) throws FactoryConfigurationError {
        new DOMConfigurator().doConfigure(url, LogManager.getLoggerRepository());
    }

    /* access modifiers changed from: protected */
    public void parse(Element element) {
        String tagName = element.getTagName();
        if (!tagName.equals(CONFIGURATION_TAG)) {
            if (tagName.equals(OLD_CONFIGURATION_TAG)) {
                LogLog.warn("The <configuration> element has been deprecated.");
                LogLog.warn("Use the <log4j:configuration> element instead.");
            } else {
                LogLog.error("DOM element is - not a <log4j:configuration> element.");
                return;
            }
        }
        String subst = subst(element.getAttribute(INTERNAL_DEBUG_ATTR));
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("debug attribute= \"");
        stringBuffer.append(subst);
        stringBuffer.append("\".");
        LogLog.debug(stringBuffer.toString());
        if (subst.equals("") || subst.equals(Configurator.NULL)) {
            LogLog.debug("Ignoring debug attribute.");
        } else {
            LogLog.setInternalDebugging(OptionConverter.toBoolean(subst, true));
        }
        String subst2 = subst(element.getAttribute(CONFIG_DEBUG_ATTR));
        if (!subst2.equals("") && !subst2.equals(Configurator.NULL)) {
            LogLog.warn("The \"configDebug\" attribute is deprecated.");
            LogLog.warn("Use the \"debug\" attribute instead.");
            LogLog.setInternalDebugging(OptionConverter.toBoolean(subst2, true));
        }
        String subst3 = subst(element.getAttribute(THRESHOLD_ATTR));
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("Threshold =\"");
        stringBuffer2.append(subst3);
        stringBuffer2.append("\".");
        LogLog.debug(stringBuffer2.toString());
        if (!"".equals(subst3) && !Configurator.NULL.equals(subst3)) {
            this.repository.setThreshold(subst3);
        }
        NodeList childNodes = element.getChildNodes();
        int length = childNodes.getLength();
        for (int i = 0; i < length; i++) {
            Node item = childNodes.item(i);
            if (item.getNodeType() == 1) {
                Element element2 = (Element) item;
                if (element2.getTagName().equals(CATEGORY_FACTORY_TAG)) {
                    parseCategoryFactory(element2);
                }
            }
        }
        for (int i2 = 0; i2 < length; i2++) {
            Node item2 = childNodes.item(i2);
            if (item2.getNodeType() == 1) {
                Element element3 = (Element) item2;
                String tagName2 = element3.getTagName();
                if (tagName2.equals(CATEGORY) || tagName2.equals(LOGGER)) {
                    parseCategory(element3);
                } else if (tagName2.equals(ROOT_TAG)) {
                    parseRoot(element3);
                } else if (tagName2.equals(RENDERER_TAG)) {
                    parseRenderer(element3);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public String subst(String str) {
        try {
            return OptionConverter.substVars(str, this.props);
        } catch (IllegalArgumentException e) {
            LogLog.warn("Could not perform variable substitution.", e);
            return str;
        }
    }
}
