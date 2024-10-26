package mc.csst.com.selfchassis.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ciot.sentrymove.R;
import mc.csst.com.selfchassis.ui.widget.disc.PointRotateView;
import mc.csst.com.selfchassis.utils.view.FlowRadioGroup;

public abstract class DialogAddMarkRightBinding extends ViewDataBinding {
    public final RelativeLayout boxButton;
    public final AppCompatButton btnSelectNegative;
    public final AppCompatButton btnSelectPositive;
    public final AppCompatTextView dialogTitle;
    public final FlowRadioGroup flowRG1;
    public final FlowRadioGroup flowRG2;
    public final AppCompatEditText locationNameEt;
    public final AppCompatTextView locationNameTv;
    public final AppCompatTextView locationTv;
    public final AppCompatTextView locationTypeTv;
    public final AppCompatRadioButton pointOutwardOfChargingPile;
    public final PointRotateView pointRotateView;
    public final AppCompatRadioButton pointTypeAccessControlRb;
    public final AppCompatRadioButton pointTypeChargeRb;
    public final AppCompatRadioButton pointTypeGateRb;
    public final AppCompatRadioButton pointTypeInTheLiftRb;
    public final AppCompatRadioButton pointTypeNormalRb;
    public final AppCompatRadioButton pointTypeOutsideTheLiftRb;
    public final AppCompatRadioButton pointTypeStopRb;
    public final AppCompatRadioButton pointTypeWaitRb;
    public final RelativeLayout rootRl;
    public final AppCompatTextView tEt;
    public final AppCompatTextView tTv;
    public final AppCompatTextView xEt;
    public final AppCompatTextView xTv;
    public final AppCompatTextView yEt;
    public final AppCompatTextView yTv;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected DialogAddMarkRightBinding(Object obj, View view, int i, RelativeLayout relativeLayout, AppCompatButton appCompatButton, AppCompatButton appCompatButton2, AppCompatTextView appCompatTextView, FlowRadioGroup flowRadioGroup, FlowRadioGroup flowRadioGroup2, AppCompatEditText appCompatEditText, AppCompatTextView appCompatTextView2, AppCompatTextView appCompatTextView3, AppCompatTextView appCompatTextView4, AppCompatRadioButton appCompatRadioButton, PointRotateView pointRotateView2, AppCompatRadioButton appCompatRadioButton2, AppCompatRadioButton appCompatRadioButton3, AppCompatRadioButton appCompatRadioButton4, AppCompatRadioButton appCompatRadioButton5, AppCompatRadioButton appCompatRadioButton6, AppCompatRadioButton appCompatRadioButton7, AppCompatRadioButton appCompatRadioButton8, AppCompatRadioButton appCompatRadioButton9, RelativeLayout relativeLayout2, AppCompatTextView appCompatTextView5, AppCompatTextView appCompatTextView6, AppCompatTextView appCompatTextView7, AppCompatTextView appCompatTextView8, AppCompatTextView appCompatTextView9, AppCompatTextView appCompatTextView10) {
        super(obj, view, i);
        this.boxButton = relativeLayout;
        this.btnSelectNegative = appCompatButton;
        this.btnSelectPositive = appCompatButton2;
        this.dialogTitle = appCompatTextView;
        this.flowRG1 = flowRadioGroup;
        this.flowRG2 = flowRadioGroup2;
        this.locationNameEt = appCompatEditText;
        this.locationNameTv = appCompatTextView2;
        this.locationTv = appCompatTextView3;
        this.locationTypeTv = appCompatTextView4;
        this.pointOutwardOfChargingPile = appCompatRadioButton;
        this.pointRotateView = pointRotateView2;
        this.pointTypeAccessControlRb = appCompatRadioButton2;
        this.pointTypeChargeRb = appCompatRadioButton3;
        this.pointTypeGateRb = appCompatRadioButton4;
        this.pointTypeInTheLiftRb = appCompatRadioButton5;
        this.pointTypeNormalRb = appCompatRadioButton6;
        this.pointTypeOutsideTheLiftRb = appCompatRadioButton7;
        this.pointTypeStopRb = appCompatRadioButton8;
        this.pointTypeWaitRb = appCompatRadioButton9;
        this.rootRl = relativeLayout2;
        this.tEt = appCompatTextView5;
        this.tTv = appCompatTextView6;
        this.xEt = appCompatTextView7;
        this.xTv = appCompatTextView8;
        this.yEt = appCompatTextView9;
        this.yTv = appCompatTextView10;
    }

    public static DialogAddMarkRightBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogAddMarkRightBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DialogAddMarkRightBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_add_mark_right, viewGroup, z, obj);
    }

    public static DialogAddMarkRightBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogAddMarkRightBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DialogAddMarkRightBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_add_mark_right, (ViewGroup) null, false, obj);
    }

    public static DialogAddMarkRightBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogAddMarkRightBinding bind(View view, Object obj) {
        return (DialogAddMarkRightBinding) bind(obj, view, R.layout.dialog_add_mark_right);
    }
}
