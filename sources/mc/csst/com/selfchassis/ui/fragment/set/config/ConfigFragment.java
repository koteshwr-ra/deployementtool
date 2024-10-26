package mc.csst.com.selfchassis.ui.fragment.set.config;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.DataBindingUtil;
import com.blankj.utilcode.util.AdaptScreenUtils;
import com.blankj.utilcode.util.ClickUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.ciot.base.constant.NetConstant;
import com.ciot.base.livedatabus.LiveDatabus;
import com.ciot.base.storage.MySpUtils;
import com.ciot.base.util.ContextUtil;
import com.ciot.base.util.MyLogUtils;
import com.ciot.sentrymove.R;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;
import java.util.List;
import mc.csst.com.selfchassis.App;
import mc.csst.com.selfchassis.adapter.SensorAdapter;
import mc.csst.com.selfchassis.databinding.FragmentSetConfigBinding;
import mc.csst.com.selfchassis.ui.dialog.ConfirmDialog;
import mc.csst.com.selfchassis.ui.dialog.EditDialog;
import mc.csst.com.selfchassis.ui.dialog.ElevatorDialog;
import mc.csst.com.selfchassis.ui.fragment.base.BaseFragment;
import mc.csst.com.selfchassis.ui.fragment.set.config.ConfigFragment;
import mc.csst.com.selfchassis.utils.BanSlidingLayoutManager;
import mc.csst.com.selfchassis.utils.MyToastUtils;
import mc.csst.com.selfchassis.utils.SpConstant;
import mc.csst.com.selfchassis.utils.Utils;
import mc.csst.com.selfchassis.utils.bean.LoadingLiveDataEvent;
import mc.csst.com.selfchassis.utils.constant.DTLiveDataConstant;
import mc.csst.com.selfchassis.utils.constant.DeploymentToolConstant;
import mc.csst.com.selfchassislibrary.bean.msg.DownCameraAutoCalibrateResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.GetDoorLengthBean;
import mc.csst.com.selfchassislibrary.bean.msg.GetGateLengthBean;
import mc.csst.com.selfchassislibrary.bean.msg.GetMatchThresholdBean;
import mc.csst.com.selfchassislibrary.bean.msg.LabelCameraExtrinsicConfigRespBean;
import mc.csst.com.selfchassislibrary.bean.msg.LaserDetectionSettingRespBean;
import mc.csst.com.selfchassislibrary.bean.msg.LocatedModeConfigRespBean;
import mc.csst.com.selfchassislibrary.bean.msg.MinNumCommandResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.MinNumFindResultResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.MinNumStatusResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.NaviSettingBean;
import mc.csst.com.selfchassislibrary.bean.msg.NaviSettingResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.RobotInfoResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.SelfDiagnosisResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.SensorFeaturesBean;
import mc.csst.com.selfchassislibrary.bean.msg.SensorResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.UltrasonicDistanceBean;
import mc.csst.com.selfchassislibrary.bean.msg.UltrasonicDistanceResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.UpCameraAutoCalibrateResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.VelocityControlResponseBean;
import mc.csst.com.selfchassislibrary.chassis.SelfChassis;
import mc.csst.com.selfchassislibrary.content.IDContent;
import mc.csst.com.selfchassislibrary.content.NaviSettingContent;
import mc.csst.com.selfchassislibrary.content.SensorSettingContent;
import mc.csst.com.selfchassislibrary.content.ServiceContent;
import mc.csst.com.selfchassislibrary.utils.LanguageUtils;
import mc.csst.com.selfchassislibrary.utils.SelfChassisListenerUtils;
import mc.csst.com.selfchassislibrary.utils.eventbus.SelfChassisEventMsg;
import org.apache.commons.lang3.CharUtils;

public class ConfigFragment extends BaseFragment implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener, SensorAdapter.OnItemSwitchClickListener {
    private static final int CAMERA_CALIBRATE_TIMEOUT = 180;
    private final float MAX_SPEED = 0.8f;
    private final float MIN_SPEED = 0.1f;
    public final String TAG = getClass().getSimpleName();
    FragmentSetConfigBinding configBinding;
    private boolean isSupportNaviSetting = false;
    private boolean isSupportUltrasonicDistance = false;
    private MyOnBaseSelfChassisListener mDownCameraAutoCalibrateListener;
    private EditDialog mEditDialog;
    private MyOnBaseSelfChassisListener mRobotInfoListener;
    int mSpeedType = 0;
    int mTravelMode = 0;
    private MyOnBaseSelfChassisListener mUpCameraAutoCalibrateListener;
    private SensorAdapter sensorAdapter;
    private List<SensorFeaturesBean> sensors;

    private int fromActualSpeed(float f) {
        if (f == 0.0f) {
            return 0;
        }
        return (int) (((f - 0.1f) * 100.0f) / 0.7f);
    }

    private int getCheckId(int i) {
        if (i == 0) {
            return R.id.rb_security;
        }
        if (i == 3) {
            return R.id.rb_balance;
        }
        if (i != 6) {
            return -1;
        }
        return R.id.rb_efficiency;
    }

    /* access modifiers changed from: private */
    public float toActualSpeed(int i) {
        return ((((float) i) * 0.7f) / 100.0f) + 0.1f;
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    /* access modifiers changed from: protected */
    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentSetConfigBinding fragmentSetConfigBinding = (FragmentSetConfigBinding) DataBindingUtil.inflate(layoutInflater, R.layout.fragment_set_config, viewGroup, false);
        this.configBinding = fragmentSetConfigBinding;
        return fragmentSetConfigBinding.getRoot();
    }

