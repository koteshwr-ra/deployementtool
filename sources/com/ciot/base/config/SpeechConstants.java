package com.ciot.base.config;

import android.os.Environment;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SpeechConstants {
    public static final String BAIDU_ACCESS_TOKEN = "BAIDU_ACCESS_TOKEN";
    public static final String BAIDU_ACCESS_TOKEN_DUE_TO = "BAIDU_ACCESS_TOKEN_DUE_TO";
    public static final String CLOUD_VOICE_NAME = "CLOUD_VOICE_NAME";
    public static final String DEFINITION_INTENT = "OS571344647.findSomeOne";
    public static final String DEVICE_ID = "DEVICE_ID";
    public static final String Dir_HUANHUAN = (Environment.getExternalStorageDirectory() + "/HuanHuan/");
    public static final String Dir_LOG_TODAY = (Dir_HUANHUAN + File.separator + LOG_TODAY_FILE_NAME);
    public static final String HTTP_UTIL = "HttpUtil";
    public static Boolean IS_CLOSE_GUIDE_CONFIG = true;
    public static Boolean IS_OPEN_CIOT_SERVER = true;
    public static final Boolean IS_OPEN_CIOT_TOPIC = false;
    public static Boolean IS_OPEN_RECONIZE = true;
    public static final String LOCAL_VOICE_NAME = "LOCAL_VOICE_NAME";
    public static final String LOG_TODAY_FILE_NAME = new SimpleDateFormat("MM-dd", Locale.getDefault()).format(new Date());
    public static final String MSGTYPE_UPLOGD_DYNAMICENTITY = "MSGTYPE_UPLOGD_DYNAMICENTITY";
    public static final String MSGTYPE_UPLOGD_SPEAKABLESYNC = "MSGTYPE_UPLOGD_SPEAKABLESYNC";
    public static final String MSGTYPE_UPLOGD_USERWORD = "MSGTYPE_UPLOGD_USERWORD";
    public static final String PITCH = "PITCH";
    public static final String SESSION_STATE_END = "END";
    public static final String SESSION_STATE_IDLE = "IDLE";
    public static final String SESSION_STATE_NOT_UNDERSTAND = "NOT_UNDERSTAND";
    public static final String SESSION_STATE_RUNNING = "RUNNING";
    public static final String SPEECHMANAGER = "SPEECHMANAGER";
    public static final String SPEECH_ENGINE_TYPE = "SPEECH_ENGINE_TYPE";
    public static final String SPEED = "SPEED";
    public static final String SP_IS_CLOSE_IFLYTEK_SWITCH = "SP_IFLYTEK_SWITCH";
    public static final String SP_IS_OPEN_NEAR_FIELD_RECOGNITION = "SP_IS_OPEN_NEAR_FIELD_RECOGNITION";
    public static final int TASK_ACTION_END = 2;
    public static final int TASK_ACTION_START = 1;
    public static final String TASK_TYPE_LOOK_FOR_LOCATION = "look_for_location";
    public static final String TASK_TYPE_LOOK_FOR_SOMEONE = "look_for_someone";
    public static final String TASK_TYPE_VISIT = "visit";
    public static final int TTS_DATA_TYPE_FILE = 1;
    public static final int TTS_DATA_TYPE_TXT = 0;
    public static final String TYPE_LOCAL = "local";
    public static final int TYPE_RECONIZE_TXT = 0;
    public static final int TYPE_UNDERSTAND_JSON = 1;
    public static final String VOLUME = "VOLUME";
}
