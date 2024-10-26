package com.kingja.loadsir.core;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public class TargetContext {
    private int childIndex;
    private Context context;
    private View oldContent;
    private ViewGroup parentView;

    public TargetContext(Context context2, ViewGroup viewGroup, View view, int i) {
        this.context = context2;
        this.parentView = viewGroup;
        this.oldContent = view;
        this.childIndex = i;
    }

    public Context getContext() {
        return this.context;
    }

    /* access modifiers changed from: package-private */
    public View getOldContent() {
        return this.oldContent;
    }

    /* access modifiers changed from: package-private */
    public int getChildIndex() {
        return this.childIndex;
    }

    /* access modifiers changed from: package-private */
    public ViewGroup getParentView() {
        return this.parentView;
    }
}
