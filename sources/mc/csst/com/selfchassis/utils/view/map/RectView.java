package mc.csst.com.selfchassis.utils.view.map;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.ciot.base.util.GsonUtils;
import com.ciot.base.util.MyLogUtils;
import com.ciot.sentrymove.R;
import java.util.ArrayList;
import java.util.List;
import mc.csst.com.selfchassislibrary.bean.MapInfoBean;
import mc.csst.com.selfchassislibrary.bean.PointBean;
import mc.csst.com.selfchassislibrary.bean.msg.GlobalLocateReqBean;

public class RectView extends View {
    private static final int RECT_DEFAULT_HEIGHT = 200;
    private static final int RECT_DEFAULT_WIDTH = 200;
    private static final String TAG = RectView.class.getSimpleName();
    private float aspect = -1.0f;
    private int cornerLength;
    private int cornerWidth;
    float downX;
    float downY;
    boolean isBottom;
    boolean isLeft;
    boolean isMove;
    boolean isRight;
    boolean isSlideBottom;
    boolean isSlideLeft;
    boolean isSlideRight;
    boolean isSlideTop;
    boolean isTop;
    boolean isTouch = false;
    private float mMapHeight;
    private Matrix mMapMatrix;
    private float mMapWidth;
    private float mOriginX;
    private float mOriginY;
    private float mRectCenterX = 0.0f;
    private float mRectCenterY = 0.0f;
    private Paint mRectFillColorPaint;
    private float mResolution;
    private float mScale = 1.0f;
    private float measuredHeight;
    private float measuredWidth;
    private Paint paint;
    private int rectBorder;
    float rectBottom;
    private int rectHeightInitValue;
    float rectLeft;
    float rectRight;
    float rectTop;
    private int rectWidthInitValue;

    public RectView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public RectView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public RectView(Context context) {
        super(context);
        init();
    }

    private void init() {
        this.cornerWidth = 4;
        this.rectBorder = 1;
        Paint paint2 = new Paint();
        this.paint = paint2;
        paint2.setAntiAlias(true);
        this.paint.setColor(getResources().getColor(R.color.clr_4786FF));
        this.paint.setStyle(Paint.Style.STROKE);
        Paint paint3 = new Paint();
        this.mRectFillColorPaint = paint3;
        paint3.setAntiAlias(true);
        this.mRectFillColorPaint.setStrokeWidth(2.0f);
        this.mRectFillColorPaint.setColor(getResources().getColor(R.color.clr_331D85FF));
        this.mRectFillColorPaint.setStyle(Paint.Style.FILL);
    }

