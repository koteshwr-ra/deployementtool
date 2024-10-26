package com.ciot.networklib.info;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import com.blankj.utilcode.util.GsonUtils;
import com.ciot.base.config.CommonConfig;
import com.ciot.base.util.AppSpUtil;
import com.ciot.base.util.InfoUtils;
import com.ciot.base.util.MyLogUtils;
import com.ciot.networklib.info.NetUtils;
import com.tencent.smtt.sdk.TbsListener;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\u0018\u0000 \u00192\u00020\u0001:\u0004\u0018\u0019\u001a\u001bB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u000e\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\bJ\u0006\u0010\u0014\u001a\u00020\u000eJ\u0006\u0010\u0015\u001a\u00020\u000eJ\b\u0010\u0016\u001a\u00020\u000eH\u0002J\u0006\u0010\u0017\u001a\u00020\u000eR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0018\u00010\nR\u00020\u0000X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/ciot/networklib/info/NetWorkInfoHelper;", "", "()V", "connectivityManager", "Landroid/net/ConnectivityManager;", "disposable", "Lio/reactivex/disposables/Disposable;", "mContext", "Landroid/content/Context;", "receiver", "Lcom/ciot/networklib/info/NetWorkInfoHelper$NetworkReceiver;", "telephonyManager", "Landroid/telephony/TelephonyManager;", "getBaseInfo", "", "getCurrentNetworkInfo", "defaultSaveLog", "", "init", "context", "saveBaseLog", "saveNetLog", "stopInterval", "unInit", "BaseInfo", "Companion", "CurrentNetInfo", "NetworkReceiver", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: NetWorkInfoHelper.kt */
public final class NetWorkInfoHelper {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static BaseInfo baseInfo;
    /* access modifiers changed from: private */
    public static CurrentNetInfo currentNetInfo;
    /* access modifiers changed from: private */
    public static NetWorkInfoHelper helper;
    private ConnectivityManager connectivityManager;
    /* access modifiers changed from: private */
    public Disposable disposable;
    private Context mContext;
    private NetworkReceiver receiver;
    private TelephonyManager telephonyManager;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\bR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/ciot/networklib/info/NetWorkInfoHelper$Companion;", "", "()V", "baseInfo", "Lcom/ciot/networklib/info/NetWorkInfoHelper$BaseInfo;", "currentNetInfo", "Lcom/ciot/networklib/info/NetWorkInfoHelper$CurrentNetInfo;", "helper", "Lcom/ciot/networklib/info/NetWorkInfoHelper;", "getInstance", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: NetWorkInfoHelper.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final NetWorkInfoHelper getInstance() {
            if (NetWorkInfoHelper.helper == null) {
                NetWorkInfoHelper.helper = new NetWorkInfoHelper();
            }
            NetWorkInfoHelper access$getHelper$cp = NetWorkInfoHelper.helper;
            Intrinsics.checkNotNull(access$getHelper$cp);
            return access$getHelper$cp;
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b-\b\b\u0018\u00002\u00020\u0001B}\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0002\u0010\u000fJ\u0010\u0010+\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0011J\u0010\u0010,\u001a\u0004\u0018\u00010\u000eHÆ\u0003¢\u0006\u0002\u0010'J\u000b\u0010-\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010/\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0011J\u0010\u00100\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0011J\u0010\u00101\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0011J\u000b\u00102\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0001\u00105\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eHÆ\u0001¢\u0006\u0002\u00106J\u0013\u00107\u001a\u00020\u000e2\b\u00108\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00109\u001a\u00020\u0003HÖ\u0001J\t\u0010:\u001a\u00020\u0005HÖ\u0001R\u001e\u0010\t\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0010\n\u0002\u0010\u0014\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0016\"\u0004\b\u001a\u0010\u0018R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0010\n\u0002\u0010\u0014\u001a\u0004\b\u001b\u0010\u0011\"\u0004\b\u001c\u0010\u0013R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0010\n\u0002\u0010\u0014\u001a\u0004\b\u001d\u0010\u0011\"\u0004\b\u001e\u0010\u0013R\u001e\u0010\b\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0010\n\u0002\u0010\u0014\u001a\u0004\b\u001f\u0010\u0011\"\u0004\b \u0010\u0013R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0016\"\u0004\b\"\u0010\u0018R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0016\"\u0004\b$\u0010\u0018R\u001c\u0010\f\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0016\"\u0004\b&\u0010\u0018R\u001e\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u0010\n\u0002\u0010*\u001a\u0004\b\r\u0010'\"\u0004\b(\u0010)¨\u0006;"}, d2 = {"Lcom/ciot/networklib/info/NetWorkInfoHelper$CurrentNetInfo;", "", "currentNetType", "", "currentSimSn", "", "currentSimImei", "currentRssi", "currentRssiLevel", "currentDelay", "currentMac", "currentIp", "currentWifiName", "isCurrentNetAvailable", "", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)V", "getCurrentDelay", "()Ljava/lang/Integer;", "setCurrentDelay", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getCurrentIp", "()Ljava/lang/String;", "setCurrentIp", "(Ljava/lang/String;)V", "getCurrentMac", "setCurrentMac", "getCurrentNetType", "setCurrentNetType", "getCurrentRssi", "setCurrentRssi", "getCurrentRssiLevel", "setCurrentRssiLevel", "getCurrentSimImei", "setCurrentSimImei", "getCurrentSimSn", "setCurrentSimSn", "getCurrentWifiName", "setCurrentWifiName", "()Ljava/lang/Boolean;", "setCurrentNetAvailable", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/ciot/networklib/info/NetWorkInfoHelper$CurrentNetInfo;", "equals", "other", "hashCode", "toString", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: NetWorkInfoHelper.kt */
    public static final class CurrentNetInfo {
        private Integer currentDelay;
        private String currentIp;
        private String currentMac;
        private Integer currentNetType;
        private Integer currentRssi;
        private Integer currentRssiLevel;
        private String currentSimImei;
        private String currentSimSn;
        private String currentWifiName;
        private Boolean isCurrentNetAvailable;

        public CurrentNetInfo() {
            this((Integer) null, (String) null, (String) null, (Integer) null, (Integer) null, (Integer) null, (String) null, (String) null, (String) null, (Boolean) null, 1023, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ CurrentNetInfo copy$default(CurrentNetInfo currentNetInfo, Integer num, String str, String str2, Integer num2, Integer num3, Integer num4, String str3, String str4, String str5, Boolean bool, int i, Object obj) {
            CurrentNetInfo currentNetInfo2 = currentNetInfo;
            int i2 = i;
            return currentNetInfo.copy((i2 & 1) != 0 ? currentNetInfo2.currentNetType : num, (i2 & 2) != 0 ? currentNetInfo2.currentSimSn : str, (i2 & 4) != 0 ? currentNetInfo2.currentSimImei : str2, (i2 & 8) != 0 ? currentNetInfo2.currentRssi : num2, (i2 & 16) != 0 ? currentNetInfo2.currentRssiLevel : num3, (i2 & 32) != 0 ? currentNetInfo2.currentDelay : num4, (i2 & 64) != 0 ? currentNetInfo2.currentMac : str3, (i2 & 128) != 0 ? currentNetInfo2.currentIp : str4, (i2 & 256) != 0 ? currentNetInfo2.currentWifiName : str5, (i2 & 512) != 0 ? currentNetInfo2.isCurrentNetAvailable : bool);
        }

        public final Integer component1() {
            return this.currentNetType;
        }

        public final Boolean component10() {
            return this.isCurrentNetAvailable;
        }

        public final String component2() {
            return this.currentSimSn;
        }

        public final String component3() {
            return this.currentSimImei;
        }

        public final Integer component4() {
            return this.currentRssi;
        }

        public final Integer component5() {
            return this.currentRssiLevel;
        }

        public final Integer component6() {
            return this.currentDelay;
        }

        public final String component7() {
            return this.currentMac;
        }

        public final String component8() {
            return this.currentIp;
        }

        public final String component9() {
            return this.currentWifiName;
        }

        public final CurrentNetInfo copy(Integer num, String str, String str2, Integer num2, Integer num3, Integer num4, String str3, String str4, String str5, Boolean bool) {
            return new CurrentNetInfo(num, str, str2, num2, num3, num4, str3, str4, str5, bool);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof CurrentNetInfo)) {
                return false;
            }
            CurrentNetInfo currentNetInfo = (CurrentNetInfo) obj;
            return Intrinsics.areEqual((Object) this.currentNetType, (Object) currentNetInfo.currentNetType) && Intrinsics.areEqual((Object) this.currentSimSn, (Object) currentNetInfo.currentSimSn) && Intrinsics.areEqual((Object) this.currentSimImei, (Object) currentNetInfo.currentSimImei) && Intrinsics.areEqual((Object) this.currentRssi, (Object) currentNetInfo.currentRssi) && Intrinsics.areEqual((Object) this.currentRssiLevel, (Object) currentNetInfo.currentRssiLevel) && Intrinsics.areEqual((Object) this.currentDelay, (Object) currentNetInfo.currentDelay) && Intrinsics.areEqual((Object) this.currentMac, (Object) currentNetInfo.currentMac) && Intrinsics.areEqual((Object) this.currentIp, (Object) currentNetInfo.currentIp) && Intrinsics.areEqual((Object) this.currentWifiName, (Object) currentNetInfo.currentWifiName) && Intrinsics.areEqual((Object) this.isCurrentNetAvailable, (Object) currentNetInfo.isCurrentNetAvailable);
        }

        public int hashCode() {
            Integer num = this.currentNetType;
            int i = 0;
            int hashCode = (num == null ? 0 : num.hashCode()) * 31;
            String str = this.currentSimSn;
            int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.currentSimImei;
            int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
            Integer num2 = this.currentRssi;
            int hashCode4 = (hashCode3 + (num2 == null ? 0 : num2.hashCode())) * 31;
            Integer num3 = this.currentRssiLevel;
            int hashCode5 = (hashCode4 + (num3 == null ? 0 : num3.hashCode())) * 31;
            Integer num4 = this.currentDelay;
            int hashCode6 = (hashCode5 + (num4 == null ? 0 : num4.hashCode())) * 31;
            String str3 = this.currentMac;
            int hashCode7 = (hashCode6 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.currentIp;
            int hashCode8 = (hashCode7 + (str4 == null ? 0 : str4.hashCode())) * 31;
            String str5 = this.currentWifiName;
            int hashCode9 = (hashCode8 + (str5 == null ? 0 : str5.hashCode())) * 31;
            Boolean bool = this.isCurrentNetAvailable;
            if (bool != null) {
                i = bool.hashCode();
            }
            return hashCode9 + i;
        }

        public String toString() {
            return "CurrentNetInfo(currentNetType=" + this.currentNetType + ", currentSimSn=" + this.currentSimSn + ", currentSimImei=" + this.currentSimImei + ", currentRssi=" + this.currentRssi + ", currentRssiLevel=" + this.currentRssiLevel + ", currentDelay=" + this.currentDelay + ", currentMac=" + this.currentMac + ", currentIp=" + this.currentIp + ", currentWifiName=" + this.currentWifiName + ", isCurrentNetAvailable=" + this.isCurrentNetAvailable + ')';
        }

        public CurrentNetInfo(Integer num, String str, String str2, Integer num2, Integer num3, Integer num4, String str3, String str4, String str5, Boolean bool) {
            this.currentNetType = num;
            this.currentSimSn = str;
            this.currentSimImei = str2;
            this.currentRssi = num2;
            this.currentRssiLevel = num3;
            this.currentDelay = num4;
            this.currentMac = str3;
            this.currentIp = str4;
            this.currentWifiName = str5;
            this.isCurrentNetAvailable = bool;
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public /* synthetic */ CurrentNetInfo(java.lang.Integer r12, java.lang.String r13, java.lang.String r14, java.lang.Integer r15, java.lang.Integer r16, java.lang.Integer r17, java.lang.String r18, java.lang.String r19, java.lang.String r20, java.lang.Boolean r21, int r22, kotlin.jvm.internal.DefaultConstructorMarker r23) {
            /*
                r11 = this;
                r0 = r22
                r1 = r0 & 1
                r2 = 0
                if (r1 == 0) goto L_0x0009
                r1 = r2
                goto L_0x000a
            L_0x0009:
                r1 = r12
            L_0x000a:
                r3 = r0 & 2
                if (r3 == 0) goto L_0x0010
                r3 = r2
                goto L_0x0011
            L_0x0010:
                r3 = r13
            L_0x0011:
                r4 = r0 & 4
                if (r4 == 0) goto L_0x0017
                r4 = r2
                goto L_0x0018
            L_0x0017:
                r4 = r14
            L_0x0018:
                r5 = r0 & 8
                if (r5 == 0) goto L_0x001e
                r5 = r2
                goto L_0x001f
            L_0x001e:
                r5 = r15
            L_0x001f:
                r6 = r0 & 16
                if (r6 == 0) goto L_0x0025
                r6 = r2
                goto L_0x0027
            L_0x0025:
                r6 = r16
            L_0x0027:
                r7 = r0 & 32
                if (r7 == 0) goto L_0x002d
                r7 = r2
                goto L_0x002f
            L_0x002d:
                r7 = r17
            L_0x002f:
                r8 = r0 & 64
                if (r8 == 0) goto L_0x0035
                r8 = r2
                goto L_0x0037
            L_0x0035:
                r8 = r18
            L_0x0037:
                r9 = r0 & 128(0x80, float:1.794E-43)
                if (r9 == 0) goto L_0x003d
                r9 = r2
                goto L_0x003f
            L_0x003d:
                r9 = r19
            L_0x003f:
                r10 = r0 & 256(0x100, float:3.59E-43)
                if (r10 == 0) goto L_0x0045
                r10 = r2
                goto L_0x0047
            L_0x0045:
                r10 = r20
            L_0x0047:
                r0 = r0 & 512(0x200, float:7.175E-43)
                if (r0 == 0) goto L_0x004c
                goto L_0x004e
            L_0x004c:
                r2 = r21
            L_0x004e:
                r12 = r11
                r13 = r1
                r14 = r3
                r15 = r4
                r16 = r5
                r17 = r6
                r18 = r7
                r19 = r8
                r20 = r9
                r21 = r10
                r22 = r2
                r12.<init>(r13, r14, r15, r16, r17, r18, r19, r20, r21, r22)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ciot.networklib.info.NetWorkInfoHelper.CurrentNetInfo.<init>(java.lang.Integer, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.Boolean, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
        }

        public final Integer getCurrentNetType() {
            return this.currentNetType;
        }

        public final void setCurrentNetType(Integer num) {
            this.currentNetType = num;
        }

        public final String getCurrentSimSn() {
            return this.currentSimSn;
        }

        public final void setCurrentSimSn(String str) {
            this.currentSimSn = str;
        }

        public final String getCurrentSimImei() {
            return this.currentSimImei;
        }

        public final void setCurrentSimImei(String str) {
            this.currentSimImei = str;
        }

        public final Integer getCurrentRssi() {
            return this.currentRssi;
        }

        public final void setCurrentRssi(Integer num) {
            this.currentRssi = num;
        }

        public final Integer getCurrentRssiLevel() {
            return this.currentRssiLevel;
        }

        public final void setCurrentRssiLevel(Integer num) {
            this.currentRssiLevel = num;
        }

        public final Integer getCurrentDelay() {
            return this.currentDelay;
        }

        public final void setCurrentDelay(Integer num) {
            this.currentDelay = num;
        }

        public final String getCurrentMac() {
            return this.currentMac;
        }

        public final void setCurrentMac(String str) {
            this.currentMac = str;
        }

        public final String getCurrentIp() {
            return this.currentIp;
        }

        public final void setCurrentIp(String str) {
            this.currentIp = str;
        }

        public final String getCurrentWifiName() {
            return this.currentWifiName;
        }

        public final void setCurrentWifiName(String str) {
            this.currentWifiName = str;
        }

        public final Boolean isCurrentNetAvailable() {
            return this.isCurrentNetAvailable;
        }

        public final void setCurrentNetAvailable(Boolean bool) {
            this.isCurrentNetAvailable = bool;
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b \n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BY\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\nJ\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0003HÆ\u0003J]\u0010\"\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010&\u001a\u00020'HÖ\u0001J\t\u0010(\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\f\"\u0004\b\u0010\u0010\u000eR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\f\"\u0004\b\u0012\u0010\u000eR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000eR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\f\"\u0004\b\u0016\u0010\u000eR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\f\"\u0004\b\u0018\u0010\u000eR\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\f\"\u0004\b\u001a\u0010\u000e¨\u0006)"}, d2 = {"Lcom/ciot/networklib/info/NetWorkInfoHelper$BaseInfo;", "", "currentBoardType", "", "currentPlatform", "currentBoardVersion", "currentRobotNumber", "currentRobotType", "installedApplications", "currentTempCamera", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCurrentBoardType", "()Ljava/lang/String;", "setCurrentBoardType", "(Ljava/lang/String;)V", "getCurrentBoardVersion", "setCurrentBoardVersion", "getCurrentPlatform", "setCurrentPlatform", "getCurrentRobotNumber", "setCurrentRobotNumber", "getCurrentRobotType", "setCurrentRobotType", "getCurrentTempCamera", "setCurrentTempCamera", "getInstalledApplications", "setInstalledApplications", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: NetWorkInfoHelper.kt */
    public static final class BaseInfo {
        private String currentBoardType;
        private String currentBoardVersion;
        private String currentPlatform;
        private String currentRobotNumber;
        private String currentRobotType;
        private String currentTempCamera;
        private String installedApplications;

        public BaseInfo() {
            this((String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, 127, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ BaseInfo copy$default(BaseInfo baseInfo, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, Object obj) {
            if ((i & 1) != 0) {
                str = baseInfo.currentBoardType;
            }
            if ((i & 2) != 0) {
                str2 = baseInfo.currentPlatform;
            }
            String str8 = str2;
            if ((i & 4) != 0) {
                str3 = baseInfo.currentBoardVersion;
            }
            String str9 = str3;
            if ((i & 8) != 0) {
                str4 = baseInfo.currentRobotNumber;
            }
            String str10 = str4;
            if ((i & 16) != 0) {
                str5 = baseInfo.currentRobotType;
            }
            String str11 = str5;
            if ((i & 32) != 0) {
                str6 = baseInfo.installedApplications;
            }
            String str12 = str6;
            if ((i & 64) != 0) {
                str7 = baseInfo.currentTempCamera;
            }
            return baseInfo.copy(str, str8, str9, str10, str11, str12, str7);
        }

        public final String component1() {
            return this.currentBoardType;
        }

        public final String component2() {
            return this.currentPlatform;
        }

        public final String component3() {
            return this.currentBoardVersion;
        }

        public final String component4() {
            return this.currentRobotNumber;
        }

        public final String component5() {
            return this.currentRobotType;
        }

        public final String component6() {
            return this.installedApplications;
        }

        public final String component7() {
            return this.currentTempCamera;
        }

        public final BaseInfo copy(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
            return new BaseInfo(str, str2, str3, str4, str5, str6, str7);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof BaseInfo)) {
                return false;
            }
            BaseInfo baseInfo = (BaseInfo) obj;
            return Intrinsics.areEqual((Object) this.currentBoardType, (Object) baseInfo.currentBoardType) && Intrinsics.areEqual((Object) this.currentPlatform, (Object) baseInfo.currentPlatform) && Intrinsics.areEqual((Object) this.currentBoardVersion, (Object) baseInfo.currentBoardVersion) && Intrinsics.areEqual((Object) this.currentRobotNumber, (Object) baseInfo.currentRobotNumber) && Intrinsics.areEqual((Object) this.currentRobotType, (Object) baseInfo.currentRobotType) && Intrinsics.areEqual((Object) this.installedApplications, (Object) baseInfo.installedApplications) && Intrinsics.areEqual((Object) this.currentTempCamera, (Object) baseInfo.currentTempCamera);
        }

        public int hashCode() {
            String str = this.currentBoardType;
            int i = 0;
            int hashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.currentPlatform;
            int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.currentBoardVersion;
            int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.currentRobotNumber;
            int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
            String str5 = this.currentRobotType;
            int hashCode5 = (hashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
            String str6 = this.installedApplications;
            int hashCode6 = (hashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
            String str7 = this.currentTempCamera;
            if (str7 != null) {
                i = str7.hashCode();
            }
            return hashCode6 + i;
        }

        public String toString() {
            return "BaseInfo(currentBoardType=" + this.currentBoardType + ", currentPlatform=" + this.currentPlatform + ", currentBoardVersion=" + this.currentBoardVersion + ", currentRobotNumber=" + this.currentRobotNumber + ", currentRobotType=" + this.currentRobotType + ", installedApplications=" + this.installedApplications + ", currentTempCamera=" + this.currentTempCamera + ')';
        }

        public BaseInfo(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
            this.currentBoardType = str;
            this.currentPlatform = str2;
            this.currentBoardVersion = str3;
            this.currentRobotNumber = str4;
            this.currentRobotType = str5;
            this.installedApplications = str6;
            this.currentTempCamera = str7;
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public /* synthetic */ BaseInfo(java.lang.String r7, java.lang.String r8, java.lang.String r9, java.lang.String r10, java.lang.String r11, java.lang.String r12, java.lang.String r13, int r14, kotlin.jvm.internal.DefaultConstructorMarker r15) {
            /*
                r6 = this;
                r15 = r14 & 1
                r0 = 0
                if (r15 == 0) goto L_0x0007
                r15 = r0
                goto L_0x0008
            L_0x0007:
                r15 = r7
            L_0x0008:
                r7 = r14 & 2
                if (r7 == 0) goto L_0x000e
                r1 = r0
                goto L_0x000f
            L_0x000e:
                r1 = r8
            L_0x000f:
                r7 = r14 & 4
                if (r7 == 0) goto L_0x0015
                r2 = r0
                goto L_0x0016
            L_0x0015:
                r2 = r9
            L_0x0016:
                r7 = r14 & 8
                if (r7 == 0) goto L_0x001c
                r3 = r0
                goto L_0x001d
            L_0x001c:
                r3 = r10
            L_0x001d:
                r7 = r14 & 16
                if (r7 == 0) goto L_0x0023
                r4 = r0
                goto L_0x0024
            L_0x0023:
                r4 = r11
            L_0x0024:
                r7 = r14 & 32
                if (r7 == 0) goto L_0x002a
                r5 = r0
                goto L_0x002b
            L_0x002a:
                r5 = r12
            L_0x002b:
                r7 = r14 & 64
                if (r7 == 0) goto L_0x0031
                r14 = r0
                goto L_0x0032
            L_0x0031:
                r14 = r13
            L_0x0032:
                r7 = r6
                r8 = r15
                r9 = r1
                r10 = r2
                r11 = r3
                r12 = r4
                r13 = r5
                r7.<init>(r8, r9, r10, r11, r12, r13, r14)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ciot.networklib.info.NetWorkInfoHelper.BaseInfo.<init>(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
        }

        public final String getCurrentBoardType() {
            return this.currentBoardType;
        }

        public final void setCurrentBoardType(String str) {
            this.currentBoardType = str;
        }

        public final String getCurrentPlatform() {
            return this.currentPlatform;
        }

        public final void setCurrentPlatform(String str) {
            this.currentPlatform = str;
        }

        public final String getCurrentBoardVersion() {
            return this.currentBoardVersion;
        }

        public final void setCurrentBoardVersion(String str) {
            this.currentBoardVersion = str;
        }

        public final String getCurrentRobotNumber() {
            return this.currentRobotNumber;
        }

        public final void setCurrentRobotNumber(String str) {
            this.currentRobotNumber = str;
        }

        public final String getCurrentRobotType() {
            return this.currentRobotType;
        }

        public final void setCurrentRobotType(String str) {
            this.currentRobotType = str;
        }

        public final String getInstalledApplications() {
            return this.installedApplications;
        }

        public final void setInstalledApplications(String str) {
            this.installedApplications = str;
        }

        public final String getCurrentTempCamera() {
            return this.currentTempCamera;
        }

        public final void setCurrentTempCamera(String str) {
            this.currentTempCamera = str;
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"Lcom/ciot/networklib/info/NetWorkInfoHelper$NetworkReceiver;", "Landroid/content/BroadcastReceiver;", "(Lcom/ciot/networklib/info/NetWorkInfoHelper;)V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: NetWorkInfoHelper.kt */
    public final class NetworkReceiver extends BroadcastReceiver {
        public NetworkReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if (context != null && intent != null && intent.getAction() != null) {
                if (Intrinsics.areEqual((Object) intent.getAction(), (Object) "android.net.wifi.RSSI_CHANGED")) {
                    int intExtra = intent.getIntExtra("newRssi", -1);
                    CurrentNetInfo access$getCurrentNetInfo$cp = NetWorkInfoHelper.currentNetInfo;
                    Intrinsics.checkNotNull(access$getCurrentNetInfo$cp);
                    access$getCurrentNetInfo$cp.setCurrentRssi(Integer.valueOf(intExtra));
                }
                NetWorkInfoHelper.this.getCurrentNetworkInfo(false);
            }
        }
    }

    public final void init(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.mContext = context;
        this.receiver = new NetworkReceiver();
        currentNetInfo = new CurrentNetInfo((Integer) null, (String) null, (String) null, (Integer) null, (Integer) null, (Integer) null, (String) null, (String) null, (String) null, (Boolean) null, 1023, (DefaultConstructorMarker) null);
        Object systemService = context.getSystemService("connectivity");
        if (systemService != null) {
            this.connectivityManager = (ConnectivityManager) systemService;
            Object systemService2 = context.getSystemService("phone");
            if (systemService2 != null) {
                this.telephonyManager = (TelephonyManager) systemService2;
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.net.ethernet.");
                intentFilter.addAction("android.net.ethernet.STATE_CHANGE");
                intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
                intentFilter.addAction("android.net.wifi.STATE_CHANGE");
                intentFilter.addAction("android.net.wifi.RSSI_CHANGED");
                context.registerReceiver(this.receiver, intentFilter);
                TelephonyManager telephonyManager2 = this.telephonyManager;
                Intrinsics.checkNotNull(telephonyManager2);
                telephonyManager2.listen(new NetWorkInfoHelper$init$1(this), TbsListener.ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_01);
                Observable.interval(1000, TimeUnit.MILLISECONDS).observeOn(Schedulers.io()).subscribe(new NetWorkInfoHelper$init$2(this));
                getBaseInfo();
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type android.telephony.TelephonyManager");
        }
        throw new NullPointerException("null cannot be cast to non-null type android.net.ConnectivityManager");
    }

    private final void stopInterval() {
        Disposable disposable2;
        Disposable disposable3 = this.disposable;
        if (disposable3 != null) {
            Intrinsics.checkNotNull(disposable3);
            if (!disposable3.isDisposed() && (disposable2 = this.disposable) != null) {
                disposable2.dispose();
            }
        }
    }

    public final void unInit() {
        stopInterval();
        Context context = this.mContext;
        if (context != null) {
            context.unregisterReceiver(this.receiver);
        }
    }

    /* access modifiers changed from: private */
    public final void getCurrentNetworkInfo(boolean z) {
        ConnectivityManager connectivityManager2 = this.connectivityManager;
        Intrinsics.checkNotNull(connectivityManager2);
        NetworkInfo activeNetworkInfo = connectivityManager2.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            CurrentNetInfo currentNetInfo2 = currentNetInfo;
            Intrinsics.checkNotNull(currentNetInfo2);
            currentNetInfo2.setCurrentNetType(-1);
            MyLogUtils.Logd(CommonConfig.NETINFO_TAG, GsonUtils.toJson(currentNetInfo));
            return;
        }
        CurrentNetInfo currentNetInfo3 = currentNetInfo;
        Intrinsics.checkNotNull(currentNetInfo3);
        Integer currentNetType = currentNetInfo3.getCurrentNetType();
        int type = activeNetworkInfo.getType();
        boolean z2 = false;
        boolean z3 = true;
        if (currentNetType == null || currentNetType.intValue() != type) {
            CurrentNetInfo currentNetInfo4 = currentNetInfo;
            Intrinsics.checkNotNull(currentNetInfo4);
            currentNetInfo4.setCurrentDelay(0);
            z = true;
        }
        CurrentNetInfo currentNetInfo5 = currentNetInfo;
        Intrinsics.checkNotNull(currentNetInfo5);
        if (!Intrinsics.areEqual((Object) currentNetInfo5.isCurrentNetAvailable(), (Object) Boolean.valueOf(activeNetworkInfo.isAvailable())) && activeNetworkInfo.isConnected()) {
            z = true;
        }
        CurrentNetInfo currentNetInfo6 = currentNetInfo;
        Intrinsics.checkNotNull(currentNetInfo6);
        currentNetInfo6.setCurrentNetType(Integer.valueOf(activeNetworkInfo.getType()));
        CurrentNetInfo currentNetInfo7 = currentNetInfo;
        Intrinsics.checkNotNull(currentNetInfo7);
        if (activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected()) {
            z2 = true;
        }
        currentNetInfo7.setCurrentNetAvailable(Boolean.valueOf(z2));
        int type2 = activeNetworkInfo.getType();
        if (type2 == 0) {
            CurrentNetInfo currentNetInfo8 = currentNetInfo;
            Intrinsics.checkNotNull(currentNetInfo8);
            currentNetInfo8.setCurrentWifiName((String) null);
        } else if (type2 == 1) {
            CurrentNetInfo currentNetInfo9 = currentNetInfo;
            Intrinsics.checkNotNull(currentNetInfo9);
            String currentWifiName = currentNetInfo9.getCurrentWifiName();
            NetUtils.Companion companion = NetUtils.Companion;
            Context context = this.mContext;
            Intrinsics.checkNotNull(context);
            if (!Intrinsics.areEqual((Object) currentWifiName, (Object) companion.getWifiSsid(context))) {
                z = true;
            }
            CurrentNetInfo currentNetInfo10 = currentNetInfo;
            Intrinsics.checkNotNull(currentNetInfo10);
            String currentIp = currentNetInfo10.getCurrentIp();
            NetUtils.Companion companion2 = NetUtils.Companion;
            Context context2 = this.mContext;
            Intrinsics.checkNotNull(context2);
            if (!Intrinsics.areEqual((Object) currentIp, (Object) companion2.getWifiIp(context2))) {
                z = true;
            }
            CurrentNetInfo currentNetInfo11 = currentNetInfo;
            Intrinsics.checkNotNull(currentNetInfo11);
            if (!Intrinsics.areEqual((Object) currentNetInfo11.getCurrentMac(), (Object) NetUtils.Companion.getWifiMacAddr())) {
                z = true;
            }
            CurrentNetInfo currentNetInfo12 = currentNetInfo;
            Intrinsics.checkNotNull(currentNetInfo12);
            NetUtils.Companion companion3 = NetUtils.Companion;
            Context context3 = this.mContext;
            Intrinsics.checkNotNull(context3);
            currentNetInfo12.setCurrentWifiName(companion3.getWifiSsid(context3));
            CurrentNetInfo currentNetInfo13 = currentNetInfo;
            Intrinsics.checkNotNull(currentNetInfo13);
            NetUtils.Companion companion4 = NetUtils.Companion;
            Context context4 = this.mContext;
            Intrinsics.checkNotNull(context4);
            currentNetInfo13.setCurrentIp(companion4.getWifiIp(context4));
            CurrentNetInfo currentNetInfo14 = currentNetInfo;
            Intrinsics.checkNotNull(currentNetInfo14);
            currentNetInfo14.setCurrentMac(NetUtils.Companion.getWifiMacAddr());
            CurrentNetInfo currentNetInfo15 = currentNetInfo;
            Intrinsics.checkNotNull(currentNetInfo15);
            if (currentNetInfo15.getCurrentRssi() != null) {
                CurrentNetInfo currentNetInfo16 = currentNetInfo;
                Intrinsics.checkNotNull(currentNetInfo16);
                Integer currentRssiLevel = currentNetInfo16.getCurrentRssiLevel();
                CurrentNetInfo currentNetInfo17 = currentNetInfo;
                Intrinsics.checkNotNull(currentNetInfo17);
                Integer currentRssi = currentNetInfo17.getCurrentRssi();
                Intrinsics.checkNotNull(currentRssi);
                int calculateSignalLevel = WifiManager.calculateSignalLevel(currentRssi.intValue(), 5);
                if (currentRssiLevel == null || currentRssiLevel.intValue() != calculateSignalLevel) {
                    z = true;
                }
                CurrentNetInfo currentNetInfo18 = currentNetInfo;
                Intrinsics.checkNotNull(currentNetInfo18);
                CurrentNetInfo currentNetInfo19 = currentNetInfo;
                Intrinsics.checkNotNull(currentNetInfo19);
                Integer currentRssi2 = currentNetInfo19.getCurrentRssi();
                Intrinsics.checkNotNull(currentRssi2);
                currentNetInfo18.setCurrentRssiLevel(Integer.valueOf(WifiManager.calculateSignalLevel(currentRssi2.intValue(), 5)));
            }
        } else if (type2 == 9) {
            CurrentNetInfo currentNetInfo20 = currentNetInfo;
            Intrinsics.checkNotNull(currentNetInfo20);
            currentNetInfo20.setCurrentWifiName((String) null);
            CurrentNetInfo currentNetInfo21 = currentNetInfo;
            Intrinsics.checkNotNull(currentNetInfo21);
            currentNetInfo21.setCurrentRssi((Integer) null);
            CurrentNetInfo currentNetInfo22 = currentNetInfo;
            Intrinsics.checkNotNull(currentNetInfo22);
            if (!Intrinsics.areEqual((Object) currentNetInfo22.getCurrentIp(), (Object) NetUtils.Companion.getHostIPAddress())) {
                z = true;
            }
            CurrentNetInfo currentNetInfo23 = currentNetInfo;
            Intrinsics.checkNotNull(currentNetInfo23);
            if (!Intrinsics.areEqual((Object) currentNetInfo23.getCurrentMac(), (Object) NetUtils.Companion.getEthernetMacAddress())) {
                z = true;
            }
            CurrentNetInfo currentNetInfo24 = currentNetInfo;
            Intrinsics.checkNotNull(currentNetInfo24);
            currentNetInfo24.setCurrentIp(NetUtils.Companion.getHostIPAddress());
            CurrentNetInfo currentNetInfo25 = currentNetInfo;
            Intrinsics.checkNotNull(currentNetInfo25);
            currentNetInfo25.setCurrentMac(NetUtils.Companion.getEthernetMacAddress());
        }
        Context context5 = this.mContext;
        Intrinsics.checkNotNull(context5);
        if (context5.checkSelfPermission("android.permission.READ_PHONE_STATE") == 0) {
            CurrentNetInfo currentNetInfo26 = currentNetInfo;
            Intrinsics.checkNotNull(currentNetInfo26);
            String currentSimSn = currentNetInfo26.getCurrentSimSn();
            NetUtils.Companion companion5 = NetUtils.Companion;
            Context context6 = this.mContext;
            Intrinsics.checkNotNull(context6);
            if (!Intrinsics.areEqual((Object) currentSimSn, (Object) companion5.getSimSn(context6))) {
                z = true;
            }
            CurrentNetInfo currentNetInfo27 = currentNetInfo;
            Intrinsics.checkNotNull(currentNetInfo27);
            String currentSimImei = currentNetInfo27.getCurrentSimImei();
            NetUtils.Companion companion6 = NetUtils.Companion;
            Context context7 = this.mContext;
            Intrinsics.checkNotNull(context7);
            if (Intrinsics.areEqual((Object) currentSimImei, (Object) companion6.getSimImei(context7))) {
                z3 = z;
            }
            CurrentNetInfo currentNetInfo28 = currentNetInfo;
            Intrinsics.checkNotNull(currentNetInfo28);
            NetUtils.Companion companion7 = NetUtils.Companion;
            Context context8 = this.mContext;
            Intrinsics.checkNotNull(context8);
            currentNetInfo28.setCurrentSimSn(companion7.getSimSn(context8));
            CurrentNetInfo currentNetInfo29 = currentNetInfo;
            Intrinsics.checkNotNull(currentNetInfo29);
            NetUtils.Companion companion8 = NetUtils.Companion;
            Context context9 = this.mContext;
            Intrinsics.checkNotNull(context9);
            currentNetInfo29.setCurrentSimImei(companion8.getSimImei(context9));
            z = z3;
        }
        if (z) {
            saveNetLog();
        }
    }

    private final void getBaseInfo() {
        if (baseInfo == null) {
            baseInfo = new BaseInfo((String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, 127, (DefaultConstructorMarker) null);
        }
        BaseInfo baseInfo2 = baseInfo;
        Intrinsics.checkNotNull(baseInfo2);
        baseInfo2.setCurrentBoardType(InfoUtils.getBoardType(this.mContext).getName());
        BaseInfo baseInfo3 = baseInfo;
        Intrinsics.checkNotNull(baseInfo3);
        baseInfo3.setCurrentBoardVersion(InfoUtils.getFirmWareVersion());
        BaseInfo baseInfo4 = baseInfo;
        Intrinsics.checkNotNull(baseInfo4);
        baseInfo4.setCurrentPlatform(InfoUtils.getRkPlatform());
        BaseInfo baseInfo5 = baseInfo;
        Intrinsics.checkNotNull(baseInfo5);
        baseInfo5.setInstalledApplications(InfoUtils.getInstalledApps(this.mContext));
        BaseInfo baseInfo6 = baseInfo;
        Intrinsics.checkNotNull(baseInfo6);
        baseInfo6.setCurrentTempCamera(InfoUtils.getTempType(this.mContext));
        String robotNumber = AppSpUtil.getInstance().getRobotNumber();
        if (robotNumber != null) {
            if (robotNumber.length() > 0) {
                BaseInfo baseInfo7 = baseInfo;
                Intrinsics.checkNotNull(baseInfo7);
                baseInfo7.setCurrentRobotNumber(robotNumber);
                BaseInfo baseInfo8 = baseInfo;
                Intrinsics.checkNotNull(baseInfo8);
                baseInfo8.setCurrentRobotType(InfoUtils.getRobotType(robotNumber).getName());
            }
        }
        saveBaseLog();
    }

    public final void saveNetLog() {
        Context context = this.mContext;
        Intrinsics.checkNotNull(context);
        if (context.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            MyLogUtils.Logd(CommonConfig.NETINFO_TAG, "NetInfo:" + GsonUtils.toJson(currentNetInfo));
        }
    }

    public final void saveBaseLog() {
        Context context = this.mContext;
        Intrinsics.checkNotNull(context);
        if (context.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            MyLogUtils.Logd(CommonConfig.NETINFO_TAG, "BaseInfo:" + GsonUtils.toJson(baseInfo));
        }
    }
}
