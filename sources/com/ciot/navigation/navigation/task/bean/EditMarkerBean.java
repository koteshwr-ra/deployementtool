package com.ciot.navigation.navigation.task.bean;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001a\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0011\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010 \u001a\u00020\u0000J\b\u0010!\u001a\u00020\u000bH\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R$\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0005@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0007\"\u0004\b\u0019\u0010\tR\u001a\u0010\u001a\u001a\u00020\u0011X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0013\"\u0004\b\u001c\u0010\u0015R\u001a\u0010\u001d\u001a\u00020\u0011X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0013\"\u0004\b\u001f\u0010\u0015¨\u0006\""}, d2 = {"Lcom/ciot/navigation/navigation/task/bean/EditMarkerBean;", "Ljava/io/Serializable;", "", "()V", "floor", "", "getFloor", "()I", "setFloor", "(I)V", "name", "", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "theta", "", "getTheta", "()D", "setTheta", "(D)V", "value", "type", "getType", "setType", "x", "getX", "setX", "y", "getY", "setY", "copy", "toString", "library-navigation_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: EditMarkerBean.kt */
public final class EditMarkerBean implements Serializable, Cloneable {
    private int floor = 1;
    private String name = "";
    private double theta;
    private int type;
    private double x;
    private double y;

    public Object clone() {
        return super.clone();
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    public final double getX() {
        return this.x;
    }

    public final void setX(double d) {
        this.x = d;
    }

    public final double getY() {
        return this.y;
    }

    public final void setY(double d) {
        this.y = d;
    }

    public final double getTheta() {
        return this.theta;
    }

    public final void setTheta(double d) {
        this.theta = d;
    }

    public final int getType() {
        return this.type;
    }

    public final void setType(int i) {
        this.type = i;
    }

    public final int getFloor() {
        return this.floor;
    }

    public final void setFloor(int i) {
        this.floor = i;
    }

    public String toString() {
        return "EditMarkerBean(name=" + this.name + ", x=" + this.x + ", y=" + this.y + ", theta=" + this.theta + ", type=" + this.type + ", floor=" + this.floor + ')';
    }

    public final EditMarkerBean copy() {
        return (EditMarkerBean) clone();
    }
}
