package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import androidx.core.os.EnvironmentCompat;
import com.tencent.bugly.Bugly;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.b;
import com.tencent.smtt.utils.f;
import com.tencent.smtt.utils.j;
import com.tencent.smtt.utils.m;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import mc.csst.com.selfchassislibrary.content.SelfChassisStateContent;
import org.apache.log4j.helpers.DateLayout;
import org.json.JSONArray;

public class TbsLogReport {
    private static TbsLogReport a;
    private Handler b = null;
    private Context c;
    private boolean d = false;

    public enum EventType {
        TYPE_DOWNLOAD(0),
        TYPE_INSTALL(1),
        TYPE_LOAD(2),
        TYPE_DOWNLOAD_DECOUPLE(3),
        TYPE_INSTALL_DECOUPLE(4),
        TYPE_COOKIE_DB_SWITCH(5),
        TYPE_SDK_REPORT_INFO(6);
        
        int a;

        private EventType(int i) {
            this.a = i;
        }
    }

    public static class TbsLogInfo implements Cloneable {
        int a;
        /* access modifiers changed from: private */
        public long b;
        /* access modifiers changed from: private */
        public String c;
        /* access modifiers changed from: private */
        public String d;
        /* access modifiers changed from: private */
        public int e;
        /* access modifiers changed from: private */
        public int f;
        /* access modifiers changed from: private */
        public int g;
        /* access modifiers changed from: private */
        public int h;
        /* access modifiers changed from: private */
        public String i;
        /* access modifiers changed from: private */
        public int j;
        /* access modifiers changed from: private */
        public int k;
        /* access modifiers changed from: private */
        public long l;
        /* access modifiers changed from: private */
        public long m;
        /* access modifiers changed from: private */
        public int n;
        /* access modifiers changed from: private */
        public String o;
        /* access modifiers changed from: private */
        public String p;
        /* access modifiers changed from: private */
        public long q;

        private TbsLogInfo() {
            resetArgs();
        }

        /* access modifiers changed from: protected */
        public Object clone() {
            try {
                return super.clone();
            } catch (CloneNotSupportedException unused) {
                return this;
            }
        }

        public int getDownFinalFlag() {
            return this.k;
        }

        public void resetArgs() {
            this.b = 0;
            this.c = null;
            this.d = null;
            this.e = 0;
            this.f = 0;
            this.g = 0;
            this.h = 2;
            this.i = EnvironmentCompat.MEDIA_UNKNOWN;
            this.j = 0;
            this.k = 2;
            this.l = 0;
            this.m = 0;
            this.n = 1;
            this.a = 0;
            this.o = null;
            this.p = null;
            this.q = 0;
        }

        public void setApn(String str) {
            this.i = str;
        }

        public void setCheckErrorDetail(String str) {
            setErrorCode(108);
            this.o = str;
        }

        public void setDownConsumeTime(long j2) {
            this.m += j2;
        }

        public void setDownFinalFlag(int i2) {
            this.k = i2;
        }

        public void setDownloadCancel(int i2) {
            this.g = i2;
        }

        public void setDownloadSize(long j2) {
            this.q += j2;
        }

        public void setDownloadUrl(String str) {
            if (this.c != null) {
                str = this.c + ";" + str;
            }
            this.c = str;
        }

        public void setErrorCode(int i2) {
            if (!(i2 == 100 || i2 == 110 || i2 == 120 || i2 == 111 || i2 >= 400)) {
                TbsLog.i(TbsDownloader.LOGTAG, "error occured, errorCode:" + i2, true);
            }
            if (i2 == 111) {
                TbsLog.i(TbsDownloader.LOGTAG, "you are not in wifi, downloading stoped", true);
            }
            this.a = i2;
        }

        public void setEventTime(long j2) {
            this.b = j2;
        }

        public void setFailDetail(String str) {
            if (str != null) {
                if (str.length() > 1024) {
                    str = str.substring(0, 1024);
                }
                this.p = str;
            }
        }

        public void setFailDetail(Throwable th) {
            if (th == null) {
                this.p = "";
                return;
            }
            String stackTraceString = Log.getStackTraceString(th);
            if (stackTraceString.length() > 1024) {
                stackTraceString = stackTraceString.substring(0, 1024);
            }
            this.p = stackTraceString;
        }

        public void setHttpCode(int i2) {
            this.e = i2;
        }

        public void setNetworkChange(int i2) {
            this.n = i2;
        }

