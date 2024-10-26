package com.ciot.networklib.info;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import androidx.core.app.ActivityCompat;
import com.tencent.smtt.sdk.TbsReaderView;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.ClassUtils;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/ciot/networklib/info/NetUtils;", "", "()V", "Companion", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: NetUtils.kt */
public final class NetUtils {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004J\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\t\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\n\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bJ\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004J\u000e\u0010\f\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\r\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u000e\u001a\u00020\u0004J\u000e\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004¨\u0006\u0011"}, d2 = {"Lcom/ciot/networklib/info/NetUtils$Companion;", "", "()V", "getEthernetMacAddress", "", "getHostIPAddress", "getSimImei", "context", "Landroid/content/Context;", "getSimSn", "getWifiIp", "getWifiMacAddr", "getWifiSsid", "loadFileAsString", "filePath", "ping", "ip", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: NetUtils.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getWifiSsid(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Object systemService = context.getSystemService("wifi");
            if (systemService != null) {
                WifiInfo connectionInfo = ((WifiManager) systemService).getConnectionInfo();
                Intrinsics.checkNotNullExpressionValue(connectionInfo, "wifiMan.connectionInfo");
                String ssid = connectionInfo.getSSID();
                Intrinsics.checkNotNullExpressionValue(ssid, "info.getSSID()");
                return ssid;
            }
            throw new NullPointerException("null cannot be cast to non-null type android.net.wifi.WifiManager");
        }

        public final String getWifiIp(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Object systemService = context.getSystemService("wifi");
            if (systemService != null) {
                WifiInfo connectionInfo = ((WifiManager) systemService).getConnectionInfo();
                Intrinsics.checkNotNullExpressionValue(connectionInfo, "wifiMan.connectionInfo");
                int ipAddress = connectionInfo.getIpAddress();
                if (ipAddress == 0) {
                    return "";
                }
                StringBuilder sb = new StringBuilder();
                sb.append(ipAddress & 255);
                sb.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
                sb.append((ipAddress >> 8) & 255);
                sb.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
                sb.append((ipAddress >> 16) & 255);
                sb.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
                sb.append((ipAddress >> 24) & 255);
                return sb.toString();
            }
            throw new NullPointerException("null cannot be cast to non-null type android.net.wifi.WifiManager");
        }

        /* JADX WARNING: Removed duplicated region for block: B:24:0x0046 A[SYNTHETIC, Splitter:B:24:0x0046] */
        /* JADX WARNING: Removed duplicated region for block: B:29:0x004e A[Catch:{ IOException -> 0x004a }] */
        /* JADX WARNING: Removed duplicated region for block: B:33:0x0059 A[SYNTHETIC, Splitter:B:33:0x0059] */
        /* JADX WARNING: Removed duplicated region for block: B:38:0x0061 A[Catch:{ IOException -> 0x005d }] */
        /* JADX WARNING: Removed duplicated region for block: B:43:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.String getEthernetMacAddress() {
            /*
                r6 = this;
                r0 = 0
                java.io.FileReader r1 = new java.io.FileReader     // Catch:{ Exception -> 0x003e, all -> 0x0039 }
                java.lang.String r2 = "sys/class/net/eth0/address"
                r1.<init>(r2)     // Catch:{ Exception -> 0x003e, all -> 0x0039 }
                java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0036, all -> 0x0031 }
                r3 = r1
                java.io.Reader r3 = (java.io.Reader) r3     // Catch:{ Exception -> 0x0036, all -> 0x0031 }
                r2.<init>(r3)     // Catch:{ Exception -> 0x0036, all -> 0x0031 }
                java.lang.String r3 = r2.readLine()     // Catch:{ Exception -> 0x002f }
                java.lang.String r4 = "reader.readLine()"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)     // Catch:{ Exception -> 0x002f }
                java.lang.String r3 = r3.toUpperCase()     // Catch:{ Exception -> 0x002f }
                java.lang.String r4 = "this as java.lang.String).toUpperCase()"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)     // Catch:{ Exception -> 0x002f }
                r2.close()     // Catch:{ IOException -> 0x0029 }
                r1.close()     // Catch:{ IOException -> 0x0029 }
                goto L_0x002d
            L_0x0029:
                r0 = move-exception
                r0.printStackTrace()
            L_0x002d:
                r0 = r3
                goto L_0x0055
            L_0x002f:
                r3 = move-exception
                goto L_0x0041
            L_0x0031:
                r2 = move-exception
                r5 = r2
                r2 = r0
                r0 = r5
                goto L_0x0057
            L_0x0036:
                r3 = move-exception
                r2 = r0
                goto L_0x0041
            L_0x0039:
                r1 = move-exception
                r2 = r0
                r0 = r1
                r1 = r2
                goto L_0x0057
            L_0x003e:
                r3 = move-exception
                r1 = r0
                r2 = r1
            L_0x0041:
                r3.printStackTrace()     // Catch:{ all -> 0x0056 }
                if (r2 == 0) goto L_0x004c
                r2.close()     // Catch:{ IOException -> 0x004a }
                goto L_0x004c
            L_0x004a:
                r1 = move-exception
                goto L_0x0052
            L_0x004c:
                if (r1 == 0) goto L_0x0055
                r1.close()     // Catch:{ IOException -> 0x004a }
                goto L_0x0055
            L_0x0052:
                r1.printStackTrace()
            L_0x0055:
                return r0
            L_0x0056:
                r0 = move-exception
            L_0x0057:
                if (r2 == 0) goto L_0x005f
                r2.close()     // Catch:{ IOException -> 0x005d }
                goto L_0x005f
            L_0x005d:
                r1 = move-exception
                goto L_0x0065
            L_0x005f:
                if (r1 == 0) goto L_0x0068
                r1.close()     // Catch:{ IOException -> 0x005d }
                goto L_0x0068
            L_0x0065:
                r1.printStackTrace()
            L_0x0068:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ciot.networklib.info.NetUtils.Companion.getEthernetMacAddress():java.lang.String");
        }

        public final String getHostIPAddress() {
            String str = null;
            try {
                Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
                while (networkInterfaces.hasMoreElements()) {
                    NetworkInterface nextElement = networkInterfaces.nextElement();
                    if (nextElement != null) {
                        Enumeration<InetAddress> inetAddresses = nextElement.getInetAddresses();
                        Intrinsics.checkNotNullExpressionValue(inetAddresses, "ni.getInetAddresses()");
                        while (true) {
                            if (!inetAddresses.hasMoreElements()) {
                                break;
                            }
                            InetAddress nextElement2 = inetAddresses.nextElement();
                            Intrinsics.checkNotNullExpressionValue(nextElement2, "ias.nextElement()");
                            InetAddress inetAddress = nextElement2;
                            if (!(inetAddress instanceof Inet6Address)) {
                                String hostAddress = inetAddress.getHostAddress();
                                Intrinsics.checkNotNullExpressionValue(hostAddress, "ia.getHostAddress()");
                                if (!Intrinsics.areEqual((Object) "127.0.0.1", (Object) hostAddress)) {
                                    str = hostAddress;
                                    break;
                                }
                            }
                        }
                    } else {
                        throw new NullPointerException("null cannot be cast to non-null type java.net.NetworkInterface");
                    }
                }
            } catch (SocketException e) {
                e.printStackTrace();
            }
            return str;
        }

        public final String getWifiMacAddr() {
            try {
                String loadFileAsString = loadFileAsString("/sys/class/net/wlan0/address");
                if (loadFileAsString != null) {
                    String upperCase = loadFileAsString.toUpperCase();
                    Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase()");
                    if (upperCase != null) {
                        String substring = upperCase.substring(0, 17);
                        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                        return substring;
                    }
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
        }

        public final String loadFileAsString(String str) {
            Intrinsics.checkNotNullParameter(str, TbsReaderView.KEY_FILE_PATH);
            StringBuffer stringBuffer = new StringBuffer(1000);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(str));
            char[] cArr = new char[1024];
            while (true) {
                int read = bufferedReader.read(cArr);
                if (read != -1) {
                    stringBuffer.append(new String(cArr, 0, read));
                } else {
                    bufferedReader.close();
                    return stringBuffer.toString();
                }
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:35:0x00da, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:0x00db, code lost:
            r1 = r0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:52:?, code lost:
            r2.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:53:0x00f7, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:54:0x00f8, code lost:
            r0.printStackTrace();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:57:?, code lost:
            r5.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:58:0x0102, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:59:0x0103, code lost:
            r0.printStackTrace();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:62:?, code lost:
            r6.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:63:0x010d, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:64:0x010e, code lost:
            r0.printStackTrace();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:70:?, code lost:
            r2.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:71:0x011b, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:72:0x011c, code lost:
            r0.printStackTrace();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:75:?, code lost:
            r5.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:76:0x0126, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:77:0x0127, code lost:
            r0.printStackTrace();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:80:?, code lost:
            r6.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:81:0x0131, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:82:0x0132, code lost:
            r0.printStackTrace();
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Removed duplicated region for block: B:35:0x00da A[ExcHandler: all (r0v10 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:8:0x0031] */
        /* JADX WARNING: Removed duplicated region for block: B:51:0x00f3 A[SYNTHETIC, Splitter:B:51:0x00f3] */
        /* JADX WARNING: Removed duplicated region for block: B:56:0x00fe A[SYNTHETIC, Splitter:B:56:0x00fe] */
        /* JADX WARNING: Removed duplicated region for block: B:61:0x0109 A[SYNTHETIC, Splitter:B:61:0x0109] */
        /* JADX WARNING: Removed duplicated region for block: B:69:0x0117 A[SYNTHETIC, Splitter:B:69:0x0117] */
        /* JADX WARNING: Removed duplicated region for block: B:74:0x0122 A[SYNTHETIC, Splitter:B:74:0x0122] */
        /* JADX WARNING: Removed duplicated region for block: B:79:0x012d A[SYNTHETIC, Splitter:B:79:0x012d] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.String ping(java.lang.String r22) {
            /*
                r21 = this;
                r0 = r22
                java.lang.String r1 = ""
                java.lang.String r2 = "ip"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r2)
                r2 = 0
                java.lang.Runtime r3 = java.lang.Runtime.getRuntime()     // Catch:{ Exception -> 0x0113, all -> 0x00ed }
                java.lang.String r4 = "su"
                java.lang.Process r3 = r3.exec(r4)     // Catch:{ Exception -> 0x0113, all -> 0x00ed }
                java.io.DataOutputStream r4 = new java.io.DataOutputStream     // Catch:{ Exception -> 0x0113, all -> 0x00ed }
                java.io.OutputStream r5 = r3.getOutputStream()     // Catch:{ Exception -> 0x0113, all -> 0x00ed }
                r4.<init>(r5)     // Catch:{ Exception -> 0x0113, all -> 0x00ed }
                java.io.DataInputStream r5 = new java.io.DataInputStream     // Catch:{ Exception -> 0x00e9, all -> 0x00e3 }
                java.io.InputStream r6 = r3.getInputStream()     // Catch:{ Exception -> 0x00e9, all -> 0x00e3 }
                r5.<init>(r6)     // Catch:{ Exception -> 0x00e9, all -> 0x00e3 }
                java.io.DataInputStream r6 = new java.io.DataInputStream     // Catch:{ Exception -> 0x00e1, all -> 0x00dd }
                java.io.InputStream r7 = r3.getErrorStream()     // Catch:{ Exception -> 0x00e1, all -> 0x00dd }
                r6.<init>(r7)     // Catch:{ Exception -> 0x00e1, all -> 0x00dd }
                java.lang.String r7 = "mount -o rw,remount /system \n"
                r4.writeBytes(r7)     // Catch:{ Exception -> 0x00eb, all -> 0x00da }
                r4.flush()     // Catch:{ Exception -> 0x00eb, all -> 0x00da }
                java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00eb, all -> 0x00da }
                r7.<init>()     // Catch:{ Exception -> 0x00eb, all -> 0x00da }
                java.lang.String r8 = "ping -c 4 "
                r7.append(r8)     // Catch:{ Exception -> 0x00eb, all -> 0x00da }
                r7.append(r0)     // Catch:{ Exception -> 0x00eb, all -> 0x00da }
                java.lang.String r0 = " \n"
                r7.append(r0)     // Catch:{ Exception -> 0x00eb, all -> 0x00da }
                java.lang.String r0 = r7.toString()     // Catch:{ Exception -> 0x00eb, all -> 0x00da }
                r4.writeBytes(r0)     // Catch:{ Exception -> 0x00eb, all -> 0x00da }
                r4.flush()     // Catch:{ Exception -> 0x00eb, all -> 0x00da }
                java.lang.String r0 = "exit\n"
                r4.writeBytes(r0)     // Catch:{ Exception -> 0x00eb, all -> 0x00da }
                r4.flush()     // Catch:{ Exception -> 0x00eb, all -> 0x00da }
                r7 = r1
            L_0x005c:
                java.lang.String r0 = r5.readLine()     // Catch:{ Exception -> 0x00d7, all -> 0x00da }
                if (r0 == 0) goto L_0x00a0
                kotlin.jvm.internal.Intrinsics.checkNotNull(r0)     // Catch:{ Exception -> 0x00d7, all -> 0x00da }
                r8 = r0
                java.lang.CharSequence r8 = (java.lang.CharSequence) r8     // Catch:{ Exception -> 0x00d7, all -> 0x00da }
                java.lang.String r9 = "avg"
                java.lang.CharSequence r9 = (java.lang.CharSequence) r9     // Catch:{ Exception -> 0x00d7, all -> 0x00da }
                r10 = 0
                r11 = 2
                boolean r8 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r8, (java.lang.CharSequence) r9, (boolean) r10, (int) r11, (java.lang.Object) r2)     // Catch:{ Exception -> 0x00d7, all -> 0x00da }
                if (r8 == 0) goto L_0x005c
                r9 = r0
                java.lang.CharSequence r9 = (java.lang.CharSequence) r9     // Catch:{ Exception -> 0x00d7, all -> 0x00da }
                java.lang.String r10 = "/"
                r11 = 20
                r12 = 0
                r13 = 4
                r14 = 0
                int r8 = kotlin.text.StringsKt.indexOf$default((java.lang.CharSequence) r9, (java.lang.String) r10, (int) r11, (boolean) r12, (int) r13, (java.lang.Object) r14)     // Catch:{ Exception -> 0x00d7, all -> 0x00da }
                r15 = r0
                java.lang.CharSequence r15 = (java.lang.CharSequence) r15     // Catch:{ Exception -> 0x00d7, all -> 0x00da }
                java.lang.String r16 = "."
                r18 = 0
                r19 = 4
                r20 = 0
                r17 = r8
                int r9 = kotlin.text.StringsKt.indexOf$default((java.lang.CharSequence) r15, (java.lang.String) r16, (int) r17, (boolean) r18, (int) r19, (java.lang.Object) r20)     // Catch:{ Exception -> 0x00d7, all -> 0x00da }
                int r8 = r8 + 1
                java.lang.String r0 = r0.substring(r8, r9)     // Catch:{ Exception -> 0x00d7, all -> 0x00da }
                java.lang.String r8 = "this as java.lang.String…ing(startIndex, endIndex)"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r8)     // Catch:{ Exception -> 0x00d7, all -> 0x00da }
                r7 = r0
                goto L_0x005c
            L_0x00a0:
                java.lang.String r0 = r6.readLine()     // Catch:{ Exception -> 0x00d7, all -> 0x00da }
                if (r0 == 0) goto L_0x00b6
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00d7, all -> 0x00da }
                r2.<init>()     // Catch:{ Exception -> 0x00d7, all -> 0x00da }
                r2.append(r1)     // Catch:{ Exception -> 0x00d7, all -> 0x00da }
                r2.append(r0)     // Catch:{ Exception -> 0x00d7, all -> 0x00da }
                java.lang.String r1 = r2.toString()     // Catch:{ Exception -> 0x00d7, all -> 0x00da }
                goto L_0x00a0
            L_0x00b6:
                r3.waitFor()     // Catch:{ Exception -> 0x00d7, all -> 0x00da }
                r4.close()     // Catch:{ IOException -> 0x00bd }
                goto L_0x00c2
            L_0x00bd:
                r0 = move-exception
                r1 = r0
                r1.printStackTrace()
            L_0x00c2:
                r5.close()     // Catch:{ IOException -> 0x00c6 }
                goto L_0x00cb
            L_0x00c6:
                r0 = move-exception
                r1 = r0
                r1.printStackTrace()
            L_0x00cb:
                r6.close()     // Catch:{ IOException -> 0x00d0 }
                goto L_0x0137
            L_0x00d0:
                r0 = move-exception
                r1 = r0
                r1.printStackTrace()
                goto L_0x0137
            L_0x00d7:
                r2 = r4
                r1 = r7
                goto L_0x0115
            L_0x00da:
                r0 = move-exception
                r1 = r0
                goto L_0x00e7
            L_0x00dd:
                r0 = move-exception
                r1 = r0
                r6 = r2
                goto L_0x00e7
            L_0x00e1:
                r6 = r2
                goto L_0x00eb
            L_0x00e3:
                r0 = move-exception
                r1 = r0
                r5 = r2
                r6 = r5
            L_0x00e7:
                r2 = r4
                goto L_0x00f1
            L_0x00e9:
                r5 = r2
                r6 = r5
            L_0x00eb:
                r2 = r4
                goto L_0x0115
            L_0x00ed:
                r0 = move-exception
                r1 = r0
                r5 = r2
                r6 = r5
            L_0x00f1:
                if (r2 == 0) goto L_0x00fc
                r2.close()     // Catch:{ IOException -> 0x00f7 }
                goto L_0x00fc
            L_0x00f7:
                r0 = move-exception
                r2 = r0
                r2.printStackTrace()
            L_0x00fc:
                if (r5 == 0) goto L_0x0107
                r5.close()     // Catch:{ IOException -> 0x0102 }
                goto L_0x0107
            L_0x0102:
                r0 = move-exception
                r2 = r0
                r2.printStackTrace()
            L_0x0107:
                if (r6 == 0) goto L_0x0112
                r6.close()     // Catch:{ IOException -> 0x010d }
                goto L_0x0112
            L_0x010d:
                r0 = move-exception
                r2 = r0
                r2.printStackTrace()
            L_0x0112:
                throw r1
            L_0x0113:
                r5 = r2
                r6 = r5
            L_0x0115:
                if (r2 == 0) goto L_0x0120
                r2.close()     // Catch:{ IOException -> 0x011b }
                goto L_0x0120
            L_0x011b:
                r0 = move-exception
                r2 = r0
                r2.printStackTrace()
            L_0x0120:
                if (r5 == 0) goto L_0x012b
                r5.close()     // Catch:{ IOException -> 0x0126 }
                goto L_0x012b
            L_0x0126:
                r0 = move-exception
                r2 = r0
                r2.printStackTrace()
            L_0x012b:
                if (r6 == 0) goto L_0x0136
                r6.close()     // Catch:{ IOException -> 0x0131 }
                goto L_0x0136
            L_0x0131:
                r0 = move-exception
                r2 = r0
                r2.printStackTrace()
            L_0x0136:
                r7 = r1
            L_0x0137:
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ciot.networklib.info.NetUtils.Companion.ping(java.lang.String):java.lang.String");
        }

        public final String getSimSn(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager == null) {
                return null;
            }
            return telephonyManager.getSimSerialNumber();
        }

        public final String getSimImei(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager == null) {
                return null;
            }
            if (Build.VERSION.SDK_INT < 21) {
                String deviceId = telephonyManager.getDeviceId();
                Intrinsics.checkNotNullExpressionValue(deviceId, "tm!!.getDeviceId()");
                return deviceId;
            }
            if (!(ActivityCompat.checkSelfPermission(context, "android.permission.READ_PHONE_STATE") != 0 || SubscriptionManager.from(context) == null || SubscriptionManager.from(context).getActiveSubscriptionInfoList() == null)) {
                List<SubscriptionInfo> activeSubscriptionInfoList = SubscriptionManager.from(context).getActiveSubscriptionInfoList();
                Intrinsics.checkNotNullExpressionValue(activeSubscriptionInfoList, "from(context!!).activeSubscriptionInfoList");
                Method method = telephonyManager.getClass().getMethod("getImei", new Class[]{Integer.TYPE});
                Intrinsics.checkNotNullExpressionValue(method, "tm.javaClass.getMethod(\"…:class.javaPrimitiveType)");
                Iterator<SubscriptionInfo> it = activeSubscriptionInfoList.iterator();
                if (it.hasNext()) {
                    Object invoke = method.invoke(telephonyManager, new Object[]{Integer.valueOf(it.next().getSimSlotIndex())});
                    if (invoke != null) {
                        return (String) invoke;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                }
            }
            return "";
        }
    }
}
