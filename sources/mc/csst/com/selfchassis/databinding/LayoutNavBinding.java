package mc.csst.com.selfchassis.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.ciot.sentrymove.R;

public abstract class LayoutNavBinding extends ViewDataBinding {
    public final AppCompatRadioButton navMultiBackRb;
    public final AppCompatButton navMultiClearBtn;
    public final AppCompatRadioButton navMultiCycleRb;
    public final ConstraintLayout navMultiLineCl;
    public final AppCompatTextView navMultiLineEmptyTv;
    public final RecyclerView navMultiLineRv;
    public final AppCompatTextView navMultiLineTip;
    public final AppCompatTextView navMultiLineTitleTv;
    public final AppCompatImageView navMultiListIconIv;
    public final LinearLayout navMultiListLl;
    public final RecyclerView navMultiListRv;
    public final AppCompatImageView navMultiMoreIv;
    public final AppCompatRadioButton navMultiSingleRb;
    public final ConstraintLayout navMultiTimesCl;
    public final AppCompatEditText navMultiTimesEt;
    public final AppCompatTextView navMultiTimesTailTv;
    public final AppCompatTextView navMultiTimesTitleTv;
    public final ConstraintLayout navMultiTitleCl;
    public final ConstraintLayout navRootCl;
    public final ConstraintLayout navSetCl;
    public final AppCompatImageView navStartIconIv;
    public final ConstraintLayout navStartNavCl;
    public final AppCompatTextView navStartTv;
    public final AppCompatRadioButton navTypeMultiRb;
    public final RadioGroup navTypeRg;
    public final AppCompatRadioButton navTypeSingleRb;
    public final RadioGroup radioGroup;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected LayoutNavBinding(Object obj, View view, int i, AppCompatRadioButton appCompatRadioButton, AppCompatButton appCompatButton, AppCompatRadioButton appCompatRadioButton2, ConstraintLayout constraintLayout, AppCompatTextView appCompatTextView, RecyclerView recyclerView, AppCompatTextView appCompatTextView2, AppCompatTextView appCompatTextView3, AppCompatImageView appCompatImageView, LinearLayout linearLayout, RecyclerView recyclerView2, AppCompatImageView appCompatImageView2, AppCompatRadioButton appCompatRadioButton3, ConstraintLayout constraintLayout2, AppCompatEditText appCompatEditText, AppCompatTextView appCompatTextView4, AppCompatTextView appCompatTextView5, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, AppCompatImageView appCompatImageView3, ConstraintLayout constraintLayout6, AppCompatTextView appCompatTextView6, AppCompatRadioButton appCompatRadioButton4, RadioGroup radioGroup2, AppCompatRadioButton appCompatRadioButton5, RadioGroup radioGroup3) {
        super(obj, view, i);
        this.navMultiBackRb = appCompatRadioButton;
        this.navMultiClearBtn = appCompatButton;
        this.navMultiCycleRb = appCompatRadioButton2;
        this.navMultiLineCl = constraintLayout;
        this.navMultiLineEmptyTv = appCompatTextView;
        this.navMultiLineRv = recyclerView;
        this.navMultiLineTip = appCompatTextView2;
        this.navMultiLineTitleTv = appCompatTextView3;
        this.navMultiListIconIv = appCompatImageView;
        this.navMultiListLl = linearLayout;
        this.navMultiListRv = recyclerView2;
        this.navMultiMoreIv = appCompatImageView2;
        this.navMultiSingleRb = appCompatRadioButton3;
        this.navMultiTimesCl = constraintLayout2;
        this.navMultiTimesEt = appCompatEditText;
        this.navMultiTimesTailTv = appCompatTextView4;
        this.navMultiTimesTitleTv = appCompatTextView5;
        this.navMultiTitleCl = constraintLayout3;
        this.navRootCl = constraintLayout4;
        this.navSetCl = constraintLayout5;
        this.navStartIconIv = appCompatImageView3;
        this.navStartNavCl = constraintLayout6;
        this.navStartTv = appCompatTextView6;
        this.navTypeMultiRb = appCompatRadioButton4;
        this.navTypeRg = radioGroup2;
        this.navTypeSingleRb = appCompatRadioButton5;
        this.radioGroup = radioGroup3;
    }

    public static LayoutNavBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutNavBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LayoutNavBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_nav, viewGroup, z, obj);
    }

    public static LayoutNavBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutNavBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LayoutNavBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_nav, (ViewGroup) null, false, obj);
    }

    public static LayoutNavBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutNavBinding bind(View view, Object obj) {
        return (LayoutNavBinding) bind(obj, view, R.layout.layout_nav);
    }
}