    /* access modifiers changed from: protected */
    public void initData() {
        this.configBinding.rgControl.check((MySpUtils.getInstance().getInt(SpConstant.KEY_MODE, 1) == 0 ? this.configBinding.rbKeyFour : this.configBinding.rbKeyEight).getId());
        this.configBinding.rgNavMode.check((MySpUtils.getInstance().getInt(SpConstant.NAV_MODE, 0) == 0 ? this.configBinding.rbNavAuto : this.configBinding.rbNavVirtual).getId());
        this.configBinding.switchManual.setChecked(MySpUtils.getInstance().getBoolean(SpConstant.ROCKER_MODE, true));
        this.configBinding.includeOtherConfig.switchSmooth.setChecked(MySpUtils.getInstance().getBoolean(SpConstant.SMOOTH_MODE));
        rockerModeView();
        smoothModeView();
        this.sensorAdapter = new SensorAdapter(this.context);
        this.configBinding.rvSensor.setLayoutManager(new BanSlidingLayoutManager(this.context));
        this.configBinding.rvSensor.setAdapter(this.sensorAdapter);
        this.configBinding.rvSensor.setFocusable(false);
        changeBtnSetAsThreshold(false);
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
        this.configBinding.rgTravel.setOnCheckedChangeListener(this);
        this.configBinding.rgNavMode.setOnCheckedChangeListener(this);
        this.configBinding.includeOtherConfig.rgLabelCameraInstallCoordinateType.setOnCheckedChangeListener(this);
        this.configBinding.includeOtherConfig.rgLocatedType.setOnCheckedChangeListener(this);
        this.configBinding.includeNavParamConfig.tvLaserRadarObstacleDetection.setOnClickListener(this);
        this.configBinding.includeNavParamConfig.switchLaserRadarObstacleDetection.setOnCheckedChangeListener(this);
        this.configBinding.btnSaveSet.setOnClickListener(this);
        this.configBinding.includeOtherConfig.btnElevatorSet.setOnClickListener(this);
        this.configBinding.rgControl.setOnCheckedChangeListener(this);
        this.configBinding.switchManual.setOnCheckedChangeListener(this);
        this.configBinding.includeOtherConfig.switchSmooth.setOnCheckedChangeListener(this);
        this.configBinding.includeOtherConfig.sbThreshold.setOnSeekBarChangeListener(this);
        this.configBinding.sbSpeedConfig.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                ConfigFragment.this.configBinding.tvSpeedConfig.setText(String.format("%.2f", new Object[]{Float.valueOf(ConfigFragment.this.toActualSpeed(i))}));
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                SelfChassis.getInstance().configMaxVelX(ConfigFragment.this.toActualSpeed(seekBar.getProgress()));
            }
        });
        this.sensorAdapter.setOnItemSwitchListener(this);
        SelfChassis.getInstance().setMatchThresholdListener(new MyOnBaseSelfChassisListener());
        SelfChassis.getInstance().setGetSensorsListener(new MyOnBaseSelfChassisListener());
        SelfChassis.getInstance().setVelocityControlListener(new MyOnBaseSelfChassisListener());
        SelfChassisListenerUtils.getInstance().setDoorLengthListener(new MyOnBaseSelfChassisListener());
        SelfChassisListenerUtils.getInstance().setGateLengthListener(new MyOnBaseSelfChassisListener());
        this.mUpCameraAutoCalibrateListener = new MyOnBaseSelfChassisListener();
        SelfChassisListenerUtils.getInstance().addUpCameraAutoCalibrateListener(this.mUpCameraAutoCalibrateListener);
        this.mDownCameraAutoCalibrateListener = new MyOnBaseSelfChassisListener();
        SelfChassisListenerUtils.getInstance().addDownCameraAutoCalibrateListener(this.mDownCameraAutoCalibrateListener);
        SelfChassisListenerUtils.getInstance().setMinNumCommandListener(new MyOnBaseSelfChassisListener());
        SelfChassisListenerUtils.getInstance().setMinNumStatusListener(new MyOnBaseSelfChassisListener());
        SelfChassisListenerUtils.getInstance().setMinNumFindResultListener(new MyOnBaseSelfChassisListener());
        SelfChassisListenerUtils.getInstance().setUltrasonicDistanceListener(new MyOnBaseSelfChassisListener());
        SelfChassis.getInstance().setNaviSettingListener(new MyOnBaseSelfChassisListener());
        this.mRobotInfoListener = new MyOnBaseSelfChassisListener();
        SelfChassis.getInstance().setRobotInfoListener(this.mRobotInfoListener);
        ClickUtils.applyGlobalDebouncing((View) this.configBinding.includeOtherConfig.tvDoorControlSize, (View.OnClickListener) this);
        ClickUtils.applyGlobalDebouncing((View) this.configBinding.includeOtherConfig.tvGateControlSize, (View.OnClickListener) this);
        ClickUtils.applyGlobalDebouncing((View) this.configBinding.includeOtherConfig.btnDepthCameraUpProbeCalibration, (View.OnClickListener) this);
        ClickUtils.applyGlobalDebouncing((View) this.configBinding.includeOtherConfig.btnDepthCameraDownProbeCalibration, (View.OnClickListener) this);
        ClickUtils.applyGlobalDebouncing((View) this.configBinding.includeOtherConfig.tvRecalculate, (View.OnClickListener) this);
        ClickUtils.applyGlobalDebouncing((View) this.configBinding.includeOtherConfig.btnSetAsThreshold, (View.OnClickListener) this);
        this.configBinding.includeOtherConfig.switchUltrasonicBeep.setOnCheckedChangeListener(this);
        ClickUtils.applyGlobalDebouncing((View) this.configBinding.includeOtherConfig.llUltrasonicBeep, (View.OnClickListener) this);
        ClickUtils.applyGlobalDebouncing((View) this.configBinding.includeNavParamConfig.llPointPosition, (View.OnClickListener) this);
        ClickUtils.applyGlobalDebouncing((View) this.configBinding.includeNavParamConfig.llPointRad, (View.OnClickListener) this);
        ClickUtils.applyGlobalDebouncing((View) this.configBinding.includeNavParamConfig.llSafeStopDistance, (View.OnClickListener) this);
        ClickUtils.applyGlobalDebouncing((View) this.configBinding.includeNavParamConfig.llNavFailureTime, (View.OnClickListener) this);
        dealChassisRequest();
    }

    private void dealChassisRequest() {
        SelfChassis.getInstance().serviceRobotInfo();
        SelfChassis.getInstance().serviceGetMatchThreshold();
        SelfChassis.getInstance().serviceGetVelocity();
        SelfChassis.getInstance().serviceGetSensors();
        changeBtnElevatorSet(App.getInstance().isOpenLife());
        SelfChassis.getInstance().serviceGetDoorLength();
        SelfChassis.getInstance().serviceGetGateLength();
        SelfChassis.getInstance().getMapMatchingStatusInfo();
        SelfChassis.getInstance().getNaviSetting();
        SelfChassis.getInstance().getUltrasonicDistance();
        SelfChassis.getInstance().labelCameraExtrinsicConfig(2);
        SelfChassis.getInstance().laserDetectionSetting(2, 0.0d, true);
        SelfChassis.getInstance().locatedModeConfig(2);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_save_set && Utils.isFastClick(1000)) {
            SelfChassis.getInstance().serviceConfigSensors(this.sensors);
            showLoadingDialog(getString(R.string.tip_config_sensors_saving));
        } else if (id == R.id.btn_elevator_set && Utils.isFastClick(1000)) {
            ElevatorDialog.newInstance().showAllowingStateLoss(getChildFragmentManager());
        } else if (id == R.id.tv_door_control_size) {
            showDoorControlSizeDialog();
        } else if (id == R.id.tv_gate_control_size) {
            showGateControlSizeDialog();
        } else if (id == R.id.btn_depth_camera_up_probe_calibration) {
            ConfirmDialog buildDefault2 = ConfirmDialog.buildDefault2(getString(R.string.txt_automatic_calibration), StringUtils.getString(R.string.txt_tip_automatic_calibration));
            buildDefault2.setOnOkButtonClickListener(new ConfirmDialog.OnDialogButtonClickListener() {
                public final boolean onClick(View view) {
                    return ConfigFragment.this.lambda$onClick$0$ConfigFragment(view);
                }
            });
            buildDefault2.showAllowingStateLoss(getChildFragmentManager());
        } else if (id == R.id.btn_depth_camera_down_probe_calibration) {
            ConfirmDialog buildDefault22 = ConfirmDialog.buildDefault2(getString(R.string.txt_automatic_calibration), StringUtils.getString(R.string.txt_tip_automatic_calibration));
            buildDefault22.setOnOkButtonClickListener(new ConfirmDialog.OnDialogButtonClickListener() {
                public final boolean onClick(View view) {
                    return ConfigFragment.this.lambda$onClick$1$ConfigFragment(view);
                }
            });
            buildDefault22.showAllowingStateLoss(getChildFragmentManager());
        } else if (id == R.id.tv_recalculate) {
            changeBtnSetAsThreshold(false);
            String string = StringUtils.getString(R.string.txt_start_calculating);
            String string2 = StringUtils.getString(R.string.txt_stop_calculating);
            String string3 = StringUtils.getString(R.string.txt_recalculate);
            String charSequence = this.configBinding.includeOtherConfig.tvRecalculate.getText().toString();
            if (TextUtils.equals(string, charSequence) || TextUtils.equals(string3, charSequence)) {
                SelfChassis.getInstance().startAutomaticMapMatchingCollection();
            } else if (TextUtils.equals(string2, charSequence)) {
                SelfChassis.getInstance().stopAutomaticMapMatchingCollection();
            }
        } else if (id == R.id.btn_set_as_threshold) {
            if (!TextUtils.isEmpty(this.configBinding.includeOtherConfig.tvCalculateThreshold.getText())) {
                try {
                    float parseFloat = Float.parseFloat(this.configBinding.includeOtherConfig.tvCalculateThreshold.getText().toString().trim());
                    this.configBinding.includeOtherConfig.sbThreshold.setProgress((int) (100.0f * parseFloat));
                    this.configBinding.includeOtherConfig.tvSecurityThreshold.setText(String.valueOf(parseFloat));
                    SelfChassis.getInstance().laserSafetyControllerConfidenceThreshold(parseFloat);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (id == R.id.ll_ultrasonic_beep) {
            showUltrasoundDistanceEditDialog();
        } else if (id == R.id.ll_point_position) {
            showPointPositionEditDialog();
        } else if (id == R.id.ll_point_rad) {
            showPointAngleEditDialog();
        } else if (id == R.id.ll_safe_stop_distance) {
            showSafeStopDistanceEditDialog();
        } else if (id == R.id.ll_nav_failure_time) {
            showNavFailureTimeEditDialog();
        } else if (id == R.id.tv_laser_radar_obstacle_detection) {
            showLaserRadarObstacleDetectionDialog();
        }
    }

    public /* synthetic */ boolean lambda$onClick$0$ConfigFragment(View view) {
        cameraAutoCalibrate(true);
        return false;
    }

    public /* synthetic */ boolean lambda$onClick$1$ConfigFragment(View view) {
        cameraAutoCalibrate(false);
        return false;
    }

    private void showUltrasoundDistanceEditDialog() {
        closeEditDialog();
        EditDialog newInstance = EditDialog.newInstance();
        this.mEditDialog = newInstance;
        newInstance.setSize(AdaptScreenUtils.pt2Px(1064.0f), AdaptScreenUtils.pt2Px(480.0f));
        this.mEditDialog.setTitle(StringUtils.getString(R.string.txt_ultrasound_bean_config));
        String string = StringUtils.getString(R.string.unit_m);
        final String string2 = StringUtils.getString(R.string.txt_please_enter_value_range_hint, 0.01f + "", 1.0f + "");
        this.mEditDialog.setSubTitle(StringUtils.getString(R.string.txt_please_enter_value_tips, string));
        this.mEditDialog.setHintTxt(string2);
        this.mEditDialog.setLimitLength(10);
        this.mEditDialog.setLimitDecimal(2);
        this.mEditDialog.setInputType(8194);
        this.mEditDialog.setOnDialogButtonClickListener(new EditDialog.OnDialogButtonClickListener(0.01f, 1.0f) {
            public void onCanCel() {
            }

            public boolean onConfirm(String str, float f) {
                try {
                    float parseFloat = Float.parseFloat(str);
                    if (parseFloat < 0.01f || parseFloat > 1.0f) {
                        MyToastUtils.showShort(ConfigFragment.this.getContext(), string2);
                        return true;
                    }
                    SelfChassis.getInstance().setUltrasonicDistance((int) (Float.parseFloat(str) * 1000.0f));
                    ConfigFragment.this.configBinding.includeOtherConfig.tvUltrasonicBeep.setText(str);
                    return false;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        });
        this.mEditDialog.showAllowingStateLoss(getChildFragmentManager());
    }

    private void showPointPositionEditDialog() {
        String string = StringUtils.getString(R.string.txt_config_location);
        if (!this.isSupportNaviSetting) {
            showNotSupportDialog(string);
            return;
        }
        closeEditDialog();
        EditDialog newInstance = EditDialog.newInstance();
        this.mEditDialog = newInstance;
        newInstance.setSize(AdaptScreenUtils.pt2Px(1064.0f), AdaptScreenUtils.pt2Px(480.0f));
        this.mEditDialog.setTitle(string);
        String string2 = StringUtils.getString(R.string.unit_m);
        final String string3 = StringUtils.getString(R.string.txt_please_enter_value_range_hint, 0.05f + "", 0.5f + "");
        this.mEditDialog.setSubTitle(StringUtils.getString(R.string.txt_please_enter_value_tips, string2));
        this.mEditDialog.setHintTxt(string3);
        this.mEditDialog.setLimitLength(10);
        this.mEditDialog.setLimitDecimal(2);
        this.mEditDialog.setSize(AdaptScreenUtils.pt2Px(1064.0f), AdaptScreenUtils.pt2Px(480.0f));
        this.mEditDialog.setInputType(8194);
        this.mEditDialog.setOnDialogButtonClickListener(new EditDialog.OnDialogButtonClickListener(0.05f, 0.5f) {
            public void onCanCel() {
            }

            public boolean onConfirm(String str, float f) {
                try {
                    float parseFloat = Float.parseFloat(str);
                    if (parseFloat < 0.05f || parseFloat > 0.5f) {
                        MyToastUtils.showShort(ConfigFragment.this.getContext(), string3);
                        return true;
                    }
                    SelfChassis.getInstance().configPointToleranceDistance(parseFloat);
                    ConfigFragment.this.configBinding.includeNavParamConfig.tvPointPosition.setText(str);
                    return false;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        });
        this.mEditDialog.showAllowingStateLoss(getChildFragmentManager());
    }

    private void showPointAngleEditDialog() {
        String string = StringUtils.getString(R.string.txt_angle);
        if (!this.isSupportNaviSetting) {
            showNotSupportDialog(string);
            return;
        }
        closeEditDialog();
        EditDialog newInstance = EditDialog.newInstance();
        this.mEditDialog = newInstance;
        newInstance.setSize(AdaptScreenUtils.pt2Px(1064.0f), AdaptScreenUtils.pt2Px(480.0f));
        this.mEditDialog.setTitle(string);
        String string2 = StringUtils.getString(R.string.unit_angle);
        final String string3 = StringUtils.getString(R.string.txt_please_enter_value_range_hint, 5 + "", 180 + "");
        this.mEditDialog.setSubTitle(StringUtils.getString(R.string.txt_please_enter_value_tips, string2));
        this.mEditDialog.setHintTxt(string3);
        this.mEditDialog.setLimitLength(10);
        this.mEditDialog.setInputType(2);
        this.mEditDialog.setOnDialogButtonClickListener(new EditDialog.OnDialogButtonClickListener(5, 180) {
            public void onCanCel() {
            }

            public boolean onConfirm(String str, float f) {
                try {
                    int parseInt = Integer.parseInt(str);
                    if (parseInt < 5 || parseInt > 180) {
                        MyToastUtils.showShort(ConfigFragment.this.getContext(), string3);
                        return true;
                    }
                    SelfChassis.getInstance().configPointToleranceAngle((float) parseInt);
                    ConfigFragment.this.configBinding.includeNavParamConfig.tvPointRad.setText(str);
                    return false;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        });
        this.mEditDialog.showAllowingStateLoss(getChildFragmentManager());
    }

    private void showSafeStopDistanceEditDialog() {
        String string = StringUtils.getString(R.string.txt_safe_stop_distance);
        if (!this.isSupportNaviSetting) {
            showNotSupportDialog(string);
            return;
        }
        closeEditDialog();
        EditDialog newInstance = EditDialog.newInstance();
        this.mEditDialog = newInstance;
        newInstance.setSize(AdaptScreenUtils.pt2Px(1064.0f), AdaptScreenUtils.pt2Px(480.0f));
        this.mEditDialog.setTitle(string);
        String string2 = StringUtils.getString(R.string.unit_m);
        final String string3 = StringUtils.getString(R.string.txt_please_enter_value_range_hint, 0.35f + "", 3.0f + "");
        this.mEditDialog.setSubTitle(StringUtils.getString(R.string.txt_please_enter_value_tips, string2));
        this.mEditDialog.setHintTxt(string3);
        this.mEditDialog.setLimitLength(10);
        this.mEditDialog.setLimitDecimal(2);
        this.mEditDialog.setInputType(8194);
        this.mEditDialog.setOnDialogButtonClickListener(new EditDialog.OnDialogButtonClickListener(0.35f, 3.0f) {
            public void onCanCel() {
            }

            public boolean onConfirm(String str, float f) {
                try {
                    float parseFloat = Float.parseFloat(str);
                    if (parseFloat < 0.35f || parseFloat > 3.0f) {
                        MyToastUtils.showShort(ConfigFragment.this.getContext(), string3);
                        return true;
                    }
                    SelfChassis.getInstance().configSafeStopDistance(parseFloat);
                    ConfigFragment.this.configBinding.includeNavParamConfig.tvSafeStopDistance.setText(str);
                    return false;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        });
        this.mEditDialog.showAllowingStateLoss(getChildFragmentManager());
    }

    private void closeEditDialog() {
        EditDialog editDialog = this.mEditDialog;
        if (editDialog != null && editDialog.isAdded()) {
            this.mEditDialog.dismissAllowingStateLoss();
            this.mEditDialog = null;
        }
    }

    private void showNavFailureTimeEditDialog() {
        String string = StringUtils.getString(R.string.txt_nav_failure_time);
        if (!this.isSupportNaviSetting) {
            showNotSupportDialog(string);
            return;
        }
        closeEditDialog();
        String string2 = StringUtils.getString(R.string.unit_s);
        final String string3 = StringUtils.getString(R.string.txt_please_enter_value_range_hint, 0 + "", 300 + "");
        EditDialog newInstance = EditDialog.newInstance();
        this.mEditDialog = newInstance;
        newInstance.setSize(AdaptScreenUtils.pt2Px(1064.0f), AdaptScreenUtils.pt2Px(480.0f));
        this.mEditDialog.setTitle(string).setSubTitle(StringUtils.getString(R.string.txt_please_enter_value_tips, string2)).setHintTxt(string3).setLimitLength(10).setInputType(2).setOnDialogButtonClickListener(new EditDialog.OnDialogButtonClickListener(0, 300) {
            public void onCanCel() {
            }

            public boolean onConfirm(String str, float f) {
                try {
                    int parseInt = Integer.parseInt(str);
                    if (parseInt < 0 || parseInt > 300) {
                        MyToastUtils.showShort(ConfigFragment.this.getContext(), string3);
                        return true;
                    }
                    SelfChassis.getInstance().configNavFailureTime((float) parseInt);
                    ConfigFragment.this.configBinding.includeNavParamConfig.tvNavFailureTime.setText(str);
                    return false;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        });
        this.mEditDialog.showAllowingStateLoss(getChildFragmentManager());
    }

    private void cameraAutoCalibrate(boolean z) {
        showLoadingDialog(getString(R.string.txt_auto_calibrating));
        SelfChassis.getInstance().serviceSelfDiagnosis();
        SelfChassis.getInstance().setSelfDiagnosisListener(new SelfChassisListenerUtils.OnBaseSelfChassisListener(z) {
            public final /* synthetic */ boolean f$1;

            {
                this.f$1 = r2;
            }

            public final void onSelfChassisMsg(SelfChassisEventMsg selfChassisEventMsg) {
                ConfigFragment.this.lambda$cameraAutoCalibrate$3$ConfigFragment(this.f$1, selfChassisEventMsg);
            }
        });
    }

    public /* synthetic */ void lambda$cameraAutoCalibrate$3$ConfigFragment(boolean z, SelfChassisEventMsg selfChassisEventMsg) {
        if (getActivity() != null) {
            ThreadUtils.runOnUiThread(new Runnable(selfChassisEventMsg, z) {
                public final /* synthetic */ SelfChassisEventMsg f$1;
                public final /* synthetic */ boolean f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    ConfigFragment.this.lambda$cameraAutoCalibrate$2$ConfigFragment(this.f$1, this.f$2);
                }
            });
        }
    }

    public /* synthetic */ void lambda$cameraAutoCalibrate$2$ConfigFragment(SelfChassisEventMsg selfChassisEventMsg, boolean z) {
        if (selfChassisEventMsg != null) {
            try {
                SelfDiagnosisResponseBean selfDiagnosisResponseBean = (SelfDiagnosisResponseBean) selfChassisEventMsg.getData();
                String str = this.TAG;
                MyLogUtils.Logd(str, "onSelfChassisMsg diagnosisInfo:" + selfDiagnosisResponseBean);
                List<SelfDiagnosisResponseBean.ValuesBean.StatusBean> status = selfDiagnosisResponseBean.getValues().getStatus();
                if (status != null && status.size() != 0) {
                    Iterator<SelfDiagnosisResponseBean.ValuesBean.StatusBean> it = LanguageUtils.selfDiagnosisChangeByLocalLanguage(status).iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        SelfDiagnosisResponseBean.ValuesBean.StatusBean next = it.next();
                        String hardware_id = next.getHardware_id();
                        String message = next.getMessage();
                        int level = next.getLevel();
                        if (!z) {
                            if (TextUtils.equals(getString(R.string.sc_txt_downward_depth_camera), hardware_id)) {
                                if (level == 0) {
                                    SelfChassis.getInstance().serviceDownCameraAutoCalibrate();
                                } else {
                                    closeLoadingDialog();
                                    ConfirmDialog.buildDefault(getString(R.string.txt_automatic_calibration), message).showAllowingStateLoss(getChildFragmentManager());
                                }
                            }
                        } else if (TextUtils.equals(getString(R.string.sc_txt_upward_depth_camera), hardware_id)) {
                            if (level == 0) {
                                SelfChassis.getInstance().serviceUpCameraAutoCalibrate();
                            } else {
                                closeLoadingDialog();
                                ConfirmDialog.buildDefault(getString(R.string.txt_automatic_calibration), message).showAllowingStateLoss(getChildFragmentManager());
                            }
                        }
                    }
                }
            } catch (Exception e) {
                closeLoadingDialog();
                e.printStackTrace();
            }
        }
        SelfChassis.getInstance().setSelfDiagnosisListener((SelfChassisListenerUtils.OnBaseSelfChassisListener) null);
    }

    private void showDoorControlSizeDialog() {
        EditDialog newInstance = EditDialog.newInstance();
        newInstance.setSize(AdaptScreenUtils.pt2Px(1064.0f), AdaptScreenUtils.pt2Px(480.0f));
        newInstance.setTitle(getResources().getString(R.string.door_control_size));
        newInstance.setSubTitle(getResources().getString(R.string.door_control_size_tips));
        newInstance.setHintTxt(getResources().getString(R.string.gate_control_size_hint));
        newInstance.setLimitLength(5);
        newInstance.setInputType(8194);
        float f = MySpUtils.getInstance().getFloat(SpConstant.DOOR_LENGTH, 1.2f);
        newInstance.setContent(f + "");
        newInstance.setOnDialogButtonClickListener(new EditDialog.OnDialogButtonClickListener() {
            public void onCanCel() {
            }

            public boolean onConfirm(String str, float f) {
                if (TextUtils.isEmpty(str)) {
                    return false;
                }
                try {
                    float parseFloat = Float.parseFloat(str);
                    if (((double) parseFloat) < 0.5d || parseFloat > 10.0f) {
                        MyToastUtils.showShort(ConfigFragment.this.getContext(), ConfigFragment.this.getResources().getString(R.string.gate_control_size_hint));
                        return true;
                    }
                    MySpUtils.getInstance().put(SpConstant.DOOR_LENGTH, parseFloat);
                    SelfChassis.getInstance().setDoorLength(parseFloat);
                    return false;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        });
        newInstance.showAllowingStateLoss(getChildFragmentManager());
    }

    private void showGateControlSizeDialog() {
        closeEditDialog();
        EditDialog newInstance = EditDialog.newInstance();
        this.mEditDialog = newInstance;
        newInstance.setSize(AdaptScreenUtils.pt2Px(1064.0f), AdaptScreenUtils.pt2Px(480.0f));
        this.mEditDialog.setTitle(getResources().getString(R.string.gate_control_size));
        this.mEditDialog.setHintTxt(getResources().getString(R.string.gate_control_size_hint));
        this.mEditDialog.setSubTitle(getResources().getString(R.string.gate_control_size_tips));
        this.mEditDialog.setLimitLength(5);
        this.mEditDialog.setInputType(8194);
        float f = MySpUtils.getInstance().getFloat(SpConstant.GATE_LENGTH, 1.2f);
        EditDialog editDialog = this.mEditDialog;
        editDialog.setContent(f + "");
        this.mEditDialog.setOnDialogButtonClickListener(new EditDialog.OnDialogButtonClickListener() {
            public void onCanCel() {
            }

            public boolean onConfirm(String str, float f) {
                if (TextUtils.isEmpty(str)) {
                    return false;
                }
                try {
                    float parseFloat = Float.parseFloat(str);
                    if (((double) parseFloat) < 0.5d || parseFloat > 10.0f) {
                        MyToastUtils.showShort(ConfigFragment.this.getContext(), ConfigFragment.this.getResources().getString(R.string.gate_control_size_hint));
                        return true;
                    }
                    MySpUtils.getInstance().put(SpConstant.GATE_LENGTH, parseFloat);
                    SelfChassis.getInstance().setGateLength(parseFloat);
                    return false;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        });
        this.mEditDialog.showAllowingStateLoss(getChildFragmentManager());
    }

    /* access modifiers changed from: private */
    public void showCameraAutoCalibrateResultDialog(int i) {
        String str;
        if (13000 == i) {
            str = getString(R.string.txt_calibration_succeeded);
        } else if (13001 == i) {
            str = getString(R.string.txt_correction_program_starts_abnormally);
        } else if (13002 == i) {
            str = getString(R.string.txt_correction_program_output_abnormally);
        } else {
            str = 13003 == i ? getString(R.string.txt_command_is_still_running) : "";
        }
        ConfirmDialog.buildDefault(getString(R.string.txt_automatic_calibration), str).showAllowingStateLoss(getChildFragmentManager());
    }

    class MyOnBaseSelfChassisListener implements SelfChassisListenerUtils.OnBaseSelfChassisListener {
        MyOnBaseSelfChassisListener() {
        }

        public void onSelfChassisMsg(SelfChassisEventMsg selfChassisEventMsg) {
            String str = ConfigFragment.this.TAG;
            MyLogUtils.Logd(str, "onSelfChassisMsg-->" + selfChassisEventMsg.toString());
            if (ConfigFragment.this.getActivity() != null) {
                ConfigFragment.this.getActivity().runOnUiThread(new Runnable(selfChassisEventMsg) {
                    public final /* synthetic */ SelfChassisEventMsg f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        ConfigFragment.MyOnBaseSelfChassisListener.this.lambda$onSelfChassisMsg$0$ConfigFragment$MyOnBaseSelfChassisListener(this.f$1);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onSelfChassisMsg$0$ConfigFragment$MyOnBaseSelfChassisListener(SelfChassisEventMsg selfChassisEventMsg) {
            try {
                String code = selfChassisEventMsg.getCode();
                char c = 65535;
                switch (code.hashCode()) {
                    case -2033918422:
                        if (code.equals(ServiceContent.VELOCITY_CONTROL)) {
                            c = 2;
                            break;
                        }
                        break;
                    case -1986614578:
                        if (code.equals(ServiceContent.MatchThreshold.MINNUM_STATUS)) {
                            c = 7;
                            break;
                        }
                        break;
                    case -1669755617:
                        if (code.equals(ServiceContent.GET_DOOR_LENGTH)) {
                            c = 3;
                            break;
                        }
                        break;
                    case -1643488926:
                        if (code.equals(ServiceContent.GET_GATE_LENGTH)) {
                            c = 4;
                            break;
                        }
                        break;
                    case -1031873799:
                        if (code.equals(ServiceContent.GET_MATCH_THRESHOLD)) {
                            c = 0;
                            break;
                        }
                        break;
                    case -406685998:
                        if (code.equals(ServiceContent.ROBOT_INFO)) {
                            c = 10;
                            break;
                        }
                        break;
                    case -270181274:
                        if (code.equals(ServiceContent.NAVI_SETTING)) {
                            c = 12;
                            break;
                        }
                        break;
                    case 768943159:
                        if (code.equals(ServiceContent.SENSORS_CONFIG)) {
                            c = 1;
                            break;
                        }
                        break;
                    case 815571021:
                        if (code.equals(ServiceContent.CHANGE_LOCATION_MODE)) {
                            c = 15;
                            break;
                        }
                        break;
                    case 1392009199:
                        if (code.equals(ServiceContent.MatchThreshold.MINNUM_COMMAND)) {
                            c = 8;
                            break;
                        }
                        break;
                    case 1403908064:
                        if (code.equals(ServiceContent.UP_CAMERA_EXTRINSIC_AUTO_CALIBRATE)) {
                            c = 5;
                            break;
                        }
                        break;
                    case 1420885727:
                        if (code.equals(ServiceContent.LABEL_CAMERA_EXTRINSIC_CONFIG)) {
                            c = CharUtils.CR;
                            break;
                        }
                        break;
                    case 1631247346:
                        if (code.equals(ServiceContent.MatchThreshold.MINNUM_FINDRESULT)) {
                            c = 9;
                            break;
                        }
                        break;
                    case 1731569475:
                        if (code.equals(ServiceContent.LASER_DETECTION_SETTING)) {
                            c = 14;
                            break;
                        }
                        break;
                    case 1918873575:
                        if (code.equals(ServiceContent.DOWN_CAMERA_EXTRINSIC_AUTO_CALIBRATE)) {
                            c = 6;
                            break;
                        }
                        break;
                    case 2131806837:
                        if (code.equals(ServiceContent.ULTRASONIC_DISTANCE)) {
                            c = 11;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        ConfigFragment.this.dealGetMatchThreshold(selfChassisEventMsg);
                        return;
                    case 1:
                        ConfigFragment.this.dealSensorsConfig(selfChassisEventMsg);
                        return;
                    case 2:
                        ConfigFragment.this.dealVelocityControl(selfChassisEventMsg);
                        return;
                    case 3:
                        ((GetDoorLengthBean) selfChassisEventMsg.getData()).getValues().getResult();
                        return;
                    case 4:
                        ((GetGateLengthBean) selfChassisEventMsg.getData()).getValues().getResult();
                        return;
                    case 5:
                        ConfigFragment.this.closeLoadingDialog();
                        UpCameraAutoCalibrateResponseBean.ValuesDTO values = ((UpCameraAutoCalibrateResponseBean) selfChassisEventMsg.getData()).getValues();
                        if (values != null) {
                            ConfigFragment.this.showCameraAutoCalibrateResultDialog(values.getResult().intValue());
                            return;
                        }
                        return;
                    case 6:
                        ConfigFragment.this.closeLoadingDialog();
                        DownCameraAutoCalibrateResponseBean downCameraAutoCalibrateResponseBean = (DownCameraAutoCalibrateResponseBean) selfChassisEventMsg.getData();
                        DownCameraAutoCalibrateResponseBean.ValuesDTO values2 = downCameraAutoCalibrateResponseBean.getValues();
                        if (downCameraAutoCalibrateResponseBean.getValues() != null) {
                            ConfigFragment.this.showCameraAutoCalibrateResultDialog(values2.getResult().intValue());
                            return;
                        }
                        return;
                    case 7:
                        ConfigFragment.this.closeLoadingDialog();
                        if (((MinNumStatusResponseBean) selfChassisEventMsg.getData()).getValues().isStatus().booleanValue()) {
                            ConfigFragment.this.configBinding.includeOtherConfig.tvRecalculate.setText(StringUtils.getString(R.string.txt_stop_calculating));
                            return;
                        } else {
                            ConfigFragment.this.configBinding.includeOtherConfig.tvRecalculate.setText(StringUtils.getString(R.string.txt_start_calculating));
                            return;
                        }
                    case 8:
                        ConfigFragment.this.dealMinNumCommand(selfChassisEventMsg);
                        return;
                    case 9:
                        ConfigFragment.this.dealMinNumFindResult(selfChassisEventMsg);
                        return;
                    case 10:
                        ConfigFragment.this.onRobotInfo((RobotInfoResponseBean) selfChassisEventMsg.getData());
                        return;
                    case 11:
                        ConfigFragment.this.dealUltrasonicDistance(selfChassisEventMsg);
                        return;
                    case 12:
                        ConfigFragment.this.dealNaviSetting(selfChassisEventMsg);
                        return;
                    case 13:
                        ConfigFragment.this.dealLabelCameraExtrinsicConfig(selfChassisEventMsg);
                        return;
                    case 14:
                        ConfigFragment.this.dealLaserDetectionSetting(selfChassisEventMsg);
                        return;
                    case 15:
                        ConfigFragment.this.dealLocatedModeConfig(selfChassisEventMsg);
                        return;
                    default:
                        return;
                }
            } catch (Exception e) {
                ConfigFragment.this.closeLoadingDialog();
                e.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: private */
    public void dealLaserDetectionSetting(SelfChassisEventMsg selfChassisEventMsg) {
        if (selfChassisEventMsg.getData() != null) {
            for (LaserDetectionSettingRespBean.ValuesBean.FeaturesRespBean next : ((LaserDetectionSettingRespBean) selfChassisEventMsg.getData()).getValues().getFeatures_resp()) {
                if (next.getName().equals(NaviSettingContent.LASER_DETECTION_RANGE)) {
                    this.configBinding.includeNavParamConfig.tvLaserRadarObstacleDetection.setText(String.format("%.2f", new Object[]{next.getValue_ext()}));
                } else if (next.getName().equals(NaviSettingContent.LASER_DETECTION_NOTIFICATION)) {
                    this.configBinding.includeNavParamConfig.switchLaserRadarObstacleDetection.setChecked(next.getEnable().booleanValue());
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void dealLabelCameraExtrinsicConfig(SelfChassisEventMsg selfChassisEventMsg) {
        if (selfChassisEventMsg.getData() != null) {
            for (LabelCameraExtrinsicConfigRespBean.ValuesBean.FeaturesRespBean next : ((LabelCameraExtrinsicConfigRespBean) selfChassisEventMsg.getData()).getValues().getFeatures_resp()) {
                if (next.getName().equals("labelcam_x")) {
                    if (next.getValue_ext().doubleValue() == -0.057d) {
                        this.configBinding.includeOtherConfig.rgLabelCameraInstallCoordinateType.check(this.configBinding.includeOtherConfig.rbD1.getId());
                    } else if (next.getValue_ext().doubleValue() == -0.181503d) {
                        this.configBinding.includeOtherConfig.rgLabelCameraInstallCoordinateType.check(this.configBinding.includeOtherConfig.rbD3.getId());
                    } else {
                        this.configBinding.includeOtherConfig.rgLabelCameraInstallCoordinateType.check(this.configBinding.includeOtherConfig.rbD3.getId());
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void dealLocatedModeConfig(SelfChassisEventMsg selfChassisEventMsg) {
        if (selfChassisEventMsg.getData() != null) {
            LocatedModeConfigRespBean locatedModeConfigRespBean = (LocatedModeConfigRespBean) selfChassisEventMsg.getData();
            if (locatedModeConfigRespBean.getValues().getResult().intValue() == 0) {
                this.configBinding.includeOtherConfig.rgLocatedType.check(this.configBinding.includeOtherConfig.rbSpeedometer.getId());
            } else if (locatedModeConfigRespBean.getValues().getResult().intValue() == 1) {
                this.configBinding.includeOtherConfig.rgLocatedType.check(this.configBinding.includeOtherConfig.rbLaserRadar.getId());
            }
        }
    }

    /* access modifiers changed from: private */
    public void dealUltrasonicDistance(SelfChassisEventMsg selfChassisEventMsg) {
        if (selfChassisEventMsg.getData() != null) {
            UltrasonicDistanceResponseBean ultrasonicDistanceResponseBean = (UltrasonicDistanceResponseBean) selfChassisEventMsg.getData();
            String info = ultrasonicDistanceResponseBean.getInfo();
            String id = ultrasonicDistanceResponseBean.getId();
            if (TextUtils.equals(IDContent.UltrasonicDistance.SERVICE_ULTRASONIC_DISTANCE_GET, id)) {
                if (TextUtils.equals(DeploymentToolConstant.CHECK_ULTRASONIC_EXIST, info)) {
                    this.isSupportUltrasonicDistance = false;
                } else {
                    this.isSupportUltrasonicDistance = true;
                }
                if (ultrasonicDistanceResponseBean.getValues().getResult() == 10) {
                    MyLogUtils.Logd(this.TAG, "重新获取超声波距离");
                    SelfChassis.getInstance().getUltrasonicDistance();
                    return;
                }
                Iterator<UltrasonicDistanceBean> it = ultrasonicDistanceResponseBean.getValues().getFeatures_resp().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    UltrasonicDistanceBean next = it.next();
                    if (TextUtils.equals(next.getName(), SensorSettingContent.ULTRASONIC_DISTANCE)) {
                        float value = ((float) next.getValue()) / 1000.0f;
                        boolean z = this.isSupportUltrasonicDistance && value != 0.0f;
                        this.configBinding.includeOtherConfig.tvUltrasonicBeep.setText(String.valueOf(value));
                        int i = z ? 0 : 8;
                        this.configBinding.includeOtherConfig.llUltrasonicBeep.setVisibility(i);
                        this.configBinding.includeOtherConfig.tvUltrasonicBeepTip.setVisibility(i);
                        this.configBinding.includeOtherConfig.switchUltrasonicBeep.setClickable(true);
                        this.configBinding.includeOtherConfig.switchUltrasonicBeep.setAlpha(1.0f);
                        this.configBinding.includeOtherConfig.switchUltrasonicBeep.setChecked(z);
                    }
                }
                if (!this.isSupportUltrasonicDistance) {
                    this.configBinding.includeOtherConfig.llUltrasonicBeep.setVisibility(0);
                    this.configBinding.includeOtherConfig.tvUltrasonicBeepTip.setVisibility(0);
                    this.configBinding.includeOtherConfig.switchUltrasonicBeep.setClickable(false);
                    this.configBinding.includeOtherConfig.switchUltrasonicBeep.setAlpha(0.5f);
                    this.configBinding.includeOtherConfig.tvUltrasonicBeep.setText(String.valueOf(0.2f));
                }
            } else if (!TextUtils.equals(IDContent.UltrasonicDistance.SERVICE_ULTRASONIC_DISTANCE_SET, id)) {
            } else {
                if (ultrasonicDistanceResponseBean.getValues().getResult() == 0) {
                    MyToastUtils.showShort(ContextUtil.getContext(), StringUtils.getString(R.string.tip_success));
                } else {
                    MyToastUtils.showShort(ContextUtil.getContext(), StringUtils.getString(R.string.txt_save_failed));
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void dealNaviSetting(SelfChassisEventMsg selfChassisEventMsg) {
        String str = this.TAG;
        MyLogUtils.Logd(str, "dealNaviSetting:" + selfChassisEventMsg.toString());
        if (selfChassisEventMsg.getData() != null) {
            NaviSettingResponseBean naviSettingResponseBean = (NaviSettingResponseBean) selfChassisEventMsg.getData();
            String info = naviSettingResponseBean.getInfo();
            String id = naviSettingResponseBean.getId();
            if (TextUtils.equals(info, DeploymentToolConstant.CHECK_NAVI_SETTING_EXIST)) {
                this.isSupportNaviSetting = false;
            } else {
                this.isSupportNaviSetting = true;
            }
            if (naviSettingResponseBean.getValues().getResult().intValue() == 10) {
                MyLogUtils.Logd(this.TAG, "重新获取导航相关参数接口");
                SelfChassis.getInstance().getNaviSetting();
            } else if (!TextUtils.equals(IDContent.NaviSetting.CONFIG_NAVI_SETTING, id) && TextUtils.equals(IDContent.NaviSetting.GET_NAVI_SETTING, id)) {
                for (NaviSettingBean next : naviSettingResponseBean.getValues().getFeaturesResp()) {
                    float valueExt = next.getValueExt();
                    if (TextUtils.equals(next.getName(), NaviSettingContent.XY_GOAL_TOLERANCE)) {
                        this.configBinding.includeNavParamConfig.tvPointPosition.setText(String.valueOf(valueExt));
                    } else if (TextUtils.equals(next.getName(), NaviSettingContent.YAW_GOAL_TOLERANCE)) {
                        this.configBinding.includeNavParamConfig.tvPointRad.setText(String.valueOf((int) BigDecimal.valueOf(((double) valueExt) * 57.29577951308232d).setScale(0, RoundingMode.HALF_UP).doubleValue()));
                    } else if (TextUtils.equals(next.getName(), NaviSettingContent.SAFE_STOP_DISTANCE)) {
                        this.configBinding.includeNavParamConfig.tvSafeStopDistance.setText(String.valueOf(valueExt));
                    } else if (TextUtils.equals(next.getName(), NaviSettingContent.NAV_FAILURE_TIME)) {
                        this.configBinding.includeNavParamConfig.tvNavFailureTime.setText(String.valueOf((int) valueExt));
                    } else if (TextUtils.equals(next.getName(), NaviSettingContent.LOCAL_PLANNER_MAX_VEL_X)) {
                        this.configBinding.tvSpeedConfig.setText(String.format("%.2f", new Object[]{Float.valueOf(valueExt)}));
                        this.configBinding.sbSpeedConfig.setProgress(fromActualSpeed(valueExt));
                    } else if (TextUtils.equals(next.getName(), NaviSettingContent.NAVIGATION_MODE_AUTO_NAVI)) {
                        RadioGroup radioGroup = this.configBinding.rgNavMode;
                        int i = (valueExt > 1.0f ? 1 : (valueExt == 1.0f ? 0 : -1));
                        FragmentSetConfigBinding fragmentSetConfigBinding = this.configBinding;
                        radioGroup.check((i == 0 ? fragmentSetConfigBinding.rbNavAuto : fragmentSetConfigBinding.rbNavVirtual).getId());
                        MySpUtils.getInstance().put(SpConstant.NAV_MODE, i == 0 ? 0 : 1);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void onRobotInfo(RobotInfoResponseBean robotInfoResponseBean) {
        String software_version = robotInfoResponseBean.getValues().getSoftware_version();
        if (TextUtils.isEmpty(software_version) || !software_version.contains(DeploymentToolConstant.VOCA_ALGORITHM_PREFIX)) {
            this.configBinding.clSpeed.setVisibility(0);
            this.configBinding.clTravel.setVisibility(0);
            return;
        }
        this.configBinding.clSpeed.setVisibility(8);
        this.configBinding.clTravel.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void dealMinNumFindResult(SelfChassisEventMsg selfChassisEventMsg) {
        closeLoadingDialog();
        this.configBinding.includeOtherConfig.tvRecalculate.setText(StringUtils.getString(R.string.txt_recalculate));
        this.configBinding.includeOtherConfig.tvCalculateThreshold.setText(String.valueOf(new BigDecimal(((MinNumFindResultResponseBean) selfChassisEventMsg.getData()).getValues().getLastnum().doubleValue()).setScale(2, RoundingMode.HALF_UP).doubleValue()));
        changeBtnSetAsThreshold(true);
    }

    /* access modifiers changed from: private */
    public void dealMinNumCommand(SelfChassisEventMsg selfChassisEventMsg) {
        MinNumCommandResponseBean minNumCommandResponseBean = (MinNumCommandResponseBean) selfChassisEventMsg.getData();
        String string = StringUtils.getString(R.string.txt_stop_calculating);
        if (!minNumCommandResponseBean.isResult().booleanValue()) {
            closeLoadingDialog();
            showNotSupportDialog(StringUtils.getString(R.string.txt_security_threshold_calculate));
        } else if (!minNumCommandResponseBean.getValues().isResult().booleanValue()) {
            SelfChassis.getInstance().getRecommendedMapMatchingThreshold();
        } else {
            closeLoadingDialog();
            this.configBinding.includeOtherConfig.tvRecalculate.setText(string);
        }
    }

    private void showNotSupportDialog(String str) {
        ConfirmDialog.buildDefault(str, StringUtils.getString(R.string.txt_tip_algorithm_version_no_support)).showAllowingStateLoss(getChildFragmentManager());
    }

    /* access modifiers changed from: private */
    public void dealVelocityControl(SelfChassisEventMsg selfChassisEventMsg) {
        VelocityControlResponseBean velocityControlResponseBean;
        int i;
        closeLoadingDialog();
        if (selfChassisEventMsg.getData() != null && selfChassisEventMsg.getData() != null && (velocityControlResponseBean = (VelocityControlResponseBean) selfChassisEventMsg.getData()) != null) {
            if (TextUtils.equals(velocityControlResponseBean.getId(), IDContent.VelocityControl.GET_SPEED)) {
                if (!velocityControlResponseBean.getResult().booleanValue() || velocityControlResponseBean.getValues().getResult().intValue() != 0) {
                    SelfChassis.getInstance().serviceGetVelocity();
                    String str = this.TAG;
                    MyLogUtils.Logd(str, "获取导航速度失败：" + velocityControlResponseBean);
                    return;
                }
                try {
                    i = Integer.parseInt(velocityControlResponseBean.getValues().getInfo());
                } catch (Exception e) {
                    e.printStackTrace();
                    i = -2;
                }
                if (i != -2) {
                    onVelocity(i);
                }
            } else if (!TextUtils.equals(velocityControlResponseBean.getId(), IDContent.VelocityControl.SET_SPEED)) {
            } else {
                if (velocityControlResponseBean.getResult().booleanValue()) {
                    MyToastUtils.showShort(ContextUtil.getContext(), StringUtils.getString(R.string.tip_success));
                } else {
                    MyToastUtils.showShort(ContextUtil.getContext(), StringUtils.getString(R.string.txt_save_failed));
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void dealSensorsConfig(SelfChassisEventMsg selfChassisEventMsg) {
        SensorResponseBean sensorResponseBean = (SensorResponseBean) selfChassisEventMsg.getData();
        if (TextUtils.equals(sensorResponseBean.getId(), String.valueOf(2))) {
            List<SensorFeaturesBean> features_resp = sensorResponseBean.getValues().getFeatures_resp();
            this.sensors = features_resp;
            if (features_resp == null || features_resp.size() <= 0) {
                this.configBinding.clSensor.setVisibility(8);
            } else {
                this.configBinding.clSensor.setVisibility(0);
            }
            setLiftState();
            this.sensorAdapter.refreshData(this.sensors);
        } else if (TextUtils.equals(sensorResponseBean.getId(), String.valueOf(1))) {
            setLiftState();
            closeLoadingDialog();
            ConfirmDialog.buildDefault(getString(R.string.tip_save_success), getString(R.string.tip_sensor_save_msg)).showAllowingStateLoss(getChildFragmentManager());
        }
    }

    /* access modifiers changed from: private */
    public void dealGetMatchThreshold(SelfChassisEventMsg selfChassisEventMsg) {
        int result = ((GetMatchThresholdBean) selfChassisEventMsg.getData()).getValues().getResult();
        this.configBinding.includeOtherConfig.sbThreshold.setProgress(result);
        this.configBinding.includeOtherConfig.tvSecurityThreshold.setText(String.valueOf(((float) result) / 100.0f));
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        this.configBinding.includeOtherConfig.tvSecurityThreshold.setText(String.valueOf(((float) i) / 100.0f));
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        SelfChassis.getInstance().laserSafetyControllerConfidenceThreshold(((float) seekBar.getProgress()) / 100.0f);
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        int i2 = 3;
        switch (radioGroup.getId()) {
            case R.id.rg_control:
                MySpUtils.getInstance().put(SpConstant.KEY_MODE, this.configBinding.rbKeyFour.isChecked() ^ true ? 1 : 0);
                return;
            case R.id.rg_label_camera_install_coordinate_type:
                SelfChassis instance = SelfChassis.getInstance();
                if (this.configBinding.includeOtherConfig.rbD1.isChecked()) {
                    i2 = 4;
                }
                instance.labelCameraExtrinsicConfig(i2);
                return;
            case R.id.rg_located_type:
                SelfChassis.getInstance().locatedModeConfig(this.configBinding.includeOtherConfig.rbLaserRadar.isChecked() ? 1 : 0);
                return;
            case R.id.rg_nav_mode:
                SelfChassis.getInstance().configAutoNavi(this.configBinding.rbNavAuto.isChecked() ? 1.0f : 0.0f);
                MySpUtils.getInstance().put(SpConstant.NAV_MODE, this.configBinding.rbNavAuto.isChecked() ^ true ? 1 : 0);
                return;
            case R.id.rg_travel:
                MyLogUtils.Logd(this.TAG, "触发rg_speed rg_travel：onCheckedChanged");
                if (this.configBinding.rbSecurity.isChecked()) {
                    i2 = 0;
                } else if (!this.configBinding.rbBalance.isChecked()) {
                    i2 = 6;
                }
                App.getInstance().setSpeedLevel(2);
                MyLogUtils.Logd("test speedType:" + 2);
                MyLogUtils.Logd("test getSpeedLevel:" + App.getInstance().getSpeedLevel());
                MyLogUtils.Logd("test: getChassisSpeed" + App.getInstance().getChassisSpeed());
                showLoadingDialog(StringUtils.getString(R.string.tip_updating), 10);
                SelfChassis.getInstance().serviceVelocityControl(i2 + 2);
                return;
            default:
                return;
        }
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (compoundButton.isPressed()) {
            switch (compoundButton.getId()) {
                case R.id.switch_laser_radar_obstacle_detection:
                    onSwitchLaserRadarObstacleDetection(z);
                    return;
                case R.id.switch_manual:
                    MySpUtils.getInstance().put(SpConstant.ROCKER_MODE, z);
                    rockerModeView();
                    return;
                case R.id.switch_smooth:
                    MySpUtils.getInstance().put(SpConstant.SMOOTH_MODE, z);
                    SelfChassis.getInstance().serviceSmoothControl(z);
                    smoothModeView();
                    SelfChassis.getInstance().serviceGetVelocity();
                    return;
                case R.id.switch_ultrasonic_beep:
                    if (!this.isSupportUltrasonicDistance) {
                        showNotSupportDialog(StringUtils.getString(R.string.txt_ultrasound_bean_config));
                        return;
                    }
                    int i = 0;
                    if (!z) {
                        SelfChassis.getInstance().setUltrasonicDistance(0);
                        this.configBinding.includeOtherConfig.tvUltrasonicBeep.setText(NetConstant.PAGE_ID_HOME);
                    }
                    this.configBinding.includeOtherConfig.llUltrasonicBeep.setVisibility(z ? 0 : 8);
                    AppCompatTextView appCompatTextView = this.configBinding.includeOtherConfig.tvUltrasonicBeepTip;
                    if (!z) {
                        i = 8;
                    }
                    appCompatTextView.setVisibility(i);
                    return;
                default:
                    return;
            }
        }
    }

    private void onSwitchLaserRadarObstacleDetection(boolean z) {
        double doubleValue = Double.valueOf(this.configBinding.includeNavParamConfig.tvLaserRadarObstacleDetection.getText().toString().trim()).doubleValue();
        if (z) {
            SelfChassis.getInstance().laserDetectionSetting(1, doubleValue, true);
        } else {
            SelfChassis.getInstance().laserDetectionSetting(1, doubleValue, false);
        }
    }

    private void onVelocity(int i) {
        switch (i) {
            case 0:
                this.mSpeedType = 0;
                this.mTravelMode = 0;
                break;
            case 1:
                this.mSpeedType = 1;
                this.mTravelMode = 0;
                break;
            case 2:
                this.mSpeedType = 2;
                this.mTravelMode = 0;
                break;
            case 3:
                this.mSpeedType = 0;
                this.mTravelMode = 3;
                break;
            case 4:
                this.mSpeedType = 1;
                this.mTravelMode = 3;
                break;
            case 5:
                this.mSpeedType = 2;
                this.mTravelMode = 3;
                break;
            case 6:
                this.mSpeedType = 0;
                this.mTravelMode = 6;
                break;
            case 7:
                this.mSpeedType = 1;
                this.mTravelMode = 6;
                break;
            case 8:
                this.mSpeedType = 2;
                this.mTravelMode = 6;
                break;
        }
        if (this.mSpeedType == -1) {
            this.mSpeedType = 1;
        }
        App.getInstance().setSpeedLevel(this.mSpeedType);
        MyLogUtils.Logd("test speedType0:" + this.mSpeedType);
        MyLogUtils.Logd("test mTravelMode:" + this.mTravelMode);
        int checkId = getCheckId(this.mTravelMode);
        if (checkId != -1) {
            this.configBinding.rgTravel.setCheckedSilent(checkId);
        }
    }

    public void onItemSwitch(int i, boolean z) {
        this.sensors.get(i).setEnable(z);
    }

    public void rockerModeView() {
        if (MySpUtils.getInstance().getBoolean(SpConstant.ROCKER_MODE, true)) {
            changeRbStyle(this.configBinding.rbKeyFour, true);
            changeRbStyle(this.configBinding.rbKeyEight, true);
            return;
        }
        changeRbStyle(this.configBinding.rbKeyFour, false);
        changeRbStyle(this.configBinding.rbKeyEight, false);
    }

    public void smoothModeView() {
        if (MySpUtils.getInstance().getBoolean(SpConstant.SMOOTH_MODE)) {
            changeRbStyle(this.configBinding.rbSecurity, false);
            changeRbStyle(this.configBinding.rbBalance, false);
            changeRbStyle(this.configBinding.rbEfficiency, false);
            return;
        }
        changeRbStyle(this.configBinding.rbSecurity, true);
        changeRbStyle(this.configBinding.rbBalance, true);
        changeRbStyle(this.configBinding.rbEfficiency, true);
    }

    private void changeRbStyle(AppCompatRadioButton appCompatRadioButton, boolean z) {
        appCompatRadioButton.setEnabled(z);
        if (z) {
            appCompatRadioButton.setAlpha(1.0f);
        } else {
            appCompatRadioButton.setAlpha(0.5f);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        closeEditDialog();
        closeLoadingDialog();
        SelfChassis.getInstance().setMatchThresholdListener((SelfChassisListenerUtils.OnBaseSelfChassisListener) null);
        SelfChassis.getInstance().setGetSensorsListener((SelfChassisListenerUtils.OnBaseSelfChassisListener) null);
        SelfChassis.getInstance().setVelocityControlListener((SelfChassisListenerUtils.OnBaseSelfChassisListener) null);
        SelfChassisListenerUtils.getInstance().setDoorLengthListener((SelfChassisListenerUtils.OnBaseSelfChassisListener) null);
        SelfChassisListenerUtils.getInstance().setGateLengthListener((SelfChassisListenerUtils.OnBaseSelfChassisListener) null);
        SelfChassisListenerUtils.getInstance().removeUpCameraAutoCalibrateListener(this.mUpCameraAutoCalibrateListener);
        SelfChassisListenerUtils.getInstance().removeDownCameraAutoCalibrateListener(this.mDownCameraAutoCalibrateListener);
        SelfChassisListenerUtils.getInstance().setMinNumCommandListener((SelfChassisListenerUtils.OnBaseSelfChassisListener) null);
        SelfChassisListenerUtils.getInstance().setMinNumStatusListener((SelfChassisListenerUtils.OnBaseSelfChassisListener) null);
        SelfChassisListenerUtils.getInstance().setMinNumFindResultListener((SelfChassisListenerUtils.OnBaseSelfChassisListener) null);
        SelfChassis.getInstance().setRobotInfoListener((SelfChassisListenerUtils.OnBaseSelfChassisListener) null);
        SelfChassisListenerUtils.getInstance().setUltrasonicDistanceListener((SelfChassisListenerUtils.OnBaseSelfChassisListener) null);
        SelfChassis.getInstance().setNaviSettingListener((SelfChassisListenerUtils.OnBaseSelfChassisListener) null);
    }

    private void setLiftState() {
        List<SensorFeaturesBean> list = this.sensors;
        if (list != null && list.size() > 0) {
            for (SensorFeaturesBean next : this.sensors) {
                if (TextUtils.equals(next.getName(), "lift")) {
                    App.getInstance().setOpenLife(next.isEnable());
                    changeBtnElevatorSet(next.isEnable());
                } else {
                    float f = 1.0f;
                    if (TextUtils.equals(next.getName(), "gate")) {
                        AppCompatTextView appCompatTextView = this.configBinding.includeOtherConfig.tvDoorControlSize;
                        if (!next.isEnable()) {
                            f = 0.5f;
                        }
                        appCompatTextView.setAlpha(f);
                        this.configBinding.includeOtherConfig.tvDoorControlSize.setEnabled(next.isEnable());
                    } else if (TextUtils.equals(next.getName(), "turnstile")) {
                        AppCompatTextView appCompatTextView2 = this.configBinding.includeOtherConfig.tvGateControlSize;
                        if (!next.isEnable()) {
                            f = 0.5f;
                        }
                        appCompatTextView2.setAlpha(f);
                        this.configBinding.includeOtherConfig.tvGateControlSize.setEnabled(next.isEnable());
                    } else if (TextUtils.equals(next.getName(), "astra_camera")) {
                        AppCompatButton appCompatButton = this.configBinding.includeOtherConfig.btnDepthCameraUpProbeCalibration;
                        if (!next.isEnable()) {
                            f = 0.5f;
                        }
                        appCompatButton.setAlpha(f);
                        this.configBinding.includeOtherConfig.btnDepthCameraUpProbeCalibration.setEnabled(next.isEnable());
                    } else if (TextUtils.equals(next.getName(), "cliff_detection")) {
                        AppCompatButton appCompatButton2 = this.configBinding.includeOtherConfig.btnDepthCameraDownProbeCalibration;
                        if (!next.isEnable()) {
                            f = 0.5f;
                        }
                        appCompatButton2.setAlpha(f);
                        this.configBinding.includeOtherConfig.btnDepthCameraDownProbeCalibration.setEnabled(next.isEnable());
                    }
                }
            }
        }
    }

    private void changeBtnElevatorSet(boolean z) {
        this.configBinding.includeOtherConfig.btnElevatorSet.setAlpha(!z ? 0.5f : 1.0f);
        this.configBinding.includeOtherConfig.btnElevatorSet.setEnabled(z);
    }

    private void changeBtnSetAsThreshold(boolean z) {
        this.configBinding.includeOtherConfig.btnSetAsThreshold.setAlpha(!z ? 0.5f : 1.0f);
        this.configBinding.includeOtherConfig.btnSetAsThreshold.setEnabled(z);
    }

    public void closeLoadingDialog() {
        LiveDatabus.getInstance().with(DTLiveDataConstant.SET_LOADING_DIALOG_EVENT).postValue(new LoadingLiveDataEvent(true));
    }

    public void showLoadingDialog(String str) {
        LiveDatabus.getInstance().with(DTLiveDataConstant.SET_LOADING_DIALOG_EVENT).postValue(new LoadingLiveDataEvent(str, 180));
    }

    public void showLoadingDialog(String str, int i) {
        LiveDatabus.getInstance().with(DTLiveDataConstant.SET_LOADING_DIALOG_EVENT).postValue(new LoadingLiveDataEvent(str, i));
    }

    private void showLaserRadarObstacleDetectionDialog() {
        String string = StringUtils.getString(R.string.text_laser_radar_obstacle_detection);
        closeEditDialog();
        EditDialog newInstance = EditDialog.newInstance();
        this.mEditDialog = newInstance;
        newInstance.setSize(AdaptScreenUtils.pt2Px(1064.0f), AdaptScreenUtils.pt2Px(480.0f));
        this.mEditDialog.setTitle(string);
        String string2 = StringUtils.getString(R.string.unit_m);
        final String string3 = StringUtils.getString(R.string.txt_please_enter_value_range_hint, 0.05f + "", 10.0f + "");
        this.mEditDialog.setSubTitle(StringUtils.getString(R.string.txt_please_enter_value_tips, string2));
        this.mEditDialog.setHintTxt(string3);
        this.mEditDialog.setLimitLength(10);
        this.mEditDialog.setLimitDecimal(2);
        this.mEditDialog.setInputType(8194);
        this.mEditDialog.setOnDialogButtonClickListener(new EditDialog.OnDialogButtonClickListener(0.05f, 10.0f) {
            public void onCanCel() {
            }

            public boolean onConfirm(String str, float f) {
                try {
                    float parseFloat = Float.parseFloat(str);
                    if (parseFloat < 0.05f || parseFloat > 10.0f) {
                        MyToastUtils.showShort(ConfigFragment.this.getContext(), string3);
                        return true;
                    }
                    SelfChassis.getInstance().configSafeStopDistance(parseFloat);
                    ConfigFragment.this.configBinding.includeNavParamConfig.tvLaserRadarObstacleDetection.setText(str);
                    SelfChassis.getInstance().laserDetectionSetting(1, (double) parseFloat, ConfigFragment.this.configBinding.includeNavParamConfig.switchLaserRadarObstacleDetection.isChecked());
                    return false;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        });
        this.mEditDialog.showAllowingStateLoss(getChildFragmentManager());
    }
}
