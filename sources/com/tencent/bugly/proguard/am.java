package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Map;

/* compiled from: BUGLY */
public final class am extends k {
    private static byte[] y;
    private static Map<String, String> z;
    public int a = 0;
    public String b = "";
    public String c = "";
    public String d = "";
    public String e = "";
    public String f = "";
    public int g = 0;
    public byte[] h = null;
    public String i = "";
    public String j = "";
    public Map<String, String> k = null;
    public String l = "";
    public long m = 0;
    public String n = "";
    public String o = "";
    public String p = "";
    public long q = 0;
    public String r = "";
    public String s = "";
    public String t = "";
    public String u = "";
    public String v = "";
    public String w = "";
    private String x = "";

    public final void a(j jVar) {
        jVar.a(this.a, 0);
        jVar.a(this.b, 1);
        jVar.a(this.c, 2);
        jVar.a(this.d, 3);
        String str = this.e;
        if (str != null) {
            jVar.a(str, 4);
        }
        jVar.a(this.f, 5);
        jVar.a(this.g, 6);
        jVar.a(this.h, 7);
        String str2 = this.i;
        if (str2 != null) {
            jVar.a(str2, 8);
        }
        String str3 = this.j;
        if (str3 != null) {
            jVar.a(str3, 9);
        }
        Map<String, String> map = this.k;
        if (map != null) {
            jVar.a(map, 10);
        }
        String str4 = this.l;
        if (str4 != null) {
            jVar.a(str4, 11);
        }
        jVar.a(this.m, 12);
        String str5 = this.n;
        if (str5 != null) {
            jVar.a(str5, 13);
        }
        String str6 = this.o;
        if (str6 != null) {
            jVar.a(str6, 14);
        }
        String str7 = this.p;
        if (str7 != null) {
            jVar.a(str7, 15);
        }
        jVar.a(this.q, 16);
        String str8 = this.r;
        if (str8 != null) {
            jVar.a(str8, 17);
        }
        String str9 = this.s;
        if (str9 != null) {
            jVar.a(str9, 18);
        }
        String str10 = this.t;
        if (str10 != null) {
            jVar.a(str10, 19);
        }
        String str11 = this.u;
        if (str11 != null) {
            jVar.a(str11, 20);
        }
        String str12 = this.v;
        if (str12 != null) {
            jVar.a(str12, 21);
        }
        String str13 = this.w;
        if (str13 != null) {
            jVar.a(str13, 22);
        }
        String str14 = this.x;
        if (str14 != null) {
            jVar.a(str14, 23);
        }
    }

    static {
        byte[] bArr = new byte[1];
        y = bArr;
        bArr[0] = 0;
        HashMap hashMap = new HashMap();
        z = hashMap;
        hashMap.put("", "");
    }

    public final void a(i iVar) {
        this.a = iVar.a(this.a, 0, true);
        this.b = iVar.b(1, true);
        this.c = iVar.b(2, true);
        this.d = iVar.b(3, true);
        this.e = iVar.b(4, false);
        this.f = iVar.b(5, true);
        this.g = iVar.a(this.g, 6, true);
        this.h = iVar.c(7, true);
        this.i = iVar.b(8, false);
        this.j = iVar.b(9, false);
        this.k = (Map) iVar.a(z, 10, false);
        this.l = iVar.b(11, false);
        this.m = iVar.a(this.m, 12, false);
        this.n = iVar.b(13, false);
        this.o = iVar.b(14, false);
        this.p = iVar.b(15, false);
        this.q = iVar.a(this.q, 16, false);
        this.r = iVar.b(17, false);
        this.s = iVar.b(18, false);
        this.t = iVar.b(19, false);
        this.u = iVar.b(20, false);
        this.v = iVar.b(21, false);
        this.w = iVar.b(22, false);
        this.x = iVar.b(23, false);
    }
}
