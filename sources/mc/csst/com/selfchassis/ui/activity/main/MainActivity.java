package mc.csst.com.selfchassis.ui.activity.main;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.blankj.utilcode.util.AdaptScreenUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ClickUtils;
import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.ciot.base.livedatabus.LiveDatabus;
import com.ciot.base.storage.MySpUtils;
import com.ciot.base.util.ContextUtil;
import com.ciot.base.util.DisposeUtil;
import com.ciot.base.util.GsonUtils;
import com.ciot.base.util.MultiLanguageUtil;
import com.ciot.base.util.MyLogUtils;
import com.ciot.base.util.ToastUtil;
import com.ciot.sentrymove.R;
import com.google.gson.reflect.TypeToken;
import com.yhao.floatwindow.FloatWindow;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import mc.csst.com.selfchassis.App;
import mc.csst.com.selfchassis.adapter.AreaAdapter;
import mc.csst.com.selfchassis.adapter.BuildAdapter;
import mc.csst.com.selfchassis.adapter.FloorAdapter;
import mc.csst.com.selfchassis.adapter.IntellectPointAdapter;
import mc.csst.com.selfchassis.adapter.LeftToolBarAdapter;
import mc.csst.com.selfchassis.adapter.MapManagerAdapter;
import mc.csst.com.selfchassis.adapter.MultiAdapter;
import mc.csst.com.selfchassis.adapter.PatrolRouteAdapter;
import mc.csst.com.selfchassis.adapter.PointAdapter;
import mc.csst.com.selfchassis.adapter.RightToolBarAdapter;
import mc.csst.com.selfchassis.adapter.RobotListAdapter;
import mc.csst.com.selfchassis.adapter.TopMenuAdapter;
import mc.csst.com.selfchassis.adapter.TrajectoryPointAdapter;
import mc.csst.com.selfchassis.adapter.VisualLabelAdapter;
import mc.csst.com.selfchassis.databinding.ActivityMainBinding;
import mc.csst.com.selfchassis.model.trajectory.MultiScheduleModel;
import mc.csst.com.selfchassis.model.trajectory.TrajectoryModel;
import mc.csst.com.selfchassis.receiver.WifiSwitchBroadcastReceiver;
import mc.csst.com.selfchassis.ui.activity.base.BaseActivity;
import mc.csst.com.selfchassis.ui.activity.main.MainActivity;
import mc.csst.com.selfchassis.ui.activity.main.MainContract;
import mc.csst.com.selfchassis.ui.activity.set.SetActivity;
import mc.csst.com.selfchassis.ui.dialog.AddMarkDialog;
import mc.csst.com.selfchassis.ui.dialog.ChooseMarkerDialog;
import mc.csst.com.selfchassis.ui.dialog.ChoosePointWaitMarkerDialog;
import mc.csst.com.selfchassis.ui.dialog.ConfirmDialog;
import mc.csst.com.selfchassis.ui.dialog.ConnectedDialog;
import mc.csst.com.selfchassis.ui.dialog.ContinueScanConfirmDialog;
import mc.csst.com.selfchassis.ui.dialog.DialogHelper;
import mc.csst.com.selfchassis.ui.dialog.LoadingDialog;
import mc.csst.com.selfchassis.ui.dialog.NormalDialog;
import mc.csst.com.selfchassis.ui.dialog.NotConnectedWifiDialog;
import mc.csst.com.selfchassis.ui.dialog.SwitchPointDialog;
import mc.csst.com.selfchassis.ui.widget.AddMarkModeView;
import mc.csst.com.selfchassis.ui.widget.DrawingSwitchView;
import mc.csst.com.selfchassis.ui.widget.FixedPopupWindow;
import mc.csst.com.selfchassis.ui.widget.LabelCameraView;
import mc.csst.com.selfchassis.ui.widget.LineTrackingListView;
import mc.csst.com.selfchassis.ui.widget.LineTrackingSettingView;
import mc.csst.com.selfchassis.ui.widget.StartCollectView;
import mc.csst.com.selfchassis.ui.widget.StartDrawingView;
import mc.csst.com.selfchassis.utils.CenterLayoutManager;
import mc.csst.com.selfchassis.utils.LayerDataUtils;
import mc.csst.com.selfchassis.utils.MultiRobotUtil;
import mc.csst.com.selfchassis.utils.MyToastUtils;
import mc.csst.com.selfchassis.utils.SoftTypeInfoManager;
import mc.csst.com.selfchassis.utils.SpConstant;
import mc.csst.com.selfchassis.utils.VirtualWallInfoManager;
import mc.csst.com.selfchassis.utils.bean.ConfirmDialogLiveDataEvent;
import mc.csst.com.selfchassis.utils.bean.LeftToolbarBean;
import mc.csst.com.selfchassis.utils.bean.ShowSelfChassisBean;
import mc.csst.com.selfchassis.utils.bean.ToolbarBean;
import mc.csst.com.selfchassis.utils.bean.TopMenuBean;
import mc.csst.com.selfchassis.utils.constant.DTLiveDataConstant;
import mc.csst.com.selfchassis.utils.constant.DeploymentToolSpConstant;
import mc.csst.com.selfchassis.utils.view.DirectionFourKey;
import mc.csst.com.selfchassis.utils.view.LayerSelectView;
import mc.csst.com.selfchassis.utils.view.RockerView;
import mc.csst.com.selfchassis.utils.view.map.MapRlView;
import mc.csst.com.selfchassis.utils.view.map.area.MapAreaView;
import mc.csst.com.selfchassislibrary.bean.MapInfoBean;
import mc.csst.com.selfchassislibrary.bean.PointBean;
import mc.csst.com.selfchassislibrary.bean.msg.ApriltagsBufferBean;
import mc.csst.com.selfchassislibrary.bean.msg.AreaItemBean;
import mc.csst.com.selfchassislibrary.bean.msg.CrossFloorNaviReqBean;
import mc.csst.com.selfchassislibrary.bean.msg.GlobalLocateReqBean;
import mc.csst.com.selfchassislibrary.bean.msg.LayeredMapCmdResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.MarkerOperationGetMarkersResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.MultiRobotStationRespBean;
import mc.csst.com.selfchassislibrary.bean.msg.PathGetResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.RobotListRespBean;
import mc.csst.com.selfchassislibrary.bean.msg.SelfDiagnosisResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.SendDeletePoiRequestBean;
import mc.csst.com.selfchassislibrary.bean.msg.SendPathRecordRequestBean;
import mc.csst.com.selfchassislibrary.bean.msg.VirtualWallOperationGetWallsResponseBean;
import mc.csst.com.selfchassislibrary.chassis.SelfChassis;
import mc.csst.com.selfchassislibrary.chassis.SelfChassisMsgCallBack;
import mc.csst.com.selfchassislibrary.chassis.SelfChassisState;
import mc.csst.com.selfchassislibrary.content.TypeContent;
import mc.csst.com.selfchassislibrary.utils.SelfChassisListenerUtils;
import mc.csst.com.selfchassislibrary.utils.eventbus.SelfChassisEventMsg;

public class MainActivity extends BaseActivity<MainPresenter> implements WifiSwitchBroadcastReceiver.WifiSwitchInterface, MainContract.View {
    /* access modifiers changed from: private */
    public BuildAdapter buildAdapter;
    /* access modifiers changed from: private */
    public List<LayeredMapCmdResponseBean.ValuesBean.ListInfoBean> buildList;
    private SelfChassisListenerUtils.OnBaseSelfChassisListener configMqttListener = null;
    private FloorAdapter floorAdapter;
    /* access modifiers changed from: private */
    public IntellectPointAdapter intellectPointAdapter;
    private LabelCameraView labelCameraView;
    /* access modifiers changed from: private */
    public LineTrackingSettingView lineTrackingSettingView;
    /* access modifiers changed from: private */
    public AreaAdapter mAreaAdapter;
    /* access modifiers changed from: private */
    public List<AreaItemBean> mAreaItemBeanList = new ArrayList();
    private ConfirmDialog mConfirmDialog = null;
    private WeakReference<ConnectedDialog> mConnectedDialog = null;
    private ContinueScanConfirmDialog mContinueScanConfirmDialog;
    private PatrolRouteAdapter mIntellectRouteAdapter;
    private final List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean> mIntellectRoutes = new ArrayList();
    /* access modifiers changed from: private */
    public boolean mIsAddingArea = false;
    private LeftToolBarAdapter mLeftToolBarAdapter;
    private String mLeftToolType = "";
    /* access modifiers changed from: private */
    public String mLeftToolTypeDesc = "";
    List<LeftToolbarBean> mLeftToolbarBeans = new ArrayList();
    private LoadingDialog mLoadingDialog = null;
    private NotConnectedWifiDialog mNotConnectedWifiDialog = null;
    private SelfChassisListenerUtils.OnBaseSelfChassisListener mNotificationsListener;
    /* access modifiers changed from: private */
    public PathGetResponseBean mPathGetResponseBean;
    private CenterLayoutManager mPatrolCenterLm;
    private PatrolRouteAdapter mPatrolRouteAdapter;
    private final List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean> mPatrolRoutes = new ArrayList();
    private RightToolBarAdapter mRightToolBarAdapter;
    List<ToolbarBean> mRightToolbarBeans = new ArrayList();
    private List<ApriltagsBufferBean.MsgBean.WaypointsBean> mTagWaypoints;
    private TopMenuAdapter mTopMenuAdapter;
    private VisualLabelAdapter mVisualLabelAdapter;
    /* access modifiers changed from: private */
    public List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean> mWaypoints;
    ActivityMainBinding mainBinding;
    /* access modifiers changed from: private */
    public MapManagerAdapter mapManagerAdapter;
    /* access modifiers changed from: private */
    public MultiAdapter multiAdapter;
    private final MapAreaView.OnDrawAreaListener myDrawAreaListener = new MapAreaView.OnDrawAreaListener() {
        public void onAddComplete(AreaItemBean areaItemBean) {
            ((MainPresenter) MainActivity.this.mPresenter).appendArea(MainActivity.this.getSupportFragmentManager(), MainActivity.this.mAreaItemBeanList, areaItemBean, MainActivity.this.mLeftToolTypeDesc);
        }

        public void onUnFinishAdd(boolean z) {
            ThreadUtils.runOnUiThread(new Runnable(z) {
                public final /* synthetic */ boolean f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    MainActivity.AnonymousClass26.this.lambda$onUnFinishAdd$0$MainActivity$26(this.f$1);
                }
            });
        }

        public /* synthetic */ void lambda$onUnFinishAdd$0$MainActivity$26(boolean z) {
            boolean unused = MainActivity.this.mIsAddingArea = z;
            MainActivity.this.mainBinding.mainLeftCleanDrawAreaIv.setVisibility(z ? 0 : 8);
            MainActivity.this.mAreaAdapter.setSelectedIndex(-1);
            if (z) {
                MainActivity mainActivity = MainActivity.this;
                mainActivity.showTipsBlack(String.format(mainActivity.getString(R.string.tips_edit_map_area_draw), new Object[]{MainActivity.this.mLeftToolTypeDesc}));
                return;
            }
            MainActivity mainActivity2 = MainActivity.this;
            mainActivity2.showTipsBlack(String.format(mainActivity2.getString(R.string.tips_edit_map_draw), new Object[]{MainActivity.this.mLeftToolTypeDesc}));
        }

