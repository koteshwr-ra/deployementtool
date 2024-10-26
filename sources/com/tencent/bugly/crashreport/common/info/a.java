package com.tencent.bugly.crashreport.common.info;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.os.Process;
import android.text.TextUtils;
import androidx.core.os.EnvironmentCompat;
import com.tencent.bugly.b;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/* compiled from: BUGLY */
public final class a {
    private static a ae;
    public boolean A = false;
    public boolean B = false;
    public HashMap<String, String> C = new HashMap<>();
    public boolean D = false;
    public List<String> E = new ArrayList();
    public com.tencent.bugly.crashreport.a F = null;
    public SharedPreferences G;
    private final Context H;
    private String I;
    private String J;
    private String K = EnvironmentCompat.MEDIA_UNKNOWN;
    private String L = EnvironmentCompat.MEDIA_UNKNOWN;
    private String M = "";
    private String N = null;
    private String O = null;
    private String P = null;
    private String Q = null;
    private long R = -1;
    private long S = -1;
    private long T = -1;
    private String U = null;
    private String V = null;
    private Map<String, PlugInBean> W = null;
    private boolean X = true;
    private String Y = null;
    private String Z = null;
    public final long a = System.currentTimeMillis();
    private Boolean aa = null;
    private String ab = null;
    private Map<String, PlugInBean> ac = null;
    private Map<String, PlugInBean> ad = null;
    private int af = -1;
    private int ag = -1;
    private Map<String, String> ah = new HashMap();
    private Map<String, String> ai = new HashMap();
    private Map<String, String> aj = new HashMap();
    private boolean ak = true;
    private Boolean al = null;
    private Boolean am = null;
    private String an = null;
    private String ao = null;
    private String ap = null;
    private String aq = null;
    private final Object ar = new Object();
    private final Object as = new Object();
    private final Object at = new Object();
    private final Object au = new Object();
    private final Object av = new Object();
    private final Object aw = new Object();
    private final Object ax = new Object();
    public final byte b;
    public String c;
    public final String d;
    public boolean e = true;
    public String f = "3.3.1";
    public final String g;
    public final String h;
    public final String i;
    public long j;
    public String k = null;
    public String l = null;
    public String m = null;
    public String n = null;
    public String o = null;
    public List<String> p = null;
    public String q = EnvironmentCompat.MEDIA_UNKNOWN;
    public long r = 0;
    public long s = 0;
    public long t = 0;
    public long u = 0;
    public boolean v = false;
    public String w = null;
    public String x = null;
    public String y = null;
    public String z = "";

    private a(Context context) {
        this.H = z.a(context);
        this.b = 1;
        PackageInfo b2 = AppInfo.b(context);
        if (b2 != null) {
            try {
                String str = b2.versionName;
                this.k = str;
                this.w = str;
                this.x = Integer.toString(b2.versionCode);
            } catch (Throwable th) {
                if (!x.a(th)) {
                    th.printStackTrace();
                }
            }
        }
        this.c = AppInfo.a(context);
        this.d = AppInfo.a(Process.myPid());
        this.g = b.n();
        this.h = b.a();
        this.l = AppInfo.c(context);
        this.i = "Android " + b.b() + ",level " + b.c();
        Map<String, String> d2 = AppInfo.d(context);
        if (d2 != null) {
            try {
                this.p = AppInfo.a(d2);
                String str2 = d2.get("BUGLY_APPID");
                if (str2 != null) {
                    this.Z = str2;
                    c("APP_ID", str2);
                }
                String str3 = d2.get("BUGLY_APP_VERSION");
                if (str3 != null) {
                    this.k = str3;
                }
                String str4 = d2.get("BUGLY_APP_CHANNEL");
                if (str4 != null) {
                    this.m = str4;
                }
                String str5 = d2.get("BUGLY_ENABLE_DEBUG");
                if (str5 != null) {
                    this.v = str5.equalsIgnoreCase("true");
                }
                String str6 = d2.get("com.tencent.rdm.uuid");
                if (str6 != null) {
                    this.y = str6;
                }
                String str7 = d2.get("BUGLY_APP_BUILD_NO");
                if (!TextUtils.isEmpty(str7)) {
                    Integer.parseInt(str7);
                }
                String str8 = d2.get("BUGLY_AREA");
                if (str8 != null) {
                    this.z = str8;
                }
            } catch (Throwable th2) {
                if (!x.a(th2)) {
                    th2.printStackTrace();
                }
            }
        }
        try {
            if (!context.getDatabasePath("bugly_db_").exists()) {
                this.B = true;
                x.c("App is first time to be installed on the device.", new Object[0]);
            }
        } catch (Throwable th3) {
            if (b.c) {
                th3.printStackTrace();
            }
        }
        this.G = z.a("BUGLY_COMMON_VALUES", context);
        x.c("com info create end", new Object[0]);
    }

