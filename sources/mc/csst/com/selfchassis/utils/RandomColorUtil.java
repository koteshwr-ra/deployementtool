package mc.csst.com.selfchassis.utils;

import android.graphics.Color;
import com.ciot.base.constant.NetConstant;
import java.util.HashMap;
import java.util.Random;

public class RandomColorUtil {
    private static String LastColor = "";
    private static HashMap<String, Integer> colorMap = new HashMap<>();

    public static String getRandColor() {
        Random random = new Random();
        int nextInt = random.nextInt(256);
        int nextInt2 = random.nextInt(256);
        int nextInt3 = random.nextInt(256);
        if ((((float) nextInt) * 0.299f) + (((float) nextInt2) * 0.587f) + (((float) nextInt3) * 0.114f) >= 192.0f) {
            return getRandColor();
        }
        String upperCase = Integer.toHexString(nextInt).toUpperCase();
        String upperCase2 = Integer.toHexString(nextInt2).toUpperCase();
        String upperCase3 = Integer.toHexString(nextInt3).toUpperCase();
        if (upperCase.length() == 1) {
            upperCase = NetConstant.PAGE_ID_HOME + upperCase;
        }
        if (upperCase2.length() == 1) {
            upperCase2 = NetConstant.PAGE_ID_HOME + upperCase2;
        }
        if (upperCase3.length() == 1) {
            upperCase3 = NetConstant.PAGE_ID_HOME + upperCase3;
        }
        String str = "#" + upperCase + upperCase2 + upperCase3;
        if (LastColor.equals(str)) {
            return getRandColor();
        }
        LastColor = str;
        return str;
    }

    public static int getRandomColor(String str) {
        if (colorMap.get(str) == null) {
            Random random = new Random();
            int nextInt = random.nextInt(256);
            int nextInt2 = random.nextInt(256);
            int nextInt3 = random.nextInt(256);
            if ((((float) nextInt) * 0.299f) + (((float) nextInt2) * 0.587f) + (((float) nextInt3) * 0.114f) >= 192.0f) {
                return getRandomColor(str);
            }
            int rgb = Color.rgb(nextInt, nextInt2, nextInt3);
            if (rgb == -16424707) {
                return getRandomColor(str);
            }
            boolean z = false;
            for (Integer intValue : colorMap.values()) {
                if (Math.abs(intValue.intValue() - rgb) < 1000) {
                    z = true;
                }
            }
            if (z) {
                return getRandomColor(str);
            }
            colorMap.put(str, Integer.valueOf(rgb));
        }
        return colorMap.get(str).intValue();
    }
}
