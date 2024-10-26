package mc.csst.com.selfchassislibrary.bean.msg;

import java.util.List;

public class SensorResponseBean {
    private String id;
    private String info;
    private String op;
    private Boolean result;
    private String service;
    private ValuesBean values;

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

    public ValuesBean getValues() {
        return this.values;
    }

    public void setValues(ValuesBean valuesBean) {
        this.values = valuesBean;
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

    public static class ValuesBean {
        private List<SensorFeaturesBean> features_resp;
        private String info;
        private Integer result;

        public String getInfo() {
            return this.info;
        }

        public void setInfo(String str) {
            this.info = str;
        }

        public List<SensorFeaturesBean> getFeatures_resp() {
            return this.features_resp;
        }

        public void setFeatures_resp(List<SensorFeaturesBean> list) {
            this.features_resp = list;
        }

        public Integer getResult() {
            return this.result;
        }

        public void setResult(Integer num) {
            this.result = num;
        }
    }
}
