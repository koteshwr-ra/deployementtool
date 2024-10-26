package mc.csst.com.selfchassislibrary.chassis;

import android.util.Log;
import com.ciot.base.config.CommonConfig;
import com.ciot.base.util.GsonUtils;
import com.ciot.base.util.MyLogUtils;
import java.util.ArrayList;
import java.util.List;
import mc.csst.com.selfchassislibrary.bean.QuaternionBean;
import mc.csst.com.selfchassislibrary.bean.msg.AreaItemBean;
import mc.csst.com.selfchassislibrary.bean.msg.BagRecordRequestBean;
import mc.csst.com.selfchassislibrary.bean.msg.CrossFloorNaviReqBean;
import mc.csst.com.selfchassislibrary.bean.msg.GlobalLocateReqBean;
import mc.csst.com.selfchassislibrary.bean.msg.NaviSettingBean;
import mc.csst.com.selfchassislibrary.bean.msg.PathGetResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.RequestBean;
import mc.csst.com.selfchassislibrary.bean.msg.SendDeletePoiRequestBean;
import mc.csst.com.selfchassislibrary.bean.msg.SendFollowPathRequestBean;
import mc.csst.com.selfchassislibrary.bean.msg.SendPathGetRequestBean;
import mc.csst.com.selfchassislibrary.bean.msg.SendPathRecordRequestBean;
import mc.csst.com.selfchassislibrary.bean.msg.SendPathSaveRequestBean;
import mc.csst.com.selfchassislibrary.bean.msg.SendRecoverPathRequestBean;
import mc.csst.com.selfchassislibrary.bean.msg.SensorFeaturesBean;
import mc.csst.com.selfchassislibrary.bean.msg.SetLayeredMapManagerPencilOpPublishBean;
import mc.csst.com.selfchassislibrary.bean.msg.SetVirtualWallsBean;
import mc.csst.com.selfchassislibrary.bean.msg.SetWayPointBean;
import mc.csst.com.selfchassislibrary.chassis.SelfChassisMsgCallBack;
import mc.csst.com.selfchassislibrary.content.IDContent;
import mc.csst.com.selfchassislibrary.content.NaviSettingContent;
import mc.csst.com.selfchassislibrary.content.ServiceContent;
import mc.csst.com.selfchassislibrary.utils.ConvertorUtils;
import mc.csst.com.selfchassislibrary.utils.MsgManager;
import mc.csst.com.selfchassislibrary.utils.SelfChassisListenerUtils;
import mc.csst.com.selfchassislibrary.websocket.IReceiveMessage;
import mc.csst.com.selfchassislibrary.websocket.WebSocketClientManager;
import okio.ByteString;

public class SelfChassis {
    private static volatile SelfChassis mInstance;
    private final String TAG = CommonConfig.SELF_CHASSIS;
    /* access modifiers changed from: private */
    public OnMessageReceivedCallBack mOnMessageReceivedCallBack;
    private WebSocketClientManager mWebSocketClient = WebSocketClientManager.getInstance();
    public SelfChassisMsgCallBack selfChassisMsgCallBack = new SelfChassisMsgCallBack();

    public interface OnMessageReceivedCallBack {
        void close();

        void messageConnect(boolean z);

        void messageReceive(String str);
    }

    public void setOnMessageReceivedCallBack(OnMessageReceivedCallBack onMessageReceivedCallBack) {
        this.mOnMessageReceivedCallBack = onMessageReceivedCallBack;
    }

    public void setOnMapInfoCallBack(SelfChassisMsgCallBack.OnMapInfoCallBack onMapInfoCallBack) {
        this.selfChassisMsgCallBack.setOnMapInfoCallBack(onMapInfoCallBack);
    }

    public void setOnNavigationManagerCallback(SelfChassisMsgCallBack.OnNavigationManagerCallback onNavigationManagerCallback) {
        this.selfChassisMsgCallBack.setOnNavigationManagerCallback(onNavigationManagerCallback);
    }

    private SelfChassis() {
    }

    public static SelfChassis getInstance() {
        if (mInstance == null) {
            synchronized (SelfChassis.class) {
                if (mInstance == null) {
                    mInstance = new SelfChassis();
                }
            }
        }
        return mInstance;
    }

    public void connectSelfChassis(String str) {
        this.mWebSocketClient.init(str, new MyIReceiveMessage());
        setOnMessageReceivedCallBack(this.selfChassisMsgCallBack);
    }

    public boolean isConnect() {
        return this.mWebSocketClient.isConnect();
    }

    public void disconnectSelfChassis() {
        MyLogUtils.Logw("NAVIGATION_TAG", "SelfChassis disconnectSelfChassis");
        this.mWebSocketClient.close();
    }

    public void sendGetRobotStatus() {
        this.mWebSocketClient.sendMessage(MsgManager.initRobotStatus());
    }

    public void sendGetRobotCurrentMap() {
        this.mWebSocketClient.sendMessage(MsgManager.serviceGetMapInfo());
    }

    public void sendServiceBuildingOperationDelete(String str, String str2) {
        this.mWebSocketClient.sendMessage(MsgManager.serviceBuildingOperationDelete(str, str2));
    }

    public void sendGetMarkerList() {
        this.mWebSocketClient.sendMessage(MsgManager.serviceMarkerOperationGetMarkers());
    }

    public void sendDeleteMarker(String str) {
        this.mWebSocketClient.sendMessage(MsgManager.serviceMarkerManagerDeletePoi(str));
    }

    public void serviceGetVirtualWalls() {
        this.mWebSocketClient.sendMessage(MsgManager.serviceGetVirtualWalls());
    }

    public void serviceVirtualWallOperationDeleteWallsLocal() {
        this.mWebSocketClient.sendMessage(MsgManager.serviceVirtualWallOperationDeleteWallsLocal());
    }

    public void sendGoHome() {
        this.mWebSocketClient.sendMessage(MsgManager.serviceStartRecharge(1, (String) null));
    }

    public void sendInsertMarkerByName(String str, int i) {
        this.mWebSocketClient.sendMessage(MsgManager.insertCurrentPoseMarker(str, i));
    }

