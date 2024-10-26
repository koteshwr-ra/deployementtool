package mc.csst.com.selfchassislibrary.bean.msg;

import java.io.Serializable;

public class RobotInfoResponseBean {
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
        private String firmware_version;
        private String hardware_version;
        private String robot_id;
        private String robot_type;
        private String software_version;

        public String getRobot_id() {
            return this.robot_id;
        }

        public void setRobot_id(String str) {
            this.robot_id = str;
        }

        public String getFirmware_version() {
            return this.firmware_version;
        }

        public void setFirmware_version(String str) {
            this.firmware_version = str;
        }

        public String getRobot_type() {
            return this.robot_type;
        }

        public void setRobot_type(String str) {
            this.robot_type = str;
        }

        public String getSoftware_version() {
            return this.software_version;
        }

        public void setSoftware_version(String str) {
            this.software_version = str;
        }

        public String getHardware_version() {
            return this.hardware_version;
        }

        public void setHardware_version(String str) {
            this.hardware_version = str;
        }
    }
}
