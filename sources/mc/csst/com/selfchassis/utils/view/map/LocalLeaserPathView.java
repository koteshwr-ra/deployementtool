package mc.csst.com.selfchassis.utils.view.map;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.SurfaceTexture;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.TextureView;
import androidx.core.internal.view.SupportMenu;
import com.blankj.utilcode.util.ColorUtils;
import com.ciot.base.storage.MySpUtils;
import com.ciot.base.util.MyLogUtils;
import com.ciot.base.util.ThreadPoolUtils;
import com.ciot.sentrymove.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import mc.csst.com.selfchassis.App;
import mc.csst.com.selfchassis.model.trajectory.TrajectoryModel;
import mc.csst.com.selfchassis.ui.widget.BezierView;
import mc.csst.com.selfchassis.utils.LayerDataUtils;
import mc.csst.com.selfchassis.utils.MultiRobotUtil;
import mc.csst.com.selfchassis.utils.RandomColorUtil;
import mc.csst.com.selfchassis.utils.SpConstant;
import mc.csst.com.selfchassis.utils.enumbs.NavStateType;
import mc.csst.com.selfchassislibrary.bean.MapInfoBean;
import mc.csst.com.selfchassislibrary.bean.PointBean;
import mc.csst.com.selfchassislibrary.bean.msg.RobotListRespBean;
import mc.csst.com.selfchassislibrary.bean.msg.SetLayeredMapManagerPencilOpPublishBean;
import mc.csst.com.selfchassislibrary.chassis.SelfChassis;
import mc.csst.com.selfchassislibrary.chassis.SelfChassisState;

public class LocalLeaserPathView extends TextureView {
    private static final int ROBOT_ICON_SIZE = 12;
    /* access modifiers changed from: private */
    public static final String TAG = LocalLeaserPathView.class.getSimpleName();
    /* access modifiers changed from: private */
    public static volatile Matrix mMapMatrix;
    /* access modifiers changed from: private */
    public BezierView bezierView;
    /* access modifiers changed from: private */
    public HashMap<List<PointF>, Integer> colorMap;
    /* access modifiers changed from: private */
    public volatile boolean done;
    /* access modifiers changed from: private */
    public int highLightIndex;
    /* access modifiers changed from: private */
    public PointBean mAddRandomPositionTargetPose;
    /* access modifiers changed from: private */
    public List<List<PointF>> mAllTrajectoryPointList;
    List<PointF> mBrushPointList;
    int mBrushSize;
    private SetLayeredMapManagerPencilOpPublishBean.MsgBean.PointInfoBean mBrushZonePointInfo;
    /* access modifiers changed from: private */
    public float mCamPointWidth;
    /* access modifiers changed from: private */
    public Canvas mCanvas;
    Paint mDestinationPaint;
    Paint mDestinationPaintCircle;
    Paint mDestinationPaintCircleBorder;
    /* access modifiers changed from: private */
    public final Path mDestinationPath;
    Paint mDownCamPointCloudPaint;
    List<PointBean> mDownCamPointList;
    /* access modifiers changed from: private */
    public volatile PointF[] mDrawBrushPointF;
    /* access modifiers changed from: private */
    public volatile Paint mDrawBrushPointPaint;
    /* access modifiers changed from: private */
    public volatile float[] mDrawBrushPoints;
    private boolean mIsNeedDrawCurrentLocation;
    Paint mLaserPaint;
    /* access modifiers changed from: private */
    public float[] mLaserPoint;
    String mLeftToolType;
    /* access modifiers changed from: private */
    public volatile float mOriginX;
    /* access modifiers changed from: private */
    public volatile float mOriginY;
    Paint mPathPaint;
    /* access modifiers changed from: private */
    public float[] mPathPoint;
    List<SetLayeredMapManagerPencilOpPublishBean.MsgBean.PointInfoBean> mPointInfo;
    /* access modifiers changed from: private */
    public volatile Paint mPosePaint;
    Paint mPosePaintCircle;
    Paint mPosePaintCircleBorder;
    /* access modifiers changed from: private */
    public volatile float mResolution;
    /* access modifiers changed from: private */
    public final Path mRobotAddRandomPointPath;
    /* access modifiers changed from: private */
    public volatile PointBean mRobotCurrentPosition;
    /* access modifiers changed from: private */
    public final List<Path> mRobotListPath;
    /* access modifiers changed from: private */
    public final List<PointF> mRobotListPoint;
    /* access modifiers changed from: private */
    public volatile Path mRobotPosePath;
    private volatile double mRobotTheta;
    /* access modifiers changed from: private */
    public float mRobotX;
    /* access modifiers changed from: private */
    public float mRobotY;
    boolean mShowAddRandomPoint;
    boolean mShowTarget;
    /* access modifiers changed from: private */
    public volatile int mSoftType;
    /* access modifiers changed from: private */
    public PointBean mTagePose;
    /* access modifiers changed from: private */
    public List<PointF> mTrajectoryFollowPointList;
    /* access modifiers changed from: private */
    public List<PointF> mTrajectoryPointList;
    Paint mUpCamPointCloudPaint;
    List<PointBean> mUpCamPointList;
    private volatile PointF mWorldCoordinatesPoint;
    /* access modifiers changed from: private */
    public final Map<Path, String> robotIdPathColorMap;
    /* access modifiers changed from: private */
    public final Map<PointF, String> robotIdPointColorMap;
    private Runnable runnable;
    /* access modifiers changed from: private */
    public int targetAddRandomPositionX;
    /* access modifiers changed from: private */
    public int targetAddRandomPositionY;
    /* access modifiers changed from: private */
    public int targetX;
    /* access modifiers changed from: private */
    public int targetY;

