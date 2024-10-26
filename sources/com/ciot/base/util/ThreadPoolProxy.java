package com.ciot.base.util;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolProxy {
    private int mCorePoolSize;
    ThreadPoolExecutor mExecutor;
    private int mMaximumPoolSize;

    public ThreadPoolProxy(int i, int i2) {
        this.mCorePoolSize = i;
        this.mMaximumPoolSize = i2;
    }

    public void initThreadPoolExecutor() {
        ThreadPoolExecutor threadPoolExecutor = this.mExecutor;
        if (threadPoolExecutor == null || threadPoolExecutor.isShutdown() || this.mExecutor.isTerminated()) {
            synchronized (ThreadPoolProxy.class) {
                if (this.mExecutor == null || this.mExecutor.isShutdown() || this.mExecutor.isTerminated()) {
                    TimeUnit timeUnit = TimeUnit.MILLISECONDS;
                    ThreadFactory defaultThreadFactory = Executors.defaultThreadFactory();
                    this.mExecutor = new ThreadPoolExecutor(this.mCorePoolSize, this.mMaximumPoolSize, 3000, timeUnit, new LinkedBlockingQueue(), defaultThreadFactory, new ThreadPoolExecutor.DiscardPolicy());
                }
            }
        }
    }

    public void execute(Runnable runnable) {
        initThreadPoolExecutor();
        this.mExecutor.execute(runnable);
    }

    public <T> Future<T> submit(Runnable runnable) {
        initThreadPoolExecutor();
        return this.mExecutor.submit(runnable);
    }

    public <T> Future<T> submit(Callable<T> callable) {
        initThreadPoolExecutor();
        return this.mExecutor.submit(callable);
    }

    public void remove(Runnable runnable) {
        initThreadPoolExecutor();
        this.mExecutor.remove(runnable);
    }

    public void shutdownNow() {
        ThreadPoolExecutor threadPoolExecutor = this.mExecutor;
        if (threadPoolExecutor != null && !threadPoolExecutor.isShutdown()) {
            this.mExecutor.shutdownNow();
        }
    }

    public void shutdown() {
        ThreadPoolExecutor threadPoolExecutor = this.mExecutor;
        if (threadPoolExecutor != null && !threadPoolExecutor.isShutdown()) {
            this.mExecutor.shutdown();
        }
    }
}
