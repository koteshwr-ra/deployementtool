package org.apache.mina.core.session;

import java.net.SocketAddress;
import java.util.ArrayList;
import org.apache.mina.util.ExpirationListener;
import org.apache.mina.util.ExpiringMap;

public class ExpiringSessionRecycler implements IoSessionRecycler {
    private ExpiringMap<Object, IoSession>.Expirer mapExpirer;
    private ExpiringMap<Object, IoSession> sessionMap;

    public ExpiringSessionRecycler() {
        this(60);
    }

    public ExpiringSessionRecycler(int i) {
        this(i, 1);
    }

    public ExpiringSessionRecycler(int i, int i2) {
        ExpiringMap<Object, IoSession> expiringMap = new ExpiringMap<>(i, i2);
        this.sessionMap = expiringMap;
        this.mapExpirer = expiringMap.getExpirer();
        this.sessionMap.addExpirationListener(new DefaultExpirationListener());
    }

    public void put(IoSession ioSession) {
        this.mapExpirer.startExpiringIfNotStarted();
        Object generateKey = generateKey(ioSession);
        if (!this.sessionMap.containsKey(generateKey)) {
            this.sessionMap.put(generateKey, ioSession);
        }
    }

    public IoSession recycle(SocketAddress socketAddress, SocketAddress socketAddress2) {
        return this.sessionMap.get(generateKey(socketAddress, socketAddress2));
    }

    public void remove(IoSession ioSession) {
        this.sessionMap.remove(generateKey(ioSession));
    }

    public void stopExpiring() {
        this.mapExpirer.stopExpiring();
    }

    public int getExpirationInterval() {
        return this.sessionMap.getExpirationInterval();
    }

    public int getTimeToLive() {
        return this.sessionMap.getTimeToLive();
    }

    public void setExpirationInterval(int i) {
        this.sessionMap.setExpirationInterval(i);
    }

    public void setTimeToLive(int i) {
        this.sessionMap.setTimeToLive(i);
    }

    private Object generateKey(IoSession ioSession) {
        return generateKey(ioSession.getLocalAddress(), ioSession.getRemoteAddress());
    }

    private Object generateKey(SocketAddress socketAddress, SocketAddress socketAddress2) {
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(socketAddress2);
        arrayList.add(socketAddress);
        return arrayList;
    }

    private class DefaultExpirationListener implements ExpirationListener<IoSession> {
        private DefaultExpirationListener() {
        }

        public void expired(IoSession ioSession) {
            ioSession.close(true);
        }
    }
}
