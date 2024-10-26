package com.ciot.base.util;

import android.text.TextUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.ciot.base.constant.AppConstant;
import com.ciot.base.constant.NetConstant;
import com.ciot.base.constant.SpConstant;
import com.ciot.base.storage.MySpUtils;
import com.ciot.networklib.bean.phone.VisitByPhone;

public class AppSpUtil {
    public static final String DEFAULT_SP_NAME = "CIOT_SP_DATA";
    private static AppSpUtil mSetSpUtil;
    private String mAdminPwd;
    private float mAlarmTemp;
    private boolean mAlwaysThermometry;
    private String mChassisBootHint;
    private boolean mFaceRecognizeGreet = this.mSPUtils.getBoolean(SpConstant.FACE_RECOGNITION_GREET, false);
    private float mFaceRectBottom;
    private float mFaceRectLeft;
    private float mFaceRectRight;
    private float mFaceRectTop;
    private float mFaceSensitivity;
    private float mFaceThreshold;
    private String mFirstNumber;
    private boolean mHasFace = false;
    private int mIdentifySupport;
    private int mIdleTime;
    private boolean mIdleTimeBackChargingPile = this.mSPUtils.getBoolean("WaterChassisIdleTimeBackChargingPileFlag", false);
    private String mIgnoreUpdateVersionId;
    private boolean mIndependent;
    private boolean mIsOpenMuteVideoAd = this.mSPUtils.getBoolean(SpConstant.SP_SET_MUTE_VIDEO_AD, true);
    private volatile boolean mIsOpenVoiceCallButton = MySpUtils.getInstance().getBoolean(SpConstant.SP_SET_VOICE_CALL_BUTTON, false);
    private volatile boolean mIsWearMarker = this.mSPUtils.getBoolean(SpConstant.SP_WEAR_MARK_HINT, false);
    private int mLanguage = this.mSPUtils.getInt(SpConstant.SP_TRADITIONAL_LANGUAGE, 0);
    private int mLanguageCode;
    private int mLanguageOne;
    private int mLanguageTwo;
    private volatile int mLowBatteryValue = this.mSPUtils.getInt(SpConstant.SP_LOW_BATTERY_VALUE, 20);
    private float mManFaceValue;
    private String mNewUpdateVersionId;
    private boolean mOpenOverTempAlarm = this.mSPUtils.getBoolean(SpConstant.SP_WATER_CHASSIS_OPEN_OVER_TEMPERATURE_FLAG, true);
    private boolean mOpenWarnLight = this.mSPUtils.getBoolean(SpConstant.SP_WARN_LIGHT_STATE);
    private String mOperatorPwd;
    private boolean mOptimizeOutElevator = this.mSPUtils.getBoolean(SpConstant.SP_OPTIMIZE_OUT_ELEVATOR, false);
    private int mPicTimeValue;
    private boolean mRemovePwdModel = this.mSPUtils.getBoolean(SpConstant.SP_WATER_REMOVE_PWD_MODEL_FLAG, false);
    private boolean mRobotIsActivate;
    private boolean mRobotIsLowPower = false;
    private String mRobotModel;
    private String mRobotNumber;
    private int mRobotRealType;
    private int mRobotType;
    private String mRootPhone;
    private final MySpUtils mSPUtils;
    private int mSpeed = 3;
    private int mSprayGear = this.mSPUtils.getInt(SpConstant.SP_SPRAY_GEAR, 2);
    private String mSuperAdminPwd;
    private String mSystemPassword;
    private String mTcpIp;
    private float mTempThreshold;
    private String mUserAccount;
    private int mUserArea;
    private int mViwCameraType;
    private int mVoicePort;
    private String mWuHanAllowUrl;
    private boolean misOpenLowPowerBackChargingPile = this.mSPUtils.getBoolean(SpConstant.SP_LOW_BATTERY_CHARGE, true);

    public int getLanguage() {
        return this.mLanguage;
    }

    public void setLanguage(int i) {
        this.mSPUtils.putInt(SpConstant.SP_TRADITIONAL_LANGUAGE, i);
        this.mLanguage = i;
    }

    public float getMaxSpeedTaskRunning() {
        return this.mSPUtils.getFloat(SpConstant.SP_MAX_SPEED_TASK_RUNNING, 0.5f);
    }

    public void setMaxSpeedTaskRunning(float f) {
        this.mSPUtils.putFloat(SpConstant.SP_MAX_SPEED_TASK_RUNNING, f);
    }

    public float getMaxSpeedTaskNotRunning() {
        float f = this.mSPUtils.getFloat(SpConstant.SP_MAX_SPEED_TASK_NOT_RUNNING, 0.5f);
        MyLogUtils.Logd("NAVIGATION_TAG", "getMaxSpeedTaskNotRunning:" + f);
        return f;
    }

    public void setMaxSpeedTaskNotRunning(float f) {
        MyLogUtils.Logd("NAVIGATION_TAG", "setMaxSpeedTaskNotRunning:" + f);
        this.mSPUtils.putFloat(SpConstant.SP_MAX_SPEED_TASK_NOT_RUNNING, f);
    }

    public void setRootPhone(String str) {
        this.mRootPhone = str;
        this.mSPUtils.putString(SpConstant.SP_ROOT_PHONE, str);
    }

    public void setChassisBootHint(String str) {
        if (!str.equals(this.mChassisBootHint)) {
            this.mSPUtils.putString("ChassisBootHintFlag", str);
            this.mChassisBootHint = str;
        }
    }

    public String getChassisBootHint() {
        if (this.mChassisBootHint == null) {
            this.mChassisBootHint = this.mSPUtils.getString("ChassisBootHintFlag", AppConstant.ROBOT_BOOT_UP_TIPS);
        }
        return this.mChassisBootHint;
    }

