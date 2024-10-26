package com.ciot.networklib.api;

import com.ciot.networklib.bean.TestBean;
import io.reactivex.Observable;
import kotlin.Metadata;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H'J\u000e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006H'J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0006H'J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0003H'¨\u0006\n"}, d2 = {"Lcom/ciot/networklib/api/TestApiService;", "", "getAllCompId2Bean", "Lretrofit2/Call;", "Lcom/ciot/networklib/bean/TestBean;", "getAllCompId2ObservableBean", "Lio/reactivex/Observable;", "getAllCompId2ObservableResponseBody", "Lokhttp3/ResponseBody;", "getAllCompId2ResponseBody", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: TestApiService.kt */
public interface TestApiService {
    @GET("/data/get_all_comp_id")
    Call<TestBean> getAllCompId2Bean();

    @GET("/data/get_all_comp_id")
    Observable<TestBean> getAllCompId2ObservableBean();

    @GET("/data/get_all_comp_id")
    Observable<ResponseBody> getAllCompId2ObservableResponseBody();

    @GET("/data/get_all_comp_id")
    Call<ResponseBody> getAllCompId2ResponseBody();
}
