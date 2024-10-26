package com.example.sroslibrary.sros;

import com.ciot.base.constant.NetConstant;
import com.example.sroslibrary.SrosManager;
import com.tencent.bugly.Bugly;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class SrosHandlerAdapter extends IoHandlerAdapter {
    private static final String TAG = "NETWORK_TAG";
    private OnSrosHandlerCallBack mOnSrosHandlerCallBack;

    public interface OnSrosHandlerCallBack {
        void messageReceived(Object obj);

        void sessionClose();

        void sessionOpen();
    }

    public void sessionOpened(IoSession ioSession) throws Exception {
        super.sessionOpened(ioSession);
        OnSrosHandlerCallBack onSrosHandlerCallBack = this.mOnSrosHandlerCallBack;
        if (onSrosHandlerCallBack != null) {
            onSrosHandlerCallBack.sessionOpen();
        }
        SrosManager.getInstance().sendMsg(NetConstant.MSG_SROS_TCP_ONLINE, "true");
    }

    public void sessionClosed(IoSession ioSession) throws Exception {
        super.sessionClosed(ioSession);
        OnSrosHandlerCallBack onSrosHandlerCallBack = this.mOnSrosHandlerCallBack;
        if (onSrosHandlerCallBack != null) {
            onSrosHandlerCallBack.sessionClose();
        }
        SrosManager.getInstance().sendMsg(NetConstant.MSG_SROS_TCP_ONLINE, Bugly.SDK_IS_DEV);
    }

    public void messageReceived(IoSession ioSession, Object obj) throws Exception {
        super.messageReceived(ioSession, obj);
        OnSrosHandlerCallBack onSrosHandlerCallBack = this.mOnSrosHandlerCallBack;
        if (onSrosHandlerCallBack != null) {
            onSrosHandlerCallBack.messageReceived(obj);
        }
    }

    public void setOnSrosHandlerCallBack(OnSrosHandlerCallBack onSrosHandlerCallBack) {
        this.mOnSrosHandlerCallBack = onSrosHandlerCallBack;
    }
}
