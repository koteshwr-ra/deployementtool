package com.tencent.mars.logger;

import android.os.Environment;
import com.blankj.utilcode.util.AppUtils;
import java.io.File;

public interface LoggerConfig {
    public static final int APPEDNER_MODE = 0;
    public static final int CACHE_DAYS = 0;
    public static final String CACHE_DIR = "";
    public static final int COMPRESS_LEVEL = 0;
    public static final int COMPRESS_MODE = 0;
    public static final boolean CONSOLE_LOG_OPEN = true;
    public static final String LOG_DIR = (Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + AppUtils.getAppPackageName() + File.separator + "RobotLog" + File.separator + LOG_FILE_NAME + File.separator);
    public static final String LOG_FILE_NAME = "xlog";
    public static final int LOG_LEVEL = 1;
    public static final long MAX_ALIVE_TIME = 259200;
    public static final long MAX_FILE_SIZE = 52428800;
    public static final String NAME_PREFIX = "SentryRobot";
    public static final String PUB_KEY = "ef8abbd5cb8c6b83d8662cc63143d912bd8ab2ff9039aa8b39c71b7c91db87d96455f0068e2d0853503482070b2494d7afdd22b746e8d0750260cfb34462f224";
}
