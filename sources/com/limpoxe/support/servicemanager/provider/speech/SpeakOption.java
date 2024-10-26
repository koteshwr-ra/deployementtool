package com.limpoxe.support.servicemanager.provider.speech;

import com.ciot.base.config.SpeechConstants;

public class SpeakOption {
    public static final int LAG_CHINESE = 0;
    public static final int LAG_ENGLISH_US = 1;
    private int dataType;
    private String mEngineType = SpeechConstants.TYPE_LOCAL;
    private int mLanguageType;
    private String mPitch;
    private String mSpeed;
    private String mVoiceName;
    private String mVolume;

    public int getLanguageType() {
        return this.mLanguageType;
    }

    public void setLanguageType(int i) {
        this.mLanguageType = i;
    }

    public String getVoiceName() {
        return this.mVoiceName;
    }

    public void setVoiceName(String str) {
        this.mVoiceName = str;
    }

    public String getSpeed() {
        return this.mSpeed;
    }

    public void setSpeed(String str) {
        this.mSpeed = str;
    }

    public String getPitch() {
        return this.mPitch;
    }

    public void setPitch(String str) {
        this.mPitch = str;
    }

    public String getVolume() {
        return this.mVolume;
    }

    public void setVolume(String str) {
        this.mVolume = str;
    }

    public String getEngineType() {
        return this.mEngineType;
    }

    public void setEngineType(String str) {
        this.mEngineType = str;
    }

    public int getDataType() {
        return this.dataType;
    }

    public void setDataType(int i) {
        this.dataType = i;
    }

    public String toString() {
        return "SpeakOption{mLanguageType=" + this.mLanguageType + ", mVoiceName='" + this.mVoiceName + '\'' + ", mSpeed='" + this.mSpeed + '\'' + ", mPitch='" + this.mPitch + '\'' + ", mVolume='" + this.mVolume + '\'' + ", dataType='" + this.dataType + '\'' + ", mEngineType='" + this.mEngineType + '\'' + '}';
    }
}
