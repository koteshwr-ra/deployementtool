package com.limpoxe.support.servicemanager;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.limpoxe.support.servicemanager.compat.ContentProviderCompat;
import com.limpoxe.support.servicemanager.util.ParamUtil;

public class MethodRouter {
    public static Object routerToBinder(String str, IBinder iBinder, Bundle bundle) {
        try {
            return ParamUtil.getResult(transact(str, iBinder, bundle));
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object routerToProvider(String str, Bundle bundle) {
        return ParamUtil.getResult(ContentProviderCompat.call(ServiceProvider.buildUri(), "call_service", str, bundle));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0040, code lost:
        if (r5.isAccessible() != false) goto L_0x0046;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0042, code lost:
        r5.setAccessible(true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0046, code lost:
        r7 = r5.invoke(r0, com.limpoxe.support.servicemanager.util.ParamUtil.unwrapperParams(r7));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.os.Bundle routerToInstance(android.os.Bundle r7) {
        /*
            java.lang.String r0 = "service_name"
            java.lang.String r0 = r7.getString(r0)
            java.lang.String r1 = "method_name"
            java.lang.String r1 = r7.getString(r1)
            java.lang.Object r0 = com.limpoxe.support.servicemanager.local.ServicePool.getService(r0)
            if (r0 == 0) goto L_0x005c
            java.lang.Class r2 = r0.getClass()
            boolean r2 = java.lang.reflect.Proxy.isProxyClass(r2)
            if (r2 != 0) goto L_0x005c
            java.lang.Class r2 = r0.getClass()
            java.lang.Class[] r2 = r2.getInterfaces()
            r3 = 0
            r2 = r2[r3]
            java.lang.reflect.Method[] r2 = r2.getDeclaredMethods()
            if (r2 == 0) goto L_0x005c
            int r4 = r2.length
        L_0x002e:
            if (r3 >= r4) goto L_0x005c
            r5 = r2[r3]
            java.lang.String r6 = r5.toGenericString()
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x0059
            boolean r1 = r5.isAccessible()     // Catch:{ IllegalAccessException -> 0x0054, InvocationTargetException -> 0x004f }
            if (r1 != 0) goto L_0x0046
            r1 = 1
            r5.setAccessible(r1)     // Catch:{ IllegalAccessException -> 0x0054, InvocationTargetException -> 0x004f }
        L_0x0046:
            java.lang.Object[] r7 = com.limpoxe.support.servicemanager.util.ParamUtil.unwrapperParams(r7)     // Catch:{ IllegalAccessException -> 0x0054, InvocationTargetException -> 0x004f }
            java.lang.Object r7 = r5.invoke(r0, r7)     // Catch:{ IllegalAccessException -> 0x0054, InvocationTargetException -> 0x004f }
            goto L_0x005d
        L_0x004f:
            r7 = move-exception
            r7.printStackTrace()
            goto L_0x005c
        L_0x0054:
            r7 = move-exception
            r7.printStackTrace()
            goto L_0x005c
        L_0x0059:
            int r3 = r3 + 1
            goto L_0x002e
        L_0x005c:
            r7 = 0
        L_0x005d:
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            java.lang.String r1 = "result"
            com.limpoxe.support.servicemanager.compat.BundleCompat.putObject(r0, r1, r7)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.limpoxe.support.servicemanager.MethodRouter.routerToInstance(android.os.Bundle):android.os.Bundle");
    }

    private static Bundle transact(String str, IBinder iBinder, Bundle bundle) throws RemoteException {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(str);
            if (bundle != null) {
                obtain.writeInt(1);
                bundle.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            iBinder.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
