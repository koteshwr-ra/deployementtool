package me.jessyan.retrofiturlmanager.parser;

import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import okhttp3.HttpUrl;

public interface UrlParser {
    void init(RetrofitUrlManager retrofitUrlManager);

    HttpUrl parseUrl(HttpUrl httpUrl, HttpUrl httpUrl2);
}
