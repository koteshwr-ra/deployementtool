package com.limpoxe.support.servicemanager.local;

import android.os.Process;
import android.util.Log;
import java.util.Hashtable;

public class ServicePool {
    private static final Hashtable<String, ServiceFetcher> SYSTEM_SERVICE_MAP = new Hashtable<>();
    /* access modifiers changed from: private */
    public static final String TAG = ServicePool.class.getSimpleName();

    public static abstract class ClassProvider {
        public abstract String getInterfaceName();

        public abstract Object getServiceInstance();
    }

    private ServicePool() {
    }

    public static synchronized void registerClass(String str, final ClassProvider classProvider) {
        synchronized (ServicePool.class) {
            Log.d(TAG, "registerClass service " + str);
            if (!SYSTEM_SERVICE_MAP.containsKey(str)) {
                AnonymousClass1 r1 = new ServiceFetcher() {
                    public Object createService(int i) {
                        Object serviceInstance = ClassProvider.this.getServiceInstance();
                        this.mGroupId = String.valueOf(Process.myPid());
                        String access$000 = ServicePool.TAG;
                        Log.d(access$000, "create service instance @ pid " + Process.myPid());
                        return serviceInstance;
                    }
                };
                r1.mServiceId++;
                SYSTEM_SERVICE_MAP.put(str, r1);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0048, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004a, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void registerInstance(java.lang.String r4, final java.lang.Object r5) {
        /*
            java.lang.Class<com.limpoxe.support.servicemanager.local.ServicePool> r0 = com.limpoxe.support.servicemanager.local.ServicePool.class
            monitor-enter(r0)
            java.lang.String r1 = TAG     // Catch:{ all -> 0x004b }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x004b }
            r2.<init>()     // Catch:{ all -> 0x004b }
            java.lang.String r3 = "registerInstance service "
            r2.append(r3)     // Catch:{ all -> 0x004b }
            r2.append(r4)     // Catch:{ all -> 0x004b }
            java.lang.String r3 = " @ "
            r2.append(r3)     // Catch:{ all -> 0x004b }
            r2.append(r5)     // Catch:{ all -> 0x004b }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x004b }
            android.util.Log.d(r1, r2)     // Catch:{ all -> 0x004b }
            java.lang.Class r1 = r5.getClass()     // Catch:{ all -> 0x004b }
            java.lang.Class[] r1 = r1.getInterfaces()     // Catch:{ all -> 0x004b }
            if (r1 == 0) goto L_0x0049
            int r1 = r1.length     // Catch:{ all -> 0x004b }
            if (r1 != 0) goto L_0x002f
            goto L_0x0049
        L_0x002f:
            java.util.Hashtable<java.lang.String, com.limpoxe.support.servicemanager.local.ServiceFetcher> r1 = SYSTEM_SERVICE_MAP     // Catch:{ all -> 0x004b }
            boolean r1 = r1.containsKey(r4)     // Catch:{ all -> 0x004b }
            if (r1 != 0) goto L_0x0047
            com.limpoxe.support.servicemanager.local.ServicePool$2 r1 = new com.limpoxe.support.servicemanager.local.ServicePool$2     // Catch:{ all -> 0x004b }
            r1.<init>(r5)     // Catch:{ all -> 0x004b }
            int r5 = r1.mServiceId     // Catch:{ all -> 0x004b }
            int r5 = r5 + 1
            r1.mServiceId = r5     // Catch:{ all -> 0x004b }
            java.util.Hashtable<java.lang.String, com.limpoxe.support.servicemanager.local.ServiceFetcher> r5 = SYSTEM_SERVICE_MAP     // Catch:{ all -> 0x004b }
            r5.put(r4, r1)     // Catch:{ all -> 0x004b }
        L_0x0047:
            monitor-exit(r0)
            return
        L_0x0049:
            monitor-exit(r0)
            return
        L_0x004b:
            r4 = move-exception
            monitor-exit(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.limpoxe.support.servicemanager.local.ServicePool.registerInstance(java.lang.String, java.lang.Object):void");
    }

    public static Object getService(String str) {
        ServiceFetcher serviceFetcher = SYSTEM_SERVICE_MAP.get(str);
        if (serviceFetcher == null) {
            return null;
        }
        return serviceFetcher.getService();
    }

    public static void unRegister(String str) {
        String str2 = TAG;
        Log.d(str2, "unRegister service " + str);
        SYSTEM_SERVICE_MAP.remove(str);
    }
}
