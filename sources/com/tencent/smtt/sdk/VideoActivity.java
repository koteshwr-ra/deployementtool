package com.tencent.smtt.sdk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.tencent.tbs.video.interfaces.a;

public class VideoActivity extends Activity {
    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        r.a(this).a(i, i2, intent);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.requestWindowFeature(1);
        super.getWindow().setFormat(-3);
        Intent intent = super.getIntent();
        Bundle bundleExtra = intent != null ? intent.getBundleExtra("extraData") : null;
        if (bundleExtra != null) {
            bundleExtra.putInt("callMode", 1);
            r.a(super.getApplicationContext()).a((String) null, bundleExtra, (a) null);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        r.a(this).a(this, 4);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        r.a(this).a(this, 3);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        r.a(this).a(this, 2);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        r.a(this).a(this, 1);
    }
}
