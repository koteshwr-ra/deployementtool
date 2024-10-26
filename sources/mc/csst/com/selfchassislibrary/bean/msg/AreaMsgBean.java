package mc.csst.com.selfchassislibrary.bean.msg;

import java.util.List;

public class AreaMsgBean {
    private List<AreaItemBean> areas;
    private HeaderBean header;

    public HeaderBean getHeader() {
        return this.header;
    }

    public void setHeader(HeaderBean headerBean) {
        this.header = headerBean;
    }

    public List<AreaItemBean> getAreas() {
        return this.areas;
    }

    public void setAreas(List<AreaItemBean> list) {
        this.areas = list;
    }

    public static class HeaderBean {
        private String frame_id;
        private Integer seq;
        private StampBean stamp;

        public StampBean getStamp() {
            return this.stamp;
        }

        public void setStamp(StampBean stampBean) {
            this.stamp = stampBean;
        }

        public String getFrame_id() {
            return this.frame_id;
        }

        public void setFrame_id(String str) {
            this.frame_id = str;
        }

        public Integer getSeq() {
            return this.seq;
        }

        public void setSeq(Integer num) {
            this.seq = num;
        }

        public static class StampBean {
            private Integer nsecs;
            private Integer secs;

            public Integer getSecs() {
                return this.secs;
            }

            public void setSecs(Integer num) {
                this.secs = num;
            }

            public Integer getNsecs() {
                return this.nsecs;
            }

            public void setNsecs(Integer num) {
                this.nsecs = num;
            }
        }
    }
}
