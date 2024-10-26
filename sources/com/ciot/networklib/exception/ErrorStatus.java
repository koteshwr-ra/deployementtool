package com.ciot.networklib.exception;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/ciot/networklib/exception/ErrorStatus;", "", "()V", "API_ERROR", "", "NETWORK_ERROR", "SERVER_ERROR", "SUCCESS", "TOKEN_INVALID", "UNKNOWN_ERROR", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: ErrorStatus.kt */
public final class ErrorStatus {
    public static final int API_ERROR = 1005;
    public static final ErrorStatus INSTANCE = new ErrorStatus();
    public static final int NETWORK_ERROR = 1004;
    public static final int SERVER_ERROR = 1003;
    public static final int SUCCESS = 0;
    public static final int TOKEN_INVALID = 401;
    public static final int UNKNOWN_ERROR = 1002;

    private ErrorStatus() {
    }
}
