package com.example.sroslibrary.monitor.monitor.result;

import com.google.gson.annotations.Expose;

public class StartVideoResultBean {
    @Expose
    private String handle;
    @Expose
    private int height;
    @Expose
    private int iMode;
    @Expose
    private String reason;
    @Expose
    private boolean result;
    @Expose
    private int stream;
    @Expose
    private int width;

    public StartVideoResultBean(int i, String str, int i2, int i3, int i4) {
        this.stream = i;
        this.handle = str;
        this.iMode = i2;
        this.width = i3;
        this.height = i4;
    }

    public void setReason(String str) {
        this.reason = str;
    }

    public void setResult(boolean z) {
        this.result = z;
    }
}
