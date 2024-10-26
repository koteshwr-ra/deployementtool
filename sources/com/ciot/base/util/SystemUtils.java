package com.ciot.base.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.media.AudioRecord;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.util.TypedValue;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.core.os.EnvironmentCompat;
import java.util.List;

public class SystemUtils {
    public static final int DEFAULT_TASK_NUM = 100;
    private static final String TAG = SystemUtils.class.getSimpleName();
    public static int audioFormat = 2;
    public static int audioSource = 1;
    public static int bufferSizeInBytes = 0;
    public static int channelConfig = 12;
    public static int sampleRateInHz = 44100;

    private void isManagerPasswordRight1(String str, String str2) {
    }

    public static List<ActivityManager.RunningTaskInfo> getRunningTask(Context context) {
        return getRunningTask(context, 100);
    }

    public static List<ActivityManager.RunningTaskInfo> getRunningTask(Context context, int i) {
        if (context != null) {
            return ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
        }
        return null;
    }

    public static boolean isTopActivity(Activity activity) {
        return isTopActivity((Context) activity, (Class<? extends Activity>) activity.getClass());
    }

    public static boolean isTopActivity(Context context, Class<? extends Activity> cls) {
        return isTopActivity(context, cls.getName());
    }

    public static boolean isTopActivity(Context context, String str) {
        List<ActivityManager.RunningTaskInfo> runningTask = getRunningTask(context, 1);
        if (runningTask == null) {
            return false;
        }
        for (ActivityManager.RunningTaskInfo next : runningTask) {
            Log.d("SystemUtils", "isTopActivity:" + next.topActivity.getClassName() + "|" + str);
            if (next.topActivity.getClassName().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAppForgroud(Context context) {
        if (context != null) {
            String packageName = context.getPackageName();
            List<ActivityManager.RunningTaskInfo> runningTask = getRunningTask(context, 1);
            if (runningTask.size() >= 1) {
                return packageName.equalsIgnoreCase(runningTask.get(0).topActivity.getPackageName());
            }
        }
        return false;
    }

    public static boolean isAppBackgroud(Context context) {
        if (context != null) {
            return !context.getPackageName().equalsIgnoreCase(getRunningTask(context, 1).get(0).topActivity.getPackageName());
        }
        return false;
    }

    public static boolean isJingGangRobot() {
        int i = Build.VERSION.SDK_INT;
        String property = getProperty("ro.product.firmware", EnvironmentCompat.MEDIA_UNKNOWN);
        String str = TAG;
        MyLogUtils.Logd(str, "currentapiVersion:" + i + ",systemVersion = " + property);
        String[] split = property.split("\\.");
        boolean z = true;
        String str2 = split[split.length - 1];
        int parseInt = Integer.parseInt(str2);
        String str3 = TAG;
        MyLogUtils.Logd(str3, "versionCode:" + str2 + ",robotType = " + parseInt);
        if ((parseInt > 0 && parseInt < 100) || (parseInt > 99 && parseInt < 200)) {
            z = false;
        } else if (parseInt > 399) {
        }
        String str4 = TAG;
        MyLogUtils.Logd(str4, "isJingGangRobot:" + z);
        return z;
    }

    public static String getProperty(String str, String str2) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", new Class[]{String.class, String.class}).invoke(cls, new Object[]{str, EnvironmentCompat.MEDIA_UNKNOWN});
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable unused) {
        }
        return str2;
    }

    public static boolean isHasAudioPermission(Context context) {
        bufferSizeInBytes = 0;
        bufferSizeInBytes = AudioRecord.getMinBufferSize(sampleRateInHz, channelConfig, audioFormat);
        AudioRecord audioRecord = new AudioRecord(audioSource, sampleRateInHz, channelConfig, audioFormat, bufferSizeInBytes);
        try {
            audioRecord.startRecording();
        } catch (IllegalStateException e) {
            MyLogUtils.Logd("SANBAO", "isHasAudioPermission IllegalStateException");
            e.printStackTrace();
        }
        if (audioRecord.getRecordingState() != 3) {
            return false;
        }
        audioRecord.stop();
        audioRecord.release();
        return true;
    }

    public static Activity scanForActivity(Context context) {
        if (context == null) {
            return null;
        }
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return scanForActivity(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    private static AppCompatActivity getAppCompActivity(Context context) {
        if (context == null) {
            return null;
        }
        if (context instanceof AppCompatActivity) {
            return (AppCompatActivity) context;
        }
        if (context instanceof ContextThemeWrapper) {
            return getAppCompActivity(((ContextThemeWrapper) context).getBaseContext());
        }
        return null;
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int dp2px(Context context, float f) {
        return (int) TypedValue.applyDimension(1, f, context.getResources().getDisplayMetrics());
    }

    public static void savePlayPosition(Context context, String str, long j) {
        context.getSharedPreferences("NICE_VIDEO_PALYER_PLAY_POSITION", 0).edit().putLong(str, j).apply();
    }

    public static long getSavedPlayPosition(Context context, String str) {
        return context.getSharedPreferences("NICE_VIDEO_PALYER_PLAY_POSITION", 0).getLong(str, 0);
    }

    public boolean isSystemManager(String str) {
        Cursor cursor;
        try {
            cursor = ContextUtil.getContext().getContentResolver().query(Uri.parse("content://com.qihancloud.accountmanager/verifyadmin"), (String[]) null, (String) null, new String[]{str}, (String) null);
            if (cursor != null) {
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            cursor = null;
        }
        return cursor != null && cursor.moveToNext() && cursor.getInt(0) == 1;
    }

    public static boolean isManagerPasswordRight(String str) {
        return "ciot2020".equals(str);
    }

    public static boolean isZh(Context context) {
        return context.getResources().getConfiguration().locale.getLanguage().endsWith("zh");
    }
}
