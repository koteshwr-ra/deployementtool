package mc.csst.com.selfchassis.utils.view.map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.blankj.utilcode.util.AdaptScreenUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.ciot.base.util.MyLogUtils;
import com.ciot.sentrymove.R;
import mc.csst.com.selfchassis.utils.MatrixUtils;
import mc.csst.com.selfchassis.utils.MyToastUtils;
import mc.csst.com.selfchassislibrary.bean.EularBean;
import mc.csst.com.selfchassislibrary.bean.msg.MarkerOperationGetMarkersResponseBean;
import mc.csst.com.selfchassislibrary.utils.ConvertorUtils;

public class PointView extends View {
    private static final float CLICK_OFFSET = 5.0f;
    private static final String TAG = PointView.class.getSimpleName();
    private boolean hasTitleShow = false;
    private float iconHeight;
    private float iconWidth;
    private Bitmap mBitmap;
    private Paint mDestinationPaint;
    private Path mDestinationPath = new Path();
    private Matrix mImageMatrix;
    private float mOriginX;
    private float mOriginY;
    private Paint mPaint;
    private float mResolution;
    private float mScalePercentage = 0.0f;
    private int mTextSize;
    private OnViewClick mViewClick;
    private MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean mWaypointsBean;
    private float targetX;
    private float targetY;

    public interface OnViewClick {
        void onClick(MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean waypointsBean);
    }

    public PointView(Context context) {
        super(context);
        init();
    }

