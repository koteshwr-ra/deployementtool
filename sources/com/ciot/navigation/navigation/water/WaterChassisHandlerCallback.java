package com.ciot.navigation.navigation.water;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.ciot.base.util.ContextUtil;
import com.ciot.base.util.MyLogUtils;
import com.ciot.navigation.R;
import com.ciot.navigation.navigation.NavigationHelper;
import com.ciot.tcpclient.ClientIoHandlerAdapter;
import com.ciot.waterchassis.WaterChassisNotificationHelper;
import com.ciot.waterchassis.bean.WaterMarkerBean;
import com.ciot.waterchassis.bean.WaterMarkersBean;
import com.ciot.waterchassis.bean.WaterNotificationBean;
import com.ciot.waterchassis.status.WaterChassisStatus;
import com.example.sroslibrary.bean.QueryNavigationBeanR;
import com.example.sroslibrary.sros.SrosSendMsgUtil;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Map;
import org.json.JSONException;

public class WaterChassisHandlerCallback implements ClientIoHandlerAdapter.OnMessageReceivedCallBack {
    private static final String CODE = "code";
    private static final String COMMAND = "command";
    private static final int MAX_TASK_BLOCK_COUNT = 2;
    private static final String TAG = WaterChassisHandlerCallback.class.getSimpleName();
    private int mBlockCount = 0;
    private String mCurrentMapName;
    private Gson mGson;
    IWaterNavigateListener mListener;
    private boolean mLowPower = false;
    private String mLowPowerAlarmCode;

