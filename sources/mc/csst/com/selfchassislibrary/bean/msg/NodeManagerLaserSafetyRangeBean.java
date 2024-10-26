package mc.csst.com.selfchassislibrary.bean.msg;

public class NodeManagerLaserSafetyRangeBean {
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
        private float data;

        public float getData() {
            return this.data;
        }

        public void setData(float f) {
            this.data = f;
        }

        public String toString() {
            return "MsgBean{data=" + this.data + '}';
        }
    }

    public String toString() {
        return "NodeManagerLaserSafetyRangeBean{topic='" + this.topic + '\'' + ", msg=" + this.msg + ", id='" + this.id + '\'' + ", op='" + this.op + '\'' + '}';
    }
}