        public void setNetworkType(int i2) {
            this.j = i2;
        }

        public void setPatchUpdateFlag(int i2) {
            this.f = i2;
        }

        public void setPkgSize(long j2) {
            this.l = j2;
        }

        public void setResolveIp(String str) {
            this.d = str;
        }

        public void setUnpkgFlag(int i2) {
            this.h = i2;
        }
    }

    private static class a {
        private final String a;
        private final String b;

        public a(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        /* JADX WARNING: Removed duplicated region for block: B:19:0x0038 A[SYNTHETIC, Splitter:B:19:0x0038] */
        /* JADX WARNING: Removed duplicated region for block: B:24:0x0043 A[SYNTHETIC, Splitter:B:24:0x0043] */
        /* JADX WARNING: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static void a(java.io.File r5) throws java.io.IOException {
            /*
                r0 = 0
                java.io.RandomAccessFile r1 = new java.io.RandomAccessFile     // Catch:{ Exception -> 0x0032 }
                java.lang.String r2 = "rw"
                r1.<init>(r5, r2)     // Catch:{ Exception -> 0x0032 }
                java.lang.String r5 = "00001000"
                r0 = 2
                int r5 = java.lang.Integer.parseInt(r5, r0)     // Catch:{ Exception -> 0x002d, all -> 0x002a }
                r2 = 7
                r1.seek(r2)     // Catch:{ Exception -> 0x002d, all -> 0x002a }
                int r0 = r1.read()     // Catch:{ Exception -> 0x002d, all -> 0x002a }
                r4 = r0 & r5
                if (r4 <= 0) goto L_0x0026
                r1.seek(r2)     // Catch:{ Exception -> 0x002d, all -> 0x002a }
                int r5 = ~r5     // Catch:{ Exception -> 0x002d, all -> 0x002a }
                r5 = r5 & 255(0xff, float:3.57E-43)
                r5 = r5 & r0
                r1.write(r5)     // Catch:{ Exception -> 0x002d, all -> 0x002a }
            L_0x0026:
                r1.close()     // Catch:{ IOException -> 0x003c }
                goto L_0x0040
            L_0x002a:
                r5 = move-exception
                r0 = r1
                goto L_0x0041
            L_0x002d:
                r5 = move-exception
                r0 = r1
                goto L_0x0033
            L_0x0030:
                r5 = move-exception
                goto L_0x0041
            L_0x0032:
                r5 = move-exception
            L_0x0033:
                r5.printStackTrace()     // Catch:{ all -> 0x0030 }
                if (r0 == 0) goto L_0x0040
                r0.close()     // Catch:{ IOException -> 0x003c }
                goto L_0x0040
            L_0x003c:
                r5 = move-exception
                r5.printStackTrace()
            L_0x0040:
                return
            L_0x0041:
                if (r0 == 0) goto L_0x004b
                r0.close()     // Catch:{ IOException -> 0x0047 }
                goto L_0x004b
            L_0x0047:
                r0 = move-exception
                r0.printStackTrace()
            L_0x004b:
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsLogReport.a.a(java.io.File):void");
        }

        /* JADX WARNING: Removed duplicated region for block: B:101:0x00e5 A[SYNTHETIC, Splitter:B:101:0x00e5] */
        /* JADX WARNING: Removed duplicated region for block: B:108:? A[RETURN, SYNTHETIC] */
        /* JADX WARNING: Removed duplicated region for block: B:42:0x006f A[SYNTHETIC, Splitter:B:42:0x006f] */
        /* JADX WARNING: Removed duplicated region for block: B:48:0x0079 A[SYNTHETIC, Splitter:B:48:0x0079] */
        /* JADX WARNING: Removed duplicated region for block: B:61:0x0098 A[SYNTHETIC, Splitter:B:61:0x0098] */
        /* JADX WARNING: Removed duplicated region for block: B:67:0x00a2 A[SYNTHETIC, Splitter:B:67:0x00a2] */
        /* JADX WARNING: Removed duplicated region for block: B:85:0x00c5 A[SYNTHETIC, Splitter:B:85:0x00c5] */
        /* JADX WARNING: Removed duplicated region for block: B:90:0x00cf A[SYNTHETIC, Splitter:B:90:0x00cf] */
        /* JADX WARNING: Removed duplicated region for block: B:96:0x00db A[SYNTHETIC, Splitter:B:96:0x00db] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a() {
            /*
                r10 = this;
                r0 = 0
                java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x00bc, all -> 0x00b7 }
                java.lang.String r2 = r10.b     // Catch:{ Exception -> 0x00bc, all -> 0x00b7 }
                r1.<init>(r2)     // Catch:{ Exception -> 0x00bc, all -> 0x00b7 }
                java.util.zip.ZipOutputStream r2 = new java.util.zip.ZipOutputStream     // Catch:{ Exception -> 0x00b2, all -> 0x00ad }
                java.io.BufferedOutputStream r3 = new java.io.BufferedOutputStream     // Catch:{ Exception -> 0x00b2, all -> 0x00ad }
                r3.<init>(r1)     // Catch:{ Exception -> 0x00b2, all -> 0x00ad }
                r2.<init>(r3)     // Catch:{ Exception -> 0x00b2, all -> 0x00ad }
                r3 = 2048(0x800, float:2.87E-42)
                byte[] r4 = new byte[r3]     // Catch:{ Exception -> 0x00ab }
                java.lang.String r5 = r10.a     // Catch:{ Exception -> 0x00ab }
                java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0066, all -> 0x0061 }
                r6.<init>(r5)     // Catch:{ Exception -> 0x0066, all -> 0x0061 }
                java.io.BufferedInputStream r7 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x005e, all -> 0x005b }
                r7.<init>(r6, r3)     // Catch:{ Exception -> 0x005e, all -> 0x005b }
                java.util.zip.ZipEntry r0 = new java.util.zip.ZipEntry     // Catch:{ Exception -> 0x0059 }
                java.lang.String r8 = "/"
                int r8 = r5.lastIndexOf(r8)     // Catch:{ Exception -> 0x0059 }
                int r8 = r8 + 1
                java.lang.String r5 = r5.substring(r8)     // Catch:{ Exception -> 0x0059 }
                r0.<init>(r5)     // Catch:{ Exception -> 0x0059 }
                r2.putNextEntry(r0)     // Catch:{ Exception -> 0x0059 }
            L_0x0036:
                r0 = 0
                int r5 = r7.read(r4, r0, r3)     // Catch:{ Exception -> 0x0059 }
                r8 = -1
                if (r5 == r8) goto L_0x0042
                r2.write(r4, r0, r5)     // Catch:{ Exception -> 0x0059 }
                goto L_0x0036
            L_0x0042:
                r2.flush()     // Catch:{ Exception -> 0x0059 }
                r2.closeEntry()     // Catch:{ Exception -> 0x0059 }
                r7.close()     // Catch:{ IOException -> 0x004c }
                goto L_0x0050
            L_0x004c:
                r0 = move-exception
                r0.printStackTrace()     // Catch:{ Exception -> 0x00ab }
            L_0x0050:
                r6.close()     // Catch:{ IOException -> 0x0054 }
                goto L_0x007f
            L_0x0054:
                r0 = move-exception
            L_0x0055:
                r0.printStackTrace()     // Catch:{ Exception -> 0x00ab }
                goto L_0x007f
            L_0x0059:
                r0 = move-exception
                goto L_0x006a
            L_0x005b:
                r3 = move-exception
                r7 = r0
                goto L_0x0064
            L_0x005e:
                r3 = move-exception
                r7 = r0
                goto L_0x0069
            L_0x0061:
                r3 = move-exception
                r6 = r0
                r7 = r6
            L_0x0064:
                r0 = r3
                goto L_0x0096
            L_0x0066:
                r3 = move-exception
                r6 = r0
                r7 = r6
            L_0x0069:
                r0 = r3
            L_0x006a:
                r0.printStackTrace()     // Catch:{ all -> 0x0095 }
                if (r7 == 0) goto L_0x0077
                r7.close()     // Catch:{ IOException -> 0x0073 }
                goto L_0x0077
            L_0x0073:
                r0 = move-exception
                r0.printStackTrace()     // Catch:{ Exception -> 0x00ab }
            L_0x0077:
                if (r6 == 0) goto L_0x007f
                r6.close()     // Catch:{ IOException -> 0x007d }
                goto L_0x007f
            L_0x007d:
                r0 = move-exception
                goto L_0x0055
            L_0x007f:
                java.io.File r0 = new java.io.File     // Catch:{ Exception -> 0x00ab }
                java.lang.String r3 = r10.b     // Catch:{ Exception -> 0x00ab }
                r0.<init>(r3)     // Catch:{ Exception -> 0x00ab }
                a(r0)     // Catch:{ Exception -> 0x00ab }
                r2.close()     // Catch:{ IOException -> 0x008d }
                goto L_0x0091
            L_0x008d:
                r0 = move-exception
                r0.printStackTrace()
            L_0x0091:
                r1.close()     // Catch:{ IOException -> 0x00d3 }
                goto L_0x00d7
            L_0x0095:
                r0 = move-exception
            L_0x0096:
                if (r7 == 0) goto L_0x00a0
                r7.close()     // Catch:{ IOException -> 0x009c }
                goto L_0x00a0
            L_0x009c:
                r3 = move-exception
                r3.printStackTrace()     // Catch:{ Exception -> 0x00ab }
            L_0x00a0:
                if (r6 == 0) goto L_0x00aa
                r6.close()     // Catch:{ IOException -> 0x00a6 }
                goto L_0x00aa
            L_0x00a6:
                r3 = move-exception
                r3.printStackTrace()     // Catch:{ Exception -> 0x00ab }
            L_0x00aa:
                throw r0     // Catch:{ Exception -> 0x00ab }
            L_0x00ab:
                r0 = move-exception
                goto L_0x00c0
            L_0x00ad:
                r2 = move-exception
                r9 = r2
                r2 = r0
                r0 = r9
                goto L_0x00d9
            L_0x00b2:
                r2 = move-exception
                r9 = r2
                r2 = r0
                r0 = r9
                goto L_0x00c0
            L_0x00b7:
                r1 = move-exception
                r2 = r0
                r0 = r1
                r1 = r2
                goto L_0x00d9
            L_0x00bc:
                r1 = move-exception
                r2 = r0
                r0 = r1
                r1 = r2
            L_0x00c0:
                r0.printStackTrace()     // Catch:{ all -> 0x00d8 }
                if (r2 == 0) goto L_0x00cd
                r2.close()     // Catch:{ IOException -> 0x00c9 }
                goto L_0x00cd
            L_0x00c9:
                r0 = move-exception
                r0.printStackTrace()
            L_0x00cd:
                if (r1 == 0) goto L_0x00d7
                r1.close()     // Catch:{ IOException -> 0x00d3 }
                goto L_0x00d7
            L_0x00d3:
                r0 = move-exception
                r0.printStackTrace()
            L_0x00d7:
                return
            L_0x00d8:
                r0 = move-exception
            L_0x00d9:
                if (r2 == 0) goto L_0x00e3
                r2.close()     // Catch:{ IOException -> 0x00df }
                goto L_0x00e3
            L_0x00df:
                r2 = move-exception
                r2.printStackTrace()
            L_0x00e3:
                if (r1 == 0) goto L_0x00ed
                r1.close()     // Catch:{ IOException -> 0x00e9 }
                goto L_0x00ed
            L_0x00e9:
                r1 = move-exception
                r1.printStackTrace()
            L_0x00ed:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsLogReport.a.a():void");
        }
    }

    private TbsLogReport(Context context) {
        this.c = context.getApplicationContext();
        HandlerThread handlerThread = new HandlerThread("TbsLogReportThread");
        handlerThread.start();
        this.b = new Handler(handlerThread.getLooper()) {
            public void handleMessage(Message message) {
                if (message.what == 600) {
                    if (message.obj instanceof TbsLogInfo) {
                        try {
                            int i = message.arg1;
                            TbsLogReport.this.a(i, (TbsLogInfo) message.obj);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else if (message.what == 601) {
                    TbsLogReport.this.b();
                }
            }
        };
    }

    private String a(int i) {
        return i + "|";
    }

    private String a(long j) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date(j));
        } catch (Exception unused) {
            return null;
        }
    }

    private String a(String str) {
        StringBuilder sb = new StringBuilder();
        if (str == null) {
            str = "";
        }
        sb.append(str);
        sb.append("|");
        return sb.toString();
    }

    private JSONArray a() {
        String string = d().getString("tbs_download_upload", (String) null);
        if (string == null) {
            return new JSONArray();
        }
        try {
            JSONArray jSONArray = new JSONArray(string);
            if (jSONArray.length() > 5) {
                JSONArray jSONArray2 = new JSONArray();
                int length = jSONArray.length() - 1;
                if (length > jSONArray.length() - 5) {
                    jSONArray2.put(jSONArray.get(length));
                    return jSONArray2;
                }
            }
            return jSONArray;
        } catch (Exception unused) {
            return new JSONArray();
        }
    }

    /* access modifiers changed from: private */
    public void a(int i, TbsLogInfo tbsLogInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append(a(i));
        sb.append(a(b.f(this.c)));
        sb.append(a(j.a(this.c)));
        sb.append(a(o.a().j(this.c)));
        String str = Build.MODEL;
        try {
            str = new String(str.getBytes("UTF-8"), "ISO8859-1");
        } catch (Exception unused) {
        }
        sb.append(a(str));
        String packageName = this.c.getPackageName();
        sb.append(a(packageName));
        sb.append(TbsConfig.APP_WX.equals(packageName) ? a(b.a(this.c, TbsDownloader.TBS_METADATA)) : a(b.d(this.c)));
        sb.append(a(a(tbsLogInfo.b)));
        sb.append(a(tbsLogInfo.c));
        sb.append(a(tbsLogInfo.d));
        sb.append(a(tbsLogInfo.e));
        sb.append(a(tbsLogInfo.f));
        sb.append(a(tbsLogInfo.g));
        sb.append(a(tbsLogInfo.h));
        sb.append(a(tbsLogInfo.i));
        sb.append(a(tbsLogInfo.j));
        sb.append(a(tbsLogInfo.k));
        sb.append(b(tbsLogInfo.q));
        sb.append(b(tbsLogInfo.l));
        sb.append(b(tbsLogInfo.m));
        sb.append(a(tbsLogInfo.n));
        sb.append(a(tbsLogInfo.a));
        sb.append(a(tbsLogInfo.o));
        sb.append(a(tbsLogInfo.p));
        sb.append(a(TbsDownloadConfig.getInstance(this.c).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0)));
        sb.append(a(b.i(this.c)));
        sb.append(a("4.3.0.93_43993"));
        sb.append(false);
        SharedPreferences d2 = d();
        JSONArray a2 = a();
        JSONArray jSONArray = new JSONArray();
        if (jSONArray.length() >= 5) {
            for (int i2 = 4; i2 >= 1; i2--) {
                try {
                    jSONArray.put(a2.get(jSONArray.length() - i2));
                } catch (Exception unused2) {
                    TbsLog.e("upload", "JSONArray transform error!");
                }
            }
            a2 = jSONArray;
        }
        a2.put(sb.toString());
        SharedPreferences.Editor edit = d2.edit();
        edit.putString("tbs_download_upload", a2.toString());
        edit.commit();
        if (this.d || i != EventType.TYPE_LOAD.a) {
            b();
        }
    }

