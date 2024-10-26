package mc.csst.com.selfchassis.ui.activity.set;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.CoroutineLiveDataKt;
import androidx.lifecycle.Observer;
import com.blankj.utilcode.util.ColorUtils;
import com.ciot.base.livedatabus.LiveDatabus;
import com.ciot.base.util.MultiLanguageUtil;
import com.ciot.base.util.MyLogUtils;
import com.ciot.sentrymove.R;
import java.lang.ref.WeakReference;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import mc.csst.com.selfchassis.ui.activity.base.BaseActivity;
import mc.csst.com.selfchassis.ui.dialog.ConfirmDialog;
import mc.csst.com.selfchassis.ui.dialog.LoadingDialog2;
import mc.csst.com.selfchassis.ui.fragment.set.config.ConfigFragment;
import mc.csst.com.selfchassis.ui.fragment.set.diagnose.DiagnoseFragment;
import mc.csst.com.selfchassis.ui.fragment.set.language.LanguageFragment;
import mc.csst.com.selfchassis.ui.fragment.set.map.MapFragment;
import mc.csst.com.selfchassis.ui.fragment.set.schedule.ScheduleFragment;
import mc.csst.com.selfchassis.ui.fragment.set.version.VersionControlFragment;
import mc.csst.com.selfchassis.utils.RxTimer;
import mc.csst.com.selfchassis.utils.bean.LoadingLiveDataEvent;
import mc.csst.com.selfchassis.utils.constant.DTLiveDataConstant;
import mc.csst.com.selfchassislibrary.bean.msg.UpgradeDownloadStatusBean;
import mc.csst.com.selfchassislibrary.chassis.SelfChassis;
import mc.csst.com.selfchassislibrary.utils.SelfChassisListenerUtils;
import mc.csst.com.selfchassislibrary.utils.eventbus.SelfChassisEventMsg;

public class SetActivity extends BaseActivity implements View.OnClickListener {
    private static final int DIALOG_INTERVAL_DETECT_TIME = 5000;
    private ConfigFragment configFragment;
    private Fragment currentFragment = null;
    private DiagnoseFragment diagnoseFragment;
    private AppCompatImageView ivBack;
    private LanguageFragment languageFragment;
    /* access modifiers changed from: private */
    public WeakReference<LoadingDialog2> mLoadingDialog;
    private volatile int mLoadingTimeOut = 0;
    private RxTimer mRxTimer;
    private MapFragment mapFragment;
    /* access modifiers changed from: private */
    public NumberFormat numberFormatPercent = NumberFormat.getPercentInstance();
    private ScheduleFragment scheduleFragment;
    private AppCompatTextView tvConfig;
    private AppCompatTextView tvDiagnose;
    private List<Integer> tvItemIdList = new ArrayList();
    private AppCompatTextView tvLanguage;
    private AppCompatTextView tvVersion;
    private VersionControlFragment versionFragment;

    /* access modifiers changed from: protected */
    public void initData() {
    }

    /* access modifiers changed from: protected */
    public View getView() {
        return LayoutInflater.from(this).inflate(R.layout.activity_set, (ViewGroup) null);
    }

