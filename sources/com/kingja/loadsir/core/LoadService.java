package com.kingja.loadsir.core;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.callback.SuccessCallback;
import com.kingja.loadsir.core.LoadSir;
import java.util.List;

public class LoadService<T> {
    private Convertor<T> convertor;
    private LoadLayout loadLayout;

    LoadService(Convertor<T> convertor2, TargetContext targetContext, Callback.OnReloadListener onReloadListener, LoadSir.Builder builder) {
        this.convertor = convertor2;
        Context context = targetContext.getContext();
        View oldContent = targetContext.getOldContent();
        ViewGroup.LayoutParams layoutParams = oldContent.getLayoutParams();
        LoadLayout loadLayout2 = new LoadLayout(context, onReloadListener);
        this.loadLayout = loadLayout2;
        loadLayout2.setupSuccessLayout(new SuccessCallback(oldContent, context, onReloadListener));
        if (targetContext.getParentView() != null) {
            targetContext.getParentView().addView(this.loadLayout, targetContext.getChildIndex(), layoutParams);
        }
        initCallback(builder);
    }

    private void initCallback(LoadSir.Builder builder) {
        List<Callback> callbacks = builder.getCallbacks();
        Class<? extends Callback> defaultCallback = builder.getDefaultCallback();
        if (callbacks != null && callbacks.size() > 0) {
            for (Callback callback : callbacks) {
                this.loadLayout.setupCallback(callback);
            }
        }
        if (defaultCallback != null) {
            this.loadLayout.showCallback(defaultCallback);
        }
    }

    public void showSuccess() {
        this.loadLayout.showCallback(SuccessCallback.class);
    }

    public void showCallback(Class<? extends Callback> cls) {
        this.loadLayout.showCallback(cls);
    }

    public void showWithConvertor(T t) {
        Convertor<T> convertor2 = this.convertor;
        if (convertor2 != null) {
            this.loadLayout.showCallback(convertor2.map(t));
            return;
        }
        throw new IllegalArgumentException("You haven't set the Convertor.");
    }

    public LoadLayout getLoadLayout() {
        return this.loadLayout;
    }

    public Class<? extends Callback> getCurrentCallback() {
        return this.loadLayout.getCurrentCallback();
    }

    public LinearLayout getTitleLoadLayout(Context context, ViewGroup viewGroup, View view) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        linearLayout.setLayoutParams(layoutParams);
        viewGroup.removeView(view);
        linearLayout.addView(view);
        linearLayout.addView(this.loadLayout, layoutParams);
        return linearLayout;
    }

    public LoadService<T> setCallBack(Class<? extends Callback> cls, Transport transport) {
        this.loadLayout.setCallBack(cls, transport);
        return this;
    }
}
