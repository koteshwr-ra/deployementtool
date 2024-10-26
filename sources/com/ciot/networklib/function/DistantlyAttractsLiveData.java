package com.ciot.networklib.function;

import androidx.lifecycle.MutableLiveData;
import com.ciot.networklib.bean.DistantlyAttractBean;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00072\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001:\u0001\u0007B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\b"}, d2 = {"Lcom/ciot/networklib/function/DistantlyAttractsLiveData;", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/ciot/networklib/bean/DistantlyAttractBean;", "()V", "getByPeriod", "", "Companion", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: DistantlyAttractsLiveData.kt */
public final class DistantlyAttractsLiveData extends MutableLiveData<List<? extends DistantlyAttractBean>> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static DistantlyAttractsLiveData sInstance;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/ciot/networklib/function/DistantlyAttractsLiveData$Companion;", "", "()V", "sInstance", "Lcom/ciot/networklib/function/DistantlyAttractsLiveData;", "get", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: DistantlyAttractsLiveData.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DistantlyAttractsLiveData get() {
            DistantlyAttractsLiveData distantlyAttractsLiveData;
            if (DistantlyAttractsLiveData.sInstance != null) {
                distantlyAttractsLiveData = DistantlyAttractsLiveData.sInstance;
                if (distantlyAttractsLiveData == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("sInstance");
                    distantlyAttractsLiveData = null;
                }
            } else {
                distantlyAttractsLiveData = new DistantlyAttractsLiveData();
            }
            DistantlyAttractsLiveData.sInstance = distantlyAttractsLiveData;
            DistantlyAttractsLiveData access$getSInstance$cp = DistantlyAttractsLiveData.sInstance;
            if (access$getSInstance$cp != null) {
                return access$getSInstance$cp;
            }
            Intrinsics.throwUninitializedPropertyAccessException("sInstance");
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0025 A[Catch:{ Exception -> 0x0036 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String getByPeriod() {
        /*
            r11 = this;
            java.lang.Object r0 = r11.getValue()
            java.util.List r0 = (java.util.List) r0
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x0037
            com.ciot.base.storage.MySpUtils r3 = com.ciot.base.storage.MySpUtils.getInstance()     // Catch:{ Exception -> 0x0036 }
            java.lang.String r4 = "SP_DISTANTLY_ATTRACTS"
            java.lang.String r3 = r3.getString(r4)     // Catch:{ Exception -> 0x0036 }
            r4 = r3
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4     // Catch:{ Exception -> 0x0036 }
            if (r4 == 0) goto L_0x0022
            boolean r4 = kotlin.text.StringsKt.isBlank(r4)     // Catch:{ Exception -> 0x0036 }
            if (r4 == 0) goto L_0x0020
            goto L_0x0022
        L_0x0020:
            r4 = 0
            goto L_0x0023
        L_0x0022:
            r4 = 1
        L_0x0023:
            if (r4 != 0) goto L_0x0037
            com.ciot.networklib.function.DistantlyAttractsLiveData$getByPeriod$1 r4 = new com.ciot.networklib.function.DistantlyAttractsLiveData$getByPeriod$1     // Catch:{ Exception -> 0x0036 }
            r4.<init>()     // Catch:{ Exception -> 0x0036 }
            java.lang.reflect.Type r4 = r4.getType()     // Catch:{ Exception -> 0x0036 }
            java.lang.Object r3 = com.blankj.utilcode.util.GsonUtils.fromJson((java.lang.String) r3, (java.lang.reflect.Type) r4)     // Catch:{ Exception -> 0x0036 }
            java.util.List r3 = (java.util.List) r3     // Catch:{ Exception -> 0x0036 }
            r0 = r3
            goto L_0x0037
        L_0x0036:
        L_0x0037:
            r3 = r0
            java.util.Collection r3 = (java.util.Collection) r3
            if (r3 == 0) goto L_0x0045
            boolean r3 = r3.isEmpty()
            if (r3 == 0) goto L_0x0043
            goto L_0x0045
        L_0x0043:
            r3 = 0
            goto L_0x0046
        L_0x0045:
            r3 = 1
        L_0x0046:
            r4 = 0
            if (r3 == 0) goto L_0x004a
            return r4
        L_0x004a:
            java.util.Calendar r3 = java.util.Calendar.getInstance()
            r5 = 11
            int r5 = r3.get(r5)
            float r5 = (float) r5
            r6 = 12
            int r3 = r3.get(r6)
            float r3 = (float) r3
            r6 = 1114636288(0x42700000, float:60.0)
            float r3 = r3 / r6
            float r5 = r5 + r3
            r3 = 1086324736(0x40c00000, float:6.0)
            r6 = 1090519040(0x41000000, float:8.0)
            int r3 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r3 < 0) goto L_0x006f
            int r3 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            if (r3 >= 0) goto L_0x006f
            java.lang.String r3 = com.ciot.networklib.function.Attract_CONST.MORNING
            goto L_0x009a
        L_0x006f:
            r7 = 4622663542519103488(0x4027000000000000, double:11.5)
            int r3 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            if (r3 < 0) goto L_0x007d
            double r9 = (double) r5
            int r3 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r3 >= 0) goto L_0x007d
            java.lang.String r3 = com.ciot.networklib.function.Attract_CONST.FORENOON
            goto L_0x009a
        L_0x007d:
            double r9 = (double) r5
            r3 = 1096810496(0x41600000, float:14.0)
            int r6 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r6 < 0) goto L_0x008b
            int r6 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r6 >= 0) goto L_0x008b
            java.lang.String r3 = com.ciot.networklib.function.Attract_CONST.NOON
            goto L_0x009a
        L_0x008b:
            int r3 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r3 < 0) goto L_0x0098
            r3 = 1099956224(0x41900000, float:18.0)
            int r3 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r3 >= 0) goto L_0x0098
            java.lang.String r3 = com.ciot.networklib.function.Attract_CONST.AFTERNOON
            goto L_0x009a
        L_0x0098:
            java.lang.String r3 = com.ciot.networklib.function.Attract_CONST.NIGHT
        L_0x009a:
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.util.Iterator r0 = r0.iterator()
        L_0x00a3:
            boolean r6 = r0.hasNext()
            if (r6 == 0) goto L_0x00ff
            java.lang.Object r6 = r0.next()
            com.ciot.networklib.bean.DistantlyAttractBean r6 = (com.ciot.networklib.bean.DistantlyAttractBean) r6
            java.util.List<java.lang.String> r7 = r6.attractTime
            java.util.Collection r7 = (java.util.Collection) r7
            if (r7 == 0) goto L_0x00be
            boolean r7 = r7.isEmpty()
            if (r7 == 0) goto L_0x00bc
            goto L_0x00be
        L_0x00bc:
            r7 = 0
            goto L_0x00bf
        L_0x00be:
            r7 = 1
        L_0x00bf:
            if (r7 == 0) goto L_0x00c2
            goto L_0x00a3
        L_0x00c2:
            java.util.List<java.lang.String> r7 = r6.attractTime
            java.util.Iterator r7 = r7.iterator()
        L_0x00c8:
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto L_0x00a3
            java.lang.Object r8 = r7.next()
            java.lang.String r8 = (java.lang.String) r8
            r9 = r8
            java.lang.CharSequence r9 = (java.lang.CharSequence) r9
            if (r9 == 0) goto L_0x00e2
            boolean r10 = kotlin.text.StringsKt.isBlank(r9)
            if (r10 == 0) goto L_0x00e0
            goto L_0x00e2
        L_0x00e0:
            r10 = 0
            goto L_0x00e3
        L_0x00e2:
            r10 = 1
        L_0x00e3:
            if (r10 == 0) goto L_0x00e6
            goto L_0x00c8
        L_0x00e6:
            java.lang.String r10 = "str"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r10)
            java.lang.CharSequence r8 = kotlin.text.StringsKt.trim((java.lang.CharSequence) r9)
            java.lang.String r8 = r8.toString()
            boolean r8 = kotlin.text.StringsKt.equals(r8, r3, r2)
            if (r8 == 0) goto L_0x00c8
            java.lang.String r6 = r6.broadcast
            r5.add(r6)
            goto L_0x00a3
        L_0x00ff:
            boolean r0 = r5.isEmpty()
            if (r0 == 0) goto L_0x0108
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x0115
        L_0x0108:
            java.util.Collection r5 = (java.util.Collection) r5
            kotlin.random.Random$Default r0 = kotlin.random.Random.Default
            kotlin.random.Random r0 = (kotlin.random.Random) r0
            java.lang.Object r0 = kotlin.collections.CollectionsKt.random(r5, r0)
            r4 = r0
            java.lang.String r4 = (java.lang.String) r4
        L_0x0115:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ciot.networklib.function.DistantlyAttractsLiveData.getByPeriod():java.lang.String");
    }
}
