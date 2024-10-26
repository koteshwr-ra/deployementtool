package com.ciot.networklib;

import com.alibaba.fastjson.JSON;
import com.ciot.networklib.bean.ProjectResponse;
import com.ciot.realm.db.common.CompanyResponse;
import com.ciot.realm.db.common.RecordResponse;
import io.reactivex.functions.Function;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.ResponseBody;

@Metadata(d1 = {"\u0000\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0005\u001a\u00020\u0002H\u0016¨\u0006\u0006"}, d2 = {"com/ciot/networklib/RetrofitManager$verifyInviteCode$1", "Lio/reactivex/functions/Function;", "Lokhttp3/ResponseBody;", "Lcom/ciot/realm/db/common/RecordResponse;", "apply", "body", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: RetrofitManager.kt */
public final class RetrofitManager$verifyInviteCode$1 implements Function<ResponseBody, RecordResponse> {
    RetrofitManager$verifyInviteCode$1() {
    }

    public RecordResponse apply(ResponseBody responseBody) {
        Intrinsics.checkNotNullParameter(responseBody, "body");
        String string = responseBody.string();
        RecordResponse recordResponse = (RecordResponse) JSON.parseObject(string, RecordResponse.class);
        if ((string.length() == 0) || Intrinsics.areEqual((Object) string, (Object) "{}")) {
            return null;
        }
        ProjectResponse info = CurrentProjectInfo.Companion.getInstance().getInfo();
        CompanyResponse company = recordResponse.getCompany();
        if (!Intrinsics.areEqual((Object) company != null ? company.getId() : null, (Object) info != null ? info.getCompany() : null)) {
            return null;
        }
        return recordResponse;
    }
}
