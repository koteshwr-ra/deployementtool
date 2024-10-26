package com.ciot.networklib.api;

import com.ciot.networklib.bean.diagnosis.DiagnosisReponseBean;
import io.reactivex.Observable;
import kotlin.Metadata;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H'¨\u0006\u0007"}, d2 = {"Lcom/ciot/networklib/api/DiagnosisApiService;", "", "upFaultInfo", "Lio/reactivex/Observable;", "Lcom/ciot/networklib/bean/diagnosis/DiagnosisReponseBean;", "body", "Lokhttp3/RequestBody;", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: DiagnosisApiService.kt */
public interface DiagnosisApiService {
    @POST("/cs-web/api/v1/cs/csRobot/upFaultInfo")
    Observable<DiagnosisReponseBean> upFaultInfo(@Body RequestBody requestBody);
}
