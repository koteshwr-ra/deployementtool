package mc.csst.com.selfchassis.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.ciot.sentrymove.R;

public abstract class ViewLayerSelectBinding extends ViewDataBinding {
    public final CardView cardLayer;
    public final CardView cardLayerChildren;
    public final RecyclerView rvLayer;
    public final RecyclerView rvLayerChildren;

    protected ViewLayerSelectBinding(Object obj, View view, int i, CardView cardView, CardView cardView2, RecyclerView recyclerView, RecyclerView recyclerView2) {
        super(obj, view, i);
        this.cardLayer = cardView;
        this.cardLayerChildren = cardView2;
        this.rvLayer = recyclerView;
        this.rvLayerChildren = recyclerView2;
    }

    public static ViewLayerSelectBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ViewLayerSelectBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ViewLayerSelectBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.view_layer_select, viewGroup, z, obj);
    }

    public static ViewLayerSelectBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ViewLayerSelectBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ViewLayerSelectBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.view_layer_select, (ViewGroup) null, false, obj);
    }

    public static ViewLayerSelectBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ViewLayerSelectBinding bind(View view, Object obj) {
        return (ViewLayerSelectBinding) bind(obj, view, R.layout.view_layer_select);
    }
}