    public void sendInsertMarkerByPose(String str, int i, float f, float f2, float f3) {
        QuaternionBean eular2quaternion = ConvertorUtils.eular2quaternion(0.0f, 0.0f, f3);
        SetWayPointBean.MsgBean.WaypointsBean waypointsBean = new SetWayPointBean.MsgBean.WaypointsBean();
        waypointsBean.setBehavior_code(i);
        waypointsBean.setName(str);
        SetWayPointBean.MsgBean.WaypointsBean.PoseBean poseBean = new SetWayPointBean.MsgBean.WaypointsBean.PoseBean();
        SetWayPointBean.MsgBean.WaypointsBean.PoseBean.OrientationBean orientationBean = new SetWayPointBean.MsgBean.WaypointsBean.PoseBean.OrientationBean();
        orientationBean.setW(eular2quaternion.getW());
        orientationBean.setX(eular2quaternion.getX());
        orientationBean.setY(eular2quaternion.getY());
        orientationBean.setZ(eular2quaternion.getZ());
        SetWayPointBean.MsgBean.WaypointsBean.PoseBean.PositionBean positionBean = new SetWayPointBean.MsgBean.WaypointsBean.PoseBean.PositionBean();
        positionBean.setX(f);
        positionBean.setY(f2);
        poseBean.setOrientation(orientationBean);
        poseBean.setPosition(positionBean);
        waypointsBean.setPose(poseBean);
        this.mWebSocketClient.sendMessage(MsgManager.appendWayPoints(waypointsBean));
    }

    public void sendMoveByMarkerName(String str) {
        this.mWebSocketClient.sendMessage(MsgManager.servicePoi(str));
    }

    public void sendMoveByMarkerName(String str, String str2) {
        this.mWebSocketClient.sendMessage(MsgManager.servicePoiId(str, str2));
    }

    public void servicePoiInit(String str) {
        this.mWebSocketClient.sendMessage(MsgManager.servicePoiInit(str));
    }

    public void sendEStop(boolean z) {
        this.mWebSocketClient.sendMessage(MsgManager.softStop(z));
    }

    public void sendMoveByLocation(float f, float f2, float f3) {
        this.mWebSocketClient.sendMessage(MsgManager.sendGoalMsg(f, f2, f3));
    }

    public void sendMoveByLocation(String str, float f, float f2, float f3) {
        this.mWebSocketClient.sendMessage(MsgManager.sendGoalIdMsg(str, f, f2, f3));
    }

    public boolean sendCancelMove() {
        return this.mWebSocketClient.sendMessage(MsgManager.cancelGoalMsg());
    }

    public void send(String str) {
        this.mWebSocketClient.sendMessage(str);
    }

    public void send(ByteString byteString) {
        this.mWebSocketClient.sendMessage(byteString);
    }

    private class MyIReceiveMessage implements IReceiveMessage {
        public void onConnectFailed(Throwable th) {
        }

        public void onMessage(byte[] bArr) {
        }

        private MyIReceiveMessage() {
        }

        public void onConnectSuccess() {
            MyLogUtils.Logd(CommonConfig.SELF_CHASSIS, "onConnectSuccess");
            SelfChassis.this.mOnMessageReceivedCallBack.messageConnect(true);
        }

        public void onClosing() {
            MyLogUtils.Logd(CommonConfig.SELF_CHASSIS, "onClosing");
            SelfChassis.this.mOnMessageReceivedCallBack.messageConnect(false);
            SelfChassis.this.mOnMessageReceivedCallBack.close();
        }

        public void onClosed() {
            MyLogUtils.Logd(CommonConfig.SELF_CHASSIS, "onClosed");
        }

        public void onMessage(String str) {
            SelfChassis.this.mOnMessageReceivedCallBack.messageReceive(str);
        }
    }

    public void initVelocity() {
        if (this.mWebSocketClient.sendMessage(MsgManager.initVelocityMsg())) {
            SelfChassisState.getInstance().setVelocityState(true);
        }
    }

    public void initGetPose() {
        if (this.mWebSocketClient.sendMessage(MsgManager.initGetPoseMsg())) {
            SelfChassisState.getInstance().setGetPoseState(true);
        }
    }

    public void sendPathRecordParams(SendPathRecordRequestBean sendPathRecordRequestBean) {
        sendPathRecordRequestBean.setOp("call_service");
        sendPathRecordRequestBean.setService(ServiceContent.PATH_RECORD);
        sendPathRecordRequestBean.setId(IDContent.TrajectoryDrawing.SERVICE_PATH_RECORD);
        if (this.mWebSocketClient.sendMessage(GsonUtils.toJson(sendPathRecordRequestBean))) {
            MyLogUtils.Logd(CommonConfig.SELF_CHASSIS, "sendPathRecordParams success ");
        }
    }

    public void sendPathGetParams() {
        SendPathGetRequestBean sendPathGetRequestBean = new SendPathGetRequestBean();
        sendPathGetRequestBean.setArgs(new SendPathGetRequestBean.ArgsBean(0));
        sendPathGetRequestBean.setOp("call_service");
        sendPathGetRequestBean.setService(ServiceContent.PATH_GET);
        sendPathGetRequestBean.setId(IDContent.TrajectoryDrawing.SERVICE_GET_PATH);
        if (this.mWebSocketClient.sendMessage(GsonUtils.toJson(sendPathGetRequestBean))) {
            MyLogUtils.Logd(CommonConfig.SELF_CHASSIS, "sendPathGetParams success ");
        }
    }

    public void startBagRecord(String str) {
        BagRecordRequestBean bagRecordRequestBean = new BagRecordRequestBean();
        BagRecordRequestBean.ArgsBean argsBean = new BagRecordRequestBean.ArgsBean();
        argsBean.setName_type(str);
        bagRecordRequestBean.setArgs(argsBean);
        bagRecordRequestBean.setOp("call_service");
        bagRecordRequestBean.setService(ServiceContent.BAG_RECORD);
        bagRecordRequestBean.setId(IDContent.Other.SERVICE_BAG_RECORD);
        if (this.mWebSocketClient.sendMessage(GsonUtils.toJson(bagRecordRequestBean))) {
            MyLogUtils.Logd(CommonConfig.SELF_CHASSIS, "startBagRecord success ");
        }
    }

    public void stopBagRecord() {
        BagRecordRequestBean bagRecordRequestBean = new BagRecordRequestBean();
        bagRecordRequestBean.setOp("call_service");
        bagRecordRequestBean.setService(ServiceContent.BAG_CANCLE);
        bagRecordRequestBean.setId(IDContent.Other.SERVICE_BAG_CANCLE);
        if (this.mWebSocketClient.sendMessage(GsonUtils.toJson(bagRecordRequestBean))) {
            MyLogUtils.Logd(CommonConfig.SELF_CHASSIS, "stopBagRecord success ");
        }
    }

