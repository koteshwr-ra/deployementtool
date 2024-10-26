package mc.csst.com.selfchassis.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ciot.sentrymove.R;
import mc.csst.com.selfchassis.ui.widget.MultiRobotMapPopupWindow;
import mc.csst.com.selfchassis.ui.widget.SwitchSilent;

public abstract class FragmentScheduleBinding extends ViewDataBinding {
    public final AppCompatButton btnModify;
    public final AppCompatButton btnModifyName;
    public final AppCompatButton btnSureDownload;
    public final AppCompatButton btnSureUpload;
    public final ConstraintLayout clMapSyncConfig;
    public final ConstraintLayout clNetworkChange;
    public final ConstraintLayout clNetworkConfig;
    public final ConstraintLayout clProjectName;
    public final ConstraintLayout clSchedulingSetting;
    public final View divider;
    public final View divider2;
    public final AppCompatTextView labelDownloadMap;
    public final AppCompatTextView labelUploadMap;
    public final MultiRobotMapPopupWindow mrmpwDownload;
    public final MultiRobotMapPopupWindow mrmpwUpload;
    public final RadioButton rbLocalNetwork;
    public final RadioButton rbPublicWork;
    public final RadioGroup rgChangeNetwork;
    public final SwitchSilent switchMultiRobotMode;
    public final AppCompatTextView tvIpaddress;
    public final AppCompatTextView tvMapSync;
    public final AppCompatTextView tvMultiRobotMode;
    public final AppCompatTextView tvNetworkChangeLabel;
    public final AppCompatTextView tvNetworkSettingLabel;
    public final AppCompatTextView tvOperation;
    public final AppCompatTextView tvProjectName;
    public final AppCompatTextView tvProjectNameLabel;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected FragmentScheduleBinding(Object obj, View view, int i, AppCompatButton appCompatButton, AppCompatButton appCompatButton2, AppCompatButton appCompatButton3, AppCompatButton appCompatButton4, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, View view2, View view3, AppCompatTextView appCompatTextView, AppCompatTextView appCompatTextView2, MultiRobotMapPopupWindow multiRobotMapPopupWindow, MultiRobotMapPopupWindow multiRobotMapPopupWindow2, RadioButton radioButton, RadioButton radioButton2, RadioGroup radioGroup, SwitchSilent switchSilent, AppCompatTextView appCompatTextView3, AppCompatTextView appCompatTextView4, AppCompatTextView appCompatTextView5, AppCompatTextView appCompatTextView6, AppCompatTextView appCompatTextView7, AppCompatTextView appCompatTextView8, AppCompatTextView appCompatTextView9, AppCompatTextView appCompatTextView10) {
        super(obj, view, i);
        this.btnModify = appCompatButton;
        this.btnModifyName = appCompatButton2;
        this.btnSureDownload = appCompatButton3;
        this.btnSureUpload = appCompatButton4;
        this.clMapSyncConfig = constraintLayout;
        this.clNetworkChange = constraintLayout2;
        this.clNetworkConfig = constraintLayout3;
        this.clProjectName = constraintLayout4;
        this.clSchedulingSetting = constraintLayout5;
        this.divider = view2;
        this.divider2 = view3;
        this.labelDownloadMap = appCompatTextView;
        this.labelUploadMap = appCompatTextView2;
        this.mrmpwDownload = multiRobotMapPopupWindow;
        this.mrmpwUpload = multiRobotMapPopupWindow2;
        this.rbLocalNetwork = radioButton;
        this.rbPublicWork = radioButton2;
        this.rgChangeNetwork = radioGroup;
        this.switchMultiRobotMode = switchSilent;
        this.tvIpaddress = appCompatTextView3;
        this.tvMapSync = appCompatTextView4;
        this.tvMultiRobotMode = appCompatTextView5;
        this.tvNetworkChangeLabel = appCompatTextView6;
        this.tvNetworkSettingLabel = appCompatTextView7;
        this.tvOperation = appCompatTextView8;
        this.tvProjectName = appCompatTextView9;
        this.tvProjectNameLabel = appCompatTextView10;
    }

    public static FragmentScheduleBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentScheduleBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentScheduleBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_schedule, viewGroup, z, obj);
    }

    public static FragmentScheduleBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentScheduleBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentScheduleBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_schedule, (ViewGroup) null, false, obj);
    }

    public static FragmentScheduleBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentScheduleBinding bind(View view, Object obj) {
        return (FragmentScheduleBinding) bind(obj, view, R.layout.fragment_schedule);
    }
}
