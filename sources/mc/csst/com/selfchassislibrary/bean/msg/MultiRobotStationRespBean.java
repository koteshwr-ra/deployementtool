package mc.csst.com.selfchassislibrary.bean.msg;

public class MultiRobotStationRespBean {
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
        private String host;
        private String project_name;
        private Boolean switch_on;
        private Boolean wan_switch;

        public String getHost() {
            return this.host;
        }

        public void setHost(String str) {
            this.host = str;
        }

        public Boolean getSwitch_on() {
            return this.switch_on;
        }

        public void setSwitch_on(Boolean bool) {
            this.switch_on = bool;
        }

        public void setWan_switch(Boolean bool) {
            this.wan_switch = bool;
        }

        public Boolean getWan_switch() {
            return this.wan_switch;
        }

        public void setProject_name(String str) {
            this.project_name = str;
        }

        public String getProject_name() {
            return this.project_name;
        }
    }
}
