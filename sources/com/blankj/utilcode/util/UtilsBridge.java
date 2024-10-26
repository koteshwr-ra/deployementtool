package com.blankj.utilcode.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Parcelable;
import android.view.View;
import com.blankj.utilcode.util.ShellUtils;
import com.blankj.utilcode.util.Utils;
import com.google.gson.Gson;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

class UtilsBridge {
    UtilsBridge() {
    }

    static void init(Application application) {
        UtilsActivityLifecycleImpl.INSTANCE.init(application);
    }

    static void unInit(Application application) {
        UtilsActivityLifecycleImpl.INSTANCE.unInit(application);
    }

    static void preLoad() {
        preLoad(AdaptScreenUtils.getPreLoadRunnable());
    }

    static Activity getTopActivity() {
        return UtilsActivityLifecycleImpl.INSTANCE.getTopActivity();
    }

    static void addOnAppStatusChangedListener(Utils.OnAppStatusChangedListener onAppStatusChangedListener) {
        UtilsActivityLifecycleImpl.INSTANCE.addOnAppStatusChangedListener(onAppStatusChangedListener);
    }

    static void removeOnAppStatusChangedListener(Utils.OnAppStatusChangedListener onAppStatusChangedListener) {
        UtilsActivityLifecycleImpl.INSTANCE.removeOnAppStatusChangedListener(onAppStatusChangedListener);
    }

    static void addActivityLifecycleCallbacks(Activity activity, Utils.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        UtilsActivityLifecycleImpl.INSTANCE.addActivityLifecycleCallbacks(activity, activityLifecycleCallbacks);
    }

    static void removeActivityLifecycleCallbacks(Activity activity) {
        UtilsActivityLifecycleImpl.INSTANCE.removeActivityLifecycleCallbacks(activity);
    }

    static void removeActivityLifecycleCallbacks(Activity activity, Utils.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        UtilsActivityLifecycleImpl.INSTANCE.removeActivityLifecycleCallbacks(activity, activityLifecycleCallbacks);
    }

    static List<Activity> getActivityList() {
        return UtilsActivityLifecycleImpl.INSTANCE.getActivityList();
    }

    static Application getApplicationByReflect() {
        return UtilsActivityLifecycleImpl.INSTANCE.getApplicationByReflect();
    }

    static boolean isActivityAlive(Activity activity) {
        return ActivityUtils.isActivityAlive(activity);
    }

    static String getLauncherActivity() {
        return ActivityUtils.getLauncherActivity();
    }

    static String getLauncherActivity(String str) {
        return ActivityUtils.getLauncherActivity(str);
    }

    static Activity getActivityByContext(Context context) {
        return ActivityUtils.getActivityByContext(context);
    }

    static void startHomeActivity() {
        ActivityUtils.startHomeActivity();
    }

    static void finishAllActivities() {
        ActivityUtils.finishAllActivities();
    }

    static Context getTopActivityOrApp() {
        if (!AppUtils.isAppForeground()) {
            return Utils.getApp();
        }
        Activity topActivity = getTopActivity();
        return topActivity == null ? Utils.getApp() : topActivity;
    }

