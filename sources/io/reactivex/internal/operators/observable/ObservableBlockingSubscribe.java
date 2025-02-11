package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.functions.Functions;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.observers.LambdaObserver;
import io.reactivex.internal.util.BlockingHelper;
import io.reactivex.internal.util.BlockingIgnoringReceiver;
import io.reactivex.internal.util.ExceptionHelper;

public final class ObservableBlockingSubscribe {
    private ObservableBlockingSubscribe() {
        throw new IllegalStateException("No instances!");
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0017  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T> void subscribe(io.reactivex.ObservableSource<? extends T> r3, io.reactivex.Observer<? super T> r4) {
        /*
            java.util.concurrent.LinkedBlockingQueue r0 = new java.util.concurrent.LinkedBlockingQueue
            r0.<init>()
            io.reactivex.internal.observers.BlockingObserver r1 = new io.reactivex.internal.observers.BlockingObserver
            r1.<init>(r0)
            r4.onSubscribe(r1)
            r3.subscribe(r1)
        L_0x0010:
            boolean r3 = r1.isDisposed()
            if (r3 == 0) goto L_0x0017
            goto L_0x003a
        L_0x0017:
            java.lang.Object r3 = r0.poll()
            if (r3 != 0) goto L_0x002a
            java.lang.Object r3 = r0.take()     // Catch:{ InterruptedException -> 0x0022 }
            goto L_0x002a
        L_0x0022:
            r3 = move-exception
            r1.dispose()
            r4.onError(r3)
            return
        L_0x002a:
            boolean r2 = r1.isDisposed()
            if (r2 != 0) goto L_0x003a
            java.lang.Object r2 = io.reactivex.internal.observers.BlockingObserver.TERMINATED
            if (r3 == r2) goto L_0x003a
            boolean r3 = io.reactivex.internal.util.NotificationLite.acceptFull((java.lang.Object) r3, r4)
            if (r3 == 0) goto L_0x0010
        L_0x003a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.observable.ObservableBlockingSubscribe.subscribe(io.reactivex.ObservableSource, io.reactivex.Observer):void");
    }

    public static <T> void subscribe(ObservableSource<? extends T> observableSource) {
        BlockingIgnoringReceiver blockingIgnoringReceiver = new BlockingIgnoringReceiver();
        LambdaObserver lambdaObserver = new LambdaObserver(Functions.emptyConsumer(), blockingIgnoringReceiver, blockingIgnoringReceiver, Functions.emptyConsumer());
        observableSource.subscribe(lambdaObserver);
        BlockingHelper.awaitForComplete(blockingIgnoringReceiver, lambdaObserver);
        Throwable th = blockingIgnoringReceiver.error;
        if (th != null) {
            throw ExceptionHelper.wrapOrThrow(th);
        }
    }

    public static <T> void subscribe(ObservableSource<? extends T> observableSource, Consumer<? super T> consumer, Consumer<? super Throwable> consumer2, Action action) {
        ObjectHelper.requireNonNull(consumer, "onNext is null");
        ObjectHelper.requireNonNull(consumer2, "onError is null");
        ObjectHelper.requireNonNull(action, "onComplete is null");
        subscribe(observableSource, new LambdaObserver(consumer, consumer2, action, Functions.emptyConsumer()));
    }
}
