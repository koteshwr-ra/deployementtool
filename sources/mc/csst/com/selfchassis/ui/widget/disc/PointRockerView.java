package mc.csst.com.selfchassis.ui.widget.disc;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;
import android.view.View;
import com.ciot.sentrymove.R;

public class PointRockerView extends View {
    private Paint buttonPaint;
    private int height;
    private PaintFlagsDrawFilter paintFlagsDrawFilter;
    private Bitmap pointerImage;
    private int rotateAngle;
    private int width;

    public void setRotateAngle(int i) {
        this.rotateAngle = i;
        invalidate();
    }

    public PointRockerView(Context context) {
        this(context, (AttributeSet) null);
    }

    public PointRockerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PointRockerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.pointerImage = BitmapFactory.decodeResource(getResources(), R.mipmap.bg_point_dial_pointer);
        this.paintFlagsDrawFilter = new PaintFlagsDrawFilter(0, 3);
        Paint paint = new Paint();
        this.buttonPaint = paint;
        paint.setAntiAlias(true);
        this.buttonPaint.setColor(Color.parseColor("#0560FD"));
        this.buttonPaint.setStyle(Paint.Style.FILL);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawButton(canvas);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        int min = Math.min(i2, i);
        this.height = min;
        this.width = min;
    }

    private void drawButton(Canvas canvas) {
        canvas.drawColor(0);
        Bitmap scaleImgMax = scaleImgMax(this.pointerImage, this.width, this.height);
        this.pointerImage = scaleImgMax;
        int width2 = scaleImgMax.getWidth();
        int height2 = this.pointerImage.getHeight();
        Matrix matrix = new Matrix();
        matrix.setTranslate((float) ((this.width - width2) / 2), (float) ((this.height - height2) / 2));
        matrix.postRotate((float) (this.rotateAngle + 90), (float) (this.width / 2), (float) (this.height / 2));
        canvas.setDrawFilter(this.paintFlagsDrawFilter);
        canvas.drawBitmap(this.pointerImage, matrix, this.buttonPaint);
    }

    public static Bitmap scaleImgMax(Bitmap bitmap, int i, int i2) {
        int width2 = bitmap.getWidth();
        int height2 = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(((float) i) / ((float) width2), ((float) i2) / ((float) height2));
        return Bitmap.createBitmap(bitmap, 0, 0, width2, height2, matrix, true);
    }
}
