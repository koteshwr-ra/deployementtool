package com.tencent.smtt.sdk;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.FrameLayout;
import com.tencent.smtt.export.external.DexLoader;
import dalvik.system.DexClassLoader;

class v {
    protected DexLoader a = null;

    public v(DexLoader dexLoader) {
        this.a = dexLoader;
    }

    public Object a(Context context) {
        DexLoader dexLoader = this.a;
        return dexLoader.newInstance("com.tencent.tbs.player.TbsPlayerProxy", new Class[]{Context.class, DexClassLoader.class}, context, dexLoader.getClassLoader());
    }

    public void a(Object obj) {
        this.a.invokeMethod(obj, "com.tencent.tbs.player.TbsPlayerProxy", "onUserStateChanged", new Class[0], new Object[0]);
    }

    public void a(Object obj, Activity activity, int i) {
        this.a.invokeMethod(obj, "com.tencent.tbs.player.TbsPlayerProxy", "onActivity", new Class[]{Activity.class, Integer.TYPE}, activity, Integer.valueOf(i));
    }

    public boolean a(Object obj, Bundle bundle, FrameLayout frameLayout, Object obj2) {
        Object obj3;
        DexLoader dexLoader = this.a;
        if (obj2 != null) {
            obj3 = dexLoader.invokeMethod(obj, "com.tencent.tbs.player.TbsPlayerProxy", "play", new Class[]{Bundle.class, FrameLayout.class, Object.class}, bundle, frameLayout, obj2);
        } else {
            obj3 = dexLoader.invokeMethod(obj, "com.tencent.tbs.player.TbsPlayerProxy", "play", new Class[]{Bundle.class, FrameLayout.class}, bundle, frameLayout);
        }
        if (obj3 instanceof Boolean) {
            return ((Boolean) obj3).booleanValue();
        }
        return false;
    }
}
