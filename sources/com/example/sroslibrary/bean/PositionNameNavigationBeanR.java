package com.example.sroslibrary.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class PositionNameNavigationBeanR implements Serializable {
    private static final long serialVersionUID = -1277868181532377929L;
    @SerializedName("reason")
    @Expose
    private String reason;
    @SerializedName("result")
    @Expose
    private boolean result;

    public boolean isResult() {
        return this.result;
    }

    public void setResult(boolean z) {
        this.result = z;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String str) {
        this.reason = str;
    }

    public String toString() {
        return "AddNavigationPointBeanR{result=" + this.result + ", reason='" + this.reason + '\'' + '}';
    }
}
