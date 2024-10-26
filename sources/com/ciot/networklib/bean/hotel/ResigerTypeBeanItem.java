package com.ciot.networklib.bean.hotel;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u001b\n\u0002\u0010\u0002\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\n¢\u0006\u0002\u0010\rJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001f\u001a\u00020\nHÆ\u0003J\t\u0010 \u001a\u00020\u0005HÆ\u0003J\t\u0010!\u001a\u00020\nHÆ\u0003JY\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\nHÆ\u0001J\u0013\u0010#\u001a\u00020\n2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0010\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u0005J\t\u0010(\u001a\u00020\u0003HÖ\u0001J\b\u0010)\u001a\u00020\u0005H\u0016R\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000fR\u001a\u0010\f\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\u0011\"\u0004\b\u0018\u0010\u0019¨\u0006*"}, d2 = {"Lcom/ciot/networklib/bean/hotel/ResigerTypeBeanItem;", "", "function_id", "", "function_key", "", "function_type", "function_name", "function_desc", "function_available", "", "backgroundurl", "isCheck", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Z)V", "getBackgroundurl", "()Ljava/lang/String;", "getFunction_available", "()Z", "getFunction_desc", "getFunction_id", "()I", "getFunction_key", "getFunction_name", "getFunction_type", "setCheck", "(Z)V", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "getMsgType", "", "typeId", "hashCode", "toString", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: ResigerTypeBean.kt */
public final class ResigerTypeBeanItem {
    private final String backgroundurl;
    private final boolean function_available;
    private final String function_desc;
    private final int function_id;
    private final String function_key;
    private final String function_name;
    private final String function_type;
    private boolean isCheck;

    public static /* synthetic */ ResigerTypeBeanItem copy$default(ResigerTypeBeanItem resigerTypeBeanItem, int i, String str, String str2, String str3, String str4, boolean z, String str5, boolean z2, int i2, Object obj) {
        ResigerTypeBeanItem resigerTypeBeanItem2 = resigerTypeBeanItem;
        int i3 = i2;
        return resigerTypeBeanItem.copy((i3 & 1) != 0 ? resigerTypeBeanItem2.function_id : i, (i3 & 2) != 0 ? resigerTypeBeanItem2.function_key : str, (i3 & 4) != 0 ? resigerTypeBeanItem2.function_type : str2, (i3 & 8) != 0 ? resigerTypeBeanItem2.function_name : str3, (i3 & 16) != 0 ? resigerTypeBeanItem2.function_desc : str4, (i3 & 32) != 0 ? resigerTypeBeanItem2.function_available : z, (i3 & 64) != 0 ? resigerTypeBeanItem2.backgroundurl : str5, (i3 & 128) != 0 ? resigerTypeBeanItem2.isCheck : z2);
    }

    public final int component1() {
        return this.function_id;
    }

    public final String component2() {
        return this.function_key;
    }

    public final String component3() {
        return this.function_type;
    }

    public final String component4() {
        return this.function_name;
    }

    public final String component5() {
        return this.function_desc;
    }

    public final boolean component6() {
        return this.function_available;
    }

    public final String component7() {
        return this.backgroundurl;
    }

    public final boolean component8() {
        return this.isCheck;
    }

    public final ResigerTypeBeanItem copy(int i, String str, String str2, String str3, String str4, boolean z, String str5, boolean z2) {
        Intrinsics.checkNotNullParameter(str, "function_key");
        Intrinsics.checkNotNullParameter(str2, "function_type");
        Intrinsics.checkNotNullParameter(str3, "function_name");
        Intrinsics.checkNotNullParameter(str4, "function_desc");
        String str6 = str5;
        Intrinsics.checkNotNullParameter(str6, "backgroundurl");
        return new ResigerTypeBeanItem(i, str, str2, str3, str4, z, str6, z2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResigerTypeBeanItem)) {
            return false;
        }
        ResigerTypeBeanItem resigerTypeBeanItem = (ResigerTypeBeanItem) obj;
        return this.function_id == resigerTypeBeanItem.function_id && Intrinsics.areEqual((Object) this.function_key, (Object) resigerTypeBeanItem.function_key) && Intrinsics.areEqual((Object) this.function_type, (Object) resigerTypeBeanItem.function_type) && Intrinsics.areEqual((Object) this.function_name, (Object) resigerTypeBeanItem.function_name) && Intrinsics.areEqual((Object) this.function_desc, (Object) resigerTypeBeanItem.function_desc) && this.function_available == resigerTypeBeanItem.function_available && Intrinsics.areEqual((Object) this.backgroundurl, (Object) resigerTypeBeanItem.backgroundurl) && this.isCheck == resigerTypeBeanItem.isCheck;
    }

    public int hashCode() {
        int hashCode = ((((((((this.function_id * 31) + this.function_key.hashCode()) * 31) + this.function_type.hashCode()) * 31) + this.function_name.hashCode()) * 31) + this.function_desc.hashCode()) * 31;
        boolean z = this.function_available;
        boolean z2 = true;
        if (z) {
            z = true;
        }
        int hashCode2 = (((hashCode + (z ? 1 : 0)) * 31) + this.backgroundurl.hashCode()) * 31;
        boolean z3 = this.isCheck;
        if (!z3) {
            z2 = z3;
        }
        return hashCode2 + (z2 ? 1 : 0);
    }

    public ResigerTypeBeanItem(int i, String str, String str2, String str3, String str4, boolean z, String str5, boolean z2) {
        Intrinsics.checkNotNullParameter(str, "function_key");
        Intrinsics.checkNotNullParameter(str2, "function_type");
        Intrinsics.checkNotNullParameter(str3, "function_name");
        Intrinsics.checkNotNullParameter(str4, "function_desc");
        Intrinsics.checkNotNullParameter(str5, "backgroundurl");
        this.function_id = i;
        this.function_key = str;
        this.function_type = str2;
        this.function_name = str3;
        this.function_desc = str4;
        this.function_available = z;
        this.backgroundurl = str5;
        this.isCheck = z2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ResigerTypeBeanItem(int i, String str, String str2, String str3, String str4, boolean z, String str5, boolean z2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, str, str2, str3, str4, z, str5, (i2 & 128) != 0 ? false : z2);
    }

    public final int getFunction_id() {
        return this.function_id;
    }

    public final String getFunction_key() {
        return this.function_key;
    }

    public final String getFunction_type() {
        return this.function_type;
    }

    public final String getFunction_name() {
        return this.function_name;
    }

    public final String getFunction_desc() {
        return this.function_desc;
    }

    public final boolean getFunction_available() {
        return this.function_available;
    }

    public final String getBackgroundurl() {
        return this.backgroundurl;
    }

    public final boolean isCheck() {
        return this.isCheck;
    }

    public final void setCheck(boolean z) {
        this.isCheck = z;
    }

    public final void getMsgType(String str) {
        String str2;
        String str3 = "21";
        switch (this.function_id) {
            case 10:
                str2 = "22";
                break;
            case 11:
                str2 = "23";
                break;
            case 12:
                str2 = "24";
                break;
            case 13:
                str2 = "25";
                break;
            case 14:
                str2 = "26";
                break;
            case 15:
                str2 = "27";
                break;
            default:
                str2 = str3;
                break;
        }
        if (str == null) {
            str = str3;
        }
        if (!Intrinsics.areEqual((Object) str, (Object) "19")) {
            str3 = str;
        }
        this.isCheck = Intrinsics.areEqual((Object) str2, (Object) str3);
    }

    public String toString() {
        return super.toString();
    }
}
