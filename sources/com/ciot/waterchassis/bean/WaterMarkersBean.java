package com.ciot.waterchassis.bean;

import java.util.Map;

public class WaterMarkersBean {
    private String command;
    private String error_message;
    private Map<String, WaterMarkerBean> results;
    private String status;
    private String type;
    private String uuid;

    public String getError_message() {
        return this.error_message;
    }

    public void setError_message(String str) {
        this.error_message = str;
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

    public Map<String, WaterMarkerBean> getResults() {
        return this.results;
    }

    public void setResults(Map<String, WaterMarkerBean> map) {
        this.results = map;
    }

    public String getCommand() {
        return this.command;
    }

    public void setCommand(String str) {
        this.command = str;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String toString() {
        return "MarkerData{error_message='" + this.error_message + '\'' + ", type='" + this.type + '\'' + ", uuid='" + this.uuid + '\'' + ", results=" + this.results + ", command='" + this.command + '\'' + ", status='" + this.status + '\'' + '}';
    }
}
