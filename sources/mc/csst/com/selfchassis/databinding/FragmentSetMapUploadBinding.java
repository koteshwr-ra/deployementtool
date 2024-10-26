package mc.csst.com.selfchassis.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ciot.sentrymove.R;

public abstract class FragmentSetMapUploadBinding extends ViewDataBinding {
    public final AppCompatButton btnSetAsThreshold;
    public final AppCompatButton btnUploadMap;
    public final ConstraintLayout clOtherSet;
    public final ConstraintLayout clSecurityThreshold;
    public final ConstraintLayout clSecurityThresholdCalculate;
    public final ConstraintLayout clSelectMapRoot;
    public final ConstraintLayout clUploadMapInfo;
    public final AppCompatImageView imgUploadMapArrow;
    public final AppCompatSeekBar sbThreshold;
    public final NestedScrollView svConfig;
    public final AppCompatTextView tvCalculateThreshold;
    public final AppCompatTextView tvRecalculate;
    public final AppCompatTextView tvSecurityThreshold;
    public final AppCompatTextView tvSetOther;
    public final AppCompatTextView tvUploadMapInfo;
    public final AppCompatTextView tvUploadMapTitle;
    public final View view;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected FragmentSetMapUploadBinding(Object obj, View view2, int i, AppCompatButton appCompatButton, AppCompatButton appCompatButton2, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, AppCompatImageView appCompatImageView, AppCompatSeekBar appCompatSeekBar, NestedScrollView nestedScrollView, AppCompatTextView appCompatTextView, AppCompatTextView appCompatTextView2, AppCompatTextView appCompatTextView3, AppCompatTextView appCompatTextView4, AppCompatTextView appCompatTextView5, AppCompatTextView appCompatTextView6, View view3) {
        super(obj, view2, i);
        this.btnSetAsThreshold = appCompatButton;
        this.btnUploadMap = appCompatButton2;
        this.clOtherSet = constraintLayout;
        this.clSecurityThreshold = constraintLayout2;
        this.clSecurityThresholdCalculate = constraintLayout3;
        this.clSelectMapRoot = constraintLayout4;
        this.clUploadMapInfo = constraintLayout5;
        this.imgUploadMapArrow = appCompatImageView;
        this.sbThreshold = appCompatSeekBar;
        this.svConfig = nestedScrollView;
        this.tvCalculateThreshold = appCompatTextView;
        this.tvRecalculate = appCompatTextView2;
        this.tvSecurityThreshold = appCompatTextView3;
        this.tvSetOther = appCompatTextView4;
        this.tvUploadMapInfo = appCompatTextView5;
        this.tvUploadMapTitle = appCompatTextView6;
        this.view = view3;
    }

    public static FragmentSetMapUploadBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSetMapUploadBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentSetMapUploadBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_set_map_upload, viewGroup, z, obj);
    }

    public static FragmentSetMapUploadBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSetMapUploadBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentSetMapUploadBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_set_map_upload, (ViewGroup) null, false, obj);
    }

    public static FragmentSetMapUploadBinding bind(View view2) {
        return bind(view2, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSetMapUploadBinding bind(View view2, Object obj) {
        return (FragmentSetMapUploadBinding) bind(obj, view2, R.layout.fragment_set_map_upload);
    }
}
