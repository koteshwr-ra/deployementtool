package com.ciot.networklib;

import android.util.Log;
import com.blankj.utilcode.util.GsonUtils;
import com.ciot.realm.db.Tactics;
import com.ciot.realm.util.SentryRealmHelper;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0016J\u0016\u0010\u000b\u001a\u00020\u00072\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"com/ciot/networklib/RetrofitManager$getTactics$1", "Lcom/ciot/networklib/BaseObserver;", "", "Lcom/ciot/realm/db/Tactics;", "mTactics", "", "onComplete", "", "onError", "e", "", "onSuccess", "response", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: RetrofitManager.kt */
public final class RetrofitManager$getTactics$1 extends BaseObserver<List<? extends Tactics>> {
    private final List<Tactics> mTactics = new ArrayList();

    RetrofitManager$getTactics$1() {
    }

    public void onSuccess(List<? extends Tactics> list) {
        Intrinsics.checkNotNullParameter(list, "response");
        this.mTactics.addAll(list);
    }

    public void onComplete() {
        Log.d(RetrofitManager.CLOCK_IN_HELPER_TAG, GsonUtils.toJson(this.mTactics));
        SentryRealmHelper instance = SentryRealmHelper.getInstance();
        instance.deleteTactics();
        instance.insertTactic(this.mTactics);
    }

    public void onError(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "e");
        Log.e(RetrofitManager.CLOCK_IN_HELPER_TAG, th.getMessage());
    }
}
