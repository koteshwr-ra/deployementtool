package mc.csst.com.selfchassislibrary.bean.msg;

public class NodeManagerControlResponseBean {
    private String id;
    private String op;
    private boolean result;
    private String service;
    private ValuesBean values;

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
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
        private int result;
        private String text;

        public String getText() {
            return this.text;
        }

        public void setText(String str) {
            this.text = str;
        }

        public int getResult() {
            return this.result;
        }

        public void setResult(int i) {
            this.result = i;
        }
    }
}
