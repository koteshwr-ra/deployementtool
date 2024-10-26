package com.blankj.utilcode.util;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import java.lang.reflect.Field;

public final class KeyboardUtils {
    private static final int TAG_ON_GLOBAL_LAYOUT_LISTENER = -8;
    private static long millis;
    private static int sDecorViewDelta;

    public interface OnSoftInputChangedListener {
        void onSoftInputChanged(int i);
    }

    private KeyboardUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void showSoftInput() {
        InputMethodManager inputMethodManager = (InputMethodManager) Utils.getApp().getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.toggleSoftInput(2, 1);
        }
    }

    public static void showSoftInput(Activity activity) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (!isSoftInputVisible(activity)) {
            toggleSoftInput();
        }
    }

    public static void showSoftInput(View view) {
        if (view != null) {
            showSoftInput(view, 0);
            return;
        }
        throw new NullPointerException("Argument 'view' of type View (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void showSoftInput(View view, int i) {
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) Utils.getApp().getSystemService("input_method");
            if (inputMethodManager != null) {
                view.setFocusable(true);
                view.setFocusableInTouchMode(true);
                view.requestFocus();
                inputMethodManager.showSoftInput(view, i, new ResultReceiver(new Handler()) {
                    /* access modifiers changed from: protected */
                    public void onReceiveResult(int i, Bundle bundle) {
                        if (i == 1 || i == 3) {
                            KeyboardUtils.toggleSoftInput();
                        }
                    }
                });
                inputMethodManager.toggleSoftInput(2, 1);
                return;
            }
            return;
        }
        throw new NullPointerException("Argument 'view' of type View (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void hideSoftInput(Activity activity) {
        if (activity != null) {
            hideSoftInput(activity.getWindow());
            return;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void hideSoftInput(Window window) {
        if (window != null) {
            View currentFocus = window.getCurrentFocus();
            if (currentFocus == null) {
                View decorView = window.getDecorView();
                View findViewWithTag = decorView.findViewWithTag("keyboardTagView");
                if (findViewWithTag == null) {
                    findViewWithTag = new EditText(window.getContext());
                    findViewWithTag.setTag("keyboardTagView");
                    ((ViewGroup) decorView).addView(findViewWithTag, 0, 0);
                }
                currentFocus = findViewWithTag;
                currentFocus.requestFocus();
            }
            hideSoftInput(currentFocus);
            return;
        }
        throw new NullPointerException("Argument 'window' of type Window (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void hideSoftInput(View view) {
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) Utils.getApp().getSystemService("input_method");
            if (inputMethodManager != null) {
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                return;
            }
            return;
        }
        throw new NullPointerException("Argument 'view' of type View (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void hideSoftInputByToggle(Activity activity) {
        long currentTimeMillis = System.currentTimeMillis();
        if (Math.abs(currentTimeMillis - millis) > 500 && isSoftInputVisible(activity)) {
            toggleSoftInput();
        }
        millis = currentTimeMillis;
    }

    public static void toggleSoftInput() {
        InputMethodManager inputMethodManager = (InputMethodManager) Utils.getApp().getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.toggleSoftInput(0, 0);
        }
    }

    public static boolean isSoftInputVisible(Activity activity) {
        if (activity != null) {
            return getDecorViewInvisibleHeight(activity.getWindow()) > 0;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    /* access modifiers changed from: private */
    public static int getDecorViewInvisibleHeight(Window window) {
        if (window != null) {
            View decorView = window.getDecorView();
            Rect rect = new Rect();
            decorView.getWindowVisibleDisplayFrame(rect);
            Log.d("KeyboardUtils", "getDecorViewInvisibleHeight: " + (decorView.getBottom() - rect.bottom));
            int abs = Math.abs(decorView.getBottom() - rect.bottom);
            if (abs > UtilsBridge.getNavBarHeight() + UtilsBridge.getStatusBarHeight()) {
                return abs - sDecorViewDelta;
            }
            sDecorViewDelta = abs;
            return 0;
        }
        throw new NullPointerException("Argument 'window' of type Window (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void registerSoftInputChangedListener(Activity activity, OnSoftInputChangedListener onSoftInputChangedListener) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (onSoftInputChangedListener != null) {
            registerSoftInputChangedListener(activity.getWindow(), onSoftInputChangedListener);
        } else {
            throw new NullPointerException("Argument 'listener' of type OnSoftInputChangedListener (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void registerSoftInputChangedListener(final Window window, final OnSoftInputChangedListener onSoftInputChangedListener) {
        if (window == null) {
            throw new NullPointerException("Argument 'window' of type Window (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (onSoftInputChangedListener != null) {
            if ((window.getAttributes().flags & 512) != 0) {
                window.clearFlags(512);
            }
            FrameLayout frameLayout = (FrameLayout) window.findViewById(16908290);
            final int[] iArr = {getDecorViewInvisibleHeight(window)};
            AnonymousClass2 r2 = new ViewTreeObserver.OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    int access$000 = KeyboardUtils.getDecorViewInvisibleHeight(window);
                    if (iArr[0] != access$000) {
                        onSoftInputChangedListener.onSoftInputChanged(access$000);
                        iArr[0] = access$000;
                    }
                }
            };
            frameLayout.getViewTreeObserver().addOnGlobalLayoutListener(r2);
            frameLayout.setTag(-8, r2);
        } else {
            throw new NullPointerException("Argument 'listener' of type OnSoftInputChangedListener (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void unregisterSoftInputChangedListener(Window window) {
        if (window != null) {
            FrameLayout frameLayout = (FrameLayout) window.findViewById(16908290);
            Object tag = frameLayout.getTag(-8);
            if ((tag instanceof ViewTreeObserver.OnGlobalLayoutListener) && Build.VERSION.SDK_INT >= 16) {
                frameLayout.getViewTreeObserver().removeOnGlobalLayoutListener((ViewTreeObserver.OnGlobalLayoutListener) tag);
                return;
            }
            return;
        }
        throw new NullPointerException("Argument 'window' of type Window (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void fixAndroidBug5497(Activity activity) {
        if (activity != null) {
            fixAndroidBug5497(activity.getWindow());
            return;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void fixAndroidBug5497(final Window window) {
        if (window != null) {
            window.setSoftInputMode(window.getAttributes().softInputMode & -17);
            FrameLayout frameLayout = (FrameLayout) window.findViewById(16908290);
            final View childAt = frameLayout.getChildAt(0);
            final int paddingBottom = childAt.getPaddingBottom();
            final int[] iArr = {getContentViewInvisibleHeight(window)};
            frameLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    int access$100 = KeyboardUtils.getContentViewInvisibleHeight(window);
                    if (iArr[0] != access$100) {
                        View view = childAt;
                        view.setPadding(view.getPaddingLeft(), childAt.getPaddingTop(), childAt.getPaddingRight(), paddingBottom + KeyboardUtils.getDecorViewInvisibleHeight(window));
                        iArr[0] = access$100;
                    }
                }
            });
            return;
        }
        throw new NullPointerException("Argument 'window' of type Window (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    /* access modifiers changed from: private */
    public static int getContentViewInvisibleHeight(Window window) {
        View findViewById = window.findViewById(16908290);
        if (findViewById == null) {
            return 0;
        }
        Rect rect = new Rect();
        findViewById.getWindowVisibleDisplayFrame(rect);
        Log.d("KeyboardUtils", "getContentViewInvisibleHeight: " + (findViewById.getBottom() - rect.bottom));
        int abs = Math.abs(findViewById.getBottom() - rect.bottom);
        if (abs <= UtilsBridge.getStatusBarHeight() + UtilsBridge.getNavBarHeight()) {
            return 0;
        }
        return abs;
    }

    public static void fixSoftInputLeaks(Activity activity) {
        if (activity != null) {
            fixSoftInputLeaks(activity.getWindow());
            return;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void fixSoftInputLeaks(Window window) {
        if (window != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) Utils.getApp().getSystemService("input_method");
            if (inputMethodManager != null) {
                String[] strArr = {"mLastSrvView", "mCurRootView", "mServedView", "mNextServedView"};
                for (int i = 0; i < 4; i++) {
                    try {
                        Field declaredField = InputMethodManager.class.getDeclaredField(strArr[i]);
                        if (!declaredField.isAccessible()) {
                            declaredField.setAccessible(true);
                        }
                        Object obj = declaredField.get(inputMethodManager);
                        if (obj instanceof View) {
                            if (((View) obj).getRootView() == window.getDecorView().getRootView()) {
                                declaredField.set(inputMethodManager, (Object) null);
                            }
                        }
                    } catch (Throwable unused) {
                    }
                }
                return;
            }
            return;
        }
        throw new NullPointerException("Argument 'window' of type Window (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void clickBlankArea2HideSoftInput() {
        Log.i("KeyboardUtils", "Please refer to the following code.");
    }
}
