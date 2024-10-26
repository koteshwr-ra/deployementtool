package me.jessyan.retrofiturlmanager;

import okhttp3.HttpUrl;

public interface onUrlChangeListener {
    void onUrlChangeBefore(HttpUrl httpUrl, String str);

    void onUrlChanged(HttpUrl httpUrl, HttpUrl httpUrl2);
}
