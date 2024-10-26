package mc.csst.com.selfchassis.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.ciot.sentrymove.R;

public abstract class DialogSelfCheckBinding extends ViewDataBinding {
    public final DialogBottomBinding buttons;
    public final RecyclerView checkRecycle;
    public final SwipeRefreshLayout checkSwipeRefresh;
    public final AppCompatTextView errorCodeTv;
    public final RelativeLayout rootRl;
    public final LinearLayout titleLin;
    public final TextView titleTv;

    protected DialogSelfCheckBinding(Object obj, View view, int i, DialogBottomBinding dialogBottomBinding, RecyclerView recyclerView, SwipeRefreshLayout swipeRefreshLayout, AppCompatTextView appCompatTextView, RelativeLayout relativeLayout, LinearLayout linearLayout, TextView textView) {
        super(obj, view, i);
        this.buttons = dialogBottomBinding;
        setContainedBinding(dialogBottomBinding);
        this.checkRecycle = recyclerView;
        this.checkSwipeRefresh = swipeRefreshLayout;
        this.errorCodeTv = appCompatTextView;
        this.rootRl = relativeLayout;
        this.titleLin = linearLayout;
        this.titleTv = textView;
    }

    public static DialogSelfCheckBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogSelfCheckBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DialogSelfCheckBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_self_check, viewGroup, z, obj);
    }

    public static DialogSelfCheckBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogSelfCheckBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DialogSelfCheckBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_self_check, (ViewGroup) null, false, obj);
    }

    public static DialogSelfCheckBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogSelfCheckBinding bind(View view, Object obj) {
        return (DialogSelfCheckBinding) bind(obj, view, R.layout.dialog_self_check);
    }
}
