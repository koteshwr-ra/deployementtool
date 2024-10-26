package com.ciot.diagnosis.base;

import com.ciot.diagnosis.bean.Status;
import com.ciot.diagnosis.util.MyContextUtil;

public abstract class BaseDiagnosis {
    public static final int CODE_INIT_FAIL = 1;
    public static final int CODE_SUCCESS = 0;
    protected String diagnosisName;
    public boolean hasSetStatus = false;
    protected Status status;
    private int unInitCode = -1;

    public BaseDiagnosis(String str, int i) {
        this.diagnosisName = str;
        this.unInitCode = i;
        this.status = new Status(i, "尚未初始化");
    }

    public String getDiagnosisName() {
        return this.diagnosisName;
    }

    public Status getStatus() {
        return this.status;
    }

    public String getStatusMessag() {
        return this.status.getMessage();
    }

    public void setStatus(boolean z) {
        int i = z ? 0 : -1;
        StringBuilder sb = new StringBuilder();
        sb.append(this.diagnosisName);
        sb.append(z ? "正常" : "异常");
        setStatus(i, sb.toString());
    }

    public void setStatus(int i, int i2) {
        setStatus(i, getString(i2));
    }

    public void setStatus(int i, String str) {
        this.status.setCode(i);
        this.status.setMessage(str);
        this.hasSetStatus = true;
    }

    public void setDefaultStatus(boolean z) {
        this.status.setCode(z ? 0 : -1);
        Status status2 = this.status;
        StringBuilder sb = new StringBuilder();
        sb.append(this.diagnosisName);
        sb.append(z ? "正常" : "异常");
        status2.setMessage(sb.toString());
    }

    public static String getString(int i) {
        return MyContextUtil.getInstance().getContext().getResources().getString(i);
    }

    public String toString() {
        return "BaseDiagnosis{diagnosisName='" + this.diagnosisName + '\'' + ", status=" + this.status + '}';
    }
}
