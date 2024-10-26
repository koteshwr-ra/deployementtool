package com.ciot.networklib.info;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0002H\u0016J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, d2 = {"com/ciot/networklib/info/NetWorkInfoHelper$init$2", "Lio/reactivex/Observer;", "", "onComplete", "", "onError", "e", "", "onNext", "t", "onSubscribe", "d", "Lio/reactivex/disposables/Disposable;", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: NetWorkInfoHelper.kt */
public final class NetWorkInfoHelper$init$2 implements Observer<Long> {
    final /* synthetic */ NetWorkInfoHelper this$0;

    public void onComplete() {
    }

    public void onError(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "e");
    }

    NetWorkInfoHelper$init$2(NetWorkInfoHelper netWorkInfoHelper) {
        this.this$0 = netWorkInfoHelper;
    }

    public /* bridge */ /* synthetic */ void onNext(Object obj) {
        onNext(((Number) obj).longValue());
    }

    public void onSubscribe(Disposable disposable) {
        Intrinsics.checkNotNullParameter(disposable, "d");
        this.this$0.disposable = disposable;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0030, code lost:
        if (r3.intValue() != 9) goto L_0x0076;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onNext(long r3) {
        /*
            r2 = this;
            com.ciot.networklib.info.NetWorkInfoHelper$CurrentNetInfo r3 = com.ciot.networklib.info.NetWorkInfoHelper.currentNetInfo
            r4 = 0
            if (r3 == 0) goto L_0x0076
            com.ciot.networklib.info.NetWorkInfoHelper$CurrentNetInfo r3 = com.ciot.networklib.info.NetWorkInfoHelper.currentNetInfo
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            java.lang.Integer r3 = r3.getCurrentNetType()
            r0 = 1
            if (r3 != 0) goto L_0x0016
            goto L_0x001c
        L_0x0016:
            int r3 = r3.intValue()
            if (r3 == r0) goto L_0x0032
        L_0x001c:
            com.ciot.networklib.info.NetWorkInfoHelper$CurrentNetInfo r3 = com.ciot.networklib.info.NetWorkInfoHelper.currentNetInfo
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            java.lang.Integer r3 = r3.getCurrentNetType()
            r1 = 9
            if (r3 != 0) goto L_0x002c
            goto L_0x0076
        L_0x002c:
            int r3 = r3.intValue()
            if (r3 != r1) goto L_0x0076
        L_0x0032:
            com.ciot.networklib.info.NetUtils$Companion r3 = com.ciot.networklib.info.NetUtils.Companion
            java.lang.String r1 = "www.baidu.com"
            java.lang.String r3 = r3.ping(r1)
            r1 = r3
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            java.lang.CharSequence r1 = kotlin.text.StringsKt.trim((java.lang.CharSequence) r1)
            java.lang.String r1 = r1.toString()
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            int r1 = r1.length()
            if (r1 <= 0) goto L_0x004f
            r1 = 1
            goto L_0x0050
        L_0x004f:
            r1 = 0
        L_0x0050:
            if (r1 == 0) goto L_0x0076
            int r3 = java.lang.Integer.parseInt(r3)
            com.ciot.networklib.info.NetWorkInfoHelper$CurrentNetInfo r1 = com.ciot.networklib.info.NetWorkInfoHelper.currentNetInfo
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            java.lang.Integer r1 = r1.getCurrentDelay()
            if (r1 == 0) goto L_0x0068
            r1 = 200(0xc8, float:2.8E-43)
            if (r3 <= r1) goto L_0x0068
            r4 = 1
        L_0x0068:
            com.ciot.networklib.info.NetWorkInfoHelper$CurrentNetInfo r0 = com.ciot.networklib.info.NetWorkInfoHelper.currentNetInfo
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r0.setCurrentDelay(r3)
        L_0x0076:
            com.ciot.networklib.info.NetWorkInfoHelper r3 = r2.this$0
            r3.getCurrentNetworkInfo(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ciot.networklib.info.NetWorkInfoHelper$init$2.onNext(long):void");
    }
}
