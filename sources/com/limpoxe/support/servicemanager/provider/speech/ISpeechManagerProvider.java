package com.limpoxe.support.servicemanager.provider.speech;

import com.alibaba.android.arouter.facade.template.IProvider;
import java.util.List;

public interface ISpeechManagerProvider extends IProvider {
    void addNavigateKeyWord(String str, List<String> list);

    void pauseSpeak();

    void resumeSpeak();

    void startSpeak(String str);

    void startSpeakforState(String str, SpeakOption speakOption, OnSynthesizeListener onSynthesizeListener);

    void stopSpeak();
}
