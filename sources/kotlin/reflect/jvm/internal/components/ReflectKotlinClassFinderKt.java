package kotlin.reflect.jvm.internal.components;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.ClassUtils;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002¨\u0006\u0003"}, d2 = {"toRuntimeFqName", "", "Lkotlin/reflect/jvm/internal/impl/name/ClassId;", "descriptors.runtime"}, k = 2, mv = {1, 1, 15})
/* compiled from: ReflectKotlinClassFinder.kt */
public final class ReflectKotlinClassFinderKt {
    /* access modifiers changed from: private */
    public static final String toRuntimeFqName(ClassId classId) {
        String asString = classId.getRelativeClassName().asString();
        Intrinsics.checkExpressionValueIsNotNull(asString, "relativeClassName.asString()");
        String replace$default = StringsKt.replace$default(asString, (char) ClassUtils.PACKAGE_SEPARATOR_CHAR, '$', false, 4, (Object) null);
        FqName packageFqName = classId.getPackageFqName();
        Intrinsics.checkExpressionValueIsNotNull(packageFqName, "packageFqName");
        if (packageFqName.isRoot()) {
            return replace$default;
        }
        return classId.getPackageFqName() + ClassUtils.PACKAGE_SEPARATOR_CHAR + replace$default;
    }
}
