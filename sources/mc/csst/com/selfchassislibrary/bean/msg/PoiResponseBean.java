package mc.csst.com.selfchassislibrary.bean.msg;

import java.util.List;

public class PoiResponseBean {
    private String id;
    private String op;
    private boolean result;
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

    public boolean isResult() {
        return this.result;
    }

    public void setResult(boolean z) {
        this.result = z;
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
        private boolean success;

        public boolean isSuccess() {
            return this.success;
        }

        public void setSuccess(boolean z) {
            this.success = z;
        }

        public List<String> getAvaliable_list() {
            return this.avaliable_list;
        }

        public void setAvaliable_list(List<String> list) {
            this.avaliable_list = list;
        }
    }
}
