package com.ciot.networklib.cookies;

import com.tencent.smtt.sdk.TbsVideoCacheTask;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000eJ\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u000e2\u0006\u0010\t\u001a\u00020\nH\u0016J\u001e\u0010\u0010\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\f0\u000eH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/ciot/networklib/cookies/CookieManager;", "Lokhttp3/CookieJar;", "()V", "COOKIE_STORE", "Lcom/ciot/networklib/cookies/PersistentCookieStore;", "clearAllCookies", "", "clearCookies", "", "url", "Lokhttp3/HttpUrl;", "cookie", "Lokhttp3/Cookie;", "getCookies", "", "loadForRequest", "saveFromResponse", "cookies", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: CookieManager.kt */
public final class CookieManager implements CookieJar {
    private final PersistentCookieStore COOKIE_STORE = new PersistentCookieStore();

    public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
        Intrinsics.checkNotNullParameter(httpUrl, TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_URL);
        Intrinsics.checkNotNullParameter(list, "cookies");
        if (list.size() > 0) {
            for (Cookie add : list) {
                this.COOKIE_STORE.add(httpUrl, add);
            }
        }
    }

    public List<Cookie> loadForRequest(HttpUrl httpUrl) {
        Intrinsics.checkNotNullParameter(httpUrl, TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_URL);
        return this.COOKIE_STORE.get(httpUrl);
    }

    public final void clearAllCookies() {
        this.COOKIE_STORE.removeAll();
    }

    public final boolean clearCookies(HttpUrl httpUrl, Cookie cookie) {
        Intrinsics.checkNotNullParameter(httpUrl, TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_URL);
        Intrinsics.checkNotNullParameter(cookie, "cookie");
        return this.COOKIE_STORE.remove(httpUrl, cookie);
    }

    public final List<Cookie> getCookies() {
        return this.COOKIE_STORE.getCookies();
    }
}
