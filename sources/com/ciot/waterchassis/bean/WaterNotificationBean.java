package com.ciot.waterchassis.bean;

public class WaterNotificationBean {
    private String code;
    private Object data;
    private String description;
    private String level;
    private String type;

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object obj) {
        this.data = obj;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String str) {
        this.level = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String toString() {
        return "WaterNotificationBean{code='" + this.code + '\'' + ", data=" + this.data + ", description='" + this.description + '\'' + ", level='" + this.level + '\'' + ", type='" + this.type + '\'' + '}';
    }
}
