package com.limpoxe.support.servicemanager.provider.navigation;

import com.alibaba.android.arouter.facade.template.IProvider;

public interface INavigationHelperProvider extends IProvider {
    Runnable getRobotTaskUtil();

    boolean isConnected();

    void isInitSuccess();

    void resetOnNavigationListener();
}
