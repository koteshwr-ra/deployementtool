package org.apache.mina.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NamePreservingRunnable implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(NamePreservingRunnable.class);
    private final String newName;
    private final Runnable runnable;

    public NamePreservingRunnable(Runnable runnable2, String str) {
        this.runnable = runnable2;
        this.newName = str;
    }

    public void run() {
        Thread currentThread = Thread.currentThread();
        String name = currentThread.getName();
        String str = this.newName;
        if (str != null) {
            setName(currentThread, str);
        }
        try {
            this.runnable.run();
        } finally {
            setName(currentThread, name);
        }
    }

    private void setName(Thread thread, String str) {
        try {
            thread.setName(str);
        } catch (SecurityException e) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("Failed to set the thread name.", (Throwable) e);
            }
        }
    }
}
