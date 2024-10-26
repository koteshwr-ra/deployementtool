package mc.csst.com.selfchassislibrary.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import androidx.core.internal.view.SupportMenu;
import com.ciot.base.util.ThreadPoolUtils;
import java.util.ArrayList;
import java.util.List;
import mc.csst.com.selfchassislibrary.bean.MapInfoBean;
import mc.csst.com.selfchassislibrary.bean.PointBean;
import mc.csst.com.selfchassislibrary.bean.msg.AreaItemBean;
import mc.csst.com.selfchassislibrary.bean.msg.MarkerOperationGetMarkersResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.PathGetResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.VirtualWallOperationGetWallsResponseBean;
import mc.csst.com.selfchassislibrary.chassis.SelfChassisMsgCallBack;

public class MapView extends SurfaceView implements SurfaceHolder.Callback, SelfChassisMsgCallBack.OnMapInfoCallBack {
    private static final int ROBOTICON_SIZE = 28;
    private static final String TAG = MapView.class.getSimpleName();
    /* access modifiers changed from: private */
    public volatile boolean done = false;
    private boolean mAllowTouch;
    private float mCurrentScale = 1.0f;
    Paint mDestinationPaint = new Paint();
    /* access modifiers changed from: private */
    public Path mDestinationPath = new Path();
    private float mEndX;
    private float mEndY;
    /* access modifiers changed from: private */
    public boolean mIsMove = false;
    boolean mIsTargetNameNull;
    Paint mLaserPaint = new Paint();
    /* access modifiers changed from: private */
    public float[] mLaserPoint;
    /* access modifiers changed from: private */
    public Bitmap mMapBm;
    private PointF mMapCenter;
    /* access modifiers changed from: private */
    public Matrix mMapMatrix = new Matrix();
    private PointF mMapOrigin;
    private float mOriginX;
    private float mOriginY;
    Paint mPathPaint = new Paint();
    /* access modifiers changed from: private */
    public float[] mPathPoint;
    Paint mPosePaint = new Paint();
    private float mResolution;
    private PointBean mRobotCurrentPosition = new PointBean();
    /* access modifiers changed from: private */
    public Path mRobotPosePath = new Path();
    private float mRobotTheta;
    private int mRobotX;
    private int mRobotY;
    private PointF mStartPoint;
    private float mStartX;
    private float mStartY;
    private float mTheta;
    /* access modifiers changed from: private */
    public float[] mVirtualWall;
    private int mWindowHeight;
    private int mWindowWidth;
    private Runnable runnable;

    public void area(List<AreaItemBean> list) {
    }

    public void bjd(List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean> list) {
    }

    public void currentMapArea(double d) {
    }

    public void downCamData(ArrayList<PointBean> arrayList) {
    }

    public void localMapInfo(String str, String str2) {
    }

    public void mapMatchDegree(MapInfoBean mapInfoBean, Bitmap bitmap) {
    }

    public void onCurrentRecordPath(ArrayList<PointBean> arrayList) {
    }

    public void onFollowPath(ArrayList<PointBean> arrayList) {
    }

    public void onGetAllRecordPath(List<List<PointBean>> list, PathGetResponseBean pathGetResponseBean) {
    }

    public void upCamData(ArrayList<PointBean> arrayList) {
    }

    public MapView(Context context) {
        super(context);
        init();
    }

