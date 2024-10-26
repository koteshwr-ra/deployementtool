package com.ciot.base.viewmodel;

import androidx.lifecycle.ViewModel;
import com.ciot.base.model.SuperBaseModel;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public abstract class MvmBaseViewModel<V, M extends SuperBaseModel> extends ViewModel implements IMvvmBaseViewModel<V> {
    private Reference<V> mUiRef;
    protected M model;

    /* access modifiers changed from: protected */
    public abstract void initModel();

    /* access modifiers changed from: protected */
    public void loadData() {
    }

    public void attachUi(V v) {
        this.mUiRef = new WeakReference(v);
    }

    public V getPageView() {
        Reference<V> reference = this.mUiRef;
        if (reference == null || reference.get() == null) {
            return null;
        }
        return this.mUiRef.get();
    }

    public boolean isUiAttach() {
        Reference<V> reference = this.mUiRef;
        return (reference == null || reference.get() == null) ? false : true;
    }

    public void detachUi() {
        Reference<V> reference = this.mUiRef;
        if (reference != null) {
            reference.clear();
            this.mUiRef = null;
        }
        M m = this.model;
        if (m != null) {
            m.cancel();
        }
    }
}
