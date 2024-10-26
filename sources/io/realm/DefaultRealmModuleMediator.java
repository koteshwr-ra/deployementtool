package io.realm;

import android.util.JsonReader;
import com.ciot.realm.db.Attendance;
import com.ciot.realm.db.ChildTask;
import com.ciot.realm.db.EmployeeBean;
import com.ciot.realm.db.EventTrackingBean;
import com.ciot.realm.db.HotelActivitesBean;
import com.ciot.realm.db.LockMode;
import com.ciot.realm.db.MarkerPoint;
import com.ciot.realm.db.MusicBean;
import com.ciot.realm.db.Person;
import com.ciot.realm.db.PersonInfoBean;
import com.ciot.realm.db.PointF;
import com.ciot.realm.db.Record;
import com.ciot.realm.db.RecordLockMode;
import com.ciot.realm.db.RegisterWithAppointmentResponse;
import com.ciot.realm.db.RegisteredRecordInfo;
import com.ciot.realm.db.RobotBean;
import com.ciot.realm.db.Tactics;
import com.ciot.realm.db.Task;
import com.ciot.realm.db.TemRecord;
import com.ciot.realm.db.TemUploadRecord;
import com.ciot.realm.db.ThermometryRecordBean;
import com.ciot.realm.db.TimeBean;
import com.ciot.realm.db.ad.AdvertisementsBean;
import com.ciot.realm.db.ad.BeginBean;
import com.ciot.realm.db.ad.CycleBean;
import com.ciot.realm.db.ad.EndBean;
import com.ciot.realm.db.ad.GetAdListsBeanR;
import com.ciot.realm.db.ad.HorseRaceLampsBean;
import com.ciot.realm.db.ad.ResourcesBean;
import com.ciot.realm.db.ad.TimesBean;
import com.ciot.realm.db.common.AckBean;
import com.ciot.realm.db.common.CompanyResponse;
import com.ciot.realm.db.common.Contact;
import com.ciot.realm.db.common.LocationBean;
import com.ciot.realm.db.common.RecordResponse;
import com.ciot.realm.db.common.Settings;
import com.ciot.realm.db.common.ValidateBean;
import com.ciot.realm.db.common.VisitorBean;
import com.ciot.realm.db.employee.EmployeeArcCode;
import com.ciot.realm.db.employee.EmployeeLockMode;
import com.ciot.realm.db.employee.EmployeeResponse;
import com.ciot.realm.db.patrol.Action;
import com.ciot.realm.db.patrol.FloorOriginBean;
import com.ciot.realm.db.patrol.Line;
import com.ciot.realm.db.patrol.MediaBean;
import com.ciot.realm.db.patrol.Message;
import com.ciot.realm.db.patrol.Operation;
import com.ciot.realm.db.patrol.PathBean;
import com.ciot.realm.db.patrol.PatrolTaskBean;
import com.ciot.realm.db.patrol.Place;
import com.ciot.realm.db.patrol.Plan;
import com.ciot.realm.db.patrol.Point;
import com.ciot.realm.db.patrol.Process;
import com.ciot.realm.db.patrol.Resource;
import com.ciot.realm.db.patrol.TransitionBean;
import com.ciot.realm.db.patrol.TurnstileBean;
import com.ciot.realm.db.patrol.WaitBean;
import com.ciot.realm.db.patrol.WaterPathBean;
import com.ciot.realm.db.recommantation.DataBean;
import com.ciot.realm.db.semantic.ActionBean;
import com.ciot.realm.db.semantic.AnswerBean;
import com.ciot.realm.db.semantic.CiotResponseBean;
import com.ciot.realm.db.semantic.SemanticBean;
import com.ciot.realm.db.stat.AdStat;
import com.ciot.realm.db.stat.AdWatchStat;
import com.ciot.realm.db.stat.RobotPersonStat;
import com.ciot.realm.db.stat.StatsRecord;
import com.ciot.realm.db.timer.TimerReceptionTaskBean;
import com.ciot.realm.db.visitor.ArcCode;
import com.ciot.realm.db.visitor.MediaResult;
import com.ciot.realm.db.visitor.VisitorResponse;
import io.realm.BaseRealm;
import io.realm.annotations.RealmModule;
import io.realm.com_ciot_realm_db_AttendanceRealmProxy;
import io.realm.com_ciot_realm_db_ChildTaskRealmProxy;
import io.realm.com_ciot_realm_db_EmployeeBeanRealmProxy;
import io.realm.com_ciot_realm_db_EventTrackingBeanRealmProxy;
import io.realm.com_ciot_realm_db_HotelActivitesBeanRealmProxy;
import io.realm.com_ciot_realm_db_LockModeRealmProxy;
import io.realm.com_ciot_realm_db_MarkerPointRealmProxy;
import io.realm.com_ciot_realm_db_MusicBeanRealmProxy;
import io.realm.com_ciot_realm_db_PersonInfoBeanRealmProxy;
import io.realm.com_ciot_realm_db_PersonRealmProxy;
import io.realm.com_ciot_realm_db_PointFRealmProxy;
import io.realm.com_ciot_realm_db_RecordLockModeRealmProxy;
import io.realm.com_ciot_realm_db_RecordRealmProxy;
import io.realm.com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxy;
import io.realm.com_ciot_realm_db_RegisteredRecordInfoRealmProxy;
import io.realm.com_ciot_realm_db_RobotBeanRealmProxy;
import io.realm.com_ciot_realm_db_TacticsRealmProxy;
import io.realm.com_ciot_realm_db_TaskRealmProxy;
import io.realm.com_ciot_realm_db_TemRecordRealmProxy;
import io.realm.com_ciot_realm_db_TemUploadRecordRealmProxy;
import io.realm.com_ciot_realm_db_ThermometryRecordBeanRealmProxy;
import io.realm.com_ciot_realm_db_TimeBeanRealmProxy;
import io.realm.com_ciot_realm_db_ad_AdvertisementsBeanRealmProxy;
import io.realm.com_ciot_realm_db_ad_BeginBeanRealmProxy;
import io.realm.com_ciot_realm_db_ad_CycleBeanRealmProxy;
import io.realm.com_ciot_realm_db_ad_EndBeanRealmProxy;
import io.realm.com_ciot_realm_db_ad_GetAdListsBeanRRealmProxy;
import io.realm.com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxy;
import io.realm.com_ciot_realm_db_ad_ResourcesBeanRealmProxy;
import io.realm.com_ciot_realm_db_ad_TimesBeanRealmProxy;
import io.realm.com_ciot_realm_db_common_AckBeanRealmProxy;
import io.realm.com_ciot_realm_db_common_CompanyResponseRealmProxy;
import io.realm.com_ciot_realm_db_common_ContactRealmProxy;
import io.realm.com_ciot_realm_db_common_LocationBeanRealmProxy;
import io.realm.com_ciot_realm_db_common_RecordResponseRealmProxy;
import io.realm.com_ciot_realm_db_common_SettingsRealmProxy;
import io.realm.com_ciot_realm_db_common_ValidateBeanRealmProxy;
import io.realm.com_ciot_realm_db_common_VisitorBeanRealmProxy;
import io.realm.com_ciot_realm_db_employee_EmployeeArcCodeRealmProxy;
import io.realm.com_ciot_realm_db_employee_EmployeeLockModeRealmProxy;
import io.realm.com_ciot_realm_db_employee_EmployeeResponseRealmProxy;
import io.realm.com_ciot_realm_db_patrol_ActionRealmProxy;
import io.realm.com_ciot_realm_db_patrol_FloorOriginBeanRealmProxy;
import io.realm.com_ciot_realm_db_patrol_LineRealmProxy;
import io.realm.com_ciot_realm_db_patrol_MediaBeanRealmProxy;
import io.realm.com_ciot_realm_db_patrol_MessageRealmProxy;
import io.realm.com_ciot_realm_db_patrol_OperationRealmProxy;
import io.realm.com_ciot_realm_db_patrol_PathBeanRealmProxy;
import io.realm.com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxy;
import io.realm.com_ciot_realm_db_patrol_PlaceRealmProxy;
import io.realm.com_ciot_realm_db_patrol_PlanRealmProxy;
import io.realm.com_ciot_realm_db_patrol_PointRealmProxy;
import io.realm.com_ciot_realm_db_patrol_ProcessRealmProxy;
import io.realm.com_ciot_realm_db_patrol_ResourceRealmProxy;
import io.realm.com_ciot_realm_db_patrol_TransitionBeanRealmProxy;
import io.realm.com_ciot_realm_db_patrol_TurnstileBeanRealmProxy;
import io.realm.com_ciot_realm_db_patrol_WaitBeanRealmProxy;
import io.realm.com_ciot_realm_db_patrol_WaterPathBeanRealmProxy;
import io.realm.com_ciot_realm_db_recommantation_DataBeanRealmProxy;
import io.realm.com_ciot_realm_db_semantic_ActionBeanRealmProxy;
import io.realm.com_ciot_realm_db_semantic_AnswerBeanRealmProxy;
import io.realm.com_ciot_realm_db_semantic_CiotResponseBeanRealmProxy;
import io.realm.com_ciot_realm_db_semantic_SemanticBeanRealmProxy;
import io.realm.com_ciot_realm_db_stat_AdStatRealmProxy;
import io.realm.com_ciot_realm_db_stat_AdWatchStatRealmProxy;
import io.realm.com_ciot_realm_db_stat_RobotPersonStatRealmProxy;
import io.realm.com_ciot_realm_db_stat_StatsRecordRealmProxy;
import io.realm.com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxy;
import io.realm.com_ciot_realm_db_visitor_ArcCodeRealmProxy;
import io.realm.com_ciot_realm_db_visitor_MediaResultRealmProxy;
import io.realm.com_ciot_realm_db_visitor_VisitorResponseRealmProxy;
import io.realm.internal.ColumnInfo;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.RealmProxyMediator;
import io.realm.internal.Row;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

@RealmModule
class DefaultRealmModuleMediator extends RealmProxyMediator {
    private static final Set<Class<? extends RealmModel>> MODEL_CLASSES;

    public boolean transformerApplied() {
        return true;
    }

    DefaultRealmModuleMediator() {
    }

    static {
        HashSet hashSet = new HashSet(71);
        hashSet.add(AdvertisementsBean.class);
        hashSet.add(BeginBean.class);
        hashSet.add(CycleBean.class);
        hashSet.add(EndBean.class);
        hashSet.add(GetAdListsBeanR.class);
        hashSet.add(HorseRaceLampsBean.class);
        hashSet.add(ResourcesBean.class);
        hashSet.add(TimesBean.class);
        hashSet.add(Attendance.class);
        hashSet.add(ChildTask.class);
        hashSet.add(AckBean.class);
        hashSet.add(CompanyResponse.class);
        hashSet.add(Contact.class);
        hashSet.add(LocationBean.class);
        hashSet.add(RecordResponse.class);
        hashSet.add(Settings.class);
        hashSet.add(ValidateBean.class);
        hashSet.add(VisitorBean.class);
        hashSet.add(EmployeeArcCode.class);
        hashSet.add(EmployeeLockMode.class);
        hashSet.add(EmployeeResponse.class);
        hashSet.add(EmployeeBean.class);
        hashSet.add(EventTrackingBean.class);
        hashSet.add(HotelActivitesBean.class);
        hashSet.add(LockMode.class);
        hashSet.add(MarkerPoint.class);
        hashSet.add(MusicBean.class);
        hashSet.add(Action.class);
        hashSet.add(FloorOriginBean.class);
        hashSet.add(Line.class);
        hashSet.add(MediaBean.class);
        hashSet.add(Message.class);
        hashSet.add(Operation.class);
        hashSet.add(PathBean.class);
        hashSet.add(PatrolTaskBean.class);
        hashSet.add(Place.class);
        hashSet.add(Plan.class);
        hashSet.add(Point.class);
        hashSet.add(Process.class);
        hashSet.add(Resource.class);
        hashSet.add(TransitionBean.class);
        hashSet.add(TurnstileBean.class);
        hashSet.add(WaitBean.class);
        hashSet.add(WaterPathBean.class);
        hashSet.add(Person.class);
        hashSet.add(PersonInfoBean.class);
        hashSet.add(PointF.class);
        hashSet.add(DataBean.class);
        hashSet.add(Record.class);
        hashSet.add(RecordLockMode.class);
        hashSet.add(RegisteredRecordInfo.class);
        hashSet.add(RegisterWithAppointmentResponse.class);
        hashSet.add(RobotBean.class);
        hashSet.add(ActionBean.class);
        hashSet.add(AnswerBean.class);
        hashSet.add(CiotResponseBean.class);
        hashSet.add(SemanticBean.class);
        hashSet.add(AdStat.class);
        hashSet.add(AdWatchStat.class);
        hashSet.add(RobotPersonStat.class);
        hashSet.add(StatsRecord.class);
        hashSet.add(Tactics.class);
        hashSet.add(Task.class);
        hashSet.add(TemRecord.class);
        hashSet.add(TemUploadRecord.class);
        hashSet.add(ThermometryRecordBean.class);
        hashSet.add(TimeBean.class);
        hashSet.add(TimerReceptionTaskBean.class);
        hashSet.add(ArcCode.class);
        hashSet.add(MediaResult.class);
        hashSet.add(VisitorResponse.class);
        MODEL_CLASSES = Collections.unmodifiableSet(hashSet);
    }

