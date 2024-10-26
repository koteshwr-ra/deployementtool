package com.ciot.networklib;

import android.text.TextUtils;
import android.util.Log;
import com.ciot.base.storage.MySpUtils;
import com.ciot.base.util.MyFileUtils;
import com.ciot.base.util.MyLogUtils;
import com.ciot.networklib.api.DownloadService;
import com.ciot.networklib.bean.FileInfoBean;
import com.ciot.networklib.download.DownloadFileListener;
import com.ciot.networklib.download.DownloadInterceptor;
import com.google.gson.Gson;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import mc.csst.com.selfchassis.ui.fragment.set.map.MapFragment;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DownloadFileUtil {
    private static final int DEFAULT_TIMEOUT = 15;
    public static final int DOWNLOAD_STATE_DOWNLOADING = 3;
    public static final int DOWNLOAD_STATE_ERROR = -1;
    public static final int DOWNLOAD_STATE_EXIST = 2;
    public static final int DOWNLOAD_STATE_FINISH = 1;
    public static final int DOWNLOAD_STATE_NORMAL = 0;
    /* access modifiers changed from: private */
    public static final String TAG = DownloadFileUtil.class.getSimpleName();
    public static final String TEMP_FILE_MARK = ".temp";
    public static int mDownloadState = 0;
    private static Pattern sUrlPattern = Pattern.compile("((http|ftp|https)://)(([a-zA-Z0-9._-]+)|([0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}))(([a-zA-Z]{2,6})|(:[0-9]{1,4})?)(/)");
    private ConcurrentHashMap<String, DownloadService> mApiServiceMap;
    /* access modifiers changed from: private */
    public int mBufferSize;
    /* access modifiers changed from: private */
    public long mOffset;
    /* access modifiers changed from: private */
    public String mTempFilePath;
    /* access modifiers changed from: private */
    public long mTotalFileLength;
    private Retrofit retrofit;

    public void release() {
    }

    public DownloadFileUtil(String str) {
        this.mTotalFileLength = -1;
        this.mBufferSize = 2048;
        this.mApiServiceMap = new ConcurrentHashMap<>();
        this.retrofit = new Retrofit.Builder().baseUrl(str).client(getOkHttpClient()).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
    }

    public DownloadFileUtil(String str, DownloadFileListener downloadFileListener) {
        this.mTotalFileLength = -1;
        this.mBufferSize = 2048;
        this.mApiServiceMap = new ConcurrentHashMap<>();
        this.retrofit = new Retrofit.Builder().baseUrl(str).client(new OkHttpClient.Builder().addInterceptor(new DownloadInterceptor(downloadFileListener)).retryOnConnectionFailure(true).connectTimeout(15, TimeUnit.SECONDS).build()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
    }

    private DownloadFileUtil() {
        this.mTotalFileLength = -1;
        this.mBufferSize = 2048;
        this.mApiServiceMap = new ConcurrentHashMap<>();
    }

    public static DownloadFileUtil getInstance() {
        return DownloadFileUtilHelper.mHolder;
    }

    private static class DownloadFileUtilHelper {
        static DownloadFileUtil mHolder = new DownloadFileUtil();

        private DownloadFileUtilHelper() {
        }
    }

    private OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder().retryOnConnectionFailure(true).connectTimeout(15, TimeUnit.SECONDS).build();
    }

    public int getDownloadState() {
        return mDownloadState;
    }

    public static String getBaseUrl(String str) {
        if (!TextUtils.isEmpty(str)) {
            Matcher matcher = sUrlPattern.matcher(str);
            if (matcher.find()) {
                return matcher.group();
            }
        }
        return "";
    }

    public Observable<Boolean> downloadFile(final String str, String str2) {
        Retrofit retrofit3 = this.retrofit;
        if (retrofit3 == null) {
            return Observable.create(new ObservableOnSubscribe<Boolean>() {
                public void subscribe(ObservableEmitter<Boolean> observableEmitter) {
                    Log.e(DownloadFileUtil.TAG, "downloadFile failed,retrofit is not init");
                    observableEmitter.onNext(false);
                    observableEmitter.onComplete();
                }
            });
        }
        final DownloadService downloadService = (DownloadService) retrofit3.create(DownloadService.class);
        this.mTempFilePath = str2 + TEMP_FILE_MARK;
        return downloadService.getFileInfo(str, "all").flatMap(new Function<ResponseBody, ObservableSource<Boolean>>() {
            public ObservableSource<Boolean> apply(ResponseBody responseBody) {
                try {
                    String string = responseBody.string();
                    long unused = DownloadFileUtil.this.mOffset = MyFileUtils.getLength(DownloadFileUtil.this.mTempFilePath);
                    long unused2 = DownloadFileUtil.this.mTotalFileLength = (long) ((FileInfoBean) new Gson().fromJson(string, FileInfoBean.class)).getSize();
                    if (DownloadFileUtil.this.mOffset < 0) {
                        long unused3 = DownloadFileUtil.this.mOffset = 0;
                    } else if (DownloadFileUtil.this.mOffset > DownloadFileUtil.this.mTotalFileLength) {
                        Log.e(DownloadFileUtil.TAG, "当已下载文件大于等于原文件长度时，删除原文件，避免数据错乱");
                        MyFileUtils.delete(DownloadFileUtil.this.mTempFilePath);
                        long unused4 = DownloadFileUtil.this.mOffset = 0;
                    } else if (DownloadFileUtil.this.mOffset == DownloadFileUtil.this.mTotalFileLength) {
                        return Observable.create(new ObservableOnSubscribe<Boolean>() {
                            public void subscribe(ObservableEmitter<Boolean> observableEmitter) {
                                Log.e(DownloadFileUtil.TAG, "当已下载文件数据一致");
                                observableEmitter.onNext(true);
                                observableEmitter.onComplete();
                            }
                        });
                    }
                } catch (Exception e) {
                    String access$100 = DownloadFileUtil.TAG;
                    Log.e(access$100, "apply exception: ->" + e.getMessage());
                    e.printStackTrace();
                }
                DownloadFileUtil downloadFileUtil = DownloadFileUtil.this;
                return downloadFileUtil.downloadFileByInfo(downloadService.downloadFile(str, downloadFileUtil.mOffset, DownloadFileUtil.this.mTotalFileLength - DownloadFileUtil.this.mOffset));
            }
        });
    }

    /* access modifiers changed from: private */
    public Observable<Boolean> downloadFileByInfo(Observable<ResponseBody> observable) {
        return observable.flatMap(new Function<ResponseBody, ObservableSource<Boolean>>() {
            public ObservableSource<Boolean> apply(final ResponseBody responseBody) {
                return Observable.create(new ObservableOnSubscribe<Boolean>() {
                    public void subscribe(ObservableEmitter<Boolean> observableEmitter) throws Exception {
                        InputStream byteStream = responseBody.byteStream();
                        boolean z = false;
                        if (!MyFileUtils.createOrExistsFile(DownloadFileUtil.this.mTempFilePath)) {
                            observableEmitter.onNext(false);
                            return;
                        }
                        long access$200 = DownloadFileUtil.this.mOffset;
                        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(DownloadFileUtil.this.mTempFilePath, true), DownloadFileUtil.this.mBufferSize);
                        byte[] bArr = new byte[DownloadFileUtil.this.mBufferSize];
                        while (true) {
                            try {
                                int read = byteStream.read(bArr);
                                if (read != -1) {
                                    bufferedOutputStream.write(bArr, 0, read);
                                    access$200 += (long) read;
                                    final int access$400 = (int) ((100 * access$200) / DownloadFileUtil.this.mTotalFileLength);
                                    String access$100 = DownloadFileUtil.TAG;
                                    Log.v(access$100, "下载进度：: " + access$400);
                                    RetrofitManager.Companion.getInstance().getMHandler().post(new Runnable() {
                                        public void run() {
                                            ApkDownloadInstallUtils.Companion.instance().reportDownloadProgress(access$400);
                                        }
                                    });
                                }
                            } catch (SocketException unused) {
                                String access$1002 = DownloadFileUtil.TAG;
                                Log.e(access$1002, "下载取消了: " + DownloadFileUtil.this.mTempFilePath);
                            }
                            try {
                                break;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        byteStream.close();
                        try {
                            bufferedOutputStream.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                        if (MyFileUtils.getLength(DownloadFileUtil.this.mTempFilePath) == DownloadFileUtil.this.mTotalFileLength) {
                            z = true;
                        }
                        observableEmitter.onNext(Boolean.valueOf(z));
                        observableEmitter.onComplete();
                    }
                });
            }
        });
    }

    public void downloadFile(DownloadService downloadService, long j, String str, String str2, String str3, DownloadCallBack downloadCallBack) {
        DownloadService downloadService2 = downloadService;
        String str4 = str;
        if (!MyFileUtils.isDir(str2)) {
            MyLogUtils.Loge(TAG, "downloadFile failed,saveDir is not Dir");
            mDownloadState = -1;
        } else if (downloadService2 == null) {
            MyLogUtils.Loge(TAG, "downloadFile failed,downloadService is null");
            mDownloadState = -1;
        } else {
            File file = new File(str2, str3);
            String str5 = MapFragment.SLASH;
            if (file.exists()) {
                str5 = str5 + file.length();
            }
            if (isDownloadFileExist(str, file)) {
                mDownloadState = 2;
                return;
            }
            String str6 = "bytes=" + Long.toString(j) + str5;
            MyLogUtils.Logi(TAG, "rangeStr=" + str6);
            final long j2 = j;
            final String str7 = str2;
            final String str8 = str3;
            final String str9 = str;
            final DownloadCallBack downloadCallBack2 = downloadCallBack;
            downloadService.download(str, j).subscribeOn(Schedulers.io()).subscribe(new Observer<ResponseBody>() {
                public void onSubscribe(Disposable disposable) {
                }

                /* JADX WARNING: Removed duplicated region for block: B:41:0x00c9 A[Catch:{ all -> 0x00e7 }] */
                /* JADX WARNING: Removed duplicated region for block: B:44:0x00d7 A[SYNTHETIC, Splitter:B:44:0x00d7] */
                /* JADX WARNING: Removed duplicated region for block: B:49:0x00df A[Catch:{ Exception -> 0x00db }] */
                /* JADX WARNING: Removed duplicated region for block: B:53:0x00ea A[SYNTHETIC, Splitter:B:53:0x00ea] */
                /* JADX WARNING: Removed duplicated region for block: B:58:0x00f2 A[Catch:{ Exception -> 0x00ee }] */
                /* JADX WARNING: Removed duplicated region for block: B:68:? A[RETURN, SYNTHETIC] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void onNext(okhttp3.ResponseBody r13) {
                    /*
                        r12 = this;
                        long r0 = r2
                        r2 = 2048(0x800, float:2.87E-42)
                        r3 = -1
                        r4 = 0
                        byte[] r2 = new byte[r2]     // Catch:{ Exception -> 0x00a5, all -> 0x00a2 }
                        long r5 = r13.contentLength()     // Catch:{ Exception -> 0x00a5, all -> 0x00a2 }
                        java.io.InputStream r13 = r13.byteStream()     // Catch:{ Exception -> 0x00a5, all -> 0x00a2 }
                        java.lang.String r7 = r4     // Catch:{ Exception -> 0x00a0 }
                        java.io.File r8 = new java.io.File     // Catch:{ Exception -> 0x00a0 }
                        java.lang.String r9 = r5     // Catch:{ Exception -> 0x00a0 }
                        r8.<init>(r7, r9)     // Catch:{ Exception -> 0x00a0 }
                        java.io.File r9 = new java.io.File     // Catch:{ Exception -> 0x00a0 }
                        r9.<init>(r7)     // Catch:{ Exception -> 0x00a0 }
                        boolean r7 = r9.exists()     // Catch:{ Exception -> 0x00a0 }
                        if (r7 != 0) goto L_0x0027
                        r9.mkdirs()     // Catch:{ Exception -> 0x00a0 }
                    L_0x0027:
                        java.io.RandomAccessFile r7 = new java.io.RandomAccessFile     // Catch:{ Exception -> 0x00a0 }
                        java.lang.String r9 = "rwd"
                        r7.<init>(r8, r9)     // Catch:{ Exception -> 0x00a0 }
                        long r8 = r2     // Catch:{ Exception -> 0x009d, all -> 0x009a }
                        r10 = 0
                        int r4 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
                        if (r4 != 0) goto L_0x0039
                        r7.setLength(r5)     // Catch:{ Exception -> 0x009d, all -> 0x009a }
                    L_0x0039:
                        long r4 = r2     // Catch:{ Exception -> 0x009d, all -> 0x009a }
                        r7.seek(r4)     // Catch:{ Exception -> 0x009d, all -> 0x009a }
                        r4 = 3
                        com.ciot.networklib.DownloadFileUtil.mDownloadState = r4     // Catch:{ Exception -> 0x009d, all -> 0x009a }
                        r4 = 0
                        r5 = 0
                    L_0x0043:
                        int r6 = r13.read(r2)     // Catch:{ Exception -> 0x009d, all -> 0x009a }
                        if (r6 == r3) goto L_0x0088
                        r7.write(r2, r4, r6)     // Catch:{ Exception -> 0x009d, all -> 0x009a }
                        long r8 = (long) r6     // Catch:{ Exception -> 0x009d, all -> 0x009a }
                        long r0 = r0 + r8
                        com.ciot.base.storage.MySpUtils r6 = com.ciot.base.storage.MySpUtils.getInstance()     // Catch:{ Exception -> 0x009d, all -> 0x009a }
                        java.lang.String r8 = r6     // Catch:{ Exception -> 0x009d, all -> 0x009a }
                        r6.putLong(r8, r0)     // Catch:{ Exception -> 0x009d, all -> 0x009a }
                        r8 = 100
                        long r8 = r8 * r0
                        long r10 = r7.length()     // Catch:{ Exception -> 0x009d, all -> 0x009a }
                        long r8 = r8 / r10
                        int r6 = (int) r8     // Catch:{ Exception -> 0x009d, all -> 0x009a }
                        if (r6 <= 0) goto L_0x0086
                        if (r6 == r5) goto L_0x0086
                        com.ciot.networklib.DownloadCallBack r5 = r7     // Catch:{ Exception -> 0x009d, all -> 0x009a }
                        if (r5 == 0) goto L_0x0086
                        java.lang.String r5 = com.ciot.networklib.DownloadFileUtil.TAG     // Catch:{ Exception -> 0x009d, all -> 0x009a }
                        java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x009d, all -> 0x009a }
                        r8.<init>()     // Catch:{ Exception -> 0x009d, all -> 0x009a }
                        java.lang.String r9 = "downloadFile progress:"
                        r8.append(r9)     // Catch:{ Exception -> 0x009d, all -> 0x009a }
                        r8.append(r6)     // Catch:{ Exception -> 0x009d, all -> 0x009a }
                        java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x009d, all -> 0x009a }
                        android.util.Log.v(r5, r8)     // Catch:{ Exception -> 0x009d, all -> 0x009a }
                        com.ciot.networklib.DownloadCallBack r5 = r7     // Catch:{ Exception -> 0x009d, all -> 0x009a }
                        r5.onProgress(r6)     // Catch:{ Exception -> 0x009d, all -> 0x009a }
                    L_0x0086:
                        r5 = r6
                        goto L_0x0043
                    L_0x0088:
                        com.ciot.networklib.DownloadCallBack r0 = r7     // Catch:{ Exception -> 0x009d, all -> 0x009a }
                        if (r0 == 0) goto L_0x0091
                        com.ciot.networklib.DownloadCallBack r0 = r7     // Catch:{ Exception -> 0x009d, all -> 0x009a }
                        r0.onCompleted()     // Catch:{ Exception -> 0x009d, all -> 0x009a }
                    L_0x0091:
                        r7.close()     // Catch:{ Exception -> 0x00db }
                        if (r13 == 0) goto L_0x00e6
                        r13.close()     // Catch:{ Exception -> 0x00db }
                        goto L_0x00e6
                    L_0x009a:
                        r0 = move-exception
                        r4 = r7
                        goto L_0x00e8
                    L_0x009d:
                        r0 = move-exception
                        r4 = r7
                        goto L_0x00a7
                    L_0x00a0:
                        r0 = move-exception
                        goto L_0x00a7
                    L_0x00a2:
                        r0 = move-exception
                        r13 = r4
                        goto L_0x00e8
                    L_0x00a5:
                        r0 = move-exception
                        r13 = r4
                    L_0x00a7:
                        com.ciot.networklib.DownloadFileUtil.mDownloadState = r3     // Catch:{ all -> 0x00e7 }
                        java.lang.String r1 = com.ciot.networklib.DownloadFileUtil.TAG     // Catch:{ all -> 0x00e7 }
                        java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00e7 }
                        r2.<init>()     // Catch:{ all -> 0x00e7 }
                        java.lang.String r3 = "downloadFile Exception:"
                        r2.append(r3)     // Catch:{ all -> 0x00e7 }
                        java.lang.String r3 = r0.getMessage()     // Catch:{ all -> 0x00e7 }
                        r2.append(r3)     // Catch:{ all -> 0x00e7 }
                        java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x00e7 }
                        android.util.Log.e(r1, r2)     // Catch:{ all -> 0x00e7 }
                        com.ciot.networklib.DownloadCallBack r1 = r7     // Catch:{ all -> 0x00e7 }
                        if (r1 == 0) goto L_0x00d2
                        com.ciot.networklib.DownloadCallBack r1 = r7     // Catch:{ all -> 0x00e7 }
                        java.lang.String r2 = r0.getMessage()     // Catch:{ all -> 0x00e7 }
                        r1.onError(r2)     // Catch:{ all -> 0x00e7 }
                    L_0x00d2:
                        r0.printStackTrace()     // Catch:{ all -> 0x00e7 }
                        if (r4 == 0) goto L_0x00dd
                        r4.close()     // Catch:{ Exception -> 0x00db }
                        goto L_0x00dd
                    L_0x00db:
                        r13 = move-exception
                        goto L_0x00e3
                    L_0x00dd:
                        if (r13 == 0) goto L_0x00e6
                        r13.close()     // Catch:{ Exception -> 0x00db }
                        goto L_0x00e6
                    L_0x00e3:
                        r13.printStackTrace()
                    L_0x00e6:
                        return
                    L_0x00e7:
                        r0 = move-exception
                    L_0x00e8:
                        if (r4 == 0) goto L_0x00f0
                        r4.close()     // Catch:{ Exception -> 0x00ee }
                        goto L_0x00f0
                    L_0x00ee:
                        r13 = move-exception
                        goto L_0x00f6
                    L_0x00f0:
                        if (r13 == 0) goto L_0x00f9
                        r13.close()     // Catch:{ Exception -> 0x00ee }
                        goto L_0x00f9
                    L_0x00f6:
                        r13.printStackTrace()
                    L_0x00f9:
                        throw r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.ciot.networklib.DownloadFileUtil.AnonymousClass4.onNext(okhttp3.ResponseBody):void");
                }

                public void onError(Throwable th) {
                    DownloadFileUtil.mDownloadState = -1;
                    DownloadCallBack downloadCallBack = downloadCallBack2;
                    if (downloadCallBack != null) {
                        downloadCallBack.onError(th.toString());
                    }
                }

                public void onComplete() {
                    DownloadFileUtil.mDownloadState = 1;
                }
            });
        }
    }

    public boolean isDownloadFileExist(String str, File file) {
        if (!MyFileUtils.isFileExists(file) || MySpUtils.getInstance().getLong(str, 0) != file.length()) {
            return false;
        }
        String str2 = TAG;
        MyLogUtils.Logi(str2, "local has already downloaded apk" + str);
        return true;
    }
}
