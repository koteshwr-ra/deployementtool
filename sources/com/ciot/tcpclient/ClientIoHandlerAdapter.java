package com.ciot.tcpclient;

import android.util.Log;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class ClientIoHandlerAdapter extends IoHandlerAdapter {
    private static final String TAG = "ClientIoHandlerAdapter";
    private OnMessageReceivedCallBack mOnMessageReceivedCallBack;

    public interface OnMessageReceivedCallBack {
        void messageReceive(Object obj);
    }

    public void messageReceived(IoSession ioSession, Object obj) throws Exception {
        super.messageReceived(ioSession, obj);
        OnMessageReceivedCallBack onMessageReceivedCallBack = this.mOnMessageReceivedCallBack;
        if (onMessageReceivedCallBack != null) {
            onMessageReceivedCallBack.messageReceive(obj);
        }
    }

    public void sessionCreated(IoSession ioSession) throws Exception {
        super.sessionCreated(ioSession);
        Log.w(TAG, "sessionCreated: 创建连接 " + ioSession);
    }

    public void setOnMessageReceivedCallBack(OnMessageReceivedCallBack onMessageReceivedCallBack) {
        this.mOnMessageReceivedCallBack = onMessageReceivedCallBack;
    }
}
