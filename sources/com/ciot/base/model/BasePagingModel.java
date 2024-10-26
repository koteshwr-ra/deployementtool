package com.ciot.base.model;

import java.lang.ref.WeakReference;
import java.util.Iterator;

public abstract class BasePagingModel<T> extends SuperBaseModel<T> {
    protected boolean hasNextPage = false;
    protected boolean isRefresh = true;
    protected String nextPageUrl = "";

    public void loadSuccess(T t, boolean z, boolean z2) {
        synchronized (this) {
            this.mUiHandler.postDelayed(new Runnable(t, z, z2) {
                public final /* synthetic */ Object f$1;
                public final /* synthetic */ boolean f$2;
                public final /* synthetic */ boolean f$3;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                }

                public final void run() {
                    BasePagingModel.this.lambda$loadSuccess$0$BasePagingModel(this.f$1, this.f$2, this.f$3);
                }
            }, 0);
        }
    }

    public /* synthetic */ void lambda$loadSuccess$0$BasePagingModel(Object obj, boolean z, boolean z2) {
        IPagingModelListener iPagingModelListener;
        Iterator it = this.mWeakReferenceDeque.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            if ((weakReference.get() instanceof IPagingModelListener) && (iPagingModelListener = (IPagingModelListener) weakReference.get()) != null) {
                iPagingModelListener.onLoadFinish(this, obj, z, z2);
            }
        }
    }

    public void loadFail(String str, boolean z) {
        synchronized (this) {
            this.mUiHandler.postDelayed(new Runnable(str) {
                public final /* synthetic */ String f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    BasePagingModel.this.lambda$loadFail$1$BasePagingModel(this.f$1);
                }
            }, 0);
        }
    }

    public /* synthetic */ void lambda$loadFail$1$BasePagingModel(String str) {
        IPagingModelListener iPagingModelListener;
        Iterator it = this.mWeakReferenceDeque.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            if ((weakReference.get() instanceof IPagingModelListener) && (iPagingModelListener = (IPagingModelListener) weakReference.get()) != null) {
                iPagingModelListener.onLoadFail(this, str, this.isRefresh);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void notifyCacheData(T t) {
        loadSuccess(t, false, true);
    }
}
