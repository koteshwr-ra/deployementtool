package mc.csst.com.selfchassis.ui.fragment.set.language.bean;

import com.google.gson.annotations.SerializedName;

public class GetGatewayRespBean {
    private String cn;
    @SerializedName("default")
    private String defaultAddress;
    private String hk;
    private String us;

    public GetGatewayRespBean(String str) {
        this.defaultAddress = str;
    }

    public String getDefaultAddress() {
        return this.defaultAddress;
    }

    public void setDefaultAddress(String str) {
        this.defaultAddress = str;
    }

    public String getCn() {
        return this.cn;
    }

    public void setCn(String str) {
        this.cn = str;
    }

    public String getHk() {
        return this.hk;
    }

    public void setHk(String str) {
        this.hk = str;
    }

    public String getUs() {
        return this.us;
    }

    public void setUs(String str) {
        this.us = str;
    }
}
