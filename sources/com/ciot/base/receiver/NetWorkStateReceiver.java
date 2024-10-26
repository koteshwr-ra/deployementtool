package com.ciot.base.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import com.ciot.base.util.MyLogUtils;

public class NetWorkStateReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        System.out.println("网络状态发生变化");
        if (Build.VERSION.SDK_INT < 21) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
            NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
            if (networkInfo.isConnected() && networkInfo2.isConnected()) {
                MyLogUtils.Logd("NETWORK_TAG", "WIFI已连接,移动数据已连接");
            } else if (networkInfo.isConnected() && !networkInfo2.isConnected()) {
                MyLogUtils.Logd("NETWORK_TAG", "WIFI已连接,移动数据已断开");
            } else if (networkInfo.isConnected() || !networkInfo2.isConnected()) {
                MyLogUtils.Logd("NETWORK_TAG", "WIFI已断开,移动数据已断开");
            } else {
                MyLogUtils.Logd("NETWORK_TAG", "WIFI已断开,移动数据已连接");
            }
        } else {
            System.out.println("API level 大于21");
            ConnectivityManager connectivityManager2 = (ConnectivityManager) context.getSystemService("connectivity");
            Network[] allNetworks = connectivityManager2.getAllNetworks();
            StringBuilder sb = new StringBuilder();
            for (Network networkInfo3 : allNetworks) {
                NetworkInfo networkInfo4 = connectivityManager2.getNetworkInfo(networkInfo3);
                sb.append(networkInfo4.getTypeName() + " connect is " + networkInfo4.isConnected());
            }
            MyLogUtils.Logd("NETWORK_TAG", sb.toString());
        }
    }
}
