package com.ciot.networklib.exception;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0017\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u000f\b\u0016\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nR\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0004\n\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/ciot/networklib/exception/ApiException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "throwable", "", "code", "", "(Ljava/lang/Throwable;I)V", "message", "", "(Ljava/lang/String;)V", "Ljava/lang/Integer;", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: ApiException.kt */
public final class ApiException extends RuntimeException {
    private Integer code;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ApiException(Throwable th, int i) {
        super(th);
        Intrinsics.checkNotNullParameter(th, "throwable");
        this.code = Integer.valueOf(i);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ApiException(String str) {
        super(new Throwable(str));
        Intrinsics.checkNotNullParameter(str, "message");
    }
}
