package mc.csst.com.selfchassis.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Switch;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ciot.sentrymove.R;

public abstract class ViewOtherConfigBinding extends ViewDataBinding {
    public final AppCompatButton btnDepthCameraDownProbeCalibration;
    public final AppCompatButton btnDepthCameraUpProbeCalibration;
    public final AppCompatButton btnElevatorSet;
    public final AppCompatButton btnSetAsThreshold;
    public final ConstraintLayout clDepthCameraDownProbeCalibration;
    public final ConstraintLayout clDepthCameraUpProbeCalibration;
    public final ConstraintLayout clDoor;
    public final ConstraintLayout clElevator;
    public final ConstraintLayout clGate;
    public final ConstraintLayout clLabelCameraInstallType;
    public final ConstraintLayout clLocatedType;
    public final ConstraintLayout clOtherSet;
    public final ConstraintLayout clSecurityThreshold;
    public final ConstraintLayout clSecurityThresholdCalculate;
    public final ConstraintLayout clSwitchSmooth;
    public final ConstraintLayout clUltrasonicBeep;
    public final AppCompatImageView imgRecalculate;
    public final LinearLayoutCompat llUltrasonicBeep;
    public final AppCompatRadioButton rbD1;
    public final AppCompatRadioButton rbD3;
    public final AppCompatRadioButton rbLaserRadar;
    public final AppCompatRadioButton rbSpeedometer;
    public final RadioGroup rgLabelCameraInstallCoordinateType;
    public final RadioGroup rgLocatedType;
    public final AppCompatSeekBar sbThreshold;
    public final Switch switchSmooth;
    public final Switch switchUltrasonicBeep;
    public final AppCompatTextView tvCalculateThreshold;
    public final AppCompatTextView tvDoorControlSize;
    public final AppCompatTextView tvGateControlSize;
    public final AppCompatTextView tvRecalculate;
    public final AppCompatTextView tvSecurityThreshold;
    public final AppCompatTextView tvSetOther;
    public final AppCompatTextView tvUltrasonicBeep;
    public final AppCompatTextView tvUltrasonicBeepTip;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected ViewOtherConfigBinding(Object obj, View view, int i, AppCompatButton appCompatButton, AppCompatButton appCompatButton2, AppCompatButton appCompatButton3, AppCompatButton appCompatButton4, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, ConstraintLayout constraintLayout6, ConstraintLayout constraintLayout7, ConstraintLayout constraintLayout8, ConstraintLayout constraintLayout9, ConstraintLayout constraintLayout10, ConstraintLayout constraintLayout11, ConstraintLayout constraintLayout12, AppCompatImageView appCompatImageView, LinearLayoutCompat linearLayoutCompat, AppCompatRadioButton appCompatRadioButton, AppCompatRadioButton appCompatRadioButton2, AppCompatRadioButton appCompatRadioButton3, AppCompatRadioButton appCompatRadioButton4, RadioGroup radioGroup, RadioGroup radioGroup2, AppCompatSeekBar appCompatSeekBar, Switch switchR, Switch switchR2, AppCompatTextView appCompatTextView, AppCompatTextView appCompatTextView2, AppCompatTextView appCompatTextView3, AppCompatTextView appCompatTextView4, AppCompatTextView appCompatTextView5, AppCompatTextView appCompatTextView6, AppCompatTextView appCompatTextView7, AppCompatTextView appCompatTextView8) {
        super(obj, view, i);
        this.btnDepthCameraDownProbeCalibration = appCompatButton;
        this.btnDepthCameraUpProbeCalibration = appCompatButton2;
        this.btnElevatorSet = appCompatButton3;
        this.btnSetAsThreshold = appCompatButton4;
        this.clDepthCameraDownProbeCalibration = constraintLayout;
        this.clDepthCameraUpProbeCalibration = constraintLayout2;
        this.clDoor = constraintLayout3;
        this.clElevator = constraintLayout4;
        this.clGate = constraintLayout5;
        this.clLabelCameraInstallType = constraintLayout6;
        this.clLocatedType = constraintLayout7;
        this.clOtherSet = constraintLayout8;
        this.clSecurityThreshold = constraintLayout9;
        this.clSecurityThresholdCalculate = constraintLayout10;
        this.clSwitchSmooth = constraintLayout11;
        this.clUltrasonicBeep = constraintLayout12;
        this.imgRecalculate = appCompatImageView;
        this.llUltrasonicBeep = linearLayoutCompat;
        this.rbD1 = appCompatRadioButton;
        this.rbD3 = appCompatRadioButton2;
        this.rbLaserRadar = appCompatRadioButton3;
        this.rbSpeedometer = appCompatRadioButton4;
        this.rgLabelCameraInstallCoordinateType = radioGroup;
        this.rgLocatedType = radioGroup2;
        this.sbThreshold = appCompatSeekBar;
        this.switchSmooth = switchR;
        this.switchUltrasonicBeep = switchR2;
        this.tvCalculateThreshold = appCompatTextView;
        this.tvDoorControlSize = appCompatTextView2;
        this.tvGateControlSize = appCompatTextView3;
        this.tvRecalculate = appCompatTextView4;
        this.tvSecurityThreshold = appCompatTextView5;
        this.tvSetOther = appCompatTextView6;
        this.tvUltrasonicBeep = appCompatTextView7;
        this.tvUltrasonicBeepTip = appCompatTextView8;
    }

    public static ViewOtherConfigBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ViewOtherConfigBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ViewOtherConfigBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.view_other_config, viewGroup, z, obj);
    }

    public static ViewOtherConfigBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ViewOtherConfigBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ViewOtherConfigBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.view_other_config, (ViewGroup) null, false, obj);
    }

    public static ViewOtherConfigBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ViewOtherConfigBinding bind(View view, Object obj) {
        return (ViewOtherConfigBinding) bind(obj, view, R.layout.view_other_config);
    }
}
