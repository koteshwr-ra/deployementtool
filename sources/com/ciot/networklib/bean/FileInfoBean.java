package com.ciot.networklib.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\f\u001a\u0004\u0018\u00010\u0004J\b\u0010\r\u001a\u0004\u0018\u00010\u0004J\b\u0010\u000e\u001a\u0004\u0018\u00010\u0004J\u0006\u0010\u000f\u001a\u00020\bJ\u0006\u0010\u0010\u001a\u00020\nJ\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\u0012\u001a\u00020\u00132\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\u0014\u001a\u00020\u00132\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\u0015\u001a\u00020\u00132\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004J\u000e\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\u0017\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u0018\u001a\u00020\u00132\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004J\b\u0010\u0019\u001a\u00020\u0004H\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/ciot/networklib/bean/FileInfoBean;", "", "()V", "fid", "", "fmd5", "id", "insTime", "", "size", "", "type", "getFid", "getFmd5", "getId", "getInsTime", "getSize", "getType", "setFid", "", "setFmd5", "setId", "setInsTime", "setSize", "setType", "toString", "Companion", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: FileInfoBean.kt */
public final class FileInfoBean {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final FileInfoBean EMPTY = new FileInfoBean();
    private String fid;
    private String fmd5;
    private String id;
    private long insTime;
    private int size;
    private String type;

    public final String getType() {
        return this.type;
    }

    public final void setType(String str) {
        this.type = str;
    }

    public final String getFid() {
        return this.fid;
    }

    public final void setFid(String str) {
        this.fid = str;
    }

    public final String getFmd5() {
        return this.fmd5;
    }

    public final void setFmd5(String str) {
        this.fmd5 = str;
    }

    public final long getInsTime() {
        return this.insTime;
    }

    public final void setInsTime(long j) {
        this.insTime = j;
    }

    public final int getSize() {
        return this.size;
    }

    public final void setSize(int i) {
        this.size = i;
    }

    public final String getId() {
        return this.id;
    }

    public final void setId(String str) {
        this.id = str;
    }

    public String toString() {
        return "FileInfoBean(type=" + this.type + ", fid=" + this.fid + ", fmd5=" + this.fmd5 + ", insTime=" + this.insTime + ", size=" + this.size + ", id=" + this.id + ')';
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/ciot/networklib/bean/FileInfoBean$Companion;", "", "()V", "EMPTY", "Lcom/ciot/networklib/bean/FileInfoBean;", "getEMPTY", "()Lcom/ciot/networklib/bean/FileInfoBean;", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: FileInfoBean.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FileInfoBean getEMPTY() {
            return FileInfoBean.EMPTY;
        }
    }
}
