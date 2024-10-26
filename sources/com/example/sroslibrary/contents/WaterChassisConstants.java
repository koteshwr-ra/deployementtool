package com.example.sroslibrary.contents;

import android.os.Environment;
import com.ciot.base.constant.FileConstant;
import io.reactivex.annotations.SchedulerSupport;
import java.io.File;
import mc.csst.com.selfchassis.ui.fragment.set.schedule.ScheduleFragment;

public class WaterChassisConstants {
    public static final String SP_MAP_NAME_KEY = "WaterMapName";
    public static final String SP_PRODUCT_ID_KEY = "WaterProductId";
    public static final String WATER_GET_CURRENT_MAP = "/api/map/get_current_map";
    public static final String WATER_GET_MAP_LIST = "/api/map/list";
    public static final String WATER_MAIN_CONTROL_APK_FILE_PATH;
    public static final String WATER_MAIN_CONTROL_CUSTOM_FILE_NAME = "robotInfo.txt";
    public static final String WATER_MAIN_CONTROL_CUSTOM_FILE_PATH = (Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "MainControl" + File.separator + SchedulerSupport.CUSTOM + File.separator);
    public static final String WATER_MAIN_CONTROL_MAP_FILE_PATH = (Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "MainControl" + File.separator + ScheduleFragment.MAP + File.separator + "water" + File.separator);
    public static final String WATER_NVR_CAPTURE_FILE_PATH = (Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "MainControl" + File.separator + "camera" + File.separator + "capture" + File.separator);
    public static final String WATER_NVR_LOG_FILE_PATH = (Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "MainControl" + File.separator + "log" + File.separator + "nvrLog" + File.separator);
    public static final String WATER_NVR_VIDEO_FILE_PATH = (Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "MainControl" + File.separator + "camera" + File.separator + FileConstant.VIDEO_FILE_NAME + File.separator);
    public static final String WATER_SET_CURRENT_MAP = "/api/map/set_current_map";
    public static final int WATER_UDP_CLOSE = 0;
    public static final String WATER_UDP_IP = "192.168.10.152";
    public static final int WATER_UDP_OPEN = 1;
    public static final int WATER_UDP_PORT = 7900;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory().getAbsolutePath());
        sb.append(File.separator);
        sb.append("MainControl");
        sb.append(File.separator);
        sb.append("apk");
        sb.append(File.separator);
        WATER_MAIN_CONTROL_APK_FILE_PATH = sb.toString();
    }
}
