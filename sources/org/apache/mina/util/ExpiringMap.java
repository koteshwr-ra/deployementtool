package org.apache.mina.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ExpiringMap<K, V> implements Map<K, V> {
    public static final int DEFAULT_EXPIRATION_INTERVAL = 1;
    public static final int DEFAULT_TIME_TO_LIVE = 60;
    private static volatile int expirerCount = 1;
    /* access modifiers changed from: private */
    public final ConcurrentHashMap<K, ExpiringMap<K, V>.ExpiringObject> delegate;
    /* access modifiers changed from: private */
    public final CopyOnWriteArrayList<ExpirationListener<V>> expirationListeners;
    private final ExpiringMap<K, V>.Expirer expirer;

    static /* synthetic */ int access$008() {
        int i = expirerCount;
        expirerCount = i + 1;
        return i;
    }

    public ExpiringMap() {
        this(60, 1);
    }

    public ExpiringMap(int i) {
        this(i, 1);
    }

    public ExpiringMap(int i, int i2) {
        this(new ConcurrentHashMap(), new CopyOnWriteArrayList(), i, i2);
    }

    private ExpiringMap(ConcurrentHashMap<K, ExpiringMap<K, V>.ExpiringObject> concurrentHashMap, CopyOnWriteArrayList<ExpirationListener<V>> copyOnWriteArrayList, int i, int i2) {
        this.delegate = concurrentHashMap;
        this.expirationListeners = copyOnWriteArrayList;
        ExpiringMap<K, V>.Expirer expirer2 = new Expirer();
        this.expirer = expirer2;
        expirer2.setTimeToLive((long) i);
        this.expirer.setExpirationInterval((long) i2);
    }

    public V put(K k, V v) {
        ExpiringObject put = this.delegate.put(k, new ExpiringObject(k, v, System.currentTimeMillis()));
        if (put == null) {
            return null;
        }
        return put.getValue();
    }

    public V get(Object obj) {
        ExpiringObject expiringObject = this.delegate.get(obj);
        if (expiringObject == null) {
            return null;
        }
        expiringObject.setLastAccessTime(System.currentTimeMillis());
        return expiringObject.getValue();
    }

    public V remove(Object obj) {
        ExpiringObject remove = this.delegate.remove(obj);
        if (remove == null) {
            return null;
        }
        return remove.getValue();
    }

    public boolean containsKey(Object obj) {
        return this.delegate.containsKey(obj);
    }

    public boolean containsValue(Object obj) {
        return this.delegate.containsValue(obj);
    }

    public int size() {
        return this.delegate.size();
    }

    public boolean isEmpty() {
        return this.delegate.isEmpty();
    }

    public void clear() {
        this.delegate.clear();
    }

    public int hashCode() {
        return this.delegate.hashCode();
    }

    public Set<K> keySet() {
        return this.delegate.keySet();
    }

    public boolean equals(Object obj) {
        return this.delegate.equals(obj);
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry next : map.entrySet()) {
            put(next.getKey(), next.getValue());
        }
    }

    public Collection<V> values() {
        throw new UnsupportedOperationException();
    }

    public Set<Map.Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException();
    }

    public void addExpirationListener(ExpirationListener<V> expirationListener) {
        this.expirationListeners.add(expirationListener);
    }

    public void removeExpirationListener(ExpirationListener<V> expirationListener) {
        this.expirationListeners.remove(expirationListener);
    }

    public ExpiringMap<K, V>.Expirer getExpirer() {
        return this.expirer;
    }

    public int getExpirationInterval() {
        return this.expirer.getExpirationInterval();
    }

    public int getTimeToLive() {
        return this.expirer.getTimeToLive();
    }

    public void setExpirationInterval(int i) {
        this.expirer.setExpirationInterval((long) i);
    }

    public void setTimeToLive(int i) {
        this.expirer.setTimeToLive((long) i);
    }

    private class ExpiringObject {
        private K key;
        private long lastAccessTime;
        private final ReadWriteLock lastAccessTimeLock = new ReentrantReadWriteLock();
        private V value;

        ExpiringObject(K k, V v, long j) {
            if (v != null) {
                this.key = k;
                this.value = v;
                this.lastAccessTime = j;
                return;
            }
            throw new IllegalArgumentException("An expiring object cannot be null.");
        }

        public long getLastAccessTime() {
            this.lastAccessTimeLock.readLock().lock();
            try {
                return this.lastAccessTime;
            } finally {
                this.lastAccessTimeLock.readLock().unlock();
            }
        }

        public void setLastAccessTime(long j) {
            this.lastAccessTimeLock.writeLock().lock();
            try {
                this.lastAccessTime = j;
            } finally {
                this.lastAccessTimeLock.writeLock().unlock();
            }
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        public boolean equals(Object obj) {
            return this.value.equals(obj);
        }

        public int hashCode() {
            return this.value.hashCode();
        }
    }

    public class Expirer implements Runnable {
        private long expirationIntervalMillis;
        private final Thread expirerThread;
        private boolean running = false;
        private final ReadWriteLock stateLock = new ReentrantReadWriteLock();
        private long timeToLiveMillis;

        public Expirer() {
            Thread thread = new Thread(this, "ExpiringMapExpirer-" + ExpiringMap.access$008());
            this.expirerThread = thread;
            thread.setDaemon(true);
        }

        public void run() {
            while (this.running) {
                processExpires();
                try {
                    Thread.sleep(this.expirationIntervalMillis);
                } catch (InterruptedException unused) {
                }
            }
        }

        private void processExpires() {
            long currentTimeMillis = System.currentTimeMillis();
            for (ExpiringObject expiringObject : ExpiringMap.this.delegate.values()) {
                if (this.timeToLiveMillis > 0 && currentTimeMillis - expiringObject.getLastAccessTime() >= this.timeToLiveMillis) {
                    ExpiringMap.this.delegate.remove(expiringObject.getKey());
                    Iterator it = ExpiringMap.this.expirationListeners.iterator();
                    while (it.hasNext()) {
                        ((ExpirationListener) it.next()).expired(expiringObject.getValue());
                    }
                }
            }
        }

        public void startExpiring() {
            this.stateLock.writeLock().lock();
            try {
                if (!this.running) {
                    this.running = true;
                    this.expirerThread.start();
                }
            } finally {
                this.stateLock.writeLock().unlock();
            }
        }

        public void startExpiringIfNotStarted() {
            this.stateLock.readLock().lock();
            try {
                if (!this.running) {
                    this.stateLock.readLock().unlock();
                    this.stateLock.writeLock().lock();
                    try {
                        if (!this.running) {
                            this.running = true;
                            this.expirerThread.start();
                        }
                    } finally {
                        this.stateLock.writeLock().unlock();
                    }
                }
            } finally {
                this.stateLock.readLock().unlock();
            }
        }

        public void stopExpiring() {
            this.stateLock.writeLock().lock();
            try {
                if (this.running) {
                    this.running = false;
                    this.expirerThread.interrupt();
                }
            } finally {
                this.stateLock.writeLock().unlock();
            }
        }

        public boolean isRunning() {
            this.stateLock.readLock().lock();
            try {
                return this.running;
            } finally {
                this.stateLock.readLock().unlock();
            }
        }

        public int getTimeToLive() {
            this.stateLock.readLock().lock();
            try {
                return ((int) this.timeToLiveMillis) / 1000;
            } finally {
                this.stateLock.readLock().unlock();
            }
        }

        public void setTimeToLive(long j) {
            this.stateLock.writeLock().lock();
            try {
                this.timeToLiveMillis = j * 1000;
            } finally {
                this.stateLock.writeLock().unlock();
            }
        }

        public int getExpirationInterval() {
            this.stateLock.readLock().lock();
            try {
                return ((int) this.expirationIntervalMillis) / 1000;
            } finally {
                this.stateLock.readLock().unlock();
            }
        }

        public void setExpirationInterval(long j) {
            this.stateLock.writeLock().lock();
            try {
                this.expirationIntervalMillis = j * 1000;
            } finally {
                this.stateLock.writeLock().unlock();
            }
        }
    }
}
