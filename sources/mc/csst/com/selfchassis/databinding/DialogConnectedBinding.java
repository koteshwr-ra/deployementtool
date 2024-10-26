package mc.csst.com.selfchassis.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ciot.sentrymove.R;

public abstract class DialogConnectedBinding extends ViewDataBinding {
    public final DialogBottomBinding buttons;
    public final AppCompatEditText ipEdit;
    public final AppCompatTextView ipTv;
    public final RelativeLayout rootRl;
    public final AppCompatTextView title;

    protected DialogConnectedBinding(Object obj, View view, int i, DialogBottomBinding dialogBottomBinding, AppCompatEditText appCompatEditText, AppCompatTextView appCompatTextView, RelativeLayout relativeLayout, AppCompatTextView appCompatTextView2) {
        super(obj, view, i);
        this.buttons = dialogBottomBinding;
        setContainedBinding(dialogBottomBinding);
        this.ipEdit = appCompatEditText;
        this.ipTv = appCompatTextView;
        this.rootRl = relativeLayout;
        this.title = appCompatTextView2;
    }

    public static DialogConnectedBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogConnectedBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DialogConnectedBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_connected, viewGroup, z, obj);
    }

    public static DialogConnectedBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogConnectedBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DialogConnectedBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_connected, (ViewGroup) null, false, obj);
    }

    public static DialogConnectedBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogConnectedBinding bind(View view, Object obj) {
        return (DialogConnectedBinding) bind(obj, view, R.layout.dialog_connected);
    }
}
