package kotlin.reflect.jvm.internal.impl.name;

import com.limpoxe.support.servicemanager.ServiceProvider;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

/* compiled from: NameUtils.kt */
public final class NameUtils {
    public static final NameUtils INSTANCE = new NameUtils();
    private static final Regex SANITIZE_AS_JAVA_INVALID_CHARACTERS = new Regex("[^\\p{L}\\p{Digit}]");

    private NameUtils() {
    }

    @JvmStatic
    public static final String sanitizeAsJavaIdentifier(String str) {
        Intrinsics.checkParameterIsNotNull(str, ServiceProvider.NAME);
        return SANITIZE_AS_JAVA_INVALID_CHARACTERS.replace((CharSequence) str, "_");
    }
}
