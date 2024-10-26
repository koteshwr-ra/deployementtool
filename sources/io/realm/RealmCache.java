package io.realm;

import android.os.SystemClock;
import io.realm.BaseRealm;
import io.realm.internal.ObjectServerFacade;
import io.realm.internal.OsObjectStore;
import io.realm.internal.OsSharedRealm;
import io.realm.internal.RealmNotifier;
import io.realm.internal.Util;
import io.realm.internal.android.AndroidCapabilities;
import io.realm.internal.android.AndroidRealmNotifier;
import io.realm.internal.async.RealmAsyncTaskImpl;
import io.realm.internal.util.Pair;
import io.realm.log.RealmLog;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

final class RealmCache {
    private static final String ASYNC_CALLBACK_NULL_MSG = "The callback cannot be null.";
    private static final String ASYNC_NOT_ALLOWED_MSG = "Realm instances cannot be loaded asynchronously on a non-looper thread.";
    private static final String DIFFERENT_KEY_MESSAGE = "Wrong key used to decrypt Realm.";
    private static final String WRONG_REALM_CLASS_MESSAGE = "The type of Realm class must be Realm or DynamicRealm.";
    private static final List<WeakReference<RealmCache>> cachesList = new ArrayList();
    private static final Collection<RealmCache> leakedCaches = new ConcurrentLinkedQueue();
    private RealmConfiguration configuration;
    private final AtomicBoolean isLeaked = new AtomicBoolean(false);
    private final String realmPath;
    private final Map<Pair<RealmCacheType, OsSharedRealm.VersionID>, ReferenceCounter> refAndCountMap = new HashMap();

    interface Callback {
        void onResult(int i);
    }

    interface Callback0 {
        void onCall();
    }

    private static abstract class ReferenceCounter {
        protected AtomicInteger globalCount;
        protected final ThreadLocal<Integer> localCount;

        /* access modifiers changed from: package-private */
        public abstract void clearThreadLocalCache();

        /* access modifiers changed from: package-private */
        public abstract BaseRealm getRealmInstance();

        /* access modifiers changed from: package-private */
        public abstract int getThreadLocalCount();

        /* access modifiers changed from: package-private */
        public abstract boolean hasInstanceAvailableForThread();

        /* access modifiers changed from: package-private */
        public abstract void onRealmCreated(BaseRealm baseRealm);

        private ReferenceCounter() {
            this.localCount = new ThreadLocal<>();
            this.globalCount = new AtomicInteger(0);
        }

        public void incrementThreadCount(int i) {
            Integer num = this.localCount.get();
            ThreadLocal<Integer> threadLocal = this.localCount;
            if (num != null) {
                i += num.intValue();
            }
            threadLocal.set(Integer.valueOf(i));
        }

        public void setThreadCount(int i) {
            this.localCount.set(Integer.valueOf(i));
        }

        public int getGlobalCount() {
            return this.globalCount.get();
        }
    }

    private static class GlobalReferenceCounter extends ReferenceCounter {
        private BaseRealm cachedRealm;

        private GlobalReferenceCounter() {
            super();
        }

        /* access modifiers changed from: package-private */
        public boolean hasInstanceAvailableForThread() {
            return this.cachedRealm != null;
        }

        /* access modifiers changed from: package-private */
        public BaseRealm getRealmInstance() {
            return this.cachedRealm;
        }

        /* access modifiers changed from: package-private */
        public void onRealmCreated(BaseRealm baseRealm) {
            this.cachedRealm = baseRealm;
            this.localCount.set(0);
            this.globalCount.incrementAndGet();
        }

        public void clearThreadLocalCache() {
            String path = this.cachedRealm.getPath();
            this.localCount.set((Object) null);
            this.cachedRealm = null;
            if (this.globalCount.decrementAndGet() < 0) {
                throw new IllegalStateException("Global reference counter of Realm" + path + " not be negative.");
            }
        }

        /* access modifiers changed from: package-private */
        public int getThreadLocalCount() {
            return this.globalCount.get();
        }
    }

