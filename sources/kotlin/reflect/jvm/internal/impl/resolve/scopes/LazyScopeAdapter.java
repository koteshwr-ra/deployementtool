package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;

/* compiled from: LazyScopeAdapter.kt */
public final class LazyScopeAdapter extends AbstractScopeAdapter {
    private final NotNullLazyValue<MemberScope> scope;

    public LazyScopeAdapter(NotNullLazyValue<? extends MemberScope> notNullLazyValue) {
        Intrinsics.checkParameterIsNotNull(notNullLazyValue, "scope");
        this.scope = notNullLazyValue;
    }

    /* access modifiers changed from: protected */
    public MemberScope getWorkerScope() {
        return (MemberScope) this.scope.invoke();
    }
}
