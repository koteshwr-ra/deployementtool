package com.ciot.networklib;

import android.text.TextUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.ciot.base.constant.NetConstant;
import com.ciot.base.util.MyLogUtils;
import com.ciot.networklib.bean.DataUpdateEvent;
import com.ciot.networklib.callback.GetVideoCodeCallback;
import com.example.sroslibrary.OnSrosManagerListener;
import com.example.sroslibrary.bean.NotificationBean;
import io.reactivex.ObservableEmitter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;

@Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0016J\u001a\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003H\u0016¨\u0006\b"}, d2 = {"com/ciot/networklib/RetrofitManager$setAccountListener$1", "Lcom/example/sroslibrary/OnSrosManagerListener;", "getMsgContent", "", "msgType", "sendMsg", "", "msgContent", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: RetrofitManager.kt */
public final class RetrofitManager$setAccountListener$1 implements OnSrosManagerListener {
    final /* synthetic */ ObservableEmitter<Boolean> $e;
    final /* synthetic */ RetrofitManager this$0;

    RetrofitManager$setAccountListener$1(RetrofitManager retrofitManager, ObservableEmitter<Boolean> observableEmitter) {
        this.this$0 = retrofitManager;
        this.$e = observableEmitter;
    }

    public void sendMsg(String str, String str2) {
        ObservableEmitter<Boolean> observableEmitter;
        Intrinsics.checkNotNullParameter(str, "msgType");
        String access$getTAG$p = this.this$0.TAG;
        MyLogUtils.Logd(access$getTAG$p, "SrosManager sendMsg:msgType=" + str + ",msgContent=" + str2);
        switch (str.hashCode()) {
            case -2096010187:
                if (str.equals(NetConstant.MSG_SROS_CHANGE_GREETINGS_NEARBY)) {
                    this.this$0.getNearbyServices();
                    break;
                }
                break;
            case -1996325205:
                if (str.equals(NetConstant.MSG_SROS_CHANGE_HOTEL_ACTIVITY)) {
                    this.this$0.getHotelActivity();
                    break;
                }
                break;
            case -1942648349:
                if (str.equals(NetConstant.MSG_SROS_DOWNLOAD_MAP) && this.this$0.mRobotRealType == 5) {
                    NotificationBean.DescBean.MapDataBean mapDataBean = (NotificationBean.DescBean.MapDataBean) GsonUtils.fromJson(str2, NotificationBean.DescBean.MapDataBean.class);
                    RetrofitManager retrofitManager = this.this$0;
                    Intrinsics.checkNotNullExpressionValue(mapDataBean, "mapData");
                    retrofitManager.downloadYunJiMap(mapDataBean);
                    break;
                }
            case -1919895339:
                if (str.equals(NetConstant.MSG_SROS_DELETE_EMPLOEE)) {
                    EventBus.getDefault().post(new DataUpdateEvent(6, str2));
                    break;
                }
                break;
            case -1889661884:
                if (str.equals(NetConstant.MSG_SROS_UPLOAD_LOG)) {
                    this.this$0.uploadLogFile();
                    break;
                }
                break;
            case -1889661348:
                if (str.equals(NetConstant.MSG_SROS_UPLOAD_MAP)) {
                    this.this$0.uploadYuJiMap();
                    break;
                }
                break;
            case -1804992626:
                if (str.equals(NetConstant.MSG_SROS_VERSION_UPDATE)) {
                    EventBus.getDefault().post(new DataUpdateEvent(7, str2));
                    break;
                }
                break;
            case -1799325585:
                if (str.equals(NetConstant.MSG_SROS_DELETE_VERIFY)) {
                    EventBus.getDefault().post(new DataUpdateEvent(2, str2));
                    break;
                }
                break;
            case -1433416267:
                if (str.equals(NetConstant.MSG_SROS_VIDEO_ACTIVATE)) {
                    this.this$0.saveVideoCode2Local(str2);
                    GetVideoCodeCallback getVideoCodeCallback = this.this$0.getGetVideoCodeCallback();
                    if (getVideoCodeCallback != null) {
                        getVideoCodeCallback.onSuccess(str2);
                        break;
                    }
                }
                break;
            case -1200883658:
                if (str.equals(NetConstant.MSG_SROS_UPDATE_VISITOR)) {
                    EventBus.getDefault().post(new DataUpdateEvent(3, str2));
                    break;
                }
                break;
            case -1150512403:
                if (str.equals(NetConstant.MSG_SROS_UPDATE_PROPERTY)) {
                    RetrofitManager retrofitManager2 = this.this$0;
                    String wuHanUserName = retrofitManager2.getWuHanUserName();
                    Intrinsics.checkNotNull(wuHanUserName);
                    String token = this.this$0.getToken();
                    Intrinsics.checkNotNull(token);
                    retrofitManager2.getAuthorizationCode(wuHanUserName, token);
                    break;
                }
                break;
            case -1109722506:
                if (str.equals(NetConstant.MSG_SROS_PATROL_TASK_REFRESH) && str2 != null) {
                    this.this$0.loadPatrolTaskFromServer(str2);
                    break;
                }
            case -987429310:
                if (str.equals(NetConstant.MSG_SROS_CHANGE_HOME_NEWS)) {
                    this.this$0.getNewsList();
                    break;
                }
                break;
            case -893750340:
                if (str.equals(NetConstant.MSG_SROS_VIDEO_CANCEL)) {
                    this.this$0.deleteLocalVideoCode();
                    GetVideoCodeCallback getVideoCodeCallback2 = this.this$0.getGetVideoCodeCallback();
                    if (getVideoCodeCallback2 != null) {
                        getVideoCodeCallback2.onLogout();
                        break;
                    }
                }
                break;
            case -507603203:
                if (str.equals(NetConstant.MSG_SROS_AD_REFRESH)) {
                    this.this$0.setAdData();
                    break;
                }
                break;
            case -489366108:
                if (str.equals(NetConstant.MSG_SROS_SET_USER_NAME)) {
                    this.this$0.setWuHanUserName(str2);
                    break;
                }
                break;
            case -207271086:
                if (str.equals(NetConstant.MSG_SROS_SET_PLATFORM_BASE_URL)) {
                    this.this$0.setInitState(0);
                    if (str2 != null) {
                        this.this$0.setWuHanBaseUrl(str2);
                        break;
                    }
                }
                break;
            case -80142870:
                if (str.equals(NetConstant.MSG_SROS_CHANGE_TACTICS)) {
                    this.this$0.getTactics();
                    break;
                }
                break;
            case 39321619:
                if (str.equals(NetConstant.MSG_SROS_VOICE_CODE_CANCLE)) {
                    this.this$0.deleteLocalVoiceCode();
                    break;
                }
                break;
            case 80921302:
                if (str.equals(NetConstant.MSG_SROS_SET_PASSWORD)) {
                    this.this$0.setWuHanPassWord(str2);
                    break;
                }
                break;
            case 170935064:
                if (str.equals(NetConstant.MSG_SROS_DELETE_VISITOR)) {
                    EventBus.getDefault().post(new DataUpdateEvent(4, str2));
                    break;
                }
                break;
            case 417305144:
                if (str.equals(NetConstant.MSG_SROS_CHANGE_GREETINGS_DISTANTLY)) {
                    this.this$0.getDistantlyServices();
                    break;
                }
                break;
            case 434861295:
                if (str.equals(NetConstant.MSG_SROS_TCP_ONLINE) && !TextUtils.isEmpty(str2)) {
                    if (!StringsKt.equals$default(str2, "true", false, 2, (Object) null)) {
                        this.this$0.setIsTcpOnline(false);
                        break;
                    } else {
                        this.this$0.setIsTcpOnline(true);
                        break;
                    }
                }
            case 470367047:
                if (str.equals(NetConstant.MSG_SROS_ARCCODE_UPDATE)) {
                    EventBus.getDefault().post(new DataUpdateEvent(8, str2));
                    break;
                }
                break;
            case 1003253235:
                if (str.equals(NetConstant.MSG_SROS_UPDATE_EMPLOEE)) {
                    EventBus.getDefault().post(new DataUpdateEvent(5, str2));
                    break;
                }
                break;
            case 1204463505:
                if (str.equals(NetConstant.MSG_SROS_UPDATE_VERIFY)) {
                    EventBus.getDefault().post(new DataUpdateEvent(1, str2));
                    break;
                }
                break;
            case 1884985027:
                if (str.equals(NetConstant.MSG_SROS_IMAGE_BASE_URL)) {
                    RetrofitManager retrofitManager3 = this.this$0;
                    Intrinsics.checkNotNull(str2);
                    retrofitManager3.setDownBaseUrl(str2);
                    break;
                }
                break;
            case 1895336954:
                if (str.equals(NetConstant.MSG_SROS_VOICE_CODE_ACTIVATE) && !TextUtils.isEmpty(str2)) {
                    this.this$0.saveVoiceCode2Local(str2);
                    break;
                }
        }
        if (this.this$0.getInitState() == 0 && !TextUtils.isEmpty(this.this$0.getWuHanUserName()) && !TextUtils.isEmpty(this.this$0.getWuHanPassWord()) && (observableEmitter = this.$e) != null) {
            observableEmitter.onNext(true);
        }
    }

    public String getMsgContent(String str) {
        Intrinsics.checkNotNullParameter(str, "msgType");
        String access$getTAG$p = this.this$0.TAG;
        MyLogUtils.Logd(access$getTAG$p, "SrosManager getMsgContent:msgType=" + str);
        if (Intrinsics.areEqual((Object) str, (Object) NetConstant.MSG_SROS_GET_TOKEN)) {
            return this.this$0.getToken();
        }
        return null;
    }
}
