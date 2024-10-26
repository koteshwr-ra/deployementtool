package com.ciot.networklib.api;

import com.ciot.networklib.bean.CommonResponseBean;
import io.reactivex.Observable;
import java.util.Map;
import kotlin.Metadata;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J@\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u00062\b\b\u0001\u0010\b\u001a\u00020\u00062\b\b\u0001\u0010\t\u001a\u00020\u00062\b\b\u0001\u0010\n\u001a\u00020\u0006H'J$\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0014\b\u0001\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\fH'J@\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00032\b\b\u0001\u0010\u000f\u001a\u00020\u00062\b\b\u0001\u0010\b\u001a\u00020\u00062\b\b\u0001\u0010\t\u001a\u00020\u00062\b\b\u0001\u0010\u0010\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u0006H'J$\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00032\u0014\b\u0001\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\fH'J@\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00032\b\b\u0001\u0010\u000f\u001a\u00020\u00062\b\b\u0001\u0010\b\u001a\u00020\u00062\b\b\u0001\u0010\t\u001a\u00020\u00062\b\b\u0001\u0010\u0010\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u0006H'J$\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00032\u0014\b\u0001\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\fH'J&\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0016\b\u0001\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00060\fH'J$\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0014\b\u0001\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\fH'J@\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u000f\u001a\u00020\u00062\b\b\u0001\u0010\b\u001a\u00020\u00062\b\b\u0001\u0010\t\u001a\u00020\u00062\b\b\u0001\u0010\u0010\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u0006H'J$\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0014\b\u0001\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\fH'J$\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0014\b\u0001\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\fH'JB\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u000f\u001a\u00020\u00062\b\b\u0001\u0010\b\u001a\u00020\u00062\b\b\u0001\u0010\t\u001a\u00020\u00062\b\b\u0001\u0010\u0010\u001a\u00020\u00062\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0006H'J$\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0014\b\u0001\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\fH'J\u0018\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0018\u001a\u00020\u0019H'J@\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00032\b\b\u0001\u0010\u000f\u001a\u00020\u00062\b\b\u0001\u0010\b\u001a\u00020\u00062\b\b\u0001\u0010\t\u001a\u00020\u00062\b\b\u0001\u0010\u0010\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u0006H'J$\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00032\u0014\b\u0001\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\fH'JT\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u000f\u001a\u00020\u00062\b\b\u0001\u0010\b\u001a\u00020\u00062\b\b\u0001\u0010\t\u001a\u00020\u00062\b\b\u0001\u0010\u0010\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u00062\b\b\u0001\u0010\u001c\u001a\u00020\u00062\b\b\u0001\u0010\u001d\u001a\u00020\u001eH'J8\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0014\b\u0001\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\f2\b\b\u0001\u0010\u001c\u001a\u00020\u00062\b\b\u0001\u0010\u001d\u001a\u00020\u001eH'J\u0018\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0018\u001a\u00020\u0019H'¨\u0006 "}, d2 = {"Lcom/ciot/networklib/api/SemanticIntentApiService;", "", "getAllQaList", "Lio/reactivex/Observable;", "Lokhttp3/ResponseBody;", "projectId", "", "area", "user", "token", "deviecId", "map", "", "getFunctionTxts", "Lcom/ciot/networklib/bean/CommonResponseBean;", "deviceid", "project", "getHomeCompanys", "getHotWord", "getNavDataFromCompanyName", "getProjectCompanysAlias", "getProjectProductsAlias", "getRecommendations", "getSemanticResult", "body", "Lokhttp3/RequestBody;", "getStandbyPageTxts", "updateTaskState", "task", "action", "", "uploadRecord", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: SemanticIntentApiService.kt */
public interface SemanticIntentApiService {
    @GET("/v1/GetQaList")
    Observable<ResponseBody> getAllQaList(@Query("project") String str, @Query("area") String str2, @Query("user") String str3, @Query("token") String str4, @Query("deviceid") String str5);

    @GET("/v1/GetQaList")
    Observable<ResponseBody> getAllQaList(@QueryMap Map<String, String> map);

    @GET("/v1/GetFunctionTxts")
    Observable<CommonResponseBean> getFunctionTxts(@Query("deviceid") String str, @Query("user") String str2, @Query("token") String str3, @Query("project") String str4, @Query("area") String str5);

    @GET("/v1/GetFunctionTxts")
    Observable<CommonResponseBean> getFunctionTxts(@QueryMap Map<String, String> map);

    @GET("/v1/GetHomeCompanys")
    Observable<CommonResponseBean> getHomeCompanys(@Query("deviceid") String str, @Query("user") String str2, @Query("token") String str3, @Query("project") String str4, @Query("area") String str5);

    @GET("/v1/GetHomeCompanys")
    Observable<CommonResponseBean> getHomeCompanys(@QueryMap Map<String, String> map);

    @GET("/v1/HotWord")
    Observable<ResponseBody> getHotWord(@QueryMap Map<String, String> map);

    @GET("/v1/GetNavDataFromCompanyName")
    Observable<ResponseBody> getNavDataFromCompanyName(@QueryMap Map<String, String> map);

    @GET("/v1/GetProjectCompanys")
    Observable<ResponseBody> getProjectCompanysAlias(@Query("deviceid") String str, @Query("user") String str2, @Query("token") String str3, @Query("project") String str4, @Query("area") String str5);

    @GET("/v1/GetProjectCompanys")
    Observable<ResponseBody> getProjectCompanysAlias(@QueryMap Map<String, String> map);

    @GET("/v1/GetProjectProducts")
    Observable<ResponseBody> getProjectProductsAlias(@QueryMap Map<String, String> map);

    @GET("/v1/QueryRecommendations")
    Observable<ResponseBody> getRecommendations(@Query("deviceid") String str, @Query("user") String str2, @Query("token") String str3, @Query("project") String str4, @Query("area") String str5);

    @GET("/v1/QueryRecommendations")
    Observable<ResponseBody> getRecommendations(@QueryMap Map<String, String> map);

    @POST("/v1/QuerySemanticResult")
    Observable<ResponseBody> getSemanticResult(@Body RequestBody requestBody);

    @GET("/v1/GetStandbyPageTxts")
    Observable<CommonResponseBean> getStandbyPageTxts(@Query("deviceid") String str, @Query("user") String str2, @Query("token") String str3, @Query("project") String str4, @Query("area") String str5);

    @GET("/v1/GetStandbyPageTxts")
    Observable<CommonResponseBean> getStandbyPageTxts(@QueryMap Map<String, String> map);

    @GET("/v1/TaskManger")
    Observable<ResponseBody> updateTaskState(@Query("deviceid") String str, @Query("user") String str2, @Query("token") String str3, @Query("project") String str4, @Query("area") String str5, @Query("task") String str6, @Query("action") int i);

    @GET("/v1/TaskManger")
    Observable<ResponseBody> updateTaskState(@QueryMap Map<String, String> map, @Query("task") String str, @Query("action") int i);

    @POST("/v1/upload-record")
    Observable<ResponseBody> uploadRecord(@Body RequestBody requestBody);
}
