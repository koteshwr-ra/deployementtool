package mc.csst.com.selfchassislibrary.bean.msg;

import java.util.List;

public class SetLayeredMapManagerPencilOpPublishBean {
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
        private List<PointInfoBean> point_info;

        public List<PointInfoBean> getPoint_info() {
            return this.point_info;
        }

        public void setPoint_info(List<PointInfoBean> list) {
            this.point_info = list;
        }

        public static class PointInfoBean {
            private int op_color;
            private int op_size;
            private float px;
            private float py;

            public float getPx() {
                return this.px;
            }

            public void setPx(float f) {
                this.px = f;
            }

            public float getPy() {
                return this.py;
            }

            public void setPy(float f) {
                this.py = f;
            }

            public int getOp_size() {
                return this.op_size;
            }

            public void setOp_size(int i) {
                this.op_size = i;
            }

            public int getOp_color() {
                return this.op_color;
            }

            public void setOp_color(int i) {
                this.op_color = i;
            }
        }
    }
}
