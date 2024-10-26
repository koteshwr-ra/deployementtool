package mc.csst.com.selfchassis.ui.dialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import com.blankj.utilcode.util.AdaptScreenUtils;
import com.ciot.base.storage.MySpUtils;
import com.ciot.base.util.ContextUtil;
import com.ciot.base.util.MyLogUtils;
import com.ciot.sentrymove.R;
import mc.csst.com.selfchassis.databinding.DialogElevatorBinding;
import mc.csst.com.selfchassis.utils.MyToastUtils;
import mc.csst.com.selfchassis.utils.SpConstant;
import mc.csst.com.selfchassis.utils.constant.DeploymentToolConstant;
import mc.csst.com.selfchassislibrary.bean.msg.LiftControlConfigureResponseBean;
import mc.csst.com.selfchassislibrary.chassis.SelfChassis;
import mc.csst.com.selfchassislibrary.utils.SelfChassisListenerUtils;
import mc.csst.com.selfchassislibrary.utils.eventbus.SelfChassisEventMsg;

public class ElevatorDialog extends BaseDialog implements View.OnClickListener {
    /* access modifiers changed from: private */
    public static final String TAG = ElevatorDialog.class.getSimpleName();
    DialogElevatorBinding mBinding;

    public void convertView(View view, BaseDialog baseDialog) {
    }

    public int setUpLayoutId() {
        return R.layout.dialog_elevator;
    }

    public /* bridge */ /* synthetic */ boolean checkNullAndShowToast(String str, String str2) {
        return super.checkNullAndShowToast(str, str2);
    }

    public /* bridge */ /* synthetic */ void fullScreenImmersive() {
        super.fullScreenImmersive();
    }

    public /* bridge */ /* synthetic */ boolean isShow() {
        return super.isShow();
    }

    public /* bridge */ /* synthetic */ void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public /* bridge */ /* synthetic */ void onStart() {
        super.onStart();
    }

    public /* bridge */ /* synthetic */ void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    public /* bridge */ /* synthetic */ void removeDecorViewBg() {
        super.removeDecorViewBg();
    }

    public /* bridge */ /* synthetic */ BaseDialog setAnimStyle(int i) {
        return super.setAnimStyle(i);
    }

    public /* bridge */ /* synthetic */ BaseDialog setDimAmout(float f) {
        return super.setDimAmout(f);
    }

    public /* bridge */ /* synthetic */ BaseDialog setMargin(int i) {
        return super.setMargin(i);
    }

    public /* bridge */ /* synthetic */ BaseDialog setOutCancel(boolean z) {
        return super.setOutCancel(z);
    }

    public /* bridge */ /* synthetic */ BaseDialog setShowBottom(boolean z) {
        return super.setShowBottom(z);
    }

    public /* bridge */ /* synthetic */ BaseDialog setSize(int i, int i2) {
        return super.setSize(i, i2);
    }

    public /* bridge */ /* synthetic */ BaseDialog show(FragmentManager fragmentManager) {
        return super.show(fragmentManager);
    }

    public /* bridge */ /* synthetic */ void showAllowingStateLoss(FragmentManager fragmentManager) {
        super.showAllowingStateLoss(fragmentManager);
    }

    public /* bridge */ /* synthetic */ void showAllowingStateLoss(FragmentManager fragmentManager, String str) {
        super.showAllowingStateLoss(fragmentManager, str);
    }

