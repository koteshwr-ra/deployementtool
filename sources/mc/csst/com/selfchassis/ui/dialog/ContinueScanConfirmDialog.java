package mc.csst.com.selfchassis.ui.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import com.blankj.utilcode.util.AdaptScreenUtils;
import com.ciot.sentrymove.R;
import mc.csst.com.selfchassis.databinding.DialogContinueScanConfirmBinding;

public class ContinueScanConfirmDialog extends BaseDialog implements View.OnClickListener {
    private DialogContinueScanConfirmBinding mBinding;
    private OnDialogButtonClickListener onCancelButtonClickListener;
    private OnDialogButtonClickListener onOkButtonClickListener;

    public interface OnDialogButtonClickListener {
        boolean onClick(View view, boolean z);
    }

    private void initView() {
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

    public static ContinueScanConfirmDialog newInstance() {
        ContinueScanConfirmDialog continueScanConfirmDialog = new ContinueScanConfirmDialog();
        continueScanConfirmDialog.setSize(AdaptScreenUtils.pt2Px(660.0f), AdaptScreenUtils.pt2Px(378.0f));
        return continueScanConfirmDialog;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mBinding = (DialogContinueScanConfirmBinding) DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_continue_scan_confirm, viewGroup, false);
        initView();
        initEvent();
        return this.mBinding.getRoot();
    }

    private void initEvent() {
        this.mBinding.btnSelectNegative.setOnClickListener(this);
        this.mBinding.btnSelectPositive.setOnClickListener(this);
    }

    public OnDialogButtonClickListener getOnOkButtonClickListener() {
        return this.onOkButtonClickListener;
    }

    public void setOnOkButtonClickListener(OnDialogButtonClickListener onDialogButtonClickListener) {
        this.onOkButtonClickListener = onDialogButtonClickListener;
    }

    public OnDialogButtonClickListener getOnCancelButtonClickListener() {
        return this.onCancelButtonClickListener;
    }

    public void setOnCancelButtonClickListener(OnDialogButtonClickListener onDialogButtonClickListener) {
        this.onCancelButtonClickListener = onDialogButtonClickListener;
    }

    public void onClick(View view) {
        int id = view.getId();
        boolean isChecked = this.mBinding.cbDesc.isChecked();
        if (id == R.id.btn_selectNegative) {
            OnDialogButtonClickListener onDialogButtonClickListener = this.onCancelButtonClickListener;
            if (onDialogButtonClickListener == null) {
                dismissAllowingStateLoss();
            } else if (!onDialogButtonClickListener.onClick(view, isChecked)) {
                dismissAllowingStateLoss();
            }
        } else if (id == R.id.btn_selectPositive) {
            OnDialogButtonClickListener onDialogButtonClickListener2 = this.onOkButtonClickListener;
            if (onDialogButtonClickListener2 == null) {
                dismissAllowingStateLoss();
            } else if (!onDialogButtonClickListener2.onClick(view, isChecked)) {
                dismissAllowingStateLoss();
            }
        }
    }
}
