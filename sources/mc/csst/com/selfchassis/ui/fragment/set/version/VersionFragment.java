package mc.csst.com.selfchassis.ui.fragment.set.version;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ClickUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.ciot.base.livedatabus.LiveDatabus;
import com.ciot.base.util.DateUtils;
import com.ciot.base.util.GsonUtils;
import com.ciot.base.util.MyLogUtils;
import com.ciot.sentrymove.R;
import java.util.TimerTask;
import mc.csst.com.selfchassis.databinding.FragmentSetVersionBinding;
import mc.csst.com.selfchassis.ui.dialog.ConfirmDialog;
import mc.csst.com.selfchassis.ui.fragment.base.BaseFragment;
import mc.csst.com.selfchassis.ui.fragment.set.version.VersionFragment;
import mc.csst.com.selfchassis.utils.DeploymentToolUtils;
import mc.csst.com.selfchassis.utils.bean.LoadingLiveDataEvent;
import mc.csst.com.selfchassis.utils.constant.DTLiveDataConstant;
import mc.csst.com.selfchassislibrary.bean.msg.GetTimeResponse;
import mc.csst.com.selfchassislibrary.bean.msg.RobotInfoResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.VersionUpgradeResponseBean;
import mc.csst.com.selfchassislibrary.chassis.SelfChassis;
import mc.csst.com.selfchassislibrary.content.ServiceContent;
import mc.csst.com.selfchassislibrary.utils.SelfChassisListenerUtils;
import mc.csst.com.selfchassislibrary.utils.eventbus.SelfChassisEventMsg;

public class VersionFragment extends BaseFragment implements View.OnClickListener, SelfChassisListenerUtils.OnBaseSelfChassisListener {
    private static final String TAG = VersionFragment.class.getSimpleName();
    FragmentSetVersionBinding versionBinding;
    VersionInfo versionViewModel;

