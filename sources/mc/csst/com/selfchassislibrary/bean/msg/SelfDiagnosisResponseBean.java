package mc.csst.com.selfchassislibrary.bean.msg;

import java.util.List;

public class SelfDiagnosisResponseBean {
    private String id;
    private String info;
    private String op;
    private boolean result;
    private String service;
    private ValuesBean values;

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String str) {
        this.info = str;
    }

    public String getService() {
        return this.service;
    }

    public void setService(String str) {
        this.service = str;
    }

    public ValuesBean getValues() {
        return this.values;
    }

    public void setValues(ValuesBean valuesBean) {
        this.values = valuesBean;
    }

    public boolean isResult() {
        return this.result;
    }

    public void setResult(boolean z) {
        this.result = z;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getOp() {
        return this.op;
    }

    public void setOp(String str) {
        this.op = str;
    }

    public static class ValuesBean {
        private String id;
        private int passed;
        private List<StatusBean> status;

        public String getId() {
            return this.id;
        }

        public void setId(String str) {
            this.id = str;
        }

        public int getPassed() {
            return this.passed;
        }

        public void setPassed(int i) {
            this.passed = i;
        }

        public List<StatusBean> getStatus() {
            return this.status;
        }

        public void setStatus(List<StatusBean> list) {
            this.status = list;
        }

        public static class StatusBean {
            private String hardware_id;
            private int level;
            private String message;
            private String name;
            private List<String> values;

            public String getMessage() {
                return this.message;
            }

            public void setMessage(String str) {
                this.message = str;
            }

            public String getHardware_id() {
                return this.hardware_id;
            }

            public void setHardware_id(String str) {
                this.hardware_id = str;
            }

            public String getName() {
                return this.name;
            }

            public void setName(String str) {
                this.name = str;
            }

            public int getLevel() {
                return this.level;
            }

            public void setLevel(int i) {
                this.level = i;
            }

            public List<String> getValues() {
                return this.values;
            }

            public void setValues(List<String> list) {
                this.values = list;
            }

            public String toString() {
                return "StatusBean{message='" + this.message + '\'' + ", hardware_id='" + this.hardware_id + '\'' + ", name='" + this.name + '\'' + ", level=" + this.level + ", values=" + this.values + '}';
            }
        }

        public String toString() {
            return "ValuesBean{id='" + this.id + '\'' + ", passed=" + this.passed + ", status=" + this.status + '}';
        }
    }

    public String toString() {
        return "SelfDiagnosisResponseBean{info='" + this.info + '\'' + ", service='" + this.service + '\'' + ", values=" + this.values + ", result=" + this.result + ", id='" + this.id + '\'' + ", op='" + this.op + '\'' + '}';
    }
}
