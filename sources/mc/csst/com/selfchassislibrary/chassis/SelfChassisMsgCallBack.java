package mc.csst.com.selfchassislibrary.chassis;

import android.graphics.Bitmap;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.LogUtils;
import com.ciot.base.util.AppSpUtil;
import com.ciot.base.util.GsonUtils;
import com.ciot.base.util.MyLogUtils;
import com.ciot.base.util.StringUtils;
import com.ciot.networklib.bean.DataUpdateEvent;
import com.google.gson.Gson;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import mc.csst.com.selfchassislibrary.bean.LiftControlStatusBean;
import mc.csst.com.selfchassislibrary.bean.MapInfoBean;
import mc.csst.com.selfchassislibrary.bean.PointBean;
import mc.csst.com.selfchassislibrary.bean.WaypointStateBean;
import mc.csst.com.selfchassislibrary.bean.msg.ApriltagsBufferBean;
import mc.csst.com.selfchassislibrary.bean.msg.AreaDeletePoiResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.AreaItemBean;
import mc.csst.com.selfchassislibrary.bean.msg.BagRecordResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.BreakPackageBean;
import mc.csst.com.selfchassislibrary.bean.msg.CamPointCloudDataPublishBean;
import mc.csst.com.selfchassislibrary.bean.msg.CancelUpgradeDownloadBean;
import mc.csst.com.selfchassislibrary.bean.msg.ChargeServerResultPublishBean;
import mc.csst.com.selfchassislibrary.bean.msg.CurrentMilesBean;
import mc.csst.com.selfchassislibrary.bean.msg.CurrentTagBean;
import mc.csst.com.selfchassislibrary.bean.msg.DownCameraAutoCalibrateResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.DownloadMapsResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.GetDoorLengthBean;
import mc.csst.com.selfchassislibrary.bean.msg.GetGateLengthBean;
import mc.csst.com.selfchassislibrary.bean.msg.GetMapFullBean;
import mc.csst.com.selfchassislibrary.bean.msg.GetMapInfoResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.GetMatchDegreeMapFullBean;
import mc.csst.com.selfchassislibrary.bean.msg.GetMatchThresholdBean;
import mc.csst.com.selfchassislibrary.bean.msg.GetPathPublishBean;
import mc.csst.com.selfchassislibrary.bean.msg.GetPosePublishBean;
import mc.csst.com.selfchassislibrary.bean.msg.GetTimeResponse;
import mc.csst.com.selfchassislibrary.bean.msg.GlobalLocateResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.LabelCameraExtrinsicConfigRespBean;
import mc.csst.com.selfchassislibrary.bean.msg.LaserDataPublishBean;
import mc.csst.com.selfchassislibrary.bean.msg.LaserDetectionSettingRespBean;
import mc.csst.com.selfchassislibrary.bean.msg.LayeredMapCmdResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.LiftControlCommandResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.LiftControlConfigureResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.LocalizationConfidenceBean;
import mc.csst.com.selfchassislibrary.bean.msg.LocatedModeConfigRespBean;
import mc.csst.com.selfchassislibrary.bean.msg.MarkerManagerDeletePoiResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.MarkerOperationGetMarkersResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.MarkersDetailsBean;
import mc.csst.com.selfchassislibrary.bean.msg.MinNumCommandResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.MinNumFindResultResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.MinNumStatusResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.MobileBaseSensorsCoreBean;
import mc.csst.com.selfchassislibrary.bean.msg.MultiRobotStationRespBean;
import mc.csst.com.selfchassislibrary.bean.msg.NaviSettingResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.NaviStatusPublishBean;
import mc.csst.com.selfchassislibrary.bean.msg.NodeManagerControlResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.NotificationBean;
import mc.csst.com.selfchassislibrary.bean.msg.ObstacleRegionBean;
import mc.csst.com.selfchassislibrary.bean.msg.PathGetResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.PoiIdResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.PoiInitResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.PoiPatrolResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.PoiResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.RobotInfoResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.RobotListRespBean;
import mc.csst.com.selfchassislibrary.bean.msg.RobotStatusPublishBean;
import mc.csst.com.selfchassislibrary.bean.msg.SelfDiagnosisResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.SendFollowPathResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.SensorResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.StartRechargeResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.TagManagerControlResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.TagManagerDeletePoiResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.TagManagerModeResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.TagManagerNaviResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.UltrasonicDistanceResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.UpCameraAutoCalibrateResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.UpgradeDownloadStatusBean;
import mc.csst.com.selfchassislibrary.bean.msg.UploadMapsResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.VelocityControlResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.VersionUpgradeResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.VirtualWallManagerDeletePoieResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.VirtualWallOperationGetWallsResponseBean;
import mc.csst.com.selfchassislibrary.bean.response.CurrentRecordPathRespBean;
import mc.csst.com.selfchassislibrary.chassis.SelfChassis;
import mc.csst.com.selfchassislibrary.content.IDContent;
import mc.csst.com.selfchassislibrary.content.OpContent;
import mc.csst.com.selfchassislibrary.content.ServiceContent;
import mc.csst.com.selfchassislibrary.content.TopicContent;
import mc.csst.com.selfchassislibrary.utils.SelfChassisListenerUtils;
import mc.csst.com.selfchassislibrary.utils.eventbus.SelfChassisEventMsg;
import mc.csst.com.selfchassislibrary.utils.eventbus.SelfChassisEventUtils;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

public class SelfChassisMsgCallBack implements SelfChassis.OnMessageReceivedCallBack {
    private static final String CLASS_NAME = SelfChassisMsgCallBack.class.getSimpleName();
    private static final String ID = "id";
    private static final String OP = "op";
    private static final String SERVICE = "service";
    private static final String TAG = "NAVIGATION_TAG";
    private static final String TOPIC = "topic";
    private HashMap<String, StringBuffer> cacheMap = new HashMap<>();
    private OnMapInfoCallBack mOnMapInfoCallBack;
    private OnNavigationManagerCallback mOnNavigationManagerCallback;
    private long preParseErrorTime;

    public interface OnMapInfoCallBack {
        void area(List<AreaItemBean> list);

        void bjd(List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean> list);

        void currentMapArea(double d);

        void downCamData(ArrayList<PointBean> arrayList);

        void laser(ArrayList<PointBean> arrayList);

        void localMapInfo(String str, String str2);

        void map(MapInfoBean mapInfoBean, Bitmap bitmap);

        void mapMatchDegree(MapInfoBean mapInfoBean, Bitmap bitmap);

        void onCurrentRecordPath(ArrayList<PointBean> arrayList);

        void onFollowPath(ArrayList<PointBean> arrayList);

        void onGetAllRecordPath(List<List<PointBean>> list, PathGetResponseBean pathGetResponseBean);

        void path(ArrayList<PointBean> arrayList);

        void pose(PointBean pointBean);

        void targetName(String str);

        void upCamData(ArrayList<PointBean> arrayList);

        void virtualWall(List<VirtualWallOperationGetWallsResponseBean.ValuesBean.VwallsBean.WallsBean> list);
    }

    public interface OnNavigationManagerCallback {
        void chassisState(SelfChassisState selfChassisState);

        void contend(boolean z);

        void currentMiles(float f);

        void getAllPositionList(List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean> list);

        void localizationConfidence(float f);

        void obstacleRegion(int i);

        void onError(int i, String str);

        void onLayeredMap(LayeredMapCmdResponseBean layeredMapCmdResponseBean);

        void onNavigationState(String str, int i);

        void selfDiagnosis(SelfDiagnosisResponseBean selfDiagnosisResponseBean);
    }

    public void close() {
    }

    public void setOnNavigationManagerCallback(OnNavigationManagerCallback onNavigationManagerCallback) {
        this.mOnNavigationManagerCallback = onNavigationManagerCallback;
    }

    public void setOnMapInfoCallBack(OnMapInfoCallBack onMapInfoCallBack) {
        this.mOnMapInfoCallBack = onMapInfoCallBack;
    }

    private void enqueueCacheData(String str, String str2) {
        if (this.cacheMap.get(str) == null) {
            this.cacheMap.put(str, new StringBuffer());
        }
        this.cacheMap.get(str).append(str2);
    }

    private String dequeueCacheData(String str) {
        if (this.cacheMap.get(str) == null) {
            return "";
        }
        String stringBuffer = this.cacheMap.get(str).toString();
        this.cacheMap.remove(str);
        return stringBuffer;
    }

