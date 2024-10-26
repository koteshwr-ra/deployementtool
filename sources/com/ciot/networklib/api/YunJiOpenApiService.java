package com.ciot.networklib.api;

import io.reactivex.Observable;
import kotlin.Metadata;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\bf\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eJN\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u00062\b\b\u0001\u0010\b\u001a\u00020\t2\b\b\u0001\u0010\n\u001a\u00020\u00062\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u0006H'JN\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u00062\b\b\u0001\u0010\b\u001a\u00020\t2\b\b\u0001\u0010\n\u001a\u00020\u00062\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u0006H'¨\u0006\u000f"}, d2 = {"Lcom/ciot/networklib/api/YunJiOpenApiService;", "", "downloadMap", "Lio/reactivex/Observable;", "Lokhttp3/ResponseBody;", "appname", "", "secret", "ts", "", "sign", "productId", "mapName", "uploadMap", "Companion", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: YunJiOpenApiService.kt */
public interface YunJiOpenApiService {
    public static final String APP_NAME = "csst.com";
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final String SECRET = "3012a144d66fac32541aa4ab6159f38b";

    @FormUrlEncoded
    @POST("/openapi/v1/robot/map/download")
    Observable<ResponseBody> downloadMap(@Field("appname") String str, @Field("secret") String str2, @Field("ts") long j, @Field("sign") String str3, @Field("productId") String str4, @Field("mapName") String str5);

    @FormUrlEncoded
    @POST("/openapi/v1/robot/map/upload")
    Observable<ResponseBody> uploadMap(@Field("appname") String str, @Field("secret") String str2, @Field("ts") long j, @Field("sign") String str3, @Field("productId") String str4, @Field("mapName") String str5);

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/ciot/networklib/api/YunJiOpenApiService$Companion;", "", "()V", "APP_NAME", "", "SECRET", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: YunJiOpenApiService.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final String APP_NAME = "csst.com";
        public static final String SECRET = "3012a144d66fac32541aa4ab6159f38b";

        private Companion() {
        }
    }
}