    private static class ThreadConfinedReferenceCounter extends ReferenceCounter {
        private final ThreadLocal<BaseRealm> localRealm;

        private ThreadConfinedReferenceCounter() {
            super();
            this.localRealm = new ThreadLocal<>();
        }

        public boolean hasInstanceAvailableForThread() {
            return this.localRealm.get() != null;
        }

        public BaseRealm getRealmInstance() {
            return this.localRealm.get();
        }

        public void onRealmCreated(BaseRealm baseRealm) {
            this.localRealm.set(baseRealm);
            this.localCount.set(0);
            this.globalCount.incrementAndGet();
        }

        public void clearThreadLocalCache() {
            String path = this.localRealm.get().getPath();
            this.localCount.set((Object) null);
            this.localRealm.set((Object) null);
            if (this.globalCount.decrementAndGet() < 0) {
                throw new IllegalStateException("Global reference counter of Realm" + path + " can not be negative.");
            }
        }

        public int getThreadLocalCount() {
            Integer num = (Integer) this.localCount.get();
            if (num != null) {
                return num.intValue();
            }
            return 0;
        }
    }

    private enum RealmCacheType {
        TYPED_REALM,
        DYNAMIC_REALM;

        static RealmCacheType valueOf(Class<? extends BaseRealm> cls) {
            if (cls == Realm.class) {
                return TYPED_REALM;
            }
            if (cls == DynamicRealm.class) {
                return DYNAMIC_REALM;
            }
            throw new IllegalArgumentException(RealmCache.WRONG_REALM_CLASS_MESSAGE);
        }
    }

    private static class CreateRealmRunnable<T extends BaseRealm> implements Runnable {
        /* access modifiers changed from: private */
        public final BaseRealm.InstanceCallback<T> callback;
        /* access modifiers changed from: private */
        public final CountDownLatch canReleaseBackgroundInstanceLatch = new CountDownLatch(1);
        /* access modifiers changed from: private */
        public final RealmConfiguration configuration;
        /* access modifiers changed from: private */
        public Future future;
        private final RealmNotifier notifier;
        /* access modifiers changed from: private */
        public final Class<T> realmClass;

        CreateRealmRunnable(RealmNotifier realmNotifier, RealmConfiguration realmConfiguration, BaseRealm.InstanceCallback<T> instanceCallback, Class<T> cls) {
            this.configuration = realmConfiguration;
            this.realmClass = cls;
            this.callback = instanceCallback;
            this.notifier = realmNotifier;
        }

        public void setFuture(Future future2) {
            this.future = future2;
        }

        public void run() {
            BaseRealm baseRealm = null;
            try {
                baseRealm = RealmCache.createRealmOrGetFromCache(this.configuration, this.realmClass);
                if (!this.notifier.post(new Runnable() {
                    public void run() {
                        if (CreateRealmRunnable.this.future == null || CreateRealmRunnable.this.future.isCancelled()) {
                            CreateRealmRunnable.this.canReleaseBackgroundInstanceLatch.countDown();
                            return;
                        }
                        BaseRealm baseRealm = null;
                        try {
                            BaseRealm createRealmOrGetFromCache = RealmCache.createRealmOrGetFromCache(CreateRealmRunnable.this.configuration, CreateRealmRunnable.this.realmClass);
                            CreateRealmRunnable.this.canReleaseBackgroundInstanceLatch.countDown();
                            BaseRealm baseRealm2 = createRealmOrGetFromCache;
                            th = null;
                            baseRealm = baseRealm2;
                        } catch (Throwable th) {
                            th = th;
                            CreateRealmRunnable.this.canReleaseBackgroundInstanceLatch.countDown();
                        }
                        if (baseRealm != null) {
                            CreateRealmRunnable.this.callback.onSuccess(baseRealm);
                        } else {
                            CreateRealmRunnable.this.callback.onError(th);
                        }
                    }
                })) {
                    this.canReleaseBackgroundInstanceLatch.countDown();
                }
                if (!this.canReleaseBackgroundInstanceLatch.await(2, TimeUnit.SECONDS)) {
                    RealmLog.warn("Timeout for creating Realm instance in foreground thread in `CreateRealmRunnable` ", new Object[0]);
                }
                if (baseRealm == null) {
                    return;
                }
            } catch (InterruptedException e) {
                RealmLog.warn(e, "`CreateRealmRunnable` has been interrupted.", new Object[0]);
                if (baseRealm == null) {
                    return;
                }
            } catch (Throwable th) {
                if (baseRealm != null) {
                    baseRealm.close();
                }
                throw th;
            }
            baseRealm.close();
        }
    }

