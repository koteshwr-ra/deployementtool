package me.jessyan.retrofiturlmanager.parser;

import android.text.TextUtils;
import java.util.ArrayList;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import me.jessyan.retrofiturlmanager.cache.Cache;
import me.jessyan.retrofiturlmanager.cache.LruCache;
import okhttp3.HttpUrl;

public class DomainUrlParser implements UrlParser {
    private Cache<String, String> mCache;

    public void init(RetrofitUrlManager retrofitUrlManager) {
        this.mCache = new LruCache(100);
    }

    public HttpUrl parseUrl(HttpUrl httpUrl, HttpUrl httpUrl2) {
        if (httpUrl == null) {
            return httpUrl2;
        }
        HttpUrl.Builder newBuilder = httpUrl2.newBuilder();
        if (TextUtils.isEmpty(this.mCache.get(getKey(httpUrl, httpUrl2)))) {
            for (int i = 0; i < httpUrl2.pathSize(); i++) {
                newBuilder.removePathSegment(0);
            }
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.addAll(httpUrl.encodedPathSegments());
            arrayList.addAll(httpUrl2.encodedPathSegments());
            for (String addEncodedPathSegment : arrayList) {
                newBuilder.addEncodedPathSegment(addEncodedPathSegment);
            }
        } else {
            newBuilder.encodedPath(this.mCache.get(getKey(httpUrl, httpUrl2)));
        }
        HttpUrl build = newBuilder.scheme(httpUrl.scheme()).host(httpUrl.host()).port(httpUrl.port()).build();
        if (TextUtils.isEmpty(this.mCache.get(getKey(httpUrl, httpUrl2)))) {
            this.mCache.put(getKey(httpUrl, httpUrl2), build.encodedPath());
        }
        return build;
    }

    private String getKey(HttpUrl httpUrl, HttpUrl httpUrl2) {
        return httpUrl.encodedPath() + httpUrl2.encodedPath();
    }
}
