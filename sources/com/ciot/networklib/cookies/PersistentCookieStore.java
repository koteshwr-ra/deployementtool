package com.ciot.networklib.cookies;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import com.ciot.base.util.ContextUtil;
import com.tencent.smtt.sdk.TbsVideoCacheTask;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Cookie;
import okhttp3.HttpUrl;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000bJ\u0010\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0015\u001a\u00020\u0004H\u0002J\u0014\u0010\u0016\u001a\u0004\u0018\u00010\u00042\b\u0010\u0010\u001a\u0004\u0018\u00010\u0017H\u0002J\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00192\u0006\u0010\u000e\u001a\u00020\u000fJ\u0010\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000bH\u0002J\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0019J\u0010\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u0004H\u0002J\u0016\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000bJ\u0006\u0010 \u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R&\u0010\b\u001a\u001a\u0012\u0004\u0012\u00020\u0004\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000b0\n0\tX\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/ciot/networklib/cookies/PersistentCookieStore;", "", "()V", "COOKIE_PREFS", "", "LOG_TAG", "cookiePrefs", "Landroid/content/SharedPreferences;", "cookies", "Ljava/util/HashMap;", "Ljava/util/concurrent/ConcurrentHashMap;", "Lokhttp3/Cookie;", "add", "", "url", "Lokhttp3/HttpUrl;", "cookie", "byteArrayToHexString", "bytes", "", "decodeCookie", "cookieString", "encodeCookie", "Lcom/ciot/networklib/cookies/OkHttpCookies;", "get", "", "getCookieToken", "getCookies", "hexStringToByteArray", "hexString", "remove", "", "removeAll", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: PersistentCookieStore.kt */
public final class PersistentCookieStore {
    private final String COOKIE_PREFS = "Cookies_Prefs";
    private final String LOG_TAG = "PersistentCookieStore";
    private final SharedPreferences cookiePrefs;
    private final HashMap<String, ConcurrentHashMap<String, Cookie>> cookies = new HashMap<>();

