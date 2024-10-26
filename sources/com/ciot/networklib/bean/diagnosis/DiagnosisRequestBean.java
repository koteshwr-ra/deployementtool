package com.ciot.networklib.bean.diagnosis;

import java.util.List;

public class DiagnosisRequestBean {
    private String address;
    private String description;
    private List<String> faultCodeList;
    private String productSerialNo;

    public String getProductSerialNo() {
        return this.productSerialNo;
    }

    public void setProductSerialNo(String str) {
        this.productSerialNo = str;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public List<String> getFaultCodeList() {
        return this.faultCodeList;
    }

    public void setFaultCodeList(List<String> list) {
        this.faultCodeList = list;
    }
}
