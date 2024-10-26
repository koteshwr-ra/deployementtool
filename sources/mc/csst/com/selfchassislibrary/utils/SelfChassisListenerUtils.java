package mc.csst.com.selfchassislibrary.utils;

import java.util.ArrayList;
import java.util.List;
import mc.csst.com.selfchassislibrary.utils.eventbus.SelfChassisEventMsg;

public class SelfChassisListenerUtils {
    private static SelfChassisListenerUtils mInstance;
    private OnBaseSelfChassisListener areaDeletePoiListener;
    private OnBaseSelfChassisListener areasInfoListener;
    private OnConnectedListener connectListener;
    private OnBaseSelfChassisListener crossFloorCancelListener;
    private OnBaseSelfChassisListener deletePoiListener;
    private OnBaseSelfChassisListener doorLengthListener;
    private OnBaseSelfChassisListener gateLengthListener;
    private OnBaseSelfChassisListener globalLocateListener;
    private OnBaseSelfChassisListener globalLocateLocalListener;
    private OnBaseSelfChassisListener liftConfigureListener;
    private OnBaseSelfChassisListener liftControlStatus;
    private ArrayList<OnBaseSelfChassisListener> mDownCameraAutoCalibrateListeners = new ArrayList<>();
    private ArrayList<OnBaseSelfChassisListener> mUpCameraAutoCalibrateListeners = new ArrayList<>();
    private OnBaseSelfChassisListener mapRenameListener;
    private OnBaseSelfChassisListener markersDetailsListener;
    private List<OnBaseSelfChassisListener> markersListeners = null;
    private OnBaseSelfChassisListener matchThresholdListener;
    private OnBaseSelfChassisListener minNumCommandListener;
    private OnBaseSelfChassisListener minNumFindResultListener;
    private OnBaseSelfChassisListener minNumStatusListener;
    private OnBaseSelfChassisListener naviSettingListener;
    private OnBaseSelfChassisListener nodeManagerControlListener;
    private ArrayList<OnBaseSelfChassisListener> notificationsListeners = new ArrayList<>();
    private OnBaseSelfChassisListener robotInfoListener;
    private OnBaseSelfChassisListener selfDiagnosisListener;
    private OnBaseSelfChassisListener sendPoiPatrolListener;
    private OnBaseSelfChassisListener sensorsListener;
    private OnBaseSelfChassisListener tagManagerDelPoiListener;
    private OnBaseSelfChassisListener tagManagerModeListListener;
    private OnBaseSelfChassisListener tagManagerModeListener;
    private OnBaseSelfChassisListener tagManagerSaveListener;
    private OnBaseSelfChassisListener timeListener;
    private OnBaseSelfChassisListener ultrasonicDistanceListener;
    private OnBaseSelfChassisListener upgradeDownloadStatusListener = null;
    private OnBaseSelfChassisListener velocityControlListener;
    private ArrayList<OnBaseSelfChassisListener> versionUpgradeListeners;
    private OnBaseSelfChassisListener waypointStatusListener;

    public interface OnBaseSelfChassisListener {
        void onSelfChassisMsg(SelfChassisEventMsg selfChassisEventMsg);
    }

    public interface OnConnectedListener {
        void connected(boolean z);
    }

    public static SelfChassisListenerUtils getInstance() {
        if (mInstance == null) {
            synchronized (SelfChassisListenerUtils.class) {
                if (mInstance == null) {
                    mInstance = new SelfChassisListenerUtils();
                }
            }
        }
        return mInstance;
    }

    public OnConnectedListener getConnectListener() {
        return this.connectListener;
    }

    public void setConnectListener(OnConnectedListener onConnectedListener) {
        this.connectListener = onConnectedListener;
    }

    public OnBaseSelfChassisListener getGlobalLocateListener() {
        return this.globalLocateListener;
    }

    public void setGlobalLocateListener(OnBaseSelfChassisListener onBaseSelfChassisListener) {
        this.globalLocateListener = onBaseSelfChassisListener;
    }

    public OnBaseSelfChassisListener getLiftConfigureListener() {
        return this.liftConfigureListener;
    }

    public void setLiftConfigureListener(OnBaseSelfChassisListener onBaseSelfChassisListener) {
        this.liftConfigureListener = onBaseSelfChassisListener;
    }

    public OnBaseSelfChassisListener getMapRenameListener() {
        return this.mapRenameListener;
    }

