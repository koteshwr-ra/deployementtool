package com.ciot.networklib.bean.thermometry;

import com.ciot.realm.db.Person;

public class CreateThermometryResponse {
    private float createtime;
    private String face;
    private String id;
    private Person person;
    private String project;
    private String robot;
    private float temperature;

    public float getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(float f) {
        this.createtime = f;
    }

    public Person getPerson() {
        return this.person;
    }

    public void setPerson(Person person2) {
        this.person = person2;
    }

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

    public String getProject() {
        return this.project;
    }

    public void setProject(String str) {
        this.project = str;
    }

    public String toString() {
        return "CreateThermometryResponse{id='" + this.id + '\'' + ", robot='" + this.robot + '\'' + ", face='" + this.face + '\'' + ", temperature=" + this.temperature + ", project='" + this.project + '\'' + ", createtime=" + this.createtime + ", person=" + this.person + '}';
    }
}
