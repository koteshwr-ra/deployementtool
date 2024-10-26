package com.example.sroslibrary.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class QueryMapBeanR implements Serializable {
    private static final long serialVersionUID = 2017512352081993303L;
    @SerializedName("maptype")
    @Expose
    private String mapType;
    @SerializedName("data")
    @Expose
    private List<Map> maps;
    @SerializedName("reason")
    @Expose
    private String reason;
    @SerializedName("resolution")
    @Expose
    private float resolution;
    @SerializedName("result")
    @Expose
    private boolean result;

    public boolean isResult() {
        return this.result;
    }

    public void setResult(boolean z) {
        this.result = z;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String str) {
        this.reason = str;
    }

    public String isMapType() {
        return this.mapType;
    }

    public void setMapType(String str) {
        this.mapType = str;
    }

    public float getResolution() {
        return this.resolution;
    }

    public void setResolution(float f) {
        this.resolution = f;
    }

    public List<Map> getMaps() {
        return this.maps;
    }

    public void setMaps(List<Map> list) {
        this.maps = list;
    }

    public static class Map {
        @SerializedName("mapheight")
        @Expose
        private int mapHeight;
        @SerializedName("mapinfo")
        @Expose
        private String mapInfo;
        @SerializedName("mapname")
        @Expose
        private String mapName;
        @SerializedName("mapwidth")
        @Expose
        private int mapWidth;
        @SerializedName("oringinx")
        @Expose
        private int oringinX;
        @SerializedName("oringiny")
        @Expose
        private int oringinY;
        @SerializedName("z")
        @Expose
        private int z;

        public String getMapName() {
            return this.mapName;
        }

        public void setMapName(String str) {
            this.mapName = str;
        }

        public int getMapHeight() {
            return this.mapHeight;
        }

        public void setMapHeight(int i) {
            this.mapHeight = i;
        }

        public int getMapWidth() {
            return this.mapWidth;
        }

        public void setMapWidth(int i) {
            this.mapWidth = i;
        }

        public int getOringinX() {
            return this.oringinX;
        }

        public void setOringinX(int i) {
            this.oringinX = i;
        }

        public int getOringinY() {
            return this.oringinY;
        }

        public void setOringinY(int i) {
            this.oringinY = i;
        }

        public String getMapInfo() {
            return this.mapInfo;
        }

        public void setMapInfo(String str) {
            this.mapInfo = str;
        }

        public int getZ() {
            return this.z;
        }

        public void setZ(int i) {
            this.z = i;
        }

        public String toString() {
            return "Map{mapName='" + this.mapName + '\'' + ", mapHeight=" + this.mapHeight + ", mapWidth=" + this.mapWidth + ", oringinX=" + this.oringinX + ", oringinY=" + this.oringinY + ", mapInfo='" + this.mapInfo + '\'' + ", z='" + this.z + '\'' + '}';
        }
    }

    public String toString() {
        return "QueryMapBeanR{result=" + this.result + ", reason=" + this.reason + ", mapType=" + this.mapType + ", resolution=" + this.resolution + ", maps=" + this.maps + '}';
    }
}
