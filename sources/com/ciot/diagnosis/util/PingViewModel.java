package com.ciot.diagnosis.util;

import android.text.TextUtils;
import android.util.Log;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PingViewModel extends ViewModel {
    private static final String TAG = "PingViewModel";
    public MutableLiveData<String> data = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public Disposable disposable;
    /* access modifiers changed from: private */
    public Process process;

    public PingViewModel() {
        setRxJavaErrorHandler();
    }

    public static void setRxJavaErrorHandler() {
        RxJavaPlugins.setErrorHandler($$Lambda$PingViewModel$Ln9u7037xSxTen9BPjA7Pqc_Ri8.INSTANCE);
    }

    static /* synthetic */ void lambda$setRxJavaErrorHandler$0(Throwable th) throws Exception {
        th.printStackTrace();
        Log.e(TAG, "setRxJavaErrorHandler " + th.getMessage());
    }

    public void ping(final LifecycleOwner lifecycleOwner, final String str) {
        this.disposable = Observable.create(new ObservableOnSubscribe<String>() {
            public void subscribe(ObservableEmitter<String> observableEmitter) {
                try {
                    String str = "ping " + str;
                    Process unused = PingViewModel.this.process = Runtime.getRuntime().exec(str);
                    observableEmitter.onNext(str);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(PingViewModel.this.process.getInputStream()));
                    String readLine = bufferedReader.readLine();
                    if (TextUtils.isEmpty(readLine)) {
                        observableEmitter.onError(new Throwable(String.format("无法ping通%s", new Object[]{str})));
                    } else {
                        do {
                            observableEmitter.onNext(readLine);
                            readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                break;
                            }
                        } while (!PingViewModel.this.isLive(lifecycleOwner));
                    }
                    bufferedReader.close();
                    PingViewModel.this.process.destroy();
                } catch (IOException e) {
                    e.printStackTrace();
                    if (!PingViewModel.this.disposable.isDisposed()) {
                        observableEmitter.onError(e);
                    }
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
            public void accept(String str) throws Exception {
                PingViewModel.this.data.setValue(str);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                PingViewModel.this.data.setValue(th.getMessage());
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean isLive(LifecycleOwner lifecycleOwner) {
        return lifecycleOwner != null && lifecycleOwner.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.CREATED);
    }

    public void onDestroy() {
        this.process.destroy();
        this.disposable.dispose();
    }
}
