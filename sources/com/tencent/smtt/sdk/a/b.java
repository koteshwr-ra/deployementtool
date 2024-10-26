package com.tencent.smtt.sdk.a;

import org.json.JSONObject;

public class b {
    private int a;
    private int b;
    private String c;
    private long d;

    private b() {
    }

    public static b a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        b bVar = new b();
        bVar.a = jSONObject.optInt("id", -1);
        bVar.b = jSONObject.optInt("cmd_id", -1);
        bVar.c = jSONObject.optString("ext_params", "");
        bVar.d = jSONObject.optLong("expiration", 0) * 1000;
        return bVar;
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public long d() {
        return this.d;
    }

    public boolean e() {
        return System.currentTimeMillis() > this.d;
    }

    public String toString() {
        return "[id=" + this.a + ", cmd=" + this.b + ", extra='" + this.c + '\'' + ", expiration=" + a.a(this.d) + ']';
    }
}
