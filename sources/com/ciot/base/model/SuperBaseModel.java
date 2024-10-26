package com.ciot.base.model;

import android.os.Handler;
import android.os.Looper;
import com.ciot.base.util.GsonUtils;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedDeque;

public abstract class SuperBaseModel<T> {
    protected CompositeDisposable compositeDisposable;
    protected ReferenceQueue<IBaseModelListener> mReferenceQueue = new ReferenceQueue<>();
    protected Handler mUiHandler = new Handler(Looper.getMainLooper());
    protected ConcurrentLinkedDeque<WeakReference<IBaseModelListener>> mWeakReferenceDeque = new ConcurrentLinkedDeque<>();

    /* access modifiers changed from: protected */
    public String getApkCache() {
        return null;
    }

    /* access modifiers changed from: protected */
    public Type getTclass() {
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean isNeedToUpData() {
        return true;
    }

    /* access modifiers changed from: protected */
    public abstract void load();

    /* access modifiers changed from: protected */
    public abstract void notifyCacheData(T t);

    public void register(IBaseModelListener iBaseModelListener) {
        if (iBaseModelListener != null) {
            synchronized (this) {
                while (true) {
                    Reference<? extends IBaseModelListener> poll = this.mReferenceQueue.poll();
                    if (poll != null) {
                        this.mWeakReferenceDeque.remove(poll);
                    } else {
                        Iterator<WeakReference<IBaseModelListener>> it = this.mWeakReferenceDeque.iterator();
                        while (it.hasNext()) {
                            if (((IBaseModelListener) it.next().get()) == iBaseModelListener) {
                                return;
                            }
                        }
                        this.mWeakReferenceDeque.add(new WeakReference(iBaseModelListener, this.mReferenceQueue));
                        return;
                    }
                }
            }
        }
    }

    public void unRegister(IBaseModelListener iBaseModelListener) {
        if (iBaseModelListener != null) {
            synchronized (this) {
                Iterator<WeakReference<IBaseModelListener>> it = this.mWeakReferenceDeque.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    WeakReference next = it.next();
                    if (((IBaseModelListener) next.get()) == iBaseModelListener) {
                        this.mWeakReferenceDeque.remove(next);
                        break;
                    }
                }
            }
        }
    }

    public void getCacheDataAndLoad() {
        if (getApkCache() != null) {
            notifyCacheData(GsonUtils.fromLocalJson(getApkCache(), getTclass()));
            if (isNeedToUpData()) {
                load();
                return;
            }
            return;
        }
        load();
    }

    public void addDisposable(Disposable disposable) {
        if (disposable != null) {
            if (this.compositeDisposable == null) {
                this.compositeDisposable = new CompositeDisposable();
            }
            this.compositeDisposable.add(disposable);
        }
    }

    public void cancel() {
        CompositeDisposable compositeDisposable2 = this.compositeDisposable;
        if (compositeDisposable2 != null && !compositeDisposable2.isDisposed()) {
            this.compositeDisposable.isDisposed();
        }
    }
}
