package com.ciot.networklib.oss;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import java.io.File;

/* renamed from: com.ciot.networklib.oss.-$$Lambda$g04I4HugH9LNTCziDkyOXv3XPLI  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$g04I4HugH9LNTCziDkyOXv3XPLI implements Function {
    public static final /* synthetic */ $$Lambda$g04I4HugH9LNTCziDkyOXv3XPLI INSTANCE = new $$Lambda$g04I4HugH9LNTCziDkyOXv3XPLI();

    private /* synthetic */ $$Lambda$g04I4HugH9LNTCziDkyOXv3XPLI() {
    }

    public final Object apply(Object obj) {
        return Observable.fromArray((File[]) obj);
    }
}