    public boolean onRectTouchEvent(MotionEvent motionEvent) {
        if (this.mMapMatrix == null) {
            return false;
        }
        float[] fArr = {motionEvent.getX(), motionEvent.getY()};
        Matrix matrix = new Matrix();
        this.mMapMatrix.invert(matrix);
        matrix.mapPoints(fArr);
        int action = motionEvent.getAction() & 255;
        if (action != 0) {
            if (action != 1) {
                if (action != 2) {
                    if (action != 3) {
                        if (action == 5) {
                            this.isTouch = false;
                        }
                    }
                } else if (!this.isTouch) {
                    return false;
                } else {
                    float f = fArr[0];
                    float f2 = fArr[1];
                    float f3 = f - this.downX;
                    float f4 = f2 - this.downY;
                    if (this.isMove) {
                        float f5 = this.rectLeft + f3;
                        this.rectLeft = f5;
                        float f6 = this.rectRight + f3;
                        this.rectRight = f6;
                        this.rectTop += f4;
                        this.rectBottom += f4;
                        if (f5 < 0.0f || f6 > this.measuredWidth) {
                            this.rectLeft -= f3;
                            this.rectRight -= f3;
                        }
                        if (this.rectTop < 0.0f || this.rectBottom > this.measuredHeight) {
                            this.rectTop -= f4;
                            this.rectBottom -= f4;
                        }
                        invalidate();
                        this.downX = f;
                        this.downY = f2;
                    } else if (this.aspect != -1.0f) {
                        if (!this.isLeft || (!this.isTop && !this.isBottom)) {
                            if (!this.isRight || (!this.isTop && !this.isBottom)) {
                                if (this.isLeft && !this.isSlideLeft) {
                                    this.isSlideLeft = true;
                                } else if (this.isRight && !this.isSlideLeft) {
                                    this.isSlideRight = true;
                                } else if (this.isTop && !this.isSlideTop) {
                                    this.isSlideTop = true;
                                } else if (this.isBottom && !this.isSlideBottom) {
                                    this.isSlideBottom = true;
                                }
                            } else if (!this.isSlideRight && !this.isSlideTop && !this.isSlideBottom) {
                                float abs = Math.abs(f3);
                                float abs2 = Math.abs(f4);
                                if (abs > abs2 && abs > 10.0f) {
                                    this.isSlideRight = true;
                                } else if (abs < abs2 && abs2 > 10.0f) {
                                    if (this.isTop) {
                                        this.isSlideTop = true;
                                    } else {
                                        this.isSlideBottom = true;
                                    }
                                }
                            }
                        } else if (!this.isSlideLeft && !this.isSlideTop && !this.isSlideBottom) {
                            float abs3 = Math.abs(f3);
                            float abs4 = Math.abs(f4);
                            if (abs3 > abs4 && abs3 > 10.0f) {
                                this.isSlideLeft = true;
                            } else if (abs3 < abs4 && abs4 > 10.0f) {
                                if (this.isTop) {
                                    this.isSlideTop = true;
                                } else {
                                    this.isSlideBottom = true;
                                }
                            }
                        }
                        if (this.isSlideLeft) {
                            float f7 = this.rectLeft + f3;
                            this.rectLeft = f7;
                            if (f7 < 0.0f) {
                                this.rectLeft = 0.0f;
                            }
                            float f8 = this.rectRight;
                            float f9 = f8 - this.rectLeft;
                            int i = this.cornerLength;
                            if (f9 < ((float) (i * 2))) {
                                f9 = (float) (i * 2);
                                this.rectLeft = f8 - f9;
                            }
                            float f10 = this.aspect;
                            float f11 = f9 / f10;
                            int i2 = this.cornerLength;
                            if (f11 < ((float) (i2 * 2))) {
                                f11 = (float) (i2 * 2);
                                this.rectLeft = this.rectRight - (f10 * f11);
                            }
                            if (this.isTop) {
                                this.rectBottom = this.rectTop + f11;
                            } else if (this.isBottom) {
                                this.rectTop = this.rectBottom - f11;
                            } else {
                                float f12 = this.rectBottom;
                                float f13 = this.rectTop;
                                float f14 = ((f12 - f13) - f11) / 2.0f;
                                this.rectTop = f13 + f14;
                                this.rectBottom = f12 - f14;
                            }
                            if (this.rectTop < 0.0f) {
                                this.rectTop = 0.0f;
                                this.rectBottom = f11;
                                float f15 = this.measuredHeight;
                                if (f11 > f15) {
                                    this.rectBottom = f15;
                                }
                                this.rectLeft = this.rectRight - (this.rectBottom * this.aspect);
                            } else {
                                float f16 = this.rectBottom;
                                float f17 = this.measuredHeight;
                                if (f16 > f17) {
                                    this.rectBottom = f17;
                                    float f18 = f17 - f11;
                                    this.rectTop = f18;
                                    if (f18 < 0.0f) {
                                        this.rectTop = 0.0f;
                                    }
                                    this.rectLeft = this.rectRight - ((this.rectBottom - this.rectTop) * this.aspect);
                                }
                            }
                            invalidate();
                            this.downX = f;
                            this.downY = f2;
                        } else if (this.isSlideRight) {
                            float f19 = this.rectRight + f3;
                            this.rectRight = f19;
                            float f20 = this.measuredWidth;
                            if (f19 > f20) {
                                this.rectRight = f20;
                            }
                            float f21 = this.rectRight;
                            float f22 = this.rectLeft;
                            float f23 = f21 - f22;
                            int i3 = this.cornerLength;
                            if (f23 < ((float) (i3 * 2))) {
                                f23 = (float) (i3 * 2);
                                this.rectRight = f22 + f23;
                            }
                            float f24 = this.aspect;
                            float f25 = f23 / f24;
                            int i4 = this.cornerLength;
                            if (f25 < ((float) (i4 * 2))) {
                                f25 = (float) (i4 * 2);
                                this.rectRight = this.rectLeft + (f24 * f25);
                            }
                            if (this.isTop) {
                                this.rectBottom = this.rectTop + f25;
                            } else if (this.isBottom) {
                                this.rectTop = this.rectBottom - f25;
                            } else {
                                float f26 = this.rectBottom;
                                float f27 = this.rectTop;
                                float f28 = ((f26 - f27) - f25) / 2.0f;
                                this.rectTop = f27 + f28;
                                this.rectBottom = f26 - f28;
                            }
                            if (this.rectTop < 0.0f) {
                                this.rectTop = 0.0f;
                                this.rectBottom = f25;
                                float f29 = this.measuredHeight;
                                if (f25 > f29) {
                                    this.rectBottom = f29;
                                }
                                this.rectRight = this.rectLeft + (this.rectBottom * this.aspect);
                            } else {
                                float f30 = this.rectBottom;
                                float f31 = this.measuredHeight;
                                if (f30 > f31) {
                                    this.rectBottom = f31;
                                    float f32 = f31 - f25;
                                    this.rectTop = f32;
                                    if (f32 < 0.0f) {
                                        this.rectTop = 0.0f;
                                    }
                                    this.rectRight = this.rectLeft + ((this.rectBottom - this.rectTop) * this.aspect);
                                }
                            }
                            invalidate();
                            this.downX = f;
                            this.downY = f2;
                        } else if (this.isSlideTop) {
                            float f33 = this.rectTop + f4;
                            this.rectTop = f33;
                            if (f33 < 0.0f) {
                                this.rectTop = 0.0f;
                            }
                            float f34 = this.rectBottom;
                            float f35 = f34 - this.rectTop;
                            int i5 = this.cornerLength;
                            if (f35 < ((float) (i5 * 2))) {
                                f35 = (float) (i5 * 2);
                                this.rectTop = f34 - f35;
                            }
                            float f36 = this.aspect;
                            float f37 = f35 * f36;
                            int i6 = this.cornerLength;
                            if (f37 < ((float) (i6 * 2))) {
                                f37 = (float) (i6 * 2);
                                this.rectTop = this.rectBottom - (f37 / f36);
                            }
                            if (this.isLeft) {
                                this.rectRight = this.rectLeft + f37;
                            } else if (this.isRight) {
                                this.rectLeft = this.rectRight - f37;
                            } else {
                                float f38 = this.rectRight;
                                float f39 = this.rectLeft;
                                float f40 = ((f38 - f39) - f37) / 2.0f;
                                this.rectLeft = f39 + f40;
                                this.rectRight = f38 - f40;
                            }
                            if (this.rectLeft < 0.0f) {
                                this.rectLeft = 0.0f;
                                this.rectRight = f37;
                                float f41 = this.measuredWidth;
                                if (f37 > f41) {
                                    this.rectRight = f41;
                                }
                                this.rectTop = this.rectBottom - (this.rectRight / this.aspect);
                            } else {
                                float f42 = this.rectRight;
                                float f43 = this.measuredWidth;
                                if (f42 > f43) {
                                    this.rectRight = f43;
                                    float f44 = f43 - f37;
                                    this.rectLeft = f44;
                                    if (f44 < 0.0f) {
                                        this.rectLeft = 0.0f;
                                        f37 = f43;
                                    }
                                    this.rectTop = this.rectBottom - (f37 / this.aspect);
                                }
                            }
                            invalidate();
                            this.downX = f;
                            this.downY = f2;
                        } else if (this.isSlideBottom) {
                            float f45 = this.rectBottom + f4;
                            this.rectBottom = f45;
                            float f46 = this.measuredHeight;
                            if (f45 > f46) {
                                this.rectBottom = f46;
                            }
                            float f47 = this.rectBottom;
                            float f48 = this.rectTop;
                            float f49 = f47 - f48;
                            int i7 = this.cornerLength;
                            if (f49 < ((float) (i7 * 2))) {
                                f49 = (float) (i7 * 2);
                                this.rectBottom = f48 + f49;
                            }
                            float f50 = this.aspect;
                            float f51 = f49 * f50;
                            int i8 = this.cornerLength;
                            if (f51 < ((float) (i8 * 2))) {
                                f51 = (float) (i8 * 2);
                                this.rectBottom = this.rectTop + (f51 / f50);
                            }
                            if (this.isLeft) {
                                this.rectRight = this.rectLeft + f51;
                            } else if (this.isRight) {
                                this.rectLeft = this.rectRight - f51;
                            } else {
                                float f52 = this.rectRight;
                                float f53 = this.rectLeft;
                                float f54 = ((f52 - f53) - f51) / 2.0f;
                                this.rectLeft = f53 + f54;
                                this.rectRight = f52 - f54;
                            }
                            if (this.rectLeft < 0.0f) {
                                this.rectLeft = 0.0f;
                                this.rectRight = f51;
                                float f55 = this.measuredWidth;
                                if (f51 > f55) {
                                    this.rectRight = f55;
                                }
                                this.rectBottom = this.rectTop + (this.rectRight / this.aspect);
                            } else {
                                float f56 = this.rectRight;
                                float f57 = this.measuredWidth;
                                if (f56 > f57) {
                                    this.rectRight = f57;
                                    float f58 = f57 - f51;
                                    this.rectLeft = f58;
                                    if (f58 < 0.0f) {
                                        this.rectLeft = 0.0f;
                                        f51 = f57;
                                    }
                                    this.rectBottom = this.rectTop + (f51 / this.aspect);
                                }
                            }
                            invalidate();
                            this.downX = f;
                            this.downY = f2;
                        }
                    } else {
                        if (this.isLeft) {
                            float f59 = this.rectLeft + f3;
                            this.rectLeft = f59;
                            if (f59 < 0.0f) {
                                this.rectLeft = 0.0f;
                            }
                            float f60 = this.rectLeft;
                            float f61 = this.rectRight;
                            int i9 = this.cornerLength;
                            if (f60 > f61 - ((float) (i9 * 2))) {
                                this.rectLeft = f61 - ((float) (i9 * 2));
                            }
                        } else if (this.isRight) {
                            float f62 = this.rectRight + f3;
                            this.rectRight = f62;
                            float f63 = this.measuredWidth;
                            if (f62 > f63) {
                                this.rectRight = f63;
                            }
                            float f64 = this.rectRight;
                            float f65 = this.rectLeft;
                            int i10 = this.cornerLength;
                            if (f64 < ((float) (i10 * 2)) + f65) {
                                this.rectRight = f65 + ((float) (i10 * 2));
                            }
                        }
                        if (this.isTop) {
                            float f66 = this.rectTop + f4;
                            this.rectTop = f66;
                            if (f66 < 0.0f) {
                                this.rectTop = 0.0f;
                            }
                            float f67 = this.rectTop;
                            float f68 = this.rectBottom;
                            int i11 = this.cornerLength;
                            if (f67 > f68 - ((float) (i11 * 2))) {
                                this.rectTop = f68 - ((float) (i11 * 2));
                            }
                        } else if (this.isBottom) {
                            float f69 = this.rectBottom + f4;
                            this.rectBottom = f69;
                            float f70 = this.measuredHeight;
                            if (f69 > f70) {
                                this.rectBottom = f70;
                            }
                            float f71 = this.rectBottom;
                            float f72 = this.rectTop;
                            int i12 = this.cornerLength;
                            if (f71 < ((float) (i12 * 2)) + f72) {
                                this.rectBottom = f72 + ((float) (i12 * 2));
                            }
                        }
                        invalidate();
                        this.downX = f;
                        this.downY = f2;
                    }
                }
            }
            this.isLeft = false;
            this.isRight = false;
            this.isTop = false;
            this.isBottom = false;
            this.isMove = false;
            this.isSlideLeft = false;
            this.isSlideRight = false;
            this.isSlideTop = false;
            this.isSlideBottom = false;
            this.isTouch = false;
            MyLogUtils.Logd(TAG, "getCutArr()： " + GsonUtils.toJson(getCutArr()));
            MyLogUtils.Logd(TAG, "getPoints()： " + GsonUtils.toJson(getPoints()));
        } else {
            float f73 = fArr[0];
            this.downX = f73;
            float f74 = fArr[1];
            this.downY = f74;
            float f75 = this.rectLeft;
            if (f73 >= f75) {
                float f76 = this.rectRight;
                if (f73 <= f76 && f74 >= this.rectTop && f74 <= this.rectBottom) {
                    this.isTouch = true;
                    int i13 = (int) ((f76 - f75) / 3.0f);
                    if (f73 < f75 || f73 > f75 + ((float) i13)) {
                        float f77 = this.downX;
                        float f78 = this.rectRight;
                        if (f77 <= f78 && f77 >= f78 - ((float) i13)) {
                            this.isRight = true;
                        }
                    } else {
                        this.isLeft = true;
                    }
                    float f79 = this.rectBottom;
                    float f80 = this.rectTop;
                    int i14 = (int) ((f79 - f80) / 3.0f);
                    float f81 = this.downY;
                    if (f81 < f80 || f81 > f80 + ((float) i14)) {
                        float f82 = this.downY;
                        float f83 = this.rectBottom;
                        if (f82 <= f83 && f82 >= f83 - ((float) i14)) {
                            this.isBottom = true;
                        }
                    } else {
                        this.isTop = true;
                    }
                    if (!this.isLeft && !this.isTop && !this.isRight && !this.isBottom) {
                        this.isMove = true;
                    }
                }
            }
        }
        return this.isTouch;
    }

