package com.ciot.base.util;

import android.content.Context;
import android.content.Intent;

public class NavigationBarUtil {
    public static void showNavigationBar(Context context, boolean z) {
        MyLogUtils.Logi("NavigationBarUtil", "showNavigationBar:" + z);
        if (z) {
            context.sendBroadcast(new Intent("action.SHOW_STATUSBAR"));
        } else {
            context.sendBroadcast(new Intent("action.HIDE_STATUSBAR"));
        }
    }
}
