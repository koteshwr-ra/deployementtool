package com.ciot.networklib.bean.hotel;

import com.limpoxe.support.servicemanager.ServiceProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0001\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0002\u0010\u000bJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0001HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003JO\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00012\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\"HÖ\u0001J\t\u0010#\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\r¨\u0006$"}, d2 = {"Lcom/ciot/networklib/bean/hotel/FloorBeanItem;", "", "createTime", "", "description", "", "id", "name", "parentId", "projectId", "updateTime", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;J)V", "getCreateTime", "()J", "getDescription", "()Ljava/lang/String;", "getId", "getName", "getParentId", "()Ljava/lang/Object;", "getProjectId", "getUpdateTime", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: FloorBean.kt */
public final class FloorBeanItem {
    private final long createTime;
    private final String description;
    private final String id;
    private final String name;
    private final Object parentId;
    private final String projectId;
    private final long updateTime;

    public static /* synthetic */ FloorBeanItem copy$default(FloorBeanItem floorBeanItem, long j, String str, String str2, String str3, Object obj, String str4, long j2, int i, Object obj2) {
        FloorBeanItem floorBeanItem2 = floorBeanItem;
        return floorBeanItem.copy((i & 1) != 0 ? floorBeanItem2.createTime : j, (i & 2) != 0 ? floorBeanItem2.description : str, (i & 4) != 0 ? floorBeanItem2.id : str2, (i & 8) != 0 ? floorBeanItem2.name : str3, (i & 16) != 0 ? floorBeanItem2.parentId : obj, (i & 32) != 0 ? floorBeanItem2.projectId : str4, (i & 64) != 0 ? floorBeanItem2.updateTime : j2);
    }

    public final long component1() {
        return this.createTime;
    }

    public final String component2() {
        return this.description;
    }

    public final String component3() {
        return this.id;
    }

    public final String component4() {
        return this.name;
    }

    public final Object component5() {
        return this.parentId;
    }

    public final String component6() {
        return this.projectId;
    }

    public final long component7() {
        return this.updateTime;
    }

    public final FloorBeanItem copy(long j, String str, String str2, String str3, Object obj, String str4, long j2) {
        Intrinsics.checkNotNullParameter(str, "description");
        Intrinsics.checkNotNullParameter(str2, "id");
        String str5 = str3;
        Intrinsics.checkNotNullParameter(str5, ServiceProvider.NAME);
        Object obj2 = obj;
        Intrinsics.checkNotNullParameter(obj2, "parentId");
        String str6 = str4;
        Intrinsics.checkNotNullParameter(str6, "projectId");
        return new FloorBeanItem(j, str, str2, str5, obj2, str6, j2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FloorBeanItem)) {
            return false;
        }
        FloorBeanItem floorBeanItem = (FloorBeanItem) obj;
        return this.createTime == floorBeanItem.createTime && Intrinsics.areEqual((Object) this.description, (Object) floorBeanItem.description) && Intrinsics.areEqual((Object) this.id, (Object) floorBeanItem.id) && Intrinsics.areEqual((Object) this.name, (Object) floorBeanItem.name) && Intrinsics.areEqual(this.parentId, floorBeanItem.parentId) && Intrinsics.areEqual((Object) this.projectId, (Object) floorBeanItem.projectId) && this.updateTime == floorBeanItem.updateTime;
    }

    public int hashCode() {
        return (((((((((((C$r8$backportedMethods$utility$Long$1$hashCode.hashCode(this.createTime) * 31) + this.description.hashCode()) * 31) + this.id.hashCode()) * 31) + this.name.hashCode()) * 31) + this.parentId.hashCode()) * 31) + this.projectId.hashCode()) * 31) + C$r8$backportedMethods$utility$Long$1$hashCode.hashCode(this.updateTime);
    }

    public String toString() {
        return "FloorBeanItem(createTime=" + this.createTime + ", description=" + this.description + ", id=" + this.id + ", name=" + this.name + ", parentId=" + this.parentId + ", projectId=" + this.projectId + ", updateTime=" + this.updateTime + ')';
    }

    public FloorBeanItem(long j, String str, String str2, String str3, Object obj, String str4, long j2) {
        Intrinsics.checkNotNullParameter(str, "description");
        Intrinsics.checkNotNullParameter(str2, "id");
        Intrinsics.checkNotNullParameter(str3, ServiceProvider.NAME);
        Intrinsics.checkNotNullParameter(obj, "parentId");
        Intrinsics.checkNotNullParameter(str4, "projectId");
        this.createTime = j;
        this.description = str;
        this.id = str2;
        this.name = str3;
        this.parentId = obj;
        this.projectId = str4;
        this.updateTime = j2;
    }

    public final long getCreateTime() {
        return this.createTime;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final Object getParentId() {
        return this.parentId;
    }

    public final String getProjectId() {
        return this.projectId;
    }

    public final long getUpdateTime() {
        return this.updateTime;
    }
}
