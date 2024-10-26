package mc.csst.com.selfchassis.utils.view.map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.blankj.utilcode.util.ConvertUtils;
import com.ciot.base.util.GsonUtils;
import com.ciot.base.util.MyLogUtils;
import com.ciot.sentrymove.R;
import com.google.gson.reflect.TypeToken;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import mc.csst.com.selfchassis.App;
import mc.csst.com.selfchassis.utils.LayerDataUtils;
import mc.csst.com.selfchassis.utils.enumbs.ConvertChassisAreaType;
import mc.csst.com.selfchassis.utils.view.map.area.MapAreaView;
import mc.csst.com.selfchassislibrary.bean.MapInfoBean;
import mc.csst.com.selfchassislibrary.bean.PointBean;
import mc.csst.com.selfchassislibrary.bean.msg.ApriltagsBufferBean;
import mc.csst.com.selfchassislibrary.bean.msg.AreaItemBean;
import mc.csst.com.selfchassislibrary.bean.msg.GlobalLocateReqBean;
import mc.csst.com.selfchassislibrary.bean.msg.MarkerOperationGetMarkersResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.SetLayeredMapManagerPencilOpPublishBean;
import mc.csst.com.selfchassislibrary.bean.msg.VirtualWallOperationGetWallsResponseBean;

public class MapRlView extends RelativeLayout implements View.OnClickListener, View.OnLongClickListener {
    private static final String TAG = MapRlView.class.getSimpleName();
    /* access modifiers changed from: private */
    public long downTime;
    /* access modifiers changed from: private */
    public int editType;
    private float firstDownX;
    private float firstDownY;
    /* access modifiers changed from: private */
    public float firstScale;
    private boolean isShowBjd = LayerDataUtils.getInstance().getShowLocation();
    /* access modifiers changed from: private */
    public boolean longPressTag = false;
    /* access modifiers changed from: private */
    public volatile int mBrushType = 0;
    private Context mContext;
    /* access modifiers changed from: private */
    public volatile String mLeftToolType = "layer_normal";
    public volatile boolean mLockTargetPoint = false;
    /* access modifiers changed from: private */
    public MapAreaView mMapAreaView;
    private PointF mMapCenter = new PointF();
    private float mMapHeight;
    private FrameLayout mMapInfoFl;
    public LocalLeaserPathView mMapInfoLlpv;
    /* access modifiers changed from: private */
    public MapBgView mMapIv;
    private RelativeLayout mMapPointLineAreaRl;
    private ConstraintLayout mMapRootCl;
    /* access modifiers changed from: private */
    public OnMapViewListener mMapViewListener;
    private RelativeLayout mMapVisualLabelRl;
    private float mMapWidth;
    private float mOriginX;
    private float mOriginY;
    private PointView mPointView;
    /* access modifiers changed from: private */
    public ArrayList<PointView> mPointViewList = new ArrayList<>();
    private String mPositionStr = "";
    /* access modifiers changed from: private */
    public RectView mRectView;
    private float mResolution;
    /* access modifiers changed from: private */
    public volatile int mSoftType = 0;
    /* access modifiers changed from: private */
    public VirtualWallView mVirtualWall;
    private ArrayList<PointView> mVisualLabelPointViewList = new ArrayList<>();
    private int mWindowHeight;
    private int mWindowWidth;
    private MyMapViewOnLongClickListener mapOnLongClickListener;
    /* access modifiers changed from: private */
    public OnMapViewAddRandomPointListener onMapViewAddRandomPointListener;
    private float tempFirstScale;

    public interface MyMapViewOnLongClickListener {
        void myMapViewOnLongClick(float f, float f2);
    }

    public interface OnMapViewAddRandomPointListener {
        void onAddRandomPoint(float f, float f2, float f3);
    }

    public interface OnMapViewListener {
        boolean navigate();
    }

    public void onClick(View view) {
    }

    public boolean onLongClick(View view) {
        return false;
    }

