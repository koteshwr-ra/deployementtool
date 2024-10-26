package com.ciot.waterchassis.bean;

public class WaterRobotInfo {
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
        private String product_id;

        public String getProduct_id() {
            return this.product_id;
        }

        public void setProduct_id(String str) {
            this.product_id = str;
        }
    }
}
