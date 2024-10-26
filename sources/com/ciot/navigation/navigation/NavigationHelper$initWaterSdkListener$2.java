package com.ciot.navigation.navigation;

import android.content.Context;
import com.ciot.base.bean.MsgEvent;
import com.ciot.base.constant.EventBusConstant;
import com.ciot.base.constant.SpConstant;
import com.ciot.base.storage.MySpUtils;
import com.ciot.base.util.ContextUtil;
import com.ciot.base.util.MyLogUtils;
import com.ciot.navigation.R;
import com.ciot.navigation.navigation.task.BaseTask;
import com.ciot.navigation.navigation.task.RobotTaskUtil;
import com.ciot.navigation.navigation.water.IWaterNavigateListener;
import com.ciot.navigation.navigation.water.WaterNavigateState;
import com.ciot.waterchassis.WaterChassisConstants;
import com.ciot.waterchassis.bean.WaterStatusBean;
import java.util.Iterator;
import java.util.LinkedList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;

@Metadata(d1 = {"\u00001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u000bH\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\u0015"}, d2 = {"com/ciot/navigation/navigation/NavigationHelper$initWaterSdkListener$2", "Lcom/ciot/navigation/navigation/water/IWaterNavigateListener;", "mMoveSuccessTime", "", "getMMoveSuccessTime", "()I", "setMMoveSuccessTime", "(I)V", "stateArrivedPointNotification", "", "message", "", "stateFull", "bean", "Lcom/ciot/waterchassis/bean/WaterStatusBean;", "stateMaxLinearSpeed", "maxLinearSpeed", "", "stateNavigateErrMsg", "errCode", "errMsg", "library-navigation_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: NavigationHelper.kt */
public final class NavigationHelper$initWaterSdkListener$2 implements IWaterNavigateListener {
    private int mMoveSuccessTime;
    final /* synthetic */ NavigationHelper this$0;

    NavigationHelper$initWaterSdkListener$2(NavigationHelper navigationHelper) {
        this.this$0 = navigationHelper;
    }

    public void stateNavigateErrMsg(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "errCode");
        Intrinsics.checkNotNullParameter(str2, "errMsg");
        int hashCode = str.hashCode();
        if (hashCode != 45836434) {
            if (hashCode != 45836437) {
                if (hashCode == 45866253 && str.equals(WaterChassisConstants.WATER_CODE_ROBOT_MAYBE_LOST)) {
                    MyLogUtils.Logd(NavigationHelper.TAG, "The robot maybe lost.机器人可能迷路了");
                    if (this.this$0.mOnNavigationListeners != null) {
                        LinkedList access$getMOnNavigationListeners$p = this.this$0.mOnNavigationListeners;
                        Intrinsics.checkNotNull(access$getMOnNavigationListeners$p);
                        if (access$getMOnNavigationListeners$p.size() > 0) {
                            LinkedList access$getMOnNavigationListeners$p2 = this.this$0.mOnNavigationListeners;
                            Intrinsics.checkNotNull(access$getMOnNavigationListeners$p2);
                            Iterator it = access$getMOnNavigationListeners$p2.iterator();
                            while (it.hasNext()) {
                                ((OnNavigationListener) it.next()).updateErrorMessage(20019, str2);
                            }
                        }
                    }
                }
            } else if (str.equals("01006")) {
                if (this.this$0.mOnNavigationListeners != null) {
                    LinkedList access$getMOnNavigationListeners$p3 = this.this$0.mOnNavigationListeners;
                    Intrinsics.checkNotNull(access$getMOnNavigationListeners$p3);
                    if (access$getMOnNavigationListeners$p3.size() > 0) {
                        LinkedList access$getMOnNavigationListeners$p4 = this.this$0.mOnNavigationListeners;
                        Intrinsics.checkNotNull(access$getMOnNavigationListeners$p4);
                        Iterator it2 = access$getMOnNavigationListeners$p4.iterator();
                        while (it2.hasNext()) {
                            ((OnNavigationListener) it2.next()).updateErrorMessage(20011, str2);
                        }
                    }
                }
                this.this$0.setNavigating(false);
                this.this$0.mIsPaused = false;
            }
        } else if (str.equals("01003")) {
            this.this$0.callbackNavigateFailed(str2);
        }
    }

    public final int getMMoveSuccessTime() {
        return this.mMoveSuccessTime;
    }

    public final void setMMoveSuccessTime(int i) {
        this.mMoveSuccessTime = i;
    }

    public void stateFull(WaterStatusBean waterStatusBean) {
        Intrinsics.checkNotNullParameter(waterStatusBean, "bean");
        MyLogUtils.Logv("NAVIGATION_TAG", "NavigationHelper stateFull WaterStatusBean=" + waterStatusBean);
        for (OnAllStatusListener positionInfo : this.this$0.mOnAllStatusListeners) {
            positionInfo.getPositionInfo((float) waterStatusBean.getResults().getCurrent_pose().getX(), (float) waterStatusBean.getResults().getCurrent_pose().getY(), (float) waterStatusBean.getResults().getCurrent_pose().getTheta(), waterStatusBean.getResults().getCurrent_floor());
        }
        this.this$0.mCurWaterStatusBean = waterStatusBean;
        if (waterStatusBean.getResults().isCharge_state()) {
            this.this$0.mTempWaterChargeState = 1;
        } else {
            this.this$0.mTempWaterChargeState = 0;
        }
        if (this.this$0.mTempWaterChargeState != this.this$0.getMCurrentChargingState()) {
            NavigationHelper navigationHelper = this.this$0;
            navigationHelper.setMCurrentChargingState(navigationHelper.mTempWaterChargeState);
            this.this$0.chargingStateChanged();
        }
        if (this.this$0.getMCurrentBattery() != waterStatusBean.getResults().getPower_percent()) {
            this.this$0.setMCurrentBattery(waterStatusBean.getResults().getPower_percent());
            MyLogUtils.Logd(NavigationHelper.TAG, "initWaterSdkListener stateFull mCurrentBattery:" + this.this$0.getMCurrentBattery());
            this.this$0.batteryStateChanged();
        }
        if (!Intrinsics.areEqual((Object) this.this$0.mCurrentHardStopState, (Object) Boolean.valueOf(waterStatusBean.getResults().isHard_estop_state()))) {
            this.this$0.mCurrentHardStopState = Boolean.valueOf(waterStatusBean.getResults().isHard_estop_state());
            this.this$0.hardStopStateChanged();
        }
        if (!Intrinsics.areEqual((Object) this.this$0.mCurrentSoftStopState, (Object) Boolean.valueOf(waterStatusBean.getResults().isSoft_estop_state()))) {
            this.this$0.mCurrentSoftStopState = Boolean.valueOf(waterStatusBean.getResults().isSoft_estop_state());
            this.this$0.softStopStateChanged();
        }
        WaterStatusBean.ResultsBean.CurrentPoseBean current_pose = waterStatusBean.getResults().getCurrent_pose();
        if (!Intrinsics.areEqual((Object) this.this$0.getMCurrentPosition(), (Object) current_pose)) {
            this.this$0.setMCurrentPosition(current_pose);
        }
        if (this.this$0.getMCurrentFloor() != waterStatusBean.getResults().getCurrent_floor()) {
            this.this$0.setMCurrentFloor(waterStatusBean.getResults().getCurrent_floor());
        }
        this.this$0.mIsRobotIsLowPower = waterStatusBean.getResults().getPower_percent() <= MySpUtils.getInstance().getInt(SpConstant.SP_LOW_BATTERY_VALUE, 20);
        WaterStatusBean.ResultsBean results = waterStatusBean.getResults();
        String move_status = results != null ? results.getMove_status() : null;
        if (move_status == null) {
            move_status = WaterNavigateState.IDLE;
        }
        if (!Intrinsics.areEqual((Object) WaterNavigateState.SUCCEEDED, (Object) move_status) || !this.this$0.isNavigating()) {
            this.mMoveSuccessTime = 0;
        } else {
            this.mMoveSuccessTime++;
        }
        if (this.mMoveSuccessTime > 5) {
            this.mMoveSuccessTime = 0;
        } else if (Intrinsics.areEqual((Object) this.this$0.mCurWaterState, (Object) move_status)) {
            return;
        }
        MyLogUtils.Logd(NavigationHelper.TAG, "NavigationHelper stateFull update bean=" + waterStatusBean + ",mCurWaterState=" + this.this$0.mCurWaterState + ",moveStatus=" + move_status);
        this.this$0.mCurWaterState = move_status;
        switch (move_status.hashCode()) {
            case -1281977283:
                if (move_status.equals(WaterNavigateState.FAILED)) {
                    NavigationHelper navigationHelper2 = this.this$0;
                    Context context = ContextUtil.getContext();
                    Intrinsics.checkNotNull(context);
                    String string = context.getString(R.string.navigation_navigation_failed);
                    Intrinsics.checkNotNullExpressionValue(string, "getContext()!!.getString…gation_navigation_failed)");
                    navigationHelper2.callbackNavigateFailed(string);
                    BaseTask currentTask = RobotTaskUtil.getInstance().getCurrentTask();
                    if (currentTask != null) {
                        currentTask.setMoving(false);
                        return;
                    }
                    return;
                }
                return;
            case -123173735:
                if (move_status.equals(WaterNavigateState.CANCELED)) {
                    this.this$0.callbackCanceled();
                    BaseTask currentTask2 = RobotTaskUtil.getInstance().getCurrentTask();
                    if (currentTask2 != null) {
                        currentTask2.setMoving(false);
                        return;
                    }
                    return;
                }
                return;
            case 945734241:
                if (move_status.equals(WaterNavigateState.SUCCEEDED) && this.this$0.getMLastTagName() != null) {
                    this.this$0.callbackArrived();
                    BaseTask currentTask3 = RobotTaskUtil.getInstance().getCurrentTask();
                    if (currentTask3 != null) {
                        currentTask3.setMoving(false);
                        return;
                    }
                    return;
                }
                return;
            case 1550783935:
                if (move_status.equals(WaterNavigateState.RUNNING)) {
                    this.this$0.mIsPaused = false;
                    BaseTask currentTask4 = RobotTaskUtil.getInstance().getCurrentTask();
                    if (currentTask4 != null) {
                        currentTask4.setMoving(true);
                        return;
                    }
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void stateArrivedPointNotification(String str) {
        Intrinsics.checkNotNullParameter(str, "message");
        EventBus.getDefault().post(new MsgEvent(EventBusConstant.TAG_WATER_ARRIVED_POINT_DISTANCE, str));
    }

    public void stateMaxLinearSpeed(float f) {
        this.this$0.setMCurrentMaxLinearSpeed(Float.valueOf(f));
    }
}
