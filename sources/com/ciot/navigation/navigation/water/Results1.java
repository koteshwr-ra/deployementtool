package com.ciot.navigation.navigation.water;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b'\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001Be\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0019¢\u0006\u0002\u0010\u001aJ\t\u00103\u001a\u00020\u0003HÆ\u0003J\t\u00104\u001a\u00020\u0015HÆ\u0003J\t\u00105\u001a\u00020\u0017HÆ\u0003J\t\u00106\u001a\u00020\u0019HÆ\u0003J\t\u00107\u001a\u00020\u0005HÆ\u0003J\t\u00108\u001a\u00020\u0007HÆ\u0003J\t\u00109\u001a\u00020\tHÆ\u0003J\t\u0010:\u001a\u00020\u000bHÆ\u0003J\t\u0010;\u001a\u00020\rHÆ\u0003J\t\u0010<\u001a\u00020\u000fHÆ\u0003J\t\u0010=\u001a\u00020\u0011HÆ\u0003J\t\u0010>\u001a\u00020\u0013HÆ\u0003J\u0001\u0010?\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u0019HÆ\u0001J\u0013\u0010@\u001a\u00020A2\b\u0010B\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010C\u001a\u00020DHÖ\u0001J\t\u0010E\u001a\u00020FHÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0016\u0010\b\u001a\u00020\t8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0016\u0010\n\u001a\u00020\u000b8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0016\u0010\f\u001a\u00020\r8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0016\u0010\u000e\u001a\u00020\u000f8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0016\u0010\u0010\u001a\u00020\u00118\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0016\u0010\u0012\u001a\u00020\u00138\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0016\u0010\u0014\u001a\u00020\u00158\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0016\u0010\u0016\u001a\u00020\u00178\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0016\u0010\u0018\u001a\u00020\u00198\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b1\u00102¨\u0006G"}, d2 = {"Lcom/ciot/navigation/navigation/water/Results1;", "", "cAN", "Lcom/ciot/navigation/navigation/water/CAN;", "dNS114114114114", "Lcom/ciot/navigation/navigation/water/DNS114114114114;", "dNS8888", "Lcom/ciot/navigation/navigation/water/DNS8888;", "depthCameraFrontDown", "Lcom/ciot/navigation/navigation/water/DepthCameraFrontDown;", "iMU", "Lcom/ciot/navigation/navigation/water/IMU;", "internet", "Lcom/ciot/navigation/navigation/water/Internet;", "laserFront", "Lcom/ciot/navigation/navigation/water/LaserFront;", "motorCore", "Lcom/ciot/navigation/navigation/water/MotorCore;", "odom", "Lcom/ciot/navigation/navigation/water/Odom;", "peripheralCore", "Lcom/ciot/navigation/navigation/water/PeripheralCore;", "powerCore", "Lcom/ciot/navigation/navigation/water/PowerCore;", "routerCore", "Lcom/ciot/navigation/navigation/water/RouterCore;", "(Lcom/ciot/navigation/navigation/water/CAN;Lcom/ciot/navigation/navigation/water/DNS114114114114;Lcom/ciot/navigation/navigation/water/DNS8888;Lcom/ciot/navigation/navigation/water/DepthCameraFrontDown;Lcom/ciot/navigation/navigation/water/IMU;Lcom/ciot/navigation/navigation/water/Internet;Lcom/ciot/navigation/navigation/water/LaserFront;Lcom/ciot/navigation/navigation/water/MotorCore;Lcom/ciot/navigation/navigation/water/Odom;Lcom/ciot/navigation/navigation/water/PeripheralCore;Lcom/ciot/navigation/navigation/water/PowerCore;Lcom/ciot/navigation/navigation/water/RouterCore;)V", "getCAN", "()Lcom/ciot/navigation/navigation/water/CAN;", "getDNS114114114114", "()Lcom/ciot/navigation/navigation/water/DNS114114114114;", "getDNS8888", "()Lcom/ciot/navigation/navigation/water/DNS8888;", "getDepthCameraFrontDown", "()Lcom/ciot/navigation/navigation/water/DepthCameraFrontDown;", "getIMU", "()Lcom/ciot/navigation/navigation/water/IMU;", "getInternet", "()Lcom/ciot/navigation/navigation/water/Internet;", "getLaserFront", "()Lcom/ciot/navigation/navigation/water/LaserFront;", "getMotorCore", "()Lcom/ciot/navigation/navigation/water/MotorCore;", "getOdom", "()Lcom/ciot/navigation/navigation/water/Odom;", "getPeripheralCore", "()Lcom/ciot/navigation/navigation/water/PeripheralCore;", "getPowerCore", "()Lcom/ciot/navigation/navigation/water/PowerCore;", "getRouterCore", "()Lcom/ciot/navigation/navigation/water/RouterCore;", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "", "library-navigation_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: WaterDiagnosisResultBean.kt */
public final class Results1 {
    @SerializedName("CAN")
    private final CAN cAN;
    @SerializedName("DNS(114.114.114.114)")
    private final DNS114114114114 dNS114114114114;
    @SerializedName("DNS(8.8.8.8)")
    private final DNS8888 dNS8888;
    @SerializedName("depth_camera_front_down")
    private final DepthCameraFrontDown depthCameraFrontDown;
    @SerializedName("IMU")
    private final IMU iMU;
    @SerializedName("internet")
    private final Internet internet;
    @SerializedName("laser_front")
    private final LaserFront laserFront;
    @SerializedName("motor_core")
    private final MotorCore motorCore;
    @SerializedName("odom")
    private final Odom odom;
    @SerializedName("peripheral_core")
    private final PeripheralCore peripheralCore;
    @SerializedName("power_core")
    private final PowerCore powerCore;
    @SerializedName("router_core")
    private final RouterCore routerCore;

