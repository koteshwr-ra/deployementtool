package com.ciot.networklib;

public interface DownloadApkCallBack {
    void onDownloadApkComplete(String str, String str2, boolean z);

    void onDownloadApkProgress(int i);

    void onDownloadFail(String str, String str2);
}
