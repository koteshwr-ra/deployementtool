package com.ciot.networklib;

public interface DownloadCallBack {
    void onCompleted();

    void onError(String str);

    void onProgress(int i);
}
