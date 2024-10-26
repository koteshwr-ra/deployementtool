package com.ciot.networklib.executor.config;

public class ThreadPoolConfig {
    private int capacity = 100;
    private int corePoolSize = 3;
    private int keepAliveTime = 60;
    private int maximumPoolSize = 3;

    private ThreadPoolConfig() {
    }

    public static ThreadPoolConfig build() {
        return new ThreadPoolConfig();
    }

    public static ThreadPoolConfig getDefaultThreadPoolConfig() {
        return build().setCorePoolSize(3).setMaximumPoolSize(3).setCapacity(100).setKeepAliveTime(60);
    }

    public int getCorePoolSize() {
        return this.corePoolSize;
    }

    public ThreadPoolConfig setCorePoolSize(int i) {
        this.corePoolSize = i;
        return this;
    }

    public int getMaximumPoolSize() {
        return this.maximumPoolSize;
    }

    public ThreadPoolConfig setMaximumPoolSize(int i) {
        this.maximumPoolSize = i;
        return this;
    }

    public int getKeepAliveTime() {
        return this.keepAliveTime;
    }

    public ThreadPoolConfig setKeepAliveTime(int i) {
        this.keepAliveTime = i;
        return this;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public ThreadPoolConfig setCapacity(int i) {
        this.capacity = i;
        return this;
    }
}
