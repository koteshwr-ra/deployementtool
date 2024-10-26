package com.ciot.networklib;

import com.blankj.utilcode.util.GsonUtils;
import com.ciot.base.util.MyLogUtils;
import com.ciot.networklib.bean.HttpErrorResponse;
import com.google.gson.JsonParseException;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;
import org.json.JSONException;
import retrofit2.HttpException;
import retrofit2.Response;

public abstract class BaseObserver<T> implements Observer<T> {
    private static final String TAG = "MAX_REQUEST";
    private static long begin = 0;
    private static long end = 0;
    private static long maxRequest = 2147483647L;

    public enum ExceptionReason {
        PARSE_ERROR,
        BAD_NETWORK,
        CONNECT_ERROR,
        CONNECT_TIMEOUT,
        UNKNOWN_ERROR
    }

    /* access modifiers changed from: protected */
    public void onChildSubscribe(Disposable disposable) {
    }

    public void onComplete() {
    }

    public abstract void onSuccess(T t);

    public final void onSubscribe(Disposable disposable) {
        onChildSubscribe(disposable);
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        if (currentTimeMillis >= begin && currentTimeMillis <= end) {
            MyLogUtils.Loge("MAX_REQUEST", "服务器已开启限流策略 begin:" + begin + " -> end:" + end + " maxRequest:" + maxRequest);
            if (maxRequest <= 0) {
                disposable.dispose();
                MyLogUtils.Loge("MAX_REQUEST", "已达到限流最大请求数，放弃该次请求。");
            }
        }
    }

    public final void onNext(T t) {
        try {
            onSuccess(t);
        } catch (Exception e) {
            e.printStackTrace();
            MyLogUtils.Loge(e.getMessage());
        }
    }

    public void onError(Throwable th) {
        if (th instanceof HttpException) {
            try {
                Response<?> response = ((HttpException) th).response();
                if (response != null && response.code() == 413) {
                    parseErrorCode413(response);
                }
                onException(ExceptionReason.BAD_NETWORK);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if ((th instanceof ConnectException) || (th instanceof UnknownHostException)) {
            onException(ExceptionReason.CONNECT_ERROR);
        } else if (th instanceof InterruptedIOException) {
            onException(ExceptionReason.CONNECT_TIMEOUT);
        } else if ((th instanceof JsonParseException) || (th instanceof JSONException) || (th instanceof ParseException)) {
            onException(ExceptionReason.PARSE_ERROR);
        } else {
            onException(ExceptionReason.UNKNOWN_ERROR);
        }
    }

    /* renamed from: com.ciot.networklib.BaseObserver$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$ciot$networklib$BaseObserver$ExceptionReason;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.ciot.networklib.BaseObserver$ExceptionReason[] r0 = com.ciot.networklib.BaseObserver.ExceptionReason.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$ciot$networklib$BaseObserver$ExceptionReason = r0
                com.ciot.networklib.BaseObserver$ExceptionReason r1 = com.ciot.networklib.BaseObserver.ExceptionReason.CONNECT_ERROR     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$ciot$networklib$BaseObserver$ExceptionReason     // Catch:{ NoSuchFieldError -> 0x001d }
                com.ciot.networklib.BaseObserver$ExceptionReason r1 = com.ciot.networklib.BaseObserver.ExceptionReason.CONNECT_TIMEOUT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$ciot$networklib$BaseObserver$ExceptionReason     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.ciot.networklib.BaseObserver$ExceptionReason r1 = com.ciot.networklib.BaseObserver.ExceptionReason.BAD_NETWORK     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$ciot$networklib$BaseObserver$ExceptionReason     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.ciot.networklib.BaseObserver$ExceptionReason r1 = com.ciot.networklib.BaseObserver.ExceptionReason.PARSE_ERROR     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$ciot$networklib$BaseObserver$ExceptionReason     // Catch:{ NoSuchFieldError -> 0x003e }
                com.ciot.networklib.BaseObserver$ExceptionReason r1 = com.ciot.networklib.BaseObserver.ExceptionReason.UNKNOWN_ERROR     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ciot.networklib.BaseObserver.AnonymousClass1.<clinit>():void");
        }
    }

    public void onException(ExceptionReason exceptionReason) {
        int i = AnonymousClass1.$SwitchMap$com$ciot$networklib$BaseObserver$ExceptionReason[exceptionReason.ordinal()];
    }

    private void parseErrorCode413(Response<?> response) throws IOException {
        HttpErrorResponse httpErrorResponse = (HttpErrorResponse) GsonUtils.fromJson(response.errorBody().string(), HttpErrorResponse.class);
        begin = httpErrorResponse.begin;
        end = httpErrorResponse.endTime;
        maxRequest = (long) (httpErrorResponse.max - httpErrorResponse.current);
        MyLogUtils.Loge("MAX_REQUEST", GsonUtils.toJson(httpErrorResponse));
    }
}
