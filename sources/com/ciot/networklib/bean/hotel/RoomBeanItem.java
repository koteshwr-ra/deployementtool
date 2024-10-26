package com.ciot.networklib.bean.hotel;

import com.limpoxe.support.servicemanager.ServiceProvider;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\"\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001Bm\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0006\u0010\u000e\u001a\u00020\u0005\u0012\u0006\u0010\u000f\u001a\u00020\u0005\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012¢\u0006\u0002\u0010\u0013J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0005HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0012HÆ\u0003J\t\u0010)\u001a\u00020\u0005HÆ\u0003J\t\u0010*\u001a\u00020\u0005HÆ\u0003J\t\u0010+\u001a\u00020\u0005HÆ\u0003J\t\u0010,\u001a\u00020\u0005HÆ\u0003J\t\u0010-\u001a\u00020\u0005HÆ\u0003J\t\u0010.\u001a\u00020\u0005HÆ\u0003J\u000f\u0010/\u001a\b\u0012\u0004\u0012\u00020\r0\fHÆ\u0003J\t\u00100\u001a\u00020\u0005HÆ\u0003J\u0001\u00101\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u0012HÆ\u0001J\u0013\u00102\u001a\u00020\u00122\b\u00103\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00104\u001a\u000205HÖ\u0001J\t\u00106\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0017R\u001a\u0010\u0011\u001a\u00020\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0017R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0017R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0017R\u0011\u0010\u000f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0017R\u0011\u0010\u0010\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0015¨\u00067"}, d2 = {"Lcom/ciot/networklib/bean/hotel/RoomBeanItem;", "", "createTime", "", "description", "", "floorId", "floorName", "id", "name", "projectId", "registerDatas", "", "Lcom/ciot/networklib/bean/hotel/RegisterData;", "roomTypeId", "roomtypeName", "updateTime", "isCheck", "", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;JZ)V", "getCreateTime", "()J", "getDescription", "()Ljava/lang/String;", "getFloorId", "getFloorName", "getId", "()Z", "setCheck", "(Z)V", "getName", "getProjectId", "getRegisterDatas", "()Ljava/util/List;", "getRoomTypeId", "getRoomtypeName", "getUpdateTime", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: RoomBean.kt */
public final class RoomBeanItem {
    private final long createTime;
    private final String description;
    private final String floorId;
    private final String floorName;
    private final String id;
    private boolean isCheck;
    private final String name;
    private final String projectId;
    private final List<RegisterData> registerDatas;
    private final String roomTypeId;
    private final String roomtypeName;
    private final long updateTime;

    public static /* synthetic */ RoomBeanItem copy$default(RoomBeanItem roomBeanItem, long j, String str, String str2, String str3, String str4, String str5, String str6, List list, String str7, String str8, long j2, boolean z, int i, Object obj) {
        RoomBeanItem roomBeanItem2 = roomBeanItem;
        int i2 = i;
        return roomBeanItem.copy((i2 & 1) != 0 ? roomBeanItem2.createTime : j, (i2 & 2) != 0 ? roomBeanItem2.description : str, (i2 & 4) != 0 ? roomBeanItem2.floorId : str2, (i2 & 8) != 0 ? roomBeanItem2.floorName : str3, (i2 & 16) != 0 ? roomBeanItem2.id : str4, (i2 & 32) != 0 ? roomBeanItem2.name : str5, (i2 & 64) != 0 ? roomBeanItem2.projectId : str6, (i2 & 128) != 0 ? roomBeanItem2.registerDatas : list, (i2 & 256) != 0 ? roomBeanItem2.roomTypeId : str7, (i2 & 512) != 0 ? roomBeanItem2.roomtypeName : str8, (i2 & 1024) != 0 ? roomBeanItem2.updateTime : j2, (i2 & 2048) != 0 ? roomBeanItem2.isCheck : z);
    }

    public final long component1() {
        return this.createTime;
    }

    public final String component10() {
        return this.roomtypeName;
    }

    public final long component11() {
        return this.updateTime;
    }

    public final boolean component12() {
        return this.isCheck;
    }

    public final String component2() {
        return this.description;
    }

    public final String component3() {
        return this.floorId;
    }

    public final String component4() {
        return this.floorName;
    }

    public final String component5() {
        return this.id;
    }

    public final String component6() {
        return this.name;
    }

    public final String component7() {
        return this.projectId;
    }

    public final List<RegisterData> component8() {
        return this.registerDatas;
    }

    public final String component9() {
        return this.roomTypeId;
    }

