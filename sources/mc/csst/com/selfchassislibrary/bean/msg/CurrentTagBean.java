package mc.csst.com.selfchassislibrary.bean.msg;

public class CurrentTagBean {
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
        private String data;

        public String getData() {
            return this.data;
        }

        public void setData(String str) {
            this.data = str;
        }
    }
}
