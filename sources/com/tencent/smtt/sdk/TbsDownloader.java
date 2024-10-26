package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.TbsDownloadUpload;
import com.tencent.smtt.sdk.TbsListener;
import com.tencent.smtt.sdk.TbsLogReport;
import com.tencent.smtt.utils.Apn;
import com.tencent.smtt.utils.FileUtil;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.a;
import com.tencent.smtt.utils.b;
import java.io.File;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.time.DateUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class TbsDownloader {
    public static final boolean DEBUG_DISABLE_DOWNLOAD = false;
    public static boolean DOWNLOAD_OVERSEA_TBS = false;
    public static final String LOGTAG = "TbsDownload";
    public static final String TBS_METADATA = "com.tencent.mm.BuildInfo.CLIENT_VERSION";
    static boolean a = false;
    private static String b = null;
    /* access modifiers changed from: private */
    public static Context c = null;
    private static Handler d = null;
    private static String e = null;
    private static Object f = new byte[0];
    /* access modifiers changed from: private */
    public static l g = null;
    private static HandlerThread h = null;
    private static boolean i = false;
    private static boolean j = false;
    private static boolean k = false;
    private static long l = -1;

    public interface TbsDownloaderCallback {
        void onNeedDownloadFinish(boolean z, int i);
    }

    protected static File a(int i2) {
        String str;
        String[] coreProviderAppList = TbsShareManager.getCoreProviderAppList();
        int length = coreProviderAppList.length;
        File file = null;
        int i3 = 0;
        while (true) {
            if (i3 >= length) {
                break;
            }
            String str2 = coreProviderAppList[i3];
            if (!str2.equals(c.getApplicationInfo().packageName)) {
                file = new File(FileUtil.a(c, str2, 4, false), getOverSea(c) ? "x5.oversea.tbs.org" : getBackupFileName(false));
                if (!file.exists()) {
                    str = "can not find local backup core file";
                } else if (a.a(c, file) == i2) {
                    TbsLog.i(LOGTAG, "local tbs version fond,path = " + file.getAbsolutePath());
                    break;
                } else {
                    str = "version is not match";
                }
                TbsLog.i(LOGTAG, str);
            }
            i3++;
        }
        return file;
    }

    private static String a(String str) {
        return str == null ? "" : str;
    }

    private static JSONArray a(boolean z) {
        File file;
        boolean z2;
        JSONArray jSONArray = new JSONArray();
        for (String a2 : TbsShareManager.getCoreProviderAppList()) {
            String a3 = FileUtil.a(c, a2, 4, false);
            if (z) {
                String backupFileName = getOverSea(c) ? "x5.oversea.tbs.org" : getBackupFileName(false);
            } else {
                file = new File(a3, "x5.tbs.decouple");
            }
            if (file.exists()) {
                long a4 = (long) a.a(c, file);
                if (a4 > 0) {
                    int i2 = 0;
                    while (true) {
                        if (i2 >= jSONArray.length()) {
                            z2 = false;
                            break;
                        } else if (((long) jSONArray.optInt(i2)) == a4) {
                            z2 = true;
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (!z2) {
                        jSONArray.put(a4);
                    }
                }
            }
        }
        return jSONArray;
    }

    private static JSONObject a(boolean z, boolean z2, boolean z3) {
        int i2;
        int i3;
        int i4;
        int i5;
        Object a2;
        boolean z4 = z;
        boolean z5 = z2;
        boolean z6 = z3;
        TbsLog.i(LOGTAG, "[TbsDownloader.postJsonData]isQuery: " + z4 + " forDecoupleCore is " + z6);
        TbsDownloadConfig instance = TbsDownloadConfig.getInstance(c);
        String b2 = b(c);
        String g2 = b.g(c);
        String f2 = b.f(c);
        String i6 = b.i(c);
        String id = TimeZone.getDefault().getID();
        String str = "";
        String str2 = id != null ? id : str;
        try {
            TelephonyManager telephonyManager = (TelephonyManager) c.getSystemService("phone");
            if (telephonyManager != null) {
                id = telephonyManager.getSimCountryIso();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (id != null) {
            str = id;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            boolean z7 = false;
            if (m.a(c).c("tpatch_num") < 5) {
                jSONObject.put("REQUEST_TPATCH", TbsShareManager.isThirdPartyApp(c) ? 2 : 1);
            } else {
                jSONObject.put("REQUEST_TPATCH", 0);
            }
            jSONObject.put("TIMEZONEID", str2);
            jSONObject.put("COUNTRYISO", str);
            if (b.c()) {
                i2 = 1;
                jSONObject.put("REQUEST_64", 1);
            } else {
                i2 = 1;
            }
            jSONObject.put("PROTOCOLVERSION", i2);
            if (TbsShareManager.isThirdPartyApp(c)) {
                i3 = QbSdk.c ? TbsShareManager.a(c, false) : TbsDownloadConfig.getInstance(c).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0);
            } else {
                i3 = z6 ? o.a().j(c) : o.a().n(c);
                if (i3 == 0 && o.a().m(c)) {
                    i3 = -1;
                    if (TbsConfig.APP_QQ.equals(c.getApplicationInfo().packageName)) {
                        TbsDownloadUpload.clear();
                        TbsDownloadUpload instance2 = TbsDownloadUpload.getInstance(c);
                        instance2.a.put(TbsDownloadUpload.TbsUploadKey.KEY_LOCAL_CORE_VERSION, -1);
                        instance2.commit();
                        TbsPVConfig.releaseInstance();
                        if (TbsPVConfig.getInstance(c).getLocalCoreVersionMoreTimes() == 1) {
                            i3 = o.a().j(c);
                        }
                    }
                }
                TbsLog.i(LOGTAG, "[TbsDownloader.postJsonData] tbsLocalVersion=" + i3 + " isDownloadForeground=" + z5);
                if (z5 && !o.a().m(c)) {
                    i3 = 0;
                }
            }
            jSONObject.put("FUNCTION", z4 ? 2 : i3 == 0 ? 0 : 1);
            if (TbsShareManager.isThirdPartyApp(c)) {
                JSONArray g3 = g();
                jSONObject.put("TBSVLARR", g3);
                instance.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_LAST_THIRDAPP_SENDREQUEST_COREVERSION, g3.toString());
                instance.commit();
                if (QbSdk.c) {
                    jSONObject.put("THIRDREQ", 1);
                }
            } else {
                JSONArray a3 = a(z3);
                if (Apn.getApnType(c) != 3 && a3.length() != 0 && i3 == 0 && z4) {
                    jSONObject.put("TBSBACKUPARR", a3);
                }
            }
            jSONObject.put("APPN", c.getPackageName());
            jSONObject.put("APPVN", a(instance.mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_APP_VERSIONNAME, (String) null)));
            jSONObject.put("APPVC", instance.mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_APP_VERSIONCODE, 0));
            jSONObject.put("APPMETA", a(instance.mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_APP_METADATA, (String) null)));
            jSONObject.put("TBSSDKV", 43993);
            jSONObject.put("TBSV", i3);
            jSONObject.put("DOWNLOADDECOUPLECORE", z6 ? 1 : 0);
            instance.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOADDECOUPLECORE, Integer.valueOf(z6 ? 1 : 0));
            instance.commit();
            if (i3 != 0) {
                jSONObject.put("TBSBACKUPV", g.c(z6));
            }
            jSONObject.put("CPU", e);
            jSONObject.put("UA", b2);
            jSONObject.put("IMSI", a(g2));
            jSONObject.put("IMEI", a(f2));
            jSONObject.put("ANDROID_ID", a(i6));
            jSONObject.put("GUID", b.e(c));
            if (!TbsShareManager.isThirdPartyApp(c)) {
                if (i3 != 0) {
                    jSONObject.put("STATUS", QbSdk.a(c, i3) ? 0 : 1);
                } else {
                    jSONObject.put("STATUS", 0);
                }
                jSONObject.put("TBSDV", o.a().i(c));
            }
            boolean z8 = TbsDownloadConfig.getInstance(c).mPreferences.getBoolean(TbsDownloadConfig.TbsConfigKey.KEY_FULL_PACKAGE, false);
            if ((!QbSdk.isEnableSensitiveApi() || (a2 = QbSdk.a(c, "can_unlzma", (Bundle) null)) == null || !(a2 instanceof Boolean)) ? false : ((Boolean) a2).booleanValue()) {
                i4 = 1;
                z7 = !z8;
            } else {
                i4 = 1;
            }
            if (z7) {
                jSONObject.put("REQUEST_LZMA", i4);
            }
            if (getOverSea(c)) {
                i5 = 1;
                jSONObject.put("OVERSEA", 1);
            } else {
                i5 = 1;
            }
            if (z5) {
                jSONObject.put("DOWNLOAD_FOREGROUND", i5);
            }
        } catch (Exception unused) {
        }
        TbsLog.i(LOGTAG, "[TbsDownloader.postJsonData] jsonData=" + jSONObject.toString());
        return jSONObject;
    }

    private static void a(JSONArray jSONArray) {
        boolean z;
        String[] f2 = f();
        int length = f2.length;
        int i2 = 0;
        while (true) {
            boolean z2 = true;
            if (i2 >= length) {
                break;
            }
            String str = f2[i2];
            int sharedTbsCoreVersion = TbsShareManager.getSharedTbsCoreVersion(c, str);
            if (sharedTbsCoreVersion > 0) {
                Context packageContext = TbsShareManager.getPackageContext(c, str, true);
                if (packageContext != null && !o.a().g(packageContext)) {
                    TbsLog.e(LOGTAG, "host check failed,packageName = " + str);
                } else if (a(c, sharedTbsCoreVersion)) {
                    TbsLog.i(LOGTAG, "add CoreVersionToJsonData,version+" + sharedTbsCoreVersion + " is in black list");
                } else {
                    int i3 = 0;
                    while (true) {
                        if (i3 >= jSONArray.length()) {
                            z2 = false;
                            break;
                        } else if (jSONArray.optInt(i3) == sharedTbsCoreVersion) {
                            break;
                        } else {
                            i3++;
                        }
                    }
                    if (!z2) {
                        jSONArray.put(sharedTbsCoreVersion);
                    }
                }
            }
            i2++;
        }
        for (String str2 : f()) {
            int coreShareDecoupleCoreVersion = TbsShareManager.getCoreShareDecoupleCoreVersion(c, str2);
            if (coreShareDecoupleCoreVersion > 0) {
                Context packageContext2 = TbsShareManager.getPackageContext(c, str2, true);
                if (packageContext2 == null || o.a().g(packageContext2)) {
                    int i4 = 0;
                    while (true) {
                        if (i4 >= jSONArray.length()) {
                            z = false;
                            break;
                        } else if (jSONArray.optInt(i4) == coreShareDecoupleCoreVersion) {
                            z = true;
                            break;
                        } else {
                            i4++;
                        }
                    }
                    if (!z) {
                        jSONArray.put(coreShareDecoupleCoreVersion);
                    }
                } else {
                    TbsLog.e(LOGTAG, "host check failed,packageName = " + str2);
                }
            }
        }
    }

    private static void a(boolean z, TbsDownloaderCallback tbsDownloaderCallback, boolean z2) {
        TbsLog.i(LOGTAG, "[TbsDownloader.queryConfig]");
        d.removeMessages(100);
        Message obtain = Message.obtain(d, 100);
        if (tbsDownloaderCallback != null) {
            obtain.obj = tbsDownloaderCallback;
        }
        obtain.arg1 = 0;
        obtain.arg1 = z ? 1 : 0;
        obtain.arg2 = z2 ? 1 : 0;
        obtain.sendToTarget();
    }

    static boolean a(Context context) {
        return TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOADDECOUPLECORE, 0) == 1;
    }

    static boolean a(Context context, int i2) {
        return Build.VERSION.SDK_INT > 28 && context.getApplicationInfo().targetSdkVersion > 28 && i2 > 0 && i2 < 45114;
    }

    private static boolean a(Context context, boolean z) {
        int i2;
        TbsDownloadConfig instance = TbsDownloadConfig.getInstance(context);
        if (Build.VERSION.SDK_INT < 8) {
            i2 = -102;
        } else if (!QbSdk.c && TbsShareManager.isThirdPartyApp(c) && !c()) {
            return false;
        } else {
            if (!instance.mPreferences.contains(TbsDownloadConfig.TbsConfigKey.KEY_IS_OVERSEA)) {
                if (z && !TbsConfig.APP_WX.equals(context.getApplicationInfo().packageName)) {
                    TbsLog.i(LOGTAG, "needDownload-oversea is true, but not WX");
                    z = false;
                }
                instance.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_IS_OVERSEA, Boolean.valueOf(z));
                instance.commit();
                j = z;
                TbsLog.i(LOGTAG, "needDownload-first-called--isoversea = " + z);
            }
            if (!getOverSea(context) || Build.VERSION.SDK_INT == 16 || Build.VERSION.SDK_INT == 17 || Build.VERSION.SDK_INT == 18) {
                Matcher matcher = null;
                String string = instance.mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_DEVICE_CPUABI, (String) null);
                e = string;
                if (TextUtils.isEmpty(string)) {
                    return true;
                }
                try {
                    matcher = Pattern.compile("i686|mips|x86_64").matcher(e);
                } catch (Exception unused) {
                }
                if (matcher == null || !matcher.find()) {
                    return true;
                }
                TbsLog.e(LOGTAG, "can not support x86 devices!!");
                i2 = -104;
            } else {
                TbsLog.i(LOGTAG, "needDownload- return false,  because of  version is " + Build.VERSION.SDK_INT + ", and overea");
                i2 = -103;
            }
        }
        instance.setDownloadInterruptCode(i2);
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:36:0x018e, code lost:
        if (r9.equals(r17) != false) goto L_0x01fa;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean a(android.content.Context r22, boolean r23, boolean r24) {
        /*
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r22)
            com.tencent.smtt.sdk.c r1 = com.tencent.smtt.sdk.c.a()
            r2 = 1000(0x3e8, float:1.401E-42)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            com.tencent.smtt.sdk.TbsDownloader$1 r3 = new com.tencent.smtt.sdk.TbsDownloader$1
            r3.<init>(r0)
            r4 = r22
            r1.a((android.content.Context) r4, (java.lang.Integer) r2, (com.tencent.smtt.sdk.c.a) r3)
            r1 = 0
            if (r23 != 0) goto L_0x01fd
            android.content.SharedPreferences r3 = r0.mPreferences
            java.lang.String r4 = "app_versionname"
            java.lang.String r3 = r3.getString(r4, r1)
            android.content.SharedPreferences r4 = r0.mPreferences
            java.lang.String r5 = "app_versioncode"
            r6 = 0
            int r4 = r4.getInt(r5, r6)
            android.content.SharedPreferences r5 = r0.mPreferences
            java.lang.String r7 = "app_metadata"
            java.lang.String r5 = r5.getString(r7, r1)
            android.content.Context r7 = c
            java.lang.String r7 = com.tencent.smtt.utils.b.c(r7)
            android.content.Context r8 = c
            int r8 = com.tencent.smtt.utils.b.d(r8)
            android.content.Context r9 = c
            java.lang.String r10 = "com.tencent.mm.BuildInfo.CLIENT_VERSION"
            java.lang.String r9 = com.tencent.smtt.utils.b.a((android.content.Context) r9, (java.lang.String) r10)
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "[TbsDownloader.needSendQueryRequest] appVersionName="
            r10.append(r11)
            r10.append(r7)
            java.lang.String r11 = " oldAppVersionName="
            r10.append(r11)
            r10.append(r3)
            java.lang.String r11 = " appVersionCode="
            r10.append(r11)
            r10.append(r8)
            java.lang.String r11 = " oldAppVersionCode="
            r10.append(r11)
            r10.append(r4)
            java.lang.String r11 = " appMetadata="
            r10.append(r11)
            r10.append(r9)
            java.lang.String r11 = " oldAppVersionMetadata="
            r10.append(r11)
            r10.append(r5)
            java.lang.String r10 = r10.toString()
            java.lang.String r11 = "TbsDownload"
            com.tencent.smtt.utils.TbsLog.i(r11, r10)
            long r12 = java.lang.System.currentTimeMillis()
            android.content.SharedPreferences r10 = r0.mPreferences
            java.lang.String r14 = "last_check"
            r1 = 0
            r16 = r7
            long r6 = r10.getLong(r14, r1)
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r15 = "[TbsDownloader.needSendQueryRequest] timeLastCheck="
            r10.append(r15)
            r10.append(r6)
            java.lang.String r15 = " timeNow="
            r10.append(r15)
            r10.append(r12)
            java.lang.String r10 = r10.toString()
            com.tencent.smtt.utils.TbsLog.i(r11, r10)
            if (r24 == 0) goto L_0x00d5
            android.content.SharedPreferences r10 = r0.mPreferences
            boolean r10 = r10.contains(r14)
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r15 = "[TbsDownloader.needSendQueryRequest] hasLaskCheckKey="
            r14.append(r15)
            r14.append(r10)
            java.lang.String r14 = r14.toString()
            com.tencent.smtt.utils.TbsLog.i(r11, r14)
            if (r10 == 0) goto L_0x00d5
            int r10 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
            if (r10 != 0) goto L_0x00d5
            r6 = r12
        L_0x00d5:
            android.content.SharedPreferences r10 = r0.mPreferences
            java.lang.String r14 = "last_request_success"
            long r14 = r10.getLong(r14, r1)
            android.content.SharedPreferences r10 = r0.mPreferences
            r17 = r5
            java.lang.String r5 = "count_request_fail_in_24hours"
            long r18 = r10.getLong(r5, r1)
            long r1 = r0.getRetryInterval()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r5 = "retryInterval = "
            r0.append(r5)
            r0.append(r1)
            java.lang.String r5 = " s"
            r0.append(r5)
            java.lang.String r0 = r0.toString()
            com.tencent.smtt.utils.TbsLog.i(r11, r0)
            com.tencent.smtt.sdk.TbsPVConfig.releaseInstance()
            android.content.Context r0 = c
            com.tencent.smtt.sdk.TbsPVConfig r0 = com.tencent.smtt.sdk.TbsPVConfig.getInstance(r0)
            int r0 = r0.getEmergentCoreVersion()
            android.content.Context r5 = c
            com.tencent.smtt.sdk.TbsDownloadConfig r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r5)
            android.content.SharedPreferences r5 = r5.mPreferences
            java.lang.String r10 = "tbs_download_version"
            r11 = 0
            int r5 = r5.getInt(r10, r11)
            long r6 = r12 - r6
            r20 = 1000(0x3e8, double:4.94E-321)
            long r1 = r1 * r20
            int r10 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
            if (r10 <= 0) goto L_0x012c
            goto L_0x01fd
        L_0x012c:
            com.tencent.smtt.sdk.o r10 = com.tencent.smtt.sdk.o.a()
            android.content.Context r11 = c
            int r10 = r10.j(r11)
            if (r0 <= r10) goto L_0x013c
            if (r0 <= r5) goto L_0x013c
            goto L_0x01fd
        L_0x013c:
            android.content.Context r0 = c
            boolean r0 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r0)
            if (r0 == 0) goto L_0x0157
            r10 = 0
            int r0 = (r14 > r10 ? 1 : (r14 == r10 ? 0 : -1))
            if (r0 <= 0) goto L_0x0157
            long r12 = r12 - r14
            int r0 = (r12 > r1 ? 1 : (r12 == r1 ? 0 : -1))
            if (r0 <= 0) goto L_0x0157
            r0 = 11
            int r2 = (r18 > r0 ? 1 : (r18 == r0 ? 0 : -1))
            if (r2 >= 0) goto L_0x0157
            goto L_0x01fd
        L_0x0157:
            android.content.Context r0 = c
            boolean r0 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r0)
            if (r0 == 0) goto L_0x0178
            android.content.Context r0 = c
            int r0 = com.tencent.smtt.sdk.TbsShareManager.findCoreForThirdPartyApp(r0)
            if (r0 != 0) goto L_0x0178
            boolean r0 = e()
            if (r0 != 0) goto L_0x0178
            com.tencent.smtt.sdk.o r0 = com.tencent.smtt.sdk.o.a()
            android.content.Context r1 = c
            r0.e(r1)
            goto L_0x01fd
        L_0x0178:
            if (r16 == 0) goto L_0x0191
            if (r8 == 0) goto L_0x0191
            if (r9 == 0) goto L_0x0191
            r0 = r16
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x01fd
            if (r8 != r4) goto L_0x01fd
            r1 = r17
            boolean r0 = r9.equals(r1)
            if (r0 != 0) goto L_0x01fa
            goto L_0x01fd
        L_0x0191:
            r0 = r16
            r1 = r17
            android.content.Context r2 = c
            boolean r2 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r2)
            if (r2 == 0) goto L_0x01fa
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r5 = "timeNow - timeLastCheck is "
            r2.append(r5)
            r2.append(r6)
            java.lang.String r5 = " TbsShareManager.findCoreForThirdPartyApp(sAppContext) is "
            r2.append(r5)
            android.content.Context r5 = c
            int r5 = com.tencent.smtt.sdk.TbsShareManager.findCoreForThirdPartyApp(r5)
            r2.append(r5)
            java.lang.String r5 = " sendRequestWithSameHostCoreVersion() is "
            r2.append(r5)
            boolean r5 = e()
            r2.append(r5)
            java.lang.String r5 = " appVersionName is "
            r2.append(r5)
            r2.append(r0)
            java.lang.String r0 = " appVersionCode is "
            r2.append(r0)
            r2.append(r8)
            java.lang.String r0 = " appMetadata is "
            r2.append(r0)
            r2.append(r9)
            java.lang.String r0 = " oldAppVersionName is "
            r2.append(r0)
            r2.append(r3)
            java.lang.String r0 = " oldAppVersionCode is "
            r2.append(r0)
            r2.append(r4)
            java.lang.String r0 = " oldAppVersionMetadata is "
            r2.append(r0)
            r2.append(r1)
            java.lang.String r0 = r2.toString()
            r1 = r0
            goto L_0x01fb
        L_0x01fa:
            r1 = 0
        L_0x01fb:
            r2 = 0
            goto L_0x01ff
        L_0x01fd:
            r1 = 0
            r2 = 1
        L_0x01ff:
            if (r2 != 0) goto L_0x0226
            android.content.Context r0 = c
            boolean r0 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r0)
            if (r0 == 0) goto L_0x0226
            android.content.Context r0 = c
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r0)
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r0 = r0.tbsLogInfo()
            r3 = -119(0xffffffffffffff89, float:NaN)
            r0.setErrorCode(r3)
            r0.setFailDetail((java.lang.String) r1)
            android.content.Context r1 = c
            com.tencent.smtt.sdk.TbsLogReport r1 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r1)
            com.tencent.smtt.sdk.TbsLogReport$EventType r3 = com.tencent.smtt.sdk.TbsLogReport.EventType.TYPE_DOWNLOAD
            r1.eventReport(r3, r0)
        L_0x0226:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsDownloader.a(android.content.Context, boolean, boolean):boolean");
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processExcHandler(RegionMaker.java:1043)
        	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:975)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    /* JADX WARNING: Removed duplicated region for block: B:320:0x079e  */
    private static boolean a(java.lang.String r33, int r34, boolean r35, boolean r36, boolean r37) throws java.lang.Exception {
        /*
            r0 = r33
            r1 = r34
            r2 = r35
            r3 = r36
            r4 = r37
            java.lang.String r5 = "TbsDownload"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "[TbsDownloader.readResponse] response="
            r6.append(r7)
            r6.append(r0)
            java.lang.String r7 = "isNeedInstall="
            r6.append(r7)
            r6.append(r4)
            java.lang.String r6 = r6.toString()
            com.tencent.smtt.utils.TbsLog.i(r5, r6)
            android.content.Context r5 = c
            com.tencent.smtt.sdk.TbsDownloadConfig r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r5)
            boolean r6 = android.text.TextUtils.isEmpty(r33)
            r7 = 0
            java.lang.Integer r8 = java.lang.Integer.valueOf(r7)
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r7)
            if (r6 == 0) goto L_0x004f
            if (r2 == 0) goto L_0x0042
            r0 = -108(0xffffffffffffff94, float:NaN)
            goto L_0x0044
        L_0x0042:
            r0 = -208(0xffffffffffffff30, float:NaN)
        L_0x0044:
            r5.setDownloadInterruptCode(r0)
            java.lang.String r0 = "TbsDownload"
            java.lang.String r1 = "[TbsDownloader.readResponse] return #1,response is empty..."
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
            return r7
        L_0x004f:
            org.json.JSONObject r6 = new org.json.JSONObject
            r6.<init>(r0)
            java.lang.String r0 = "RET"
            int r0 = r6.getInt(r0)
            if (r0 == 0) goto L_0x007d
            if (r2 == 0) goto L_0x0061
            r1 = -109(0xffffffffffffff93, float:NaN)
            goto L_0x0063
        L_0x0061:
            r1 = -209(0xffffffffffffff2f, float:NaN)
        L_0x0063:
            r5.setDownloadInterruptCode(r1)
            java.lang.String r1 = "TbsDownload"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "[TbsDownloader.readResponse] return #2,returnCode="
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            com.tencent.smtt.utils.TbsLog.i(r1, r0)
            return r7
        L_0x007d:
            java.lang.String r0 = "RESPONSECODE"
            int r10 = r6.getInt(r0)
            java.lang.String r0 = "DOWNLOADURL"
            java.lang.String r11 = r6.getString(r0)
            java.lang.String r0 = "URLLIST"
            java.lang.String r12 = ""
            java.lang.String r12 = r6.optString(r0, r12)
            java.lang.String r0 = "TBSAPKSERVERVERSION"
            int r13 = r6.getInt(r0)
            java.lang.String r0 = "DOWNLOADMAXFLOW"
            int r14 = r6.getInt(r0)
            java.lang.String r0 = "DOWNLOAD_MIN_FREE_SPACE"
            int r15 = r6.getInt(r0)
            java.lang.String r0 = "DOWNLOAD_SUCCESS_MAX_RETRYTIMES"
            int r16 = r6.getInt(r0)
            java.lang.String r0 = "DOWNLOAD_FAILED_MAX_RETRYTIMES"
            int r17 = r6.getInt(r0)
            java.lang.String r0 = "DOWNLOAD_SINGLE_TIMEOUT"
            long r18 = r6.getLong(r0)
            java.lang.String r0 = "TBSAPKFILESIZE"
            long r20 = r6.getLong(r0)
            java.lang.String r0 = "RETRY_INTERVAL"
            r22 = r8
            r7 = 0
            long r23 = r6.optLong(r0, r7)
            java.lang.String r0 = "FLOWCTR"
            r7 = -1
            int r7 = r6.optInt(r0, r7)
            java.lang.String r0 = "USEBBACKUPVER"
            int r0 = r6.getInt(r0)     // Catch:{ Exception -> 0x00d3 }
            goto L_0x00d4
        L_0x00d3:
            r0 = 0
        L_0x00d4:
            java.util.Map<java.lang.String, java.lang.Object> r8 = r5.mSyncMap
            java.lang.String r4 = "use_backup_version"
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r8.put(r4, r0)
            if (r2 == 0) goto L_0x0128
            boolean r0 = com.tencent.smtt.sdk.QbSdk.i
            if (r0 == 0) goto L_0x0128
            android.content.Context r0 = c
            boolean r0 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r0)
            if (r0 == 0) goto L_0x0128
            java.lang.String r0 = "BUGLY"
            r8 = 0
            int r0 = r6.optInt(r0, r8)     // Catch:{ all -> 0x010a }
            com.tencent.smtt.sdk.TbsExtensionFunctionManager r8 = com.tencent.smtt.sdk.TbsExtensionFunctionManager.getInstance()     // Catch:{ all -> 0x010a }
            android.content.Context r4 = c     // Catch:{ all -> 0x010a }
            java.lang.String r3 = "bugly_switch.txt"
            r25 = r15
            r15 = 1
            if (r0 != r15) goto L_0x0103
            r0 = 1
            goto L_0x0104
        L_0x0103:
            r0 = 0
        L_0x0104:
            r8.setFunctionEnable(r4, r3, r0)     // Catch:{ all -> 0x0108 }
            goto L_0x012a
        L_0x0108:
            r0 = move-exception
            goto L_0x010d
        L_0x010a:
            r0 = move-exception
            r25 = r15
        L_0x010d:
            java.lang.String r3 = "qbsdk"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r8 = "throwable:"
            r4.append(r8)
            java.lang.String r0 = r0.toString()
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            com.tencent.smtt.utils.TbsLog.i(r3, r0)
            goto L_0x012a
        L_0x0128:
            r25 = r15
        L_0x012a:
            if (r2 == 0) goto L_0x01f5
            java.lang.String r0 = "TEMPLATESWITCH"
            r3 = 0
            int r0 = r6.optInt(r0, r3)     // Catch:{ all -> 0x01da }
            r3 = r0 & 1
            if (r3 == 0) goto L_0x0139
            r3 = 1
            goto L_0x013a
        L_0x0139:
            r3 = 0
        L_0x013a:
            com.tencent.smtt.sdk.TbsExtensionFunctionManager r4 = com.tencent.smtt.sdk.TbsExtensionFunctionManager.getInstance()     // Catch:{ all -> 0x01da }
            android.content.Context r8 = c     // Catch:{ all -> 0x01da }
            java.lang.String r15 = "cookie_switch.txt"
            r4.setFunctionEnable(r8, r15, r3)     // Catch:{ all -> 0x01da }
            java.lang.String r4 = "TbsDownload"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x01da }
            r8.<init>()     // Catch:{ all -> 0x01da }
            java.lang.String r15 = "useCookieCompatiable:"
            r8.append(r15)     // Catch:{ all -> 0x01da }
            r8.append(r3)     // Catch:{ all -> 0x01da }
            java.lang.String r3 = r8.toString()     // Catch:{ all -> 0x01da }
            com.tencent.smtt.utils.TbsLog.w(r4, r3)     // Catch:{ all -> 0x01da }
            r3 = r0 & 2
            if (r3 == 0) goto L_0x0161
            r3 = 1
            goto L_0x0162
        L_0x0161:
            r3 = 0
        L_0x0162:
            com.tencent.smtt.sdk.TbsExtensionFunctionManager r4 = com.tencent.smtt.sdk.TbsExtensionFunctionManager.getInstance()     // Catch:{ all -> 0x01da }
            android.content.Context r8 = c     // Catch:{ all -> 0x01da }
            java.lang.String r15 = "disable_get_apk_version_switch.txt"
            r4.setFunctionEnable(r8, r15, r3)     // Catch:{ all -> 0x01da }
            java.lang.String r4 = "TbsDownload"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x01da }
            r8.<init>()     // Catch:{ all -> 0x01da }
            java.lang.String r15 = "disableGetApkVersionByReadFile:"
            r8.append(r15)     // Catch:{ all -> 0x01da }
            r8.append(r3)     // Catch:{ all -> 0x01da }
            java.lang.String r3 = r8.toString()     // Catch:{ all -> 0x01da }
            com.tencent.smtt.utils.TbsLog.w(r4, r3)     // Catch:{ all -> 0x01da }
            r3 = r0 & 4
            if (r3 == 0) goto L_0x0189
            r3 = 1
            goto L_0x018a
        L_0x0189:
            r3 = 0
        L_0x018a:
            com.tencent.smtt.sdk.TbsExtensionFunctionManager r4 = com.tencent.smtt.sdk.TbsExtensionFunctionManager.getInstance()     // Catch:{ all -> 0x01da }
            android.content.Context r8 = c     // Catch:{ all -> 0x01da }
            java.lang.String r15 = "disable_unpreinit.txt"
            r4.setFunctionEnable(r8, r15, r3)     // Catch:{ all -> 0x01da }
            com.tencent.smtt.sdk.QbSdk.setDisableUnpreinitBySwitch(r3)     // Catch:{ all -> 0x01da }
            java.lang.String r4 = "TbsDownload"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x01da }
            r8.<init>()     // Catch:{ all -> 0x01da }
            java.lang.String r15 = "disableUnpreinitBySwitch:"
            r8.append(r15)     // Catch:{ all -> 0x01da }
            r8.append(r3)     // Catch:{ all -> 0x01da }
            java.lang.String r3 = r8.toString()     // Catch:{ all -> 0x01da }
            com.tencent.smtt.utils.TbsLog.i(r4, r3)     // Catch:{ all -> 0x01da }
            r0 = r0 & 8
            if (r0 == 0) goto L_0x01b4
            r0 = 1
            goto L_0x01b5
        L_0x01b4:
            r0 = 0
        L_0x01b5:
            com.tencent.smtt.sdk.TbsExtensionFunctionManager r3 = com.tencent.smtt.sdk.TbsExtensionFunctionManager.getInstance()     // Catch:{ all -> 0x01da }
            android.content.Context r4 = c     // Catch:{ all -> 0x01da }
            java.lang.String r8 = "disable_use_host_backup_core.txt"
            r3.setFunctionEnable(r4, r8, r0)     // Catch:{ all -> 0x01da }
            com.tencent.smtt.sdk.QbSdk.setDisableUseHostBackupCoreBySwitch(r0)     // Catch:{ all -> 0x01da }
            java.lang.String r3 = "TbsDownload"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x01da }
            r4.<init>()     // Catch:{ all -> 0x01da }
            java.lang.String r8 = "disableUseHostBackupCoreBySwitch:"
            r4.append(r8)     // Catch:{ all -> 0x01da }
            r4.append(r0)     // Catch:{ all -> 0x01da }
            java.lang.String r0 = r4.toString()     // Catch:{ all -> 0x01da }
            com.tencent.smtt.utils.TbsLog.i(r3, r0)     // Catch:{ all -> 0x01da }
            goto L_0x01f5
        L_0x01da:
            r0 = move-exception
            java.lang.String r3 = "qbsdk"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r8 = "throwable:"
            r4.append(r8)
            java.lang.String r0 = r0.toString()
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            com.tencent.smtt.utils.TbsLog.i(r3, r0)
        L_0x01f5:
            java.lang.String r0 = ""
            java.lang.String r4 = "PKGMD5"
            java.lang.String r4 = r6.getString(r4)     // Catch:{ Exception -> 0x0281 }
            java.lang.String r8 = "RESETX5"
            int r8 = r6.getInt(r8)     // Catch:{ Exception -> 0x027d }
            java.lang.String r15 = "UPLOADLOG"
            int r15 = r6.getInt(r15)     // Catch:{ Exception -> 0x0279 }
            java.lang.String r3 = "RESETTOKEN"
            boolean r3 = r6.has(r3)     // Catch:{ Exception -> 0x0275 }
            if (r3 == 0) goto L_0x021f
            java.lang.String r3 = "RESETTOKEN"
            int r3 = r6.getInt(r3)     // Catch:{ Exception -> 0x0277 }
            if (r3 == 0) goto L_0x021b
            r3 = 1
            goto L_0x021c
        L_0x021b:
            r3 = 0
        L_0x021c:
            r26 = r0
            goto L_0x0222
        L_0x021f:
            r26 = r0
            r3 = 0
        L_0x0222:
            java.lang.String r0 = "SETTOKEN"
            boolean r0 = r6.has(r0)     // Catch:{ Exception -> 0x0272 }
            if (r0 == 0) goto L_0x0232
            java.lang.String r0 = "SETTOKEN"
            java.lang.String r0 = r6.getString(r0)     // Catch:{ Exception -> 0x0272 }
            r26 = r0
        L_0x0232:
            java.lang.String r0 = "ENABLE_LOAD_RENAME_FILE_LOCK"
            boolean r0 = r6.has(r0)     // Catch:{ Exception -> 0x0272 }
            if (r0 == 0) goto L_0x0248
            java.lang.String r0 = "ENABLE_LOAD_RENAME_FILE_LOCK"
            int r0 = r6.getInt(r0)     // Catch:{ Exception -> 0x0272 }
            if (r0 == 0) goto L_0x0244
            r0 = 1
            goto L_0x0245
        L_0x0244:
            r0 = 0
        L_0x0245:
            r27 = r0
            goto L_0x024a
        L_0x0248:
            r27 = 1
        L_0x024a:
            java.lang.String r0 = "ENABLE_LOAD_RENAME_FILE_LOCK_WAIT"
            boolean r0 = r6.has(r0)     // Catch:{ Exception -> 0x026f }
            if (r0 == 0) goto L_0x025d
            java.lang.String r0 = "ENABLE_LOAD_RENAME_FILE_LOCK_WAIT"
            int r0 = r6.getInt(r0)     // Catch:{ Exception -> 0x026f }
            if (r0 == 0) goto L_0x025b
            goto L_0x025d
        L_0x025b:
            r0 = 0
            goto L_0x025e
        L_0x025d:
            r0 = 1
        L_0x025e:
            r28 = r12
            r32 = r8
            r8 = r3
            r3 = r26
            r26 = r4
            r4 = r27
            r27 = r14
            r14 = r15
            r15 = r32
            goto L_0x0296
        L_0x026f:
            r0 = r26
            goto L_0x0289
        L_0x0272:
            r0 = r26
            goto L_0x0287
        L_0x0275:
            r26 = r0
        L_0x0277:
            r3 = 0
            goto L_0x0287
        L_0x0279:
            r26 = r0
            r3 = 0
            goto L_0x0286
        L_0x027d:
            r26 = r0
            r3 = 0
            goto L_0x0285
        L_0x0281:
            r26 = r0
            r3 = 0
            r4 = 0
        L_0x0285:
            r8 = 0
        L_0x0286:
            r15 = 0
        L_0x0287:
            r27 = 1
        L_0x0289:
            r26 = r4
            r28 = r12
            r4 = r27
            r27 = r14
            r14 = r15
            r15 = r8
            r8 = r3
            r3 = r0
            r0 = 1
        L_0x0296:
            java.lang.String r12 = "RESETDECOUPLECORE"
            int r12 = r6.getInt(r12)     // Catch:{ Exception -> 0x029d }
            goto L_0x029e
        L_0x029d:
            r12 = 0
        L_0x029e:
            java.lang.String r1 = "RESETTODECOUPLECORE"
            int r1 = r6.getInt(r1)     // Catch:{ Exception -> 0x02a5 }
            goto L_0x02a6
        L_0x02a5:
            r1 = 0
        L_0x02a6:
            java.lang.Object r29 = f
            monitor-enter(r29)
            if (r8 == 0) goto L_0x02b9
            java.util.Map<java.lang.String, java.lang.Object> r8 = r5.mSyncMap     // Catch:{ all -> 0x07ad }
            r30 = r10
            java.lang.String r10 = "tbs_deskey_token"
            r31 = r13
            java.lang.String r13 = ""
            r8.put(r10, r13)     // Catch:{ all -> 0x07ad }
            goto L_0x02bd
        L_0x02b9:
            r30 = r10
            r31 = r13
        L_0x02bd:
            boolean r8 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x07ad }
            if (r8 != 0) goto L_0x02ea
            int r8 = r3.length()     // Catch:{ all -> 0x07ad }
            r10 = 96
            if (r8 != r10) goto L_0x02ea
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x07ad }
            r8.<init>()     // Catch:{ all -> 0x07ad }
            r8.append(r3)     // Catch:{ all -> 0x07ad }
            java.lang.String r3 = "&"
            r8.append(r3)     // Catch:{ all -> 0x07ad }
            java.lang.String r3 = com.tencent.smtt.utils.g.c()     // Catch:{ all -> 0x07ad }
            r8.append(r3)     // Catch:{ all -> 0x07ad }
            java.lang.String r3 = r8.toString()     // Catch:{ all -> 0x07ad }
            java.util.Map<java.lang.String, java.lang.Object> r8 = r5.mSyncMap     // Catch:{ all -> 0x07ad }
            java.lang.String r10 = "tbs_deskey_token"
            r8.put(r10, r3)     // Catch:{ all -> 0x07ad }
        L_0x02ea:
            monitor-exit(r29)     // Catch:{ all -> 0x07ad }
            r3 = 1
            if (r15 != r3) goto L_0x031a
            if (r2 == 0) goto L_0x02f3
            r0 = -110(0xffffffffffffff92, float:NaN)
            goto L_0x02f5
        L_0x02f3:
            r0 = -210(0xffffffffffffff2e, float:NaN)
        L_0x02f5:
            r5.setDownloadInterruptCode(r0)
            android.content.Context r0 = c
            if (r1 != r3) goto L_0x02fe
            r1 = 1
            goto L_0x02ff
        L_0x02fe:
            r1 = 0
        L_0x02ff:
            com.tencent.smtt.sdk.QbSdk.reset(r0, r1)
            java.lang.String r0 = "TbsDownload"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "[TbsDownloader.readResponse] return #3,needResetTbs=1,isQuery="
            r1.append(r3)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
            r1 = 0
            return r1
        L_0x031a:
            if (r4 != 0) goto L_0x031f
            r5.setTbsCoreLoadRenameFileLockEnable(r4)
        L_0x031f:
            if (r0 != 0) goto L_0x0324
            r5.setTbsCoreLoadRenameFileLockWaitEnable(r0)
        L_0x0324:
            r1 = 1
            if (r12 != r1) goto L_0x032c
            android.content.Context r0 = c
            com.tencent.smtt.sdk.QbSdk.resetDecoupleCore(r0)
        L_0x032c:
            if (r14 != r1) goto L_0x033e
            android.os.Handler r0 = d
            r3 = 104(0x68, float:1.46E-43)
            r0.removeMessages(r3)
            android.os.Handler r0 = d
            android.os.Message r0 = android.os.Message.obtain(r0, r3)
            r0.sendToTarget()
        L_0x033e:
            r3 = 86400(0x15180, double:4.26873E-319)
            if (r7 != r1) goto L_0x0355
            r0 = 604800(0x93a80, double:2.98811E-318)
            int r7 = (r23 > r0 ? 1 : (r23 == r0 ? 0 : -1))
            if (r7 <= 0) goto L_0x034c
            r23 = r0
        L_0x034c:
            r7 = 0
            int r0 = (r23 > r7 ? 1 : (r23 == r7 ? 0 : -1))
            if (r0 <= 0) goto L_0x0357
            r3 = r23
            goto L_0x0357
        L_0x0355:
            r7 = 0
        L_0x0357:
            long r0 = getRetryIntervalInSeconds()
            int r10 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r10 < 0) goto L_0x0363
            long r3 = getRetryIntervalInSeconds()
        L_0x0363:
            java.util.Map<java.lang.String, java.lang.Object> r0 = r5.mSyncMap
            java.lang.String r1 = "retry_interval"
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            r0.put(r1, r3)
            if (r2 == 0) goto L_0x0377
            java.lang.String r0 = "DECOUPLECOREVERSION"
            int r0 = r6.getInt(r0)     // Catch:{ Exception -> 0x0387 }
            goto L_0x0388
        L_0x0377:
            android.content.Context r0 = c     // Catch:{ Exception -> 0x0387 }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r0)     // Catch:{ Exception -> 0x0387 }
            android.content.SharedPreferences r0 = r0.mPreferences     // Catch:{ Exception -> 0x0387 }
            java.lang.String r1 = "tbs_decouplecoreversion"
            r3 = 0
            int r0 = r0.getInt(r1, r3)     // Catch:{ Exception -> 0x0387 }
            goto L_0x0388
        L_0x0387:
            r0 = 0
        L_0x0388:
            android.content.Context r1 = c     // Catch:{ Exception -> 0x0398 }
            com.tencent.smtt.sdk.TbsDownloadConfig r1 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r1)     // Catch:{ Exception -> 0x0398 }
            android.content.SharedPreferences r1 = r1.mPreferences     // Catch:{ Exception -> 0x0398 }
            java.lang.String r3 = "tbs_downloaddecouplecore"
            r4 = 0
            int r1 = r1.getInt(r3, r4)     // Catch:{ Exception -> 0x0398 }
            goto L_0x0399
        L_0x0398:
            r1 = 0
        L_0x0399:
            if (r2 == 0) goto L_0x03af
            android.content.Context r3 = c
            boolean r3 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r3)
            if (r3 != 0) goto L_0x03af
            if (r0 != 0) goto L_0x03af
            com.tencent.smtt.sdk.o r0 = com.tencent.smtt.sdk.o.a()
            android.content.Context r3 = c
            int r0 = r0.i(r3)
        L_0x03af:
            java.lang.String r3 = "TbsDownload"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r7 = "in response decoupleCoreVersion is "
            r4.append(r7)
            r4.append(r0)
            java.lang.String r4 = r4.toString()
            com.tencent.smtt.utils.TbsLog.i(r3, r4)
            java.util.Map<java.lang.String, java.lang.Object> r3 = r5.mSyncMap
            java.lang.String r4 = "tbs_decouplecoreversion"
            java.lang.Integer r7 = java.lang.Integer.valueOf(r0)
            r3.put(r4, r7)
            java.util.Map<java.lang.String, java.lang.Object> r3 = r5.mSyncMap
            java.lang.String r4 = "tbs_downloaddecouplecore"
            java.lang.Integer r7 = java.lang.Integer.valueOf(r1)
            r3.put(r4, r7)
            android.content.Context r3 = c
            boolean r3 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r3)
            if (r3 != 0) goto L_0x0418
            if (r0 <= 0) goto L_0x0407
            com.tencent.smtt.sdk.o r3 = com.tencent.smtt.sdk.o.a()
            android.content.Context r4 = c
            int r3 = r3.i(r4)
            if (r0 == r3) goto L_0x0407
            com.tencent.smtt.sdk.o r3 = com.tencent.smtt.sdk.o.a()
            android.content.Context r4 = c
            int r3 = r3.j(r4)
            if (r0 != r3) goto L_0x0407
            com.tencent.smtt.sdk.o r0 = com.tencent.smtt.sdk.o.a()
            android.content.Context r3 = c
            r0.o(r3)
            goto L_0x0418
        L_0x0407:
            if (r0 != 0) goto L_0x0418
            com.tencent.smtt.sdk.o r0 = com.tencent.smtt.sdk.o.a()     // Catch:{ all -> 0x0417 }
            android.content.Context r3 = c     // Catch:{ all -> 0x0417 }
            java.io.File r0 = r0.q(r3)     // Catch:{ all -> 0x0417 }
            com.tencent.smtt.utils.FileUtil.b((java.io.File) r0)     // Catch:{ all -> 0x0417 }
            goto L_0x0418
        L_0x0417:
        L_0x0418:
            boolean r0 = android.text.TextUtils.isEmpty(r11)
            if (r0 == 0) goto L_0x0444
            android.content.Context r0 = c
            boolean r0 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r0)
            if (r0 == 0) goto L_0x0444
            java.util.Map<java.lang.String, java.lang.Object> r0 = r5.mSyncMap
            java.lang.String r1 = "tbs_needdownload"
            r0.put(r1, r9)
            r5.commit()
            if (r2 == 0) goto L_0x043b
            android.content.Context r0 = c
            r3 = r31
            r1 = 0
            com.tencent.smtt.sdk.TbsShareManager.writeCoreInfoForThirdPartyApp(r0, r3, r1)
            goto L_0x043c
        L_0x043b:
            r1 = 0
        L_0x043c:
            java.lang.String r0 = "TbsDownload"
            java.lang.String r2 = "[TbsDownloader.readResponse] return #4,current app is third app..."
            com.tencent.smtt.utils.TbsLog.i(r0, r2)
            return r1
        L_0x0444:
            r3 = r31
            java.lang.String r0 = "TbsDownload"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r7 = "in response responseCode is "
            r4.append(r7)
            r7 = r30
            r4.append(r7)
            java.lang.String r4 = r4.toString()
            com.tencent.smtt.utils.TbsLog.i(r0, r4)
            if (r7 != 0) goto L_0x04a5
            java.util.Map<java.lang.String, java.lang.Object> r0 = r5.mSyncMap
            java.lang.String r1 = "tbs_responsecode"
            java.lang.Integer r3 = java.lang.Integer.valueOf(r7)
            r0.put(r1, r3)
            java.util.Map<java.lang.String, java.lang.Object> r0 = r5.mSyncMap
            java.lang.String r1 = "tbs_needdownload"
            r0.put(r1, r9)
            java.util.Map<java.lang.String, java.lang.Object> r0 = r5.mSyncMap
            java.lang.String r1 = "tbs_download_interrupt_code_reason"
            if (r2 == 0) goto L_0x0482
            r2 = -111(0xffffffffffffff91, float:NaN)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r2)
            goto L_0x048e
        L_0x0482:
            r2 = -211(0xffffffffffffff2d, float:NaN)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r3)
            r5.setDownloadInterruptCode(r2)
        L_0x048e:
            r5.commit()
            android.content.Context r0 = c
            boolean r0 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r0)
            if (r0 != 0) goto L_0x049c
            startDecoupleCoreIfNeeded()
        L_0x049c:
            java.lang.String r0 = "TbsDownload"
            java.lang.String r1 = "[TbsDownloader.readResponse] return #5,responseCode=0"
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
            r4 = 0
            return r4
        L_0x04a5:
            r4 = 0
            android.content.Context r0 = c
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r0)
            android.content.SharedPreferences r0 = r0.mPreferences
            java.lang.String r8 = "tbs_download_version"
            int r0 = r0.getInt(r8, r4)
            if (r0 <= r3) goto L_0x04c4
            com.tencent.smtt.sdk.l r4 = g
            r4.c()
            com.tencent.smtt.sdk.o r4 = com.tencent.smtt.sdk.o.a()
            android.content.Context r8 = c
            r4.p(r8)
        L_0x04c4:
            android.content.Context r4 = c
            boolean r4 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r4)
            if (r4 != 0) goto L_0x04fd
            com.tencent.smtt.sdk.o r4 = com.tencent.smtt.sdk.o.a()
            android.content.Context r8 = c
            r10 = 0
            int r4 = r4.e((android.content.Context) r8, (int) r10)
            if (r4 < r3) goto L_0x04db
            r8 = 1
            goto L_0x04dc
        L_0x04db:
            r8 = 0
        L_0x04dc:
            java.lang.String r10 = "TbsDownload"
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r13 = "tmpCoreVersion is "
            r12.append(r13)
            r12.append(r4)
            java.lang.String r4 = " tbsDownloadVersion is"
            r12.append(r4)
            r12.append(r3)
            java.lang.String r4 = r12.toString()
            com.tencent.smtt.utils.TbsLog.i(r10, r4)
            r4 = r34
            goto L_0x0500
        L_0x04fd:
            r4 = r34
            r8 = 0
        L_0x0500:
            if (r4 >= r3) goto L_0x050a
            boolean r10 = android.text.TextUtils.isEmpty(r11)
            if (r10 != 0) goto L_0x050a
            if (r8 == 0) goto L_0x0590
        L_0x050a:
            r8 = 1
            if (r1 == r8) goto L_0x0590
            java.util.Map<java.lang.String, java.lang.Object> r1 = r5.mSyncMap
            java.lang.String r6 = "tbs_needdownload"
            r1.put(r6, r9)
            if (r2 == 0) goto L_0x053b
            boolean r1 = android.text.TextUtils.isEmpty(r11)
            if (r1 == 0) goto L_0x052a
            java.util.Map<java.lang.String, java.lang.Object> r1 = r5.mSyncMap
            java.lang.String r2 = "tbs_download_interrupt_code_reason"
            r6 = -124(0xffffffffffffff84, float:NaN)
        L_0x0522:
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r1.put(r2, r6)
            goto L_0x055d
        L_0x052a:
            java.util.Map<java.lang.String, java.lang.Object> r1 = r5.mSyncMap
            java.lang.String r2 = "tbs_download_interrupt_code_reason"
            if (r3 > 0) goto L_0x0533
            r6 = -125(0xffffffffffffff83, float:NaN)
            goto L_0x0522
        L_0x0533:
            if (r4 < r3) goto L_0x0538
            r6 = -127(0xffffffffffffff81, float:NaN)
            goto L_0x0522
        L_0x0538:
            r6 = -112(0xffffffffffffff90, float:NaN)
            goto L_0x0522
        L_0x053b:
            r1 = -212(0xffffffffffffff2c, float:NaN)
            boolean r2 = android.text.TextUtils.isEmpty(r11)
            if (r2 == 0) goto L_0x0546
            r1 = -217(0xffffffffffffff27, float:NaN)
            goto L_0x054f
        L_0x0546:
            if (r3 > 0) goto L_0x054b
            r1 = -218(0xffffffffffffff26, float:NaN)
            goto L_0x054f
        L_0x054b:
            if (r4 < r3) goto L_0x054f
            r1 = -219(0xffffffffffffff25, float:NaN)
        L_0x054f:
            java.util.Map<java.lang.String, java.lang.Object> r2 = r5.mSyncMap
            java.lang.String r6 = "tbs_download_interrupt_code_reason"
            java.lang.Integer r7 = java.lang.Integer.valueOf(r1)
            r2.put(r6, r7)
            r5.setDownloadInterruptCode(r1)
        L_0x055d:
            r5.commit()
            java.lang.String r1 = "TbsDownload"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r5 = "version error or downloadUrl empty ,return ahead tbsLocalVersion="
            r2.append(r5)
            r2.append(r4)
            java.lang.String r4 = " tbsDownloadVersion="
            r2.append(r4)
            r2.append(r3)
            java.lang.String r3 = " tbsLastDownloadVersion="
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = " downloadUrl="
            r2.append(r0)
            r2.append(r11)
            java.lang.String r0 = r2.toString()
            com.tencent.smtt.utils.TbsLog.i(r1, r0)
            r1 = 0
            return r1
        L_0x0590:
            android.content.SharedPreferences r0 = r5.mPreferences
            java.lang.String r4 = "tbs_downloadurl"
            r8 = 0
            java.lang.String r0 = r0.getString(r4, r8)
            boolean r0 = r11.equals(r0)
            if (r0 != 0) goto L_0x05b5
            com.tencent.smtt.sdk.l r0 = g
            r0.c()
            java.util.Map<java.lang.String, java.lang.Object> r0 = r5.mSyncMap
            java.lang.String r4 = "tbs_download_failed_retrytimes"
            r8 = r22
            r0.put(r4, r8)
            java.util.Map<java.lang.String, java.lang.Object> r0 = r5.mSyncMap
            java.lang.String r4 = "tbs_download_success_retrytimes"
            r0.put(r4, r8)
            goto L_0x05b7
        L_0x05b5:
            r8 = r22
        L_0x05b7:
            java.util.Map<java.lang.String, java.lang.Object> r0 = r5.mSyncMap
            java.lang.String r4 = "tbs_download_version"
            java.lang.Integer r10 = java.lang.Integer.valueOf(r3)
            r0.put(r4, r10)
            java.lang.String r0 = "TbsDownload"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r10 = "put KEY_TBS_DOWNLOAD_V is "
            r4.append(r10)
            r4.append(r3)
            java.lang.String r4 = r4.toString()
            com.tencent.smtt.utils.TbsLog.i(r0, r4)
            if (r3 <= 0) goto L_0x0604
            r4 = 1
            java.util.Map<java.lang.String, java.lang.Object> r0 = r5.mSyncMap
            if (r1 != r4) goto L_0x05e9
            java.lang.String r8 = "tbs_download_version_type"
            java.lang.Integer r10 = java.lang.Integer.valueOf(r4)
            r0.put(r8, r10)
            goto L_0x05ee
        L_0x05e9:
            java.lang.String r4 = "tbs_download_version_type"
            r0.put(r4, r8)
        L_0x05ee:
            java.lang.String r0 = "TbsDownload"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r8 = "put KEY_TBS_DOWNLOAD_V_TYPE is "
            r4.append(r8)
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
        L_0x0604:
            java.util.Map<java.lang.String, java.lang.Object> r0 = r5.mSyncMap
            java.lang.String r1 = "tbs_downloadurl"
            r0.put(r1, r11)
            java.util.Map<java.lang.String, java.lang.Object> r0 = r5.mSyncMap
            java.lang.String r1 = "tbs_downloadurl_list"
            r4 = r28
            r0.put(r1, r4)
            java.util.Map<java.lang.String, java.lang.Object> r0 = r5.mSyncMap
            java.lang.String r1 = "tbs_responsecode"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r7)
            r0.put(r1, r4)
            java.util.Map<java.lang.String, java.lang.Object> r0 = r5.mSyncMap
            java.lang.String r1 = "tbs_download_maxflow"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r27)
            r0.put(r1, r4)
            java.util.Map<java.lang.String, java.lang.Object> r0 = r5.mSyncMap
            java.lang.String r1 = "tbs_download_min_free_space"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r25)
            r0.put(r1, r4)
            java.util.Map<java.lang.String, java.lang.Object> r0 = r5.mSyncMap
            java.lang.String r1 = "tbs_download_success_max_retrytimes"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r16)
            r0.put(r1, r4)
            java.util.Map<java.lang.String, java.lang.Object> r0 = r5.mSyncMap
            java.lang.String r1 = "tbs_download_failed_max_retrytimes"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r17)
            r0.put(r1, r4)
            java.util.Map<java.lang.String, java.lang.Object> r0 = r5.mSyncMap
            java.lang.String r1 = "tbs_single_timeout"
            java.lang.Long r4 = java.lang.Long.valueOf(r18)
            r0.put(r1, r4)
            java.util.Map<java.lang.String, java.lang.Object> r0 = r5.mSyncMap
            java.lang.String r1 = "tbs_apkfilesize"
            java.lang.Long r4 = java.lang.Long.valueOf(r20)
            r0.put(r1, r4)
            r5.commit()
            if (r26 == 0) goto L_0x066f
            java.util.Map<java.lang.String, java.lang.Object> r0 = r5.mSyncMap
            java.lang.String r1 = "tbs_apk_md5"
            r4 = r26
            r0.put(r1, r4)
        L_0x066f:
            r1 = r36
            if (r1 != 0) goto L_0x06ae
            if (r37 == 0) goto L_0x06ae
            com.tencent.smtt.sdk.o r0 = com.tencent.smtt.sdk.o.a()
            android.content.Context r4 = c
            boolean r0 = r0.b((android.content.Context) r4, (int) r3)
            if (r0 == 0) goto L_0x06ae
            java.util.Map<java.lang.String, java.lang.Object> r0 = r5.mSyncMap
            java.lang.String r1 = "tbs_download_interrupt_code_reason"
            if (r2 == 0) goto L_0x0691
            r2 = -113(0xffffffffffffff8f, float:NaN)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r2)
            goto L_0x069d
        L_0x0691:
            r2 = -213(0xffffffffffffff2b, float:NaN)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r3)
            r5.setDownloadInterruptCode(r2)
        L_0x069d:
            java.util.Map<java.lang.String, java.lang.Object> r0 = r5.mSyncMap
            java.lang.String r1 = "tbs_needdownload"
            r0.put(r1, r9)
            java.lang.String r0 = "TbsDownload"
            java.lang.String r1 = "[TbsDownloader.readResponse] ##6 set needDownload=false"
        L_0x06a8:
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
            r2 = 1
            goto L_0x0795
        L_0x06ae:
            r0 = 100
            if (r1 != 0) goto L_0x0713
            if (r37 == 0) goto L_0x0713
            com.tencent.smtt.sdk.l r3 = g
            r4 = 1
            if (r7 == r4) goto L_0x06bf
            r4 = 2
            if (r7 != r4) goto L_0x06bd
            goto L_0x06bf
        L_0x06bd:
            r4 = 0
            goto L_0x06c0
        L_0x06bf:
            r4 = 1
        L_0x06c0:
            boolean r1 = r3.a((boolean) r1, (boolean) r4)
            if (r1 == 0) goto L_0x0713
            java.util.Map<java.lang.String, java.lang.Object> r1 = r5.mSyncMap
            java.lang.String r2 = "tbs_needdownload"
            r1.put(r2, r9)
            android.content.Context r1 = c
            com.tencent.smtt.sdk.TbsLogReport r1 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r1)
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r1 = r1.tbsLogInfo()
            r1.setErrorCode(r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "use local backup apk in needDownload"
            r0.append(r2)
            com.tencent.smtt.sdk.l r2 = g
            java.lang.String r2 = r2.a
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            r1.setFailDetail((java.lang.String) r0)
            android.content.Context r0 = c
            boolean r0 = a((android.content.Context) r0)
            if (r0 == 0) goto L_0x0703
            android.content.Context r0 = c
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r0)
            com.tencent.smtt.sdk.TbsLogReport$EventType r2 = com.tencent.smtt.sdk.TbsLogReport.EventType.TYPE_DOWNLOAD_DECOUPLE
            goto L_0x070b
        L_0x0703:
            android.content.Context r0 = c
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r0)
            com.tencent.smtt.sdk.TbsLogReport$EventType r2 = com.tencent.smtt.sdk.TbsLogReport.EventType.TYPE_DOWNLOAD
        L_0x070b:
            r0.eventReport(r2, r1)
            java.lang.String r0 = "TbsDownload"
            java.lang.String r1 = "[TbsDownloader.readResponse] ##7 set needDownload=false"
            goto L_0x06a8
        L_0x0713:
            android.content.Context r1 = c
            com.tencent.smtt.sdk.TbsDownloadConfig r1 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r1)
            android.content.SharedPreferences r1 = r1.mPreferences
            java.lang.String r3 = "tbs_download_version_type"
            r4 = 0
            int r1 = r1.getInt(r3, r4)
            r3 = 1
            if (r1 != r3) goto L_0x077b
            com.tencent.smtt.sdk.l r1 = g
            boolean r1 = r1.a()
            if (r1 == 0) goto L_0x077b
            java.util.Map<java.lang.String, java.lang.Object> r1 = r5.mSyncMap
            java.lang.String r2 = "tbs_needdownload"
            r1.put(r2, r9)
            android.content.Context r1 = c
            com.tencent.smtt.sdk.TbsLogReport r1 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r1)
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r1 = r1.tbsLogInfo()
            r1.setErrorCode(r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "installDecoupleCoreFromBackup"
            r0.append(r2)
            com.tencent.smtt.sdk.l r2 = g
            java.lang.String r2 = r2.a
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            r1.setFailDetail((java.lang.String) r0)
            android.content.Context r0 = c
            boolean r0 = a((android.content.Context) r0)
            if (r0 == 0) goto L_0x076a
            android.content.Context r0 = c
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r0)
            com.tencent.smtt.sdk.TbsLogReport$EventType r2 = com.tencent.smtt.sdk.TbsLogReport.EventType.TYPE_DOWNLOAD_DECOUPLE
            goto L_0x0772
        L_0x076a:
            android.content.Context r0 = c
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r0)
            com.tencent.smtt.sdk.TbsLogReport$EventType r2 = com.tencent.smtt.sdk.TbsLogReport.EventType.TYPE_DOWNLOAD
        L_0x0772:
            r0.eventReport(r2, r1)
            java.lang.String r0 = "TbsDownload"
            java.lang.String r1 = "[TbsDownloader.readResponse] ##8 set needDownload=false"
            goto L_0x06a8
        L_0x077b:
            if (r2 != 0) goto L_0x0782
            r0 = -216(0xffffffffffffff28, float:NaN)
            r5.setDownloadInterruptCode(r0)
        L_0x0782:
            java.util.Map<java.lang.String, java.lang.Object> r0 = r5.mSyncMap
            java.lang.String r1 = "tbs_needdownload"
            r2 = 1
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r2)
            r0.put(r1, r3)
            java.lang.String r0 = "TbsDownload"
            java.lang.String r1 = "[TbsDownloader.readResponse] ##9 set needDownload=true"
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
        L_0x0795:
            java.lang.String r0 = "stop_pre_oat"
            r1 = 0
            int r0 = r6.optInt(r0, r1)
            if (r0 != r2) goto L_0x07a9
            java.util.Map<java.lang.String, java.lang.Object> r0 = r5.mSyncMap
            java.lang.String r1 = "tbs_stop_preoat"
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r2)
            r0.put(r1, r3)
        L_0x07a9:
            r5.commit()
            return r2
        L_0x07ad:
            r0 = move-exception
            monitor-exit(r29)     // Catch:{ all -> 0x07ad }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsDownloader.a(java.lang.String, int, boolean, boolean, boolean):boolean");
    }

    protected static File b(int i2) {
        StringBuilder sb;
        String[] coreProviderAppList = TbsShareManager.getCoreProviderAppList();
        int length = coreProviderAppList.length;
        File file = null;
        int i3 = 0;
        while (i3 < length) {
            String str = coreProviderAppList[i3];
            File file2 = new File(FileUtil.a(c, str, 4, false), getOverSea(c) ? "x5.oversea.tbs.org" : getBackupFileName(false));
            if (!file2.exists() || a.a(c, file2) != i2) {
                file2 = new File(FileUtil.a(c, str, 4, false), "x5.tbs.decouple");
                if (!file2.exists() || a.a(c, file2) != i2) {
                    i3++;
                    file = file2;
                } else {
                    sb = new StringBuilder();
                }
            } else {
                sb = new StringBuilder();
            }
            sb.append("local tbs version fond,path = ");
            sb.append(file2.getAbsolutePath());
            TbsLog.i(LOGTAG, sb.toString());
            return file2;
        }
        return file;
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00a5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.String b(android.content.Context r6) {
        /*
            java.lang.String r6 = "ISO8859-1"
            java.lang.String r0 = "UTF-8"
            java.lang.String r1 = b
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L_0x000f
            java.lang.String r6 = b
            return r6
        L_0x000f:
            java.util.Locale r1 = java.util.Locale.getDefault()
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r2.<init>()
            java.lang.String r3 = android.os.Build.VERSION.RELEASE
            java.lang.String r4 = new java.lang.String     // Catch:{ Exception -> 0x0025 }
            byte[] r5 = r3.getBytes(r0)     // Catch:{ Exception -> 0x0025 }
            r4.<init>(r5, r6)     // Catch:{ Exception -> 0x0025 }
            r3 = r4
            goto L_0x0026
        L_0x0025:
        L_0x0026:
            java.lang.String r4 = "1.0"
            if (r3 != 0) goto L_0x002e
        L_0x002a:
            r2.append(r4)
            goto L_0x0037
        L_0x002e:
            int r5 = r3.length()
            if (r5 <= 0) goto L_0x002a
            r2.append(r3)
        L_0x0037:
            java.lang.String r3 = "; "
            r2.append(r3)
            java.lang.String r4 = r1.getLanguage()
            if (r4 == 0) goto L_0x0059
            java.lang.String r4 = r4.toLowerCase()
            r2.append(r4)
            java.lang.String r1 = r1.getCountry()
            if (r1 == 0) goto L_0x005e
            java.lang.String r4 = "-"
            r2.append(r4)
            java.lang.String r1 = r1.toLowerCase()
            goto L_0x005b
        L_0x0059:
            java.lang.String r1 = "en"
        L_0x005b:
            r2.append(r1)
        L_0x005e:
            java.lang.String r1 = android.os.Build.VERSION.CODENAME
            java.lang.String r4 = "REL"
            boolean r1 = r4.equals(r1)
            if (r1 == 0) goto L_0x0088
            java.lang.String r1 = android.os.Build.MODEL
            java.lang.String r4 = new java.lang.String     // Catch:{ Exception -> 0x0075 }
            byte[] r0 = r1.getBytes(r0)     // Catch:{ Exception -> 0x0075 }
            r4.<init>(r0, r6)     // Catch:{ Exception -> 0x0075 }
            r1 = r4
            goto L_0x0076
        L_0x0075:
        L_0x0076:
            if (r1 != 0) goto L_0x007c
            r2.append(r3)
            goto L_0x0088
        L_0x007c:
            int r6 = r1.length()
            if (r6 <= 0) goto L_0x0088
            r2.append(r3)
            r2.append(r1)
        L_0x0088:
            java.lang.String r6 = android.os.Build.ID
            java.lang.String r0 = ""
            if (r6 != 0) goto L_0x0090
            r6 = r0
            goto L_0x0092
        L_0x0090:
            java.lang.String r6 = android.os.Build.ID
        L_0x0092:
            java.lang.String r1 = "[一-龥]"
            java.lang.String r6 = r6.replaceAll(r1, r0)
            java.lang.String r0 = " Build/"
            if (r6 != 0) goto L_0x00a5
            r2.append(r0)
            java.lang.String r6 = "00"
        L_0x00a1:
            r2.append(r6)
            goto L_0x00af
        L_0x00a5:
            int r1 = r6.length()
            if (r1 <= 0) goto L_0x00af
            r2.append(r0)
            goto L_0x00a1
        L_0x00af:
            r6 = 1
            java.lang.Object[] r6 = new java.lang.Object[r6]
            r0 = 0
            r6[r0] = r2
            java.lang.String r0 = "Mozilla/5.0 (Linux; U; Android %s) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 Mobile Safari/533.1"
            java.lang.String r6 = java.lang.String.format(r0, r6)
            b = r6
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsDownloader.b(android.content.Context):java.lang.String");
    }

    private static void b(JSONArray jSONArray) {
        if (TbsShareManager.getHostCorePathAppDefined() != null) {
            int a2 = o.a().a(TbsShareManager.getHostCorePathAppDefined());
            boolean z = false;
            int i2 = 0;
            while (true) {
                if (i2 >= jSONArray.length()) {
                    break;
                } else if (jSONArray.optInt(i2) == a2) {
                    z = true;
                    break;
                } else {
                    i2++;
                }
            }
            if (!z) {
                jSONArray.put(a2);
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x032c A[Catch:{ all -> 0x0396 }] */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x0352 A[Catch:{ all -> 0x0396 }] */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x039d  */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x03a0  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0241  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x0271  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x02ac  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean b(boolean r25, boolean r26, boolean r27, boolean r28) {
        /*
            r1 = r25
            r0 = r27
            java.lang.String r2 = "tbs_startdownload_code"
            java.lang.String r3 = "tbs_needdownload_code"
            if (r1 == 0) goto L_0x001f
            android.content.Context r4 = c
            com.tencent.smtt.sdk.TbsDownloadUpload r4 = com.tencent.smtt.sdk.TbsDownloadUpload.getInstance(r4)
            java.util.Map<java.lang.String, java.lang.Object> r5 = r4.a
            r6 = 144(0x90, float:2.02E-43)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r5.put(r3, r6)
        L_0x001b:
            r4.commit()
            goto L_0x0033
        L_0x001f:
            if (r0 != 0) goto L_0x0033
            android.content.Context r4 = c
            com.tencent.smtt.sdk.TbsDownloadUpload r4 = com.tencent.smtt.sdk.TbsDownloadUpload.getInstance(r4)
            java.util.Map<java.lang.String, java.lang.Object> r5 = r4.a
            r6 = 164(0xa4, float:2.3E-43)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r5.put(r2, r6)
            goto L_0x001b
        L_0x0033:
            java.util.Map<java.lang.String, java.lang.Object> r4 = com.tencent.smtt.sdk.QbSdk.n
            java.lang.String r5 = "TbsDownload"
            r6 = 0
            if (r4 == 0) goto L_0x0083
            java.util.Map<java.lang.String, java.lang.Object> r4 = com.tencent.smtt.sdk.QbSdk.n
            java.lang.String r7 = "SET_SENDREQUEST_AND_UPLOAD"
            boolean r4 = r4.containsKey(r7)
            if (r4 == 0) goto L_0x0083
            java.util.Map<java.lang.String, java.lang.Object> r4 = com.tencent.smtt.sdk.QbSdk.n
            java.lang.Object r4 = r4.get(r7)
            java.lang.String r7 = "false"
            boolean r4 = r4.equals(r7)
            if (r4 == 0) goto L_0x0083
            java.lang.String r4 = "[TbsDownloader.sendRequest] -- SET_SENDREQUEST_AND_UPLOAD is false"
            com.tencent.smtt.utils.TbsLog.i(r5, r4)
            if (r1 == 0) goto L_0x006e
            android.content.Context r0 = c
            com.tencent.smtt.sdk.TbsDownloadUpload r0 = com.tencent.smtt.sdk.TbsDownloadUpload.getInstance(r0)
            java.util.Map<java.lang.String, java.lang.Object> r1 = r0.a
            r2 = 145(0x91, float:2.03E-43)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r1.put(r3, r2)
        L_0x006a:
            r0.commit()
            goto L_0x0082
        L_0x006e:
            if (r0 != 0) goto L_0x0082
            android.content.Context r0 = c
            com.tencent.smtt.sdk.TbsDownloadUpload r0 = com.tencent.smtt.sdk.TbsDownloadUpload.getInstance(r0)
            java.util.Map<java.lang.String, java.lang.Object> r1 = r0.a
            r3 = 165(0xa5, float:2.31E-43)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r1.put(r2, r3)
            goto L_0x006a
        L_0x0082:
            return r6
        L_0x0083:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r7 = "[TbsDownloader.sendRequest]isQuery: "
            r4.append(r7)
            r4.append(r1)
            java.lang.String r7 = " forDecoupleCore is "
            r4.append(r7)
            r4.append(r0)
            java.lang.String r4 = r4.toString()
            com.tencent.smtt.utils.TbsLog.i(r5, r4)
            com.tencent.smtt.sdk.o r4 = com.tencent.smtt.sdk.o.a()
            android.content.Context r7 = c
            boolean r4 = r4.d(r7)
            if (r4 == 0) goto L_0x00dc
            java.lang.String r4 = "[TbsDownloader.sendRequest] -- isTbsLocalInstalled!"
            com.tencent.smtt.utils.TbsLog.i(r5, r4)
            if (r1 == 0) goto L_0x00c7
            android.content.Context r0 = c
            com.tencent.smtt.sdk.TbsDownloadUpload r0 = com.tencent.smtt.sdk.TbsDownloadUpload.getInstance(r0)
            java.util.Map<java.lang.String, java.lang.Object> r1 = r0.a
            r2 = 146(0x92, float:2.05E-43)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r1.put(r3, r2)
        L_0x00c3:
            r0.commit()
            goto L_0x00db
        L_0x00c7:
            if (r0 != 0) goto L_0x00db
            android.content.Context r0 = c
            com.tencent.smtt.sdk.TbsDownloadUpload r0 = com.tencent.smtt.sdk.TbsDownloadUpload.getInstance(r0)
            java.util.Map<java.lang.String, java.lang.Object> r1 = r0.a
            r3 = 166(0xa6, float:2.33E-43)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r1.put(r2, r3)
            goto L_0x00c3
        L_0x00db:
            return r6
        L_0x00dc:
            android.content.Context r4 = c
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4)
            java.io.File r7 = new java.io.File
            android.content.Context r8 = c
            r9 = 1
            java.lang.String r8 = com.tencent.smtt.utils.FileUtil.a((android.content.Context) r8, (int) r9)
            android.content.Context r10 = c
            boolean r10 = getOverSea(r10)
            java.lang.String r11 = "x5.oversea.tbs.org"
            if (r10 == 0) goto L_0x00f7
            r10 = r11
            goto L_0x00fb
        L_0x00f7:
            java.lang.String r10 = getBackupFileName(r6)
        L_0x00fb:
            r7.<init>(r8, r10)
            java.io.File r8 = new java.io.File
            android.content.Context r10 = c
            r12 = 2
            java.lang.String r10 = com.tencent.smtt.utils.FileUtil.a((android.content.Context) r10, (int) r12)
            android.content.Context r12 = c
            boolean r12 = getOverSea(r12)
            if (r12 == 0) goto L_0x0111
            r12 = r11
            goto L_0x0115
        L_0x0111:
            java.lang.String r12 = getBackupFileName(r6)
        L_0x0115:
            r8.<init>(r10, r12)
            java.io.File r10 = new java.io.File
            android.content.Context r12 = c
            r13 = 3
            java.lang.String r12 = com.tencent.smtt.utils.FileUtil.a((android.content.Context) r12, (int) r13)
            android.content.Context r13 = c
            boolean r13 = getOverSea(r13)
            if (r13 == 0) goto L_0x012b
            r13 = r11
            goto L_0x012f
        L_0x012b:
            java.lang.String r13 = getBackupFileName(r6)
        L_0x012f:
            r10.<init>(r12, r13)
            java.io.File r12 = new java.io.File
            android.content.Context r13 = c
            r14 = 4
            java.lang.String r13 = com.tencent.smtt.utils.FileUtil.a((android.content.Context) r13, (int) r14)
            android.content.Context r14 = c
            boolean r14 = getOverSea(r14)
            if (r14 == 0) goto L_0x0144
            goto L_0x0148
        L_0x0144:
            java.lang.String r11 = getBackupFileName(r6)
        L_0x0148:
            r12.<init>(r13, r11)
            boolean r11 = r12.exists()
            if (r11 != 0) goto L_0x016e
            boolean r11 = r10.exists()
            if (r11 == 0) goto L_0x015b
            r10.renameTo(r12)
            goto L_0x016e
        L_0x015b:
            boolean r10 = r8.exists()
            if (r10 == 0) goto L_0x0165
            r8.renameTo(r12)
            goto L_0x016e
        L_0x0165:
            boolean r8 = r7.exists()
            if (r8 == 0) goto L_0x016e
            r7.renameTo(r12)
        L_0x016e:
            java.lang.String r7 = e
            if (r7 != 0) goto L_0x0184
            java.lang.String r7 = com.tencent.smtt.utils.b.b()
            e = r7
            java.util.Map<java.lang.String, java.lang.Object> r7 = r4.mSyncMap
            java.lang.String r8 = e
            java.lang.String r10 = "device_cpuabi"
            r7.put(r10, r8)
            r4.commit()
        L_0x0184:
            java.lang.String r7 = e
            boolean r7 = android.text.TextUtils.isEmpty(r7)
            if (r7 != 0) goto L_0x01fa
            r7 = 0
            java.lang.String r8 = "i686|mips|x86_64"
            java.util.regex.Pattern r8 = java.util.regex.Pattern.compile(r8)     // Catch:{ Exception -> 0x019a }
            java.lang.String r10 = e     // Catch:{ Exception -> 0x019a }
            java.util.regex.Matcher r7 = r8.matcher(r10)     // Catch:{ Exception -> 0x019a }
            goto L_0x019b
        L_0x019a:
        L_0x019b:
            if (r7 == 0) goto L_0x01fa
            boolean r7 = r7.find()
            if (r7 == 0) goto L_0x01fa
            android.content.Context r7 = c
            boolean r7 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r7)
            r8 = -104(0xffffffffffffff98, float:NaN)
            r10 = -205(0xffffffffffffff33, float:NaN)
            if (r7 == 0) goto L_0x01ef
            java.lang.String r7 = "don't support x86 devices,skip send request"
            com.tencent.smtt.utils.TbsLog.e(r5, r7)
            android.content.Context r7 = c
            com.tencent.smtt.sdk.TbsLogReport r7 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r7)
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r7 = r7.tbsLogInfo()
            if (r1 == 0) goto L_0x01c7
            r4.setDownloadInterruptCode(r8)
            r7.setErrorCode(r8)
            goto L_0x01cd
        L_0x01c7:
            r4.setDownloadInterruptCode(r10)
            r7.setErrorCode(r10)
        L_0x01cd:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r10 = "mycpu is "
            r8.append(r10)
            java.lang.String r10 = e
            r8.append(r10)
            java.lang.String r8 = r8.toString()
            r7.setFailDetail((java.lang.String) r8)
            android.content.Context r8 = c
            com.tencent.smtt.sdk.TbsLogReport r8 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r8)
            com.tencent.smtt.sdk.TbsLogReport$EventType r10 = com.tencent.smtt.sdk.TbsLogReport.EventType.TYPE_DOWNLOAD
            r8.eventReport(r10, r7)
            goto L_0x01f8
        L_0x01ef:
            if (r1 == 0) goto L_0x01f5
            r4.setDownloadInterruptCode(r8)
            goto L_0x01f8
        L_0x01f5:
            r4.setDownloadInterruptCode(r10)
        L_0x01f8:
            r7 = 1
            goto L_0x01fb
        L_0x01fa:
            r7 = 0
        L_0x01fb:
            java.util.Map<java.lang.String, java.lang.Object> r8 = r4.mSyncMap
            android.content.Context r10 = c
            java.lang.String r10 = com.tencent.smtt.utils.b.c(r10)
            java.lang.String r11 = "app_versionname"
            r8.put(r11, r10)
            java.util.Map<java.lang.String, java.lang.Object> r8 = r4.mSyncMap
            android.content.Context r10 = c
            int r10 = com.tencent.smtt.utils.b.d(r10)
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            java.lang.String r12 = "app_versioncode"
            r8.put(r12, r10)
            r4.commit()
            org.json.JSONObject r8 = a((boolean) r25, (boolean) r26, (boolean) r27)
            r10 = -1
            java.lang.String r13 = "TBSV"
            int r13 = r8.getInt(r13)     // Catch:{ Exception -> 0x0228 }
            goto L_0x0229
        L_0x0228:
            r13 = -1
        L_0x0229:
            if (r7 != 0) goto L_0x0233
            if (r13 == r10) goto L_0x022e
            goto L_0x0233
        L_0x022e:
            r20 = r8
        L_0x0230:
            r6 = -1
            goto L_0x02d9
        L_0x0233:
            long r14 = java.lang.System.currentTimeMillis()
            android.content.Context r16 = c
            boolean r16 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r16)
            java.lang.String r9 = "request_fail"
            if (r16 == 0) goto L_0x0271
            android.content.SharedPreferences r10 = r4.mPreferences
            r17 = r7
            r6 = 0
            long r18 = r10.getLong(r9, r6)
            android.content.SharedPreferences r10 = r4.mPreferences
            r20 = r8
            java.lang.String r8 = "count_request_fail_in_24hours"
            long r6 = r10.getLong(r8, r6)
            long r18 = r14 - r18
            long r21 = r4.getRetryInterval()
            r23 = 1000(0x3e8, double:4.94E-321)
            long r21 = r21 * r23
            r23 = 1
            int r10 = (r18 > r21 ? 1 : (r18 == r21 ? 0 : -1))
            if (r10 >= 0) goto L_0x0267
            long r23 = r6 + r23
        L_0x0267:
            java.util.Map<java.lang.String, java.lang.Object> r6 = r4.mSyncMap
            java.lang.Long r7 = java.lang.Long.valueOf(r23)
            r6.put(r8, r7)
            goto L_0x0275
        L_0x0271:
            r17 = r7
            r20 = r8
        L_0x0275:
            java.util.Map<java.lang.String, java.lang.Object> r6 = r4.mSyncMap
            java.lang.Long r7 = java.lang.Long.valueOf(r14)
            r6.put(r9, r7)
            java.util.Map<java.lang.String, java.lang.Object> r6 = r4.mSyncMap
            android.content.Context r7 = c
            java.lang.String r7 = com.tencent.smtt.utils.b.c(r7)
            r6.put(r11, r7)
            java.util.Map<java.lang.String, java.lang.Object> r6 = r4.mSyncMap
            android.content.Context r7 = c
            int r7 = com.tencent.smtt.utils.b.d(r7)
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            r6.put(r12, r7)
            java.util.Map<java.lang.String, java.lang.Object> r6 = r4.mSyncMap
            android.content.Context r7 = c
            java.lang.String r8 = "com.tencent.mm.BuildInfo.CLIENT_VERSION"
            java.lang.String r7 = com.tencent.smtt.utils.b.a((android.content.Context) r7, (java.lang.String) r8)
            java.lang.String r8 = "app_metadata"
            r6.put(r8, r7)
            r4.commit()
            if (r17 == 0) goto L_0x0230
            if (r1 == 0) goto L_0x02c3
            android.content.Context r0 = c
            com.tencent.smtt.sdk.TbsDownloadUpload r0 = com.tencent.smtt.sdk.TbsDownloadUpload.getInstance(r0)
            java.util.Map<java.lang.String, java.lang.Object> r1 = r0.a
            r2 = 147(0x93, float:2.06E-43)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r1.put(r3, r2)
        L_0x02bf:
            r0.commit()
            goto L_0x02d7
        L_0x02c3:
            if (r0 != 0) goto L_0x02d7
            android.content.Context r0 = c
            com.tencent.smtt.sdk.TbsDownloadUpload r0 = com.tencent.smtt.sdk.TbsDownloadUpload.getInstance(r0)
            java.util.Map<java.lang.String, java.lang.Object> r1 = r0.a
            r3 = 167(0xa7, float:2.34E-43)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r1.put(r2, r3)
            goto L_0x02bf
        L_0x02d7:
            r1 = 0
            return r1
        L_0x02d9:
            if (r13 != r6) goto L_0x030c
            if (r0 == 0) goto L_0x02de
            goto L_0x030c
        L_0x02de:
            if (r1 == 0) goto L_0x02f5
            android.content.Context r0 = c
            com.tencent.smtt.sdk.TbsDownloadUpload r0 = com.tencent.smtt.sdk.TbsDownloadUpload.getInstance(r0)
            java.util.Map<java.lang.String, java.lang.Object> r1 = r0.a
            r2 = 149(0x95, float:2.09E-43)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r1.put(r3, r2)
        L_0x02f1:
            r0.commit()
            goto L_0x0309
        L_0x02f5:
            if (r0 != 0) goto L_0x0309
            android.content.Context r0 = c
            com.tencent.smtt.sdk.TbsDownloadUpload r0 = com.tencent.smtt.sdk.TbsDownloadUpload.getInstance(r0)
            java.util.Map<java.lang.String, java.lang.Object> r1 = r0.a
            r3 = 169(0xa9, float:2.37E-43)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r1.put(r2, r3)
            goto L_0x02f1
        L_0x0309:
            r3 = 0
            goto L_0x03a5
        L_0x030c:
            android.content.Context r6 = c     // Catch:{ all -> 0x0396 }
            com.tencent.smtt.utils.m r6 = com.tencent.smtt.utils.m.a(r6)     // Catch:{ all -> 0x0396 }
            java.lang.String r6 = r6.d()     // Catch:{ all -> 0x0396 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x0396 }
            r7.<init>()     // Catch:{ all -> 0x0396 }
            java.lang.String r8 = "[TbsDownloader.sendRequest] postUrl="
            r7.append(r8)     // Catch:{ all -> 0x0396 }
            r7.append(r6)     // Catch:{ all -> 0x0396 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x0396 }
            com.tencent.smtt.utils.TbsLog.i(r5, r7)     // Catch:{ all -> 0x0396 }
            if (r1 == 0) goto L_0x0352
            android.content.Context r0 = c     // Catch:{ all -> 0x0396 }
            com.tencent.smtt.sdk.TbsDownloadUpload r0 = com.tencent.smtt.sdk.TbsDownloadUpload.getInstance(r0)     // Catch:{ all -> 0x0396 }
            java.util.Map<java.lang.String, java.lang.Object> r2 = r0.a     // Catch:{ all -> 0x0396 }
            r7 = 148(0x94, float:2.07E-43)
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ all -> 0x0396 }
            r2.put(r3, r7)     // Catch:{ all -> 0x0396 }
            java.util.Map<java.lang.String, java.lang.Object> r2 = r0.a     // Catch:{ all -> 0x0396 }
            java.lang.String r3 = "tbs_needdownload_sent"
            r7 = 1
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ all -> 0x0396 }
            r2.put(r3, r7)     // Catch:{ all -> 0x0396 }
            r0.commit()     // Catch:{ all -> 0x0396 }
            java.lang.String r0 = "sendRequest query 148"
        L_0x034e:
            com.tencent.smtt.utils.TbsLog.i(r5, r0)     // Catch:{ all -> 0x0396 }
            goto L_0x0377
        L_0x0352:
            if (r0 != 0) goto L_0x0377
            android.content.Context r0 = c     // Catch:{ all -> 0x0396 }
            com.tencent.smtt.sdk.TbsDownloadUpload r0 = com.tencent.smtt.sdk.TbsDownloadUpload.getInstance(r0)     // Catch:{ all -> 0x0396 }
            java.util.Map<java.lang.String, java.lang.Object> r3 = r0.a     // Catch:{ all -> 0x0396 }
            r7 = 168(0xa8, float:2.35E-43)
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ all -> 0x0396 }
            r3.put(r2, r7)     // Catch:{ all -> 0x0396 }
            java.util.Map<java.lang.String, java.lang.Object> r2 = r0.a     // Catch:{ all -> 0x0396 }
            java.lang.String r3 = "tbs_startdownload_sent"
            r7 = 1
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ all -> 0x0396 }
            r2.put(r3, r7)     // Catch:{ all -> 0x0396 }
            r0.commit()     // Catch:{ all -> 0x0396 }
            java.lang.String r0 = "sendRequest download 168"
            goto L_0x034e
        L_0x0377:
            java.lang.String r0 = r20.toString()     // Catch:{ all -> 0x0396 }
            java.lang.String r2 = "utf-8"
            byte[] r0 = r0.getBytes(r2)     // Catch:{ all -> 0x0396 }
            com.tencent.smtt.sdk.TbsDownloader$3 r2 = new com.tencent.smtt.sdk.TbsDownloader$3     // Catch:{ all -> 0x0396 }
            r2.<init>(r4, r1)     // Catch:{ all -> 0x0396 }
            r3 = 0
            java.lang.String r0 = com.tencent.smtt.utils.f.a(r6, r0, r2, r3)     // Catch:{ all -> 0x0394 }
            r2 = r26
            r5 = r28
            boolean r6 = a(r0, r13, r1, r2, r5)     // Catch:{ all -> 0x0394 }
            goto L_0x03a6
        L_0x0394:
            r0 = move-exception
            goto L_0x0398
        L_0x0396:
            r0 = move-exception
            r3 = 0
        L_0x0398:
            r0.printStackTrace()
            if (r1 == 0) goto L_0x03a0
            r0 = -106(0xffffffffffffff96, float:NaN)
            goto L_0x03a2
        L_0x03a0:
            r0 = -206(0xffffffffffffff32, float:NaN)
        L_0x03a2:
            r4.setDownloadInterruptCode(r0)
        L_0x03a5:
            r6 = 0
        L_0x03a6:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsDownloader.b(boolean, boolean, boolean, boolean):boolean");
    }

    static void c(Context context) {
        TbsDownloadConfig.getInstance(context).clear();
        TbsLogReport.getInstance(context).clear();
        l.c(context);
        (Build.VERSION.SDK_INT >= 11 ? context.getSharedPreferences("tbs_extension_config", 4) : context.getSharedPreferences("tbs_extension_config", 0)).edit().clear().commit();
        (Build.VERSION.SDK_INT >= 11 ? context.getSharedPreferences("tbs_preloadx5_check_cfg_file", 4) : context.getSharedPreferences("tbs_preloadx5_check_cfg_file", 0)).edit().clear().commit();
    }

    private static void c(JSONArray jSONArray) {
        StringBuilder sb;
        boolean z;
        if (!TbsPVConfig.getInstance(c).isDisableHostBackupCore()) {
            for (String str : f()) {
                int backupCoreVersion = TbsShareManager.getBackupCoreVersion(c, str);
                boolean z2 = true;
                if (backupCoreVersion > 0) {
                    Context packageContext = TbsShareManager.getPackageContext(c, str, false);
                    if (packageContext != null && !o.a().g(packageContext)) {
                        sb = new StringBuilder();
                        sb.append("host check failed,packageName = ");
                        sb.append(str);
                        TbsLog.e(LOGTAG, sb.toString());
                    } else if (a(c, backupCoreVersion)) {
                        TbsLog.i(LOGTAG, "add addBackupVersionToJsonData,version+" + backupCoreVersion + " is in black list");
                    } else {
                        int i2 = 0;
                        while (true) {
                            if (i2 >= jSONArray.length()) {
                                z = false;
                                break;
                            } else if (jSONArray.optInt(i2) == backupCoreVersion) {
                                z = true;
                                break;
                            } else {
                                i2++;
                            }
                        }
                        if (!z) {
                            jSONArray.put(backupCoreVersion);
                        }
                    }
                }
                int backupDecoupleCoreVersion = TbsShareManager.getBackupDecoupleCoreVersion(c, str);
                if (backupDecoupleCoreVersion > 0) {
                    Context packageContext2 = TbsShareManager.getPackageContext(c, str, false);
                    if (packageContext2 == null || o.a().g(packageContext2)) {
                        int i3 = 0;
                        while (true) {
                            if (i3 >= jSONArray.length()) {
                                z2 = false;
                                break;
                            } else if (jSONArray.optInt(i3) == backupDecoupleCoreVersion) {
                                break;
                            } else {
                                i3++;
                            }
                        }
                        if (!z2) {
                            jSONArray.put(backupDecoupleCoreVersion);
                        }
                    } else {
                        sb = new StringBuilder();
                        sb.append("host check failed,packageName = ");
                        sb.append(str);
                        TbsLog.e(LOGTAG, sb.toString());
                    }
                }
            }
        }
    }

    private static boolean c() {
        try {
            for (String sharedTbsCoreVersion : TbsShareManager.getCoreProviderAppList()) {
                if (TbsShareManager.getSharedTbsCoreVersion(c, sharedTbsCoreVersion) > 0) {
                    return true;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return false;
    }

    private static synchronized void d() {
        synchronized (TbsDownloader.class) {
            if (h == null) {
                h = n.a();
                try {
                    g = new l(c);
                    d = new Handler(h.getLooper()) {
                        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: java.nio.channels.FileLock} */
                        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: java.nio.channels.FileLock} */
                        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v0, resolved type: java.io.FileOutputStream} */
                        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v10, resolved type: java.nio.channels.FileLock} */
                        /* JADX WARNING: type inference failed for: r6v6, types: [java.nio.channels.FileLock] */
                        /* JADX WARNING: Multi-variable type inference failed */
                        /* JADX WARNING: Unknown variable types count: 1 */
                        /* Code decompiled incorrectly, please refer to instructions dump. */
                        public void handleMessage(android.os.Message r15) {
                            /*
                                r14 = this;
                                int r0 = r15.what
                                r1 = 108(0x6c, float:1.51E-43)
                                java.lang.String r2 = "tbs_download_version"
                                java.lang.String r3 = "TbsDownload"
                                r4 = 1
                                r5 = 0
                                if (r0 == r1) goto L_0x0157
                                r6 = 109(0x6d, float:1.53E-43)
                                if (r0 == r6) goto L_0x0148
                                switch(r0) {
                                    case 100: goto L_0x0096;
                                    case 101: goto L_0x0157;
                                    case 102: goto L_0x004a;
                                    case 103: goto L_0x0027;
                                    case 104: goto L_0x0015;
                                    default: goto L_0x0013;
                                }
                            L_0x0013:
                                goto L_0x0267
                            L_0x0015:
                                java.lang.String r15 = "[TbsDownloader.handleMessage] MSG_UPLOAD_TBSLOG"
                                com.tencent.smtt.utils.TbsLog.i(r3, r15)
                                android.content.Context r15 = com.tencent.smtt.sdk.TbsDownloader.c
                                com.tencent.smtt.sdk.TbsLogReport r15 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r15)
                                r15.reportTbsLog()
                                goto L_0x0267
                            L_0x0027:
                                java.lang.String r0 = "[TbsDownloader.handleMessage] MSG_CONTINUEINSTALL_TBSCORE"
                                com.tencent.smtt.utils.TbsLog.i(r3, r0)
                                int r0 = r15.arg1
                                if (r0 != 0) goto L_0x003d
                                com.tencent.smtt.sdk.o r0 = com.tencent.smtt.sdk.o.a()
                                java.lang.Object r15 = r15.obj
                                android.content.Context r15 = (android.content.Context) r15
                                r0.a((android.content.Context) r15, (boolean) r4)
                                goto L_0x0267
                            L_0x003d:
                                com.tencent.smtt.sdk.o r0 = com.tencent.smtt.sdk.o.a()
                                java.lang.Object r15 = r15.obj
                                android.content.Context r15 = (android.content.Context) r15
                                r0.a((android.content.Context) r15, (boolean) r5)
                                goto L_0x0267
                            L_0x004a:
                                java.lang.String r15 = "[TbsDownloader.handleMessage] MSG_REPORT_DOWNLOAD_STAT"
                                com.tencent.smtt.utils.TbsLog.i(r3, r15)
                                android.content.Context r15 = com.tencent.smtt.sdk.TbsDownloader.c
                                boolean r15 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r15)
                                if (r15 == 0) goto L_0x0062
                                android.content.Context r15 = com.tencent.smtt.sdk.TbsDownloader.c
                                int r15 = com.tencent.smtt.sdk.TbsShareManager.a((android.content.Context) r15, (boolean) r5)
                                goto L_0x006e
                            L_0x0062:
                                com.tencent.smtt.sdk.o r15 = com.tencent.smtt.sdk.o.a()
                                android.content.Context r0 = com.tencent.smtt.sdk.TbsDownloader.c
                                int r15 = r15.n(r0)
                            L_0x006e:
                                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                                r0.<init>()
                                java.lang.String r1 = "[TbsDownloader.handleMessage] localTbsVersion="
                                r0.append(r1)
                                r0.append(r15)
                                java.lang.String r0 = r0.toString()
                                com.tencent.smtt.utils.TbsLog.i(r3, r0)
                                com.tencent.smtt.sdk.l r0 = com.tencent.smtt.sdk.TbsDownloader.g
                                r0.a((int) r15)
                                android.content.Context r15 = com.tencent.smtt.sdk.TbsDownloader.c
                                com.tencent.smtt.sdk.TbsLogReport r15 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r15)
                                r15.dailyReport()
                                goto L_0x0267
                            L_0x0096:
                                int r0 = r15.arg1
                                if (r0 != r4) goto L_0x009c
                                r0 = 1
                                goto L_0x009d
                            L_0x009c:
                                r0 = 0
                            L_0x009d:
                                int r1 = r15.arg2
                                if (r1 != r4) goto L_0x00a3
                                r1 = 1
                                goto L_0x00a4
                            L_0x00a3:
                                r1 = 0
                            L_0x00a4:
                                boolean r1 = com.tencent.smtt.sdk.TbsDownloader.b(r4, r5, r5, r1)
                                java.lang.Object r4 = r15.obj
                                if (r4 == 0) goto L_0x0133
                                java.lang.Object r4 = r15.obj
                                boolean r4 = r4 instanceof com.tencent.smtt.sdk.TbsDownloader.TbsDownloaderCallback
                                if (r4 == 0) goto L_0x0133
                                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                                r4.<init>()
                                java.lang.String r6 = "needDownload-onNeedDownloadFinish needStartDownload="
                                r4.append(r6)
                                r4.append(r1)
                                java.lang.String r4 = r4.toString()
                                com.tencent.smtt.utils.TbsLog.i(r3, r4)
                                android.content.Context r4 = com.tencent.smtt.sdk.TbsDownloader.c
                                if (r4 == 0) goto L_0x00f3
                                android.content.Context r4 = com.tencent.smtt.sdk.TbsDownloader.c
                                android.content.Context r4 = r4.getApplicationContext()
                                if (r4 == 0) goto L_0x00f3
                                android.content.Context r4 = com.tencent.smtt.sdk.TbsDownloader.c
                                android.content.Context r4 = r4.getApplicationContext()
                                android.content.pm.ApplicationInfo r4 = r4.getApplicationInfo()
                                if (r4 == 0) goto L_0x00f3
                                android.content.Context r4 = com.tencent.smtt.sdk.TbsDownloader.c
                                android.content.Context r4 = r4.getApplicationContext()
                                android.content.pm.ApplicationInfo r4 = r4.getApplicationInfo()
                                java.lang.String r4 = r4.packageName
                                goto L_0x00f5
                            L_0x00f3:
                                java.lang.String r4 = ""
                            L_0x00f5:
                                if (r1 == 0) goto L_0x011e
                                if (r0 == 0) goto L_0x00fa
                                goto L_0x011e
                            L_0x00fa:
                                java.lang.String r0 = "com.tencent.mm"
                                boolean r0 = r0.equals(r4)
                                if (r0 != 0) goto L_0x010a
                                java.lang.String r0 = "com.tencent.mobileqq"
                                boolean r0 = r0.equals(r4)
                                if (r0 == 0) goto L_0x0133
                            L_0x010a:
                                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                                r0.<init>()
                                java.lang.String r4 = "needDownload-onNeedDownloadFinish in mm or QQ callback needStartDownload = "
                                r0.append(r4)
                                r0.append(r1)
                                java.lang.String r0 = r0.toString()
                                com.tencent.smtt.utils.TbsLog.i(r3, r0)
                            L_0x011e:
                                java.lang.Object r15 = r15.obj
                                com.tencent.smtt.sdk.TbsDownloader$TbsDownloaderCallback r15 = (com.tencent.smtt.sdk.TbsDownloader.TbsDownloaderCallback) r15
                                android.content.Context r0 = com.tencent.smtt.sdk.TbsDownloader.c
                                com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r0)
                                android.content.SharedPreferences r0 = r0.mPreferences
                                int r0 = r0.getInt(r2, r5)
                                r15.onNeedDownloadFinish(r1, r0)
                            L_0x0133:
                                android.content.Context r15 = com.tencent.smtt.sdk.TbsDownloader.c
                                boolean r15 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r15)
                                if (r15 == 0) goto L_0x0267
                                if (r1 == 0) goto L_0x0267
                                android.content.Context r15 = com.tencent.smtt.sdk.TbsDownloader.c
                                com.tencent.smtt.sdk.TbsDownloader.startDownload(r15)
                                goto L_0x0267
                            L_0x0148:
                                com.tencent.smtt.sdk.l r15 = com.tencent.smtt.sdk.TbsDownloader.g
                                if (r15 == 0) goto L_0x0267
                                com.tencent.smtt.sdk.l r15 = com.tencent.smtt.sdk.TbsDownloader.g
                                r15.f()
                                goto L_0x0267
                            L_0x0157:
                                android.content.Context r0 = com.tencent.smtt.sdk.TbsDownloader.c
                                int r0 = com.tencent.smtt.utils.Apn.getApnType(r0)
                                r6 = 3
                                if (r0 == r6) goto L_0x016e
                                boolean r0 = com.tencent.smtt.sdk.QbSdk.getDownloadWithoutWifi()
                                if (r0 != 0) goto L_0x016e
                                java.lang.String r15 = "not wifi,no need send request"
                            L_0x016a:
                                com.tencent.smtt.utils.TbsLog.i(r3, r15)
                                return
                            L_0x016e:
                                android.content.Context r0 = com.tencent.smtt.sdk.TbsDownloader.c
                                boolean r0 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r0)
                                r6 = 0
                                if (r0 != 0) goto L_0x01eb
                                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                                r0.<init>()
                                java.lang.String r7 = "tbs_download_lock_file"
                                r0.append(r7)
                                android.content.Context r7 = com.tencent.smtt.sdk.TbsDownloader.c
                                com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)
                                android.content.SharedPreferences r7 = r7.mPreferences
                                int r7 = r7.getInt(r2, r5)
                                r0.append(r7)
                                java.lang.String r7 = ".txt"
                                r0.append(r7)
                                java.lang.String r0 = r0.toString()
                                android.content.Context r7 = com.tencent.smtt.sdk.TbsDownloader.c
                                java.io.FileOutputStream r0 = com.tencent.smtt.utils.FileUtil.b(r7, r5, r0)
                                if (r0 == 0) goto L_0x01cd
                                android.content.Context r6 = com.tencent.smtt.sdk.TbsDownloader.c
                                java.nio.channels.FileLock r6 = com.tencent.smtt.utils.FileUtil.a((android.content.Context) r6, (java.io.FileOutputStream) r0)
                                if (r6 != 0) goto L_0x01e7
                                com.tencent.smtt.sdk.TbsListener r15 = com.tencent.smtt.sdk.QbSdk.m
                                r0 = 177(0xb1, float:2.48E-43)
                                r15.onDownloadFinish(r0)
                                java.lang.String r15 = "file lock locked,wx or qq is downloading"
                                com.tencent.smtt.utils.TbsLog.i(r3, r15)
                                android.content.Context r15 = com.tencent.smtt.sdk.TbsDownloader.c
                                com.tencent.smtt.sdk.TbsDownloadConfig r15 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r15)
                                r0 = -203(0xffffffffffffff35, float:NaN)
                                r15.setDownloadInterruptCode(r0)
                                java.lang.String r15 = "MSG_START_DOWNLOAD_DECOUPLECORE return #1"
                                goto L_0x016a
                            L_0x01cd:
                                android.content.Context r7 = com.tencent.smtt.sdk.TbsDownloader.c
                                boolean r7 = com.tencent.smtt.utils.FileUtil.a((android.content.Context) r7)
                                if (r7 == 0) goto L_0x01e7
                                android.content.Context r15 = com.tencent.smtt.sdk.TbsDownloader.c
                                com.tencent.smtt.sdk.TbsDownloadConfig r15 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r15)
                                r0 = -204(0xffffffffffffff34, float:NaN)
                                r15.setDownloadInterruptCode(r0)
                                java.lang.String r15 = "MSG_START_DOWNLOAD_DECOUPLECORE return #2"
                                goto L_0x016a
                            L_0x01e7:
                                r13 = r6
                                r6 = r0
                                r0 = r13
                                goto L_0x01ec
                            L_0x01eb:
                                r0 = r6
                            L_0x01ec:
                                int r7 = r15.arg1
                                if (r7 != r4) goto L_0x01f2
                                r7 = 1
                                goto L_0x01f3
                            L_0x01f2:
                                r7 = 0
                            L_0x01f3:
                                android.content.Context r8 = com.tencent.smtt.sdk.TbsDownloader.c
                                com.tencent.smtt.sdk.TbsDownloadConfig r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8)
                                int r9 = r15.what
                                if (r1 != r9) goto L_0x0201
                                r9 = 1
                                goto L_0x0202
                            L_0x0201:
                                r9 = 0
                            L_0x0202:
                                boolean r9 = com.tencent.smtt.sdk.TbsDownloader.b(r5, r7, r9, r4)
                                r10 = 110(0x6e, float:1.54E-43)
                                if (r9 == 0) goto L_0x025a
                                if (r7 == 0) goto L_0x0235
                                com.tencent.smtt.sdk.o r9 = com.tencent.smtt.sdk.o.a()
                                android.content.Context r11 = com.tencent.smtt.sdk.TbsDownloader.c
                                android.content.Context r12 = com.tencent.smtt.sdk.TbsDownloader.c
                                com.tencent.smtt.sdk.TbsDownloadConfig r12 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r12)
                                android.content.SharedPreferences r12 = r12.mPreferences
                                int r2 = r12.getInt(r2, r5)
                                boolean r2 = r9.b((android.content.Context) r11, (int) r2)
                                if (r2 == 0) goto L_0x0235
                                com.tencent.smtt.sdk.TbsListener r15 = com.tencent.smtt.sdk.QbSdk.m
                                r1 = 122(0x7a, float:1.71E-43)
                                r15.onDownloadFinish(r1)
                                r15 = -213(0xffffffffffffff2b, float:NaN)
                                r8.setDownloadInterruptCode(r15)
                                goto L_0x025f
                            L_0x0235:
                                android.content.SharedPreferences r2 = r8.mPreferences
                                java.lang.String r8 = "tbs_needdownload"
                                boolean r2 = r2.getBoolean(r8, r5)
                                if (r2 == 0) goto L_0x025a
                                android.content.Context r2 = com.tencent.smtt.sdk.TbsDownloader.c
                                com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
                                r8 = -215(0xffffffffffffff29, float:NaN)
                                r2.setDownloadInterruptCode(r8)
                                com.tencent.smtt.sdk.l r2 = com.tencent.smtt.sdk.TbsDownloader.g
                                int r15 = r15.what
                                if (r1 != r15) goto L_0x0255
                                goto L_0x0256
                            L_0x0255:
                                r4 = 0
                            L_0x0256:
                                r2.b(r7, r4)
                                goto L_0x025f
                            L_0x025a:
                                com.tencent.smtt.sdk.TbsListener r15 = com.tencent.smtt.sdk.QbSdk.m
                                r15.onDownloadFinish(r10)
                            L_0x025f:
                                java.lang.String r15 = "------freeFileLock called :"
                                com.tencent.smtt.utils.TbsLog.i(r3, r15)
                                com.tencent.smtt.utils.FileUtil.a((java.nio.channels.FileLock) r0, (java.io.FileOutputStream) r6)
                            L_0x0267:
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsDownloader.AnonymousClass2.handleMessage(android.os.Message):void");
                        }
                    };
                } catch (Exception unused) {
                    i = true;
                    TbsLog.e(LOGTAG, "TbsApkDownloader init has Exception");
                }
            }
        }
    }

    private static boolean e() {
        try {
            return TbsDownloadConfig.getInstance(c).mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_LAST_THIRDAPP_SENDREQUEST_COREVERSION, "").equals(g().toString());
        } catch (Exception unused) {
            return false;
        }
    }

    private static String[] f() {
        if (QbSdk.getOnlyDownload()) {
            return new String[]{c.getApplicationContext().getPackageName()};
        }
        String[] coreProviderAppList = TbsShareManager.getCoreProviderAppList();
        String packageName = c.getApplicationContext().getPackageName();
        if (!packageName.equals(TbsShareManager.f(c))) {
            return coreProviderAppList;
        }
        int length = coreProviderAppList.length;
        String[] strArr = new String[(length + 1)];
        System.arraycopy(coreProviderAppList, 0, strArr, 0, length);
        strArr[length] = packageName;
        return strArr;
    }

    private static JSONArray g() {
        if (!TbsShareManager.isThirdPartyApp(c)) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        a(jSONArray);
        c(jSONArray);
        b(jSONArray);
        return jSONArray;
    }

    public static String getBackupFileName(boolean z) {
        return z ? b.c() ? "x5.tbs.decouple.64" : "x5.tbs.decouple" : b.c() ? "x5.tbs.org.64" : "x5.tbs.org";
    }

    public static int getCoreShareDecoupleCoreVersion() {
        return o.a().i(c);
    }

    public static int getCoreShareDecoupleCoreVersionByContext(Context context) {
        return o.a().i(context);
    }

    public static synchronized boolean getOverSea(Context context) {
        boolean z;
        synchronized (TbsDownloader.class) {
            if (!k) {
                k = true;
                TbsDownloadConfig instance = TbsDownloadConfig.getInstance(context);
                if (instance.mPreferences.contains(TbsDownloadConfig.TbsConfigKey.KEY_IS_OVERSEA)) {
                    j = instance.mPreferences.getBoolean(TbsDownloadConfig.TbsConfigKey.KEY_IS_OVERSEA, false);
                    TbsLog.i(LOGTAG, "[TbsDownloader.getOverSea]  first called. sOverSea = " + j);
                }
                TbsLog.i(LOGTAG, "[TbsDownloader.getOverSea]  sOverSea = " + j);
            }
            z = j;
        }
        return z;
    }

    public static long getRetryIntervalInSeconds() {
        return l;
    }

    public static HandlerThread getsTbsHandlerThread() {
        return h;
    }

    private static boolean h() {
        int i2;
        TbsDownloadConfig instance = TbsDownloadConfig.getInstance(c);
        if (instance.mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOAD_SUCCESS_RETRYTIMES, 0) >= instance.getDownloadSuccessMaxRetrytimes()) {
            TbsLog.i(LOGTAG, "[TbsDownloader.needStartDownload] out of success retrytimes", true);
            i2 = -115;
        } else if (instance.mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOAD_FAILED_RETRYTIMES, 0) >= instance.getDownloadFailedMaxRetrytimes()) {
            TbsLog.i(LOGTAG, "[TbsDownloader.needStartDownload] out of failed retrytimes", true);
            i2 = -116;
        } else if (!FileUtil.b(c)) {
            TbsLog.i(LOGTAG, "[TbsDownloader.needStartDownload] local rom freespace limit", true);
            i2 = -117;
        } else {
            if (System.currentTimeMillis() - instance.mPreferences.getLong(TbsDownloadConfig.TbsConfigKey.KEY_TBSDOWNLOAD_STARTTIME, 0) <= DateUtils.MILLIS_PER_DAY) {
                long j2 = instance.mPreferences.getLong(TbsDownloadConfig.TbsConfigKey.KEY_TBSDOWNLOAD_FLOW, 0);
                TbsLog.i(LOGTAG, "[TbsDownloader.needStartDownload] downloadFlow=" + j2);
                if (j2 >= instance.getDownloadMaxflow()) {
                    TbsLog.i(LOGTAG, "[TbsDownloader.needStartDownload] failed because you exceeded max flow!", true);
                    i2 = -120;
                }
            }
            return true;
        }
        instance.setDownloadInterruptCode(i2);
        return false;
    }

    public static boolean isDownloadForeground() {
        l lVar = g;
        return lVar != null && lVar.d();
    }

    public static synchronized boolean isDownloading() {
        boolean z;
        synchronized (TbsDownloader.class) {
            TbsLog.i(LOGTAG, "[TbsDownloader.isDownloading] is " + a);
            z = a;
        }
        return z;
    }

    public static boolean needDownload(Context context, boolean z) {
        return needDownload(context, z, false, true, (TbsDownloaderCallback) null);
    }

    public static boolean needDownload(Context context, boolean z, boolean z2, TbsDownloaderCallback tbsDownloaderCallback) {
        return needDownload(context, z, z2, true, tbsDownloaderCallback);
    }

    public static boolean needDownload(Context context, boolean z, boolean z2, boolean z3, TbsDownloaderCallback tbsDownloaderCallback) {
        boolean z4;
        boolean z5;
        int i2;
        TbsLog.initIfNeed(context);
        TbsLog.i(LOGTAG, "needDownload,process=" + QbSdk.getCurrentProcessName(context) + "stack=" + Log.getStackTraceString(new Throwable()));
        TbsDownloadUpload.clear();
        TbsDownloadUpload instance = TbsDownloadUpload.getInstance(context);
        instance.a.put(TbsDownloadUpload.TbsUploadKey.KEY_NEEDDOWNLOAD_CODE, Integer.valueOf(TbsListener.ErrorCode.NEEDDOWNLOAD_1));
        instance.commit();
        o.a().b(context, f.a == 0);
        int b2 = o.a().b(context);
        TbsLog.i(LOGTAG, "[TbsDownloader.needDownload],renameRet=" + b2);
        if (b2 != 0) {
            TbsLogReport.TbsLogInfo tbsLogInfo = TbsLogReport.getInstance(context).tbsLogInfo();
            tbsLogInfo.a = TbsListener.ErrorCode.RENAME_NO_NEED_SENDREQUEST;
            tbsLogInfo.setFailDetail("code=2" + b2);
            TbsLogReport.getInstance(context).eventReport(TbsLogReport.EventType.TYPE_SDK_REPORT_INFO, tbsLogInfo);
        }
        if (b2 < 0) {
            TbsLog.i(LOGTAG, "[TbsDownloader.needDownload],needReNameFile=" + b2);
            return false;
        }
        TbsLog.i(LOGTAG, "[TbsDownloader.needDownload] oversea=" + z + ",isDownloadForeground=" + z2);
        if (o.b) {
            if (tbsDownloaderCallback != null) {
                tbsDownloaderCallback.onNeedDownloadFinish(false, 0);
            }
            TbsLog.i(LOGTAG, "[TbsDownloader.needDownload]#1,return " + false);
            instance.a.put(TbsDownloadUpload.TbsUploadKey.KEY_NEEDDOWNLOAD_RETURN, Integer.valueOf(TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_1));
            instance.commit();
            return false;
        }
        TbsLog.app_extra(LOGTAG, context);
        Context applicationContext = context.getApplicationContext();
        c = applicationContext;
        TbsDownloadConfig instance2 = TbsDownloadConfig.getInstance(applicationContext);
        instance2.setDownloadInterruptCode(-100);
        if (!a(c, z)) {
            TbsLog.i(LOGTAG, "[TbsDownloader.needDownload]#2,return " + false);
            instance.a.put(TbsDownloadUpload.TbsUploadKey.KEY_NEEDDOWNLOAD_CODE, Integer.valueOf(TbsListener.ErrorCode.NEEDDOWNLOAD_2));
            instance.commit();
            instance.a.put(TbsDownloadUpload.TbsUploadKey.KEY_NEEDDOWNLOAD_RETURN, Integer.valueOf(TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_2));
            instance.commit();
            if (tbsDownloaderCallback != null) {
                tbsDownloaderCallback.onNeedDownloadFinish(false, 0);
            }
            return false;
        }
        d();
        if (i) {
            if (tbsDownloaderCallback != null) {
                tbsDownloaderCallback.onNeedDownloadFinish(false, 0);
            }
            instance2.setDownloadInterruptCode(-105);
            TbsLog.i(LOGTAG, "[TbsDownloader.needDownload]#3,return " + false);
            instance.a.put(TbsDownloadUpload.TbsUploadKey.KEY_NEEDDOWNLOAD_CODE, Integer.valueOf(TbsListener.ErrorCode.NEEDDOWNLOAD_3));
            instance.commit();
            instance.a.put(TbsDownloadUpload.TbsUploadKey.KEY_NEEDDOWNLOAD_RETURN, Integer.valueOf(TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_3));
            instance.commit();
            if (tbsDownloaderCallback != null) {
                tbsDownloaderCallback.onNeedDownloadFinish(false, 0);
            }
            return false;
        }
        boolean a2 = a(c, z2, false);
        TbsLog.i(LOGTAG, "[TbsDownloader.needDownload],needSendRequest=" + a2);
        if (a2) {
            a(z2, tbsDownloaderCallback, z3);
            instance2.setDownloadInterruptCode(-114);
        } else {
            instance.a.put(TbsDownloadUpload.TbsUploadKey.KEY_NEEDDOWNLOAD_CODE, Integer.valueOf(TbsListener.ErrorCode.NEEDDOWNLOAD_4));
            instance.commit();
        }
        d.removeMessages(102);
        Message.obtain(d, 102).sendToTarget();
        if (QbSdk.c || !TbsShareManager.isThirdPartyApp(context)) {
            z4 = instance2.mPreferences.contains(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD);
            TbsLog.i(LOGTAG, "[TbsDownloader.needDownload] hasNeedDownloadKey=" + z4);
            z5 = (z4 || TbsShareManager.isThirdPartyApp(context)) ? instance2.mPreferences.getBoolean(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD, false) : true;
        } else {
            z5 = false;
            z4 = false;
        }
        TbsLog.i(LOGTAG, "[TbsDownloader.needDownload]#4,needDownload=" + z5 + ",hasNeedDownloadKey=" + z4);
        if (!z5) {
            int n = o.a().n(c);
            TbsLog.i(LOGTAG, "[TbsDownloader.needDownload]#7,tbsLocalVersion=" + n + ",needSendRequest=" + a2);
            if (a2 || n <= 0) {
                d.removeMessages(103);
                ((n > 0 || a2) ? Message.obtain(d, 103, 1, 0, c) : Message.obtain(d, 103, 0, 0, c)).sendToTarget();
                i2 = -121;
            } else {
                i2 = -119;
            }
            instance2.setDownloadInterruptCode(i2);
        } else if (!h()) {
            TbsLog.i(LOGTAG, "[TbsDownloader.needDownload]#5,set needDownload = false");
            z5 = false;
        } else {
            instance2.setDownloadInterruptCode(-118);
            TbsLog.i(LOGTAG, "[TbsDownloader.needDownload]#6");
        }
        if (!a2 && tbsDownloaderCallback != null) {
            tbsDownloaderCallback.onNeedDownloadFinish(false, 0);
        }
        TbsLog.i(LOGTAG, "[TbsDownloader.needDownload] needDownload=" + z5);
        instance.a.put(TbsDownloadUpload.TbsUploadKey.KEY_NEEDDOWNLOAD_RETURN, Integer.valueOf(z5 ? TbsListener.ErrorCode.NEEDDOWNLOAD_TRUE : TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_4));
        instance.commit();
        return z5;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x003b, code lost:
        r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(c).mPreferences.getInt(com.tencent.smtt.sdk.TbsDownloadConfig.TbsConfigKey.KEY_DECOUPLECOREVERSION, 0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean needDownloadDecoupleCore() {
        /*
            android.content.Context r0 = c
            boolean r0 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r0)
            r1 = 0
            if (r0 == 0) goto L_0x000a
            return r1
        L_0x000a:
            android.content.Context r0 = c
            boolean r0 = a((android.content.Context) r0)
            if (r0 == 0) goto L_0x0013
            return r1
        L_0x0013:
            android.content.Context r0 = c
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r0)
            android.content.SharedPreferences r0 = r0.mPreferences
            r2 = 0
            java.lang.String r4 = "last_download_decouple_core"
            long r2 = r0.getLong(r4, r2)
            long r4 = java.lang.System.currentTimeMillis()
            android.content.Context r0 = c
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r0)
            long r6 = r0.getRetryInterval()
            long r4 = r4 - r2
            r2 = 1000(0x3e8, double:4.94E-321)
            long r6 = r6 * r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 >= 0) goto L_0x003b
            return r1
        L_0x003b:
            android.content.Context r0 = c
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r0)
            android.content.SharedPreferences r0 = r0.mPreferences
            java.lang.String r2 = "tbs_decouplecoreversion"
            int r0 = r0.getInt(r2, r1)
            if (r0 <= 0) goto L_0x0069
            com.tencent.smtt.sdk.o r2 = com.tencent.smtt.sdk.o.a()
            android.content.Context r3 = c
            int r2 = r2.i(r3)
            if (r0 == r2) goto L_0x0069
            android.content.Context r2 = c
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
            android.content.SharedPreferences r2 = r2.mPreferences
            java.lang.String r3 = "tbs_download_version"
            int r2 = r2.getInt(r3, r1)
            if (r2 == r0) goto L_0x0069
            r0 = 1
            return r0
        L_0x0069:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsDownloader.needDownloadDecoupleCore():boolean");
    }

    public static boolean needSendRequest(Context context, boolean z) {
        c = context.getApplicationContext();
        TbsLog.initIfNeed(context);
        boolean z2 = false;
        if (!a(c, z)) {
            return false;
        }
        int n = o.a().n(context);
        TbsLog.i(LOGTAG, "[TbsDownloader.needSendRequest] localTbsVersion=" + n);
        if (n > 0) {
            return false;
        }
        if (a(c, false, true)) {
            return true;
        }
        TbsDownloadConfig instance = TbsDownloadConfig.getInstance(c);
        boolean contains = instance.mPreferences.contains(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD);
        TbsLog.i(LOGTAG, "[TbsDownloader.needSendRequest] hasNeedDownloadKey=" + contains);
        boolean z3 = !contains ? true : instance.mPreferences.getBoolean(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD, false);
        TbsLog.i(LOGTAG, "[TbsDownloader.needSendRequest] needDownload=" + z3);
        if (z3 && h()) {
            z2 = true;
        }
        TbsLog.i(LOGTAG, "[TbsDownloader.needSendRequest] ret=" + z2);
        return z2;
    }

    public static void pauseDownload() {
        TbsLog.i(LOGTAG, "called pauseDownload,downloader=" + g);
        l lVar = g;
        if (lVar != null) {
            lVar.e();
        }
    }

    public static void resumeDownload() {
        TbsLog.i(LOGTAG, "called resumeDownload,downloader=" + g);
        Handler handler = d;
        if (handler != null) {
            handler.removeMessages(109);
            d.sendEmptyMessage(109);
        }
    }

    public static void setAppContext(Context context) {
        if (context != null && context.getApplicationContext() != null) {
            c = context.getApplicationContext();
        }
    }

    public static void setRetryIntervalInSeconds(Context context, long j2) {
        if (context != null) {
            if (context.getApplicationInfo().packageName.equals("com.tencent.qqlive")) {
                l = j2;
            }
            TbsLog.i(LOGTAG, "mRetryIntervalInSeconds is " + l);
        }
    }

    public static boolean startDecoupleCoreIfNeeded() {
        StringBuilder sb;
        int i2;
        TbsLog.i(LOGTAG, "startDecoupleCoreIfNeeded ");
        if (TbsShareManager.isThirdPartyApp(c)) {
            return false;
        }
        TbsLog.i(LOGTAG, "startDecoupleCoreIfNeeded #1");
        if (a(c) || d == null) {
            return false;
        }
        TbsLog.i(LOGTAG, "startDecoupleCoreIfNeeded #2");
        long j2 = TbsDownloadConfig.getInstance(c).mPreferences.getLong(TbsDownloadConfig.TbsConfigKey.KEY_LAST_DOWNLOAD_DECOUPLE_CORE, 0);
        if (System.currentTimeMillis() - j2 < TbsDownloadConfig.getInstance(c).getRetryInterval() * 1000) {
            return false;
        }
        TbsLog.i(LOGTAG, "startDecoupleCoreIfNeeded #3");
        int i3 = TbsDownloadConfig.getInstance(c).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DECOUPLECOREVERSION, 0);
        if (i3 <= 0 || i3 == o.a().i(c)) {
            sb = new StringBuilder();
            sb.append("startDecoupleCoreIfNeeded no need, deCoupleCoreVersion is ");
            sb.append(i3);
            sb.append(" getTbsCoreShareDecoupleCoreVersion is ");
            i2 = o.a().i(c);
        } else if (TbsDownloadConfig.getInstance(c).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0) != i3 || TbsDownloadConfig.getInstance(c).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V_TYPE, 0) == 1) {
            TbsLog.i(LOGTAG, "startDecoupleCoreIfNeeded #4");
            a = true;
            d.removeMessages(108);
            Message obtain = Message.obtain(d, 108, QbSdk.m);
            obtain.arg1 = 0;
            obtain.sendToTarget();
            TbsDownloadConfig.getInstance(c).mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_LAST_DOWNLOAD_DECOUPLE_CORE, Long.valueOf(System.currentTimeMillis()));
            return true;
        } else {
            sb = new StringBuilder();
            sb.append("startDecoupleCoreIfNeeded no need, KEY_TBS_DOWNLOAD_V is ");
            sb.append(TbsDownloadConfig.getInstance(c).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0));
            sb.append(" deCoupleCoreVersion is ");
            sb.append(i3);
            sb.append(" KEY_TBS_DOWNLOAD_V_TYPE is ");
            i2 = TbsDownloadConfig.getInstance(c).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V_TYPE, 0);
        }
        sb.append(i2);
        TbsLog.i(LOGTAG, sb.toString());
        return false;
    }

    public static void startDownload(Context context) {
        startDownload(context, false);
    }

    public static synchronized void startDownload(Context context, boolean z) {
        synchronized (TbsDownloader.class) {
            TbsLog.initIfNeed(context);
            TbsDownloadUpload instance = TbsDownloadUpload.getInstance(context);
            instance.a.put(TbsDownloadUpload.TbsUploadKey.KEY_STARTDOWNLOAD_CODE, 160);
            instance.commit();
            TbsLog.i(LOGTAG, "[TbsDownloader.startDownload] sAppContext=" + c);
            if (o.b) {
                instance.a.put(TbsDownloadUpload.TbsUploadKey.KEY_STARTDOWNLOAD_CODE, 161);
                instance.commit();
                return;
            }
            int i2 = 0;
            o.a().b(context, f.a == 0);
            int b2 = o.a().b(context);
            TbsLog.i(LOGTAG, "[TbsDownloader.needDownload],renameRet=" + b2);
            if (b2 != 0) {
                TbsLogReport.TbsLogInfo tbsLogInfo = TbsLogReport.getInstance(context).tbsLogInfo();
                tbsLogInfo.a = TbsListener.ErrorCode.RENAME_NO_NEED_SENDREQUEST;
                tbsLogInfo.setFailDetail("code=2" + b2);
                TbsLogReport.getInstance(context).eventReport(TbsLogReport.EventType.TYPE_SDK_REPORT_INFO, tbsLogInfo);
            }
            if (b2 < 0) {
                TbsLog.i(LOGTAG, "[TbsDownloader.needDownload],needReNameFile=" + b2);
                return;
            }
            a = true;
            Context applicationContext = context.getApplicationContext();
            c = applicationContext;
            TbsDownloadConfig.getInstance(applicationContext).setDownloadInterruptCode(-200);
            if (Build.VERSION.SDK_INT < 8) {
                QbSdk.m.onDownloadFinish(110);
                TbsDownloadConfig.getInstance(c).setDownloadInterruptCode(-201);
                instance.a.put(TbsDownloadUpload.TbsUploadKey.KEY_STARTDOWNLOAD_CODE, 162);
                instance.commit();
                return;
            }
            d();
            if (i) {
                QbSdk.m.onDownloadFinish(TbsListener.ErrorCode.THREAD_INIT_ERROR);
                TbsDownloadConfig.getInstance(c).setDownloadInterruptCode(-202);
                instance.a.put(TbsDownloadUpload.TbsUploadKey.KEY_STARTDOWNLOAD_CODE, 163);
                instance.commit();
                return;
            }
            if (z) {
                stopDownload();
            }
            d.removeMessages(101);
            d.removeMessages(100);
            Message obtain = Message.obtain(d, 101, QbSdk.m);
            if (z) {
                i2 = 1;
            }
            obtain.arg1 = i2;
            obtain.sendToTarget();
        }
    }

    public static void stopDownload() {
        if (!i) {
            TbsLog.i(LOGTAG, "[TbsDownloader.stopDownload]");
            l lVar = g;
            if (lVar != null) {
                lVar.b();
            }
            Handler handler = d;
            if (handler != null) {
                handler.removeMessages(100);
                d.removeMessages(101);
                d.removeMessages(108);
            }
        }
    }
}
