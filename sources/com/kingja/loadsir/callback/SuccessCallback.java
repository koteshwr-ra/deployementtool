package com.kingja.loadsir.callback;

import android.content.Context;
import android.view.View;
import com.kingja.loadsir.callback.Callback;

public class SuccessCallback extends Callback {
    /* access modifiers changed from: protected */
    public int onCreateView() {
        return 0;
    }

    public SuccessCallback(View view, Context context, Callback.OnReloadListener onReloadListener) {
        super(view, context, onReloadListener);
    }

    public void hide() {
        obtainRootView().setVisibility(4);
    }

    public void show() {
        obtainRootView().setVisibility(0);
    }

    public void showWithCallback(boolean z) {
        obtainRootView().setVisibility(z ? 0 : 4);
    }
}
