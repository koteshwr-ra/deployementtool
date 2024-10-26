package com.ciot.networklib.bean.phone;

import com.ciot.realm.db.common.VisitorBean;

public class VisitByPhone {
    public static final String UNVERIFIED = "no";
    public static final String VERIFIED = "yes";
    private String company;
    private VisitorBean visitor;

    public VisitorBean getVisitor() {
        return this.visitor;
    }

    public void setVisitor(VisitorBean visitorBean) {
        this.visitor = visitorBean;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String str) {
        this.company = str;
    }
}
