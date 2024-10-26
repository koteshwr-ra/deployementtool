package mc.csst.com.selfchassislibrary.bean.msg;

public class InsertCurrentPoseMarkerPublishBean {
    private String id;
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

    public static class MsgBean {
        private int behavior_code;
        private String name;
        private int rest_time = 0;
        private int time_out = -1;

        public int getTime_out() {
            return this.time_out;
        }

        public void setTime_out(int i) {
            this.time_out = i;
        }

        public int getBehavior_code() {
            return this.behavior_code;
        }

        public void setBehavior_code(int i) {
            this.behavior_code = i;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public int getRest_time() {
            return this.rest_time;
        }

        public void setRest_time(int i) {
            this.rest_time = i;
        }
    }
}
