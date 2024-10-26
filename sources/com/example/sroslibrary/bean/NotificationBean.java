package com.example.sroslibrary.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class NotificationBean implements Serializable {
    public static final int FUNCTIONAL_INFORMATION_SYNCHRONIZATION = 2;
    public static final int ISSUE_TEMPORARY_TASK = 3;
    public static final int VERSION_UPDATE = 1;
    private static final long serialVersionUID = -8599019136991526979L;
    @SerializedName("desc")
    @Expose
    private DescBean desc;
    @SerializedName("nowtime")
    @Expose
    private String nowtime;
    @SerializedName("robotid")
    @Expose
    private String robotid;
    @SerializedName("type")
    @Expose
    private int type;
    @SerializedName("url")
    @Expose
    private String url;

    public String getRobotid() {
        return this.robotid;
    }

    public void setRobotid(String str) {
        this.robotid = str;
    }

    public String getNowtime() {
        return this.nowtime;
    }

    public void setNowtime(String str) {
        this.nowtime = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public DescBean getDesc() {
        return this.desc;
    }

    public void setDesc(DescBean descBean) {
        this.desc = descBean;
    }

    public static class DescBean {
        @SerializedName("data")
        @Expose
        private Object data;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("type")
        @Expose
        private String type;

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getType() {
            return this.type;
        }

        public void setType(String str) {
            this.type = str;
        }

        public Object getData() {
            return this.data;
        }

        public void setData(DataBean dataBean) {
            this.data = dataBean;
        }

        public static class MapDataBean {
            @SerializedName("maplists")
            private List<MaplistsBean> maplists;

            public List<MaplistsBean> getMaplists() {
                return this.maplists;
            }

            public void setMaplists(List<MaplistsBean> list) {
                this.maplists = list;
            }

            public String toString() {
                return "MapDataBean{maplists=" + this.maplists + '}';
            }

            public static class MaplistsBean {
                private String createTime;
                private String head;
                @SerializedName("id")
                private int idX;
                private String mapName;
                private String placeId;
                private int status;
                private String updateTime;

                public String getHead() {
                    return this.head;
                }

                public void setHead(String str) {
                    this.head = str;
                }

                public String getCreateTime() {
                    return this.createTime;
                }

                public void setCreateTime(String str) {
                    this.createTime = str;
                }

                public String getPlaceId() {
                    return this.placeId;
                }

                public void setPlaceId(String str) {
                    this.placeId = str;
                }

                public String getUpdateTime() {
                    return this.updateTime;
                }

                public void setUpdateTime(String str) {
                    this.updateTime = str;
                }

                public int getIdX() {
                    return this.idX;
                }

                public void setIdX(int i) {
                    this.idX = i;
                }

                public String getMapName() {
                    return this.mapName;
                }

                public void setMapName(String str) {
                    this.mapName = str;
                }

                public int getStatus() {
                    return this.status;
                }

                public void setStatus(int i) {
                    this.status = i;
                }

                public String toString() {
                    return "MaplistsBean{head='" + this.head + '\'' + ", createTime='" + this.createTime + '\'' + ", placeId='" + this.placeId + '\'' + ", updateTime='" + this.updateTime + '\'' + ", idX=" + this.idX + ", mapName='" + this.mapName + '\'' + ", status=" + this.status + '}';
                }
            }
        }

        public static class DataBean {
            public String begin;
            public String code;
            public String description;
            public String end;
            @SerializedName("id")
            @Expose
            private String id;

            public String getId() {
                return this.id;
            }

            public void setId(String str) {
                this.id = str;
            }

            public String toString() {
                return "DataBean{id='" + this.id + '\'' + ", code='" + this.code + '\'' + ", begin='" + this.begin + '\'' + ", end='" + this.end + '\'' + ", description='" + this.description + '\'' + '}';
            }
        }

        public String toString() {
            return "DescBean{name='" + this.name + '\'' + ", type='" + this.type + '\'' + ", data=" + this.data + '}';
        }
    }

    public String toString() {
        return "NotificationBean{robotid='" + this.robotid + '\'' + ", nowtime='" + this.nowtime + '\'' + ", type=" + this.type + ", url='" + this.url + '\'' + ", desc=" + this.desc + '}';
    }
}
