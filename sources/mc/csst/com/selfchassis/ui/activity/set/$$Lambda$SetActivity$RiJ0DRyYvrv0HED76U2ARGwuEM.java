package mc.csst.com.selfchassis.ui.activity.set;

import mc.csst.com.selfchassis.ui.dialog.LoadingDialog2;
import mc.csst.com.selfchassislibrary.chassis.SelfChassis;

/* renamed from: mc.csst.com.selfchassis.ui.activity.set.-$$Lambda$SetActivity$RiJ0DRyYvrv0HE-D76U2ARGwuEM  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$SetActivity$RiJ0DRyYvrv0HED76U2ARGwuEM implements LoadingDialog2.OnCancelClickListener {
    public static final /* synthetic */ $$Lambda$SetActivity$RiJ0DRyYvrv0HED76U2ARGwuEM INSTANCE = new $$Lambda$SetActivity$RiJ0DRyYvrv0HED76U2ARGwuEM();

    private /* synthetic */ $$Lambda$SetActivity$RiJ0DRyYvrv0HED76U2ARGwuEM() {
    }

    public final void onCancel() {
        SelfChassis.getInstance().serviceCancelUpgradeDownload();
    }
}
