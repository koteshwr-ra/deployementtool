package com.ciot.networklib.info;

import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import com.ciot.networklib.info.NetWorkInfoHelper;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0017¨\u0006\u0006"}, d2 = {"com/ciot/networklib/info/NetWorkInfoHelper$init$1", "Landroid/telephony/PhoneStateListener;", "onSignalStrengthsChanged", "", "signalStrength", "Landroid/telephony/SignalStrength;", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: NetWorkInfoHelper.kt */
public final class NetWorkInfoHelper$init$1 extends PhoneStateListener {
    final /* synthetic */ NetWorkInfoHelper this$0;

    NetWorkInfoHelper$init$1(NetWorkInfoHelper netWorkInfoHelper) {
        this.this$0 = netWorkInfoHelper;
    }

    public void onSignalStrengthsChanged(SignalStrength signalStrength) {
        Intrinsics.checkNotNullParameter(signalStrength, "signalStrength");
        super.onSignalStrengthsChanged(signalStrength);
        boolean z = false;
        Method method = signalStrength.getClass().getMethod("getDbm", new Class[0]);
        Intrinsics.checkNotNullExpressionValue(method, "signalStrength.javaClass.getMethod(\"getDbm\")");
        Object invoke = method.invoke(signalStrength, new Object[0]);
        if (invoke != null) {
            int intValue = ((Integer) invoke).intValue();
            NetWorkInfoHelper.CurrentNetInfo access$getCurrentNetInfo$cp = NetWorkInfoHelper.currentNetInfo;
            Intrinsics.checkNotNull(access$getCurrentNetInfo$cp);
            access$getCurrentNetInfo$cp.setCurrentRssi(Integer.valueOf(intValue));
            NetWorkInfoHelper.CurrentNetInfo access$getCurrentNetInfo$cp2 = NetWorkInfoHelper.currentNetInfo;
            Intrinsics.checkNotNull(access$getCurrentNetInfo$cp2);
            Integer currentRssiLevel = access$getCurrentNetInfo$cp2.getCurrentRssiLevel();
            int level = signalStrength.getLevel();
            if (currentRssiLevel == null || currentRssiLevel.intValue() != level) {
                z = true;
            }
            NetWorkInfoHelper.CurrentNetInfo access$getCurrentNetInfo$cp3 = NetWorkInfoHelper.currentNetInfo;
            Intrinsics.checkNotNull(access$getCurrentNetInfo$cp3);
            access$getCurrentNetInfo$cp3.setCurrentRssiLevel(Integer.valueOf(signalStrength.getLevel()));
            this.this$0.getCurrentNetworkInfo(z);
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
    }
}
