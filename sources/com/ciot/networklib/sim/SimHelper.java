package com.ciot.networklib.sim;

import android.telephony.TelephonyManager;
import com.ciot.base.util.AppSpUtil;
import com.ciot.base.util.ContextUtil;
import com.ciot.networklib.BaseObserver;
import com.ciot.networklib.RetrofitManager;
import com.ciot.networklib.api.WuhanApiService;
import com.google.gson.JsonObject;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bJ&\u0010\n\u001a\u00020\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bJ\u000e\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J0\u0010\f\u001a\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¨\u0006\u000e"}, d2 = {"Lcom/ciot/networklib/sim/SimHelper;", "", "()V", "getSimDetail", "", "token", "", "observer", "Lcom/ciot/networklib/BaseObserver;", "Lokhttp3/ResponseBody;", "getSimsDetail", "iccid", "postRobotInfo", "robotId", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: SimHelper.kt */
public final class SimHelper {
    public final void postRobotInfo(String str) {
        Intrinsics.checkNotNullParameter(str, "token");
        Object systemService = ContextUtil.getContext().getSystemService("phone");
        if (systemService != null) {
            postRobotInfo(AppSpUtil.getInstance().getRobotNumber(), ((TelephonyManager) systemService).getSimSerialNumber(), str, new SimHelper$postRobotInfo$1());
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.telephony.TelephonyManager");
    }

    public final void getSimDetail(String str, BaseObserver<ResponseBody> baseObserver) {
        Intrinsics.checkNotNullParameter(str, "token");
        Intrinsics.checkNotNullParameter(baseObserver, "observer");
        Object systemService = ContextUtil.getContext().getSystemService("phone");
        if (systemService != null) {
            getSimsDetail(((TelephonyManager) systemService).getSimSerialNumber(), str, baseObserver);
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.telephony.TelephonyManager");
    }

    public final void postRobotInfo(String str, String str2, String str3, BaseObserver<ResponseBody> baseObserver) {
        Observable<ResponseBody> postRobotInfo;
        Observable<ResponseBody> retry;
        Observable<ResponseBody> subscribeOn;
        Observable<ResponseBody> observeOn;
        Intrinsics.checkNotNullParameter(str3, "token");
        Intrinsics.checkNotNullParameter(baseObserver, "observer");
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("robot", str);
        jsonObject.addProperty("iccid", str2);
        RequestBody.Companion companion = RequestBody.Companion;
        MediaType parse = MediaType.Companion.parse("application/json; charset=utf-8");
        String jsonObject2 = jsonObject.toString();
        Intrinsics.checkNotNullExpressionValue(jsonObject2, "jsonObject.toString()");
        RequestBody create = companion.create(parse, jsonObject2);
        WuhanApiService wuHanApiService = RetrofitManager.Companion.getInstance().getWuHanApiService();
        if (wuHanApiService != null && (postRobotInfo = wuHanApiService.postRobotInfo(create, str3)) != null && (retry = postRobotInfo.retry(2)) != null && (subscribeOn = retry.subscribeOn(Schedulers.io())) != null && (observeOn = subscribeOn.observeOn(AndroidSchedulers.mainThread())) != null) {
            observeOn.subscribe((Observer<? super ResponseBody>) baseObserver);
        }
    }

    public final void getSimsDetail(String str, String str2, BaseObserver<ResponseBody> baseObserver) {
        Observable<ResponseBody> simsDetail;
        Observable<ResponseBody> retry;
        Observable<ResponseBody> subscribeOn;
        Observable<ResponseBody> observeOn;
        Intrinsics.checkNotNullParameter(str2, "token");
        Intrinsics.checkNotNullParameter(baseObserver, "observer");
        WuhanApiService wuHanApiService = RetrofitManager.Companion.getInstance().getWuHanApiService();
        if (wuHanApiService != null && (simsDetail = wuHanApiService.getSimsDetail(str, str2)) != null && (retry = simsDetail.retry(2)) != null && (subscribeOn = retry.subscribeOn(Schedulers.io())) != null && (observeOn = subscribeOn.observeOn(AndroidSchedulers.mainThread())) != null) {
            observeOn.subscribe((Observer<? super ResponseBody>) baseObserver);
        }
    }
}
