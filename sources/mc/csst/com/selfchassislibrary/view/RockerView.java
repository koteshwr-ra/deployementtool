package mc.csst.com.selfchassislibrary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.core.internal.view.SupportMenu;
import mc.csst.com.selfchassislibrary.R;

public class RockerView extends View {
    private static final double ANGLE_0 = 0.0d;
    private static final double ANGLE_360 = 360.0d;
    private static final double ANGLE_4D_OF_0P = 0.0d;
    private static final double ANGLE_4D_OF_1P = 90.0d;
    private static final double ANGLE_4D_OF_2P = 180.0d;
    private static final double ANGLE_4D_OF_3P = 270.0d;
    private static final double ANGLE_8D_OF_0P = 22.5d;
    private static final double ANGLE_8D_OF_1P = 67.5d;
    private static final double ANGLE_8D_OF_2P = 112.5d;
    private static final double ANGLE_8D_OF_3P = 157.5d;
    private static final double ANGLE_8D_OF_4P = 202.5d;
    private static final double ANGLE_8D_OF_5P = 247.5d;
    private static final double ANGLE_8D_OF_6P = 292.5d;
    private static final double ANGLE_8D_OF_7P = 337.5d;
    private static final double ANGLE_HORIZONTAL_2D_OF_0P = 90.0d;
    private static final double ANGLE_HORIZONTAL_2D_OF_1P = 270.0d;
    private static final double ANGLE_ROTATE45_4D_OF_0P = 45.0d;
    private static final double ANGLE_ROTATE45_4D_OF_1P = 135.0d;
    private static final double ANGLE_ROTATE45_4D_OF_2P = 225.0d;
    private static final double ANGLE_ROTATE45_4D_OF_3P = 315.0d;
    private static final double ANGLE_VERTICAL_2D_OF_0P = 0.0d;
    private static final double ANGLE_VERTICAL_2D_OF_1P = 180.0d;
    private static final int AREA_BACKGROUND_MODE_COLOR = 1;
    private static final int AREA_BACKGROUND_MODE_DEFAULT = 3;
    private static final int AREA_BACKGROUND_MODE_PIC = 0;
    private static final int AREA_BACKGROUND_MODE_XML = 2;
    private static final int DEFAULT_ROCKER_RADIUS = 50;
    private static final int DEFAULT_SIZE = 400;
    private static final int ROCKER_BACKGROUND_MODE_COLOR = 5;
    private static final int ROCKER_BACKGROUND_MODE_DEFAULT = 7;
    private static final int ROCKER_BACKGROUND_MODE_PIC = 4;
    private static final int ROCKER_BACKGROUND_MODE_XML = 6;
    private static final String TAG = "RockerView";
    private int mAreaBackgroundMode = 3;
    private Paint mAreaBackgroundPaint;
    private Bitmap mAreaBitmap;
    private int mAreaColor;
    private int mAreaRadius;
    private CallBackMode mCallBackMode = CallBackMode.CALL_BACK_MODE_MOVE;
    private Point mCenterPoint;
    private DirectionMode mDirectionMode;
    private OnAngleChangeListener mOnAngleChangeListener;
    private OnShakeListener mOnShakeListener;
    private int mRockerBackgroundMode = 7;
    private Bitmap mRockerBitmap;
    private int mRockerColor;
    private Paint mRockerPaint;
    private Point mRockerPosition;
    private int mRockerRadius;
    private Direction tempDirection = Direction.DIRECTION_CENTER;

    public enum CallBackMode {
        CALL_BACK_MODE_MOVE,
        CALL_BACK_MODE_STATE_CHANGE
    }

    public enum Direction {
        DIRECTION_LEFT,
        DIRECTION_RIGHT,
        DIRECTION_UP,
        DIRECTION_DOWN,
        DIRECTION_UP_LEFT,
        DIRECTION_UP_RIGHT,
        DIRECTION_DOWN_LEFT,
        DIRECTION_DOWN_RIGHT,
        DIRECTION_CENTER
    }

