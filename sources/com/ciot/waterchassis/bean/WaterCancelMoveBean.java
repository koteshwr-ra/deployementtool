package com.ciot.waterchassis.bean;

public class WaterCancelMoveBean {
    private String command;
    private String error_message;
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

    public String toString() {
        return "WaterCancelMoveBean{command='" + this.command + '\'' + ", error_message='" + this.error_message + '\'' + ", status='" + this.status + '\'' + ", type='" + this.type + '\'' + ", uuid='" + this.uuid + '\'' + '}';
    }
}
