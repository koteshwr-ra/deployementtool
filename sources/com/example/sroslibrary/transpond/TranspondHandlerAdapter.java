package com.example.sroslibrary.transpond;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class TranspondHandlerAdapter extends IoHandlerAdapter {
    private static final String TAG = "NETWORK_TAG";
    private OnTranspondHandlerCallBack mOnTranspondHandlerCallBack;

    public interface OnTranspondHandlerCallBack {
        void messageReceived(Object obj);

        void sessionClose();

        void sessionOpen();
    }

    public void sessionOpened(IoSession ioSession) throws Exception {
        super.sessionOpened(ioSession);
        OnTranspondHandlerCallBack onTranspondHandlerCallBack = this.mOnTranspondHandlerCallBack;
        if (onTranspondHandlerCallBack != null) {
            onTranspondHandlerCallBack.sessionOpen();
        }
    }

    public void sessionClosed(IoSession ioSession) throws Exception {
        super.sessionClosed(ioSession);
        OnTranspondHandlerCallBack onTranspondHandlerCallBack = this.mOnTranspondHandlerCallBack;
        if (onTranspondHandlerCallBack != null) {
            onTranspondHandlerCallBack.sessionClose();
        }
    }

    public void messageReceived(IoSession ioSession, Object obj) throws Exception {
        super.messageReceived(ioSession, obj);
        OnTranspondHandlerCallBack onTranspondHandlerCallBack = this.mOnTranspondHandlerCallBack;
        if (onTranspondHandlerCallBack != null) {
            onTranspondHandlerCallBack.messageReceived(obj);
        }
    }

    public void setOnTranspondHandlerCallBack(OnTranspondHandlerCallBack onTranspondHandlerCallBack) {
        this.mOnTranspondHandlerCallBack = onTranspondHandlerCallBack;
    }
}
