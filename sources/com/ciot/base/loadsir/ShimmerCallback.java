package com.ciot.base.loadsir;

import android.content.Context;
import android.view.View;
import com.drz.base.R;
import com.kingja.loadsir.callback.Callback;

public class ShimmerCallback extends Callback {
    /* access modifiers changed from: protected */
    public boolean onReloadEvent(Context context, View view) {
        return true;
    }

    /* access modifiers changed from: protected */
    public int onCreateView() {
        return R.layout.base_layout_placeholder;
    }
}
