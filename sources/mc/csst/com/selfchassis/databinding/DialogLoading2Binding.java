package mc.csst.com.selfchassis.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ciot.sentrymove.R;

public abstract class DialogLoading2Binding extends ViewDataBinding {
    public final TextView buttonCancel;
    public final ProgressBar loadingProgress;
    public final TextView loadingTv;
    public final TextView tvProgress;

    protected DialogLoading2Binding(Object obj, View view, int i, TextView textView, ProgressBar progressBar, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.buttonCancel = textView;
        this.loadingProgress = progressBar;
        this.loadingTv = textView2;
        this.tvProgress = textView3;
    }

    public static DialogLoading2Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogLoading2Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DialogLoading2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_loading2, viewGroup, z, obj);
    }

    public static DialogLoading2Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogLoading2Binding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DialogLoading2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_loading2, (ViewGroup) null, false, obj);
    }

    public static DialogLoading2Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogLoading2Binding bind(View view, Object obj) {
        return (DialogLoading2Binding) bind(obj, view, R.layout.dialog_loading2);
    }
}
