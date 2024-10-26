package com.tencent.bugly.proguard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.List;
import java.util.Map;

/* compiled from: BUGLY */
public final class p {
    private static p a = null;
    private static q b = null;
    private static boolean c = false;

    private p(Context context, List<com.tencent.bugly.a> list) {
        b = new q(context, list);
    }

    public static synchronized p a(Context context, List<com.tencent.bugly.a> list) {
        p pVar;
        synchronized (p.class) {
            if (a == null) {
                a = new p(context, list);
            }
            pVar = a;
        }
        return pVar;
    }

    public static synchronized p a() {
        p pVar;
        synchronized (p.class) {
            pVar = a;
        }
        return pVar;
    }

    public final long a(String str, ContentValues contentValues, o oVar, boolean z) {
        return a(str, contentValues, (o) null);
    }

    public final Cursor a(String str, String[] strArr, String str2, String[] strArr2, o oVar, boolean z) {
        return a(false, str, strArr, str2, (String[]) null, (String) null, (String) null, (String) null, (String) null, (o) null);
    }

    public final int a(String str, String str2, String[] strArr, o oVar, boolean z) {
        return a(str, str2, (String[]) null, (o) null);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002d, code lost:
        if (r9 != null) goto L_0x002f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        java.lang.Long.valueOf(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003f, code lost:
        if (r9 != null) goto L_0x002f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized long a(java.lang.String r7, android.content.ContentValues r8, com.tencent.bugly.proguard.o r9) {
        /*
            r6 = this;
            monitor-enter(r6)
            r0 = 0
            com.tencent.bugly.proguard.q r2 = b     // Catch:{ all -> 0x0035 }
            android.database.sqlite.SQLiteDatabase r2 = r2.getWritableDatabase()     // Catch:{ all -> 0x0035 }
            if (r2 == 0) goto L_0x002d
            if (r8 == 0) goto L_0x002d
            java.lang.String r3 = "_id"
            long r2 = r2.replace(r7, r3, r8)     // Catch:{ all -> 0x0035 }
            r8 = 0
            r4 = 1
            int r5 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r5 < 0) goto L_0x0023
            java.lang.String r5 = "[Database] insert %s success."
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x0035 }
            r4[r8] = r7     // Catch:{ all -> 0x0035 }
            com.tencent.bugly.proguard.x.c(r5, r4)     // Catch:{ all -> 0x0035 }
            goto L_0x002c
        L_0x0023:
            java.lang.String r5 = "[Database] replace %s error."
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x0035 }
            r4[r8] = r7     // Catch:{ all -> 0x0035 }
            com.tencent.bugly.proguard.x.d(r5, r4)     // Catch:{ all -> 0x0035 }
        L_0x002c:
            r0 = r2
        L_0x002d:
            if (r9 == 0) goto L_0x0042
        L_0x002f:
            java.lang.Long.valueOf(r0)     // Catch:{ all -> 0x0033 }
            goto L_0x0042
        L_0x0033:
            r7 = move-exception
            goto L_0x004b
        L_0x0035:
            r7 = move-exception
            boolean r8 = com.tencent.bugly.proguard.x.a(r7)     // Catch:{ all -> 0x0044 }
            if (r8 != 0) goto L_0x003f
            r7.printStackTrace()     // Catch:{ all -> 0x0044 }
        L_0x003f:
            if (r9 == 0) goto L_0x0042
            goto L_0x002f
        L_0x0042:
            monitor-exit(r6)
            return r0
        L_0x0044:
            r7 = move-exception
            if (r9 == 0) goto L_0x004a
            java.lang.Long.valueOf(r0)     // Catch:{ all -> 0x0033 }
        L_0x004a:
            throw r7     // Catch:{ all -> 0x0033 }
        L_0x004b:
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.p.a(java.lang.String, android.content.ContentValues, com.tencent.bugly.proguard.o):long");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002f, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized android.database.Cursor a(boolean r13, java.lang.String r14, java.lang.String[] r15, java.lang.String r16, java.lang.String[] r17, java.lang.String r18, java.lang.String r19, java.lang.String r20, java.lang.String r21, com.tencent.bugly.proguard.o r22) {
        /*
            r12 = this;
            monitor-enter(r12)
            r1 = 0
            com.tencent.bugly.proguard.q r0 = b     // Catch:{ all -> 0x001e }
            android.database.sqlite.SQLiteDatabase r2 = r0.getWritableDatabase()     // Catch:{ all -> 0x001e }
            if (r2 == 0) goto L_0x0028
            r3 = r13
            r4 = r14
            r5 = r15
            r6 = r16
            r7 = r17
            r8 = r18
            r9 = r19
            r10 = r20
            r11 = r21
            android.database.Cursor r1 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10, r11)     // Catch:{ all -> 0x001e }
            goto L_0x0028
        L_0x001e:
            r0 = move-exception
            boolean r2 = com.tencent.bugly.proguard.x.a(r0)     // Catch:{ all -> 0x002a }
            if (r2 != 0) goto L_0x0028
            r0.printStackTrace()     // Catch:{ all -> 0x002a }
        L_0x0028:
            monitor-exit(r12)
            return r1
        L_0x002a:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x002c }
        L_0x002c:
            r0 = move-exception
            r1 = r0
            monitor-exit(r12)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.p.a(boolean, java.lang.String, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.tencent.bugly.proguard.o):android.database.Cursor");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0020, code lost:
        if (r6 != null) goto L_0x0010;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000e, code lost:
        if (r6 != null) goto L_0x0010;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:?, code lost:
        java.lang.Integer.valueOf(r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int a(java.lang.String r3, java.lang.String r4, java.lang.String[] r5, com.tencent.bugly.proguard.o r6) {
        /*
            r2 = this;
            monitor-enter(r2)
            r0 = 0
            com.tencent.bugly.proguard.q r1 = b     // Catch:{ all -> 0x0016 }
            android.database.sqlite.SQLiteDatabase r1 = r1.getWritableDatabase()     // Catch:{ all -> 0x0016 }
            if (r1 == 0) goto L_0x000e
            int r0 = r1.delete(r3, r4, r5)     // Catch:{ all -> 0x0016 }
        L_0x000e:
            if (r6 == 0) goto L_0x0023
        L_0x0010:
            java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x0014 }
            goto L_0x0023
        L_0x0014:
            r3 = move-exception
            goto L_0x002c
        L_0x0016:
            r3 = move-exception
            boolean r4 = com.tencent.bugly.proguard.x.a(r3)     // Catch:{ all -> 0x0025 }
            if (r4 != 0) goto L_0x0020
            r3.printStackTrace()     // Catch:{ all -> 0x0025 }
        L_0x0020:
            if (r6 == 0) goto L_0x0023
            goto L_0x0010
        L_0x0023:
            monitor-exit(r2)
            return r0
        L_0x0025:
            r3 = move-exception
            if (r6 == 0) goto L_0x002b
            java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x0014 }
        L_0x002b:
            throw r3     // Catch:{ all -> 0x0014 }
        L_0x002c:
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.p.a(java.lang.String, java.lang.String, java.lang.String[], com.tencent.bugly.proguard.o):int");
    }

    public final boolean a(int i, String str, byte[] bArr, o oVar, boolean z) {
        if (z) {
            return a(i, str, bArr, (o) null);
        }
        a aVar = new a(4, (o) null);
        aVar.a(i, str, bArr);
        w.a().a(aVar);
        return true;
    }

    public final Map<String, byte[]> a(int i, o oVar, boolean z) {
        return a(i, (o) null);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0027, code lost:
        if (r8 != null) goto L_0x0019;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002a, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0017, code lost:
        if (r8 != null) goto L_0x0019;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0019, code lost:
        java.lang.Boolean.valueOf(r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(int r5, java.lang.String r6, byte[] r7, com.tencent.bugly.proguard.o r8) {
        /*
            r4 = this;
            r0 = 0
            com.tencent.bugly.proguard.r r1 = new com.tencent.bugly.proguard.r     // Catch:{ all -> 0x001d }
            r1.<init>()     // Catch:{ all -> 0x001d }
            long r2 = (long) r5     // Catch:{ all -> 0x001d }
            r1.a = r2     // Catch:{ all -> 0x001d }
            r1.f = r6     // Catch:{ all -> 0x001d }
            long r5 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x001d }
            r1.e = r5     // Catch:{ all -> 0x001d }
            r1.g = r7     // Catch:{ all -> 0x001d }
            boolean r0 = r4.b((com.tencent.bugly.proguard.r) r1)     // Catch:{ all -> 0x001d }
            if (r8 == 0) goto L_0x002a
        L_0x0019:
            java.lang.Boolean.valueOf(r0)
            goto L_0x002a
        L_0x001d:
            r5 = move-exception
            boolean r6 = com.tencent.bugly.proguard.x.a(r5)     // Catch:{ all -> 0x002b }
            if (r6 != 0) goto L_0x0027
            r5.printStackTrace()     // Catch:{ all -> 0x002b }
        L_0x0027:
            if (r8 == 0) goto L_0x002a
            goto L_0x0019
        L_0x002a:
            return r0
        L_0x002b:
            r5 = move-exception
            if (r8 == 0) goto L_0x0031
            java.lang.Boolean.valueOf(r0)
        L_0x0031:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.p.a(int, java.lang.String, byte[], com.tencent.bugly.proguard.o):boolean");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Map<java.lang.String, byte[]> a(int r4, com.tencent.bugly.proguard.o r5) {
        /*
            r3 = this;
            r0 = 0
            java.util.List r4 = r3.c((int) r4)     // Catch:{ all -> 0x002b }
            if (r4 == 0) goto L_0x0035
            java.util.HashMap r1 = new java.util.HashMap     // Catch:{ all -> 0x002b }
            r1.<init>()     // Catch:{ all -> 0x002b }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ all -> 0x0028 }
        L_0x0010:
            boolean r0 = r4.hasNext()     // Catch:{ all -> 0x0028 }
            if (r0 == 0) goto L_0x0026
            java.lang.Object r0 = r4.next()     // Catch:{ all -> 0x0028 }
            com.tencent.bugly.proguard.r r0 = (com.tencent.bugly.proguard.r) r0     // Catch:{ all -> 0x0028 }
            byte[] r2 = r0.g     // Catch:{ all -> 0x0028 }
            if (r2 == 0) goto L_0x0010
            java.lang.String r0 = r0.f     // Catch:{ all -> 0x0028 }
            r1.put(r0, r2)     // Catch:{ all -> 0x0028 }
            goto L_0x0010
        L_0x0026:
            r0 = r1
            goto L_0x0035
        L_0x0028:
            r4 = move-exception
            r0 = r1
            goto L_0x002c
        L_0x002b:
            r4 = move-exception
        L_0x002c:
            boolean r1 = com.tencent.bugly.proguard.x.a(r4)     // Catch:{ all -> 0x0036 }
            if (r1 != 0) goto L_0x0035
            r4.printStackTrace()     // Catch:{ all -> 0x0036 }
        L_0x0035:
            return r0
        L_0x0036:
            r4 = move-exception
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.p.a(int, com.tencent.bugly.proguard.o):java.util.Map");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0035, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean a(com.tencent.bugly.proguard.r r8) {
        /*
            r7 = this;
            monitor-enter(r7)
            r0 = 0
            if (r8 != 0) goto L_0x0006
            monitor-exit(r7)
            return r0
        L_0x0006:
            com.tencent.bugly.proguard.q r1 = b     // Catch:{ all -> 0x0036 }
            android.database.sqlite.SQLiteDatabase r1 = r1.getWritableDatabase()     // Catch:{ all -> 0x0036 }
            if (r1 == 0) goto L_0x0034
            android.content.ContentValues r2 = c((com.tencent.bugly.proguard.r) r8)     // Catch:{ all -> 0x0036 }
            if (r2 == 0) goto L_0x0034
            java.lang.String r3 = "t_lr"
            java.lang.String r4 = "_id"
            long r1 = r1.replace(r3, r4, r2)     // Catch:{ all -> 0x0036 }
            r3 = 0
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 < 0) goto L_0x0032
            java.lang.String r3 = "[Database] insert %s success."
            r4 = 1
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch:{ all -> 0x0036 }
            java.lang.String r6 = "t_lr"
            r5[r0] = r6     // Catch:{ all -> 0x0036 }
            com.tencent.bugly.proguard.x.c(r3, r5)     // Catch:{ all -> 0x0036 }
            r8.a = r1     // Catch:{ all -> 0x0036 }
            monitor-exit(r7)
            return r4
        L_0x0032:
            monitor-exit(r7)
            return r0
        L_0x0034:
            monitor-exit(r7)
            return r0
        L_0x0036:
            r8 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r8)     // Catch:{ all -> 0x0042 }
            if (r1 != 0) goto L_0x0040
            r8.printStackTrace()     // Catch:{ all -> 0x0042 }
        L_0x0040:
            monitor-exit(r7)
            return r0
        L_0x0042:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x0044 }
        L_0x0044:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.p.a(com.tencent.bugly.proguard.r):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0035, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized boolean b(com.tencent.bugly.proguard.r r8) {
        /*
            r7 = this;
            monitor-enter(r7)
            r0 = 0
            if (r8 != 0) goto L_0x0006
            monitor-exit(r7)
            return r0
        L_0x0006:
            com.tencent.bugly.proguard.q r1 = b     // Catch:{ all -> 0x0036 }
            android.database.sqlite.SQLiteDatabase r1 = r1.getWritableDatabase()     // Catch:{ all -> 0x0036 }
            if (r1 == 0) goto L_0x0034
            android.content.ContentValues r2 = d(r8)     // Catch:{ all -> 0x0036 }
            if (r2 == 0) goto L_0x0034
            java.lang.String r3 = "t_pf"
            java.lang.String r4 = "_id"
            long r1 = r1.replace(r3, r4, r2)     // Catch:{ all -> 0x0036 }
            r3 = 0
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 < 0) goto L_0x0032
            java.lang.String r3 = "[Database] insert %s success."
            r4 = 1
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch:{ all -> 0x0036 }
            java.lang.String r6 = "t_pf"
            r5[r0] = r6     // Catch:{ all -> 0x0036 }
            com.tencent.bugly.proguard.x.c(r3, r5)     // Catch:{ all -> 0x0036 }
            r8.a = r1     // Catch:{ all -> 0x0036 }
            monitor-exit(r7)
            return r4
        L_0x0032:
            monitor-exit(r7)
            return r0
        L_0x0034:
            monitor-exit(r7)
            return r0
        L_0x0036:
            r8 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r8)     // Catch:{ all -> 0x0042 }
            if (r1 != 0) goto L_0x0040
            r8.printStackTrace()     // Catch:{ all -> 0x0042 }
        L_0x0040:
            monitor-exit(r7)
            return r0
        L_0x0042:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x0044 }
        L_0x0044:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.p.b(com.tencent.bugly.proguard.r):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0035, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x009f, code lost:
        return r2;
     */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00aa A[Catch:{ all -> 0x00b3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00af A[SYNTHETIC, Splitter:B:47:0x00af] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized java.util.List<com.tencent.bugly.proguard.r> a(int r12) {
        /*
            r11 = this;
            monitor-enter(r11)
            com.tencent.bugly.proguard.q r0 = b     // Catch:{ all -> 0x00bc }
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()     // Catch:{ all -> 0x00bc }
            r9 = 0
            if (r0 == 0) goto L_0x00ba
            if (r12 < 0) goto L_0x0020
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x001c }
            java.lang.String r2 = "_tp = "
            r1.<init>(r2)     // Catch:{ all -> 0x001c }
            r1.append(r12)     // Catch:{ all -> 0x001c }
            java.lang.String r12 = r1.toString()     // Catch:{ all -> 0x001c }
            r4 = r12
            goto L_0x0021
        L_0x001c:
            r12 = move-exception
            r0 = r9
            goto L_0x00a4
        L_0x0020:
            r4 = r9
        L_0x0021:
            java.lang.String r2 = "t_lr"
            r3 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r1 = r0
            android.database.Cursor r12 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x001c }
            if (r12 != 0) goto L_0x0036
            if (r12 == 0) goto L_0x0034
            r12.close()     // Catch:{ all -> 0x00bc }
        L_0x0034:
            monitor-exit(r11)
            return r9
        L_0x0036:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a0 }
            r1.<init>()     // Catch:{ all -> 0x00a0 }
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x00a0 }
            r2.<init>()     // Catch:{ all -> 0x00a0 }
        L_0x0040:
            boolean r3 = r12.moveToNext()     // Catch:{ all -> 0x00a0 }
            r4 = 0
            if (r3 == 0) goto L_0x0071
            com.tencent.bugly.proguard.r r3 = a((android.database.Cursor) r12)     // Catch:{ all -> 0x00a0 }
            if (r3 == 0) goto L_0x0051
            r2.add(r3)     // Catch:{ all -> 0x00a0 }
            goto L_0x0040
        L_0x0051:
            java.lang.String r3 = "_id"
            int r3 = r12.getColumnIndex(r3)     // Catch:{ all -> 0x0069 }
            long r5 = r12.getLong(r3)     // Catch:{ all -> 0x0069 }
            java.lang.String r3 = " or _id"
            r1.append(r3)     // Catch:{ all -> 0x0069 }
            java.lang.String r3 = " = "
            r1.append(r3)     // Catch:{ all -> 0x0069 }
            r1.append(r5)     // Catch:{ all -> 0x0069 }
            goto L_0x0040
        L_0x0069:
            java.lang.String r3 = "[Database] unknown id."
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x00a0 }
            com.tencent.bugly.proguard.x.d(r3, r4)     // Catch:{ all -> 0x00a0 }
            goto L_0x0040
        L_0x0071:
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00a0 }
            int r3 = r1.length()     // Catch:{ all -> 0x00a0 }
            if (r3 <= 0) goto L_0x0099
            r3 = 4
            java.lang.String r1 = r1.substring(r3)     // Catch:{ all -> 0x00a0 }
            java.lang.String r3 = "t_lr"
            int r0 = r0.delete(r3, r1, r9)     // Catch:{ all -> 0x00a0 }
            java.lang.String r1 = "[Database] deleted %s illegal data %d"
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x00a0 }
            java.lang.String r5 = "t_lr"
            r3[r4] = r5     // Catch:{ all -> 0x00a0 }
            r4 = 1
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x00a0 }
            r3[r4] = r0     // Catch:{ all -> 0x00a0 }
            com.tencent.bugly.proguard.x.d(r1, r3)     // Catch:{ all -> 0x00a0 }
        L_0x0099:
            if (r12 == 0) goto L_0x009e
            r12.close()     // Catch:{ all -> 0x00bc }
        L_0x009e:
            monitor-exit(r11)
            return r2
        L_0x00a0:
            r0 = move-exception
            r10 = r0
            r0 = r12
            r12 = r10
        L_0x00a4:
            boolean r1 = com.tencent.bugly.proguard.x.a(r12)     // Catch:{ all -> 0x00b3 }
            if (r1 != 0) goto L_0x00ad
            r12.printStackTrace()     // Catch:{ all -> 0x00b3 }
        L_0x00ad:
            if (r0 == 0) goto L_0x00ba
            r0.close()     // Catch:{ all -> 0x00bc }
            goto L_0x00ba
        L_0x00b3:
            r12 = move-exception
            if (r0 == 0) goto L_0x00b9
            r0.close()     // Catch:{ all -> 0x00bc }
        L_0x00b9:
            throw r12     // Catch:{ all -> 0x00bc }
        L_0x00ba:
            monitor-exit(r11)
            return r9
        L_0x00bc:
            r12 = move-exception
            monitor-exit(r11)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.p.a(int):java.util.List");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x007a, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void a(java.util.List<com.tencent.bugly.proguard.r> r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            if (r5 == 0) goto L_0x0079
            int r0 = r5.size()     // Catch:{ all -> 0x0076 }
            if (r0 != 0) goto L_0x000a
            goto L_0x0079
        L_0x000a:
            com.tencent.bugly.proguard.q r0 = b     // Catch:{ all -> 0x0076 }
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()     // Catch:{ all -> 0x0076 }
            if (r0 == 0) goto L_0x0074
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0076 }
            r1.<init>()     // Catch:{ all -> 0x0076 }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ all -> 0x0076 }
        L_0x001b:
            boolean r2 = r5.hasNext()     // Catch:{ all -> 0x0076 }
            if (r2 == 0) goto L_0x0037
            java.lang.Object r2 = r5.next()     // Catch:{ all -> 0x0076 }
            com.tencent.bugly.proguard.r r2 = (com.tencent.bugly.proguard.r) r2     // Catch:{ all -> 0x0076 }
            java.lang.String r3 = " or _id"
            r1.append(r3)     // Catch:{ all -> 0x0076 }
            java.lang.String r3 = " = "
            r1.append(r3)     // Catch:{ all -> 0x0076 }
            long r2 = r2.a     // Catch:{ all -> 0x0076 }
            r1.append(r2)     // Catch:{ all -> 0x0076 }
            goto L_0x001b
        L_0x0037:
            java.lang.String r5 = r1.toString()     // Catch:{ all -> 0x0076 }
            int r2 = r5.length()     // Catch:{ all -> 0x0076 }
            if (r2 <= 0) goto L_0x0046
            r2 = 4
            java.lang.String r5 = r5.substring(r2)     // Catch:{ all -> 0x0076 }
        L_0x0046:
            r2 = 0
            r1.setLength(r2)     // Catch:{ all -> 0x0076 }
            java.lang.String r1 = "t_lr"
            r3 = 0
            int r5 = r0.delete(r1, r5, r3)     // Catch:{ all -> 0x0066 }
            java.lang.String r0 = "[Database] deleted %s data %d"
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x0066 }
            java.lang.String r3 = "t_lr"
            r1[r2] = r3     // Catch:{ all -> 0x0066 }
            r2 = 1
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0066 }
            r1[r2] = r5     // Catch:{ all -> 0x0066 }
            com.tencent.bugly.proguard.x.c(r0, r1)     // Catch:{ all -> 0x0066 }
            monitor-exit(r4)
            return
        L_0x0066:
            r5 = move-exception
            boolean r0 = com.tencent.bugly.proguard.x.a(r5)     // Catch:{ all -> 0x0072 }
            if (r0 != 0) goto L_0x0070
            r5.printStackTrace()     // Catch:{ all -> 0x0072 }
        L_0x0070:
            monitor-exit(r4)
            return
        L_0x0072:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0076 }
        L_0x0074:
            monitor-exit(r4)
            return
        L_0x0076:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        L_0x0079:
            monitor-exit(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.p.a(java.util.List):void");
    }

    public final synchronized void b(int i) {
        String str;
        SQLiteDatabase writableDatabase = b.getWritableDatabase();
        if (writableDatabase != null) {
            if (i >= 0) {
                try {
                    str = "_tp = " + i;
                } catch (Throwable th) {
                    if (!x.a(th)) {
                        th.printStackTrace();
                    }
                    return;
                }
            } else {
                str = null;
            }
            x.c("[Database] deleted %s data %d", "t_lr", Integer.valueOf(writableDatabase.delete("t_lr", str, (String[]) null)));
        }
    }

    private static ContentValues c(r rVar) {
        if (rVar == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (rVar.a > 0) {
                contentValues.put("_id", Long.valueOf(rVar.a));
            }
            contentValues.put("_tp", Integer.valueOf(rVar.b));
            contentValues.put("_pc", rVar.c);
            contentValues.put("_th", rVar.d);
            contentValues.put("_tm", Long.valueOf(rVar.e));
            if (rVar.g != null) {
                contentValues.put("_dt", rVar.g);
            }
            return contentValues;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    private static r a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            r rVar = new r();
            rVar.a = cursor.getLong(cursor.getColumnIndex("_id"));
            rVar.b = cursor.getInt(cursor.getColumnIndex("_tp"));
            rVar.c = cursor.getString(cursor.getColumnIndex("_pc"));
            rVar.d = cursor.getString(cursor.getColumnIndex("_th"));
            rVar.e = cursor.getLong(cursor.getColumnIndex("_tm"));
            rVar.g = cursor.getBlob(cursor.getColumnIndex("_dt"));
            return rVar;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002d, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00a0, code lost:
        return r4;
     */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00ab A[Catch:{ all -> 0x00b5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00b0 A[SYNTHETIC, Splitter:B:42:0x00b0] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized java.util.List<com.tencent.bugly.proguard.r> c(int r12) {
        /*
            r11 = this;
            monitor-enter(r11)
            r0 = 0
            com.tencent.bugly.proguard.q r1 = b     // Catch:{ all -> 0x00a3 }
            android.database.sqlite.SQLiteDatabase r1 = r1.getWritableDatabase()     // Catch:{ all -> 0x00a3 }
            if (r1 == 0) goto L_0x00b3
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a3 }
            java.lang.String r3 = "_id = "
            r2.<init>(r3)     // Catch:{ all -> 0x00a3 }
            r2.append(r12)     // Catch:{ all -> 0x00a3 }
            java.lang.String r10 = r2.toString()     // Catch:{ all -> 0x00a3 }
            java.lang.String r3 = "t_pf"
            r4 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r2 = r1
            r5 = r10
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ all -> 0x00a3 }
            if (r2 != 0) goto L_0x002e
            if (r2 == 0) goto L_0x002c
            r2.close()     // Catch:{ all -> 0x00bc }
        L_0x002c:
            monitor-exit(r11)
            return r0
        L_0x002e:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a1 }
            r3.<init>()     // Catch:{ all -> 0x00a1 }
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ all -> 0x00a1 }
            r4.<init>()     // Catch:{ all -> 0x00a1 }
        L_0x0038:
            boolean r5 = r2.moveToNext()     // Catch:{ all -> 0x00a1 }
            r6 = 0
            if (r5 == 0) goto L_0x0069
            com.tencent.bugly.proguard.r r5 = b((android.database.Cursor) r2)     // Catch:{ all -> 0x00a1 }
            if (r5 == 0) goto L_0x0049
            r4.add(r5)     // Catch:{ all -> 0x00a1 }
            goto L_0x0038
        L_0x0049:
            java.lang.String r5 = "_tp"
            int r5 = r2.getColumnIndex(r5)     // Catch:{ all -> 0x0061 }
            java.lang.String r5 = r2.getString(r5)     // Catch:{ all -> 0x0061 }
            java.lang.String r7 = " or _tp"
            r3.append(r7)     // Catch:{ all -> 0x0061 }
            java.lang.String r7 = " = "
            r3.append(r7)     // Catch:{ all -> 0x0061 }
            r3.append(r5)     // Catch:{ all -> 0x0061 }
            goto L_0x0038
        L_0x0061:
            java.lang.String r5 = "[Database] unknown id."
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch:{ all -> 0x00a1 }
            com.tencent.bugly.proguard.x.d(r5, r6)     // Catch:{ all -> 0x00a1 }
            goto L_0x0038
        L_0x0069:
            int r5 = r3.length()     // Catch:{ all -> 0x00a1 }
            if (r5 <= 0) goto L_0x009a
            java.lang.String r5 = " and _id"
            r3.append(r5)     // Catch:{ all -> 0x00a1 }
            java.lang.String r5 = " = "
            r3.append(r5)     // Catch:{ all -> 0x00a1 }
            r3.append(r12)     // Catch:{ all -> 0x00a1 }
            r12 = 4
            java.lang.String r12 = r10.substring(r12)     // Catch:{ all -> 0x00a1 }
            java.lang.String r3 = "t_pf"
            int r12 = r1.delete(r3, r12, r0)     // Catch:{ all -> 0x00a1 }
            java.lang.String r1 = "[Database] deleted %s illegal data %d."
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x00a1 }
            java.lang.String r5 = "t_pf"
            r3[r6] = r5     // Catch:{ all -> 0x00a1 }
            r5 = 1
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)     // Catch:{ all -> 0x00a1 }
            r3[r5] = r12     // Catch:{ all -> 0x00a1 }
            com.tencent.bugly.proguard.x.d(r1, r3)     // Catch:{ all -> 0x00a1 }
        L_0x009a:
            if (r2 == 0) goto L_0x009f
            r2.close()     // Catch:{ all -> 0x00bc }
        L_0x009f:
            monitor-exit(r11)
            return r4
        L_0x00a1:
            r12 = move-exception
            goto L_0x00a5
        L_0x00a3:
            r12 = move-exception
            r2 = r0
        L_0x00a5:
            boolean r1 = com.tencent.bugly.proguard.x.a(r12)     // Catch:{ all -> 0x00b5 }
            if (r1 != 0) goto L_0x00ae
            r12.printStackTrace()     // Catch:{ all -> 0x00b5 }
        L_0x00ae:
            if (r2 == 0) goto L_0x00b3
            r2.close()     // Catch:{ all -> 0x00bc }
        L_0x00b3:
            monitor-exit(r11)
            return r0
        L_0x00b5:
            r12 = move-exception
            if (r2 == 0) goto L_0x00bb
            r2.close()     // Catch:{ all -> 0x00bc }
        L_0x00bb:
            throw r12     // Catch:{ all -> 0x00bc }
        L_0x00bc:
            r12 = move-exception
            monitor-exit(r11)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.p.c(int):java.util.List");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x005c, code lost:
        if (r7 != null) goto L_0x005e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        java.lang.Boolean.valueOf(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x006e, code lost:
        if (r7 != null) goto L_0x005e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean a(int r5, java.lang.String r6, com.tencent.bugly.proguard.o r7) {
        /*
            r4 = this;
            monitor-enter(r4)
            r0 = 0
            com.tencent.bugly.proguard.q r1 = b     // Catch:{ all -> 0x0064 }
            android.database.sqlite.SQLiteDatabase r1 = r1.getWritableDatabase()     // Catch:{ all -> 0x0064 }
            if (r1 == 0) goto L_0x005c
            boolean r2 = com.tencent.bugly.proguard.z.a((java.lang.String) r6)     // Catch:{ all -> 0x0064 }
            if (r2 == 0) goto L_0x001f
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0064 }
            java.lang.String r2 = "_id = "
            r6.<init>(r2)     // Catch:{ all -> 0x0064 }
            r6.append(r5)     // Catch:{ all -> 0x0064 }
            java.lang.String r5 = r6.toString()     // Catch:{ all -> 0x0064 }
            goto L_0x003f
        L_0x001f:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0064 }
            java.lang.String r3 = "_id = "
            r2.<init>(r3)     // Catch:{ all -> 0x0064 }
            r2.append(r5)     // Catch:{ all -> 0x0064 }
            java.lang.String r5 = " and _tp"
            r2.append(r5)     // Catch:{ all -> 0x0064 }
            java.lang.String r5 = " = \""
            r2.append(r5)     // Catch:{ all -> 0x0064 }
            r2.append(r6)     // Catch:{ all -> 0x0064 }
            java.lang.String r5 = "\""
            r2.append(r5)     // Catch:{ all -> 0x0064 }
            java.lang.String r5 = r2.toString()     // Catch:{ all -> 0x0064 }
        L_0x003f:
            java.lang.String r6 = "t_pf"
            r2 = 0
            int r5 = r1.delete(r6, r5, r2)     // Catch:{ all -> 0x0064 }
            java.lang.String r6 = "[Database] deleted %s data %d"
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x0064 }
            java.lang.String r2 = "t_pf"
            r1[r0] = r2     // Catch:{ all -> 0x0064 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0064 }
            r3 = 1
            r1[r3] = r2     // Catch:{ all -> 0x0064 }
            com.tencent.bugly.proguard.x.c(r6, r1)     // Catch:{ all -> 0x0064 }
            if (r5 <= 0) goto L_0x005c
            r0 = 1
        L_0x005c:
            if (r7 == 0) goto L_0x0071
        L_0x005e:
            java.lang.Boolean.valueOf(r0)     // Catch:{ all -> 0x0062 }
            goto L_0x0071
        L_0x0062:
            r5 = move-exception
            goto L_0x007a
        L_0x0064:
            r5 = move-exception
            boolean r6 = com.tencent.bugly.proguard.x.a(r5)     // Catch:{ all -> 0x0073 }
            if (r6 != 0) goto L_0x006e
            r5.printStackTrace()     // Catch:{ all -> 0x0073 }
        L_0x006e:
            if (r7 == 0) goto L_0x0071
            goto L_0x005e
        L_0x0071:
            monitor-exit(r4)
            return r0
        L_0x0073:
            r5 = move-exception
            if (r7 == 0) goto L_0x0079
            java.lang.Boolean.valueOf(r0)     // Catch:{ all -> 0x0062 }
        L_0x0079:
            throw r5     // Catch:{ all -> 0x0062 }
        L_0x007a:
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.p.a(int, java.lang.String, com.tencent.bugly.proguard.o):boolean");
    }

    private static ContentValues d(r rVar) {
        if (rVar != null && !z.a(rVar.f)) {
            try {
                ContentValues contentValues = new ContentValues();
                if (rVar.a > 0) {
                    contentValues.put("_id", Long.valueOf(rVar.a));
                }
                contentValues.put("_tp", rVar.f);
                contentValues.put("_tm", Long.valueOf(rVar.e));
                if (rVar.g != null) {
                    contentValues.put("_dt", rVar.g);
                }
                return contentValues;
            } catch (Throwable th) {
                if (!x.a(th)) {
                    th.printStackTrace();
                }
            }
        }
        return null;
    }

    private static r b(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            r rVar = new r();
            rVar.a = cursor.getLong(cursor.getColumnIndex("_id"));
            rVar.e = cursor.getLong(cursor.getColumnIndex("_tm"));
            rVar.f = cursor.getString(cursor.getColumnIndex("_tp"));
            rVar.g = cursor.getBlob(cursor.getColumnIndex("_dt"));
            return rVar;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* compiled from: BUGLY */
    class a extends Thread {
        private int a;
        private o b;
        private String c;
        private ContentValues d;
        private boolean e;
        private String[] f;
        private String g;
        private String[] h;
        private String i;
        private String j;
        private String k;
        private String l;
        private String m;
        private String[] n;
        private int o;
        private String p;
        private byte[] q;

        public a(int i2, o oVar) {
            this.a = i2;
            this.b = oVar;
        }

        public final void a(boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
            this.e = z;
            this.c = str;
            this.f = strArr;
            this.g = str2;
            this.h = strArr2;
            this.i = str3;
            this.j = str4;
            this.k = str5;
            this.l = str6;
        }

        public final void a(int i2, String str, byte[] bArr) {
            this.o = i2;
            this.p = str;
            this.q = bArr;
        }

        public final void run() {
            switch (this.a) {
                case 1:
                    long unused = p.this.a(this.c, this.d, this.b);
                    return;
                case 2:
                    int unused2 = p.this.a(this.c, this.m, this.n, this.b);
                    return;
                case 3:
                    Cursor a2 = p.this.a(this.e, this.c, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.b);
                    if (a2 != null) {
                        a2.close();
                        return;
                    }
                    return;
                case 4:
                    boolean unused3 = p.this.a(this.o, this.p, this.q, this.b);
                    return;
                case 5:
                    Map unused4 = p.this.a(this.o, this.b);
                    return;
                case 6:
                    boolean unused5 = p.this.a(this.o, this.p, this.b);
                    return;
                default:
                    return;
            }
        }
    }
}
