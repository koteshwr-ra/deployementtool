package com.ciot.base.util;

public class ThreadPoolProxyFactory {
    static ThreadPoolProxy mDownloadThreadPoolProxy;
    static ThreadPoolProxy mNormalThreadPoolProxy;

    public static ThreadPoolProxy getNormalThreadPoolProxy() {
        if (mNormalThreadPoolProxy == null) {
            synchronized (ThreadPoolProxyFactory.class) {
                if (mNormalThreadPoolProxy == null) {
                    mNormalThreadPoolProxy = new ThreadPoolProxy(5, 5);
                }
            }
        }
        return mNormalThreadPoolProxy;
    }

    public static ThreadPoolProxy getDownloadThreadPoolProxy() {
        if (mDownloadThreadPoolProxy == null) {
            synchronized (ThreadPoolProxyFactory.class) {
                if (mDownloadThreadPoolProxy == null) {
                    mDownloadThreadPoolProxy = new ThreadPoolProxy(3, 3);
                }
            }
        }
        return mDownloadThreadPoolProxy;
    }
}
