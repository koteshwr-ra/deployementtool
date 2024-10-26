package com.ciot.networklib.bean.patrol;

import java.util.List;

public class PatrolRouteBean {
    private List<PatrolActionBean> actions;
    private List<PatrolReportBean> alarms;
    private Long begin;
    private Long end;
    private List<Double> loc_end;
    private List<Double> loc_start;
    private float speed;

    public PatrolRouteBean(List<Double> list, List<Double> list2, float f, Long l, Long l2, List<PatrolActionBean> list3, List<PatrolReportBean> list4) {
        this.loc_start = list;
        this.loc_end = list2;
        this.speed = f;
        this.begin = l;
        this.end = l2;
        this.actions = list3;
        this.alarms = list4;
    }

    public PatrolRouteBean() {
    }

    public List<Double> getLoc_start() {
        return this.loc_start;
    }

    public void setLoc_start(List<Double> list) {
        this.loc_start = list;
    }

    public List<Double> getLoc_end() {
        return this.loc_end;
    }

    public void setLoc_end(List<Double> list) {
        this.loc_end = list;
    }

    public float getSpeed() {
        return this.speed;
    }

    public void setSpeed(float f) {
        this.speed = f;
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
