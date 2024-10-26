package com.example.sroslibrary.sros;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.ciot.base.config.CommonConfig;
import com.ciot.base.constant.FileConstant;
import com.ciot.base.constant.NetConstant;
import com.ciot.base.util.AppSpUtil;
import com.ciot.base.util.LogPlus;
import com.ciot.base.util.MyLogUtils;
import com.ciot.base.util.ThreadPoolProxyFactory;
import com.ciot.realm.db.MarkerPoint;
import com.ciot.realm.db.Person;
import com.ciot.realm.util.RealmHelper;
import com.example.sroslibrary.SrosManager;
import com.example.sroslibrary.bean.ActivateLicenseBeanR;
import com.example.sroslibrary.bean.NetInitProcessBean;
import com.example.sroslibrary.bean.NotificationBean;
import com.example.sroslibrary.bean.ProtocolBean;
import com.example.sroslibrary.bean.QueryMarkerLiveData;
import com.example.sroslibrary.bean.QueryNavigationBean;
import com.example.sroslibrary.bean.ResultBean;
import com.example.sroslibrary.bean.RobotStatusResponse;
import com.example.sroslibrary.bean.SrosEventMsg;
import com.example.sroslibrary.livedata.NetInitProcesLiveData;
import com.example.sroslibrary.livedata.RobotStatusLiveData;
import com.example.sroslibrary.monitor.VideoProcessHandler;
import com.example.sroslibrary.sros.SrosHandlerAdapter;
import com.google.gson.Gson;
import com.tencent.bugly.Bugly;
import io.reactivex.ObservableEmitter;
import io.reactivex.disposables.Disposable;
import java.io.File;
import java.io.FileFilter;
import java.util.List;
import mc.csst.com.selfchassis.ui.fragment.set.schedule.ScheduleFragment;
import mc.csst.com.selfchassislibrary.content.OpContent;
import org.greenrobot.eventbus.EventBus;

public class SrosHandlerCallback implements SrosHandlerAdapter.OnSrosHandlerCallBack {
    private static final int RETRY_REGISTER_SROS_INTERVAL_TIME = 10000;
    public static final int STATE_ACTIVATE_FAILED = 4;
    public static final int STATE_ACTIVATE_SUCCESS = 3;
    public static final int STATE_CONNECT_SUCCESS = 1;
    public static final int STATE_DISCONNECT = -1;
    public static final int STATE_REGISTER_SUCCESS = 2;
    private static final String TAG = "NETWORK_TAG";
    private ActivateLicenseBeanR mActivateLicense;
    private Runnable mActivateLicenseRunnable;
    volatile int mCurrentState;
    private Gson mGson;
    private OnConnectedServerListener mOnConnectedServerListener;
    private Runnable mRegisterRobotRunnable;
    /* access modifiers changed from: private */
    public Handler mRetrySendMsgHandler;
    private Disposable mVersionDisposable;

    public interface OnConnectedServerListener {
        void onConnectedServer();
    }

    public SrosHandlerCallback() {
        this.mCurrentState = -1;
        this.mCurrentState = -1;
        if (this.mRetrySendMsgHandler == null) {
            this.mRetrySendMsgHandler = new Handler();
        }
        if (this.mGson == null) {
            this.mGson = new Gson();
        }
    }

    public int getCurrentState() {
        return this.mCurrentState;
    }

    public void sendMsg2RetrofitManager() {
        if (this.mCurrentState == 3 && this.mActivateLicense != null) {
            MyLogUtils.Logd("NETWORK_TAG", "sendMsg2RetrofitManager");
            ThreadPoolProxyFactory.getNormalThreadPoolProxy().execute(new Runnable() {
                public final void run() {
                    SrosHandlerCallback.this.lambda$sendMsg2RetrofitManager$0$SrosHandlerCallback();
                }
            });
        }
    }

    public /* synthetic */ void lambda$sendMsg2RetrofitManager$0$SrosHandlerCallback() {
        sendMsg(this.mActivateLicense);
    }

