package com.tencent.smtt.sdk;

import android.content.Context;
import android.util.Log;
import com.tencent.smtt.export.external.DexLoader;

public class TbsMediaFactory {
    private Context a = null;
    private u b = null;
    private DexLoader c = null;

    public TbsMediaFactory(Context context) {
        this.a = context.getApplicationContext();
        a();
    }

    private void a() {
        if (this.a == null) {
            Log.e("TbsVideo", "TbsVideo needs context !!");
            return;
        }
        if (this.b == null) {
            f.a(true).a(this.a, false, false);
            u a2 = f.a(true).a();
            this.b = a2;
            if (a2 != null) {
                this.c = a2.b();
            }
        }
        if (this.b == null || this.c == null) {
            throw new RuntimeException("tbs core dex(s) load failure !!!");
        }
    }

    public TbsMediaPlayer createPlayer() {
        DexLoader dexLoader;
        if (this.b != null && (dexLoader = this.c) != null) {
            return new TbsMediaPlayer(new p(dexLoader, this.a));
        }
        throw new RuntimeException("tbs core dex(s) did not loaded !!!");
    }
}
