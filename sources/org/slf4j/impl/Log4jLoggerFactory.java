package org.slf4j.impl;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.LogManager;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

public class Log4jLoggerFactory implements ILoggerFactory {
    Map loggerMap = new HashMap();

    public Logger getLogger(String str) {
        Logger logger;
        org.apache.log4j.Logger logger2;
        synchronized (this) {
            logger = (Logger) this.loggerMap.get(str);
            if (logger == null) {
                if (str.equalsIgnoreCase(Logger.ROOT_LOGGER_NAME)) {
                    logger2 = LogManager.getRootLogger();
                } else {
                    logger2 = LogManager.getLogger(str);
                }
                Log4jLoggerAdapter log4jLoggerAdapter = new Log4jLoggerAdapter(logger2);
                this.loggerMap.put(str, log4jLoggerAdapter);
                logger = log4jLoggerAdapter;
            }
        }
        return logger;
    }
}
