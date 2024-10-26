package com.ciot.networklib;

import com.ciot.base.util.MyLogUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.ResponseBody;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0012\u0010\b\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\n"}, d2 = {"com/ciot/networklib/RetrofitManager$uploadTaskLogcat$1", "Lcom/ciot/networklib/BaseObserver;", "Lokhttp3/ResponseBody;", "onComplete", "", "onError", "e", "", "onSuccess", "response", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: RetrofitManager.kt */
public final class RetrofitManager$uploadTaskLogcat$1 extends BaseObserver<ResponseBody> {
    public void onComplete() {
    }

    RetrofitManager$uploadTaskLogcat$1() {
    }

    public void onSuccess(ResponseBody responseBody) {
        MyLogUtils.Logd("NETWORK_TAG", "===上传消毒任务日志onSuccess===");
    }

    public void onError(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "e");
        MyLogUtils.Loge("NETWORK_TAG", "===上传onError===" + th);
    }
}
