package com.ciot.networklib;

import com.ciot.realm.db.common.RecordResponse;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0014J\b\u0010\u0007\u001a\u00020\u0004H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nH\u0016J\u0012\u0010\u000b\u001a\u00020\u00042\b\u0010\f\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\r"}, d2 = {"com/ciot/networklib/RetrofitManager$verifyInviteCode$2", "Lcom/ciot/networklib/BaseObserver;", "Lcom/ciot/realm/db/common/RecordResponse;", "onChildSubscribe", "", "d", "Lio/reactivex/disposables/Disposable;", "onComplete", "onError", "e", "", "onSuccess", "response", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: RetrofitManager.kt */
public final class RetrofitManager$verifyInviteCode$2 extends BaseObserver<RecordResponse> {
    final /* synthetic */ Function1<RecordResponse, Unit> $callback;
    final /* synthetic */ RetrofitManager this$0;

    public void onComplete() {
    }

    RetrofitManager$verifyInviteCode$2(RetrofitManager retrofitManager, Function1<? super RecordResponse, Unit> function1) {
        this.this$0 = retrofitManager;
        this.$callback = function1;
    }

    /* access modifiers changed from: protected */
    public void onChildSubscribe(Disposable disposable) {
        Intrinsics.checkNotNullParameter(disposable, "d");
        CompositeDisposable access$getMCompositeDisposable$p = this.this$0.mCompositeDisposable;
        if (access$getMCompositeDisposable$p != null) {
            access$getMCompositeDisposable$p.add(disposable);
        }
    }

    public void onSuccess(RecordResponse recordResponse) {
        this.$callback.invoke(recordResponse);
    }

    public void onError(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "e");
        super.onError(th);
        this.$callback.invoke(null);
    }
}
