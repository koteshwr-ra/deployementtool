package com.example.sroslibrary.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class QueryNavigationBeanR implements Serializable {
    private static final long serialVersionUID = 6269443258130365580L;
    @SerializedName("data")
    @Expose
    private List<NavigationPoint> data;
    @SerializedName("reason")
    @Expose
    private String reason;
    @SerializedName("result")
    @Expose
    private boolean result;

    public boolean isResult() {
        return this.result;
    }

    public void setResult(boolean z) {
        this.result = z;
    }

    public List<NavigationPoint> getData() {
        return this.data;
    }

    public void setData(List<NavigationPoint> list) {
        this.data = list;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String str) {
        this.reason = str;
    }

    public static class NavigationPoint {
        @Expose
        private float angle;
        @Expose
        private String mapinfo;
        @Expose
        private String positionname;
        @Expose
        private int type;
        @Expose
        private int x;
        @Expose
        private double x1;
        @Expose
        private int y;
        @Expose
        private double y1;
        @Expose
        private int z;
        @Expose
        private double z1;

        public float getAngle() {
            return this.angle;
        }

        public void setAngle(float f) {
            this.angle = f;
        }

        public int getX() {
            return this.x;
        }

        public void setX(int i) {
            this.x = i;
        }

        public int getY() {
            return this.y;
        }

        public void setY(int i) {
            this.y = i;
        }

        public void setX1(double d) {
            this.x1 = d;
        }

        public void setY1(double d) {
            this.y1 = d;
        }

        public void setZ1(double d) {
            this.z1 = d;
        }

        public void setZ1(float f) {
            this.z1 = (double) f;
        }

        public double getX1() {
            return this.x1;
        }

        public double getY1() {
            return this.y1;
        }

        public double getZ1() {
            return this.z1;
        }

        public String getPositionname() {
            return this.positionname;
        }

        public void setPositionname(String str) {
            this.positionname = str;
        }

        public String getMapinfo() {
            return this.mapinfo;
        }

        public void setMapinfo(String str) {
            this.mapinfo = str;
        }

        public int getZ() {
            return this.z;
        }

        public void setZ(int i) {
            this.z = i;
        }

        public int getType() {
            return this.type;
        }

        public void setType(int i) {
            this.type = i;
        }

        public String toString() {
            return "NavigationPoint{angle=" + this.angle + ", x=" + this.x + ", y=" + this.y + ", z=" + this.z + ", positionname='" + this.positionname + '\'' + ", mapinfo='" + this.mapinfo + '\'' + ", type=" + this.type + '}';
        }
    }

    public String toString() {
        return "QueryNavigationBeanR{result=" + this.result + "reason=" + this.reason + ", data=" + this.data + '}';
    }
}
