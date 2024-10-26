package mc.csst.com.selfchassis.ui.activity.main;

import android.view.View;
import mc.csst.com.selfchassis.ui.dialog.ConfirmDialog;
import mc.csst.com.selfchassis.utils.SoftTypeInfoManager;

/* renamed from: mc.csst.com.selfchassis.ui.activity.main.-$$Lambda$MainPresenter$qjNnAcs4IAlnllJivVt9PaxfaKg  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$MainPresenter$qjNnAcs4IAlnllJivVt9PaxfaKg implements ConfirmDialog.OnDialogButtonClickListener {
    public static final /* synthetic */ $$Lambda$MainPresenter$qjNnAcs4IAlnllJivVt9PaxfaKg INSTANCE = new $$Lambda$MainPresenter$qjNnAcs4IAlnllJivVt9PaxfaKg();

    private /* synthetic */ $$Lambda$MainPresenter$qjNnAcs4IAlnllJivVt9PaxfaKg() {
    }

    public final boolean onClick(View view) {
        return SoftTypeInfoManager.getInstance().setSoftType(0);
    }
}
