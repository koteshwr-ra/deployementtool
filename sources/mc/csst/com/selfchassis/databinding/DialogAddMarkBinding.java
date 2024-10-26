package mc.csst.com.selfchassis.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ciot.sentrymove.R;

public abstract class DialogAddMarkBinding extends ViewDataBinding {
    public final DialogBottomBinding buttons;
    public final AppCompatTextView dialogTitle;
    public final AppCompatEditText locationNameEt;
    public final AppCompatTextView locationNameTv;
    public final AppCompatTextView locationTv;
    public final AppCompatTextView locationTypeTv;
    public final AppCompatRadioButton pointOutwardOfChargingPile;
    public final AppCompatRadioButton pointTypeAccessControlRb;
    public final AppCompatRadioButton pointTypeChargeRb;
    public final AppCompatRadioButton pointTypeGateRb;
    public final AppCompatRadioButton pointTypeInTheLiftRb;
    public final AppCompatRadioButton pointTypeNormalRb;
    public final AppCompatRadioButton pointTypeOutsideTheLiftRb;
    public final AppCompatRadioButton pointTypeStopRb;
    public final AppCompatRadioButton pointTypeWaitRb;
    public final RelativeLayout rootRl;
    public final AppCompatEditText tEt;
    public final AppCompatTextView tTv;
    public final ConstraintLayout titleCl;
    public final AppCompatEditText xEt;
    public final AppCompatTextView xTv;
    public final AppCompatEditText yEt;
    public final AppCompatTextView yTv;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected DialogAddMarkBinding(Object obj, View view, int i, DialogBottomBinding dialogBottomBinding, AppCompatTextView appCompatTextView, AppCompatEditText appCompatEditText, AppCompatTextView appCompatTextView2, AppCompatTextView appCompatTextView3, AppCompatTextView appCompatTextView4, AppCompatRadioButton appCompatRadioButton, AppCompatRadioButton appCompatRadioButton2, AppCompatRadioButton appCompatRadioButton3, AppCompatRadioButton appCompatRadioButton4, AppCompatRadioButton appCompatRadioButton5, AppCompatRadioButton appCompatRadioButton6, AppCompatRadioButton appCompatRadioButton7, AppCompatRadioButton appCompatRadioButton8, AppCompatRadioButton appCompatRadioButton9, RelativeLayout relativeLayout, AppCompatEditText appCompatEditText2, AppCompatTextView appCompatTextView5, ConstraintLayout constraintLayout, AppCompatEditText appCompatEditText3, AppCompatTextView appCompatTextView6, AppCompatEditText appCompatEditText4, AppCompatTextView appCompatTextView7) {
        super(obj, view, i);
        this.buttons = dialogBottomBinding;
        setContainedBinding(dialogBottomBinding);
        this.dialogTitle = appCompatTextView;
        this.locationNameEt = appCompatEditText;
        this.locationNameTv = appCompatTextView2;
        this.locationTv = appCompatTextView3;
        this.locationTypeTv = appCompatTextView4;
        this.pointOutwardOfChargingPile = appCompatRadioButton;
        this.pointTypeAccessControlRb = appCompatRadioButton2;
        this.pointTypeChargeRb = appCompatRadioButton3;
        this.pointTypeGateRb = appCompatRadioButton4;
        this.pointTypeInTheLiftRb = appCompatRadioButton5;
        this.pointTypeNormalRb = appCompatRadioButton6;
        this.pointTypeOutsideTheLiftRb = appCompatRadioButton7;
        this.pointTypeStopRb = appCompatRadioButton8;
        this.pointTypeWaitRb = appCompatRadioButton9;
        this.rootRl = relativeLayout;
        this.tEt = appCompatEditText2;
        this.tTv = appCompatTextView5;
        this.titleCl = constraintLayout;
        this.xEt = appCompatEditText3;
        this.xTv = appCompatTextView6;
        this.yEt = appCompatEditText4;
        this.yTv = appCompatTextView7;
    }

    public static DialogAddMarkBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogAddMarkBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DialogAddMarkBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_add_mark, viewGroup, z, obj);
    }

    public static DialogAddMarkBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogAddMarkBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DialogAddMarkBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_add_mark, (ViewGroup) null, false, obj);
    }

    public static DialogAddMarkBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogAddMarkBinding bind(View view, Object obj) {
        return (DialogAddMarkBinding) bind(obj, view, R.layout.dialog_add_mark);
    }
}