    public float[] getCutArr() {
        return new float[]{this.rectLeft, this.rectTop, this.rectRight, this.rectBottom};
    }

    public int getRectWidth() {
        return (int) this.measuredWidth;
    }

    public int getRectHeight() {
        return (int) this.measuredHeight;
    }

    public void setAspect(float f) {
        this.aspect = f;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        MyLogUtils.Logd(TAG, "触发onMeasure");
        if (this.measuredWidth == 0.0f) {
            initParams();
        }
    }

    private void initParams() {
        float f = this.mMapWidth;
        if (f != 0.0f) {
            this.measuredWidth = f;
        } else if (this.measuredWidth == 0.0f) {
            this.measuredWidth = (float) getMeasuredWidth();
        }
        if (this.mMapWidth != 0.0f) {
            this.measuredHeight = this.mMapHeight;
        } else if (this.measuredHeight == 0.0f) {
            this.measuredHeight = (float) getMeasuredHeight();
        }
        String str = TAG;
        MyLogUtils.Logd(str, "矩形画布宽高：measuredWidth：" + this.measuredWidth + ";measuredHeight:" + this.measuredHeight);
        String str2 = TAG;
        MyLogUtils.Logd(str2, "矩形中心点初始值：mRectCenterX：" + this.mRectCenterX + ";mRectCenterY:" + this.mRectCenterY);
        this.rectWidthInitValue = 200;
        this.rectHeightInitValue = 200;
        if (((float) 200) > this.measuredWidth || ((float) 200) > this.measuredHeight) {
            float f2 = this.measuredWidth;
            float f3 = this.measuredHeight;
            int i = f2 > f3 ? (int) f3 : (int) f2;
            this.rectWidthInitValue = i;
            this.rectHeightInitValue = i;
        }
        float f4 = this.mRectCenterX;
        int i2 = this.rectWidthInitValue;
        if (f4 < ((float) i2) / 2.0f) {
            this.mRectCenterX = ((float) i2) / 2.0f;
        } else {
            float f5 = f4 + (((float) i2) / 2.0f);
            float f6 = this.measuredWidth;
            if (f5 > f6) {
                this.mRectCenterX = f6 - (((float) i2) / 2.0f);
            }
        }
        float f7 = this.mRectCenterY;
        int i3 = this.rectHeightInitValue;
        if (f7 < ((float) i3) / 2.0f) {
            this.mRectCenterY = ((float) i3) / 2.0f;
        } else {
            float f8 = f7 + (((float) i3) / 2.0f);
            float f9 = this.measuredHeight;
            if (f8 > f9) {
                this.mRectCenterY = f9 - (((float) i3) / 2.0f);
            }
        }
        float f10 = this.aspect;
        if (f10 == -1.0f) {
            int i4 = this.rectWidthInitValue;
            this.cornerLength = i4 / 6;
            float f11 = this.mRectCenterX;
            this.rectRight = (((float) i4) / 2.0f) + f11;
            this.rectLeft = f11 - (((float) i4) / 2.0f);
            float f12 = this.mRectCenterY;
            int i5 = this.rectHeightInitValue;
            this.rectTop = f12 - (((float) i5) / 2.0f);
            this.rectBottom = f12 + (((float) i5) / 2.0f);
            return;
        }
        float f13 = (this.measuredWidth * 1.0f) / this.measuredHeight;
        if (f10 > 1.0f) {
            this.cornerLength = this.rectWidthInitValue / 6;
        } else {
            this.cornerLength = this.rectHeightInitValue / 6;
        }
        float f14 = this.aspect;
        if (f14 > f13) {
            this.rectLeft = 0.0f;
            float f15 = this.measuredWidth;
            this.rectRight = f15;
            float f16 = f15 / f14;
            float f17 = (this.measuredHeight - f16) / 2.0f;
            this.rectTop = f17;
            this.rectBottom = f17 + f16;
            return;
        }
        this.rectTop = 0.0f;
        float f18 = this.measuredHeight;
        this.rectBottom = f18;
        float f19 = f18 * f14;
        float f20 = (this.measuredWidth - f19) / 2.0f;
        this.rectLeft = f20;
        this.rectRight = f20 + f19;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        canvas.setMatrix(this.mMapMatrix);
        this.paint.setStrokeWidth((float) this.rectBorder);
        canvas.drawRect(this.rectLeft, this.rectTop, this.rectRight, this.rectBottom, this.mRectFillColorPaint);
        canvas.drawRect(this.rectLeft, this.rectTop, this.rectRight, this.rectBottom, this.paint);
        drawLine(canvas, this.rectLeft, this.rectTop, this.rectRight, this.rectBottom);
    }

