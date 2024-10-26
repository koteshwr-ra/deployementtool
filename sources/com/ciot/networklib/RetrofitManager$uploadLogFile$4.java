package com.ciot.networklib;

import com.blankj.utilcode.util.FileUtils;
import com.ciot.base.util.MyLogUtils;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.ResponseBody;

@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0014J\b\u0010\u0007\u001a\u00020\u0004H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0002H\u0016¨\u0006\r"}, d2 = {"com/ciot/networklib/RetrofitManager$uploadLogFile$4", "Lcom/ciot/networklib/BaseObserver;", "Lokhttp3/ResponseBody;", "onChildSubscribe", "", "d", "Lio/reactivex/disposables/Disposable;", "onComplete", "onError", "e", "", "onSuccess", "t", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: RetrofitManager.kt */
public final class RetrofitManager$uploadLogFile$4 extends BaseObserver<ResponseBody> {
    final /* synthetic */ AtomicReference<String> $uploadFilePath;
    final /* synthetic */ RetrofitManager this$0;

    /* access modifiers changed from: protected */
    public void onChildSubscribe(Disposable disposable) {
        Intrinsics.checkNotNullParameter(disposable, "d");
    }

    RetrofitManager$uploadLogFile$4(RetrofitManager retrofitManager, AtomicReference<String> atomicReference) {
        this.this$0 = retrofitManager;
        this.$uploadFilePath = atomicReference;
    }

    public void onSuccess(ResponseBody responseBody) {
        Intrinsics.checkNotNullParameter(responseBody, "t");
        String access$getTAG$p = this.this$0.TAG;
        MyLogUtils.Logd(access$getTAG$p, "uploadLogFile: 日志上传成功: " + responseBody);
        this.this$0.replySrosNotification(true, "");
        FileUtils.delete(this.$uploadFilePath.get());
        FileUtils.notifySystemToScan(this.$uploadFilePath.get());
    }

    public void onError(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "e");
        super.onError(th);
        RetrofitManager retrofitManager = this.this$0;
        retrofitManager.replySrosNotification(true, "uploadLogFile: 日志上传出错 " + th);
        String access$getTAG$p = this.this$0.TAG;
        MyLogUtils.Loge(access$getTAG$p, "uploadLogFile: 日志上传出错，删除zip文件: " + th);
        FileUtils.delete(this.$uploadFilePath.get());
        FileUtils.notifySystemToScan(this.$uploadFilePath.get());
    }

    public void onComplete() {
        MyLogUtils.Logd(this.this$0.TAG, "uploadLogFile: 日志上传完成!!!");
    }
}
