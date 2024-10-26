package com.ciot.networklib.bean;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\f\u001a\u00020\u0004H\u0016R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\b¨\u0006\r"}, d2 = {"Lcom/ciot/networklib/bean/TestBean;", "", "()V", "error_reason", "", "getError_reason", "()Ljava/lang/String;", "setError_reason", "(Ljava/lang/String;)V", "success", "getSuccess", "setSuccess", "toString", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: TestBean.kt */
public final class TestBean {
    private String error_reason;
    private String success;

    public final String getSuccess() {
        return this.success;
    }

    public final void setSuccess(String str) {
        this.success = str;
    }

    public final String getError_reason() {
        return this.error_reason;
    }

    public final void setError_reason(String str) {
        this.error_reason = str;
    }

    public String toString() {
        return "TestBean(success=" + this.success + ", error_reason=" + this.error_reason + ')';
    }
}
