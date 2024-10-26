package mc.csst.com.selfchassislibrary.bean.msg;

public class LiftControlConfigureResponseBean {
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

    public static class ValuesBean {
        private String info;
        private Integer result;

        public String getInfo() {
            return this.info;
        }

        public void setInfo(String str) {
            this.info = str;
        }

        public Integer getResult() {
            return this.result;
        }

        public void setResult(Integer num) {
            this.result = num;
        }

        public String toString() {
            return "ValuesBean{info='" + this.info + '\'' + ", result=" + this.result + '}';
        }
    }

    public String toString() {
        return "LiftControlConfigureResponseBean{info='" + this.info + '\'' + ", service='" + this.service + '\'' + ", values=" + this.values + ", result=" + this.result + ", id='" + this.id + '\'' + ", op='" + this.op + '\'' + '}';
    }
}
