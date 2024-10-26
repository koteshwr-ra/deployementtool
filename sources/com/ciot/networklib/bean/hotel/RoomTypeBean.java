package com.ciot.networklib.bean.hotel;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0007J$\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0010J\u0013\u0010\u0011\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b\u0004\u0010\u0007\"\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/ciot/networklib/bean/hotel/RoomTypeBean;", "", "roomtypeName", "", "isCheck", "", "(Ljava/lang/String;Ljava/lang/Boolean;)V", "()Ljava/lang/Boolean;", "setCheck", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getRoomtypeName", "()Ljava/lang/String;", "component1", "component2", "copy", "(Ljava/lang/String;Ljava/lang/Boolean;)Lcom/ciot/networklib/bean/hotel/RoomTypeBean;", "equals", "other", "hashCode", "", "toString", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: RoomTypeBean.kt */
public final class RoomTypeBean {
    private Boolean isCheck;
    private final String roomtypeName;

    public static /* synthetic */ RoomTypeBean copy$default(RoomTypeBean roomTypeBean, String str, Boolean bool, int i, Object obj) {
        if ((i & 1) != 0) {
            str = roomTypeBean.roomtypeName;
        }
        if ((i & 2) != 0) {
            bool = roomTypeBean.isCheck;
        }
        return roomTypeBean.copy(str, bool);
    }

    public final String component1() {
        return this.roomtypeName;
    }

    public final Boolean component2() {
        return this.isCheck;
    }

    public final RoomTypeBean copy(String str, Boolean bool) {
        Intrinsics.checkNotNullParameter(str, "roomtypeName");
        return new RoomTypeBean(str, bool);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RoomTypeBean)) {
            return false;
        }
        RoomTypeBean roomTypeBean = (RoomTypeBean) obj;
        return Intrinsics.areEqual((Object) this.roomtypeName, (Object) roomTypeBean.roomtypeName) && Intrinsics.areEqual((Object) this.isCheck, (Object) roomTypeBean.isCheck);
    }

    public int hashCode() {
        int hashCode = this.roomtypeName.hashCode() * 31;
        Boolean bool = this.isCheck;
        return hashCode + (bool == null ? 0 : bool.hashCode());
    }

    public String toString() {
        return "RoomTypeBean(roomtypeName=" + this.roomtypeName + ", isCheck=" + this.isCheck + ')';
    }

    public RoomTypeBean(String str, Boolean bool) {
        Intrinsics.checkNotNullParameter(str, "roomtypeName");
        this.roomtypeName = str;
        this.isCheck = bool;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ RoomTypeBean(String str, Boolean bool, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? false : bool);
    }

    public final String getRoomtypeName() {
        return this.roomtypeName;
    }

    public final Boolean isCheck() {
        return this.isCheck;
    }

    public final void setCheck(Boolean bool) {
        this.isCheck = bool;
    }
}
