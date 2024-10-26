package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.TbsListener;
import com.tencent.smtt.utils.FileUtil;
import com.tencent.smtt.utils.TbsLog;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.nio.channels.FileLock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import mc.csst.com.selfchassislibrary.content.SelfChassisStateContent;
import org.apache.commons.lang3.StringUtils;

class o {
    public static ThreadLocal<Integer> a = new ThreadLocal<Integer>() {
        /* renamed from: a */
        public Integer initialValue() {
            return 0;
        }
    };
    static boolean b = false;
    static final FileFilter c = new FileFilter() {
        public boolean accept(File file) {
            String name = file.getName();
            if (name == null || name.endsWith(".jar_is_first_load_dex_flag_file")) {
                return false;
            }
            if (Build.VERSION.SDK_INT >= 21 && name.endsWith(".dex")) {
                return false;
            }
            if (Build.VERSION.SDK_INT < 26 || !name.endsWith(".prof")) {
                return Build.VERSION.SDK_INT < 26 || !name.equals("oat");
            }
            return false;
        }
    };
    private static o d;
    private static final ReentrantLock i = new ReentrantLock();
    private static final Lock j = new ReentrantLock();
    private static FileLock l = null;
    private static Handler m = null;
    private static final Long[][] n = {new Long[]{44006L, 39094008L}, new Long[]{44005L, 39094008L}, new Long[]{43910L, 38917816L}, new Long[]{44027L, 39094008L}, new Long[]{44028L, 39094008L}, new Long[]{44029L, 39094008L}, new Long[]{44030L, 39094008L}, new Long[]{44032L, 39094008L}, new Long[]{44033L, 39094008L}, new Long[]{44034L, 39094008L}, new Long[]{43909L, 38917816L}};
    private static int o = 0;
    private static boolean p = false;
    private int e = 0;
    private FileLock f;
    private FileOutputStream g;
    private boolean h = false;
    private boolean k = false;

    private o() {
        if (m == null) {
            m = new Handler(n.a().getLooper()) {
                public void handleMessage(Message message) {
                    QbSdk.setTBSInstallingStatus(true);
                    int i = message.what;
                    if (i == 1) {
                        TbsLog.i("TbsInstaller", "TbsInstaller--handleMessage--MSG_INSTALL_TBS_CORE");
                        Object[] objArr = (Object[]) message.obj;
                        o.this.b((Context) objArr[0], (String) objArr[1], ((Integer) objArr[2]).intValue());
                    } else if (i == 2) {
                        TbsLog.i("TbsInstaller", "TbsInstaller--handleMessage--MSG_COPY_TBS_CORE");
                        Object[] objArr2 = (Object[]) message.obj;
                        o.this.a((Context) objArr2[0], (Context) objArr2[1], ((Integer) objArr2[2]).intValue());
                    } else if (i == 3) {
                        TbsLog.i("TbsInstaller", "TbsInstaller--handleMessage--MSG_INSTALL_TBS_CORE_EX");
                        Object[] objArr3 = (Object[]) message.obj;
                        o.this.b((Context) objArr3[0], (Bundle) objArr3[1]);
                    } else if (i == 4) {
                        TbsLog.i("TbsInstaller", "TbsInstaller--handleMessage--MSG_UNZIP_TBS_CORE");
                        Object[] objArr4 = (Object[]) message.obj;
                        o.this.b((Context) objArr4[0], (File) objArr4[1], ((Integer) objArr4[2]).intValue());
                        QbSdk.setTBSInstallingStatus(false);
                        super.handleMessage(message);
                    }
                }
            };
        }
    }