    public final boolean a() {
        return this.ak;
    }

    public final void a(boolean z2) {
        this.ak = z2;
        com.tencent.bugly.crashreport.a aVar = this.F;
        if (aVar != null) {
            aVar.setNativeIsAppForeground(z2);
        }
    }

    public static synchronized a a(Context context) {
        a aVar;
        synchronized (a.class) {
            if (ae == null) {
                ae = new a(context);
            }
            aVar = ae;
        }
        return aVar;
    }

    public static synchronized a b() {
        a aVar;
        synchronized (a.class) {
            aVar = ae;
        }
        return aVar;
    }

    public final String c() {
        return this.f;
    }

    public final void d() {
        synchronized (this.ar) {
            this.I = UUID.randomUUID().toString();
        }
    }

    public final String e() {
        String str;
        synchronized (this.ar) {
            if (this.I == null) {
                synchronized (this.ar) {
                    this.I = UUID.randomUUID().toString();
                }
            }
            str = this.I;
        }
        return str;
    }

    public final String f() {
        if (!z.a((String) null)) {
            return null;
        }
        return this.Z;
    }

    public final void a(String str) {
        this.Z = str;
        c("APP_ID", str);
    }

    public final String g() {
        String str;
        synchronized (this.aw) {
            str = this.K;
        }
        return str;
    }

    public final void b(String str) {
        synchronized (this.aw) {
            if (str == null) {
                str = "10000";
            }
            this.K = str;
        }
    }

    public final void b(boolean z2) {
        this.X = z2;
    }

    public final String h() {
        String str;
        String str2 = this.J;
        if (str2 != null) {
            return str2;
        }
        if (!this.X) {
            str = "";
        } else {
            if (this.Q == null) {
                this.Q = b.a(this.H);
            }
            str = this.Q;
        }
        this.J = str;
        return str;
    }

    public final void c(String str) {
        this.J = str;
        synchronized (this.ax) {
            this.ai.put("E8", str);
        }
    }

    public final synchronized String i() {
        return this.L;
    }

    public final synchronized void d(String str) {
        this.L = str;
    }

    public final synchronized String j() {
        return this.M;
    }

    public final synchronized void e(String str) {
        this.M = str;
    }

    public final String k() {
        if (!this.X) {
            return "";
        }
        if (this.N == null) {
            this.N = b.d();
        }
        return this.N;
    }

    public final String l() {
        if (!this.X) {
            return "";
        }
        String str = this.O;
        if (str == null || !str.contains(":")) {
            this.O = b.f();
        }
        return this.O;
    }

    public final String m() {
        if (!this.X) {
            return "";
        }
        if (this.P == null) {
            this.P = b.e();
        }
        return this.P;
    }

    public final long n() {
        if (this.R <= 0) {
            this.R = b.g();
        }
        return this.R;
    }

    public final long o() {
        if (this.S <= 0) {
            this.S = b.i();
        }
        return this.S;
    }

    public final long p() {
        if (this.T <= 0) {
            this.T = b.k();
        }
        return this.T;
    }

    public final String q() {
        if (this.U == null) {
            this.U = b.a(this.H, true);
        }
        return this.U;
    }

    public final String r() {
        if (this.V == null) {
            this.V = b.d(this.H);
        }
        return this.V;
    }

    public final void a(String str, String str2) {
        if (str != null && str2 != null) {
            synchronized (this.as) {
                this.C.put(str, str2);
            }
        }
    }

