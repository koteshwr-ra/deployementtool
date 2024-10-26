package com.tencent.smtt.sdk.ui.dialog.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.StateListDrawable;
import android.widget.Button;
import androidx.core.view.ViewCompat;

public class b extends Button {
    private int a;
    private int b;
    private float c;
    private float d;
    private float e;
    private float f;
    private c g;
    private c h;
    private c i;

    public b(Context context, float f2, float f3, float f4, float f5, int i2) {
        super(context);
        this.g = null;
        this.h = null;
        this.i = null;
        this.c = f2;
        this.d = f3;
        this.e = f4;
        this.f = f5;
        this.a = i2;
        this.b = Color.parseColor("#D0D0D0");
        a();
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public b(android.content.Context r8, int r9, int r10) {
        /*
            r7 = this;
            float r5 = (float) r9
            r0 = r7
            r1 = r8
            r2 = r5
            r3 = r5
            r4 = r5
            r6 = r10
            r0.<init>(r1, r2, r3, r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.ui.dialog.widget.b.<init>(android.content.Context, int, int):void");
    }

    public void a() {
        c cVar = new c(this.a, this.c, this.d, this.e, this.f);
        this.g = cVar;
        cVar.a(getWidth(), getHeight());
        c cVar2 = new c(1342177280 | (this.a & ViewCompat.MEASURED_SIZE_MASK), this.c, this.d, this.e, this.f);
        this.h = cVar2;
        cVar2.a(getWidth(), getHeight());
        c cVar3 = new c(this.b, this.c, this.d, this.e, this.f);
        this.i = cVar3;
        cVar3.a(getWidth(), getHeight());
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842910, -16842919}, this.g);
        stateListDrawable.addState(new int[]{16842910, 16842919}, this.h);
        stateListDrawable.addState(new int[]{-16842910}, this.i);
        setBackgroundDrawable(stateListDrawable);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        c cVar = this.g;
        if (cVar != null) {
            cVar.a(i4 - i2, i5 - i3);
        }
        c cVar2 = this.h;
        if (cVar2 != null) {
            cVar2.a(i4 - i2, i5 - i3);
        }
        c cVar3 = this.i;
        if (cVar3 != null) {
            cVar3.a(i4 - i2, i5 - i3);
        }
    }
}
