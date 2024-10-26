package com.ciot.networklib.bean;

import com.ciot.base.config.SpeechConstants;
import com.ciot.base.constant.NetConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001:\u0001)B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004J\b\u0010\u0012\u001a\u0004\u0018\u00010\u0004J\b\u0010\u0013\u001a\u0004\u0018\u00010\u0007J\b\u0010\u0014\u001a\u0004\u0018\u00010\u0004J\b\u0010\u0015\u001a\u0004\u0018\u00010\u0004J\r\u0010\u0016\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\u0017J\u0006\u0010\u0018\u001a\u00020\u0004J\b\u0010\u0019\u001a\u0004\u0018\u00010\u0004J\b\u0010\u001a\u001a\u0004\u0018\u00010\u0004J\u0006\u0010\u001b\u001a\u00020\u000bJ\b\u0010\u001c\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\u001d\u001a\u00020\u001e2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\u001f\u001a\u00020\u001e2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004J\u0010\u0010 \u001a\u00020\u001e2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007J\u000e\u0010!\u001a\u00020\u001e2\u0006\u0010\b\u001a\u00020\u0004J\u0010\u0010\"\u001a\u00020\u001e2\b\u0010\t\u001a\u0004\u0018\u00010\u0004J\u000e\u0010#\u001a\u00020\u001e2\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010$\u001a\u00020\u001e2\u0006\u0010\f\u001a\u00020\u0004J\u0010\u0010%\u001a\u00020\u001e2\b\u0010\r\u001a\u0004\u0018\u00010\u0004J\u0010\u0010&\u001a\u00020\u001e2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0004J\u000e\u0010'\u001a\u00020\u001e2\u0006\u0010\u000f\u001a\u00020\u000bJ\u0010\u0010(\u001a\u00020\u001e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/ciot/networklib/bean/CiotRequestBean;", "", "()V", "area", "", "deviceid", "inviterData", "Lcom/ciot/networklib/bean/CiotRequestBean$InviterDataBean;", "pageid", "project", "robottype", "", "sessionState", "text", "token", "type", "user", "getArea", "getDeviceid", "getInviterData", "getPageId", "getProject", "getRobottype", "()Ljava/lang/Integer;", "getSessionState", "getText", "getToken", "getType", "getUser", "setArea", "", "setDeviceid", "setInviterData", "setPageId", "setProject", "setRobottype", "setSessionState", "setText", "setToken", "setType", "setUser", "InviterDataBean", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: CiotRequestBean.kt */
public final class CiotRequestBean {
    private String area;
    private String deviceid;
    private InviterDataBean inviterData;
    private String pageid = NetConstant.PAGE_ID_HOME;
    private String project;
    private int robottype = 5;
    private String sessionState = SpeechConstants.SESSION_STATE_IDLE;
    private String text;
    private String token;
    private int type;
    private String user;

    public final int getType() {
        return this.type;
    }

    public final void setType(int i) {
        this.type = i;
    }

    public final String getProject() {
        return this.project;
    }

    public final void setProject(String str) {
        this.project = str;
    }

    public final String getDeviceid() {
        return this.deviceid;
    }

    public final void setDeviceid(String str) {
        this.deviceid = str;
    }

    public final String getArea() {
        return this.area;
    }

    public final void setArea(String str) {
        this.area = str;
    }

    public final String getUser() {
        return this.user;
    }

    public final void setUser(String str) {
        this.user = str;
    }

    public final String getToken() {
        return this.token;
    }

    public final void setToken(String str) {
        this.token = str;
    }

    public final String getSessionState() {
        return this.sessionState;
    }

    public final void setSessionState(String str) {
        Intrinsics.checkNotNullParameter(str, "sessionState");
        this.sessionState = str;
    }

    public final String getText() {
        return this.text;
    }

    public final void setText(String str) {
        this.text = str;
    }

    public final String getPageId() {
        return this.pageid;
    }

    public final void setPageId(String str) {
        Intrinsics.checkNotNullParameter(str, "pageid");
        this.pageid = str;
    }

    public final Integer getRobottype() {
        return Integer.valueOf(this.robottype);
    }

    public final void setRobottype(int i) {
        this.robottype = i;
    }

    public final InviterDataBean getInviterData() {
        return this.inviterData;
    }

    public final void setInviterData(InviterDataBean inviterDataBean) {
        this.inviterData = inviterDataBean;
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0018\u001a\u00020\u0004H\u0016R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001a\u0010\u0012\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006\u0019"}, d2 = {"Lcom/ciot/networklib/bean/CiotRequestBean$InviterDataBean;", "", "()V", "inviter", "", "getInviter", "()Ljava/lang/String;", "setInviter", "(Ljava/lang/String;)V", "inviterCompany", "getInviterCompany", "setInviterCompany", "inviterPhone", "getInviterPhone", "setInviterPhone", "visitor", "getVisitor", "setVisitor", "visitorSex", "", "getVisitorSex", "()I", "setVisitorSex", "(I)V", "toString", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: CiotRequestBean.kt */
    public static final class InviterDataBean {
        private String inviter;
        private String inviterCompany;
        private String inviterPhone;
        private String visitor;
        private int visitorSex;

        public final String getVisitor() {
            return this.visitor;
        }

        public final void setVisitor(String str) {
            this.visitor = str;
        }

        public final int getVisitorSex() {
            return this.visitorSex;
        }

        public final void setVisitorSex(int i) {
            this.visitorSex = i;
        }

        public final String getInviter() {
            return this.inviter;
        }

        public final void setInviter(String str) {
            this.inviter = str;
        }

        public final String getInviterCompany() {
            return this.inviterCompany;
        }

        public final void setInviterCompany(String str) {
            this.inviterCompany = str;
        }

        public final String getInviterPhone() {
            return this.inviterPhone;
        }

        public final void setInviterPhone(String str) {
            this.inviterPhone = str;
        }

        public String toString() {
            return "InviterDataBean(visitor=" + this.visitor + ", visitorSex=" + this.visitorSex + ", inviter=" + this.inviter + ", inviterCompany=" + this.inviterCompany + ", inviterPhone=" + this.inviterPhone + ')';
        }
    }
}
