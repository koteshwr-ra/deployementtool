package mc.csst.com.selfchassis.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ciot.sentrymove.R;

public abstract class DialogBuildMapBinding extends ViewDataBinding {
    public final DialogBottomBinding buttons;
    public final AppCompatRadioButton dialogMapBuildTypeAssistRb;
    public final AppCompatRadioButton dialogMapBuildTypeBigRb;
    public final AppCompatRadioButton dialogMapBuildTypeLikeRb;
    public final AppCompatRadioButton dialogMapBuildTypeNormalRb;
    public final RadioGroup dialogMapBuildTypeRg;
    public final AppCompatEditText dialogMapFloorEt;
    public final AppCompatTextView dialogMapFloorTv;
    public final AppCompatEditText dialogMapNameEt;
    public final AppCompatTextView dialogMapNameTv;
    public final AppCompatTextView dialogTitle;
    public final RelativeLayout rootRl;
    public final ConstraintLayout titleCl;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected DialogBuildMapBinding(Object obj, View view, int i, DialogBottomBinding dialogBottomBinding, AppCompatRadioButton appCompatRadioButton, AppCompatRadioButton appCompatRadioButton2, AppCompatRadioButton appCompatRadioButton3, AppCompatRadioButton appCompatRadioButton4, RadioGroup radioGroup, AppCompatEditText appCompatEditText, AppCompatTextView appCompatTextView, AppCompatEditText appCompatEditText2, AppCompatTextView appCompatTextView2, AppCompatTextView appCompatTextView3, RelativeLayout relativeLayout, ConstraintLayout constraintLayout) {
        super(obj, view, i);
        this.buttons = dialogBottomBinding;
        setContainedBinding(dialogBottomBinding);
        this.dialogMapBuildTypeAssistRb = appCompatRadioButton;
        this.dialogMapBuildTypeBigRb = appCompatRadioButton2;
        this.dialogMapBuildTypeLikeRb = appCompatRadioButton3;
        this.dialogMapBuildTypeNormalRb = appCompatRadioButton4;
        this.dialogMapBuildTypeRg = radioGroup;
        this.dialogMapFloorEt = appCompatEditText;
        this.dialogMapFloorTv = appCompatTextView;
        this.dialogMapNameEt = appCompatEditText2;
        this.dialogMapNameTv = appCompatTextView2;
        this.dialogTitle = appCompatTextView3;
        this.rootRl = relativeLayout;
        this.titleCl = constraintLayout;
    }

    public static DialogBuildMapBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogBuildMapBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DialogBuildMapBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_build_map, viewGroup, z, obj);
    }

    public static DialogBuildMapBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogBuildMapBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DialogBuildMapBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_build_map, (ViewGroup) null, false, obj);
    }

    public static DialogBuildMapBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogBuildMapBinding bind(View view, Object obj) {
        return (DialogBuildMapBinding) bind(obj, view, R.layout.dialog_build_map);
    }
}
