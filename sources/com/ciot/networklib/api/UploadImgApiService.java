package com.ciot.networklib.api;

import com.ciot.networklib.bean.UploadImageResponse;
import com.ciot.networklib.bean.media.UploadImageBaseUrlResponse;
import com.ciot.networklib.bean.media.UploadImageIdResponse;
import com.ciot.networklib.bean.temp.TemResultBean;
import com.ciot.networklib.bean.temp.UploadTemImageUrlResponse;
import com.ciot.networklib.bean.temp.UploadTemRecordResponse;
import com.ciot.networklib.bean.thermometry.CreateThermometryResponse;
import com.ciot.networklib.bean.upload.OssUploadResponse;
import com.ciot.networklib.bean.upload.UploadResponse;
import com.ciot.realm.db.RegisterWithAppointmentResponse;
import com.google.gson.JsonObject;
import io.reactivex.Observable;
import java.util.List;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface UploadImgApiService {
    @POST("api/Visitors/Temperatures")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<CreateThermometryResponse> CreateThermometry(@Body RequestBody requestBody, @Query("access_token") String str);

    @GET("api/Visitors/Temperature/getTemUrl")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<UploadTemImageUrlResponse> getTemUrl(@Query("id") String str, @Query("access_token") String str2);

    @POST("api/Media")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<UploadImageResponse> getUploadFileUrl(@Query("access_token") String str);

    @GET("api/Media/getMediaConfig")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<UploadImageBaseUrlResponse> getUploadImageBaseUrl(@Query("access_token") String str);

    @POST("api/Media")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<UploadImageIdResponse> getUploadImageId(@Query("access_token") String str);

    @POST("api/Visitors/Temperature/bulkCreateTem")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<List<TemResultBean>> postBulkCreateTem(@Body RequestBody requestBody, @Query("access_token") String str);

    @POST("api/Visitors/temperature/newBulkCreateTem")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<UploadTemRecordResponse> postNewBulkCreateTem(@Body RequestBody requestBody, @Query("access_token") String str);

    @POST("api/Visitors/temperature/ossBulkCreateTem")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<OssUploadResponse> postOssBulkCreateTem(@Body RequestBody requestBody, @Query("access_token") String str);

    @POST("api/Visitors/temperature/uploadAbnormal")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<UploadTemRecordResponse> postUploadAbnormal(@Body RequestBody requestBody, @Query("access_token") String str);

    @POST("api/Visitors/temperature/uploadOssAbnormal")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<OssUploadResponse> postUploadOssAbnormal(@Body RequestBody requestBody, @Query("access_token") String str);

    @PUT("api/Records/getMark")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<JsonObject> recordVisitorInfo(@Query("id") String str, @Query("robot") String str2, @Query("markFace") String str3, @Query("verification") String str4, @Query("access_token") String str5);

    @PUT("api/Records/getMark")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<JsonObject> recordVisitorInfo(@Query("id") String str, @Query("robot") String str2, @Query("ossFace") String str3, @Query("verification") String str4, @Query("access_token") String str5, @Query("healthcode") String str6, @Query("healthcolor") String str7);

    @PUT("api/Records/register")
    @Headers({"Domain-Name: PROPERTY"})
    Observable<RegisterWithAppointmentResponse> registerWithAppointment(@Query("id") String str, @Query("access_token") String str2);

    @Multipart
    @POST
    @Headers({"Domain-Name: FILE"})
    Observable<ResponseBody> uploadFile(@Url String str, @Part MultipartBody.Part part);

    @POST("{id}")
    @Headers({"Domain-Name: FILE"})
    Observable<UploadResponse> uploadImage(@Path("id") String str, @Body RequestBody requestBody);

    @POST
    @Headers({"Domain-Name: FILE"})
    Observable<UploadResponse> uploadTemImage(@Url String str, @Body RequestBody requestBody);
}