    public void messageReceive(Object obj) {
        if (this.mGson == null) {
            this.mGson = new Gson();
        }
        String obj2 = obj.toString();
        if (obj2.contains(COMMAND)) {
            try {
                convert2AppropriateClazzByCommand(obj2);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (obj2.contains("code")) {
            notification(obj2);
        }
    }

    private void notification(String str) {
        WaterNotificationBean waterNotificationBean = (WaterNotificationBean) this.mGson.fromJson(str, WaterNotificationBean.class);
        WaterNotificationLiveData.Companion.get().postValue(waterNotificationBean);
        int notificationUser = WaterChassisNotificationHelper.getInstance().notificationUser(waterNotificationBean.getCode());
        try {
            if (this.mListener != null && !TextUtils.isEmpty(waterNotificationBean.getCode()) && waterNotificationBean.getCode().equals("01002")) {
                this.mListener.stateArrivedPointNotification(str);
            }
            String string = ContextUtil.getContext().getString(notificationUser);
            if (this.mListener != null && !TextUtils.isEmpty(string)) {
                this.mListener.stateNavigateErrMsg(waterNotificationBean.getCode(), string);
            }
        } catch (Exception e) {
            String str2 = TAG;
            MyLogUtils.Logd(str2, "notification: " + e.getMessage());
        }
        taskBlockStopSpray(waterNotificationBean);
    }

    private void taskBlockStopSpray(WaterNotificationBean waterNotificationBean) {
        Log.d(TAG, "notificationBean.getCode():" + waterNotificationBean.getCode());
        if ("01006".equals(waterNotificationBean.getCode())) {
            this.mBlockCount++;
        }
        if (this.mBlockCount == 2) {
            this.mBlockCount = 0;
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void convert2AppropriateClazzByCommand(java.lang.String r6) throws org.json.JSONException {
        /*
            r5 = this;
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>(r6)
            java.lang.String r1 = "command"
            java.lang.String r0 = r0.getString(r1)
            int r1 = r0.hashCode()
            r2 = 1
            switch(r1) {
                case -2108369309: goto L_0x010e;
                case -1918414348: goto L_0x0104;
                case -1764534662: goto L_0x00f9;
                case -1616950009: goto L_0x00ee;
                case -1239683320: goto L_0x00e3;
                case -923415884: goto L_0x00d8;
                case -663279363: goto L_0x00cd;
                case -431224693: goto L_0x00c2;
                case -260583570: goto L_0x00b7;
                case -127185323: goto L_0x00ab;
                case 65204982: goto L_0x00a0;
                case 296942509: goto L_0x0094;
                case 741212051: goto L_0x0088;
                case 907663119: goto L_0x007d;
                case 940084229: goto L_0x0071;
                case 956554330: goto L_0x0065;
                case 975957506: goto L_0x0059;
                case 1091750163: goto L_0x004e;
                case 1260399477: goto L_0x0042;
                case 1305340779: goto L_0x0037;
                case 1809296726: goto L_0x002b;
                case 1871885269: goto L_0x0020;
                case 2101611111: goto L_0x0015;
                default: goto L_0x0013;
            }
        L_0x0013:
            goto L_0x0118
        L_0x0015:
            java.lang.String r1 = "/api/robot_info"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0118
            r1 = 1
            goto L_0x0119
        L_0x0020:
            java.lang.String r1 = "/api/move"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0118
            r1 = 5
            goto L_0x0119
        L_0x002b:
            java.lang.String r1 = "/api/make_plan"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0118
            r1 = 17
            goto L_0x0119
        L_0x0037:
            java.lang.String r1 = "/api/robot_status"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0118
            r1 = 0
            goto L_0x0119
        L_0x0042:
            java.lang.String r1 = "/api/map/list"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0118
            r1 = 9
            goto L_0x0119
        L_0x004e:
            java.lang.String r1 = "/api/markers/insert"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0118
            r1 = 7
            goto L_0x0119
        L_0x0059:
            java.lang.String r1 = "/api/map/set_current_map"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0118
            r1 = 11
            goto L_0x0119
        L_0x0065:
            java.lang.String r1 = "/api/shutdown"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0118
            r1 = 22
            goto L_0x0119
        L_0x0071:
            java.lang.String r1 = "/api/markers/delete"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0118
            r1 = 8
            goto L_0x0119
        L_0x007d:
            java.lang.String r1 = "/api/markers/query_list"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0118
            r1 = 6
            goto L_0x0119
        L_0x0088:
            java.lang.String r1 = "/api/get_params"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0118
            r1 = 12
            goto L_0x0119
        L_0x0094:
            java.lang.String r1 = "/api/software/update"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0118
            r1 = 20
            goto L_0x0119
        L_0x00a0:
            java.lang.String r1 = "/api/map/get_current_map"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0118
            r1 = 2
            goto L_0x0119
        L_0x00ab:
            java.lang.String r1 = "/api/router/scan_wifi"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0118
            r1 = 14
            goto L_0x0119
        L_0x00b7:
            java.lang.String r1 = "/api/joy_control"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0118
            r1 = 13
            goto L_0x0119
        L_0x00c2:
            java.lang.String r1 = "/api/software/get_version"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0118
            r1 = 19
            goto L_0x0119
        L_0x00cd:
            java.lang.String r1 = "/api/router/get_bridge_status"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0118
            r1 = 16
            goto L_0x0119
        L_0x00d8:
            java.lang.String r1 = "/api/router/hotspot_config"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0118
            r1 = 15
            goto L_0x0119
        L_0x00e3:
            java.lang.String r1 = "/api/diagnosis/get_result"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0118
            r1 = 21
            goto L_0x0119
        L_0x00ee:
            java.lang.String r1 = "/api/set_params"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0118
            r1 = 10
            goto L_0x0119
        L_0x00f9:
            java.lang.String r1 = "/api/software/check_for_update"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0118
            r1 = 18
            goto L_0x0119
        L_0x0104:
            java.lang.String r1 = "/api/move/cancel"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0118
            r1 = 4
            goto L_0x0119
        L_0x010e:
            java.lang.String r1 = "/api/estop"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0118
            r1 = 3
            goto L_0x0119
        L_0x0118:
            r1 = -1
        L_0x0119:
            java.lang.String r3 = "底盘信息"
            java.lang.String r4 = "NAVIGATION_TAG"
            switch(r1) {
                case 0: goto L_0x04aa;
                case 1: goto L_0x048c;
                case 2: goto L_0x0450;
                case 3: goto L_0x0420;
                case 4: goto L_0x03f0;
                case 5: goto L_0x0348;
                case 6: goto L_0x0317;
                case 7: goto L_0x0304;
                case 8: goto L_0x02d4;
                case 9: goto L_0x02c1;
                case 10: goto L_0x0520;
                case 11: goto L_0x02ae;
                case 12: goto L_0x028d;
                case 13: goto L_0x0264;
                case 14: goto L_0x0251;
                case 15: goto L_0x023e;
                case 16: goto L_0x022b;
                case 17: goto L_0x01c4;
                case 18: goto L_0x01f3;
                case 19: goto L_0x017b;
                case 20: goto L_0x0164;
                case 21: goto L_0x014f;
                case 22: goto L_0x0138;
                default: goto L_0x0120;
            }
        L_0x0120:
            java.lang.String r0 = TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "default: "
            r1.append(r2)
            r1.append(r6)
            java.lang.String r6 = r1.toString()
            com.ciot.base.util.MyLogUtils.Logd(r0, r6)
            goto L_0x0520
        L_0x0138:
            com.ciot.base.util.LogPlus.w((java.lang.String) r4, (java.lang.String) r6)
            com.google.gson.Gson r0 = r5.mGson
            java.lang.Class<com.ciot.navigation.navigation.water.WaterVersionBean> r1 = com.ciot.navigation.navigation.water.WaterVersionBean.class
            java.lang.Object r6 = r0.fromJson((java.lang.String) r6, r1)
            com.ciot.navigation.navigation.water.WaterVersionBean r6 = (com.ciot.navigation.navigation.water.WaterVersionBean) r6
            com.ciot.navigation.navigation.water.WaterChassisHandlerCallback$2 r0 = new com.ciot.navigation.navigation.water.WaterChassisHandlerCallback$2
            r0.<init>(r6)
            com.blankj.utilcode.util.ViewUtils.runOnUiThread(r0)
            goto L_0x0520
        L_0x014f:
            com.google.gson.Gson r0 = r5.mGson
            java.lang.Class<com.ciot.navigation.navigation.water.WaterDiagnosisResultBean> r1 = com.ciot.navigation.navigation.water.WaterDiagnosisResultBean.class
            java.lang.Object r6 = r0.fromJson((java.lang.String) r6, r1)
            com.ciot.navigation.navigation.water.WaterDiagnosisResultBean r6 = (com.ciot.navigation.navigation.water.WaterDiagnosisResultBean) r6
            com.ciot.navigation.navigation.water.WaterDiagnosisResultLiveData$Companion r0 = com.ciot.navigation.navigation.water.WaterDiagnosisResultLiveData.Companion
            com.ciot.navigation.navigation.water.WaterDiagnosisResultLiveData r0 = r0.get()
            r0.postValue(r6)
            goto L_0x0520
        L_0x0164:
            com.ciot.base.util.LogPlus.w((java.lang.String) r4, (java.lang.String) r6)
            com.google.gson.Gson r0 = r5.mGson
            java.lang.Class<com.ciot.navigation.navigation.water.WaterVersionBean> r1 = com.ciot.navigation.navigation.water.WaterVersionBean.class
            java.lang.Object r6 = r0.fromJson((java.lang.String) r6, r1)
            com.ciot.navigation.navigation.water.WaterVersionBean r6 = (com.ciot.navigation.navigation.water.WaterVersionBean) r6
            com.ciot.navigation.navigation.water.WaterChassisHandlerCallback$1 r0 = new com.ciot.navigation.navigation.water.WaterChassisHandlerCallback$1
            r0.<init>(r6)
            com.blankj.utilcode.util.ViewUtils.runOnUiThread(r0)
            goto L_0x0520
        L_0x017b:
            com.google.gson.Gson r0 = r5.mGson
            java.lang.Class<com.ciot.navigation.navigation.water.WaterVersionBean> r1 = com.ciot.navigation.navigation.water.WaterVersionBean.class
            java.lang.Object r6 = r0.fromJson((java.lang.String) r6, r1)
            com.ciot.navigation.navigation.water.WaterVersionBean r6 = (com.ciot.navigation.navigation.water.WaterVersionBean) r6
            java.lang.String r0 = r6.toString()
            com.ciot.base.util.LogPlus.w((java.lang.String) r4, (java.lang.String) r0)
            com.ciot.navigation.navigation.water.WaterVersionLiveData$Companion r0 = com.ciot.navigation.navigation.water.WaterVersionLiveData.Companion
            com.ciot.navigation.navigation.water.WaterVersionLiveData r0 = r0.get()
            java.lang.Object r0 = r0.getValue()
            com.ciot.navigation.navigation.water.WaterCheckUpdataBean r0 = (com.ciot.navigation.navigation.water.WaterCheckUpdataBean) r0
            if (r0 == 0) goto L_0x0520
            com.ciot.navigation.navigation.water.Results r1 = r0.getResults()
            boolean r0 = r0.isUpdataing()
            if (r0 == 0) goto L_0x0520
            boolean r0 = r1.getEnable_update()
            if (r0 == 0) goto L_0x0520
            java.lang.String r0 = r1.getVersion_latest()
            java.lang.String r6 = r6.getResults()
            boolean r6 = r0.equals(r6)
            if (r6 == 0) goto L_0x0520
            com.ciot.navigation.navigation.water.WaterVersionLiveData$Companion r6 = com.ciot.navigation.navigation.water.WaterVersionLiveData.Companion
            com.ciot.navigation.navigation.water.WaterVersionLiveData r6 = r6.get()
            r0 = 0
            r6.postValue(r0)
            goto L_0x0520
        L_0x01c4:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r0 = "----"
            r1.append(r0)
            r1.append(r6)
            java.lang.String r0 = r1.toString()
            com.ciot.base.util.MyLogUtils.Logw(r4, r0)
            com.google.gson.Gson r0 = r5.mGson
            java.lang.Class<com.ciot.navigation.navigation.water.WaterMakePlanBean> r1 = com.ciot.navigation.navigation.water.WaterMakePlanBean.class
            java.lang.Object r0 = r0.fromJson((java.lang.String) r6, r1)
            com.ciot.navigation.navigation.water.WaterMakePlanBean r0 = (com.ciot.navigation.navigation.water.WaterMakePlanBean) r0
            java.lang.String r1 = r0.toString()
            com.ciot.base.util.LogPlus.w(r1)
            org.greenrobot.eventbus.EventBus r1 = org.greenrobot.eventbus.EventBus.getDefault()
            r1.post(r0)
        L_0x01f3:
            com.ciot.base.util.LogPlus.w((java.lang.String) r4, (java.lang.String) r6)
            com.google.gson.Gson r0 = r5.mGson
            java.lang.Class<com.ciot.navigation.navigation.water.WaterCheckUpdataBean> r1 = com.ciot.navigation.navigation.water.WaterCheckUpdataBean.class
            java.lang.Object r6 = r0.fromJson((java.lang.String) r6, r1)
            com.ciot.navigation.navigation.water.WaterCheckUpdataBean r6 = (com.ciot.navigation.navigation.water.WaterCheckUpdataBean) r6
            java.lang.String r0 = r6.getStatus()
            java.lang.String r1 = "OK"
            boolean r0 = android.text.TextUtils.equals(r0, r1)
            if (r0 == 0) goto L_0x0520
            com.ciot.navigation.navigation.water.Results r0 = r6.getResults()
            boolean r0 = r0.getEnable_update()
            if (r0 == 0) goto L_0x0220
            com.ciot.waterchassis.WaterChassisHelper r0 = com.ciot.waterchassis.WaterChassisHelper.getInstance()
            r0.updateChassisVersion()
            r6.setUpdataing(r2)
        L_0x0220:
            com.ciot.navigation.navigation.water.WaterVersionLiveData$Companion r0 = com.ciot.navigation.navigation.water.WaterVersionLiveData.Companion
            com.ciot.navigation.navigation.water.WaterVersionLiveData r0 = r0.get()
            r0.postValue(r6)
            goto L_0x0520
        L_0x022b:
            com.google.gson.Gson r0 = r5.mGson
            java.lang.Class<com.ciot.navigation.navigation.water.WaterBridgeStatusBean> r1 = com.ciot.navigation.navigation.water.WaterBridgeStatusBean.class
            java.lang.Object r6 = r0.fromJson((java.lang.String) r6, r1)
            com.ciot.navigation.navigation.water.WaterBridgeStatusBean r6 = (com.ciot.navigation.navigation.water.WaterBridgeStatusBean) r6
            org.greenrobot.eventbus.EventBus r0 = org.greenrobot.eventbus.EventBus.getDefault()
            r0.post(r6)
            goto L_0x0520
        L_0x023e:
            com.google.gson.Gson r0 = r5.mGson
            java.lang.Class<com.ciot.navigation.navigation.water.WaterHotspotBean> r1 = com.ciot.navigation.navigation.water.WaterHotspotBean.class
            java.lang.Object r6 = r0.fromJson((java.lang.String) r6, r1)
            com.ciot.navigation.navigation.water.WaterHotspotBean r6 = (com.ciot.navigation.navigation.water.WaterHotspotBean) r6
            org.greenrobot.eventbus.EventBus r0 = org.greenrobot.eventbus.EventBus.getDefault()
            r0.post(r6)
            goto L_0x0520
        L_0x0251:
            com.google.gson.Gson r0 = r5.mGson
            java.lang.Class<com.ciot.navigation.navigation.water.WaterScanWifiBean> r1 = com.ciot.navigation.navigation.water.WaterScanWifiBean.class
            java.lang.Object r6 = r0.fromJson((java.lang.String) r6, r1)
            com.ciot.navigation.navigation.water.WaterScanWifiBean r6 = (com.ciot.navigation.navigation.water.WaterScanWifiBean) r6
            org.greenrobot.eventbus.EventBus r0 = org.greenrobot.eventbus.EventBus.getDefault()
            r0.post(r6)
            goto L_0x0520
        L_0x0264:
            com.google.gson.Gson r0 = r5.mGson
            java.lang.Class<com.ciot.waterchassis.bean.OperationBean> r1 = com.ciot.waterchassis.bean.OperationBean.class
            java.lang.Object r6 = r0.fromJson((java.lang.String) r6, r1)
            com.ciot.waterchassis.bean.OperationBean r6 = (com.ciot.waterchassis.bean.OperationBean) r6
            com.example.sroslibrary.sros.SrosSendMsgUtil r0 = com.example.sroslibrary.sros.SrosSendMsgUtil.getInstance()
            android.content.Context r1 = com.ciot.base.util.ContextUtil.getContext()
            int r2 = com.ciot.navigation.R.string.navigation_api_success_status
            java.lang.String r1 = r1.getString(r2)
            java.lang.String r2 = r6.getStatus()
            boolean r1 = r1.equals(r2)
            java.lang.String r6 = r6.getError_message()
            r0.replyManualControl(r1, r6)
            goto L_0x0520
        L_0x028d:
            com.google.gson.Gson r0 = r5.mGson
            java.lang.Class<com.ciot.waterchassis.bean.WaterGetParams> r1 = com.ciot.waterchassis.bean.WaterGetParams.class
            java.lang.Object r6 = r0.fromJson((java.lang.String) r6, r1)
            com.ciot.waterchassis.bean.WaterGetParams r6 = (com.ciot.waterchassis.bean.WaterGetParams) r6
            com.ciot.waterchassis.bean.WaterGetParams$ResultsBean r0 = r6.getResults()
            if (r0 == 0) goto L_0x0520
            com.ciot.waterchassis.bean.WaterGetParams$ResultsBean r6 = r6.getResults()
            float r6 = r6.getMax_speed_linear()
            com.ciot.navigation.navigation.water.IWaterNavigateListener r0 = r5.mListener
            if (r0 == 0) goto L_0x0520
            r0.stateMaxLinearSpeed(r6)
            goto L_0x0520
        L_0x02ae:
            com.google.gson.Gson r0 = r5.mGson
            java.lang.Class<com.ciot.waterchassis.bean.WaterSetCurrentMapBean> r1 = com.ciot.waterchassis.bean.WaterSetCurrentMapBean.class
            java.lang.Object r6 = r0.fromJson((java.lang.String) r6, r1)
            com.ciot.waterchassis.bean.WaterSetCurrentMapBean r6 = (com.ciot.waterchassis.bean.WaterSetCurrentMapBean) r6
            org.greenrobot.eventbus.EventBus r0 = org.greenrobot.eventbus.EventBus.getDefault()
            r0.post(r6)
            goto L_0x0520
        L_0x02c1:
            com.google.gson.Gson r0 = r5.mGson
            java.lang.Class<com.example.sroslibrary.bean.WaterMapBean> r1 = com.example.sroslibrary.bean.WaterMapBean.class
            java.lang.Object r6 = r0.fromJson((java.lang.String) r6, r1)
            com.example.sroslibrary.bean.WaterMapBean r6 = (com.example.sroslibrary.bean.WaterMapBean) r6
            com.example.sroslibrary.sros.SrosSendMsgUtil r0 = com.example.sroslibrary.sros.SrosSendMsgUtil.getInstance()
            r0.replyQueryMapList(r2, r6)
            goto L_0x0520
        L_0x02d4:
            com.google.gson.Gson r0 = r5.mGson
            java.lang.Class<com.ciot.waterchassis.bean.WaterDeleteMarkerBean> r1 = com.ciot.waterchassis.bean.WaterDeleteMarkerBean.class
            java.lang.Object r6 = r0.fromJson((java.lang.String) r6, r1)
            com.ciot.waterchassis.bean.WaterDeleteMarkerBean r6 = (com.ciot.waterchassis.bean.WaterDeleteMarkerBean) r6
            com.example.sroslibrary.sros.SrosSendMsgUtil r0 = com.example.sroslibrary.sros.SrosSendMsgUtil.getInstance()
            android.content.Context r1 = com.ciot.base.util.ContextUtil.getContext()
            int r2 = com.ciot.navigation.R.string.navigation_api_success_status
            java.lang.String r1 = r1.getString(r2)
            java.lang.String r2 = r6.getStatus()
            boolean r1 = r1.equals(r2)
            java.lang.String r2 = r6.getError_message()
            r0.replyDeleteNavigationPoint(r1, r2)
            org.greenrobot.eventbus.EventBus r0 = org.greenrobot.eventbus.EventBus.getDefault()
            r0.post(r6)
            goto L_0x0520
        L_0x0304:
            com.google.gson.Gson r0 = r5.mGson
            java.lang.Class<com.ciot.waterchassis.bean.WaterInsertMarkerBean> r1 = com.ciot.waterchassis.bean.WaterInsertMarkerBean.class
            java.lang.Object r6 = r0.fromJson((java.lang.String) r6, r1)
            com.ciot.waterchassis.bean.WaterInsertMarkerBean r6 = (com.ciot.waterchassis.bean.WaterInsertMarkerBean) r6
            org.greenrobot.eventbus.EventBus r0 = org.greenrobot.eventbus.EventBus.getDefault()
            r0.post(r6)
            goto L_0x0520
        L_0x0317:
            com.google.gson.Gson r0 = r5.mGson
            java.lang.Class<com.ciot.waterchassis.bean.WaterMarkersBean> r1 = com.ciot.waterchassis.bean.WaterMarkersBean.class
            java.lang.Object r6 = r0.fromJson((java.lang.String) r6, r1)
            com.ciot.waterchassis.bean.WaterMarkersBean r6 = (com.ciot.waterchassis.bean.WaterMarkersBean) r6
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "WATER_QUERY_MARKER_LIST result:"
            r0.append(r1)
            java.lang.String r1 = r6.toString()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.ciot.base.util.LogPlus.i((java.lang.String) r4, (java.lang.String) r0)
            r5.replyServerQueryMarkers(r6)
            r5.replyLaserNavigationPoint(r6)
            org.greenrobot.eventbus.EventBus r0 = org.greenrobot.eventbus.EventBus.getDefault()
            r0.post(r6)
            goto L_0x0520
        L_0x0348:
            com.google.gson.Gson r0 = r5.mGson
            java.lang.Class<com.ciot.waterchassis.bean.WaterMoveBean> r1 = com.ciot.waterchassis.bean.WaterMoveBean.class
            java.lang.Object r0 = r0.fromJson((java.lang.String) r6, r1)
            com.ciot.waterchassis.bean.WaterMoveBean r0 = (com.ciot.waterchassis.bean.WaterMoveBean) r0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "convert2AppropriateClazzByCommand WaterChassisConstants.WATER_MOVE waterMoveBean："
            r1.append(r2)
            r1.append(r6)
            java.lang.String r6 = r1.toString()
            com.ciot.base.util.MyLogUtils.Logd(r4, r6)
            java.lang.String r6 = r0.getStatus()
            java.lang.String r1 = "INVALID_REQUEST"
            boolean r6 = r1.equals(r6)
            java.lang.String r1 = "01003"
            if (r6 == 0) goto L_0x037d
            com.ciot.navigation.navigation.water.IWaterNavigateListener r6 = r5.mListener
            java.lang.String r2 = "移动任务失败，目标点无效"
            r6.stateNavigateErrMsg(r1, r2)
            goto L_0x03a4
        L_0x037d:
            java.lang.String r6 = r0.getStatus()
            java.lang.String r2 = "REQUEST_DENIED"
            boolean r6 = r2.equals(r6)
            if (r6 == 0) goto L_0x0391
            com.ciot.navigation.navigation.water.IWaterNavigateListener r6 = r5.mListener
            java.lang.String r2 = "底盘网络异常，请检查低盘网络设置"
            r6.stateNavigateErrMsg(r1, r2)
            goto L_0x03a4
        L_0x0391:
            java.lang.String r6 = r0.getStatus()
            java.lang.String r2 = "UNKNOWN_ERROR"
            boolean r6 = r2.equals(r6)
            if (r6 == 0) goto L_0x03a4
            com.ciot.navigation.navigation.water.IWaterNavigateListener r6 = r5.mListener
            java.lang.String r2 = "底盘未知错误"
            r6.stateNavigateErrMsg(r1, r2)
        L_0x03a4:
            com.ciot.navigation.navigation.NavigationHelper$Companion r6 = com.ciot.navigation.navigation.NavigationHelper.Companion
            com.ciot.navigation.navigation.NavigationHelper r6 = r6.getInstance()
            r6.updateMoveResponse(r0)
            java.lang.String r6 = r0.toString()
            com.ciot.base.util.LogPlus.i((java.lang.String) r3, (java.lang.String) r6)
            com.example.sroslibrary.sros.SrosSendMsgUtil r6 = com.example.sroslibrary.sros.SrosSendMsgUtil.getInstance()
            android.content.Context r1 = com.ciot.base.util.ContextUtil.getContext()
            int r2 = com.ciot.navigation.R.string.navigation_api_success_status
            java.lang.String r1 = r1.getString(r2)
            java.lang.String r2 = r0.getStatus()
            boolean r1 = r1.equals(r2)
            java.lang.String r2 = r0.getError_message()
            r6.replyCoordinateNavigation(r1, r2)
            com.example.sroslibrary.sros.SrosSendMsgUtil r6 = com.example.sroslibrary.sros.SrosSendMsgUtil.getInstance()
            android.content.Context r1 = com.ciot.base.util.ContextUtil.getContext()
            int r2 = com.ciot.navigation.R.string.navigation_api_success_status
            java.lang.String r1 = r1.getString(r2)
            java.lang.String r2 = r0.getStatus()
            boolean r1 = r1.equals(r2)
            java.lang.String r0 = r0.getError_message()
            r6.replyPositionNameNavigation(r1, r0)
            goto L_0x0520
        L_0x03f0:
            com.google.gson.Gson r0 = r5.mGson
            java.lang.Class<com.ciot.waterchassis.bean.WaterCancelMoveBean> r1 = com.ciot.waterchassis.bean.WaterCancelMoveBean.class
            java.lang.Object r6 = r0.fromJson((java.lang.String) r6, r1)
            com.ciot.waterchassis.bean.WaterCancelMoveBean r6 = (com.ciot.waterchassis.bean.WaterCancelMoveBean) r6
            com.example.sroslibrary.sros.SrosSendMsgUtil r0 = com.example.sroslibrary.sros.SrosSendMsgUtil.getInstance()
            android.content.Context r1 = com.ciot.base.util.ContextUtil.getContext()
            int r2 = com.ciot.navigation.R.string.navigation_api_success_status
            java.lang.String r1 = r1.getString(r2)
            java.lang.String r2 = r6.getStatus()
            boolean r1 = r1.equals(r2)
            java.lang.String r2 = r6.getError_message()
            r0.replyManualControl(r1, r2)
            org.greenrobot.eventbus.EventBus r0 = org.greenrobot.eventbus.EventBus.getDefault()
            r0.post(r6)
            goto L_0x0520
        L_0x0420:
            com.google.gson.Gson r0 = r5.mGson
            java.lang.Class<com.ciot.waterchassis.bean.WaterEstopBean> r1 = com.ciot.waterchassis.bean.WaterEstopBean.class
            java.lang.Object r6 = r0.fromJson((java.lang.String) r6, r1)
            com.ciot.waterchassis.bean.WaterEstopBean r6 = (com.ciot.waterchassis.bean.WaterEstopBean) r6
            com.example.sroslibrary.sros.SrosSendMsgUtil r0 = com.example.sroslibrary.sros.SrosSendMsgUtil.getInstance()
            android.content.Context r1 = com.ciot.base.util.ContextUtil.getContext()
            int r2 = com.ciot.navigation.R.string.navigation_api_success_status
            java.lang.String r1 = r1.getString(r2)
            java.lang.String r2 = r6.getStatus()
            boolean r1 = r1.equals(r2)
            java.lang.String r2 = r6.getError_message()
            r0.replyEStop(r1, r2)
            org.greenrobot.eventbus.EventBus r0 = org.greenrobot.eventbus.EventBus.getDefault()
            r0.post(r6)
            goto L_0x0520
        L_0x0450:
            com.google.gson.Gson r0 = r5.mGson
            java.lang.Class<com.ciot.waterchassis.bean.WaterGetCurMapBean> r1 = com.ciot.waterchassis.bean.WaterGetCurMapBean.class
            java.lang.Object r6 = r0.fromJson((java.lang.String) r6, r1)
            com.ciot.waterchassis.bean.WaterGetCurMapBean r6 = (com.ciot.waterchassis.bean.WaterGetCurMapBean) r6
            com.ciot.waterchassis.bean.WaterGetCurMapBean$ResultsBean r6 = r6.getResults()
            if (r6 == 0) goto L_0x0520
            com.ciot.waterchassis.status.WaterChassisStatus r0 = com.ciot.waterchassis.status.WaterChassisStatus.getInstance()
            int r1 = r6.getFloor()
            r0.setCurFloor(r1)
            java.lang.String r0 = r5.mCurrentMapName
            if (r0 == 0) goto L_0x0479
            java.lang.String r1 = r6.getMap_name()
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0520
        L_0x0479:
            java.lang.String r6 = r6.getMap_name()
            r5.mCurrentMapName = r6
            com.ciot.base.storage.MySpUtils r6 = com.ciot.base.storage.MySpUtils.getInstance()
            java.lang.String r0 = r5.mCurrentMapName
            java.lang.String r1 = "WaterMapName"
            r6.put((java.lang.String) r1, (java.lang.String) r0)
            goto L_0x0520
        L_0x048c:
            com.google.gson.Gson r0 = r5.mGson
            java.lang.Class<com.ciot.waterchassis.bean.WaterRobotInfo> r1 = com.ciot.waterchassis.bean.WaterRobotInfo.class
            java.lang.Object r6 = r0.fromJson((java.lang.String) r6, r1)
            com.ciot.waterchassis.bean.WaterRobotInfo r6 = (com.ciot.waterchassis.bean.WaterRobotInfo) r6
            com.ciot.waterchassis.bean.WaterRobotInfo$ResultsBean r6 = r6.getResults()
            if (r6 == 0) goto L_0x0520
            com.ciot.base.storage.MySpUtils r0 = com.ciot.base.storage.MySpUtils.getInstance()
            java.lang.String r6 = r6.getProduct_id()
            java.lang.String r1 = "WaterProductId"
            r0.put((java.lang.String) r1, (java.lang.String) r6)
            goto L_0x0520
        L_0x04aa:
            com.google.gson.Gson r0 = r5.mGson
            java.lang.Class<com.ciot.waterchassis.bean.WaterStatusBean> r1 = com.ciot.waterchassis.bean.WaterStatusBean.class
            java.lang.Object r6 = r0.fromJson((java.lang.String) r6, r1)
            com.ciot.waterchassis.bean.WaterStatusBean r6 = (com.ciot.waterchassis.bean.WaterStatusBean) r6
            com.ciot.waterchassis.bean.WaterStatusBean$ResultsBean r0 = r6.getResults()
            java.lang.String r1 = r6.toString()
            com.ciot.base.util.LogPlus.v((java.lang.String) r3, (java.lang.String) r1)
            com.ciot.navigation.navigation.water.WaterStatusLiveData$Companion r1 = com.ciot.navigation.navigation.water.WaterStatusLiveData.Companion
            com.ciot.navigation.navigation.water.WaterStatusLiveData r1 = r1.get()
            r1.postValue(r6)
            org.greenrobot.eventbus.EventBus r1 = org.greenrobot.eventbus.EventBus.getDefault()
            r1.post(r0)
            if (r0 == 0) goto L_0x0520
            com.ciot.waterchassis.bean.WaterStatusBean$ResultsBean$CurrentPoseBean r1 = r0.getCurrent_pose()
            if (r1 == 0) goto L_0x04f1
            com.ciot.waterchassis.status.WaterChassisStatus r2 = com.ciot.waterchassis.status.WaterChassisStatus.getInstance()
            double r3 = r1.getX()
            r2.setCoordinateX(r3)
            double r3 = r1.getY()
            r2.setCoordinateY(r3)
            double r3 = r1.getTheta()
            float r1 = (float) r3
            r2.setAngle(r1)
        L_0x04f1:
            com.ciot.waterchassis.status.WaterChassisStatus r1 = com.ciot.waterchassis.status.WaterChassisStatus.getInstance()
            boolean r2 = r0.isCharge_state()
            r1.setCharging(r2)
            java.lang.String r1 = r0.getMove_status()
            java.lang.String r2 = "running"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x050f
            com.example.sroslibrary.sros.SrosSendMsgUtil r1 = com.example.sroslibrary.sros.SrosSendMsgUtil.getInstance()
            r1.sendCoordinate()
        L_0x050f:
            r0.getPower_percent()
            java.lang.String r0 = r0.getRunning_status()
            r5.isInLift(r0)
            com.ciot.navigation.navigation.water.IWaterNavigateListener r0 = r5.mListener
            if (r0 == 0) goto L_0x0520
            r0.stateFull(r6)
        L_0x0520:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ciot.navigation.navigation.water.WaterChassisHandlerCallback.convert2AppropriateClazzByCommand(java.lang.String):void");
    }

    private void replyServerQueryMarkers(WaterMarkersBean waterMarkersBean) {
        ArrayList arrayList = new ArrayList();
        Map<String, WaterMarkerBean> results = waterMarkersBean.getResults();
        ArrayList arrayList2 = new ArrayList();
        for (String str : results.keySet()) {
            WaterMarkerBean waterMarkerBean = results.get(str);
            if (waterMarkerBean != null) {
                if (waterMarkerBean.getKey() == 11) {
                    arrayList2.add(waterMarkerBean);
                }
                QueryNavigationBeanR.NavigationPoint navigationPoint = new QueryNavigationBeanR.NavigationPoint();
                navigationPoint.setPositionname(waterMarkerBean.getMarker_name());
                navigationPoint.setMapinfo(String.valueOf(waterMarkerBean.getFloor()));
                navigationPoint.setType(waterMarkerBean.getKey());
                navigationPoint.setX1(waterMarkerBean.getPose().getPosition().getX());
                navigationPoint.setY1(waterMarkerBean.getPose().getPosition().getY());
                navigationPoint.setZ1(waterMarkerBean.getPose().getPosition().getZ());
                arrayList.add(navigationPoint);
            }
        }
        ChargingPileLiveData.Companion.get().postValue(arrayList2);
        NavigationHelper.Companion.getInstance().setAllPoint(arrayList);
        SrosSendMsgUtil.getInstance().replyQueryNavigationPoint(true, arrayList);
    }

    private void isInLift(String str) {
        Context context = ContextUtil.getContext();
        String string = context.getString(R.string.navigation_main_enter_lift);
        String string2 = context.getString(R.string.navigation_main_avoid_lift);
        String string3 = context.getString(R.string.navigation_main_take_lift);
        String string4 = context.getString(R.string.navigation_main_exit_lift);
        String string5 = context.getString(R.string.navigation_main_back_to_lift);
        if (string.equals(str) || string2.equals(str) || string3.equals(str) || string4.equals(str) || string2.equals(string5)) {
            WaterChassisStatus.getInstance().setInElevator(true);
        } else {
            WaterChassisStatus.getInstance().setInElevator(false);
        }
    }

    private void replyLaserNavigationPoint(WaterMarkersBean waterMarkersBean) {
        Map<String, WaterMarkerBean> results;
        if (waterMarkersBean != null && (results = waterMarkersBean.getResults()) != null) {
            String string = ContextUtil.getContext().getString(R.string.navigation_charge_marker);
            if (results.containsKey(string)) {
                WaterMarkerBean waterMarkerBean = results.get(string);
                if (waterMarkerBean != null) {
                    WaterMarkerBean.PoseBean.PositionBean position = waterMarkerBean.getPose().getPosition();
                    SrosSendMsgUtil.getInstance().replyGetLaserNavigationOriginPoint(true, "", (int) position.getX(), (int) position.getY(), (float) waterMarkerBean.getPose().getOrientation().getZ(), waterMarkerBean.getFloor());
                    return;
                }
                return;
            }
            SrosSendMsgUtil.getInstance().replyGetLaserNavigationOriginPoint(false, "没有充电桩点位", 0, 0, 0.0f, 0);
        }
    }

    public void setNavigateListener(IWaterNavigateListener iWaterNavigateListener) {
        this.mListener = iWaterNavigateListener;
    }
}
