package mc.csst.com.selfchassis.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.ciot.sentrymove.R;

public abstract class LayoutVisualLabelCalibrationModeBinding extends ViewDataBinding {
    public final ConstraintLayout clLabelCamera;
    public final ConstraintLayout labelInfoCl;
    public final AppCompatImageView labelInfoIconIv;
    public final AppCompatImageView labelInfoIv;
    public final AppCompatTextView labelInfoTv;
    public final RecyclerView labelListRv;
    public final ConstraintLayout labelRootCl;
    public final TextView tvLabelCameraWindow;
    public final Switch vlcmSwitchLabelCameraWindow;

    protected LayoutVisualLabelCalibrationModeBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, AppCompatImageView appCompatImageView, AppCompatImageView appCompatImageView2, AppCompatTextView appCompatTextView, RecyclerView recyclerView, ConstraintLayout constraintLayout3, TextView textView, Switch switchR) {
        super(obj, view, i);
        this.clLabelCamera = constraintLayout;
        this.labelInfoCl = constraintLayout2;
        this.labelInfoIconIv = appCompatImageView;
        this.labelInfoIv = appCompatImageView2;
        this.labelInfoTv = appCompatTextView;
        this.labelListRv = recyclerView;
        this.labelRootCl = constraintLayout3;
        this.tvLabelCameraWindow = textView;
        this.vlcmSwitchLabelCameraWindow = switchR;
    }

    public static LayoutVisualLabelCalibrationModeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutVisualLabelCalibrationModeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LayoutVisualLabelCalibrationModeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_visual_label_calibration_mode, viewGroup, z, obj);
    }

    public static LayoutVisualLabelCalibrationModeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutVisualLabelCalibrationModeBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LayoutVisualLabelCalibrationModeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_visual_label_calibration_mode, (ViewGroup) null, false, obj);
    }

    public static LayoutVisualLabelCalibrationModeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutVisualLabelCalibrationModeBinding bind(View view, Object obj) {
        return (LayoutVisualLabelCalibrationModeBinding) bind(obj, view, R.layout.layout_visual_label_calibration_mode);
    }
}
