package com.tencent.smtt.sdk;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.ValueCallback;
import androidx.core.os.EnvironmentCompat;
import androidx.core.provider.FontsContractCompat;
import com.alibaba.android.arouter.utils.Consts;
import com.ciot.base.config.SpeechConstants;
import com.ciot.base.constant.NetConstant;
import com.tencent.bugly.Bugly;
import com.tencent.smtt.export.external.DexLoader;
import com.tencent.smtt.export.external.TbsCoreSettings;
import com.tencent.smtt.export.external.interfaces.IX5WebViewBase;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.TbsDownloadUpload;
import com.tencent.smtt.sdk.TbsDownloader;
import com.tencent.smtt.sdk.TbsListener;
import com.tencent.smtt.sdk.stat.MttLoader;
import com.tencent.smtt.sdk.ui.dialog.d;
import com.tencent.smtt.sdk.ui.dialog.e;
import com.tencent.smtt.utils.FileProvider;
import com.tencent.smtt.utils.FileUtil;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.TbsLogClient;
import com.tencent.smtt.utils.b;
import com.tencent.smtt.utils.i;
import com.tencent.smtt.utils.l;
import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class QbSdk {
    private static String A = null;
    private static String B = null;
    /* access modifiers changed from: private */
    public static boolean C = false;
    private static boolean D = true;
    /* access modifiers changed from: private */
    public static TbsListener E = null;
    public static final int EXTENSION_INIT_FAILURE = -99999;
    /* access modifiers changed from: private */
    public static TbsListener F = null;
    public static final String FILERADER_MENUDATA = "menuData";
    private static boolean G = false;
    private static boolean H = false;
    public static final String KEY_SET_SENDREQUEST_AND_UPLOAD = "SET_SENDREQUEST_AND_UPLOAD";
    public static final String LOGIN_TYPE_KEY_PARTNER_CALL_POS = "PosID";
    public static final String LOGIN_TYPE_KEY_PARTNER_ID = "ChannelID";
    public static final String PARAM_KEY_FEATUREID = "param_key_featureid";
    public static final String PARAM_KEY_FUNCTIONID = "param_key_functionid";
    public static final String PARAM_KEY_POSITIONID = "param_key_positionid";
    public static final int QBMODE = 2;
    public static final String SHARE_PREFERENCES_NAME = "tbs_file_open_dialog_config";
    public static final String SVNVERSION = "jnizz";
    public static final int TBSMODE = 1;
    public static final String TID_QQNumber_Prefix = "QQ:";
    public static final int VERSION = 1;
    static boolean a = false;
    static boolean b = false;
    static boolean c = true;
    static String d = null;
    static boolean e = false;
    static long f = 0;
    static long g = 0;
    static Object h = new Object();
    static boolean i = true;
    public static boolean isDefaultDialog = false;
    static boolean j = true;
    static boolean k = false;
    static volatile boolean l = a;
    static TbsListener m = new TbsListener() {
        public void onDownloadFinish(int i) {
            if (TbsDownloader.needDownloadDecoupleCore()) {
                TbsLog.i("QbSdk", "onDownloadFinish needDownloadDecoupleCore is true", true);
                TbsDownloader.a = true;
                return;
            }
            TbsLog.i("QbSdk", "onDownloadFinish needDownloadDecoupleCore is false", true);
            TbsDownloader.a = false;
            if (QbSdk.E != null) {
                QbSdk.E.onDownloadFinish(i);
            }
            if (QbSdk.F != null) {
                QbSdk.F.onDownloadFinish(i);
            }
        }

        public void onDownloadProgress(int i) {
            if (QbSdk.F != null) {
                QbSdk.F.onDownloadProgress(i);
            }
            if (QbSdk.E != null) {
                QbSdk.E.onDownloadProgress(i);
            }
        }

        public void onInstallFinish(int i) {
            if (i != 200) {
            }
            boolean z = false;
            QbSdk.setTBSInstallingStatus(false);
            TbsDownloader.a = false;
            if (TbsDownloader.startDecoupleCoreIfNeeded()) {
                z = true;
            }
            TbsDownloader.a = z;
            if (QbSdk.E != null) {
                QbSdk.E.onInstallFinish(i);
            }
            if (QbSdk.F != null) {
                QbSdk.F.onInstallFinish(i);
            }
        }
    };
    public static boolean mDisableUseHostBackupCore = false;
    static Map<String, Object> n = null;
    private static int o = 0;
    private static String p = "";
    private static Class<?> q = null;
    private static Object r = null;
    private static boolean s = false;
    public static boolean sIsVersionPrinted = false;
    private static String[] t = null;
    private static String u = "NULL";
    private static String v = "UNKNOWN";
    private static boolean w = false;
    private static boolean x = true;
    private static int y = 0;
    private static int z = TbsListener.ErrorCode.NEEDDOWNLOAD_TRUE;

    public interface PreInitCallback {
        void onCoreInitFinished();

        void onViewInitFinished(boolean z);
    }

    static Bundle a(Context context, Bundle bundle) throws Exception {
        TbsLogReport instance;
        String str;
        if (!a(context)) {
            instance = TbsLogReport.getInstance(context);
            str = "initForPatch return false!";
        } else {
            Object a2 = i.a(r, "incrUpdate", (Class<?>[]) new Class[]{Context.class, Bundle.class}, context, bundle);
            if (a2 != null) {
                return (Bundle) a2;
            }
            instance = TbsLogReport.getInstance(context);
            str = "incrUpdate return null!";
        }
        instance.setInstallErrorCode((int) TbsListener.ErrorCode.INCR_UPDATE_ERROR, str);
        return null;
    }

    private static Bundle a(Context context, Map<String, String> map) {
        if (map == null) {
            return null;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putString("style", map.get("style") == null ? NetConstant.PAGE_ID_HOME : map.get("style"));
            try {
                bundle.putInt("topBarBgColor", Color.parseColor(map.get("topBarBgColor")));
            } catch (Exception unused) {
            }
            if (map != null) {
                if (map.containsKey(FILERADER_MENUDATA)) {
                    JSONObject jSONObject = new JSONObject(map.get(FILERADER_MENUDATA));
                    JSONArray jSONArray = jSONObject.getJSONArray("menuItems");
                    if (jSONArray != null) {
                        ArrayList arrayList = new ArrayList();
                        int i2 = 0;
                        while (i2 < jSONArray.length() && i2 < 5) {
                            try {
                                JSONObject jSONObject2 = (JSONObject) jSONArray.get(i2);
                                arrayList.add(i2, BitmapFactory.decodeResource(context.getResources(), jSONObject2.getInt("iconResId")));
                                jSONObject2.put("iconResId", i2);
                            } catch (Exception unused2) {
                            }
                            i2++;
                        }
                        bundle.putParcelableArrayList("resArray", arrayList);
                    }
                    bundle.putString(FILERADER_MENUDATA, jSONObject.toString());
                }
            }
            return bundle;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    static Object a(Context context, String str, Bundle bundle) {
        if (!a(context)) {
            return Integer.valueOf(EXTENSION_INIT_FAILURE);
        }
        Object a2 = i.a(r, "miscCall", (Class<?>[]) new Class[]{String.class, Bundle.class}, str, bundle);
        if (a2 != null) {
            return a2;
        }
        return null;
    }

    static String a() {
        return p;
    }

    static void a(Context context, Integer num, Map<Integer, String> map) {
        if (a(context)) {
            i.a(r, "dispatchEmergencyCommand", (Class<?>[]) new Class[]{Integer.class, Map.class}, num, map);
        }
    }

    static synchronized void a(Context context, String str) {
        synchronized (QbSdk.class) {
            if (!a) {
                a = true;
                v = "forceSysWebViewInner: " + str;
                TbsLog.e("QbSdk", "QbSdk.SysWebViewForcedInner..." + v);
                TbsCoreLoadStat.getInstance().a(context, 401, new Throwable(v));
            }
        }
    }

    static boolean a(Context context) {
        try {
            if (q != null) {
                return true;
            }
            File r2 = o.a().r(context);
            if (r2 == null) {
                TbsLog.e("QbSdk", "QbSdk initExtension (false) optDir == null");
                return false;
            }
            File file = new File(r2, "tbs_sdk_extension_dex.jar");
            if (!file.exists()) {
                TbsLog.e("QbSdk", "QbSdk initExtension (false) dexFile.exists()=false", true);
                return false;
            }
            TbsLog.i("QbSdk", "new DexLoader #3 dexFile is " + file.getAbsolutePath());
            w.a().b(context);
            l.a(context);
            Context context2 = context;
            q = new DexLoader(file.getParent(), context2, new String[]{file.getAbsolutePath()}, r2.getAbsolutePath(), getSettings()).loadClass("com.tencent.tbs.sdk.extension.TbsSDKExtension");
            if (isEnableSensitiveApi() || i.a(q, "isSuiteableGetSensitative", (Class<?>[]) new Class[0], new Object[0]) != null) {
                loadTBSSDKExtension(context, file.getParent());
                return true;
            }
            TbsLog.e("QbSdk", "isSuiteableGetSensitative check failed,can not use x5");
            return false;
        } catch (Throwable th) {
            TbsLog.e("QbSdk", "initExtension sys WebView: " + Log.getStackTraceString(th));
            return false;
        }
    }

    static boolean a(Context context, int i2) {
        return a(context, i2, 20000);
    }

    static boolean a(Context context, int i2, int i3) {
        Map<String, Object> map = n;
        if (map != null && map.containsKey(KEY_SET_SENDREQUEST_AND_UPLOAD) && n.get(KEY_SET_SENDREQUEST_AND_UPLOAD).equals(Bugly.SDK_IS_DEV)) {
            TbsLog.i("QbSdk", "[QbSdk.isX5Disabled] -- SET_SENDREQUEST_AND_UPLOAD is false");
            return true;
        } else if (isEnableSensitiveApi() || i.a(q, "isSuiteableGetSensitative", (Class<?>[]) new Class[0], new Object[0]) != null) {
            o.a().b(context, f.a == 0);
            if (!c(context)) {
                return true;
            }
            Object a2 = i.a(r, "isX5Disabled", (Class<?>[]) new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE}, Integer.valueOf(i2), 43993, Integer.valueOf(i3));
            if (a2 == null && (a2 = i.a(r, "isX5Disabled", (Class<?>[]) new Class[]{Integer.TYPE, Integer.TYPE}, Integer.valueOf(i2), 43993)) == null) {
                return true;
            }
            return ((Boolean) a2).booleanValue();
        } else {
            TbsLog.e("QbSdk", "isSuiteableGetSensitative check failed,can not use x5");
            return false;
        }
    }

    private static boolean a(Context context, boolean z2) {
        int i2;
        File file;
        TbsCoreLoadStat instance;
        int i3;
        Exception exc;
        TbsLog.initIfNeed(context);
        if (!sIsVersionPrinted) {
            TbsLog.i("QbSdk", "svn revision: jnizz; SDK_VERSION_CODE: 43993; SDK_VERSION_NAME: 4.3.0.93");
            sIsVersionPrinted = true;
        }
        if (a && !z2) {
            TbsLog.e("QbSdk", "QbSdk init: " + v, false);
            TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.INFO_SDKINIT_IS_SYS_FORCED, new Throwable(v));
            return false;
        } else if (b) {
            TbsLog.e("QbSdk", "QbSdk init mIsSysWebViewForcedByOuter = true", true);
            TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.INFO_FORCE_SYSTEM_WEBVIEW_OUTER, new Throwable(u));
            return false;
        } else {
            if (!D) {
                d(context);
            }
            try {
                File r2 = o.a().r(context);
                if (r2 == null) {
                    TbsLog.e("QbSdk", "QbSdk init (false) optDir == null");
                    TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.ERROR_TBSCORE_SHARE_DIR, new Throwable("QbSdk.init (false) TbsCoreShareDir is null"));
                    return false;
                }
                if (TbsShareManager.isThirdPartyApp(context)) {
                    if (o == 0 || o == TbsShareManager.d(context)) {
                        i2 = TbsShareManager.d(context);
                    } else {
                        q = null;
                        r = null;
                        TbsLog.e("QbSdk", "QbSdk init (false) ERROR_UNMATCH_TBSCORE_VER_THIRDPARTY!");
                        TbsCoreLoadStat instance2 = TbsCoreLoadStat.getInstance();
                        instance2.a(context, TbsListener.ErrorCode.ERROR_UNMATCH_TBSCORE_VER_THIRDPARTY, new Throwable("sTbsVersion: " + o + "; AvailableTbsCoreVersion: " + TbsShareManager.d(context)));
                        return false;
                    }
                } else if (o != 0) {
                    i2 = o.a().a(true, context);
                    if (o != i2) {
                        q = null;
                        r = null;
                        TbsLog.e("QbSdk", "QbSdk init (false) not isThirdPartyApp tbsCoreInstalledVer=" + i2, true);
                        TbsLog.e("QbSdk", "QbSdk init (false) not isThirdPartyApp sTbsVersion=" + o, true);
                        TbsCoreLoadStat instance3 = TbsCoreLoadStat.getInstance();
                        instance3.a(context, TbsListener.ErrorCode.ERROR_UNMATCH_TBSCORE_VER, new Throwable("sTbsVersion: " + o + "; tbsCoreInstalledVer: " + i2));
                        return false;
                    }
                } else {
                    i2 = 0;
                }
                o = i2;
                if (TbsDownloader.a(context, o)) {
                    TbsLog.i("QbSdk", "version " + o + " is in blacklist,can not load! return");
                    return false;
                } else if (q != null && r != null) {
                    return true;
                } else {
                    if (!TbsShareManager.isThirdPartyApp(context)) {
                        file = new File(o.a().r(context), "tbs_sdk_extension_dex.jar");
                    } else if (TbsShareManager.j(context)) {
                        file = new File(TbsShareManager.c(context), "tbs_sdk_extension_dex.jar");
                    } else {
                        TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.ERROR_HOST_UNAVAILABLE, new Throwable("isShareTbsCoreAvailable false!"));
                        return false;
                    }
                    if (!file.exists()) {
                        TbsLog.e("QbSdk", "QbSdk init (false) tbs_sdk_extension_dex.jar is not exist!");
                        int j2 = o.a().j(context);
                        if (new File(file.getParentFile(), "tbs_jars_fusion_dex.jar").exists()) {
                            if (j2 > 0) {
                                instance = TbsCoreLoadStat.getInstance();
                                i3 = TbsListener.ErrorCode.INFO_MISS_SDKEXTENSION_JAR_WITH_FUSION_DEX_WITH_CORE;
                                exc = new Exception("tbs_sdk_extension_dex not exist(with fusion dex)!" + j2);
                            } else {
                                instance = TbsCoreLoadStat.getInstance();
                                i3 = TbsListener.ErrorCode.INFO_MISS_SDKEXTENSION_JAR_WITH_FUSION_DEX_WITHOUT_CORE;
                                exc = new Exception("tbs_sdk_extension_dex not exist(with fusion dex)!" + j2);
                            }
                        } else if (j2 > 0) {
                            instance = TbsCoreLoadStat.getInstance();
                            i3 = TbsListener.ErrorCode.INFO_INFO_MISS_SDKEXTENSION_JAR_WITHOUT_FUSION_DEX_WITH_CORE;
                            exc = new Exception("tbs_sdk_extension_dex not exist(without fusion dex)!" + j2);
                        } else {
                            instance = TbsCoreLoadStat.getInstance();
                            i3 = TbsListener.ErrorCode.INFO_INFO_MISS_SDKEXTENSION_JAR_WITHOUT_FUSION_DEX_WITHOUT_CORE;
                            exc = new Exception("tbs_sdk_extension_dex not exist(without fusion dex)!" + j2);
                        }
                        instance.a(context, i3, exc);
                        return false;
                    }
                    String hostCorePathAppDefined = TbsShareManager.getHostCorePathAppDefined() != null ? TbsShareManager.getHostCorePathAppDefined() : r2.getAbsolutePath();
                    TbsLog.i("QbSdk", "QbSdk init optDirExtension #1 is " + hostCorePathAppDefined);
                    TbsLog.i("QbSdk", "new DexLoader #1 dexFile is " + file.getAbsolutePath());
                    w.a().b(context);
                    l.a(context);
                    q = new DexLoader(file.getParent(), context, new String[]{file.getAbsolutePath()}, hostCorePathAppDefined, getSettings()).loadClass("com.tencent.tbs.sdk.extension.TbsSDKExtension");
                    if (isEnableSensitiveApi() || i.a(q, "isSuiteableGetSensitative", (Class<?>[]) new Class[0], new Object[0]) != null) {
                        loadTBSSDKExtension(context, file.getParent());
                        i.a(r, "setClientVersion", (Class<?>[]) new Class[]{Integer.TYPE}, 1);
                        return true;
                    }
                    TbsLog.e("QbSdk", "isSuiteableGetSensitative check failed,can not use x5");
                    return false;
                }
            } catch (Throwable th) {
                TbsLog.e("QbSdk", "QbSdk init Throwable: " + Log.getStackTraceString(th));
                TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.THROWABLE_QBSDK_INIT, th);
                return false;
            }
        }
    }

    static boolean a(Context context, boolean z2, boolean z3) {
        int i2;
        TbsCoreLoadStat tbsCoreLoadStat;
        Throwable th;
        int i3;
        boolean z4 = true;
        boolean z5 = false;
        o.a().b(context, f.a == 0);
        int disabledCoreVersion = TbsPVConfig.getInstance(context).getDisabledCoreVersion();
        if (disabledCoreVersion != 0 && disabledCoreVersion == o.a().j(context)) {
            TbsLog.e("QbSdk", "force use sys by remote switch");
            return false;
        } else if (!isEnableSensitiveApi() && i.a(r, "isSuiteableGetSensitative", (Class<?>[]) new Class[0], new Object[0]) == null) {
            TbsLog.e("QbSdk", "isSuiteableGetSensitative check failed,can not use x5");
            return false;
        } else if (TbsShareManager.isThirdPartyApp(context) && !TbsShareManager.i(context)) {
            TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.ERROR_UNMATCH_TBSCORE_VER_THIRDPARTY);
            return false;
        } else if (!a(context, z2)) {
            TbsLog.e("QbSdk", "QbSdk.init failure!");
            return false;
        } else {
            Object a2 = i.a(r, "canLoadX5Core", (Class<?>[]) new Class[]{Integer.TYPE}, 43993);
            if (a2 == null) {
                Object a3 = i.a(r, "canLoadX5", (Class<?>[]) new Class[]{Integer.TYPE}, Integer.valueOf(a.a()));
                if (a3 == null) {
                    TbsCoreLoadStat.getInstance().a(context, 308);
                } else if ((a3 instanceof String) && ((String) a3).equalsIgnoreCase("AuthenticationFail")) {
                    return false;
                } else {
                    if (a3 instanceof Boolean) {
                        o = f.d();
                        boolean a4 = a(context, f.d());
                        Boolean bool = (Boolean) a3;
                        if (!bool.booleanValue() || a4) {
                            z4 = false;
                        }
                        if (!z4) {
                            TbsLog.e(TbsListener.tag_load_error, "318");
                            TbsLog.w(TbsListener.tag_load_error, "isX5Disable:" + a4);
                            TbsLog.w(TbsListener.tag_load_error, "(Boolean) ret:" + bool);
                        }
                        return z4;
                    }
                }
            } else if ((a2 instanceof String) && ((String) a2).equalsIgnoreCase("AuthenticationFail")) {
                return false;
            } else {
                if (!(a2 instanceof Bundle)) {
                    TbsCoreLoadStat instance = TbsCoreLoadStat.getInstance();
                    instance.a(context, TbsListener.ErrorCode.ERROR_QBSDK_INIT_ERROR_RET_TYPE_NOT_BUNDLE, new Throwable("" + a2));
                    TbsLog.e(TbsListener.tag_load_error, "ret not instance of bundle");
                    return false;
                }
                Bundle bundle = (Bundle) a2;
                if (bundle.isEmpty()) {
                    TbsCoreLoadStat instance2 = TbsCoreLoadStat.getInstance();
                    instance2.a(context, TbsListener.ErrorCode.ERROR_QBSDK_INIT_ERROR_EMPTY_BUNDLE, new Throwable("" + a2));
                    TbsLog.e(TbsListener.tag_load_error, "empty bundle");
                    return false;
                }
                try {
                    i2 = bundle.getInt(FontsContractCompat.Columns.RESULT_CODE, -1);
                } catch (Exception e2) {
                    TbsLog.e("QbSdk", "bundle.getInt(KEY_RESULT_CODE) error : " + e2.toString());
                    i2 = -1;
                }
                boolean z6 = i2 == 0;
                if (TbsShareManager.isThirdPartyApp(context)) {
                    f.a(TbsShareManager.d(context));
                    String valueOf = String.valueOf(TbsShareManager.d(context));
                    p = valueOf;
                    if (valueOf.length() == 5) {
                        p = NetConstant.PAGE_ID_HOME + p;
                    }
                    if (p.length() != 6) {
                        p = "";
                    }
                } else {
                    try {
                        if (Build.VERSION.SDK_INT >= 12) {
                            p = bundle.getString("tbs_core_version", NetConstant.PAGE_ID_HOME);
                        } else {
                            String string = bundle.getString("tbs_core_version");
                            p = string;
                            if (string == null) {
                                p = NetConstant.PAGE_ID_HOME;
                            }
                        }
                    } catch (Exception unused) {
                        p = NetConstant.PAGE_ID_HOME;
                    }
                    try {
                        o = Integer.parseInt(p);
                    } catch (NumberFormatException unused2) {
                        o = 0;
                    }
                    f.a(o);
                    int i4 = o;
                    if (i4 == 0) {
                        TbsCoreLoadStat.getInstance().a(context, 307, new Throwable("sTbsVersion is 0"));
                        return false;
                    }
                    if ((i4 <= 0 || i4 > 25442) && o != 25472) {
                        z4 = false;
                    }
                    if (z4) {
                        TbsLog.e(TbsDownloader.LOGTAG, "is_obsolete --> delete old core:" + o);
                        FileUtil.b(o.a().r(context));
                        TbsCoreLoadStat instance3 = TbsCoreLoadStat.getInstance();
                        instance3.a(context, 307, new Throwable("is_obsolete --> delete old core:" + o));
                        return false;
                    }
                }
                try {
                    String[] stringArray = bundle.getStringArray("tbs_jarfiles");
                    t = stringArray;
                    if (!(stringArray instanceof String[])) {
                        TbsCoreLoadStat instance4 = TbsCoreLoadStat.getInstance();
                        instance4.a(context, 307, new Throwable("sJarFiles not instanceof String[]: " + t));
                        return false;
                    }
                    try {
                        d = bundle.getString("tbs_librarypath");
                        Object obj = null;
                        if (i2 != 0) {
                            try {
                                obj = i.a(r, "getErrorCodeForLogReport", (Class<?>[]) new Class[0], new Object[0]);
                            } catch (Exception e3) {
                                e3.printStackTrace();
                            }
                        }
                        if (i2 != -2) {
                            if (i2 != -1) {
                                if (i2 != 0) {
                                    TbsCoreLoadStat instance5 = TbsCoreLoadStat.getInstance();
                                    instance5.a(context, TbsListener.ErrorCode.INFO_INITX5_FALSE_DEFAULT, new Throwable("detail: " + obj + "errcode" + i2));
                                }
                            } else if (obj instanceof Integer) {
                                tbsCoreLoadStat = TbsCoreLoadStat.getInstance();
                                i3 = ((Integer) obj).intValue();
                                th = new Throwable("detail: " + obj);
                            } else {
                                TbsCoreLoadStat instance6 = TbsCoreLoadStat.getInstance();
                                instance6.a(context, 307, new Throwable("detail: " + obj));
                            }
                            z5 = z6;
                        } else if (obj instanceof Integer) {
                            tbsCoreLoadStat = TbsCoreLoadStat.getInstance();
                            i3 = ((Integer) obj).intValue();
                            th = new Throwable("detail: " + obj);
                        } else {
                            tbsCoreLoadStat = TbsCoreLoadStat.getInstance();
                            i3 = TbsListener.ErrorCode.INFO_DISABLE_X5;
                            th = new Throwable("detail: " + obj);
                        }
                        tbsCoreLoadStat.a(context, i3, th);
                        z5 = z6;
                    } catch (Exception unused3) {
                        return false;
                    }
                } catch (Throwable th2) {
                    TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.ERROR_GETSTRINGARRAY_JARFILE, th2);
                    return false;
                }
            }
            if (!z5) {
                TbsLog.e(TbsListener.tag_load_error, "319");
            }
            return z5;
        }
    }

    protected static String b() {
        Object invokeStaticMethod;
        w a2 = w.a();
        if (a2 == null || !a2.b() || (invokeStaticMethod = a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "getGUID", new Class[0], new Object[0])) == null || !(invokeStaticMethod instanceof String)) {
            return null;
        }
        return (String) invokeStaticMethod;
    }

    static boolean b(Context context) {
        if (context == null) {
            return false;
        }
        try {
            if (!context.getApplicationInfo().packageName.contains("com.tencent.portfolio")) {
                return true;
            }
            TbsLog.i("QbSdk", "clearPluginConfigFile #1");
            String string = TbsDownloadConfig.getInstance(context).mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_APP_VERSIONNAME, (String) null);
            String str = context.getPackageManager().getPackageInfo("com.tencent.portfolio", 0).versionName;
            TbsLog.i("QbSdk", "clearPluginConfigFile oldAppVersionName is " + string + " newAppVersionName is " + str);
            if (string == null) {
                return true;
            }
            if (string.contains(str)) {
                return true;
            }
            SharedPreferences sharedPreferences = context.getSharedPreferences("plugin_setting", 0);
            if (sharedPreferences == null) {
                return true;
            }
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.clear();
            edit.commit();
            TbsLog.i("QbSdk", "clearPluginConfigFile done");
            return true;
        } catch (Throwable th) {
            TbsLog.i("QbSdk", "clearPluginConfigFile error is " + th.getMessage());
            return false;
        }
    }

    private static boolean c(Context context) {
        File file;
        try {
            if (q != null) {
                return true;
            }
            File r2 = o.a().r(context);
            if (r2 == null) {
                TbsLog.e("QbSdk", "QbSdk initForX5DisableConfig (false) optDir == null");
                return false;
            }
            if (!TbsShareManager.isThirdPartyApp(context)) {
                file = new File(o.a().r(context), "tbs_sdk_extension_dex.jar");
            } else if (TbsShareManager.j(context)) {
                file = new File(TbsShareManager.c(context), "tbs_sdk_extension_dex.jar");
            } else {
                TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.ERROR_HOST_UNAVAILABLE);
                return false;
            }
            if (!file.exists()) {
                TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.INFO_MISS_SDKEXTENSION_JAR_OLD, new Exception("initForX5DisableConfig failure -- tbs_sdk_extension_dex.jar is not exist!"));
                return false;
            }
            String hostCorePathAppDefined = TbsShareManager.getHostCorePathAppDefined() != null ? TbsShareManager.getHostCorePathAppDefined() : r2.getAbsolutePath();
            TbsLog.i("QbSdk", "QbSdk init optDirExtension #3 is " + hostCorePathAppDefined);
            TbsLog.i("QbSdk", "new DexLoader #4 dexFile is " + file.getAbsolutePath());
            w.a().b(context);
            l.a(context);
            q = new DexLoader(file.getParent(), context, new String[]{file.getAbsolutePath()}, hostCorePathAppDefined, getSettings()).loadClass("com.tencent.tbs.sdk.extension.TbsSDKExtension");
            if (isEnableSensitiveApi() || i.a(q, "isSuiteableGetSensitative", (Class<?>[]) new Class[0], new Object[0]) != null) {
                loadTBSSDKExtension(context, file.getParent());
                i.a(r, "setClientVersion", (Class<?>[]) new Class[]{Integer.TYPE}, 1);
                return true;
            }
            TbsLog.e("QbSdk", "isSuiteableGetSensitative check failed,can not use x5");
            return false;
        } catch (Throwable th) {
            TbsLog.e("QbSdk", "initForX5DisableConfig sys WebView: " + Log.getStackTraceString(th));
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0036  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean canLoadVideo(android.content.Context r5) {
        /*
            java.lang.Object r0 = r
            r1 = 1
            java.lang.Class[] r2 = new java.lang.Class[r1]
            java.lang.Class r3 = java.lang.Integer.TYPE
            r4 = 0
            r2[r4] = r3
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.Integer r3 = java.lang.Integer.valueOf(r4)
            r1[r4] = r3
            java.lang.String r3 = "canLoadVideo"
            java.lang.Object r0 = com.tencent.smtt.utils.i.a((java.lang.Object) r0, (java.lang.String) r3, (java.lang.Class<?>[]) r2, (java.lang.Object[]) r1)
            if (r0 == 0) goto L_0x002a
            r1 = r0
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 != 0) goto L_0x0033
            com.tencent.smtt.sdk.TbsCoreLoadStat r1 = com.tencent.smtt.sdk.TbsCoreLoadStat.getInstance()
            r2 = 313(0x139, float:4.39E-43)
            goto L_0x0030
        L_0x002a:
            com.tencent.smtt.sdk.TbsCoreLoadStat r1 = com.tencent.smtt.sdk.TbsCoreLoadStat.getInstance()
            r2 = 314(0x13a, float:4.4E-43)
        L_0x0030:
            r1.a(r5, r2)
        L_0x0033:
            if (r0 != 0) goto L_0x0036
            goto L_0x003c
        L_0x0036:
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r4 = r0.booleanValue()
        L_0x003c:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.QbSdk.canLoadVideo(android.content.Context):boolean");
    }

    public static boolean canLoadX5(Context context) {
        return a(context, false, false);
    }

    public static boolean canLoadX5FirstTimeThirdApp(Context context) {
        try {
            if (context.getApplicationInfo().packageName.contains("com.moji.mjweather") && Build.VERSION.SDK_INT == 19) {
                return true;
            }
            if (q == null) {
                File r2 = o.a().r(context);
                if (r2 == null) {
                    TbsLog.e("QbSdk", "QbSdk canLoadX5FirstTimeThirdApp (false) optDir == null");
                    return false;
                }
                File file = new File(TbsShareManager.c(context), "tbs_sdk_extension_dex.jar");
                if (!file.exists()) {
                    TbsLog.e("QbSdk", "QbSdk canLoadX5FirstTimeThirdApp (false) dexFile.exists()=false", true);
                    return false;
                }
                String hostCorePathAppDefined = TbsShareManager.getHostCorePathAppDefined() != null ? TbsShareManager.getHostCorePathAppDefined() : r2.getAbsolutePath();
                TbsLog.i("QbSdk", "QbSdk init optDirExtension #2 is " + hostCorePathAppDefined);
                TbsLog.i("QbSdk", "new DexLoader #2 dexFile is " + file.getAbsolutePath());
                w.a().b(context);
                l.a(context);
                q = new DexLoader(file.getParent(), context, new String[]{file.getAbsolutePath()}, hostCorePathAppDefined, getSettings()).loadClass("com.tencent.tbs.sdk.extension.TbsSDKExtension");
                if (r == null) {
                    if (TbsShareManager.e(context) == null && TbsShareManager.getHostCorePathAppDefined() == null) {
                        TbsLogReport.getInstance(context.getApplicationContext()).setLoadErrorCode((int) TbsListener.ErrorCode.HOST_CONTEXT_IS_NULL, "host context is null!");
                        return false;
                    } else if (isEnableSensitiveApi() || i.a(q, "isSuiteableGetSensitative", (Class<?>[]) new Class[0], new Object[0]) != null) {
                        loadTBSSDKExtension(context, file.getParent());
                    } else {
                        TbsLog.e("QbSdk", "isSuiteableGetSensitative check failed,can not use x5");
                        return false;
                    }
                }
            }
            Object a2 = i.a(r, "canLoadX5CoreForThirdApp", (Class<?>[]) new Class[0], new Object[0]);
            if (a2 == null || !(a2 instanceof Boolean)) {
                return false;
            }
            return ((Boolean) a2).booleanValue();
        } catch (Throwable th) {
            TbsLog.e("QbSdk", "canLoadX5FirstTimeThirdApp sys WebView: " + Log.getStackTraceString(th));
            return false;
        }
    }

    public static void canOpenFile(final Context context, final String str, final ValueCallback<Boolean> valueCallback) {
        new Thread() {
            public void run() {
                w a2 = w.a();
                a2.a(context);
                final boolean a3 = a2.b() ? a2.c().a(context, str) : false;
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        valueCallback.onReceiveValue(Boolean.valueOf(a3));
                    }
                });
            }
        }.start();
    }

    public static boolean canOpenMimeFileType(Context context, String str) {
        boolean a2 = a(context, false);
        return false;
    }

    /* JADX WARNING: type inference failed for: r6v4, types: [java.io.FileInputStream, java.io.InputStream] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00f0 A[SYNTHETIC, Splitter:B:50:0x00f0] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0124 A[SYNTHETIC, Splitter:B:75:0x0124] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean canOpenWebPlus(android.content.Context r9) {
        /*
            int r0 = y
            if (r0 != 0) goto L_0x000a
            int r0 = com.tencent.smtt.sdk.a.a()
            y = r0
        L_0x000a:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "canOpenWebPlus - totalRAM: "
            r0.append(r1)
            int r1 = y
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "QbSdk"
            com.tencent.smtt.utils.TbsLog.i(r1, r0)
            int r0 = android.os.Build.VERSION.SDK_INT
            r2 = 7
            r3 = 0
            if (r0 < r2) goto L_0x012f
            int r0 = y
            int r2 = z
            if (r0 >= r2) goto L_0x0030
            goto L_0x012f
        L_0x0030:
            if (r9 != 0) goto L_0x0033
            return r3
        L_0x0033:
            r0 = 0
            java.io.File r2 = new java.io.File     // Catch:{ all -> 0x0119 }
            com.tencent.smtt.sdk.o r4 = com.tencent.smtt.sdk.o.a()     // Catch:{ all -> 0x0119 }
            java.io.File r4 = r4.r(r9)     // Catch:{ all -> 0x0119 }
            java.lang.String r5 = "tbs.conf"
            r2.<init>(r4, r5)     // Catch:{ all -> 0x0119 }
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ all -> 0x0119 }
            r4.<init>(r2)     // Catch:{ all -> 0x0119 }
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ all -> 0x0119 }
            r2.<init>(r4)     // Catch:{ all -> 0x0119 }
            java.util.Properties r4 = new java.util.Properties     // Catch:{ all -> 0x0116 }
            r4.<init>()     // Catch:{ all -> 0x0116 }
            r4.load(r2)     // Catch:{ all -> 0x0116 }
            java.lang.String r5 = "android_sdk_max_supported"
            java.lang.String r5 = r4.getProperty(r5)     // Catch:{ all -> 0x0116 }
            java.lang.String r6 = "android_sdk_min_supported"
            java.lang.String r6 = r4.getProperty(r6)     // Catch:{ all -> 0x0116 }
            int r5 = java.lang.Integer.parseInt(r5)     // Catch:{ all -> 0x0116 }
            int r6 = java.lang.Integer.parseInt(r6)     // Catch:{ all -> 0x0116 }
            java.lang.String r7 = android.os.Build.VERSION.SDK     // Catch:{ all -> 0x0116 }
            int r7 = java.lang.Integer.parseInt(r7)     // Catch:{ all -> 0x0116 }
            if (r7 > r5) goto L_0x00fe
            if (r7 >= r6) goto L_0x0075
            goto L_0x00fe
        L_0x0075:
            java.lang.String r5 = "tbs_core_version"
            java.lang.String r4 = r4.getProperty(r5)     // Catch:{ all -> 0x0116 }
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ all -> 0x0116 }
            r2.close()     // Catch:{ Exception -> 0x0082 }
        L_0x0082:
            r2 = 1
            java.io.File r5 = new java.io.File     // Catch:{ all -> 0x00e9 }
            java.io.File r6 = com.tencent.smtt.sdk.o.t(r9)     // Catch:{ all -> 0x00e9 }
            java.lang.String r7 = "tbs_extension.conf"
            r5.<init>(r6, r7)     // Catch:{ all -> 0x00e9 }
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch:{ all -> 0x00e9 }
            r6.<init>(r5)     // Catch:{ all -> 0x00e9 }
            java.util.Properties r0 = new java.util.Properties     // Catch:{ all -> 0x00e8 }
            r0.<init>()     // Catch:{ all -> 0x00e8 }
            r0.load(r6)     // Catch:{ all -> 0x00e8 }
            java.lang.String r5 = "tbs_local_version"
            java.lang.String r5 = r0.getProperty(r5)     // Catch:{ all -> 0x00e8 }
            int r5 = java.lang.Integer.parseInt(r5)     // Catch:{ all -> 0x00e8 }
            java.lang.String r7 = "app_versioncode_for_switch"
            java.lang.String r7 = r0.getProperty(r7)     // Catch:{ all -> 0x00e8 }
            int r7 = java.lang.Integer.parseInt(r7)     // Catch:{ all -> 0x00e8 }
            r8 = 88888888(0x54c5638, float:9.60787E-36)
            if (r4 == r8) goto L_0x00e4
            if (r5 != r8) goto L_0x00b7
            goto L_0x00e4
        L_0x00b7:
            if (r4 <= r5) goto L_0x00ba
            goto L_0x00e4
        L_0x00ba:
            if (r4 != r5) goto L_0x00e4
            if (r7 <= 0) goto L_0x00c5
            int r4 = com.tencent.smtt.utils.b.d(r9)     // Catch:{ all -> 0x00e8 }
            if (r7 == r4) goto L_0x00c5
            goto L_0x00e4
        L_0x00c5:
            java.lang.String r4 = "x5_disabled"
            java.lang.String r0 = r0.getProperty(r4)     // Catch:{ all -> 0x00e8 }
            boolean r0 = java.lang.Boolean.parseBoolean(r0)     // Catch:{ all -> 0x00e8 }
            if (r0 == 0) goto L_0x00e4
            android.content.Context r9 = r9.getApplicationContext()     // Catch:{ all -> 0x00e8 }
            com.tencent.smtt.sdk.TbsDownloadConfig r9 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r9)     // Catch:{ all -> 0x00e8 }
            android.content.SharedPreferences r9 = r9.mPreferences     // Catch:{ all -> 0x00e8 }
            java.lang.String r0 = "switch_backupcore_enable"
            boolean r9 = r9.getBoolean(r0, r3)     // Catch:{ all -> 0x00e8 }
            if (r9 != 0) goto L_0x00e4
            r3 = 1
        L_0x00e4:
            r6.close()     // Catch:{ Exception -> 0x00f4 }
            goto L_0x00f4
        L_0x00e8:
            r0 = r6
        L_0x00e9:
            java.lang.String r9 = "canOpenWebPlus - isX5Disabled Exception"
            com.tencent.smtt.utils.TbsLog.i(r1, r9)     // Catch:{ all -> 0x00f7 }
            if (r0 == 0) goto L_0x00f3
            r0.close()     // Catch:{ Exception -> 0x00f3 }
        L_0x00f3:
            r3 = 1
        L_0x00f4:
            r9 = r3 ^ 1
            return r9
        L_0x00f7:
            r9 = move-exception
            if (r0 == 0) goto L_0x00fd
            r0.close()     // Catch:{ Exception -> 0x00fd }
        L_0x00fd:
            throw r9
        L_0x00fe:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x0116 }
            r9.<init>()     // Catch:{ all -> 0x0116 }
            java.lang.String r0 = "canOpenWebPlus - sdkVersion: "
            r9.append(r0)     // Catch:{ all -> 0x0116 }
            r9.append(r7)     // Catch:{ all -> 0x0116 }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x0116 }
            com.tencent.smtt.utils.TbsLog.i(r1, r9)     // Catch:{ all -> 0x0116 }
            r2.close()     // Catch:{ Exception -> 0x0115 }
        L_0x0115:
            return r3
        L_0x0116:
            r9 = move-exception
            r0 = r2
            goto L_0x011a
        L_0x0119:
            r9 = move-exception
        L_0x011a:
            r9.printStackTrace()     // Catch:{ all -> 0x0128 }
            java.lang.String r9 = "canOpenWebPlus - canLoadX5 Exception"
            com.tencent.smtt.utils.TbsLog.i(r1, r9)     // Catch:{ all -> 0x0128 }
            if (r0 == 0) goto L_0x0127
            r0.close()     // Catch:{ Exception -> 0x0127 }
        L_0x0127:
            return r3
        L_0x0128:
            r9 = move-exception
            if (r0 == 0) goto L_0x012e
            r0.close()     // Catch:{ Exception -> 0x012e }
        L_0x012e:
            throw r9
        L_0x012f:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.QbSdk.canOpenWebPlus(android.content.Context):boolean");
    }

    public static boolean canUseVideoFeatrue(Context context, int i2) {
        Object a2 = i.a(r, "canUseVideoFeatrue", (Class<?>[]) new Class[]{Integer.TYPE}, Integer.valueOf(i2));
        if (a2 == null || !(a2 instanceof Boolean)) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    public static boolean checkApkExist(Context context, String str) {
        if (str != null && !"".equals(str)) {
            try {
                context.getPackageManager().getApplicationInfo(str, 8192);
                return true;
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return false;
    }

    public static boolean checkContentProviderPrivilage(Context context) {
        if (context == null || context.getApplicationInfo().targetSdkVersion < 24 || Build.VERSION.SDK_INT < 24 || TbsConfig.APP_QQ.equals(context.getApplicationInfo().packageName)) {
            return true;
        }
        try {
            if (!TextUtils.isEmpty(context.getPackageManager().getProviderInfo(new ComponentName(context.getPackageName(), "androidx.core.content.FileProvider"), 0).authority)) {
                return true;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        PackageManager packageManager = context.getPackageManager();
        ProviderInfo resolveContentProvider = packageManager.resolveContentProvider(context.getApplicationInfo().packageName + ".provider", 128);
        if (resolveContentProvider == null) {
            Log.e("QbSdk", "Must declare com.tencent.smtt.utils.FileProvider in AndroidManifest above Android 7.0,please view document in x5.tencent.com");
        }
        return resolveContentProvider != null;
    }

    public static void checkTbsValidity(Context context) {
        if (context != null && !l.b(context)) {
            TbsLog.e("QbSdk", "sys WebView: SysWebViewForcedBy checkTbsValidity");
            TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.INFO_CORE_CHECK_VALIDITY_FALSE);
            forceSysWebView();
        }
    }

    public static void clear(Context context) {
    }

    public static void clearAllDefaultBrowser(Context context) {
        context.getSharedPreferences(SHARE_PREFERENCES_NAME, 0).edit().clear().commit();
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x006c A[SYNTHETIC, Splitter:B:19:0x006c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void clearAllWebViewCache(android.content.Context r6, boolean r7) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "clearAllWebViewCache("
            r0.append(r1)
            r0.append(r6)
            java.lang.String r1 = ", "
            r0.append(r1)
            r0.append(r7)
            java.lang.String r1 = ")"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "QbSdk"
            com.tencent.smtt.utils.TbsLog.i(r1, r0)
            r0 = 1
            r2 = 0
            com.tencent.smtt.sdk.WebView r3 = new com.tencent.smtt.sdk.WebView     // Catch:{ all -> 0x0048 }
            r3.<init>(r6)     // Catch:{ all -> 0x0048 }
            com.tencent.smtt.export.external.extension.interfaces.IX5WebViewClientExtension r3 = r3.getWebViewClientExtension()     // Catch:{ all -> 0x0048 }
            if (r3 == 0) goto L_0x0064
            com.tencent.smtt.sdk.w r2 = com.tencent.smtt.sdk.w.a()     // Catch:{ all -> 0x0045 }
            if (r2 == 0) goto L_0x0043
            boolean r3 = r2.b()     // Catch:{ all -> 0x0045 }
            if (r3 == 0) goto L_0x0043
            com.tencent.smtt.sdk.x r2 = r2.c()     // Catch:{ all -> 0x0045 }
            r2.a((android.content.Context) r6, (boolean) r7)     // Catch:{ all -> 0x0045 }
        L_0x0043:
            r2 = 1
            goto L_0x0064
        L_0x0045:
            r2 = move-exception
            r3 = 1
            goto L_0x004b
        L_0x0048:
            r3 = move-exception
            r2 = r3
            r3 = 0
        L_0x004b:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "clearAllWebViewCache exception 2 -- "
            r4.append(r5)
            java.lang.String r2 = android.util.Log.getStackTraceString(r2)
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            com.tencent.smtt.utils.TbsLog.e(r1, r2)
            r2 = r3
        L_0x0064:
            if (r2 == 0) goto L_0x006c
            java.lang.String r6 = "is_in_x5_mode --> no need to clear system webview!"
            com.tencent.smtt.utils.TbsLog.i(r1, r6)
            return
        L_0x006c:
            android.webkit.WebView r2 = new android.webkit.WebView     // Catch:{ all -> 0x00b9 }
            r2.<init>(r6)     // Catch:{ all -> 0x00b9 }
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x00b9 }
            r4 = 11
            if (r3 < r4) goto L_0x0086
            java.lang.String r3 = "searchBoxJavaBridge_"
            r2.removeJavascriptInterface(r3)     // Catch:{ all -> 0x00b9 }
            java.lang.String r3 = "accessibility"
            r2.removeJavascriptInterface(r3)     // Catch:{ all -> 0x00b9 }
            java.lang.String r3 = "accessibilityTraversal"
            r2.removeJavascriptInterface(r3)     // Catch:{ all -> 0x00b9 }
        L_0x0086:
            r2.clearCache(r0)     // Catch:{ all -> 0x00b9 }
            if (r7 == 0) goto L_0x0095
            android.webkit.CookieSyncManager.createInstance(r6)     // Catch:{ all -> 0x00b9 }
            android.webkit.CookieManager r7 = android.webkit.CookieManager.getInstance()     // Catch:{ all -> 0x00b9 }
            r7.removeAllCookie()     // Catch:{ all -> 0x00b9 }
        L_0x0095:
            android.webkit.WebViewDatabase r7 = android.webkit.WebViewDatabase.getInstance(r6)     // Catch:{ all -> 0x00b9 }
            r7.clearUsernamePassword()     // Catch:{ all -> 0x00b9 }
            android.webkit.WebViewDatabase r7 = android.webkit.WebViewDatabase.getInstance(r6)     // Catch:{ all -> 0x00b9 }
            r7.clearHttpAuthUsernamePassword()     // Catch:{ all -> 0x00b9 }
            android.webkit.WebViewDatabase r6 = android.webkit.WebViewDatabase.getInstance(r6)     // Catch:{ all -> 0x00b9 }
            r6.clearFormData()     // Catch:{ all -> 0x00b9 }
            android.webkit.WebStorage r6 = android.webkit.WebStorage.getInstance()     // Catch:{ all -> 0x00b9 }
            r6.deleteAllData()     // Catch:{ all -> 0x00b9 }
            android.webkit.WebIconDatabase r6 = android.webkit.WebIconDatabase.getInstance()     // Catch:{ all -> 0x00b9 }
            r6.removeAllIcons()     // Catch:{ all -> 0x00b9 }
            goto L_0x00d2
        L_0x00b9:
            r6 = move-exception
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r0 = "clearAllWebViewCache exception 1 -- "
            r7.append(r0)
            java.lang.String r6 = android.util.Log.getStackTraceString(r6)
            r7.append(r6)
            java.lang.String r6 = r7.toString()
            com.tencent.smtt.utils.TbsLog.e(r1, r6)
        L_0x00d2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.QbSdk.clearAllWebViewCache(android.content.Context, boolean):void");
    }

    public static void clearDefaultBrowser(Context context, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARE_PREFERENCES_NAME, 0);
        String d2 = e.d(str);
        if (TextUtils.isEmpty(d2)) {
            d2 = "*/*";
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.remove("key_tbs_picked_default_browser_" + d2).commit();
    }

    public static void closeFileReader(Context context) {
        w a2 = w.a();
        a2.a(context);
        if (a2.b()) {
            a2.c().p();
        }
    }

    public static String closeNetLogAndSavaToLocal() {
        w a2 = w.a();
        if (a2 != null && a2.b()) {
            try {
                Object invokeStaticMethod = a2.c().b().invokeStaticMethod("com.tencent.smtt.livelog.NetLogManager", "closeNetLogAndSavaToLocal", new Class[0], new Object[0]);
                if (invokeStaticMethod != null && (invokeStaticMethod instanceof String)) {
                    return (String) invokeStaticMethod;
                }
            } catch (Exception unused) {
            }
        }
        return "";
    }

    public static void continueLoadSo(Context context) {
        if (TbsConfig.APP_WX.equals(getCurrentProcessName(context)) && WebView.mWebViewCreated) {
            i.a(r, "continueLoadSo", (Class<?>[]) new Class[0], new Object[0]);
        }
    }

    public static boolean createMiniQBShortCut(Context context, String str, String str2, Drawable drawable) {
        w a2;
        if (!(context == null || TbsDownloader.getOverSea(context) || isMiniQBShortCutExist(context, str, str2) || (a2 = w.a()) == null || !a2.b())) {
            Bitmap bitmap = null;
            if (drawable instanceof BitmapDrawable) {
                bitmap = ((BitmapDrawable) drawable).getBitmap();
            }
            DexLoader b2 = a2.c().b();
            TbsLog.e("QbSdk", "qbsdk createMiniQBShortCut");
            Object invokeStaticMethod = b2.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "createMiniQBShortCut", new Class[]{Context.class, String.class, String.class, Bitmap.class}, context, str, str2, bitmap);
            TbsLog.e("QbSdk", "qbsdk after createMiniQBShortCut ret: " + invokeStaticMethod);
            if (invokeStaticMethod != null) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00f6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void d(android.content.Context r11) {
        /*
            java.lang.String r0 = "tbs_preload_x5_recorder"
            java.lang.String r1 = "tbs_preload_x5_counter"
            java.lang.String r2 = "QbSdk"
            r3 = 1
            D = r3
            r4 = 0
            r5 = -1
            r6 = 0
            int r7 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x005c }
            r8 = 11
            java.lang.String r9 = "tbs_preloadx5_check_cfg_file"
            r10 = 4
            if (r7 < r8) goto L_0x001a
            android.content.SharedPreferences r6 = r11.getSharedPreferences(r9, r10)     // Catch:{ all -> 0x005c }
            goto L_0x001e
        L_0x001a:
            android.content.SharedPreferences r6 = r11.getSharedPreferences(r9, r4)     // Catch:{ all -> 0x005c }
        L_0x001e:
            int r7 = r6.getInt(r0, r5)     // Catch:{ all -> 0x005c }
            if (r7 < 0) goto L_0x002b
            int r7 = r7 + 1
            if (r7 <= r10) goto L_0x0029
            return
        L_0x0029:
            r8 = r7
            goto L_0x002c
        L_0x002b:
            r8 = -1
        L_0x002c:
            com.tencent.smtt.sdk.o r9 = com.tencent.smtt.sdk.o.a()     // Catch:{ all -> 0x005a }
            int r9 = r9.j(r11)     // Catch:{ all -> 0x005a }
            if (r9 > 0) goto L_0x0037
            return
        L_0x0037:
            if (r7 > r10) goto L_0x0047
            android.content.SharedPreferences$Editor r10 = r6.edit()     // Catch:{ all -> 0x0045 }
            android.content.SharedPreferences$Editor r0 = r10.putInt(r0, r7)     // Catch:{ all -> 0x0045 }
            r0.commit()     // Catch:{ all -> 0x0045 }
            goto L_0x0047
        L_0x0045:
            r0 = move-exception
            goto L_0x005f
        L_0x0047:
            int r0 = r6.getInt(r1, r5)     // Catch:{ all -> 0x0045 }
            if (r0 < 0) goto L_0x0077
            android.content.SharedPreferences$Editor r7 = r6.edit()     // Catch:{ all -> 0x0045 }
            int r0 = r0 + r3
            android.content.SharedPreferences$Editor r3 = r7.putInt(r1, r0)     // Catch:{ all -> 0x0045 }
            r3.commit()     // Catch:{ all -> 0x0045 }
            goto L_0x0078
        L_0x005a:
            r0 = move-exception
            goto L_0x005e
        L_0x005c:
            r0 = move-exception
            r8 = -1
        L_0x005e:
            r9 = -1
        L_0x005f:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r7 = "tbs_preload_x5_counter Inc exception:"
            r3.append(r7)
            java.lang.String r0 = android.util.Log.getStackTraceString(r0)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            com.tencent.smtt.utils.TbsLog.e(r2, r0)
        L_0x0077:
            r0 = -1
        L_0x0078:
            r3 = 3
            if (r0 <= r3) goto L_0x00f6
            java.lang.String r0 = "tbs_preload_x5_version"
            int r0 = r6.getInt(r0, r5)     // Catch:{ all -> 0x00dc }
            android.content.SharedPreferences$Editor r1 = r6.edit()     // Catch:{ all -> 0x00dc }
            if (r0 != r9) goto L_0x00b9
            com.tencent.smtt.sdk.o r3 = com.tencent.smtt.sdk.o.a()     // Catch:{ all -> 0x00dc }
            java.io.File r3 = r3.r(r11)     // Catch:{ all -> 0x00dc }
            com.tencent.smtt.utils.FileUtil.a((java.io.File) r3, (boolean) r4)     // Catch:{ all -> 0x00dc }
            com.tencent.smtt.sdk.m r11 = com.tencent.smtt.sdk.m.a((android.content.Context) r11)     // Catch:{ all -> 0x00dc }
            java.io.File r11 = r11.a()     // Catch:{ all -> 0x00dc }
            if (r11 == 0) goto L_0x009f
            com.tencent.smtt.utils.FileUtil.a((java.io.File) r11, (boolean) r4)     // Catch:{ all -> 0x00dc }
        L_0x009f:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x00dc }
            r11.<init>()     // Catch:{ all -> 0x00dc }
            java.lang.String r3 = "QbSdk - preload_x5_check: tbs core "
            r11.append(r3)     // Catch:{ all -> 0x00dc }
            r11.append(r9)     // Catch:{ all -> 0x00dc }
            java.lang.String r3 = " is deleted!"
            r11.append(r3)     // Catch:{ all -> 0x00dc }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x00dc }
        L_0x00b5:
            com.tencent.smtt.utils.TbsLog.e(r2, r11)     // Catch:{ all -> 0x00dc }
            goto L_0x00d3
        L_0x00b9:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x00dc }
            r11.<init>()     // Catch:{ all -> 0x00dc }
            java.lang.String r3 = "QbSdk - preload_x5_check -- reset exception core_ver:"
            r11.append(r3)     // Catch:{ all -> 0x00dc }
            r11.append(r9)     // Catch:{ all -> 0x00dc }
            java.lang.String r3 = "; value:"
            r11.append(r3)     // Catch:{ all -> 0x00dc }
            r11.append(r0)     // Catch:{ all -> 0x00dc }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x00dc }
            goto L_0x00b5
        L_0x00d3:
            java.lang.String r11 = "tbs_precheck_disable_version"
            r1.putInt(r11, r0)     // Catch:{ all -> 0x00dc }
            r1.commit()     // Catch:{ all -> 0x00dc }
            goto L_0x00f5
        L_0x00dc:
            r11 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "tbs_preload_x5_counter disable version exception:"
            r0.append(r1)
            java.lang.String r11 = android.util.Log.getStackTraceString(r11)
            r0.append(r11)
            java.lang.String r11 = r0.toString()
            com.tencent.smtt.utils.TbsLog.e(r2, r11)
        L_0x00f5:
            return
        L_0x00f6:
            if (r8 <= 0) goto L_0x010c
            if (r8 > r3) goto L_0x010c
            java.lang.String r0 = "QbSdk - preload_x5_check -- before creation!"
            com.tencent.smtt.utils.TbsLog.i(r2, r0)
            com.tencent.smtt.sdk.w r0 = com.tencent.smtt.sdk.w.a()
            r0.a((android.content.Context) r11)
            java.lang.String r11 = "QbSdk - preload_x5_check -- after creation!"
            com.tencent.smtt.utils.TbsLog.i(r2, r11)
            goto L_0x010d
        L_0x010c:
            r4 = -1
        L_0x010d:
            int r11 = r6.getInt(r1, r5)     // Catch:{ all -> 0x0120 }
            if (r11 <= 0) goto L_0x0139
            android.content.SharedPreferences$Editor r0 = r6.edit()     // Catch:{ all -> 0x0120 }
            int r11 = r11 + r5
            android.content.SharedPreferences$Editor r11 = r0.putInt(r1, r11)     // Catch:{ all -> 0x0120 }
            r11.commit()     // Catch:{ all -> 0x0120 }
            goto L_0x0139
        L_0x0120:
            r11 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "tbs_preload_x5_counter Dec exception:"
            r0.append(r1)
            java.lang.String r11 = android.util.Log.getStackTraceString(r11)
            r0.append(r11)
            java.lang.String r11 = r0.toString()
            com.tencent.smtt.utils.TbsLog.e(r2, r11)
        L_0x0139:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r0 = "QbSdk -- preload_x5_check result:"
            r11.append(r0)
            r11.append(r4)
            java.lang.String r11 = r11.toString()
            com.tencent.smtt.utils.TbsLog.i(r2, r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.QbSdk.d(android.content.Context):void");
    }

    public static boolean deleteMiniQBShortCut(Context context, String str, String str2) {
        w a2;
        if (context != null && !TbsDownloader.getOverSea(context) && (a2 = w.a()) != null && a2.b()) {
            if (a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "deleteMiniQBShortCut", new Class[]{Context.class, String.class, String.class}, context, str, str2) != null) {
                return true;
            }
        }
        return false;
    }

    public static void disAllowThirdAppDownload() {
        c = false;
    }

    public static void disableAutoCreateX5Webview() {
        j = false;
    }

    public static void disableSensitiveApi() {
        x = false;
        HashMap hashMap = new HashMap();
        hashMap.put(TbsCoreSettings.NO_SENSITIVE_API, true);
        initTbsSettings(hashMap);
    }

    public static void fileInfoDetect(Context context, String str, ValueCallback<String> valueCallback) {
        w a2 = w.a();
        if (a2 != null && a2.b()) {
            try {
                a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "fileInfoDetect", new Class[]{Context.class, String.class, ValueCallback.class}, context, str, valueCallback);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static void forceSysWebView() {
        b = true;
        u = "SysWebViewForcedByOuter: " + Log.getStackTraceString(new Throwable());
        TbsLog.e("QbSdk", "sys WebView: SysWebViewForcedByOuter");
    }

    public static long getApkFileSize(Context context) {
        if (context != null) {
            return TbsDownloadConfig.getInstance(context.getApplicationContext()).mPreferences.getLong(TbsDownloadConfig.TbsConfigKey.KEY_TBSAPKFILESIZE, 0);
        }
        return 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x001f A[Catch:{ all -> 0x0033 }] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x002f A[SYNTHETIC, Splitter:B:17:0x002f] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x003c A[SYNTHETIC, Splitter:B:25:0x003c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getCurrentProcessName(android.content.Context r7) {
        /*
            r7 = 0
            java.lang.String r0 = "/proc/self/cmdline"
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ all -> 0x0035 }
            r1.<init>(r0)     // Catch:{ all -> 0x0035 }
            r0 = 256(0x100, float:3.59E-43)
            byte[] r2 = new byte[r0]     // Catch:{ all -> 0x0033 }
            r3 = 0
            r4 = 0
        L_0x000e:
            int r5 = r1.read()     // Catch:{ all -> 0x0033 }
            if (r5 <= 0) goto L_0x001d
            if (r4 >= r0) goto L_0x001d
            int r6 = r4 + 1
            byte r5 = (byte) r5     // Catch:{ all -> 0x0033 }
            r2[r4] = r5     // Catch:{ all -> 0x0033 }
            r4 = r6
            goto L_0x000e
        L_0x001d:
            if (r4 <= 0) goto L_0x002f
            java.lang.String r0 = new java.lang.String     // Catch:{ all -> 0x0033 }
            java.lang.String r5 = "UTF-8"
            r0.<init>(r2, r3, r4, r5)     // Catch:{ all -> 0x0033 }
            r1.close()     // Catch:{ IOException -> 0x002a }
            goto L_0x002e
        L_0x002a:
            r7 = move-exception
            r7.printStackTrace()
        L_0x002e:
            return r0
        L_0x002f:
            r1.close()     // Catch:{ IOException -> 0x0040 }
            goto L_0x0044
        L_0x0033:
            r0 = move-exception
            goto L_0x0037
        L_0x0035:
            r0 = move-exception
            r1 = r7
        L_0x0037:
            r0.printStackTrace()     // Catch:{ all -> 0x0045 }
            if (r1 == 0) goto L_0x0044
            r1.close()     // Catch:{ IOException -> 0x0040 }
            goto L_0x0044
        L_0x0040:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0044:
            return r7
        L_0x0045:
            r7 = move-exception
            if (r1 == 0) goto L_0x0050
            r1.close()     // Catch:{ IOException -> 0x004c }
            goto L_0x0050
        L_0x004c:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0050:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.QbSdk.getCurrentProcessName(android.content.Context):java.lang.String");
    }

    public static String[] getDexLoaderFileList(Context context, Context context2, String str) {
        String[] strArr = t;
        if (strArr instanceof String[]) {
            int length = strArr.length;
            String[] strArr2 = new String[length];
            for (int i2 = 0; i2 < length; i2++) {
                strArr2[i2] = str + t[i2];
            }
            return strArr2;
        }
        Object a2 = i.a(r, "getJarFiles", (Class<?>[]) new Class[]{Context.class, Context.class, String.class}, context, context2, str);
        if (!(a2 instanceof String[])) {
            a2 = new String[]{""};
        }
        return (String[]) a2;
    }

    public static boolean getDownloadWithoutWifi() {
        return G;
    }

    public static boolean getIsSysWebViewForcedByOuter() {
        return b;
    }

    public static boolean getJarFilesAndLibraryPath(Context context) {
        String str;
        Object obj = r;
        if (obj == null) {
            str = "getJarFilesAndLibraryPath sExtensionObj is null";
        } else {
            Bundle bundle = (Bundle) i.a(obj, "canLoadX5CoreAndNotLoadSo", (Class<?>[]) new Class[]{Integer.TYPE}, 43993);
            if (bundle == null) {
                str = "getJarFilesAndLibraryPath bundle is null and coreverison is " + o.a().a(true, context);
            } else {
                t = bundle.getStringArray("tbs_jarfiles");
                d = bundle.getString("tbs_librarypath");
                return true;
            }
        }
        TbsLog.i("QbSdk", str);
        return false;
    }

    public static String getMiniQBVersion(Context context) {
        w a2 = w.a();
        a2.a(context);
        if (a2 == null || !a2.b()) {
            return null;
        }
        return a2.c().f();
    }

    public static boolean getOnlyDownload() {
        return k;
    }

    public static String getQQBuildNumber() {
        return B;
    }

    public static Map<String, Object> getSettings() {
        return n;
    }

    public static boolean getTBSInstalling() {
        return H;
    }

    public static String getTID() {
        return A;
    }

    public static File getTbsFolderDir(Context context) {
        if (context == null) {
            return null;
        }
        try {
            if (b.c()) {
                return context.getDir("tbs_64", 0);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return context.getDir("tbs", 0);
    }

    public static String getTbsResourcesPath(Context context) {
        return TbsShareManager.g(context);
    }

    public static int getTbsSdkVersion() {
        return 43993;
    }

    public static int getTbsVersion(Context context) {
        return TbsShareManager.isThirdPartyApp(context) ? TbsShareManager.a(context, false) : o.a().j(context);
    }

    public static int getTbsVersionForCrash(Context context) {
        if (TbsShareManager.isThirdPartyApp(context)) {
            return TbsShareManager.a(context, false);
        }
        int k2 = o.a().k(context);
        if (k2 == 0 && m.a(context).c() == 3) {
            reset(context);
        }
        return k2;
    }

    public static int getTmpDirTbsVersion(Context context) {
        if (m.a(context).c() == 2) {
            return o.a().e(context, 0);
        }
        if (m.a(context).b("copy_status") == 1) {
            return o.a().e(context, 1);
        }
        return 0;
    }

    public static void initBuglyAsync(boolean z2) {
        i = z2;
    }

    public static void initForinitAndNotLoadSo(Context context) {
        String str;
        if (q == null) {
            File r2 = o.a().r(context);
            if (r2 == null) {
                str = "QbSdk initForinitAndNotLoadSo optDir == null";
            } else {
                File file = new File(r2, "tbs_sdk_extension_dex.jar");
                if (!file.exists()) {
                    str = "QbSdk initForinitAndNotLoadSo dexFile.exists()=false";
                } else {
                    String absolutePath = r2.getAbsolutePath();
                    w.a().b(context);
                    l.a(context);
                    Context context2 = context;
                    q = new DexLoader(file.getParent(), context2, new String[]{file.getAbsolutePath()}, absolutePath, getSettings()).loadClass("com.tencent.tbs.sdk.extension.TbsSDKExtension");
                    return;
                }
            }
            Log.e("QbSdk", str);
        }
    }

    public static void initTbsSettings(Map<String, Object> map) {
        Map<String, Object> map2 = n;
        if (map2 == null) {
            n = map;
            return;
        }
        try {
            map2.putAll(map);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void initX5Environment(final Context context, final PreInitCallback preInitCallback) {
        TbsLog.initIfNeed(context);
        if (context == null) {
            TbsLog.e("QbSdk", "initX5Environment,context=null");
            return;
        }
        b(context);
        F = new TbsListener() {
            public void onDownloadFinish(int i) {
            }

            public void onDownloadProgress(int i) {
            }

            public void onInstallFinish(int i) {
                QbSdk.preInit(context, preInitCallback);
            }
        };
        if (TbsShareManager.isThirdPartyApp(context)) {
            o.a().b(context, f.a == 0);
        }
        TbsDownloader.needDownload(context, false, false, true, new TbsDownloader.TbsDownloaderCallback() {
            public void onNeedDownloadFinish(boolean z, int i) {
                if (TbsShareManager.findCoreForThirdPartyApp(context) == 0 && !TbsShareManager.getCoreDisabled()) {
                    TbsShareManager.forceToLoadX5ForThirdApp(context, false);
                }
                if (QbSdk.i && TbsShareManager.isThirdPartyApp(context)) {
                    TbsExtensionFunctionManager.getInstance().initTbsBuglyIfNeed(context);
                }
                QbSdk.preInit(context, preInitCallback);
            }
        });
    }

    public static boolean installLocalQbApk(Context context, String str, String str2, Bundle bundle) {
        f a2 = f.a(true);
        a2.a(context, false, false);
        if (a2 == null || !a2.b()) {
            return false;
        }
        return a2.a().a(context, str, str2, bundle);
    }

    public static void installLocalTbsCore(Context context, int i2, String str) {
        o.a().a(context, str, i2);
    }

    public static boolean intentDispatch(WebView webView, Intent intent, String str, String str2) {
        String str3;
        if (webView == null) {
            return false;
        }
        if (str.startsWith("mttbrowser://miniqb/ch=icon?")) {
            Context context = webView.getContext();
            int indexOf = str.indexOf("url=");
            str = indexOf > 0 ? str.substring(indexOf + 4) : null;
            HashMap hashMap = new HashMap();
            try {
                str3 = context.getApplicationInfo().packageName;
            } catch (Exception e2) {
                e2.printStackTrace();
                str3 = EnvironmentCompat.MEDIA_UNKNOWN;
            }
            hashMap.put("ChannelID", str3);
            hashMap.put("PosID", "14004");
            if (MttLoader.loadUrl(context, "miniqb://home".equals(str) ? "qb://navicard/addCard?cardId=168&cardName=168" : str, hashMap, "QbSdk.startMiniQBToLoadUrl", (WebView) null) != 0) {
                w a2 = w.a();
                if (a2 != null && a2.b() && a2.c().a(context, str, (Map<String, String>) null, str2, (ValueCallback<String>) null) == 0) {
                    return true;
                }
            }
            return false;
        }
        webView.loadUrl(str);
        return false;
    }

    public static boolean isEnableSensitiveApi() {
        return x;
    }

    public static boolean isInDefaultBrowser(Context context, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARE_PREFERENCES_NAME, 0);
        String d2 = e.d(str);
        if (TextUtils.isEmpty(d2)) {
            d2 = "*/*";
        }
        return sharedPreferences.contains("key_tbs_picked_default_browser_" + d2);
    }

    public static boolean isMiniQBShortCutExist(Context context, String str, String str2) {
        w a2;
        Object invokeStaticMethod;
        if (context == null || TbsDownloader.getOverSea(context) || (a2 = w.a()) == null || !a2.b() || (invokeStaticMethod = a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "isMiniQBShortCutExist", new Class[]{Context.class, String.class}, context, str)) == null) {
            return false;
        }
        Boolean bool = false;
        if (invokeStaticMethod instanceof Boolean) {
            bool = (Boolean) invokeStaticMethod;
        }
        return bool.booleanValue();
    }

    public static boolean isNeedInitX5FirstTime() {
        return w;
    }

    public static boolean isSuportOpenFile(String str, int i2) {
        List asList;
        int i3 = i2;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String[] strArr = {"rar", "zip", "tar", "bz2", "gz", "7z", "doc", "docx", "ppt", "pptx", "xls", "xlsx", "txt", "pdf", "epub", "chm", "html", "htm", "xml", "mht", TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_URL, "ini", "log", "bat", "php", "js", "lrc", "jpg", "jpeg", "png", "gif", "bmp", "tiff", "webp", "mp3", "m4a", "aac", "amr", "wav", "ogg", "mid", "ra", "wma", "mpga", "ape", "flac", "RTSP", "RTP", "SDP", "RTMP", "mp4", "flv", "avi", "3gp", "3gpp", "webm", "ts", "ogv", "m3u8", "asf", "wmv", "rmvb", "rm", "f4v", "dat", "mov", "mpg", "mkv", "mpeg", "mpeg1", "mpeg2", "xvid", "dvd", "vcd", "vob", "divx"};
        String[] strArr2 = {"doc", "docx", "ppt", "pptx", "xls", "xlsx", "txt", "pdf", "epub"};
        if (i3 == 1) {
            asList = Arrays.asList(strArr2);
        } else if (i3 != 2) {
            return false;
        } else {
            asList = Arrays.asList(strArr);
        }
        return asList.contains(str.toLowerCase());
    }

    public static boolean isTbsCoreInited() {
        f a2 = f.a(false);
        return a2 != null && a2.g();
    }

    public static boolean isX5DisabledSync(Context context) {
        if (m.a(context).c() == 2) {
            return false;
        }
        if (!c(context)) {
            return true;
        }
        Object a2 = i.a(r, "isX5DisabledSync", (Class<?>[]) new Class[]{Integer.TYPE, Integer.TYPE}, Integer.valueOf(o.a().j(context)), 43993);
        if (a2 != null) {
            return ((Boolean) a2).booleanValue();
        }
        return true;
    }

    public static void loadTBSSDKExtension(Context context, String str) {
        boolean z2;
        Constructor constructor;
        Object newInstance;
        if (r == null) {
            synchronized (QbSdk.class) {
                if (r == null) {
                    if (q == null) {
                        TbsLog.i("QbSdk", "QbSdk loadTBSSDKExtension sExtensionClass is null");
                    }
                    try {
                        constructor = q.getConstructor(new Class[]{Context.class, Context.class, String.class, String.class, String.class});
                        z2 = true;
                    } catch (Throwable unused) {
                        constructor = null;
                        z2 = false;
                    }
                    try {
                        if (TbsShareManager.isThirdPartyApp(context)) {
                            Context e2 = TbsShareManager.e(context);
                            if (e2 == null && TbsShareManager.getHostCorePathAppDefined() == null) {
                                TbsLogReport.getInstance(context.getApplicationContext()).setLoadErrorCode((int) TbsListener.ErrorCode.HOST_CONTEXT_IS_NULL, "host context is null!");
                                return;
                            }
                            if (context.getApplicationContext() != null) {
                                context = context.getApplicationContext();
                            }
                            newInstance = !z2 ? e2 == null ? q.getConstructor(new Class[]{Context.class, Context.class, String.class}).newInstance(new Object[]{context, e2, TbsShareManager.getHostCorePathAppDefined(), str, null}) : q.getConstructor(new Class[]{Context.class, Context.class}).newInstance(new Object[]{context, e2}) : constructor.newInstance(new Object[]{context, e2, TbsShareManager.getHostCorePathAppDefined(), str, null});
                        } else if (!z2) {
                            Constructor<?> constructor2 = q.getConstructor(new Class[]{Context.class, Context.class});
                            if (context.getApplicationContext() != null) {
                                context = context.getApplicationContext();
                            }
                            newInstance = constructor2.newInstance(new Object[]{context, context});
                        } else {
                            String str2 = (!TbsConfig.APP_WX.equals(getCurrentProcessName(context)) || WebView.mWebViewCreated) ? null : "notLoadSo";
                            if (context.getApplicationContext() != null) {
                                context = context.getApplicationContext();
                            }
                            newInstance = constructor.newInstance(new Object[]{context, context, null, str, str2});
                        }
                        r = newInstance;
                    } catch (Throwable th) {
                        TbsLog.e("QbSdk", "throwable" + Log.getStackTraceString(th));
                    }
                }
            }
        }
    }

    public static void openBrowserList(Context context, String str, Bundle bundle, final ValueCallback<String> valueCallback) {
        String str2;
        if (context != null) {
            String string = bundle != null ? bundle.getString("ChannelId") : "";
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setData(Uri.parse(str));
            String d2 = e.d(str);
            isDefaultDialog = false;
            d dVar = new d(context, "选择其它应用打开", intent, bundle, valueCallback, d2, string);
            String a2 = dVar.a();
            if (a2 != null && !TextUtils.isEmpty(a2)) {
                if (TbsConfig.APP_QB.equals(a2)) {
                    intent.putExtra("ChannelID", context.getApplicationContext().getPackageName());
                    intent.putExtra("PosID", NetConstant.PAGE_ID_CONSULTATION);
                }
                intent.setPackage(a2);
                intent.putExtra("big_brother_source_key", string);
                context.startActivity(intent);
                if (valueCallback != null) {
                    str2 = "default browser:" + a2;
                } else {
                    return;
                }
            } else if (isDefaultDialog) {
                new AlertDialog.Builder(context).setTitle("提示").setMessage("此文件无法打开").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).show();
                if (valueCallback != null) {
                    str2 = "can not open";
                } else {
                    return;
                }
            } else {
                dVar.show();
                dVar.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    public void onDismiss(DialogInterface dialogInterface) {
                        ValueCallback valueCallback = valueCallback;
                        if (valueCallback != null) {
                            valueCallback.onReceiveValue("TbsReaderDialogClosed");
                        }
                    }
                });
                return;
            }
            valueCallback.onReceiveValue(str2);
        }
    }

    public static void openBrowserList(Context context, String str, ValueCallback<String> valueCallback) {
        openBrowserList(context, str, (Bundle) null, valueCallback);
    }

    public static int openFileReader(Context context, String str, HashMap<String, String> hashMap, ValueCallback<String> valueCallback) {
        TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.INFO_CODE_FILEREADER_OPENFILEREADER_COUNTS);
        if (!checkContentProviderPrivilage(context)) {
            TbsLog.e("QbSdk", "Got an unexpected result when check content provider privilege above api 24");
            return -5;
        } else if (str != null) {
            String substring = str.substring(str.lastIndexOf(Consts.DOT) + 1);
            String str2 = null;
            if (substring != null) {
                str2 = substring.toLowerCase();
            }
            String str3 = hashMap.get("fileext");
            String str4 = !TextUtils.isEmpty(str3) ? str3 : str2;
            if (str4 == null) {
                TbsLog.e("QbSdk", "open openFileReader null file type");
                return -7;
            } else if ("apk".equalsIgnoreCase(str4)) {
                Intent intent = new Intent("android.intent.action.VIEW");
                if (context != null && context.getApplicationInfo().targetSdkVersion >= 24 && Build.VERSION.SDK_INT >= 24) {
                    intent.addFlags(1);
                }
                Uri a2 = FileProvider.a(context, str);
                if (a2 == null) {
                    valueCallback.onReceiveValue("uri failed");
                    return -6;
                }
                intent.setDataAndType(a2, "application/vnd.android.package-archive");
                context.startActivity(intent);
                TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.INFO_CODE_FILEREADER_OPENFILEREADER_APKFILE);
                TbsLog.e("QbSdk", "open openFileReader ret = 4");
                return 4;
            } else {
                if (MttLoader.isBrowserInstalled(context)) {
                    if (!isSuportOpenFile(str4, 2)) {
                        TbsLog.e("QbSdk", "Open file reader in qq browser, isQBSupport: false  ret = 3");
                        openFileReaderListWithQBDownload(context, str, valueCallback);
                        TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.INFO_CODE_FILEREADER_OPENFILEREADER_NOTSUPPORT);
                        return 3;
                    }
                    if (startQBForDoc(context, str, 4, 0, str4, a(context, (Map<String, String>) hashMap))) {
                        if (valueCallback != null) {
                            valueCallback.onReceiveValue("open QB");
                        }
                        TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.INFO_CODE_FILEREADER_OPENFILEREADER_NOTSUPPORT);
                        TbsLog.e("QbSdk", "open openFileReader open QB ret = 1");
                        return 1;
                    }
                }
                if (hashMap == null) {
                    hashMap = new HashMap<>();
                }
                hashMap.put(SpeechConstants.TYPE_LOCAL, "true");
                TbsLog.setWriteLogJIT(true);
                int startMiniQBToLoadUrl = startMiniQBToLoadUrl(context, str, hashMap, valueCallback);
                if (startMiniQBToLoadUrl != 0) {
                    openFileReaderListWithQBDownload(context, str, valueCallback);
                    TbsLogReport instance = TbsLogReport.getInstance(context);
                    instance.setLoadErrorCode(511, "" + startMiniQBToLoadUrl);
                    return 3;
                }
                TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.INFO_CODE_FILEREADER_OPENFILEREADER_MINIQBSUCCESS);
                return 2;
            }
        } else {
            if (valueCallback != null) {
                valueCallback.onReceiveValue("filepath error");
            }
            TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.INFO_CODE_FILEREADER_OPENFILEREADER_FILEPATHISNULL);
            TbsLog.e("QbSdk", "open openFileReader filepath error ret -1");
            return -1;
        }
    }

    public static void openFileReaderListWithQBDownload(Context context, String str, Bundle bundle, final ValueCallback<String> valueCallback) {
        String str2;
        if (context != null && !context.getApplicationInfo().packageName.equals("com.tencent.qim") && !context.getApplicationInfo().packageName.equals("com.tencent.androidqqmail")) {
            String string = bundle != null ? bundle.getString("ChannelId") : "";
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.DEFAULT");
            String d2 = e.d(str);
            if (context != null && context.getApplicationInfo().targetSdkVersion >= 24 && Build.VERSION.SDK_INT >= 24) {
                intent.addFlags(1);
            }
            Uri a2 = FileProvider.a(context, str);
            if (a2 == null) {
                TbsLog.i("QbSdk", "openFileReaderListWithQBDownload,uri failed");
                valueCallback.onReceiveValue("uri failed");
                return;
            }
            TbsLog.i("QbSdk", "openFileReaderListWithQBDownload,fileUri:" + str + ",mimeType:" + d2);
            intent.setDataAndType(a2, d2);
            isDefaultDialog = false;
            d dVar = new d(context, "选择其它应用打开", intent, bundle, valueCallback, d2, string);
            String a3 = dVar.a();
            TbsLog.i("QbSdk", "openFileReaderListWithQBDownload,defaultBrowser:" + a3);
            if (a3 != null && !TextUtils.isEmpty(a3) && a3.startsWith("extraMenuEvent:")) {
                TbsLog.i("QbSdk", "openFileReaderListWithQBDownload, is default extra menu action");
                valueCallback.onReceiveValue(a3);
            } else if (a3 != null && !TextUtils.isEmpty(a3) && checkApkExist(context, a3)) {
                TbsLog.i("QbSdk", "openFileReaderListWithQBDownload, is default normal menu action");
                if (TbsConfig.APP_QB.equals(a3)) {
                    intent.putExtra("ChannelID", context.getApplicationContext().getPackageName());
                    intent.putExtra("PosID", NetConstant.PAGE_ID_CONSULTATION);
                }
                if (!TextUtils.isEmpty(string)) {
                    intent.putExtra("big_brother_source_key", string);
                }
                intent.setPackage(a3);
                context.startActivity(intent);
                if (valueCallback != null) {
                    valueCallback.onReceiveValue("default browser:" + a3);
                }
            } else if (!"com.tencent.rtxlite".equalsIgnoreCase(context.getApplicationContext().getPackageName()) || !isDefaultDialog) {
                if (isDefaultDialog) {
                    TbsLog.i("QbSdk", "isDefaultDialog=true");
                    if (valueCallback != null) {
                        TbsLog.i("QbSdk", "isDefaultDialog=true, can not open");
                        str2 = "can not open";
                    }
                    TbsLog.i("QbSdk", "unexpected return, dialogBuilder not show!");
                }
                try {
                    TbsLog.i("QbSdk", "isDefaultDialog=false,try to open dialog");
                    dVar.show();
                    dVar.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        public void onDismiss(DialogInterface dialogInterface) {
                            ValueCallback valueCallback = valueCallback;
                            if (valueCallback != null) {
                                valueCallback.onReceiveValue("TbsReaderDialogClosed");
                            }
                        }
                    });
                } catch (Exception e2) {
                    e2.printStackTrace();
                    TbsLog.i("QbSdk", "isDefaultDialog=false,try to open dialog, but failed");
                    str2 = "TbsReaderDialogClosed";
                }
                TbsLog.i("QbSdk", "unexpected return, dialogBuilder not show!");
                valueCallback.onReceiveValue(str2);
                TbsLog.i("QbSdk", "unexpected return, dialogBuilder not show!");
            } else {
                new AlertDialog.Builder(context).setTitle("提示").setMessage("此文件无法打开").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).show();
            }
        }
    }

    public static void openFileReaderListWithQBDownload(Context context, String str, ValueCallback<String> valueCallback) {
        openFileReaderListWithQBDownload(context, str, (Bundle) null, valueCallback);
    }

    public static int openFileWithQB(Context context, String str, String str2) {
        TbsLog.i("QbSdk", "open openFileReader startMiniQBToLoadUrl filepath = " + str);
        if (!checkContentProviderPrivilage(context)) {
            return -1;
        }
        if (str != null) {
            String lowerCase = str.substring(str.lastIndexOf(Consts.DOT) + 1).toLowerCase();
            if (!MttLoader.isBrowserInstalled(context)) {
                TbsLog.i("QbSdk", "openFileReader QQ browser not installed");
                return -4;
            } else if (!isSuportOpenFile(lowerCase, 2)) {
                TbsLog.i("QbSdk", "openFileReader open in QB isQBSupport: false");
                return -2;
            } else {
                HashMap hashMap = new HashMap();
                hashMap.put("ChannelID", context.getApplicationContext().getApplicationInfo().processName);
                hashMap.put("PosID", Integer.toString(4));
                if (MttLoader.openDocWithQb(context, str, 0, lowerCase, str2, hashMap, (Bundle) null)) {
                    return 0;
                }
                TbsLog.i("QbSdk", "openFileReader startQBForDoc return false");
                return -3;
            }
        } else {
            TbsLog.i("QbSdk", "open openFileReader filepath error");
            return -5;
        }
    }

    public static void openNetLog(String str) {
        w a2 = w.a();
        if (a2 != null && a2.b()) {
            try {
                a2.c().b().invokeStaticMethod("com.tencent.smtt.livelog.NetLogManager", "openNetLog", new Class[]{String.class}, str);
            } catch (Exception unused) {
            }
        }
    }

    public static void pauseDownload() {
        TbsDownloader.pauseDownload();
    }

    public static synchronized void preInit(Context context) {
        synchronized (QbSdk.class) {
            preInit(context, (PreInitCallback) null);
        }
    }

    public static synchronized void preInit(final Context context, final PreInitCallback preInitCallback) {
        synchronized (QbSdk.class) {
            TbsLog.initIfNeed(context);
            TbsLog.i("QbSdk", "preInit -- processName: " + getCurrentProcessName(context));
            TbsLog.i("QbSdk", "preInit -- stack: " + Log.getStackTraceString(new Throwable("#")));
            l = a;
            if (!s) {
                final AnonymousClass3 r1 = new Handler(Looper.getMainLooper()) {
                    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0044, code lost:
                        if (r4 != null) goto L_0x0019;
                     */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void handleMessage(android.os.Message r4) {
                        /*
                            r3 = this;
                            int r4 = r4.what
                            r0 = 1
                            if (r4 == r0) goto L_0x0020
                            r0 = 2
                            if (r4 == r0) goto L_0x0014
                            r0 = 3
                            if (r4 == r0) goto L_0x000c
                            goto L_0x0047
                        L_0x000c:
                            com.tencent.smtt.sdk.QbSdk$PreInitCallback r4 = r6
                            if (r4 == 0) goto L_0x0047
                            r4.onCoreInitFinished()
                            goto L_0x0047
                        L_0x0014:
                            com.tencent.smtt.sdk.QbSdk$PreInitCallback r4 = r6
                            if (r4 == 0) goto L_0x001c
                            r0 = 0
                        L_0x0019:
                            r4.onViewInitFinished(r0)
                        L_0x001c:
                            com.tencent.smtt.utils.TbsLog.writeLogToDisk()
                            goto L_0x0047
                        L_0x0020:
                            com.tencent.smtt.sdk.TbsExtensionFunctionManager r4 = com.tencent.smtt.sdk.TbsExtensionFunctionManager.getInstance()
                            android.content.Context r1 = r5
                            java.lang.String r2 = "disable_unpreinit.txt"
                            boolean r4 = r4.canUseFunction(r1, r2)
                            boolean unused = com.tencent.smtt.sdk.QbSdk.C = r4
                            boolean r4 = com.tencent.smtt.sdk.QbSdk.j
                            if (r4 == 0) goto L_0x0042
                            com.tencent.smtt.sdk.w r4 = com.tencent.smtt.sdk.w.a()
                            com.tencent.smtt.sdk.x r4 = r4.c()
                            if (r4 == 0) goto L_0x0042
                            android.content.Context r1 = r5
                            r4.a((android.content.Context) r1)
                        L_0x0042:
                            com.tencent.smtt.sdk.QbSdk$PreInitCallback r4 = r6
                            if (r4 == 0) goto L_0x001c
                            goto L_0x0019
                        L_0x0047:
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.QbSdk.AnonymousClass3.handleMessage(android.os.Message):void");
                    }
                };
                AnonymousClass4 r6 = new Thread() {
                    public void run() {
                        int a2 = o.a().a(true, context);
                        TbsDownloader.setAppContext(context);
                        TbsLog.i("QbSdk", "QbSdk preinit ver is " + a2);
                        o.a().b(context, f.a == 0);
                        TbsLog.i("QbSdk", "preInit -- prepare initAndLoadSo");
                        f.a(true).a(context, false, false);
                        w a3 = w.a();
                        a3.a(context);
                        boolean b2 = a3.b();
                        r1.sendEmptyMessage(3);
                        if (!b2) {
                            r1.sendEmptyMessage(2);
                        } else {
                            r1.sendEmptyMessage(1);
                        }
                    }
                };
                r6.setName("tbs_preinit");
                r6.setPriority(10);
                r6.start();
                s = true;
            }
        }
    }

    public static void reset(Context context) {
        reset(context, false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0059 A[Catch:{ all -> 0x0079 }] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x005e A[Catch:{ all -> 0x0079 }] */
    /* JADX WARNING: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void reset(android.content.Context r5, boolean r6) {
        /*
            java.lang.String r0 = "QbSdk"
            java.lang.String r1 = "QbSdk reset!"
            r2 = 1
            com.tencent.smtt.utils.TbsLog.e(r0, r1, r2)
            com.tencent.smtt.sdk.TbsDownloader.stopDownload()     // Catch:{ all -> 0x0079 }
            r1 = 0
            if (r6 == 0) goto L_0x002d
            boolean r6 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r5)     // Catch:{ all -> 0x0079 }
            if (r6 != 0) goto L_0x002d
            com.tencent.smtt.sdk.o r6 = com.tencent.smtt.sdk.o.a()     // Catch:{ all -> 0x0079 }
            int r6 = r6.i(r5)     // Catch:{ all -> 0x0079 }
            com.tencent.smtt.sdk.o r3 = com.tencent.smtt.sdk.o.a()     // Catch:{ all -> 0x0079 }
            int r3 = r3.j(r5)     // Catch:{ all -> 0x0079 }
            r4 = 43300(0xa924, float:6.0676E-41)
            if (r6 <= r4) goto L_0x002d
            if (r6 == r3) goto L_0x002d
            r6 = 1
            goto L_0x002e
        L_0x002d:
            r6 = 0
        L_0x002e:
            com.tencent.smtt.sdk.TbsDownloader.c((android.content.Context) r5)     // Catch:{ all -> 0x0079 }
            java.io.File r3 = getTbsFolderDir(r5)     // Catch:{ all -> 0x0079 }
            java.lang.String r4 = "core_share_decouple"
            com.tencent.smtt.utils.FileUtil.a((java.io.File) r3, (boolean) r1, (java.lang.String) r4)     // Catch:{ all -> 0x0079 }
            java.lang.String r3 = "delete downloaded apk success"
            com.tencent.smtt.utils.TbsLog.i(r0, r3, r2)     // Catch:{ all -> 0x0079 }
            java.lang.ThreadLocal<java.lang.Integer> r2 = com.tencent.smtt.sdk.o.a     // Catch:{ all -> 0x0079 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x0079 }
            r2.set(r3)     // Catch:{ all -> 0x0079 }
            java.io.File r2 = new java.io.File     // Catch:{ all -> 0x0079 }
            java.io.File r3 = r5.getFilesDir()     // Catch:{ all -> 0x0079 }
            java.lang.String r4 = "bugly_switch.txt"
            r2.<init>(r3, r4)     // Catch:{ all -> 0x0079 }
            boolean r3 = r2.exists()     // Catch:{ all -> 0x0079 }
            if (r3 == 0) goto L_0x005c
            r2.delete()     // Catch:{ all -> 0x0079 }
        L_0x005c:
            if (r6 == 0) goto L_0x0092
            com.tencent.smtt.sdk.o r6 = com.tencent.smtt.sdk.o.a()     // Catch:{ all -> 0x0079 }
            java.io.File r6 = r6.q(r5)     // Catch:{ all -> 0x0079 }
            com.tencent.smtt.sdk.o r2 = com.tencent.smtt.sdk.o.a()     // Catch:{ all -> 0x0079 }
            java.io.File r1 = r2.f((android.content.Context) r5, (int) r1)     // Catch:{ all -> 0x0079 }
            com.tencent.smtt.utils.FileUtil.b((java.io.File) r6, (java.io.File) r1)     // Catch:{ all -> 0x0079 }
            com.tencent.smtt.sdk.o r6 = com.tencent.smtt.sdk.o.a()     // Catch:{ all -> 0x0079 }
            r6.c(r5)     // Catch:{ all -> 0x0079 }
            goto L_0x0092
        L_0x0079:
            r5 = move-exception
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r1 = "QbSdk reset exception:"
            r6.append(r1)
            java.lang.String r5 = android.util.Log.getStackTraceString(r5)
            r6.append(r5)
            java.lang.String r5 = r6.toString()
            com.tencent.smtt.utils.TbsLog.e(r0, r5)
        L_0x0092:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.QbSdk.reset(android.content.Context, boolean):void");
    }

    public static void resetDecoupleCore(Context context) {
        TbsLog.e("QbSdk", "QbSdk resetDecoupleCore!", true);
        try {
            FileUtil.b(o.a().q(context));
        } catch (Throwable th) {
            TbsLog.e("QbSdk", "QbSdk resetDecoupleCore exception:" + Log.getStackTraceString(th));
        }
    }

    public static void resumeDownload() {
        TbsDownloader.resumeDownload();
    }

    public static void setCurrentID(String str) {
        if (str != null && str.startsWith(TID_QQNumber_Prefix)) {
            String substring = str.substring(3);
            A = "0000000000000000".substring(substring.length()) + substring;
        }
    }

    public static void setDisableUnpreinitBySwitch(boolean z2) {
        C = z2;
        TbsLog.i("QbSdk", "setDisableUnpreinitBySwitch -- mDisableUnpreinitBySwitch is " + C);
    }

    public static void setDisableUseHostBackupCoreBySwitch(boolean z2) {
        mDisableUseHostBackupCore = z2;
        TbsLog.i("QbSdk", "setDisableUseHostBackupCoreBySwitch -- mDisableUseHostBackupCore is " + mDisableUseHostBackupCore);
    }

    public static void setDownloadWithoutWifi(boolean z2) {
        G = z2;
    }

    public static void setNeedInitX5FirstTime(boolean z2) {
        w = z2;
    }

    public static void setNetLogEncryptionKey(String str) {
        w a2 = w.a();
        if (a2 != null && a2.b()) {
            try {
                a2.c().b().invokeStaticMethod("com.tencent.smtt.livelog.NetLogManager", "setNetLogEncryptionKey", new Class[]{String.class}, str);
            } catch (Exception unused) {
            }
        }
    }

    public static void setNewDnsHostList(String str) {
        w a2 = w.a();
        if (a2 != null && a2.b()) {
            try {
                a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "setNewDnsHostList", new Class[]{String.class}, str);
            } catch (Exception unused) {
            }
        }
    }

    public static void setOnlyDownload(boolean z2) {
        k = z2;
    }

    public static void setQQBuildNumber(String str) {
        B = str;
    }

    public static void setTBSInstallingStatus(boolean z2) {
        H = z2;
    }

    public static void setTbsListener(TbsListener tbsListener) {
        E = tbsListener;
    }

    public static void setTbsLogClient(TbsLogClient tbsLogClient) {
        TbsLog.setTbsLogClient(tbsLogClient);
    }

    public static void setUploadCode(Context context, int i2) {
        TbsDownloadUpload instance;
        Map<String, Object> map;
        Integer valueOf;
        String str;
        if (i2 >= 130 && i2 <= 139) {
            instance = TbsDownloadUpload.getInstance(context);
            map = instance.a;
            valueOf = Integer.valueOf(i2);
            str = TbsDownloadUpload.TbsUploadKey.KEY_NEEDDOWNLOAD_CODE;
        } else if (i2 >= 150 && i2 <= 159) {
            instance = TbsDownloadUpload.getInstance(context);
            map = instance.a;
            valueOf = Integer.valueOf(i2);
            str = TbsDownloadUpload.TbsUploadKey.KEY_STARTDOWNLOAD_CODE;
        } else {
            return;
        }
        map.put(str, valueOf);
        instance.commit();
    }

    public static int startMiniQBToLoadUrl(Context context, String str, HashMap<String, String> hashMap, ValueCallback<String> valueCallback) {
        TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.INFO_CODE_MINIQB_STARTMINIQBTOLOADURL_COUNTS);
        if (context == null) {
            return -100;
        }
        w a2 = w.a();
        a2.a(context);
        if (!a2.b()) {
            TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.INFO_CODE_MINIQB_STARTMINIQBTOLOADURL_ISNOTX5CORE);
            Log.e("QbSdk", "startMiniQBToLoadUrl  ret = -102");
            return -102;
        } else if (context != null && context.getApplicationInfo().packageName.equals("com.nd.android.pandahome2") && getTbsVersion(context) < 25487) {
            return -101;
        } else {
            int a3 = a2.c().a(context, str, hashMap, (String) null, valueCallback);
            if (a3 == 0) {
                TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.INFO_CODE_MINIQB_STARTMINIQBTOLOADURL_SUCCESS);
            } else {
                TbsLogReport instance = TbsLogReport.getInstance(context);
                instance.setLoadErrorCode((int) TbsListener.ErrorCode.INFO_CODE_MINIQB_STARTMINIQBTOLOADURL_FAILED, "" + a3);
            }
            Log.e("QbSdk", "startMiniQBToLoadUrl  ret = " + a3);
            return a3;
        }
    }

    public static boolean startQBForDoc(Context context, String str, int i2, int i3, String str2, Bundle bundle) {
        HashMap hashMap = new HashMap();
        hashMap.put("ChannelID", context.getApplicationContext().getApplicationInfo().processName);
        hashMap.put("PosID", Integer.toString(i2));
        return MttLoader.openDocWithQb(context, str, i3, str2, hashMap, bundle);
    }

    public static boolean startQBForVideo(Context context, String str, int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put("ChannelID", context.getApplicationInfo().processName);
        hashMap.put("PosID", Integer.toString(i2));
        return MttLoader.openVideoWithQb(context, str, hashMap);
    }

    public static boolean startQBToLoadurl(Context context, String str, int i2, WebView webView) {
        w a2;
        Object invokeStaticMethod;
        IX5WebViewBase iX5WebViewBase;
        HashMap hashMap = new HashMap();
        hashMap.put("ChannelID", context.getApplicationInfo().processName);
        hashMap.put("PosID", Integer.toString(i2));
        if (webView == null) {
            try {
                String str2 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).packageName;
                if (!((str2 != TbsConfig.APP_WX && str2 != TbsConfig.APP_QQ) || (a2 = w.a()) == null || !a2.b() || (invokeStaticMethod = a2.c().b().invokeStaticMethod("com.tencent.smtt.webkit.WebViewList", "getCurrentMainWebviewJustForQQandWechat", new Class[0], new Object[0])) == null || (iX5WebViewBase = (IX5WebViewBase) invokeStaticMethod) == null)) {
                    webView = (WebView) iX5WebViewBase.getView().getParent();
                }
            } catch (Exception unused) {
            }
        }
        return MttLoader.loadUrl(context, str, hashMap, "QbSdk.startQBToLoadurl", webView) == 0;
    }

    public static boolean startQBWithBrowserlist(Context context, String str, int i2) {
        boolean startQBToLoadurl = startQBToLoadurl(context, str, i2, (WebView) null);
        if (!startQBToLoadurl) {
            openBrowserList(context, str, (ValueCallback<String>) null);
        }
        return startQBToLoadurl;
    }

    public static boolean startQbOrMiniQBToLoadUrl(Context context, String str, HashMap<String, String> hashMap, ValueCallback<String> valueCallback) {
        if (context == null) {
            return false;
        }
        w a2 = w.a();
        a2.a(context);
        if (hashMap != null && NetConstant.PAGE_ID_NAV_GUIDE_INTRODUCE.equals(hashMap.get("PosID")) && a2.b()) {
            Bundle bundle = (Bundle) a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "getAdWebViewInfoFromX5Core", new Class[0], new Object[0]);
        }
        if (MttLoader.loadUrl(context, str, hashMap, "QbSdk.startMiniQBToLoadUrl", (WebView) null) != 0) {
            return a2.b() && (context == null || !context.getApplicationInfo().packageName.equals("com.nd.android.pandahome2") || getTbsVersion(context) >= 25487) && a2.c().a(context, str, hashMap, (String) null, valueCallback) == 0;
        }
        return true;
    }

    public static void unForceSysWebView() {
        b = false;
        TbsLog.e("QbSdk", "sys WebView: unForceSysWebView called");
    }

    public static synchronized boolean unPreInit(Context context) {
        synchronized (QbSdk.class) {
        }
        return true;
    }

    public static void uploadNetLog(String str) {
        w a2 = w.a();
        if (a2 != null && a2.b()) {
            try {
                a2.c().b().invokeStaticMethod("com.tencent.smtt.livelog.NetLogManager", "uploadNetLog", new Class[]{String.class}, str);
            } catch (Exception unused) {
            }
        }
    }

    public static boolean useSoftWare() {
        Object obj = r;
        if (obj == null) {
            return false;
        }
        Object a2 = i.a(obj, "useSoftWare", (Class<?>[]) new Class[0], new Object[0]);
        if (a2 == null) {
            a2 = i.a(r, "useSoftWare", (Class<?>[]) new Class[]{Integer.TYPE}, Integer.valueOf(a.a()));
        }
        if (a2 == null) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }
}