    public void sendPathSave(PathGetResponseBean.ValuesBean.AdjacentListBean.NodesBean nodesBean) {
        SendPathSaveRequestBean sendPathSaveRequestBean = new SendPathSaveRequestBean();
        SendPathSaveRequestBean.ArgsBean argsBean = (SendPathSaveRequestBean.ArgsBean) GsonUtils.fromLocalJson(GsonUtils.toJson(nodesBean), SendPathSaveRequestBean.ArgsBean.class);
        argsBean.setCmd(1);
        sendPathSaveRequestBean.setArgs(argsBean);
        sendPathSaveRequestBean.setOp("call_service");
        sendPathSaveRequestBean.setService(ServiceContent.PATH_SAVE);
        sendPathSaveRequestBean.setId(IDContent.TrajectoryDrawing.SERVICE_SAVE_PATH);
        if (this.mWebSocketClient.sendMessage(GsonUtils.toJson(sendPathSaveRequestBean))) {
            MyLogUtils.Logd(CommonConfig.SELF_CHASSIS, "sendPathSave success ");
        }
    }

    public void sendFollowPathOpParams(SendFollowPathRequestBean sendFollowPathRequestBean) {
        sendFollowPathRequestBean.setOp("call_service");
        sendFollowPathRequestBean.setService(ServiceContent.FOLLOW_PATH_OP);
        sendFollowPathRequestBean.setId(IDContent.TrajectoryDrawing.SERVICE_FOLLOW_PATH_OP);
        if (this.mWebSocketClient.sendMessage(GsonUtils.toJson(sendFollowPathRequestBean))) {
            MyLogUtils.Logd(CommonConfig.SELF_CHASSIS, "sendFollowPathOpParams success ");
        }
    }

    public void sendDeletePoiOpParams(SendDeletePoiRequestBean sendDeletePoiRequestBean) {
        sendDeletePoiRequestBean.setOp("call_service");
        sendDeletePoiRequestBean.setService(ServiceContent.DELETE_POI);
        sendDeletePoiRequestBean.setId(IDContent.TrajectoryDrawing.SERVICE_DELETE_PATH_POI);
        if (this.mWebSocketClient.sendMessage(GsonUtils.toJson(sendDeletePoiRequestBean))) {
            MyLogUtils.Logd(CommonConfig.SELF_CHASSIS, "sendDeletePoiOpParams success ");
        }
    }

    public void sendRecoverLastPathOpParams() {
        SendRecoverPathRequestBean sendRecoverPathRequestBean = new SendRecoverPathRequestBean();
        sendRecoverPathRequestBean.setOp("call_service");
        sendRecoverPathRequestBean.setService(ServiceContent.PATH_MANAGER_CONTROL);
        sendRecoverPathRequestBean.setId(IDContent.TrajectoryDrawing.SERVICE_LOAD_PATH);
        sendRecoverPathRequestBean.setArgs(new SendRecoverPathRequestBean.ArgsBean(1));
        if (this.mWebSocketClient.sendMessage(GsonUtils.toJson(sendRecoverPathRequestBean))) {
            MyLogUtils.Logd(CommonConfig.SELF_CHASSIS, "sendRecoverLastPathOpParams success ");
        }
    }

    public void initLaserData() {
        if (this.mWebSocketClient.sendMessage(MsgManager.initLaserDataMsg())) {
            SelfChassisState.getInstance().setLaserDataState(true);
        }
    }

    public void initSendGoal() {
        if (this.mWebSocketClient.sendMessage(MsgManager.initSendGoalMsg())) {
            SelfChassisState.getInstance().setSendGoalState(true);
        }
    }

    public void initSendGoalId() {
        if (this.mWebSocketClient.sendMessage(MsgManager.initSendGoalIdMsg())) {
            SelfChassisState.getInstance().setSendGoalState(true);
        }
    }

    public void initCancelGoalMsg() {
        if (this.mWebSocketClient.sendMessage(MsgManager.initCancelGoalMsg())) {
            SelfChassisState.getInstance().setCancelGoalState(true);
        }
    }

    public void initSetPose() {
        if (this.mWebSocketClient.sendMessage(MsgManager.initSetPoseMsg())) {
            SelfChassisState.getInstance().setSetPoseState(true);
        }
    }

    public void initGetPath() {
        if (this.mWebSocketClient.sendMessage(MsgManager.initGetPathMsg())) {
            SelfChassisState.getInstance().setGetPathState(true);
        }
    }

    public void initNaviStatus() {
        this.mWebSocketClient.sendMessage(MsgManager.initNaviStatus());
    }

    public void initChargeServerResult() {
        this.mWebSocketClient.sendMessage(MsgManager.initChargeServerResult());
    }

    public void initSetHomePose() {
        this.mWebSocketClient.sendMessage(MsgManager.initSetHomePose());
    }

    public void initSoftStop() {
        this.mWebSocketClient.sendMessage(MsgManager.initSoftStop());
    }

    public void initSetVirtualWalls() {
        this.mWebSocketClient.sendMessage(MsgManager.initSetVirtualWalls());
    }

    public void initAppendVirtualWalls() {
        this.mWebSocketClient.sendMessage(MsgManager.initAppendVirtualWalls());
    }

    public void initSetWayPoint() {
        this.mWebSocketClient.sendMessage(MsgManager.initSetWayPoint());
    }

    public void initAppendWayPoint() {
        this.mWebSocketClient.sendMessage(MsgManager.initAppendWayPoint());
    }

    public void initInsertCurrentPoseMarker() {
        this.mWebSocketClient.sendMessage(MsgManager.initInsertCurrentPoseMarker());
    }

    public void initGetCurrentMap() {
        this.mWebSocketClient.sendMessage(MsgManager.initGetCurrentMap());
    }

    public void initSetLayeredMapManagerPencilOp() {
        this.mWebSocketClient.sendMessage(MsgManager.initSetLayeredMapManagerPencilOp());
    }

    public void initApriltagsBuffer() {
        this.mWebSocketClient.sendMessage(MsgManager.initApriltagsBuffer());
    }

    public void initCurrentTag() {
        this.mWebSocketClient.sendMessage(MsgManager.initCurrentTag());
    }