    public PersistentCookieStore() {
        Cookie decodeCookie;
        Context context = ContextUtil.getContext();
        SharedPreferences sharedPreferences = context != null ? context.getSharedPreferences(this.COOKIE_PREFS, 0) : null;
        Intrinsics.checkNotNull(sharedPreferences);
        this.cookiePrefs = sharedPreferences;
        Map<String, ?> all = sharedPreferences.getAll();
        Intrinsics.checkNotNullExpressionValue(all, "prefsMap");
        for (Map.Entry next : all.entrySet()) {
            Object value = next.getValue();
            if (value != null) {
                String[] split = TextUtils.split((String) value, ",");
                Intrinsics.checkNotNullExpressionValue(split, "cookieNames");
                for (String str : split) {
                    String string = this.cookiePrefs.getString(str, (String) null);
                    if (!(string == null || (decodeCookie = decodeCookie(string)) == null)) {
                        if (!this.cookies.containsKey(next.getKey())) {
                            this.cookies.put(next.getKey(), new ConcurrentHashMap());
                        }
                        ConcurrentHashMap concurrentHashMap = this.cookies.get(next.getKey());
                        if (concurrentHashMap != null) {
                            Cookie cookie = (Cookie) concurrentHashMap.put(str, decodeCookie);
                        }
                    }
                }
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        }
    }

    private final String getCookieToken(Cookie cookie) {
        return cookie.name() + '@' + cookie.domain();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0082, code lost:
        r6 = r6.entrySet();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void add(okhttp3.HttpUrl r6, okhttp3.Cookie r7) {
        /*
            r5 = this;
            java.lang.String r0 = "url"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.lang.String r0 = "cookie"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.lang.String r0 = r5.getCookieToken(r7)
            boolean r1 = r7.persistent()
            if (r1 != 0) goto L_0x0045
            java.util.HashMap<java.lang.String, java.util.concurrent.ConcurrentHashMap<java.lang.String, okhttp3.Cookie>> r1 = r5.cookies
            java.lang.String r2 = r6.host()
            boolean r1 = r1.containsKey(r2)
            if (r1 != 0) goto L_0x0030
            java.util.HashMap<java.lang.String, java.util.concurrent.ConcurrentHashMap<java.lang.String, okhttp3.Cookie>> r1 = r5.cookies
            java.lang.String r2 = r6.host()
            java.util.concurrent.ConcurrentHashMap r3 = new java.util.concurrent.ConcurrentHashMap
            r4 = 10
            r3.<init>(r4)
            r1.put(r2, r3)
        L_0x0030:
            java.util.HashMap<java.lang.String, java.util.concurrent.ConcurrentHashMap<java.lang.String, okhttp3.Cookie>> r1 = r5.cookies
            java.lang.String r2 = r6.host()
            java.lang.Object r1 = r1.get(r2)
            java.util.concurrent.ConcurrentHashMap r1 = (java.util.concurrent.ConcurrentHashMap) r1
            if (r1 == 0) goto L_0x0065
            java.lang.Object r1 = r1.put(r0, r7)
            okhttp3.Cookie r1 = (okhttp3.Cookie) r1
            goto L_0x0065
        L_0x0045:
            java.util.HashMap<java.lang.String, java.util.concurrent.ConcurrentHashMap<java.lang.String, okhttp3.Cookie>> r1 = r5.cookies
            java.lang.String r2 = r6.host()
            boolean r1 = r1.containsKey(r2)
            if (r1 == 0) goto L_0x0065
            java.util.HashMap<java.lang.String, java.util.concurrent.ConcurrentHashMap<java.lang.String, okhttp3.Cookie>> r1 = r5.cookies
            java.lang.String r2 = r6.host()
            java.lang.Object r1 = r1.get(r2)
            java.util.concurrent.ConcurrentHashMap r1 = (java.util.concurrent.ConcurrentHashMap) r1
            if (r1 == 0) goto L_0x0065
            java.lang.Object r1 = r1.remove(r0)
            okhttp3.Cookie r1 = (okhttp3.Cookie) r1
        L_0x0065:
            android.content.SharedPreferences r1 = r5.cookiePrefs
            android.content.SharedPreferences$Editor r1 = r1.edit()
            java.lang.String r2 = "cookiePrefs.edit()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            java.lang.String r2 = r6.host()
            java.util.HashMap<java.lang.String, java.util.concurrent.ConcurrentHashMap<java.lang.String, okhttp3.Cookie>> r3 = r5.cookies
            java.lang.String r6 = r6.host()
            java.lang.Object r6 = r3.get(r6)
            java.util.concurrent.ConcurrentHashMap r6 = (java.util.concurrent.ConcurrentHashMap) r6
            if (r6 == 0) goto L_0x0093
            java.util.Set r6 = r6.entrySet()
            if (r6 == 0) goto L_0x0093
            java.lang.String r3 = ","
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.lang.String r6 = android.text.TextUtils.join(r3, r6)
            goto L_0x0094
        L_0x0093:
            r6 = 0
        L_0x0094:
            r1.putString(r2, r6)
            com.ciot.networklib.cookies.OkHttpCookies r6 = new com.ciot.networklib.cookies.OkHttpCookies
            r6.<init>(r7)
            java.lang.String r6 = r5.encodeCookie(r6)
            r1.putString(r0, r6)
            r1.apply()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ciot.networklib.cookies.PersistentCookieStore.add(okhttp3.HttpUrl, okhttp3.Cookie):void");
    }

    public final List<Cookie> get(HttpUrl httpUrl) {
        Intrinsics.checkNotNullParameter(httpUrl, TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_URL);
        ArrayList arrayList = new ArrayList();
        if (this.cookies.containsKey(httpUrl.host())) {
            ConcurrentHashMap concurrentHashMap = this.cookies.get(httpUrl.host());
            Collection values = concurrentHashMap != null ? concurrentHashMap.values() : null;
            Intrinsics.checkNotNull(values);
            arrayList.addAll(values);
        }
        return arrayList;
    }

    public final void removeAll() {
        SharedPreferences.Editor edit = this.cookiePrefs.edit();
        edit.clear();
        edit.apply();
        for (String remove : this.cookies.keySet()) {
            this.cookies.remove(remove);
        }
    }

    public final boolean remove(HttpUrl httpUrl, Cookie cookie) {
        Set keySet;
        Intrinsics.checkNotNullParameter(httpUrl, TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_URL);
        Intrinsics.checkNotNullParameter(cookie, "cookie");
        String cookieToken = getCookieToken(cookie);
        if (this.cookies.containsKey(httpUrl.host())) {
            ConcurrentHashMap concurrentHashMap = this.cookies.get(httpUrl.host());
            String str = null;
            Boolean valueOf = concurrentHashMap != null ? Boolean.valueOf(concurrentHashMap.containsKey(cookieToken)) : null;
            Intrinsics.checkNotNull(valueOf);
            if (valueOf.booleanValue()) {
                ConcurrentHashMap concurrentHashMap2 = this.cookies.get(httpUrl.host());
                if (concurrentHashMap2 != null) {
                    Cookie cookie2 = (Cookie) concurrentHashMap2.remove(cookieToken);
                }
                SharedPreferences.Editor edit = this.cookiePrefs.edit();
                if (this.cookiePrefs.contains(cookieToken)) {
                    edit.remove(cookieToken);
                }
                String host = httpUrl.host();
                ConcurrentHashMap concurrentHashMap3 = this.cookies.get(httpUrl.host());
                if (!(concurrentHashMap3 == null || (keySet = concurrentHashMap3.keySet()) == null)) {
                    str = TextUtils.join(",", keySet);
                }
                edit.putString(host, str);
                edit.apply();
                return true;
            }
        }
        return false;
    }

    public final List<Cookie> getCookies() {
        ArrayList arrayList = new ArrayList();
        for (String str : this.cookies.keySet()) {
            ConcurrentHashMap concurrentHashMap = this.cookies.get(str);
            Collection values = concurrentHashMap != null ? concurrentHashMap.values() : null;
            Intrinsics.checkNotNull(values);
            arrayList.addAll(values);
        }
        return arrayList;
    }

    private final String encodeCookie(OkHttpCookies okHttpCookies) {
        if (okHttpCookies == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new ObjectOutputStream(byteArrayOutputStream).writeObject(okHttpCookies);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            Intrinsics.checkNotNullExpressionValue(byteArray, "os.toByteArray()");
            return byteArrayToHexString(byteArray);
        } catch (IOException e) {
            Log.d(this.LOG_TAG, "IOException in encodeCookie", e);
            return null;
        }
    }

    private final Cookie decodeCookie(String str) {
        try {
            Object readObject = new ObjectInputStream(new ByteArrayInputStream(hexStringToByteArray(str))).readObject();
            if (readObject != null) {
                return ((OkHttpCookies) readObject).getCookies();
            }
            throw new NullPointerException("null cannot be cast to non-null type com.ciot.networklib.cookies.OkHttpCookies");
        } catch (IOException e) {
            Log.d(this.LOG_TAG, "IOException in decodeCookie", e);
            return null;
        } catch (ClassNotFoundException e2) {
            Log.d(this.LOG_TAG, "ClassNotFoundException in decodeCookie", e2);
            return null;
        }
    }

    private final String byteArrayToHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            byte b2 = b & 255;
            if (b2 < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(b2));
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "sb.toString()");
        Locale locale = Locale.US;
        Intrinsics.checkNotNullExpressionValue(locale, "US");
        String upperCase = sb2.toUpperCase(locale);
        Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(locale)");
        return upperCase;
    }

    private final byte[] hexStringToByteArray(String str) {
        int length = str.length();
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }
}
