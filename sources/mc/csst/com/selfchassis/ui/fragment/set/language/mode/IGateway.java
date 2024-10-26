package mc.csst.com.selfchassis.ui.fragment.set.language.mode;

import io.reactivex.Observable;
import mc.csst.com.selfchassis.ui.fragment.set.language.bean.GetGatewayRespBean;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IGateway {
    @GET("platurl")
    Observable<GetGatewayRespBean> getGateway(@Query("default") Boolean bool);

    @FormUrlEncoded
    @POST("setplat")
    Observable<ResponseBody> setGateway(@Field("env") String str);
}
