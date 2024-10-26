package mc.csst.com.selfchassis.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.ciot.sentrymove.R;

public abstract class DialogSwitchingPointBinding extends ViewDataBinding {
    public final RecyclerView buildNameRv;
    public final AppCompatButton exitBtn;
    public final SwipeRefreshLayout floorSwipeRefresh;
    public final RecyclerView markerRecycle;
    public final AppCompatTextView pointTitleTv;
    public final LinearLayoutCompat rvLin;
    public final LinearLayoutCompat titleLin;

    protected DialogSwitchingPointBinding(Object obj, View view, int i, RecyclerView recyclerView, AppCompatButton appCompatButton, SwipeRefreshLayout swipeRefreshLayout, RecyclerView recyclerView2, AppCompatTextView appCompatTextView, LinearLayoutCompat linearLayoutCompat, LinearLayoutCompat linearLayoutCompat2) {
        super(obj, view, i);
        this.buildNameRv = recyclerView;
        this.exitBtn = appCompatButton;
        this.floorSwipeRefresh = swipeRefreshLayout;
        this.markerRecycle = recyclerView2;
        this.pointTitleTv = appCompatTextView;
        this.rvLin = linearLayoutCompat;
        this.titleLin = linearLayoutCompat2;
    }

    public static DialogSwitchingPointBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogSwitchingPointBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DialogSwitchingPointBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_switching_point, viewGroup, z, obj);
    }

    public static DialogSwitchingPointBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogSwitchingPointBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DialogSwitchingPointBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_switching_point, (ViewGroup) null, false, obj);
    }

    public static DialogSwitchingPointBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogSwitchingPointBinding bind(View view, Object obj) {
        return (DialogSwitchingPointBinding) bind(obj, view, R.layout.dialog_switching_point);
    }
}
