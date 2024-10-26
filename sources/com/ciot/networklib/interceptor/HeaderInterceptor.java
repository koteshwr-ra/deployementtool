package com.ciot.networklib.interceptor;

import com.ciot.base.constant.HttpConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/ciot/networklib/interceptor/HeaderInterceptor;", "Lokhttp3/Interceptor;", "()V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: HeaderInterceptor.kt */
public final class HeaderInterceptor implements Interceptor {
    public Response intercept(Interceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Request request = chain.request();
        Request.Builder newBuilder = request.newBuilder();
        newBuilder.addHeader("Content-type", "application/json; charset=utf-8");
        String host = request.url().host();
        String httpUrl = request.url().toString();
        boolean z = true;
        if (host.length() > 0) {
            CharSequence charSequence = httpUrl;
            if (StringsKt.contains$default(charSequence, (CharSequence) HttpConstant.COLLECTIONS_WEBSITE, false, 2, (Object) null) || StringsKt.contains$default(charSequence, (CharSequence) HttpConstant.UNCOLLECTIONS_WEBSITE, false, 2, (Object) null) || StringsKt.contains$default(charSequence, (CharSequence) HttpConstant.ARTICLE_WEBSITE, false, 2, (Object) null) || StringsKt.contains$default(charSequence, (CharSequence) HttpConstant.TODO_WEBSITE, false, 2, (Object) null) || StringsKt.contains$default(charSequence, (CharSequence) HttpConstant.COIN_WEBSITE, false, 2, (Object) null)) {
                if ("".length() > 0) {
                }
                if ("".length() <= 0) {
                    z = false;
                }
                if (z) {
                    newBuilder.addHeader("Cookie", "");
                }
            }
        }
        return chain.proceed(newBuilder.build());
    }
}
