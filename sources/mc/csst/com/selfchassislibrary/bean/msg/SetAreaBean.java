package mc.csst.com.selfchassislibrary.bean.msg;

public class SetAreaBean {
    private String id;
    private AreaMsgBean msg;
    private String op;
    private String topic;

    public String getTopic() {
        return this.topic;
    }

    public void setTopic(String str) {
        this.topic = str;
    }

    public AreaMsgBean getMsg() {
        return this.msg;
    }

    public void setMsg(AreaMsgBean areaMsgBean) {
        this.msg = areaMsgBean;
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
}
