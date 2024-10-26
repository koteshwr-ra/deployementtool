package mc.csst.com.selfchassislibrary.bean.msg;

public class BreakPackageBean {
    private String data;
    private String id;
    private int num;
    private String op;
    private String topic;
    private int total;

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int i) {
        this.total = i;
    }

    public int getNum() {
        return this.num;
    }

    public void setNum(int i) {
        this.num = i;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String str) {
        this.data = str;
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

    public String getTopic() {
        return this.topic;
    }

    public void setTopic(String str) {
        this.topic = str;
    }

    public String toString() {
        return "BreakPackageBean{total=" + this.total + ", num=" + this.num + ", data='" + this.data + '\'' + ", id='" + this.id + '\'' + ", op='" + this.op + '\'' + ", topic='" + this.topic + '\'' + '}';
    }
}
