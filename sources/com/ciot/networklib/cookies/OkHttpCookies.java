package com.ciot.networklib.cookies;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Cookie;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0012¢\u0006\u0002\u0010\u0002B\u0011\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/ciot/networklib/cookies/OkHttpCookies;", "Ljava/io/Serializable;", "()V", "cookies", "Lokhttp3/Cookie;", "(Lokhttp3/Cookie;)V", "clientCookies", "getCookies", "readObject", "", "in", "Ljava/io/ObjectInputStream;", "writeObject", "out", "Ljava/io/ObjectOutputStream;", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: OkHttpCookies.kt */
public final class OkHttpCookies implements Serializable {
    private Cookie clientCookies;
    private Cookie cookies;

    private OkHttpCookies() {
    }

    public OkHttpCookies(Cookie cookie) {
        Intrinsics.checkNotNull(cookie);
        this.cookies = cookie;
    }

    public final Cookie getCookies() {
        Cookie cookie = this.cookies;
        if (cookie == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cookies");
            cookie = null;
        }
        Cookie cookie2 = this.clientCookies;
        if (cookie2 == null) {
            return cookie;
        }
        if (cookie2 != null) {
            return cookie2;
        }
        throw new NullPointerException("null cannot be cast to non-null type okhttp3.Cookie");
    }

    private final void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        Cookie cookie = this.cookies;
        Cookie cookie2 = null;
        if (cookie == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cookies");
            cookie = null;
        }
        objectOutputStream.writeObject(cookie.name());
        Cookie cookie3 = this.cookies;
        if (cookie3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cookies");
            cookie3 = null;
        }
        objectOutputStream.writeObject(cookie3.value());
        Cookie cookie4 = this.cookies;
        if (cookie4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cookies");
            cookie4 = null;
        }
        objectOutputStream.writeLong(cookie4.expiresAt());
        Cookie cookie5 = this.cookies;
        if (cookie5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cookies");
            cookie5 = null;
        }
        objectOutputStream.writeObject(cookie5.domain());
        Cookie cookie6 = this.cookies;
        if (cookie6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cookies");
            cookie6 = null;
        }
        objectOutputStream.writeObject(cookie6.path());
        Cookie cookie7 = this.cookies;
        if (cookie7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cookies");
            cookie7 = null;
        }
        objectOutputStream.writeBoolean(cookie7.secure());
        Cookie cookie8 = this.cookies;
        if (cookie8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cookies");
            cookie8 = null;
        }
        objectOutputStream.writeBoolean(cookie8.httpOnly());
        Cookie cookie9 = this.cookies;
        if (cookie9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cookies");
            cookie9 = null;
        }
        objectOutputStream.writeBoolean(cookie9.hostOnly());
        Cookie cookie10 = this.cookies;
        if (cookie10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cookies");
        } else {
            cookie2 = cookie10;
        }
        objectOutputStream.writeBoolean(cookie2.persistent());
    }

    private final void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        Object readObject = objectInputStream.readObject();
        if (readObject != null) {
            String str = (String) readObject;
            Object readObject2 = objectInputStream.readObject();
            if (readObject2 != null) {
                String str2 = (String) readObject2;
                long readLong = objectInputStream.readLong();
                Object readObject3 = objectInputStream.readObject();
                if (readObject3 != null) {
                    String str3 = (String) readObject3;
                    Object readObject4 = objectInputStream.readObject();
                    if (readObject4 != null) {
                        String str4 = (String) readObject4;
                        boolean readBoolean = objectInputStream.readBoolean();
                        boolean readBoolean2 = objectInputStream.readBoolean();
                        boolean readBoolean3 = objectInputStream.readBoolean();
                        Cookie.Builder expiresAt = new Cookie.Builder().name(str).value(str2).expiresAt(readLong);
                        Cookie.Builder path = (readBoolean3 ? expiresAt.hostOnlyDomain(str3) : expiresAt.domain(str3)).path(str4);
                        if (readBoolean) {
                            path = path.secure();
                        }
                        if (readBoolean2) {
                            path = path.httpOnly();
                        }
                        this.clientCookies = path.build();
                        return;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
    }
}
