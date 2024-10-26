package com.ciot.networklib.api;

import io.reactivex.Observable;
import kotlin.Metadata;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0001\u0010\u0007\u001a\u00020\bH'J2\u0010\t\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0001\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\n\u001a\u00020\bH'J*\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u0006H'¨\u0006\r"}, d2 = {"Lcom/ciot/networklib/api/DownloadService;", "", "download", "Lio/reactivex/Observable;", "Lokhttp3/ResponseBody;", "url", "", "offset", "", "downloadFile", "length", "getFileInfo", "peek", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: DownloadService.kt */
public interface DownloadService {
    @Streaming
    @GET
    Observable<ResponseBody> download(@Url String str, @Query("offset") long j);

    @Streaming
    @GET
    Observable<ResponseBody> downloadFile(@Url String str, @Query("offset") long j, @Query("length") long j2);

    @GET
    Observable<ResponseBody> getFileInfo(@Url String str, @Query("peek") String str2);
}
