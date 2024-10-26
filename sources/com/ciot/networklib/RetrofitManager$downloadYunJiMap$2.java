package com.ciot.networklib;

import com.ciot.base.util.MyLogUtils;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import okhttp3.ResponseBody;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0002H\u0016J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, d2 = {"com/ciot/networklib/RetrofitManager$downloadYunJiMap$2", "Lio/reactivex/Observer;", "Lokhttp3/ResponseBody;", "onComplete", "", "onError", "e", "", "onNext", "t", "onSubscribe", "d", "Lio/reactivex/disposables/Disposable;", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: RetrofitManager.kt */
public final class RetrofitManager$downloadYunJiMap$2 implements Observer<ResponseBody> {
    final /* synthetic */ RetrofitManager this$0;

    public void onComplete() {
    }

    public void onSubscribe(Disposable disposable) {
        Intrinsics.checkNotNullParameter(disposable, "d");
    }

    RetrofitManager$downloadYunJiMap$2(RetrofitManager retrofitManager) {
        this.this$0 = retrofitManager;
    }

    public void onNext(ResponseBody responseBody) {
        Intrinsics.checkNotNullParameter(responseBody, "t");
        String str = new String(responseBody.bytes(), Charsets.UTF_8);
        MyLogUtils.Logd("NETWORK_TAG", "地图下载:" + str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.getInt("errcode");
            String string = jSONObject.getString("errmsg");
            if (i != 0) {
                MyLogUtils.Logd("NETWORK_TAG", "地图下载失败 error: " + i + " errorMsg: " + string);
                RetrofitManager retrofitManager = this.this$0;
                retrofitManager.replySrosNotification(false, "地图下载失败 error: " + i + " errorMsg: " + string);
                return;
            }
            MyLogUtils.Logd("NETWORK_TAG", "地图下载成功！！！");
            this.this$0.replySrosNotification(true, "");
        } catch (Exception e) {
            RetrofitManager retrofitManager2 = this.this$0;
            retrofitManager2.replySrosNotification(false, "地图下载失败 catch Exception: " + e);
            MyLogUtils.Logd("NETWORK_TAG", "地图下载失败 catch Exception: " + e);
        }
    }

    public void onError(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "e");
        MyLogUtils.Loge("地图下载: " + th);
        RetrofitManager retrofitManager = this.this$0;
        retrofitManager.replySrosNotification(false, "地图下载失败 onError: " + th);
    }
}
