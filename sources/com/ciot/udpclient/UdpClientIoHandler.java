package com.ciot.udpclient;

import android.util.Log;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class UdpClientIoHandler extends IoHandlerAdapter {
    public static final String TAG = UdpClientIoHandler.class.getSimpleName();
    private final OnUdpListener mUdpListener;

    public void messageSent(IoSession ioSession, Object obj) {
    }

    public UdpClientIoHandler(OnUdpListener onUdpListener) {
        this.mUdpListener = onUdpListener;
    }

    public void sessionOpened(IoSession ioSession) {
        OnUdpListener onUdpListener = this.mUdpListener;
        if (onUdpListener != null) {
            onUdpListener.onConnectUdp();
        }
    }

    public void sessionClosed(IoSession ioSession) {
        OnUdpListener onUdpListener = this.mUdpListener;
        if (onUdpListener != null) {
            onUdpListener.onDisConnectUdp();
        }
        ioSession.close(true);
    }

    public void messageReceived(IoSession ioSession, Object obj) {
        if (obj instanceof byte[]) {
            OnUdpListener onUdpListener = this.mUdpListener;
            if (onUdpListener != null) {
                onUdpListener.onReceiveMsg((byte[]) obj);
                return;
            }
            return;
        }
        String str = TAG;
        Log.i(str, "客户端成功接收到消息:" + obj.toString());
    }
}
