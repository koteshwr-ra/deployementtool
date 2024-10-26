package com.kingja.loadsir.core;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import com.kingja.loadsir.LoadSirUtil;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.callback.SuccessCallback;
import java.util.HashMap;
import java.util.Map;

public class LoadLayout extends FrameLayout {
    private static final int CALLBACK_CUSTOM_INDEX = 1;
    private Map<Class<? extends Callback>, Callback> callbacks;
    private Context context;
    private Class<? extends Callback> curCallback;
    private Callback.OnReloadListener onReloadListener;
    private Class<? extends Callback> preCallback;

    public LoadLayout(Context context2) {
        super(context2);
        this.callbacks = new HashMap();
    }

    public LoadLayout(Context context2, Callback.OnReloadListener onReloadListener2) {
        this(context2);
        this.context = context2;
        this.onReloadListener = onReloadListener2;
    }

    public void setupSuccessLayout(Callback callback) {
        addCallback(callback);
        View rootView = callback.getRootView();
        rootView.setVisibility(8);
        addView(rootView);
        this.curCallback = SuccessCallback.class;
    }

    public void setupCallback(Callback callback) {
        Callback copy = callback.copy();
        copy.setCallback((View) null, this.context, this.onReloadListener);
        addCallback(copy);
    }

    public void addCallback(Callback callback) {
        if (!this.callbacks.containsKey(callback.getClass())) {
            this.callbacks.put(callback.getClass(), callback);
        }
    }

    public void showCallback(Class<? extends Callback> cls) {
        checkCallbackExist(cls);
        if (LoadSirUtil.isMainThread()) {
            showCallbackView(cls);
        } else {
            postToMainThread(cls);
        }
    }

    public Class<? extends Callback> getCurrentCallback() {
        return this.curCallback;
    }

    private void postToMainThread(final Class<? extends Callback> cls) {
        post(new Runnable() {
            public void run() {
                LoadLayout.this.showCallbackView(cls);
            }
        });
    }

    /* access modifiers changed from: private */
    public void showCallbackView(Class<? extends Callback> cls) {
        Class<? extends Callback> cls2 = this.preCallback;
        if (cls2 != null) {
            if (cls2 != cls) {
                this.callbacks.get(cls2).onDetach();
            } else {
                return;
            }
        }
        if (getChildCount() > 1) {
            removeViewAt(1);
        }
        for (Class<SuccessCallback> next : this.callbacks.keySet()) {
            if (next == cls) {
                SuccessCallback successCallback = (SuccessCallback) this.callbacks.get(SuccessCallback.class);
                if (next == SuccessCallback.class) {
                    successCallback.show();
                } else {
                    successCallback.showWithCallback(this.callbacks.get(next).getSuccessVisible());
                    View rootView = this.callbacks.get(next).getRootView();
                    addView(rootView);
                    this.callbacks.get(next).onAttach(this.context, rootView);
                }
                this.preCallback = cls;
            }
        }
        this.curCallback = cls;
    }

    public void setCallBack(Class<? extends Callback> cls, Transport transport) {
        if (transport != null) {
            checkCallbackExist(cls);
            transport.order(this.context, this.callbacks.get(cls).obtainRootView());
        }
    }

    private void checkCallbackExist(Class<? extends Callback> cls) {
        if (!this.callbacks.containsKey(cls)) {
            throw new IllegalArgumentException(String.format("The Callback (%s) is nonexistent.", new Object[]{cls.getSimpleName()}));
        }
    }
}
