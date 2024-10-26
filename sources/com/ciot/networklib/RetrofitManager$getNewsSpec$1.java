package com.ciot.networklib;

import com.ciot.base.util.MyLogUtils;
import com.ciot.networklib.bean.NewsSpecBean;
import com.ciot.networklib.function.NewsSpecLiveData;
import com.google.gson.Gson;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import okhttp3.ResponseBody;

@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0012\u0010\u0007\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\t"}, d2 = {"com/ciot/networklib/RetrofitManager$getNewsSpec$1", "Lcom/ciot/networklib/BaseObserver;", "Lokhttp3/ResponseBody;", "onError", "", "e", "", "onSuccess", "response", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: RetrofitManager.kt */
public final class RetrofitManager$getNewsSpec$1 extends BaseObserver<ResponseBody> {
    final /* synthetic */ RetrofitManager this$0;

    RetrofitManager$getNewsSpec$1(RetrofitManager retrofitManager) {
        this.this$0 = retrofitManager;
    }

    public void onSuccess(ResponseBody responseBody) {
        try {
            Intrinsics.checkNotNull(responseBody);
            String str = new String(responseBody.bytes(), Charsets.UTF_8);
            MyLogUtils.Loge(this.this$0.TAG, str);
            Object fromJson = new Gson().fromJson(str, new RetrofitManager$getNewsSpec$1$onSuccess$newsSpec$1().getType());
            NewsSpecBean newsSpecBean = fromJson instanceof NewsSpecBean ? (NewsSpecBean) fromJson : null;
            NewsSpecLiveData newsSpecLiveData = NewsSpecLiveData.Companion.get();
            Intrinsics.checkNotNull(newsSpecBean);
            newsSpecLiveData.postValue(newsSpecBean);
        } catch (Exception unused) {
            MyLogUtils.Loge(this.this$0.TAG, "资讯详情解析异常");
        }
    }

    public void onError(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "e");
        super.onError(th);
        String access$getTAG$p = this.this$0.TAG;
        MyLogUtils.Loge(access$getTAG$p, th + "资讯详情请求失败");
    }
}
