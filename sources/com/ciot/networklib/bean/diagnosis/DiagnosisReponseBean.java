package com.ciot.networklib.bean.diagnosis;

import java.util.List;

public class DiagnosisReponseBean {
    private Integer code;
    private List<String> data;
    private Boolean encode;
    private String message;
    private Boolean status;
    private Integer total;
    private Integer totalSize;

    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(Boolean bool) {
        this.status = bool;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer num) {
        this.code = num;
    }

    public Integer getTotalSize() {
        return this.totalSize;
    }

    public void setTotalSize(Integer num) {
        this.totalSize = num;
    }

    public Integer getTotal() {
        return this.total;
    }

    public void setTotal(Integer num) {
        this.total = num;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public Boolean getEncode() {
        return this.encode;
    }

    public void setEncode(Boolean bool) {
        this.encode = bool;
    }

    public List<String> getData() {
        return this.data;
    }

    public void setData(List<String> list) {
        this.data = list;
    }
}
