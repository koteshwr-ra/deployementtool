package mc.csst.com.selfchassis.utils.view.bubble;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.shapes.Shape;

class ChatShape extends Shape {
    private String arrowDirection;
    private int arrowHeight;
    private int arrowUpDistance;
    private int arrowWidth;
    private int connerRadius;
    private int fillColor;
    Path fillPath = new Path();
    private boolean hasStroke;
    float height;
    float heightEnd;
    final float heightStart = 3.0f;
    private boolean isArrowCenter;
    final float reviseValue = 3.0f;
    private int strokeColor;
    Path strokePath = new Path();
    private int strokeWidth;
    float width;
    float widthEnd;
    final float widthStart = 3.0f;

    public ChatShape(int i, int i2, boolean z, int i3, String str, int i4, int i5, int i6, int i7) {
        this.arrowWidth = i;
        this.arrowHeight = i2;
        this.isArrowCenter = z;
        this.strokeWidth = i3;
        this.arrowDirection = str;
        this.arrowUpDistance = i4;
        this.connerRadius = i5;
        this.strokeColor = i6;
        this.fillColor = i7;
    }

    private void resizePath(float f, float f2, Path path) {
        this.widthEnd = f - 3.0f;
        this.heightEnd = f2 - 3.0f;
        if (this.isArrowCenter) {
            this.arrowUpDistance = (int) ((f2 / 2.0f) - ((float) (this.arrowHeight / 2)));
        }
        RectF rectF = new RectF();
        path.reset();
        path.moveTo(((float) this.arrowWidth) + 3.0f, (float) (this.arrowUpDistance + this.arrowHeight));
        path.lineTo(3.0f, (float) (this.arrowUpDistance + (this.arrowHeight / 2)));
        path.lineTo(((float) this.arrowWidth) + 3.0f, (float) this.arrowUpDistance);
        path.lineTo(((float) this.arrowWidth) + 3.0f, (float) this.connerRadius);
        int i = this.arrowWidth;
        int i2 = this.connerRadius;
        rectF.set(((float) i) + 3.0f, 3.0f, ((float) i) + 3.0f + ((float) i2), (float) i2);
        path.arcTo(rectF, 180.0f, 90.0f);
        path.lineTo(f - ((float) this.connerRadius), 3.0f);
        int i3 = this.connerRadius;
        rectF.set(f - ((float) i3), 3.0f, f, (float) i3);
        path.arcTo(rectF, 270.0f, 90.0f);
        path.lineTo(f, f2 - ((float) this.connerRadius));
        int i4 = this.connerRadius;
        rectF.set(f - ((float) i4), f2 - ((float) i4), f, f2);
        path.arcTo(rectF, 3.0f, 90.0f);
        path.lineTo(((float) this.arrowWidth) + 3.0f + ((float) this.connerRadius), f2);
        int i5 = this.arrowWidth;
        int i6 = this.connerRadius;
        rectF.set(((float) i5) + 3.0f, f2 - ((float) i6), ((float) i5) + 3.0f + ((float) i6), f2);
        path.arcTo(rectF, 90.0f, 90.0f);
        path.close();
    }

    /* access modifiers changed from: protected */
    public void onResize(float f, float f2) {
        super.onResize(f, f2);
        this.width = f;
        this.height = f2;
        float f3 = f - 3.0f;
        float f4 = f2 - 3.0f;
        resizePath(f3, f4, this.strokePath);
        resizePath(f3, f4, this.fillPath);
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.save();
        if (this.arrowDirection.equals("right")) {
            canvas.scale(-1.0f, 1.0f, this.width / 2.0f, this.height / 2.0f);
        }
        drawFill(canvas, paint);
        drawStroke(canvas, paint);
        canvas.restore();
    }

    private void drawStroke(Canvas canvas, Paint paint) {
        paint.setColor(this.strokeColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth((float) this.strokeWidth);
        canvas.drawPath(this.strokePath, paint);
    }

    private void drawFill(Canvas canvas, Paint paint) {
        paint.setColor(this.fillColor);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStrokeWidth((float) this.strokeWidth);
        canvas.drawPath(this.fillPath, paint);
    }
}
