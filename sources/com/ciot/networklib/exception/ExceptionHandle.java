package com.ciot.networklib.exception;

import com.ciot.base.util.MyLogUtils;
import com.google.gson.JsonParseException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import retrofit2.HttpException;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/ciot/networklib/exception/ExceptionHandle;", "", "()V", "Companion", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: ExceptionHandle.kt */
public final class ExceptionHandle {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "ExceptionHandle";
    /* access modifiers changed from: private */
    public static int errorCode = 1002;
    /* access modifiers changed from: private */
    public static String errorMsg = "不好意思，我忽然反应不过来了";

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u0003\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0012R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0013"}, d2 = {"Lcom/ciot/networklib/exception/ExceptionHandle$Companion;", "", "()V", "TAG", "", "errorCode", "", "getErrorCode", "()I", "setErrorCode", "(I)V", "errorMsg", "getErrorMsg", "()Ljava/lang/String;", "setErrorMsg", "(Ljava/lang/String;)V", "handleException", "e", "", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: ExceptionHandle.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int getErrorCode() {
            return ExceptionHandle.errorCode;
        }

        public final void setErrorCode(int i) {
            ExceptionHandle.errorCode = i;
        }

        public final String getErrorMsg() {
            return ExceptionHandle.errorMsg;
        }

        public final void setErrorMsg(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            ExceptionHandle.errorMsg = str;
        }

        public final String handleException(Throwable th) {
            Intrinsics.checkNotNullParameter(th, "e");
            th.printStackTrace();
            if ((th instanceof SocketTimeoutException) || (th instanceof ConnectException) || (th instanceof HttpException)) {
                MyLogUtils.Loge(ExceptionHandle.TAG, "网络连接异常: " + th.getMessage());
                setErrorMsg("网络连接异常");
                setErrorCode(1004);
            } else if ((th instanceof JsonParseException) || (th instanceof JSONException) || (th instanceof ParseException)) {
                MyLogUtils.Loge(ExceptionHandle.TAG, "数据解析异常: " + th.getMessage());
                setErrorMsg("数据解析异常");
                setErrorCode(1003);
            } else if (th instanceof ApiException) {
                setErrorMsg(String.valueOf(th.getMessage()));
                setErrorCode(1003);
            } else if (th instanceof UnknownHostException) {
                MyLogUtils.Loge(ExceptionHandle.TAG, "网络连接异常: " + th.getMessage());
                setErrorMsg("网络连接异常");
                setErrorCode(1004);
            } else if (th instanceof IllegalArgumentException) {
                setErrorMsg("参数错误");
                setErrorCode(1003);
            } else {
                try {
                    MyLogUtils.Loge(ExceptionHandle.TAG, "错误: " + th.getMessage());
                } catch (Exception unused) {
                    MyLogUtils.Loge(ExceptionHandle.TAG, "未知错误Debug调试 ");
                }
                setErrorMsg("未知错误，可能抛锚了吧~");
                setErrorCode(1002);
            }
            return getErrorMsg();
        }
    }
}
