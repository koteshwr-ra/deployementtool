package com.tencent.smtt.sdk;

import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Process;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class c {
    private static c f = null;
    private static boolean h = false;
    private String a = "EmergenceMsgPublisher";
    private String b = "tbs_emergence";
    private String c = "emergence_executed_ids";
    private String d = "emergence_ids";
    private String e = "";
    private final Map<Integer, a> g = new LinkedHashMap();

    public interface a {
        void a(String str);
    }

    public static class b {
        public int a = -1;
        public int b = -1;
        public String c = "";
        public long d = -1;

        public String toString() {
            return "{seqId=" + this.a + ", code=" + this.b + ", extra='" + this.c + '\'' + ", expired=" + this.d + '}';
        }
    }

    private c() {
    }

    public static c a() {
        if (f == null) {
            f = new c();
        }
        return f;
    }

    private void a(Context context, b bVar, a aVar) {
        String[] split;
        if (aVar != null) {
            a("Executed command: " + bVar.b + ", extra: " + bVar.c);
            aVar.a(bVar.c);
            SharedPreferences sharedPreferences = context.getSharedPreferences(this.b, 4);
            String string = sharedPreferences.getString(this.c, "");
            HashSet<Integer> hashSet = new HashSet<>();
            if (!TextUtils.isEmpty(string) && (split = string.split(",")) != null && split.length > 0) {
                try {
                    for (String parseInt : split) {
                        hashSet.add(Integer.valueOf(Integer.parseInt(parseInt)));
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
            hashSet.add(Integer.valueOf(bVar.a));
            StringBuilder sb = new StringBuilder();
            for (Integer append : hashSet) {
                sb.append(append);
                sb.append(",");
            }
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString(this.c, sb.toString());
            edit.commit();
        }
    }

    private void a(String str) {
    }

    public static String c(Context context) {
        try {
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
            if (runningAppProcesses == null) {
                return null;
            }
            int myPid = Process.myPid();
            for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
                if (next.pid == myPid) {
                    return next.processName;
                }
            }
            return "";
        } catch (Throwable unused) {
            return "";
        }
    }

    public Map<Integer, b> a(Context context) {
        String[] split;
        String str;
        HashMap hashMap = new HashMap();
        SharedPreferences sharedPreferences = context.getSharedPreferences(this.b, 0);
        if (context == null) {
            str = "Unexpected null context!";
        } else {
            String string = sharedPreferences.getString(this.d, "");
            if (TextUtils.isEmpty(string)) {
                str = "Empty local emergence ids!";
            } else {
                a("Local emergence ids: " + string);
                String[] split2 = string.split(";");
                if (split2 != null) {
                    for (String str2 : split2) {
                        if (!TextUtils.isEmpty(str2) && (split = str2.split(",")) != null && split.length == 4) {
                            b bVar = new b();
                            try {
                                bVar.a = Integer.parseInt(split[0]);
                                bVar.b = Integer.parseInt(split[1]);
                                bVar.c = String.valueOf(split[2]);
                                bVar.d = Long.parseLong(split[3]);
                            } catch (Throwable unused) {
                            }
                            if (System.currentTimeMillis() < bVar.d) {
                                hashMap.put(Integer.valueOf(bVar.a), bVar);
                            }
                        }
                    }
                }
                String string2 = sharedPreferences.getString(this.c, "");
                a("Executed ids: " + string2);
                String[] split3 = string2.split(",");
                if (split3 != null) {
                    ArrayList arrayList = new ArrayList();
                    for (String str3 : split3) {
                        if (!TextUtils.isEmpty(str3)) {
                            int i = -1;
                            try {
                                i = Integer.parseInt(str3);
                            } catch (Throwable th) {
                                th.printStackTrace();
                            }
                            if (i > 0) {
                                arrayList.add(Integer.valueOf(i));
                            }
                        }
                    }
                    for (int i2 = 0; i2 < arrayList.size(); i2++) {
                        hashMap.remove(arrayList.get(i2));
                    }
                }
                return hashMap;
            }
        }
        a(str);
        return hashMap;
    }

    public void a(Context context, Integer num, a aVar) {
        this.e = c(context);
        Map<Integer, b> a2 = a(context);
        for (Integer num2 : a2.keySet()) {
            if (a2.get(num2).b == num.intValue()) {
                a(context, a2.get(num), aVar);
                return;
            }
        }
        if (!h) {
            this.g.put(num, aVar);
            a("Emergence config did not arrived yet, code[" + num + "] has been suspended");
        }
    }

    public void b(Context context) {
        Map<Integer, b> a2 = a(context);
        a("On notify emergence, validate commands: " + a2);
        h = true;
        for (Integer next : this.g.keySet()) {
            for (Integer next2 : a2.keySet()) {
                if (a2.get(next2).b == next.intValue()) {
                    a(context, a2.get(next2), this.g.get(next));
                }
            }
        }
        if (!this.g.isEmpty()) {
            a("Emergency code[" + this.g.keySet() + "] did not happen, clear suspended queries");
            this.g.clear();
        }
    }
}
