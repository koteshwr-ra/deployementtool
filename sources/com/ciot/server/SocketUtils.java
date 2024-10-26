package com.ciot.server;

import android.os.SystemClock;
import android.util.Log;
import com.ciot.tcpclient.CharsetCodecFactory;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class SocketUtils {
    private static final String TAG = SocketUtils.class.getSimpleName();
    private IoAcceptor mIoAcceptor;

    public boolean serviceStart(int i, CharsetCodecFactory charsetCodecFactory, IoHandlerAdapter ioHandlerAdapter) {
        try {
            if (this.mIoAcceptor == null) {
                this.mIoAcceptor = new NioSocketAcceptor();
            } else {
                serviceStop();
                this.mIoAcceptor = new NioSocketAcceptor();
            }
            this.mIoAcceptor.setHandler(ioHandlerAdapter);
            this.mIoAcceptor.getFilterChain().addLast("protocol", new ProtocolCodecFilter(charsetCodecFactory));
            this.mIoAcceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 30);
            ((NioSocketAcceptor) this.mIoAcceptor).setReuseAddress(true);
            this.mIoAcceptor.bind((SocketAddress) new InetSocketAddress(i));
            String str = TAG;
            Log.d(str, "服务器启动成功... 端口号为：" + i);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            String str2 = TAG;
            Log.d(str2, "服务器启动异常..." + e.getMessage());
            return false;
        }
    }

    public void serviceRestart(int i, CharsetCodecFactory charsetCodecFactory, IoHandlerAdapter ioHandlerAdapter) {
        serviceStop();
        while (!serviceStart(i, charsetCodecFactory, ioHandlerAdapter)) {
            serviceStop();
            SystemClock.sleep(1000);
        }
    }

    public void serviceStop() {
        IoAcceptor ioAcceptor = this.mIoAcceptor;
        if (ioAcceptor != null) {
            try {
                ioAcceptor.unbind();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                this.mIoAcceptor.dispose();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.mIoAcceptor = null;
        }
    }
}