    public String getRootPhone() {
        return this.mRootPhone;
    }

    public boolean isRemovePwdModel() {
        return this.mRemovePwdModel;
    }

    public void setRemovePwdModel(boolean z) {
        this.mSPUtils.putBoolean(SpConstant.SP_WATER_REMOVE_PWD_MODEL_FLAG, z);
        this.mRemovePwdModel = z;
    }

    public String getOperatorPwd() {
        if (this.mOperatorPwd == null) {
            this.mOperatorPwd = this.mSPUtils.getString(SpConstant.SP_WATER_OPERATOR_PASSWORD_FLAG, AppConstant.WATER_DEFAULT_OPERATOR_PASSWORD);
        }
        return this.mOperatorPwd;
    }

    public void setOperatorPwd(String str) {
        if (!str.equals(this.mOperatorPwd)) {
            this.mOperatorPwd = str;
            this.mSPUtils.putString(SpConstant.SP_WATER_OPERATOR_PASSWORD_FLAG, str);
        }
    }

    public String getAdminPwd() {
        if (this.mAdminPwd == null) {
            this.mAdminPwd = this.mSPUtils.getString(SpConstant.SP_WATER_ADMIN_PASSWORD_FLAG, "86926868");
        }
        return this.mAdminPwd;
    }

    public void setAdminPwd(String str) {
        this.mAdminPwd = str;
    }

    public String getSuperAdminPwd() {
        if (this.mSuperAdminPwd == null) {
            this.mSuperAdminPwd = this.mSPUtils.getString(SpConstant.SP_WATER_SUPER_ADMIN_PASSWORD_FLAG, "86926868");
        }
        return this.mSuperAdminPwd;
    }

    public void setSuperAdminPwd(String str) {
        this.mSuperAdminPwd = str;
    }

    public int getIdleTime() {
        if (this.mIdleTime < 5) {
            this.mIdleTime = this.mSPUtils.getInt(SpConstant.SP_WATER_CHASSIS_IDLE_TIME_VALUE_FLAG, 5);
        }
        return this.mIdleTime;
    }

    public void setIdleTime(int i) {
        if (i != this.mIdleTime) {
            this.mIdleTime = i;
            this.mSPUtils.putInt(SpConstant.SP_WATER_CHASSIS_IDLE_TIME_VALUE_FLAG, i);
        }
    }

    public boolean isRobotIsLowPower() {
        if (this.misOpenLowPowerBackChargingPile) {
            return this.mRobotIsLowPower;
        }
        return false;
    }

    public void setRobotIsLowPower(boolean z) {
        this.mRobotIsLowPower = z;
    }

    private AppSpUtil() {
        MySpUtils instance = MySpUtils.getInstance();
        this.mSPUtils = instance;
        this.mRootPhone = instance.getString(SpConstant.SP_ROOT_PHONE, "");
    }

    public static AppSpUtil getInstance() {
        if (mSetSpUtil == null) {
            synchronized (AppSpUtil.class) {
                if (mSetSpUtil == null) {
                    mSetSpUtil = new AppSpUtil();
                }
            }
        }
        return mSetSpUtil;
    }

    public void setPhoneFirstNumber(String str) {
        if (!str.equals(this.mFirstNumber)) {
            this.mFirstNumber = str;
            this.mSPUtils.putString(SpConstant.LIN_PHONE_FIRST_NUMBER_KEY, str);
        }
    }

    public void setRobotNumber(String str) {
        if (!TextUtils.isEmpty(str) && !str.equals(this.mRobotNumber)) {
            this.mRobotNumber = str;
            MyLogUtils.Logd("NETWORK_TAG", "setRobotNumber:" + this.mRobotNumber);
            this.mSPUtils.putString(SpConstant.ROBOT_NUMBER, this.mRobotNumber);
        }
    }

    public String getRobotNumber() {
        if (TextUtils.isEmpty(this.mRobotNumber)) {
            this.mRobotNumber = this.mSPUtils.getString(SpConstant.ROBOT_NUMBER, "");
        }
        return this.mRobotNumber;
    }

    public void setRobotRealType(int i) {
        this.mRobotRealType = i;
        this.mSPUtils.putInt(SpConstant.ROBOT_REAL_TYPE, i);
    }

    public int getRobotRealType() {
        return this.mSPUtils.getInt(SpConstant.ROBOT_REAL_TYPE, 6);
    }

    public void setRobotModel(String str) {
        if (!TextUtils.isEmpty(str) && !str.equals(this.mRobotModel)) {
            this.mRobotModel = str;
            this.mSPUtils.putString(SpConstant.ROBOT_MODULE, str);
        }
    }

    public String getRobotModel() {
        if (TextUtils.isEmpty(this.mRobotModel)) {
            this.mRobotModel = this.mSPUtils.getString(SpConstant.ROBOT_MODULE, AppConstant.ROBOT_MODULE);
        }
        return this.mRobotModel;
    }

    public void setWuHanAllowUrl(String str) {
        if (!TextUtils.isEmpty(this.mWuHanAllowUrl) && !str.equals(this.mWuHanAllowUrl)) {
            this.mWuHanAllowUrl = str;
            MyLogUtils.Logd("NETWORK_TAG", "setWuHanAllowUrl:" + str);
            this.mSPUtils.putString(SpConstant.SP_WUHAN_ALLOW_URL, this.mWuHanAllowUrl);
        }
    }

    public String getWuHanAllowUrl() {
        if (TextUtils.isEmpty(this.mWuHanAllowUrl)) {
            this.mWuHanAllowUrl = this.mSPUtils.getString(SpConstant.SP_WUHAN_ALLOW_URL, NetConstant.INSTANCE.getDEFAULT_SERVICE_URL());
        }
        return this.mWuHanAllowUrl;
    }

