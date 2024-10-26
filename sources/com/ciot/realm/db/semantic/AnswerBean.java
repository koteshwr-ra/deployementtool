package com.ciot.realm.db.semantic;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.com_ciot_realm_db_semantic_AnswerBeanRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class AnswerBean extends RealmObject implements com_ciot_realm_db_semantic_AnswerBeanRealmProxyInterface {
    private ActionBean action;
    private String ans;
    private RealmList<String> buttons;
    private RealmList<String> list;
    private String music;
    private String picture;
    private RealmList<String> recommendations;
    private String sessionState;
    private String showans;
    private String text;
    private String title;
    private int type;
    private String url;
    private String video;
    private String videocover;

    public ActionBean realmGet$action() {
        return this.action;
    }

    public String realmGet$ans() {
        return this.ans;
    }

    public RealmList realmGet$buttons() {
        return this.buttons;
    }

    public RealmList realmGet$list() {
        return this.list;
    }

    public String realmGet$music() {
        return this.music;
    }

    public String realmGet$picture() {
        return this.picture;
    }

    public RealmList realmGet$recommendations() {
        return this.recommendations;
    }

    public String realmGet$sessionState() {
        return this.sessionState;
    }

    public String realmGet$showans() {
        return this.showans;
    }

    public String realmGet$text() {
        return this.text;
    }

    public String realmGet$title() {
        return this.title;
    }

    public int realmGet$type() {
        return this.type;
    }

    public String realmGet$url() {
        return this.url;
    }

    public String realmGet$video() {
        return this.video;
    }

    public String realmGet$videocover() {
        return this.videocover;
    }

    public void realmSet$action(ActionBean actionBean) {
        this.action = actionBean;
    }

    public void realmSet$ans(String str) {
        this.ans = str;
    }

    public void realmSet$buttons(RealmList realmList) {
        this.buttons = realmList;
    }

    public void realmSet$list(RealmList realmList) {
        this.list = realmList;
    }

    public void realmSet$music(String str) {
        this.music = str;
    }

    public void realmSet$picture(String str) {
        this.picture = str;
    }

    public void realmSet$recommendations(RealmList realmList) {
        this.recommendations = realmList;
    }

    public void realmSet$sessionState(String str) {
        this.sessionState = str;
    }

    public void realmSet$showans(String str) {
        this.showans = str;
    }

    public void realmSet$text(String str) {
        this.text = str;
    }

    public void realmSet$title(String str) {
        this.title = str;
    }

    public void realmSet$type(int i) {
        this.type = i;
    }

    public void realmSet$url(String str) {
        this.url = str;
    }

    public void realmSet$video(String str) {
        this.video = str;
    }

    public void realmSet$videocover(String str) {
        this.videocover = str;
    }

    public String getVideocover() {
        return realmGet$videocover();
    }

    public void setVideocover(String str) {
        realmSet$videocover(str);
    }

    public AnswerBean() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public ActionBean getAction() {
        return realmGet$action();
    }

    public void setAction(ActionBean actionBean) {
        realmSet$action(actionBean);
    }

    public String getShowans() {
        return realmGet$showans();
    }

    public void setShowans(String str) {
        realmSet$showans(str);
    }

    public String getAns() {
        return realmGet$ans();
    }

    public void setAns(String str) {
        realmSet$ans(str);
    }

    public RealmList<String> getButtons() {
        return realmGet$buttons();
    }

    public void setButtons(RealmList<String> realmList) {
        realmSet$buttons(realmList);
    }

    public RealmList<String> getList() {
        return realmGet$list();
    }

    public void setList(RealmList<String> realmList) {
        realmSet$list(realmList);
    }

    public String getMusic() {
        return realmGet$music();
    }

    public void setMusic(String str) {
        realmSet$music(str);
    }

    public String getPicture() {
        return realmGet$picture();
    }

    public void setPicture(String str) {
        realmSet$picture(str);
    }

    public RealmList<String> getRecommendations() {
        return realmGet$recommendations();
    }

    public void setRecommendations(RealmList<String> realmList) {
        realmSet$recommendations(realmList);
    }

    public String getSessionState() {
        return realmGet$sessionState();
    }

    public void setSessionState(String str) {
        realmSet$sessionState(str);
    }

    public int getType() {
        return realmGet$type();
    }

    public void setType(int i) {
        realmSet$type(i);
    }

    public String getTitle() {
        return realmGet$title();
    }

    public void setTitle(String str) {
        realmSet$title(str);
    }

    public String getText() {
        return realmGet$text();
    }

    public void setText(String str) {
        realmSet$text(str);
    }

    public String getVideo() {
        return realmGet$video();
    }

    public void setVideo(String str) {
        realmSet$video(str);
    }

    public String getUrl() {
        return realmGet$url();
    }

    public void setUrl(String str) {
        realmSet$url(str);
    }

    public String toString() {
        return "AnswerBean{action=" + realmGet$action() + ", ans='" + realmGet$ans() + '\'' + ", showans='" + realmGet$showans() + '\'' + ", buttons=" + realmGet$buttons() + ", list=" + realmGet$list() + ", music='" + realmGet$music() + '\'' + ", picture='" + realmGet$picture() + '\'' + ", recommendations=" + realmGet$recommendations() + ", sessionState='" + realmGet$sessionState() + '\'' + ", type=" + realmGet$type() + ", title='" + realmGet$title() + '\'' + ", text='" + realmGet$text() + '\'' + ", video='" + realmGet$video() + '\'' + ", videocover='" + realmGet$videocover() + '\'' + '}';
    }
}
