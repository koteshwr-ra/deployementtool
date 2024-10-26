package mc.csst.com.selfchassislibrary.bean.msg;

public class RobotStatusPublishBean {
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
        private int battery;
        private int charger;
        private int control_state;
        private String current_building_name;
        private String current_floor_name;
        private CurrentGoalCoordinateBean current_goal_coordinate;
        private String current_goal_name;
        private boolean hard_estop;
        private int nav_status;
        private int patrol_status;
        private boolean soft_estop;

        public String getCurrent_building_name() {
            return this.current_building_name;
        }

        public void setCurrent_building_name(String str) {
            this.current_building_name = str;
        }

        public boolean isSoft_estop() {
            return this.soft_estop;
        }

        public void setSoft_estop(boolean z) {
            this.soft_estop = z;
        }

        public int getBattery() {
            return this.battery;
        }

        public void setBattery(int i) {
            this.battery = i;
        }

        public int getCharger() {
            return this.charger;
        }

        public void setCharger(int i) {
            this.charger = i;
        }

        public int getNav_status() {
            return this.nav_status;
        }

        public void setNav_status(int i) {
            this.nav_status = i;
        }

        public int getPatrol_status() {
            return this.patrol_status;
        }

        public void setPatrol_status(int i) {
            this.patrol_status = i;
        }

        public String getCurrent_floor_name() {
            return this.current_floor_name;
        }

        public void setCurrent_floor_name(String str) {
            this.current_floor_name = str;
        }

        public CurrentGoalCoordinateBean getCurrent_goal_coordinate() {
            return this.current_goal_coordinate;
        }

        public void setCurrent_goal_coordinate(CurrentGoalCoordinateBean currentGoalCoordinateBean) {
            this.current_goal_coordinate = currentGoalCoordinateBean;
        }

        public boolean isHard_estop() {
            return this.hard_estop;
        }

        public void setHard_estop(boolean z) {
            this.hard_estop = z;
        }

        public int getControl_state() {
            return this.control_state;
        }

        public void setControl_state(int i) {
            this.control_state = i;
        }

        public String getCurrent_goal_name() {
            return this.current_goal_name;
        }

        public void setCurrent_goal_name(String str) {
            this.current_goal_name = str;
        }

        public static class CurrentGoalCoordinateBean {
            private float theta;
            private float x;
            private float y;

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

            public float getTheta() {
                return this.theta;
            }

            public void setTheta(float f) {
                this.theta = f;
            }
        }
    }
}
