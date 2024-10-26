package mc.csst.com.selfchassislibrary.bean.msg;

import java.util.List;

public class NotificationBean {
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
        private String file;
        private String function;
        private HeaderBean header;
        private int level;
        private int line;
        private String msg;
        private String name;
        private List<String> topics;

        public String getFunction() {
            return this.function;
        }

        public void setFunction(String str) {
            this.function = str;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public int getLevel() {
            return this.level;
        }

        public void setLevel(int i) {
            this.level = i;
        }

        public List<String> getTopics() {
            return this.topics;
        }

        public void setTopics(List<String> list) {
            this.topics = list;
        }

        public HeaderBean getHeader() {
            return this.header;
        }

        public void setHeader(HeaderBean headerBean) {
            this.header = headerBean;
        }

        public String getFile() {
            return this.file;
        }

        public void setFile(String str) {
            this.file = str;
        }

        public String getMsg() {
            return this.msg;
        }

        public void setMsg(String str) {
            this.msg = str;
        }

        public int getLine() {
            return this.line;
        }

        public void setLine(int i) {
            this.line = i;
        }

        public static class HeaderBean {
            private String frame_id;
            private int seq;
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

            public int getSeq() {
                return this.seq;
            }

            public void setSeq(int i) {
                this.seq = i;
            }

            public static class StampBean {
                private long nsecs;
                private long secs;

                public long getSecs() {
                    return this.secs;
                }

                public void setSecs(long j) {
                    this.secs = j;
                }

                public long getNsecs() {
                    return this.nsecs;
                }

                public void setNsecs(long j) {
                    this.nsecs = j;
                }
            }
        }
    }
}