    public static ElevatorDialog newInstance() {
        ElevatorDialog elevatorDialog = new ElevatorDialog();
        elevatorDialog.setSize(AdaptScreenUtils.pt2Px(1251.0f), AdaptScreenUtils.pt2Px(650.0f));
        return elevatorDialog;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mBinding = (DialogElevatorBinding) DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_elevator, viewGroup, false);
        initData();
        this.mBinding.buttons.btnSelectPositive.setOnClickListener(this);
        this.mBinding.buttons.btnSelectNegative.setOnClickListener(this);
        return this.mBinding.getRoot();
    }

    private void initData() {
        this.mBinding.etElevatorProject.setContentText(MySpUtils.getInstance().getString(SpConstant.ELEVATOR_PROJECT_INFO));
        this.mBinding.etAppId.setContentText(MySpUtils.getInstance().getString(SpConstant.ELEVATOR_APP_ID));
        this.mBinding.etElevatorAppSecret.setContentText(MySpUtils.getInstance().getString(SpConstant.ELEVATOR_APP_SECRET));
        this.mBinding.etRebootId.setContentText(MySpUtils.getInstance().getString(SpConstant.ELEVATOR_REBOOT_ID));
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_selectPositive) {
            dialogOk();
        } else if (id == R.id.btn_selectNegative) {
            dismiss();
        }
    }

    private void dialogOk() {
        String contentText = this.mBinding.etElevatorProject.getContentText();
        if (TextUtils.isEmpty(contentText)) {
            this.mBinding.etElevatorProject.setError(getString(R.string.please_input_project_info));
            return;
        }
        String contentText2 = this.mBinding.etAppId.getContentText();
        if (TextUtils.isEmpty(contentText2)) {
            this.mBinding.etAppId.setError(getString(R.string.please_input_app_id));
            return;
        }
        String contentText3 = this.mBinding.etElevatorAppSecret.getContentText();
        if (TextUtils.isEmpty(contentText3)) {
            this.mBinding.etElevatorAppSecret.setError(getString(R.string.please_input_app_secret));
            return;
        }
        String contentText4 = this.mBinding.etRebootId.getContentText();
        if (TextUtils.isEmpty(contentText4)) {
            this.mBinding.etRebootId.setError(getString(R.string.please_input_reboot_id));
            return;
        }
        MySpUtils.getInstance().put(SpConstant.ELEVATOR_PROJECT_INFO, contentText);
        MySpUtils.getInstance().put(SpConstant.ELEVATOR_APP_ID, contentText2);
        MySpUtils.getInstance().put(SpConstant.ELEVATOR_APP_SECRET, contentText3);
        MySpUtils.getInstance().put(SpConstant.ELEVATOR_REBOOT_ID, contentText4);
        SelfChassis.getInstance().setLiftControlConfigure(contentText, contentText2, contentText3, contentText4);
        SelfChassisListenerUtils.getInstance().setLiftConfigureListener(new SelfChassisListenerUtils.OnBaseSelfChassisListener() {
            public void onSelfChassisMsg(SelfChassisEventMsg selfChassisEventMsg) {
                SelfChassisListenerUtils.getInstance().setLiftConfigureListener((SelfChassisListenerUtils.OnBaseSelfChassisListener) null);
                if (selfChassisEventMsg != null) {
                    LiftControlConfigureResponseBean liftControlConfigureResponseBean = (LiftControlConfigureResponseBean) selfChassisEventMsg.getData();
                    if (liftControlConfigureResponseBean.isResult().booleanValue()) {
                        MyToastUtils.showShort(ContextUtil.getContext(), ContextUtil.getContext().getResources().getString(R.string.tip_elevator_info_success));
                        return;
                    }
                    String access$000 = ElevatorDialog.TAG;
                    MyLogUtils.Loge(access$000, "dialogOk configureResponseBean(梯控信息初始化异常)：" + liftControlConfigureResponseBean.toString());
                    String info = liftControlConfigureResponseBean.getValues().getInfo();
                    if (TextUtils.equals(DeploymentToolConstant.CHECK_LIFT_SERVER_OPEN, liftControlConfigureResponseBean.getInfo())) {
                        MyToastUtils.showShort(ContextUtil.getContext(), ContextUtil.getContext().getResources().getString(R.string.tip_elevator_config_failed));
                    } else if (TextUtils.isEmpty(info)) {
                        MyToastUtils.showShort(ContextUtil.getContext(), ContextUtil.getContext().getResources().getString(R.string.elevator_config_info_error));
                    } else {
                        MyToastUtils.showShort(ContextUtil.getContext(), liftControlConfigureResponseBean.getValues().getInfo());
                    }
                }
            }
        });
        dismiss();
    }
}
