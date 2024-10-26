package org.apache.log4j;

public class BasicConfigurator {
    protected BasicConfigurator() {
    }

    public static void configure() {
        Logger.getRootLogger().addAppender(new ConsoleAppender(new PatternLayout(PatternLayout.TTCC_CONVERSION_PATTERN)));
    }

    public static void configure(Appender appender) {
        Logger.getRootLogger().addAppender(appender);
    }

    public static void resetConfiguration() {
        LogManager.resetConfiguration();
    }
}