    public static /* synthetic */ Results1 copy$default(Results1 results1, CAN can, DNS114114114114 dns114114114114, DNS8888 dns8888, DepthCameraFrontDown depthCameraFrontDown2, IMU imu, Internet internet2, LaserFront laserFront2, MotorCore motorCore2, Odom odom2, PeripheralCore peripheralCore2, PowerCore powerCore2, RouterCore routerCore2, int i, Object obj) {
        Results1 results12 = results1;
        int i2 = i;
        return results1.copy((i2 & 1) != 0 ? results12.cAN : can, (i2 & 2) != 0 ? results12.dNS114114114114 : dns114114114114, (i2 & 4) != 0 ? results12.dNS8888 : dns8888, (i2 & 8) != 0 ? results12.depthCameraFrontDown : depthCameraFrontDown2, (i2 & 16) != 0 ? results12.iMU : imu, (i2 & 32) != 0 ? results12.internet : internet2, (i2 & 64) != 0 ? results12.laserFront : laserFront2, (i2 & 128) != 0 ? results12.motorCore : motorCore2, (i2 & 256) != 0 ? results12.odom : odom2, (i2 & 512) != 0 ? results12.peripheralCore : peripheralCore2, (i2 & 1024) != 0 ? results12.powerCore : powerCore2, (i2 & 2048) != 0 ? results12.routerCore : routerCore2);
    }

    public final CAN component1() {
        return this.cAN;
    }

    public final PeripheralCore component10() {
        return this.peripheralCore;
    }

    public final PowerCore component11() {
        return this.powerCore;
    }

    public final RouterCore component12() {
        return this.routerCore;
    }

    public final DNS114114114114 component2() {
        return this.dNS114114114114;
    }

    public final DNS8888 component3() {
        return this.dNS8888;
    }

    public final DepthCameraFrontDown component4() {
        return this.depthCameraFrontDown;
    }

    public final IMU component5() {
        return this.iMU;
    }

    public final Internet component6() {
        return this.internet;
    }

    public final LaserFront component7() {
        return this.laserFront;
    }

    public final MotorCore component8() {
        return this.motorCore;
    }

    public final Odom component9() {
        return this.odom;
    }

