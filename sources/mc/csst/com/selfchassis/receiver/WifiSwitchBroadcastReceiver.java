package mc.csst.com.selfchassis.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ShellUtils;
import com.ciot.base.storage.MySpUtils;
import com.ciot.base.util.MyLogUtils;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mc.csst.com.selfchassis.utils.SpConstant;
import mc.csst.com.selfchassis.utils.constant.DeploymentToolConstant;
import mc.csst.com.selfchassislibrary.chassis.SelfChassis;

public class WifiSwitchBroadcastReceiver {
    /* access modifiers changed from: private */
    public static final String TAG = WifiSwitchBroadcastReceiver.class.getSimpleName();
    /* access modifiers changed from: private */
    public static volatile String mCurSsidName = "";
    private static int recordNum;
    /* access modifiers changed from: private */
    public static volatile int wifiState;
    /* access modifiers changed from: private */
    public boolean isConnect;
    private Context mContext;
    /* access modifiers changed from: private */
    public Disposable mDisposable;
    /* access modifiers changed from: private */
    public WifiSwitchInterface mInterface;
    private Receiver receiver;

    public interface WifiSwitchInterface {
        public static final int WIFI_STATE_CONNECTED = 1;
        public static final int WIFI_STATE_DISCONNECTED = 0;

        void wifiSwitchState(int i);
    }

    public WifiSwitchBroadcastReceiver(Context context, WifiSwitchInterface wifiSwitchInterface) {
        this.mContext = context;
        this.mInterface = wifiSwitchInterface;
    }

