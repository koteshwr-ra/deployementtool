package io.realm.internal.android;

import android.os.Looper;
import io.realm.internal.Capabilities;
import javax.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;

public class AndroidCapabilities implements Capabilities {
    public static boolean EMULATE_MAIN_THREAD = false;
    private final boolean isIntentServiceThread = isIntentServiceThread();
    private final Looper looper = Looper.myLooper();

    public boolean canDeliverNotification() {
        return hasLooper() && !this.isIntentServiceThread;
    }

    public void checkCanDeliverNotification(@Nullable String str) {
        String str2 = "";
        if (!hasLooper()) {
            if (str != null) {
                str2 = str + StringUtils.SPACE + "Realm cannot be automatically updated on a thread without a looper.";
            }
            throw new IllegalStateException(str2);
        } else if (this.isIntentServiceThread) {
            if (str != null) {
                str2 = str + StringUtils.SPACE + "Realm cannot be automatically updated on an IntentService thread.";
            }
            throw new IllegalStateException(str2);
        }
    }

    public boolean isMainThread() {
        Looper looper2 = this.looper;
        return looper2 != null && (EMULATE_MAIN_THREAD || looper2 == Looper.getMainLooper());
    }

    private boolean hasLooper() {
        return this.looper != null;
    }

    private static boolean isIntentServiceThread() {
        String name = Thread.currentThread().getName();
        return name != null && name.startsWith("IntentService[");
    }
}
