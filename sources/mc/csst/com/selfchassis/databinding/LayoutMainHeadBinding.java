package mc.csst.com.selfchassis.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ciot.sentrymove.R;
import mc.csst.com.selfchassis.utils.bean.ShowSelfChassisBean;
import mc.csst.com.selfchassis.utils.view.IconButton;

public abstract class LayoutMainHeadBinding extends ViewDataBinding {
    public final AppCompatImageView headBatteryIv;
    public final AppCompatTextView headBatteryTitleTv;
    public final AppCompatTextView headBatteryTv;
    public final ConstraintLayout headBuildingCl;
    public final AppCompatImageView headBuildingFloorIv;
    public final AppCompatTextView headBuildingFloorTv;
    public final IconButton headCancelIb;
    public final LinearLayoutCompat headCreateMapIb;
    public final AppCompatTextView headDeviceTitleTv;
    public final AppCompatTextView headDeviceTv;
    public final LinearLayout headLocationLl;
    public final AppCompatTextView headLocationTTv;
    public final AppCompatTextView headLocationTitleTv;
    public final AppCompatTextView headLocationXTv;
    public final AppCompatTextView headLocationYTv;
    public final AppCompatTextView headMotionTitleTv;
    public final AppCompatTextView headMotionTv;
    public final AppCompatImageView headOnDockStateIv;
    public final AppCompatTextView headOnDockStateTv;
    public final AppCompatTextView headSuitabilityTitleTv;
    public final AppCompatTextView headSuitabilityTv;
    public final ConstraintLayout headTitleCl;
    @Bindable
    protected ShowSelfChassisBean mShowChassisState;

    public abstract void setShowChassisState(ShowSelfChassisBean showSelfChassisBean);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected LayoutMainHeadBinding(Object obj, View view, int i, AppCompatImageView appCompatImageView, AppCompatTextView appCompatTextView, AppCompatTextView appCompatTextView2, ConstraintLayout constraintLayout, AppCompatImageView appCompatImageView2, AppCompatTextView appCompatTextView3, IconButton iconButton, LinearLayoutCompat linearLayoutCompat, AppCompatTextView appCompatTextView4, AppCompatTextView appCompatTextView5, LinearLayout linearLayout, AppCompatTextView appCompatTextView6, AppCompatTextView appCompatTextView7, AppCompatTextView appCompatTextView8, AppCompatTextView appCompatTextView9, AppCompatTextView appCompatTextView10, AppCompatTextView appCompatTextView11, AppCompatImageView appCompatImageView3, AppCompatTextView appCompatTextView12, AppCompatTextView appCompatTextView13, AppCompatTextView appCompatTextView14, ConstraintLayout constraintLayout2) {
        super(obj, view, i);
        this.headBatteryIv = appCompatImageView;
        this.headBatteryTitleTv = appCompatTextView;
        this.headBatteryTv = appCompatTextView2;
        this.headBuildingCl = constraintLayout;
        this.headBuildingFloorIv = appCompatImageView2;
        this.headBuildingFloorTv = appCompatTextView3;
        this.headCancelIb = iconButton;
        this.headCreateMapIb = linearLayoutCompat;
        this.headDeviceTitleTv = appCompatTextView4;
        this.headDeviceTv = appCompatTextView5;
        this.headLocationLl = linearLayout;
        this.headLocationTTv = appCompatTextView6;
        this.headLocationTitleTv = appCompatTextView7;
        this.headLocationXTv = appCompatTextView8;
        this.headLocationYTv = appCompatTextView9;
        this.headMotionTitleTv = appCompatTextView10;
        this.headMotionTv = appCompatTextView11;
        this.headOnDockStateIv = appCompatImageView3;
        this.headOnDockStateTv = appCompatTextView12;
        this.headSuitabilityTitleTv = appCompatTextView13;
        this.headSuitabilityTv = appCompatTextView14;
        this.headTitleCl = constraintLayout2;
    }

    public ShowSelfChassisBean getShowChassisState() {
        return this.mShowChassisState;
    }

    public static LayoutMainHeadBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutMainHeadBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LayoutMainHeadBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_main_head, viewGroup, z, obj);
    }

    public static LayoutMainHeadBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutMainHeadBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LayoutMainHeadBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_main_head, (ViewGroup) null, false, obj);
    }

    public static LayoutMainHeadBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutMainHeadBinding bind(View view, Object obj) {
        return (LayoutMainHeadBinding) bind(obj, view, R.layout.layout_main_head);
    }
}
