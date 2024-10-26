package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.media.session.PlaybackStateCompat;
import com.tencent.smtt.sdk.TbsLogReport;
import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

public class TbsDownloadConfig {
    public static final int CMD_ID_DOWNLOAD_FILE = 101;
    public static final int CMD_ID_FILE_UPLOAD = 100;
    public static final long DEFAULT_RETRY_INTERVAL_SEC = 86400;
    public static final int ERROR_DOWNLOAD = 2;
    public static final int ERROR_INSTALL = 5;
    public static final int ERROR_LOAD = 6;
    public static final int ERROR_NONE = 1;
    public static final int ERROR_REPORTED = 0;
    public static final int ERROR_UNZIP = 4;
    public static final int ERROR_VERIFY = 3;
    private static TbsDownloadConfig a;
    private Context b;
    public SharedPreferences mPreferences;
    public Map<String, Object> mSyncMap = new HashMap();

    public interface TbsConfigKey {
        public static final String KEY_APP_METADATA = "app_metadata";
        public static final String KEY_APP_VERSIONCODE = "app_versioncode";
        public static final String KEY_APP_VERSIONCODE_FOR_SWITCH = "app_versioncode_for_switch";
        public static final String KEY_APP_VERSIONNAME = "app_versionname";
        public static final String KEY_BACKUPCORE_DELFILELIST = "backupcore_delfilelist";
        public static final String KEY_COUNT_REQUEST_FAIL_IN_24HOURS = "count_request_fail_in_24hours";
        public static final String KEY_DECOUPLECOREVERSION = "tbs_decouplecoreversion";
        public static final String KEY_DESkEY_TOKEN = "tbs_deskey_token";
        public static final String KEY_DEVICE_CPUABI = "device_cpuabi";
        public static final String KEY_DOWNLOADDECOUPLECORE = "tbs_downloaddecouplecore";
        public static final String KEY_DOWNLOADURL_LIST = "tbs_downloadurl_list";
        public static final String KEY_DOWNLOAD_FAILED_MAX_RETRYTIMES = "tbs_download_failed_max_retrytimes";
        public static final String KEY_DOWNLOAD_FAILED_RETRYTIMES = "tbs_download_failed_retrytimes";
        public static final String KEY_DOWNLOAD_INTERRUPT_CODE = "tbs_download_interrupt_code";
        public static final String KEY_DOWNLOAD_INTERRUPT_CODE_REASON = "tbs_download_interrupt_code_reason";
        public static final String KEY_DOWNLOAD_INTERRUPT_TIME = "tbs_download_interrupt_time";
        public static final String KEY_DOWNLOAD_MAXFLOW = "tbs_download_maxflow";
        public static final String KEY_DOWNLOAD_MIN_FREE_SPACE = "tbs_download_min_free_space";
        public static final String KEY_DOWNLOAD_SINGLE_TIMEOUT = "tbs_single_timeout";
        public static final String KEY_DOWNLOAD_SUCCESS_MAX_RETRYTIMES = "tbs_download_success_max_retrytimes";
        public static final String KEY_DOWNLOAD_SUCCESS_RETRYTIMES = "tbs_download_success_retrytimes";
        public static final String KEY_FULL_PACKAGE = "request_full_package";
        public static final String KEY_GUID = "tbs_guid";
        public static final String KEY_INSTALL_INTERRUPT_CODE = "tbs_install_interrupt_code";
        public static final String KEY_IS_OVERSEA = "is_oversea";
        public static final String KEY_LAST_CHECK = "last_check";
        public static final String KEY_LAST_DOWNLOAD_DECOUPLE_CORE = "last_download_decouple_core";
        public static final String KEY_LAST_REQUEST_SUCCESS = "last_request_success";
        public static final String KEY_LAST_THIRDAPP_SENDREQUEST_COREVERSION = "last_thirdapp_sendrequest_coreversion";
        public static final String KEY_NEEDDOWNLOAD = "tbs_needdownload";
        public static final String KEY_REQUEST_FAIL = "request_fail";
        public static final String KEY_RESPONSECODE = "tbs_responsecode";
        public static final String KEY_RETRY_INTERVAL = "retry_interval";
        public static final String KEY_STOP_PRE_OAT = "tbs_stop_preoat";
        public static final String KEY_SWITCH_BACKUPCORE_ENABLE = "switch_backupcore_enable";
        public static final String KEY_TBSAPKFILESIZE = "tbs_apkfilesize";
        public static final String KEY_TBSAPK_MD5 = "tbs_apk_md5";
        public static final String KEY_TBSDOWNLOADURL = "tbs_downloadurl";
        public static final String KEY_TBSDOWNLOAD_FLOW = "tbs_downloadflow";
        public static final String KEY_TBSDOWNLOAD_STARTTIME = "tbs_downloadstarttime";
        public static final String KEY_TBS_CORE_LOAD_RENAME_FILE_LOCK_ENABLE = "tbs_core_load_rename_file_lock_enable";
        public static final String KEY_TBS_CORE_LOAD_RENAME_FILE_LOCK_WAIT_ENABLE = "tbs_core_load_rename_file_lock_wait_enable";
        public static final String KEY_TBS_DOWNLOAD_V = "tbs_download_version";
        public static final String KEY_TBS_DOWNLOAD_V_TYPE = "tbs_download_version_type";
        public static final String KEY_USE_BACKUP_VERSION = "use_backup_version";
        public static final String KEY_USE_BUGLY = "tbs_use_bugly";
    }

