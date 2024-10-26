package com.ciot.networklib.bean;

public class Function {
    private String backgroundurl;
    private boolean function_available;
    private String function_desc;
    private int function_id;
    private String function_key;
    private String function_name;
    private String function_type;

    public int getFunction_id() {
        return this.function_id;
    }

    public void setFunction_id(int i) {
        this.function_id = i;
    }

    public String getFunction_key() {
        return this.function_key;
    }

    public void setFunction_key(String str) {
        this.function_key = str;
    }

    public String getFunction_type() {
        return this.function_type;
    }

    public void setFunction_type(String str) {
        this.function_type = str;
    }

    public String getFunction_name() {
        return this.function_name;
    }

    public void setFunction_name(String str) {
        this.function_name = str;
    }

    public String getFunction_desc() {
        return this.function_desc;
    }

    public void setFunction_desc(String str) {
        this.function_desc = str;
    }

    public boolean isFunction_available() {
        return this.function_available;
    }

    public void setFunction_available(boolean z) {
        this.function_available = z;
    }

    public String getBackgroundurl() {
        return this.backgroundurl;
    }

    public void setBackgroundurl(String str) {
        this.backgroundurl = str;
    }

    public String toString() {
        return "Function{function_id=" + this.function_id + ", function_key='" + this.function_key + '\'' + ", function_type='" + this.function_type + '\'' + ", function_name='" + this.function_name + '\'' + ", function_desc='" + this.function_desc + '\'' + ", function_available=" + this.function_available + ", backgroundurl='" + this.backgroundurl + '\'' + '}';
    }
}
