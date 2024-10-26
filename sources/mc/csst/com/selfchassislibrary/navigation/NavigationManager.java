package mc.csst.com.selfchassislibrary.navigation;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.ciot.base.storage.MySpUtils;
import com.ciot.base.util.MyLogUtils;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import mc.csst.com.selfchassislibrary.bean.EularBean;
import mc.csst.com.selfchassislibrary.bean.MapInfoBean;
import mc.csst.com.selfchassislibrary.bean.PointBean;
import mc.csst.com.selfchassislibrary.bean.msg.AreaItemBean;
import mc.csst.com.selfchassislibrary.bean.msg.LayeredMapCmdResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.MarkerOperationGetMarkersResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.PathGetResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.SelfDiagnosisResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.VirtualWallOperationGetWallsResponseBean;
import mc.csst.com.selfchassislibrary.chassis.SelfChassis;
import mc.csst.com.selfchassislibrary.chassis.SelfChassisMsgCallBack;
import mc.csst.com.selfchassislibrary.chassis.SelfChassisState;
import mc.csst.com.selfchassislibrary.utils.ConvertorUtils;

public class NavigationManager implements INavigationManager {
    private static final String TAG = "SELFCHASSIS";
    static INavigationMsgCallback mINavigationMsgCallback;

    public int loadMap() {
        return 0;
    }

    public static NavigationManager getInstance() {
        return NavigationHelperHolder.holder;
    }

    private static class NavigationHelperHolder {
        public static NavigationManager holder = new NavigationManager();

        private NavigationHelperHolder() {
        }
    }

    public void updateSpeakTxt(String str, String str2) {
        MySpUtils.getInstance().put(str, str2);
    }

    public String loadSpeakTxt(String str) {
        return MySpUtils.getInstance().getString(str, "");
    }

    public void connect(String str) {
        SelfChassis.getInstance().setOnNavigationManagerCallback(new MyOnNavigationManagerCallback());
        SelfChassis.getInstance().setOnMapInfoCallBack(new MyOnMapInfoCallBack());
        SelfChassis.getInstance().connectSelfChassis(str);
    }

    public void init(Context context) {
        SelfChassis.getInstance().sendGetMap();
        SelfChassis.getInstance().initGetPose();
        SelfChassis.getInstance().sendGetRobotStatus();
        SelfChassis.getInstance().initNaviStatus();
        SelfChassis.getInstance().initChargeServerResult();
        SelfChassis.getInstance().initMobileBaseSensorsCore();
        SelfChassis.getInstance().initNotification();
        SelfChassis.getInstance().initCurrentMiles();
        SelfChassis.getInstance().initSendGoal();
        SelfChassis.getInstance().initSendGoalId();
        SelfChassis.getInstance().initCancelGoalMsg();
        SelfChassis.getInstance().initSoftStop();
        SystemClock.sleep(200);
        INavigationMsgCallback iNavigationMsgCallback = mINavigationMsgCallback;
        if (iNavigationMsgCallback != null) {
            iNavigationMsgCallback.isInitSuccess(true);
        }
    }

    public void requestAllPositionList() {
        SelfChassis.getInstance().sendGetMarkerList();
    }

    public void sendTarget(String str) {
        SelfChassis.getInstance().sendMoveByMarkerName(str);
    }

    public void sendTarget(String str, String str2) {
        SelfChassis.getInstance().sendMoveByMarkerName(str, str2);
    }

    public void sendTarget(float f, float f2, float f3) {
        SelfChassis.getInstance().sendMoveByLocation(f, f2, f3);
    }

    public boolean cancelNavigation() {
        return SelfChassis.getInstance().sendCancelMove();
    }

    public void release() {
        SelfChassis.getInstance().stopGetCurrentMap();
        SelfChassis.getInstance().stopGetPose();
        SelfChassis.getInstance().disconnectSelfChassis();
        SelfChassis.getInstance().stopPathMarkerSync();
        SelfChassis.getInstance().stopPathFollowerCancel();
    }

    public void setNavigationListener(INavigationMsgCallback iNavigationMsgCallback) {
        mINavigationMsgCallback = iNavigationMsgCallback;
    }

    public void getMap() {
        SelfChassis.getInstance().sendGetMap();
    }

    public void getRobotCurrentMap() {
        SelfChassis.getInstance().sendGetRobotCurrentMap();
    }

    public static class MyOnMapInfoCallBack implements SelfChassisMsgCallBack.OnMapInfoCallBack {
        public void area(List<AreaItemBean> list) {
        }

        public void bjd(List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean> list) {
        }

        public void currentMapArea(double d) {
        }

        public void downCamData(ArrayList<PointBean> arrayList) {
        }

        public void laser(ArrayList<PointBean> arrayList) {
        }

        public void mapMatchDegree(MapInfoBean mapInfoBean, Bitmap bitmap) {
        }

        public void onCurrentRecordPath(ArrayList<PointBean> arrayList) {
        }

        public void onFollowPath(ArrayList<PointBean> arrayList) {
        }

        public void onGetAllRecordPath(List<List<PointBean>> list, PathGetResponseBean pathGetResponseBean) {
        }