    private void A(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--generateNewTbsCoreFromCopy");
        if (!x(context)) {
            TbsLog.i("TbsInstaller", "get rename fileLock#4 ## failed!");
            return;
        }
        try {
            B(context);
            D(context);
            if (TbsShareManager.isThirdPartyApp(context)) {
                TbsShareManager.writeCoreInfoForThirdPartyApp(context, n(context), true);
            } else {
                TbsShareManager.a(context);
            }
            m.a(context).a(0, 3);
            m.a(context).a("tpatch_num", 0);
            if (!TbsShareManager.isThirdPartyApp(context)) {
                int i2 = TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DECOUPLECOREVERSION, 0);
                if (i2 <= 0 || i2 == a().i(context) || i2 != a().j(context)) {
                    TbsLog.i("TbsInstaller", "TbsInstaller--generateNewTbsCoreFromCopy #1 deCoupleCoreVersion is " + i2 + " getTbsCoreShareDecoupleCoreVersion is " + a().i(context) + " getTbsCoreInstalledVerInNolock is " + a().j(context));
                } else {
                    o(context);
                }
            }
            a.set(0);
        } catch (Exception e2) {
            e2.printStackTrace();
            TbsLogReport instance = TbsLogReport.getInstance(context);
            instance.setInstallErrorCode((int) TbsListener.ErrorCode.RENAME_EXCEPTION, "exception when renameing from copy:" + e2.toString());
        }
        h(context);
    }

    private void B(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--deleteOldCore");
        FileUtil.a(r(context), false);
    }

    private void C(Context context) {
        int i2;
        TbsLogReport tbsLogReport;
        TbsLog.i("TbsInstaller", "TbsInstaller--renameShareDir");
        File f2 = f(context, 0);
        File r = r(context);
        if (f2 == null || r == null) {
            TbsLog.i("TbsInstaller", "renameTbsCoreShareDir return,tmpTbsCoreUnzipDir=" + f2 + "tbsSharePath=" + r);
            return;
        }
        boolean renameTo = f2.renameTo(r);
        TbsLog.i("TbsInstaller", "renameTbsCoreShareDir rename success=" + renameTo);
        if (context != null && TbsConfig.APP_WX.equals(context.getApplicationContext().getApplicationInfo().packageName)) {
            if (renameTo) {
                tbsLogReport = TbsLogReport.getInstance(context);
                i2 = TbsListener.ErrorCode.RENAME_SUCCESS;
            } else {
                tbsLogReport = TbsLogReport.getInstance(context);
                i2 = TbsListener.ErrorCode.RENAME_FAIL;
            }
            tbsLogReport.setInstallErrorCode(i2, StringUtils.SPACE);
        }
        g(context, false);
    }

    private void D(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--renameTbsCoreCopyDir");
        File f2 = f(context, 1);
        File r = r(context);
        if (f2 != null && r != null) {
            f2.renameTo(r);
            g(context, false);
        }
    }

    private void E(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--renameTbsTpatchCoreDir");
        File f2 = f(context, 5);
        File r = r(context);
        if (f2 != null && r != null) {
            f2.renameTo(r);
            g(context, false);
        }
    }

    private void F(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--clearNewTbsCore");
        File f2 = f(context, 0);
        if (f2 != null) {
            FileUtil.a(f2, false);
        }
        m.a(context).c(0, 5);
        m.a(context).c(-1);
        QbSdk.a(context, "TbsInstaller::clearNewTbsCore forceSysWebViewInner!");
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
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    static synchronized com.tencent.smtt.sdk.o a() {
        /*
            java.lang.Class<com.tencent.smtt.sdk.o> r0 = com.tencent.smtt.sdk.o.class
            monitor-enter(r0)
            com.tencent.smtt.sdk.o r1 = d     // Catch:{ all -> 0x001c }
            if (r1 != 0) goto L_0x0018
            monitor-enter(r0)     // Catch:{ all -> 0x001c }
            com.tencent.smtt.sdk.o r1 = d     // Catch:{ all -> 0x0015 }
            if (r1 != 0) goto L_0x0013
            com.tencent.smtt.sdk.o r1 = new com.tencent.smtt.sdk.o     // Catch:{ all -> 0x0015 }
            r1.<init>()     // Catch:{ all -> 0x0015 }
            d = r1     // Catch:{ all -> 0x0015 }
        L_0x0013:
            monitor-exit(r0)     // Catch:{ all -> 0x0015 }
            goto L_0x0018
        L_0x0015:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0015 }
            throw r1     // Catch:{ all -> 0x001c }
        L_0x0018:
            com.tencent.smtt.sdk.o r1 = d     // Catch:{ all -> 0x001c }
            monitor-exit(r0)
            return r1
        L_0x001c:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.o.a():com.tencent.smtt.sdk.o");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: java.io.BufferedOutputStream} */
    /* JADX WARNING: type inference failed for: r1v2, types: [java.lang.String] */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0080 A[SYNTHETIC, Splitter:B:24:0x0080] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x008a A[SYNTHETIC, Splitter:B:29:0x008a] */
    /* JADX WARNING: Removed duplicated region for block: B:46:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(int r6, java.lang.String r7, android.content.Context r8) {
        /*
            r5 = this;
            java.io.File r6 = new java.io.File
            r6.<init>(r7)
            r6.delete()
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r0 = "Local tbs apk("
            r6.append(r0)
            r6.append(r7)
            java.lang.String r7 = ") is deleted!"
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            java.lang.String r7 = "TbsInstaller"
            r0 = 1
            com.tencent.smtt.utils.TbsLog.i(r7, r6, r0)
            java.io.File r6 = com.tencent.smtt.sdk.QbSdk.getTbsFolderDir(r8)
            java.io.File r8 = new java.io.File
            java.lang.String r1 = "core_unzip_tmp"
            r8.<init>(r6, r1)
            boolean r6 = r8.canRead()
            if (r6 == 0) goto L_0x00a9
            java.io.File r6 = new java.io.File
            java.lang.String r1 = "tbs.conf"
            r6.<init>(r8, r1)
            java.util.Properties r8 = new java.util.Properties
            r8.<init>()
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ all -> 0x0079 }
            r2.<init>(r6)     // Catch:{ all -> 0x0079 }
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch:{ all -> 0x0079 }
            r3.<init>(r2)     // Catch:{ all -> 0x0079 }
            r8.load(r3)     // Catch:{ all -> 0x0077 }
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ all -> 0x0077 }
            r2.<init>(r6)     // Catch:{ all -> 0x0077 }
            java.io.BufferedOutputStream r6 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x0077 }
            r6.<init>(r2)     // Catch:{ all -> 0x0077 }
            java.lang.String r2 = "tbs_local_installation"
            java.lang.String r4 = "true"
            r8.setProperty(r2, r4)     // Catch:{ all -> 0x0074 }
            r8.store(r6, r1)     // Catch:{ all -> 0x0074 }
            java.lang.String r8 = "TBS_LOCAL_INSTALLATION is set!"
            com.tencent.smtt.utils.TbsLog.i(r7, r8, r0)     // Catch:{ all -> 0x0074 }
            r6.close()     // Catch:{ IOException -> 0x006c }
            goto L_0x0070
        L_0x006c:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0070:
            r3.close()     // Catch:{ IOException -> 0x008e }
            goto L_0x00a9
        L_0x0074:
            r7 = move-exception
            r1 = r6
            goto L_0x007b
        L_0x0077:
            r7 = move-exception
            goto L_0x007b
        L_0x0079:
            r7 = move-exception
            r3 = r1
        L_0x007b:
            r7.printStackTrace()     // Catch:{ all -> 0x0093 }
            if (r1 == 0) goto L_0x0088
            r1.close()     // Catch:{ IOException -> 0x0084 }
            goto L_0x0088
        L_0x0084:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0088:
            if (r3 == 0) goto L_0x00a9
            r3.close()     // Catch:{ IOException -> 0x008e }
            goto L_0x00a9
        L_0x008e:
            r6 = move-exception
            r6.printStackTrace()
            goto L_0x00a9
        L_0x0093:
            r6 = move-exception
            if (r1 == 0) goto L_0x009e
            r1.close()     // Catch:{ IOException -> 0x009a }
            goto L_0x009e
        L_0x009a:
            r7 = move-exception
            r7.printStackTrace()
        L_0x009e:
            if (r3 == 0) goto L_0x00a8
            r3.close()     // Catch:{ IOException -> 0x00a4 }
            goto L_0x00a8
        L_0x00a4:
            r7 = move-exception
            r7.printStackTrace()
        L_0x00a8:
            throw r6
        L_0x00a9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.o.a(int, java.lang.String, android.content.Context):void");
    }

    public static void a(Context context) {
        String str;
        if (!w(context)) {
            if (a(context, "core_unzip_tmp")) {
                TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.INFO_TEMP_CORE_EXIST_CONF_ERROR, new Throwable("TMP_TBS_UNZIP_FOLDER_NAME"));
                str = "TbsInstaller-UploadIfTempCoreExistConfError INFO_TEMP_CORE_EXIST_CONF_ERROR TMP_TBS_UNZIP_FOLDER_NAME";
            } else if (a(context, "core_share_backup_tmp")) {
                TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.INFO_TEMP_CORE_EXIST_CONF_ERROR, new Throwable("TMP_BACKUP_TBSCORE_FOLDER_NAME"));
                str = "TbsInstaller-UploadIfTempCoreExistConfError INFO_TEMP_CORE_EXIST_CONF_ERROR TMP_BACKUP_TBSCORE_FOLDER_NAME";
            } else if (a(context, "core_copy_tmp")) {
                TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.INFO_TEMP_CORE_EXIST_CONF_ERROR, new Throwable("TMP_TBS_COPY_FOLDER_NAME"));
                str = "TbsInstaller-UploadIfTempCoreExistConfError INFO_TEMP_CORE_EXIST_CONF_ERROR TMP_TBS_COPY_FOLDER_NAME";
            } else {
                return;
            }
            TbsLog.e("TbsInstaller", str);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x02db, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x02dc, code lost:
        r3 = r0;
        r16 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x02e1, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x02e2, code lost:
        r6 = r0;
        r9 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:?, code lost:
        r16.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x02ef, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:?, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x02db A[ExcHandler: all (r0v9 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:90:0x029d] */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x02eb A[SYNTHETIC, Splitter:B:121:0x02eb] */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x02f7 A[Catch:{ Exception -> 0x04f2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x03c4 A[ADDED_TO_REGION, Catch:{ Exception -> 0x04f2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:168:0x040e A[Catch:{ Exception -> 0x04f2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:169:0x0411 A[Catch:{ Exception -> 0x04f2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x0429 A[Catch:{ Exception -> 0x04f2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:175:0x0435 A[Catch:{ Exception -> 0x04f2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x0461 A[Catch:{ Exception -> 0x04f2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:179:0x046a A[Catch:{ Exception -> 0x04f2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:190:0x04aa A[SYNTHETIC, Splitter:B:190:0x04aa] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(android.content.Context r22, android.content.Context r23, int r24) {
        /*
            r21 = this;
            r1 = r21
            r2 = r23
            r3 = r24
            java.lang.String r4 = ""
            java.lang.String r5 = "1"
            java.lang.String r6 = "copy_retry_num"
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r22)
            r8 = -524(0xfffffffffffffdf4, float:NaN)
            r7.setInstallInterruptCode(r8)
            boolean r7 = r1.d(r2)
            if (r7 == 0) goto L_0x001c
            return
        L_0x001c:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "TbsInstaller-copyTbsCoreInThread start!  tbsCoreTargetVer is "
            r7.append(r8)
            r7.append(r3)
            java.lang.String r7 = r7.toString()
            java.lang.String r8 = "TbsInstaller"
            com.tencent.smtt.utils.TbsLog.i(r8, r7)
            int r7 = android.os.Build.VERSION.SDK_INT
            r9 = 4
            r10 = 11
            java.lang.String r11 = "tbs_preloadx5_check_cfg_file"
            r12 = 0
            if (r7 < r10) goto L_0x0041
            android.content.SharedPreferences r7 = r2.getSharedPreferences(r11, r9)
            goto L_0x0045
        L_0x0041:
            android.content.SharedPreferences r7 = r2.getSharedPreferences(r11, r12)
        L_0x0045:
            java.lang.String r13 = "tbs_precheck_disable_version"
            r14 = -1
            int r7 = r7.getInt(r13, r14)
            if (r7 != r3) goto L_0x0071
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "TbsInstaller-copyTbsCoreInThread -- version:"
            r2.append(r4)
            r2.append(r3)
            java.lang.String r3 = " is disabled by preload_x5_check!"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            com.tencent.smtt.utils.TbsLog.e(r8, r2)
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r22)
            r3 = -525(0xfffffffffffffdf3, float:NaN)
            r2.setInstallInterruptCode(r3)
            return
        L_0x0071:
            boolean r7 = r1.u(r2)
            if (r7 != 0) goto L_0x0081
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r22)
            r3 = -526(0xfffffffffffffdf2, float:NaN)
            r2.setInstallInterruptCode(r3)
            return
        L_0x0081:
            java.util.concurrent.locks.Lock r7 = j
            boolean r7 = r7.tryLock()
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.lang.String r15 = "TbsInstaller-copyTbsCoreInThread #1 locked is "
            r13.append(r15)
            r13.append(r7)
            java.lang.String r13 = r13.toString()
            com.tencent.smtt.utils.TbsLog.i(r8, r13)
            if (r7 == 0) goto L_0x0577
            java.util.concurrent.locks.ReentrantLock r7 = i
            r7.lock()
            com.tencent.smtt.sdk.m r13 = com.tencent.smtt.sdk.m.a((android.content.Context) r23)     // Catch:{ Exception -> 0x050f }
            java.lang.String r15 = "copy_core_ver"
            int r13 = r13.c((java.lang.String) r15)     // Catch:{ Exception -> 0x050f }
            com.tencent.smtt.sdk.m r15 = com.tencent.smtt.sdk.m.a((android.content.Context) r23)     // Catch:{ Exception -> 0x050f }
            java.lang.String r7 = "copy_status"
            int r7 = r15.b((java.lang.String) r7)     // Catch:{ Exception -> 0x050f }
            r15 = -528(0xfffffffffffffdf0, float:NaN)
            r14 = 220(0xdc, float:3.08E-43)
            if (r13 != r3) goto L_0x00d6
            com.tencent.smtt.sdk.TbsListener r3 = com.tencent.smtt.sdk.QbSdk.m     // Catch:{ Exception -> 0x050f }
            r3.onInstallFinish(r14)     // Catch:{ Exception -> 0x050f }
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r22)     // Catch:{ Exception -> 0x050f }
            r3.setInstallInterruptCode(r15)     // Catch:{ Exception -> 0x050f }
            java.util.concurrent.locks.ReentrantLock r2 = i
            r2.unlock()
            java.util.concurrent.locks.Lock r2 = j
            r2.unlock()
            r21.b()
            return
        L_0x00d6:
            int r9 = r1.j(r2)     // Catch:{ Exception -> 0x050f }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x050f }
            r10.<init>()     // Catch:{ Exception -> 0x050f }
            java.lang.String r12 = "TbsInstaller-copyTbsCoreInThread tbsCoreInstalledVer="
            r10.append(r12)     // Catch:{ Exception -> 0x050f }
            r10.append(r9)     // Catch:{ Exception -> 0x050f }
            java.lang.String r10 = r10.toString()     // Catch:{ Exception -> 0x050f }
            com.tencent.smtt.utils.TbsLog.i(r8, r10)     // Catch:{ Exception -> 0x050f }
            if (r9 != r3) goto L_0x011e
            com.tencent.smtt.sdk.TbsListener r3 = com.tencent.smtt.sdk.QbSdk.m     // Catch:{ Exception -> 0x050f }
            r3.onInstallFinish(r14)     // Catch:{ Exception -> 0x050f }
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r22)     // Catch:{ Exception -> 0x050f }
            r3.setInstallInterruptCode(r15)     // Catch:{ Exception -> 0x050f }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x050f }
            r3.<init>()     // Catch:{ Exception -> 0x050f }
            java.lang.String r4 = "TbsInstaller-copyTbsCoreInThread return have same version is "
            r3.append(r4)     // Catch:{ Exception -> 0x050f }
            r3.append(r9)     // Catch:{ Exception -> 0x050f }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x050f }
            com.tencent.smtt.utils.TbsLog.i(r8, r3)     // Catch:{ Exception -> 0x050f }
            java.util.concurrent.locks.ReentrantLock r2 = i
            r2.unlock()
            java.util.concurrent.locks.Lock r2 = j
            r2.unlock()
            r21.b()
            return
        L_0x011e:
            com.tencent.smtt.sdk.m r10 = com.tencent.smtt.sdk.m.a((android.content.Context) r23)     // Catch:{ Exception -> 0x050f }
            int r10 = r10.b()     // Catch:{ Exception -> 0x050f }
            if (r10 <= 0) goto L_0x012a
            if (r3 > r10) goto L_0x012e
        L_0x012a:
            if (r13 <= 0) goto L_0x0131
            if (r3 <= r13) goto L_0x0131
        L_0x012e:
            r1.p(r2)     // Catch:{ Exception -> 0x050f }
        L_0x0131:
            r10 = 3
            r12 = 1
            if (r7 != r10) goto L_0x0147
            if (r9 <= 0) goto L_0x0147
            if (r3 > r9) goto L_0x013e
            r9 = 88888888(0x54c5638, float:9.60787E-36)
            if (r3 != r9) goto L_0x0147
        L_0x013e:
            r1.p(r2)     // Catch:{ Exception -> 0x050f }
            java.lang.String r7 = "TbsInstaller-copyTbsCoreInThread -- update TBS....."
            com.tencent.smtt.utils.TbsLog.i(r8, r7, r12)     // Catch:{ Exception -> 0x050f }
            r7 = -1
        L_0x0147:
            boolean r9 = com.tencent.smtt.utils.FileUtil.b((android.content.Context) r23)     // Catch:{ Exception -> 0x050f }
            if (r9 != 0) goto L_0x0192
            long r3 = com.tencent.smtt.utils.p.a()     // Catch:{ Exception -> 0x050f }
            com.tencent.smtt.sdk.TbsDownloadConfig r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r23)     // Catch:{ Exception -> 0x050f }
            long r5 = r5.getDownloadMinFreeSpace()     // Catch:{ Exception -> 0x050f }
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r22)     // Catch:{ Exception -> 0x050f }
            r9 = -529(0xfffffffffffffdef, float:NaN)
            r7.setInstallInterruptCode(r9)     // Catch:{ Exception -> 0x050f }
            com.tencent.smtt.sdk.TbsLogReport r7 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r23)     // Catch:{ Exception -> 0x050f }
            r9 = 210(0xd2, float:2.94E-43)
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x050f }
            r10.<init>()     // Catch:{ Exception -> 0x050f }
            java.lang.String r11 = "rom is not enough when copying tbs core! curAvailROM="
            r10.append(r11)     // Catch:{ Exception -> 0x050f }
            r10.append(r3)     // Catch:{ Exception -> 0x050f }
            java.lang.String r3 = ",minReqRom="
            r10.append(r3)     // Catch:{ Exception -> 0x050f }
            r10.append(r5)     // Catch:{ Exception -> 0x050f }
            java.lang.String r3 = r10.toString()     // Catch:{ Exception -> 0x050f }
            r7.setInstallErrorCode((int) r9, (java.lang.String) r3)     // Catch:{ Exception -> 0x050f }
            java.util.concurrent.locks.ReentrantLock r2 = i
            r2.unlock()
            java.util.concurrent.locks.Lock r2 = j
            r2.unlock()
            r21.b()
            return
        L_0x0192:
            if (r7 <= 0) goto L_0x01cd
            boolean r9 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r23)     // Catch:{ Exception -> 0x050f }
            if (r9 != 0) goto L_0x01a7
            boolean r9 = com.tencent.smtt.sdk.TbsDownloader.a((android.content.Context) r23)     // Catch:{ Exception -> 0x050f }
            if (r9 == 0) goto L_0x01a7
            int r9 = r1.i(r2)     // Catch:{ Exception -> 0x050f }
            if (r3 == r9) goto L_0x01a7
            goto L_0x01cd
        L_0x01a7:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x050f }
            r3.<init>()     // Catch:{ Exception -> 0x050f }
            java.lang.String r4 = "TbsInstaller-copyTbsCoreInThread return have copied is "
            r3.append(r4)     // Catch:{ Exception -> 0x050f }
            int r4 = r1.i(r2)     // Catch:{ Exception -> 0x050f }
            r3.append(r4)     // Catch:{ Exception -> 0x050f }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x050f }
            com.tencent.smtt.utils.TbsLog.i(r8, r3)     // Catch:{ Exception -> 0x050f }
            java.util.concurrent.locks.ReentrantLock r2 = i
            r2.unlock()
            java.util.concurrent.locks.Lock r2 = j
            r2.unlock()
            r21.b()
            return
        L_0x01cd:
            r9 = 2
            if (r7 != 0) goto L_0x0204
            com.tencent.smtt.sdk.m r7 = com.tencent.smtt.sdk.m.a((android.content.Context) r23)     // Catch:{ Exception -> 0x050f }
            int r7 = r7.c((java.lang.String) r6)     // Catch:{ Exception -> 0x050f }
            if (r7 <= r9) goto L_0x01fc
            com.tencent.smtt.sdk.TbsLogReport r3 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r23)     // Catch:{ Exception -> 0x050f }
            r4 = 211(0xd3, float:2.96E-43)
            java.lang.String r5 = "exceed copy retry num!"
            r3.setInstallErrorCode((int) r4, (java.lang.String) r5)     // Catch:{ Exception -> 0x050f }
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r22)     // Catch:{ Exception -> 0x050f }
            r4 = -530(0xfffffffffffffdee, float:NaN)
            r3.setInstallInterruptCode(r4)     // Catch:{ Exception -> 0x050f }
            java.util.concurrent.locks.ReentrantLock r2 = i
            r2.unlock()
            java.util.concurrent.locks.Lock r2 = j
            r2.unlock()
            r21.b()
            return
        L_0x01fc:
            com.tencent.smtt.sdk.m r10 = com.tencent.smtt.sdk.m.a((android.content.Context) r23)     // Catch:{ Exception -> 0x050f }
            int r7 = r7 + r12
            r10.a((java.lang.String) r6, (int) r7)     // Catch:{ Exception -> 0x050f }
        L_0x0204:
            java.io.File r6 = r21.r(r22)     // Catch:{ Exception -> 0x050f }
            boolean r7 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r23)     // Catch:{ Exception -> 0x050f }
            if (r7 != 0) goto L_0x0219
            boolean r7 = com.tencent.smtt.sdk.TbsDownloader.a((android.content.Context) r23)     // Catch:{ Exception -> 0x050f }
            if (r7 == 0) goto L_0x0219
            java.io.File r7 = r1.q(r2)     // Catch:{ Exception -> 0x050f }
            goto L_0x021d
        L_0x0219:
            java.io.File r7 = r1.f((android.content.Context) r2, (int) r12)     // Catch:{ Exception -> 0x050f }
        L_0x021d:
            if (r6 == 0) goto L_0x04db
            if (r7 == 0) goto L_0x04db
            com.tencent.smtt.sdk.m r13 = com.tencent.smtt.sdk.m.a((android.content.Context) r23)     // Catch:{ Exception -> 0x04f2 }
            r15 = 0
            r13.a((int) r3, (int) r15)     // Catch:{ Exception -> 0x04f2 }
            com.tencent.smtt.utils.o r13 = new com.tencent.smtt.utils.o     // Catch:{ Exception -> 0x04f2 }
            r13.<init>()     // Catch:{ Exception -> 0x04f2 }
            r13.a(r6)     // Catch:{ Exception -> 0x04f2 }
            long r17 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x04f2 }
            com.tencent.smtt.sdk.TbsDownloadConfig r15 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r22)     // Catch:{ Exception -> 0x04f2 }
            r9 = -551(0xfffffffffffffdd9, float:NaN)
            r15.setInstallInterruptCode(r9)     // Catch:{ Exception -> 0x04f2 }
            java.io.FileFilter r9 = c     // Catch:{ Exception -> 0x04f2 }
            boolean r9 = com.tencent.smtt.utils.FileUtil.a((java.io.File) r6, (java.io.File) r7, (java.io.FileFilter) r9)     // Catch:{ Exception -> 0x04f2 }
            boolean r15 = com.tencent.smtt.sdk.TbsDownloader.a((android.content.Context) r23)     // Catch:{ Exception -> 0x04f2 }
            if (r15 == 0) goto L_0x024d
            com.tencent.smtt.sdk.TbsShareManager.b(r23)     // Catch:{ Exception -> 0x04f2 }
        L_0x024d:
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04f2 }
            r15.<init>()     // Catch:{ Exception -> 0x04f2 }
            java.lang.String r14 = "TbsInstaller-copyTbsCoreInThread time="
            r15.append(r14)     // Catch:{ Exception -> 0x04f2 }
            long r19 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x04f2 }
            r14 = r11
            long r10 = r19 - r17
            r15.append(r10)     // Catch:{ Exception -> 0x04f2 }
            java.lang.String r10 = r15.toString()     // Catch:{ Exception -> 0x04f2 }
            com.tencent.smtt.utils.TbsLog.i(r8, r10)     // Catch:{ Exception -> 0x04f2 }
            if (r9 == 0) goto L_0x04b4
            r13.b(r6)     // Catch:{ Exception -> 0x04f2 }
            boolean r6 = r13.a()     // Catch:{ Exception -> 0x04f2 }
            if (r6 != 0) goto L_0x029d
            java.lang.String r3 = "TbsInstaller-copyTbsCoreInThread copy-verify fail!"
            com.tencent.smtt.utils.TbsLog.i(r8, r3)     // Catch:{ Exception -> 0x04f2 }
            com.tencent.smtt.utils.FileUtil.a((java.io.File) r7, (boolean) r12)     // Catch:{ Exception -> 0x04f2 }
            com.tencent.smtt.sdk.TbsLogReport r3 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r23)     // Catch:{ Exception -> 0x04f2 }
            java.lang.String r4 = "TbsCopy-Verify fail after copying tbs core!"
            r5 = 213(0xd5, float:2.98E-43)
            r3.setInstallErrorCode((int) r5, (java.lang.String) r4)     // Catch:{ Exception -> 0x04f2 }
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r22)     // Catch:{ Exception -> 0x04f2 }
            r4 = -531(0xfffffffffffffded, float:NaN)
            r3.setInstallInterruptCode(r4)     // Catch:{ Exception -> 0x04f2 }
            java.util.concurrent.locks.ReentrantLock r2 = i
            r2.unlock()
            java.util.concurrent.locks.Lock r2 = j
            r2.unlock()
            r21.b()
            return
        L_0x029d:
            java.io.File r6 = new java.io.File     // Catch:{ Exception -> 0x02e1, all -> 0x02db }
            r6.<init>(r7, r5)     // Catch:{ Exception -> 0x02e1, all -> 0x02db }
            java.util.Properties r9 = new java.util.Properties     // Catch:{ Exception -> 0x02e1, all -> 0x02db }
            r9.<init>()     // Catch:{ Exception -> 0x02e1, all -> 0x02db }
            boolean r10 = r6.exists()     // Catch:{ Exception -> 0x02d8, all -> 0x02db }
            if (r10 == 0) goto L_0x02c9
            java.io.FileInputStream r10 = new java.io.FileInputStream     // Catch:{ Exception -> 0x02d8, all -> 0x02db }
            r10.<init>(r6)     // Catch:{ Exception -> 0x02d8, all -> 0x02db }
            java.io.BufferedInputStream r6 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x02d8, all -> 0x02db }
            r6.<init>(r10)     // Catch:{ Exception -> 0x02d8, all -> 0x02db }
            r9.load(r6)     // Catch:{ Exception -> 0x02c4, all -> 0x02be }
            r16 = r6
            r6 = 1
            goto L_0x02cc
        L_0x02be:
            r0 = move-exception
            r3 = r0
            r16 = r6
            goto L_0x04a8
        L_0x02c4:
            r0 = move-exception
            r16 = r6
            r6 = r0
            goto L_0x02e6
        L_0x02c9:
            r6 = 0
            r16 = 0
        L_0x02cc:
            if (r16 == 0) goto L_0x02f5
            r16.close()     // Catch:{ IOException -> 0x02d2 }
            goto L_0x02f5
        L_0x02d2:
            r0 = move-exception
            r10 = r0
            r10.printStackTrace()     // Catch:{ Exception -> 0x04f2 }
            goto L_0x02f5
        L_0x02d8:
            r0 = move-exception
            r6 = r0
            goto L_0x02e4
        L_0x02db:
            r0 = move-exception
            r3 = r0
            r16 = 0
            goto L_0x04a8
        L_0x02e1:
            r0 = move-exception
            r6 = r0
            r9 = 0
        L_0x02e4:
            r16 = 0
        L_0x02e6:
            r6.printStackTrace()     // Catch:{ all -> 0x04a6 }
            if (r16 == 0) goto L_0x02f4
            r16.close()     // Catch:{ IOException -> 0x02ef }
            goto L_0x02f4
        L_0x02ef:
            r0 = move-exception
            r6 = r0
            r6.printStackTrace()     // Catch:{ Exception -> 0x04f2 }
        L_0x02f4:
            r6 = 1
        L_0x02f5:
            if (r6 == 0) goto L_0x03ad
            java.io.File[] r10 = r7.listFiles()     // Catch:{ Exception -> 0x04f2 }
            com.tencent.smtt.sdk.TbsDownloadConfig r11 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r22)     // Catch:{ Exception -> 0x04f2 }
            r13 = -552(0xfffffffffffffdd8, float:NaN)
            r11.setInstallInterruptCode(r13)     // Catch:{ Exception -> 0x04f2 }
            r11 = 0
        L_0x0305:
            int r13 = r10.length     // Catch:{ Exception -> 0x04f2 }
            if (r11 >= r13) goto L_0x03ad
            r13 = r10[r11]     // Catch:{ Exception -> 0x04f2 }
            java.lang.String r15 = r13.getName()     // Catch:{ Exception -> 0x04f2 }
            boolean r15 = r5.equals(r15)     // Catch:{ Exception -> 0x04f2 }
            if (r15 != 0) goto L_0x03a4
            java.lang.String r15 = r13.getName()     // Catch:{ Exception -> 0x04f2 }
            java.lang.String r12 = ".dex"
            boolean r12 = r15.endsWith(r12)     // Catch:{ Exception -> 0x04f2 }
            if (r12 != 0) goto L_0x03a4
            java.lang.String r12 = "tbs.conf"
            java.lang.String r15 = r13.getName()     // Catch:{ Exception -> 0x04f2 }
            boolean r12 = r12.equals(r15)     // Catch:{ Exception -> 0x04f2 }
            if (r12 != 0) goto L_0x03a4
            boolean r12 = r13.isDirectory()     // Catch:{ Exception -> 0x04f2 }
            if (r12 != 0) goto L_0x03a4
            java.lang.String r12 = r13.getName()     // Catch:{ Exception -> 0x04f2 }
            java.lang.String r15 = ".prof"
            boolean r12 = r12.endsWith(r15)     // Catch:{ Exception -> 0x04f2 }
            if (r12 == 0) goto L_0x033f
            goto L_0x03a4
        L_0x033f:
            java.lang.String r12 = com.tencent.smtt.utils.a.a((java.io.File) r13)     // Catch:{ Exception -> 0x04f2 }
            java.lang.String r15 = r13.getName()     // Catch:{ Exception -> 0x04f2 }
            java.lang.String r15 = r9.getProperty(r15, r4)     // Catch:{ Exception -> 0x04f2 }
            boolean r16 = r15.equals(r4)     // Catch:{ Exception -> 0x04f2 }
            r18 = r4
            java.lang.String r4 = ")"
            if (r16 != 0) goto L_0x0377
            boolean r16 = r12.equals(r15)     // Catch:{ Exception -> 0x04f2 }
            if (r16 == 0) goto L_0x0377
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04f2 }
            r12.<init>()     // Catch:{ Exception -> 0x04f2 }
            java.lang.String r15 = "md5_check_success for ("
            r12.append(r15)     // Catch:{ Exception -> 0x04f2 }
            java.lang.String r13 = r13.getName()     // Catch:{ Exception -> 0x04f2 }
            r12.append(r13)     // Catch:{ Exception -> 0x04f2 }
            r12.append(r4)     // Catch:{ Exception -> 0x04f2 }
            java.lang.String r4 = r12.toString()     // Catch:{ Exception -> 0x04f2 }
            com.tencent.smtt.utils.TbsLog.i(r8, r4)     // Catch:{ Exception -> 0x04f2 }
            goto L_0x03a6
        L_0x0377:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04f2 }
            r5.<init>()     // Catch:{ Exception -> 0x04f2 }
            java.lang.String r9 = "md5_check_failure for ("
            r5.append(r9)     // Catch:{ Exception -> 0x04f2 }
            java.lang.String r9 = r13.getName()     // Catch:{ Exception -> 0x04f2 }
            r5.append(r9)     // Catch:{ Exception -> 0x04f2 }
            r5.append(r4)     // Catch:{ Exception -> 0x04f2 }
            java.lang.String r4 = " targetMd5:"
            r5.append(r4)     // Catch:{ Exception -> 0x04f2 }
            r5.append(r15)     // Catch:{ Exception -> 0x04f2 }
            java.lang.String r4 = ", realMd5:"
            r5.append(r4)     // Catch:{ Exception -> 0x04f2 }
            r5.append(r12)     // Catch:{ Exception -> 0x04f2 }
            java.lang.String r4 = r5.toString()     // Catch:{ Exception -> 0x04f2 }
            com.tencent.smtt.utils.TbsLog.e(r8, r4)     // Catch:{ Exception -> 0x04f2 }
            r4 = 0
            goto L_0x03ae
        L_0x03a4:
            r18 = r4
        L_0x03a6:
            int r11 = r11 + 1
            r4 = r18
            r12 = 1
            goto L_0x0305
        L_0x03ad:
            r4 = 1
        L_0x03ae:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04f2 }
            r5.<init>()     // Catch:{ Exception -> 0x04f2 }
            java.lang.String r9 = "copyTbsCoreInThread - md5_check_success:"
            r5.append(r9)     // Catch:{ Exception -> 0x04f2 }
            r5.append(r4)     // Catch:{ Exception -> 0x04f2 }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x04f2 }
            com.tencent.smtt.utils.TbsLog.i(r8, r5)     // Catch:{ Exception -> 0x04f2 }
            if (r6 == 0) goto L_0x03f1
            if (r4 != 0) goto L_0x03f1
            java.lang.String r3 = "copyTbsCoreInThread - md5 incorrect -> delete destTmpDir!"
            com.tencent.smtt.utils.TbsLog.e(r8, r3)     // Catch:{ Exception -> 0x04f2 }
            r3 = 1
            com.tencent.smtt.utils.FileUtil.a((java.io.File) r7, (boolean) r3)     // Catch:{ Exception -> 0x04f2 }
            com.tencent.smtt.sdk.TbsLogReport r3 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r23)     // Catch:{ Exception -> 0x04f2 }
            java.lang.String r4 = "TbsCopy-Verify md5 fail after copying tbs core!"
            r5 = 213(0xd5, float:2.98E-43)
            r3.setInstallErrorCode((int) r5, (java.lang.String) r4)     // Catch:{ Exception -> 0x04f2 }
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r22)     // Catch:{ Exception -> 0x04f2 }
            r4 = -532(0xfffffffffffffdec, float:NaN)
            r3.setInstallInterruptCode(r4)     // Catch:{ Exception -> 0x04f2 }
            java.util.concurrent.locks.ReentrantLock r2 = i
            r2.unlock()
            java.util.concurrent.locks.Lock r2 = j
            r2.unlock()
            r21.b()
            return
        L_0x03f1:
            java.lang.String r4 = "TbsInstaller-copyTbsCoreInThread success!"
            com.tencent.smtt.utils.TbsLog.i(r8, r4)     // Catch:{ Exception -> 0x04f2 }
            r4 = 1
            r1.g((android.content.Context) r2, (boolean) r4)     // Catch:{ Exception -> 0x04f2 }
            java.io.File r4 = com.tencent.smtt.sdk.l.b((android.content.Context) r22)     // Catch:{ Exception -> 0x04f2 }
            if (r4 == 0) goto L_0x041d
            boolean r5 = r4.exists()     // Catch:{ Exception -> 0x04f2 }
            if (r5 == 0) goto L_0x041d
            java.io.File r5 = new java.io.File     // Catch:{ Exception -> 0x04f2 }
            boolean r6 = com.tencent.smtt.sdk.TbsDownloader.getOverSea(r23)     // Catch:{ Exception -> 0x04f2 }
            if (r6 == 0) goto L_0x0411
            java.lang.String r6 = "x5.oversea.tbs.org"
            goto L_0x0417
        L_0x0411:
            r6 = 0
            java.lang.String r9 = com.tencent.smtt.sdk.TbsDownloader.getBackupFileName(r6)     // Catch:{ Exception -> 0x04f2 }
            r6 = r9
        L_0x0417:
            r5.<init>(r4, r6)     // Catch:{ Exception -> 0x04f2 }
            com.tencent.smtt.sdk.l.a((java.io.File) r5, (android.content.Context) r2)     // Catch:{ Exception -> 0x04f2 }
        L_0x041d:
            com.tencent.smtt.sdk.m r4 = com.tencent.smtt.sdk.m.a((android.content.Context) r23)     // Catch:{ Exception -> 0x04f2 }
            r5 = 1
            r4.a((int) r3, (int) r5)     // Catch:{ Exception -> 0x04f2 }
            boolean r4 = r1.k     // Catch:{ Exception -> 0x04f2 }
            if (r4 == 0) goto L_0x0435
            com.tencent.smtt.sdk.TbsLogReport r4 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r23)     // Catch:{ Exception -> 0x04f2 }
            java.lang.String r5 = "continueInstallWithout core success"
            r6 = 220(0xdc, float:3.08E-43)
        L_0x0431:
            r4.setInstallErrorCode((int) r6, (java.lang.String) r5)     // Catch:{ Exception -> 0x04f2 }
            goto L_0x043e
        L_0x0435:
            com.tencent.smtt.sdk.TbsLogReport r4 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r23)     // Catch:{ Exception -> 0x04f2 }
            java.lang.String r5 = "success"
            r6 = 220(0xdc, float:3.08E-43)
            goto L_0x0431
        L_0x043e:
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r22)     // Catch:{ Exception -> 0x04f2 }
            r5 = -533(0xfffffffffffffdeb, float:NaN)
            r4.setInstallInterruptCode(r5)     // Catch:{ Exception -> 0x04f2 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04f2 }
            r4.<init>()     // Catch:{ Exception -> 0x04f2 }
            java.lang.String r5 = "TbsInstaller-copyTbsCoreInThread success -- version:"
            r4.append(r5)     // Catch:{ Exception -> 0x04f2 }
            r4.append(r3)     // Catch:{ Exception -> 0x04f2 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x04f2 }
            com.tencent.smtt.utils.TbsLog.i(r8, r4)     // Catch:{ Exception -> 0x04f2 }
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x04f2 }
            r5 = 11
            if (r4 < r5) goto L_0x046a
            r5 = r14
            r4 = 4
            android.content.SharedPreferences r4 = r2.getSharedPreferences(r5, r4)     // Catch:{ Exception -> 0x04f2 }
            r5 = r4
            r4 = 0
            goto L_0x0470
        L_0x046a:
            r5 = r14
            r4 = 0
            android.content.SharedPreferences r5 = r2.getSharedPreferences(r5, r4)     // Catch:{ Exception -> 0x04f2 }
        L_0x0470:
            android.content.SharedPreferences$Editor r5 = r5.edit()     // Catch:{ all -> 0x0487 }
            java.lang.String r6 = "tbs_preload_x5_counter"
            r5.putInt(r6, r4)     // Catch:{ all -> 0x0487 }
            java.lang.String r6 = "tbs_preload_x5_recorder"
            r5.putInt(r6, r4)     // Catch:{ all -> 0x0487 }
            java.lang.String r4 = "tbs_preload_x5_version"
            r5.putInt(r4, r3)     // Catch:{ all -> 0x0487 }
            r5.commit()     // Catch:{ all -> 0x0487 }
            goto L_0x04a1
        L_0x0487:
            r0 = move-exception
            r3 = r0
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04f2 }
            r4.<init>()     // Catch:{ Exception -> 0x04f2 }
            java.lang.String r5 = "Init tbs_preload_x5_counter#2 exception:"
            r4.append(r5)     // Catch:{ Exception -> 0x04f2 }
            java.lang.String r3 = android.util.Log.getStackTraceString(r3)     // Catch:{ Exception -> 0x04f2 }
            r4.append(r3)     // Catch:{ Exception -> 0x04f2 }
            java.lang.String r3 = r4.toString()     // Catch:{ Exception -> 0x04f2 }
            com.tencent.smtt.utils.TbsLog.e(r8, r3)     // Catch:{ Exception -> 0x04f2 }
        L_0x04a1:
            com.tencent.smtt.utils.p.a(r23)     // Catch:{ Exception -> 0x04f2 }
            goto L_0x055b
        L_0x04a6:
            r0 = move-exception
            r3 = r0
        L_0x04a8:
            if (r16 == 0) goto L_0x04b3
            r16.close()     // Catch:{ IOException -> 0x04ae }
            goto L_0x04b3
        L_0x04ae:
            r0 = move-exception
            r4 = r0
            r4.printStackTrace()     // Catch:{ Exception -> 0x04f2 }
        L_0x04b3:
            throw r3     // Catch:{ Exception -> 0x04f2 }
        L_0x04b4:
            java.lang.String r4 = "TbsInstaller-copyTbsCoreInThread fail!"
            com.tencent.smtt.utils.TbsLog.i(r8, r4)     // Catch:{ Exception -> 0x04f2 }
            com.tencent.smtt.sdk.m r4 = com.tencent.smtt.sdk.m.a((android.content.Context) r23)     // Catch:{ Exception -> 0x04f2 }
            r5 = 2
            r4.a((int) r3, (int) r5)     // Catch:{ Exception -> 0x04f2 }
            r3 = 0
            com.tencent.smtt.utils.FileUtil.a((java.io.File) r7, (boolean) r3)     // Catch:{ Exception -> 0x04f2 }
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r22)     // Catch:{ Exception -> 0x04f2 }
            r4 = -534(0xfffffffffffffdea, float:NaN)
            r3.setInstallInterruptCode(r4)     // Catch:{ Exception -> 0x04f2 }
            com.tencent.smtt.sdk.TbsLogReport r3 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r23)     // Catch:{ Exception -> 0x04f2 }
            r4 = 212(0xd4, float:2.97E-43)
            java.lang.String r5 = "copy fail!"
            r3.setInstallErrorCode((int) r4, (java.lang.String) r5)     // Catch:{ Exception -> 0x04f2 }
            goto L_0x055b
        L_0x04db:
            if (r6 != 0) goto L_0x04f5
            com.tencent.smtt.sdk.TbsLogReport r3 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r23)     // Catch:{ Exception -> 0x04f2 }
            java.lang.String r4 = "src-dir is null when copying tbs core!"
            r5 = 213(0xd5, float:2.98E-43)
            r3.setInstallErrorCode((int) r5, (java.lang.String) r4)     // Catch:{ Exception -> 0x04f2 }
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r22)     // Catch:{ Exception -> 0x04f2 }
            r4 = -535(0xfffffffffffffde9, float:NaN)
            r3.setInstallInterruptCode(r4)     // Catch:{ Exception -> 0x04f2 }
            goto L_0x04f5
        L_0x04f2:
            r0 = move-exception
            r3 = r0
            goto L_0x0512
        L_0x04f5:
            if (r7 != 0) goto L_0x055b
            com.tencent.smtt.sdk.TbsLogReport r3 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r23)     // Catch:{ Exception -> 0x04f2 }
            r4 = 214(0xd6, float:3.0E-43)
            java.lang.String r5 = "dst-dir is null when copying tbs core!"
            r3.setInstallErrorCode((int) r4, (java.lang.String) r5)     // Catch:{ Exception -> 0x04f2 }
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r22)     // Catch:{ Exception -> 0x04f2 }
            r4 = -536(0xfffffffffffffde8, float:NaN)
            r3.setInstallInterruptCode(r4)     // Catch:{ Exception -> 0x04f2 }
            goto L_0x055b
        L_0x050c:
            r0 = move-exception
            r2 = r0
            goto L_0x0569
        L_0x050f:
            r0 = move-exception
            r3 = r0
            r7 = 0
        L_0x0512:
            com.tencent.smtt.sdk.TbsLogReport r4 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r23)     // Catch:{ all -> 0x050c }
            r5 = 215(0xd7, float:3.01E-43)
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x050c }
            r4.setInstallErrorCode((int) r5, (java.lang.String) r3)     // Catch:{ all -> 0x050c }
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r22)     // Catch:{ all -> 0x050c }
            r4 = -537(0xfffffffffffffde7, float:NaN)
            r3.setInstallInterruptCode(r4)     // Catch:{ all -> 0x050c }
            r3 = 0
            com.tencent.smtt.utils.FileUtil.a((java.io.File) r7, (boolean) r3)     // Catch:{ Exception -> 0x0535 }
            com.tencent.smtt.sdk.m r2 = com.tencent.smtt.sdk.m.a((android.content.Context) r23)     // Catch:{ Exception -> 0x0535 }
            r4 = -1
            r2.a((int) r3, (int) r4)     // Catch:{ Exception -> 0x0535 }
            goto L_0x055b
        L_0x0535:
            r0 = move-exception
            r2 = r0
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x050c }
            r3.<init>()     // Catch:{ all -> 0x050c }
            java.lang.String r4 = "[TbsInstaller-copyTbsCoreInThread] delete dstTmpDir throws exception:"
            r3.append(r4)     // Catch:{ all -> 0x050c }
            java.lang.String r4 = r2.getMessage()     // Catch:{ all -> 0x050c }
            r3.append(r4)     // Catch:{ all -> 0x050c }
            java.lang.String r4 = ","
            r3.append(r4)     // Catch:{ all -> 0x050c }
            java.lang.Throwable r2 = r2.getCause()     // Catch:{ all -> 0x050c }
            r3.append(r2)     // Catch:{ all -> 0x050c }
            java.lang.String r2 = r3.toString()     // Catch:{ all -> 0x050c }
            com.tencent.smtt.utils.TbsLog.e(r8, r2)     // Catch:{ all -> 0x050c }
        L_0x055b:
            java.util.concurrent.locks.ReentrantLock r2 = i
            r2.unlock()
            java.util.concurrent.locks.Lock r2 = j
            r2.unlock()
            r21.b()
            goto L_0x0583
        L_0x0569:
            java.util.concurrent.locks.ReentrantLock r3 = i
            r3.unlock()
            java.util.concurrent.locks.Lock r3 = j
            r3.unlock()
            r21.b()
            throw r2
        L_0x0577:
            r21.b()
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r22)
            r3 = -538(0xfffffffffffffde6, float:NaN)
            r2.setInstallInterruptCode(r3)
        L_0x0583:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.o.a(android.content.Context, android.content.Context, int):void");
    }

    private boolean a(Context context, File file) {
        return a(context, file, false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:66:0x016a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:?, code lost:
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17).setInstallInterruptCode(-523);
        com.tencent.smtt.sdk.TbsLogReport.getInstance(r17).setInstallErrorCode((int) com.tencent.smtt.sdk.TbsListener.ErrorCode.UNZIP_IO_ERROR, (java.lang.Throwable) r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0184, code lost:
        r10 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:?, code lost:
        com.tencent.smtt.utils.FileUtil.b(r8);
        com.tencent.smtt.utils.TbsLog.e("TbsInstaller", "copyFileIfChanged -- delete tmpTbsCoreUnzipDir#2! exist:" + r8.exists());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x01a3, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x01a4, code lost:
        com.tencent.smtt.utils.TbsLog.e("TbsInstaller", "copyFileIfChanged -- delete tmpTbsCoreUnzipDir#2! exception:" + android.util.Log.getStackTraceString(r0));
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x016a A[ExcHandler: IOException (r0v3 'e' java.io.IOException A[CUSTOM_DECLARE]), Splitter:B:24:0x008f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean a(android.content.Context r17, java.io.File r18, boolean r19) {
        /*
            r16 = this;
            r1 = r16
            r2 = r17
            java.lang.String r3 = "copyFileIfChanged -- delete tmpTbsCoreUnzipDir#2! exist:"
            java.lang.String r4 = "copyFileIfChanged -- delete tmpTbsCoreUnzipDir#2! exception:"
            java.lang.String r5 = "TbsInstaller-unzipTbs done"
            java.lang.String r6 = "TbsInstaller"
            java.lang.String r0 = "TbsInstaller-unzipTbs start"
            com.tencent.smtt.utils.TbsLog.i(r6, r0)
            boolean r0 = com.tencent.smtt.utils.FileUtil.c((java.io.File) r18)
            r7 = 0
            if (r0 != 0) goto L_0x002d
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r17)
            r3 = 204(0xcc, float:2.86E-43)
            java.lang.String r4 = "apk is invalid!"
            r0.setInstallErrorCode((int) r3, (java.lang.String) r4)
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)
            r2 = -520(0xfffffffffffffdf8, float:NaN)
        L_0x0029:
            r0.setInstallInterruptCode(r2)
            return r7
        L_0x002d:
            java.io.File r0 = com.tencent.smtt.sdk.QbSdk.getTbsFolderDir(r17)     // Catch:{ all -> 0x0052 }
            if (r19 == 0) goto L_0x003b
            java.io.File r8 = new java.io.File     // Catch:{ all -> 0x0052 }
            java.lang.String r9 = "core_share_decouple"
            r8.<init>(r0, r9)     // Catch:{ all -> 0x0052 }
            goto L_0x0042
        L_0x003b:
            java.io.File r8 = new java.io.File     // Catch:{ all -> 0x0052 }
            java.lang.String r9 = "core_unzip_tmp"
            r8.<init>(r0, r9)     // Catch:{ all -> 0x0052 }
        L_0x0042:
            boolean r0 = r8.exists()     // Catch:{ all -> 0x0052 }
            if (r0 == 0) goto L_0x006b
            boolean r0 = com.tencent.smtt.sdk.TbsDownloader.a((android.content.Context) r17)     // Catch:{ all -> 0x0052 }
            if (r0 != 0) goto L_0x006b
            com.tencent.smtt.utils.FileUtil.b((java.io.File) r8)     // Catch:{ all -> 0x0052 }
            goto L_0x006b
        L_0x0052:
            r0 = move-exception
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "TbsInstaller-unzipTbs -- delete unzip folder if exists exception"
            r8.append(r9)
            java.lang.String r0 = android.util.Log.getStackTraceString(r0)
            r8.append(r0)
            java.lang.String r0 = r8.toString()
            com.tencent.smtt.utils.TbsLog.e(r6, r0)
        L_0x006b:
            if (r19 == 0) goto L_0x0073
            r0 = 2
            java.io.File r0 = r1.f((android.content.Context) r2, (int) r0)
            goto L_0x0077
        L_0x0073:
            java.io.File r0 = r1.f((android.content.Context) r2, (int) r7)
        L_0x0077:
            r8 = r0
            if (r8 != 0) goto L_0x008c
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r17)
            r3 = 205(0xcd, float:2.87E-43)
            java.lang.String r4 = "tmp unzip dir is null!"
            r0.setInstallErrorCode((int) r3, (java.lang.String) r4)
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)
            r2 = -521(0xfffffffffffffdf7, float:NaN)
            goto L_0x0029
        L_0x008c:
            r9 = -523(0xfffffffffffffdf5, float:NaN)
            r10 = 1
            com.tencent.smtt.utils.FileUtil.a((java.io.File) r8)     // Catch:{ IOException -> 0x016a, Exception -> 0x0116 }
            if (r19 == 0) goto L_0x0097
            com.tencent.smtt.utils.FileUtil.a((java.io.File) r8, (boolean) r10)     // Catch:{ IOException -> 0x016a, Exception -> 0x0116 }
        L_0x0097:
            r11 = r18
            boolean r0 = com.tencent.smtt.utils.FileUtil.a((java.io.File) r11, (java.io.File) r8)     // Catch:{ IOException -> 0x016a, Exception -> 0x0116 }
            if (r0 == 0) goto L_0x00a3
            boolean r0 = r1.a((java.io.File) r8, (android.content.Context) r2)     // Catch:{ IOException -> 0x016a, Exception -> 0x0116 }
        L_0x00a3:
            if (r19 == 0) goto L_0x00d6
            java.lang.String[] r11 = r8.list()     // Catch:{ IOException -> 0x016a, Exception -> 0x0116 }
            r12 = 0
        L_0x00aa:
            int r13 = r11.length     // Catch:{ IOException -> 0x016a, Exception -> 0x0116 }
            if (r12 >= r13) goto L_0x00c6
            java.io.File r13 = new java.io.File     // Catch:{ IOException -> 0x016a, Exception -> 0x0116 }
            r14 = r11[r12]     // Catch:{ IOException -> 0x016a, Exception -> 0x0116 }
            r13.<init>(r8, r14)     // Catch:{ IOException -> 0x016a, Exception -> 0x0116 }
            java.lang.String r14 = r13.getName()     // Catch:{ IOException -> 0x016a, Exception -> 0x0116 }
            java.lang.String r15 = ".dex"
            boolean r14 = r14.endsWith(r15)     // Catch:{ IOException -> 0x016a, Exception -> 0x0116 }
            if (r14 == 0) goto L_0x00c3
            r13.delete()     // Catch:{ IOException -> 0x016a, Exception -> 0x0116 }
        L_0x00c3:
            int r12 = r12 + 1
            goto L_0x00aa
        L_0x00c6:
            java.io.File r11 = t(r17)     // Catch:{ Exception -> 0x00d5, IOException -> 0x016a }
            java.io.File r12 = new java.io.File     // Catch:{ Exception -> 0x00d5, IOException -> 0x016a }
            java.lang.String r13 = "x5.tbs"
            r12.<init>(r11, r13)     // Catch:{ Exception -> 0x00d5, IOException -> 0x016a }
            r12.delete()     // Catch:{ Exception -> 0x00d5, IOException -> 0x016a }
            goto L_0x00d6
        L_0x00d5:
        L_0x00d6:
            if (r0 != 0) goto L_0x00fd
            com.tencent.smtt.utils.FileUtil.b((java.io.File) r8)     // Catch:{ IOException -> 0x016a, Exception -> 0x0116 }
            com.tencent.smtt.sdk.TbsDownloadConfig r11 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)     // Catch:{ IOException -> 0x016a, Exception -> 0x0116 }
            r12 = -522(0xfffffffffffffdf6, float:NaN)
            r11.setInstallInterruptCode(r12)     // Catch:{ IOException -> 0x016a, Exception -> 0x0116 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x016a, Exception -> 0x0116 }
            r11.<init>()     // Catch:{ IOException -> 0x016a, Exception -> 0x0116 }
            java.lang.String r12 = "copyFileIfChanged -- delete tmpTbsCoreUnzipDir#1! exist:"
            r11.append(r12)     // Catch:{ IOException -> 0x016a, Exception -> 0x0116 }
            boolean r12 = r8.exists()     // Catch:{ IOException -> 0x016a, Exception -> 0x0116 }
            r11.append(r12)     // Catch:{ IOException -> 0x016a, Exception -> 0x0116 }
            java.lang.String r11 = r11.toString()     // Catch:{ IOException -> 0x016a, Exception -> 0x0116 }
            com.tencent.smtt.utils.TbsLog.e(r6, r11)     // Catch:{ IOException -> 0x016a, Exception -> 0x0116 }
            goto L_0x010f
        L_0x00fd:
            r1.g((android.content.Context) r2, (boolean) r10)     // Catch:{ IOException -> 0x016a, Exception -> 0x0116 }
            if (r19 == 0) goto L_0x010f
            java.io.File r11 = r16.q(r17)     // Catch:{ IOException -> 0x016a, Exception -> 0x0116 }
            com.tencent.smtt.utils.FileUtil.a((java.io.File) r11, (boolean) r10)     // Catch:{ IOException -> 0x016a, Exception -> 0x0116 }
            r8.renameTo(r11)     // Catch:{ IOException -> 0x016a, Exception -> 0x0116 }
            com.tencent.smtt.sdk.TbsShareManager.b(r17)     // Catch:{ IOException -> 0x016a, Exception -> 0x0116 }
        L_0x010f:
            com.tencent.smtt.utils.TbsLog.i(r6, r5)
            return r0
        L_0x0113:
            r0 = move-exception
            goto L_0x01be
        L_0x0116:
            r0 = move-exception
            com.tencent.smtt.sdk.TbsDownloadConfig r11 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)     // Catch:{ all -> 0x0113 }
            r11.setInstallInterruptCode(r9)     // Catch:{ all -> 0x0113 }
            com.tencent.smtt.sdk.TbsLogReport r2 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r17)     // Catch:{ all -> 0x0113 }
            r9 = 207(0xcf, float:2.9E-43)
            r2.setInstallErrorCode((int) r9, (java.lang.Throwable) r0)     // Catch:{ all -> 0x0113 }
            if (r8 == 0) goto L_0x0130
            boolean r0 = r8.exists()     // Catch:{ all -> 0x0113 }
            if (r0 == 0) goto L_0x0130
            goto L_0x0131
        L_0x0130:
            r10 = 0
        L_0x0131:
            if (r10 == 0) goto L_0x0166
            if (r8 == 0) goto L_0x0166
            com.tencent.smtt.utils.FileUtil.b((java.io.File) r8)     // Catch:{ all -> 0x014f }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x014f }
            r0.<init>()     // Catch:{ all -> 0x014f }
            r0.append(r3)     // Catch:{ all -> 0x014f }
            boolean r2 = r8.exists()     // Catch:{ all -> 0x014f }
            r0.append(r2)     // Catch:{ all -> 0x014f }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x014f }
            com.tencent.smtt.utils.TbsLog.e(r6, r0)     // Catch:{ all -> 0x014f }
            goto L_0x0166
        L_0x014f:
            r0 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r4)
            java.lang.String r0 = android.util.Log.getStackTraceString(r0)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            com.tencent.smtt.utils.TbsLog.e(r6, r0)
        L_0x0166:
            com.tencent.smtt.utils.TbsLog.i(r6, r5)
            return r7
        L_0x016a:
            r0 = move-exception
            com.tencent.smtt.sdk.TbsDownloadConfig r11 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)     // Catch:{ all -> 0x0113 }
            r11.setInstallInterruptCode(r9)     // Catch:{ all -> 0x0113 }
            com.tencent.smtt.sdk.TbsLogReport r2 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r17)     // Catch:{ all -> 0x0113 }
            r9 = 206(0xce, float:2.89E-43)
            r2.setInstallErrorCode((int) r9, (java.lang.Throwable) r0)     // Catch:{ all -> 0x0113 }
            if (r8 == 0) goto L_0x0184
            boolean r0 = r8.exists()     // Catch:{ all -> 0x0113 }
            if (r0 == 0) goto L_0x0184
            goto L_0x0185
        L_0x0184:
            r10 = 0
        L_0x0185:
            if (r10 == 0) goto L_0x01ba
            if (r8 == 0) goto L_0x01ba
            com.tencent.smtt.utils.FileUtil.b((java.io.File) r8)     // Catch:{ all -> 0x01a3 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x01a3 }
            r0.<init>()     // Catch:{ all -> 0x01a3 }
            r0.append(r3)     // Catch:{ all -> 0x01a3 }
            boolean r2 = r8.exists()     // Catch:{ all -> 0x01a3 }
            r0.append(r2)     // Catch:{ all -> 0x01a3 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x01a3 }
            com.tencent.smtt.utils.TbsLog.e(r6, r0)     // Catch:{ all -> 0x01a3 }
            goto L_0x01ba
        L_0x01a3:
            r0 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r4)
            java.lang.String r0 = android.util.Log.getStackTraceString(r0)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            com.tencent.smtt.utils.TbsLog.e(r6, r0)
        L_0x01ba:
            com.tencent.smtt.utils.TbsLog.i(r6, r5)
            return r7
        L_0x01be:
            com.tencent.smtt.utils.TbsLog.i(r6, r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.o.a(android.content.Context, java.io.File, boolean):boolean");
    }

    static boolean a(Context context, String str) {
        String str2;
        File file = new File(QbSdk.getTbsFolderDir(context), str);
        if (!file.exists()) {
            str2 = "TbsInstaller-isPrepareTbsCore, #1";
        } else if (!new File(file, "tbs.conf").exists()) {
            str2 = "TbsInstaller-isPrepareTbsCore, #2";
        } else {
            TbsLog.i("TbsInstaller", "TbsInstaller-isPrepareTbsCore, #3");
            return true;
        }
        TbsLog.i("TbsInstaller", str2);
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0051, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0052, code lost:
        r11 = r4;
        r4 = null;
        r14 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0056, code lost:
        r13 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0066, code lost:
        r14 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0067, code lost:
        r14.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:?, code lost:
        r14.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0156, code lost:
        r14 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0157, code lost:
        r14.printStackTrace();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0056 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:1:0x0023] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0062 A[SYNTHETIC, Splitter:B:25:0x0062] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0140 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0152 A[SYNTHETIC, Splitter:B:63:0x0152] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean a(java.io.File r13, android.content.Context r14) {
        /*
            r12 = this;
            java.lang.String r0 = "1"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "finalCheckForTbsCoreValidity - "
            r1.append(r2)
            r1.append(r13)
            java.lang.String r2 = ", "
            r1.append(r2)
            r1.append(r14)
            java.lang.String r14 = r1.toString()
            java.lang.String r1 = "TbsInstaller"
            com.tencent.smtt.utils.TbsLog.i(r1, r14)
            r14 = 0
            r2 = 0
            r3 = 1
            java.io.File r4 = new java.io.File     // Catch:{ Exception -> 0x0059, all -> 0x0056 }
            r4.<init>(r13, r0)     // Catch:{ Exception -> 0x0059, all -> 0x0056 }
            java.util.Properties r5 = new java.util.Properties     // Catch:{ Exception -> 0x0059, all -> 0x0056 }
            r5.<init>()     // Catch:{ Exception -> 0x0059, all -> 0x0056 }
            boolean r6 = r4.exists()     // Catch:{ Exception -> 0x0051, all -> 0x0056 }
            if (r6 == 0) goto L_0x0045
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0051, all -> 0x0056 }
            r6.<init>(r4)     // Catch:{ Exception -> 0x0051, all -> 0x0056 }
            java.io.BufferedInputStream r4 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x0051, all -> 0x0056 }
            r4.<init>(r6)     // Catch:{ Exception -> 0x0051, all -> 0x0056 }
            r5.load(r4)     // Catch:{ Exception -> 0x0043 }
            r14 = r4
            r4 = 1
            goto L_0x0046
        L_0x0043:
            r14 = move-exception
            goto L_0x005d
        L_0x0045:
            r4 = 0
        L_0x0046:
            if (r14 == 0) goto L_0x006b
            r14.close()     // Catch:{ IOException -> 0x004c }
            goto L_0x006b
        L_0x004c:
            r14 = move-exception
            r14.printStackTrace()
            goto L_0x006b
        L_0x0051:
            r4 = move-exception
            r11 = r4
            r4 = r14
            r14 = r11
            goto L_0x005d
        L_0x0056:
            r13 = move-exception
            goto L_0x0150
        L_0x0059:
            r4 = move-exception
            r5 = r14
            r14 = r4
            r4 = r5
        L_0x005d:
            r14.printStackTrace()     // Catch:{ all -> 0x014e }
            if (r4 == 0) goto L_0x006a
            r4.close()     // Catch:{ IOException -> 0x0066 }
            goto L_0x006a
        L_0x0066:
            r14 = move-exception
            r14.printStackTrace()
        L_0x006a:
            r4 = 1
        L_0x006b:
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r6 = "finalCheckForTbsCoreValidity - need_check:"
            r14.append(r6)
            r14.append(r4)
            java.lang.String r14 = r14.toString()
            com.tencent.smtt.utils.TbsLog.i(r1, r14)
            if (r4 == 0) goto L_0x0129
            java.io.File[] r13 = r13.listFiles()
            r14 = 0
        L_0x0086:
            int r6 = r13.length
            if (r14 >= r6) goto L_0x0129
            r6 = r13[r14]
            java.lang.String r7 = r6.getName()
            boolean r7 = r0.equals(r7)
            if (r7 != 0) goto L_0x0125
            java.lang.String r7 = r6.getName()
            java.lang.String r8 = ".dex"
            boolean r7 = r7.endsWith(r8)
            if (r7 != 0) goto L_0x0125
            java.lang.String r7 = r6.getName()
            java.lang.String r8 = "tbs.conf"
            boolean r7 = r8.equals(r7)
            if (r7 != 0) goto L_0x0125
            boolean r7 = r6.isDirectory()
            if (r7 != 0) goto L_0x0125
            java.lang.String r7 = r6.getName()
            java.lang.String r8 = ".prof"
            boolean r7 = r7.endsWith(r8)
            if (r7 == 0) goto L_0x00c0
            goto L_0x0125
        L_0x00c0:
            java.lang.String r7 = com.tencent.smtt.utils.a.a((java.io.File) r6)
            java.lang.String r8 = r6.getName()
            java.lang.String r9 = ""
            java.lang.String r8 = r5.getProperty(r8, r9)
            boolean r9 = r8.equals(r9)
            java.lang.String r10 = ")"
            if (r9 != 0) goto L_0x00f8
            boolean r9 = r8.equals(r7)
            if (r9 == 0) goto L_0x00f8
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "md5_check_success for ("
            r7.append(r8)
            java.lang.String r6 = r6.getName()
            r7.append(r6)
            r7.append(r10)
            java.lang.String r6 = r7.toString()
            com.tencent.smtt.utils.TbsLog.i(r1, r6)
            goto L_0x0125
        L_0x00f8:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.lang.String r14 = "md5_check_failure for ("
            r13.append(r14)
            java.lang.String r14 = r6.getName()
            r13.append(r14)
            r13.append(r10)
            java.lang.String r14 = " targetMd5:"
            r13.append(r14)
            r13.append(r8)
            java.lang.String r14 = ", realMd5:"
            r13.append(r14)
            r13.append(r7)
            java.lang.String r13 = r13.toString()
            com.tencent.smtt.utils.TbsLog.e(r1, r13)
            r13 = 0
            goto L_0x012a
        L_0x0125:
            int r14 = r14 + 1
            goto L_0x0086
        L_0x0129:
            r13 = 1
        L_0x012a:
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r0 = "finalCheckForTbsCoreValidity - md5_check_success:"
            r14.append(r0)
            r14.append(r13)
            java.lang.String r14 = r14.toString()
            com.tencent.smtt.utils.TbsLog.i(r1, r14)
            if (r4 == 0) goto L_0x0148
            if (r13 != 0) goto L_0x0148
            java.lang.String r13 = "finalCheckForTbsCoreValidity - Verify failed after unzipping!"
            com.tencent.smtt.utils.TbsLog.e(r1, r13)
            return r2
        L_0x0148:
            java.lang.String r13 = "finalCheckForTbsCoreValidity success!"
            com.tencent.smtt.utils.TbsLog.i(r1, r13)
            return r3
        L_0x014e:
            r13 = move-exception
            r14 = r4
        L_0x0150:
            if (r14 == 0) goto L_0x015a
            r14.close()     // Catch:{ IOException -> 0x0156 }
            goto L_0x015a
        L_0x0156:
            r14 = move-exception
            r14.printStackTrace()
        L_0x015a:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.o.a(java.io.File, android.content.Context):boolean");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x04c1 A[SYNTHETIC, Splitter:B:163:0x04c1] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:189:0x0524=Splitter:B:189:0x0524, B:74:0x02da=Splitter:B:74:0x02da} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(android.content.Context r17, java.lang.String r18, int r19) {
        /*
            r16 = this;
            r1 = r16
            r2 = r17
            r3 = r18
            r4 = r19
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)
            r5 = -501(0xfffffffffffffe0b, float:NaN)
            r0.setInstallInterruptCode(r5)
            boolean r0 = r16.d(r17)
            r5 = 1
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r5)
            java.lang.String r7 = "TbsInstaller"
            if (r0 == 0) goto L_0x0032
            java.lang.String r0 = "isTbsLocalInstalled --> no installation!"
            com.tencent.smtt.utils.TbsLog.i(r7, r0, r5)
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)
            r2 = -502(0xfffffffffffffe0a, float:NaN)
            r0.setInstallInterruptCode(r2)
            com.tencent.smtt.sdk.TbsListener r0 = com.tencent.smtt.sdk.QbSdk.m
            r0.onInstallFinish(r2)
            return
        L_0x0032:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r8 = "TbsInstaller-installTbsCoreInThread tbsApkPath="
            r0.append(r8)
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            com.tencent.smtt.utils.TbsLog.i(r7, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r8 = "TbsInstaller-installTbsCoreInThread tbsCoreTargetVer="
            r0.append(r8)
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            com.tencent.smtt.utils.TbsLog.i(r7, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r8 = "TbsInstaller-continueInstallTbsCore currentProcessName="
            r0.append(r8)
            android.content.pm.ApplicationInfo r8 = r17.getApplicationInfo()
            java.lang.String r8 = r8.processName
            r0.append(r8)
            java.lang.String r0 = r0.toString()
            com.tencent.smtt.utils.TbsLog.i(r7, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r8 = "TbsInstaller-installTbsCoreInThread currentProcessId="
            r0.append(r8)
            int r8 = android.os.Process.myPid()
            r0.append(r8)
            java.lang.String r0 = r0.toString()
            com.tencent.smtt.utils.TbsLog.i(r7, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r8 = "TbsInstaller-installTbsCoreInThread currentThreadName="
            r0.append(r8)
            java.lang.Thread r8 = java.lang.Thread.currentThread()
            java.lang.String r8 = r8.getName()
            r0.append(r8)
            java.lang.String r0 = r0.toString()
            com.tencent.smtt.utils.TbsLog.i(r7, r0)
            int r0 = android.os.Build.VERSION.SDK_INT
            r8 = 4
            r9 = 11
            java.lang.String r10 = "tbs_preloadx5_check_cfg_file"
            r11 = 0
            if (r0 < r9) goto L_0x00b7
            android.content.SharedPreferences r0 = r2.getSharedPreferences(r10, r8)
            goto L_0x00bb
        L_0x00b7:
            android.content.SharedPreferences r0 = r2.getSharedPreferences(r10, r11)
        L_0x00bb:
            java.lang.String r12 = "tbs_precheck_disable_version"
            r13 = -1
            int r0 = r0.getInt(r12, r13)
            if (r0 != r4) goto L_0x00ec
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r3 = "TbsInstaller-installTbsCoreInThread -- version:"
            r0.append(r3)
            r0.append(r4)
            java.lang.String r3 = " is disabled by preload_x5_check!"
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            com.tencent.smtt.utils.TbsLog.e(r7, r0)
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)
            r2 = -503(0xfffffffffffffe09, float:NaN)
            r0.setInstallInterruptCode(r2)
            com.tencent.smtt.sdk.TbsListener r0 = com.tencent.smtt.sdk.QbSdk.m
            r0.onInstallFinish(r2)
            return
        L_0x00ec:
            boolean r0 = com.tencent.smtt.utils.FileUtil.b((android.content.Context) r17)
            if (r0 != 0) goto L_0x0132
            long r3 = com.tencent.smtt.utils.p.a()
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)
            long r5 = r0.getDownloadMinFreeSpace()
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)
            r8 = -504(0xfffffffffffffe08, float:NaN)
            r0.setInstallInterruptCode(r8)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r8 = "rom is not enough when installing tbs core! curAvailROM="
            r0.append(r8)
            r0.append(r3)
            java.lang.String r3 = ",minReqRom="
            r0.append(r3)
            r0.append(r5)
            java.lang.String r0 = r0.toString()
            com.tencent.smtt.sdk.TbsLogReport r2 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r17)
            r3 = 210(0xd2, float:2.94E-43)
            r2.setInstallErrorCode((int) r3, (java.lang.String) r0)
            com.tencent.smtt.utils.TbsLog.i(r7, r0)
            com.tencent.smtt.sdk.TbsListener r0 = com.tencent.smtt.sdk.QbSdk.m
            r0.onInstallFinish(r3)
            return
        L_0x0132:
            boolean r0 = r16.u(r17)
            if (r0 != 0) goto L_0x014c
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)
            r2 = -505(0xfffffffffffffe07, float:NaN)
            r0.setInstallInterruptCode(r2)
            java.lang.String r0 = "getInstalling file lock failed,return!"
            com.tencent.smtt.utils.TbsLog.i(r7, r0)
            com.tencent.smtt.sdk.TbsListener r0 = com.tencent.smtt.sdk.QbSdk.m
            r0.onInstallFinish(r2)
            return
        L_0x014c:
            java.util.concurrent.locks.Lock r0 = j
            boolean r0 = r0.tryLock()
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r14 = "TbsInstaller-installTbsCoreInThread locked ="
            r12.append(r14)
            r12.append(r0)
            java.lang.String r12 = r12.toString()
            com.tencent.smtt.utils.TbsLog.i(r7, r12)
            if (r0 == 0) goto L_0x068b
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)
            r12 = -507(0xfffffffffffffe05, float:NaN)
            r0.setInstallInterruptCode(r12)
            java.util.concurrent.locks.ReentrantLock r0 = i
            r0.lock()
            com.tencent.smtt.sdk.m r0 = com.tencent.smtt.sdk.m.a((android.content.Context) r17)     // Catch:{ all -> 0x0670 }
            java.lang.String r12 = "copy_core_ver"
            int r0 = r0.c((java.lang.String) r12)     // Catch:{ all -> 0x0670 }
            com.tencent.smtt.sdk.m r12 = com.tencent.smtt.sdk.m.a((android.content.Context) r17)     // Catch:{ all -> 0x0670 }
            int r12 = r12.b()     // Catch:{ all -> 0x0670 }
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ all -> 0x0670 }
            r14.<init>()     // Catch:{ all -> 0x0670 }
            java.lang.String r15 = "TbsInstaller-installTbsCoreInThread tbsCoreCopyVer ="
            r14.append(r15)     // Catch:{ all -> 0x0670 }
            r14.append(r0)     // Catch:{ all -> 0x0670 }
            java.lang.String r14 = r14.toString()     // Catch:{ all -> 0x0670 }
            com.tencent.smtt.utils.TbsLog.i(r7, r14)     // Catch:{ all -> 0x0670 }
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ all -> 0x0670 }
            r14.<init>()     // Catch:{ all -> 0x0670 }
            java.lang.String r15 = "TbsInstaller-installTbsCoreInThread tbsCoreInstallVer ="
            r14.append(r15)     // Catch:{ all -> 0x0670 }
            r14.append(r12)     // Catch:{ all -> 0x0670 }
            java.lang.String r14 = r14.toString()     // Catch:{ all -> 0x0670 }
            com.tencent.smtt.utils.TbsLog.i(r7, r14)     // Catch:{ all -> 0x0670 }
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ all -> 0x0670 }
            r14.<init>()     // Catch:{ all -> 0x0670 }
            java.lang.String r15 = "TbsInstaller-installTbsCoreInThread tbsCoreTargetVer ="
            r14.append(r15)     // Catch:{ all -> 0x0670 }
            r14.append(r4)     // Catch:{ all -> 0x0670 }
            java.lang.String r14 = r14.toString()     // Catch:{ all -> 0x0670 }
            com.tencent.smtt.utils.TbsLog.i(r7, r14)     // Catch:{ all -> 0x0670 }
            if (r12 <= 0) goto L_0x01c8
            if (r4 > r12) goto L_0x01cc
        L_0x01c8:
            if (r0 <= 0) goto L_0x01cf
            if (r4 <= r0) goto L_0x01cf
        L_0x01cc:
            r16.p(r17)     // Catch:{ all -> 0x0670 }
        L_0x01cf:
            com.tencent.smtt.sdk.m r0 = com.tencent.smtt.sdk.m.a((android.content.Context) r17)     // Catch:{ all -> 0x0670 }
            int r0 = r0.c()     // Catch:{ all -> 0x0670 }
            int r12 = r16.j(r17)     // Catch:{ all -> 0x0670 }
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ all -> 0x0670 }
            r14.<init>()     // Catch:{ all -> 0x0670 }
            java.lang.String r15 = "TbsInstaller-installTbsCoreInThread installStatus1="
            r14.append(r15)     // Catch:{ all -> 0x0670 }
            r14.append(r0)     // Catch:{ all -> 0x0670 }
            java.lang.String r14 = r14.toString()     // Catch:{ all -> 0x0670 }
            com.tencent.smtt.utils.TbsLog.i(r7, r14)     // Catch:{ all -> 0x0670 }
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ all -> 0x0670 }
            r14.<init>()     // Catch:{ all -> 0x0670 }
            java.lang.String r15 = "TbsInstaller-installTbsCoreInThread tbsCoreInstalledVer="
            r14.append(r15)     // Catch:{ all -> 0x0670 }
            r14.append(r12)     // Catch:{ all -> 0x0670 }
            java.lang.String r14 = r14.toString()     // Catch:{ all -> 0x0670 }
            com.tencent.smtt.utils.TbsLog.i(r7, r14)     // Catch:{ all -> 0x0670 }
            r14 = 88888888(0x54c5638, float:9.60787E-36)
            r15 = 2
            if (r0 < 0) goto L_0x0213
            if (r0 >= r15) goto L_0x0213
            java.lang.String r12 = "TbsInstaller-installTbsCoreInThread -- retry....."
            com.tencent.smtt.utils.TbsLog.i(r7, r12, r5)     // Catch:{ all -> 0x0670 }
            r13 = r0
            r0 = 1
            goto L_0x0229
        L_0x0213:
            r13 = 3
            if (r0 != r13) goto L_0x0227
            if (r12 < 0) goto L_0x0227
            if (r4 > r12) goto L_0x021c
            if (r4 != r14) goto L_0x0227
        L_0x021c:
            r16.p(r17)     // Catch:{ all -> 0x0670 }
            java.lang.String r0 = "TbsInstaller-installTbsCoreInThread -- update TBS....."
            com.tencent.smtt.utils.TbsLog.i(r7, r0, r5)     // Catch:{ all -> 0x0670 }
            r0 = 0
            r13 = -1
            goto L_0x0229
        L_0x0227:
            r13 = r0
            r0 = 0
        L_0x0229:
            com.tencent.smtt.sdk.TbsDownloadConfig r12 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)     // Catch:{ all -> 0x0670 }
            r14 = -508(0xfffffffffffffe04, float:NaN)
            r12.setInstallInterruptCode(r14)     // Catch:{ all -> 0x0670 }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x0670 }
            r12.<init>()     // Catch:{ all -> 0x0670 }
            java.lang.String r14 = "TbsInstaller-installTbsCoreInThread installStatus2="
            r12.append(r14)     // Catch:{ all -> 0x0670 }
            r12.append(r13)     // Catch:{ all -> 0x0670 }
            java.lang.String r12 = r12.toString()     // Catch:{ all -> 0x0670 }
            com.tencent.smtt.utils.TbsLog.i(r7, r12)     // Catch:{ all -> 0x0670 }
            java.lang.String r14 = "apk path is null!"
            r8 = 202(0xca, float:2.83E-43)
            r9 = 10
            java.lang.String r15 = "install_apk_path"
            if (r13 >= r5) goto L_0x04e8
            java.lang.String r11 = "STEP 2/2 begin installation....."
            com.tencent.smtt.utils.TbsLog.i(r7, r11, r5)     // Catch:{ all -> 0x0670 }
            com.tencent.smtt.sdk.TbsDownloadConfig r11 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)     // Catch:{ all -> 0x0670 }
            r12 = -509(0xfffffffffffffe03, float:NaN)
            r11.setInstallInterruptCode(r12)     // Catch:{ all -> 0x0670 }
            if (r0 == 0) goto L_0x02a4
            com.tencent.smtt.sdk.m r11 = com.tencent.smtt.sdk.m.a((android.content.Context) r17)     // Catch:{ all -> 0x0670 }
            java.lang.String r12 = "unzip_retry_num"
            int r11 = r11.c((java.lang.String) r12)     // Catch:{ all -> 0x0670 }
            if (r11 <= r9) goto L_0x029c
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r17)     // Catch:{ all -> 0x0670 }
            r3 = 201(0xc9, float:2.82E-43)
            java.lang.String r4 = "exceed unzip retry num!"
            r0.setInstallErrorCode((int) r3, (java.lang.String) r4)     // Catch:{ all -> 0x0670 }
            r16.F(r17)     // Catch:{ all -> 0x0670 }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)     // Catch:{ all -> 0x0670 }
            r2 = -510(0xfffffffffffffe02, float:NaN)
            r0.setInstallInterruptCode(r2)     // Catch:{ all -> 0x0670 }
            java.util.concurrent.locks.ReentrantLock r0 = i     // Catch:{ Exception -> 0x028e }
            r0.unlock()     // Catch:{ Exception -> 0x028e }
            java.util.concurrent.locks.Lock r0 = j     // Catch:{ Exception -> 0x028e }
            r0.unlock()     // Catch:{ Exception -> 0x028e }
            goto L_0x0292
        L_0x028e:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0292:
            r16.b()     // Catch:{ Exception -> 0x0296 }
            goto L_0x029b
        L_0x0296:
            r0 = move-exception
            r2 = r0
            r2.printStackTrace()
        L_0x029b:
            return
        L_0x029c:
            com.tencent.smtt.sdk.m r12 = com.tencent.smtt.sdk.m.a((android.content.Context) r17)     // Catch:{ all -> 0x0670 }
            int r11 = r11 + r5
            r12.b((int) r11)     // Catch:{ all -> 0x0670 }
        L_0x02a4:
            if (r3 != 0) goto L_0x02d9
            com.tencent.smtt.sdk.m r11 = com.tencent.smtt.sdk.m.a((android.content.Context) r17)     // Catch:{ all -> 0x0670 }
            java.lang.String r11 = r11.d((java.lang.String) r15)     // Catch:{ all -> 0x0670 }
            if (r11 != 0) goto L_0x02da
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r17)     // Catch:{ all -> 0x0670 }
            r0.setInstallErrorCode((int) r8, (java.lang.String) r14)     // Catch:{ all -> 0x0670 }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)     // Catch:{ all -> 0x0670 }
            r2 = -511(0xfffffffffffffe01, float:NaN)
            r0.setInstallInterruptCode(r2)     // Catch:{ all -> 0x0670 }
            java.util.concurrent.locks.ReentrantLock r0 = i     // Catch:{ Exception -> 0x02cb }
            r0.unlock()     // Catch:{ Exception -> 0x02cb }
            java.util.concurrent.locks.Lock r0 = j     // Catch:{ Exception -> 0x02cb }
            r0.unlock()     // Catch:{ Exception -> 0x02cb }
            goto L_0x02cf
        L_0x02cb:
            r0 = move-exception
            r0.printStackTrace()
        L_0x02cf:
            r16.b()     // Catch:{ Exception -> 0x02d3 }
            goto L_0x02d8
        L_0x02d3:
            r0 = move-exception
            r2 = r0
            r2.printStackTrace()
        L_0x02d8:
            return
        L_0x02d9:
            r11 = r3
        L_0x02da:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x0670 }
            r8.<init>()     // Catch:{ all -> 0x0670 }
            java.lang.String r12 = "TbsInstaller-installTbsCoreInThread apkPath ="
            r8.append(r12)     // Catch:{ all -> 0x0670 }
            r8.append(r11)     // Catch:{ all -> 0x0670 }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x0670 }
            com.tencent.smtt.utils.TbsLog.i(r7, r8)     // Catch:{ all -> 0x0670 }
            int r8 = r1.c((android.content.Context) r2, (java.lang.String) r11)     // Catch:{ all -> 0x0670 }
            if (r8 != 0) goto L_0x0321
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)     // Catch:{ all -> 0x0670 }
            r3 = -512(0xfffffffffffffe00, float:NaN)
            r0.setInstallInterruptCode(r3)     // Catch:{ all -> 0x0670 }
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r17)     // Catch:{ all -> 0x0670 }
            r2 = 203(0xcb, float:2.84E-43)
            java.lang.String r3 = "apk version is 0!"
            r0.setInstallErrorCode((int) r2, (java.lang.String) r3)     // Catch:{ all -> 0x0670 }
            java.util.concurrent.locks.ReentrantLock r0 = i     // Catch:{ Exception -> 0x0313 }
            r0.unlock()     // Catch:{ Exception -> 0x0313 }
            java.util.concurrent.locks.Lock r0 = j     // Catch:{ Exception -> 0x0313 }
            r0.unlock()     // Catch:{ Exception -> 0x0313 }
            goto L_0x0317
        L_0x0313:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0317:
            r16.b()     // Catch:{ Exception -> 0x031b }
            goto L_0x0320
        L_0x031b:
            r0 = move-exception
            r2 = r0
            r2.printStackTrace()
        L_0x0320:
            return
        L_0x0321:
            com.tencent.smtt.sdk.m r12 = com.tencent.smtt.sdk.m.a((android.content.Context) r17)     // Catch:{ all -> 0x0670 }
            r12.a((java.lang.String) r15, (java.lang.String) r11)     // Catch:{ all -> 0x0670 }
            com.tencent.smtt.sdk.m r12 = com.tencent.smtt.sdk.m.a((android.content.Context) r17)     // Catch:{ all -> 0x0670 }
            r14 = 0
            r12.c(r8, r14)     // Catch:{ all -> 0x0670 }
            com.tencent.smtt.sdk.TbsDownloadConfig r12 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)     // Catch:{ all -> 0x0670 }
            r14 = -548(0xfffffffffffffddc, float:NaN)
            r12.setInstallInterruptCode(r14)     // Catch:{ all -> 0x0670 }
            boolean r12 = com.tencent.smtt.sdk.TbsDownloader.a((android.content.Context) r17)     // Catch:{ all -> 0x0670 }
            java.lang.String r14 = "unzipTbsApk failed"
            r15 = 207(0xcf, float:2.9E-43)
            if (r12 == 0) goto L_0x0370
            java.io.File r12 = new java.io.File     // Catch:{ all -> 0x0670 }
            r12.<init>(r11)     // Catch:{ all -> 0x0670 }
            boolean r11 = r1.a((android.content.Context) r2, (java.io.File) r12, (boolean) r5)     // Catch:{ all -> 0x0670 }
            if (r11 != 0) goto L_0x039b
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r17)     // Catch:{ all -> 0x0670 }
            com.tencent.smtt.sdk.TbsLogReport$EventType r2 = com.tencent.smtt.sdk.TbsLogReport.EventType.TYPE_INSTALL_DECOUPLE     // Catch:{ all -> 0x0670 }
            r0.setInstallErrorCode(r15, r14, r2)     // Catch:{ all -> 0x0670 }
            java.util.concurrent.locks.ReentrantLock r0 = i     // Catch:{ Exception -> 0x0362 }
            r0.unlock()     // Catch:{ Exception -> 0x0362 }
            java.util.concurrent.locks.Lock r0 = j     // Catch:{ Exception -> 0x0362 }
            r0.unlock()     // Catch:{ Exception -> 0x0362 }
            goto L_0x0366
        L_0x0362:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0366:
            r16.b()     // Catch:{ Exception -> 0x036a }
            goto L_0x036f
        L_0x036a:
            r0 = move-exception
            r2 = r0
            r2.printStackTrace()
        L_0x036f:
            return
        L_0x0370:
            java.io.File r12 = new java.io.File     // Catch:{ all -> 0x0670 }
            r12.<init>(r11)     // Catch:{ all -> 0x0670 }
            boolean r11 = r1.a((android.content.Context) r2, (java.io.File) r12)     // Catch:{ all -> 0x0670 }
            if (r11 != 0) goto L_0x039b
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r17)     // Catch:{ all -> 0x0670 }
            r0.setInstallErrorCode((int) r15, (java.lang.String) r14)     // Catch:{ all -> 0x0670 }
            java.util.concurrent.locks.ReentrantLock r0 = i     // Catch:{ Exception -> 0x038d }
            r0.unlock()     // Catch:{ Exception -> 0x038d }
            java.util.concurrent.locks.Lock r0 = j     // Catch:{ Exception -> 0x038d }
            r0.unlock()     // Catch:{ Exception -> 0x038d }
            goto L_0x0391
        L_0x038d:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0391:
            r16.b()     // Catch:{ Exception -> 0x0395 }
            goto L_0x039a
        L_0x0395:
            r0 = move-exception
            r2 = r0
            r2.printStackTrace()
        L_0x039a:
            return
        L_0x039b:
            if (r0 == 0) goto L_0x0402
            com.tencent.smtt.sdk.m r11 = com.tencent.smtt.sdk.m.a((android.content.Context) r17)     // Catch:{ all -> 0x0670 }
            java.lang.String r12 = "unlzma_status"
            int r11 = r11.b((java.lang.String) r12)     // Catch:{ all -> 0x0670 }
            r12 = 5
            if (r11 <= r12) goto L_0x03fa
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r17)     // Catch:{ all -> 0x0670 }
            r3 = 223(0xdf, float:3.12E-43)
            java.lang.String r4 = "exceed unlzma retry num!"
            r0.setInstallErrorCode((int) r3, (java.lang.String) r4)     // Catch:{ all -> 0x0670 }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)     // Catch:{ all -> 0x0670 }
            r3 = -553(0xfffffffffffffdd7, float:NaN)
            r0.setInstallInterruptCode(r3)     // Catch:{ all -> 0x0670 }
            r16.F(r17)     // Catch:{ all -> 0x0670 }
            com.tencent.smtt.sdk.l.c((android.content.Context) r17)     // Catch:{ all -> 0x0670 }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)     // Catch:{ all -> 0x0670 }
            java.util.Map<java.lang.String, java.lang.Object> r0 = r0.mSyncMap     // Catch:{ all -> 0x0670 }
            java.lang.String r3 = "tbs_needdownload"
            r0.put(r3, r6)     // Catch:{ all -> 0x0670 }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)     // Catch:{ all -> 0x0670 }
            java.util.Map<java.lang.String, java.lang.Object> r0 = r0.mSyncMap     // Catch:{ all -> 0x0670 }
            java.lang.String r3 = "request_full_package"
            r0.put(r3, r6)     // Catch:{ all -> 0x0670 }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)     // Catch:{ all -> 0x0670 }
            r0.commit()     // Catch:{ all -> 0x0670 }
            java.util.concurrent.locks.ReentrantLock r0 = i     // Catch:{ Exception -> 0x03ec }
            r0.unlock()     // Catch:{ Exception -> 0x03ec }
            java.util.concurrent.locks.Lock r0 = j     // Catch:{ Exception -> 0x03ec }
            r0.unlock()     // Catch:{ Exception -> 0x03ec }
            goto L_0x03f0
        L_0x03ec:
            r0 = move-exception
            r0.printStackTrace()
        L_0x03f0:
            r16.b()     // Catch:{ Exception -> 0x03f4 }
            goto L_0x03f9
        L_0x03f4:
            r0 = move-exception
            r2 = r0
            r2.printStackTrace()
        L_0x03f9:
            return
        L_0x03fa:
            com.tencent.smtt.sdk.m r6 = com.tencent.smtt.sdk.m.a((android.content.Context) r17)     // Catch:{ all -> 0x0670 }
            int r11 = r11 + r5
            r6.d((int) r11)     // Catch:{ all -> 0x0670 }
        L_0x0402:
            java.lang.String r6 = "unlzma begin"
            com.tencent.smtt.utils.TbsLog.i(r7, r6)     // Catch:{ all -> 0x0670 }
            com.tencent.smtt.sdk.TbsDownloadConfig r6 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance()     // Catch:{ all -> 0x0670 }
            android.content.SharedPreferences r6 = r6.mPreferences     // Catch:{ all -> 0x0670 }
            java.lang.String r11 = "tbs_responsecode"
            r12 = 0
            int r6 = r6.getInt(r11, r12)     // Catch:{ all -> 0x0670 }
            int r11 = r16.j(r17)     // Catch:{ all -> 0x0670 }
            if (r11 == 0) goto L_0x04da
            java.lang.String r11 = "can_unlzma"
            r12 = 0
            java.lang.Object r11 = com.tencent.smtt.sdk.QbSdk.a((android.content.Context) r2, (java.lang.String) r11, (android.os.Bundle) r12)     // Catch:{ all -> 0x0670 }
            if (r11 == 0) goto L_0x042e
            boolean r12 = r11 instanceof java.lang.Boolean     // Catch:{ all -> 0x0670 }
            if (r12 == 0) goto L_0x042e
            java.lang.Boolean r11 = (java.lang.Boolean) r11     // Catch:{ all -> 0x0670 }
            boolean r11 = r11.booleanValue()     // Catch:{ all -> 0x0670 }
            goto L_0x042f
        L_0x042e:
            r11 = 0
        L_0x042f:
            if (r11 == 0) goto L_0x04da
            android.os.Bundle r11 = new android.os.Bundle     // Catch:{ all -> 0x0670 }
            r11.<init>()     // Catch:{ all -> 0x0670 }
            java.lang.String r12 = "responseCode"
            r11.putInt(r12, r6)     // Catch:{ all -> 0x0670 }
            boolean r6 = com.tencent.smtt.sdk.TbsDownloader.a((android.content.Context) r17)     // Catch:{ all -> 0x0670 }
            java.lang.String r12 = "unzip_temp_path"
            if (r6 == 0) goto L_0x044f
            java.io.File r6 = r16.q(r17)     // Catch:{ all -> 0x0670 }
            java.lang.String r6 = r6.getAbsolutePath()     // Catch:{ all -> 0x0670 }
        L_0x044b:
            r11.putString(r12, r6)     // Catch:{ all -> 0x0670 }
            goto L_0x0459
        L_0x044f:
            r6 = 0
            java.io.File r14 = r1.f((android.content.Context) r2, (int) r6)     // Catch:{ all -> 0x0670 }
            java.lang.String r6 = r14.getAbsolutePath()     // Catch:{ all -> 0x0670 }
            goto L_0x044b
        L_0x0459:
            java.lang.String r6 = "unlzma"
            java.lang.Object r6 = com.tencent.smtt.sdk.QbSdk.a((android.content.Context) r2, (java.lang.String) r6, (android.os.Bundle) r11)     // Catch:{ all -> 0x0670 }
            r11 = 222(0xde, float:3.11E-43)
            if (r6 != 0) goto L_0x0472
            java.lang.String r6 = "unlzma return null"
            com.tencent.smtt.utils.TbsLog.i(r7, r6)     // Catch:{ all -> 0x0670 }
            com.tencent.smtt.sdk.TbsLogReport r6 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r17)     // Catch:{ all -> 0x0670 }
            java.lang.String r12 = "unlzma is null"
        L_0x046e:
            r6.setInstallErrorCode((int) r11, (java.lang.String) r12)     // Catch:{ all -> 0x0670 }
            goto L_0x04be
        L_0x0472:
            boolean r12 = r6 instanceof java.lang.Boolean     // Catch:{ all -> 0x0670 }
            if (r12 == 0) goto L_0x0490
            java.lang.Boolean r6 = (java.lang.Boolean) r6     // Catch:{ all -> 0x0670 }
            boolean r6 = r6.booleanValue()     // Catch:{ all -> 0x0670 }
            if (r6 == 0) goto L_0x0484
            java.lang.String r6 = "unlzma success"
            com.tencent.smtt.utils.TbsLog.i(r7, r6)     // Catch:{ all -> 0x0670 }
            goto L_0x0494
        L_0x0484:
            java.lang.String r6 = "unlzma return false"
            com.tencent.smtt.utils.TbsLog.i(r7, r6)     // Catch:{ all -> 0x0670 }
            com.tencent.smtt.sdk.TbsLogReport r6 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r17)     // Catch:{ all -> 0x0670 }
            java.lang.String r12 = "unlzma return false"
            goto L_0x046e
        L_0x0490:
            boolean r12 = r6 instanceof android.os.Bundle     // Catch:{ all -> 0x0670 }
            if (r12 == 0) goto L_0x0496
        L_0x0494:
            r6 = 1
            goto L_0x04bf
        L_0x0496:
            boolean r12 = r6 instanceof java.lang.Throwable     // Catch:{ all -> 0x0670 }
            if (r12 == 0) goto L_0x04be
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x0670 }
            r12.<init>()     // Catch:{ all -> 0x0670 }
            java.lang.String r14 = "unlzma failure because Throwable"
            r12.append(r14)     // Catch:{ all -> 0x0670 }
            r14 = r6
            java.lang.Throwable r14 = (java.lang.Throwable) r14     // Catch:{ all -> 0x0670 }
            java.lang.String r14 = android.util.Log.getStackTraceString(r14)     // Catch:{ all -> 0x0670 }
            r12.append(r14)     // Catch:{ all -> 0x0670 }
            java.lang.String r12 = r12.toString()     // Catch:{ all -> 0x0670 }
            com.tencent.smtt.utils.TbsLog.i(r7, r12)     // Catch:{ all -> 0x0670 }
            com.tencent.smtt.sdk.TbsLogReport r12 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r17)     // Catch:{ all -> 0x0670 }
            java.lang.Throwable r6 = (java.lang.Throwable) r6     // Catch:{ all -> 0x0670 }
            r12.setInstallErrorCode((int) r11, (java.lang.Throwable) r6)     // Catch:{ all -> 0x0670 }
        L_0x04be:
            r6 = 0
        L_0x04bf:
            if (r6 != 0) goto L_0x04da
            java.util.concurrent.locks.ReentrantLock r0 = i     // Catch:{ Exception -> 0x04cc }
            r0.unlock()     // Catch:{ Exception -> 0x04cc }
            java.util.concurrent.locks.Lock r0 = j     // Catch:{ Exception -> 0x04cc }
            r0.unlock()     // Catch:{ Exception -> 0x04cc }
            goto L_0x04d0
        L_0x04cc:
            r0 = move-exception
            r0.printStackTrace()
        L_0x04d0:
            r16.b()     // Catch:{ Exception -> 0x04d4 }
            goto L_0x04d9
        L_0x04d4:
            r0 = move-exception
            r2 = r0
            r2.printStackTrace()
        L_0x04d9:
            return
        L_0x04da:
            java.lang.String r6 = "unlzma finished"
            com.tencent.smtt.utils.TbsLog.i(r7, r6)     // Catch:{ all -> 0x0670 }
            com.tencent.smtt.sdk.m r6 = com.tencent.smtt.sdk.m.a((android.content.Context) r17)     // Catch:{ all -> 0x0670 }
            r6.c(r8, r5)     // Catch:{ all -> 0x0670 }
            r6 = 2
            goto L_0x052e
        L_0x04e8:
            boolean r6 = com.tencent.smtt.sdk.TbsDownloader.a((android.content.Context) r17)     // Catch:{ all -> 0x0670 }
            if (r6 == 0) goto L_0x052c
            if (r3 != 0) goto L_0x0523
            com.tencent.smtt.sdk.m r6 = com.tencent.smtt.sdk.m.a((android.content.Context) r17)     // Catch:{ all -> 0x0670 }
            java.lang.String r6 = r6.d((java.lang.String) r15)     // Catch:{ all -> 0x0670 }
            if (r6 != 0) goto L_0x0524
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r17)     // Catch:{ all -> 0x0670 }
            r0.setInstallErrorCode((int) r8, (java.lang.String) r14)     // Catch:{ all -> 0x0670 }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)     // Catch:{ all -> 0x0670 }
            r2 = -511(0xfffffffffffffe01, float:NaN)
            r0.setInstallInterruptCode(r2)     // Catch:{ all -> 0x0670 }
            java.util.concurrent.locks.ReentrantLock r0 = i     // Catch:{ Exception -> 0x0515 }
            r0.unlock()     // Catch:{ Exception -> 0x0515 }
            java.util.concurrent.locks.Lock r0 = j     // Catch:{ Exception -> 0x0515 }
            r0.unlock()     // Catch:{ Exception -> 0x0515 }
            goto L_0x0519
        L_0x0515:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0519:
            r16.b()     // Catch:{ Exception -> 0x051d }
            goto L_0x0522
        L_0x051d:
            r0 = move-exception
            r2 = r0
            r2.printStackTrace()
        L_0x0522:
            return
        L_0x0523:
            r6 = r3
        L_0x0524:
            java.io.File r8 = new java.io.File     // Catch:{ all -> 0x0670 }
            r8.<init>(r6)     // Catch:{ all -> 0x0670 }
            r1.a((android.content.Context) r2, (java.io.File) r8, (boolean) r5)     // Catch:{ all -> 0x0670 }
        L_0x052c:
            r6 = 2
            r8 = 0
        L_0x052e:
            if (r13 >= r6) goto L_0x064d
            if (r0 == 0) goto L_0x0576
            com.tencent.smtt.sdk.m r0 = com.tencent.smtt.sdk.m.a((android.content.Context) r17)     // Catch:{ all -> 0x0670 }
            java.lang.String r6 = "dexopt_retry_num"
            int r0 = r0.c((java.lang.String) r6)     // Catch:{ all -> 0x0670 }
            if (r0 <= r9) goto L_0x056e
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r17)     // Catch:{ all -> 0x0670 }
            r3 = 208(0xd0, float:2.91E-43)
            java.lang.String r4 = "exceed dexopt retry num!"
            r0.setInstallErrorCode((int) r3, (java.lang.String) r4)     // Catch:{ all -> 0x0670 }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)     // Catch:{ all -> 0x0670 }
            r3 = -514(0xfffffffffffffdfe, float:NaN)
            r0.setInstallInterruptCode(r3)     // Catch:{ all -> 0x0670 }
            r16.F(r17)     // Catch:{ all -> 0x0670 }
            java.util.concurrent.locks.ReentrantLock r0 = i     // Catch:{ Exception -> 0x0560 }
            r0.unlock()     // Catch:{ Exception -> 0x0560 }
            java.util.concurrent.locks.Lock r0 = j     // Catch:{ Exception -> 0x0560 }
            r0.unlock()     // Catch:{ Exception -> 0x0560 }
            goto L_0x0564
        L_0x0560:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0564:
            r16.b()     // Catch:{ Exception -> 0x0568 }
            goto L_0x056d
        L_0x0568:
            r0 = move-exception
            r2 = r0
            r2.printStackTrace()
        L_0x056d:
            return
        L_0x056e:
            com.tencent.smtt.sdk.m r6 = com.tencent.smtt.sdk.m.a((android.content.Context) r17)     // Catch:{ all -> 0x0670 }
            int r0 = r0 + r5
            r6.a((int) r0)     // Catch:{ all -> 0x0670 }
        L_0x0576:
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)     // Catch:{ all -> 0x0670 }
            r6 = -549(0xfffffffffffffddb, float:NaN)
            r0.setInstallInterruptCode(r6)     // Catch:{ all -> 0x0670 }
            r0 = 0
            boolean r6 = r1.j(r2, r0)     // Catch:{ all -> 0x0670 }
            if (r6 != 0) goto L_0x05a8
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)     // Catch:{ all -> 0x0670 }
            r2 = -515(0xfffffffffffffdfd, float:NaN)
            r0.setInstallInterruptCode(r2)     // Catch:{ all -> 0x0670 }
            java.util.concurrent.locks.ReentrantLock r0 = i     // Catch:{ Exception -> 0x059a }
            r0.unlock()     // Catch:{ Exception -> 0x059a }
            java.util.concurrent.locks.Lock r0 = j     // Catch:{ Exception -> 0x059a }
            r0.unlock()     // Catch:{ Exception -> 0x059a }
            goto L_0x059e
        L_0x059a:
            r0 = move-exception
            r0.printStackTrace()
        L_0x059e:
            r16.b()     // Catch:{ Exception -> 0x05a2 }
            goto L_0x05a7
        L_0x05a2:
            r0 = move-exception
            r2 = r0
            r2.printStackTrace()
        L_0x05a7:
            return
        L_0x05a8:
            com.tencent.smtt.sdk.m r0 = com.tencent.smtt.sdk.m.a((android.content.Context) r17)     // Catch:{ all -> 0x0670 }
            r6 = 2
            r0.c(r8, r6)     // Catch:{ all -> 0x0670 }
            java.lang.String r0 = "STEP 2/2 installation completed! you can restart!"
            com.tencent.smtt.utils.TbsLog.i(r7, r0, r5)     // Catch:{ all -> 0x0670 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0670 }
            r0.<init>()     // Catch:{ all -> 0x0670 }
            java.lang.String r5 = "STEP 2/2 installation completed! you can restart! version:"
            r0.append(r5)     // Catch:{ all -> 0x0670 }
            r0.append(r4)     // Catch:{ all -> 0x0670 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0670 }
            com.tencent.smtt.utils.TbsLog.i(r7, r0)     // Catch:{ all -> 0x0670 }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)     // Catch:{ all -> 0x0670 }
            r5 = -516(0xfffffffffffffdfc, float:NaN)
            r0.setInstallInterruptCode(r5)     // Catch:{ all -> 0x0670 }
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0670 }
            r5 = 11
            if (r0 < r5) goto L_0x05e0
            r0 = 4
            android.content.SharedPreferences r0 = r2.getSharedPreferences(r10, r0)     // Catch:{ all -> 0x0670 }
            r5 = r0
            r0 = 0
            goto L_0x05e5
        L_0x05e0:
            r0 = 0
            android.content.SharedPreferences r5 = r2.getSharedPreferences(r10, r0)     // Catch:{ all -> 0x0670 }
        L_0x05e5:
            android.content.SharedPreferences$Editor r5 = r5.edit()     // Catch:{ all -> 0x0608 }
            java.lang.String r6 = "tbs_preload_x5_counter"
            r5.putInt(r6, r0)     // Catch:{ all -> 0x0608 }
            java.lang.String r6 = "tbs_preload_x5_recorder"
            r5.putInt(r6, r0)     // Catch:{ all -> 0x0608 }
            java.lang.String r0 = "tbs_preload_x5_version"
            r5.putInt(r0, r4)     // Catch:{ all -> 0x0608 }
            r5.commit()     // Catch:{ all -> 0x0608 }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)     // Catch:{ all -> 0x0608 }
            r5 = -517(0xfffffffffffffdfb, float:NaN)
            r0.setInstallInterruptCode(r5)     // Catch:{ all -> 0x0608 }
        L_0x0604:
            r5 = 88888888(0x54c5638, float:9.60787E-36)
            goto L_0x062b
        L_0x0608:
            r0 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0670 }
            r5.<init>()     // Catch:{ all -> 0x0670 }
            java.lang.String r6 = "Init tbs_preload_x5_counter#1 exception:"
            r5.append(r6)     // Catch:{ all -> 0x0670 }
            java.lang.String r0 = android.util.Log.getStackTraceString(r0)     // Catch:{ all -> 0x0670 }
            r5.append(r0)     // Catch:{ all -> 0x0670 }
            java.lang.String r0 = r5.toString()     // Catch:{ all -> 0x0670 }
            com.tencent.smtt.utils.TbsLog.e(r7, r0)     // Catch:{ all -> 0x0670 }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)     // Catch:{ all -> 0x0670 }
            r5 = -518(0xfffffffffffffdfa, float:NaN)
            r0.setInstallInterruptCode(r5)     // Catch:{ all -> 0x0670 }
            goto L_0x0604
        L_0x062b:
            if (r4 != r5) goto L_0x0630
            r1.a((int) r4, (java.lang.String) r3, (android.content.Context) r2)     // Catch:{ all -> 0x0670 }
        L_0x0630:
            boolean r0 = r1.k     // Catch:{ all -> 0x0670 }
            if (r0 == 0) goto L_0x0642
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r17)     // Catch:{ all -> 0x0670 }
            int r2 = r16.v(r17)     // Catch:{ all -> 0x0670 }
            java.lang.String r3 = "continueInstallWithout core success"
        L_0x063e:
            r0.setInstallErrorCode((int) r2, (java.lang.String) r3)     // Catch:{ all -> 0x0670 }
            goto L_0x0657
        L_0x0642:
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r17)     // Catch:{ all -> 0x0670 }
            int r2 = r16.v(r17)     // Catch:{ all -> 0x0670 }
            java.lang.String r3 = "success"
            goto L_0x063e
        L_0x064d:
            r0 = 2
            if (r13 != r0) goto L_0x0657
            com.tencent.smtt.sdk.TbsListener r0 = com.tencent.smtt.sdk.QbSdk.m     // Catch:{ all -> 0x0670 }
            r2 = 200(0xc8, float:2.8E-43)
            r0.onInstallFinish(r2)     // Catch:{ all -> 0x0670 }
        L_0x0657:
            java.util.concurrent.locks.ReentrantLock r0 = i     // Catch:{ Exception -> 0x0662 }
            r0.unlock()     // Catch:{ Exception -> 0x0662 }
            java.util.concurrent.locks.Lock r0 = j     // Catch:{ Exception -> 0x0662 }
            r0.unlock()     // Catch:{ Exception -> 0x0662 }
            goto L_0x0666
        L_0x0662:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0666:
            r16.b()     // Catch:{ Exception -> 0x066a }
            goto L_0x0697
        L_0x066a:
            r0 = move-exception
            r2 = r0
            r2.printStackTrace()
            goto L_0x0697
        L_0x0670:
            r0 = move-exception
            r2 = r0
            java.util.concurrent.locks.ReentrantLock r0 = i     // Catch:{ Exception -> 0x067d }
            r0.unlock()     // Catch:{ Exception -> 0x067d }
            java.util.concurrent.locks.Lock r0 = j     // Catch:{ Exception -> 0x067d }
            r0.unlock()     // Catch:{ Exception -> 0x067d }
            goto L_0x0681
        L_0x067d:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0681:
            r16.b()     // Catch:{ Exception -> 0x0685 }
            goto L_0x068a
        L_0x0685:
            r0 = move-exception
            r3 = r0
            r3.printStackTrace()
        L_0x068a:
            throw r2
        L_0x068b:
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)
            r2 = -519(0xfffffffffffffdf9, float:NaN)
            r0.setInstallInterruptCode(r2)
            r16.b()
        L_0x0697:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.o.b(android.content.Context, java.lang.String, int):void");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|(3:8|9|10)|11|12|(2:14|15)|20|16) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002a */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0031 A[Catch:{ Exception -> 0x0060 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean b(android.content.Context r11, java.io.File r12) {
        /*
            r10 = this;
            java.lang.String r0 = "TbsInstaller"
            r1 = 0
            com.tencent.smtt.sdk.o$6 r2 = new com.tencent.smtt.sdk.o$6     // Catch:{ Exception -> 0x0060 }
            r2.<init>()     // Catch:{ Exception -> 0x0060 }
            java.io.File[] r2 = r12.listFiles(r2)     // Catch:{ Exception -> 0x0060 }
            int r3 = r2.length     // Catch:{ Exception -> 0x0060 }
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0060 }
            r5 = 16
            if (r4 >= r5) goto L_0x002a
            java.lang.String r4 = r11.getPackageName()     // Catch:{ Exception -> 0x0060 }
            if (r4 == 0) goto L_0x002a
            java.lang.String r4 = r11.getPackageName()     // Catch:{ Exception -> 0x0060 }
            java.lang.String r5 = "com.tencent.tbs"
            boolean r4 = r4.equalsIgnoreCase(r5)     // Catch:{ Exception -> 0x0060 }
            if (r4 == 0) goto L_0x002a
            r4 = 5000(0x1388, double:2.4703E-320)
            java.lang.Thread.sleep(r4)     // Catch:{ Exception -> 0x002a }
        L_0x002a:
            java.lang.ClassLoader r4 = r11.getClassLoader()     // Catch:{ Exception -> 0x0060 }
            r5 = 0
        L_0x002f:
            if (r5 >= r3) goto L_0x005e
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0060 }
            r6.<init>()     // Catch:{ Exception -> 0x0060 }
            java.lang.String r7 = "jarFile: "
            r6.append(r7)     // Catch:{ Exception -> 0x0060 }
            r7 = r2[r5]     // Catch:{ Exception -> 0x0060 }
            java.lang.String r7 = r7.getAbsolutePath()     // Catch:{ Exception -> 0x0060 }
            r6.append(r7)     // Catch:{ Exception -> 0x0060 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x0060 }
            com.tencent.smtt.utils.TbsLog.i(r0, r6)     // Catch:{ Exception -> 0x0060 }
            dalvik.system.DexClassLoader r6 = new dalvik.system.DexClassLoader     // Catch:{ Exception -> 0x0060 }
            r7 = r2[r5]     // Catch:{ Exception -> 0x0060 }
            java.lang.String r7 = r7.getAbsolutePath()     // Catch:{ Exception -> 0x0060 }
            java.lang.String r8 = r12.getAbsolutePath()     // Catch:{ Exception -> 0x0060 }
            r9 = 0
            r6.<init>(r7, r8, r9, r4)     // Catch:{ Exception -> 0x0060 }
            int r5 = r5 + 1
            goto L_0x002f
        L_0x005e:
            r11 = 1
            return r11
        L_0x0060:
            r12 = move-exception
            r12.printStackTrace()
            com.tencent.smtt.sdk.TbsLogReport r11 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r11)
            r2 = 209(0xd1, float:2.93E-43)
            java.lang.String r12 = r12.toString()
            r11.setInstallErrorCode((int) r2, (java.lang.String) r12)
            java.lang.String r11 = "TbsInstaller-doTbsDexOpt done"
            com.tencent.smtt.utils.TbsLog.i(r0, r11)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.o.b(android.content.Context, java.io.File):boolean");
    }

    private int c(Context context, Bundle bundle) {
        TbsLogReport instance;
        int i2;
        try {
            Bundle a2 = QbSdk.a(context, bundle);
            TbsLog.i("TbsInstaller", "tpatch finished,ret is" + a2);
            int i3 = a2.getInt("patch_result");
            if (i3 == 0) {
                String string = bundle.getString("new_apk_location");
                int i4 = bundle.getInt("new_core_ver");
                int a3 = a(new File(string));
                if (i4 != a3) {
                    TbsLog.i("TbsInstaller", "version not equals!!!" + i4 + "patchVersion:" + a3);
                    TbsLogReport instance2 = TbsLogReport.getInstance(context);
                    instance2.setInstallErrorCode((int) TbsListener.ErrorCode.TPATCH_VERSION_FAILED, "version=" + i4 + ",patchVersion=" + a3);
                    return 1;
                }
                if (TbsDownloader.a(context)) {
                    TbsLog.i("TbsInstaller", "Tpatch decouple success!");
                    instance = TbsLogReport.getInstance(context);
                    i2 = TbsListener.ErrorCode.DECOUPLE_TPATCH_INSTALL_SUCCESS;
                } else {
                    TbsLog.i("TbsInstaller", "Tpatch success!");
                    instance = TbsLogReport.getInstance(context);
                    i2 = TbsListener.ErrorCode.TPATCH_INSTALL_SUCCESS;
                }
                instance.setInstallErrorCode(i2, "");
                return 0;
            }
            String string2 = bundle.getString("new_apk_location");
            if (!TextUtils.isEmpty(string2)) {
                FileUtil.b(new File(string2));
            }
            TbsLogReport instance3 = TbsLogReport.getInstance(context);
            instance3.setInstallErrorCode(i3, "tpatch fail,patch error_code=" + i3);
            return 1;
        } catch (Exception e2) {
            e2.printStackTrace();
            TbsLogReport instance4 = TbsLogReport.getInstance(context);
            instance4.setInstallErrorCode((int) TbsListener.ErrorCode.DECOUPLE_TPATCH_FAIL, "patch exception" + Log.getStackTraceString(e2));
            return 1;
        }
    }

    private boolean c(Context context, File file) {
        try {
            File file2 = new File(file, "tbs_sdk_extension_dex.jar");
            File file3 = new File(file, "tbs_sdk_extension_dex.dex");
            new DexClassLoader(file2.getAbsolutePath(), file.getAbsolutePath(), (String) null, context.getClassLoader());
            String a2 = e.a(context, file3.getAbsolutePath());
            if (TextUtils.isEmpty(a2)) {
                TbsLogReport.getInstance(context).setInstallErrorCode((int) TbsListener.ErrorCode.DEXOAT_EXCEPTION, "can not find oat command");
                return false;
            }
            for (File file4 : file.listFiles(new FileFilter() {
                public boolean accept(File file) {
                    return file.getName().endsWith(".jar");
                }
            })) {
                String substring = file4.getName().substring(0, file4.getName().length() - 4);
                Runtime.getRuntime().exec("/system/bin/dex2oat " + a2.replaceAll("tbs_sdk_extension_dex", substring) + " --dex-location=" + a().r(context) + File.separator + substring + ".jar").waitFor();
            }
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            TbsLogReport.getInstance(context).setInstallErrorCode((int) TbsListener.ErrorCode.DEXOAT_EXCEPTION, (Throwable) e2);
            return false;
        }
    }

    private synchronized boolean c(Context context, boolean z) {
        boolean z2;
        TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromTpatch");
        z2 = false;
        try {
            if (!u(context)) {
                return false;
            }
            boolean tryLock = i.tryLock();
            TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromTpatch Locked =" + tryLock);
            if (tryLock) {
                int b2 = m.a(context).b("tpatch_status");
                int a2 = a(false, context);
                TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromTpatch copyStatus =" + b2);
                TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromTpatch tbsCoreInstalledVer =" + a2);
                if (b2 == 1) {
                    if (a2 == 0) {
                        TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromTpatch tbsCoreInstalledVer = 0", true);
                    } else if (z) {
                        TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromTpatch tbsCoreInstalledVer != 0", true);
                    }
                    z(context);
                    z2 = true;
                }
                i.unlock();
            }
            b();
        } catch (Throwable th) {
            TbsLogReport.getInstance(context).setInstallErrorCode((int) TbsListener.ErrorCode.COPY_EXCEPTION, th.toString());
            QbSdk.a(context, "TbsInstaller::enableTbsCoreFromTpatch exception:" + Log.getStackTraceString(th));
        }
        return z2;
    }

    private synchronized boolean d(Context context, boolean z) {
        boolean z2;
        TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromCopy");
        z2 = false;
        try {
            if (!u(context)) {
                return false;
            }
            boolean tryLock = i.tryLock();
            TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromCopy Locked =" + tryLock);
            if (tryLock) {
                int b2 = m.a(context).b("copy_status");
                int a2 = a(false, context);
                TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromCopy copyStatus =" + b2);
                TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromCopy tbsCoreInstalledVer =" + a2);
                if (b2 == 1) {
                    if (a2 == 0) {
                        TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromCopy tbsCoreInstalledVer = 0", true);
                    } else if (z) {
                        TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromCopy tbsCoreInstalledVer != 0", true);
                    }
                    A(context);
                    z2 = true;
                }
                i.unlock();
            }
            b();
        } catch (Throwable th) {
            TbsLogReport.getInstance(context).setInstallErrorCode((int) TbsListener.ErrorCode.COPY_EXCEPTION, th.toString());
            QbSdk.a(context, "TbsInstaller::enableTbsCoreFromCopy exception:" + Log.getStackTraceString(th));
        }
        return z2;
    }

    private boolean e(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            packageInfo = null;
        }
        return packageInfo != null;
    }

    private synchronized boolean e(Context context, boolean z) {
        boolean z2;
        if (context != null) {
            if (TbsConfig.APP_WX.equals(context.getApplicationContext().getApplicationInfo().packageName)) {
                TbsLogReport.getInstance(context).setInstallErrorCode((int) TbsListener.ErrorCode.INSTALL_FROM_UNZIP, StringUtils.SPACE);
            }
        }
        TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromUnzip canRenameTmpDir =" + z);
        TbsLog.i("TbsInstaller", "Tbsinstaller enableTbsCoreFromUnzip #1 ");
        z2 = false;
        try {
            if (!u(context)) {
                return false;
            }
            TbsLog.i("TbsInstaller", "Tbsinstaller enableTbsCoreFromUnzip #2 ");
            boolean tryLock = i.tryLock();
            TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromUnzip locked=" + tryLock);
            if (tryLock) {
                int c2 = m.a(context).c();
                TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromUnzip installStatus=" + c2);
                int a2 = a(false, context);
                if (c2 == 2) {
                    TbsLog.i("TbsInstaller", "Tbsinstaller enableTbsCoreFromUnzip #4 ");
                    if (a2 == 0) {
                        TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromUnzip tbsCoreInstalledVer = 0", false);
                    } else if (z) {
                        TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromUnzip tbsCoreInstalledVer != 0", false);
                    }
                    y(context);
                    z2 = true;
                }
                i.unlock();
            }
            b();
        } catch (Exception e2) {
            QbSdk.a(context, "TbsInstaller::enableTbsCoreFromUnzip Exception: " + e2);
            e2.printStackTrace();
        } catch (Throwable th) {
            i.unlock();
            throw th;
        }
        return z2;
    }

    private synchronized boolean f(Context context, boolean z) {
        return false;
    }

    private void g(Context context, boolean z) {
        if (context == null) {
            TbsLogReport.getInstance(context).setInstallErrorCode((int) TbsListener.ErrorCode.CREATE_TEMP_CONF_ERROR, "setTmpFolderCoreToRead context is null");
            return;
        }
        try {
            File file = new File(QbSdk.getTbsFolderDir(context), "tmp_folder_core_to_read.conf");
            if (!z) {
                FileUtil.b(file);
            } else if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e2) {
            TbsLogReport instance = TbsLogReport.getInstance(context);
            instance.setInstallErrorCode((int) TbsListener.ErrorCode.CREATE_TEMP_CONF_ERROR, "setTmpFolderCoreToRead Exception message is " + e2.getMessage() + " Exception cause is " + e2.getCause());
        }
    }

    private void h(Context context, int i2) {
        TbsLog.i("TbsInstaller", "proceedTpatchStatus,result=" + i2);
        if (i2 == 0) {
            if (TbsDownloader.a(context)) {
                i(context, 6);
            } else {
                g(context, true);
                m.a(context).b(TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0), 1);
            }
        }
        QbSdk.setTBSInstallingStatus(false);
    }

    private void i(Context context, int i2) {
        File f2 = a().f(context, i2);
        a().g(context, true);
        File q = q(context);
        FileUtil.a(q, true);
        f2.renameTo(q);
        TbsShareManager.b(context);
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x006c A[Catch:{ Exception -> 0x00a2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x006e A[Catch:{ Exception -> 0x00a2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x008a A[Catch:{ Exception -> 0x00a2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0090 A[Catch:{ Exception -> 0x00a2 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean j(android.content.Context r8, int r9) {
        /*
            r7 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "TbsInstaller-doTbsDexOpt start - dirMode: "
            r0.append(r1)
            r0.append(r9)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "TbsInstaller"
            com.tencent.smtt.utils.TbsLog.i(r1, r0)
            r0 = 0
            r2 = 1
            if (r9 == 0) goto L_0x003e
            if (r9 == r2) goto L_0x0039
            r3 = 2
            if (r9 == r3) goto L_0x0034
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00a2 }
            r3.<init>()     // Catch:{ Exception -> 0x00a2 }
            java.lang.String r4 = "doDexoptOrDexoat mode error: "
            r3.append(r4)     // Catch:{ Exception -> 0x00a2 }
            r3.append(r9)     // Catch:{ Exception -> 0x00a2 }
            java.lang.String r9 = r3.toString()     // Catch:{ Exception -> 0x00a2 }
            com.tencent.smtt.utils.TbsLog.e(r1, r9)     // Catch:{ Exception -> 0x00a2 }
            return r0
        L_0x0034:
            java.io.File r9 = r7.r(r8)     // Catch:{ Exception -> 0x00a2 }
            goto L_0x0049
        L_0x0039:
            java.io.File r9 = r7.f((android.content.Context) r8, (int) r2)     // Catch:{ Exception -> 0x00a2 }
            goto L_0x0049
        L_0x003e:
            boolean r9 = com.tencent.smtt.sdk.TbsDownloader.a((android.content.Context) r8)     // Catch:{ Exception -> 0x00a2 }
            if (r9 == 0) goto L_0x0045
            return r2
        L_0x0045:
            java.io.File r9 = r7.f((android.content.Context) r8, (int) r0)     // Catch:{ Exception -> 0x00a2 }
        L_0x0049:
            java.lang.String r3 = "java.vm.version"
            java.lang.String r3 = java.lang.System.getProperty(r3)     // Catch:{ all -> 0x005b }
            if (r3 == 0) goto L_0x0065
            java.lang.String r4 = "2"
            boolean r3 = r3.startsWith(r4)     // Catch:{ all -> 0x005b }
            if (r3 == 0) goto L_0x0065
            r3 = 1
            goto L_0x0066
        L_0x005b:
            r3 = move-exception
            com.tencent.smtt.sdk.TbsLogReport r4 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r8)     // Catch:{ Exception -> 0x00a2 }
            r5 = 226(0xe2, float:3.17E-43)
            r4.setInstallErrorCode((int) r5, (java.lang.Throwable) r3)     // Catch:{ Exception -> 0x00a2 }
        L_0x0065:
            r3 = 0
        L_0x0066:
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x00a2 }
            r5 = 23
            if (r4 != r5) goto L_0x006e
            r4 = 1
            goto L_0x006f
        L_0x006e:
            r4 = 0
        L_0x006f:
            com.tencent.smtt.sdk.TbsDownloadConfig r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8)     // Catch:{ Exception -> 0x00a2 }
            android.content.SharedPreferences r5 = r5.mPreferences     // Catch:{ Exception -> 0x00a2 }
            java.lang.String r6 = "tbs_stop_preoat"
            boolean r5 = r5.getBoolean(r6, r0)     // Catch:{ Exception -> 0x00a2 }
            if (r3 == 0) goto L_0x0082
            if (r4 == 0) goto L_0x0082
            if (r5 != 0) goto L_0x0082
            r0 = 1
        L_0x0082:
            if (r0 == 0) goto L_0x0090
            boolean r0 = r7.c((android.content.Context) r8, (java.io.File) r9)     // Catch:{ Exception -> 0x00a2 }
            if (r0 == 0) goto L_0x0090
            java.lang.String r9 = "doTbsDexOpt -- doDexoatForArtVm"
            com.tencent.smtt.utils.TbsLog.i(r1, r9)     // Catch:{ Exception -> 0x00a2 }
            return r2
        L_0x0090:
            if (r3 == 0) goto L_0x0098
            java.lang.String r9 = "doTbsDexOpt -- is ART mode, skip!"
            com.tencent.smtt.utils.TbsLog.i(r1, r9)     // Catch:{ Exception -> 0x00a2 }
            goto L_0x00b3
        L_0x0098:
            java.lang.String r0 = "doTbsDexOpt -- doDexoptForDavlikVM"
            com.tencent.smtt.utils.TbsLog.i(r1, r0)     // Catch:{ Exception -> 0x00a2 }
            boolean r8 = r7.b((android.content.Context) r8, (java.io.File) r9)     // Catch:{ Exception -> 0x00a2 }
            return r8
        L_0x00a2:
            r9 = move-exception
            r9.printStackTrace()
            com.tencent.smtt.sdk.TbsLogReport r8 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r8)
            r0 = 209(0xd1, float:2.93E-43)
            java.lang.String r9 = r9.toString()
            r8.setInstallErrorCode((int) r0, (java.lang.String) r9)
        L_0x00b3:
            java.lang.String r8 = "TbsInstaller-doTbsDexOpt done"
            com.tencent.smtt.utils.TbsLog.i(r1, r8)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.o.j(android.content.Context, int):boolean");
    }

    static File t(Context context) {
        File file = new File(QbSdk.getTbsFolderDir(context), "core_private");
        if (file.isDirectory() || file.mkdir()) {
            return file;
        }
        return null;
    }

    private int v(Context context) {
        boolean z = true;
        if (m.a(context).d() != 1) {
            z = false;
        }
        boolean a2 = TbsDownloader.a(context);
        if (z) {
            return a2 ? TbsListener.ErrorCode.DECOUPLE_INCURUPDATE_SUCCESS : TbsListener.ErrorCode.INCRUPDATE_INSTALL_SUCCESS;
        }
        if (a2) {
            return TbsListener.ErrorCode.DECOUPLE_INSTLL_SUCCESS;
        }
        return 200;
    }

    private static boolean w(Context context) {
        String str;
        if (context == null) {
            str = "TbsInstaller-getTmpFolderCoreToRead, #1";
        } else {
            try {
                if (new File(QbSdk.getTbsFolderDir(context), "tmp_folder_core_to_read.conf").exists()) {
                    TbsLog.i("TbsInstaller", "TbsInstaller-getTmpFolderCoreToRead, #2");
                    return true;
                }
                TbsLog.i("TbsInstaller", "TbsInstaller-getTmpFolderCoreToRead, #3");
                return false;
            } catch (Exception unused) {
                str = "TbsInstaller-getTmpFolderCoreToRead, #4";
            }
        }
        TbsLog.i("TbsInstaller", str);
        return true;
    }

    private boolean x(Context context) {
        boolean z;
        TbsLog.i("TbsInstaller", "Tbsinstaller getTbsCoreRenameFileLock #1 ");
        try {
            z = TbsDownloadConfig.getInstance().getTbsCoreLoadRenameFileLockEnable();
        } catch (Throwable unused) {
            z = true;
        }
        TbsLog.i("TbsInstaller", "Tbsinstaller getTbsCoreRenameFileLock #2  enabled is " + z);
        l = !z ? w.a().b(context) : FileUtil.f(context);
        if (l == null) {
            TbsLog.i("TbsInstaller", "getTbsCoreRenameFileLock## failed!");
            return false;
        }
        TbsLog.i("TbsInstaller", "Tbsinstaller getTbsCoreRenameFileLock true ");
        return true;
    }

    private void y(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--generateNewTbsCoreFromUnzip");
        if (!x(context)) {
            TbsLog.i("TbsInstaller", "get rename fileLock#4 ## failed!");
            return;
        }
        try {
            B(context);
            C(context);
            TbsLog.i("TbsInstaller", "after renameTbsCoreShareDir");
            if (!TbsShareManager.isThirdPartyApp(context)) {
                TbsLog.i("TbsInstaller", "prepare to shareTbsCore");
                TbsShareManager.a(context);
            } else {
                TbsLog.i("TbsInstaller", "is thirdapp and not chmod");
            }
            m.a(context).a(0);
            m.a(context).b(0);
            m.a(context).d(0);
            m.a(context).a("incrupdate_retry_num", 0);
            m.a(context).c(0, 3);
            m.a(context).a("");
            m.a(context).a("tpatch_num", 0);
            m.a(context).c(-1);
            if (!TbsShareManager.isThirdPartyApp(context)) {
                int i2 = TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DECOUPLECOREVERSION, 0);
                if (i2 <= 0 || i2 == a().i(context) || i2 != a().j(context)) {
                    TbsLog.i("TbsInstaller", "TbsInstaller--generateNewTbsCoreFromUnzip #1 deCoupleCoreVersion is " + i2 + " getTbsCoreShareDecoupleCoreVersion is " + a().i(context) + " getTbsCoreInstalledVerInNolock is " + a().j(context));
                } else {
                    o(context);
                }
            }
            if (TbsShareManager.isThirdPartyApp(context)) {
                TbsShareManager.writeCoreInfoForThirdPartyApp(context, n(context), true);
            }
            a.set(0);
            o = 0;
        } catch (Throwable th) {
            th.printStackTrace();
            TbsLogReport instance = TbsLogReport.getInstance(context);
            instance.setInstallErrorCode((int) TbsListener.ErrorCode.RENAME_EXCEPTION, "exception when renameing from unzip:" + th.toString());
            TbsLog.e("TbsInstaller", "TbsInstaller--generateNewTbsCoreFromUnzip Exception", true);
        }
        h(context);
    }

    private void z(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--generateNewTbsCoreFromTpatch");
        if (!x(context)) {
            TbsLog.i("TbsInstaller", "get rename fileLock#4 ## failed!");
            return;
        }
        try {
            B(context);
            E(context);
            if (TbsShareManager.isThirdPartyApp(context)) {
                TbsShareManager.writeCoreInfoForThirdPartyApp(context, n(context), true);
            } else {
                TbsShareManager.a(context);
            }
            m.a(context).b(0, -1);
            m.a(context).a("tpatch_num", 0);
            a.set(0);
        } catch (Exception e2) {
            e2.printStackTrace();
            TbsLogReport instance = TbsLogReport.getInstance(context);
            instance.setInstallErrorCode((int) TbsListener.ErrorCode.TPATCH_ENABLE_EXCEPTION, "exception when renameing from tpatch:" + e2.toString());
        }
        h(context);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0057 A[SYNTHETIC, Splitter:B:25:0x0057] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x005e A[SYNTHETIC, Splitter:B:31:0x005e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int a(java.io.File r6) {
        /*
            r5 = this;
            r0 = 0
            r1 = 0
            java.lang.String r2 = "TbsInstaller"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x005b, all -> 0x0054 }
            r3.<init>()     // Catch:{ Exception -> 0x005b, all -> 0x0054 }
            java.lang.String r4 = "TbsInstaller--getTbsVersion  tbsShareDir is "
            r3.append(r4)     // Catch:{ Exception -> 0x005b, all -> 0x0054 }
            r3.append(r6)     // Catch:{ Exception -> 0x005b, all -> 0x0054 }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x005b, all -> 0x0054 }
            com.tencent.smtt.utils.TbsLog.i(r2, r3)     // Catch:{ Exception -> 0x005b, all -> 0x0054 }
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x005b, all -> 0x0054 }
            java.lang.String r3 = "tbs.conf"
            r2.<init>(r6, r3)     // Catch:{ Exception -> 0x005b, all -> 0x0054 }
            boolean r6 = r2.exists()     // Catch:{ Exception -> 0x005b, all -> 0x0054 }
            if (r6 != 0) goto L_0x0026
            return r0
        L_0x0026:
            java.util.Properties r6 = new java.util.Properties     // Catch:{ Exception -> 0x005b, all -> 0x0054 }
            r6.<init>()     // Catch:{ Exception -> 0x005b, all -> 0x0054 }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Exception -> 0x005b, all -> 0x0054 }
            r3.<init>(r2)     // Catch:{ Exception -> 0x005b, all -> 0x0054 }
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x005b, all -> 0x0054 }
            r2.<init>(r3)     // Catch:{ Exception -> 0x005b, all -> 0x0054 }
            r6.load(r2)     // Catch:{ Exception -> 0x0052, all -> 0x004f }
            r2.close()     // Catch:{ Exception -> 0x0052, all -> 0x004f }
            java.lang.String r1 = "tbs_core_version"
            java.lang.String r6 = r6.getProperty(r1)     // Catch:{ Exception -> 0x0052, all -> 0x004f }
            if (r6 != 0) goto L_0x0047
            r2.close()     // Catch:{ IOException -> 0x0046 }
        L_0x0046:
            return r0
        L_0x0047:
            int r6 = java.lang.Integer.parseInt(r6)     // Catch:{ Exception -> 0x0052, all -> 0x004f }
            r2.close()     // Catch:{ IOException -> 0x004e }
        L_0x004e:
            return r6
        L_0x004f:
            r6 = move-exception
            r1 = r2
            goto L_0x0055
        L_0x0052:
            r1 = r2
            goto L_0x005c
        L_0x0054:
            r6 = move-exception
        L_0x0055:
            if (r1 == 0) goto L_0x005a
            r1.close()     // Catch:{ IOException -> 0x005a }
        L_0x005a:
            throw r6
        L_0x005b:
        L_0x005c:
            if (r1 == 0) goto L_0x0061
            r1.close()     // Catch:{ IOException -> 0x0061 }
        L_0x0061:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.o.a(java.io.File):int");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0049 A[SYNTHETIC, Splitter:B:28:0x0049] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0050 A[SYNTHETIC, Splitter:B:34:0x0050] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int a(java.lang.String r5) {
        /*
            r4 = this;
            r0 = 0
            if (r5 != 0) goto L_0x0004
            return r0
        L_0x0004:
            r1 = 0
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x004d, all -> 0x0046 }
            r2.<init>(r5)     // Catch:{ Exception -> 0x004d, all -> 0x0046 }
            java.io.File r5 = new java.io.File     // Catch:{ Exception -> 0x004d, all -> 0x0046 }
            java.lang.String r3 = "tbs.conf"
            r5.<init>(r2, r3)     // Catch:{ Exception -> 0x004d, all -> 0x0046 }
            boolean r2 = r5.exists()     // Catch:{ Exception -> 0x004d, all -> 0x0046 }
            if (r2 != 0) goto L_0x0018
            return r0
        L_0x0018:
            java.util.Properties r2 = new java.util.Properties     // Catch:{ Exception -> 0x004d, all -> 0x0046 }
            r2.<init>()     // Catch:{ Exception -> 0x004d, all -> 0x0046 }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Exception -> 0x004d, all -> 0x0046 }
            r3.<init>(r5)     // Catch:{ Exception -> 0x004d, all -> 0x0046 }
            java.io.BufferedInputStream r5 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x004d, all -> 0x0046 }
            r5.<init>(r3)     // Catch:{ Exception -> 0x004d, all -> 0x0046 }
            r2.load(r5)     // Catch:{ Exception -> 0x0044, all -> 0x0041 }
            r5.close()     // Catch:{ Exception -> 0x0044, all -> 0x0041 }
            java.lang.String r1 = "tbs_core_version"
            java.lang.String r1 = r2.getProperty(r1)     // Catch:{ Exception -> 0x0044, all -> 0x0041 }
            if (r1 != 0) goto L_0x0039
            r5.close()     // Catch:{ IOException -> 0x0038 }
        L_0x0038:
            return r0
        L_0x0039:
            int r0 = java.lang.Integer.parseInt(r1)     // Catch:{ Exception -> 0x0044, all -> 0x0041 }
            r5.close()     // Catch:{ IOException -> 0x0040 }
        L_0x0040:
            return r0
        L_0x0041:
            r0 = move-exception
            r1 = r5
            goto L_0x0047
        L_0x0044:
            r1 = r5
            goto L_0x004e
        L_0x0046:
            r0 = move-exception
        L_0x0047:
            if (r1 == 0) goto L_0x004c
            r1.close()     // Catch:{ IOException -> 0x004c }
        L_0x004c:
            throw r0
        L_0x004d:
        L_0x004e:
            if (r1 == 0) goto L_0x0053
            r1.close()     // Catch:{ IOException -> 0x0053 }
        L_0x0053:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.o.a(java.lang.String):int");
    }

    public int a(boolean z, Context context) {
        if (z || a.get().intValue() <= 0) {
            a.set(Integer.valueOf(j(context)));
        }
        return a.get().intValue();
    }

    /* access modifiers changed from: package-private */
    public File a(Context context, int i2, boolean z) {
        String str;
        String str2;
        File tbsFolderDir = QbSdk.getTbsFolderDir(context);
        switch (i2) {
            case 0:
                str = "core_unzip_tmp";
                break;
            case 1:
                str = "core_copy_tmp";
                break;
            case 2:
                str = "core_unzip_tmp_decouple";
                break;
            case 3:
                str = "core_share_backup";
                break;
            case 4:
                str = "core_share_backup_tmp";
                break;
            case 5:
                str = "tpatch_tmp";
                break;
            case 6:
                str = "tpatch_decouple_tmp";
                break;
            default:
                str = "";
                break;
        }
        TbsLog.i("TbsInstaller", "type=" + i2 + "needMakeDir=" + z + "folder=" + str);
        File file = new File(tbsFolderDir, str);
        if (!file.isDirectory()) {
            if (!z) {
                str2 = "getCoreDir,no need mkdir";
            } else if (!file.mkdir()) {
                str2 = "getCoreDir,mkdir false";
            }
            TbsLog.i("TbsInstaller", str2);
            return null;
        }
        return file;
    }

    public void a(Context context, int i2) {
        g(context, true);
        m.a(context).c(i2, 2);
    }

    /* access modifiers changed from: package-private */
    public void a(Context context, Bundle bundle) {
        if (bundle != null && context != null) {
            Object[] objArr = {context, bundle};
            Message message = new Message();
            message.what = 3;
            message.obj = objArr;
            m.sendMessage(message);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Context context, File file, int i2) {
        TbsLog.i("TbsInstaller", "unzipTbsCoreToThirdAppTmp,ctx=" + context + "File=" + file + "coreVersion=" + i2);
        if (file != null && context != null) {
            Object[] objArr = {context, file, Integer.valueOf(i2)};
            Message message = new Message();
            message.what = 4;
            message.obj = objArr;
            m.sendMessage(message);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Context context, String str, int i2) {
        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCore tbsApkPath=" + str);
        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCore tbsCoreTargetVer=" + i2);
        TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore currentProcessName=" + context.getApplicationInfo().processName);
        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCore currentProcessId=" + Process.myPid());
        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCore currentThreadName=" + Thread.currentThread().getName());
        Object[] objArr = {context, str, Integer.valueOf(i2)};
        Message message = new Message();
        message.what = 1;
        message.obj = objArr;
        m.sendMessage(message);
    }

    /* access modifiers changed from: package-private */
    public void a(Context context, boolean z) {
        int i2;
        int i3;
        int i4;
        String str;
        int i5;
        boolean z2 = true;
        if (z) {
            this.k = true;
        }
        TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore currentProcessName=" + context.getApplicationInfo().processName);
        TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore currentProcessId=" + Process.myPid());
        TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore currentThreadName=" + Thread.currentThread().getName());
        if (u(context)) {
            if (i.tryLock()) {
                try {
                    i2 = m.a(context).c();
                    i5 = m.a(context).b();
                    str = m.a(context).d("install_apk_path");
                    i4 = m.a(context).c("copy_core_ver");
                    i3 = m.a(context).b("copy_status");
                } finally {
                    i.unlock();
                }
            } else {
                str = null;
                i2 = -1;
                i5 = 0;
                i4 = 0;
                i3 = -1;
            }
            b();
            TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore installStatus=" + i2);
            TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore tbsCoreInstallVer=" + i5);
            TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore tbsApkPath=" + str);
            TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore tbsCoreCopyVer=" + i4);
            TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore copyStatus=" + i3);
            if (TbsShareManager.isThirdPartyApp(context)) {
                c(context, TbsShareManager.a(context, false));
                return;
            }
            int i6 = TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_RESPONSECODE, 0);
            if (!(i6 == 1 || i6 == 2 || i6 == 4)) {
                z2 = false;
            }
            if (!(z2 || i6 == 0 || i6 == 5)) {
                Bundle bundle = new Bundle();
                bundle.putInt("operation", SelfChassisStateContent.LiftControlStatus.ELEVATOR_INFO_TOKEN_ERROR);
                a(context, bundle);
            }
            if (i2 > -1 && i2 < 2) {
                a(context, str, i5);
            }
            if (i3 == 0) {
                b(context, i4);
            }
        }
    }

    public synchronized boolean a(final Context context, final Context context2) {
        TbsLog.i("TbsInstaller", "TbsInstaller--quickDexOptForThirdPartyApp");
        if (p) {
            return true;
        }
        p = true;
        new Thread() {
            public void run() {
                File file;
                o oVar;
                Context context;
                TbsLog.i("TbsInstaller", "TbsInstaller--quickDexOptForThirdPartyApp thread start");
                try {
                    if (context2 == null) {
                        file = new File(TbsShareManager.getHostCorePathAppDefined());
                    } else {
                        if (!TbsShareManager.isThirdPartyApp(context)) {
                            oVar = o.this;
                            context = context2;
                        } else if (TbsShareManager.c(context) == null || !TbsShareManager.c(context).contains("decouple")) {
                            oVar = o.this;
                            context = context2;
                        } else {
                            file = o.this.q(context2);
                        }
                        file = oVar.r(context);
                    }
                    File r = o.this.r(context);
                    int i = Build.VERSION.SDK_INT;
                    if (i != 19 && i < 21) {
                        FileUtil.a(file, r, (FileFilter) new FileFilter() {
                            public boolean accept(File file) {
                                return file.getName().endsWith(".dex");
                            }
                        });
                    }
                    FileUtil.a(file, r, (FileFilter) new FileFilter() {
                        public boolean accept(File file) {
                            return file.getName().endsWith("tbs.conf");
                        }
                    });
                    TbsLog.i("TbsInstaller", "TbsInstaller--quickDexOptForThirdPartyApp thread done");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
        return true;
    }

    public boolean a(Context context, File[] fileArr) {
        return false;
    }

    public int b(Context context) {
        if (!w(context)) {
            return 0;
        }
        if (a(context, "core_unzip_tmp")) {
            return -1;
        }
        if (a(context, "core_share_backup_tmp")) {
            return -2;
        }
        if (a(context, "core_copy_tmp")) {
            return -3;
        }
        return a(context, "tpatch_tmp") ? -4 : 1;
    }

    /* access modifiers changed from: package-private */
    public Context b(Context context, String str) {
        try {
            if (context.getPackageName() == str || !TbsPVConfig.getInstance(context).isEnableNoCoreGray()) {
                return context.createPackageContext(str, 2);
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public File b(Context context, Context context2) {
        File file = new File(QbSdk.getTbsFolderDir(context2), "core_share");
        if (file.isDirectory() || ((context != null && TbsShareManager.isThirdPartyApp(context)) || file.mkdir())) {
            return file;
        }
        TbsLog.i("TbsInstaller", "getTbsCoreShareDir,mkdir false");
        return null;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0059, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void b() {
        /*
            r3 = this;
            monitor-enter(r3)
            int r0 = r3.e     // Catch:{ all -> 0x005a }
            if (r0 > 0) goto L_0x0030
            java.lang.String r0 = "TbsInstaller"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x005a }
            r1.<init>()     // Catch:{ all -> 0x005a }
            java.lang.String r2 = "releaseTbsInstallingFileLock currentTbsFileLockStackCount="
            r1.append(r2)     // Catch:{ all -> 0x005a }
            int r2 = r3.e     // Catch:{ all -> 0x005a }
            r1.append(r2)     // Catch:{ all -> 0x005a }
            java.lang.String r2 = "call stack:"
            r1.append(r2)     // Catch:{ all -> 0x005a }
            java.lang.Throwable r2 = new java.lang.Throwable     // Catch:{ all -> 0x005a }
            r2.<init>()     // Catch:{ all -> 0x005a }
            java.lang.String r2 = android.util.Log.getStackTraceString(r2)     // Catch:{ all -> 0x005a }
            r1.append(r2)     // Catch:{ all -> 0x005a }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x005a }
            com.tencent.smtt.utils.TbsLog.i(r0, r1)     // Catch:{ all -> 0x005a }
            monitor-exit(r3)
            return
        L_0x0030:
            int r0 = r3.e     // Catch:{ all -> 0x005a }
            r1 = 1
            if (r0 <= r1) goto L_0x0043
            java.lang.String r0 = "TbsInstaller"
            java.lang.String r2 = "releaseTbsInstallingFileLock with skip"
            com.tencent.smtt.utils.TbsLog.i(r0, r2)     // Catch:{ all -> 0x005a }
            int r0 = r3.e     // Catch:{ all -> 0x005a }
            int r0 = r0 - r1
            r3.e = r0     // Catch:{ all -> 0x005a }
            monitor-exit(r3)
            return
        L_0x0043:
            int r0 = r3.e     // Catch:{ all -> 0x005a }
            if (r0 != r1) goto L_0x0058
            java.lang.String r0 = "TbsInstaller"
            java.lang.String r1 = "releaseTbsInstallingFileLock without skip"
            com.tencent.smtt.utils.TbsLog.i(r0, r1)     // Catch:{ all -> 0x005a }
            java.nio.channels.FileLock r0 = r3.f     // Catch:{ all -> 0x005a }
            java.io.FileOutputStream r1 = r3.g     // Catch:{ all -> 0x005a }
            com.tencent.smtt.utils.FileUtil.a((java.nio.channels.FileLock) r0, (java.io.FileOutputStream) r1)     // Catch:{ all -> 0x005a }
            r0 = 0
            r3.e = r0     // Catch:{ all -> 0x005a }
        L_0x0058:
            monitor-exit(r3)
            return
        L_0x005a:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.o.b():void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x03cf  */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x03d3  */
    /* JADX WARNING: Removed duplicated region for block: B:207:0x04ff  */
    /* JADX WARNING: Removed duplicated region for block: B:209:0x0504  */
    /* JADX WARNING: Removed duplicated region for block: B:223:0x057c  */
    /* JADX WARNING: Removed duplicated region for block: B:225:0x0580  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(android.content.Context r25, android.os.Bundle r26) {
        /*
            r24 = this;
            r1 = r24
            r2 = r25
            r0 = r26
            java.lang.String r3 = "tpatch_num"
            java.lang.String r4 = "tbs_core_ver"
            java.lang.String r5 = "apk_path"
            java.lang.String r6 = "TbsInstaller-installLocalTesCoreExInThread PATCH_SUCCESS"
            java.lang.String r7 = "decouple incrUpdate fail! patch ret="
            java.lang.String r8 = "incrUpdate fail! patch ret="
            java.lang.String r9 = "TbsInstaller-installLocalTesCoreExInThread PATCH_FAIL"
            java.lang.String r10 = "TbsInstaller-installLocalTesCoreExInThread PATCH_NONEEDPATCH"
            java.lang.String r11 = "tbs_needdownload"
            java.lang.String r12 = "incrupdate_retry_num"
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.lang.String r14 = "TbsInstaller installLocalTbsCoreExInThreadthread "
            r13.append(r14)
            java.lang.Thread r14 = java.lang.Thread.currentThread()
            java.lang.String r14 = r14.getName()
            r13.append(r14)
            java.lang.Throwable r14 = new java.lang.Throwable
            r14.<init>()
            java.lang.String r14 = android.util.Log.getStackTraceString(r14)
            r13.append(r14)
            java.lang.String r13 = r13.toString()
            java.lang.String r14 = "TbsInstaller"
            com.tencent.smtt.utils.TbsLog.i(r14, r13)
            boolean r13 = r24.d(r25)
            if (r13 == 0) goto L_0x0054
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r25)
            r2 = -539(0xfffffffffffffde5, float:NaN)
            r0.setInstallInterruptCode(r2)
            return
        L_0x0054:
            java.lang.String r13 = "TbsInstaller-installLocalTesCoreExInThread"
            com.tencent.smtt.utils.TbsLog.i(r14, r13)
            if (r0 == 0) goto L_0x0631
            if (r2 != 0) goto L_0x005f
            goto L_0x0631
        L_0x005f:
            boolean r13 = com.tencent.smtt.utils.FileUtil.b((android.content.Context) r25)
            if (r13 != 0) goto L_0x009d
            long r3 = com.tencent.smtt.utils.p.a()
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r25)
            long r5 = r0.getDownloadMinFreeSpace()
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r25)
            r7 = 210(0xd2, float:2.94E-43)
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "rom is not enough when patching tbs core! curAvailROM="
            r8.append(r9)
            r8.append(r3)
            java.lang.String r3 = ",minReqRom="
            r8.append(r3)
            r8.append(r5)
            java.lang.String r3 = r8.toString()
            r0.setInstallErrorCode((int) r7, (java.lang.String) r3)
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r25)
            r2 = -540(0xfffffffffffffde4, float:NaN)
            r0.setInstallInterruptCode(r2)
            return
        L_0x009d:
            boolean r13 = r24.u(r25)
            if (r13 != 0) goto L_0x00ad
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r25)
            r2 = -541(0xfffffffffffffde3, float:NaN)
            r0.setInstallInterruptCode(r2)
            return
        L_0x00ad:
            java.util.concurrent.locks.Lock r13 = j
            boolean r13 = r13.tryLock()
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            java.lang.String r0 = "TbsInstaller-installLocalTesCoreExInThread locked="
            r15.append(r0)
            r15.append(r13)
            java.lang.String r0 = r15.toString()
            com.tencent.smtt.utils.TbsLog.i(r14, r0)
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r25)
            if (r13 == 0) goto L_0x0629
            android.content.SharedPreferences r0 = r0.mPreferences
            java.lang.String r13 = "tbs_responsecode"
            r15 = 0
            int r13 = r0.getInt(r13, r15)
            r15 = 1
            com.tencent.smtt.sdk.QbSdk.setTBSInstallingStatus(r15)     // Catch:{ Exception -> 0x04b6, all -> 0x04aa }
            r15 = 5
            if (r13 != r15) goto L_0x01e3
            int r15 = r24.c((android.content.Context) r25, (android.os.Bundle) r26)     // Catch:{ Exception -> 0x01d7, all -> 0x01c7 }
            r18 = r8
            r8 = 1
            if (r15 != r8) goto L_0x010b
            com.tencent.smtt.sdk.m r0 = com.tencent.smtt.sdk.m.a((android.content.Context) r25)     // Catch:{ Exception -> 0x0103, all -> 0x00fa }
            int r0 = r0.c((java.lang.String) r3)     // Catch:{ Exception -> 0x0103, all -> 0x00fa }
            com.tencent.smtt.sdk.m r8 = com.tencent.smtt.sdk.m.a((android.content.Context) r25)     // Catch:{ Exception -> 0x0103, all -> 0x00fa }
            r17 = 1
            int r0 = r0 + 1
            r8.a((java.lang.String) r3, (int) r0)     // Catch:{ Exception -> 0x0103, all -> 0x00fa }
            goto L_0x010b
        L_0x00fa:
            r0 = move-exception
            r3 = r7
            r8 = r11
            r11 = r18
        L_0x00ff:
            r7 = r0
            r0 = 0
            goto L_0x056f
        L_0x0103:
            r0 = move-exception
            r3 = r7
            r8 = r11
            r7 = r15
            r11 = r18
            goto L_0x04be
        L_0x010b:
            java.util.concurrent.locks.Lock r0 = j
            r0.unlock()
            r24.b()
            r3 = 5
            if (r13 != r3) goto L_0x011a
            r1.h(r2, r15)
            return
        L_0x011a:
            if (r15 != 0) goto L_0x0163
            com.tencent.smtt.utils.TbsLog.i(r14, r6)
            com.tencent.smtt.sdk.m r0 = com.tencent.smtt.sdk.m.a((android.content.Context) r25)
            r3 = 0
            r0.a((java.lang.String) r12, (int) r3)
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r25)
            r6 = -544(0xfffffffffffffde0, float:NaN)
            r0.setInstallInterruptCode(r6)
            com.tencent.smtt.sdk.m r0 = com.tencent.smtt.sdk.m.a((android.content.Context) r25)
            r6 = -1
            r0.c(r3, r6)
            com.tencent.smtt.sdk.m r0 = com.tencent.smtt.sdk.m.a((android.content.Context) r25)
            r3 = 1
            r0.c((int) r3)
            r3 = 0
            java.lang.String r0 = r3.getString(r5)
            java.io.File r5 = new java.io.File
            r5.<init>(r0)
            com.tencent.smtt.sdk.l.a((java.io.File) r5, (android.content.Context) r2)
            int r3 = r3.getInt(r4)
            r1.b((android.content.Context) r2, (java.lang.String) r0, (int) r3)
            boolean r0 = com.tencent.smtt.sdk.TbsDownloader.a((android.content.Context) r25)
            if (r0 == 0) goto L_0x0169
            com.tencent.smtt.sdk.m r0 = com.tencent.smtt.sdk.m.a((android.content.Context) r25)
            r2 = -1
            r0.c((int) r2)
            goto L_0x0169
        L_0x0163:
            r3 = 2
            if (r15 != r3) goto L_0x016b
            com.tencent.smtt.utils.TbsLog.i(r14, r10)
        L_0x0169:
            r2 = 0
            goto L_0x01c3
        L_0x016b:
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r25)
            r3 = -546(0xfffffffffffffdde, float:NaN)
            r0.setInstallInterruptCode(r3)
            com.tencent.smtt.utils.TbsLog.i(r14, r9)
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r25)
            java.util.Map<java.lang.String, java.lang.Object> r0 = r0.mSyncMap
            r3 = 1
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)
            r0.put(r11, r3)
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r25)
            r0.commit()
            boolean r0 = com.tencent.smtt.sdk.TbsDownloader.a((android.content.Context) r25)
            if (r0 == 0) goto L_0x01a8
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r25)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r7)
            r2.append(r15)
            java.lang.String r2 = r2.toString()
            r3 = 235(0xeb, float:3.3E-43)
            goto L_0x01bf
        L_0x01a8:
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r25)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r8 = r18
            r2.append(r8)
            r2.append(r15)
            java.lang.String r2 = r2.toString()
            r3 = 217(0xd9, float:3.04E-43)
        L_0x01bf:
            r0.setInstallErrorCode((int) r3, (java.lang.String) r2)
            goto L_0x0169
        L_0x01c3:
            com.tencent.smtt.sdk.QbSdk.setTBSInstallingStatus(r2)
            return
        L_0x01c7:
            r0 = move-exception
            r3 = 0
            r15 = 2
            r22 = r7
            r7 = r0
            r0 = r3
            r3 = r22
            r23 = r11
            r11 = r8
            r8 = r23
            goto L_0x056f
        L_0x01d7:
            r0 = move-exception
            r3 = 0
            r15 = r3
            r3 = r7
            r7 = 2
        L_0x01dc:
            r22 = r11
            r11 = r8
            r8 = r22
            goto L_0x04bf
        L_0x01e3:
            r3 = 0
            int r0 = r24.j(r25)     // Catch:{ Exception -> 0x04b6, all -> 0x04aa }
            if (r0 <= 0) goto L_0x0484
            com.tencent.smtt.sdk.m r0 = com.tencent.smtt.sdk.m.a((android.content.Context) r25)     // Catch:{ Exception -> 0x04b6, all -> 0x04aa }
            int r0 = r0.d()     // Catch:{ Exception -> 0x04b6, all -> 0x04aa }
            r15 = 1
            if (r0 != r15) goto L_0x01f7
            goto L_0x0484
        L_0x01f7:
            if (r13 == r15) goto L_0x0202
            r15 = 2
            if (r13 == r15) goto L_0x0202
            r0 = 4
            if (r13 != r0) goto L_0x0200
            goto L_0x0202
        L_0x0200:
            r0 = 0
            goto L_0x0203
        L_0x0202:
            r0 = 1
        L_0x0203:
            if (r0 != 0) goto L_0x03bc
            if (r13 == 0) goto L_0x03bc
            com.tencent.smtt.sdk.m r0 = com.tencent.smtt.sdk.m.a((android.content.Context) r25)     // Catch:{ Exception -> 0x03b6, all -> 0x03aa }
            int r0 = r0.c((java.lang.String) r12)     // Catch:{ Exception -> 0x03b6, all -> 0x03aa }
            r15 = 5
            if (r0 <= r15) goto L_0x028f
            java.lang.String r0 = "TbsInstaller-installLocalTesCoreExInThread exceed incrupdate num"
            com.tencent.smtt.utils.TbsLog.i(r14, r0)     // Catch:{ Exception -> 0x03b6, all -> 0x03aa }
            java.lang.String r0 = "old_apk_location"
            r15 = r26
            java.lang.String r0 = r15.getString(r0)     // Catch:{ Exception -> 0x03b6, all -> 0x03aa }
            java.lang.String r3 = "new_apk_location"
            java.lang.String r3 = r15.getString(r3)     // Catch:{ Exception -> 0x03b6, all -> 0x03aa }
            r18 = r8
            java.lang.String r8 = "diff_file_location"
            java.lang.String r8 = r15.getString(r8)     // Catch:{ Exception -> 0x03a3, all -> 0x039b }
            boolean r15 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x03a3, all -> 0x039b }
            if (r15 != 0) goto L_0x023b
            java.io.File r15 = new java.io.File     // Catch:{ Exception -> 0x03a3, all -> 0x039b }
            r15.<init>(r0)     // Catch:{ Exception -> 0x03a3, all -> 0x039b }
            com.tencent.smtt.utils.FileUtil.b((java.io.File) r15)     // Catch:{ Exception -> 0x03a3, all -> 0x039b }
        L_0x023b:
            boolean r0 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Exception -> 0x03a3, all -> 0x039b }
            if (r0 != 0) goto L_0x0249
            java.io.File r0 = new java.io.File     // Catch:{ Exception -> 0x03a3, all -> 0x039b }
            r0.<init>(r3)     // Catch:{ Exception -> 0x03a3, all -> 0x039b }
            com.tencent.smtt.utils.FileUtil.b((java.io.File) r0)     // Catch:{ Exception -> 0x03a3, all -> 0x039b }
        L_0x0249:
            boolean r0 = android.text.TextUtils.isEmpty(r8)     // Catch:{ Exception -> 0x03a3, all -> 0x039b }
            if (r0 != 0) goto L_0x0257
            java.io.File r0 = new java.io.File     // Catch:{ Exception -> 0x03a3, all -> 0x039b }
            r0.<init>(r8)     // Catch:{ Exception -> 0x03a3, all -> 0x039b }
            com.tencent.smtt.utils.FileUtil.b((java.io.File) r0)     // Catch:{ Exception -> 0x03a3, all -> 0x039b }
        L_0x0257:
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r25)     // Catch:{ Exception -> 0x03a3, all -> 0x039b }
            java.util.Map<java.lang.String, java.lang.Object> r0 = r0.mSyncMap     // Catch:{ Exception -> 0x03a3, all -> 0x039b }
            r3 = 1
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r3)     // Catch:{ Exception -> 0x03a3, all -> 0x039b }
            r0.put(r11, r8)     // Catch:{ Exception -> 0x03a3, all -> 0x039b }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r25)     // Catch:{ Exception -> 0x03a3, all -> 0x039b }
            r0.commit()     // Catch:{ Exception -> 0x03a3, all -> 0x039b }
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r25)     // Catch:{ Exception -> 0x03a3, all -> 0x039b }
            r3 = 224(0xe0, float:3.14E-43)
            java.lang.String r8 = "incrUpdate exceed retry max num"
            r0.setInstallErrorCode((int) r3, (java.lang.String) r8)     // Catch:{ Exception -> 0x03a3, all -> 0x039b }
            java.util.concurrent.locks.Lock r0 = j
            r0.unlock()
            r24.b()
            r3 = 5
            if (r13 != r3) goto L_0x0287
            r3 = 2
            r1.h(r2, r3)
            return
        L_0x0287:
            com.tencent.smtt.utils.TbsLog.i(r14, r10)
            r2 = 0
            com.tencent.smtt.sdk.QbSdk.setTBSInstallingStatus(r2)
            return
        L_0x028f:
            r15 = r26
            r18 = r8
            com.tencent.smtt.sdk.m r3 = com.tencent.smtt.sdk.m.a((android.content.Context) r25)     // Catch:{ Exception -> 0x03a3, all -> 0x039b }
            r8 = 1
            int r0 = r0 + r8
            r3.a((java.lang.String) r12, (int) r0)     // Catch:{ Exception -> 0x03a3, all -> 0x039b }
            java.io.File r0 = t(r25)     // Catch:{ Exception -> 0x03a3, all -> 0x039b }
            if (r0 == 0) goto L_0x0398
            java.io.File r3 = new java.io.File     // Catch:{ Exception -> 0x03a3, all -> 0x039b }
            java.lang.String r8 = "x5.tbs"
            r3.<init>(r0, r8)     // Catch:{ Exception -> 0x03a3, all -> 0x039b }
            boolean r0 = r3.exists()     // Catch:{ Exception -> 0x03a3, all -> 0x039b }
            if (r0 == 0) goto L_0x0398
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r25)     // Catch:{ Exception -> 0x03a3, all -> 0x039b }
            r3 = -550(0xfffffffffffffdda, float:NaN)
            r0.setInstallInterruptCode(r3)     // Catch:{ Exception -> 0x03a3, all -> 0x039b }
            android.os.Bundle r3 = com.tencent.smtt.sdk.QbSdk.a((android.content.Context) r25, (android.os.Bundle) r26)     // Catch:{ Exception -> 0x03a3, all -> 0x039b }
            java.lang.String r0 = "new_core_ver"
            if (r3 != 0) goto L_0x0322
            com.tencent.smtt.sdk.TbsLogReport r8 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r25)     // Catch:{ Exception -> 0x0319, all -> 0x030c }
            r19 = r7
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0304, all -> 0x02fa }
            r7.<init>()     // Catch:{ Exception -> 0x0304, all -> 0x02fa }
            r20 = r11
            java.lang.String r11 = "result null : "
            r7.append(r11)     // Catch:{ Exception -> 0x02f1, all -> 0x02e6 }
            int r0 = r15.getInt(r0)     // Catch:{ Exception -> 0x02f1, all -> 0x02e6 }
            r7.append(r0)     // Catch:{ Exception -> 0x02f1, all -> 0x02e6 }
            java.lang.String r0 = r7.toString()     // Catch:{ Exception -> 0x02f1, all -> 0x02e6 }
            r7 = 228(0xe4, float:3.2E-43)
            r8.setInstallErrorCode((int) r7, (java.lang.String) r0)     // Catch:{ Exception -> 0x02f1, all -> 0x02e6 }
            r15 = r3
            r7 = 1
            goto L_0x03c4
        L_0x02e6:
            r0 = move-exception
            r7 = r0
            r0 = r3
            r11 = r18
            r3 = r19
            r8 = r20
            goto L_0x0563
        L_0x02f1:
            r0 = move-exception
            r15 = r3
            r11 = r18
            r3 = r19
            r8 = r20
            goto L_0x031f
        L_0x02fa:
            r0 = move-exception
            r7 = r0
            r0 = r3
            r8 = r11
            r11 = r18
            r3 = r19
            goto L_0x0563
        L_0x0304:
            r0 = move-exception
            r15 = r3
            r8 = r11
            r11 = r18
            r3 = r19
            goto L_0x031f
        L_0x030c:
            r0 = move-exception
            r8 = r11
            r11 = r18
            r15 = 1
            r22 = r7
            r7 = r0
            r0 = r3
            r3 = r22
            goto L_0x056f
        L_0x0319:
            r0 = move-exception
            r15 = r3
            r3 = r7
            r8 = r11
            r11 = r18
        L_0x031f:
            r7 = 1
            goto L_0x04bf
        L_0x0322:
            r19 = r7
            r20 = r11
            java.lang.String r7 = "patch_result"
            int r7 = r3.getInt(r7)     // Catch:{ Exception -> 0x038a, all -> 0x037c }
            if (r7 == 0) goto L_0x0377
            com.tencent.smtt.sdk.TbsLogReport r8 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r25)     // Catch:{ Exception -> 0x036a, all -> 0x035b }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x036a, all -> 0x035b }
            r11.<init>()     // Catch:{ Exception -> 0x036a, all -> 0x035b }
            r21 = r3
            java.lang.String r3 = "result "
            r11.append(r3)     // Catch:{ Exception -> 0x0359, all -> 0x0357 }
            r11.append(r7)     // Catch:{ Exception -> 0x0359, all -> 0x0357 }
            java.lang.String r3 = " : "
            r11.append(r3)     // Catch:{ Exception -> 0x0359, all -> 0x0357 }
            int r0 = r15.getInt(r0)     // Catch:{ Exception -> 0x0359, all -> 0x0357 }
            r11.append(r0)     // Catch:{ Exception -> 0x0359, all -> 0x0357 }
            java.lang.String r0 = r11.toString()     // Catch:{ Exception -> 0x0359, all -> 0x0357 }
            r3 = 228(0xe4, float:3.2E-43)
            r8.setInstallErrorCode((int) r3, (java.lang.String) r0)     // Catch:{ Exception -> 0x0359, all -> 0x0357 }
            goto L_0x0379
        L_0x0357:
            r0 = move-exception
            goto L_0x035e
        L_0x0359:
            r0 = move-exception
            goto L_0x036d
        L_0x035b:
            r0 = move-exception
            r21 = r3
        L_0x035e:
            r15 = r7
            r11 = r18
            r3 = r19
            r8 = r20
            r7 = r0
            r0 = r21
            goto L_0x056f
        L_0x036a:
            r0 = move-exception
            r21 = r3
        L_0x036d:
            r11 = r18
            r3 = r19
            r8 = r20
            r15 = r21
            goto L_0x04bf
        L_0x0377:
            r21 = r3
        L_0x0379:
            r15 = r21
            goto L_0x03c4
        L_0x037c:
            r0 = move-exception
            r21 = r3
            r7 = r0
            r11 = r18
            r3 = r19
            r8 = r20
            r0 = r21
            goto L_0x04b3
        L_0x038a:
            r0 = move-exception
            r21 = r3
            r11 = r18
            r3 = r19
            r8 = r20
            r15 = r21
            r7 = 2
            goto L_0x04bf
        L_0x0398:
            r19 = r7
            goto L_0x03c0
        L_0x039b:
            r0 = move-exception
            r3 = r7
            r8 = r11
            r11 = r18
            r15 = 2
            goto L_0x00ff
        L_0x03a3:
            r0 = move-exception
            r3 = r7
            r8 = r11
            r11 = r18
            goto L_0x04bd
        L_0x03aa:
            r0 = move-exception
            r3 = r7
            r15 = 2
            r7 = r0
            r0 = 0
            r22 = r11
            r11 = r8
            r8 = r22
            goto L_0x056f
        L_0x03b6:
            r0 = move-exception
            r3 = r7
            r7 = 2
            r15 = 0
            goto L_0x01dc
        L_0x03bc:
            r19 = r7
            r18 = r8
        L_0x03c0:
            r20 = r11
            r7 = 2
            r15 = 0
        L_0x03c4:
            java.util.concurrent.locks.Lock r0 = j
            r0.unlock()
            r24.b()
            r3 = 5
            if (r13 != r3) goto L_0x03d3
            r1.h(r2, r7)
            return
        L_0x03d3:
            if (r7 != 0) goto L_0x041b
            com.tencent.smtt.utils.TbsLog.i(r14, r6)
            com.tencent.smtt.sdk.m r0 = com.tencent.smtt.sdk.m.a((android.content.Context) r25)
            r3 = 0
            r0.a((java.lang.String) r12, (int) r3)
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r25)
            r6 = -544(0xfffffffffffffde0, float:NaN)
            r0.setInstallInterruptCode(r6)
            com.tencent.smtt.sdk.m r0 = com.tencent.smtt.sdk.m.a((android.content.Context) r25)
            r6 = -1
            r0.c(r3, r6)
            com.tencent.smtt.sdk.m r0 = com.tencent.smtt.sdk.m.a((android.content.Context) r25)
            r3 = 1
            r0.c((int) r3)
            java.lang.String r0 = r15.getString(r5)
            java.io.File r3 = new java.io.File
            r3.<init>(r0)
            com.tencent.smtt.sdk.l.a((java.io.File) r3, (android.content.Context) r2)
            int r3 = r15.getInt(r4)
            r1.b((android.content.Context) r2, (java.lang.String) r0, (int) r3)
            boolean r0 = com.tencent.smtt.sdk.TbsDownloader.a((android.content.Context) r25)
            if (r0 == 0) goto L_0x0421
            com.tencent.smtt.sdk.m r0 = com.tencent.smtt.sdk.m.a((android.content.Context) r25)
            r2 = -1
            r0.c((int) r2)
            goto L_0x0421
        L_0x041b:
            r3 = 2
            if (r7 != r3) goto L_0x0423
            com.tencent.smtt.utils.TbsLog.i(r14, r10)
        L_0x0421:
            r7 = 0
            goto L_0x047f
        L_0x0423:
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r25)
            r3 = -546(0xfffffffffffffdde, float:NaN)
            r0.setInstallInterruptCode(r3)
            com.tencent.smtt.utils.TbsLog.i(r14, r9)
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r25)
            java.util.Map<java.lang.String, java.lang.Object> r0 = r0.mSyncMap
            r3 = 1
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)
            r8 = r20
            r0.put(r8, r3)
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r25)
            r0.commit()
            boolean r0 = com.tencent.smtt.sdk.TbsDownloader.a((android.content.Context) r25)
            if (r0 == 0) goto L_0x0464
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r25)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r3 = r19
            r2.append(r3)
            r2.append(r7)
            java.lang.String r2 = r2.toString()
            r3 = 235(0xeb, float:3.3E-43)
            goto L_0x047b
        L_0x0464:
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r25)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r11 = r18
            r2.append(r11)
            r2.append(r7)
            java.lang.String r2 = r2.toString()
            r3 = 217(0xd9, float:3.04E-43)
        L_0x047b:
            r0.setInstallErrorCode((int) r3, (java.lang.String) r2)
            goto L_0x0421
        L_0x047f:
            com.tencent.smtt.sdk.QbSdk.setTBSInstallingStatus(r7)
            goto L_0x0631
        L_0x0484:
            r3 = r7
            r7 = 0
            r22 = r11
            r11 = r8
            r8 = r22
            com.tencent.smtt.sdk.QbSdk.setTBSInstallingStatus(r7)     // Catch:{ Exception -> 0x04a8, all -> 0x04a6 }
            java.util.concurrent.locks.Lock r0 = j
            r0.unlock()
            r24.b()
            r3 = 5
            if (r13 != r3) goto L_0x049e
            r3 = 2
            r1.h(r2, r3)
            return
        L_0x049e:
            com.tencent.smtt.utils.TbsLog.i(r14, r10)
            r2 = 0
            com.tencent.smtt.sdk.QbSdk.setTBSInstallingStatus(r2)
            return
        L_0x04a6:
            r0 = move-exception
            goto L_0x04b1
        L_0x04a8:
            r0 = move-exception
            goto L_0x04bd
        L_0x04aa:
            r0 = move-exception
            r3 = r7
            r22 = r11
            r11 = r8
            r8 = r22
        L_0x04b1:
            r7 = r0
            r0 = 0
        L_0x04b3:
            r15 = 2
            goto L_0x056f
        L_0x04b6:
            r0 = move-exception
            r3 = r7
            r22 = r11
            r11 = r8
            r8 = r22
        L_0x04bd:
            r7 = 2
        L_0x04be:
            r15 = 0
        L_0x04bf:
            r26 = r7
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x0567 }
            r7.<init>()     // Catch:{ all -> 0x0567 }
            r16 = r15
            java.lang.String r15 = "installLocalTbsCoreExInThread exception:"
            r7.append(r15)     // Catch:{ all -> 0x0565 }
            java.lang.String r15 = android.util.Log.getStackTraceString(r0)     // Catch:{ all -> 0x0565 }
            r7.append(r15)     // Catch:{ all -> 0x0565 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x0565 }
            com.tencent.smtt.utils.TbsLog.i(r14, r7)     // Catch:{ all -> 0x0565 }
            r0.printStackTrace()     // Catch:{ all -> 0x0565 }
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r25)     // Catch:{ all -> 0x055f }
            r15 = -543(0xfffffffffffffde1, float:NaN)
            r7.setInstallInterruptCode(r15)     // Catch:{ all -> 0x055f }
            com.tencent.smtt.sdk.TbsLogReport r7 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r25)     // Catch:{ all -> 0x055f }
            r15 = 218(0xda, float:3.05E-43)
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x055f }
            r7.setInstallErrorCode((int) r15, (java.lang.String) r0)     // Catch:{ all -> 0x055f }
            java.util.concurrent.locks.Lock r0 = j
            r0.unlock()
            r24.b()
            r4 = 5
            if (r13 != r4) goto L_0x0504
            r4 = 1
            r1.h(r2, r4)
            return
        L_0x0504:
            r4 = 1
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r25)
            r5 = -546(0xfffffffffffffdde, float:NaN)
            r0.setInstallInterruptCode(r5)
            com.tencent.smtt.utils.TbsLog.i(r14, r9)
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r25)
            java.util.Map<java.lang.String, java.lang.Object> r0 = r0.mSyncMap
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r4)
            r0.put(r8, r5)
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r25)
            r0.commit()
            boolean r0 = com.tencent.smtt.sdk.TbsDownloader.a((android.content.Context) r25)
            if (r0 == 0) goto L_0x0541
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r25)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r3)
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            r3 = 235(0xeb, float:3.3E-43)
            goto L_0x0556
        L_0x0541:
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r25)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r11)
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            r3 = 217(0xd9, float:3.04E-43)
        L_0x0556:
            r0.setInstallErrorCode((int) r3, (java.lang.String) r2)
            r2 = 0
            com.tencent.smtt.sdk.QbSdk.setTBSInstallingStatus(r2)
            goto L_0x0631
        L_0x055f:
            r0 = move-exception
            r7 = r0
            r0 = r16
        L_0x0563:
            r15 = 1
            goto L_0x056f
        L_0x0565:
            r0 = move-exception
            goto L_0x056a
        L_0x0567:
            r0 = move-exception
            r16 = r15
        L_0x056a:
            r15 = r26
            r7 = r0
            r0 = r16
        L_0x056f:
            java.util.concurrent.locks.Lock r16 = j
            r16.unlock()
            r24.b()
            r26 = r7
            r7 = 5
            if (r13 != r7) goto L_0x0580
            r1.h(r2, r15)
            return
        L_0x0580:
            if (r15 != 0) goto L_0x05c8
            com.tencent.smtt.utils.TbsLog.i(r14, r6)
            com.tencent.smtt.sdk.m r3 = com.tencent.smtt.sdk.m.a((android.content.Context) r25)
            r6 = 0
            r3.a((java.lang.String) r12, (int) r6)
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r25)
            r7 = -544(0xfffffffffffffde0, float:NaN)
            r3.setInstallInterruptCode(r7)
            com.tencent.smtt.sdk.m r3 = com.tencent.smtt.sdk.m.a((android.content.Context) r25)
            r7 = -1
            r3.c(r6, r7)
            com.tencent.smtt.sdk.m r3 = com.tencent.smtt.sdk.m.a((android.content.Context) r25)
            r6 = 1
            r3.c((int) r6)
            java.lang.String r3 = r0.getString(r5)
            java.io.File r5 = new java.io.File
            r5.<init>(r3)
            com.tencent.smtt.sdk.l.a((java.io.File) r5, (android.content.Context) r2)
            int r0 = r0.getInt(r4)
            r1.b((android.content.Context) r2, (java.lang.String) r3, (int) r0)
            boolean r0 = com.tencent.smtt.sdk.TbsDownloader.a((android.content.Context) r25)
            if (r0 == 0) goto L_0x0624
            com.tencent.smtt.sdk.m r0 = com.tencent.smtt.sdk.m.a((android.content.Context) r25)
            r2 = -1
            r0.c((int) r2)
            goto L_0x0624
        L_0x05c8:
            r4 = 2
            if (r15 == r4) goto L_0x0621
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r25)
            r4 = -546(0xfffffffffffffdde, float:NaN)
            r0.setInstallInterruptCode(r4)
            com.tencent.smtt.utils.TbsLog.i(r14, r9)
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r25)
            java.util.Map<java.lang.String, java.lang.Object> r0 = r0.mSyncMap
            r4 = 1
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)
            r0.put(r8, r4)
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r25)
            r0.commit()
            boolean r0 = com.tencent.smtt.sdk.TbsDownloader.a((android.content.Context) r25)
            if (r0 == 0) goto L_0x0608
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r25)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r3)
            r2.append(r15)
            java.lang.String r2 = r2.toString()
            r3 = 235(0xeb, float:3.3E-43)
            goto L_0x061d
        L_0x0608:
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r25)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r11)
            r2.append(r15)
            java.lang.String r2 = r2.toString()
            r3 = 217(0xd9, float:3.04E-43)
        L_0x061d:
            r0.setInstallErrorCode((int) r3, (java.lang.String) r2)
            goto L_0x0624
        L_0x0621:
            com.tencent.smtt.utils.TbsLog.i(r14, r10)
        L_0x0624:
            r2 = 0
            com.tencent.smtt.sdk.QbSdk.setTBSInstallingStatus(r2)
            throw r26
        L_0x0629:
            r2 = -547(0xfffffffffffffddd, float:NaN)
            r0.setInstallInterruptCode(r2)
            r24.b()
        L_0x0631:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.o.b(android.content.Context, android.os.Bundle):void");
    }

    public void b(Context context, File file, int i2) {
        FileOutputStream b2 = FileUtil.b(context, true, "core_unzip.lock");
        FileLock a2 = FileUtil.a(context, b2);
        if (a2 != null) {
            TbsLog.i("TbsInstaller", "unzipTbsCoreToThirdAppTmpInThread #1");
            boolean a3 = a(context, file, false);
            TbsLog.i("TbsInstaller", "unzipTbsCoreToThirdAppTmpInThread result is " + a3);
            if (a3) {
                a().a(context, i2);
            }
            FileUtil.a(a2, b2);
            return;
        }
        TbsLog.i("TbsInstaller", "can not get Core unzip FileLock,skip!!!");
    }

    /* access modifiers changed from: package-private */
    public void b(Context context, boolean z) {
        String str;
        if (!QbSdk.b) {
            if (Build.VERSION.SDK_INT < 8) {
                TbsLog.e("TbsInstaller", "android version < 2.1 no need install X5 core", true);
                return;
            }
            TbsLog.i("TbsInstaller", "Tbsinstaller installTbsCoreIfNeeded #1 ");
            if (TbsShareManager.isThirdPartyApp(context) && m.a(context).b("remove_old_core") == 1 && z) {
                try {
                    FileUtil.b(a().r(context));
                    TbsLog.i("TbsInstaller", "thirdAPP success--> delete old core_share Directory");
                } catch (Throwable th) {
                    th.printStackTrace();
                }
                m.a(context).a("remove_old_core", 0);
            }
            if (w(context)) {
                TbsLog.i("TbsInstaller", "Tbsinstaller installTbsCoreIfNeeded #2 ");
                if (a(context, "core_unzip_tmp") && e(context, z)) {
                    str = "TbsInstaller-installTbsCoreIfNeeded, enableTbsCoreFromUnzip!!";
                } else if (a(context, "core_share_backup_tmp") && f(context, z)) {
                    str = "TbsInstaller-installTbsCoreIfNeeded, enableTbsCoreFromBackup!!";
                } else if (a(context, "core_copy_tmp") && d(context, z)) {
                    str = "TbsInstaller-installTbsCoreIfNeeded, enableTbsCoreFromCopy!!";
                } else if (a(context, "tpatch_tmp") && c(context, z)) {
                    str = "TbsInstaller-installTbsCoreIfNeeded, enableTbsCoreFromTpatch!!";
                } else {
                    TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreIfNeeded, error !!", true);
                    return;
                }
                TbsLog.i("TbsInstaller", str, true);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean b(Context context, int i2) {
        if (TbsDownloader.getOverSea(context)) {
            return false;
        }
        TbsLog.i("TbsInstaller", "TbsInstaller-installLocalTbsCore targetTbsCoreVer=" + i2);
        TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore currentProcessName=" + context.getApplicationInfo().processName);
        TbsLog.i("TbsInstaller", "TbsInstaller-installLocalTbsCore currentProcessId=" + Process.myPid());
        TbsLog.i("TbsInstaller", "TbsInstaller-installLocalTbsCore currentThreadName=" + Thread.currentThread().getName());
        Context d2 = d(context, i2);
        if (d2 != null) {
            Object[] objArr = {d2, context, Integer.valueOf(i2)};
            Message message = new Message();
            message.what = 2;
            message.obj = objArr;
            m.sendMessage(message);
            return true;
        }
        TbsLog.i("TbsInstaller", "TbsInstaller--installLocalTbsCore copy from null");
        return false;
    }

    /* access modifiers changed from: package-private */
    public int c(Context context, String str) {
        PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(str, 0);
        if (packageArchiveInfo != null) {
            return packageArchiveInfo.versionCode;
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public File c(Context context, Context context2) {
        File file = new File(QbSdk.getTbsFolderDir(context2), "core_share_decouple");
        if (file.isDirectory() || ((context != null && TbsShareManager.isThirdPartyApp(context)) || file.mkdir())) {
            return file;
        }
        return null;
    }

    public void c(Context context) {
        g(context, true);
        m.a(context).c(i(context), 2);
    }

    /* access modifiers changed from: package-private */
    public void c(Context context, int i2) {
        int j2;
        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreForThirdPartyApp");
        if (i2 > 0 && (j2 = j(context)) != i2) {
            Context e2 = TbsShareManager.e(context);
            if (e2 != null || TbsShareManager.getHostCorePathAppDefined() != null) {
                TbsLog.i("TbsInstaller", "TbsInstaller--quickDexOptForThirdPartyApp hostContext != null");
                a(context, e2);
            } else if (j2 <= 0) {
                TbsLog.i("TbsInstaller", "TbsInstaller--installTbsCoreForThirdPartyApp hostContext == null");
                QbSdk.a(context, "TbsInstaller::installTbsCoreForThirdPartyApp forceSysWebViewInner #2");
            }
        }
    }

    public Context d(Context context, int i2) {
        Context b2;
        TbsLog.i("TbsInstaller", "TbsInstaller--getTbsCoreHostContext tbsCoreTargetVer=" + i2);
        if (i2 <= 0) {
            return null;
        }
        String[] coreProviderAppList = TbsShareManager.getCoreProviderAppList();
        for (int i3 = 0; i3 < coreProviderAppList.length; i3++) {
            if (!context.getPackageName().equalsIgnoreCase(coreProviderAppList[i3]) && e(context, coreProviderAppList[i3]) && (b2 = b(context, coreProviderAppList[i3])) != null) {
                if (!g(b2)) {
                    TbsLog.e("TbsInstaller", "TbsInstaller--getTbsCoreHostContext " + coreProviderAppList[i3] + " illegal signature go on next");
                } else {
                    int j2 = j(b2);
                    TbsLog.i("TbsInstaller", "TbsInstaller-getTbsCoreHostContext hostTbsCoreVer=" + j2);
                    if (j2 != 0 && j2 == i2) {
                        TbsLog.i("TbsInstaller", "TbsInstaller-getTbsCoreHostContext targetApp=" + coreProviderAppList[i3]);
                        return b2;
                    }
                }
            }
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x003f A[SYNTHETIC, Splitter:B:19:0x003f] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0046 A[SYNTHETIC, Splitter:B:26:0x0046] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String d(android.content.Context r4, java.lang.String r5) {
        /*
            r3 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r5)
            r1 = 0
            if (r0 == 0) goto L_0x0008
            return r1
        L_0x0008:
            java.io.File r4 = r3.r(r4)     // Catch:{ Exception -> 0x0043, all -> 0x003c }
            java.io.File r0 = new java.io.File     // Catch:{ Exception -> 0x0043, all -> 0x003c }
            java.lang.String r2 = "tbs.conf"
            r0.<init>(r4, r2)     // Catch:{ Exception -> 0x0043, all -> 0x003c }
            boolean r4 = r0.exists()     // Catch:{ Exception -> 0x0043, all -> 0x003c }
            if (r4 != 0) goto L_0x001a
            return r1
        L_0x001a:
            java.util.Properties r4 = new java.util.Properties     // Catch:{ Exception -> 0x0043, all -> 0x003c }
            r4.<init>()     // Catch:{ Exception -> 0x0043, all -> 0x003c }
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0043, all -> 0x003c }
            r2.<init>(r0)     // Catch:{ Exception -> 0x0043, all -> 0x003c }
            java.io.BufferedInputStream r0 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x0043, all -> 0x003c }
            r0.<init>(r2)     // Catch:{ Exception -> 0x0043, all -> 0x003c }
            r4.load(r0)     // Catch:{ Exception -> 0x003a, all -> 0x0037 }
            r0.close()     // Catch:{ Exception -> 0x003a, all -> 0x0037 }
            java.lang.String r4 = r4.getProperty(r5)     // Catch:{ Exception -> 0x003a, all -> 0x0037 }
            r0.close()     // Catch:{ IOException -> 0x0036 }
        L_0x0036:
            return r4
        L_0x0037:
            r4 = move-exception
            r1 = r0
            goto L_0x003d
        L_0x003a:
            goto L_0x0044
        L_0x003c:
            r4 = move-exception
        L_0x003d:
            if (r1 == 0) goto L_0x0042
            r1.close()     // Catch:{ IOException -> 0x0042 }
        L_0x0042:
            throw r4
        L_0x0043:
            r0 = r1
        L_0x0044:
            if (r0 == 0) goto L_0x0049
            r0.close()     // Catch:{ IOException -> 0x0049 }
        L_0x0049:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.o.d(android.content.Context, java.lang.String):java.lang.String");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0080 A[SYNTHETIC, Splitter:B:28:0x0080] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean d(android.content.Context r10) {
        /*
            r9 = this;
            java.io.File r10 = r9.r(r10)
            java.io.File r0 = new java.io.File
            java.lang.String r1 = "tbs.conf"
            r0.<init>(r10, r1)
            boolean r10 = r0.exists()
            r1 = 0
            if (r10 != 0) goto L_0x0013
            return r1
        L_0x0013:
            java.util.Properties r10 = new java.util.Properties
            r10.<init>()
            r2 = 0
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ all -> 0x007a }
            r3.<init>(r0)     // Catch:{ all -> 0x007a }
            java.io.BufferedInputStream r4 = new java.io.BufferedInputStream     // Catch:{ all -> 0x007a }
            r4.<init>(r3)     // Catch:{ all -> 0x007a }
            r10.load(r4)     // Catch:{ all -> 0x0077 }
            java.lang.String r2 = "tbs_local_installation"
            java.lang.String r3 = "false"
            java.lang.String r10 = r10.getProperty(r2, r3)     // Catch:{ all -> 0x0077 }
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r10)     // Catch:{ all -> 0x0077 }
            boolean r10 = r10.booleanValue()     // Catch:{ all -> 0x0077 }
            r2 = 1
            if (r10 == 0) goto L_0x004a
            long r5 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0074 }
            long r7 = r0.lastModified()     // Catch:{ all -> 0x0074 }
            long r5 = r5 - r7
            r7 = 259200000(0xf731400, double:1.280618154E-315)
            int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r0 <= 0) goto L_0x004a
            r1 = 1
        L_0x004a:
            java.lang.String r0 = "TbsInstaller"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0074 }
            r3.<init>()     // Catch:{ all -> 0x0074 }
            java.lang.String r5 = "TBS_LOCAL_INSTALLATION is:"
            r3.append(r5)     // Catch:{ all -> 0x0074 }
            r3.append(r10)     // Catch:{ all -> 0x0074 }
            java.lang.String r5 = " expired="
            r3.append(r5)     // Catch:{ all -> 0x0074 }
            r3.append(r1)     // Catch:{ all -> 0x0074 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0074 }
            com.tencent.smtt.utils.TbsLog.i(r0, r3)     // Catch:{ all -> 0x0074 }
            r0 = r1 ^ 1
            r10 = r10 & r0
            r4.close()     // Catch:{ IOException -> 0x006f }
            goto L_0x0089
        L_0x006f:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0089
        L_0x0074:
            r0 = move-exception
            r1 = r10
            goto L_0x0078
        L_0x0077:
            r0 = move-exception
        L_0x0078:
            r2 = r4
            goto L_0x007b
        L_0x007a:
            r0 = move-exception
        L_0x007b:
            r0.printStackTrace()     // Catch:{ all -> 0x008a }
            if (r2 == 0) goto L_0x0088
            r2.close()     // Catch:{ IOException -> 0x0084 }
            goto L_0x0088
        L_0x0084:
            r10 = move-exception
            r10.printStackTrace()
        L_0x0088:
            r10 = r1
        L_0x0089:
            return r10
        L_0x008a:
            r10 = move-exception
            if (r2 == 0) goto L_0x0095
            r2.close()     // Catch:{ IOException -> 0x0091 }
            goto L_0x0095
        L_0x0091:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0095:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.o.d(android.content.Context):boolean");
    }

    /* access modifiers changed from: package-private */
    public int e(Context context, int i2) {
        return a(f(context, i2));
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0035 */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0040 A[SYNTHETIC, Splitter:B:20:0x0040] */
    /* JADX WARNING: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void e(android.content.Context r6) {
        /*
            r5 = this;
            java.io.File r6 = r5.r(r6)     // Catch:{  }
            java.io.File r0 = new java.io.File     // Catch:{  }
            java.lang.String r1 = "tbs.conf"
            r0.<init>(r6, r1)     // Catch:{  }
            java.util.Properties r6 = new java.util.Properties     // Catch:{  }
            r6.<init>()     // Catch:{  }
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ all -> 0x003d }
            r2.<init>(r0)     // Catch:{ all -> 0x003d }
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch:{ all -> 0x003d }
            r3.<init>(r2)     // Catch:{ all -> 0x003d }
            r6.load(r3)     // Catch:{ all -> 0x003b }
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ all -> 0x003b }
            r2.<init>(r0)     // Catch:{ all -> 0x003b }
            java.io.BufferedOutputStream r0 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x003b }
            r0.<init>(r2)     // Catch:{ all -> 0x003b }
            java.lang.String r2 = "tbs_local_installation"
            java.lang.String r4 = "false"
            r6.setProperty(r2, r4)     // Catch:{ all -> 0x0039 }
            r6.store(r0, r1)     // Catch:{ all -> 0x0039 }
            r0.close()     // Catch:{ IOException -> 0x0035 }
        L_0x0035:
            r3.close()     // Catch:{ all -> 0x0048 }
            goto L_0x0048
        L_0x0039:
            r1 = r0
            goto L_0x003e
        L_0x003b:
            goto L_0x003e
        L_0x003d:
            r3 = r1
        L_0x003e:
            if (r1 == 0) goto L_0x0045
            r1.close()     // Catch:{ IOException -> 0x0044 }
            goto L_0x0045
        L_0x0044:
        L_0x0045:
            if (r3 == 0) goto L_0x0048
            goto L_0x0035
        L_0x0048:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.o.e(android.content.Context):void");
    }

    /* access modifiers changed from: package-private */
    public File f(Context context, int i2) {
        return a(context, i2, true);
    }

    public boolean f(Context context) {
        try {
            File file = new File(FileUtil.a(context, 4), TbsDownloader.getBackupFileName(true));
            File f2 = a().f(context, 2);
            FileUtil.a(f2);
            FileUtil.a(f2, true);
            FileUtil.a(file, f2);
            String[] list = f2.list();
            for (String file2 : list) {
                File file3 = new File(f2, file2);
                if (file3.getName().endsWith(".dex")) {
                    file3.delete();
                }
            }
            i(context, 2);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean g(Context context) {
        if (TbsShareManager.getHostCorePathAppDefined() != null) {
            return true;
        }
        try {
            Signature signature = context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures[0];
            if (context.getPackageName().equals(TbsConfig.APP_QB)) {
                if (!signature.toCharsString().equals("3082023f308201a8a00302010202044c46914a300d06092a864886f70d01010505003064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f301e170d3130303732313036313835305a170d3430303731333036313835305a3064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f30819f300d06092a864886f70d010101050003818d0030818902818100c209077044bd0d63ea00ede5b839914cabcc912a87f0f8b390877e0f7a2583f0d5933443c40431c35a4433bc4c965800141961adc44c9625b1d321385221fd097e5bdc2f44a1840d643ab59dc070cf6c4b4b4d98bed5cbb8046e0a7078ae134da107cdf2bfc9b440fe5cb2f7549b44b73202cc6f7c2c55b8cfb0d333a021f01f0203010001300d06092a864886f70d010105050003818100b007db9922774ef4ccfee81ba514a8d57c410257e7a2eba64bfa17c9e690da08106d32f637ac41fbc9f205176c71bde238c872c3ee2f8313502bee44c80288ea4ef377a6f2cdfe4d3653c145c4acfedbfbadea23b559d41980cc3cdd35d79a68240693739aabf5c5ed26148756cf88264226de394c8a24ac35b712b120d4d23a")) {
                    return false;
                }
            } else if (context.getPackageName().equals(TbsConfig.APP_WX)) {
                if (!signature.toCharsString().equals("308202eb30820254a00302010202044d36f7a4300d06092a864886f70d01010505003081b9310b300906035504061302383631123010060355040813094775616e67646f6e673111300f060355040713085368656e7a68656e31353033060355040a132c54656e63656e7420546563686e6f6c6f6779285368656e7a68656e2920436f6d70616e79204c696d69746564313a3038060355040b133154656e63656e74204775616e677a686f7520526573656172636820616e6420446576656c6f706d656e742043656e7465723110300e0603550403130754656e63656e74301e170d3131303131393134333933325a170d3431303131313134333933325a3081b9310b300906035504061302383631123010060355040813094775616e67646f6e673111300f060355040713085368656e7a68656e31353033060355040a132c54656e63656e7420546563686e6f6c6f6779285368656e7a68656e2920436f6d70616e79204c696d69746564313a3038060355040b133154656e63656e74204775616e677a686f7520526573656172636820616e6420446576656c6f706d656e742043656e7465723110300e0603550403130754656e63656e7430819f300d06092a864886f70d010101050003818d0030818902818100c05f34b231b083fb1323670bfbe7bdab40c0c0a6efc87ef2072a1ff0d60cc67c8edb0d0847f210bea6cbfaa241be70c86daf56be08b723c859e52428a064555d80db448cdcacc1aea2501eba06f8bad12a4fa49d85cacd7abeb68945a5cb5e061629b52e3254c373550ee4e40cb7c8ae6f7a8151ccd8df582d446f39ae0c5e930203010001300d06092a864886f70d0101050500038181009c8d9d7f2f908c42081b4c764c377109a8b2c70582422125ce545842d5f520aea69550b6bd8bfd94e987b75a3077eb04ad341f481aac266e89d3864456e69fba13df018acdc168b9a19dfd7ad9d9cc6f6ace57c746515f71234df3a053e33ba93ece5cd0fc15f3e389a3f365588a9fcb439e069d3629cd7732a13fff7b891499")) {
                    return false;
                }
            } else if (context.getPackageName().equals(TbsConfig.APP_QQ)) {
                if (!signature.toCharsString().equals("30820253308201bca00302010202044bbb0361300d06092a864886f70d0101050500306d310e300c060355040613054368696e61310f300d06035504080c06e58c97e4baac310f300d06035504070c06e58c97e4baac310f300d060355040a0c06e885bee8aeaf311b3019060355040b0c12e697a0e7babfe4b89ae58aa1e7b3bbe7bb9f310b30090603550403130251513020170d3130303430363039343831375a180f32323834303132303039343831375a306d310e300c060355040613054368696e61310f300d06035504080c06e58c97e4baac310f300d06035504070c06e58c97e4baac310f300d060355040a0c06e885bee8aeaf311b3019060355040b0c12e697a0e7babfe4b89ae58aa1e7b3bbe7bb9f310b300906035504031302515130819f300d06092a864886f70d010101050003818d0030818902818100a15e9756216f694c5915e0b529095254367c4e64faeff07ae13488d946615a58ddc31a415f717d019edc6d30b9603d3e2a7b3de0ab7e0cf52dfee39373bc472fa997027d798d59f81d525a69ecf156e885fd1e2790924386b2230cc90e3b7adc95603ddcf4c40bdc72f22db0f216a99c371d3bf89cba6578c60699e8a0d536950203010001300d06092a864886f70d01010505000381810094a9b80e80691645dd42d6611775a855f71bcd4d77cb60a8e29404035a5e00b21bcc5d4a562482126bd91b6b0e50709377ceb9ef8c2efd12cc8b16afd9a159f350bb270b14204ff065d843832720702e28b41491fbc3a205f5f2f42526d67f17614d8a974de6487b2c866efede3b4e49a0f916baa3c1336fd2ee1b1629652049")) {
                    return false;
                }
            } else if (context.getPackageName().equals(TbsConfig.APP_DEMO)) {
                if (!signature.toCharsString().equals("3082023f308201a8a00302010202044c46914a300d06092a864886f70d01010505003064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f301e170d3130303732313036313835305a170d3430303731333036313835305a3064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f30819f300d06092a864886f70d010101050003818d0030818902818100c209077044bd0d63ea00ede5b839914cabcc912a87f0f8b390877e0f7a2583f0d5933443c40431c35a4433bc4c965800141961adc44c9625b1d321385221fd097e5bdc2f44a1840d643ab59dc070cf6c4b4b4d98bed5cbb8046e0a7078ae134da107cdf2bfc9b440fe5cb2f7549b44b73202cc6f7c2c55b8cfb0d333a021f01f0203010001300d06092a864886f70d010105050003818100b007db9922774ef4ccfee81ba514a8d57c410257e7a2eba64bfa17c9e690da08106d32f637ac41fbc9f205176c71bde238c872c3ee2f8313502bee44c80288ea4ef377a6f2cdfe4d3653c145c4acfedbfbadea23b559d41980cc3cdd35d79a68240693739aabf5c5ed26148756cf88264226de394c8a24ac35b712b120d4d23a")) {
                    return false;
                }
            } else if (!context.getPackageName().equals(TbsConfig.APP_QZONE)) {
                return !context.getPackageName().equals("com.tencent.qqpimsecure") || signature.toCharsString().equals("30820239308201a2a00302010202044c96f48f300d06092a864886f70d01010505003060310b300906035504061302434e310b300906035504081302474431123010060355040713094775616e677a686f753110300e060355040a130754656e63656e74310b3009060355040b130233473111300f0603550403130857696c736f6e57753020170d3130303932303035343334335a180f32303635303632333035343334335a3060310b300906035504061302434e310b300906035504081302474431123010060355040713094775616e677a686f753110300e060355040a130754656e63656e74310b3009060355040b130233473111300f0603550403130857696c736f6e577530819f300d06092a864886f70d010101050003818d0030818902818100b56e79dbb1185a79e52d792bb3d0bb3da8010d9b87da92ec69f7dc5ad66ab6bfdff2a6a1ed285dd2358f28b72a468be7c10a2ce30c4c27323ed4edcc936080e5bedc2cbbca0b7e879c08a631182793f44bb3ea284179b263410c298e5f6831032c9702ba4a74e2ccfc9ef857f12201451602fc8e774ac59d6398511586c83d1d0203010001300d06092a864886f70d0101050500038181002475615bb65b8d8786b890535802948840387d06b1692ff3ea47ef4c435719ba1865b81e6bfa6293ce31747c3cd6b34595b485cc1563fd90107ba5845c28b95c79138f0dec288940395bc10f92f2b69d8dc410999deb38900974ce9984b678030edfba8816582f56160d87e38641288d8588d2a31e20b89f223d788dd35cc9c8");
            } else {
                if (!signature.toCharsString().equals("308202ad30820216a00302010202044c26cea2300d06092a864886f70d010105050030819a310b3009060355040613023836311530130603550408130c4265696a696e672043697479311530130603550407130c4265696a696e67204369747931263024060355040a131d515a6f6e65205465616d206f662054656e63656e7420436f6d70616e7931183016060355040b130f54656e63656e7420436f6d70616e79311b301906035504031312416e64726f696420515a6f6e65205465616d301e170d3130303632373034303830325a170d3335303632313034303830325a30819a310b3009060355040613023836311530130603550408130c4265696a696e672043697479311530130603550407130c4265696a696e67204369747931263024060355040a131d515a6f6e65205465616d206f662054656e63656e7420436f6d70616e7931183016060355040b130f54656e63656e7420436f6d70616e79311b301906035504031312416e64726f696420515a6f6e65205465616d30819f300d06092a864886f70d010101050003818d003081890281810082d6aca037a9843fbbe88b6dd19f36e9c24ce174c1b398f3a529e2a7fe02de99c27539602c026edf96ad8d43df32a85458bca1e6fbf11958658a7d6751a1d9b782bf43a8c19bd1c06bdbfd94c0516326ae3cf638ac42bb470580e340c46e6f306a772c1ef98f10a559edf867f3f31fe492808776b7bd953b2cba2d2b2d66a44f0203010001300d06092a864886f70d0101050500038181006003b04a8a8c5be9650f350cda6896e57dd13e6e83e7f891fc70f6a3c2eaf75cfa4fc998365deabbd1b9092159edf4b90df5702a0d101f8840b5d4586eb92a1c3cd19d95fbc1c2ac956309eda8eef3944baf08c4a49d3b9b3ffb06bc13dab94ecb5b8eb74e8789aa0ba21cb567f538bbc59c2a11e6919924a24272eb79251677")) {
                    return false;
                }
            }
        } catch (Exception unused) {
            TbsLog.i("TbsInstaller", "TbsInstaller-installLocalTbsCore getPackageInfo fail");
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean g(Context context, int i2) {
        File file;
        String str;
        try {
            boolean isThirdPartyApp = TbsShareManager.isThirdPartyApp(context);
            if (!isThirdPartyApp) {
                file = r(context);
            } else if (TbsShareManager.j(context)) {
                file = new File(TbsShareManager.c(context));
                if (file.getAbsolutePath().contains(TbsConfig.APP_DEMO)) {
                    return true;
                }
            } else {
                TbsLog.e("TbsInstaller", "321");
                return false;
            }
            if (file != null) {
                Long[][] lArr = n;
                int length = lArr.length;
                int i3 = 0;
                while (true) {
                    if (i3 >= length) {
                        break;
                    }
                    Long[] lArr2 = lArr[i3];
                    if (i2 == lArr2[0].intValue()) {
                        File file2 = new File(file, "libmttwebview.so");
                        if (!file2.exists() || file2.length() != lArr2[1].longValue()) {
                            if (!isThirdPartyApp) {
                                FileUtil.b(QbSdk.getTbsFolderDir(context));
                            }
                            a.set(0);
                            str = "322";
                        } else {
                            TbsLog.d("TbsInstaller", "check so success: " + i2 + "; file: " + file2);
                        }
                    } else {
                        i3++;
                    }
                }
                return true;
            }
            str = "323";
            TbsLog.e("TbsInstaller", str);
            return false;
        } catch (Throwable th) {
            TbsLog.e("TbsInstaller", "ISTBSCORELEGAL exception getMessage is " + th.getMessage());
            TbsLog.e("TbsInstaller", "ISTBSCORELEGAL exception getCause is " + th.getCause());
            return false;
        }
    }

    public void h(Context context) {
        boolean z;
        FileLock fileLock;
        try {
            z = TbsDownloadConfig.getInstance().getTbsCoreLoadRenameFileLockEnable();
        } catch (Throwable unused) {
            z = true;
        }
        if (z && (fileLock = l) != null) {
            FileUtil.a(context, fileLock);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0045 A[SYNTHETIC, Splitter:B:25:0x0045] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x004c A[SYNTHETIC, Splitter:B:31:0x004c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int i(android.content.Context r5) {
        /*
            r4 = this;
            r0 = 0
            r1 = 0
            java.io.File r5 = r4.q(r5)     // Catch:{ Exception -> 0x0049, all -> 0x0042 }
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x0049, all -> 0x0042 }
            java.lang.String r3 = "tbs.conf"
            r2.<init>(r5, r3)     // Catch:{ Exception -> 0x0049, all -> 0x0042 }
            boolean r5 = r2.exists()     // Catch:{ Exception -> 0x0049, all -> 0x0042 }
            if (r5 != 0) goto L_0x0014
            return r0
        L_0x0014:
            java.util.Properties r5 = new java.util.Properties     // Catch:{ Exception -> 0x0049, all -> 0x0042 }
            r5.<init>()     // Catch:{ Exception -> 0x0049, all -> 0x0042 }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0049, all -> 0x0042 }
            r3.<init>(r2)     // Catch:{ Exception -> 0x0049, all -> 0x0042 }
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x0049, all -> 0x0042 }
            r2.<init>(r3)     // Catch:{ Exception -> 0x0049, all -> 0x0042 }
            r5.load(r2)     // Catch:{ Exception -> 0x0040, all -> 0x003d }
            r2.close()     // Catch:{ Exception -> 0x0040, all -> 0x003d }
            java.lang.String r1 = "tbs_core_version"
            java.lang.String r5 = r5.getProperty(r1)     // Catch:{ Exception -> 0x0040, all -> 0x003d }
            if (r5 != 0) goto L_0x0035
            r2.close()     // Catch:{ IOException -> 0x0034 }
        L_0x0034:
            return r0
        L_0x0035:
            int r5 = java.lang.Integer.parseInt(r5)     // Catch:{ Exception -> 0x0040, all -> 0x003d }
            r2.close()     // Catch:{ IOException -> 0x003c }
        L_0x003c:
            return r5
        L_0x003d:
            r5 = move-exception
            r1 = r2
            goto L_0x0043
        L_0x0040:
            r1 = r2
            goto L_0x004a
        L_0x0042:
            r5 = move-exception
        L_0x0043:
            if (r1 == 0) goto L_0x0048
            r1.close()     // Catch:{ IOException -> 0x0048 }
        L_0x0048:
            throw r5
        L_0x0049:
        L_0x004a:
            if (r1 == 0) goto L_0x004f
            r1.close()     // Catch:{ IOException -> 0x004f }
        L_0x004f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.o.i(android.content.Context):int");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x009a A[SYNTHETIC, Splitter:B:32:0x009a] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00b8 A[SYNTHETIC, Splitter:B:38:0x00b8] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int j(android.content.Context r7) {
        /*
            r6 = this;
            java.lang.String r0 = "TbsInstaller--getTbsCoreInstalledVerInNolock IOException="
            java.lang.String r1 = "TbsInstaller"
            r2 = 0
            r3 = 0
            java.io.File r7 = r6.r(r7)     // Catch:{ Exception -> 0x007f }
            java.io.File r4 = new java.io.File     // Catch:{ Exception -> 0x007f }
            java.lang.String r5 = "tbs.conf"
            r4.<init>(r7, r5)     // Catch:{ Exception -> 0x007f }
            boolean r7 = r4.exists()     // Catch:{ Exception -> 0x007f }
            if (r7 != 0) goto L_0x0018
            return r2
        L_0x0018:
            java.util.Properties r7 = new java.util.Properties     // Catch:{ Exception -> 0x007f }
            r7.<init>()     // Catch:{ Exception -> 0x007f }
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ Exception -> 0x007f }
            r5.<init>(r4)     // Catch:{ Exception -> 0x007f }
            java.io.BufferedInputStream r4 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x007f }
            r4.<init>(r5)     // Catch:{ Exception -> 0x007f }
            r7.load(r4)     // Catch:{ Exception -> 0x007a, all -> 0x0077 }
            r4.close()     // Catch:{ Exception -> 0x007a, all -> 0x0077 }
            java.lang.String r3 = "tbs_core_version"
            java.lang.String r7 = r7.getProperty(r3)     // Catch:{ Exception -> 0x007a, all -> 0x0077 }
            if (r7 != 0) goto L_0x0051
            r4.close()     // Catch:{ IOException -> 0x0039 }
            goto L_0x0050
        L_0x0039:
            r7 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r0)
            java.lang.String r7 = r7.toString()
            r3.append(r7)
            java.lang.String r7 = r3.toString()
            com.tencent.smtt.utils.TbsLog.i(r1, r7)
        L_0x0050:
            return r2
        L_0x0051:
            int r7 = java.lang.Integer.parseInt(r7)     // Catch:{ Exception -> 0x007a, all -> 0x0077 }
            int r3 = o     // Catch:{ Exception -> 0x007a, all -> 0x0077 }
            if (r3 != 0) goto L_0x005b
            o = r7     // Catch:{ Exception -> 0x007a, all -> 0x0077 }
        L_0x005b:
            r4.close()     // Catch:{ IOException -> 0x005f }
            goto L_0x0076
        L_0x005f:
            r2 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r0)
            java.lang.String r0 = r2.toString()
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            com.tencent.smtt.utils.TbsLog.i(r1, r0)
        L_0x0076:
            return r7
        L_0x0077:
            r7 = move-exception
            r3 = r4
            goto L_0x00b6
        L_0x007a:
            r7 = move-exception
            r3 = r4
            goto L_0x0080
        L_0x007d:
            r7 = move-exception
            goto L_0x00b6
        L_0x007f:
            r7 = move-exception
        L_0x0080:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x007d }
            r4.<init>()     // Catch:{ all -> 0x007d }
            java.lang.String r5 = "TbsInstaller--getTbsCoreInstalledVerInNolock Exception="
            r4.append(r5)     // Catch:{ all -> 0x007d }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x007d }
            r4.append(r7)     // Catch:{ all -> 0x007d }
            java.lang.String r7 = r4.toString()     // Catch:{ all -> 0x007d }
            com.tencent.smtt.utils.TbsLog.i(r1, r7)     // Catch:{ all -> 0x007d }
            if (r3 == 0) goto L_0x00b5
            r3.close()     // Catch:{ IOException -> 0x009e }
            goto L_0x00b5
        L_0x009e:
            r7 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r0)
            java.lang.String r7 = r7.toString()
            r3.append(r7)
            java.lang.String r7 = r3.toString()
            com.tencent.smtt.utils.TbsLog.i(r1, r7)
        L_0x00b5:
            return r2
        L_0x00b6:
            if (r3 == 0) goto L_0x00d3
            r3.close()     // Catch:{ IOException -> 0x00bc }
            goto L_0x00d3
        L_0x00bc:
            r2 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r0)
            java.lang.String r0 = r2.toString()
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            com.tencent.smtt.utils.TbsLog.i(r1, r0)
        L_0x00d3:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.o.j(android.content.Context):int");
    }

    /* access modifiers changed from: package-private */
    public int k(Context context) {
        int i2 = o;
        return i2 != 0 ? i2 : j(context);
    }

    /* access modifiers changed from: package-private */
    public void l(Context context) {
        if (o == 0) {
            o = j(context);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean m(Context context) {
        return new File(r(context), "tbs.conf").exists();
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x013b A[SYNTHETIC, Splitter:B:57:0x013b] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x015e A[Catch:{ all -> 0x0164 }] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x017d A[SYNTHETIC, Splitter:B:70:0x017d] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x01a0 A[Catch:{ all -> 0x01a6 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int n(android.content.Context r8) {
        /*
            r7 = this;
            java.lang.String r0 = "TbsInstaller--getTbsCoreInstalledVerWithLock IOException="
            java.lang.String r1 = "TbsRenameLock.unlock exception: "
            boolean r2 = r7.u(r8)
            if (r2 != 0) goto L_0x000c
            r8 = -1
            return r8
        L_0x000c:
            java.util.concurrent.locks.ReentrantLock r2 = i
            boolean r2 = r2.tryLock()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "TbsInstaller--getTbsCoreInstalledVerWithLock locked="
            r3.append(r4)
            r3.append(r2)
            java.lang.String r3 = r3.toString()
            java.lang.String r4 = "TbsInstaller"
            com.tencent.smtt.utils.TbsLog.i(r4, r3)
            r3 = 0
            if (r2 == 0) goto L_0x01bd
            r2 = 0
            java.io.File r8 = r7.r(r8)     // Catch:{ Exception -> 0x0120 }
            java.io.File r5 = new java.io.File     // Catch:{ Exception -> 0x0120 }
            java.lang.String r6 = "tbs.conf"
            r5.<init>(r8, r6)     // Catch:{ Exception -> 0x0120 }
            boolean r8 = r5.exists()     // Catch:{ Exception -> 0x0120 }
            if (r8 != 0) goto L_0x0062
            java.util.concurrent.locks.ReentrantLock r8 = i     // Catch:{ all -> 0x004b }
            boolean r8 = r8.isHeldByCurrentThread()     // Catch:{ all -> 0x004b }
            if (r8 == 0) goto L_0x005e
            java.util.concurrent.locks.ReentrantLock r8 = i     // Catch:{ all -> 0x004b }
            r8.unlock()     // Catch:{ all -> 0x004b }
            goto L_0x005e
        L_0x004b:
            r8 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r1)
            r0.append(r8)
            java.lang.String r8 = r0.toString()
            com.tencent.smtt.utils.TbsLog.e(r4, r8)
        L_0x005e:
            r7.b()
            return r3
        L_0x0062:
            java.util.Properties r8 = new java.util.Properties     // Catch:{ Exception -> 0x0120 }
            r8.<init>()     // Catch:{ Exception -> 0x0120 }
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0120 }
            r6.<init>(r5)     // Catch:{ Exception -> 0x0120 }
            java.io.BufferedInputStream r5 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x0120 }
            r5.<init>(r6)     // Catch:{ Exception -> 0x0120 }
            r8.load(r5)     // Catch:{ Exception -> 0x011b, all -> 0x0118 }
            r5.close()     // Catch:{ Exception -> 0x011b, all -> 0x0118 }
            java.lang.String r2 = "tbs_core_version"
            java.lang.String r8 = r8.getProperty(r2)     // Catch:{ Exception -> 0x011b, all -> 0x0118 }
            if (r8 != 0) goto L_0x00bf
            r5.close()     // Catch:{ IOException -> 0x0083 }
            goto L_0x009a
        L_0x0083:
            r8 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            java.lang.String r8 = r8.toString()
            r2.append(r8)
            java.lang.String r8 = r2.toString()
            com.tencent.smtt.utils.TbsLog.i(r4, r8)
        L_0x009a:
            java.util.concurrent.locks.ReentrantLock r8 = i     // Catch:{ all -> 0x00a8 }
            boolean r8 = r8.isHeldByCurrentThread()     // Catch:{ all -> 0x00a8 }
            if (r8 == 0) goto L_0x00bb
            java.util.concurrent.locks.ReentrantLock r8 = i     // Catch:{ all -> 0x00a8 }
            r8.unlock()     // Catch:{ all -> 0x00a8 }
            goto L_0x00bb
        L_0x00a8:
            r8 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r1)
            r0.append(r8)
            java.lang.String r8 = r0.toString()
            com.tencent.smtt.utils.TbsLog.e(r4, r8)
        L_0x00bb:
            r7.b()
            return r3
        L_0x00bf:
            java.lang.ThreadLocal<java.lang.Integer> r2 = a     // Catch:{ Exception -> 0x011b, all -> 0x0118 }
            int r8 = java.lang.Integer.parseInt(r8)     // Catch:{ Exception -> 0x011b, all -> 0x0118 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ Exception -> 0x011b, all -> 0x0118 }
            r2.set(r8)     // Catch:{ Exception -> 0x011b, all -> 0x0118 }
            java.lang.ThreadLocal<java.lang.Integer> r8 = a     // Catch:{ Exception -> 0x011b, all -> 0x0118 }
            java.lang.Object r8 = r8.get()     // Catch:{ Exception -> 0x011b, all -> 0x0118 }
            java.lang.Integer r8 = (java.lang.Integer) r8     // Catch:{ Exception -> 0x011b, all -> 0x0118 }
            int r8 = r8.intValue()     // Catch:{ Exception -> 0x011b, all -> 0x0118 }
            r5.close()     // Catch:{ IOException -> 0x00dc }
            goto L_0x00f3
        L_0x00dc:
            r2 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r0)
            java.lang.String r0 = r2.toString()
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            com.tencent.smtt.utils.TbsLog.i(r4, r0)
        L_0x00f3:
            java.util.concurrent.locks.ReentrantLock r0 = i     // Catch:{ all -> 0x0101 }
            boolean r0 = r0.isHeldByCurrentThread()     // Catch:{ all -> 0x0101 }
            if (r0 == 0) goto L_0x0114
            java.util.concurrent.locks.ReentrantLock r0 = i     // Catch:{ all -> 0x0101 }
            r0.unlock()     // Catch:{ all -> 0x0101 }
            goto L_0x0114
        L_0x0101:
            r0 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r1)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            com.tencent.smtt.utils.TbsLog.e(r4, r0)
        L_0x0114:
            r7.b()
            return r8
        L_0x0118:
            r8 = move-exception
            r2 = r5
            goto L_0x017b
        L_0x011b:
            r8 = move-exception
            r2 = r5
            goto L_0x0121
        L_0x011e:
            r8 = move-exception
            goto L_0x017b
        L_0x0120:
            r8 = move-exception
        L_0x0121:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x011e }
            r5.<init>()     // Catch:{ all -> 0x011e }
            java.lang.String r6 = "TbsInstaller--getTbsCoreInstalledVerWithLock Exception="
            r5.append(r6)     // Catch:{ all -> 0x011e }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x011e }
            r5.append(r8)     // Catch:{ all -> 0x011e }
            java.lang.String r8 = r5.toString()     // Catch:{ all -> 0x011e }
            com.tencent.smtt.utils.TbsLog.i(r4, r8)     // Catch:{ all -> 0x011e }
            if (r2 == 0) goto L_0x0156
            r2.close()     // Catch:{ IOException -> 0x013f }
            goto L_0x0156
        L_0x013f:
            r8 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            java.lang.String r8 = r8.toString()
            r2.append(r8)
            java.lang.String r8 = r2.toString()
            com.tencent.smtt.utils.TbsLog.i(r4, r8)
        L_0x0156:
            java.util.concurrent.locks.ReentrantLock r8 = i     // Catch:{ all -> 0x0164 }
            boolean r8 = r8.isHeldByCurrentThread()     // Catch:{ all -> 0x0164 }
            if (r8 == 0) goto L_0x0177
            java.util.concurrent.locks.ReentrantLock r8 = i     // Catch:{ all -> 0x0164 }
            r8.unlock()     // Catch:{ all -> 0x0164 }
            goto L_0x0177
        L_0x0164:
            r8 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r1)
            r0.append(r8)
            java.lang.String r8 = r0.toString()
            com.tencent.smtt.utils.TbsLog.e(r4, r8)
        L_0x0177:
            r7.b()
            return r3
        L_0x017b:
            if (r2 == 0) goto L_0x0198
            r2.close()     // Catch:{ IOException -> 0x0181 }
            goto L_0x0198
        L_0x0181:
            r2 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r0)
            java.lang.String r0 = r2.toString()
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            com.tencent.smtt.utils.TbsLog.i(r4, r0)
        L_0x0198:
            java.util.concurrent.locks.ReentrantLock r0 = i     // Catch:{ all -> 0x01a6 }
            boolean r0 = r0.isHeldByCurrentThread()     // Catch:{ all -> 0x01a6 }
            if (r0 == 0) goto L_0x01b9
            java.util.concurrent.locks.ReentrantLock r0 = i     // Catch:{ all -> 0x01a6 }
            r0.unlock()     // Catch:{ all -> 0x01a6 }
            goto L_0x01b9
        L_0x01a6:
            r0 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r1)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            com.tencent.smtt.utils.TbsLog.e(r4, r0)
        L_0x01b9:
            r7.b()
            throw r8
        L_0x01bd:
            r7.b()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.o.n(android.content.Context):int");
    }

    public boolean o(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--coreShareCopyToDecouple #0");
        File r = r(context);
        File q = q(context);
        try {
            FileUtil.a(q, true);
            FileUtil.a(r, q, (FileFilter) new FileFilter() {
                public boolean accept(File file) {
                    return !file.getName().endsWith(".dex") && !file.getName().endsWith(".jar_is_first_load_dex_flag_file");
                }
            });
            TbsShareManager.b(context);
            TbsLog.i("TbsInstaller", "TbsInstaller--coreShareCopyToDecouple success!!!");
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void p(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--cleanStatusAndTmpDir");
        m.a(context).a(0);
        m.a(context).b(0);
        m.a(context).d(0);
        m.a(context).a("incrupdate_retry_num", 0);
        if (!TbsDownloader.a(context)) {
            m.a(context).c(0, -1);
            m.a(context).a("");
            m.a(context).a("copy_retry_num", 0);
            m.a(context).c(-1);
            m.a(context).a(0, -1);
            FileUtil.a(f(context, 0), true);
            FileUtil.a(f(context, 1), true);
        }
    }

    /* access modifiers changed from: package-private */
    public File q(Context context) {
        File file = new File(QbSdk.getTbsFolderDir(context), "core_share_decouple");
        if (file.isDirectory() || file.mkdir()) {
            return file;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public File r(Context context) {
        return b((Context) null, context);
    }

    /* access modifiers changed from: package-private */
    public File s(Context context) {
        File file = new File(QbSdk.getTbsFolderDir(context), "share");
        if (file.isDirectory() || file.mkdir()) {
            return file;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean u(Context context) {
        if (this.e > 0) {
            TbsLog.i("TbsInstaller", "getTbsInstallingFileLock success,is cached= true");
            this.e++;
            return true;
        }
        FileOutputStream b2 = FileUtil.b(context, true, "tbslock.txt");
        this.g = b2;
        if (b2 != null) {
            FileLock a2 = FileUtil.a(context, b2);
            this.f = a2;
            if (a2 == null) {
                TbsLog.i("TbsInstaller", "getTbsInstallingFileLock tbsFileLockFileLock == null");
                return false;
            }
            TbsLog.i("TbsInstaller", "getTbsInstallingFileLock success,is cached= false");
            this.e++;
            return true;
        }
        TbsLog.i("TbsInstaller", "getTbsInstallingFileLock get install fos failed");
        return false;
    }
}
