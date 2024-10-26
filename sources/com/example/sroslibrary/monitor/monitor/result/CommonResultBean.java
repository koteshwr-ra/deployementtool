package com.example.sroslibrary.monitor.monitor.result;

import com.google.gson.annotations.Expose;

public class CommonResultBean {
    @Expose
    private boolean result;

    public void setResult(boolean z) {
        this.result = z;
    }

    public boolean isResult() {
        return this.result;
    }
}