    public void observeWifiSwitch() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        Receiver receiver2 = new Receiver();
        this.receiver = receiver2;
        this.mContext.registerReceiver(receiver2, intentFilter);
    }

    public void onDestroy() {
        cancel();
        Receiver receiver2 = this.receiver;
        if (receiver2 != null) {
            this.mContext.unregisterReceiver(receiver2);
        }
        if (this.mContext != null) {
            this.mContext = null;
        }
    }

    /* access modifiers changed from: private */
    public String getChassisIp() {
        return MySpUtils.getInstance().getString(SpConstant.CHASSIS_IP, DeploymentToolConstant.CHASSIS_IP);
    }

    class Receiver extends BroadcastReceiver {
        Receiver() {
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            String access$000 = WifiSwitchBroadcastReceiver.TAG;
            MyLogUtils.Logd(access$000, "onReceive-----------action" + action);
            if ("android.net.wifi.WIFI_STATE_CHANGED".equals(action)) {
                int unused = WifiSwitchBroadcastReceiver.wifiState = intent.getIntExtra("wifi_state", -1);
                if (WifiSwitchBroadcastReceiver.wifiState == 1) {
                    MyLogUtils.Logd(WifiSwitchBroadcastReceiver.TAG, "WIFI状态:wifiState:WIFI_STATE_DISABLED");
                    if (!TextUtils.equals(WifiSwitchBroadcastReceiver.this.getChassisIp(), DeploymentToolConstant.CHASSIS_DIRECT_IP)) {
                        if (WifiSwitchBroadcastReceiver.this.mInterface != null) {
                            WifiSwitchBroadcastReceiver.this.mInterface.wifiSwitchState(0);
                        }
                        WifiSwitchBroadcastReceiver.this.ping();
                    } else {
                        WifiSwitchBroadcastReceiver.this.ping();
                    }
                    boolean unused2 = WifiSwitchBroadcastReceiver.this.isConnect = false;
                } else if (WifiSwitchBroadcastReceiver.wifiState == 3) {
                    MyLogUtils.Logd(WifiSwitchBroadcastReceiver.TAG, "WIFI状态:wifiState:WIFI_STATE_ENABLED");
                    if (!TextUtils.equals(WifiSwitchBroadcastReceiver.this.getChassisIp(), DeploymentToolConstant.CHASSIS_DIRECT_IP)) {
                        if (WifiSwitchBroadcastReceiver.this.mInterface != null && !WifiSwitchBroadcastReceiver.this.isConnect) {
                            WifiSwitchBroadcastReceiver.this.mInterface.wifiSwitchState(1);
                        }
                        WifiSwitchBroadcastReceiver.this.ping();
                    } else {
                        WifiSwitchBroadcastReceiver.this.ping();
                    }
                    boolean unused3 = WifiSwitchBroadcastReceiver.this.isConnect = true;
                }
            } else if ("android.net.wifi.STATE_CHANGE".equals(action)) {
                String ssid = NetworkUtils.getSSID();
                String access$0002 = WifiSwitchBroadcastReceiver.TAG;
                MyLogUtils.Logd(access$0002, WifiSwitchBroadcastReceiver.wifiState + "WIFI状态:NetworkUtils.getSSID():" + ssid);
                if (!TextUtils.equals(WifiSwitchBroadcastReceiver.mCurSsidName, ssid)) {
                    MyLogUtils.Logd(WifiSwitchBroadcastReceiver.TAG, "wifi改变");
                    if (!TextUtils.equals(WifiSwitchBroadcastReceiver.this.getChassisIp(), DeploymentToolConstant.CHASSIS_DIRECT_IP)) {
                        if (WifiSwitchBroadcastReceiver.this.mInterface != null) {
                            if (WifiSwitchBroadcastReceiver.wifiState == 1) {
                                MyLogUtils.Logd(WifiSwitchBroadcastReceiver.TAG, "wifi未打开");
                                WifiSwitchBroadcastReceiver.this.mInterface.wifiSwitchState(0);
                            } else {
                                MyLogUtils.Logd(WifiSwitchBroadcastReceiver.TAG, "wifi发生变化");
                                SelfChassis.getInstance().disconnectSelfChassis();
                                WifiSwitchBroadcastReceiver.this.mInterface.wifiSwitchState(1);
                            }
                        }
                        WifiSwitchBroadcastReceiver.this.ping();
                    } else {
                        WifiSwitchBroadcastReceiver.this.ping();
                    }
                    boolean unused4 = WifiSwitchBroadcastReceiver.this.isConnect = true;
                }
                String unused5 = WifiSwitchBroadcastReceiver.mCurSsidName = ssid;
            }
        }
    }

    public static boolean isChassisAvailable() {
        for (int i = 0; i < 3; i++) {
            ShellUtils.CommandResult execCmd = ShellUtils.execCmd("ping -c 1 -W 1 192.168.20.22", false);
            String str = TAG;
            MyLogUtils.Logd(str, "commandResult.toString()===>" + execCmd.toString());
            if (execCmd.result == 0) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void ping() {
        if (TextUtils.equals(getChassisIp(), DeploymentToolConstant.CHASSIS_DIRECT_IP)) {
            recordNum++;
            MyLogUtils.Logd(TAG, "ping===========recordNum" + recordNum + " wifiState:" + wifiState);
            cancel();
            Observable.create($$Lambda$WifiSwitchBroadcastReceiver$XhU7dr3YMsAsDtsBMBSLFJkSkEE.INSTANCE).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Boolean>() {
                public void onComplete() {
                }

                public void onError(Throwable th) {
                }

                public void onSubscribe(Disposable disposable) {
                    Disposable unused = WifiSwitchBroadcastReceiver.this.mDisposable = disposable;
                }

                public void onNext(Boolean bool) {
                    String access$000 = WifiSwitchBroadcastReceiver.TAG;
                    MyLogUtils.Logd(access$000, "ping===========" + bool);
                    if ((!bool.booleanValue() || !SelfChassis.getInstance().isConnect()) && WifiSwitchBroadcastReceiver.this.mInterface != null) {
                        WifiSwitchBroadcastReceiver.this.mInterface.wifiSwitchState(1);
                    }
                }
            });
        }
    }

    static /* synthetic */ void lambda$ping$0(ObservableEmitter observableEmitter) throws Exception {
        observableEmitter.onNext(Boolean.valueOf(isChassisAvailable()));
        observableEmitter.onComplete();
    }

    private void cancel() {
        Disposable disposable = this.mDisposable;
        if (disposable != null && !disposable.isDisposed()) {
            this.mDisposable.dispose();
        }
    }
}