    private void a(int i, TbsLogInfo tbsLogInfo, EventType eventType) {
        tbsLogInfo.setErrorCode(i);
        tbsLogInfo.setEventTime(System.currentTimeMillis());
        QbSdk.m.onInstallFinish(i);
        eventReport(eventType, tbsLogInfo);
    }

    private String b(long j) {
        return j + "|";
    }

    /* access modifiers changed from: private */
    public void b() {
        String str;
        String str2;
        if (QbSdk.n == null || !QbSdk.n.containsKey(QbSdk.KEY_SET_SENDREQUEST_AND_UPLOAD) || !QbSdk.n.get(QbSdk.KEY_SET_SENDREQUEST_AND_UPLOAD).equals(Bugly.SDK_IS_DEV)) {
            str = TbsDownloader.LOGTAG;
            TbsLog.i(str, "[TbsApkDownloadStat.reportDownloadStat]");
            JSONArray a2 = a();
            if (a2 == null || a2.length() == 0) {
                str2 = "[TbsApkDownloadStat.reportDownloadStat] no data";
            } else {
                TbsLog.i(str, "[TbsApkDownloadStat.reportDownloadStat] jsonArray:" + a2);
                try {
                    String a3 = f.a(m.a(this.c).c(), a2.toString().getBytes("utf-8"), new f.a() {
                        public void a(int i) {
                            TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloadStat.reportDownloadStat] onHttpResponseCode:" + i);
                            if (i < 300) {
                                TbsLogReport.this.c();
                            }
                        }
                    }, true);
                    TbsLog.i(str, "[TbsApkDownloadStat.reportDownloadStat] response:" + a3 + " testcase: " + -1);
                    return;
                } catch (Throwable th) {
                    th.printStackTrace();
                    return;
                }
            }
        } else {
            str = "upload";
            str2 = "[TbsLogReport.sendLogReportRequest] -- SET_SENDREQUEST_AND_UPLOAD is false";
        }
        TbsLog.i(str, str2);
    }

