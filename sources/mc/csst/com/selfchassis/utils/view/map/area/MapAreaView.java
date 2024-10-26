package mc.csst.com.selfchassis.utils.view.map.area;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.ciot.base.util.ContextUtil;
import com.ciot.base.util.GsonUtils;
import com.ciot.base.util.MyLogUtils;
import com.ciot.sentrymove.R;
import java.util.ArrayList;
import java.util.List;
import mc.csst.com.selfchassis.utils.LayerDataUtils;
import mc.csst.com.selfchassis.utils.MyToastUtils;
import mc.csst.com.selfchassis.utils.enumbs.ConvertChassisAreaType;
import mc.csst.com.selfchassis.utils.view.map.area.bean.LineSegment;
import mc.csst.com.selfchassislibrary.bean.MapInfoBean;
import mc.csst.com.selfchassislibrary.bean.msg.AreaItemBean;

public class MapAreaView extends View {
    private static final int LEAST_POINT_NUM = 3;
    private static final String TAG = MapAreaView.class.getSimpleName();
    private static final float mRadiusOffset = 20.0f;
    private Paint mAreaFillColorPaint;
    private List<AreaItemBean> mAreaItemList;
    private Paint mAreaPaint;
    private Path mAreaPath;
    private int mAreaType;
    private AreaItemBean.PolygonBean.PointsBean mClickPoint;
    private int mCurPointIndex;
    private RectF mDottedRect;
    private Paint mDottedRectPaint;
    private AreaItemBean mEditAreaBean;
    private Paint mInnerCirclePaint;
    private float mInnerRadius;
    private float mMapHeight;
    private Matrix mMapMatrix;
    private float mMapWidth;
    private OnDrawAreaListener mOnDrawAreaListener;
    private int mOperateType;
    private float mOriginX;
    private float mOriginY;
    private Paint mOuterCirclePaint;
    private float mOuterRadius;
    private List<AreaItemBean.PolygonBean.PointsBean> mPathPointsBeans;
    private AreaItemBean.PolygonBean.PointsBean mPreEditPoint;
    private float mResolution;
    private float mScale;

    public interface OnDrawAreaListener {
        void onAddComplete(AreaItemBean areaItemBean);

        void onChangePoints(AreaItemBean areaItemBean);

        void onUnFinishAdd(boolean z);
    }

    interface OperateType {
        public static final int ADD = 0;
        public static final int EDIT = 2;
        public static final int INIT = -1;
    }

    public MapAreaView(Context context) {
        this(context, (AttributeSet) null);
    }