    private void drawLine(Canvas canvas, float f, float f2, float f3, float f4) {
        this.paint.setStrokeWidth(1.0f);
        float f5 = (f3 - f) / 3.0f;
        float f6 = f5 + f;
        canvas.drawLine(f6, f2, f6, f4, this.paint);
        float f7 = (f5 * 2.0f) + f;
        Canvas canvas2 = canvas;
        canvas2.drawLine(f7, f2, f7, f4, this.paint);
        float f8 = (f4 - f2) / 3.0f;
        float f9 = f8 + f2;
        float f10 = f;
        float f11 = f3;
        canvas2.drawLine(f10, f9, f11, f9, this.paint);
        float f12 = (f8 * 2.0f) + f2;
        canvas2.drawLine(f10, f12, f11, f12, this.paint);
        this.paint.setStrokeWidth((float) this.cornerWidth);
        float f13 = f2;
        canvas2.drawLine(f - (((float) this.cornerWidth) / 2.0f), f13, f + ((float) this.cornerLength), f2, this.paint);
        canvas2.drawLine(f, f13, f, f2 + ((float) this.cornerLength), this.paint);
        canvas2.drawLine(f3 + (((float) this.cornerWidth) / 2.0f), f13, f3 - ((float) this.cornerLength), f2, this.paint);
        canvas2.drawLine(f3, f13, f3, f2 + ((float) this.cornerLength), this.paint);
        float f14 = f4;
        canvas2.drawLine(f, f14, f, f4 - ((float) this.cornerLength), this.paint);
        canvas2.drawLine(f - (((float) this.cornerWidth) / 2.0f), f14, f + ((float) this.cornerLength), f4, this.paint);
        Canvas canvas3 = canvas;
        float f15 = f4;
        canvas3.drawLine(f3 + (((float) this.cornerWidth) / 2.0f), f15, f3 - ((float) this.cornerLength), f4, this.paint);
        canvas3.drawLine(f3, f15, f3, f4 - ((float) this.cornerLength), this.paint);
    }