    public final Results1 copy(CAN can, DNS114114114114 dns114114114114, DNS8888 dns8888, DepthCameraFrontDown depthCameraFrontDown2, IMU imu, Internet internet2, LaserFront laserFront2, MotorCore motorCore2, Odom odom2, PeripheralCore peripheralCore2, PowerCore powerCore2, RouterCore routerCore2) {
        Intrinsics.checkNotNullParameter(can, "cAN");
        DNS114114114114 dns1141141141142 = dns114114114114;
        Intrinsics.checkNotNullParameter(dns1141141141142, "dNS114114114114");
        DNS8888 dns88882 = dns8888;
        Intrinsics.checkNotNullParameter(dns88882, "dNS8888");
        DepthCameraFrontDown depthCameraFrontDown3 = depthCameraFrontDown2;
        Intrinsics.checkNotNullParameter(depthCameraFrontDown3, "depthCameraFrontDown");
        IMU imu2 = imu;
        Intrinsics.checkNotNullParameter(imu2, "iMU");
        Internet internet3 = internet2;
        Intrinsics.checkNotNullParameter(internet3, "internet");
        LaserFront laserFront3 = laserFront2;
        Intrinsics.checkNotNullParameter(laserFront3, "laserFront");
        MotorCore motorCore3 = motorCore2;
        Intrinsics.checkNotNullParameter(motorCore3, "motorCore");
        Odom odom3 = odom2;
        Intrinsics.checkNotNullParameter(odom3, "odom");
        PeripheralCore peripheralCore3 = peripheralCore2;
        Intrinsics.checkNotNullParameter(peripheralCore3, "peripheralCore");
        PowerCore powerCore3 = powerCore2;
        Intrinsics.checkNotNullParameter(powerCore3, "powerCore");
        RouterCore routerCore3 = routerCore2;
        Intrinsics.checkNotNullParameter(routerCore3, "routerCore");
        return new Results1(can, dns1141141141142, dns88882, depthCameraFrontDown3, imu2, internet3, laserFront3, motorCore3, odom3, peripheralCore3, powerCore3, routerCore3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Results1)) {
            return false;
        }
        Results1 results1 = (Results1) obj;
        return Intrinsics.areEqual((Object) this.cAN, (Object) results1.cAN) && Intrinsics.areEqual((Object) this.dNS114114114114, (Object) results1.dNS114114114114) && Intrinsics.areEqual((Object) this.dNS8888, (Object) results1.dNS8888) && Intrinsics.areEqual((Object) this.depthCameraFrontDown, (Object) results1.depthCameraFrontDown) && Intrinsics.areEqual((Object) this.iMU, (Object) results1.iMU) && Intrinsics.areEqual((Object) this.internet, (Object) results1.internet) && Intrinsics.areEqual((Object) this.laserFront, (Object) results1.laserFront) && Intrinsics.areEqual((Object) this.motorCore, (Object) results1.motorCore) && Intrinsics.areEqual((Object) this.odom, (Object) results1.odom) && Intrinsics.areEqual((Object) this.peripheralCore, (Object) results1.peripheralCore) && Intrinsics.areEqual((Object) this.powerCore, (Object) results1.powerCore) && Intrinsics.areEqual((Object) this.routerCore, (Object) results1.routerCore);
    }

    public int hashCode() {
        return (((((((((((((((((((((this.cAN.hashCode() * 31) + this.dNS114114114114.hashCode()) * 31) + this.dNS8888.hashCode()) * 31) + this.depthCameraFrontDown.hashCode()) * 31) + this.iMU.hashCode()) * 31) + this.internet.hashCode()) * 31) + this.laserFront.hashCode()) * 31) + this.motorCore.hashCode()) * 31) + this.odom.hashCode()) * 31) + this.peripheralCore.hashCode()) * 31) + this.powerCore.hashCode()) * 31) + this.routerCore.hashCode();
    }

    public String toString() {
        return "Results1(cAN=" + this.cAN + ", dNS114114114114=" + this.dNS114114114114 + ", dNS8888=" + this.dNS8888 + ", depthCameraFrontDown=" + this.depthCameraFrontDown + ", iMU=" + this.iMU + ", internet=" + this.internet + ", laserFront=" + this.laserFront + ", motorCore=" + this.motorCore + ", odom=" + this.odom + ", peripheralCore=" + this.peripheralCore + ", powerCore=" + this.powerCore + ", routerCore=" + this.routerCore + ')';
    }

    public Results1(CAN can, DNS114114114114 dns114114114114, DNS8888 dns8888, DepthCameraFrontDown depthCameraFrontDown2, IMU imu, Internet internet2, LaserFront laserFront2, MotorCore motorCore2, Odom odom2, PeripheralCore peripheralCore2, PowerCore powerCore2, RouterCore routerCore2) {
        Intrinsics.checkNotNullParameter(can, "cAN");
        Intrinsics.checkNotNullParameter(dns114114114114, "dNS114114114114");
        Intrinsics.checkNotNullParameter(dns8888, "dNS8888");
        Intrinsics.checkNotNullParameter(depthCameraFrontDown2, "depthCameraFrontDown");
        Intrinsics.checkNotNullParameter(imu, "iMU");
        Intrinsics.checkNotNullParameter(internet2, "internet");
        Intrinsics.checkNotNullParameter(laserFront2, "laserFront");
        Intrinsics.checkNotNullParameter(motorCore2, "motorCore");
        Intrinsics.checkNotNullParameter(odom2, "odom");
        Intrinsics.checkNotNullParameter(peripheralCore2, "peripheralCore");
        Intrinsics.checkNotNullParameter(powerCore2, "powerCore");
        Intrinsics.checkNotNullParameter(routerCore2, "routerCore");
        this.cAN = can;
        this.dNS114114114114 = dns114114114114;
        this.dNS8888 = dns8888;
        this.depthCameraFrontDown = depthCameraFrontDown2;
        this.iMU = imu;
        this.internet = internet2;
        this.laserFront = laserFront2;
        this.motorCore = motorCore2;
        this.odom = odom2;
        this.peripheralCore = peripheralCore2;
        this.powerCore = powerCore2;
        this.routerCore = routerCore2;
    }

    public final CAN getCAN() {
        return this.cAN;
    }

    public final DNS114114114114 getDNS114114114114() {
        return this.dNS114114114114;
    }

    public final DNS8888 getDNS8888() {
        return this.dNS8888;
    }

    public final DepthCameraFrontDown getDepthCameraFrontDown() {
        return this.depthCameraFrontDown;
    }

    public final IMU getIMU() {
        return this.iMU;
    }

    public final Internet getInternet() {
        return this.internet;
    }

    public final LaserFront getLaserFront() {
        return this.laserFront;
    }

    public final MotorCore getMotorCore() {
        return this.motorCore;
    }

    public final Odom getOdom() {
        return this.odom;
    }

    public final PeripheralCore getPeripheralCore() {
        return this.peripheralCore;
    }

    public final PowerCore getPowerCore() {
        return this.powerCore;
    }

    public final RouterCore getRouterCore() {
        return this.routerCore;
    }
}
