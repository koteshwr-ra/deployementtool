package com.ciot.realm.db.employee;

import io.realm.RealmObject;
import io.realm.com_ciot_realm_db_employee_EmployeeArcCodeRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class EmployeeArcCode extends RealmObject implements com_ciot_realm_db_employee_EmployeeArcCodeRealmProxyInterface {
    private String code;
    private String type;

    public String realmGet$code() {
        return this.code;
    }

    public String realmGet$type() {
        return this.type;
    }

    public void realmSet$code(String str) {
        this.code = str;
    }

    public void realmSet$type(String str) {
        this.type = str;
    }

    public EmployeeArcCode() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getType() {
        return realmGet$type();
    }

    public void setType(String str) {
        realmSet$type(str);
    }

    public String getCode() {
        return realmGet$code();
    }

    public void setCode(String str) {
        realmSet$code(str);
    }
}