    public Map<Class<? extends RealmModel>, OsObjectSchemaInfo> getExpectedObjectSchemaInfoMap() {
        HashMap hashMap = new HashMap(71);
        hashMap.put(AdvertisementsBean.class, com_ciot_realm_db_ad_AdvertisementsBeanRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(BeginBean.class, com_ciot_realm_db_ad_BeginBeanRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(CycleBean.class, com_ciot_realm_db_ad_CycleBeanRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(EndBean.class, com_ciot_realm_db_ad_EndBeanRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(GetAdListsBeanR.class, com_ciot_realm_db_ad_GetAdListsBeanRRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(HorseRaceLampsBean.class, com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(ResourcesBean.class, com_ciot_realm_db_ad_ResourcesBeanRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(TimesBean.class, com_ciot_realm_db_ad_TimesBeanRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(Attendance.class, com_ciot_realm_db_AttendanceRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(ChildTask.class, com_ciot_realm_db_ChildTaskRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(AckBean.class, com_ciot_realm_db_common_AckBeanRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(CompanyResponse.class, com_ciot_realm_db_common_CompanyResponseRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(Contact.class, com_ciot_realm_db_common_ContactRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(LocationBean.class, com_ciot_realm_db_common_LocationBeanRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(RecordResponse.class, com_ciot_realm_db_common_RecordResponseRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(Settings.class, com_ciot_realm_db_common_SettingsRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(ValidateBean.class, com_ciot_realm_db_common_ValidateBeanRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(VisitorBean.class, com_ciot_realm_db_common_VisitorBeanRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(EmployeeArcCode.class, com_ciot_realm_db_employee_EmployeeArcCodeRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(EmployeeLockMode.class, com_ciot_realm_db_employee_EmployeeLockModeRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(EmployeeResponse.class, com_ciot_realm_db_employee_EmployeeResponseRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(EmployeeBean.class, com_ciot_realm_db_EmployeeBeanRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(EventTrackingBean.class, com_ciot_realm_db_EventTrackingBeanRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(HotelActivitesBean.class, com_ciot_realm_db_HotelActivitesBeanRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(LockMode.class, com_ciot_realm_db_LockModeRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(MarkerPoint.class, com_ciot_realm_db_MarkerPointRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(MusicBean.class, com_ciot_realm_db_MusicBeanRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(Action.class, com_ciot_realm_db_patrol_ActionRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(FloorOriginBean.class, com_ciot_realm_db_patrol_FloorOriginBeanRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(Line.class, com_ciot_realm_db_patrol_LineRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(MediaBean.class, com_ciot_realm_db_patrol_MediaBeanRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(Message.class, com_ciot_realm_db_patrol_MessageRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(Operation.class, com_ciot_realm_db_patrol_OperationRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(PathBean.class, com_ciot_realm_db_patrol_PathBeanRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(PatrolTaskBean.class, com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(Place.class, com_ciot_realm_db_patrol_PlaceRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(Plan.class, com_ciot_realm_db_patrol_PlanRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(Point.class, com_ciot_realm_db_patrol_PointRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(Process.class, com_ciot_realm_db_patrol_ProcessRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(Resource.class, com_ciot_realm_db_patrol_ResourceRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(TransitionBean.class, com_ciot_realm_db_patrol_TransitionBeanRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(TurnstileBean.class, com_ciot_realm_db_patrol_TurnstileBeanRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(WaitBean.class, com_ciot_realm_db_patrol_WaitBeanRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(WaterPathBean.class, com_ciot_realm_db_patrol_WaterPathBeanRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(Person.class, com_ciot_realm_db_PersonRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(PersonInfoBean.class, com_ciot_realm_db_PersonInfoBeanRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(PointF.class, com_ciot_realm_db_PointFRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(DataBean.class, com_ciot_realm_db_recommantation_DataBeanRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(Record.class, com_ciot_realm_db_RecordRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(RecordLockMode.class, com_ciot_realm_db_RecordLockModeRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(RegisteredRecordInfo.class, com_ciot_realm_db_RegisteredRecordInfoRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(RegisterWithAppointmentResponse.class, com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(RobotBean.class, com_ciot_realm_db_RobotBeanRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(ActionBean.class, com_ciot_realm_db_semantic_ActionBeanRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(AnswerBean.class, com_ciot_realm_db_semantic_AnswerBeanRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(CiotResponseBean.class, com_ciot_realm_db_semantic_CiotResponseBeanRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(SemanticBean.class, com_ciot_realm_db_semantic_SemanticBeanRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(AdStat.class, com_ciot_realm_db_stat_AdStatRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(AdWatchStat.class, com_ciot_realm_db_stat_AdWatchStatRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(RobotPersonStat.class, com_ciot_realm_db_stat_RobotPersonStatRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(StatsRecord.class, com_ciot_realm_db_stat_StatsRecordRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(Tactics.class, com_ciot_realm_db_TacticsRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(Task.class, com_ciot_realm_db_TaskRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(TemRecord.class, com_ciot_realm_db_TemRecordRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(TemUploadRecord.class, com_ciot_realm_db_TemUploadRecordRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(ThermometryRecordBean.class, com_ciot_realm_db_ThermometryRecordBeanRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(TimeBean.class, com_ciot_realm_db_TimeBeanRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(TimerReceptionTaskBean.class, com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(ArcCode.class, com_ciot_realm_db_visitor_ArcCodeRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(MediaResult.class, com_ciot_realm_db_visitor_MediaResultRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(VisitorResponse.class, com_ciot_realm_db_visitor_VisitorResponseRealmProxy.getExpectedObjectSchemaInfo());
        return hashMap;
    }

    public ColumnInfo createColumnInfo(Class<? extends RealmModel> cls, OsSchemaInfo osSchemaInfo) {
        checkClass(cls);
        if (cls.equals(AdvertisementsBean.class)) {
            return com_ciot_realm_db_ad_AdvertisementsBeanRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(BeginBean.class)) {
            return com_ciot_realm_db_ad_BeginBeanRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(CycleBean.class)) {
            return com_ciot_realm_db_ad_CycleBeanRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(EndBean.class)) {
            return com_ciot_realm_db_ad_EndBeanRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(GetAdListsBeanR.class)) {
            return com_ciot_realm_db_ad_GetAdListsBeanRRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(HorseRaceLampsBean.class)) {
            return com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(ResourcesBean.class)) {
            return com_ciot_realm_db_ad_ResourcesBeanRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(TimesBean.class)) {
            return com_ciot_realm_db_ad_TimesBeanRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(Attendance.class)) {
            return com_ciot_realm_db_AttendanceRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(ChildTask.class)) {
            return com_ciot_realm_db_ChildTaskRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(AckBean.class)) {
            return com_ciot_realm_db_common_AckBeanRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(CompanyResponse.class)) {
            return com_ciot_realm_db_common_CompanyResponseRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(Contact.class)) {
            return com_ciot_realm_db_common_ContactRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(LocationBean.class)) {
            return com_ciot_realm_db_common_LocationBeanRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(RecordResponse.class)) {
            return com_ciot_realm_db_common_RecordResponseRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(Settings.class)) {
            return com_ciot_realm_db_common_SettingsRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(ValidateBean.class)) {
            return com_ciot_realm_db_common_ValidateBeanRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(VisitorBean.class)) {
            return com_ciot_realm_db_common_VisitorBeanRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(EmployeeArcCode.class)) {
            return com_ciot_realm_db_employee_EmployeeArcCodeRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(EmployeeLockMode.class)) {
            return com_ciot_realm_db_employee_EmployeeLockModeRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(EmployeeResponse.class)) {
            return com_ciot_realm_db_employee_EmployeeResponseRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(EmployeeBean.class)) {
            return com_ciot_realm_db_EmployeeBeanRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(EventTrackingBean.class)) {
            return com_ciot_realm_db_EventTrackingBeanRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(HotelActivitesBean.class)) {
            return com_ciot_realm_db_HotelActivitesBeanRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(LockMode.class)) {
            return com_ciot_realm_db_LockModeRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(MarkerPoint.class)) {
            return com_ciot_realm_db_MarkerPointRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(MusicBean.class)) {
            return com_ciot_realm_db_MusicBeanRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(Action.class)) {
            return com_ciot_realm_db_patrol_ActionRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(FloorOriginBean.class)) {
            return com_ciot_realm_db_patrol_FloorOriginBeanRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(Line.class)) {
            return com_ciot_realm_db_patrol_LineRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(MediaBean.class)) {
            return com_ciot_realm_db_patrol_MediaBeanRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(Message.class)) {
            return com_ciot_realm_db_patrol_MessageRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(Operation.class)) {
            return com_ciot_realm_db_patrol_OperationRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(PathBean.class)) {
            return com_ciot_realm_db_patrol_PathBeanRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(PatrolTaskBean.class)) {
            return com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(Place.class)) {
            return com_ciot_realm_db_patrol_PlaceRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(Plan.class)) {
            return com_ciot_realm_db_patrol_PlanRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(Point.class)) {
            return com_ciot_realm_db_patrol_PointRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(Process.class)) {
            return com_ciot_realm_db_patrol_ProcessRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(Resource.class)) {
            return com_ciot_realm_db_patrol_ResourceRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(TransitionBean.class)) {
            return com_ciot_realm_db_patrol_TransitionBeanRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(TurnstileBean.class)) {
            return com_ciot_realm_db_patrol_TurnstileBeanRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(WaitBean.class)) {
            return com_ciot_realm_db_patrol_WaitBeanRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(WaterPathBean.class)) {
            return com_ciot_realm_db_patrol_WaterPathBeanRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(Person.class)) {
            return com_ciot_realm_db_PersonRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(PersonInfoBean.class)) {
            return com_ciot_realm_db_PersonInfoBeanRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(PointF.class)) {
            return com_ciot_realm_db_PointFRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(DataBean.class)) {
            return com_ciot_realm_db_recommantation_DataBeanRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(Record.class)) {
            return com_ciot_realm_db_RecordRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(RecordLockMode.class)) {
            return com_ciot_realm_db_RecordLockModeRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(RegisteredRecordInfo.class)) {
            return com_ciot_realm_db_RegisteredRecordInfoRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(RegisterWithAppointmentResponse.class)) {
            return com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(RobotBean.class)) {
            return com_ciot_realm_db_RobotBeanRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(ActionBean.class)) {
            return com_ciot_realm_db_semantic_ActionBeanRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(AnswerBean.class)) {
            return com_ciot_realm_db_semantic_AnswerBeanRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(CiotResponseBean.class)) {
            return com_ciot_realm_db_semantic_CiotResponseBeanRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(SemanticBean.class)) {
            return com_ciot_realm_db_semantic_SemanticBeanRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(AdStat.class)) {
            return com_ciot_realm_db_stat_AdStatRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(AdWatchStat.class)) {
            return com_ciot_realm_db_stat_AdWatchStatRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(RobotPersonStat.class)) {
            return com_ciot_realm_db_stat_RobotPersonStatRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(StatsRecord.class)) {
            return com_ciot_realm_db_stat_StatsRecordRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(Tactics.class)) {
            return com_ciot_realm_db_TacticsRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(Task.class)) {
            return com_ciot_realm_db_TaskRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(TemRecord.class)) {
            return com_ciot_realm_db_TemRecordRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(TemUploadRecord.class)) {
            return com_ciot_realm_db_TemUploadRecordRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(ThermometryRecordBean.class)) {
            return com_ciot_realm_db_ThermometryRecordBeanRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(TimeBean.class)) {
            return com_ciot_realm_db_TimeBeanRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(TimerReceptionTaskBean.class)) {
            return com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(ArcCode.class)) {
            return com_ciot_realm_db_visitor_ArcCodeRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(MediaResult.class)) {
            return com_ciot_realm_db_visitor_MediaResultRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(VisitorResponse.class)) {
            return com_ciot_realm_db_visitor_VisitorResponseRealmProxy.createColumnInfo(osSchemaInfo);
        }
        throw getMissingProxyClassException(cls);
    }

    public String getSimpleClassNameImpl(Class<? extends RealmModel> cls) {
        checkClass(cls);
        if (cls.equals(AdvertisementsBean.class)) {
            return com_ciot_realm_db_ad_AdvertisementsBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(BeginBean.class)) {
            return com_ciot_realm_db_ad_BeginBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(CycleBean.class)) {
            return com_ciot_realm_db_ad_CycleBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(EndBean.class)) {
            return com_ciot_realm_db_ad_EndBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(GetAdListsBeanR.class)) {
            return com_ciot_realm_db_ad_GetAdListsBeanRRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(HorseRaceLampsBean.class)) {
            return com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(ResourcesBean.class)) {
            return com_ciot_realm_db_ad_ResourcesBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(TimesBean.class)) {
            return com_ciot_realm_db_ad_TimesBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(Attendance.class)) {
            return com_ciot_realm_db_AttendanceRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(ChildTask.class)) {
            return com_ciot_realm_db_ChildTaskRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(AckBean.class)) {
            return com_ciot_realm_db_common_AckBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(CompanyResponse.class)) {
            return com_ciot_realm_db_common_CompanyResponseRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(Contact.class)) {
            return com_ciot_realm_db_common_ContactRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(LocationBean.class)) {
            return com_ciot_realm_db_common_LocationBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(RecordResponse.class)) {
            return com_ciot_realm_db_common_RecordResponseRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(Settings.class)) {
            return com_ciot_realm_db_common_SettingsRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(ValidateBean.class)) {
            return com_ciot_realm_db_common_ValidateBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(VisitorBean.class)) {
            return com_ciot_realm_db_common_VisitorBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(EmployeeArcCode.class)) {
            return com_ciot_realm_db_employee_EmployeeArcCodeRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(EmployeeLockMode.class)) {
            return com_ciot_realm_db_employee_EmployeeLockModeRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(EmployeeResponse.class)) {
            return com_ciot_realm_db_employee_EmployeeResponseRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(EmployeeBean.class)) {
            return com_ciot_realm_db_EmployeeBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(EventTrackingBean.class)) {
            return com_ciot_realm_db_EventTrackingBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(HotelActivitesBean.class)) {
            return com_ciot_realm_db_HotelActivitesBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(LockMode.class)) {
            return com_ciot_realm_db_LockModeRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(MarkerPoint.class)) {
            return com_ciot_realm_db_MarkerPointRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(MusicBean.class)) {
            return com_ciot_realm_db_MusicBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(Action.class)) {
            return com_ciot_realm_db_patrol_ActionRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(FloorOriginBean.class)) {
            return com_ciot_realm_db_patrol_FloorOriginBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(Line.class)) {
            return com_ciot_realm_db_patrol_LineRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(MediaBean.class)) {
            return com_ciot_realm_db_patrol_MediaBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(Message.class)) {
            return com_ciot_realm_db_patrol_MessageRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(Operation.class)) {
            return com_ciot_realm_db_patrol_OperationRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(PathBean.class)) {
            return com_ciot_realm_db_patrol_PathBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(PatrolTaskBean.class)) {
            return com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(Place.class)) {
            return com_ciot_realm_db_patrol_PlaceRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(Plan.class)) {
            return com_ciot_realm_db_patrol_PlanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(Point.class)) {
            return com_ciot_realm_db_patrol_PointRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(Process.class)) {
            return com_ciot_realm_db_patrol_ProcessRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(Resource.class)) {
            return com_ciot_realm_db_patrol_ResourceRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(TransitionBean.class)) {
            return com_ciot_realm_db_patrol_TransitionBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(TurnstileBean.class)) {
            return com_ciot_realm_db_patrol_TurnstileBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(WaitBean.class)) {
            return "WaitBean";
        }
        if (cls.equals(WaterPathBean.class)) {
            return com_ciot_realm_db_patrol_WaterPathBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(Person.class)) {
            return com_ciot_realm_db_PersonRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(PersonInfoBean.class)) {
            return com_ciot_realm_db_PersonInfoBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(PointF.class)) {
            return com_ciot_realm_db_PointFRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(DataBean.class)) {
            return com_ciot_realm_db_recommantation_DataBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(Record.class)) {
            return com_ciot_realm_db_RecordRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(RecordLockMode.class)) {
            return com_ciot_realm_db_RecordLockModeRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(RegisteredRecordInfo.class)) {
            return com_ciot_realm_db_RegisteredRecordInfoRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(RegisterWithAppointmentResponse.class)) {
            return com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(RobotBean.class)) {
            return com_ciot_realm_db_RobotBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(ActionBean.class)) {
            return com_ciot_realm_db_semantic_ActionBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(AnswerBean.class)) {
            return com_ciot_realm_db_semantic_AnswerBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(CiotResponseBean.class)) {
            return com_ciot_realm_db_semantic_CiotResponseBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(SemanticBean.class)) {
            return com_ciot_realm_db_semantic_SemanticBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(AdStat.class)) {
            return com_ciot_realm_db_stat_AdStatRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(AdWatchStat.class)) {
            return com_ciot_realm_db_stat_AdWatchStatRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(RobotPersonStat.class)) {
            return com_ciot_realm_db_stat_RobotPersonStatRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(StatsRecord.class)) {
            return com_ciot_realm_db_stat_StatsRecordRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(Tactics.class)) {
            return com_ciot_realm_db_TacticsRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(Task.class)) {
            return com_ciot_realm_db_TaskRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(TemRecord.class)) {
            return com_ciot_realm_db_TemRecordRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(TemUploadRecord.class)) {
            return com_ciot_realm_db_TemUploadRecordRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(ThermometryRecordBean.class)) {
            return com_ciot_realm_db_ThermometryRecordBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(TimeBean.class)) {
            return com_ciot_realm_db_TimeBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(TimerReceptionTaskBean.class)) {
            return com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(ArcCode.class)) {
            return com_ciot_realm_db_visitor_ArcCodeRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(MediaResult.class)) {
            return com_ciot_realm_db_visitor_MediaResultRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        if (cls.equals(VisitorResponse.class)) {
            return com_ciot_realm_db_visitor_VisitorResponseRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        }
        throw getMissingProxyClassException(cls);
    }

    public <E extends RealmModel> E newInstance(Class<E> cls, Object obj, Row row, ColumnInfo columnInfo, boolean z, List<String> list) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        try {
            realmObjectContext.set((BaseRealm) obj, row, columnInfo, z, list);
            checkClass(cls);
            if (cls.equals(AdvertisementsBean.class)) {
                return (RealmModel) cls.cast(new com_ciot_realm_db_ad_AdvertisementsBeanRealmProxy());
            }
            if (cls.equals(BeginBean.class)) {
                E e = (RealmModel) cls.cast(new com_ciot_realm_db_ad_BeginBeanRealmProxy());
                realmObjectContext.clear();
                return e;
            } else if (cls.equals(CycleBean.class)) {
                E e2 = (RealmModel) cls.cast(new com_ciot_realm_db_ad_CycleBeanRealmProxy());
                realmObjectContext.clear();
                return e2;
            } else if (cls.equals(EndBean.class)) {
                E e3 = (RealmModel) cls.cast(new com_ciot_realm_db_ad_EndBeanRealmProxy());
                realmObjectContext.clear();
                return e3;
            } else if (cls.equals(GetAdListsBeanR.class)) {
                E e4 = (RealmModel) cls.cast(new com_ciot_realm_db_ad_GetAdListsBeanRRealmProxy());
                realmObjectContext.clear();
                return e4;
            } else if (cls.equals(HorseRaceLampsBean.class)) {
                E e5 = (RealmModel) cls.cast(new com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxy());
                realmObjectContext.clear();
                return e5;
            } else if (cls.equals(ResourcesBean.class)) {
                E e6 = (RealmModel) cls.cast(new com_ciot_realm_db_ad_ResourcesBeanRealmProxy());
                realmObjectContext.clear();
                return e6;
            } else if (cls.equals(TimesBean.class)) {
                E e7 = (RealmModel) cls.cast(new com_ciot_realm_db_ad_TimesBeanRealmProxy());
                realmObjectContext.clear();
                return e7;
            } else if (cls.equals(Attendance.class)) {
                E e8 = (RealmModel) cls.cast(new com_ciot_realm_db_AttendanceRealmProxy());
                realmObjectContext.clear();
                return e8;
            } else if (cls.equals(ChildTask.class)) {
                E e9 = (RealmModel) cls.cast(new com_ciot_realm_db_ChildTaskRealmProxy());
                realmObjectContext.clear();
                return e9;
            } else if (cls.equals(AckBean.class)) {
                E e10 = (RealmModel) cls.cast(new com_ciot_realm_db_common_AckBeanRealmProxy());
                realmObjectContext.clear();
                return e10;
            } else if (cls.equals(CompanyResponse.class)) {
                E e11 = (RealmModel) cls.cast(new com_ciot_realm_db_common_CompanyResponseRealmProxy());
                realmObjectContext.clear();
                return e11;
            } else if (cls.equals(Contact.class)) {
                E e12 = (RealmModel) cls.cast(new com_ciot_realm_db_common_ContactRealmProxy());
                realmObjectContext.clear();
                return e12;
            } else if (cls.equals(LocationBean.class)) {
                E e13 = (RealmModel) cls.cast(new com_ciot_realm_db_common_LocationBeanRealmProxy());
                realmObjectContext.clear();
                return e13;
            } else if (cls.equals(RecordResponse.class)) {
                E e14 = (RealmModel) cls.cast(new com_ciot_realm_db_common_RecordResponseRealmProxy());
                realmObjectContext.clear();
                return e14;
            } else if (cls.equals(Settings.class)) {
                E e15 = (RealmModel) cls.cast(new com_ciot_realm_db_common_SettingsRealmProxy());
                realmObjectContext.clear();
                return e15;
            } else if (cls.equals(ValidateBean.class)) {
                E e16 = (RealmModel) cls.cast(new com_ciot_realm_db_common_ValidateBeanRealmProxy());
                realmObjectContext.clear();
                return e16;
            } else if (cls.equals(VisitorBean.class)) {
                E e17 = (RealmModel) cls.cast(new com_ciot_realm_db_common_VisitorBeanRealmProxy());
                realmObjectContext.clear();
                return e17;
            } else if (cls.equals(EmployeeArcCode.class)) {
                E e18 = (RealmModel) cls.cast(new com_ciot_realm_db_employee_EmployeeArcCodeRealmProxy());
                realmObjectContext.clear();
                return e18;
            } else if (cls.equals(EmployeeLockMode.class)) {
                E e19 = (RealmModel) cls.cast(new com_ciot_realm_db_employee_EmployeeLockModeRealmProxy());
                realmObjectContext.clear();
                return e19;
            } else if (cls.equals(EmployeeResponse.class)) {
                E e20 = (RealmModel) cls.cast(new com_ciot_realm_db_employee_EmployeeResponseRealmProxy());
                realmObjectContext.clear();
                return e20;
            } else if (cls.equals(EmployeeBean.class)) {
                E e21 = (RealmModel) cls.cast(new com_ciot_realm_db_EmployeeBeanRealmProxy());
                realmObjectContext.clear();
                return e21;
            } else if (cls.equals(EventTrackingBean.class)) {
                E e22 = (RealmModel) cls.cast(new com_ciot_realm_db_EventTrackingBeanRealmProxy());
                realmObjectContext.clear();
                return e22;
            } else if (cls.equals(HotelActivitesBean.class)) {
                E e23 = (RealmModel) cls.cast(new com_ciot_realm_db_HotelActivitesBeanRealmProxy());
                realmObjectContext.clear();
                return e23;
            } else if (cls.equals(LockMode.class)) {
                E e24 = (RealmModel) cls.cast(new com_ciot_realm_db_LockModeRealmProxy());
                realmObjectContext.clear();
                return e24;
            } else if (cls.equals(MarkerPoint.class)) {
                E e25 = (RealmModel) cls.cast(new com_ciot_realm_db_MarkerPointRealmProxy());
                realmObjectContext.clear();
                return e25;
            } else if (cls.equals(MusicBean.class)) {
                E e26 = (RealmModel) cls.cast(new com_ciot_realm_db_MusicBeanRealmProxy());
                realmObjectContext.clear();
                return e26;
            } else if (cls.equals(Action.class)) {
                E e27 = (RealmModel) cls.cast(new com_ciot_realm_db_patrol_ActionRealmProxy());
                realmObjectContext.clear();
                return e27;
            } else if (cls.equals(FloorOriginBean.class)) {
                E e28 = (RealmModel) cls.cast(new com_ciot_realm_db_patrol_FloorOriginBeanRealmProxy());
                realmObjectContext.clear();
                return e28;
            } else if (cls.equals(Line.class)) {
                E e29 = (RealmModel) cls.cast(new com_ciot_realm_db_patrol_LineRealmProxy());
                realmObjectContext.clear();
                return e29;
            } else if (cls.equals(MediaBean.class)) {
                E e30 = (RealmModel) cls.cast(new com_ciot_realm_db_patrol_MediaBeanRealmProxy());
                realmObjectContext.clear();
                return e30;
            } else if (cls.equals(Message.class)) {
                E e31 = (RealmModel) cls.cast(new com_ciot_realm_db_patrol_MessageRealmProxy());
                realmObjectContext.clear();
                return e31;
            } else if (cls.equals(Operation.class)) {
                E e32 = (RealmModel) cls.cast(new com_ciot_realm_db_patrol_OperationRealmProxy());
                realmObjectContext.clear();
                return e32;
            } else if (cls.equals(PathBean.class)) {
                E e33 = (RealmModel) cls.cast(new com_ciot_realm_db_patrol_PathBeanRealmProxy());
                realmObjectContext.clear();
                return e33;
            } else if (cls.equals(PatrolTaskBean.class)) {
                E e34 = (RealmModel) cls.cast(new com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxy());
                realmObjectContext.clear();
                return e34;
            } else if (cls.equals(Place.class)) {
                E e35 = (RealmModel) cls.cast(new com_ciot_realm_db_patrol_PlaceRealmProxy());
                realmObjectContext.clear();
                return e35;
            } else if (cls.equals(Plan.class)) {
                E e36 = (RealmModel) cls.cast(new com_ciot_realm_db_patrol_PlanRealmProxy());
                realmObjectContext.clear();
                return e36;
            } else if (cls.equals(Point.class)) {
                E e37 = (RealmModel) cls.cast(new com_ciot_realm_db_patrol_PointRealmProxy());
                realmObjectContext.clear();
                return e37;
            } else if (cls.equals(Process.class)) {
                E e38 = (RealmModel) cls.cast(new com_ciot_realm_db_patrol_ProcessRealmProxy());
                realmObjectContext.clear();
                return e38;
            } else if (cls.equals(Resource.class)) {
                E e39 = (RealmModel) cls.cast(new com_ciot_realm_db_patrol_ResourceRealmProxy());
                realmObjectContext.clear();
                return e39;
            } else if (cls.equals(TransitionBean.class)) {
                E e40 = (RealmModel) cls.cast(new com_ciot_realm_db_patrol_TransitionBeanRealmProxy());
                realmObjectContext.clear();
                return e40;
            } else if (cls.equals(TurnstileBean.class)) {
                E e41 = (RealmModel) cls.cast(new com_ciot_realm_db_patrol_TurnstileBeanRealmProxy());
                realmObjectContext.clear();
                return e41;
            } else if (cls.equals(WaitBean.class)) {
                E e42 = (RealmModel) cls.cast(new com_ciot_realm_db_patrol_WaitBeanRealmProxy());
                realmObjectContext.clear();
                return e42;
            } else if (cls.equals(WaterPathBean.class)) {
                E e43 = (RealmModel) cls.cast(new com_ciot_realm_db_patrol_WaterPathBeanRealmProxy());
                realmObjectContext.clear();
                return e43;
            } else if (cls.equals(Person.class)) {
                E e44 = (RealmModel) cls.cast(new com_ciot_realm_db_PersonRealmProxy());
                realmObjectContext.clear();
                return e44;
            } else if (cls.equals(PersonInfoBean.class)) {
                E e45 = (RealmModel) cls.cast(new com_ciot_realm_db_PersonInfoBeanRealmProxy());
                realmObjectContext.clear();
                return e45;
            } else if (cls.equals(PointF.class)) {
                E e46 = (RealmModel) cls.cast(new com_ciot_realm_db_PointFRealmProxy());
                realmObjectContext.clear();
                return e46;
            } else if (cls.equals(DataBean.class)) {
                E e47 = (RealmModel) cls.cast(new com_ciot_realm_db_recommantation_DataBeanRealmProxy());
                realmObjectContext.clear();
                return e47;
            } else if (cls.equals(Record.class)) {
                E e48 = (RealmModel) cls.cast(new com_ciot_realm_db_RecordRealmProxy());
                realmObjectContext.clear();
                return e48;
            } else if (cls.equals(RecordLockMode.class)) {
                E e49 = (RealmModel) cls.cast(new com_ciot_realm_db_RecordLockModeRealmProxy());
                realmObjectContext.clear();
                return e49;
            } else if (cls.equals(RegisteredRecordInfo.class)) {
                E e50 = (RealmModel) cls.cast(new com_ciot_realm_db_RegisteredRecordInfoRealmProxy());
                realmObjectContext.clear();
                return e50;
            } else if (cls.equals(RegisterWithAppointmentResponse.class)) {
                E e51 = (RealmModel) cls.cast(new com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxy());
                realmObjectContext.clear();
                return e51;
            } else if (cls.equals(RobotBean.class)) {
                E e52 = (RealmModel) cls.cast(new com_ciot_realm_db_RobotBeanRealmProxy());
                realmObjectContext.clear();
                return e52;
            } else if (cls.equals(ActionBean.class)) {
                E e53 = (RealmModel) cls.cast(new com_ciot_realm_db_semantic_ActionBeanRealmProxy());
                realmObjectContext.clear();
                return e53;
            } else if (cls.equals(AnswerBean.class)) {
                E e54 = (RealmModel) cls.cast(new com_ciot_realm_db_semantic_AnswerBeanRealmProxy());
                realmObjectContext.clear();
                return e54;
            } else if (cls.equals(CiotResponseBean.class)) {
                E e55 = (RealmModel) cls.cast(new com_ciot_realm_db_semantic_CiotResponseBeanRealmProxy());
                realmObjectContext.clear();
                return e55;
            } else if (cls.equals(SemanticBean.class)) {
                E e56 = (RealmModel) cls.cast(new com_ciot_realm_db_semantic_SemanticBeanRealmProxy());
                realmObjectContext.clear();
                return e56;
            } else if (cls.equals(AdStat.class)) {
                E e57 = (RealmModel) cls.cast(new com_ciot_realm_db_stat_AdStatRealmProxy());
                realmObjectContext.clear();
                return e57;
            } else if (cls.equals(AdWatchStat.class)) {
                E e58 = (RealmModel) cls.cast(new com_ciot_realm_db_stat_AdWatchStatRealmProxy());
                realmObjectContext.clear();
                return e58;
            } else if (cls.equals(RobotPersonStat.class)) {
                E e59 = (RealmModel) cls.cast(new com_ciot_realm_db_stat_RobotPersonStatRealmProxy());
                realmObjectContext.clear();
                return e59;
            } else if (cls.equals(StatsRecord.class)) {
                E e60 = (RealmModel) cls.cast(new com_ciot_realm_db_stat_StatsRecordRealmProxy());
                realmObjectContext.clear();
                return e60;
            } else if (cls.equals(Tactics.class)) {
                E e61 = (RealmModel) cls.cast(new com_ciot_realm_db_TacticsRealmProxy());
                realmObjectContext.clear();
                return e61;
            } else if (cls.equals(Task.class)) {
                E e62 = (RealmModel) cls.cast(new com_ciot_realm_db_TaskRealmProxy());
                realmObjectContext.clear();
                return e62;
            } else if (cls.equals(TemRecord.class)) {
                E e63 = (RealmModel) cls.cast(new com_ciot_realm_db_TemRecordRealmProxy());
                realmObjectContext.clear();
                return e63;
            } else if (cls.equals(TemUploadRecord.class)) {
                E e64 = (RealmModel) cls.cast(new com_ciot_realm_db_TemUploadRecordRealmProxy());
                realmObjectContext.clear();
                return e64;
            } else if (cls.equals(ThermometryRecordBean.class)) {
                E e65 = (RealmModel) cls.cast(new com_ciot_realm_db_ThermometryRecordBeanRealmProxy());
                realmObjectContext.clear();
                return e65;
            } else if (cls.equals(TimeBean.class)) {
                E e66 = (RealmModel) cls.cast(new com_ciot_realm_db_TimeBeanRealmProxy());
                realmObjectContext.clear();
                return e66;
            } else if (cls.equals(TimerReceptionTaskBean.class)) {
                E e67 = (RealmModel) cls.cast(new com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxy());
                realmObjectContext.clear();
                return e67;
            } else if (cls.equals(ArcCode.class)) {
                E e68 = (RealmModel) cls.cast(new com_ciot_realm_db_visitor_ArcCodeRealmProxy());
                realmObjectContext.clear();
                return e68;
            } else if (cls.equals(MediaResult.class)) {
                E e69 = (RealmModel) cls.cast(new com_ciot_realm_db_visitor_MediaResultRealmProxy());
                realmObjectContext.clear();
                return e69;
            } else if (cls.equals(VisitorResponse.class)) {
                E e70 = (RealmModel) cls.cast(new com_ciot_realm_db_visitor_VisitorResponseRealmProxy());
                realmObjectContext.clear();
                return e70;
            } else {
                throw getMissingProxyClassException((Class<? extends RealmModel>) cls);
            }
        } finally {
            realmObjectContext.clear();
        }
    }

    public Set<Class<? extends RealmModel>> getModelClasses() {
        return MODEL_CLASSES;
    }

    public <E extends RealmModel> E copyOrUpdate(Realm realm, E e, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        Class superclass = e instanceof RealmObjectProxy ? e.getClass().getSuperclass() : e.getClass();
        if (superclass.equals(AdvertisementsBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_ad_AdvertisementsBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_ad_AdvertisementsBeanRealmProxy.AdvertisementsBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) AdvertisementsBean.class), (AdvertisementsBean) e, z, map, set));
        } else if (superclass.equals(BeginBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_ad_BeginBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_ad_BeginBeanRealmProxy.BeginBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) BeginBean.class), (BeginBean) e, z, map, set));
        } else if (superclass.equals(CycleBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_ad_CycleBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_ad_CycleBeanRealmProxy.CycleBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) CycleBean.class), (CycleBean) e, z, map, set));
        } else if (superclass.equals(EndBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_ad_EndBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_ad_EndBeanRealmProxy.EndBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) EndBean.class), (EndBean) e, z, map, set));
        } else if (superclass.equals(GetAdListsBeanR.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_ad_GetAdListsBeanRRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_ad_GetAdListsBeanRRealmProxy.GetAdListsBeanRColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) GetAdListsBeanR.class), (GetAdListsBeanR) e, z, map, set));
        } else if (superclass.equals(HorseRaceLampsBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxy.HorseRaceLampsBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) HorseRaceLampsBean.class), (HorseRaceLampsBean) e, z, map, set));
        } else if (superclass.equals(ResourcesBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_ad_ResourcesBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_ad_ResourcesBeanRealmProxy.ResourcesBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) ResourcesBean.class), (ResourcesBean) e, z, map, set));
        } else if (superclass.equals(TimesBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_ad_TimesBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_ad_TimesBeanRealmProxy.TimesBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) TimesBean.class), (TimesBean) e, z, map, set));
        } else if (superclass.equals(Attendance.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_AttendanceRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_AttendanceRealmProxy.AttendanceColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Attendance.class), (Attendance) e, z, map, set));
        } else if (superclass.equals(ChildTask.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_ChildTaskRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_ChildTaskRealmProxy.ChildTaskColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) ChildTask.class), (ChildTask) e, z, map, set));
        } else if (superclass.equals(AckBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_common_AckBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_common_AckBeanRealmProxy.AckBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) AckBean.class), (AckBean) e, z, map, set));
        } else if (superclass.equals(CompanyResponse.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_common_CompanyResponseRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_common_CompanyResponseRealmProxy.CompanyResponseColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) CompanyResponse.class), (CompanyResponse) e, z, map, set));
        } else if (superclass.equals(Contact.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_common_ContactRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_common_ContactRealmProxy.ContactColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Contact.class), (Contact) e, z, map, set));
        } else if (superclass.equals(LocationBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_common_LocationBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_common_LocationBeanRealmProxy.LocationBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) LocationBean.class), (LocationBean) e, z, map, set));
        } else if (superclass.equals(RecordResponse.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_common_RecordResponseRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_common_RecordResponseRealmProxy.RecordResponseColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) RecordResponse.class), (RecordResponse) e, z, map, set));
        } else if (superclass.equals(Settings.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_common_SettingsRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_common_SettingsRealmProxy.SettingsColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Settings.class), (Settings) e, z, map, set));
        } else if (superclass.equals(ValidateBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_common_ValidateBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_common_ValidateBeanRealmProxy.ValidateBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) ValidateBean.class), (ValidateBean) e, z, map, set));
        } else if (superclass.equals(VisitorBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_common_VisitorBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_common_VisitorBeanRealmProxy.VisitorBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) VisitorBean.class), (VisitorBean) e, z, map, set));
        } else if (superclass.equals(EmployeeArcCode.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_employee_EmployeeArcCodeRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_employee_EmployeeArcCodeRealmProxy.EmployeeArcCodeColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) EmployeeArcCode.class), (EmployeeArcCode) e, z, map, set));
        } else if (superclass.equals(EmployeeLockMode.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_employee_EmployeeLockModeRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_employee_EmployeeLockModeRealmProxy.EmployeeLockModeColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) EmployeeLockMode.class), (EmployeeLockMode) e, z, map, set));
        } else if (superclass.equals(EmployeeResponse.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_employee_EmployeeResponseRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_employee_EmployeeResponseRealmProxy.EmployeeResponseColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) EmployeeResponse.class), (EmployeeResponse) e, z, map, set));
        } else if (superclass.equals(EmployeeBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_EmployeeBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_EmployeeBeanRealmProxy.EmployeeBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) EmployeeBean.class), (EmployeeBean) e, z, map, set));
        } else if (superclass.equals(EventTrackingBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_EventTrackingBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_EventTrackingBeanRealmProxy.EventTrackingBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) EventTrackingBean.class), (EventTrackingBean) e, z, map, set));
        } else if (superclass.equals(HotelActivitesBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_HotelActivitesBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_HotelActivitesBeanRealmProxy.HotelActivitesBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) HotelActivitesBean.class), (HotelActivitesBean) e, z, map, set));
        } else if (superclass.equals(LockMode.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_LockModeRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_LockModeRealmProxy.LockModeColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) LockMode.class), (LockMode) e, z, map, set));
        } else if (superclass.equals(MarkerPoint.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_MarkerPointRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_MarkerPointRealmProxy.MarkerPointColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) MarkerPoint.class), (MarkerPoint) e, z, map, set));
        } else if (superclass.equals(MusicBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_MusicBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_MusicBeanRealmProxy.MusicBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) MusicBean.class), (MusicBean) e, z, map, set));
        } else if (superclass.equals(Action.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_patrol_ActionRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_ActionRealmProxy.ActionColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Action.class), (Action) e, z, map, set));
        } else if (superclass.equals(FloorOriginBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_patrol_FloorOriginBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_FloorOriginBeanRealmProxy.FloorOriginBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) FloorOriginBean.class), (FloorOriginBean) e, z, map, set));
        } else if (superclass.equals(Line.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_patrol_LineRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_LineRealmProxy.LineColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Line.class), (Line) e, z, map, set));
        } else if (superclass.equals(MediaBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_patrol_MediaBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_MediaBeanRealmProxy.MediaBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) MediaBean.class), (MediaBean) e, z, map, set));
        } else if (superclass.equals(Message.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_patrol_MessageRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_MessageRealmProxy.MessageColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Message.class), (Message) e, z, map, set));
        } else if (superclass.equals(Operation.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_patrol_OperationRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_OperationRealmProxy.OperationColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Operation.class), (Operation) e, z, map, set));
        } else if (superclass.equals(PathBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_patrol_PathBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_PathBeanRealmProxy.PathBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) PathBean.class), (PathBean) e, z, map, set));
        } else if (superclass.equals(PatrolTaskBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxy.PatrolTaskBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) PatrolTaskBean.class), (PatrolTaskBean) e, z, map, set));
        } else if (superclass.equals(Place.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_patrol_PlaceRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_PlaceRealmProxy.PlaceColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Place.class), (Place) e, z, map, set));
        } else if (superclass.equals(Plan.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_patrol_PlanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_PlanRealmProxy.PlanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Plan.class), (Plan) e, z, map, set));
        } else if (superclass.equals(Point.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_patrol_PointRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_PointRealmProxy.PointColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Point.class), (Point) e, z, map, set));
        } else if (superclass.equals(Process.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_patrol_ProcessRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_ProcessRealmProxy.ProcessColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Process.class), (Process) e, z, map, set));
        } else if (superclass.equals(Resource.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_patrol_ResourceRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_ResourceRealmProxy.ResourceColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Resource.class), (Resource) e, z, map, set));
        } else if (superclass.equals(TransitionBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_patrol_TransitionBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_TransitionBeanRealmProxy.TransitionBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) TransitionBean.class), (TransitionBean) e, z, map, set));
        } else if (superclass.equals(TurnstileBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_patrol_TurnstileBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_TurnstileBeanRealmProxy.TurnstileBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) TurnstileBean.class), (TurnstileBean) e, z, map, set));
        } else if (superclass.equals(WaitBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_patrol_WaitBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_WaitBeanRealmProxy.WaitBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) WaitBean.class), (WaitBean) e, z, map, set));
        } else if (superclass.equals(WaterPathBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_patrol_WaterPathBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_WaterPathBeanRealmProxy.WaterPathBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) WaterPathBean.class), (WaterPathBean) e, z, map, set));
        } else if (superclass.equals(Person.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_PersonRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_PersonRealmProxy.PersonColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Person.class), (Person) e, z, map, set));
        } else if (superclass.equals(PersonInfoBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_PersonInfoBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_PersonInfoBeanRealmProxy.PersonInfoBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) PersonInfoBean.class), (PersonInfoBean) e, z, map, set));
        } else if (superclass.equals(PointF.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_PointFRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_PointFRealmProxy.PointFColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) PointF.class), (PointF) e, z, map, set));
        } else if (superclass.equals(DataBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_recommantation_DataBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_recommantation_DataBeanRealmProxy.DataBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) DataBean.class), (DataBean) e, z, map, set));
        } else if (superclass.equals(Record.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_RecordRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_RecordRealmProxy.RecordColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Record.class), (Record) e, z, map, set));
        } else if (superclass.equals(RecordLockMode.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_RecordLockModeRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_RecordLockModeRealmProxy.RecordLockModeColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) RecordLockMode.class), (RecordLockMode) e, z, map, set));
        } else if (superclass.equals(RegisteredRecordInfo.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_RegisteredRecordInfoRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_RegisteredRecordInfoRealmProxy.RegisteredRecordInfoColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) RegisteredRecordInfo.class), (RegisteredRecordInfo) e, z, map, set));
        } else if (superclass.equals(RegisterWithAppointmentResponse.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxy.RegisterWithAppointmentResponseColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) RegisterWithAppointmentResponse.class), (RegisterWithAppointmentResponse) e, z, map, set));
        } else if (superclass.equals(RobotBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_RobotBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_RobotBeanRealmProxy.RobotBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) RobotBean.class), (RobotBean) e, z, map, set));
        } else if (superclass.equals(ActionBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_semantic_ActionBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_semantic_ActionBeanRealmProxy.ActionBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) ActionBean.class), (ActionBean) e, z, map, set));
        } else if (superclass.equals(AnswerBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_semantic_AnswerBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_semantic_AnswerBeanRealmProxy.AnswerBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) AnswerBean.class), (AnswerBean) e, z, map, set));
        } else if (superclass.equals(CiotResponseBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_semantic_CiotResponseBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_semantic_CiotResponseBeanRealmProxy.CiotResponseBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) CiotResponseBean.class), (CiotResponseBean) e, z, map, set));
        } else if (superclass.equals(SemanticBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_semantic_SemanticBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_semantic_SemanticBeanRealmProxy.SemanticBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) SemanticBean.class), (SemanticBean) e, z, map, set));
        } else if (superclass.equals(AdStat.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_stat_AdStatRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_stat_AdStatRealmProxy.AdStatColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) AdStat.class), (AdStat) e, z, map, set));
        } else if (superclass.equals(AdWatchStat.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_stat_AdWatchStatRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_stat_AdWatchStatRealmProxy.AdWatchStatColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) AdWatchStat.class), (AdWatchStat) e, z, map, set));
        } else if (superclass.equals(RobotPersonStat.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_stat_RobotPersonStatRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_stat_RobotPersonStatRealmProxy.RobotPersonStatColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) RobotPersonStat.class), (RobotPersonStat) e, z, map, set));
        } else if (superclass.equals(StatsRecord.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_stat_StatsRecordRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_stat_StatsRecordRealmProxy.StatsRecordColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) StatsRecord.class), (StatsRecord) e, z, map, set));
        } else if (superclass.equals(Tactics.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_TacticsRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_TacticsRealmProxy.TacticsColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Tactics.class), (Tactics) e, z, map, set));
        } else if (superclass.equals(Task.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_TaskRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_TaskRealmProxy.TaskColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Task.class), (Task) e, z, map, set));
        } else if (superclass.equals(TemRecord.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_TemRecordRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_TemRecordRealmProxy.TemRecordColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) TemRecord.class), (TemRecord) e, z, map, set));
        } else if (superclass.equals(TemUploadRecord.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_TemUploadRecordRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_TemUploadRecordRealmProxy.TemUploadRecordColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) TemUploadRecord.class), (TemUploadRecord) e, z, map, set));
        } else if (superclass.equals(ThermometryRecordBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_ThermometryRecordBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_ThermometryRecordBeanRealmProxy.ThermometryRecordBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) ThermometryRecordBean.class), (ThermometryRecordBean) e, z, map, set));
        } else if (superclass.equals(TimeBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_TimeBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_TimeBeanRealmProxy.TimeBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) TimeBean.class), (TimeBean) e, z, map, set));
        } else if (superclass.equals(TimerReceptionTaskBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxy.TimerReceptionTaskBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) TimerReceptionTaskBean.class), (TimerReceptionTaskBean) e, z, map, set));
        } else if (superclass.equals(ArcCode.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_visitor_ArcCodeRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_visitor_ArcCodeRealmProxy.ArcCodeColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) ArcCode.class), (ArcCode) e, z, map, set));
        } else if (superclass.equals(MediaResult.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_visitor_MediaResultRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_visitor_MediaResultRealmProxy.MediaResultColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) MediaResult.class), (MediaResult) e, z, map, set));
        } else if (superclass.equals(VisitorResponse.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_visitor_VisitorResponseRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_visitor_VisitorResponseRealmProxy.VisitorResponseColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) VisitorResponse.class), (VisitorResponse) e, z, map, set));
        } else {
            throw getMissingProxyClassException((Class<? extends RealmModel>) superclass);
        }
    }

    public void insert(Realm realm, RealmModel realmModel, Map<RealmModel, Long> map) {
        Class<?> superclass = realmModel instanceof RealmObjectProxy ? realmModel.getClass().getSuperclass() : realmModel.getClass();
        if (superclass.equals(AdvertisementsBean.class)) {
            com_ciot_realm_db_ad_AdvertisementsBeanRealmProxy.insert(realm, (AdvertisementsBean) realmModel, map);
        } else if (superclass.equals(BeginBean.class)) {
            com_ciot_realm_db_ad_BeginBeanRealmProxy.insert(realm, (BeginBean) realmModel, map);
        } else if (superclass.equals(CycleBean.class)) {
            com_ciot_realm_db_ad_CycleBeanRealmProxy.insert(realm, (CycleBean) realmModel, map);
        } else if (superclass.equals(EndBean.class)) {
            com_ciot_realm_db_ad_EndBeanRealmProxy.insert(realm, (EndBean) realmModel, map);
        } else if (superclass.equals(GetAdListsBeanR.class)) {
            com_ciot_realm_db_ad_GetAdListsBeanRRealmProxy.insert(realm, (GetAdListsBeanR) realmModel, map);
        } else if (superclass.equals(HorseRaceLampsBean.class)) {
            com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxy.insert(realm, (HorseRaceLampsBean) realmModel, map);
        } else if (superclass.equals(ResourcesBean.class)) {
            com_ciot_realm_db_ad_ResourcesBeanRealmProxy.insert(realm, (ResourcesBean) realmModel, map);
        } else if (superclass.equals(TimesBean.class)) {
            com_ciot_realm_db_ad_TimesBeanRealmProxy.insert(realm, (TimesBean) realmModel, map);
        } else if (superclass.equals(Attendance.class)) {
            com_ciot_realm_db_AttendanceRealmProxy.insert(realm, (Attendance) realmModel, map);
        } else if (superclass.equals(ChildTask.class)) {
            com_ciot_realm_db_ChildTaskRealmProxy.insert(realm, (ChildTask) realmModel, map);
        } else if (superclass.equals(AckBean.class)) {
            com_ciot_realm_db_common_AckBeanRealmProxy.insert(realm, (AckBean) realmModel, map);
        } else if (superclass.equals(CompanyResponse.class)) {
            com_ciot_realm_db_common_CompanyResponseRealmProxy.insert(realm, (CompanyResponse) realmModel, map);
        } else if (superclass.equals(Contact.class)) {
            com_ciot_realm_db_common_ContactRealmProxy.insert(realm, (Contact) realmModel, map);
        } else if (superclass.equals(LocationBean.class)) {
            com_ciot_realm_db_common_LocationBeanRealmProxy.insert(realm, (LocationBean) realmModel, map);
        } else if (superclass.equals(RecordResponse.class)) {
            com_ciot_realm_db_common_RecordResponseRealmProxy.insert(realm, (RecordResponse) realmModel, map);
        } else if (superclass.equals(Settings.class)) {
            com_ciot_realm_db_common_SettingsRealmProxy.insert(realm, (Settings) realmModel, map);
        } else if (superclass.equals(ValidateBean.class)) {
            com_ciot_realm_db_common_ValidateBeanRealmProxy.insert(realm, (ValidateBean) realmModel, map);
        } else if (superclass.equals(VisitorBean.class)) {
            com_ciot_realm_db_common_VisitorBeanRealmProxy.insert(realm, (VisitorBean) realmModel, map);
        } else if (superclass.equals(EmployeeArcCode.class)) {
            com_ciot_realm_db_employee_EmployeeArcCodeRealmProxy.insert(realm, (EmployeeArcCode) realmModel, map);
        } else if (superclass.equals(EmployeeLockMode.class)) {
            com_ciot_realm_db_employee_EmployeeLockModeRealmProxy.insert(realm, (EmployeeLockMode) realmModel, map);
        } else if (superclass.equals(EmployeeResponse.class)) {
            com_ciot_realm_db_employee_EmployeeResponseRealmProxy.insert(realm, (EmployeeResponse) realmModel, map);
        } else if (superclass.equals(EmployeeBean.class)) {
            com_ciot_realm_db_EmployeeBeanRealmProxy.insert(realm, (EmployeeBean) realmModel, map);
        } else if (superclass.equals(EventTrackingBean.class)) {
            com_ciot_realm_db_EventTrackingBeanRealmProxy.insert(realm, (EventTrackingBean) realmModel, map);
        } else if (superclass.equals(HotelActivitesBean.class)) {
            com_ciot_realm_db_HotelActivitesBeanRealmProxy.insert(realm, (HotelActivitesBean) realmModel, map);
        } else if (superclass.equals(LockMode.class)) {
            com_ciot_realm_db_LockModeRealmProxy.insert(realm, (LockMode) realmModel, map);
        } else if (superclass.equals(MarkerPoint.class)) {
            com_ciot_realm_db_MarkerPointRealmProxy.insert(realm, (MarkerPoint) realmModel, map);
        } else if (superclass.equals(MusicBean.class)) {
            com_ciot_realm_db_MusicBeanRealmProxy.insert(realm, (MusicBean) realmModel, map);
        } else if (superclass.equals(Action.class)) {
            com_ciot_realm_db_patrol_ActionRealmProxy.insert(realm, (Action) realmModel, map);
        } else if (superclass.equals(FloorOriginBean.class)) {
            com_ciot_realm_db_patrol_FloorOriginBeanRealmProxy.insert(realm, (FloorOriginBean) realmModel, map);
        } else if (superclass.equals(Line.class)) {
            com_ciot_realm_db_patrol_LineRealmProxy.insert(realm, (Line) realmModel, map);
        } else if (superclass.equals(MediaBean.class)) {
            com_ciot_realm_db_patrol_MediaBeanRealmProxy.insert(realm, (MediaBean) realmModel, map);
        } else if (superclass.equals(Message.class)) {
            com_ciot_realm_db_patrol_MessageRealmProxy.insert(realm, (Message) realmModel, map);
        } else if (superclass.equals(Operation.class)) {
            com_ciot_realm_db_patrol_OperationRealmProxy.insert(realm, (Operation) realmModel, map);
        } else if (superclass.equals(PathBean.class)) {
            com_ciot_realm_db_patrol_PathBeanRealmProxy.insert(realm, (PathBean) realmModel, map);
        } else if (superclass.equals(PatrolTaskBean.class)) {
            com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxy.insert(realm, (PatrolTaskBean) realmModel, map);
        } else if (superclass.equals(Place.class)) {
            com_ciot_realm_db_patrol_PlaceRealmProxy.insert(realm, (Place) realmModel, map);
        } else if (superclass.equals(Plan.class)) {
            com_ciot_realm_db_patrol_PlanRealmProxy.insert(realm, (Plan) realmModel, map);
        } else if (superclass.equals(Point.class)) {
            com_ciot_realm_db_patrol_PointRealmProxy.insert(realm, (Point) realmModel, map);
        } else if (superclass.equals(Process.class)) {
            com_ciot_realm_db_patrol_ProcessRealmProxy.insert(realm, (Process) realmModel, map);
        } else if (superclass.equals(Resource.class)) {
            com_ciot_realm_db_patrol_ResourceRealmProxy.insert(realm, (Resource) realmModel, map);
        } else if (superclass.equals(TransitionBean.class)) {
            com_ciot_realm_db_patrol_TransitionBeanRealmProxy.insert(realm, (TransitionBean) realmModel, map);
        } else if (superclass.equals(TurnstileBean.class)) {
            com_ciot_realm_db_patrol_TurnstileBeanRealmProxy.insert(realm, (TurnstileBean) realmModel, map);
        } else if (superclass.equals(WaitBean.class)) {
            com_ciot_realm_db_patrol_WaitBeanRealmProxy.insert(realm, (WaitBean) realmModel, map);
        } else if (superclass.equals(WaterPathBean.class)) {
            com_ciot_realm_db_patrol_WaterPathBeanRealmProxy.insert(realm, (WaterPathBean) realmModel, map);
        } else if (superclass.equals(Person.class)) {
            com_ciot_realm_db_PersonRealmProxy.insert(realm, (Person) realmModel, map);
        } else if (superclass.equals(PersonInfoBean.class)) {
            com_ciot_realm_db_PersonInfoBeanRealmProxy.insert(realm, (PersonInfoBean) realmModel, map);
        } else if (superclass.equals(PointF.class)) {
            com_ciot_realm_db_PointFRealmProxy.insert(realm, (PointF) realmModel, map);
        } else if (superclass.equals(DataBean.class)) {
            com_ciot_realm_db_recommantation_DataBeanRealmProxy.insert(realm, (DataBean) realmModel, map);
        } else if (superclass.equals(Record.class)) {
            com_ciot_realm_db_RecordRealmProxy.insert(realm, (Record) realmModel, map);
        } else if (superclass.equals(RecordLockMode.class)) {
            com_ciot_realm_db_RecordLockModeRealmProxy.insert(realm, (RecordLockMode) realmModel, map);
        } else if (superclass.equals(RegisteredRecordInfo.class)) {
            com_ciot_realm_db_RegisteredRecordInfoRealmProxy.insert(realm, (RegisteredRecordInfo) realmModel, map);
        } else if (superclass.equals(RegisterWithAppointmentResponse.class)) {
            com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxy.insert(realm, (RegisterWithAppointmentResponse) realmModel, map);
        } else if (superclass.equals(RobotBean.class)) {
            com_ciot_realm_db_RobotBeanRealmProxy.insert(realm, (RobotBean) realmModel, map);
        } else if (superclass.equals(ActionBean.class)) {
            com_ciot_realm_db_semantic_ActionBeanRealmProxy.insert(realm, (ActionBean) realmModel, map);
        } else if (superclass.equals(AnswerBean.class)) {
            com_ciot_realm_db_semantic_AnswerBeanRealmProxy.insert(realm, (AnswerBean) realmModel, map);
        } else if (superclass.equals(CiotResponseBean.class)) {
            com_ciot_realm_db_semantic_CiotResponseBeanRealmProxy.insert(realm, (CiotResponseBean) realmModel, map);
        } else if (superclass.equals(SemanticBean.class)) {
            com_ciot_realm_db_semantic_SemanticBeanRealmProxy.insert(realm, (SemanticBean) realmModel, map);
        } else if (superclass.equals(AdStat.class)) {
            com_ciot_realm_db_stat_AdStatRealmProxy.insert(realm, (AdStat) realmModel, map);
        } else if (superclass.equals(AdWatchStat.class)) {
            com_ciot_realm_db_stat_AdWatchStatRealmProxy.insert(realm, (AdWatchStat) realmModel, map);
        } else if (superclass.equals(RobotPersonStat.class)) {
            com_ciot_realm_db_stat_RobotPersonStatRealmProxy.insert(realm, (RobotPersonStat) realmModel, map);
        } else if (superclass.equals(StatsRecord.class)) {
            com_ciot_realm_db_stat_StatsRecordRealmProxy.insert(realm, (StatsRecord) realmModel, map);
        } else if (superclass.equals(Tactics.class)) {
            com_ciot_realm_db_TacticsRealmProxy.insert(realm, (Tactics) realmModel, map);
        } else if (superclass.equals(Task.class)) {
            com_ciot_realm_db_TaskRealmProxy.insert(realm, (Task) realmModel, map);
        } else if (superclass.equals(TemRecord.class)) {
            com_ciot_realm_db_TemRecordRealmProxy.insert(realm, (TemRecord) realmModel, map);
        } else if (superclass.equals(TemUploadRecord.class)) {
            com_ciot_realm_db_TemUploadRecordRealmProxy.insert(realm, (TemUploadRecord) realmModel, map);
        } else if (superclass.equals(ThermometryRecordBean.class)) {
            com_ciot_realm_db_ThermometryRecordBeanRealmProxy.insert(realm, (ThermometryRecordBean) realmModel, map);
        } else if (superclass.equals(TimeBean.class)) {
            com_ciot_realm_db_TimeBeanRealmProxy.insert(realm, (TimeBean) realmModel, map);
        } else if (superclass.equals(TimerReceptionTaskBean.class)) {
            com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxy.insert(realm, (TimerReceptionTaskBean) realmModel, map);
        } else if (superclass.equals(ArcCode.class)) {
            com_ciot_realm_db_visitor_ArcCodeRealmProxy.insert(realm, (ArcCode) realmModel, map);
        } else if (superclass.equals(MediaResult.class)) {
            com_ciot_realm_db_visitor_MediaResultRealmProxy.insert(realm, (MediaResult) realmModel, map);
        } else if (superclass.equals(VisitorResponse.class)) {
            com_ciot_realm_db_visitor_VisitorResponseRealmProxy.insert(realm, (VisitorResponse) realmModel, map);
        } else {
            throw getMissingProxyClassException((Class<? extends RealmModel>) superclass);
        }
    }

    public void insert(Realm realm, Collection<? extends RealmModel> collection) {
        Iterator<? extends RealmModel> it = collection.iterator();
        HashMap hashMap = new HashMap(collection.size());
        if (it.hasNext()) {
            RealmModel realmModel = (RealmModel) it.next();
            Class<?> superclass = realmModel instanceof RealmObjectProxy ? realmModel.getClass().getSuperclass() : realmModel.getClass();
            if (superclass.equals(AdvertisementsBean.class)) {
                com_ciot_realm_db_ad_AdvertisementsBeanRealmProxy.insert(realm, (AdvertisementsBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(BeginBean.class)) {
                com_ciot_realm_db_ad_BeginBeanRealmProxy.insert(realm, (BeginBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(CycleBean.class)) {
                com_ciot_realm_db_ad_CycleBeanRealmProxy.insert(realm, (CycleBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(EndBean.class)) {
                com_ciot_realm_db_ad_EndBeanRealmProxy.insert(realm, (EndBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(GetAdListsBeanR.class)) {
                com_ciot_realm_db_ad_GetAdListsBeanRRealmProxy.insert(realm, (GetAdListsBeanR) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(HorseRaceLampsBean.class)) {
                com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxy.insert(realm, (HorseRaceLampsBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(ResourcesBean.class)) {
                com_ciot_realm_db_ad_ResourcesBeanRealmProxy.insert(realm, (ResourcesBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(TimesBean.class)) {
                com_ciot_realm_db_ad_TimesBeanRealmProxy.insert(realm, (TimesBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Attendance.class)) {
                com_ciot_realm_db_AttendanceRealmProxy.insert(realm, (Attendance) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(ChildTask.class)) {
                com_ciot_realm_db_ChildTaskRealmProxy.insert(realm, (ChildTask) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(AckBean.class)) {
                com_ciot_realm_db_common_AckBeanRealmProxy.insert(realm, (AckBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(CompanyResponse.class)) {
                com_ciot_realm_db_common_CompanyResponseRealmProxy.insert(realm, (CompanyResponse) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Contact.class)) {
                com_ciot_realm_db_common_ContactRealmProxy.insert(realm, (Contact) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(LocationBean.class)) {
                com_ciot_realm_db_common_LocationBeanRealmProxy.insert(realm, (LocationBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(RecordResponse.class)) {
                com_ciot_realm_db_common_RecordResponseRealmProxy.insert(realm, (RecordResponse) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Settings.class)) {
                com_ciot_realm_db_common_SettingsRealmProxy.insert(realm, (Settings) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(ValidateBean.class)) {
                com_ciot_realm_db_common_ValidateBeanRealmProxy.insert(realm, (ValidateBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(VisitorBean.class)) {
                com_ciot_realm_db_common_VisitorBeanRealmProxy.insert(realm, (VisitorBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(EmployeeArcCode.class)) {
                com_ciot_realm_db_employee_EmployeeArcCodeRealmProxy.insert(realm, (EmployeeArcCode) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(EmployeeLockMode.class)) {
                com_ciot_realm_db_employee_EmployeeLockModeRealmProxy.insert(realm, (EmployeeLockMode) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(EmployeeResponse.class)) {
                com_ciot_realm_db_employee_EmployeeResponseRealmProxy.insert(realm, (EmployeeResponse) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(EmployeeBean.class)) {
                com_ciot_realm_db_EmployeeBeanRealmProxy.insert(realm, (EmployeeBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(EventTrackingBean.class)) {
                com_ciot_realm_db_EventTrackingBeanRealmProxy.insert(realm, (EventTrackingBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(HotelActivitesBean.class)) {
                com_ciot_realm_db_HotelActivitesBeanRealmProxy.insert(realm, (HotelActivitesBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(LockMode.class)) {
                com_ciot_realm_db_LockModeRealmProxy.insert(realm, (LockMode) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(MarkerPoint.class)) {
                com_ciot_realm_db_MarkerPointRealmProxy.insert(realm, (MarkerPoint) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(MusicBean.class)) {
                com_ciot_realm_db_MusicBeanRealmProxy.insert(realm, (MusicBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Action.class)) {
                com_ciot_realm_db_patrol_ActionRealmProxy.insert(realm, (Action) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(FloorOriginBean.class)) {
                com_ciot_realm_db_patrol_FloorOriginBeanRealmProxy.insert(realm, (FloorOriginBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Line.class)) {
                com_ciot_realm_db_patrol_LineRealmProxy.insert(realm, (Line) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(MediaBean.class)) {
                com_ciot_realm_db_patrol_MediaBeanRealmProxy.insert(realm, (MediaBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Message.class)) {
                com_ciot_realm_db_patrol_MessageRealmProxy.insert(realm, (Message) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Operation.class)) {
                com_ciot_realm_db_patrol_OperationRealmProxy.insert(realm, (Operation) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(PathBean.class)) {
                com_ciot_realm_db_patrol_PathBeanRealmProxy.insert(realm, (PathBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(PatrolTaskBean.class)) {
                com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxy.insert(realm, (PatrolTaskBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Place.class)) {
                com_ciot_realm_db_patrol_PlaceRealmProxy.insert(realm, (Place) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Plan.class)) {
                com_ciot_realm_db_patrol_PlanRealmProxy.insert(realm, (Plan) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Point.class)) {
                com_ciot_realm_db_patrol_PointRealmProxy.insert(realm, (Point) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Process.class)) {
                com_ciot_realm_db_patrol_ProcessRealmProxy.insert(realm, (Process) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Resource.class)) {
                com_ciot_realm_db_patrol_ResourceRealmProxy.insert(realm, (Resource) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(TransitionBean.class)) {
                com_ciot_realm_db_patrol_TransitionBeanRealmProxy.insert(realm, (TransitionBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(TurnstileBean.class)) {
                com_ciot_realm_db_patrol_TurnstileBeanRealmProxy.insert(realm, (TurnstileBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(WaitBean.class)) {
                com_ciot_realm_db_patrol_WaitBeanRealmProxy.insert(realm, (WaitBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(WaterPathBean.class)) {
                com_ciot_realm_db_patrol_WaterPathBeanRealmProxy.insert(realm, (WaterPathBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Person.class)) {
                com_ciot_realm_db_PersonRealmProxy.insert(realm, (Person) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(PersonInfoBean.class)) {
                com_ciot_realm_db_PersonInfoBeanRealmProxy.insert(realm, (PersonInfoBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(PointF.class)) {
                com_ciot_realm_db_PointFRealmProxy.insert(realm, (PointF) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(DataBean.class)) {
                com_ciot_realm_db_recommantation_DataBeanRealmProxy.insert(realm, (DataBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Record.class)) {
                com_ciot_realm_db_RecordRealmProxy.insert(realm, (Record) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(RecordLockMode.class)) {
                com_ciot_realm_db_RecordLockModeRealmProxy.insert(realm, (RecordLockMode) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(RegisteredRecordInfo.class)) {
                com_ciot_realm_db_RegisteredRecordInfoRealmProxy.insert(realm, (RegisteredRecordInfo) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(RegisterWithAppointmentResponse.class)) {
                com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxy.insert(realm, (RegisterWithAppointmentResponse) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(RobotBean.class)) {
                com_ciot_realm_db_RobotBeanRealmProxy.insert(realm, (RobotBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(ActionBean.class)) {
                com_ciot_realm_db_semantic_ActionBeanRealmProxy.insert(realm, (ActionBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(AnswerBean.class)) {
                com_ciot_realm_db_semantic_AnswerBeanRealmProxy.insert(realm, (AnswerBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(CiotResponseBean.class)) {
                com_ciot_realm_db_semantic_CiotResponseBeanRealmProxy.insert(realm, (CiotResponseBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(SemanticBean.class)) {
                com_ciot_realm_db_semantic_SemanticBeanRealmProxy.insert(realm, (SemanticBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(AdStat.class)) {
                com_ciot_realm_db_stat_AdStatRealmProxy.insert(realm, (AdStat) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(AdWatchStat.class)) {
                com_ciot_realm_db_stat_AdWatchStatRealmProxy.insert(realm, (AdWatchStat) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(RobotPersonStat.class)) {
                com_ciot_realm_db_stat_RobotPersonStatRealmProxy.insert(realm, (RobotPersonStat) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(StatsRecord.class)) {
                com_ciot_realm_db_stat_StatsRecordRealmProxy.insert(realm, (StatsRecord) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Tactics.class)) {
                com_ciot_realm_db_TacticsRealmProxy.insert(realm, (Tactics) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Task.class)) {
                com_ciot_realm_db_TaskRealmProxy.insert(realm, (Task) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(TemRecord.class)) {
                com_ciot_realm_db_TemRecordRealmProxy.insert(realm, (TemRecord) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(TemUploadRecord.class)) {
                com_ciot_realm_db_TemUploadRecordRealmProxy.insert(realm, (TemUploadRecord) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(ThermometryRecordBean.class)) {
                com_ciot_realm_db_ThermometryRecordBeanRealmProxy.insert(realm, (ThermometryRecordBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(TimeBean.class)) {
                com_ciot_realm_db_TimeBeanRealmProxy.insert(realm, (TimeBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(TimerReceptionTaskBean.class)) {
                com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxy.insert(realm, (TimerReceptionTaskBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(ArcCode.class)) {
                com_ciot_realm_db_visitor_ArcCodeRealmProxy.insert(realm, (ArcCode) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(MediaResult.class)) {
                com_ciot_realm_db_visitor_MediaResultRealmProxy.insert(realm, (MediaResult) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(VisitorResponse.class)) {
                com_ciot_realm_db_visitor_VisitorResponseRealmProxy.insert(realm, (VisitorResponse) realmModel, (Map<RealmModel, Long>) hashMap);
            } else {
                throw getMissingProxyClassException((Class<? extends RealmModel>) superclass);
            }
            if (!it.hasNext()) {
                return;
            }
            if (superclass.equals(AdvertisementsBean.class)) {
                com_ciot_realm_db_ad_AdvertisementsBeanRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(BeginBean.class)) {
                com_ciot_realm_db_ad_BeginBeanRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(CycleBean.class)) {
                com_ciot_realm_db_ad_CycleBeanRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(EndBean.class)) {
                com_ciot_realm_db_ad_EndBeanRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(GetAdListsBeanR.class)) {
                com_ciot_realm_db_ad_GetAdListsBeanRRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(HorseRaceLampsBean.class)) {
                com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(ResourcesBean.class)) {
                com_ciot_realm_db_ad_ResourcesBeanRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(TimesBean.class)) {
                com_ciot_realm_db_ad_TimesBeanRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Attendance.class)) {
                com_ciot_realm_db_AttendanceRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(ChildTask.class)) {
                com_ciot_realm_db_ChildTaskRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(AckBean.class)) {
                com_ciot_realm_db_common_AckBeanRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(CompanyResponse.class)) {
                com_ciot_realm_db_common_CompanyResponseRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Contact.class)) {
                com_ciot_realm_db_common_ContactRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(LocationBean.class)) {
                com_ciot_realm_db_common_LocationBeanRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(RecordResponse.class)) {
                com_ciot_realm_db_common_RecordResponseRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Settings.class)) {
                com_ciot_realm_db_common_SettingsRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(ValidateBean.class)) {
                com_ciot_realm_db_common_ValidateBeanRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(VisitorBean.class)) {
                com_ciot_realm_db_common_VisitorBeanRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(EmployeeArcCode.class)) {
                com_ciot_realm_db_employee_EmployeeArcCodeRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(EmployeeLockMode.class)) {
                com_ciot_realm_db_employee_EmployeeLockModeRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(EmployeeResponse.class)) {
                com_ciot_realm_db_employee_EmployeeResponseRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(EmployeeBean.class)) {
                com_ciot_realm_db_EmployeeBeanRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(EventTrackingBean.class)) {
                com_ciot_realm_db_EventTrackingBeanRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(HotelActivitesBean.class)) {
                com_ciot_realm_db_HotelActivitesBeanRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(LockMode.class)) {
                com_ciot_realm_db_LockModeRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(MarkerPoint.class)) {
                com_ciot_realm_db_MarkerPointRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(MusicBean.class)) {
                com_ciot_realm_db_MusicBeanRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Action.class)) {
                com_ciot_realm_db_patrol_ActionRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(FloorOriginBean.class)) {
                com_ciot_realm_db_patrol_FloorOriginBeanRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Line.class)) {
                com_ciot_realm_db_patrol_LineRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(MediaBean.class)) {
                com_ciot_realm_db_patrol_MediaBeanRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Message.class)) {
                com_ciot_realm_db_patrol_MessageRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Operation.class)) {
                com_ciot_realm_db_patrol_OperationRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(PathBean.class)) {
                com_ciot_realm_db_patrol_PathBeanRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(PatrolTaskBean.class)) {
                com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Place.class)) {
                com_ciot_realm_db_patrol_PlaceRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Plan.class)) {
                com_ciot_realm_db_patrol_PlanRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Point.class)) {
                com_ciot_realm_db_patrol_PointRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Process.class)) {
                com_ciot_realm_db_patrol_ProcessRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Resource.class)) {
                com_ciot_realm_db_patrol_ResourceRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(TransitionBean.class)) {
                com_ciot_realm_db_patrol_TransitionBeanRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(TurnstileBean.class)) {
                com_ciot_realm_db_patrol_TurnstileBeanRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(WaitBean.class)) {
                com_ciot_realm_db_patrol_WaitBeanRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(WaterPathBean.class)) {
                com_ciot_realm_db_patrol_WaterPathBeanRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Person.class)) {
                com_ciot_realm_db_PersonRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(PersonInfoBean.class)) {
                com_ciot_realm_db_PersonInfoBeanRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(PointF.class)) {
                com_ciot_realm_db_PointFRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(DataBean.class)) {
                com_ciot_realm_db_recommantation_DataBeanRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Record.class)) {
                com_ciot_realm_db_RecordRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(RecordLockMode.class)) {
                com_ciot_realm_db_RecordLockModeRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(RegisteredRecordInfo.class)) {
                com_ciot_realm_db_RegisteredRecordInfoRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(RegisterWithAppointmentResponse.class)) {
                com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(RobotBean.class)) {
                com_ciot_realm_db_RobotBeanRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(ActionBean.class)) {
                com_ciot_realm_db_semantic_ActionBeanRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(AnswerBean.class)) {
                com_ciot_realm_db_semantic_AnswerBeanRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(CiotResponseBean.class)) {
                com_ciot_realm_db_semantic_CiotResponseBeanRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(SemanticBean.class)) {
                com_ciot_realm_db_semantic_SemanticBeanRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(AdStat.class)) {
                com_ciot_realm_db_stat_AdStatRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(AdWatchStat.class)) {
                com_ciot_realm_db_stat_AdWatchStatRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(RobotPersonStat.class)) {
                com_ciot_realm_db_stat_RobotPersonStatRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(StatsRecord.class)) {
                com_ciot_realm_db_stat_StatsRecordRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Tactics.class)) {
                com_ciot_realm_db_TacticsRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Task.class)) {
                com_ciot_realm_db_TaskRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(TemRecord.class)) {
                com_ciot_realm_db_TemRecordRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(TemUploadRecord.class)) {
                com_ciot_realm_db_TemUploadRecordRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(ThermometryRecordBean.class)) {
                com_ciot_realm_db_ThermometryRecordBeanRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(TimeBean.class)) {
                com_ciot_realm_db_TimeBeanRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(TimerReceptionTaskBean.class)) {
                com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(ArcCode.class)) {
                com_ciot_realm_db_visitor_ArcCodeRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(MediaResult.class)) {
                com_ciot_realm_db_visitor_MediaResultRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(VisitorResponse.class)) {
                com_ciot_realm_db_visitor_VisitorResponseRealmProxy.insert(realm, it, (Map<RealmModel, Long>) hashMap);
            } else {
                throw getMissingProxyClassException((Class<? extends RealmModel>) superclass);
            }
        }
    }

    public void insertOrUpdate(Realm realm, RealmModel realmModel, Map<RealmModel, Long> map) {
        Class<?> superclass = realmModel instanceof RealmObjectProxy ? realmModel.getClass().getSuperclass() : realmModel.getClass();
        if (superclass.equals(AdvertisementsBean.class)) {
            com_ciot_realm_db_ad_AdvertisementsBeanRealmProxy.insertOrUpdate(realm, (AdvertisementsBean) realmModel, map);
        } else if (superclass.equals(BeginBean.class)) {
            com_ciot_realm_db_ad_BeginBeanRealmProxy.insertOrUpdate(realm, (BeginBean) realmModel, map);
        } else if (superclass.equals(CycleBean.class)) {
            com_ciot_realm_db_ad_CycleBeanRealmProxy.insertOrUpdate(realm, (CycleBean) realmModel, map);
        } else if (superclass.equals(EndBean.class)) {
            com_ciot_realm_db_ad_EndBeanRealmProxy.insertOrUpdate(realm, (EndBean) realmModel, map);
        } else if (superclass.equals(GetAdListsBeanR.class)) {
            com_ciot_realm_db_ad_GetAdListsBeanRRealmProxy.insertOrUpdate(realm, (GetAdListsBeanR) realmModel, map);
        } else if (superclass.equals(HorseRaceLampsBean.class)) {
            com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxy.insertOrUpdate(realm, (HorseRaceLampsBean) realmModel, map);
        } else if (superclass.equals(ResourcesBean.class)) {
            com_ciot_realm_db_ad_ResourcesBeanRealmProxy.insertOrUpdate(realm, (ResourcesBean) realmModel, map);
        } else if (superclass.equals(TimesBean.class)) {
            com_ciot_realm_db_ad_TimesBeanRealmProxy.insertOrUpdate(realm, (TimesBean) realmModel, map);
        } else if (superclass.equals(Attendance.class)) {
            com_ciot_realm_db_AttendanceRealmProxy.insertOrUpdate(realm, (Attendance) realmModel, map);
        } else if (superclass.equals(ChildTask.class)) {
            com_ciot_realm_db_ChildTaskRealmProxy.insertOrUpdate(realm, (ChildTask) realmModel, map);
        } else if (superclass.equals(AckBean.class)) {
            com_ciot_realm_db_common_AckBeanRealmProxy.insertOrUpdate(realm, (AckBean) realmModel, map);
        } else if (superclass.equals(CompanyResponse.class)) {
            com_ciot_realm_db_common_CompanyResponseRealmProxy.insertOrUpdate(realm, (CompanyResponse) realmModel, map);
        } else if (superclass.equals(Contact.class)) {
            com_ciot_realm_db_common_ContactRealmProxy.insertOrUpdate(realm, (Contact) realmModel, map);
        } else if (superclass.equals(LocationBean.class)) {
            com_ciot_realm_db_common_LocationBeanRealmProxy.insertOrUpdate(realm, (LocationBean) realmModel, map);
        } else if (superclass.equals(RecordResponse.class)) {
            com_ciot_realm_db_common_RecordResponseRealmProxy.insertOrUpdate(realm, (RecordResponse) realmModel, map);
        } else if (superclass.equals(Settings.class)) {
            com_ciot_realm_db_common_SettingsRealmProxy.insertOrUpdate(realm, (Settings) realmModel, map);
        } else if (superclass.equals(ValidateBean.class)) {
            com_ciot_realm_db_common_ValidateBeanRealmProxy.insertOrUpdate(realm, (ValidateBean) realmModel, map);
        } else if (superclass.equals(VisitorBean.class)) {
            com_ciot_realm_db_common_VisitorBeanRealmProxy.insertOrUpdate(realm, (VisitorBean) realmModel, map);
        } else if (superclass.equals(EmployeeArcCode.class)) {
            com_ciot_realm_db_employee_EmployeeArcCodeRealmProxy.insertOrUpdate(realm, (EmployeeArcCode) realmModel, map);
        } else if (superclass.equals(EmployeeLockMode.class)) {
            com_ciot_realm_db_employee_EmployeeLockModeRealmProxy.insertOrUpdate(realm, (EmployeeLockMode) realmModel, map);
        } else if (superclass.equals(EmployeeResponse.class)) {
            com_ciot_realm_db_employee_EmployeeResponseRealmProxy.insertOrUpdate(realm, (EmployeeResponse) realmModel, map);
        } else if (superclass.equals(EmployeeBean.class)) {
            com_ciot_realm_db_EmployeeBeanRealmProxy.insertOrUpdate(realm, (EmployeeBean) realmModel, map);
        } else if (superclass.equals(EventTrackingBean.class)) {
            com_ciot_realm_db_EventTrackingBeanRealmProxy.insertOrUpdate(realm, (EventTrackingBean) realmModel, map);
        } else if (superclass.equals(HotelActivitesBean.class)) {
            com_ciot_realm_db_HotelActivitesBeanRealmProxy.insertOrUpdate(realm, (HotelActivitesBean) realmModel, map);
        } else if (superclass.equals(LockMode.class)) {
            com_ciot_realm_db_LockModeRealmProxy.insertOrUpdate(realm, (LockMode) realmModel, map);
        } else if (superclass.equals(MarkerPoint.class)) {
            com_ciot_realm_db_MarkerPointRealmProxy.insertOrUpdate(realm, (MarkerPoint) realmModel, map);
        } else if (superclass.equals(MusicBean.class)) {
            com_ciot_realm_db_MusicBeanRealmProxy.insertOrUpdate(realm, (MusicBean) realmModel, map);
        } else if (superclass.equals(Action.class)) {
            com_ciot_realm_db_patrol_ActionRealmProxy.insertOrUpdate(realm, (Action) realmModel, map);
        } else if (superclass.equals(FloorOriginBean.class)) {
            com_ciot_realm_db_patrol_FloorOriginBeanRealmProxy.insertOrUpdate(realm, (FloorOriginBean) realmModel, map);
        } else if (superclass.equals(Line.class)) {
            com_ciot_realm_db_patrol_LineRealmProxy.insertOrUpdate(realm, (Line) realmModel, map);
        } else if (superclass.equals(MediaBean.class)) {
            com_ciot_realm_db_patrol_MediaBeanRealmProxy.insertOrUpdate(realm, (MediaBean) realmModel, map);
        } else if (superclass.equals(Message.class)) {
            com_ciot_realm_db_patrol_MessageRealmProxy.insertOrUpdate(realm, (Message) realmModel, map);
        } else if (superclass.equals(Operation.class)) {
            com_ciot_realm_db_patrol_OperationRealmProxy.insertOrUpdate(realm, (Operation) realmModel, map);
        } else if (superclass.equals(PathBean.class)) {
            com_ciot_realm_db_patrol_PathBeanRealmProxy.insertOrUpdate(realm, (PathBean) realmModel, map);
        } else if (superclass.equals(PatrolTaskBean.class)) {
            com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxy.insertOrUpdate(realm, (PatrolTaskBean) realmModel, map);
        } else if (superclass.equals(Place.class)) {
            com_ciot_realm_db_patrol_PlaceRealmProxy.insertOrUpdate(realm, (Place) realmModel, map);
        } else if (superclass.equals(Plan.class)) {
            com_ciot_realm_db_patrol_PlanRealmProxy.insertOrUpdate(realm, (Plan) realmModel, map);
        } else if (superclass.equals(Point.class)) {
            com_ciot_realm_db_patrol_PointRealmProxy.insertOrUpdate(realm, (Point) realmModel, map);
        } else if (superclass.equals(Process.class)) {
            com_ciot_realm_db_patrol_ProcessRealmProxy.insertOrUpdate(realm, (Process) realmModel, map);
        } else if (superclass.equals(Resource.class)) {
            com_ciot_realm_db_patrol_ResourceRealmProxy.insertOrUpdate(realm, (Resource) realmModel, map);
        } else if (superclass.equals(TransitionBean.class)) {
            com_ciot_realm_db_patrol_TransitionBeanRealmProxy.insertOrUpdate(realm, (TransitionBean) realmModel, map);
        } else if (superclass.equals(TurnstileBean.class)) {
            com_ciot_realm_db_patrol_TurnstileBeanRealmProxy.insertOrUpdate(realm, (TurnstileBean) realmModel, map);
        } else if (superclass.equals(WaitBean.class)) {
            com_ciot_realm_db_patrol_WaitBeanRealmProxy.insertOrUpdate(realm, (WaitBean) realmModel, map);
        } else if (superclass.equals(WaterPathBean.class)) {
            com_ciot_realm_db_patrol_WaterPathBeanRealmProxy.insertOrUpdate(realm, (WaterPathBean) realmModel, map);
        } else if (superclass.equals(Person.class)) {
            com_ciot_realm_db_PersonRealmProxy.insertOrUpdate(realm, (Person) realmModel, map);
        } else if (superclass.equals(PersonInfoBean.class)) {
            com_ciot_realm_db_PersonInfoBeanRealmProxy.insertOrUpdate(realm, (PersonInfoBean) realmModel, map);
        } else if (superclass.equals(PointF.class)) {
            com_ciot_realm_db_PointFRealmProxy.insertOrUpdate(realm, (PointF) realmModel, map);
        } else if (superclass.equals(DataBean.class)) {
            com_ciot_realm_db_recommantation_DataBeanRealmProxy.insertOrUpdate(realm, (DataBean) realmModel, map);
        } else if (superclass.equals(Record.class)) {
            com_ciot_realm_db_RecordRealmProxy.insertOrUpdate(realm, (Record) realmModel, map);
        } else if (superclass.equals(RecordLockMode.class)) {
            com_ciot_realm_db_RecordLockModeRealmProxy.insertOrUpdate(realm, (RecordLockMode) realmModel, map);
        } else if (superclass.equals(RegisteredRecordInfo.class)) {
            com_ciot_realm_db_RegisteredRecordInfoRealmProxy.insertOrUpdate(realm, (RegisteredRecordInfo) realmModel, map);
        } else if (superclass.equals(RegisterWithAppointmentResponse.class)) {
            com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxy.insertOrUpdate(realm, (RegisterWithAppointmentResponse) realmModel, map);
        } else if (superclass.equals(RobotBean.class)) {
            com_ciot_realm_db_RobotBeanRealmProxy.insertOrUpdate(realm, (RobotBean) realmModel, map);
        } else if (superclass.equals(ActionBean.class)) {
            com_ciot_realm_db_semantic_ActionBeanRealmProxy.insertOrUpdate(realm, (ActionBean) realmModel, map);
        } else if (superclass.equals(AnswerBean.class)) {
            com_ciot_realm_db_semantic_AnswerBeanRealmProxy.insertOrUpdate(realm, (AnswerBean) realmModel, map);
        } else if (superclass.equals(CiotResponseBean.class)) {
            com_ciot_realm_db_semantic_CiotResponseBeanRealmProxy.insertOrUpdate(realm, (CiotResponseBean) realmModel, map);
        } else if (superclass.equals(SemanticBean.class)) {
            com_ciot_realm_db_semantic_SemanticBeanRealmProxy.insertOrUpdate(realm, (SemanticBean) realmModel, map);
        } else if (superclass.equals(AdStat.class)) {
            com_ciot_realm_db_stat_AdStatRealmProxy.insertOrUpdate(realm, (AdStat) realmModel, map);
        } else if (superclass.equals(AdWatchStat.class)) {
            com_ciot_realm_db_stat_AdWatchStatRealmProxy.insertOrUpdate(realm, (AdWatchStat) realmModel, map);
        } else if (superclass.equals(RobotPersonStat.class)) {
            com_ciot_realm_db_stat_RobotPersonStatRealmProxy.insertOrUpdate(realm, (RobotPersonStat) realmModel, map);
        } else if (superclass.equals(StatsRecord.class)) {
            com_ciot_realm_db_stat_StatsRecordRealmProxy.insertOrUpdate(realm, (StatsRecord) realmModel, map);
        } else if (superclass.equals(Tactics.class)) {
            com_ciot_realm_db_TacticsRealmProxy.insertOrUpdate(realm, (Tactics) realmModel, map);
        } else if (superclass.equals(Task.class)) {
            com_ciot_realm_db_TaskRealmProxy.insertOrUpdate(realm, (Task) realmModel, map);
        } else if (superclass.equals(TemRecord.class)) {
            com_ciot_realm_db_TemRecordRealmProxy.insertOrUpdate(realm, (TemRecord) realmModel, map);
        } else if (superclass.equals(TemUploadRecord.class)) {
            com_ciot_realm_db_TemUploadRecordRealmProxy.insertOrUpdate(realm, (TemUploadRecord) realmModel, map);
        } else if (superclass.equals(ThermometryRecordBean.class)) {
            com_ciot_realm_db_ThermometryRecordBeanRealmProxy.insertOrUpdate(realm, (ThermometryRecordBean) realmModel, map);
        } else if (superclass.equals(TimeBean.class)) {
            com_ciot_realm_db_TimeBeanRealmProxy.insertOrUpdate(realm, (TimeBean) realmModel, map);
        } else if (superclass.equals(TimerReceptionTaskBean.class)) {
            com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxy.insertOrUpdate(realm, (TimerReceptionTaskBean) realmModel, map);
        } else if (superclass.equals(ArcCode.class)) {
            com_ciot_realm_db_visitor_ArcCodeRealmProxy.insertOrUpdate(realm, (ArcCode) realmModel, map);
        } else if (superclass.equals(MediaResult.class)) {
            com_ciot_realm_db_visitor_MediaResultRealmProxy.insertOrUpdate(realm, (MediaResult) realmModel, map);
        } else if (superclass.equals(VisitorResponse.class)) {
            com_ciot_realm_db_visitor_VisitorResponseRealmProxy.insertOrUpdate(realm, (VisitorResponse) realmModel, map);
        } else {
            throw getMissingProxyClassException((Class<? extends RealmModel>) superclass);
        }
    }

    public void insertOrUpdate(Realm realm, Collection<? extends RealmModel> collection) {
        Iterator<? extends RealmModel> it = collection.iterator();
        HashMap hashMap = new HashMap(collection.size());
        if (it.hasNext()) {
            RealmModel realmModel = (RealmModel) it.next();
            Class<?> superclass = realmModel instanceof RealmObjectProxy ? realmModel.getClass().getSuperclass() : realmModel.getClass();
            if (superclass.equals(AdvertisementsBean.class)) {
                com_ciot_realm_db_ad_AdvertisementsBeanRealmProxy.insertOrUpdate(realm, (AdvertisementsBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(BeginBean.class)) {
                com_ciot_realm_db_ad_BeginBeanRealmProxy.insertOrUpdate(realm, (BeginBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(CycleBean.class)) {
                com_ciot_realm_db_ad_CycleBeanRealmProxy.insertOrUpdate(realm, (CycleBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(EndBean.class)) {
                com_ciot_realm_db_ad_EndBeanRealmProxy.insertOrUpdate(realm, (EndBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(GetAdListsBeanR.class)) {
                com_ciot_realm_db_ad_GetAdListsBeanRRealmProxy.insertOrUpdate(realm, (GetAdListsBeanR) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(HorseRaceLampsBean.class)) {
                com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxy.insertOrUpdate(realm, (HorseRaceLampsBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(ResourcesBean.class)) {
                com_ciot_realm_db_ad_ResourcesBeanRealmProxy.insertOrUpdate(realm, (ResourcesBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(TimesBean.class)) {
                com_ciot_realm_db_ad_TimesBeanRealmProxy.insertOrUpdate(realm, (TimesBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Attendance.class)) {
                com_ciot_realm_db_AttendanceRealmProxy.insertOrUpdate(realm, (Attendance) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(ChildTask.class)) {
                com_ciot_realm_db_ChildTaskRealmProxy.insertOrUpdate(realm, (ChildTask) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(AckBean.class)) {
                com_ciot_realm_db_common_AckBeanRealmProxy.insertOrUpdate(realm, (AckBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(CompanyResponse.class)) {
                com_ciot_realm_db_common_CompanyResponseRealmProxy.insertOrUpdate(realm, (CompanyResponse) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Contact.class)) {
                com_ciot_realm_db_common_ContactRealmProxy.insertOrUpdate(realm, (Contact) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(LocationBean.class)) {
                com_ciot_realm_db_common_LocationBeanRealmProxy.insertOrUpdate(realm, (LocationBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(RecordResponse.class)) {
                com_ciot_realm_db_common_RecordResponseRealmProxy.insertOrUpdate(realm, (RecordResponse) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Settings.class)) {
                com_ciot_realm_db_common_SettingsRealmProxy.insertOrUpdate(realm, (Settings) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(ValidateBean.class)) {
                com_ciot_realm_db_common_ValidateBeanRealmProxy.insertOrUpdate(realm, (ValidateBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(VisitorBean.class)) {
                com_ciot_realm_db_common_VisitorBeanRealmProxy.insertOrUpdate(realm, (VisitorBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(EmployeeArcCode.class)) {
                com_ciot_realm_db_employee_EmployeeArcCodeRealmProxy.insertOrUpdate(realm, (EmployeeArcCode) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(EmployeeLockMode.class)) {
                com_ciot_realm_db_employee_EmployeeLockModeRealmProxy.insertOrUpdate(realm, (EmployeeLockMode) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(EmployeeResponse.class)) {
                com_ciot_realm_db_employee_EmployeeResponseRealmProxy.insertOrUpdate(realm, (EmployeeResponse) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(EmployeeBean.class)) {
                com_ciot_realm_db_EmployeeBeanRealmProxy.insertOrUpdate(realm, (EmployeeBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(EventTrackingBean.class)) {
                com_ciot_realm_db_EventTrackingBeanRealmProxy.insertOrUpdate(realm, (EventTrackingBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(HotelActivitesBean.class)) {
                com_ciot_realm_db_HotelActivitesBeanRealmProxy.insertOrUpdate(realm, (HotelActivitesBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(LockMode.class)) {
                com_ciot_realm_db_LockModeRealmProxy.insertOrUpdate(realm, (LockMode) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(MarkerPoint.class)) {
                com_ciot_realm_db_MarkerPointRealmProxy.insertOrUpdate(realm, (MarkerPoint) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(MusicBean.class)) {
                com_ciot_realm_db_MusicBeanRealmProxy.insertOrUpdate(realm, (MusicBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Action.class)) {
                com_ciot_realm_db_patrol_ActionRealmProxy.insertOrUpdate(realm, (Action) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(FloorOriginBean.class)) {
                com_ciot_realm_db_patrol_FloorOriginBeanRealmProxy.insertOrUpdate(realm, (FloorOriginBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Line.class)) {
                com_ciot_realm_db_patrol_LineRealmProxy.insertOrUpdate(realm, (Line) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(MediaBean.class)) {
                com_ciot_realm_db_patrol_MediaBeanRealmProxy.insertOrUpdate(realm, (MediaBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Message.class)) {
                com_ciot_realm_db_patrol_MessageRealmProxy.insertOrUpdate(realm, (Message) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Operation.class)) {
                com_ciot_realm_db_patrol_OperationRealmProxy.insertOrUpdate(realm, (Operation) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(PathBean.class)) {
                com_ciot_realm_db_patrol_PathBeanRealmProxy.insertOrUpdate(realm, (PathBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(PatrolTaskBean.class)) {
                com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxy.insertOrUpdate(realm, (PatrolTaskBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Place.class)) {
                com_ciot_realm_db_patrol_PlaceRealmProxy.insertOrUpdate(realm, (Place) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Plan.class)) {
                com_ciot_realm_db_patrol_PlanRealmProxy.insertOrUpdate(realm, (Plan) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Point.class)) {
                com_ciot_realm_db_patrol_PointRealmProxy.insertOrUpdate(realm, (Point) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Process.class)) {
                com_ciot_realm_db_patrol_ProcessRealmProxy.insertOrUpdate(realm, (Process) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Resource.class)) {
                com_ciot_realm_db_patrol_ResourceRealmProxy.insertOrUpdate(realm, (Resource) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(TransitionBean.class)) {
                com_ciot_realm_db_patrol_TransitionBeanRealmProxy.insertOrUpdate(realm, (TransitionBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(TurnstileBean.class)) {
                com_ciot_realm_db_patrol_TurnstileBeanRealmProxy.insertOrUpdate(realm, (TurnstileBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(WaitBean.class)) {
                com_ciot_realm_db_patrol_WaitBeanRealmProxy.insertOrUpdate(realm, (WaitBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(WaterPathBean.class)) {
                com_ciot_realm_db_patrol_WaterPathBeanRealmProxy.insertOrUpdate(realm, (WaterPathBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Person.class)) {
                com_ciot_realm_db_PersonRealmProxy.insertOrUpdate(realm, (Person) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(PersonInfoBean.class)) {
                com_ciot_realm_db_PersonInfoBeanRealmProxy.insertOrUpdate(realm, (PersonInfoBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(PointF.class)) {
                com_ciot_realm_db_PointFRealmProxy.insertOrUpdate(realm, (PointF) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(DataBean.class)) {
                com_ciot_realm_db_recommantation_DataBeanRealmProxy.insertOrUpdate(realm, (DataBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Record.class)) {
                com_ciot_realm_db_RecordRealmProxy.insertOrUpdate(realm, (Record) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(RecordLockMode.class)) {
                com_ciot_realm_db_RecordLockModeRealmProxy.insertOrUpdate(realm, (RecordLockMode) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(RegisteredRecordInfo.class)) {
                com_ciot_realm_db_RegisteredRecordInfoRealmProxy.insertOrUpdate(realm, (RegisteredRecordInfo) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(RegisterWithAppointmentResponse.class)) {
                com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxy.insertOrUpdate(realm, (RegisterWithAppointmentResponse) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(RobotBean.class)) {
                com_ciot_realm_db_RobotBeanRealmProxy.insertOrUpdate(realm, (RobotBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(ActionBean.class)) {
                com_ciot_realm_db_semantic_ActionBeanRealmProxy.insertOrUpdate(realm, (ActionBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(AnswerBean.class)) {
                com_ciot_realm_db_semantic_AnswerBeanRealmProxy.insertOrUpdate(realm, (AnswerBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(CiotResponseBean.class)) {
                com_ciot_realm_db_semantic_CiotResponseBeanRealmProxy.insertOrUpdate(realm, (CiotResponseBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(SemanticBean.class)) {
                com_ciot_realm_db_semantic_SemanticBeanRealmProxy.insertOrUpdate(realm, (SemanticBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(AdStat.class)) {
                com_ciot_realm_db_stat_AdStatRealmProxy.insertOrUpdate(realm, (AdStat) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(AdWatchStat.class)) {
                com_ciot_realm_db_stat_AdWatchStatRealmProxy.insertOrUpdate(realm, (AdWatchStat) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(RobotPersonStat.class)) {
                com_ciot_realm_db_stat_RobotPersonStatRealmProxy.insertOrUpdate(realm, (RobotPersonStat) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(StatsRecord.class)) {
                com_ciot_realm_db_stat_StatsRecordRealmProxy.insertOrUpdate(realm, (StatsRecord) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Tactics.class)) {
                com_ciot_realm_db_TacticsRealmProxy.insertOrUpdate(realm, (Tactics) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Task.class)) {
                com_ciot_realm_db_TaskRealmProxy.insertOrUpdate(realm, (Task) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(TemRecord.class)) {
                com_ciot_realm_db_TemRecordRealmProxy.insertOrUpdate(realm, (TemRecord) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(TemUploadRecord.class)) {
                com_ciot_realm_db_TemUploadRecordRealmProxy.insertOrUpdate(realm, (TemUploadRecord) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(ThermometryRecordBean.class)) {
                com_ciot_realm_db_ThermometryRecordBeanRealmProxy.insertOrUpdate(realm, (ThermometryRecordBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(TimeBean.class)) {
                com_ciot_realm_db_TimeBeanRealmProxy.insertOrUpdate(realm, (TimeBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(TimerReceptionTaskBean.class)) {
                com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxy.insertOrUpdate(realm, (TimerReceptionTaskBean) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(ArcCode.class)) {
                com_ciot_realm_db_visitor_ArcCodeRealmProxy.insertOrUpdate(realm, (ArcCode) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(MediaResult.class)) {
                com_ciot_realm_db_visitor_MediaResultRealmProxy.insertOrUpdate(realm, (MediaResult) realmModel, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(VisitorResponse.class)) {
                com_ciot_realm_db_visitor_VisitorResponseRealmProxy.insertOrUpdate(realm, (VisitorResponse) realmModel, (Map<RealmModel, Long>) hashMap);
            } else {
                throw getMissingProxyClassException((Class<? extends RealmModel>) superclass);
            }
            if (!it.hasNext()) {
                return;
            }
            if (superclass.equals(AdvertisementsBean.class)) {
                com_ciot_realm_db_ad_AdvertisementsBeanRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(BeginBean.class)) {
                com_ciot_realm_db_ad_BeginBeanRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(CycleBean.class)) {
                com_ciot_realm_db_ad_CycleBeanRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(EndBean.class)) {
                com_ciot_realm_db_ad_EndBeanRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(GetAdListsBeanR.class)) {
                com_ciot_realm_db_ad_GetAdListsBeanRRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(HorseRaceLampsBean.class)) {
                com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(ResourcesBean.class)) {
                com_ciot_realm_db_ad_ResourcesBeanRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(TimesBean.class)) {
                com_ciot_realm_db_ad_TimesBeanRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Attendance.class)) {
                com_ciot_realm_db_AttendanceRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(ChildTask.class)) {
                com_ciot_realm_db_ChildTaskRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(AckBean.class)) {
                com_ciot_realm_db_common_AckBeanRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(CompanyResponse.class)) {
                com_ciot_realm_db_common_CompanyResponseRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Contact.class)) {
                com_ciot_realm_db_common_ContactRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(LocationBean.class)) {
                com_ciot_realm_db_common_LocationBeanRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(RecordResponse.class)) {
                com_ciot_realm_db_common_RecordResponseRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Settings.class)) {
                com_ciot_realm_db_common_SettingsRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(ValidateBean.class)) {
                com_ciot_realm_db_common_ValidateBeanRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(VisitorBean.class)) {
                com_ciot_realm_db_common_VisitorBeanRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(EmployeeArcCode.class)) {
                com_ciot_realm_db_employee_EmployeeArcCodeRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(EmployeeLockMode.class)) {
                com_ciot_realm_db_employee_EmployeeLockModeRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(EmployeeResponse.class)) {
                com_ciot_realm_db_employee_EmployeeResponseRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(EmployeeBean.class)) {
                com_ciot_realm_db_EmployeeBeanRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(EventTrackingBean.class)) {
                com_ciot_realm_db_EventTrackingBeanRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(HotelActivitesBean.class)) {
                com_ciot_realm_db_HotelActivitesBeanRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(LockMode.class)) {
                com_ciot_realm_db_LockModeRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(MarkerPoint.class)) {
                com_ciot_realm_db_MarkerPointRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(MusicBean.class)) {
                com_ciot_realm_db_MusicBeanRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Action.class)) {
                com_ciot_realm_db_patrol_ActionRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(FloorOriginBean.class)) {
                com_ciot_realm_db_patrol_FloorOriginBeanRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Line.class)) {
                com_ciot_realm_db_patrol_LineRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(MediaBean.class)) {
                com_ciot_realm_db_patrol_MediaBeanRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Message.class)) {
                com_ciot_realm_db_patrol_MessageRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Operation.class)) {
                com_ciot_realm_db_patrol_OperationRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(PathBean.class)) {
                com_ciot_realm_db_patrol_PathBeanRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(PatrolTaskBean.class)) {
                com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Place.class)) {
                com_ciot_realm_db_patrol_PlaceRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Plan.class)) {
                com_ciot_realm_db_patrol_PlanRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Point.class)) {
                com_ciot_realm_db_patrol_PointRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Process.class)) {
                com_ciot_realm_db_patrol_ProcessRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Resource.class)) {
                com_ciot_realm_db_patrol_ResourceRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(TransitionBean.class)) {
                com_ciot_realm_db_patrol_TransitionBeanRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(TurnstileBean.class)) {
                com_ciot_realm_db_patrol_TurnstileBeanRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(WaitBean.class)) {
                com_ciot_realm_db_patrol_WaitBeanRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(WaterPathBean.class)) {
                com_ciot_realm_db_patrol_WaterPathBeanRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Person.class)) {
                com_ciot_realm_db_PersonRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(PersonInfoBean.class)) {
                com_ciot_realm_db_PersonInfoBeanRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(PointF.class)) {
                com_ciot_realm_db_PointFRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(DataBean.class)) {
                com_ciot_realm_db_recommantation_DataBeanRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Record.class)) {
                com_ciot_realm_db_RecordRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(RecordLockMode.class)) {
                com_ciot_realm_db_RecordLockModeRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(RegisteredRecordInfo.class)) {
                com_ciot_realm_db_RegisteredRecordInfoRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(RegisterWithAppointmentResponse.class)) {
                com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(RobotBean.class)) {
                com_ciot_realm_db_RobotBeanRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(ActionBean.class)) {
                com_ciot_realm_db_semantic_ActionBeanRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(AnswerBean.class)) {
                com_ciot_realm_db_semantic_AnswerBeanRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(CiotResponseBean.class)) {
                com_ciot_realm_db_semantic_CiotResponseBeanRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(SemanticBean.class)) {
                com_ciot_realm_db_semantic_SemanticBeanRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(AdStat.class)) {
                com_ciot_realm_db_stat_AdStatRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(AdWatchStat.class)) {
                com_ciot_realm_db_stat_AdWatchStatRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(RobotPersonStat.class)) {
                com_ciot_realm_db_stat_RobotPersonStatRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(StatsRecord.class)) {
                com_ciot_realm_db_stat_StatsRecordRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Tactics.class)) {
                com_ciot_realm_db_TacticsRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(Task.class)) {
                com_ciot_realm_db_TaskRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(TemRecord.class)) {
                com_ciot_realm_db_TemRecordRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(TemUploadRecord.class)) {
                com_ciot_realm_db_TemUploadRecordRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(ThermometryRecordBean.class)) {
                com_ciot_realm_db_ThermometryRecordBeanRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(TimeBean.class)) {
                com_ciot_realm_db_TimeBeanRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(TimerReceptionTaskBean.class)) {
                com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(ArcCode.class)) {
                com_ciot_realm_db_visitor_ArcCodeRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(MediaResult.class)) {
                com_ciot_realm_db_visitor_MediaResultRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else if (superclass.equals(VisitorResponse.class)) {
                com_ciot_realm_db_visitor_VisitorResponseRealmProxy.insertOrUpdate(realm, it, (Map<RealmModel, Long>) hashMap);
            } else {
                throw getMissingProxyClassException((Class<? extends RealmModel>) superclass);
            }
        }
    }

    public <E extends RealmModel> E createOrUpdateUsingJsonObject(Class<E> cls, Realm realm, JSONObject jSONObject, boolean z) throws JSONException {
        checkClass(cls);
        if (cls.equals(AdvertisementsBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_ad_AdvertisementsBeanRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(BeginBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_ad_BeginBeanRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(CycleBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_ad_CycleBeanRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(EndBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_ad_EndBeanRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(GetAdListsBeanR.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_ad_GetAdListsBeanRRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(HorseRaceLampsBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(ResourcesBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_ad_ResourcesBeanRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(TimesBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_ad_TimesBeanRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(Attendance.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_AttendanceRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(ChildTask.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_ChildTaskRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(AckBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_common_AckBeanRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(CompanyResponse.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_common_CompanyResponseRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(Contact.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_common_ContactRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(LocationBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_common_LocationBeanRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(RecordResponse.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_common_RecordResponseRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(Settings.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_common_SettingsRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(ValidateBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_common_ValidateBeanRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(VisitorBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_common_VisitorBeanRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(EmployeeArcCode.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_employee_EmployeeArcCodeRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(EmployeeLockMode.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_employee_EmployeeLockModeRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(EmployeeResponse.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_employee_EmployeeResponseRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(EmployeeBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_EmployeeBeanRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(EventTrackingBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_EventTrackingBeanRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(HotelActivitesBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_HotelActivitesBeanRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(LockMode.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_LockModeRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(MarkerPoint.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_MarkerPointRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(MusicBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_MusicBeanRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(Action.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_patrol_ActionRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(FloorOriginBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_patrol_FloorOriginBeanRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(Line.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_patrol_LineRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(MediaBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_patrol_MediaBeanRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(Message.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_patrol_MessageRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(Operation.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_patrol_OperationRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(PathBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_patrol_PathBeanRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(PatrolTaskBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(Place.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_patrol_PlaceRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(Plan.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_patrol_PlanRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(Point.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_patrol_PointRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(Process.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_patrol_ProcessRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(Resource.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_patrol_ResourceRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(TransitionBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_patrol_TransitionBeanRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(TurnstileBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_patrol_TurnstileBeanRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(WaitBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_patrol_WaitBeanRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(WaterPathBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_patrol_WaterPathBeanRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(Person.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_PersonRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(PersonInfoBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_PersonInfoBeanRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(PointF.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_PointFRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(DataBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_recommantation_DataBeanRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(Record.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_RecordRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(RecordLockMode.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_RecordLockModeRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(RegisteredRecordInfo.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_RegisteredRecordInfoRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(RegisterWithAppointmentResponse.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(RobotBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_RobotBeanRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(ActionBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_semantic_ActionBeanRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(AnswerBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_semantic_AnswerBeanRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(CiotResponseBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_semantic_CiotResponseBeanRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(SemanticBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_semantic_SemanticBeanRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(AdStat.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_stat_AdStatRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(AdWatchStat.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_stat_AdWatchStatRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(RobotPersonStat.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_stat_RobotPersonStatRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(StatsRecord.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_stat_StatsRecordRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(Tactics.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_TacticsRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(Task.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_TaskRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(TemRecord.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_TemRecordRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(TemUploadRecord.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_TemUploadRecordRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(ThermometryRecordBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_ThermometryRecordBeanRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(TimeBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_TimeBeanRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(TimerReceptionTaskBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(ArcCode.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_visitor_ArcCodeRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(MediaResult.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_visitor_MediaResultRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        if (cls.equals(VisitorResponse.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_visitor_VisitorResponseRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject, z));
        }
        throw getMissingProxyClassException((Class<? extends RealmModel>) cls);
    }

    public <E extends RealmModel> E createUsingJsonStream(Class<E> cls, Realm realm, JsonReader jsonReader) throws IOException {
        checkClass(cls);
        if (cls.equals(AdvertisementsBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_ad_AdvertisementsBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(BeginBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_ad_BeginBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(CycleBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_ad_CycleBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(EndBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_ad_EndBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(GetAdListsBeanR.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_ad_GetAdListsBeanRRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(HorseRaceLampsBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(ResourcesBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_ad_ResourcesBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(TimesBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_ad_TimesBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(Attendance.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_AttendanceRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(ChildTask.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_ChildTaskRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(AckBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_common_AckBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(CompanyResponse.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_common_CompanyResponseRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(Contact.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_common_ContactRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(LocationBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_common_LocationBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(RecordResponse.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_common_RecordResponseRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(Settings.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_common_SettingsRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(ValidateBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_common_ValidateBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(VisitorBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_common_VisitorBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(EmployeeArcCode.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_employee_EmployeeArcCodeRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(EmployeeLockMode.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_employee_EmployeeLockModeRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(EmployeeResponse.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_employee_EmployeeResponseRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(EmployeeBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_EmployeeBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(EventTrackingBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_EventTrackingBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(HotelActivitesBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_HotelActivitesBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(LockMode.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_LockModeRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(MarkerPoint.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_MarkerPointRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(MusicBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_MusicBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(Action.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_patrol_ActionRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(FloorOriginBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_patrol_FloorOriginBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(Line.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_patrol_LineRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(MediaBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_patrol_MediaBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(Message.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_patrol_MessageRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(Operation.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_patrol_OperationRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(PathBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_patrol_PathBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(PatrolTaskBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(Place.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_patrol_PlaceRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(Plan.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_patrol_PlanRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(Point.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_patrol_PointRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(Process.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_patrol_ProcessRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(Resource.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_patrol_ResourceRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(TransitionBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_patrol_TransitionBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(TurnstileBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_patrol_TurnstileBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(WaitBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_patrol_WaitBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(WaterPathBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_patrol_WaterPathBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(Person.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_PersonRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(PersonInfoBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_PersonInfoBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(PointF.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_PointFRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(DataBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_recommantation_DataBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(Record.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_RecordRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(RecordLockMode.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_RecordLockModeRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(RegisteredRecordInfo.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_RegisteredRecordInfoRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(RegisterWithAppointmentResponse.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(RobotBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_RobotBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(ActionBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_semantic_ActionBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(AnswerBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_semantic_AnswerBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(CiotResponseBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_semantic_CiotResponseBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(SemanticBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_semantic_SemanticBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(AdStat.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_stat_AdStatRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(AdWatchStat.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_stat_AdWatchStatRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(RobotPersonStat.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_stat_RobotPersonStatRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(StatsRecord.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_stat_StatsRecordRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(Tactics.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_TacticsRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(Task.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_TaskRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(TemRecord.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_TemRecordRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(TemUploadRecord.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_TemUploadRecordRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(ThermometryRecordBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_ThermometryRecordBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(TimeBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_TimeBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(TimerReceptionTaskBean.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(ArcCode.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_visitor_ArcCodeRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(MediaResult.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_visitor_MediaResultRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        if (cls.equals(VisitorResponse.class)) {
            return (RealmModel) cls.cast(com_ciot_realm_db_visitor_VisitorResponseRealmProxy.createUsingJsonStream(realm, jsonReader));
        }
        throw getMissingProxyClassException((Class<? extends RealmModel>) cls);
    }

    public <E extends RealmModel> E createDetachedCopy(E e, int i, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        Class<? super Object> superclass = e.getClass().getSuperclass();
        if (superclass.equals(AdvertisementsBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_ad_AdvertisementsBeanRealmProxy.createDetachedCopy((AdvertisementsBean) e, 0, i, map));
        }
        if (superclass.equals(BeginBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_ad_BeginBeanRealmProxy.createDetachedCopy((BeginBean) e, 0, i, map));
        }
        if (superclass.equals(CycleBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_ad_CycleBeanRealmProxy.createDetachedCopy((CycleBean) e, 0, i, map));
        }
        if (superclass.equals(EndBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_ad_EndBeanRealmProxy.createDetachedCopy((EndBean) e, 0, i, map));
        }
        if (superclass.equals(GetAdListsBeanR.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_ad_GetAdListsBeanRRealmProxy.createDetachedCopy((GetAdListsBeanR) e, 0, i, map));
        }
        if (superclass.equals(HorseRaceLampsBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxy.createDetachedCopy((HorseRaceLampsBean) e, 0, i, map));
        }
        if (superclass.equals(ResourcesBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_ad_ResourcesBeanRealmProxy.createDetachedCopy((ResourcesBean) e, 0, i, map));
        }
        if (superclass.equals(TimesBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_ad_TimesBeanRealmProxy.createDetachedCopy((TimesBean) e, 0, i, map));
        }
        if (superclass.equals(Attendance.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_AttendanceRealmProxy.createDetachedCopy((Attendance) e, 0, i, map));
        }
        if (superclass.equals(ChildTask.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_ChildTaskRealmProxy.createDetachedCopy((ChildTask) e, 0, i, map));
        }
        if (superclass.equals(AckBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_common_AckBeanRealmProxy.createDetachedCopy((AckBean) e, 0, i, map));
        }
        if (superclass.equals(CompanyResponse.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_common_CompanyResponseRealmProxy.createDetachedCopy((CompanyResponse) e, 0, i, map));
        }
        if (superclass.equals(Contact.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_common_ContactRealmProxy.createDetachedCopy((Contact) e, 0, i, map));
        }
        if (superclass.equals(LocationBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_common_LocationBeanRealmProxy.createDetachedCopy((LocationBean) e, 0, i, map));
        }
        if (superclass.equals(RecordResponse.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_common_RecordResponseRealmProxy.createDetachedCopy((RecordResponse) e, 0, i, map));
        }
        if (superclass.equals(Settings.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_common_SettingsRealmProxy.createDetachedCopy((Settings) e, 0, i, map));
        }
        if (superclass.equals(ValidateBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_common_ValidateBeanRealmProxy.createDetachedCopy((ValidateBean) e, 0, i, map));
        }
        if (superclass.equals(VisitorBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_common_VisitorBeanRealmProxy.createDetachedCopy((VisitorBean) e, 0, i, map));
        }
        if (superclass.equals(EmployeeArcCode.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_employee_EmployeeArcCodeRealmProxy.createDetachedCopy((EmployeeArcCode) e, 0, i, map));
        }
        if (superclass.equals(EmployeeLockMode.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_employee_EmployeeLockModeRealmProxy.createDetachedCopy((EmployeeLockMode) e, 0, i, map));
        }
        if (superclass.equals(EmployeeResponse.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_employee_EmployeeResponseRealmProxy.createDetachedCopy((EmployeeResponse) e, 0, i, map));
        }
        if (superclass.equals(EmployeeBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_EmployeeBeanRealmProxy.createDetachedCopy((EmployeeBean) e, 0, i, map));
        }
        if (superclass.equals(EventTrackingBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_EventTrackingBeanRealmProxy.createDetachedCopy((EventTrackingBean) e, 0, i, map));
        }
        if (superclass.equals(HotelActivitesBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_HotelActivitesBeanRealmProxy.createDetachedCopy((HotelActivitesBean) e, 0, i, map));
        }
        if (superclass.equals(LockMode.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_LockModeRealmProxy.createDetachedCopy((LockMode) e, 0, i, map));
        }
        if (superclass.equals(MarkerPoint.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_MarkerPointRealmProxy.createDetachedCopy((MarkerPoint) e, 0, i, map));
        }
        if (superclass.equals(MusicBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_MusicBeanRealmProxy.createDetachedCopy((MusicBean) e, 0, i, map));
        }
        if (superclass.equals(Action.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_patrol_ActionRealmProxy.createDetachedCopy((Action) e, 0, i, map));
        }
        if (superclass.equals(FloorOriginBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_patrol_FloorOriginBeanRealmProxy.createDetachedCopy((FloorOriginBean) e, 0, i, map));
        }
        if (superclass.equals(Line.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_patrol_LineRealmProxy.createDetachedCopy((Line) e, 0, i, map));
        }
        if (superclass.equals(MediaBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_patrol_MediaBeanRealmProxy.createDetachedCopy((MediaBean) e, 0, i, map));
        }
        if (superclass.equals(Message.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_patrol_MessageRealmProxy.createDetachedCopy((Message) e, 0, i, map));
        }
        if (superclass.equals(Operation.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_patrol_OperationRealmProxy.createDetachedCopy((Operation) e, 0, i, map));
        }
        if (superclass.equals(PathBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_patrol_PathBeanRealmProxy.createDetachedCopy((PathBean) e, 0, i, map));
        }
        if (superclass.equals(PatrolTaskBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxy.createDetachedCopy((PatrolTaskBean) e, 0, i, map));
        }
        if (superclass.equals(Place.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_patrol_PlaceRealmProxy.createDetachedCopy((Place) e, 0, i, map));
        }
        if (superclass.equals(Plan.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_patrol_PlanRealmProxy.createDetachedCopy((Plan) e, 0, i, map));
        }
        if (superclass.equals(Point.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_patrol_PointRealmProxy.createDetachedCopy((Point) e, 0, i, map));
        }
        if (superclass.equals(Process.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_patrol_ProcessRealmProxy.createDetachedCopy((Process) e, 0, i, map));
        }
        if (superclass.equals(Resource.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_patrol_ResourceRealmProxy.createDetachedCopy((Resource) e, 0, i, map));
        }
        if (superclass.equals(TransitionBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_patrol_TransitionBeanRealmProxy.createDetachedCopy((TransitionBean) e, 0, i, map));
        }
        if (superclass.equals(TurnstileBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_patrol_TurnstileBeanRealmProxy.createDetachedCopy((TurnstileBean) e, 0, i, map));
        }
        if (superclass.equals(WaitBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_patrol_WaitBeanRealmProxy.createDetachedCopy((WaitBean) e, 0, i, map));
        }
        if (superclass.equals(WaterPathBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_patrol_WaterPathBeanRealmProxy.createDetachedCopy((WaterPathBean) e, 0, i, map));
        }
        if (superclass.equals(Person.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_PersonRealmProxy.createDetachedCopy((Person) e, 0, i, map));
        }
        if (superclass.equals(PersonInfoBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_PersonInfoBeanRealmProxy.createDetachedCopy((PersonInfoBean) e, 0, i, map));
        }
        if (superclass.equals(PointF.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_PointFRealmProxy.createDetachedCopy((PointF) e, 0, i, map));
        }
        if (superclass.equals(DataBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_recommantation_DataBeanRealmProxy.createDetachedCopy((DataBean) e, 0, i, map));
        }
        if (superclass.equals(Record.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_RecordRealmProxy.createDetachedCopy((Record) e, 0, i, map));
        }
        if (superclass.equals(RecordLockMode.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_RecordLockModeRealmProxy.createDetachedCopy((RecordLockMode) e, 0, i, map));
        }
        if (superclass.equals(RegisteredRecordInfo.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_RegisteredRecordInfoRealmProxy.createDetachedCopy((RegisteredRecordInfo) e, 0, i, map));
        }
        if (superclass.equals(RegisterWithAppointmentResponse.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxy.createDetachedCopy((RegisterWithAppointmentResponse) e, 0, i, map));
        }
        if (superclass.equals(RobotBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_RobotBeanRealmProxy.createDetachedCopy((RobotBean) e, 0, i, map));
        }
        if (superclass.equals(ActionBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_semantic_ActionBeanRealmProxy.createDetachedCopy((ActionBean) e, 0, i, map));
        }
        if (superclass.equals(AnswerBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_semantic_AnswerBeanRealmProxy.createDetachedCopy((AnswerBean) e, 0, i, map));
        }
        if (superclass.equals(CiotResponseBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_semantic_CiotResponseBeanRealmProxy.createDetachedCopy((CiotResponseBean) e, 0, i, map));
        }
        if (superclass.equals(SemanticBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_semantic_SemanticBeanRealmProxy.createDetachedCopy((SemanticBean) e, 0, i, map));
        }
        if (superclass.equals(AdStat.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_stat_AdStatRealmProxy.createDetachedCopy((AdStat) e, 0, i, map));
        }
        if (superclass.equals(AdWatchStat.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_stat_AdWatchStatRealmProxy.createDetachedCopy((AdWatchStat) e, 0, i, map));
        }
        if (superclass.equals(RobotPersonStat.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_stat_RobotPersonStatRealmProxy.createDetachedCopy((RobotPersonStat) e, 0, i, map));
        }
        if (superclass.equals(StatsRecord.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_stat_StatsRecordRealmProxy.createDetachedCopy((StatsRecord) e, 0, i, map));
        }
        if (superclass.equals(Tactics.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_TacticsRealmProxy.createDetachedCopy((Tactics) e, 0, i, map));
        }
        if (superclass.equals(Task.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_TaskRealmProxy.createDetachedCopy((Task) e, 0, i, map));
        }
        if (superclass.equals(TemRecord.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_TemRecordRealmProxy.createDetachedCopy((TemRecord) e, 0, i, map));
        }
        if (superclass.equals(TemUploadRecord.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_TemUploadRecordRealmProxy.createDetachedCopy((TemUploadRecord) e, 0, i, map));
        }
        if (superclass.equals(ThermometryRecordBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_ThermometryRecordBeanRealmProxy.createDetachedCopy((ThermometryRecordBean) e, 0, i, map));
        }
        if (superclass.equals(TimeBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_TimeBeanRealmProxy.createDetachedCopy((TimeBean) e, 0, i, map));
        }
        if (superclass.equals(TimerReceptionTaskBean.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxy.createDetachedCopy((TimerReceptionTaskBean) e, 0, i, map));
        }
        if (superclass.equals(ArcCode.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_visitor_ArcCodeRealmProxy.createDetachedCopy((ArcCode) e, 0, i, map));
        }
        if (superclass.equals(MediaResult.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_visitor_MediaResultRealmProxy.createDetachedCopy((MediaResult) e, 0, i, map));
        }
        if (superclass.equals(VisitorResponse.class)) {
            return (RealmModel) superclass.cast(com_ciot_realm_db_visitor_VisitorResponseRealmProxy.createDetachedCopy((VisitorResponse) e, 0, i, map));
        }
        throw getMissingProxyClassException((Class<? extends RealmModel>) superclass);
    }
}
