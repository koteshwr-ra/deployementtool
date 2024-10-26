package mc.csst.com.selfchassis.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.ciot.sentrymove.R;
import mc.csst.com.selfchassis.ui.widget.DrawingSwitchView;
import mc.csst.com.selfchassis.ui.widget.LineTrackingListView;
import mc.csst.com.selfchassis.ui.widget.LineTrackingSettingView;
import mc.csst.com.selfchassis.ui.widget.MaxHeightRecyclerView;
import mc.csst.com.selfchassis.ui.widget.StartDrawingView;

public abstract class LayoutTrackingBinding extends ViewDataBinding {
    public final AppCompatTextView btnSure;
    public final DrawingSwitchView drawingSwitchView;
    public final ConstraintLayout intellectMultiLineCl;
    public final AppCompatTextView intellectMultiLineEmptyTv;
    public final RecyclerView intellectMultiLineRv;
    public final AppCompatTextView intellectMultiLineTitleTv;
    public final LinearLayout intellectMultiListLl;
    public final AppCompatImageView intellectMultiMoreIv;
    public final ConstraintLayout intellectMultiTitleCl;
    public final MaxHeightRecyclerView intellectPointListRv;
    public final LineTrackingListView lineTrackingListView;
    public final LineTrackingSettingView lineTrackingSettingView;
    public final StartDrawingView startDrawingView;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected LayoutTrackingBinding(Object obj, View view, int i, AppCompatTextView appCompatTextView, DrawingSwitchView drawingSwitchView2, ConstraintLayout constraintLayout, AppCompatTextView appCompatTextView2, RecyclerView recyclerView, AppCompatTextView appCompatTextView3, LinearLayout linearLayout, AppCompatImageView appCompatImageView, ConstraintLayout constraintLayout2, MaxHeightRecyclerView maxHeightRecyclerView, LineTrackingListView lineTrackingListView2, LineTrackingSettingView lineTrackingSettingView2, StartDrawingView startDrawingView2) {
        super(obj, view, i);
        this.btnSure = appCompatTextView;
        this.drawingSwitchView = drawingSwitchView2;
        this.intellectMultiLineCl = constraintLayout;
        this.intellectMultiLineEmptyTv = appCompatTextView2;
        this.intellectMultiLineRv = recyclerView;
        this.intellectMultiLineTitleTv = appCompatTextView3;
        this.intellectMultiListLl = linearLayout;
        this.intellectMultiMoreIv = appCompatImageView;
        this.intellectMultiTitleCl = constraintLayout2;
        this.intellectPointListRv = maxHeightRecyclerView;
        this.lineTrackingListView = lineTrackingListView2;
        this.lineTrackingSettingView = lineTrackingSettingView2;
        this.startDrawingView = startDrawingView2;
    }

    public static LayoutTrackingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutTrackingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LayoutTrackingBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_tracking, viewGroup, z, obj);
    }

    public static LayoutTrackingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutTrackingBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LayoutTrackingBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_tracking, (ViewGroup) null, false, obj);
    }

    public static LayoutTrackingBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutTrackingBinding bind(View view, Object obj) {
        return (LayoutTrackingBinding) bind(obj, view, R.layout.layout_tracking);
    }
}