    public void stopCurrentTag() {
        this.mWebSocketClient.sendMessage(MsgManager.stopCurrentTag());
    }

    public void initUpgradeDownloadStatus() {
        this.mWebSocketClient.sendMessage(MsgManager.initUpgradeDownloadStatus());
    }

    public void initGetRobotList() {
        this.mWebSocketClient.sendMessage(MsgManager.initGetRobotList());
    }

    public void initLocalizationConfidence() {
        this.mWebSocketClient.sendMessage(MsgManager.initLocalizationConfidence());
    }

    public void initLaserSafetyControllerConfidenceThreshold() {
        this.mWebSocketClient.sendMessage(MsgManager.initLaserSafetyControllerConfidenceThreshold());
    }

    public void initObstacleRegion() {
        this.mWebSocketClient.sendMessage(MsgManager.initObstacleRegion());
    }

    public void initNodeManagerLaserSafetyController() {
        this.mWebSocketClient.sendMessage(MsgManager.initNodeManagerLaserSafetyController());
    }

    public void initNodeManagerLaserSafetyRange() {
        this.mWebSocketClient.sendMessage(MsgManager.initNodeManagerLaserSafetyRange());
    }

    public void initMobileBaseSensorsCore() {
        this.mWebSocketClient.sendMessage(MsgManager.initMobileBaseSensorsCore());
    }

    public void initNotification() {
        this.mWebSocketClient.sendMessage(MsgManager.initNotification());
    }

    public void initCurrentMiles() {
        this.mWebSocketClient.sendMessage(MsgManager.initCurrentMiles());
    }

    public void stopVelocity() {
        if (this.mWebSocketClient.sendMessage(MsgManager.stopVelocityMsg())) {
            SelfChassisState.getInstance().setVelocityState(false);
        }
    }

    public void stopGetPose() {
        if (this.mWebSocketClient.sendMessage(MsgManager.stopGetPoseMsg())) {
            SelfChassisState.getInstance().setGetPoseState(false);
        }
    }

    public void stopLaserData() {
        if (this.mWebSocketClient.sendMessage(MsgManager.stopLaserDataMsg())) {
            SelfChassisState.getInstance().setLaserDataState(false);
        }
    }

    public void stopSendGoal() {
        if (this.mWebSocketClient.sendMessage(MsgManager.stopSendGoalMsg())) {
            SelfChassisState.getInstance().setSendGoalState(false);
        }
    }

    public void stopCancelGoal() {
        if (this.mWebSocketClient.sendMessage(MsgManager.stopCancelGoalMsg())) {
            SelfChassisState.getInstance().setCancelGoalState(false);
        }
    }

    public void stopSetPose() {
        if (this.mWebSocketClient.sendMessage(MsgManager.stopSetPoseMsg())) {
            SelfChassisState.getInstance().setSetPoseState(false);
        }
    }

    public void stopGetPath() {
        if (this.mWebSocketClient.sendMessage(MsgManager.stopGetPathMsg())) {
            SelfChassisState.getInstance().setGetPathState(false);
        }
    }

    public void stopRobotStatus() {
        this.mWebSocketClient.sendMessage(MsgManager.stopRobotStatus());
    }

    public void stopNaviStatus() {
        this.mWebSocketClient.sendMessage(MsgManager.stopNaviStatus());
    }

    public void stopChargeServerResult() {
        this.mWebSocketClient.sendMessage(MsgManager.stopChargeServerResult());
    }

    public void stopSetHomePose() {
        this.mWebSocketClient.sendMessage(MsgManager.stopSetHomePose());
    }

    public void stopSoftStop() {
        this.mWebSocketClient.sendMessage(MsgManager.stopSoftStop());
    }

    public void stopSetVirtualWalls() {
        this.mWebSocketClient.sendMessage(MsgManager.stopSetVirtualWalls());
    }

    public void stopAppendVirtualWalls() {
        this.mWebSocketClient.sendMessage(MsgManager.stopAppendVirtualWalls());
    }

    public void stopSetWayPoint() {
        this.mWebSocketClient.sendMessage(MsgManager.stopSetWayPoint());
    }

    public void stopAppendWayPoint() {
        this.mWebSocketClient.sendMessage(MsgManager.stopAppendWayPoint());
    }

    public void stopInsertCurrentPoseMarker() {
        this.mWebSocketClient.sendMessage(MsgManager.stopInsertCurrentPoseMarker());
    }

    public void stopSetLayeredMapManagerPencilOp() {
        this.mWebSocketClient.sendMessage(MsgManager.stopSetLayeredMapManagerPencilOp());
    }

    public void stopGetCurrentMap() {
        this.mWebSocketClient.sendMessage(MsgManager.stopGetCurrentMap());
    }

    public void stopApriltagsBuffer() {
        this.mWebSocketClient.sendMessage(MsgManager.stopApriltagsBuffer());
    }

    public void stopLocalizationConfidence() {
        this.mWebSocketClient.sendMessage(MsgManager.stopLocalizationConfidence());
    }

    public void stopLaserSafetyControllerConfidenceThreshold() {
        this.mWebSocketClient.sendMessage(MsgManager.stopLaserSafetyControllerConfidenceThreshold());
    }

    public void setVelocity(float f, float f2) {
        this.mWebSocketClient.sendMessage(MsgManager.velocityMsg(f, f2));
    }

    public void setPoseMsg(float f, float f2, float f3) {
        this.mWebSocketClient.sendMessage(MsgManager.setPoseMsg(f, f2, f3));
    }

    public void setHomePose(float f, float f2, float f3) {
        this.mWebSocketClient.sendMessage(MsgManager.setHomePose(f, f2, f3));
    }

    public void serviceNodeManagerControl(int i, String str, String str2) {
        this.mWebSocketClient.sendMessage(MsgManager.serviceNodeManagerControl(i, str, str2));
    }

    public void serviceChangeMap(String str, String str2) {
        serviceNodeManagerControl(7, str, str2);
    }

    public void serviceBuilding(String str, String str2) {
        serviceNodeManagerControl(0, str, str2);
    }

    public void serviceSave() {
        serviceNodeManagerControl(3, (String) null, (String) null);
    }

    public void serviceNav() {
        serviceNodeManagerControl(4, (String) null, (String) null);
    }

    public void serviceLayeredMapCmd() {
        this.mWebSocketClient.sendMessage(MsgManager.serviceLayeredMapCmd());
    }

