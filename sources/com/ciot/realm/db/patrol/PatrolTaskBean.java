package com.ciot.realm.db.patrol;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;
import java.io.Serializable;

public class PatrolTaskBean extends RealmObject implements Serializable, com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxyInterface {
    private String area;
    private Place backplace;
    private String brief;
    private int count;
    @PrimaryKey
    private String id;
    private RealmList<Line> lines;
    private String map;
    private Message message;
    private String name;
    private Plan plan;
    private TransitionBean prologud;
    private Resource resource;
    private String robotid;

    public String realmGet$area() {
        return this.area;
    }

    public Place realmGet$backplace() {
        return this.backplace;
    }

    public String realmGet$brief() {
        return this.brief;
    }

    public int realmGet$count() {
        return this.count;
    }

    public String realmGet$id() {
        return this.id;
    }

    public RealmList realmGet$lines() {
        return this.lines;
    }

    public String realmGet$map() {
        return this.map;
    }

    public Message realmGet$message() {
        return this.message;
    }

    public String realmGet$name() {
        return this.name;
    }

    public Plan realmGet$plan() {
        return this.plan;
    }

    public TransitionBean realmGet$prologud() {
        return this.prologud;
    }

    public Resource realmGet$resource() {
        return this.resource;
    }

    public String realmGet$robotid() {
        return this.robotid;
    }

    public void realmSet$area(String str) {
        this.area = str;
    }

    public void realmSet$backplace(Place place) {
        this.backplace = place;
    }

    public void realmSet$brief(String str) {
        this.brief = str;
    }

    public void realmSet$count(int i) {
        this.count = i;
    }

    public void realmSet$id(String str) {
        this.id = str;
    }

    public void realmSet$lines(RealmList realmList) {
        this.lines = realmList;
    }

    public void realmSet$map(String str) {
        this.map = str;
    }

    public void realmSet$message(Message message2) {
        this.message = message2;
    }

    public void realmSet$name(String str) {
        this.name = str;
    }

    public void realmSet$plan(Plan plan2) {
        this.plan = plan2;
    }

    public void realmSet$prologud(TransitionBean transitionBean) {
        this.prologud = transitionBean;
    }

    public void realmSet$resource(Resource resource2) {
        this.resource = resource2;
    }

    public void realmSet$robotid(String str) {
        this.robotid = str;
    }

    public PatrolTaskBean() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getId() {
        return realmGet$id();
    }

    public void setId(String str) {
        realmSet$id(str);
    }

    public String getRobotid() {
        return realmGet$robotid();
    }

    public void setRobotid(String str) {
        realmSet$robotid(str);
    }

    public String getArea() {
        return realmGet$area();
    }

    public void setArea(String str) {
        realmSet$area(str);
    }

    public String getName() {
        return realmGet$name();
    }

    public void setName(String str) {
        realmSet$name(str);
    }

    public String getBrief() {
        return realmGet$brief();
    }

    public void setBrief(String str) {
        realmSet$brief(str);
    }

    public Resource getResource() {
        return realmGet$resource();
    }

    public void setResource(Resource resource2) {
        realmSet$resource(resource2);
    }

    public Plan getPlan() {
        return realmGet$plan();
    }

    public void setPlan(Plan plan2) {
        realmSet$plan(plan2);
    }

    public int getCount() {
        return realmGet$count();
    }

    public void setCount(int i) {
        realmSet$count(i);
    }

    public String getMap() {
        return realmGet$map();
    }

    public void setMap(String str) {
        realmSet$map(str);
    }

    public Place getBackplace() {
        return realmGet$backplace();
    }

    public void setBackplace(Place place) {
        realmSet$backplace(place);
    }

    public TransitionBean getPrologud() {
        return realmGet$prologud();
    }

    public void setPrologud(TransitionBean transitionBean) {
        realmSet$prologud(transitionBean);
    }

    public RealmList<Line> getLines() {
        return realmGet$lines();
    }

    public void setLines(RealmList<Line> realmList) {
        realmSet$lines(realmList);
    }

    public Message getMessage() {
        return realmGet$message();
    }

    public void setMessage(Message message2) {
        realmSet$message(message2);
    }

    public String toString() {
        return "PatrolTaskBean{id='" + realmGet$id() + '\'' + ", robotid='" + realmGet$robotid() + '\'' + ", area='" + realmGet$area() + '\'' + ", name='" + realmGet$name() + '\'' + ", brief='" + realmGet$brief() + '\'' + ", resource=" + realmGet$resource() + ", plan=" + realmGet$plan() + ", count=" + realmGet$count() + ", map='" + realmGet$map() + '\'' + ", backplace=" + realmGet$backplace() + ", prologud=" + realmGet$prologud() + ", lines=" + realmGet$lines() + ", message=" + realmGet$message() + '}';
    }
}
