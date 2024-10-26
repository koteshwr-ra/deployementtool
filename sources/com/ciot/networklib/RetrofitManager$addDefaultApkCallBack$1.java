package com.ciot.networklib;

import android.util.Log;
import com.blankj.utilcode.util.FileUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u001a\u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\r\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\u000e"}, d2 = {"com/ciot/networklib/RetrofitManager$addDefaultApkCallBack$1", "Lcom/ciot/networklib/DownloadApkCallBack;", "onDownloadApkComplete", "", "apkPath", "", "md5", "mandatory", "", "onDownloadApkProgress", "progress", "", "onDownloadFail", "failString", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: RetrofitManager.kt */
public final class RetrofitManager$addDefaultApkCallBack$1 implements DownloadApkCallBack {
    final /* synthetic */ RetrofitManager this$0;

    RetrofitManager$addDefaultApkCallBack$1(RetrofitManager retrofitManager) {
        this.this$0 = retrofitManager;
    }

    public void onDownloadApkComplete(String str, String str2, boolean z) {
        Intrinsics.checkNotNullParameter(str, "apkPath");
        Intrinsics.checkNotNullParameter(str2, "md5");
        String access$getTAG$p = this.this$0.TAG;
        Log.d(access$getTAG$p, "onDownloadApkComplete: start install ->" + z);
        if (z) {
            ApkDownloadInstallUtils.Companion.instance().install(str, str2);
        }
    }

    public void onDownloadApkProgress(int i) {
        String access$getTAG$p = this.this$0.TAG;
        Log.d(access$getTAG$p, "onDownloadApkProgress progress: " + i);
    }

    public void onDownloadFail(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "apkPath");
        String access$getTAG$p = this.this$0.TAG;
        Log.d(access$getTAG$p, "onDownloadFail: " + str2);
        FileUtils.delete(str);
    }
}
