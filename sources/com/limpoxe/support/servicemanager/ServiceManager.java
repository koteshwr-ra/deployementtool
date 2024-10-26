package com.limpoxe.support.servicemanager;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Process;
import com.limpoxe.support.servicemanager.compat.BundleCompat;
import com.limpoxe.support.servicemanager.compat.ContentProviderCompat;
import com.limpoxe.support.servicemanager.local.ServicePool;

public class ServiceManager {
    public static final String ACTION_SERVICE_DIE_OR_CLEAR = "com.limpoxe.support.action.SERVICE_DIE_OR_CLEAR";
    public static Application sApplication;

    public static void init(Application application) {
        sApplication = application;
        Bundle bundle = new Bundle();
        int myPid = Process.myPid();
        bundle.putInt("pid", myPid);
        BundleCompat.putBinder(bundle, ServiceProvider.BINDER, new ProcessBinder(ProcessBinder.class.getName() + "_" + myPid));
        ContentProviderCompat.call(ServiceProvider.buildUri(), ServiceProvider.REPORT_BINDER, (String) null, bundle);
        sApplication.registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                ServicePool.unRegister(intent.getStringExtra(ServiceProvider.NAME));
            }
        }, new IntentFilter(ACTION_SERVICE_DIE_OR_CLEAR));
    }

    public static Object getService(String str) {
        return getService(str, ServiceManager.class.getClassLoader());
    }

    public static Object getService(String str, ClassLoader classLoader) {
        Bundle call;
        String string;
        Object service = ServicePool.getService(str);
        if (!(service != null || (call = ContentProviderCompat.call(ServiceProvider.buildUri(), ServiceProvider.QUERY_INTERFACE, str, (Bundle) null)) == null || (string = call.getString(ServiceProvider.QUERY_INTERFACE_RESULT)) == null || (service = RemoteProxy.getProxyService(str, string, classLoader)) == null)) {
            ServicePool.registerInstance(str, service);
        }
        return service;
    }

    public static void publishService(String str, String str2) {
        publishService(str, str2, ServiceManager.class.getClassLoader());
    }

    public static void publishService(String str, final String str2, final ClassLoader classLoader) {
        publishService(str, (ServicePool.ClassProvider) new ServicePool.ClassProvider() {
            public Object getServiceInstance() {
                try {
                    return classLoader.loadClass(str2).newInstance();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    return null;
                } catch (InstantiationException e2) {
                    e2.printStackTrace();
                    return null;
                } catch (IllegalAccessException e3) {
                    e3.printStackTrace();
                    return null;
                }
            }

            public String getInterfaceName() {
                try {
                    return classLoader.loadClass(str2).getInterfaces()[0].getName();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        });
    }

    public static void publishService(String str, ServicePool.ClassProvider classProvider) {
        ServicePool.registerClass(str, classProvider);
        int myPid = Process.myPid();
        Bundle bundle = new Bundle();
        bundle.putInt("pid", myPid);
        bundle.putString(ServiceProvider.INTERFACE, classProvider.getInterfaceName());
        ContentProviderCompat.call(ServiceProvider.buildUri(), ServiceProvider.PUBLISH_SERVICE, str, bundle);
    }

    public static void unPublishAllService() {
        int myPid = Process.myPid();
        Bundle bundle = new Bundle();
        bundle.putInt("pid", myPid);
        ContentProviderCompat.call(ServiceProvider.buildUri(), ServiceProvider.UNPUBLISH_SERVICE, (String) null, bundle);
    }

    public static void unPublishService(String str) {
        int myPid = Process.myPid();
        Bundle bundle = new Bundle();
        bundle.putInt("pid", myPid);
        bundle.putString(ServiceProvider.NAME, str);
        ContentProviderCompat.call(ServiceProvider.buildUri(), ServiceProvider.UNPUBLISH_SERVICE, (String) null, bundle);
    }
}
