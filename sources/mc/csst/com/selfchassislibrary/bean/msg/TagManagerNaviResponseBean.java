package mc.csst.com.selfchassislibrary.bean.msg;

import java.util.List;

public class TagManagerNaviResponseBean {
    private String id;
    private String op;
    private Boolean result;
    private String service;
    private ValuesBean values;

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public ValuesBean getValues() {
        return this.values;
    }

    public void setValues(ValuesBean valuesBean) {
        this.values = valuesBean;
    }

    public Boolean isResult() {
        return this.result;
    }

    public void setResult(Boolean bool) {
        this.result = bool;
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

    public static class ValuesBean {
        private List<String> avaliable_list;
        private Boolean success;

        public Boolean isSuccess() {
            return this.success;
        }

        public void setSuccess(Boolean bool) {
            this.success = bool;
        }

        public List<String> getAvaliable_list() {
            return this.avaliable_list;
        }

        public void setAvaliable_list(List<String> list) {
            this.avaliable_list = list;
        }
    }
}
