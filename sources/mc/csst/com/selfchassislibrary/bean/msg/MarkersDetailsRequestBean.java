package mc.csst.com.selfchassislibrary.bean.msg;

import com.google.gson.annotations.SerializedName;

public class MarkersDetailsRequestBean {
    @SerializedName("args")
    private ArgsBean args;
    @SerializedName("id")
    private String id;
    @SerializedName("op")
    private String op;
    @SerializedName("service")
    private String service;

    public ArgsBean getArgs() {
        return this.args;
    }

    public void setArgs(ArgsBean argsBean) {
        this.args = argsBean;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getService() {
        return this.service;
    }

    public void setService(String str) {
        this.service = str;
    }

    public String getOp() {
        return this.op;
    }

    public void setOp(String str) {
        this.op = str;
    }

    public static class ArgsBean {
        @SerializedName("building_name")
        private String buildingName;
        @SerializedName("floor_name")
        private String floorName;
        @SerializedName("type")
        private Integer type;

        public String getFloorName() {
            return this.floorName;
        }

        public void setFloorName(String str) {
            this.floorName = str;
        }

        public Integer getType() {
            return this.type;
        }

        public void setType(Integer num) {
            this.type = num;
        }

        public String getBuildingName() {
            return this.buildingName;
        }

        public void setBuildingName(String str) {
            this.buildingName = str;
        }
    }
}
