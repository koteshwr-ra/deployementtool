package com.ciot.networklib.bean.upload;

public class UploadResponse {
    private String error;
    private boolean success;

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean z) {
        this.success = z;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String str) {
        this.error = str;
    }

    public String toString() {
        return "UploadResponse{success='" + this.success + '\'' + ", error='" + this.error + '\'' + '}';
    }
}
