package mc.csst.com.selfchassis.ui.dialog;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.ciot.base.util.ContextUtil;
import com.ciot.sentrymove.R;
import java.lang.reflect.Field;
import mc.csst.com.selfchassis.utils.MyToastUtils;

abstract class BaseDialog extends DialogFragment {
    private int mAnimStyle = 0;
    private float mDimAmount = 0.5f;
    private int mHeight = 0;
    protected int mLayoutResId;
    private int mMargin = 0;
    private boolean mOutCancel = false;
    private boolean mShowBottomEnable;
    private int mWidth = 0;

    public abstract void convertView(View view, BaseDialog baseDialog);

    public abstract int setUpLayoutId();

    BaseDialog() {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(0, R.style.BaseDialog);
        this.mLayoutResId = setUpLayoutId();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(this.mLayoutResId, viewGroup, false);
        convertView(inflate, this);
        return inflate;
    }

    public void onStart() {
        super.onStart();
        initParams();
        fullScreenImmersive();
        removeDecorViewBg();
    }

    private void initParams() {
        Window window = getDialog().getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.dimAmount = this.mDimAmount;
            if (this.mShowBottomEnable) {
                attributes.gravity = 80;
            }
            int i = this.mWidth;
            if (i == 0) {
                attributes.width = -2;
            } else if (i > 0) {
                attributes.width = i;
            }
            int i2 = this.mHeight;
            if (i2 == 0) {
                attributes.height = -2;
            } else if (i2 > 0) {
                attributes.height = i2;
            }
            int i3 = this.mAnimStyle;
            if (i3 != 0) {
                window.setWindowAnimations(i3);
            }
            window.setAttributes(attributes);
        }
        setCancelable(this.mOutCancel);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        solveNavigationKeyAppear();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r0 = getDialog().getWindow();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void solveNavigationKeyAppear() {
        /*
            r3 = this;
            android.app.Dialog r0 = r3.getDialog()
            if (r0 == 0) goto L_0x0021
            android.app.Dialog r0 = r3.getDialog()
            android.view.Window r0 = r0.getWindow()
            if (r0 == 0) goto L_0x0021
            r1 = 8
            r0.addFlags(r1)
            android.app.Dialog r1 = r3.getDialog()
            mc.csst.com.selfchassis.ui.dialog.BaseDialog$1 r2 = new mc.csst.com.selfchassis.ui.dialog.BaseDialog$1
            r2.<init>(r0)
            r1.setOnShowListener(r2)
        L_0x0021:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: mc.csst.com.selfchassis.ui.dialog.BaseDialog.solveNavigationKeyAppear():void");
    }

    public BaseDialog setDimAmout(float f) {
        this.mDimAmount = f;
        return this;
    }

    public BaseDialog setShowBottom(boolean z) {
        this.mShowBottomEnable = z;
        return this;
    }

    public BaseDialog setSize(int i, int i2) {
        this.mWidth = i;
        this.mHeight = i2;
        return this;
    }

    public BaseDialog setMargin(int i) {
        this.mMargin = i;
        return this;
    }

    public BaseDialog setAnimStyle(int i) {
        this.mAnimStyle = i;
        return this;
    }

    public BaseDialog setOutCancel(boolean z) {
        this.mOutCancel = z;
        return this;
    }

    public BaseDialog show(FragmentManager fragmentManager) {
        super.show(fragmentManager, String.valueOf(System.currentTimeMillis()));
        return this;
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int dp2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public void removeDecorViewBg() {
        getDialog().getWindow().getDecorView().setBackground((Drawable) null);
    }

    public void fullScreenImmersive() {
        if (getDialog() != null) {
            getDialog().getWindow().getDecorView().setSystemUiVisibility(5894);
        }
    }

    public boolean checkNullAndShowToast(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            return false;
        }
        Context context = ContextUtil.getContext();
        MyToastUtils.showShort(context, str2 + ContextUtil.getContext().getResources().getString(R.string.tip_empty));
        return true;
    }

    public void showAllowingStateLoss(FragmentManager fragmentManager) {
        showAllowingStateLoss(fragmentManager, getClass().getSimpleName());
    }

    public void showAllowingStateLoss(FragmentManager fragmentManager, String str) {
        try {
            Field declaredField = DialogFragment.class.getDeclaredField("mDismissed");
            declaredField.setAccessible(true);
            declaredField.set(this, false);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        try {
            Field declaredField2 = DialogFragment.class.getDeclaredField("mShownByMe");
            declaredField2.setAccessible(true);
            declaredField2.set(this, true);
        } catch (IllegalAccessException | NoSuchFieldException e2) {
            e2.printStackTrace();
        }
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.add((Fragment) this, str);
        beginTransaction.commitAllowingStateLoss();
    }

    public boolean isShow() {
        if (getDialog() != null) {
            return getDialog().isShowing();
        }
        return false;
    }
}
