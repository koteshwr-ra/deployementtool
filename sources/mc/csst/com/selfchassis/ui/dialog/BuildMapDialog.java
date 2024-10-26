package mc.csst.com.selfchassis.ui.dialog;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import com.ciot.sentrymove.R;
import java.util.Objects;
import mc.csst.com.selfchassis.databinding.DialogBuildMapBinding;

public class BuildMapDialog extends BaseDialog implements View.OnClickListener {
    private static final String KEY_CMD = "cmd";
    private static final String KEY_FLOOR_NAME = "floorName";
    private static final String KEY_MAP_NAME = "mapName";
    private String dialogTitle = "";
    private DialogBuildMapBinding mBinding;
    OnDialogButtonOnClickListener mOnDialogButtonOnClickListener;

    public interface OnDialogButtonOnClickListener {
        void cancelBtn();

        boolean okBtn(int i, String str, String str2);
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

    public static BuildMapDialog newInstance() {
        return newInstance(0, "", "");
    }

    public static BuildMapDialog newInstance(int i, String str, String str2) {
        BuildMapDialog buildMapDialog = new BuildMapDialog();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_MAP_NAME, str2);
        bundle.putString(KEY_FLOOR_NAME, str);
        bundle.putString(KEY_CMD, String.valueOf(i));
        buildMapDialog.setArguments(bundle);
        return buildMapDialog;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mBinding = (DialogBuildMapBinding) DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_build_map, viewGroup, false);
        initData();
        this.mBinding.buttons.btnSelectPositive.setOnClickListener(this);
        this.mBinding.buttons.btnSelectNegative.setOnClickListener(this);
        return this.mBinding.getRoot();
    }

    private void initData() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            String string = arguments.getString(KEY_MAP_NAME);
            String string2 = arguments.getString(KEY_FLOOR_NAME);
            int parseInt = Integer.parseInt(arguments.getString(KEY_CMD));
            if (!TextUtils.isEmpty(string)) {
                this.mBinding.dialogMapNameEt.setText(string);
                this.mBinding.dialogMapBuildTypeRg.setVisibility(4);
            }
            if (!TextUtils.isEmpty(string2)) {
                this.mBinding.dialogMapFloorEt.setText(string2);
            }
            if (parseInt == 0) {
                this.mBinding.dialogMapBuildTypeNormalRb.setChecked(true);
            } else if (parseInt == 11) {
                this.mBinding.dialogMapBuildTypeLikeRb.setChecked(true);
            } else if (parseInt == 12) {
                this.mBinding.dialogMapBuildTypeBigRb.setChecked(true);
            } else if (parseInt == 13) {
                this.mBinding.dialogMapBuildTypeAssistRb.setChecked(true);
            }
            if (!TextUtils.isEmpty(this.dialogTitle)) {
                this.mBinding.dialogTitle.setText(this.dialogTitle);
            }
        }
    }

    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(this.mBinding.rootRl.getLayoutParams().width, this.mBinding.rootRl.getLayoutParams().height);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_selectPositive) {
            dialogOk();
        } else if (id == R.id.btn_selectNegative) {
            dialogCancel();
        }
    }

    private void dialogOk() {
        String trim = ((Editable) Objects.requireNonNull(this.mBinding.dialogMapNameEt.getText())).toString().trim();
        String trim2 = ((Editable) Objects.requireNonNull(this.mBinding.dialogMapFloorEt.getText())).toString().trim();
        int i = 0;
        if (!this.mBinding.dialogMapBuildTypeNormalRb.isChecked()) {
            if (this.mBinding.dialogMapBuildTypeLikeRb.isChecked()) {
                i = 11;
            } else if (this.mBinding.dialogMapBuildTypeBigRb.isChecked()) {
                i = 12;
            } else if (this.mBinding.dialogMapBuildTypeAssistRb.isChecked()) {
                i = 13;
            }
        }
        if (!checkNullAndShowToast(trim, getString(R.string.tip_map_name)) && !checkNullAndShowToast(trim2, getString(R.string.tip_floor_name))) {
            OnDialogButtonOnClickListener onDialogButtonOnClickListener = this.mOnDialogButtonOnClickListener;
            if (onDialogButtonOnClickListener == null || onDialogButtonOnClickListener.okBtn(i, trim2, trim)) {
                dismiss();
            }
        }
    }

    private void dialogCancel() {
        OnDialogButtonOnClickListener onDialogButtonOnClickListener = this.mOnDialogButtonOnClickListener;
        if (onDialogButtonOnClickListener != null) {
            onDialogButtonOnClickListener.cancelBtn();
        }
        dismiss();
    }

    public void setTitle(String str) {
        this.dialogTitle = str;
    }

    public void setOnDialogButtonOnClickListener(OnDialogButtonOnClickListener onDialogButtonOnClickListener) {
        this.mOnDialogButtonOnClickListener = onDialogButtonOnClickListener;
    }
}