    public void messageReceive(String str) {
        Log.d("NAVIGATION_TAG", "messageReceive-- " + str);
        if (!TextUtils.isEmpty(str)) {
            try {
                if (TextUtils.equals(OpContent.FRAGMENT, new JSONObject(str).getString(OP))) {
                    BreakPackageBean breakPackageBean = (BreakPackageBean) GsonUtils.fromLocalJson(str, BreakPackageBean.class);
                    int total = breakPackageBean.getTotal();
                    int num = breakPackageBean.getNum();
                    enqueueCacheData(breakPackageBean.getTopic(), breakPackageBean.getData());
                    if (num == total - 1) {
                        dataParse(dequeueCacheData(breakPackageBean.getTopic()));
                        return;
                    }
                    return;
                }
                dataParse(str);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void dataParse(java.lang.String r27) {
        /*
            r26 = this;
            r1 = r26
            r2 = r27
            java.lang.String r0 = "id"
            java.lang.String r3 = "NAVIGATION_TAG"
            boolean r4 = android.text.TextUtils.isEmpty(r27)
            if (r4 == 0) goto L_0x000f
            return
        L_0x000f:
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ Exception -> 0x0632 }
            r4.<init>(r2)     // Catch:{ Exception -> 0x0632 }
            java.lang.String r5 = "op"
            java.lang.String r5 = r4.getString(r5)     // Catch:{ Exception -> 0x0632 }
            java.lang.String r6 = ""
            boolean r7 = r4.has(r0)     // Catch:{ Exception -> 0x0632 }
            if (r7 == 0) goto L_0x0026
            java.lang.String r6 = r4.getString(r0)     // Catch:{ Exception -> 0x0632 }
        L_0x0026:
            java.lang.String r0 = "service_response"
            boolean r0 = android.text.TextUtils.equals(r0, r5)     // Catch:{ Exception -> 0x0632 }
            r8 = 10
            r9 = 5
            r10 = 15
            r11 = 4
            r12 = 20
            r13 = 3
            r14 = 13
            r15 = 14
            r16 = 21
            r17 = 8
            r18 = 18
            r19 = 17
            r20 = 19
            r21 = 9
            r22 = 16
            r23 = -1
            r24 = 1
            r25 = 0
            r7 = 2
            if (r0 == 0) goto L_0x04b3
            java.lang.String r0 = "service"
            java.lang.String r0 = r4.getString(r0)     // Catch:{ Exception -> 0x0632 }
            int r4 = r0.hashCode()     // Catch:{ Exception -> 0x0632 }
            switch(r4) {
                case -2126383280: goto L_0x02cb;
                case -2033918422: goto L_0x02c0;
                case -1986614578: goto L_0x02b5;
                case -1874442323: goto L_0x02aa;
                case -1693541271: goto L_0x029f;
                case -1669755617: goto L_0x0294;
                case -1643488926: goto L_0x0289;
                case -1537925911: goto L_0x027e;
                case -1468927683: goto L_0x0274;
                case -1458264442: goto L_0x0268;
                case -1328318956: goto L_0x025c;
                case -1190493893: goto L_0x0250;
                case -1160817287: goto L_0x0244;
                case -1160800388: goto L_0x0238;
                case -1031873799: goto L_0x022c;
                case -959818038: goto L_0x0220;
                case -938525741: goto L_0x0214;
                case -832126554: goto L_0x0208;
                case -807065942: goto L_0x01fd;
                case -698951248: goto L_0x01f1;
                case -584538907: goto L_0x01e5;
                case -406685998: goto L_0x01d9;
                case -270181274: goto L_0x01cd;
                case -214930577: goto L_0x01c1;
                case -112748656: goto L_0x01b5;
                case 1511355: goto L_0x01aa;
                case 73845817: goto L_0x019f;
                case 563822495: goto L_0x0193;
                case 596338688: goto L_0x0187;
                case 768943159: goto L_0x017b;
                case 815571021: goto L_0x016f;
                case 842122286: goto L_0x0164;
                case 893563335: goto L_0x0158;
                case 896816803: goto L_0x014c;
                case 966586721: goto L_0x0140;
                case 1007346270: goto L_0x0134;
                case 1029154263: goto L_0x0128;
                case 1065614604: goto L_0x011c;
                case 1392009199: goto L_0x0110;
                case 1400941332: goto L_0x0105;
                case 1403908064: goto L_0x00f9;
                case 1412864283: goto L_0x00ed;
                case 1420885727: goto L_0x00e1;
                case 1441477624: goto L_0x00d5;
                case 1631247346: goto L_0x00c9;
                case 1645618729: goto L_0x00bd;
                case 1731569475: goto L_0x00b1;
                case 1885514546: goto L_0x00a5;
                case 1918873575: goto L_0x0099;
                case 1931166802: goto L_0x008e;
                case 1999194118: goto L_0x0083;
                case 2040205343: goto L_0x0077;
                case 2075198495: goto L_0x006b;
                case 2131806837: goto L_0x005f;
                default: goto L_0x005d;
            }     // Catch:{ Exception -> 0x0632 }
        L_0x005d:
            goto L_0x02d6
        L_0x005f:
            java.lang.String r4 = "/sensor_setting/ultrasonic"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 43
            goto L_0x02d7
        L_0x006b:
            java.lang.String r4 = "/poi_id"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 16
            goto L_0x02d7
        L_0x0077:
            java.lang.String r4 = "/download_maps"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 25
            goto L_0x02d7
        L_0x0083:
            java.lang.String r4 = "/virtual_wall_operation/get_walls"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 4
            goto L_0x02d7
        L_0x008e:
            java.lang.String r4 = "/marker_operation/get_markers"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 2
            goto L_0x02d7
        L_0x0099:
            java.lang.String r4 = "/downcamera_extrinsic_autocalibrate"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 39
            goto L_0x02d7
        L_0x00a5:
            java.lang.String r4 = "/rosapi/get_time"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 26
            goto L_0x02d7
        L_0x00b1:
            java.lang.String r4 = "/laser_detection/setting"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 13
            goto L_0x02d7
        L_0x00bd:
            java.lang.String r4 = "/get_map_info"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 10
            goto L_0x02d7
        L_0x00c9:
            java.lang.String r4 = "/minnum_findresult"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 42
            goto L_0x02d7
        L_0x00d5:
            java.lang.String r4 = "/area_manager/control"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 34
            goto L_0x02d7
        L_0x00e1:
            java.lang.String r4 = "/labelcamera_extrinsic_config"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 14
            goto L_0x02d7
        L_0x00ed:
            java.lang.String r4 = "/marker_manager/get_markers_details"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 29
            goto L_0x02d7
        L_0x00f9:
            java.lang.String r4 = "/upcamera_extrinsic_autocalibrate"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 38
            goto L_0x02d7
        L_0x0105:
            java.lang.String r4 = "/poi_init"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 7
            goto L_0x02d7
        L_0x0110:
            java.lang.String r4 = "/minnum_command"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 40
            goto L_0x02d7
        L_0x011c:
            java.lang.String r4 = "/tag_manager/delete_poi"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 21
            goto L_0x02d7
        L_0x0128:
            java.lang.String r4 = "/bag_record"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 47
            goto L_0x02d7
        L_0x0134:
            java.lang.String r4 = "/path_manager/path_record"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 45
            goto L_0x02d7
        L_0x0140:
            java.lang.String r4 = "/cancel_upgrade_download"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 12
            goto L_0x02d7
        L_0x014c:
            java.lang.String r4 = "/path_manager/delete_poi"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 51
            goto L_0x02d7
        L_0x0158:
            java.lang.String r4 = "/tag_manager/control"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 22
            goto L_0x02d7
        L_0x0164:
            java.lang.String r4 = "/marker_manager/delete_poi"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 3
            goto L_0x02d7
        L_0x016f:
            java.lang.String r4 = "/change_location_mode"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 15
            goto L_0x02d7
        L_0x017b:
            java.lang.String r4 = "/sensors_config"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 28
            goto L_0x02d7
        L_0x0187:
            java.lang.String r4 = "/bag_cancel"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 48
            goto L_0x02d7
        L_0x0193:
            java.lang.String r4 = "/global_locate"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 19
            goto L_0x02d7
        L_0x019f:
            java.lang.String r4 = "/layered_map_cmd"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 1
            goto L_0x02d7
        L_0x01aa:
            java.lang.String r4 = "/poi"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 6
            goto L_0x02d7
        L_0x01b5:
            java.lang.String r4 = "/path_manager/control"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 52
            goto L_0x02d7
        L_0x01c1:
            java.lang.String r4 = "/lift_control/configure"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 31
            goto L_0x02d7
        L_0x01cd:
            java.lang.String r4 = "/navi_setting"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 44
            goto L_0x02d7
        L_0x01d9:
            java.lang.String r4 = "/robot_info"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 18
            goto L_0x02d7
        L_0x01e5:
            java.lang.String r4 = "/followPath"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 50
            goto L_0x02d7
        L_0x01f1:
            java.lang.String r4 = "/config_mqtt_server"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 53
            goto L_0x02d7
        L_0x01fd:
            java.lang.String r4 = "/virtual_wall_manager/delete_poi"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 5
            goto L_0x02d7
        L_0x0208:
            java.lang.String r4 = "/version_upgrade"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 11
            goto L_0x02d7
        L_0x0214:
            java.lang.String r4 = "/start_recharge"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 9
            goto L_0x02d7
        L_0x0220:
            java.lang.String r4 = "/path_manager/path_save"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 49
            goto L_0x02d7
        L_0x022c:
            java.lang.String r4 = "/get_match_threshold"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 23
            goto L_0x02d7
        L_0x0238:
            java.lang.String r4 = "/tag_manager/navi"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 33
            goto L_0x02d7
        L_0x0244:
            java.lang.String r4 = "/tag_manager/mode"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 20
            goto L_0x02d7
        L_0x0250:
            java.lang.String r4 = "/area_manager/delete_poi"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 30
            goto L_0x02d7
        L_0x025c:
            java.lang.String r4 = "/lift_control/command"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 32
            goto L_0x02d7
        L_0x0268:
            java.lang.String r4 = "/upload_maps"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 24
            goto L_0x02d7
        L_0x0274:
            java.lang.String r4 = "/node_manager_control"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 0
            goto L_0x02d7
        L_0x027e:
            java.lang.String r4 = "/virtual_wall_manager/control"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 35
            goto L_0x02d7
        L_0x0289:
            java.lang.String r4 = "/get_gate_length"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 37
            goto L_0x02d7
        L_0x0294:
            java.lang.String r4 = "/get_door_length"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 36
            goto L_0x02d7
        L_0x029f:
            java.lang.String r4 = "/path_manager/path_get"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 46
            goto L_0x02d7
        L_0x02aa:
            java.lang.String r4 = "/self_diagnosis"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 17
            goto L_0x02d7
        L_0x02b5:
            java.lang.String r4 = "/minnum_status"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 41
            goto L_0x02d7
        L_0x02c0:
            java.lang.String r4 = "/velocity_control"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 27
            goto L_0x02d7
        L_0x02cb:
            java.lang.String r4 = "/poi_patrol"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x02d6
            r0 = 8
            goto L_0x02d7
        L_0x02d6:
            r0 = -1
        L_0x02d7:
            switch(r0) {
                case 0: goto L_0x04ae;
                case 1: goto L_0x04a9;
                case 2: goto L_0x04a4;
                case 3: goto L_0x049f;
                case 4: goto L_0x049a;
                case 5: goto L_0x0495;
                case 6: goto L_0x0490;
                case 7: goto L_0x048b;
                case 8: goto L_0x0486;
                case 9: goto L_0x0481;
                case 10: goto L_0x047c;
                case 11: goto L_0x0477;
                case 12: goto L_0x0472;
                case 13: goto L_0x046d;
                case 14: goto L_0x0468;
                case 15: goto L_0x0463;
                case 16: goto L_0x045e;
                case 17: goto L_0x0459;
                case 18: goto L_0x0451;
                case 19: goto L_0x0454;
                case 20: goto L_0x044c;
                case 21: goto L_0x0447;
                case 22: goto L_0x0442;
                case 23: goto L_0x043d;
                case 24: goto L_0x0438;
                case 25: goto L_0x0433;
                case 26: goto L_0x042e;
                case 27: goto L_0x0429;
                case 28: goto L_0x0424;
                case 29: goto L_0x041f;
                case 30: goto L_0x041a;
                case 31: goto L_0x0415;
                case 32: goto L_0x0410;
                case 33: goto L_0x040b;
                case 34: goto L_0x0406;
                case 35: goto L_0x0401;
                case 36: goto L_0x03fc;
                case 37: goto L_0x03f7;
                case 38: goto L_0x03f2;
                case 39: goto L_0x03ed;
                case 40: goto L_0x03e8;
                case 41: goto L_0x03e3;
                case 42: goto L_0x03de;
                case 43: goto L_0x03d9;
                case 44: goto L_0x03d4;
                case 45: goto L_0x03bb;
                case 46: goto L_0x039c;
                case 47: goto L_0x037d;
                case 48: goto L_0x0361;
                case 49: goto L_0x033e;
                case 50: goto L_0x031f;
                case 51: goto L_0x0300;
                case 52: goto L_0x02e1;
                case 53: goto L_0x02dc;
                default: goto L_0x02da;
            }     // Catch:{ Exception -> 0x0632 }
        L_0x02da:
            goto L_0x0664
        L_0x02dc:
            r26.configStationServer(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x02e1:
            r26.dealPathManagerControl(r27)     // Catch:{ Exception -> 0x0632 }
            java.lang.Object[] r0 = new java.lang.Object[r7]     // Catch:{ Exception -> 0x0632 }
            r0[r25] = r3     // Catch:{ Exception -> 0x0632 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0632 }
            r4.<init>()     // Catch:{ Exception -> 0x0632 }
            java.lang.String r5 = "PATH_MANAGER_CONTROL: "
            r4.append(r5)     // Catch:{ Exception -> 0x0632 }
            r4.append(r2)     // Catch:{ Exception -> 0x0632 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x0632 }
            r0[r24] = r4     // Catch:{ Exception -> 0x0632 }
            com.blankj.utilcode.util.LogUtils.v(r0)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x0300:
            r26.dealDeletePoi(r27)     // Catch:{ Exception -> 0x0632 }
            java.lang.Object[] r0 = new java.lang.Object[r7]     // Catch:{ Exception -> 0x0632 }
            r0[r25] = r3     // Catch:{ Exception -> 0x0632 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0632 }
            r4.<init>()     // Catch:{ Exception -> 0x0632 }
            java.lang.String r5 = "DELETE_POI: "
            r4.append(r5)     // Catch:{ Exception -> 0x0632 }
            r4.append(r2)     // Catch:{ Exception -> 0x0632 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x0632 }
            r0[r24] = r4     // Catch:{ Exception -> 0x0632 }
            com.blankj.utilcode.util.LogUtils.v(r0)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x031f:
            r26.dealFollowPath(r27)     // Catch:{ Exception -> 0x0632 }
            java.lang.Object[] r0 = new java.lang.Object[r7]     // Catch:{ Exception -> 0x0632 }
            r0[r25] = r3     // Catch:{ Exception -> 0x0632 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0632 }
            r4.<init>()     // Catch:{ Exception -> 0x0632 }
            java.lang.String r5 = "FOLLOW_PATH_OP: "
            r4.append(r5)     // Catch:{ Exception -> 0x0632 }
            r4.append(r2)     // Catch:{ Exception -> 0x0632 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x0632 }
            r0[r24] = r4     // Catch:{ Exception -> 0x0632 }
            com.blankj.utilcode.util.LogUtils.v(r0)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x033e:
            mc.csst.com.selfchassislibrary.chassis.SelfChassis r0 = mc.csst.com.selfchassislibrary.chassis.SelfChassis.getInstance()     // Catch:{ Exception -> 0x0632 }
            r0.sendPathGetParams()     // Catch:{ Exception -> 0x0632 }
            java.lang.Object[] r0 = new java.lang.Object[r7]     // Catch:{ Exception -> 0x0632 }
            r0[r25] = r3     // Catch:{ Exception -> 0x0632 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0632 }
            r4.<init>()     // Catch:{ Exception -> 0x0632 }
            java.lang.String r5 = "PATH_SAVE: "
            r4.append(r5)     // Catch:{ Exception -> 0x0632 }
            r4.append(r2)     // Catch:{ Exception -> 0x0632 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x0632 }
            r0[r24] = r4     // Catch:{ Exception -> 0x0632 }
            com.blankj.utilcode.util.LogUtils.v(r0)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x0361:
            java.lang.Object[] r0 = new java.lang.Object[r7]     // Catch:{ Exception -> 0x0632 }
            r0[r25] = r3     // Catch:{ Exception -> 0x0632 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0632 }
            r4.<init>()     // Catch:{ Exception -> 0x0632 }
            java.lang.String r5 = "BAG_CANCLE: "
            r4.append(r5)     // Catch:{ Exception -> 0x0632 }
            r4.append(r2)     // Catch:{ Exception -> 0x0632 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x0632 }
            r0[r24] = r4     // Catch:{ Exception -> 0x0632 }
            com.blankj.utilcode.util.LogUtils.v(r0)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x037d:
            r26.dealBagRecord(r27)     // Catch:{ Exception -> 0x0632 }
            java.lang.Object[] r0 = new java.lang.Object[r7]     // Catch:{ Exception -> 0x0632 }
            r0[r25] = r3     // Catch:{ Exception -> 0x0632 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0632 }
            r4.<init>()     // Catch:{ Exception -> 0x0632 }
            java.lang.String r5 = "BAG_RECORD: "
            r4.append(r5)     // Catch:{ Exception -> 0x0632 }
            r4.append(r2)     // Catch:{ Exception -> 0x0632 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x0632 }
            r0[r24] = r4     // Catch:{ Exception -> 0x0632 }
            com.blankj.utilcode.util.LogUtils.v(r0)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x039c:
            r26.dealPathGet(r27)     // Catch:{ Exception -> 0x0632 }
            java.lang.Object[] r0 = new java.lang.Object[r7]     // Catch:{ Exception -> 0x0632 }
            r0[r25] = r3     // Catch:{ Exception -> 0x0632 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0632 }
            r4.<init>()     // Catch:{ Exception -> 0x0632 }
            java.lang.String r5 = "PATH_GET: "
            r4.append(r5)     // Catch:{ Exception -> 0x0632 }
            r4.append(r2)     // Catch:{ Exception -> 0x0632 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x0632 }
            r0[r24] = r4     // Catch:{ Exception -> 0x0632 }
            com.blankj.utilcode.util.LogUtils.v(r0)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x03bb:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0632 }
            r0.<init>()     // Catch:{ Exception -> 0x0632 }
            java.lang.String r4 = "PATH_RECORD: "
            r0.append(r4)     // Catch:{ Exception -> 0x0632 }
            r0.append(r2)     // Catch:{ Exception -> 0x0632 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0632 }
            android.util.Log.d(r3, r0)     // Catch:{ Exception -> 0x0632 }
            r26.dealPathRecord(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x03d4:
            r26.dealNaviSetting(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x03d9:
            r26.dealUltrasonicDistance(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x03de:
            r26.getMinNumFindResult(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x03e3:
            r26.getMinNumStatus(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x03e8:
            r26.getMinNumCommand(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x03ed:
            r26.getDownCameraAutoCalibrate(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x03f2:
            r26.getUpCameraAutoCalibrate(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x03f7:
            r26.getGateLength(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x03fc:
            r26.getDoorLength(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x0401:
            r26.virtualWallManagerControl(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x0406:
            r26.areaManagerControl(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x040b:
            r26.tagManagerNavi(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x0410:
            r26.liftControlCommand(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x0415:
            r26.liftControlConfigure(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x041a:
            r26.areaDeletePoi(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x041f:
            r26.getMarkersDetails(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x0424:
            r26.sensorsConfig(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x0429:
            r26.velocityControl(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x042e:
            r26.getTime(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x0433:
            r26.downloadMaps(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x0438:
            r26.uploadMaps(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x043d:
            r26.getMatchThreshold(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x0442:
            r26.tagManagerControl(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x0447:
            r26.tagManagerDeletePoi(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x044c:
            r26.tagManagerMode(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x0451:
            r26.robotInfo(r27)     // Catch:{ Exception -> 0x0632 }
        L_0x0454:
            r26.dealGlobalLocate(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x0459:
            r26.selfDiagnosis(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x045e:
            r26.poiId(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x0463:
            r26.locatedModeConfig(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x0468:
            r26.labelCameraExtrinsicConfig(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x046d:
            r26.laserDetectionSetting(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x0472:
            r26.cancelUpgradeDownload(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x0477:
            r26.versionUpgrade(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x047c:
            r26.getMapInfo(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x0481:
            r26.startRecharge(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x0486:
            r26.poiPatrol(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x048b:
            r26.poiInit(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x0490:
            r26.poi(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x0495:
            r26.virtualWallManagerDeletePoie(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x049a:
            r26.virtualWallOperationGetWalls(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x049f:
            r26.markerManagerDeletePoi(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x04a4:
            r26.markerOperationGetMarkers(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x04a9:
            r1.dealLayeredMapCmd(r6, r2)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x04ae:
            r26.nodeManagerControl(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x04b3:
            java.lang.String r0 = "topic"
            java.lang.String r0 = r4.getString(r0)     // Catch:{ Exception -> 0x0632 }
            int r4 = r0.hashCode()     // Catch:{ Exception -> 0x0632 }
            switch(r4) {
                case -1935819767: goto L_0x05ad;
                case -1921473467: goto L_0x05a2;
                case -1797110165: goto L_0x0597;
                case -1548686142: goto L_0x058c;
                case -801585372: goto L_0x0581;
                case -472563187: goto L_0x0577;
                case -413743345: goto L_0x056c;
                case -406601022: goto L_0x0561;
                case -406476107: goto L_0x0557;
                case -309806438: goto L_0x054b;
                case -125538491: goto L_0x053f;
                case 1508045: goto L_0x0534;
                case 42845438: goto L_0x0528;
                case 203442147: goto L_0x051d;
                case 293717225: goto L_0x0511;
                case 308472086: goto L_0x0506;
                case 401311455: goto L_0x04fa;
                case 869897421: goto L_0x04ef;
                case 930300850: goto L_0x04e5;
                case 1302920853: goto L_0x04d9;
                case 1390044956: goto L_0x04ce;
                case 1550794465: goto L_0x04c2;
                default: goto L_0x04c0;
            }     // Catch:{ Exception -> 0x0632 }
        L_0x04c0:
            goto L_0x05b8
        L_0x04c2:
            java.lang.String r4 = "/localization_confidence"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x05b8
            r7 = 11
            goto L_0x05b9
        L_0x04ce:
            java.lang.String r4 = "/navi_status"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x05b8
            r7 = 6
            goto L_0x05b9
        L_0x04d9:
            java.lang.String r4 = "/obstacle_region"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x05b8
            r7 = 12
            goto L_0x05b9
        L_0x04e5:
            java.lang.String r4 = "/global_path"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x05b8
            goto L_0x05b9
        L_0x04ef:
            java.lang.String r4 = "/charge_server/result"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x05b8
            r7 = 7
            goto L_0x05b9
        L_0x04fa:
            java.lang.String r4 = "/mobile_base/commands/upgradeDownloadStatus"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x05b8
            r7 = 10
            goto L_0x05b9
        L_0x0506:
            java.lang.String r4 = "/robot_status"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x05b8
            r7 = 5
            goto L_0x05b9
        L_0x0511:
            java.lang.String r4 = "/current_miles"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x05b8
            r7 = 15
            goto L_0x05b9
        L_0x051d:
            java.lang.String r4 = "/map_chart/mapdata"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x05b8
            r7 = 4
            goto L_0x05b9
        L_0x0528:
            java.lang.String r4 = "/current_record_path"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x05b8
            r7 = 20
            goto L_0x05b9
        L_0x0534:
            java.lang.String r4 = "/map"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x05b8
            r7 = 3
            goto L_0x05b9
        L_0x053f:
            java.lang.String r4 = "/mobile_base/sensors/core"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x05b8
            r7 = 13
            goto L_0x05b9
        L_0x054b:
            java.lang.String r4 = "/notification"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x05b8
            r7 = 14
            goto L_0x05b9
        L_0x0557:
            java.lang.String r4 = "/robot_pose"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x05b8
            r7 = 0
            goto L_0x05b9
        L_0x0561:
            java.lang.String r4 = "/robot_list"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x05b8
            r7 = 21
            goto L_0x05b9
        L_0x056c:
            java.lang.String r4 = "/apriltags_buffer"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x05b8
            r7 = 8
            goto L_0x05b9
        L_0x0577:
            java.lang.String r4 = "/laser_data"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x05b8
            r7 = 1
            goto L_0x05b9
        L_0x0581:
            java.lang.String r4 = "/upcam_data"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x05b8
            r7 = 18
            goto L_0x05b9
        L_0x058c:
            java.lang.String r4 = "/waypoint_state"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x05b8
            r7 = 17
            goto L_0x05b9
        L_0x0597:
            java.lang.String r4 = "/downcam_data"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x05b8
            r7 = 19
            goto L_0x05b9
        L_0x05a2:
            java.lang.String r4 = "/current_tag"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x05b8
            r7 = 9
            goto L_0x05b9
        L_0x05ad:
            java.lang.String r4 = "/lift_control/status"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x0632 }
            if (r0 == 0) goto L_0x05b8
            r7 = 16
            goto L_0x05b9
        L_0x05b8:
            r7 = -1
        L_0x05b9:
            switch(r7) {
                case 0: goto L_0x062e;
                case 1: goto L_0x062a;
                case 2: goto L_0x0626;
                case 3: goto L_0x0622;
                case 4: goto L_0x061e;
                case 5: goto L_0x061a;
                case 6: goto L_0x0613;
                case 7: goto L_0x0616;
                case 8: goto L_0x060f;
                case 9: goto L_0x060b;
                case 10: goto L_0x0607;
                case 11: goto L_0x0603;
                case 12: goto L_0x05ff;
                case 13: goto L_0x05fa;
                case 14: goto L_0x05f5;
                case 15: goto L_0x05f0;
                case 16: goto L_0x05eb;
                case 17: goto L_0x05e6;
                case 18: goto L_0x05e1;
                case 19: goto L_0x05dc;
                case 20: goto L_0x05c3;
                case 21: goto L_0x05be;
                default: goto L_0x05bc;
            }     // Catch:{ Exception -> 0x0632 }
        L_0x05bc:
            goto L_0x0664
        L_0x05be:
            r26.robotList(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x05c3:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0632 }
            r0.<init>()     // Catch:{ Exception -> 0x0632 }
            java.lang.String r4 = "CURRENT_RECORD_PATH : "
            r0.append(r4)     // Catch:{ Exception -> 0x0632 }
            r0.append(r2)     // Catch:{ Exception -> 0x0632 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0632 }
            android.util.Log.d(r3, r0)     // Catch:{ Exception -> 0x0632 }
            r26.getCurrentRecordPath(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x05dc:
            r26.getDownCamData(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x05e1:
            r26.getUpCamData(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x05e6:
            r26.waypointStatus(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x05eb:
            r26.liftControlStatus(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x05f0:
            r26.currentMiles(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x05f5:
            r26.notification(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x05fa:
            r26.mobileBaseSensorsCore(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x05ff:
            r26.obstacleRegion(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x0603:
            r26.localizationConfidence(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x0607:
            r26.upgradeDownloadStatus(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x060b:
            r26.currentTag(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x060f:
            r26.apriltagsBuffer(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x0613:
            r26.naviStatus(r27)     // Catch:{ Exception -> 0x0632 }
        L_0x0616:
            r26.chargeServerResult(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x061a:
            r26.robotStatus(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x061e:
            r26.getMatchDegreeMap(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x0622:
            r26.getMap(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x0626:
            r1.getPath(r2, r5)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x062a:
            r26.getLaserData(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x062e:
            r26.getPose(r27)     // Catch:{ Exception -> 0x0632 }
            goto L_0x0664
        L_0x0632:
            r0 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "dataParse=>jsonStr=>"
            r4.append(r5)
            r4.append(r2)
            java.lang.String r2 = android.util.Log.getStackTraceString(r0)
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            long r4 = java.lang.System.currentTimeMillis()
            long r6 = r1.preParseErrorTime
            long r4 = r4 - r6
            r6 = 3000(0xbb8, double:1.482E-320)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 <= 0) goto L_0x0661
            com.ciot.base.util.MyLogUtils.Loge(r3, r2)
            long r2 = java.lang.System.currentTimeMillis()
            r1.preParseErrorTime = r2
        L_0x0661:
            r0.printStackTrace()
        L_0x0664:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: mc.csst.com.selfchassislibrary.chassis.SelfChassisMsgCallBack.dataParse(java.lang.String):void");
    }

    private void robotList(String str) {
        SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(TopicContent.ROBOT_LIST, (RobotListRespBean) new Gson().fromJson(str, RobotListRespBean.class));
        for (SelfChassisListenerUtils.OnBaseSelfChassisListener onSelfChassisMsg : SelfChassisListenerUtils.RobotList.getRobotListListener()) {
            onSelfChassisMsg.onSelfChassisMsg(selfChassisEventMsg);
        }
    }

    private void cancelUpgradeDownload(String str) {
        SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(ServiceContent.CANCEL_UPGRADE_DOWNLOAD, (CancelUpgradeDownloadBean) new Gson().fromJson(str, CancelUpgradeDownloadBean.class));
        ArrayList<SelfChassisListenerUtils.OnBaseSelfChassisListener> versionUpgradeListeners = SelfChassisListenerUtils.getInstance().getVersionUpgradeListeners();
        if (versionUpgradeListeners != null) {
            Iterator<SelfChassisListenerUtils.OnBaseSelfChassisListener> it = versionUpgradeListeners.iterator();
            while (it.hasNext()) {
                it.next().onSelfChassisMsg(selfChassisEventMsg);
            }
        }
        SelfChassisEventUtils.post(selfChassisEventMsg);
    }

    private void labelCameraExtrinsicConfig(String str) {
        SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(ServiceContent.LABEL_CAMERA_EXTRINSIC_CONFIG);
        selfChassisEventMsg.setData((LabelCameraExtrinsicConfigRespBean) new Gson().fromJson(str, LabelCameraExtrinsicConfigRespBean.class));
        if (SelfChassisListenerUtils.getInstance().getNaviSettingListener() != null) {
            SelfChassisListenerUtils.getInstance().getNaviSettingListener().onSelfChassisMsg(selfChassisEventMsg);
        }
    }

    private void locatedModeConfig(String str) {
        SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(ServiceContent.CHANGE_LOCATION_MODE);
        selfChassisEventMsg.setData((LocatedModeConfigRespBean) new Gson().fromJson(str, LocatedModeConfigRespBean.class));
        if (SelfChassisListenerUtils.getInstance().getNaviSettingListener() != null) {
            SelfChassisListenerUtils.getInstance().getNaviSettingListener().onSelfChassisMsg(selfChassisEventMsg);
        }
    }

    private void laserDetectionSetting(String str) {
        SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(ServiceContent.LASER_DETECTION_SETTING);
        selfChassisEventMsg.setData((LaserDetectionSettingRespBean) new Gson().fromJson(str, LaserDetectionSettingRespBean.class));
        if (SelfChassisListenerUtils.getInstance().getNaviSettingListener() != null) {
            SelfChassisListenerUtils.getInstance().getNaviSettingListener().onSelfChassisMsg(selfChassisEventMsg);
        }
    }

    private void upgradeDownloadStatus(String str) {
        SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(TopicContent.UPGRADE_DOWNLOAD_STATUS);
        selfChassisEventMsg.setData((UpgradeDownloadStatusBean) new Gson().fromJson(str, UpgradeDownloadStatusBean.class));
        if (SelfChassisListenerUtils.getInstance().getUpgradeDownloadStatusListener() != null) {
            SelfChassisListenerUtils.getInstance().getUpgradeDownloadStatusListener().onSelfChassisMsg(selfChassisEventMsg);
        }
    }

    private void configStationServer(String str) {
        SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(ServiceContent.CONFIG_STATION_SERVER);
        selfChassisEventMsg.setData((MultiRobotStationRespBean) new Gson().fromJson(str, MultiRobotStationRespBean.class));
        for (SelfChassisListenerUtils.OnBaseSelfChassisListener next : SelfChassisListenerUtils.ConfigMqtt.getConfigMqttListeners()) {
            if (next != null) {
                next.onSelfChassisMsg(selfChassisEventMsg);
            }
        }
    }

    private void dealPathManagerControl(String str) {
        SelfChassis.getInstance().sendPathGetParams();
    }

    private void dealPathRecord(String str) {
        SelfChassis.getInstance().sendPathGetParams();
    }

    private void dealDeletePoi(String str) {
        SelfChassis.getInstance().sendPathGetParams();
    }

    private void getCurrentRecordPath(String str) {
        CurrentRecordPathRespBean currentRecordPathRespBean = (CurrentRecordPathRespBean) new Gson().fromJson(str, CurrentRecordPathRespBean.class);
        if (currentRecordPathRespBean != null && currentRecordPathRespBean.getMsg() != null) {
            List<Float> path_x = currentRecordPathRespBean.getMsg().getPath_x();
            List<Float> path_y = currentRecordPathRespBean.getMsg().getPath_y();
            if (path_x != null && path_y != null && path_x.size() != 0 && path_y.size() != 0 && path_x.size() == path_y.size()) {
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < path_x.size(); i++) {
                    PointBean pointBean = new PointBean();
                    pointBean.setX(path_x.get(i).floatValue());
                    pointBean.setY(path_y.get(i).floatValue());
                    arrayList.add(pointBean);
                }
                OnMapInfoCallBack onMapInfoCallBack = this.mOnMapInfoCallBack;
                if (onMapInfoCallBack != null) {
                    onMapInfoCallBack.onCurrentRecordPath(arrayList);
                }
            }
        }
    }

    private void dealBagRecord(String str) {
        BagRecordResponseBean bagRecordResponseBean = (BagRecordResponseBean) GsonUtils.fromLocalJson(str, BagRecordResponseBean.class);
    }

    private void dealPathGet(String str) {
        PathGetResponseBean pathGetResponseBean = (PathGetResponseBean) GsonUtils.fromLocalJson(str, PathGetResponseBean.class);
        if (pathGetResponseBean == null || pathGetResponseBean.getValues() == null || pathGetResponseBean.getValues().getAdjacent_list() == null || pathGetResponseBean.getValues().getAdjacent_list().getNodes() == null) {
            dealPathGetCallback((List<List<PointBean>>) null, pathGetResponseBean);
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (PathGetResponseBean.ValuesBean.AdjacentListBean.NodesBean next : pathGetResponseBean.getValues().getAdjacent_list().getNodes()) {
            List<Float> path_x = next.getPath_x();
            List<Float> path_y = next.getPath_y();
            if (path_x == null || path_y == null || path_x.size() == 0 || path_y.size() == 0 || path_x.size() != path_y.size()) {
                dealPathGetCallback((List<List<PointBean>>) null, pathGetResponseBean);
                return;
            }
            ArrayList arrayList2 = new ArrayList();
            for (int i = 0; i < path_x.size(); i++) {
                PointBean pointBean = new PointBean();
                pointBean.setX(path_x.get(i).floatValue());
                pointBean.setY(path_y.get(i).floatValue());
                arrayList2.add(pointBean);
            }
            arrayList.add(arrayList2);
        }
        dealPathGetCallback(arrayList, pathGetResponseBean);
    }

    private void dealPathGetCallback(List<List<PointBean>> list, PathGetResponseBean pathGetResponseBean) {
        OnMapInfoCallBack onMapInfoCallBack = this.mOnMapInfoCallBack;
        if (onMapInfoCallBack != null) {
            onMapInfoCallBack.onGetAllRecordPath(list, pathGetResponseBean);
        }
    }

    private void dealFollowPath(String str) {
        SendFollowPathResponseBean sendFollowPathResponseBean = (SendFollowPathResponseBean) new Gson().fromJson(str, SendFollowPathResponseBean.class);
        if (sendFollowPathResponseBean != null && sendFollowPathResponseBean.getValues() != null && sendFollowPathResponseBean.getValues().getAdjacent_list() != null && sendFollowPathResponseBean.getValues().getAdjacent_list().getNodes() != null) {
            ArrayList arrayList = new ArrayList();
            for (SendFollowPathResponseBean.ValuesBean.AdjacentListBean.NodesBean next : sendFollowPathResponseBean.getValues().getAdjacent_list().getNodes()) {
                List<Float> path_x = next.getPath_x();
                List<Float> path_y = next.getPath_y();
                if (path_x != null && path_y != null && path_x.size() != 0 && path_y.size() != 0 && path_x.size() == path_y.size()) {
                    for (int i = 0; i < path_x.size(); i++) {
                        PointBean pointBean = new PointBean();
                        pointBean.setX(path_x.get(i).floatValue());
                        pointBean.setY(path_y.get(i).floatValue());
                        arrayList.add(pointBean);
                    }
                } else {
                    return;
                }
            }
            OnMapInfoCallBack onMapInfoCallBack = this.mOnMapInfoCallBack;
            if (onMapInfoCallBack != null) {
                onMapInfoCallBack.onFollowPath(arrayList);
            }
        }
    }

    private void dealNaviSetting(String str) {
        SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(ServiceContent.NAVI_SETTING);
        selfChassisEventMsg.setData((NaviSettingResponseBean) GsonUtils.fromLocalJson(str, NaviSettingResponseBean.class));
        if (SelfChassisListenerUtils.getInstance().getNaviSettingListener() != null) {
            SelfChassisListenerUtils.getInstance().getNaviSettingListener().onSelfChassisMsg(selfChassisEventMsg);
        }
    }

    private void dealUltrasonicDistance(String str) {
        SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(ServiceContent.ULTRASONIC_DISTANCE);
        selfChassisEventMsg.setData((UltrasonicDistanceResponseBean) GsonUtils.fromLocalJson(str, UltrasonicDistanceResponseBean.class));
        if (SelfChassisListenerUtils.getInstance().getUltrasonicDistanceListener() != null) {
            SelfChassisListenerUtils.getInstance().getUltrasonicDistanceListener().onSelfChassisMsg(selfChassisEventMsg);
        }
    }

    private void getMinNumFindResult(String str) {
        SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(ServiceContent.MatchThreshold.MINNUM_FINDRESULT);
        selfChassisEventMsg.setData((MinNumFindResultResponseBean) GsonUtils.fromLocalJson(str, MinNumFindResultResponseBean.class));
        if (SelfChassisListenerUtils.getInstance().getMinNumFindResultListener() != null) {
            SelfChassisListenerUtils.getInstance().getMinNumFindResultListener().onSelfChassisMsg(selfChassisEventMsg);
        }
    }

    private void getMinNumStatus(String str) {
        SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(ServiceContent.MatchThreshold.MINNUM_STATUS);
        selfChassisEventMsg.setData((MinNumStatusResponseBean) GsonUtils.fromLocalJson(str, MinNumStatusResponseBean.class));
        if (SelfChassisListenerUtils.getInstance().getMinNumCommandListener() != null) {
            SelfChassisListenerUtils.getInstance().getMinNumCommandListener().onSelfChassisMsg(selfChassisEventMsg);
        }
    }

    private void getMinNumCommand(String str) {
        SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(ServiceContent.MatchThreshold.MINNUM_COMMAND);
        selfChassisEventMsg.setData((MinNumCommandResponseBean) GsonUtils.fromLocalJson(str, MinNumCommandResponseBean.class));
        if (SelfChassisListenerUtils.getInstance().getMinNumCommandListener() != null) {
            SelfChassisListenerUtils.getInstance().getMinNumCommandListener().onSelfChassisMsg(selfChassisEventMsg);
        }
    }

    private void virtualWallManagerControl(String str) {
        MyLogUtils.Logd("NAVIGATION_TAG", "virtualWallManagerControl响应:" + str);
        LayeredMapCmdResponseBean layeredMapCmdResponseBean = (LayeredMapCmdResponseBean) GsonUtils.fromLocalJson(str, LayeredMapCmdResponseBean.class);
    }

    private void areaManagerControl(String str) {
        MyLogUtils.Logd("NAVIGATION_TAG", "areaManagerControl响应:" + str);
        LayeredMapCmdResponseBean layeredMapCmdResponseBean = (LayeredMapCmdResponseBean) GsonUtils.fromLocalJson(str, LayeredMapCmdResponseBean.class);
    }

    private void tagManagerNavi(String str) {
        MyLogUtils.Logd("NAVIGATION_TAG", "tagManagerNavi返回:" + str);
        TagManagerNaviResponseBean tagManagerNaviResponseBean = (TagManagerNaviResponseBean) GsonUtils.fromLocalJson(str, TagManagerNaviResponseBean.class);
    }

    public void messageConnect(boolean z) {
        if (SelfChassisListenerUtils.getInstance().getConnectListener() != null) {
            SelfChassisListenerUtils.getInstance().getConnectListener().connected(z);
        }
        OnNavigationManagerCallback onNavigationManagerCallback = this.mOnNavigationManagerCallback;
        if (onNavigationManagerCallback != null) {
            onNavigationManagerCallback.contend(z);
        }
        SelfChassisEventUtils.post(new SelfChassisEventMsg(SelfChassisEventMsg.CODE_CONNECT_STATE, Boolean.valueOf(z)));
    }

    private void getPath(String str, String str2) {
        GetPathPublishBean getPathPublishBean = (GetPathPublishBean) GsonUtils.fromLocalJson(str, GetPathPublishBean.class);
        List<Float> px = getPathPublishBean.getMsg().getPx();
        List<Float> py = getPathPublishBean.getMsg().getPy();
        if (px != null && py != null && px.size() != 0 && py.size() != 0 && px.size() == py.size()) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < px.size(); i++) {
                PointBean pointBean = new PointBean();
                pointBean.setX(px.get(i).floatValue());
                pointBean.setY(py.get(i).floatValue());
                arrayList.add(pointBean);
            }
            OnMapInfoCallBack onMapInfoCallBack = this.mOnMapInfoCallBack;
            if (onMapInfoCallBack != null) {
                onMapInfoCallBack.path(arrayList);
            }
        }
    }

    private void chargeServerResult(String str) {
        ((ChargeServerResultPublishBean) GsonUtils.fromLocalJson(str, ChargeServerResultPublishBean.class)).getMsg().getData();
    }

    private void apriltagsBuffer(String str) {
        Log.d("NAVIGATION_TAG", "apriltagsBuffer jsonStr:" + str);
        SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(TopicContent.APRIL_TAGS_BUFFER, ((ApriltagsBufferBean) GsonUtils.fromLocalJson(str, ApriltagsBufferBean.class)).getMsg().getWaypoints());
        SelfChassisEventUtils.post(selfChassisEventMsg);
        if (SelfChassisListenerUtils.getInstance().getTagManagerModeListListener() != null) {
            SelfChassisListenerUtils.getInstance().getTagManagerModeListListener().onSelfChassisMsg(selfChassisEventMsg);
        }
    }

    private void currentTag(String str) {
        Log.d("NAVIGATION_TAG", "currentTag jsonStr:" + str);
        CurrentTagBean.MsgBean msg = ((CurrentTagBean) GsonUtils.fromLocalJson(str, CurrentTagBean.class)).getMsg();
        List arrayList = new ArrayList();
        if (!StringUtils.isEmpty(msg.getData())) {
            if (msg.getData().contains(",")) {
                arrayList = Arrays.asList((Object[]) msg.getData().replace(";", "").split(",").clone());
            } else {
                arrayList.add(msg.getData().replace(";", ""));
            }
        }
        SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(TopicContent.CURRENT_TAG, arrayList);
        SelfChassisEventUtils.post(selfChassisEventMsg);
        if (SelfChassisListenerUtils.getInstance().getTagManagerModeListListener() != null) {
            SelfChassisListenerUtils.getInstance().getTagManagerModeListListener().onSelfChassisMsg(selfChassisEventMsg);
        }
    }

    private void localizationConfidence(String str) {
        float data = ((LocalizationConfidenceBean) GsonUtils.fromLocalJson(str, LocalizationConfidenceBean.class)).getMsg().getData();
        OnNavigationManagerCallback onNavigationManagerCallback = this.mOnNavigationManagerCallback;
        if (onNavigationManagerCallback != null) {
            onNavigationManagerCallback.localizationConfidence(data);
        }
        SelfChassisEventUtils.post(new SelfChassisEventMsg(TopicContent.LOCALIZATION_CONFIDENCE, Float.valueOf(data)));
    }

    private void obstacleRegion(String str) {
        int data = ((ObstacleRegionBean) GsonUtils.fromLocalJson(str, ObstacleRegionBean.class)).getMsg().getData();
        OnNavigationManagerCallback onNavigationManagerCallback = this.mOnNavigationManagerCallback;
        if (onNavigationManagerCallback != null) {
            onNavigationManagerCallback.obstacleRegion(data);
        }
    }

    private void mobileBaseSensorsCore(String str) {
        SelfChassisState.getInstance().setOnDock(((MobileBaseSensorsCoreBean) GsonUtils.fromLocalJson(str, MobileBaseSensorsCoreBean.class)).getMsg().getCharger());
    }

    private void notification(String str) {
        MyLogUtils.Logd("NAVIGATION_TAG", "主动推送通知回调：" + str);
        ArrayList<SelfChassisListenerUtils.OnBaseSelfChassisListener> notificationsListener = SelfChassisListenerUtils.getInstance().getNotificationsListener();
        SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(TopicContent.NOTIFICATION, (NotificationBean) GsonUtils.fromLocalJson(str, NotificationBean.class));
        if (notificationsListener != null && notificationsListener.size() > 0) {
            Iterator<SelfChassisListenerUtils.OnBaseSelfChassisListener> it = notificationsListener.iterator();
            while (it.hasNext()) {
                it.next().onSelfChassisMsg(selfChassisEventMsg);
            }
        }
    }

    private void currentMiles(String str) {
        float data = ((CurrentMilesBean) GsonUtils.fromLocalJson(str, CurrentMilesBean.class)).getMsg().getData();
        OnNavigationManagerCallback onNavigationManagerCallback = this.mOnNavigationManagerCallback;
        if (onNavigationManagerCallback != null) {
            onNavigationManagerCallback.currentMiles(data);
        }
    }

    private void naviStatus(String str) {
        NaviStatusPublishBean.MsgBean msg = ((NaviStatusPublishBean) GsonUtils.fromLocalJson(str, NaviStatusPublishBean.class)).getMsg();
        int status = msg.getStatus();
        String id = msg.getGoal_id().getId();
        msg.getText();
        OnNavigationManagerCallback onNavigationManagerCallback = this.mOnNavigationManagerCallback;
        if (onNavigationManagerCallback != null) {
            onNavigationManagerCallback.onNavigationState(id, status);
        }
    }

    private void robotStatus(String str) {
        SelfChassisState instance = SelfChassisState.getInstance();
        RobotStatusPublishBean.MsgBean msg = ((RobotStatusPublishBean) GsonUtils.fromLocalJson(str, RobotStatusPublishBean.class)).getMsg();
        instance.setBuildingName(msg.getCurrent_building_name());
        instance.setSoftStop(msg.isSoft_estop());
        int battery = msg.getBattery();
        instance.setBattery(battery);
        if (instance.isOpenCharge()) {
            if (battery < AppSpUtil.getInstance().getLowBatteryValue()) {
                instance.setRobotIsLowPower(true);
            } else {
                instance.setRobotIsLowPower(false);
            }
        }
        instance.setCharging(msg.getCharger());
        instance.setNavStatus(msg.getNav_status());
        instance.setPatrolStatus(msg.getPatrol_status());
        instance.setFloorName(msg.getCurrent_floor_name());
        instance.setHardStop(msg.isHard_estop());
        instance.setControlStatus(msg.getControl_state());
        String current_goal_name = msg.getCurrent_goal_name();
        instance.setCurrentGoalName(current_goal_name);
        OnMapInfoCallBack onMapInfoCallBack = this.mOnMapInfoCallBack;
        if (onMapInfoCallBack != null) {
            onMapInfoCallBack.targetName(current_goal_name);
        }
        RobotStatusPublishBean.MsgBean.CurrentGoalCoordinateBean current_goal_coordinate = msg.getCurrent_goal_coordinate();
        SelfChassisState.getInstance().setTargetTheta(current_goal_coordinate.getTheta());
        SelfChassisState.getInstance().setTargetX(current_goal_coordinate.getX());
        SelfChassisState.getInstance().setTargetY(current_goal_coordinate.getY());
        OnNavigationManagerCallback onNavigationManagerCallback = this.mOnNavigationManagerCallback;
        if (onNavigationManagerCallback != null) {
            onNavigationManagerCallback.chassisState(instance);
        }
        EventBus.getDefault().post(instance);
    }

    private void getMatchDegreeMap(String str) {
        MyLogUtils.Logd("NAVIGATION_TAG", "getMap：获取地图" + str);
        GetMatchDegreeMapFullBean getMatchDegreeMapFullBean = (GetMatchDegreeMapFullBean) GsonUtils.fromLocalJson(str, GetMatchDegreeMapFullBean.class);
        MapInfoBean mapInfoBean = new MapInfoBean();
        GetMatchDegreeMapFullBean.MsgBean.InfoBean info = getMatchDegreeMapFullBean.getMsg().getInfo();
        int intValue = info.getHeight().intValue();
        int intValue2 = info.getWidth().intValue();
        float floatValue = info.getResolution().floatValue();
        float floatValue2 = info.getOrigin().getPosition().getX().floatValue();
        float floatValue3 = info.getOrigin().getPosition().getY().floatValue();
        Bitmap bytes2Bitmap = ConvertUtils.bytes2Bitmap(Base64.decode(getMatchDegreeMapFullBean.getMsg().getData(), 0));
        mapInfoBean.setH(intValue);
        mapInfoBean.setW(intValue2);
        mapInfoBean.setR(floatValue);
        mapInfoBean.setX(floatValue2);
        mapInfoBean.setY(floatValue3);
        if (this.mOnMapInfoCallBack != null) {
            MyLogUtils.Logd("NAVIGATION_TAG", "getMap：mOnMapInfoCallBack");
            this.mOnMapInfoCallBack.mapMatchDegree(mapInfoBean, bytes2Bitmap);
        }
    }

    private void getMap(String str) {
        MyLogUtils.Logd("NAVIGATION_TAG", "getMap：获取地图" + str);
        GetMapFullBean getMapFullBean = (GetMapFullBean) GsonUtils.fromLocalJson(str, GetMapFullBean.class);
        MapInfoBean mapInfoBean = new MapInfoBean();
        GetMapFullBean.MsgBean.InfoBean info = getMapFullBean.getMsg().getInfo();
        int height = info.getHeight();
        int width = info.getWidth();
        float resolution = info.getResolution();
        float x = info.getOrigin().getPosition().getX();
        float y = info.getOrigin().getPosition().getY();
        Bitmap bytes2Bitmap = ConvertUtils.bytes2Bitmap(Base64.decode(getMapFullBean.getMsg().getData(), 0));
        mapInfoBean.setH(height);
        mapInfoBean.setW(width);
        mapInfoBean.setR(resolution);
        mapInfoBean.setX(x);
        mapInfoBean.setY(y);
        if (this.mOnMapInfoCallBack != null) {
            MyLogUtils.Logd("NAVIGATION_TAG", "getMap：mOnMapInfoCallBack");
            this.mOnMapInfoCallBack.map(mapInfoBean, bytes2Bitmap);
        }
    }

    private void getLaserData(String str) {
        LaserDataPublishBean laserDataPublishBean = (LaserDataPublishBean) GsonUtils.fromLocalJson(str, LaserDataPublishBean.class);
        ArrayList arrayList = new ArrayList();
        List<Float> px = laserDataPublishBean.getMsg().getPx();
        List<Float> py = laserDataPublishBean.getMsg().getPy();
        if (px != null && px.size() != 0 && py != null && py.size() != 0 && py.size() == px.size()) {
            for (int i = 0; i < px.size(); i++) {
                Float f = px.get(i);
                Float f2 = py.get(i);
                if (!(f == null || f2 == null)) {
                    PointBean pointBean = new PointBean();
                    pointBean.setX(f.floatValue());
                    pointBean.setY(f2.floatValue());
                    arrayList.add(pointBean);
                }
            }
            OnMapInfoCallBack onMapInfoCallBack = this.mOnMapInfoCallBack;
            if (onMapInfoCallBack != null) {
                onMapInfoCallBack.laser(arrayList);
            }
        }
    }

    private void getPose(String str) {
        GetPosePublishBean getPosePublishBean = (GetPosePublishBean) GsonUtils.fromLocalJson(str, GetPosePublishBean.class);
        PointBean pointBean = new PointBean();
        float x = getPosePublishBean.getMsg().getX();
        float y = getPosePublishBean.getMsg().getY();
        float theta = getPosePublishBean.getMsg().getTheta();
        pointBean.setX(x);
        pointBean.setY(y);
        pointBean.setTheta(theta);
        OnMapInfoCallBack onMapInfoCallBack = this.mOnMapInfoCallBack;
        if (onMapInfoCallBack != null) {
            onMapInfoCallBack.pose(pointBean);
        }
        SelfChassisState.getInstance().setX(x);
        SelfChassisState.getInstance().setY(y);
        SelfChassisState.getInstance().setTheta(theta);
        EventBus.getDefault().post(SelfChassisState.getInstance());
    }

    private void nodeManagerControl(String str) {
        NodeManagerControlResponseBean nodeManagerControlResponseBean = (NodeManagerControlResponseBean) GsonUtils.fromLocalJson(str, NodeManagerControlResponseBean.class);
        int result = nodeManagerControlResponseBean.getValues().getResult();
        if (String.valueOf(3).equals(nodeManagerControlResponseBean.getId()) && result == 0) {
            SelfChassis.getInstance().serviceNav();
            SystemClock.sleep(200);
            SelfChassis.getInstance().sendGetFloorData();
            SystemClock.sleep(200);
            SelfChassis.getInstance().sendPathGetParams();
        }
        SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(ServiceContent.NODE_MANAGER_CONTROL);
        selfChassisEventMsg.setData(nodeManagerControlResponseBean);
        if (SelfChassisListenerUtils.getInstance().getNodeManagerControlListener() != null) {
            SelfChassisListenerUtils.getInstance().getNodeManagerControlListener().onSelfChassisMsg(selfChassisEventMsg);
        }
    }

    private void dealLayeredMapCmd(String str, String str2) {
        MyLogUtils.Logd("dealLayeredMapCmd====>" + str2);
        LayeredMapCmdResponseBean layeredMapCmdResponseBean = (LayeredMapCmdResponseBean) GsonUtils.fromLocalJson(str2, LayeredMapCmdResponseBean.class);
        if (TextUtils.equals(IDContent.SERVICE_LAYERED_MAP_CMD, str)) {
            MyLogUtils.Logd("layeredMapCmd====>" + str2);
            layeredMapCmd(layeredMapCmdResponseBean);
        } else if (TextUtils.equals(IDContent.SERVICE_GET_AREAS, str)) {
            MyLogUtils.Logd("getAreasInfo====>" + str2);
            getAreasInfo(layeredMapCmdResponseBean);
        } else if (TextUtils.equals(IDContent.SERVICE_GET_FLOOR_DATA, str)) {
            MyLogUtils.Logd("getFloorData====>" + str2);
            getFloorData(layeredMapCmdResponseBean);
        } else if (TextUtils.equals(IDContent.SERVICE_MAP_RENAME, str)) {
            MyLogUtils.Logd("mapRename====>" + str2);
            mapRename(layeredMapCmdResponseBean);
        }
    }

    private void mapRename(LayeredMapCmdResponseBean layeredMapCmdResponseBean) {
        SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(IDContent.SERVICE_GET_AREAS);
        selfChassisEventMsg.setData(layeredMapCmdResponseBean);
        if (SelfChassisListenerUtils.getInstance().getMapRenameListener() != null) {
            SelfChassisListenerUtils.getInstance().getMapRenameListener().onSelfChassisMsg(selfChassisEventMsg);
        }
        SelfChassisEventUtils.post(selfChassisEventMsg);
    }

    private void layeredMapCmd(LayeredMapCmdResponseBean layeredMapCmdResponseBean) {
        OnNavigationManagerCallback onNavigationManagerCallback = this.mOnNavigationManagerCallback;
        if (onNavigationManagerCallback != null) {
            onNavigationManagerCallback.onLayeredMap(layeredMapCmdResponseBean);
        }
        EventBus.getDefault().post(new SelfChassisEventMsg(ServiceContent.LAYERED_MAP_CMD, layeredMapCmdResponseBean));
    }

    private void getAreasInfo(LayeredMapCmdResponseBean layeredMapCmdResponseBean) {
        List arrayList = new ArrayList();
        try {
            arrayList = layeredMapCmdResponseBean.getValues().getAreas().getAreas();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(IDContent.SERVICE_GET_AREAS);
        selfChassisEventMsg.setData(arrayList);
        if (SelfChassisListenerUtils.getInstance().getAreasInfoListener() != null) {
            SelfChassisListenerUtils.getInstance().getAreasInfoListener().onSelfChassisMsg(selfChassisEventMsg);
        }
        OnMapInfoCallBack onMapInfoCallBack = this.mOnMapInfoCallBack;
        if (onMapInfoCallBack != null) {
            onMapInfoCallBack.area(arrayList);
        }
        SelfChassisEventUtils.post(selfChassisEventMsg);
    }

    private void getFloorData(LayeredMapCmdResponseBean layeredMapCmdResponseBean) {
        if (layeredMapCmdResponseBean == null) {
            MyLogUtils.Logd("NAVIGATION_TAG", "getFloorData---info is null");
            return;
        }
        SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(IDContent.SERVICE_GET_FLOOR_DATA);
        selfChassisEventMsg.setData(layeredMapCmdResponseBean);
        if (SelfChassisListenerUtils.getInstance().getAreasInfoListener() != null) {
            SelfChassisListenerUtils.getInstance().getAreasInfoListener().onSelfChassisMsg(selfChassisEventMsg);
        }
        SelfChassisEventUtils.post(selfChassisEventMsg);
        if (layeredMapCmdResponseBean.getValues().getResult() == 10 || layeredMapCmdResponseBean.getValues().getResult() == 99 || !layeredMapCmdResponseBean.isResult()) {
            MyLogUtils.Logd("NAVIGATION_TAG", "getFloorData---info.getValues().getResult()：" + layeredMapCmdResponseBean.getValues().getResult() + "---info.isResult()：" + layeredMapCmdResponseBean.isResult());
            return;
        }
        String json = GsonUtils.toJson(layeredMapCmdResponseBean.getValues());
        MyLogUtils.Logd("NAVIGATION_TAG", "getFloorData--valuesJson：" + json);
        List<LayeredMapCmdResponseBean.ValuesBean.VwallsBean.WallsBean> walls = layeredMapCmdResponseBean.getValues().getVwalls().getWalls();
        if (walls == null || walls.size() <= 0) {
            OnMapInfoCallBack onMapInfoCallBack = this.mOnMapInfoCallBack;
            if (onMapInfoCallBack != null) {
                onMapInfoCallBack.virtualWall(new ArrayList());
            }
        } else {
            VirtualWallOperationGetWallsResponseBean virtualWallOperationGetWallsResponseBean = new VirtualWallOperationGetWallsResponseBean();
            virtualWallOperationGetWallsResponseBean.setResult(true);
            virtualWallOperationGetWallsResponseBean.setValues((VirtualWallOperationGetWallsResponseBean.ValuesBean) GsonUtils.fromLocalJson(json, VirtualWallOperationGetWallsResponseBean.ValuesBean.class));
            virtualWallOperationGetWalls(GsonUtils.toJson(virtualWallOperationGetWallsResponseBean));
        }
        MarkerOperationGetMarkersResponseBean markerOperationGetMarkersResponseBean = new MarkerOperationGetMarkersResponseBean();
        markerOperationGetMarkersResponseBean.setResult(true);
        markerOperationGetMarkersResponseBean.setValues((MarkerOperationGetMarkersResponseBean.ValuesBean) GsonUtils.fromLocalJson(json, MarkerOperationGetMarkersResponseBean.ValuesBean.class));
        markerOperationGetMarkers(GsonUtils.toJson(markerOperationGetMarkersResponseBean));
        getAreasInfo(layeredMapCmdResponseBean);
    }

    private void markerOperationGetMarkers(String str) {
        MyLogUtils.Logd("NAVIGATION_TAG", "markerOperationGetMarkers: " + str);
        MarkerOperationGetMarkersResponseBean markerOperationGetMarkersResponseBean = (MarkerOperationGetMarkersResponseBean) GsonUtils.fromLocalJson(str, MarkerOperationGetMarkersResponseBean.class);
        if (markerOperationGetMarkersResponseBean.isResult()) {
            EventBus.getDefault().post(markerOperationGetMarkersResponseBean);
            List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean> waypoints = markerOperationGetMarkersResponseBean.getValues().getMarkers().getWaypoints();
            OnNavigationManagerCallback onNavigationManagerCallback = this.mOnNavigationManagerCallback;
            if (onNavigationManagerCallback != null) {
                onNavigationManagerCallback.getAllPositionList(waypoints);
            }
            OnMapInfoCallBack onMapInfoCallBack = this.mOnMapInfoCallBack;
            if (onMapInfoCallBack != null) {
                onMapInfoCallBack.bjd(waypoints);
            }
            SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(ServiceContent.MARKER_OPERATION_GET_MARKERS, waypoints);
            if (markerOperationGetMarkersResponseBean.isResult()) {
                EventBus.getDefault().post(selfChassisEventMsg);
            }
        } else {
            MyLogUtils.Loge("NAVIGATION_TAG", "markerOperationGetMarkers err:" + markerOperationGetMarkersResponseBean.getInfo());
        }
        List<SelfChassisListenerUtils.OnBaseSelfChassisListener> markersListeners = SelfChassisListenerUtils.getInstance().getMarkersListeners();
        if (markersListeners != null) {
            List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean> list = null;
            if (markerOperationGetMarkersResponseBean.isResult()) {
                list = markerOperationGetMarkersResponseBean.getValues().getMarkers().getWaypoints();
            }
            SelfChassisEventMsg selfChassisEventMsg2 = new SelfChassisEventMsg(ServiceContent.MARKER_OPERATION_GET_MARKERS, list);
            for (SelfChassisListenerUtils.OnBaseSelfChassisListener onSelfChassisMsg : markersListeners) {
                onSelfChassisMsg.onSelfChassisMsg(selfChassisEventMsg2);
            }
        }
    }

    private void markerManagerDeletePoi(String str) {
        SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(ServiceContent.MARKER_MANAGER_DELETE_POI);
        selfChassisEventMsg.setData((MarkerManagerDeletePoiResponseBean) GsonUtils.fromLocalJson(str, MarkerManagerDeletePoiResponseBean.class));
        if (SelfChassisListenerUtils.getInstance().getDeletePoiListener() != null) {
            SelfChassisListenerUtils.getInstance().getDeletePoiListener().onSelfChassisMsg(selfChassisEventMsg);
        }
    }

    private void virtualWallOperationGetWalls(String str) {
        VirtualWallOperationGetWallsResponseBean virtualWallOperationGetWallsResponseBean = (VirtualWallOperationGetWallsResponseBean) GsonUtils.fromLocalJson(str, VirtualWallOperationGetWallsResponseBean.class);
        if (virtualWallOperationGetWallsResponseBean.getValues().getVwalls() == null) {
            OnMapInfoCallBack onMapInfoCallBack = this.mOnMapInfoCallBack;
            if (onMapInfoCallBack != null) {
                onMapInfoCallBack.virtualWall(new ArrayList());
                return;
            }
            return;
        }
        List<VirtualWallOperationGetWallsResponseBean.ValuesBean.VwallsBean.WallsBean> walls = virtualWallOperationGetWallsResponseBean.getValues().getVwalls().getWalls();
        if (this.mOnMapInfoCallBack != null && virtualWallOperationGetWallsResponseBean.isResult()) {
            this.mOnMapInfoCallBack.virtualWall(walls);
        }
    }

    private void virtualWallManagerDeletePoie(String str) {
        VirtualWallManagerDeletePoieResponseBean virtualWallManagerDeletePoieResponseBean = (VirtualWallManagerDeletePoieResponseBean) GsonUtils.fromLocalJson(str, VirtualWallManagerDeletePoieResponseBean.class);
    }

    private void poi(String str) {
        OnNavigationManagerCallback onNavigationManagerCallback;
        PoiResponseBean.ValuesBean values = ((PoiResponseBean) GsonUtils.fromLocalJson(str, PoiResponseBean.class)).getValues();
        List<String> avaliable_list = values.getAvaliable_list();
        String obj = (avaliable_list == null || avaliable_list.size() == 0) ? "" : avaliable_list.toString();
        if (!values.isSuccess() && (onNavigationManagerCallback = this.mOnNavigationManagerCallback) != null) {
            onNavigationManagerCallback.onError(6, obj);
        }
    }

    private void poiInit(String str) {
        PoiInitResponseBean poiInitResponseBean = (PoiInitResponseBean) GsonUtils.fromLocalJson(str, PoiInitResponseBean.class);
    }

    private void poiPatrol(String str) {
        SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(ServiceContent.POI_PATROL, (PoiPatrolResponseBean) GsonUtils.fromLocalJson(str, PoiPatrolResponseBean.class));
        if (SelfChassisListenerUtils.getInstance().getSendPoiPatrolListener() != null) {
            SelfChassisListenerUtils.getInstance().getSendPoiPatrolListener().onSelfChassisMsg(selfChassisEventMsg);
        }
    }

    private void startRecharge(String str) {
        StartRechargeResponseBean startRechargeResponseBean = (StartRechargeResponseBean) GsonUtils.fromLocalJson(str, StartRechargeResponseBean.class);
    }

    private void getMapInfo(String str) {
        GetMapInfoResponseBean getMapInfoResponseBean = (GetMapInfoResponseBean) GsonUtils.fromLocalJson(str, GetMapInfoResponseBean.class);
        if (this.mOnMapInfoCallBack != null) {
            this.mOnMapInfoCallBack.currentMapArea(BigDecimal.valueOf(getMapInfoResponseBean.getValues().getFree_space()).add(BigDecimal.valueOf(getMapInfoResponseBean.getValues().getOcc_space())).doubleValue());
        }
    }

    private void versionUpgrade(String str) {
        SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(ServiceContent.VERSION_UPGRADE, (VersionUpgradeResponseBean) GsonUtils.fromLocalJson(str, VersionUpgradeResponseBean.class));
        ArrayList<SelfChassisListenerUtils.OnBaseSelfChassisListener> versionUpgradeListeners = SelfChassisListenerUtils.getInstance().getVersionUpgradeListeners();
        if (versionUpgradeListeners != null) {
            Iterator<SelfChassisListenerUtils.OnBaseSelfChassisListener> it = versionUpgradeListeners.iterator();
            while (it.hasNext()) {
                it.next().onSelfChassisMsg(selfChassisEventMsg);
            }
        }
        SelfChassisEventUtils.post(selfChassisEventMsg);
    }

    private void poiId(String str) {
        OnNavigationManagerCallback onNavigationManagerCallback;
        PoiIdResponseBean.ValuesBean values = ((PoiIdResponseBean) GsonUtils.fromLocalJson(str, PoiIdResponseBean.class)).getValues();
        List<String> avaliable_list = values.getAvaliable_list();
        String obj = (avaliable_list == null || avaliable_list.size() <= 0) ? "" : avaliable_list.toString();
        if (!values.isSuccess() && (onNavigationManagerCallback = this.mOnNavigationManagerCallback) != null) {
            onNavigationManagerCallback.onError(6, obj);
        }
    }

    private void selfDiagnosis(String str) {
        SelfDiagnosisResponseBean selfDiagnosisResponseBean = (SelfDiagnosisResponseBean) GsonUtils.fromLocalJson(str, SelfDiagnosisResponseBean.class);
        OnNavigationManagerCallback onNavigationManagerCallback = this.mOnNavigationManagerCallback;
        if (onNavigationManagerCallback != null) {
            onNavigationManagerCallback.selfDiagnosis(selfDiagnosisResponseBean);
        }
        SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(ServiceContent.SELF_DIAGNOSIS, selfDiagnosisResponseBean);
        if (SelfChassisListenerUtils.getInstance().getSelfDiagnosisListener() != null) {
            SelfChassisListenerUtils.getInstance().getSelfDiagnosisListener().onSelfChassisMsg(selfChassisEventMsg);
        }
    }

    private void robotInfo(String str) {
        SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(ServiceContent.ROBOT_INFO, (RobotInfoResponseBean) GsonUtils.fromLocalJson(str, RobotInfoResponseBean.class));
        if (SelfChassisListenerUtils.getInstance().getRobotInfoListener() != null) {
            SelfChassisListenerUtils.getInstance().getRobotInfoListener().onSelfChassisMsg(selfChassisEventMsg);
        }
        SelfChassisEventUtils.post(selfChassisEventMsg);
    }

    private void dealGlobalLocate(String str) {
        GlobalLocateResponseBean globalLocateResponseBean = (GlobalLocateResponseBean) GsonUtils.fromLocalJson(str, GlobalLocateResponseBean.class);
        if (globalLocateResponseBean != null) {
            String id = globalLocateResponseBean.getId();
            if (TextUtils.equals(IDContent.SERVICE_GLOBAL_LOCATE_GLOBAL, id)) {
                globalLocate(globalLocateResponseBean);
            } else if (TextUtils.equals(IDContent.SERVICE_GLOBAL_LOCATE_LOCAL, id)) {
                globalLocateLocal(globalLocateResponseBean);
            }
        } else {
            globalLocate((GlobalLocateResponseBean) null);
            globalLocateLocal((GlobalLocateResponseBean) null);
        }
    }

    private void globalLocate(GlobalLocateResponseBean globalLocateResponseBean) {
        SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(IDContent.SERVICE_GLOBAL_LOCATE_GLOBAL);
        selfChassisEventMsg.setData(globalLocateResponseBean);
        if (SelfChassisListenerUtils.getInstance().getGlobalLocateListener() != null) {
            SelfChassisListenerUtils.getInstance().getGlobalLocateListener().onSelfChassisMsg(selfChassisEventMsg);
        }
        SelfChassisEventUtils.post(selfChassisEventMsg);
    }

    private void globalLocateLocal(GlobalLocateResponseBean globalLocateResponseBean) {
        SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(IDContent.SERVICE_GLOBAL_LOCATE_LOCAL);
        selfChassisEventMsg.setData(globalLocateResponseBean);
        if (SelfChassisListenerUtils.getInstance().getGlobalLocateLocalListener() != null) {
            SelfChassisListenerUtils.getInstance().getGlobalLocateLocalListener().onSelfChassisMsg(selfChassisEventMsg);
        }
        SelfChassisEventUtils.post(selfChassisEventMsg);
    }

    private void tagManagerMode(String str) {
        MyLogUtils.Logd("NAVIGATION_TAG", "视觉标签模式切换响应：" + str);
        SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(ServiceContent.TAG_MANAGER_MODE);
        selfChassisEventMsg.setData((TagManagerModeResponseBean) GsonUtils.fromLocalJson(str, TagManagerModeResponseBean.class));
        SelfChassisEventUtils.post(selfChassisEventMsg);
        if (SelfChassisListenerUtils.getInstance().getTagManagerModeListener() != null) {
            SelfChassisListenerUtils.getInstance().getTagManagerModeListener().onSelfChassisMsg(selfChassisEventMsg);
        }
    }

    private void tagManagerDeletePoi(String str) {
        SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(ServiceContent.TAG_MANAGER_DELETE_POI);
        selfChassisEventMsg.setData((TagManagerDeletePoiResponseBean) GsonUtils.fromLocalJson(str, TagManagerDeletePoiResponseBean.class));
        SelfChassisEventUtils.post(selfChassisEventMsg);
        if (SelfChassisListenerUtils.getInstance().getTagManagerDelPoiListener() != null) {
            SelfChassisListenerUtils.getInstance().getTagManagerDelPoiListener().onSelfChassisMsg(selfChassisEventMsg);
        }
    }

    private void tagManagerControl(String str) {
        SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(ServiceContent.TAG_MANAGER_CONTROL);
        selfChassisEventMsg.setData((TagManagerControlResponseBean) GsonUtils.fromLocalJson(str, TagManagerControlResponseBean.class));
        SelfChassisEventUtils.post(selfChassisEventMsg);
        if (SelfChassisListenerUtils.getInstance().getTagManagerSaveListener() != null) {
            SelfChassisListenerUtils.getInstance().getTagManagerSaveListener().onSelfChassisMsg(selfChassisEventMsg);
        }
    }

    private void getMatchThreshold(String str) {
        Log.d("NAVIGATION_TAG", "getMatchThreshold jsonStr:" + str);
        SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(ServiceContent.GET_MATCH_THRESHOLD);
        selfChassisEventMsg.setData((GetMatchThresholdBean) GsonUtils.fromLocalJson(str, GetMatchThresholdBean.class));
        if (SelfChassisListenerUtils.getInstance().getMatchThresholdListener() != null) {
            SelfChassisListenerUtils.getInstance().getMatchThresholdListener().onSelfChassisMsg(selfChassisEventMsg);
        }
        SelfChassisEventUtils.post(selfChassisEventMsg);
    }

    private void uploadMaps(String str) {
        LogUtils.d("NAVIGATION_TAG", "messageReceive--  uploadMaps:" + str);
        UploadMapsResponseBean uploadMapsResponseBean = (UploadMapsResponseBean) GsonUtils.fromLocalJson(str, UploadMapsResponseBean.class);
        if (!uploadMapsResponseBean.isResult()) {
            LogUtils.w("NAVIGATION_TAG", CLASS_NAME + " messageReceive--  uploadMaps error:" + uploadMapsResponseBean.getInfo());
            EventBus eventBus = EventBus.getDefault();
            eventBus.post(new DataUpdateEvent(13, "messageReceive--  uploadMaps error:" + uploadMapsResponseBean.getInfo()));
        } else if (uploadMapsResponseBean.getValues().getStatus() != 0) {
            EventBus eventBus2 = EventBus.getDefault();
            eventBus2.post(new DataUpdateEvent(13, "messageReceive--  uploadMaps error code:" + uploadMapsResponseBean.getValues().getStatus() + "   error info :" + uploadMapsResponseBean.getValues().getInfo()));
            LogUtils.w("NAVIGATION_TAG", CLASS_NAME + " messageReceive--  uploadMaps error code:" + uploadMapsResponseBean.getValues().getStatus() + "   error info :" + uploadMapsResponseBean.getValues().getInfo());
        } else {
            SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(ServiceContent.UPLOAD_MAPS);
            selfChassisEventMsg.setData(uploadMapsResponseBean);
            EventBus.getDefault().post(selfChassisEventMsg);
        }
    }

    private void downloadMaps(String str) {
        LogUtils.d("NAVIGATION_TAG", CLASS_NAME + "messageReceive--  downloadMaps:" + str);
        DownloadMapsResponseBean downloadMapsResponseBean = (DownloadMapsResponseBean) GsonUtils.fromLocalJson(str, DownloadMapsResponseBean.class);
        if (!downloadMapsResponseBean.isResult()) {
            LogUtils.w("NAVIGATION_TAG", CLASS_NAME + "messageReceive--  downloadMaps error:" + downloadMapsResponseBean.getInfo());
            EventBus eventBus = EventBus.getDefault();
            eventBus.post(new DataUpdateEvent(12, "messageReceive--  downloadMaps error:" + downloadMapsResponseBean.getInfo()));
        } else if (downloadMapsResponseBean.getValues().getStatus() != 0) {
            LogUtils.w("NAVIGATION_TAG", CLASS_NAME + "messageReceive--  downloadMaps error code:" + downloadMapsResponseBean.getValues().getStatus() + "   error info :" + downloadMapsResponseBean.getValues().getInfo());
            EventBus eventBus2 = EventBus.getDefault();
            eventBus2.post(new DataUpdateEvent(12, "messageReceive--  downloadMaps error code:" + downloadMapsResponseBean.getValues().getStatus() + "  error info :" + downloadMapsResponseBean.getValues().getInfo()));
        } else {
            SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(ServiceContent.DOWNLOAD_MAPS);
            selfChassisEventMsg.setData(downloadMapsResponseBean);
            EventBus.getDefault().post(selfChassisEventMsg);
        }
    }

    private void getTime(String str) {
        SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(ServiceContent.GET_TIME);
        selfChassisEventMsg.setData((GetTimeResponse) GsonUtils.fromLocalJson(str, GetTimeResponse.class));
        if (SelfChassisListenerUtils.getInstance().getTimeListener() != null) {
            SelfChassisListenerUtils.getInstance().getTimeListener().onSelfChassisMsg(selfChassisEventMsg);
        }
        SelfChassisEventUtils.post(selfChassisEventMsg);
    }

    private void velocityControl(String str) {
        SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(ServiceContent.VELOCITY_CONTROL);
        selfChassisEventMsg.setData((VelocityControlResponseBean) GsonUtils.fromLocalJson(str, VelocityControlResponseBean.class));
        if (SelfChassisListenerUtils.getInstance().getVelocityControlListener() != null) {
            SelfChassisListenerUtils.getInstance().getVelocityControlListener().onSelfChassisMsg(selfChassisEventMsg);
        }
        SelfChassisEventUtils.post(selfChassisEventMsg);
    }

    private void sensorsConfig(String str) {
        SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(ServiceContent.SENSORS_CONFIG);
        selfChassisEventMsg.setData((SensorResponseBean) GsonUtils.fromLocalJson(str, SensorResponseBean.class));
        if (SelfChassisListenerUtils.getInstance().getSensorsListener() != null) {
            SelfChassisListenerUtils.getInstance().getSensorsListener().onSelfChassisMsg(selfChassisEventMsg);
        }
        SelfChassisEventUtils.post(selfChassisEventMsg);
    }

    private void getMarkersDetails(String str) {
        SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(ServiceContent.GET_MARKERS_DETAILS);
        selfChassisEventMsg.setData((MarkersDetailsBean) GsonUtils.fromLocalJson(str, MarkersDetailsBean.class));
        if (SelfChassisListenerUtils.getInstance().getMarkersDetailsListener() != null) {
            SelfChassisListenerUtils.getInstance().getMarkersDetailsListener().onSelfChassisMsg(selfChassisEventMsg);
        }
        SelfChassisEventUtils.post(selfChassisEventMsg);
    }

    private void liftControlStatus(String str) {
        MyLogUtils.Logd("liftControlStatus===>" + str);
        SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(TopicContent.LIFT_CONTROL_STATUS);
        selfChassisEventMsg.setData((LiftControlStatusBean) GsonUtils.fromLocalJson(str, LiftControlStatusBean.class));
        if (SelfChassisListenerUtils.getInstance().getLiftControlStatus() != null) {
            SelfChassisListenerUtils.getInstance().getLiftControlStatus().onSelfChassisMsg(selfChassisEventMsg);
        }
        SelfChassisEventUtils.post(selfChassisEventMsg);
    }

    private void waypointStatus(String str) {
        MyLogUtils.Logd("waypointStatus===>" + str);
        SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(TopicContent.WAYPOINT_STATE);
        selfChassisEventMsg.setData((WaypointStateBean) GsonUtils.fromLocalJson(str, WaypointStateBean.class));
        if (SelfChassisListenerUtils.getInstance().getWaypointStatusListener() != null) {
            SelfChassisListenerUtils.getInstance().getWaypointStatusListener().onSelfChassisMsg(selfChassisEventMsg);
        }
        SelfChassisEventUtils.post(selfChassisEventMsg);
    }

    private void areaDeletePoi(String str) {
        SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(ServiceContent.AREA_MANAGER_DELETE_POI);
        selfChassisEventMsg.setData((AreaDeletePoiResponseBean) GsonUtils.fromLocalJson(str, AreaDeletePoiResponseBean.class));
        if (SelfChassisListenerUtils.getInstance().getAreaDeletePoiListener() != null) {
            SelfChassisListenerUtils.getInstance().getAreaDeletePoiListener().onSelfChassisMsg(selfChassisEventMsg);
        }
        SelfChassisEventUtils.post(selfChassisEventMsg);
    }

    private void liftControlConfigure(String str) {
        MyLogUtils.Logd("NAVIGATION_TAG", "梯控配置回调：" + str);
        SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(ServiceContent.LIFT_CONTROL_CONFIGURE);
        selfChassisEventMsg.setData((LiftControlConfigureResponseBean) GsonUtils.fromLocalJson(str, LiftControlConfigureResponseBean.class));
        if (SelfChassisListenerUtils.getInstance().getLiftConfigureListener() != null) {
            SelfChassisListenerUtils.getInstance().getLiftConfigureListener().onSelfChassisMsg(selfChassisEventMsg);
        }
        SelfChassisEventUtils.post(selfChassisEventMsg);
    }

    private void liftControlCommand(String str) {
        LiftControlCommandResponseBean liftControlCommandResponseBean = (LiftControlCommandResponseBean) GsonUtils.fromLocalJson(str, LiftControlCommandResponseBean.class);
        if (TextUtils.equals(liftControlCommandResponseBean.getId(), IDContent.SERVICE_CROSS_FLOOR_CANCEL)) {
            SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(IDContent.SERVICE_CROSS_FLOOR_CANCEL);
            selfChassisEventMsg.setData(liftControlCommandResponseBean);
            if (SelfChassisListenerUtils.getInstance().getCrossFloorCancelListener() != null) {
                SelfChassisListenerUtils.getInstance().getCrossFloorCancelListener().onSelfChassisMsg(selfChassisEventMsg);
            }
            SelfChassisEventUtils.post(selfChassisEventMsg);
        }
    }

    private void getDoorLength(String str) {
        Log.d("NAVIGATION_TAG", "getDoorLength jsonStr:" + str);
        SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(ServiceContent.GET_DOOR_LENGTH);
        selfChassisEventMsg.setData((GetDoorLengthBean) GsonUtils.fromLocalJson(str, GetDoorLengthBean.class));
        if (SelfChassisListenerUtils.getInstance().getDoorLengthListener() != null) {
            SelfChassisListenerUtils.getInstance().getDoorLengthListener().onSelfChassisMsg(selfChassisEventMsg);
        }
        SelfChassisEventUtils.post(selfChassisEventMsg);
    }

    private void getGateLength(String str) {
        Log.d("NAVIGATION_TAG", "getGateLength jsonStr:" + str);
        SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(ServiceContent.GET_GATE_LENGTH);
        selfChassisEventMsg.setData((GetGateLengthBean) GsonUtils.fromLocalJson(str, GetGateLengthBean.class));
        if (SelfChassisListenerUtils.getInstance().getGateLengthListener() != null) {
            SelfChassisListenerUtils.getInstance().getGateLengthListener().onSelfChassisMsg(selfChassisEventMsg);
        }
        SelfChassisEventUtils.post(selfChassisEventMsg);
    }

    private void getUpCameraAutoCalibrate(String str) {
        Log.d("NAVIGATION_TAG", "getUpCameraAutoCalibrate jsonStr:" + str);
        SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(ServiceContent.UP_CAMERA_EXTRINSIC_AUTO_CALIBRATE);
        selfChassisEventMsg.setData((UpCameraAutoCalibrateResponseBean) GsonUtils.fromLocalJson(str, UpCameraAutoCalibrateResponseBean.class));
        ArrayList<SelfChassisListenerUtils.OnBaseSelfChassisListener> upCameraAutoCalibrateListeners = SelfChassisListenerUtils.getInstance().getUpCameraAutoCalibrateListeners();
        if (upCameraAutoCalibrateListeners != null && upCameraAutoCalibrateListeners.size() > 0) {
            Iterator<SelfChassisListenerUtils.OnBaseSelfChassisListener> it = upCameraAutoCalibrateListeners.iterator();
            while (it.hasNext()) {
                it.next().onSelfChassisMsg(selfChassisEventMsg);
            }
        }
        SelfChassisEventUtils.post(selfChassisEventMsg);
    }

    private void getDownCameraAutoCalibrate(String str) {
        Log.d("NAVIGATION_TAG", "getDownCameraAutoCalibrate jsonStr:" + str);
        SelfChassisEventMsg selfChassisEventMsg = new SelfChassisEventMsg(ServiceContent.DOWN_CAMERA_EXTRINSIC_AUTO_CALIBRATE);
        selfChassisEventMsg.setData((DownCameraAutoCalibrateResponseBean) GsonUtils.fromLocalJson(str, DownCameraAutoCalibrateResponseBean.class));
        ArrayList<SelfChassisListenerUtils.OnBaseSelfChassisListener> downCameraAutoCalibrateListeners = SelfChassisListenerUtils.getInstance().getDownCameraAutoCalibrateListeners();
        if (downCameraAutoCalibrateListeners != null && downCameraAutoCalibrateListeners.size() > 0) {
            Iterator<SelfChassisListenerUtils.OnBaseSelfChassisListener> it = downCameraAutoCalibrateListeners.iterator();
            while (it.hasNext()) {
                it.next().onSelfChassisMsg(selfChassisEventMsg);
            }
        }
        SelfChassisEventUtils.post(selfChassisEventMsg);
    }

    private void getUpCamData(String str) {
        CamPointCloudDataPublishBean camPointCloudDataPublishBean = (CamPointCloudDataPublishBean) GsonUtils.fromLocalJson(str, CamPointCloudDataPublishBean.class);
        ArrayList arrayList = new ArrayList();
        List<Float> px = camPointCloudDataPublishBean.getMsg().getPx();
        List<Float> py = camPointCloudDataPublishBean.getMsg().getPy();
        List<Float> pt = camPointCloudDataPublishBean.getMsg().getPt();
        if (px != null && px.size() != 0 && py != null && py.size() != 0 && py.size() == px.size()) {
            for (int i = 0; i < px.size(); i++) {
                Float f = px.get(i);
                Float f2 = py.get(i);
                Float f3 = pt.get(i);
                if (!(f == null || f2 == null || f3 == null)) {
                    PointBean pointBean = new PointBean();
                    pointBean.setX(f.floatValue());
                    pointBean.setY(f2.floatValue());
                    pointBean.setTheta(f3.floatValue());
                    arrayList.add(pointBean);
                }
            }
            OnMapInfoCallBack onMapInfoCallBack = this.mOnMapInfoCallBack;
            if (onMapInfoCallBack != null) {
                onMapInfoCallBack.upCamData(arrayList);
            }
        }
    }

    private void getDownCamData(String str) {
        CamPointCloudDataPublishBean camPointCloudDataPublishBean = (CamPointCloudDataPublishBean) GsonUtils.fromLocalJson(str, CamPointCloudDataPublishBean.class);
        ArrayList arrayList = new ArrayList();
        List<Float> px = camPointCloudDataPublishBean.getMsg().getPx();
        List<Float> py = camPointCloudDataPublishBean.getMsg().getPy();
        List<Float> pt = camPointCloudDataPublishBean.getMsg().getPt();
        if (px != null && px.size() != 0 && py != null && py.size() != 0 && py.size() == px.size()) {
            for (int i = 0; i < px.size(); i++) {
                Float f = px.get(i);
                Float f2 = py.get(i);
                Float f3 = pt.get(i);
                if (!(f == null || f2 == null || f3 == null)) {
                    PointBean pointBean = new PointBean();
                    pointBean.setX(f.floatValue());
                    pointBean.setY(f2.floatValue());
                    pointBean.setTheta(f3.floatValue());
                    arrayList.add(pointBean);
                }
            }
            OnMapInfoCallBack onMapInfoCallBack = this.mOnMapInfoCallBack;
            if (onMapInfoCallBack != null) {
                onMapInfoCallBack.downCamData(arrayList);
            }
        }
    }
}
