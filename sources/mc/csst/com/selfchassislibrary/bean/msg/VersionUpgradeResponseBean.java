package mc.csst.com.selfchassislibrary.bean.msg;

public class VersionUpgradeResponseBean {
    private String id;
    private String info;
    private String op;
    private boolean result;
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

    public boolean isResult() {
        return this.result;
    }

    public void setResult(boolean z) {
        this.result = z;
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
        private String current_version;
        private String desp;
        private String latest_version;
        private int result;

        public String getCurrent_version() {
            return this.current_version;
        }

        public void setCurrent_version(String str) {
            this.current_version = str;
        }

        public String getDesp() {
            return this.desp;
        }

        public void setDesp(String str) {
            this.desp = str;
        }

        public int getResult() {
            return this.result;
        }

        public void setResult(int i) {
            this.result = i;
        }

        public String getLatest_version() {
            return this.latest_version;
        }

        public void setLatest_version(String str) {
            this.latest_version = str;
        }

        public String toString() {
            return "ValuesBean{current_version='" + this.current_version + '\'' + ", desp='" + this.desp + '\'' + ", result=" + this.result + ", latest_version='" + this.latest_version + '\'' + '}';
        }
    }

    public String toString() {
        return "VersionUpgradeResponseBean{info='" + this.info + '\'' + ", service='" + this.service + '\'' + ", values=" + this.values + ", result=" + this.result + ", id='" + this.id + '\'' + ", op='" + this.op + '\'' + '}';
    }
}
