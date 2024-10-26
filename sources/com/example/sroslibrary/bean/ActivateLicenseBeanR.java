package com.example.sroslibrary.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActivateLicenseBeanR {
    @SerializedName("password")
    @Expose(deserialize = true, serialize = true)
    private String password;
    @SerializedName("plat_port")
    @Expose(deserialize = true, serialize = true)
    private int plat_port;
    @SerializedName("platform_ip")
    @Expose(deserialize = true, serialize = true)
    private String platform_ip;
    @SerializedName("resourceUrl")
    @Expose(deserialize = true, serialize = true)
    private String resourceUrl;
    @SerializedName("result")
    @Expose(deserialize = true, serialize = true)
    private boolean result;
    @SerializedName("robotid")
    @Expose(deserialize = true, serialize = true)
    private String robotId;
    @SerializedName("username")
    @Expose(deserialize = true, serialize = true)
    private String username;
    @SerializedName("valid_end")
    @Expose(deserialize = true, serialize = true)
    private int valid_end;
    @SerializedName("valid_start")
    @Expose(deserialize = true, serialize = true)
    private int valid_start;

    public String getRobotId() {
        return this.robotId;
    }

    public void setRobotId(String str) {
        this.robotId = str;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public String getPlatform_ip() {
        return this.platform_ip;
    }

    public void setPlatform_ip(String str) {
        this.platform_ip = str;
    }

    public int getPlat_port() {
        return this.plat_port;
    }

    public void setPlat_port(int i) {
        this.plat_port = i;
    }

    public int getValid_start() {
        return this.valid_start;
    }

    public void setValid_start(int i) {
        this.valid_start = i;
    }

    public int getValid_end() {
        return this.valid_end;
    }

    public void setValid_end(int i) {
        this.valid_end = i;
    }

    public boolean isResult() {
        return this.result;
    }

    public void setResult(boolean z) {
        this.result = z;
    }

    public String getResourceUrl() {
        return this.resourceUrl;
    }

    public void setResourceUrl(String str) {
        this.resourceUrl = str;
    }

    public String toString() {
        return "ActivateLicenseBeanR{robotId='" + this.robotId + '\'' + ", username='" + this.username + '\'' + ", password='" + this.password + '\'' + ", platform_ip='" + this.platform_ip + '\'' + ", plat_port=" + this.plat_port + ", valid_start=" + this.valid_start + ", valid_end=" + this.valid_end + ", result=" + this.result + ", resourceUrl=" + this.resourceUrl + '}';
    }
}
