package com.ciot.tcpclient;

import android.util.Log;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import org.apache.commons.lang3.StringUtils;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.service.IoService;
import org.apache.mina.core.service.IoServiceListener;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class ClientSocketUtil {
    private static final long CONNECT_TIMEOUT = 5000;
    public static final int SROS_TCP_PORT = 28888;
    private static final String TAG = "NETWORK_TAG";
    /* access modifiers changed from: private */
    public final int mClientType;
    private InetSocketAddress mInetSocketAddress;
    private NioSocketConnector mIoConnector;
    /* access modifiers changed from: private */
    public IoSession mIoSession;

    public ClientSocketUtil(String str, int i, CharsetCodecFactory charsetCodecFactory, IoHandlerAdapter ioHandlerAdapter, int i2) {
        this.mClientType = i2;
        this.mInetSocketAddress = new InetSocketAddress(str, i);
        NioSocketConnector nioSocketConnector = new NioSocketConnector();
        this.mIoConnector = nioSocketConnector;
        nioSocketConnector.setConnectTimeoutMillis(5000);
        this.mIoConnector.getSessionConfig().setReadBufferSize(10240);
        this.mIoConnector.getSessionConfig().setMinReadBufferSize(10240);
        if (i2 == 0) {
            this.mIoConnector.getSessionConfig().setReaderIdleTime(40);
        } else {
            this.mIoConnector.getSessionConfig().setReaderIdleTime(40);
        }
        this.mIoConnector.getFilterChain().addLast("protocol", new ProtocolCodecFilter(charsetCodecFactory));
        this.mIoConnector.setHandler(ioHandlerAdapter);
        this.mIoConnector.addListener(new IoServiceListener() {
            public void serviceActivated(IoService ioService) {
            }

            public void serviceDeactivated(IoService ioService) {
            }

            public void serviceIdle(IoService ioService, IdleStatus idleStatus) {
            }

            public void sessionCreated(IoSession ioSession) {
                Log.w("NETWORK_TAG", "已连接到服务器: " + ioSession);
            }

            public void sessionDestroyed(IoSession ioSession) {
                Log.w("NETWORK_TAG", "TCP连接已断开");
                IoSession unused = ClientSocketUtil.this.mIoSession = null;
                if (ClientSocketUtil.this.mClientType == 0) {
                    ClientIoSessionManager.getInstance().setChassisSession(ClientSocketUtil.this.mIoSession);
                } else {
                    ClientIoSessionManager.getInstance().setSrosSession(ClientSocketUtil.this.mIoSession);
                }
            }
        });
    }

    public boolean connect() {
        IoSession ioSession = this.mIoSession;
        if (ioSession != null) {
            if (!ioSession.isReaderIdle()) {
                return true;
            }
            Log.w("NETWORK_TAG", "读取空闲，断开连接: " + this.mIoSession);
            disconnectClient();
        }
        try {
            ConnectFuture connect = this.mIoConnector.connect((SocketAddress) this.mInetSocketAddress);
            connect.awaitUninterruptibly();
            this.mIoSession = connect.getSession();
            if (this.mClientType == 0) {
                ClientIoSessionManager.getInstance().setChassisSession(this.mIoSession);
            } else {
                ClientIoSessionManager.getInstance().setSrosSession(this.mIoSession);
            }
            return true;
        } catch (Exception e) {
            Log.w("NETWORK_TAG", "Failed to connect: " + e.getMessage() + StringUtils.SPACE + this.mInetSocketAddress);
            if (this.mIoSession == null) {
                return false;
            }
            this.mIoSession = null;
            if (this.mClientType == 0) {
                ClientIoSessionManager.getInstance().setChassisSession(this.mIoSession);
                return false;
            }
            ClientIoSessionManager.getInstance().setSrosSession(this.mIoSession);
            return false;
        }
    }

    private void disconnectClient() {
        try {
            if (this.mIoSession != null && this.mIoSession.isConnected()) {
                this.mIoSession.close(true);
                this.mIoSession.getCloseFuture().awaitUninterruptibly();
            }
            this.mIoSession = null;
        } catch (Exception unused) {
            this.mIoSession = null;
        }
    }

    public void serviceStop() {
        try {
            disconnectClient();
            if (this.mIoConnector != null && !this.mIoConnector.isDisposed()) {
                this.mIoConnector.dispose();
            }
            this.mIoConnector = null;
            Log.d("NETWORK_TAG", "ClientSocketUtil serviceStop");
        } catch (Exception unused) {
            this.mIoConnector = null;
        }
    }
}
