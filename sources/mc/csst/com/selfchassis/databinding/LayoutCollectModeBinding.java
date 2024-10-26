package mc.csst.com.selfchassis.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ciot.sentrymove.R;
import mc.csst.com.selfchassis.ui.widget.StartCollectView;

public abstract class LayoutCollectModeBinding extends ViewDataBinding {
    public final LinearLayout llCollectMode;
    public final RadioButton rbSimplifiedChinese;
    public final StartCollectView startCollectingView;

    protected LayoutCollectModeBinding(Object obj, View view, int i, LinearLayout linearLayout, RadioButton radioButton, StartCollectView startCollectView) {
        super(obj, view, i);
        this.llCollectMode = linearLayout;
        this.rbSimplifiedChinese = radioButton;
        this.startCollectingView = startCollectView;
    }

    public static LayoutCollectModeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutCollectModeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LayoutCollectModeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_collect_mode, viewGroup, z, obj);
    }

    public static LayoutCollectModeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutCollectModeBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LayoutCollectModeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_collect_mode, (ViewGroup) null, false, obj);
    }

    public static LayoutCollectModeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutCollectModeBinding bind(View view, Object obj) {
        return (LayoutCollectModeBinding) bind(obj, view, R.layout.layout_collect_mode);
    }
}