    public void messageReceived(Object obj) {
        replyServer((ProtocolBean) obj);
    }

    private void replyServer(ProtocolBean protocolBean) {
        short cmd = protocolBean.getCmd();
        if (cmd == -32544) {
            ResultBean resultBean = (ResultBean) protocolBean.getBody();
        } else if (cmd == -30975) {
            MyLogUtils.Logw("1机器人注册: " + protocolBean.toString());
            deviceManagementRegister(protocolBean);
        } else if (cmd == -30968) {
            MyLogUtils.Logw("激活: " + protocolBean.toString());
            activateRobot(protocolBean);
        } else if (cmd == 17) {
            MyLogUtils.Logw("主动通知： " + protocolBean.toString());
            notification(protocolBean);
        } else if (cmd == 522) {
            MyLogUtils.Logd("NETWORK_TAG", "查询导航点");
            SrosSendMsgUtil.getInstance().addReplyServerMap(protocolBean.getCmd(), protocolBean.getSeq());
            ObservableEmitter<Boolean> emitter = QueryMarkerLiveData.Companion.getInstance().getEmitter();
            if (emitter != null) {
                emitter.onNext(true);
            }
        } else if (cmd == 1284) {
            queryMapList(protocolBean);
        }
        if (AppSpUtil.getInstance().getRobotRealType() == 5) {
            VideoProcessHandler.getInstance().requestVideo(protocolBean);
        }
    }

    private void queryMapList(ProtocolBean protocolBean) {
        SrosSendMsgUtil.getInstance().addReplyServerMap(protocolBean.getCmd(), protocolBean.getSeq());
        SrosEventMsg srosEventMsg = new SrosEventMsg();
        srosEventMsg.setMsgType("/api/map/list");
        EventBus.getDefault().post(srosEventMsg);
    }

    private void queryNavigationPoint(ProtocolBean protocolBean) {
        String mapName = ((QueryNavigationBean) protocolBean.getBody()).getMapName();
        if (TextUtils.isEmpty(mapName)) {
            SrosSendMsgUtil.getInstance().replyQueryMarkerPoint(false, "地图名称不能为空", (List<MarkerPoint>) null, protocolBean.getSeq());
            return;
        }
        SrosSendMsgUtil.getInstance().replyQueryMarkerPoint(true, "", RealmHelper.getInstance().findMarkerPointByMapName(mapName), protocolBean.getSeq());
    }

    private void activateRobot(ProtocolBean protocolBean) {
        ActivateLicenseBeanR activateLicenseBeanR = (ActivateLicenseBeanR) protocolBean.getBody();
        MyLogUtils.Logd("NETWORK_TAG", "接收激活结果: " + activateLicenseBeanR.toString());
        RobotStatusLiveData.Companion.get().postValue(new RobotStatusResponse(activateLicenseBeanR.isResult() ? "activite" : "unActivite"));
        if (activateLicenseBeanR.isResult()) {
            this.mActivateLicense = activateLicenseBeanR;
            this.mCurrentState = 3;
            AppSpUtil.getInstance().setRobotActivate(true);
            sendMsg(activateLicenseBeanR);
        } else {
            this.mCurrentState = 4;
            AppSpUtil.getInstance().setRobotActivate(false);
        }
        if (TextUtils.isEmpty(SrosManager.getInstance().getMsgContent(NetConstant.MSG_SROS_GET_TOKEN))) {
            EventBus.getDefault().post(new SrosEventMsg(Bugly.SDK_IS_DEV));
        }
    }

