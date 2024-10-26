package mc.csst.com.selfchassis.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.ciot.sentrymove.R;

public abstract class FragmentSetOnlineUpdateConfigBinding extends ViewDataBinding {
    public final AppCompatButton btnUpdateNow;
    public final CardView cardUpgradeLink;
    public final ConstraintLayout clUpgradeLink;
    public final LinearLayoutCompat iconBtnAdd;
    public final AppCompatImageView imgBack;
    public final AppCompatImageView imgLinkArrow;
    public final LinearLayoutCompat llBack;
    public final RecyclerView rvUpgradeLink;
    public final AppCompatTextView tvLinkDesc;
    public final AppCompatTextView tvSelectUpgradeLinkTitle;
    public final AppCompatTextView tvTitle;
    public final AppCompatTextView tvUpgradeLink;
    public final View view;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected FragmentSetOnlineUpdateConfigBinding(Object obj, View view2, int i, AppCompatButton appCompatButton, CardView cardView, ConstraintLayout constraintLayout, LinearLayoutCompat linearLayoutCompat, AppCompatImageView appCompatImageView, AppCompatImageView appCompatImageView2, LinearLayoutCompat linearLayoutCompat2, RecyclerView recyclerView, AppCompatTextView appCompatTextView, AppCompatTextView appCompatTextView2, AppCompatTextView appCompatTextView3, AppCompatTextView appCompatTextView4, View view3) {
        super(obj, view2, i);
        this.btnUpdateNow = appCompatButton;
        this.cardUpgradeLink = cardView;
        this.clUpgradeLink = constraintLayout;
        this.iconBtnAdd = linearLayoutCompat;
        this.imgBack = appCompatImageView;
        this.imgLinkArrow = appCompatImageView2;
        this.llBack = linearLayoutCompat2;
        this.rvUpgradeLink = recyclerView;
        this.tvLinkDesc = appCompatTextView;
        this.tvSelectUpgradeLinkTitle = appCompatTextView2;
        this.tvTitle = appCompatTextView3;
        this.tvUpgradeLink = appCompatTextView4;
        this.view = view3;
    }

    public static FragmentSetOnlineUpdateConfigBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSetOnlineUpdateConfigBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentSetOnlineUpdateConfigBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_set_online_update_config, viewGroup, z, obj);
    }

    public static FragmentSetOnlineUpdateConfigBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSetOnlineUpdateConfigBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentSetOnlineUpdateConfigBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_set_online_update_config, (ViewGroup) null, false, obj);
    }

    public static FragmentSetOnlineUpdateConfigBinding bind(View view2) {
        return bind(view2, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSetOnlineUpdateConfigBinding bind(View view2, Object obj) {
        return (FragmentSetOnlineUpdateConfigBinding) bind(obj, view2, R.layout.fragment_set_online_update_config);
    }
}
