package com.ciot.networklib.download;

public interface DownloadFileListener {
    void onFail(String str);

    void onProgress(float f);

    void onStartDownload(long j);
}
