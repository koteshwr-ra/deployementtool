package mc.csst.com.selfchassis.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ciot.sentrymove.R;
import mc.csst.com.selfchassis.ui.fragment.set.version.VersionInfo;

public abstract class FragmentSetVersionBinding extends ViewDataBinding {
    public final AppCompatButton btnUpdateAlgorithm;
    public final AppCompatButton btnUpdateAlgorithmUsb;
    public final AppCompatButton btnUpdateDriveFirmware;
    public final AppCompatButton btnUpdateTime;
    public final ConstraintLayout clUpdateTime;
    @Bindable
    protected VersionInfo mVersion;

    public abstract void setVersion(VersionInfo versionInfo);

    protected FragmentSetVersionBinding(Object obj, View view, int i, AppCompatButton appCompatButton, AppCompatButton appCompatButton2, AppCompatButton appCompatButton3, AppCompatButton appCompatButton4, ConstraintLayout constraintLayout) {
        super(obj, view, i);
        this.btnUpdateAlgorithm = appCompatButton;
        this.btnUpdateAlgorithmUsb = appCompatButton2;
        this.btnUpdateDriveFirmware = appCompatButton3;
        this.btnUpdateTime = appCompatButton4;
        this.clUpdateTime = constraintLayout;
    }

    public VersionInfo getVersion() {
        return this.mVersion;
    }

    public static FragmentSetVersionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSetVersionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentSetVersionBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_set_version, viewGroup, z, obj);
    }

    public static FragmentSetVersionBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSetVersionBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentSetVersionBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_set_version, (ViewGroup) null, false, obj);
    }

    public static FragmentSetVersionBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSetVersionBinding bind(View view, Object obj) {
        return (FragmentSetVersionBinding) bind(obj, view, R.layout.fragment_set_version);
    }
}