    public MapRlView(Context context) {
        super(context);
        init(context);
    }

    public MapRlView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public MapRlView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.view_map_rl, this);
        this.mMapRootCl = (ConstraintLayout) findViewById(R.id.map_root_cl);
        this.mMapIv = (MapBgView) findViewById(R.id.map_iv);
        this.mMapPointLineAreaRl = (RelativeLayout) findViewById(R.id.map_point_line_area_rl);
        this.mMapVisualLabelRl = (RelativeLayout) findViewById(R.id.map_visual_label_rl);
        this.mMapInfoLlpv = (LocalLeaserPathView) findViewById(R.id.map_info_llpv);
        this.mMapInfoFl = (FrameLayout) findViewById(R.id.map_info_fl);
        this.mVirtualWall = (VirtualWallView) findViewById(R.id.map_virtual_wall);
        this.mMapAreaView = (MapAreaView) findViewById(R.id.map_area);
        this.mRectView = (RectView) findViewById(R.id.map_rect);
        this.mPointView = new PointView(context);
        setOnTouchListener(new MyTouchListener());
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.mWindowWidth = measureWidth(i);
        this.mWindowHeight = measureHeight(i2);
        String str = TAG;
        MyLogUtils.Logd(str, "mWindowWidth:" + this.mWindowWidth + " mWindowHeight:" + this.mWindowHeight);
        this.mMapCenter.set(((float) this.mWindowWidth) / 2.0f, ((float) this.mWindowHeight) / 2.0f);
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

    private int measureHeight(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == Integer.MIN_VALUE) {
            return getHeight();
        }
        if (mode == 1073741824) {
            return size;
        }
        return 0;
    }

    public void setMap(MapInfoBean mapInfoBean, Bitmap bitmap) {
        this.mMapInfoLlpv.setMapInfo(mapInfoBean);
        Bitmap bitMapZip = bitMapZip(bitmap);
        this.mMapIv.setImageBitmap(bitMapZip);
        this.mOriginX = mapInfoBean.getX();
        this.mOriginY = mapInfoBean.getY();
        this.mResolution = mapInfoBean.getR();
        this.mMapWidth = (float) bitMapZip.getWidth();
        float height = (float) bitMapZip.getHeight();
        this.mMapHeight = height;
        this.mVirtualWall.setMapInfo(mapInfoBean, this.mMapWidth, height);
        this.mMapAreaView.setMapInfo(mapInfoBean, this.mMapWidth, this.mMapHeight);
        this.mRectView.setMapInfo(mapInfoBean, this.mMapWidth, this.mMapHeight);
        float width = ((float) getWidth()) / ((float) bitMapZip.getWidth());
        float height2 = ((float) getHeight()) / ((float) bitMapZip.getHeight());
        String str = TAG;
        MyLogUtils.Logd(str, "showMap mMapIv.mMapHeight():" + this.mMapHeight);
        String str2 = TAG;
        MyLogUtils.Logd(str2, "showMap mMapIv.mMapWidth():" + this.mMapWidth);
        float min = Math.min(width, height2);
        if (this.mSoftType != 4) {
            showMap(min);
        }
    }

    public static Bitmap bitMapZip(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return decodeByteArray;
    }

    private void showMap(float f) {
        this.firstScale = f;
        this.tempFirstScale = f;
        String str = TAG;
        MyLogUtils.Logd(str, "showMap scale:" + f);
        Matrix matrix = new Matrix();
        matrix.postScale(1.0f, -1.0f, this.mMapWidth / 2.0f, this.mMapHeight / 2.0f);
        matrix.preScale(f, f, this.mMapWidth / 2.0f, this.mMapHeight / 2.0f);
        matrix.postTranslate(this.mMapCenter.x - (this.mMapWidth / 2.0f), this.mMapCenter.y - (this.mMapHeight / 2.0f));
        String str2 = TAG;
        Log.d(str2, " mMapWidth/scale: " + (this.mMapWidth / f));
        String str3 = TAG;
        Log.d(str3, " mMapHeight/scale: " + (this.mMapHeight / f));
        this.mMapIv.setImageMatrix(matrix);
        this.mMapInfoLlpv.setMapMatrix(matrix);
        this.mVirtualWall.setMapMatrix(matrix);
        this.mMapAreaView.setMapMatrix(matrix);
        this.mRectView.setMapMatrix(matrix);
    }

    public void zoomIn() {
        showMap(this.firstScale + 0.5f);
        showBjd();
        showVisualLabelPoint();
    }

    public void zoomOut() {
        showMap(this.firstScale - 0.5f);
        showBjd();
        showVisualLabelPoint();
    }

    public void restore() {
        showMap(Math.min(((float) getWidth()) / this.mMapWidth, ((float) getHeight()) / this.mMapHeight));
        showBjd();
        showVisualLabelPoint();
        this.mVirtualWall.setScale(this.firstScale);
    }

    public void setPose(PointBean pointBean) {
        if (pointBean == null) {
            pointBean = new PointBean();
        }
        this.mMapInfoLlpv.setPose(pointBean);
    }

    public void setLaser(ArrayList<PointBean> arrayList) {
        this.mMapInfoLlpv.setLaser(arrayList);
    }

    public void setUpCamData(ArrayList<PointBean> arrayList) {
        this.mMapInfoLlpv.setUpCamData(arrayList);
    }

    public void setDownCamData(ArrayList<PointBean> arrayList) {
        this.mMapInfoLlpv.setDownCamData(arrayList);
    }

    public void setPath(ArrayList<PointBean> arrayList) {
        this.mMapInfoLlpv.setPath(arrayList);
    }

    public void setTrajectoryDrawingPath(ArrayList<PointBean> arrayList) {
        this.mMapInfoLlpv.setTrajectoryDrawingPath(arrayList);
    }

    public void setTrajectoryFollowPath(ArrayList<PointBean> arrayList) {
        this.mMapInfoLlpv.setTrajectoryDrawingFollowPath(arrayList);
    }

    public void setAllTrajectoryDrawingPath(List<List<PointBean>> list) {
        this.mMapInfoLlpv.setAllTrajectoryDrawingPath(list);
    }

    public void setHighLightIndex(int i) {
        this.mMapInfoLlpv.setHighLightIndex(i);
    }

    public void setVirtualWall(List<VirtualWallOperationGetWallsResponseBean.ValuesBean.VwallsBean.WallsBean> list) {
        this.mVirtualWall.setWallsBeans(list);
    }

    public void setBjd(List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean> list) {
        clearBjd();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            PointView pointView = new PointView(this.mContext);
            pointView.setPointInfo(list.get(i));
            arrayList.add(pointView);
        }
        this.mPointViewList.clear();
        this.mPointViewList.addAll(arrayList);
        showBjd();
    }

    public void setShowBjdState(boolean z) {
        this.isShowBjd = z;
    }

    public void clearBjd() {
        for (int i = 0; i < this.mPointViewList.size(); i++) {
            this.mMapPointLineAreaRl.removeView(this.mPointViewList.get(i));
        }
    }

    public void showBjd() {
        if (!LayerDataUtils.getInstance().getShowMarkPoint() || isHideViewBySoftType()) {
            clearBjd();
            return;
        }
        String str = TAG;
        MyLogUtils.Logd(str, "showBjd isShowBjd:" + this.isShowBjd + ";firstScale:" + this.firstScale + ";tempFirstScale:" + this.tempFirstScale + ";ScalePercentage:" + (this.firstScale / this.tempFirstScale) + "(int)ScalePercentage：" + ((int) (this.firstScale / this.tempFirstScale)));
        for (int i = 0; i < this.mPointViewList.size(); i++) {
            PointView pointView = this.mPointViewList.get(i);
            this.mMapPointLineAreaRl.removeView(pointView);
            pointView.setExtraInfo(this.mOriginX, this.mOriginY, this.mResolution, this.mMapIv.getImageMatrix());
            pointView.setScalePercentage(this.firstScale / this.tempFirstScale);
            this.mMapPointLineAreaRl.addView(pointView);
        }
    }

    public float getFirstDownX() {
        return this.firstDownX;
    }

    public void setFirstDownX(float f) {
        this.firstDownX = f;
    }

    public float getFirstDownY() {
        return this.firstDownY;
    }

    public void setFirstDownY(float f) {
        this.firstDownY = f;
    }

    public void setMapOnLongClickListener(MyMapViewOnLongClickListener myMapViewOnLongClickListener) {
        this.mapOnLongClickListener = myMapViewOnLongClickListener;
        setOnLongClickListener(this);
    }

    public MyMapViewOnLongClickListener getMapOnLongClickListener() {
        return this.mapOnLongClickListener;
    }

    private class MyTouchListener implements View.OnTouchListener {
        private static final int MODE_DRAG = 1;
        private static final int MODE_NORMAL = 0;
        private static final int MODE_ZOOM = 2;
        private Matrix mCurrentMatrix;
        private volatile boolean mHavDragOutsideMap;
        private Matrix mMatrix;
        private PointF mMidPoint;
        List<SetLayeredMapManagerPencilOpPublishBean.MsgBean.PointInfoBean> mPointInfo;
        private float mStartDis;
        private PointF mStartPoint;
        private float mTheta;
        private int mode;

        private MyTouchListener() {
            this.mode = 0;
            this.mStartPoint = new PointF();
            this.mMatrix = new Matrix();
            this.mCurrentMatrix = new Matrix();
            this.mHavDragOutsideMap = false;
            this.mPointInfo = new ArrayList();
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (MapRlView.this.editType == 1) {
                MapRlView.this.mVirtualWall.setScale(MapRlView.this.firstScale);
                return MapRlView.this.mVirtualWall.onWallTouchEvent(motionEvent);
            } else if (MapRlView.this.editType == 2) {
                MapRlView.this.mMapAreaView.setScale(MapRlView.this.firstScale);
                return MapRlView.this.mMapAreaView.onAreaTouchEvent(motionEvent);
            } else {
                if (MapRlView.this.mSoftType == 7) {
                    MapRlView.this.mRectView.setScale(MapRlView.this.firstScale);
                    MapRlView.this.mRectView.onRectTouchEvent(motionEvent);
                }
                if (MapRlView.this.mPointViewList.size() > 0) {
                    for (int i = 0; i < MapRlView.this.mPointViewList.size(); i++) {
                        ((PointView) MapRlView.this.mPointViewList.get(i)).onPointTouchEvent(motionEvent);
                    }
                }
                try {
                    int action = motionEvent.getAction() & 255;
                    if (action == 0) {
                        if (MapRlView.this.mBrushType == 1 || MapRlView.this.mBrushType == 2 || MapRlView.this.mBrushType == 3) {
                            MapRlView.this.mMapInfoLlpv.clearBrushZone();
                        }
                        this.mode = 1;
                        this.mHavDragOutsideMap = MapRlView.this.isClickMapOutside(motionEvent);
                        this.mStartPoint.set(motionEvent.getX(), motionEvent.getY());
                        if (!this.mHavDragOutsideMap) {
                            this.mCurrentMatrix.set(MapRlView.this.mMapIv.getImageMatrix());
                            this.mMatrix.set(this.mCurrentMatrix);
                            MapRlView.this.mMapInfoLlpv.clearBrushZone();
                        }
                    } else if (action == 1) {
                        boolean unused = MapRlView.this.longPressTag = System.currentTimeMillis() - MapRlView.this.downTime > 2000;
                        if (this.mode == 1) {
                            MapRlView.this.mMapInfoLlpv.setShowTarget(false);
                            if (!(MapRlView.this.mSoftType == 3 || MapRlView.this.mSoftType == 1)) {
                                if (MapRlView.this.mSoftType != 12) {
                                    if (MapRlView.this.mSoftType == 4 && (MapRlView.this.mBrushType == 1 || MapRlView.this.mBrushType == 2 || MapRlView.this.mBrushType == 3)) {
                                        MapRlView.this.mMapInfoLlpv.sendBrushZone();
                                    }
                                }
                            }
                            float[] fArr = {(float) ((int) this.mStartPoint.x), (float) ((int) this.mStartPoint.y)};
                            Matrix matrix = new Matrix();
                            this.mMatrix.invert(matrix);
                            matrix.mapPoints(fArr);
                            if (!this.mHavDragOutsideMap) {
                                if (MapRlView.this.mSoftType == 3) {
                                    MapRlView.this.mMapInfoLlpv.calibrationPoint(fArr[0], fArr[1], this.mTheta);
                                } else if (MapRlView.this.mSoftType == 1 && App.getNavType() == 0 && !MapRlView.this.mLockTargetPoint) {
                                    if (((float) ConvertUtils.px2dp(getDistanceBetween2Points(this.mStartPoint.x, this.mStartPoint.y, motionEvent.getX(), motionEvent.getY()))) > 3.0f) {
                                        if (MapRlView.this.mMapViewListener == null) {
                                            MapRlView.this.mMapInfoLlpv.moveToPoint(fArr[0], fArr[1], this.mTheta);
                                        } else if (!MapRlView.this.mMapViewListener.navigate()) {
                                            MapRlView.this.mMapInfoLlpv.moveToPoint(fArr[0], fArr[1], this.mTheta);
                                        }
                                    }
                                } else if (MapRlView.this.mSoftType == 12) {
                                    if (((float) ConvertUtils.px2dp(getDistanceBetween2Points(this.mStartPoint.x, this.mStartPoint.y, motionEvent.getX(), motionEvent.getY()))) > 3.0f && MapRlView.this.onMapViewAddRandomPointListener != null) {
                                        PointF worldCoordinates = MapRlView.this.mMapInfoLlpv.getWorldCoordinates(fArr[0], fArr[1]);
                                        MapRlView.this.onMapViewAddRandomPointListener.onAddRandomPoint(worldCoordinates.x, worldCoordinates.y, this.mTheta);
                                    }
                                }
                            }
                        }
                        this.mode = 0;
                    } else if (action != 2) {
                        if (action == 5) {
                            this.mode = 2;
                            MapRlView.this.mMapInfoLlpv.setShowTarget(false);
                            float px2dp = (float) ConvertUtils.px2dp(distance(motionEvent));
                            this.mStartDis = px2dp;
                            if (px2dp > 10.0f) {
                                this.mMidPoint = mid(motionEvent);
                                this.mCurrentMatrix.set(MapRlView.this.mMapIv.getImageMatrix());
                            }
                        } else if (action == 6) {
                            this.mode = 0;
                        }
                    } else if (this.mode == 2) {
                        this.mMatrix.set(this.mCurrentMatrix);
                        this.mMatrix.postTranslate(motionEvent.getX() - this.mStartPoint.x, motionEvent.getY() - this.mStartPoint.y);
                        float px2dp2 = (float) ConvertUtils.px2dp(distance(motionEvent));
                        if (px2dp2 > 10.0f) {
                            float f = px2dp2 / this.mStartDis;
                            this.mMatrix.postScale(f, f, this.mMidPoint.x, this.mMidPoint.y);
                        }
                    }
                    if ((motionEvent.getAction() & 255) == 2) {
                        if (this.mode == 2) {
                            MapRlView.this.mMapInfoLlpv.setMapMatrix(this.mMatrix);
                            MapRlView.this.mMapIv.setImageMatrix(this.mMatrix);
                            MapRlView.this.mVirtualWall.setMapMatrix(this.mMatrix);
                            MapRlView.this.mMapAreaView.setMapMatrix(this.mMatrix);
                            MapRlView.this.mRectView.setMapMatrix(this.mMatrix);
                            float[] fArr2 = new float[9];
                            this.mMatrix.getValues(fArr2);
                            float unused2 = MapRlView.this.firstScale = fArr2[0];
                            MapRlView.this.showBjd();
                            MapRlView.this.showVisualLabelPoint();
                        } else if (this.mode == 1 && !this.mHavDragOutsideMap) {
                            MapRlView.this.mMapInfoLlpv.setShowTarget(true);
                            MapRlView.this.mMapInfoLlpv.setShowAddRandomPoint(true);
                            float x = motionEvent.getX();
                            float y = motionEvent.getY();
                            float px2dp3 = (float) ConvertUtils.px2dp(getDistanceBetween2Points(this.mStartPoint.x, this.mStartPoint.y, x, y));
                            if (MapRlView.this.mSoftType == 4) {
                                PointF pointF = new PointF();
                                pointF.x = motionEvent.getX();
                                pointF.y = motionEvent.getY();
                                float[] fArr3 = {pointF.x, pointF.y};
                                Matrix matrix2 = new Matrix();
                                this.mMatrix.invert(matrix2);
                                matrix2.mapPoints(fArr3);
                                pointF.x = fArr3[0];
                                pointF.y = fArr3[1];
                                if (MapRlView.this.mBrushType != 0) {
                                    MapRlView.this.mMapInfoLlpv.brushZone(pointF, MapRlView.this.mLeftToolType, MapRlView.this.mBrushType);
                                }
                            } else if (px2dp3 > 6.0f) {
                                this.mTheta = (float) Math.atan2((double) (this.mStartPoint.y - y), (double) (this.mStartPoint.x - x));
                                float[] fArr4 = {(float) ((int) this.mStartPoint.x), (float) ((int) this.mStartPoint.y)};
                                Matrix matrix3 = new Matrix();
                                this.mMatrix.invert(matrix3);
                                matrix3.mapPoints(fArr4);
                                PointBean pointBean = new PointBean();
                                pointBean.setX(fArr4[0]);
                                pointBean.setY(fArr4[1]);
                                pointBean.setTheta(this.mTheta);
                                if (MapRlView.this.mSoftType == 1 && MapRlView.this.mSoftType == 12 && MapRlView.this.mLockTargetPoint) {
                                    MapRlView.this.mMapInfoLlpv.setTagePoint((PointBean) null);
                                    MapRlView.this.mMapInfoLlpv.setRobotAddRandomPointPosition((PointBean) null);
                                } else {
                                    MapRlView.this.mMapInfoLlpv.setTagePoint(pointBean);
                                    MapRlView.this.mMapInfoLlpv.setRobotAddRandomPointPosition(pointBean);
                                }
                            }
                        }
                        MapRlView.this.getParent().requestDisallowInterceptTouchEvent(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (MapRlView.this.getMapOnLongClickListener() == null) {
                    return true;
                }
                return false;
            }
        }

        public float getDistanceBetween2Points(float f, float f2, float f3, float f4) {
            return (float) Math.sqrt(Math.pow((double) (f2 - f4), 2.0d) + Math.pow((double) (f - f3), 2.0d));
        }

        private float distance(MotionEvent motionEvent) {
            float x = motionEvent.getX(1) - motionEvent.getX(0);
            float y = motionEvent.getY(1) - motionEvent.getY(0);
            return (float) Math.sqrt((double) ((x * x) + (y * y)));
        }

        private PointF mid(MotionEvent motionEvent) {
            return new PointF((motionEvent.getX(1) + motionEvent.getX(0)) / 2.0f, (motionEvent.getY(1) + motionEvent.getY(0)) / 2.0f);
        }
    }

    private void showVirtualWall() {
        if (isHideViewBySoftType()) {
            this.mVirtualWall.setVisibility(8);
        } else if (this.mSoftType == 4) {
            if (TextUtils.equals(this.mLeftToolType, "layer_virtual_wall") || LayerDataUtils.getInstance().getShowVirtualWall()) {
                this.mVirtualWall.setVisibility(0);
            } else {
                this.mVirtualWall.setVisibility(8);
            }
        } else if (LayerDataUtils.getInstance().getShowVirtualWall()) {
            this.mVirtualWall.setVisibility(0);
        } else {
            this.mVirtualWall.setVisibility(8);
        }
    }

    public void showMapAreaView() {
        this.mMapAreaView.convertChassisAreaType(this.mLeftToolType);
        this.mMapAreaView.setScale(this.firstScale);
        int i = 8;
        if (isHideViewBySoftType()) {
            this.mMapAreaView.setVisibility(8);
        } else if (this.mSoftType != 4) {
            MapAreaView mapAreaView = this.mMapAreaView;
            if (LayerDataUtils.getInstance().getShowArea()) {
                i = 0;
            }
            mapAreaView.setVisibility(i);
            this.mMapAreaView.resetAreaType();
        } else if (ConvertChassisAreaType.isEqualLeftToolType(this.mLeftToolType)) {
            this.mMapAreaView.setVisibility(0);
            this.mMapAreaView.invalidate();
        } else {
            this.mMapAreaView.setVisibility(0);
            clearUnfinishedArea();
        }
    }

    private boolean isHideViewBySoftType() {
        return this.mSoftType == 2;
    }

    public void refreshView() {
        showBjd();
        showVirtualWall();
        showMapAreaView();
    }

    public void changeLeftToolType(String str) {
        this.editType = 0;
        this.mBrushType = 0;
        this.mLeftToolType = str;
        showVirtualWall();
        showMapAreaView();
    }

    public void changeShapeType(int i) {
        this.editType = i;
        this.mVirtualWall.setShapeType(i);
        if (i != 1) {
            this.mVirtualWall.cleanRect();
        } else {
            this.mVirtualWall.setScale(this.firstScale);
        }
        this.mVirtualWall.invalidate();
        if (i == 2) {
            this.mMapAreaView.convertChassisAreaType(this.mLeftToolType);
            this.mMapAreaView.setScale(this.firstScale);
            this.mMapAreaView.invalidate();
        }
    }

    public void changeSoftType(int i) {
        this.mSoftType = i;
        this.mMapInfoLlpv.setSoftType(i);
        showVirtualWall();
        showMapAreaView();
        showRectView();
        showVisualLabelPoint();
    }

    public void changeBrushType(int i) {
        this.mBrushType = i;
    }

    public void lockTargetPoint(boolean z) {
        this.mLockTargetPoint = z;
    }

    public void setSelectArea(AreaItemBean areaItemBean) {
        MapAreaView mapAreaView = this.mMapAreaView;
        if (mapAreaView != null) {
            mapAreaView.setScale(this.firstScale);
            AreaItemBean areaItemBean2 = (AreaItemBean) GsonUtils.fromLocalJson(GsonUtils.toJson(areaItemBean), AreaItemBean.class);
            this.mMapAreaView.convertDrawMapPosition(areaItemBean2);
            this.mMapAreaView.switchEditMode(areaItemBean2);
        }
    }

    public void clearUnfinishedArea() {
        MapAreaView mapAreaView = this.mMapAreaView;
        if (mapAreaView != null) {
            mapAreaView.clearUnfinishedArea();
        }
    }

    public void setOnDrawAreaListener(MapAreaView.OnDrawAreaListener onDrawAreaListener) {
        MapAreaView mapAreaView = this.mMapAreaView;
        if (mapAreaView != null) {
            mapAreaView.setOnDrawAreaListener(onDrawAreaListener);
        }
    }

    public void setAreaItemBeans(List<AreaItemBean> list) {
        MapAreaView mapAreaView = this.mMapAreaView;
        if (mapAreaView != null) {
            mapAreaView.clearAllView();
            this.mMapAreaView.setAreaItemBeans((List) GsonUtils.fromLocalJson(GsonUtils.toJson(list), new TypeToken<List<AreaItemBean>>() {
            }.getType()));
        }
    }

    /* access modifiers changed from: private */
    public boolean isClickMapOutside(MotionEvent motionEvent) {
        float[] fArr = {motionEvent.getX(), motionEvent.getY()};
        Matrix matrix = new Matrix();
        this.mMapIv.getImageMatrix().invert(matrix);
        matrix.mapPoints(fArr);
        float f = fArr[0];
        float f2 = fArr[1];
        if (f < 0.0f || f2 < 0.0f || f > this.mMapWidth || f2 > this.mMapHeight) {
            return true;
        }
        return false;
    }

    public void setMapViewListener(OnMapViewListener onMapViewListener) {
        this.mMapViewListener = onMapViewListener;
    }

    public void showRectView() {
        if (this.mSoftType == 7) {
            this.mRectView.setVisibility(0);
            this.mRectView.refresh();
            return;
        }
        this.mRectView.setVisibility(8);
    }

    public List<GlobalLocateReqBean.ArgsBean.SearchBoundaryBean.PolygonBean.PointsBean> getRectPoints() {
        RectView rectView = this.mRectView;
        if (rectView != null) {
            return rectView.getPoints();
        }
        return null;
    }

    public void setVisualLabelPoint(List<ApriltagsBufferBean.MsgBean.WaypointsBean> list) {
        clearVisualLabelPoint();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            list.get(i).getName();
            list.get(i).getBehavior_code();
            float x = list.get(i).getPose().getPosition().getX();
            float y = list.get(i).getPose().getPosition().getY();
            float f = x - this.mOriginX;
            float f2 = this.mResolution;
            float f3 = f / f2;
            float f4 = (y - this.mOriginY) / f2;
            PointView pointView = new PointView(this.mContext);
            pointView.setPointInfo((MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean) GsonUtils.fromLocalJson(GsonUtils.toJson(list.get(i)), MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean.class));
            arrayList.add(pointView);
            String str = TAG;
            MyLogUtils.Logd(str, "setVisualLabelPoint x:" + f3 + "  y:" + f4 + " mOriginX:" + this.mOriginX + " mOriginY:" + this.mOriginY + " mResolution:" + this.mResolution);
        }
        this.mVisualLabelPointViewList.clear();
        this.mVisualLabelPointViewList.addAll(arrayList);
        showVisualLabelPoint();
    }

    public void clearVisualLabelPoint() {
        this.mMapVisualLabelRl.removeAllViews();
    }

    public void showVisualLabelPoint() {
        if (!LayerDataUtils.getInstance().getShowLabelPoint()) {
            this.mMapVisualLabelRl.setVisibility(8);
            return;
        }
        if (isHideViewBySoftType()) {
            this.mMapVisualLabelRl.setVisibility(8);
        } else {
            this.mMapVisualLabelRl.setVisibility(0);
        }
        for (int i = 0; i < this.mVisualLabelPointViewList.size(); i++) {
            PointView pointView = this.mVisualLabelPointViewList.get(i);
            this.mMapVisualLabelRl.removeView(pointView);
            pointView.setExtraInfo(this.mOriginX, this.mOriginY, this.mResolution, this.mMapIv.getImageMatrix());
            pointView.setScalePercentage(this.firstScale / this.tempFirstScale);
            this.mMapVisualLabelRl.addView(pointView);
        }
    }

    public void setOnMapViewAddRandomPointListener(OnMapViewAddRandomPointListener onMapViewAddRandomPointListener2) {
        this.onMapViewAddRandomPointListener = onMapViewAddRandomPointListener2;
    }
}
