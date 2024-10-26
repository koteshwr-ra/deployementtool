package mc.csst.com.selfchassis.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ciot.sentrymove.R;

public abstract class DialogBottomBinding extends ViewDataBinding {
    public final LinearLayout boxButton;
    public final AppCompatButton btnSelectNegative;
    public final AppCompatButton btnSelectPositive;

    protected DialogBottomBinding(Object obj, View view, int i, LinearLayout linearLayout, AppCompatButton appCompatButton, AppCompatButton appCompatButton2) {
        super(obj, view, i);
        this.boxButton = linearLayout;
        this.btnSelectNegative = appCompatButton;
        this.btnSelectPositive = appCompatButton2;
    }

    public static DialogBottomBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogBottomBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DialogBottomBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_bottom, viewGroup, z, obj);
    }

    public static DialogBottomBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogBottomBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DialogBottomBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_bottom, (ViewGroup) null, false, obj);
    }

    public static DialogBottomBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogBottomBinding bind(View view, Object obj) {
        return (DialogBottomBinding) bind(obj, view, R.layout.dialog_bottom);
    }
}
