package mc.csst.com.selfchassislibrary.bean.response;

import java.util.List;

public class CurrentRecordPathRespBean {
    private MsgBean msg;
    private String op;
    private String topic;

    public String getTopic() {
        return this.topic;
    }

    public void setTopic(String str) {
        this.topic = str;
    }

    public MsgBean getMsg() {
        return this.msg;
    }

    public void setMsg(MsgBean msgBean) {
        this.msg = msgBean;
    }

    public String getOp() {
        return this.op;
    }

    public void setOp(String str) {
        this.op = str;
    }

    public static class MsgBean {
        private String goal_node_name;
        private Float pass_capacity;
        private String path_name;
        private List<Float> path_t;
        private Float path_width;
        private List<Float> path_x;
        private List<Float> path_y;
        private String start_node_name;
        private Double velocity;

        public String getPath_name() {
            return this.path_name;
        }

        public void setPath_name(String str) {
            this.path_name = str;
        }

        public List<Float> getPath_x() {
            return this.path_x;
        }

        public Float getPath_width() {
            return this.path_width;
        }

        public List<Float> getPath_y() {
            return this.path_y;
        }

        public List<Float> getPath_t() {
            return this.path_t;
        }

        public Double getVelocity() {
            return this.velocity;
        }

        public void setVelocity(Double d) {
            this.velocity = d;
        }

        public String getGoal_node_name() {
            return this.goal_node_name;
        }

        public void setGoal_node_name(String str) {
            this.goal_node_name = str;
        }

        public Float getPass_capacity() {
            return this.pass_capacity;
        }

        public String getStart_node_name() {
            return this.start_node_name;
        }

        public void setStart_node_name(String str) {
            this.start_node_name = str;
        }
    }
}
