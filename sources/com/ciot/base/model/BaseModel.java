package com.ciot.base.model;

import java.lang.ref.WeakReference;
import java.util.Iterator;

public abstract class BaseModel<T> extends SuperBaseModel<T> {
    public void loadSuccess(T t) {
        synchronized (this) {
            this.mUiHandler.postDelayed(new Runnable(t) {
                public final /* synthetic */ Object f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    BaseModel.this.lambda$loadSuccess$0$BaseModel(this.f$1);
                }
            }, 0);
        }
    }

    public /* synthetic */ void lambda$loadSuccess$0$BaseModel(Object obj) {
        IModelListener iModelListener;
        Iterator it = this.mWeakReferenceDeque.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            if ((weakReference.get() instanceof IModelListener) && (iModelListener = (IModelListener) weakReference.get()) != null) {
                iModelListener.onLoadFinish(this, obj);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void loadFail(String str) {
        synchronized (this) {
            this.mUiHandler.postDelayed(new Runnable(str) {
                public final /* synthetic */ String f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    BaseModel.this.lambda$loadFail$1$BaseModel(this.f$1);
                }
            }, 0);
        }
    }

    public /* synthetic */ void lambda$loadFail$1$BaseModel(String str) {
        IModelListener iModelListener;
        Iterator it = this.mWeakReferenceDeque.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            if ((weakReference.get() instanceof IModelListener) && (iModelListener = (IModelListener) weakReference.get()) != null) {
                iModelListener.onLoadFail(this, str);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void notifyCacheData(T t) {
        loadSuccess(t);
    }
}
