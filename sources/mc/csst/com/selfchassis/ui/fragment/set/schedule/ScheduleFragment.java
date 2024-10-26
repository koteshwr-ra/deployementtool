package mc.csst.com.selfchassis.ui.fragment.set.schedule;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import com.blankj.utilcode.util.AdaptScreenUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.ciot.base.livedatabus.LiveDatabus;
import com.ciot.base.util.GsonUtils;
import com.ciot.base.util.MyLogUtils;
import com.ciot.networklib.bean.DataUpdateEvent;
import com.ciot.sentrymove.R;
import java.util.ArrayList;
import java.util.regex.Pattern;
import mc.csst.com.selfchassis.databinding.FragmentScheduleBinding;
import mc.csst.com.selfchassis.ui.dialog.EditDialog;
import mc.csst.com.selfchassis.ui.fragment.base.BaseFragment;
import mc.csst.com.selfchassis.utils.MultiRobotUtil;
import mc.csst.com.selfchassis.utils.MyToastUtils;
import mc.csst.com.selfchassis.utils.bean.LoadingLiveDataEvent;
import mc.csst.com.selfchassis.utils.constant.DTLiveDataConstant;
import mc.csst.com.selfchassislibrary.bean.msg.MultiRobotStationRespBean;
import mc.csst.com.selfchassislibrary.bean.msg.RequestBean;
import mc.csst.com.selfchassislibrary.chassis.SelfChassis;
import mc.csst.com.selfchassislibrary.utils.SelfChassisListenerUtils;
import mc.csst.com.selfchassislibrary.utils.eventbus.SelfChassisEventMsg;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class ScheduleFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener {
    public static final String ANNEX_TAR_GZ = "annex.tar.gz";
    public static final String FLOOR = "floor";
    public static final String MAP = "map";
    public static final String MAP_PNG = "map.png";
    public static final String SLASH = "/";
    SelfChassisListenerUtils.OnBaseSelfChassisListener configMqttListener = null;
    private EditDialog mEditDialog;
    private EditDialog mProjectNameEditDialog;
    FragmentScheduleBinding scheduleBinding;

    /* access modifiers changed from: protected */
    public void initData() {
    }

    /* access modifiers changed from: protected */
    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentScheduleBinding fragmentScheduleBinding = (FragmentScheduleBinding) DataBindingUtil.inflate(layoutInflater, R.layout.fragment_schedule, viewGroup, false);
        this.scheduleBinding = fragmentScheduleBinding;
        return fragmentScheduleBinding.getRoot();
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
        this.scheduleBinding.switchMultiRobotMode.setOnCheckedChangeListener($$Lambda$ScheduleFragment$FAxhDT2KtqKDlcs37mDgtIyFp4g.INSTANCE);
        $$Lambda$ScheduleFragment$uSgvFRx6UwOw0xVEVxJ5Z63m_jc r0 = new SelfChassisListenerUtils.OnBaseSelfChassisListener() {
            public final void onSelfChassisMsg(SelfChassisEventMsg selfChassisEventMsg) {
                ScheduleFragment.this.lambda$initEvent$1$ScheduleFragment(selfChassisEventMsg);
            }
        };
        this.configMqttListener = r0;
        SelfChassisListenerUtils.ConfigMqtt.addConfigMqttListener(r0);
        SelfChassis.getInstance().configStationServer(0, (String) null, (Boolean) null, "initEvent");
        this.scheduleBinding.btnModify.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ScheduleFragment.this.showUltrasoundDistanceEditDialog();
            }
        });
        this.scheduleBinding.btnModifyName.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ScheduleFragment.this.showProjectNameEditDialog();
            }
        });
        this.scheduleBinding.btnSureUpload.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ScheduleFragment scheduleFragment = ScheduleFragment.this;
                scheduleFragment.showLoadingDialog(scheduleFragment.getString(R.string.txt_multi_robot_map_uploading));
                ArrayList arrayList = new ArrayList();
                RequestBean.ArgsBean.FloorBean floorBean = new RequestBean.ArgsBean.FloorBean();
                floorBean.setNum(ScheduleFragment.this.scheduleBinding.mrmpwUpload.getSelectedFloorName());
                arrayList.add(floorBean);
                SelfChassis.getInstance().serviceUploadMapsMultiRobot(ScheduleFragment.this.scheduleBinding.mrmpwUpload.getSelectedBuildName(), arrayList);
            }
        });
        this.scheduleBinding.btnSureDownload.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ScheduleFragment scheduleFragment = ScheduleFragment.this;
                scheduleFragment.showLoadingDialog(scheduleFragment.getString(R.string.txt_multi_robot_map_downloading));
                ArrayList arrayList = new ArrayList();
                RequestBean.ArgsBean.FloorBean floorBean = new RequestBean.ArgsBean.FloorBean();
                ScheduleFragment scheduleFragment2 = ScheduleFragment.this;
                floorBean.setAnnex(scheduleFragment2.getAnnex(scheduleFragment2.scheduleBinding.mrmpwDownload.getSelectedBuildName(), ScheduleFragment.this.scheduleBinding.mrmpwDownload.getSelectedFloorName()));
                floorBean.setNum(ScheduleFragment.this.scheduleBinding.mrmpwDownload.getSelectedFloorName());
                ScheduleFragment scheduleFragment3 = ScheduleFragment.this;
                floorBean.setPath(scheduleFragment3.getPath(scheduleFragment3.scheduleBinding.mrmpwDownload.getSelectedBuildName(), ScheduleFragment.this.scheduleBinding.mrmpwDownload.getSelectedFloorName()));
                arrayList.add(floorBean);
                SelfChassis.getInstance().serviceDownloadMapsMultiRobot(ScheduleFragment.this.scheduleBinding.mrmpwDownload.getSelectedBuildName(), arrayList);
            }
        });
        this.scheduleBinding.rgChangeNetwork.setOnCheckedChangeListener(this);
    }

    public /* synthetic */ void lambda$initEvent$1$ScheduleFragment(final SelfChassisEventMsg selfChassisEventMsg) {
        ThreadUtils.runOnUiThread(new Runnable() {
            public void run() {
                MultiRobotStationRespBean multiRobotStationRespBean = (MultiRobotStationRespBean) selfChassisEventMsg.getData();
                MyLogUtils.Logd("", "MultiRobotStationRespBean:" + GsonUtils.toJson(multiRobotStationRespBean));
                if (multiRobotStationRespBean.getResult().booleanValue()) {
                    ScheduleFragment.this.scheduleBinding.tvIpaddress.setText(multiRobotStationRespBean.getValues().getHost());
                    ScheduleFragment.this.scheduleBinding.tvProjectName.setText(multiRobotStationRespBean.getValues().getProject_name());
                    if (multiRobotStationRespBean.getValues().getWan_switch().booleanValue()) {
                        ScheduleFragment.this.scheduleBinding.rgChangeNetwork.check(R.id.rb_public_work);
                    } else {
                        ScheduleFragment.this.scheduleBinding.rgChangeNetwork.check(R.id.rb_local_network);
                    }
                    ScheduleFragment.this.scheduleBinding.switchMultiRobotMode.setCheckedSilent(multiRobotStationRespBean.getValues().getSwitch_on().booleanValue());
                    ScheduleFragment.this.updateSwitchView(multiRobotStationRespBean.getValues().getSwitch_on().booleanValue());
                    MultiRobotUtil.isTurnOnMultiRobot = multiRobotStationRespBean.getValues().getSwitch_on().booleanValue();
                    return;
                }
                Context context = ScheduleFragment.this.getContext();
                MyToastUtils.showShort(context, ScheduleFragment.this.getString(R.string.txt_network_setting_failure) + multiRobotStationRespBean.getInfo());
            }
        });
    }

    /* access modifiers changed from: private */
    public void updateSwitchView(boolean z) {
        int i = 0;
        this.scheduleBinding.clMapSyncConfig.setVisibility(z ? 0 : 8);
        this.scheduleBinding.clNetworkConfig.setVisibility((!z || this.scheduleBinding.rgChangeNetwork.getCheckedRadioButtonId() != R.id.rb_local_network) ? 8 : 0);
        this.scheduleBinding.clNetworkChange.setVisibility(z ? 0 : 8);
        ConstraintLayout constraintLayout = this.scheduleBinding.clProjectName;
        if (!z || this.scheduleBinding.rgChangeNetwork.getCheckedRadioButtonId() != R.id.rb_public_work) {
            i = 8;
        }
        constraintLayout.setVisibility(i);
    }

    /* access modifiers changed from: private */
    public void showUltrasoundDistanceEditDialog() {
        closeEditDialog();
        EditDialog newInstance = EditDialog.newInstance();
        this.mEditDialog = newInstance;
        newInstance.setSize(AdaptScreenUtils.pt2Px(1064.0f), AdaptScreenUtils.pt2Px(480.0f));
        this.mEditDialog.setTitle(StringUtils.getString(R.string.txt_dispatch_setting));
        this.mEditDialog.setSubTitle(StringUtils.getString(R.string.txt_please_enter_gateway));
        this.mEditDialog.setContent(this.scheduleBinding.tvIpaddress.getText().toString().trim());
        this.mEditDialog.setHintTxt("xxx.xxx.xxx.xxx");
        this.mEditDialog.setOnDialogButtonClickListener(new EditDialog.OnDialogButtonClickListener() {
            public void onCanCel() {
            }

            public boolean onConfirm(String str, float f) {
                if (ScheduleFragment.isCorrectIp(str)) {
                    ScheduleFragment.this.scheduleBinding.tvIpaddress.setText(str);
                    SelfChassis.getInstance().configStationServer(1, str, true, "showUltrasoundDistanceEditDialog");
                    return false;
                }
                MyToastUtils.showShort(ScheduleFragment.this.getContext(), ScheduleFragment.this.getString(R.string.txt_please_input_the_right_gateway));
                return true;
            }
        });
        this.mEditDialog.showAllowingStateLoss(getChildFragmentManager());
    }

    /* access modifiers changed from: private */
    public void showProjectNameEditDialog() {
        closeProjectNameEditDialog();
        EditDialog newInstance = EditDialog.newInstance();
        this.mProjectNameEditDialog = newInstance;
        newInstance.setSize(AdaptScreenUtils.pt2Px(1064.0f), AdaptScreenUtils.pt2Px(480.0f));
        this.mProjectNameEditDialog.setTitle(getString(R.string.input_project_name));
        this.mProjectNameEditDialog.setSubTitle(getString(R.string.input_project_name));
        this.mProjectNameEditDialog.setContent(this.scheduleBinding.tvProjectName.getText().toString().trim());
        this.mProjectNameEditDialog.setHintTxt(getString(R.string.please_input));
        this.mProjectNameEditDialog.setOnDialogButtonClickListener(new EditDialog.OnDialogButtonClickListener() {
            public void onCanCel() {
            }

            public boolean onConfirm(String str, float f) {
                if (TextUtils.isEmpty(str)) {
                    MyToastUtils.showShort(ScheduleFragment.this.requireContext(), ScheduleFragment.this.getString(R.string.input_project_name));
                    return true;
                }
                SelfChassis.getInstance().configStationServer(4, str, false, "showProjectNameEditDialog");
                ScheduleFragment.this.scheduleBinding.tvProjectName.setText(str);
                return false;
            }
        });
        this.mProjectNameEditDialog.showAllowingStateLoss(getChildFragmentManager());
    }

    private void closeEditDialog() {
        EditDialog editDialog = this.mEditDialog;
        if (editDialog != null && editDialog.isAdded()) {
            this.mEditDialog.dismissAllowingStateLoss();
            this.mEditDialog = null;
        }
    }

    private void closeProjectNameEditDialog() {
        EditDialog editDialog = this.mProjectNameEditDialog;
        if (editDialog != null && editDialog.isAdded()) {
            this.mProjectNameEditDialog.dismissAllowingStateLoss();
            this.mProjectNameEditDialog = null;
        }
    }

    /* access modifiers changed from: private */
    public String getPath(String str, String str2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(MAP);
        stringBuffer.append("/");
        stringBuffer.append(str);
        stringBuffer.append("/");
        stringBuffer.append(FLOOR);
        stringBuffer.append("_");
        stringBuffer.append(str2);
        stringBuffer.append("/");
        stringBuffer.append(MAP_PNG);
        return stringBuffer.toString();
    }

    /* access modifiers changed from: private */
    public String getAnnex(String str, String str2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(MAP);
        stringBuffer.append("/");
        stringBuffer.append(str);
        stringBuffer.append("/");
        stringBuffer.append(FLOOR);
        stringBuffer.append("_");
        stringBuffer.append(str2);
        stringBuffer.append("/");
        stringBuffer.append(ANNEX_TAR_GZ);
        return stringBuffer.toString();
    }

    public void onDestroy() {
        super.onDestroy();
        SelfChassisListenerUtils.ConfigMqtt.removeMqttListener(this.configMqttListener);
    }

    public static boolean isCorrectIp(String str) {
        return Pattern.compile("([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}").matcher(str).matches();
    }

    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002b  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0050  */
    @org.greenrobot.eventbus.Subscribe
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleSuccessEvent(mc.csst.com.selfchassislibrary.utils.eventbus.SelfChassisEventMsg r5) {
        /*
            r4 = this;
            java.lang.String r0 = r5.getCode()
            int r1 = r0.hashCode()
            r2 = -1458264442(0xffffffffa914a686, float:-3.3007038E-14)
            r3 = 1
            if (r1 == r2) goto L_0x001e
            r2 = 2040205343(0x799b101f, float:1.0064162E35)
            if (r1 == r2) goto L_0x0014
            goto L_0x0028
        L_0x0014:
            java.lang.String r1 = "/download_maps"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0028
            r0 = 1
            goto L_0x0029
        L_0x001e:
            java.lang.String r1 = "/upload_maps"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0028
            r0 = 0
            goto L_0x0029
        L_0x0028:
            r0 = -1
        L_0x0029:
            if (r0 == 0) goto L_0x0050
            if (r0 == r3) goto L_0x002e
            goto L_0x0071
        L_0x002e:
            r4.closeLoadingDialog()
            java.lang.Object r5 = r5.getData()
            mc.csst.com.selfchassislibrary.bean.msg.DownloadMapsResponseBean r5 = (mc.csst.com.selfchassislibrary.bean.msg.DownloadMapsResponseBean) r5
            android.content.Context r0 = r4.getContext()
            boolean r5 = r5.isResult()
            if (r5 == 0) goto L_0x0045
            r5 = 2131755655(0x7f100287, float:1.9142195E38)
            goto L_0x0048
        L_0x0045:
            r5 = 2131755252(0x7f1000f4, float:1.9141378E38)
        L_0x0048:
            java.lang.String r5 = r4.getString(r5)
            mc.csst.com.selfchassis.utils.MyToastUtils.showShort(r0, r5)
            goto L_0x0071
        L_0x0050:
            r4.closeLoadingDialog()
            java.lang.Object r5 = r5.getData()
            mc.csst.com.selfchassislibrary.bean.msg.UploadMapsResponseBean r5 = (mc.csst.com.selfchassislibrary.bean.msg.UploadMapsResponseBean) r5
            android.content.Context r0 = r4.getContext()
            boolean r5 = r5.isResult()
            if (r5 == 0) goto L_0x0067
            r5 = 2131755803(0x7f10031b, float:1.9142496E38)
            goto L_0x006a
        L_0x0067:
            r5 = 2131755802(0x7f10031a, float:1.9142494E38)
        L_0x006a:
            java.lang.String r5 = r4.getString(r5)
            mc.csst.com.selfchassis.utils.MyToastUtils.showShort(r0, r5)
        L_0x0071:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: mc.csst.com.selfchassis.ui.fragment.set.schedule.ScheduleFragment.handleSuccessEvent(mc.csst.com.selfchassislibrary.utils.eventbus.SelfChassisEventMsg):void");
    }

    @Subscribe
    public void handleErrorEvent(DataUpdateEvent dataUpdateEvent) {
        int type = dataUpdateEvent.getType();
        if (type == 12 || type == 13) {
            closeLoadingDialog();
            MyToastUtils.showShort(getContext(), dataUpdateEvent.getId());
        }
    }

    public void closeLoadingDialog() {
        LiveDatabus.getInstance().with(DTLiveDataConstant.SET_LOADING_DIALOG_EVENT).postValue(new LoadingLiveDataEvent(true));
    }

    public void showLoadingDialog(String str) {
        LiveDatabus.getInstance().with(DTLiveDataConstant.SET_LOADING_DIALOG_EVENT).postValue(new LoadingLiveDataEvent(str));
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (i == R.id.rb_local_network) {
            this.scheduleBinding.clNetworkConfig.setVisibility(0);
            this.scheduleBinding.clProjectName.setVisibility(8);
            SelfChassis.getInstance().configStationServer(3, "", false, "rb_local_network");
        } else if (i == R.id.rb_public_work) {
            this.scheduleBinding.clNetworkConfig.setVisibility(8);
            this.scheduleBinding.clProjectName.setVisibility(0);
            SelfChassis.getInstance().configStationServer(3, "", true, "rb_public_work");
        }
    }
}
