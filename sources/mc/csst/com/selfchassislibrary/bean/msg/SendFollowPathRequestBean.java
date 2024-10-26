package mc.csst.com.selfchassislibrary.bean.msg;

public class SendFollowPathRequestBean {
    private ArgsBean args;
    private String id;
    private String op;
    private String service;

    public String getOp() {
        return this.op;
    }

    public void setOp(String str) {
        this.op = str;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getService() {
        return this.service;
    }

    public void setService(String str) {
        this.service = str;
    }

    public ArgsBean getArgs() {
        return this.args;
    }

    public void setArgs(ArgsBean argsBean) {
        this.args = argsBean;
    }

    public static class ArgsBean {
        private Integer command;
        private String goal_node_name;
        private GoalNodePoseBean goal_node_pose;
        private String start_node_name;
        private StartNodePoseBean start_node_pose;
        private String task_id;

        public Integer getCommand() {
            return this.command;
        }

        public void setCommand(Integer num) {
            this.command = num;
        }

        public String getTask_id() {
            return this.task_id;
        }

        public void setTask_id(String str) {
            this.task_id = str;
        }

        public String getStart_node_name() {
            return this.start_node_name;
        }

        public void setStart_node_name(String str) {
            this.start_node_name = str;
        }

        public String getGoal_node_name() {
            return this.goal_node_name;
        }

        public void setGoal_node_name(String str) {
            this.goal_node_name = str;
        }

        public StartNodePoseBean getStart_node_pose() {
            return this.start_node_pose;
        }

        public void setStart_node_pose(StartNodePoseBean startNodePoseBean) {
            this.start_node_pose = startNodePoseBean;
        }

        public GoalNodePoseBean getGoal_node_pose() {
            return this.goal_node_pose;
        }

        public void setGoal_node_pose(GoalNodePoseBean goalNodePoseBean) {
            this.goal_node_pose = goalNodePoseBean;
        }

        public static class StartNodePoseBean {
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
                private Integer x;
                private Integer y;
                private Integer z;

                public Integer getY() {
                    return this.y;
                }

                public void setY(Integer num) {
                    this.y = num;
                }

                public Integer getX() {
                    return this.x;
                }

                public void setX(Integer num) {
                    this.x = num;
                }

                public Integer getZ() {
                    return this.z;
                }

                public void setZ(Integer num) {
                    this.z = num;
                }
            }

            public static class OrientationBean {
                private Integer w;
                private Integer x;
                private Integer y;
                private Integer z;

                public Integer getY() {
                    return this.y;
                }

                public void setY(Integer num) {
                    this.y = num;
                }

                public Integer getX() {
                    return this.x;
                }

                public void setX(Integer num) {
                    this.x = num;
                }

                public Integer getZ() {
                    return this.z;
                }

                public void setZ(Integer num) {
                    this.z = num;
                }

                public Integer getW() {
                    return this.w;
                }

                public void setW(Integer num) {
                    this.w = num;
                }
            }
        }

        public static class GoalNodePoseBean {
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
                private Float x;
                private Float y;
                private Float z;

                public PositionBean(Float f, Float f2, Float f3) {
                    this.y = f2;
                    this.x = f;
                    this.z = f3;
                }
            }

            public static class OrientationBean {
                private Float w;
                private Float x;
                private Float y;
                private Float z;

                public OrientationBean(Float f, Float f2, Float f3, Float f4) {
                    this.y = f2;
                    this.x = f;
                    this.z = f3;
                    this.w = f4;
                }
            }
        }
    }
}
