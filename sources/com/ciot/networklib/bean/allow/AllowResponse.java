package com.ciot.networklib.bean.allow;

public class AllowResponse {
    private DataBean data;
    private String message;
    private boolean success;

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean z) {
        this.success = z;
    }

    public DataBean getData() {
        return this.data;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public static class DataBean {
        private String cloud_trails;
        private String host;
        private String self_study;
        private String semantics_type;

        public String getHost() {
            return this.host;
        }

        public void setHost(String str) {
            this.host = str;
        }

        public String getCloud_trails() {
            return this.cloud_trails;
        }

        public void setCloud_trails(String str) {
            this.cloud_trails = str;
        }

        public String getSelf_study() {
            return this.self_study;
        }

        public void setSelf_study(String str) {
            this.self_study = str;
        }

        public String getSemantics_type() {
            return this.semantics_type;
        }

        public void setSemantics_type(String str) {
            this.semantics_type = str;
        }
    }
}
