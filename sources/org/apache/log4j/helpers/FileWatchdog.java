package org.apache.log4j.helpers;

import java.io.File;

public abstract class FileWatchdog extends Thread {
    public static final long DEFAULT_DELAY = 60000;
    protected long delay = 60000;
    File file;
    protected String filename;
    boolean interrupted = false;
    long lastModif = 0;
    boolean warnedAlready = false;

    /* access modifiers changed from: protected */
    public abstract void doOnChange();

    protected FileWatchdog(String str) {
        this.filename = str;
        this.file = new File(str);
        setDaemon(true);
        checkAndConfigure();
    }

    public void setDelay(long j) {
        this.delay = j;
    }

    /* access modifiers changed from: protected */
    public void checkAndConfigure() {
        try {
            if (this.file.exists()) {
                long lastModified = this.file.lastModified();
                if (lastModified > this.lastModif) {
                    this.lastModif = lastModified;
                    doOnChange();
                    this.warnedAlready = false;
                }
            } else if (!this.warnedAlready) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("[");
                stringBuffer.append(this.filename);
                stringBuffer.append("] does not exist.");
                LogLog.debug(stringBuffer.toString());
                this.warnedAlready = true;
            }
        } catch (SecurityException unused) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Was not allowed to read check file existance, file:[");
            stringBuffer2.append(this.filename);
            stringBuffer2.append("].");
            LogLog.warn(stringBuffer2.toString());
            this.interrupted = true;
        }
    }

    public void run() {
        while (!this.interrupted) {
            try {
                Thread.currentThread();
                Thread.sleep(this.delay);
            } catch (InterruptedException unused) {
            }
            checkAndConfigure();
        }
    }
}