    static boolean isAppRunning(String str) {
        if (str != null) {
            return AppUtils.isAppRunning(str);
        }
        throw new NullPointerException("Argument 'pkgName' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    static boolean isAppInstalled(String str) {
        return AppUtils.isAppInstalled(str);
    }

    static String getAppVersionName() {
        return AppUtils.getAppVersionName();
    }

    static int getAppVersionCode() {
        return AppUtils.getAppVersionCode();
    }

    static boolean isAppDebug() {
        return AppUtils.isAppDebug();
    }

    static int getStatusBarHeight() {
        return BarUtils.getStatusBarHeight();
    }

    static int getNavBarHeight() {
        return BarUtils.getNavBarHeight();
    }

    static String bytes2HexString(byte[] bArr) {
        return ConvertUtils.bytes2HexString(bArr);
    }

    static byte[] hexString2Bytes(String str) {
        return ConvertUtils.hexString2Bytes(str);
    }

    static byte[] string2Bytes(String str) {
        return ConvertUtils.string2Bytes(str);
    }

    static String bytes2String(byte[] bArr) {
        return ConvertUtils.bytes2String(bArr);
    }

    static byte[] jsonObject2Bytes(JSONObject jSONObject) {
        return ConvertUtils.jsonObject2Bytes(jSONObject);
    }

    static JSONObject bytes2JSONObject(byte[] bArr) {
        return ConvertUtils.bytes2JSONObject(bArr);
    }

    static byte[] jsonArray2Bytes(JSONArray jSONArray) {
        return ConvertUtils.jsonArray2Bytes(jSONArray);
    }

    static JSONArray bytes2JSONArray(byte[] bArr) {
        return ConvertUtils.bytes2JSONArray(bArr);
    }

    static byte[] parcelable2Bytes(Parcelable parcelable) {
        return ConvertUtils.parcelable2Bytes(parcelable);
    }

    static <T> T bytes2Parcelable(byte[] bArr, Parcelable.Creator<T> creator) {
        return ConvertUtils.bytes2Parcelable(bArr, creator);
    }

    static byte[] serializable2Bytes(Serializable serializable) {
        return ConvertUtils.serializable2Bytes(serializable);
    }

    static Object bytes2Object(byte[] bArr) {
        return ConvertUtils.bytes2Object(bArr);
    }

    static String byte2FitMemorySize(long j) {
        return ConvertUtils.byte2FitMemorySize(j);
    }

    static byte[] inputStream2Bytes(InputStream inputStream) {
        return ConvertUtils.inputStream2Bytes(inputStream);
    }

    static ByteArrayOutputStream input2OutputStream(InputStream inputStream) {
        return ConvertUtils.input2OutputStream(inputStream);
    }

    static List<String> inputStream2Lines(InputStream inputStream, String str) {
        return ConvertUtils.inputStream2Lines(inputStream, str);
    }

    static byte[] base64Encode(byte[] bArr) {
        return EncodeUtils.base64Encode(bArr);
    }

    static byte[] base64Decode(byte[] bArr) {
        return EncodeUtils.base64Decode(bArr);
    }

    static byte[] hashTemplate(byte[] bArr, String str) {
        return EncryptUtils.hashTemplate(bArr, str);
    }

    static boolean writeFileFromBytes(File file, byte[] bArr) {
        return FileIOUtils.writeFileFromBytesByChannel(file, bArr, true);
    }

    static byte[] readFile2Bytes(File file) {
        return FileIOUtils.readFile2BytesByChannel(file);
    }

    static boolean writeFileFromString(String str, String str2, boolean z) {
        return FileIOUtils.writeFileFromString(str, str2, z);
    }

    static boolean writeFileFromIS(String str, InputStream inputStream) {
        return FileIOUtils.writeFileFromIS(str, inputStream);
    }

    static boolean isFileExists(File file) {
        return FileUtils.isFileExists(file);
    }

    static File getFileByPath(String str) {
        return FileUtils.getFileByPath(str);
    }

    static boolean deleteAllInDir(File file) {
        return FileUtils.deleteAllInDir(file);
    }

    static boolean createOrExistsFile(File file) {
        return FileUtils.createOrExistsFile(file);
    }

    static boolean createOrExistsDir(File file) {
        return FileUtils.createOrExistsDir(file);
    }

    static boolean createFileByDeleteOldFile(File file) {
        return FileUtils.createFileByDeleteOldFile(file);
    }

    static long getFsTotalSize(String str) {
        return FileUtils.getFsTotalSize(str);
    }

    static long getFsAvailableSize(String str) {
        return FileUtils.getFsAvailableSize(str);
    }

    static void notifySystemToScan(File file) {
        FileUtils.notifySystemToScan(file);
    }

    static String toJson(Object obj) {
        return GsonUtils.toJson(obj);
    }

    static <T> T fromJson(String str, Type type) {
        return GsonUtils.fromJson(str, type);
    }

    static Gson getGson4LogUtils() {
        return GsonUtils.getGson4LogUtils();
    }

    static byte[] bitmap2Bytes(Bitmap bitmap) {
        return ImageUtils.bitmap2Bytes(bitmap);
    }

    static byte[] bitmap2Bytes(Bitmap bitmap, Bitmap.CompressFormat compressFormat, int i) {
        return ImageUtils.bitmap2Bytes(bitmap, compressFormat, i);
    }

    static Bitmap bytes2Bitmap(byte[] bArr) {
        return ImageUtils.bytes2Bitmap(bArr);
    }

    static byte[] drawable2Bytes(Drawable drawable) {
        return ImageUtils.drawable2Bytes(drawable);
    }

    static byte[] drawable2Bytes(Drawable drawable, Bitmap.CompressFormat compressFormat, int i) {
        return ImageUtils.drawable2Bytes(drawable, compressFormat, i);
    }

    static Drawable bytes2Drawable(byte[] bArr) {
        return ImageUtils.bytes2Drawable(bArr);
    }

    static Bitmap view2Bitmap(View view) {
        return ImageUtils.view2Bitmap(view);
    }

    static Bitmap drawable2Bitmap(Drawable drawable) {
        return ImageUtils.drawable2Bitmap(drawable);
    }

    static Drawable bitmap2Drawable(Bitmap bitmap) {
        return ImageUtils.bitmap2Drawable(bitmap);
    }

    static boolean isIntentAvailable(Intent intent) {
        return IntentUtils.isIntentAvailable(intent);
    }

    static Intent getLaunchAppIntent(String str) {
        return IntentUtils.getLaunchAppIntent(str);
    }

    static Intent getInstallAppIntent(File file) {
        return IntentUtils.getInstallAppIntent(file);
    }

    static Intent getInstallAppIntent(Uri uri) {
        return IntentUtils.getInstallAppIntent(uri);
    }

    static Intent getUninstallAppIntent(String str) {
        return IntentUtils.getUninstallAppIntent(str);
    }

    static Intent getDialIntent(String str) {
        return IntentUtils.getDialIntent(str);
    }

    static Intent getCallIntent(String str) {
        return IntentUtils.getCallIntent(str);
    }

    static Intent getSendSmsIntent(String str, String str2) {
        return IntentUtils.getSendSmsIntent(str, str2);
    }

    static Intent getLaunchAppDetailsSettingsIntent(String str, boolean z) {
        return IntentUtils.getLaunchAppDetailsSettingsIntent(str, z);
    }

    static String formatJson(String str) {
        return JsonUtils.formatJson(str);
    }

    static void fixSoftInputLeaks(Activity activity) {
        KeyboardUtils.fixSoftInputLeaks(activity);
    }

    static void applyLanguage(Activity activity) {
        LanguageUtils.applyLanguage(activity);
    }

    static boolean isGranted(String... strArr) {
        return PermissionUtils.isGranted(strArr);
    }

    static boolean isGrantedDrawOverlays() {
        return PermissionUtils.isGrantedDrawOverlays();
    }

    static boolean isMainProcess() {
        return ProcessUtils.isMainProcess();
    }

    static String getForegroundProcessName() {
        return ProcessUtils.getForegroundProcessName();
    }

    static String getCurrentProcessName() {
        return ProcessUtils.getCurrentProcessName();
    }

    static String getSDCardPathByEnvironment() {
        return SDCardUtils.getSDCardPathByEnvironment();
    }

    static boolean isSDCardEnableByEnvironment() {
        return SDCardUtils.isSDCardEnableByEnvironment();
    }

    static boolean isServiceRunning(String str) {
        return ServiceUtils.isServiceRunning(str);
    }

    static ShellUtils.CommandResult execCmd(String str, boolean z) {
        return ShellUtils.execCmd(str, z);
    }

    static int dp2px(float f) {
        return SizeUtils.dp2px(f);
    }

    static int px2dp(float f) {
        return SizeUtils.px2dp(f);
    }

    static int sp2px(float f) {
        return SizeUtils.sp2px(f);
    }

    static int px2sp(float f) {
        return SizeUtils.px2sp(f);
    }

    static SPUtils getSpUtils4Utils() {
        return SPUtils.getInstance("Utils");
    }

    static boolean isSpace(String str) {
        return StringUtils.isSpace(str);
    }

    static boolean equals(CharSequence charSequence, CharSequence charSequence2) {
        return StringUtils.equals(charSequence, charSequence2);
    }

    static <T> Utils.Task<T> doAsync(Utils.Task<T> task) {
        ThreadUtils.getCachedPool().execute(task);
        return task;
    }

    static void runOnUiThread(Runnable runnable) {
        ThreadUtils.runOnUiThread(runnable);
    }

    static void runOnUiThreadDelayed(Runnable runnable, long j) {
        ThreadUtils.runOnUiThreadDelayed(runnable, j);
    }

    static String getFullStackTrace(Throwable th) {
        return ThrowableUtils.getFullStackTrace(th);
    }

    static String millis2FitTimeSpan(long j, int i) {
        return TimeUtils.millis2FitTimeSpan(j, i);
    }

    static void toastShowShort(CharSequence charSequence) {
        ToastUtils.showShort(charSequence);
    }

    static void toastCancel() {
        ToastUtils.cancel();
    }

    private static void preLoad(Runnable... runnableArr) {
        for (Runnable execute : runnableArr) {
            ThreadUtils.getCachedPool().execute(execute);
        }
    }

    static Uri file2Uri(File file) {
        return UriUtils.file2Uri(file);
    }

    static File uri2File(Uri uri) {
        return UriUtils.uri2File(uri);
    }
}
