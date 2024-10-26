package com.ciot.server;

import java.util.WeakHashMap;
import org.apache.mina.core.session.IoSession;

public class IoSessionManager {
    private static final String aaa = "\r\n";
    private static volatile IoSessionManager mIoSessionManager;
    private WeakHashMap<Long, IoSession> mSessionList = new WeakHashMap<>();

    private IoSessionManager() {
    }

    public static IoSessionManager getInstance() {
        if (mIoSessionManager == null) {
            synchronized (IoSessionManager.class) {
                if (mIoSessionManager == null) {
                    mIoSessionManager = new IoSessionManager();
                }
            }
        }
        return mIoSessionManager;
    }

    public synchronized void addIoSession(IoSession ioSession) {
        if (this.mSessionList != null) {
            this.mSessionList.put(Long.valueOf(ioSession.getId()), ioSession);
        }
    }

    public synchronized void removeIoSession(IoSession ioSession) {
        if (this.mSessionList != null && this.mSessionList.size() > 0) {
            this.mSessionList.remove(Long.valueOf(ioSession.getId()));
        }
    }

    public synchronized void send(long j, String str) {
        String str2 = str + "\r\n";
        if (this.mSessionList != null && this.mSessionList.size() > 0) {
            this.mSessionList.get(Long.valueOf(j)).write(str2);
        }
    }

    public synchronized void sendAll(String str) {
        String str2 = str + "\r\n";
        if (this.mSessionList != null && this.mSessionList.size() > 0) {
            for (IoSession write : this.mSessionList.values()) {
                write.write(str2);
            }
        }
    }

    public synchronized void sendAll(byte[] bArr) {
        if (this.mSessionList != null && this.mSessionList.size() > 0) {
            for (IoSession write : this.mSessionList.values()) {
                write.write(bArr);
            }
        }
    }
}
