package mc.csst.com.selfchassis.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ciot.sentrymove.R;

public abstract class DialogLoadingBinding extends ViewDataBinding {
    public final ProgressBar loadingProgress;
    public final TextView loadingTv;

    protected DialogLoadingBinding(Object obj, View view, int i, ProgressBar progressBar, TextView textView) {
        super(obj, view, i);
        this.loadingProgress = progressBar;
        this.loadingTv = textView;
    }

    public static DialogLoadingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogLoadingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DialogLoadingBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_loading, viewGroup, z, obj);
    }

    public static DialogLoadingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogLoadingBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DialogLoadingBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_loading, (ViewGroup) null, false, obj);
    }

    public static DialogLoadingBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogLoadingBinding bind(View view, Object obj) {
        return (DialogLoadingBinding) bind(obj, view, R.layout.dialog_loading);
    }
}