    public final RoomBeanItem copy(long j, String str, String str2, String str3, String str4, String str5, String str6, List<RegisterData> list, String str7, String str8, long j2, boolean z) {
        String str9 = str;
        Intrinsics.checkNotNullParameter(str9, "description");
        String str10 = str2;
        Intrinsics.checkNotNullParameter(str10, "floorId");
        String str11 = str3;
        Intrinsics.checkNotNullParameter(str11, "floorName");
        String str12 = str4;
        Intrinsics.checkNotNullParameter(str12, "id");
        String str13 = str5;
        Intrinsics.checkNotNullParameter(str13, ServiceProvider.NAME);
        String str14 = str6;
        Intrinsics.checkNotNullParameter(str14, "projectId");
        List<RegisterData> list2 = list;
        Intrinsics.checkNotNullParameter(list2, "registerDatas");
        String str15 = str7;
        Intrinsics.checkNotNullParameter(str15, "roomTypeId");
        String str16 = str8;
        Intrinsics.checkNotNullParameter(str16, "roomtypeName");
        return new RoomBeanItem(j, str9, str10, str11, str12, str13, str14, list2, str15, str16, j2, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RoomBeanItem)) {
            return false;
        }
        RoomBeanItem roomBeanItem = (RoomBeanItem) obj;
        return this.createTime == roomBeanItem.createTime && Intrinsics.areEqual((Object) this.description, (Object) roomBeanItem.description) && Intrinsics.areEqual((Object) this.floorId, (Object) roomBeanItem.floorId) && Intrinsics.areEqual((Object) this.floorName, (Object) roomBeanItem.floorName) && Intrinsics.areEqual((Object) this.id, (Object) roomBeanItem.id) && Intrinsics.areEqual((Object) this.name, (Object) roomBeanItem.name) && Intrinsics.areEqual((Object) this.projectId, (Object) roomBeanItem.projectId) && Intrinsics.areEqual((Object) this.registerDatas, (Object) roomBeanItem.registerDatas) && Intrinsics.areEqual((Object) this.roomTypeId, (Object) roomBeanItem.roomTypeId) && Intrinsics.areEqual((Object) this.roomtypeName, (Object) roomBeanItem.roomtypeName) && this.updateTime == roomBeanItem.updateTime && this.isCheck == roomBeanItem.isCheck;
    }

    public int hashCode() {
        int hashCode = ((((((((((((((((((((C$r8$backportedMethods$utility$Long$1$hashCode.hashCode(this.createTime) * 31) + this.description.hashCode()) * 31) + this.floorId.hashCode()) * 31) + this.floorName.hashCode()) * 31) + this.id.hashCode()) * 31) + this.name.hashCode()) * 31) + this.projectId.hashCode()) * 31) + this.registerDatas.hashCode()) * 31) + this.roomTypeId.hashCode()) * 31) + this.roomtypeName.hashCode()) * 31) + C$r8$backportedMethods$utility$Long$1$hashCode.hashCode(this.updateTime)) * 31;
        boolean z = this.isCheck;
        if (z) {
            z = true;
        }
        return hashCode + (z ? 1 : 0);
    }

    public String toString() {
        return "RoomBeanItem(createTime=" + this.createTime + ", description=" + this.description + ", floorId=" + this.floorId + ", floorName=" + this.floorName + ", id=" + this.id + ", name=" + this.name + ", projectId=" + this.projectId + ", registerDatas=" + this.registerDatas + ", roomTypeId=" + this.roomTypeId + ", roomtypeName=" + this.roomtypeName + ", updateTime=" + this.updateTime + ", isCheck=" + this.isCheck + ')';
    }

    public RoomBeanItem(long j, String str, String str2, String str3, String str4, String str5, String str6, List<RegisterData> list, String str7, String str8, long j2, boolean z) {
        Intrinsics.checkNotNullParameter(str, "description");
        Intrinsics.checkNotNullParameter(str2, "floorId");
        Intrinsics.checkNotNullParameter(str3, "floorName");
        Intrinsics.checkNotNullParameter(str4, "id");
        Intrinsics.checkNotNullParameter(str5, ServiceProvider.NAME);
        Intrinsics.checkNotNullParameter(str6, "projectId");
        Intrinsics.checkNotNullParameter(list, "registerDatas");
        Intrinsics.checkNotNullParameter(str7, "roomTypeId");
        Intrinsics.checkNotNullParameter(str8, "roomtypeName");
        this.createTime = j;
        this.description = str;
        this.floorId = str2;
        this.floorName = str3;
        this.id = str4;
        this.name = str5;
        this.projectId = str6;
        this.registerDatas = list;
        this.roomTypeId = str7;
        this.roomtypeName = str8;
        this.updateTime = j2;
        this.isCheck = z;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ RoomBeanItem(long j, String str, String str2, String str3, String str4, String str5, String str6, List list, String str7, String str8, long j2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, str, str2, str3, str4, str5, str6, list, str7, str8, j2, (i & 2048) != 0 ? false : z);
    }

    public final long getCreateTime() {
        return this.createTime;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getFloorId() {
        return this.floorId;
    }

    public final String getFloorName() {
        return this.floorName;
    }

    public final String getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final String getProjectId() {
        return this.projectId;
    }

    public final List<RegisterData> getRegisterDatas() {
        return this.registerDatas;
    }

    public final String getRoomTypeId() {
        return this.roomTypeId;
    }

    public final String getRoomtypeName() {
        return this.roomtypeName;
    }

    public final long getUpdateTime() {
        return this.updateTime;
    }

    public final boolean isCheck() {
        return this.isCheck;
    }

    public final void setCheck(boolean z) {
        this.isCheck = z;
    }
}
