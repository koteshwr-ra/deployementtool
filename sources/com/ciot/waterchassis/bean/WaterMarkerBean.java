package com.ciot.waterchassis.bean;

import com.ciot.base.util.PinyinUtils;

public class WaterMarkerBean {
    private String avatar;
    private int floor;
    public boolean isChecked;
    private int key;
    private String marker_name;
    private String num;
    private String pinyin;
    private PoseBean pose;

    public String getPinyin() {
        return this.pinyin;
    }

    public void setPinyin(String str) {
        this.pinyin = str;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public int getFloor() {
        return this.floor;
    }

    public void setFloor(int i) {
        this.floor = i;
    }

    public int getKey() {
        return this.key;
    }

    public void setKey(int i) {
        this.key = i;
    }

    public String getMarker_name() {
        return this.marker_name;
    }

    public void setMarker_name(String str) {
        this.marker_name = str;
        this.pinyin = PinyinUtils.getFirstCcs2PinyinUpCase(str);
    }

    public PoseBean getPose() {
        return this.pose;
    }

    public void setPose(PoseBean poseBean) {
        this.pose = poseBean;
    }

    public String getNum() {
        return this.num;
    }

    public void setNum(String str) {
        this.num = str;
    }

    public static class PoseBean {
        private OrientationBean orientation;
        private PositionBean position;

        public OrientationBean getOrientation() {
            return this.orientation;
        }

        public void setOrientation(OrientationBean orientationBean) {
            this.orientation = orientationBean;
        }

        public PositionBean getPosition() {
            return this.position;
        }

        public void setPosition(PositionBean positionBean) {
            this.position = positionBean;
        }

        public static class OrientationBean {
            private double w;
            private float x;
            private float y;
            private double z;

            public double getW() {
                return this.w;
            }

            public void setW(double d) {
                this.w = d;
            }

            public float getX() {
                return this.x;
            }

            public void setX(float f) {
                this.x = f;
            }

            public float getY() {
                return this.y;
            }

            public void setY(float f) {
                this.y = f;
            }

            public double getZ() {
                return this.z;
            }

            public void setZ(double d) {
                this.z = d;
            }

            public String toString() {
                return "OrientationBean{w=" + this.w + ", x=" + this.x + ", y=" + this.y + ", z=" + this.z + '}';
            }
        }

        public static class PositionBean {
            private double x;
            private double y;
            private float z;

            public double getX() {
                return this.x;
            }

            public void setX(double d) {
                this.x = d;
            }

            public double getY() {
                return this.y;
            }

            public void setY(double d) {
                this.y = d;
            }

            public float getZ() {
                return this.z;
            }

            public void setZ(float f) {
                this.z = f;
            }

            public String toString() {
                return "PositionBean{x=" + this.x + ", y=" + this.y + ", z=" + this.z + '}';
            }
        }

        public String toString() {
            return "PoseBean{orientation=" + this.orientation + ", position=" + this.position + '}';
        }
    }

    public String toString() {
        return "WaterMarkerBean{avatar='" + this.avatar + '\'' + ", floor=" + this.floor + ", key=" + this.key + ", marker_name='" + this.marker_name + '\'' + ", pose=" + this.pose + '}';
    }
}
