package com.ciot.waterchassis.bean;

public class WaterInfoBean {
    private String command;
    private String error_message;
    private InfoBean results;
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

    public InfoBean getResults() {
        return this.results;
    }

    public void setResults(InfoBean infoBean) {
        this.results = infoBean;
    }

    public String toString() {
        return "WaterInfoBean{type='" + this.type + '\'' + ", command='" + this.command + '\'' + ", uuid='" + this.uuid + '\'' + ", status='" + this.status + '\'' + ", error_message='" + this.error_message + '\'' + ", results=" + this.results + '}';
    }

    public static class InfoBean {
        private String product_id;

        public String getProductId() {
            return this.product_id;
        }

        public void setProductId(String str) {
            this.product_id = str;
        }

        public String toString() {
            return "InfoBean{product_id='" + this.product_id + '\'' + '}';
        }
    }
}
