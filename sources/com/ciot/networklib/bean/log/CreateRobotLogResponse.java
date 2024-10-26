package com.ciot.networklib.bean.log;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b$\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010.\u001a\u00020\u000bH\u0016R\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0010\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0011\u0010\u0006\"\u0004\b\u0012\u0010\bR\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\r\"\u0004\b\u0015\u0010\u000fR\u001e\u0010\u0016\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0017\u0010\u0006\"\u0004\b\u0018\u0010\bR\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\r\"\u0004\b\u001b\u0010\u000fR\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\r\"\u0004\b\u001e\u0010\u000fR\u001c\u0010\u001f\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\r\"\u0004\b!\u0010\u000fR\u001c\u0010\"\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\r\"\u0004\b$\u0010\u000fR\u001c\u0010%\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\r\"\u0004\b'\u0010\u000fR\u001c\u0010(\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\r\"\u0004\b*\u0010\u000fR\u001c\u0010+\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\r\"\u0004\b-\u0010\u000f¨\u0006/"}, d2 = {"Lcom/ciot/networklib/bean/log/CreateRobotLogResponse;", "", "()V", "begin", "", "getBegin", "()Ljava/lang/Integer;", "setBegin", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "content", "", "getContent", "()Ljava/lang/String;", "setContent", "(Ljava/lang/String;)V", "createtime", "getCreatetime", "setCreatetime", "description", "getDescription", "setDescription", "end", "getEnd", "setEnd", "id", "getId", "setId", "mdpath", "getMdpath", "setMdpath", "name", "getName", "setName", "path", "getPath", "setPath", "resource", "getResource", "setResource", "robot", "getRobot", "setRobot", "user", "getUser", "setUser", "toString", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: CreateRobotLogResponse.kt */
public final class CreateRobotLogResponse {
    private Integer begin;
    private String content;
    private Integer createtime;
    private String description;
    private Integer end;
    private String id;
    private String mdpath;
    private String name;
    private String path;
    private String resource;
    private String robot;
    private String user;

    public final Integer getBegin() {
        return this.begin;
    }

    public final void setBegin(Integer num) {
        this.begin = num;
    }

    public final String getContent() {
        return this.content;
    }

    public final void setContent(String str) {
        this.content = str;
    }

    public final Integer getCreatetime() {
        return this.createtime;
    }

    public final void setCreatetime(Integer num) {
        this.createtime = num;
    }

    public final String getDescription() {
        return this.description;
    }

    public final void setDescription(String str) {
        this.description = str;
    }

    public final Integer getEnd() {
        return this.end;
    }

    public final void setEnd(Integer num) {
        this.end = num;
    }

    public final String getId() {
        return this.id;
    }

    public final void setId(String str) {
        this.id = str;
    }

    public final String getMdpath() {
        return this.mdpath;
    }

    public final void setMdpath(String str) {
        this.mdpath = str;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        this.name = str;
    }

    public final String getPath() {
        return this.path;
    }

    public final void setPath(String str) {
        this.path = str;
    }

    public final String getResource() {
        return this.resource;
    }

    public final void setResource(String str) {
        this.resource = str;
    }

    public final String getRobot() {
        return this.robot;
    }

    public final void setRobot(String str) {
        this.robot = str;
    }

    public final String getUser() {
        return this.user;
    }

    public final void setUser(String str) {
        this.user = str;
    }

    public String toString() {
        return "CreateRobotLogResponse(begin=" + this.begin + ", content=" + this.content + ", createtime=" + this.createtime + ", description=" + this.description + ", end=" + this.end + ", id=" + this.id + ", mdpath=" + this.mdpath + ", name=" + this.name + ", path=" + this.path + ", resource=" + this.resource + ", robot=" + this.robot + ", user=" + this.user + ')';
    }
}