    private int dp2px(float f) {
        return (int) ((f * Resources.getSystem().getDisplayMetrics().density) + 0.5f);
    }

    public void setRectColor(int i, int i2) {
        this.paint.setColor(i);
        this.mRectFillColorPaint.setColor(i2);
        invalidate();
    }

    public void setMapInfo(MapInfoBean mapInfoBean, float f, float f2) {
        String str = TAG;
        MyLogUtils.Logd(str, "setMapInfo :" + mapInfoBean.toString());
        this.mOriginX = mapInfoBean.getX();
        this.mOriginY = mapInfoBean.getY();
        this.mResolution = mapInfoBean.getR();
        this.mMapWidth = f;
        this.mMapHeight = f2;
        this.measuredWidth = 0.0f;
        this.measuredHeight = 0.0f;
    }

    public void setPose(PointBean pointBean) {
        float x = pointBean.getX();
        float y = pointBean.getY();
        float f = x - this.mOriginX;
        float f2 = this.mResolution;
        this.mRectCenterX = f / f2;
        this.mRectCenterY = (y - this.mOriginY) / f2;
    }

    public void setScale(float f) {
        if (f > 5.0f) {
            f = 5.0f;
        }
        this.mScale = f;
    }

    public void setMapMatrix(Matrix matrix) {
        this.mMapMatrix = matrix;
        invalidate();
    }