    public void setMapRenameListener(OnBaseSelfChassisListener onBaseSelfChassisListener) {
        this.mapRenameListener = onBaseSelfChassisListener;
    }

    public OnBaseSelfChassisListener getMatchThresholdListener() {
        return this.matchThresholdListener;
    }

    public void setMatchThresholdListener(OnBaseSelfChassisListener onBaseSelfChassisListener) {
        this.matchThresholdListener = onBaseSelfChassisListener;
    }

    public OnBaseSelfChassisListener getRobotInfoListener() {
        return this.robotInfoListener;
    }

    public void setRobotInfoListener(OnBaseSelfChassisListener onBaseSelfChassisListener) {
        this.robotInfoListener = onBaseSelfChassisListener;
    }

    public ArrayList<OnBaseSelfChassisListener> getVersionUpgradeListeners() {
        return this.versionUpgradeListeners;
    }

    public void addVersionUpgradeListeners(OnBaseSelfChassisListener onBaseSelfChassisListener) {
        if (this.versionUpgradeListeners == null) {
            this.versionUpgradeListeners = new ArrayList<>();
        }
        this.versionUpgradeListeners.add(onBaseSelfChassisListener);
    }

    public OnBaseSelfChassisListener getSelfDiagnosisListener() {
        return this.selfDiagnosisListener;
    }

    public void setSelfDiagnosisListener(OnBaseSelfChassisListener onBaseSelfChassisListener) {
        this.selfDiagnosisListener = onBaseSelfChassisListener;
    }

    public OnBaseSelfChassisListener getTimeListener() {
        return this.timeListener;
    }

    public void setTimeListener(OnBaseSelfChassisListener onBaseSelfChassisListener) {
        this.timeListener = onBaseSelfChassisListener;
    }

    public OnBaseSelfChassisListener getSensorsListener() {
        return this.sensorsListener;
    }

    public void setSensorsListener(OnBaseSelfChassisListener onBaseSelfChassisListener) {
        this.sensorsListener = onBaseSelfChassisListener;
    }

    public OnBaseSelfChassisListener getVelocityControlListener() {
        return this.velocityControlListener;
    }

    public void setVelocityControlListener(OnBaseSelfChassisListener onBaseSelfChassisListener) {
        this.velocityControlListener = onBaseSelfChassisListener;
    }

    public OnBaseSelfChassisListener getMarkersDetailsListener() {
        return this.markersDetailsListener;
    }

    public void setMarkersDetailsListener(OnBaseSelfChassisListener onBaseSelfChassisListener) {
        this.markersDetailsListener = onBaseSelfChassisListener;
    }

    public OnBaseSelfChassisListener getLiftControlStatus() {
        return this.liftControlStatus;
    }

    public void setLiftControlStatus(OnBaseSelfChassisListener onBaseSelfChassisListener) {
        this.liftControlStatus = onBaseSelfChassisListener;
    }

    public OnBaseSelfChassisListener getAreasInfoListener() {
        return this.areasInfoListener;
    }

    public void setAreasInfoListener(OnBaseSelfChassisListener onBaseSelfChassisListener) {
        this.areasInfoListener = onBaseSelfChassisListener;
    }

    public OnBaseSelfChassisListener getAreaDeletePoiListener() {
        return this.areaDeletePoiListener;
    }

    public void setAreaDeletePoiListener(OnBaseSelfChassisListener onBaseSelfChassisListener) {
        this.areaDeletePoiListener = onBaseSelfChassisListener;
    }

    public OnBaseSelfChassisListener getUpgradeDownloadStatusListener() {
        return this.upgradeDownloadStatusListener;
    }

    public void setUpgradeDownloadStatusListener(OnBaseSelfChassisListener onBaseSelfChassisListener) {
        this.upgradeDownloadStatusListener = onBaseSelfChassisListener;
    }

    public OnBaseSelfChassisListener getCrossFloorCancelListener() {
        return this.crossFloorCancelListener;
    }

    public void setCrossFloorCancelListener(OnBaseSelfChassisListener onBaseSelfChassisListener) {
        this.crossFloorCancelListener = onBaseSelfChassisListener;
    }

    public OnBaseSelfChassisListener getDeletePoiListener() {
        return this.deletePoiListener;
    }

    public void setDeletePoiListener(OnBaseSelfChassisListener onBaseSelfChassisListener) {
        this.deletePoiListener = onBaseSelfChassisListener;
    }

    public List<OnBaseSelfChassisListener> getMarkersListeners() {
        return this.markersListeners;
    }

