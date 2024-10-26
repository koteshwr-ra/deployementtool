package mc.csst.com.selfchassis.ui.adb;

import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShellUtils {
    private static final String TAG = "ShellUtils";

    public static void executeGeneralCmd(String str) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(str).getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            char[] cArr = new char[1024];
            while (true) {
                int read = bufferedReader.read(cArr);
                if (read != -1) {
                    stringBuffer.append(cArr, 0, read);
                } else {
                    bufferedReader.close();
                    Log.d(TAG, "shellExec: " + stringBuffer.toString());
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x004e A[SYNTHETIC, Splitter:B:23:0x004e] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0058 A[SYNTHETIC, Splitter:B:30:0x0058] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean RootCmd(java.lang.String r4) {
        /*
            r0 = 0
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch:{ Exception -> 0x0046, all -> 0x0043 }
            java.lang.String r2 = "su"
            java.lang.Process r1 = r1.exec(r2)     // Catch:{ Exception -> 0x0046, all -> 0x0043 }
            java.io.DataOutputStream r2 = new java.io.DataOutputStream     // Catch:{ Exception -> 0x0041 }
            java.io.OutputStream r3 = r1.getOutputStream()     // Catch:{ Exception -> 0x0041 }
            r2.<init>(r3)     // Catch:{ Exception -> 0x0041 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x003e, all -> 0x003b }
            r0.<init>()     // Catch:{ Exception -> 0x003e, all -> 0x003b }
            r0.append(r4)     // Catch:{ Exception -> 0x003e, all -> 0x003b }
            java.lang.String r4 = "\n"
            r0.append(r4)     // Catch:{ Exception -> 0x003e, all -> 0x003b }
            java.lang.String r4 = r0.toString()     // Catch:{ Exception -> 0x003e, all -> 0x003b }
            r2.writeBytes(r4)     // Catch:{ Exception -> 0x003e, all -> 0x003b }
            java.lang.String r4 = "exit\n"
            r2.writeBytes(r4)     // Catch:{ Exception -> 0x003e, all -> 0x003b }
            r2.flush()     // Catch:{ Exception -> 0x003e, all -> 0x003b }
            r1.waitFor()     // Catch:{ Exception -> 0x003e, all -> 0x003b }
            r2.close()     // Catch:{ Exception -> 0x0039 }
            r1.destroy()     // Catch:{ Exception -> 0x0039 }
        L_0x0039:
            r4 = 1
            return r4
        L_0x003b:
            r4 = move-exception
            r0 = r2
            goto L_0x0056
        L_0x003e:
            r4 = move-exception
            r0 = r2
            goto L_0x0048
        L_0x0041:
            r4 = move-exception
            goto L_0x0048
        L_0x0043:
            r4 = move-exception
            r1 = r0
            goto L_0x0056
        L_0x0046:
            r4 = move-exception
            r1 = r0
        L_0x0048:
            r4.printStackTrace()     // Catch:{ all -> 0x0055 }
            r4 = 0
            if (r0 == 0) goto L_0x0051
            r0.close()     // Catch:{ Exception -> 0x0054 }
        L_0x0051:
            r1.destroy()     // Catch:{ Exception -> 0x0054 }
        L_0x0054:
            return r4
        L_0x0055:
            r4 = move-exception
        L_0x0056:
            if (r0 == 0) goto L_0x005b
            r0.close()     // Catch:{ Exception -> 0x005e }
        L_0x005b:
            r1.destroy()     // Catch:{ Exception -> 0x005e }
        L_0x005e:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: mc.csst.com.selfchassis.ui.adb.ShellUtils.RootCmd(java.lang.String):boolean");
    }

    public static List<String> getIp() {
        ArrayList arrayList = new ArrayList();
        try {
            for (T inetAddresses : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                for (T t : Collections.list(inetAddresses.getInetAddresses())) {
                    if (!t.isLoopbackAddress() && (t instanceof Inet4Address) && t != null) {
                        arrayList.add(t.getHostAddress().toUpperCase());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
