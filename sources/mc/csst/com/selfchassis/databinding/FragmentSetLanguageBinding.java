package mc.csst.com.selfchassis.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ciot.sentrymove.R;
import mc.csst.com.selfchassis.ui.widget.GatewayPopupWindow;

public abstract class FragmentSetLanguageBinding extends ViewDataBinding {
    public final AppCompatButton btnSureSwitch;
    public final HorizontalScrollView flSwitchLanguage;
    public final GatewayPopupWindow gpwDownload;
    public final AppCompatTextView labelGateway;
    public final View line1;
    public final RadioButton rbArabic;
    public final RadioButton rbEnglish;
    public final RadioButton rbGerman;
    public final RadioButton rbHebrew;
    public final RadioButton rbIndonesian;
    public final RadioButton rbJapanese;
    public final RadioButton rbKorean;
    public final RadioButton rbSimplifiedChinese;
    public final RadioButton rbThai;
    public final RadioButton rbTraditionalChinese;
    public final RadioButton rbTurkish;
    public final RadioGroup rgLanguage;
    public final AppCompatTextView tvSwitchLanguage;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected FragmentSetLanguageBinding(Object obj, View view, int i, AppCompatButton appCompatButton, HorizontalScrollView horizontalScrollView, GatewayPopupWindow gatewayPopupWindow, AppCompatTextView appCompatTextView, View view2, RadioButton radioButton, RadioButton radioButton2, RadioButton radioButton3, RadioButton radioButton4, RadioButton radioButton5, RadioButton radioButton6, RadioButton radioButton7, RadioButton radioButton8, RadioButton radioButton9, RadioButton radioButton10, RadioButton radioButton11, RadioGroup radioGroup, AppCompatTextView appCompatTextView2) {
        super(obj, view, i);
        this.btnSureSwitch = appCompatButton;
        this.flSwitchLanguage = horizontalScrollView;
        this.gpwDownload = gatewayPopupWindow;
        this.labelGateway = appCompatTextView;
        this.line1 = view2;
        this.rbArabic = radioButton;
        this.rbEnglish = radioButton2;
        this.rbGerman = radioButton3;
        this.rbHebrew = radioButton4;
        this.rbIndonesian = radioButton5;
        this.rbJapanese = radioButton6;
        this.rbKorean = radioButton7;
        this.rbSimplifiedChinese = radioButton8;
        this.rbThai = radioButton9;
        this.rbTraditionalChinese = radioButton10;
        this.rbTurkish = radioButton11;
        this.rgLanguage = radioGroup;
        this.tvSwitchLanguage = appCompatTextView2;
    }

    public static FragmentSetLanguageBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSetLanguageBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentSetLanguageBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_set_language, viewGroup, z, obj);
    }

    public static FragmentSetLanguageBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSetLanguageBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentSetLanguageBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_set_language, (ViewGroup) null, false, obj);
    }

    public static FragmentSetLanguageBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSetLanguageBinding bind(View view, Object obj) {
        return (FragmentSetLanguageBinding) bind(obj, view, R.layout.fragment_set_language);
    }
}
