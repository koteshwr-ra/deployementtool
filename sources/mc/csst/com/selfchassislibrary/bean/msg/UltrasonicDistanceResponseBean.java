package mc.csst.com.selfchassislibrary.bean.msg;

import java.io.Serializable;
import java.util.List;

public class UltrasonicDistanceResponseBean implements Serializable {
    private String id;
    private String info;
    private String op;
    private Boolean result;
    private String service;
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

    public Boolean getResult() {
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

    public static class ValuesDTO {
        private List<UltrasonicDistanceBean> features_resp;
        private String info;
        private int result;

        public String getInfo() {
            return this.info;
        }

        public void setInfo(String str) {
            this.info = str;
        }

        public List<UltrasonicDistanceBean> getFeatures_resp() {
            return this.features_resp;
        }

        public void setFeatures_resp(List<UltrasonicDistanceBean> list) {
            this.features_resp = list;
        }

        public int getResult() {
            return this.result;
        }

        public void setResult(int i) {
            this.result = i;
        }
    }
}
