package com.ciot.networklib.executor;

import com.ciot.networklib.executor.config.ThreadPoolConfig;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorFactory {
    private ExecutorFactory() {
    }

    public static Executor newFixedThreadPool(ThreadPoolConfig threadPoolConfig) {
        return new ThreadPoolExecutor(threadPoolConfig.getCorePoolSize(), threadPoolConfig.getMaximumPoolSize(), (long) threadPoolConfig.getKeepAliveTime(), TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public static Executor newDiscardOldThreadPool(ThreadPoolConfig threadPoolConfig) {
        return new ThreadPoolExecutor(threadPoolConfig.getCorePoolSize(), threadPoolConfig.getMaximumPoolSize(), (long) threadPoolConfig.getKeepAliveTime(), TimeUnit.MILLISECONDS, new ArrayBlockingQueue(threadPoolConfig.getCapacity()), new ThreadPoolExecutor.DiscardOldestPolicy());
    }

    public static Executor newSingleThreadExecutor() {
        return Executors.newSingleThreadExecutor();
    }
}
