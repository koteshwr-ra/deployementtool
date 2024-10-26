package mc.csst.com.selfchassislibrary.bean.msg;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class NaviSettingReqBean implements Serializable {
    @SerializedName("args")
    private ArgsDTO args;
    @SerializedName("id")
    private String id;
    @SerializedName("op")
    private String op;
    @SerializedName("service")
    private String service;

    public ArgsDTO getArgs() {
        return this.args;
    }

    public void setArgs(ArgsDTO argsDTO) {
        this.args = argsDTO;
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

    public static class ArgsDTO implements Serializable {
        @SerializedName("cmd")
        private Integer cmd;
        @SerializedName("features")
        private List<NaviSettingBean> features;

        public Integer getCmd() {
            return this.cmd;
        }

        public void setCmd(Integer num) {
            this.cmd = num;
        }

        public List<NaviSettingBean> getFeatures() {
            return this.features;
        }

        public void setFeatures(List<NaviSettingBean> list) {
            this.features = list;
        }

        public String toString() {
            return "ArgsDTO{cmd=" + this.cmd + ", features=" + this.features + '}';
        }
    }
}
