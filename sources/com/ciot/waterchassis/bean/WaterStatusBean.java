package com.ciot.waterchassis.bean;

import com.ciot.base.constant.SpConstant;
import com.ciot.base.storage.MySpUtils;

public class WaterStatusBean {
    private String command;
    private String error_message;
    private ResultsBean results;
    private String status;
    private String type;
    private String uuid;

    public String getCommand() {
        return this.command;
    }

    public void setCommand(String str) {
        this.command = str;
    }

    public String getError_message() {
        return this.error_message;
    }

    public void setError_message(String str) {
        this.error_message = str;
    }

    public ResultsBean getResults() {
        return this.results;
    }

    public void setResults(ResultsBean resultsBean) {
        this.results = resultsBean;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String str) {
        this.uuid = str;
    }

    public static class ResultsBean {
        public boolean charge_state;
        public String chargepile_id;
        private int current_floor;
        private CurrentPoseBean current_pose;
        private String error_code;
        private boolean estop_state;
        private boolean hand_charge;
        public boolean hard_estop_state;
        private int move_retry_times;
        private String move_status;
        private String move_target;
        private int power_percent;
        private String running_status;
        private boolean soft_estop_state;

        public boolean isFullCharge() {
            return this.power_percent > MySpUtils.getInstance().getInt(SpConstant.SP_LOW_BATTERY_VALUE, 20);
        }

        public boolean isFullAndCharge() {
            return this.power_percent >= 100 && this.charge_state;
        }

        public boolean isCharge_state() {
            return this.charge_state;
        }

        public void setCharge_state(boolean z) {
            this.charge_state = z;
        }

        public int getCurrent_floor() {
            return this.current_floor;
        }

        public void setCurrent_floor(int i) {
            this.current_floor = i;
        }

        public CurrentPoseBean getCurrent_pose() {
            return this.current_pose;
        }

        public void setCurrent_pose(CurrentPoseBean currentPoseBean) {
            this.current_pose = currentPoseBean;
        }

        public String getError_code() {
            return this.error_code;
        }

        public void setError_code(String str) {
            this.error_code = str;
        }

        public boolean isEstop_state() {
            return this.estop_state;
        }

        public void setEstop_state(boolean z) {
            this.estop_state = z;
        }

        public boolean isHard_estop_state() {
            return this.hard_estop_state;
        }

        public void setHard_estop_state(boolean z) {
            this.hard_estop_state = z;
        }

        public int getMove_retry_times() {
            return this.move_retry_times;
        }

        public void setMove_retry_times(int i) {
            this.move_retry_times = i;
        }

        public String getMove_status() {
            return this.move_status;
        }

        public void setMove_status(String str) {
            this.move_status = str;
        }

        public String getMove_target() {
            return this.move_target;
        }

        public void setMove_target(String str) {
            this.move_target = str;
        }

        public int getPower_percent() {
            return this.power_percent;
        }

        public void setPower_percent(int i) {
            this.power_percent = i;
        }

        public boolean isSoft_estop_state() {
            return this.soft_estop_state;
        }

        public void setSoft_estop_state(boolean z) {
            this.soft_estop_state = z;
        }

        public boolean isHand_charge() {
            return this.hand_charge;
        }

        public void setHand_charge(boolean z) {
            this.hand_charge = z;
        }

        public String getRunning_status() {
            return this.running_status;
        }

        public void setRunning_status(String str) {
            this.running_status = str;
        }

        public static class CurrentPoseBean {
            private double theta;
            private double x;
            private double y;

            public double getTheta() {
                return this.theta;
            }

            public void setTheta(double d) {
                this.theta = d;
            }

            public double getX() {
                return this.x;
            }

            public void setX(double d) {
                this.x = d;
            }

            public double getY() {
                return this.y;
            }

            public void setY(double d) {
                this.y = d;
            }

            public String toString() {
                return "x=" + this.x + ", y=" + this.y + ", theta=" + this.theta;
            }
        }

        public String toString() {
            return "ResultsBean{charge_state=" + this.charge_state + ", current_floor=" + this.current_floor + ", current_pose=" + this.current_pose + ", error_code='" + this.error_code + '\'' + ", estop_state=" + this.estop_state + ", hand_charge=" + this.hand_charge + ", hard_estop_state=" + this.hard_estop_state + ", move_retry_times=" + this.move_retry_times + ", move_status='" + this.move_status + '\'' + ", move_target='" + this.move_target + '\'' + ", power_percent=" + this.power_percent + ", running_status='" + this.running_status + '\'' + ", chargepile_id='" + this.chargepile_id + '\'' + ", soft_estop_state=" + this.soft_estop_state + '}';
        }
    }

    public String toString() {
        return "WaterStatusBean{command='" + this.command + '\'' + ", error_message='" + this.error_message + '\'' + ", results=" + this.results + ", status='" + this.status + '\'' + ", type='" + this.type + '\'' + ", uuid='" + this.uuid + '\'' + '}';
    }
}