    public void setTcpIp(String str) {
        if (!TextUtils.isEmpty(str) && !str.equals(this.mTcpIp)) {
            this.mTcpIp = str;
            this.mSPUtils.putString(SpConstant.TCP_IP, str);
        }
    }

    public String getTcpIp() {
        if (TextUtils.isEmpty(this.mTcpIp)) {
            this.mTcpIp = this.mSPUtils.getString(SpConstant.TCP_IP, NetConstant.INSTANCE.getIP());
        }
        return this.mTcpIp;
    }

    public void setVoiceIp(String str) {
        this.mSPUtils.putString(SpConstant.VOICE_IP, str);
    }

    public synchronized String getVoiceIp() {
        return this.mSPUtils.getString(SpConstant.VOICE_IP, "");
    }

    public void setZiyanVoiceIP(String str) {
        this.mSPUtils.putString(SpConstant.ZIYAN_VOICE_IP, str);
    }

    public synchronized String getZiyanVoiceIp() {
        return this.mSPUtils.getString(SpConstant.ZIYAN_VOICE_IP, "");
    }

    public void setIsYunJi(boolean z) {
        this.mSPUtils.putBoolean(SpConstant.IS_YUNJI, z, true);
    }

    public synchronized boolean getIsYunJi() {
        return this.mSPUtils.getBoolean(SpConstant.IS_YUNJI, false);
    }

    public void setVoicePort(int i) {
        if (i != this.mVoicePort) {
            this.mVoicePort = i;
            this.mSPUtils.putInt(SpConstant.VOICE_PORT, i);
        }
    }

    public int getVoicePort() {
        int i = this.mSPUtils.getInt(SpConstant.VOICE_PORT, 8000);
        this.mVoicePort = i;
        return i;
    }

    public void setRobotActivate(boolean z) {
        if (this.mRobotIsActivate != z) {
            this.mRobotIsActivate = z;
            this.mSPUtils.putBoolean(SpConstant.SP_ROBOT_ACTIVATE, z);
        }
        this.mRobotIsActivate = z;
    }

    public boolean isRobotActivate() {
        return this.mSPUtils.getBoolean(SpConstant.SP_ROBOT_ACTIVATE, this.mRobotIsActivate);
    }

    public void setRobotType(int i) {
        if (i != this.mRobotType) {
            this.mRobotType = i;
            this.mSPUtils.putInt(SpConstant.ROBOT_TYPE, i);
        }
    }

    public int getRobotType() {
        if (this.mRobotType == 0) {
            this.mRobotType = this.mSPUtils.getInt(SpConstant.ROBOT_TYPE, 2);
        }
        return this.mRobotType;
    }

    public void setModleLevelString(int i, String str) {
        if (i == 1) {
            this.mSPUtils.putString(SpConstant.MODLE_LEVEL_RECORD, str);
        } else if (i == 2) {
            this.mSPUtils.putString(SpConstant.MODLE_LEVEL_VERIFY, str);
        } else if (i == 3) {
            this.mSPUtils.putString(SpConstant.MODLE_LEVEL_DIY, str);
        } else if (i == 4) {
            this.mSPUtils.putString(SpConstant.MODLE_LEVEL_HEALTH, str);
        }
    }

    public String getModleLevelString(int i) {
        String string = this.mSPUtils.getString(SpConstant.MODLE_LEVEL_RECORD, ConvertUtils.bytes2HexString(new byte[]{1, 1, 0, 0, 0, 0, 0, 0, 0}));
        if (i == 1) {
            return this.mSPUtils.getString(SpConstant.MODLE_LEVEL_RECORD, ConvertUtils.bytes2HexString(new byte[]{1, 1, 0, 0, 0, 0, 0, 0, 0}));
        }
        if (i == 2) {
            return this.mSPUtils.getString(SpConstant.MODLE_LEVEL_VERIFY, ConvertUtils.bytes2HexString(new byte[]{1, 1, 1, 1, 1, 0, 0, 1, 0}));
        }
        if (i == 3) {
            return this.mSPUtils.getString(SpConstant.MODLE_LEVEL_DIY, ConvertUtils.bytes2HexString(new byte[]{0, 1, 0, 0, 0, 0, 0, 0, 0}));
        }
        if (i != 4) {
            return string;
        }
        return this.mSPUtils.getString(SpConstant.MODLE_LEVEL_HEALTH, ConvertUtils.bytes2HexString(new byte[]{1, 1, 0, 0, 0, 0, 0, 0, 1}));
    }

    public void setAlarmTemp(float f) {
        if (f != this.mAlarmTemp) {
            this.mAlarmTemp = f;
            this.mSPUtils.putFloat(SpConstant.ALARM_TEMP, f);
        }
    }

    public float getAlarmTemp() {
        float f = this.mSPUtils.getFloat(SpConstant.ALARM_TEMP, 37.3f);
        this.mAlarmTemp = f;
        return f;
    }

    public void setPicTimeValue(int i) {
        if (this.mPicTimeValue != i) {
            this.mPicTimeValue = i;
            this.mSPUtils.putInt(SpConstant.PIC_TIME_VALUE, i);
        }
    }

    public int getPicTimeValue() {
        int i = this.mSPUtils.getInt(SpConstant.PIC_TIME_VALUE, 10);
        this.mPicTimeValue = i;
        return i;
    }

    public void setAdsStatus(boolean z) {
        if (this.mIndependent != z) {
            this.mIndependent = z;
            this.mSPUtils.putBoolean(SpConstant.INDEPENDENT, z);
        }
    }

