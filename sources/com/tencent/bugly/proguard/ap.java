package com.tencent.bugly.proguard;

import com.alibaba.android.arouter.compiler.utils.Consts;
import com.tencent.smtt.sdk.TbsVideoCacheTask;
import java.util.HashMap;
import java.util.Map;

/* compiled from: BUGLY */
public final class ap extends k implements Cloneable {
    private static ao m = new ao();
    private static Map<String, String> n;
    private static /* synthetic */ boolean o = (!ap.class.desiredAssertionStatus());
    public boolean a = true;
    public boolean b = true;
    public boolean c = true;
    public String d = "";
    public String e = "";
    public ao f = null;
    public Map<String, String> g = null;
    public long h = 0;
    public int i = 0;
    private String j = "";
    private String k = "";
    private int l = 0;

    static {
        HashMap hashMap = new HashMap();
        n = hashMap;
        hashMap.put("", "");
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        ap apVar = (ap) obj;
        if (!l.a(this.a, apVar.a) || !l.a(this.b, apVar.b) || !l.a(this.c, apVar.c) || !l.a((Object) this.d, (Object) apVar.d) || !l.a((Object) this.e, (Object) apVar.e) || !l.a((Object) this.f, (Object) apVar.f) || !l.a((Object) this.g, (Object) apVar.g) || !l.a(this.h, apVar.h) || !l.a((Object) this.j, (Object) apVar.j) || !l.a((Object) this.k, (Object) apVar.k) || !l.a(this.l, apVar.l) || !l.a(this.i, apVar.i)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        try {
            throw new Exception("Need define key first!");
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public final Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            if (o) {
                return null;
            }
            throw new AssertionError();
        }
    }

    public final void a(j jVar) {
        jVar.a(this.a, 0);
        jVar.a(this.b, 1);
        jVar.a(this.c, 2);
        String str = this.d;
        if (str != null) {
            jVar.a(str, 3);
        }
        String str2 = this.e;
        if (str2 != null) {
            jVar.a(str2, 4);
        }
        ao aoVar = this.f;
        if (aoVar != null) {
            jVar.a((k) aoVar, 5);
        }
        Map<String, String> map = this.g;
        if (map != null) {
            jVar.a(map, 6);
        }
        jVar.a(this.h, 7);
        String str3 = this.j;
        if (str3 != null) {
            jVar.a(str3, 8);
        }
        String str4 = this.k;
        if (str4 != null) {
            jVar.a(str4, 9);
        }
        jVar.a(this.l, 10);
        jVar.a(this.i, 11);
    }

    public final void a(i iVar) {
        this.a = iVar.a(0, true);
        this.b = iVar.a(1, true);
        this.c = iVar.a(2, true);
        this.d = iVar.b(3, false);
        this.e = iVar.b(4, false);
        this.f = (ao) iVar.a((k) m, 5, false);
        this.g = (Map) iVar.a(n, 6, false);
        this.h = iVar.a(this.h, 7, false);
        this.j = iVar.b(8, false);
        this.k = iVar.b(9, false);
        this.l = iVar.a(this.l, 10, false);
        this.i = iVar.a(this.i, 11, false);
    }

    public final void a(StringBuilder sb, int i2) {
        h hVar = new h(sb, i2);
        hVar.a(this.a, Consts.VALUE_ENABLE);
        hVar.a(this.b, "enableUserInfo");
        hVar.a(this.c, "enableQuery");
        hVar.a(this.d, TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_URL);
        hVar.a(this.e, "expUrl");
        hVar.a((k) this.f, "security");
        hVar.a(this.g, "valueMap");
        hVar.a(this.h, "strategylastUpdateTime");
        hVar.a(this.j, "httpsUrl");
        hVar.a(this.k, "httpsExpUrl");
        hVar.a(this.l, "eventRecordCount");
        hVar.a(this.i, "eventTimeInterval");
    }
}
