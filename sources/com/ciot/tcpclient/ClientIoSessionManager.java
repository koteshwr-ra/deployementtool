package com.ciot.tcpclient;

import android.util.Log;
import com.ciot.thread.SendMsgRunnable;
import com.ciot.thread.SocketThreadFactory;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.spi.LocationInfo;
import org.apache.mina.core.session.IoSession;

public class ClientIoSessionManager {
    private static ClientIoSessionManager sClientIoSessionManager;
    private volatile IoSession mIoSession;
    private final SendMsgRunnable mSendSrosMsgRunnable = new SendMsgRunnable();
    private volatile IoSession mSrosSession;
    private final ThreadPoolExecutor mThreadPoolExecutor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new SocketThreadFactory(SendMsgRunnable.class.getSimpleName()));

    private ClientIoSessionManager() {
    }

    public static ClientIoSessionManager getInstance() {
        if (sClientIoSessionManager == null) {
            synchronized (ClientIoSessionManager.class) {
                if (sClientIoSessionManager == null) {
                    sClientIoSessionManager = new ClientIoSessionManager();
                }
            }
        }
        return sClientIoSessionManager;
    }

    public Boolean sendChassisMsg(String str) {
        if (this.mIoSession == null) {
            return false;
        }
        this.mIoSession.write(str);
        return true;
    }

    public void sendChassisMsg(String str, Map<String, Object> map) {
        StringBuilder sb = new StringBuilder(str);
        sb.append(LocationInfo.NA);
        for (Map.Entry next : map.entrySet()) {
            sb.append((String) next.getKey());
            sb.append("=");
            sb.append(next.getValue());
            sb.append("&");
        }
        String substring = sb.substring(0, sb.length() - 1);
        Log.e("woaini", "sendChassisMsg: -----" + substring);
        if (this.mIoSession != null) {
            this.mIoSession.write(substring);
        }
    }

    public void setChassisSession(IoSession ioSession) {
        this.mIoSession = ioSession;
    }

    public void setSrosSession(IoSession ioSession) {
        this.mSrosSession = ioSession;
        if (ioSession != null && !this.mSendSrosMsgRunnable.isAlive()) {
            this.mThreadPoolExecutor.execute(this.mSendSrosMsgRunnable);
        }
        this.mSendSrosMsgRunnable.setSession(ioSession);
    }

    public void sendSrosMsg(Object obj) {
        SendMsgRunnable sendMsgRunnable = this.mSendSrosMsgRunnable;
        if (sendMsgRunnable != null) {
            sendMsgRunnable.takeMsg(obj);
        }
    }

    public void sendSrosMsg(byte[] bArr) {
        if (this.mSrosSession != null) {
            this.mSrosSession.write(bArr);
        }
    }
}
