package mc.csst.com.selfchassis.ui.activity.main;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import androidx.fragment.app.FragmentManager;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.ciot.base.livedatabus.LiveDatabus;
import com.ciot.base.storage.MySpUtils;
import com.ciot.base.util.ContextUtil;
import com.ciot.base.util.DisposeUtil;
import com.ciot.base.util.MyLogUtils;
import com.ciot.base.util.ToastUtil;
import com.ciot.sentrymove.R;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import mc.csst.com.selfchassis.App;
import mc.csst.com.selfchassis.model.trajectory.TrajectoryModel;
import mc.csst.com.selfchassis.ui.activity.base.BasePresenter;
import mc.csst.com.selfchassis.ui.activity.main.MainContract;
import mc.csst.com.selfchassis.ui.activity.main.MainPresenter;
import mc.csst.com.selfchassis.ui.dialog.AddMarkDialog;
import mc.csst.com.selfchassis.ui.dialog.ChoosePointWaitMarkerDialog;
import mc.csst.com.selfchassis.ui.dialog.ConfirmDialog;
import mc.csst.com.selfchassis.ui.dialog.ContinueScanConfirmDialog;
import mc.csst.com.selfchassis.ui.dialog.EditDialog;
import mc.csst.com.selfchassis.ui.dialog.NormalDialog;
import mc.csst.com.selfchassis.ui.fragment.set.language.transaction.GatewayTool;
import mc.csst.com.selfchassis.utils.DeploymentToolUtils;
import mc.csst.com.selfchassis.utils.LayerDataUtils;
import mc.csst.com.selfchassis.utils.MyToastUtils;
import mc.csst.com.selfchassis.utils.NavSettingApiUtil;
import mc.csst.com.selfchassis.utils.RxTimer;
import mc.csst.com.selfchassis.utils.SoftTypeInfoManager;
import mc.csst.com.selfchassis.utils.SpConstant;
import mc.csst.com.selfchassis.utils.bean.LeftToolbarBean;
import mc.csst.com.selfchassis.utils.bean.ShowSelfChassisBean;
import mc.csst.com.selfchassis.utils.bean.ToolbarBean;
import mc.csst.com.selfchassis.utils.bean.TopMenuBean;
import mc.csst.com.selfchassis.utils.constant.DTLiveDataConstant;
import mc.csst.com.selfchassis.utils.constant.DeploymentToolConstant;
import mc.csst.com.selfchassis.utils.constant.DeploymentToolSpConstant;
import mc.csst.com.selfchassis.utils.enumbs.BottomNavTipsType;
import mc.csst.com.selfchassis.utils.enumbs.NavDialogTipsType;
import mc.csst.com.selfchassis.utils.enumbs.NavStateType;
import mc.csst.com.selfchassis.utils.view.map.area.AreaUtils;
import mc.csst.com.selfchassislibrary.bean.MapInfoBean;
import mc.csst.com.selfchassislibrary.bean.WaypointStateBean;
import mc.csst.com.selfchassislibrary.bean.msg.AreaItemBean;
import mc.csst.com.selfchassislibrary.bean.msg.CrossFloorNaviReqBean;
import mc.csst.com.selfchassislibrary.bean.msg.GlobalLocateResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.LayeredMapCmdResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.LiftControlConfigureResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.MarkerOperationGetMarkersResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.NotificationBean;
import mc.csst.com.selfchassislibrary.bean.msg.PoiPatrolResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.SendPathRecordRequestBean;
import mc.csst.com.selfchassislibrary.bean.msg.SensorFeaturesBean;
import mc.csst.com.selfchassislibrary.bean.msg.SensorResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.TagManagerControlResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.TagManagerDeletePoiResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.TagManagerModeResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.VelocityControlResponseBean;
import mc.csst.com.selfchassislibrary.chassis.SelfChassis;
import mc.csst.com.selfchassislibrary.chassis.SelfChassisState;
import mc.csst.com.selfchassislibrary.content.IDContent;
import mc.csst.com.selfchassislibrary.content.ServiceContent;
import mc.csst.com.selfchassislibrary.content.TopicContent;
import mc.csst.com.selfchassislibrary.utils.SelfChassisListenerUtils;
import mc.csst.com.selfchassislibrary.utils.eventbus.SelfChassisEventMsg;
import org.apache.log4j.spi.Configurator;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {
    private static final String RESET_NOTIFY_FOR_CHANGE_BUILDING = "resetForChangeBuilding";
    /* access modifiers changed from: private */
    public static final String TAG = MainPresenter.class.getSimpleName();
    /* access modifiers changed from: private */
    public static volatile float curSpeed;
    private static volatile boolean isRightTopBtnCancelNav = false;
    private static volatile boolean isTimerOpen = false;
    private static volatile long mRefreshMapTime = 0;
    private static volatile RxTimer mRxTimer;
    private static volatile boolean navSwitchNormal = false;
    /* access modifiers changed from: private */
    public static volatile boolean speedFlag;
    /* access modifiers changed from: private */
    public static volatile float targetSpeed;
    private static volatile int tempNavStatus = -1;
    private static volatile String tempNotifyFunc = "init";
    /* access modifiers changed from: private */
    public static volatile float wzSpeed;
    private volatile boolean mHavLabelCamera = false;
    private MainContract.Model mModel = new MainModel();
    private volatile int mTempSoftType = -1;
    private String preDeviceStopState;
    private ScheduledExecutorService scheduledExecutor;
    private Disposable speedDisposable;
    private volatile int tempState = 0;
    private volatile int tryGetFloorDataTime = 0;
    private volatile int tryLoadMapTime = 0;
    private volatile int trySaveMapTime = 0;

    private int getBatteryIconResByBattery(int i, boolean z) {
        return z ? R.mipmap.ic_battery_charge : i <= 10 ? R.mipmap.ic_battery_10 : i <= 20 ? R.mipmap.ic_battery_20 : i <= 30 ? R.mipmap.ic_battery_30 : i <= 40 ? R.mipmap.ic_battery_40 : i <= 50 ? R.mipmap.ic_battery_50 : i <= 60 ? R.mipmap.ic_battery_60 : i <= 70 ? R.mipmap.ic_battery_70 : i <= 80 ? R.mipmap.ic_battery_80 : (i > 90 && i >= 100) ? R.mipmap.ic_battery_100 : R.mipmap.ic_battery_90;
    }

    private int getOnDockIconRes(int i) {
        return i == 1 ? R.mipmap.icon_on_dock : R.mipmap.icon_off_dock;
    }

    static /* synthetic */ boolean lambda$continueToScanMap$32(View view) {
        return false;
    }

    static /* synthetic */ boolean lambda$exitMode$0(View view) {
        return false;
    }

    static /* synthetic */ boolean lambda$exitMode$3(View view) {
        return false;
    }

    static /* synthetic */ boolean lambda$exitMode$5(View view) {
        return false;
    }

    public void zoomIn() {
    }

    public void zoomOut() {
    }

    static /* synthetic */ float access$116(float f) {
        float f2 = curSpeed + f;
        curSpeed = f2;
        return f2;
    }

    static /* synthetic */ float access$124(float f) {
        float f2 = curSpeed - f;
        curSpeed = f2;
        return f2;
    }

    public static MainPresenter newInstance() {
        return new MainPresenter();
    }

    public void detachView() {
        super.detachView();
        cancelTimer();
        resetNotificationState();
        if (SelfChassisListenerUtils.getInstance().getNotificationsListener() != null) {
            SelfChassisListenerUtils.getInstance().getNotificationsListener().clear();
        }
        SelfChassisListenerUtils.getInstance().setTagManagerModeListListener((SelfChassisListenerUtils.OnBaseSelfChassisListener) null);
        SelfChassisListenerUtils.getInstance().setNodeManagerControlListener((SelfChassisListenerUtils.OnBaseSelfChassisListener) null);
    }

    public void attachView(MainContract.View view) {
        super.attachView(view);
    }

    public void createMap() {
        ((MainContract.View) this.mView).showBuildMapDialog();
    }

    public void exitMode() {
        ((MainContract.View) this.mView).selectShapeBtn(0);
        isRightTopBtnCancelNav = false;
        int softType = SoftTypeInfoManager.getInstance().getSoftType();
        dealDisconnectSelfChassis();
        switch (softType) {
            case 1:
            case 6:
                exitModeByNavCancel();
                return;
            case 2:
            case 9:
                SelfChassis.getInstance().serviceNav();
                this.tempState = 1;
                return;
            case 3:
            case 7:
                SoftTypeInfoManager.getInstance().setSoftType(0);
                return;
            case 4:
                ((MainContract.View) this.mView).showEditMapDialog();
                return;
            case 5:
                exitModeByCrossFloorNavCancel();
                return;
            case 8:
                Context context = ContextUtil.getContext();
                ((MainContract.View) this.mView).showConfirmDialog(context.getString(R.string.txt_exit_calibration_mode), context.getString(R.string.txt_whether_to_save_edits), context.getString(R.string.dialog_cancle), context.getString(R.string.txt_exit), $$Lambda$MainPresenter$iDFskVzNMpjU_SIBT7HE_tt_lCY.INSTANCE, $$Lambda$MainPresenter$Tcibv37IdMJqcx7lsGj0yhmOM.INSTANCE);
                return;
            case 10:
                String string = ContextUtil.getContext().getString(R.string.txt_exit_trajectory_drawing_mode);
                String string2 = ContextUtil.getContext().getString(R.string.txt_whether_to_exit);
                String string3 = ContextUtil.getContext().getString(R.string.txt_save_and_exit);
                String string4 = ContextUtil.getContext().getString(R.string.txt_not_save);
                NormalDialog createDialog = NormalDialog.createDialog((Context) this.mView);
                createDialog.setTitle(string);
                createDialog.setMessage(string2);
                createDialog.setBtCancel(string4);
                createDialog.setBtOk(string3);
                createDialog.setShowTopExit(true);
                createDialog.setMessageTxtColor(R.color.black);
                createDialog.setBtnCancleStyle();
                createDialog.setmOnDialogListener(new NormalDialog.OnDialogListener() {
                    public final void isPositiveBtClick(boolean z) {
                        MainPresenter.this.lambda$exitMode$2$MainPresenter(z);
                    }
                });
                createDialog.show();
                return;
            case 11:
                ((MainContract.View) this.mView).showConfirmDialog(ContextUtil.getContext().getString(R.string.txt_exit_collecting_mode), ContextUtil.getContext().getString(R.string.txt_whether_to_exit_collecting_mode), ContextUtil.getContext().getString(R.string.dialog_cancle), ContextUtil.getContext().getString(R.string.txt_exit), $$Lambda$MainPresenter$Wc77ugLJKOTv4nqC2yLUCq3z_A.INSTANCE, $$Lambda$MainPresenter$ZsFtg1WEOxeHdcX84wYnAnC82Y.INSTANCE);
                return;
            case 12:
                ((MainContract.View) this.mView).showConfirmDialog(ContextUtil.getContext().getString(R.string.txt_exit_add_random_point_mode), ContextUtil.getContext().getString(R.string.txt_whether_to_exit_add_random_point_mode), ContextUtil.getContext().getString(R.string.dialog_cancle), ContextUtil.getContext().getString(R.string.txt_exit), $$Lambda$MainPresenter$UyI3NgXCqz2rsnw0oODVOorUUc.INSTANCE, $$Lambda$MainPresenter$Kb7ijlaC3Z7tR3O9iORRVQ0194.INSTANCE);
                return;
            default:
                return;
        }
    }

    static /* synthetic */ boolean lambda$exitMode$1(View view) {
        SelfChassis.getInstance().stopCurrentTag();
        SelfChassis.getInstance().serviceTagManagerModeLocation();
        SoftTypeInfoManager.getInstance().setSoftType(0);
        return false;
    }

    public /* synthetic */ void lambda$exitMode$2$MainPresenter(boolean z) {
        if (z) {
            saveTrajectoryDrawing();
            return;
        }
        SelfChassis.getInstance().sendRecoverLastPathOpParams();
        exitTrajectoryDrawing();
        SoftTypeInfoManager.getInstance().setSoftType(0);
    }

    static /* synthetic */ boolean lambda$exitMode$4(View view) {
        SelfChassis.getInstance().stopBagRecord();
        SoftTypeInfoManager.getInstance().setSoftType(0);
        return false;
    }

    private void saveTrajectoryDrawing() {
        if (TrajectoryModel.getInstance().getIsInDrawing().booleanValue()) {
            MyToastUtils.showShort(ContextUtil.getContext(), ContextUtil.getContext().getString(R.string.text_please_end_drawing));
            return;
        }
        SelfChassis.getInstance().sendPathMarkerSync();
        exitTrajectoryDrawing();
        tryLoadMap("", "");
        SelfChassis.getInstance().serviceSave();
    }

    private void exitTrajectoryDrawing() {
        SelfChassis.getInstance().stopCurrentRecordPath();
        TrajectoryModel.getInstance().setIsInDrawing(false);
        SendPathRecordRequestBean sendPathRecordRequestBean = new SendPathRecordRequestBean();
        SendPathRecordRequestBean.ArgsBean argsBean = new SendPathRecordRequestBean.ArgsBean();
        sendPathRecordRequestBean.setArgs(argsBean);
        argsBean.setPath_name(TrajectoryModel.getInstance().getCurrentPathName());
        argsBean.setCommand(5);
        SelfChassis.getInstance().sendPathRecordParams(sendPathRecordRequestBean);
    }

    public void selectBuild(LayeredMapCmdResponseBean.ValuesBean.ListInfoBean listInfoBean) {
        ((MainContract.View) this.mView).showFloorRv(listInfoBean);
    }

    public void selectFloor(String str, String str2) {
        SelfChassis.getInstance().serviceChangeMap(str2, str);
        tryLoadMap(str, str2);
        tempNotifyFunc = RESET_NOTIFY_FOR_CHANGE_BUILDING;
    }

    private void tryLoadMap(String str, String str2) {
        if (this.mView != null) {
            ((MainContract.View) this.mView).resetData();
            cancelTimer();
            ((MainContract.View) this.mView).closeWaitDialog();
            MyLogUtils.Logd(TAG, "tryLoadMap closeWaitDialog");
            ((MainContract.View) this.mView).showWaitDialog(ContextUtil.getContext().getString(R.string.dialog_loading_map));
            MyLogUtils.Logd(TAG, "tryLoadMap showWaitDialog");
            SelfChassisListenerUtils.getInstance().setNodeManagerControlListener(new SelfChassisListenerUtils.OnBaseSelfChassisListener(str, str2) {
                public final /* synthetic */ String f$1;
                public final /* synthetic */ String f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void onSelfChassisMsg(SelfChassisEventMsg selfChassisEventMsg) {
                    MainPresenter.this.lambda$tryLoadMap$7$MainPresenter(this.f$1, this.f$2, selfChassisEventMsg);
                }
            });
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0035, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ void lambda$tryLoadMap$7$MainPresenter(java.lang.String r8, java.lang.String r9, mc.csst.com.selfchassislibrary.utils.eventbus.SelfChassisEventMsg r10) {
        /*
            r7 = this;
            monitor-enter(r7)     // Catch:{ Exception -> 0x0177 }
            if (r10 == 0) goto L_0x0172
            java.lang.Object r10 = r10.getData()     // Catch:{ all -> 0x0174 }
            mc.csst.com.selfchassislibrary.bean.msg.NodeManagerControlResponseBean r10 = (mc.csst.com.selfchassislibrary.bean.msg.NodeManagerControlResponseBean) r10     // Catch:{ all -> 0x0174 }
            java.lang.String r0 = r10.getId()     // Catch:{ all -> 0x0174 }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ all -> 0x0174 }
            mc.csst.com.selfchassislibrary.bean.msg.NodeManagerControlResponseBean$ValuesBean r1 = r10.getValues()     // Catch:{ all -> 0x0174 }
            int r1 = r1.getResult()     // Catch:{ all -> 0x0174 }
            r2 = 7
            r3 = 1000(0x3e8, double:4.94E-321)
            r5 = 1
            r6 = 0
            if (r2 != r0) goto L_0x009d
            if (r1 == 0) goto L_0x0092
            int r0 = r7.tryLoadMapTime     // Catch:{ all -> 0x0174 }
            r1 = 5
            if (r0 <= r1) goto L_0x0036
            r7.tryLoadMapTime = r6     // Catch:{ all -> 0x0174 }
            mc.csst.com.selfchassis.ui.activity.base.BaseView r8 = r7.mView     // Catch:{ all -> 0x0174 }
            if (r8 == 0) goto L_0x0034
            mc.csst.com.selfchassis.ui.activity.base.BaseView r8 = r7.mView     // Catch:{ all -> 0x0174 }
            mc.csst.com.selfchassis.ui.activity.main.MainContract$View r8 = (mc.csst.com.selfchassis.ui.activity.main.MainContract.View) r8     // Catch:{ all -> 0x0174 }
            r8.closeWaitDialog()     // Catch:{ all -> 0x0174 }
        L_0x0034:
            monitor-exit(r7)     // Catch:{ all -> 0x0174 }
            return
        L_0x0036:
            int r0 = r7.tryLoadMapTime     // Catch:{ all -> 0x0174 }
            int r0 = r0 + r5
            r7.tryLoadMapTime = r0     // Catch:{ all -> 0x0174 }
            java.lang.Thread.sleep(r3)     // Catch:{ all -> 0x0174 }
            java.lang.String r0 = TAG     // Catch:{ all -> 0x0174 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0174 }
            r1.<init>()     // Catch:{ all -> 0x0174 }
            java.lang.String r2 = "SWITCH_MAP 响应失败==>延时1s"
            r1.append(r2)     // Catch:{ all -> 0x0174 }
            mc.csst.com.selfchassislibrary.bean.msg.NodeManagerControlResponseBean$ValuesBean r10 = r10.getValues()     // Catch:{ all -> 0x0174 }
            java.lang.String r10 = r10.getText()     // Catch:{ all -> 0x0174 }
            r1.append(r10)     // Catch:{ all -> 0x0174 }
            java.lang.String r10 = r1.toString()     // Catch:{ all -> 0x0174 }
            com.ciot.base.util.MyLogUtils.Loge(r0, r10)     // Catch:{ all -> 0x0174 }
            boolean r10 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x0174 }
            if (r10 == 0) goto L_0x0071
            mc.csst.com.selfchassislibrary.chassis.SelfChassis r8 = mc.csst.com.selfchassislibrary.chassis.SelfChassis.getInstance()     // Catch:{ all -> 0x0174 }
            r8.sendGetMap()     // Catch:{ all -> 0x0174 }
            mc.csst.com.selfchassislibrary.chassis.SelfChassis r8 = mc.csst.com.selfchassislibrary.chassis.SelfChassis.getInstance()     // Catch:{ all -> 0x0174 }
            r8.getCurrentMap()     // Catch:{ all -> 0x0174 }
            goto L_0x0078
        L_0x0071:
            mc.csst.com.selfchassislibrary.chassis.SelfChassis r10 = mc.csst.com.selfchassislibrary.chassis.SelfChassis.getInstance()     // Catch:{ all -> 0x0174 }
            r10.serviceChangeMap(r9, r8)     // Catch:{ all -> 0x0174 }
        L_0x0078:
            java.lang.String r8 = TAG     // Catch:{ all -> 0x0174 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x0174 }
            r9.<init>()     // Catch:{ all -> 0x0174 }
            java.lang.String r10 = "tryLoadMap createIntervalTimer 重新获取一次 "
            r9.append(r10)     // Catch:{ all -> 0x0174 }
            int r10 = r7.tryLoadMapTime     // Catch:{ all -> 0x0174 }
            r9.append(r10)     // Catch:{ all -> 0x0174 }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x0174 }
            com.ciot.base.util.MyLogUtils.Logd(r8, r9)     // Catch:{ all -> 0x0174 }
            goto L_0x0172
        L_0x0092:
            r7.tryLoadMapTime = r6     // Catch:{ all -> 0x0174 }
            mc.csst.com.selfchassislibrary.chassis.SelfChassis r8 = mc.csst.com.selfchassislibrary.chassis.SelfChassis.getInstance()     // Catch:{ all -> 0x0174 }
            r8.sendPathGetParams()     // Catch:{ all -> 0x0174 }
            goto L_0x0172
        L_0x009d:
            r8 = 3
            if (r8 != r0) goto L_0x0148
            mc.csst.com.selfchassis.utils.SoftTypeInfoManager r8 = mc.csst.com.selfchassis.utils.SoftTypeInfoManager.getInstance()     // Catch:{ all -> 0x0174 }
            int r8 = r8.getSoftType()     // Catch:{ all -> 0x0174 }
            r9 = 4
            r0 = 10
            r2 = 2
            if (r8 == r9) goto L_0x00c2
            mc.csst.com.selfchassis.utils.SoftTypeInfoManager r8 = mc.csst.com.selfchassis.utils.SoftTypeInfoManager.getInstance()     // Catch:{ all -> 0x0174 }
            int r8 = r8.getSoftType()     // Catch:{ all -> 0x0174 }
            if (r8 == r0) goto L_0x00c2
            mc.csst.com.selfchassis.utils.SoftTypeInfoManager r8 = mc.csst.com.selfchassis.utils.SoftTypeInfoManager.getInstance()     // Catch:{ all -> 0x0174 }
            int r8 = r8.getSoftType()     // Catch:{ all -> 0x0174 }
            if (r8 != r2) goto L_0x0172
        L_0x00c2:
            if (r1 == 0) goto L_0x0126
            int r8 = r7.trySaveMapTime     // Catch:{ all -> 0x0174 }
            if (r8 <= r2) goto L_0x00e0
            r7.trySaveMapTime = r6     // Catch:{ all -> 0x0174 }
            android.content.Context r8 = com.ciot.base.util.ContextUtil.getContext()     // Catch:{ all -> 0x0174 }
            android.content.Context r9 = com.ciot.base.util.ContextUtil.getContext()     // Catch:{ all -> 0x0174 }
            android.content.res.Resources r9 = r9.getResources()     // Catch:{ all -> 0x0174 }
            r0 = 2131755758(0x7f1002ee, float:1.9142404E38)
            java.lang.String r9 = r9.getString(r0)     // Catch:{ all -> 0x0174 }
            mc.csst.com.selfchassis.utils.MyToastUtils.showShort(r8, r9)     // Catch:{ all -> 0x0174 }
        L_0x00e0:
            java.lang.Thread.sleep(r3)     // Catch:{ all -> 0x0174 }
            java.lang.String r8 = TAG     // Catch:{ all -> 0x0174 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x0174 }
            r9.<init>()     // Catch:{ all -> 0x0174 }
            java.lang.String r0 = "SAVE_MAP_INFO 响应失败==>延时1s"
            r9.append(r0)     // Catch:{ all -> 0x0174 }
            mc.csst.com.selfchassislibrary.bean.msg.NodeManagerControlResponseBean$ValuesBean r10 = r10.getValues()     // Catch:{ all -> 0x0174 }
            java.lang.String r10 = r10.getText()     // Catch:{ all -> 0x0174 }
            r9.append(r10)     // Catch:{ all -> 0x0174 }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x0174 }
            com.ciot.base.util.MyLogUtils.Loge(r8, r9)     // Catch:{ all -> 0x0174 }
            mc.csst.com.selfchassislibrary.chassis.SelfChassis r8 = mc.csst.com.selfchassislibrary.chassis.SelfChassis.getInstance()     // Catch:{ all -> 0x0174 }
            r8.serviceSave()     // Catch:{ all -> 0x0174 }
            int r8 = r7.trySaveMapTime     // Catch:{ all -> 0x0174 }
            int r8 = r8 + r5
            r7.trySaveMapTime = r8     // Catch:{ all -> 0x0174 }
            java.lang.String r8 = TAG     // Catch:{ all -> 0x0174 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x0174 }
            r9.<init>()     // Catch:{ all -> 0x0174 }
            java.lang.String r10 = "SAVE_MAP_INFO 重新获取一次 "
            r9.append(r10)     // Catch:{ all -> 0x0174 }
            int r10 = r7.tryLoadMapTime     // Catch:{ all -> 0x0174 }
            r9.append(r10)     // Catch:{ all -> 0x0174 }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x0174 }
            com.ciot.base.util.MyLogUtils.Logd(r8, r9)     // Catch:{ all -> 0x0174 }
            goto L_0x0172
        L_0x0126:
            mc.csst.com.selfchassislibrary.chassis.SelfChassis r8 = mc.csst.com.selfchassislibrary.chassis.SelfChassis.getInstance()     // Catch:{ all -> 0x0174 }
            r8.getCurrentMap()     // Catch:{ all -> 0x0174 }
            mc.csst.com.selfchassis.utils.SoftTypeInfoManager r8 = mc.csst.com.selfchassis.utils.SoftTypeInfoManager.getInstance()     // Catch:{ all -> 0x0174 }
            int r8 = r8.getSoftType()     // Catch:{ all -> 0x0174 }
            if (r8 == r0) goto L_0x013e
            mc.csst.com.selfchassis.utils.SoftTypeInfoManager r8 = mc.csst.com.selfchassis.utils.SoftTypeInfoManager.getInstance()     // Catch:{ all -> 0x0174 }
            r8.getSoftType()     // Catch:{ all -> 0x0174 }
        L_0x013e:
            mc.csst.com.selfchassis.utils.SoftTypeInfoManager r8 = mc.csst.com.selfchassis.utils.SoftTypeInfoManager.getInstance()     // Catch:{ all -> 0x0174 }
            r8.setSoftType(r6)     // Catch:{ all -> 0x0174 }
            r7.trySaveMapTime = r6     // Catch:{ all -> 0x0174 }
            goto L_0x0172
        L_0x0148:
            if (r5 != r0) goto L_0x0172
            if (r1 == 0) goto L_0x0169
            r8 = 102(0x66, float:1.43E-43)
            if (r1 != r8) goto L_0x0151
            goto L_0x0169
        L_0x0151:
            mc.csst.com.selfchassis.ui.activity.base.BaseView r8 = r7.mView     // Catch:{ all -> 0x0174 }
            mc.csst.com.selfchassis.ui.activity.main.MainContract$View r8 = (mc.csst.com.selfchassis.ui.activity.main.MainContract.View) r8     // Catch:{ all -> 0x0174 }
            r9 = 2131755634(0x7f100272, float:1.9142153E38)
            java.lang.String r9 = com.blankj.utilcode.util.StringUtils.getString(r9)     // Catch:{ all -> 0x0174 }
            mc.csst.com.selfchassis.utils.DeploymentToolUtils r10 = mc.csst.com.selfchassis.utils.DeploymentToolUtils.getInstance()     // Catch:{ all -> 0x0174 }
            java.lang.String r10 = r10.getManagerControlPrompt(r1)     // Catch:{ all -> 0x0174 }
            r0 = 0
            r8.showConfirmDialog(r9, r10, r0)     // Catch:{ all -> 0x0174 }
            goto L_0x0172
        L_0x0169:
            mc.csst.com.selfchassis.utils.SoftTypeInfoManager r8 = mc.csst.com.selfchassis.utils.SoftTypeInfoManager.getInstance()     // Catch:{ all -> 0x0174 }
            r9 = 9
            r8.setSoftType(r9)     // Catch:{ all -> 0x0174 }
        L_0x0172:
            monitor-exit(r7)     // Catch:{ all -> 0x0174 }
            goto L_0x01a0
        L_0x0174:
            r8 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x0174 }
            throw r8     // Catch:{ Exception -> 0x0177 }
        L_0x0177:
            r8 = move-exception
            r8.printStackTrace()
            java.lang.String r9 = TAG
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r0 = "setNodeManagerControlListener==>"
            r10.append(r0)
            java.lang.String r8 = android.util.Log.getStackTraceString(r8)
            r10.append(r8)
            java.lang.String r8 = r10.toString()
            com.ciot.base.util.MyLogUtils.Loge(r9, r8)
            mc.csst.com.selfchassis.ui.activity.base.BaseView r8 = r7.mView
            if (r8 == 0) goto L_0x01a0
            mc.csst.com.selfchassis.ui.activity.base.BaseView r8 = r7.mView
            mc.csst.com.selfchassis.ui.activity.main.MainContract$View r8 = (mc.csst.com.selfchassis.ui.activity.main.MainContract.View) r8
            r8.closeWaitDialog()
        L_0x01a0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: mc.csst.com.selfchassis.ui.activity.main.MainPresenter.lambda$tryLoadMap$7$MainPresenter(java.lang.String, java.lang.String, mc.csst.com.selfchassislibrary.utils.eventbus.SelfChassisEventMsg):void");
    }

    public void changeBuilding() {
        resetExpandedView();
        ((MainContract.View) this.mView).showChangeBuildingView(!((MainContract.View) this.mView).isShowChangeBuildingView());
        SelfChassis.getInstance().serviceLayeredMapCmd();
    }

    public void buildManage() {
        resetExpandedView();
        ((MainContract.View) this.mView).showBuildManageView(!((MainContract.View) this.mView).isShowBuildPointManageView());
        SelfChassis.getInstance().serviceLayeredMapCmd();
    }

    public void changeSoftType(int i) {
        if (this.mView != null) {
            ((MainContract.View) this.mView).showSoftTypeView(i);
            if (i == 0) {
                softTypeNormal();
            }
        }
    }

    private void softTypeNormal() {
        ShowSelfChassisBean.getInstance().setExitBtnCanClick(true);
        isRightTopBtnCancelNav = false;
    }

    public void pointManage() {
        MyLogUtils.Logd(TAG, "zzy111");
        resetExpandedView();
        ((MainContract.View) this.mView).showPointManageView(!((MainContract.View) this.mView).isShowPointManageView());
    }

    public void addPoint() {
        ((MainContract.View) this.mView).showAddMarkDialog();
    }

    public void addLineTrackingPoint(AddMarkDialog.OnInsertMarkerListener onInsertMarkerListener) {
        ((MainContract.View) this.mView).showLineTrackingAddMarkDialog(onInsertMarkerListener);
    }

    public void showOtherFloorPoint() {
        String string = MySpUtils.getInstance().getString(SpConstant.ELEVATOR_PROJECT_INFO);
        if (!App.getInstance().isOpenLife() || TextUtils.isEmpty(string)) {
            showLiftSetDialog();
            return;
        }
        SelfChassisListenerUtils.getInstance().setLiftConfigureListener(new SelfChassisListenerUtils.OnBaseSelfChassisListener() {
            public final void onSelfChassisMsg(SelfChassisEventMsg selfChassisEventMsg) {
                MainPresenter.this.lambda$showOtherFloorPoint$9$MainPresenter(selfChassisEventMsg);
            }
        });
        SelfChassis.getInstance().getLiftControlConfigure();
    }

    public /* synthetic */ void lambda$showOtherFloorPoint$9$MainPresenter(SelfChassisEventMsg selfChassisEventMsg) {
        SelfChassisListenerUtils.getInstance().setLiftConfigureListener((SelfChassisListenerUtils.OnBaseSelfChassisListener) null);
        ThreadUtils.runOnUiThread(new Runnable(selfChassisEventMsg) {
            public final /* synthetic */ SelfChassisEventMsg f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                MainPresenter.this.lambda$showOtherFloorPoint$8$MainPresenter(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$showOtherFloorPoint$8$MainPresenter(SelfChassisEventMsg selfChassisEventMsg) {
        if (selfChassisEventMsg != null) {
            LiftControlConfigureResponseBean liftControlConfigureResponseBean = (LiftControlConfigureResponseBean) selfChassisEventMsg.getData();
            if (!TextUtils.equals(liftControlConfigureResponseBean.getId(), IDContent.SERVICE_GET_LIFT_CONFIGURE)) {
                return;
            }
            if (liftControlConfigureResponseBean.isResult().booleanValue()) {
                if (liftControlConfigureResponseBean.getValues().getResult().intValue() == 10000) {
                    showLiftSetDialog();
                } else {
                    ((MainContract.View) this.mView).showSwitchPointDialog();
                }
            } else if (TextUtils.equals(DeploymentToolConstant.CHECK_LIFT_SERVER_OPEN, liftControlConfigureResponseBean.getInfo())) {
                showLiftSetDialog();
            }
        }
    }

    private void showLiftSetDialog() {
        ((MainContract.View) this.mView).showConfirmDialog(ContextUtil.getContext().getString(R.string.across_floor_navigation), ContextUtil.getContext().getString(R.string.tip_check_open_lift), ContextUtil.getContext().getString(R.string.txt_go_to_settings), ContextUtil.getContext().getString(R.string.dialog_cancle), new ConfirmDialog.OnDialogButtonClickListener() {
            public final boolean onClick(View view) {
                return MainPresenter.this.lambda$showLiftSetDialog$10$MainPresenter(view);
            }
        }, (ConfirmDialog.OnDialogButtonClickListener) null);
    }

    public /* synthetic */ boolean lambda$showLiftSetDialog$10$MainPresenter(View view) {
        ((MainContract.View) this.mView).showSetActivity();
        return false;
    }

    public void showCoverage() {
        resetExpandedView();
        ((MainContract.View) this.mView).showCoverageView(!((MainContract.View) this.mView).isShowCoverageView());
    }

    public void showPositioning(Activity activity) {
        resetExpandedView();
        String string = ContextUtil.getContext().getString(R.string.txt_choose_self_positioning_method);
        String string2 = ContextUtil.getContext().getString(R.string.txt_regional_self_positioning);
        String string3 = ContextUtil.getContext().getString(R.string.global_locate);
        NormalDialog createDialog = NormalDialog.createDialog(activity);
        createDialog.setTitle((CharSequence) null);
        createDialog.setMessage(string);
        createDialog.setBtCancel(string3);
        createDialog.setBtOk(string2);
        createDialog.setShowTopExit(true);
        createDialog.setMessageTxtColor(R.color.black);
        createDialog.setBtnCancleStyle();
        createDialog.setmOnDialogListener(new NormalDialog.OnDialogListener() {
            public final void isPositiveBtClick(boolean z) {
                MainPresenter.this.lambda$showPositioning$12$MainPresenter(z);
            }
        });
        createDialog.show();
    }

    public /* synthetic */ void lambda$showPositioning$12$MainPresenter(boolean z) {
        if (z) {
            SoftTypeInfoManager.getInstance().setSoftType(7);
            return;
        }
        SelfChassisListenerUtils.getInstance().setGlobalLocateListener(new SelfChassisListenerUtils.OnBaseSelfChassisListener() {
            public final void onSelfChassisMsg(SelfChassisEventMsg selfChassisEventMsg) {
                MainPresenter.this.lambda$showPositioning$11$MainPresenter(selfChassisEventMsg);
            }
        });
        ((MainContract.View) this.mView).showWaitDialog(ContextUtil.getContext().getString(R.string.tip_global_locate));
        SelfChassis.getInstance().serviceGlobalLocate();
    }

    public /* synthetic */ void lambda$showPositioning$11$MainPresenter(SelfChassisEventMsg selfChassisEventMsg) {
        ((MainContract.View) this.mView).closeWaitDialog();
        if (selfChassisEventMsg != null) {
            if (((GlobalLocateResponseBean) selfChassisEventMsg.getData()).isResult()) {
                MyToastUtils.showShort(ContextUtil.getContext(), ContextUtil.getContext().getString(R.string.tip_global_locate_success));
            } else {
                MyToastUtils.showShort(ContextUtil.getContext(), ContextUtil.getContext().getString(R.string.tip_global_locate_faile));
            }
        }
        SelfChassisListenerUtils.getInstance().setGlobalLocateListener((SelfChassisListenerUtils.OnBaseSelfChassisListener) null);
    }

    public void showCalibration() {
        resetExpandedView();
        ((MainContract.View) this.mView).showChooseMarkerDialog();
    }

    public void showEditMap() {
        resetExpandedView();
        SoftTypeInfoManager.getInstance().setSoftType(4);
    }

    public void showTrajectoryDrawing() {
        resetExpandedView();
        SoftTypeInfoManager.getInstance().setSoftType(10);
    }

    public void showAddRandomPoint() {
        resetExpandedView();
        SoftTypeInfoManager.getInstance().setSoftType(12);
    }

    public void showCollectMode() {
        resetExpandedView();
        SoftTypeInfoManager.getInstance().setSoftType(11);
    }

    public void showSetting() {
        resetExpandedView();
        jump2Set();
    }

    private void resetExpandedView() {
        ((MainContract.View) this.mView).showChangeBuildingView(false);
        ((MainContract.View) this.mView).showBuildManageView(false);
        ((MainContract.View) this.mView).showPointManageView(false);
        ((MainContract.View) this.mView).showCoverageView(false);
        ((MainContract.View) this.mView).showTopMenuView(false);
    }

    public void selectLine() {
        ((MainContract.View) this.mView).selectShapeBtn(1);
    }

    public void selectRectangle() {
        ((MainContract.View) this.mView).selectShapeBtn(2);
    }

    public void selectSmallBrush() {
        ((MainContract.View) this.mView).selectBrushBtn(1);
    }

    public void selectMediumBrush() {
        ((MainContract.View) this.mView).selectBrushBtn(2);
    }

    public void selectBigBrush() {
        ((MainContract.View) this.mView).selectBrushBtn(3);
    }

    private void jump2Set() {
        ((MainContract.View) this.mView).showSetActivity();
    }

    public void changeSoftTypeNav() {
        SoftTypeInfoManager.getInstance().setSoftType(1);
    }

    public ShowSelfChassisBean chassisState(SelfChassisState selfChassisState) {
        String str;
        String str2;
        boolean isHardStop = selfChassisState.isHardStop();
        boolean isSoftStop = selfChassisState.isSoftStop();
        float x = selfChassisState.getX();
        float y = selfChassisState.getY();
        float theta = selfChassisState.getTheta();
        int controlStatus = selfChassisState.getControlStatus();
        int navStatus = selfChassisState.getNavStatus();
        int battery = selfChassisState.getBattery();
        int onDock = selfChassisState.getOnDock();
        String currentGoalName = selfChassisState.getCurrentGoalName();
        ShowSelfChassisBean.getInstance().setBuild(selfChassisState.getBuildingName());
        ShowSelfChassisBean.getInstance().setFloor(selfChassisState.getFloorName());
        ShowSelfChassisBean.getInstance().setX(String.valueOf(x));
        ShowSelfChassisBean.getInstance().setY(String.valueOf(y));
        ShowSelfChassisBean.getInstance().setT(String.valueOf(theta));
        ShowSelfChassisBean.getInstance().setSoftStop(isSoftStop);
        ShowSelfChassisBean.getInstance().setOnDock(onDock);
        App.isScram = true;
        if (isHardStop && isSoftStop) {
            str = ContextUtil.getContext().getString(R.string.emergency_stop);
        } else if (isHardStop) {
            str = ContextUtil.getContext().getString(R.string.hard_stop);
        } else if (isSoftStop) {
            str = ContextUtil.getContext().getString(R.string.soft_emergency_stop);
        } else {
            App.isScram = false;
            str = ContextUtil.getContext().getString(R.string.normal);
        }
        String str3 = this.preDeviceStopState;
        if (str3 != null && !TextUtils.equals(str3, str) && !App.isScram) {
            resetNotificationState();
        }
        this.preDeviceStopState = str;
        if (controlStatus == 20) {
            if (this.mTempSoftType != SoftTypeInfoManager.getInstance().getSoftType()) {
                SoftTypeInfoManager.getInstance().setSoftType(2);
            }
            str2 = ContextUtil.getContext().getString(R.string.sport_state_scan);
        } else {
            str2 = "";
        }
        if (controlStatus == 25) {
            if (this.mTempSoftType != SoftTypeInfoManager.getInstance().getSoftType()) {
                SoftTypeInfoManager.getInstance().setSoftType(9);
            }
            str2 = ContextUtil.getContext().getString(R.string.sport_state_continue_scan);
        } else if (controlStatus == 30) {
            if (this.tempState == 1) {
                SoftTypeInfoManager.getInstance().setSoftType(0);
            }
            this.tempState = 0;
            str2 = NavStateType.getDesc(navStatus);
            exitNavOperate(navStatus);
            if (NavStateType.isEnableExitBtn(navStatus)) {
                ShowSelfChassisBean.getInstance().setExitBtnCanClick(false);
            }
            showNavErrorDialog(navStatus);
            if (!App.isMultiNaving()) {
                showNavBottomTips(navStatus, str2, currentGoalName);
            }
            enterNavOperate(navStatus, str2);
            tempNavStatus = navStatus;
        } else if (controlStatus == 99) {
            if (this.mView != null) {
                ((MainContract.View) this.mView).closeWaitDialog();
            }
            this.tempState = 0;
            str2 = ContextUtil.getContext().getString(R.string.sport_state_abnormal);
        }
        this.mTempSoftType = SoftTypeInfoManager.getInstance().getSoftType();
        int batteryIconResByBattery = getBatteryIconResByBattery(battery, selfChassisState.isCharging());
        int onDockIconRes = getOnDockIconRes(onDock);
        ShowSelfChassisBean.getInstance().setBatteryIconRes(batteryIconResByBattery);
        ShowSelfChassisBean.getInstance().setOnDockIconRes(onDockIconRes);
        ShowSelfChassisBean.getInstance().setDeviceState(str);
        if (!App.isMultiNaving()) {
            ShowSelfChassisBean.getInstance().setSportState(str2);
        }
        ShowSelfChassisBean.getInstance().setBattery(battery);
        ShowSelfChassisBean instance = ShowSelfChassisBean.getInstance();
        instance.setBatteryPercent(battery + "%");
        return ShowSelfChassisBean.getInstance();
    }

    /* access modifiers changed from: private */
    public void showNavBottomTips(int i, String str, String str2) {
        if (this.mView != null) {
            if (SoftTypeInfoManager.getInstance().getSoftType() == 1) {
                if (!TextUtils.isEmpty(str2) && NavStateType.isCommonNavigating(i)) {
                    str = String.format(ContextUtil.getContext().getString(R.string.tip_nav_to_point), new Object[]{str2});
                }
                ((MainContract.View) this.mView).showTipsBlack(str + "（" + i + "）");
            } else if (SoftTypeInfoManager.getInstance().getSoftType() == 5) {
                String desc = BottomNavTipsType.getDesc(i);
                ((MainContract.View) this.mView).showTipsBlack(desc + "（" + i + "）");
            }
        }
    }

    public ShowSelfChassisBean localizationConfidence(float f) {
        int i = (int) (f * 100.0f);
        ShowSelfChassisBean.getInstance().setMatching((float) i);
        ShowSelfChassisBean instance = ShowSelfChassisBean.getInstance();
        instance.setMatchingPercent(i + "%");
        return ShowSelfChassisBean.getInstance();
    }

    public void controlDirectionStart(float f, float f2) {
        speedFlag = true;
        wzSpeed = f;
        targetSpeed = f2;
        if (this.speedDisposable == null) {
            this.speedDisposable = Observable.interval(100, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io()).subscribe(new Consumer<Long>() {
                public void accept(Long l) {
                    synchronized (this) {
                        String access$000 = MainPresenter.TAG;
                        MyLogUtils.Logd(access$000, "count: " + l);
                        if (App.isScram) {
                            float unused = MainPresenter.curSpeed = 0.0f;
                        }
                        if (!MainPresenter.speedFlag) {
                            String access$0002 = MainPresenter.TAG;
                            MyLogUtils.Logd(access$0002, "speedDisposable 停止运动：" + MainPresenter.curSpeed);
                            MainPresenter.this.controlDirectionStop();
                        }
                        if (MainPresenter.targetSpeed > 0.0f) {
                            if (MainPresenter.curSpeed <= MainPresenter.targetSpeed) {
                                String access$0003 = MainPresenter.TAG;
                                MyLogUtils.Logd(access$0003, "wz curSpeed 前进加速：" + MainPresenter.curSpeed);
                                MainPresenter.access$116(0.025f);
                                if (MainPresenter.curSpeed > MainPresenter.targetSpeed) {
                                    float unused2 = MainPresenter.curSpeed = MainPresenter.targetSpeed;
                                }
                                SelfChassis.getInstance().setVelocity(MainPresenter.wzSpeed * 0.8f, MainPresenter.curSpeed);
                            } else {
                                String access$0004 = MainPresenter.TAG;
                                MyLogUtils.Logd(access$0004, "wz curSpeed 前进减速：" + MainPresenter.curSpeed);
                                MainPresenter.access$124(0.025f);
                                if (MainPresenter.curSpeed < MainPresenter.targetSpeed) {
                                    float unused3 = MainPresenter.curSpeed = MainPresenter.targetSpeed;
                                }
                                SelfChassis.getInstance().setVelocity(MainPresenter.wzSpeed * 0.8f, MainPresenter.curSpeed);
                            }
                            String access$0005 = MainPresenter.TAG;
                            MyLogUtils.Logd(access$0005, "wz curSpeed 前进：" + MainPresenter.curSpeed + ";wzSpeed * 0.8f:" + (MainPresenter.wzSpeed * 0.8f) + "；targetSpeed：" + MainPresenter.targetSpeed + MainPresenter.speedFlag);
                        } else {
                            if (MainPresenter.curSpeed >= MainPresenter.targetSpeed) {
                                String access$0006 = MainPresenter.TAG;
                                MyLogUtils.Logd(access$0006, "wz curSpeed 后退加速：" + MainPresenter.curSpeed);
                                MainPresenter.access$124(0.025f);
                                if (MainPresenter.curSpeed < MainPresenter.targetSpeed) {
                                    float unused4 = MainPresenter.curSpeed = MainPresenter.targetSpeed;
                                }
                                SelfChassis.getInstance().setVelocity(MainPresenter.wzSpeed * 0.8f, MainPresenter.curSpeed);
                            } else {
                                String access$0007 = MainPresenter.TAG;
                                MyLogUtils.Logd(access$0007, "wz curSpeed 后退减速：" + MainPresenter.curSpeed);
                                MainPresenter.access$116(0.025f);
                                if (MainPresenter.curSpeed < MainPresenter.targetSpeed) {
                                    float unused5 = MainPresenter.curSpeed = MainPresenter.targetSpeed;
                                }
                                SelfChassis.getInstance().setVelocity(MainPresenter.wzSpeed * 0.8f, MainPresenter.curSpeed);
                            }
                            String access$0008 = MainPresenter.TAG;
                            MyLogUtils.Logd(access$0008, "wz curSpeed 后退：" + MainPresenter.curSpeed + ";wzSpeed * 0.8f:" + (MainPresenter.wzSpeed * 0.8f) + "；targetSpeed：" + MainPresenter.targetSpeed + MainPresenter.speedFlag);
                        }
                    }
                }
            }, new Consumer() {
                public final void accept(Object obj) {
                    MainPresenter.this.lambda$controlDirectionStart$13$MainPresenter((Throwable) obj);
                }
            });
            DisposeUtil.INSTANCE.addSubscription(this.speedDisposable);
        }
    }

    public /* synthetic */ void lambda$controlDirectionStart$13$MainPresenter(Throwable th) throws Exception {
        th.printStackTrace();
        controlDirectionStop();
        String str = TAG;
        MyLogUtils.Loge(str, "wz curSpeed error：" + Log.getStackTraceString(th));
    }

    public void controlDirectionStop() {
        speedFlag = false;
        curSpeed = 0.0f;
        targetSpeed = 0.0f;
        wzSpeed = 0.0f;
        Disposable disposable = this.speedDisposable;
        if (disposable != null && !disposable.isDisposed()) {
            this.speedDisposable.dispose();
        }
        this.speedDisposable = null;
        resetNotificationState();
        SelfChassis.getInstance().setVelocity(0.0f, 0.0f);
    }

    public void placeCalibration(String str) {
        SelfChassis.getInstance().servicePoiInit(str);
    }

    public void stopAddOrSubtract() {
        ScheduledExecutorService scheduledExecutorService = this.scheduledExecutor;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdownNow();
            this.scheduledExecutor = null;
        }
    }

    public void contend(boolean z) {
        if (z) {
            GatewayTool.getInstance().getGateway(true);
            GatewayTool.getInstance().getGateway(false);
            NavSettingApiUtil.getInstance().initListener();
            SelfChassis.getInstance().getNaviSetting();
            SelfChassis.getInstance().initNotification();
            SelfChassis.getInstance().initGetPose();
            SelfChassis.getInstance().initLaserData();
            if (LayerDataUtils.getInstance().getShowPointCloudUpCam()) {
                SelfChassis.getInstance().initUpCamData();
            }
            if (LayerDataUtils.getInstance().getShowPointCloudDownCam()) {
                SelfChassis.getInstance().initDownCamData();
            }
            if (LayerDataUtils.getInstance().getShowTravelPath()) {
                SelfChassis.getInstance().initGetPath();
            }
            SelfChassis.getInstance().sendGetRobotStatus();
            SelfChassis.getInstance().initNaviStatus();
            SelfChassis.getInstance().initStartPathMarkerSync();
            SelfChassis.getInstance().initStartPathFollowerCancel();
            SelfChassis.getInstance().initChargeServerResult();
            SelfChassis.getInstance().initPoseTopic();
            SelfChassis.getInstance().initMobileBaseSensorsCore();
            SelfChassis.getInstance().initVelocity();
            SelfChassis.getInstance().initSendGoal();
            SelfChassis.getInstance().initCancelGoalMsg();
            SelfChassis.getInstance().initSetPose();
            SelfChassis.getInstance().initSetHomePose();
            SelfChassis.getInstance().initSoftStop();
            SelfChassis.getInstance().initSetVirtualWalls();
            SelfChassis.getInstance().initAppendVirtualWalls();
            SelfChassis.getInstance().initSetWayPoint();
            SelfChassis.getInstance().initAppendWayPoint();
            SelfChassis.getInstance().initInsertCurrentPoseMarker();
            SelfChassis.getInstance().initSetLayeredMapManagerPencilOp();
            SelfChassis.getInstance().initGetCurrentMap();
            SelfChassis.getInstance().initApriltagsBuffer();
            SelfChassis.getInstance().initLocalizationConfidence();
            SelfChassis.getInstance().initLaserSafetyControllerConfidenceThreshold();
            SelfChassis.getInstance().initCrossFloorNaviTopic();
            SelfChassis.getInstance().initLiftControlStatus();
            SelfChassis.getInstance().initAppendArea();
            SelfChassis.getInstance().initSetArea();
            SelfChassis.getInstance().initLiftControlForceCancelTopic();
            SystemClock.sleep(500);
            SelfChassis.getInstance().serviceGetSensors();
            SystemClock.sleep(500);
            initWayPointStateTopic();
            SystemClock.sleep(500);
            SelfChassis.getInstance().sendGetMap();
            SelfChassis.getInstance().sendGetMatchDegreeMap();
            SelfChassis.getInstance().getCurrentMap();
            tryLoadMap("", "");
            SystemClock.sleep(500);
            SelfChassis.getInstance().serviceLayeredMapCmd();
            SystemClock.sleep(500);
            initLiftConfig();
            setVisualLabelListListener();
            setSensorsListener();
            SelfChassis.getInstance().initDoorLengthTopic();
            SelfChassis.getInstance().initGateLengthTopic();
            SelfChassis.getInstance().serviceGetVelocity();
            SelfChassis.getInstance().setVelocityControlListener($$Lambda$MainPresenter$VJpNPltTy5l8OhESgwtLOVbALX0.INSTANCE);
            SystemClock.sleep(500);
            SelfChassis.getInstance().sendPathGetParams();
            SelfChassis.getInstance().initUpgradeDownloadStatus();
            SelfChassis.getInstance().initGetRobotList();
            SelfChassis.getInstance().configStationServer(0, (String) null, (Boolean) null, "contend");
        }
    }

    static /* synthetic */ void lambda$contend$14(SelfChassisEventMsg selfChassisEventMsg) {
        VelocityControlResponseBean velocityControlResponseBean;
        if (TextUtils.equals(selfChassisEventMsg.getCode(), ServiceContent.VELOCITY_CONTROL)) {
            if (selfChassisEventMsg.getData() != null && (selfChassisEventMsg.getData() instanceof VelocityControlResponseBean) && (velocityControlResponseBean = (VelocityControlResponseBean) selfChassisEventMsg.getData()) != null && TextUtils.equals(velocityControlResponseBean.getId(), IDContent.VelocityControl.GET_SPEED) && velocityControlResponseBean.getResult().booleanValue()) {
                int i = -2;
                try {
                    i = Integer.parseInt(velocityControlResponseBean.getValues().getInfo());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (i == 0 || i == 3 || i == 6) {
                    App.getInstance().setSpeedLevel(0);
                } else if (i == 1 || i == 4 || i == 7) {
                    App.getInstance().setSpeedLevel(1);
                } else if (i == 2 || i == 5 || i == 8) {
                    App.getInstance().setSpeedLevel(2);
                }
            }
            SelfChassis.getInstance().setVelocityControlListener((SelfChassisListenerUtils.OnBaseSelfChassisListener) null);
        }
    }

    private void initWayPointStateTopic() {
        SelfChassis.getInstance().initWayPointStateTopic();
        SelfChassisListenerUtils.getInstance().setWaypointStatusListener(new SelfChassisListenerUtils.OnBaseSelfChassisListener() {
            /* JADX WARNING: Removed duplicated region for block: B:22:0x009f A[Catch:{ Exception -> 0x00f9 }] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onSelfChassisMsg(mc.csst.com.selfchassislibrary.utils.eventbus.SelfChassisEventMsg r8) {
                /*
                    r7 = this;
                    if (r8 == 0) goto L_0x0115
                    java.lang.Object r8 = r8.getData()     // Catch:{ Exception -> 0x00f9 }
                    mc.csst.com.selfchassislibrary.bean.WaypointStateBean r8 = (mc.csst.com.selfchassislibrary.bean.WaypointStateBean) r8     // Catch:{ Exception -> 0x00f9 }
                    mc.csst.com.selfchassislibrary.bean.WaypointStateBean$MsgBean r8 = r8.getMsg()     // Catch:{ Exception -> 0x00f9 }
                    r8.getPatrol_mode()     // Catch:{ Exception -> 0x00f9 }
                    int r0 = r8.getPatrol_status()     // Catch:{ Exception -> 0x00f9 }
                    r8.getNum_total()     // Catch:{ Exception -> 0x00f9 }
                    int r1 = r8.getNav_status()     // Catch:{ Exception -> 0x00f9 }
                    java.lang.String r2 = r8.getCurrent_goal_name()     // Catch:{ Exception -> 0x00f9 }
                    java.util.List r3 = r8.getPoi_patrol_list()     // Catch:{ Exception -> 0x00f9 }
                    mc.csst.com.selfchassis.ui.activity.main.MainPresenter r4 = mc.csst.com.selfchassis.ui.activity.main.MainPresenter.this     // Catch:{ Exception -> 0x00f9 }
                    mc.csst.com.selfchassis.ui.activity.base.BaseView r4 = r4.mView     // Catch:{ Exception -> 0x00f9 }
                    if (r4 == 0) goto L_0x0115
                    r4 = 4
                    java.lang.String r5 = ""
                    java.lang.String r6 = "multipoint_nav_points_list"
                    if (r0 == r4) goto L_0x00d9
                    r4 = 680(0x2a8, float:9.53E-43)
                    if (r0 != r4) goto L_0x0037
                    goto L_0x00d9
                L_0x0037:
                    java.lang.String r0 = mc.csst.com.selfchassis.utils.enumbs.NavStateType.getDesc(r1)     // Catch:{ Exception -> 0x00f9 }
                    mc.csst.com.selfchassis.utils.bean.ShowSelfChassisBean r4 = mc.csst.com.selfchassis.utils.bean.ShowSelfChassisBean.getInstance()     // Catch:{ Exception -> 0x00f9 }
                    r4.setSportState(r0)     // Catch:{ Exception -> 0x00f9 }
                    java.lang.String r0 = mc.csst.com.selfchassis.utils.enumbs.BottomNavTipsType.getDesc(r1)     // Catch:{ Exception -> 0x00f9 }
                    mc.csst.com.selfchassis.ui.activity.main.MainPresenter r4 = mc.csst.com.selfchassis.ui.activity.main.MainPresenter.this     // Catch:{ Exception -> 0x00f9 }
                    r4.showNavBottomTips(r1, r0, r2)     // Catch:{ Exception -> 0x00f9 }
                    java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ Exception -> 0x00f9 }
                    r0.<init>()     // Catch:{ Exception -> 0x00f9 }
                    int r1 = mc.csst.com.selfchassis.App.getNavType()     // Catch:{ Exception -> 0x00f9 }
                    r2 = 1
                    if (r1 != r2) goto L_0x005d
                    boolean r1 = mc.csst.com.selfchassis.App.isMultiNaving()     // Catch:{ Exception -> 0x00f9 }
                    if (r1 != 0) goto L_0x007c
                L_0x005d:
                    mc.csst.com.selfchassis.ui.activity.main.MainPresenter r1 = mc.csst.com.selfchassis.ui.activity.main.MainPresenter.this     // Catch:{ Exception -> 0x00f9 }
                    mc.csst.com.selfchassis.ui.activity.base.BaseView r1 = r1.mView     // Catch:{ Exception -> 0x00f9 }
                    mc.csst.com.selfchassis.ui.activity.main.MainContract$View r1 = (mc.csst.com.selfchassis.ui.activity.main.MainContract.View) r1     // Catch:{ Exception -> 0x00f9 }
                    r1.changeNavType(r2)     // Catch:{ Exception -> 0x00f9 }
                    mc.csst.com.selfchassis.ui.activity.main.MainPresenter r1 = mc.csst.com.selfchassis.ui.activity.main.MainPresenter.this     // Catch:{ Exception -> 0x00f9 }
                    mc.csst.com.selfchassis.ui.activity.base.BaseView r1 = r1.mView     // Catch:{ Exception -> 0x00f9 }
                    mc.csst.com.selfchassis.ui.activity.main.MainContract$View r1 = (mc.csst.com.selfchassis.ui.activity.main.MainContract.View) r1     // Catch:{ Exception -> 0x00f9 }
                    r1.changePatrollingView(r2)     // Catch:{ Exception -> 0x00f9 }
                    java.lang.String r1 = mc.csst.com.selfchassis.ui.activity.main.MainPresenter.TAG     // Catch:{ Exception -> 0x00f9 }
                    java.lang.String r2 = "initWayPointStateTopic:恢复多点导航视图"
                    com.ciot.base.util.MyLogUtils.Logd(r1, r2)     // Catch:{ Exception -> 0x00f9 }
                L_0x007c:
                    if (r3 == 0) goto L_0x0091
                    boolean r1 = r3.isEmpty()     // Catch:{ Exception -> 0x00f9 }
                    if (r1 == 0) goto L_0x0085
                    goto L_0x0091
                L_0x0085:
                    java.lang.String r1 = com.blankj.utilcode.util.GsonUtils.toJson(r3)     // Catch:{ Exception -> 0x00f9 }
                    com.ciot.base.storage.MySpUtils r2 = com.ciot.base.storage.MySpUtils.getInstance()     // Catch:{ Exception -> 0x00f9 }
                    r2.putString(r6, r1)     // Catch:{ Exception -> 0x00f9 }
                    goto L_0x0099
                L_0x0091:
                    com.ciot.base.storage.MySpUtils r1 = com.ciot.base.storage.MySpUtils.getInstance()     // Catch:{ Exception -> 0x00f9 }
                    java.lang.String r1 = r1.getString(r6, r5)     // Catch:{ Exception -> 0x00f9 }
                L_0x0099:
                    boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Exception -> 0x00f9 }
                    if (r2 != 0) goto L_0x00d5
                    mc.csst.com.selfchassis.ui.activity.main.MainPresenter$2$1 r2 = new mc.csst.com.selfchassis.ui.activity.main.MainPresenter$2$1     // Catch:{ Exception -> 0x00f9 }
                    r2.<init>()     // Catch:{ Exception -> 0x00f9 }
                    java.lang.reflect.Type r2 = r2.getType()     // Catch:{ Exception -> 0x00f9 }
                    java.lang.Object r1 = com.blankj.utilcode.util.GsonUtils.fromJson((java.lang.String) r1, (java.lang.reflect.Type) r2)     // Catch:{ Exception -> 0x00f9 }
                    java.util.List r1 = (java.util.List) r1     // Catch:{ Exception -> 0x00f9 }
                    java.util.Iterator r1 = r1.iterator()     // Catch:{ Exception -> 0x00f9 }
                L_0x00b2:
                    boolean r2 = r1.hasNext()     // Catch:{ Exception -> 0x00f9 }
                    if (r2 == 0) goto L_0x00ca
                    java.lang.Object r2 = r1.next()     // Catch:{ Exception -> 0x00f9 }
                    java.lang.String r2 = (java.lang.String) r2     // Catch:{ Exception -> 0x00f9 }
                    mc.csst.com.selfchassislibrary.bean.msg.MarkerOperationGetMarkersResponseBean$ValuesBean$MarkersBean$WaypointsBean r3 = new mc.csst.com.selfchassislibrary.bean.msg.MarkerOperationGetMarkersResponseBean$ValuesBean$MarkersBean$WaypointsBean     // Catch:{ Exception -> 0x00f9 }
                    r3.<init>()     // Catch:{ Exception -> 0x00f9 }
                    r3.setName(r2)     // Catch:{ Exception -> 0x00f9 }
                    r0.add(r3)     // Catch:{ Exception -> 0x00f9 }
                    goto L_0x00b2
                L_0x00ca:
                    mc.csst.com.selfchassis.ui.activity.main.MainPresenter r1 = mc.csst.com.selfchassis.ui.activity.main.MainPresenter.this     // Catch:{ Exception -> 0x00f9 }
                    mc.csst.com.selfchassis.ui.activity.base.BaseView r1 = r1.mView     // Catch:{ Exception -> 0x00f9 }
                    mc.csst.com.selfchassis.ui.activity.main.MainContract$View r1 = (mc.csst.com.selfchassis.ui.activity.main.MainContract.View) r1     // Catch:{ Exception -> 0x00f9 }
                    r1.refreshPatrolPointAdapter(r0)     // Catch:{ Exception -> 0x00f9 }
                L_0x00d5:
                    r7.updateMultipointView(r0, r8)     // Catch:{ Exception -> 0x00f9 }
                    goto L_0x0115
                L_0x00d9:
                    com.ciot.base.storage.MySpUtils r8 = com.ciot.base.storage.MySpUtils.getInstance()     // Catch:{ Exception -> 0x00f9 }
                    r8.putString(r6, r5)     // Catch:{ Exception -> 0x00f9 }
                    mc.csst.com.selfchassis.ui.activity.main.MainPresenter r8 = mc.csst.com.selfchassis.ui.activity.main.MainPresenter.this     // Catch:{ Exception -> 0x00f9 }
                    mc.csst.com.selfchassis.ui.activity.base.BaseView r8 = r8.mView     // Catch:{ Exception -> 0x00f9 }
                    mc.csst.com.selfchassis.ui.activity.main.MainContract$View r8 = (mc.csst.com.selfchassis.ui.activity.main.MainContract.View) r8     // Catch:{ Exception -> 0x00f9 }
                    r0 = 0
                    r8.refreshPatrolPointAdapter(r0)     // Catch:{ Exception -> 0x00f9 }
                    mc.csst.com.selfchassis.ui.activity.main.MainPresenter r8 = mc.csst.com.selfchassis.ui.activity.main.MainPresenter.this     // Catch:{ Exception -> 0x00f9 }
                    mc.csst.com.selfchassis.ui.activity.base.BaseView r8 = r8.mView     // Catch:{ Exception -> 0x00f9 }
                    mc.csst.com.selfchassis.ui.activity.main.MainContract$View r8 = (mc.csst.com.selfchassis.ui.activity.main.MainContract.View) r8     // Catch:{ Exception -> 0x00f9 }
                    r0 = 0
                    r8.changePatrollingView(r0)     // Catch:{ Exception -> 0x00f9 }
                    goto L_0x0115
                L_0x00f9:
                    r8 = move-exception
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder
                    r0.<init>()
                    java.lang.String r1 = "initWayPointStateTopic error:"
                    r0.append(r1)
                    java.lang.String r1 = android.util.Log.getStackTraceString(r8)
                    r0.append(r1)
                    java.lang.String r0 = r0.toString()
                    com.ciot.base.util.MyLogUtils.Loge(r0)
                    r8.printStackTrace()
                L_0x0115:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: mc.csst.com.selfchassis.ui.activity.main.MainPresenter.AnonymousClass2.onSelfChassisMsg(mc.csst.com.selfchassislibrary.utils.eventbus.SelfChassisEventMsg):void");
            }

            private void updateMultipointView(List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean> list, WaypointStateBean.MsgBean msgBean) {
                int i;
                boolean z;
                int patrol_mode = msgBean.getPatrol_mode();
                int patrol_status = msgBean.getPatrol_status();
                int num_total = msgBean.getNum_total();
                int nav_status = msgBean.getNav_status();
                int size = list.size();
                int patrol_time = msgBean.getPatrol_time();
                int i2 = 0;
                if (patrol_time == 0) {
                    patrol_time = MySpUtils.getInstance().getInt(DeploymentToolSpConstant.MULTIPOINT_NAV_LOOP_NUM, 0);
                } else {
                    MySpUtils.getInstance().putInt(DeploymentToolSpConstant.MULTIPOINT_NAV_LOOP_NUM, patrol_time);
                }
                if (list.size() > 0) {
                    if (patrol_mode == 2) {
                        i2 = num_total % size;
                        i = (patrol_time - (num_total / size)) - 1;
                        z = true;
                    } else if (patrol_mode == 3) {
                        int i3 = (size * 2) - 1;
                        int i4 = num_total % i3;
                        if (i4 > size - 1) {
                            i2 = (size - (i4 % size)) - 2;
                            z = false;
                        } else {
                            i2 = i4;
                            z = true;
                        }
                        i = (patrol_time - (num_total / i3)) - 1;
                    } else {
                        z = true;
                        i = 0;
                    }
                    if (!(patrol_status == 4 || 1 == nav_status || nav_status == 601 || patrol_status == 680)) {
                        ((MainContract.View) MainPresenter.this.mView).selectPatrolRoute(i2, patrol_mode, z);
                    }
                    ((MainContract.View) MainPresenter.this.mView).updateMultipointNavData(patrol_mode, i);
                }
            }
        });
    }

    private void setSensorsListener() {
        SelfChassisListenerUtils.getInstance().setSensorsListener(new SelfChassisListenerUtils.OnBaseSelfChassisListener() {
            public final void onSelfChassisMsg(SelfChassisEventMsg selfChassisEventMsg) {
                MainPresenter.this.lambda$setSensorsListener$15$MainPresenter(selfChassisEventMsg);
            }
        });
    }

    public /* synthetic */ void lambda$setSensorsListener$15$MainPresenter(SelfChassisEventMsg selfChassisEventMsg) {
        SensorResponseBean sensorResponseBean;
        try {
            if (this.mView != null && selfChassisEventMsg != null && (sensorResponseBean = (SensorResponseBean) selfChassisEventMsg.getData()) != null && TextUtils.equals(sensorResponseBean.getId(), String.valueOf(2))) {
                List<SensorFeaturesBean> features_resp = sensorResponseBean.getValues().getFeatures_resp();
                this.mHavLabelCamera = false;
                App.getInstance().setOpenLife(false);
                if (features_resp != null && features_resp.size() > 0) {
                    for (SensorFeaturesBean next : features_resp) {
                        if (TextUtils.equals(next.getName(), "camera")) {
                            this.mHavLabelCamera = true;
                        } else if (TextUtils.equals(next.getName(), "lift")) {
                            App.getInstance().setOpenLife(next.isEnable());
                        }
                    }
                }
                ((MainContract.View) this.mView).refreshRightToolBarAdapter(this.mHavLabelCamera);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initLiftConfig() {
        MySpUtils instance = MySpUtils.getInstance();
        if (instance.getBoolean(SpConstant.ELEVATOR_CONTROL, false)) {
            SystemClock.sleep(500);
            SelfChassis.getInstance().setLiftControlConfigure(instance.getString(SpConstant.ELEVATOR_PROJECT_INFO), instance.getString(SpConstant.ELEVATOR_APP_ID), instance.getString(SpConstant.ELEVATOR_APP_SECRET), instance.getString(SpConstant.ELEVATOR_REBOOT_ID));
        }
    }

    public void deleteMap(String str, String str2) {
        SelfChassis.getInstance().sendServiceBuildingOperationDelete(str2, str);
    }

    public void deletePoint(String str) {
        SelfChassis.getInstance().sendDeleteMarker(str);
        SelfChassisListenerUtils.getInstance().setDeletePoiListener($$Lambda$MainPresenter$hrlp8FgjrMNT2gVbWmO1rO4Id0M.INSTANCE);
    }

    static /* synthetic */ void lambda$deletePoint$16(SelfChassisEventMsg selfChassisEventMsg) {
        SelfChassis.getInstance().sendGetMarkerList();
        MyLogUtils.Logd(TAG, "setDeletePoiListener======>");
    }

    public void softStop() {
        MyLogUtils.Logd("软急停：" + ShowSelfChassisBean.getInstance().isSoftStop());
        SelfChassis.getInstance().sendEStop(ShowSelfChassisBean.getInstance().isSoftStop() ^ true);
    }

    public void mapLogic(MapInfoBean mapInfoBean, Bitmap bitmap) {
        if (this.mView != null) {
            SelfChassis.getInstance().sendGetRobotCurrentMap();
            ((MainContract.View) this.mView).refreshMap(mapInfoBean, bitmap);
            String str = TAG;
            MyLogUtils.Logd(str, "mapLogic：tempNotifyFunc-->" + tempNotifyFunc);
            if (SoftTypeInfoManager.getInstance().getSoftType() == 1 && !App.isMultiNaving() && !App.isSingleNaving()) {
                SoftTypeInfoManager.getInstance().setSoftType(0);
                ((MainContract.View) this.mView).refreshPatrolPointAdapter((List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean>) null);
                ((MainContract.View) this.mView).changePatrollingView(false);
            }
            if (SoftTypeInfoManager.getInstance().getSoftType() == 8) {
                SystemClock.sleep(100);
                SelfChassis.getInstance().serviceTagManagerModeLocation();
                SoftTypeInfoManager.getInstance().setSoftType(0);
            }
            MyLogUtils.Logd("mapLogic ：" + SelfChassisState.getInstance().getControlStatus());
            if (SelfChassisState.getInstance().getControlStatus() != 20) {
                SystemClock.sleep(200);
                MyLogUtils.Logd(TAG, "mapLogic ：sendGetFloorData:");
                SelfChassis.getInstance().sendGetFloorData();
                SelfChassisListenerUtils.getInstance().setAreasInfoListener(new SelfChassisListenerUtils.OnBaseSelfChassisListener() {
                    public final void onSelfChassisMsg(SelfChassisEventMsg selfChassisEventMsg) {
                        MainPresenter.this.lambda$mapLogic$17$MainPresenter(selfChassisEventMsg);
                    }
                });
                if (this.mHavLabelCamera) {
                    SystemClock.sleep(200);
                    ((MainContract.View) this.mView).refreshVisualLabelData(new ArrayList());
                    SelfChassis.getInstance().initApriltagsBuffer();
                    setVisualLabelListListener();
                } else {
                    ((MainContract.View) this.mView).refreshVisualLabelData(new ArrayList());
                }
            }
            cancelTimer();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0071, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00f0, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ void lambda$mapLogic$17$MainPresenter(mc.csst.com.selfchassislibrary.utils.eventbus.SelfChassisEventMsg r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            java.lang.String r0 = TAG     // Catch:{ all -> 0x00f1 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f1 }
            r1.<init>()     // Catch:{ all -> 0x00f1 }
            java.lang.String r2 = "mapLogic ：setAreasInfoListener data:"
            r1.append(r2)     // Catch:{ all -> 0x00f1 }
            r1.append(r5)     // Catch:{ all -> 0x00f1 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00f1 }
            com.ciot.base.util.MyLogUtils.Logd(r0, r1)     // Catch:{ all -> 0x00f1 }
            if (r5 == 0) goto L_0x00e4
            java.lang.String r0 = r5.getCode()     // Catch:{ all -> 0x00f1 }
            java.lang.String r1 = "service_get_floor_data"
            boolean r0 = r0.equals(r1)     // Catch:{ all -> 0x00f1 }
            if (r0 == 0) goto L_0x00ef
            java.lang.Object r5 = r5.getData()     // Catch:{ all -> 0x00f1 }
            mc.csst.com.selfchassislibrary.bean.msg.LayeredMapCmdResponseBean r5 = (mc.csst.com.selfchassislibrary.bean.msg.LayeredMapCmdResponseBean) r5     // Catch:{ all -> 0x00f1 }
            java.lang.String r0 = TAG     // Catch:{ all -> 0x00f1 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f1 }
            r1.<init>()     // Catch:{ all -> 0x00f1 }
            java.lang.String r2 = "mapLogic ：setAreasInfoListener SERVICE_GET_FLOOR_DATA :"
            r1.append(r2)     // Catch:{ all -> 0x00f1 }
            java.lang.String r2 = com.blankj.utilcode.util.GsonUtils.toJson(r5)     // Catch:{ all -> 0x00f1 }
            r1.append(r2)     // Catch:{ all -> 0x00f1 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00f1 }
            com.ciot.base.util.MyLogUtils.Logd(r0, r1)     // Catch:{ all -> 0x00f1 }
            mc.csst.com.selfchassislibrary.bean.msg.LayeredMapCmdResponseBean$ValuesBean r0 = r5.getValues()     // Catch:{ all -> 0x00f1 }
            r1 = 0
            r2 = 0
            if (r0 == 0) goto L_0x00bb
            mc.csst.com.selfchassislibrary.bean.msg.LayeredMapCmdResponseBean$ValuesBean r0 = r5.getValues()     // Catch:{ all -> 0x00f1 }
            int r0 = r0.getResult()     // Catch:{ all -> 0x00f1 }
            if (r0 == 0) goto L_0x00bb
            int r0 = r4.tryGetFloorDataTime     // Catch:{ all -> 0x00f1 }
            r3 = 5
            if (r0 <= r3) goto L_0x0072
            r4.tryGetFloorDataTime = r2     // Catch:{ all -> 0x00f1 }
            mc.csst.com.selfchassislibrary.utils.SelfChassisListenerUtils r5 = mc.csst.com.selfchassislibrary.utils.SelfChassisListenerUtils.getInstance()     // Catch:{ all -> 0x00f1 }
            r5.setAreasInfoListener(r1)     // Catch:{ all -> 0x00f1 }
            mc.csst.com.selfchassis.ui.activity.base.BaseView r5 = r4.mView     // Catch:{ all -> 0x00f1 }
            if (r5 == 0) goto L_0x0070
            mc.csst.com.selfchassis.ui.activity.base.BaseView r5 = r4.mView     // Catch:{ all -> 0x00f1 }
            mc.csst.com.selfchassis.ui.activity.main.MainContract$View r5 = (mc.csst.com.selfchassis.ui.activity.main.MainContract.View) r5     // Catch:{ all -> 0x00f1 }
            r5.closeWaitDialog()     // Catch:{ all -> 0x00f1 }
        L_0x0070:
            monitor-exit(r4)     // Catch:{ all -> 0x00f1 }
            return
        L_0x0072:
            int r0 = r4.tryGetFloorDataTime     // Catch:{ all -> 0x00f1 }
            int r0 = r0 + 1
            r4.tryGetFloorDataTime = r0     // Catch:{ all -> 0x00f1 }
            r0 = 200(0xc8, double:9.9E-322)
            android.os.SystemClock.sleep(r0)     // Catch:{ all -> 0x00f1 }
            java.lang.String r0 = TAG     // Catch:{ all -> 0x00f1 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f1 }
            r1.<init>()     // Catch:{ all -> 0x00f1 }
            java.lang.String r2 = "获取楼层相关信息失败"
            r1.append(r2)     // Catch:{ all -> 0x00f1 }
            mc.csst.com.selfchassislibrary.bean.msg.LayeredMapCmdResponseBean$ValuesBean r5 = r5.getValues()     // Catch:{ all -> 0x00f1 }
            java.lang.String r5 = r5.getInfo()     // Catch:{ all -> 0x00f1 }
            r1.append(r5)     // Catch:{ all -> 0x00f1 }
            java.lang.String r5 = r1.toString()     // Catch:{ all -> 0x00f1 }
            com.ciot.base.util.MyLogUtils.Loge(r0, r5)     // Catch:{ all -> 0x00f1 }
            mc.csst.com.selfchassislibrary.chassis.SelfChassis r5 = mc.csst.com.selfchassislibrary.chassis.SelfChassis.getInstance()     // Catch:{ all -> 0x00f1 }
            r5.sendGetFloorData()     // Catch:{ all -> 0x00f1 }
            java.lang.String r5 = TAG     // Catch:{ all -> 0x00f1 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f1 }
            r0.<init>()     // Catch:{ all -> 0x00f1 }
            java.lang.String r1 = "获取楼层相关信息失败 重新获取一次 "
            r0.append(r1)     // Catch:{ all -> 0x00f1 }
            int r1 = r4.tryGetFloorDataTime     // Catch:{ all -> 0x00f1 }
            r0.append(r1)     // Catch:{ all -> 0x00f1 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00f1 }
            com.ciot.base.util.MyLogUtils.Logd(r5, r0)     // Catch:{ all -> 0x00f1 }
            goto L_0x00ef
        L_0x00bb:
            mc.csst.com.selfchassis.ui.activity.base.BaseView r5 = r4.mView     // Catch:{ all -> 0x00f1 }
            if (r5 == 0) goto L_0x00c6
            mc.csst.com.selfchassis.ui.activity.base.BaseView r5 = r4.mView     // Catch:{ all -> 0x00f1 }
            mc.csst.com.selfchassis.ui.activity.main.MainContract$View r5 = (mc.csst.com.selfchassis.ui.activity.main.MainContract.View) r5     // Catch:{ all -> 0x00f1 }
            r5.closeWaitDialog()     // Catch:{ all -> 0x00f1 }
        L_0x00c6:
            r4.tryGetFloorDataTime = r2     // Catch:{ all -> 0x00f1 }
            mc.csst.com.selfchassislibrary.utils.SelfChassisListenerUtils r5 = mc.csst.com.selfchassislibrary.utils.SelfChassisListenerUtils.getInstance()     // Catch:{ all -> 0x00f1 }
            r5.setAreasInfoListener(r1)     // Catch:{ all -> 0x00f1 }
            mc.csst.com.selfchassislibrary.chassis.SelfChassis r5 = mc.csst.com.selfchassislibrary.chassis.SelfChassis.getInstance()     // Catch:{ all -> 0x00f1 }
            r5.initApriltagsBuffer()     // Catch:{ all -> 0x00f1 }
            java.lang.String r5 = "resetForChangeBuilding"
            java.lang.String r0 = tempNotifyFunc     // Catch:{ all -> 0x00f1 }
            boolean r5 = android.text.TextUtils.equals(r5, r0)     // Catch:{ all -> 0x00f1 }
            if (r5 == 0) goto L_0x00ef
            r4.resetNotificationState()     // Catch:{ all -> 0x00f1 }
            goto L_0x00ef
        L_0x00e4:
            mc.csst.com.selfchassis.ui.activity.base.BaseView r5 = r4.mView     // Catch:{ all -> 0x00f1 }
            if (r5 == 0) goto L_0x00ef
            mc.csst.com.selfchassis.ui.activity.base.BaseView r5 = r4.mView     // Catch:{ all -> 0x00f1 }
            mc.csst.com.selfchassis.ui.activity.main.MainContract$View r5 = (mc.csst.com.selfchassis.ui.activity.main.MainContract.View) r5     // Catch:{ all -> 0x00f1 }
            r5.closeWaitDialog()     // Catch:{ all -> 0x00f1 }
        L_0x00ef:
            monitor-exit(r4)     // Catch:{ all -> 0x00f1 }
            return
        L_0x00f1:
            r5 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x00f1 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: mc.csst.com.selfchassis.ui.activity.main.MainPresenter.lambda$mapLogic$17$MainPresenter(mc.csst.com.selfchassislibrary.utils.eventbus.SelfChassisEventMsg):void");
    }

    public void navigationToPoint(MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean waypointsBean) {
        resetNotificationState();
        String name = waypointsBean.getName();
        tempNavStatus = -1;
        DeploymentToolUtils.getInstance().navigationToPoint(waypointsBean);
        ((MainContract.View) this.mView).showTipsBlack(String.format(ContextUtil.getContext().getString(R.string.tip_nav_to_point), new Object[]{name}));
    }

    public void rightBtnSave() {
        dealDisconnectSelfChassis();
        ((MainContract.View) this.mView).selectShapeBtn(0);
        switch (SoftTypeInfoManager.getInstance().getSoftType()) {
            case 1:
                SoftTypeInfoManager.getInstance().setSoftType(0);
                return;
            case 2:
                operationSave();
                return;
            case 3:
                SoftTypeInfoManager.getInstance().setSoftType(0);
                return;
            case 4:
                editMapSave();
                return;
            case 7:
                localPositioning();
                return;
            case 8:
                saveVisualLabelCalibrationMode();
                return;
            case 9:
                if (!SelfChassis.getInstance().isConnect()) {
                    MyLogUtils.Logw(TAG, "底盘已断连，请重新连接底盘");
                    return;
                } else if (this.tempState != 1) {
                    ((MainContract.View) this.mView).showContinueScanConfirmDialog(new ContinueScanConfirmDialog.OnDialogButtonClickListener() {
                        public final boolean onClick(View view, boolean z) {
                            return MainPresenter.this.lambda$rightBtnSave$18$MainPresenter(view, z);
                        }
                    });
                    return;
                } else {
                    return;
                }
            case 10:
                saveTrajectoryDrawing();
                return;
            default:
                return;
        }
    }

    public /* synthetic */ boolean lambda$rightBtnSave$18$MainPresenter(View view, boolean z) {
        ((MainContract.View) this.mView).showTipsBlack(ContextUtil.getContext().getString(R.string.txt_map_saving));
        ((MainContract.View) this.mView).showRightBtnSave(false);
        if (z) {
            deleteMapInfoOnScanMap();
        }
        SelfChassis.getInstance().serviceSave();
        this.tempState = 1;
        return false;
    }

    private void operationSave() {
        if (!SelfChassis.getInstance().isConnect()) {
            MyLogUtils.Logw(TAG, "底盘已断连，请重新连接底盘");
        } else if (this.tempState != 1) {
            ((MainContract.View) this.mView).showTipsBlack(ContextUtil.getContext().getString(R.string.txt_map_saving));
            ((MainContract.View) this.mView).showRightBtnSave(false);
            SelfChassis.getInstance().serviceSave();
            this.tempState = 1;
        }
    }

    public synchronized void rightTopBtnLock() {
        App.isLockMap = !App.isLockMap;
        ((MainContract.View) this.mView).lockMap(App.isLockMap);
        ((MainContract.View) this.mView).changeRightTopBtnLock(App.isLockMap);
    }

    public void rightTopBtnCancelNav() {
        isRightTopBtnCancelNav = true;
        int softType = SoftTypeInfoManager.getInstance().getSoftType();
        if (softType == 1) {
            SelfChassis.getInstance().sendCancelMove();
        } else if (softType == 5) {
            SelfChassis.getInstance().serviceLiftControlCancel();
            SelfChassisListenerUtils.getInstance().setCrossFloorCancelListener((SelfChassisListenerUtils.OnBaseSelfChassisListener) null);
        } else {
            MyToastUtils.showShort(ContextUtil.getContext(), ContextUtil.getContext().getString(R.string.sport_state_cancel_nav));
            Context context = ContextUtil.getContext();
            MyToastUtils.showShort(context, "softType" + softType);
        }
    }

    public void crossFloorNavToPoint(CrossFloorNaviReqBean.MsgBean msgBean, FragmentManager fragmentManager) {
        String goal_floor = msgBean.getGoal_floor();
        String marker_name = msgBean.getMarker_name();
        String string = ContextUtil.getContext().getString(R.string.confirm_cross_floor_nav_to_point);
        ConfirmDialog buildDefault2 = ConfirmDialog.buildDefault2(ContextUtil.getContext().getString(R.string.across_floor_navigation), String.format(string, new Object[]{goal_floor, marker_name}));
        buildDefault2.setOnOkButtonClickListener(new ConfirmDialog.OnDialogButtonClickListener(msgBean, goal_floor, marker_name) {
            public final /* synthetic */ CrossFloorNaviReqBean.MsgBean f$1;
            public final /* synthetic */ String f$2;
            public final /* synthetic */ String f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final boolean onClick(View view) {
                return MainPresenter.this.lambda$crossFloorNavToPoint$19$MainPresenter(this.f$1, this.f$2, this.f$3, view);
            }
        });
        buildDefault2.show(fragmentManager);
        resetNotificationState();
    }

    public /* synthetic */ boolean lambda$crossFloorNavToPoint$19$MainPresenter(CrossFloorNaviReqBean.MsgBean msgBean, String str, String str2, View view) {
        SoftTypeInfoManager.getInstance().setSoftType(5);
        SelfChassis.getInstance().startCrossFloorNavi(msgBean);
        ((MainContract.View) this.mView).showTipsBlack(String.format(ContextUtil.getContext().getString(R.string.tip_cross_floor_nav_to_point), new Object[]{str, str2}));
        return false;
    }

    private void exitNavOperate(int i) {
        if (tempNavStatus != i && NavStateType.isNavExit(i)) {
            if (isRightTopBtnCancelNav) {
                if (App.isSingleNaving()) {
                    App.setIsSingleNaving(false);
                    SoftTypeInfoManager.getInstance().setSoftType(SoftTypeInfoManager.getInstance().getSoftType());
                    MyLogUtils.Logd(TAG, "exitNavOperate执行了拖拽导航刷新当前视图");
                }
            } else if (SoftTypeInfoManager.getInstance().getSoftType() == 5 || SoftTypeInfoManager.getInstance().getSoftType() == 1 || SoftTypeInfoManager.getInstance().getSoftType() == 6) {
                SoftTypeInfoManager.getInstance().setSoftType(0);
                navSwitchNormal = true;
                App.setIsSingleNaving(false);
            }
        }
    }

    private void enterNavOperate(int i, String str) {
        if (NavStateType.isCommonNavigating(i) && !App.isSingleNaving()) {
            resetNotificationState();
            App.setNavType(0);
            App.setIsSingleNaving(true);
            String str2 = TAG;
            MyLogUtils.Logd(str2, " TANG: enterNavOperate isNavi " + SoftTypeInfoManager.getInstance().getSoftType() + ";tempNavStatus：" + tempNavStatus + "；navStatus:" + i);
            SoftTypeInfoManager.getInstance().setSoftType(1);
            String str3 = TAG;
            MyLogUtils.Logd(str3, " TANG: enterNavOperate isNavi2 " + SoftTypeInfoManager.getInstance().getSoftType() + ";tempNavStatus：" + tempNavStatus + "；navStatus:" + i);
        } else if (NavStateType.isCrossFloorNaving(i) && !App.isSingleNaving()) {
            resetNotificationState();
            App.setNavType(0);
            App.setIsSingleNaving(true);
            SoftTypeInfoManager.getInstance().setSoftType(5);
        }
    }

    public void bottomLock() {
        ShowSelfChassisBean.getInstance().setBottomLock(!ShowSelfChassisBean.getInstance().isBottomLock());
        ((MainContract.View) this.mView).changeBottomLock(ShowSelfChassisBean.getInstance().isBottomLock());
    }

    public void forcedCancelFloorNav() {
        if (SoftTypeInfoManager.getInstance().getSoftType() == 5) {
            SelfChassis.getInstance().sendCancelMove();
            SystemClock.sleep(150);
            SelfChassis.getInstance().liftControlForceCancel();
            MyToastUtils.showShort(ContextUtil.getContext(), ContextUtil.getContext().getString(R.string.sport_state_cancel_nav));
            MyLogUtils.Logd(TAG, "forcedCancelFloorNav=====触发强制取消");
        }
    }

    private void showNavErrorDialog(int i) {
        if (navSwitchNormal && tempNavStatus != i && SoftTypeInfoManager.getInstance().getSoftType() == 0) {
            String desc = NavDialogTipsType.getDesc(i);
            if (!TextUtils.isEmpty(desc)) {
                ((MainContract.View) this.mView).showConfirmDialog((String) null, desc, (ConfirmDialog.OnDialogButtonClickListener) null);
            }
        }
    }

    private void exitModeByCrossFloorNavCancel() {
        SelfChassis.getInstance().serviceLiftControlCancel();
        if (!NavStateType.isCommonNavigating(tempNavStatus) && !NavStateType.isCrossFloorNaving(tempNavStatus)) {
            SoftTypeInfoManager.getInstance().setSoftType(0);
        }
    }

    private void exitModeByNavCancel() {
        if (App.getNavType() == 1) {
            stopPoiPatrol();
        } else {
            SelfChassis.getInstance().sendCancelMove();
        }
        if (!NavStateType.isCommonNavigating(tempNavStatus) && !NavStateType.isCrossFloorNaving(tempNavStatus)) {
            SoftTypeInfoManager.getInstance().setSoftType(0);
        }
    }

    public void cancelTimer() {
        if (mRxTimer != null) {
            MyLogUtils.Logd(TAG, "触发cancelTimer");
            mRxTimer.cancel();
            mRxTimer = null;
            isTimerOpen = false;
        }
    }

    public void dealOnResume() {
        if (!isTimerOpen) {
            ((MainContract.View) this.mView).closeWaitDialog();
        }
        SoftTypeInfoManager.getInstance().refresh();
    }

    public void createIntervalTimer(long j, RxTimer.RxAction rxAction) {
        cancelTimer();
        if (mRxTimer == null) {
            mRxTimer = new RxTimer();
            isTimerOpen = true;
            mRxTimer.interval(j, rxAction);
        }
    }

    public List<LeftToolbarBean> initEditLeftToolbar() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new LeftToolbarBean("layer_virtual_wall", ContextUtil.getContext().getString(R.string.virtual_wall), R.mipmap.icon_virtual_wall_normal, R.mipmap.icon_virtual_wall_press, false));
        arrayList.add(new LeftToolbarBean("layer_strong_light_area", ContextUtil.getContext().getString(R.string.strong_light_zone), R.mipmap.icon_strong_light_normal, R.mipmap.icon_strong_light_press, false));
        arrayList.add(new LeftToolbarBean("layer_slow_area", ContextUtil.getContext().getString(R.string.slow_zone), R.mipmap.icon_slow_zone_normal, R.mipmap.icon_slow_zone_press, false));
        arrayList.add(new LeftToolbarBean("layer_danger_area", ContextUtil.getContext().getString(R.string.danger_zone), R.mipmap.icon_danger_normal, R.mipmap.icon_danger_press, false));
        arrayList.add(new LeftToolbarBean("layer_narrow_channel", ContextUtil.getContext().getString(R.string.txt_narrow_channel), R.mipmap.icon_zhaitongdao_normal, R.mipmap.icon_zhaitongdao_press, false));
        arrayList.add(new LeftToolbarBean("layer_work_area", ContextUtil.getContext().getString(R.string.working_zone), R.mipmap.icon_working_zone_normal, R.mipmap.icon_working_zone_press, true));
        arrayList.add(new LeftToolbarBean("layer_slope_area", ContextUtil.getContext().getString(R.string.slope_zone), R.mipmap.icon_slope_zone_normal, R.mipmap.icon_slope_zone_press, false));
        arrayList.add(new LeftToolbarBean("layer_label_area", ContextUtil.getContext().getString(R.string.txt_label_area), R.drawable.icon_label_zone_normal, R.drawable.icon_label_zone_press, false));
        arrayList.add(new LeftToolbarBean("layer_empty_area", ContextUtil.getContext().getString(R.string.empty_zone), R.mipmap.icon_empty_zone_normal, R.mipmap.icon_empty_zone_press, false));
        arrayList.add(new LeftToolbarBean("layer_obstacle_area", ContextUtil.getContext().getString(R.string.obstacle_zone), R.mipmap.icon_obstacle_zone_normal, R.mipmap.icon_obstacle_zone_press, false));
        arrayList.add(new LeftToolbarBean("layer_unknown_area", ContextUtil.getContext().getString(R.string.unknown_zone), R.mipmap.icon_unknown_zone_normal, R.mipmap.icon_unknown_zone_press, false));
        return arrayList;
    }

    public List<ToolbarBean> initRightToolbar(boolean z) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ToolbarBean(2, ContextUtil.getContext().getString(R.string.positioning), R.mipmap.icon_positioning_normal, R.mipmap.icon_positioning_press, false));
        arrayList.add(new ToolbarBean(3, ContextUtil.getContext().getString(R.string.calibration), R.mipmap.icon_calibration_normal, R.mipmap.icon_calibration_press, false));
        arrayList.add(new ToolbarBean(4, ContextUtil.getContext().getString(R.string.edit_map), R.mipmap.icon_edit_map_normal, R.mipmap.icon_edit_map_press, false));
        arrayList.add(new ToolbarBean(6, ContextUtil.getContext().getString(R.string.txt_calibration_mode), R.mipmap.icon_label_normal, R.mipmap.icon_label_press, !z));
        arrayList.add(new ToolbarBean(7, ContextUtil.getContext().getString(R.string.txt_line_tracking), R.mipmap.icon_line_tracking, R.mipmap.icon_line_tracking, false));
        arrayList.add(new ToolbarBean(5, ContextUtil.getContext().getString(R.string.setting), R.mipmap.icon_setting_normal, R.mipmap.icon_setting_press, false));
        return arrayList;
    }

    public void deleteVisualLabelByName(String str) {
        ((MainContract.View) this.mView).showConfirmDialog(ContextUtil.getContext().getString(R.string.delete_point), String.format(ContextUtil.getContext().getString(R.string.confirm_delete_point), new Object[]{str}), new ConfirmDialog.OnDialogButtonClickListener(str) {
            public final /* synthetic */ String f$1;

            {
                this.f$1 = r2;
            }

            public final boolean onClick(View view) {
                return MainPresenter.this.lambda$deleteVisualLabelByName$21$MainPresenter(this.f$1, view);
            }
        }, (ConfirmDialog.OnDialogButtonClickListener) null);
    }

    public /* synthetic */ boolean lambda$deleteVisualLabelByName$21$MainPresenter(String str, View view) {
        ((MainContract.View) this.mView).showWaitDialog(ContextUtil.getContext().getString(R.string.txt_deleting));
        SelfChassisListenerUtils.getInstance().setTagManagerDelPoiListener(new SelfChassisListenerUtils.OnBaseSelfChassisListener(str) {
            public final /* synthetic */ String f$1;

            {
                this.f$1 = r2;
            }

            public final void onSelfChassisMsg(SelfChassisEventMsg selfChassisEventMsg) {
                MainPresenter.this.lambda$deleteVisualLabelByName$20$MainPresenter(this.f$1, selfChassisEventMsg);
            }
        });
        SelfChassis.getInstance().serviceTagManagerDeletePoi(str);
        return false;
    }

    public /* synthetic */ void lambda$deleteVisualLabelByName$20$MainPresenter(String str, SelfChassisEventMsg selfChassisEventMsg) {
        TagManagerDeletePoiResponseBean tagManagerDeletePoiResponseBean;
        if (this.mView != null) {
            ((MainContract.View) this.mView).closeWaitDialog();
            if (!(selfChassisEventMsg == null || selfChassisEventMsg.getData() == null || (tagManagerDeletePoiResponseBean = (TagManagerDeletePoiResponseBean) selfChassisEventMsg.getData()) == null)) {
                if (tagManagerDeletePoiResponseBean.isResult()) {
                    ((MainContract.View) this.mView).removeItemOnVisualLabelAdapter(str);
                    MyLogUtils.Logd(TAG, "删除视觉标签点成功");
                } else {
                    MyLogUtils.Logd(TAG, "删除视觉标签点失败");
                }
            }
            SelfChassisListenerUtils.getInstance().setTagManagerDelPoiListener((SelfChassisListenerUtils.OnBaseSelfChassisListener) null);
        }
    }

    public void saveVisualLabelCalibrationMode() {
        ((MainContract.View) this.mView).showWaitDialog(ContextUtil.getContext().getString(R.string.save));
        SelfChassisListenerUtils.getInstance().setTagManagerSaveListener(new SelfChassisListenerUtils.OnBaseSelfChassisListener() {
            public void onSelfChassisMsg(SelfChassisEventMsg selfChassisEventMsg) {
                TagManagerControlResponseBean tagManagerControlResponseBean;
                ((MainContract.View) MainPresenter.this.mView).closeWaitDialog();
                if (!(selfChassisEventMsg == null || selfChassisEventMsg.getData() == null || (tagManagerControlResponseBean = (TagManagerControlResponseBean) selfChassisEventMsg.getData()) == null || !TextUtils.equals(tagManagerControlResponseBean.getId(), IDContent.TagManagerControl.SAVE))) {
                    if (tagManagerControlResponseBean.isResult()) {
                        MyToastUtils.showShort(ContextUtil.getContext(), ContextUtil.getContext().getString(R.string.txt_save_tag_point_success));
                        MyLogUtils.Logd(MainPresenter.TAG, "保存视觉标签点成功");
                    } else {
                        MyToastUtils.showShort(ContextUtil.getContext(), ContextUtil.getContext().getString(R.string.txt_save_failed));
                        MyLogUtils.Logd(MainPresenter.TAG, "保存视觉标签点失败");
                    }
                }
                SelfChassisListenerUtils.getInstance().setTagManagerDelPoiListener((SelfChassisListenerUtils.OnBaseSelfChassisListener) null);
            }
        });
        SelfChassis.getInstance().serviceTagManagerControl();
    }

    public void setVisualLabelListListener() {
        SelfChassisListenerUtils.getInstance().setTagManagerModeListListener(new SelfChassisListenerUtils.OnBaseSelfChassisListener() {
            public final void onSelfChassisMsg(SelfChassisEventMsg selfChassisEventMsg) {
                MainPresenter.this.lambda$setVisualLabelListListener$22$MainPresenter(selfChassisEventMsg);
            }
        });
    }

    public /* synthetic */ void lambda$setVisualLabelListListener$22$MainPresenter(SelfChassisEventMsg selfChassisEventMsg) {
        if (selfChassisEventMsg != null) {
            try {
                String code = selfChassisEventMsg.getCode();
                char c = 65535;
                int hashCode = code.hashCode();
                if (hashCode != -1921473467) {
                    if (hashCode == -413743345) {
                        if (code.equals(TopicContent.APRIL_TAGS_BUFFER)) {
                            c = 0;
                        }
                    }
                } else if (code.equals(TopicContent.CURRENT_TAG)) {
                    c = 1;
                }
                if (c == 0) {
                    List list = (List) selfChassisEventMsg.getData();
                    if (this.mView != null) {
                        ((MainContract.View) this.mView).refreshVisualLabelData(list);
                    }
                } else if (c == 1) {
                    List list2 = (List) selfChassisEventMsg.getData();
                    if (this.mView != null) {
                        ((MainContract.View) this.mView).refreshCurrentVisualLabelData(list2);
                    }
                }
            } catch (Exception e) {
                String str = TAG;
                MyLogUtils.Logd(str, "解析视觉列表失败：" + selfChassisEventMsg + "；error:" + Log.getStackTraceString(e));
            }
        }
    }

    public void startPoiPatrol(int i, final int i2, List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean> list) {
        setRightTopBtnCancelNav(true);
        ((MainContract.View) this.mView).showWaitDialog(ContextUtil.getContext().getString(R.string.dialog_loading_hint));
        ArrayList arrayList = new ArrayList();
        for (MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean name : list) {
            arrayList.add(name.getName());
        }
        MySpUtils.getInstance().putInt(DeploymentToolSpConstant.MULTIPOINT_NAV_LOOP_NUM, i);
        MySpUtils.getInstance().putString(DeploymentToolSpConstant.MULTIPOINT_NAV_POINTS_LIST, GsonUtils.toJson(arrayList));
        SelfChassis.getInstance().servicePoiPatrolStart(i, i2, arrayList);
        SelfChassisListenerUtils.getInstance().setSendPoiPatrolListener(new SelfChassisListenerUtils.OnBaseSelfChassisListener() {
            public void onSelfChassisMsg(SelfChassisEventMsg selfChassisEventMsg) {
                String access$000 = MainPresenter.TAG;
                MyLogUtils.Logd(access$000, "startPoiPatrol响应数据:" + GsonUtils.toJson(selfChassisEventMsg));
                if (MainPresenter.this.mView != null) {
                    ((MainContract.View) MainPresenter.this.mView).closeWaitDialog();
                }
                if (selfChassisEventMsg != null) {
                    try {
                        int result = ((PoiPatrolResponseBean) selfChassisEventMsg.getData()).getValues().getResult();
                        if (result != 0) {
                            ThreadUtils.runOnUiThread(new Runnable(result) {
                                public final /* synthetic */ int f$1;

                                {
                                    this.f$1 = r2;
                                }

                                public final void run() {
                                    MainPresenter.AnonymousClass4.this.lambda$onSelfChassisMsg$1$MainPresenter$4(this.f$1);
                                }
                            });
                        } else if (MainPresenter.this.mView != null) {
                            ((MainContract.View) MainPresenter.this.mView).selectPatrolRoute(0, i2, true);
                            ((MainContract.View) MainPresenter.this.mView).changePatrollingView(true);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    ThreadUtils.runOnUiThread($$Lambda$MainPresenter$4$Mvi2w4DXPCyRLtMb3wLGxdzmB4M.INSTANCE);
                    MyLogUtils.Logd(MainPresenter.TAG, "startPoiPatrol:多点导航失败");
                }
                SelfChassisListenerUtils.getInstance().setSendPoiPatrolListener((SelfChassisListenerUtils.OnBaseSelfChassisListener) null);
            }

            public /* synthetic */ void lambda$onSelfChassisMsg$1$MainPresenter$4(int i) {
                if (MainPresenter.this.mView != null) {
                    String string = ContextUtil.getContext().getString(R.string.txt_nav_type_multi);
                    String string2 = ContextUtil.getContext().getString(R.string.sport_state_nav_fail);
                    if (i == 686) {
                        string2 = ContextUtil.getContext().getString(R.string.txt_nav_fail_some_points_are_invalid);
                    } else if (i == 687) {
                        string2 = ContextUtil.getContext().getString(R.string.txt_nav_fail_only_a_single_point_is_valid);
                    }
                    ((MainContract.View) MainPresenter.this.mView).showConfirmDialog(string, string2, new ConfirmDialog.OnDialogButtonClickListener() {
                        public final boolean onClick(View view) {
                            return MainPresenter.AnonymousClass4.this.lambda$onSelfChassisMsg$0$MainPresenter$4(view);
                        }
                    });
                }
            }

            public /* synthetic */ boolean lambda$onSelfChassisMsg$0$MainPresenter$4(View view) {
                ((MainContract.View) MainPresenter.this.mView).refreshPatrolPointAdapter((List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean>) null);
                SelfChassis.getInstance().sendGetFloorData();
                return false;
            }
        });
        resetNotificationState();
    }

    public void stopPoiPatrol() {
        MyLogUtils.Logd(TAG, "stopPoiPatrol setRightTopBtnCancelNav is true");
        setRightTopBtnCancelNav(true);
        SelfChassis.getInstance().servicePoiPatrolStop();
        if (this.mView != null) {
            ((MainContract.View) this.mView).refreshPatrolPointAdapter((List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean>) null);
            ((MainContract.View) this.mView).changePatrollingView(false);
        }
    }

    public void sendGetFloorData() {
        if (this.mView != null) {
            ((MainContract.View) this.mView).showWaitDialog(ContextUtil.getContext().getString(R.string.dialog_loading_hint));
            SelfChassis.getInstance().sendGetFloorData();
            SelfChassisListenerUtils.getInstance().setAreasInfoListener(new SelfChassisListenerUtils.OnBaseSelfChassisListener() {
                public final void onSelfChassisMsg(SelfChassisEventMsg selfChassisEventMsg) {
                    MainPresenter.this.lambda$sendGetFloorData$24$MainPresenter(selfChassisEventMsg);
                }
            });
            if (this.mHavLabelCamera) {
                SystemClock.sleep(200);
                ((MainContract.View) this.mView).refreshVisualLabelData(new ArrayList());
                SelfChassis.getInstance().initApriltagsBuffer();
                setVisualLabelListListener();
                return;
            }
            ((MainContract.View) this.mView).refreshVisualLabelData(new ArrayList());
        }
    }

    public /* synthetic */ void lambda$sendGetFloorData$24$MainPresenter(SelfChassisEventMsg selfChassisEventMsg) {
        ThreadUtils.runOnUiThread(new Runnable() {
            public final void run() {
                MainPresenter.this.lambda$sendGetFloorData$23$MainPresenter();
            }
        });
        SelfChassisListenerUtils.getInstance().setAreasInfoListener((SelfChassisListenerUtils.OnBaseSelfChassisListener) null);
    }

    public /* synthetic */ void lambda$sendGetFloorData$23$MainPresenter() {
        ((MainContract.View) this.mView).closeWaitDialog();
    }

    public void setRightTopBtnCancelNav(boolean z) {
        isRightTopBtnCancelNav = z;
    }

    public void editMapSave() {
        MyLogUtils.Logd(TAG, "editMapSave()------编辑地图保存");
        tryLoadMap("", "");
        SelfChassis.getInstance().serviceSave();
    }

    public void exitEditMap() {
        MyLogUtils.Logd(TAG, "exitEditMap()------取消地图编辑");
        SelfChassis.getInstance().serviceAreaManagerRevert();
        SelfChassis.getInstance().serviceVirtualWallManagerRevert();
        SystemClock.sleep(100);
        SelfChassis.getInstance().clearPencil();
        SystemClock.sleep(100);
        SelfChassis.getInstance().sendGetFloorData();
        SoftTypeInfoManager.getInstance().setSoftType(0);
    }

    public synchronized void dealNotification(SelfChassisEventMsg selfChassisEventMsg) {
        if (selfChassisEventMsg.getData() != null) {
            NotificationBean notificationBean = (NotificationBean) selfChassisEventMsg.getData();
            if (notificationBean.getMsg() != null) {
                String function = notificationBean.getMsg().getFunction();
                if (TextUtils.isEmpty(tempNotifyFunc) && TextUtils.equals(function, "dangerous area")) {
                    MyLogUtils.Logd(TAG, "触发dangerous area");
                    tempNotifyFunc = "dangerous area";
                    ((MainContract.View) this.mView).showConfirmDialog((String) null, ContextUtil.getContext().getString(R.string.txt_entered_danger_zone_tips), ContextUtil.getContext().getString(R.string.txt_ok), (String) null, (ConfirmDialog.OnDialogButtonClickListener) null, (ConfirmDialog.OnDialogButtonClickListener) null);
                }
            }
        }
    }

    public void resetNotificationState() {
        tempNotifyFunc = "";
    }

    public boolean havPointInDangerousArea(List<AreaItemBean> list, MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean waypointsBean) {
        AreaItemBean.PolygonBean polygon;
        List<AreaItemBean.PolygonBean.PointsBean> points;
        try {
            AreaItemBean.PolygonBean.PointsBean pointsBean = new AreaItemBean.PolygonBean.PointsBean();
            pointsBean.setX((double) waypointsBean.getPose().getPosition().getX());
            pointsBean.setY((double) waypointsBean.getPose().getPosition().getY());
            for (AreaItemBean next : list) {
                if (next.getType() == 4 && (polygon = next.getPolygon()) != null && (points = polygon.getPoints()) != null && points.size() > 0 && AreaUtils.isCoordinatePoint(pointsBean, points)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            MyLogUtils.Loge(TAG, Log.getStackTraceString(e));
            return false;
        }
    }

    public boolean appendArea(AreaItemBean areaItemBean) {
        if (areaItemBean == null) {
            return false;
        }
        SelfChassis.getInstance().appendAreas(areaItemBean);
        return true;
    }

    public void appendArea(FragmentManager fragmentManager, final List<AreaItemBean> list, final AreaItemBean areaItemBean, String str) {
        final boolean z = areaItemBean.getType() == 8;
        int type = areaItemBean.getType();
        EditDialog newInstance = EditDialog.newInstance();
        newInstance.setTitle(ContextUtil.getContext().getResources().getString(R.string.txt_area_name)).setSubTitle(ContextUtil.getContext().getResources().getString(R.string.txt_area_sub_name)).setHintTxt(ContextUtil.getContext().getResources().getString(R.string.txt_hint_input_name)).setShowSlowZoneSpeed(z);
        newInstance.setOnDialogButtonClickListener(new EditDialog.OnDialogButtonClickListener() {
            public boolean onConfirm(String str, float f) {
                List<AreaItemBean> list = list;
                if (list != null) {
                    for (AreaItemBean name : list) {
                        if (TextUtils.equals(str, name.getName())) {
                            MyToastUtils.showShort(ContextUtil.getContext(), ContextUtil.getContext().getResources().getString(R.string.txt_zone_name_already_exists));
                            return true;
                        }
                    }
                }
                String access$000 = MainPresenter.TAG;
                MyLogUtils.Logd(access$000, "slowSpeed :" + f);
                areaItemBean.setName(str);
                if (z) {
                    areaItemBean.setParam1(f);
                }
                String access$0002 = MainPresenter.TAG;
                MyLogUtils.Logd(access$0002, "areaItemBean :" + areaItemBean);
                MainPresenter.this.appendArea(areaItemBean);
                List list2 = list;
                if (list2 != null) {
                    list2.add(areaItemBean);
                    ((MainContract.View) MainPresenter.this.mView).refreshAreaView(list);
                }
                SystemClock.sleep(200);
                return false;
            }

            public void onCanCel() {
                ((MainContract.View) MainPresenter.this.mView).clearUnfinishedArea();
            }
        });
        newInstance.showAllowingStateLoss(fragmentManager);
    }

    private void showChooseWaitMarkerDialog(FragmentManager fragmentManager, final List<AreaItemBean> list, final AreaItemBean areaItemBean) {
        ChoosePointWaitMarkerDialog newInstance = ChoosePointWaitMarkerDialog.newInstance();
        newInstance.setOnChooseMarkerListener(new ChoosePointWaitMarkerDialog.OnChooseMarkerListener() {
            public void onChooseMarkerListener(ArrayList<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean> arrayList) {
                if (arrayList.isEmpty()) {
                    ToastUtil.showShort((Context) MainPresenter.this.mView, ((Context) MainPresenter.this.mView).getString(R.string.txt_select_point_wait));
                    return;
                }
                areaItemBean.setParam1(arrayList.get(0).getPose().getPosition().getX());
                areaItemBean.setParam2((double) arrayList.get(0).getPose().getPosition().getY());
                if (arrayList.size() >= 2) {
                    areaItemBean.setParam3((double) arrayList.get(1).getPose().getPosition().getX());
                    areaItemBean.setParam4((double) arrayList.get(1).getPose().getPosition().getY());
                }
                MainPresenter.this.appendArea(areaItemBean);
                List list = list;
                if (list != null) {
                    list.add(areaItemBean);
                    ((MainContract.View) MainPresenter.this.mView).refreshAreaView(list);
                }
                SystemClock.sleep(200);
            }
        });
        newInstance.showAllowingStateLoss(fragmentManager);
    }

    public void deleteArea(List<AreaItemBean> list, String str) {
        SelfChassisListenerUtils.getInstance().setAreaDeletePoiListener(new SelfChassisListenerUtils.OnBaseSelfChassisListener(list, str) {
            public final /* synthetic */ List f$1;
            public final /* synthetic */ String f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void onSelfChassisMsg(SelfChassisEventMsg selfChassisEventMsg) {
                MainPresenter.this.lambda$deleteArea$25$MainPresenter(this.f$1, this.f$2, selfChassisEventMsg);
            }
        });
        String string = ContextUtil.getContext().getString(R.string.txt_del_area);
        Context context = ContextUtil.getContext();
        Object[] objArr = new Object[2];
        objArr[0] = str == null ? Configurator.NULL : str;
        objArr[1] = "";
        ((MainContract.View) this.mView).showConfirmDialog(string, context.getString(R.string.confirm_delete_map, objArr), new ConfirmDialog.OnDialogButtonClickListener(str) {
            public final /* synthetic */ String f$1;

            {
                this.f$1 = r2;
            }

            public final boolean onClick(View view) {
                return MainPresenter.this.lambda$deleteArea$26$MainPresenter(this.f$1, view);
            }
        }, (ConfirmDialog.OnDialogButtonClickListener) null);
    }

    public /* synthetic */ void lambda$deleteArea$25$MainPresenter(List list, String str, SelfChassisEventMsg selfChassisEventMsg) {
        MyLogUtils.Logd("删除区域列表回调：" + GsonUtils.toJson(selfChassisEventMsg));
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (TextUtils.equals(str, ((AreaItemBean) it.next()).getName())) {
                    it.remove();
                }
            }
            ((MainContract.View) this.mView).clearUnfinishedArea();
            ((MainContract.View) this.mView).refreshAreaView(list);
        }
        ((MainContract.View) this.mView).closeWaitDialog();
    }

    public /* synthetic */ boolean lambda$deleteArea$26$MainPresenter(String str, View view) {
        ((MainContract.View) this.mView).showWaitDialog(ContextUtil.getContext().getResources().getString(R.string.txt_deleting));
        SelfChassis.getInstance().sendDeleteArea(str);
        return false;
    }

    public void localPositioning() {
        ((MainContract.View) this.mView).showWaitDialog(ContextUtil.getContext().getString(R.string.tip_global_locate));
        SelfChassis.getInstance().serviceGlobalLocateLocal(0.0f, 0, ((MainContract.View) this.mView).getPositioningRectPoints());
        SelfChassisListenerUtils.getInstance().setGlobalLocateLocalListener(new SelfChassisListenerUtils.OnBaseSelfChassisListener() {
            public final void onSelfChassisMsg(SelfChassisEventMsg selfChassisEventMsg) {
                MainPresenter.this.lambda$localPositioning$27$MainPresenter(selfChassisEventMsg);
            }
        });
    }

    public /* synthetic */ void lambda$localPositioning$27$MainPresenter(SelfChassisEventMsg selfChassisEventMsg) {
        ((MainContract.View) this.mView).closeWaitDialog();
        SoftTypeInfoManager.getInstance().setSoftType(0);
        if (selfChassisEventMsg != null) {
            if (((GlobalLocateResponseBean) selfChassisEventMsg.getData()).isResult()) {
                MyToastUtils.showShort(ContextUtil.getContext(), ContextUtil.getContext().getString(R.string.tip_global_locate_local_success));
            } else {
                MyToastUtils.showShort(ContextUtil.getContext(), ContextUtil.getContext().getString(R.string.tip_global_locate_local_fail));
            }
        }
        SelfChassisListenerUtils.getInstance().setGlobalLocateLocalListener((SelfChassisListenerUtils.OnBaseSelfChassisListener) null);
    }

    public void showVisualLabelCalibrationMode() {
        resetExpandedView();
        ((MainContract.View) this.mView).showWaitDialog(ContextUtil.getContext().getString(R.string.dialog_loading_hint));
        SelfChassis.getInstance().serviceTagManagerModeCalibration();
        SelfChassisListenerUtils.getInstance().setTagManagerModeListener(new SelfChassisListenerUtils.OnBaseSelfChassisListener() {
            public final void onSelfChassisMsg(SelfChassisEventMsg selfChassisEventMsg) {
                MainPresenter.this.lambda$showVisualLabelCalibrationMode$31$MainPresenter(selfChassisEventMsg);
            }
        });
    }

    public /* synthetic */ void lambda$showVisualLabelCalibrationMode$31$MainPresenter(SelfChassisEventMsg selfChassisEventMsg) {
        ((MainContract.View) this.mView).closeWaitDialog();
        String str = TAG;
        MyLogUtils.Logd(str, "标定模式底盘回调信息:" + selfChassisEventMsg);
        if (selfChassisEventMsg != null) {
            TagManagerModeResponseBean tagManagerModeResponseBean = (TagManagerModeResponseBean) selfChassisEventMsg.getData();
            if (TextUtils.equals(tagManagerModeResponseBean.getId(), "1")) {
                SoftTypeInfoManager.getInstance().setSoftType(8);
                if (tagManagerModeResponseBean.isResult()) {
                    SelfChassis.getInstance().initApriltagsBuffer();
                    SelfChassis.getInstance().initCurrentTag();
                    setVisualLabelListListener();
                } else {
                    if (this.mView != null) {
                        if (TextUtils.equals("Service /tag_manager/mode does not exist", tagManagerModeResponseBean.getInfo())) {
                            ((MainContract.View) this.mView).showConfirmDialog((String) null, ContextUtil.getContext().getString(R.string.txt_tip_open_label_camera), ContextUtil.getContext().getString(R.string.txt_go_to_settings), ContextUtil.getContext().getString(R.string.txt_exit), new ConfirmDialog.OnDialogButtonClickListener() {
                                public final boolean onClick(View view) {
                                    return MainPresenter.this.lambda$showVisualLabelCalibrationMode$28$MainPresenter(view);
                                }
                            }, $$Lambda$MainPresenter$vXYoWrFRmczx7rMFC0X__8LwS0.INSTANCE);
                        } else {
                            ((MainContract.View) this.mView).showConfirmDialog((String) null, tagManagerModeResponseBean.getInfo() + "", $$Lambda$MainPresenter$qjNnAcs4IAlnllJivVt9PaxfaKg.INSTANCE);
                        }
                    }
                    String str2 = TAG;
                    MyLogUtils.Logd(str2, "标定模式底盘回调失败信息:" + tagManagerModeResponseBean.getInfo());
                }
            }
        }
        SelfChassisListenerUtils.getInstance().setTagManagerModeListener((SelfChassisListenerUtils.OnBaseSelfChassisListener) null);
    }

    public /* synthetic */ boolean lambda$showVisualLabelCalibrationMode$28$MainPresenter(View view) {
        SoftTypeInfoManager.getInstance().setSoftType(0);
        ((MainContract.View) this.mView).showSetActivity();
        return false;
    }

    public List<TopMenuBean> initTopMenu() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new TopMenuBean(0, ContextUtil.getContext().getString(R.string.txt_new_map), R.drawable.selector_new_map));
        arrayList.add(new TopMenuBean(1, ContextUtil.getContext().getString(R.string.txt_continue_to_scan_map), R.drawable.selector_continue_scan));
        return arrayList;
    }

    public void continueToScanMap() {
        if (this.mView != null) {
            if (SelfChassisState.getInstance().getOnDock() != 1) {
                ((MainContract.View) this.mView).showConfirmDialog("", StringUtils.getString(R.string.txt_contiue_scan_requires_on_dock), $$Lambda$MainPresenter$nNjzXIN0DnYsOGvJIhy92xkQmxY.INSTANCE);
                return;
            }
            String floorName = SelfChassisState.getInstance().getFloorName();
            String buildingName = SelfChassisState.getInstance().getBuildingName();
            String str = TAG;
            MyLogUtils.Logd(str, "地图续扫：curFloorName：" + floorName + "；curBuildingName：" + buildingName);
            SelfChassis.getInstance().serviceNodeManagerControl(1, floorName, buildingName);
        }
    }

    public void showTopMenu() {
        resetExpandedView();
        ((MainContract.View) this.mView).showTopMenuView(!((MainContract.View) this.mView).isShowTopMenuView());
    }

    public void deleteMapInfoOnScanMap() {
        SelfChassis.getInstance().serviceVirtualWallOperationDeleteWallsLocal();
        SystemClock.sleep(50);
        SelfChassis.getInstance().serviceMarkerOperationDeleteMarkersLocal();
        SystemClock.sleep(50);
        SelfChassis.getInstance().serviceTagManagerControlDeleteAll();
        SystemClock.sleep(50);
        SelfChassis.getInstance().serviceAreaManagerDelete();
        SystemClock.sleep(50);
    }

    public void dealTopMenuItem(TopMenuBean topMenuBean) {
        resetExpandedView();
        if (topMenuBean.getLabelId() == 0) {
            createMap();
        } else if (topMenuBean.getLabelId() == 1) {
            continueToScanMap();
        }
    }

    private void dealDisconnectSelfChassis() {
        if (!SelfChassis.getInstance().isConnect()) {
            SelfChassis.getInstance().disconnectSelfChassis();
            LiveDatabus.getInstance().with(DTLiveDataConstant.MAIN_CONNECT_DIALOG_EVENT).postValue("showConnectedDialog");
        }
    }
}
