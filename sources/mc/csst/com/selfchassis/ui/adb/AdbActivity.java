package mc.csst.com.selfchassis.ui.adb;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.ciot.sentrymove.R;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014¨\u0006\u0007"}, d2 = {"Lmc/csst/com/selfchassis/ui/adb/AdbActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "deploymenttool_deploymenttoolRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: AdbActivity.kt */
public final class AdbActivity extends AppCompatActivity {
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        if (findViewById == null) {
            return null;
        }
        map.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_adb);
        ((Button) _$_findCachedViewById(mc.csst.com.selfchassis.R.id.btnRestartAdb)).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                AdbActivity.m1573onCreate$lambda2(AdbActivity.this, view);
            }
        });
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-2  reason: not valid java name */
    public static final void m1573onCreate$lambda2(AdbActivity adbActivity, View view) {
        Intrinsics.checkNotNullParameter(adbActivity, "this$0");
        new Thread(new Runnable(Integer.parseInt(StringsKt.trim((CharSequence) ((EditText) adbActivity._$_findCachedViewById(mc.csst.com.selfchassis.R.id.etPort)).getText().toString()).toString()), adbActivity) {
            public final /* synthetic */ int f$0;
            public final /* synthetic */ AdbActivity f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            public final void run() {
                AdbActivity.m1574onCreate$lambda2$lambda1(this.f$0, this.f$1);
            }
        }).start();
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-2$lambda-1  reason: not valid java name */
    public static final void m1574onCreate$lambda2$lambda1(int i, AdbActivity adbActivity) {
        Intrinsics.checkNotNullParameter(adbActivity, "this$0");
        if (i < 5555 || i > 5585) {
            ((TextView) adbActivity._$_findCachedViewById(mc.csst.com.selfchassis.R.id.tvTip)).setText("请输入5555到5585之间端口号");
            return;
        }
        Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        booleanRef.element = ShellUtils.RootCmd("setprop service.adb.tcp.port " + i);
        booleanRef.element = booleanRef.element & ShellUtils.RootCmd("stop adbd");
        booleanRef.element = booleanRef.element & ShellUtils.RootCmd("start adbd");
        adbActivity.runOnUiThread(new Runnable(i, adbActivity) {
            public final /* synthetic */ int f$1;
            public final /* synthetic */ AdbActivity f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                AdbActivity.m1575onCreate$lambda2$lambda1$lambda0(Ref.BooleanRef.this, this.f$1, this.f$2);
            }
        });
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-2$lambda-1$lambda-0  reason: not valid java name */
    public static final void m1575onCreate$lambda2$lambda1$lambda0(Ref.BooleanRef booleanRef, int i, AdbActivity adbActivity) {
        String str;
        Intrinsics.checkNotNullParameter(booleanRef, "$isSuccess");
        Intrinsics.checkNotNullParameter(adbActivity, "this$0");
        String str2 = "";
        for (String next : ShellUtils.getIp()) {
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            if (booleanRef.element) {
                str = "电脑输入adb connect " + next + ':' + i;
            } else {
                str = "执行指令失败";
            }
            sb.append(str);
            str2 = sb.toString();
        }
        ((TextView) adbActivity._$_findCachedViewById(mc.csst.com.selfchassis.R.id.tvTip)).setText(str2);
    }
}
