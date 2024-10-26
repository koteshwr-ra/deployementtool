package com.ciot.networklib.oss;

import com.alibaba.sdk.android.oss.common.OSSConstants;
import com.ciot.base.util.LogPlus;
import io.reactivex.functions.Consumer;

/* renamed from: com.ciot.networklib.oss.-$$Lambda$OssHelper$W_1odzdcPIDZL-zOOaO8qn3UBAQ  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$OssHelper$W_1odzdcPIDZLzOOaO8qn3UBAQ implements Consumer {
    public static final /* synthetic */ $$Lambda$OssHelper$W_1odzdcPIDZLzOOaO8qn3UBAQ INSTANCE = new $$Lambda$OssHelper$W_1odzdcPIDZLzOOaO8qn3UBAQ();

    private /* synthetic */ $$Lambda$OssHelper$W_1odzdcPIDZLzOOaO8qn3UBAQ() {
    }

    public final void accept(Object obj) {
        LogPlus.w((String) OSSConstants.RESOURCE_NAME_OSS, ((Throwable) obj).getMessage());
    }
}