    public void sendGetMap() {
        this.mWebSocketClient.sendMessage(MsgManager.getMapMsg());
    }

    public void sendGetMatchDegreeMap() {
        this.mWebSocketClient.sendMessage(MsgManager.getMapChartMsg());
    }

    public void serviceVirtualWallManagerDeletePoie(String str) {
        this.mWebSocketClient.sendMessage(MsgManager.serviceVirtualWallManagerDeletePoie(str));
    }

    public void appendVirtualWalls(SetVirtualWallsBean.MsgBean.WallsBean wallsBean) {
        this.mWebSocketClient.sendMessage(MsgManager.appendVirtualWalls(wallsBean));
    }

    public void setVirtualWalls(List<SetVirtualWallsBean.MsgBean.WallsBean> list) {
        this.mWebSocketClient.sendMessage(MsgManager.setVirtualWalls(list));
    }

    public void setLayeredMapManagerPencilOp(List<SetLayeredMapManagerPencilOpPublishBean.MsgBean.PointInfoBean> list) {
        this.mWebSocketClient.sendMessage(MsgManager.setLayeredMapManagerPencilOp(list));
    }

    public void clearPencil() {
        ArrayList arrayList = new ArrayList();
        SetLayeredMapManagerPencilOpPublishBean.MsgBean.PointInfoBean pointInfoBean = new SetLayeredMapManagerPencilOpPublishBean.MsgBean.PointInfoBean();
        pointInfoBean.setOp_size(0);
        pointInfoBean.setOp_color(4);
        pointInfoBean.setPy(0.0f);
        pointInfoBean.setPx(0.0f);
        arrayList.add(pointInfoBean);
        this.mWebSocketClient.sendMessage(MsgManager.setLayeredMapManagerPencilOp(arrayList));
    }

    public void getCurrentMap() {
        this.mWebSocketClient.sendMessage(MsgManager.getCurrentMap());
    }

    public void serviceVelocityControl(int i) {
        this.mWebSocketClient.sendMessage(MsgManager.serviceVelocityControl(i));
    }

    public void setVelocityControlListener(SelfChassisListenerUtils.OnBaseSelfChassisListener onBaseSelfChassisListener) {
        SelfChassisListenerUtils.getInstance().setVelocityControlListener(onBaseSelfChassisListener);
    }

    public void serviceVersionUpgrade(int i) {
        this.mWebSocketClient.sendMessage(MsgManager.serviceVersionUpgrade(i));
    }

    public void serviceVersionUpgrade(int i, String str) {
        this.mWebSocketClient.sendMessage(MsgManager.serviceVersionUpgrade(i, str));
    }

    public void addVersionUpgradeListener(SelfChassisListenerUtils.OnBaseSelfChassisListener onBaseSelfChassisListener) {
        SelfChassisListenerUtils.getInstance().addVersionUpgradeListeners(onBaseSelfChassisListener);
    }

    public void removeVersionUpgradeListener(SelfChassisListenerUtils.OnBaseSelfChassisListener onBaseSelfChassisListener) {
        ArrayList<SelfChassisListenerUtils.OnBaseSelfChassisListener> versionUpgradeListeners = SelfChassisListenerUtils.getInstance().getVersionUpgradeListeners();
        if (versionUpgradeListeners != null) {
            versionUpgradeListeners.remove(onBaseSelfChassisListener);
        }
    }

    public void serviceSelfDiagnosis() {
        this.mWebSocketClient.sendMessage(MsgManager.serviceSelfDiagnosis());
    }

    public void setSelfDiagnosisListener(SelfChassisListenerUtils.OnBaseSelfChassisListener onBaseSelfChassisListener) {
        SelfChassisListenerUtils.getInstance().setSelfDiagnosisListener(onBaseSelfChassisListener);
    }

    public void serviceRobotInfo() {
        this.mWebSocketClient.sendMessage(MsgManager.serviceRobotInfo());
    }

    public void setRobotInfoListener(SelfChassisListenerUtils.OnBaseSelfChassisListener onBaseSelfChassisListener) {
        SelfChassisListenerUtils.getInstance().setRobotInfoListener(onBaseSelfChassisListener);
    }

    public void serviceGlobalLocate() {
        this.mWebSocketClient.sendMessage(MsgManager.serviceGlobalLocate());
    }

    public void serviceGlobalLocateLocal(float f, int i, List<GlobalLocateReqBean.ArgsBean.SearchBoundaryBean.PolygonBean.PointsBean> list) {
        this.mWebSocketClient.sendMessage(MsgManager.serviceGlobalLocateLocal(f, i, list));
    }

    public void serviceTagManagerModeCalibration() {
        this.mWebSocketClient.sendMessage(MsgManager.serviceTagManagerMode(1));
    }

    public void serviceTagManagerModeLocation() {
        this.mWebSocketClient.sendMessage(MsgManager.serviceTagManagerMode(2));
    }

    public void serviceTagManagerDeletePoi(String str) {
        this.mWebSocketClient.sendMessage(MsgManager.serviceTagManagerDeletePoi(str));
    }

    public void serviceTagManagerControl() {
        this.mWebSocketClient.sendMessage(MsgManager.serviceTagManagerControl());
    }

    public void serviceTagManagerControlDeleteAll() {
        this.mWebSocketClient.sendMessage(MsgManager.serviceTagManagerControlDeleteAll());
    }

    public void serviceGetMatchThreshold() {
        this.mWebSocketClient.sendMessage(MsgManager.serviceGetMatchThreshold());
    }

    public void setMatchThresholdListener(SelfChassisListenerUtils.OnBaseSelfChassisListener onBaseSelfChassisListener) {
        SelfChassisListenerUtils.getInstance().setMatchThresholdListener(onBaseSelfChassisListener);
    }

    public void laserSafetyControllerConfidenceThreshold(float f) {
        this.mWebSocketClient.sendMessage(MsgManager.laserSafetyControllerConfidenceThreshold(f));
    }

    public void serviceUploadMaps(String str, List<RequestBean.ArgsBean.FloorBean> list) {
        this.mWebSocketClient.sendMessage(MsgManager.serviceUploadMaps(str, list));
    }

    public void serviceDownloadMaps(String str, List<RequestBean.ArgsBean.FloorBean> list) {
        this.mWebSocketClient.sendMessage(MsgManager.serviceDownloadMaps(str, list));
    }

