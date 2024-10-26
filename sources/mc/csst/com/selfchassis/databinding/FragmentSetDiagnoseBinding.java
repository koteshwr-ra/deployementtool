package mc.csst.com.selfchassis.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ciot.sentrymove.R;

public abstract class FragmentSetDiagnoseBinding extends ViewDataBinding {
    public final AppCompatButton btnClearLog;
    public final AppCompatTextView btnDiagnose;
    public final AppCompatTextView btnLogProcessing;
    public final AppCompatButton btnStartDetection;
    public final View line1;

    protected FragmentSetDiagnoseBinding(Object obj, View view, int i, AppCompatButton appCompatButton, AppCompatTextView appCompatTextView, AppCompatTextView appCompatTextView2, AppCompatButton appCompatButton2, View view2) {
        super(obj, view, i);
        this.btnClearLog = appCompatButton;
        this.btnDiagnose = appCompatTextView;
        this.btnLogProcessing = appCompatTextView2;
        this.btnStartDetection = appCompatButton2;
        this.line1 = view2;
    }

    public static FragmentSetDiagnoseBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSetDiagnoseBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentSetDiagnoseBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_set_diagnose, viewGroup, z, obj);
    }

    public static FragmentSetDiagnoseBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSetDiagnoseBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentSetDiagnoseBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_set_diagnose, (ViewGroup) null, false, obj);
    }

    public static FragmentSetDiagnoseBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSetDiagnoseBinding bind(View view, Object obj) {
        return (FragmentSetDiagnoseBinding) bind(obj, view, R.layout.fragment_set_diagnose);
    }
}
