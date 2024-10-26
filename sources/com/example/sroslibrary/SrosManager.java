package com.example.sroslibrary;

public class SrosManager {
    private OnSrosManagerListener mListener;

    public static SrosManager getInstance() {
        return SrosManagerHolder.mSrosManager;
    }

    private static class SrosManagerHolder {
        /* access modifiers changed from: private */
        public static SrosManager mSrosManager = new SrosManager();

        private SrosManagerHolder() {
        }
    }

    public void sendMsg(String str, String str2) {
        OnSrosManagerListener onSrosManagerListener = this.mListener;
        if (onSrosManagerListener != null) {
            onSrosManagerListener.sendMsg(str, str2);
        }
    }

    public CharSequence getMsgContent(String str) {
        OnSrosManagerListener onSrosManagerListener = this.mListener;
        if (onSrosManagerListener != null) {
            return onSrosManagerListener.getMsgContent(str);
        }
        return null;
    }

    public void setMsgListener(OnSrosManagerListener onSrosManagerListener) {
        this.mListener = onSrosManagerListener;
    }
}
