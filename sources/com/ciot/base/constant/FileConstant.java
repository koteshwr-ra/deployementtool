package com.ciot.base.constant;

import android.os.Environment;
import com.blankj.utilcode.util.AppUtils;
import com.ciot.base.config.SpeechConstants;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FileConstant {
    private static final String APK_FILE_NAME = "apk";
    public static final String APK_FILE_PATH = (Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + AppUtils.getAppPackageName() + File.separator + APK_FILE_NAME + File.separator);
    private static final String APP_FILE_NAME = "spray";
    public static final String APP_PATH;
    public static final String Dir_LOG_TODAY = (APP_PATH + File.separator + LOG_TODAY_FILE_NAME);
    public static final String EMPLOYEE_FACE_CODE_FILE_NAME = "employee.txt";
    public static final String EMPLOYEE_FACE_CODE_FILE_PATH;
    private static final String FACE_CODE_DIR_NAME = "faceCode";
    public static final String FACE_CODE_DIR_PATH = (Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + AppUtils.getAppPackageName() + File.separator + FACE_CODE_DIR_NAME + File.separator);
    public static final String FACE_IMAGE_DIR_PATH;
    private static final String FACE_IMAGE_FILE_DIR_NAME = "image";
    private static final String FACE_IMAGE_FILE_NAME = "face.jpg";
    public static final String FACE_IMAGE_FILE_PATH = (Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + AppUtils.getAppPackageName() + File.separator + FACE_IMAGE_FILE_DIR_NAME + File.separator + FACE_IMAGE_FILE_NAME);
    public static final String FACE_LEAVE_MARK_DIR_PATH = (FACE_IMAGE_DIR_PATH + FACE_LEAVE_MARK_FILE_DIR_NAME + File.separator);
    private static final String FACE_LEAVE_MARK_FILE_DIR_NAME = "leave_mark";
    public static final String FACE_MASK_IMAGE_FILE_PATH = (File.separator + "data" + File.separator + SpeechConstants.TYPE_LOCAL + File.separator + "tmp" + File.separator + FACE_IMAGE_FILE_NAME);
    public static final String FACE_THERMOMETRY_DIR_PATH = (FACE_IMAGE_DIR_PATH + FACE_THERMOMETRY_FILE_DIR_NAME + File.separator);
    private static final String FACE_THERMOMETRY_FILE_DIR_NAME = "thermometry";
    public static final String IDLE_BROAD = "idle_broad";
    public static final String IDLE_BROAD_VOICE_DIR_PATH = (Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + AppUtils.getAppPackageName() + File.separator + IDLE_BROAD + File.separator);
    private static final String LOG_CRASH_NAME = "logCrash";
    public static final String LOG_CRASH_PATH = (Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + AppUtils.getAppPackageName() + File.separator + LOG_CRASH_NAME + File.separator);
    private static final String LOG_ERROR_NAME = "logError";
    public static final String LOG_ERROR_PATH = (Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + AppUtils.getAppPackageName() + File.separator + LOG_ERROR_NAME + File.separator);
    private static final String LOG_FILE_NAME = "log";
    public static final String LOG_FILE_PATH = (Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + AppUtils.getAppPackageName() + File.separator + LOG_FILE_NAME + File.separator);
    private static final String LOG_INFO_NAME = "logInfo";
    public static final String LOG_INFO_PATH = (Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + AppUtils.getAppPackageName() + File.separator + LOG_INFO_NAME + File.separator);
    public static final String LOG_TODAY_FILE_NAME = new SimpleDateFormat("MM-dd", Locale.getDefault()).format(new Date());
    private static final String STATS_FILE_DIR_NAME = "stats";
    public static final String STATS_FILE_DIR_PATH = (Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + AppUtils.getAppPackageName() + File.separator + STATS_FILE_DIR_NAME + File.separator);
    private static final String TASK_MUSIC = "Music";
    public static final String TASK_MUSIC_PATH = (Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + AppUtils.getAppPackageName() + File.separator + TASK_MUSIC + File.separator);
    public static final String VIDEO_DIR_PATH = (Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + AppUtils.getAppPackageName() + File.separator + VIDEO_FILE_NAME + File.separator);
    public static final String VIDEO_FILE_NAME = "video";
    public static final String VISITOR_FACE_CODE_FILE_NAME = "visitor.txt";
    public static final String VISITOR_FACE_CODE_FILE_PATH = (FACE_CODE_DIR_PATH + VISITOR_FACE_CODE_FILE_NAME);

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory());
        sb.append(File.separator);
        sb.append(AppUtils.getAppPackageName());
        sb.append(File.separator);
        sb.append("RobotLog");
        APP_PATH = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(Environment.getExternalStorageDirectory().getAbsolutePath());
        sb2.append(File.separator);
        sb2.append(AppUtils.getAppPackageName());
        sb2.append(File.separator);
        sb2.append(FACE_IMAGE_FILE_DIR_NAME);
        sb2.append(File.separator);
        FACE_IMAGE_DIR_PATH = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(FACE_CODE_DIR_PATH);
        sb3.append(EMPLOYEE_FACE_CODE_FILE_NAME);
        EMPLOYEE_FACE_CODE_FILE_PATH = sb3.toString();
    }
}
