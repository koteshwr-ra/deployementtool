package com.example.sroslibrary.monitor.monitor.result;

import com.google.gson.annotations.Expose;

public class StartPhoneCallResultBean {
    @Expose
    private String handle;
    @Expose
    private String reason;
    @Expose
    private boolean result;
    @Expose
    private int stream;

    public StartPhoneCallResultBean(int i, String str) {
        this.stream = i;
        this.handle = str;
    }

    public void setReason(String str) {
        this.reason = str;
    }

    public void setResult(boolean z) {
        this.result = z;
    }
}
