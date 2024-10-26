package com.ciot.base.util;

import android.content.Context;
import com.blankj.utilcode.util.NetworkUtils;

public class NetworkUtil {
    public static boolean isNetworkDisconnect = false;

    public static boolean isNetworkAvailable(Context context) {
        return NetworkUtils.isConnected();
    }
}
