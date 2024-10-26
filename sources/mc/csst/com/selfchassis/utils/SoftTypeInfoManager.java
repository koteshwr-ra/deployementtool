package mc.csst.com.selfchassis.utils;

import com.blankj.utilcode.util.LogUtils;

public class SoftTypeInfoManager {
    private static volatile SoftTypeInfoManager mInstance;
    private OnSoftTypeListener mOnSoftTypeListener;
    private int softType = 0;

    public interface OnSoftTypeListener {
        void softType(int i);
    }

    private SoftTypeInfoManager() {
    }

    public static SoftTypeInfoManager getInstance() {
        if (mInstance == null) {
            synchronized (SoftTypeInfoManager.class) {
                if (mInstance == null) {
                    mInstance = new SoftTypeInfoManager();
                }
            }
        }
        return mInstance;
    }

    public void setOnSoftTypeListener(OnSoftTypeListener onSoftTypeListener) {
        this.mOnSoftTypeListener = onSoftTypeListener;
    }

    public int getSoftType() {
        return this.softType;
    }

    public void setSoftType(int i) {
        try {
            for (StackTraceElement stackTraceElement : new Throwable().getStackTrace()) {
                LogUtils.d("SoftTypeInfoManager", " |----" + stackTraceElement.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.softType = i;
        OnSoftTypeListener onSoftTypeListener = this.mOnSoftTypeListener;
        if (onSoftTypeListener != null) {
            onSoftTypeListener.softType(i);
        }
    }

    public void refresh() {
        OnSoftTypeListener onSoftTypeListener = this.mOnSoftTypeListener;
        if (onSoftTypeListener != null) {
            onSoftTypeListener.softType(this.softType);
        }
    }

    public void release() {
        this.mOnSoftTypeListener = null;
    }
}
