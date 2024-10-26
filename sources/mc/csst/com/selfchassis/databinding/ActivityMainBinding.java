package mc.csst.com.selfchassis.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.ciot.sentrymove.R;
import mc.csst.com.selfchassis.ui.widget.AddMarkModeView;
import mc.csst.com.selfchassis.utils.bean.ShowSelfChassisBean;
import mc.csst.com.selfchassis.utils.view.IconButton;
import mc.csst.com.selfchassis.utils.view.LayerSelectView;
import mc.csst.com.selfchassis.utils.view.ScaleMapView;
import mc.csst.com.selfchassis.utils.view.map.MapRlView;

public abstract class ActivityMainBinding extends ViewDataBinding {
    public final AddMarkModeView addRandomMarkView;
    public final ConstraintLayout clRobotList;
    public final LayoutCollectModeBinding includeCollecting;
    public final LayoutMainDirectionControlBinding includeDirectionControl;
    public final LayoutMainHeadBinding includeHead;
    public final LayoutNavBinding includeNav;
    public final LayoutTrackingBinding includeTracking;
    public final LayoutVisualLabelCalibrationModeBinding includeVisualLabel;
    public final AppCompatImageView ivRobotList;
    public final LinearLayout llRobotList;
    @Bindable
    protected ShowSelfChassisBean mShowChassisState;
    public final AppCompatTextView mainAreaContentTv;
    public final AppCompatTextView mainAreaTitleTv;
    public final ConstraintLayout mainBottomBtnCl;
    public final AppCompatImageView mainBottomLayerIv;
    public final AppCompatImageView mainBottomLockIv;
    public final AppCompatImageView mainBottomNavIv;
    public final AppCompatImageView mainBottomRestoreIv;
    public final AppCompatImageView mainBottomStopIv;
    public final AppCompatImageView mainBrushBigIv;
    public final RelativeLayout mainBrushBigRl;
    public final AppCompatImageView mainBrushMediumIv;
    public final RelativeLayout mainBrushMediumRl;
    public final AppCompatImageView mainBrushSmallIv;
    public final RelativeLayout mainBrushSmallRl;
    public final LinearLayout mainBuildFloorLl;
    public final ConstraintLayout mainBuildInfoCl;
    public final AppCompatImageView mainBuildInfoIconIv;
    public final RecyclerView mainBuildInfoListRv;
    public final AppCompatTextView mainBuildInfoTv;
    public final RecyclerView mainBuildNameRv;
    public final ConstraintLayout mainClCamPointCloud;
    public final RecyclerView mainFloorRv;
    public final AppCompatImageView mainImgCamPointCloudBg;
    public final LayerSelectView mainLayerView;
    public final AppCompatImageView mainLeftCleanDrawAreaIv;
    public final LinearLayout mainLeftToolBrushCl;
    public final RecyclerView mainLeftToolRv;
    public final ConstraintLayout mainLeftToolShapeCl;
    public final AppCompatImageView mainLineIv;
    public final ConstraintLayout mainMapArea;
    public final MapRlView mainMapControlMrv;
    public final ConstraintLayout mainPointInfoCl;
    public final FrameLayout mainPointInfoFl;
    public final AppCompatImageView mainPointInfoIconIv;
    public final AppCompatImageView mainPointInfoIv;
    public final AppCompatTextView mainPointInfoTv;
    public final LinearLayout mainPointListLl;
    public final RecyclerView mainPointListRv;
    public final SwipeRefreshLayout mainPointSwipeRefresh;
    public final ConstraintLayout mainPointTypeCl;
    public final AppCompatImageView mainRectangleIv;
    public final IconButton mainRightBtnCancelNavIb;
    public final IconButton mainRightBtnLockIb;
    public final IconButton mainRightBtnOkIb;
    public final RecyclerView mainRightToolRl;
    public final ScaleMapView mainScale;
    public final AppCompatTextView mainTipsTv;
    public final RecyclerView mainTopMenuRv;
    public final LinearLayout mainZoomCl;
    public final AppCompatTextView mainZoomInTv;
    public final AppCompatTextView mainZoomOutTv;
    public final AppCompatImageView rightAreaArrowIv;
    public final ConstraintLayout rightAreaListCl;
    public final RecyclerView rightAreaListRv;
    public final ConstraintLayout rightAreaListTitleCl;
    public final ConstraintLayout rightAreaRootCl;
    public final AppCompatTextView rightAreaTitleNameTv;
    public final LinearLayoutCompat robotListContainer;
    public final RecyclerView rvRobotList;
    public final AppCompatTextView tvRobotList;

