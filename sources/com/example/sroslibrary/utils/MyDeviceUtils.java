package com.example.sroslibrary.utils;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import com.blankj.utilcode.util.Utils;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public final class MyDeviceUtils {
    private MyDeviceUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static String getMacAddress() {
        return getMacAddress((String[]) null);
    }

    private static boolean getWifiEnabled() {
        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService("wifi");
        if (wifiManager == null) {
            return false;
        }
        return wifiManager.isWifiEnabled();
    }

    public static void setWifiEnabled(boolean z) {
        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService("wifi");
        if (wifiManager != null && z != wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(z);
        }
    }

    public static String getMacAddress(String... strArr) {
        String macAddressByNetworkInterface = getMacAddressByNetworkInterface();
        Log.d("getMacAddress", "getMacAddressByNetworkInterface-> " + macAddressByNetworkInterface);
        if (isAddressNotInExcepts(macAddressByNetworkInterface, strArr)) {
            return macAddressByNetworkInterface;
        }
        String macAddressByInetAddress = getMacAddressByInetAddress();
        Log.d("getMacAddress", "getMacAddressByInetAddress-> " + macAddressByInetAddress);
        if (isAddressNotInExcepts(macAddressByInetAddress, strArr)) {
            return macAddressByInetAddress;
        }
        String macAddressByWifiInfo = getMacAddressByWifiInfo();
        Log.d("getMacAddress", "getMacAddressByWifiInfo-> " + macAddressByWifiInfo);
        if (isAddressNotInExcepts(macAddressByWifiInfo, strArr)) {
            return macAddressByWifiInfo;
        }
        String macAddressByFile = getMacAddressByFile();
        Log.d("getMacAddress", "getMacAddressByFile-> " + macAddressByFile);
        return isAddressNotInExcepts(macAddressByFile, strArr) ? macAddressByFile : "";
    }

    private static boolean isAddressNotInExcepts(String str, String... strArr) {
        if (strArr == null || strArr.length == 0) {
            return !"02:00:00:00:00:00".equals(str);
        }
        for (String equals : strArr) {
            if (str.equals(equals)) {
                return false;
            }
        }
        return true;
    }

    private static String getMacAddressByWifiInfo() {
        WifiInfo connectionInfo;
        try {
            WifiManager wifiManager = (WifiManager) Utils.getApp().getApplicationContext().getSystemService("wifi");
            if (wifiManager == null || (connectionInfo = wifiManager.getConnectionInfo()) == null) {
                return "02:00:00:00:00:00";
            }
            return connectionInfo.getMacAddress();
        } catch (Exception e) {
            e.printStackTrace();
            return "02:00:00:00:00:00";
        }
    }

    private static String getMacAddressByNetworkInterface() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                if (nextElement != null) {
                    byte[] hardwareAddress = nextElement.getHardwareAddress();
                    if (hardwareAddress != null && hardwareAddress.length > 0) {
                        StringBuilder sb = new StringBuilder();
                        int length = hardwareAddress.length;
                        for (int i = 0; i < length; i++) {
                            sb.append(String.format("%02x:", new Object[]{Byte.valueOf(hardwareAddress[i])}));
                        }
                        return sb.substring(0, sb.length() - 1);
                    }
                }
            }
            return "02:00:00:00:00:00";
        } catch (Exception e) {
            e.printStackTrace();
            return "02:00:00:00:00:00";
        }
    }

    private static String getMacAddressByInetAddress() {
        NetworkInterface byInetAddress;
        byte[] hardwareAddress;
        try {
            InetAddress inetAddress = getInetAddress();
            if (inetAddress == null || (byInetAddress = NetworkInterface.getByInetAddress(inetAddress)) == null || (hardwareAddress = byInetAddress.getHardwareAddress()) == null || hardwareAddress.length <= 0) {
                return "02:00:00:00:00:00";
            }
            StringBuilder sb = new StringBuilder();
            int length = hardwareAddress.length;
            for (int i = 0; i < length; i++) {
                sb.append(String.format("%02x:", new Object[]{Byte.valueOf(hardwareAddress[i])}));
            }
            return sb.substring(0, sb.length() - 1);
        } catch (Exception e) {
            e.printStackTrace();
            return "02:00:00:00:00:00";
        }
    }

    private static InetAddress getInetAddress() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                if (nextElement.isUp()) {
                    Enumeration<InetAddress> inetAddresses = nextElement.getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress nextElement2 = inetAddresses.nextElement();
                        if (!nextElement2.isLoopbackAddress() && nextElement2.getHostAddress().indexOf(58) < 0) {
                            return nextElement2;
                        }
                    }
                    continue;
                }
            }
            return null;
        } catch (SocketException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x002d, code lost:
        r0 = r0.successMsg;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String getMacAddressByFile() {
        /*
            java.lang.String r0 = "getprop wifi.interface"
            r1 = 0
            com.blankj.utilcode.util.ShellUtils$CommandResult r0 = com.blankj.utilcode.util.ShellUtils.execCmd((java.lang.String) r0, (boolean) r1)
            int r2 = r0.result
            if (r2 != 0) goto L_0x0038
            java.lang.String r0 = r0.successMsg
            if (r0 == 0) goto L_0x0038
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "cat /sys/class/net/"
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = "/address"
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            com.blankj.utilcode.util.ShellUtils$CommandResult r0 = com.blankj.utilcode.util.ShellUtils.execCmd((java.lang.String) r0, (boolean) r1)
            int r1 = r0.result
            if (r1 != 0) goto L_0x0038
            java.lang.String r0 = r0.successMsg
            if (r0 == 0) goto L_0x0038
            int r1 = r0.length()
            if (r1 <= 0) goto L_0x0038
            return r0
        L_0x0038:
            java.lang.String r0 = "02:00:00:00:00:00"
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.sroslibrary.utils.MyDeviceUtils.getMacAddressByFile():java.lang.String");
    }
}
