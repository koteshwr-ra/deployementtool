package com.yhao.floatwindow;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import com.ciot.diagnosis.diagnosis.ScannerDiagnosis;

class FloatPhone extends FloatView {
    private boolean isRemove = false;
    private final Context mContext;
    /* access modifiers changed from: private */
    public final WindowManager.LayoutParams mLayoutParams;
    /* access modifiers changed from: private */
    public PermissionListener mPermissionListener;
    /* access modifiers changed from: private */
    public View mView;
    /* access modifiers changed from: private */
    public final WindowManager mWindowManager;
    private int mX;
    private int mY;

    FloatPhone(Context context, PermissionListener permissionListener) {
        this.mContext = context;
        this.mPermissionListener = permissionListener;
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.mLayoutParams = layoutParams;
        layoutParams.format = 1;
        this.mLayoutParams.flags = 552;
        this.mLayoutParams.windowAnimations = 0;
    }

    public void setSize(int i, int i2) {
        this.mLayoutParams.width = i;
        this.mLayoutParams.height = i2;
    }

    public void setView(View view) {
        this.mView = view;
    }

    public void setGravity(int i, int i2, int i3) {
        this.mLayoutParams.gravity = i;
        WindowManager.LayoutParams layoutParams = this.mLayoutParams;
        this.mX = i2;
        layoutParams.x = i2;
        WindowManager.LayoutParams layoutParams2 = this.mLayoutParams;
        this.mY = i3;
        layoutParams2.y = i3;
    }

    public void init() {
        if (Build.VERSION.SDK_INT >= 25) {
            req();
        } else if (!Miui.rom()) {
            try {
                this.mLayoutParams.type = 2005;
                this.mWindowManager.addView(this.mView, this.mLayoutParams);
            } catch (Exception unused) {
                this.mWindowManager.removeView(this.mView);
                LogUtil.e("TYPE_TOAST 失败");
                req();
            }
        } else if (Build.VERSION.SDK_INT >= 23) {
            req();
        } else {
            this.mLayoutParams.type = ScannerDiagnosis.SubScannerDiagnosis.CODE_INITED_AND_DID_NOT_READ_DATA;
            Miui.req(this.mContext, new PermissionListener() {
                public void onSuccess() {
                    FloatPhone.this.mWindowManager.addView(FloatPhone.this.mView, FloatPhone.this.mLayoutParams);
                    if (FloatPhone.this.mPermissionListener != null) {
                        FloatPhone.this.mPermissionListener.onSuccess();
                    }
                }

                public void onFail() {
                    if (FloatPhone.this.mPermissionListener != null) {
                        FloatPhone.this.mPermissionListener.onFail();
                    }
                }
            });
        }
    }

    private void req() {
        if (Build.VERSION.SDK_INT >= 26) {
            this.mLayoutParams.type = 2038;
        } else {
            this.mLayoutParams.type = ScannerDiagnosis.SubScannerDiagnosis.CODE_INITED_AND_DID_NOT_READ_DATA;
        }
        FloatActivity.request(this.mContext, new PermissionListener() {
            public void onSuccess() {
                FloatPhone.this.mWindowManager.addView(FloatPhone.this.mView, FloatPhone.this.mLayoutParams);
                if (FloatPhone.this.mPermissionListener != null) {
                    FloatPhone.this.mPermissionListener.onSuccess();
                }
            }

            public void onFail() {
                if (FloatPhone.this.mPermissionListener != null) {
                    FloatPhone.this.mPermissionListener.onFail();
                }
            }
        });
    }

    public void dismiss() {
        this.isRemove = true;
        this.mWindowManager.removeView(this.mView);
    }

    public void updateXY(int i, int i2) {
        if (!this.isRemove) {
            WindowManager.LayoutParams layoutParams = this.mLayoutParams;
            this.mX = i;
            layoutParams.x = i;
            WindowManager.LayoutParams layoutParams2 = this.mLayoutParams;
            this.mY = i2;
            layoutParams2.y = i2;
            this.mWindowManager.updateViewLayout(this.mView, this.mLayoutParams);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateX(int i) {
        if (!this.isRemove) {
            WindowManager.LayoutParams layoutParams = this.mLayoutParams;
            this.mX = i;
            layoutParams.x = i;
            this.mWindowManager.updateViewLayout(this.mView, this.mLayoutParams);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateY(int i) {
        if (!this.isRemove) {
            WindowManager.LayoutParams layoutParams = this.mLayoutParams;
            this.mY = i;
            layoutParams.y = i;
            this.mWindowManager.updateViewLayout(this.mView, this.mLayoutParams);
        }
    }

    /* access modifiers changed from: package-private */
    public int getX() {
        return this.mX;
    }

    /* access modifiers changed from: package-private */
    public int getY() {
        return this.mY;
    }
}
