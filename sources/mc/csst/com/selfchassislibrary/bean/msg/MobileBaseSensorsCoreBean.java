package mc.csst.com.selfchassislibrary.bean.msg;

import java.util.List;

public class MobileBaseSensorsCoreBean {
    private MsgBean msg;
    private String op;
    private String topic;

    public String getTopic() {
        return this.topic;
    }

    public void setTopic(String str) {
        this.topic = str;
    }

    public MsgBean getMsg() {
        return this.msg;
    }

    public void setMsg(MsgBean msgBean) {
        this.msg = msgBean;
    }

    public String getOp() {
        return this.op;
    }

    public void setOp(String str) {
        this.op = str;
    }

    public static class MsgBean {
        private List<Integer> analog_input;
        private int battery;
        private List<Integer> bottom;
        private int bumper;
        private int buttons;
        private int charger;
        private int cliff;
        private String current;
        private int digital_input;
        private HeaderBean header;
        private int left_encoder;
        private int left_pwm;
        private int over_current;
        private int right_encoder;
        private int right_pwm;
        private int time_stamp;
        private int wheel_drop;

        public String getCurrent() {
            return this.current;
        }

        public void setCurrent(String str) {
            this.current = str;
        }

        public int getRight_encoder() {
            return this.right_encoder;
        }

        public void setRight_encoder(int i) {
            this.right_encoder = i;
        }

        public List<Integer> getAnalog_input() {
            return this.analog_input;
        }

        public void setAnalog_input(List<Integer> list) {
            this.analog_input = list;
        }

        public List<Integer> getBottom() {
            return this.bottom;
        }

        public void setBottom(List<Integer> list) {
            this.bottom = list;
        }

        public int getBattery() {
            return this.battery;
        }

        public void setBattery(int i) {
            this.battery = i;
        }

        public int getCharger() {
            return this.charger;
        }

        public void setCharger(int i) {
            this.charger = i;
        }

        public int getOver_current() {
            return this.over_current;
        }

        public void setOver_current(int i) {
            this.over_current = i;
        }

        public int getLeft_pwm() {
            return this.left_pwm;
        }

        public void setLeft_pwm(int i) {
            this.left_pwm = i;
        }

        public int getButtons() {
            return this.buttons;
        }

        public void setButtons(int i) {
            this.buttons = i;
        }

        public HeaderBean getHeader() {
            return this.header;
        }

        public void setHeader(HeaderBean headerBean) {
            this.header = headerBean;
        }

        public int getRight_pwm() {
            return this.right_pwm;
        }

        public void setRight_pwm(int i) {
            this.right_pwm = i;
        }

        public int getWheel_drop() {
            return this.wheel_drop;
        }

        public void setWheel_drop(int i) {
            this.wheel_drop = i;
        }

        public int getBumper() {
            return this.bumper;
        }

        public void setBumper(int i) {
            this.bumper = i;
        }

        public int getTime_stamp() {
            return this.time_stamp;
        }

        public void setTime_stamp(int i) {
            this.time_stamp = i;
        }

        public int getDigital_input() {
            return this.digital_input;
        }

        public void setDigital_input(int i) {
            this.digital_input = i;
        }

        public int getLeft_encoder() {
            return this.left_encoder;
        }

        public void setLeft_encoder(int i) {
            this.left_encoder = i;
        }

        public int getCliff() {
            return this.cliff;
        }

        public void setCliff(int i) {
            this.cliff = i;
        }

        public static class HeaderBean {
            private String frame_id;
            private long seq;
            private StampBean stamp;

            public StampBean getStamp() {
                return this.stamp;
            }

            public void setStamp(StampBean stampBean) {
                this.stamp = stampBean;
            }

            public String getFrame_id() {
                return this.frame_id;
            }

            public void setFrame_id(String str) {
                this.frame_id = str;
            }

            public long getSeq() {
                return this.seq;
            }

            public void setSeq(long j) {
                this.seq = j;
            }

            public static class StampBean {
                private long nsecs;
                private long secs;

                public long getSecs() {
                    return this.secs;
                }

                public void setSecs(long j) {
                    this.secs = j;
                }

                public long getNsecs() {
                    return this.nsecs;
                }

                public void setNsecs(long j) {
                    this.nsecs = j;
                }
            }
        }
    }
}
