package mc.csst.com.selfchassislibrary.bean.msg;

import java.util.List;

public class SendPathSaveRequestBean {
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
        private int cmd;
        private String goal_node_name;
        private Float pass_capacity;
        private String path_name;
        private List<Float> path_t;
        private Float path_width;
        private List<Float> path_x;
        private List<Float> path_y;
        private String start_node_name;
        private Float velocity;

        public void setCmd(int i) {
            this.cmd = i;
        }
    }
}
