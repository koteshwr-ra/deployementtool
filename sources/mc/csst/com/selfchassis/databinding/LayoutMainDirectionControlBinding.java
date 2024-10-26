package mc.csst.com.selfchassis.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ciot.sentrymove.R;
import mc.csst.com.selfchassis.utils.view.DirectionFourKey;
import mc.csst.com.selfchassis.utils.view.RockerView;

public abstract class LayoutMainDirectionControlBinding extends ViewDataBinding {
    public final RockerView mainDirectionAllRv;
    public final RelativeLayout mainDirectionControl;
    public final DirectionFourKey mainDirectionFourRv;

    protected LayoutMainDirectionControlBinding(Object obj, View view, int i, RockerView rockerView, RelativeLayout relativeLayout, DirectionFourKey directionFourKey) {
        super(obj, view, i);
        this.mainDirectionAllRv = rockerView;
        this.mainDirectionControl = relativeLayout;
        this.mainDirectionFourRv = directionFourKey;
    }

    public static LayoutMainDirectionControlBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutMainDirectionControlBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LayoutMainDirectionControlBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_main_direction_control, viewGroup, z, obj);
    }

    public static LayoutMainDirectionControlBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutMainDirectionControlBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LayoutMainDirectionControlBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_main_direction_control, (ViewGroup) null, false, obj);
    }

    public static LayoutMainDirectionControlBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutMainDirectionControlBinding bind(View view, Object obj) {
        return (LayoutMainDirectionControlBinding) bind(obj, view, R.layout.layout_main_direction_control);
    }
}
