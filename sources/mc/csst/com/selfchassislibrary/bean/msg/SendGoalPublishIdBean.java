package mc.csst.com.selfchassislibrary.bean.msg;

public class SendGoalPublishIdBean {
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
        private GoalBean goal;
        private GoalIdBean goal_id;
        private HeaderBean header;

        public HeaderBean getHeader() {
            return this.header;
        }

        public void setHeader(HeaderBean headerBean) {
            this.header = headerBean;
        }

        public GoalIdBean getGoal_id() {
            return this.goal_id;
        }

        public void setGoal_id(GoalIdBean goalIdBean) {
            this.goal_id = goalIdBean;
        }

        public GoalBean getGoal() {
            return this.goal;
        }

        public void setGoal(GoalBean goalBean) {
            this.goal = goalBean;
        }

        public static class HeaderBean {
            private String frame_id;
            private int seq;
            private float stamp;

            public float getStamp() {
                return this.stamp;
            }

            public void setStamp(float f) {
                this.stamp = f;
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
        }

        public static class GoalIdBean {
            private String id;
            private float stamp;

            public float getStamp() {
                return this.stamp;
            }

            public void setStamp(float f) {
                this.stamp = f;
            }

            public String getId() {
                return this.id;
            }

            public void setId(String str) {
                this.id = str;
            }
        }

        public static class GoalBean {
            private TargetPoseBean target_pose;

            public TargetPoseBean getTarget_pose() {
                return this.target_pose;
            }

            public void setTarget_pose(TargetPoseBean targetPoseBean) {
                this.target_pose = targetPoseBean;
            }

            public static class TargetPoseBean {
                private HeaderBeanX header;
                private PoseBean pose;

                public HeaderBeanX getHeader() {
                    return this.header;
                }

                public void setHeader(HeaderBeanX headerBeanX) {
                    this.header = headerBeanX;
                }

                public PoseBean getPose() {
                    return this.pose;
                }

                public void setPose(PoseBean poseBean) {
                    this.pose = poseBean;
                }

                public static class HeaderBeanX {
                    private String frame_id;
                    private int seq;
                    private float stamp;

                    public float getStamp() {
                        return this.stamp;
                    }

                    public void setStamp(float f) {
                        this.stamp = f;
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
}
