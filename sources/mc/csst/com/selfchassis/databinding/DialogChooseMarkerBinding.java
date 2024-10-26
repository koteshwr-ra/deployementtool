package mc.csst.com.selfchassis.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.ciot.sentrymove.R;

public abstract class DialogChooseMarkerBinding extends ViewDataBinding {
    public final DialogBottomBinding buttons;
    public final RecyclerView markerRecycle;
    public final SwipeRefreshLayout markerSwipeRefresh;
    public final RelativeLayout rootRl;
    public final TextView titleTv;

    protected DialogChooseMarkerBinding(Object obj, View view, int i, DialogBottomBinding dialogBottomBinding, RecyclerView recyclerView, SwipeRefreshLayout swipeRefreshLayout, RelativeLayout relativeLayout, TextView textView) {
        super(obj, view, i);
        this.buttons = dialogBottomBinding;
        setContainedBinding(dialogBottomBinding);
        this.markerRecycle = recyclerView;
        this.markerSwipeRefresh = swipeRefreshLayout;
        this.rootRl = relativeLayout;
        this.titleTv = textView;
    }

    public static DialogChooseMarkerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogChooseMarkerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DialogChooseMarkerBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_choose_marker, viewGroup, z, obj);
    }

    public static DialogChooseMarkerBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogChooseMarkerBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DialogChooseMarkerBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_choose_marker, (ViewGroup) null, false, obj);
    }

    public static DialogChooseMarkerBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogChooseMarkerBinding bind(View view, Object obj) {
        return (DialogChooseMarkerBinding) bind(obj, view, R.layout.dialog_choose_marker);
    }
}
