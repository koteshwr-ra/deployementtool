package mc.csst.com.selfchassislibrary.bean.msg;

import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;
import java.util.List;

public class UltrasonicDistanceReqBean implements Serializable {
    @JSONField(name = "args")
    private ArgsDTO args;
    @JSONField(name = "id")
    private String id;
    @JSONField(name = "op")
    private String op;
    @JSONField(name = "service")
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
        @JSONField(name = "cmd")
        private Integer cmd;
        @JSONField(name = "features")
        private List<UltrasonicDistanceBean> features;

        public Integer getCmd() {
            return this.cmd;
        }

        public void setCmd(Integer num) {
            this.cmd = num;
        }

        public List<UltrasonicDistanceBean> getFeatures() {
            return this.features;
        }

        public void setFeatures(List<UltrasonicDistanceBean> list) {
            this.features = list;
        }
    }
}