    public boolean getAdsStatus() {
        boolean z = this.mSPUtils.getBoolean(SpConstant.INDEPENDENT, false);
        this.mIndependent = z;
        return z;
    }

    public int getLanguageOne() {
        int i = this.mSPUtils.getInt(SpConstant.LANGUAGE_ONE, -1);
        this.mLanguageOne = i;
        return i;
    }

    public int getLanguageTwo() {
        int i = this.mSPUtils.getInt(SpConstant.LANGUAGE_TWO, -1);
        this.mLanguageTwo = i;
        return i;
    }

    public void setLanguageOne(int i) {
        if (i != this.mLanguageOne) {
            this.mLanguageOne = i;
            this.mSPUtils.putInt(SpConstant.LANGUAGE_ONE, i);
        }
    }

    public void setLanguageTwo(int i) {
        if (i != this.mLanguageTwo) {
            this.mLanguageTwo = i;
            this.mSPUtils.putInt(SpConstant.LANGUAGE_TWO, i);
        }
    }

    public String getSystemPwd() {
        String string = this.mSPUtils.getString(SpConstant.SYSTEM_PASSWORD, "888888");
        this.mSystemPassword = string;
        return string;
    }

    public void setSystemPassword(String str) {
        if (!str.equals(this.mSystemPassword)) {
            this.mSystemPassword = str;
            this.mSPUtils.putString(SpConstant.SYSTEM_PASSWORD, str);
        }
    }

    public void setUserArea(int i) {
        if (i != this.mUserArea) {
            this.mUserArea = i;
            this.mSPUtils.putInt(SpConstant.USER_AREA, i);
        }
    }

    public int getUserArea() {
        int i = this.mSPUtils.getInt(SpConstant.USER_AREA, 0);
        this.mUserArea = i;
        return i;
    }

    public void setIdentifySupport(int i) {
        if (i != this.mIdentifySupport) {
            this.mIdentifySupport = i;
            this.mSPUtils.putInt(SpConstant.IDENTIFY_SUPPORT, i);
        }
    }

    public int getIdentifySupport() {
        int i = this.mSPUtils.getInt(SpConstant.IDENTIFY_SUPPORT, 0);
        this.mIdentifySupport = i;
        return i;
    }

    public void setLanguageCode(int i) {
        if (i != this.mLanguageCode) {
            this.mLanguageCode = i;
            this.mSPUtils.putInt(SpConstant.LANGUAGE_CODE, i);
        }
    }

    public int getLanguageCode() {
        int i = this.mSPUtils.getInt(SpConstant.LANGUAGE_CODE, 0);
        this.mLanguageCode = i;
        if (i < 0) {
            this.mLanguageCode = 0;
        }
        return this.mLanguageCode;
    }

    public void setSprayGear(boolean z) {
        setSprayGear(z ? 1 : 2);
    }

    public void setSprayGear(int i) {
        if (i != this.mSprayGear) {
            this.mSprayGear = i;
            this.mSPUtils.putInt(SpConstant.SP_SPRAY_GEAR, i);
        }
    }

    public int getSprayGear() {
        return this.mSprayGear;
    }

    public void saveNewUpdateVersionId(String str) {
        if (!TextUtils.isEmpty(str) && !str.equals(getNewUpdateVersionId())) {
            this.mNewUpdateVersionId = str;
            this.mSPUtils.putString(SpConstant.NEED_UPDATE_VERSION, str);
        }
    }

    public String getNewUpdateVersionId() {
        String string = this.mSPUtils.getString(SpConstant.NEED_UPDATE_VERSION, "");
        this.mNewUpdateVersionId = string;
        return string;
    }

    public void saveIgnoreVersionId(String str) {
        if (!TextUtils.isEmpty(str) && !str.equals(this.mIgnoreUpdateVersionId)) {
            this.mIgnoreUpdateVersionId = str;
            this.mSPUtils.putString(SpConstant.IGNORE_UPDATE_VERSION, str);
        }
    }

    public String getIgnoreVersionId() {
        if (this.mIgnoreUpdateVersionId == null) {
            this.mIgnoreUpdateVersionId = this.mSPUtils.getString(SpConstant.IGNORE_UPDATE_VERSION, (String) null);
        }
        return this.mIgnoreUpdateVersionId;
    }

    public boolean getAlwaysThermometry() {
        boolean z = this.mSPUtils.getBoolean(SpConstant.ALWAYS_THERMOMETRY, false);
        this.mAlwaysThermometry = z;
        return z;
    }

    public void setAlwaysThermometry(boolean z) {
        this.mAlwaysThermometry = z;
        this.mSPUtils.putBoolean(SpConstant.ALWAYS_THERMOMETRY, z);
    }

    public void setUserAccount(String str) {
        this.mUserAccount = str;
        this.mSPUtils.putString(SpConstant.USER_ACCOUNT, str);
    }

    public String getUserAccount() {
        String string = this.mSPUtils.getString(SpConstant.USER_ACCOUNT, "");
        this.mUserAccount = string;
        return string;
    }

    public void setOptimizeOutElevator(boolean z) {
        if (z != this.mOptimizeOutElevator) {
            this.mOptimizeOutElevator = z;
            this.mSPUtils.putBoolean(SpConstant.SP_OPTIMIZE_OUT_ELEVATOR, z);
        }
    }

    public boolean isOptimizeOutElevator() {
        return this.mOptimizeOutElevator;
    }

    public void setLowBatteryValue(int i) {
        this.mLowBatteryValue = i;
        this.mSPUtils.putInt(SpConstant.SP_LOW_BATTERY_VALUE, i);
    }

    public int getLowBatteryValue() {
        return this.mSPUtils.getInt(SpConstant.SP_LOW_BATTERY_VALUE, 20);
    }

