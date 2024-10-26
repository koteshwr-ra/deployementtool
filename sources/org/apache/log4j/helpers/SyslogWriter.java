package org.apache.log4j.helpers;

import java.io.IOException;
import java.io.Writer;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SyslogWriter extends Writer {
    static String syslogHost;
    final int SYSLOG_PORT = 514;
    private InetAddress address;
    private DatagramSocket ds;
    private final int port;

    public void close() {
    }

    public void flush() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0071  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SyslogWriter(java.lang.String r8) {
        /*
            r7 = this;
            java.lang.String r0 = ". All logging will FAIL."
            r7.<init>()
            r1 = 514(0x202, float:7.2E-43)
            r7.SYSLOG_PORT = r1
            syslogHost = r8
            if (r8 == 0) goto L_0x00b7
            java.lang.String r2 = "["
            int r3 = r8.indexOf(r2)
            r4 = -1
            if (r3 != r4) goto L_0x0025
            r3 = 58
            int r5 = r8.indexOf(r3)
            int r3 = r8.lastIndexOf(r3)
            if (r5 != r3) goto L_0x0023
            goto L_0x0025
        L_0x0023:
            r2 = -1
            goto L_0x006e
        L_0x0025:
            java.net.URL r3 = new java.net.URL     // Catch:{ MalformedURLException -> 0x0067 }
            java.lang.StringBuffer r5 = new java.lang.StringBuffer     // Catch:{ MalformedURLException -> 0x0067 }
            r5.<init>()     // Catch:{ MalformedURLException -> 0x0067 }
            java.lang.String r6 = "http://"
            r5.append(r6)     // Catch:{ MalformedURLException -> 0x0067 }
            r5.append(r8)     // Catch:{ MalformedURLException -> 0x0067 }
            java.lang.String r5 = r5.toString()     // Catch:{ MalformedURLException -> 0x0067 }
            r3.<init>(r5)     // Catch:{ MalformedURLException -> 0x0067 }
            java.lang.String r5 = r3.getHost()     // Catch:{ MalformedURLException -> 0x0067 }
            if (r5 == 0) goto L_0x0023
            java.lang.String r8 = r3.getHost()     // Catch:{ MalformedURLException -> 0x0067 }
            boolean r2 = r8.startsWith(r2)     // Catch:{ MalformedURLException -> 0x0067 }
            if (r2 == 0) goto L_0x0062
            int r2 = r8.length()     // Catch:{ MalformedURLException -> 0x0067 }
            r5 = 1
            int r2 = r2 - r5
            char r2 = r8.charAt(r2)     // Catch:{ MalformedURLException -> 0x0067 }
            r6 = 93
            if (r2 != r6) goto L_0x0062
            int r2 = r8.length()     // Catch:{ MalformedURLException -> 0x0067 }
            int r2 = r2 - r5
            java.lang.String r8 = r8.substring(r5, r2)     // Catch:{ MalformedURLException -> 0x0067 }
        L_0x0062:
            int r2 = r3.getPort()     // Catch:{ MalformedURLException -> 0x0067 }
            goto L_0x006e
        L_0x0067:
            r2 = move-exception
            java.lang.String r3 = "Malformed URL: will attempt to interpret as InetAddress."
            org.apache.log4j.helpers.LogLog.error(r3, r2)
            goto L_0x0023
        L_0x006e:
            if (r2 != r4) goto L_0x0071
            goto L_0x0072
        L_0x0071:
            r1 = r2
        L_0x0072:
            r7.port = r1
            java.net.InetAddress r1 = java.net.InetAddress.getByName(r8)     // Catch:{ UnknownHostException -> 0x007b }
            r7.address = r1     // Catch:{ UnknownHostException -> 0x007b }
            goto L_0x0093
        L_0x007b:
            r1 = move-exception
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r2.<init>()
            java.lang.String r3 = "Could not find "
            r2.append(r3)
            r2.append(r8)
            r2.append(r0)
            java.lang.String r2 = r2.toString()
            org.apache.log4j.helpers.LogLog.error(r2, r1)
        L_0x0093:
            java.net.DatagramSocket r1 = new java.net.DatagramSocket     // Catch:{ SocketException -> 0x009b }
            r1.<init>()     // Catch:{ SocketException -> 0x009b }
            r7.ds = r1     // Catch:{ SocketException -> 0x009b }
            goto L_0x00b6
        L_0x009b:
            r1 = move-exception
            r1.printStackTrace()
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r2.<init>()
            java.lang.String r3 = "Could not instantiate DatagramSocket to "
            r2.append(r3)
            r2.append(r8)
            r2.append(r0)
            java.lang.String r8 = r2.toString()
            org.apache.log4j.helpers.LogLog.error(r8, r1)
        L_0x00b6:
            return
        L_0x00b7:
            java.lang.NullPointerException r8 = new java.lang.NullPointerException
            java.lang.String r0 = "syslogHost"
            r8.<init>(r0)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.log4j.helpers.SyslogWriter.<init>(java.lang.String):void");
    }

    public void write(char[] cArr, int i, int i2) throws IOException {
        write(new String(cArr, i, i2));
    }

    public void write(String str) throws IOException {
        byte[] bytes = str.getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, this.address, this.port);
        DatagramSocket datagramSocket = this.ds;
        if (datagramSocket != null && this.address != null) {
            datagramSocket.send(datagramPacket);
        }
    }
}
