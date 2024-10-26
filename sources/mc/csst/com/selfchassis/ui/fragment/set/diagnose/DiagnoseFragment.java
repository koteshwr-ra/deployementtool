package mc.csst.com.selfchassis.ui.fragment.set.diagnose;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import com.blankj.utilcode.util.ThreadUtils;
import com.ciot.base.util.ContextUtil;
import com.ciot.base.util.MyLogUtils;
import com.ciot.sentrymove.R;
import mc.csst.com.selfchassis.databinding.FragmentSetDiagnoseBinding;
import mc.csst.com.selfchassis.ui.dialog.SelfCheckDialog;
import mc.csst.com.selfchassis.ui.fragment.base.BaseFragment;
import mc.csst.com.selfchassis.utils.MyToastUtils;
import mc.csst.com.selfchassislibrary.bean.msg.VersionUpgradeResponseBean;
import mc.csst.com.selfchassislibrary.chassis.SelfChassis;
import mc.csst.com.selfchassislibrary.content.ServiceContent;
import mc.csst.com.selfchassislibrary.utils.SelfChassisListenerUtils;
import mc.csst.com.selfchassislibrary.utils.eventbus.SelfChassisEventMsg;

public class DiagnoseFragment extends BaseFragment implements View.OnClickListener, SelfChassisListenerUtils.OnBaseSelfChassisListener {
    private static final String TAG = DiagnoseFragment.class.getSimpleName();
    FragmentSetDiagnoseBinding mBinding;
    private SelfCheckDialog mSelfCheckDialog;

    /* access modifiers changed from: protected */
    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentSetDiagnoseBinding fragmentSetDiagnoseBinding = (FragmentSetDiagnoseBinding) DataBindingUtil.inflate(layoutInflater, R.layout.fragment_set_diagnose, viewGroup, false);
        this.mBinding = fragmentSetDiagnoseBinding;
        return fragmentSetDiagnoseBinding.getRoot();
    }

    /* access modifiers changed from: protected */
    public void initData() {
        SelfChassis.getInstance().addVersionUpgradeListener(this);
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
        this.mBinding.btnStartDetection.setOnClickListener(this);
        this.mBinding.btnClearLog.setOnClickListener(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_start_detection) {
            startDetection();
        } else if (id == R.id.btn_clear_log) {
            clearLog();
        }
    }

    private void clearLog() {
        SelfChassis.getInstance().serviceVersionUpgrade(51);
    }

    private void startDetection() {
        if (this.mSelfCheckDialog == null) {
            this.mSelfCheckDialog = SelfCheckDialog.newInstance();
        }
        if (!this.mSelfCheckDialog.isAdded()) {
            this.mSelfCheckDialog.showAllowingStateLoss(getChildFragmentManager());
        }
    }

    public void onSelfChassisMsg(SelfChassisEventMsg selfChassisEventMsg) {
        ThreadUtils.runOnUiThread(new Runnable(selfChassisEventMsg) {
            public final /* synthetic */ SelfChassisEventMsg f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                DiagnoseFragment.this.lambda$onSelfChassisMsg$0$DiagnoseFragment(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$onSelfChassisMsg$0$DiagnoseFragment(SelfChassisEventMsg selfChassisEventMsg) {
        if (ServiceContent.VERSION_UPGRADE.equals(selfChassisEventMsg.getCode())) {
            onVersionUpgrade((VersionUpgradeResponseBean) selfChassisEventMsg.getData());
        }
    }

    private void onVersionUpgrade(VersionUpgradeResponseBean versionUpgradeResponseBean) {
        if (versionUpgradeResponseBean == null) {
            MyToastUtils.showShort(ContextUtil.getContext(), getString(R.string.clear_log_fail));
            MyLogUtils.Logd(TAG, "onVersionUpgrade data is null!");
        } else if (!versionUpgradeResponseBean.isResult()) {
            MyToastUtils.showShort(ContextUtil.getContext(), getString(R.string.clear_log_fail));
            MyLogUtils.Logd(TAG, "onVersionUpgrade data.isResult is false!");
        } else {
            String str = TAG;
            MyLogUtils.Logd(str, "onVersionUpgrade data :" + versionUpgradeResponseBean.toString());
            int result = versionUpgradeResponseBean.getValues().getResult();
            int parseInt = Integer.parseInt(versionUpgradeResponseBean.getId());
            if (result == 0) {
                if (parseInt == 51) {
                    MyToastUtils.showShort(ContextUtil.getContext(), getString(R.string.clear_log_success));
                    MyLogUtils.Logd(TAG, "日志清除成功");
                }
            } else if (parseInt == 51) {
                MyToastUtils.showShort(ContextUtil.getContext(), getString(R.string.clear_log_fail));
                String str2 = TAG;
                MyLogUtils.Logd(str2, "日志清除失败 service：" + versionUpgradeResponseBean.getService() + " id:" + versionUpgradeResponseBean.getId() + " result：" + result);
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
        SelfChassis.getInstance().removeVersionUpgradeListener(this);
    }
}
