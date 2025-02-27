package com.ciot.networklib.interceptor;

import com.ciot.base.util.ContextUtil;
import com.ciot.base.util.NetworkUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.Response;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/ciot/networklib/interceptor/NetCacheInterceptor;", "Lokhttp3/Interceptor;", "()V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: NetCacheInterceptor.kt */
public final class NetCacheInterceptor implements Interceptor {
    public Response intercept(Interceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Response proceed = chain.proceed(chain.request());
        if (NetworkUtil.isNetworkAvailable(ContextUtil.getContext())) {
            Response.Builder newBuilder = proceed.newBuilder();
            newBuilder.header("Cache-Control", "public, max-age=" + 180).removeHeader("Retrofit").build();
        }
        return proceed;
    }
}
