package com.ciot.realm.util;

import android.text.TextUtils;
import android.util.Log;
import com.ciot.base.constant.OtherConstants;
import com.ciot.realm.db.Attendance;
import com.ciot.realm.db.EventTrackingBean;
import com.ciot.realm.db.HotelActivitesBean;
import com.ciot.realm.db.MarkerPoint;
import com.ciot.realm.db.Person;
import com.ciot.realm.db.Record;
import com.ciot.realm.db.RegisteredRecordInfo;
import com.ciot.realm.db.RobotBean;
import com.ciot.realm.db.Tactics;
import com.ciot.realm.db.TemRecord;
import com.ciot.realm.db.TemUploadRecord;
import com.ciot.realm.db.ad.AdvertisementsBean;
import com.ciot.realm.db.ad.BeginBean;
import com.ciot.realm.db.ad.CycleBean;
import com.ciot.realm.db.ad.EndBean;
import com.ciot.realm.db.ad.GetAdListsBeanR;
import com.ciot.realm.db.ad.HorseRaceLampsBean;
import com.ciot.realm.db.ad.ResourcesBean;
import com.ciot.realm.db.ad.TimesBean;
import com.ciot.realm.db.common.RecordResponse;
import com.ciot.realm.db.employee.EmployeeArcCode;
import com.ciot.realm.db.employee.EmployeeLockMode;
import com.ciot.realm.db.employee.EmployeeResponse;
import com.ciot.realm.db.patrol.Action;
import com.ciot.realm.db.patrol.Line;
import com.ciot.realm.db.patrol.MediaBean;
import com.ciot.realm.db.patrol.PathBean;
import com.ciot.realm.db.patrol.PatrolTaskBean;
import com.ciot.realm.db.patrol.Place;
import com.ciot.realm.db.patrol.Plan;
import com.ciot.realm.db.patrol.Resource;
import com.ciot.realm.db.patrol.TransitionBean;
import com.ciot.realm.db.patrol.WaitBean;
import com.ciot.realm.db.recommantation.DataBean;
import com.ciot.realm.db.semantic.SemanticBean;
import com.ciot.realm.db.stat.AdStat;
import com.ciot.realm.db.stat.AdWatchStat;
import com.ciot.realm.db.stat.RobotPersonStat;
import com.ciot.realm.db.stat.StatsRecord;
import com.ciot.realm.db.timer.TimerReceptionTaskBean;
import com.ciot.realm.db.visitor.VisitorResponse;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.TbsVideoCacheTask;
import io.realm.Case;
import io.realm.ImportFlag;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmResults;
import io.realm.Sort;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import org.apache.commons.lang3.time.DateUtils;
import xcrash.TombstoneParser;

public class SentryRealmHelper {
    public static final String REALM_NAME = "SentryRobot.realm";
    public static final int SCHEMA_VERSION = 7;
    private static final String TAG = SentryRealmHelper.class.getSimpleName();
    private static volatile SentryRealmHelper mRealmHelper;
    private final DateFormat mDateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
    private RealmConfiguration mRealmConfiguration;

    public static SentryRealmHelper getInstance() {
        if (mRealmHelper == null) {
            synchronized (SentryRealmHelper.class) {
                if (mRealmHelper == null) {
                    mRealmHelper = new SentryRealmHelper();
                }
            }
        }
        return mRealmHelper;
    }

    public Realm newRealmInstance() {
        if (this.mRealmConfiguration == null) {
            synchronized (RealmHelper.class) {
                if (this.mRealmConfiguration == null) {
                    this.mRealmConfiguration = new RealmConfiguration.Builder().name(OtherConstants.DB_NAME).schemaVersion(7).deleteRealmIfMigrationNeeded().build();
                }
            }
        }
        return Realm.getInstance(this.mRealmConfiguration);
    }

    public void updateRecommantation(DataBean dataBean) {
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.beginTransaction();
        newRealmInstance.copyToRealmOrUpdate(dataBean, new ImportFlag[0]);
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
    }

    public List<String> findAllRecommantation() {
        Realm newRealmInstance = newRealmInstance();
        DataBean dataBean = (DataBean) newRealmInstance.where(DataBean.class).findFirst();
        DataBean dataBean2 = dataBean != null ? (DataBean) newRealmInstance.copyFromRealm(dataBean) : null;
        newRealmInstance.close();
        if (dataBean2 == null) {
            return null;
        }
        RealmList<String> recommendations = dataBean2.getRecommendations();
        String str = TAG;
        Log.d(str, "SentryRealmHelper findAllRecommantation:" + recommendations.toString());
        return recommendations;
    }

    public void insertOrUpdateEmployee(EmployeeResponse employeeResponse) {
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.beginTransaction();
        newRealmInstance.copyToRealmOrUpdate(employeeResponse, new ImportFlag[0]);
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
    }

    public void insertOrUpdateEmployee(List<EmployeeResponse> list) {
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.beginTransaction();
        newRealmInstance.copyToRealmOrUpdate(list, new ImportFlag[0]);
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
    }

    public void deleteEmployee(String str) {
        Realm newRealmInstance = newRealmInstance();
        EmployeeResponse employeeResponse = (EmployeeResponse) newRealmInstance.where(EmployeeResponse.class).equalTo("id", str).findFirst();
        newRealmInstance.beginTransaction();
        if (employeeResponse != null) {
            employeeResponse.deleteFromRealm();
        }
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
        Realm.compactRealm(newRealmInstance.getConfiguration());
    }

    public void deleteEmployeeByUuid(long j) {
        Realm newRealmInstance = newRealmInstance();
        EmployeeResponse employeeResponse = (EmployeeResponse) newRealmInstance.where(EmployeeResponse.class).equalTo("uuid", Long.valueOf(j)).findFirst();
        newRealmInstance.beginTransaction();
        if (employeeResponse != null) {
            employeeResponse.deleteFromRealm();
        }
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
        Realm.compactRealm(newRealmInstance.getConfiguration());
    }

    public void deleteAllEmployee() {
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.beginTransaction();
        RealmResults<E> findAll = newRealmInstance.where(EmployeeResponse.class).findAll();
        if (findAll != null) {
            findAll.deleteAllFromRealm();
        }
        RealmResults<E> findAll2 = newRealmInstance.where(EmployeeArcCode.class).findAll();
        if (findAll2 != null) {
            findAll2.deleteAllFromRealm();
        }
        RealmResults<E> findAll3 = newRealmInstance.where(EmployeeLockMode.class).findAll();
        if (findAll3 != null) {
            findAll3.deleteAllFromRealm();
        }
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
        Realm.compactRealm(newRealmInstance.getConfiguration());
    }

    public List<EmployeeResponse> findAllEmployee() {
        Realm newRealmInstance = newRealmInstance();
        List<EmployeeResponse> copyFromRealm = newRealmInstance.copyFromRealm(newRealmInstance.where(EmployeeResponse.class).findAll());
        newRealmInstance.close();
        return copyFromRealm;
    }

    public EmployeeResponse findEmployeeByUUId(long j) {
        Realm newRealmInstance = newRealmInstance();
        EmployeeResponse employeeResponse = (EmployeeResponse) newRealmInstance.where(EmployeeResponse.class).equalTo("uuid", Long.valueOf(j)).findFirst();
        if (employeeResponse != null) {
            employeeResponse = (EmployeeResponse) newRealmInstance.copyFromRealm(employeeResponse);
        }
        newRealmInstance.close();
        return employeeResponse;
    }

    public EmployeeResponse findEmployeeById(String str) {
        Realm newRealmInstance = newRealmInstance();
        EmployeeResponse employeeResponse = (EmployeeResponse) newRealmInstance.where(EmployeeResponse.class).equalTo("id", str).findFirst();
        if (employeeResponse != null) {
            employeeResponse = (EmployeeResponse) newRealmInstance.copyFromRealm(employeeResponse);
        }
        newRealmInstance.close();
        return employeeResponse;
    }

