package com.ciot.networklib.bean.version.record;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0014\"\u0004\b\u0019\u0010\u0016R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0006\"\u0004\b\u001c\u0010\bR\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0006\"\u0004\b\u001f\u0010\b¨\u0006 "}, d2 = {"Lcom/ciot/networklib/bean/version/record/UpdateVersionBean;", "", "()V", "error", "", "getError", "()Ljava/lang/String;", "setError", "(Ljava/lang/String;)V", "id", "getId", "setId", "isSuccessful", "", "()Z", "setSuccessful", "(Z)V", "versionCode", "", "getVersionCode", "()I", "setVersionCode", "(I)V", "versionCodeTarget", "getVersionCodeTarget", "setVersionCodeTarget", "versionName", "getVersionName", "setVersionName", "versionNameTarget", "getVersionNameTarget", "setVersionNameTarget", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: UpdateVersionBean.kt */
public final class UpdateVersionBean {
    private String error;
    private String id;
    private boolean isSuccessful;
    private int versionCode;
    private int versionCodeTarget;
    private String versionName;
    private String versionNameTarget;

    public final String getId() {
        return this.id;
    }

    public final void setId(String str) {
        this.id = str;
    }

    public final int getVersionCode() {
        return this.versionCode;
    }

    public final void setVersionCode(int i) {
        this.versionCode = i;
    }

    public final String getVersionName() {
        return this.versionName;
    }

    public final void setVersionName(String str) {
        this.versionName = str;
    }

    public final String getVersionNameTarget() {
        return this.versionNameTarget;
    }

    public final void setVersionNameTarget(String str) {
        this.versionNameTarget = str;
    }

    public final int getVersionCodeTarget() {
        return this.versionCodeTarget;
    }

    public final void setVersionCodeTarget(int i) {
        this.versionCodeTarget = i;
    }

    public final boolean isSuccessful() {
        return this.isSuccessful;
    }

    public final void setSuccessful(boolean z) {
        this.isSuccessful = z;
    }

    public final String getError() {
        return this.error;
    }

    public final void setError(String str) {
        this.error = str;
    }
}
