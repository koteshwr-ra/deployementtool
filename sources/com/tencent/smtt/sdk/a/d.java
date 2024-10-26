package com.tencent.smtt.sdk.a;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class d {
    private int a;
    private long b;
    private List<b> c;

    private d() {
    }

    public static d a(String str) {
        d dVar;
        JSONException e;
        if (str == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            dVar = new d();
            try {
                dVar.a = jSONObject.optInt("ret_code", -1);
                dVar.b = jSONObject.optLong("next_req_interval", 1000);
                JSONArray optJSONArray = jSONObject.optJSONArray("cmds");
                if (optJSONArray != null) {
                    dVar.c = new ArrayList();
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        b a2 = b.a(optJSONArray.optJSONObject(i));
                        if (a2 != null) {
                            dVar.c.add(a2);
                        }
                    }
                }
            } catch (JSONException e2) {
                e = e2;
                e.printStackTrace();
                return dVar;
            }
        } catch (JSONException e3) {
            e = e3;
            dVar = null;
            e.printStackTrace();
            return dVar;
        }
        return dVar;
    }

    public int a() {
        return this.a;
    }

    public long b() {
        return this.b;
    }

    public List<b> c() {
        return this.c;
    }
}