    public EmployeeResponse findEmployeeByIdCard(String str) {
        Realm newRealmInstance = newRealmInstance();
        EmployeeResponse employeeResponse = (EmployeeResponse) newRealmInstance.where(EmployeeResponse.class).equalTo("idcard", str, Case.INSENSITIVE).findFirst();
        if (employeeResponse != null) {
            employeeResponse = (EmployeeResponse) newRealmInstance.copyFromRealm(employeeResponse);
        }
        newRealmInstance.close();
        return employeeResponse;
    }

    public void insertOrUpdateVisitor(VisitorResponse visitorResponse) {
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.beginTransaction();
        newRealmInstance.copyToRealmOrUpdate(visitorResponse, new ImportFlag[0]);
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
    }

    public void insertOrUpdateVisitor(List<VisitorResponse> list) {
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.beginTransaction();
        newRealmInstance.copyToRealmOrUpdate(list, new ImportFlag[0]);
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
    }

    public List<VisitorResponse> findAllVisitor() {
        Realm newRealmInstance = newRealmInstance();
        List<VisitorResponse> copyFromRealm = newRealmInstance.copyFromRealm(newRealmInstance.where(VisitorResponse.class).findAll());
        newRealmInstance.close();
        return copyFromRealm;
    }

    public void deleteAllVisitor() {
        Realm newRealmInstance = newRealmInstance();
        try {
            newRealmInstance.executeTransaction(new Realm.Transaction() {
                public final void execute(Realm realm) {
                    RealmResults.this.deleteAllFromRealm();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            newRealmInstance.executeTransaction(new Realm.Transaction() {
                public final void execute(Realm realm) {
                    RealmResults.this.deleteAllFromRealm();
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        newRealmInstance.close();
        Realm.compactRealm(newRealmInstance.getConfiguration());
    }

    public void deleteVisitor(String str) {
        Realm newRealmInstance = newRealmInstance();
        VisitorResponse visitorResponse = (VisitorResponse) newRealmInstance.where(VisitorResponse.class).equalTo("id", str).findFirst();
        newRealmInstance.beginTransaction();
        if (visitorResponse != null) {
            visitorResponse.deleteFromRealm();
        }
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
        Realm.compactRealm(newRealmInstance.getConfiguration());
    }

    public VisitorResponse findVisitorById(String str) {
        Realm newRealmInstance = newRealmInstance();
        VisitorResponse visitorResponse = (VisitorResponse) newRealmInstance.where(VisitorResponse.class).equalTo("id", str).findFirst();
        VisitorResponse visitorResponse2 = visitorResponse != null ? (VisitorResponse) newRealmInstance.copyFromRealm(visitorResponse) : null;
        newRealmInstance.close();
        return visitorResponse2;
    }

    public VisitorResponse findVisitorByUid(long j) {
        Realm newRealmInstance = newRealmInstance();
        VisitorResponse visitorResponse = (VisitorResponse) newRealmInstance.where(VisitorResponse.class).equalTo("uuid", Long.valueOf(j)).findFirst();
        VisitorResponse visitorResponse2 = visitorResponse != null ? (VisitorResponse) newRealmInstance.copyFromRealm(visitorResponse) : null;
        newRealmInstance.close();
        return visitorResponse2;
    }

    public void deleteAllRecord() {
        Realm newRealmInstance = newRealmInstance();
        try {
            newRealmInstance.executeTransaction(new Realm.Transaction() {
                public final void execute(Realm realm) {
                    RealmResults.this.deleteAllFromRealm();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            newRealmInstance.executeTransaction(new Realm.Transaction() {
                public final void execute(Realm realm) {
                    RealmResults.this.deleteAllFromRealm();
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            newRealmInstance.executeTransaction(new Realm.Transaction() {
                public final void execute(Realm realm) {
                    RealmResults.this.deleteAllFromRealm();
                }
            });
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        try {
            newRealmInstance.executeTransaction(new Realm.Transaction() {
                public final void execute(Realm realm) {
                    RealmResults.this.deleteAllFromRealm();
                }
            });
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        try {
            newRealmInstance.executeTransaction(new Realm.Transaction() {
                public final void execute(Realm realm) {
                    RealmResults.this.deleteAllFromRealm();
                }
            });
        } catch (Exception e5) {
            e5.printStackTrace();
        }
        try {
            newRealmInstance.executeTransaction(new Realm.Transaction() {
                public final void execute(Realm realm) {
                    RealmResults.this.deleteAllFromRealm();
                }
            });
        } catch (Exception e6) {
            e6.printStackTrace();
        }
        try {
            newRealmInstance.executeTransaction(new Realm.Transaction() {
                public final void execute(Realm realm) {
                    RealmResults.this.deleteAllFromRealm();
                }
            });
        } catch (Exception e7) {
            e7.printStackTrace();
        }
        try {
            newRealmInstance.executeTransaction(new Realm.Transaction() {
                public final void execute(Realm realm) {
                    RealmResults.this.deleteAllFromRealm();
                }
            });
        } catch (Exception e8) {
            e8.printStackTrace();
        }
        try {
            newRealmInstance.executeTransaction(new Realm.Transaction() {
                public final void execute(Realm realm) {
                    RealmResults.this.deleteAllFromRealm();
                }
            });
        } catch (Exception e9) {
            e9.printStackTrace();
        }
        newRealmInstance.close();
        Realm.compactRealm(newRealmInstance.getConfiguration());
    }

    public void upsertRecord(RecordResponse recordResponse) {
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.beginTransaction();
        newRealmInstance.copyToRealmOrUpdate(recordResponse, new ImportFlag[0]);
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
    }

    public void deleteRecordById(String str) {
        Realm newRealmInstance = newRealmInstance();
        RecordResponse recordResponse = (RecordResponse) newRealmInstance.where(RecordResponse.class).equalTo("id", str).findFirst();
        newRealmInstance.beginTransaction();
        if (recordResponse != null) {
            recordResponse.deleteFromRealm();
        }
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
        Realm.compactRealm(newRealmInstance.getConfiguration());
    }

    public RecordResponse findRecordById(String str) {
        Realm newRealmInstance = newRealmInstance();
        RecordResponse recordResponse = (RecordResponse) newRealmInstance.where(RecordResponse.class).equalTo("id", str).findFirst();
        RecordResponse recordResponse2 = recordResponse != null ? (RecordResponse) newRealmInstance.copyFromRealm(recordResponse) : null;
        newRealmInstance.close();
        return recordResponse2;
    }

    public RecordResponse findRecordByVisitorId(String str) {
        Realm newRealmInstance = newRealmInstance();
        RecordResponse recordResponse = (RecordResponse) newRealmInstance.where(RecordResponse.class).equalTo("visitor.id", str).and().greaterThan("end", (double) (System.currentTimeMillis() / 1000)).findFirst();
        RecordResponse recordResponse2 = recordResponse != null ? (RecordResponse) newRealmInstance.copyFromRealm(recordResponse) : null;
        newRealmInstance.close();
        return recordResponse2;
    }

    /* JADX WARNING: type inference failed for: r7v7, types: [io.realm.RealmModel] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.ciot.realm.db.common.RecordResponse findRecordByVisitorIdOrIdCard(java.lang.String r7, java.lang.String r8) {
        /*
            r6 = this;
            long r0 = java.lang.System.currentTimeMillis()
            r2 = 1000(0x3e8, double:4.94E-321)
            long r0 = r0 / r2
            double r0 = (double) r0
            io.realm.Realm r2 = r6.newRealmInstance()
            java.lang.Class<com.ciot.realm.db.common.RecordResponse> r3 = com.ciot.realm.db.common.RecordResponse.class
            io.realm.RealmQuery r3 = r2.where(r3)
            java.lang.String r4 = "visitor.id"
            io.realm.RealmQuery r7 = r3.equalTo((java.lang.String) r4, (java.lang.String) r7)
            io.realm.RealmQuery r7 = r7.and()
            java.lang.String r3 = "end"
            io.realm.RealmQuery r7 = r7.greaterThan((java.lang.String) r3, (double) r0)
            boolean r4 = android.text.TextUtils.isEmpty(r8)
            if (r4 != 0) goto L_0x003c
            io.realm.RealmQuery r7 = r7.or()
            io.realm.Case r4 = io.realm.Case.INSENSITIVE
            java.lang.String r5 = "visitor.idcard"
            io.realm.RealmQuery r7 = r7.equalTo(r5, r8, r4)
            io.realm.RealmQuery r7 = r7.and()
            io.realm.RealmQuery r7 = r7.greaterThan((java.lang.String) r3, (double) r0)
        L_0x003c:
            java.lang.Object r7 = r7.findFirst()
            com.ciot.realm.db.common.RecordResponse r7 = (com.ciot.realm.db.common.RecordResponse) r7
            r8 = 0
            if (r7 == 0) goto L_0x004c
            io.realm.RealmModel r7 = r2.copyFromRealm(r7)
            r8 = r7
            com.ciot.realm.db.common.RecordResponse r8 = (com.ciot.realm.db.common.RecordResponse) r8
        L_0x004c:
            r2.close()
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ciot.realm.util.SentryRealmHelper.findRecordByVisitorIdOrIdCard(java.lang.String, java.lang.String):com.ciot.realm.db.common.RecordResponse");
    }

    /* JADX WARNING: type inference failed for: r7v6, types: [io.realm.RealmModel] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.ciot.realm.db.common.RecordResponse findRecordByInvitedCode(java.lang.String r7) {
        /*
            r6 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r7)
            r1 = 0
            if (r0 == 0) goto L_0x0008
            return r1
        L_0x0008:
            io.realm.Realm r0 = r6.newRealmInstance()
            java.lang.Class<com.ciot.realm.db.common.RecordResponse> r2 = com.ciot.realm.db.common.RecordResponse.class
            io.realm.RealmQuery r2 = r0.where(r2)
            java.lang.String r3 = "sms"
            io.realm.RealmQuery r2 = r2.equalTo((java.lang.String) r3, (java.lang.String) r7)
            io.realm.RealmQuery r2 = r2.and()
            java.lang.String r3 = "status"
            java.lang.String r4 = "complete"
            io.realm.RealmQuery r2 = r2.notEqualTo((java.lang.String) r3, (java.lang.String) r4)
            io.realm.RealmQuery r2 = r2.or()
            java.lang.String r5 = "code"
            io.realm.RealmQuery r7 = r2.equalTo((java.lang.String) r5, (java.lang.String) r7)
            io.realm.RealmQuery r7 = r7.and()
            io.realm.RealmQuery r7 = r7.notEqualTo((java.lang.String) r3, (java.lang.String) r4)
            java.lang.Object r7 = r7.findFirst()
            com.ciot.realm.db.common.RecordResponse r7 = (com.ciot.realm.db.common.RecordResponse) r7
            if (r7 == 0) goto L_0x0045
            io.realm.RealmModel r7 = r0.copyFromRealm(r7)
            r1 = r7
            com.ciot.realm.db.common.RecordResponse r1 = (com.ciot.realm.db.common.RecordResponse) r1
        L_0x0045:
            r0.close()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ciot.realm.util.SentryRealmHelper.findRecordByInvitedCode(java.lang.String):com.ciot.realm.db.common.RecordResponse");
    }

    public void updateRecord(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            Realm newRealmInstance = newRealmInstance();
            RecordResponse recordResponse = (RecordResponse) newRealmInstance.where(RecordResponse.class).equalTo("id", str).findFirst();
            newRealmInstance.beginTransaction();
            if (recordResponse != null) {
                recordResponse.setStatus(str2);
            }
            newRealmInstance.commitTransaction();
            newRealmInstance.close();
        }
    }

    public void upsertRegisteredRocord(RegisteredRecordInfo registeredRecordInfo) {
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.beginTransaction();
        newRealmInstance.copyToRealmOrUpdate(registeredRecordInfo, new ImportFlag[0]);
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
    }

    public RegisteredRecordInfo findRegisteredRecord() {
        Realm newRealmInstance = newRealmInstance();
        RegisteredRecordInfo registeredRecordInfo = (RegisteredRecordInfo) newRealmInstance.where(RegisteredRecordInfo.class).sort("id", Sort.ASCENDING).findFirst();
        RegisteredRecordInfo registeredRecordInfo2 = registeredRecordInfo != null ? (RegisteredRecordInfo) newRealmInstance.copyFromRealm(registeredRecordInfo) : null;
        newRealmInstance.close();
        return registeredRecordInfo2;
    }

    public void deleteRegisteredRecord(RegisteredRecordInfo registeredRecordInfo) {
        Record record;
        if (registeredRecordInfo != null) {
            Realm newRealmInstance = newRealmInstance();
            RegisteredRecordInfo registeredRecordInfo2 = (RegisteredRecordInfo) newRealmInstance.where(RegisteredRecordInfo.class).equalTo("id", Long.valueOf(registeredRecordInfo.getId())).findFirst();
            newRealmInstance.beginTransaction();
            if (registeredRecordInfo2 != null) {
                registeredRecordInfo2.deleteFromRealm();
            }
            Record record2 = registeredRecordInfo.getRecord();
            if (!(record2 == null || (record = (Record) newRealmInstance.where(Record.class).equalTo("recordId", record2.getRecordId()).findFirst()) == null)) {
                record.deleteFromRealm();
            }
            newRealmInstance.commitTransaction();
            newRealmInstance.close();
            Realm.compactRealm(newRealmInstance.getConfiguration());
        }
    }

    public List<RegisteredRecordInfo> findAllRegisteredRecord() {
        Realm newRealmInstance = newRealmInstance();
        List<RegisteredRecordInfo> copyFromRealm = newRealmInstance.copyFromRealm(newRealmInstance.where(RegisteredRecordInfo.class).findAll().sort("id", Sort.ASCENDING));
        newRealmInstance.close();
        return copyFromRealm;
    }

    public void upsertClockIn(Attendance attendance) {
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.beginTransaction();
        newRealmInstance.copyToRealmOrUpdate(attendance, new ImportFlag[0]);
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
    }

    public void deleteClockIn(String str, String str2) {
        Realm newRealmInstance = newRealmInstance();
        Attendance attendance = (Attendance) newRealmInstance.where(Attendance.class).equalTo("id", str).equalTo("employeeId", str2).findFirst();
        newRealmInstance.beginTransaction();
        if (attendance != null) {
            attendance.deleteFromRealm();
        }
        try {
            RealmResults<E> findAll = newRealmInstance.where(Attendance.class).findAll();
            if (findAll != null && findAll.size() == 0) {
                newRealmInstance.where(RobotBean.class).findAll().deleteAllFromRealm();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
        Realm.compactRealm(newRealmInstance.getConfiguration());
    }

    public Attendance findClockIn() {
        Realm newRealmInstance = newRealmInstance();
        Attendance attendance = (Attendance) newRealmInstance.where(Attendance.class).findFirst();
        Attendance attendance2 = attendance != null ? (Attendance) newRealmInstance.copyFromRealm(attendance) : null;
        newRealmInstance.close();
        return attendance2;
    }

    public void showCurrentClockRecord() {
        Realm newRealmInstance = newRealmInstance();
        Iterator it = newRealmInstance.where(Attendance.class).findAll().iterator();
        while (it.hasNext()) {
            Log.i(TAG, ((Attendance) it.next()).toString());
        }
        newRealmInstance.close();
    }

    public void insertOrUpdateTemRecord(TemRecord temRecord) {
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.beginTransaction();
        newRealmInstance.insertOrUpdate((RealmModel) temRecord);
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
    }

    public void insertOrUpdateTemRecord(List<TemRecord> list) {
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.beginTransaction();
        newRealmInstance.insertOrUpdate((Collection<? extends RealmModel>) list);
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
    }

    public void deleteTemRecord(TemRecord temRecord) {
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.beginTransaction();
        TemRecord temRecord2 = (TemRecord) newRealmInstance.where(TemRecord.class).equalTo("createtime", Long.valueOf(temRecord.getCreatetime())).equalTo("staffno", temRecord.getStaffno()).findFirst();
        if (temRecord2 != null) {
            temRecord2.deleteFromRealm();
        }
        try {
            RealmResults<E> findAll = newRealmInstance.where(TemRecord.class).findAll();
            if (findAll != null && findAll.size() == 0) {
                newRealmInstance.where(Person.class).findAll().deleteAllFromRealm();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
        Realm.compactRealm(newRealmInstance.getConfiguration());
    }

    public void findTemRecordByLessTimeAsync(long j, RealmChangeListener<RealmResults<TemRecord>> realmChangeListener) {
        newRealmInstance().where(TemRecord.class).lessThan("createtime", j).findAllAsync().addChangeListener(realmChangeListener);
    }

    public void insertUploadTemRecord(TemUploadRecord temUploadRecord) {
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.executeTransactionAsync(new Realm.Transaction() {
            public final void execute(Realm realm) {
                realm.insertOrUpdate((RealmModel) TemUploadRecord.this);
            }
        });
        newRealmInstance.close();
    }

    public TemUploadRecord findFirstUnUploadTemRecord() {
        Realm newRealmInstance = newRealmInstance();
        TemUploadRecord temUploadRecord = (TemUploadRecord) newRealmInstance.where(TemUploadRecord.class).isNull(TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_URL).and().isNotEmpty("datas").findFirst();
        if (temUploadRecord != null) {
            temUploadRecord = (TemUploadRecord) newRealmInstance.copyFromRealm(temUploadRecord);
        }
        newRealmInstance.close();
        return temUploadRecord;
    }

    public void deleteTemUploadRecord(TemUploadRecord temUploadRecord) {
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.beginTransaction();
        TemUploadRecord temUploadRecord2 = (TemUploadRecord) newRealmInstance.where(TemUploadRecord.class).equalTo("uploadtime", Long.valueOf(temUploadRecord.getUploadtime())).findFirst();
        if (temUploadRecord2 != null) {
            temUploadRecord2.deleteFromRealm();
        }
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
    }

    public TemUploadRecord findUnUploadTemRecord2TemUploadRecord(String str, long j) {
        Realm newRealmInstance = newRealmInstance();
        RealmResults<E> findAll = newRealmInstance.where(TemRecord.class).isNull("pathname").sort("createtime", Sort.DESCENDING).limit(j).findAll();
        if (findAll == null || findAll.isEmpty()) {
            newRealmInstance.close();
            return null;
        }
        newRealmInstance.beginTransaction();
        String uuid = UUID.randomUUID().toString();
        TemUploadRecord temUploadRecord = (TemUploadRecord) newRealmInstance.createObject(TemUploadRecord.class, Long.valueOf(System.currentTimeMillis() / 1000));
        temUploadRecord.setCode(uuid);
        temUploadRecord.setRobot(str);
        temUploadRecord.getDatas().addAll(findAll);
        newRealmInstance.commitTransaction();
        TemUploadRecord temUploadRecord2 = (TemUploadRecord) newRealmInstance.copyFromRealm(temUploadRecord);
        newRealmInstance.close();
        return temUploadRecord2;
    }

    public List<TemRecord> findUnUploadTemRecord() {
        Realm newRealmInstance = newRealmInstance();
        List<TemRecord> copyFromRealm = newRealmInstance.copyFromRealm(newRealmInstance.where(TemRecord.class).isNull("isUpload").findAll());
        newRealmInstance.close();
        return copyFromRealm;
    }

    public List<TemRecord> findUploadedTemRecord() {
        Realm newRealmInstance = newRealmInstance();
        List<TemRecord> copyFromRealm = newRealmInstance.copyFromRealm(newRealmInstance.where(TemRecord.class).equalTo("isUpload", (Boolean) true).findAll());
        newRealmInstance.close();
        return copyFromRealm;
    }

    public boolean isTemRecordExistByFilename(Realm realm, String str) {
        return ((TemRecord) realm.where(TemRecord.class).equalTo("staffno", str).findFirst()) != null;
    }

    public boolean isUploadTemRecordExistByFilename(Realm realm, String str) {
        return ((TemUploadRecord) realm.where(TemUploadRecord.class).contains(TombstoneParser.keyCode, str).findFirst()) != null;
    }

    public TemUploadRecord findFirstNormalTemUploadRecord() {
        Realm newRealmInstance = newRealmInstance();
        TemUploadRecord temUploadRecord = (TemUploadRecord) newRealmInstance.where(TemUploadRecord.class).isNotNull(TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_URL).and().isNotEmpty("datas").sort("uploadtime", Sort.DESCENDING).findFirst();
        if (temUploadRecord != null) {
            temUploadRecord = (TemUploadRecord) newRealmInstance.copyFromRealm(temUploadRecord);
        }
        newRealmInstance.close();
        return temUploadRecord;
    }

    public TemUploadRecord findFirstAbnormalTemUploadRecord() {
        Realm newRealmInstance = newRealmInstance();
        TemUploadRecord temUploadRecord = (TemUploadRecord) newRealmInstance.where(TemUploadRecord.class).isNotNull(TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_URL).and().isNotNull("filepath").sort("uploadtime", Sort.DESCENDING).findFirst();
        if (temUploadRecord != null) {
            temUploadRecord = (TemUploadRecord) newRealmInstance.copyFromRealm(temUploadRecord);
        }
        newRealmInstance.close();
        return temUploadRecord;
    }

    public List<GetAdListsBeanR> findAdList(Realm realm) {
        Calendar instance = Calendar.getInstance();
        long j = (long) (instance.get(12) + (instance.get(11) * 60));
        RealmResults<E> findAll = realm.where(GetAdListsBeanR.class).lessThanOrEqualTo("times.begin.minutes", j).findAll().where().greaterThan("times.end.minutes", j).findAll();
        long currentTimeMillis = (System.currentTimeMillis() / 1000) - (60 * j);
        RealmResults<E> findAll2 = findAll.where().findAll();
        ArrayList arrayList = new ArrayList();
        for (E e : realm.copyFromRealm(findAll2)) {
            addDailyAd(arrayList, e);
            addTempAd(currentTimeMillis, arrayList, e);
            addWeeklyAd(instance, arrayList, e);
            addMonthlyAd(instance, arrayList, e);
        }
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            GetAdListsBeanR getAdListsBeanR = (GetAdListsBeanR) arrayList.get(i);
            int i2 = 0;
            while (true) {
                if (i2 >= getAdListsBeanR.getTimes().size()) {
                    break;
                }
                TimesBean timesBean = getAdListsBeanR.getTimes().get(i2);
                if (timesBean.getBegin().getMinutes() <= j && timesBean.getEnd().getMinutes() > j) {
                    arrayList2.add(getAdListsBeanR);
                    break;
                }
                i2++;
            }
        }
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            RealmList<AdvertisementsBean> advertisements = ((GetAdListsBeanR) it.next()).getAdvertisements();
            if (advertisements != null) {
                Iterator<AdvertisementsBean> it2 = advertisements.iterator();
                while (it2.hasNext()) {
                    AdvertisementsBean next = it2.next();
                    long end = (long) next.getEnd();
                    long currentTimeMillis2 = System.currentTimeMillis() / 1000;
                    if (currentTimeMillis2 <= ((long) next.getBegin()) || currentTimeMillis2 >= end) {
                        it2.remove();
                    }
                }
                if (advertisements.size() <= 0) {
                    it.remove();
                }
            } else {
                it.remove();
            }
        }
        return arrayList2;
    }

    private void addDailyAd(List<GetAdListsBeanR> list, GetAdListsBeanR getAdListsBeanR) {
        if (CycleBean.DAILY_TYPE.equals(getAdListsBeanR.getCycle().getType())) {
            list.add(getAdListsBeanR);
        }
    }

    private void addMonthlyAd(Calendar calendar, List<GetAdListsBeanR> list, GetAdListsBeanR getAdListsBeanR) {
        if (CycleBean.MONTHLY_TYPE.equals(getAdListsBeanR.getCycle().getType())) {
            Iterator<Long> it = getAdListsBeanR.getCycle().getData().iterator();
            while (it.hasNext()) {
                if (it.next().longValue() == ((long) calendar.get(5))) {
                    list.add(getAdListsBeanR);
                    return;
                }
            }
        }
    }

    private void addWeeklyAd(Calendar calendar, List<GetAdListsBeanR> list, GetAdListsBeanR getAdListsBeanR) {
        if (CycleBean.WEEKLY_TYPE.equals(getAdListsBeanR.getCycle().getType())) {
            Iterator<Long> it = getAdListsBeanR.getCycle().getData().iterator();
            while (it.hasNext()) {
                Long next = it.next();
                int i = 7;
                int i2 = calendar.get(7);
                if (i2 > 1) {
                    i = i2 - 1;
                }
                if (next.longValue() == ((long) i)) {
                    list.add(getAdListsBeanR);
                    return;
                }
            }
        }
    }

    private void addTempAd(long j, List<GetAdListsBeanR> list, GetAdListsBeanR getAdListsBeanR) {
        if (CycleBean.TIME_TYPE.equals(getAdListsBeanR.getCycle().getType())) {
            Iterator<Long> it = getAdListsBeanR.getCycle().getData().iterator();
            while (it.hasNext()) {
                Long valueOf = Long.valueOf(it.next().longValue() + 120);
                if (valueOf.longValue() >= j && valueOf.longValue() < TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC + j) {
                    list.add(getAdListsBeanR);
                    return;
                }
            }
        }
    }

    public void insertAd(Realm realm, GetAdListsBeanR getAdListsBeanR) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(getAdListsBeanR, new ImportFlag[0]);
        realm.commitTransaction();
    }

    public void deleteAllAdList(Realm realm) {
        realm.beginTransaction();
        RealmResults<E> findAll = realm.where(GetAdListsBeanR.class).findAll();
        RealmResults<E> findAll2 = realm.where(CycleBean.class).findAll();
        RealmResults<E> findAll3 = realm.where(TimesBean.class).findAll();
        RealmResults<E> findAll4 = realm.where(AdvertisementsBean.class).findAll();
        RealmResults<E> findAll5 = realm.where(BeginBean.class).findAll();
        RealmResults<E> findAll6 = realm.where(EndBean.class).findAll();
        RealmResults<E> findAll7 = realm.where(ResourcesBean.class).findAll();
        RealmResults<E> findAll8 = realm.where(HorseRaceLampsBean.class).findAll();
        findAll.deleteAllFromRealm();
        findAll2.deleteAllFromRealm();
        findAll3.deleteAllFromRealm();
        findAll4.deleteAllFromRealm();
        findAll5.deleteAllFromRealm();
        findAll6.deleteAllFromRealm();
        findAll7.deleteAllFromRealm();
        findAll8.deleteAllFromRealm();
        realm.commitTransaction();
    }

    public boolean isAdFileExistByResourceId(Realm realm, String str) {
        return ((GetAdListsBeanR) realm.where(GetAdListsBeanR.class).equalTo("advertisements.resources.id", str).findFirst()) != null;
    }

    public void insertOrUpdateSemantic(SemanticBean semanticBean) {
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.beginTransaction();
        newRealmInstance.insertOrUpdate((RealmModel) semanticBean);
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
    }

    public SemanticBean getSemanticByQuestion(String str) {
        Realm newRealmInstance = newRealmInstance();
        SemanticBean semanticBean = (SemanticBean) newRealmInstance.where(SemanticBean.class).equalTo("question", str).findFirst();
        SemanticBean semanticBean2 = semanticBean != null ? (SemanticBean) newRealmInstance.copyFromRealm(semanticBean) : null;
        newRealmInstance.close();
        return semanticBean2;
    }

    public void deleteSemanticByQuestion(String str) {
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.beginTransaction();
        SemanticBean semanticBean = (SemanticBean) newRealmInstance.where(SemanticBean.class).equalTo("question", str).findFirst();
        if (semanticBean != null) {
            semanticBean.deleteFromRealm();
        }
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
        Realm.compactRealm(newRealmInstance.getConfiguration());
    }

    public void saveMarkerPoint(MarkerPoint markerPoint) {
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.beginTransaction();
        newRealmInstance.copyToRealmOrUpdate(markerPoint, new ImportFlag[0]);
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
    }

    public List<MarkerPoint> findMarkerPointByMapName(String str) {
        Realm newRealmInstance = newRealmInstance();
        List<MarkerPoint> copyFromRealm = newRealmInstance.copyFromRealm(newRealmInstance.where(MarkerPoint.class).equalTo("mapName", str).findAll());
        newRealmInstance.close();
        return copyFromRealm;
    }

    public void insertPatrolTask(Realm realm, PatrolTaskBean patrolTaskBean) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(patrolTaskBean, new ImportFlag[0]);
        realm.commitTransaction();
    }

    public void deletePatrolTaskById(String str) {
        Realm newRealmInstance = newRealmInstance();
        PatrolTaskBean patrolTaskBean = (PatrolTaskBean) newRealmInstance.where(PatrolTaskBean.class).equalTo("id", str).findFirst();
        newRealmInstance.beginTransaction();
        if (patrolTaskBean != null) {
            patrolTaskBean.deleteFromRealm();
        }
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
        Realm.compactRealm(newRealmInstance.getConfiguration());
    }

    public List<PatrolTaskBean> findAllPatrolTask(Realm realm) {
        return realm.copyFromRealm(realm.where(PatrolTaskBean.class).findAll());
    }

    public void deleteAllPatrolTask(Realm realm) {
        realm.beginTransaction();
        RealmResults<E> findAll = realm.where(PatrolTaskBean.class).findAll();
        RealmResults<E> findAll2 = realm.where(Line.class).findAll();
        RealmResults<E> findAll3 = realm.where(Plan.class).findAll();
        RealmResults<E> findAll4 = realm.where(Resource.class).findAll();
        RealmResults<E> findAll5 = realm.where(Action.class).findAll();
        RealmResults<E> findAll6 = realm.where(Place.class).findAll();
        RealmResults<E> findAll7 = realm.where(TransitionBean.class).findAll();
        RealmResults<E> findAll8 = realm.where(WaitBean.class).findAll();
        RealmResults<E> findAll9 = realm.where(MediaBean.class).findAll();
        findAll.deleteAllFromRealm();
        findAll2.deleteAllFromRealm();
        findAll3.deleteAllFromRealm();
        findAll4.deleteAllFromRealm();
        findAll5.deleteAllFromRealm();
        findAll6.deleteAllFromRealm();
        findAll7.deleteAllFromRealm();
        findAll8.deleteAllFromRealm();
        findAll9.deleteAllFromRealm();
        realm.commitTransaction();
    }

    public PatrolTaskBean findPatrolTaskByStartTime(Realm realm, String str) {
        RealmResults<E> findAll = realm.where(PatrolTaskBean.class).equalTo("plan.start", str).findAll();
        List<E> copyFromRealm = findAll != null ? realm.copyFromRealm(findAll) : null;
        if (copyFromRealm != null && copyFromRealm.size() > 0) {
            Calendar instance = Calendar.getInstance();
            ArrayList arrayList = new ArrayList();
            for (E e : copyFromRealm) {
                addDailyPatrolTask(arrayList, e);
                addMonthlyPatrolTask(instance, arrayList, e);
                addWeeklyPatrolTask(instance, arrayList, e);
                addTempPatrolTask(arrayList, e);
            }
            if (arrayList.size() > 0) {
                return (PatrolTaskBean) arrayList.get(arrayList.size() - 1);
            }
        }
        return null;
    }

    private void addDailyPatrolTask(List<PatrolTaskBean> list, PatrolTaskBean patrolTaskBean) {
        if ("day".equals(patrolTaskBean.getPlan().getCycle())) {
            list.add(patrolTaskBean);
        }
    }

    private void addMonthlyPatrolTask(Calendar calendar, List<PatrolTaskBean> list, PatrolTaskBean patrolTaskBean) {
        if ("month".equals(patrolTaskBean.getPlan().getCycle())) {
            Iterator<Integer> it = patrolTaskBean.getPlan().getDate().iterator();
            while (it.hasNext()) {
                if (it.next().intValue() == calendar.get(5)) {
                    list.add(patrolTaskBean);
                    return;
                }
            }
        }
    }

    private void addWeeklyPatrolTask(Calendar calendar, List<PatrolTaskBean> list, PatrolTaskBean patrolTaskBean) {
        if ("week".equals(patrolTaskBean.getPlan().getCycle())) {
            Iterator<Integer> it = patrolTaskBean.getPlan().getDate().iterator();
            while (it.hasNext()) {
                int intValue = it.next().intValue();
                int i = 7;
                int i2 = calendar.get(7);
                if (i2 > 1) {
                    i = i2 - 1;
                    continue;
                }
                if (intValue == i) {
                    list.add(patrolTaskBean);
                    return;
                }
            }
        }
    }

    private void addTempPatrolTask(List<PatrolTaskBean> list, PatrolTaskBean patrolTaskBean) {
        String format = this.mDateFormat.format(new Date());
        String format2 = this.mDateFormat.format(Long.valueOf(patrolTaskBean.getPlan().getCreate()));
        if (Plan.ONE_TYPE.equals(patrolTaskBean.getPlan().getCycle()) && format.equals(format2)) {
            list.add(patrolTaskBean);
        }
    }

    public List<PatrolTaskBean> findAllNeedExecutePatrolTask(Realm realm, String str) {
        Log.d("PATROL_TASK_TAG", "findAllNeedExecutePatrolTask nowTime=" + str);
        RealmResults<E> findAll = realm.where(PatrolTaskBean.class).findAll();
        List<E> copyFromRealm = findAll != null ? realm.copyFromRealm(findAll) : null;
        if (copyFromRealm == null || copyFromRealm.size() <= 0) {
            return null;
        }
        ArrayList<PatrolTaskBean> arrayList = new ArrayList<>();
        for (E e : copyFromRealm) {
            String start = e.getPlan().getStart();
            Log.v("PATROL_TASK_TAG", "findAllNeedExecutePatrolTask task=" + e.toString());
            if (start.compareTo(str) > 0 && start.compareTo("24:00") <= 0) {
                arrayList.add(e);
            }
        }
        Calendar instance = Calendar.getInstance();
        ArrayList arrayList2 = new ArrayList();
        for (PatrolTaskBean patrolTaskBean : arrayList) {
            addDailyPatrolTask(arrayList2, patrolTaskBean);
            addMonthlyPatrolTask(instance, arrayList2, patrolTaskBean);
            addWeeklyPatrolTask(instance, arrayList2, patrolTaskBean);
            addTempPatrolTask(arrayList2, patrolTaskBean);
        }
        Log.d("PATROL_TASK_TAG", "findAllNeedExecutePatrolTask patrolTaskList size=" + arrayList2.size());
        return arrayList2;
    }

    public List<PatrolTaskBean> findAllCompletePatrolTask(Realm realm, String str) {
        Log.d("PATROL_TASK_TAG", "findAllCompletePatrolTask nowTime=" + str);
        RealmResults<E> findAll = realm.where(PatrolTaskBean.class).findAll();
        List<E> copyFromRealm = findAll != null ? realm.copyFromRealm(findAll) : null;
        if (copyFromRealm == null || copyFromRealm.size() <= 0) {
            return null;
        }
        ArrayList<PatrolTaskBean> arrayList = new ArrayList<>();
        for (E e : copyFromRealm) {
            String start = e.getPlan().getStart();
            if (start.compareTo(str) <= 0 && start.compareTo("00:00") > 0) {
                arrayList.add(e);
            }
        }
        Calendar instance = Calendar.getInstance();
        ArrayList arrayList2 = new ArrayList();
        for (PatrolTaskBean patrolTaskBean : arrayList) {
            addDailyPatrolTask(arrayList2, patrolTaskBean);
            addMonthlyPatrolTask(instance, arrayList2, patrolTaskBean);
            addWeeklyPatrolTask(instance, arrayList2, patrolTaskBean);
            addTempPatrolTask(arrayList2, patrolTaskBean);
        }
        Log.d("PATROL_TASK_TAG", "findAllCompletePatrolTask patrolTaskList size=" + arrayList2.size());
        return arrayList2;
    }

    public void deletePath(String str) {
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.beginTransaction();
        newRealmInstance.where(PathBean.class).equalTo("pathId", str).findAll().deleteAllFromRealm();
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
        Realm.compactRealm(newRealmInstance.getConfiguration());
    }

    public void deleteAllPath() {
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.beginTransaction();
        newRealmInstance.where(PathBean.class).findAll().deleteAllFromRealm();
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
        Realm.compactRealm(newRealmInstance.getConfiguration());
    }

    public void insertOrUpdatePath(PathBean pathBean) {
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.beginTransaction();
        newRealmInstance.insertOrUpdate((RealmModel) pathBean);
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
    }

    public List<PathBean> findAllPath() {
        Realm newRealmInstance = newRealmInstance();
        List<PathBean> copyFromRealm = newRealmInstance.copyFromRealm(newRealmInstance.where(PathBean.class).findAll());
        newRealmInstance.close();
        return copyFromRealm;
    }

    public void insertOrUpdateTimerReceptionTask(Realm realm, TimerReceptionTaskBean timerReceptionTaskBean) {
        realm.beginTransaction();
        realm.insertOrUpdate((RealmModel) timerReceptionTaskBean);
        realm.commitTransaction();
    }

    public void deleteAllTimerReceptionTask(Realm realm) {
        realm.beginTransaction();
        realm.where(TimerReceptionTaskBean.class).findAll().deleteAllFromRealm();
        realm.commitTransaction();
    }

    public List<TimerReceptionTaskBean> findTodayTimerReceptionTask() {
        Realm newRealmInstance = newRealmInstance();
        List<E> copyFromRealm = newRealmInstance.copyFromRealm(newRealmInstance.where(TimerReceptionTaskBean.class).findAll());
        newRealmInstance.close();
        Log.i("Date----", "receptionTaskList: " + copyFromRealm);
        if (copyFromRealm == null) {
            return null;
        }
        Calendar instance = Calendar.getInstance();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < copyFromRealm.size(); i++) {
            TimerReceptionTaskBean timerReceptionTaskBean = (TimerReceptionTaskBean) copyFromRealm.get(i);
            if (timerReceptionTaskBean.isEnable()) {
                addWeeklyTimerReceptionTask(instance, timerReceptionTaskBean, arrayList);
                addDayTimerReceptionTask(timerReceptionTaskBean, arrayList);
                addMonthTimerReceptionTask(instance, timerReceptionTaskBean, arrayList);
            }
        }
        return arrayList;
    }

    private void addDayTimerReceptionTask(TimerReceptionTaskBean timerReceptionTaskBean, List<TimerReceptionTaskBean> list) {
        if (timerReceptionTaskBean != null && "day".equals(timerReceptionTaskBean.getCycletype())) {
            list.add(timerReceptionTaskBean);
        }
    }

    private void addWeeklyTimerReceptionTask(Calendar calendar, TimerReceptionTaskBean timerReceptionTaskBean, List<TimerReceptionTaskBean> list) {
        String taskdatas;
        if (timerReceptionTaskBean != null && "week".equals(timerReceptionTaskBean.getCycletype()) && (taskdatas = timerReceptionTaskBean.getTaskdatas()) != null) {
            String[] split = taskdatas.split(",");
            int i = 7;
            int i2 = calendar.get(7);
            if (i2 > 1) {
                i = i2 - 1;
            }
            for (String equals : split) {
                if (String.valueOf(i).equals(equals)) {
                    list.add(timerReceptionTaskBean);
                    return;
                }
            }
        }
    }

    private void addMonthTimerReceptionTask(Calendar calendar, TimerReceptionTaskBean timerReceptionTaskBean, List<TimerReceptionTaskBean> list) {
        String taskdatas;
        if (timerReceptionTaskBean != null && "month".equals(timerReceptionTaskBean.getCycletype()) && (taskdatas = timerReceptionTaskBean.getTaskdatas()) != null) {
            String[] split = taskdatas.split(",");
            int length = split.length;
            int i = 0;
            while (i < length) {
                String str = split[i];
                try {
                    if (Integer.parseInt(str) == calendar.get(5)) {
                        list.add(timerReceptionTaskBean);
                        return;
                    }
                    i++;
                } catch (NumberFormatException unused) {
                }
            }
        }
    }

    public List<TemRecord> findTemRecordByOutThreshold(long j, int i) {
        Realm newRealmInstance = newRealmInstance();
        List<E> arrayList = new ArrayList<>();
        if (newRealmInstance.where(TemRecord.class).findAll().size() >= i) {
            arrayList = newRealmInstance.copyFromRealm(newRealmInstance.where(TemRecord.class).sort("createtime").limit(j).findAll());
        }
        newRealmInstance.close();
        return arrayList;
    }

    public TemUploadRecord findOssUnUploadTemRecord2TemUploadRecord(String str, long j) {
        Realm newRealmInstance = newRealmInstance();
        RealmResults<E> findAll = newRealmInstance.where(TemRecord.class).isNotNull("pathname").or().isEmpty("staffno").sort("createtime", Sort.DESCENDING).limit(j).findAll();
        if (findAll == null || findAll.isEmpty()) {
            newRealmInstance.close();
            return null;
        }
        newRealmInstance.beginTransaction();
        String uuid = UUID.randomUUID().toString();
        TemUploadRecord temUploadRecord = (TemUploadRecord) newRealmInstance.createObject(TemUploadRecord.class, Long.valueOf(System.currentTimeMillis() / 1000));
        temUploadRecord.setCode(uuid);
        temUploadRecord.setRobot(str);
        temUploadRecord.getDatas().addAll(findAll);
        newRealmInstance.commitTransaction();
        TemUploadRecord temUploadRecord2 = (TemUploadRecord) newRealmInstance.copyFromRealm(temUploadRecord);
        newRealmInstance.close();
        return temUploadRecord2;
    }

    public List<TemRecord> findAllOssUnUploadTemRecord() {
        Realm newRealmInstance = newRealmInstance();
        List<TemRecord> copyFromRealm = newRealmInstance.copyFromRealm(newRealmInstance.where(TemRecord.class).isNull("pathname").and().isNotEmpty("staffno").findAll());
        newRealmInstance.close();
        return copyFromRealm;
    }

    public void deleteTactics() {
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.beginTransaction();
        newRealmInstance.where(Tactics.class).findAll().deleteAllFromRealm();
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
        Realm.compactRealm(newRealmInstance.getConfiguration());
    }

    public void insertTactic(List<Tactics> list) {
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.beginTransaction();
        newRealmInstance.insertOrUpdate((Collection<? extends RealmModel>) list);
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
    }

    public Tactics findTactics(String str, long j) {
        Tactics tactics;
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        int i = 7;
        int i2 = instance.get(7);
        if (i2 != 1) {
            i = i2 - 1;
        }
        int i3 = (instance.get(11) * 60) + instance.get(12);
        Realm newRealmInstance = newRealmInstance();
        Log.e("ClockInHelper", "findTactics id=" + str);
        Log.e("ClockInHelper", "findTactics time=" + i3);
        RealmResults<E> findAll = newRealmInstance.where(Tactics.class).findAll();
        Log.e("ClockInHelper", "findTactics findAll size=" + findAll.size());
        RealmResults<E> findAll2 = findAll.where().beginGroup().contains("person", str).and().contains("effect", i + "").endGroup().findAll();
        Log.e("ClockInHelper", "findCustomTactics size=" + findAll2.size());
        if (!findAll2.isEmpty()) {
            tactics = (Tactics) findAll2.where().beginGroup().lessThanOrEqualTo("startTimeOpen", i3).and().greaterThanOrEqualTo("startTimeClose", i3).endGroup().or().beginGroup().lessThanOrEqualTo("endTimeOpen", i3).and().greaterThanOrEqualTo("endTimeClose", i3).endGroup().findFirst();
        } else {
            tactics = (Tactics) findAll.where().isNull("person").and().isNull("effect").beginGroup().lessThanOrEqualTo("startTimeOpen", i3).and().greaterThanOrEqualTo("startTimeClose", i3).or().lessThanOrEqualTo("endTimeOpen", i3).and().greaterThanOrEqualTo("endTimeClose", i3).endGroup().findFirst();
        }
        if (tactics != null) {
            tactics = (Tactics) newRealmInstance.copyFromRealm(tactics);
            if (tactics.getPerson() != null) {
                Log.e("ClockInHelper", "" + tactics.getPerson().contains(str));
            }
        }
        newRealmInstance.close();
        return tactics;
    }

    public Attendance findClockIn(String str) {
        Realm newRealmInstance = newRealmInstance();
        Attendance attendance = (Attendance) newRealmInstance.where(Attendance.class).equalTo("employeeId", str).sort("checkTime", Sort.DESCENDING).findFirst();
        if (attendance != null) {
            attendance = (Attendance) newRealmInstance.copyFromRealm(attendance);
        }
        newRealmInstance.close();
        return attendance;
    }

    public List<Attendance> findAllUnClockIn() {
        Realm newRealmInstance = newRealmInstance();
        List<Attendance> copyFromRealm = newRealmInstance.copyFromRealm(newRealmInstance.where(Attendance.class).equalTo("isCommit", (Boolean) false).findAll());
        newRealmInstance.close();
        return copyFromRealm;
    }

    public int deleteCommitClockIn() {
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.beginTransaction();
        RealmResults<E> findAll = newRealmInstance.where(Attendance.class).equalTo("isCommit", (Boolean) true).and().lessThan("checkTime", (System.currentTimeMillis() - DateUtils.MILLIS_PER_DAY) / 1000).findAll();
        int size = findAll.size();
        findAll.deleteAllFromRealm();
        try {
            if (newRealmInstance.where(Attendance.class).findAll().isEmpty()) {
                newRealmInstance.where(RobotBean.class).findAll().deleteAllFromRealm();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
        Realm.compactRealm(newRealmInstance.getConfiguration());
        return size;
    }

    public void insertAttendance(Attendance attendance) {
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.beginTransaction();
        newRealmInstance.copyToRealmOrUpdate(attendance, new ImportFlag[0]);
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
    }

    public GetAdListsBeanR findTimeCountAdListById(Realm realm, String str) {
        GetAdListsBeanR getAdListsBeanR = (GetAdListsBeanR) realm.where(GetAdListsBeanR.class).greaterThan("timeLimit", 0).or().greaterThan("countLimit", 0).and().equalTo("id", str).findFirst();
        if (getAdListsBeanR == null) {
            return null;
        }
        return (GetAdListsBeanR) realm.copyFromRealm(getAdListsBeanR);
    }

    public void insertAdStat(AdStat adStat) {
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.beginTransaction();
        newRealmInstance.insertOrUpdate((RealmModel) adStat);
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
    }

    public List<AdStat> findAllAdStat() {
        Realm newRealmInstance = newRealmInstance();
        List<AdStat> copyFromRealm = newRealmInstance.copyFromRealm(newRealmInstance.where(AdStat.class).findAll());
        newRealmInstance.close();
        return copyFromRealm;
    }

    public List<AdStat> findAdStats(long j) {
        Realm newRealmInstance = newRealmInstance();
        List<AdStat> copyFromRealm = newRealmInstance.copyFromRealm(findAdStats(newRealmInstance, j));
        newRealmInstance.close();
        return copyFromRealm;
    }

    private RealmResults<AdStat> findAdStats(Realm realm, long j) {
        return realm.where(AdStat.class).lessThan("timestamp", j).findAll();
    }

    public boolean deleteAdStats(long j) {
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.beginTransaction();
        boolean deleteAllFromRealm = findAdStats(newRealmInstance, j).deleteAllFromRealm();
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
        Realm.compactRealm(newRealmInstance.getConfiguration());
        return deleteAllFromRealm;
    }

    public void insertAdViewStat(AdWatchStat adWatchStat) {
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.beginTransaction();
        newRealmInstance.insertOrUpdate((RealmModel) adWatchStat);
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
    }

    public List<AdWatchStat> findAllAdViewStat() {
        Realm newRealmInstance = newRealmInstance();
        List<AdWatchStat> copyFromRealm = newRealmInstance.copyFromRealm(newRealmInstance.where(AdWatchStat.class).findAll());
        newRealmInstance.close();
        return copyFromRealm;
    }

    public List<AdWatchStat> findAdViewStats(long j) {
        Realm newRealmInstance = newRealmInstance();
        List<AdWatchStat> copyFromRealm = newRealmInstance.copyFromRealm(findAdViewStats(newRealmInstance, j));
        newRealmInstance.close();
        return copyFromRealm;
    }

    private RealmResults<AdWatchStat> findAdViewStats(Realm realm, long j) {
        return realm.where(AdWatchStat.class).lessThan("timestamp", j).findAll();
    }

    public boolean deleteAdViewStat(long j) {
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.beginTransaction();
        boolean deleteAllFromRealm = findAdViewStats(newRealmInstance, j).deleteAllFromRealm();
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
        Realm.compactRealm(newRealmInstance.getConfiguration());
        return deleteAllFromRealm;
    }

    public void insertRobotPersonStat(RobotPersonStat robotPersonStat) {
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.beginTransaction();
        newRealmInstance.insertOrUpdate((RealmModel) robotPersonStat);
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
    }

    public List<RobotPersonStat> findAllRobotPersonStat() {
        Realm newRealmInstance = newRealmInstance();
        List<RobotPersonStat> copyFromRealm = newRealmInstance.copyFromRealm(newRealmInstance.where(RobotPersonStat.class).findAll());
        newRealmInstance.close();
        return copyFromRealm;
    }

    public List<RobotPersonStat> findRobotPersonStats(long j) {
        Realm newRealmInstance = newRealmInstance();
        List<RobotPersonStat> copyFromRealm = newRealmInstance.copyFromRealm(findRobotPersonStats(newRealmInstance, j));
        newRealmInstance.close();
        return copyFromRealm;
    }

    private RealmResults<RobotPersonStat> findRobotPersonStats(Realm realm, long j) {
        return realm.where(RobotPersonStat.class).lessThan("timestamp", j).findAll();
    }

    public boolean deleteRobotPersonStat(long j) {
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.beginTransaction();
        boolean deleteAllFromRealm = findRobotPersonStats(newRealmInstance, j).deleteAllFromRealm();
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
        Realm.compactRealm(newRealmInstance.getConfiguration());
        return deleteAllFromRealm;
    }

    public void insertStatsRecord(StatsRecord statsRecord) {
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.beginTransaction();
        newRealmInstance.copyToRealmOrUpdate(statsRecord, new ImportFlag[0]);
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
    }

    public List<StatsRecord> findAllStatsRecord() {
        Realm newRealmInstance = newRealmInstance();
        List<StatsRecord> copyFromRealm = newRealmInstance.copyFromRealm(newRealmInstance.where(StatsRecord.class).findAll());
        newRealmInstance.close();
        return copyFromRealm;
    }

    public void deleteStatsRecord(StatsRecord statsRecord) {
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.beginTransaction();
        StatsRecord statsRecord2 = (StatsRecord) newRealmInstance.where(StatsRecord.class).equalTo("path", statsRecord.getPath()).findFirst();
        if (statsRecord2 != null) {
            statsRecord2.deleteFromRealm();
        }
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
        Realm.compactRealm(newRealmInstance.getConfiguration());
    }

    public void insertOrUpdateHotelActivity(List<HotelActivitesBean> list) {
        Log.e("插入活动", "///////");
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.beginTransaction();
        newRealmInstance.insertOrUpdate((Collection<? extends RealmModel>) list);
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
    }

    public List<HotelActivitesBean> findAllHotelActivity() {
        Log.e("活动", "///////");
        Realm newRealmInstance = newRealmInstance();
        List<HotelActivitesBean> copyFromRealm = newRealmInstance.copyFromRealm(newRealmInstance.where(HotelActivitesBean.class).findAll());
        newRealmInstance.close();
        return copyFromRealm;
    }

    public HotelActivitesBean findActivityById(String str) {
        Realm newRealmInstance = newRealmInstance();
        HotelActivitesBean hotelActivitesBean = (HotelActivitesBean) newRealmInstance.copyFromRealm((HotelActivitesBean) newRealmInstance.where(HotelActivitesBean.class).equalTo("id", str).findFirst());
        newRealmInstance.close();
        return hotelActivitesBean;
    }

    public void deleteAllHotelActivity() {
        Log.e("删除活动", "...................");
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.executeTransaction(new Realm.Transaction() {
            public void execute(Realm realm) {
                RealmResults<E> findAll = realm.where(HotelActivitesBean.class).findAll();
                if (findAll != null) {
                    try {
                        findAll.deleteAllFromRealm();
                    } catch (Exception unused) {
                    }
                }
            }
        });
        newRealmInstance.close();
        Realm.compactRealm(newRealmInstance.getConfiguration());
    }

    public void insertEventTracking(EventTrackingBean eventTrackingBean) {
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.beginTransaction();
        newRealmInstance.copyToRealmOrUpdate(eventTrackingBean, new ImportFlag[0]);
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
    }

    public void deleteEventTrackingUpSuccessData(EventTrackingBean eventTrackingBean) {
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.beginTransaction();
        EventTrackingBean eventTrackingBean2 = (EventTrackingBean) newRealmInstance.where(EventTrackingBean.class).equalTo("begintimestamp", Long.valueOf(eventTrackingBean.getBegintimestamp())).findFirst();
        if (eventTrackingBean2 != null) {
            eventTrackingBean2.deleteFromRealm();
        }
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
        Realm.compactRealm(newRealmInstance.getConfiguration());
    }

    public List<EventTrackingBean> findFailedEventTracking() {
        Realm newRealmInstance = newRealmInstance();
        List<EventTrackingBean> copyFromRealm = newRealmInstance.copyFromRealm(newRealmInstance.where(EventTrackingBean.class).findAll());
        newRealmInstance.close();
        return copyFromRealm;
    }
}
