package mc.csst.com.selfchassis.ui.fragment.set.version;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.blankj.utilcode.util.AdaptScreenUtils;
import com.blankj.utilcode.util.ClickUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.ciot.base.livedatabus.LiveDatabus;
import com.ciot.base.util.ContextUtil;
import com.ciot.base.util.GsonUtils;
import com.ciot.base.util.MultiLanguageUtil;
import com.ciot.base.util.MyLogUtils;
import com.ciot.sentrymove.R;
import java.util.ArrayList;
import java.util.List;
import mc.csst.com.selfchassis.adapter.OnlineUpdateUrlAdapter;
import mc.csst.com.selfchassis.databinding.FragmentSetOnlineUpdateConfigBinding;
import mc.csst.com.selfchassis.ui.dialog.ConfirmDialog;
import mc.csst.com.selfchassis.ui.dialog.EditDialog;
import mc.csst.com.selfchassis.ui.fragment.base.BaseFragment;
import mc.csst.com.selfchassis.utils.ChassisSpUtils;
import mc.csst.com.selfchassis.utils.DeploymentToolUtils;
import mc.csst.com.selfchassis.utils.MyToastUtils;
import mc.csst.com.selfchassis.utils.bean.LoadingLiveDataEvent;
import mc.csst.com.selfchassis.utils.bean.OnlineUpdateUrlBean;
import mc.csst.com.selfchassis.utils.constant.DTLiveDataConstant;
import mc.csst.com.selfchassis.utils.constant.DeploymentToolConstant;
import mc.csst.com.selfchassislibrary.bean.msg.CancelUpgradeDownloadBean;
import mc.csst.com.selfchassislibrary.bean.msg.VersionUpgradeResponseBean;
import mc.csst.com.selfchassislibrary.chassis.SelfChassis;
import mc.csst.com.selfchassislibrary.content.ServiceContent;
import mc.csst.com.selfchassislibrary.utils.SelfChassisListenerUtils;
import mc.csst.com.selfchassislibrary.utils.eventbus.SelfChassisEventMsg;

public class OnlineUpdateConfigFragment extends BaseFragment implements SelfChassisListenerUtils.OnBaseSelfChassisListener {
    private static final int MAX_LINK_NUM = 10;
    public static final String TAG = OnlineUpdateConfigFragment.class.getSimpleName();
    /* access modifiers changed from: private */
    public FragmentSetOnlineUpdateConfigBinding mOnlineUpdateConfigBinding;
    /* access modifiers changed from: private */
    public OnlineUpdateUrlAdapter mOnlineUpdateUrlAdapter;
    /* access modifiers changed from: private */
    public List<OnlineUpdateUrlBean> mOnlineUpdateUrls = new ArrayList();
    /* access modifiers changed from: private */
    public OnlineUpdateUrlBean mSelectOnlineUpdateUrlBean = null;

