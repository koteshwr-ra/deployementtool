package com.tencent.smtt.sdk.ui.dialog.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

public class c extends Drawable {
    private float a;
    private float b;
    private float c;
    private float d;
    private Path e;
    private Paint f;
    private RectF g = new RectF();

    public c(int i, float f2, float f3, float f4, float f5) {
        this.a = f2;
        this.b = f3;
        this.d = f4;
        this.c = f5;
        Paint paint = new Paint();
        this.f = paint;
        paint.setStyle(Paint.Style.FILL);
        this.f.setAntiAlias(true);
        this.f.setColor(i);
    }

    public void a(int i, int i2) {
        this.g.left = 0.0f;
        this.g.top = 0.0f;
        this.g.right = (float) i;
        this.g.bottom = (float) i2;
    }

    public void draw(Canvas canvas) {
        if (this.e == null) {
            this.e = new Path();
        }
        this.e.reset();
        Path path = this.e;
        RectF rectF = this.g;
        float f2 = this.a;
        float f3 = this.b;
        float f4 = this.d;
        float f5 = this.c;
        path.addRoundRect(rectF, new float[]{f2, f2, f3, f3, f4, f4, f5, f5}, Path.Direction.CCW);
        this.e.close();
        canvas.drawPath(this.e, this.f);
    }

    public int getOpacity() {
        return -3;
    }

    public void setAlpha(int i) {
        this.f.setAlpha(i);
        invalidateSelf();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.f.setColorFilter(colorFilter);
        invalidateSelf();
    }
}
