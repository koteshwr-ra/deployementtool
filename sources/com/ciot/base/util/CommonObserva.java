package com.ciot.base.util;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00028\u0000¢\u0006\u0002\u0010\tJ\u0006\u0010\n\u001a\u00020\u0007J\u0016\u0010\u000b\u001a\u00020\u00072\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0016R\u0016\u0010\u0004\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/ciot/base/util/CommonObserva;", "T", "Lio/reactivex/ObservableOnSubscribe;", "()V", "mEmitter", "Lio/reactivex/ObservableEmitter;", "setInfo", "", "t", "(Ljava/lang/Object;)V", "setOnComplete", "subscribe", "emitter", "library-base_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: CommonObserva.kt */
public final class CommonObserva<T> implements ObservableOnSubscribe<T> {
    private ObservableEmitter<T> mEmitter;

    public void subscribe(ObservableEmitter<T> observableEmitter) throws Exception {
        Intrinsics.checkNotNullParameter(observableEmitter, "emitter");
        this.mEmitter = observableEmitter;
    }

    public final void setInfo(T t) {
        ObservableEmitter<T> observableEmitter = this.mEmitter;
        if (observableEmitter != null) {
            observableEmitter.onNext(t);
        }
    }

    public final void setOnComplete() {
        ObservableEmitter<T> observableEmitter = this.mEmitter;
        if (observableEmitter != null) {
            observableEmitter.onComplete();
        }
    }
}
