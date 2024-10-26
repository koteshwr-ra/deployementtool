package mc.csst.com.selfchassislibrary.bean.msg;

public class SensorFeaturesBean {
    private boolean enable;
    private String info;
    private String name;
    private int value;
    private double value_ext;

    public SensorFeaturesBean(String str, boolean z) {
        this.enable = z;
        this.name = str;
    }

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String str) {
        this.info = str;
    }

    public double getValue_ext() {
        return this.value_ext;
    }

    public void setValue_ext(double d) {
        this.value_ext = d;
    }

    public boolean isEnable() {
        return this.enable;
    }

    public void setEnable(boolean z) {
        this.enable = z;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int i) {
        this.value = i;
    }
}
