package mc.csst.com.selfchassislibrary.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import androidx.core.view.InputDeviceCompat;
import com.blankj.utilcode.util.ConvertUtils;
import com.ciot.base.util.ThreadPoolUtils;
import java.util.ArrayList;
import java.util.List;
import mc.csst.com.selfchassislibrary.bean.MapInfoBean;
import mc.csst.com.selfchassislibrary.bean.PointBean;
import mc.csst.com.selfchassislibrary.bean.msg.AreaItemBean;
import mc.csst.com.selfchassislibrary.bean.msg.MarkerOperationGetMarkersResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.PathGetResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.SetLayeredMapManagerPencilOpPublishBean;
import mc.csst.com.selfchassislibrary.bean.msg.SetVirtualWallsBean;
import mc.csst.com.selfchassislibrary.bean.msg.VirtualWallOperationGetWallsResponseBean;
import mc.csst.com.selfchassislibrary.chassis.SelfChassis;
import mc.csst.com.selfchassislibrary.chassis.SelfChassisMsgCallBack;
import mc.csst.com.selfchassislibrary.utils.ConvertorUtils;
import mc.csst.com.selfchassislibrary.utils.OtherUtils;

public class EditMapView extends SurfaceView implements SurfaceHolder.Callback, SelfChassisMsgCallBack.OnMapInfoCallBack {
    public static final int EDIT_MAP_TYPE_BARRIER = 3;
    public static final int EDIT_MAP_TYPE_FREE = 2;
    public static final int EDIT_MAP_TYPE_UNKNOWN = 1;
    private static final String TAG = MapView.class.getSimpleName();
    public static final int TYPE_EDIT_MAP = 1;
    public static final int TYPE_VIRTUAL_WALL = 0;
    private float actionX;
    private float actionY;
    private float degree;
    /* access modifiers changed from: private */
    public volatile boolean done = false;
    private boolean mAllowTouch;
    Paint mBgPaint = new Paint();
    /* access modifiers changed from: private */
    public float mCurrentScale = 1.0f;
    Paint mDrawVirtualWallPaint = new Paint();
    Path mDrawVirtualWallPath = new Path();
    /* access modifiers changed from: private */
    public int mEditMapType = 2;
    private float mEndX;
    private float mEndY;
    /* access modifiers changed from: private */
    public int mIndex = 0;
    private boolean mIsMove = false;
    /* access modifiers changed from: private */
    public Bitmap mMapBm;
    private PointF mMapCenter = new PointF();
    /* access modifiers changed from: private */
    public Matrix mMapMatrix = new Matrix();
    private float mOriginX;
    private float mOriginY;
    List<PointBean> mPointBeanList = new ArrayList();
    List<SetLayeredMapManagerPencilOpPublishBean.MsgBean.PointInfoBean> mPointInfo = new ArrayList();
    private float mResolution;
    /* access modifiers changed from: private */
    public float[] mSelectVirtualWall;
    Paint mSelectVirtualWallPaint = new Paint();
    int mSize = 0;
    private float mStartX;
    private float mStartY;
    private int mType;
    /* access modifiers changed from: private */
    public float[] mVirtualWall;
    Paint mVirtualWallPaint = new Paint();
    private List<VirtualWallOperationGetWallsResponseBean.ValuesBean.VwallsBean.WallsBean> mWalls;
    private int mWindowHeight;
    private int mWindowWidth;
    private int moveType;
    private float rotation;
    private Runnable runnable;
    private float spacing;
    private float translationX;
    private float translationY;

    public void area(List<AreaItemBean> list) {
    }

    public void bjd(List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean> list) {
    }

    public void currentMapArea(double d) {
    }

    public void downCamData(ArrayList<PointBean> arrayList) {
    }