    public final String s() {
        try {
            Map<String, ?> all = this.H.getSharedPreferences("BuglySdkInfos", 0).getAll();
            if (!all.isEmpty()) {
                synchronized (this.as) {
                    for (Map.Entry next : all.entrySet()) {
                        try {
                            this.C.put(next.getKey(), next.getValue().toString());
                        } catch (Throwable th) {
                            x.a(th);
                        }
                    }
                }
            }
        } catch (Throwable th2) {
            x.a(th2);
        }
        if (!this.C.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry next2 : this.C.entrySet()) {
                sb.append("[");
                sb.append((String) next2.getKey());
                sb.append(",");
                sb.append((String) next2.getValue());
                sb.append("] ");
            }
            x.c("SDK_INFO = %s", sb.toString());
            c("SDK_INFO", sb.toString());
            return sb.toString();
        }
        x.c("SDK_INFO is empty", new Object[0]);
        return null;
    }

    public final synchronized Map<String, PlugInBean> t() {
        return null;
    }

    public final String u() {
        if (this.Y == null) {
            this.Y = b.m();
        }
        return this.Y;
    }

    public final Boolean v() {
        if (this.aa == null) {
            this.aa = Boolean.valueOf(b.o());
        }
        return this.aa;
    }

    public final String w() {
        if (this.ab == null) {
            String str = b.c(this.H);
            this.ab = str;
            x.a("ROM ID: %s", str);
        }
        return this.ab;
    }

    public final Map<String, String> x() {
        synchronized (this.at) {
            if (this.ah.size() <= 0) {
                return null;
            }
            HashMap hashMap = new HashMap(this.ah);
            return hashMap;
        }
    }

    public final String f(String str) {
        String remove;
        if (z.a(str)) {
            x.d("key should not be empty %s", str);
            return null;
        }
        synchronized (this.at) {
            remove = this.ah.remove(str);
        }
        return remove;
    }

    public final void y() {
        synchronized (this.at) {
            this.ah.clear();
        }
    }

    public final String g(String str) {
        String str2;
        if (z.a(str)) {
            x.d("key should not be empty %s", str);
            return null;
        }
        synchronized (this.at) {
            str2 = this.ah.get(str);
        }
        return str2;
    }

    public final void b(String str, String str2) {
        if (z.a(str) || z.a(str2)) {
            x.d("key&value should not be empty %s %s", str, str2);
            return;
        }
        synchronized (this.at) {
            this.ah.put(str, str2);
        }
    }

    public final int z() {
        int size;
        synchronized (this.at) {
            size = this.ah.size();
        }
        return size;
    }

    public final Set<String> A() {
        Set<String> keySet;
        synchronized (this.at) {
            keySet = this.ah.keySet();
        }
        return keySet;
    }

    public final Map<String, String> B() {
        synchronized (this.ax) {
            if (this.ai.size() <= 0) {
                return null;
            }
            HashMap hashMap = new HashMap(this.ai);
            return hashMap;
        }
    }

    public final void c(String str, String str2) {
        if (z.a(str) || z.a(str2)) {
            x.d("server key&value should not be empty %s %s", str, str2);
            return;
        }
        synchronized (this.au) {
            this.aj.put(str, str2);
        }
    }

    public final Map<String, String> C() {
        synchronized (this.au) {
            if (this.aj.size() <= 0) {
                return null;
            }
            HashMap hashMap = new HashMap(this.aj);
            return hashMap;
        }
    }

    public final void a(int i2) {
        synchronized (this.av) {
            int i3 = this.af;
            if (i3 != i2) {
                this.af = i2;
                x.a("user scene tag %d changed to tag %d", Integer.valueOf(i3), Integer.valueOf(this.af));
            }
        }
    }

    public final int D() {
        int i2;
        synchronized (this.av) {
            i2 = this.af;
        }
        return i2;
    }

    public final void b(int i2) {
        int i3 = this.ag;
        if (i3 != 24096) {
            this.ag = 24096;
            x.a("server scene tag %d changed to tag %d", Integer.valueOf(i3), Integer.valueOf(this.ag));
        }
    }

    public final int E() {
        return this.ag;
    }

    public final synchronized Map<String, PlugInBean> F() {
        return null;
    }

    public static int G() {
        return b.c();
    }

    public final String H() {
        if (this.an == null) {
            this.an = b.p();
        }
        return this.an;
    }

    public final String I() {
        if (this.ao == null) {
            this.ao = b.e(this.H);
        }
        return this.ao;
    }

    public final String J() {
        if (this.ap == null) {
            this.ap = b.f(this.H);
        }
        return this.ap;
    }

    public final String K() {
        return b.q();
    }

    public final String L() {
        if (this.aq == null) {
            this.aq = b.g(this.H);
        }
        return this.aq;
    }

    public final long M() {
        return b.r();
    }

    public final boolean N() {
        if (this.al == null) {
            this.al = Boolean.valueOf(b.h(this.H));
            x.a("Is it a virtual machine? " + this.al, new Object[0]);
        }
        return this.al.booleanValue();
    }

    public final boolean O() {
        if (this.am == null) {
            this.am = Boolean.valueOf(b.i(this.H));
            x.a("Does it has hook frame? " + this.am, new Object[0]);
        }
        return this.am.booleanValue();
    }
}
