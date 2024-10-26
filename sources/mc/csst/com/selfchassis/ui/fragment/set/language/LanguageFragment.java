package mc.csst.com.selfchassis.ui.fragment.set.language;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.blankj.utilcode.util.SPUtils;
import com.ciot.base.util.ContextUtil;
import com.ciot.base.util.GsonUtils;
import com.ciot.base.util.MultiLanguageUtil;
import com.ciot.sentrymove.R;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import mc.csst.com.selfchassis.databinding.FragmentSetLanguageBinding;
import mc.csst.com.selfchassis.ui.adb.AdbActivity;
import mc.csst.com.selfchassis.ui.dialog.ConfirmDialog;
import mc.csst.com.selfchassis.ui.fragment.base.BaseFragment;
import mc.csst.com.selfchassis.ui.fragment.set.language.bean.CommonAddressBean;
import mc.csst.com.selfchassis.ui.fragment.set.language.bean.GetGatewayRespBean;
import mc.csst.com.selfchassis.ui.fragment.set.language.transaction.GatewayTool;
import mc.csst.com.selfchassis.utils.MyToastUtils;
import mc.csst.com.selfchassislibrary.chassis.SelfChassis;

public class LanguageFragment extends BaseFragment implements View.OnClickListener {
    /* access modifiers changed from: private */
    public int count;
    GatewayViewModel gatewayViewModel;
    /* access modifiers changed from: private */
    public Handler handler = new Handler();
    FragmentSetLanguageBinding languageBinding;
    ConfirmDialog mConfirmDialog;

    static /* synthetic */ int access$108(LanguageFragment languageFragment) {
        int i = languageFragment.count;
        languageFragment.count = i + 1;
        return i;
    }

