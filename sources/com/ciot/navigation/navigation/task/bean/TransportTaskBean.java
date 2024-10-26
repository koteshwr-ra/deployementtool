package com.ciot.navigation.navigation.task.bean;

import java.io.Serializable;

public class TransportTaskBean implements Serializable {
    private static final long serialVersionUID = -1731262821229505033L;
    private String markerName;
    private String recipientName;

    public String getRecipientName() {
        return this.recipientName;
    }

    public void setRecipientName(String str) {
        this.recipientName = str;
    }

    public String getMarkerName() {
        return this.markerName;
    }

    public void setMarkerName(String str) {
        this.markerName = str;
    }

    public String toString() {
        return "TransportTaskBean{recipientName='" + this.recipientName + '\'' + ", markerName='" + this.markerName + '\'' + '}';
    }
}
