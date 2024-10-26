package com.ciot.networklib.bean;

import java.util.List;

public class UploadSelfMapRequestBean {
    private List<FloorBean> floor;
    private String id;
    private long insTime;
    private String name;
    private String project;

    public List<FloorBean> getFloor() {
        return this.floor;
    }

    public void setFloor(List<FloorBean> list) {
        this.floor = list;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getProject() {
        return this.project;
    }

    public void setProject(String str) {
        this.project = str;
    }

    public long getInsTime() {
        return this.insTime;
    }

    public void setInsTime(long j) {
        this.insTime = j;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public static class FloorBean {
        private String alias;
        private String annex;
        private AnnexresourceBean annexresource;
        private ArearesourceBean arearesource;
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

        public ArearesourceBean getArearesource() {
            return this.arearesource;
        }

        public void setArearesource(ArearesourceBean arearesourceBean) {
            this.arearesource = arearesourceBean;
        }

        public AnnexresourceBean getAnnexresource() {
            return this.annexresource;
        }

        public void setAnnexresource(AnnexresourceBean annexresourceBean) {
            this.annexresource = annexresourceBean;
        }

        public static class ArearesourceBean {
            private DataBean data;
            private String type;

            public String getType() {
                return this.type;
            }

            public void setType(String str) {
                this.type = str;
            }

            public DataBean getData() {
                return this.data;
            }

            public void setData(DataBean dataBean) {
                this.data = dataBean;
            }

            public static class DataBean {
                private String mdpath;
                private String path;

                public String getPath() {
                    return this.path;
                }

                public void setPath(String str) {
                    this.path = str;
                }

                public String getMdpath() {
                    return this.mdpath;
                }

                public void setMdpath(String str) {
                    this.mdpath = str;
                }
            }
        }

        public static class AnnexresourceBean {
            private DataBean data;
            private String type;

            public String getType() {
                return this.type;
            }

            public void setType(String str) {
                this.type = str;
            }

            public DataBean getData() {
                return this.data;
            }

            public void setData(DataBean dataBean) {
                this.data = dataBean;
            }

            public static class DataBean {
                private String mdpath;
                private String path;

                public String getPath() {
                    return this.path;
                }

                public void setPath(String str) {
                    this.path = str;
                }

                public String getMdpath() {
                    return this.mdpath;
                }

                public void setMdpath(String str) {
                    this.mdpath = str;
                }
            }
        }

        public static class PlaceBean {
            private float angle;
            private String name;
            private int type;
            private float x;
            private float y;

            public float getAngle() {
                return this.angle;
            }

            public void setAngle(float f) {
                this.angle = f;
            }

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
        }
    }
}
