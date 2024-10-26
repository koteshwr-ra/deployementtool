package com.ciot.networklib;

import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.GsonUtils;
import com.limpoxe.support.servicemanager.util.ParamUtil;
import io.reactivex.disposables.Disposable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import okhttp3.ResponseBody;

@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0014J\b\u0010\u0007\u001a\u00020\u0004H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0002H\u0016¨\u0006\r"}, d2 = {"com/ciot/networklib/RetrofitManager$bindSimInfo$1", "Lcom/ciot/networklib/BaseObserver;", "Lokhttp3/ResponseBody;", "onChildSubscribe", "", "d", "Lio/reactivex/disposables/Disposable;", "onComplete", "onError", "e", "", "onSuccess", "response", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: RetrofitManager.kt */
public final class RetrofitManager$bindSimInfo$1 extends BaseObserver<ResponseBody> {
    final /* synthetic */ RetrofitManager this$0;

    /* access modifiers changed from: protected */
    public void onChildSubscribe(Disposable disposable) {
        Intrinsics.checkNotNullParameter(disposable, "d");
    }

    public void onComplete() {
    }

    RetrofitManager$bindSimInfo$1(RetrofitManager retrofitManager) {
        this.this$0 = retrofitManager;
    }

    public void onSuccess(ResponseBody responseBody) {
        Intrinsics.checkNotNullParameter(responseBody, "response");
        try {
            JSONObject parseObject = JSON.parseObject(new String(responseBody.bytes(), Charsets.UTF_8));
            Log.d("JIN_YU", "getSimInfo onNext->" + parseObject);
            org.json.JSONObject jSONObject = new org.json.JSONObject(parseObject.getJSONObject("results").getJSONObject("sim_info").getJSONArray(ParamUtil.result).getJSONObject(1));
            Log.d("JIN_YU", "getSimInfo simInfo->" + GsonUtils.toJson(jSONObject));
            String optString = jSONObject.optString("IMSI");
            String optString2 = jSONObject.optString("CCID");
            Log.d("JIN_YU", "getSimInfo IMSI=" + optString + " CCID=" + optString2);
            RetrofitManager retrofitManager = this.this$0;
            Intrinsics.checkNotNullExpressionValue(optString, "imsi");
            retrofitManager.setIMSI(optString);
            RetrofitManager retrofitManager2 = this.this$0;
            Intrinsics.checkNotNullExpressionValue(optString2, "ccid");
            retrofitManager2.setCCID(optString2);
        } catch (Exception e) {
            Log.e("JIN_YU", "parse error->" + e.getMessage());
        }
    }

    public void onError(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "e");
        super.onError(th);
        Log.e("JIN_YU", "getSimInfo onError->" + th.getMessage());
    }
}
