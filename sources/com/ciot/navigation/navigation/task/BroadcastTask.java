package com.ciot.navigation.navigation.task;

import android.os.SystemClock;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ciot.base.util.MyLogUtils;
import com.ciot.realm.db.patrol.Operation;
import com.limpoxe.support.servicemanager.provider.ARouterConstants;
import com.limpoxe.support.servicemanager.provider.speech.IMusic;
import com.limpoxe.support.servicemanager.provider.speech.ISpeechManagerProvider;
import com.limpoxe.support.servicemanager.provider.speech.OnSynthesizeListener;
import com.limpoxe.support.servicemanager.provider.speech.SpeakOption;

public class BroadcastTask implements Runnable {
    public static IMusic iMusic;
    private volatile Operation mOperation;
    private boolean mPlaying;
    private boolean mStopBroadcastTask;
    private volatile int mTaskStatus = 2;

    public synchronized void setBroadcast(Operation operation) {
        this.mOperation = operation;
        this.mTaskStatus = 1;
        this.mStopBroadcastTask = false;
        MyLogUtils.Logd("NAVIGATION_TAG", "BroadcastTask,setBroadcast=" + operation.toString());
    }

    public void run() {
        int i = 0;
        setTaskStatus(0);
        int operTime = this.mOperation.getOperTime() * 1000;
        int operRepeat = this.mOperation.getOperRepeat();
        String operText = this.mOperation.getOperText();
        MyLogUtils.Logd("NAVIGATION_TAG", "BroadcastTask operRepeat=" + operRepeat + "==operTime == " + operTime);
        while (true) {
            if (operRepeat != -1 && i >= operRepeat) {
                break;
            }
            MyLogUtils.Logd("NAVIGATION_TAG", "BroadcastTask mStopBroadcastTask=" + this.mStopBroadcastTask);
            if (!this.mStopBroadcastTask && !playTtsWaitComplete(operTime, operText)) {
                break;
            }
            i++;
            if (this.mStopBroadcastTask) {
                MyLogUtils.Logd("NAVIGATION_TAG", "BroadcastTask mStopBroadcastTask stopSpeak");
                ((ISpeechManagerProvider) ARouter.getInstance().build(ARouterConstants.LIB_SPEECH).navigation()).stopSpeak();
                break;
            }
        }
        if (this.mStopBroadcastTask) {
            setTaskStatus(3);
        } else {
            setTaskStatus(2);
        }
    }

    public synchronized void stopBroadcast() {
        MyLogUtils.Logd("NAVIGATION_TAG", "BroadcastTask stopBroadcast");
        this.mStopBroadcastTask = true;
    }

    public void pauseSpeak() {
        ((ISpeechManagerProvider) ARouter.getInstance().build(ARouterConstants.LIB_SPEECH).navigation()).pauseSpeak();
    }

    public void resumeSpeak() {
        ((ISpeechManagerProvider) ARouter.getInstance().build(ARouterConstants.LIB_SPEECH).navigation()).resumeSpeak();
    }

    public boolean isCancel() {
        return getTaskStatus() == 3;
    }

    public boolean isComplete() {
        return getTaskStatus() >= 2;
    }

    public synchronized int getTaskStatus() {
        return this.mTaskStatus;
    }

    private void setTaskStatus(int i) {
        MyLogUtils.Logd("NAVIGATION_TAG", "BroadcastTask setTaskStatus=" + i);
        this.mTaskStatus = i;
    }

    private boolean playTtsWaitComplete(int i, String str) {
        MyLogUtils.Logd("NAVIGATION_TAG", "BroadcastTask playTtsWaitComplete: " + str);
        ISpeechManagerProvider iSpeechManagerProvider = (ISpeechManagerProvider) ARouter.getInstance().build(ARouterConstants.LIB_SPEECH).navigation();
        iSpeechManagerProvider.startSpeakforState(str, (SpeakOption) null, new OnSynthesizeListener() {
            public void onBufferProgress(int i, int i2, int i3, String str) {
            }

            public void onInitSuccess() {
            }

            public void onSpeakPaused() {
            }

            public void onSpeakProgress(int i, int i2, int i3) {
            }

            public void onSpeakResumed() {
            }

            public void onSpeakStart() {
                MyLogUtils.Logd("NAVIGATION_TAG", "BroadcastTask onSpeakStart");
                BroadcastTask.this.setIsPlaying(true);
                if (BroadcastTask.iMusic != null) {
                    BroadcastTask.iMusic.pause();
                }
            }

            public void onSpeakFinish() {
                MyLogUtils.Logd("NAVIGATION_TAG", "BroadcastTask onSpeakFinish");
                BroadcastTask.this.setIsPlaying(false);
                if (BroadcastTask.iMusic != null) {
                    BroadcastTask.iMusic.resume();
                }
            }

            public void onSpeakError(int i, String str) {
                MyLogUtils.Logd("NAVIGATION_TAG", "BroadcastTask onSpeakError");
                BroadcastTask.this.setIsPlaying(false);
            }
        });
        while (true) {
            SystemClock.sleep(100);
            if (!this.mStopBroadcastTask) {
                if (!isPlaying()) {
                    break;
                }
            } else {
                MyLogUtils.Logd("NAVIGATION_TAG", "BroadcastTask stopSpeak");
                iSpeechManagerProvider.stopSpeak();
                SystemClock.sleep(1000);
                break;
            }
        }
        if (this.mStopBroadcastTask) {
            return true;
        }
        if (i >= 0) {
            SystemClock.sleep((long) i);
            return true;
        }
        SystemClock.sleep(1000);
        return true;
    }

    public synchronized void setIsPlaying(boolean z) {
        this.mPlaying = z;
    }

    private synchronized boolean isPlaying() {
        return this.mPlaying;
    }
}
