package com.tencent.smtt.sdk.ui.dialog;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class c {
    private static float a = -1.0f;
    private static int b = -1;
    private static int c = -1;

    public static int a(Context context) {
        if (b == -1) {
            b(context);
        }
        return b;
    }

    public static int a(Context context, float f) {
        return b(context, (f * 160.0f) / 320.0f);
    }

    public static int b(Context context, float f) {
        if (a == -1.0f) {
            b(context);
        }
        return (int) ((f * a) + 0.5f);
    }

    private static void b(Context context) {
        if (a < 0.0f) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            a = displayMetrics.density;
            b = displayMetrics.heightPixels;
        }
    }
}