    /* access modifiers changed from: protected */
    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentSetOnlineUpdateConfigBinding fragmentSetOnlineUpdateConfigBinding = (FragmentSetOnlineUpdateConfigBinding) DataBindingUtil.inflate(layoutInflater, R.layout.fragment_set_online_update_config, viewGroup, false);
        this.mOnlineUpdateConfigBinding = fragmentSetOnlineUpdateConfigBinding;
        return fragmentSetOnlineUpdateConfigBinding.getRoot();
    }

    /* access modifiers changed from: protected */
    public void initData() {
        String str;
        if (MultiLanguageUtil.getInstance().isRtlLanguage()) {
            this.mOnlineUpdateConfigBinding.imgBack.setRotation(180.0f);
        }
        OnlineUpdateUrlBean curOnlineUpdateUrl = ChassisSpUtils.getCurOnlineUpdateUrl();
        this.mSelectOnlineUpdateUrlBean = curOnlineUpdateUrl;
        int i = -1;
        if (curOnlineUpdateUrl != null) {
            str = curOnlineUpdateUrl.getUrl();
            String urlAlias = this.mSelectOnlineUpdateUrlBean.getUrlAlias();
            if (this.mSelectOnlineUpdateUrlBean.isDefault() && !TextUtils.isEmpty(urlAlias)) {
                str = urlAlias;
            }
            i = this.mSelectOnlineUpdateUrlBean.getUrlDescType();
        } else {
            str = "";
        }
        this.mOnlineUpdateConfigBinding.tvUpgradeLink.setText(str);
        this.mOnlineUpdateConfigBinding.tvLinkDesc.setText(getSelectUrlDesc(i));
        this.mOnlineUpdateUrlAdapter = new OnlineUpdateUrlAdapter();
        this.mOnlineUpdateConfigBinding.rvUpgradeLink.setLayoutManager(new LinearLayoutManager(ContextUtil.getContext()));
        this.mOnlineUpdateConfigBinding.rvUpgradeLink.setAdapter(this.mOnlineUpdateUrlAdapter);
        List<OnlineUpdateUrlBean> updateUrls = getUpdateUrls();
        this.mOnlineUpdateUrls = updateUrls;
        this.mOnlineUpdateUrlAdapter.refreshData(updateUrls);
        setSelectPosition();
    }

    /* access modifiers changed from: private */
    public void setSelectPosition() {
        if (this.mSelectOnlineUpdateUrlBean != null) {
            int i = 0;
            while (i < this.mOnlineUpdateUrls.size()) {
                OnlineUpdateUrlBean onlineUpdateUrlBean = this.mOnlineUpdateUrls.get(i);
                if (!TextUtils.equals(onlineUpdateUrlBean.getUrl(), this.mSelectOnlineUpdateUrlBean.getUrl()) || onlineUpdateUrlBean.getUrlDescType() != this.mSelectOnlineUpdateUrlBean.getUrlDescType()) {
                    i++;
                } else {
                    this.mSelectOnlineUpdateUrlBean = onlineUpdateUrlBean;
                    this.mOnlineUpdateUrlAdapter.setSelectPosition(this.mOnlineUpdateUrls.indexOf(onlineUpdateUrlBean));
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
        this.mOnlineUpdateConfigBinding.llBack.setOnClickListener(new MyClickListener());
        this.mOnlineUpdateConfigBinding.iconBtnAdd.setOnClickListener(new MyClickListener());
        this.mOnlineUpdateConfigBinding.clUpgradeLink.setOnClickListener(new MyClickListener());
        this.mOnlineUpdateConfigBinding.btnUpdateNow.setOnClickListener(new MyClickListener());
        this.mOnlineUpdateUrlAdapter.setOnItemClickListener(new OnlineUpdateUrlAdapter.OnItemClickListener() {
            public void onItemClick(OnlineUpdateUrlBean onlineUpdateUrlBean) {
                OnlineUpdateUrlBean unused = OnlineUpdateConfigFragment.this.mSelectOnlineUpdateUrlBean = onlineUpdateUrlBean;
                String url = onlineUpdateUrlBean.getUrl();
                String urlAlias = onlineUpdateUrlBean.getUrlAlias();
                int urlDescType = onlineUpdateUrlBean.getUrlDescType();
                if (onlineUpdateUrlBean.isDefault() && !TextUtils.isEmpty(urlAlias)) {
                    url = urlAlias;
                }
                OnlineUpdateConfigFragment.this.mOnlineUpdateConfigBinding.tvUpgradeLink.setText(url);
                OnlineUpdateConfigFragment.this.mOnlineUpdateConfigBinding.tvLinkDesc.setText(OnlineUpdateConfigFragment.this.getSelectUrlDesc(urlDescType));
                OnlineUpdateConfigFragment.this.showUpdateLinksView(false);
                ChassisSpUtils.setCurOnlineUpdateUrl(onlineUpdateUrlBean);
            }

            public void onDelete(OnlineUpdateUrlBean onlineUpdateUrlBean) {
                OnlineUpdateConfigFragment.this.mOnlineUpdateUrls.remove(onlineUpdateUrlBean);
                ChassisSpUtils.removeOnlineUpdateUrl(onlineUpdateUrlBean);
                OnlineUpdateConfigFragment.this.setSelectPosition();
            }
        });
        SelfChassis.getInstance().addVersionUpgradeListener(this);
    }

    public void onSelfChassisMsg(SelfChassisEventMsg selfChassisEventMsg) {
        ThreadUtils.runOnUiThread(new Runnable(selfChassisEventMsg) {
            public final /* synthetic */ SelfChassisEventMsg f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                OnlineUpdateConfigFragment.this.lambda$onSelfChassisMsg$0$OnlineUpdateConfigFragment(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$onSelfChassisMsg$0$OnlineUpdateConfigFragment(SelfChassisEventMsg selfChassisEventMsg) {
        if (isAdded()) {
            if (ServiceContent.VERSION_UPGRADE.equals(selfChassisEventMsg.getCode())) {
                onVersionUpgrade((VersionUpgradeResponseBean) selfChassisEventMsg.getData());
            } else if (ServiceContent.CANCEL_UPGRADE_DOWNLOAD.equals(selfChassisEventMsg.getCode())) {
                CancelUpgradeDownloadBean cancelUpgradeDownloadBean = (CancelUpgradeDownloadBean) selfChassisEventMsg.getData();
                if (cancelUpgradeDownloadBean == null || cancelUpgradeDownloadBean.getValues() == null || cancelUpgradeDownloadBean.getValues().getResult() == null) {
                    MyToastUtils.showShort(ContextUtil.getContext(), getString(R.string.txt_fail_to_cancel_update));
                } else if (cancelUpgradeDownloadBean.getValues().getResult().intValue() == 718) {
                    closeLoadingDialog();
                } else {
                    MyToastUtils.showShort(ContextUtil.getContext(), cancelUpgradeDownloadBean.getValues().getInfo());
                }
            }
        }
    }

    class MyClickListener extends ClickUtils.OnDebouncingClickListener {
        MyClickListener() {
        }

        public void onDebouncingClick(View view) {
            int id = view.getId();
            if (id == R.id.ll_back) {
                Navigation.findNavController(view).popBackStack();
            } else if (id == R.id.icon_btn_add) {
                OnlineUpdateConfigFragment.this.showUpdateLinksView(false);
                if (OnlineUpdateConfigFragment.this.mOnlineUpdateUrls.size() > 10) {
                    MyToastUtils.showShort(ContextUtil.getContext(), StringUtils.getString(R.string.txt_update_links_check_num));
                    return;
                }
                EditDialog newInstance = EditDialog.newInstance();
                newInstance.setTitle(StringUtils.getString(R.string.txt_add_update_link));
                newInstance.setSize(AdaptScreenUtils.pt2Px(1064.0f), AdaptScreenUtils.pt2Px(480.0f));
                newInstance.setSubTitle(StringUtils.getString(R.string.txt_link));
                newInstance.setHintTxt(StringUtils.getString(R.string.txt_please_enter));
                newInstance.setOnDialogButtonClickListener(new EditDialog.OnDialogButtonClickListener() {
                    public void onCanCel() {
                    }

                    public boolean onConfirm(String str, float f) {
                        if (!RegexUtils.isURL(str)) {
                            MyToastUtils.showShort(ContextUtil.getContext(), StringUtils.getString(R.string.ip_format_error));
                            return true;
                        } else if (ChassisSpUtils.addOnlineUpdateUrl(OnlineUpdateConfigFragment.this.getCustomUrl(str))) {
                            OnlineUpdateConfigFragment.this.mOnlineUpdateUrls.add(OnlineUpdateConfigFragment.this.getCustomUrl(str));
                            OnlineUpdateConfigFragment.this.mOnlineUpdateUrlAdapter.refreshData(OnlineUpdateConfigFragment.this.mOnlineUpdateUrls);
                            return false;
                        } else {
                            MyToastUtils.showShort(ContextUtil.getContext(), StringUtils.getString(R.string.txt_address_already_exists));
                            return true;
                        }
                    }
                });
                newInstance.showAllowingStateLoss(OnlineUpdateConfigFragment.this.getChildFragmentManager());
            } else if (id == R.id.cl_upgrade_link) {
                if (OnlineUpdateConfigFragment.this.mOnlineUpdateConfigBinding.cardUpgradeLink.getVisibility() == 0) {
                    OnlineUpdateConfigFragment.this.showUpdateLinksView(false);
                } else {
                    OnlineUpdateConfigFragment.this.showUpdateLinksView(true);
                }
            } else if (id != R.id.btn_update_now) {
            } else {
                if (TextUtils.isEmpty(OnlineUpdateConfigFragment.this.mOnlineUpdateConfigBinding.tvUpgradeLink.getText())) {
                    MyToastUtils.showShort(ContextUtil.getContext(), StringUtils.getString(R.string.txt_please_select_upgrade_link));
                    return;
                }
                MyLogUtils.Logd(OnlineUpdateConfigFragment.TAG, "立即更新");
                OnlineUpdateConfigFragment.this.showLoadingDialog(StringUtils.getString(R.string.tip_check_updating));
                OnlineUpdateConfigFragment.this.getLastVersion();
            }
        }
    }

    /* access modifiers changed from: private */
    public void getLastVersion() {
        OnlineUpdateUrlBean onlineUpdateUrlBean = this.mSelectOnlineUpdateUrlBean;
        if (onlineUpdateUrlBean == null) {
            return;
        }
        if (onlineUpdateUrlBean.getUrlDescType() == 3) {
            SelfChassis.getInstance().serviceVersionUpgrade(1, this.mSelectOnlineUpdateUrlBean.getUrl());
        } else {
            SelfChassis.getInstance().serviceVersionUpgrade(87, this.mSelectOnlineUpdateUrlBean.getUrl());
        }
    }

    private void updateVersion() {
        OnlineUpdateUrlBean onlineUpdateUrlBean = this.mSelectOnlineUpdateUrlBean;
        if (onlineUpdateUrlBean == null) {
            return;
        }
        if (onlineUpdateUrlBean.getUrlDescType() == 3) {
            SelfChassis.getInstance().serviceVersionUpgrade(12);
        } else {
            SelfChassis.getInstance().serviceVersionUpgrade(88, this.mSelectOnlineUpdateUrlBean.getUrl());
        }
    }

    /* access modifiers changed from: private */
    public void showUpdateLinksView(boolean z) {
        if (z) {
            this.mOnlineUpdateConfigBinding.imgLinkArrow.setBackgroundResource(R.mipmap.ic_arrow_up2);
            this.mOnlineUpdateConfigBinding.cardUpgradeLink.setVisibility(0);
            return;
        }
        this.mOnlineUpdateConfigBinding.imgLinkArrow.setBackgroundResource(R.mipmap.ic_arrow_down2);
        this.mOnlineUpdateConfigBinding.cardUpgradeLink.setVisibility(8);
    }

    public void onVersionUpgrade(VersionUpgradeResponseBean versionUpgradeResponseBean) {
        if (versionUpgradeResponseBean == null) {
            MyLogUtils.Logd(TAG, "onVersionUpgrade versionInfo is null!");
            return;
        }
        MyLogUtils.Logd("onVersionUpgrade:" + GsonUtils.toJson(versionUpgradeResponseBean));
        VersionUpgradeResponseBean.ValuesBean values = versionUpgradeResponseBean.getValues();
        int result = values.getResult();
        int parseInt = Integer.parseInt(versionUpgradeResponseBean.getId());
        if (result == 0) {
            closeLoadingDialog();
            if (parseInt == 1 || parseInt == 87) {
                String latest_version = values.getLatest_version();
                String current_version = values.getCurrent_version();
                String format = String.format(StringUtils.getString(R.string.confirm_update_version), new Object[]{values.getLatest_version()});
                if (TextUtils.equals(latest_version, current_version)) {
                    format = String.format(StringUtils.getString(R.string.confirm_update_version_repeat), new Object[]{values.getLatest_version()});
                }
                ConfirmDialog buildDefault2 = ConfirmDialog.buildDefault2(StringUtils.getString(R.string.check_update), format);
                buildDefault2.setOnOkButtonClickListener(new ConfirmDialog.OnDialogButtonClickListener() {
                    public final boolean onClick(View view) {
                        return OnlineUpdateConfigFragment.this.lambda$onVersionUpgrade$1$OnlineUpdateConfigFragment(view);
                    }
                });
                buildDefault2.showAllowingStateLoss(getChildFragmentManager());
            }
        } else if (result == 718) {
            closeLoadingDialog();
            MyToastUtils.showShort(ContextUtil.getContext(), StringUtils.getString(R.string.txt_success_to_cancel_update));
        } else {
            if (result == 717) {
                Context context = ContextUtil.getContext();
                MyToastUtils.showShort(context, StringUtils.getString(R.string.firmware_invalid) + values.getDesp());
            }
            closeLoadingDialog();
            ConfirmDialog.buildDefault(StringUtils.getString(R.string.check_update), DeploymentToolUtils.getInstance().getUpgradePrompt(result)).showAllowingStateLoss(getChildFragmentManager());
        }
    }

    public /* synthetic */ boolean lambda$onVersionUpgrade$1$OnlineUpdateConfigFragment(View view) {
        updateVersion();
        MyLogUtils.Logd(TAG, "开始更新");
        showLoadingDialog(StringUtils.getString(R.string.tip_updating));
        return false;
    }

    public void closeLoadingDialog() {
        LiveDatabus.getInstance().with(DTLiveDataConstant.SET_LOADING_DIALOG_EVENT).postValue(new LoadingLiveDataEvent(true));
    }

    public void showLoadingDialog(String str) {
        LiveDatabus.getInstance().with(DTLiveDataConstant.SET_LOADING_DIALOG_EVENT).postValue(new LoadingLiveDataEvent(str, -1));
    }

    public void onDestroy() {
        super.onDestroy();
        closeLoadingDialog();
        SelfChassis.getInstance().removeVersionUpgradeListener(this);
    }

    public String getSelectUrlDesc(int i) {
        if (i == 3 || i == 0) {
            return StringUtils.getString(R.string.txt_li_ce);
        }
        if (i == 1) {
            return StringUtils.getString(R.string.txt_lan_hai);
        }
        if (i == 2) {
            return StringUtils.getString(R.string.txt_customize);
        }
        return "";
    }

    private List<OnlineUpdateUrlBean> getUpdateUrls() {
        List<OnlineUpdateUrlBean> onlineUpdateUrls = ChassisSpUtils.getOnlineUpdateUrls();
        onlineUpdateUrls.add(0, new OnlineUpdateUrlBean(DeploymentToolConstant.UPDATE_URL_LAN_HAI, 1, true, "TY126AAc001"));
        onlineUpdateUrls.add(1, new OnlineUpdateUrlBean(DeploymentToolConstant.UPDATE_URL_LI_CE, 0, true, "TY126AAc002"));
        onlineUpdateUrls.add(2, new OnlineUpdateUrlBean(DeploymentToolConstant.UPDATE_URL_LI_CE, 3, true, StringUtils.getString(R.string.txt_tip_lice_old_version_update_url)));
        return onlineUpdateUrls;
    }

    /* access modifiers changed from: private */
    public OnlineUpdateUrlBean getCustomUrl(String str) {
        return new OnlineUpdateUrlBean(str, 2, false, "");
    }
}
