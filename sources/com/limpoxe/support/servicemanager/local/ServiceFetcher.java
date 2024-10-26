package com.limpoxe.support.servicemanager.local;

public abstract class ServiceFetcher {
    private Object mCachedInstance;
    String mGroupId;
    int mServiceId;

    public abstract Object createService(int i);

    public final Object getService() {
        synchronized (this) {
            Object obj = this.mCachedInstance;
            if (obj != null) {
                return obj;
            }
            Object createService = createService(this.mServiceId);
            this.mCachedInstance = createService;
            return createService;
        }
    }
}
