package mc.csst.com.selfchassislibrary.bean.msg;

import java.io.Serializable;

public class GlobalLocateResponseBean {
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

    public static class ValuesBean implements Serializable {
        private int cmd_id;
        private double confidence;
        private EstimatedPoseBean estimated_pose;
        private String info;
        private int result;

        public String getInfo() {
            return this.info;
        }

        public void setInfo(String str) {
            this.info = str;
        }

        public EstimatedPoseBean getEstimated_pose() {
            return this.estimated_pose;
        }

        public void setEstimated_pose(EstimatedPoseBean estimatedPoseBean) {
            this.estimated_pose = estimatedPoseBean;
        }

        public double getConfidence() {
            return this.confidence;
        }

        public void setConfidence(double d) {
            this.confidence = d;
        }

        public int getResult() {
            return this.result;
        }

        public void setResult(int i) {
            this.result = i;
        }

        public int getCmd_id() {
            return this.cmd_id;
        }

        public void setCmd_id(int i) {
            this.cmd_id = i;
        }

        public static class EstimatedPoseBean implements Serializable {
            private float theta;
            private float x;
            private float y;

            public float getY() {
                return this.y;
            }

            public void setY(float f) {
                this.y = f;
            }

            public float getX() {
                return this.x;
            }

            public void setX(float f) {
                this.x = f;
            }

            public float getTheta() {
                return this.theta;
            }

            public void setTheta(float f) {
                this.theta = f;
            }
        }
    }
}
