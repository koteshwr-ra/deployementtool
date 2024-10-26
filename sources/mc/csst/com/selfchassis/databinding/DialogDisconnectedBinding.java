package mc.csst.com.selfchassis.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ciot.sentrymove.R;

public abstract class DialogDisconnectedBinding extends ViewDataBinding {
    public final AppCompatImageView imageDisconnected;
    public final RelativeLayout rootRl;

    protected DialogDisconnectedBinding(Object obj, View view, int i, AppCompatImageView appCompatImageView, RelativeLayout relativeLayout) {
        super(obj, view, i);
        this.imageDisconnected = appCompatImageView;
        this.rootRl = relativeLayout;
    }

    public static DialogDisconnectedBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDisconnectedBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DialogDisconnectedBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_disconnected, viewGroup, z, obj);
    }

    public static DialogDisconnectedBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDisconnectedBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DialogDisconnectedBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_disconnected, (ViewGroup) null, false, obj);
    }

    public static DialogDisconnectedBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDisconnectedBinding bind(View view, Object obj) {
        return (DialogDisconnectedBinding) bind(obj, view, R.layout.dialog_disconnected);
    }
}