    /* access modifiers changed from: protected */
    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentSetVersionBinding fragmentSetVersionBinding = (FragmentSetVersionBinding) DataBindingUtil.inflate(layoutInflater, R.layout.fragment_set_version, viewGroup, false);
        this.versionBinding = fragmentSetVersionBinding;
        return fragmentSetVersionBinding.getRoot();
    }

    /* access modifiers changed from: protected */
    public void initData() {
        MyLogUtils.Logd(TAG, "initData");
        VersionInfo versionInfo = new VersionInfo();
        this.versionViewModel = versionInfo;
        versionInfo.setCurVersion(AppUtils.getAppVersionName());
        this.versionBinding.setVersion(this.versionViewModel);
        initSelfChassisListener();
        dealChassisRequest();
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
        ClickUtils.applyGlobalDebouncing((View) this.versionBinding.btnUpdateAlgorithmUsb, (View.OnClickListener) this);
        ClickUtils.applyGlobalDebouncing((View) this.versionBinding.btnUpdateAlgorithm, (View.OnClickListener) this);
        ClickUtils.applyGlobalDebouncing((View) this.versionBinding.btnUpdateDriveFirmware, (View.OnClickListener) this);
        ClickUtils.applyGlobalDebouncing((View) this.versionBinding.btnUpdateTime, (View.OnClickListener) this);
    }

    private void dealChassisRequest() {
        SelfChassis.getInstance().serviceRobotInfo();
        SelfChassis.getInstance().serviceGetTime();
    }

    private void initSelfChassisListener() {
        SelfChassis.getInstance().setRobotInfoListener(this);
        SelfChassis.getInstance().addVersionUpgradeListener(this);
        SelfChassis.getInstance().setGetTimeListener(this);
    }

    private void resetSelfChassisListener() {
        SelfChassis.getInstance().setRobotInfoListener((SelfChassisListenerUtils.OnBaseSelfChassisListener) null);
        SelfChassis.getInstance().removeVersionUpgradeListener(this);
        SelfChassis.getInstance().setGetTimeListener((SelfChassisListenerUtils.OnBaseSelfChassisListener) null);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_update_algorithm:
                Navigation.findNavController(view).navigate((int) R.id.onlineUpdateConfigFragment);
                return;
            case R.id.btn_update_algorithm_usb:
                showLoadingDialog(getString(R.string.tip_check_updating));
                SelfChassis.getInstance().serviceVersionUpgrade(2);
                return;
            case R.id.btn_update_drive_firmware:
                ConfirmDialog buildDefault2 = ConfirmDialog.buildDefault2(getString(R.string.chassis_hardware_update), getString(R.string.chassis_hardware_update_confirm));
                buildDefault2.setOnOkButtonClickListener(new ConfirmDialog.OnDialogButtonClickListener() {
                    public final boolean onClick(View view) {
                        return VersionFragment.this.lambda$onClick$0$VersionFragment(view);
                    }
                });
                buildDefault2.showAllowingStateLoss(getChildFragmentManager());
                return;
            case R.id.btn_update_time:
                showLoadingDialog();
                SelfChassis.getInstance().serviceVersionUpgrade(68);
                return;
            default:
                return;
        }
    }

    public /* synthetic */ boolean lambda$onClick$0$VersionFragment(View view) {
        SelfChassis.getInstance().serviceVersionUpgrade(55);
        showLoadingDialog();
        return false;
    }

    public void onSelfChassisMsg(SelfChassisEventMsg selfChassisEventMsg) {
        ThreadUtils.runOnUiThread(new Runnable(selfChassisEventMsg) {
            public final /* synthetic */ SelfChassisEventMsg f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                VersionFragment.this.lambda$onSelfChassisMsg$1$VersionFragment(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$onSelfChassisMsg$1$VersionFragment(SelfChassisEventMsg selfChassisEventMsg) {
        if (isAdded()) {
            String str = TAG;
            MyLogUtils.Logd(str, "onSelfChassisMsg :" + selfChassisEventMsg.toString());
            String code = selfChassisEventMsg.getCode();
            char c = 65535;
            int hashCode = code.hashCode();
            if (hashCode != -832126554) {
                if (hashCode != -406685998) {
                    if (hashCode == 1885514546 && code.equals(ServiceContent.GET_TIME)) {
                        c = 0;
                    }
                } else if (code.equals(ServiceContent.ROBOT_INFO)) {
                    c = 2;
                }
            } else if (code.equals(ServiceContent.VERSION_UPGRADE)) {
                c = 1;
            }
            if (c == 0) {
                try {
                    this.versionViewModel.setCurTime(DateUtils.formatTime(Long.valueOf(Long.parseLong(((GetTimeResponse) selfChassisEventMsg.getData()).getValues().getTime().getSecs() + "000")), getString(R.string.txt_date_time_format)));
                } catch (Exception e) {
                    String str2 = TAG;
                    MyLogUtils.Logd(str2, "更新工控机时间出现错误：" + Log.getStackTraceString(e));
                }
            } else if (c == 1) {
                onVersionUpgrade((VersionUpgradeResponseBean) selfChassisEventMsg.getData());
            } else if (c == 2) {
                onRobotInfo((RobotInfoResponseBean) selfChassisEventMsg.getData());
            }
        }
    }

    public void onVersionUpgrade(VersionUpgradeResponseBean versionUpgradeResponseBean) {
        if (versionUpgradeResponseBean == null) {
            MyLogUtils.Logd(TAG, "onVersionUpgrade versionInfo is null!");
            return;
        }
        MyLogUtils.Logd("onVersionUpgrade:" + GsonUtils.toJson(versionUpgradeResponseBean));
        VersionUpgradeResponseBean.ValuesBean values = versionUpgradeResponseBean.getValues();
        int result = values.getResult();
        int parseInt = Integer.parseInt(versionUpgradeResponseBean.getId());
        if (result == 0) {
            closeLoadingDialog();
            if (parseInt == 2) {
                String latest_version = values.getLatest_version();
                String current_version = values.getCurrent_version();
                String format = String.format(getString(R.string.confirm_update_version), new Object[]{values.getLatest_version()});
                if (TextUtils.equals(latest_version, current_version)) {
                    format = String.format(getString(R.string.confirm_update_version_repeat), new Object[]{values.getLatest_version()});
                }
                ConfirmDialog buildDefault2 = ConfirmDialog.buildDefault2(getString(R.string.check_update), format);
                buildDefault2.setOnOkButtonClickListener(new ConfirmDialog.OnDialogButtonClickListener() {
                    public final boolean onClick(View view) {
                        return VersionFragment.this.lambda$onVersionUpgrade$2$VersionFragment(view);
                    }
                });
                buildDefault2.showAllowingStateLoss(getChildFragmentManager());
            } else if (parseInt == 55) {
                ConfirmDialog.build(getString(R.string.check_update), getString(R.string.drive_firmware_updateing)).showAllowingStateLoss(getChildFragmentManager());
            } else if (parseInt == 68) {
                ConfirmDialog.buildDefault(getString(R.string.check_update), getString(R.string.update_time_success)).showAllowingStateLoss(getChildFragmentManager());
                SelfChassis.getInstance().serviceGetTime();
            }
        } else {
            if (result == 717) {
                VersionInfo versionInfo = this.versionViewModel;
                versionInfo.setChassisDriveFirmwareVersion(getString(R.string.firmware_invalid) + values.getDesp());
            }
            closeLoadingDialog();
            ConfirmDialog.buildDefault(getString(R.string.check_update), DeploymentToolUtils.getInstance().getUpgradePrompt(result)).showAllowingStateLoss(getChildFragmentManager());
        }
    }

    public /* synthetic */ boolean lambda$onVersionUpgrade$2$VersionFragment(View view) {
        SelfChassis.getInstance().serviceVersionUpgrade(11);
        showLoadingDialog();
        return false;
    }

    public void onRobotInfo(RobotInfoResponseBean robotInfoResponseBean) {
        RobotInfoResponseBean.ValuesBean values = robotInfoResponseBean.getValues();
        String robot_id = values.getRobot_id();
        String firmware_version = values.getFirmware_version();
        String hardware_version = values.getHardware_version();
        String software_version = values.getSoftware_version();
        if (!robotInfoResponseBean.isResult()) {
            MyLogUtils.Logd(TAG, "重新获取版本信息");
            SelfChassis.getInstance().serviceRobotInfo();
            return;
        }
        this.versionViewModel.setRobotChassisNumber(robot_id);
        this.versionViewModel.setAlgorithmVersion(software_version);
        this.versionViewModel.setChassisDriveFirmwareVersion(firmware_version);
        this.versionViewModel.setChassisHardwareVersion(hardware_version);
    }

    private void showLoadingDialog() {
        showLoadingDialog(getString(R.string.tip_updating));
    }

    class CheckConnectTimeOut extends TimerTask {
        CheckConnectTimeOut() {
        }

        public void run() {
            if (!SelfChassis.getInstance().isConnect()) {
                VersionFragment.this.closeLoadingDialog();
                ConfirmDialog buildDefault = ConfirmDialog.buildDefault(VersionFragment.this.getString(R.string.dialog_tip), VersionFragment.this.getString(R.string.connect_chassis_timeout));
                buildDefault.setOnOkButtonClickListener(new ConfirmDialog.OnDialogButtonClickListener() {
                    public final boolean onClick(View view) {
                        return VersionFragment.CheckConnectTimeOut.this.lambda$run$0$VersionFragment$CheckConnectTimeOut(view);
                    }
                });
                if (!buildDefault.isAdded()) {
                    buildDefault.showAllowingStateLoss(VersionFragment.this.getChildFragmentManager());
                }
            }
        }

        public /* synthetic */ boolean lambda$run$0$VersionFragment$CheckConnectTimeOut(View view) {
            if (VersionFragment.this.getActivity() == null) {
                return false;
            }
            VersionFragment.this.getActivity().finish();
            return false;
        }
    }

    public void onDestroy() {
        super.onDestroy();
        closeLoadingDialog();
        resetSelfChassisListener();
    }

    public void closeLoadingDialog() {
        LiveDatabus.getInstance().with(DTLiveDataConstant.SET_LOADING_DIALOG_EVENT).postValue(new LoadingLiveDataEvent(true));
    }

    public void showLoadingDialog(String str) {
        LiveDatabus.getInstance().with(DTLiveDataConstant.SET_LOADING_DIALOG_EVENT).postValue(new LoadingLiveDataEvent(str));
    }

    public void showLoadingDialog(String str, int i) {
        LiveDatabus.getInstance().with(DTLiveDataConstant.SET_LOADING_DIALOG_EVENT).postValue(new LoadingLiveDataEvent(str, i));
    }
}
