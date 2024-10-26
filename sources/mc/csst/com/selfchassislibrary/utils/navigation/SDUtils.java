package mc.csst.com.selfchassislibrary.utils.navigation;

import android.content.Context;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.text.TextUtils;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class SDUtils {
    private static final String APP_ROOT_SAVE_PATH = "servicerobot";
    private static final String property = File.separator;

    public static String getLocalRootSavePathDir(Context context, String str) {
        List<String> sDCardPaths;
        String sDCardPath = getSDCardPath();
        if ((!isSDCardEnable(context) || TextUtils.isEmpty(sDCardPath)) && (sDCardPaths = getSDCardPaths(context)) != null && sDCardPaths.size() > 0 && !TextUtils.isEmpty(sDCardPaths.get(0))) {
            sDCardPath = sDCardPaths.get(0);
        }
        if (TextUtils.isEmpty(sDCardPath)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(sDCardPath);
        sb.append(property);
        sb.append(APP_ROOT_SAVE_PATH);
        if (str != null && str.length() > 0) {
            sb.append(property);
            sb.append(str);
        }
        return sb.toString();
    }

    public static String getFileRootPath(Context context, String str) {
        List<String> sDCardPaths;
        String sDCardPath = getSDCardPath();
        if ((!isSDCardEnable(context) || TextUtils.isEmpty(sDCardPath)) && (sDCardPaths = getSDCardPaths(context)) != null && sDCardPaths.size() > 0 && !TextUtils.isEmpty(sDCardPaths.get(0))) {
            sDCardPath = sDCardPaths.get(0);
        }
        if (TextUtils.isEmpty(sDCardPath)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(sDCardPath);
        sb.append(property);
        if (str != null && str.length() > 0) {
            sb.append(str);
        }
        return sb.toString();
    }

    private static boolean isMounted() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public static String getSDCardPath() {
        if (isMounted()) {
            return Environment.getExternalStorageDirectory().getPath();
        }
        return null;
    }

    public static boolean isSDCardEnable(Context context) {
        return !getSDCardPaths(context).isEmpty();
    }

    public static List<String> getSDCardPaths(Context context) {
        StorageManager storageManager = (StorageManager) context.getApplicationContext().getSystemService("storage");
        ArrayList arrayList = new ArrayList();
        try {
            Method method = StorageManager.class.getMethod("getVolumePaths", new Class[0]);
            method.setAccessible(true);
            return Arrays.asList((String[]) method.invoke(storageManager, new Object[0]));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return arrayList;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return arrayList;
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
            return arrayList;
        }
    }
}