        public void onChangePoints(AreaItemBean areaItemBean) {
            ((MainPresenter) MainActivity.this.mPresenter).appendArea(areaItemBean);
        }
    };
    private final RockerView.OnAngleChangeListener onAngleChangeListener = new RockerView.OnAngleChangeListener() {
        public void onStart() {
        }

        public void angle(double d, float f) {
            if ((d >= 0.0d && 10.0d >= d) || (360.0d >= d && d >= 350.0d)) {
                d = 360.0d;
            } else if (190.0d >= d && d >= 170.0d) {
                d = 180.0d;
            } else if (280.0d >= d && d >= 260.0d) {
                d = 270.0d;
            } else if (100.0d >= d && d >= 80.0d) {
                d = 90.0d;
            }
            double radians = Math.toRadians(d - 180.0d);
            double d2 = (double) f;
            float sin = (float) (Math.sin(radians) * d2);
            float cos = (float) (Math.cos(radians) * d2);
            if (sin < 0.0f) {
                cos = -cos;
            }
            ((MainPresenter) MainActivity.this.mPresenter).controlDirectionStart(cos, App.getInstance().getChassisSpeed() * sin);
            MyLogUtils.Logd(MainActivity.this.TAG, "wz * 0.8f：" + (cos * 0.8f) + "；vx * 0.3f：" + (sin * 0.3f));
        }

        public void onFinish() {
            ((MainPresenter) MainActivity.this.mPresenter).controlDirectionStop();
        }
    };
    private final DirectionFourKey.OnDirectionListener onDirectionListener = new DirectionFourKey.OnDirectionListener() {
        public void upUp() {
            ((MainPresenter) MainActivity.this.mPresenter).controlDirectionStop();
        }

        public void downUp() {
            ((MainPresenter) MainActivity.this.mPresenter).controlDirectionStop();
        }

        public void leftUp() {
            ((MainPresenter) MainActivity.this.mPresenter).controlDirectionStop();
        }

        public void rightUp() {
            ((MainPresenter) MainActivity.this.mPresenter).controlDirectionStop();
        }

        public void upDown() {
            ((MainPresenter) MainActivity.this.mPresenter).controlDirectionStart(0.0f, App.getInstance().getChassisSpeed());
        }

        public void downDown() {
            ((MainPresenter) MainActivity.this.mPresenter).controlDirectionStart(0.0f, App.getInstance().getChassisSpeed() * -1.0f);
        }

        public void leftDown() {
            ((MainPresenter) MainActivity.this.mPresenter).controlDirectionStart(0.8f, 0.0f);
        }

        public void rightDown() {
            ((MainPresenter) MainActivity.this.mPresenter).controlDirectionStart(-0.8f, 0.0f);
        }
    };
    /* access modifiers changed from: private */
    public PointAdapter pointAdapter;
    /* access modifiers changed from: private */
    public RobotListAdapter robotListAdapter = null;
    private TrajectoryPointAdapter trajectoryPointAdapter;
    private WifiSwitchBroadcastReceiver wifiSwitchBroadcastReceiver;

    static /* synthetic */ boolean lambda$changeBottomLock$42(boolean z, View view, MotionEvent motionEvent) {
        return z;
    }

    static /* synthetic */ boolean lambda$changeBottomLock$43(boolean z, View view, MotionEvent motionEvent) {
        return z;
    }

    /* access modifiers changed from: protected */
    public View getView() {
        ActivityMainBinding activityMainBinding = (ActivityMainBinding) DataBindingUtil.setContentView(this, R.layout.activity_main);
        this.mainBinding = activityMainBinding;
        return activityMainBinding.getRoot();
    }

    /* access modifiers changed from: protected */
    public void initView() {
        MyLogUtils.Logd("initView");
        this.mainBinding.addRandomMarkView.setFragmentManager(getSupportFragmentManager());
        initRecycleView();
        initTrajectoryDrawingView();
        initRecordBagView();
    }

    private void initRecordBagView() {
        SelfChassisListenerUtils.RobotList.addRobotListListener(new SelfChassisListenerUtils.OnBaseSelfChassisListener() {
            public void onSelfChassisMsg(SelfChassisEventMsg selfChassisEventMsg) {
                ThreadUtils.runOnUiThread(new Runnable((RobotListRespBean) selfChassisEventMsg.getData()) {
                    public final /* synthetic */ RobotListRespBean f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        MainActivity.AnonymousClass1.this.lambda$onSelfChassisMsg$0$MainActivity$1(this.f$1);
                    }
                });
            }

            public /* synthetic */ void lambda$onSelfChassisMsg$0$MainActivity$1(RobotListRespBean robotListRespBean) {
                MainActivity.this.mainBinding.mainMapControlMrv.mMapInfoLlpv.setRobotList(robotListRespBean);
                MainActivity.this.robotListAdapter.setRobotListRespBean(robotListRespBean);
            }
        });
        this.mainBinding.includeCollecting.startCollectingView.setStartCollectingViewListener(new StartCollectView.StartDrawingViewListener() {
            public void onChecked(boolean z) {
                if (z) {
                    SelfChassis.getInstance().startBagRecord(TypeContent.BagRecordType.localization);
                    MainActivity.this.mainBinding.includeCollecting.startCollectingView.startDrawing();
                    MainActivity mainActivity = MainActivity.this;
                    mainActivity.showTipsBlack(mainActivity.getString(R.string.tips_is_collecting));
                    return;
                }
                SelfChassis.getInstance().stopBagRecord();
                MainActivity.this.mainBinding.includeCollecting.startCollectingView.endDrawing();
                MainActivity.this.hiddenTips();
            }
        });
    }

    /* access modifiers changed from: private */
    public void isPassMarker() {
        if (SoftTypeInfoManager.getInstance().getSoftType() == 10) {
            String currentHasPoint = TrajectoryModel.getInstance().getCurrentHasPoint(this.mWaypoints);
            if (!TextUtils.isEmpty(currentHasPoint)) {
                showTipsBlack(String.format(getString(R.string.tips_passing_point_tip), new Object[]{currentHasPoint}));
            } else if (TrajectoryModel.getInstance().getIsInDrawing().booleanValue()) {
                showTipsBlack(getString(R.string.tips_start_trajectory_draw));
            } else {
                hiddenTips();
            }
        }
    }

    private void initTrajectoryDrawingView() {
        LineTrackingSettingView lineTrackingSettingView2 = (LineTrackingSettingView) findViewById(R.id.line_tracking_setting_view);
        this.lineTrackingSettingView = lineTrackingSettingView2;
        lineTrackingSettingView2.setFragmentManager(getSupportFragmentManager());
        this.lineTrackingSettingView.setLineTrackingSettingViewListener(new LineTrackingSettingView.LineTrackingSettingViewListener() {
            public void onChecked(boolean z) {
                if (z) {
                    FloatWindow.get().show();
                } else {
                    FloatWindow.get().hide();
                }
            }
        });
        this.mainBinding.includeTracking.lineTrackingListView.setOnItemClickListener(new LineTrackingListView.OnItemClickListener() {
            public void onDeleteClick(String str) {
                String string = ContextUtil.getContext().getString(R.string.txt_whether_trajectory_segment_title);
                String string2 = ContextUtil.getContext().getString(R.string.txt_whether_trajectory_segment_message);
                String string3 = ContextUtil.getContext().getString(R.string.txt_whether_trajectory_segment_ok);
                String string4 = ContextUtil.getContext().getString(R.string.txt_whether_trajectory_segment_cancel);
                NormalDialog createDialog = NormalDialog.createDialog(MainActivity.this);
                createDialog.setTitle(string);
                createDialog.setMessage(string2);
                createDialog.setBtCancel(string4);
                createDialog.setBtOk(string3);
                createDialog.setShowTopExit(true);
                createDialog.setMessageTxtColor(R.color.black);
                createDialog.setBtnCancleStyle();
                createDialog.setmOnDialogListener(new NormalDialog.OnDialogListener(str) {
                    public final /* synthetic */ String f$0;

                    {
                        this.f$0 = r1;
                    }

                    public final void isPositiveBtClick(boolean z) {
                        MainActivity.AnonymousClass4.lambda$onDeleteClick$0(this.f$0, z);
                    }
                });
                createDialog.show();
            }

            static /* synthetic */ void lambda$onDeleteClick$0(String str, boolean z) {
                if (z) {
                    SendDeletePoiRequestBean sendDeletePoiRequestBean = new SendDeletePoiRequestBean();
                    SendDeletePoiRequestBean.ArgsBean argsBean = new SendDeletePoiRequestBean.ArgsBean();
                    argsBean.setPoi(str);
                    sendDeletePoiRequestBean.setArgs(argsBean);
                    SelfChassis.getInstance().sendDeletePoiOpParams(sendDeletePoiRequestBean);
                }
            }

            public void onSelectedIndex(int i) {
                MainActivity.this.mainBinding.mainMapControlMrv.setHighLightIndex(i);
            }
        });
        this.mainBinding.includeTracking.startDrawingView.setStartDrawingViewListener(new StartDrawingView.StartDrawingViewListener() {
            static /* synthetic */ boolean lambda$onCancel$5(View view) {
                return false;
            }

            public void onChecked(boolean z) {
                if (z) {
                    String currentHasPoint = TrajectoryModel.getInstance().getCurrentHasPoint(MainActivity.this.mWaypoints);
                    if (!TextUtils.isEmpty(currentHasPoint)) {
                        MainActivity.this.showConfirmDialog(ContextUtil.getContext().getString(R.string.txt_start_drawing_title), String.format(ContextUtil.getContext().getString(R.string.txt_start_drawing_tips), new Object[]{currentHasPoint}), ContextUtil.getContext().getString(R.string.txt_start_drawing_create_new_point), ContextUtil.getContext().getString(R.string.txt_start_drawing_current_point), true, new ConfirmDialog.OnDialogButtonClickListener() {
                            public final boolean onClick(View view) {
                                return MainActivity.AnonymousClass5.this.lambda$onChecked$0$MainActivity$5(view);
                            }
                        }, new ConfirmDialog.OnDialogButtonClickListener(currentHasPoint) {
                            public final /* synthetic */ String f$1;

                            {
                                this.f$1 = r2;
                            }

                            public final boolean onClick(View view) {
                                return MainActivity.AnonymousClass5.this.lambda$onChecked$1$MainActivity$5(this.f$1, view);
                            }
                        });
                        return;
                    }
                    MainActivity.this.addPointBeforeStartDrawing();
                    return;
                }
                String currentHasPoint2 = TrajectoryModel.getInstance().getCurrentHasPoint(MainActivity.this.mWaypoints);
                if (!TextUtils.isEmpty(currentHasPoint2)) {
                    MainActivity.this.showConfirmDialog(ContextUtil.getContext().getString(R.string.txt_end_drawing_title), String.format(ContextUtil.getContext().getString(R.string.txt_end_drawing_tips), new Object[]{currentHasPoint2}), ContextUtil.getContext().getString(R.string.txt_end_drawing_create_new_point), ContextUtil.getContext().getString(R.string.txt_end_drawing_current_point), true, new ConfirmDialog.OnDialogButtonClickListener() {
                        public final boolean onClick(View view) {
                            return MainActivity.AnonymousClass5.this.lambda$onChecked$2$MainActivity$5(view);
                        }
                    }, new ConfirmDialog.OnDialogButtonClickListener(currentHasPoint2) {
                        public final /* synthetic */ String f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final boolean onClick(View view) {
                            return MainActivity.AnonymousClass5.this.lambda$onChecked$3$MainActivity$5(this.f$1, view);
                        }
                    });
                    return;
                }
                MainActivity.this.addPointBeforeEndDrawing();
            }

            public /* synthetic */ boolean lambda$onChecked$0$MainActivity$5(View view) {
                MainActivity.this.addPointBeforeStartDrawing();
                return false;
            }

            public /* synthetic */ boolean lambda$onChecked$1$MainActivity$5(String str, View view) {
                MainActivity.this.startDrawing(str);
                return false;
            }

            public /* synthetic */ boolean lambda$onChecked$2$MainActivity$5(View view) {
                MainActivity.this.addPointBeforeEndDrawing();
                return false;
            }

            public /* synthetic */ boolean lambda$onChecked$3$MainActivity$5(String str, View view) {
                MainActivity.this.endDrawing(str);
                return false;
            }

            public void onCancel() {
                MainActivity.this.showConfirmDialog(ContextUtil.getContext().getString(R.string.txt_cancel_drawing_title), ContextUtil.getContext().getString(R.string.txt_cancel_drawing_tips), ContextUtil.getContext().getString(R.string.txt_cancel_drawing_ok), ContextUtil.getContext().getString(R.string.txt_cancel_drawing_current_cancel), true, new ConfirmDialog.OnDialogButtonClickListener() {
                    public final boolean onClick(View view) {
                        return MainActivity.AnonymousClass5.this.lambda$onCancel$4$MainActivity$5(view);
                    }
                }, $$Lambda$MainActivity$5$Q4gfgpfYw5S6bgRlrZQrN0DNuYo.INSTANCE);
            }

            public /* synthetic */ boolean lambda$onCancel$4$MainActivity$5(View view) {
                MainActivity.this.cancelDrawing();
                return false;
            }
        });
        LabelCameraView labelCameraView2 = new LabelCameraView(getApplicationContext());
        this.labelCameraView = labelCameraView2;
        labelCameraView2.setOnCloseListener(new LabelCameraView.OnCloseListener() {
            public void onClose() {
                if (SoftTypeInfoManager.getInstance().getSoftType() == 10) {
                    MainActivity.this.lineTrackingSettingView.setChecked(false);
                } else if (SoftTypeInfoManager.getInstance().getSoftType() == 8) {
                    MainActivity.this.mainBinding.includeVisualLabel.vlcmSwitchLabelCameraWindow.setChecked(false);
                }
                FloatWindow.get().hide();
            }
        });
        this.mainBinding.includeVisualLabel.vlcmSwitchLabelCameraWindow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    FloatWindow.get().show();
                } else {
                    FloatWindow.get().hide();
                }
            }
        });
        FloatWindow.with(getApplicationContext()).setView((View) this.labelCameraView).setMoveType(2, 0, 0).setFilter(true, MainActivity.class).setX(0, 0.65f).setY(1, 0.5f).setDesktopShow(false).build();
        FloatWindow.get().hide();
    }

    /* access modifiers changed from: private */
    public void addPointBeforeEndDrawing() {
        ((MainPresenter) this.mPresenter).addLineTrackingPoint(new AddMarkDialog.OnInsertMarkerListener() {
            public void insertCancel() {
            }

            public void insertSuccess(String str) {
                MainActivity.this.endDrawing(str);
            }
        });
    }

    /* access modifiers changed from: private */
    public void addPointBeforeStartDrawing() {
        ((MainPresenter) this.mPresenter).addLineTrackingPoint(new AddMarkDialog.OnInsertMarkerListener() {
            public void insertCancel() {
            }

            public void insertSuccess(String str) {
                MainActivity.this.startDrawing(str);
            }
        });
    }

    /* access modifiers changed from: private */
    public void startDrawing(String str) {
        TrajectoryModel.getInstance().setIsInDrawing(true);
        TrajectoryModel.getInstance().setCurrentPathName();
        TrajectoryModel.getInstance().setCurrentStartPointName(str);
        SelfChassis.getInstance().initCurrentRecordPath();
        SendPathRecordRequestBean sendPathRecordRequestBean = new SendPathRecordRequestBean();
        SendPathRecordRequestBean.ArgsBean argsBean = new SendPathRecordRequestBean.ArgsBean();
        sendPathRecordRequestBean.setArgs(argsBean);
        argsBean.setPath_name(TrajectoryModel.getInstance().getCurrentPathName());
        argsBean.setStart_node_name(TrajectoryModel.getInstance().getCurrentStartPointName());
        argsBean.setStart_node_pose(TrajectoryModel.getInstance().getStartPointNodePose(this.mWaypoints));
        argsBean.setPath_width(Float.valueOf(this.lineTrackingSettingView.getPathWidth()));
        argsBean.setPass_capacity(Integer.valueOf(this.lineTrackingSettingView.getPassCapacity()));
        argsBean.setVelocity(Float.valueOf(this.lineTrackingSettingView.getVelocity()));
        argsBean.setCommand(2);
        SelfChassis.getInstance().sendPathRecordParams(sendPathRecordRequestBean);
        runOnUiThread(new Runnable() {
            public void run() {
                MainActivity.this.mainBinding.mainMapControlMrv.setTrajectoryDrawingPath((ArrayList<PointBean>) null);
                MainActivity mainActivity = MainActivity.this;
                mainActivity.showTipsBlack(mainActivity.getString(R.string.tips_start_trajectory_draw));
                MainActivity.this.mainBinding.includeTracking.startDrawingView.startDrawing();
            }
        });
    }

    /* access modifiers changed from: private */
    public void endDrawing(String str) {
        TrajectoryModel.getInstance().setIsInDrawing(false);
        TrajectoryModel.getInstance().setCurrentEndPointName(str);
        SendPathRecordRequestBean sendPathRecordRequestBean = new SendPathRecordRequestBean();
        SendPathRecordRequestBean.ArgsBean argsBean = new SendPathRecordRequestBean.ArgsBean();
        sendPathRecordRequestBean.setArgs(argsBean);
        argsBean.setPath_name(TrajectoryModel.getInstance().getCurrentPathName());
        argsBean.setStart_node_name(TrajectoryModel.getInstance().getCurrentStartPointName());
        argsBean.setStart_node_pose(TrajectoryModel.getInstance().getStartPointNodePose(this.mWaypoints));
        argsBean.setGoal_node_name(TrajectoryModel.getInstance().getCurrentEndPointName());
        argsBean.setGoal_node_pose(TrajectoryModel.getInstance().getEndPointNodePose(this.mWaypoints));
        argsBean.setPath_width(Float.valueOf(this.lineTrackingSettingView.getPathWidth()));
        argsBean.setPass_capacity(Integer.valueOf(this.lineTrackingSettingView.getPassCapacity()));
        argsBean.setVelocity(Float.valueOf(this.lineTrackingSettingView.getVelocity()));
        argsBean.setCommand(3);
        SelfChassis.getInstance().sendPathRecordParams(sendPathRecordRequestBean);
        runOnUiThread(new Runnable() {
            public void run() {
                MainActivity.this.hiddenTips();
                MainActivity.this.mainBinding.includeTracking.startDrawingView.endDrawing();
            }
        });
    }

    /* access modifiers changed from: private */
    public void cancelDrawing() {
        TrajectoryModel.getInstance().setIsInDrawing(false);
        SendPathRecordRequestBean sendPathRecordRequestBean = new SendPathRecordRequestBean();
        SendPathRecordRequestBean.ArgsBean argsBean = new SendPathRecordRequestBean.ArgsBean();
        sendPathRecordRequestBean.setArgs(argsBean);
        argsBean.setPath_name(TrajectoryModel.getInstance().getCurrentPathName());
        argsBean.setCommand(5);
        SelfChassis.getInstance().sendPathRecordParams(sendPathRecordRequestBean);
        runOnUiThread(new Runnable() {
            public void run() {
                MainActivity.this.hiddenTips();
                MainActivity.this.mainBinding.includeTracking.startDrawingView.endDrawing();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void initData() {
        this.mPresenter = MainPresenter.newInstance();
        ((MainPresenter) this.mPresenter).attachView((MainContract.View) this);
        List<LeftToolbarBean> initEditLeftToolbar = ((MainPresenter) this.mPresenter).initEditLeftToolbar();
        this.mLeftToolbarBeans = initEditLeftToolbar;
        this.mLeftToolBarAdapter.refreshData(initEditLeftToolbar);
        refreshRightToolBarAdapter(false);
        this.mainBinding.setShowChassisState(ShowSelfChassisBean.getInstance());
        this.mTopMenuAdapter.refreshData(((MainPresenter) this.mPresenter).initTopMenu());
        showConnectedDialog();
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
        SoftTypeInfoManager.getInstance().setOnSoftTypeListener(new SoftTypeInfoManager.OnSoftTypeListener() {
            public final void softType(int i) {
                MainActivity.this.lambda$initEvent$1$MainActivity(i);
            }
        });
        SelfChassis.getInstance().setOnNavigationManagerCallback(new SelfChassisMsgCallBack.OnNavigationManagerCallback() {
            public void currentMiles(float f) {
            }

            public void obstacleRegion(int i) {
            }

            public void onError(int i, String str) {
            }

            public void onNavigationState(String str, int i) {
            }

            public void selfDiagnosis(SelfDiagnosisResponseBean selfDiagnosisResponseBean) {
            }

            public void chassisState(SelfChassisState selfChassisState) {
                MainActivity.this.runOnUiThread(new Runnable(selfChassisState) {
                    public final /* synthetic */ SelfChassisState f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        MainActivity.AnonymousClass13.this.lambda$chassisState$0$MainActivity$13(this.f$1);
                    }
                });
            }

            public /* synthetic */ void lambda$chassisState$0$MainActivity$13(SelfChassisState selfChassisState) {
                ShowSelfChassisBean chassisState = ((MainPresenter) MainActivity.this.mPresenter).chassisState(selfChassisState);
                MainActivity.this.mainBinding.includeHead.setShowChassisState(chassisState);
                MainActivity.this.mainBinding.setShowChassisState(chassisState);
            }

            public void contend(boolean z) {
                ((MainPresenter) MainActivity.this.mPresenter).contend(z);
            }

            public void getAllPositionList(List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean> list) {
                GsonUtils.toJson(list);
                String str = MainActivity.this.TAG;
                MyLogUtils.Logd(str, "getAllPositionList===>" + GsonUtils.toJson(list));
                List unused = MainActivity.this.mWaypoints = list;
                MainActivity.this.runOnUiThread(new Runnable() {
                    public final void run() {
                        MainActivity.AnonymousClass13.this.lambda$getAllPositionList$1$MainActivity$13();
                    }
                });
            }

            public /* synthetic */ void lambda$getAllPositionList$1$MainActivity$13() {
                MainActivity.this.multiAdapter.refreshData(MainActivity.this.mWaypoints);
                MainActivity.this.intellectPointAdapter.refreshData(MainActivity.this.mWaypoints);
                MainActivity.this.refreshPointAdapter();
            }

            public void localizationConfidence(float f) {
                MainActivity.this.mainBinding.includeHead.setShowChassisState(((MainPresenter) MainActivity.this.mPresenter).localizationConfidence(f));
            }

            public void onLayeredMap(LayeredMapCmdResponseBean layeredMapCmdResponseBean) {
                String str = MainActivity.this.TAG;
                MyLogUtils.Logd(str, "onLayeredMap===>" + GsonUtils.toJson(layeredMapCmdResponseBean));
                if (layeredMapCmdResponseBean == null) {
                    List unused = MainActivity.this.buildList = new ArrayList();
                } else {
                    List unused2 = MainActivity.this.buildList = layeredMapCmdResponseBean.getValues().getList_info();
                }
                MainActivity.this.runOnUiThread(new Runnable() {
                    public final void run() {
                        MainActivity.AnonymousClass13.this.lambda$onLayeredMap$2$MainActivity$13();
                    }
                });
            }

            public /* synthetic */ void lambda$onLayeredMap$2$MainActivity$13() {
                MainActivity.this.buildAdapter.refreshData(MainActivity.this.buildList);
                MainActivity.this.mapManagerAdapter.refreshData(MainActivity.this.buildList);
                MultiScheduleModel.buildList = MainActivity.this.buildList;
            }
        });
        SelfChassis.getInstance().setOnMapInfoCallBack(new SelfChassisMsgCallBack.OnMapInfoCallBack() {
            public void localMapInfo(String str, String str2) {
            }

            public void mapMatchDegree(MapInfoBean mapInfoBean, Bitmap bitmap) {
            }

            public void targetName(String str) {
            }

            public /* synthetic */ void lambda$onCurrentRecordPath$0$MainActivity$14(ArrayList arrayList) {
                MainActivity.this.mainBinding.mainMapControlMrv.setTrajectoryDrawingPath(arrayList);
            }

            public void onCurrentRecordPath(ArrayList<PointBean> arrayList) {
                MainActivity.this.runOnUiThread(new Runnable(arrayList) {
                    public final /* synthetic */ ArrayList f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        MainActivity.AnonymousClass14.this.lambda$onCurrentRecordPath$0$MainActivity$14(this.f$1);
                    }
                });
            }

            public void onGetAllRecordPath(List<List<PointBean>> list, PathGetResponseBean pathGetResponseBean) {
                PathGetResponseBean unused = MainActivity.this.mPathGetResponseBean = pathGetResponseBean;
                MainActivity.this.runOnUiThread(new Runnable(list, pathGetResponseBean) {
                    public final /* synthetic */ List f$1;
                    public final /* synthetic */ PathGetResponseBean f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final void run() {
                        MainActivity.AnonymousClass14.this.lambda$onGetAllRecordPath$1$MainActivity$14(this.f$1, this.f$2);
                    }
                });
            }

            public /* synthetic */ void lambda$onGetAllRecordPath$1$MainActivity$14(List list, PathGetResponseBean pathGetResponseBean) {
                MainActivity.this.mainBinding.mainMapControlMrv.setHighLightIndex(-1);
                MainActivity.this.mainBinding.mainMapControlMrv.setAllTrajectoryDrawingPath(list);
                MainActivity.this.mainBinding.includeTracking.lineTrackingListView.setPathGetResponseBean(pathGetResponseBean);
            }

            public /* synthetic */ void lambda$onFollowPath$2$MainActivity$14(ArrayList arrayList) {
                MainActivity.this.mainBinding.mainMapControlMrv.setTrajectoryFollowPath(arrayList);
            }

            public void onFollowPath(ArrayList<PointBean> arrayList) {
                MainActivity.this.runOnUiThread(new Runnable(arrayList) {
                    public final /* synthetic */ ArrayList f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        MainActivity.AnonymousClass14.this.lambda$onFollowPath$2$MainActivity$14(this.f$1);
                    }
                });
            }

            public /* synthetic */ void lambda$path$3$MainActivity$14(ArrayList arrayList) {
                MainActivity.this.mainBinding.mainMapControlMrv.setPath(arrayList);
            }

            public void path(ArrayList<PointBean> arrayList) {
                MainActivity.this.runOnUiThread(new Runnable(arrayList) {
                    public final /* synthetic */ ArrayList f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        MainActivity.AnonymousClass14.this.lambda$path$3$MainActivity$14(this.f$1);
                    }
                });
            }

            public void map(MapInfoBean mapInfoBean, Bitmap bitmap) {
                MainActivity.this.runOnUiThread(new Runnable(mapInfoBean, bitmap) {
                    public final /* synthetic */ MapInfoBean f$1;
                    public final /* synthetic */ Bitmap f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final void run() {
                        MainActivity.AnonymousClass14.this.lambda$map$4$MainActivity$14(this.f$1, this.f$2);
                    }
                });
            }

            public /* synthetic */ void lambda$map$4$MainActivity$14(MapInfoBean mapInfoBean, Bitmap bitmap) {
                MainActivity.this.mainBinding.mainMapControlMrv.clearBjd();
                ((MainPresenter) MainActivity.this.mPresenter).mapLogic(mapInfoBean, bitmap);
            }

            public /* synthetic */ void lambda$laser$5$MainActivity$14(ArrayList arrayList) {
                MainActivity.this.mainBinding.mainMapControlMrv.setLaser(arrayList);
            }

            public void laser(ArrayList<PointBean> arrayList) {
                MainActivity.this.runOnUiThread(new Runnable(arrayList) {
                    public final /* synthetic */ ArrayList f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        MainActivity.AnonymousClass14.this.lambda$laser$5$MainActivity$14(this.f$1);
                    }
                });
            }

            public void pose(PointBean pointBean) {
                MainActivity.this.runOnUiThread(new Runnable(pointBean) {
                    public final /* synthetic */ PointBean f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        MainActivity.AnonymousClass14.this.lambda$pose$6$MainActivity$14(this.f$1);
                    }
                });
            }

            public /* synthetic */ void lambda$pose$6$MainActivity$14(PointBean pointBean) {
                MainActivity.this.mainBinding.mainMapControlMrv.setPose(pointBean);
                MainActivity.this.isPassMarker();
            }

            public void virtualWall(List<VirtualWallOperationGetWallsResponseBean.ValuesBean.VwallsBean.WallsBean> list) {
                MainActivity.this.runOnUiThread(new Runnable(list) {
                    public final /* synthetic */ List f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        MainActivity.AnonymousClass14.this.lambda$virtualWall$7$MainActivity$14(this.f$1);
                    }
                });
            }

            public /* synthetic */ void lambda$virtualWall$7$MainActivity$14(List list) {
                String str = MainActivity.this.TAG;
                MyLogUtils.Logd(str, "points.size():" + list.size());
                VirtualWallInfoManager.getInstance().setPoints(list);
                MainActivity.this.mainBinding.mainMapControlMrv.setVirtualWall((List) GsonUtils.fromLocalJson(GsonUtils.toJson(list), new TypeToken<List<VirtualWallOperationGetWallsResponseBean.ValuesBean.VwallsBean.WallsBean>>() {
                }.getType()));
            }

            public void area(List<AreaItemBean> list) {
                MainActivity.this.refreshAreaView(list);
            }

            public void bjd(List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean> list) {
                MainActivity.this.runOnUiThread(new Runnable(list) {
                    public final /* synthetic */ List f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        MainActivity.AnonymousClass14.this.lambda$bjd$8$MainActivity$14(this.f$1);
                    }
                });
            }

            public /* synthetic */ void lambda$bjd$8$MainActivity$14(List list) {
                MainActivity.this.mainBinding.mainMapControlMrv.setBjd(list);
            }

            public void currentMapArea(double d) {
                MainActivity.this.runOnUiThread(new Runnable(d) {
                    public final /* synthetic */ double f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        MainActivity.AnonymousClass14.this.lambda$currentMapArea$9$MainActivity$14(this.f$1);
                    }
                });
            }

            public /* synthetic */ void lambda$currentMapArea$9$MainActivity$14(double d) {
                MainActivity.this.mainBinding.mainAreaContentTv.setText(String.format(MainActivity.this.getString(R.string.area_unit), new Object[]{Double.valueOf(d)}));
            }

            public /* synthetic */ void lambda$upCamData$10$MainActivity$14(ArrayList arrayList) {
                MainActivity.this.mainBinding.mainMapControlMrv.setUpCamData(arrayList);
            }

            public void upCamData(ArrayList<PointBean> arrayList) {
                MainActivity.this.runOnUiThread(new Runnable(arrayList) {
                    public final /* synthetic */ ArrayList f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        MainActivity.AnonymousClass14.this.lambda$upCamData$10$MainActivity$14(this.f$1);
                    }
                });
            }

            public void downCamData(ArrayList<PointBean> arrayList) {
                MainActivity.this.runOnUiThread(new Runnable(arrayList) {
                    public final /* synthetic */ ArrayList f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        MainActivity.AnonymousClass14.this.lambda$downCamData$11$MainActivity$14(this.f$1);
                    }
                });
            }

            public /* synthetic */ void lambda$downCamData$11$MainActivity$14(ArrayList arrayList) {
                MainActivity.this.mainBinding.mainMapControlMrv.setDownCamData(arrayList);
            }
        });
        this.mainBinding.includeHead.headCreateMapIb.setOnClickListener(new MyOnClickListener());
        this.mainBinding.includeHead.headCancelIb.setOnClickListener(new MyOnClickListener());
        this.mainBinding.includeHead.headBuildingCl.setOnClickListener(new MyOnClickListener());
        this.mainBinding.mainBuildInfoCl.setOnClickListener(new MyOnClickListener());
        this.mainBinding.mainPointInfoCl.setOnClickListener(new MyOnClickListener());
        this.mainBinding.mainBottomLayerIv.setOnClickListener(new MyOnClickListener());
        this.mainBinding.clRobotList.setOnClickListener(new MyOnClickListener());
        this.mLeftToolBarAdapter.setOnItemClickListener(new LeftToolBarAdapter.OnItemClickListener() {
            public final void onItemClick(LeftToolbarBean leftToolbarBean) {
                MainActivity.this.lambda$initEvent$2$MainActivity(leftToolbarBean);
            }
        });
        this.mRightToolBarAdapter.setOnItemClickListener(new RightToolBarAdapter.OnItemClickListener() {
            public final void onItemClick(ToolbarBean toolbarBean) {
                MainActivity.this.lambda$initEvent$3$MainActivity(toolbarBean);
            }
        });
        this.mainBinding.mainLineIv.setOnClickListener(new MyOnClickListener());
        this.mainBinding.mainRectangleIv.setOnClickListener(new MyOnClickListener());
        this.mainBinding.mainBrushSmallRl.setOnClickListener(new MyOnClickListener());
        this.mainBinding.mainBrushMediumRl.setOnClickListener(new MyOnClickListener());
        this.mainBinding.mainBrushBigRl.setOnClickListener(new MyOnClickListener());
        this.mainBinding.mainZoomInTv.setOnClickListener(new MyOnClickListener());
        this.mainBinding.mainZoomOutTv.setOnClickListener(new MyOnClickListener());
        this.mainBinding.mainBottomRestoreIv.setOnClickListener(new MyOnClickListener());
        this.mainBinding.mainBottomNavIv.setOnClickListener(new MyOnClickListener());
        this.mainBinding.mainRightBtnOkIb.setOnClickListener(new MyOnClickListener());
        this.buildAdapter.setOnItemClickListener(new BuildAdapter.OnItemClickListener() {
            public final void onItemClick(LayeredMapCmdResponseBean.ValuesBean.ListInfoBean listInfoBean) {
                MainActivity.this.lambda$initEvent$4$MainActivity(listInfoBean);
            }
        });
        this.floorAdapter.setOnItemClickListener(new FloorAdapter.OnItemClickListener() {
            public final void onItemClick(String str, String str2) {
                MainActivity.this.lambda$initEvent$5$MainActivity(str, str2);
            }
        });
        this.mapManagerAdapter.setOnItemClickListener(new MapManagerAdapter.OnItemClickListener() {
            public void onItemDeleteClick(String str, String str2) {
                ConfirmDialog buildDefault2 = ConfirmDialog.buildDefault2(MainActivity.this.getString(R.string.delete_map), String.format(MainActivity.this.getString(R.string.confirm_delete_map), new Object[]{str, str2}));
                buildDefault2.setOnOkButtonClickListener(new ConfirmDialog.OnDialogButtonClickListener(str, str2) {
                    public final /* synthetic */ String f$1;
                    public final /* synthetic */ String f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final boolean onClick(View view) {
                        return MainActivity.AnonymousClass15.this.lambda$onItemDeleteClick$0$MainActivity$15(this.f$1, this.f$2, view);
                    }
                });
                buildDefault2.showAllowingStateLoss(MainActivity.this.getSupportFragmentManager());
            }

            public /* synthetic */ boolean lambda$onItemDeleteClick$0$MainActivity$15(String str, String str2, View view) {
                ((MainPresenter) MainActivity.this.mPresenter).deleteMap(str, str2);
                MainActivity.this.mapManagerAdapter.remove(str, str2);
                return false;
            }

            public void onItemEditClick(String str, String str2) {
                MainActivity mainActivity = MainActivity.this;
                DialogHelper.showEditMapDialog(mainActivity, 0, str2, str, mainActivity.buildList);
            }
        });
        this.pointAdapter.setOnItemClickListener(new PointAdapter.OnItemClickListener() {
            public void onItemDeleteClick(MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean waypointsBean) {
                if (waypointsBean != null) {
                    if (waypointsBean.getTime_out() == 1.0f) {
                        ConfirmDialog.buildDefault(MainActivity.this.getString(R.string.text_delete_mark_point), MainActivity.this.getString(R.string.text_please_delete_mark_point_after_deleting_trajectory_first)).showAllowingStateLoss(MainActivity.this.getSupportFragmentManager());
                        return;
                    }
                    String name = waypointsBean.getName();
                    ConfirmDialog buildDefault2 = ConfirmDialog.buildDefault2(MainActivity.this.getString(R.string.delete_point), String.format(MainActivity.this.getString(R.string.confirm_delete_point), new Object[]{name}));
                    buildDefault2.setOnOkButtonClickListener(new ConfirmDialog.OnDialogButtonClickListener(name) {
                        public final /* synthetic */ String f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final boolean onClick(View view) {
                            return MainActivity.AnonymousClass16.this.lambda$onItemDeleteClick$0$MainActivity$16(this.f$1, view);
                        }
                    });
                    buildDefault2.showAllowingStateLoss(MainActivity.this.getSupportFragmentManager());
                }
            }

            public /* synthetic */ boolean lambda$onItemDeleteClick$0$MainActivity$16(String str, View view) {
                MainActivity.this.pointAdapter.remove(str);
                ((MainPresenter) MainActivity.this.mPresenter).deletePoint(str);
                return false;
            }

            public void onItemEditClick(final MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean waypointsBean) {
                if (waypointsBean != null) {
                    if (waypointsBean.getTime_out() == 1.0f) {
                        ConfirmDialog.buildDefault(MainActivity.this.getString(R.string.edit_mark_title), MainActivity.this.getString(R.string.text_trajectory_mark_point_can_not_edit)).showAllowingStateLoss(MainActivity.this.getSupportFragmentManager());
                        return;
                    }
                    ((MainPresenter) MainActivity.this.mPresenter).showAddRandomPoint();
                    MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean.PoseBean pose = waypointsBean.getPose();
                    MainActivity.this.mainBinding.addRandomMarkView.setOnDialogButtonOnClickListener(new AddMarkModeView.OnDialogButtonOnClickListener() {
                        public void cancelBtn() {
                        }

                        public boolean okBtn(int i, String str, float f, float f2, float f3) {
                            return TextUtils.equals(waypointsBean.getName(), str) || !DialogHelper.checkMarkerExsit(str, MainActivity.this.mWaypoints);
                        }
                    });
                    MainActivity.this.mainBinding.addRandomMarkView.setData(pose.getPosition().getX(), pose.getPosition().getY(), DialogHelper.getEularBeanByWaypoint(waypointsBean).getY(), waypointsBean.getBehavior_code(), waypointsBean.getName());
                    MainActivity.this.mainBinding.addRandomMarkView.setVisibility(0);
                    MainActivity.this.mainBinding.mainMapControlMrv.mMapInfoLlpv.setShowAddRandomPoint(true);
                }
            }

            public void onItemNavigationClick(MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean waypointsBean, boolean z) {
                MainActivity.this.doOnItemNavigationClick(waypointsBean);
            }
        });
        this.trajectoryPointAdapter.setOnItemClickListener(new TrajectoryPointAdapter.OnItemClickListener() {
            public void onItemDeleteClick(String str) {
            }

            public void onItemEditClick(MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean waypointsBean) {
            }

            public void onItemNavigationClick(MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean waypointsBean, boolean z) {
                MainActivity.this.doOnItemNavigationClick(waypointsBean);
            }
        });
        this.multiAdapter.setOnItemClickListener(new MultiAdapter.OnItemClickListener() {
            public final void onItemAdd2Line(MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean waypointsBean) {
                MainActivity.this.lambda$initEvent$6$MainActivity(waypointsBean);
            }
        });
        this.intellectPointAdapter.setOnItemClickListener(new IntellectPointAdapter.OnItemClickListener() {
            public final void onItemAdd2Line(MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean waypointsBean) {
                MainActivity.this.lambda$initEvent$7$MainActivity(waypointsBean);
            }
        });
        this.mainBinding.includeTracking.btnSure.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                MainActivity.this.lambda$initEvent$8$MainActivity(view);
            }
        });
        this.mainBinding.includeTracking.intellectMultiTitleCl.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MainActivity.this.mainBinding.includeTracking.intellectPointListRv.getVisibility() == 0) {
                    MainActivity.this.mainBinding.includeTracking.intellectPointListRv.setVisibility(8);
                    MainActivity.this.mainBinding.includeTracking.intellectMultiMoreIv.setBackground(MainActivity.this.getDrawable(R.mipmap.right_arrow));
                    return;
                }
                MainActivity.this.mainBinding.includeTracking.intellectPointListRv.setVisibility(0);
                MainActivity.this.mainBinding.includeTracking.intellectMultiMoreIv.setBackground(MainActivity.this.getDrawable(R.mipmap.down_arrow));
            }
        });
        this.mainBinding.includeDirectionControl.mainDirectionAllRv.setOnAngleChangeListener(this.onAngleChangeListener);
        this.mainBinding.includeDirectionControl.mainDirectionFourRv.setOnDirectionListener(this.onDirectionListener);
        this.mainBinding.mainBottomStopIv.setOnClickListener(new MyOnClickListener());
        this.mainBinding.mainRightBtnLockIb.setOnClickListener(new MyOnClickListener());
        this.mainBinding.mainRightBtnCancelNavIb.setOnClickListener(new MyOnClickListener());
        this.mainBinding.mainBottomLockIv.setOnClickListener(new MyOnClickListener());
        this.mainBinding.mainTipsTv.setOnClickListener(new ClickUtils.OnMultiClickListener(6) {
            public void onBeforeTriggerClick(View view, int i) {
            }

            public void onTriggerClick(View view) {
                ((MainPresenter) MainActivity.this.mPresenter).forcedCancelFloorNav();
            }
        });
        this.mainBinding.mainMapControlMrv.setOnDrawAreaListener(this.myDrawAreaListener);
        this.mainBinding.rightAreaListTitleCl.setOnClickListener(new MyOnClickListener());
        this.mainBinding.mainLeftCleanDrawAreaIv.setOnClickListener(new MyOnClickListener());
        this.mainBinding.mainMapControlMrv.setMapViewListener(new MapRlView.OnMapViewListener() {
            public final boolean navigate() {
                return MainActivity.this.lambda$initEvent$9$MainActivity();
            }
        });
        this.mainBinding.mainMapControlMrv.setOnMapViewAddRandomPointListener(new MapRlView.OnMapViewAddRandomPointListener() {
            public void onAddRandomPoint(float f, float f2, float f3) {
                MainActivity.this.mainBinding.addRandomMarkView.setData(f, f2, f3);
                MainActivity.this.mainBinding.addRandomMarkView.setVisibility(0);
                MainActivity.this.mainBinding.mainMapControlMrv.mMapInfoLlpv.setShowAddRandomPoint(true);
            }
        });
        this.mainBinding.addRandomMarkView.setOnUpdatePositionListener(new AddMarkModeView.OnUpdatePositionListener() {
            public void update(PointBean pointBean) {
                PointF fromWorldCoordinates = MainActivity.this.mainBinding.mainMapControlMrv.mMapInfoLlpv.fromWorldCoordinates(pointBean.getX(), pointBean.getY());
                pointBean.setX(fromWorldCoordinates.x);
                pointBean.setY(fromWorldCoordinates.y);
                MainActivity.this.mainBinding.mainMapControlMrv.mMapInfoLlpv.setRobotAddRandomPointPosition(pointBean);
            }
        });
        this.mVisualLabelAdapter.setOnItemClickListener(new VisualLabelAdapter.OnItemClickListener() {
            public final void onItemDeleteClick(String str) {
                MainActivity.this.lambda$initEvent$10$MainActivity(str);
            }
        });
        this.mainBinding.includeVisualLabel.labelInfoCl.setOnClickListener(new MyOnClickListener());
        this.mainBinding.includeNav.navMultiTitleCl.setOnClickListener(new MyOnClickListener());
        this.mainBinding.includeNav.navTypeRg.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        this.mainBinding.includeNav.navStartNavCl.setOnClickListener(new MyOnClickListener());
        this.mainBinding.includeNav.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                MainActivity.this.lambda$initEvent$11$MainActivity(radioGroup, i);
            }
        });
        this.mainBinding.mainPointSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public final void onRefresh() {
                MainActivity.this.lambda$initEvent$12$MainActivity();
            }
        });
        this.mainBinding.includeNav.navMultiClearBtn.setOnClickListener(new MyOnClickListener());
        LiveDatabus.getInstance().with(DTLiveDataConstant.MAIN_CONFIRM_DIALOG_EVENT).observe(this, new Observer() {
            public final void onChanged(Object obj) {
                MainActivity.this.lambda$initEvent$13$MainActivity(obj);
            }
        });
        LiveDatabus.getInstance().with(DTLiveDataConstant.MAIN_CONNECT_DIALOG_EVENT).observe(this, new Observer() {
            public final void onChanged(Object obj) {
                MainActivity.this.lambda$initEvent$14$MainActivity(obj);
            }
        });
        this.mNotificationsListener = new SelfChassisListenerUtils.OnBaseSelfChassisListener() {
            public final void onSelfChassisMsg(SelfChassisEventMsg selfChassisEventMsg) {
                MainActivity.this.lambda$initEvent$15$MainActivity(selfChassisEventMsg);
            }
        };
        SelfChassisListenerUtils.getInstance().addNotificationsListener(this.mNotificationsListener);
        this.mTopMenuAdapter.setOnItemClickListener(new TopMenuAdapter.OnItemClickListener() {
            public final void onItemClick(TopMenuBean topMenuBean) {
                MainActivity.this.lambda$initEvent$16$MainActivity(topMenuBean);
            }
        });
        this.mainBinding.mainLayerView.setOnSelectListener(new LayerSelectView.OnSelectListener() {
            public final void onRefresh() {
                MainActivity.this.lambda$initEvent$17$MainActivity();
            }
        });
        this.mainBinding.includeTracking.drawingSwitchView.setOnSelectListener(new DrawingSwitchView.OnSelectListener() {
            public final void onSelected(DrawingSwitchView.SelectionEnum selectionEnum) {
                MainActivity.this.lambda$initEvent$18$MainActivity(selectionEnum);
            }
        });
        this.mainBinding.mainPointInfoFl.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainActivity mainActivity = MainActivity.this;
                mainActivity.showPointManagerWindow(mainActivity.mainBinding.mainPointInfoCl);
            }
        });
        $$Lambda$MainActivity$HafJoiK38E0z3Uu6DFPDzBIpgg r0 = new SelfChassisListenerUtils.OnBaseSelfChassisListener() {
            public final void onSelfChassisMsg(SelfChassisEventMsg selfChassisEventMsg) {
                MainActivity.this.lambda$initEvent$20$MainActivity(selfChassisEventMsg);
            }
        };
        this.configMqttListener = r0;
        SelfChassisListenerUtils.ConfigMqtt.addConfigMqttListener(r0);
    }

    public /* synthetic */ void lambda$initEvent$0$MainActivity(int i) {
        ((MainPresenter) this.mPresenter).changeSoftType(i);
    }

    public /* synthetic */ void lambda$initEvent$1$MainActivity(int i) {
        runOnUiThread(new Runnable(i) {
            public final /* synthetic */ int f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                MainActivity.this.lambda$initEvent$0$MainActivity(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$initEvent$2$MainActivity(LeftToolbarBean leftToolbarBean) {
        this.mLeftToolTypeDesc = leftToolbarBean.getLabelText();
        String labelId = leftToolbarBean.getLabelId();
        this.mLeftToolType = labelId;
        selectLeftToolBtn(labelId);
        if (TextUtils.equals(this.mLeftToolType, "layer_slow_area") || TextUtils.equals(this.mLeftToolType, "layer_work_area") || TextUtils.equals(this.mLeftToolType, "layer_strong_light_area") || TextUtils.equals(this.mLeftToolType, "layer_danger_area") || TextUtils.equals(this.mLeftToolType, "layer_narrow_channel") || TextUtils.equals(this.mLeftToolType, "layer_slope_area") || TextUtils.equals(this.mLeftToolType, "layer_label_area")) {
            this.mAreaAdapter.refreshData(this.mAreaItemBeanList, this.mLeftToolType);
        }
    }

    public /* synthetic */ void lambda$initEvent$3$MainActivity(ToolbarBean toolbarBean) {
        selectRightToolBtn(toolbarBean.getLabelId());
    }

    public /* synthetic */ void lambda$initEvent$4$MainActivity(LayeredMapCmdResponseBean.ValuesBean.ListInfoBean listInfoBean) {
        ((MainPresenter) this.mPresenter).selectBuild(listInfoBean);
    }

    public /* synthetic */ void lambda$initEvent$5$MainActivity(String str, String str2) {
        ((MainPresenter) this.mPresenter).selectFloor(str, str2);
    }

    public /* synthetic */ void lambda$initEvent$6$MainActivity(MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean waypointsBean) {
        if (!App.isMultiNaving()) {
            MyLogUtils.Logd(this.TAG, "onItemAdd2Line");
            this.mPatrolRoutes.add(waypointsBean);
            this.mPatrolRouteAdapter.setDeletePosition(-1);
            this.mainBinding.includeNav.navMultiLineRv.scrollToPosition(this.mPatrolRouteAdapter.getItemCount() - 1);
            showPatrolRouteView();
        }
    }

    public /* synthetic */ void lambda$initEvent$7$MainActivity(MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean waypointsBean) {
        if (this.mIntellectRoutes.size() < 2) {
            this.mIntellectRoutes.add(waypointsBean);
            updateIntellectRouteView();
        }
    }

    public /* synthetic */ void lambda$initEvent$8$MainActivity(View view) {
        if (isRepetitive(this.mIntellectRoutes.get(0).getName(), this.mIntellectRoutes.get(1).getName())) {
            showConfirmDialog((String) null, getString(R.string.txt_already_exist_the_route), getString(R.string.txt_sure), (String) null, (ConfirmDialog.OnDialogButtonClickListener) null, (ConfirmDialog.OnDialogButtonClickListener) null);
            return;
        }
        TrajectoryModel.getInstance().setCurrentPathName();
        TrajectoryModel.getInstance().setCurrentStartPointName(this.mIntellectRoutes.get(0).getName());
        TrajectoryModel.getInstance().setCurrentEndPointName(this.mIntellectRoutes.get(1).getName());
        SendPathRecordRequestBean sendPathRecordRequestBean = new SendPathRecordRequestBean();
        SendPathRecordRequestBean.ArgsBean argsBean = new SendPathRecordRequestBean.ArgsBean();
        sendPathRecordRequestBean.setArgs(argsBean);
        argsBean.setPath_name(TrajectoryModel.getInstance().getCurrentPathName());
        argsBean.setStart_node_name(TrajectoryModel.getInstance().getCurrentStartPointName());
        argsBean.setStart_node_pose(TrajectoryModel.getInstance().getStartPointNodePose(this.mWaypoints));
        argsBean.setGoal_node_name(TrajectoryModel.getInstance().getCurrentEndPointName());
        argsBean.setGoal_node_pose(TrajectoryModel.getInstance().getEndPointNodePose(this.mWaypoints));
        argsBean.setPath_width(Float.valueOf(this.lineTrackingSettingView.getPathWidth()));
        argsBean.setPass_capacity(Integer.valueOf(this.lineTrackingSettingView.getPassCapacity()));
        argsBean.setVelocity(Float.valueOf(this.lineTrackingSettingView.getVelocity()));
        argsBean.setCommand(1);
        SelfChassis.getInstance().sendPathRecordParams(sendPathRecordRequestBean);
        this.mIntellectRoutes.clear();
        updateIntellectRouteView();
    }

    public /* synthetic */ boolean lambda$initEvent$9$MainActivity() {
        if (App.isScram) {
            ConfirmDialog.buildDefault(getString(R.string.dialog_tip), getString(R.string.robot_emergency_stop)).showAllowingStateLoss(getSupportFragmentManager());
            return true;
        }
        ((MainPresenter) this.mPresenter).resetNotificationState();
        ((MainPresenter) this.mPresenter).setRightTopBtnCancelNav(true);
        return false;
    }

    public /* synthetic */ void lambda$initEvent$10$MainActivity(String str) {
        ((MainPresenter) this.mPresenter).deleteVisualLabelByName(str);
    }

    public /* synthetic */ void lambda$initEvent$11$MainActivity(RadioGroup radioGroup, int i) {
        if (!App.isMultiNaving()) {
            if (R.id.nav_multi_single_rb == i) {
                this.mainBinding.includeNav.navMultiTimesEt.setText("1");
                this.mainBinding.includeNav.navMultiTimesEt.setEnabled(false);
                this.mainBinding.includeNav.navMultiTimesEt.setBackground((Drawable) null);
                return;
            }
            this.mainBinding.includeNav.navMultiTimesEt.setText("3");
            this.mainBinding.includeNav.navMultiTimesEt.setEnabled(true);
            this.mainBinding.includeNav.navMultiTimesEt.setBackgroundResource(R.drawable.selector_nav_bg_type);
        }
    }

    public /* synthetic */ void lambda$initEvent$12$MainActivity() {
        this.mainBinding.mainPointSwipeRefresh.setRefreshing(false);
        ((MainPresenter) this.mPresenter).sendGetFloorData();
    }

    public /* synthetic */ void lambda$initEvent$13$MainActivity(Object obj) {
        if (obj instanceof ConfirmDialogLiveDataEvent) {
            showConfirmDialog((ConfirmDialogLiveDataEvent) obj);
        }
    }

    public /* synthetic */ void lambda$initEvent$14$MainActivity(Object obj) {
        if (obj instanceof String) {
            String obj2 = obj.toString();
            SelfChassis.getInstance().disconnectSelfChassis();
            if (TextUtils.equals(obj2, "showConnectedDialog")) {
                showConnectedDialog();
            } else {
                showNotConnectedWifiDialog();
            }
        }
    }

    public /* synthetic */ void lambda$initEvent$15$MainActivity(SelfChassisEventMsg selfChassisEventMsg) {
        if (this.mPresenter != null) {
            ((MainPresenter) this.mPresenter).dealNotification(selfChassisEventMsg);
        }
    }

    public /* synthetic */ void lambda$initEvent$16$MainActivity(TopMenuBean topMenuBean) {
        ((MainPresenter) this.mPresenter).dealTopMenuItem(topMenuBean);
    }

    public /* synthetic */ void lambda$initEvent$17$MainActivity() {
        showCameraPointCloudView();
        this.mainBinding.mainMapControlMrv.refreshView();
    }

    public /* synthetic */ void lambda$initEvent$18$MainActivity(DrawingSwitchView.SelectionEnum selectionEnum) {
        if (selectionEnum == DrawingSwitchView.SelectionEnum.IntellectDrawing) {
            this.mainBinding.includeTracking.intellectMultiListLl.setVisibility(0);
            this.mainBinding.includeTracking.intellectMultiLineCl.setVisibility(0);
            this.mainBinding.includeTracking.startDrawingView.setVisibility(8);
            return;
        }
        this.mainBinding.includeTracking.intellectMultiListLl.setVisibility(8);
        this.mainBinding.includeTracking.intellectMultiLineCl.setVisibility(8);
        this.mainBinding.includeTracking.startDrawingView.setVisibility(0);
    }

    public /* synthetic */ void lambda$initEvent$20$MainActivity(SelfChassisEventMsg selfChassisEventMsg) {
        ThreadUtils.runOnUiThread(new Runnable(selfChassisEventMsg) {
            public final /* synthetic */ SelfChassisEventMsg f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                MainActivity.this.lambda$initEvent$19$MainActivity(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$initEvent$19$MainActivity(SelfChassisEventMsg selfChassisEventMsg) {
        MultiRobotStationRespBean multiRobotStationRespBean = (MultiRobotStationRespBean) selfChassisEventMsg.getData();
        if (multiRobotStationRespBean.getResult().booleanValue()) {
            MultiRobotUtil.isTurnOnMultiRobot = multiRobotStationRespBean.getValues().getSwitch_on().booleanValue();
        }
        showRobotListView(true);
    }

    private void showRobotListView(boolean z) {
        this.mainBinding.robotListContainer.setVisibility((!z || !MultiRobotUtil.isTurnOnMultiRobot) ? 8 : 0);
    }

    public void showPointManagerWindow(View view) {
        View inflate = ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(R.layout.popup_window_point_manager, (ViewGroup) null, false);
        final FixedPopupWindow fixedPopupWindow = new FixedPopupWindow(inflate, -2, -2, true);
        fixedPopupWindow.setAnimationStyle(-1);
        fixedPopupWindow.setOutsideTouchable(true);
        inflate.measure(0, 0);
        fixedPopupWindow.showAsDropDown(view, (int) getResources().getDimension(R.dimen.pt14), -view.getMeasuredHeight(), GravityCompat.END);
        inflate.findViewById(R.id.ll_add_current_point).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ((MainPresenter) MainActivity.this.mPresenter).showAddRandomPoint();
                MainActivity.this.showAddMarkDialog();
                fixedPopupWindow.dismiss();
            }
        });
        inflate.findViewById(R.id.ll_add_arbitrary_point).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ((MainPresenter) MainActivity.this.mPresenter).showAddRandomPoint();
                fixedPopupWindow.dismiss();
            }
        });
        inflate.findViewById(R.id.ll_nav_cross_floor).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ((MainPresenter) MainActivity.this.mPresenter).showOtherFloorPoint();
                fixedPopupWindow.dismiss();
            }
        });
    }

    private void updateIntellectRouteView() {
        this.mIntellectRouteAdapter.setDeletePosition(-1);
        boolean z = false;
        if (this.mIntellectRoutes.size() > 0) {
            this.mainBinding.includeTracking.intellectMultiLineEmptyTv.setVisibility(8);
            this.mainBinding.includeTracking.intellectMultiLineRv.setVisibility(0);
        } else {
            this.mainBinding.includeTracking.intellectMultiLineEmptyTv.setVisibility(0);
            this.mainBinding.includeTracking.intellectMultiLineRv.setVisibility(8);
        }
        AppCompatTextView appCompatTextView = this.mainBinding.includeTracking.btnSure;
        if (this.mIntellectRoutes.size() == 2) {
            z = true;
        }
        appCompatTextView.setEnabled(z);
    }

    private boolean isRepetitive(String str, String str2) {
        PathGetResponseBean pathGetResponseBean = this.mPathGetResponseBean;
        if (!(pathGetResponseBean == null || pathGetResponseBean.getValues() == null || this.mPathGetResponseBean.getValues().getAdjacent_list() == null || this.mPathGetResponseBean.getValues().getAdjacent_list().getNodes() == null)) {
            for (PathGetResponseBean.ValuesBean.AdjacentListBean.NodesBean next : this.mPathGetResponseBean.getValues().getAdjacent_list().getNodes()) {
                if (next.getStart_node_name().equals(str) && next.getGoal_node_name().equals(str2)) {
                    return true;
                }
                if (next.getStart_node_name().equals(str2) && next.getGoal_node_name().equals(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void doOnItemNavigationClick(MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean waypointsBean) {
        if (App.isScram) {
            ConfirmDialog.buildDefault(getString(R.string.point_navigation), getString(R.string.robot_emergency_stop)).showAllowingStateLoss(getSupportFragmentManager());
            return;
        }
        ((MainPresenter) this.mPresenter).havPointInDangerousArea(this.mAreaItemBeanList, waypointsBean);
        String name = waypointsBean.getName();
        ConfirmDialog buildDefault2 = ConfirmDialog.buildDefault2(getString(R.string.point_navigation), String.format(getString(R.string.confirm_point_navigation), new Object[]{name}));
        buildDefault2.setOnOkButtonClickListener(new ConfirmDialog.OnDialogButtonClickListener(waypointsBean) {
            public final /* synthetic */ MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean f$1;

            {
                this.f$1 = r2;
            }

            public final boolean onClick(View view) {
                return MainActivity.this.lambda$doOnItemNavigationClick$21$MainActivity(this.f$1, view);
            }
        });
        buildDefault2.showAllowingStateLoss(getSupportFragmentManager());
    }

    public /* synthetic */ boolean lambda$doOnItemNavigationClick$21$MainActivity(MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean waypointsBean, View view) {
        ((MainPresenter) this.mPresenter).navigationToPoint(waypointsBean);
        return false;
    }

    private void showConfirmDialog(ConfirmDialogLiveDataEvent confirmDialogLiveDataEvent) {
        runOnUiThread(new Runnable(confirmDialogLiveDataEvent.getTitle(), confirmDialogLiveDataEvent.getMessage(), confirmDialogLiveDataEvent.getOkStrBtn(), confirmDialogLiveDataEvent.getCancelStrBtn(), confirmDialogLiveDataEvent.getOnOkButtonClickListener(), confirmDialogLiveDataEvent.getOnCancelButtonClickListener()) {
            public final /* synthetic */ String f$1;
            public final /* synthetic */ String f$2;
            public final /* synthetic */ String f$3;
            public final /* synthetic */ String f$4;
            public final /* synthetic */ ConfirmDialog.OnDialogButtonClickListener f$5;
            public final /* synthetic */ ConfirmDialog.OnDialogButtonClickListener f$6;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
                this.f$5 = r6;
                this.f$6 = r7;
            }

            public final void run() {
                MainActivity.this.lambda$showConfirmDialog$22$MainActivity(this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
            }
        });
    }

    public /* synthetic */ void lambda$showConfirmDialog$22$MainActivity(String str, String str2, String str3, String str4, ConfirmDialog.OnDialogButtonClickListener onDialogButtonClickListener, ConfirmDialog.OnDialogButtonClickListener onDialogButtonClickListener2) {
        ConfirmDialog confirmDialog = this.mConfirmDialog;
        if (confirmDialog != null && confirmDialog.isAdded()) {
            this.mConfirmDialog.dismissAllowingStateLoss();
        }
        ConfirmDialog build = ConfirmDialog.build(str, str2, str3, str4);
        this.mConfirmDialog = build;
        build.setOnOkButtonClickListener(onDialogButtonClickListener);
        this.mConfirmDialog.setOnCancelButtonClickListener(onDialogButtonClickListener2);
        this.mConfirmDialog.showAllowingStateLoss(getSupportFragmentManager());
    }

    /* access modifiers changed from: private */
    public void showConfirmDialog(String str, String str2, String str3, String str4, boolean z, ConfirmDialog.OnDialogButtonClickListener onDialogButtonClickListener, ConfirmDialog.OnDialogButtonClickListener onDialogButtonClickListener2) {
        runOnUiThread(new Runnable(str, str2, str3, str4, z, onDialogButtonClickListener, onDialogButtonClickListener2) {
            public final /* synthetic */ String f$1;
            public final /* synthetic */ String f$2;
            public final /* synthetic */ String f$3;
            public final /* synthetic */ String f$4;
            public final /* synthetic */ boolean f$5;
            public final /* synthetic */ ConfirmDialog.OnDialogButtonClickListener f$6;
            public final /* synthetic */ ConfirmDialog.OnDialogButtonClickListener f$7;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
                this.f$5 = r6;
                this.f$6 = r7;
                this.f$7 = r8;
            }

            public final void run() {
                MainActivity.this.lambda$showConfirmDialog$23$MainActivity(this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7);
            }
        });
    }

    public /* synthetic */ void lambda$showConfirmDialog$23$MainActivity(String str, String str2, String str3, String str4, boolean z, ConfirmDialog.OnDialogButtonClickListener onDialogButtonClickListener, ConfirmDialog.OnDialogButtonClickListener onDialogButtonClickListener2) {
        ConfirmDialog confirmDialog = this.mConfirmDialog;
        if (confirmDialog != null && confirmDialog.isAdded()) {
            this.mConfirmDialog.dismissAllowingStateLoss();
        }
        ConfirmDialog build = ConfirmDialog.build(str, str2, str3, str4, Boolean.valueOf(z));
        this.mConfirmDialog = build;
        build.setOnOkButtonClickListener(onDialogButtonClickListener);
        this.mConfirmDialog.setOnCancelButtonClickListener(onDialogButtonClickListener2);
        this.mConfirmDialog.showAllowingStateLoss(getSupportFragmentManager());
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() != 4) {
            return super.dispatchKeyEvent(keyEvent);
        }
        AppUtils.exitApp();
        return false;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        supportRtl();
        ((MainPresenter) this.mPresenter).dealOnResume();
        int i = 0;
        if (!SelfChassis.getInstance().isConnect()) {
            WifiSwitchBroadcastReceiver wifiSwitchBroadcastReceiver2 = this.wifiSwitchBroadcastReceiver;
            if (wifiSwitchBroadcastReceiver2 != null) {
                wifiSwitchBroadcastReceiver2.onDestroy();
                this.wifiSwitchBroadcastReceiver = null;
            }
            WifiSwitchBroadcastReceiver wifiSwitchBroadcastReceiver3 = new WifiSwitchBroadcastReceiver(this, this);
            this.wifiSwitchBroadcastReceiver = wifiSwitchBroadcastReceiver3;
            wifiSwitchBroadcastReceiver3.observeWifiSwitch();
            App.setIsMultiNaving(false);
            App.setIsSingleNaving(false);
        }
        AppCompatRadioButton appCompatRadioButton = this.mainBinding.includeNav.navTypeMultiRb;
        if (MySpUtils.getInstance().getInt(SpConstant.NAV_MODE, 0) == 1) {
            i = 4;
        }
        appCompatRadioButton.setVisibility(i);
    }

    public void wifiSwitchState(int i) {
        if (i == 0) {
            LiveDatabus.getInstance().with(DTLiveDataConstant.MAIN_CONNECT_DIALOG_EVENT).postValue("closeConnectedDialog");
        } else if (i == 1 && !SelfChassis.getInstance().isConnect()) {
            LiveDatabus.getInstance().with(DTLiveDataConstant.MAIN_CONNECT_DIALOG_EVENT).postValue("showConnectedDialog");
        }
    }

    private void showNotConnectedWifiDialog() {
        AdaptScreenUtils.adaptWidth(super.getResources(), 1920);
        if (this.mNotConnectedWifiDialog == null) {
            NotConnectedWifiDialog newInstance = NotConnectedWifiDialog.newInstance();
            this.mNotConnectedWifiDialog = newInstance;
            if (!newInstance.isAdded() && !this.mNotConnectedWifiDialog.isShow() && !SelfChassis.getInstance().isConnect()) {
                this.mNotConnectedWifiDialog.showAllowingStateLoss(getSupportFragmentManager(), "notConnectedWifiDialog");
            }
        }
        closeConfirmDialog();
    }

    private void closeConnectedDialog() {
        WeakReference<ConnectedDialog> weakReference = this.mConnectedDialog;
        if (weakReference != null && weakReference.get() != null && ((ConnectedDialog) this.mConnectedDialog.get()).isAdded() && ((ConnectedDialog) this.mConnectedDialog.get()).isShow()) {
            ((ConnectedDialog) this.mConnectedDialog.get()).dismissAllowingStateLoss();
        }
        this.mConnectedDialog = null;
    }

    public void showConnectedDialog() {
        AdaptScreenUtils.adaptWidth(super.getResources(), 1920);
        closeNotConnectedWifiDialog();
        if (SelfChassis.getInstance().isConnect()) {
            closeConnectedDialog();
            return;
        }
        WeakReference<ConnectedDialog> weakReference = this.mConnectedDialog;
        if (weakReference == null || weakReference.get() == null) {
            this.mConnectedDialog = new WeakReference<>(ConnectedDialog.newInstance());
        }
        if (!((ConnectedDialog) this.mConnectedDialog.get()).isAdded()) {
            ((ConnectedDialog) this.mConnectedDialog.get()).showAllowingStateLoss(getSupportFragmentManager(), "connectedDialog");
        }
    }

    private void closeNotConnectedWifiDialog() {
        NotConnectedWifiDialog notConnectedWifiDialog = this.mNotConnectedWifiDialog;
        if (notConnectedWifiDialog != null && notConnectedWifiDialog.isAdded()) {
            this.mNotConnectedWifiDialog.dismissAllowingStateLoss();
        }
        this.mNotConnectedWifiDialog = null;
    }

    public void showConfirmDialog(String str, String str2, ConfirmDialog.OnDialogButtonClickListener onDialogButtonClickListener) {
        showConfirmDialog(str, str2, ContextUtil.getContext().getString(R.string.dialog_confirm), (String) null, onDialogButtonClickListener, (ConfirmDialog.OnDialogButtonClickListener) null);
    }

    public void showConfirmDialog(String str, String str2, ConfirmDialog.OnDialogButtonClickListener onDialogButtonClickListener, ConfirmDialog.OnDialogButtonClickListener onDialogButtonClickListener2) {
        showConfirmDialog(str, str2, ContextUtil.getContext().getString(R.string.dialog_confirm), ContextUtil.getContext().getString(R.string.dialog_cancle), onDialogButtonClickListener, onDialogButtonClickListener2);
    }

    public void showConfirmDialog(String str, String str2, String str3, String str4, ConfirmDialog.OnDialogButtonClickListener onDialogButtonClickListener, ConfirmDialog.OnDialogButtonClickListener onDialogButtonClickListener2) {
        ConfirmDialogLiveDataEvent confirmDialogLiveDataEvent = new ConfirmDialogLiveDataEvent();
        confirmDialogLiveDataEvent.setTitle(str);
        confirmDialogLiveDataEvent.setOkStrBtn(str3);
        confirmDialogLiveDataEvent.setCancelStrBtn(str4);
        confirmDialogLiveDataEvent.setMessage(str2);
        confirmDialogLiveDataEvent.setOnOkButtonClickListener(onDialogButtonClickListener);
        confirmDialogLiveDataEvent.setOnCancelButtonClickListener(onDialogButtonClickListener2);
        LiveDatabus.getInstance().with(DTLiveDataConstant.MAIN_CONFIRM_DIALOG_EVENT).postValue(confirmDialogLiveDataEvent);
    }

    public void closeConfirmDialog() {
        runOnUiThread(new Runnable() {
            public final void run() {
                MainActivity.this.lambda$closeConfirmDialog$24$MainActivity();
            }
        });
    }

    public /* synthetic */ void lambda$closeConfirmDialog$24$MainActivity() {
        ConfirmDialog confirmDialog = this.mConfirmDialog;
        if (confirmDialog != null && confirmDialog.isAdded()) {
            this.mConfirmDialog.dismissAllowingStateLoss();
        }
    }

    public void refreshAreaView(List<AreaItemBean> list) {
        runOnUiThread(new Runnable(list) {
            public final /* synthetic */ List f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                MainActivity.this.lambda$refreshAreaView$25$MainActivity(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$refreshAreaView$25$MainActivity(List list) {
        String str = this.TAG;
        MyLogUtils.Logd(str, "area areas:" + list);
        this.mAreaItemBeanList = list;
        this.mAreaAdapter.refreshData(list, this.mLeftToolType);
        this.mainBinding.mainMapControlMrv.setAreaItemBeans(list);
    }

    class MyDialogClickListener implements NormalDialog.OnDialogListener, ChooseMarkerDialog.OnChooseMarkerListener, SwitchPointDialog.OnSwitchPointSelectListener {
        MyDialogClickListener() {
        }

        public void isPositiveBtClick(boolean z) {
            if (z) {
                SoftTypeInfoManager.getInstance().setSoftType(3);
            } else {
                showChooseMarkerDialog();
            }
        }

        public void onChooseMarkerListener(MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean waypointsBean) {
            if (waypointsBean != null) {
                MyToastUtils.showShort(ContextUtil.getContext(), waypointsBean.getName());
                ((MainPresenter) MainActivity.this.mPresenter).placeCalibration(waypointsBean.getName());
            }
        }

        private void showChooseMarkerDialog() {
            ChooseMarkerDialog newInstance = ChooseMarkerDialog.newInstance();
            newInstance.setOnChooseMarkerListener(new MyDialogClickListener());
            newInstance.showAllowingStateLoss(MainActivity.this.getSupportFragmentManager());
        }

        public void onSelect(CrossFloorNaviReqBean.MsgBean msgBean) {
            if (App.isScram) {
                ConfirmDialog.buildDefault(MainActivity.this.getString(R.string.across_floor_navigation), MainActivity.this.getString(R.string.robot_emergency_stop)).showAllowingStateLoss(MainActivity.this.getSupportFragmentManager());
            } else {
                ((MainPresenter) MainActivity.this.mPresenter).crossFloorNavToPoint(msgBean, MainActivity.this.getSupportFragmentManager());
            }
        }
    }

    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        MyOnCheckedChangeListener() {
        }

        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if (R.id.nav_type_single_rb == i) {
                MainActivity.this.changeNavType(0);
                MainActivity mainActivity = MainActivity.this;
                mainActivity.showTipsBlack(mainActivity.getString(R.string.tips_nav_normal));
            } else if (R.id.nav_type_multi_rb == i) {
                MainActivity.this.changeNavType(1);
                MainActivity mainActivity2 = MainActivity.this;
                mainActivity2.showTipsBlack(mainActivity2.getString(R.string.txt_nav_multi_tips));
            }
        }
    }

    class MyOnClickListener extends ClickUtils.OnDebouncingClickListener {
        MyOnClickListener() {
        }

        public void onDebouncingClick(View view) {
            int id = view.getId();
            if (id == R.id.head_create_map_ib) {
                ((MainPresenter) MainActivity.this.mPresenter).showTopMenu();
            } else if (id == R.id.head_cancel_ib) {
                ((MainPresenter) MainActivity.this.mPresenter).exitMode();
            } else if (id == R.id.head_building_cl) {
                ((MainPresenter) MainActivity.this.mPresenter).changeBuilding();
            } else if (id == R.id.main_build_info_cl) {
                ((MainPresenter) MainActivity.this.mPresenter).buildManage();
            } else if (id == R.id.main_point_info_cl) {
                ((MainPresenter) MainActivity.this.mPresenter).pointManage();
            } else if (id == R.id.cl_robot_list) {
                MainActivity.this.switchRobotListShow();
            } else if (id == R.id.main_line_iv) {
                ((MainPresenter) MainActivity.this.mPresenter).selectLine();
            } else if (id == R.id.main_rectangle_iv) {
                ((MainPresenter) MainActivity.this.mPresenter).selectRectangle();
            } else if (id == R.id.main_brush_small_rl) {
                ((MainPresenter) MainActivity.this.mPresenter).selectSmallBrush();
            } else if (id == R.id.main_brush_medium_rl) {
                ((MainPresenter) MainActivity.this.mPresenter).selectMediumBrush();
            } else if (id == R.id.main_brush_big_rl) {
                ((MainPresenter) MainActivity.this.mPresenter).selectBigBrush();
            } else if (id == R.id.main_zoom_in_tv) {
                MainActivity.this.mainBinding.mainMapControlMrv.zoomIn();
                ((MainPresenter) MainActivity.this.mPresenter).zoomIn();
            } else if (id == R.id.main_zoom_out_tv) {
                MainActivity.this.mainBinding.mainMapControlMrv.zoomOut();
                ((MainPresenter) MainActivity.this.mPresenter).zoomOut();
            } else if (id == R.id.main_bottom_restore_iv) {
                MainActivity.this.mainBinding.mainMapControlMrv.restore();
            } else if (id == R.id.main_bottom_nav_iv) {
                ((MainPresenter) MainActivity.this.mPresenter).changeSoftTypeNav();
            } else if (id == R.id.main_bottom_stop_iv) {
                ((MainPresenter) MainActivity.this.mPresenter).softStop();
            } else if (id == R.id.main_right_btn_ok_ib) {
                ((MainPresenter) MainActivity.this.mPresenter).rightBtnSave();
                MainActivity.this.mainBinding.mainMapControlMrv.setTrajectoryDrawingPath((ArrayList<PointBean>) null);
            } else if (id == R.id.main_right_btn_lock_ib) {
                ((MainPresenter) MainActivity.this.mPresenter).rightTopBtnLock();
            } else if (id == R.id.main_right_btn_cancel_nav_ib) {
                ((MainPresenter) MainActivity.this.mPresenter).rightTopBtnCancelNav();
            } else if (id == R.id.main_bottom_lock_iv) {
                ((MainPresenter) MainActivity.this.mPresenter).bottomLock();
            } else if (id == R.id.right_area_list_title_cl) {
                MainActivity.this.showChangeAreaList();
            } else if (id == R.id.main_left_clean_draw_area_iv) {
                MainActivity mainActivity = MainActivity.this;
                mainActivity.showConfirmDialog(mainActivity.getString(R.string.txt_clear_point), MainActivity.this.getString(R.string.txt_confirm_to_clear_point), new ConfirmDialog.OnDialogButtonClickListener() {
                    public final boolean onClick(View view) {
                        return MainActivity.MyOnClickListener.this.lambda$onDebouncingClick$0$MainActivity$MyOnClickListener(view);
                    }
                }, (ConfirmDialog.OnDialogButtonClickListener) null);
            } else if (id == R.id.label_info_cl) {
                MainActivity.this.showVisualLabelView();
            } else if (id == R.id.nav_multi_title_cl) {
                MainActivity.this.showMultiPointSelect();
            } else if (id == R.id.nav_start_nav_cl) {
                MainActivity.this.startPatrol();
            } else if (id == R.id.nav_multi_clear_btn) {
                MainActivity.this.refreshPatrolPointAdapter((List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean>) null);
                MainActivity.this.showPatrolRouteView();
            } else if (id == R.id.nav_type_single_rb) {
                ((MainPresenter) MainActivity.this.mPresenter).setRightTopBtnCancelNav(false);
            } else if (id == R.id.nav_type_multi_rb) {
                ((MainPresenter) MainActivity.this.mPresenter).setRightTopBtnCancelNav(false);
            } else if (id == R.id.main_bottom_layer_iv) {
                ((MainPresenter) MainActivity.this.mPresenter).showCoverage();
            }
        }

        public /* synthetic */ boolean lambda$onDebouncingClick$0$MainActivity$MyOnClickListener(View view) {
            MainActivity.this.clearUnfinishedArea();
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void switchRobotListShow() {
        if (this.mainBinding.llRobotList.getVisibility() == 0) {
            this.mainBinding.llRobotList.setVisibility(8);
        } else {
            this.mainBinding.llRobotList.setVisibility(0);
        }
    }

    public void clearUnfinishedArea() {
        ThreadUtils.runOnUiThread(new Runnable() {
            public final void run() {
                MainActivity.this.lambda$clearUnfinishedArea$26$MainActivity();
            }
        });
    }

    public /* synthetic */ void lambda$clearUnfinishedArea$26$MainActivity() {
        this.mainBinding.mainMapControlMrv.clearUnfinishedArea();
        this.mIsAddingArea = false;
        this.mainBinding.mainLeftCleanDrawAreaIv.setVisibility(8);
        showTipsBlack(String.format(getString(R.string.tips_edit_map_draw), new Object[]{this.mLeftToolTypeDesc}));
    }

    public List<GlobalLocateReqBean.ArgsBean.SearchBoundaryBean.PolygonBean.PointsBean> getPositioningRectPoints() {
        return this.mainBinding.mainMapControlMrv.getRectPoints();
    }

    public void refreshVisualLabelData(List<ApriltagsBufferBean.MsgBean.WaypointsBean> list) {
        runOnUiThread(new Runnable(list) {
            public final /* synthetic */ List f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                MainActivity.this.lambda$refreshVisualLabelData$27$MainActivity(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$refreshVisualLabelData$27$MainActivity(List list) {
        this.mTagWaypoints = list;
        this.mVisualLabelAdapter.refreshData(list);
        this.mainBinding.mainMapControlMrv.setVisualLabelPoint(list);
        refreshPointAdapter();
    }

    public void refreshCurrentVisualLabelData(List<String> list) {
        this.mVisualLabelAdapter.refreshCurrentTagPoints(list);
    }

    public void showVisualLabelView() {
        if (this.mainBinding.includeVisualLabel.labelListRv.getVisibility() == 0) {
            this.mainBinding.includeVisualLabel.labelInfoIv.setBackgroundResource(R.mipmap.right_arrow);
            this.mainBinding.includeVisualLabel.labelListRv.setVisibility(8);
            return;
        }
        this.mainBinding.includeVisualLabel.labelInfoIv.setBackgroundResource(R.mipmap.down_arrow);
        this.mainBinding.includeVisualLabel.labelListRv.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public void showMultiPointSelect() {
        if (this.mainBinding.includeNav.navMultiListRv.getVisibility() == 0) {
            this.mainBinding.includeNav.navMultiMoreIv.setBackgroundResource(R.mipmap.right_arrow);
            this.mainBinding.includeNav.navMultiListRv.setVisibility(8);
            return;
        }
        this.mainBinding.includeNav.navMultiMoreIv.setBackgroundResource(R.mipmap.down_arrow);
        this.mainBinding.includeNav.navMultiListRv.setVisibility(0);
    }

    public void refreshRightToolBarAdapter(boolean z) {
        runOnUiThread(new Runnable() {
            public final void run() {
                MainActivity.this.lambda$refreshRightToolBarAdapter$28$MainActivity();
            }
        });
    }

    public /* synthetic */ void lambda$refreshRightToolBarAdapter$28$MainActivity() {
        List<ToolbarBean> initRightToolbar = ((MainPresenter) this.mPresenter).initRightToolbar(true);
        this.mRightToolbarBeans = initRightToolbar;
        this.mRightToolBarAdapter.refreshData(initRightToolbar);
    }

    public void resetData() {
        ThreadUtils.runOnUiThread(new Runnable() {
            public final void run() {
                MainActivity.this.lambda$resetData$29$MainActivity();
            }
        });
    }

    public /* synthetic */ void lambda$resetData$29$MainActivity() {
        try {
            if (this.mWaypoints != null) {
                this.mWaypoints.clear();
            } else {
                this.mWaypoints = new ArrayList();
            }
            if (this.mTagWaypoints != null) {
                this.mTagWaypoints.clear();
            } else {
                this.mTagWaypoints = new ArrayList();
            }
            refreshPointAdapter();
            this.mVisualLabelAdapter.refreshData(this.mTagWaypoints);
            this.mainBinding.mainMapControlMrv.setVisualLabelPoint(this.mTagWaypoints);
            if (this.mAreaItemBeanList != null) {
                this.mAreaItemBeanList.clear();
            } else {
                this.mAreaItemBeanList = new ArrayList();
            }
            this.mAreaAdapter.refreshData(this.mAreaItemBeanList, this.mLeftToolType);
            this.mainBinding.mainMapControlMrv.setAreaItemBeans(this.mAreaItemBeanList);
            this.mainBinding.mainMapControlMrv.setVirtualWall((List<VirtualWallOperationGetWallsResponseBean.ValuesBean.VwallsBean.WallsBean>) null);
            refreshPatrolPointAdapter((List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean>) null);
            this.mainBinding.mainMapControlMrv.setPose((PointBean) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showCameraPointCloudView() {
        if (!(LayerDataUtils.getInstance().getShowPointCloudUpCam() || LayerDataUtils.getInstance().getShowPointCloudDownCam()) || SoftTypeInfoManager.getInstance().getSoftType() != 0) {
            this.mainBinding.mainClCamPointCloud.setVisibility(8);
        } else {
            this.mainBinding.mainClCamPointCloud.setVisibility(0);
        }
    }

    public void showContinueScanConfirmDialog(ContinueScanConfirmDialog.OnDialogButtonClickListener onDialogButtonClickListener) {
        ContinueScanConfirmDialog newInstance = ContinueScanConfirmDialog.newInstance();
        this.mContinueScanConfirmDialog = newInstance;
        newInstance.setOnOkButtonClickListener(onDialogButtonClickListener);
        this.mContinueScanConfirmDialog.showAllowingStateLoss(getSupportFragmentManager());
    }

    public void refreshPointAdapter() {
        ArrayList arrayList = new ArrayList();
        List<ApriltagsBufferBean.MsgBean.WaypointsBean> list = this.mTagWaypoints;
        if (list != null && list.size() > 0) {
            arrayList.addAll((List) GsonUtils.fromLocalJson(GsonUtils.toJson(this.mTagWaypoints), new TypeToken<List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean>>() {
            }.getType()));
        }
        List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean> list2 = this.mWaypoints;
        if (list2 != null && list2.size() > 0) {
            arrayList.addAll(this.mWaypoints);
        }
        this.pointAdapter.refreshData(arrayList);
        this.trajectoryPointAdapter.refreshData(arrayList);
    }

    public void startPatrol() {
        if (TextUtils.equals(this.mainBinding.includeNav.navStartTv.getText(), getResources().getString(R.string.txt_start_nav))) {
            String obj = this.mainBinding.includeNav.navMultiTimesEt.getText() != null ? this.mainBinding.includeNav.navMultiTimesEt.getText().toString() : "";
            if (TextUtils.isEmpty(obj)) {
                ToastUtil.showShort(this, getResources().getString(R.string.txt_nav_times_check));
            } else if (Integer.parseInt(obj) == 0) {
                ToastUtil.showShort(this, getResources().getString(R.string.txt_nav_times_check2));
            } else {
                List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean> list = this.mPatrolRoutes;
                if (list == null || list.size() == 0) {
                    ToastUtil.showShort(this, getResources().getString(R.string.txt_nav_times_check3));
                } else if (new ArrayList(new HashSet(this.mPatrolRoutes)).size() == 1) {
                    ToastUtil.showShort(this, getResources().getString(R.string.txt_nav_times_check3));
                } else {
                    int i = this.mainBinding.includeNav.radioGroup.getCheckedRadioButtonId() == R.id.nav_multi_back_rb ? 3 : 2;
                    if (App.isScram) {
                        ConfirmDialog.buildDefault(getString(R.string.point_navigation), getString(R.string.robot_emergency_stop)).showAllowingStateLoss(getSupportFragmentManager());
                        return;
                    }
                    ((MainPresenter) this.mPresenter).startPoiPatrol(Integer.parseInt(obj), i, this.mPatrolRoutes);
                    ((MainPresenter) this.mPresenter).setRightTopBtnCancelNav(false);
                }
            }
        } else {
            if (this.mainBinding.includeNav.radioGroup.getCheckedRadioButtonId() == R.id.nav_multi_single_rb) {
                this.mainBinding.includeNav.navMultiTimesEt.setText("1");
            } else {
                this.mainBinding.includeNav.navMultiTimesEt.setText("3");
            }
            ((MainPresenter) this.mPresenter).stopPoiPatrol();
        }
    }

    public void refreshPatrolPointAdapter(List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean> list) {
        runOnUiThread(new Runnable(list) {
            public final /* synthetic */ List f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                MainActivity.this.lambda$refreshPatrolPointAdapter$30$MainActivity(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$refreshPatrolPointAdapter$30$MainActivity(List list) {
        if (list != null) {
            this.mPatrolRoutes.clear();
            this.mPatrolRoutes.addAll(list);
            this.mPatrolRouteAdapter.notifyDataSetChanged();
        } else {
            this.mPatrolRoutes.clear();
            MySpUtils.getInstance().putString(DeploymentToolSpConstant.MULTIPOINT_NAV_POINTS_LIST, "");
            this.mPatrolRouteAdapter.notifyDataSetChanged();
        }
        showPatrolRouteView();
        MyLogUtils.Logd(this.TAG, "selectPatrolRoute：恢复列表");
    }

    public void changePatrollingView(boolean z) {
        runOnUiThread(new Runnable(z) {
            public final /* synthetic */ boolean f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                MainActivity.this.lambda$changePatrollingView$31$MainActivity(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$changePatrollingView$31$MainActivity(boolean z) {
        App.setIsMultiNaving(z);
        AppCompatTextView appCompatTextView = this.mainBinding.includeNav.navStartTv;
        if (z) {
            this.mPatrolRouteAdapter.setAdapterCanClick(false);
            changeView(this.mainBinding.includeNav.navTypeRg, false);
            changeView(this.mainBinding.includeNav.navMultiListLl, false);
            this.mainBinding.includeNav.navMultiLineTitleTv.setVisibility(8);
            this.mainBinding.includeNav.navMultiLineTip.setVisibility(8);
            appCompatTextView.setText(getResources().getString(R.string.exit_nav));
            appCompatTextView.setTextColor(ColorUtils.getColor(R.color.clr_0560FD));
            this.mainBinding.includeNav.navStartIconIv.setBackgroundResource(R.mipmap.icon_x);
            this.mainBinding.includeNav.navStartNavCl.setBackgroundResource(R.drawable.shape_rectangle_white_5);
            this.mainBinding.includeNav.navMultiTimesTitleTv.setText(getResources().getString(R.string.txt_remaining_times));
            this.mainBinding.includeNav.navMultiTimesEt.setTextAppearance(this, R.style.nav_times_show);
            this.mainBinding.includeNav.navMultiTimesEt.setBackground((Drawable) null);
            this.mainBinding.includeNav.navMultiTimesEt.setEnabled(false);
            this.mainBinding.includeNav.radioGroup.setEnabled(false);
            changeView(this.mainBinding.includeNav.radioGroup, false);
        } else {
            this.mPatrolRouteAdapter.setAdapterCanClick(true);
            changeView(this.mainBinding.includeNav.navTypeRg, true);
            changeView(this.mainBinding.includeNav.navMultiListLl, true);
            this.mainBinding.includeNav.navMultiLineTitleTv.setVisibility(0);
            this.mainBinding.includeNav.navMultiLineTip.setVisibility(0);
            appCompatTextView.setText(getResources().getString(R.string.txt_start_nav));
            appCompatTextView.setTextColor(ColorUtils.getColor(R.color.white));
            this.mainBinding.includeNav.navStartIconIv.setBackgroundResource(R.mipmap.right_white);
            this.mainBinding.includeNav.navStartNavCl.setBackgroundResource(R.drawable.shape_rectangle_blue_5);
            this.mainBinding.includeNav.navMultiTimesTitleTv.setText(getResources().getString(R.string.txt_nav_times));
            this.mainBinding.includeNav.navMultiTimesEt.setTextAppearance(this, R.style.nav_times_edit);
            this.mainBinding.includeNav.navMultiTimesEt.setBackgroundResource(R.drawable.selector_nav_bg_type);
            this.mainBinding.includeNav.navMultiTimesEt.setEnabled(true);
            this.mainBinding.includeNav.radioGroup.setEnabled(true);
            changeView(this.mainBinding.includeNav.radioGroup, true);
        }
        changeView(this.mainBinding.includeNav.navStartNavCl, true);
    }

    private void changeView(ViewGroup viewGroup, boolean z) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if (z) {
                childAt.setEnabled(true);
                childAt.setAlpha(1.0f);
            } else {
                childAt.setEnabled(false);
                childAt.setAlpha(0.5f);
            }
        }
    }

    /* access modifiers changed from: private */
    public void showChangeAreaList() {
        if (this.mainBinding.rightAreaListCl.getVisibility() == 0) {
            this.mainBinding.rightAreaArrowIv.setBackgroundResource(R.mipmap.right_arrow);
            this.mainBinding.rightAreaListCl.setVisibility(8);
            return;
        }
        this.mainBinding.rightAreaArrowIv.setBackgroundResource(R.mipmap.down_arrow);
        this.mainBinding.rightAreaListCl.setVisibility(0);
    }

    public boolean isShowChangeBuildingView() {
        return this.mainBinding.mainBuildFloorLl.getVisibility() == 0;
    }

    public void showChangeBuildingView(boolean z) {
        if (z) {
            this.mainBinding.mainBuildFloorLl.setVisibility(0);
            this.mainBinding.mainBuildNameRv.setVisibility(0);
            this.mainBinding.mainFloorRv.setVisibility(8);
            this.mainBinding.includeHead.headBuildingFloorIv.setBackground(getDrawable(R.mipmap.down_arrow));
            return;
        }
        this.mainBinding.mainBuildFloorLl.setVisibility(8);
        this.mainBinding.includeHead.headBuildingFloorIv.setBackground(getDrawable(R.mipmap.right_arrow));
    }

    public void showTrajectoryDrawingView(boolean z) {
        this.mainBinding.includeTracking.getRoot().setVisibility(z ? 0 : 8);
        this.mainBinding.includeTracking.startDrawingView.endDrawing();
        TrajectoryModel.getInstance().setIsInDrawing(false);
        if (!z || !this.mainBinding.includeTracking.lineTrackingSettingView.isChecked()) {
            FloatWindow.get().hide();
        } else {
            FloatWindow.get().show();
        }
    }

    public void showCollectModeView(boolean z) {
        SelfChassis.getInstance().stopBagRecord();
        this.mainBinding.includeCollecting.getRoot().setVisibility(z ? 0 : 8);
        this.mainBinding.includeCollecting.startCollectingView.endDrawing();
    }

    public void showAddRandomPointView(boolean z) {
        int i = 8;
        this.mainBinding.addRandomMarkView.setVisibility(8);
        ConstraintLayout constraintLayout = this.mainBinding.mainPointInfoCl;
        if (z) {
            i = 0;
        }
        constraintLayout.setVisibility(i);
        showPointManageView(z);
    }

    public void showVisualLabelCalibrationView(boolean z) {
        if (!z || !this.mainBinding.includeVisualLabel.vlcmSwitchLabelCameraWindow.isChecked()) {
            FloatWindow.get().hide();
        } else {
            FloatWindow.get().show();
        }
    }

    public boolean isShowBuildPointManageView() {
        return this.mainBinding.mainBuildInfoListRv.getVisibility() == 0;
    }

    public void showRocker(boolean z) {
        if (!z || !MySpUtils.getInstance().getBoolean(SpConstant.ROCKER_MODE, true)) {
            this.mainBinding.includeDirectionControl.mainDirectionControl.setVisibility(8);
            return;
        }
        if (MySpUtils.getInstance().getInt(SpConstant.KEY_MODE, 1) == 0) {
            this.mainBinding.includeDirectionControl.mainDirectionFourRv.setVisibility(0);
            this.mainBinding.includeDirectionControl.mainDirectionAllRv.setVisibility(8);
        } else {
            this.mainBinding.includeDirectionControl.mainDirectionFourRv.setVisibility(8);
            this.mainBinding.includeDirectionControl.mainDirectionAllRv.setVisibility(0);
        }
        this.mainBinding.includeDirectionControl.mainDirectionControl.setVisibility(0);
    }

    public void showBuildMapDialog() {
        DialogHelper.showBuildMapDialog(this, this.buildList);
    }

    public void showAddMarkDialog() {
        String x = ShowSelfChassisBean.getInstance().getX();
        String y = ShowSelfChassisBean.getInstance().getY();
        String t = ShowSelfChassisBean.getInstance().getT();
        this.mainBinding.addRandomMarkView.setOnDialogButtonOnClickListener(new AddMarkModeView.OnDialogButtonOnClickListener() {
            public void cancelBtn() {
            }

            public boolean okBtn(int i, String str, float f, float f2, float f3) {
                return !DialogHelper.checkMarkerExsit(str, MainActivity.this.mWaypoints);
            }
        });
        AddMarkModeView addMarkModeView = this.mainBinding.addRandomMarkView;
        float f = 0.0f;
        float parseFloat = TextUtils.isEmpty(x) ? 0.0f : Float.parseFloat(x);
        float parseFloat2 = TextUtils.isEmpty(y) ? 0.0f : Float.parseFloat(y);
        if (!TextUtils.isEmpty(t)) {
            f = Float.parseFloat(t);
        }
        addMarkModeView.setData(parseFloat, parseFloat2, AddMarkModeView.chassisToApp(f));
        this.mainBinding.addRandomMarkView.setVisibility(0);
        this.mainBinding.mainMapControlMrv.mMapInfoLlpv.setShowAddRandomPoint(true);
    }

    public void showLineTrackingAddMarkDialog(AddMarkDialog.OnInsertMarkerListener onInsertMarkerListener) {
        DialogHelper.showAddMarkDialog(this, this.mWaypoints, onInsertMarkerListener);
    }

    public void removeItemOnVisualLabelAdapter(String str) {
        runOnUiThread(new Runnable(str) {
            public final /* synthetic */ String f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                MainActivity.this.lambda$removeItemOnVisualLabelAdapter$32$MainActivity(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$removeItemOnVisualLabelAdapter$32$MainActivity(String str) {
        VisualLabelAdapter visualLabelAdapter = this.mVisualLabelAdapter;
        if (visualLabelAdapter != null) {
            visualLabelAdapter.remove(str);
        }
    }

    public void showFloorRv(LayeredMapCmdResponseBean.ValuesBean.ListInfoBean listInfoBean) {
        if (this.mainBinding.mainFloorRv.getVisibility() == 8) {
            this.mainBinding.mainFloorRv.setVisibility(0);
        }
        this.floorAdapter.refreshData(listInfoBean);
    }

    public void showSwitchPointDialog() {
        SwitchPointDialog newInstance = SwitchPointDialog.newInstance();
        newInstance.setSwitchPointSelectListener(new MyDialogClickListener());
        newInstance.showAllowingStateLoss(getSupportFragmentManager());
    }

    public void showWaitDialog(String str) {
        runOnUiThread(new Runnable(str) {
            public final /* synthetic */ String f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                MainActivity.this.lambda$showWaitDialog$33$MainActivity(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$showWaitDialog$33$MainActivity(String str) {
        try {
            if (this.mLoadingDialog == null) {
                this.mLoadingDialog = LoadingDialog.newInstance();
            }
            getSupportFragmentManager().beginTransaction().remove(this.mLoadingDialog).commitAllowingStateLoss();
            if (!this.mLoadingDialog.isAdded()) {
                this.mLoadingDialog.setLoadingText(str);
                this.mLoadingDialog.showAllowingStateLoss(getSupportFragmentManager(), "MyShowLoadingDialog");
                MyLogUtils.Logd(this.TAG, "showWaitDialog");
            }
        } catch (Exception e) {
            String str2 = this.TAG;
            MyLogUtils.Loge(str2, "showWaitDialog error:" + Log.getStackTraceString(e));
        }
    }

    private void printStack() {
        try {
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            if (stackTrace.length >= 1) {
                for (int i = 1; i < stackTrace.length; i++) {
                    LogUtils.dTag("t1ang", "File:" + stackTrace[i].getFileName() + ", Line: " + stackTrace[i].getLineNumber() + ", MethodName:" + stackTrace[i].getMethodName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeWaitDialog() {
        runOnUiThread(new Runnable() {
            public final void run() {
                MainActivity.this.lambda$closeWaitDialog$34$MainActivity();
            }
        });
    }

    public /* synthetic */ void lambda$closeWaitDialog$34$MainActivity() {
        LoadingDialog loadingDialog = this.mLoadingDialog;
        if (loadingDialog != null && loadingDialog.isAdded()) {
            this.mLoadingDialog.dismissAllowingStateLoss();
            MyLogUtils.Logd(this.TAG, "closeWaitDialog");
            this.mLoadingDialog = null;
        }
    }

    public void refreshMap(MapInfoBean mapInfoBean, Bitmap bitmap) {
        ThreadUtils.runOnUiThread(new Runnable(mapInfoBean, bitmap) {
            public final /* synthetic */ MapInfoBean f$1;
            public final /* synthetic */ Bitmap f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                MainActivity.this.lambda$refreshMap$35$MainActivity(this.f$1, this.f$2);
            }
        });
    }

    public /* synthetic */ void lambda$refreshMap$35$MainActivity(MapInfoBean mapInfoBean, Bitmap bitmap) {
        MyLogUtils.Logd(this.TAG, "refreshMap======>");
        this.mainBinding.mainMapControlMrv.setMap(mapInfoBean, bitmap);
    }

    public void showSoftTypeView(int i) {
        runOnUiThread(new Runnable(i) {
            public final /* synthetic */ int f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                MainActivity.this.lambda$showSoftTypeView$36$MainActivity(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$showSoftTypeView$36$MainActivity(int i) {
        showCameraPointCloudView();
        this.mainBinding.mainLayerView.refreshData();
        this.mainBinding.mainMapControlMrv.changeSoftType(i);
        this.mainBinding.mainBuildFloorLl.setVisibility(8);
        String str = this.TAG;
        MyLogUtils.Logd(str, "showSoftTypeView==>softType:" + i);
        showTrajectoryDrawingView(false);
        showCollectModeView(false);
        showAddRandomPointView(false);
        showVisualLabelCalibrationView(false);
        this.mainBinding.mainMapControlMrv.setHighLightIndex(-1);
        this.mainBinding.mainMapControlMrv.mMapInfoLlpv.setShowAddRandomPoint(false);
        showRobotListView(false);
        switch (i) {
            case 0:
                showCommonView();
                this.mainBinding.includeNav.navRootCl.setVisibility(8);
                this.mainBinding.includeHead.headCreateMapIb.setVisibility(0);
                this.mainBinding.includeHead.headCancelIb.setVisibility(8);
                this.mainBinding.includeHead.headBuildingCl.setVisibility(0);
                showRocker(true);
                this.mainBinding.mainBuildInfoCl.setVisibility(0);
                showBuildManageView(false);
                this.mainBinding.mainPointInfoCl.setVisibility(0);
                showPointManageView(false);
                this.mainBinding.mainPointTypeCl.setVisibility(0);
                this.mainBinding.mainLeftToolRv.setVisibility(8);
                selectLeftToolBtn("layer_normal");
                this.mainBinding.mainRightToolRl.setVisibility(0);
                selectRightToolBtn(0);
                this.mainBinding.mainRightBtnLockIb.setVisibility(8);
                this.mainBinding.mainRightBtnCancelNavIb.setVisibility(8);
                this.mainBinding.mainRightBtnOkIb.setVisibility(8);
                this.mainBinding.mainBottomBtnCl.setVisibility(0);
                this.mainBinding.mainBottomNavIv.setVisibility(0);
                this.mainBinding.mainBottomLockIv.setVisibility(0);
                this.mainBinding.mainBottomStopIv.setVisibility(0);
                this.mainBinding.mainBottomRestoreIv.setVisibility(0);
                this.mainBinding.mainBottomLockIv.setBackgroundResource(R.mipmap.icon_lock);
                this.mainBinding.mainZoomCl.setVisibility(8);
                showRobotListView(true);
                hiddenTips();
                return;
            case 1:
                showCommonView();
                this.mainBinding.mainBottomBtnCl.setVisibility(0);
                this.mainBinding.mainBottomStopIv.setVisibility(0);
                this.mainBinding.includeHead.headCancelIb.setBtnText(getString(R.string.exit_nav));
                this.mainBinding.mainRightBtnOkIb.setVisibility(8);
                this.mainBinding.includeNav.navRootCl.setVisibility(0);
                this.mainBinding.mainRightBtnLockIb.setVisibility(0);
                this.mainBinding.mainRightBtnCancelNavIb.setVisibility(0);
                showTipsBlack(getString(R.string.tips_nav_normal));
                changeNavType(App.getNavType());
                return;
            case 2:
                showCommonView();
                showRocker(true);
                this.mainBinding.includeHead.headCancelIb.setBtnText(getString(R.string.cancel_scan_map));
                this.mainBinding.mainRightBtnOkIb.setBtnText(getString(R.string.finish_scan_map));
                this.mainBinding.mainRightBtnOkIb.setBtnIcon(R.mipmap.icon_btn_ok);
                this.mainBinding.mainBottomLayerIv.setVisibility(8);
                showTipsBlack(getString(R.string.tips_scan_map_normal));
                return;
            case 3:
                showCommonView();
                this.mainBinding.includeHead.headCancelIb.setBtnText(getString(R.string.exit_calibration));
                this.mainBinding.mainRightBtnOkIb.setBtnText(getString(R.string.finish_calibration));
                this.mainBinding.mainRightBtnOkIb.setBtnIcon(R.mipmap.icon_btn_ok);
                showTipsBlack(getString(R.string.tips_calibration_normal));
                return;
            case 4:
                showCommonView();
                this.mainBinding.includeHead.headCancelIb.setBtnText(getString(R.string.cancel_edit));
                this.mainBinding.mainLeftToolRv.setVisibility(0);
                this.mainBinding.mainRightBtnOkIb.setBtnText(getString(R.string.save));
                this.mainBinding.mainRightBtnOkIb.setBtnIcon(R.mipmap.icon_btn_save);
                hiddenTips();
                return;
            case 5:
            case 6:
                showCommonView();
                this.mainBinding.mainBottomBtnCl.setVisibility(0);
                this.mainBinding.mainBottomStopIv.setVisibility(0);
                this.mainBinding.includeHead.headCancelIb.setBtnText(getString(R.string.exit_nav));
                this.mainBinding.mainRightBtnOkIb.setVisibility(8);
                this.mainBinding.mainRightBtnLockIb.setVisibility(8);
                this.mainBinding.mainRightBtnCancelNavIb.setVisibility(8);
                showTipsBlack(getString(R.string.tips_nav_normal));
                changeNavType(App.getNavType());
                return;
            case 7:
                showCommonView();
                this.mainBinding.includeHead.headCancelIb.setBtnText(getString(R.string.txt_exit));
                this.mainBinding.mainRightBtnOkIb.setBtnText(getString(R.string.dialog_confirm));
                this.mainBinding.mainRightBtnOkIb.setBtnIcon(R.mipmap.icon_btn_ok);
                showTipsBlack(getString(R.string.tips_setting_area));
                return;
            case 8:
                showCommonView();
                this.mainBinding.includeVisualLabel.labelRootCl.setVisibility(0);
                this.mainBinding.includeHead.headCancelIb.setBtnText(getString(R.string.txt_exit));
                this.mainBinding.mainRightBtnOkIb.setBtnText(getString(R.string.save));
                this.mainBinding.mainRightBtnOkIb.setBtnIcon(R.mipmap.icon_btn_save);
                showVisualLabelCalibrationView(true);
                return;
            case 9:
                showCommonView();
                showRocker(true);
                this.mainBinding.includeHead.headCancelIb.setBtnText(getString(R.string.txt_cancel_continue_scan_map));
                this.mainBinding.mainRightBtnOkIb.setBtnText(getString(R.string.txt_finish_continue_scan_map));
                this.mainBinding.mainRightBtnOkIb.setBtnIcon(R.mipmap.icon_btn_ok);
                showTipsBlack(getString(R.string.tips_continue_scan_map_normal));
                return;
            case 10:
                this.mainBinding.mainMapControlMrv.setTrajectoryDrawingPath((ArrayList<PointBean>) null);
                showCommonView();
                this.mainBinding.includeHead.headCancelIb.setBtnText(getString(R.string.cancel_trajectory_drawing));
                this.mainBinding.mainRightBtnOkIb.setBtnText(getString(R.string.save));
                this.mainBinding.mainRightBtnOkIb.setBtnIcon(R.mipmap.icon_btn_save);
                showTrajectoryDrawingView(true);
                this.mainBinding.mainBottomLayerIv.setVisibility(0);
                this.mainBinding.mainBottomRestoreIv.setVisibility(0);
                hiddenTips();
                return;
            case 11:
                showCommonView();
                this.mainBinding.includeHead.headCancelIb.setBtnText(getString(R.string.txt_exit));
                this.mainBinding.mainRightBtnOkIb.setVisibility(8);
                showCollectModeView(true);
                this.mainBinding.mainBottomLayerIv.setVisibility(0);
                this.mainBinding.mainBottomRestoreIv.setVisibility(0);
                hiddenTips();
                return;
            case 12:
                showCommonView();
                this.mainBinding.includeHead.headCancelIb.setBtnText(getString(R.string.txt_exit));
                this.mainBinding.mainRightBtnOkIb.setVisibility(8);
                showAddRandomPointView(true);
                this.mainBinding.mainBottomLayerIv.setVisibility(0);
                this.mainBinding.mainBottomRestoreIv.setVisibility(0);
                hiddenTips();
                return;
            default:
                return;
        }
    }

    public void changeNavType(int i) {
        runOnUiThread(new Runnable(i) {
            public final /* synthetic */ int f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                MainActivity.this.lambda$changeNavType$37$MainActivity(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$changeNavType$37$MainActivity(int i) {
        String str = this.TAG;
        MyLogUtils.Logd(str, "changeNavType:" + i);
        App.setNavType(i);
        if (i == 0) {
            this.mainBinding.includeNav.navTypeSingleRb.setChecked(true);
            this.mainBinding.includeNav.navMultiLineCl.setVisibility(8);
            if (MySpUtils.getInstance().getInt(SpConstant.NAV_MODE, 0) == 0) {
                this.mainBinding.includeNav.navMultiListLl.setVisibility(8);
            } else {
                this.mainBinding.includeNav.navMultiListRv.setAdapter(this.trajectoryPointAdapter);
                this.mainBinding.includeNav.navMultiListLl.setVisibility(0);
            }
            this.mainBinding.includeNav.navSetCl.setVisibility(8);
            this.mainBinding.mainRightBtnLockIb.setVisibility(0);
            this.mainBinding.mainRightBtnCancelNavIb.setVisibility(0);
            if (App.isSingleNaving()) {
                changeView(this.mainBinding.includeNav.navTypeRg, false);
            } else {
                changeView(this.mainBinding.includeNav.navTypeRg, true);
            }
        } else if (i == 1) {
            this.mainBinding.includeNav.navTypeMultiRb.setChecked(true);
            this.mainBinding.includeNav.navMultiLineCl.setVisibility(0);
            this.mainBinding.includeNav.navMultiListRv.setAdapter(this.multiAdapter);
            this.mainBinding.includeNav.navMultiListLl.setVisibility(0);
            this.mainBinding.includeNav.navSetCl.setVisibility(0);
            this.mainBinding.mainRightBtnLockIb.setVisibility(8);
            this.mainBinding.mainRightBtnCancelNavIb.setVisibility(8);
            if (this.mainBinding.includeNav.navMultiSingleRb.isChecked()) {
                this.mainBinding.includeNav.navMultiTimesEt.setText("1");
                this.mainBinding.includeNav.navMultiTimesEt.setEnabled(false);
                this.mainBinding.includeNav.navMultiTimesEt.setBackground((Drawable) null);
            }
            showPatrolRouteView();
        }
    }

    /* access modifiers changed from: private */
    public void showPatrolRouteView() {
        String str = this.TAG;
        MyLogUtils.Logd(str, this.mPatrolRoutes.size() + "mPatrolRoutes:" + GsonUtils.toJson(this.mPatrolRoutes));
        if (this.mPatrolRoutes.size() > 0) {
            this.mainBinding.includeNav.navMultiLineEmptyTv.setVisibility(8);
            this.mainBinding.includeNav.navMultiLineRv.setVisibility(0);
            if (App.isMultiNaving()) {
                this.mainBinding.includeNav.navMultiClearBtn.setVisibility(8);
            } else {
                this.mainBinding.includeNav.navMultiClearBtn.setVisibility(0);
                changeView(this.mainBinding.includeNav.radioGroup, true);
            }
            this.mainBinding.includeNav.navStartNavCl.setClickable(true);
            this.mainBinding.includeNav.navStartNavCl.setAlpha(1.0f);
            changeView(this.mainBinding.includeNav.navMultiTimesCl, true);
            this.mainBinding.includeNav.navMultiTimesEt.setEnabled(true);
            return;
        }
        this.mainBinding.includeNav.navMultiLineEmptyTv.setVisibility(0);
        this.mainBinding.includeNav.navMultiLineRv.setVisibility(8);
        this.mainBinding.includeNav.navMultiClearBtn.setVisibility(8);
        this.mainBinding.includeNav.navStartNavCl.setClickable(false);
        this.mainBinding.includeNav.navStartNavCl.setAlpha(0.5f);
        changeView(this.mainBinding.includeNav.navMultiTimesCl, false);
        this.mainBinding.includeNav.navMultiTimesEt.setEnabled(false);
        changeView(this.mainBinding.includeNav.radioGroup, false);
    }

    public void selectPatrolRoute(int i, int i2, boolean z) {
        runOnUiThread(new Runnable(i, i2, z) {
            public final /* synthetic */ int f$1;
            public final /* synthetic */ int f$2;
            public final /* synthetic */ boolean f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void run() {
                MainActivity.this.lambda$selectPatrolRoute$38$MainActivity(this.f$1, this.f$2, this.f$3);
            }
        });
    }

    public /* synthetic */ void lambda$selectPatrolRoute$38$MainActivity(int i, int i2, boolean z) {
        if (this.mPatrolRouteAdapter != null) {
            String str = this.TAG;
            MyLogUtils.Logd(str, "selectPatrolRoute：;selectPosition：" + i + "；patrolMode：" + i2 + "isRight：" + z);
            this.mPatrolRouteAdapter.setSelectPosition(i, z, i2);
            if (i <= this.mPatrolRouteAdapter.getItemCount() - 1) {
                this.mPatrolCenterLm.smoothScrollToPosition(this.mainBinding.includeNav.navMultiLineRv, new RecyclerView.State(), i);
            }
        }
    }

    public void updateMultipointNavData(int i, int i2) {
        runOnUiThread(new Runnable(i2, i) {
            public final /* synthetic */ int f$1;
            public final /* synthetic */ int f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                MainActivity.this.lambda$updateMultipointNavData$39$MainActivity(this.f$1, this.f$2);
            }
        });
    }

    public /* synthetic */ void lambda$updateMultipointNavData$39$MainActivity(int i, int i2) {
        int i3 = MySpUtils.getInstance().getInt(DeploymentToolSpConstant.MULTIPOINT_NAV_LOOP_NUM, 0);
        this.mainBinding.includeNav.navMultiTimesEt.setText(String.valueOf(i));
        if (i2 == 2) {
            if (i3 == 1) {
                this.mainBinding.includeNav.radioGroup.check(R.id.nav_multi_single_rb);
            } else {
                this.mainBinding.includeNav.radioGroup.check(R.id.nav_multi_cycle_rb);
            }
        } else if (i2 == 3) {
            this.mainBinding.includeNav.radioGroup.check(R.id.nav_multi_back_rb);
        }
    }

    public void showEditMapDialog() {
        String string = ContextUtil.getContext().getString(R.string.txt_whether_to_save_edits);
        String string2 = ContextUtil.getContext().getString(R.string.txt_save_and_exit);
        String string3 = ContextUtil.getContext().getString(R.string.txt_not_save);
        String string4 = ContextUtil.getContext().getString(R.string.txt_exit_edit_map_mode);
        NormalDialog createDialog = NormalDialog.createDialog(this);
        createDialog.setTitle(string4);
        createDialog.setMessage(string);
        createDialog.setBtCancel(string3);
        createDialog.setBtOk(string2);
        createDialog.setShowTopExit(true);
        createDialog.setMessageTxtColor(R.color.black);
        createDialog.setBtnCancleStyle();
        createDialog.setmOnDialogListener(new NormalDialog.OnDialogListener() {
            public final void isPositiveBtClick(boolean z) {
                MainActivity.this.lambda$showEditMapDialog$40$MainActivity(z);
            }
        });
        createDialog.show();
    }

    public /* synthetic */ void lambda$showEditMapDialog$40$MainActivity(boolean z) {
        if (z) {
            ((MainPresenter) this.mPresenter).editMapSave();
        } else {
            ((MainPresenter) this.mPresenter).exitEditMap();
        }
    }

    private void showCommonView() {
        this.mainBinding.includeHead.headCreateMapIb.setVisibility(8);
        this.mainBinding.includeHead.headCancelIb.setVisibility(0);
        this.mainBinding.includeHead.headBuildingCl.setVisibility(8);
        showRocker(false);
        this.mainBinding.mainBuildInfoCl.setVisibility(8);
        showBuildManageView(false);
        this.mainBinding.mainPointInfoCl.setVisibility(8);
        showPointManageView(false);
        this.mainBinding.mainPointTypeCl.setVisibility(8);
        this.mainBinding.mainLeftToolRv.setVisibility(8);
        selectLeftToolBtn("layer_normal");
        this.mainBinding.mainRightToolRl.setVisibility(8);
        selectRightToolBtn(0);
        this.mainBinding.mainRightBtnLockIb.setVisibility(8);
        this.mainBinding.mainRightBtnCancelNavIb.setVisibility(8);
        this.mainBinding.mainRightBtnOkIb.setVisibility(0);
        this.mainBinding.mainRightBtnOkIb.setBtnText(getString(R.string.save));
        this.mainBinding.mainRightBtnOkIb.setBtnIcon(R.mipmap.icon_btn_save);
        this.mainBinding.mainBottomBtnCl.setVisibility(4);
        this.mainBinding.mainBottomLockIv.setVisibility(8);
        this.mainBinding.mainBottomNavIv.setVisibility(8);
        this.mainBinding.mainBottomStopIv.setVisibility(4);
        this.mainBinding.mainZoomCl.setVisibility(8);
        this.mainBinding.mainBottomRestoreIv.setVisibility(0);
        this.mainBinding.includeNav.navRootCl.setVisibility(8);
        this.mainBinding.includeVisualLabel.labelRootCl.setVisibility(8);
        if (!App.isMultiNaving()) {
            changePatrollingView(false);
        }
        this.mainBinding.mainBottomLayerIv.setVisibility(0);
        showCameraPointCloudView();
    }

    private void initRecycleView() {
        this.buildAdapter = new BuildAdapter(ContextUtil.getContext());
        this.mainBinding.mainBuildNameRv.setLayoutManager(new LinearLayoutManager(ContextUtil.getContext()));
        this.mainBinding.mainBuildNameRv.setAdapter(this.buildAdapter);
        this.floorAdapter = new FloorAdapter(ContextUtil.getContext());
        this.mainBinding.mainFloorRv.setLayoutManager(new LinearLayoutManager(ContextUtil.getContext()));
        this.mainBinding.mainFloorRv.setAdapter(this.floorAdapter);
        this.mapManagerAdapter = new MapManagerAdapter();
        this.mainBinding.mainBuildInfoListRv.setLayoutManager(new LinearLayoutManager(ContextUtil.getContext()));
        this.mainBinding.mainBuildInfoListRv.setAdapter(this.mapManagerAdapter);
        this.pointAdapter = new PointAdapter(ContextUtil.getContext());
        this.mainBinding.mainPointListRv.setLayoutManager(new LinearLayoutManager(ContextUtil.getContext()));
        this.mainBinding.mainPointListRv.setAdapter(this.pointAdapter);
        this.multiAdapter = new MultiAdapter(ContextUtil.getContext());
        this.mainBinding.includeNav.navMultiListRv.setLayoutManager(new LinearLayoutManager(ContextUtil.getContext()));
        this.intellectPointAdapter = new IntellectPointAdapter(ContextUtil.getContext());
        this.mainBinding.includeTracking.intellectPointListRv.setAdapter(this.intellectPointAdapter);
        this.trajectoryPointAdapter = new TrajectoryPointAdapter(ContextUtil.getContext());
        this.mPatrolRouteAdapter = new PatrolRouteAdapter(this.mPatrolRoutes);
        this.mPatrolCenterLm = new CenterLayoutManager(ContextUtil.getContext(), 0, false);
        this.mainBinding.includeNav.navMultiLineRv.setLayoutManager(this.mPatrolCenterLm);
        this.mainBinding.includeNav.navMultiLineRv.setAdapter(this.mPatrolRouteAdapter);
        new ItemTouchHelper(new ItemDragAndSwipeCallback(this.mPatrolRouteAdapter)).attachToRecyclerView(this.mainBinding.includeNav.navMultiLineRv);
        this.mPatrolRouteAdapter.setOnPatrolRouteItemListener(new PatrolRouteAdapter.OnPatrolRouteItemListener() {
            public final void onItemDeleteClick() {
                MainActivity.this.showPatrolRouteView();
            }
        });
        this.mIntellectRouteAdapter = new PatrolRouteAdapter(this.mIntellectRoutes);
        this.mainBinding.includeTracking.intellectMultiLineRv.setLayoutManager(new CenterLayoutManager(ContextUtil.getContext(), 0, false));
        this.mainBinding.includeTracking.intellectMultiLineRv.setAdapter(this.mIntellectRouteAdapter);
        this.mIntellectRouteAdapter.setOnPatrolRouteItemListener(new PatrolRouteAdapter.OnPatrolRouteItemListener() {
            public final void onItemDeleteClick() {
                MainActivity.this.lambda$initRecycleView$41$MainActivity();
            }
        });
        this.mLeftToolBarAdapter = new LeftToolBarAdapter();
        this.mainBinding.mainLeftToolRv.setLayoutManager(new LinearLayoutManager(ContextUtil.getContext()));
        this.mainBinding.mainLeftToolRv.setAdapter(this.mLeftToolBarAdapter);
        this.mRightToolBarAdapter = new RightToolBarAdapter();
        this.mainBinding.mainRightToolRl.setLayoutManager(new LinearLayoutManager(ContextUtil.getContext()));
        this.mainBinding.mainRightToolRl.setAdapter(this.mRightToolBarAdapter);
        AreaAdapter areaAdapter = new AreaAdapter(this.mAreaItemBeanList, this.mLeftToolType);
        this.mAreaAdapter = areaAdapter;
        areaAdapter.setItemOnClickListener(new AreaAdapter.ItemOnClickListener() {
            public void selectedItem(AreaItemBean areaItemBean) {
                ((MainPresenter) MainActivity.this.mPresenter).selectRectangle();
                MainActivity.this.clearUnfinishedArea();
                MainActivity.this.mainBinding.mainMapControlMrv.setSelectArea(areaItemBean);
            }

            public void delItem(AreaItemBean areaItemBean) {
                ((MainPresenter) MainActivity.this.mPresenter).deleteArea(MainActivity.this.mAreaItemBeanList, areaItemBean.getName());
            }

            public void bindItem(AreaItemBean areaItemBean) {
                MainActivity.this.showChooseWaitMarkerDialog(areaItemBean);
            }
        });
        this.mainBinding.rightAreaListRv.setLayoutManager(new LinearLayoutManager(this));
        this.mainBinding.rightAreaListRv.setAdapter(this.mAreaAdapter);
        this.mVisualLabelAdapter = new VisualLabelAdapter(this);
        this.mainBinding.includeVisualLabel.labelListRv.setLayoutManager(new LinearLayoutManager(this));
        this.mainBinding.includeVisualLabel.labelListRv.setAdapter(this.mVisualLabelAdapter);
        this.mTopMenuAdapter = new TopMenuAdapter();
        this.mainBinding.mainTopMenuRv.setLayoutManager(new LinearLayoutManager(ContextUtil.getContext()));
        this.mainBinding.mainTopMenuRv.setAdapter(this.mTopMenuAdapter);
        this.robotListAdapter = new RobotListAdapter();
        this.mainBinding.rvRobotList.setAdapter(this.robotListAdapter);
    }

    public /* synthetic */ void lambda$initRecycleView$41$MainActivity() {
        this.mainBinding.includeTracking.btnSure.setEnabled(this.mIntellectRoutes.size() > 0);
    }

    /* access modifiers changed from: private */
    public void showChooseWaitMarkerDialog(final AreaItemBean areaItemBean) {
        ChoosePointWaitMarkerDialog newInstance = ChoosePointWaitMarkerDialog.newInstance();
        newInstance.setOnChooseMarkerListener(new ChoosePointWaitMarkerDialog.OnChooseMarkerListener() {
            public void onChooseMarkerListener(ArrayList<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean> arrayList) {
                if (arrayList.isEmpty()) {
                    MainActivity mainActivity = MainActivity.this;
                    ToastUtil.showShort(mainActivity, mainActivity.getString(R.string.txt_select_point_wait));
                    return;
                }
                areaItemBean.setParam1(arrayList.get(0).getPose().getPosition().getX());
                areaItemBean.setParam2((double) arrayList.get(0).getPose().getPosition().getY());
                areaItemBean.setParam3(0.0d);
                areaItemBean.setParam4(0.0d);
                if (arrayList.size() >= 2) {
                    areaItemBean.setParam3((double) arrayList.get(1).getPose().getPosition().getX());
                    areaItemBean.setParam4((double) arrayList.get(1).getPose().getPosition().getY());
                }
                ((MainPresenter) MainActivity.this.mPresenter).appendArea(areaItemBean);
            }
        });
        newInstance.showAllowingStateLoss(getSupportFragmentManager());
    }

    private RobotListRespBean getFakeData(PointBean pointBean) {
        String str = this.TAG;
        Log.d(str, "getFakeData: " + GsonUtils.toJson(pointBean));
        RobotListRespBean robotListRespBean = new RobotListRespBean();
        ArrayList arrayList = new ArrayList();
        RobotListRespBean.MsgBean.ListBean listBean = new RobotListRespBean.MsgBean.ListBean();
        listBean.setRobot_id("No.2187916384328946982");
        listBean.setPose(new RobotListRespBean.MsgBean.ListBean.PoseBean(pointBean.getX() + 10.0f, pointBean.getY(), pointBean.getTheta()));
        arrayList.add(listBean);
        RobotListRespBean.MsgBean.ListBean listBean2 = new RobotListRespBean.MsgBean.ListBean();
        listBean2.setRobot_id("No.2187916384328946983");
        listBean2.setPose(new RobotListRespBean.MsgBean.ListBean.PoseBean(pointBean.getX() + 20.0f, pointBean.getY(), pointBean.getTheta()));
        arrayList.add(listBean2);
        RobotListRespBean.MsgBean.ListBean listBean3 = new RobotListRespBean.MsgBean.ListBean();
        listBean3.setRobot_id("No.2187916384328946984");
        listBean3.setPose(new RobotListRespBean.MsgBean.ListBean.PoseBean(pointBean.getX() + 30.0f, pointBean.getY(), pointBean.getTheta()));
        arrayList.add(listBean3);
        robotListRespBean.setMsg(new RobotListRespBean.MsgBean(arrayList));
        return robotListRespBean;
    }

    public void showBuildManageView(boolean z) {
        if (z) {
            this.mainBinding.mainBuildInfoListRv.setVisibility(0);
        } else {
            this.mainBinding.mainBuildInfoListRv.setVisibility(8);
        }
    }

    public boolean isShowPointManageView() {
        return this.mainBinding.mainPointListLl.getVisibility() == 0;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        getResources();
    }

    public void showPointManageView(boolean z) {
        if (z) {
            this.mainBinding.mainPointListRv.getAdapter().notifyDataSetChanged();
            this.mainBinding.mainPointListLl.setVisibility(0);
            return;
        }
        this.mainBinding.mainPointListLl.setVisibility(8);
    }

    public boolean isShowCoverageView() {
        return this.mainBinding.mainLayerView.getVisibility() == 0;
    }

    public void showCoverageView(boolean z) {
        if (z) {
            this.mainBinding.mainLayerView.setVisibility(0);
            this.mainBinding.mainBottomLayerIv.setBackgroundResource(R.drawable.ic_layer_press);
            return;
        }
        this.mainBinding.mainLayerView.setVisibility(8);
        this.mainBinding.mainBottomLayerIv.setBackgroundResource(R.drawable.ic_layer_normal);
    }

    public boolean isShowTopMenuView() {
        return this.mainBinding.mainTopMenuRv.getVisibility() == 0;
    }

    public void showTopMenuView(boolean z) {
        if (z) {
            this.mainBinding.mainTopMenuRv.setVisibility(0);
        } else {
            this.mainBinding.mainTopMenuRv.setVisibility(8);
        }
    }

    public void selectRightToolBtn(int i) {
        switch (i) {
            case 0:
                showCoverageView(false);
                return;
            case 1:
                ((MainPresenter) this.mPresenter).showCoverage();
                return;
            case 2:
                ((MainPresenter) this.mPresenter).showPositioning(this);
                return;
            case 3:
                ((MainPresenter) this.mPresenter).showCalibration();
                return;
            case 4:
                ((MainPresenter) this.mPresenter).showEditMap();
                return;
            case 5:
                ((MainPresenter) this.mPresenter).showSetting();
                return;
            case 6:
                ((MainPresenter) this.mPresenter).showVisualLabelCalibrationMode();
                return;
            case 7:
                ((MainPresenter) this.mPresenter).showTrajectoryDrawing();
                return;
            case 8:
                ((MainPresenter) this.mPresenter).showCollectMode();
                return;
            default:
                return;
        }
    }

    public void showSetActivity() {
        startActivity(new Intent(getApplicationContext(), SetActivity.class));
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0038, code lost:
        if (r7.equals("layer_work_area") != false) goto L_0x00a5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void selectLeftToolBtn(java.lang.String r7) {
        /*
            r6 = this;
            mc.csst.com.selfchassis.utils.SoftTypeInfoManager r0 = mc.csst.com.selfchassis.utils.SoftTypeInfoManager.getInstance()
            int r0 = r0.getSoftType()
            r1 = 4
            if (r0 != r1) goto L_0x0019
            mc.csst.com.selfchassis.databinding.ActivityMainBinding r0 = r6.mainBinding
            mc.csst.com.selfchassis.utils.view.LayerSelectView r0 = r0.mainLayerView
            r0.refreshByLeftToolType(r7)
            mc.csst.com.selfchassis.databinding.ActivityMainBinding r0 = r6.mainBinding
            mc.csst.com.selfchassis.utils.view.map.MapRlView r0 = r0.mainMapControlMrv
            r0.changeLeftToolType(r7)
        L_0x0019:
            int r0 = r7.hashCode()
            r2 = -1
            r3 = 1
            r4 = 8
            r5 = 0
            switch(r0) {
                case -2147287059: goto L_0x0099;
                case -2135188017: goto L_0x008e;
                case -447263283: goto L_0x0084;
                case -366495153: goto L_0x007a;
                case 123376443: goto L_0x0070;
                case 1158444949: goto L_0x0066;
                case 1395992912: goto L_0x005c;
                case 1478720400: goto L_0x0051;
                case 1557329574: goto L_0x0046;
                case 1707136669: goto L_0x003c;
                case 2024711053: goto L_0x0032;
                case 2132302508: goto L_0x0027;
                default: goto L_0x0025;
            }
        L_0x0025:
            goto L_0x00a4
        L_0x0027:
            java.lang.String r0 = "layer_virtual_wall"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x00a4
            r1 = 1
            goto L_0x00a5
        L_0x0032:
            java.lang.String r0 = "layer_work_area"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x00a4
            goto L_0x00a5
        L_0x003c:
            java.lang.String r0 = "layer_slow_area"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x00a4
            r1 = 2
            goto L_0x00a5
        L_0x0046:
            java.lang.String r0 = "layer_label_area"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x00a4
            r1 = 8
            goto L_0x00a5
        L_0x0051:
            java.lang.String r0 = "layer_unknown_area"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x00a4
            r1 = 11
            goto L_0x00a5
        L_0x005c:
            java.lang.String r0 = "layer_strong_light_area"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x00a4
            r1 = 3
            goto L_0x00a5
        L_0x0066:
            java.lang.String r0 = "layer_normal"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x00a4
            r1 = 0
            goto L_0x00a5
        L_0x0070:
            java.lang.String r0 = "layer_danger_area"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x00a4
            r1 = 5
            goto L_0x00a5
        L_0x007a:
            java.lang.String r0 = "layer_slope_area"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x00a4
            r1 = 7
            goto L_0x00a5
        L_0x0084:
            java.lang.String r0 = "layer_narrow_channel"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x00a4
            r1 = 6
            goto L_0x00a5
        L_0x008e:
            java.lang.String r0 = "layer_obstacle_area"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x00a4
            r1 = 10
            goto L_0x00a5
        L_0x0099:
            java.lang.String r0 = "layer_empty_area"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x00a4
            r1 = 9
            goto L_0x00a5
        L_0x00a4:
            r1 = -1
        L_0x00a5:
            r0 = 2131755583(0x7f10023f, float:1.914205E38)
            switch(r1) {
                case 0: goto L_0x018f;
                case 1: goto L_0x0159;
                case 2: goto L_0x0128;
                case 3: goto L_0x0128;
                case 4: goto L_0x0128;
                case 5: goto L_0x0128;
                case 6: goto L_0x0128;
                case 7: goto L_0x0128;
                case 8: goto L_0x0128;
                case 9: goto L_0x00ff;
                case 10: goto L_0x00d6;
                case 11: goto L_0x00ad;
                default: goto L_0x00ab;
            }
        L_0x00ab:
            goto L_0x01a7
        L_0x00ad:
            mc.csst.com.selfchassis.databinding.ActivityMainBinding r1 = r6.mainBinding
            androidx.constraintlayout.widget.ConstraintLayout r1 = r1.mainLeftToolShapeCl
            r1.setVisibility(r4)
            mc.csst.com.selfchassis.databinding.ActivityMainBinding r1 = r6.mainBinding
            android.widget.LinearLayout r1 = r1.mainLeftToolBrushCl
            r1.setVisibility(r5)
            r6.selectBrushBtn(r5)
            java.lang.String r0 = r6.getString(r0)
            java.lang.Object[] r1 = new java.lang.Object[r3]
            r2 = 2131755821(0x7f10032d, float:1.9142532E38)
            java.lang.String r2 = r6.getString(r2)
            r1[r5] = r2
            java.lang.String r0 = java.lang.String.format(r0, r1)
            r6.showTipsBlack(r0)
            goto L_0x01a7
        L_0x00d6:
            mc.csst.com.selfchassis.databinding.ActivityMainBinding r1 = r6.mainBinding
            androidx.constraintlayout.widget.ConstraintLayout r1 = r1.mainLeftToolShapeCl
            r1.setVisibility(r4)
            mc.csst.com.selfchassis.databinding.ActivityMainBinding r1 = r6.mainBinding
            android.widget.LinearLayout r1 = r1.mainLeftToolBrushCl
            r1.setVisibility(r5)
            r6.selectBrushBtn(r5)
            java.lang.String r0 = r6.getString(r0)
            java.lang.Object[] r1 = new java.lang.Object[r3]
            r2 = 2131755395(0x7f100183, float:1.9141668E38)
            java.lang.String r2 = r6.getString(r2)
            r1[r5] = r2
            java.lang.String r0 = java.lang.String.format(r0, r1)
            r6.showTipsBlack(r0)
            goto L_0x01a7
        L_0x00ff:
            mc.csst.com.selfchassis.databinding.ActivityMainBinding r1 = r6.mainBinding
            androidx.constraintlayout.widget.ConstraintLayout r1 = r1.mainLeftToolShapeCl
            r1.setVisibility(r4)
            mc.csst.com.selfchassis.databinding.ActivityMainBinding r1 = r6.mainBinding
            android.widget.LinearLayout r1 = r1.mainLeftToolBrushCl
            r1.setVisibility(r5)
            r6.selectBrushBtn(r5)
            java.lang.String r0 = r6.getString(r0)
            java.lang.Object[] r1 = new java.lang.Object[r3]
            r2 = 2131755267(0x7f100103, float:1.9141408E38)
            java.lang.String r2 = r6.getString(r2)
            r1[r5] = r2
            java.lang.String r0 = java.lang.String.format(r0, r1)
            r6.showTipsBlack(r0)
            goto L_0x01a7
        L_0x0128:
            mc.csst.com.selfchassis.databinding.ActivityMainBinding r1 = r6.mainBinding
            androidx.constraintlayout.widget.ConstraintLayout r1 = r1.mainLeftToolShapeCl
            r1.setVisibility(r5)
            mc.csst.com.selfchassis.databinding.ActivityMainBinding r1 = r6.mainBinding
            androidx.appcompat.widget.AppCompatImageView r1 = r1.mainLineIv
            r1.setVisibility(r4)
            mc.csst.com.selfchassis.databinding.ActivityMainBinding r1 = r6.mainBinding
            androidx.appcompat.widget.AppCompatImageView r1 = r1.mainRectangleIv
            r1.setVisibility(r5)
            r6.selectShapeBtn(r5)
            mc.csst.com.selfchassis.databinding.ActivityMainBinding r1 = r6.mainBinding
            android.widget.LinearLayout r1 = r1.mainLeftToolBrushCl
            r1.setVisibility(r4)
            java.lang.String r0 = r6.getString(r0)
            java.lang.Object[] r1 = new java.lang.Object[r3]
            java.lang.String r2 = r6.mLeftToolTypeDesc
            r1[r5] = r2
            java.lang.String r0 = java.lang.String.format(r0, r1)
            r6.showTipsBlack(r0)
            goto L_0x01a7
        L_0x0159:
            mc.csst.com.selfchassis.databinding.ActivityMainBinding r1 = r6.mainBinding
            androidx.constraintlayout.widget.ConstraintLayout r1 = r1.mainLeftToolShapeCl
            r1.setVisibility(r5)
            mc.csst.com.selfchassis.databinding.ActivityMainBinding r1 = r6.mainBinding
            androidx.appcompat.widget.AppCompatImageView r1 = r1.mainLineIv
            r1.setVisibility(r5)
            mc.csst.com.selfchassis.databinding.ActivityMainBinding r1 = r6.mainBinding
            androidx.appcompat.widget.AppCompatImageView r1 = r1.mainRectangleIv
            r1.setVisibility(r4)
            r6.selectShapeBtn(r5)
            mc.csst.com.selfchassis.databinding.ActivityMainBinding r1 = r6.mainBinding
            android.widget.LinearLayout r1 = r1.mainLeftToolBrushCl
            r1.setVisibility(r4)
            java.lang.String r0 = r6.getString(r0)
            java.lang.Object[] r1 = new java.lang.Object[r3]
            r2 = 2131755833(0x7f100339, float:1.9142556E38)
            java.lang.String r2 = r6.getString(r2)
            r1[r5] = r2
            java.lang.String r0 = java.lang.String.format(r0, r1)
            r6.showTipsBlack(r0)
            goto L_0x01a7
        L_0x018f:
            mc.csst.com.selfchassis.databinding.ActivityMainBinding r0 = r6.mainBinding
            androidx.constraintlayout.widget.ConstraintLayout r0 = r0.mainLeftToolShapeCl
            r0.setVisibility(r4)
            mc.csst.com.selfchassis.databinding.ActivityMainBinding r0 = r6.mainBinding
            android.widget.LinearLayout r0 = r0.mainLeftToolBrushCl
            r0.setVisibility(r4)
            mc.csst.com.selfchassis.adapter.LeftToolBarAdapter r0 = r6.mLeftToolBarAdapter
            if (r0 == 0) goto L_0x01a4
            r0.setSelectPosition(r2)
        L_0x01a4:
            r6.hiddenTips()
        L_0x01a7:
            boolean r7 = mc.csst.com.selfchassis.utils.enumbs.ConvertChassisAreaType.isEqualLeftToolType(r7)
            if (r7 == 0) goto L_0x01b3
            java.lang.String r7 = r6.mLeftToolTypeDesc
            r6.showAreaView(r3, r7)
            goto L_0x01b8
        L_0x01b3:
            java.lang.String r7 = ""
            r6.showAreaView(r5, r7)
        L_0x01b8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: mc.csst.com.selfchassis.ui.activity.main.MainActivity.selectLeftToolBtn(java.lang.String):void");
    }

    private void showAreaView(boolean z, String str) {
        if (z) {
            this.mainBinding.rightAreaTitleNameTv.setText(str);
            this.mainBinding.rightAreaRootCl.setVisibility(0);
            this.mainBinding.rightAreaListCl.setVisibility(0);
            this.mainBinding.rightAreaArrowIv.setBackgroundResource(R.mipmap.down_arrow);
            clearUnfinishedArea();
            return;
        }
        this.mainBinding.rightAreaArrowIv.setBackgroundResource(R.mipmap.right_arrow);
        this.mainBinding.mainLeftCleanDrawAreaIv.setVisibility(8);
        this.mainBinding.rightAreaRootCl.setVisibility(8);
    }

    public void showChooseMarkerDialog() {
        NormalDialog showCalibrationMode = DialogHelper.showCalibrationMode(this);
        showCalibrationMode.setmOnDialogListener(new MyDialogClickListener());
        showCalibrationMode.show();
    }

    public boolean isShowShapeBtn() {
        return this.mainBinding.mainLeftToolShapeCl.getVisibility() == 0;
    }

    public void selectShapeBtn(int i) {
        this.mainBinding.mainMapControlMrv.changeShapeType(i);
        if (i == 0) {
            this.mainBinding.mainLineIv.setImageResource(R.mipmap.icon_shap_line_normal);
            this.mainBinding.mainRectangleIv.setImageResource(R.mipmap.icon_shap_rectangle_normal);
        } else if (i == 1) {
            this.mainBinding.mainLineIv.setImageResource(R.mipmap.icon_shap_line_press);
            this.mainBinding.mainRectangleIv.setImageResource(R.mipmap.icon_shap_rectangle_normal);
        } else if (i == 2) {
            this.mainBinding.mainLineIv.setImageResource(R.mipmap.icon_shap_line_normal);
            this.mainBinding.mainRectangleIv.setImageResource(R.mipmap.icon_shap_rectangle_press);
        }
    }

    public boolean isShowBrushBtn() {
        return this.mainBinding.mainLeftToolBrushCl.getVisibility() == 0;
    }

    public void selectBrushBtn(int i) {
        this.mainBinding.mainMapControlMrv.changeBrushType(i);
        if (i == 0) {
            this.mainBinding.mainBrushSmallIv.setImageResource(R.drawable.shape_brush_normal);
            this.mainBinding.mainBrushMediumIv.setImageResource(R.drawable.shape_brush_normal);
            this.mainBinding.mainBrushBigIv.setImageResource(R.drawable.shape_brush_normal);
        } else if (i == 1) {
            this.mainBinding.mainBrushSmallIv.setImageResource(R.drawable.shape_brush_press);
            this.mainBinding.mainBrushMediumIv.setImageResource(R.drawable.shape_brush_normal);
            this.mainBinding.mainBrushBigIv.setImageResource(R.drawable.shape_brush_normal);
        } else if (i == 2) {
            this.mainBinding.mainBrushSmallIv.setImageResource(R.drawable.shape_brush_normal);
            this.mainBinding.mainBrushMediumIv.setImageResource(R.drawable.shape_brush_press);
            this.mainBinding.mainBrushBigIv.setImageResource(R.drawable.shape_brush_normal);
        } else if (i == 3) {
            this.mainBinding.mainBrushSmallIv.setImageResource(R.drawable.shape_brush_normal);
            this.mainBinding.mainBrushMediumIv.setImageResource(R.drawable.shape_brush_normal);
            this.mainBinding.mainBrushBigIv.setImageResource(R.drawable.shape_brush_press);
        }
    }

    public void showTipsBlack(String str) {
        showTips(true, Integer.valueOf(R.color.black), str);
    }

    public void showTipsGreen(String str) {
        showTips(true, Integer.valueOf(R.color.clr_22C288), str);
    }

    public void hiddenTips() {
        showTips(false, (Integer) null, (String) null);
    }

    private void showTips(boolean z, Integer num, String str) {
        if (z) {
            ShowSelfChassisBean.getInstance().setBottomTips(str);
            ShowSelfChassisBean.getInstance().setShowBottomTips(0);
            ShowSelfChassisBean.getInstance().setBottomTipsColor(ColorUtils.getColor(num.intValue()));
            return;
        }
        ShowSelfChassisBean.getInstance().setShowBottomTips(8);
    }

    public void showRightBtnSave(boolean z) {
        if (z) {
            this.mainBinding.mainRightBtnOkIb.setVisibility(0);
        } else {
            this.mainBinding.mainRightBtnOkIb.setVisibility(8);
        }
    }

    public void changeRightTopBtnLock(boolean z) {
        if (z) {
            this.mainBinding.mainRightBtnLockIb.setBtnIcon(R.mipmap.icon_btn_unlock);
            this.mainBinding.mainRightBtnLockIb.setBtnText(getString(R.string.unlock_map));
            return;
        }
        this.mainBinding.mainRightBtnLockIb.setBtnIcon(R.mipmap.icon_btn_lock);
        this.mainBinding.mainRightBtnLockIb.setBtnText(getString(R.string.lock_map));
    }

    public void changeBottomLock(boolean z) {
        this.mainBinding.includeDirectionControl.mainDirectionFourRv.setOnTouchListener(new View.OnTouchListener(z) {
            public final /* synthetic */ boolean f$0;

            {
                this.f$0 = r1;
            }

            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return MainActivity.lambda$changeBottomLock$42(this.f$0, view, motionEvent);
            }
        });
        this.mainBinding.includeDirectionControl.mainDirectionAllRv.setOnTouchListener(new View.OnTouchListener(z) {
            public final /* synthetic */ boolean f$0;

            {
                this.f$0 = r1;
            }

            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return MainActivity.lambda$changeBottomLock$43(this.f$0, view, motionEvent);
            }
        });
        float f = z ? 0.5f : 1.0f;
        this.mainBinding.includeDirectionControl.mainDirectionFourRv.setAlpha(f);
        this.mainBinding.includeDirectionControl.mainDirectionAllRv.setAlpha(f);
    }

    public void lockMap(boolean z) {
        this.mainBinding.mainMapControlMrv.lockTargetPoint(z);
    }

    private void closeContinueScanConfirmDialog() {
        ContinueScanConfirmDialog continueScanConfirmDialog = this.mContinueScanConfirmDialog;
        if (continueScanConfirmDialog != null && continueScanConfirmDialog.isAdded() && this.mContinueScanConfirmDialog.isShow()) {
            this.mContinueScanConfirmDialog.dismissAllowingStateLoss();
        }
        this.mContinueScanConfirmDialog = null;
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        WifiSwitchBroadcastReceiver wifiSwitchBroadcastReceiver2 = this.wifiSwitchBroadcastReceiver;
        if (wifiSwitchBroadcastReceiver2 != null) {
            wifiSwitchBroadcastReceiver2.onDestroy();
        }
        if (this.mNotificationsListener != null) {
            SelfChassisListenerUtils.getInstance().getNotificationsListener().remove(this.mNotificationsListener);
        }
        SelfChassisListenerUtils.OnBaseSelfChassisListener onBaseSelfChassisListener = this.configMqttListener;
        if (onBaseSelfChassisListener != null) {
            SelfChassisListenerUtils.ConfigMqtt.removeMqttListener(onBaseSelfChassisListener);
        }
        closeConnectedDialog();
        closeNotConnectedWifiDialog();
        closeContinueScanConfirmDialog();
        DisposeUtil.INSTANCE.onUnsubscribe();
        AppUtils.exitApp();
    }

    private void supportRtl() {
        MultiLanguageUtil.getInstance().isRtlLanguage();
    }
}
