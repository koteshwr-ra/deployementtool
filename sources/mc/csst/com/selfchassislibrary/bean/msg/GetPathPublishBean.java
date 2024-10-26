package mc.csst.com.selfchassislibrary.bean.msg;

import java.util.List;

public class GetPathPublishBean {
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
        private List<Float> pt;
        private List<Float> px;
        private List<Float> py;

        public List<Float> getPx() {
            return this.px;
        }

        public void setPx(List<Float> list) {
            this.px = list;
        }

        public List<Float> getPy() {
            return this.py;
        }

        public void setPy(List<Float> list) {
            this.py = list;
        }

        public List<Float> getPt() {
            return this.pt;
        }

        public void setPt(List<Float> list) {
            this.pt = list;
        }

        public String toString() {
            return "MsgBean{px=" + this.px + ", py=" + this.py + ", pt=" + this.pt + '}';
        }
    }

    public String toString() {
        return "GetPathPublishBean{topic='" + this.topic + '\'' + ", msg=" + this.msg + ", op='" + this.op + '\'' + '}';
    }
}