        public void path(ArrayList<PointBean> arrayList) {
        }

        public void pose(PointBean pointBean) {
        }

        public void targetName(String str) {
        }

        public void upCamData(ArrayList<PointBean> arrayList) {
        }

        public void virtualWall(List<VirtualWallOperationGetWallsResponseBean.ValuesBean.VwallsBean.WallsBean> list) {
        }

        public void map(MapInfoBean mapInfoBean, Bitmap bitmap) {
            if (NavigationManager.mINavigationMsgCallback != null) {
                NavigationManager.mINavigationMsgCallback.mapBit(mapInfoBean, bitmap);
            }
        }

        public void localMapInfo(String str, String str2) {
            NavigationManager.mINavigationMsgCallback.mapInfo(str, str2);
        }
    }

    public static class MyOnNavigationManagerCallback implements SelfChassisMsgCallBack.OnNavigationManagerCallback {
        public void onLayeredMap(LayeredMapCmdResponseBean layeredMapCmdResponseBean) {
        }

        public void chassisState(SelfChassisState selfChassisState) {
            if (NavigationManager.mINavigationMsgCallback != null) {
                NavigationManager.mINavigationMsgCallback.chassisState(selfChassisState);
            }
        }

        public void contend(boolean z) {
            MyLogUtils.Logd("SELFCHASSIS", "contend: " + z);
            if (NavigationManager.mINavigationMsgCallback != null) {
                NavigationManager.mINavigationMsgCallback.isConnect(z);
            }
        }

        public void onNavigationState(String str, int i) {
            MyLogUtils.Logd("SELFCHASSIS", "id:" + str + "---state:" + i);
            if (NavigationManager.mINavigationMsgCallback != null) {
                NavigationManager.mINavigationMsgCallback.onNavigationState(str, i);
            }
        }

        public void getAllPositionList(List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean> list) {
            float f;
            ArrayList arrayList = new ArrayList();
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    PositionTag positionTag = new PositionTag();
                    MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean waypointsBean = list.get(i);
                    int behavior_code = waypointsBean.getBehavior_code();
                    float f2 = 0.0f;
                    if (waypointsBean.getPose() == null || waypointsBean.getPose().getPosition() == null) {
                        f = 0.0f;
                    } else {
                        f2 = waypointsBean.getPose().getPosition().getX();
                        f = waypointsBean.getPose().getPosition().getY();
                    }
                    String name = waypointsBean.getName();
                    String str = "nav_" + name;
                    String string = MySpUtils.getInstance().getString(str, "");
                    if (!TextUtils.isEmpty(string)) {
                        arrayList.add((PositionTag) new Gson().fromJson(string, PositionTag.class));
                    } else {
                        positionTag.setId(str);
                        positionTag.setName(name);
                        positionTag.setType(behavior_code);
                        positionTag.setX(f2);
                        positionTag.setY(f);
                        Log.d("SELFCHASSIS", "waypointsBean.getPose().getOrientation().getW():" + waypointsBean.getPose().getOrientation().getW() + "x:" + waypointsBean.getPose().getOrientation().getX() + "y" + waypointsBean.getPose().getOrientation().getY() + "z" + waypointsBean.getPose().getOrientation().getZ());
                        EularBean quaternion2eular = ConvertorUtils.quaternion2eular(waypointsBean.getPose().getOrientation().getW(), waypointsBean.getPose().getOrientation().getX(), waypointsBean.getPose().getOrientation().getY(), waypointsBean.getPose().getOrientation().getZ());
                        StringBuilder sb = new StringBuilder();
                        sb.append("eularBean.toString():");
                        sb.append(quaternion2eular.toString());
                        Log.d("SELFCHASSIS", sb.toString());
                        positionTag.setTheta(quaternion2eular.getY());
                        arrayList.add(positionTag);
                    }
                }
            }
            if (NavigationManager.mINavigationMsgCallback != null) {
                NavigationManager.mINavigationMsgCallback.getAllPositionList(arrayList);
            }
        }

        public void onError(int i, String str) {
            MyLogUtils.Logd("SELFCHASSIS", "onError errorCode: " + i);
            if (NavigationManager.mINavigationMsgCallback != null) {
                NavigationManager.mINavigationMsgCallback.onNavigationState(str, i);
            }
        }

        public void obstacleRegion(int i) {
            if (NavigationManager.mINavigationMsgCallback != null) {
                NavigationManager.mINavigationMsgCallback.obstacleRegion(i);
            }
        }

        public void selfDiagnosis(SelfDiagnosisResponseBean selfDiagnosisResponseBean) {
            if (NavigationManager.mINavigationMsgCallback != null) {
                NavigationManager.mINavigationMsgCallback.selfDiagnosis(selfDiagnosisResponseBean);
            }
        }

        public void currentMiles(float f) {
            if (NavigationManager.mINavigationMsgCallback != null) {
                NavigationManager.mINavigationMsgCallback.currentMiles(f);
            }
        }

        public void localizationConfidence(float f) {
            if (NavigationManager.mINavigationMsgCallback != null) {
                NavigationManager.mINavigationMsgCallback.localizationConfidence(f);
            }
        }
    }
}