    public void serviceRecordMiles(boolean z) {
        this.mWebSocketClient.sendMessage(MsgManager.serviceRecordMiles(z));
    }

    public void serviceGetTime() {
        this.mWebSocketClient.sendMessage(MsgManager.serviceGetTime());
    }

    public void setGetTimeListener(SelfChassisListenerUtils.OnBaseSelfChassisListener onBaseSelfChassisListener) {
        SelfChassisListenerUtils.getInstance().setTimeListener(onBaseSelfChassisListener);
    }

    public void serviceGetSensors() {
        this.mWebSocketClient.sendMessage(MsgManager.serviceGetSensors());
    }

    public void setGetSensorsListener(SelfChassisListenerUtils.OnBaseSelfChassisListener onBaseSelfChassisListener) {
        SelfChassisListenerUtils.getInstance().setSensorsListener(onBaseSelfChassisListener);
    }

    public void serviceConfigSensors(List<SensorFeaturesBean> list) {
        this.mWebSocketClient.sendMessage(MsgManager.serviceSensorsConfig(list));
    }

    public void serviceSmoothControl(boolean z) {
        serviceVelocityControl(z ? 60 : 61);
    }

    public void serviceGetVelocity() {
        serviceVelocityControl(99);
    }

    public void setMarkersDetailsListener(SelfChassisListenerUtils.OnBaseSelfChassisListener onBaseSelfChassisListener) {
        SelfChassisListenerUtils.getInstance().setMarkersDetailsListener(onBaseSelfChassisListener);
    }

    public void serviceGetMarkersDetails(String str, String str2, int i) {
        this.mWebSocketClient.sendMessage(MsgManager.serviceGetMarkersDetails(str, i, str2));
    }

    public void setLiftControlStatus(SelfChassisListenerUtils.OnBaseSelfChassisListener onBaseSelfChassisListener) {
        SelfChassisListenerUtils.getInstance().setLiftControlStatus(onBaseSelfChassisListener);
    }

    public void initCrossFloorNaviTopic() {
        this.mWebSocketClient.sendMessage(MsgManager.initCrossFloorNaviTopic());
    }

    public void stopCrossFloorNaviTopic() {
        this.mWebSocketClient.sendMessage(MsgManager.stopCrossFloorNaviTopic());
    }

    public void startCrossFloorNavi(CrossFloorNaviReqBean.MsgBean msgBean) {
        this.mWebSocketClient.sendMessage(MsgManager.startCrossFloorNavi(msgBean));
    }

    public void initLiftControlStatus() {
        this.mWebSocketClient.sendMessage(MsgManager.initLiftControlStatus());
    }

    public void stopLiftControlStatus() {
        this.mWebSocketClient.sendMessage(MsgManager.stopLiftControlStatus());
    }

    public void serviceLiftControlCancel() {
        this.mWebSocketClient.sendMessage(MsgManager.serviceLiftControlCancel());
    }

    public void initLiftControlForceCancelTopic() {
        this.mWebSocketClient.sendMessage(MsgManager.initLiftControlForceCancelTopic());
    }

    public void stopLiftControlForceCancelTopic() {
        this.mWebSocketClient.sendMessage(MsgManager.stopLiftControlForceCancelTopic());
    }

    public void liftControlForceCancel() {
        this.mWebSocketClient.sendMessage(MsgManager.liftControlForceCancel());
    }

    public void initAppendArea() {
        this.mWebSocketClient.sendMessage(MsgManager.initAppendArea());
    }

    public void appendAreas(AreaItemBean areaItemBean) {
        this.mWebSocketClient.sendMessage(MsgManager.appendAreas(areaItemBean));
    }

    public void stopAppendArea() {
        this.mWebSocketClient.sendMessage(MsgManager.stopAppendArea());
    }

    public void initSetArea() {
        this.mWebSocketClient.sendMessage(MsgManager.initSetArea());
    }

    public void setAreas(List<AreaItemBean> list) {
        this.mWebSocketClient.sendMessage(MsgManager.setAreas(list));
    }

    public void stopSetArea() {
        this.mWebSocketClient.sendMessage(MsgManager.stopSetArea());
    }

    public void sendDeleteArea(String str) {
        this.mWebSocketClient.sendMessage(MsgManager.serviceAreaDeletePoi(str));
    }

    public void serviceGetAreas() {
        this.mWebSocketClient.sendMessage(MsgManager.serviceGetAreas());
    }

    public void sendGetFloorData() {
        this.mWebSocketClient.sendMessage(MsgManager.serviceGetFloorData());
    }

    public void sendMapRename(String str, String str2, String str3, String str4) {
        this.mWebSocketClient.sendMessage(MsgManager.serviceMapRename(str, str2, str3, str4));
    }

    public void setLiftControlConfigure(String str, String str2, String str3, String str4) {
        this.mWebSocketClient.sendMessage(MsgManager.setLiftControlConfigure(str, str2, str3, str4));
    }

    public void getLiftControlConfigure() {
        this.mWebSocketClient.sendMessage(MsgManager.getLiftControlConfigure());
    }

    public void deleteLiftControlConfigure() {
        this.mWebSocketClient.sendMessage(MsgManager.deleteLiftControlConfigure());
    }

    public void initWayPointStateTopic() {
        this.mWebSocketClient.sendMessage(MsgManager.initWayPointStateTopic());
    }

    public void stopWayPointStateTopic() {
        this.mWebSocketClient.sendMessage(MsgManager.stopWayPointStateTopic());
    }

    public void servicePoiPatrol(int i, int i2, int i3, List<String> list) {
        this.mWebSocketClient.sendMessage(MsgManager.servicePoiPatrol(i, i2, i3, list));
    }

    public void servicePoiPatrolStart(int i, int i2, List<String> list) {
        this.mWebSocketClient.sendMessage(MsgManager.servicePoiPatrol(1, i, i2, list));
    }

    public void servicePoiPatrolPause() {
        this.mWebSocketClient.sendMessage(MsgManager.servicePoiPatrol(2, 0, 0, new ArrayList()));
    }

    public void servicePoiPatrolContinue() {
        this.mWebSocketClient.sendMessage(MsgManager.servicePoiPatrol(3, 0, 0, new ArrayList()));
    }

    public void servicePoiPatrolStop() {
        this.mWebSocketClient.sendMessage(MsgManager.servicePoiPatrol(4, 0, 0, new ArrayList()));
    }

