package com.ciot.base.util;

import android.app.ActivityManager;
import android.app.ZysjSystemManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.text.TextUtils;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.ciot.base.util.ExeShellUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class InfoUtils {
    private static final String CAT_RESULT = "IOT-3288A";
    private static final String CAT_SHIMEITAI_3288 = "cat sys/devices/virtual/param/smdt_param/hw";
    public static final String DISINFECT_PCK = "csst.patrol.spray";
    public static final String DISINFECT_PCK1 = "csst.patrol.oh_spray";
    private static final String DISPLAY_ID = "ro.build.display.id";
    public static final String GATEKEEPER_PCK = "com.ciot.sentry3";
    public static final String LELE_FOOD_PCK = "com.ciot.lelefoodservice";
    public static final String LELE_PCK = "com.ciot.lelerobot";
    public static final String MEAL_DELIVERY__PCK = "com.csst.robot_food";
    private static final List<String> PACKAGES = Arrays.asList(new String[]{GATEKEEPER_PCK, SERVER_ROBOT_PCK, WELCOME_PCK, LELE_PCK, LELE_FOOD_PCK, DISINFECT_PCK, DISINFECT_PCK1, MEAL_DELIVERY__PCK});
    private static final String RK_PLATFORM = "ro.board.platform";
    private static final String RK_SDK_VERSION = "ro.build.version.incremental";
    private static final String RK_SMDT = "ro.product.factory";
    public static final String ROBOT_NUM_DISINFECT = "ME1252C";
    public static final String ROBOT_NUM_GATEKEEPER = "ZZPR1120B";
    public static final String ROBOT_NUM_LELE = "PR1151A";
    public static final String ROBOT_NUM_MEAL_DELIVERY = "DE1230B";
    public static final String ROBOT_NUM_MINI_OH_DISINFECT = "ME1253B";
    public static final String ROBOT_NUM_OH_DISINFECT = "ME1253A";
    public static final String ROBOT_NUM_WELCOM = "PR1150B";
    public static final String ROBOT_NUM_WELCOME_PATROL = "PR1151C";
    public static final String ROBOT_NUM_WELCOM_1 = "PR1150C";
    public static final String SERVER_ROBOT_PCK = "com.ciot.servicerobot";
    public static final String WELCOME_PCK = "com.ciot.welcomepatrol";
    private static final String XINBU_SECONDPACKAGE = "persist.sys.secondpackage";

    public enum BoadrType {
        XINBU("信步"),
        ZHONGYUN("众云"),
        SHIMEITAI("视美泰"),
        OTHER("未知");
        
        private String name;

        private BoadrType(String str) {
            this.name = str;
        }

        public String getName() {
            return this.name;
        }
    }

    public static BoadrType getBoardType(Context context) {
        try {
            ((ZysjSystemManager) context.getSystemService("zysj")).get_zysj_gpio_value(1);
            return BoadrType.ZHONGYUN;
        } catch (Exception unused) {
            String str = GetSystemProperties.get(RK_SMDT);
            if (str != null && str.equals("SMDT")) {
                return BoadrType.SHIMEITAI;
            }
            String readMotherBoardSn = EepromUtils.readMotherBoardSn();
            String str2 = GetSystemProperties.get(DISPLAY_ID);
            String str3 = GetSystemProperties.get(RK_SDK_VERSION);
            if (str2.contains("seavo") || str2.startsWith(ExifInterface.LATITUDE_SOUTH) || str3.contains("seavo") || str3.startsWith(ExifInterface.LATITUDE_SOUTH)) {
                return BoadrType.XINBU;
            }
            if (!TextUtils.isEmpty(readMotherBoardSn)) {
                return BoadrType.XINBU;
            }
            String execRootCmd = ExeShellUtils.Companion.execRootCmd(CAT_SHIMEITAI_3288);
            if (!TextUtils.isEmpty(execRootCmd) && execRootCmd.equals(CAT_RESULT)) {
                return BoadrType.SHIMEITAI;
            }
            String str4 = GetSystemProperties.get(XINBU_SECONDPACKAGE);
            if (TextUtils.isEmpty(str4) || !str4.equals("com.dtr.dtplayer")) {
                return BoadrType.OTHER;
            }
            return BoadrType.XINBU;
        }
    }

    public enum RobotType {
        MENGANG("门岗机器人"),
        HUANHUAN("欢欢机器人"),
        LELE("乐乐机器人"),
        XUNGENG("巡更机器人"),
        XIAODU("消毒机器人"),
        OH_XIAODU("羟基消毒机器人"),
        MINI_OH_XIAODU("MINI羟基消毒机器人"),
        SONGCAN("送餐机器人"),
        OTHER("未知");
        
        private String name;

        private RobotType(String str) {
            this.name = str;
        }

        public String getName() {
            return this.name;
        }
    }

    public static String getFirmWareVersion() {
        return GetSystemProperties.get(RK_SDK_VERSION);
    }

    public static RobotType getRobotType(String str) {
        if (str.contains(ROBOT_NUM_GATEKEEPER)) {
            return RobotType.MENGANG;
        }
        if (str.contains(ROBOT_NUM_WELCOM) || str.contains(ROBOT_NUM_WELCOM_1)) {
            return RobotType.HUANHUAN;
        }
        if (str.contains(ROBOT_NUM_LELE)) {
            return RobotType.LELE;
        }
        if (str.contains(ROBOT_NUM_WELCOME_PATROL)) {
            return RobotType.XUNGENG;
        }
        if (str.contains(ROBOT_NUM_DISINFECT)) {
            return RobotType.XIAODU;
        }
        if (str.contains(ROBOT_NUM_OH_DISINFECT)) {
            return RobotType.OH_XIAODU;
        }
        if (str.contains(ROBOT_NUM_MINI_OH_DISINFECT)) {
            return RobotType.MINI_OH_XIAODU;
        }
        if (str.contains(ROBOT_NUM_MEAL_DELIVERY)) {
            return RobotType.SONGCAN;
        }
        return RobotType.OTHER;
    }

    public static void removeAllRobotApps(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        for (ActivityManager.RecentTaskInfo recentTaskInfo : activityManager.getRecentTasks(1000, 2)) {
            Log.d("wytest", "removeAllRobotApps: recentTasks->" + recentTaskInfo.baseActivity.getPackageName());
        }
        for (ActivityManager.RunningTaskInfo runningTaskInfo : activityManager.getRunningTasks(1000)) {
            Log.d("wytest", "removeAllRobotApps: runningTaskInfos-> " + runningTaskInfo.baseActivity.getPackageName());
        }
        for (ActivityManager.AppTask taskInfo : activityManager.getAppTasks()) {
            Log.d("wytest", "removeAllRobotApps: " + taskInfo.getTaskInfo().baseActivity.getPackageName());
        }
    }

    private static List<UsbDevice> getDevices(Context context) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, UsbDevice> value : ((UsbManager) context.getSystemService("usb")).getDeviceList().entrySet()) {
            arrayList.add((UsbDevice) value.getValue());
        }
        return arrayList;
    }

    public static String getTempType(Context context) {
        for (UsbDevice next : getDevices(context)) {
            if (next.getVendorId() == 33596 && next.getProductId() == 1) {
                return "巨哥";
            }
            if (next.getVendorId() == 11231 && next.getProductId() == 257) {
                return "海康";
            }
            if (next.getVendorId() == 6790 && (next.getProductId() == 21795 || next.getProductId() == 21778 || next.getProductId() == 57360)) {
                return "海曼";
            }
        }
        return "未知";
    }

    public static String getInstalledApps(Context context) {
        StringBuilder sb = new StringBuilder();
        PackageManager packageManager = context.getPackageManager();
        for (PackageInfo next : packageManager.getInstalledPackages(0)) {
            if (PACKAGES.contains(next.applicationInfo.packageName)) {
                sb.append(packageManager.getApplicationLabel(next.applicationInfo) + ",");
            }
        }
        return (sb.toString() == null || sb.toString().length() <= 1) ? "" : sb.toString().substring(0, sb.toString().length() - 1);
    }

    public static String forceAllRobotApps(Context context) {
        removeAllRobotApps(context);
        StringBuilder sb = new StringBuilder();
        for (PackageInfo next : context.getPackageManager().getInstalledPackages(0)) {
            if (PACKAGES.contains(next.applicationInfo.packageName)) {
                ExeShellUtils.Companion companion = ExeShellUtils.Companion;
                companion.execRootCmd("am force-stop " + next.applicationInfo.packageName);
            }
        }
        return (sb.toString() == null || sb.toString().length() <= 1) ? "" : sb.toString().substring(0, sb.toString().length() - 1);
    }

    public static PackageInfo getInstalledApp(Context context) {
        for (PackageInfo next : context.getPackageManager().getInstalledPackages(0)) {
            if (PACKAGES.contains(next.applicationInfo.packageName)) {
                return next;
            }
        }
        return null;
    }

    public static boolean isGateKeeper(Context context) {
        return context.getPackageName().equals(GATEKEEPER_PCK);
    }

    public static String getRkPlatform() {
        return GetSystemProperties.get(RK_PLATFORM);
    }
}
