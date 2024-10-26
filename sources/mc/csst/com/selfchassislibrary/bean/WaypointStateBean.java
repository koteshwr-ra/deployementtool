package mc.csst.com.selfchassislibrary.bean;

import java.io.Serializable;
import java.util.List;

public class WaypointStateBean implements Serializable {
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

    public static class MsgBean implements Serializable {
        private CurrentGoalCoordinateBean current_goal_coordinate;
        private String current_goal_name;
        private int nav_status;
        private int num_cancel;
        private int num_fail;
        private int num_success;
        private int num_timeout;
        private int num_total;
        private int patrol_mode;
        private int patrol_status;
        private int patrol_time;
        private List<String> poi_patrol_list;

        public int getPatrol_time() {
            return this.patrol_time;
        }

        public void setPatrol_time(int i) {
            this.patrol_time = i;
        }

        public List<String> getPoi_patrol_list() {
            return this.poi_patrol_list;
        }

        public void setPoi_patrol_list(List<String> list) {
            this.poi_patrol_list = list;
        }

        public int getNum_total() {
            return this.num_total;
        }

        public void setNum_total(int i) {
            this.num_total = i;
        }

        public int getNum_success() {
            return this.num_success;
        }

        public void setNum_success(int i) {
            this.num_success = i;
        }

        public int getNum_fail() {
            return this.num_fail;
        }

        public void setNum_fail(int i) {
            this.num_fail = i;
        }

        public int getNum_cancel() {
            return this.num_cancel;
        }

        public void setNum_cancel(int i) {
            this.num_cancel = i;
        }

        public int getNum_timeout() {
            return this.num_timeout;
        }

        public void setNum_timeout(int i) {
            this.num_timeout = i;
        }

        public int getPatrol_status() {
            return this.patrol_status;
        }

        public void setPatrol_status(int i) {
            this.patrol_status = i;
        }

        public String getCurrent_goal_name() {
            return this.current_goal_name;
        }

        public void setCurrent_goal_name(String str) {
            this.current_goal_name = str;
        }

        public CurrentGoalCoordinateBean getCurrent_goal_coordinate() {
            return this.current_goal_coordinate;
        }

        public void setCurrent_goal_coordinate(CurrentGoalCoordinateBean currentGoalCoordinateBean) {
            this.current_goal_coordinate = currentGoalCoordinateBean;
        }

        public int getPatrol_mode() {
            return this.patrol_mode;
        }

        public void setPatrol_mode(int i) {
            this.patrol_mode = i;
        }

        public int getNav_status() {
            return this.nav_status;
        }

        public void setNav_status(int i) {
            this.nav_status = i;
        }

        public static class CurrentGoalCoordinateBean implements Serializable {
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
