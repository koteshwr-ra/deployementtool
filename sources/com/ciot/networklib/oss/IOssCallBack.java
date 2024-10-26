package com.ciot.networklib.oss;

public interface IOssCallBack {
    void failure(String str);

    void progress(int i);

    void success(String str);
}