    /* access modifiers changed from: private */
    public void c() {
        SharedPreferences.Editor edit = d().edit();
        edit.remove("tbs_download_upload");
        edit.commit();
    }

    private SharedPreferences d() {
        return this.c.getSharedPreferences("tbs_download_stat", 4);
    }

    public static TbsLogReport getInstance(Context context) {
        if (a == null) {
            synchronized (TbsLogReport.class) {
                if (a == null) {
                    a = new TbsLogReport(context);
                }
            }
        }
        return a;
    }

    public void clear() {
        try {
            SharedPreferences.Editor edit = d().edit();
            edit.clear();
            edit.commit();
        } catch (Exception unused) {
        }
    }

    public void dailyReport() {
        this.b.sendEmptyMessage(SelfChassisStateContent.NavStatus.NAVIGATING);
    }

    public void eventReport(EventType eventType, TbsLogInfo tbsLogInfo) {
        try {
            Message obtainMessage = this.b.obtainMessage();
            obtainMessage.what = SelfChassisStateContent.NavStatus.INIT;
            obtainMessage.arg1 = eventType.a;
            obtainMessage.obj = (TbsLogInfo) tbsLogInfo.clone();
            this.b.sendMessage(obtainMessage);
        } catch (Throwable th) {
            TbsLog.w("upload", "[TbsLogReport.eventReport] error, message=" + th.getMessage());
        }
    }

