package mc.csst.com.selfchassis.ui.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import java.util.List;

public class BezierView {
    private float drawScale = 1.0f;
    private float lineSmoothness = 0.2f;
    private Path mAssistPath;
    private Path mPath;
    private PathMeasure mPathMeasure;
    private List<PointF> mPointList;

    public void setPointList(List<PointF> list) {
        this.mPointList = list;
        measurePath();
    }

    public void startDraw(Canvas canvas, int i) {
        if (this.mPointList != null) {
            Paint paint = new Paint();
            paint.setStrokeWidth(5.0f);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(i);
            paint.setAntiAlias(true);
            paint.setStrokeCap(Paint.Cap.ROUND);
            Path path = new Path();
            path.rLineTo(0.0f, 0.0f);
            float length = this.mPathMeasure.getLength() * this.drawScale;
            if (this.mPathMeasure.getSegment(0.0f, length, path, true)) {
                canvas.drawPath(path, paint);
                this.mPathMeasure.getPosTan(length, new float[2], (float[]) null);
            }
        }
    }

    private void measurePath() {
        float f;
        float f2;
        if (this.mPointList != null) {
            this.mPath = new Path();
            this.mAssistPath = new Path();
            int size = this.mPointList.size();
            float f3 = Float.NaN;
            float f4 = Float.NaN;
            float f5 = Float.NaN;
            float f6 = Float.NaN;
            float f7 = Float.NaN;
            float f8 = Float.NaN;
            int i = 0;
            while (i < size) {
                if (Float.isNaN(f3)) {
                    PointF pointF = this.mPointList.get(i);
                    float f9 = pointF.x;
                    f5 = pointF.y;
                    f3 = f9;
                }
                if (Float.isNaN(f4)) {
                    if (i > 0) {
                        PointF pointF2 = this.mPointList.get(i - 1);
                        float f10 = pointF2.x;
                        f7 = pointF2.y;
                        f4 = f10;
                    } else {
                        f4 = f3;
                        f7 = f5;
                    }
                }
                if (Float.isNaN(f6)) {
                    if (i > 1) {
                        PointF pointF3 = this.mPointList.get(i - 2);
                        float f11 = pointF3.x;
                        f8 = pointF3.y;
                        f6 = f11;
                    } else {
                        f6 = f4;
                        f8 = f7;
                    }
                }
                if (i < size - 1) {
                    PointF pointF4 = this.mPointList.get(i + 1);
                    float f12 = pointF4.x;
                    f = pointF4.y;
                    f2 = f12;
                } else {
                    f2 = f3;
                    f = f5;
                }
                if (i == 0) {
                    this.mPath.moveTo(f3, f5);
                    this.mAssistPath.moveTo(f3, f5);
                } else {
                    float f13 = this.lineSmoothness;
                    float f14 = ((f3 - f6) * f13) + f4;
                    float f15 = ((f5 - f8) * f13) + f7;
                    float f16 = f3 - ((f2 - f4) * f13);
                    float f17 = f5 - (f13 * (f - f7));
                    this.mPath.cubicTo(f14, f15, f16, f17, f3, f5);
                    this.mAssistPath.lineTo(f14, f15);
                    this.mAssistPath.lineTo(f16, f17);
                    this.mAssistPath.lineTo(f3, f5);
                }
                i++;
                f6 = f4;
                f8 = f7;
                f4 = f3;
                f7 = f5;
                f3 = f2;
                f5 = f;
            }
            this.mPathMeasure = new PathMeasure(this.mPath, false);
        }
    }
}