    public MapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public MapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.done = false;
        draw();
        Log.w(TAG, "surfaceCreated");
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        Log.w(TAG, "surfaceChanged");
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.done = true;
        Log.w(TAG, "surfaceDestroyed");
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.mWindowWidth = measureWidth(i);
        this.mWindowHeight = measureWidth(i2);
        String str = TAG;
        Log.e(str, "mWindowWidth:" + this.mWindowWidth + " mWindowHeight:" + this.mWindowHeight);
        this.mMapCenter.set((float) (this.mWindowWidth / 2), (float) (this.mWindowHeight / 2));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x000f, code lost:
        if (r0 != 2) goto L_0x0172;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r15) {
        /*
            r14 = this;
            boolean r0 = r14.mAllowTouch
            r1 = 1
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            int r0 = r15.getAction()
            if (r0 == 0) goto L_0x0077
            r2 = 2
            if (r0 == r1) goto L_0x0013
            if (r0 == r2) goto L_0x0083
            goto L_0x0172
        L_0x0013:
            android.graphics.Bitmap r15 = r14.mMapBm
            if (r15 != 0) goto L_0x0019
            goto L_0x0172
        L_0x0019:
            android.graphics.Matrix r15 = new android.graphics.Matrix
            r15.<init>()
            android.graphics.Matrix r0 = r14.mMapMatrix
            boolean r0 = r0.invert(r15)
            float[] r3 = new float[r2]
            if (r0 != 0) goto L_0x002a
            goto L_0x0172
        L_0x002a:
            float[] r0 = new float[r2]
            float r2 = r14.mStartX
            r4 = 0
            r0[r4] = r2
            float r2 = r14.mStartY
            r0[r1] = r2
            r15.mapPoints(r3, r0)
            r15 = r3[r4]
            r0 = r3[r1]
            float r2 = r14.mOriginX
            float r3 = r14.mResolution
            float r15 = r15 * r3
            float r2 = r2 + r15
            float r15 = r14.mOriginY
            float r0 = r0 * r3
            float r15 = r15 + r0
            boolean r0 = r14.mIsMove
            if (r0 == 0) goto L_0x0172
            mc.csst.com.selfchassislibrary.chassis.SelfChassisState r0 = mc.csst.com.selfchassislibrary.chassis.SelfChassisState.getInstance()
            int r0 = r0.getPointState()
            if (r0 != 0) goto L_0x0060
            mc.csst.com.selfchassislibrary.chassis.SelfChassis r0 = mc.csst.com.selfchassislibrary.chassis.SelfChassis.getInstance()
            float r3 = r14.mTheta
            r0.sendMoveByLocation(r2, r15, r3)
            goto L_0x0073
        L_0x0060:
            mc.csst.com.selfchassislibrary.chassis.SelfChassisState r0 = mc.csst.com.selfchassislibrary.chassis.SelfChassisState.getInstance()
            int r0 = r0.getPointState()
            if (r0 != r1) goto L_0x0073
            mc.csst.com.selfchassislibrary.chassis.SelfChassis r0 = mc.csst.com.selfchassislibrary.chassis.SelfChassis.getInstance()
            float r3 = r14.mTheta
            r0.setPoseMsg(r2, r15, r3)
        L_0x0073:
            r14.mIsMove = r4
            goto L_0x0172
        L_0x0077:
            float r0 = r15.getX()
            r14.mStartX = r0
            float r0 = r15.getY()
            r14.mStartY = r0
        L_0x0083:
            float r0 = r15.getX()
            r14.mEndX = r0
            float r15 = r15.getY()
            r14.mEndY = r15
            float r0 = r14.mStartX
            float r2 = r14.mStartY
            float r3 = r14.mEndX
            float r15 = mc.csst.com.selfchassislibrary.utils.ConvertorUtils.getDistanceBetween2Points(r0, r2, r3, r15)
            r0 = 1086324736(0x40c00000, float:6.0)
            int r15 = (r15 > r0 ? 1 : (r15 == r0 ? 0 : -1))
            if (r15 <= 0) goto L_0x0172
            android.graphics.Path r15 = r14.mDestinationPath
            r15.reset()
            r14.mIsMove = r1
            float r15 = r14.mStartY
            float r0 = r14.mEndY
            float r15 = r15 - r0
            double r2 = (double) r15
            float r15 = r14.mEndX
            float r0 = r14.mStartX
            float r15 = r15 - r0
            double r4 = (double) r15
            double r2 = java.lang.Math.atan2(r2, r4)
            float r15 = (float) r2
            r14.mTheta = r15
            java.lang.String r15 = TAG
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "mTheta--"
            r0.append(r2)
            float r2 = r14.mTheta
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            android.util.Log.e(r15, r0)
            float r15 = r14.mStartX
            int r15 = (int) r15
            float r0 = r14.mStartY
            int r0 = (int) r0
            android.graphics.Path r2 = r14.mDestinationPath
            android.graphics.Path$FillType r3 = android.graphics.Path.FillType.WINDING
            r2.setFillType(r3)
            android.graphics.Path r2 = r14.mDestinationPath
            float r15 = (float) r15
            float r3 = r14.mTheta
            double r3 = (double) r3
            double r3 = java.lang.Math.cos(r3)
            float r3 = (float) r3
            r4 = 1105199104(0x41e00000, float:28.0)
            float r3 = r3 * r4
            r5 = 1073741824(0x40000000, float:2.0)
            float r3 = r3 / r5
            float r3 = r3 + r15
            float r0 = (float) r0
            float r6 = r14.mTheta
            double r6 = (double) r6
            double r6 = java.lang.Math.sin(r6)
            float r6 = (float) r6
            float r6 = r6 * r4
            float r6 = r6 / r5
            float r6 = r0 - r6
            r2.moveTo(r3, r6)
            android.graphics.Path r2 = r14.mDestinationPath
            float r3 = r14.mTheta
            double r6 = (double) r3
            r8 = 4612291590509815726(0x400226c3bcdbe7ae, double:2.2689280275926285)
            double r6 = r6 + r8
            double r6 = java.lang.Math.cos(r6)
            float r3 = (float) r6
            float r3 = r3 * r4
            float r3 = r3 / r5
            float r3 = r3 + r15
            float r6 = r14.mTheta
            double r6 = (double) r6
            double r6 = r6 + r8
            double r6 = java.lang.Math.sin(r6)
            float r6 = (float) r6
            float r6 = r6 * r4
            float r6 = r6 / r5
            float r6 = r0 - r6
            r2.lineTo(r3, r6)
            android.graphics.Path r2 = r14.mDestinationPath
            float r3 = r14.mTheta
            double r6 = (double) r3
            r10 = 4614256656552045848(0x400921fb54442d18, double:3.141592653589793)
            double r6 = r6 + r10
            double r6 = java.lang.Math.cos(r6)
            float r3 = (float) r6
            float r3 = r3 * r4
            r6 = 1092616192(0x41200000, float:10.0)
            float r3 = r3 / r6
            float r3 = r3 + r15
            float r7 = r14.mTheta
            double r12 = (double) r7
            double r12 = r12 + r10
            double r10 = java.lang.Math.sin(r12)
            float r7 = (float) r10
            float r7 = r7 * r4
            float r7 = r7 / r6
            float r6 = r0 - r7
            r2.lineTo(r3, r6)
            android.graphics.Path r2 = r14.mDestinationPath
            float r3 = r14.mTheta
            double r6 = (double) r3
            double r6 = r6 - r8
            double r6 = java.lang.Math.cos(r6)
            float r3 = (float) r6
            float r3 = r3 * r4
            float r3 = r3 / r5
            float r15 = r15 + r3
            float r3 = r14.mTheta
            double r6 = (double) r3
            double r6 = r6 - r8
            double r6 = java.lang.Math.sin(r6)
            float r3 = (float) r6
            float r3 = r3 * r4
            float r3 = r3 / r5
            float r0 = r0 - r3
            r2.lineTo(r15, r0)
            android.graphics.Path r15 = r14.mDestinationPath
            r15.close()
        L_0x0172:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: mc.csst.com.selfchassislibrary.view.MapView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void path(ArrayList<PointBean> arrayList) {
        this.mPathPoint = null;
        if (arrayList.size() > 0) {
            this.mPathPoint = new float[(arrayList.size() * 2)];
        }
        for (int i = 0; i < arrayList.size(); i++) {
            float x = (arrayList.get(i).getX() - this.mOriginX) / this.mResolution;
            float y = (arrayList.get(i).getY() - this.mOriginY) / this.mResolution;
            if (!Float.isNaN(x) && !Float.isNaN(y)) {
                float[] fArr = this.mPathPoint;
                int i2 = i * 2;
                fArr[i2] = x;
                fArr[i2 + 1] = y;
            }
        }
    }

    public void map(MapInfoBean mapInfoBean, Bitmap bitmap) {
        this.mMapBm = bitmap;
        this.mOriginX = mapInfoBean.getX();
        this.mOriginY = mapInfoBean.getY();
        this.mResolution = mapInfoBean.getR();
        float width = ((float) getWidth()) / ((float) this.mMapBm.getWidth());
        float height = ((float) getHeight()) / ((float) this.mMapBm.getHeight());
        if (width < height) {
            this.mCurrentScale = width;
        } else {
            this.mCurrentScale = height;
        }
        this.mMapMatrix.setScale(1.0f, -1.0f, (float) (this.mMapBm.getWidth() / 2), (float) (this.mMapBm.getHeight() / 2));
        Matrix matrix = this.mMapMatrix;
        float f = this.mCurrentScale;
        matrix.preScale(f, f, (float) (this.mMapBm.getWidth() / 2), (float) (this.mMapBm.getHeight() / 2));
        this.mMapMatrix.postTranslate(this.mMapCenter.x - ((float) (this.mMapBm.getWidth() / 2)), this.mMapCenter.y - ((float) (this.mMapBm.getHeight() / 2)));
    }

    public void laser(ArrayList<PointBean> arrayList) {
        float x = this.mRobotCurrentPosition.getX();
        float y = this.mRobotCurrentPosition.getY();
        float theta = this.mRobotCurrentPosition.getTheta();
        this.mLaserPoint = null;
        if (arrayList.size() > 0) {
            this.mLaserPoint = new float[(arrayList.size() * 2)];
        }
        for (int i = 0; i < arrayList.size(); i++) {
            double d = (double) theta;
            float x2 = ((float) ((((double) x) + (((double) arrayList.get(i).getX()) * Math.cos(d))) - (((double) arrayList.get(i).getY()) * Math.sin(d)))) - this.mOriginX;
            float f = this.mResolution;
            float f2 = x2 / f;
            float x3 = (((float) ((((double) y) + (((double) arrayList.get(i).getX()) * Math.sin(d))) + (((double) arrayList.get(i).getY()) * Math.cos(d)))) - this.mOriginY) / f;
            float[] fArr = this.mLaserPoint;
            int i2 = i * 2;
            fArr[i2] = f2;
            fArr[i2 + 1] = x3;
        }
    }

    public void pose(PointBean pointBean) {
        float x = pointBean.getX();
        float y = pointBean.getY();
        this.mRobotCurrentPosition = pointBean;
        this.mRobotPosePath.reset();
        this.mRobotTheta = pointBean.getTheta() * -1.0f;
        float f = x - this.mOriginX;
        float f2 = this.mResolution;
        this.mRobotX = (int) (f / f2);
        this.mRobotY = (int) ((y - this.mOriginY) / f2);
        this.mRobotPosePath.setFillType(Path.FillType.WINDING);
        this.mRobotPosePath.moveTo(((float) this.mRobotX) + ((((float) Math.cos((double) this.mRobotTheta)) * 28.0f) / 2.0f), ((float) this.mRobotY) - ((((float) Math.sin((double) this.mRobotTheta)) * 28.0f) / 2.0f));
        this.mRobotPosePath.lineTo(((float) this.mRobotX) + ((((float) Math.cos(((double) this.mRobotTheta) + 2.2689280275926285d)) * 28.0f) / 2.0f), ((float) this.mRobotY) - ((((float) Math.sin(((double) this.mRobotTheta) + 2.2689280275926285d)) * 28.0f) / 2.0f));
        this.mRobotPosePath.lineTo(((float) this.mRobotX) + ((((float) Math.cos(((double) this.mRobotTheta) + 3.141592653589793d)) * 28.0f) / 10.0f), ((float) this.mRobotY) - ((((float) Math.sin(((double) this.mRobotTheta) + 3.141592653589793d)) * 28.0f) / 10.0f));
        this.mRobotPosePath.lineTo(((float) this.mRobotX) + ((((float) Math.cos(((double) this.mRobotTheta) - 2.2689280275926285d)) * 28.0f) / 2.0f), ((float) this.mRobotY) - ((((float) Math.sin(((double) this.mRobotTheta) - 2.2689280275926285d)) * 28.0f) / 2.0f));
        this.mRobotPosePath.close();
    }

    public void virtualWall(List<VirtualWallOperationGetWallsResponseBean.ValuesBean.VwallsBean.WallsBean> list) {
        float[] fArr = new float[(list.size() * 4)];
        this.mVirtualWall = null;
        for (int i = 0; i < list.size(); i++) {
            int i2 = i * 4;
            fArr[i2] = (float) ((int) ((list.get(i).getStart_x() - this.mOriginX) / this.mResolution));
            fArr[i2 + 1] = (float) ((int) ((list.get(i).getStart_y() - this.mOriginY) / this.mResolution));
            fArr[i2 + 2] = (float) ((int) ((list.get(i).getEnd_x() - this.mOriginX) / this.mResolution));
            fArr[i2 + 3] = (float) ((int) ((list.get(i).getEnd_y() - this.mOriginY) / this.mResolution));
        }
        this.mVirtualWall = fArr;
    }

    public void targetName(String str) {
        this.mIsTargetNameNull = TextUtils.isEmpty(str);
    }

    private void init() {
        getHolder().addCallback(this);
        this.mStartPoint = new PointF();
        this.mMapCenter = new PointF();
        this.mMapOrigin = new PointF();
    }

    private int measureWidth(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == Integer.MIN_VALUE) {
            return getWidth();
        }
        if (mode == 1073741824) {
            return size;
        }
        return 0;
    }

    public void draw() {
        if (this.runnable == null) {
            this.runnable = new Runnable() {
                /* JADX WARNING: Removed duplicated region for block: B:16:0x004b A[Catch:{ Exception -> 0x008c }] */
                /* JADX WARNING: Removed duplicated region for block: B:17:0x0053 A[Catch:{ Exception -> 0x008c }] */
                /* JADX WARNING: Removed duplicated region for block: B:27:0x007e A[Catch:{ Exception -> 0x008c }] */
                /* JADX WARNING: Removed duplicated region for block: B:28:0x0086 A[Catch:{ Exception -> 0x008c }] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void run() {
                    /*
                        r6 = this;
                        mc.csst.com.selfchassislibrary.view.MapView r0 = mc.csst.com.selfchassislibrary.view.MapView.this
                        android.view.SurfaceHolder r0 = r0.getHolder()
                    L_0x0006:
                        mc.csst.com.selfchassislibrary.view.MapView r1 = mc.csst.com.selfchassislibrary.view.MapView.this
                        boolean r1 = r1.done
                        if (r1 != 0) goto L_0x00a0
                        android.graphics.Canvas r1 = r0.lockCanvas()
                        if (r1 == 0) goto L_0x0094
                        monitor-enter(r0)
                        r6.setMapViewBg(r1)     // Catch:{ Exception -> 0x008c }
                        r6.drawMap(r1)     // Catch:{ Exception -> 0x008c }
                        r1.save()     // Catch:{ Exception -> 0x008c }
                        mc.csst.com.selfchassislibrary.chassis.SelfChassisState r2 = mc.csst.com.selfchassislibrary.chassis.SelfChassisState.getInstance()     // Catch:{ Exception -> 0x008c }
                        boolean r2 = r2.isGetPathState()     // Catch:{ Exception -> 0x008c }
                        r3 = 601(0x259, float:8.42E-43)
                        r4 = 0
                        if (r2 == 0) goto L_0x003a
                        mc.csst.com.selfchassislibrary.chassis.SelfChassisState r2 = mc.csst.com.selfchassislibrary.chassis.SelfChassisState.getInstance()     // Catch:{ Exception -> 0x008c }
                        int r2 = r2.getNavStatus()     // Catch:{ Exception -> 0x008c }
                        if (r2 == r3) goto L_0x0036
                        goto L_0x003a
                    L_0x0036:
                        r6.drawPath(r1)     // Catch:{ Exception -> 0x008c }
                        goto L_0x0041
                    L_0x003a:
                        mc.csst.com.selfchassislibrary.view.MapView r2 = mc.csst.com.selfchassislibrary.view.MapView.this     // Catch:{ Exception -> 0x008c }
                        float[] r5 = new float[r4]     // Catch:{ Exception -> 0x008c }
                        float[] unused = r2.mPathPoint = r5     // Catch:{ Exception -> 0x008c }
                    L_0x0041:
                        mc.csst.com.selfchassislibrary.chassis.SelfChassisState r2 = mc.csst.com.selfchassislibrary.chassis.SelfChassisState.getInstance()     // Catch:{ Exception -> 0x008c }
                        boolean r2 = r2.isLaserDataState()     // Catch:{ Exception -> 0x008c }
                        if (r2 != 0) goto L_0x0053
                        mc.csst.com.selfchassislibrary.view.MapView r2 = mc.csst.com.selfchassislibrary.view.MapView.this     // Catch:{ Exception -> 0x008c }
                        float[] r4 = new float[r4]     // Catch:{ Exception -> 0x008c }
                        float[] unused = r2.mLaserPoint = r4     // Catch:{ Exception -> 0x008c }
                        goto L_0x0056
                    L_0x0053:
                        r6.drawLaser(r1)     // Catch:{ Exception -> 0x008c }
                    L_0x0056:
                        r6.drawVirtualWall(r1)     // Catch:{ Exception -> 0x008c }
                        mc.csst.com.selfchassislibrary.chassis.SelfChassisState r2 = mc.csst.com.selfchassislibrary.chassis.SelfChassisState.getInstance()     // Catch:{ Exception -> 0x008c }
                        int r2 = r2.getNavStatus()     // Catch:{ Exception -> 0x008c }
                        if (r2 == r3) goto L_0x006b
                        mc.csst.com.selfchassislibrary.view.MapView r2 = mc.csst.com.selfchassislibrary.view.MapView.this     // Catch:{ Exception -> 0x008c }
                        boolean r2 = r2.mIsMove     // Catch:{ Exception -> 0x008c }
                        if (r2 == 0) goto L_0x0074
                    L_0x006b:
                        mc.csst.com.selfchassislibrary.view.MapView r2 = mc.csst.com.selfchassislibrary.view.MapView.this     // Catch:{ Exception -> 0x008c }
                        boolean r2 = r2.mIsTargetNameNull     // Catch:{ Exception -> 0x008c }
                        if (r2 == 0) goto L_0x0074
                        r6.drawDestination(r1)     // Catch:{ Exception -> 0x008c }
                    L_0x0074:
                        mc.csst.com.selfchassislibrary.chassis.SelfChassisState r2 = mc.csst.com.selfchassislibrary.chassis.SelfChassisState.getInstance()     // Catch:{ Exception -> 0x008c }
                        boolean r2 = r2.isGetPoseState()     // Catch:{ Exception -> 0x008c }
                        if (r2 != 0) goto L_0x0086
                        mc.csst.com.selfchassislibrary.view.MapView r2 = mc.csst.com.selfchassislibrary.view.MapView.this     // Catch:{ Exception -> 0x008c }
                        android.graphics.Paint r2 = r2.mPosePaint     // Catch:{ Exception -> 0x008c }
                        r2.reset()     // Catch:{ Exception -> 0x008c }
                        goto L_0x0090
                    L_0x0086:
                        r6.drawRobot(r1)     // Catch:{ Exception -> 0x008c }
                        goto L_0x0090
                    L_0x008a:
                        r1 = move-exception
                        goto L_0x0092
                    L_0x008c:
                        r2 = move-exception
                        r2.printStackTrace()     // Catch:{ all -> 0x008a }
                    L_0x0090:
                        monitor-exit(r0)     // Catch:{ all -> 0x008a }
                        goto L_0x0094
                    L_0x0092:
                        monitor-exit(r0)     // Catch:{ all -> 0x008a }
                        throw r1
                    L_0x0094:
                        if (r1 == 0) goto L_0x0099
                        r0.unlockCanvasAndPost(r1)
                    L_0x0099:
                        r1 = 80
                        android.os.SystemClock.sleep(r1)
                        goto L_0x0006
                    L_0x00a0:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: mc.csst.com.selfchassislibrary.view.MapView.AnonymousClass1.run():void");
                }

                private void drawVirtualWall(Canvas canvas) {
                    if (canvas != null && MapView.this.mVirtualWall != null) {
                        Paint paint = new Paint();
                        paint.setColor(-7829368);
                        paint.setStrokeWidth(2.0f);
                        canvas.setMatrix(MapView.this.mMapMatrix);
                        canvas.drawLines(MapView.this.mVirtualWall, paint);
                    }
                }

                private void drawDestination(Canvas canvas) {
                    if (canvas != null && MapView.this.mDestinationPath != null) {
                        canvas.restore();
                        MapView.this.mDestinationPaint.setColor(SupportMenu.CATEGORY_MASK);
                        MapView.this.mDestinationPaint.setStrokeWidth(2.0f);
                        canvas.drawPath(MapView.this.mDestinationPath, MapView.this.mDestinationPaint);
                    }
                }

                private void drawMap(Canvas canvas) {
                    if (canvas != null && MapView.this.mMapBm != null) {
                        canvas.drawBitmap(MapView.this.mMapBm, MapView.this.mMapMatrix, (Paint) null);
                    }
                }

                private void drawPath(Canvas canvas) {
                    if (canvas != null && MapView.this.mPathPoint != null) {
                        MapView.this.mPathPaint.setColor(-16776961);
                        MapView.this.mPathPaint.setStrokeWidth(2.0f);
                        canvas.setMatrix(MapView.this.mMapMatrix);
                        canvas.drawPoints(MapView.this.mPathPoint, MapView.this.mPathPaint);
                    }
                }

                private void drawRobot(Canvas canvas) {
                    if (canvas != null && MapView.this.mRobotPosePath != null) {
                        MapView.this.mPosePaint.setColor(-16711681);
                        canvas.setMatrix(MapView.this.mMapMatrix);
                        canvas.drawPath(MapView.this.mRobotPosePath, MapView.this.mPosePaint);
                    }
                }

                private void drawLaser(Canvas canvas) {
                    MapView.this.mLaserPaint.setColor(SupportMenu.CATEGORY_MASK);
                    MapView.this.mLaserPaint.setStrokeWidth(2.0f);
                    if (canvas != null && MapView.this.mLaserPoint != null && MapView.this.mLaserPoint.length != 0) {
                        synchronized (MapView.class) {
                            if (!(MapView.this.mLaserPoint == null || MapView.this.mLaserPoint.length == 0)) {
                                canvas.setMatrix(MapView.this.mMapMatrix);
                                canvas.drawPoints(MapView.this.mLaserPoint, MapView.this.mLaserPaint);
                            }
                        }
                    }
                }

                private void setMapViewBg(Canvas canvas) {
                    if (canvas != null) {
                        Paint paint = new Paint();
                        paint.setColor(-3355444);
                        paint.setStyle(Paint.Style.FILL);
                        canvas.drawRect(new Rect(0, 0, MapView.this.getWidth(), MapView.this.getHeight()), paint);
                    }
                }
            };
        }
        if (!this.done) {
            ThreadPoolUtils.getInstance().execute(this.runnable);
        }
    }

    public boolean isAllowTouch() {
        return this.mAllowTouch;
    }

    public void setAllowTouch(boolean z) {
        this.mAllowTouch = z;
    }
}
