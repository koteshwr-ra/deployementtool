package com.tencent.bugly.proguard;

import android.text.TextUtils;
import com.alibaba.android.arouter.compiler.utils.Consts;
import com.tencent.bugly.crashreport.biz.UserInfoBean;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import mc.csst.com.selfchassis.ui.fragment.set.schedule.ScheduleFragment;
import org.apache.log4j.spi.LocationInfo;

/* compiled from: BUGLY */
public class a {
    private static Proxy e;
    protected HashMap<String, HashMap<String, byte[]>> a = new HashMap<>();
    protected String b;
    i c;
    private HashMap<String, Object> d;

    a() {
        new HashMap();
        this.d = new HashMap<>();
        this.b = "GBK";
        this.c = new i();
    }

    public static void a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            e = null;
        } else {
            e = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(str, i));
        }
    }

    public static void a(InetAddress inetAddress, int i) {
        if (inetAddress == null) {
            e = null;
        } else {
            e = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(inetAddress, i));
        }
    }

    public void a(String str) {
        this.b = str;
    }

    public static Proxy b() {
        return e;
    }

    public static aq a(UserInfoBean userInfoBean) {
        if (userInfoBean == null) {
            return null;
        }
        aq aqVar = new aq();
        aqVar.a = userInfoBean.e;
        aqVar.e = userInfoBean.j;
        aqVar.d = userInfoBean.c;
        aqVar.c = userInfoBean.d;
        aqVar.g = com.tencent.bugly.crashreport.common.info.a.b().i();
        aqVar.h = userInfoBean.o == 1;
        int i = userInfoBean.b;
        if (i == 1) {
            aqVar.b = 1;
        } else if (i == 2) {
            aqVar.b = 4;
        } else if (i == 3) {
            aqVar.b = 2;
        } else if (i == 4) {
            aqVar.b = 3;
        } else if (userInfoBean.b < 10 || userInfoBean.b >= 20) {
            x.e("unknown uinfo type %d ", Integer.valueOf(userInfoBean.b));
            return null;
        } else {
            aqVar.b = (byte) userInfoBean.b;
        }
        aqVar.f = new HashMap();
        if (userInfoBean.p >= 0) {
            Map<String, String> map = aqVar.f;
            StringBuilder sb = new StringBuilder();
            sb.append(userInfoBean.p);
            map.put("C01", sb.toString());
        }
        if (userInfoBean.q >= 0) {
            Map<String, String> map2 = aqVar.f;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(userInfoBean.q);
            map2.put("C02", sb2.toString());
        }
        if (userInfoBean.r != null && userInfoBean.r.size() > 0) {
            for (Map.Entry next : userInfoBean.r.entrySet()) {
                Map<String, String> map3 = aqVar.f;
                map3.put("C03_" + ((String) next.getKey()), next.getValue());
            }
        }
        if (userInfoBean.s != null && userInfoBean.s.size() > 0) {
            for (Map.Entry next2 : userInfoBean.s.entrySet()) {
                Map<String, String> map4 = aqVar.f;
                map4.put("C04_" + ((String) next2.getKey()), next2.getValue());
            }
        }
        Map<String, String> map5 = aqVar.f;
        StringBuilder sb3 = new StringBuilder();
        sb3.append(!userInfoBean.l);
        map5.put("A36", sb3.toString());
        Map<String, String> map6 = aqVar.f;
        StringBuilder sb4 = new StringBuilder();
        sb4.append(userInfoBean.g);
        map6.put("F02", sb4.toString());
        Map<String, String> map7 = aqVar.f;
        StringBuilder sb5 = new StringBuilder();
        sb5.append(userInfoBean.h);
        map7.put("F03", sb5.toString());
        Map<String, String> map8 = aqVar.f;
        map8.put("F04", userInfoBean.j);
        Map<String, String> map9 = aqVar.f;
        StringBuilder sb6 = new StringBuilder();
        sb6.append(userInfoBean.i);
        map9.put("F05", sb6.toString());
        Map<String, String> map10 = aqVar.f;
        map10.put("F06", userInfoBean.m);
        Map<String, String> map11 = aqVar.f;
        StringBuilder sb7 = new StringBuilder();
        sb7.append(userInfoBean.k);
        map11.put("F10", sb7.toString());
        x.c("summary type %d vm:%d", Byte.valueOf(aqVar.b), Integer.valueOf(aqVar.f.size()));
        return aqVar;
    }

    public static String a(ArrayList<String> arrayList) {
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        while (true) {
            int size = arrayList.size();
            String str = ScheduleFragment.MAP;
            if (i < size) {
                String str2 = arrayList.get(i);
                if (str2.equals(Consts.INTEGER) || str2.equals("int")) {
                    str = "int32";
                } else if (str2.equals(Consts.BOOLEAN) || str2.equals("boolean")) {
                    str = "bool";
                } else if (str2.equals(Consts.BYTE) || str2.equals("byte")) {
                    str = "char";
                } else if (str2.equals(Consts.DOUBEL) || str2.equals("double")) {
                    str = "double";
                } else if (str2.equals(Consts.FLOAT) || str2.equals("float")) {
                    str = "float";
                } else if (str2.equals(Consts.LONG) || str2.equals("long")) {
                    str = "int64";
                } else if (str2.equals(Consts.SHORT) || str2.equals("short")) {
                    str = "short";
                } else if (str2.equals(Consts.CHAR)) {
                    throw new IllegalArgumentException("can not support java.lang.Character");
                } else if (str2.equals(Consts.STRING)) {
                    str = "string";
                } else if (str2.equals("java.util.List")) {
                    str = "list";
                } else if (!str2.equals("java.util.Map")) {
                    str = str2;
                }
                arrayList.set(i, str);
                i++;
            } else {
                Collections.reverse(arrayList);
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    String str3 = arrayList.get(i2);
                    if (str3.equals("list")) {
                        int i3 = i2 - 1;
                        arrayList.set(i3, "<" + arrayList.get(i3));
                        arrayList.set(0, arrayList.get(0) + ">");
                    } else if (str3.equals(str)) {
                        int i4 = i2 - 1;
                        arrayList.set(i4, "<" + arrayList.get(i4) + ",");
                        arrayList.set(0, arrayList.get(0) + ">");
                    } else if (str3.equals("Array")) {
                        int i5 = i2 - 1;
                        arrayList.set(i5, "<" + arrayList.get(i5));
                        arrayList.set(0, arrayList.get(0) + ">");
                    }
                }
                Collections.reverse(arrayList);
                Iterator<String> it = arrayList.iterator();
                while (it.hasNext()) {
                    stringBuffer.append(it.next());
                }
                return stringBuffer.toString();
            }
        }
    }

    public <T> void a(String str, T t) {
        if (str == null) {
            throw new IllegalArgumentException("put key can not is null");
        } else if (t == null) {
            throw new IllegalArgumentException("put value can not is null");
        } else if (!(t instanceof Set)) {
            j jVar = new j();
            jVar.a(this.b);
            jVar.a((Object) t, 0);
            byte[] a2 = l.a(jVar.a());
            HashMap hashMap = new HashMap(1);
            ArrayList arrayList = new ArrayList(1);
            a((ArrayList<String>) arrayList, (Object) t);
            hashMap.put(a((ArrayList<String>) arrayList), a2);
            this.d.remove(str);
            this.a.put(str, hashMap);
        } else {
            throw new IllegalArgumentException("can not support Set");
        }
    }

    public static ar a(List<UserInfoBean> list, int i) {
        com.tencent.bugly.crashreport.common.info.a b2;
        if (list == null || list.size() == 0 || (b2 = com.tencent.bugly.crashreport.common.info.a.b()) == null) {
            return null;
        }
        b2.s();
        ar arVar = new ar();
        arVar.b = b2.d;
        arVar.c = b2.h();
        ArrayList<aq> arrayList = new ArrayList<>();
        for (UserInfoBean a2 : list) {
            aq a3 = a(a2);
            if (a3 != null) {
                arrayList.add(a3);
            }
        }
        arVar.d = arrayList;
        arVar.e = new HashMap();
        Map<String, String> map = arVar.e;
        map.put("A7", b2.g);
        Map<String, String> map2 = arVar.e;
        map2.put("A6", b2.r());
        Map<String, String> map3 = arVar.e;
        map3.put("A5", b2.q());
        Map<String, String> map4 = arVar.e;
        StringBuilder sb = new StringBuilder();
        sb.append(b2.o());
        map4.put("A2", sb.toString());
        Map<String, String> map5 = arVar.e;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(b2.o());
        map5.put("A1", sb2.toString());
        Map<String, String> map6 = arVar.e;
        map6.put("A24", b2.i);
        Map<String, String> map7 = arVar.e;
        StringBuilder sb3 = new StringBuilder();
        sb3.append(b2.p());
        map7.put("A17", sb3.toString());
        Map<String, String> map8 = arVar.e;
        map8.put("A15", b2.u());
        Map<String, String> map9 = arVar.e;
        StringBuilder sb4 = new StringBuilder();
        sb4.append(b2.v());
        map9.put("A13", sb4.toString());
        Map<String, String> map10 = arVar.e;
        map10.put("F08", b2.w);
        Map<String, String> map11 = arVar.e;
        map11.put("F09", b2.x);
        Map<String, String> C = b2.C();
        if (C != null && C.size() > 0) {
            for (Map.Entry next : C.entrySet()) {
                Map<String, String> map12 = arVar.e;
                map12.put("C04_" + ((String) next.getKey()), next.getValue());
            }
        }
        if (i == 1) {
            arVar.a = 1;
        } else if (i != 2) {
            x.e("unknown up type %d ", Integer.valueOf(i));
            return null;
        } else {
            arVar.a = 2;
        }
        return arVar;
    }

    public static <T extends k> T a(byte[] bArr, Class<T> cls) {
        if (bArr != null && bArr.length > 0) {
            try {
                T t = (k) cls.newInstance();
                i iVar = new i(bArr);
                iVar.a("utf-8");
                t.a(iVar);
                return t;
            } catch (Throwable th) {
                if (!x.b(th)) {
                    th.printStackTrace();
                }
            }
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x023f, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0244, code lost:
        if (com.tencent.bugly.proguard.x.b(r6) == false) goto L_0x0246;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0246, code lost:
        r6.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0249, code lost:
        return null;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.tencent.bugly.proguard.am a(android.content.Context r6, int r7, byte[] r8) {
        /*
            com.tencent.bugly.crashreport.common.info.a r0 = com.tencent.bugly.crashreport.common.info.a.b()
            com.tencent.bugly.crashreport.common.strategy.a r1 = com.tencent.bugly.crashreport.common.strategy.a.a()
            com.tencent.bugly.crashreport.common.strategy.StrategyBean r1 = r1.c()
            r2 = 0
            if (r0 == 0) goto L_0x024a
            if (r1 != 0) goto L_0x0013
            goto L_0x024a
        L_0x0013:
            com.tencent.bugly.proguard.am r3 = new com.tencent.bugly.proguard.am     // Catch:{ all -> 0x023f }
            r3.<init>()     // Catch:{ all -> 0x023f }
            monitor-enter(r0)     // Catch:{ all -> 0x023f }
            r4 = 1
            r3.a = r4     // Catch:{ all -> 0x023c }
            java.lang.String r4 = r0.f()     // Catch:{ all -> 0x023c }
            r3.b = r4     // Catch:{ all -> 0x023c }
            java.lang.String r4 = r0.c     // Catch:{ all -> 0x023c }
            r3.c = r4     // Catch:{ all -> 0x023c }
            java.lang.String r4 = r0.k     // Catch:{ all -> 0x023c }
            r3.d = r4     // Catch:{ all -> 0x023c }
            java.lang.String r4 = r0.m     // Catch:{ all -> 0x023c }
            r3.e = r4     // Catch:{ all -> 0x023c }
            java.lang.String r4 = r0.f     // Catch:{ all -> 0x023c }
            r3.f = r4     // Catch:{ all -> 0x023c }
            r3.g = r7     // Catch:{ all -> 0x023c }
            if (r8 != 0) goto L_0x003c
            java.lang.String r7 = ""
            byte[] r8 = r7.getBytes()     // Catch:{ all -> 0x023c }
        L_0x003c:
            r3.h = r8     // Catch:{ all -> 0x023c }
            java.lang.String r7 = r0.h     // Catch:{ all -> 0x023c }
            r3.i = r7     // Catch:{ all -> 0x023c }
            java.lang.String r7 = r0.i     // Catch:{ all -> 0x023c }
            r3.j = r7     // Catch:{ all -> 0x023c }
            java.util.HashMap r7 = new java.util.HashMap     // Catch:{ all -> 0x023c }
            r7.<init>()     // Catch:{ all -> 0x023c }
            r3.k = r7     // Catch:{ all -> 0x023c }
            java.lang.String r7 = r0.e()     // Catch:{ all -> 0x023c }
            r3.l = r7     // Catch:{ all -> 0x023c }
            long r7 = r1.n     // Catch:{ all -> 0x023c }
            r3.m = r7     // Catch:{ all -> 0x023c }
            java.lang.String r7 = r0.h()     // Catch:{ all -> 0x023c }
            r3.o = r7     // Catch:{ all -> 0x023c }
            java.lang.String r6 = com.tencent.bugly.crashreport.common.info.b.b(r6)     // Catch:{ all -> 0x023c }
            r3.p = r6     // Catch:{ all -> 0x023c }
            long r6 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x023c }
            r3.q = r6     // Catch:{ all -> 0x023c }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x023c }
            r6.<init>()     // Catch:{ all -> 0x023c }
            java.lang.String r7 = r0.k()     // Catch:{ all -> 0x023c }
            r6.append(r7)     // Catch:{ all -> 0x023c }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x023c }
            r3.r = r6     // Catch:{ all -> 0x023c }
            java.lang.String r6 = r0.j()     // Catch:{ all -> 0x023c }
            r3.s = r6     // Catch:{ all -> 0x023c }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x023c }
            r6.<init>()     // Catch:{ all -> 0x023c }
            java.lang.String r7 = r0.m()     // Catch:{ all -> 0x023c }
            r6.append(r7)     // Catch:{ all -> 0x023c }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x023c }
            r3.t = r6     // Catch:{ all -> 0x023c }
            java.lang.String r6 = r0.l()     // Catch:{ all -> 0x023c }
            r3.u = r6     // Catch:{ all -> 0x023c }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x023c }
            r6.<init>()     // Catch:{ all -> 0x023c }
            java.lang.String r7 = r0.h()     // Catch:{ all -> 0x023c }
            r6.append(r7)     // Catch:{ all -> 0x023c }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x023c }
            r3.v = r6     // Catch:{ all -> 0x023c }
            java.lang.String r6 = r3.p     // Catch:{ all -> 0x023c }
            r3.w = r6     // Catch:{ all -> 0x023c }
            r0.getClass()     // Catch:{ all -> 0x023c }
            java.lang.String r6 = "com.tencent.bugly"
            r3.n = r6     // Catch:{ all -> 0x023c }
            java.util.Map<java.lang.String, java.lang.String> r6 = r3.k     // Catch:{ all -> 0x023c }
            java.lang.String r7 = "A26"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x023c }
            r8.<init>()     // Catch:{ all -> 0x023c }
            java.lang.String r1 = r0.w()     // Catch:{ all -> 0x023c }
            r8.append(r1)     // Catch:{ all -> 0x023c }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x023c }
            r6.put(r7, r8)     // Catch:{ all -> 0x023c }
            java.util.Map<java.lang.String, java.lang.String> r6 = r3.k     // Catch:{ all -> 0x023c }
            java.lang.String r7 = "A62"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x023c }
            r8.<init>()     // Catch:{ all -> 0x023c }
            boolean r1 = r0.N()     // Catch:{ all -> 0x023c }
            r8.append(r1)     // Catch:{ all -> 0x023c }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x023c }
            r6.put(r7, r8)     // Catch:{ all -> 0x023c }
            java.util.Map<java.lang.String, java.lang.String> r6 = r3.k     // Catch:{ all -> 0x023c }
            java.lang.String r7 = "A63"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x023c }
            r8.<init>()     // Catch:{ all -> 0x023c }
            boolean r1 = r0.O()     // Catch:{ all -> 0x023c }
            r8.append(r1)     // Catch:{ all -> 0x023c }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x023c }
            r6.put(r7, r8)     // Catch:{ all -> 0x023c }
            java.util.Map<java.lang.String, java.lang.String> r6 = r3.k     // Catch:{ all -> 0x023c }
            java.lang.String r7 = "F11"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x023c }
            r8.<init>()     // Catch:{ all -> 0x023c }
            boolean r1 = r0.B     // Catch:{ all -> 0x023c }
            r8.append(r1)     // Catch:{ all -> 0x023c }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x023c }
            r6.put(r7, r8)     // Catch:{ all -> 0x023c }
            java.util.Map<java.lang.String, java.lang.String> r6 = r3.k     // Catch:{ all -> 0x023c }
            java.lang.String r7 = "F12"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x023c }
            r8.<init>()     // Catch:{ all -> 0x023c }
            boolean r1 = r0.A     // Catch:{ all -> 0x023c }
            r8.append(r1)     // Catch:{ all -> 0x023c }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x023c }
            r6.put(r7, r8)     // Catch:{ all -> 0x023c }
            boolean r6 = r0.D     // Catch:{ all -> 0x023c }
            if (r6 == 0) goto L_0x01b7
            java.util.Map<java.lang.String, java.lang.String> r6 = r3.k     // Catch:{ all -> 0x023c }
            java.lang.String r7 = "G2"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x023c }
            r8.<init>()     // Catch:{ all -> 0x023c }
            java.lang.String r1 = r0.H()     // Catch:{ all -> 0x023c }
            r8.append(r1)     // Catch:{ all -> 0x023c }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x023c }
            r6.put(r7, r8)     // Catch:{ all -> 0x023c }
            java.util.Map<java.lang.String, java.lang.String> r6 = r3.k     // Catch:{ all -> 0x023c }
            java.lang.String r7 = "G3"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x023c }
            r8.<init>()     // Catch:{ all -> 0x023c }
            java.lang.String r1 = r0.I()     // Catch:{ all -> 0x023c }
            r8.append(r1)     // Catch:{ all -> 0x023c }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x023c }
            r6.put(r7, r8)     // Catch:{ all -> 0x023c }
            java.util.Map<java.lang.String, java.lang.String> r6 = r3.k     // Catch:{ all -> 0x023c }
            java.lang.String r7 = "G4"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x023c }
            r8.<init>()     // Catch:{ all -> 0x023c }
            java.lang.String r1 = r0.J()     // Catch:{ all -> 0x023c }
            r8.append(r1)     // Catch:{ all -> 0x023c }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x023c }
            r6.put(r7, r8)     // Catch:{ all -> 0x023c }
            java.util.Map<java.lang.String, java.lang.String> r6 = r3.k     // Catch:{ all -> 0x023c }
            java.lang.String r7 = "G5"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x023c }
            r8.<init>()     // Catch:{ all -> 0x023c }
            java.lang.String r1 = r0.K()     // Catch:{ all -> 0x023c }
            r8.append(r1)     // Catch:{ all -> 0x023c }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x023c }
            r6.put(r7, r8)     // Catch:{ all -> 0x023c }
            java.util.Map<java.lang.String, java.lang.String> r6 = r3.k     // Catch:{ all -> 0x023c }
            java.lang.String r7 = "G6"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x023c }
            r8.<init>()     // Catch:{ all -> 0x023c }
            java.lang.String r1 = r0.L()     // Catch:{ all -> 0x023c }
            r8.append(r1)     // Catch:{ all -> 0x023c }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x023c }
            r6.put(r7, r8)     // Catch:{ all -> 0x023c }
            java.util.Map<java.lang.String, java.lang.String> r6 = r3.k     // Catch:{ all -> 0x023c }
            java.lang.String r7 = "G7"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x023c }
            r8.<init>()     // Catch:{ all -> 0x023c }
            long r4 = r0.M()     // Catch:{ all -> 0x023c }
            java.lang.String r1 = java.lang.Long.toString(r4)     // Catch:{ all -> 0x023c }
            r8.append(r1)     // Catch:{ all -> 0x023c }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x023c }
            r6.put(r7, r8)     // Catch:{ all -> 0x023c }
        L_0x01b7:
            java.util.Map<java.lang.String, java.lang.String> r6 = r3.k     // Catch:{ all -> 0x023c }
            java.lang.String r7 = "D3"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x023c }
            r8.<init>()     // Catch:{ all -> 0x023c }
            java.lang.String r1 = r0.l     // Catch:{ all -> 0x023c }
            r8.append(r1)     // Catch:{ all -> 0x023c }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x023c }
            r6.put(r7, r8)     // Catch:{ all -> 0x023c }
            java.util.List<com.tencent.bugly.a> r6 = com.tencent.bugly.b.b     // Catch:{ all -> 0x023c }
            if (r6 == 0) goto L_0x01f4
            java.util.List<com.tencent.bugly.a> r6 = com.tencent.bugly.b.b     // Catch:{ all -> 0x023c }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ all -> 0x023c }
        L_0x01d6:
            boolean r7 = r6.hasNext()     // Catch:{ all -> 0x023c }
            if (r7 == 0) goto L_0x01f4
            java.lang.Object r7 = r6.next()     // Catch:{ all -> 0x023c }
            com.tencent.bugly.a r7 = (com.tencent.bugly.a) r7     // Catch:{ all -> 0x023c }
            java.lang.String r8 = r7.versionKey     // Catch:{ all -> 0x023c }
            if (r8 == 0) goto L_0x01d6
            java.lang.String r8 = r7.version     // Catch:{ all -> 0x023c }
            if (r8 == 0) goto L_0x01d6
            java.util.Map<java.lang.String, java.lang.String> r8 = r3.k     // Catch:{ all -> 0x023c }
            java.lang.String r1 = r7.versionKey     // Catch:{ all -> 0x023c }
            java.lang.String r7 = r7.version     // Catch:{ all -> 0x023c }
            r8.put(r1, r7)     // Catch:{ all -> 0x023c }
            goto L_0x01d6
        L_0x01f4:
            java.util.Map<java.lang.String, java.lang.String> r6 = r3.k     // Catch:{ all -> 0x023c }
            java.lang.String r7 = "G15"
            java.lang.String r8 = "G15"
            java.lang.String r1 = ""
            java.lang.String r8 = com.tencent.bugly.proguard.z.b((java.lang.String) r8, (java.lang.String) r1)     // Catch:{ all -> 0x023c }
            r6.put(r7, r8)     // Catch:{ all -> 0x023c }
            java.util.Map<java.lang.String, java.lang.String> r6 = r3.k     // Catch:{ all -> 0x023c }
            java.lang.String r7 = "D4"
            java.lang.String r8 = "D4"
            java.lang.String r1 = "0"
            java.lang.String r8 = com.tencent.bugly.proguard.z.b((java.lang.String) r8, (java.lang.String) r1)     // Catch:{ all -> 0x023c }
            r6.put(r7, r8)     // Catch:{ all -> 0x023c }
            monitor-exit(r0)     // Catch:{ all -> 0x023c }
            java.util.Map r6 = r0.B()     // Catch:{ all -> 0x023f }
            if (r6 == 0) goto L_0x023b
            java.util.Set r6 = r6.entrySet()     // Catch:{ all -> 0x023f }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ all -> 0x023f }
        L_0x0221:
            boolean r7 = r6.hasNext()     // Catch:{ all -> 0x023f }
            if (r7 == 0) goto L_0x023b
            java.lang.Object r7 = r6.next()     // Catch:{ all -> 0x023f }
            java.util.Map$Entry r7 = (java.util.Map.Entry) r7     // Catch:{ all -> 0x023f }
            java.util.Map<java.lang.String, java.lang.String> r8 = r3.k     // Catch:{ all -> 0x023f }
            java.lang.Object r0 = r7.getKey()     // Catch:{ all -> 0x023f }
            java.lang.Object r7 = r7.getValue()     // Catch:{ all -> 0x023f }
            r8.put(r0, r7)     // Catch:{ all -> 0x023f }
            goto L_0x0221
        L_0x023b:
            return r3
        L_0x023c:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x023f }
            throw r6     // Catch:{ all -> 0x023f }
        L_0x023f:
            r6 = move-exception
            boolean r7 = com.tencent.bugly.proguard.x.b(r6)
            if (r7 != 0) goto L_0x0249
            r6.printStackTrace()
        L_0x0249:
            return r2
        L_0x024a:
            java.lang.String r6 = "Can not create request pkg for parameters is invalid."
            r7 = 0
            java.lang.Object[] r7 = new java.lang.Object[r7]
            com.tencent.bugly.proguard.x.e(r6, r7)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.a.a(android.content.Context, int, byte[]):com.tencent.bugly.proguard.am");
    }

    private void a(ArrayList<String> arrayList, Object obj) {
        if (obj.getClass().isArray()) {
            if (!obj.getClass().getComponentType().toString().equals("byte")) {
                throw new IllegalArgumentException("only byte[] is supported");
            } else if (Array.getLength(obj) > 0) {
                arrayList.add("java.util.List");
                a(arrayList, Array.get(obj, 0));
            } else {
                arrayList.add("Array");
                arrayList.add(LocationInfo.NA);
            }
        } else if (obj instanceof Array) {
            throw new IllegalArgumentException("can not support Array, please use List");
        } else if (obj instanceof List) {
            arrayList.add("java.util.List");
            List list = (List) obj;
            if (list.size() > 0) {
                a(arrayList, list.get(0));
            } else {
                arrayList.add(LocationInfo.NA);
            }
        } else if (obj instanceof Map) {
            arrayList.add("java.util.Map");
            Map map = (Map) obj;
            if (map.size() > 0) {
                Object next = map.keySet().iterator().next();
                Object obj2 = map.get(next);
                arrayList.add(next.getClass().getName());
                a(arrayList, obj2);
                return;
            }
            arrayList.add(LocationInfo.NA);
            arrayList.add(LocationInfo.NA);
        } else {
            arrayList.add(obj.getClass().getName());
        }
    }

    public byte[] a() {
        j jVar = new j(0);
        jVar.a(this.b);
        jVar.a(this.a, 0);
        return l.a(jVar.a());
    }

    public void a(byte[] bArr) {
        this.c.a(bArr);
        this.c.a(this.b);
        HashMap hashMap = new HashMap(1);
        HashMap hashMap2 = new HashMap(1);
        hashMap2.put("", new byte[0]);
        hashMap.put("", hashMap2);
        this.a = this.c.a(hashMap, 0, false);
    }

    public static byte[] a(Object obj) {
        try {
            d dVar = new d();
            dVar.c();
            dVar.a("utf-8");
            dVar.a(1);
            dVar.b("RqdServer");
            dVar.c("sync");
            dVar.a("detail", obj);
            return dVar.a();
        } catch (Throwable th) {
            if (x.b(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    public static an b(byte[] bArr) {
        if (bArr != null) {
            try {
                d dVar = new d();
                dVar.c();
                dVar.a("utf-8");
                dVar.a(bArr);
                Object b2 = dVar.b("detail", new an());
                if (an.class.isInstance(b2)) {
                    return an.class.cast(b2);
                }
                return null;
            } catch (Throwable th) {
                if (!x.b(th)) {
                    th.printStackTrace();
                }
            }
        }
        return null;
    }

    public static byte[] a(k kVar) {
        try {
            j jVar = new j();
            jVar.a("utf-8");
            kVar.a(jVar);
            return jVar.b();
        } catch (Throwable th) {
            if (x.b(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }
}
