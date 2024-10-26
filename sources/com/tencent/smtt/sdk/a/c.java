package com.tencent.smtt.sdk.a;

import android.text.TextUtils;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class c {
    private String a;
    private String b;
    private Integer c;
    private String d;
    private String e;
    private Integer f;
    private Integer g;
    private List<Integer> h;

    public String a() {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            if (!TextUtils.isEmpty(this.a)) {
                jSONObject2.put("PP", this.a);
            }
            if (!TextUtils.isEmpty(this.b)) {
                jSONObject2.put("PPVN", this.b);
            }
            if (this.c != null) {
                jSONObject2.put("ADRV", this.c);
            }
            if (!TextUtils.isEmpty(this.d)) {
                jSONObject2.put("MODEL", this.d);
            }
            if (!TextUtils.isEmpty(this.e)) {
                jSONObject2.put("NAME", this.e);
            }
            if (this.f != null) {
                jSONObject2.put("SDKVC", this.f);
            }
            if (this.g != null) {
                jSONObject2.put("COMPVC", this.g);
            }
            jSONObject.put("terminal_params", jSONObject2);
            if (this.h != null) {
                JSONArray jSONArray = new JSONArray();
                for (int i = 0; i < this.h.size(); i++) {
                    jSONArray.put(this.h.get(i));
                }
                jSONObject.put("ids", jSONArray);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject.toString();
    }

    public void a(Integer num) {
        this.c = num;
    }

    public void a(String str) {
        this.a = str;
    }

    public void a(List<Integer> list) {
        this.h = list;
    }

    public void b(Integer num) {
        this.f = num;
    }

    public void b(String str) {
        this.b = str;
    }

    public void c(Integer num) {
        this.g = num;
    }

    public void c(String str) {
        this.d = str;
    }

    public void d(String str) {
        this.e = str;
    }
}
