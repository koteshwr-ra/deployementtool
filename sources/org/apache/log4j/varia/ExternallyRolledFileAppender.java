package org.apache.log4j.varia;

import org.apache.log4j.RollingFileAppender;

public class ExternallyRolledFileAppender extends RollingFileAppender {
    public static final String OK = "OK";
    public static final String ROLL_OVER = "RollOver";
    HUP hup;
    int port = 0;

    public void setPort(int i) {
        this.port = i;
    }

    public int getPort() {
        return this.port;
    }

    public void activateOptions() {
        super.activateOptions();
        if (this.port != 0) {
            HUP hup2 = this.hup;
            if (hup2 != null) {
                hup2.interrupt();
            }
            HUP hup3 = new HUP(this, this.port);
            this.hup = hup3;
            hup3.setDaemon(true);
            this.hup.start();
        }
    }
}
