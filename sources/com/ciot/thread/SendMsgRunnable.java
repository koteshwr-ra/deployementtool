package com.ciot.thread;

import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.apache.mina.core.session.IoSession;

public class SendMsgRunnable implements Runnable {
    private static final String TAG = SendMsgRunnable.class.getSimpleName();
    private WeakReference<IoSession> mIoSessionWeakReference;
    private final Object mLock = new Object();
    private ConcurrentLinkedQueue<Object> mMsgQueue = new ConcurrentLinkedQueue<>();
    private boolean mSendMsg = false;

    public void takeMsg(Object obj) {
        this.mMsgQueue.add(obj);
        notifyLock();
    }

    public boolean isAlive() {
        return this.mSendMsg;
    }

    public void setSession(IoSession ioSession) {
        if (ioSession == null) {
            this.mSendMsg = false;
            WeakReference<IoSession> weakReference = this.mIoSessionWeakReference;
            if (weakReference != null) {
                weakReference.clear();
            }
            this.mMsgQueue.clear();
            notifyLock();
            return;
        }
        this.mIoSessionWeakReference = new WeakReference<>(ioSession);
    }

    private void notifyLock() {
        synchronized (this.mLock) {
            this.mLock.notifyAll();
        }
    }

    private void lock() {
        synchronized (this.mLock) {
            try {
                this.mLock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void run() {
        this.mSendMsg = true;
        while (this.mSendMsg) {
            if (this.mIoSessionWeakReference == null || this.mMsgQueue.size() <= 0) {
                lock();
            } else {
                IoSession ioSession = (IoSession) this.mIoSessionWeakReference.get();
                if (ioSession != null) {
                    ioSession.write(this.mMsgQueue.poll());
                }
            }
        }
    }
}
