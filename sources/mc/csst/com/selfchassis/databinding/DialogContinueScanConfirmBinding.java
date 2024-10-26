package mc.csst.com.selfchassis.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ciot.sentrymove.R;

public abstract class DialogContinueScanConfirmBinding extends ViewDataBinding {
    public final LinearLayout boxButton;
    public final Button btnSelectNegative;
    public final Button btnSelectOther;
    public final Button btnSelectPositive;
    public final AppCompatButton btnTopExit;
    public final CheckBox cbDesc;
    public final LinearLayout llDesc;
    public final TextView message;
    public final TextView title;

    protected DialogContinueScanConfirmBinding(Object obj, View view, int i, LinearLayout linearLayout, Button button, Button button2, Button button3, AppCompatButton appCompatButton, CheckBox checkBox, LinearLayout linearLayout2, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.boxButton = linearLayout;
        this.btnSelectNegative = button;
        this.btnSelectOther = button2;
        this.btnSelectPositive = button3;
        this.btnTopExit = appCompatButton;
        this.cbDesc = checkBox;
        this.llDesc = linearLayout2;
        this.message = textView;
        this.title = textView2;
    }

    public static DialogContinueScanConfirmBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogContinueScanConfirmBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DialogContinueScanConfirmBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_continue_scan_confirm, viewGroup, z, obj);
    }

    public static DialogContinueScanConfirmBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogContinueScanConfirmBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DialogContinueScanConfirmBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_continue_scan_confirm, (ViewGroup) null, false, obj);
    }

    public static DialogContinueScanConfirmBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogContinueScanConfirmBinding bind(View view, Object obj) {
        return (DialogContinueScanConfirmBinding) bind(obj, view, R.layout.dialog_continue_scan_confirm);
    }
}
