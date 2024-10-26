package com.ciot.networklib.executor;

import com.ciot.networklib.executor.config.ThreadPoolConfig;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;
import java.util.concurrent.Executor;

public class ExecutorManager {
    private static Executor normalExecutor;
    private static HashMap<String, Executor> singleExecutorMap = new HashMap<>();

    public static synchronized void init(ThreadPoolConfig threadPoolConfig) {
        synchronized (ExecutorManager.class) {
            if (threadPoolConfig == null) {
                threadPoolConfig = ThreadPoolConfig.getDefaultThreadPoolConfig();
            }
            normalExecutor = ExecutorFactory.newFixedThreadPool(threadPoolConfig);
            clear();
        }
    }

    public static synchronized Scheduler getRxJavaExecutor(int i, String str) {
        Scheduler from;
        synchronized (ExecutorManager.class) {
            from = Schedulers.from(getExecutor(i, str));
        }
        return from;
    }

    public static synchronized Executor getExecutor(int i, String str) {
        Executor executor;
        synchronized (ExecutorManager.class) {
            if (i == 0) {
                executor = getSingleExecutorByKey(str);
            } else if (i == 1) {
                executor = getNormalExecutor();
            } else if (i != 2) {
                executor = getNormalExecutor();
            } else {
                executor = getSingleDiscardExecutor(str);
            }
        }
        return executor;
    }

    public static synchronized Executor getSingleExecutorByKey(String str) {
        Executor executor;
        synchronized (ExecutorManager.class) {
            executor = singleExecutorMap.get(str);
            if (executor == null) {
                executor = ExecutorFactory.newSingleThreadExecutor();
                singleExecutorMap.put(str, executor);
            }
        }
        return executor;
    }

    public static synchronized Executor getNormalExecutor() {
        Executor executor;
        synchronized (ExecutorManager.class) {
            if (normalExecutor == null) {
                init((ThreadPoolConfig) null);
            }
            executor = normalExecutor;
        }
        return executor;
    }

    public static synchronized Executor getSingleDiscardExecutor(String str) {
        Executor executor;
        synchronized (ExecutorManager.class) {
            executor = singleExecutorMap.get(str);
            if (executor == null) {
                executor = ExecutorFactory.newDiscardOldThreadPool(ThreadPoolConfig.build().setCorePoolSize(1).setCapacity(1).setMaximumPoolSize(1));
                singleExecutorMap.put(str, executor);
            }
        }
        return executor;
    }

    public static void clear() {
        singleExecutorMap.clear();
    }
}
