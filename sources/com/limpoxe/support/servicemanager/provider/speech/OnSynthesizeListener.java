package com.limpoxe.support.servicemanager.provider.speech;

public interface OnSynthesizeListener {
    void onBufferProgress(int i, int i2, int i3, String str);

    void onInitSuccess();

    void onSpeakError(int i, String str);

    void onSpeakFinish();

    void onSpeakPaused();

    void onSpeakProgress(int i, int i2, int i3);

    void onSpeakResumed();

    void onSpeakStart();
}
