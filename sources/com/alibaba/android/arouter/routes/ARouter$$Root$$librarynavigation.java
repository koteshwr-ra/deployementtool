package com.alibaba.android.arouter.routes;

import androidx.core.app.NotificationCompat;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.alibaba.android.arouter.facade.template.IRouteRoot;
import java.util.Map;

public class ARouter$$Root$$librarynavigation implements IRouteRoot {
    public void loadInto(Map<String, Class<? extends IRouteGroup>> map) {
        map.put(NotificationCompat.CATEGORY_NAVIGATION, ARouter$$Group$$navigation.class);
    }
}
