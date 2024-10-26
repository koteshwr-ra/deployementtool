package mc.csst.com.selfchassis.utils.view.map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.blankj.utilcode.util.AdaptScreenUtils;
import com.ciot.base.util.ContextUtil;
import com.ciot.base.util.MyLogUtils;
import com.ciot.sentrymove.R;
import java.util.ArrayList;
import java.util.List;
import mc.csst.com.selfchassis.utils.bean.UndoRedoBean;
import mc.csst.com.selfchassis.utils.bean.UndoRedoLinkedList;
import mc.csst.com.selfchassislibrary.bean.MapInfoBean;
import mc.csst.com.selfchassislibrary.bean.msg.SetVirtualWallsBean;
import mc.csst.com.selfchassislibrary.bean.msg.VirtualWallOperationGetWallsResponseBean;
import mc.csst.com.selfchassislibrary.chassis.SelfChassis;
import mc.csst.com.selfchassislibrary.utils.ConvertorUtils;
import mc.csst.com.selfchassislibrary.utils.OtherUtils;

public class VirtualWallView extends View {
    private static final String TAG = VirtualWallView.class.getSimpleName();
    private int clickType;
    private Bitmap delBitmap;
    private int delBitmapRadius;
    private RectF dottedRect;
    private float downX;
    private float downY;
    private float mInnerRadius;
    private float mOriginX;
    private float mOriginY;
    private float mOuterRadius;
    private final Paint mPaint;
    private float mResolution;
    private float mScale;
    private int mShapeType;
    private UndoRedoLinkedList<UndoRedoBean> mUndoRedoLinkedList;
    private VirtualWallOperationGetWallsResponseBean.ValuesBean.VwallsBean.WallsBean mVirtualWall;
    private float mapHeight;
    private Matrix mapMatrix;
    private float mapWidth;
    boolean newAdd;
    private final Paint rPaint;
    private List<VirtualWallOperationGetWallsResponseBean.ValuesBean.VwallsBean.WallsBean> wallsBeans;

    public VirtualWallView(Context context) {
        this(context, (AttributeSet) null);
    }

