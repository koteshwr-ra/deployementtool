package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Process;
import android.support.v4.media.session.PlaybackStateCompat;
import com.tencent.bugly.b;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/* compiled from: BUGLY */
public final class u {
    private static u a;
    private final p b;
    private final Context c;
    private Map<Integer, Long> d = new HashMap();
    private long e;
    private long f;
    private LinkedBlockingQueue<Runnable> g = new LinkedBlockingQueue<>();
    private LinkedBlockingQueue<Runnable> h = new LinkedBlockingQueue<>();
    /* access modifiers changed from: private */
    public final Object i = new Object();
    private int j = 0;

    static /* synthetic */ int b(u uVar) {
        int i2 = uVar.j - 1;
        uVar.j = i2;
        return i2;
    }

    private u(Context context) {
        this.c = context;
        this.b = p.a();
    }

    public static synchronized u a(Context context) {
        u uVar;
        synchronized (u.class) {
            if (a == null) {
                a = new u(context);
            }
            uVar = a;
        }
        return uVar;
    }

    public static synchronized u a() {
        u uVar;
        synchronized (u.class) {
            uVar = a;
        }
        return uVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002d  */
    /* JADX WARNING: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(int r13, com.tencent.bugly.proguard.am r14, java.lang.String r15, java.lang.String r16, com.tencent.bugly.proguard.t r17, long r18, boolean r20) {
        /*
            r12 = this;
            r0 = r14
            int r3 = r0.g
            byte[] r4 = com.tencent.bugly.proguard.a.a((java.lang.Object) r14)
            com.tencent.bugly.proguard.v r10 = new com.tencent.bugly.proguard.v     // Catch:{ all -> 0x0025 }
            r11 = r12
            android.content.Context r1 = r11.c     // Catch:{ all -> 0x0023 }
            r8 = 1
            r0 = r10
            r2 = r13
            r5 = r15
            r6 = r16
            r7 = r17
            r9 = r20
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9)     // Catch:{ all -> 0x0023 }
            r7 = 1
            r8 = 1
            r5 = r12
            r6 = r10
            r9 = r18
            r5.a(r6, r7, r8, r9)     // Catch:{ all -> 0x0023 }
            return
        L_0x0023:
            r0 = move-exception
            goto L_0x0027
        L_0x0025:
            r0 = move-exception
            r11 = r12
        L_0x0027:
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x0030
            r0.printStackTrace()
        L_0x0030:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.u.a(int, com.tencent.bugly.proguard.am, java.lang.String, java.lang.String, com.tencent.bugly.proguard.t, long, boolean):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(int r15, com.tencent.bugly.proguard.am r16, java.lang.String r17, java.lang.String r18, com.tencent.bugly.proguard.t r19, boolean r20) {
        /*
            r14 = this;
            r0 = r16
            int r3 = r0.g
            byte[] r4 = com.tencent.bugly.proguard.a.a((java.lang.Object) r16)
            com.tencent.bugly.proguard.v r12 = new com.tencent.bugly.proguard.v     // Catch:{ all -> 0x0029 }
            r13 = r14
            android.content.Context r1 = r13.c     // Catch:{ all -> 0x0027 }
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r0 = r12
            r2 = r15
            r5 = r17
            r6 = r18
            r7 = r19
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)     // Catch:{ all -> 0x0027 }
            r8 = 0
            r9 = 0
            r5 = r14
            r6 = r12
            r7 = r20
            r5.a(r6, r7, r8, r9)     // Catch:{ all -> 0x0027 }
            return
        L_0x0027:
            r0 = move-exception
            goto L_0x002b
        L_0x0029:
            r0 = move-exception
            r13 = r14
        L_0x002b:
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x0034
            r0.printStackTrace()
        L_0x0034:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.u.a(int, com.tencent.bugly.proguard.am, java.lang.String, java.lang.String, com.tencent.bugly.proguard.t, boolean):void");
    }

    public final long a(boolean z) {
        long j2;
        long b2 = z.b();
        int i2 = z ? 5 : 3;
        List<r> a2 = this.b.a(i2);
        if (a2 == null || a2.size() <= 0) {
            j2 = z ? this.f : this.e;
        } else {
            j2 = 0;
            try {
                r rVar = a2.get(0);
                if (rVar.e >= b2) {
                    j2 = z.b(rVar.g);
                    if (i2 == 3) {
                        this.e = j2;
                    } else {
                        this.f = j2;
                    }
                    a2.remove(rVar);
                }
            } catch (Throwable th) {
                x.a(th);
            }
            if (a2.size() > 0) {
                this.b.a(a2);
            }
        }
        x.c("[UploadManager] Local network consume: %d KB", Long.valueOf(j2 / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID));
        return j2;
    }

    /* access modifiers changed from: protected */
    public final synchronized void a(long j2, boolean z) {
        int i2 = z ? 5 : 3;
        r rVar = new r();
        rVar.b = i2;
        rVar.e = z.b();
        rVar.c = "";
        rVar.d = "";
        rVar.g = z.c(j2);
        this.b.b(i2);
        this.b.a(rVar);
        if (z) {
            this.f = j2;
        } else {
            this.e = j2;
        }
        x.c("[UploadManager] Network total consume: %d KB", Long.valueOf(j2 / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID));
    }

    public final synchronized void a(int i2, long j2) {
        if (i2 >= 0) {
            this.d.put(Integer.valueOf(i2), Long.valueOf(j2));
            r rVar = new r();
            rVar.b = i2;
            rVar.e = j2;
            rVar.c = "";
            rVar.d = "";
            rVar.g = new byte[0];
            this.b.b(i2);
            this.b.a(rVar);
            x.c("[UploadManager] Uploading(ID:%d) time: %s", Integer.valueOf(i2), z.a(j2));
            return;
        }
        x.e("[UploadManager] Unknown uploading ID: %d", Integer.valueOf(i2));
    }

    public final synchronized long a(int i2) {
        long j2;
        j2 = 0;
        if (i2 >= 0) {
            Long l = this.d.get(Integer.valueOf(i2));
            if (l != null) {
                return l.longValue();
            }
            List<r> a2 = this.b.a(i2);
            if (a2 != null && a2.size() > 0) {
                if (a2.size() > 1) {
                    for (r next : a2) {
                        if (next.e > j2) {
                            j2 = next.e;
                        }
                    }
                    this.b.b(i2);
                } else {
                    try {
                        j2 = a2.get(0).e;
                    } catch (Throwable th) {
                        x.a(th);
                    }
                }
            }
        } else {
            x.e("[UploadManager] Unknown upload ID: %d", Integer.valueOf(i2));
        }
        return j2;
    }

    public final boolean b(int i2) {
        if (b.c) {
            x.c("Uploading frequency will not be checked if SDK is in debug mode.", new Object[0]);
            return true;
        }
        long currentTimeMillis = System.currentTimeMillis() - a(i2);
        x.c("[UploadManager] Time interval is %d seconds since last uploading(ID: %d).", Long.valueOf(currentTimeMillis / 1000), Integer.valueOf(i2));
        if (currentTimeMillis >= 30000) {
            return true;
        }
        x.a("[UploadManager] Data only be uploaded once in %d seconds.", 30L);
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00a1, code lost:
        if (r3 <= 0) goto L_0x00c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00a3, code lost:
        com.tencent.bugly.proguard.x.c("[UploadManager] Execute urgent upload tasks of queue which has %d tasks (pid=%d | tid=%d)", java.lang.Integer.valueOf(r3), java.lang.Integer.valueOf(android.os.Process.myPid()), java.lang.Integer.valueOf(android.os.Process.myTid()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00c4, code lost:
        r6 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00c5, code lost:
        if (r6 >= r3) goto L_0x0113;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00c7, code lost:
        r9 = (java.lang.Runnable) r0.poll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00cd, code lost:
        if (r9 == null) goto L_0x0113;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00cf, code lost:
        r10 = r13.i;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00d1, code lost:
        monitor-enter(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00d4, code lost:
        if (r13.j < 2) goto L_0x00dd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00d6, code lost:
        if (r14 == null) goto L_0x00dd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00d8, code lost:
        r14.a(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00db, code lost:
        monitor-exit(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00dd, code lost:
        monitor-exit(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00de, code lost:
        com.tencent.bugly.proguard.x.a("[UploadManager] Create and start a new thread to execute a upload task: %s", "BUGLY_ASYNC_UPLOAD");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00f4, code lost:
        if (com.tencent.bugly.proguard.z.a((java.lang.Runnable) new com.tencent.bugly.proguard.u.AnonymousClass1(r13), "BUGLY_ASYNC_UPLOAD") == null) goto L_0x0103;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00f6, code lost:
        r9 = r13.i;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00f8, code lost:
        monitor-enter(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:?, code lost:
        r13.j++;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00fe, code lost:
        monitor-exit(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0103, code lost:
        com.tencent.bugly.proguard.x.d("[UploadManager] Failed to start a thread to execute asynchronous upload task, will try again next time.", new java.lang.Object[0]);
        a(r9, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x010d, code lost:
        r6 = r6 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0113, code lost:
        if (r5 <= 0) goto L_0x0136;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0115, code lost:
        com.tencent.bugly.proguard.x.c("[UploadManager] Execute upload tasks of queue which has %d tasks (pid=%d | tid=%d)", java.lang.Integer.valueOf(r5), java.lang.Integer.valueOf(android.os.Process.myPid()), java.lang.Integer.valueOf(android.os.Process.myTid()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0136, code lost:
        if (r14 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0138, code lost:
        r14.a(new com.tencent.bugly.proguard.u.AnonymousClass2(r13));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void c(int r14) {
        /*
            r13 = this;
            com.tencent.bugly.proguard.w r14 = com.tencent.bugly.proguard.w.a()
            java.util.concurrent.LinkedBlockingQueue r0 = new java.util.concurrent.LinkedBlockingQueue
            r0.<init>()
            java.util.concurrent.LinkedBlockingQueue r1 = new java.util.concurrent.LinkedBlockingQueue
            r1.<init>()
            java.lang.Object r2 = r13.i
            monitor-enter(r2)
            java.lang.String r3 = "[UploadManager] Try to poll all upload task need and put them into temp queue (pid=%d | tid=%d)"
            r4 = 2
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch:{ all -> 0x0141 }
            int r6 = android.os.Process.myPid()     // Catch:{ all -> 0x0141 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x0141 }
            r7 = 0
            r5[r7] = r6     // Catch:{ all -> 0x0141 }
            int r6 = android.os.Process.myTid()     // Catch:{ all -> 0x0141 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x0141 }
            r8 = 1
            r5[r8] = r6     // Catch:{ all -> 0x0141 }
            com.tencent.bugly.proguard.x.c(r3, r5)     // Catch:{ all -> 0x0141 }
            java.util.concurrent.LinkedBlockingQueue<java.lang.Runnable> r3 = r13.g     // Catch:{ all -> 0x0141 }
            int r3 = r3.size()     // Catch:{ all -> 0x0141 }
            java.util.concurrent.LinkedBlockingQueue<java.lang.Runnable> r5 = r13.h     // Catch:{ all -> 0x0141 }
            int r5 = r5.size()     // Catch:{ all -> 0x0141 }
            if (r3 != 0) goto L_0x0048
            if (r5 != 0) goto L_0x0048
            java.lang.String r14 = "[UploadManager] There is no upload task in queue."
            java.lang.Object[] r0 = new java.lang.Object[r7]     // Catch:{ all -> 0x0141 }
            com.tencent.bugly.proguard.x.c(r14, r0)     // Catch:{ all -> 0x0141 }
            monitor-exit(r2)     // Catch:{ all -> 0x0141 }
            return
        L_0x0048:
            if (r14 == 0) goto L_0x0050
            boolean r6 = r14.c()     // Catch:{ all -> 0x0141 }
            if (r6 != 0) goto L_0x0051
        L_0x0050:
            r5 = 0
        L_0x0051:
            r6 = 0
        L_0x0052:
            if (r6 >= r3) goto L_0x0078
            java.util.concurrent.LinkedBlockingQueue<java.lang.Runnable> r9 = r13.g     // Catch:{ all -> 0x0141 }
            java.lang.Object r9 = r9.peek()     // Catch:{ all -> 0x0141 }
            java.lang.Runnable r9 = (java.lang.Runnable) r9     // Catch:{ all -> 0x0141 }
            if (r9 == 0) goto L_0x0078
            r0.put(r9)     // Catch:{ all -> 0x0067 }
            java.util.concurrent.LinkedBlockingQueue<java.lang.Runnable> r9 = r13.g     // Catch:{ all -> 0x0067 }
            r9.poll()     // Catch:{ all -> 0x0067 }
            goto L_0x0075
        L_0x0067:
            r9 = move-exception
            java.lang.String r10 = "[UploadManager] Failed to add upload task to temp urgent queue: %s"
            java.lang.Object[] r11 = new java.lang.Object[r8]     // Catch:{ all -> 0x0141 }
            java.lang.String r9 = r9.getMessage()     // Catch:{ all -> 0x0141 }
            r11[r7] = r9     // Catch:{ all -> 0x0141 }
            com.tencent.bugly.proguard.x.e(r10, r11)     // Catch:{ all -> 0x0141 }
        L_0x0075:
            int r6 = r6 + 1
            goto L_0x0052
        L_0x0078:
            r6 = 0
        L_0x0079:
            if (r6 >= r5) goto L_0x009f
            java.util.concurrent.LinkedBlockingQueue<java.lang.Runnable> r9 = r13.h     // Catch:{ all -> 0x0141 }
            java.lang.Object r9 = r9.peek()     // Catch:{ all -> 0x0141 }
            java.lang.Runnable r9 = (java.lang.Runnable) r9     // Catch:{ all -> 0x0141 }
            if (r9 == 0) goto L_0x009f
            r1.put(r9)     // Catch:{ all -> 0x008e }
            java.util.concurrent.LinkedBlockingQueue<java.lang.Runnable> r9 = r13.h     // Catch:{ all -> 0x008e }
            r9.poll()     // Catch:{ all -> 0x008e }
            goto L_0x009c
        L_0x008e:
            r9 = move-exception
            java.lang.String r10 = "[UploadManager] Failed to add upload task to temp urgent queue: %s"
            java.lang.Object[] r11 = new java.lang.Object[r8]     // Catch:{ all -> 0x0141 }
            java.lang.String r9 = r9.getMessage()     // Catch:{ all -> 0x0141 }
            r11[r7] = r9     // Catch:{ all -> 0x0141 }
            com.tencent.bugly.proguard.x.e(r10, r11)     // Catch:{ all -> 0x0141 }
        L_0x009c:
            int r6 = r6 + 1
            goto L_0x0079
        L_0x009f:
            monitor-exit(r2)     // Catch:{ all -> 0x0141 }
            r2 = 3
            if (r3 <= 0) goto L_0x00c4
            java.lang.String r6 = "[UploadManager] Execute urgent upload tasks of queue which has %d tasks (pid=%d | tid=%d)"
            java.lang.Object[] r9 = new java.lang.Object[r2]
            java.lang.Integer r10 = java.lang.Integer.valueOf(r3)
            r9[r7] = r10
            int r10 = android.os.Process.myPid()
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            r9[r8] = r10
            int r10 = android.os.Process.myTid()
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            r9[r4] = r10
            com.tencent.bugly.proguard.x.c(r6, r9)
        L_0x00c4:
            r6 = 0
        L_0x00c5:
            if (r6 >= r3) goto L_0x0113
            java.lang.Object r9 = r0.poll()
            java.lang.Runnable r9 = (java.lang.Runnable) r9
            if (r9 == 0) goto L_0x0113
            java.lang.Object r10 = r13.i
            monitor-enter(r10)
            int r11 = r13.j     // Catch:{ all -> 0x0110 }
            if (r11 < r4) goto L_0x00dd
            if (r14 == 0) goto L_0x00dd
            r14.a(r9)     // Catch:{ all -> 0x0110 }
            monitor-exit(r10)     // Catch:{ all -> 0x0110 }
            goto L_0x010d
        L_0x00dd:
            monitor-exit(r10)
            java.lang.String r10 = "[UploadManager] Create and start a new thread to execute a upload task: %s"
            java.lang.Object[] r11 = new java.lang.Object[r8]
            java.lang.String r12 = "BUGLY_ASYNC_UPLOAD"
            r11[r7] = r12
            com.tencent.bugly.proguard.x.a(r10, r11)
            com.tencent.bugly.proguard.u$1 r10 = new com.tencent.bugly.proguard.u$1
            r10.<init>(r9)
            java.lang.String r11 = "BUGLY_ASYNC_UPLOAD"
            java.lang.Thread r10 = com.tencent.bugly.proguard.z.a((java.lang.Runnable) r10, (java.lang.String) r11)
            if (r10 == 0) goto L_0x0103
            java.lang.Object r9 = r13.i
            monitor-enter(r9)
            int r10 = r13.j     // Catch:{ all -> 0x0100 }
            int r10 = r10 + r8
            r13.j = r10     // Catch:{ all -> 0x0100 }
            monitor-exit(r9)     // Catch:{ all -> 0x0100 }
            goto L_0x010d
        L_0x0100:
            r14 = move-exception
            monitor-exit(r9)
            throw r14
        L_0x0103:
            java.lang.String r10 = "[UploadManager] Failed to start a thread to execute asynchronous upload task, will try again next time."
            java.lang.Object[] r11 = new java.lang.Object[r7]
            com.tencent.bugly.proguard.x.d(r10, r11)
            r13.a((java.lang.Runnable) r9, (boolean) r8)
        L_0x010d:
            int r6 = r6 + 1
            goto L_0x00c5
        L_0x0110:
            r14 = move-exception
            monitor-exit(r10)
            throw r14
        L_0x0113:
            if (r5 <= 0) goto L_0x0136
            java.lang.String r0 = "[UploadManager] Execute upload tasks of queue which has %d tasks (pid=%d | tid=%d)"
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.Integer r3 = java.lang.Integer.valueOf(r5)
            r2[r7] = r3
            int r3 = android.os.Process.myPid()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r2[r8] = r3
            int r3 = android.os.Process.myTid()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r2[r4] = r3
            com.tencent.bugly.proguard.x.c(r0, r2)
        L_0x0136:
            if (r14 == 0) goto L_0x0140
            com.tencent.bugly.proguard.u$2 r0 = new com.tencent.bugly.proguard.u$2
            r0.<init>(r13, r5, r1)
            r14.a(r0)
        L_0x0140:
            return
        L_0x0141:
            r14 = move-exception
            monitor-exit(r2)
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.u.c(int):void");
    }

    private boolean a(Runnable runnable, boolean z) {
        if (runnable == null) {
            x.a("[UploadManager] Upload task should not be null", new Object[0]);
            return false;
        }
        try {
            x.c("[UploadManager] Add upload task to queue (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            synchronized (this.i) {
                if (z) {
                    this.g.put(runnable);
                } else {
                    this.h.put(runnable);
                }
            }
            return true;
        } catch (Throwable th) {
            x.e("[UploadManager] Failed to add upload task to queue: %s", th.getMessage());
            return false;
        }
    }

    private void a(Runnable runnable, boolean z, boolean z2, long j2) {
        if (runnable == null) {
            x.d("[UploadManager] Upload task should not be null", new Object[0]);
        }
        x.c("[UploadManager] Add upload task (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        if (!z2) {
            a(runnable, z);
            c(0);
        } else if (runnable == null) {
            x.d("[UploadManager] Upload task should not be null", new Object[0]);
        } else {
            x.c("[UploadManager] Execute synchronized upload task (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            Thread a2 = z.a(runnable, "BUGLY_SYNC_UPLOAD");
            if (a2 == null) {
                x.e("[UploadManager] Failed to start a thread to execute synchronized upload task, add it to queue.", new Object[0]);
                a(runnable, true);
                return;
            }
            try {
                a2.join(j2);
            } catch (Throwable th) {
                x.e("[UploadManager] Failed to join upload synchronized task with message: %s. Add it to queue.", th.getMessage());
                a(runnable, true);
                c(0);
            }
        }
    }
}
