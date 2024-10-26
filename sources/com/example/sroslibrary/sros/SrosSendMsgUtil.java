package com.example.sroslibrary.sros;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.ciot.base.constant.AppConstant;
import com.ciot.base.constant.NetConstant;
import com.ciot.base.constant.SpConstant;
import com.ciot.base.storage.MySpUtils;
import com.ciot.base.util.AppSpUtil;
import com.ciot.base.util.LogPlus;
import com.ciot.base.util.MyLogUtils;
import com.ciot.realm.db.MarkerPoint;
import com.ciot.tcpclient.ClientIoSessionManager;
import com.example.sroslibrary.bean.ActivateLicenseBean;
import com.example.sroslibrary.bean.CoordinateBean;
import com.example.sroslibrary.bean.CoordinateNavigationBeanR;
import com.example.sroslibrary.bean.DeleteNavigationPointBeanR;
import com.example.sroslibrary.bean.EStopBeanR;
import com.example.sroslibrary.bean.GetReferenceCoordinateBeanR;
import com.example.sroslibrary.bean.HeartBeatBeanR;
import com.example.sroslibrary.bean.ManualControlBeanR;
import com.example.sroslibrary.bean.NotificationBeanR;
import com.example.sroslibrary.bean.PositionNameNavigationBeanR;
import com.example.sroslibrary.bean.ProtocolBean;
import com.example.sroslibrary.bean.QueryMapBeanR;
import com.example.sroslibrary.bean.QueryNavigationBeanR;
import com.example.sroslibrary.bean.RegisterControlBean;
import com.example.sroslibrary.bean.WaterMapBean;
import com.example.sroslibrary.contents.SrosConstants;
import com.example.sroslibrary.contents.VideoConstants;
import com.example.sroslibrary.contents.WaterChassisConstants;
import com.example.sroslibrary.monitor.monitor.request.StartLocalPhoneCallBean;
import com.example.sroslibrary.monitor.monitor.request.StopLocalPhoneCallBean;
import com.example.sroslibrary.status.WaterChassisStatus;
import com.example.sroslibrary.utils.ImageFileFilter;
import com.example.sroslibrary.utils.MyDeviceUtils;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class SrosSendMsgUtil {
    private static final String TAG = "NETWORK_TAG";
    private static SrosSendMsgUtil sSrosSendMsgUtil;
    private SparseArray<Long> alarmMap = new SparseArray<>();
    private String mIpAddress = NetworkUtils.getIPAddress(true);
    private Date mNowTime;
    private String mRobotId = AppSpUtil.getInstance().getRobotNumber();
    private String mRobotModel = AppSpUtil.getInstance().getRobotModel();
    private int mRobotType = AppSpUtil.getInstance().getRobotRealType();
    private int mSeq = 1;
    private SparseIntArray replyServerMap = new SparseIntArray();

    private int getSeq() {
        int i = this.mSeq;
        if (i >= Integer.MAX_VALUE) {
            this.mSeq = 1;
        } else {
            this.mSeq = i + 1;
        }
        return this.mSeq;
    }

    public ProtocolBean getProtocolBean() {
        ProtocolBean protocolBean = new ProtocolBean();
        protocolBean.setVer(2);
        protocolBean.setId(this.mRobotId);
        protocolBean.setCflag(0);
        protocolBean.setRflag(0);
        return protocolBean;
    }

    public static SrosSendMsgUtil getInstance() {
        if (sSrosSendMsgUtil == null) {
            synchronized (SrosSendMsgUtil.class) {
                if (sSrosSendMsgUtil == null) {
                    sSrosSendMsgUtil = new SrosSendMsgUtil();
                }
            }
        }
        return sSrosSendMsgUtil;
    }

    public void init() {
        this.mRobotId = MySpUtils.getInstance().getString(SpConstant.ROBOT_NUMBER, "");
        this.mRobotType = MySpUtils.getInstance().getInt(SpConstant.ROBOT_REAL_TYPE, 6);
        this.mRobotModel = MySpUtils.getInstance().getString(SpConstant.ROBOT_MODULE, AppConstant.ROBOT_MODULE);
    }

    public void sendRegisterRobot() {
        RegisterControlBean registerControlBean = new RegisterControlBean();
        registerControlBean.setRobotType(this.mRobotType);
        registerControlBean.setRobotIP(this.mIpAddress);
        registerControlBean.setRobotModel(this.mRobotModel);
        if (TextUtils.isEmpty(this.mRobotId)) {
            this.mRobotId = AppSpUtil.getInstance().getRobotNumber();
        }
        registerControlBean.setRobotID(String.valueOf(this.mRobotId));
        registerControlBean.setNowtime(new Date());
        registerControlBean.setVersion(AppUtils.getAppVersionName());
        registerControlBean.setRobotInfo(getRobotInfo());
        String string = MySpUtils.getInstance().getString(WaterChassisConstants.SP_PRODUCT_ID_KEY, "");
        LogPlus.w("底盘编号----" + string);
        registerControlBean.setPlaceid(string);
        MyLogUtils.Logd("NETWORK_TAG", "注册信息：" + registerControlBean.toString());
        ProtocolBean protocolBean = getProtocolBean();
        protocolBean.setQa((byte) 0);
        protocolBean.setSeq(getSeq());
        protocolBean.setCmd(-30975);
        protocolBean.setBody(registerControlBean);
        ClientIoSessionManager.getInstance().sendSrosMsg((Object) protocolBean);
        LogPlus.w("NETWORK_TAG", "发送注册平台消息" + protocolBean.toString());
    }

    private RegisterControlBean.RobotInfo getRobotInfo() {
        RegisterControlBean.RobotInfo robotInfo = new RegisterControlBean.RobotInfo();
        robotInfo.setScreen(1);
        robotInfo.setVideo(getVideo());
        robotInfo.setSensor(getSensor());
        robotInfo.setBat(getBat());
        robotInfo.setVoice(1);
        robotInfo.setLight(getLight());
        return robotInfo;
    }

    private RegisterControlBean.RobotInfo.Light getLight() {
        RegisterControlBean.RobotInfo.Light light = new RegisterControlBean.RobotInfo.Light();
        light.setAlarm(0);
        light.setExpression(0);
        light.setFront(0);
        light.setBack(0);
        return light;
    }

    private RegisterControlBean.RobotInfo.Bat getBat() {
        RegisterControlBean.RobotInfo.Bat bat = new RegisterControlBean.RobotInfo.Bat();
        bat.setModel(1);
        bat.setCapacity(100);
        return bat;
    }

    private RegisterControlBean.RobotInfo.Video getVideo() {
        RegisterControlBean.RobotInfo.Video video = new RegisterControlBean.RobotInfo.Video();
        video.setModel(1);
        video.setIP(VideoConstants.VIDEO_HIK_IP);
        video.setPort(8000);
        video.setNum(1);
        video.setName(VideoConstants.VIDEO_HIK_USER_NAME);
        video.setPass(VideoConstants.VIDEO_HIK_PASSWORD);
        video.setProtocol(1);
        video.setResolution(1);
        video.setMainId(1);
        return video;
    }

    private RegisterControlBean.RobotInfo.Sensor getSensor() {
        RegisterControlBean.RobotInfo.Sensor sensor = new RegisterControlBean.RobotInfo.Sensor();
        sensor.setSmoke(0);
        sensor.setTemp(0);
        sensor.setLaser(0);
        sensor.setSonic(1);
        sensor.setShock(0);
        return sensor;
    }

    public void sendHeartBeat() {
        HeartBeatBeanR heartBeatBeanR = new HeartBeatBeanR();
        heartBeatBeanR.setRobotType(this.mRobotType);
        heartBeatBeanR.setRobotIP(this.mIpAddress);
        heartBeatBeanR.setRobotID(String.valueOf(this.mRobotId));
        heartBeatBeanR.setNowtime(new Date());
        heartBeatBeanR.setVersion(String.valueOf(AppUtils.getAppVersionCode()));
        heartBeatBeanR.setDevState("00-00-00");
        ProtocolBean protocolBean = getProtocolBean();
        protocolBean.setSeq(getSeq());
        protocolBean.setCmd(NetConstant.CONTROL_STATUS_HEART_BEAT);
        protocolBean.setBody(heartBeatBeanR);
        if (protocolBean.getSeq() % 10 == 0) {
            Log.v("NETWORK_TAG", "sendHeartBeat:" + heartBeatBeanR.toString());
        }
        ClientIoSessionManager.getInstance().sendSrosMsg((Object) protocolBean);
    }

    public void replyNotification(boolean z, String str) {
        int i = this.replyServerMap.get(17, -1);
        if (i >= 0) {
            NotificationBeanR notificationBeanR = new NotificationBeanR();
            notificationBeanR.setResult(z);
            if (!z) {
                notificationBeanR.setReason(str);
            }
            ProtocolBean protocolBean = getProtocolBean();
            protocolBean.setSeq(i);
            protocolBean.setQa((byte) 1);
            protocolBean.setResult(z ^ true ? (short) 1 : 0);
            protocolBean.setCmd(17);
            protocolBean.setBody(notificationBeanR);
            ClientIoSessionManager.getInstance().sendSrosMsg((Object) protocolBean);
        }
    }

    public void sendActivateLicense() {
        ActivateLicenseBean activateLicenseBean = new ActivateLicenseBean();
        activateLicenseBean.setRobotType(this.mRobotType);
        activateLicenseBean.setRobotIP(this.mIpAddress);
        activateLicenseBean.setRobotID(String.valueOf(this.mRobotId));
        activateLicenseBean.setNowtime(new Date());
        activateLicenseBean.setMac(MyDeviceUtils.getMacAddress());
        ProtocolBean protocolBean = getProtocolBean();
        protocolBean.setSeq(getSeq());
        protocolBean.setCmd(NetConstant.CONTROL_ACTIVATE);
        protocolBean.setBody(activateLicenseBean);
        MyLogUtils.Logd("NETWORK_TAG", "sendActivateLicense ProtocolBean:" + protocolBean.toString());
        ClientIoSessionManager.getInstance().sendSrosMsg((Object) protocolBean);
    }

    public void replyEStop(boolean z, String str) {
        int i = this.replyServerMap.get(771, -1);
        if (i >= 0) {
            SparseIntArray sparseIntArray = this.replyServerMap;
            sparseIntArray.removeAt(sparseIntArray.indexOfKey(771));
            EStopBeanR eStopBeanR = new EStopBeanR();
            eStopBeanR.setResult(z);
            if (!z) {
                eStopBeanR.setReason(str);
            }
            ProtocolBean protocolBean = getProtocolBean();
            protocolBean.setSeq(i);
            protocolBean.setQa((byte) 1);
            protocolBean.setResult(z ^ true ? (short) 1 : 0);
            protocolBean.setCmd(SrosConstants.CONTROL_E_STOP);
            protocolBean.setBody(eStopBeanR);
            ClientIoSessionManager.getInstance().sendSrosMsg((Object) protocolBean);
        }
    }

    public void replyManualControl(boolean z, String str) {
        int i = this.replyServerMap.get(769, -1);
        if (i >= 0) {
            SparseIntArray sparseIntArray = this.replyServerMap;
            sparseIntArray.removeAt(sparseIntArray.indexOfKey(769));
            ManualControlBeanR manualControlBeanR = new ManualControlBeanR();
            manualControlBeanR.setResult(z);
            if (!z) {
                manualControlBeanR.setReason(str);
            }
            ProtocolBean protocolBean = getProtocolBean();
            protocolBean.setSeq(i);
            protocolBean.setQa((byte) 1);
            protocolBean.setResult(z ^ true ? (short) 1 : 0);
            protocolBean.setCmd(SrosConstants.CONTROL_MANUAL_CONTROL);
            protocolBean.setBody(manualControlBeanR);
            ClientIoSessionManager.getInstance().sendSrosMsg((Object) protocolBean);
        }
    }

    public void replyManualControl(boolean z, String str, int i) {
        ManualControlBeanR manualControlBeanR = new ManualControlBeanR();
        manualControlBeanR.setResult(z);
        if (!z) {
            manualControlBeanR.setReason(str);
        }
        ProtocolBean protocolBean = getProtocolBean();
        protocolBean.setSeq(i);
        protocolBean.setQa((byte) 1);
        protocolBean.setResult(z ^ true ? (short) 1 : 0);
        protocolBean.setCmd(SrosConstants.CONTROL_MANUAL_CONTROL);
        protocolBean.setBody(manualControlBeanR);
        ClientIoSessionManager.getInstance().sendSrosMsg((Object) protocolBean);
    }

    public void replyCoordinateNavigation(boolean z, String str) {
        int i = this.replyServerMap.get(775, -1);
        if (i >= 0) {
            SparseIntArray sparseIntArray = this.replyServerMap;
            sparseIntArray.removeAt(sparseIntArray.indexOfKey(775));
            CoordinateNavigationBeanR coordinateNavigationBeanR = new CoordinateNavigationBeanR();
            coordinateNavigationBeanR.setResult(z);
            if (!z) {
                coordinateNavigationBeanR.setReason(str);
            }
            ProtocolBean protocolBean = getProtocolBean();
            protocolBean.setSeq(i);
            protocolBean.setQa((byte) 1);
            protocolBean.setResult(z ^ true ? (short) 1 : 0);
            protocolBean.setCmd(SrosConstants.CONTROL_COORDINATE_NAVIGATION);
            protocolBean.setBody(coordinateNavigationBeanR);
            ClientIoSessionManager.getInstance().sendSrosMsg((Object) protocolBean);
        }
    }

    public void replyCoordinateNavigation(boolean z, String str, int i) {
        MyLogUtils.Logd("NETWORK_TAG", "replyCoordinateNavigation " + z + StringUtils.SPACE + str + StringUtils.SPACE + i);
        CoordinateNavigationBeanR coordinateNavigationBeanR = new CoordinateNavigationBeanR();
        coordinateNavigationBeanR.setResult(z);
        if (!z) {
            coordinateNavigationBeanR.setReason(str);
        }
        ProtocolBean protocolBean = getProtocolBean();
        protocolBean.setSeq(i);
        protocolBean.setQa((byte) 1);
        protocolBean.setResult(z ^ true ? (short) 1 : 0);
        protocolBean.setCmd(SrosConstants.CONTROL_COORDINATE_NAVIGATION);
        protocolBean.setBody(coordinateNavigationBeanR);
        ClientIoSessionManager.getInstance().sendSrosMsg((Object) protocolBean);
    }

    public void replyPositionNameNavigation(boolean z, String str) {
        int i = this.replyServerMap.get(777, -1);
        if (i >= 0) {
            SparseIntArray sparseIntArray = this.replyServerMap;
            sparseIntArray.removeAt(sparseIntArray.indexOfKey(777));
            PositionNameNavigationBeanR positionNameNavigationBeanR = new PositionNameNavigationBeanR();
            positionNameNavigationBeanR.setResult(z);
            if (!z) {
                positionNameNavigationBeanR.setReason(str);
            }
            ProtocolBean protocolBean = getProtocolBean();
            protocolBean.setSeq(i);
            protocolBean.setQa((byte) 1);
            protocolBean.setResult(z ^ true ? (short) 1 : 0);
            protocolBean.setCmd(SrosConstants.CONTROL_POSITION_NAME_NAVIGATION);
            protocolBean.setBody(positionNameNavigationBeanR);
            ClientIoSessionManager.getInstance().sendSrosMsg((Object) protocolBean);
        }
    }

    public void replyPositionNameNavigation(boolean z, String str, int i) {
        PositionNameNavigationBeanR positionNameNavigationBeanR = new PositionNameNavigationBeanR();
        positionNameNavigationBeanR.setResult(z);
        if (!z) {
            positionNameNavigationBeanR.setReason(str);
        }
        ProtocolBean protocolBean = getProtocolBean();
        protocolBean.setSeq(i);
        protocolBean.setQa((byte) 1);
        protocolBean.setResult(z ^ true ? (short) 1 : 0);
        protocolBean.setCmd(SrosConstants.CONTROL_POSITION_NAME_NAVIGATION);
        protocolBean.setBody(positionNameNavigationBeanR);
        ClientIoSessionManager.getInstance().sendSrosMsg((Object) protocolBean);
    }

    public void replyDeleteNavigationPoint(boolean z, String str) {
        int i = this.replyServerMap.get(523, -1);
        if (i >= 0) {
            SparseIntArray sparseIntArray = this.replyServerMap;
            sparseIntArray.removeAt(sparseIntArray.indexOfKey(523));
            DeleteNavigationPointBeanR deleteNavigationPointBeanR = new DeleteNavigationPointBeanR();
            deleteNavigationPointBeanR.setResult(z);
            if (!z) {
                deleteNavigationPointBeanR.setReason(str);
            }
            ProtocolBean protocolBean = getProtocolBean();
            protocolBean.setSeq(i);
            protocolBean.setQa((byte) 1);
            protocolBean.setResult(z ^ true ? (short) 1 : 0);
            protocolBean.setCmd(SrosConstants.CONTROL_DELETE_NAVIGATION_POINT);
            protocolBean.setBody(deleteNavigationPointBeanR);
            ClientIoSessionManager.getInstance().sendSrosMsg((Object) protocolBean);
        }
    }

    public void replyQueryMapList(final boolean z, WaterMapBean waterMapBean) {
        final int i = this.replyServerMap.get(1284, -1);
        if (i >= 0) {
            SparseIntArray sparseIntArray = this.replyServerMap;
            sparseIntArray.removeAt(sparseIntArray.indexOfKey(1284));
            Observable.create(new ObservableOnSubscribe<QueryMapBeanR>() {
                public void subscribe(ObservableEmitter<QueryMapBeanR> observableEmitter) throws Exception {
                    QueryMapBeanR queryMapBeanR = new QueryMapBeanR();
                    if (!FileUtils.isFileExists(WaterChassisConstants.WATER_MAIN_CONTROL_MAP_FILE_PATH)) {
                        queryMapBeanR.setReason("没有查找到地图文件");
                        queryMapBeanR.setResult(false);
                    } else {
                        queryMapBeanR.setResult(z);
                        if (!z) {
                            queryMapBeanR.setReason("");
                        }
                        queryMapBeanR.setMapType("png");
                        queryMapBeanR.setResolution(WaterChassisStatus.getInstance().getResolution());
                        String curMapName = WaterChassisStatus.getInstance().getCurMapName();
                        List<File> listFilesInDirWithFilter = FileUtils.listFilesInDirWithFilter(WaterChassisConstants.WATER_MAIN_CONTROL_MAP_FILE_PATH + curMapName + File.separator, (FileFilter) new ImageFileFilter());
                        ArrayList arrayList = new ArrayList();
                        for (int i = 0; i < listFilesInDirWithFilter.size(); i++) {
                            File file = listFilesInDirWithFilter.get(i);
                            Bitmap decodeFile = BitmapFactory.decodeFile(file.getAbsolutePath());
                            QueryMapBeanR.Map map = new QueryMapBeanR.Map();
                            map.setMapHeight(decodeFile.getHeight());
                            map.setMapWidth(decodeFile.getWidth());
                            map.setMapName(curMapName);
                            map.setOringinX(0);
                            map.setOringinY(0);
                            try {
                                int parseInt = Integer.parseInt(FileUtils.getFileNameNoExtension(file));
                                map.setZ(parseInt);
                                MySpUtils instance = MySpUtils.getInstance();
                                map.setMapInfo(instance.getString(curMapName + parseInt, ""));
                                arrayList.add(map);
                                decodeFile.recycle();
                            } catch (NumberFormatException unused) {
                                decodeFile.recycle();
                            }
                        }
                        queryMapBeanR.setMaps(arrayList);
                    }
                    observableEmitter.onNext(queryMapBeanR);
                    observableEmitter.onComplete();
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<QueryMapBeanR>() {
                public void onComplete() {
                }

                public void onError(Throwable th) {
                }

                public void onSubscribe(Disposable disposable) {
                }

                public void onNext(QueryMapBeanR queryMapBeanR) {
                    ProtocolBean protocolBean = SrosSendMsgUtil.this.getProtocolBean();
                    protocolBean.setSeq(i);
                    protocolBean.setQa((byte) 1);
                    protocolBean.setResult(true ^ z ? (short) 1 : 0);
                    protocolBean.setCmd(SrosConstants.CONTROL_QUERY_MAP_LIST);
                    protocolBean.setBody(queryMapBeanR);
                    ClientIoSessionManager.getInstance().sendSrosMsg((Object) protocolBean);
                }
            });
        }
    }

    public void replyGetLaserNavigationOriginPoint(boolean z, String str, int i, int i2, float f, int i3) {
        int i4 = this.replyServerMap.get(515, -1);
        if (i4 >= 0) {
            SparseIntArray sparseIntArray = this.replyServerMap;
            sparseIntArray.removeAt(sparseIntArray.indexOfKey(515));
            GetReferenceCoordinateBeanR getReferenceCoordinateBeanR = new GetReferenceCoordinateBeanR();
            getReferenceCoordinateBeanR.setResult(z);
            if (z) {
                getReferenceCoordinateBeanR.setMapName(WaterChassisStatus.getInstance().getCurMapName());
                getReferenceCoordinateBeanR.setX((float) i);
                getReferenceCoordinateBeanR.setY((float) i2);
                getReferenceCoordinateBeanR.setAngle(f);
                getReferenceCoordinateBeanR.setZ(i3);
                MySpUtils instance = MySpUtils.getInstance();
                getReferenceCoordinateBeanR.setMapInfo(instance.getString(WaterChassisStatus.getInstance().getCurMapName() + i3, ""));
            } else {
                getReferenceCoordinateBeanR.setReason(str);
            }
            ProtocolBean protocolBean = getProtocolBean();
            protocolBean.setSeq(i4);
            protocolBean.setQa((byte) 1);
            protocolBean.setResult(z ^ true ? (short) 1 : 0);
            protocolBean.setCmd(SrosConstants.CONTROL_GET_LASER_NAVIGATION_ORIGIN_POINT_COORDINATE);
            protocolBean.setBody(getReferenceCoordinateBeanR);
            ClientIoSessionManager.getInstance().sendSrosMsg((Object) protocolBean);
        }
    }

    public void sendCoordinate() {
        WaterChassisStatus instance = WaterChassisStatus.getInstance();
        getSameParams();
        CoordinateBean coordinateBean = new CoordinateBean();
        coordinateBean.setRobotType(this.mRobotType);
        coordinateBean.setRobotIP(this.mIpAddress);
        coordinateBean.setRobotID(String.valueOf(this.mRobotId));
        coordinateBean.setNowTime(this.mNowTime);
        coordinateBean.setSpeed(3);
        coordinateBean.setX(instance.getCoordinateX());
        coordinateBean.setY(instance.getCoordinateY());
        coordinateBean.setAngle(instance.getAngle());
        int curFloor = instance.getCurFloor();
        coordinateBean.setZ(curFloor);
        MySpUtils instance2 = MySpUtils.getInstance();
        coordinateBean.setMapInfo(instance2.getString(WaterChassisStatus.getInstance().getCurMapName() + curFloor, ""));
        ProtocolBean protocolBean = getProtocolBean();
        protocolBean.setSeq(getSeq());
        protocolBean.setCmd(SrosConstants.CONTROL_STATUS_COORDINATE);
        protocolBean.setBody(coordinateBean);
        ClientIoSessionManager.getInstance().sendSrosMsg((Object) protocolBean);
    }

    private void getSameParams() {
        this.mIpAddress = NetworkUtils.getIPAddress(true);
        this.mNowTime = new Date();
    }

    public void replyQueryMarkerPoint(final boolean z, final String str, final List<MarkerPoint> list, final int i) {
        Observable.create(new ObservableOnSubscribe<QueryNavigationBeanR>() {
            public void subscribe(ObservableEmitter<QueryNavigationBeanR> observableEmitter) {
                ArrayList arrayList = new ArrayList();
                if (list != null) {
                    for (int i = 0; i < list.size(); i++) {
                        MarkerPoint markerPoint = (MarkerPoint) list.get(i);
                        QueryNavigationBeanR.NavigationPoint navigationPoint = new QueryNavigationBeanR.NavigationPoint();
                        navigationPoint.setX(markerPoint.getX());
                        navigationPoint.setY(markerPoint.getY());
                        navigationPoint.setAngle(markerPoint.getAngle());
                        navigationPoint.setPositionname(markerPoint.getPositionName());
                        navigationPoint.setZ(markerPoint.getZ());
                        navigationPoint.setMapinfo(markerPoint.getMapInfo());
                        arrayList.add(navigationPoint);
                    }
                }
                QueryNavigationBeanR queryNavigationBeanR = new QueryNavigationBeanR();
                queryNavigationBeanR.setData(arrayList);
                queryNavigationBeanR.setResult(z);
                queryNavigationBeanR.setReason(str);
                observableEmitter.onNext(queryNavigationBeanR);
                observableEmitter.onComplete();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<QueryNavigationBeanR>() {
            public void onComplete() {
            }

            public void onError(Throwable th) {
            }

            public void onSubscribe(Disposable disposable) {
            }

            public void onNext(QueryNavigationBeanR queryNavigationBeanR) {
                ProtocolBean protocolBean = SrosSendMsgUtil.this.getProtocolBean();
                protocolBean.setSeq(i);
                protocolBean.setQa((byte) 1);
                protocolBean.setResult(true ^ z ? (short) 1 : 0);
                protocolBean.setCmd(SrosConstants.CONTROL_QUERY_NAVIGATION_POINT);
                protocolBean.setBody(queryNavigationBeanR);
                ClientIoSessionManager.getInstance().sendSrosMsg((Object) protocolBean);
            }
        });
    }

    public void addReplyServerMap(int i, int i2) {
        this.replyServerMap.put(i, i2);
    }

    public void replyQueryNavigationPoint(boolean z, List<QueryNavigationBeanR.NavigationPoint> list) {
        int i = this.replyServerMap.get(522, -1);
        if (i >= 0) {
            SparseIntArray sparseIntArray = this.replyServerMap;
            sparseIntArray.removeAt(sparseIntArray.indexOfKey(522));
            QueryNavigationBeanR queryNavigationBeanR = new QueryNavigationBeanR();
            if (z) {
                queryNavigationBeanR.setData(list);
            }
            queryNavigationBeanR.setResult(z);
            ProtocolBean protocolBean = getProtocolBean();
            protocolBean.setSeq(i);
            protocolBean.setQa((byte) 1);
            protocolBean.setResult(z ^ true ? (short) 1 : 0);
            protocolBean.setCmd(SrosConstants.CONTROL_QUERY_NAVIGATION_POINT);
            protocolBean.setBody(queryNavigationBeanR);
            ClientIoSessionManager.getInstance().sendSrosMsg((Object) protocolBean);
            MyLogUtils.Logi("NAVIGATION_TAG", "replyQueryNavigationPoint queryNavigationBeanR:" + queryNavigationBeanR.toString());
            MyLogUtils.Logi("NAVIGATION_TAG", "replyQueryNavigationPoint protocolBean:" + protocolBean.toString());
        }
    }

    public void sendLocalStartPhoneCall(int i, int i2, int i3) {
        if (AppSpUtil.getInstance().getRobotRealType() == 5) {
            StartLocalPhoneCallBean startLocalPhoneCallBean = new StartLocalPhoneCallBean();
            startLocalPhoneCallBean.setRobotType(this.mRobotType);
            startLocalPhoneCallBean.setRobotIP(this.mIpAddress);
            String robotNumber = AppSpUtil.getInstance().getRobotNumber();
            this.mRobotId = robotNumber;
            startLocalPhoneCallBean.setRobotID(String.valueOf(robotNumber));
            startLocalPhoneCallBean.setNowtime(new Date());
            startLocalPhoneCallBean.setStream(i);
            startLocalPhoneCallBean.setChannel(i2);
            startLocalPhoneCallBean.setSource(i3);
            ProtocolBean protocolBean = getProtocolBean();
            protocolBean.setSeq(getSeq());
            protocolBean.setCmd(2064);
            protocolBean.setBody(startLocalPhoneCallBean);
            MyLogUtils.Logi("NETWORK_TAG", "上报语音呼叫:" + GsonUtils.getGson().toJson((Object) startLocalPhoneCallBean));
            ClientIoSessionManager.getInstance().sendSrosMsg((Object) protocolBean);
        }
    }

    public void sendLocalStopPhoneCall(int i, int i2, String str, String str2) {
        StopLocalPhoneCallBean stopLocalPhoneCallBean = new StopLocalPhoneCallBean();
        stopLocalPhoneCallBean.setRobotType(this.mRobotType);
        stopLocalPhoneCallBean.setRobotIP(this.mIpAddress);
        String robotNumber = AppSpUtil.getInstance().getRobotNumber();
        this.mRobotId = robotNumber;
        stopLocalPhoneCallBean.setRobotID(String.valueOf(robotNumber));
        stopLocalPhoneCallBean.setNowtime(new Date());
        stopLocalPhoneCallBean.setStream(i);
        stopLocalPhoneCallBean.setChannel(i2);
        stopLocalPhoneCallBean.setClient(str);
        stopLocalPhoneCallBean.setHandle(str2);
        ProtocolBean protocolBean = getProtocolBean();
        protocolBean.setSeq(getSeq());
        protocolBean.setCmd(2065);
        protocolBean.setBody(stopLocalPhoneCallBean);
        MyLogUtils.Logi("NETWORK_TAG", "停止语音呼叫:" + GsonUtils.getGson().toJson((Object) stopLocalPhoneCallBean));
        ClientIoSessionManager.getInstance().sendSrosMsg((Object) protocolBean);
    }
}
