package com.ciot.navigation.navigation;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ThreadUtils;
import com.ciot.base.config.NavigationConfig;
import com.ciot.base.storage.MySpUtils;
import com.ciot.base.util.ContextUtil;
import com.ciot.base.util.MyLogUtils;
import com.ciot.diagnosis.DiagnosisManager;
import com.ciot.diagnosis.base.BaseDiagnosis;
import com.ciot.diagnosis.diagnosis.SelfChassisDiagnosis;
import com.ciot.navigation.R;
import com.ciot.navigation.navigation.NavigationHelper;
import com.limpoxe.support.servicemanager.provider.ARouterConstants;
import com.limpoxe.support.servicemanager.provider.speech.ISpeechManagerProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import mc.csst.com.selfchassislibrary.bean.MapInfoBean;
import mc.csst.com.selfchassislibrary.bean.msg.SelfDiagnosisResponseBean;
import mc.csst.com.selfchassislibrary.chassis.SelfChassis;
import mc.csst.com.selfchassislibrary.chassis.SelfChassisState;
import mc.csst.com.selfchassislibrary.navigation.INavigationMsgCallback;
import mc.csst.com.selfchassislibrary.navigation.NavigationManager;
import mc.csst.com.selfchassislibrary.navigation.PositionTag;

@Metadata(d1 = {"\u0000U\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00032\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nH\u0016J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u000eH\u0016J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0007H\u0016J\u001c\u0010\u0013\u001a\u00020\u00032\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u001c\u0010\u0014\u001a\u00020\u00032\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019H\u0016J\u0010\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0018\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\u0019H\u0016J\u0018\u0010!\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020\u00192\u0006\u0010#\u001a\u00020\u001dH\u0016J\u0012\u0010$\u001a\u00020\u00032\b\u0010\u001c\u001a\u0004\u0018\u00010%H\u0016¨\u0006&"}, d2 = {"com/ciot/navigation/navigation/NavigationHelper$initSelfSdkListener$1", "Lmc/csst/com/selfchassislibrary/navigation/INavigationMsgCallback;", "chassisState", "", "selfChassisState", "Lmc/csst/com/selfchassislibrary/chassis/SelfChassisState;", "currentMiles", "", "getAllPositionList", "positionTags", "", "Lmc/csst/com/selfchassislibrary/navigation/PositionTag;", "isConnect", "isConnectSuccess", "", "isInitSuccess", "successResult", "localizationConfidence", "suitability", "mapBit", "mapInfo", "Lmc/csst/com/selfchassislibrary/bean/MapInfoBean;", "map", "Landroid/graphics/Bitmap;", "floor", "", "buildName", "obstacleRegion", "data", "", "onError", "errorCode", "errorMsg", "onNavigationState", "msg", "state", "selfDiagnosis", "Lmc/csst/com/selfchassislibrary/bean/msg/SelfDiagnosisResponseBean;", "library-navigation_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: NavigationHelper.kt */
public final class NavigationHelper$initSelfSdkListener$1 implements INavigationMsgCallback {
    final /* synthetic */ NavigationHelper this$0;

    public void localizationConfidence(float f) {
    }

    public void mapBit(MapInfoBean mapInfoBean, Bitmap bitmap) {
    }

    public void mapInfo(String str, String str2) {
    }

    public void obstacleRegion(int i) {
    }

    NavigationHelper$initSelfSdkListener$1(NavigationHelper navigationHelper) {
        this.this$0 = navigationHelper;
    }

    public void chassisState(SelfChassisState selfChassisState) {
        this.this$0.dealChassisState(selfChassisState);
        if (selfChassisState != null) {
            for (OnAllStatusListener positionInfo : this.this$0.mOnAllStatusListeners) {
                positionInfo.getPositionInfo(selfChassisState.getX(), selfChassisState.getY(), selfChassisState.getTheta(), selfChassisState.getCurFloor());
            }
        }
    }

    public void selfDiagnosis(SelfDiagnosisResponseBean selfDiagnosisResponseBean) {
        BaseDiagnosis diagnosis;
        String access$getTAG$cp = NavigationHelper.TAG;
        Log.d(access$getTAG$cp, "SelfDiagnosisResponseBean  data:" + selfDiagnosisResponseBean);
        List<SelfDiagnosisResponseBean.ValuesBean.StatusBean> list = null;
        SelfDiagnosisResponseBean.ValuesBean values = selfDiagnosisResponseBean != null ? selfDiagnosisResponseBean.getValues() : null;
        if (values != null) {
            list = values.getStatus();
        }
        if (list != null) {
            for (SelfDiagnosisResponseBean.ValuesBean.StatusBean next : list) {
                String hardware_id = next.getHardware_id();
                String message = next.getMessage();
                if (next.getLevel() == 0) {
                    DiagnosisManager.getInstance().getSelfChassisDiagnosis().getDiagnosis(hardware_id).setStatus(0, R.string.diagnosis_chassis_normal);
                } else {
                    if (TextUtils.isEmpty(message)) {
                        message = ContextUtil.getContext().getString(R.string.diagnosis_chassis_abnormal);
                    }
                    SelfChassisDiagnosis selfChassisDiagnosis = DiagnosisManager.getInstance().getSelfChassisDiagnosis();
                    if (!(selfChassisDiagnosis == null || (diagnosis = selfChassisDiagnosis.getDiagnosis(hardware_id)) == null)) {
                        diagnosis.setStatus(3001, message);
                    }
                }
            }
        }
    }

    public void currentMiles(float f) {
        this.this$0.setMCurrentMiles(Float.valueOf(f));
    }

    public void isConnect(boolean z) {
        String access$getTAG$cp = NavigationHelper.TAG;
        MyLogUtils.Logd(access$getTAG$cp, "NavigationSDK isConnect:" + z);
        this.this$0.mIsNavigateConnected = z;
        if (z) {
            DiagnosisManager.getInstance().getSelfChassisDiagnosis().getDiagnosis(SelfChassisDiagnosis.CHASSIS_CONNECT).setStatus(0, R.string.diagnosis_chassis_success_connected);
            NavigationManager.getInstance().init(ContextUtil.getContext());
            NavigationManager.getInstance().requestAllPositionList();
            SelfChassis.getInstance().serviceRobotInfo();
            Handler threadHandler = this.this$0.getThreadHandler();
            if (threadHandler != null) {
                threadHandler.postDelayed(new Runnable() {
                    public final void run() {
                        NavigationHelper$initSelfSdkListener$1.m44isConnect$lambda1(NavigationHelper.this);
                    }
                }, 2000);
            }
        } else {
            DiagnosisManager.getInstance().getSelfChassisDiagnosis().getDiagnosis(SelfChassisDiagnosis.CHASSIS_CONNECT).setStatus(3002, R.string.diagnosis_chassis_fail_connect);
            this.this$0.mAllTagList = null;
        }
        if (this.this$0.mOnNavigationListeners != null) {
            LinkedList access$getMOnNavigationListeners$p = this.this$0.mOnNavigationListeners;
            Intrinsics.checkNotNull(access$getMOnNavigationListeners$p);
            if (access$getMOnNavigationListeners$p.size() > 0) {
                ThreadUtils.runOnUiThread(new Runnable(z) {
                    public final /* synthetic */ boolean f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        NavigationHelper$initSelfSdkListener$1.m45isConnect$lambda2(NavigationHelper.this, this.f$1);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: isConnect$lambda-1  reason: not valid java name */
    public static final void m44isConnect$lambda1(NavigationHelper navigationHelper) {
        Intrinsics.checkNotNullParameter(navigationHelper, "this$0");
        MyLogUtils.Logd(NavigationHelper.TAG, "sendNavStateChangeBroadcast MSG_CODE_POWER_CHANGED");
        navigationHelper.sendNavStateChangeBroadcast(20015);
    }

    /* access modifiers changed from: private */
    /* renamed from: isConnect$lambda-2  reason: not valid java name */
    public static final void m45isConnect$lambda2(NavigationHelper navigationHelper, boolean z) {
        Intrinsics.checkNotNullParameter(navigationHelper, "this$0");
        LinkedList access$getMOnNavigationListeners$p = navigationHelper.mOnNavigationListeners;
        Intrinsics.checkNotNull(access$getMOnNavigationListeners$p);
        Iterator it = access$getMOnNavigationListeners$p.iterator();
        while (it.hasNext()) {
            ((OnNavigationListener) it.next()).connectResult(z);
        }
    }

    public void isInitSuccess(boolean z) {
        String access$getTAG$cp = NavigationHelper.TAG;
        MyLogUtils.Logd(access$getTAG$cp, "NavigationSDK isInitSuccess:" + z);
        this.this$0.mIsNavigateInitSuccess = z;
        if (!this.this$0.mIsNavigateInitSuccess) {
            this.this$0.mAllTagList = null;
            return;
        }
        SelfChassis.getInstance().serviceRecordMiles(true);
        SelfChassis.getInstance().serviceSelfDiagnosis();
        NavigationHelper.Companion companion = NavigationHelper.Companion;
        NavigationHelper.mReceptionTagName = MySpUtils.getInstance().getString(NavigationConfig.NAVIGATION_RECEPTION_NAME, "");
        Handler threadHandler = this.this$0.getThreadHandler();
        if (threadHandler != null) {
            threadHandler.postDelayed(new Runnable() {
                public final void run() {
                    NavigationHelper$initSelfSdkListener$1.m46isInitSuccess$lambda3(NavigationHelper.this);
                }
            }, 1000);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: isInitSuccess$lambda-3  reason: not valid java name */
    public static final void m46isInitSuccess$lambda3(NavigationHelper navigationHelper) {
        Intrinsics.checkNotNullParameter(navigationHelper, "this$0");
        navigationHelper.sendNavStateChangeBroadcast(20015);
    }

    public void onNavigationState(String str, int i) {
        Intrinsics.checkNotNullParameter(str, NotificationCompat.CATEGORY_MESSAGE);
        String access$getTAG$cp = NavigationHelper.TAG;
        MyLogUtils.Logd(access$getTAG$cp, "NavigationSDK onNavigationState:" + i + ",id=" + str);
        if (i != 2) {
            if (i != 3) {
                if (i != 4) {
                    if (i == 6) {
                        Context context = ContextUtil.getContext();
                        Intrinsics.checkNotNull(context);
                        String string = context.getString(R.string.navigation_navigation_invalid_point);
                        Intrinsics.checkNotNullExpressionValue(string, "getContext()!!.getString…navigation_invalid_point)");
                        this.this$0.callbackNavigateFailed(string);
                    }
                } else if (!TextUtils.isEmpty(this.this$0.getMLastTagName())) {
                    Context context2 = ContextUtil.getContext();
                    Intrinsics.checkNotNull(context2);
                    String string2 = context2.getString(R.string.navigation_navigation_failed);
                    Intrinsics.checkNotNullExpressionValue(string2, "getContext()!!.getString…gation_navigation_failed)");
                    this.this$0.callbackNavigateFailed(string2);
                }
            } else if (str.equals(this.this$0.getMLastTagId()) || ((Intrinsics.areEqual((Object) this.this$0.getMChargeTagName(), (Object) str) && Intrinsics.areEqual((Object) this.this$0.getMChargeTagName(), (Object) this.this$0.getMLastTagName())) || StringsKt.contains$default((CharSequence) str, (CharSequence) "move_base", false, 2, (Object) null))) {
                this.this$0.callbackArrived();
            }
        } else if (str.equals(this.this$0.getMLastTagId())) {
            this.this$0.callbackCanceled();
        }
    }

    public void getAllPositionList(List<? extends PositionTag> list) {
        Handler threadHandler;
        String access$getTAG$cp = NavigationHelper.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("NavigationSDK getAllPositionList:");
        sb.append(list != null ? list.toString() : null);
        MyLogUtils.Logd(access$getTAG$cp, sb.toString());
        this.this$0.mAllTagList = list;
        List access$getMAllTagList$p = this.this$0.mAllTagList;
        if ((access$getMAllTagList$p != null ? access$getMAllTagList$p.size() : 0) > 0) {
            if (this.this$0.mOnNavigationListeners != null) {
                LinkedList access$getMOnNavigationListeners$p = this.this$0.mOnNavigationListeners;
                Intrinsics.checkNotNull(access$getMOnNavigationListeners$p);
                if (access$getMOnNavigationListeners$p.size() > 0) {
                    ThreadUtils.runOnUiThread(new Runnable(list) {
                        public final /* synthetic */ List f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            NavigationHelper$initSelfSdkListener$1.m41getAllPositionList$lambda4(NavigationHelper.this, this.f$1);
                        }
                    });
                }
            }
        } else if (this.this$0.mOnNavigationListeners != null) {
            LinkedList access$getMOnNavigationListeners$p2 = this.this$0.mOnNavigationListeners;
            Intrinsics.checkNotNull(access$getMOnNavigationListeners$p2);
            if (access$getMOnNavigationListeners$p2.size() > 0) {
                ThreadUtils.runOnUiThread(new Runnable(list) {
                    public final /* synthetic */ List f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        NavigationHelper$initSelfSdkListener$1.m42getAllPositionList$lambda5(NavigationHelper.this, this.f$1);
                    }
                });
            }
        }
        if (list != null && (!list.isEmpty()) && (threadHandler = this.this$0.getThreadHandler()) != null) {
            threadHandler.post(new Runnable(list, this.this$0) {
                public final /* synthetic */ List f$0;
                public final /* synthetic */ NavigationHelper f$1;

                {
                    this.f$0 = r1;
                    this.f$1 = r2;
                }

                public final void run() {
                    NavigationHelper$initSelfSdkListener$1.m43getAllPositionList$lambda6(this.f$0, this.f$1);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: getAllPositionList$lambda-4  reason: not valid java name */
    public static final void m41getAllPositionList$lambda4(NavigationHelper navigationHelper, List list) {
        Intrinsics.checkNotNullParameter(navigationHelper, "this$0");
        LinkedList access$getMOnNavigationListeners$p = navigationHelper.mOnNavigationListeners;
        Intrinsics.checkNotNull(access$getMOnNavigationListeners$p);
        Iterator it = access$getMOnNavigationListeners$p.iterator();
        while (it.hasNext()) {
            ((OnNavigationListener) it.next()).getNavigationTags(true, list);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: getAllPositionList$lambda-5  reason: not valid java name */
    public static final void m42getAllPositionList$lambda5(NavigationHelper navigationHelper, List list) {
        Intrinsics.checkNotNullParameter(navigationHelper, "this$0");
        LinkedList access$getMOnNavigationListeners$p = navigationHelper.mOnNavigationListeners;
        Intrinsics.checkNotNull(access$getMOnNavigationListeners$p);
        Iterator it = access$getMOnNavigationListeners$p.iterator();
        while (it.hasNext()) {
            ((OnNavigationListener) it.next()).getNavigationTags(false, list);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: getAllPositionList$lambda-6  reason: not valid java name */
    public static final void m43getAllPositionList$lambda6(List list, NavigationHelper navigationHelper) {
        Intrinsics.checkNotNullParameter(navigationHelper, "this$0");
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            int i2 = i + 1;
            PositionTag positionTag = (PositionTag) it.next();
            if (positionTag.getType() == 11) {
                navigationHelper.setMChargeTagName(positionTag.getName());
                navigationHelper.setMChargeTagIndex(Integer.valueOf(i));
            }
            arrayList.add(positionTag.getName());
            i = i2;
        }
        if (!arrayList.isEmpty()) {
            Object navigation = ARouter.getInstance().build(ARouterConstants.LIB_SPEECH).navigation();
            if (navigation != null) {
                ((ISpeechManagerProvider) navigation).addNavigateKeyWord("allTagName", arrayList);
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type com.limpoxe.support.servicemanager.provider.speech.ISpeechManagerProvider");
        }
    }

    public void onError(int i, String str) {
        Intrinsics.checkNotNullParameter(str, "errorMsg");
        String access$getTAG$cp = NavigationHelper.TAG;
        MyLogUtils.Logd(access$getTAG$cp, "NavigationSDK onError=" + i + ",errorMsg=" + str);
        if (this.this$0.mOnNavigationListeners != null) {
            LinkedList access$getMOnNavigationListeners$p = this.this$0.mOnNavigationListeners;
            Intrinsics.checkNotNull(access$getMOnNavigationListeners$p);
            if (access$getMOnNavigationListeners$p.size() > 0) {
                LinkedList access$getMOnNavigationListeners$p2 = this.this$0.mOnNavigationListeners;
                Intrinsics.checkNotNull(access$getMOnNavigationListeners$p2);
                Iterator it = access$getMOnNavigationListeners$p2.iterator();
                while (it.hasNext()) {
                    ThreadUtils.runOnUiThread(new Runnable(i, str, this.this$0) {
                        public final /* synthetic */ int f$1;
                        public final /* synthetic */ String f$2;
                        public final /* synthetic */ NavigationHelper f$3;

                        {
                            this.f$1 = r2;
                            this.f$2 = r3;
                            this.f$3 = r4;
                        }

                        public final void run() {
                            NavigationHelper$initSelfSdkListener$1.m50onError$lambda7(OnNavigationListener.this, this.f$1, this.f$2, this.f$3);
                        }
                    });
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: onError$lambda-7  reason: not valid java name */
    public static final void m50onError$lambda7(OnNavigationListener onNavigationListener, int i, String str, NavigationHelper navigationHelper) {
        Intrinsics.checkNotNullParameter(onNavigationListener, "$listener");
        Intrinsics.checkNotNullParameter(str, "$errorMsg");
        Intrinsics.checkNotNullParameter(navigationHelper, "this$0");
        onNavigationListener.updateErrorMessage(i, str);
        navigationHelper.setNavigating(false);
    }
}
