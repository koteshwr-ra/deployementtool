package org.slf4j;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import org.slf4j.helpers.SubstituteLoggerFactory;
import org.slf4j.helpers.Util;
import org.slf4j.impl.StaticLoggerBinder;

public final class LoggerFactory {
    private static final String[] API_COMPATIBILITY_LIST = {"1.5.5", "1.5.6", "1.5.7", "1.5.8"};
    static final int FAILED_INITILIZATION = 2;
    static final int GET_SINGLETON_EXISTS = 2;
    static final int GET_SINGLETON_INEXISTENT = 1;
    static int GET_SINGLETON_METHOD = 0;
    static int INITIALIZATION_STATE = 0;
    static final String MULTIPLE_BINDINGS_URL = "http://www.slf4j.org/codes.html#multiple_bindings";
    static final String NO_STATICLOGGERBINDER_URL = "http://www.slf4j.org/codes.html#StaticLoggerBinder";
    static final String NULL_LF_URL = "http://www.slf4j.org/codes.html#null_LF";
    static final int ONGOING_INITILIZATION = 1;
    private static String STATIC_LOGGER_BINDER_PATH = "org/slf4j/impl/StaticLoggerBinder.class";
    static final String SUBSTITUTE_LOGGER_URL = "http://www.slf4j.org/codes.html#substituteLogger";
    static final int SUCCESSFUL_INITILIZATION = 3;
    static SubstituteLoggerFactory TEMP_FACTORY = new SubstituteLoggerFactory();
    static final int UNINITIALIZED = 0;
    static final String UNSUCCESSFUL_INIT_MSG = "org.slf4j.LoggerFactory could not be successfully initialized. See also http://www.slf4j.org/codes.html#unsuccessfulInit";
    static final String UNSUCCESSFUL_INIT_URL = "http://www.slf4j.org/codes.html#unsuccessfulInit";
    static final String VERSION_MISMATCH = "http://www.slf4j.org/codes.html#version_mismatch";
    static /* synthetic */ Class class$org$slf4j$LoggerFactory;

    private LoggerFactory() {
    }

    static void reset() {
        INITIALIZATION_STATE = 0;
        GET_SINGLETON_METHOD = 0;
        TEMP_FACTORY = new SubstituteLoggerFactory();
    }

    private static final void performInitialization() {
        bind();
        versionSanityCheck();
        singleImplementationSanityCheck();
    }

    private static final void bind() {
        try {
            getSingleton();
            INITIALIZATION_STATE = 3;
            emitSubstitureLoggerWarning();
        } catch (NoClassDefFoundError e) {
            INITIALIZATION_STATE = 2;
            String message = e.getMessage();
            if (!(message == null || message.indexOf("org/slf4j/impl/StaticLoggerBinder") == -1)) {
                Util.reportFailure("Failed to load class \"org.slf4j.impl.StaticLoggerBinder\".");
                Util.reportFailure("See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.");
            }
            throw e;
        } catch (Exception e2) {
            INITIALIZATION_STATE = 2;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Failed to instantiate logger [");
            stringBuffer.append(getSingleton().getLoggerFactoryClassStr());
            stringBuffer.append("]");
            Util.reportFailure(stringBuffer.toString(), e2);
        }
    }

    private static final void emitSubstitureLoggerWarning() {
        List loggerNameList = TEMP_FACTORY.getLoggerNameList();
        if (loggerNameList.size() != 0) {
            Util.reportFailure("The following loggers will not work becasue they were created");
            Util.reportFailure("during the default configuration phase of the underlying logging system.");
            Util.reportFailure("See also http://www.slf4j.org/codes.html#substituteLogger");
            for (int i = 0; i < loggerNameList.size(); i++) {
                Util.reportFailure((String) loggerNameList.get(i));
            }
        }
    }

    private static final void versionSanityCheck() {
        try {
            String str = StaticLoggerBinder.REQUESTED_API_VERSION;
            boolean z = false;
            for (String equals : API_COMPATIBILITY_LIST) {
                if (equals.equals(str)) {
                    z = true;
                }
            }
            if (!z) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("The requested version ");
                stringBuffer.append(str);
                stringBuffer.append(" by your slf4j binding is not compatible with ");
                stringBuffer.append(Arrays.asList(API_COMPATIBILITY_LIST).toString());
                Util.reportFailure(stringBuffer.toString());
                Util.reportFailure("See http://www.slf4j.org/codes.html#version_mismatch for further details.");
            }
        } catch (NoSuchFieldError unused) {
        } catch (Throwable th) {
            Util.reportFailure("Unexpected problem occured during version sanity check", th);
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    private static void singleImplementationSanityCheck() {
        Class cls;
        try {
            if (class$org$slf4j$LoggerFactory == null) {
                cls = class$("org.slf4j.LoggerFactory");
                class$org$slf4j$LoggerFactory = cls;
            } else {
                cls = class$org$slf4j$LoggerFactory;
            }
            Enumeration<URL> resources = cls.getClassLoader().getResources(STATIC_LOGGER_BINDER_PATH);
            ArrayList arrayList = new ArrayList();
            while (resources.hasMoreElements()) {
                arrayList.add(resources.nextElement());
            }
            if (arrayList.size() > 1) {
                Util.reportFailure("Class path contains multiple SLF4J bindings.");
                for (int i = 0; i < arrayList.size(); i++) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Found binding in [");
                    stringBuffer.append(arrayList.get(i));
                    stringBuffer.append("]");
                    Util.reportFailure(stringBuffer.toString());
                }
                Util.reportFailure("See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.");
            }
        } catch (IOException e) {
            Util.reportFailure("Error getting resources from path", e);
        }
    }

    private static final StaticLoggerBinder getSingleton() {
        int i = GET_SINGLETON_METHOD;
        if (i == 1) {
            return StaticLoggerBinder.SINGLETON;
        }
        if (i == 2) {
            return StaticLoggerBinder.getSingleton();
        }
        try {
            StaticLoggerBinder singleton = StaticLoggerBinder.getSingleton();
            GET_SINGLETON_METHOD = 2;
            return singleton;
        } catch (NoSuchMethodError unused) {
            GET_SINGLETON_METHOD = 1;
            return StaticLoggerBinder.SINGLETON;
        }
    }

    public static Logger getLogger(String str) {
        return getILoggerFactory().getLogger(str);
    }

    public static Logger getLogger(Class cls) {
        return getLogger(cls.getName());
    }

    public static ILoggerFactory getILoggerFactory() {
        if (INITIALIZATION_STATE == 0) {
            INITIALIZATION_STATE = 1;
            performInitialization();
        }
        int i = INITIALIZATION_STATE;
        if (i == 1) {
            return TEMP_FACTORY;
        }
        if (i == 2) {
            throw new IllegalStateException(UNSUCCESSFUL_INIT_MSG);
        } else if (i == 3) {
            return getSingleton().getLoggerFactory();
        } else {
            throw new IllegalStateException("Unreachable code");
        }
    }
}
