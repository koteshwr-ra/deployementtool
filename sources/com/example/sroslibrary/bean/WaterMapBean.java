package com.example.sroslibrary.bean;

import java.util.Map;

public class WaterMapBean {
    private String command;
    private String error_message;
    private Map<String, int[]> results;
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

    public Map<String, int[]> getResults() {
        return this.results;
    }

    public void setResults(Map<String, int[]> map) {
        this.results = map;
    }

    public String toString() {
        return "MapData{type='" + this.type + '\'' + ", command='" + this.command + '\'' + ", uuid='" + this.uuid + '\'' + ", status='" + this.status + '\'' + ", error_message='" + this.error_message + '\'' + ", results=" + this.results + '}';
    }
}
