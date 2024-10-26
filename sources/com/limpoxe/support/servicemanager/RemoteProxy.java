package com.limpoxe.support.servicemanager;

import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import com.limpoxe.support.servicemanager.compat.BundleCompat;
import com.limpoxe.support.servicemanager.compat.ContentProviderCompat;
import com.limpoxe.support.servicemanager.util.ParamUtil;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class RemoteProxy {
    public static Object getProxyService(final String str, String str2, ClassLoader classLoader) {
        try {
            return Proxy.newProxyInstance(classLoader, new Class[]{classLoader.loadClass(str2)}, new InvocationHandler() {
                String desciptpr;
                IBinder iBinder;
                Boolean isInProviderProcess;

                public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
                    IBinder iBinder2;
                    Bundle wrapperParams = ParamUtil.wrapperParams(str, method.toGenericString(), objArr);
                    if (this.isInProviderProcess == null) {
                        prepare(wrapperParams);
                    }
                    if (Boolean.TRUE.equals(this.isInProviderProcess)) {
                        return MethodRouter.routerToProvider(str, wrapperParams);
                    }
                    String str = this.desciptpr;
                    if (str != null && (iBinder2 = this.iBinder) != null) {
                        return MethodRouter.routerToBinder(str, iBinder2, wrapperParams);
                    }
                    Log.w("RemoteProxy", "not active，service May Died！");
                    if (!method.getReturnType().isPrimitive()) {
                        return null;
                    }
                    throw new IllegalStateException("Service not active! Remote process may died");
                }

                private void prepare(Bundle bundle) throws Throwable {
                    Bundle call = ContentProviderCompat.call(ServiceProvider.buildUri(), ServiceProvider.QUERY_SERVICE, str, bundle);
                    if (call != null) {
                        this.isInProviderProcess = Boolean.valueOf(call.getBoolean(ServiceProvider.QUERY_SERVICE_RESULT_IS_IN_PROVIDIDER_PROCESS, false));
                        this.iBinder = BundleCompat.getBinder(call, ServiceProvider.QUERY_SERVICE_RESULT_BINDER);
                        this.desciptpr = call.getString(ServiceProvider.QUERY_SERVICE_RESULT_DESCRIPTOR);
                        IBinder iBinder2 = this.iBinder;
                        if (iBinder2 != null) {
                            iBinder2.linkToDeath(new IBinder.DeathRecipient() {
                                public void binderDied() {
                                    AnonymousClass1.this.isInProviderProcess = null;
                                    AnonymousClass1.this.iBinder = null;
                                    AnonymousClass1.this.desciptpr = null;
                                }
                            }, 0);
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
