package com.ciot.navigation.navigation.task.bean;

import java.util.List;

public class TransportProgressList {
    private List<TransportProgressBean> mTransportProgressList;

    public List<TransportProgressBean> getTransportProgressList() {
        return this.mTransportProgressList;
    }

    public void setTransportProgressList(List<TransportProgressBean> list) {
        this.mTransportProgressList = list;
    }

    public String toString() {
        return "TransportProgressList{mTransportProgressList=" + this.mTransportProgressList + '}';
    }
}