    public MapAreaView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MapAreaView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mScale = 1.0f;
        this.mInnerRadius = 9.0f;
        this.mOuterRadius = 10.0f;
        this.mOperateType = -1;
        this.mAreaType = -1;
        this.mAreaItemList = new ArrayList();
        this.mPathPointsBeans = new ArrayList();
        this.mEditAreaBean = new AreaItemBean();
        this.mCurPointIndex = -1;
        this.mPreEditPoint = new AreaItemBean.PolygonBean.PointsBean();
        this.mClickPoint = null;
        initPaint();
    }

    private void initPaint() {
        this.mAreaPath = new Path();
        Paint paint = new Paint();
        this.mAreaPaint = paint;
        paint.setAntiAlias(true);
        this.mAreaPaint.setStrokeWidth(2.0f);
        this.mAreaPaint.setColor(-16776961);
        this.mAreaPaint.setStyle(Paint.Style.STROKE);
        Paint paint2 = new Paint();
        this.mAreaFillColorPaint = paint2;
        paint2.setAntiAlias(true);
        this.mAreaFillColorPaint.setStrokeWidth(2.0f);
        this.mAreaFillColorPaint.setColor(getLocalColor(R.color.clr_33FF5A5A));
        this.mAreaFillColorPaint.setStyle(Paint.Style.FILL);
        Paint paint3 = new Paint();
        this.mInnerCirclePaint = paint3;
        paint3.setStyle(Paint.Style.FILL);
        this.mInnerCirclePaint.setColor(getLocalColor(R.color.white));
        this.mInnerCirclePaint.setAntiAlias(true);
        this.mInnerCirclePaint.setStrokeWidth(2.0f);
        Paint paint4 = new Paint();
        this.mOuterCirclePaint = paint4;
        paint4.setStyle(Paint.Style.STROKE);
        this.mOuterCirclePaint.setColor(getLocalColor(R.color.clr_FF8329));
        this.mOuterCirclePaint.setAntiAlias(true);
        this.mOuterCirclePaint.setStrokeWidth(2.0f);
        Paint paint5 = new Paint();
        this.mDottedRectPaint = paint5;
        paint5.setStyle(Paint.Style.STROKE);
        this.mDottedRectPaint.setStrokeWidth(2.0f);
        this.mDottedRectPaint.setColor(getLocalColor(R.color.clr_0560FD));
        this.mDottedRectPaint.setAntiAlias(true);
        this.mDottedRectPaint.setPathEffect(new DashPathEffect(new float[]{5.0f, 5.0f}, 0.0f));
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        String str = TAG;
        MyLogUtils.Logd(str, "onMeasure widthMeasureSpec:" + i + " heightMeasureSpec:" + i2);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.setMatrix(this.mMapMatrix);
        drawAllArea(canvas);
        if (this.mDottedRect != null) {
            drawEditArea(canvas);
        } else {
            drawNewArea(canvas);
        }
    }

    private void drawDottedRect(Canvas canvas) {
        RectF rectF = this.mDottedRect;
        if (rectF != null) {
            canvas.drawRect(rectF, this.mDottedRectPaint);
        }
    }

    private int getLocalColor(int i) {
        return ContextUtil.getContext().getResources().getColor(i);
    }

    private void drawEditArea(Canvas canvas) {
        setOperateType(2);
        drawArea(canvas, this.mEditAreaBean);
        drawDottedRect(canvas);
    }

    public int getOperateType() {
        return this.mOperateType;
    }

    public void setOperateType(int i) {
        this.mOperateType = i;
    }

    private void drawAllArea(Canvas canvas) {
        setOperateType(-1);
        List<AreaItemBean> list = this.mAreaItemList;
        if (list != null && list.size() != 0) {
            for (int i = 0; i < this.mAreaItemList.size(); i++) {
                drawArea(canvas, this.mAreaItemList.get(i));
            }
        }
    }

    private void drawArea(Canvas canvas, AreaItemBean areaItemBean) {
        boolean z;
        List<AreaItemBean.PolygonBean.PointsBean> list;
        List<AreaItemBean.PolygonBean.PointsBean> list2;
        Canvas canvas2 = canvas;
        this.mAreaPath.reset();
        List<AreaItemBean.PolygonBean.PointsBean> points = areaItemBean.getPolygon().getPoints();
        if (areaItemBean.getPolygon() != null && points != null && points.size() != 0) {
            int type = areaItemBean.getType();
            if (5 == type) {
                if (LayerDataUtils.getInstance().getShowStrongArea()) {
                    this.mAreaPaint.setColor(getLocalColor(R.color.clr_FFFF5A5A));
                    this.mOuterCirclePaint.setColor(getLocalColor(R.color.clr_FFFF5A5A));
                    this.mInnerCirclePaint.setColor(-1);
                    this.mAreaFillColorPaint.setColor(getLocalColor(R.color.clr_33FF5A5A));
                } else {
                    return;
                }
            } else if (6 == type) {
                if (LayerDataUtils.getInstance().getShowSlopeArea()) {
                    this.mAreaPaint.setColor(getLocalColor(R.color.clr_F8B405));
                    this.mOuterCirclePaint.setColor(getLocalColor(R.color.clr_F8B405));
                    this.mInnerCirclePaint.setColor(-1);
                    this.mAreaFillColorPaint.setColor(getLocalColor(R.color.clr_80FFD35E));
                } else {
                    return;
                }
            } else if (7 == type) {
                this.mAreaPaint.setColor(getLocalColor(R.color.clr_FF4785FF));
                this.mOuterCirclePaint.setColor(getLocalColor(R.color.clr_FF4785FF));
                this.mInnerCirclePaint.setColor(-1);
                this.mAreaFillColorPaint.setColor(getLocalColor(R.color.clr_331C84FF));
            } else if (8 == type) {
                if (LayerDataUtils.getInstance().getShowSlowArea()) {
                    this.mAreaPaint.setColor(getLocalColor(R.color.clr_FF3AEDA2));
                    this.mOuterCirclePaint.setColor(getLocalColor(R.color.clr_FF3AEDA2));
                    this.mInnerCirclePaint.setColor(-1);
                    this.mAreaFillColorPaint.setColor(getLocalColor(R.color.clr_331CFFAD));
                } else {
                    return;
                }
            } else if (4 == type) {
                if (LayerDataUtils.getInstance().getShowDangerArea()) {
                    this.mAreaPaint.setColor(getLocalColor(R.color.clr_FFFF8A0));
                    this.mOuterCirclePaint.setColor(getLocalColor(R.color.clr_FFFF8A0));
                    this.mInnerCirclePaint.setColor(-1);
                    this.mAreaFillColorPaint.setColor(getLocalColor(R.color.clr_33FF8A00));
                } else {
                    return;
                }
            } else if (3 == type) {
                if (LayerDataUtils.getInstance().getShowLabelArea()) {
                    this.mAreaPaint.setColor(getLocalColor(R.color.clr_725BFF));
                    this.mOuterCirclePaint.setColor(getLocalColor(R.color.clr_725BFF));
                    this.mInnerCirclePaint.setColor(-1);
                    this.mAreaFillColorPaint.setColor(getLocalColor(R.color.clr_33725BFF));
                } else {
                    return;
                }
            }
            if (points.size() > 0) {
                boolean z2 = false;
                float f = 0.0f;
                float f2 = 0.0f;
                for (int i = 0; i < points.size(); i++) {
                    AreaItemBean.PolygonBean.PointsBean pointsBean = points.get(i);
                    float x = (float) pointsBean.getX();
                    float y = (float) pointsBean.getY();
                    if (i == 0) {
                        this.mAreaPath.moveTo(x, y);
                        f2 = y;
                        f = x;
                    } else {
                        if (getOperateType() == 0) {
                            float f3 = y;
                            z = z2;
                            float f4 = x;
                            if (isClickOnCircle(x, y, (double) f, (double) f2)) {
                                List<LineSegment> lineSegmentOther = AreaUtils.getLineSegmentOther(this.mPathPointsBeans);
                                String str = TAG;
                                MyLogUtils.Logd(str, "添加模式时是否有交点：" + AreaUtils.crossJudgment(lineSegmentOther));
                                if (AreaUtils.crossJudgment(lineSegmentOther) && (list2 = this.mPathPointsBeans) != null && !list2.isEmpty()) {
                                    List<AreaItemBean.PolygonBean.PointsBean> list3 = this.mPathPointsBeans;
                                    list3.remove(list3.size() - 1);
                                    invalidate();
                                    MyToastUtils.showShort(ContextUtil.getContext(), ContextUtil.getContext().getResources().getString(R.string.txt_cannot_draw_intersecting_closed_area));
                                    return;
                                } else if (points.size() > 3 || (list = this.mPathPointsBeans) == null) {
                                    this.mAreaPath.close();
                                    OnDrawAreaListener onDrawAreaListener = this.mOnDrawAreaListener;
                                    if (onDrawAreaListener != null) {
                                        onDrawAreaListener.onUnFinishAdd(false);
                                    }
                                    z2 = true;
                                } else {
                                    list.remove(list.size() - 1);
                                    invalidate();
                                    MyToastUtils.showShort(ContextUtil.getContext(), ContextUtil.getContext().getResources().getString(R.string.drawing_area_least_three_points));
                                    return;
                                }
                            } else {
                                this.mAreaPath.lineTo(f4, f3);
                            }
                        } else {
                            z = z2;
                            this.mAreaPath.lineTo(x, y);
                        }
                        z2 = z;
                    }
                }
                boolean z3 = z2;
                if (getOperateType() == -1 || getOperateType() == 2) {
                    this.mAreaPath.close();
                }
                canvas2.drawPath(this.mAreaPath, this.mAreaPaint);
                canvas2.drawPath(this.mAreaPath, this.mAreaFillColorPaint);
                boolean z4 = z3;
                drawAreaCircle(canvas2, points, z4);
                if (z4 && getOperateType() == 0) {
                    points.remove(points.size() - 1);
                    AreaItemBean areaItemBean2 = (AreaItemBean) GsonUtils.fromLocalJson(GsonUtils.toJson(areaItemBean), AreaItemBean.class);
                    switchEditModeFromAdd(areaItemBean2);
                    if (this.mOnDrawAreaListener != null) {
                        AreaItemBean areaItemBean3 = (AreaItemBean) GsonUtils.fromLocalJson(GsonUtils.toJson(areaItemBean2), AreaItemBean.class);
                        convertActualMapPosition(areaItemBean3);
                        this.mOnDrawAreaListener.onAddComplete(areaItemBean3);
                    }
                }
            }
        }
    }

    private void drawAreaCircle(Canvas canvas, List<AreaItemBean.PolygonBean.PointsBean> list, boolean z) {
        if (list != null && list.size() != 0 && getOperateType() != -1) {
            for (int i = 0; i < list.size(); i++) {
                AreaItemBean.PolygonBean.PointsBean pointsBean = list.get(i);
                float x = (float) pointsBean.getX();
                float y = (float) pointsBean.getY();
                boolean z2 = true;
                if (z && i == list.size() - 1 && i != 0) {
                    z2 = false;
                }
                if (z2) {
                    canvas.drawCircle(x, y, this.mOuterRadius, this.mOuterCirclePaint);
                    canvas.drawCircle(x, y, this.mInnerRadius, this.mInnerCirclePaint);
                }
            }
        }
    }

    private boolean isClickOnCircle(float f, float f2, double d, double d2) {
        double d3 = (double) f;
        float f3 = this.mOuterRadius;
        float f4 = this.mScale;
        boolean z = d3 <= (((double) (f3 / f4)) + d) + ((double) (mRadiusOffset / f4)) && d3 >= (d - ((double) (f3 / f4))) - ((double) (mRadiusOffset / f4));
        double d4 = (double) f2;
        float f5 = this.mOuterRadius;
        float f6 = this.mScale;
        boolean z2 = d4 <= (((double) (f5 / f6)) + d2) + ((double) (mRadiusOffset / f6)) && d4 >= (d2 - ((double) (f5 / f6))) - ((double) (mRadiusOffset / f6));
        if (!z || !z2) {
            return false;
        }
        return true;
    }

    private void drawNewArea(Canvas canvas) {
        setOperateType(0);
        AreaItemBean areaItemBean = new AreaItemBean();
        areaItemBean.setType(this.mAreaType);
        AreaItemBean.PolygonBean polygonBean = new AreaItemBean.PolygonBean();
        polygonBean.setPoints(this.mPathPointsBeans);
        areaItemBean.setPolygon(polygonBean);
        drawArea(canvas, areaItemBean);
    }

    public boolean onAreaTouchEvent(MotionEvent motionEvent) {
        List<AreaItemBean.PolygonBean.PointsBean> list;
        AreaItemBean areaItemBean;
        if (this.mMapMatrix == null) {
            return true;
        }
        boolean z = false;
        float[] fArr = {motionEvent.getX(), motionEvent.getY()};
        Matrix matrix = new Matrix();
        this.mMapMatrix.invert(matrix);
        matrix.mapPoints(fArr);
        float f = fArr[0];
        float f2 = fArr[1];
        AreaItemBean.PolygonBean.PointsBean pointsBean = new AreaItemBean.PolygonBean.PointsBean();
        this.mClickPoint = pointsBean;
        pointsBean.setX((double) f);
        this.mClickPoint.setY((double) f2);
        int i = (f > 0.0f ? 1 : (f == 0.0f ? 0 : -1));
        if (i < 0 || f2 < 0.0f || f > this.mMapWidth || f2 > this.mMapHeight) {
            z = true;
        }
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action == 1) {
                if (this.mDottedRect != null) {
                    AreaItemBean areaItemBean2 = this.mEditAreaBean;
                    if (!(areaItemBean2 == null || this.mPreEditPoint == null)) {
                        List<LineSegment> lineSegment = AreaUtils.getLineSegment(areaItemBean2.getPolygon().getPoints());
                        String str = TAG;
                        MyLogUtils.Logd(str, "编辑模式时是否有交点：" + AreaUtils.crossJudgment(lineSegment));
                        if (AreaUtils.crossJudgment(lineSegment)) {
                            AreaItemBean.PolygonBean.PointsBean pointsBean2 = this.mPreEditPoint;
                            this.mClickPoint = pointsBean2;
                            changeEditAreaBean(pointsBean2, this.mCurPointIndex);
                            invalidate();
                            MyToastUtils.showShort(getContext(), getContext().getString(R.string.txt_cannot_draw_intersecting_closed_area));
                        } else if (!(this.mOnDrawAreaListener == null || (areaItemBean = this.mEditAreaBean) == null)) {
                            AreaItemBean areaItemBean3 = (AreaItemBean) GsonUtils.fromLocalJson(GsonUtils.toJson(areaItemBean), AreaItemBean.class);
                            convertActualMapPosition(areaItemBean3);
                            this.mOnDrawAreaListener.onChangePoints(areaItemBean3);
                        }
                    }
                } else {
                    List<LineSegment> lineSegmentOther = AreaUtils.getLineSegmentOther(this.mPathPointsBeans);
                    String str2 = TAG;
                    MyLogUtils.Logd(str2, "添加模式时是否有交点：" + AreaUtils.crossJudgment(lineSegmentOther));
                    if (AreaUtils.crossJudgment(lineSegmentOther) && (list = this.mPathPointsBeans) != null && !list.isEmpty()) {
                        if (this.mPathPointsBeans.size() - 1 >= 0) {
                            List<AreaItemBean.PolygonBean.PointsBean> list2 = this.mPathPointsBeans;
                            list2.remove(list2.size() - 1);
                        }
                        invalidate();
                        MyToastUtils.showShort(ContextUtil.getContext(), ContextUtil.getContext().getResources().getString(R.string.txt_cannot_draw_intersecting_closed_area));
                    }
                }
                this.mCurPointIndex = -1;
            } else if (action == 2 && i >= 0 && f2 >= 0.0f && f <= this.mMapWidth && f2 <= this.mMapHeight && this.mDottedRect != null) {
                changeEditAreaBean(this.mClickPoint, this.mCurPointIndex);
            }
        } else if (z) {
            return true;
        } else {
            RectF rectF = this.mDottedRect;
            if (rectF == null || !rectF.contains(f, f2)) {
                this.mDottedRect = null;
                this.mPathPointsBeans.add(this.mClickPoint);
                OnDrawAreaListener onDrawAreaListener = this.mOnDrawAreaListener;
                if (onDrawAreaListener != null) {
                    onDrawAreaListener.onUnFinishAdd(true);
                }
                invalidate();
            } else {
                this.mCurPointIndex = getCurPointIndex(this.mClickPoint);
            }
        }
        return true;
    }

    private void changeEditAreaBean(AreaItemBean.PolygonBean.PointsBean pointsBean, int i) {
        AreaItemBean areaItemBean;
        if (i >= 0 && (areaItemBean = this.mEditAreaBean) != null) {
            List<AreaItemBean.PolygonBean.PointsBean> points = areaItemBean.getPolygon().getPoints();
            if (i < points.size()) {
                points.set(i, pointsBean);
            }
        }
        this.mDottedRect = getDottedRect();
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
    }

    public void setAreaItemBeans(List<AreaItemBean> list) {
        this.mAreaItemList = list;
        if (list != null && list.size() > 0) {
            for (AreaItemBean convertDrawMapPosition : list) {
                convertDrawMapPosition(convertDrawMapPosition);
            }
        }
        invalidate();
    }

    public void setScale(float f) {
        if (f > 5.0f) {
            f = 5.0f;
        }
        this.mScale = f;
        this.mOuterRadius = 10.0f / f;
        this.mInnerRadius = 9.0f / f;
    }

    public void convertChassisAreaType(String str) {
        this.mAreaType = ConvertChassisAreaType.getChassisAreaTypeByToolType(str);
    }

    private RectF getDottedRect() {
        AreaItemBean.PolygonBean polygon;
        List<AreaItemBean.PolygonBean.PointsBean> points;
        AreaItemBean areaItemBean = this.mEditAreaBean;
        if (areaItemBean == null || (polygon = areaItemBean.getPolygon()) == null || (points = polygon.getPoints()) == null || points.size() < 3) {
            return null;
        }
        return new RectF(((float) AreaUtils.getPointExtremum(points, true, false)) - (30.0f / this.mScale), ((float) AreaUtils.getPointExtremum(points, false, false)) - (30.0f / this.mScale), ((float) AreaUtils.getPointExtremum(points, true, true)) + (30.0f / this.mScale), ((float) AreaUtils.getPointExtremum(points, false, true)) + (30.0f / this.mScale));
    }

    private int getCurPointIndex(AreaItemBean.PolygonBean.PointsBean pointsBean) {
        AreaItemBean.PolygonBean polygon;
        AreaItemBean areaItemBean = this.mEditAreaBean;
        if (areaItemBean == null || (polygon = areaItemBean.getPolygon()) == null) {
            return -1;
        }
        List<AreaItemBean.PolygonBean.PointsBean> points = polygon.getPoints();
        for (int i = 0; i < points.size(); i++) {
            AreaItemBean.PolygonBean.PointsBean pointsBean2 = points.get(i);
            float x = (float) pointsBean2.getX();
            float y = (float) pointsBean2.getY();
            if (isClickOnCircle((float) pointsBean.getX(), (float) pointsBean.getY(), (double) x, (double) y)) {
                String str = TAG;
                MyLogUtils.Logd(str, "当前点击坐标(x,y):（" + pointsBean.getX() + "," + pointsBean.getY() + "“）匹配上的点击坐标(x,y):（" + x + "," + y + "“）");
                this.mPreEditPoint = pointsBean2;
                return i;
            }
        }
        return -1;
    }

    public void clearAllView() {
        this.mDottedRect = null;
        List<AreaItemBean.PolygonBean.PointsBean> list = this.mPathPointsBeans;
        if (list != null) {
            list.clear();
        }
        List<AreaItemBean> list2 = this.mAreaItemList;
        if (list2 != null) {
            list2.clear();
        }
        this.mPreEditPoint = null;
        this.mCurPointIndex = -1;
        this.mEditAreaBean = null;
        invalidate();
    }

    public void switchEditMode(AreaItemBean areaItemBean) {
        if (this.mAreaItemList != null && areaItemBean != null) {
            for (int i = 0; i < this.mAreaItemList.size(); i++) {
                AreaItemBean areaItemBean2 = this.mAreaItemList.get(i);
                if (TextUtils.equals(areaItemBean2.getName(), areaItemBean.getName())) {
                    this.mEditAreaBean = areaItemBean2;
                    setOperateType(2);
                    this.mDottedRect = getDottedRect();
                    invalidate();
                    return;
                }
            }
        }
    }

    private void switchEditModeFromAdd(AreaItemBean areaItemBean) {
        if (areaItemBean != null) {
            this.mEditAreaBean = areaItemBean;
            setOperateType(2);
            this.mDottedRect = getDottedRect();
            invalidate();
        }
    }

    public void clearUnfinishedArea() {
        this.mPathPointsBeans.clear();
        this.mDottedRect = null;
        invalidate();
    }

    public void convertDrawMapPosition(AreaItemBean areaItemBean) {
        try {
            for (AreaItemBean.PolygonBean.PointsBean next : areaItemBean.getPolygon().getPoints()) {
                next.setX((next.getX() - ((double) this.mOriginX)) / ((double) this.mResolution));
                next.setY((next.getY() - ((double) this.mOriginY)) / ((double) this.mResolution));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void convertActualMapPosition(AreaItemBean areaItemBean) {
        try {
            for (AreaItemBean.PolygonBean.PointsBean next : areaItemBean.getPolygon().getPoints()) {
                double x = next.getX();
                double y = next.getY();
                Matrix matrix = new Matrix();
                float[] fArr = new float[2];
                if (getMatrix().invert(matrix)) {
                    matrix.mapPoints(fArr, new float[]{(float) x, (float) y});
                    next.setX((double) (this.mOriginX + (fArr[0] * this.mResolution)));
                    next.setY((double) (this.mOriginY + (fArr[1] * this.mResolution)));
                } else {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMapMatrix(Matrix matrix) {
        this.mMapMatrix = matrix;
        invalidate();
    }

    public void setOnDrawAreaListener(OnDrawAreaListener onDrawAreaListener) {
        this.mOnDrawAreaListener = onDrawAreaListener;
    }

    public void resetAreaType() {
        this.mAreaType = -1;
        invalidate();
    }
}
