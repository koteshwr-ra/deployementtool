package kotlin.reflect.jvm.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.KPackageImpl;
import kotlin.reflect.jvm.internal.components.ReflectKotlinClass;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lkotlin/reflect/jvm/internal/impl/resolve/scopes/MemberScope;", "invoke"}, k = 3, mv = {1, 1, 15})
/* compiled from: KPackageImpl.kt */
final class KPackageImpl$Data$scope$2 extends Lambda implements Function0<MemberScope> {
    final /* synthetic */ KPackageImpl.Data this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    KPackageImpl$Data$scope$2(KPackageImpl.Data data) {
        super(0);
        this.this$0 = data;
    }

    public final MemberScope invoke() {
        ReflectKotlinClass access$getKotlinClass$p = this.this$0.getKotlinClass();
        if (access$getKotlinClass$p != null) {
            return this.this$0.getModuleData().getPackagePartScopeCache().getPackagePartScope(access$getKotlinClass$p);
        }
        return MemberScope.Empty.INSTANCE;
    }
}
