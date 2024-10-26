package com.ciot.networklib.bean.thermometry;

import com.ciot.realm.db.Person;

public class CreateThermometry {
    private long createtime;
    private String face;
    private Person person;
    private String robot;
    private float temperature;

    public long getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(long j) {
        this.createtime = j;
    }

    public String getRobot() {
        return this.robot;
    }

    public void setRobot(String str) {
        this.robot = str;
    }

    public String getFace() {
        return this.face;
    }

    public void setFace(String str) {
        this.face = str;
    }

    public float getTemperature() {
        return this.temperature;
    }

    public void setTemperature(float f) {
        this.temperature = f;
    }

    public Person getPerson() {
        return this.person;
    }

    public void setPerson(Person person2) {
        this.person = person2;
    }

    public String toString() {
        return "CreateThermometry{robot='" + this.robot + '\'' + ", face='" + this.face + '\'' + ", temperature=" + this.temperature + ", person=" + this.person + ", createtime=" + this.createtime + '}';
    }
}
