package com.ciot.navigation.navigation.provider;

import android.content.Context;
import com.ciot.navigation.navigation.NavigationHelper;
import com.ciot.navigation.navigation.self.listener.OnMarkerListListener;
import com.ciot.navigation.navigation.task.RobotTaskUtil;
import com.limpoxe.support.servicemanager.provider.navigation.INavigationHelperProvider;

public class NavigationProviderImpl implements INavigationHelperProvider {
    public void init(Context context) {
    }

    public Runnable getRobotTaskUtil() {
        return RobotTaskUtil.getInstance();
    }

    public void isInitSuccess() {
        NavigationHelper.Companion.getInstance().isInitSuccess();
    }

    public boolean isConnected() {
        return NavigationHelper.Companion.getInstance().isConnected();
    }

    public void resetOnNavigationListener() {
        NavigationHelper.Companion.getInstance().setOnMarkerListListener((OnMarkerListListener) null);
    }
}
