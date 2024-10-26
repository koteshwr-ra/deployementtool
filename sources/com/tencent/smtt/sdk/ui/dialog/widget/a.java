package com.tencent.smtt.sdk.ui.dialog.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class a extends View {
    private int a;
    private int b;
    private Paint c;
    private Paint d;
    private Path e;
    private Path f;
    private RectF g;
    private float[] h;
    private float i;
    private float j;

    public a(Context context, float f2, float f3, float f4) {
        super(context, (AttributeSet) null, 0);
        a(context, f2, f3, f4);
    }

    private int a(int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == 1073741824) {
            return size;
        }
        if (mode == Integer.MIN_VALUE) {
            return Math.min(100, size);
        }
        return 100;
    }

    public static int a(Context context, float f2) {
        return (int) ((f2 * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    private void a(Context context, float f2, float f3, float f4) {
        this.i = f3;
        this.j = f4;
        int parseColor = Color.parseColor("#989DB4");
        this.c = new Paint();
        Paint paint = new Paint();
        this.d = paint;
        paint.setColor(-1);
        this.d.setStyle(Paint.Style.FILL);
        this.d.setAntiAlias(true);
        this.c.setColor(parseColor);
        this.c.setStyle(Paint.Style.STROKE);
        this.c.setAntiAlias(true);
        this.c.setStrokeWidth((float) a(context, 6.0f));
        this.c.setStrokeJoin(Paint.Join.ROUND);
        this.g = new RectF();
        this.h = new float[]{f2, f2, f2, f2, 0.0f, 0.0f, 0.0f, 0.0f};
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(0.0f, 0.0f);
        canvas.rotate(0.0f);
        if (this.f == null) {
            this.f = new Path();
        }
        this.f.reset();
        this.f.addRoundRect(this.g, this.h, Path.Direction.CCW);
        this.f.close();
        canvas.drawPath(this.f, this.d);
        canvas.translate(((float) this.a) / 2.0f, (((float) this.b) / 2.0f) + (this.j / 2.0f));
        if (this.e == null) {
            this.e = new Path();
        }
        this.e.reset();
        this.e.moveTo(0.0f, 0.0f);
        this.e.lineTo((-this.i) / 2.0f, (-this.j) / 2.0f);
        this.e.close();
        canvas.drawPath(this.e, this.c);
        this.e.reset();
        this.e.moveTo(0.0f, 0.0f);
        this.e.lineTo(this.i / 2.0f, (-this.j) / 2.0f);
        this.e.close();
        canvas.drawPath(this.e, this.c);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        setMeasuredDimension(a(i2), a(i3));
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        this.a = i2;
        this.b = i3;
        this.g.left = 0.0f;
        this.g.top = 0.0f;
        this.g.right = (float) this.a;
        this.g.bottom = (float) this.b;
    }
}
