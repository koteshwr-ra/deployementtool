package com.ciot.networklib;

import com.ciot.base.util.MyFileUtils;
import com.ciot.base.util.MyLogUtils;
import com.ciot.networklib.bean.version.VersionBean;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0002H\u0016J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, d2 = {"com/ciot/networklib/ApkDownloadInstallUtils$downloadApk$1", "Lio/reactivex/Observer;", "", "onComplete", "", "onError", "e", "", "onNext", "downloadSuccess", "onSubscribe", "d", "Lio/reactivex/disposables/Disposable;", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: ApkDownloadInstallUtils.kt */
public final class ApkDownloadInstallUtils$downloadApk$1 implements Observer<Boolean> {
    final /* synthetic */ String $fileName;
    final /* synthetic */ String $filePath;
    final /* synthetic */ VersionBean $version;
    final /* synthetic */ ApkDownloadInstallUtils this$0;

    public void onComplete() {
    }

    ApkDownloadInstallUtils$downloadApk$1(ApkDownloadInstallUtils apkDownloadInstallUtils, String str, String str2, VersionBean versionBean) {
        this.this$0 = apkDownloadInstallUtils;
        this.$filePath = str;
        this.$fileName = str2;
        this.$version = versionBean;
    }

    public /* bridge */ /* synthetic */ void onNext(Object obj) {
        onNext(((Boolean) obj).booleanValue());
    }

    public void onSubscribe(Disposable disposable) {
        Intrinsics.checkNotNullParameter(disposable, "d");
        this.this$0.disposable = disposable;
    }

    public void onNext(boolean z) {
        String str = this.$filePath + DownloadFileUtil.TEMP_FILE_MARK;
        if (!MyFileUtils.isFileExists(str)) {
            this.this$0.reportDownloadFailCallBack(this.$filePath, "文件下载完成，但是没找到不存在");
        } else if (z) {
            MyLogUtils.Logi("文件下载完成: " + this.$fileName);
            MyFileUtils.delete(this.$filePath);
            MyFileUtils.rename(str, this.$fileName);
            ApkDownloadInstallUtils apkDownloadInstallUtils = this.this$0;
            String str2 = this.$filePath;
            String md5 = this.$version.getMd5();
            Intrinsics.checkNotNull(md5);
            apkDownloadInstallUtils.reportDownloadCompleteCallBack(str2, md5, this.$version.getMandatory());
        } else {
            this.this$0.reportDownloadFailCallBack(this.$filePath, "不明原因导致下载中断");
        }
    }

    public void onError(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "e");
        ApkDownloadInstallUtils apkDownloadInstallUtils = this.this$0;
        String str = this.$filePath;
        apkDownloadInstallUtils.reportDownloadFailCallBack(str, "文件下载失败: " + th);
    }
}