    /* access modifiers changed from: protected */
    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentSetLanguageBinding fragmentSetLanguageBinding = (FragmentSetLanguageBinding) DataBindingUtil.inflate(layoutInflater, R.layout.fragment_set_language, viewGroup, false);
        this.languageBinding = fragmentSetLanguageBinding;
        return fragmentSetLanguageBinding.getRoot();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.gatewayViewModel = (GatewayViewModel) ViewModelProviders.of((Fragment) this).get(GatewayViewModel.class);
    }

    /* access modifiers changed from: protected */
    public void initData() {
        observe();
        setLanguage();
        GatewayTool.wuHanAddressList.clear();
        GatewayTool.localAddressList.clear();
        GatewayTool.getInstance().initWuhanGateway();
        initLocalGateway();
        this.languageBinding.gpwDownload.setFragmentManager(getChildFragmentManager());
    }

    private void initLocalGateway() {
        List list = (List) GsonUtils.fromLocalJson(SPUtils.getInstance().getString("gateway", ""), new TypeToken<List<CommonAddressBean>>() {
        }.getType());
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (!GatewayTool.getInstance().getCurrentGateway().equalsIgnoreCase(((CommonAddressBean) list.get(i)).getUrl())) {
                    GatewayTool.localAddressList.add((CommonAddressBean) list.get(i));
                }
            }
        }
    }

    private void observe() {
        this.gatewayViewModel.setGateway.observe(this, new Observer<Boolean>() {
            public void onChanged(Boolean bool) {
                if (bool.booleanValue()) {
                    GatewayTool.currentGateway = new GetGatewayRespBean(GatewayTool.getInstance().getCurrentGateway());
                    MyToastUtils.showShort(LanguageFragment.this.getContext(), LanguageFragment.this.getString(R.string.txt_tip_set_gateway_success));
                    return;
                }
                MyToastUtils.showShort(LanguageFragment.this.getContext(), LanguageFragment.this.getString(R.string.txt_tip_set_gateway_fail));
                LanguageFragment.this.handler.removeCallbacksAndMessages((Object) null);
                LanguageFragment.this.handler.post(new Runnable() {
                    public void run() {
                        LanguageFragment.this.languageBinding.btnSureSwitch.setEnabled(true);
                    }
                });
            }
        });
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
        this.languageBinding.rbSimplifiedChinese.setOnClickListener(this);
        this.languageBinding.rbTraditionalChinese.setOnClickListener(this);
        this.languageBinding.rbEnglish.setOnClickListener(this);
        this.languageBinding.rbArabic.setOnClickListener(this);
        this.languageBinding.rbJapanese.setOnClickListener(this);
        this.languageBinding.rbKorean.setOnClickListener(this);
        this.languageBinding.rbThai.setOnClickListener(this);
        this.languageBinding.rbGerman.setOnClickListener(this);
        this.languageBinding.rbIndonesian.setOnClickListener(this);
        this.languageBinding.rbHebrew.setOnClickListener(this);
        this.languageBinding.rbTurkish.setOnClickListener(this);
        this.languageBinding.btnSureSwitch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LanguageFragment.this.languageBinding.btnSureSwitch.setEnabled(false);
                LanguageFragment.this.handler.postDelayed(new Runnable() {
                    public void run() {
                        LanguageFragment.this.languageBinding.btnSureSwitch.setEnabled(true);
                    }
                }, 30000);
                LanguageFragment.this.gatewayViewModel.setGateway(GatewayTool.getInstance().getCurrentGateway());
            }
        });
        this.languageBinding.tvSwitchLanguage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (LanguageFragment.this.count == 0) {
                    LanguageFragment.this.handler.postDelayed(new Runnable() {
                        public void run() {
                            int unused = LanguageFragment.this.count = 0;
                        }
                    }, 3000);
                }
                LanguageFragment.access$108(LanguageFragment.this);
                if (LanguageFragment.this.count > 10) {
                    LanguageFragment.this.startActivity(new Intent(LanguageFragment.this.getContext(), AdbActivity.class));
                    int unused = LanguageFragment.this.count = 0;
                }
            }
        });
    }

    private void showRestartDialog(int i) {
        Context context = ContextUtil.getContext();
        ConfirmDialog confirmDialog = this.mConfirmDialog;
        if (confirmDialog != null && confirmDialog.isAdded()) {
            this.mConfirmDialog.dismissAllowingStateLoss();
        }
        ConfirmDialog build = ConfirmDialog.build(context.getResources().getString(R.string.tpis), context.getResources().getString(R.string.click_to_change), context.getResources().getString(R.string.restart), context.getResources().getString(R.string.dialog_cancle));
        this.mConfirmDialog = build;
        build.setOnOkButtonClickListener(new ConfirmDialog.OnDialogButtonClickListener(i) {
            public final /* synthetic */ int f$0;

            {
                this.f$0 = r1;
            }

            public final boolean onClick(View view) {
                return LanguageFragment.lambda$showRestartDialog$1(this.f$0, view);
            }
        });
        this.mConfirmDialog.setOnCancelButtonClickListener(new ConfirmDialog.OnDialogButtonClickListener() {
            public final boolean onClick(View view) {
                return LanguageFragment.this.lambda$showRestartDialog$2$LanguageFragment(view);
            }
        });
        this.mConfirmDialog.showAllowingStateLoss(getChildFragmentManager());
    }

    static /* synthetic */ boolean lambda$showRestartDialog$1(int i, View view) {
        SelfChassis.getInstance().disconnectSelfChassis();
        MultiLanguageUtil.getInstance().updateLanguage(i);
        new Handler().postDelayed($$Lambda$LanguageFragment$o7M3rRin1aSrbEZ6atg635uq81A.INSTANCE, 100);
        return false;
    }

    public /* synthetic */ boolean lambda$showRestartDialog$2$LanguageFragment(View view) {
        setLanguage();
        return false;
    }

    private void setLanguage() {
        int languageType = MultiLanguageUtil.getInstance().getLanguageType();
        if (languageType == 0) {
            this.languageBinding.rbSimplifiedChinese.setChecked(true);
        } else if (languageType == 1) {
            this.languageBinding.rbTraditionalChinese.setChecked(true);
        } else if (languageType == 2) {
            this.languageBinding.rbEnglish.setChecked(true);
        } else if (languageType == 3) {
            this.languageBinding.rbThai.setChecked(true);
        } else if (languageType == 6) {
            this.languageBinding.rbArabic.setChecked(true);
        } else if (languageType == 4) {
            this.languageBinding.rbJapanese.setChecked(true);
        } else if (languageType == 5) {
            this.languageBinding.rbKorean.setChecked(true);
        } else if (languageType == 7) {
            this.languageBinding.rbGerman.setChecked(true);
        } else if (languageType == 8) {
            this.languageBinding.rbIndonesian.setChecked(true);
        } else if (languageType == 9) {
            this.languageBinding.rbHebrew.setChecked(true);
        } else if (languageType == 10) {
            this.languageBinding.rbTurkish.setChecked(true);
        }
    }

    public void onClick(View view) {
        int i;
        int id = view.getId();
        if (!(id == R.id.rb_arabic || id == R.id.rb_korean || id == R.id.rb_simplified_chinese)) {
            switch (id) {
                case R.id.rb_english:
                case R.id.rb_german:
                case R.id.rb_hebrew:
                case R.id.rb_indonesian:
                case R.id.rb_japanese:
                    break;
                default:
                    switch (id) {
                        case R.id.rb_thai:
                        case R.id.rb_traditional_chinese:
                        case R.id.rb_turkish:
                            break;
                        default:
                            return;
                    }
            }
        }
        if (this.languageBinding.rbSimplifiedChinese.isChecked()) {
            i = 0;
        } else if (this.languageBinding.rbTraditionalChinese.isChecked()) {
            i = 1;
        } else if (this.languageBinding.rbThai.isChecked()) {
            i = 3;
        } else if (this.languageBinding.rbArabic.isChecked()) {
            i = 6;
        } else if (this.languageBinding.rbJapanese.isChecked()) {
            i = 4;
        } else if (this.languageBinding.rbKorean.isChecked()) {
            i = 5;
        } else if (this.languageBinding.rbGerman.isChecked()) {
            i = 7;
        } else if (this.languageBinding.rbIndonesian.isChecked()) {
            i = 8;
        } else if (this.languageBinding.rbHebrew.isChecked()) {
            i = 9;
        } else {
            i = this.languageBinding.rbTurkish.isChecked() ? 10 : 2;
        }
        if (i != MultiLanguageUtil.getInstance().getLanguageType()) {
            showRestartDialog(i);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        ConfirmDialog confirmDialog = this.mConfirmDialog;
        if (confirmDialog != null && confirmDialog.isAdded()) {
            this.mConfirmDialog.dismissAllowingStateLoss();
        }
    }
}
