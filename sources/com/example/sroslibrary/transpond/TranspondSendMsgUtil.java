package com.example.sroslibrary.transpond;

import com.blankj.utilcode.util.NetworkUtils;
import com.ciot.base.util.AppSpUtil;
import com.example.sroslibrary.bean.TranspondProtocolBean;

public class TranspondSendMsgUtil {
    private static volatile TranspondSendMsgUtil mInstance;
    private String mIpAddress = NetworkUtils.getIPAddress(true);
    private String mRobotId = AppSpUtil.getInstance().getRobotNumber();
    private String mRobotModel = AppSpUtil.getInstance().getRobotModel();
    private int mRobotType = AppSpUtil.getInstance().getRobotRealType();
    private int mSeq = 1;

    private TranspondSendMsgUtil() {
    }

    public static void getInstance() {
        if (mInstance == null) {
            synchronized (TranspondSendMsgUtil.class) {
                if (mInstance == null) {
                    mInstance = new TranspondSendMsgUtil();
                }
            }
        }
    }

    private TranspondProtocolBean getTranspondProtocolBean() {
        return new TranspondProtocolBean();
    }

    private int getSeq() {
        int i = this.mSeq;
        if (i >= Integer.MAX_VALUE) {
            this.mSeq = 1;
        } else {
            this.mSeq = i + 1;
        }
        return this.mSeq;
    }
}
