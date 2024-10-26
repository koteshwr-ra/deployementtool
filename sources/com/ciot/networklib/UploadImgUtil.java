package com.ciot.networklib;

import androidx.lifecycle.CoroutineLiveDataKt;
import com.ciot.networklib.api.Api;
import com.ciot.networklib.bean.media.UploadImageBaseUrlResponse;
import com.ciot.networklib.bean.media.UploadImageIdResponse;
import com.ciot.networklib.bean.thermometry.CreateThermometry;
import com.ciot.networklib.bean.thermometry.CreateThermometryResponse;
import com.ciot.networklib.bean.upload.UploadResponse;
import com.ciot.networklib.function.RetryWithDelay;
import com.ciot.realm.db.RegisterWithAppointmentResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class UploadImgUtil {
    private static final int DELAY_TIME = 5000;
    private static final int RETRY_TIME = 3;
    private static UploadImgUtil mUploadImgUtil;
    private Gson mGson = new Gson();

    public static UploadImgUtil getInstance() {
        if (mUploadImgUtil == null) {
            synchronized (UploadImgUtil.class) {
                if (mUploadImgUtil == null) {
                    mUploadImgUtil = new UploadImgUtil();
                }
            }
        }
        return mUploadImgUtil;
    }

    public void registerWithAppointment(String str, BaseObserver<RegisterWithAppointmentResponse> baseObserver) {
        setPropertyDomain(RetrofitManager.Companion.getInstance().getWuHanBaseUrl());
        RetrofitManager.Companion.getInstance().getUploadImgApiService().registerWithAppointment(str, RetrofitManager.Companion.getInstance().getToken()).retry(3).subscribeOn(Schedulers.io()).subscribe(baseObserver);
    }

    public void CreateThermometry(CreateThermometry createThermometry, BaseObserver<CreateThermometryResponse> baseObserver) {
        setPropertyDomain(RetrofitManager.Companion.getInstance().getWuHanBaseUrl());
        RetrofitManager.Companion.getInstance().getUploadImgApiService().CreateThermometry(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), this.mGson.toJson((Object) createThermometry)), RetrofitManager.Companion.getInstance().getToken()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(baseObserver);
    }

    public void recordVisitorInfo(String str, String str2, String str3, String str4, BaseObserver<JsonObject> baseObserver) {
        setPropertyDomain(RetrofitManager.Companion.getInstance().getWuHanBaseUrl());
        RetrofitManager.Companion.getInstance().getUploadImgApiService().recordVisitorInfo(str, str2, str3, str4, RetrofitManager.Companion.getInstance().getToken()).retryWhen(getDefaultRetryWith()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(baseObserver);
    }

    public void recordVisitorInfo(String str, String str2, String str3, String str4, String str5, String str6, BaseObserver<JsonObject> baseObserver) {
        setPropertyDomain(RetrofitManager.Companion.getInstance().getWuHanBaseUrl());
        RetrofitManager.Companion.getInstance().getUploadImgApiService().recordVisitorInfo(str, str2, str3, str4, str5, str6, RetrofitManager.Companion.getInstance().getToken()).retryWhen(getDefaultRetryWith()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(baseObserver);
    }

    public void getUploadImageBaseUrl(BaseObserver<UploadImageBaseUrlResponse> baseObserver) {
        setPropertyDomain(RetrofitManager.Companion.getInstance().getWuHanBaseUrl());
        RetrofitManager.Companion.getInstance().getUploadImgApiService().getUploadImageBaseUrl(RetrofitManager.Companion.getInstance().getToken()).retry(3).subscribeOn(Schedulers.io()).subscribe(baseObserver);
    }

    public void getUploadImageId(BaseObserver<UploadImageIdResponse> baseObserver) {
        setPropertyDomain(RetrofitManager.Companion.getInstance().getWuHanBaseUrl());
        RetrofitManager.Companion.getInstance().getUploadImgApiService().getUploadImageId(RetrofitManager.Companion.getInstance().getToken()).retry(3).subscribeOn(Schedulers.io()).subscribe(baseObserver);
    }

    public void uploadImage(String str, String str2, String str3, BaseObserver<UploadResponse> baseObserver) {
        RetrofitManager.Companion.getInstance().getUploadImgApiService(str).uploadImage(str2, RequestBody.create(MediaType.parse(FileUtil.extension2MediaType(FileUtil.getFileExtension(str3))), new File(str3))).retry(3).subscribeOn(Schedulers.io()).subscribe(baseObserver);
    }

    public void setPropertyDomain(String str) {
        HttpUrl fetchDomain = RetrofitUrlManager.getInstance().fetchDomain(Api.DOMAIN_NAME_PROPERTY);
        if (fetchDomain == null || !fetchDomain.toString().equals(str)) {
            RetrofitUrlManager.getInstance().putDomain(Api.DOMAIN_NAME_PROPERTY, str);
        }
    }

    private RetryWithDelay getDefaultRetryWith() {
        return new RetryWithDelay(3, CoroutineLiveDataKt.DEFAULT_TIMEOUT);
    }
}
