package com.ciot.networklib.bean.patrol;

import java.util.List;

public class PatrolRecordBean {
    private Long begin;
    private Long end;
    private String exception;
    private String id;
    private String line;
    private String map;
    private List<PatrolPathBean> paths;
    private String robot;
    private String state;
    private String task;

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getRobot() {
        return this.robot;
    }

    public void setRobot(String str) {
        this.robot = str;
    }

    public String getLine() {
        return this.line;
    }

    public void setLine(String str) {
        this.line = str;
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

    public String getTask() {
        return this.task;
    }

    public void setTask(String str) {
        this.task = str;
    }

    public String getMap() {
        return this.map;
    }

    public void setMap(String str) {
        this.map = str;
    }

    public List<PatrolPathBean> getPaths() {
        return this.paths;
    }

    public void setPaths(List<PatrolPathBean> list) {
        this.paths = list;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String str) {
        this.state = str;
    }

    public String getException() {
        return this.exception;
    }

    public void setException(String str) {
        this.exception = str;
    }
}
