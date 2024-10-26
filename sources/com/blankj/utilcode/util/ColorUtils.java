package com.blankj.utilcode.util;

import android.graphics.Color;
import androidx.core.content.ContextCompat;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.ViewCompat;
import com.ciot.base.constant.NetConstant;

public final class ColorUtils {
    public static int setAlphaComponent(int i, float f) {
        return (i & ViewCompat.MEASURED_SIZE_MASK) | (((int) ((f * 255.0f) + 0.5f)) << 24);
    }

    public static int setAlphaComponent(int i, int i2) {
        return (i & ViewCompat.MEASURED_SIZE_MASK) | (i2 << 24);
    }

    public static int setBlueComponent(int i, float f) {
        return (i & InputDeviceCompat.SOURCE_ANY) | ((int) ((f * 255.0f) + 0.5f));
    }

    public static int setBlueComponent(int i, int i2) {
        return (i & InputDeviceCompat.SOURCE_ANY) | i2;
    }

    public static int setGreenComponent(int i, float f) {
        return (i & -65281) | (((int) ((f * 255.0f) + 0.5f)) << 8);
    }

    public static int setGreenComponent(int i, int i2) {
        return (i & -65281) | (i2 << 8);
    }

    public static int setRedComponent(int i, float f) {
        return (i & -16711681) | (((int) ((f * 255.0f) + 0.5f)) << 16);
    }

    public static int setRedComponent(int i, int i2) {
        return (i & -16711681) | (i2 << 16);
    }

    private ColorUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static int getColor(int i) {
        return ContextCompat.getColor(Utils.getApp(), i);
    }

    public static int string2Int(String str) {
        if (str != null) {
            return Color.parseColor(str);
        }
        throw new NullPointerException("Argument 'colorString' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static String int2RgbString(int i) {
        String hexString = Integer.toHexString(i & ViewCompat.MEASURED_SIZE_MASK);
        while (hexString.length() < 6) {
            hexString = NetConstant.PAGE_ID_HOME + hexString;
        }
        return "#" + hexString;
    }

    public static String int2ArgbString(int i) {
        String hexString = Integer.toHexString(i);
        while (hexString.length() < 6) {
            hexString = NetConstant.PAGE_ID_HOME + hexString;
        }
        while (hexString.length() < 8) {
            hexString = "f" + hexString;
        }
        return "#" + hexString;
    }

    public static int getRandomColor() {
        return getRandomColor(true);
    }

    public static int getRandomColor(boolean z) {
        return (z ? ((int) (Math.random() * 256.0d)) << 24 : -16777216) | ((int) (Math.random() * 1.6777216E7d));
    }
}
