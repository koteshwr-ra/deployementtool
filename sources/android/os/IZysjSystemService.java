package android.os;

import java.util.Map;

public interface IZysjSystemService extends IInterface {
    int ZYCancelSaveWifi(String str) throws RemoteException;

    int ZYCloseAp() throws RemoteException;

    int ZYCreateSoftAp(String str) throws RemoteException;

    int ZYInstallSecure(boolean z) throws RemoteException;

    int ZYOpenSoftAp() throws RemoteException;

    int ZYRebootSys() throws RemoteException;

    String ZYShotScreen(String str, String str2, int i) throws RemoteException;

    int ZYShutdownSys() throws RemoteException;

    int ZYSilentInstallation(String str, String str2) throws RemoteException;

    int ZYSystemBar(int i) throws RemoteException;

    int ZYUpateBootLogo(String str) throws RemoteException;

    int ZYbackageLightControl(boolean z) throws RemoteException;

    int ZYgetBackLight() throws RemoteException;

    String ZYgetDeviceKernelInfo() throws RemoteException;

    String ZYgetEthDns1() throws RemoteException;

    String ZYgetEthDns2() throws RemoteException;

    String ZYgetEthGatWay() throws RemoteException;

    String ZYgetEthIp() throws RemoteException;

    String ZYgetEthMacAddress() throws RemoteException;

    String ZYgetEthNetMask() throws RemoteException;

    int ZYgetScreenDirection() throws RemoteException;

    String ZYgetSerialNo() throws RemoteException;

    String ZYgetUserData() throws RemoteException;

    String ZYgetWifiDns1() throws RemoteException;

    String ZYgetWifiDns2() throws RemoteException;

    String ZYgetWifiGatWay() throws RemoteException;

    String ZYgetWifiIp() throws RemoteException;

    String ZYgetWifiMacAddress() throws RemoteException;

    String ZYgetWifiNetMask() throws RemoteException;

    boolean ZYhdmiScreenControl(boolean z) throws RemoteException;

    int ZYresetSys() throws RemoteException;

    void ZYsetAdbWiress(boolean z) throws RemoteException;

    int ZYsetBackLight(int i) throws RemoteException;

    int ZYsetEthTurnOff() throws RemoteException;

    int ZYsetEthTurnOn() throws RemoteException;

    boolean ZYsetEthernetEnabled(boolean z) throws RemoteException;

    void ZYsetEthernetParams(int i, Map map) throws RemoteException;

    int ZYsetScreenDirection(int i) throws RemoteException;

    int ZYsetSerialNo(String str) throws RemoteException;

    boolean ZYsetSysTime(String str) throws RemoteException;

    boolean ZYsetTimeZone(String str) throws RemoteException;

    int ZYsetUserData(String str) throws RemoteException;

    void ZYsetWifiParams(int i, Map map) throws RemoteException;

    int get_zysj_gpio_value(int i) throws RemoteException;

    int setKeyCode(int i) throws RemoteException;

    int setTimeToRtc(int i, String str, String str2) throws RemoteException;

    int set_zysj_gpio_value(int i, int i2) throws RemoteException;

    int watchDogEnable(int i, int i2) throws RemoteException;

    int watchDogFeed() throws RemoteException;

