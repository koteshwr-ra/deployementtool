package mc.csst.com.selfchassis;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.blankj.utilcode.util.AppUtils;
import com.ciot.base.constant.FileConstant;
import com.ciot.base.util.ContextUtil;
import com.ciot.base.util.MultiLanguageUtil;
import com.ciot.base.util.MyLogUtils;
import com.tencent.bugly.crashreport.CrashReport;
import mc.csst.com.selfchassis.utils.LayerDataUtils;
import xcrash.ICrashCallback;
import xcrash.XCrash;

public class App extends Application {
    public static volatile boolean isLockMap = false;
    public static volatile boolean isScram = false;
    private static volatile boolean mIsMultiNaving = false;
    private static volatile boolean mIsSingleNaving = false;
    private static volatile int mNavType;
    private static App sApp;
    private volatile boolean mOpenLife = false;
    private int mSpeedLevel = 1;

    public void onCreate() {
        super.onCreate();
        sApp = this;
        init();
        LayerDataUtils.getInstance().init();
    }

    private void init() {
        ContextUtil.setContext(this);
        MultiLanguageUtil.init(this);
        MultiLanguageUtil.getInstance().setConfiguration();
        registerActivityLifecycleCallbacks();
        initCrashTools();
        initLog();
        initBugly();
    }

    private void initCrashTools() {
        XCrash.InitParameters initParameters = new XCrash.InitParameters();
        initParameters.setLogDir(FileConstant.LOG_CRASH_PATH);
        initParameters.setAppVersion(AppUtils.getAppVersionName());
        AnonymousClass1 r1 = new ICrashCallback() {
            public void onCrash(String str, String str2) {
            }
        };
        initParameters.setJavaCallback(r1);
        initParameters.setAnrCallback(r1);
        initParameters.setNativeCallback(r1);
        XCrash.init(this, initParameters);
    }

    private void registerActivityLifecycleCallbacks() {
        registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            public void onActivityDestroyed(Activity activity) {
            }

            public void onActivityPaused(Activity activity) {
            }

            public void onActivityResumed(Activity activity) {
            }

            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            public void onActivityStarted(Activity activity) {
            }

            public void onActivityStopped(Activity activity) {
            }

            public void onActivityCreated(Activity activity, Bundle bundle) {
                MyLogUtils.Logd(" 对Application和Activity更新上下文的语言环境:" + activity.getClass().getSimpleName());
                MultiLanguageUtil.getInstance().setConfiguration();
            }
        });
    }

    public static int getNavType() {
        return mNavType;
    }

    public static void setNavType(int i) {
        mNavType = i;
    }

    public static boolean isMultiNaving() {
        return mIsMultiNaving;
    }

    public static void setIsMultiNaving(boolean z) {
        mIsMultiNaving = z;
    }

    public static boolean isSingleNaving() {
        return mIsSingleNaving;
    }

    public static void setIsSingleNaving(boolean z) {
        mIsSingleNaving = z;
    }

    public boolean isOpenLife() {
        return this.mOpenLife;
    }

    public void setOpenLife(boolean z) {
        this.mOpenLife = z;
    }

    public int getSpeedLevel() {
        return this.mSpeedLevel;
    }

    public void setSpeedLevel(int i) {
        this.mSpeedLevel = i;
    }

    public float getChassisSpeed() {
        int i = this.mSpeedLevel;
        if (i == 0) {
            return 0.3f;
        }
        return (i != 1 && i == 2) ? 0.8f : 0.5f;
    }

    private void initBugly() {
        CrashReport.initCrashReport(getApplicationContext(), BuildConfig.BuglyAppId, false);
        CrashReport.setUserId("DeploymentTool");
    }

    private void initLog() {
        MyLogUtils.Builder builder = new MyLogUtils.Builder();
        builder.setLogSwitch(true);
        builder.setLog2FileSwitch(true);
        MyLogUtils.checkLog();
    }

    public static App getInstance() {
        return sApp;
    }
}