    /* access modifiers changed from: protected */
    public void initView() {
        this.tvItemIdList.add(Integer.valueOf(R.id.tv_set_config));
        this.tvItemIdList.add(Integer.valueOf(R.id.tv_set_diagnose));
        this.tvItemIdList.add(Integer.valueOf(R.id.tv_set_version));
        this.tvItemIdList.add(Integer.valueOf(R.id.tv_set_language));
        this.tvItemIdList.add(Integer.valueOf(R.id.tv_multi_schedule_setting));
        this.tvItemIdList.add(Integer.valueOf(R.id.tv_map_setting));
        this.ivBack = (AppCompatImageView) findViewById(R.id.iv_back);
        this.tvConfig = (AppCompatTextView) findViewById(R.id.tv_set_config);
        this.tvDiagnose = (AppCompatTextView) findViewById(R.id.tv_set_diagnose);
        this.tvVersion = (AppCompatTextView) findViewById(R.id.tv_set_version);
        this.tvLanguage = (AppCompatTextView) findViewById(R.id.tv_set_language);
        Fragment findFragmentByTag = getSupportFragmentManager().findFragmentByTag(ConfigFragment.class.getSimpleName());
        if (findFragmentByTag == null) {
            this.configFragment = new ConfigFragment();
        } else {
            this.configFragment = (ConfigFragment) findFragmentByTag;
        }
        Fragment findFragmentByTag2 = getSupportFragmentManager().findFragmentByTag(DiagnoseFragment.class.getSimpleName());
        if (findFragmentByTag2 == null) {
            this.diagnoseFragment = new DiagnoseFragment();
        } else {
            this.diagnoseFragment = (DiagnoseFragment) findFragmentByTag2;
        }
        Fragment findFragmentByTag3 = getSupportFragmentManager().findFragmentByTag(VersionControlFragment.class.getSimpleName());
        if (findFragmentByTag3 == null) {
            this.versionFragment = VersionControlFragment.newInstance();
        } else {
            this.versionFragment = (VersionControlFragment) findFragmentByTag3;
        }
        Fragment findFragmentByTag4 = getSupportFragmentManager().findFragmentByTag(LanguageFragment.class.getSimpleName());
        if (findFragmentByTag4 == null) {
            this.languageFragment = new LanguageFragment();
        } else {
            this.languageFragment = (LanguageFragment) findFragmentByTag4;
        }
        Fragment findFragmentByTag5 = getSupportFragmentManager().findFragmentByTag(MapFragment.class.getSimpleName());
        if (findFragmentByTag5 == null) {
            this.mapFragment = new MapFragment();
        } else {
            this.mapFragment = (MapFragment) findFragmentByTag5;
        }
        Fragment findFragmentByTag6 = getSupportFragmentManager().findFragmentByTag(ScheduleFragment.class.getSimpleName());
        if (findFragmentByTag6 == null) {
            this.scheduleFragment = new ScheduleFragment();
        } else {
            this.scheduleFragment = (ScheduleFragment) findFragmentByTag6;
        }
        switchFragment(this.configFragment);
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
        for (Integer intValue : this.tvItemIdList) {
            findViewById(intValue.intValue()).setOnClickListener(this);
        }
        findViewById(R.id.btn_set_disconnect).setOnClickListener(this);
        findViewById(R.id.ll_back).setOnClickListener(this);
        LiveDatabus.getInstance().with(DTLiveDataConstant.MAIN_CONNECT_DIALOG_EVENT).observe(this, new Observer() {
            public final void onChanged(Object obj) {
                SetActivity.this.lambda$initEvent$0$SetActivity(obj);
            }
        });
        LiveDatabus.getInstance().with(DTLiveDataConstant.SET_LOADING_DIALOG_EVENT).observe(this, new Observer() {
            public final void onChanged(Object obj) {
                SetActivity.this.lambda$initEvent$1$SetActivity(obj);
            }
        });
    }

    public /* synthetic */ void lambda$initEvent$0$SetActivity(Object obj) {
        if (obj instanceof String) {
            String obj2 = obj.toString();
            String str = this.TAG;
            MyLogUtils.Logd(str, "接收到网络变化直接退出设置界面：" + obj2);
            SelfChassis.getInstance().disconnectSelfChassis();
            finish();
        }
    }

