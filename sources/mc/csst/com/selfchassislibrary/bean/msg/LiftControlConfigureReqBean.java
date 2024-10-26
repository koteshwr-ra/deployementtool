package mc.csst.com.selfchassislibrary.bean.msg;

public class LiftControlConfigureReqBean {
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
        private String APPID;
        private String APPSecret;
        private String RobotID;
        private Integer cmd;
        private String projectID;

        public Integer getCmd() {
            return this.cmd;
        }

        public void setCmd(Integer num) {
            this.cmd = num;
        }

        public String getProjectID() {
            return this.projectID;
        }

        public void setProjectID(String str) {
            this.projectID = str;
        }

        public String getAPPID() {
            return this.APPID;
        }

        public void setAPPID(String str) {
            this.APPID = str;
        }

        public String getAPPSecret() {
            return this.APPSecret;
        }

        public void setAPPSecret(String str) {
            this.APPSecret = str;
        }

        public String getRobotID() {
            return this.RobotID;
        }

        public void setRobotID(String str) {
            this.RobotID = str;
        }
    }
}
