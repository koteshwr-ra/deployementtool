package me.jessyan.retrofiturlmanager;

import okhttp3.HttpUrl;

class Utils {
    private Utils() {
        throw new IllegalStateException("do not instantiation me");
    }

    static HttpUrl checkUrl(String str) {
        HttpUrl parse = HttpUrl.parse(str);
        if (parse != null) {
            return parse;
        }
        throw new InvalidUrlException(str);
    }

    static <T> T checkNotNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }
}
