package com.ciot.networklib.function;

import androidx.lifecycle.MutableLiveData;
import com.ciot.networklib.NearbyAttractList;
import com.ciot.realm.db.PersonInfoBean;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00122\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0012B\u0005¢\u0006\u0002\u0010\u0003J\"\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0002J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\u0010\u001a\u00020\u0005J*\u0010\u0011\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0010\u001a\u00020\u00052\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0002R\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u0013"}, d2 = {"Lcom/ciot/networklib/function/NearbyAttractsLiveData;", "Landroidx/lifecycle/MutableLiveData;", "Lcom/ciot/networklib/NearbyAttractList;", "()V", "person_default", "Lcom/ciot/realm/db/PersonInfoBean;", "getPerson_default", "()Lcom/ciot/realm/db/PersonInfoBean;", "setPerson_default", "(Lcom/ciot/realm/db/PersonInfoBean;)V", "common", "", "period", "", "Lcom/ciot/networklib/NearbyAttractList$NearbyAttract;", "getByPeriod", "person", "group", "Companion", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: NearbyAttractsLiveData.kt */
public final class NearbyAttractsLiveData extends MutableLiveData<NearbyAttractList> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static NearbyAttractsLiveData sInstance;
    private PersonInfoBean person_default;

    public NearbyAttractsLiveData() {
        PersonInfoBean personInfoBean = new PersonInfoBean();
        this.person_default = personInfoBean;
        personInfoBean.setType(2);
    }

    public final PersonInfoBean getPerson_default() {
        return this.person_default;
    }

    public final void setPerson_default(PersonInfoBean personInfoBean) {
        Intrinsics.checkNotNullParameter(personInfoBean, "<set-?>");
        this.person_default = personInfoBean;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/ciot/networklib/function/NearbyAttractsLiveData$Companion;", "", "()V", "sInstance", "Lcom/ciot/networklib/function/NearbyAttractsLiveData;", "get", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: NearbyAttractsLiveData.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final NearbyAttractsLiveData get() {
            NearbyAttractsLiveData nearbyAttractsLiveData;
            if (NearbyAttractsLiveData.sInstance != null) {
                nearbyAttractsLiveData = NearbyAttractsLiveData.sInstance;
                if (nearbyAttractsLiveData == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("sInstance");
                    nearbyAttractsLiveData = null;
                }
            } else {
                nearbyAttractsLiveData = new NearbyAttractsLiveData();
            }
            NearbyAttractsLiveData.sInstance = nearbyAttractsLiveData;
            NearbyAttractsLiveData access$getSInstance$cp = NearbyAttractsLiveData.sInstance;
            if (access$getSInstance$cp != null) {
                return access$getSInstance$cp;
            }
            Intrinsics.throwUninitializedPropertyAccessException("sInstance");
            return null;
        }
    }

    public static /* synthetic */ String getByPeriod$default(NearbyAttractsLiveData nearbyAttractsLiveData, PersonInfoBean personInfoBean, int i, Object obj) {
        if ((i & 1) != 0) {
            personInfoBean = nearbyAttractsLiveData.person_default;
        }
        return nearbyAttractsLiveData.getByPeriod(personInfoBean);
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x002e A[Catch:{ Exception -> 0x0038 }] */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x01fe  */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x0201  */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x00d8 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0110  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String getByPeriod(com.ciot.realm.db.PersonInfoBean r21) {
        /*
            r20 = this;
            r0 = r20
            r1 = r21
            java.lang.String r2 = "person"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r2)
            java.lang.Object r2 = r20.getValue()
            com.ciot.networklib.NearbyAttractList r2 = (com.ciot.networklib.NearbyAttractList) r2
            r3 = 0
            r4 = 1
            if (r2 != 0) goto L_0x0039
            com.ciot.base.storage.MySpUtils r5 = com.ciot.base.storage.MySpUtils.getInstance()     // Catch:{ Exception -> 0x0038 }
            java.lang.String r6 = "SP_NEARBY_ATTRACTS"
            java.lang.String r5 = r5.getString(r6)     // Catch:{ Exception -> 0x0038 }
            r6 = r5
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6     // Catch:{ Exception -> 0x0038 }
            if (r6 == 0) goto L_0x002b
            boolean r6 = kotlin.text.StringsKt.isBlank(r6)     // Catch:{ Exception -> 0x0038 }
            if (r6 == 0) goto L_0x0029
            goto L_0x002b
        L_0x0029:
            r6 = 0
            goto L_0x002c
        L_0x002b:
            r6 = 1
        L_0x002c:
            if (r6 != 0) goto L_0x0039
            java.lang.Class<com.ciot.networklib.NearbyAttractList> r6 = com.ciot.networklib.NearbyAttractList.class
            java.lang.Object r5 = com.blankj.utilcode.util.GsonUtils.fromJson((java.lang.String) r5, r6)     // Catch:{ Exception -> 0x0038 }
            com.ciot.networklib.NearbyAttractList r5 = (com.ciot.networklib.NearbyAttractList) r5     // Catch:{ Exception -> 0x0038 }
            r2 = r5
            goto L_0x0039
        L_0x0038:
        L_0x0039:
            r5 = 0
            if (r2 != 0) goto L_0x003d
            return r5
        L_0x003d:
            java.util.Calendar r6 = java.util.Calendar.getInstance()
            r7 = 11
            int r7 = r6.get(r7)
            float r7 = (float) r7
            r8 = 12
            int r6 = r6.get(r8)
            float r6 = (float) r6
            r8 = 1114636288(0x42700000, float:60.0)
            float r6 = r6 / r8
            float r7 = r7 + r6
            r6 = 1086324736(0x40c00000, float:6.0)
            r8 = 1090519040(0x41000000, float:8.0)
            int r6 = (r7 > r6 ? 1 : (r7 == r6 ? 0 : -1))
            if (r6 < 0) goto L_0x006a
            int r6 = (r7 > r8 ? 1 : (r7 == r8 ? 0 : -1))
            if (r6 >= 0) goto L_0x006a
            java.lang.String r6 = com.ciot.networklib.function.Attract_CONST.MORNING_WELCOME
            java.lang.String r7 = "MORNING_WELCOME"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)
            java.lang.String r7 = com.ciot.networklib.function.Attract_CONST.MORNING
        L_0x0068:
            r10 = r6
            goto L_0x00b2
        L_0x006a:
            r9 = 4622663542519103488(0x4027000000000000, double:11.5)
            int r6 = (r7 > r8 ? 1 : (r7 == r8 ? 0 : -1))
            if (r6 < 0) goto L_0x007f
            double r11 = (double) r7
            int r6 = (r11 > r9 ? 1 : (r11 == r9 ? 0 : -1))
            if (r6 >= 0) goto L_0x007f
            java.lang.String r6 = com.ciot.networklib.function.Attract_CONST.FORENOON_WELCOME
            java.lang.String r7 = "FORENOON_WELCOME"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)
            java.lang.String r7 = com.ciot.networklib.function.Attract_CONST.FORENOON
            goto L_0x0068
        L_0x007f:
            double r11 = (double) r7
            r6 = 1096810496(0x41600000, float:14.0)
            int r8 = (r11 > r9 ? 1 : (r11 == r9 ? 0 : -1))
            if (r8 < 0) goto L_0x0094
            int r8 = (r7 > r6 ? 1 : (r7 == r6 ? 0 : -1))
            if (r8 >= 0) goto L_0x0094
            java.lang.String r6 = com.ciot.networklib.function.Attract_CONST.NOON_WELCOME
            java.lang.String r7 = "NOON_WELCOME"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)
            java.lang.String r7 = com.ciot.networklib.function.Attract_CONST.NOON
            goto L_0x0068
        L_0x0094:
            int r6 = (r7 > r6 ? 1 : (r7 == r6 ? 0 : -1))
            if (r6 < 0) goto L_0x00a8
            r6 = 1099956224(0x41900000, float:18.0)
            int r6 = (r7 > r6 ? 1 : (r7 == r6 ? 0 : -1))
            if (r6 >= 0) goto L_0x00a8
            java.lang.String r6 = com.ciot.networklib.function.Attract_CONST.AFTERNOON_WELCOME
            java.lang.String r7 = "AFTERNOON_WELCOME"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)
            java.lang.String r7 = com.ciot.networklib.function.Attract_CONST.AFTERNOON
            goto L_0x0068
        L_0x00a8:
            java.lang.String r6 = com.ciot.networklib.function.Attract_CONST.NIGHT_WELCOME
            java.lang.String r7 = "NIGHT_WELCOME"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)
            java.lang.String r7 = com.ciot.networklib.function.Attract_CONST.NIGHT
            goto L_0x0068
        L_0x00b2:
            java.util.List<com.ciot.networklib.NearbyAttractList$NearbyAttract> r6 = r2.specific
            java.util.Collection r6 = (java.util.Collection) r6
            if (r6 == 0) goto L_0x00c1
            boolean r6 = r6.isEmpty()
            if (r6 == 0) goto L_0x00bf
            goto L_0x00c1
        L_0x00bf:
            r6 = 0
            goto L_0x00c2
        L_0x00c1:
            r6 = 1
        L_0x00c2:
            if (r6 != 0) goto L_0x0148
            java.util.List<com.ciot.networklib.NearbyAttractList$NearbyAttract> r6 = r2.specific
            java.lang.String r8 = "list.specific"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r8)
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            java.util.Collection r8 = (java.util.Collection) r8
            java.util.Iterator r6 = r6.iterator()
        L_0x00d8:
            boolean r9 = r6.hasNext()
            if (r9 == 0) goto L_0x0114
            java.lang.Object r9 = r6.next()
            r11 = r9
            com.ciot.networklib.NearbyAttractList$NearbyAttract r11 = (com.ciot.networklib.NearbyAttractList.NearbyAttract) r11
            java.lang.String r12 = r11.personId
            java.lang.String r13 = r21.getUniqueId()
            boolean r12 = r12.equals(r13)
            if (r12 == 0) goto L_0x010d
            java.util.List<java.lang.String> r12 = r11.welcomeTime
            boolean r12 = r12.contains(r7)
            if (r12 == 0) goto L_0x010d
            java.util.List<java.lang.String> r11 = r11.contents
            java.util.Collection r11 = (java.util.Collection) r11
            if (r11 == 0) goto L_0x0108
            boolean r11 = r11.isEmpty()
            if (r11 == 0) goto L_0x0106
            goto L_0x0108
        L_0x0106:
            r11 = 0
            goto L_0x0109
        L_0x0108:
            r11 = 1
        L_0x0109:
            if (r11 != 0) goto L_0x010d
            r11 = 1
            goto L_0x010e
        L_0x010d:
            r11 = 0
        L_0x010e:
            if (r11 == 0) goto L_0x00d8
            r8.add(r9)
            goto L_0x00d8
        L_0x0114:
            java.util.List r8 = (java.util.List) r8
            java.util.Collection r8 = (java.util.Collection) r8
            boolean r6 = r8.isEmpty()
            if (r6 != 0) goto L_0x0148
            kotlin.random.Random$Default r6 = kotlin.random.Random.Default
            kotlin.random.Random r6 = (kotlin.random.Random) r6
            java.lang.Object r6 = kotlin.collections.CollectionsKt.random(r8, r6)
            com.ciot.networklib.NearbyAttractList$NearbyAttract r6 = (com.ciot.networklib.NearbyAttractList.NearbyAttract) r6
            java.util.List<java.lang.String> r6 = r6.contents
            r8 = r6
            java.util.Collection r8 = (java.util.Collection) r8
            if (r8 == 0) goto L_0x0138
            boolean r9 = r8.isEmpty()
            if (r9 == 0) goto L_0x0136
            goto L_0x0138
        L_0x0136:
            r9 = 0
            goto L_0x0139
        L_0x0138:
            r9 = 1
        L_0x0139:
            if (r9 != 0) goto L_0x0148
            java.lang.String r5 = "contents"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r5)
            kotlin.random.Random$Default r5 = kotlin.random.Random.Default
            kotlin.random.Random r5 = (kotlin.random.Random) r5
            java.lang.Object r5 = kotlin.collections.CollectionsKt.random(r8, r5)
        L_0x0148:
            r6 = r5
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            if (r6 == 0) goto L_0x0156
            boolean r6 = kotlin.text.StringsKt.isBlank(r6)
            if (r6 == 0) goto L_0x0154
            goto L_0x0156
        L_0x0154:
            r6 = 0
            goto L_0x0157
        L_0x0156:
            r6 = 1
        L_0x0157:
            if (r6 == 0) goto L_0x0176
            java.util.List<com.ciot.networklib.NearbyAttractList$NearbyAttract> r6 = r2.identity
            java.util.Collection r6 = (java.util.Collection) r6
            if (r6 == 0) goto L_0x0168
            boolean r6 = r6.isEmpty()
            if (r6 == 0) goto L_0x0166
            goto L_0x0168
        L_0x0166:
            r6 = 0
            goto L_0x0169
        L_0x0168:
            r6 = 1
        L_0x0169:
            if (r6 != 0) goto L_0x0176
            java.util.List<com.ciot.networklib.NearbyAttractList$NearbyAttract> r5 = r2.identity
            java.lang.String r6 = "list.identity"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)
            java.lang.String r5 = r0.group(r1, r7, r5)
        L_0x0176:
            r6 = r5
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            if (r6 == 0) goto L_0x0184
            boolean r6 = kotlin.text.StringsKt.isBlank(r6)
            if (r6 == 0) goto L_0x0182
            goto L_0x0184
        L_0x0182:
            r6 = 0
            goto L_0x0185
        L_0x0184:
            r6 = 1
        L_0x0185:
            if (r6 == 0) goto L_0x01a4
            java.util.List<com.ciot.networklib.NearbyAttractList$NearbyAttract> r6 = r2.common
            java.util.Collection r6 = (java.util.Collection) r6
            if (r6 == 0) goto L_0x0196
            boolean r6 = r6.isEmpty()
            if (r6 == 0) goto L_0x0194
            goto L_0x0196
        L_0x0194:
            r6 = 0
            goto L_0x0197
        L_0x0196:
            r6 = 1
        L_0x0197:
            if (r6 != 0) goto L_0x01a4
            java.util.List<com.ciot.networklib.NearbyAttractList$NearbyAttract> r2 = r2.common
            java.lang.String r5 = "list.common"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r5)
            java.lang.String r5 = r0.common(r7, r2)
        L_0x01a4:
            r2 = r5
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            if (r2 == 0) goto L_0x01b2
            boolean r2 = kotlin.text.StringsKt.isBlank(r2)
            if (r2 == 0) goto L_0x01b0
            goto L_0x01b2
        L_0x01b0:
            r2 = 0
            goto L_0x01b3
        L_0x01b2:
            r2 = 1
        L_0x01b3:
            if (r2 != 0) goto L_0x022a
            java.lang.String r2 = r1.sex
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            if (r2 == 0) goto L_0x01c1
            boolean r2 = kotlin.text.StringsKt.isBlank(r2)
            if (r2 == 0) goto L_0x01c2
        L_0x01c1:
            r3 = 1
        L_0x01c2:
            java.lang.String r2 = ""
            if (r3 == 0) goto L_0x01c8
        L_0x01c6:
            r13 = r2
            goto L_0x01eb
        L_0x01c8:
            java.lang.String r3 = com.ciot.networklib.function.Attract_CONST.MALE
            java.lang.String r6 = r1.sex
            boolean r3 = kotlin.text.StringsKt.equals(r3, r6, r4)
            java.lang.String r6 = "{\n                    At…WELCOME\n                }"
            if (r3 == 0) goto L_0x01db
            java.lang.String r3 = com.ciot.networklib.function.Attract_CONST.MALE_WELCOME
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r6)
        L_0x01d9:
            r13 = r3
            goto L_0x01eb
        L_0x01db:
            java.lang.String r3 = com.ciot.networklib.function.Attract_CONST.FEMALE
            java.lang.String r7 = r1.sex
            boolean r3 = kotlin.text.StringsKt.equals(r3, r7, r4)
            if (r3 == 0) goto L_0x01c6
            java.lang.String r3 = com.ciot.networklib.function.Attract_CONST.FEMALE_WELCOME
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r6)
            goto L_0x01d9
        L_0x01eb:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
            r14 = r5
            java.lang.String r14 = (java.lang.String) r14
            java.lang.String r15 = com.ciot.networklib.function.Attract_CONST.TAG_NAME
            java.lang.String r3 = "TAG_NAME"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r15, r3)
            java.lang.String r1 = r21.getName()
            if (r1 != 0) goto L_0x0201
            r16 = r2
            goto L_0x0203
        L_0x0201:
            r16 = r1
        L_0x0203:
            r17 = 0
            r18 = 4
            r19 = 0
            java.lang.String r11 = kotlin.text.StringsKt.replace$default((java.lang.String) r14, (java.lang.String) r15, (java.lang.String) r16, (boolean) r17, (int) r18, (java.lang.Object) r19)
            java.lang.String r12 = com.ciot.networklib.function.Attract_CONST.TAG_SEX
            java.lang.String r1 = "TAG_SEX"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r1)
            r14 = 0
            r15 = 4
            r16 = 0
            java.lang.String r8 = kotlin.text.StringsKt.replace$default((java.lang.String) r11, (java.lang.String) r12, (java.lang.String) r13, (boolean) r14, (int) r15, (java.lang.Object) r16)
            java.lang.String r9 = com.ciot.networklib.function.Attract_CONST.TAG_TIME
            java.lang.String r1 = "TAG_TIME"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r1)
            r11 = 0
            r12 = 4
            r13 = 0
            java.lang.String r5 = kotlin.text.StringsKt.replace$default((java.lang.String) r8, (java.lang.String) r9, (java.lang.String) r10, (boolean) r11, (int) r12, (java.lang.Object) r13)
        L_0x022a:
            java.lang.String r5 = (java.lang.String) r5
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ciot.networklib.function.NearbyAttractsLiveData.getByPeriod(com.ciot.realm.db.PersonInfoBean):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:101:0x015b  */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x0023 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x0023 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00ce  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.String group(com.ciot.realm.db.PersonInfoBean r11, java.lang.String r12, java.util.List<? extends com.ciot.networklib.NearbyAttractList.NearbyAttract> r13) {
        /*
            r10 = this;
            r0 = r13
            java.util.Collection r0 = (java.util.Collection) r0
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x0010
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x000e
            goto L_0x0010
        L_0x000e:
            r0 = 0
            goto L_0x0011
        L_0x0010:
            r0 = 1
        L_0x0011:
            r3 = 0
            if (r0 == 0) goto L_0x0015
            return r3
        L_0x0015:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.Iterator r13 = r13.iterator()
        L_0x0023:
            boolean r5 = r13.hasNext()
            if (r5 == 0) goto L_0x0162
            java.lang.Object r5 = r13.next()
            com.ciot.networklib.NearbyAttractList$NearbyAttract r5 = (com.ciot.networklib.NearbyAttractList.NearbyAttract) r5
            java.util.List<java.lang.String> r6 = r5.sex
            java.util.Collection r6 = (java.util.Collection) r6
            if (r6 == 0) goto L_0x003e
            boolean r6 = r6.isEmpty()
            if (r6 == 0) goto L_0x003c
            goto L_0x003e
        L_0x003c:
            r6 = 0
            goto L_0x003f
        L_0x003e:
            r6 = 1
        L_0x003f:
            if (r6 == 0) goto L_0x0042
            goto L_0x0023
        L_0x0042:
            java.util.List<java.lang.String> r6 = r5.contents
            java.util.Collection r6 = (java.util.Collection) r6
            if (r6 == 0) goto L_0x0051
            boolean r6 = r6.isEmpty()
            if (r6 == 0) goto L_0x004f
            goto L_0x0051
        L_0x004f:
            r6 = 0
            goto L_0x0052
        L_0x0051:
            r6 = 1
        L_0x0052:
            if (r6 == 0) goto L_0x0055
            goto L_0x0023
        L_0x0055:
            java.util.List<java.lang.String> r6 = r5.welcomeTime
            java.util.Collection r6 = (java.util.Collection) r6
            if (r6 == 0) goto L_0x0064
            boolean r6 = r6.isEmpty()
            if (r6 == 0) goto L_0x0062
            goto L_0x0064
        L_0x0062:
            r6 = 0
            goto L_0x0065
        L_0x0064:
            r6 = 1
        L_0x0065:
            if (r6 == 0) goto L_0x0068
            goto L_0x0023
        L_0x0068:
            java.util.List<java.lang.String> r6 = r5.personType
            java.util.Collection r6 = (java.util.Collection) r6
            if (r6 == 0) goto L_0x0077
            boolean r6 = r6.isEmpty()
            if (r6 == 0) goto L_0x0075
            goto L_0x0077
        L_0x0075:
            r6 = 0
            goto L_0x0078
        L_0x0077:
            r6 = 1
        L_0x0078:
            if (r6 == 0) goto L_0x007b
            goto L_0x0023
        L_0x007b:
            java.lang.String r6 = r11.sex
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            if (r6 == 0) goto L_0x008a
            boolean r6 = kotlin.text.StringsKt.isBlank(r6)
            if (r6 == 0) goto L_0x0088
            goto L_0x008a
        L_0x0088:
            r6 = 0
            goto L_0x008b
        L_0x008a:
            r6 = 1
        L_0x008b:
            if (r6 == 0) goto L_0x008f
        L_0x008d:
            r6 = 1
            goto L_0x00ca
        L_0x008f:
            java.util.List<java.lang.String> r6 = r5.sex
            java.util.Iterator r6 = r6.iterator()
        L_0x0095:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x00c9
            java.lang.Object r7 = r6.next()
            java.lang.String r7 = (java.lang.String) r7
            r8 = r7
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8
            if (r8 == 0) goto L_0x00af
            boolean r9 = kotlin.text.StringsKt.isBlank(r8)
            if (r9 == 0) goto L_0x00ad
            goto L_0x00af
        L_0x00ad:
            r9 = 0
            goto L_0x00b0
        L_0x00af:
            r9 = 1
        L_0x00b0:
            if (r9 == 0) goto L_0x00b3
            goto L_0x0095
        L_0x00b3:
            java.lang.String r9 = "sex"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r9)
            java.lang.CharSequence r7 = kotlin.text.StringsKt.trim((java.lang.CharSequence) r8)
            java.lang.String r7 = r7.toString()
            java.lang.String r8 = r11.sex
            boolean r7 = kotlin.text.StringsKt.equals(r7, r8, r2)
            if (r7 == 0) goto L_0x0095
            goto L_0x008d
        L_0x00c9:
            r6 = 0
        L_0x00ca:
            if (r6 != 0) goto L_0x00ce
            goto L_0x0023
        L_0x00ce:
            java.util.List<java.lang.String> r6 = r5.welcomeTime
            java.util.Iterator r6 = r6.iterator()
        L_0x00d4:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x0107
            java.lang.Object r7 = r6.next()
            java.lang.String r7 = (java.lang.String) r7
            r8 = r7
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8
            if (r8 == 0) goto L_0x00ee
            boolean r9 = kotlin.text.StringsKt.isBlank(r8)
            if (r9 == 0) goto L_0x00ec
            goto L_0x00ee
        L_0x00ec:
            r9 = 0
            goto L_0x00ef
        L_0x00ee:
            r9 = 1
        L_0x00ef:
            if (r9 == 0) goto L_0x00f2
            goto L_0x00d4
        L_0x00f2:
            java.lang.String r9 = "s"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r9)
            java.lang.CharSequence r7 = kotlin.text.StringsKt.trim((java.lang.CharSequence) r8)
            java.lang.String r7 = r7.toString()
            boolean r7 = kotlin.text.StringsKt.equals(r7, r12, r2)
            if (r7 == 0) goto L_0x00d4
            r6 = 1
            goto L_0x0108
        L_0x0107:
            r6 = 0
        L_0x0108:
            if (r6 != 0) goto L_0x010c
            goto L_0x0023
        L_0x010c:
            boolean r6 = r11.isVip
            if (r6 == 0) goto L_0x0119
            java.util.List<java.lang.String> r6 = r5.personType
            java.lang.String r7 = com.ciot.networklib.function.Attract_CONST.VIP
            boolean r6 = r6.contains(r7)
            goto L_0x011a
        L_0x0119:
            r6 = 0
        L_0x011a:
            if (r6 == 0) goto L_0x0123
            java.util.List<java.lang.String> r5 = r5.contents
            r0.add(r5)
            goto L_0x0023
        L_0x0123:
            r6 = r0
            java.util.Collection r6 = (java.util.Collection) r6
            boolean r6 = r6.isEmpty()
            if (r6 == 0) goto L_0x0023
            int r6 = r11.getType()
            if (r6 == 0) goto L_0x014f
            if (r6 == r2) goto L_0x0144
            r7 = 2
            if (r6 == r7) goto L_0x0139
        L_0x0137:
            r6 = 0
            goto L_0x0159
        L_0x0139:
            java.util.List<java.lang.String> r6 = r5.personType
            if (r6 == 0) goto L_0x0137
            java.lang.String r7 = com.ciot.networklib.function.Attract_CONST.STRANGER
            boolean r6 = r6.contains(r7)
            goto L_0x0159
        L_0x0144:
            java.util.List<java.lang.String> r6 = r5.personType
            if (r6 == 0) goto L_0x0137
            java.lang.String r7 = com.ciot.networklib.function.Attract_CONST.EMPLOYEE
            boolean r6 = r6.contains(r7)
            goto L_0x0159
        L_0x014f:
            java.util.List<java.lang.String> r6 = r5.personType
            if (r6 == 0) goto L_0x0137
            java.lang.String r7 = com.ciot.networklib.function.Attract_CONST.VISITOR
            boolean r6 = r6.contains(r7)
        L_0x0159:
            if (r6 == 0) goto L_0x0023
            java.util.List<java.lang.String> r5 = r5.contents
            r4.add(r5)
            goto L_0x0023
        L_0x0162:
            java.util.Collection r0 = (java.util.Collection) r0
            boolean r11 = r0.isEmpty()
            r11 = r11 ^ r2
            if (r11 == 0) goto L_0x0190
            kotlin.random.Random$Default r11 = kotlin.random.Random.Default
            kotlin.random.Random r11 = (kotlin.random.Random) r11
            java.lang.Object r11 = kotlin.collections.CollectionsKt.random(r0, r11)
            java.util.List r11 = (java.util.List) r11
            java.util.Collection r11 = (java.util.Collection) r11
            if (r11 == 0) goto L_0x0182
            boolean r12 = r11.isEmpty()
            if (r12 == 0) goto L_0x0180
            goto L_0x0182
        L_0x0180:
            r12 = 0
            goto L_0x0183
        L_0x0182:
            r12 = 1
        L_0x0183:
            if (r12 != 0) goto L_0x0190
            kotlin.random.Random$Default r12 = kotlin.random.Random.Default
            kotlin.random.Random r12 = (kotlin.random.Random) r12
            java.lang.Object r11 = kotlin.collections.CollectionsKt.random(r11, r12)
            java.lang.String r11 = (java.lang.String) r11
            return r11
        L_0x0190:
            java.util.Collection r4 = (java.util.Collection) r4
            boolean r11 = r4.isEmpty()
            r11 = r11 ^ r2
            if (r11 == 0) goto L_0x01bb
            kotlin.random.Random$Default r11 = kotlin.random.Random.Default
            kotlin.random.Random r11 = (kotlin.random.Random) r11
            java.lang.Object r11 = kotlin.collections.CollectionsKt.random(r4, r11)
            java.util.List r11 = (java.util.List) r11
            java.util.Collection r11 = (java.util.Collection) r11
            if (r11 == 0) goto L_0x01ad
            boolean r12 = r11.isEmpty()
            if (r12 == 0) goto L_0x01ae
        L_0x01ad:
            r1 = 1
        L_0x01ae:
            if (r1 != 0) goto L_0x01bb
            kotlin.random.Random$Default r12 = kotlin.random.Random.Default
            kotlin.random.Random r12 = (kotlin.random.Random) r12
            java.lang.Object r11 = kotlin.collections.CollectionsKt.random(r11, r12)
            java.lang.String r11 = (java.lang.String) r11
            return r11
        L_0x01bb:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ciot.networklib.function.NearbyAttractsLiveData.group(com.ciot.realm.db.PersonInfoBean, java.lang.String, java.util.List):java.lang.String");
    }

    private final String common(String str, List<? extends NearbyAttractList.NearbyAttract> list) {
        Collection collection = list;
        boolean z = false;
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (NearbyAttractList.NearbyAttract nearbyAttract : list) {
            Collection collection2 = nearbyAttract.contents;
            if (!(collection2 == null || collection2.isEmpty()) && nearbyAttract.welcomeTime.contains(str)) {
                arrayList.add(nearbyAttract.contents);
            }
        }
        Collection collection3 = arrayList;
        if (!collection3.isEmpty()) {
            Collection collection4 = (List) CollectionsKt.random(collection3, Random.Default);
            if (collection4 == null || collection4.isEmpty()) {
                z = true;
            }
            if (!z) {
                return (String) CollectionsKt.random(collection4, Random.Default);
            }
        }
        return null;
    }
}
