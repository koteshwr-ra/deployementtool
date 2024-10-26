package com.ciot.base.util;

import android.util.Log;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolUtils {
    private static final String TAG = ThreadPoolUtils.class.getSimpleName();
    private static ThreadPoolUtils mInstance = new ThreadPoolUtils();
    private ThreadPoolExecutor mExecutor;

    public static ThreadPoolUtils getInstance() {
        return mInstance;
    }

    private ThreadPoolUtils() {
        TimeUnit timeUnit = TimeUnit.HOURS;
        int availableProcessors = (Runtime.getRuntime().availableProcessors() * 2) + 1;
        String str = TAG;
        Log.d(str, "corePoolSize" + availableProcessors);
        this.mExecutor = new ThreadPoolExecutor(availableProcessors, availableProcessors, 1, timeUnit, new LinkedBlockingQueue(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
    }

    public void execute(Runnable runnable) {
        if (runnable != null) {
            this.mExecutor.execute(runnable);
        }
    }

    public void remove(Runnable runnable) {
        if (runnable != null) {
            this.mExecutor.remove(runnable);
        }
    }
}
