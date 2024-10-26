package com.ciot.networklib.bean;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0012\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u001e\u001a\u00020\u0004H\u0016R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\bR\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0006\"\u0004\b\u001a\u0010\bR\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0006\"\u0004\b\u001d\u0010\b¨\u0006\u001f"}, d2 = {"Lcom/ciot/networklib/bean/AuthorizationCodeTimerTaskBean;", "", "()V", "cycletype", "", "getCycletype", "()Ljava/lang/String;", "setCycletype", "(Ljava/lang/String;)V", "description", "getDescription", "setDescription", "enable", "", "getEnable", "()Z", "setEnable", "(Z)V", "taskdatas", "getTaskdatas", "setTaskdatas", "taskend", "getTaskend", "setTaskend", "taskstart", "getTaskstart", "setTaskstart", "tasktype", "getTasktype", "setTasktype", "toString", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: AuthorizationCodeTimerTaskBean.kt */
public final class AuthorizationCodeTimerTaskBean {
    private String cycletype;
    private String description;
    private boolean enable;
    private String taskdatas;
    private String taskend;
    private String taskstart;
    private String tasktype;

    public final String getTasktype() {
        return this.tasktype;
    }

    public final void setTasktype(String str) {
        this.tasktype = str;
    }

    public final String getCycletype() {
        return this.cycletype;
    }

    public final void setCycletype(String str) {
        this.cycletype = str;
    }

    public final String getTaskdatas() {
        return this.taskdatas;
    }

    public final void setTaskdatas(String str) {
        this.taskdatas = str;
    }

    public final String getTaskstart() {
        return this.taskstart;
    }

    public final void setTaskstart(String str) {
        this.taskstart = str;
    }

    public final String getTaskend() {
        return this.taskend;
    }

    public final void setTaskend(String str) {
        this.taskend = str;
    }

    public final boolean getEnable() {
        return this.enable;
    }

    public final void setEnable(boolean z) {
        this.enable = z;
    }

    public final String getDescription() {
        return this.description;
    }

    public final void setDescription(String str) {
        this.description = str;
    }

    public String toString() {
        return "AuthorizationCodeTimerTaskBean(tasktype=" + this.tasktype + ", cycletype=" + this.cycletype + ", taskdatas=" + this.taskdatas + ", taskstart=" + this.taskstart + ", taskend=" + this.taskend + ", enable=" + this.enable + ", description=" + this.description + ')';
    }
}
