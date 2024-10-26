// IZysjSystemService.aidl

package android.os;

interface IZysjSystemService {
	
	/* add by lrm, aidl interface support read/write gpio interface. */
	int get_zysj_gpio_value(int which);
	int set_zysj_gpio_value(int which, int value);
	
	/* add by lrm, aidl interface support power manger interface. */
	int setTimeToRtc(int cmd, String date, String time);
	int ZYRebootSys();
	int ZYShutdownSys();
	
	/*add by alvin, support for screen cut and system bar aidl interface.*/
	int ZYSystemBar(int cmd);
	String ZYShotScreen(String picPath, String fileName, int screenId);
	
	/*add by alvin, support for Backlight and ResetSystem aidl interface.*/
	int ZYsetBackLight(int backageLight);
	int ZYgetBackLight();
	int ZYresetSys();
	
	/*add by yfc, support for ScreenDirection and SystemTime aidl interface.*/
	int ZYgetScreenDirection();
	int ZYsetScreenDirection(int rotate);
	boolean ZYsetSysTime(String time);
	boolean ZYsetTimeZone(String timeZone);
	
	/*add by yfc, support for backageLight and wiress adb aidl interface.*/
	void ZYsetAdbWiress(boolean enable);
	boolean ZYhdmiScreenControl(boolean flag);
	int ZYbackageLightControl(boolean flag);
	
	/*add by yfc, support for network aidl interface.*/
	boolean ZYsetEthernetEnabled(boolean enabled);
	void ZYsetEthernetParams(int mode,in Map params);
	void ZYsetWifiParams(int mode,in Map params);
	String ZYgetWifiIp();
	String ZYgetWifiMacAddress();
	String ZYgetWifiGatWay();
	String ZYgetWifiNetMask();
	String ZYgetWifiDns1();
	String ZYgetWifiDns2();
	String ZYgetEthIp();
	String ZYgetEthMacAddress();
	String ZYgetEthGatWay();
	String ZYgetEthNetMask();
	String ZYgetEthDns1();
	String ZYgetEthDns2();
	int ZYsetEthTurnOff();
	int ZYsetEthTurnOn();
	
	/*add by yfc, support for install apk aidl interface.*/
	int ZYSilentInstallation(String apkPath, String apkName);
	
	/*add by XYP, support get device kernel info*/
	String ZYgetDeviceKernelInfo();
	int watchDogFeed();
	int watchDogEnable(int enable,int timeOut);
	int setKeyCode(int keyCode);
	
	
	int ZYInstallSecure(boolean enable);
	int ZYUpateBootLogo(String logoPath);
	int ZYCancelSaveWifi(String wifiSSID);
	int ZYCreateSoftAp(String softApName);
	int ZYOpenSoftAp();
	int ZYCloseAp();
	
	int ZYsetSerialNo(String serialno);
	String ZYgetSerialNo();
	
	int ZYsetUserData(String userData);
	String ZYgetUserData();
}