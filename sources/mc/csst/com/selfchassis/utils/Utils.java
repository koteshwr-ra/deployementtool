package mc.csst.com.selfchassis.utils;

public class Utils {
    private static final long MIN_CLICK_DELAY_TIME = 1000;
    private static long lastClickTime;

    public static boolean isFastClick() {
        return isFastClick(1000);
    }

    public static boolean isFastClick(long j) {
        long currentTimeMillis = System.currentTimeMillis();
        boolean z = currentTimeMillis - lastClickTime >= j;
        lastClickTime = currentTimeMillis;
        return z;
    }
}
