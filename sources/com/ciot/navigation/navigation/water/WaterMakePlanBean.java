package com.ciot.navigation.navigation.water;

public class WaterMakePlanBean {
    public String command;
    public String error_message;
    public ResultsBean results;
    public String status;
    public String type;
    public String uuid;

    public static class ResultsBean {
        public double distance;

        public String toString() {
            return "ResultsBean{distance=" + this.distance + '}';
        }
    }

    public String toString() {
        return "WaterMakePlanBean{command='" + this.command + '\'' + ", error_message='" + this.error_message + '\'' + ", results=" + this.results + ", status='" + this.status + '\'' + ", type='" + this.type + '\'' + ", uuid='" + this.uuid + '\'' + '}';
    }
}
