package com.ciot.navigation.navigation;

import android.os.Handler;
import android.os.HandlerThread;
import com.ciot.base.config.NavigationConfig;
import com.ciot.base.storage.MySpUtils;
import com.ciot.base.util.MyLogUtils;
import com.limpoxe.support.servicemanager.ServiceProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import mc.csst.com.selfchassislibrary.chassis.SelfChassis;
import mc.csst.com.selfchassislibrary.navigation.NavigationManager;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u0006\n\u0002\b\u000f\u0018\u0000 )2\u00020\u0001:\u0002)*B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\bH\u0016J@\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\nH\u0016J\b\u0010\u0016\u001a\u00020\bH\u0016J8\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001cH\u0016J\b\u0010\u001f\u001a\u00020\bH\u0016J\b\u0010 \u001a\u00020\bH\u0016J\b\u0010!\u001a\u00020\bH\u0002J\b\u0010\"\u001a\u00020\bH\u0016J\b\u0010#\u001a\u00020\bH\u0016J \u0010$\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020\u000eH\u0016J\u0018\u0010$\u001a\u00020\u00062\u0006\u0010&\u001a\u00020\n2\u0006\u0010'\u001a\u00020\nH\u0016J\b\u0010(\u001a\u00020\bH\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/ciot/navigation/navigation/SelfNavigationHelper;", "Lcom/ciot/navigation/navigation/INavigationHelper;", "()V", "wHandler", "Landroid/os/Handler;", "cancelNavigation", "", "deleteMarkerByName", "", "markerName", "", "detectNewVersion", "getBetweenPath", "start_x", "", "start_y", "start_floor", "", "goal_x", "goal_y", "goal_floor", "uuid", "init", "insertMarker", "name", "type", "floor", "x", "", "y", "theta", "pause", "queryAllMarkers", "reConnectX86", "release", "sendGetMapList", "setTargetPosition", "radians", "id", "tagName", "updateChassisVersion", "Companion", "SelfChassisHelperHolder", "library-navigation_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: SelfNavigationHelper.kt */
public final class SelfNavigationHelper implements INavigationHelper {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "NAVIGATION_TAG";
    private Handler wHandler;

    public /* synthetic */ SelfNavigationHelper(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public void detectNewVersion() {
    }

    public void getBetweenPath(float f, float f2, int i, float f3, float f4, int i2, String str) {
        Intrinsics.checkNotNullParameter(str, "uuid");
    }

    public void sendGetMapList() {
    }

    public void updateChassisVersion() {
    }

    private SelfNavigationHelper() {
    }

    public void init() {
        reConnectX86();
    }

    public boolean cancelNavigation() {
        return NavigationManager.getInstance().cancelNavigation();
    }

    public void pause() {
        SelfChassis.getInstance().sendCancelMove();
    }

    public void release() {
        NavigationManager.getInstance().release();
    }

    public boolean setTargetPosition(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(str2, "tagName");
        NavigationManager.getInstance().sendTarget(str, str2);
        return true;
    }

    public boolean setTargetPosition(float f, float f2, float f3) {
        NavigationManager.getInstance().sendTarget(f, f2, f3);
        return true;
    }

    public void queryAllMarkers() {
        SelfChassis.getInstance().sendGetMarkerList();
    }

    public void insertMarker(String str, int i, int i2, double d, double d2, double d3) {
        Intrinsics.checkNotNullParameter(str, ServiceProvider.NAME);
        SelfChassis.getInstance().sendInsertMarkerByPose(str, i, (float) d, (float) d2, (float) d3);
    }

    public void deleteMarkerByName(String str) {
        Intrinsics.checkNotNullParameter(str, "markerName");
        SelfChassis.getInstance().sendDeleteMarker(str);
    }

    private final void reConnectX86() {
        if (this.wHandler == null) {
            HandlerThread handlerThread = new HandlerThread("navigate-load");
            handlerThread.start();
            this.wHandler = new Handler(handlerThread.getLooper());
        }
        Handler handler = this.wHandler;
        if (handler != null) {
            handler.post($$Lambda$SelfNavigationHelper$pX_5SeNU3LWjIfZ9wP2ZViKfs3s.INSTANCE);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: reConnectX86$lambda-0  reason: not valid java name */
    public static final void m51reConnectX86$lambda0() {
        String string = MySpUtils.getInstance().getString(NavigationConfig.NAVIGATION_X86_URL, "ws://192.168.20.22:9090");
        NavigationManager.getInstance().connect(string);
        String str = TAG;
        MyLogUtils.Logd(str, "NavigationHelper init,connectUrl =:" + string);
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/ciot/navigation/navigation/SelfNavigationHelper$SelfChassisHelperHolder;", "", "()V", "holder", "Lcom/ciot/navigation/navigation/SelfNavigationHelper;", "getHolder", "()Lcom/ciot/navigation/navigation/SelfNavigationHelper;", "setHolder", "(Lcom/ciot/navigation/navigation/SelfNavigationHelper;)V", "library-navigation_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: SelfNavigationHelper.kt */
    private static final class SelfChassisHelperHolder {
        public static final SelfChassisHelperHolder INSTANCE = new SelfChassisHelperHolder();
        private static SelfNavigationHelper holder = new SelfNavigationHelper((DefaultConstructorMarker) null);

        private SelfChassisHelperHolder() {
        }

        public final SelfNavigationHelper getHolder() {
            return holder;
        }

        public final void setHolder(SelfNavigationHelper selfNavigationHelper) {
            Intrinsics.checkNotNullParameter(selfNavigationHelper, "<set-?>");
            holder = selfNavigationHelper;
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006R\u000e\u0010\u0003\u001a\u00020\u0004XD¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/ciot/navigation/navigation/SelfNavigationHelper$Companion;", "", "()V", "TAG", "", "getInstance", "Lcom/ciot/navigation/navigation/SelfNavigationHelper;", "library-navigation_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: SelfNavigationHelper.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SelfNavigationHelper getInstance() {
            return SelfChassisHelperHolder.INSTANCE.getHolder();
        }
    }
}
