package mc.csst.com.selfchassis.ui.fragment.set.schedule;

import android.widget.CompoundButton;
import mc.csst.com.selfchassislibrary.chassis.SelfChassis;

/* renamed from: mc.csst.com.selfchassis.ui.fragment.set.schedule.-$$Lambda$ScheduleFragment$FAxhDT2KtqKDlcs37mDgtIyFp4g  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$ScheduleFragment$FAxhDT2KtqKDlcs37mDgtIyFp4g implements CompoundButton.OnCheckedChangeListener {
    public static final /* synthetic */ $$Lambda$ScheduleFragment$FAxhDT2KtqKDlcs37mDgtIyFp4g INSTANCE = new $$Lambda$ScheduleFragment$FAxhDT2KtqKDlcs37mDgtIyFp4g();

    private /* synthetic */ $$Lambda$ScheduleFragment$FAxhDT2KtqKDlcs37mDgtIyFp4g() {
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        SelfChassis.getInstance().configStationServer(2, (String) null, Boolean.valueOf(z), "switchMultiRobotMode");
    }
}
