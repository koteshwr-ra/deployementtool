package com.ciot.waterchassis.bean;

public class WaterMoveBean {
    private String command;
    private String error_message;
    private String status;
    private String task_id;
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

    public String getTask_id() {
        return this.task_id;
    }

    public void setTask_id(String str) {
        this.task_id = str;
    }

    public String toString() {
        return "WaterMoveBean{type='" + this.type + '\'' + ", command='" + this.command + '\'' + ", uuid='" + this.uuid + '\'' + ", status='" + this.status + '\'' + ", error_message='" + this.error_message + '\'' + ", task_id='" + this.task_id + '\'' + '}';
    }
}