    public static abstract class Stub extends Binder implements IZysjSystemService {
        private static final String DESCRIPTOR = "android.os.IZysjSystemService";
        static final int TRANSACTION_ZYCancelSaveWifi = 42;
        static final int TRANSACTION_ZYCloseAp = 45;
        static final int TRANSACTION_ZYCreateSoftAp = 43;
        static final int TRANSACTION_ZYInstallSecure = 40;
        static final int TRANSACTION_ZYOpenSoftAp = 44;
        static final int TRANSACTION_ZYRebootSys = 4;
        static final int TRANSACTION_ZYShotScreen = 7;
        static final int TRANSACTION_ZYShutdownSys = 5;
        static final int TRANSACTION_ZYSilentInstallation = 35;
        static final int TRANSACTION_ZYSystemBar = 6;
        static final int TRANSACTION_ZYUpateBootLogo = 41;
        static final int TRANSACTION_ZYbackageLightControl = 17;
        static final int TRANSACTION_ZYgetBackLight = 9;
        static final int TRANSACTION_ZYgetDeviceKernelInfo = 36;
        static final int TRANSACTION_ZYgetEthDns1 = 31;
        static final int TRANSACTION_ZYgetEthDns2 = 32;
        static final int TRANSACTION_ZYgetEthGatWay = 29;
        static final int TRANSACTION_ZYgetEthIp = 27;
        static final int TRANSACTION_ZYgetEthMacAddress = 28;
        static final int TRANSACTION_ZYgetEthNetMask = 30;
        static final int TRANSACTION_ZYgetScreenDirection = 11;
        static final int TRANSACTION_ZYgetSerialNo = 47;
        static final int TRANSACTION_ZYgetUserData = 49;
        static final int TRANSACTION_ZYgetWifiDns1 = 25;
        static final int TRANSACTION_ZYgetWifiDns2 = 26;
        static final int TRANSACTION_ZYgetWifiGatWay = 23;
        static final int TRANSACTION_ZYgetWifiIp = 21;
        static final int TRANSACTION_ZYgetWifiMacAddress = 22;
        static final int TRANSACTION_ZYgetWifiNetMask = 24;
        static final int TRANSACTION_ZYhdmiScreenControl = 16;
        static final int TRANSACTION_ZYresetSys = 10;
        static final int TRANSACTION_ZYsetAdbWiress = 15;
        static final int TRANSACTION_ZYsetBackLight = 8;
        static final int TRANSACTION_ZYsetEthTurnOff = 33;
        static final int TRANSACTION_ZYsetEthTurnOn = 34;
        static final int TRANSACTION_ZYsetEthernetEnabled = 18;
        static final int TRANSACTION_ZYsetEthernetParams = 19;
        static final int TRANSACTION_ZYsetScreenDirection = 12;
        static final int TRANSACTION_ZYsetSerialNo = 46;
        static final int TRANSACTION_ZYsetSysTime = 13;
        static final int TRANSACTION_ZYsetTimeZone = 14;
        static final int TRANSACTION_ZYsetUserData = 48;
        static final int TRANSACTION_ZYsetWifiParams = 20;
        static final int TRANSACTION_get_zysj_gpio_value = 1;
        static final int TRANSACTION_setKeyCode = 39;
        static final int TRANSACTION_setTimeToRtc = 3;
        static final int TRANSACTION_set_zysj_gpio_value = 2;
        static final int TRANSACTION_watchDogEnable = 38;
        static final int TRANSACTION_watchDogFeed = 37;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IZysjSystemService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IZysjSystemService)) {
                return new Proxy(iBinder);
            }
            return (IZysjSystemService) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                boolean z = false;
                switch (i) {
                    case 1:
                        parcel.enforceInterface(DESCRIPTOR);
                        int i3 = get_zysj_gpio_value(parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeInt(i3);
                        return true;
                    case 2:
                        parcel.enforceInterface(DESCRIPTOR);
                        int i4 = set_zysj_gpio_value(parcel.readInt(), parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeInt(i4);
                        return true;
                    case 3:
                        parcel.enforceInterface(DESCRIPTOR);
                        int timeToRtc = setTimeToRtc(parcel.readInt(), parcel.readString(), parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(timeToRtc);
                        return true;
                    case 4:
                        parcel.enforceInterface(DESCRIPTOR);
                        int ZYRebootSys = ZYRebootSys();
                        parcel2.writeNoException();
                        parcel2.writeInt(ZYRebootSys);
                        return true;
                    case 5:
                        parcel.enforceInterface(DESCRIPTOR);
                        int ZYShutdownSys = ZYShutdownSys();
                        parcel2.writeNoException();
                        parcel2.writeInt(ZYShutdownSys);
                        return true;
                    case 6:
                        parcel.enforceInterface(DESCRIPTOR);
                        int ZYSystemBar = ZYSystemBar(parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeInt(ZYSystemBar);
                        return true;
                    case 7:
                        parcel.enforceInterface(DESCRIPTOR);
                        String ZYShotScreen = ZYShotScreen(parcel.readString(), parcel.readString(), parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeString(ZYShotScreen);
                        return true;
                    case 8:
                        parcel.enforceInterface(DESCRIPTOR);
                        int ZYsetBackLight = ZYsetBackLight(parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeInt(ZYsetBackLight);
                        return true;
                    case 9:
                        parcel.enforceInterface(DESCRIPTOR);
                        int ZYgetBackLight = ZYgetBackLight();
                        parcel2.writeNoException();
                        parcel2.writeInt(ZYgetBackLight);
                        return true;
                    case 10:
                        parcel.enforceInterface(DESCRIPTOR);
                        int ZYresetSys = ZYresetSys();
                        parcel2.writeNoException();
                        parcel2.writeInt(ZYresetSys);
                        return true;
                    case 11:
                        parcel.enforceInterface(DESCRIPTOR);
                        int ZYgetScreenDirection = ZYgetScreenDirection();
                        parcel2.writeNoException();
                        parcel2.writeInt(ZYgetScreenDirection);
                        return true;
                    case 12:
                        parcel.enforceInterface(DESCRIPTOR);
                        int ZYsetScreenDirection = ZYsetScreenDirection(parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeInt(ZYsetScreenDirection);
                        return true;
                    case 13:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean ZYsetSysTime = ZYsetSysTime(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(ZYsetSysTime ? 1 : 0);
                        return true;
                    case 14:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean ZYsetTimeZone = ZYsetTimeZone(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(ZYsetTimeZone ? 1 : 0);
                        return true;
                    case 15:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        ZYsetAdbWiress(z);
                        parcel2.writeNoException();
                        return true;
                    case 16:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        boolean ZYhdmiScreenControl = ZYhdmiScreenControl(z);
                        parcel2.writeNoException();
                        parcel2.writeInt(ZYhdmiScreenControl ? 1 : 0);
                        return true;
                    case 17:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        int ZYbackageLightControl = ZYbackageLightControl(z);
                        parcel2.writeNoException();
                        parcel2.writeInt(ZYbackageLightControl);
                        return true;
                    case 18:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        boolean ZYsetEthernetEnabled = ZYsetEthernetEnabled(z);
                        parcel2.writeNoException();
                        parcel2.writeInt(ZYsetEthernetEnabled ? 1 : 0);
                        return true;
                    case 19:
                        parcel.enforceInterface(DESCRIPTOR);
                        ZYsetEthernetParams(parcel.readInt(), parcel.readHashMap(getClass().getClassLoader()));
                        parcel2.writeNoException();
                        return true;
                    case 20:
                        parcel.enforceInterface(DESCRIPTOR);
                        ZYsetWifiParams(parcel.readInt(), parcel.readHashMap(getClass().getClassLoader()));
                        parcel2.writeNoException();
                        return true;
                    case 21:
                        parcel.enforceInterface(DESCRIPTOR);
                        String ZYgetWifiIp = ZYgetWifiIp();
                        parcel2.writeNoException();
                        parcel2.writeString(ZYgetWifiIp);
                        return true;
                    case 22:
                        parcel.enforceInterface(DESCRIPTOR);
                        String ZYgetWifiMacAddress = ZYgetWifiMacAddress();
                        parcel2.writeNoException();
                        parcel2.writeString(ZYgetWifiMacAddress);
                        return true;
                    case 23:
                        parcel.enforceInterface(DESCRIPTOR);
                        String ZYgetWifiGatWay = ZYgetWifiGatWay();
                        parcel2.writeNoException();
                        parcel2.writeString(ZYgetWifiGatWay);
                        return true;
                    case 24:
                        parcel.enforceInterface(DESCRIPTOR);
                        String ZYgetWifiNetMask = ZYgetWifiNetMask();
                        parcel2.writeNoException();
                        parcel2.writeString(ZYgetWifiNetMask);
                        return true;
                    case 25:
                        parcel.enforceInterface(DESCRIPTOR);
                        String ZYgetWifiDns1 = ZYgetWifiDns1();
                        parcel2.writeNoException();
                        parcel2.writeString(ZYgetWifiDns1);
                        return true;
                    case 26:
                        parcel.enforceInterface(DESCRIPTOR);
                        String ZYgetWifiDns2 = ZYgetWifiDns2();
                        parcel2.writeNoException();
                        parcel2.writeString(ZYgetWifiDns2);
                        return true;
                    case 27:
                        parcel.enforceInterface(DESCRIPTOR);
                        String ZYgetEthIp = ZYgetEthIp();
                        parcel2.writeNoException();
                        parcel2.writeString(ZYgetEthIp);
                        return true;
                    case 28:
                        parcel.enforceInterface(DESCRIPTOR);
                        String ZYgetEthMacAddress = ZYgetEthMacAddress();
                        parcel2.writeNoException();
                        parcel2.writeString(ZYgetEthMacAddress);
                        return true;
                    case 29:
                        parcel.enforceInterface(DESCRIPTOR);
                        String ZYgetEthGatWay = ZYgetEthGatWay();
                        parcel2.writeNoException();
                        parcel2.writeString(ZYgetEthGatWay);
                        return true;
                    case 30:
                        parcel.enforceInterface(DESCRIPTOR);
                        String ZYgetEthNetMask = ZYgetEthNetMask();
                        parcel2.writeNoException();
                        parcel2.writeString(ZYgetEthNetMask);
                        return true;
                    case 31:
                        parcel.enforceInterface(DESCRIPTOR);
                        String ZYgetEthDns1 = ZYgetEthDns1();
                        parcel2.writeNoException();
                        parcel2.writeString(ZYgetEthDns1);
                        return true;
                    case 32:
                        parcel.enforceInterface(DESCRIPTOR);
                        String ZYgetEthDns2 = ZYgetEthDns2();
                        parcel2.writeNoException();
                        parcel2.writeString(ZYgetEthDns2);
                        return true;
                    case 33:
                        parcel.enforceInterface(DESCRIPTOR);
                        int ZYsetEthTurnOff = ZYsetEthTurnOff();
                        parcel2.writeNoException();
                        parcel2.writeInt(ZYsetEthTurnOff);
                        return true;
                    case 34:
                        parcel.enforceInterface(DESCRIPTOR);
                        int ZYsetEthTurnOn = ZYsetEthTurnOn();
                        parcel2.writeNoException();
                        parcel2.writeInt(ZYsetEthTurnOn);
                        return true;
                    case 35:
                        parcel.enforceInterface(DESCRIPTOR);
                        int ZYSilentInstallation = ZYSilentInstallation(parcel.readString(), parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(ZYSilentInstallation);
                        return true;
                    case 36:
                        parcel.enforceInterface(DESCRIPTOR);
                        String ZYgetDeviceKernelInfo = ZYgetDeviceKernelInfo();
                        parcel2.writeNoException();
                        parcel2.writeString(ZYgetDeviceKernelInfo);
                        return true;
                    case 37:
                        parcel.enforceInterface(DESCRIPTOR);
                        int watchDogFeed = watchDogFeed();
                        parcel2.writeNoException();
                        parcel2.writeInt(watchDogFeed);
                        return true;
                    case 38:
                        parcel.enforceInterface(DESCRIPTOR);
                        int watchDogEnable = watchDogEnable(parcel.readInt(), parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeInt(watchDogEnable);
                        return true;
                    case 39:
                        parcel.enforceInterface(DESCRIPTOR);
                        int keyCode = setKeyCode(parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeInt(keyCode);
                        return true;
                    case 40:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        int ZYInstallSecure = ZYInstallSecure(z);
                        parcel2.writeNoException();
                        parcel2.writeInt(ZYInstallSecure);
                        return true;
                    case 41:
                        parcel.enforceInterface(DESCRIPTOR);
                        int ZYUpateBootLogo = ZYUpateBootLogo(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(ZYUpateBootLogo);
                        return true;
                    case 42:
                        parcel.enforceInterface(DESCRIPTOR);
                        int ZYCancelSaveWifi = ZYCancelSaveWifi(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(ZYCancelSaveWifi);
                        return true;
                    case 43:
                        parcel.enforceInterface(DESCRIPTOR);
                        int ZYCreateSoftAp = ZYCreateSoftAp(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(ZYCreateSoftAp);
                        return true;
                    case 44:
                        parcel.enforceInterface(DESCRIPTOR);
                        int ZYOpenSoftAp = ZYOpenSoftAp();
                        parcel2.writeNoException();
                        parcel2.writeInt(ZYOpenSoftAp);
                        return true;
                    case 45:
                        parcel.enforceInterface(DESCRIPTOR);
                        int ZYCloseAp = ZYCloseAp();
                        parcel2.writeNoException();
                        parcel2.writeInt(ZYCloseAp);
                        return true;
                    case 46:
                        parcel.enforceInterface(DESCRIPTOR);
                        int ZYsetSerialNo = ZYsetSerialNo(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(ZYsetSerialNo);
                        return true;
                    case 47:
                        parcel.enforceInterface(DESCRIPTOR);
                        String ZYgetSerialNo = ZYgetSerialNo();
                        parcel2.writeNoException();
                        parcel2.writeString(ZYgetSerialNo);
                        return true;
                    case 48:
                        parcel.enforceInterface(DESCRIPTOR);
                        int ZYsetUserData = ZYsetUserData(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(ZYsetUserData);
                        return true;
                    case 49:
                        parcel.enforceInterface(DESCRIPTOR);
                        String ZYgetUserData = ZYgetUserData();
                        parcel2.writeNoException();
                        parcel2.writeString(ZYgetUserData);
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }

        private static class Proxy implements IZysjSystemService {
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public int get_zysj_gpio_value(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int set_zysj_gpio_value(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int setTimeToRtc(int i, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int ZYRebootSys() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int ZYShutdownSys() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int ZYSystemBar(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String ZYShotScreen(String str, String str2, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int ZYsetBackLight(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int ZYgetBackLight() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int ZYresetSys() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int ZYgetScreenDirection() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int ZYsetScreenDirection(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean ZYsetSysTime(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    boolean z = false;
                    this.mRemote.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean ZYsetTimeZone(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    boolean z = false;
                    this.mRemote.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void ZYsetAdbWiress(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean ZYhdmiScreenControl(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z2 = true;
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z2 = false;
                    }
                    return z2;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int ZYbackageLightControl(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean ZYsetEthernetEnabled(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z2 = true;
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z2 = false;
                    }
                    return z2;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void ZYsetEthernetParams(int i, Map map) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeMap(map);
                    this.mRemote.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void ZYsetWifiParams(int i, Map map) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeMap(map);
                    this.mRemote.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String ZYgetWifiIp() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String ZYgetWifiMacAddress() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String ZYgetWifiGatWay() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String ZYgetWifiNetMask() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String ZYgetWifiDns1() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String ZYgetWifiDns2() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String ZYgetEthIp() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String ZYgetEthMacAddress() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(28, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String ZYgetEthGatWay() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(29, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String ZYgetEthNetMask() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(30, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String ZYgetEthDns1() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(31, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String ZYgetEthDns2() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(32, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int ZYsetEthTurnOff() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(33, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int ZYsetEthTurnOn() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(34, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int ZYSilentInstallation(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(35, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String ZYgetDeviceKernelInfo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(36, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int watchDogFeed() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(37, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int watchDogEnable(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(38, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int setKeyCode(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(39, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int ZYInstallSecure(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(40, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int ZYUpateBootLogo(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(41, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int ZYCancelSaveWifi(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(42, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int ZYCreateSoftAp(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(43, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int ZYOpenSoftAp() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(44, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int ZYCloseAp() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(45, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int ZYsetSerialNo(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(46, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String ZYgetSerialNo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(47, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int ZYsetUserData(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(48, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String ZYgetUserData() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(49, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
