package com.ciot.networklib.bean;

import java.util.List;

public class UploadRecordBean {
    public List<ZiYanUpdateBean> records;

    public UploadRecordBean(List<ZiYanUpdateBean> list) {
        this.records = list;
    }

    public UploadRecordBean() {
    }

    public String toString() {
        return "UploadRecordBean{record=" + this.records + '}';
    }
}
