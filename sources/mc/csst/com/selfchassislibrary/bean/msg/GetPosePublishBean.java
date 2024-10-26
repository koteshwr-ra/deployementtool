package mc.csst.com.selfchassislibrary.bean.msg;

public class GetPosePublishBean {
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
        private float theta;
        private float x;
        private float y;

        public float getY() {
            return this.y;
        }

        public void setY(float f) {
            this.y = f;
        }

        public float getX() {
            return this.x;
        }

        public void setX(float f) {
            this.x = f;
        }

        public float getTheta() {
            return this.theta;
        }

        public void setTheta(float f) {
            this.theta = f;
        }

        public String toString() {
            return "MsgBean{y=" + this.y + ", x=" + this.x + ", theta=" + this.theta + '}';
        }
    }

    public String toString() {
        return "GetPosePublishBean{topic='" + this.topic + '\'' + ", msg=" + this.msg + ", op='" + this.op + '\'' + '}';
    }
}
