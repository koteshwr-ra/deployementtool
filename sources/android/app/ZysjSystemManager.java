package android.app;

import android.content.Context;
import android.os.IZysjSystemService;
import android.os.RemoteException;
import android.util.Log;
import java.util.Map;

public class ZysjSystemManager {
    public static final String STATIC_DNS1 = "dns1";
    public static final String STATIC_DNS2 = "dns2";
    public static final String STATIC_GATEWAY = "gateway";
    public static final String STATIC_IP = "ip";
    public static final String STATIC_NETMASK = "netmask";
    private static final String TAG_ZMS = "ZysjSystemService";
    IZysjSystemService mService;

    public ZysjSystemManager(Context context, IZysjSystemService iZysjSystemService) {
        this.mService = iZysjSystemService;
    }

    public int get_zysj_gpio_value(int i) {
        try {
            return this.mService.get_zysj_gpio_value(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "get_zysj_gpio_value RemoteException: " + e.getMessage());
            return -1;
        }
    }

    public int set_zysj_gpio_value(int i, int i2) {
        try {
            return this.mService.set_zysj_gpio_value(i, i2);
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "set_zysj_gpio_value RemoteException: " + e.getMessage());
            return -1;
        }
    }

    public int setTimeToRtc(int i, String str, String str2) {
        try {
            return this.mService.setTimeToRtc(i, str, str2);
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "setTimeToRtc RemoteException: " + e.getMessage());
            return -1;
        }
    }

    public int ZYRebootSys() {
        Log.i(TAG_ZMS, "User Control ZYRebootSys To Reboot System !!! ");
        try {
            return this.mService.ZYRebootSys();
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "ZYRebootSys RemoteException: " + e.getMessage());
            return -1;
        }
    }

    public int ZYShutdownSys() {
        Log.i(TAG_ZMS, "User Control ZYShutdownSys To Shutdown System !!! ");
        try {
            return this.mService.ZYShutdownSys();
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "ZYShutdownSys RemoteException: " + e.getMessage());
            return -1;
        }
    }

    public int ZYSystemBar(int i) {
        Log.i(TAG_ZMS, "User Control ZYSystemBar Value: " + i);
        try {
            return this.mService.ZYSystemBar(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "ZYShutdownSys RemoteException: " + e.getMessage());
            return -1;
        }
    }

    public String ZYShotScreen(String str, String str2, int i) {
        Log.i(TAG_ZMS, "User Control ZYShotScreen To Cut Screem !!! ");
        try {
            return this.mService.ZYShotScreen(str, str2, i);
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "ZYShutdownSys RemoteException: " + e.getMessage());
            return null;
        }
    }

    public int ZYgetBackLight() {
        Log.i(TAG_ZMS, "User Control ZYgetBackLight From Interface. !!! ");
        try {
            return this.mService.ZYgetBackLight();
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "ZYSgetBackLight RemoteException: " + e.getMessage());
            return -1;
        }
    }

    public int ZYsetBackLight(int i) {
        Log.i(TAG_ZMS, "User Control ZYsetBackLight From Interface. !!! ");
        try {
            return this.mService.ZYsetBackLight(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "ZYSgetBackLight RemoteException: " + e.getMessage());
            return -1;
        }
    }

    public int ZYresetSys() {
        Log.i(TAG_ZMS, "User Control ZYresetSys From Interface. !!! ");
        try {
            return this.mService.ZYresetSys();
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "ZYresetSys RemoteException: " + e.getMessage());
            return -1;
        }
    }

    public int ZYgetScreenDirection() {
        try {
            return this.mService.ZYgetScreenDirection();
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "ZYgetScreenDirection RemoteException: " + e.getMessage());
            return -1;
        }
    }

    public int ZYsetScreenDirection(int i) {
        try {
            return this.mService.ZYsetScreenDirection(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "ZYsetScreenDirection RemoteException: " + e.getMessage());
            return -1;
        }
    }

    public boolean ZYsetSysTime(String str) {
        try {
            return this.mService.ZYsetSysTime(str);
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "ZYsetSysTime RemoteException: " + e.getMessage());
            return false;
        }
    }

    public boolean ZYsetTimeZone(String str) {
        try {
            return this.mService.ZYsetTimeZone(str);
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "ZYsetTimeZone RemoteException: " + e.getMessage());
            return false;
        }
    }

    public void ZYsetAdbWiress(boolean z) {
        try {
            this.mService.ZYsetAdbWiress(z);
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "ZYsetAdbWiress RemoteException: " + e.getMessage());
        }
    }

    public boolean ZYhdmiScreenControl(boolean z) {
        try {
            return this.mService.ZYhdmiScreenControl(z);
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "ZYsetTimeZone RemoteException: " + e.getMessage());
            return false;
        }
    }

    public int ZYbackageLightControl(boolean z) {
        try {
            return this.mService.ZYbackageLightControl(z);
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "ZYsetTimeZone RemoteException: " + e.getMessage());
            return -1;
        }
    }

    public boolean ZYsetEthernetEnabled(boolean z) {
        try {
            return this.mService.ZYsetEthernetEnabled(z);
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "ZYsetEthernetEnabled RemoteException: " + e.getMessage());
            return false;
        }
    }

    public void ZYsetEthernetParams(int i, Map map) {
        try {
            this.mService.ZYsetEthernetParams(i, map);
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "ZYsetEthernetParams RemoteException: " + e.getMessage());
        }
    }

    public void ZYsetWifiParams(int i, Map map) {
        try {
            this.mService.ZYsetWifiParams(i, map);
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "ZYsetWifiParams RemoteException: " + e.getMessage());
        }
    }

    public String ZYgetWifiIp() {
        try {
            return this.mService.ZYgetWifiIp();
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "ZYgetWifiIp RemoteException: " + e.getMessage());
            return null;
        }
    }

    public String ZYgetWifiGatWay() {
        try {
            return this.mService.ZYgetWifiGatWay();
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "ZYgetWifiGatWay RemoteException: " + e.getMessage());
            return null;
        }
    }

    public String ZYgetWifiNetMask() {
        try {
            return this.mService.ZYgetWifiNetMask();
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "ZYgetWifiNetMask RemoteException: " + e.getMessage());
            return null;
        }
    }

    public String ZYgetWifiDns1() {
        try {
            return this.mService.ZYgetWifiDns1();
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "ZYgetWifiDns1 RemoteException: " + e.getMessage());
            return null;
        }
    }

    public String ZYgetWifiDns2() {
        try {
            return this.mService.ZYgetWifiDns2();
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "ZYgetWifiDns2 RemoteException: " + e.getMessage());
            return null;
        }
    }

    public String ZYgetWifiMacAddress() {
        try {
            return this.mService.ZYgetWifiMacAddress();
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "ZYgetWifiMacAddress RemoteException: " + e.getMessage());
            return null;
        }
    }

    public String ZYgetEthIp() {
        try {
            return this.mService.ZYgetEthIp();
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "ZYgetEthIp RemoteException: " + e.getMessage());
            return null;
        }
    }

    public String ZYgetEthGatWay() {
        try {
            return this.mService.ZYgetEthGatWay();
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "ZYgetEthGatWay RemoteException: " + e.getMessage());
            return null;
        }
    }

    public String ZYgetEthNetMask() {
        try {
            return this.mService.ZYgetEthNetMask();
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "ZYgetEthNetMask RemoteException: " + e.getMessage());
            return null;
        }
    }

    public String ZYgetEthDns1() {
        try {
            return this.mService.ZYgetEthDns1();
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "ZYgetEthDns1 RemoteException: " + e.getMessage());
            return null;
        }
    }

    public String ZYgetEthDns2() {
        try {
            return this.mService.ZYgetEthDns2();
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "ZYgetEthDns2 RemoteException: " + e.getMessage());
            return null;
        }
    }

    public String ZYgetEthMacAddress() {
        try {
            return this.mService.ZYgetEthMacAddress();
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "ZYgetEthMacAddress RemoteException: " + e.getMessage());
            return null;
        }
    }

    public int ZYSilentInstallation(String str, String str2) {
        try {
            return this.mService.ZYSilentInstallation(str, str2);
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "ZYShutdownSys RemoteException: " + e.getMessage());
            return -1;
        }
    }

    public String ZYgetDeviceKernelInfo() {
        try {
            return this.mService.ZYgetDeviceKernelInfo();
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "ZYgetDeviceKernelInfo RemoteException: " + e.getMessage());
            return null;
        }
    }

    public int watchDogFeed() {
        try {
            return this.mService.watchDogFeed();
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "watchDogFeed RemoteException: " + e.getMessage());
            return -1;
        }
    }

    public int watchDogEnable(int i, int i2) {
        try {
            return this.mService.watchDogEnable(i, i2);
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "watchDogEnable RemoteException: " + e.getMessage());
            return -1;
        }
    }

    public int setKeyCode(int i) {
        try {
            return this.mService.setKeyCode(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "setKeyCode RemoteException: " + e.getMessage());
            return -1;
        }
    }

    public int ZYInstallSecure(boolean z) {
        try {
            return this.mService.ZYInstallSecure(z);
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "ZYInstallSecure RemoteException: " + e.getMessage());
            return -1;
        }
    }

    public int ZYUpateBootLogo(String str) {
        try {
            return this.mService.ZYUpateBootLogo(str);
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "ZYUpateBootLogo RemoteException: " + e.getMessage());
            return -1;
        }
    }

    public int ZYCancelSaveWifi(String str) {
        try {
            return this.mService.ZYCancelSaveWifi(str);
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "ZYCancelSaveWifi RemoteException: " + e.getMessage());
            return -1;
        }
    }

    public int ZYCreateSoftAp(String str) {
        try {
            return this.mService.ZYCreateSoftAp(str);
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "ZYCreateSoftAp RemoteException: " + e.getMessage());
            return -1;
        }
    }

    public int ZYOpenSoftAp() {
        try {
            return this.mService.ZYOpenSoftAp();
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "ZYOpenSoftAp RemoteException: " + e.getMessage());
            return -1;
        }
    }

    public int ZYCloseAp() {
        try {
            return this.mService.ZYCloseAp();
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "ZYCloseAp RemoteException: " + e.getMessage());
            return -1;
        }
    }

    public int ZYsetSerialNo(String str) {
        try {
            return this.mService.ZYsetSerialNo(str);
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "ZYsetSerialNo RemoteException: " + e.getMessage());
            return -1;
        }
    }

    public String ZYgetSerialNo() {
        try {
            return this.mService.ZYgetSerialNo();
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "ZYgetSerialNo RemoteException: " + e.getMessage());
            return "";
        }
    }

    public int ZYsetUserData(String str) {
        try {
            return this.mService.ZYsetUserData(str);
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "ZYsetUserData RemoteException: " + e.getMessage());
            return -1;
        }
    }

    public String ZYgetUserData() {
        try {
            return this.mService.ZYgetUserData();
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG_ZMS, "ZYgetUserData RemoteException: " + e.getMessage());
            return "";
        }
    }
}
