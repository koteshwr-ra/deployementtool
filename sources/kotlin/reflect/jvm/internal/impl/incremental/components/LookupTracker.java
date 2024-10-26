package kotlin.reflect.jvm.internal.impl.incremental.components;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.limpoxe.support.servicemanager.ServiceProvider;
import com.tencent.smtt.sdk.TbsReaderView;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LookupTracker.kt */
public interface LookupTracker {
    boolean getRequiresPosition();

    void record(String str, Position position, String str2, ScopeKind scopeKind, String str3);

    /* compiled from: LookupTracker.kt */
    public static final class DO_NOTHING implements LookupTracker {
        public static final DO_NOTHING INSTANCE = new DO_NOTHING();

        public boolean getRequiresPosition() {
            return false;
        }

        public void record(String str, Position position, String str2, ScopeKind scopeKind, String str3) {
            Intrinsics.checkParameterIsNotNull(str, TbsReaderView.KEY_FILE_PATH);
            Intrinsics.checkParameterIsNotNull(position, RequestParameters.POSITION);
            Intrinsics.checkParameterIsNotNull(str2, "scopeFqName");
            Intrinsics.checkParameterIsNotNull(scopeKind, "scopeKind");
            Intrinsics.checkParameterIsNotNull(str3, ServiceProvider.NAME);
        }

        private DO_NOTHING() {
        }
    }
}
