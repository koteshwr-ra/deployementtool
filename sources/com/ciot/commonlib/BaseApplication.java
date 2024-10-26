package com.ciot.commonlib;

import android.app.Application;
import android.content.Context;
import androidx.multidex.MultiDexApplication;
import com.alibaba.android.arouter.launcher.ARouter;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014¨\u0006\b"}, d2 = {"Lcom/ciot/commonlib/BaseApplication;", "Landroidx/multidex/MultiDexApplication;", "()V", "attachBaseContext", "", "base", "Landroid/content/Context;", "Companion", "library-commonlib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: BaseApplication.kt */
public class BaseApplication extends MultiDexApplication {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static Application instance;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/ciot/commonlib/BaseApplication$Companion;", "", "()V", "instance", "Landroid/app/Application;", "getInstance", "()Landroid/app/Application;", "setInstance", "(Landroid/app/Application;)V", "library-commonlib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: BaseApplication.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Application getInstance() {
            Application application = BaseApplication.instance;
            if (application != null) {
                return application;
            }
            Intrinsics.throwUninitializedPropertyAccessException("instance");
            return null;
        }

        public final void setInstance(Application application) {
            Intrinsics.checkNotNullParameter(application, "<set-?>");
            BaseApplication.instance = application;
        }
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        Application application = this;
        Companion.setInstance(application);
        ARouter.init(application);
    }
}
