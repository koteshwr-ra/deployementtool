package com.ciot.networklib.oss;

import com.ciot.networklib.RetrofitManager;
import io.reactivex.functions.Function;
import okhttp3.RequestBody;

/* renamed from: com.ciot.networklib.oss.-$$Lambda$OssHelper$rduaCOFWtnnahVnyzkAivYm32T4  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$OssHelper$rduaCOFWtnnahVnyzkAivYm32T4 implements Function {
    public static final /* synthetic */ $$Lambda$OssHelper$rduaCOFWtnnahVnyzkAivYm32T4 INSTANCE = new $$Lambda$OssHelper$rduaCOFWtnnahVnyzkAivYm32T4();

    private /* synthetic */ $$Lambda$OssHelper$rduaCOFWtnnahVnyzkAivYm32T4() {
    }

    public final Object apply(Object obj) {
        return RetrofitManager.Companion.getInstance().getCiotSemanticIntentApiService().uploadRecord((RequestBody) obj);
    }
}