    private RealmCache(String str) {
        this.realmPath = str;
    }

    private static RealmCache getCache(String str, boolean z) {
        RealmCache realmCache;
        synchronized (cachesList) {
            Iterator<WeakReference<RealmCache>> it = cachesList.iterator();
            realmCache = null;
            while (it.hasNext()) {
                RealmCache realmCache2 = (RealmCache) it.next().get();
                if (realmCache2 == null) {
                    it.remove();
                } else if (realmCache2.realmPath.equals(str)) {
                    realmCache = realmCache2;
                }
            }
            if (realmCache == null && z) {
                realmCache = new RealmCache(str);
                cachesList.add(new WeakReference(realmCache));
            }
        }
        return realmCache;
    }

    static <T extends BaseRealm> RealmAsyncTask createRealmOrGetFromCacheAsync(RealmConfiguration realmConfiguration, BaseRealm.InstanceCallback<T> instanceCallback, Class<T> cls) {
        return getCache(realmConfiguration.getPath(), true).doCreateRealmOrGetFromCacheAsync(realmConfiguration, instanceCallback, cls);
    }

    private synchronized <T extends BaseRealm> RealmAsyncTask doCreateRealmOrGetFromCacheAsync(RealmConfiguration realmConfiguration, BaseRealm.InstanceCallback<T> instanceCallback, Class<T> cls) {
        Future<?> submitTransaction;
        AndroidCapabilities androidCapabilities = new AndroidCapabilities();
        androidCapabilities.checkCanDeliverNotification(ASYNC_NOT_ALLOWED_MSG);
        if (instanceCallback != null) {
            CreateRealmRunnable createRealmRunnable = new CreateRealmRunnable(new AndroidRealmNotifier((OsSharedRealm) null, androidCapabilities), realmConfiguration, instanceCallback, cls);
            submitTransaction = BaseRealm.asyncTaskExecutor.submitTransaction(createRealmRunnable);
            createRealmRunnable.setFuture(submitTransaction);
            ObjectServerFacade.getSyncFacadeIfPossible().createNativeSyncSession(realmConfiguration);
        } else {
            throw new IllegalArgumentException(ASYNC_CALLBACK_NULL_MSG);
        }
        return new RealmAsyncTaskImpl(submitTransaction, BaseRealm.asyncTaskExecutor);
    }

    static <E extends BaseRealm> E createRealmOrGetFromCache(RealmConfiguration realmConfiguration, Class<E> cls) {
        return getCache(realmConfiguration.getPath(), true).doCreateRealmOrGetFromCache(realmConfiguration, cls, OsSharedRealm.VersionID.LIVE);
    }

