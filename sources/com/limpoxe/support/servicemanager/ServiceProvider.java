package com.limpoxe.support.servicemanager;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.limpoxe.support.servicemanager.compat.BundleCompat;
import com.limpoxe.support.servicemanager.local.ServicePool;
import java.lang.reflect.Proxy;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceProvider extends ContentProvider {
    public static final String BINDER = "binder";
    public static final String CALL_SERVICE = "call_service";
    private static Uri CONTENT_URI = null;
    public static final String INTERFACE = "interface";
    public static final String NAME = "name";
    public static final String PID = "pid";
    public static final String PUBLISH_SERVICE = "publish_service";
    public static final String PUBLISH_SERVICE_BINDER = "publish_service_binder";
    public static final String QUERY_INTERFACE = "query_interface";
    public static final String QUERY_INTERFACE_RESULT = "query_interface_result";
    public static final String QUERY_SERVICE = "query_service";
    public static final String QUERY_SERVICE_RESULT_BINDER = "query_service_result_binder";
    public static final String QUERY_SERVICE_RESULT_DESCRIPTOR = "query_service_result_desciptor";
    public static final String QUERY_SERVICE_RESULT_IS_IN_PROVIDIDER_PROCESS = "query_service_result_is_in_provider_process";
    public static final String REPORT_BINDER = "report_binder";
    public static final String UNPUBLISH_SERVICE = "unpublish_service";
    private static ConcurrentHashMap<String, Recorder> allServiceList = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<Integer, IBinder> processBinder = new ConcurrentHashMap<>();

    public static class Recorder {
        public String interfaceClass;
        public Integer pid;
    }

    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    public String getType(Uri uri) {
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    public boolean onCreate() {
        return false;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    public static Uri buildUri() {
        if (CONTENT_URI == null) {
            CONTENT_URI = Uri.parse("content://" + ServiceManager.sApplication.getPackageName() + ".svcmgr/call");
        }
        return CONTENT_URI;
    }

    public Bundle call(String str, String str2, Bundle bundle) {
        IBinder iBinder;
        if (Build.VERSION.SDK_INT >= 19) {
            Log.d(NotificationCompat.CATEGORY_CALL, "callingPackage = " + getCallingPackage());
        }
        Log.d(NotificationCompat.CATEGORY_CALL, "Thead : id = " + Thread.currentThread().getId() + ", name = " + Thread.currentThread().getName() + ", method = " + str + ", arg = " + str2);
        if (str.equals(REPORT_BINDER)) {
            final int i = bundle.getInt("pid");
            IBinder binder = BundleCompat.getBinder(bundle, BINDER);
            processBinder.put(Integer.valueOf(i), binder);
            try {
                binder.linkToDeath(new IBinder.DeathRecipient() {
                    public void binderDied() {
                        ServiceProvider.this.removeAllRecordorForPid(i);
                    }
                }, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
                processBinder.remove(Integer.valueOf(i));
            }
        } else if (str.equals(PUBLISH_SERVICE)) {
            int i2 = bundle.getInt("pid");
            String string = bundle.getString(INTERFACE);
            IBinder iBinder2 = processBinder.get(Integer.valueOf(i2));
            if (iBinder2 == null || !iBinder2.isBinderAlive()) {
                allServiceList.remove(Integer.valueOf(i2));
            } else {
                Recorder recorder = new Recorder();
                recorder.pid = Integer.valueOf(i2);
                recorder.interfaceClass = string;
                allServiceList.put(str2, recorder);
            }
            return null;
        } else if (str.equals(UNPUBLISH_SERVICE)) {
            int i3 = bundle.getInt("pid");
            String string2 = bundle.getString(NAME);
            if (TextUtils.isEmpty(string2)) {
                removeAllRecordorForPid(i3);
            } else {
                allServiceList.remove(string2);
                notifyClient(string2);
            }
            return null;
        } else if (str.equals("call_service")) {
            return MethodRouter.routerToInstance(bundle);
        } else {
            if (str.equals(QUERY_INTERFACE)) {
                Bundle bundle2 = new Bundle();
                Recorder recorder2 = allServiceList.get(str2);
                if (recorder2 != null) {
                    bundle2.putString(QUERY_INTERFACE_RESULT, recorder2.interfaceClass);
                }
                return bundle2;
            } else if (str.equals(QUERY_SERVICE) && allServiceList.containsKey(str2)) {
                Object service = ServicePool.getService(str2);
                Bundle bundle3 = new Bundle();
                if (service == null || Proxy.isProxyClass(service.getClass())) {
                    Recorder recorder3 = allServiceList.get(str2);
                    if (!(recorder3 == null || (iBinder = processBinder.get(recorder3.pid)) == null || !iBinder.isBinderAlive())) {
                        bundle3.putBoolean(QUERY_SERVICE_RESULT_IS_IN_PROVIDIDER_PROCESS, false);
                        bundle3.putString(QUERY_SERVICE_RESULT_DESCRIPTOR, ProcessBinder.class.getName() + "_" + recorder3.pid);
                        BundleCompat.putBinder(bundle3, QUERY_SERVICE_RESULT_BINDER, iBinder);
                        return bundle3;
                    }
                } else {
                    bundle3.putBoolean(QUERY_SERVICE_RESULT_IS_IN_PROVIDIDER_PROCESS, true);
                    return bundle3;
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void removeAllRecordorForPid(int i) {
        Log.w("ServiceProvider", "remove all service recordor for pid" + i);
        processBinder.remove(Integer.valueOf(i));
        Iterator<Map.Entry<String, Recorder>> it = allServiceList.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            if (((Recorder) next.getValue()).pid.equals(Integer.valueOf(i))) {
                it.remove();
                notifyClient((String) next.getKey());
            }
        }
    }

    private void notifyClient(String str) {
        Intent intent = new Intent(ServiceManager.ACTION_SERVICE_DIE_OR_CLEAR);
        intent.putExtra(NAME, str);
        ServiceManager.sApplication.sendBroadcast(intent);
    }
}
