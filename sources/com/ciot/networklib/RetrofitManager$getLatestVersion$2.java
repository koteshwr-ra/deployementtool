package com.ciot.networklib;

import android.text.TextUtils;
import android.util.Log;
import androidx.lifecycle.CoroutineLiveDataKt;
import com.ciot.base.util.ContextUtil;
import com.ciot.base.util.MyLogUtils;
import com.ciot.networklib.bean.version.VersionBean;
import com.google.gson.Gson;
import io.reactivex.disposables.Disposable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import okhttp3.ResponseBody;

@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0014J\b\u0010\u0007\u001a\u00020\u0004H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0002H\u0016¨\u0006\r"}, d2 = {"com/ciot/networklib/RetrofitManager$getLatestVersion$2", "Lcom/ciot/networklib/BaseObserver;", "Lokhttp3/ResponseBody;", "onChildSubscribe", "", "d", "Lio/reactivex/disposables/Disposable;", "onComplete", "onError", "throwable", "", "onSuccess", "responseBody", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: RetrofitManager.kt */
public final class RetrofitManager$getLatestVersion$2 extends BaseObserver<ResponseBody> {
    final /* synthetic */ RetrofitManager this$0;

    /* access modifiers changed from: protected */
    public void onChildSubscribe(Disposable disposable) {
        Intrinsics.checkNotNullParameter(disposable, "d");
    }

    public void onComplete() {
    }

    RetrofitManager$getLatestVersion$2(RetrofitManager retrofitManager) {
        this.this$0 = retrofitManager;
    }

    public void onSuccess(ResponseBody responseBody) {
        Intrinsics.checkNotNullParameter(responseBody, "responseBody");
        try {
            String str = new String(responseBody.bytes(), Charsets.UTF_8);
            if (!TextUtils.isEmpty(str)) {
                if (!Intrinsics.areEqual((Object) str, (Object) "{}")) {
                    String access$getTAG$p = this.this$0.TAG;
                    Log.d(access$getTAG$p, "getLatestVersion: " + str);
                    VersionBean versionBean = (VersionBean) new Gson().fromJson(str, VersionBean.class);
                    int i = ContextUtil.getContext().getPackageManager().getPackageInfo(ContextUtil.getContext().getPackageName(), 0).versionCode;
                    Intrinsics.checkNotNull(versionBean);
                    int versionCode = versionBean.getVersionCode();
                    String str2 = ContextUtil.getContext().getApplicationInfo().packageName;
                    String access$getTAG$p2 = this.this$0.TAG;
                    MyLogUtils.Logi(access$getTAG$p2, "applicationId: " + str2 + "  webVersionCode: " + versionCode + "   localVersionCode:" + i);
                    if (versionCode > i && str2.equals(versionBean.getPackageName())) {
                        this.this$0.setMVersionBean(versionBean);
                        this.this$0.addDefaultApkCallBack();
                        if (versionBean.getMandatory()) {
                            ApkDownloadInstallUtils.Companion.instance().downloadApk(versionBean);
                        }
                    }
                    String access$getTAG$p3 = this.this$0.TAG;
                    MyLogUtils.Logw(access$getTAG$p3, "getLatestVersion mVersionBean:" + versionBean);
                    return;
                }
            }
            String access$getTAG$p4 = this.this$0.TAG;
            Log.d(access$getTAG$p4, "getLatestVersion: ----back is {},reget times " + this.this$0.getCurrentRegetVersionTimes() + "----");
            if (this.this$0.getCurrentRegetVersionTimes() < 2) {
                this.this$0.getLatestVersion(CoroutineLiveDataKt.DEFAULT_TIMEOUT);
            }
        } catch (Exception e) {
            String access$getTAG$p5 = this.this$0.TAG;
            MyLogUtils.Logw(access$getTAG$p5, "getLatestVersion Exception:" + e);
        }
    }

    public void onError(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "throwable");
        super.onError(th);
        String access$getTAG$p = this.this$0.TAG;
        MyLogUtils.Logd(access$getTAG$p, "getLatestVersion throwable:" + th);
    }
}
