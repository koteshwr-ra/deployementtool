package mc.csst.com.selfchassis.ui.dialog;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import com.blankj.utilcode.util.AdaptScreenUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.ciot.base.storage.MySpUtils;
import com.ciot.base.util.ContextUtil;
import com.ciot.sentrymove.R;
import mc.csst.com.selfchassis.databinding.DialogConnectedBinding;
import mc.csst.com.selfchassis.utils.MyToastUtils;
import mc.csst.com.selfchassis.utils.SpConstant;
import mc.csst.com.selfchassis.utils.constant.DeploymentToolConstant;
import mc.csst.com.selfchassislibrary.chassis.SelfChassis;
import mc.csst.com.selfchassislibrary.utils.SelfChassisListenerUtils;

public class ConnectedDialog extends BaseDialog implements View.OnClickListener, TextWatcher {
    private DialogConnectedBinding mBinding;
    /* access modifiers changed from: private */
    public boolean mCheckFail = false;
    /* access modifiers changed from: private */
    public LoadingDialog mLoadingDialog;

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void convertView(View view, BaseDialog baseDialog) {
    }

    public int setUpLayoutId() {
        return 0;
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

    public static ConnectedDialog newInstance() {
        ConnectedDialog connectedDialog = new ConnectedDialog();
        connectedDialog.setSize(AdaptScreenUtils.pt2Px(1064.0f), AdaptScreenUtils.pt2Px(480.0f));
        return connectedDialog;
    }

    public void onResume() {
        super.onResume();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mBinding = (DialogConnectedBinding) DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_connected, viewGroup, false);
        initListener();
        initData();
        return this.mBinding.getRoot();
    }

    private void initData() {
        this.mBinding.buttons.btnSelectPositive.setClickable(false);
        this.mBinding.buttons.btnSelectNegative.setText(getString(R.string.exit_app));
        this.mBinding.buttons.btnSelectPositive.setText(getString(R.string.connect_chassis_confirm));
        String chassisIp = getChassisIp();
        if (chassisIp != null) {
            this.mBinding.ipEdit.setText(chassisIp);
        }
    }

    private void initListener() {
        this.mBinding.buttons.btnSelectNegative.setOnClickListener(this);
        this.mBinding.buttons.btnSelectPositive.setOnClickListener(this);
        this.mBinding.ipEdit.addTextChangedListener(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_selectNegative) {
            dealCancle();
        } else if (id == R.id.btn_selectPositive) {
            dealOk();
        }
    }

    private void dealCancle() {
        AppUtils.exitApp();
    }

    private void dealOk() {
        this.mCheckFail = false;
        if (!TextUtils.isEmpty(this.mBinding.ipEdit.getText())) {
            String trim = this.mBinding.ipEdit.getText().toString().trim();
            if (!RegexUtils.isIP(trim)) {
                MyToastUtils.showShort(ContextUtil.getContext(), ContextUtil.getContext().getString(R.string.ip_format_error));
                return;
            }
            String chassisFullAddress = getChassisFullAddress(trim);
            if (this.mLoadingDialog == null) {
                LoadingDialog newInstance = LoadingDialog.newInstance();
                this.mLoadingDialog = newInstance;
                newInstance.setLoadingText(getString(R.string.tip_connecting));
            }
            if (!this.mLoadingDialog.isAdded()) {
                this.mLoadingDialog.showAllowingStateLoss(getChildFragmentManager(), "loadingDialog");
            }
            SelfChassis.getInstance().connectSelfChassis(chassisFullAddress);
            SelfChassisListenerUtils.getInstance().setConnectListener(new SelfChassisListenerUtils.OnConnectedListener() {
                public final void connected(boolean z) {
                    ConnectedDialog.this.lambda$dealOk$0$ConnectedDialog(z);
                }
            });
            if (SelfChassis.getInstance().isConnect()) {
                dismissAllowingStateLoss();
            }
        }
    }

    public /* synthetic */ void lambda$dealOk$0$ConnectedDialog(final boolean z) {
        ThreadUtils.runOnUiThread(new Runnable() {
            public void run() {
                if (ConnectedDialog.this.mLoadingDialog != null && ConnectedDialog.this.mLoadingDialog.isAdded()) {
                    ConnectedDialog.this.mLoadingDialog.dismissAllowingStateLoss();
                    LoadingDialog unused = ConnectedDialog.this.mLoadingDialog = null;
                }
                if (z && ConnectedDialog.this.isShow()) {
                    boolean unused2 = ConnectedDialog.this.mCheckFail = true;
                    ConnectedDialog.this.dismissAllowingStateLoss();
                    MyToastUtils.showShort(ContextUtil.getContext(), ContextUtil.getContext().getString(R.string.tip_connecte_success));
                } else if (!ConnectedDialog.this.mCheckFail) {
                    MyToastUtils.showShort(ContextUtil.getContext(), ContextUtil.getContext().getString(R.string.tip_connecte_fail));
                    boolean unused3 = ConnectedDialog.this.mCheckFail = true;
                }
                SelfChassisListenerUtils.getInstance().setConnectListener((SelfChassisListenerUtils.OnConnectedListener) null);
            }
        });
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (TextUtils.isEmpty(charSequence) || charSequence.length() == 0) {
            this.mBinding.buttons.btnSelectPositive.setClickable(false);
            this.mBinding.buttons.btnSelectPositive.setBackgroundResource(R.drawable.button_unselect);
            return;
        }
        this.mBinding.buttons.btnSelectPositive.setClickable(true);
        this.mBinding.buttons.btnSelectPositive.setBackgroundResource(R.drawable.button_select);
    }

    private String getChassisIp() {
        return MySpUtils.getInstance().getString(SpConstant.CHASSIS_IP, DeploymentToolConstant.CHASSIS_IP);
    }

    private void setChassisIp(String str) {
        SPUtils.getInstance().put(SpConstant.CHASSIS_IP, str);
    }

    private String getChassisFullAddress(String str) {
        if (TextUtils.isEmpty(str)) {
            return "ws://" + getChassisIp() + ":" + DeploymentToolConstant.CHASSIS_PORT;
        }
        MySpUtils.getInstance().put(SpConstant.CHASSIS_IP, str);
        return "ws://" + str + ":" + DeploymentToolConstant.CHASSIS_PORT;
    }

    public void onDestroyView() {
        super.onDestroyView();
        KeyboardUtils.hideSoftInput(this.mBinding.getRoot());
        LoadingDialog loadingDialog = this.mLoadingDialog;
        if (loadingDialog != null && loadingDialog.isAdded()) {
            this.mLoadingDialog.dismissAllowingStateLoss();
            this.mLoadingDialog = null;
        }
        SelfChassisListenerUtils.getInstance().setConnectListener((SelfChassisListenerUtils.OnConnectedListener) null);
    }
}
