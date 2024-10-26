package com.ciot.networklib;

import com.ciot.base.util.MyLogUtils;
import io.reactivex.disposables.Disposable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0014J\b\u0010\u0007\u001a\u00020\u0004H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0002H\u0016¨\u0006\r"}, d2 = {"com/ciot/networklib/RetrofitManager$loadPatrolTaskFromServer$2", "Lcom/ciot/networklib/BaseObserver;", "", "onChildSubscribe", "", "d", "Lio/reactivex/disposables/Disposable;", "onComplete", "onError", "e", "", "onSuccess", "b", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: RetrofitManager.kt */
public final class RetrofitManager$loadPatrolTaskFromServer$2 extends BaseObserver<Boolean> {
    /* access modifiers changed from: protected */
    public void onChildSubscribe(Disposable disposable) {
        Intrinsics.checkNotNullParameter(disposable, "d");
    }

    public void onComplete() {
    }

    public void onSuccess(boolean z) {
    }

    RetrofitManager$loadPatrolTaskFromServer$2() {
    }

    public /* bridge */ /* synthetic */ void onSuccess(Object obj) {
        onSuccess(((Boolean) obj).booleanValue());
    }

    public void onError(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "e");
        super.onError(th);
        MyLogUtils.Loge("SrosHandlerCallback: " + th);
    }
}