    public VirtualWallView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.wallsBeans = new ArrayList();
        this.clickType = -1;
        this.mShapeType = 0;
        this.mInnerRadius = 9.0f;
        this.mOuterRadius = 10.0f;
        this.mScale = 1.0f;
        setBackgroundColor(0);
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setStrokeWidth(2.0f);
        this.mPaint.setColor(ContextUtil.getContext().getResources().getColor(R.color.clr_FF8329));
        this.mPaint.setAntiAlias(true);
        Paint paint2 = new Paint();
        this.rPaint = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        this.rPaint.setStrokeWidth(2.0f);
        this.rPaint.setColor(ContextUtil.getContext().getResources().getColor(R.color.clr_666666));
        this.rPaint.setAntiAlias(true);
        this.rPaint.setPathEffect(new DashPathEffect(new float[]{5.0f, 5.0f}, 0.0f));
        this.mUndoRedoLinkedList = new UndoRedoLinkedList<>(null);
    }

    public Bitmap getDelBitmap() {
        Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_virtual_wall_delete);
        int width = decodeResource.getWidth();
        int height = decodeResource.getHeight();
        int pt2Px = AdaptScreenUtils.pt2Px(64.0f / this.mScale);
        Matrix matrix = new Matrix();
        matrix.postScale(((float) pt2Px) / ((float) width), ((float) AdaptScreenUtils.pt2Px(64.0f / this.mScale)) / ((float) height));
        return Bitmap.createBitmap(decodeResource, 0, 0, width, height, matrix, true);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.setMatrix(this.mapMatrix);
        drawVirtualWall(canvas);
        if (this.dottedRect != null) {
            Bitmap delBitmap2 = getDelBitmap();
            this.delBitmap = delBitmap2;
            this.delBitmapRadius = delBitmap2.getWidth() / 2;
            canvas.drawRect(this.dottedRect, this.rPaint);
            canvas.drawBitmap(this.delBitmap, this.dottedRect.right - ((float) this.delBitmapRadius), this.dottedRect.bottom - ((float) this.delBitmapRadius), this.rPaint);
        }
    }

    private void drawVirtualWall(Canvas canvas) {
        List<VirtualWallOperationGetWallsResponseBean.ValuesBean.VwallsBean.WallsBean> list = this.wallsBeans;
        if (list != null) {
            VirtualWallOperationGetWallsResponseBean.ValuesBean.VwallsBean.WallsBean wallsBean = this.mVirtualWall;
            if (wallsBean != null && list.contains(wallsBean)) {
                this.wallsBeans.remove(this.mVirtualWall);
                this.wallsBeans.add(this.mVirtualWall);
            }
            for (VirtualWallOperationGetWallsResponseBean.ValuesBean.VwallsBean.WallsBean next : this.wallsBeans) {
                if (next != null) {
                    float[] fArr = {next.getStart_x(), next.getStart_y(), next.getEnd_x(), next.getEnd_y()};
                    if (next == this.mVirtualWall && this.mShapeType == 1 && this.dottedRect != null) {
                        this.mPaint.setColor(ContextUtil.getContext().getResources().getColor(R.color.clr_0560FD));
                    } else {
                        this.mPaint.setColor(ContextUtil.getContext().getResources().getColor(R.color.clr_FF8329));
                    }
                    canvas.drawLines(fArr, this.mPaint);
                    if (this.mShapeType == 1) {
                        this.mPaint.setStyle(Paint.Style.FILL);
                        this.mPaint.setColor(ContextUtil.getContext().getResources().getColor(R.color.white));
                        canvas.drawCircle(fArr[0], fArr[1], this.mInnerRadius, this.mPaint);
                        canvas.drawCircle(fArr[2], fArr[3], this.mInnerRadius, this.mPaint);
                        this.mPaint.setStyle(Paint.Style.STROKE);
                        if (next != this.mVirtualWall || this.dottedRect == null) {
                            this.mPaint.setColor(ContextUtil.getContext().getResources().getColor(R.color.clr_FF8329));
                        } else {
                            this.mPaint.setColor(ContextUtil.getContext().getResources().getColor(R.color.clr_0560FD));
                        }
                        canvas.drawCircle(fArr[0], fArr[1], this.mOuterRadius, this.mPaint);
                        canvas.drawCircle(fArr[2], fArr[3], this.mOuterRadius, this.mPaint);
                    }
                }
            }
        }
    }

    public void setScale(float f) {
        if (f > 5.0f) {
            f = 5.0f;
        }
        this.mScale = f;
        this.mOuterRadius = 10.0f / f;
        this.mInnerRadius = 9.0f / f;
    }

    public boolean onWallTouchEvent(MotionEvent motionEvent) {
        if (this.mapMatrix == null) {
            return true;
        }
        float[] fArr = {motionEvent.getX(), motionEvent.getY()};
        Matrix matrix = new Matrix();
        this.mapMatrix.invert(matrix);
        matrix.mapPoints(fArr);
        float f = fArr[0];
        float f2 = fArr[1];
        int action = motionEvent.getAction();
        if (action == 0) {
            this.downX = f;
            this.downY = f2;
            RectF rectF = this.dottedRect;
            if (rectF != null && isRangeOfView(rectF.right, this.dottedRect.bottom, f, f2)) {
                this.clickType = 3;
            } else {
                VirtualWallOperationGetWallsResponseBean.ValuesBean.VwallsBean.WallsBean clickVirtualWall = getClickVirtualWall(f, f2);
                if (clickVirtualWall != null) {
                    this.mVirtualWall = clickVirtualWall;
                } else if (f >= 0.0f && f2 >= 0.0f && f <= this.mapWidth && f2 <= this.mapHeight) {
                    this.dottedRect = null;
                    this.clickType = 0;
                    VirtualWallOperationGetWallsResponseBean.ValuesBean.VwallsBean.WallsBean wallsBean = new VirtualWallOperationGetWallsResponseBean.ValuesBean.VwallsBean.WallsBean();
                    this.mVirtualWall = wallsBean;
                    wallsBean.setStart_x(f);
                    this.mVirtualWall.setStart_y(f2);
                    this.mVirtualWall.setEnd_x(f);
                    this.mVirtualWall.setEnd_y(f2);
                    this.newAdd = false;
                }
            }
        } else if (action == 1) {
            float distanceBetween2Points = ConvertorUtils.getDistanceBetween2Points(this.downX, this.downY, f, f2);
            int i = this.clickType;
            if (i != 0) {
                if (i == 1 || i == 2) {
                    this.dottedRect = getRect(this.mVirtualWall);
                    if (distanceBetween2Points > 5.0f) {
                        addVirtualWall(this.mVirtualWall);
                    }
                } else if (i == 3) {
                    this.wallsBeans.remove(this.mVirtualWall);
                    this.dottedRect = null;
                    delVirtualWall(this.mVirtualWall.getWall_id());
                }
            } else if (distanceBetween2Points > 20.0f) {
                this.dottedRect = getRect(this.mVirtualWall);
                this.mVirtualWall.setWall_id(OtherUtils.getUUID());
                addVirtualWall(this.mVirtualWall);
            }
            this.downX = 0.0f;
            this.downY = 0.0f;
            invalidate();
        } else if (action != 2 || f < 0.0f || f2 < 0.0f || f > this.mapWidth || f2 > this.mapHeight) {
            return true;
        } else {
            int i2 = this.clickType;
            if (i2 == 0) {
                this.mVirtualWall.setEnd_x(f);
                this.mVirtualWall.setEnd_y(f2);
                if (ConvertorUtils.getDistanceBetween2Points(this.downX, this.downY, f, f2) > 20.0f && !this.newAdd) {
                    this.wallsBeans.add(0, this.mVirtualWall);
                    this.newAdd = true;
                }
            } else if (i2 == 1) {
                this.mVirtualWall.setStart_x(f);
                this.mVirtualWall.setStart_y(f2);
                this.dottedRect = getRect(this.mVirtualWall);
            } else if (i2 == 2) {
                this.mVirtualWall.setEnd_x(f);
                this.mVirtualWall.setEnd_y(f2);
                this.dottedRect = getRect(this.mVirtualWall);
            }
            invalidate();
        }
        return true;
    }

    public void cleanRect() {
        this.dottedRect = null;
        invalidate();
    }

    public void setMapMatrix(Matrix matrix) {
        this.mapMatrix = matrix;
        invalidate();
    }

    public void setWallsBeans(List<VirtualWallOperationGetWallsResponseBean.ValuesBean.VwallsBean.WallsBean> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        for (VirtualWallOperationGetWallsResponseBean.ValuesBean.VwallsBean.WallsBean next : list) {
            float start_x = (next.getStart_x() - this.mOriginX) / this.mResolution;
            float start_y = (next.getStart_y() - this.mOriginY) / this.mResolution;
            float end_x = (next.getEnd_x() - this.mOriginX) / this.mResolution;
            next.setStart_x(start_x);
            next.setStart_y(start_y);
            next.setEnd_x(end_x);
            next.setEnd_y((next.getEnd_y() - this.mOriginY) / this.mResolution);
        }
        this.wallsBeans = list;
        invalidate();
    }

    public void setMapInfo(MapInfoBean mapInfoBean, float f, float f2) {
        String str = TAG;
        MyLogUtils.Logd(str, "setMapInfo :" + mapInfoBean.toString());
        this.mOriginX = mapInfoBean.getX();
        this.mOriginY = mapInfoBean.getY();
        this.mResolution = mapInfoBean.getR();
        this.mapWidth = f;
        this.mapHeight = f2;
    }

    public void addVirtualWall(VirtualWallOperationGetWallsResponseBean.ValuesBean.VwallsBean.WallsBean wallsBean) {
        float start_x = wallsBean.getStart_x();
        float start_y = wallsBean.getStart_y();
        float end_x = wallsBean.getEnd_x();
        float end_y = wallsBean.getEnd_y();
        Matrix matrix = new Matrix();
        float[] fArr = new float[2];
        float[] fArr2 = new float[2];
        if (getMatrix().invert(matrix)) {
            matrix.mapPoints(fArr, new float[]{start_x, start_y});
            matrix.mapPoints(fArr2, new float[]{end_x, end_y});
            SetVirtualWallsBean.MsgBean.WallsBean wallsBean2 = new SetVirtualWallsBean.MsgBean.WallsBean();
            float f = this.mOriginX;
            float f2 = fArr[0];
            float f3 = this.mResolution;
            float f4 = this.mOriginY;
            float f5 = (fArr[1] * f3) + f4;
            wallsBean2.setStart_x((f2 * f3) + f);
            wallsBean2.setStart_y(f5);
            wallsBean2.setEnd_x(f + (fArr2[0] * f3));
            wallsBean2.setEnd_y(f4 + (fArr2[1] * f3));
            wallsBean2.setWall_id(wallsBean.getWall_id());
            String str = TAG;
            MyLogUtils.Logd(str, "addVirtualWall wall:" + wallsBean2.toString());
            SelfChassis.getInstance().appendVirtualWalls(wallsBean2);
        }
    }

    public void delVirtualWall(String str) {
        SelfChassis.getInstance().serviceVirtualWallManagerDeletePoie(str);
    }

    private boolean isRangeOfView(float f, float f2, float f3, float f4) {
        float pt2Px = ((float) AdaptScreenUtils.pt2Px(30.0f)) / this.mScale;
        return f3 > f - pt2Px && f3 < f + pt2Px && f4 > f2 - pt2Px && f4 < f2 + pt2Px;
    }

    private VirtualWallOperationGetWallsResponseBean.ValuesBean.VwallsBean.WallsBean getClickVirtualWall(float f, float f2) {
        List<VirtualWallOperationGetWallsResponseBean.ValuesBean.VwallsBean.WallsBean> list = this.wallsBeans;
        if (!(list == null || list.size() == 0)) {
            float pt2Px = ((float) AdaptScreenUtils.pt2Px(20.0f)) / this.mScale;
            for (VirtualWallOperationGetWallsResponseBean.ValuesBean.VwallsBean.WallsBean next : this.wallsBeans) {
                if (f > next.getStart_x() - pt2Px && f < next.getStart_x() + pt2Px && f2 > next.getStart_y() - pt2Px && f2 < next.getStart_y() + pt2Px) {
                    this.clickType = 1;
                    return next;
                } else if (f > next.getEnd_x() - pt2Px && f < next.getEnd_x() + pt2Px && f2 > next.getEnd_y() - pt2Px && f2 < next.getEnd_y() + pt2Px) {
                    this.clickType = 2;
                    return next;
                }
            }
        }
        return null;
    }

    private RectF getRect(VirtualWallOperationGetWallsResponseBean.ValuesBean.VwallsBean.WallsBean wallsBean) {
        float f;
        float f2;
        float f3;
        float f4;
        float start_x = wallsBean.getStart_x();
        float start_y = wallsBean.getStart_y();
        float end_x = wallsBean.getEnd_x();
        float end_y = wallsBean.getEnd_y();
        float pt2Px = (float) AdaptScreenUtils.pt2Px((32.0f / this.mScale) + 10.0f);
        float pt2Px2 = (float) AdaptScreenUtils.pt2Px((32.0f / this.mScale) + 20.0f);
        int i = (end_x > start_x ? 1 : (end_x == start_x ? 0 : -1));
        if (i > 0 && end_y > start_y) {
            f4 = start_x - pt2Px;
            f3 = start_y - pt2Px;
            f2 = end_x + pt2Px;
            f = end_y + pt2Px;
        } else if (i > 0) {
            f4 = start_x - pt2Px;
            f2 = end_x + pt2Px;
            float f5 = start_y + pt2Px;
            f3 = end_y - pt2Px;
            f = f5;
        } else if (end_y > start_y) {
            f3 = start_y - pt2Px;
            f = end_y + pt2Px;
            float f6 = end_x - pt2Px;
            f2 = start_x + pt2Px;
            f4 = f6;
        } else {
            float f7 = start_y + pt2Px;
            f3 = end_y - pt2Px;
            f = f7;
            float f8 = end_x - pt2Px;
            f2 = start_x + pt2Px;
            f4 = f8;
        }
        if (Math.abs(f3 - f) < 30.0f) {
            f3 -= pt2Px2;
            f += pt2Px2;
        }
        if (Math.abs(f4 - f2) < 30.0f) {
            f4 -= pt2Px2;
            f2 += pt2Px2;
        }
        return new RectF(f4, f3, f2, f);
    }

    public void setShapeType(int i) {
        this.mShapeType = i;
    }
}
