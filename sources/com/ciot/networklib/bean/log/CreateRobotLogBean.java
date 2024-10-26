package com.ciot.networklib.bean.log;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0011\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001a\u0010\u0012\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001c\u0010\u0015\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\f\"\u0004\b\u0017\u0010\u000eR\u001c\u0010\u0018\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\f\"\u0004\b\u001a\u0010\u000e¨\u0006\u001b"}, d2 = {"Lcom/ciot/networklib/bean/log/CreateRobotLogBean;", "", "()V", "begin", "", "getBegin", "()J", "setBegin", "(J)V", "content", "", "getContent", "()Ljava/lang/String;", "setContent", "(Ljava/lang/String;)V", "description", "getDescription", "setDescription", "end", "getEnd", "setEnd", "name", "getName", "setName", "robot", "getRobot", "setRobot", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: CreateRobotLogBean.kt */
public final class CreateRobotLogBean {
    private long begin;
    private String content;
    private String description;
    private long end;
    private String name;
    private String robot;

    public final String getRobot() {
        return this.robot;
    }

    public final void setRobot(String str) {
        this.robot = str;
    }

    public final long getBegin() {
        return this.begin;
    }

    public final void setBegin(long j) {
        this.begin = j;
    }

    public final long getEnd() {
        return this.end;
    }

    public final void setEnd(long j) {
        this.end = j;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        this.name = str;
    }

    public final String getContent() {
        return this.content;
    }

    public final void setContent(String str) {
        this.content = str;
    }

    public final String getDescription() {
        return this.description;
    }

    public final void setDescription(String str) {
        this.description = str;
    }
}
