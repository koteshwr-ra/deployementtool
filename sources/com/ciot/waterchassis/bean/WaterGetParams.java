package com.ciot.waterchassis.bean;

public class WaterGetParams {
    private String command;
    private String error_message;
    private ResultsBean results;
    private String status;
    private String type;
    private String uuid;

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getCommand() {
        return this.command;
    }

    public void setCommand(String str) {
        this.command = str;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String str) {
        this.uuid = str;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
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

    public static class ResultsBean {
        private float max_speed_linear;

        public float getMax_speed_linear() {
            return this.max_speed_linear;
        }

        public void setMax_speed_linear(float f) {
            this.max_speed_linear = f;
        }

        public String toString() {
            return "ResultsBean{max_speed_linear=" + this.max_speed_linear + '}';
        }
    }

    public String toString() {
        return "WaterGetParams{type='" + this.type + '\'' + ", command='" + this.command + '\'' + ", uuid='" + this.uuid + '\'' + ", status='" + this.status + '\'' + ", error_message='" + this.error_message + '\'' + ", results=" + this.results + '}';
    }
}
