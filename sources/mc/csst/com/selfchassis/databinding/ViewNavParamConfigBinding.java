package mc.csst.com.selfchassis.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ciot.sentrymove.R;

public abstract class ViewNavParamConfigBinding extends ViewDataBinding {
    public final ConstraintLayout clLaserRadarObstacleDetection;
    public final ConstraintLayout clNavFailureTime;
    public final ConstraintLayout clNavParamConfig;
    public final ConstraintLayout clPointToleranceSetting;
    public final ConstraintLayout clSafeStopDistance;
    public final LinearLayoutCompat llLaserRadarObstacleDetection;
    public final LinearLayoutCompat llNavFailureTime;
    public final LinearLayoutCompat llPointPosition;
    public final LinearLayoutCompat llPointRad;
    public final LinearLayoutCompat llSafeStopDistance;
    public final Switch switchLaserRadarObstacleDetection;
    public final AppCompatTextView tipUnit;
    public final AppCompatTextView titleLaserRadarObstacleDetection;
    public final AppCompatTextView tvLaserRadarObstacleDetection;
    public final AppCompatTextView tvNavFailureTime;
    public final AppCompatTextView tvNavParamConfig;
    public final AppCompatTextView tvPointPosition;
    public final AppCompatTextView tvPointPositionTitle;
    public final AppCompatTextView tvPointRad;
    public final AppCompatTextView tvPointRadSeparator;
    public final AppCompatTextView tvPointRadTitle;
    public final AppCompatTextView tvSafeStopDistance;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected ViewNavParamConfigBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, LinearLayoutCompat linearLayoutCompat, LinearLayoutCompat linearLayoutCompat2, LinearLayoutCompat linearLayoutCompat3, LinearLayoutCompat linearLayoutCompat4, LinearLayoutCompat linearLayoutCompat5, Switch switchR, AppCompatTextView appCompatTextView, AppCompatTextView appCompatTextView2, AppCompatTextView appCompatTextView3, AppCompatTextView appCompatTextView4, AppCompatTextView appCompatTextView5, AppCompatTextView appCompatTextView6, AppCompatTextView appCompatTextView7, AppCompatTextView appCompatTextView8, AppCompatTextView appCompatTextView9, AppCompatTextView appCompatTextView10, AppCompatTextView appCompatTextView11) {
        super(obj, view, i);
        this.clLaserRadarObstacleDetection = constraintLayout;
        this.clNavFailureTime = constraintLayout2;
        this.clNavParamConfig = constraintLayout3;
        this.clPointToleranceSetting = constraintLayout4;
        this.clSafeStopDistance = constraintLayout5;
        this.llLaserRadarObstacleDetection = linearLayoutCompat;
        this.llNavFailureTime = linearLayoutCompat2;
        this.llPointPosition = linearLayoutCompat3;
        this.llPointRad = linearLayoutCompat4;
        this.llSafeStopDistance = linearLayoutCompat5;
        this.switchLaserRadarObstacleDetection = switchR;
        this.tipUnit = appCompatTextView;
        this.titleLaserRadarObstacleDetection = appCompatTextView2;
        this.tvLaserRadarObstacleDetection = appCompatTextView3;
        this.tvNavFailureTime = appCompatTextView4;
        this.tvNavParamConfig = appCompatTextView5;
        this.tvPointPosition = appCompatTextView6;
        this.tvPointPositionTitle = appCompatTextView7;
        this.tvPointRad = appCompatTextView8;
        this.tvPointRadSeparator = appCompatTextView9;
        this.tvPointRadTitle = appCompatTextView10;
        this.tvSafeStopDistance = appCompatTextView11;
    }

    public static ViewNavParamConfigBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ViewNavParamConfigBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ViewNavParamConfigBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.view_nav_param_config, viewGroup, z, obj);
    }

    public static ViewNavParamConfigBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ViewNavParamConfigBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ViewNavParamConfigBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.view_nav_param_config, (ViewGroup) null, false, obj);
    }

    public static ViewNavParamConfigBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ViewNavParamConfigBinding bind(View view, Object obj) {
        return (ViewNavParamConfigBinding) bind(obj, view, R.layout.view_nav_param_config);
    }
}
