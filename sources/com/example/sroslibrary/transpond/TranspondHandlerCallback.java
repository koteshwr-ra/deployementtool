package com.example.sroslibrary.transpond;

import com.ciot.base.util.MyLogUtils;
import com.example.sroslibrary.bean.TranspondProtocolBean;
import com.example.sroslibrary.transpond.TranspondHandlerAdapter;
import com.google.gson.Gson;

public class TranspondHandlerCallback implements TranspondHandlerAdapter.OnTranspondHandlerCallBack {
    private static final String TAG = "NETWORK_TAG";
    private Gson mGson;

    public void messageReceived(Object obj) {
        TranspondProtocolBean transpondProtocolBean = (TranspondProtocolBean) obj;
    }

    public void sessionOpen() {
        MyLogUtils.Logi("NETWORK_TAG", "启动转发服务");
    }

    public void sessionClose() {
        MyLogUtils.Logi("NETWORK_TAG", "关闭转发服务");
    }
}
