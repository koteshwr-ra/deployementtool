package com.ciot.networklib.bean;

import java.util.List;

public class DownloadSelfMapBean {
    private List<FloorBean> floor;
    private String id;
    private String name;
    private String project;

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public List<FloorBean> getFloor() {
        return this.floor;
    }

    public void setFloor(List<FloorBean> list) {
        this.floor = list;
    }

    public String getProject() {
        return this.project;
    }

    public void setProject(String str) {
        this.project = str;
    }

    public static class FloorBean {
        private String alias;
        private String annex;
        private int height;
        private int num;
        private String path;
        private int pixel;
        private List<PlaceBean> place;
        private int width;

        public int getNum() {
            return this.num;
        }

        public void setNum(int i) {
            this.num = i;
        }

        public String getAlias() {
            return this.alias;
        }

        public void setAlias(String str) {
            this.alias = str;
        }

        public int getWidth() {
            return this.width;
        }

        public void setWidth(int i) {
            this.width = i;
        }

        public int getHeight() {
            return this.height;
        }

        public void setHeight(int i) {
            this.height = i;
        }

        public int getPixel() {
            return this.pixel;
        }

        public void setPixel(int i) {
            this.pixel = i;
        }

        public String getPath() {
            return this.path;
        }

        public void setPath(String str) {
            this.path = str;
        }

        public String getAnnex() {
            return this.annex;
        }

        public void setAnnex(String str) {
            this.annex = str;
        }

        public List<PlaceBean> getPlace() {
            return this.place;
        }

        public void setPlace(List<PlaceBean> list) {
            this.place = list;
        }

        public static class PlaceBean {
            private float angle;
            private String name;
            private int type;
            private float x;
            private float y;

            public String getName() {
                return this.name;
            }

            public void setName(String str) {
                this.name = str;
            }

            public int getType() {
                return this.type;
            }

            public void setType(int i) {
                this.type = i;
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

            public float getAngle() {
                return this.angle;
            }

            public void setAngle(float f) {
                this.angle = f;
            }
        }
    }
}
