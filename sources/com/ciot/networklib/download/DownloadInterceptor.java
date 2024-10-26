package com.ciot.networklib.download;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Response;

public class DownloadInterceptor implements Interceptor {
    private final DownloadHandler mDownloadHandler;

    public DownloadInterceptor(DownloadFileListener downloadFileListener) {
        DownloadHandler downloadHandler = new DownloadHandler();
        this.mDownloadHandler = downloadHandler;
        downloadHandler.setDownloadListener(downloadFileListener);
    }

    public Response intercept(Interceptor.Chain chain) throws IOException {
        Response proceed = chain.proceed(chain.request());
        return proceed.newBuilder().body(new DownloadResponseBody(proceed.body(), this.mDownloadHandler)).build();
    }
}
