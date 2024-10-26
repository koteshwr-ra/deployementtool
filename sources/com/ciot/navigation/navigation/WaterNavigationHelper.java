package com.ciot.navigation.navigation;

import android.os.SystemClock;
import com.blankj.utilcode.util.ThreadUtils;
import com.ciot.base.util.AppSpUtil;
import com.ciot.base.util.MyLogUtils;
import com.ciot.navigation.navigation.water.WaterChassisHandlerCallback;
import com.ciot.waterchassis.WaterChassisHelper;
import com.limpoxe.support.servicemanager.ServiceProvider;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0010\u0006\n\u0002\b\u0012\u0018\u0000 ;2\u00020\u0001:\u0002;<B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0017\u001a\u00020\rH\u0016J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\nH\u0016J\b\u0010\u001b\u001a\u00020\u0019H\u0016J@\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020\u001e2\u0006\u0010#\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\nH\u0016J\b\u0010%\u001a\u00020\u0019H\u0016J8\u0010&\u001a\u00020\u00192\u0006\u0010'\u001a\u00020\n2\u0006\u0010(\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\u00042\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020+2\u0006\u0010-\u001a\u00020+H\u0016J\b\u0010.\u001a\u00020\u0019H\u0016J\b\u0010/\u001a\u00020\u0019H\u0016J\b\u00100\u001a\u00020\u0019H\u0016J\b\u00101\u001a\u00020\u0019H\u0016J \u00102\u001a\u00020\r2\u0006\u0010*\u001a\u00020\u001e2\u0006\u0010,\u001a\u00020\u001e2\u0006\u00103\u001a\u00020\u001eH\u0016J&\u00102\u001a\u00020\u00192\u0006\u0010*\u001a\u00020\u001e2\u0006\u0010,\u001a\u00020\u001e2\u0006\u00103\u001a\u00020\u001e2\u0006\u00104\u001a\u00020\u001eJ\u0018\u00102\u001a\u00020\r2\u0006\u00105\u001a\u00020\n2\u0006\u00106\u001a\u00020\nH\u0016J\u000e\u00107\u001a\u00020\u00192\u0006\u00108\u001a\u00020\bJ\b\u00109\u001a\u00020\u0019H\u0016J\b\u0010:\u001a\u00020\u0019H\u0002R\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0006\"\u0004\b\u0015\u0010\u0016¨\u0006="}, d2 = {"Lcom/ciot/navigation/navigation/WaterNavigationHelper;", "Lcom/ciot/navigation/navigation/INavigationHelper;", "()V", "MAX_TIMEOUT_TIMES", "", "getMAX_TIMEOUT_TIMES", "()I", "mCallback", "Lcom/ciot/navigation/navigation/water/WaterChassisHandlerCallback;", "mLastsetTargetPositionId", "", "mRobotId", "mSendMoveSuccess", "", "getMSendMoveSuccess", "()Z", "setMSendMoveSuccess", "(Z)V", "mSeq", "mWaitTime", "getMWaitTime", "setMWaitTime", "(I)V", "cancelNavigation", "deleteMarkerByName", "", "markerName", "detectNewVersion", "getBetweenPath", "start_x", "", "start_y", "start_floor", "goal_x", "goal_y", "goal_floor", "uuid", "init", "insertMarker", "name", "type", "floor", "x", "", "y", "theta", "pause", "queryAllMarkers", "release", "sendGetMapList", "setTargetPosition", "radians", "distanceTolerance", "id", "tagName", "setWaterChassiHandlerCallback", "callback", "updateChassisVersion", "waitMoveResponse", "Companion", "WaterChassisHelerHolder", "library-navigation_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: WaterNavigationHelper.kt */
public final class WaterNavigationHelper implements INavigationHelper {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final int MAX_TIMEOUT_TIMES;
    private WaterChassisHandlerCallback mCallback;
    private String mLastsetTargetPositionId;
    private String mRobotId;
    private volatile boolean mSendMoveSuccess;
    private int mSeq;
    private volatile int mWaitTime;

