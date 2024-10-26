package com.tencent.smtt.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.tbs.video.interfaces.IUserStateChangedListener;
import com.tencent.tbs.video.interfaces.a;

class r {
    private static r e;
    t a = null;
    Context b;
    a c;
    IUserStateChangedListener d;

    private r(Context context) {
        this.b = context.getApplicationContext();
        this.a = new t(this.b);
    }

    public static synchronized r a(Context context) {
        r rVar;
        synchronized (r.class) {
            if (e == null) {
                e = new r(context);
            }
            rVar = e;
        }
        return rVar;
    }

    public void a(int i, int i2, Intent intent) {
        a aVar = this.c;
        if (aVar != null) {
            aVar.a(i, i2, intent);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Activity activity, int i) {
        this.a.a(activity, i);
    }

    public boolean a() {
        this.a.a();
        return this.a.b();
    }

    public boolean a(String str, Bundle bundle, a aVar) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (!TextUtils.isEmpty(str)) {
            bundle.putString("videoUrl", str);
        }
        if (aVar != null) {
            this.a.a();
            if (!this.a.b()) {
                return false;
            }
            this.c = aVar;
            AnonymousClass1 r1 = new IUserStateChangedListener() {
                public void onUserStateChanged() {
                    r.this.a.c();
                }
            };
            this.d = r1;
            this.c.a(r1);
            bundle.putInt("callMode", 3);
        } else {
            bundle.putInt("callMode", 1);
        }
        this.a.a(bundle, (Object) aVar == null ? null : this);
        return true;
    }
}
