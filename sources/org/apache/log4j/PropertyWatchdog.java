package org.apache.log4j;

import org.apache.log4j.helpers.FileWatchdog;

/* compiled from: PropertyConfigurator */
class PropertyWatchdog extends FileWatchdog {
    PropertyWatchdog(String str) {
        super(str);
    }

    public void doOnChange() {
        new PropertyConfigurator().doConfigure(this.filename, LogManager.getLoggerRepository());
    }
}
