package com.tencent.bugly.crashreport.biz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Parcelable;
import androidx.lifecycle.CoroutineLiveDataKt;
import com.tencent.bugly.proguard.o;
import com.tencent.bugly.proguard.p;
import com.tencent.bugly.proguard.w;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import java.util.List;
import org.apache.commons.lang3.time.DateUtils;

/* compiled from: BUGLY */
public final class a {
    private Context a;
    /* access modifiers changed from: private */
    public long b;
    private int c;
    private boolean d = true;

    static /* synthetic */ void a(a aVar, UserInfoBean userInfoBean, boolean z) {
        List<UserInfoBean> a2;
        if (userInfoBean == null) {
            return;
        }
        if (z || userInfoBean.b == 1 || (a2 = aVar.a(com.tencent.bugly.crashreport.common.info.a.a(aVar.a).d)) == null || a2.size() < 20) {
            long a3 = p.a().a("t_ui", a(userInfoBean), (o) null, true);
            if (a3 >= 0) {
                x.c("[Database] insert %s success with ID: %d", "t_ui", Long.valueOf(a3));
                userInfoBean.a = a3;
                return;
            }
            return;
        }
        x.a("[UserInfo] There are too many user info in local: %d", Integer.valueOf(a2.size()));
    }

    public a(Context context, boolean z) {
        this.a = context;
        this.d = z;
    }

    public final void a(int i, boolean z, long j) {
        com.tencent.bugly.crashreport.common.strategy.a a2 = com.tencent.bugly.crashreport.common.strategy.a.a();
        int i2 = 0;
        if (a2 == null || a2.c().f || i == 1 || i == 3) {
            if (i == 1 || i == 3) {
                this.c++;
            }
            com.tencent.bugly.crashreport.common.info.a a3 = com.tencent.bugly.crashreport.common.info.a.a(this.a);
            UserInfoBean userInfoBean = new UserInfoBean();
            userInfoBean.b = i;
            userInfoBean.c = a3.d;
            userInfoBean.d = a3.g();
            userInfoBean.e = System.currentTimeMillis();
            userInfoBean.f = -1;
            userInfoBean.n = a3.k;
            if (i == 1) {
                i2 = 1;
            }
            userInfoBean.o = i2;
            userInfoBean.l = a3.a();
            userInfoBean.m = a3.q;
            userInfoBean.g = a3.r;
            userInfoBean.h = a3.s;
            userInfoBean.i = a3.t;
            userInfoBean.k = a3.u;
            userInfoBean.r = a3.x();
            userInfoBean.s = a3.C();
            userInfoBean.p = a3.D();
            userInfoBean.q = a3.E();
            w.a().a(new C0000a(userInfoBean, z), 0);
            return;
        }
        x.e("UserInfo is disable", new Object[0]);
    }

    public final void a() {
        this.b = z.b() + DateUtils.MILLIS_PER_DAY;
        w.a().a(new b(), (this.b - System.currentTimeMillis()) + CoroutineLiveDataKt.DEFAULT_TIMEOUT);
    }

    /* renamed from: com.tencent.bugly.crashreport.biz.a$a  reason: collision with other inner class name */
    /* compiled from: BUGLY */
    class C0000a implements Runnable {
        private boolean a;
        private UserInfoBean b;

        public C0000a(UserInfoBean userInfoBean, boolean z) {
            this.b = userInfoBean;
            this.a = z;
        }