    public PointView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        Paint paint = new Paint(1);
        this.mPaint = paint;
        paint.setColor(-8158333);
        this.mPaint.setTextSize(42.0f);
        this.mPaint.setTextAlign(Paint.Align.CENTER);
        Paint paint2 = new Paint(1);
        this.mDestinationPaint = paint2;
        paint2.setColor(-8158333);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        try {
            super.onDraw(canvas);
            if (this.mWaypointsBean != null && this.mImageMatrix != null) {
                if (this.mResolution != 0.0f) {
                    this.mWaypointsBean.getName();
                    this.mWaypointsBean.getBehavior_code();
                    float x = this.mWaypointsBean.getPose().getPosition().getX();
                    float y = this.mWaypointsBean.getPose().getPosition().getY();
                    float f = (x - this.mOriginX) / this.mResolution;
                    float f2 = (y - this.mOriginY) / this.mResolution;
                    this.targetX = MatrixUtils.getMatrixScaleX(f, this.mImageMatrix);
                    this.targetY = MatrixUtils.getMatrixScaleY(f2, this.mImageMatrix);
                    String str = TAG;
                    MyLogUtils.Logd(str, "setBjd x:" + f + "  y:" + f2 + " mOriginX:" + this.mOriginX + " mOriginY:" + this.mOriginY + " mResolution:" + this.mResolution + "; targetX:" + this.targetX + "; targetY:" + this.targetY);
                    drawIcon(canvas);
                    drawText(canvas);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean onPointTouchEvent(MotionEvent motionEvent) {
        MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean waypointsBean;
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (motionEvent.getAction() == 1 && (waypointsBean = this.mWaypointsBean) != null) {
            float f = (float) x;
            float f2 = this.targetX;
            float f3 = f2 - CLICK_OFFSET;
            float f4 = this.iconWidth;
            if (f > f3 - (f4 / 2.0f) && f < f2 + (f4 / 2.0f) + CLICK_OFFSET) {
                float f5 = (float) y;
                float f6 = this.targetY;
                float f7 = f6 - CLICK_OFFSET;
                float f8 = this.iconHeight;
                if (f5 > f7 - (f8 / 2.0f) && f5 < f6 + (f8 / 2.0f) + CLICK_OFFSET) {
                    OnViewClick onViewClick = this.mViewClick;
                    if (onViewClick != null) {
                        onViewClick.onClick(waypointsBean);
                    }
                    String name = this.mWaypointsBean.getName();
                    if (!TextUtils.isEmpty(name)) {
                        MyToastUtils.showShort(getContext(), name);
                    }
                }
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    private void drawIcon(Canvas canvas) {
        int i;
        int i2;
        Canvas canvas2 = canvas;
        this.iconWidth = 16.0f;
        int behavior_code = this.mWaypointsBean.getBehavior_code();
        if (behavior_code == 3) {
            i2 = R.drawable.shape_point_call_ladder;
            i = getResources().getColor(R.color.clr_04EEFD);
        } else if (behavior_code == 4) {
            i2 = R.drawable.shape_point_take_ladder;
            i = getResources().getColor(R.color.clr_FF2F5E);
        } else if (behavior_code == 5) {
            i2 = R.drawable.shape_point_wait;
            i = getResources().getColor(R.color.clr_FDE405);
        } else if (behavior_code == 7) {
            i2 = R.drawable.shape_point_turnstile;
            i = getResources().getColor(R.color.clr_FF7F27);
        } else if (behavior_code == 8) {
            i2 = R.drawable.shape_point_access_control;
            i = getResources().getColor(R.color.clr_E271FF);
        } else if (behavior_code == 11) {
            i2 = R.drawable.shape_point_charge;
            i = getResources().getColor(R.color.clr_02C769);
        } else if (behavior_code == 20) {
            i2 = R.mipmap.icon_label;
            this.iconWidth = 36.0f;
            i = -1;
        } else if (behavior_code != 50) {
            i2 = R.drawable.shape_point_normal;
            i = getResources().getColor(R.color.clr_0560FD);
        } else {
            i2 = R.drawable.shape_point_stop;
            i = getResources().getColor(R.color.clr_C2F300);
        }
        float f = this.mScalePercentage;
        if (f > 50.0f) {
            this.mTextSize = AdaptScreenUtils.pt2Px(66.0f);
            this.iconWidth += 50.0f;
        } else if (f > 1.5f) {
            this.mTextSize = AdaptScreenUtils.pt2Px((float) (((int) f) + 16));
            this.iconWidth += (float) ((int) this.mScalePercentage);
        } else {
            this.mTextSize = AdaptScreenUtils.pt2Px(16.0f);
        }
        this.mPaint.setTextSize((float) this.mTextSize);
        this.mDestinationPaint.setColor(i);
        Bitmap bitmap = getBitmap(getContext(), i2);
        this.mBitmap = bitmap;
        Bitmap scale = ImageUtils.scale(bitmap, AdaptScreenUtils.pt2Px(this.iconWidth), AdaptScreenUtils.pt2Px(this.iconWidth), true);
        this.mBitmap = scale;
        if (scale != null) {
            EularBean quaternion2eular = ConvertorUtils.quaternion2eular(this.mWaypointsBean.getPose().getOrientation().getW(), this.mWaypointsBean.getPose().getOrientation().getX(), this.mWaypointsBean.getPose().getOrientation().getY(), this.mWaypointsBean.getPose().getOrientation().getZ());
            float y = quaternion2eular.getY() * -1.0f;
            MyLogUtils.Logd(TAG, this.mWaypointsBean.getName() + "eularBean：" + quaternion2eular.toString());
            if (i != -1) {
                double d = ((double) (180.0f * y)) / 3.141592653589793d;
                this.iconWidth = (float) this.mBitmap.getWidth();
                this.iconHeight = (float) this.mBitmap.getHeight();
                double d2 = (double) y;
                float cos = this.targetX + ((((float) Math.cos(d2)) * this.iconWidth) / 1.000001f);
                float sin = this.targetY + ((((float) Math.sin(d2)) * this.iconHeight) / 1.000001f);
                this.mDestinationPath.reset();
                this.mDestinationPath.setFillType(Path.FillType.WINDING);
                this.mDestinationPath.moveTo(cos, sin);
                double d3 = ((d + 50.0d) * 3.141592653589793d) / 180.0d;
                this.mDestinationPath.lineTo(this.targetX + ((((float) Math.cos(d3)) * this.iconWidth) / 2.0f), this.targetY + ((((float) Math.sin(d3)) * this.iconWidth) / 2.0f));
                double d4 = (d * 3.141592653589793d) / 180.0d;
                this.mDestinationPath.lineTo(this.targetX + ((((float) Math.cos(d4)) * this.iconWidth) / 10.0f), this.targetY + ((((float) Math.sin(d4)) * this.iconHeight) / 10.0f));
                double d5 = ((d - 50.0d) * 3.141592653589793d) / 180.0d;
                this.mDestinationPath.lineTo(this.targetX + ((((float) Math.cos(d5)) * this.iconWidth) / 2.0f), this.targetY + ((((float) Math.sin(d5)) * this.iconWidth) / 2.0f));
                this.mDestinationPath.close();
                canvas2.drawPath(this.mDestinationPath, this.mDestinationPaint);
            } else {
                Bitmap rotaingImageView = rotaingImageView(((int) (((double) (y * 180.0f)) / 3.141592653589793d)) + 135, this.mBitmap);
                this.mBitmap = rotaingImageView;
                this.iconWidth = (float) rotaingImageView.getWidth();
                this.iconHeight = (float) this.mBitmap.getHeight();
            }
            if (this.mBitmap != null) {
                Log.d("test", "iconWidth:" + this.iconWidth + "；iconHeight:" + this.iconHeight);
                canvas2.drawBitmap(this.mBitmap, this.targetX - (this.iconWidth / 2.0f), this.targetY - (this.iconHeight / 2.0f), this.mPaint);
                Bitmap bitmap2 = this.mBitmap;
                if (bitmap2 != null && !bitmap2.isRecycled()) {
                    this.mBitmap.recycle();
                }
            }
        }
    }

    private void drawText(Canvas canvas) {
        if (this.mBitmap != null && this.hasTitleShow) {
            String name = this.mWaypointsBean.getName();
            Paint.FontMetrics fontMetrics = this.mPaint.getFontMetrics();
            float height = this.targetY + (((fontMetrics.bottom - fontMetrics.top) / 2.0f) - fontMetrics.bottom) + ((float) this.mBitmap.getHeight()) + ((float) AdaptScreenUtils.pt2Px(CLICK_OFFSET));
            if (name.length() > 30) {
                name = name.substring(0, 30) + "...";
            }
            canvas.drawText(name, this.targetX, height, this.mPaint);
        }
    }

    private static Bitmap getBitmap(Context context, int i) {
        if (Build.VERSION.SDK_INT <= 21) {
            return BitmapFactory.decodeResource(context.getResources(), i);
        }
        Drawable drawable = context.getDrawable(i);
        Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    public Bitmap rotaingImageView(int i, Bitmap bitmap) {
        Matrix matrix = new Matrix();
        bitmap.getWidth();
        bitmap.getHeight();
        matrix.postRotate((float) i);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (!(createBitmap == bitmap || bitmap == null || bitmap.isRecycled())) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public void setScalePercentage(float f) {
        this.mScalePercentage = f;
        if (f > 1.5f) {
            this.hasTitleShow = true;
        } else {
            this.hasTitleShow = false;
        }
        invalidate();
    }

    public void setOnViewClick(OnViewClick onViewClick) {
        this.mViewClick = onViewClick;
    }

    public void setPointInfo(MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean waypointsBean) {
        this.mWaypointsBean = waypointsBean;
    }

    public void setExtraInfo(float f, float f2, float f3, Matrix matrix) {
        this.mOriginX = f;
        this.mOriginY = f2;
        this.mResolution = f3;
        this.mImageMatrix = matrix;
    }
}
