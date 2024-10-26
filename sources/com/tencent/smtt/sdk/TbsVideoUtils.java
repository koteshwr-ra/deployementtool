package com.tencent.smtt.sdk;

import android.content.Context;
import com.tencent.smtt.export.external.DexLoader;

public class TbsVideoUtils {
    private static s a;

    private static void a(Context context) {
        synchronized (TbsVideoUtils.class) {
            if (a == null) {
                f.a(true).a(context, false, false);
                u a2 = f.a(true).a();
                DexLoader dexLoader = null;
                if (a2 != null) {
                    dexLoader = a2.b();
                }
                if (dexLoader != null) {
                    a = new s(dexLoader);
                }
            }
        }
    }

    public static void deleteVideoCache(Context context, String str) {
        a(context);
        s sVar = a;
        if (sVar != null) {
            sVar.a(context, str);
        }
    }

    public static String getCurWDPDecodeType(Context context) {
        a(context);
        s sVar = a;
        return sVar != null ? sVar.a(context) : "";
    }
}
