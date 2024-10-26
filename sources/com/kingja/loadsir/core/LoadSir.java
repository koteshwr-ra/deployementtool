package com.kingja.loadsir.core;

import com.kingja.loadsir.LoadSirUtil;
import com.kingja.loadsir.callback.Callback;
import java.util.ArrayList;
import java.util.List;

public class LoadSir {
    private static volatile LoadSir loadSir;
    private Builder builder;

    public static LoadSir getDefault() {
        if (loadSir == null) {
            synchronized (LoadSir.class) {
                if (loadSir == null) {
                    loadSir = new LoadSir();
                }
            }
        }
        return loadSir;
    }

    private LoadSir() {
        this.builder = new Builder();
    }

    /* access modifiers changed from: private */
    public void setBuilder(Builder builder2) {
        this.builder = builder2;
    }

    private LoadSir(Builder builder2) {
        this.builder = builder2;
    }

    public LoadService register(Object obj) {
        return register(obj, (Callback.OnReloadListener) null, (Convertor) null);
    }

    public LoadService register(Object obj, Callback.OnReloadListener onReloadListener) {
        return register(obj, onReloadListener, (Convertor) null);
    }

    public <T> LoadService register(Object obj, Callback.OnReloadListener onReloadListener, Convertor<T> convertor) {
        return new LoadService(convertor, LoadSirUtil.getTargetContext(obj), onReloadListener, this.builder);
    }

    public static Builder beginBuilder() {
        return new Builder();
    }

    public static class Builder {
        private List<Callback> callbacks = new ArrayList();
        private Class<? extends Callback> defaultCallback;

        public Builder addCallback(Callback callback) {
            this.callbacks.add(callback);
            return this;
        }

        public Builder setDefaultCallback(Class<? extends Callback> cls) {
            this.defaultCallback = cls;
            return this;
        }

        /* access modifiers changed from: package-private */
        public List<Callback> getCallbacks() {
            return this.callbacks;
        }

        /* access modifiers changed from: package-private */
        public Class<? extends Callback> getDefaultCallback() {
            return this.defaultCallback;
        }

        public void commit() {
            LoadSir.getDefault().setBuilder(this);
        }

        public LoadSir build() {
            return new LoadSir(this);
        }
    }
}
