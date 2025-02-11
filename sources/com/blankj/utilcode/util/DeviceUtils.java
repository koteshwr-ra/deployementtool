package com.blankj.utilcode.util;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.UUID;
import mc.csst.com.selfchassis.ui.fragment.set.map.MapFragment;

public final class DeviceUtils {
    private static final String KEY_UDID = "KEY_UDID";
    private static volatile String udid;

    private DeviceUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean isDeviceRooted() {
        String[] strArr = {"/system/bin/", "/system/xbin/", "/sbin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/xbin/", "/data/local/bin/", "/data/local/", "/system/sbin/", "/usr/bin/", "/vendor/bin/"};
        for (int i = 0; i < 11; i++) {
            String str = strArr[i];
            if (new File(str + "su").exists()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAdbEnabled() {
        if (Settings.Secure.getInt(Utils.getApp().getContentResolver(), "adb_enabled", 0) > 0) {
            return true;
        }
        return false;
    }

    public static String getSDKVersionName() {
        return Build.VERSION.RELEASE;
    }

    public static int getSDKVersionCode() {
        return Build.VERSION.SDK_INT;
    }

    public static String getAndroidID() {
        String string = Settings.Secure.getString(Utils.getApp().getContentResolver(), "android_id");
        if ("9774d56d682e549c".equals(string)) {
            return "";
        }
        return string == null ? "" : string;
    }

    public static String getMacAddress() {
        String[] strArr = null;
        String macAddress = getMacAddress(strArr);
        if (!macAddress.equals("") || getWifiEnabled()) {
            return macAddress;
        }
        setWifiEnabled(true);
        setWifiEnabled(false);
        return getMacAddress(strArr);
    }

    private static boolean getWifiEnabled() {
        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService("wifi");
        if (wifiManager == null) {
            return false;
        }
        return wifiManager.isWifiEnabled();
    }

    private static void setWifiEnabled(boolean z) {
        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService("wifi");
        if (wifiManager != null && z != wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(z);
        }
    }

    public static String getMacAddress(String... strArr) {
        String macAddressByNetworkInterface = getMacAddressByNetworkInterface();
        if (isAddressNotInExcepts(macAddressByNetworkInterface, strArr)) {
            return macAddressByNetworkInterface;
        }
        String macAddressByInetAddress = getMacAddressByInetAddress();
        if (isAddressNotInExcepts(macAddressByInetAddress, strArr)) {
            return macAddressByInetAddress;
        }
        String macAddressByWifiInfo = getMacAddressByWifiInfo();
        if (isAddressNotInExcepts(macAddressByWifiInfo, strArr)) {
            return macAddressByWifiInfo;
        }
        String macAddressByFile = getMacAddressByFile();
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
                    if (nextElement.getName().equalsIgnoreCase("wlan0")) {
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
            com.blankj.utilcode.util.ShellUtils$CommandResult r0 = com.blankj.utilcode.util.UtilsBridge.execCmd(r0, r1)
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
            com.blankj.utilcode.util.ShellUtils$CommandResult r0 = com.blankj.utilcode.util.UtilsBridge.execCmd(r0, r1)
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
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.DeviceUtils.getMacAddressByFile():java.lang.String");
    }

    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }

    public static String getModel() {
        String str = Build.MODEL;
        if (str != null) {
            return str.trim().replaceAll("\\s*", "");
        }
        return "";
    }

    public static String[] getABIs() {
        if (Build.VERSION.SDK_INT >= 21) {
            return Build.SUPPORTED_ABIS;
        }
        if (!TextUtils.isEmpty(Build.CPU_ABI2)) {
            return new String[]{Build.CPU_ABI, Build.CPU_ABI2};
        }
        return new String[]{Build.CPU_ABI};
    }

    public static boolean isTablet() {
        return (Resources.getSystem().getConfiguration().screenLayout & 15) >= 3;
    }

    public static boolean isEmulator() {
        String str;
        if (Build.FINGERPRINT.startsWith("generic") || Build.FINGERPRINT.toLowerCase().contains("vbox") || Build.FINGERPRINT.toLowerCase().contains("test-keys") || Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build.MODEL.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion") || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")) || "google_sdk".equals(Build.PRODUCT)) {
            return true;
        }
        TelephonyManager telephonyManager = (TelephonyManager) Utils.getApp().getSystemService("phone");
        if (telephonyManager == null || (str = telephonyManager.getNetworkOperatorName()) == null) {
            str = "";
        }
        if (str.toLowerCase().equals("android")) {
            return true;
        }
        Intent intent = new Intent();
        intent.setData(Uri.parse("tel:123456"));
        intent.setAction("android.intent.action.DIAL");
        return intent.resolveActivity(Utils.getApp().getPackageManager()) == null;
    }

    public static String getUniqueDeviceId() {
        return getUniqueDeviceId("", true);
    }

    public static String getUniqueDeviceId(String str) {
        return getUniqueDeviceId(str, true);
    }

    public static String getUniqueDeviceId(boolean z) {
        return getUniqueDeviceId("", z);
    }

    public static String getUniqueDeviceId(String str, boolean z) {
        if (!z) {
            return getUniqueDeviceIdReal(str);
        }
        if (udid == null) {
            synchronized (DeviceUtils.class) {
                if (udid == null) {
                    String string = UtilsBridge.getSpUtils4Utils().getString(KEY_UDID, (String) null);
                    if (string != null) {
                        udid = string;
                        String str2 = udid;
                        return str2;
                    }
                    String uniqueDeviceIdReal = getUniqueDeviceIdReal(str);
                    return uniqueDeviceIdReal;
                }
            }
        }
        return udid;
    }

    private static String getUniqueDeviceIdReal(String str) {
        try {
            String androidID = getAndroidID();
            if (!TextUtils.isEmpty(androidID)) {
                return saveUdid(str + 2, androidID);
            }
        } catch (Exception unused) {
        }
        return saveUdid(str + 9, "");
    }

    public static boolean isSameDevice(String str) {
        if (TextUtils.isEmpty(str) && str.length() < 33) {
            return false;
        }
        if (str.equals(udid) || str.equals(UtilsBridge.getSpUtils4Utils().getString(KEY_UDID, (String) null))) {
            return true;
        }
        int length = str.length() - 33;
        int i = length + 1;
        String substring = str.substring(length, i);
        if (substring.startsWith("1")) {
            String macAddress = getMacAddress();
            if (macAddress.equals("")) {
                return false;
            }
            return str.substring(i).equals(getUdid("", macAddress));
        } else if (!substring.startsWith("2")) {
            return false;
        } else {
            String androidID = getAndroidID();
            if (TextUtils.isEmpty(androidID)) {
                return false;
            }
            return str.substring(i).equals(getUdid("", androidID));
        }
    }

    private static String saveUdid(String str, String str2) {
        udid = getUdid(str, str2);
        UtilsBridge.getSpUtils4Utils().put(KEY_UDID, udid);
        return udid;
    }

    private static String getUdid(String str, String str2) {
        if (str2.equals("")) {
            return str + UUID.randomUUID().toString().replace(MapFragment.SLASH, "");
        }
        return str + UUID.nameUUIDFromBytes(str2.getBytes()).toString().replace(MapFragment.SLASH, "");
    }
}
