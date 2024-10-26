package com.ciot.networklib;

import com.blankj.utilcode.util.ThreadUtils;
import com.ciot.base.util.ContextUtil;
import com.ciot.base.util.MyLogUtils;
import com.ciot.diagnosis.DiagnosisManager;
import com.ciot.diagnosis.diagnosis.NetworkDiagnosis;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import kotlin.Metadata;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.ResponseBody;

@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0002H\u0016J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, d2 = {"com/ciot/networklib/RetrofitManager$init$1", "Lio/reactivex/Observer;", "Lokhttp3/ResponseBody;", "onComplete", "", "onError", "e", "", "onNext", "response", "onSubscribe", "d", "Lio/reactivex/disposables/Disposable;", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: RetrofitManager.kt */
public final class RetrofitManager$init$1 implements Observer<ResponseBody> {
    final /* synthetic */ RetrofitManager this$0;

    public void onComplete() {
    }

    RetrofitManager$init$1(RetrofitManager retrofitManager) {
        this.this$0 = retrofitManager;
    }

    public void onSubscribe(Disposable disposable) {
        Intrinsics.checkNotNullParameter(disposable, "d");
        this.this$0.mRobotAllowDisable = disposable;
        this.this$0.addSubscription(disposable);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x00d9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onNext(okhttp3.ResponseBody r8) {
        /*
            r7 = this;
            java.lang.String r0 = "response"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            java.lang.String r0 = new java.lang.String
            byte[] r8 = r8.bytes()
            java.nio.charset.Charset r1 = kotlin.text.Charsets.UTF_8
            r0.<init>(r8, r1)
            java.lang.Class<com.ciot.networklib.bean.allow.AllowResponse> r8 = com.ciot.networklib.bean.allow.AllowResponse.class
            java.lang.Object r8 = com.blankj.utilcode.util.GsonUtils.fromJson((java.lang.String) r0, r8)
            com.ciot.networklib.bean.allow.AllowResponse r8 = (com.ciot.networklib.bean.allow.AllowResponse) r8
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "robotAllow onNext->"
            r0.append(r1)
            java.lang.String r1 = com.blankj.utilcode.util.GsonUtils.toJson(r8)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "NETWORK_TAG"
            com.ciot.base.util.MyLogUtils.Logi(r1, r0)
            boolean r0 = r8.isSuccess()
            if (r0 == 0) goto L_0x00f9
            com.ciot.networklib.bean.allow.AllowResponse$DataBean r0 = r8.getData()
            java.lang.String r2 = "allowResponse.data"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r2)
            java.lang.String r2 = r0.getHost()
            com.ciot.base.util.AppSpUtil r3 = com.ciot.base.util.AppSpUtil.getInstance()
            r3.setTcpIp(r2)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "save dymamic tcpid:"
            r3.append(r4)
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            com.ciot.base.util.MyLogUtils.Logd(r1, r2)
            java.lang.String r1 = r0.getSemantics_type()
            java.lang.String r2 = "cloud_trails"
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r2)
            r3 = 0
            r4 = 1
            if (r1 == 0) goto L_0x00a1
            java.lang.String r1 = r0.getCloud_trails()
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            if (r1 == 0) goto L_0x007f
            int r1 = r1.length()
            if (r1 != 0) goto L_0x007d
            goto L_0x007f
        L_0x007d:
            r1 = 0
            goto L_0x0080
        L_0x007f:
            r1 = 1
        L_0x0080:
            if (r1 != 0) goto L_0x00a1
            com.ciot.base.util.AppSpUtil r1 = com.ciot.base.util.AppSpUtil.getInstance()
            java.lang.String r5 = r0.getCloud_trails()
            r1.setVoiceIp(r5)
            com.ciot.networklib.RetrofitManager r1 = r7.this$0
            r1.mSemanticIntentType = r4
            com.ciot.networklib.RetrofitManager r1 = r7.this$0
            java.lang.String r5 = r0.getCloud_trails()
            java.lang.String r6 = "data.cloud_trails"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)
            r1.setSemanticIntentBaseUrl(r5)
            goto L_0x00c0
        L_0x00a1:
            com.ciot.base.util.AppSpUtil r1 = com.ciot.base.util.AppSpUtil.getInstance()
            java.lang.String r5 = r0.getSelf_study()
            r1.setVoiceIp(r5)
            com.ciot.networklib.RetrofitManager r1 = r7.this$0
            r5 = 2
            r1.mSemanticIntentType = r5
            com.ciot.networklib.RetrofitManager r1 = r7.this$0
            java.lang.String r5 = r0.getSelf_study()
            java.lang.String r6 = "data.self_study"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)
            r1.setSemanticIntentBaseUrl(r5)
        L_0x00c0:
            com.ciot.base.util.AppSpUtil r1 = com.ciot.base.util.AppSpUtil.getInstance()
            java.lang.String r5 = r0.getSelf_study()
            r1.setZiyanVoiceIP(r5)
            com.ciot.base.util.AppSpUtil r1 = com.ciot.base.util.AppSpUtil.getInstance()
            java.lang.String r5 = r0.getSemantics_type()
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r2)
            if (r2 == 0) goto L_0x00ee
            java.lang.String r0 = r0.getCloud_trails()
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            if (r0 == 0) goto L_0x00ea
            int r0 = r0.length()
            if (r0 != 0) goto L_0x00e8
            goto L_0x00ea
        L_0x00e8:
            r0 = 0
            goto L_0x00eb
        L_0x00ea:
            r0 = 1
        L_0x00eb:
            if (r0 != 0) goto L_0x00ee
            r3 = 1
        L_0x00ee:
            r1.setIsYunJi(r3)
            org.greenrobot.eventbus.EventBus r0 = org.greenrobot.eventbus.EventBus.getDefault()
            r0.post(r8)
            goto L_0x010c
        L_0x00f9:
            com.ciot.diagnosis.DiagnosisManager r8 = com.ciot.diagnosis.DiagnosisManager.getInstance()
            com.ciot.diagnosis.diagnosis.NetworkDiagnosis r8 = r8.getNetworkDiagnosis()
            com.ciot.diagnosis.diagnosis.NetworkDiagnosis$SubNetworkDiagnosis r8 = r8.getSubNetworkDiagnosis()
            r0 = 7002(0x1b5a, float:9.812E-42)
            int r1 = com.ciot.networklib.R.string.diagnosis_network_error_robot_allow
            r8.setStatus((int) r0, (int) r1)
        L_0x010c:
            com.ciot.networklib.RetrofitManager r8 = r7.this$0
            r8.initRobot()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ciot.networklib.RetrofitManager$init$1.onNext(okhttp3.ResponseBody):void");
    }

    public void onError(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "e");
        if (!this.this$0.isNeedRetry || this.this$0.retryCount >= 20) {
            MyLogUtils.Loge("NETWORK_TAG", "robotAllow error->" + th.getMessage());
            Function4 access$getMLoginListener$p = this.this$0.mLoginListener;
            if (access$getMLoginListener$p != null) {
                access$getMLoginListener$p.invoke(false, false, false, 4);
            }
            RetrofitManager retrofitManager = this.this$0;
            String string = ContextUtil.getContext().getString(R.string.network__init_failed);
            Intrinsics.checkNotNullExpressionValue(string, "getContext().getString(R…ing.network__init_failed)");
            retrofitManager.sendNetErrorMsg(4, string);
            DiagnosisManager.getInstance().getNetworkDiagnosis().getSubNetworkDiagnosis().setStatus((int) NetworkDiagnosis.SubNetworkDiagnosis.CODE_ERROR_ROBOT_ALLOW, R.string.diagnosis_network_error_robot_allow);
            return;
        }
        ThreadUtils.getMainHandler().postDelayed(new Runnable() {
            public final void run() {
                RetrofitManager$init$1.m88onError$lambda0(RetrofitManager.this);
            }
        }, 3000);
        RetrofitManager retrofitManager2 = this.this$0;
        retrofitManager2.retryCount = retrofitManager2.retryCount + 1;
        MyLogUtils.Logd("NETWORK_TAG", "===retryCount====" + this.this$0.retryCount);
    }

    /* access modifiers changed from: private */
    /* renamed from: onError$lambda-0  reason: not valid java name */
    public static final void m88onError$lambda0(RetrofitManager retrofitManager) {
        Intrinsics.checkNotNullParameter(retrofitManager, "this$0");
        retrofitManager.init();
    }
}
