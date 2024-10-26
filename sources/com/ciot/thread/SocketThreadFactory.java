package com.ciot.thread;

import java.util.concurrent.ThreadFactory;

public class SocketThreadFactory implements ThreadFactory {
    private int mCounter = 0;
    private final String mName;

    public SocketThreadFactory(String str) {
        this.mName = str;
    }

    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, this.mName + "-Thread-" + this.mCounter);
        this.mCounter = this.mCounter + 1;
        return thread;
    }
}
