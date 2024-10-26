package mc.csst.com.selfchassis.utils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.TimeUnit;

public class RxTimer {
    /* access modifiers changed from: private */
    public Disposable mDisposable;

    public interface RxAction {
        void action(long j);
    }

    public void timer(long j, final RxAction rxAction) {
        Observable.timer(j, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Long>() {
            public void onSubscribe(Disposable disposable) {
                Disposable unused = RxTimer.this.mDisposable = disposable;
            }

            public void onNext(Long l) {
                RxAction rxAction = rxAction;
                if (rxAction != null) {
                    rxAction.action(l.longValue());
                }
            }

            public void onError(Throwable th) {
                RxTimer.this.cancel();
            }

            public void onComplete() {
                RxTimer.this.cancel();
            }
        });
    }

    public void interval(long j, final RxAction rxAction) {
        Observable.interval(j, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Long>() {
            public void onComplete() {
            }

            public void onError(Throwable th) {
            }

            public void onSubscribe(Disposable disposable) {
                Disposable unused = RxTimer.this.mDisposable = disposable;
            }

            public void onNext(Long l) {
                RxAction rxAction = rxAction;
                if (rxAction != null) {
                    rxAction.action(l.longValue());
                }
            }
        });
    }

    public void cancel() {
        Disposable disposable = this.mDisposable;
        if (disposable != null && !disposable.isDisposed()) {
            this.mDisposable.dispose();
        }
    }
}