    public void laser(ArrayList<PointBean> arrayList) {
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

    public void path(ArrayList<PointBean> arrayList) {
    }

    public void pose(PointBean pointBean) {
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    public void targetName(String str) {
    }

    public void upCamData(ArrayList<PointBean> arrayList) {
    }

    public EditMapView(Context context) {
        super(context);
        init();
    }

    public EditMapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public EditMapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        Log.e(TAG, "init: ");
        getHolder().addCallback(this);
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

    private float getSpacing(MotionEvent motionEvent) {
        float x = motionEvent.getX(0) - motionEvent.getX(1);
        float y = motionEvent.getY(0) - motionEvent.getY(1);
        return (float) Math.sqrt((double) ((x * x) + (y * y)));
    }

    private float getDegree(MotionEvent motionEvent) {
        return (float) Math.toDegrees(Math.atan2((double) (motionEvent.getY(0) - motionEvent.getY(1)), (double) (motionEvent.getX(0) - motionEvent.getX(1))));
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            this.moveType = 1;
            this.actionX = motionEvent.getRawX();
            this.actionY = motionEvent.getRawY();
            this.mStartX = motionEvent.getX();
            this.mStartY = motionEvent.getY();
            this.mPointInfo.clear();
            Log.e("MotionEvent ACTION_MASK", "ACTION_DOWN");
        } else if (action == 1) {
            if (this.moveType == 1) {
                this.mEndX = motionEvent.getX();
                this.mEndY = motionEvent.getY();
                this.mDrawVirtualWallPath.reset();
                this.mPointBeanList.clear();
                if (this.mIsMove) {
                    int i = this.mType;
                    if (i == 0) {
                        Matrix matrix = new Matrix();
                        float[] fArr = new float[2];
                        float[] fArr2 = new float[2];
                        if (this.mMapMatrix.invert(matrix)) {
                            matrix.mapPoints(fArr, new float[]{this.mStartX, this.mStartY});
                            matrix.mapPoints(fArr2, new float[]{this.mEndX, this.mEndY});
                            String uuid = OtherUtils.getUUID();
                            SetVirtualWallsBean.MsgBean.WallsBean wallsBean = new SetVirtualWallsBean.MsgBean.WallsBean();
                            float f = this.mOriginX;
                            float f2 = fArr[0];
                            float f3 = this.mResolution;
                            float f4 = this.mOriginY;
                            float f5 = (fArr[1] * f3) + f4;
                            wallsBean.setStart_x((f2 * f3) + f);
                            wallsBean.setStart_y(f5);
                            wallsBean.setEnd_x(f + (fArr2[0] * f3));
                            wallsBean.setEnd_y(f4 + (fArr2[1] * f3));
                            wallsBean.setWall_id(uuid);
                            SelfChassis.getInstance().appendVirtualWalls(wallsBean);
                            SystemClock.sleep(200);
                            SelfChassis.getInstance().serviceGetVirtualWalls();
                        }
                    } else if (i == 1) {
                        SelfChassis.getInstance().setLayeredMapManagerPencilOp(this.mPointInfo);
                        this.mPointInfo.clear();
                    }
                }
            }
            this.mIsMove = false;
            this.moveType = 0;
            Log.e("MotionEvent ACTION_MASK", "ACTION_UP");
        } else if (action == 2) {
            this.mEndX = motionEvent.getX();
            float y = motionEvent.getY();
            this.mEndY = y;
            int i2 = this.moveType;
            if (i2 == 1) {
                float px2dp = (float) ConvertUtils.px2dp(ConvertorUtils.getDistanceBetween2Points(this.mStartX, this.mStartY, this.mEndX, y));
                Log.e("distance", px2dp + "");
                this.mIsMove = true;
                this.mDrawVirtualWallPath.reset();
                int i3 = this.mType;
                if (i3 == 0) {
                    if (px2dp > 20.0f) {
                        this.mDrawVirtualWallPath.setFillType(Path.FillType.WINDING);
                        this.mDrawVirtualWallPath.moveTo(this.mStartX, this.mStartY);
                        this.mDrawVirtualWallPath.lineTo(this.mEndX, this.mEndY);
                        this.mDrawVirtualWallPath.close();
                    }
                } else if (i3 == 1) {
                    Log.e(TAG, "onTouchEvent: ");
                    Matrix matrix2 = new Matrix();
                    float[] fArr3 = new float[2];
                    if (this.mMapMatrix.invert(matrix2)) {
                        PointBean pointBean = new PointBean();
                        pointBean.setX(this.mEndX);
                        pointBean.setY(this.mEndY);
                        this.mPointBeanList.add(pointBean);
                        matrix2.mapPoints(fArr3, new float[]{this.mEndX, this.mEndY});
                        float f6 = this.mOriginX;
                        float f7 = fArr3[0];
                        float f8 = this.mResolution;
                        float f9 = f6 + (f7 * f8);
                        SetLayeredMapManagerPencilOpPublishBean.MsgBean.PointInfoBean pointInfoBean = new SetLayeredMapManagerPencilOpPublishBean.MsgBean.PointInfoBean();
                        pointInfoBean.setPx(f9);
                        pointInfoBean.setPy(this.mOriginY + (fArr3[1] * f8));
                        pointInfoBean.setOp_color(this.mEditMapType);
                        pointInfoBean.setOp_size(this.mSize);
                        this.mPointInfo.add(pointInfoBean);
                    }
                }
                Log.e("MotionEvent ACTION_MASK", "ACTION_MOVE");
            } else if (i2 == 2) {
                this.mDrawVirtualWallPath.reset();
                this.translationX = (this.translationX + motionEvent.getRawX()) - this.actionX;
                this.translationY = (this.translationY + motionEvent.getRawY()) - this.actionY;
                setTranslationX(this.translationX);
                setTranslationY(this.translationY);
                this.actionX = motionEvent.getRawX();
                this.actionY = motionEvent.getRawY();
                float spacing2 = (this.mCurrentScale * getSpacing(motionEvent)) / this.spacing;
                this.mCurrentScale = spacing2;
                setScaleX(spacing2);
                setScaleY(this.mCurrentScale);
            }
        } else if (action == 5) {
            this.moveType = 2;
            this.spacing = getSpacing(motionEvent);
            this.degree = getDegree(motionEvent);
            Log.e("MotionEvent ACTION_MASK", "ACTION_POINTER_DOWN");
        } else if (action == 6) {
            this.moveType = 0;
            Log.e("MotionEvent ACTION_MASK", "ACTION_POINTER_UP");
        }
        return true;
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.done = false;
        draw();
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.done = true;
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

    public void virtualWall(List<VirtualWallOperationGetWallsResponseBean.ValuesBean.VwallsBean.WallsBean> list) {
        this.mWalls = list;
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

    public void draw() {
        if (this.runnable == null) {
            this.runnable = new Runnable() {
                public void run() {
                    SurfaceHolder holder = EditMapView.this.getHolder();
                    while (!EditMapView.this.done) {
                        Canvas lockCanvas = holder.lockCanvas();
                        if (lockCanvas != null) {
                            synchronized (holder) {
                                try {
                                    drawBg(lockCanvas);
                                    drawMap(lockCanvas);
                                    drawPoint(lockCanvas);
                                    lockCanvas.save();
                                    drawVirtualWall(lockCanvas);
                                    drawSelectVirtualWall(lockCanvas);
                                    drawTouchPath(lockCanvas);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            holder.unlockCanvasAndPost(lockCanvas);
                        }
                        SystemClock.sleep(80);
                    }
                }

                private void drawBg(Canvas canvas) {
                    if (canvas != null) {
                        EditMapView.this.mBgPaint.setColor(-3355444);
                        EditMapView.this.mBgPaint.setStyle(Paint.Style.FILL);
                        canvas.drawRect(new Rect(0, 0, EditMapView.this.getWidth(), EditMapView.this.getHeight()), EditMapView.this.mBgPaint);
                    }
                }

                private void drawMap(Canvas canvas) {
                    if (canvas != null && EditMapView.this.mMapBm != null) {
                        canvas.drawBitmap(EditMapView.this.mMapBm, EditMapView.this.mMapMatrix, (Paint) null);
                    }
                }

                private void drawVirtualWall(Canvas canvas) {
                    if (canvas != null && EditMapView.this.mVirtualWall != null) {
                        EditMapView.this.mVirtualWallPaint.setColor(-7829368);
                        EditMapView.this.mVirtualWallPaint.setStrokeWidth(2.0f);
                        canvas.setMatrix(EditMapView.this.mMapMatrix);
                        canvas.drawLines(EditMapView.this.mVirtualWall, EditMapView.this.mVirtualWallPaint);
                    }
                }

                private void drawSelectVirtualWall(Canvas canvas) {
                    if (canvas != null && EditMapView.this.mVirtualWall != null && EditMapView.this.mVirtualWall.length != 0) {
                        float[] unused = EditMapView.this.mSelectVirtualWall = new float[4];
                        EditMapView.this.mSelectVirtualWall[0] = EditMapView.this.mVirtualWall[EditMapView.this.mIndex * 4];
                        EditMapView.this.mSelectVirtualWall[1] = EditMapView.this.mVirtualWall[(EditMapView.this.mIndex * 4) + 1];
                        EditMapView.this.mSelectVirtualWall[2] = EditMapView.this.mVirtualWall[(EditMapView.this.mIndex * 4) + 2];
                        EditMapView.this.mSelectVirtualWall[3] = EditMapView.this.mVirtualWall[(EditMapView.this.mIndex * 4) + 3];
                        EditMapView.this.mSelectVirtualWallPaint.setColor(InputDeviceCompat.SOURCE_ANY);
                        EditMapView.this.mSelectVirtualWallPaint.setStrokeWidth(2.0f);
                        canvas.setMatrix(EditMapView.this.mMapMatrix);
                        canvas.drawLines(EditMapView.this.mSelectVirtualWall, EditMapView.this.mSelectVirtualWallPaint);
                    }
                }

                private void drawTouchPath(Canvas canvas) {
                    if (canvas != null) {
                        canvas.restore();
                        EditMapView.this.mDrawVirtualWallPaint.setColor(-16776961);
                        EditMapView.this.mDrawVirtualWallPaint.setStrokeWidth(9.0f);
                        EditMapView.this.mDrawVirtualWallPaint.setStrokeCap(Paint.Cap.ROUND);
                        EditMapView.this.mDrawVirtualWallPaint.setStyle(Paint.Style.STROKE);
                        canvas.drawPath(EditMapView.this.mDrawVirtualWallPath, EditMapView.this.mDrawVirtualWallPaint);
                    }
                }

                private void drawPoint(Canvas canvas) {
                    if (canvas != null) {
                        Paint paint = new Paint();
                        paint.setStrokeWidth(((float) ((EditMapView.this.mSize * 2) + 1)) * EditMapView.this.mCurrentScale);
                        if (EditMapView.this.mEditMapType == 1) {
                            paint.setColor(-3355444);
                        } else if (EditMapView.this.mEditMapType == 3) {
                            paint.setColor(-16777216);
                        } else if (EditMapView.this.mEditMapType == 2) {
                            paint.setColor(-1);
                        }
                        PointBean[] pointBeanArr = (PointBean[]) EditMapView.this.mPointBeanList.toArray(new PointBean[EditMapView.this.mPointBeanList.size()]);
                        float[] fArr = new float[(pointBeanArr.length * 2)];
                        for (int i = 0; i < pointBeanArr.length; i++) {
                            int i2 = i * 2;
                            fArr[i2] = pointBeanArr[i].getX();
                            fArr[i2 + 1] = pointBeanArr[i].getY();
                        }
                        canvas.drawPoints(fArr, paint);
                    }
                }
            };
        }
        if (!this.done) {
            ThreadPoolUtils.getInstance().execute(this.runnable);
        }
    }

    public int nextVirtualWall() {
        float[] fArr = this.mVirtualWall;
        if (fArr == null || fArr.length == 0) {
            return 0;
        }
        int length = (fArr.length / 4) - 1;
        int i = this.mIndex;
        if (i < length) {
            this.mIndex = i + 1;
        } else {
            this.mIndex = 0;
        }
        return this.mIndex;
    }

    public int previousVirtualWall() {
        float[] fArr = this.mVirtualWall;
        if (fArr == null || fArr.length == 0) {
            return 0;
        }
        int length = (fArr.length / 4) - 1;
        int i = this.mIndex;
        if (i > 0) {
            this.mIndex = i - 1;
        } else {
            this.mIndex = length;
        }
        return this.mIndex;
    }

    public int getSelectIndex() {
        return this.mIndex;
    }

    public String getSelectVirtualWallName() {
        int i;
        List<VirtualWallOperationGetWallsResponseBean.ValuesBean.VwallsBean.WallsBean> list = this.mWalls;
        if (list == null || list.size() == 0 || this.mWalls.size() == (i = this.mIndex)) {
            return null;
        }
        return this.mWalls.get(i).getWall_id();
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

    public boolean isAllowTouch() {
        return this.mAllowTouch;
    }

    public void setAllowTouch(boolean z) {
        this.mAllowTouch = z;
    }

    public int getType() {
        return this.mType;
    }

    public void setType(int i) {
        this.mType = i;
    }

    public int getEditMapType() {
        return this.mEditMapType;
    }

    public void setEditMapType(int i) {
        this.mEditMapType = i;
    }

    public int getSize() {
        return this.mSize;
    }

    public void setSize(int i) {
        this.mSize = i;
    }

    public void resetCanvas() {
        setTranslationX(0.0f);
        setTranslationY(0.0f);
        setScaleX(1.0f);
        setScaleY(1.0f);
    }
}
