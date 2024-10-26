package com.ciot.networklib.bean.version.record;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0015\u0018\u00002\u00020\u0001:\u0001,B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001a\u0010\u0012\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001c\u0010\u001d\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\f\"\u0004\b\u001f\u0010\u000eR\u001a\u0010 \u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0006\"\u0004\b\"\u0010\bR\u001a\u0010#\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0006\"\u0004\b%\u0010\bR\u001c\u0010&\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\f\"\u0004\b(\u0010\u000eR\u001c\u0010)\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\f\"\u0004\b+\u0010\u000e¨\u0006-"}, d2 = {"Lcom/ciot/networklib/bean/version/record/UpdateVersionRecordResponse;", "", "()V", "createtime", "", "getCreatetime", "()I", "setCreatetime", "(I)V", "error", "", "getError", "()Ljava/lang/String;", "setError", "(Ljava/lang/String;)V", "id", "getId", "setId", "isSuccessful", "", "()Z", "setSuccessful", "(Z)V", "robot", "Lcom/ciot/networklib/bean/version/record/UpdateVersionRecordResponse$RobotBean;", "getRobot", "()Lcom/ciot/networklib/bean/version/record/UpdateVersionRecordResponse$RobotBean;", "setRobot", "(Lcom/ciot/networklib/bean/version/record/UpdateVersionRecordResponse$RobotBean;)V", "user", "getUser", "setUser", "versionCode", "getVersionCode", "setVersionCode", "versionCodeTarget", "getVersionCodeTarget", "setVersionCodeTarget", "versionName", "getVersionName", "setVersionName", "versionNameTarget", "getVersionNameTarget", "setVersionNameTarget", "RobotBean", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: UpdateVersionRecordResponse.kt */
public final class UpdateVersionRecordResponse {
    private int createtime;
    private String error;
    private String id;
    private boolean isSuccessful;
    private RobotBean robot;
    private String user;
    private int versionCode;
    private int versionCodeTarget;
    private String versionName;
    private String versionNameTarget;

    public final RobotBean getRobot() {
        return this.robot;
    }

    public final void setRobot(RobotBean robotBean) {
        this.robot = robotBean;
    }

    public final String getUser() {
        return this.user;
    }

    public final void setUser(String str) {
        this.user = str;
    }

    public final String getVersionName() {
        return this.versionName;
    }

    public final void setVersionName(String str) {
        this.versionName = str;
    }

    public final int getVersionCode() {
        return this.versionCode;
    }

    public final void setVersionCode(int i) {
        this.versionCode = i;
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

    public final int getCreatetime() {
        return this.createtime;
    }

    public final void setCreatetime(int i) {
        this.createtime = i;
    }

    public final String getId() {
        return this.id;
    }

    public final void setId(String str) {
        this.id = str;
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b-\u0018\u00002\u00020\u0001:\u0001WB\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001a\u0010\u0012\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0006\"\u0004\b\u001a\u0010\bR\u001a\u0010\u001b\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0015\"\u0004\b\u001d\u0010\u0017R\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0006\"\u0004\b \u0010\bR\u001c\u0010!\u001a\u0004\u0018\u00010\"X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001c\u0010'\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0006\"\u0004\b)\u0010\bR\u001a\u0010*\u001a\u00020+X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u001c\u00100\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u0006\"\u0004\b2\u0010\bR\u001c\u00103\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u0006\"\u0004\b5\u0010\bR\u001c\u00106\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\u0006\"\u0004\b8\u0010\bR\u001c\u00109\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\u0006\"\u0004\b;\u0010\bR\u001a\u0010<\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\u0015\"\u0004\b>\u0010\u0017R\u001c\u0010?\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010\u0006\"\u0004\bA\u0010\bR\u001c\u0010B\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010\u0006\"\u0004\bD\u0010\bR\u001a\u0010E\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010\u0015\"\u0004\bG\u0010\u0017R\u001c\u0010H\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010\u0006\"\u0004\bJ\u0010\bR\u001a\u0010K\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010\u0015\"\u0004\bM\u0010\u0017R\u001c\u0010N\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bO\u0010\u0006\"\u0004\bP\u0010\bR\u001a\u0010Q\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u0010\u0015\"\u0004\bS\u0010\u0017R\u001a\u0010T\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bU\u0010\u0015\"\u0004\bV\u0010\u0017¨\u0006X"}, d2 = {"Lcom/ciot/networklib/bean/version/record/UpdateVersionRecordResponse$RobotBean;", "", "()V", "account", "", "getAccount", "()Ljava/lang/String;", "setAccount", "(Ljava/lang/String;)V", "address", "getAddress", "setAddress", "alias", "getAlias", "setAlias", "area", "getArea", "setArea", "begin", "", "getBegin", "()I", "setBegin", "(I)V", "description", "getDescription", "setDescription", "end", "getEnd", "setEnd", "group", "getGroup", "setGroup", "hardware", "Lcom/ciot/networklib/bean/version/record/UpdateVersionRecordResponse$RobotBean$HardwareBean;", "getHardware", "()Lcom/ciot/networklib/bean/version/record/UpdateVersionRecordResponse$RobotBean$HardwareBean;", "setHardware", "(Lcom/ciot/networklib/bean/version/record/UpdateVersionRecordResponse$RobotBean$HardwareBean;)V", "id", "getId", "setId", "link", "", "getLink", "()Z", "setLink", "(Z)V", "mfrs", "getMfrs", "setMfrs", "model", "getModel", "setModel", "name", "getName", "setName", "projectid", "getProjectid", "setProjectid", "registerTime", "getRegisterTime", "setRegisterTime", "sid", "getSid", "setSid", "status", "getStatus", "setStatus", "time", "getTime", "setTime", "type", "getType", "setType", "updateTime", "getUpdateTime", "setUpdateTime", "version", "getVersion", "setVersion", "x", "getX", "setX", "y", "getY", "setY", "HardwareBean", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: UpdateVersionRecordResponse.kt */
    public static final class RobotBean {
        private String account;
        private String address;
        private String alias;
        private String area;
        private int begin;
        private String description;
        private int end;
        private String group;
        private HardwareBean hardware;
        private String id;
        private boolean link;
        private String mfrs;
        private String model;
        private String name;
        private String projectid;
        private int registerTime;
        private String sid;
        private String status;
        private int time;
        private String type;
        private int updateTime;
        private String version;
        private int x;
        private int y;

        public final String getAccount() {
            return this.account;
        }

        public final void setAccount(String str) {
            this.account = str;
        }

        public final String getAlias() {
            return this.alias;
        }

        public final void setAlias(String str) {
            this.alias = str;
        }

        public final String getArea() {
            return this.area;
        }

        public final void setArea(String str) {
            this.area = str;
        }

        public final int getBegin() {
            return this.begin;
        }

        public final void setBegin(int i) {
            this.begin = i;
        }

        public final String getDescription() {
            return this.description;
        }

        public final void setDescription(String str) {
            this.description = str;
        }

        public final int getEnd() {
            return this.end;
        }

        public final void setEnd(int i) {
            this.end = i;
        }

        public final String getGroup() {
            return this.group;
        }

        public final void setGroup(String str) {
            this.group = str;
        }

        public final String getMfrs() {
            return this.mfrs;
        }

        public final void setMfrs(String str) {
            this.mfrs = str;
        }

        public final String getModel() {
            return this.model;
        }

        public final void setModel(String str) {
            this.model = str;
        }

        public final String getName() {
            return this.name;
        }

        public final void setName(String str) {
            this.name = str;
        }

        public final String getProjectid() {
            return this.projectid;
        }

        public final void setProjectid(String str) {
            this.projectid = str;
        }

        public final String getType() {
            return this.type;
        }

        public final void setType(String str) {
            this.type = str;
        }

        public final String getVersion() {
            return this.version;
        }

        public final void setVersion(String str) {
            this.version = str;
        }

        public final int getX() {
            return this.x;
        }

        public final void setX(int i) {
            this.x = i;
        }

        public final int getY() {
            return this.y;
        }

        public final void setY(int i) {
            this.y = i;
        }

        public final String getId() {
            return this.id;
        }

        public final void setId(String str) {
            this.id = str;
        }

        public final boolean getLink() {
            return this.link;
        }

        public final void setLink(boolean z) {
            this.link = z;
        }

        public final String getStatus() {
            return this.status;
        }

        public final void setStatus(String str) {
            this.status = str;
        }

        public final String getAddress() {
            return this.address;
        }

        public final void setAddress(String str) {
            this.address = str;
        }

        public final int getTime() {
            return this.time;
        }

        public final void setTime(int i) {
            this.time = i;
        }

        public final String getSid() {
            return this.sid;
        }

        public final void setSid(String str) {
            this.sid = str;
        }

        public final int getRegisterTime() {
            return this.registerTime;
        }

        public final void setRegisterTime(int i) {
            this.registerTime = i;
        }

        public final int getUpdateTime() {
            return this.updateTime;
        }

        public final void setUpdateTime(int i) {
            this.updateTime = i;
        }

        public final HardwareBean getHardware() {
            return this.hardware;
        }

        public final void setHardware(HardwareBean hardwareBean) {
            this.hardware = hardwareBean;
        }

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\b¨\u0006\u000f"}, d2 = {"Lcom/ciot/networklib/bean/version/record/UpdateVersionRecordResponse$RobotBean$HardwareBean;", "", "()V", "hairpin", "", "getHairpin", "()Ljava/lang/String;", "setHairpin", "(Ljava/lang/String;)V", "idcard", "getIdcard", "setIdcard", "printer", "getPrinter", "setPrinter", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
        /* compiled from: UpdateVersionRecordResponse.kt */
        public static final class HardwareBean {
            private String hairpin;
            private String idcard;
            private String printer;

            public final String getPrinter() {
                return this.printer;
            }

            public final void setPrinter(String str) {
                this.printer = str;
            }

            public final String getIdcard() {
                return this.idcard;
            }

            public final void setIdcard(String str) {
                this.idcard = str;
            }

            public final String getHairpin() {
                return this.hairpin;
            }

            public final void setHairpin(String str) {
                this.hairpin = str;
            }
        }
    }
}
