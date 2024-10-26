package com.example.chassislibrary.bean;

import com.limpoxe.support.servicemanager.ServiceProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b$\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\u0007¢\u0006\u0002\u0010\rJ\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0007HÆ\u0003J\t\u0010(\u001a\u00020\tHÆ\u0003J\t\u0010)\u001a\u00020\tHÆ\u0003J\t\u0010*\u001a\u00020\tHÆ\u0003J\t\u0010+\u001a\u00020\u0007HÆ\u0003JY\u0010,\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\u0007HÆ\u0001J\u0013\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00100\u001a\u00020\u0007HÖ\u0001J\t\u00101\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0005\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000f\"\u0004\b\u0013\u0010\u0011R\u001a\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000f\"\u0004\b\u0015\u0010\u0011R\u001a\u0010\u000b\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\b\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0017\"\u0004\b\u001f\u0010\u0019R\u001a\u0010\n\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0017\"\u0004\b!\u0010\u0019R\u001a\u0010\f\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u001b\"\u0004\b#\u0010\u001d¨\u00062"}, d2 = {"Lcom/example/chassislibrary/bean/APositionTag;", "", "id", "", "name", "content", "type", "", "x", "", "y", "theta", "z", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IFFFI)V", "getContent", "()Ljava/lang/String;", "setContent", "(Ljava/lang/String;)V", "getId", "setId", "getName", "setName", "getTheta", "()F", "setTheta", "(F)V", "getType", "()I", "setType", "(I)V", "getX", "setX", "getY", "setY", "getZ", "setZ", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "toString", "library-navigation_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: APositionTag.kt */
public final class APositionTag {
    private String content;
    private String id;
    private String name;
    private float theta;
    private int type;
    private float x;
    private float y;
    private int z;

    public static /* synthetic */ APositionTag copy$default(APositionTag aPositionTag, String str, String str2, String str3, int i, float f, float f2, float f3, int i2, int i3, Object obj) {
        APositionTag aPositionTag2 = aPositionTag;
        int i4 = i3;
        return aPositionTag.copy((i4 & 1) != 0 ? aPositionTag2.id : str, (i4 & 2) != 0 ? aPositionTag2.name : str2, (i4 & 4) != 0 ? aPositionTag2.content : str3, (i4 & 8) != 0 ? aPositionTag2.type : i, (i4 & 16) != 0 ? aPositionTag2.x : f, (i4 & 32) != 0 ? aPositionTag2.y : f2, (i4 & 64) != 0 ? aPositionTag2.theta : f3, (i4 & 128) != 0 ? aPositionTag2.z : i2);
    }

    public final String component1() {
        return this.id;
    }

    public final String component2() {
        return this.name;
    }

    public final String component3() {
        return this.content;
    }

    public final int component4() {
        return this.type;
    }

    public final float component5() {
        return this.x;
    }

    public final float component6() {
        return this.y;
    }

    public final float component7() {
        return this.theta;
    }

    public final int component8() {
        return this.z;
    }

    public final APositionTag copy(String str, String str2, String str3, int i, float f, float f2, float f3, int i2) {
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(str2, ServiceProvider.NAME);
        Intrinsics.checkNotNullParameter(str3, "content");
        return new APositionTag(str, str2, str3, i, f, f2, f3, i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof APositionTag)) {
            return false;
        }
        APositionTag aPositionTag = (APositionTag) obj;
        return Intrinsics.areEqual((Object) this.id, (Object) aPositionTag.id) && Intrinsics.areEqual((Object) this.name, (Object) aPositionTag.name) && Intrinsics.areEqual((Object) this.content, (Object) aPositionTag.content) && this.type == aPositionTag.type && Intrinsics.areEqual((Object) Float.valueOf(this.x), (Object) Float.valueOf(aPositionTag.x)) && Intrinsics.areEqual((Object) Float.valueOf(this.y), (Object) Float.valueOf(aPositionTag.y)) && Intrinsics.areEqual((Object) Float.valueOf(this.theta), (Object) Float.valueOf(aPositionTag.theta)) && this.z == aPositionTag.z;
    }

    public int hashCode() {
        return (((((((((((((this.id.hashCode() * 31) + this.name.hashCode()) * 31) + this.content.hashCode()) * 31) + this.type) * 31) + Float.floatToIntBits(this.x)) * 31) + Float.floatToIntBits(this.y)) * 31) + Float.floatToIntBits(this.theta)) * 31) + this.z;
    }

    public String toString() {
        return "APositionTag(id=" + this.id + ", name=" + this.name + ", content=" + this.content + ", type=" + this.type + ", x=" + this.x + ", y=" + this.y + ", theta=" + this.theta + ", z=" + this.z + ')';
    }

    public APositionTag(String str, String str2, String str3, int i, float f, float f2, float f3, int i2) {
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(str2, ServiceProvider.NAME);
        Intrinsics.checkNotNullParameter(str3, "content");
        this.id = str;
        this.name = str2;
        this.content = str3;
        this.type = i;
        this.x = f;
        this.y = f2;
        this.theta = f3;
        this.z = i2;
    }

    public final String getId() {
        return this.id;
    }

    public final void setId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.id = str;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    public final String getContent() {
        return this.content;
    }

    public final void setContent(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.content = str;
    }

    public final int getType() {
        return this.type;
    }

    public final void setType(int i) {
        this.type = i;
    }

    public final float getX() {
        return this.x;
    }

    public final void setX(float f) {
        this.x = f;
    }

    public final float getY() {
        return this.y;
    }

    public final void setY(float f) {
        this.y = f;
    }

    public final float getTheta() {
        return this.theta;
    }

    public final void setTheta(float f) {
        this.theta = f;
    }

    public final int getZ() {
        return this.z;
    }

    public final void setZ(int i) {
        this.z = i;
    }
}
