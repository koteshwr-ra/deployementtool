package com.alibaba.android.arouter.routes;

import androidx.core.app.NotificationCompat;
import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.ciot.navigation.navigation.provider.NavigationProviderImpl;
import com.limpoxe.support.servicemanager.provider.ARouterConstants;
import java.util.Map;

public class ARouter$$Group$$navigation implements IRouteGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put(ARouterConstants.LIB_NAVIGATION, RouteMeta.build(RouteType.PROVIDER, NavigationProviderImpl.class, ARouterConstants.LIB_NAVIGATION, NotificationCompat.CATEGORY_NAVIGATION, (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
    }
}