    private void sendMsg(ActivateLicenseBeanR activateLicenseBeanR) {
        SrosManager.getInstance().sendMsg(NetConstant.MSG_SROS_IMAGE_BASE_URL, activateLicenseBeanR.getResourceUrl());
        Handler handler = this.mRetrySendMsgHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages((Object) null);
        }
        String format = String.format("http://%1$s:%2$d/", new Object[]{activateLicenseBeanR.getPlatform_ip(), Integer.valueOf(activateLicenseBeanR.getPlat_port())});
        MyLogUtils.Logd("NETWORK_TAG", "get WuHan BaseUrl:" + format);
        SrosManager.getInstance().sendMsg(NetConstant.MSG_SROS_SET_PLATFORM_BASE_URL, format);
        SrosManager.getInstance().sendMsg(NetConstant.MSG_SROS_SET_USER_NAME, activateLicenseBeanR.getUsername());
        SrosManager.getInstance().sendMsg(NetConstant.MSG_SROS_SET_PASSWORD, activateLicenseBeanR.getPassword());
        this.mRetrySendMsgHandler.postDelayed(new Runnable() {
            public void run() {
                MyLogUtils.Logi("NETWORK_TAG", "activation delayTask excute,RETRY_REGISTER_SROS_INTERVAL_TIME=10000");
                if (SrosHandlerCallback.this.mCurrentState == -1 || SrosHandlerCallback.this.mCurrentState == 1 || SrosHandlerCallback.this.mCurrentState == 2) {
                    MyLogUtils.Logw("NETWORK_TAG", "No activation reply message received after 10 seconds,ready reconnectTcp");
                    SrosHandlerCallback.this.mRetrySendMsgHandler.removeCallbacksAndMessages((Object) null);
                    NetInitProcesLiveData.Companion.get().postValue(new NetInitProcessBean(true));
                }
            }
        }, 10000);
    }

    private void notification(ProtocolBean protocolBean) {
        NotificationBean notificationBean = (NotificationBean) protocolBean.getBody();
        int type = notificationBean.getType();
        SrosSendMsgUtil.getInstance().addReplyServerMap(protocolBean.getCmd(), protocolBean.getSeq());
        if (type == 2) {
            syncInfo(notificationBean);
        }
    }

    private void replyNotification(boolean z, String str) {
        SrosSendMsgUtil.getInstance().replyNotification(true, "");
    }

    private void syncInfo(NotificationBean notificationBean) {
        if (notificationBean == null) {
            MyLogUtils.Logw("NotificationBean is null");
            replyNotification(false, "NotificationBean is Null");
            return;
        }
        MyLogUtils.Logi("NETWORK_TAG", "SrosHandlerCallback NotificationBean:" + notificationBean.toString());
        NotificationBean.DescBean desc = notificationBean.getDesc();
        if (desc == null) {
            replyNotification(false, "desc is Null");
            return;
        }
        Object data = desc.getData();
        NotificationBean.DescBean.DataBean dataBean = (NotificationBean.DescBean.DataBean) GsonUtils.fromJson(GsonUtils.toJson(data), NotificationBean.DescBean.DataBean.class);
        String str = null;
        if (dataBean != null) {
            str = dataBean.getId();
        }
        if (OpContent.PUBLISH.equalsIgnoreCase(desc.getName())) {
            if ("notify".equalsIgnoreCase(desc.getType()) || "create".equalsIgnoreCase(desc.getType())) {
                MyLogUtils.Logi(CommonConfig.TAG_AD, "tag_ad: " + notificationBean);
                SrosManager.getInstance().sendMsg(NetConstant.MSG_SROS_AD_REFRESH, "");
            }
            replyNotification(true, "");
        } else if (Person.VISITOR.equalsIgnoreCase(desc.getName())) {
            if ("create".equalsIgnoreCase(desc.getType()) || "update".equalsIgnoreCase(desc.getType()) || "arccode".equalsIgnoreCase(desc.getType())) {
                SrosManager.getInstance().sendMsg(NetConstant.MSG_SROS_UPDATE_VISITOR, str);
            } else if (RequestParameters.SUBRESOURCE_DELETE.equalsIgnoreCase(desc.getType())) {
                SrosManager.getInstance().sendMsg(NetConstant.MSG_SROS_DELETE_VISITOR, str);
            }
            replyNotification(true, "");
        } else if (Person.EMPLOYEE.equalsIgnoreCase(desc.getName())) {
            if ("create".equalsIgnoreCase(desc.getType()) || "update".equalsIgnoreCase(desc.getType()) || "arccode".equalsIgnoreCase(desc.getType())) {
                SrosManager.getInstance().sendMsg(NetConstant.MSG_SROS_UPDATE_EMPLOEE, str);
            } else if (RequestParameters.SUBRESOURCE_DELETE.equalsIgnoreCase(desc.getType())) {
                SrosManager.getInstance().sendMsg(NetConstant.MSG_SROS_UPDATE_EMPLOEE, str);
            }
            replyNotification(true, "");
        } else if ("record".equalsIgnoreCase(desc.getName())) {
            if ("ack".equalsIgnoreCase(desc.getType()) || "update".equalsIgnoreCase(desc.getType()) || "register".equalsIgnoreCase(desc.getType())) {
                SrosManager.getInstance().sendMsg(NetConstant.MSG_SROS_UPDATE_VERIFY, str);
            } else if (RequestParameters.SUBRESOURCE_DELETE.equalsIgnoreCase(desc.getType())) {
                SrosManager.getInstance().sendMsg(NetConstant.MSG_SROS_DELETE_VERIFY, str);
            }
            replyNotification(true, "");
        } else if ("version".equalsIgnoreCase(desc.getName())) {
            if ("update".equalsIgnoreCase(desc.getType())) {
                SrosManager.getInstance().sendMsg(NetConstant.MSG_SROS_VERSION_UPDATE, str);
            }
            replyNotification(true, "");
        } else if ("log".equalsIgnoreCase(desc.getName())) {
            if ("upload".equalsIgnoreCase(desc.getType())) {
                SrosManager.getInstance().sendMsg(NetConstant.MSG_SROS_UPLOAD_LOG, str);
            }
        } else if ("robot".equalsIgnoreCase(desc.getName())) {
            if ("cancel".equalsIgnoreCase(desc.getType())) {
                logoutCurrentUser();
            }
            replyNotification(true, "");
        } else if ("voice".equalsIgnoreCase(desc.getName())) {
            MyLogUtils.Loge("NETWORK_TAG", "receive wuhan voiceCode msg:" + desc.toString());
            if ("activate".equalsIgnoreCase(desc.getType())) {
                if (dataBean != null) {
                    SrosManager.getInstance().sendMsg(NetConstant.MSG_SROS_VOICE_CODE_ACTIVATE, dataBean.code);
                }
            } else if ("cancel".equalsIgnoreCase(desc.getType())) {
                SrosManager.getInstance().sendMsg(NetConstant.MSG_SROS_VOICE_CODE_CANCLE, "");
            }
            replyNotification(true, "");
        } else if (FileConstant.VIDEO_FILE_NAME.equalsIgnoreCase(desc.getName())) {
            if ("activate".equalsIgnoreCase(desc.getType())) {
                if (dataBean != null) {
                    SrosManager.getInstance().sendMsg(NetConstant.MSG_SROS_VIDEO_ACTIVATE, dataBean.code);
                }
            } else if ("cancel".equalsIgnoreCase(desc.getType()) && dataBean != null) {
                SrosManager.getInstance().sendMsg(NetConstant.MSG_SROS_VIDEO_CANCEL, dataBean.code);
            }
            if (dataBean != null) {
                Log.d("dddfsfsadfa", "syncInfo: " + dataBean.code);
            }
            replyNotification(true, "");
        } else if ("updateProperty".equals(desc.getName())) {
            if ("update".equalsIgnoreCase(desc.getType()) && dataBean != null) {
                SrosManager.getInstance().sendMsg(NetConstant.MSG_SROS_UPDATE_PROPERTY, "");
            }
        } else if ("patrolTask".equalsIgnoreCase(desc.getName())) {
            Log.d("NETWORK_TAG", "SrosHandlerCallback syncInfo 巡更任务 id:" + str);
            if ("create".equalsIgnoreCase(desc.getType())) {
                Log.d("NETWORK_TAG", "SrosHandlerCallback syncInfo 巡更任务添加");
                SrosManager.getInstance().sendMsg(NetConstant.MSG_SROS_PATROL_TASK_REFRESH, str);
            } else if ("update".equalsIgnoreCase(desc.getType())) {
                SrosManager.getInstance().sendMsg(NetConstant.MSG_SROS_PATROL_TASK_REFRESH, str);
            } else if (RequestParameters.SUBRESOURCE_DELETE.equalsIgnoreCase(desc.getType())) {
                Log.d("NETWORK_TAG", "SrosHandlerCallback syncInfo 巡更任务删除");
                RealmHelper.getInstance().deletePatrolTaskById(str);
            }
            replyNotification(true, "");
        } else if ("yunji".equalsIgnoreCase(desc.getName())) {
            Log.d("NETWORK_TAG", "Notification mapData: " + desc.getData());
            if ("uploadmark".equalsIgnoreCase(desc.getType())) {
                SrosManager.getInstance().sendMsg(NetConstant.MSG_SROS_UPLOAD_MAP, str);
            } else if ("downloadmark".equalsIgnoreCase(desc.getType())) {
                Log.d("NETWORK_TAG", "Notification mapData: " + data);
                if (data == null) {
                    replyNotification(false, "data is Null");
                } else {
                    SrosManager.getInstance().sendMsg(NetConstant.MSG_SROS_DOWNLOAD_MAP, GsonUtils.toJson(data));
                }
            }
        } else if ("robotStatus".equalsIgnoreCase(desc.getName())) {
            RobotStatusLiveData.Companion.get().postValue(new RobotStatusResponse(desc.getType()));
        } else if ("robotIsLock".equalsIgnoreCase(desc.getName())) {
            LogPlus.w("robotIsLock===" + desc.getType());
            AppSpUtil.getInstance().setRobotLock(desc.getType());
            RobotStatusLiveData.Companion.get().postValue(new RobotStatusResponse(desc.getType(), ""));
        } else if ("tactics".equalsIgnoreCase(desc.getName())) {
            SrosManager.getInstance().sendMsg(NetConstant.MSG_SROS_CHANGE_TACTICS, "");
        } else if ("updateProperty".equals(desc.getName())) {
            if ("update".equalsIgnoreCase(desc.getType()) && desc.getData() != null) {
                SrosManager.getInstance().sendMsg(NetConstant.MSG_SROS_UPDATE_PROPERTY, "");
            }
        } else if ("attract".equalsIgnoreCase(desc.getName())) {
            SrosManager.getInstance().sendMsg(NetConstant.MSG_SROS_CHANGE_GREETINGS_DISTANTLY, "");
        } else if ("entertain".equalsIgnoreCase(desc.getName())) {
            SrosManager.getInstance().sendMsg(NetConstant.MSG_SROS_CHANGE_GREETINGS_NEARBY, "");
        } else if ("informative".equalsIgnoreCase(desc.getName())) {
            SrosManager.getInstance().sendMsg(NetConstant.MSG_SROS_CHANGE_HOME_NEWS, "");
        } else if ("hotel".equalsIgnoreCase(desc.getName())) {
            if ("activity".equals(desc.getType())) {
                SrosManager.getInstance().sendMsg(NetConstant.MSG_SROS_CHANGE_HOTEL_ACTIVITY, "");
            }
        } else if (ScheduleFragment.MAP.equalsIgnoreCase(desc.getName()) && "update".equals(desc.getType())) {
            Log.d("NETWORK_TAG", "更新地图");
            SrosManager.getInstance().sendMsg(NetConstant.MSG_SROS_SELF_MAP_UPDATE, str);
        }
    }

    private void logoutCurrentUser() {
        SrosManager.getInstance().sendMsg(NetConstant.MSG_SROS_SET_TOKEN, "");
        registerServerAndRetry();
    }

    private void registerServerAndRetry() {
        if (this.mRegisterRobotRunnable == null) {
            this.mRegisterRobotRunnable = new Runnable() {
                public void run() {
                    SrosSendMsgUtil.getInstance().sendRegisterRobot();
                    SrosHandlerCallback.this.mRetrySendMsgHandler.postDelayed(this, 10000);
                }
            };
        }
        this.mRetrySendMsgHandler.postDelayed(this.mRegisterRobotRunnable, 0);
    }

    private void deviceManagementRegister(ProtocolBean protocolBean) {
        ResultBean resultBean = (ResultBean) protocolBean.getBody();
        RobotStatusLiveData.Companion.get().postValue(new RobotStatusResponse(resultBean.getResult() ? "activite" : "unActivite"));
        if (resultBean.getResult()) {
            MyLogUtils.Logi("NETWORK_TAG", "注册成功");
            this.mCurrentState = 2;
            OnConnectedServerListener onConnectedServerListener = this.mOnConnectedServerListener;
            if (onConnectedServerListener != null) {
                onConnectedServerListener.onConnectedServer();
            }
            this.mRetrySendMsgHandler.removeCallbacks(this.mRegisterRobotRunnable);
            activateLicense();
        }
    }

    private void activateLicense() {
        if (this.mActivateLicenseRunnable == null) {
            this.mActivateLicenseRunnable = new Runnable() {
                public final void run() {
                    SrosHandlerCallback.this.lambda$activateLicense$1$SrosHandlerCallback();
                }
            };
        }
        this.mRetrySendMsgHandler.postDelayed(this.mActivateLicenseRunnable, 0);
        this.mRetrySendMsgHandler.postDelayed(new Runnable() {
            public void run() {
                MyLogUtils.Logi("NETWORK_TAG", "activation delayTask excute,RETRY_REGISTER_SROS_INTERVAL_TIME=10000");
                if (SrosHandlerCallback.this.mCurrentState == -1 || SrosHandlerCallback.this.mCurrentState == 1 || SrosHandlerCallback.this.mCurrentState == 2) {
                    MyLogUtils.Logw("NETWORK_TAG", "No activation reply message received after 10 seconds,ready reconnectTcp");
                    SrosHandlerCallback.this.mRetrySendMsgHandler.removeCallbacksAndMessages((Object) null);
                    NetInitProcesLiveData.Companion.get().postValue(new NetInitProcessBean(true));
                }
            }
        }, 10000);
    }

    public /* synthetic */ void lambda$activateLicense$1$SrosHandlerCallback() {
        SrosSendMsgUtil.getInstance().sendActivateLicense();
        this.mRetrySendMsgHandler.postDelayed(this.mActivateLicenseRunnable, 10000);
    }

    public void sessionOpen() {
        MyLogUtils.Logi("NETWORK_TAG", "已连接Sros");
        this.mCurrentState = 1;
        registerServerAndRetry();
    }

    public void sessionClose() {
        this.mRetrySendMsgHandler.removeCallbacksAndMessages((Object) null);
        MyLogUtils.Logi("NETWORK_TAG", "已断开Sros");
        this.mCurrentState = -1;
        this.mActivateLicense = null;
    }

    public void setConnectedServerListener(OnConnectedServerListener onConnectedServerListener) {
        this.mOnConnectedServerListener = onConnectedServerListener;
    }

    private FileFilter getCrashFileFilter() {
        return $$Lambda$SrosHandlerCallback$8EC8m9h9Q2AphNtHWlQLH6rGOo.INSTANCE;
    }

    static /* synthetic */ boolean lambda$getCrashFileFilter$2(File file) {
        String fileExtension = FileUtils.getFileExtension(file.getAbsolutePath());
        return "xcrash".equals(fileExtension) || "zip".equals(fileExtension);
    }

    private void deleteCrashFiles(List<File> list) {
        for (int i = 0; i < list.size(); i++) {
            FileUtils.delete(list.get(i));
        }
    }
}
