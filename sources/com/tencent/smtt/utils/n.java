package com.tencent.smtt.utils;

import android.content.Context;
import com.tencent.smtt.sdk.QbSdk;
import java.io.File;

public class n {
    private static n e;
    public boolean a = false;
    private Context b = null;
    private File c = null;
    private boolean d = false;
    private File f = null;

    private n(Context context) {
        this.b = context.getApplicationContext();
        b();
    }

    public static synchronized n a() {
        n nVar;
        synchronized (n.class) {
            nVar = e;
        }
        return nVar;
    }

    public static synchronized n a(Context context) {
        n nVar;
        synchronized (n.class) {
            if (e == null) {
                e = new n(context);
            }
            nVar = e;
        }
        return nVar;
    }

    private File d() {
        try {
            if (this.c == null) {
                File file = new File(QbSdk.getTbsFolderDir(this.b), "core_private");
                this.c = file;
                if (file == null || !file.isDirectory()) {
                    return null;
                }
            }
            File file2 = new File(this.c, "debug.conf");
            if (!file2.exists()) {
                file2.createNewFile();
            }
            return file2;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void a(boolean z) {
        this.d = z;
        c();
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x004e A[SYNTHETIC, Splitter:B:27:0x004e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void b() {
        /*
            r4 = this;
            monitor-enter(r4)
            r0 = 0
            java.io.File r1 = r4.f     // Catch:{ all -> 0x0048 }
            if (r1 != 0) goto L_0x000c
            java.io.File r1 = r4.d()     // Catch:{ all -> 0x0048 }
            r4.f = r1     // Catch:{ all -> 0x0048 }
        L_0x000c:
            java.io.File r1 = r4.f     // Catch:{ all -> 0x0048 }
            if (r1 != 0) goto L_0x0012
            monitor-exit(r4)
            return
        L_0x0012:
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ all -> 0x0048 }
            java.io.File r2 = r4.f     // Catch:{ all -> 0x0048 }
            r1.<init>(r2)     // Catch:{ all -> 0x0048 }
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ all -> 0x0048 }
            r2.<init>(r1)     // Catch:{ all -> 0x0048 }
            java.util.Properties r0 = new java.util.Properties     // Catch:{ all -> 0x0045 }
            r0.<init>()     // Catch:{ all -> 0x0045 }
            r0.load(r2)     // Catch:{ all -> 0x0045 }
            java.lang.String r1 = "setting_forceUseSystemWebview"
            java.lang.String r3 = ""
            java.lang.String r0 = r0.getProperty(r1, r3)     // Catch:{ all -> 0x0045 }
            java.lang.String r1 = ""
            boolean r1 = r1.equals(r0)     // Catch:{ all -> 0x0045 }
            if (r1 != 0) goto L_0x003c
            boolean r0 = java.lang.Boolean.parseBoolean(r0)     // Catch:{ all -> 0x0045 }
            r4.a = r0     // Catch:{ all -> 0x0045 }
        L_0x003c:
            r2.close()     // Catch:{ Exception -> 0x0040 }
            goto L_0x0056
        L_0x0040:
            r0 = move-exception
        L_0x0041:
            r0.printStackTrace()     // Catch:{ all -> 0x0052 }
            goto L_0x0056
        L_0x0045:
            r1 = move-exception
            r0 = r2
            goto L_0x0049
        L_0x0048:
            r1 = move-exception
        L_0x0049:
            r1.printStackTrace()     // Catch:{ all -> 0x0058 }
            if (r0 == 0) goto L_0x0056
            r0.close()     // Catch:{ Exception -> 0x0054 }
            goto L_0x0056
        L_0x0052:
            r0 = move-exception
            goto L_0x0064
        L_0x0054:
            r0 = move-exception
            goto L_0x0041
        L_0x0056:
            monitor-exit(r4)
            return
        L_0x0058:
            r1 = move-exception
            if (r0 == 0) goto L_0x0063
            r0.close()     // Catch:{ Exception -> 0x005f }
            goto L_0x0063
        L_0x005f:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x0052 }
        L_0x0063:
            throw r1     // Catch:{ all -> 0x0052 }
        L_0x0064:
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.n.b():void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: java.io.BufferedInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: java.io.BufferedOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: java.io.BufferedInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: java.io.BufferedOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: java.io.BufferedInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: java.io.BufferedOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: java.io.BufferedOutputStream} */
    /* JADX WARNING: type inference failed for: r0v0, types: [java.io.BufferedInputStream, java.lang.String, java.io.BufferedOutputStream] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:22:0x0055=Splitter:B:22:0x0055, B:35:0x006f=Splitter:B:35:0x006f} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void c() {
        /*
            r7 = this;
            r0 = 0
            java.io.File r1 = r7.d()     // Catch:{ all -> 0x0060 }
            if (r1 != 0) goto L_0x0018
            r0.close()     // Catch:{ Exception -> 0x000b }
            goto L_0x000f
        L_0x000b:
            r1 = move-exception
            r1.printStackTrace()
        L_0x000f:
            r0.close()     // Catch:{ Exception -> 0x0013 }
            goto L_0x0017
        L_0x0013:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0017:
            return
        L_0x0018:
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ all -> 0x0060 }
            r2.<init>(r1)     // Catch:{ all -> 0x0060 }
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch:{ all -> 0x0060 }
            r3.<init>(r2)     // Catch:{ all -> 0x0060 }
            java.util.Properties r2 = new java.util.Properties     // Catch:{ all -> 0x005b }
            r2.<init>()     // Catch:{ all -> 0x005b }
            r2.load(r3)     // Catch:{ all -> 0x005b }
            java.lang.String r4 = "setting_forceUseSystemWebview"
            boolean r5 = r7.a     // Catch:{ all -> 0x005b }
            java.lang.String r5 = java.lang.Boolean.toString(r5)     // Catch:{ all -> 0x005b }
            r2.setProperty(r4, r5)     // Catch:{ all -> 0x005b }
            java.lang.String r4 = "result_systemWebviewForceUsed"
            boolean r5 = r7.d     // Catch:{ all -> 0x005b }
            java.lang.String r5 = java.lang.Boolean.toString(r5)     // Catch:{ all -> 0x005b }
            r2.setProperty(r4, r5)     // Catch:{ all -> 0x005b }
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ all -> 0x005b }
            r4.<init>(r1)     // Catch:{ all -> 0x005b }
            java.io.BufferedOutputStream r1 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x005b }
            r1.<init>(r4)     // Catch:{ all -> 0x005b }
            r2.store(r1, r0)     // Catch:{ all -> 0x0059 }
            r3.close()     // Catch:{ Exception -> 0x0051 }
            goto L_0x0055
        L_0x0051:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0055:
            r1.close()     // Catch:{ Exception -> 0x0073 }
            goto L_0x0077
        L_0x0059:
            r0 = move-exception
            goto L_0x0064
        L_0x005b:
            r1 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
            goto L_0x0064
        L_0x0060:
            r1 = move-exception
            r3 = r0
            r0 = r1
            r1 = r3
        L_0x0064:
            r0.printStackTrace()     // Catch:{ all -> 0x0078 }
            r3.close()     // Catch:{ Exception -> 0x006b }
            goto L_0x006f
        L_0x006b:
            r0 = move-exception
            r0.printStackTrace()
        L_0x006f:
            r1.close()     // Catch:{ Exception -> 0x0073 }
            goto L_0x0077
        L_0x0073:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0077:
            return
        L_0x0078:
            r0 = move-exception
            r3.close()     // Catch:{ Exception -> 0x007d }
            goto L_0x0081
        L_0x007d:
            r2 = move-exception
            r2.printStackTrace()
        L_0x0081:
            r1.close()     // Catch:{ Exception -> 0x0085 }
            goto L_0x0089
        L_0x0085:
            r1 = move-exception
            r1.printStackTrace()
        L_0x0089:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.n.c():void");
    }
}