    public void setIsOpenCharge(boolean z) {
        this.misOpenLowPowerBackChargingPile = z;
        this.mSPUtils.putBoolean(SpConstant.SP_LOW_BATTERY_CHARGE, z);
    }

    public boolean getIsOpenCharge() {
        return this.misOpenLowPowerBackChargingPile;
    }

    public boolean isIdleTimeBackChargingPile() {
        return this.mSPUtils.getBoolean("WaterChassisIdleTimeBackChargingPileFlag", false);
    }

    public void setIdleTimeBackChargingPile(boolean z) {
        this.mIdleTimeBackChargingPile = z;
        this.mSPUtils.putBoolean("WaterChassisIdleTimeBackChargingPileFlag", z);
    }

    public boolean getHasFace() {
        boolean z = this.mSPUtils.getBoolean(SpConstant.HAS_FACE, false);
        this.mHasFace = z;
        return z;
    }

    public void setHasFace(boolean z) {
        if (this.mHasFace != z) {
            this.mHasFace = z;
            this.mSPUtils.putBoolean(SpConstant.HAS_FACE, z);
        }
    }

    public void setTempThreshold(float f) {
        AppConstant.HK_TEMPERATURE_THRESHOLD = f;
        this.mTempThreshold = f;
        this.mSPUtils.putFloat(SpConstant.SP_TEMP_THRESHOLD, f);
        AppConstant.HK_TEMPERATURE_THRESHOLD = this.mTempThreshold;
    }

    public float getTempThreshold() {
        float f = this.mSPUtils.getFloat(SpConstant.SP_TEMP_THRESHOLD, AppConstant.HK_TEMPERATURE_THRESHOLD);
        this.mTempThreshold = f;
        return f;
    }

    public void setFaceThreshold(float f) {
        this.mFaceThreshold = f;
        this.mSPUtils.putFloat(SpConstant.SP_FACE_THRESHOLD, f);
    }

    public float getFaceThreshold() {
        float f = this.mSPUtils.getFloat(SpConstant.SP_FACE_THRESHOLD, 0.8f);
        this.mFaceThreshold = f;
        return f;
    }

    public void setFaceSensitivity(float f) {
        this.mFaceSensitivity = f;
        this.mSPUtils.putFloat(SpConstant.FACE_SENSITIVITY, f);
    }

    public float getFaceSensitivity() {
        float f = this.mSPUtils.getFloat(SpConstant.FACE_SENSITIVITY, 1.0f);
        this.mFaceSensitivity = f;
        return f;
    }

    public void setFaceRectLeft(int i) {
        this.mFaceRectLeft = (float) i;
        this.mSPUtils.putInt(SpConstant.FACE_RECT_LEFT, i);
    }

    public int getFaceRectLeft() {
        int i = this.mSPUtils.getInt(SpConstant.FACE_RECT_LEFT, 0);
        this.mFaceRectLeft = (float) i;
        return i;
    }

    public void setFaceRectRight(int i) {
        this.mFaceRectRight = (float) i;
        this.mSPUtils.putInt(SpConstant.FACE_RECT_RIGHT, i);
    }

    public int getFaceRectRight() {
        int i = this.mSPUtils.getInt(SpConstant.FACE_RECT_RIGHT, 0);
        this.mFaceRectRight = (float) i;
        return i;
    }

    public void setFaceRectTop(int i) {
        this.mFaceRectTop = (float) i;
        this.mSPUtils.putInt(SpConstant.FACE_RECT_TOP, i);
    }

    public int getFaceRectTop() {
        int i = this.mSPUtils.getInt(SpConstant.FACE_RECT_TOP, 0);
        this.mFaceRectTop = (float) i;
        return i;
    }

    public void setFaceRectBottom(int i) {
        this.mFaceRectBottom = (float) i;
        this.mSPUtils.putInt(SpConstant.FACE_RECT_BOTTOM, i);
    }

    public int getFaceRectBottom() {
        int i = this.mSPUtils.getInt(SpConstant.FACE_RECT_BOTTOM, 0);
        this.mFaceRectBottom = (float) i;
        return i;
    }

    public int getViwCameraType() {
        int i = this.mSPUtils.getInt(SpConstant.VIW_CAMERA_TYPE, 1);
        this.mViwCameraType = i;
        return i;
    }

    public void setViwCameraType(int i) {
        this.mViwCameraType = i;
        this.mSPUtils.putInt(SpConstant.VIW_CAMERA_TYPE, i);
    }

    public void setOpenMuteVideoAd(boolean z) {
        this.mIsOpenMuteVideoAd = z;
        this.mSPUtils.putBoolean(SpConstant.SP_SET_MUTE_VIDEO_AD, z);
    }

    public boolean isOpenMuteVideoAd() {
        return this.mIsOpenMuteVideoAd;
    }

    public boolean getFaceRecognizeGreet() {
        return this.mFaceRecognizeGreet;
    }

    public void setFaceRecognizeGreet(boolean z) {
        this.mFaceRecognizeGreet = z;
        this.mSPUtils.putBoolean(SpConstant.FACE_RECOGNITION_GREET, z);
    }

    public boolean getWifiEnable() {
        return this.mSPUtils.getBoolean(SpConstant.SP_WIFI_ENABLE, true);
    }

    public void setWifiEnable(boolean z) {
        this.mSPUtils.putBoolean(SpConstant.SP_WIFI_ENABLE, z, true);
    }

    public String getCurrWifiSSID() {
        return this.mSPUtils.getString(SpConstant.SP_WIFI_CURR_SSID, "");
    }

    public void setCurrWifiSSID(String str) {
        this.mSPUtils.putString(SpConstant.SP_WIFI_CURR_SSID, str, true);
    }

