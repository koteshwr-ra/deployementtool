package mc.csst.com.selfchassis.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ciot.sentrymove.R;

public abstract class FragmentSetVersionControlBinding extends ViewDataBinding {
    protected FragmentSetVersionControlBinding(Object obj, View view, int i) {
        super(obj, view, i);
    }

    public static FragmentSetVersionControlBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSetVersionControlBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentSetVersionControlBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_set_version_control, viewGroup, z, obj);
    }

    public static FragmentSetVersionControlBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSetVersionControlBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentSetVersionControlBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_set_version_control, (ViewGroup) null, false, obj);
    }

    public static FragmentSetVersionControlBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSetVersionControlBinding bind(View view, Object obj) {
        return (FragmentSetVersionControlBinding) bind(obj, view, R.layout.fragment_set_version_control);
    }
}
