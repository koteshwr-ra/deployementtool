package com.ciot.waterchassis;

import com.ciot.tcpclient.ClientIoHandlerAdapter;
import com.ciot.tcpclient.ClientIoSessionManager;
import com.ciot.waterchassis.listener.OnConnectListener;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import kotlinx.coroutines.DebugKt;

public class WaterChassisHelper {
    private static WaterChassisHelper sWaterChassisHelper;
    private ClientIoSessionManager mClientIoSessionManager = ClientIoSessionManager.getInstance();
    private ConnectWaterChassisRunnable mConnectWaterChassisRunnable;
    private OnConnectListener mOnConnectListener;

    private WaterChassisHelper() {
    }

    public static WaterChassisHelper getInstance() {
        if (sWaterChassisHelper == null) {
            synchronized (WaterChassisHelper.class) {
                if (sWaterChassisHelper == null) {
                    sWaterChassisHelper = new WaterChassisHelper();
                }
            }
        }
        return sWaterChassisHelper;
    }

    public void connectWaterChassis(ClientIoHandlerAdapter.OnMessageReceivedCallBack onMessageReceivedCallBack) {
        ConnectWaterChassisRunnable connectWaterChassisRunnable = new ConnectWaterChassisRunnable(onMessageReceivedCallBack);
        this.mConnectWaterChassisRunnable = connectWaterChassisRunnable;
        connectWaterChassisRunnable.setOnConnectListener(this.mOnConnectListener);
        new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new WaterChassisFactory(WaterChassisHelper.class.getSimpleName())).execute(this.mConnectWaterChassisRunnable);
    }

    public void disconnectWaterChassis() {
        ConnectWaterChassisRunnable connectWaterChassisRunnable = this.mConnectWaterChassisRunnable;
        if (connectWaterChassisRunnable != null) {
            connectWaterChassisRunnable.disConnect();
        }
    }

    public void sendGetRobotStatus() {
        this.mClientIoSessionManager.sendChassisMsg("/api/robot_status");
    }

    public void sendGetRobotInfo() {
        this.mClientIoSessionManager.sendChassisMsg("/api/robot_info");
    }

    public void sendGetRobotCurrentMap() {
        this.mClientIoSessionManager.sendChassisMsg("/api/map/get_current_map");
    }

    public void sendGetMarkerList(Integer num) {
        StringBuilder sb = new StringBuilder();
        sb.append("/api/markers/query_list");
        if (num != null) {
            sb.append("?floor=");
            sb.append(num);
        }
        this.mClientIoSessionManager.sendChassisMsg(sb.toString());
    }

    public void sendGetBetweenPath(Map<String, Object> map) {
        this.mClientIoSessionManager.sendChassisMsg(WaterChassisConstants.WATER_MAKE_PLAN, map);
    }

    public void sendDeleteMarker(String str) {
        ClientIoSessionManager clientIoSessionManager = this.mClientIoSessionManager;
        clientIoSessionManager.sendChassisMsg("/api/markers/delete?name=" + str);
    }

    public void sendInsertMarker(String str, int i) {
        ClientIoSessionManager clientIoSessionManager = this.mClientIoSessionManager;
        clientIoSessionManager.sendChassisMsg("/api/markers/insert?name=" + str + "&type=" + i);
    }

    public void sendInsertMarkerByPose(String str, int i, int i2, double d, double d2, double d3) {
        ClientIoSessionManager clientIoSessionManager = this.mClientIoSessionManager;
        clientIoSessionManager.sendChassisMsg("/api/markers/insert_by_pose?name=" + str + "&x=" + d + "&y=" + d2 + "&theta=" + d3 + "&floor=" + i2 + "&type=" + i);
    }

    public void sendGetMapList() {
        this.mClientIoSessionManager.sendChassisMsg("/api/map/list");
    }

    public void sendMoveByMarkerName(String str) {
        ClientIoSessionManager clientIoSessionManager = this.mClientIoSessionManager;
        clientIoSessionManager.sendChassisMsg("/api/move?marker=" + str);
    }

    public void sendMoveByMarkerName(String str, float f, float f2) {
        ClientIoSessionManager clientIoSessionManager = this.mClientIoSessionManager;
        clientIoSessionManager.sendChassisMsg("/api/move?marker=" + str + "&distance_tolerance=" + f + "&occupied_tolerance=" + f2);
    }

    public void sendEStop(boolean z) {
        ClientIoSessionManager clientIoSessionManager = this.mClientIoSessionManager;
        clientIoSessionManager.sendChassisMsg("/api/estop?flag=" + z);
    }

    public void sendSetMaxSpeedRatio(float f) {
        ClientIoSessionManager clientIoSessionManager = this.mClientIoSessionManager;
        clientIoSessionManager.sendChassisMsg("/api/set_params?max_speed_linear=" + f);
    }

    public void sendAdjustMarker(String str) {
        ClientIoSessionManager clientIoSessionManager = this.mClientIoSessionManager;
        clientIoSessionManager.sendChassisMsg("/api/position_adjust?marker=" + str);
    }

    public void sendAdjustByPose(double d, double d2, double d3, int i) {
        ClientIoSessionManager clientIoSessionManager = this.mClientIoSessionManager;
        clientIoSessionManager.sendChassisMsg("/api/position_adjust_by_pose?x=" + d + "&y=" + d2 + "&theta=" + d3 + "&floor=" + i);
    }

    public void sendGetMaxSpeedRatio() {
        this.mClientIoSessionManager.sendChassisMsg("/api/get_params");
    }

    public void sendSetMap(String str, int i) {
        ClientIoSessionManager clientIoSessionManager = this.mClientIoSessionManager;
        clientIoSessionManager.sendChassisMsg("/api/map/set_current_map?map_name=" + str + "&floor=" + i);
    }

    public void manualControl(float f, float f2) {
        ClientIoSessionManager clientIoSessionManager = this.mClientIoSessionManager;
        clientIoSessionManager.sendChassisMsg("/api/joy_control?angular_velocity=" + f + "&linear_velocity=" + f2);
    }

    public void requestHumanDetection(float f) {
        ClientIoSessionManager clientIoSessionManager = this.mClientIoSessionManager;
        clientIoSessionManager.sendChassisMsg("/api/request_data?topic=human_detection&frequency=" + f);
    }

    public void requestRobotVelocity(float f) {
        ClientIoSessionManager clientIoSessionManager = this.mClientIoSessionManager;
        clientIoSessionManager.sendChassisMsg("/api/request_data?topic=robot_velocity&frequency=" + f);
    }

    public void sendMoveByLocationWithDistanceTolerance(float f, float f2, float f3, float f4) {
        ClientIoSessionManager clientIoSessionManager = this.mClientIoSessionManager;
        clientIoSessionManager.sendChassisMsg("/api/move?location=" + f + "," + f2 + "," + f3 + "&distance_tolerance=" + f4);
    }

    public void sendShutdownRobot(boolean z) {
        ClientIoSessionManager clientIoSessionManager = this.mClientIoSessionManager;
        clientIoSessionManager.sendChassisMsg("/api/shutdown?reboot=" + z);
    }

    public void getWaterChassisVersion() {
        this.mClientIoSessionManager.sendChassisMsg("/api/software/get_version");
    }

    public void detectNewVersion() {
        this.mClientIoSessionManager.sendChassisMsg("/api/software/check_for_update");
    }

    public void updateChassisVersion() {
        this.mClientIoSessionManager.sendChassisMsg("/api/software/update");
    }

    public void getDiagnosisResult() {
        this.mClientIoSessionManager.sendChassisMsg(WaterChassisConstants.WATER_GET_DIAGNOSIS_RESULT);
    }

    public double getTheta(double d, double d2) {
        double atan2 = Math.atan2(d2, d) * 2.0d;
        if (atan2 > 3.141592653589793d) {
            return atan2 - 6.283185307179586d;
        }
        return atan2 < -3.141592653589793d ? atan2 + 6.283185307179586d : atan2;
    }

    public boolean sendCancelMove() {
        return this.mClientIoSessionManager.sendChassisMsg("/api/move/cancel").booleanValue();
    }

    public void setOnConnectListener(OnConnectListener onConnectListener) {
        this.mOnConnectListener = onConnectListener;
    }

    public void sendScanWifi() {
        this.mClientIoSessionManager.sendChassisMsg(WaterChassisConstants.WATER_SCAN_WIFI);
    }

    public String sendHotspotConfig(boolean z, String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(WaterChassisConstants.WATER_HOTSPOT_CONFIG);
        sb.append("?on_off=");
        sb.append(z ? DebugKt.DEBUG_PROPERTY_VALUE_ON : DebugKt.DEBUG_PROPERTY_VALUE_OFF);
        if (z) {
            sb.append("&SSID=" + str);
            sb.append("&password=" + str2);
        }
        String sb2 = sb.toString();
        this.mClientIoSessionManager.sendChassisMsg(sb2);
        return sb2;
    }

    public void sendGetBridgeStatus() {
        this.mClientIoSessionManager.sendChassisMsg(WaterChassisConstants.WATER_GET_BRIDGE_STATUS);
    }

    public void sendGetRobotVersion() {
        this.mClientIoSessionManager.sendChassisMsg("/api/software/get_version");
    }
}