    public void refresh() {
        this.measuredWidth = 0.0f;
        invalidate();
    }

    public List<GlobalLocateReqBean.ArgsBean.SearchBoundaryBean.PolygonBean.PointsBean> getPoints() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(convertActualMapPosition(this.rectLeft, this.rectTop));
        arrayList.add(convertActualMapPosition(this.rectLeft, this.rectBottom));
        arrayList.add(convertActualMapPosition(this.rectRight, this.rectBottom));
        arrayList.add(convertActualMapPosition(this.rectRight, this.rectTop));
        return arrayList;
    }

    public GlobalLocateReqBean.ArgsBean.SearchBoundaryBean.PolygonBean.PointsBean convertActualMapPosition(float f, float f2) {
        GlobalLocateReqBean.ArgsBean.SearchBoundaryBean.PolygonBean.PointsBean pointsBean = new GlobalLocateReqBean.ArgsBean.SearchBoundaryBean.PolygonBean.PointsBean();
        try {
            Matrix matrix = new Matrix();
            getMatrix().invert(matrix);
            float[] fArr = new float[2];
            matrix.mapPoints(fArr, new float[]{f, f2});
            pointsBean.setX((double) (this.mOriginX + (fArr[0] * this.mResolution)));
            pointsBean.setY((double) (this.mOriginY + (fArr[1] * this.mResolution)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pointsBean;
    }
}
