package mc.csst.com.selfchassis.ui.widget.disc;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class TempControlView extends View {
    private static final String TAG = "TempControlView";
    private float angleOne;
    private int angleRate;
    private Paint arcPaint;
    private Paint buttonPaint;
    private boolean canRotate;
    private int curAngle;
    private float currentAngle;
    private Paint dialPaint;
    private int height;
    private boolean isDown;
    private boolean isMove;
    private int maxAngle;
    private int minAngle;
    private OnClickListener onClickListener;
    private OnTempChangeListener onTempChangeListener;
    private float rotateAngle;
    private Paint tempFlagPaint;
    private Paint tempPaint;
    private Paint titlePaint;
    private int totalAngle;
    private int width;

    public interface OnClickListener {
        void onClick(int i);
    }

    public interface OnTempChangeListener {
        void change(int i);
    }

    public TempControlView(Context context) {
        this(context, (AttributeSet) null);
    }

    public TempControlView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TempControlView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.curAngle = 0;
        this.minAngle = 0;
        this.maxAngle = 359;
        this.angleRate = 1;
        this.totalAngle = 360;
        this.angleOne = (((float) 360) / ((float) (359 - 0))) / ((float) 1);
        this.canRotate = true;
        init();
    }

    private void init() {
        Paint paint = new Paint();
        this.dialPaint = paint;
        paint.setAntiAlias(true);
        this.dialPaint.setStrokeWidth((float) dp2px(2.0f));
        this.dialPaint.setStyle(Paint.Style.STROKE);
        Paint paint2 = new Paint();
        this.arcPaint = paint2;
        paint2.setAntiAlias(true);
        this.arcPaint.setColor(Color.parseColor("#3CB7EA"));
        this.arcPaint.setStrokeWidth((float) dp2px(2.0f));
        this.arcPaint.setStyle(Paint.Style.STROKE);
        Paint paint3 = new Paint();
        this.titlePaint = paint3;
        paint3.setAntiAlias(true);
        this.titlePaint.setTextSize((float) sp2px(15.0f));
        this.titlePaint.setColor(Color.parseColor("#3B434E"));
        this.titlePaint.setStyle(Paint.Style.STROKE);
        Paint paint4 = new Paint();
        this.tempFlagPaint = paint4;
        paint4.setAntiAlias(true);
        this.tempFlagPaint.setTextSize((float) sp2px(25.0f));
        this.tempFlagPaint.setColor(Color.parseColor("#E4A07E"));
        this.tempFlagPaint.setStyle(Paint.Style.STROKE);
        Paint paint5 = new Paint();
        this.buttonPaint = paint5;
        paint5.setAntiAlias(true);
        this.buttonPaint.setColor(Color.parseColor("#0560FD"));
        this.buttonPaint.setStyle(Paint.Style.FILL);
        Paint paint6 = new Paint();
        this.tempPaint = paint6;
        paint6.setAntiAlias(true);
        this.tempPaint.setTextSize((float) sp2px(60.0f));
        this.tempPaint.setColor(Color.parseColor("#E27A3F"));
        this.tempPaint.setStyle(Paint.Style.STROKE);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        int min = Math.min(i2, i);
        this.height = min;
        this.width = min;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawButton(canvas);
        OnTempChangeListener onTempChangeListener2 = this.onTempChangeListener;
        if (onTempChangeListener2 != null) {
            onTempChangeListener2.change(this.curAngle);
        }
    }

    private void drawButton(Canvas canvas) {
        int i = this.width;
        canvas.drawCircle((float) (i / 2), (float) (this.height / 2), (float) (i / 2), this.buttonPaint);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0016, code lost:
        if (r0 != 3) goto L_0x009c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            boolean r0 = r4.canRotate
            if (r0 != 0) goto L_0x0009
            boolean r5 = super.onTouchEvent(r5)
            return r5
        L_0x0009:
            int r0 = r5.getAction()
            r1 = 1
            if (r0 == 0) goto L_0x008c
            if (r0 == r1) goto L_0x0061
            r2 = 2
            if (r0 == r2) goto L_0x001a
            r5 = 3
            if (r0 == r5) goto L_0x0061
            goto L_0x009c
        L_0x001a:
            r4.isMove = r1
            float r0 = r5.getX()
            float r5 = r5.getY()
            float r5 = r4.calcAngle(r0, r5)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "onTouchEvent: "
            r0.append(r2)
            r0.append(r5)
            java.lang.String r0 = r0.toString()
            java.lang.String r2 = "TempControlView"
            android.util.Log.d(r2, r0)
            float r0 = r4.currentAngle
            float r0 = r5 - r0
            int r2 = r4.totalAngle
            int r3 = r2 + -1
            int r3 = -r3
            float r3 = (float) r3
            int r3 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r3 >= 0) goto L_0x004f
            float r2 = (float) r2
            float r0 = r0 + r2
            goto L_0x0058
        L_0x004f:
            int r3 = r2 + 1
            float r3 = (float) r3
            int r3 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r3 <= 0) goto L_0x0058
            float r2 = (float) r2
            float r0 = r0 - r2
        L_0x0058:
            r4.IncreaseAngle(r0)
            r4.currentAngle = r5
            r4.invalidate()
            goto L_0x009c
        L_0x0061:
            boolean r5 = r4.isDown
            if (r5 == 0) goto L_0x009c
            boolean r5 = r4.isMove
            r0 = 0
            if (r5 == 0) goto L_0x0080
            int r5 = r4.curAngle
            int r2 = r4.minAngle
            int r5 = r5 - r2
            int r2 = r4.angleRate
            int r5 = r5 * r2
            float r5 = (float) r5
            float r2 = r4.angleOne
            float r5 = r5 * r2
            r4.rotateAngle = r5
            r4.invalidate()
            r4.isMove = r0
            goto L_0x0089
        L_0x0080:
            mc.csst.com.selfchassis.ui.widget.disc.TempControlView$OnTempChangeListener r5 = r4.onTempChangeListener
            if (r5 == 0) goto L_0x0089
            int r2 = r4.curAngle
            r5.change(r2)
        L_0x0089:
            r4.isDown = r0
            goto L_0x009c
        L_0x008c:
            r4.isDown = r1
            float r0 = r5.getX()
            float r5 = r5.getY()
            float r5 = r4.calcAngle(r0, r5)
            r4.currentAngle = r5
        L_0x009c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: mc.csst.com.selfchassis.ui.widget.disc.TempControlView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    private float calcAngle(float f, float f2) {
        double d;
        float f3 = f - ((float) (this.width / 2));
        float f4 = f2 - ((float) (this.height / 2));
        int i = (f3 > 0.0f ? 1 : (f3 == 0.0f ? 0 : -1));
        if (i != 0) {
            float abs = Math.abs(f4 / f3);
            d = i > 0 ? f4 >= 0.0f ? Math.atan((double) abs) : 6.283185307179586d - Math.atan((double) abs) : f4 >= 0.0f ? 3.141592653589793d - Math.atan((double) abs) : Math.atan((double) abs) + 3.141592653589793d;
        } else {
            d = f4 > 0.0f ? 1.5707963267948966d : -1.5707963267948966d;
        }
        return (float) ((d * 180.0d) / 3.141592653589793d);
    }

    private void IncreaseAngle(float f) {
        float f2 = this.rotateAngle + f;
        this.rotateAngle = f2;
        if (f2 < 0.0f) {
            this.rotateAngle = f2 + ((float) this.totalAngle);
        } else {
            int i = this.totalAngle;
            if (f2 > ((float) i)) {
                this.rotateAngle = f2 - ((float) i);
            }
        }
        this.curAngle = ((int) (((double) ((this.rotateAngle / this.angleOne) / ((float) this.angleRate))) + 0.5d)) + this.minAngle;
    }

    public void setAngleRate(int i) {
        this.angleRate = i;
    }

    public void setTemp(int i) {
        setTemp(this.minAngle, this.maxAngle, i);
    }

    public void setTemp(int i, int i2, int i3) {
        this.minAngle = i;
        this.maxAngle = i2;
        if (i3 < i) {
            this.curAngle = i;
        } else {
            this.curAngle = i3;
        }
        float f = ((float) this.totalAngle) / ((float) (i2 - i));
        int i4 = this.angleRate;
        float f2 = f / ((float) i4);
        this.angleOne = f2;
        this.rotateAngle = ((float) ((i3 - i) * i4)) * f2;
        invalidate();
    }

    public void setCanRotate(boolean z) {
        this.canRotate = z;
    }

    public boolean getCanRotate() {
        return this.canRotate;
    }

    public void setOnTempChangeListener(OnTempChangeListener onTempChangeListener2) {
        this.onTempChangeListener = onTempChangeListener2;
    }

    public void setOnClickListener(OnClickListener onClickListener2) {
        this.onClickListener = onClickListener2;
    }

    public int dp2px(float f) {
        return (int) TypedValue.applyDimension(1, f, getResources().getDisplayMetrics());
    }

    private int sp2px(float f) {
        return (int) TypedValue.applyDimension(2, f, getResources().getDisplayMetrics());
    }
}
