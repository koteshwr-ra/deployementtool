package com.ciot.waterchassis;

import java.util.concurrent.ThreadFactory;

public class WaterChassisFactory implements ThreadFactory {
    private int mCounter = 0;
    private String mName;

    public WaterChassisFactory(String str) {
        this.mName = str;
    }

    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, this.mName + "-Thread-" + this.mCounter);
        this.mCounter = this.mCounter + 1;
        return thread;
    }
}