    public void setMarkersListeners(List<OnBaseSelfChassisListener> list) {
        this.markersListeners = list;
    }

    public void addMarkersListeners(OnBaseSelfChassisListener onBaseSelfChassisListener) {
        if (this.markersListeners == null) {
            this.markersListeners = new ArrayList();
        }
        this.markersListeners.add(onBaseSelfChassisListener);
    }

    public OnBaseSelfChassisListener getNodeManagerControlListener() {
        return this.nodeManagerControlListener;
    }

    public void setNodeManagerControlListener(OnBaseSelfChassisListener onBaseSelfChassisListener) {
        this.nodeManagerControlListener = onBaseSelfChassisListener;
    }

    public OnBaseSelfChassisListener getGlobalLocateLocalListener() {
        return this.globalLocateLocalListener;
    }

    public void setGlobalLocateLocalListener(OnBaseSelfChassisListener onBaseSelfChassisListener) {
        this.globalLocateLocalListener = onBaseSelfChassisListener;
    }

    public OnBaseSelfChassisListener getTagManagerModeListener() {
        return this.tagManagerModeListener;
    }

    public void setTagManagerModeListener(OnBaseSelfChassisListener onBaseSelfChassisListener) {
        this.tagManagerModeListener = onBaseSelfChassisListener;
    }

    public OnBaseSelfChassisListener getTagManagerModeListListener() {
        return this.tagManagerModeListListener;
    }

    public void setTagManagerModeListListener(OnBaseSelfChassisListener onBaseSelfChassisListener) {
        this.tagManagerModeListListener = onBaseSelfChassisListener;
    }

    public OnBaseSelfChassisListener getTagManagerDelPoiListener() {
        return this.tagManagerDelPoiListener;
    }

    public void setTagManagerDelPoiListener(OnBaseSelfChassisListener onBaseSelfChassisListener) {
        this.tagManagerDelPoiListener = onBaseSelfChassisListener;
    }

    public OnBaseSelfChassisListener getTagManagerSaveListener() {
        return this.tagManagerSaveListener;
    }

    public void setTagManagerSaveListener(OnBaseSelfChassisListener onBaseSelfChassisListener) {
        this.tagManagerSaveListener = onBaseSelfChassisListener;
    }

    public OnBaseSelfChassisListener getSendPoiPatrolListener() {
        return this.sendPoiPatrolListener;
    }

    public void setSendPoiPatrolListener(OnBaseSelfChassisListener onBaseSelfChassisListener) {
        this.sendPoiPatrolListener = onBaseSelfChassisListener;
    }

    public OnBaseSelfChassisListener getWaypointStatusListener() {
        return this.waypointStatusListener;
    }

    public void setWaypointStatusListener(OnBaseSelfChassisListener onBaseSelfChassisListener) {
        this.waypointStatusListener = onBaseSelfChassisListener;
    }

    public OnBaseSelfChassisListener getDoorLengthListener() {
        return this.doorLengthListener;
    }

    public void setDoorLengthListener(OnBaseSelfChassisListener onBaseSelfChassisListener) {
        this.doorLengthListener = onBaseSelfChassisListener;
    }

    public OnBaseSelfChassisListener getGateLengthListener() {
        return this.gateLengthListener;
    }

    public void setGateLengthListener(OnBaseSelfChassisListener onBaseSelfChassisListener) {
        this.gateLengthListener = onBaseSelfChassisListener;
    }

    public ArrayList<OnBaseSelfChassisListener> getNotificationsListener() {
        return this.notificationsListeners;
    }

    public void setNotificationsListener(ArrayList<OnBaseSelfChassisListener> arrayList) {
        this.notificationsListeners = arrayList;
    }

    public void addNotificationsListener(OnBaseSelfChassisListener onBaseSelfChassisListener) {
        this.notificationsListeners.add(onBaseSelfChassisListener);
    }

    public ArrayList<OnBaseSelfChassisListener> getUpCameraAutoCalibrateListeners() {
        return this.mUpCameraAutoCalibrateListeners;
    }

    public void setUpCameraAutoCalibrateListeners(ArrayList<OnBaseSelfChassisListener> arrayList) {
        this.mUpCameraAutoCalibrateListeners = arrayList;
    }

    public ArrayList<OnBaseSelfChassisListener> getDownCameraAutoCalibrateListeners() {
        return this.mDownCameraAutoCalibrateListeners;
    }

