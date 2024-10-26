package mc.csst.com.selfchassislibrary.bean.msg;

public class SendPathRecordRequestBean {
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
        private Integer pass_capacity;
        private String path_name;
        private Float path_width;
        private String start_node_name;
        private StartNodePoseBean start_node_pose;
        private Float velocity;

        public String getPath_name() {
            return this.path_name;
        }

        public void setPath_name(String str) {
            this.path_name = str;
        }

        public Float getPath_width() {
            return this.path_width;
        }

        public void setPath_width(Float f) {
            this.path_width = f;
        }

        public Integer getPass_capacity() {
            return this.pass_capacity;
        }

        public void setPass_capacity(Integer num) {
            this.pass_capacity = num;
        }

        public Integer getCommand() {
            return this.command;
        }

        public void setCommand(Integer num) {
            this.command = num;
        }

        public String getStart_node_name() {
            return this.start_node_name;
        }

        public void setStart_node_name(String str) {
            this.start_node_name = str;
        }

        public Float getVelocity() {
            return this.velocity;
        }

        public void setVelocity(Float f) {
            this.velocity = f;
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
