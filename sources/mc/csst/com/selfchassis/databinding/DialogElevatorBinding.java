package mc.csst.com.selfchassis.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ciot.sentrymove.R;
import mc.csst.com.selfchassis.utils.view.LimitEditText;

public abstract class DialogElevatorBinding extends ViewDataBinding {
    public final DialogBottomBinding buttons;
    public final LimitEditText etAppId;
    public final LimitEditText etElevatorAppSecret;
    public final LimitEditText etElevatorProject;
    public final LimitEditText etRebootId;
    public final AppCompatTextView tvAppId;
    public final AppCompatTextView tvElevatorAppSecret;
    public final AppCompatTextView tvElevatorProject;
    public final AppCompatTextView tvRebootId;

    protected DialogElevatorBinding(Object obj, View view, int i, DialogBottomBinding dialogBottomBinding, LimitEditText limitEditText, LimitEditText limitEditText2, LimitEditText limitEditText3, LimitEditText limitEditText4, AppCompatTextView appCompatTextView, AppCompatTextView appCompatTextView2, AppCompatTextView appCompatTextView3, AppCompatTextView appCompatTextView4) {
        super(obj, view, i);
        this.buttons = dialogBottomBinding;
        setContainedBinding(dialogBottomBinding);
        this.etAppId = limitEditText;
        this.etElevatorAppSecret = limitEditText2;
        this.etElevatorProject = limitEditText3;
        this.etRebootId = limitEditText4;
        this.tvAppId = appCompatTextView;
        this.tvElevatorAppSecret = appCompatTextView2;
        this.tvElevatorProject = appCompatTextView3;
        this.tvRebootId = appCompatTextView4;
    }

    public static DialogElevatorBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogElevatorBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DialogElevatorBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_elevator, viewGroup, z, obj);
    }

    public static DialogElevatorBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogElevatorBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DialogElevatorBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_elevator, (ViewGroup) null, false, obj);
    }

    public static DialogElevatorBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogElevatorBinding bind(View view, Object obj) {
        return (DialogElevatorBinding) bind(obj, view, R.layout.dialog_elevator);
    }
}
