package mc.csst.com.selfchassislibrary.bean.msg;

import java.util.List;

public class SetVirtualWallsBean {
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
        private HeaderBean header;
        private List<WallsBean> walls;

        public HeaderBean getHeader() {
            return this.header;
        }

        public void setHeader(HeaderBean headerBean) {
            this.header = headerBean;
        }

        public List<WallsBean> getWalls() {
            return this.walls;
        }

        public void setWalls(List<WallsBean> list) {
            this.walls = list;
        }

        public static class HeaderBean {
            private String frame_id;

            public String getFrame_id() {
                return this.frame_id;
            }

            public void setFrame_id(String str) {
                this.frame_id = str;
            }
        }

        public static class WallsBean {
            private float end_x;
            private float end_y;
            private float start_x;
            private float start_y;
            private String wall_id;

            public float getEnd_x() {
                return this.end_x;
            }

            public void setEnd_x(float f) {
                this.end_x = f;
            }

            public String getWall_id() {
                return this.wall_id;
            }

            public void setWall_id(String str) {
                this.wall_id = str;
            }

            public float getEnd_y() {
                return this.end_y;
            }

            public void setEnd_y(float f) {
                this.end_y = f;
            }

            public float getStart_x() {
                return this.start_x;
            }

            public void setStart_x(float f) {
                this.start_x = f;
            }

            public float getStart_y() {
                return this.start_y;
            }

            public void setStart_y(float f) {
                this.start_y = f;
            }

            public String toString() {
                return "WallsBean{end_x=" + this.end_x + ", wall_id='" + this.wall_id + '\'' + ", end_y=" + this.end_y + ", start_x=" + this.start_x + ", start_y=" + this.start_y + '}';
            }
        }
    }
}
