package com.example.sroslibrary.bean;

import io.reactivex.ObservableEmitter;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0005¢\u0006\u0002\u0010\u0002R\"\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u000b"}, d2 = {"Lcom/example/sroslibrary/bean/QueryMarkerLiveData;", "", "()V", "emitter", "Lio/reactivex/ObservableEmitter;", "", "getEmitter", "()Lio/reactivex/ObservableEmitter;", "setEmitter", "(Lio/reactivex/ObservableEmitter;)V", "Companion", "library-sroslibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: QueryMarkerLiveData.kt */
public final class QueryMarkerLiveData {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final Lazy<QueryMarkerLiveData> instance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, QueryMarkerLiveData$Companion$instance$2.INSTANCE);
    private ObservableEmitter<Boolean> emitter;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/example/sroslibrary/bean/QueryMarkerLiveData$Companion;", "", "()V", "instance", "Lcom/example/sroslibrary/bean/QueryMarkerLiveData;", "getInstance", "()Lcom/example/sroslibrary/bean/QueryMarkerLiveData;", "instance$delegate", "Lkotlin/Lazy;", "library-sroslibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: QueryMarkerLiveData.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final QueryMarkerLiveData getInstance() {
            return (QueryMarkerLiveData) QueryMarkerLiveData.instance$delegate.getValue();
        }
    }

    public final ObservableEmitter<Boolean> getEmitter() {
        return this.emitter;
    }

    public final void setEmitter(ObservableEmitter<Boolean> observableEmitter) {
        this.emitter = observableEmitter;
    }
}
