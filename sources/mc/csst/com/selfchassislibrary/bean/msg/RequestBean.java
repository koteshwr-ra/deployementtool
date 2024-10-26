package mc.csst.com.selfchassislibrary.bean.msg;

import java.util.List;

public class RequestBean {
    private ArgsBean args;
    private String compression;
    private Integer fragment_size;
    private String id;
    private String op;
    private String service;
    private Integer throttle_rate;
    private String topic;
    private String type;

    public String getCompression() {
        return this.compression;
    }

    public void setCompression(String str) {
        this.compression = str;
    }

    public String getTopic() {
        return this.topic;
    }

    public void setTopic(String str) {
        this.topic = str;
    }

    public Integer getFragment_size() {
        return this.fragment_size;
    }

    public void setFragment_size(Integer num) {
        this.fragment_size = num;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getOp() {
        return this.op;
    }

    public void setOp(String str) {
        this.op = str;
    }

    public String getService() {
        return this.service;
    }

    public void setService(String str) {
        this.service = str;
    }

    public Integer getThrottle_rate() {
        return this.throttle_rate;
    }

    public void setThrottle_rate(Integer num) {
        this.throttle_rate = num;
    }

    public static class ArgsBean {
        private String arg;
        private Integer args;
        private String building_name;
        private Integer cmd;
        private List<SensorFeaturesBean> features;
        private List<FloorBean> floor;
        private String floor_name;
        private String floor_num;
        private String host;
        private String map_name;
        private Integer op;
        private Boolean overwritten;
        private Integer patrol_mode;
        private Integer patrol_time;
        private String poi;
        private List<String> poi_patrol_list;
        private String project_name;
        private String spec_version;
        private String str;
        private Boolean switch_on;
        private String target_building;
        private String target_floor;
        private String uuid;
        private Boolean wan_switch;

        public void setHost(String str2) {
            this.host = str2;
        }

        public void setSwitch_on(Boolean bool) {
            this.switch_on = bool;
        }

        public void setWan_switch(Boolean bool) {
            this.wan_switch = bool;
        }

        public Boolean getWan_switch() {
            return this.wan_switch;
        }

        public void setProject_name(String str2) {
            this.project_name = str2;
        }

        public String getProject_name() {
            return this.project_name;
        }

        public String getTarget_floor() {
            return this.target_floor;
        }

        public void setTarget_floor(String str2) {
            this.target_floor = str2;
        }

        public String getTarget_building() {
            return this.target_building;
        }

        public void setTarget_building(String str2) {
            this.target_building = str2;
        }

        public String getArg() {
            return this.arg;
        }

        public void setArg(String str2) {
            this.arg = str2;
        }

        public String getMap_name() {
            return this.map_name;
        }

        public void setMap_name(String str2) {
            this.map_name = str2;
        }

        public String getSpec_version() {
            return this.spec_version;
        }

        public void setSpec_version(String str2) {
            this.spec_version = str2;
        }

        public String getFloor_num() {
            return this.floor_num;
        }

        public void setFloor_num(String str2) {
            this.floor_num = str2;
        }

        public String getFloor_name() {
            return this.floor_name;
        }

        public void setFloor_name(String str2) {
            this.floor_name = str2;
        }

        public Integer getCmd() {
            return this.cmd;
        }

        public void setCmd(Integer num) {
            this.cmd = num;
        }

        public Integer getArgs() {
            return this.args;
        }

        public void setArgs(Integer num) {
            this.args = num;
        }

        public String getBuilding_name() {
            return this.building_name;
        }

        public void setBuilding_name(String str2) {
            this.building_name = str2;
        }

        public Integer getOp() {
            return this.op;
        }

        public void setOp(Integer num) {
            this.op = num;
        }

        public String getPoi() {
            return this.poi;
        }

        public void setPoi(String str2) {
            this.poi = str2;
        }

        public String getUuid() {
            return this.uuid;
        }

        public void setUuid(String str2) {
            this.uuid = str2;
        }

        public Integer getPatrol_time() {
            return this.patrol_time;
        }

        public void setPatrol_time(Integer num) {
            this.patrol_time = num;
        }

        public Integer getPatrol_mode() {
            return this.patrol_mode;
        }

        public void setPatrol_mode(Integer num) {
            this.patrol_mode = num;
        }

        public List<String> getPoi_patrol_list() {
            return this.poi_patrol_list;
        }

        public void setPoi_patrol_list(List<String> list) {
            this.poi_patrol_list = list;
        }

        public String getStr() {
            return this.str;
        }

        public void setStr(String str2) {
            this.str = str2;
        }

        public List<FloorBean> getFloor() {
            return this.floor;
        }

        public void setFloor(List<FloorBean> list) {
            this.floor = list;
        }

        public boolean isOverwritten() {
            return this.overwritten.booleanValue();
        }

        public void setOverwritten(boolean z) {
            this.overwritten = Boolean.valueOf(z);
        }

        public List<SensorFeaturesBean> getFeatures() {
            return this.features;
        }

        public void setFeatures(List<SensorFeaturesBean> list) {
            this.features = list;
        }

        public static class FloorBean {
            private String annex;
            private String num;
            private String path;

            public String getAnnex() {
                return this.annex;
            }

            public void setAnnex(String str) {
                this.annex = str;
            }

            public String getPath() {
                return this.path;
            }

            public void setPath(String str) {
                this.path = str;
            }

            public String getNum() {
                return this.num;
            }

            public void setNum(String str) {
                this.num = str;
            }

            public String toString() {
                return "FloorBean{annex='" + this.annex + '\'' + ", num='" + this.num + '\'' + ", path='" + this.path + '\'' + '}';
            }
        }
    }

    public ArgsBean getArgs() {
        return this.args;
    }

    public void setArgs(ArgsBean argsBean) {
        this.args = argsBean;
    }

    public String toString() {
        return "RequestBean{compression='" + this.compression + '\'' + ", topic='" + this.topic + '\'' + ", service='" + this.service + '\'' + ", throttle_rate=" + this.throttle_rate + ", fragment_size=" + this.fragment_size + ", type='" + this.type + '\'' + ", id='" + this.id + '\'' + ", op='" + this.op + '\'' + ", args=" + this.args + '}';
    }
}
