package mc.csst.com.selfchassislibrary.bean.msg;

public class SendDeletePoiRequestBean {
    private ArgsBean args;
    private String id;
    private String op;
    private String service;

    public ArgsBean getArgs() {
        return this.args;
    }

    public void setArgs(ArgsBean argsBean) {
        this.args = argsBean;
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

    public static class ArgsBean {
        private String poi;

        public String getPoi() {
            return this.poi;
        }

        public void setPoi(String str) {
            this.poi = str;
        }
    }
}
