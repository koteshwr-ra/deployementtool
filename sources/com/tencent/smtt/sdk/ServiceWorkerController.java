package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Build;
import com.tencent.smtt.export.external.interfaces.IX5CoreServiceWorkerController;
import com.tencent.smtt.export.external.interfaces.ServiceWorkerClient;
import com.tencent.smtt.export.external.interfaces.ServiceWorkerWebSettings;

public abstract class ServiceWorkerController {
    public static ServiceWorkerController getInstance(Context context) {
        w a = w.a();
        a.a(context);
        if (a.b()) {
            final IX5CoreServiceWorkerController q = w.a().c().q();
            if (q != null) {
                return new ServiceWorkerController() {
                    public ServiceWorkerWebSettings getServiceWorkerWebSettings() {
                        return q.getServiceWorkerWebSettings();
                    }

                    public void setServiceWorkerClient(ServiceWorkerClient serviceWorkerClient) {
                        q.setServiceWorkerClient(serviceWorkerClient);
                    }
                };
            }
            return null;
        } else if (Build.VERSION.SDK_INT >= 24) {
            return new k();
        } else {
            return null;
        }
    }

    public abstract ServiceWorkerWebSettings getServiceWorkerWebSettings();

    public abstract void setServiceWorkerClient(ServiceWorkerClient serviceWorkerClient);
}