        public final void run() {
            com.tencent.bugly.crashreport.common.info.a b2;
            try {
                if (this.b != null) {
                    UserInfoBean userInfoBean = this.b;
                    if (!(userInfoBean == null || (b2 = com.tencent.bugly.crashreport.common.info.a.b()) == null)) {
                        userInfoBean.j = b2.e();
                    }
                    x.c("[UserInfo] Record user info.", new Object[0]);
                    a.a(a.this, this.b, false);
                }
                if (this.a) {
                    a aVar = a.this;
                    w a2 = w.a();
                    if (a2 != null) {
                        a2.a(new Runnable() {
                            public final void run() {
                                try {
                                    a.this.c();
                                } catch (Throwable th) {
                                    x.a(th);
                                }
                            }
                        });
                    }
                }
            } catch (Throwable th) {
                if (!x.a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00f7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void c() {
        /*
            r13 = this;
            monitor-enter(r13)
            boolean r0 = r13.d     // Catch:{ all -> 0x0173 }
            if (r0 != 0) goto L_0x0007
            monitor-exit(r13)
            return
        L_0x0007:
            com.tencent.bugly.proguard.u r0 = com.tencent.bugly.proguard.u.a()     // Catch:{ all -> 0x0173 }
            if (r0 != 0) goto L_0x000f
            monitor-exit(r13)
            return
        L_0x000f:
            com.tencent.bugly.crashreport.common.strategy.a r1 = com.tencent.bugly.crashreport.common.strategy.a.a()     // Catch:{ all -> 0x0173 }
            if (r1 != 0) goto L_0x0017
            monitor-exit(r13)
            return
        L_0x0017:
            boolean r1 = r1.b()     // Catch:{ all -> 0x0173 }
            if (r1 == 0) goto L_0x0027
            r1 = 1001(0x3e9, float:1.403E-42)
            boolean r0 = r0.b((int) r1)     // Catch:{ all -> 0x0173 }
            if (r0 != 0) goto L_0x0027
            monitor-exit(r13)
            return
        L_0x0027:
            android.content.Context r0 = r13.a     // Catch:{ all -> 0x0173 }
            com.tencent.bugly.crashreport.common.info.a r0 = com.tencent.bugly.crashreport.common.info.a.a((android.content.Context) r0)     // Catch:{ all -> 0x0173 }
            java.lang.String r0 = r0.d     // Catch:{ all -> 0x0173 }
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x0173 }
            r1.<init>()     // Catch:{ all -> 0x0173 }
            java.util.List r0 = r13.a((java.lang.String) r0)     // Catch:{ all -> 0x0173 }
            r2 = 0
            r3 = 1
            if (r0 == 0) goto L_0x00e6
            int r4 = r0.size()     // Catch:{ all -> 0x0173 }
            int r4 = r4 + -20
            if (r4 <= 0) goto L_0x008b
            r5 = 0
        L_0x0045:
            int r6 = r0.size()     // Catch:{ all -> 0x0173 }
            int r6 = r6 - r3
            if (r5 >= r6) goto L_0x007e
            int r6 = r5 + 1
            r7 = r6
        L_0x004f:
            int r8 = r0.size()     // Catch:{ all -> 0x0173 }
            if (r7 >= r8) goto L_0x007c
            java.lang.Object r8 = r0.get(r5)     // Catch:{ all -> 0x0173 }
            com.tencent.bugly.crashreport.biz.UserInfoBean r8 = (com.tencent.bugly.crashreport.biz.UserInfoBean) r8     // Catch:{ all -> 0x0173 }
            long r8 = r8.e     // Catch:{ all -> 0x0173 }
            java.lang.Object r10 = r0.get(r7)     // Catch:{ all -> 0x0173 }
            com.tencent.bugly.crashreport.biz.UserInfoBean r10 = (com.tencent.bugly.crashreport.biz.UserInfoBean) r10     // Catch:{ all -> 0x0173 }
            long r10 = r10.e     // Catch:{ all -> 0x0173 }
            int r12 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r12 <= 0) goto L_0x0079
            java.lang.Object r8 = r0.get(r5)     // Catch:{ all -> 0x0173 }
            com.tencent.bugly.crashreport.biz.UserInfoBean r8 = (com.tencent.bugly.crashreport.biz.UserInfoBean) r8     // Catch:{ all -> 0x0173 }
            java.lang.Object r9 = r0.get(r7)     // Catch:{ all -> 0x0173 }
            r0.set(r5, r9)     // Catch:{ all -> 0x0173 }
            r0.set(r7, r8)     // Catch:{ all -> 0x0173 }
        L_0x0079:
            int r7 = r7 + 1
            goto L_0x004f
        L_0x007c:
            r5 = r6
            goto L_0x0045
        L_0x007e:
            r5 = 0
        L_0x007f:
            if (r5 >= r4) goto L_0x008b
            java.lang.Object r6 = r0.get(r5)     // Catch:{ all -> 0x0173 }
            r1.add(r6)     // Catch:{ all -> 0x0173 }
            int r5 = r5 + 1
            goto L_0x007f
        L_0x008b:
            java.util.Iterator r4 = r0.iterator()     // Catch:{ all -> 0x0173 }
            r5 = 0
        L_0x0090:
            boolean r6 = r4.hasNext()     // Catch:{ all -> 0x0173 }
            if (r6 == 0) goto L_0x00d3
            java.lang.Object r6 = r4.next()     // Catch:{ all -> 0x0173 }
            com.tencent.bugly.crashreport.biz.UserInfoBean r6 = (com.tencent.bugly.crashreport.biz.UserInfoBean) r6     // Catch:{ all -> 0x0173 }
            long r7 = r6.f     // Catch:{ all -> 0x0173 }
            r9 = -1
            int r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r11 == 0) goto L_0x00b4
            r4.remove()     // Catch:{ all -> 0x0173 }
            long r7 = r6.e     // Catch:{ all -> 0x0173 }
            long r9 = com.tencent.bugly.proguard.z.b()     // Catch:{ all -> 0x0173 }
            int r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r11 >= 0) goto L_0x00b4
            r1.add(r6)     // Catch:{ all -> 0x0173 }
        L_0x00b4:
            long r7 = r6.e     // Catch:{ all -> 0x0173 }
            long r9 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0173 }
            r11 = 600000(0x927c0, double:2.964394E-318)
            long r9 = r9 - r11
            int r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r11 <= 0) goto L_0x0090
            int r7 = r6.b     // Catch:{ all -> 0x0173 }
            if (r7 == r3) goto L_0x00d0
            int r7 = r6.b     // Catch:{ all -> 0x0173 }
            r8 = 4
            if (r7 == r8) goto L_0x00d0
            int r6 = r6.b     // Catch:{ all -> 0x0173 }
            r7 = 3
            if (r6 != r7) goto L_0x0090
        L_0x00d0:
            int r5 = r5 + 1
            goto L_0x0090
        L_0x00d3:
            r4 = 15
            if (r5 <= r4) goto L_0x00eb
            java.lang.String r4 = "[UserInfo] Upload user info too many times in 10 min: %d"
            java.lang.Object[] r6 = new java.lang.Object[r3]     // Catch:{ all -> 0x0173 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0173 }
            r6[r2] = r5     // Catch:{ all -> 0x0173 }
            com.tencent.bugly.proguard.x.d(r4, r6)     // Catch:{ all -> 0x0173 }
            r4 = 0
            goto L_0x00ec
        L_0x00e6:
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x0173 }
            r0.<init>()     // Catch:{ all -> 0x0173 }
        L_0x00eb:
            r4 = 1
        L_0x00ec:
            int r5 = r1.size()     // Catch:{ all -> 0x0173 }
            if (r5 <= 0) goto L_0x00f5
            a((java.util.List<com.tencent.bugly.crashreport.biz.UserInfoBean>) r1)     // Catch:{ all -> 0x0173 }
        L_0x00f5:
            if (r4 == 0) goto L_0x016a
            int r1 = r0.size()     // Catch:{ all -> 0x0173 }
            if (r1 != 0) goto L_0x00fe
            goto L_0x016a
        L_0x00fe:
            java.lang.String r1 = "[UserInfo] Upload user info(size: %d)"
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ all -> 0x0173 }
            int r5 = r0.size()     // Catch:{ all -> 0x0173 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0173 }
            r4[r2] = r5     // Catch:{ all -> 0x0173 }
            com.tencent.bugly.proguard.x.c(r1, r4)     // Catch:{ all -> 0x0173 }
            int r1 = r13.c     // Catch:{ all -> 0x0173 }
            if (r1 != r3) goto L_0x0115
            r1 = 1
            goto L_0x0116
        L_0x0115:
            r1 = 2
        L_0x0116:
            com.tencent.bugly.proguard.ar r1 = com.tencent.bugly.proguard.a.a((java.util.List<com.tencent.bugly.crashreport.biz.UserInfoBean>) r0, (int) r1)     // Catch:{ all -> 0x0173 }
            if (r1 != 0) goto L_0x0125
            java.lang.String r0 = "[UserInfo] Failed to create UserInfoPackage."
            java.lang.Object[] r1 = new java.lang.Object[r2]     // Catch:{ all -> 0x0173 }
            com.tencent.bugly.proguard.x.d(r0, r1)     // Catch:{ all -> 0x0173 }
            monitor-exit(r13)
            return
        L_0x0125:
            byte[] r1 = com.tencent.bugly.proguard.a.a((com.tencent.bugly.proguard.k) r1)     // Catch:{ all -> 0x0173 }
            if (r1 != 0) goto L_0x0134
            java.lang.String r0 = "[UserInfo] Failed to encode data."
            java.lang.Object[] r1 = new java.lang.Object[r2]     // Catch:{ all -> 0x0173 }
            com.tencent.bugly.proguard.x.d(r0, r1)     // Catch:{ all -> 0x0173 }
            monitor-exit(r13)
            return
        L_0x0134:
            android.content.Context r4 = r13.a     // Catch:{ all -> 0x0173 }
            r5 = 840(0x348, float:1.177E-42)
            com.tencent.bugly.proguard.am r8 = com.tencent.bugly.proguard.a.a(r4, r5, r1)     // Catch:{ all -> 0x0173 }
            if (r8 != 0) goto L_0x0147
            java.lang.String r0 = "[UserInfo] Request package is null."
            java.lang.Object[] r1 = new java.lang.Object[r2]     // Catch:{ all -> 0x0173 }
            com.tencent.bugly.proguard.x.d(r0, r1)     // Catch:{ all -> 0x0173 }
            monitor-exit(r13)
            return
        L_0x0147:
            com.tencent.bugly.crashreport.biz.a$1 r11 = new com.tencent.bugly.crashreport.biz.a$1     // Catch:{ all -> 0x0173 }
            r11.<init>(r0)     // Catch:{ all -> 0x0173 }
            com.tencent.bugly.crashreport.common.strategy.a r0 = com.tencent.bugly.crashreport.common.strategy.a.a()     // Catch:{ all -> 0x0173 }
            com.tencent.bugly.crashreport.common.strategy.StrategyBean r0 = r0.c()     // Catch:{ all -> 0x0173 }
            java.lang.String r9 = r0.p     // Catch:{ all -> 0x0173 }
            java.lang.String r10 = com.tencent.bugly.crashreport.common.strategy.StrategyBean.a     // Catch:{ all -> 0x0173 }
            com.tencent.bugly.proguard.u r6 = com.tencent.bugly.proguard.u.a()     // Catch:{ all -> 0x0173 }
            r7 = 1001(0x3e9, float:1.403E-42)
            int r0 = r13.c     // Catch:{ all -> 0x0173 }
            if (r0 != r3) goto L_0x0164
            r12 = 1
            goto L_0x0165
        L_0x0164:
            r12 = 0
        L_0x0165:
            r6.a(r7, r8, r9, r10, r11, r12)     // Catch:{ all -> 0x0173 }
            monitor-exit(r13)
            return
        L_0x016a:
            java.lang.String r0 = "[UserInfo] There is no user info in local database."
            java.lang.Object[] r1 = new java.lang.Object[r2]     // Catch:{ all -> 0x0173 }
            com.tencent.bugly.proguard.x.c(r0, r1)     // Catch:{ all -> 0x0173 }
            monitor-exit(r13)
            return
        L_0x0173:
            r0 = move-exception
            monitor-exit(r13)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.biz.a.c():void");
    }

    public final void b() {
        w a2 = w.a();
        if (a2 != null) {
            a2.a(new Runnable() {
                public final void run() {
                    try {
                        a.this.c();
                    } catch (Throwable th) {
                        x.a(th);
                    }
                }
            });
        }
    }

    /* compiled from: BUGLY */
    class b implements Runnable {
        b() {
        }

        public final void run() {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis < a.this.b) {
                w.a().a(new b(), (a.this.b - currentTimeMillis) + CoroutineLiveDataKt.DEFAULT_TIMEOUT);
                return;
            }
            a.this.a(3, false, 0);
            a.this.a();
        }
    }

    /* compiled from: BUGLY */
    class c implements Runnable {
        private long a = 21600000;

        public c(long j) {
            this.a = j;
        }

        public final void run() {
            a aVar = a.this;
            w a2 = w.a();
            if (a2 != null) {
                a2.a(new Runnable() {
                    public final void run() {
                        try {
                            a.this.c();
                        } catch (Throwable th) {
                            x.a(th);
                        }
                    }
                });
            }
            a aVar2 = a.this;
            long j = this.a;
            w.a().a(new c(j), j);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x00ad A[Catch:{ all -> 0x00b6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00b2 A[DONT_GENERATE] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.tencent.bugly.crashreport.biz.UserInfoBean> a(java.lang.String r12) {
        /*
            r11 = this;
            r0 = 0
            boolean r1 = com.tencent.bugly.proguard.z.a((java.lang.String) r12)     // Catch:{ all -> 0x00a5 }
            if (r1 == 0) goto L_0x0009
            r4 = r0
            goto L_0x001d
        L_0x0009:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a5 }
            java.lang.String r2 = "_pc = '"
            r1.<init>(r2)     // Catch:{ all -> 0x00a5 }
            r1.append(r12)     // Catch:{ all -> 0x00a5 }
            java.lang.String r12 = "'"
            r1.append(r12)     // Catch:{ all -> 0x00a5 }
            java.lang.String r12 = r1.toString()     // Catch:{ all -> 0x00a5 }
            r4 = r12
        L_0x001d:
            com.tencent.bugly.proguard.p r1 = com.tencent.bugly.proguard.p.a()     // Catch:{ all -> 0x00a5 }
            java.lang.String r2 = "t_ui"
            r3 = 0
            r5 = 0
            r6 = 0
            r7 = 1
            android.database.Cursor r12 = r1.a(r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x00a5 }
            if (r12 != 0) goto L_0x0033
            if (r12 == 0) goto L_0x0032
            r12.close()
        L_0x0032:
            return r0
        L_0x0033:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a3 }
            r1.<init>()     // Catch:{ all -> 0x00a3 }
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x00a3 }
            r2.<init>()     // Catch:{ all -> 0x00a3 }
        L_0x003d:
            boolean r3 = r12.moveToNext()     // Catch:{ all -> 0x00a3 }
            r4 = 0
            if (r3 == 0) goto L_0x006e
            com.tencent.bugly.crashreport.biz.UserInfoBean r3 = a((android.database.Cursor) r12)     // Catch:{ all -> 0x00a3 }
            if (r3 == 0) goto L_0x004e
            r2.add(r3)     // Catch:{ all -> 0x00a3 }
            goto L_0x003d
        L_0x004e:
            java.lang.String r3 = "_id"
            int r3 = r12.getColumnIndex(r3)     // Catch:{ all -> 0x0066 }
            long r5 = r12.getLong(r3)     // Catch:{ all -> 0x0066 }
            java.lang.String r3 = " or _id"
            r1.append(r3)     // Catch:{ all -> 0x0066 }
            java.lang.String r3 = " = "
            r1.append(r3)     // Catch:{ all -> 0x0066 }
            r1.append(r5)     // Catch:{ all -> 0x0066 }
            goto L_0x003d
        L_0x0066:
            java.lang.String r3 = "[Database] unknown id."
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x00a3 }
            com.tencent.bugly.proguard.x.d(r3, r4)     // Catch:{ all -> 0x00a3 }
            goto L_0x003d
        L_0x006e:
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00a3 }
            int r3 = r1.length()     // Catch:{ all -> 0x00a3 }
            if (r3 <= 0) goto L_0x009d
            r3 = 4
            java.lang.String r7 = r1.substring(r3)     // Catch:{ all -> 0x00a3 }
            com.tencent.bugly.proguard.p r5 = com.tencent.bugly.proguard.p.a()     // Catch:{ all -> 0x00a3 }
            java.lang.String r6 = "t_ui"
            r8 = 0
            r9 = 0
            r10 = 1
            int r1 = r5.a((java.lang.String) r6, (java.lang.String) r7, (java.lang.String[]) r8, (com.tencent.bugly.proguard.o) r9, (boolean) r10)     // Catch:{ all -> 0x00a3 }
            java.lang.String r3 = "[Database] deleted %s error data %d"
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ all -> 0x00a3 }
            java.lang.String r6 = "t_ui"
            r5[r4] = r6     // Catch:{ all -> 0x00a3 }
            r4 = 1
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x00a3 }
            r5[r4] = r1     // Catch:{ all -> 0x00a3 }
            com.tencent.bugly.proguard.x.d(r3, r5)     // Catch:{ all -> 0x00a3 }
        L_0x009d:
            if (r12 == 0) goto L_0x00a2
            r12.close()
        L_0x00a2:
            return r2
        L_0x00a3:
            r1 = move-exception
            goto L_0x00a7
        L_0x00a5:
            r1 = move-exception
            r12 = r0
        L_0x00a7:
            boolean r2 = com.tencent.bugly.proguard.x.a(r1)     // Catch:{ all -> 0x00b6 }
            if (r2 != 0) goto L_0x00b0
            r1.printStackTrace()     // Catch:{ all -> 0x00b6 }
        L_0x00b0:
            if (r12 == 0) goto L_0x00b5
            r12.close()
        L_0x00b5:
            return r0
        L_0x00b6:
            r0 = move-exception
            if (r12 == 0) goto L_0x00bc
            r12.close()
        L_0x00bc:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.biz.a.a(java.lang.String):java.util.List");
    }

    private static void a(List<UserInfoBean> list) {
        if (list != null && list.size() != 0) {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            while (i < list.size() && i < 50) {
                sb.append(" or _id");
                sb.append(" = ");
                sb.append(list.get(i).a);
                i++;
            }
            String sb2 = sb.toString();
            if (sb2.length() > 0) {
                sb2 = sb2.substring(4);
            }
            String str = sb2;
            sb.setLength(0);
            try {
                x.c("[Database] deleted %s data %d", "t_ui", Integer.valueOf(p.a().a("t_ui", str, (String[]) null, (o) null, true)));
            } catch (Throwable th) {
                if (!x.a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    private static ContentValues a(UserInfoBean userInfoBean) {
        if (userInfoBean == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (userInfoBean.a > 0) {
                contentValues.put("_id", Long.valueOf(userInfoBean.a));
            }
            contentValues.put("_tm", Long.valueOf(userInfoBean.e));
            contentValues.put("_ut", Long.valueOf(userInfoBean.f));
            contentValues.put("_tp", Integer.valueOf(userInfoBean.b));
            contentValues.put("_pc", userInfoBean.c);
            contentValues.put("_dt", z.a((Parcelable) userInfoBean));
            return contentValues;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    private static UserInfoBean a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            byte[] blob = cursor.getBlob(cursor.getColumnIndex("_dt"));
            if (blob == null) {
                return null;
            }
            long j = cursor.getLong(cursor.getColumnIndex("_id"));
            UserInfoBean userInfoBean = (UserInfoBean) z.a(blob, UserInfoBean.CREATOR);
            if (userInfoBean != null) {
                userInfoBean.a = j;
            }
            return userInfoBean;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }
}