    public void serviceTagPoi(String str) {
        this.mWebSocketClient.sendMessage(MsgManager.serviceTagPoi(str));
    }

    public void serviceAreaManagerRevert() {
        this.mWebSocketClient.sendMessage(MsgManager.serviceAreaManagerRevert());
    }

    public void serviceAreaManagerDelete() {
        this.mWebSocketClient.sendMessage(MsgManager.serviceAreaManagerDelete());
    }

    public void serviceVirtualWallManagerRevert() {
        this.mWebSocketClient.sendMessage(MsgManager.serviceVirtualWallManagerRevert());
    }

    public void initPoseTopic() {
        this.mWebSocketClient.sendMessage(MsgManager.initPoseTopic());
    }

    public void stopInitPoseTopic() {
        this.mWebSocketClient.sendMessage(MsgManager.stopInitPoseTopic());
    }

    public void setInitPose(float f, float f2, float f3) {
        this.mWebSocketClient.sendMessage(MsgManager.setInitPose(f, f2, f3));
    }

    public void initDoorLengthTopic() {
        this.mWebSocketClient.sendMessage(MsgManager.initDoorLengthTopic());
    }

    public void stopDoorLengthTopic() {
        this.mWebSocketClient.sendMessage(MsgManager.stopDoorLengthTopic());
    }

    public void serviceGetDoorLength() {
        this.mWebSocketClient.sendMessage(MsgManager.serviceGetDoorLength());
    }

    public void setDoorLength(float f) {
        this.mWebSocketClient.sendMessage(MsgManager.setDoorLength(f));
    }

    public void initGateLengthTopic() {
        this.mWebSocketClient.sendMessage(MsgManager.initGateLengthTopic());
    }

    public void stopGateLengthTopic() {
        this.mWebSocketClient.sendMessage(MsgManager.stopGateLengthTopic());
    }

    public void serviceGetGateLength() {
        this.mWebSocketClient.sendMessage(MsgManager.serviceGetGateLength());
    }

    public void setGateLength(float f) {
        this.mWebSocketClient.sendMessage(MsgManager.setGateLength(f));
    }

    public void serviceUpCameraAutoCalibrate() {
        this.mWebSocketClient.sendMessage(MsgManager.serviceUpCameraAutoCalibrate());
    }

    public void serviceDownCameraAutoCalibrate() {
        this.mWebSocketClient.sendMessage(MsgManager.serviceDownCameraAutoCalibrate());
    }

    public void startAutomaticMapMatchingCollection() {
        this.mWebSocketClient.sendMessage(MsgManager.serviceMinNumCommand(1));
    }

    public void stopAutomaticMapMatchingCollection() {
        this.mWebSocketClient.sendMessage(MsgManager.serviceMinNumCommand(0));
    }

    public void getMapMatchingStatusInfo() {
        this.mWebSocketClient.sendMessage(MsgManager.serviceMinNumStatus());
    }

    public void getRecommendedMapMatchingThreshold() {
        this.mWebSocketClient.sendMessage(MsgManager.serviceMinNumFindResult());
    }

    public void getUltrasonicDistance() {
        this.mWebSocketClient.sendMessage(MsgManager.serviceGetUltrasonicDistance());
    }

    public void setUltrasonicDistance(int i) {
        this.mWebSocketClient.sendMessage(MsgManager.serviceSetUltrasonicDistance(i));
    }

    public void initUpCamData() {
        if (this.mWebSocketClient.sendMessage(MsgManager.initUpCamDataMsg())) {
            SelfChassisState.getInstance().setUpCamDataState(true);
        }
    }

    public void stopUpCamData() {
        if (this.mWebSocketClient.sendMessage(MsgManager.stopUpCamDataMsg())) {
            SelfChassisState.getInstance().setUpCamDataState(false);
        }
    }

    public void initCurrentRecordPath() {
        if (this.mWebSocketClient.sendMessage(MsgManager.getStartCurrentRecordPathParams())) {
            Log.d(CommonConfig.SELF_CHASSIS, "initCurrentRecordPath: ");
        }
    }

    public void stopCurrentRecordPath() {
        if (this.mWebSocketClient.sendMessage(MsgManager.getStopCurrentRecordPathParams())) {
            Log.d(CommonConfig.SELF_CHASSIS, "stopCurrentRecordPath: ");
        }
    }

    public void sendPathMarkerSync() {
        if (this.mWebSocketClient.sendMessage(MsgManager.getSendPathMarkerSyncParams())) {
            Log.d(CommonConfig.SELF_CHASSIS, "sendPathMarkerSync: ");
        }
    }

    public void initStartPathMarkerSync() {
        if (this.mWebSocketClient.sendMessage(MsgManager.getStartPathMarkerSyncParams())) {
            Log.d(CommonConfig.SELF_CHASSIS, "initStartPathMarkerSync: ");
        }
    }

    public void stopPathMarkerSync() {
        if (this.mWebSocketClient.sendMessage(MsgManager.getStopPathMarkerSyncParams())) {
            Log.d(CommonConfig.SELF_CHASSIS, "stopPathMarkerSync: ");
        }
    }

    public void initStartPathFollowerCancel() {
        if (this.mWebSocketClient.sendMessage(MsgManager.getStartPathFollowerCancelParams())) {
            Log.d(CommonConfig.SELF_CHASSIS, "initStartPathFollowerCancel: ");
        }
    }

    public void sendPathFollowerCancel() {
        if (this.mWebSocketClient.sendMessage(MsgManager.getSendPathFollowerCancelParams())) {
            Log.d(CommonConfig.SELF_CHASSIS, "sendPathFollowerCancel: ");
        }
    }

    public void stopPathFollowerCancel() {
        if (this.mWebSocketClient.sendMessage(MsgManager.getStopPathFollowerCancelParams())) {
            Log.d(CommonConfig.SELF_CHASSIS, "stopPathFollowerCancel: ");
        }
    }

    public void initDownCamData() {
        if (this.mWebSocketClient.sendMessage(MsgManager.initDownCamDataMsg())) {
            SelfChassisState.getInstance().setDownCamDataState(true);
        }
    }

    public void stopDownCamData() {
        if (this.mWebSocketClient.sendMessage(MsgManager.stopDownCamDataMsg())) {
            SelfChassisState.getInstance().setDownCamDataState(false);
        }
    }

    public void configNaviSetting(List<NaviSettingBean> list) {
        this.mWebSocketClient.sendMessage(MsgManager.configNaviSetting(list));
    }

