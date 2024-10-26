package mc.csst.com.selfchassis.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ciot.sentrymove.R;

public abstract class DialogEditBinding extends ViewDataBinding {
    public final DialogBottomBinding buttons;
    public final AppCompatEditText letInput;
    public final AppCompatRadioButton rbSzSpeedCentre;
    public final AppCompatRadioButton rbSzSpeedLow;
    public final RadioGroup rgSzSpeed;
    public final TextView tvSlowZoneSpeedTitle;
    public final TextView tvSubTitle;
    public final TextView tvTitle;

    protected DialogEditBinding(Object obj, View view, int i, DialogBottomBinding dialogBottomBinding, AppCompatEditText appCompatEditText, AppCompatRadioButton appCompatRadioButton, AppCompatRadioButton appCompatRadioButton2, RadioGroup radioGroup, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.buttons = dialogBottomBinding;
        setContainedBinding(dialogBottomBinding);
        this.letInput = appCompatEditText;
        this.rbSzSpeedCentre = appCompatRadioButton;
        this.rbSzSpeedLow = appCompatRadioButton2;
        this.rgSzSpeed = radioGroup;
        this.tvSlowZoneSpeedTitle = textView;
        this.tvSubTitle = textView2;
        this.tvTitle = textView3;
    }

    public static DialogEditBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogEditBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DialogEditBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_edit, viewGroup, z, obj);
    }

    public static DialogEditBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogEditBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DialogEditBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_edit, (ViewGroup) null, false, obj);
    }

    public static DialogEditBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogEditBinding bind(View view, Object obj) {
        return (DialogEditBinding) bind(obj, view, R.layout.dialog_edit);
    }
}
