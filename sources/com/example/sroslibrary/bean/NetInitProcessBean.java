package com.example.sroslibrary.bean;

public class NetInitProcessBean {
    private boolean isActivateTimeout = false;
    private boolean isRegisterTimeout = false;

    public NetInitProcessBean(boolean z) {
        this.isActivateTimeout = z;
    }

    public boolean isRegisterTimeout() {
        return this.isRegisterTimeout;
    }

    public void setRegisterTimeout(boolean z) {
        this.isRegisterTimeout = z;
    }

    public boolean isActivateTimeout() {
        return this.isActivateTimeout;
    }

    public void setActivateTimeout(boolean z) {
        this.isActivateTimeout = z;
    }

    public String toString() {
        return "NetInitProcessBean{isRegisterTimeout=" + this.isRegisterTimeout + ", isActivateTimeout=" + this.isActivateTimeout + '}';
    }
}