    public String getLastWifiSSID() {
        return this.mSPUtils.getString(SpConstant.SP_WIFI_LAST_SSID, "");
    }

    public void setLastWifiSSID(String str) {
        this.mSPUtils.putString(SpConstant.SP_WIFI_LAST_SSID, str, true);
    }

    public void setRobotLock(String str) {
        this.mSPUtils.putString(SpConstant.SP_ROBOT_LOCK, str, true);
    }

    public String getRobotLock() {
        return this.mSPUtils.getString(SpConstant.SP_ROBOT_LOCK, VisitByPhone.UNVERIFIED);
    }

    public boolean getClockIn() {
        return this.mSPUtils.getBoolean(SpConstant.SP_IS_CLOCK_IN, false);
    }

    public void setClockIn(boolean z) {
        this.mSPUtils.put(SpConstant.SP_IS_CLOCK_IN, z);
    }

    public boolean getScanCodeState() {
        return this.mSPUtils.getBoolean(SpConstant.SP_QR_CODE_STATE, false);
    }

    public void setScanCodeState(boolean z) {
        this.mSPUtils.putBoolean(SpConstant.SP_QR_CODE_STATE, z);
    }

    public boolean getPassManage() {
        return this.mSPUtils.getBoolean(SpConstant.SP_PASS_MANAGE, false);
    }

    public void setPassManage(boolean z) {
        this.mSPUtils.putBoolean(SpConstant.SP_PASS_MANAGE, z);
    }

    public boolean getOpenDoorId1() {
        return this.mSPUtils.getBoolean(SpConstant.SP_PASS_MANAGE_OPENDOOR_ID1, false);
    }

    public void setOpenDoorId1(boolean z) {
        this.mSPUtils.putBoolean(SpConstant.SP_PASS_MANAGE_OPENDOOR_ID1, z);
    }

    public boolean getOpenDoorId2() {
        return this.mSPUtils.getBoolean(SpConstant.SP_PASS_MANAGE_OPENDOOR_ID2, false);
    }

    public void setOpenDoorId2(boolean z) {
        this.mSPUtils.putBoolean(SpConstant.SP_PASS_MANAGE_OPENDOOR_ID2, z);
    }

    public boolean getOpenDoorId3() {
        return this.mSPUtils.getBoolean(SpConstant.SP_PASS_MANAGE_OPENDOOR_ID3, false);
    }

    public void setOpenDoorId3(boolean z) {
        this.mSPUtils.putBoolean(SpConstant.SP_PASS_MANAGE_OPENDOOR_ID3, z);
    }

    public boolean getOpenDoorId4() {
        return this.mSPUtils.getBoolean(SpConstant.SP_PASS_MANAGE_OPENDOOR_ID4, false);
    }

    public void setOpenDoorId4(boolean z) {
        this.mSPUtils.putBoolean(SpConstant.SP_PASS_MANAGE_OPENDOOR_ID4, z);
    }

    public boolean getOpenDoorId5() {
        return this.mSPUtils.getBoolean(SpConstant.SP_PASS_MANAGE_OPENDOOR_ID5, false);
    }

    public void setOpenDoorId5(boolean z) {
        this.mSPUtils.putBoolean(SpConstant.SP_PASS_MANAGE_OPENDOOR_ID5, z);
    }

    public boolean getOpenDoorId6() {
        return this.mSPUtils.getBoolean(SpConstant.SP_PASS_MANAGE_OPENDOOR_ID6, false);
    }

    public void setOpenDoorId6(boolean z) {
        this.mSPUtils.putBoolean(SpConstant.SP_PASS_MANAGE_OPENDOOR_ID6, z);
    }

    public boolean getOpenDoorId7() {
        return this.mSPUtils.getBoolean(SpConstant.SP_PASS_MANAGE_OPENDOOR_ID7, false);
    }

    public void setOpenDoorId7(boolean z) {
        this.mSPUtils.putBoolean(SpConstant.SP_PASS_MANAGE_OPENDOOR_ID7, z);
    }

    public boolean getOpenDoorId8() {
        return this.mSPUtils.getBoolean(SpConstant.SP_PASS_MANAGE_OPENDOOR_ID8, false);
    }

    public void setOpenDoorId8(boolean z) {
        this.mSPUtils.putBoolean(SpConstant.SP_PASS_MANAGE_OPENDOOR_ID8, z);
    }

    public boolean getOpenDoorId9() {
        return this.mSPUtils.getBoolean(SpConstant.SP_PASS_MANAGE_OPENDOOR_ID9, false);
    }

    public void setOpenDoorId9(boolean z) {
        this.mSPUtils.putBoolean(SpConstant.SP_PASS_MANAGE_OPENDOOR_ID9, z);
    }

    public boolean getOpenDoorId10() {
        return this.mSPUtils.getBoolean(SpConstant.SP_PASS_MANAGE_OPENDOOR_ID10, false);
    }

    public void setOpenDoorId10(boolean z) {
        this.mSPUtils.putBoolean(SpConstant.SP_PASS_MANAGE_OPENDOOR_ID10, z);
    }

    public boolean getVisitorRegisterNow() {
        return this.mSPUtils.getBoolean(SpConstant.SP_VISITOR_REGISTER_NOW, false);
    }

    public void setVisitorRegisterNow(boolean z) {
        this.mSPUtils.putBoolean(SpConstant.SP_VISITOR_REGISTER_NOW, z);
    }

    public boolean getVisitorVerifySwitch() {
        return this.mSPUtils.getBoolean(SpConstant.SP_VISITOR_VERIFY_SWITCH, true);
    }

    public void setVisitorVerifySwitch(boolean z) {
        this.mSPUtils.putBoolean(SpConstant.SP_VISITOR_VERIFY_SWITCH, z);
    }

