package mc.csst.com.selfchassislibrary.bean.msg;

public class GetTimeResponse {
    private String info;
    private String op;
    private Boolean result;
    private String service;
    private ValuesBean values;

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String str) {
        this.info = str;
    }

    public ValuesBean getValues() {
        return this.values;
    }

    public void setValues(ValuesBean valuesBean) {
        this.values = valuesBean;
    }

    public Boolean getResult() {
        return this.result;
    }

    public void setResult(Boolean bool) {
        this.result = bool;
    }

    public String getService() {
        return this.service;
    }

    public void setService(String str) {
        this.service = str;
    }

    public String getOp() {
        return this.op;
    }

    public void setOp(String str) {
        this.op = str;
    }

    public static class ValuesBean {
        private TimeBean time;

        public TimeBean getTime() {
            return this.time;
        }

        public void setTime(TimeBean timeBean) {
            this.time = timeBean;
        }

        public static class TimeBean {
            private Integer nsecs;
            private Integer secs;

            public Integer getSecs() {
                return this.secs;
            }

            public void setSecs(Integer num) {
                this.secs = num;
            }

            public Integer getNsecs() {
                return this.nsecs;
            }

            public void setNsecs(Integer num) {
                this.nsecs = num;
            }
        }
    }
}
