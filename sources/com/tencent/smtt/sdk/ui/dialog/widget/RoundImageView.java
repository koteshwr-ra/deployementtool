package com.tencent.smtt.sdk.ui.dialog.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.tencent.smtt.sdk.ui.dialog.c;
import java.lang.ref.WeakReference;

public class RoundImageView extends ImageView {
    private Paint a;
    private Xfermode b;
    private Bitmap c;
    private float[] d;
    private RectF e;
    private int f;
    private WeakReference<Bitmap> g;
    private float h;
    private Path i;

    public RoundImageView(Context context) {
        this(context, (AttributeSet) null);
    }

    public RoundImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        this.f = Color.parseColor("#eaeaea");
        Paint paint = new Paint();
        this.a = paint;
        paint.setAntiAlias(true);
        this.i = new Path();
        this.d = new float[8];
        this.e = new RectF();
        this.h = (float) c.a(context, 16.46f);
        int i2 = 0;
        while (true) {
            float[] fArr = this.d;
            if (i2 < fArr.length) {
                fArr[i2] = this.h;
                i2++;
            } else {
                return;
            }
        }
    }

    private Bitmap a() {
        Bitmap bitmap = null;
        try {
            bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint(1);
            paint.setColor(-16777216);
            canvas.drawRoundRect(new RectF(0.0f, 0.0f, (float) getWidth(), (float) getHeight()), this.h, this.h, paint);
            return bitmap;
        } catch (Throwable th) {
            th.printStackTrace();
            return bitmap;
        }
    }

    private void a(int i2, int i3) {
        this.i.reset();
        this.a.setStrokeWidth((float) i2);
        this.a.setColor(i3);
        this.a.setStyle(Paint.Style.STROKE);
    }

    private void a(Canvas canvas, int i2, int i3, RectF rectF, float[] fArr) {
        a(i2, i3);
        this.i.addRoundRect(rectF, fArr, Path.Direction.CCW);
        canvas.drawPath(this.i, this.a);
    }

    public void invalidate() {
        this.g = null;
        Bitmap bitmap = this.c;
        if (bitmap != null) {
            bitmap.recycle();
            this.c = null;
        }
        super.invalidate();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        WeakReference<Bitmap> weakReference = this.g;
        Bitmap bitmap = weakReference == null ? null : (Bitmap) weakReference.get();
        if (bitmap == null || bitmap.isRecycled()) {
            Drawable drawable = getDrawable();
            if (drawable != null) {
                int intrinsicWidth = drawable.getIntrinsicWidth();
                int intrinsicHeight = drawable.getIntrinsicHeight();
                Bitmap createBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas2 = new Canvas(createBitmap);
                float f2 = (float) intrinsicWidth;
                float f3 = (float) intrinsicHeight;
                float max = Math.max((((float) getWidth()) * 1.0f) / f2, (((float) getHeight()) * 1.0f) / f3);
                drawable.setBounds(0, 0, (int) (f2 * max), (int) (max * f3));
                drawable.draw(canvas2);
                Bitmap bitmap2 = this.c;
                if (bitmap2 == null || bitmap2.isRecycled()) {
                    this.c = a();
                }
                this.a.reset();
                this.a.setFilterBitmap(false);
                this.a.setXfermode(this.b);
                Bitmap bitmap3 = this.c;
                if (bitmap3 != null) {
                    canvas2.drawBitmap(bitmap3, 0.0f, 0.0f, this.a);
                }
                this.a.setXfermode((Xfermode) null);
                canvas.drawBitmap(createBitmap, 0.0f, 0.0f, (Paint) null);
                this.g = new WeakReference<>(createBitmap);
            }
        } else {
            this.a.setXfermode((Xfermode) null);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, this.a);
        }
        a(canvas, 1, this.f, this.e, this.d);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        this.e.set(0.5f, 0.5f, ((float) i2) - 0.5f, ((float) i3) - 0.5f);
    }
}
