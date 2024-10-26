package io.realm;

import com.ciot.realm.db.EmployeeBean;
import com.ciot.realm.db.LockMode;
import com.ciot.realm.db.common.AckBean;
import com.ciot.realm.db.common.CompanyResponse;
import com.ciot.realm.db.common.Settings;
import com.ciot.realm.db.common.ValidateBean;
import com.ciot.realm.db.common.VisitorBean;

public interface com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxyInterface {
    AckBean realmGet$ack();

    String realmGet$auditnotes();

    double realmGet$begin();

    String realmGet$code();

    CompanyResponse realmGet$company();

    int realmGet$createtime();

    String realmGet$description();

    EmployeeBean realmGet$employee();

    double realmGet$end();

    String realmGet$id();

    LockMode realmGet$lockmode();

    int realmGet$proofNum();

    Settings realmGet$settings();

    String realmGet$sms();

    String realmGet$status();

    int realmGet$type();

    ValidateBean realmGet$validate();

    VisitorBean realmGet$visitor();

    void realmSet$ack(AckBean ackBean);

    void realmSet$auditnotes(String str);

    void realmSet$begin(double d);

    void realmSet$code(String str);

    void realmSet$company(CompanyResponse companyResponse);

    void realmSet$createtime(int i);

    void realmSet$description(String str);

    void realmSet$employee(EmployeeBean employeeBean);

    void realmSet$end(double d);

    void realmSet$id(String str);

    void realmSet$lockmode(LockMode lockMode);

    void realmSet$proofNum(int i);

    void realmSet$settings(Settings settings);

    void realmSet$sms(String str);

    void realmSet$status(String str);

    void realmSet$type(int i);

    void realmSet$validate(ValidateBean validateBean);

    void realmSet$visitor(VisitorBean visitorBean);
}
