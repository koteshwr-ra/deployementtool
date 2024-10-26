package com.ciot.networklib.bean.version;

import java.io.Serializable;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0010\b\n\u0002\b\u0012\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010@\u001a\u00020\u0004H\u0016R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0006R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0006\"\u0004\b\u0016\u0010\bR\u001a\u0010\u0017\u001a\u00020\u0018X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0006\"\u0004\b\u001f\u0010\bR\u001c\u0010 \u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0006\"\u0004\b\"\u0010\bR\u001c\u0010#\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0006\"\u0004\b%\u0010\bR\u001c\u0010&\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u0006\"\u0004\b(\u0010\bR\u001c\u0010)\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u0006\"\u0004\b+\u0010\bR\u0013\u0010,\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u0006R\u001a\u0010.\u001a\u00020/X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u001c\u00104\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\u0006\"\u0004\b6\u0010\bR\u001a\u00107\u001a\u00020/X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00101\"\u0004\b9\u00103R\u001c\u0010:\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010\u0006\"\u0004\b<\u0010\bR\u001c\u0010=\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010\u0006\"\u0004\b?\u0010\b¨\u0006A"}, d2 = {"Lcom/ciot/networklib/bean/version/VersionBean;", "Ljava/io/Serializable;", "()V", "content", "", "getContent", "()Ljava/lang/String;", "setContent", "(Ljava/lang/String;)V", "createtime", "", "getCreatetime", "()J", "setCreatetime", "(J)V", "id", "getId", "setId", "key", "getKey", "kind", "getKind", "setKind", "mandatory", "", "getMandatory", "()Z", "setMandatory", "(Z)V", "mark", "getMark", "setMark", "md5", "getMd5", "setMd5", "metaid", "getMetaid", "setMetaid", "owner", "getOwner", "setOwner", "packageName", "getPackageName", "setPackageName", "path", "getPath", "type", "", "getType", "()I", "setType", "(I)V", "user", "getUser", "setUser", "versionCode", "getVersionCode", "setVersionCode", "versionName", "getVersionName", "setVersionName", "visible", "getVisible", "setVisible", "toString", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: VersionBean.kt */
public final class VersionBean implements Serializable {
    private String content;
    private long createtime;
    private String id;
    private final String key;
    private String kind;
    private boolean mandatory;
    private String mark;
    private String md5;
    private String metaid;
    private String owner;
    private String packageName;
    private final String path;
    private int type;
    private String user;
    private int versionCode;
    private String versionName;
    private String visible;

    public final String getKey() {
        return this.key;
    }

    public final String getPath() {
        return this.path;
    }

    public final String getContent() {
        return this.content;
    }

    public final void setContent(String str) {
        this.content = str;
    }

    public final String getMark() {
        return this.mark;
    }

    public final void setMark(String str) {
        this.mark = str;
    }

    public final String getMetaid() {
        return this.metaid;
    }

    public final void setMetaid(String str) {
        this.metaid = str;
    }

    public final String getKind() {
        return this.kind;
    }

    public final void setKind(String str) {
        this.kind = str;
    }

    public final int getType() {
        return this.type;
    }

    public final void setType(int i) {
        this.type = i;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final void setPackageName(String str) {
        this.packageName = str;
    }

    public final String getVersionName() {
        return this.versionName;
    }

    public final void setVersionName(String str) {
        this.versionName = str;
    }

    public final boolean getMandatory() {
        return this.mandatory;
    }

    public final void setMandatory(boolean z) {
        this.mandatory = z;
    }

    public final String getVisible() {
        return this.visible;
    }

    public final void setVisible(String str) {
        this.visible = str;
    }

    public final String getUser() {
        return this.user;
    }

    public final void setUser(String str) {
        this.user = str;
    }

    public final String getOwner() {
        return this.owner;
    }

    public final void setOwner(String str) {
        this.owner = str;
    }

    public final String getMd5() {
        return this.md5;
    }

    public final void setMd5(String str) {
        this.md5 = str;
    }

    public final int getVersionCode() {
        return this.versionCode;
    }

    public final void setVersionCode(int i) {
        this.versionCode = i;
    }

    public final long getCreatetime() {
        return this.createtime;
    }

    public final void setCreatetime(long j) {
        this.createtime = j;
    }

    public final String getId() {
        return this.id;
    }

    public final void setId(String str) {
        this.id = str;
    }

    public String toString() {
        return "VersionBean(key=" + this.key + ", content=" + this.content + ", mark=" + this.mark + ", metaid=" + this.metaid + ", kind=" + this.kind + ", type=" + this.type + ", packageName=" + this.packageName + ", versionName=" + this.versionName + ", mandatory=" + this.mandatory + ", visible=" + this.visible + ", user=" + this.user + ", owner=" + this.owner + ", md5=" + this.md5 + ", versionCode=" + this.versionCode + ", createtime=" + this.createtime + ", id=" + this.id + ')';
    }
}
