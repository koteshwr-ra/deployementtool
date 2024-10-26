package mc.csst.com.selfchassislibrary.bean.msg;

public class NaviStatusPublishBean {
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
        private GoalIdBean goal_id;
        private int status;
        private String text;

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int i) {
            this.status = i;
        }

        public String getText() {
            return this.text;
        }

        public void setText(String str) {
            this.text = str;
        }

        public GoalIdBean getGoal_id() {
            return this.goal_id;
        }

        public void setGoal_id(GoalIdBean goalIdBean) {
            this.goal_id = goalIdBean;
        }

        public static class GoalIdBean {
            private String id;
            private StampBean stamp;

            public StampBean getStamp() {
                return this.stamp;
            }

            public void setStamp(StampBean stampBean) {
                this.stamp = stampBean;
            }

            public String getId() {
                return this.id;
            }

            public void setId(String str) {
                this.id = str;
            }

            public static class StampBean {
                private int nsecs;
                private int secs;

                public int getSecs() {
                    return this.secs;
                }

                public void setSecs(int i) {
                    this.secs = i;
                }

                public int getNsecs() {
                    return this.nsecs;
                }

                public void setNsecs(int i) {
                    this.nsecs = i;
                }
            }
        }
    }

    public String toString() {
        return "NaviStatusPublishBean{topic='" + this.topic + '\'' + ", msg=" + this.msg + ", op='" + this.op + '\'' + '}';
    }
}
