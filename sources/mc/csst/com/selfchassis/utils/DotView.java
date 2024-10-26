package mc.csst.com.selfchassis.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.ciot.sentrymove.R;

public class DotView extends View {
    private float dot1_x;
    private float dot1_y;
    private float dot2_x;
    private float dot2_y;
    private float dot3_x;
    private float dot3_y;
    private float dot4_x;
    private float dot4_y;
    private float event_x;
    private float event_y;
    private float[] pts;
    private int select_index = 0;
    private float v_height;
    private float v_width;

    private boolean isRangeOfView(float f, float f2, float f3, float f4) {
        return f3 > f - 40.0f && f3 < f + 40.0f && f4 > f2 - 40.0f && f4 < f2 + 40.0f;
    }

    public DotView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DotView(Context context) {
        super(context);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.event_x = motionEvent.getX();
        this.event_y = motionEvent.getY();
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action == 2) {
                int i = this.select_index;
                if (i == 0) {
                    float f = this.event_x;
                    this.dot1_x = f;
                    float f2 = this.event_y;
                    this.dot1_y = f2;
                    this.pts = new float[]{f, f2, this.dot2_x, this.dot2_y, this.dot3_x, this.dot3_y, this.dot4_x, this.dot4_y};
                    invalidate();
                } else if (i == 1) {
                    float f3 = this.event_x;
                    this.dot2_x = f3;
                    float f4 = this.event_y;
                    this.dot2_y = f4;
                    this.pts = new float[]{this.dot1_x, this.dot1_y, f3, f4, this.dot3_x, this.dot3_y, this.dot4_x, this.dot4_y};
                    invalidate();
                } else if (i == 2) {
                    float f5 = this.event_x;
                    this.dot3_x = f5;
                    float f6 = this.event_y;
                    this.dot3_y = f6;
                    this.pts = new float[]{this.dot1_x, this.dot1_y, this.dot2_x, this.dot2_y, f5, f6, this.dot4_x, this.dot4_y};
                    invalidate();
                } else if (i == 3) {
                    float f7 = this.event_x;
                    this.dot4_x = f7;
                    float f8 = this.event_y;
                    this.dot4_y = f8;
                    this.pts = new float[]{this.dot1_x, this.dot1_y, this.dot2_x, this.dot2_y, this.dot3_x, this.dot3_y, f7, f8};
                    invalidate();
                }
            }
        } else if (isRangeOfView(this.dot1_x, this.dot1_y, this.event_x, this.event_y)) {
            this.select_index = 0;
        } else if (isRangeOfView(this.dot2_x, this.dot2_y, this.event_x, this.event_y)) {
            this.select_index = 1;
        } else if (isRangeOfView(this.dot3_x, this.dot3_y, this.event_x, this.event_y)) {
            this.select_index = 2;
        } else if (isRangeOfView(this.dot4_x, this.dot4_y, this.event_x, this.event_y)) {
            this.select_index = 3;
        } else {
            this.select_index = -1;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.v_width = (float) getWidth();
        float height = (float) getHeight();
        this.v_height = height;
        float f = (this.v_width / 2.0f) - 250.0f;
        this.dot1_x = f;
        float f2 = (height / 2.0f) + 250.0f;
        this.dot1_y = f2;
        float f3 = f + 300.0f;
        this.dot2_x = f3;
        this.dot2_y = f2;
        this.dot3_x = f;
        float f4 = f2 + 300.0f;
        this.dot3_y = f4;
        this.dot4_x = f3;
        float f5 = 300.0f + f2;
        this.dot4_y = f5;
        this.pts = new float[]{f, f2, f3, f2, f, f4, f3, f5};
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color.clr_02C769));
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20.0f);
        canvas.drawPoints(this.pts, paint);
        paint.setStrokeWidth(5.0f);
        float f = this.dot1_x;
        float f2 = this.dot1_y;
        float f3 = this.dot2_x;
        float f4 = this.dot2_y;
        float f5 = this.dot4_x;
        float f6 = this.dot4_y;
        float f7 = this.dot3_x;
        float f8 = this.dot3_y;
        canvas.drawLines(new float[]{f, f2, f3, f4, f3, f4, f5, f6, f5, f6, f7, f8, f7, f8, f, f2}, paint);
        Path path = new Path();
        path.moveTo(this.dot1_x, this.dot1_y);
        path.lineTo(this.dot2_x, this.dot2_y);
        path.lineTo(this.dot4_x, this.dot4_y);
        path.lineTo(this.dot3_x, this.dot3_y);
        path.lineTo(this.dot1_x, this.dot1_y);
        paint.setColor(getResources().getColor(R.color.clr_666666));
        canvas.drawPath(path, paint);
    }
}