    public /* synthetic */ WaterNavigationHelper(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private WaterNavigationHelper() {
        this.mSeq = 1;
        this.MAX_TIMEOUT_TIMES = 30;
    }

    public final void setWaterChassiHandlerCallback(WaterChassisHandlerCallback waterChassisHandlerCallback) {
        Intrinsics.checkNotNullParameter(waterChassisHandlerCallback, "callback");
        this.mCallback = waterChassisHandlerCallback;
    }

    public void init() {
        if (this.mCallback == null) {
            MyLogUtils.Logd("NAVIGATION_TAG", "WaterNavigationHelper failed,callback is null");
            return;
        }
        this.mRobotId = AppSpUtil.getInstance().getRobotNumber();
        WaterChassisHelper.getInstance().connectWaterChassis(this.mCallback);
    }

    public boolean cancelNavigation() {
        return WaterChassisHelper.getInstance().sendCancelMove();
    }

    public void pause() {
        WaterChassisHelper.getInstance().sendCancelMove();
    }

    public void release() {
        WaterChassisHelper.getInstance().disconnectWaterChassis();
    }

    public final boolean getMSendMoveSuccess() {
        return this.mSendMoveSuccess;
    }

    public final void setMSendMoveSuccess(boolean z) {
        this.mSendMoveSuccess = z;
    }

    public final int getMWaitTime() {
        return this.mWaitTime;
    }

    public final void setMWaitTime(int i) {
        this.mWaitTime = i;
    }

    public final int getMAX_TIMEOUT_TIMES() {
        return this.MAX_TIMEOUT_TIMES;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0089, code lost:
        return r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean setTargetPosition(java.lang.String r4, java.lang.String r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            java.lang.String r0 = "id"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)     // Catch:{ all -> 0x008a }
            java.lang.String r0 = "tagName"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)     // Catch:{ all -> 0x008a }
            java.lang.String r0 = r3.mLastsetTargetPositionId     // Catch:{ all -> 0x008a }
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r0)     // Catch:{ all -> 0x008a }
            if (r0 == 0) goto L_0x0035
            java.lang.String r0 = "NAVIGATION_TAG"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x008a }
            r1.<init>()     // Catch:{ all -> 0x008a }
            java.lang.String r2 = "WaterNavigationHelper setTargetPosition error id: "
            r1.append(r2)     // Catch:{ all -> 0x008a }
            r1.append(r4)     // Catch:{ all -> 0x008a }
            java.lang.String r4 = " tagName: "
            r1.append(r4)     // Catch:{ all -> 0x008a }
            r1.append(r5)     // Catch:{ all -> 0x008a }
            java.lang.String r4 = r1.toString()     // Catch:{ all -> 0x008a }
            com.ciot.base.util.MyLogUtils.Logd(r0, r4)     // Catch:{ all -> 0x008a }
            boolean r4 = r3.mSendMoveSuccess     // Catch:{ all -> 0x008a }
            monitor-exit(r3)
            return r4
        L_0x0035:
            java.lang.String r0 = "NAVIGATION_TAG"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x008a }
            r1.<init>()     // Catch:{ all -> 0x008a }
            java.lang.String r2 = "WaterNavigationHelper setTargetPosition success id: "
            r1.append(r2)     // Catch:{ all -> 0x008a }
            r1.append(r4)     // Catch:{ all -> 0x008a }
            java.lang.String r2 = " tagName: "
            r1.append(r2)     // Catch:{ all -> 0x008a }
            r1.append(r5)     // Catch:{ all -> 0x008a }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x008a }
            com.ciot.base.util.MyLogUtils.Logd(r0, r1)     // Catch:{ all -> 0x008a }
            r3.mLastsetTargetPositionId = r4     // Catch:{ all -> 0x008a }
            r4 = 0
            r3.mSendMoveSuccess = r4     // Catch:{ all -> 0x008a }
            com.ciot.navigation.navigation.NavigationHelper$Companion r4 = com.ciot.navigation.navigation.NavigationHelper.Companion     // Catch:{ all -> 0x008a }
            com.ciot.navigation.navigation.NavigationHelper r4 = r4.getInstance()     // Catch:{ all -> 0x008a }
            java.lang.String r4 = r4.getReceptionTag()     // Catch:{ all -> 0x008a }
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r4)     // Catch:{ all -> 0x008a }
            if (r4 == 0) goto L_0x0074
            com.ciot.waterchassis.WaterChassisHelper r4 = com.ciot.waterchassis.WaterChassisHelper.getInstance()     // Catch:{ all -> 0x008a }
            r0 = 1036831949(0x3dcccccd, float:0.1)
            r1 = 0
            r4.sendMoveByMarkerName(r5, r0, r1)     // Catch:{ all -> 0x008a }
            goto L_0x007b
        L_0x0074:
            com.ciot.waterchassis.WaterChassisHelper r4 = com.ciot.waterchassis.WaterChassisHelper.getInstance()     // Catch:{ all -> 0x008a }
            r4.sendMoveByMarkerName(r5)     // Catch:{ all -> 0x008a }
        L_0x007b:
            boolean r4 = com.blankj.utilcode.util.ThreadUtils.isMainThread()     // Catch:{ all -> 0x008a }
            if (r4 == 0) goto L_0x0083
            r4 = 1
            goto L_0x0088
        L_0x0083:
            r3.waitMoveResponse()     // Catch:{ all -> 0x008a }
            boolean r4 = r3.mSendMoveSuccess     // Catch:{ all -> 0x008a }
        L_0x0088:
            monitor-exit(r3)
            return r4
        L_0x008a:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ciot.navigation.navigation.WaterNavigationHelper.setTargetPosition(java.lang.String, java.lang.String):boolean");
    }

    private final void waitMoveResponse() {
        this.mWaitTime = 0;
        int i = 0;
        while (!this.mSendMoveSuccess && this.mWaitTime < this.MAX_TIMEOUT_TIMES) {
            SystemClock.sleep(100);
            if (this.mWaitTime < this.MAX_TIMEOUT_TIMES) {
                i++;
                this.mWaitTime = i;
            }
        }
        this.mWaitTime = 0;
        MyLogUtils.Logd("NAVIGATION_TAG", "SendMove： " + this.mSendMoveSuccess);
    }

    public boolean setTargetPosition(float f, float f2, float f3) {
        this.mSendMoveSuccess = false;
        setTargetPosition(f, f2, f3, 0.01f);
        if (ThreadUtils.isMainThread()) {
            return true;
        }
        waitMoveResponse();
        return this.mSendMoveSuccess;
    }

    public void queryAllMarkers() {
        WaterChassisHelper.getInstance().sendGetMarkerList((Integer) null);
    }

    public void getBetweenPath(float f, float f2, int i, float f3, float f4, int i2, String str) {
        Intrinsics.checkNotNullParameter(str, "uuid");
        Map linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("start_x", Float.valueOf(f));
        linkedHashMap.put("start_y", Float.valueOf(f2));
        linkedHashMap.put("start_floor", Integer.valueOf(i));
        linkedHashMap.put("goal_x", Float.valueOf(f3));
        linkedHashMap.put("goal_y", Float.valueOf(f4));
        linkedHashMap.put("goal_y", Float.valueOf(f4));
        linkedHashMap.put("goal_floor", Integer.valueOf(i2));
        linkedHashMap.put("uuid", str);
        WaterChassisHelper.getInstance().sendGetBetweenPath(linkedHashMap);
    }

    public void detectNewVersion() {
        WaterChassisHelper.getInstance().detectNewVersion();
    }

    public void updateChassisVersion() {
        WaterChassisHelper.getInstance().updateChassisVersion();
    }

    public void insertMarker(String str, int i, int i2, double d, double d2, double d3) {
        String str2 = str;
        Intrinsics.checkNotNullParameter(str, ServiceProvider.NAME);
        if (i2 == 0) {
            boolean z = true;
            if (d == 0.0d) {
                if (d2 == 0.0d) {
                    if (d3 != 0.0d) {
                        z = false;
                    }
                    if (z) {
                        int i3 = i;
                        WaterChassisHelper.getInstance().sendInsertMarker(str, i);
                        return;
                    }
                }
            }
        }
        int i4 = i;
        WaterChassisHelper.getInstance().sendInsertMarkerByPose(str, i, i2, d, d2, d3);
    }

    public void deleteMarkerByName(String str) {
        Intrinsics.checkNotNullParameter(str, "markerName");
        WaterChassisHelper.getInstance().sendDeleteMarker(str);
    }

    public final void setTargetPosition(float f, float f2, float f3, float f4) {
        WaterChassisHelper.getInstance().sendMoveByLocationWithDistanceTolerance(f, f2, f3, f4);
    }

    public void sendGetMapList() {
        WaterChassisHelper.getInstance().sendGetMapList();
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/ciot/navigation/navigation/WaterNavigationHelper$WaterChassisHelerHolder;", "", "()V", "holder", "Lcom/ciot/navigation/navigation/WaterNavigationHelper;", "getHolder", "()Lcom/ciot/navigation/navigation/WaterNavigationHelper;", "setHolder", "(Lcom/ciot/navigation/navigation/WaterNavigationHelper;)V", "library-navigation_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: WaterNavigationHelper.kt */
    private static final class WaterChassisHelerHolder {
        public static final WaterChassisHelerHolder INSTANCE = new WaterChassisHelerHolder();
        private static WaterNavigationHelper holder = new WaterNavigationHelper((DefaultConstructorMarker) null);

        private WaterChassisHelerHolder() {
        }

        public final WaterNavigationHelper getHolder() {
            return holder;
        }

        public final void setHolder(WaterNavigationHelper waterNavigationHelper) {
            Intrinsics.checkNotNullParameter(waterNavigationHelper, "<set-?>");
            holder = waterNavigationHelper;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/ciot/navigation/navigation/WaterNavigationHelper$Companion;", "", "()V", "getInstance", "Lcom/ciot/navigation/navigation/WaterNavigationHelper;", "library-navigation_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: WaterNavigationHelper.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final WaterNavigationHelper getInstance() {
            return WaterChassisHelerHolder.INSTANCE.getHolder();
        }
    }
}
