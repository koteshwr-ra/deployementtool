package com.ciot.networklib.bean.temp;

import com.ciot.realm.db.Person;

public class TemRecordBean {
    private long createtime;
    private Person person;
    private String robot;
    private float temperature;

    public TemRecordBean(String str, Person person2, long j, float f) {
        this.robot = str;
        this.person = person2;
        this.createtime = j;
        this.temperature = f;
    }

    public String getRobot() {
        return this.robot;
    }

    public void setRobot(String str) {
        this.robot = str;
    }

    public Person getPerson() {
        return this.person;
    }

    public void setPerson(Person person2) {
        this.person = person2;
    }

    public long getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(long j) {
        this.createtime = j;
    }

    public float getTemperature() {
        return this.temperature;
    }

    public void setTemperature(float f) {
        this.temperature = f;
    }
}
