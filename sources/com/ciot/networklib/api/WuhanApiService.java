package com.ciot.networklib.api;

import com.ciot.networklib.bean.ArcSoftActiveInfo;
import com.ciot.networklib.bean.CoapBean;
import com.ciot.networklib.bean.Md5Bean;
import com.ciot.networklib.bean.NewArcCode;
import com.ciot.networklib.bean.hotel.FloorBean;
import com.ciot.networklib.bean.hotel.ResigerTypeBean;
import com.ciot.networklib.bean.hotel.RoomBean;
import com.ciot.networklib.bean.log.CreateRobotLogResponse;
import com.ciot.networklib.bean.meeting.MeetingInfoBean;
import com.ciot.networklib.bean.meeting.MeetingPersonBean;
import com.ciot.networklib.bean.version.VersionBean;
import com.ciot.networklib.bean.version.record.UpdateVersionRecordResponse;
import com.ciot.networklib.oss.OssConfigResponse;
import com.ciot.realm.db.common.CompanyResponse;
import com.ciot.realm.db.common.RecordResponse;
import com.ciot.realm.db.employee.EmployeeResponse;
import com.ciot.realm.db.visitor.VisitorResponse;
import com.example.sroslibrary.bean.RobotStatusResponse;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.reactivex.Observable;
import java.util.List;
import kotlin.Metadata;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