    public abstract void setShowChassisState(ShowSelfChassisBean showSelfChassisBean);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected ActivityMainBinding(Object obj, View view, int i, AddMarkModeView addMarkModeView, ConstraintLayout constraintLayout, LayoutCollectModeBinding layoutCollectModeBinding, LayoutMainDirectionControlBinding layoutMainDirectionControlBinding, LayoutMainHeadBinding layoutMainHeadBinding, LayoutNavBinding layoutNavBinding, LayoutTrackingBinding layoutTrackingBinding, LayoutVisualLabelCalibrationModeBinding layoutVisualLabelCalibrationModeBinding, AppCompatImageView appCompatImageView, LinearLayout linearLayout, AppCompatTextView appCompatTextView, AppCompatTextView appCompatTextView2, ConstraintLayout constraintLayout2, AppCompatImageView appCompatImageView2, AppCompatImageView appCompatImageView3, AppCompatImageView appCompatImageView4, AppCompatImageView appCompatImageView5, AppCompatImageView appCompatImageView6, AppCompatImageView appCompatImageView7, RelativeLayout relativeLayout, AppCompatImageView appCompatImageView8, RelativeLayout relativeLayout2, AppCompatImageView appCompatImageView9, RelativeLayout relativeLayout3, LinearLayout linearLayout2, ConstraintLayout constraintLayout3, AppCompatImageView appCompatImageView10, RecyclerView recyclerView, AppCompatTextView appCompatTextView3, RecyclerView recyclerView2, ConstraintLayout constraintLayout4, RecyclerView recyclerView3, AppCompatImageView appCompatImageView11, LayerSelectView layerSelectView, AppCompatImageView appCompatImageView12, LinearLayout linearLayout3, RecyclerView recyclerView4, ConstraintLayout constraintLayout5, AppCompatImageView appCompatImageView13, ConstraintLayout constraintLayout6, MapRlView mapRlView, ConstraintLayout constraintLayout7, FrameLayout frameLayout, AppCompatImageView appCompatImageView14, AppCompatImageView appCompatImageView15, AppCompatTextView appCompatTextView4, LinearLayout linearLayout4, RecyclerView recyclerView5, SwipeRefreshLayout swipeRefreshLayout, ConstraintLayout constraintLayout8, AppCompatImageView appCompatImageView16, IconButton iconButton, IconButton iconButton2, IconButton iconButton3, RecyclerView recyclerView6, ScaleMapView scaleMapView, AppCompatTextView appCompatTextView5, RecyclerView recyclerView7, LinearLayout linearLayout5, AppCompatTextView appCompatTextView6, AppCompatTextView appCompatTextView7, AppCompatImageView appCompatImageView17, ConstraintLayout constraintLayout9, RecyclerView recyclerView8, ConstraintLayout constraintLayout10, ConstraintLayout constraintLayout11, AppCompatTextView appCompatTextView8, LinearLayoutCompat linearLayoutCompat, RecyclerView recyclerView9, AppCompatTextView appCompatTextView9) {
        super(obj, view, i);
        LayoutMainHeadBinding layoutMainHeadBinding2 = layoutMainHeadBinding;
        LayoutNavBinding layoutNavBinding2 = layoutNavBinding;
        LayoutTrackingBinding layoutTrackingBinding2 = layoutTrackingBinding;
        LayoutVisualLabelCalibrationModeBinding layoutVisualLabelCalibrationModeBinding2 = layoutVisualLabelCalibrationModeBinding;
        this.addRandomMarkView = addMarkModeView;
        this.clRobotList = constraintLayout;
        this.includeCollecting = layoutCollectModeBinding;
        setContainedBinding(layoutCollectModeBinding);
        this.includeDirectionControl = layoutMainDirectionControlBinding;
        setContainedBinding(layoutMainDirectionControlBinding);
        this.includeHead = layoutMainHeadBinding2;
        setContainedBinding(layoutMainHeadBinding2);
        this.includeNav = layoutNavBinding2;
        setContainedBinding(layoutNavBinding2);
        this.includeTracking = layoutTrackingBinding2;
        setContainedBinding(layoutTrackingBinding2);
        this.includeVisualLabel = layoutVisualLabelCalibrationModeBinding2;
        setContainedBinding(layoutVisualLabelCalibrationModeBinding2);
        this.ivRobotList = appCompatImageView;
        this.llRobotList = linearLayout;
        this.mainAreaContentTv = appCompatTextView;
        this.mainAreaTitleTv = appCompatTextView2;
        this.mainBottomBtnCl = constraintLayout2;
        this.mainBottomLayerIv = appCompatImageView2;
        this.mainBottomLockIv = appCompatImageView3;
        this.mainBottomNavIv = appCompatImageView4;
        this.mainBottomRestoreIv = appCompatImageView5;
        this.mainBottomStopIv = appCompatImageView6;
        this.mainBrushBigIv = appCompatImageView7;
        this.mainBrushBigRl = relativeLayout;
        this.mainBrushMediumIv = appCompatImageView8;
        this.mainBrushMediumRl = relativeLayout2;
        this.mainBrushSmallIv = appCompatImageView9;
        this.mainBrushSmallRl = relativeLayout3;
        this.mainBuildFloorLl = linearLayout2;
        this.mainBuildInfoCl = constraintLayout3;
        this.mainBuildInfoIconIv = appCompatImageView10;
        this.mainBuildInfoListRv = recyclerView;
        this.mainBuildInfoTv = appCompatTextView3;
        this.mainBuildNameRv = recyclerView2;
        this.mainClCamPointCloud = constraintLayout4;
        this.mainFloorRv = recyclerView3;
        this.mainImgCamPointCloudBg = appCompatImageView11;
        this.mainLayerView = layerSelectView;
        this.mainLeftCleanDrawAreaIv = appCompatImageView12;
        this.mainLeftToolBrushCl = linearLayout3;
        this.mainLeftToolRv = recyclerView4;
        this.mainLeftToolShapeCl = constraintLayout5;
        this.mainLineIv = appCompatImageView13;
        this.mainMapArea = constraintLayout6;
        this.mainMapControlMrv = mapRlView;
        this.mainPointInfoCl = constraintLayout7;
        this.mainPointInfoFl = frameLayout;
        this.mainPointInfoIconIv = appCompatImageView14;
        this.mainPointInfoIv = appCompatImageView15;
        this.mainPointInfoTv = appCompatTextView4;
        this.mainPointListLl = linearLayout4;
        this.mainPointListRv = recyclerView5;
        this.mainPointSwipeRefresh = swipeRefreshLayout;
        this.mainPointTypeCl = constraintLayout8;
        this.mainRectangleIv = appCompatImageView16;
        this.mainRightBtnCancelNavIb = iconButton;
        this.mainRightBtnLockIb = iconButton2;
        this.mainRightBtnOkIb = iconButton3;
        this.mainRightToolRl = recyclerView6;
        this.mainScale = scaleMapView;
        this.mainTipsTv = appCompatTextView5;
        this.mainTopMenuRv = recyclerView7;
        this.mainZoomCl = linearLayout5;
        this.mainZoomInTv = appCompatTextView6;
        this.mainZoomOutTv = appCompatTextView7;
        this.rightAreaArrowIv = appCompatImageView17;
        this.rightAreaListCl = constraintLayout9;
        this.rightAreaListRv = recyclerView8;
        this.rightAreaListTitleCl = constraintLayout10;
        this.rightAreaRootCl = constraintLayout11;
        this.rightAreaTitleNameTv = appCompatTextView8;
        this.robotListContainer = linearLayoutCompat;
        this.rvRobotList = recyclerView9;
        this.tvRobotList = appCompatTextView9;
    }

    public ShowSelfChassisBean getShowChassisState() {
        return this.mShowChassisState;
    }

    public static ActivityMainBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityMainBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_main, viewGroup, z, obj);
    }

    public static ActivityMainBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityMainBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_main, (ViewGroup) null, false, obj);
    }

    public static ActivityMainBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainBinding bind(View view, Object obj) {
        return (ActivityMainBinding) bind(obj, view, R.layout.activity_main);
    }
}
