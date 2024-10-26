package org.apache.log4j.net;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Vector;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

public class TelnetAppender extends AppenderSkeleton {
    private int port = 23;
    private SocketHandler sh;

    public boolean requiresLayout() {
        return true;
    }

    public void activateOptions() {
        try {
            SocketHandler socketHandler = new SocketHandler(this.port);
            this.sh = socketHandler;
            socketHandler.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int i) {
        this.port = i;
    }

    public void close() {
        this.sh.finalize();
    }

    /* access modifiers changed from: protected */
    public void append(LoggingEvent loggingEvent) {
        String[] throwableStrRep;
        this.sh.send(this.layout.format(loggingEvent));
        if (this.layout.ignoresThrowable() && (throwableStrRep = loggingEvent.getThrowableStrRep()) != null) {
            for (String send : throwableStrRep) {
                this.sh.send(send);
                this.sh.send(Layout.LINE_SEP);
            }
        }
    }

    protected class SocketHandler extends Thread {
        private int MAX_CONNECTIONS = 20;
        private Vector connections = new Vector();
        private boolean done = false;
        private ServerSocket serverSocket;
        private Vector writers = new Vector();

        public void finalize() {
            Enumeration elements = this.connections.elements();
            while (elements.hasMoreElements()) {
                try {
                    ((Socket) elements.nextElement()).close();
                } catch (Exception unused) {
                }
            }
            try {
                this.serverSocket.close();
            } catch (Exception unused2) {
            }
            this.done = true;
        }

        public void send(String str) {
            Enumeration elements = this.connections.elements();
            Enumeration elements2 = this.writers.elements();
            while (elements2.hasMoreElements()) {
                Socket socket = (Socket) elements.nextElement();
                PrintWriter printWriter = (PrintWriter) elements2.nextElement();
                printWriter.print(str);
                if (printWriter.checkError()) {
                    this.connections.remove(socket);
                    this.writers.remove(printWriter);
                }
            }
        }

        public void run() {
            while (!this.done) {
                try {
                    Socket accept = this.serverSocket.accept();
                    PrintWriter printWriter = new PrintWriter(accept.getOutputStream());
                    if (this.connections.size() < this.MAX_CONNECTIONS) {
                        this.connections.addElement(accept);
                        this.writers.addElement(printWriter);
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("TelnetAppender v1.0 (");
                        stringBuffer.append(this.connections.size());
                        stringBuffer.append(" active connections)\r\n\r\n");
                        printWriter.print(stringBuffer.toString());
                        printWriter.flush();
                    } else {
                        printWriter.print("Too many connections.\r\n");
                        printWriter.flush();
                        accept.close();
                    }
                } catch (Exception e) {
                    LogLog.error("Encountered error while in SocketHandler loop.", e);
                }
            }
        }

        public SocketHandler(int i) throws IOException {
            this.serverSocket = new ServerSocket(i);
        }
    }
}