    public boolean getVisitorFaceVerify() {
        return this.mSPUtils.getBoolean(SpConstant.SP_VISITOR_FACE_VERIFY, false);
    }

    public void setVisitorFaceVerify(boolean z) {
        this.mSPUtils.putBoolean(SpConstant.SP_VISITOR_FACE_VERIFY, z);
    }

    public boolean getVisitorIdCardVerify() {
        return this.mSPUtils.getBoolean(SpConstant.SP_VISITOR_ID_CARD_VERIFY, false);
    }

    public void setVisitorIdCardVerify(boolean z) {
        this.mSPUtils.putBoolean(SpConstant.SP_VISITOR_ID_CARD_VERIFY, z);
    }

    public boolean getVisitorPersonIdCardVerify() {
        return this.mSPUtils.getBoolean(SpConstant.SP_VISITOR_PERSON_ID_CARD_VERIFY, false);
    }

    public void setVisitorPersonIdCardVerify(boolean z) {
        this.mSPUtils.putBoolean(SpConstant.SP_VISITOR_PERSON_ID_CARD_VERIFY, z);
    }

    public boolean getWearMarker() {
        return this.mIsWearMarker;
    }

    public void setWearMarker(boolean z) {
        this.mIsWearMarker = z;
        this.mSPUtils.putBoolean(SpConstant.SP_WEAR_MARK_HINT, z);
    }

    public void setWaterLightState(boolean z) {
        this.mSPUtils.putBoolean(SpConstant.SP_WATER_LIGHT_STATE, z);
    }

    public boolean isOpenWaterLight() {
        return this.mSPUtils.getBoolean(SpConstant.SP_WATER_LIGHT_STATE, true);
    }

    public synchronized boolean isOpenWarnLight() {
        return this.mOpenWarnLight;
    }

    public synchronized void openWarnLight(boolean z) {
        if (this.mOpenWarnLight != z) {
            this.mOpenWarnLight = z;
            this.mSPUtils.putBoolean(SpConstant.SP_WARN_LIGHT_STATE, z);
        }
    }

    public int getSpeed() {
        return this.mSpeed;
    }

    public void setSpeed(int i) {
        this.mSpeed = i;
    }

    public void setMaxSpeedRatio(float f) {
        this.mSPUtils.putFloat(SpConstant.SP_MAX_SPEED_RATIO, f);
        if (f >= 0.8f) {
            setSpeed(3);
        } else if (f >= 0.5f) {
            setSpeed(2);
        } else {
            setSpeed(1);
        }
    }

    public float getMaxSpeedRatio() {
        return this.mSPUtils.getFloat(SpConstant.SP_MAX_SPEED_RATIO, 0.5f);
    }

    public boolean isOpenSmogAlarm() {
        return this.mSPUtils.getBoolean(SpConstant.SP_WATER_CHASSIS_SMOG_FLAG, true);
    }

    public void setOpenSmogAlarm(boolean z) {
        this.mSPUtils.putBoolean(SpConstant.SP_WATER_CHASSIS_SMOG_FLAG, z);
    }

    public boolean isOpenOverTempAlarm() {
        return this.mOpenOverTempAlarm;
    }

    public void setOpenOverTempAlarm(boolean z) {
        if (this.mOpenOverTempAlarm != z) {
            this.mOpenOverTempAlarm = z;
            this.mSPUtils.putBoolean(SpConstant.SP_WATER_CHASSIS_OPEN_OVER_TEMPERATURE_FLAG, z);
        }
    }

    public float getTemperatureOffset() {
        return this.mSPUtils.getFloat(SpConstant.SP_TEMPERATURE_OFFSET, 0.0f);
    }

    public void setTemperatureOffset(float f) {
        this.mSPUtils.putFloat(SpConstant.SP_TEMPERATURE_OFFSET, f);
    }

    public boolean getMeetingSignIn() {
        return this.mSPUtils.getBoolean(SpConstant.SP_MEETING_SIGN_IN, false);
    }

    public void setMeetingSignIn(boolean z) {
        this.mSPUtils.put(SpConstant.SP_MEETING_SIGN_IN, z);
    }

    public boolean isIdleBroad() {
        return this.mSPUtils.getBoolean(SpConstant.SP_IDLE_BROAD, false);
    }

    public void setIdleBroad(boolean z) {
        this.mSPUtils.put(SpConstant.SP_IDLE_BROAD, z);
    }

    public boolean isIdleBroadTxt() {
        return this.mSPUtils.getBoolean(SpConstant.SP_IDLE_BROAD_TEXT, true);
    }

    public void setIdleBroadTxtStatus(boolean z) {
        this.mSPUtils.put(SpConstant.SP_IDLE_BROAD_TEXT, z);
    }

    public boolean isIdleBroadAudio() {
        return this.mSPUtils.getBoolean(SpConstant.SP_IDLE_BROAD_AUDIO, false);
    }

    public void setIdleBroadAudioStatus(boolean z) {
        this.mSPUtils.put(SpConstant.SP_IDLE_BROAD_AUDIO, z);
    }

    public void setBroadText(String str) {
        this.mSPUtils.putString(SpConstant.SP_IDLE_BROAD_TEXT_CONTENT, str);
    }

    public String getBroadText() {
        return this.mSPUtils.getString(SpConstant.SP_IDLE_BROAD_TEXT_CONTENT);
    }

    public void setBroadAudio(String str) {
        this.mSPUtils.putString(SpConstant.SP_IDLE_BROAD_AUDIO_CONTENT, str);
    }

    public String getBroadAudio() {
        return this.mSPUtils.getString(SpConstant.SP_IDLE_BROAD_AUDIO_CONTENT);
    }

