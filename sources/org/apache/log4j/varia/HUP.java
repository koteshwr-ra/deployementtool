package org.apache.log4j.varia;

import java.net.ServerSocket;
import java.net.Socket;
import org.apache.log4j.helpers.LogLog;

/* compiled from: ExternallyRolledFileAppender */
class HUP extends Thread {
    ExternallyRolledFileAppender er;
    int port;

    HUP(ExternallyRolledFileAppender externallyRolledFileAppender, int i) {
        this.er = externallyRolledFileAppender;
        this.port = i;
    }

    public void run() {
        while (!isInterrupted()) {
            try {
                while (true) {
                    Socket accept = new ServerSocket(this.port).accept();
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Connected to client at ");
                    stringBuffer.append(accept.getInetAddress());
                    LogLog.debug(stringBuffer.toString());
                    new Thread(new HUPNode(accept, this.er)).start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
