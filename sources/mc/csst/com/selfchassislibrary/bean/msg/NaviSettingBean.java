package mc.csst.com.selfchassislibrary.bean.msg;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class NaviSettingBean implements Serializable {
    @SerializedName("enable")
    private Boolean enable;
    @SerializedName("info")
    private String info;
    @SerializedName("name")
    private String name;
    @SerializedName("value")
    private Integer value;
    @SerializedName("value_ext")
    private float valueExt;

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String str) {
        this.info = str;
    }

    public float getValueExt() {
        return this.valueExt;
    }

    public void setValueExt(float f) {
        this.valueExt = f;
    }

    public Boolean isEnable() {
        return this.enable;
    }

    public void setEnable(Boolean bool) {
        this.enable = bool;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public Integer getValue() {
        return this.value;
    }

    public void setValue(Integer num) {
        this.value = num;
    }
}
