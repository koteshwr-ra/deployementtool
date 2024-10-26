package mc.csst.com.selfchassis.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Switch;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.ciot.sentrymove.R;
import mc.csst.com.selfchassis.ui.widget.RadioButtonSilent;

public abstract class FragmentSetConfigBinding extends ViewDataBinding {
    public final AppCompatButton btnSaveSet;
    public final ConstraintLayout clNavMode;
    public final ConstraintLayout clOperation;
    public final ConstraintLayout clSensor;
    public final ConstraintLayout clSpeed;
    public final ConstraintLayout clTravel;
    public final ViewNavParamConfigBinding includeNavParamConfig;
    public final ViewOtherConfigBinding includeOtherConfig;
    public final AppCompatRadioButton rbBalance;
    public final AppCompatRadioButton rbEfficiency;
    public final AppCompatRadioButton rbKeyEight;
    public final AppCompatRadioButton rbKeyFour;
    public final AppCompatRadioButton rbNavAuto;
    public final AppCompatRadioButton rbNavVirtual;
    public final AppCompatRadioButton rbSecurity;
    public final RadioGroup rgControl;
    public final RadioGroup rgNavMode;
    public final RadioButtonSilent rgTravel;
    public final RecyclerView rvSensor;
    public final AppCompatSeekBar sbSpeedConfig;
    public final NestedScrollView svConfig;
    public final Switch switchManual;
    public final Switch switchPushMode;
    public final AppCompatTextView tvOperation;
    public final AppCompatTextView tvSpeedConfig;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected FragmentSetConfigBinding(Object obj, View view, int i, AppCompatButton appCompatButton, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, ViewNavParamConfigBinding viewNavParamConfigBinding, ViewOtherConfigBinding viewOtherConfigBinding, AppCompatRadioButton appCompatRadioButton, AppCompatRadioButton appCompatRadioButton2, AppCompatRadioButton appCompatRadioButton3, AppCompatRadioButton appCompatRadioButton4, AppCompatRadioButton appCompatRadioButton5, AppCompatRadioButton appCompatRadioButton6, AppCompatRadioButton appCompatRadioButton7, RadioGroup radioGroup, RadioGroup radioGroup2, RadioButtonSilent radioButtonSilent, RecyclerView recyclerView, AppCompatSeekBar appCompatSeekBar, NestedScrollView nestedScrollView, Switch switchR, Switch switchR2, AppCompatTextView appCompatTextView, AppCompatTextView appCompatTextView2) {
        super(obj, view, i);
        this.btnSaveSet = appCompatButton;
        this.clNavMode = constraintLayout;
        this.clOperation = constraintLayout2;
        this.clSensor = constraintLayout3;
        this.clSpeed = constraintLayout4;
        this.clTravel = constraintLayout5;
        this.includeNavParamConfig = viewNavParamConfigBinding;
        setContainedBinding(viewNavParamConfigBinding);
        this.includeOtherConfig = viewOtherConfigBinding;
        setContainedBinding(viewOtherConfigBinding);
        this.rbBalance = appCompatRadioButton;
        this.rbEfficiency = appCompatRadioButton2;
        this.rbKeyEight = appCompatRadioButton3;
        this.rbKeyFour = appCompatRadioButton4;
        this.rbNavAuto = appCompatRadioButton5;
        this.rbNavVirtual = appCompatRadioButton6;
        this.rbSecurity = appCompatRadioButton7;
        this.rgControl = radioGroup;
        this.rgNavMode = radioGroup2;
        this.rgTravel = radioButtonSilent;
        this.rvSensor = recyclerView;
        this.sbSpeedConfig = appCompatSeekBar;
        this.svConfig = nestedScrollView;
        this.switchManual = switchR;
        this.switchPushMode = switchR2;
        this.tvOperation = appCompatTextView;
        this.tvSpeedConfig = appCompatTextView2;
    }

    public static FragmentSetConfigBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSetConfigBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentSetConfigBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_set_config, viewGroup, z, obj);
    }

    public static FragmentSetConfigBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSetConfigBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentSetConfigBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_set_config, (ViewGroup) null, false, obj);
    }

    public static FragmentSetConfigBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSetConfigBinding bind(View view, Object obj) {
        return (FragmentSetConfigBinding) bind(obj, view, R.layout.fragment_set_config);
    }
}