    public enum DirectionMode {
        DIRECTION_2_HORIZONTAL,
        DIRECTION_2_VERTICAL,
        DIRECTION_4_ROTATE_0,
        DIRECTION_4_ROTATE_45,
        DIRECTION_8
    }

    public interface OnAngleChangeListener {
        void angle(double d, float f);

        void onFinish();

        void onStart();
    }

    public interface OnShakeListener {
        void direction(Direction direction);

        void onFinish();

        void onStart();
    }

    public RockerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initAttribute(context, attributeSet);
        if (isInEditMode()) {
            Log.e(TAG, "RockerView: isInEditMode");
        }
        Paint paint = new Paint();
        this.mAreaBackgroundPaint = paint;
        paint.setAntiAlias(true);
        Paint paint2 = new Paint();
        this.mRockerPaint = paint2;
        paint2.setAntiAlias(true);
        this.mCenterPoint = new Point();
        this.mRockerPosition = new Point();
    }

    private void initAttribute(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RockerView);
        Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.RockerView_areaBackground);
        if (drawable == null) {
            this.mAreaBackgroundMode = 3;
        } else if (drawable instanceof BitmapDrawable) {
            this.mAreaBitmap = ((BitmapDrawable) drawable).getBitmap();
            this.mAreaBackgroundMode = 0;
        } else if (drawable instanceof GradientDrawable) {
            this.mAreaBitmap = drawable2Bitmap(drawable);
            this.mAreaBackgroundMode = 2;
        } else if (drawable instanceof ColorDrawable) {
            this.mAreaColor = ((ColorDrawable) drawable).getColor();
            this.mAreaBackgroundMode = 1;
        } else {
            this.mAreaBackgroundMode = 3;
        }
        Drawable drawable2 = obtainStyledAttributes.getDrawable(R.styleable.RockerView_rockerBackground);
        if (drawable2 == null) {
            this.mRockerBackgroundMode = 7;
        } else if (drawable2 instanceof BitmapDrawable) {
            this.mRockerBitmap = ((BitmapDrawable) drawable2).getBitmap();
            this.mRockerBackgroundMode = 4;
        } else if (drawable2 instanceof GradientDrawable) {
            this.mRockerBitmap = drawable2Bitmap(drawable2);
            this.mRockerBackgroundMode = 6;
        } else if (drawable2 instanceof ColorDrawable) {
            this.mRockerColor = ((ColorDrawable) drawable2).getColor();
            this.mRockerBackgroundMode = 5;
        } else {
            this.mRockerBackgroundMode = 7;
        }
        this.mRockerRadius = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.RockerView_rockerRadius, 50);
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode != 1073741824) {
            size = 400;
        }
        if (mode2 != 1073741824) {
            size2 = 400;
        }
        setMeasuredDimension(size, size2);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int i = measuredWidth / 2;
        int i2 = measuredHeight / 2;
        this.mCenterPoint.set(i, i2);
        if (measuredWidth > measuredHeight) {
            i = i2;
        }
        this.mAreaRadius = i;
        if (this.mRockerPosition.x == 0 || this.mRockerPosition.y == 0) {
            this.mRockerPosition.set(this.mCenterPoint.x, this.mCenterPoint.y);
        }
        int i3 = this.mAreaBackgroundMode;
        if (i3 == 0 || 2 == i3) {
            canvas.drawBitmap(this.mAreaBitmap, new Rect(0, 0, this.mAreaBitmap.getWidth(), this.mAreaBitmap.getHeight()), new Rect(this.mCenterPoint.x - this.mAreaRadius, this.mCenterPoint.y - this.mAreaRadius, this.mCenterPoint.x + this.mAreaRadius, this.mCenterPoint.y + this.mAreaRadius), this.mAreaBackgroundPaint);
        } else if (1 == i3) {
            this.mAreaBackgroundPaint.setColor(this.mAreaColor);
            canvas.drawCircle((float) this.mCenterPoint.x, (float) this.mCenterPoint.y, (float) this.mAreaRadius, this.mAreaBackgroundPaint);
        } else {
            this.mAreaBackgroundPaint.setColor(-7829368);
            canvas.drawCircle((float) this.mCenterPoint.x, (float) this.mCenterPoint.y, (float) this.mAreaRadius, this.mAreaBackgroundPaint);
        }
        int i4 = this.mRockerBackgroundMode;
        if (4 == i4 || 6 == i4) {
            canvas.drawBitmap(this.mRockerBitmap, new Rect(0, 0, this.mRockerBitmap.getWidth(), this.mRockerBitmap.getHeight()), new Rect(this.mRockerPosition.x - this.mRockerRadius, this.mRockerPosition.y - this.mRockerRadius, this.mRockerPosition.x + this.mRockerRadius, this.mRockerPosition.y + this.mRockerRadius), this.mRockerPaint);
        } else if (5 == i4) {
            this.mRockerPaint.setColor(this.mRockerColor);
            canvas.drawCircle((float) this.mRockerPosition.x, (float) this.mRockerPosition.y, (float) this.mRockerRadius, this.mRockerPaint);
        } else {
            this.mRockerPaint.setColor(SupportMenu.CATEGORY_MASK);
            canvas.drawCircle((float) this.mRockerPosition.x, (float) this.mRockerPosition.y, (float) this.mRockerRadius, this.mRockerPaint);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000d, code lost:
        if (r0 != 3) goto L_0x0052;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            int r0 = r5.getAction()
            r1 = 1
            if (r0 == 0) goto L_0x0027
            if (r0 == r1) goto L_0x0010
            r2 = 2
            if (r0 == r2) goto L_0x002a
            r2 = 3
            if (r0 == r2) goto L_0x0010
            goto L_0x0052
        L_0x0010:
            r4.callBackFinish()
            r5.getX()
            r5.getY()
            android.graphics.Point r5 = r4.mCenterPoint
            int r5 = r5.x
            float r5 = (float) r5
            android.graphics.Point r0 = r4.mCenterPoint
            int r0 = r0.y
            float r0 = (float) r0
            r4.moveRocker(r5, r0)
            goto L_0x0052
        L_0x0027:
            r4.callBackStart()
        L_0x002a:
            float r0 = r5.getX()
            float r5 = r5.getY()
            android.graphics.Point r2 = r4.mCenterPoint
            android.graphics.Point r3 = new android.graphics.Point
            int r0 = (int) r0
            int r5 = (int) r5
            r3.<init>(r0, r5)
            int r5 = r4.mAreaRadius
            float r5 = (float) r5
            int r0 = r4.mRockerRadius
            float r0 = (float) r0
            android.graphics.Point r5 = r4.getRockerPositionPoint(r2, r3, r5, r0)
            r4.mRockerPosition = r5
            int r5 = r5.x
            float r5 = (float) r5
            android.graphics.Point r0 = r4.mRockerPosition
            int r0 = r0.y
            float r0 = (float) r0
            r4.moveRocker(r5, r0)
        L_0x0052:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: mc.csst.com.selfchassislibrary.view.RockerView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    private Point getRockerPositionPoint(Point point, Point point2, float f, float f2) {
        float f3 = (float) (point2.x - point.x);
        float f4 = (float) (point2.y - point.y);
        float sqrt = (float) Math.sqrt((double) ((f3 * f3) + (f4 * f4)));
        double acos = Math.acos((double) (f3 / sqrt)) * ((double) (point2.y < point.y ? -1 : 1));
        callBack(radian2Angle(acos), sqrt);
        if (sqrt + f2 <= f) {
            return point2;
        }
        double d = (double) (f - f2);
        return new Point((int) (((double) point.x) + (Math.cos(acos) * d)), (int) (((double) point.y) + (d * Math.sin(acos))));
    }

    private void moveRocker(float f, float f2) {
        this.mRockerPosition.set((int) f, (int) f2);
        invalidate();
    }

    private double radian2Angle(double d) {
        double round = (double) Math.round((d / 3.141592653589793d) * 180.0d);
        return round >= 0.0d ? round : round + ANGLE_360;
    }

    private Bitmap drawable2Bitmap(Drawable drawable) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        drawable.draw(canvas);
        return createBitmap;
    }

    private void callBackStart() {
        this.tempDirection = Direction.DIRECTION_CENTER;
        OnAngleChangeListener onAngleChangeListener = this.mOnAngleChangeListener;
        if (onAngleChangeListener != null) {
            onAngleChangeListener.onStart();
        }
        OnShakeListener onShakeListener = this.mOnShakeListener;
        if (onShakeListener != null) {
            onShakeListener.onStart();
        }
    }

    private void callBack(double d, float f) {
        double d2 = d;
        if (this.mOnAngleChangeListener != null) {
            float f2 = f / ((float) this.mRockerRadius);
            if (f2 > 1.0f) {
                f2 = 1.0f;
            }
            this.mOnAngleChangeListener.angle(d2, f2);
        }
        if (this.mOnShakeListener == null) {
            return;
        }
        if (CallBackMode.CALL_BACK_MODE_MOVE == this.mCallBackMode) {
            int i = AnonymousClass1.$SwitchMap$mc$csst$com$selfchassislibrary$view$RockerView$DirectionMode[this.mDirectionMode.ordinal()];
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            if (i == 5) {
                                if ((0.0d <= d2 && ANGLE_8D_OF_0P > d2) || (ANGLE_8D_OF_7P <= d2 && ANGLE_360 > d2)) {
                                    this.mOnShakeListener.direction(Direction.DIRECTION_RIGHT);
                                } else if (ANGLE_8D_OF_0P <= d2 && ANGLE_8D_OF_1P > d2) {
                                    this.mOnShakeListener.direction(Direction.DIRECTION_DOWN_RIGHT);
                                } else if (ANGLE_8D_OF_1P <= d2 && ANGLE_8D_OF_2P > d2) {
                                    this.mOnShakeListener.direction(Direction.DIRECTION_DOWN);
                                } else if (ANGLE_8D_OF_2P <= d2 && ANGLE_8D_OF_3P > d2) {
                                    this.mOnShakeListener.direction(Direction.DIRECTION_DOWN_LEFT);
                                } else if (ANGLE_8D_OF_3P <= d2 && ANGLE_8D_OF_4P > d2) {
                                    this.mOnShakeListener.direction(Direction.DIRECTION_LEFT);
                                } else if (ANGLE_8D_OF_4P <= d2 && ANGLE_8D_OF_5P > d2) {
                                    this.mOnShakeListener.direction(Direction.DIRECTION_UP_LEFT);
                                } else if (ANGLE_8D_OF_5P <= d2 && ANGLE_8D_OF_6P > d2) {
                                    this.mOnShakeListener.direction(Direction.DIRECTION_UP);
                                } else if (ANGLE_8D_OF_6P <= d2 && ANGLE_8D_OF_7P > d2) {
                                    this.mOnShakeListener.direction(Direction.DIRECTION_UP_RIGHT);
                                }
                            }
                        } else if ((0.0d <= d2 && ANGLE_ROTATE45_4D_OF_0P > d2) || (ANGLE_ROTATE45_4D_OF_3P <= d2 && ANGLE_360 > d2)) {
                            this.mOnShakeListener.direction(Direction.DIRECTION_RIGHT);
                        } else if (ANGLE_ROTATE45_4D_OF_0P <= d2 && ANGLE_ROTATE45_4D_OF_1P > d2) {
                            this.mOnShakeListener.direction(Direction.DIRECTION_DOWN);
                        } else if (ANGLE_ROTATE45_4D_OF_1P <= d2 && ANGLE_ROTATE45_4D_OF_2P > d2) {
                            this.mOnShakeListener.direction(Direction.DIRECTION_LEFT);
                        } else if (ANGLE_ROTATE45_4D_OF_2P <= d2 && ANGLE_ROTATE45_4D_OF_3P > d2) {
                            this.mOnShakeListener.direction(Direction.DIRECTION_UP);
                        }
                    } else if (0.0d <= d2 && 90.0d > d2) {
                        this.mOnShakeListener.direction(Direction.DIRECTION_DOWN_RIGHT);
                    } else if (90.0d <= d2 && 180.0d > d2) {
                        this.mOnShakeListener.direction(Direction.DIRECTION_DOWN_LEFT);
                    } else if (180.0d <= d2 && 270.0d > d2) {
                        this.mOnShakeListener.direction(Direction.DIRECTION_UP_LEFT);
                    } else if (270.0d <= d2 && ANGLE_360 > d2) {
                        this.mOnShakeListener.direction(Direction.DIRECTION_UP_RIGHT);
                    }
                } else if (0.0d <= d2 && 180.0d > d2) {
                    this.mOnShakeListener.direction(Direction.DIRECTION_DOWN);
                } else if (180.0d <= d2 && ANGLE_360 > d2) {
                    this.mOnShakeListener.direction(Direction.DIRECTION_UP);
                }
            } else if ((0.0d <= d2 && 90.0d > d2) || (270.0d <= d2 && ANGLE_360 > d2)) {
                this.mOnShakeListener.direction(Direction.DIRECTION_RIGHT);
            } else if (90.0d <= d2 && 270.0d > d2) {
                this.mOnShakeListener.direction(Direction.DIRECTION_LEFT);
            }
        } else if (CallBackMode.CALL_BACK_MODE_STATE_CHANGE == this.mCallBackMode) {
            int i2 = AnonymousClass1.$SwitchMap$mc$csst$com$selfchassislibrary$view$RockerView$DirectionMode[this.mDirectionMode.ordinal()];
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        if (i2 != 4) {
                            if (i2 == 5) {
                                if (((0.0d <= d2 && ANGLE_8D_OF_0P > d2) || (ANGLE_8D_OF_7P <= d2 && ANGLE_360 > d2)) && this.tempDirection != Direction.DIRECTION_RIGHT) {
                                    this.tempDirection = Direction.DIRECTION_RIGHT;
                                    this.mOnShakeListener.direction(Direction.DIRECTION_RIGHT);
                                } else if (ANGLE_8D_OF_0P <= d2 && ANGLE_8D_OF_1P > d2 && this.tempDirection != Direction.DIRECTION_DOWN_RIGHT) {
                                    this.tempDirection = Direction.DIRECTION_DOWN_RIGHT;
                                    this.mOnShakeListener.direction(Direction.DIRECTION_DOWN_RIGHT);
                                } else if (ANGLE_8D_OF_1P <= d2 && ANGLE_8D_OF_2P > d2 && this.tempDirection != Direction.DIRECTION_DOWN) {
                                    this.tempDirection = Direction.DIRECTION_DOWN;
                                    this.mOnShakeListener.direction(Direction.DIRECTION_DOWN);
                                } else if (ANGLE_8D_OF_2P <= d2 && ANGLE_8D_OF_3P > d2 && this.tempDirection != Direction.DIRECTION_DOWN_LEFT) {
                                    this.tempDirection = Direction.DIRECTION_DOWN_LEFT;
                                    this.mOnShakeListener.direction(Direction.DIRECTION_DOWN_LEFT);
                                } else if (ANGLE_8D_OF_3P <= d2 && ANGLE_8D_OF_4P > d2 && this.tempDirection != Direction.DIRECTION_LEFT) {
                                    this.tempDirection = Direction.DIRECTION_LEFT;
                                    this.mOnShakeListener.direction(Direction.DIRECTION_LEFT);
                                } else if (ANGLE_8D_OF_4P <= d2 && ANGLE_8D_OF_5P > d2 && this.tempDirection != Direction.DIRECTION_UP_LEFT) {
                                    this.tempDirection = Direction.DIRECTION_UP_LEFT;
                                    this.mOnShakeListener.direction(Direction.DIRECTION_UP_LEFT);
                                } else if (ANGLE_8D_OF_5P <= d2 && ANGLE_8D_OF_6P > d2 && this.tempDirection != Direction.DIRECTION_UP) {
                                    this.tempDirection = Direction.DIRECTION_UP;
                                    this.mOnShakeListener.direction(Direction.DIRECTION_UP);
                                } else if (ANGLE_8D_OF_6P <= d2 && ANGLE_8D_OF_7P > d2 && this.tempDirection != Direction.DIRECTION_UP_RIGHT) {
                                    this.tempDirection = Direction.DIRECTION_UP_RIGHT;
                                    this.mOnShakeListener.direction(Direction.DIRECTION_UP_RIGHT);
                                }
                            }
                        } else if (((0.0d <= d2 && ANGLE_ROTATE45_4D_OF_0P > d2) || (ANGLE_ROTATE45_4D_OF_3P <= d2 && ANGLE_360 > d2)) && this.tempDirection != Direction.DIRECTION_RIGHT) {
                            this.tempDirection = Direction.DIRECTION_RIGHT;
                            this.mOnShakeListener.direction(Direction.DIRECTION_RIGHT);
                        } else if (ANGLE_ROTATE45_4D_OF_0P <= d2 && ANGLE_ROTATE45_4D_OF_1P > d2 && this.tempDirection != Direction.DIRECTION_DOWN) {
                            this.tempDirection = Direction.DIRECTION_DOWN;
                            this.mOnShakeListener.direction(Direction.DIRECTION_DOWN);
                        } else if (ANGLE_ROTATE45_4D_OF_1P <= d2 && ANGLE_ROTATE45_4D_OF_2P > d2 && this.tempDirection != Direction.DIRECTION_LEFT) {
                            this.tempDirection = Direction.DIRECTION_LEFT;
                            this.mOnShakeListener.direction(Direction.DIRECTION_LEFT);
                        } else if (ANGLE_ROTATE45_4D_OF_2P <= d2 && ANGLE_ROTATE45_4D_OF_3P > d2 && this.tempDirection != Direction.DIRECTION_UP) {
                            this.tempDirection = Direction.DIRECTION_UP;
                            this.mOnShakeListener.direction(Direction.DIRECTION_UP);
                        }
                    } else if (0.0d <= d2 && 90.0d > d2 && this.tempDirection != Direction.DIRECTION_DOWN_RIGHT) {
                        this.tempDirection = Direction.DIRECTION_DOWN_RIGHT;
                        this.mOnShakeListener.direction(Direction.DIRECTION_DOWN_RIGHT);
                    } else if (90.0d <= d2 && 180.0d > d2 && this.tempDirection != Direction.DIRECTION_DOWN_LEFT) {
                        this.tempDirection = Direction.DIRECTION_DOWN_LEFT;
                        this.mOnShakeListener.direction(Direction.DIRECTION_DOWN_LEFT);
                    } else if (180.0d <= d2 && 270.0d > d2 && this.tempDirection != Direction.DIRECTION_UP_LEFT) {
                        this.tempDirection = Direction.DIRECTION_UP_LEFT;
                        this.mOnShakeListener.direction(Direction.DIRECTION_UP_LEFT);
                    } else if (270.0d <= d2 && ANGLE_360 > d2 && this.tempDirection != Direction.DIRECTION_UP_RIGHT) {
                        this.tempDirection = Direction.DIRECTION_UP_RIGHT;
                        this.mOnShakeListener.direction(Direction.DIRECTION_UP_RIGHT);
                    }
                } else if (0.0d <= d2 && 180.0d > d2 && this.tempDirection != Direction.DIRECTION_DOWN) {
                    this.tempDirection = Direction.DIRECTION_DOWN;
                    this.mOnShakeListener.direction(Direction.DIRECTION_DOWN);
                } else if (180.0d <= d2 && ANGLE_360 > d2 && this.tempDirection != Direction.DIRECTION_UP) {
                    this.tempDirection = Direction.DIRECTION_UP;
                    this.mOnShakeListener.direction(Direction.DIRECTION_UP);
                }
            } else if (((0.0d <= d2 && 90.0d > d2) || (270.0d <= d2 && ANGLE_360 > d2)) && this.tempDirection != Direction.DIRECTION_RIGHT) {
                this.tempDirection = Direction.DIRECTION_RIGHT;
                this.mOnShakeListener.direction(Direction.DIRECTION_RIGHT);
            } else if (90.0d <= d2 && 270.0d > d2 && this.tempDirection != Direction.DIRECTION_LEFT) {
                this.tempDirection = Direction.DIRECTION_LEFT;
                this.mOnShakeListener.direction(Direction.DIRECTION_LEFT);
            }
        }
    }

    /* renamed from: mc.csst.com.selfchassislibrary.view.RockerView$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$mc$csst$com$selfchassislibrary$view$RockerView$DirectionMode;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                mc.csst.com.selfchassislibrary.view.RockerView$DirectionMode[] r0 = mc.csst.com.selfchassislibrary.view.RockerView.DirectionMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$mc$csst$com$selfchassislibrary$view$RockerView$DirectionMode = r0
                mc.csst.com.selfchassislibrary.view.RockerView$DirectionMode r1 = mc.csst.com.selfchassislibrary.view.RockerView.DirectionMode.DIRECTION_2_HORIZONTAL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$mc$csst$com$selfchassislibrary$view$RockerView$DirectionMode     // Catch:{ NoSuchFieldError -> 0x001d }
                mc.csst.com.selfchassislibrary.view.RockerView$DirectionMode r1 = mc.csst.com.selfchassislibrary.view.RockerView.DirectionMode.DIRECTION_2_VERTICAL     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$mc$csst$com$selfchassislibrary$view$RockerView$DirectionMode     // Catch:{ NoSuchFieldError -> 0x0028 }
                mc.csst.com.selfchassislibrary.view.RockerView$DirectionMode r1 = mc.csst.com.selfchassislibrary.view.RockerView.DirectionMode.DIRECTION_4_ROTATE_0     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$mc$csst$com$selfchassislibrary$view$RockerView$DirectionMode     // Catch:{ NoSuchFieldError -> 0x0033 }
                mc.csst.com.selfchassislibrary.view.RockerView$DirectionMode r1 = mc.csst.com.selfchassislibrary.view.RockerView.DirectionMode.DIRECTION_4_ROTATE_45     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$mc$csst$com$selfchassislibrary$view$RockerView$DirectionMode     // Catch:{ NoSuchFieldError -> 0x003e }
                mc.csst.com.selfchassislibrary.view.RockerView$DirectionMode r1 = mc.csst.com.selfchassislibrary.view.RockerView.DirectionMode.DIRECTION_8     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: mc.csst.com.selfchassislibrary.view.RockerView.AnonymousClass1.<clinit>():void");
        }
    }

    private void callBackFinish() {
        this.tempDirection = Direction.DIRECTION_CENTER;
        OnAngleChangeListener onAngleChangeListener = this.mOnAngleChangeListener;
        if (onAngleChangeListener != null) {
            onAngleChangeListener.onFinish();
        }
        OnShakeListener onShakeListener = this.mOnShakeListener;
        if (onShakeListener != null) {
            onShakeListener.onFinish();
        }
    }

    public void setCallBackMode(CallBackMode callBackMode) {
        this.mCallBackMode = callBackMode;
    }

    public void setOnAngleChangeListener(OnAngleChangeListener onAngleChangeListener) {
        this.mOnAngleChangeListener = onAngleChangeListener;
    }

    public void setOnShakeListener(DirectionMode directionMode, OnShakeListener onShakeListener) {
        this.mDirectionMode = directionMode;
        this.mOnShakeListener = onShakeListener;
    }
}
