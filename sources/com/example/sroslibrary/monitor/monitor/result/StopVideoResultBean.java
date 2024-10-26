package com.example.sroslibrary.monitor.monitor.result;

import com.google.gson.annotations.Expose;

public class StopVideoResultBean {
    @Expose
    private String reason;
    @Expose
    private boolean result;

    public void setResult(boolean z) {
        this.result = z;
    }

    public void setReason(String str) {
        this.reason = str;
    }
}