    public LocalLeaserPathView(Context context) {
        this(context, (AttributeSet) null);
    }

    public LocalLeaserPathView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LocalLeaserPathView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.done = false;
        this.mSoftType = 0;
        this.mPosePaint = new Paint();
        this.mPosePaintCircleBorder = new Paint();
        this.mPosePaintCircle = new Paint();
        this.mDestinationPaint = new Paint();
        this.mDestinationPaintCircleBorder = new Paint();
        this.mDestinationPaintCircle = new Paint();
        this.mLaserPaint = new Paint();
        this.mUpCamPointCloudPaint = new Paint();
        this.mDownCamPointCloudPaint = new Paint();
        this.mPathPaint = new Paint();
        this.mRobotCurrentPosition = new PointBean();
        this.mRobotPosePath = new Path();
        this.bezierView = new BezierView();
        this.mTrajectoryPointList = new ArrayList();
        this.mAllTrajectoryPointList = new ArrayList();
        this.mTrajectoryFollowPointList = new ArrayList();
        this.highLightIndex = -1;
        this.mBrushSize = 0;
        this.mLeftToolType = "layer_normal";
        this.mIsNeedDrawCurrentLocation = true;
        this.mDrawBrushPointPaint = new Paint();
        this.mDrawBrushPointF = new PointF[0];
        this.mDrawBrushPoints = new float[0];
        this.mWorldCoordinatesPoint = new PointF();
        this.mBrushZonePointInfo = new SetLayeredMapManagerPencilOpPublishBean.MsgBean.PointInfoBean();
        this.mUpCamPointList = new Vector();
        this.mDownCamPointList = new Vector();
        this.mCamPointWidth = 2.0f;
        this.mRobotTheta = 0.0d;
        this.colorMap = new HashMap<>();
        this.mDestinationPath = new Path();
        this.mRobotAddRandomPointPath = new Path();
        this.mRobotListPath = new ArrayList();
        this.mRobotListPoint = new ArrayList();
        this.robotIdPathColorMap = new HashMap();
        this.robotIdPointColorMap = new HashMap();
        this.mPointInfo = new ArrayList();
        this.mBrushPointList = new ArrayList();
        init();
    }

    private void init() {
        setOpaque(false);
        setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
            }

            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
                String access$000 = LocalLeaserPathView.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onSurfaceTextureAvailable isMainThread:");
                sb.append(Looper.getMainLooper() == Looper.myLooper());
                MyLogUtils.Logd(access$000, sb.toString());
                boolean unused = LocalLeaserPathView.this.done = false;
                LocalLeaserPathView.this.draw();
            }

            public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
                MyLogUtils.Logd(LocalLeaserPathView.TAG, "onSurfaceTextureSizeChanged:");
            }

            public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                MyLogUtils.Logd(LocalLeaserPathView.TAG, "onSurfaceTextureDestroyed:");
                boolean unused = LocalLeaserPathView.this.done = true;
                return false;
            }
        });
    }

    public void draw() {
        if (this.runnable == null) {
            this.runnable = new Runnable() {
                public void run() {
                    while (!LocalLeaserPathView.this.done) {
                        LocalLeaserPathView localLeaserPathView = LocalLeaserPathView.this;
                        Canvas unused = localLeaserPathView.mCanvas = localLeaserPathView.lockCanvas();
                        if (LocalLeaserPathView.this.mCanvas != null) {
                            if (LocalLeaserPathView.mMapMatrix != null) {
                                LocalLeaserPathView.this.mCanvas.setMatrix(LocalLeaserPathView.mMapMatrix);
                            }
                            synchronized (LocalLeaserPathView.class) {
                                try {
                                    LocalLeaserPathView.this.mCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
                                    if (LayerDataUtils.getInstance().getShowTrajectory() && !LocalLeaserPathView.this.isScanMap()) {
                                        drawAllTrajectory(LocalLeaserPathView.this.mCanvas);
                                    }
                                    if (TrajectoryModel.getInstance().getIsInDrawing().booleanValue() && !LocalLeaserPathView.this.isScanMap()) {
                                        drawCurrentTrajectory(LocalLeaserPathView.this.mCanvas);
                                    }
                                    if (MySpUtils.getInstance().getInt(SpConstant.NAV_MODE, 0) == 1 && NavStateType.isNavigating(SelfChassisState.getInstance().getNavStatus()) && !LocalLeaserPathView.this.isScanMap()) {
                                        drawFollowPathTrajectory(LocalLeaserPathView.this.mCanvas);
                                    }
                                    if (LayerDataUtils.getInstance().getShowPointCloudRadar() || LocalLeaserPathView.this.isScanMap()) {
                                        drawLaser(LocalLeaserPathView.this.mCanvas);
                                    }
                                    if (LayerDataUtils.getInstance().getShowPointCloudUpCam() && !LocalLeaserPathView.this.isScanMap()) {
                                        drawUpCamLaser(LocalLeaserPathView.this.mCanvas);
                                    }
                                    if (LayerDataUtils.getInstance().getShowPointCloudDownCam() && !LocalLeaserPathView.this.isScanMap() && LocalLeaserPathView.this.mSoftType != 2) {
                                        drawDownCamLaser(LocalLeaserPathView.this.mCanvas);
                                    }
                                    int navStatus = SelfChassisState.getInstance().getNavStatus();
                                    if (LayerDataUtils.getInstance().getShowTravelPath() && !LocalLeaserPathView.this.isScanMap() && NavStateType.isNavigating(navStatus)) {
                                        drawPath(LocalLeaserPathView.this.mCanvas);
                                    }
                                    drawBrushPoint(LocalLeaserPathView.this.mCanvas);
                                    if (LocalLeaserPathView.this.mShowTarget && ((LocalLeaserPathView.this.mSoftType == 1 && App.getNavType() == 0) || LocalLeaserPathView.this.mSoftType == 3)) {
                                        drawDestination(LocalLeaserPathView.this.mCanvas);
                                    }
                                    if (LayerDataUtils.getInstance().getShowLocation() || LocalLeaserPathView.this.isScanMap()) {
                                        drawRobot(LocalLeaserPathView.this.mCanvas, LocalLeaserPathView.this.mRobotX, LocalLeaserPathView.this.mRobotY);
                                    }
                                    if (MultiRobotUtil.isTurnOnMultiRobot) {
                                        drawRobotList(LocalLeaserPathView.this.mCanvas);
                                    }
                                    if (LocalLeaserPathView.this.mShowAddRandomPoint && LocalLeaserPathView.this.mSoftType == 12) {
                                        drawAddRandomPoint(LocalLeaserPathView.this.mCanvas);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            LocalLeaserPathView localLeaserPathView2 = LocalLeaserPathView.this;
                            localLeaserPathView2.unlockCanvasAndPost(localLeaserPathView2.mCanvas);
                            SystemClock.sleep(80);
                        } else {
                            return;
                        }
                    }
                }

                private void drawBrushPoint(Canvas canvas) {
                    if (LocalLeaserPathView.this.mBrushPointList.size() != 0) {
                        LocalLeaserPathView.this.mDrawBrushPointPaint.setStrokeWidth((float) ((LocalLeaserPathView.this.mBrushSize * 2) + 1));
                        if (TextUtils.equals(LocalLeaserPathView.this.mLeftToolType, "layer_unknown_area")) {
                            LocalLeaserPathView.this.mDrawBrushPointPaint.setColor(-3355444);
                        } else if (TextUtils.equals(LocalLeaserPathView.this.mLeftToolType, "layer_obstacle_area")) {
                            LocalLeaserPathView.this.mDrawBrushPointPaint.setColor(-16777216);
                        } else if (TextUtils.equals(LocalLeaserPathView.this.mLeftToolType, "layer_empty_area")) {
                            LocalLeaserPathView.this.mDrawBrushPointPaint.setColor(-1);
                        }
                        PointF[] pointFArr = (PointF[]) LocalLeaserPathView.this.mBrushPointList.toArray(LocalLeaserPathView.this.mDrawBrushPointF);
                        float[] unused = LocalLeaserPathView.this.mDrawBrushPoints = new float[(pointFArr.length * 2)];
                        for (int i = 0; i < pointFArr.length; i++) {
                            int i2 = i * 2;
                            LocalLeaserPathView.this.mDrawBrushPoints[i2] = pointFArr[i].x;
                            LocalLeaserPathView.this.mDrawBrushPoints[i2 + 1] = pointFArr[i].y;
                        }
                        canvas.drawPoints(LocalLeaserPathView.this.mDrawBrushPoints, LocalLeaserPathView.this.mDrawBrushPointPaint);
                    }
                }

                private void drawDestination(Canvas canvas) {
                    if (canvas != null && LocalLeaserPathView.this.mTagePose != null) {
                        LocalLeaserPathView.this.mDestinationPaint.setAntiAlias(true);
                        LocalLeaserPathView.this.mDestinationPaint.setColor(ColorUtils.string2Int("#FF0560FD"));
                        canvas.drawPath(LocalLeaserPathView.this.mDestinationPath, LocalLeaserPathView.this.mDestinationPaint);
                        LocalLeaserPathView.this.mDestinationPaintCircle.setAntiAlias(true);
                        LocalLeaserPathView.this.mDestinationPaintCircle.setColor(ColorUtils.string2Int("#FF0560FD"));
                        canvas.drawCircle((float) LocalLeaserPathView.this.targetX, (float) LocalLeaserPathView.this.targetY, 6.0f, LocalLeaserPathView.this.mDestinationPaintCircle);
                        LocalLeaserPathView.this.mDestinationPaintCircleBorder.setAntiAlias(true);
                        LocalLeaserPathView.this.mDestinationPaintCircleBorder.setColor(ColorUtils.getColor(R.color.clr_FEFEFF));
                        LocalLeaserPathView.this.mDestinationPaintCircleBorder.setStyle(Paint.Style.STROKE);
                        LocalLeaserPathView.this.mDestinationPaintCircleBorder.setStrokeWidth(2.0f);
                        canvas.drawCircle((float) LocalLeaserPathView.this.targetX, (float) LocalLeaserPathView.this.targetY, 6.0f, LocalLeaserPathView.this.mDestinationPaintCircleBorder);
                    }
                }

                private void drawRobot(Canvas canvas, float f, float f2) {
                    Canvas canvas2 = canvas;
                    float f3 = f;
                    float f4 = f2;
                    if (canvas2 != null) {
                        LocalLeaserPathView.this.mRobotPosePath.reset();
                        double theta = (double) (LocalLeaserPathView.this.mRobotCurrentPosition.getTheta() * -1.0f);
                        float access$600 = LocalLeaserPathView.this.mRobotX + ((((float) Math.cos(theta)) * 12.0f) / 1.000001f);
                        float access$700 = LocalLeaserPathView.this.mRobotY - ((((float) Math.sin(theta)) * 12.0f) / 1.000001f);
                        LocalLeaserPathView.this.mRobotPosePath.setFillType(Path.FillType.WINDING);
                        LocalLeaserPathView.this.mRobotPosePath.moveTo(access$600, access$700);
                        double d = theta + 1.2217304763960306d;
                        LocalLeaserPathView.this.mRobotPosePath.lineTo(LocalLeaserPathView.this.mRobotX + ((((float) Math.cos(d)) * 12.0f) / 2.0f), LocalLeaserPathView.this.mRobotY - ((((float) Math.sin(d)) * 12.0f) / 2.0f));
                        double d2 = 3.141592653589793d + theta;
                        LocalLeaserPathView.this.mRobotPosePath.lineTo(LocalLeaserPathView.this.mRobotX + ((((float) Math.cos(d2)) * 12.0f) / 10.0f), LocalLeaserPathView.this.mRobotY - ((((float) Math.sin(d2)) * 12.0f) / 10.0f));
                        double d3 = theta - 1.2217304763960306d;
                        LocalLeaserPathView.this.mRobotPosePath.lineTo(LocalLeaserPathView.this.mRobotX + ((((float) Math.cos(d3)) * 12.0f) / 2.0f), LocalLeaserPathView.this.mRobotY - ((((float) Math.sin(d3)) * 12.0f) / 2.0f));
                        LocalLeaserPathView.this.mRobotPosePath.close();
                        LocalLeaserPathView.this.mPosePaint.setAntiAlias(true);
                        LocalLeaserPathView.this.mPosePaint.setColor(ColorUtils.string2Int("#FF0560FD"));
                        canvas2.drawPath(LocalLeaserPathView.this.mRobotPosePath, LocalLeaserPathView.this.mPosePaint);
                        LocalLeaserPathView.this.mPosePaintCircle.setAntiAlias(true);
                        LocalLeaserPathView.this.mPosePaintCircle.setColor(ColorUtils.string2Int("#FF0560FD"));
                        canvas2.drawCircle(f3, f4, 6.0f, LocalLeaserPathView.this.mPosePaintCircle);
                        LocalLeaserPathView.this.mPosePaintCircleBorder.setAntiAlias(true);
                        LocalLeaserPathView.this.mPosePaintCircleBorder.setColor(ColorUtils.getColor(R.color.clr_FEFEFF));
                        LocalLeaserPathView.this.mPosePaintCircleBorder.setStyle(Paint.Style.STROKE);
                        LocalLeaserPathView.this.mPosePaintCircleBorder.setStrokeWidth(2.0f);
                        canvas2.drawCircle(f3, f4, 6.0f, LocalLeaserPathView.this.mPosePaintCircleBorder);
                    }
                }

                private void drawAddRandomPoint(Canvas canvas) {
                    if (canvas != null && LocalLeaserPathView.this.mAddRandomPositionTargetPose != null) {
                        LocalLeaserPathView.this.mDestinationPaint.setAntiAlias(true);
                        LocalLeaserPathView.this.mDestinationPaint.setColor(ColorUtils.string2Int("#FF0560FD"));
                        canvas.drawPath(LocalLeaserPathView.this.mRobotAddRandomPointPath, LocalLeaserPathView.this.mDestinationPaint);
                        LocalLeaserPathView.this.mDestinationPaintCircle.setAntiAlias(true);
                        LocalLeaserPathView.this.mDestinationPaintCircle.setColor(ColorUtils.string2Int("#CDDFFF"));
                        canvas.drawCircle((float) LocalLeaserPathView.this.targetAddRandomPositionX, (float) LocalLeaserPathView.this.targetAddRandomPositionY, 6.0f, LocalLeaserPathView.this.mDestinationPaintCircle);
                        LocalLeaserPathView.this.mDestinationPaintCircleBorder.setAntiAlias(true);
                        LocalLeaserPathView.this.mDestinationPaintCircleBorder.setColor(ColorUtils.getColor(R.color.clr_FEFEFF));
                        LocalLeaserPathView.this.mDestinationPaintCircleBorder.setStyle(Paint.Style.STROKE);
                        LocalLeaserPathView.this.mDestinationPaintCircleBorder.setStrokeWidth(2.0f);
                        canvas.drawCircle((float) LocalLeaserPathView.this.targetAddRandomPositionX, (float) LocalLeaserPathView.this.targetAddRandomPositionY, 6.0f, LocalLeaserPathView.this.mDestinationPaintCircleBorder);
                        LocalLeaserPathView.this.mDestinationPaintCircleBorder.setPathEffect(new DashPathEffect(new float[]{1.0f, 1.0f}, 1.0f));
                        LocalLeaserPathView.this.mDestinationPaintCircleBorder.setColor(ColorUtils.getColor(R.color._ff0560fd));
                        LocalLeaserPathView.this.mDestinationPaintCircleBorder.setStrokeWidth(1.0f);
                        canvas.drawCircle((float) LocalLeaserPathView.this.targetAddRandomPositionX, (float) LocalLeaserPathView.this.targetAddRandomPositionY, 6.0f, LocalLeaserPathView.this.mDestinationPaintCircleBorder);
                    }
                }

                private void drawRobotList(Canvas canvas) {
                    if (canvas != null && LocalLeaserPathView.this.mRobotListPath.size() != 0 && LocalLeaserPathView.this.mRobotListPoint.size() != 0) {
                        for (Path path : LocalLeaserPathView.this.mRobotListPath) {
                            LocalLeaserPathView.this.mDestinationPaint.setAntiAlias(true);
                            LocalLeaserPathView.this.mDestinationPaint.setColor(RandomColorUtil.getRandomColor((String) LocalLeaserPathView.this.robotIdPathColorMap.get(path)));
                            canvas.drawPath(path, LocalLeaserPathView.this.mDestinationPaint);
                        }
                        for (PointF pointF : LocalLeaserPathView.this.mRobotListPoint) {
                            LocalLeaserPathView.this.mDestinationPaintCircle.setAntiAlias(true);
                            LocalLeaserPathView.this.mDestinationPaintCircle.setColor(RandomColorUtil.getRandomColor((String) LocalLeaserPathView.this.robotIdPointColorMap.get(pointF)));
                            canvas.drawCircle(pointF.x, pointF.y, 6.0f, LocalLeaserPathView.this.mDestinationPaintCircle);
                            LocalLeaserPathView.this.mDestinationPaintCircleBorder.setAntiAlias(true);
                            LocalLeaserPathView.this.mDestinationPaintCircleBorder.setColor(ColorUtils.getColor(R.color.clr_FEFEFF));
                            LocalLeaserPathView.this.mDestinationPaintCircleBorder.setStyle(Paint.Style.STROKE);
                            LocalLeaserPathView.this.mDestinationPaintCircleBorder.setStrokeWidth(2.0f);
                            canvas.drawCircle(pointF.x, pointF.y, 6.0f, LocalLeaserPathView.this.mDestinationPaintCircleBorder);
                        }
                    }
                }

                private void drawLaser(Canvas canvas) {
                    LocalLeaserPathView.this.mLaserPaint.setColor(SupportMenu.CATEGORY_MASK);
                    LocalLeaserPathView.this.mLaserPaint.setStrokeWidth(2.0f);
                    if (canvas != null && LocalLeaserPathView.this.mLaserPoint != null && LocalLeaserPathView.this.mLaserPoint.length != 0 && LocalLeaserPathView.this.mLaserPoint != null && LocalLeaserPathView.this.mLaserPoint.length != 0) {
                        canvas.drawPoints(LocalLeaserPathView.this.mLaserPoint, LocalLeaserPathView.this.mLaserPaint);
                    }
                }

                private void drawUpCamLaser(Canvas canvas) {
                    if (canvas != null) {
                        LocalLeaserPathView.this.mUpCamPointCloudPaint.setStrokeWidth(LocalLeaserPathView.this.mCamPointWidth);
                        float x = LocalLeaserPathView.this.mRobotCurrentPosition.getX();
                        float y = LocalLeaserPathView.this.mRobotCurrentPosition.getY();
                        float theta = LocalLeaserPathView.this.mRobotCurrentPosition.getTheta();
                        for (int i = 0; i < LocalLeaserPathView.this.mUpCamPointList.size(); i++) {
                            double d = (double) theta;
                            float x2 = (((float) ((((double) x) + (((double) LocalLeaserPathView.this.mUpCamPointList.get(i).getX()) * Math.cos(d))) - (((double) LocalLeaserPathView.this.mUpCamPointList.get(i).getY()) * Math.sin(d)))) - LocalLeaserPathView.this.mOriginX) / LocalLeaserPathView.this.mResolution;
                            float x3 = (((float) ((((double) y) + (((double) LocalLeaserPathView.this.mUpCamPointList.get(i).getX()) * Math.sin(d))) + (((double) LocalLeaserPathView.this.mUpCamPointList.get(i).getY()) * Math.cos(d)))) - LocalLeaserPathView.this.mOriginY) / LocalLeaserPathView.this.mResolution;
                            LocalLeaserPathView.this.mUpCamPointCloudPaint.setColor(LocalLeaserPathView.this.getCamClrByHeight(LocalLeaserPathView.this.mUpCamPointList.get(i).getTheta()));
                            canvas.drawPoint(x2, x3, LocalLeaserPathView.this.mUpCamPointCloudPaint);
                        }
                    }
                }

                private void drawDownCamLaser(Canvas canvas) {
                    if (canvas != null) {
                        LocalLeaserPathView.this.mDownCamPointCloudPaint.setStrokeWidth(LocalLeaserPathView.this.mCamPointWidth);
                        float x = LocalLeaserPathView.this.mRobotCurrentPosition.getX();
                        float y = LocalLeaserPathView.this.mRobotCurrentPosition.getY();
                        float theta = LocalLeaserPathView.this.mRobotCurrentPosition.getTheta();
                        for (int i = 0; i < LocalLeaserPathView.this.mDownCamPointList.size(); i++) {
                            double d = (double) theta;
                            float x2 = (((float) ((((double) x) + (((double) LocalLeaserPathView.this.mDownCamPointList.get(i).getX()) * Math.cos(d))) - (((double) LocalLeaserPathView.this.mDownCamPointList.get(i).getY()) * Math.sin(d)))) - LocalLeaserPathView.this.mOriginX) / LocalLeaserPathView.this.mResolution;
                            float x3 = (((float) ((((double) y) + (((double) LocalLeaserPathView.this.mDownCamPointList.get(i).getX()) * Math.sin(d))) + (((double) LocalLeaserPathView.this.mDownCamPointList.get(i).getY()) * Math.cos(d)))) - LocalLeaserPathView.this.mOriginY) / LocalLeaserPathView.this.mResolution;
                            LocalLeaserPathView.this.mDownCamPointCloudPaint.setColor(LocalLeaserPathView.this.getCamClrByHeight(LocalLeaserPathView.this.mDownCamPointList.get(i).getTheta()));
                            canvas.drawPoint(x2, x3, LocalLeaserPathView.this.mDownCamPointCloudPaint);
                        }
                    }
                }

                private void drawPath(Canvas canvas) {
                    if (canvas != null && LocalLeaserPathView.this.mPathPoint != null) {
                        LocalLeaserPathView.this.mPathPaint.setColor(-16776961);
                        LocalLeaserPathView.this.mPathPaint.setStrokeWidth(2.0f);
                        LocalLeaserPathView.this.mPathPaint.setAntiAlias(true);
                        canvas.drawPoints(LocalLeaserPathView.this.mPathPoint, LocalLeaserPathView.this.mPathPaint);
                    }
                }

                private void drawCurrentTrajectory(Canvas canvas) {
                    if (canvas != null && LocalLeaserPathView.this.mTrajectoryPointList != null && LocalLeaserPathView.this.mTrajectoryPointList.size() != 0) {
                        LocalLeaserPathView.this.bezierView.setPointList(LocalLeaserPathView.this.mTrajectoryPointList);
                        LocalLeaserPathView.this.bezierView.startDraw(canvas, -16776961);
                    }
                }

                private void drawFollowPathTrajectory(Canvas canvas) {
                    if (canvas != null && LocalLeaserPathView.this.mTrajectoryFollowPointList != null && LocalLeaserPathView.this.mTrajectoryFollowPointList.size() != 0) {
                        LocalLeaserPathView.this.bezierView.setPointList(LocalLeaserPathView.this.mTrajectoryFollowPointList);
                        LocalLeaserPathView.this.bezierView.startDraw(canvas, -16776961);
                    }
                }

                private void drawAllTrajectory(Canvas canvas) {
                    if (canvas != null && LocalLeaserPathView.this.mAllTrajectoryPointList != null && LocalLeaserPathView.this.mAllTrajectoryPointList.size() != 0) {
                        for (int i = 0; i < LocalLeaserPathView.this.mAllTrajectoryPointList.size(); i++) {
                            List list = (List) LocalLeaserPathView.this.mAllTrajectoryPointList.get(i);
                            if (LocalLeaserPathView.this.colorMap.get(list) == null) {
                                LocalLeaserPathView.this.colorMap.put(list, Integer.valueOf(Color.parseColor(RandomColorUtil.getRandColor())));
                            }
                            LocalLeaserPathView.this.bezierView.setPointList(list);
                            if (i == LocalLeaserPathView.this.highLightIndex && LocalLeaserPathView.this.mSoftType == 10) {
                                LocalLeaserPathView.this.bezierView.startDraw(canvas, -16776961);
                            } else {
                                LocalLeaserPathView.this.bezierView.startDraw(canvas, Color.parseColor("#96BBFF"));
                            }
                        }
                    }
                }
            };
        }
        if (!this.done) {
            ThreadPoolUtils.getInstance().execute(this.runnable);
        }
    }

    /* access modifiers changed from: private */
    public int getCamClrByHeight(float f) {
        double d = (double) f;
        if (d <= 0.1d) {
            return ColorUtils.getColor(R.color.clr_point_cloud_1);
        }
        if (d <= 0.2d) {
            return ColorUtils.getColor(R.color.clr_point_cloud_2);
        }
        if (d <= 0.3d) {
            return ColorUtils.getColor(R.color.clr_point_cloud_3);
        }
        if (d <= 0.4d) {
            return ColorUtils.getColor(R.color.clr_point_cloud_4);
        }
        if (d <= 0.5d) {
            return ColorUtils.getColor(R.color.clr_point_cloud_5);
        }
        if (d <= 0.6d) {
            return ColorUtils.getColor(R.color.clr_point_cloud_6);
        }
        if (d <= 0.7d) {
            return ColorUtils.getColor(R.color.clr_point_cloud_7);
        }
        if (d <= 0.8d) {
            return ColorUtils.getColor(R.color.clr_point_cloud_8);
        }
        if (d <= 0.9d) {
            return ColorUtils.getColor(R.color.clr_point_cloud_9);
        }
        if (d <= 1.0d) {
            return ColorUtils.getColor(R.color.clr_point_cloud_10);
        }
        if (d <= 1.1d) {
            return ColorUtils.getColor(R.color.clr_point_cloud_11);
        }
        if (d <= 1.2d) {
            return ColorUtils.getColor(R.color.clr_point_cloud_12);
        }
        if (d <= 1.3d) {
            return ColorUtils.getColor(R.color.clr_point_cloud_13);
        }
        if (d <= 1.4d) {
            return ColorUtils.getColor(R.color.clr_point_cloud_14);
        }
        if (d <= 1.5d) {
            return ColorUtils.getColor(R.color.clr_point_cloud_15);
        }
        return ColorUtils.getColor(R.color.clr_point_cloud_15);
    }

    public void setTagePoint(PointBean pointBean) {
        this.mTagePose = pointBean;
        this.mDestinationPath.reset();
        if (pointBean != null) {
            this.targetX = (int) pointBean.getX();
            this.targetY = (int) pointBean.getY();
            double theta = (double) (pointBean.getTheta() + 3.1415927f);
            float cos = ((float) this.targetX) + ((((float) Math.cos(theta)) * 12.0f) / 1.000001f);
            float sin = ((float) this.targetY) - ((((float) Math.sin(theta)) * 12.0f) / 1.000001f);
            this.mDestinationPath.setFillType(Path.FillType.WINDING);
            this.mDestinationPath.moveTo(cos, sin);
            double d = theta - 1.2217304763960306d;
            this.mDestinationPath.lineTo(((float) this.targetX) + ((((float) Math.cos(d)) * 12.0f) / 2.0f), ((float) this.targetY) - ((((float) Math.sin(d)) * 12.0f) / 2.0f));
            double d2 = 3.141592653589793d + theta;
            this.mDestinationPath.lineTo(((float) this.targetX) + ((((float) Math.cos(d2)) * 12.0f) / 10.0f), ((float) this.targetY) - ((((float) Math.sin(d2)) * 12.0f) / 10.0f));
            double d3 = theta + 1.2217304763960306d;
            this.mDestinationPath.lineTo(((float) this.targetX) + ((((float) Math.cos(d3)) * 12.0f) / 2.0f), ((float) this.targetY) - ((((float) Math.sin(d3)) * 12.0f) / 2.0f));
            this.mDestinationPath.close();
        }
    }

    public void setRobotAddRandomPointPosition(PointBean pointBean) {
        this.mAddRandomPositionTargetPose = pointBean;
        this.mRobotAddRandomPointPath.reset();
        if (pointBean != null) {
            this.targetAddRandomPositionX = (int) pointBean.getX();
            this.targetAddRandomPositionY = (int) pointBean.getY();
            double theta = (double) (pointBean.getTheta() + 3.1415927f);
            float cos = ((float) this.targetAddRandomPositionX) + ((((float) Math.cos(theta)) * 12.0f) / 1.000001f);
            float sin = ((float) this.targetAddRandomPositionY) - ((((float) Math.sin(theta)) * 12.0f) / 1.000001f);
            this.mRobotAddRandomPointPath.setFillType(Path.FillType.WINDING);
            this.mRobotAddRandomPointPath.moveTo(cos, sin);
            double d = theta - 1.2217304763960306d;
            this.mRobotAddRandomPointPath.lineTo(((float) this.targetAddRandomPositionX) + ((((float) Math.cos(d)) * 12.0f) / 2.0f), ((float) this.targetAddRandomPositionY) - ((((float) Math.sin(d)) * 12.0f) / 2.0f));
            double d2 = 3.141592653589793d + theta;
            this.mRobotAddRandomPointPath.lineTo(((float) this.targetAddRandomPositionX) + ((((float) Math.cos(d2)) * 12.0f) / 10.0f), ((float) this.targetAddRandomPositionY) - ((((float) Math.sin(d2)) * 12.0f) / 10.0f));
            double d3 = theta + 1.2217304763960306d;
            this.mRobotAddRandomPointPath.lineTo(((float) this.targetAddRandomPositionX) + ((((float) Math.cos(d3)) * 12.0f) / 2.0f), ((float) this.targetAddRandomPositionY) - ((((float) Math.sin(d3)) * 12.0f) / 2.0f));
            this.mRobotAddRandomPointPath.close();
        }
    }

    public void setRobotList(RobotListRespBean robotListRespBean) {
        this.mRobotListPath.clear();
        this.mRobotListPoint.clear();
        this.robotIdPathColorMap.clear();
        if (robotListRespBean != null) {
            for (RobotListRespBean.MsgBean.ListBean next : robotListRespBean.getMsg().getList()) {
                PointF pointF = new PointF();
                Path path = new Path();
                pointF.x = (next.getPose().getX() - this.mOriginX) / this.mResolution;
                pointF.y = (next.getPose().getY() - this.mOriginY) / this.mResolution;
                double theta = (double) (next.getPose().getTheta() * -1.0f);
                path.setFillType(Path.FillType.WINDING);
                path.moveTo(pointF.x + ((((float) Math.cos(theta)) * 12.0f) / 1.000001f), pointF.y - ((((float) Math.sin(theta)) * 12.0f) / 1.000001f));
                double d = theta - 1.2217304763960306d;
                path.lineTo(pointF.x + ((((float) Math.cos(d)) * 12.0f) / 2.0f), pointF.y - ((((float) Math.sin(d)) * 12.0f) / 2.0f));
                double d2 = 3.141592653589793d + theta;
                path.lineTo(pointF.x + ((((float) Math.cos(d2)) * 12.0f) / 10.0f), pointF.y - ((((float) Math.sin(d2)) * 12.0f) / 10.0f));
                double d3 = theta + 1.2217304763960306d;
                path.lineTo(pointF.x + ((((float) Math.cos(d3)) * 12.0f) / 2.0f), pointF.y - ((((float) Math.sin(d3)) * 12.0f) / 2.0f));
                path.close();
                this.mRobotListPoint.add(pointF);
                this.mRobotListPath.add(path);
                this.robotIdPathColorMap.put(path, next.getRobot_id());
                this.robotIdPointColorMap.put(pointF, next.getRobot_id());
            }
        }
    }

    public void setPose(PointBean pointBean) {
        if (pointBean == null) {
            pointBean = new PointBean();
        }
        float x = pointBean.getX();
        float y = pointBean.getY();
        this.mRobotCurrentPosition = pointBean;
        this.mRobotX = (x - this.mOriginX) / this.mResolution;
        this.mRobotY = (y - this.mOriginY) / this.mResolution;
    }

    public void setLaser(ArrayList<PointBean> arrayList) {
        if (SelfChassisState.getInstance().isLaserDataState()) {
            float x = this.mRobotCurrentPosition.getX();
            float y = this.mRobotCurrentPosition.getY();
            float theta = this.mRobotCurrentPosition.getTheta();
            this.mLaserPoint = null;
            if (arrayList.size() > 0) {
                this.mLaserPoint = new float[(arrayList.size() * 2)];
            }
            for (int i = 0; i < arrayList.size(); i++) {
                double d = (double) theta;
                float x2 = (((float) ((((double) x) + (((double) arrayList.get(i).getX()) * Math.cos(d))) - (((double) arrayList.get(i).getY()) * Math.sin(d)))) - this.mOriginX) / this.mResolution;
                float x3 = (((float) ((((double) y) + (((double) arrayList.get(i).getX()) * Math.sin(d))) + (((double) arrayList.get(i).getY()) * Math.cos(d)))) - this.mOriginY) / this.mResolution;
                float[] fArr = this.mLaserPoint;
                int i2 = i * 2;
                fArr[i2] = x2;
                fArr[i2 + 1] = x3;
            }
        }
    }

    public void setPath(ArrayList<PointBean> arrayList) {
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

    public void setTrajectoryDrawingPath(ArrayList<PointBean> arrayList) {
        this.mTrajectoryPointList.clear();
        if (arrayList != null) {
            for (int i = 0; i < arrayList.size(); i++) {
                float x = (arrayList.get(i).getX() - this.mOriginX) / this.mResolution;
                float y = (arrayList.get(i).getY() - this.mOriginY) / this.mResolution;
                if (!Float.isNaN(x) && !Float.isNaN(y)) {
                    this.mTrajectoryPointList.add(new PointF(x, y));
                }
            }
        }
    }

    public void setTrajectoryDrawingFollowPath(ArrayList<PointBean> arrayList) {
        this.mTrajectoryFollowPointList.clear();
        if (arrayList != null) {
            for (int i = 0; i < arrayList.size(); i++) {
                float x = (arrayList.get(i).getX() - this.mOriginX) / this.mResolution;
                float y = (arrayList.get(i).getY() - this.mOriginY) / this.mResolution;
                if (!Float.isNaN(x) && !Float.isNaN(y)) {
                    this.mTrajectoryFollowPointList.add(new PointF(x, y));
                }
            }
        }
    }

    public void setAllTrajectoryDrawingPath(List<List<PointBean>> list) {
        this.mAllTrajectoryPointList.clear();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                ArrayList arrayList = new ArrayList();
                for (int i2 = 0; i2 < list.get(i).size(); i2++) {
                    float x = (((PointBean) list.get(i).get(i2)).getX() - this.mOriginX) / this.mResolution;
                    float y = (((PointBean) list.get(i).get(i2)).getY() - this.mOriginY) / this.mResolution;
                    if (!Float.isNaN(x) && !Float.isNaN(y)) {
                        arrayList.add(new PointF(x, y));
                    }
                }
                this.mAllTrajectoryPointList.add(arrayList);
            }
        }
    }

    public void setHighLightIndex(int i) {
        this.highLightIndex = i;
    }

    public void setMapInfo(MapInfoBean mapInfoBean) {
        this.mOriginX = mapInfoBean.getX();
        this.mOriginY = mapInfoBean.getY();
        this.mResolution = mapInfoBean.getR();
    }

    public void setSoftType(int i) {
        String str = TAG;
        MyLogUtils.Logd(str, "setSoftType :" + i);
        this.mSoftType = i;
    }

    public void setShowTarget(boolean z) {
        this.mShowTarget = z;
    }

    public void setShowAddRandomPoint(boolean z) {
        this.mShowAddRandomPoint = z;
    }

    public void calibrationPoint(float f, float f2, float f3) {
        PointF worldCoordinates = getWorldCoordinates(f, f2);
        SelfChassis.getInstance().setPoseMsg(worldCoordinates.x, worldCoordinates.y, (float) (((double) (-f3)) + 3.141592653589793d));
    }

    public void moveToPoint(float f, float f2, float f3) {
        PointF worldCoordinates = getWorldCoordinates(f, f2);
        SelfChassis.getInstance().sendMoveByLocation(worldCoordinates.x, worldCoordinates.y, (float) (((double) (-f3)) + 3.141592653589793d));
    }

    public PointF getWorldCoordinates(float f, float f2) {
        this.mWorldCoordinatesPoint.set((this.mResolution * f) + this.mOriginX, (this.mResolution * f2) + this.mOriginY);
        return this.mWorldCoordinatesPoint;
    }

    public PointF fromWorldCoordinates(float f, float f2) {
        return new PointF((f - this.mOriginX) / this.mResolution, (f2 - this.mOriginY) / this.mResolution);
    }

    public void clearBrushZone() {
        this.mPointInfo.clear();
        this.mBrushPointList.clear();
    }

    public void sendBrushZone() {
        SelfChassis.getInstance().setLayeredMapManagerPencilOp(this.mPointInfo);
        clearBrushZone();
    }

    public void brushZone(PointF pointF, String str, int i) {
        this.mLeftToolType = str;
        this.mBrushPointList.add(pointF);
        float f = (this.mResolution * pointF.x) + this.mOriginX;
        float f2 = (this.mResolution * pointF.y) + this.mOriginY;
        SetLayeredMapManagerPencilOpPublishBean.MsgBean.PointInfoBean pointInfoBean = new SetLayeredMapManagerPencilOpPublishBean.MsgBean.PointInfoBean();
        this.mBrushZonePointInfo = pointInfoBean;
        pointInfoBean.setPx(f);
        this.mBrushZonePointInfo.setPy(f2);
        if (TextUtils.equals(str, "layer_empty_area")) {
            this.mBrushZonePointInfo.setOp_color(2);
        } else if (TextUtils.equals(str, "layer_obstacle_area")) {
            this.mBrushZonePointInfo.setOp_color(3);
        } else if (TextUtils.equals(str, "layer_unknown_area")) {
            this.mBrushZonePointInfo.setOp_color(1);
        }
        if (i == 1) {
            this.mBrushSize = 1;
        } else if (i == 2) {
            this.mBrushSize = 3;
        } else if (i == 3) {
            this.mBrushSize = 5;
        }
        this.mBrushZonePointInfo.setOp_size(this.mBrushSize);
        String str2 = TAG;
        MyLogUtils.Logd(str2, "pointF:" + pointF.y + "  tempX:" + f + "  tempY:" + f2 + " leftToolType:" + str + " brushType:" + i);
        this.mPointInfo.add(this.mBrushZonePointInfo);
    }

    public void setMapMatrix(Matrix matrix) {
        mMapMatrix = matrix;
    }

    public void setUpCamData(ArrayList<PointBean> arrayList) {
        if (SelfChassisState.getInstance().isUpCamDataState()) {
            if (arrayList != null) {
                this.mUpCamPointList = arrayList;
            } else {
                this.mUpCamPointList.clear();
            }
        }
    }

    public void setDownCamData(ArrayList<PointBean> arrayList) {
        if (SelfChassisState.getInstance().isDownCamDataState()) {
            if (arrayList != null) {
                this.mDownCamPointList = arrayList;
            } else {
                this.mDownCamPointList.clear();
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean isScanMap() {
        return this.mSoftType == 2;
    }
}