    public void configPointToleranceDistance(float f) {
        NaviSettingBean naviSettingBean = new NaviSettingBean();
        naviSettingBean.setName(NaviSettingContent.XY_GOAL_TOLERANCE);
        naviSettingBean.setValueExt(f);
        ArrayList arrayList = new ArrayList();
        arrayList.add(naviSettingBean);
        configNaviSetting(arrayList);
    }

    public void configPointToleranceAngle(float f) {
        NaviSettingBean naviSettingBean = new NaviSettingBean();
        naviSettingBean.setName(NaviSettingContent.YAW_GOAL_TOLERANCE);
        naviSettingBean.setValueExt((float) (((double) (f / 180.0f)) * 3.141592653589793d));
        ArrayList arrayList = new ArrayList();
        arrayList.add(naviSettingBean);
        configNaviSetting(arrayList);
    }

    public void configMaxVelX(float f) {
        NaviSettingBean naviSettingBean = new NaviSettingBean();
        naviSettingBean.setName(NaviSettingContent.LOCAL_PLANNER_MAX_VEL_X);
        naviSettingBean.setValueExt(f);
        ArrayList arrayList = new ArrayList();
        arrayList.add(naviSettingBean);
        configNaviSetting(arrayList);
    }

    public void configAutoNavi(float f) {
        NaviSettingBean naviSettingBean = new NaviSettingBean();
        naviSettingBean.setName(NaviSettingContent.NAVIGATION_MODE_AUTO_NAVI);
        naviSettingBean.setValueExt(f);
        ArrayList arrayList = new ArrayList();
        arrayList.add(naviSettingBean);
        configNaviSetting(arrayList);
    }

    public void labelCameraExtrinsicConfig(int i) {
        this.mWebSocketClient.sendMessage(MsgManager.labelCameraExtrinsicConfig(i));
    }

    public void locatedModeConfig(int i) {
        this.mWebSocketClient.sendMessage(MsgManager.locatedModeConfig(i));
    }

    public void laserDetectionSetting(int i, double d, boolean z) {
        this.mWebSocketClient.sendMessage(MsgManager.laserDetectionSetting(i, d, z));
    }

    public void serviceCancelUpgradeDownload() {
        this.mWebSocketClient.sendMessage(MsgManager.serviceCancelUpgradeDownload());
    }

    public void configFollowPath(float f) {
        NaviSettingBean naviSettingBean = new NaviSettingBean();
        naviSettingBean.setName(NaviSettingContent.NAVIGATION_MODE_FOLLOW_PATH);
        naviSettingBean.setValueExt(f);
        ArrayList arrayList = new ArrayList();
        arrayList.add(naviSettingBean);
        configNaviSetting(arrayList);
    }

    public void configSafeStopDistance(float f) {
        NaviSettingBean naviSettingBean = new NaviSettingBean();
        naviSettingBean.setName(NaviSettingContent.SAFE_STOP_DISTANCE);
        naviSettingBean.setValueExt(f);
        ArrayList arrayList = new ArrayList();
        arrayList.add(naviSettingBean);
        configNaviSetting(arrayList);
    }

    public void configNavFailureTime(float f) {
        NaviSettingBean naviSettingBean = new NaviSettingBean();
        naviSettingBean.setName(NaviSettingContent.NAV_FAILURE_TIME);
        naviSettingBean.setValueExt(f);
        ArrayList arrayList = new ArrayList();
        arrayList.add(naviSettingBean);
        configNaviSetting(arrayList);
    }

    public void getNaviSetting() {
        ArrayList arrayList = new ArrayList();
        NaviSettingBean naviSettingBean = new NaviSettingBean();
        naviSettingBean.setName(NaviSettingContent.XY_GOAL_TOLERANCE);
        arrayList.add(naviSettingBean);
        NaviSettingBean naviSettingBean2 = new NaviSettingBean();
        naviSettingBean2.setName(NaviSettingContent.YAW_GOAL_TOLERANCE);
        arrayList.add(naviSettingBean2);
        NaviSettingBean naviSettingBean3 = new NaviSettingBean();
        naviSettingBean3.setName(NaviSettingContent.SAFE_STOP_DISTANCE);
        arrayList.add(naviSettingBean3);
        NaviSettingBean naviSettingBean4 = new NaviSettingBean();
        naviSettingBean4.setName(NaviSettingContent.NAV_FAILURE_TIME);
        arrayList.add(naviSettingBean4);
        NaviSettingBean naviSettingBean5 = new NaviSettingBean();
        naviSettingBean5.setName(NaviSettingContent.LOCAL_PLANNER_MAX_VEL_X);
        arrayList.add(naviSettingBean5);
        NaviSettingBean naviSettingBean6 = new NaviSettingBean();
        naviSettingBean5.setName(NaviSettingContent.NAVIGATION_MODE_AUTO_NAVI);
        arrayList.add(naviSettingBean6);
        this.mWebSocketClient.sendMessage(MsgManager.getNaviSetting(arrayList));
    }

    public void getNaviSetting(List<NaviSettingBean> list) {
        this.mWebSocketClient.sendMessage(MsgManager.getNaviSetting(list));
    }

    public void setNaviSettingListener(SelfChassisListenerUtils.OnBaseSelfChassisListener onBaseSelfChassisListener) {
        SelfChassisListenerUtils.getInstance().setNaviSettingListener(onBaseSelfChassisListener);
    }

    public void serviceMarkerOperationDeleteMarkersLocal() {
        this.mWebSocketClient.sendMessage(MsgManager.serviceMarkerOperationDeleteMarkersLocal());
    }

    public void configStationServer(int i, String str, Boolean bool, String str2) {
        MyLogUtils.Logd(CommonConfig.SELF_CHASSIS, "configStationServer:" + str2);
        this.mWebSocketClient.sendMessage(MsgManager.configStationServer(i, str, bool));
    }

    public void serviceUploadMapsMultiRobot(String str, List<RequestBean.ArgsBean.FloorBean> list) {
        this.mWebSocketClient.sendMessage(MsgManager.serviceUploadMaps(0, str, list));
    }

    public void serviceDownloadMapsMultiRobot(String str, List<RequestBean.ArgsBean.FloorBean> list) {
        this.mWebSocketClient.sendMessage(MsgManager.serviceDownloadMaps(0, str, list));
    }
}
