package com.ciot.base.util;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u0006\u0010\t\u001a\u00020\u0006R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/ciot/base/util/DisposeUtil;", "", "()V", "mCompositeDisposable", "Lio/reactivex/disposables/CompositeDisposable;", "addSubscription", "", "disposable", "Lio/reactivex/disposables/Disposable;", "onUnsubscribe", "library-base_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: DisposeUtil.kt */
public final class DisposeUtil {
    public static final DisposeUtil INSTANCE = new DisposeUtil();
    private static CompositeDisposable mCompositeDisposable;

    private DisposeUtil() {
    }

    public final void onUnsubscribe() {
        CompositeDisposable compositeDisposable = mCompositeDisposable;
        if (compositeDisposable != null) {
            Intrinsics.checkNotNull(compositeDisposable);
            compositeDisposable.clear();
        }
    }

    public final void addSubscription(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        if (disposable != null) {
            CompositeDisposable compositeDisposable = mCompositeDisposable;
            Intrinsics.checkNotNull(compositeDisposable);
            compositeDisposable.add(disposable);
        }
    }
}
