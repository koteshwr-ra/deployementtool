package com.limpoxe.support.servicemanager.compat;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.limpoxe.support.servicemanager.ServiceManager;
import com.limpoxe.support.servicemanager.util.RefIectUtil;

public class ContentProviderCompat {
    public static Bundle call(Uri uri, String str, String str2, Bundle bundle) {
        ContentResolver contentResolver = ServiceManager.sApplication.getContentResolver();
        if (Build.VERSION.SDK_INT >= 11) {
            return contentResolver.call(uri, str, str2, bundle);
        }
        ContentProviderClient acquireContentProviderClient = contentResolver.acquireContentProviderClient(uri);
        if (acquireContentProviderClient != null) {
            try {
                Object fieldObject = RefIectUtil.getFieldObject(acquireContentProviderClient, ContentProviderClient.class, "mContentProvider");
                Object obj = null;
                if (fieldObject != null) {
                    obj = RefIectUtil.invokeMethod(fieldObject, Class.forName("android.content.IContentProvider"), NotificationCompat.CATEGORY_CALL, new Class[]{String.class, String.class, Bundle.class}, new Object[]{str, str2, bundle});
                    Bundle bundle2 = (Bundle) obj;
                    acquireContentProviderClient.release();
                    return bundle2;
                }
                acquireContentProviderClient.release();
                return null;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Throwable th) {
                acquireContentProviderClient.release();
                throw th;
            }
        } else {
            throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }
}