    private TbsDownloadConfig(Context context) {
        this.mPreferences = context.getSharedPreferences("tbs_download_config", 4);
        Context applicationContext = context.getApplicationContext();
        this.b = applicationContext;
        if (applicationContext == null) {
            this.b = context;
        }
    }

    public static synchronized TbsDownloadConfig getInstance() {
        TbsDownloadConfig tbsDownloadConfig;
        synchronized (TbsDownloadConfig.class) {
            tbsDownloadConfig = a;
        }
        return tbsDownloadConfig;
    }

    public static synchronized TbsDownloadConfig getInstance(Context context) {
        TbsDownloadConfig tbsDownloadConfig;
        synchronized (TbsDownloadConfig.class) {
            if (a == null) {
                a = new TbsDownloadConfig(context);
            }
            tbsDownloadConfig = a;
        }
        return tbsDownloadConfig;
    }

    public void clear() {
        try {
            this.mSyncMap.clear();
            SharedPreferences.Editor edit = this.mPreferences.edit();
            edit.clear();
            edit.commit();
        } catch (Exception unused) {
        }
    }

    public synchronized void commit() {
        try {
            SharedPreferences.Editor edit = this.mPreferences.edit();
            for (String next : this.mSyncMap.keySet()) {
                Object obj = this.mSyncMap.get(next);
                if (obj instanceof String) {
                    edit.putString(next, (String) obj);
                } else if (obj instanceof Boolean) {
                    edit.putBoolean(next, ((Boolean) obj).booleanValue());
                } else if (obj instanceof Long) {
                    edit.putLong(next, ((Long) obj).longValue());
                } else if (obj instanceof Integer) {
                    edit.putInt(next, ((Integer) obj).intValue());
                } else if (obj instanceof Float) {
                    edit.putFloat(next, ((Float) obj).floatValue());
                }
            }
            edit.commit();
            this.mSyncMap.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

    public synchronized int getDownloadFailedMaxRetrytimes() {
        int i;
        i = this.mPreferences.getInt(TbsConfigKey.KEY_DOWNLOAD_FAILED_MAX_RETRYTIMES, 0);
        if (i == 0) {
            i = 100;
        }
        return i;
    }

    public synchronized int getDownloadInterruptCode() {
        int i;
        if (!this.mPreferences.contains(TbsConfigKey.KEY_DOWNLOAD_INTERRUPT_CODE)) {
            try {
                i = !new File(new File(this.b.getFilesDir(), "shared_prefs"), "tbs_download_config").exists() ? -97 : !this.mPreferences.contains(TbsConfigKey.KEY_NEEDDOWNLOAD) ? -96 : -101;
            } catch (Throwable unused) {
                i = -95;
            }
        } else {
            i = this.mPreferences.getInt(TbsConfigKey.KEY_DOWNLOAD_INTERRUPT_CODE, -99);
            if (i == -119 || i == -121) {
                i = this.mPreferences.getInt(TbsConfigKey.KEY_DOWNLOAD_INTERRUPT_CODE_REASON, -119);
            }
            if (System.currentTimeMillis() - this.mPreferences.getLong(TbsConfigKey.KEY_DOWNLOAD_INTERRUPT_TIME, 0) > DateUtils.MILLIS_PER_DAY) {
                i -= 98000;
            }
        }
        return (this.b == null || !TbsConfig.APP_QQ.equals(this.b.getApplicationInfo().packageName) || "CN".equals(Locale.getDefault().getCountry())) ? (i * 1000) + this.mPreferences.getInt(TbsConfigKey.KEY_INSTALL_INTERRUPT_CODE, -1) : -320;
    }

    public synchronized long getDownloadMaxflow() {
        int i;
        i = this.mPreferences.getInt(TbsConfigKey.KEY_DOWNLOAD_MAXFLOW, 0);
        if (i == 0) {
            i = 20;
        }
        return ((long) (i * 1024)) * PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
    }

    public synchronized long getDownloadMinFreeSpace() {
        int i;
        i = 0;
        int i2 = this.mPreferences.getInt(TbsConfigKey.KEY_DOWNLOAD_MIN_FREE_SPACE, 0);
        if (i2 != 0) {
            i = i2;
        }
        return ((long) (i * 1024)) * PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
    }

    public synchronized long getDownloadSingleTimeout() {
        long j;
        j = this.mPreferences.getLong(TbsConfigKey.KEY_DOWNLOAD_SINGLE_TIMEOUT, 0);
        if (j == 0) {
            j = 1200000;
        }
        return j;
    }

    public synchronized int getDownloadSuccessMaxRetrytimes() {
        int i;
        i = this.mPreferences.getInt(TbsConfigKey.KEY_DOWNLOAD_SUCCESS_MAX_RETRYTIMES, 0);
        if (i == 0) {
            i = 3;
        }
        return i;
    }

    public synchronized long getRetryInterval() {
        if (TbsDownloader.getRetryIntervalInSeconds() >= 0) {
            return TbsDownloader.getRetryIntervalInSeconds();
        }
        return this.mPreferences.getLong(TbsConfigKey.KEY_RETRY_INTERVAL, DEFAULT_RETRY_INTERVAL_SEC);
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean getTbsCoreLoadRenameFileLockEnable() {
        /*
            r3 = this;
            monitor-enter(r3)
            r0 = 1
            android.content.SharedPreferences r1 = r3.mPreferences     // Catch:{ Exception -> 0x000e, all -> 0x000b }
            java.lang.String r2 = "tbs_core_load_rename_file_lock_enable"
            boolean r0 = r1.getBoolean(r2, r0)     // Catch:{ Exception -> 0x000e, all -> 0x000b }
            goto L_0x000e
        L_0x000b:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        L_0x000e:
            monitor-exit(r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsDownloadConfig.getTbsCoreLoadRenameFileLockEnable():boolean");
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean getTbsCoreLoadRenameFileLockWaitEnable() {
        /*
            r3 = this;
            monitor-enter(r3)
            r0 = 1
            android.content.SharedPreferences r1 = r3.mPreferences     // Catch:{ Exception -> 0x000e, all -> 0x000b }
            java.lang.String r2 = "tbs_core_load_rename_file_lock_wait_enable"
            boolean r0 = r1.getBoolean(r2, r0)     // Catch:{ Exception -> 0x000e, all -> 0x000b }
            goto L_0x000e
        L_0x000b:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        L_0x000e:
            monitor-exit(r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsDownloadConfig.getTbsCoreLoadRenameFileLockWaitEnable():boolean");
    }

    public synchronized boolean isOverSea() {
        return this.mPreferences.getBoolean(TbsConfigKey.KEY_IS_OVERSEA, false);
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setDownloadInterruptCode(int r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            android.content.SharedPreferences r0 = r3.mPreferences     // Catch:{ Exception -> 0x001c, all -> 0x0019 }
            android.content.SharedPreferences$Editor r0 = r0.edit()     // Catch:{ Exception -> 0x001c, all -> 0x0019 }
            java.lang.String r1 = "tbs_download_interrupt_code"
            r0.putInt(r1, r4)     // Catch:{ Exception -> 0x001c, all -> 0x0019 }
            java.lang.String r4 = "tbs_download_interrupt_time"
            long r1 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x001c, all -> 0x0019 }
            r0.putLong(r4, r1)     // Catch:{ Exception -> 0x001c, all -> 0x0019 }
            r0.commit()     // Catch:{ Exception -> 0x001c, all -> 0x0019 }
            goto L_0x001c
        L_0x0019:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        L_0x001c:
            monitor-exit(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsDownloadConfig.setDownloadInterruptCode(int):void");
    }

    public synchronized void setInstallInterruptCode(int i) {
        SharedPreferences.Editor edit = this.mPreferences.edit();
        edit.putInt(TbsConfigKey.KEY_INSTALL_INTERRUPT_CODE, i);
        edit.commit();
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setTbsCoreLoadRenameFileLockEnable(boolean r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            android.content.SharedPreferences r0 = r2.mPreferences     // Catch:{ Exception -> 0x0013, all -> 0x0010 }
            android.content.SharedPreferences$Editor r0 = r0.edit()     // Catch:{ Exception -> 0x0013, all -> 0x0010 }
            java.lang.String r1 = "tbs_core_load_rename_file_lock_enable"
            r0.putBoolean(r1, r3)     // Catch:{ Exception -> 0x0013, all -> 0x0010 }
            r0.commit()     // Catch:{ Exception -> 0x0013, all -> 0x0010 }
            goto L_0x0013
        L_0x0010:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        L_0x0013:
            monitor-exit(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsDownloadConfig.setTbsCoreLoadRenameFileLockEnable(boolean):void");
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setTbsCoreLoadRenameFileLockWaitEnable(boolean r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            android.content.SharedPreferences r0 = r2.mPreferences     // Catch:{ Exception -> 0x0013, all -> 0x0010 }
            android.content.SharedPreferences$Editor r0 = r0.edit()     // Catch:{ Exception -> 0x0013, all -> 0x0010 }
            java.lang.String r1 = "tbs_core_load_rename_file_lock_wait_enable"
            r0.putBoolean(r1, r3)     // Catch:{ Exception -> 0x0013, all -> 0x0010 }
            r0.commit()     // Catch:{ Exception -> 0x0013, all -> 0x0010 }
            goto L_0x0013
        L_0x0010:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        L_0x0013:
            monitor-exit(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsDownloadConfig.setTbsCoreLoadRenameFileLockWaitEnable(boolean):void");
    }

    public synchronized void uploadDownloadInterruptCodeIfNeeded(Context context) {
        int i;
        if (context != null) {
            try {
                if (TbsConfig.APP_WX.equals(context.getApplicationContext().getApplicationInfo().packageName)) {
                    boolean z = true;
                    if (!this.mPreferences.contains(TbsConfigKey.KEY_DOWNLOAD_INTERRUPT_CODE)) {
                        try {
                            i = !new File(new File(this.b.getFilesDir(), "shared_prefs"), "tbs_download_config").exists() ? -97 : !this.mPreferences.contains(TbsConfigKey.KEY_NEEDDOWNLOAD) ? -96 : -101;
                        } catch (Throwable unused) {
                            i = -95;
                        }
                    } else {
                        i = this.mPreferences.getInt(TbsConfigKey.KEY_DOWNLOAD_INTERRUPT_CODE, -99);
                        if (i > -206 || i < -219) {
                            if (i > -302 || i < -316) {
                                if (i > -318 || i < -322) {
                                    z = false;
                                }
                            }
                        }
                    }
                    if (z) {
                        TbsLogReport.TbsLogInfo tbsLogInfo = TbsLogReport.getInstance(context).tbsLogInfo();
                        tbsLogInfo.setErrorCode(128);
                        tbsLogInfo.setFailDetail(StringUtils.SPACE + i);
                        TbsLogReport.getInstance(context).eventReport(TbsLogReport.EventType.TYPE_DOWNLOAD, tbsLogInfo);
                    }
                }
            } catch (Throwable unused2) {
            }
        }
    }
}
