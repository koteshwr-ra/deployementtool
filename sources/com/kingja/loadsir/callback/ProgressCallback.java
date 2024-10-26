package com.kingja.loadsir.callback;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProgressCallback extends Callback {
    private String subTitle;
    private int subTitleStyleRes;
    private String title;
    private int titleStyleRes;

    /* access modifiers changed from: protected */
    public int onCreateView() {
        return 0;
    }

    private ProgressCallback(Builder builder) {
        this.subTitleStyleRes = -1;
        this.titleStyleRes = -1;
        this.title = builder.title;
        this.subTitle = builder.subTitle;
        this.subTitleStyleRes = builder.subTitleStyleRes;
        this.titleStyleRes = builder.titleStyleRes;
        setSuccessVisible(builder.aboveable);
    }

    /* access modifiers changed from: protected */
    public View onBuildView(Context context) {
        return new LinearLayout(context);
    }

    /* access modifiers changed from: protected */
    public void onViewCreate(Context context, View view) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        LinearLayout linearLayout = (LinearLayout) view;
        linearLayout.setOrientation(1);
        linearLayout.setGravity(17);
        linearLayout.addView(new ProgressBar(context), layoutParams);
        if (!TextUtils.isEmpty(this.title)) {
            TextView textView = new TextView(context);
            textView.setText(this.title);
            int i = this.titleStyleRes;
            if (i == -1) {
                textView.setTextAppearance(context, 16973890);
            } else {
                textView.setTextAppearance(context, i);
            }
            linearLayout.addView(textView, layoutParams);
        }
        if (!TextUtils.isEmpty(this.subTitle)) {
            TextView textView2 = new TextView(context);
            textView2.setText(this.subTitle);
            int i2 = this.subTitleStyleRes;
            if (i2 == -1) {
                textView2.setTextAppearance(context, 16973892);
            } else {
                textView2.setTextAppearance(context, i2);
            }
            linearLayout.addView(textView2, layoutParams);
        }
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public boolean aboveable;
        /* access modifiers changed from: private */
        public String subTitle;
        /* access modifiers changed from: private */
        public int subTitleStyleRes = -1;
        /* access modifiers changed from: private */
        public String title;
        /* access modifiers changed from: private */
        public int titleStyleRes = -1;

        public Builder setTitle(String str) {
            return setTitle(str, -1);
        }

        public Builder setTitle(String str, int i) {
            this.title = str;
            this.titleStyleRes = i;
            return this;
        }

        public Builder setSubTitle(String str) {
            return setSubTitle(str, -1);
        }

        public Builder setSubTitle(String str, int i) {
            this.subTitle = str;
            this.subTitleStyleRes = i;
            return this;
        }

        public Builder setAboveSuccess(boolean z) {
            this.aboveable = z;
            return this;
        }

        public ProgressCallback build() {
            return new ProgressCallback(this);
        }
    }
}
