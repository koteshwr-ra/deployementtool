package mc.csst.com.selfchassislibrary.bean.msg;

public class LiftControlForceCancelReqBean {
    private String id;
    private MsgBean msg;
    private String op;
    private String topic;

    public String getOp() {
        return this.op;
    }

    public void setOp(String str) {
        this.op = str;
    }

    public String getTopic() {
        return this.topic;
    }

    public void setTopic(String str) {
        this.topic = str;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public MsgBean getMsg() {
        return this.msg;
    }

    public void setMsg(MsgBean msgBean) {
        this.msg = msgBean;
    }

    public static class MsgBean {
        private String id;
        private String stamp;

        public String getStamp() {
            return this.stamp;
        }

        public void setStamp(String str) {
            this.stamp = str;
        }

        public String getId() {
            return this.id;
        }

        public void setId(String str) {
            this.id = str;
        }
    }
}
