package com.ciot.networklib;

import android.text.TextUtils;
import android.util.Log;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.ShellUtils;
import com.blankj.utilcode.util.StringUtils;
import com.ciot.base.constant.FileConstant;
import com.ciot.base.util.ContextUtil;
import com.ciot.base.util.MyFileUtils;
import com.ciot.base.util.MyLogUtils;
import com.ciot.networklib.bean.version.VersionBean;
import com.tencent.smtt.sdk.TbsReaderView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0004H\u0002J\u000e\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0016\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0004J\u0006\u0010\u0013\u001a\u00020\nJ\u001e\u0010\u0014\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0016J\u0016\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004J\u000e\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001c\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004XD¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/ciot/networklib/ApkDownloadInstallUtils;", "", "()V", "TAG", "", "disposable", "Lio/reactivex/disposables/Disposable;", "mDownloadApkCallBack", "Lcom/ciot/networklib/DownloadApkCallBack;", "cancelDownload", "", "deleteAllApkExcept", "filePath", "downloadApk", "version", "Lcom/ciot/networklib/bean/version/VersionBean;", "install", "apkPath", "md5", "removeDownloadApkCallBack", "reportDownloadCompleteCallBack", "mandatory", "", "reportDownloadFailCallBack", "failString", "reportDownloadProgress", "progress", "", "setDownloadApkCallBack", "callBack", "Companion", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: ApkDownloadInstallUtils.kt */
public final class ApkDownloadInstallUtils {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static ApkDownloadInstallUtils utils;
    /* access modifiers changed from: private */
    public final String TAG = "ApkDownload";
    /* access modifiers changed from: private */
    public Disposable disposable;
    private DownloadApkCallBack mDownloadApkCallBack;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\u0004R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\n"}, d2 = {"Lcom/ciot/networklib/ApkDownloadInstallUtils$Companion;", "", "()V", "utils", "Lcom/ciot/networklib/ApkDownloadInstallUtils;", "getUtils", "()Lcom/ciot/networklib/ApkDownloadInstallUtils;", "setUtils", "(Lcom/ciot/networklib/ApkDownloadInstallUtils;)V", "instance", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: ApkDownloadInstallUtils.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ApkDownloadInstallUtils getUtils() {
            return ApkDownloadInstallUtils.utils;
        }

        public final void setUtils(ApkDownloadInstallUtils apkDownloadInstallUtils) {
            ApkDownloadInstallUtils.utils = apkDownloadInstallUtils;
        }

        public final ApkDownloadInstallUtils instance() {
            if (getUtils() == null) {
                setUtils(new ApkDownloadInstallUtils());
            }
            ApkDownloadInstallUtils utils = getUtils();
            Intrinsics.checkNotNull(utils);
            return utils;
        }
    }

    public final void downloadApk(VersionBean versionBean) {
        Intrinsics.checkNotNullParameter(versionBean, "version");
        cancelDownload();
        if (versionBean.getVersionCode() > AppUtils.getAppVersionCode() || !Intrinsics.areEqual((Object) ContextUtil.getContext().getApplicationInfo().packageName, (Object) versionBean.getPackageName())) {
            String metaid = versionBean.getMetaid();
            Intrinsics.checkNotNull(metaid);
            DownloadFileUtil downloadFileUtil = new DownloadFileUtil(DownloadFileUtil.getBaseUrl(versionBean.getPath()));
            String str = metaid + ".apk";
            String str2 = FileConstant.APK_FILE_PATH.toString() + str;
            deleteAllApkExcept(str2);
            MyLogUtils.Logi("下载APK path: " + str2);
            if (!MyFileUtils.isFileExists(str2)) {
                String path = TextUtils.isEmpty(versionBean.getPath()) ? "" : versionBean.getPath();
                MyLogUtils.Logi("下载APK: " + versionBean);
                MyLogUtils.Logi("fullUrl：" + path);
                Intrinsics.checkNotNull(path);
                Observable<Boolean> downloadFile = downloadFileUtil.downloadFile(path, str2);
                Intrinsics.checkNotNullExpressionValue(downloadFile, "downloadFileUtil.downloadFile(fullUrl!!, filePath)");
                downloadFile.retry(2).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new ApkDownloadInstallUtils$downloadApk$1(this, str2, str, versionBean));
                return;
            }
            RetrofitManager.Companion.getInstance().setMNewApkFilePath(str2);
            RetrofitManager.Companion.getInstance().getMHandler().post(new Runnable(str2, versionBean) {
                public final /* synthetic */ String f$1;
                public final /* synthetic */ VersionBean f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    ApkDownloadInstallUtils.m52downloadApk$lambda0(ApkDownloadInstallUtils.this, this.f$1, this.f$2);
                }
            });
            return;
        }
        Log.d(this.TAG, "downloadApk: eqaul version code,need not download");
    }

    /* access modifiers changed from: private */
    /* renamed from: downloadApk$lambda-0  reason: not valid java name */
    public static final void m52downloadApk$lambda0(ApkDownloadInstallUtils apkDownloadInstallUtils, String str, VersionBean versionBean) {
        Intrinsics.checkNotNullParameter(apkDownloadInstallUtils, "this$0");
        Intrinsics.checkNotNullParameter(str, "$filePath");
        Intrinsics.checkNotNullParameter(versionBean, "$version");
        String md5 = versionBean.getMd5();
        Intrinsics.checkNotNull(md5);
        apkDownloadInstallUtils.reportDownloadCompleteCallBack(str, md5, versionBean.getMandatory());
    }

    public final void cancelDownload() {
        Disposable disposable2 = this.disposable;
        if (disposable2 != null) {
            Intrinsics.checkNotNull(disposable2);
            if (!disposable2.isDisposed()) {
                Disposable disposable3 = this.disposable;
                Intrinsics.checkNotNull(disposable3);
                disposable3.dispose();
                this.disposable = null;
            }
        }
    }

    private final void deleteAllApkExcept(String str) {
        if (new File(FileConstant.APK_FILE_PATH).exists()) {
            List<File> listFilesInDir = MyFileUtils.listFilesInDir(FileConstant.APK_FILE_PATH);
            Intrinsics.checkNotNullExpressionValue(listFilesInDir, "listFilesInDir(FileConstant.APK_FILE_PATH)");
            for (File next : listFilesInDir) {
                if (next != null && !Intrinsics.areEqual((Object) next.getPath(), (Object) str)) {
                    String path = next.getPath();
                    if (!Intrinsics.areEqual((Object) path, (Object) str + DownloadFileUtil.TEMP_FILE_MARK) && next.exists()) {
                        MyFileUtils.delete(next);
                    }
                }
                Log.d(this.TAG, "deleteAllApkExcept: has file,no del");
            }
        }
    }

    public final void reportDownloadCompleteCallBack(String str, String str2, boolean z) {
        Intrinsics.checkNotNullParameter(str, TbsReaderView.KEY_FILE_PATH);
        Intrinsics.checkNotNullParameter(str2, "md5");
        DownloadApkCallBack downloadApkCallBack = this.mDownloadApkCallBack;
        if (downloadApkCallBack != null) {
            downloadApkCallBack.onDownloadApkComplete(str, str2, z);
        }
    }

    public final void reportDownloadProgress(int i) {
        DownloadApkCallBack downloadApkCallBack = this.mDownloadApkCallBack;
        if (downloadApkCallBack != null) {
            downloadApkCallBack.onDownloadApkProgress(i);
        }
    }

    public final void reportDownloadFailCallBack(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "apkPath");
        Intrinsics.checkNotNullParameter(str2, "failString");
        DownloadApkCallBack downloadApkCallBack = this.mDownloadApkCallBack;
        if (downloadApkCallBack != null) {
            downloadApkCallBack.onDownloadFail(str, str2);
        }
    }

    public final void setDownloadApkCallBack(DownloadApkCallBack downloadApkCallBack) {
        Intrinsics.checkNotNullParameter(downloadApkCallBack, "callBack");
        this.mDownloadApkCallBack = downloadApkCallBack;
    }

    public final void removeDownloadApkCallBack() {
        this.mDownloadApkCallBack = null;
    }

    public final void install(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "apkPath");
        Intrinsics.checkNotNullParameter(str2, "md5");
        if (!TextUtils.isEmpty(str) && FileUtils.isFileExists(str)) {
            if (!StringUtils.equalsIgnoreCase(str2, FileUtils.getFileMD5ToString(str))) {
                FileUtils.delete(str);
            } else {
                DisposableObserver disposableObserver = (DisposableObserver) Observable.create(new ObservableOnSubscribe(str) {
                    public final /* synthetic */ String f$0;

                    {
                        this.f$0 = r1;
                    }

                    public final void subscribe(ObservableEmitter observableEmitter) {
                        ApkDownloadInstallUtils.m53install$lambda1(this.f$0, observableEmitter);
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new ApkDownloadInstallUtils$install$mInstallAppDisposable$2(this, str));
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: install$lambda-1  reason: not valid java name */
    public static final void m53install$lambda1(String str, ObservableEmitter observableEmitter) {
        Intrinsics.checkNotNullParameter(str, "$apkPath");
        Intrinsics.checkNotNullParameter(observableEmitter, "observableEmitter");
        observableEmitter.onNext(ShellUtils.execCmd(new String[]{"LD_LIBRARY_PATH=/vendor/lib*:/system/lib* pm install -r " + str}, true, true));
        observableEmitter.onComplete();
    }
}
