package com.ciot.navigation.navigation.water;

import com.ciot.waterchassis.bean.WaterStatusBean;

public interface IWaterNavigateListener {
    void stateArrivedPointNotification(String str);

    void stateFull(WaterStatusBean waterStatusBean);

    void stateMaxLinearSpeed(float f);

    void stateNavigateErrMsg(String str, String str2);
}