    public void setDownCameraAutoCalibrateListeners(ArrayList<OnBaseSelfChassisListener> arrayList) {
        this.mDownCameraAutoCalibrateListeners = arrayList;
    }

    public void addUpCameraAutoCalibrateListener(OnBaseSelfChassisListener onBaseSelfChassisListener) {
        if (this.mUpCameraAutoCalibrateListeners == null) {
            this.mUpCameraAutoCalibrateListeners = new ArrayList<>();
        }
        if (onBaseSelfChassisListener != null) {
            this.mUpCameraAutoCalibrateListeners.add(onBaseSelfChassisListener);
        }
    }

    public void removeUpCameraAutoCalibrateListener(OnBaseSelfChassisListener onBaseSelfChassisListener) {
        ArrayList<OnBaseSelfChassisListener> arrayList;
        if (onBaseSelfChassisListener != null && (arrayList = this.mUpCameraAutoCalibrateListeners) != null) {
            arrayList.remove(onBaseSelfChassisListener);
        }
    }

    public void addDownCameraAutoCalibrateListener(OnBaseSelfChassisListener onBaseSelfChassisListener) {
        if (this.mDownCameraAutoCalibrateListeners == null) {
            this.mDownCameraAutoCalibrateListeners = new ArrayList<>();
        }
        if (onBaseSelfChassisListener != null) {
            this.mDownCameraAutoCalibrateListeners.add(onBaseSelfChassisListener);
        }
    }

    public void removeDownCameraAutoCalibrateListener(OnBaseSelfChassisListener onBaseSelfChassisListener) {
        ArrayList<OnBaseSelfChassisListener> arrayList;
        if (onBaseSelfChassisListener != null && (arrayList = this.mDownCameraAutoCalibrateListeners) != null) {
            arrayList.remove(onBaseSelfChassisListener);
        }
    }

    public OnBaseSelfChassisListener getMinNumCommandListener() {
        return this.minNumCommandListener;
    }

    public void setMinNumCommandListener(OnBaseSelfChassisListener onBaseSelfChassisListener) {
        this.minNumCommandListener = onBaseSelfChassisListener;
    }

    public OnBaseSelfChassisListener getMinNumStatusListener() {
        return this.minNumStatusListener;
    }

    public void setMinNumStatusListener(OnBaseSelfChassisListener onBaseSelfChassisListener) {
        this.minNumStatusListener = onBaseSelfChassisListener;
    }

    public OnBaseSelfChassisListener getMinNumFindResultListener() {
        return this.minNumFindResultListener;
    }

    public void setMinNumFindResultListener(OnBaseSelfChassisListener onBaseSelfChassisListener) {
        this.minNumFindResultListener = onBaseSelfChassisListener;
    }

    public OnBaseSelfChassisListener getUltrasonicDistanceListener() {
        return this.ultrasonicDistanceListener;
    }

    public void setUltrasonicDistanceListener(OnBaseSelfChassisListener onBaseSelfChassisListener) {
        this.ultrasonicDistanceListener = onBaseSelfChassisListener;
    }

    public OnBaseSelfChassisListener getNaviSettingListener() {
        return this.naviSettingListener;
    }

    public void setNaviSettingListener(OnBaseSelfChassisListener onBaseSelfChassisListener) {
        this.naviSettingListener = onBaseSelfChassisListener;
    }

    public static class ConfigMqtt {
        private static List<OnBaseSelfChassisListener> configMqttListeners = new ArrayList();

        public static List<OnBaseSelfChassisListener> getConfigMqttListeners() {
            return configMqttListeners;
        }

        public static void addConfigMqttListener(OnBaseSelfChassisListener onBaseSelfChassisListener) {
            configMqttListeners.add(onBaseSelfChassisListener);
        }

        public static void removeMqttListener(OnBaseSelfChassisListener onBaseSelfChassisListener) {
            configMqttListeners.remove(onBaseSelfChassisListener);
        }
    }

    public static class RobotList {
        private static List<OnBaseSelfChassisListener> onRobotListListeners = new ArrayList();

        public static void addRobotListListener(OnBaseSelfChassisListener onBaseSelfChassisListener) {
            onRobotListListeners.add(onBaseSelfChassisListener);
        }

        public static List<OnBaseSelfChassisListener> getRobotListListener() {
            return onRobotListListeners;
        }

        public static void removeRobotListListener(OnBaseSelfChassisListener onBaseSelfChassisListener) {
            onRobotListListeners.remove(onBaseSelfChassisListener);
        }
    }
}