    public void setIdleBroadTime(int i) {
        this.mSPUtils.putInt(SpConstant.SP_IDLE_BROAD_TIME, i);
    }

    public int getIdleBroadTime() {
        return this.mSPUtils.getInt(SpConstant.SP_IDLE_BROAD_TIME, 15);
    }

    public void setAudioBroadTime(int i) {
        this.mSPUtils.putInt(SpConstant.SP_AUDIO_BROAD_TIME, i);
    }

    public int getAudioBroadTime() {
        return this.mSPUtils.getInt(SpConstant.SP_AUDIO_BROAD_TIME, 15);
    }

    public void setFaceAudioActivate(boolean z) {
        this.mSPUtils.put(SpConstant.SP_FACE_AUDIO_ACTIVATE, z);
    }

    public boolean getFaceAudioActivate() {
        return this.mSPUtils.getBoolean(SpConstant.SP_FACE_AUDIO_ACTIVATE, true);
    }

    public void setProjectType(String str) {
        this.mSPUtils.putString(SpConstant.SP_HOME_PROJECT_TYPE, str);
    }

    public String getProjectType() {
        return this.mSPUtils.getString(SpConstant.SP_HOME_PROJECT_TYPE, "");
    }

    public void setOpenQrCodeFunction(boolean z) {
        this.mSPUtils.put(SpConstant.SP_OPEN_QR_CODE_FUNCTION, z);
    }

    public boolean getOpenQrCodeFunction() {
        return this.mSPUtils.getBoolean(SpConstant.SP_OPEN_QR_CODE_FUNCTION, false);
    }

    public void setOpenVoiceCallButton(boolean z) {
        this.mIsOpenVoiceCallButton = z;
        this.mSPUtils.put(SpConstant.SP_SET_VOICE_CALL_BUTTON, this.mIsOpenVoiceCallButton);
    }

    public boolean isOpenVoiceCallButton() {
        return this.mIsOpenVoiceCallButton;
    }

    public void setArcSoftActiveInfo(String str) {
        this.mSPUtils.put(SpConstant.SP_ARC_SOFT_ACTIVE_INFO, str);
    }

    public String getArcSoftActiveInfo() {
        return this.mSPUtils.getString(SpConstant.SP_ARC_SOFT_ACTIVE_INFO, "");
    }

    public void setSwitchTime(int i) {
        this.mSPUtils.putInt(SpConstant.SP_SWITCH_ITME_ID, i);
    }

    public int getSwitchTime() {
        return this.mSPUtils.getInt(SpConstant.SP_SWITCH_ITME_ID, 5);
    }

    public void setBroadCastTime(int i) {
        this.mSPUtils.putInt(SpConstant.SP_BROADCAST_ITME_ID, i);
    }

    public int getBroadCastTime() {
        return this.mSPUtils.getInt(SpConstant.SP_BROADCAST_ITME_ID, 1);
    }

    public void setFreeBroadcastSwitchButton(boolean z) {
        this.mSPUtils.put(SpConstant.SP_FREEBROADCAST_SWITCHBUTTON_ID, z);
    }

    public boolean getFreeBroadcastSwitchButton() {
        return this.mSPUtils.getBoolean(SpConstant.SP_FREEBROADCAST_SWITCHBUTTON_ID, false);
    }

    public void setBroadcastSwitchButton(boolean z) {
        this.mSPUtils.put(SpConstant.SP_BROADCAST_SWITCH_BUTTON_ID, z);
    }

    public boolean getBroadcastSwitchButton() {
        return this.mSPUtils.getBoolean(SpConstant.SP_BROADCAST_SWITCH_BUTTON_ID, false);
    }

    public void setTextBroadcastTv(boolean z) {
        this.mSPUtils.put(SpConstant.SP_TEXT_BROADCAST_TV_ID, z);
    }

    public boolean getTextBroadcastTv() {
        return this.mSPUtils.getBoolean(SpConstant.SP_TEXT_BROADCAST_TV_ID, false);
    }

    public void setAudioBroadcastTv(boolean z) {
        this.mSPUtils.put(SpConstant.SP_AUDIO_BROADCAST_TV_ID, z);
    }

    public boolean getAudioBroadcastTv() {
        return this.mSPUtils.getBoolean(SpConstant.SP_AUDIO_BROADCAST_TV_ID, false);
    }

    public void setScreenText(String str) {
        this.mSPUtils.putString(SpConstant.SP_SCREEN_TEXT_ID, str);
    }

    public String getScreenText() {
        return this.mSPUtils.getString(SpConstant.SP_SCREEN_TEXT_ID);
    }

    public void setBroadcastDataStr(String str) {
        this.mSPUtils.putString(SpConstant.SP_BROADCAST_TEXT_ID, str);
    }

    public String getBroadcastDataStr() {
        return this.mSPUtils.getString(SpConstant.SP_BROADCAST_TEXT_ID);
    }

    public void setMasterVolume(Float f) {
        this.mSPUtils.put(SpConstant.MASTERVOLUME, f.floatValue());
    }

    public Float getMasterVolume() {
        return Float.valueOf(this.mSPUtils.getFloat(SpConstant.MASTERVOLUME, -1.0f));
    }

    public void setViceVolume(Float f) {
        this.mSPUtils.put(SpConstant.VICEVOLUME, f.floatValue());
    }

    public Float getViceVolume() {
        return Float.valueOf(this.mSPUtils.getFloat(SpConstant.VICEVOLUME, -1.0f));
    }

    public void setFilePathByUri(String str) {
        this.mSPUtils.putString(SpConstant.SP_FILEPATHBYURI_ID, str);
    }

    public String getFilePathByUri() {
        return this.mSPUtils.getString(SpConstant.SP_FILEPATHBYURI_ID);
    }
}
