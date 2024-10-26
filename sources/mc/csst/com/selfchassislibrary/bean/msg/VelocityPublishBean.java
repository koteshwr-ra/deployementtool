package mc.csst.com.selfchassislibrary.bean.msg;

public class VelocityPublishBean {
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
        private AngularBean angular;
        private LinearBean linear;

        public AngularBean getAngular() {
            return this.angular;
        }

        public void setAngular(AngularBean angularBean) {
            this.angular = angularBean;
        }

        public LinearBean getLinear() {
            return this.linear;
        }

        public void setLinear(LinearBean linearBean) {
            this.linear = linearBean;
        }

        public static class AngularBean {
            private float z;

            public float getZ() {
                return this.z;
            }

            public void setZ(float f) {
                this.z = f;
            }

            public String toString() {
                return "AngularBean{z=" + this.z + '}';
            }
        }

        public static class LinearBean {
            private float x;

            public float getX() {
                return this.x;
            }

            public void setX(float f) {
                this.x = f;
            }

            public String toString() {
                return "LinearBean{x=" + this.x + '}';
            }
        }

        public String toString() {
            return "MsgBean{angular=" + this.angular + ", linear=" + this.linear + '}';
        }
    }

    public String toString() {
        return "VelocityPublishBean{op='" + this.op + '\'' + ", topic='" + this.topic + '\'' + ", id='" + this.id + '\'' + ", msg=" + this.msg + '}';
    }
}
