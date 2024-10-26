package mc.csst.com.selfchassislibrary.bean.msg;

public class SendRecoverPathRequestBean {
    private ArgsBean args;
    private String id;
    private String op;
    private String service;

    public String getOp() {
        return this.op;
    }

    public void setOp(String str) {
        this.op = str;
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

    public ArgsBean getArgs() {
        return this.args;
    }

    public void setArgs(ArgsBean argsBean) {
        this.args = argsBean;
    }

    public static class ArgsBean {
        private Integer op;

        public ArgsBean(Integer num) {
            this.op = num;
        }

        public Integer getOp() {
            return this.op;
        }

        public void setOp(Integer num) {
            this.op = num;
        }
    }
}
