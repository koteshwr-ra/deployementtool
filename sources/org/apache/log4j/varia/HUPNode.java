package org.apache.log4j.varia;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/* compiled from: ExternallyRolledFileAppender */
class HUPNode implements Runnable {
    DataInputStream dis;
    DataOutputStream dos;
    ExternallyRolledFileAppender er;
    Socket socket;

    public HUPNode(Socket socket2, ExternallyRolledFileAppender externallyRolledFileAppender) {
        this.socket = socket2;
        this.er = externallyRolledFileAppender;
        try {
            this.dis = new DataInputStream(socket2.getInputStream());
            this.dos = new DataOutputStream(socket2.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0034, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0035, code lost:
        org.apache.log4j.helpers.LogLog.error("Unexpected exception. Exiting HUPNode.", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        return;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r2 = this;
            java.io.DataInputStream r0 = r2.dis     // Catch:{ Exception -> 0x0034 }
            java.lang.String r0 = r0.readUTF()     // Catch:{ Exception -> 0x0034 }
            java.lang.String r1 = "Got external roll over signal."
            org.apache.log4j.helpers.LogLog.debug(r1)     // Catch:{ Exception -> 0x0034 }
            java.lang.String r1 = "RollOver"
            boolean r0 = r1.equals(r0)     // Catch:{ Exception -> 0x0034 }
            if (r0 == 0) goto L_0x0027
            org.apache.log4j.varia.ExternallyRolledFileAppender r0 = r2.er     // Catch:{ Exception -> 0x0034 }
            monitor-enter(r0)     // Catch:{ Exception -> 0x0034 }
            org.apache.log4j.varia.ExternallyRolledFileAppender r1 = r2.er     // Catch:{ all -> 0x0024 }
            r1.rollOver()     // Catch:{ all -> 0x0024 }
            monitor-exit(r0)     // Catch:{ all -> 0x0024 }
            java.io.DataOutputStream r0 = r2.dos     // Catch:{ Exception -> 0x0034 }
            java.lang.String r1 = "OK"
            r0.writeUTF(r1)     // Catch:{ Exception -> 0x0034 }
            goto L_0x002e
        L_0x0024:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ Exception -> 0x0034 }
            throw r1     // Catch:{ Exception -> 0x0034 }
        L_0x0027:
            java.io.DataOutputStream r0 = r2.dos     // Catch:{ Exception -> 0x0034 }
            java.lang.String r1 = "Expecting [RollOver] string."
            r0.writeUTF(r1)     // Catch:{ Exception -> 0x0034 }
        L_0x002e:
            java.io.DataOutputStream r0 = r2.dos     // Catch:{ Exception -> 0x0034 }
            r0.close()     // Catch:{ Exception -> 0x0034 }
            goto L_0x003a
        L_0x0034:
            r0 = move-exception
            java.lang.String r1 = "Unexpected exception. Exiting HUPNode."
            org.apache.log4j.helpers.LogLog.error(r1, r0)
        L_0x003a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.log4j.varia.HUPNode.run():void");
    }
}
