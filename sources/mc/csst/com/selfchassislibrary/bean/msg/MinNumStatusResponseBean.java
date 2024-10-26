package mc.csst.com.selfchassislibrary.bean.msg;

import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;

public class MinNumStatusResponseBean implements Serializable {
    @JSONField(name = "id")
    private String id;
    @JSONField(name = "info")
    private String info;
    @JSONField(name = "op")
    private String op;
    @JSONField(name = "result")
    private Boolean result;
    @JSONField(name = "service")
    private String service;
    @JSONField(name = "values")
    private ValuesDTO values;

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String str) {
        this.info = str;
    }

    public String getService() {
        return this.service;
    }

    public void setService(String str) {
        this.service = str;
    }

    public ValuesDTO getValues() {
        return this.values;
    }

    public void setValues(ValuesDTO valuesDTO) {
        this.values = valuesDTO;
    }

    public Boolean isResult() {
        return this.result;
    }

    public void setResult(Boolean bool) {
        this.result = bool;
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

    public static class ValuesDTO implements Serializable {
        @JSONField(name = "status")
        private Boolean status;
        @JSONField(name = "status_info")
        private String statusInfo;

        public String getStatusInfo() {
            return this.statusInfo;
        }

        public void setStatusInfo(String str) {
            this.statusInfo = str;
        }

        public Boolean isStatus() {
            return this.status;
        }

        public void setStatus(Boolean bool) {
            this.status = bool;
        }
    }
}