    static <E extends BaseRealm> E createRealmOrGetFromCache(RealmConfiguration realmConfiguration, Class<E> cls, OsSharedRealm.VersionID versionID) {
        return getCache(realmConfiguration.getPath(), true).doCreateRealmOrGetFromCache(realmConfiguration, cls, versionID);
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x0068  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized <E extends io.realm.BaseRealm> E doCreateRealmOrGetFromCache(io.realm.RealmConfiguration r7, java.lang.Class<E> r8, io.realm.internal.OsSharedRealm.VersionID r9) {
        /*
            r6 = this;
            monitor-enter(r6)
            io.realm.RealmCache$ReferenceCounter r0 = r6.getRefCounter(r8, r9)     // Catch:{ all -> 0x0081 }
            int r1 = r6.getTotalGlobalRefCount()     // Catch:{ all -> 0x0081 }
            r2 = 1
            if (r1 != 0) goto L_0x000e
            r1 = 1
            goto L_0x000f
        L_0x000e:
            r1 = 0
        L_0x000f:
            boolean r3 = r7.realmExists()     // Catch:{ all -> 0x0081 }
            r3 = r3 ^ r2
            if (r1 == 0) goto L_0x006c
            copyAssetFileIfNeeded(r7)     // Catch:{ all -> 0x0081 }
            r1 = 0
            boolean r4 = r7.isSyncConfiguration()     // Catch:{ all -> 0x0065 }
            if (r4 == 0) goto L_0x005d
            if (r3 == 0) goto L_0x005d
            io.realm.internal.OsRealmConfig$Builder r4 = new io.realm.internal.OsRealmConfig$Builder     // Catch:{ all -> 0x0065 }
            r4.<init>(r7)     // Catch:{ all -> 0x0065 }
            io.realm.internal.OsRealmConfig r4 = r4.build()     // Catch:{ all -> 0x0065 }
            io.realm.internal.ObjectServerFacade r5 = io.realm.internal.ObjectServerFacade.getSyncFacadeIfPossible()     // Catch:{ all -> 0x0065 }
            r5.wrapObjectStoreSessionIfRequired(r4)     // Catch:{ all -> 0x0065 }
            io.realm.internal.ObjectServerFacade r4 = io.realm.internal.ObjectServerFacade.getSyncFacadeIfPossible()     // Catch:{ all -> 0x0065 }
            boolean r4 = r4.isPartialRealm(r7)     // Catch:{ all -> 0x0065 }
            if (r4 == 0) goto L_0x0056
            io.realm.internal.OsSharedRealm$VersionID r4 = io.realm.internal.OsSharedRealm.VersionID.LIVE     // Catch:{ all -> 0x0065 }
            io.realm.internal.OsSharedRealm r4 = io.realm.internal.OsSharedRealm.getInstance((io.realm.RealmConfiguration) r7, (io.realm.internal.OsSharedRealm.VersionID) r4)     // Catch:{ all -> 0x0065 }
            io.realm.internal.ObjectServerFacade r5 = io.realm.internal.ObjectServerFacade.getSyncFacadeIfPossible()     // Catch:{ all -> 0x004b }
            r5.downloadInitialRemoteChanges(r7)     // Catch:{ all -> 0x004b }
            r1 = r4
            goto L_0x005d
        L_0x004b:
            r8 = move-exception
            r4.close()     // Catch:{ all -> 0x0053 }
            deleteRealmFileOnDisk(r7)     // Catch:{ all -> 0x0065 }
            throw r8     // Catch:{ all -> 0x0065 }
        L_0x0053:
            r7 = move-exception
            r1 = r4
            goto L_0x0066
        L_0x0056:
            io.realm.internal.ObjectServerFacade r4 = io.realm.internal.ObjectServerFacade.getSyncFacadeIfPossible()     // Catch:{ all -> 0x0065 }
            r4.downloadInitialRemoteChanges(r7)     // Catch:{ all -> 0x0065 }
        L_0x005d:
            if (r1 == 0) goto L_0x0062
            r1.close()     // Catch:{ all -> 0x0081 }
        L_0x0062:
            r6.configuration = r7     // Catch:{ all -> 0x0081 }
            goto L_0x006f
        L_0x0065:
            r7 = move-exception
        L_0x0066:
            if (r1 == 0) goto L_0x006b
            r1.close()     // Catch:{ all -> 0x0081 }
        L_0x006b:
            throw r7     // Catch:{ all -> 0x0081 }
        L_0x006c:
            r6.validateConfiguration(r7)     // Catch:{ all -> 0x0081 }
        L_0x006f:
            boolean r7 = r0.hasInstanceAvailableForThread()     // Catch:{ all -> 0x0081 }
            if (r7 != 0) goto L_0x0078
            r6.createInstance(r8, r0, r3, r9)     // Catch:{ all -> 0x0081 }
        L_0x0078:
            r0.incrementThreadCount(r2)     // Catch:{ all -> 0x0081 }
            io.realm.BaseRealm r7 = r0.getRealmInstance()     // Catch:{ all -> 0x0081 }
            monitor-exit(r6)
            return r7
        L_0x0081:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.RealmCache.doCreateRealmOrGetFromCache(io.realm.RealmConfiguration, java.lang.Class, io.realm.internal.OsSharedRealm$VersionID):io.realm.BaseRealm");
    }

    private <E extends BaseRealm> ReferenceCounter getRefCounter(Class<E> cls, OsSharedRealm.VersionID versionID) {
        Pair pair = new Pair(RealmCacheType.valueOf((Class<? extends BaseRealm>) cls), versionID);
        ReferenceCounter referenceCounter = this.refAndCountMap.get(pair);
        if (referenceCounter == null) {
            if (versionID.equals(OsSharedRealm.VersionID.LIVE)) {
                referenceCounter = new ThreadConfinedReferenceCounter();
            } else {
                referenceCounter = new GlobalReferenceCounter();
            }
            this.refAndCountMap.put(pair, referenceCounter);
        }
        return referenceCounter;
    }

    private <E extends BaseRealm> void createInstance(Class<E> cls, ReferenceCounter referenceCounter, boolean z, OsSharedRealm.VersionID versionID) {
        BaseRealm baseRealm;
        if (cls == Realm.class) {
            baseRealm = Realm.createInstance(this, versionID);
            synchronizeInitialSubscriptionsIfNeeded((Realm) baseRealm, z);
        } else if (cls == DynamicRealm.class) {
            baseRealm = DynamicRealm.createInstance(this, versionID);
        } else {
            throw new IllegalArgumentException(WRONG_REALM_CLASS_MESSAGE);
        }
        referenceCounter.onRealmCreated(baseRealm);
    }

    private static void synchronizeInitialSubscriptionsIfNeeded(Realm realm, boolean z) {
        if (z) {
            try {
                ObjectServerFacade.getSyncFacadeIfPossible().downloadInitialSubscriptions(realm);
            } catch (Throwable unused) {
                realm.close();
                deleteRealmFileOnDisk(realm.getConfiguration());
            }
        }
    }

    private static void deleteRealmFileOnDisk(RealmConfiguration realmConfiguration) {
        int i = 5;
        boolean z = false;
        while (i > 0 && !z) {
            try {
                z = BaseRealm.deleteRealm(realmConfiguration);
            } catch (IllegalStateException unused) {
                i--;
                RealmLog.warn("Sync server still holds a reference to the Realm. It cannot be deleted. Retrying " + i + " more times", new Object[0]);
                if (i > 0) {
                    SystemClock.sleep(15);
                }
            }
        }
        if (!z) {
            RealmLog.error("Failed to delete the underlying Realm file: " + realmConfiguration.getPath(), new Object[0]);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x008b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void release(io.realm.BaseRealm r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            java.lang.String r0 = r5.getPath()     // Catch:{ all -> 0x008c }
            java.lang.Class r1 = r5.getClass()     // Catch:{ all -> 0x008c }
            boolean r2 = r5.isFrozen()     // Catch:{ all -> 0x008c }
            if (r2 == 0) goto L_0x0016
            io.realm.internal.OsSharedRealm r2 = r5.sharedRealm     // Catch:{ all -> 0x008c }
            io.realm.internal.OsSharedRealm$VersionID r2 = r2.getVersionID()     // Catch:{ all -> 0x008c }
            goto L_0x0018
        L_0x0016:
            io.realm.internal.OsSharedRealm$VersionID r2 = io.realm.internal.OsSharedRealm.VersionID.LIVE     // Catch:{ all -> 0x008c }
        L_0x0018:
            io.realm.RealmCache$ReferenceCounter r1 = r4.getRefCounter(r1, r2)     // Catch:{ all -> 0x008c }
            int r2 = r1.getThreadLocalCount()     // Catch:{ all -> 0x008c }
            if (r2 > 0) goto L_0x0036
            java.lang.String r5 = "%s has been closed already. refCount is %s"
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x008c }
            r3 = 0
            r1[r3] = r0     // Catch:{ all -> 0x008c }
            r0 = 1
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x008c }
            r1[r0] = r2     // Catch:{ all -> 0x008c }
            io.realm.log.RealmLog.warn(r5, r1)     // Catch:{ all -> 0x008c }
            monitor-exit(r4)
            return
        L_0x0036:
            int r2 = r2 + -1
            if (r2 != 0) goto L_0x0087
            r1.clearThreadLocalCache()     // Catch:{ all -> 0x008c }
            r5.doClose()     // Catch:{ all -> 0x008c }
            int r0 = r4.getTotalLiveRealmGlobalRefCount()     // Catch:{ all -> 0x008c }
            if (r0 != 0) goto L_0x008a
            r0 = 0
            r4.configuration = r0     // Catch:{ all -> 0x008c }
            java.util.Map<io.realm.internal.util.Pair<io.realm.RealmCache$RealmCacheType, io.realm.internal.OsSharedRealm$VersionID>, io.realm.RealmCache$ReferenceCounter> r0 = r4.refAndCountMap     // Catch:{ all -> 0x008c }
            java.util.Collection r0 = r0.values()     // Catch:{ all -> 0x008c }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x008c }
        L_0x0053:
            boolean r1 = r0.hasNext()     // Catch:{ all -> 0x008c }
            if (r1 == 0) goto L_0x0073
            java.lang.Object r1 = r0.next()     // Catch:{ all -> 0x008c }
            io.realm.RealmCache$ReferenceCounter r1 = (io.realm.RealmCache.ReferenceCounter) r1     // Catch:{ all -> 0x008c }
            boolean r2 = r1 instanceof io.realm.RealmCache.GlobalReferenceCounter     // Catch:{ all -> 0x008c }
            if (r2 == 0) goto L_0x0053
            io.realm.BaseRealm r1 = r1.getRealmInstance()     // Catch:{ all -> 0x008c }
            if (r1 == 0) goto L_0x0053
        L_0x0069:
            boolean r2 = r1.isClosed()     // Catch:{ all -> 0x008c }
            if (r2 != 0) goto L_0x0053
            r1.close()     // Catch:{ all -> 0x008c }
            goto L_0x0069
        L_0x0073:
            io.realm.RealmConfiguration r0 = r5.getConfiguration()     // Catch:{ all -> 0x008c }
            boolean r0 = r0.isSyncConfiguration()     // Catch:{ all -> 0x008c }
            io.realm.internal.ObjectServerFacade r0 = io.realm.internal.ObjectServerFacade.getFacade(r0)     // Catch:{ all -> 0x008c }
            io.realm.RealmConfiguration r5 = r5.getConfiguration()     // Catch:{ all -> 0x008c }
            r0.realmClosed(r5)     // Catch:{ all -> 0x008c }
            goto L_0x008a
        L_0x0087:
            r1.setThreadCount(r2)     // Catch:{ all -> 0x008c }
        L_0x008a:
            monitor-exit(r4)
            return
        L_0x008c:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.RealmCache.release(io.realm.BaseRealm):void");
    }

    private void validateConfiguration(RealmConfiguration realmConfiguration) {
        if (!this.configuration.equals(realmConfiguration)) {
            if (Arrays.equals(this.configuration.getEncryptionKey(), realmConfiguration.getEncryptionKey())) {
                RealmMigration migration = realmConfiguration.getMigration();
                RealmMigration migration2 = this.configuration.getMigration();
                if (migration2 == null || migration == null || !migration2.getClass().equals(migration.getClass()) || migration.equals(migration2)) {
                    throw new IllegalArgumentException("Configurations cannot be different if used to open the same file. \nCached configuration: \n" + this.configuration + "\n\nNew configuration: \n" + realmConfiguration);
                }
                throw new IllegalArgumentException("Configurations cannot be different if used to open the same file. The most likely cause is that equals() and hashCode() are not overridden in the migration class: " + realmConfiguration.getMigration().getClass().getCanonicalName());
            }
            throw new IllegalArgumentException(DIFFERENT_KEY_MESSAGE);
        }
    }

    static void invokeWithGlobalRefCount(RealmConfiguration realmConfiguration, Callback callback) {
        synchronized (cachesList) {
            RealmCache cache = getCache(realmConfiguration.getPath(), false);
            if (cache == null) {
                callback.onResult(0);
            } else {
                cache.doInvokeWithGlobalRefCount(callback);
            }
        }
    }

    private synchronized void doInvokeWithGlobalRefCount(Callback callback) {
        callback.onResult(getTotalGlobalRefCount());
    }

    /* access modifiers changed from: package-private */
    public synchronized void invokeWithLock(Callback0 callback0) {
        callback0.onCall();
    }

    private static void copyAssetFileIfNeeded(final RealmConfiguration realmConfiguration) {
        final File file = realmConfiguration.hasAssetFile() ? new File(realmConfiguration.getRealmDirectory(), realmConfiguration.getRealmFileName()) : null;
        final String syncServerCertificateAssetName = ObjectServerFacade.getFacade(realmConfiguration.isSyncConfiguration()).getSyncServerCertificateAssetName(realmConfiguration);
        final boolean z = !Util.isEmptyString(syncServerCertificateAssetName);
        if (file != null || z) {
            OsObjectStore.callWithLock(realmConfiguration, new Runnable() {
                public void run() {
                    if (file != null) {
                        RealmCache.copyFileIfNeeded(realmConfiguration.getAssetFilePath(), file);
                    }
                    if (z) {
                        RealmCache.copyFileIfNeeded(syncServerCertificateAssetName, new File(ObjectServerFacade.getFacade(realmConfiguration.isSyncConfiguration()).getSyncServerCertificateFilePath(realmConfiguration)));
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0087 A[SYNTHETIC, Splitter:B:45:0x0087] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x008e A[SYNTHETIC, Splitter:B:49:0x008e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void copyFileIfNeeded(java.lang.String r7, java.io.File r8) {
        /*
            boolean r0 = r8.exists()
            if (r0 == 0) goto L_0x0007
            return
        L_0x0007:
            r0 = 0
            android.content.Context r1 = io.realm.BaseRealm.applicationContext     // Catch:{ IOException -> 0x0068, all -> 0x0064 }
            android.content.res.AssetManager r1 = r1.getAssets()     // Catch:{ IOException -> 0x0068, all -> 0x0064 }
            java.io.InputStream r1 = r1.open(r7)     // Catch:{ IOException -> 0x0068, all -> 0x0064 }
            if (r1 == 0) goto L_0x004b
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0048, all -> 0x0045 }
            r2.<init>(r8)     // Catch:{ IOException -> 0x0048, all -> 0x0045 }
            r8 = 4096(0x1000, float:5.74E-42)
            byte[] r8 = new byte[r8]     // Catch:{ IOException -> 0x0043 }
        L_0x001d:
            int r3 = r1.read(r8)     // Catch:{ IOException -> 0x0043 }
            r4 = -1
            if (r3 <= r4) goto L_0x0029
            r4 = 0
            r2.write(r8, r4, r3)     // Catch:{ IOException -> 0x0043 }
            goto L_0x001d
        L_0x0029:
            if (r1 == 0) goto L_0x0030
            r1.close()     // Catch:{ IOException -> 0x002f }
            goto L_0x0030
        L_0x002f:
            r0 = move-exception
        L_0x0030:
            r2.close()     // Catch:{ IOException -> 0x0034 }
            goto L_0x0038
        L_0x0034:
            r7 = move-exception
            if (r0 != 0) goto L_0x0038
            r0 = r7
        L_0x0038:
            if (r0 != 0) goto L_0x003b
            return
        L_0x003b:
            io.realm.exceptions.RealmFileException r7 = new io.realm.exceptions.RealmFileException
            io.realm.exceptions.RealmFileException$Kind r8 = io.realm.exceptions.RealmFileException.Kind.ACCESS_ERROR
            r7.<init>((io.realm.exceptions.RealmFileException.Kind) r8, (java.lang.Throwable) r0)
            throw r7
        L_0x0043:
            r8 = move-exception
            goto L_0x006b
        L_0x0045:
            r7 = move-exception
            r2 = r0
            goto L_0x0085
        L_0x0048:
            r8 = move-exception
            r2 = r0
            goto L_0x006b
        L_0x004b:
            io.realm.exceptions.RealmFileException r8 = new io.realm.exceptions.RealmFileException     // Catch:{ IOException -> 0x0048, all -> 0x0045 }
            io.realm.exceptions.RealmFileException$Kind r2 = io.realm.exceptions.RealmFileException.Kind.ACCESS_ERROR     // Catch:{ IOException -> 0x0048, all -> 0x0045 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0048, all -> 0x0045 }
            r3.<init>()     // Catch:{ IOException -> 0x0048, all -> 0x0045 }
            java.lang.String r4 = "Invalid input stream to the asset file: "
            r3.append(r4)     // Catch:{ IOException -> 0x0048, all -> 0x0045 }
            r3.append(r7)     // Catch:{ IOException -> 0x0048, all -> 0x0045 }
            java.lang.String r3 = r3.toString()     // Catch:{ IOException -> 0x0048, all -> 0x0045 }
            r8.<init>((io.realm.exceptions.RealmFileException.Kind) r2, (java.lang.String) r3)     // Catch:{ IOException -> 0x0048, all -> 0x0045 }
            throw r8     // Catch:{ IOException -> 0x0048, all -> 0x0045 }
        L_0x0064:
            r7 = move-exception
            r1 = r0
            r2 = r1
            goto L_0x0085
        L_0x0068:
            r8 = move-exception
            r1 = r0
            r2 = r1
        L_0x006b:
            io.realm.exceptions.RealmFileException r3 = new io.realm.exceptions.RealmFileException     // Catch:{ all -> 0x0084 }
            io.realm.exceptions.RealmFileException$Kind r4 = io.realm.exceptions.RealmFileException.Kind.ACCESS_ERROR     // Catch:{ all -> 0x0084 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0084 }
            r5.<init>()     // Catch:{ all -> 0x0084 }
            java.lang.String r6 = "Could not resolve the path to the asset file: "
            r5.append(r6)     // Catch:{ all -> 0x0084 }
            r5.append(r7)     // Catch:{ all -> 0x0084 }
            java.lang.String r7 = r5.toString()     // Catch:{ all -> 0x0084 }
            r3.<init>(r4, r7, r8)     // Catch:{ all -> 0x0084 }
            throw r3     // Catch:{ all -> 0x0084 }
        L_0x0084:
            r7 = move-exception
        L_0x0085:
            if (r1 == 0) goto L_0x008c
            r1.close()     // Catch:{ IOException -> 0x008b }
            goto L_0x008c
        L_0x008b:
            r0 = move-exception
        L_0x008c:
            if (r2 == 0) goto L_0x0091
            r2.close()     // Catch:{ IOException -> 0x0091 }
        L_0x0091:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.RealmCache.copyFileIfNeeded(java.lang.String, java.io.File):void");
    }

    static int getLocalThreadCount(RealmConfiguration realmConfiguration) {
        int i = 0;
        RealmCache cache = getCache(realmConfiguration.getPath(), false);
        if (cache == null) {
            return 0;
        }
        for (ReferenceCounter threadLocalCount : cache.refAndCountMap.values()) {
            i += threadLocalCount.getThreadLocalCount();
        }
        return i;
    }

    public RealmConfiguration getConfiguration() {
        return this.configuration;
    }

    private int getTotalGlobalRefCount() {
        int i = 0;
        for (ReferenceCounter globalCount : this.refAndCountMap.values()) {
            i += globalCount.getGlobalCount();
        }
        return i;
    }

    private int getTotalLiveRealmGlobalRefCount() {
        int i = 0;
        for (ReferenceCounter next : this.refAndCountMap.values()) {
            if (next instanceof ThreadConfinedReferenceCounter) {
                i += next.getGlobalCount();
            }
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    public void leak() {
        if (!this.isLeaked.getAndSet(true)) {
            leakedCaches.add(this);
        }
    }
}
