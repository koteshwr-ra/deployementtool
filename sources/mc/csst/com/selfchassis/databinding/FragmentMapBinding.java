package mc.csst.com.selfchassis.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ciot.sentrymove.R;
import mc.csst.com.selfchassis.ui.widget.MultiRobotMapPopupWindow;

public abstract class FragmentMapBinding extends ViewDataBinding {
    public final AppCompatButton btnSureDownload;
    public final AppCompatButton btnSureUpload;
    public final AppCompatTextView labelDownloadMap;
    public final AppCompatTextView labelUploadMap;
    public final MultiRobotMapPopupWindow mrmpwDownload;
    public final MultiRobotMapPopupWindow mrmpwUpload;
    public final AppCompatTextView tvMapSync;

    protected FragmentMapBinding(Object obj, View view, int i, AppCompatButton appCompatButton, AppCompatButton appCompatButton2, AppCompatTextView appCompatTextView, AppCompatTextView appCompatTextView2, MultiRobotMapPopupWindow multiRobotMapPopupWindow, MultiRobotMapPopupWindow multiRobotMapPopupWindow2, AppCompatTextView appCompatTextView3) {
        super(obj, view, i);
        this.btnSureDownload = appCompatButton;
        this.btnSureUpload = appCompatButton2;
        this.labelDownloadMap = appCompatTextView;
        this.labelUploadMap = appCompatTextView2;
        this.mrmpwDownload = multiRobotMapPopupWindow;
        this.mrmpwUpload = multiRobotMapPopupWindow2;
        this.tvMapSync = appCompatTextView3;
    }

    public static FragmentMapBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentMapBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentMapBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_map, viewGroup, z, obj);
    }

    public static FragmentMapBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentMapBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentMapBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_map, (ViewGroup) null, false, obj);
    }

    public static FragmentMapBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentMapBinding bind(View view, Object obj) {
        return (FragmentMapBinding) bind(obj, view, R.layout.fragment_map);
    }
}
