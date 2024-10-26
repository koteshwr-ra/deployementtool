package mc.csst.com.selfchassislibrary.bean.msg;

import java.util.List;

public class SetWayPointBean {
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
        private List<WaypointsBean> waypoints;

        public List<WaypointsBean> getWaypoints() {
            return this.waypoints;
        }

        public void setWaypoints(List<WaypointsBean> list) {
            this.waypoints = list;
        }

        public static class WaypointsBean {
            private int behavior_code;
            private String name;
            private PoseBean pose;
            private int rest_time = 0;
            private int time_out = -1;

            public int getBehavior_code() {
                return this.behavior_code;
            }

            public void setBehavior_code(int i) {
                this.behavior_code = i;
            }

            public int getTime_out() {
                return this.time_out;
            }

            public void setTime_out(int i) {
                this.time_out = i;
            }

            public PoseBean getPose() {
                return this.pose;
            }

            public void setPose(PoseBean poseBean) {
                this.pose = poseBean;
            }

            public String getName() {
                return this.name;
            }

            public void setName(String str) {
                this.name = str;
            }

            public int getRest_time() {
                return this.rest_time;
            }

            public void setRest_time(int i) {
                this.rest_time = i;
            }

            public static class PoseBean {
                private OrientationBean orientation;
                private PositionBean position;

                public PositionBean getPosition() {
                    return this.position;
                }

                public void setPosition(PositionBean positionBean) {
                    this.position = positionBean;
                }

                public OrientationBean getOrientation() {
                    return this.orientation;
                }

                public void setOrientation(OrientationBean orientationBean) {
                    this.orientation = orientationBean;
                }

                public static class PositionBean {
                    private float x;
                    private float y;
                    private float z;

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

                    public float getZ() {
                        return this.z;
                    }

                    public void setZ(float f) {
                        this.z = f;
                    }
                }

                public static class OrientationBean {
                    private float w;
                    private float x;
                    private float y;
                    private float z;

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

                    public float getZ() {
                        return this.z;
                    }

                    public void setZ(float f) {
                        this.z = f;
                    }

                    public float getW() {
                        return this.w;
                    }

                    public void setW(float f) {
                        this.w = f;
                    }
                }
            }
        }
    }
}