    public boolean getShouldUploadEventReport() {
        return this.d;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v9, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v11, resolved type: java.io.FileInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v13, resolved type: java.io.FileInputStream} */
    /* JADX WARNING: type inference failed for: r5v6 */
    /* JADX WARNING: type inference failed for: r5v7, types: [java.io.FileInputStream] */
    /* JADX WARNING: type inference failed for: r5v8 */
    /* JADX WARNING: type inference failed for: r6v1, types: [java.io.FileInputStream] */
    /* JADX WARNING: type inference failed for: r5v10 */
    /* JADX WARNING: type inference failed for: r6v10 */
    /* JADX WARNING: type inference failed for: r5v12 */
    /* JADX WARNING: type inference failed for: r5v13 */
    /* JADX WARNING: type inference failed for: r5v14 */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x012e, code lost:
        if (r0 != null) goto L_0x0103;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x0100 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0122 A[SYNTHETIC, Splitter:B:54:0x0122] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0129 A[SYNTHETIC, Splitter:B:58:0x0129] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0152 A[SYNTHETIC, Splitter:B:67:0x0152] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0159 A[SYNTHETIC, Splitter:B:71:0x0159] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0160  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void reportTbsLog() {
        /*
            r11 = this;
            java.util.Map<java.lang.String, java.lang.Object> r0 = com.tencent.smtt.sdk.QbSdk.n
            if (r0 == 0) goto L_0x0024
            java.util.Map<java.lang.String, java.lang.Object> r0 = com.tencent.smtt.sdk.QbSdk.n
            java.lang.String r1 = "SET_SENDREQUEST_AND_UPLOAD"
            boolean r0 = r0.containsKey(r1)
            if (r0 == 0) goto L_0x0024
            java.util.Map<java.lang.String, java.lang.Object> r0 = com.tencent.smtt.sdk.QbSdk.n
            java.lang.Object r0 = r0.get(r1)
            java.lang.String r1 = "false"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0024
            java.lang.String r0 = "upload"
            java.lang.String r1 = "[TbsLogReport.reportTbsLog] -- SET_SENDREQUEST_AND_UPLOAD is false"
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
            return
        L_0x0024:
            android.content.Context r0 = r11.c
            int r0 = com.tencent.smtt.utils.Apn.getApnType(r0)
            r1 = 3
            if (r0 == r1) goto L_0x002e
            return
        L_0x002e:
            java.lang.String r0 = com.tencent.smtt.utils.TbsLog.getTbsLogFilePath()
            if (r0 != 0) goto L_0x0035
            return
        L_0x0035:
            com.tencent.smtt.utils.g r1 = com.tencent.smtt.utils.g.a()
            java.lang.String r1 = r1.b()
            android.content.Context r2 = r11.c
            java.lang.String r2 = com.tencent.smtt.utils.b.f(r2)
            android.content.Context r3 = r11.c
            java.lang.String r3 = com.tencent.smtt.utils.b.i(r3)
            byte[] r2 = r2.getBytes()
            byte[] r3 = r3.getBytes()
            com.tencent.smtt.utils.g r4 = com.tencent.smtt.utils.g.a()     // Catch:{ Exception -> 0x0061 }
            byte[] r2 = r4.a(r2)     // Catch:{ Exception -> 0x0061 }
            com.tencent.smtt.utils.g r4 = com.tencent.smtt.utils.g.a()     // Catch:{ Exception -> 0x0061 }
            byte[] r3 = r4.a(r3)     // Catch:{ Exception -> 0x0061 }
        L_0x0061:
            java.lang.String r2 = com.tencent.smtt.utils.g.b(r2)
            java.lang.String r3 = com.tencent.smtt.utils.g.b(r3)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            android.content.Context r5 = r11.c
            com.tencent.smtt.utils.m r5 = com.tencent.smtt.utils.m.a(r5)
            java.lang.String r5 = r5.e()
            r4.append(r5)
            r4.append(r2)
            java.lang.String r2 = "&aid="
            r4.append(r2)
            r4.append(r3)
            java.lang.String r2 = r4.toString()
            java.util.HashMap r3 = new java.util.HashMap
            r3.<init>()
            java.lang.String r4 = "Content-Type"
            java.lang.String r5 = "application/octet-stream"
            r3.put(r4, r5)
            java.lang.String r4 = "Charset"
            java.lang.String r5 = "UTF-8"
            r3.put(r4, r5)
            android.content.Context r4 = r11.c
            java.lang.String r4 = com.tencent.smtt.utils.j.a((android.content.Context) r4)
            java.lang.String r5 = "QUA2"
            r3.put(r5, r4)
            r4 = 0
            r5 = 0
            java.io.File r6 = new java.io.File     // Catch:{ Exception -> 0x0119, all -> 0x0115 }
            java.lang.String r7 = com.tencent.smtt.utils.FileUtil.a     // Catch:{ Exception -> 0x0119, all -> 0x0115 }
            r6.<init>(r7)     // Catch:{ Exception -> 0x0119, all -> 0x0115 }
            com.tencent.smtt.sdk.TbsLogReport$a r6 = new com.tencent.smtt.sdk.TbsLogReport$a     // Catch:{ Exception -> 0x0119, all -> 0x0115 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0119, all -> 0x0115 }
            r7.<init>()     // Catch:{ Exception -> 0x0119, all -> 0x0115 }
            java.lang.String r8 = com.tencent.smtt.utils.FileUtil.a     // Catch:{ Exception -> 0x0119, all -> 0x0115 }
            r7.append(r8)     // Catch:{ Exception -> 0x0119, all -> 0x0115 }
            java.lang.String r8 = "/tbslog_temp.zip"
            r7.append(r8)     // Catch:{ Exception -> 0x0119, all -> 0x0115 }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x0119, all -> 0x0115 }
            r6.<init>(r0, r7)     // Catch:{ Exception -> 0x0119, all -> 0x0115 }
            r6.a()     // Catch:{ Exception -> 0x0119, all -> 0x0115 }
            java.io.File r0 = new java.io.File     // Catch:{ Exception -> 0x0119, all -> 0x0115 }
            java.lang.String r6 = com.tencent.smtt.utils.FileUtil.a     // Catch:{ Exception -> 0x0119, all -> 0x0115 }
            java.lang.String r7 = "tbslog_temp.zip"
            r0.<init>(r6, r7)     // Catch:{ Exception -> 0x0119, all -> 0x0115 }
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0112, all -> 0x010f }
            r6.<init>(r0)     // Catch:{ Exception -> 0x0112, all -> 0x010f }
            r7 = 8192(0x2000, float:1.14794E-41)
            byte[] r7 = new byte[r7]     // Catch:{ Exception -> 0x010c, all -> 0x0109 }
            java.io.ByteArrayOutputStream r8 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x010c, all -> 0x0109 }
            r8.<init>()     // Catch:{ Exception -> 0x010c, all -> 0x0109 }
        L_0x00e3:
            int r9 = r6.read(r7)     // Catch:{ Exception -> 0x0107 }
            r10 = -1
            if (r9 == r10) goto L_0x00ee
            r8.write(r7, r4, r9)     // Catch:{ Exception -> 0x0107 }
            goto L_0x00e3
        L_0x00ee:
            r8.flush()     // Catch:{ Exception -> 0x0107 }
            com.tencent.smtt.utils.g r7 = com.tencent.smtt.utils.g.a()     // Catch:{ Exception -> 0x0107 }
            byte[] r9 = r8.toByteArray()     // Catch:{ Exception -> 0x0107 }
            byte[] r5 = r7.a(r9)     // Catch:{ Exception -> 0x0107 }
            r6.close()     // Catch:{ Exception -> 0x0100 }
        L_0x0100:
            r8.close()     // Catch:{ Exception -> 0x0103 }
        L_0x0103:
            r0.delete()
            goto L_0x0131
        L_0x0107:
            r7 = move-exception
            goto L_0x011d
        L_0x0109:
            r1 = move-exception
            r8 = r5
            goto L_0x014f
        L_0x010c:
            r7 = move-exception
            r8 = r5
            goto L_0x011d
        L_0x010f:
            r1 = move-exception
            r8 = r5
            goto L_0x0150
        L_0x0112:
            r7 = move-exception
            r6 = r5
            goto L_0x011c
        L_0x0115:
            r1 = move-exception
            r0 = r5
            r8 = r0
            goto L_0x0150
        L_0x0119:
            r7 = move-exception
            r0 = r5
            r6 = r0
        L_0x011c:
            r8 = r6
        L_0x011d:
            r7.printStackTrace()     // Catch:{ all -> 0x014e }
            if (r6 == 0) goto L_0x0127
            r6.close()     // Catch:{ Exception -> 0x0126 }
            goto L_0x0127
        L_0x0126:
        L_0x0127:
            if (r8 == 0) goto L_0x012e
            r8.close()     // Catch:{ Exception -> 0x012d }
            goto L_0x012e
        L_0x012d:
        L_0x012e:
            if (r0 == 0) goto L_0x0131
            goto L_0x0103
        L_0x0131:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r2)
            java.lang.String r2 = "&ek="
            r0.append(r2)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.tencent.smtt.sdk.TbsLogReport$2 r1 = new com.tencent.smtt.sdk.TbsLogReport$2
            r1.<init>()
            com.tencent.smtt.utils.f.a(r0, r3, r5, r1, r4)
            return
        L_0x014e:
            r1 = move-exception
        L_0x014f:
            r5 = r6
        L_0x0150:
            if (r5 == 0) goto L_0x0157
            r5.close()     // Catch:{ Exception -> 0x0156 }
            goto L_0x0157
        L_0x0156:
        L_0x0157:
            if (r8 == 0) goto L_0x015e
            r8.close()     // Catch:{ Exception -> 0x015d }
            goto L_0x015e
        L_0x015d:
        L_0x015e:
            if (r0 == 0) goto L_0x0163
            r0.delete()
        L_0x0163:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsLogReport.reportTbsLog():void");
    }

    public void setInstallErrorCode(int i, String str) {
        setInstallErrorCode(i, str, EventType.TYPE_INSTALL);
    }

    public void setInstallErrorCode(int i, String str, EventType eventType) {
        if (!(i == 200 || i == 220 || i == 221)) {
            TbsLog.i(TbsDownloader.LOGTAG, "error occured in installation, errorCode:" + i, true);
        }
        TbsLogInfo tbsLogInfo = tbsLogInfo();
        tbsLogInfo.setFailDetail(str);
        a(i, tbsLogInfo, eventType);
    }

    public void setInstallErrorCode(int i, Throwable th) {
        TbsLogInfo tbsLogInfo = tbsLogInfo();
        tbsLogInfo.setFailDetail(th);
        a(i, tbsLogInfo, EventType.TYPE_INSTALL);
    }

    public void setLoadErrorCode(int i, String str) {
        TbsLogInfo tbsLogInfo = tbsLogInfo();
        tbsLogInfo.setErrorCode(i);
        tbsLogInfo.setEventTime(System.currentTimeMillis());
        tbsLogInfo.setFailDetail(str);
        eventReport(EventType.TYPE_LOAD, tbsLogInfo);
    }

    public void setLoadErrorCode(int i, Throwable th) {
        String str;
        if (th != null) {
            str = "msg: " + th.getMessage() + "; err: " + th + "; cause: " + Log.getStackTraceString(th.getCause());
            if (str.length() > 1024) {
                str = str.substring(0, 1024);
            }
        } else {
            str = DateLayout.NULL_DATE_FORMAT;
        }
        setLoadErrorCode(i, str);
    }

    public void setShouldUploadEventReport(boolean z) {
        this.d = z;
    }

    public TbsLogInfo tbsLogInfo() {
        return new TbsLogInfo();
    }
}
