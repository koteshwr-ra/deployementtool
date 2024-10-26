package mc.csst.com.selfchassislibrary.bean.msg;

import java.util.List;
import java.util.Objects;

public class RobotListRespBean {
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
        private List<ListBean> list;

        public MsgBean(List<ListBean> list2) {
            this.list = list2;
        }

        public List<ListBean> getList() {
            return this.list;
        }

        public void setList(List<ListBean> list2) {
            this.list = list2;
        }

        public static class ListBean {
            private PoseBean pose;
            private String robot_id;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                return Objects.equals(getRobot_id(), ((ListBean) obj).getRobot_id());
            }

            public int hashCode() {
                return Objects.hash(new Object[]{getRobot_id(), getPose()});
            }

            public String getRobot_id() {
                return this.robot_id;
            }

            public void setRobot_id(String str) {
                this.robot_id = str;
            }

            public PoseBean getPose() {
                return this.pose;
            }

            public void setPose(PoseBean poseBean) {
                this.pose = poseBean;
            }

            public static class PoseBean {
                private float theta;
                private float x;
                private float y;

                public PoseBean(float f, float f2, float f3) {
                    this.x = f;
                    this.y = f2;
                    this.theta = f3;
                }

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    PoseBean poseBean = (PoseBean) obj;
                    if (Float.compare(poseBean.getX(), getX()) == 0 && Float.compare(poseBean.getY(), getY()) == 0 && Float.compare(poseBean.getTheta(), getTheta()) == 0) {
                        return true;
                    }
                    return false;
                }

                public int hashCode() {
                    return Objects.hash(new Object[]{Float.valueOf(getX()), Float.valueOf(getY()), Float.valueOf(getTheta())});
                }

                public float getX() {
                    return this.x;
                }

                public void setX(float f) {
                    this.x = f;
                }

                public float getY() {
                    return this.y;
                }

                public void setY(float f) {
                    this.y = f;
                }

                public float getTheta() {
                    return this.theta;
                }

                public void setTheta(float f) {
                    this.theta = f;
                }
            }
        }
    }
}
