package com.blankj.utilcode.util;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public final class ScreenUtils {
    private ScreenUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static int getScreenWidth() {
        WindowManager windowManager = (WindowManager) Utils.getApp().getSystemService("window");
        if (windowManager == null) {
            return -1;
        }
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= 17) {
            windowManager.getDefaultDisplay().getRealSize(point);
        } else {
            windowManager.getDefaultDisplay().getSize(point);
        }
        return point.x;
    }

    public static int getScreenHeight() {
        WindowManager windowManager = (WindowManager) Utils.getApp().getSystemService("window");
        if (windowManager == null) {
            return -1;
        }
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= 17) {
            windowManager.getDefaultDisplay().getRealSize(point);
        } else {
            windowManager.getDefaultDisplay().getSize(point);
        }
        return point.y;
    }

    public static int getAppScreenWidth() {
        WindowManager windowManager = (WindowManager) Utils.getApp().getSystemService("window");
        if (windowManager == null) {
            return -1;
        }
        Point point = new Point();
        windowManager.getDefaultDisplay().getSize(point);
        return point.x;
    }

    public static int getAppScreenHeight() {
        WindowManager windowManager = (WindowManager) Utils.getApp().getSystemService("window");
        if (windowManager == null) {
            return -1;
        }
        Point point = new Point();
        windowManager.getDefaultDisplay().getSize(point);
        return point.y;
    }

    public static float getScreenDensity() {
        return Resources.getSystem().getDisplayMetrics().density;
    }

    public static int getScreenDensityDpi() {
        return Resources.getSystem().getDisplayMetrics().densityDpi;
    }

    public static void setFullScreen(Activity activity) {
        if (activity != null) {
            activity.getWindow().addFlags(1024);
            return;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void setNonFullScreen(Activity activity) {
        if (activity != null) {
            activity.getWindow().clearFlags(1024);
            return;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void toggleFullScreen(Activity activity) {
        if (activity != null) {
            boolean isFullScreen = isFullScreen(activity);
            Window window = activity.getWindow();
            if (isFullScreen) {
                window.clearFlags(1024);
            } else {
                window.addFlags(1024);
            }
        } else {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static boolean isFullScreen(Activity activity) {
        if (activity != null) {
            return (activity.getWindow().getAttributes().flags & 1024) == 1024;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void setLandscape(Activity activity) {
        if (activity != null) {
            activity.setRequestedOrientation(0);
            return;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void setPortrait(Activity activity) {
        if (activity != null) {
            activity.setRequestedOrientation(1);
            return;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static boolean isLandscape() {
        return Utils.getApp().getResources().getConfiguration().orientation == 2;
    }

    public static boolean isPortrait() {
        return Utils.getApp().getResources().getConfiguration().orientation == 1;
    }

    public static int getScreenRotation(Activity activity) {
        if (activity != null) {
            int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
            if (rotation == 1) {
                return 90;
            }
            if (rotation != 2) {
                return rotation != 3 ? 0 : 270;
            }
            return 180;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static Bitmap screenShot(Activity activity) {
        if (activity != null) {
            return screenShot(activity, false);
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static Bitmap screenShot(Activity activity, boolean z) {
        Bitmap bitmap;
        if (activity != null) {
            View decorView = activity.getWindow().getDecorView();
            boolean isDrawingCacheEnabled = decorView.isDrawingCacheEnabled();
            boolean willNotCacheDrawing = decorView.willNotCacheDrawing();
            decorView.setDrawingCacheEnabled(true);
            decorView.setWillNotCacheDrawing(false);
            Bitmap drawingCache = decorView.getDrawingCache();
            if (drawingCache == null) {
                decorView.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
                decorView.layout(0, 0, decorView.getMeasuredWidth(), decorView.getMeasuredHeight());
                decorView.buildDrawingCache();
                drawingCache = Bitmap.createBitmap(decorView.getDrawingCache());
            }
            if (drawingCache == null) {
                return null;
            }
            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            if (z) {
                Resources resources = activity.getResources();
                int dimensionPixelSize = resources.getDimensionPixelSize(resources.getIdentifier("status_bar_height", "dimen", "android"));
                bitmap = Bitmap.createBitmap(drawingCache, 0, dimensionPixelSize, displayMetrics.widthPixels, displayMetrics.heightPixels - dimensionPixelSize);
            } else {
                bitmap = Bitmap.createBitmap(drawingCache, 0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
            }
            decorView.destroyDrawingCache();
            decorView.setWillNotCacheDrawing(willNotCacheDrawing);
            decorView.setDrawingCacheEnabled(isDrawingCacheEnabled);
            return bitmap;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static boolean isScreenLock() {
        KeyguardManager keyguardManager = (KeyguardManager) Utils.getApp().getSystemService("keyguard");
        if (keyguardManager == null) {
            return false;
        }
        return keyguardManager.inKeyguardRestrictedInputMode();
    }

    public static void setSleepDuration(int i) {
        Settings.System.putInt(Utils.getApp().getContentResolver(), "screen_off_timeout", i);
    }

    public static int getSleepDuration() {
        try {
            return Settings.System.getInt(Utils.getApp().getContentResolver(), "screen_off_timeout");
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            return -123;
        }
    }
}