@Metadata(d1 = {"\u0000Ä\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\bH'J$\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\n\u001a\u00020\u00062\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0006H'J*\u0010\f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\r\u0018\u00010\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\bH'J$\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0007\u001a\u00020\b2\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0006H'J$\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0010\u001a\u00020\u00062\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0006H'J\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0006H'J\u001a\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u00032\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0006H'J \u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00150\u00032\n\b\u0001\u0010\u0017\u001a\u0004\u0018\u00010\u0006H'J\"\u0010\u0018\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u00150\u00032\n\b\u0001\u0010\u0017\u001a\u0004\u0018\u00010\u0006H'J$\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00032\b\b\u0001\u0010\u001c\u001a\u00020\u00062\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0006H'J*\u0010\u001d\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0016\u0018\u00010\u00032\n\b\u0001\u0010\u001c\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0006H'J&\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0001\u0010\u001f\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0017\u001a\u0004\u0018\u00010\u0006H'J$\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u001c\u001a\u00020\u00062\n\b\u0001\u0010\u0017\u001a\u0004\u0018\u00010\u0006H'J&\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u00032\n\b\u0001\u0010\u001c\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0006H'J$\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0001\u0010\n\u001a\u00020\u0006H'J*\u0010%\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0019\u0018\u00010\u00032\n\b\u0001\u0010\u001c\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0006H'J&\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00130\u00032\n\b\u0001\u0010\u001c\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0017\u001a\u0004\u0018\u00010\u0006H'J \u0010'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0\u00150\u00032\n\b\u0001\u0010\u0017\u001a\u0004\u0018\u00010\u0006H'J\"\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010)\u001a\u00020\u00062\b\b\u0001\u0010*\u001a\u00020\u0006H'J$\u0010+\u001a\b\u0012\u0004\u0012\u00020,0\u00032\b\b\u0001\u0010-\u001a\u00020\u00062\n\b\u0001\u0010.\u001a\u0004\u0018\u00010\u0006H'J\u0018\u0010/\u001a\b\u0012\u0004\u0012\u0002000\u00032\b\b\u0001\u0010)\u001a\u00020\u0006H'J\"\u00101\u001a\b\u0012\u0004\u0012\u0002020\u00032\b\b\u0001\u00103\u001a\u00020\u00062\b\b\u0001\u0010\u000b\u001a\u00020\u0006H'J\"\u00104\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u00105\u001a\u00020\u00062\b\b\u0001\u0010\u000b\u001a\u00020\u0006H'J,\u00106\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0017\u001a\u00020\u00062\b\b\u0001\u00107\u001a\u00020\u00062\b\b\u0001\u0010\u0010\u001a\u00020\u0006H'J$\u00108\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0001\u0010\n\u001a\u00020\u0006H'J$\u00109\u001a\b\u0012\u0004\u0012\u00020:0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0001\u0010\n\u001a\u00020\u0006H'J\"\u0010;\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u000b\u001a\u00020\u00062\b\b\u0001\u0010<\u001a\u00020\u0006H'J,\u0010=\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020?0>0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010@\u001a\u0004\u0018\u00010\u0006H'J,\u0010A\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0017\u001a\u00020\u00062\b\b\u0001\u00107\u001a\u00020\u00062\b\b\u0001\u0010\u0010\u001a\u00020\u0006H'J.\u0010B\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0001\u00107\u001a\u00020\u00062\b\b\u0001\u0010\n\u001a\u00020\u0006H'J.\u0010C\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0001\u0010\u001c\u001a\u00020\u00062\b\b\u0001\u0010\n\u001a\u00020\u0006H'J\u001a\u0010D\u001a\b\u0012\u0004\u0012\u00020E0\u00032\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0006H'J,\u0010F\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020G0>0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u001c\u001a\u0004\u0018\u00010\u0006H'J0\u0010H\u001a\b\u0012\u0004\u0012\u00020I0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0001\u0010\n\u001a\u00020\u00062\n\b\u0001\u0010J\u001a\u0004\u0018\u00010\u0006H'J\"\u0010K\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010)\u001a\u00020\u00062\b\b\u0001\u0010L\u001a\u00020MH'J\u0018\u0010N\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010)\u001a\u00020\u0006H'J&\u0010O\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0001\u0010P\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0006H'J&\u0010Q\u001a\b\u0012\u0004\u0012\u00020\u00130\u00032\n\b\u0001\u0010R\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0006H'J&\u0010S\u001a\b\u0012\u0004\u0012\u00020T0\u00032\n\b\u0001\u0010\u001c\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0006H'J\u0018\u0010U\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0007\u001a\u00020\bH'J\u0018\u0010V\u001a\b\u0012\u0004\u0012\u00020W0\u00032\b\b\u0001\u0010)\u001a\u00020\u0006H'J*\u0010X\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010Y\u0018\u00010\u00032\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0006H'J$\u0010Z\u001a\b\u0012\u0004\u0012\u00020G0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0001\u0010\u0007\u001a\u00020\bH'J&\u0010[\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0006H'J\u001a\u0010\\\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0006H'J,\u0010]\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010^\u001a\u00020\u00062\b\b\u0001\u0010_\u001a\u00020\u0006H'J&\u0010`\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0001\u0010a\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0017\u001a\u0004\u0018\u00010\u0006H'J&\u0010b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0006H'J$\u0010c\u001a\b\u0012\u0004\u0012\u00020\"0\u00032\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u00062\b\b\u0001\u0010\u0007\u001a\u00020\bH'J\u0018\u0010d\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0007\u001a\u00020\bH'J&\u0010e\u001a\b\u0012\u0004\u0012\u00020f0\u00032\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u001c\u001a\u0004\u0018\u00010\u0006H'J$\u0010g\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u001c\u001a\u00020\u00062\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0006H'J&\u0010h\u001a\b\u0012\u0004\u0012\u00020i0\u00032\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\bH'J$\u0010j\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0007\u001a\u00020\b2\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0006H'J\"\u0010k\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\u0005\u001a\u00020\u0006H'J$\u0010l\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0007\u001a\u00020\b2\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0006H'J\"\u0010m\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010n\u001a\u00020\u00062\b\b\u0001\u0010\u000b\u001a\u00020\u0006H'¨\u0006o"}, d2 = {"Lcom/ciot/networklib/api/WuhanApiService;", "Lcom/ciot/networklib/api/DownloadService;", "attendanceClockIn", "Lio/reactivex/Observable;", "Lokhttp3/ResponseBody;", "serverToken", "", "body", "Lokhttp3/RequestBody;", "companiesFindByProjectYuYin", "projectId", "token", "createUploadRobotLog", "Lcom/ciot/networklib/bean/log/CreateRobotLogResponse;", "deletePatrolTaskById", "employeesFindByCompany", "companyId", "employeesFindByProject", "findAllCompany", "Lcom/google/gson/JsonArray;", "findAllEmployeeByProject", "", "Lcom/ciot/realm/db/employee/EmployeeResponse;", "accessToken", "findAllVisitorByProject", "Lcom/ciot/realm/db/visitor/VisitorResponse;", "findCompanyById", "Lcom/ciot/realm/db/common/CompanyResponse;", "id", "findEmployeeById", "findPatrolTaskByRobotNumber", "robotid", "findPatrolTaskByTaskId", "findRecordById", "Lcom/ciot/realm/db/common/RecordResponse;", "findRegisterType", "Lcom/ciot/networklib/bean/hotel/ResigerTypeBean;", "findVisitorById", "getAdLists", "getAllRecords", "getArcSoftFaceCode", "url", "arc", "getArcSoftInfo", "Lcom/ciot/networklib/bean/ArcSoftActiveInfo;", "robot", "mac", "getCoapUrl", "Lcom/ciot/networklib/bean/CoapBean;", "getCodeByFeatureId", "Lcom/ciot/networklib/bean/NewArcCode;", "featureId", "getCodeForAccount", "account", "getDistantlyAttracts", "robotId", "getHotelActivitys", "getHotelFloor", "Lcom/ciot/networklib/bean/hotel/FloorBean;", "getLatestVersion", "robotNumber", "getMeetingsByCompany", "", "Lcom/ciot/networklib/bean/meeting/MeetingInfoBean;", "company", "getNearbyAttracts", "getNewsList", "getNewsSpec", "getOssConfig", "Lcom/ciot/networklib/oss/OssConfigResponse;", "getPersonsByMeeting", "Lcom/ciot/networklib/bean/meeting/MeetingPersonBean;", "getRoom", "Lcom/ciot/networklib/bean/hotel/RoomBean;", "floorId", "getSenseFaceCode", "st", "", "getSimInfo", "getSimsDetail", "iccid", "getTactics", "ruletype", "getVersionById", "Lcom/ciot/networklib/bean/version/VersionBean;", "login", "peekImge", "Lcom/ciot/networklib/bean/Md5Bean;", "postAdEvents", "Lcom/google/gson/JsonObject;", "postMeetingPersonSign", "postRobotInfo", "queryCurrentProject", "queryPatrolRecordByPage", "start", "limit", "queryWeather", "city", "registHotelData", "registerVisitorRecord", "robotAllow", "robotIsLock", "Lcom/example/sroslibrary/bean/RobotStatusResponse;", "robots", "updateVersionRecord", "Lcom/ciot/networklib/bean/version/record/UpdateVersionRecordResponse;", "uploadAlarm", "uploadPatrolRecord", "uploadPatrolTask", "verifyCode", "code", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: WuhanApiService.kt */
public interface WuhanApiService extends DownloadService {
    @POST("api/Attendances")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<ResponseBody> attendanceClockIn(@Query("access_token") String str, @Body RequestBody requestBody);

    @GET("/api/Companies/findByProjectYuYi")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<ResponseBody> companiesFindByProjectYuYin(@Query("project") String str, @Query("access_token") String str2);

    @POST("api/Robots/log/create")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<CreateRobotLogResponse> createUploadRobotLog(@Query("access_token") String str, @Body RequestBody requestBody);

    @POST("api/patrols/task/delete")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<ResponseBody> deletePatrolTaskById(@Body RequestBody requestBody, @Query("access_token") String str);

    @GET("/api/Employees/findByCompany")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<ResponseBody> employeesFindByCompany(@Query("company") String str, @Query("access_token") String str2);

    @GET("/api/Employees/findEmployeesByProject")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<ResponseBody> employeesFindByProject(@Query("access_token") String str);

    @GET("api/Companies/findByProject")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<JsonArray> findAllCompany(@Query("access_token") String str);

    @GET("api/Employees/findEmployeesByProject")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<List<EmployeeResponse>> findAllEmployeeByProject(@Query("access_token") String str);

    @GET("api/Visitors/findVisitorsByProject")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<List<VisitorResponse>> findAllVisitorByProject(@Query("access_token") String str);

    @GET("api/Companies/{id}")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<CompanyResponse> findCompanyById(@Path("id") String str, @Query("access_token") String str2);

    @GET("api/Employees/{id}")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<EmployeeResponse> findEmployeeById(@Path("id") String str, @Query("access_token") String str2);

    @GET("api/patrols/task/findbypublish")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<ResponseBody> findPatrolTaskByRobotNumber(@Query("robotid") String str, @Query("access_token") String str2);

    @GET("api/patrols/task/findbytaskid")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<ResponseBody> findPatrolTaskByTaskId(@Query("taskId") String str, @Query("access_token") String str2);

    @GET("api/Records/{id}")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<RecordResponse> findRecordById(@Path("id") String str, @Query("access_token") String str2);

    @GET("api/HotelExes/findRegisterType?")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<ResigerTypeBean> findRegisterType(@Query("access_token") String str, @Query("projectId") String str2);

    @GET("api/Visitors/{id}")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<VisitorResponse> findVisitorById(@Path("id") String str, @Query("access_token") String str2);

    @GET("api/Playlists/queryByRobot")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<JsonArray> getAdLists(@Query("id") String str, @Query("access_token") String str2);

    @GET("api/Records/findByProject")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<List<RecordResponse>> getAllRecords(@Query("access_token") String str);

    @GET
    Observable<ResponseBody> getArcSoftFaceCode(@Url String str, @Query("arcex") String str2);

    @GET("api/ArcSofts/queryCodeByRobot")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<ArcSoftActiveInfo> getArcSoftInfo(@Query("robot") String str, @Query("mac") String str2);

    @GET
    Observable<CoapBean> getCoapUrl(@Url String str);

    @GET("api/Media/getCodeByFeaid")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<NewArcCode> getCodeByFeatureId(@Query("feaid") String str, @Query("access_token") String str2);

    @GET("/api/Users/findPropertyByAccount")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<ResponseBody> getCodeForAccount(@Query("account") String str, @Query("access_token") String str2);

    @GET("api/Attracts/taking")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<ResponseBody> getDistantlyAttracts(@Query("access_token") String str, @Query("robotId") String str2, @Query("projectId") String str3);

    @GET("api/HotelExes/queryActivity")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<ResponseBody> getHotelActivitys(@Query("access_token") String str, @Query("projectId") String str2);

    @GET("api/HotelExes/queryFloor")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<FloorBean> getHotelFloor(@Query("access_token") String str, @Query("projectId") String str2);

    @GET("api/Versions/latest")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<ResponseBody> getLatestVersion(@Query("access_token") String str, @Query("id") String str2);

    @GET("api/Meetings/findByCompany")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<List<MeetingInfoBean>> getMeetingsByCompany(@Query("access_token") String str, @Query("company") String str2);

    @GET("api/Entertains/taking")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<ResponseBody> getNearbyAttracts(@Query("access_token") String str, @Query("robotId") String str2, @Query("projectId") String str3);

    @GET("api/Informatives/taking")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<ResponseBody> getNewsList(@Query("access_token") String str, @Query("robotId") String str2, @Query("projectId") String str3);

    @GET("api/Informatives/takingById")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<ResponseBody> getNewsSpec(@Query("access_token") String str, @Query("id") String str2, @Query("projectId") String str3);

    @GET("api/Media/ossConfig")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<OssConfigResponse> getOssConfig(@Query("access_token") String str);

    @GET("api/Meetings/findPersonsByMeeting")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<List<MeetingPersonBean>> getPersonsByMeeting(@Query("access_token") String str, @Query("id") String str2);

    @GET("api/HotelExes/queryRoom")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<RoomBean> getRoom(@Query("access_token") String str, @Query("projectId") String str2, @Query("floorId") String str3);

    @GET
    Observable<ResponseBody> getSenseFaceCode(@Url String str, @Query("st") int i);

    @GET
    @Headers({"Domain-Name: OTHER"})
    Observable<ResponseBody> getSimInfo(@Url String str);

    @GET("api/Sims/detail")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<ResponseBody> getSimsDetail(@Query("iccid") String str, @Query("access_token") String str2);

    @GET("api/Tactics/findByCompany")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<JsonArray> getTactics(@Query("ruletype") String str, @Query("access_token") String str2);

    @GET("api/Versions/{id}")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<VersionBean> getVersionById(@Path("id") String str, @Query("access_token") String str2);

    @POST("/api/Users/login")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<ResponseBody> login(@Body RequestBody requestBody);

    @GET
    Observable<Md5Bean> peekImge(@Url String str);

    @POST("api/ADEvents")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<JsonObject> postAdEvents(@Body RequestBody requestBody, @Query("access_token") String str);

    @POST("api/Meetings/persons/sign")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<MeetingPersonBean> postMeetingPersonSign(@Query("access_token") String str, @Body RequestBody requestBody);

    @POST("api/Robots/RobotInfo")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<ResponseBody> postRobotInfo(@Body RequestBody requestBody, @Query("access_token") String str);

    @GET("api/Projects/current")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<ResponseBody> queryCurrentProject(@Query("access_token") String str);

    @POST("api/Patrols/record/queryByPage")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<ResponseBody> queryPatrolRecordByPage(@Query("access_token") String str, @Query("start") String str2, @Query("limit") String str3);

    @GET("api/Information/queryWeather")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<ResponseBody> queryWeather(@Query("city") String str, @Query("access_token") String str2);

    @POST("api/HotelExes/createRegister")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<ResponseBody> registHotelData(@Body RequestBody requestBody, @Query("access_token") String str);

    @POST("api/Records/visitor")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<RecordResponse> registerVisitorRecord(@Query("access_token") String str, @Body RequestBody requestBody);

    @POST("/api/Robots/V3.0/allow")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<ResponseBody> robotAllow(@Body RequestBody requestBody);

    @GET("api/Robots/lockStatus")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<RobotStatusResponse> robotIsLock(@Query("access_token") String str, @Query("id") String str2);

    @GET("/api/Robots/{id}")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<ResponseBody> robots(@Path("id") String str, @Query("access_token") String str2);

    @POST("api/Versions/updateRecord")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<UpdateVersionRecordResponse> updateVersionRecord(@Query("access_token") String str, @Body RequestBody requestBody);

    @POST("api/faults")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<ResponseBody> uploadAlarm(@Body RequestBody requestBody, @Query("access_token") String str);

    @POST("api/patrols/record/create")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<ResponseBody> uploadPatrolRecord(@Body RequestBody requestBody, @Query("access_token") String str);

    @POST("api/patrols/task/upload")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<ResponseBody> uploadPatrolTask(@Body RequestBody requestBody, @Query("access_token") String str);

    @GET("/api/Records/verifyCode")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<ResponseBody> verifyCode(@Query("code") String str, @Query("access_token") String str2);
}
