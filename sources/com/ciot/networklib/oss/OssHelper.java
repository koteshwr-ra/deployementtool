package com.ciot.networklib.oss;

import android.content.Context;
import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.OSSConstants;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.model.ObjectMetadata;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.FileUtils;
import com.ciot.base.config.CommonConfig;
import com.ciot.base.constant.FileConstant;
import com.ciot.base.util.LogPlus;
import com.ciot.networklib.RetrofitManager;
import com.ciot.networklib.bean.UploadRecordBean;
import com.ciot.networklib.bean.ZiYanUpdateBean;
import com.google.gson.Gson;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class OssHelper {
    private static final OssHelper INSTANCE = new OssHelper();
    private static final String TAG = OssHelper.class.getSimpleName();
    public static String abnormalBucket;
    public static String normalBucket;
    public static String robotbucket;
    public static String turingbucket;
    private String mEndpoint;
    private OSS mOssCline;
    private Disposable mSubscribe;

    private OssHelper() {
    }

    public static OssHelper getInstance() {
        return INSTANCE;
    }

    public void init(Context context) {
        LogPlus.w(OSSConstants.RESOURCE_NAME_OSS, "我来了几次");
        RetrofitManager.Companion.getInstance().getWuHanApiService().getOssConfig(RetrofitManager.Companion.getInstance().getToken()).subscribeOn(Schedulers.io()).delay(3, TimeUnit.SECONDS).subscribe(new Consumer(context) {
            public final /* synthetic */ Context f$1;

            {
                this.f$1 = r2;
            }

            public final void accept(Object obj) {
                OssHelper.this.lambda$init$0$OssHelper(this.f$1, (OssConfigResponse) obj);
            }
        }, $$Lambda$OssHelper$5YFd8aozfznLM4ISszKAVIM2bg8.INSTANCE);
    }

    public /* synthetic */ void lambda$init$0$OssHelper(Context context, OssConfigResponse ossConfigResponse) throws Exception {
        normalBucket = ossConfigResponse.normalbucket;
        abnormalBucket = ossConfigResponse.abnormalbucket;
        turingbucket = ossConfigResponse.turingbucket;
        robotbucket = ossConfigResponse.robotbucket;
        initOss(context, ossConfigResponse.accessKeyId, ossConfigResponse.accessKeySecret, ossConfigResponse.region);
    }

    static /* synthetic */ void lambda$init$1(Throwable th) throws Exception {
        String str = TAG;
        LogPlus.e(str, "getOssConfig failed->" + th.getMessage());
    }

    public void uploadFileByPath(String str, String str2, String str3, final IOssCallBack iOssCallBack) {
        if (this.mOssCline != null) {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType("text/plain");
            objectMetadata.setContentEncoding("UTF-8");
            PutObjectRequest putObjectRequest = new PutObjectRequest(str, str3, str2, objectMetadata);
            putObjectRequest.setProgressCallback(new OSSProgressCallback() {
                public final void onProgress(Object obj, long j, long j2) {
                    IOssCallBack.this.progress((int) ((((double) j) / ((double) j2)) * 100.0d));
                }
            });
            this.mOssCline.asyncPutObject(putObjectRequest, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
                public void onSuccess(PutObjectRequest putObjectRequest, PutObjectResult putObjectResult) {
                    iOssCallBack.success("oss upload success ");
                    LogPlus.e(putObjectResult.getServerCallbackReturnBody());
                }

                public void onFailure(PutObjectRequest putObjectRequest, ClientException clientException, ServiceException serviceException) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("oss upload failed ");
                    if (clientException != null) {
                        stringBuffer.append(" client error->");
                        stringBuffer.append(clientException.getMessage());
                    }
                    if (serviceException != null) {
                        stringBuffer.append(" service error->");
                        stringBuffer.append(serviceException.getMessage());
                    }
                    iOssCallBack.failure(stringBuffer.toString());
                }
            });
            return;
        }
        iOssCallBack.failure("oss unInt");
    }

    private void initOss(Context context, String str, String str2, String str3) {
        this.mEndpoint = String.format(Config.OSS_ENDPOINT, new Object[]{str3});
        OSSPlainTextAKSKCredentialProvider oSSPlainTextAKSKCredentialProvider = new OSSPlainTextAKSKCredentialProvider(str, str2);
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setConnectionTimeout(30000);
        clientConfiguration.setSocketTimeout(30000);
        clientConfiguration.setMaxConcurrentRequest(5);
        clientConfiguration.setMaxErrorRetry(3);
        this.mOssCline = new OSSClient(context, this.mEndpoint, oSSPlainTextAKSKCredentialProvider, clientConfiguration);
        String str4 = TAG;
        LogPlus.e(str4, "initOss endpoint=" + this.mEndpoint + " AK=" + str + " SK" + str2);
    }

    public void ossUploadRecord() {
        String str = FileConstant.Dir_LOG_TODAY + File.separator + CommonConfig.CIOT_SEMANTIC_INTENT + ".txt";
        if (FileUtils.isFileExists(str)) {
            LogPlus.w(OSSConstants.RESOURCE_NAME_OSS, str);
            String str2 = "ziyanSemantics/CIOT_SEMANTIC_INTENT_" + System.currentTimeMillis() + ".txt";
            LogPlus.w("robotbucket=" + robotbucket + "----");
            Observable.create(new ObservableOnSubscribe(str, str2) {
                public final /* synthetic */ String f$1;
                public final /* synthetic */ String f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void subscribe(ObservableEmitter observableEmitter) {
                    OssHelper.this.lambda$ossUploadRecord$3$OssHelper(this.f$1, this.f$2, observableEmitter);
                }
            }).subscribeOn(Schedulers.io()).map(new Function(str2) {
                public final /* synthetic */ String f$1;

                {
                    this.f$1 = r2;
                }

                public final Object apply(Object obj) {
                    return OssHelper.this.lambda$ossUploadRecord$4$OssHelper(this.f$1, (String) obj);
                }
            }).subscribe(new Consumer(str) {
                public final /* synthetic */ String f$0;

                {
                    this.f$0 = r1;
                }

                public final void accept(Object obj) {
                    RetrofitManager.Companion.getInstance().getCiotSemanticIntentApiService().uploadRecord((RequestBody) obj).subscribe(new Consumer(this.f$0) {
                        public final /* synthetic */ String f$0;

                        {
                            this.f$0 = r1;
                        }

                        public final void accept(Object obj) {
                            OssHelper.lambda$ossUploadRecord$5(this.f$0, (ResponseBody) obj);
                        }
                    }, $$Lambda$OssHelper$W_1odzdcPIDZLzOOaO8qn3UBAQ.INSTANCE);
                }
            });
        }
    }

    public /* synthetic */ void lambda$ossUploadRecord$3$OssHelper(String str, final String str2, final ObservableEmitter observableEmitter) throws Exception {
        uploadFileByPath(robotbucket, str, str2, new IOssCallBack() {
            public void progress(int i) {
            }

            public void success(String str) {
                observableEmitter.onNext(str2);
                observableEmitter.onComplete();
                LogPlus.w(OSSConstants.RESOURCE_NAME_OSS, "oos上传成功");
            }

            public void failure(String str) {
                observableEmitter.onError(new Throwable(str));
            }
        });
    }

    public /* synthetic */ RequestBody lambda$ossUploadRecord$4$OssHelper(String str, String str2) throws Exception {
        RetrofitManager.Companion.getInstance();
        this.mOssCline.presignPublicObjectURL(robotbucket, str);
        UploadRecordBean uploadRecordBean = new UploadRecordBean();
        String jSONString = JSON.toJSONString(uploadRecordBean);
        LogPlus.w(OSSConstants.RESOURCE_NAME_OSS, uploadRecordBean.toString());
        LogPlus.w(OSSConstants.RESOURCE_NAME_OSS, jSONString);
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jSONString);
    }

    static /* synthetic */ void lambda$ossUploadRecord$5(String str, ResponseBody responseBody) throws Exception {
        LogPlus.w(OSSConstants.RESOURCE_NAME_OSS, new String(responseBody.bytes()));
        FileUtils.delete(str);
    }

    public void uploadZiyan() {
        Disposable disposable = this.mSubscribe;
        if (disposable == null || disposable.isDisposed()) {
            Gson gson = new Gson();
            AtomicReference atomicReference = new AtomicReference(FileConstant.Dir_LOG_TODAY + File.separator + CommonConfig.CIOT_SEMANTIC_INTENT + ".txt");
            LogPlus.w("语义上传", FileConstant.APP_PATH);
            this.mSubscribe = Observable.interval(3, 3600, TimeUnit.SECONDS).subscribe(new Consumer(atomicReference, gson) {
                public final /* synthetic */ AtomicReference f$0;
                public final /* synthetic */ Gson f$1;

                {
                    this.f$0 = r1;
                    this.f$1 = r2;
                }

                public final void accept(Object obj) {
                    Observable.just(FileConstant.APP_PATH).filter($$Lambda$OssHelper$hZdvfukraedeKNJh_hcscX5JZw.INSTANCE).subscribeOn(Schedulers.io()).concatMap($$Lambda$OssHelper$xMB49lqxHFLgcepzKf3E1Z6gq3E.INSTANCE).map(new Function(this.f$0) {
                        public final /* synthetic */ AtomicReference f$0;

                        {
                            this.f$0 = r1;
                        }

                        public final Object apply(Object obj) {
                            return OssHelper.lambda$uploadZiyan$12(this.f$0, (File) obj);
                        }
                    }).concatMap(new Function(this.f$0) {
                        public final /* synthetic */ AtomicReference f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final Object apply(Object obj) {
                            return Observable.fromIterable((List) obj).filter($$Lambda$OssHelper$JVo1Y8nQ2KPq0nCPm5vV_QRAQfU.INSTANCE).map(new Function() {
                                public final Object apply(Object obj) {
                                    return OssHelper.lambda$uploadZiyan$14(Gson.this, (String) obj);
                                }
                            }).buffer(100).map($$Lambda$OssHelper$5IiMD0qxqcvYXkIuk_isTplWU0.INSTANCE).concatMap($$Lambda$OssHelper$rduaCOFWtnnahVnyzkAivYm32T4.INSTANCE).retry(3).doOnComplete(new Action(this.f$1) {
                                public final /* synthetic */ AtomicReference f$0;

                                {
                                    this.f$0 = r1;
                                }

                                public final void run() {
                                    FileUtils.delete((String) this.f$0.get());
                                }
                            });
                        }
                    }).subscribe(new Consumer(this.f$0) {
                        public final /* synthetic */ AtomicReference f$0;

                        {
                            this.f$0 = r1;
                        }

                        public final void accept(Object obj) {
                            OssHelper.lambda$uploadZiyan$19(this.f$0, (ResponseBody) obj);
                        }
                    }, $$Lambda$OssHelper$Updbr82ikNbqSppxbM1Sve3ew0.INSTANCE);
                }
            });
        }
    }

    static /* synthetic */ ObservableSource lambda$uploadZiyan$11(String str) throws Exception {
        File[] listFiles = new File(FileConstant.APP_PATH).listFiles($$Lambda$k1LMnpJLlrYtcSsQvSbPWdaMgg.INSTANCE);
        LogPlus.w(Arrays.toString(listFiles));
        return Observable.fromArray(listFiles).map($$Lambda$OssHelper$Ya7x_mOuNwxKsyZtV7LqvAOIlxg.INSTANCE).concatMap($$Lambda$g04I4HugH9LNTCziDkyOXv3XPLI.INSTANCE);
    }

    static /* synthetic */ File[] lambda$uploadZiyan$10(File file) throws Exception {
        File[] listFiles = file.listFiles($$Lambda$OssHelper$3hs2BVm72N6iytINycPB3SmPtew.INSTANCE);
        LogPlus.w(Arrays.toString(listFiles));
        return listFiles;
    }

    static /* synthetic */ boolean lambda$uploadZiyan$9(File file) {
        return file.isFile() && TextUtils.equals(file.getName(), "CIOT_ZIYAN_SEMANTICS.txt");
    }

    static /* synthetic */ List lambda$uploadZiyan$12(AtomicReference atomicReference, File file) throws Exception {
        LogPlus.w("语义上传", file.getAbsolutePath());
        atomicReference.set(file.getAbsolutePath());
        return FileIOUtils.readFile2List(file);
    }

    static /* synthetic */ boolean lambda$uploadZiyan$13(String str) throws Exception {
        return str.startsWith("{") && str.endsWith("}");
    }

    static /* synthetic */ ZiYanUpdateBean lambda$uploadZiyan$14(Gson gson, String str) throws Exception {
        try {
            return (ZiYanUpdateBean) gson.fromJson(str, ZiYanUpdateBean.class);
        } catch (Exception unused) {
            HashMap<String, String> requestMap = RetrofitManager.Companion.getInstance().getRequestMap();
            return new ZiYanUpdateBean(requestMap.get("area"), requestMap.get("deviceid"), requestMap.get("project"), Integer.parseInt(requestMap.get("robottype")), String.valueOf(System.currentTimeMillis()), "", "", "");
        }
    }

    static /* synthetic */ RequestBody lambda$uploadZiyan$15(List list) throws Exception {
        UploadRecordBean uploadRecordBean = new UploadRecordBean(list);
        String jSONString = JSON.toJSONString(uploadRecordBean);
        LogPlus.w(OSSConstants.RESOURCE_NAME_OSS, uploadRecordBean.toString());
        LogPlus.w(OSSConstants.RESOURCE_NAME_OSS, "数据长度---" + list.size());
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jSONString);
    }

    static /* synthetic */ void lambda$uploadZiyan$19(AtomicReference atomicReference, ResponseBody responseBody) throws Exception {
        FileUtils.delete((String) atomicReference.get());
        LogPlus.w(OSSConstants.RESOURCE_NAME_OSS, responseBody.string());
    }
}
