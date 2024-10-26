package com.ciot.networklib.bean.hotel;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003JE\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u001e"}, d2 = {"Lcom/ciot/networklib/bean/hotel/RegisterData;", "", "description", "", "phone", "registerId", "registerType", "registerTypeName", "state", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDescription", "()Ljava/lang/String;", "getPhone", "getRegisterId", "getRegisterType", "getRegisterTypeName", "getState", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: RoomBean.kt */
public final class RegisterData {
    private final String description;
    private final String phone;
    private final String registerId;
    private final String registerType;
    private final String registerTypeName;
    private final String state;

    public static /* synthetic */ RegisterData copy$default(RegisterData registerData, String str, String str2, String str3, String str4, String str5, String str6, int i, Object obj) {
        if ((i & 1) != 0) {
            str = registerData.description;
        }
        if ((i & 2) != 0) {
            str2 = registerData.phone;
        }
        String str7 = str2;
        if ((i & 4) != 0) {
            str3 = registerData.registerId;
        }
        String str8 = str3;
        if ((i & 8) != 0) {
            str4 = registerData.registerType;
        }
        String str9 = str4;
        if ((i & 16) != 0) {
            str5 = registerData.registerTypeName;
        }
        String str10 = str5;
        if ((i & 32) != 0) {
            str6 = registerData.state;
        }
        return registerData.copy(str, str7, str8, str9, str10, str6);
    }

    public final String component1() {
        return this.description;
    }

    public final String component2() {
        return this.phone;
    }

    public final String component3() {
        return this.registerId;
    }

    public final String component4() {
        return this.registerType;
    }

    public final String component5() {
        return this.registerTypeName;
    }

    public final String component6() {
        return this.state;
    }

    public final RegisterData copy(String str, String str2, String str3, String str4, String str5, String str6) {
        Intrinsics.checkNotNullParameter(str, "description");
        Intrinsics.checkNotNullParameter(str2, "phone");
        Intrinsics.checkNotNullParameter(str3, "registerId");
        Intrinsics.checkNotNullParameter(str4, "registerType");
        Intrinsics.checkNotNullParameter(str5, "registerTypeName");
        Intrinsics.checkNotNullParameter(str6, "state");
        return new RegisterData(str, str2, str3, str4, str5, str6);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RegisterData)) {
            return false;
        }
        RegisterData registerData = (RegisterData) obj;
        return Intrinsics.areEqual((Object) this.description, (Object) registerData.description) && Intrinsics.areEqual((Object) this.phone, (Object) registerData.phone) && Intrinsics.areEqual((Object) this.registerId, (Object) registerData.registerId) && Intrinsics.areEqual((Object) this.registerType, (Object) registerData.registerType) && Intrinsics.areEqual((Object) this.registerTypeName, (Object) registerData.registerTypeName) && Intrinsics.areEqual((Object) this.state, (Object) registerData.state);
    }

    public int hashCode() {
        return (((((((((this.description.hashCode() * 31) + this.phone.hashCode()) * 31) + this.registerId.hashCode()) * 31) + this.registerType.hashCode()) * 31) + this.registerTypeName.hashCode()) * 31) + this.state.hashCode();
    }

    public String toString() {
        return "RegisterData(description=" + this.description + ", phone=" + this.phone + ", registerId=" + this.registerId + ", registerType=" + this.registerType + ", registerTypeName=" + this.registerTypeName + ", state=" + this.state + ')';
    }

    public RegisterData(String str, String str2, String str3, String str4, String str5, String str6) {
        Intrinsics.checkNotNullParameter(str, "description");
        Intrinsics.checkNotNullParameter(str2, "phone");
        Intrinsics.checkNotNullParameter(str3, "registerId");
        Intrinsics.checkNotNullParameter(str4, "registerType");
        Intrinsics.checkNotNullParameter(str5, "registerTypeName");
        Intrinsics.checkNotNullParameter(str6, "state");
        this.description = str;
        this.phone = str2;
        this.registerId = str3;
        this.registerType = str4;
        this.registerTypeName = str5;
        this.state = str6;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getPhone() {
        return this.phone;
    }

    public final String getRegisterId() {
        return this.registerId;
    }

    public final String getRegisterType() {
        return this.registerType;
    }

    public final String getRegisterTypeName() {
        return this.registerTypeName;
    }

    public final String getState() {
        return this.state;
    }
}
