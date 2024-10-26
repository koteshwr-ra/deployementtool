package mc.csst.com.selfchassis.ui.dialog;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import com.blankj.utilcode.util.AdaptScreenUtils;
import com.ciot.base.util.ContextUtil;
import com.ciot.sentrymove.R;
import java.util.ArrayList;
import mc.csst.com.selfchassis.databinding.DialogEditBinding;
import mc.csst.com.selfchassis.utils.InputFilterDotNum;
import mc.csst.com.selfchassis.utils.MyToastUtils;

public class EditDialog extends BaseDialog implements View.OnClickListener {
    private ArrayList<InputFilter> filterArrayList = new ArrayList<>();
    private DialogEditBinding mBinding;
    private String mContent = "";
    private String mHintTxt = "";
    private InputFilter[] mInputFilters = new InputFilter[0];
    private int mInputType = -1;
    private int mLimitDecimal = -1;
    private int mLimitLength = -1;
    private OnDialogButtonClickListener mOnDialogButtonClickListener;
    private boolean mShowSlowZoneSpeed = false;
    private String mSubTitle = "";
    private String mTitle = "";

    public interface OnDialogButtonClickListener {
        void onCanCel();

        boolean onConfirm(String str, float f);
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

    public static EditDialog newInstance() {
        EditDialog editDialog = new EditDialog();
        editDialog.setSize(AdaptScreenUtils.pt2Px(700.0f), AdaptScreenUtils.pt2Px(406.0f));
        return editDialog;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mBinding = (DialogEditBinding) DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_edit, viewGroup, false);
        initView();
        initListener();
        return this.mBinding.getRoot();
    }

    private void initListener() {
        this.mBinding.buttons.btnSelectNegative.setOnClickListener(this);
        this.mBinding.buttons.btnSelectPositive.setOnClickListener(this);
    }

    private void initView() {
        if (!TextUtils.isEmpty(this.mTitle)) {
            this.mBinding.tvTitle.setText(this.mTitle);
        }
        if (!TextUtils.isEmpty(this.mSubTitle)) {
            this.mBinding.tvSubTitle.setText(this.mSubTitle);
        }
        if (!TextUtils.isEmpty(this.mHintTxt)) {
            this.mBinding.letInput.setHint(this.mHintTxt);
        }
        if (!TextUtils.isEmpty(this.mContent)) {
            this.mBinding.letInput.setText(this.mContent);
        }
        if (this.mInputType != -1) {
            this.mBinding.letInput.setInputType(this.mInputType);
        }
        if (this.mLimitLength != -1) {
            this.filterArrayList.add(new InputFilter.LengthFilter(this.mLimitLength));
        }
        int i = this.mLimitDecimal;
        if (i != -1) {
            this.filterArrayList.add(new InputFilterDotNum(i));
        }
        ArrayList<InputFilter> arrayList = this.filterArrayList;
        if (arrayList != null && arrayList.size() > 0) {
            ArrayList<InputFilter> arrayList2 = this.filterArrayList;
            this.mInputFilters = (InputFilter[]) arrayList2.toArray(new InputFilter[arrayList2.size()]);
            this.mBinding.letInput.setFilters(this.mInputFilters);
        }
        showSlowZoneSpeed(this.mShowSlowZoneSpeed);
    }

    public EditDialog setTitle(String str) {
        this.mTitle = str;
        return this;
    }

    public EditDialog setContent(String str) {
        this.mContent = str;
        return this;
    }

    public EditDialog setSubTitle(String str) {
        this.mSubTitle = str;
        return this;
    }

    public EditDialog setHintTxt(String str) {
        this.mHintTxt = str;
        return this;
    }

    public EditDialog setShowSlowZoneSpeed(boolean z) {
        this.mShowSlowZoneSpeed = z;
        return this;
    }

    public EditDialog setLimitLength(int i) {
        this.mLimitLength = i;
        return this;
    }

    public EditDialog setLimitDecimal(int i) {
        this.mLimitDecimal = i;
        return this;
    }

    public EditDialog setInputType(int i) {
        this.mInputType = i;
        return this;
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btn_selectNegative) {
            OnDialogButtonClickListener onDialogButtonClickListener = this.mOnDialogButtonClickListener;
            if (onDialogButtonClickListener != null) {
                onDialogButtonClickListener.onCanCel();
                if (isAdded()) {
                    dismissAllowingStateLoss();
                }
            }
        } else if (view.getId() == R.id.btn_selectPositive) {
            float f = -1.0f;
            String trim = !TextUtils.isEmpty(this.mBinding.letInput.getText()) ? this.mBinding.letInput.getText().toString().trim() : "";
            if (!TextUtils.isEmpty(trim) || TextUtils.isEmpty(this.mHintTxt)) {
                if (this.mBinding.rbSzSpeedLow.isChecked()) {
                    f = 0.3f;
                } else if (this.mBinding.rbSzSpeedCentre.isChecked()) {
                    f = 0.5f;
                }
                OnDialogButtonClickListener onDialogButtonClickListener2 = this.mOnDialogButtonClickListener;
                if (onDialogButtonClickListener2 != null && !onDialogButtonClickListener2.onConfirm(trim, f) && isAdded()) {
                    dismissAllowingStateLoss();
                    return;
                }
                return;
            }
            MyToastUtils.showShort(ContextUtil.getContext(), this.mHintTxt);
        }
    }

    private void showSlowZoneSpeed(boolean z) {
        if (z) {
            this.mBinding.tvSlowZoneSpeedTitle.setVisibility(0);
            this.mBinding.rgSzSpeed.setVisibility(0);
            return;
        }
        this.mBinding.tvSlowZoneSpeedTitle.setVisibility(8);
        this.mBinding.rgSzSpeed.setVisibility(8);
    }

    public void setOnDialogButtonClickListener(OnDialogButtonClickListener onDialogButtonClickListener) {
        this.mOnDialogButtonClickListener = onDialogButtonClickListener;
    }
}
