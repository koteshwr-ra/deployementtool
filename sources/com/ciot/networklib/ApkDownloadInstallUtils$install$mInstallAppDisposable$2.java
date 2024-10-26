package com.ciot.networklib;

import android.util.Log;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.ShellUtils;
import io.reactivex.observers.DisposableObserver;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0002H\u0016¨\u0006\n"}, d2 = {"com/ciot/networklib/ApkDownloadInstallUtils$install$mInstallAppDisposable$2", "Lio/reactivex/observers/DisposableObserver;", "Lcom/blankj/utilcode/util/ShellUtils$CommandResult;", "onComplete", "", "onError", "throwable", "", "onNext", "commandResult", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: ApkDownloadInstallUtils.kt */
public final class ApkDownloadInstallUtils$install$mInstallAppDisposable$2 extends DisposableObserver<ShellUtils.CommandResult> {
    final /* synthetic */ String $apkPath;
    final /* synthetic */ ApkDownloadInstallUtils this$0;

    ApkDownloadInstallUtils$install$mInstallAppDisposable$2(ApkDownloadInstallUtils apkDownloadInstallUtils, String str) {
        this.this$0 = apkDownloadInstallUtils;
        this.$apkPath = str;
    }

    public void onNext(ShellUtils.CommandResult commandResult) {
        Intrinsics.checkNotNullParameter(commandResult, "commandResult");
        Log.d(this.this$0.TAG, "install: 开始");
    }

    public void onError(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "throwable");
        Log.d(this.this$0.TAG, "install: 错误");
        FileUtils.delete(this.$apkPath);
    }

    public void onComplete() {
        Log.d(this.this$0.TAG, "install: 完成");
        FileUtils.delete(this.$apkPath);
    }
}
