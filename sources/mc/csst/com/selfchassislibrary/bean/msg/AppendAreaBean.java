package mc.csst.com.selfchassislibrary.bean.msg;

public class AppendAreaBean {
    private String id;
    private AreaItemBean msg;
    private String op;
    private String topic;

    public String getTopic() {
        return this.topic;
    }

    public void setTopic(String str) {
        this.topic = str;
    }

    public AreaItemBean getMsg() {
        return this.msg;
    }

    public void setMsg(AreaItemBean areaItemBean) {
        this.msg = areaItemBean;
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