    public /* synthetic */ void lambda$initEvent$1$SetActivity(Object obj) {
        if (obj instanceof LoadingLiveDataEvent) {
            LoadingLiveDataEvent loadingLiveDataEvent = (LoadingLiveDataEvent) obj;
            if (loadingLiveDataEvent.isCloseDialog()) {
                closeLoadingDialog();
                return;
            }
            this.mLoadingTimeOut = loadingLiveDataEvent.getTimeOut();
            String str = this.TAG;
            MyLogUtils.Logd(str, "mLoadingTimeOut：" + this.mLoadingTimeOut);
            showLoadingDialog(loadingLiveDataEvent.getLoadingTxt());
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        SelfChassisListenerUtils.getInstance().setUpgradeDownloadStatusListener(new SelfChassisListenerUtils.OnBaseSelfChassisListener() {
            public void onSelfChassisMsg(final SelfChassisEventMsg selfChassisEventMsg) {
                SetActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        if (SetActivity.this.mLoadingDialog != null && SetActivity.this.mLoadingDialog.get() != null && ((LoadingDialog2) SetActivity.this.mLoadingDialog.get()).isShow()) {
                            UpgradeDownloadStatusBean upgradeDownloadStatusBean = (UpgradeDownloadStatusBean) selfChassisEventMsg.getData();
                            ((LoadingDialog2) SetActivity.this.mLoadingDialog.get()).setProgress(SetActivity.this.numberFormatPercent.format(upgradeDownloadStatusBean.getMsg().getData()));
                            if (upgradeDownloadStatusBean.getMsg().getData().doubleValue() >= 0.0d && upgradeDownloadStatusBean.getMsg().getData().doubleValue() < 0.25d) {
                                updateState(false, R.string.text_installing_upgrade_file);
                            } else if (upgradeDownloadStatusBean.getMsg().getData().doubleValue() < 0.25d || upgradeDownloadStatusBean.getMsg().getData().doubleValue() >= 0.8d) {
                                updateState(false, R.string.text_installing_upgrade_file);
                            } else {
                                updateState(true, R.string.text_downloading_upgrade_file);
                            }
                        }
                    }

                    private void updateState(boolean z, int i) {
                        ((LoadingDialog2) SetActivity.this.mLoadingDialog.get()).setButtonVisible(z);
                        ((LoadingDialog2) SetActivity.this.mLoadingDialog.get()).setLoadingText(SetActivity.this.getString(i));
                        ((LoadingDialog2) SetActivity.this.mLoadingDialog.get()).updateLoadingText(SetActivity.this.getString(i));
                    }
                });
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        SelfChassisListenerUtils.getInstance().setUpgradeDownloadStatusListener((SelfChassisListenerUtils.OnBaseSelfChassisListener) null);
    }

    private void switchFragment(Fragment fragment) {
        if (fragment != null && fragment != this.currentFragment) {
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            if (!fragment.isAdded()) {
                Fragment fragment2 = this.currentFragment;
                if (fragment2 != null) {
                    beginTransaction.hide(fragment2).add((int) R.id.fl_set_container, fragment, fragment.getClass().getSimpleName()).commit();
                } else {
                    beginTransaction.add((int) R.id.fl_set_container, fragment, fragment.getClass().getSimpleName()).commit();
                }
            } else {
                beginTransaction.hide(this.currentFragment).show(fragment).commit();
            }
            this.currentFragment = fragment;
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        dealSelfChassisState();
        supportRtl();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_set_disconnect:
                ConfirmDialog buildDefault2 = ConfirmDialog.buildDefault2(getString(R.string.chassis_connection), getString(R.string.chassis_connection_confirm));
                buildDefault2.setOnOkButtonClickListener(new ConfirmDialog.OnDialogButtonClickListener() {
                    public final boolean onClick(View view) {
                        return SetActivity.this.lambda$onClick$2$SetActivity(view);
                    }
                });
                buildDefault2.showAllowingStateLoss(getSupportFragmentManager());
                return;
            case R.id.ll_back:
                finish();
                return;
            case R.id.tv_map_setting:
                switchFragment(this.mapFragment);
                setHighLight(view.getId());
                return;
            case R.id.tv_multi_schedule_setting:
                switchFragment(this.scheduleFragment);
                setHighLight(view.getId());
                return;
            case R.id.tv_set_config:
                switchFragment(this.configFragment);
                setHighLight(view.getId());
                return;
            case R.id.tv_set_diagnose:
                switchFragment(this.diagnoseFragment);
                setHighLight(view.getId());
                return;
            case R.id.tv_set_language:
                switchFragment(this.languageFragment);
                setHighLight(view.getId());
                return;
            case R.id.tv_set_version:
                switchFragment(this.versionFragment);
                setHighLight(view.getId());
                return;
            default:
                return;
        }
    }

    public /* synthetic */ boolean lambda$onClick$2$SetActivity(View view) {
        SelfChassis.getInstance().disconnectSelfChassis();
        finish();
        return false;
    }

    private void setHighLight(int i) {
        for (Integer intValue : this.tvItemIdList) {
            int intValue2 = intValue.intValue();
            TextView textView = (TextView) findViewById(intValue2);
            if (intValue2 == i) {
                textView.setBackground(getDrawable(R.drawable.set_choose_selected_bg));
                textView.setTextColor(ColorUtils.getColor(R.color.clr_0560FD));
            } else {
                textView.setBackgroundColor(ColorUtils.getColor(R.color.white));
                textView.setTextColor(ColorUtils.getColor(R.color.black));
            }
        }
    }

    private void supportRtl() {
        if (MultiLanguageUtil.getInstance().isRtlLanguage()) {
            this.ivBack.setRotation(180.0f);
        }
    }

    private void dealSelfChassisState() {
        if (!SelfChassis.getInstance().isConnect()) {
            closeLoadingDialog();
            ConfirmDialog buildDefault = ConfirmDialog.buildDefault(getString(R.string.tpis), getString(R.string.connect_chassis_timeout));
            buildDefault.setOnOkButtonClickListener(new ConfirmDialog.OnDialogButtonClickListener() {
                public final boolean onClick(View view) {
                    return SetActivity.this.lambda$dealSelfChassisState$3$SetActivity(view);
                }
            });
            if (!buildDefault.isAdded()) {
                buildDefault.showAllowingStateLoss(getSupportFragmentManager());
            }
        }
    }

    public /* synthetic */ boolean lambda$dealSelfChassisState$3$SetActivity(View view) {
        finish();
        return false;
    }

    private void showLoadingDialog(String str) {
        WeakReference<LoadingDialog2> weakReference = this.mLoadingDialog;
        if (weakReference == null || weakReference.get() == null) {
            this.mLoadingDialog = new WeakReference<>(LoadingDialog2.newInstance(str));
        }
        ((LoadingDialog2) this.mLoadingDialog.get()).setLoadingText(str);
        ((LoadingDialog2) this.mLoadingDialog.get()).setOnCancelClickListener($$Lambda$SetActivity$RiJ0DRyYvrv0HED76U2ARGwuEM.INSTANCE);
        closeTimer();
        if (this.mRxTimer == null) {
            RxTimer rxTimer = new RxTimer();
            this.mRxTimer = rxTimer;
            rxTimer.interval(CoroutineLiveDataKt.DEFAULT_TIMEOUT, new RxTimer.RxAction() {
                public final void action(long j) {
                    SetActivity.this.lambda$showLoadingDialog$6$SetActivity(j);
                }
            });
        }
        if (!((LoadingDialog2) this.mLoadingDialog.get()).isAdded()) {
            ((LoadingDialog2) this.mLoadingDialog.get()).showAllowingStateLoss(getSupportFragmentManager());
        }
    }

    public /* synthetic */ void lambda$showLoadingDialog$6$SetActivity(long j) {
        String str = this.TAG;
        MyLogUtils.Logd(str, "mRxTimer mRxTimer:" + j);
        if (!SelfChassis.getInstance().isConnect()) {
            closeLoadingDialog();
            ConfirmDialog buildDefault = ConfirmDialog.buildDefault(getString(R.string.tpis), getString(R.string.connect_chassis_timeout));
            buildDefault.setOnOkButtonClickListener(new ConfirmDialog.OnDialogButtonClickListener() {
                public final boolean onClick(View view) {
                    return SetActivity.this.lambda$showLoadingDialog$5$SetActivity(view);
                }
            });
            if (!buildDefault.isAdded()) {
                buildDefault.showAllowingStateLoss(getSupportFragmentManager());
            }
        } else if (this.mLoadingTimeOut > 0 && (j + 1) * CoroutineLiveDataKt.DEFAULT_TIMEOUT >= ((long) this.mLoadingTimeOut) * 1000) {
            closeLoadingDialog();
            ConfirmDialog buildDefault2 = ConfirmDialog.buildDefault(getString(R.string.tpis), getString(R.string.txt_timeout_failed));
            if (!buildDefault2.isAdded()) {
                buildDefault2.showAllowingStateLoss(getSupportFragmentManager());
            }
        }
    }

    public /* synthetic */ boolean lambda$showLoadingDialog$5$SetActivity(View view) {
        finish();
        return false;
    }

    private void closeLoadingDialog() {
        WeakReference<LoadingDialog2> weakReference = this.mLoadingDialog;
        if (!(weakReference == null || weakReference.get() == null || !((LoadingDialog2) this.mLoadingDialog.get()).isAdded())) {
            ((LoadingDialog2) this.mLoadingDialog.get()).setButtonVisible(false);
            ((LoadingDialog2) this.mLoadingDialog.get()).setProgress("");
            ((LoadingDialog2) this.mLoadingDialog.get()).dismissAllowingStateLoss();
        }
        closeTimer();
    }

    private void closeTimer() {
        if (this.mRxTimer != null) {
            MyLogUtils.Logd(this.TAG, "触发cancelTimer");
            this.mRxTimer.cancel();
            this.mRxTimer = null;
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        closeLoadingDialog();
    }
}
