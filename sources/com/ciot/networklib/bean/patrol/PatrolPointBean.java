package com.ciot.networklib.bean.patrol;

import java.util.List;

public class PatrolPointBean {
    private List<PatrolActionBean> actions;
    private List<PatrolReportBean> alarms;
    private Long begin;
    private Long end;
    private List<Double> loc;
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public List<Double> getLoc() {
        return this.loc;
    }

    public void setLoc(List<Double> list) {
        this.loc = list;
    }

    public Long getBegin() {
        return this.begin;
    }

    public void setBegin(Long l) {
        this.begin = l;
    }

    public Long getEnd() {
        return this.end;
    }

    public void setEnd(Long l) {
        this.end = l;
    }

    public List<PatrolActionBean> getActions() {
        return this.actions;
    }

    public void setActions(List<PatrolActionBean> list) {
        this.actions = list;
    }

    public List<PatrolReportBean> getAlarms() {
        return this.alarms;
    }

    public void setAlarms(List<PatrolReportBean> list) {
        this.alarms = list;
    }
}
