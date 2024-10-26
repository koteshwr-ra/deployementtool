package mc.csst.com.selfchassis.utils;

import android.content.Context;
import android.widget.Toast;
import com.blankj.utilcode.util.ThreadUtils;
import java.lang.ref.SoftReference;

public class MyToastUtils {
    private static SoftReference<Toast> mToast;

    public static void showShort(Context context, CharSequence charSequence) {
        show(context, charSequence, 0);
    }

    public static void showLong(Context context, CharSequence charSequence) {
        show(context, charSequence, 1);
    }

    private static void show(Context context, CharSequence charSequence, int i) {
        ThreadUtils.runOnUiThread(new Runnable(context, charSequence, i) {
            public final /* synthetic */ Context f$0;
            public final /* synthetic */ CharSequence f$1;
            public final /* synthetic */ int f$2;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                MyToastUtils.lambda$show$0(this.f$0, this.f$1, this.f$2);
            }
        });
    }

    static /* synthetic */ void lambda$show$0(Context context, CharSequence charSequence, int i) {
        SoftReference<Toast> softReference = mToast;
        if (!(softReference == null || softReference.get() == null)) {
            mToast.get().cancel();
        }
        Toast makeText = Toast.makeText(context, charSequence, i);
        makeText.show();
        mToast = new SoftReference<>(makeText);
    }
}
