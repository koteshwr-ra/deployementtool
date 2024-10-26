package com.ciot.networklib.function;

import com.ciot.networklib.function.RetryWithDelay;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import retrofit2.HttpException;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u001a\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0002\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001:\u0001\u0010B\u0007\b\u0016¢\u0006\u0002\u0010\u0004B\u000f\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u0017\b\u0016\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\nB\u001f\b\u0016\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u001c\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u00022\u000e\u0010\u000f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0002H\u0016R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/ciot/networklib/function/RetryWithDelay;", "Lio/reactivex/functions/Function;", "Lio/reactivex/Observable;", "", "()V", "retryDelayMillis", "", "(J)V", "maxRetryCount", "", "(IJ)V", "isFilterHttp", "", "(IJZ)V", "apply", "observable", "Wrapper", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: RetryWithDelay.kt */
public final class RetryWithDelay implements Function<Observable<? extends Throwable>, Observable<?>> {
    private boolean isFilterHttp;
    private int maxRetryCount = 3;
    private long retryDelayMillis = 3000;

    public RetryWithDelay() {
    }

    public RetryWithDelay(long j) {
        this.retryDelayMillis = j;
    }

    public RetryWithDelay(int i, long j) {
        this.maxRetryCount = i;
        this.retryDelayMillis = j;
    }

    public RetryWithDelay(int i, long j, boolean z) {
        this.maxRetryCount = i;
        this.retryDelayMillis = j;
        this.isFilterHttp = z;
    }

    public Observable<?> apply(Observable<? extends Throwable> observable) throws Exception {
        Intrinsics.checkNotNullParameter(observable, "observable");
        Observable<R> flatMap = observable.zipWith((ObservableSource<? extends U>) Observable.range(1, this.maxRetryCount + 1), (BiFunction<? super Object, ? super U, ? extends R>) new BiFunction() {
            public final Object apply(Object obj, Object obj2) {
                return RetryWithDelay.m89apply$lambda0(RetryWithDelay.this, (Throwable) obj, (Integer) obj2);
            }
        }).flatMap(new Function() {
            public final Object apply(Object obj) {
                return RetryWithDelay.m90apply$lambda1(RetryWithDelay.this, (RetryWithDelay.Wrapper) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(flatMap, "observable\n             …owable)\n                }");
        return flatMap;
    }

    /* access modifiers changed from: private */
    /* renamed from: apply$lambda-0  reason: not valid java name */
    public static final Wrapper m89apply$lambda0(RetryWithDelay retryWithDelay, Throwable th, Integer num) {
        Intrinsics.checkNotNullParameter(retryWithDelay, "this$0");
        Intrinsics.checkNotNullParameter(th, "t1");
        Intrinsics.checkNotNullParameter(num, "t2");
        return new Wrapper(retryWithDelay, num.intValue(), th);
    }

    /* access modifiers changed from: private */
    /* renamed from: apply$lambda-1  reason: not valid java name */
    public static final ObservableSource m90apply$lambda1(RetryWithDelay retryWithDelay, Wrapper wrapper) {
        Observable<Long> observable;
        Intrinsics.checkNotNullParameter(retryWithDelay, "this$0");
        Intrinsics.checkNotNullParameter(wrapper, "wrapper");
        Throwable throwable = wrapper.getThrowable();
        if ((!(throwable instanceof ConnectException) && !(throwable instanceof SocketTimeoutException) && !(throwable instanceof TimeoutException) && !(throwable instanceof HttpException)) || wrapper.getIndex() >= retryWithDelay.maxRetryCount + 1) {
            observable = Observable.error(wrapper.getThrowable());
        } else if (retryWithDelay.isFilterHttp) {
            observable = Observable.timer(retryWithDelay.retryDelayMillis, TimeUnit.MILLISECONDS);
        } else {
            observable = Observable.timer(retryWithDelay.retryDelayMillis * ((long) wrapper.getIndex()), TimeUnit.MILLISECONDS);
        }
        return observable;
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0006\b\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/ciot/networklib/function/RetryWithDelay$Wrapper;", "", "index", "", "throwable", "", "(Lcom/ciot/networklib/function/RetryWithDelay;ILjava/lang/Throwable;)V", "getIndex", "()I", "getThrowable", "()Ljava/lang/Throwable;", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: RetryWithDelay.kt */
    private final class Wrapper {
        private final int index;
        final /* synthetic */ RetryWithDelay this$0;
        private final Throwable throwable;

        public Wrapper(RetryWithDelay retryWithDelay, int i, Throwable th) {
            Intrinsics.checkNotNullParameter(th, "throwable");
            this.this$0 = retryWithDelay;
            this.index = i;
            this.throwable = th;
        }

        public final int getIndex() {
            return this.index;
        }

        public final Throwable getThrowable() {
            return this.throwable;
        }
    }
}
