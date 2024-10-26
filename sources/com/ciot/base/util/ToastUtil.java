package com.ciot.base.util;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.drz.base.R;
import java.lang.reflect.Field;

public class ToastUtil {
    private static final String FIELD_NAME_HANDLER = "mHandler";
    private static final String FIELD_NAME_TN = "mTN";
    static boolean isToast = true;
    private static Toast mToast = null;
    private static Field sField_TN = null;
    private static Field sField_TN_Handler = null;
    private static boolean sIsHookFieldInit = false;

    public static void showLong(Context context, String str) {
        if (isToast) {
            Toast makeText = Toast.makeText(context, str, 1);
            makeText.setGravity(48, 0, 10);
            makeText.show();
        }
    }

    public static void showShort(Context context, String str) {
        if (isToast) {
            Toast makeText = Toast.makeText(context, str, 0);
            makeText.setGravity(48, 0, 10);
            makeText.show();
        }
    }

    public static void showText2(Context context, String str) {
        if (context != null) {
            try {
                if (mToast != null) {
                    mToast.cancel();
                }
                mToast = new Toast(context);
                LinearLayout linearLayout = new LinearLayout(context);
                linearLayout.setOrientation(0);
                linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
                linearLayout.setBackgroundResource(R.drawable.toast_bg);
                TextView textView = new TextView(context);
                textView.setText(str);
                textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
                textView.setPadding(60, 57, 60, 57);
                textView.setTextSize(0, 36.0f);
                textView.setTextColor(-1);
                linearLayout.addView(textView);
                mToast.setView(linearLayout);
                mToast.setDuration(0);
                mToast.setGravity(17, 0, 0);
                if (isNeedHook()) {
                    hookToast(mToast);
                }
                mToast.show();
            } catch (Exception e) {
                e.printStackTrace();
                MyLogUtils.Logd("SPEECHMANAGER", "ToastUtil Exception:" + e);
            }
        }
    }

    public static void cancleToast() {
        Toast toast = mToast;
        if (toast != null) {
            toast.cancel();
        }
    }

    private static class SafelyHandlerWrapper extends Handler {
        private Handler originHandler;

        public SafelyHandlerWrapper(Handler handler) {
            this.originHandler = handler;
        }

        public void dispatchMessage(Message message) {
            try {
                super.dispatchMessage(message);
            } catch (Exception e) {
                MyLogUtils.Logd("SPEECHMANAGER", "ToastUtil Exception:" + e);
            }
        }

        public void handleMessage(Message message) {
            Handler handler = this.originHandler;
            if (handler != null) {
                handler.handleMessage(message);
            }
        }
    }

    private static void hookToast(Toast toast) {
        try {
            if (!sIsHookFieldInit) {
                Field declaredField = Toast.class.getDeclaredField(FIELD_NAME_TN);
                sField_TN = declaredField;
                declaredField.setAccessible(true);
                Field declaredField2 = sField_TN.getType().getDeclaredField(FIELD_NAME_HANDLER);
                sField_TN_Handler = declaredField2;
                declaredField2.setAccessible(true);
                sIsHookFieldInit = true;
            }
            Object obj = sField_TN.get(toast);
            sField_TN_Handler.set(obj, new SafelyHandlerWrapper((Handler) sField_TN_Handler.get(obj)));
        } catch (Exception e) {
            MyLogUtils.Logd("SPEECHMANAGER", "ToastUtil Exception:" + e);
        }
    }

    private static boolean isNeedHook() {
        return Build.VERSION.SDK_INT == 25 || Build.VERSION.SDK_INT == 24;
    }
}
